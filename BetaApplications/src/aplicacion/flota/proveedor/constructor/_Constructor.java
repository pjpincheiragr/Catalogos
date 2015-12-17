package aplicacion.flota.proveedor.constructor;

import aplicacion.modelo.constructor.Constructor;
import aplicacion.flota.proveedor.events.ActionListenerHandler;
import aplicacion.flota.proveedor.events.WindowAdapterHandler;
import aplicacion.flota.proveedor.events.KeyListenerHandler;
import aplicacion.flota.proveedor.gui._Frame;
import aplicacion.flota.proveedor.interfaces.*;
import aplicacion.flota.proveedor.logic._Data;
import aplicacion.flota.proveedor.logic._Logic;
import aplicacion.flota.proveedor.interfaces._Interface;
import aplicacion.flota.proveedor.events.DropTargetListenerHandler;

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

		frame.get_btn_buscar().setActionCommand(_Interface._btn_buscar);
		frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		frame.get_btn_eliminar().setActionCommand(_Interface._btn_eliminar);
		frame.get_btn_guardar().setActionCommand(_Interface._btn_guardar);
		frame.get_btn_nuevo().setActionCommand(_Interface._btn_nuevo);
		frame.get_btn_rename().setActionCommand(_Interface._btn_rename);
		frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		frame.get_txt_cuit().setName(_Interface._txt_cuit);
		frame.get_txt_idproveedor().setName(_Interface._txt_idproveedor);
		frame.get_txt_telefono().setName(_Interface._txt_telefono);
		
	}
	
	public void init(){
		super.init();
		
	}
}
