package aplicacion.sistema.query.constructor;

import aplicacion.modelo.constructor.Constructor;
import aplicacion.sistema.query.events.ActionListenerHandler;
import aplicacion.sistema.query.events.WindowAdapterHandler;
import aplicacion.sistema.query.events.KeyListenerHandler;
import aplicacion.sistema.query.events.ItemListenerHandler;
import aplicacion.sistema.query.gui._Frame;
import aplicacion.sistema.query.interfaces.*;
import aplicacion.sistema.query.logic._Data;
import aplicacion.sistema.query.logic._Logic;

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
		frame.get_btn_cargar().setActionCommand(_Interface._btn_cargar);
		frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		frame.get_btn_guardar().setActionCommand(_Interface._btn_guardar);
		frame.get_btn_aplicaciones().setActionCommand(_Interface._btn_aplicaciones);
		frame.get_btn_eliminar().setActionCommand(_Interface._btn_eliminar);
		frame.get_btn_aplicaciones().setActionCommand(_Interface._btn_aplicaciones);
		frame.get_btn_error().setActionCommand(_Interface._btn_error);
		frame.get_btn_play().setActionCommand(_Interface._btn_play);
		frame.get_btn_buscar_usuario().setActionCommand(_Interface._btn_buscar_usuario);
		frame.get_txt_idquery().setName(_Interface._txt_idquery);
		frame.get_txt_idproveedor().setName(_Interface._txt_nombre);
		frame.get_txt_odbc().setName(_Interface._txt_odbc);
		frame.get_txt_tabla().setName(_Interface._txt_tabla);
	}
	
	public void init(){
		super.init();
		_Logic _logic=(_Logic) this._logic;
		_logic.centrar();
		_logic.initialize_Query();
		_logic.focus();
		
	}
}
