package aplicacion.contabilidad.modificacion.constructor;

import aplicacion.contabilidad.modificacion.events.ActionListenerHandler;
import aplicacion.contabilidad.modificacion.events.ItemListenerHandler;
import aplicacion.contabilidad.modificacion.events.KeyListenerHandler;

import aplicacion.contabilidad.modificacion.events.WindowAdapterHandler;
import aplicacion.contabilidad.modificacion.gui._Frame;
import aplicacion.contabilidad.modificacion.logic._Data;
import aplicacion.contabilidad.modificacion.logic._Logic;
import aplicacion.modelo.constructor.Constructor;
import aplicacion.contabilidad.modificacion.interfaces._Interface;

public class _Constructor extends Constructor {

	public void initialize_frame(){
		_frame=new _Frame();
	}
	public void initialize_logic(){
		_logic=new _Logic();
	}
	
	public void initialize_data(){
		_data=new _Data();
	}
	
	public void initialize_windowadapter_handler(){
		this._windowadapter_handler=new WindowAdapterHandler();
	}
	public void initialize_actionlistener_handler(){
		this._actionlistener_handler=new ActionListenerHandler();
	}
	public void initialize_keylistener_handler(){
		this._keylistener_handler=new KeyListenerHandler();
	}
	public void initialize_itemlistener_handler(){
		this._itemlistener_handler=new ItemListenerHandler();
	}
	
	public void initialize_components(){
		System.out.println("Initialize Components!!!!");
		_Frame frame=(_Frame) _frame;
		frame.get_txt_numero().setName(_Interface._txt_numero);
		frame.get_txt_sucursal().setName(_Interface._txt_sucursal);
		frame.get_txt_idcomprobante().setName(_Interface._txt_idcomprobante);
		frame.get_txt_fecha().setName(_Interface._txt_fecha);
		frame.get_list_letra().setName(_Interface._lst_letra);
		frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		frame.get_btn_guardar().setActionCommand(_Interface._btn_guardar);
	}
	
	
	public void init(){
		super.init();
		_Logic logic=(_Logic)_logic;
		logic.init();
	}
}
