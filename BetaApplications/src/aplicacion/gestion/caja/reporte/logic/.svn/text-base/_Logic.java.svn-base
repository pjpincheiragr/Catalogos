package aplicacion.gestion.caja.reporte.logic;

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
import aplicacion.gestion.caja.manejo.logic.DateRenderer;
import aplicacion.gestion.caja.manejo.logic.MoneyRenderer;
import aplicacion.gestion.caja.reporte.gui._Frame;
import aplicacion.gestion.caja.reporte.interfaces._Interface;

import aplicacion.modelo.logic.Logic;
import aplicacion.modelo.logic.Data;
import aplicacion.modelo.interfaces.*;

public class _Logic extends Logic{
	private _Frame frame=null;
	private _Data data=null;
	private aplicacion.herramientas.java.visualselector.constructor._Constructor vSelector=null;
	
	public _Logic(){
		
	}
	public void setFrame(JFrame _frame){
		this.frame=(_Frame)_frame;
		super.setFrame(_frame);
	}
	
	public void setData(Data _data){
		this.data=(_Data)_data;
		super.setData(_data);
	}
	
	/**
	 * Metodo para seleccionar la caja con que debe trabajar la aplicacion.
	 * Este metodo se utiliza cuando se llama a esta aplicacion desde gestion de caja 
	 * @param idcaja
	 */
	public void setCaja(String idcaja){
		for (int i=0;i<frame.get_list_cajas().getItemCount();i++){
			if (idcaja.compareTo(frame.get_list_cajas().getItemAt(i).toString())==0){
				frame.get_list_cajas().setSelectedIndex(i);
			}
		}
		frame.get_txt_fecha().requestFocusInWindow();
	}
	
	public void clean(){
		
		frame.get_txt_fecha().setText("");
		frame.get_txt_fecha_hasta().setText("");
		
		cargar_cajas();
		this.setInitialDate();
	}
	
	public void cancelar(){
		if (preguntar("Confirmar","Cancela?")) {
			clean();
		}
	}
	
	
public void getToday(){
_Frame _frame=(_Frame) this._frame;
_frame.get_txt_fecha().setText(
			new Convertidor().getDateWithFormat("dd-MM-yyyy")
			);	
}
	
	
public Double getTotal(Object[][] aux){
		Double tmp=0.0;
		for (int i=0;i<aux.length;i++){
			double _tmp=0.0;
			try {
				_tmp= new Double((String) aux[i][4]);
				
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tmp+=_tmp;
		}
		return tmp;
}
	


public void cargar(){
		_Data data=(_Data)this.getData();
		String caja=frame.get_list_cajas().getSelectedItem().toString();
		String desde="01-01-1900";
		String hasta="01-01-2900";
		String _desde=frame.get_txt_fecha().getText();
		String _hasta=frame.get_txt_fecha_hasta().getText();
		if (_desde.compareTo("")!=0){
			desde=_desde;
		}
		if (_hasta.compareTo("")!=0){
			hasta=_hasta;
		}
		
		
}
	
public void init(){
		this.cargar_cajas();
		this.setInitialDate();
}
	
		
public _Data getData(){
		return this.data;
	}
	
public void evaluate_caja(JComboBox cb){
		String desc=cb.getSelectedItem().toString();
		desc=data.getDetalleCaja(desc);
		if (desc.compareTo("")!=0){
			frame.get_txt_caja_detalle().setText(desc);
		}
		
	}

public void cargar_cajas(){
	frame.get_list_cajas().removeAllItems();
	frame.get_list_cajas().removeActionListener(null);
	_Data data=(_Data) _data;
	String iduser=this.getConstructor().getIduser();
	Object[][] results=data.get_cajas_origen(iduser);
	for (int i=0;i<results.length;i++){
		
		try {
			frame.get_list_cajas().addItem(results[i][0]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


private aplicacion.herramientas.java.visualizadores.Cuenta vCuenta=null;
public void buscarCuenta(JTextField tx) {
	if (vCuenta!=null){
		vCuenta.close();
	}
	vCuenta=new aplicacion.herramientas.java.visualizadores.Cuenta(this.getConstructor());
	int n = vCuenta.Buscar(tx);
	if (n == 0) {
			aviso("no hay Cuentas con ese codigo");
	}
}
private aplicacion.herramientas.java.ireport.constructor._Constructor reporte=null;
public void reporte(){
	if (reporte!=null){
		 reporte.dispose();
	}
	String caja=frame.get_list_cajas().getSelectedItem().toString();
	String desde="01-01-1900";
	String hasta="01-01-2900";
	String _desde=frame.get_txt_fecha().getText();
	String _hasta=frame.get_txt_fecha_hasta().getText();
	if (_desde.compareTo("")!=0){
		desde=_desde;
	}
	if (_hasta.compareTo("")!=0){
		hasta=_hasta;
	}
	reporte=new aplicacion.herramientas.java.ireport.constructor._Constructor();
	HashMap param=new HashMap();
	param.put("caja", caja);
	param.put("desde", desde);
	param.put("hasta", hasta);
	reporte.setParameter(_parametros.LookAndFeel,this.getConstructor().getLookAndFeelTheme());
	reporte.setParameter(_parametros.connector,this.getConstructor().getConnectionHandler());
	reporte.setParameter(aplicacion.herramientas.java.ireport.interfaces._parametros.parametros, param);
	if (frame.get_chk_beta().isSelected()){
		reporte.setParameter(aplicacion.herramientas.java.ireport.interfaces._parametros.reporte, "cajab.jasper");	
	}else{
		reporte.setParameter(aplicacion.herramientas.java.ireport.interfaces._parametros.reporte, "cajaab.jasper");
	}
	
	reporte.build(this.getConstructor());
	reporte.init();	
}




public void setInitialDate(){
	String aux=data.getSystemDate();
	frame.get_txt_fecha().setText(aux);
}

public void transfer_fecha_focus(JTextField tx){
	if (tx.getName()==_Interface._txt_fecha){
		frame.get_txt_fecha_hasta().requestFocusInWindow();
	}
	if (tx.getName()==_Interface._txt_fecha_hasta){
		cargar();
	}
}

	private aplicacion.herramientas.java.evaluadores.Fecha Fecha=null;
	public void initialize_Fecha(){
		Fecha=new aplicacion.herramientas.java.evaluadores.Fecha(){
			public void cargar(JTextField tx){
				transfer_fecha_focus(tx);
			}
		};
		Fecha.setConstructor(this.getConstructor());
	}
	public void BuscarFecha(JTextField tx){
		Fecha.Buscar(tx);
	}
	public void BuscarFecha(){
		Fecha.Buscar(frame.get_txt_fecha());
	}
	public void BuscarFechaHasta(){
		Fecha.Buscar(frame.get_txt_fecha_hasta());
	}
	
	public void evaluarFecha(JTextField tx){
		Fecha.evaluate(tx);
	}
	

}