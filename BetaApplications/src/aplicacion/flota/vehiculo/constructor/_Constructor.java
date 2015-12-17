package aplicacion.flota.vehiculo.constructor;

import aplicacion.modelo.constructor.Constructor;
import aplicacion.flota.vehiculo.events.ActionListenerHandler;
import aplicacion.flota.vehiculo.events.WindowAdapterHandler;
import aplicacion.flota.vehiculo.events.KeyListenerHandler;
import aplicacion.flota.vehiculo.gui._Frame;
import aplicacion.flota.vehiculo.interfaces.*;
import aplicacion.flota.vehiculo.logic._Data;
import aplicacion.flota.vehiculo.logic._Logic;
import aplicacion.flota.vehiculo.interfaces._Interface;
import aplicacion.flota.vehiculo.events.DropTargetListenerHandler;

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
		frame.get_btn_calendario().setActionCommand(_Interface._btn_calendario);
		frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		frame.get_btn_eliminar().setActionCommand(_Interface._btn_eliminar);
		frame.get_btn_eliminarAuxilio().setActionCommand(_Interface._btn_eliminarAuxilio);
		frame.get_btn_eliminarEquipamiento().setActionCommand(_Interface._btn_eliminarEquipamiento);
		frame.get_btn_eliminarNovedad().setActionCommand(_Interface._btn_eliminarNovedad);
		frame.get_btn_eliminarPlanificacion().setActionCommand(_Interface._btn_eliminarPlanificacion);
		frame.get_btn_eliminarTarjeta().setActionCommand(_Interface._btn_eliminarTarjeta);
		frame.get_btn_eliminarTrabajo().setActionCommand(_Interface._btn_eliminarTrabajo);
		frame.get_btn_guardar().setActionCommand(_Interface._btn_guardar);
		frame.get_btn_nuevaNovedad().setActionCommand(_Interface._btn_nuevaNovedad);
		frame.get_btn_nuevaPlanificacion().setActionCommand(_Interface._btn_nuevaPlanificacion);
		frame.get_btn_nuevaTarjeta().setActionCommand(_Interface._btn_nuevaTarjeta);
		frame.get_btn_nuevoAuxilio().setActionCommand(_Interface._btn_nuevoEquipamiento);
		frame.get_btn_nuevoTrabajo().setActionCommand(_Interface._btn_nuevoTrabajo);
		frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		frame.get_lst_marca().setActionCommand(_Interface._lst_marca);
		frame.get_lst_modelo().setActionCommand(_Interface._lst_modelo);
		frame.get_lst_version().setActionCommand(_Interface._lst_version);
		frame.get_table_auxilio().setName(_Interface._table_auxilio);
		frame.get_table_equipamiento().setName(_Interface._table_equipamiento);
		frame.get_table_novedad().setName(_Interface._table_novedad);
		frame.get_table_planificacion().setName(_Interface._table_planificacion);
		frame.get_table_tarjetas().setName(_Interface._table_tarjetas);
		frame.get_table_trabajo().setName(_Interface._table_trabajo);
	}
	
	public void init(){
		super.init();
	}
}
