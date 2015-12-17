package aplicacion.herramientas.java.sortableselector.constructor;
import aplicacion.modelo.constructor.Constructor;
import aplicacion.herramientas.java.sortableselector.interfaces.*;
import aplicacion.herramientas.java.sortableselector.logic.*;
import aplicacion.herramientas.java.sortableselector.events.*;
import aplicacion.herramientas.java.sortableselector.gui.*;
import java.text.NumberFormat;

public class _Constructor extends Constructor{
	
	
		
	public _Constructor(){
	
	}
	
	protected void initialize_frame(){
		_frame=new _Frame();
	}
	
	protected void initialize_logic(){
		_logic=new _Logic();
		
	}
	
	public void initialize_data(){
		_data=new _Data();
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
		
		_Frame _frame=(_Frame) this._frame;
		_frame.setResizable(false);
		_frame.get_btn_error().setActionCommand(_Interface._btn_error);
		_frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		_frame.get_btn_buscar().setActionCommand(_Interface._btn_buscar);
		_frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		
	}
	
	
	
	public void init(){
		super.init();
		manager.startConnections();
		_Logic _logic=(_Logic) this._logic;
		_logic.clean();
		//_logic.init();
		//_logic.setFocus();
		
	}

	


	
}

