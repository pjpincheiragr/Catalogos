package aplicacion.flota.chofer.constructor;

import aplicacion.modelo.constructor.Constructor;
import aplicacion.flota.chofer.events.ActionListenerHandler;
import aplicacion.flota.chofer.events.WindowAdapterHandler;
import aplicacion.flota.chofer.events.KeyListenerHandler;
import aplicacion.flota.chofer.gui._Frame;
import aplicacion.flota.chofer.interfaces.*;
import aplicacion.flota.chofer.logic._Data;
import aplicacion.flota.chofer.logic._Logic;
import aplicacion.flota.chofer.interfaces._Interface;
import aplicacion.flota.chofer.events.DropTargetListenerHandler;

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
		frame.get_btn_guardar().setActionCommand(_Interface._btn_nuevo);
		frame.get_btn_nuevo().setActionCommand(_Interface._btn_nuevo);
		frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		frame.get_txt_DNI().setName(_Interface._txt_DNI);
		frame.get_txt_apellido().setName(_Interface._txt_apellido);
		frame.get_txt_idchofer().setName(_Interface._txt_idchofer);
		frame.get_txt_licencia().setName(_Interface._txt_licencia);
		frame.get_txt_nombre().setName(_Interface._txt_nombre);
		frame.get_txt_vencimiento().setName(_Interface._txt_vencimiento);
		
	}
	
	public void init(){
	super.init();
	}
}
