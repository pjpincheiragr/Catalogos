package aplicacion.sistema.aplicacion2.constructor;

import aplicacion.modelo.constructor.Constructor;
import aplicacion.sistema.aplicacion2.events.ActionListenerHandler;
import aplicacion.sistema.aplicacion2.events.WindowAdapterHandler;
import aplicacion.sistema.aplicacion2.events.KeyListenerHandler;
import aplicacion.sistema.aplicacion2.gui._Frame;
import aplicacion.sistema.aplicacion2.interfaces.*;
import aplicacion.sistema.aplicacion2.logic._Data;
import aplicacion.sistema.aplicacion2.logic._Logic;
import aplicacion.sistema.aplicacion2.interfaces._Interface;
import aplicacion.sistema.aplicacion2.events.DropTargetListenerHandler;

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
		frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		frame.get_btn_guardar().setActionCommand(_Interface._btn_guardar);
		frame.get_btn_eliminar().setActionCommand(_Interface._btn_eliminar);
		frame.get_btn_error().setActionCommand(_Interface._btn_error);
		frame.get_btn_nueva().setActionCommand(_Interface._btn_nueva);
		frame.get_btn_buscar_aplicacion().setActionCommand(_Interface._btn_buscar_aplicacion);
		frame.get_txt_idaplicacion().setName(_Interface._txt_idaplicacion);
		frame.get_txt_nombre().setName(_Interface._txt_nombre);
		frame.get_txt_lanzador().setName(_Interface._txt_lanzador);
		frame.get_btn_buscarImagen().setActionCommand(_Interface._btn_buscarImagen);
		frame.get_btn_eliminarFoto().setActionCommand(_Interface._btn_eliminarFoto);
		frame.get_btn_goFirst().setActionCommand(_Interface._btn_goFirst);
		frame.get_btn_anterior().setActionCommand(_Interface._btn_anterior);
		frame.get_btn_siguiente().setActionCommand(_Interface._btn_siguiente);
		frame.get_btn_goLast().setActionCommand(_Interface._btn_goLast);
		frame.get_btn_rename().setActionCommand(_Interface._btn_rename);
		frame.get_btn_probar_aplicacion().setActionCommand(_Interface._btn_probar_aplicaciones);
		frame.get_lst_area().setActionCommand(_Interface._lst_area);
	}
	
	public void init(){
		super.init();
		String idaplicacion=(String)this.getParameter(aplicacion.sistema.aplicacion.interfaces._Parametros._idaplicacion2);
		_Logic _logic=(_Logic) this._logic;
		_logic.centrar();	
		_logic.initialize_aplicacion();
		_logic.initializeJCombobox();
		_logic.initialize_dnd();
		_logic.focus();
		
		if(idaplicacion!=null && idaplicacion.compareTo("")!=0)
			_logic.cargar_parametros(idaplicacion);
		
	}
}
