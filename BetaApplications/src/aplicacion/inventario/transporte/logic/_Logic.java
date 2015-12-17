package aplicacion.inventario.transporte.logic;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.*;
import javax.swing.JTextField;

import aplicacion.herramientas.java.*;
import aplicacion.herramientas.java.launcher.logic.*;



import aplicacion.herramientas.java.table.CellEditor;
import aplicacion.herramientas.java.table.Column;
import aplicacion.herramientas.java.table.CustomTable;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Data;
import aplicacion.modelo.logic.Logic;
import aplicacion.inventario.transporte.gui._Frame;
import aplicacion.inventario.transporte.interfaces.*;
import aplicacion.inventario.transporte.logic._Data;
import aplicacion.herramientas.java.sortableselector.*;

public class _Logic extends Logic {
	private _Frame frame;
	private _Data data;
	private String estado="";
	private int current;
	private int errors;
	private int lenght;
	private boolean done,canceled;
	private Timer Timer;  //  @jve:decl-index=0:
	private Crono crono;
	
	
	private aplicacion.herramientas.java.visualselector.constructor._Constructor vSelector=null;
	
	public void setFrame(JFrame _frame){
		this.frame=(_Frame)_frame;
		super.setFrame(_frame);
	}
	
	public void setData(Data _data){
		this.data=(_Data)_data;
		super.setData(_data);
	}
	
	
	public void BuscarTransporte() {
		this.BuscarTransporte(frame.get_txt_idtransporte());
	}
	
	
	private aplicacion.herramientas.java.buscadores.Transporte bTransporte=null;
	public void BuscarTransporte(JTextField ext) {
		 if (bTransporte==null){
			 bTransporte=new aplicacion.herramientas.java.buscadores.Transporte(this.getConstructor());
		 }
	 
	 //bTransporte.setDescripcion(frame.get_txt_transporte_descripcion());
	 bTransporte.Buscar(ext);
	}
	
	private aplicacion.herramientas.java.visualizadores.Transporte vTransporte=null;
	public void buscarTransporte(JTextField tx) {
		if (vTransporte!=null){
			vTransporte.close();
		}
		vTransporte=new aplicacion.herramientas.java.visualizadores.Transporte(this.getConstructor());
		int n = vTransporte.Buscar(tx);
		if (n == 0) {
				aviso("no hay Transportes con ese codigo");
		}
	}


	

	public void clean(){
		this.frame.get_txt_idtransporte().setText("");
		this.frame.get_txt_transporte_descripcion().setText("");
		
		this.block();
	}
	
	public void check_description(){
		String description=this.frame.get_txt_transporte_descripcion().getText();
		if (description.compareTo("")!=0){
			
		}else {
			aviso( "Ingrese una descripcion al transporte");
			this.frame.get_txt_transporte_descripcion().requestFocusInWindow();
		}
	}
	public void block(){
		
		this.frame.get_txt_transporte_descripcion().setEditable(false);
		this.frame.get_txt_idtransporte().setEditable(true);
		
	}

	public void unblock(){
		
		this.frame.get_txt_transporte_descripcion().setEditable(true);
		this.frame.get_txt_idtransporte().setEditable(false);
		
	}

	private boolean check_transporte(String code){
		boolean exist=false;
		
		Object[][] results=data.getTransporte(code);
		if (results!=null){
			if (results.length>0){
				exist=true;
			}
		}
		return exist;
	}

	
	public void Guardar(){
		boolean ok=true;
		String idtransporte=this.frame.get_txt_idtransporte().getText();
		ok=this.frame.get_txt_transporte_descripcion().getText().compareTo("")!=0;
		if (ok){
			ok=this.check_transporte(idtransporte);
			if (!ok){
				String nuevo=data.getProximoTransporte();
				ok=idtransporte.compareTo(nuevo)==0;
			}
			if (ok){
				System.out.println("Se puede grabar");
				String iduser=this.validar_usuario();
				if (iduser.compareTo("")!=0){
					this.grabar(iduser);	
				}else{
					error("OPERACION CANCELADA");
				}	
			}
			
					
		}else {
			aviso( "Ingrese una descripcion al transporte");
		}
		
	}

	public void grabar(String iduser){
		String idtransporte=this.frame.get_txt_idtransporte().getText();
		String descripcion=this.frame.get_txt_transporte_descripcion().getText();
		String q="";
		if (this.check_transporte(this.frame.get_txt_idtransporte().getText())){
			q=data.getUpdate(idtransporte, descripcion);
		}else {
			
			q=data.getInsert(idtransporte, descripcion);
		}
		data.beginTransaction();
		data.clearBatch();
		int idoperacion=data.getProximoOperacion();
		String _descripcion="CAMBIO DE TRANSPORTE ("+idtransporte+")";
		data.addBatch(q);
		q=data.getOperacion(""+idoperacion, iduser, _descripcion);
		data.addBatch(q);
		boolean error=data.executeBatch();
		if (!error){
			data.commitTransaction();
			aviso( "Se Grabo Correctamente EL TRANSPORTE");
		}else{
			data.rollbackTransaction();
			aviso( "Error Grabando TRANSPORTE");
		}
		
	}

	public void cargarTransporte(String code){
		
		Object[][] results=data.getTransporte(code);
		if (results!=null){
			if (results.length>0){
				String idtransporte="";
				String descripcion="";
				String fcosto="";
				String fpublico="";
				idtransporte=(String) results[0][0];
				descripcion=(String) results[0][1];
				
				this.frame.get_txt_idtransporte().setText(idtransporte);
				this.frame.get_txt_transporte_descripcion().setText(descripcion);
				
			}
		}
		
	}

	
	
	
	

   
			   
		
	
   
	

	public void evaluar_transporte(JTextField tx){
		String valor="";
		valor=tx.getText();
		if (valor.compareTo("")==0){
			this.buscarTransporte(tx);
		}else {
			int n=0;
			try {
				n=new Integer(valor);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (n>0){
				if (data.check_transporte(valor)){
					this.Cargar();
				}else{
					error("Codigo de transporte incorrecto");
				}
				
			}else {
				System.out.println("visualizador");
				this.buscarTransporte(tx);
			}
		}
	}

	public void nuevo(){
		frame.get_txt_idtransporte().setText("");
		frame.get_txt_transporte_descripcion().setText("");
		String idtransporte=data.getProximoTransporte();
		frame.get_txt_idtransporte().setText(idtransporte);
		this.unblock();
		frame.get_txt_transporte_descripcion().requestFocus();
	}

	private boolean _preguntar_si_crea_nueva_transporte(String idtransporte){
		final String _idtransporte=idtransporte;
		final Boolean[] answer = new Boolean[1];
		Runnable _execute=new Runnable() {
				   public void run() {
					  answer[0] =preguntar("Confirmar", "El Transporte "+_idtransporte+" no existe. Crea uno nuevo?");
				   }
				 };
		this.invokeAndWait(_execute);
		return answer[0];
	}
	
	public void Cargar(){
		String idtransporte=this.frame.get_txt_idtransporte().getText();
		if (this.check_transporte(idtransporte)){
			this.cargarTransporte(idtransporte);
			this.unblock();
		}else {
			
			if (this._preguntar_si_crea_nueva_transporte(idtransporte)){
				this.unblock();
					
			}else{
				this.frame.get_txt_idtransporte().setEditable(true);
				this.block();
			}
			
			
		}
	}
	
	public void focus(){
		frame.get_txt_idtransporte().requestFocusInWindow();
	}
	
	
}
