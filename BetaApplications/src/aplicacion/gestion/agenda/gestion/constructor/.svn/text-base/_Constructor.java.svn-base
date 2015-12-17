/**
 * Aplicacion de Agenda
 * Autor: Agustin Wisky
 * ultima modificacion: 05-07-2010
 * version: 1
 * 
 */
package aplicacion.gestion.agenda.gestion.constructor;

import aplicacion.modelo.constructor.Constructor;
import aplicacion.modelo.interfaces._parametros;


import aplicacion.gestion.agenda.gestion.events.*;
import aplicacion.gestion.agenda.gestion.gui.*;
import aplicacion.gestion.agenda.gestion.interfaces._Interface;
import aplicacion.gestion.agenda.gestion.logic.*;


public class _Constructor extends Constructor {
		
	public _Constructor(){
	
	}
	
	protected void initialize_frame(){
		_frame=new _Frame();
	}
	
	protected void initialize_logic(){
		_logic=new _Logic();
		
	}
	
	public void initialize_data(){
		_data=new _Data();
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
	
	public void initialize_mouselistener_handler(){
		_mouselistener_handler=new MouseListenerHandler();
	}
	protected void initialize_droptargetlistener_handler(){
		_droptargetlistener_handler=new DropTargetListenerHandler();
	}
	protected void initialize_dragsourcelistener_handler(){
		_dragsourcelistener_handler=new DragSourceListenerHandler();
	}
	public void initialize_components(){
		_Frame _frame=(_Frame) this._frame;
		_frame.setResizable(true);
		_frame.get_txt_agenda().setName(_Interface._txt_agenda);
		_frame.get_lst_anio().setName(_Interface._lst_anio);
		_frame.get_lst_mes().setName(_Interface._lst_mes);
		_frame.get_btn_agregar().setActionCommand(_Interface._btn_nueva_categoria);
		_frame.get_btn_editar().setActionCommand(_Interface._btn_renombrar_categoria);
		_frame.get_btn_quitar().setActionCommand(_Interface._btn_eliminar_categoria);
		_frame.get_btn_nuevo().setActionCommand(_Interface._btn_nuevo);
		_frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		_frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		_frame.get_btn_cargar().setActionCommand(_Interface._btn_cargar);
		_frame.get_btn_hoy().setActionCommand(_Interface._btn_hoy);
		
	}
	
	
	
	public void init(){
		
		super.init();
		_Frame _frame=(_Frame) this._frame;
		_Logic _logic=(_Logic) this._logic;
		
		_logic.maximizar();
		_logic.centrar();
		
		
		_logic.initialize_Hora();
		_logic.initialize_Vendedor();
		_logic.fechas();
		
		_logic.elegir_vendedor();
		
		
		
	}

	


	
}

