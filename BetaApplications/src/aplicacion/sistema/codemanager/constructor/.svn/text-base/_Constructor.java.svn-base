package aplicacion.sistema.codemanager.constructor;

import aplicacion.sistema.codemanager.events.ActionListenerHandler;
import aplicacion.sistema.codemanager.events.TreeModelListenerHandler;
import aplicacion.sistema.codemanager.events.KeyListenerHandler;
import aplicacion.sistema.codemanager.gui._Frame;
import aplicacion.sistema.codemanager.interfaces._Interface;
import aplicacion.sistema.codemanager.logic._Data;
import aplicacion.sistema.codemanager.logic._Logic;
import aplicacion.modelo.constructor.Constructor;

public class _Constructor extends Constructor {

	public void initialize_frame(){
		_frame=new _Frame();
	}
	
	public void initialize_data(){
		_data=new _Data();
	}
	
	public void initialize_logic(){
		_logic=new _Logic();
	}
	public void initialize_actionlistener_handler(){
		_actionlistener_handler=new ActionListenerHandler();
	}
	
	public void initialize_keylistener_handler(){
		_keylistener_handler=new KeyListenerHandler();
	}
	
	public void initialize_treemodellistener_handler(){
		_treemodellistener_handler=new TreeModelListenerHandler();
	}
	
	public void initialize_components(){
		_Frame frame=(_Frame) this._frame;
		frame.get_btn_agregar().setActionCommand(_Interface._btn_agregar);
		frame.get_btn_borrar().setActionCommand(_Interface._btn_borrar);
		frame.get_btn_edit().setActionCommand(_Interface._btn_editar);
		frame.get_txt_info().setName(_Interface._table_tecnica_dato);
	}
}
