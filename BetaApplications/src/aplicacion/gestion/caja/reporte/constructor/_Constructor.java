/**
 * Aplicacion Para verificar los cheques en cuenta en la caja seleccionada
 * Autor: Agustin Wisky
 * ultima modificacion: 02-09-2009
 * version: 1
 * 
 */
package aplicacion.gestion.caja.reporte.constructor;

import aplicacion.modelo.constructor.Constructor;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.gestion.caja.reporte.events.*;
import aplicacion.gestion.caja.reporte.gui.*;
import aplicacion.gestion.caja.reporte.logic.*;
import aplicacion.gestion.caja.reporte.interfaces.*;


public class _Constructor extends Constructor{
		
	public _Constructor(){
	
	}
	
	protected void initialize_frame(){
		_frame=new _Frame();
	}
	
	protected void initialize_logic(){
		_logic=new _Logic();
		
	}
	
	public void initialize_data(){
		_data=new _Data();
	}
	
	protected void initialize_actionlistener_handler(){
		_actionlistener_handler=new ActionListenerHandler();
	}
	
	protected void initialize_windowadapter_handler(){
		_windowadapter_handler=new WindowAdapterHandler();
	}
	
	protected void initialize_keylistener_handler(){
		_keylistener_handler=new KeyListenerHandler();
	}
	
	protected void initialize_itemlistener_handler(){
		_itemlistener_handler=new ItemListenerHandler();
	}
	
	public void initialize_components(){
		_Frame _frame=(_Frame) this._frame;
		_frame.setResizable(false);
		_frame.get_list_cajas().setName(_Interface._list_cajas);
		_frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		_frame.get_btn_mostrar().setActionCommand(_Interface._btn_mostrar);
		_frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		_frame.get_btn_error().setActionCommand(_Interface._btn_error);
		_frame.get_btn_reporte().setActionCommand(_Interface._btn_reporte);
		_frame.get_txt_fecha().setName(_Interface._txt_fecha);
		_frame.get_txt_fecha_hasta().setName(_Interface._txt_fecha_hasta);
		_frame.get_btn_fecha_desde().setActionCommand(_Interface._btn_fecha);
		_frame.get_btn_fecha_hasta().setActionCommand(_Interface._btn_fecha_hasta);
	}
	
	
	
	public void init(){
		
		super.init();
		_Frame _frame=(_Frame) this._frame;
		_Logic _logic=(_Logic) this._logic;
		String idcaja=(String)this.getValueParameter(_Parametros.caja);
		String cuenta=(String)this.getValueParameter(_Parametros.cuenta);
		_logic.centrar();
		
		_logic.initialize_Fecha();
		_logic.cargar_cajas();
		
		if (idcaja.compareTo("")!=0){
			_logic.setCaja(idcaja);
			
		}
		
	}

	


	
}

