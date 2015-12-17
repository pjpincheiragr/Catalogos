package aplicacion.contabilidad.modificacion.logic;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

import aplicacion.modelo.logic.Data;
import aplicacion.modelo.logic.Logic;
import aplicacion.contabilidad.modificacion.gui._Frame;
import aplicacion.contabilidad.modificacion.logic._Data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class _Logic extends Logic {
	private _Frame frame;
	private _Data data;
	
	public void setFrame(JFrame _frame) {
		this.frame = (_Frame) _frame;
		super.setFrame(_frame);
	}

	public void setData(Data _data) {
		this.data = (_Data) _data;
		super.setData(_data);
	}
	
	public void init(){
		super.centrar();
		this.cargarLetras();
		this.cargarTC();
		frame.get_txt_sucursal().requestFocusInWindow();
	}
	public boolean check_id_cpte(String idcomprobante){
		boolean ok=false;
		String letra=frame.get_list_letra().getSelectedItem().toString();
		String sucursal=frame.get_txt_sucursal().getText();
		String numero=frame.get_txt_numero().getText();
		String _idcomprobante=sucursal+numero+letra;
		if (idcomprobante.length()==13){
			if (idcomprobante.compareTo(_idcomprobante)==0){
				ok=true;
			}
		}
		return ok;
	}
	
	public void clean(){
		frame.get_txt_cuenta().setText("");
		frame.get_txt_cuenta_descripcion().setText("");
		frame.get_txt_importe().setText("");
		frame.get_txt_fecha().setText("");
		frame.get_txt_sucursal().setText("");
		frame.get_txt_numero().setText("");
		frame.get_txt_numero().setEditable(true);
		frame.get_txt_sucursal().setEditable(true);
		frame.get_list_letra().setEnabled(true);
		frame.get_lst_tc().setEnabled(true);
		frame.get_btn_guardar().setEnabled(false);
		frame.get_txt_fecha().setEnabled(true);
		frame.get_txt_fecha().setText("");
		frame.get_txt_idcomprobante().setEditable(true);
		frame.get_txt_fecha().setEnabled(true);
		frame.get_btn_fecha().setEnabled(true);
		frame.get_txt_idcomprobante().setText("");
	}
	
	public void _evaluar_idcomprobante(JTextField tx) {
		String idcomprobante = tx.getText();
		String tc=this.getTipoTC();
		String sucursal=frame.get_txt_sucursal().getText();
		String numero=frame.get_txt_numero().getText();
		String letra=frame.get_list_letra().getSelectedItem().toString();
		frame.get_txt_cuenta().setText("");
		frame.get_txt_cuenta_descripcion().setText("");
		frame.get_txt_importe().setText("");
		if (idcomprobante.compareTo("") != 0) {
			boolean ok=this.check_id_cpte(idcomprobante);
			if (ok){
				Object[][] results=data.getCpte(tc, sucursal, numero, letra);
				if (results!=null){
					if (results.length>0){
						String cuenta=(String) results[0][0];
						String descripcion=(String) results[0][1];
						String importe=(String) results[0][2];
						String fecha=(String) results[0][3];
						frame.get_txt_cuenta().setText(cuenta);
						
						frame.get_txt_cuenta_descripcion().setText(descripcion);
						
						frame.get_txt_importe().setText(importe);
						frame.get_txt_fecha().setText(fecha);
						frame.get_txt_cuenta().setEditable(false);
						frame.get_txt_cuenta_descripcion().setEditable(false);
						frame.get_txt_importe().setEditable(false);
						frame.get_txt_numero().setEditable(false);
						frame.get_txt_sucursal().setEditable(false);
						frame.get_txt_idcomprobante().setEditable(false);
						frame.get_list_letra().setEnabled(false);
						frame.get_lst_tc().setEnabled(false);
						frame.get_list_letra().setEditable(false);
						frame.get_lst_tc().setEditable(false);
						frame.get_txt_fecha().requestFocusInWindow();
						
					}
					
				}
			}else{
				error("Error en Carga de Comprobante. Verifique");
			}
		}else{
			error("Error. Debe Cargar El comprobante");
		}
	}
	public void cargarTC(){
		frame.get_lst_tc().removeAllItems();
		frame.get_lst_tc().addItem("FC");
		frame.get_lst_tc().addItem("NC");
	}
	
	public void cargarLetras(){
		frame.get_list_letra().removeAllItems();
		frame.get_list_letra().addItem("A");
		frame.get_list_letra().addItem("B");
		
	}
	
	public void _evaluar_sucursal(JTextField tx) {
		String sucursal = tx.getText();
		boolean ok = this._evaluar_sucursal(sucursal);
		if (ok) {
			
			frame.get_txt_numero().requestFocusInWindow();
		} else {
			error("Error en numero de sucursal");
			tx.setSelectionStart(0);
			tx.setSelectionEnd(tx.getText().length());
		}
	}

	public String getTipoTC(){
		String tc=frame.get_lst_tc().getSelectedItem().toString();
		return tc;
	}
	
	public void _evaluar_letra(JComboBox combo) {

		String letra = combo.getSelectedItem().toString();
		String sucursal = frame.get_txt_sucursal().getText();
		String numero = frame.get_txt_numero().getText();
		String idcomprobante = sucursal + numero + letra;
		if (idcomprobante.length() == 13) {
			frame.get_txt_idcomprobante().setText(idcomprobante);
			frame.get_txt_idcomprobante().requestFocusInWindow();

		} else {
			error("Revise El id de comprobante ingresado");
		}

	}
	
	private boolean _evaluar_numero(String s) {
		boolean ok = true;
		int num = -1;
		String aux = "";

		if (s.compareTo("") == 0) {
			s = "1";
		}
		try {
			num = new Integer(s);

			aux = "" + num;
			while (aux.length() < 8) {
				aux = "0" + aux;
			}

		} catch (Exception e) {
			ok = false;

		}
		if (ok) {
			this.frame.get_txt_numero().setText(aux);
		}
		return ok;
	}

	public void _evaluar_numero(JTextField tx) {
		String numero = tx.getText();
		boolean ok = this._evaluar_numero(numero);
		
		if (ok) {
			frame.get_list_letra().requestFocusInWindow();
		} else {
			error("Error en numero de comprobante");
			tx.setSelectionStart(0);
			tx.setSelectionEnd(tx.getText().length());
		}
	}
	
	
	
	
	private boolean existe_cpte() {
		boolean existe=false;
		return existe;
	}
	
	private boolean _evaluar_sucursal(String s) {
		boolean ok = true;
		int suc = -1;
		String aux = "";

		if (s.compareTo("") == 0) {
			s = "1";
		}
		try {
			suc = new Integer(s);

			aux = "" + suc;
			while (aux.length() < 4) {
				aux = "0" + aux;
			}

		} catch (Exception e) {
			ok = false;

		}
		if (ok) {
			this.frame.get_txt_sucursal().setText(aux);
		}
		return ok;
	}

	public void guardar(){
		
		String tc=this.getTipoTC();
		String sucursal=frame.get_txt_sucursal().getText();
		String numero=frame.get_txt_numero().getText();
		String letra=frame.get_list_letra().getSelectedItem().toString();
		String fecha=frame.get_txt_fecha().getText();
		if (data.existe(tc, sucursal, numero, letra)){
			if (this.evaluarFecha(fecha)){
				boolean ok=confirmar("Confirme para modificar este comprobante:",3);
				if (ok){
					this._guardar(tc, sucursal, numero, letra, fecha);	
				}	
			}else{
				error("Error en fecha");
			}	
		}else{
			error("No existe Este Comprobante");
		}
		
		
		
	}
	public void _guardar(String tc,String sucursal,String numero,String letra,String fecha){
		List<String> instrucciones=data.getInstruccionesCambioFecha(tc, sucursal, numero, letra, fecha);
		data.beginTransaction();
		data.clearBatch();
		for (int i=0;i<instrucciones.size();i++){
			data.addBatch(instrucciones.get(i));
		}
		boolean error=data.executeBatch();
		if (!error){
			data.commitTransaction();
			aviso("Se modifico el comprobante correctamente");
		}else{
			data.rollbackTransaction();
			error("error modificando comprobante");
		}
	}
	
	
	

	private aplicacion.herramientas.java.buscadores.Fecha bFecha = null;

	public void BuscarFecha(JTextField tx) {
		if (bFecha == null) {
			bFecha = new aplicacion.herramientas.java.buscadores.Fecha(this
					.getConstructor());
		}

		bFecha.Buscar(tx);

	}

	public void BuscarFecha() {
		JTextField tx = frame.get_txt_fecha();
		this.BuscarFecha(tx);
	}

	public void _evaluar_fecha(JTextField tx) {
		String fecha = tx.getText();
		if (fecha.compareTo("") == 0) {
			this.BuscarFecha(tx);
		} else {
			if (this.evaluarFecha(fecha)) {
				boolean ok=confirmar("Confirme para habilitar grabacion:",3);
				if (ok){
					frame.get_btn_guardar().setEnabled(true);
					tx.setEnabled(false);
					frame.get_btn_fecha().setEnabled(false);
				}
			} else {
				error("Fecha " + fecha + " incorrecta");
				tx.setSelectionStart(0);
				tx.setSelectionEnd(tx.getText().length());
			}
		}

	}

}
