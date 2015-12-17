package aplicacion.sistema.version.constructor;

import aplicacion.modelo.constructor.Constructor;
import aplicacion.sistema.version.gui.*;
import aplicacion.sistema.version.logic.*;
import aplicacion.sistema.version.events.*;
import aplicacion.sistema.version.interfaces.*;

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

	public void initialize_components(){
		_Frame frame=(_Frame) _frame;
		frame.get_btn_descargar().setActionCommand(_Interface._btn_actualizar);
		frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		frame.get_btn_error().setActionCommand(_Interface._btn_error);
		frame.get_btn_buscar_version().setActionCommand(_Interface._btn_buscar_version);
		frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
	}
	
	
	public void init(){
		
		this.setShowOnStartup(false);
		super.init();
		_Logic logic=(_Logic) _logic;
		logic.centrar();
		logic.cargar();
		
		
	}
}
