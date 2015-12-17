package aplicacion.gestion.agenda.logic;

import java.awt.Font;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import aplicacion.herramientas.java.*;
import aplicacion.herramientas.java.sortableselector.logic.Filtro;
import aplicacion.herramientas.java.sortableselector.logic.columna;
import aplicacion.herramientas.java.table.Column;
import aplicacion.herramientas.java.table.CustomTable;
import aplicacion.gestion.agenda.gui._Frame;
import aplicacion.gestion.agenda.interfaces._Interface;
import aplicacion.modelo.logic.Logic;
import aplicacion.modelo.logic.Data;
import aplicacion.modelo.interfaces.*;

public class _Logic extends Logic {
	private _Frame frame = null;
	private _Data data = null;
	private boolean nuevo=false;
	public _Logic() {
		
	}

	public void setFrame(JFrame _frame) {
		this.frame = (_Frame) _frame;
		super.setFrame(_frame);
	}

	public void setData(Data _data) {
		this.data = (_Data) _data;
		super.setData(_data);
	}

	public void clean() {
		frame.get_txt_agenda().setText("");
		frame.get_txt_titulo().setText("");
		frame.get_txt_vendedor_descripcion().setText("");
		frame.get_txt_mensaje().setText("");
		frame.get_txt_idevento().setText("");
	
	}

	
	public void init(){
		this.clean();
	}
	
	public void getToday() {
		_Frame _frame = (_Frame) this._frame;
		_frame.get_txt_agenda().setText(
				new Convertidor().getDateWithFormat("dd-MM-yyyy HH:mm:ss"));
	}

	public _Data getData() {
		return this.data;
	}

	private aplicacion.herramientas.java.evaluadores.Hora Hora = null;

	public void initialize_Hora() {
		Hora = new aplicacion.herramientas.java.evaluadores.Hora() {
			public void cargar(JTextField tx) {

			}
		};
		Hora.setConstructor(this.getConstructor());
	}

	public void BuscarHora(JTextField tx) {
		Hora.Buscar(tx);
	}

	public void BuscarHora() {
		Hora.Buscar(frame.get_txt_agenda());
	}

	public void evaluarHora(JTextField tx) {
		Hora.evaluate(tx);
	}

	private aplicacion.herramientas.java.evaluadores.Vendedor Vendedor=null;
	public void initialize_Vendedor(){
		Vendedor=new aplicacion.herramientas.java.evaluadores.Vendedor(){
			public void cargar(JTextField tx){
				String codigo=tx.getText();
				Object[][] results=this.getInfo(codigo);
				String descripcion=(String) results[0][1];
				String cod=(String) results[0][0];
				frame.get_txt_idvendedor().setText(cod);
				frame.get_txt_vendedor_descripcion().setText(descripcion);	
				
				
				
				
				
			}
		};
		Vendedor.setConstructor(this.getConstructor());
	}
	public void BuscarVendedor(JTextField tx){
		Vendedor.Buscar(tx);
	}
	public void BuscarVendedor(){
		Vendedor.Buscar(frame.get_txt_idvendedor());
	}
	public void buscarVendedor(JTextField tx){
		Vendedor.buscar(tx);
	}
	
	public void evaluarVendedor(JTextField tx){
		tx.setText(tx.getText().replaceAll(" ", ""));
		Vendedor.evaluate(tx);
	}
	
	public String validar_vendedor() {
		String idvendedor = "";
		while (idvendedor.compareTo("") == 0) {
			String password = this.requestPassword("Ingrese Su Clave:");
			idvendedor = data.getVendedorValidacion(password);
			if (idvendedor.compareTo("") == 0) {
				error("Error de Validacion de Usuario");
			}
		}
		return idvendedor;
	}
	public boolean elegir_vendedor() {
		boolean ok = false;
		System.out.println("elegir vendedor");
		String idvendedor = this.validar_vendedor();
		frame.get_txt_idvendedor().setText(idvendedor);

		if (idvendedor.compareTo("") != 0) {
			ok = true;
			this.evaluarVendedor(frame.get_txt_idvendedor());
			frame.get_txt_idvendedor().setEditable(false);
						
		} else {
			ok = false;
		}

		return ok;
	}

	public void evaluate_idaviso(JTextField tx){
		String idaviso=tx.getText();
		if (idaviso.compareTo("")!=0){
			
			if (nuevo){
				String proximo=data.getProximoPGCorrecto();
				if (idaviso.compareTo(proximo)==0){
					
				}else{
					proximo=data.getProximoPGCorrecto();
					frame.get_txt_idevento().setText(proximo);
				}
				frame.get_txt_agenda().requestFocusInWindow();
			}else{
				if (data.exist(idaviso)){
					this.cargarAviso(idaviso);
				}
			}
		}
	}
	
	public void cargarAviso(String idaviso){
		Object[][] results=data.getAviso(idaviso);
		if (results!=null){
			if (results.length>0){
				String _idaviso=(String)results[0][0];
				String titulo=(String)results[0][1];
				String mensaje=(String)results[0][2];
				
				String iduser=(String)results[0][3];
				String agenda=(String)results[0][4];
				frame.get_txt_idevento().setEditable(false);
				frame.get_txt_agenda().setText(agenda);
				frame.get_txt_idvendedor().setText(iduser);
				this.evaluarVendedor(frame.get_txt_idvendedor());
				frame.get_txt_titulo().setText(titulo);
				frame.get_txt_mensaje().setText(mensaje);
			}
		}
	}
	public void obtener_proximo_cpte() {
		String numero = data.getProximoPGCorrecto();
		frame.get_txt_idevento().setText(numero);
		nuevo=true;
	}
		
	public void nuevo() {
		this.clean();
		this.obtener_proximo_cpte();
		frame.get_txt_idevento().requestFocusInWindow();
	}
}