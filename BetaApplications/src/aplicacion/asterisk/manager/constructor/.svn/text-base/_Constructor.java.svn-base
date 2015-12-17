package aplicacion.asterisk.manager.constructor;

import javax.swing.JOptionPane;

import aplicacion.asterisk.manager.gui._Frame;
import aplicacion.asterisk.manager.logic._Data;
import aplicacion.asterisk.manager.logic._Logic;
import aplicacion.asterisk.manager.events.ActionListenerHandler;
import aplicacion.asterisk.manager.events.MouseListenerHandler;
import aplicacion.asterisk.manager.events.KeyListenerHandler;
import aplicacion.asterisk.manager.events.WindowAdapterHandler;
import aplicacion.asterisk.manager.interfaces._Interface;
import aplicacion.modelo.constructor.Constructor;

public class _Constructor extends Constructor {

	protected void initialize_frame(){
		_frame=new _Frame();
	}
	
	
	public void initialize_components(){
		_Frame frame=(_Frame)_frame;
		frame.get_btn_minimizar().setActionCommand(_Interface._btn_minimizar);
		frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		frame.get_btn_ayuda().setActionCommand(_Interface._btn_ayuda);
		frame.get_btn_maximizar().setActionCommand(_Interface._btn_maximizar);
		frame.get_btn_reconnectar().setActionCommand(_Interface._btn_reconectar);
		
	}
	protected void initialize_logic(){
		_logic=new _Logic();
	}
	
	protected void initialize_actionlistener_handler(){
		_actionlistener_handler=new ActionListenerHandler();
	}
	
	protected void initialize_mouselistener_handler(){
		_mouselistener_handler=new MouseListenerHandler();
	}
	protected void initialize_keylistener_handler(){
		_keylistener_handler=new KeyListenerHandler();
	}
	protected void initialize_windowadapter_handler(){
		_windowadapter_handler=new WindowAdapterHandler();
	}
	public void initialize_data(){
		_data=new _Data();
	}
	
	public void init(){
		this.setShowOnStartup(false);
		this.setMaster(true);
		super.init();
		//394-rtvxr5
		_Logic logic=(_Logic)this.getLogic();
		
		logic.check_running();
		
		
		
	}
}
