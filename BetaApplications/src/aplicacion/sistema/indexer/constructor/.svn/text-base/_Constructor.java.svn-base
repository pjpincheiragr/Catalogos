package aplicacion.sistema.indexer.constructor;

import aplicacion.modelo.constructor.Constructor;
import aplicacion.sistema.indexer.events.ActionListenerHandler;
import aplicacion.sistema.indexer.events.WindowAdapterHandler;
import aplicacion.sistema.indexer.events.KeyListenerHandler;
import aplicacion.sistema.indexer.events.ItemListenerHandler;
import aplicacion.sistema.indexer.gui._Frame;
import aplicacion.sistema.indexer.interfaces.*;
import aplicacion.sistema.indexer.logic._Data;
import aplicacion.sistema.indexer.logic._Logic;

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
		frame.get_btn_cargar().setActionCommand(_Interface._btn_cargar);
		frame.get_btn_indexar().setActionCommand(_Interface._btn_indexar);
		frame.get_btn_eliminar().setActionCommand(_Interface._btn_eliminar);
		frame.get_btn_quitar_todos().setActionCommand(_Interface._btn_quitar_todos);
		frame.get_btn_buscar_archivo().setActionCommand(_Interface._btn_abrir_archivo);
		frame.get_txt_archivo().setName(_Interface._txt_archivo);
		frame.get_chk_seleccionar().setName(_Interface._chk_seleccionar);
	}
	
	public void init(){
		super.init();
		_Logic _logic=(_Logic) this._logic;
		
		_logic.initialize_proveedor();
		_logic.centrar();
		_logic.focus();
	}
}
