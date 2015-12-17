package aplicacion.sistema.host.constructor;

import aplicacion.modelo.constructor.Constructor;
import aplicacion.sistema.host.events.ActionListenerHandler;
import aplicacion.sistema.host.events.WindowAdapterHandler;
import aplicacion.sistema.host.events.KeyListenerHandler;
import aplicacion.sistema.host.events.ItemListenerHandler;
import aplicacion.sistema.host.gui._Frame;
import aplicacion.sistema.host.interfaces.*;
import aplicacion.sistema.host.logic._Data;
import aplicacion.sistema.host.logic._Logic;

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
		frame.get_btn_eliminar().setActionCommand(_Interface._btn_eliminar);
		frame.get_btn_error().setActionCommand(_Interface._btn_error);
		frame.get_btn_play().setActionCommand(_Interface._btn_play);
		frame.get_btn_buscar_host().setActionCommand(_Interface._btn_buscar_host);
		frame.get_txt_idhost().setName(_Interface._txt_idhost);
		frame.get_txt_ip().setName(_Interface._txt_ip);
		frame.get_txt_extension().setName(_Interface._txt_extension);
		frame.get_chk_printer().setName(_Interface._chk_printer);
		frame.get_chk_server().setName(_Interface._chk_server);
		frame.get_rad_dhcp().setName(_Interface._rad_idhcp);
		frame.get_rad_manual().setName(_Interface._rad_manual);
	
		frame.get_btn_rename().setActionCommand(_Interface._btn_rename);
		frame.get_txt_email().setName(_Interface._txt_email);
		frame.get_txt_os().setName(_Interface._txt_os);
		frame.get_txt_macAddress().setName(_Interface._txt_macAddress);
	}
	
	public void init(){
		super.init();
		_Logic _logic=(_Logic) this._logic;
		_logic.centrar();	
		_logic.initialize_Host();
		_logic.rad();
		_logic.focus();
		_logic.block();
		
	}
}
