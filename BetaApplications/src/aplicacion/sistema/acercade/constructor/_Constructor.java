package aplicacion.sistema.acercade.constructor;

import aplicacion.modelo.constructor.Constructor;

import aplicacion.sistema.acercade.events.MouseListenerHandler;
import aplicacion.sistema.acercade.events.WindowAdapterHandler;
import aplicacion.sistema.acercade.gui._Frame;
import aplicacion.sistema.acercade.interfaces.*;
import aplicacion.sistema.acercade.logic._Data;
import aplicacion.sistema.acercade.logic._Logic;

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
	
	protected void initialize_windowlistener_handler(){
		_windowadapter_handler=new WindowAdapterHandler();
	}
	
	
	public void initialize_components(){
		_Frame frame=(_Frame) _frame;
		
	}
	public void init(){
		super.init();
		_Logic _logic=(_Logic) this._logic;
		_logic.centrar();
		_logic.init();
		_logic.createScreenImage();
		_logic.run();
		_logic.start();
		_logic.disturb(200,200);
		
		
    	
	}
}
