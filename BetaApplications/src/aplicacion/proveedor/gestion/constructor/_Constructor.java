package aplicacion.proveedor.gestion.constructor;

import aplicacion.proveedor.gestion.gui.*;
import aplicacion.proveedor.gestion.logic.*;
import aplicacion.modelo.constructor.Constructor;
import aplicacion.proveedor.gestion.interfaces._Interface;
import aplicacion.proveedor.gestion.interfaces._parametros;
import aplicacion.proveedor.gestion.events.ActionListenerHandler;
import aplicacion.proveedor.gestion.events.ItemListenerHandler;
import aplicacion.proveedor.gestion.events.MouseListenerHandler;
import aplicacion.proveedor.gestion.events.KeyListenerHandler;
import aplicacion.proveedor.gestion.events.WindowAdapterHandler;

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
		frame.get_btn_editar().setActionCommand(_Interface._btn_maestro);
		frame.get_btn_reporte().setActionCommand(_Interface._btn_reporte);
		frame.get_chk_saldos_distintos_de_cero().setName(_Interface._chk_seleccionar_saldos_distintos_de_cero);
	}
	
	public void init(){
		super.init();
		_Logic logic=(_Logic) this.getLogic();
		_logic.centrar();
		logic.goCargar();
		logic.focus();
	}
}
