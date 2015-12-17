package aplicacion.actualizacion.fotos.constructor;

import aplicacion.actualizacion.fotos.events.ActionListenerHandler;
import aplicacion.actualizacion.fotos.events.ItemListenerHandler;
import aplicacion.actualizacion.fotos.events.KeyListenerHandler;
import aplicacion.actualizacion.fotos.events.WindowAdapterHandler;
import aplicacion.actualizacion.fotos.gui._Frame;
import aplicacion.actualizacion.fotos.logic._Data;
import aplicacion.actualizacion.fotos.logic._Logic;
import aplicacion.actualizacion.fotos.interfaces.*;

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
		_Frame frame=(_Frame)_frame;
		frame.get_btn_buscar_archivo().setActionCommand(_Interface._btn_buscar_archivo);
		frame.get_btn_cargar_archivo().setActionCommand(_Interface._btn_cargar_archivo);
		frame.get_btn_columnas().setActionCommand(_Interface._btn_columnas);
		frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		frame.get_btn_error().setActionCommand(_Interface._btn_error);
		frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		frame.get_btn_buscar_proveedor().setActionCommand(_Interface._btn_buscar_proveedor);
		frame.get_btn_actualizar().setActionCommand(_Interface._btn_actualizar);
		frame.get_txt_idproveedor().setName(_Interface._txt_idproveedor);
		frame.get_txt_politica().setName(_Interface._txt_idpolitica);
		frame.get_btn_buscar_politica().setActionCommand(_Interface._btn_buscar_politica);
		frame.get_btn_editar_politica().setActionCommand(_Interface._btn_editar_politica);
		frame.get_btn_nueva_politica().setActionCommand(_Interface._btn_nueva_politica);
		frame.get_txt_archivo().setName(_Interface._txt_archivo);
		frame.get_txt_linea().setName(_Interface._txt_linea);
	}
	
	public void init(){
		super.init();
		_Logic logic=(_Logic) this._logic;
		logic.initialize_proveedor();
		logic.focus();
	}
}
