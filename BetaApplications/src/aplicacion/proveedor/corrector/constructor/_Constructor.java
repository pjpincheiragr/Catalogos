package aplicacion.proveedor.corrector.constructor;

import aplicacion.proveedor.corrector.gui.*;
import aplicacion.proveedor.corrector.logic.*;
import aplicacion.modelo.constructor.Constructor;
import aplicacion.proveedor.corrector.interfaces._Interface;
import aplicacion.proveedor.corrector.interfaces._parametros;
import aplicacion.proveedor.corrector.events.ActionListenerHandler;
import aplicacion.proveedor.corrector.events.ItemListenerHandler;
import aplicacion.proveedor.corrector.events.MouseListenerHandler;
import aplicacion.proveedor.corrector.events.KeyListenerHandler;
import aplicacion.proveedor.corrector.events.WindowAdapterHandler;

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
	
	protected void initialize_mouselistener_handler(){
		_mouselistener_handler=new MouseListenerHandler();
	}
	
	public void initialize_components(){
		_Frame frame=(_Frame)_frame;
		frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		frame.get_btn_error().setActionCommand(_Interface._btn_error);
		frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		frame.get_btn_cargar().setActionCommand(_Interface._btn_cargar);
		frame.get_txt_idproveedor().setName(_Interface._txt_idproveedor);
		frame.get_chk_seleccionar_creditos().setName(_Interface._chk_seleccionar_creditos);
		frame.get_chk_seleccionar_deudas().setName(_Interface._chk_seleccionar_deudas);
		frame.get_chk_seleccionar_anticipos().setName(_Interface._chk_seleccionar_anticipos);
		frame.get_btn_seleccionar_pendientes().setActionCommand(_Interface._btn_seleccionar_pendientes);
	}
	
	public void init(){
		super.init();
		_Logic logic=(_Logic) this.getLogic();
		logic.initialize_proveedor();
		_Frame frame=(_Frame) this.getFrame();
		logic.centrar();
	
		logic.focus();
		String idcliente="";
		try {
			idcliente=(String)this.getParameter(_parametros.idproveedor);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (idcliente.compareTo("")!=0){
			frame.get_txt_idproveedor().setText(idcliente);
			logic.evaluarProveedor(frame.get_txt_idproveedor());
		}
	}
}
