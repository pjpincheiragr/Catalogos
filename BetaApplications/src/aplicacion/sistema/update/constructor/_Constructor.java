package aplicacion.sistema.update.constructor;

import aplicacion.sistema.update.events.ActionListenerHandler;
import aplicacion.sistema.update.events.ItemListenerHandler;
import aplicacion.sistema.update.events.KeyListenerHandler;
import aplicacion.sistema.update.events.WindowAdapterHandler;
import aplicacion.sistema.update.gui._Frame;
import aplicacion.sistema.update.logic._Data;
import aplicacion.sistema.update.logic._Logic;
import aplicacion.sistema.update.interfaces.*;

import aplicacion.sistema.update.events.TreeModelListenerHandler;
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
	public void initialize_treemodellistener_handler(){
		_treemodellistener_handler=new TreeModelListenerHandler();
	}
	
	
	protected void initialize_itemlistener_handler(){
		_itemlistener_handler=new ItemListenerHandler();
		
	}
	
	public void initialize_components(){
		_Frame frame=(_Frame)_frame;
		
		frame.get_btn_cargar().setActionCommand(_Interface._btn_cargar);
		frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		frame.get_btn_error().setActionCommand(_Interface._btn_error);
		frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		frame.get_btn_nuevo().setActionCommand(_Interface._btn_nuevo);
		frame.get_txt_idcomprobante().setName(_Interface._txt_idcomprobante);
		
	}
	
	public void init(){
		super.init();
		_Logic logic=(_Logic) this._logic;
		_Frame frame=(_Frame)_frame;
		String idproveedor="";
		try {
			idproveedor=(String) this.getParameter(_parametros._idproveedor);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logic.centrar();
		logic.load_variables();
		logic.nuevo();
	
		logic.focus();
	}
}
