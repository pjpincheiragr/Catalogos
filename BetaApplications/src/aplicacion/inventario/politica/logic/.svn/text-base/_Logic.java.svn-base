package aplicacion.inventario.politica.logic;

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
import aplicacion.inventario.politica.gui._Frame;
import aplicacion.inventario.politica.interfaces.*;
import aplicacion.inventario.politica.logic._Data;
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
	
		
	
	

	private class _taskAplicar {
		_taskAplicar() {
			_taskworkAplicar();

		}
	}

	public void _recodificar() {
		this.createTimer();
		SwingWorker worker = null;
		if (worker == null) {
			worker = new SwingWorker() {
				@Override
				public Object construct() {
					return new _taskAplicar();
				}
			};
		}
		if (Timer!=null) {
			Timer.start();
		}
		worker.start();
	}

	//metodos basicos de tareas swing
	public void createTimer(){
		current=0;
		errors=0;
		canceled = false;
		done = false;
		crono=new Crono();
		crono.start();
		Timer=new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (done |canceled){
					endbar();
					Timer.stop();
					canceled=false;
					done=false;
					}else {
					updateBar();
				}
			}
		}); 
		
	}
	
	
	public void updateBar(){
		frame.getJProgressBar().setMaximum(lenght);
		frame.getJProgressBar().setValue(current);
		frame.getJProgressBar().setString(estado+" "+current+"/"+lenght+" "+crono.elapsed());
		frame.getJProgressBar().setStringPainted(true);
	}
	
	public void endbar(){
		estado="";
		frame.getJProgressBar().setString("");
		frame.getJProgressBar().setIndeterminate(false);
		frame.getJProgressBar().setValue(0);
	}
	
	
	public void BuscarPolitica() {
		this.BuscarPolitica(frame.get_txt_idpolitica());
	}
	
	
	private aplicacion.herramientas.java.buscadores.Politica bPolitica=null;
	public void BuscarPolitica(JTextField ext) {
		 if (bPolitica==null){
			 bPolitica=new aplicacion.herramientas.java.buscadores.Politica(this.getConstructor());
		 }
	 
	 bPolitica.setDescripcion(frame.get_txt_politica_descripcion());
	 bPolitica.Buscar(ext);
	}
	
	private aplicacion.herramientas.java.visualizadores.Politica vPolitica=null;
	public void buscarPolitica(JTextField tx) {
		if (vPolitica!=null){
			vPolitica.close();
		}
		vPolitica=new aplicacion.herramientas.java.visualizadores.Politica(this.getConstructor());
		int n = vPolitica.Buscar(tx);
		if (n == 0) {
				aviso("no hay Politicas con ese codigo");
		}
	}


	

	public void clean(){
		frame.get_txt_idpolitica().setEditable(true);
		this.frame.get_txt_idpolitica().setText("");
		this.frame.get_txt_politica_descripcion().setText("");
		this.frame.get_txt_formula_costo().setText("");
		this.frame.get_txt_formula_publico().setText("");
		this.frame.get_txt_idpolitica().setEditable(true);
		this.frame.get_txt_mcosto().setText("");
		this.frame.get_txt_mpublico().setText("");
		frame.getJProgressBar().setString("");
		this.block();
	}
	
	public void check_description(){
		String description=this.frame.get_txt_politica_descripcion().getText();
		if (description.compareTo("")!=0){
			this.frame.get_txt_formula_costo().requestFocusInWindow();
		}else {
			aviso( "Ingrese una descripcion a la politica");
			this.frame.get_txt_politica_descripcion().requestFocusInWindow();
		}
	}
	public void block(){
		
		this.frame.get_txt_politica_descripcion().setEditable(false);
		this.frame.get_txt_formula_costo().setEditable(false);
		this.frame.get_txt_formula_publico().setEditable(false);
		this.frame.get_txt_idpolitica().setEditable(true);
		
	}

	public void unblock(){
		
		this.frame.get_txt_politica_descripcion().setEditable(true);
		this.frame.get_txt_formula_costo().setEditable(true);
		this.frame.get_txt_formula_publico().setEditable(true);
		this.frame.get_txt_idpolitica().setEditable(false);
		
	}

	private boolean check_politica(String code){
		boolean exist=false;
		
		Object[][] results=data.getPolitica(code);
		if (results!=null){
			if (results.length>0){
				exist=true;
			}
		}
		return exist;
	}

	public double convert_formula(String formula){
		double tmp=0.0;
		try {
			tmp=new Convertidor().formula(formula);
		}catch(Exception e){
			
		}
		return tmp;
	}

	private boolean check_fcosto(){
		boolean ok=false;
		double tmp=0.0;
		tmp=this.convert_formula(this.frame.get_txt_formula_costo().getText());
		ok=(tmp>0);
		return ok;
	}

	private boolean check_fpublico(){
		boolean ok=false;
		double tmp=0.0;
		tmp=this.convert_formula(this.frame.get_txt_formula_publico().getText());
		ok=(tmp>0);
		return ok;
	}


	public void Guardar(){
		boolean ok=true;
		
		ok=this.frame.get_txt_politica_descripcion().getText().compareTo("")!=0;
		if (ok){
			ok=this.check_fcosto();
			if (ok){
				ok=this.check_fpublico();
				if (ok){
					System.out.println("Se puede grabar");
					String iduser=this.validar_usuario();
					if (iduser.compareTo("")!=0){
						this.grabar(iduser);	
					}else{
						error("OPERACION CANCELADA");
					}
					
				}else {
					aviso( "Revise la formula de precio publico");
				}
			}else {
				aviso( "Revise la formula de precio costo");
			}
			
		}else {
			aviso( "Ingrese una descripcion a la politica");
		}
		
	}

	public void nueva(){
		this.clean();
		Object[][] tmp=data.getListadePoliticas();
		int value=0;
		boolean found=false;
		if (tmp!=null){
			if (tmp.length>0){
				String idpolitica="";
				int i=0;
				while (i<tmp.length &!found){
					String _valor=tmp[i][0].toString();
					System.out.println(_valor+"<?>"+value);
					found=new Integer(_valor)!=(value);
					if (!found){
						value++;	
					}else{
						idpolitica=""+value;
						while (idpolitica.length()<4){
							idpolitica="0"+idpolitica;
						}
						System.out.println("idpolitica nueva?"+idpolitica);
						found=!data.exist(idpolitica);
					}
					i++;
				}
				
				System.out.println("idpolitica nueva?"+idpolitica);
				if (!data.exist(idpolitica)){
					this.unblock();
					frame.get_txt_idpolitica().setText(idpolitica);
					frame.get_txt_idpolitica().setEditable(false);
					frame.get_txt_politica_descripcion().requestFocusInWindow();
					
				}
			}
		}
		
	}
	public void grabar(String iduser){
		String idpolitica=this.frame.get_txt_idpolitica().getText();
		String descripcion=this.frame.get_txt_politica_descripcion().getText();
		String fcosto=this.frame.get_txt_formula_costo().getText();
		String fpublico=this.frame.get_txt_formula_publico().getText();
		double mcosto=this.convert_formula(fcosto);
		double mpublico=this.convert_formula(fpublico);
		String q="";
		if (this.check_politica(this.frame.get_txt_idpolitica().getText())){
			q=data.getUpdate(idpolitica, descripcion, fcosto, fpublico, mcosto, mpublico);
		}else {
			q=data.getInsert(idpolitica, descripcion, fcosto, fpublico, mcosto, mpublico);
		}
		System.out.println(q);
		int idoperacion=data.getProximoOperacion();
		String _descripcion="CAMBIO DE POLITICA DE PRECIOS ("+frame.get_txt_idpolitica().getText()+")";
		data.clearBatch();
		data.addBatch(q);
		q=data.getOperacion(""+idoperacion, iduser, _descripcion);
		data.addBatch(q);
		boolean error=data.executeBatch();
		if (!error){
			data.commitTransaction();
			aviso( "Se Grabo Correctamente la politica");
		}else{
			data.rollbackTransaction();
			aviso( "Error Grabando politica");
		}
		
	}

	public void cargarPolitica(String code){
		
		Object[][] results=data.getPolitica(code);
		if (results!=null){
			if (results.length>0){
				String idpolitica="";
				String descripcion="";
				String fcosto="";
				String fpublico="";
				idpolitica=(String) results[0][0];
				descripcion=(String) results[0][1];
				fcosto=(String) results[0][2];
				fpublico=(String) results[0][3];
				double mcosto=this.convert_formula(fcosto);
				double mpublico=this.convert_formula(fpublico);
				this.frame.get_txt_idpolitica().setText(idpolitica);
				this.frame.get_txt_politica_descripcion().setText(descripcion);
				this.frame.get_txt_formula_costo().setText(fcosto);
				this.frame.get_txt_formula_publico().setText(fpublico);
				this.frame.get_txt_mcosto().setText(new Convertidor().getMoney(mcosto, 4));
				this.frame.get_txt_mpublico().setText(new Convertidor().getMoney(mpublico, 4));
			}
		}
		
	}

	
	public void Aplicar() {
		String iduser=this.validar_usuario();
		if (iduser.compareTo("")!=0){
			
			String descripcion="APLICACION DE POLITICA DE PRECIOS ("+frame.get_txt_idpolitica().getText()+")";
			data.beginTransaction();
			int idoperacion=data.getProximoOperacion();
			data.clearBatch();
			String q=data.getOperacion(""+idoperacion, iduser, descripcion);
			data.addBatch(q);
			boolean error=data.executeBatch();
			if (!error){
				data.commitTransaction();
				this.goAplicar();
			}else{
				data.rollbackTransaction();
				error("Error al Registrar Operacion");
			}
			
		}else{
			error("OPERACION CANCELADA");
		}
	}
	public void goAplicar() {
		this.createTimer();
		SwingWorker worker = null;
		if (worker == null) {
			worker = new SwingWorker() {
				@Override
				public Object construct() {
					return new _taskAplicar();
				}
			};
		}
		if (Timer!=null) {
			Timer.start();
		}
		worker.start();
	}

	

   
			   
		
	
   
	private void _taskworkAplicar(){
		estado="Buscando Articulos con Politica";
		frame.getJProgressBar().setIndeterminate(true);
		this.errors=0;
		String fcosto=this.frame.get_txt_formula_costo().getText();
		String fpublico=this.frame.get_txt_formula_publico().getText();
		double mcosto=this.convert_formula(fcosto);
		double mpublico=this.convert_formula(fpublico);
		
		
		String idpolitica=frame.get_txt_idpolitica().getText();
		
		
		Object[][] results=data.getArticulos(idpolitica);
		
		if (mcosto>0 & mpublico>0){
			if (results!=null){
				if (results.length>0){
					frame.getJProgressBar().setIndeterminate(false);
					this.lenght = results.length;
					int i=0;
					while (i<results.length & !canceled){
					
						current++;
						String idarticulo="";
						String precio5="";
						double p5=0.0;
						try {
							idarticulo=(String) results[i][0];
							precio5=(String) results[i][1];
							
						}catch(Exception e){
							
						}
						
						try {
							p5=new Double(precio5);
						}catch(Exception e){
							errors++;
						}
						if (p5>0){
							data.actualizar_articulo(idarticulo, p5, mcosto, mpublico);
						}
						estado="Aplicando politica a Articulo " + idarticulo;
						i++;
					}
					 aviso("Se Completo la Aplicacion de Politicas con "+lenght+" operaciones y "+errors+" errores");
				}else {
					aviso("No hay Articulos que utilicen esta Politica");
				}
			}
		}else {
			error("Error en Formulas de Politica. Se cancelar la aplicacion en articulos!");
		}
		done=true;
	}

	

	public void evaluar_politica(JTextField tx){
		String valor="";
		valor=tx.getText();
		if (valor.compareTo("")==0){
			this.buscarPolitica(tx);
		}else {
			int n=-1;
			try {
				n=new Integer(valor);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (n>=0){
				if (data.check_politica(valor)){
					this.Cargar();
				}else{
					error("Codigo de politica incorrecto");
				}
				
			}else {
				System.out.println("visualizador");
				this.buscarPolitica(tx);
			}
		}
	}

	public void cancelar_tarea(){
		if (preguntar("Confirmar","Cancela Tarea en Ejecucion?")){
			this.canceled=true;
		}
		
	}

	public void recalcular_costo(JTextField tx){
		String fcosto=tx.getText();
		double mcosto=this.convert_formula(fcosto);
		this.frame.get_txt_mcosto().setText(new Convertidor().getMoney(mcosto, 5));
		if (mcosto<=0){
			aviso( "Error En Formula");
			tx.requestFocusInWindow();
		}else {
			this.frame.get_txt_formula_publico().requestFocusInWindow();
		}
	}

	public void recalcular_publico(JTextField tx){
		String fpublico=tx.getText();
		double mpublico=this.convert_formula(fpublico);
		this.frame.get_txt_mpublico().setText(new Convertidor().getMoney(mpublico, 5));
		if (mpublico<=0){
			aviso( "Error En Formula");
			tx.requestFocusInWindow();
		}	else {
			
		}
	}

	private boolean _preguntar_si_crea_nueva_politica(String idpolitica){
		final String _idpolitica=idpolitica;
		final Boolean[] answer = new Boolean[1];
		Runnable _execute=new Runnable() {
				   public void run() {
					  answer[0] =preguntar("Confirmar", "La politica "+_idpolitica+" no existe. Crea una nueva?");
				   }
				 };
		this.invokeAndWait(_execute);
		return answer[0];
	}
	
	public void Cargar(){
		String idpolitica=this.frame.get_txt_idpolitica().getText();
		if (this.check_politica(idpolitica)){
			this.cargarPolitica(idpolitica);
			this.frame.get_txt_idpolitica().setEditable(false);
			this.frame.get_txt_mcosto().requestFocusInWindow();
			this.unblock();
		}else {
			
			if (this._preguntar_si_crea_nueva_politica(idpolitica)){
				this.unblock();
				this.frame.get_txt_formula_costo().setText("<CLASE5>");
				this.frame.get_txt_formula_publico().setText("<COSTO>");
				this.frame.get_txt_idpolitica().setEditable(false);
				this.frame.get_txt_politica_descripcion().requestFocusInWindow();	
			}else{
				this.frame.get_txt_idpolitica().setEditable(true);
				this.block();
			}
			
			
		}
	}
	
	public void focus(){
		frame.get_txt_idpolitica().requestFocusInWindow();
	}
	
	
}
