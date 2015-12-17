package aplicacion.flota.provincia.constructor;

import aplicacion.modelo.constructor.Constructor;
import aplicacion.flota.provincia.events.ActionListenerHandler;
import aplicacion.flota.provincia.events.WindowAdapterHandler;
import aplicacion.flota.provincia.events.KeyListenerHandler;
import aplicacion.flota.provincia.gui._Frame;
import aplicacion.flota.provincia.interfaces.*;
import aplicacion.flota.provincia.logic._Data;
import aplicacion.flota.provincia.logic._Logic;
import aplicacion.flota.provincia.interfaces._Interface;
import aplicacion.flota.provincia.events.DropTargetListenerHandler;

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
		frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		frame.get_btn_eliminar().setActionCommand(_Interface._btn_eliminar);
		frame.get_btn_eliminarLocailidad().setActionCommand(_Interface._btn_eliminarLocailidad);
		frame.get_btn_guardar().setActionCommand(_Interface._btn_guardar);
		frame.get_btn_nueva().setActionCommand(_Interface._btn_nueva);
		frame.get_btn_nuevaLocalidad().setActionCommand(_Interface._btn_nuevaLocalidad);
		frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		frame.get_table_localidades().setName(_Interface._table_localidades);
		frame.get_txt_idprovincia().setName(_Interface._txt_idprovincia);
		frame.get_txt_nombre().setName(_Interface._txt_nombre);
		
	}
	
	public void init(){

	}
}
