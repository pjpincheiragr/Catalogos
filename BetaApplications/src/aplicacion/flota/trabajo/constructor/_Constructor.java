package aplicacion.flota.trabajo.constructor;

import aplicacion.modelo.constructor.Constructor;
import aplicacion.flota.trabajo.events.ActionListenerHandler;
import aplicacion.flota.trabajo.events.WindowAdapterHandler;
import aplicacion.flota.trabajo.events.KeyListenerHandler;
import aplicacion.flota.trabajo.gui._Frame;
import aplicacion.flota.trabajo.interfaces.*;
import aplicacion.flota.trabajo.logic._Data;
import aplicacion.flota.trabajo.logic._Logic;
import aplicacion.flota.trabajo.interfaces._Interface;
import aplicacion.flota.trabajo.events.DropTargetListenerHandler;

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
	
		frame.get_btn_buscar().setActionCommand(_Interface._btn_buscar);
		frame.get_btn_buscar_unidad().setActionCommand(_Interface._btn_buscar_unidad);
		frame.get_btn_calendario().setActionCommand(_Interface._btn_calendario);
		frame.get_btn_calendario1().setActionCommand(_Interface._btn_calendario1);
		frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		frame.get_btn_eliminar().setActionCommand(_Interface._btn_eliminar);
		frame.get_btn_guardar().setActionCommand(_Interface._btn_guardar);
		frame.get_btn_nueva().setActionCommand(_Interface._btn_nueva);
		frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		frame.get_lst_parteLetra().setActionCommand(_Interface._lst_parteLetra);
		frame.get_table_comprobantes().setName(_Interface._table_comprobantes);
		frame.get_table_mecanicos().setName(_Interface._table_mecanicos);
		frame.get_table_operaciones().setName(_Interface._table_servicio);
		frame.get_table_servicio().setName(_Interface._table_servicio);
		frame.get_txt_detalleUnidad().setActionCommand(_Interface._txt_detalleUnidad);
		frame.get_txt_dominio().setActionCommand(_Interface._txt_dominio);
		frame.get_txt_fechaActual().setActionCommand(_Interface._txt_fechaActual);
		frame.get_txt_fechaFinal().setActionCommand(_Interface._txt_fechaFinal);
		frame.get_txt_fechaInicio().setActionCommand(_Interface._txt_fechaInicio);
		frame.get_txt_idtrabajo().setActionCommand(_Interface._txt_idtrabajo);
		frame.get_txt_idunidad().setActionCommand(_Interface._txt_idunidad);
		frame.get_txt_lanzador().setActionCommand(_Interface._txt_lanzador);
		frame.get_txt_parteNumero().setActionCommand(_Interface._txt_parteNumero);
		frame.get_txt_parteSucursal().setActionCommand(_Interface._txt_parteSucursal);
		
		
		
		
	}
	
	public void init(){
		
	}
}
