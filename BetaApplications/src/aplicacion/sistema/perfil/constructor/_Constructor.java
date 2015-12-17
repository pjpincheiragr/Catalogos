package aplicacion.sistema.perfil.constructor;

import aplicacion.sistema.perfil.events.DropTargetListenerHandler;
import aplicacion.modelo.constructor.Constructor;
import aplicacion.sistema.perfil.events.ActionListenerHandler;
import aplicacion.sistema.perfil.events.WindowAdapterHandler;
import aplicacion.sistema.perfil.events.KeyListenerHandler;
import aplicacion.sistema.perfil.events.ItemListenerHandler;
import aplicacion.sistema.perfil.gui._Frame;
import aplicacion.sistema.perfil.interfaces.*;
import aplicacion.sistema.perfil.logic._Data;
import aplicacion.sistema.perfil.logic._Logic;

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
	protected void initialize_itemlistener_handler(){
		_itemlistener_handler=new ItemListenerHandler();
	}
	
	public void initialize_components(){
		_Frame frame=(_Frame) _frame;
	
		frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		frame.get_btn_guardar().setActionCommand(_Interface._btn_guardar);
		frame.get_btn_error().setActionCommand(_Interface._btn_error);
		frame.get_txt_idusuario().setName(_Interface._txt_idusuario);
		frame.get_txt_nombre().setName(_Interface._txt_nombre);
		frame.get_txt_email().setName(_Interface._txt_email);
		frame.get_txt_apellido().setName(_Interface._txt_apellido);
		frame.get_txt_telefono().setName(_Interface._txt_telefono);
		frame.get_txt_DNI().setName(_Interface._txt_DNI);
		frame.get_txt_liscencia().setName(_Interface._txt_liscencia);
		frame.get_txt_nacimiento().setName(_Interface._txt_nacimiento);
		frame.get_txt_domicilio().setName(_Interface._txt_domicilio);
		frame.get_btn_cambiarContrasenia().setActionCommand(_Interface._btn_cambiarContrasenia);
		frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		frame.get_btn_buscarImagen().setActionCommand(_Interface._btn_buscarImagen);
		frame.get_btn_eliminarFoto().setActionCommand(_Interface._btn_eliminarFoto);
		frame.get_btn_goFirst().setActionCommand(_Interface._btn_goFirst);
		frame.get_btn_anterior().setActionCommand(_Interface._btn_anterior);
		frame.get_btn_siguiente().setActionCommand(_Interface._btn_siguente);
		frame.get_btn_goLast().setActionCommand(_Interface._btn_goLast);
		frame.get_btn_zoomIn().setActionCommand(_Interface._btn_zoomIn);
		frame.get_btn_zoomOut().setActionCommand(_Interface._btn_zoomOut);
		frame.get_btn_resetFoto().setActionCommand(_Interface._btn_resetFoto);
		frame.get_btn_view().setActionCommand(_Interface._btn_view);
	}
	
	public void init(){
		super.init();
		_Logic _logic=(_Logic) this._logic;
		_logic.centrar();	
		_logic.initialize_usuario();
		_logic.initialize_dnd();
		_logic.focus();
		_logic.__initial_elegir_vendedor();	
		
		
		
		
	}
}
