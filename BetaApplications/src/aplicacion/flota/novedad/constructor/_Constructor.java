package aplicacion.flota.novedad.constructor;

import aplicacion.modelo.constructor.Constructor;
import aplicacion.flota.novedad.events.ActionListenerHandler;
import aplicacion.flota.novedad.events.WindowAdapterHandler;
import aplicacion.flota.novedad.events.KeyListenerHandler;
import aplicacion.flota.novedad.gui._Frame;
import aplicacion.flota.novedad.logic._Data;
import aplicacion.flota.novedad.logic._Logic;
import aplicacion.flota.novedad.interfaces._Interface;
import aplicacion.flota.novedad.events.DropTargetListenerHandler;

public class _Constructor extends Constructor {
	protected void initialize_frame(){
		_frame=new _Frame();
		
	}
	protected void initialize_data(){
		_data=new _Data();
	}
	protected void initialize_logic(){
		_logic=new _Logic();
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
	protected void initialize_droptargetlistener_handler(){
		_droptargetlistener_handler=new DropTargetListenerHandler();
	}
	
	/*protected void initialize_itemlistener_handler(){
		_itemlistener_handler=new ItemListenerHandler();
		
	}*/
	
	public void initialize_components(){
		_Frame frame=(_Frame) _frame;

		frame.get_btn_buscar_aplicacion().setActionCommand(_Interface._btn_buscar_aplicacion);
		frame.get_btn_buscar_chofer().setActionCommand(_Interface._btn_buscar_chofer);
		frame.get_btn_buscar_unidad().setActionCommand(_Interface._btn_buscar_unidad);
		frame.get_btn_calendario().setActionCommand(_Interface._btn_calendario);
		frame.get_btn_calendario1().setActionCommand(_Interface._btn_calendario1);
		frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		frame.get_btn_eliminar().setActionCommand(_Interface._btn_eliminar);
		frame.get_btn_guardar().setActionCommand(_Interface._btn_guardar);
		frame.get_btn_nueva().setActionCommand(_Interface._btn_nueva);
		frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		frame.get_lst_combustibleEstacion().setActionCommand(_Interface._lst_combustibleEstacion);
		frame.get_lst_combustibleGarage().setActionCommand(_Interface._lst_combustibleGarage);
		frame.get_lst_letra().setActionCommand(_Interface._lst_letra);
		frame.get_lst_tarjeta().setActionCommand(_Interface._lst_tarjeta);
		frame.get_table_equipamiento().setName(_Interface._table_equipamiento);
		frame.get_table_prechequeo().setName(_Interface._table_prechequeo);
		frame.get_txt_observasiones().setName(_Interface._txt_observasiones);
		frame.get_txt_chofer().setActionCommand(_Interface._txt_chofer);
		frame.get_txt_combustibleEstacion().setActionCommand(_Interface._txt_combustibleEstacion);
		frame.get_txt_detalleUnidad().setActionCommand(_Interface._txt_detalleUnidad);
		frame.get_txt_dominio().setActionCommand(_Interface._txt_fechaFinalizacion);
		frame.get_txt_fechaFinalizacion().setActionCommand(_Interface._txt_fechaFinalizacion);
		frame.get_txt_fechaInicio().setActionCommand(_Interface._txt_fechaInicio);
		frame.get_txt_idnovedad().setActionCommand(_Interface._txt_idnovedad);
		frame.get_txt_idunidad().setActionCommand(_Interface._txt_idunidad);
		frame.get_txt_nombreChofer().setActionCommand(_Interface._txt_nombreChofer);
		frame.get_txt_numero().setActionCommand(_Interface._txt_numero);
		frame.get_txt_numeroTarjeta().setActionCommand(_Interface._txt_numeroTarjeta);
		frame.get_txt_sucursal().setActionCommand(_Interface._txt_sucursal);
		
		
	}
	
	public void init(){
		
	
	}
	
}
