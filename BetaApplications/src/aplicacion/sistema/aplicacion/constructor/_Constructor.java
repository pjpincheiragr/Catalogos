package aplicacion.sistema.aplicacion.constructor;

import aplicacion.modelo.constructor.Constructor;
import aplicacion.sistema.aplicacion.events.ActionListenerHandler;
import aplicacion.sistema.aplicacion.events.WindowAdapterHandler;
import aplicacion.sistema.aplicacion.events.KeyListenerHandler;
import aplicacion.sistema.aplicacion.events.MouseListenerHandler;
import aplicacion.sistema.aplicacion.gui._Frame;
import aplicacion.sistema.aplicacion.interfaces.*;
import aplicacion.sistema.aplicacion.logic._Data;
import aplicacion.sistema.aplicacion.logic._Logic;

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
	protected void initialize_mouselistener_handler(){
		_mouselistener_handler=new MouseListenerHandler();
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
	
	public void initialize_components(){
		_Frame frame=(_Frame) _frame;
		frame.get_btn_cargar().setActionCommand(_Interface._btn_cargar);
		frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		frame.get_btn_error().setActionCommand(_Interface._btn_error);
		frame.get_btn_nuevo().setActionCommand(_Interface._btn_nuevo);
		frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		frame.get_btn_exportar().setActionCommand(_Interface._btn_exportar);
		frame.get_btn_importar().setActionCommand(_Interface._btn_importar);
		frame.get_txt_id().setName(_Interface._txt_id);
		
	}
	public void init(){
		super.init();
		_Logic _logic=(_Logic) this._logic;
		_logic.centrar();
		_logic.cargar_parametros();
		
	}
}
