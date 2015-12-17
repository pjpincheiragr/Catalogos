package aplicacion.herramientas.java.ftp.constructor;

import aplicacion.modelo.constructor.Constructor;
import aplicacion.herramientas.java.ftp.events.ActionListenerHandler;

import aplicacion.herramientas.java.ftp.events.KeyListenerHandler;
import aplicacion.herramientas.java.ftp.gui._Frame;
import aplicacion.herramientas.java.ftp.interfaces.*;
import aplicacion.herramientas.java.ftp.logic._Data;
import aplicacion.herramientas.java.ftp.logic._Logic;

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
	
	//protected void initialize_windowadapter_handler(){
	//	_windowadapter_handler=new WindowAdapterHandler();
	//}

	protected void initialize_keylistener_handler(){
		_keylistener_handler=new KeyListenerHandler();
	}
	
	
	public void initialize_components(){
		_Frame frame=(_Frame) _frame;
		
		frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		frame.get_txt_archivo().setName(_Interface._txt_archivo);
	}
	
	public void init(){
		super.init();
		_Logic _logic=(_Logic) this._logic;
		_logic.centrar();
		_logic.load_variables();
		
			_logic.focus();
		
	}
}
