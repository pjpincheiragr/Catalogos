package aplicacion.flota.modelo.constructor;

import aplicacion.modelo.constructor.Constructor;
import aplicacion.flota.modelo.events.ActionListenerHandler;
import aplicacion.flota.modelo.events.WindowAdapterHandler;
import aplicacion.flota.modelo.events.KeyListenerHandler;
import aplicacion.flota.modelo.gui._Frame;
import aplicacion.flota.modelo.interfaces.*;
import aplicacion.flota.modelo.logic._Data;
import aplicacion.flota.modelo.logic._Logic;
import aplicacion.flota.modelo.interfaces._Interface;
import aplicacion.flota.modelo.events.DropTargetListenerHandler;

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
	protected void initialize_droptargetlistener_handler(){
		_droptargetlistener_handler=new DropTargetListenerHandler();
	}
	
	/*protected void initialize_itemlistener_handler(){
		_itemlistener_handler=new ItemListenerHandler();
		
	}*/
	
	public void initialize_components(){
		_Frame frame=(_Frame) _frame;
	
		frame.get_btn_anterior().setActionCommand(_Interface._btn_anterior);
		frame.get_btn_buscar().setActionCommand(_Interface._btn_buscar);
		frame.get_btn_buscarImagen().setActionCommand(_Interface._btn_buscarImagen);
		frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		frame.get_btn_eliminar().setActionCommand(_Interface._btn_eliminar);
		frame.get_btn_eliminarChequeo().setActionCommand(_Interface._btn_eliminarChequeo);
		frame.get_btn_eliminarEquipamiento().setActionCommand(_Interface._btn_eliminarEquipamiento);
		frame.get_btn_eliminarFoto().setActionCommand(_Interface._btn_eliminarFoto);
		frame.get_btn_eliminarMantenimiento().setActionCommand(_Interface._btn_eliminarMantenimiento);
		frame.get_btn_goFirst().setActionCommand(_Interface._btn_goFirst);
		frame.get_btn_goLast().setActionCommand(_Interface._btn_goLast);
		frame.get_btn_guardar().setActionCommand(_Interface._btn_guardar);
		frame.get_btn_nuevoChequeo().setActionCommand(_Interface._btn_nuevoChequeo);
		frame.get_btn_nuevoEquipamiento().setActionCommand(_Interface._btn_nuevoEquipamiento);
		frame.get_btn_nuevoMantenimiento().setActionCommand(_Interface._btn_nuevoMantenimiento);
		frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		frame.get_btn_siguiente().setActionCommand(_Interface._btn_siguiente);
		frame.get_table_chequeo().setName(_Interface._table_chequeo);
		frame.get_table_equipamiento().setName(_Interface._table_equipamiento);
		frame.get_table_mantenimiento().setName(_Interface._table_mantenimiento);
		frame.get_txt_marca().setActionCommand(_Interface._txt_marca);
		frame.get_txt_modelo().setActionCommand(_Interface._txt_modelo);
		frame.get_txt_version().setActionCommand(_Interface._txt_version);
		
		
		
	}
	
	public void init(){
		
		super.init();
		_Logic _logic=(_Logic) this._logic;
		_logic.focus(); 
		_logic.initialize_dnd();
		this._frame.setVisible(true);
	}
}
