package aplicacion.herramientas.java.ejemplo.constructor;

import aplicacion.herramientas.java.ejemplo.interfaces._Interface;
import aplicacion.herramientas.java.ejemplo.events.ActionListenerHandler;
import aplicacion.herramientas.java.ejemplo.gui._Frame;
import aplicacion.herramientas.java.ejemplo.logic._Logic;
import aplicacion.modelo.constructor.Constructor;
import aplicacion.modelo.logic.Data;

public class _Constructor extends Constructor {
	protected void initialize_frame(){
		_frame=new _Frame();
	}
	
	protected void initialize_logic(){
		_logic=new _Logic();
		
	}
	
	
	protected void initialize_actionlistener_handler(){
		_actionlistener_handler=new ActionListenerHandler();
	}
	
	public void initialize_components(){
		
		_Frame frame=(_Frame) this._frame;
		
		frame.setResizable(false);
		frame.get_btn_empezar().setActionCommand(_Interface._btn_empezar);
		frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		frame.setVisible(true);
	}
}
