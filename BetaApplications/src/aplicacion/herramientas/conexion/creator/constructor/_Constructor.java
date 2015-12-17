package aplicacion.herramientas.conexion.creator.constructor;

import aplicacion.modelo.constructor.Constructor;
import aplicacion.herramientas.conexion.creator.events.*;
import aplicacion.herramientas.conexion.creator.interfaces.*;
import aplicacion.herramientas.conexion.creator.gui.*;
import aplicacion.herramientas.conexion.creator.logic.*;

public class _Constructor extends Constructor {
	
	protected void initialize_frame(){
		_frame=new _Frame();
	}
	
	protected void initialize_logic(){
		_logic=new _Logic();
	}
	
	public void initialize_data(){
		_data=new _Data();
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
		_Frame _frame=(_Frame) this._frame;
		_frame.setResizable(false);
		_frame.get_txt_idconexion().setName(_Interface._txt_idconexion);
		_frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		_frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		_frame.get_btn_buscar().setActionCommand(_Interface._btn_buscar);
		_frame.get_btn_login().setActionCommand(_Interface._btn_login);
		_frame.get_btn_guardar().setActionCommand(_Interface._btn_guardar);
		_frame.get_btn_eliminar().setActionCommand(_Interface._btn_eliminar);
	}
	
	
	
	public void init(){
		
		
		_Logic _logic=(_Logic) this._logic;
		
		
		super.init();
		
		_logic.cargarTipos();
	}
}
