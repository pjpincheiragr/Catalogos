package aplicacion.gestion.cartera.logic;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import aplicacion.herramientas.java.*;
import aplicacion.gestion.cartera.gui._Frame;
import aplicacion.gestion.cartera.interfaces._Interface;
import aplicacion.gestion.cartera.logic.extensions.Egreso_Medios_de_Pago;
import aplicacion.gestion.cartera.logic.extensions.Egreso_Asiento;
import aplicacion.modelo.logic.Logic;
import aplicacion.modelo.logic.Data;


public class _Logic extends Logic{
	private _Frame frame=null;
	private _Data data=null;
	
	public _Logic(){
		Egreso_Medios_de_Pago control=new Egreso_Medios_de_Pago();
		Egreso_Asiento asiento=new Egreso_Asiento();
		this.addExtension(control);
		this.addExtension(asiento);
		System.out.println("Extension=?"+this.getExtension("Pago_Control"));
		System.out.println("Extension=?"+this.getExtension("Pago_Asiento"));
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
	}
	
	public void clean(){
		frame.get_txt_total_cheques().setText("0.00");
		frame.get_txt_fecha().setText("");
		frame.setJTableMedios(null);
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
	
	
public void cargar_cheques(){
		_Data data=(_Data)this.getData();
		String caja=frame.get_list_cajas().getSelectedItem().toString();
		Object[][] aux=data.getCheques(caja);
		if (aux!=null){
			if (aux.length>0){
			Egreso_Medios_de_Pago control=(Egreso_Medios_de_Pago)this.getExtension(_Interface._extension_medios_de_pago);
			control.create_table_medios(aux);
			double tmp=this.getTotal(aux);
			frame.get_txt_total_cheques().setText(new Convertidor().getMoney(tmp,2));
			}else {
				this.clean();
				caja=caja.replaceAll(" ", "");
				this.error("No hay cheques disponibles para la caja "+caja);
			}
		}else {
			this.clean();
			caja=caja.replaceAll(" ", "");
			this.error("No hay cheques disponibles para la caja "+caja);
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

public void setInitialDate(){
	String aux=data.getSystemDate();
	frame.get_txt_fecha().setText(aux);
}

}