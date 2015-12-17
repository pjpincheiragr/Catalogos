package aplicacion.sistema.scantool.constructor;

import aplicacion.modelo.constructor.Constructor;
import aplicacion.sistema.scantool.events.ActionListenerHandler;
import aplicacion.sistema.scantool.events.WindowAdapterHandler;
import aplicacion.sistema.scantool.events.KeyListenerHandler;
import aplicacion.sistema.scantool.events.ItemListenerHandler;
import aplicacion.sistema.scantool.gui._Frame;
import aplicacion.sistema.scantool.interfaces.*;
import aplicacion.sistema.scantool.logic._Data;
import aplicacion.sistema.scantool.logic._Logic;

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
	
	public void initialize_components(){
		_Frame frame=(_Frame) _frame;
		frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		frame.get_btn_error().setActionCommand(_Interface._btn_error);
		frame.get_txt_codigo().setName(_Interface._txt_codigo);
		
	}
	
	public void init(){
		
		
		super.init();
		/*_Logic _logic=(_Logic) this._logic;
		_Frame _frame=(_Frame) this._frame;
		_logic.centrar();
		
		_logic.setTx(_frame.get_txt_codigo());
		*/
		
	}
}
