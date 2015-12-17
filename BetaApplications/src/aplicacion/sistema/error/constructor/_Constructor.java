package aplicacion.sistema.error.constructor;

import aplicacion.sistema.error.events.ActionListenerHandler;
import aplicacion.sistema.error.events.KeyListenerHandler;
import aplicacion.modelo.constructor.Constructor;
import aplicacion.sistema.error.gui.*;
import aplicacion.sistema.error.logic.*;
import aplicacion.sistema.error.interfaces.*;
public class _Constructor extends Constructor {
	public void initialize_frame(){
		this._frame=new _Frame();
	}
	
	public void initialize_data(){
		this._data=new _Data();
	}
	
	public void initialize_logic(){
		this._logic=new _Logic();
	}
	public void initialize_actionlistener_handler(){
		_actionlistener_handler=new ActionListenerHandler();
	}
	public void initialize_keylistener_handler(){
		_keylistener_handler=new KeyListenerHandler();
	}
	public void initialize_components(){
		_Frame frame=(_Frame)this._frame;
		frame.get_btn_enviar().setActionCommand(_Interface._btn_enviar);
		frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		frame.get_btn_guardar().setActionCommand(_Interface._btn_guardar);
		frame.get_txt_asunto().setName(_Interface._txt_asunto);
	}
	
	public void init(){
		super.init();
		_Logic logic=(_Logic) this.getLogic();
		
		logic.centrar();
		logic.focus();
	}
}
