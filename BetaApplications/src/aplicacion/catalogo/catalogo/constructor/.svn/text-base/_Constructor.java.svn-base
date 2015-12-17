package aplicacion.catalogo.catalogo.constructor;

import aplicacion.catalogo.catalogo.interfaces._Interface;
import aplicacion.catalogo.catalogo.events.ActionListenerHandler;
import aplicacion.catalogo.catalogo.events.KeyListenerHandler;
import aplicacion.catalogo.catalogo.gui._Frame;
import aplicacion.catalogo.catalogo.logic._Data;
import aplicacion.catalogo.catalogo.logic._Logic;
import aplicacion.modelo.constructor.Constructor;

public class _Constructor extends Constructor {

	public void initialize_frame(){
		_frame=new _Frame();
	}
	
	public void initialize_data(){
		_data=new _Data();
	}
	
	public void initialize_logic(){
		_logic=new _Logic();
	}
	public void initialize_actionlistener_handler(){
		_actionlistener_handler=new ActionListenerHandler();
	}
	
	public void initialize_keylistener_handler(){
		_keylistener_handler=new KeyListenerHandler();
	}
	
	public void initialize_components(){
		_Frame frame=(_Frame) this._frame;
		frame.get_btn_eliminar().setActionCommand(_Interface._btn_eliminar);
		frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
	}
	
	
	public void init(){
		super.init();
		_Logic logic=(_Logic) this.getLogic();
		logic.init();
		logic.load_variables();
		logic.load_Options();
	}
	
}
