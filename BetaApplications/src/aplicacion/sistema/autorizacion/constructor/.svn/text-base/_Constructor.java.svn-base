package aplicacion.sistema.autorizacion.constructor;

import aplicacion.modelo.constructor.Constructor;
import aplicacion.sistema.autorizacion.events.ActionListenerHandler;
import aplicacion.sistema.autorizacion.events.WindowAdapterHandler;
import aplicacion.sistema.autorizacion.events.KeyListenerHandler;
import aplicacion.sistema.autorizacion.events.ItemListenerHandler;
import aplicacion.sistema.autorizacion.gui._Frame;
import aplicacion.sistema.autorizacion.interfaces.*;
import aplicacion.sistema.autorizacion.logic._Data;
import aplicacion.sistema.autorizacion.logic._Logic;

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
		frame.get_txt_usuario().setName(_Interface._txt_idusuario);
	}
	
	public void init(){
		super.init();
		_Logic _logic=(_Logic) this._logic;
		_logic.centrar();
		String iduser="";
		try {
			iduser=this.getParameter(aplicacion.sistema.autorizacion.interfaces._parametros._iduser).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (iduser.compareTo("")!=0){
			_logic.cargar_parametros(iduser);	
		}else{
			_logic.focus();
		}
		
	}
}
