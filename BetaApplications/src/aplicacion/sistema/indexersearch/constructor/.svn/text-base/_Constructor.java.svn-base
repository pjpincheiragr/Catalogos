package aplicacion.sistema.indexersearch.constructor;

import aplicacion.modelo.constructor.Constructor;
import aplicacion.sistema.indexersearch.events.ActionListenerHandler;
import aplicacion.sistema.indexersearch.events.WindowAdapterHandler;
import aplicacion.sistema.indexersearch.events.KeyListenerHandler;
import aplicacion.sistema.indexersearch.events.ItemListenerHandler;
import aplicacion.sistema.indexersearch.events.MouseListenerHandler;
import aplicacion.sistema.indexersearch.gui._Frame;
import aplicacion.sistema.indexersearch.interfaces.*;
import aplicacion.sistema.indexersearch.logic._Data;
import aplicacion.sistema.indexersearch.logic._Logic;

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
	
	protected void initialize_itemlistener_handler(){
		_itemlistener_handler=new ItemListenerHandler();
	}
	protected void initialize_mouselistener_handler(){
		_mouselistener_handler=new MouseListenerHandler();
	}
	public void initialize_components(){
		_Frame frame=(_Frame) _frame;
		
		frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		frame.get_btn_buscar().setActionCommand(_Interface._btn_buscar);
		frame.get_txt_busqueda().setName(_Interface._txt_buscar);
		
		
	}
	
	public void init(){
		super.init();
		_Logic _logic=(_Logic) this._logic;
		_logic.centrar();	
		_logic.focus();
	}
}
