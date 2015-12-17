package aplicacion.gestion.tablero.constructor;
import aplicacion.modelo.constructor.Constructor;
import aplicacion.proveedor.*;

import aplicacion.gestion.tablero.events.*;
import aplicacion.gestion.tablero.gui.*;
import aplicacion.gestion.tablero.logic.*;
import aplicacion.gestion.tablero.events.*;
import aplicacion.gestion.tablero.interfaces.*;


import java.text.NumberFormat;

public class _Constructor extends Constructor{
	
	
		
	
	
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
	
	protected void initialize_mouselistener_handler(){
		_mouselistener_handler=new MouseListenerHandler();
		
	}
	
	public void initialize_components(){
		_Frame _frame=(_Frame) this._frame;
		_frame.setResizable(false);
		_frame.get_btn_cargar().setActionCommand(_Interface._btn_cargar);
		_frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		_frame.get_btn_error().setActionCommand(_Interface._btn_error);
		_frame.get_btn_guardar().setActionCommand(_Interface._btn_guardar);
		_frame.get_btn_guardar().addActionListener(getActionListener());
		_frame.get_btn_elegir_foreground().setActionCommand(_Interface._btn_elegir_foreground);
		_frame.get_btn_elegir_foreground().addActionListener(getActionListener());
		_frame.get_btn_elegir_background().setActionCommand(_Interface._btn_elegir_background);
		_frame.get_btn_elegir_background().addActionListener(getActionListener());
		_frame.get_txt_fecha_desde().setName(_Interface._txt_desde);
		_frame.get_txt_fecha_hasta().setName(_Interface._txt_hasta);
		_frame.get_btn_buscar_fecha_desde().setActionCommand(_Interface._btn_fecha_desde);
		_frame.get_btn_buscar_fecha_desde().addActionListener(getActionListener());
		_frame.get_btn_buscar_fecha_hasta().setActionCommand(_Interface._btn_fecha_hasta);
		_frame.get_btn_buscar_fecha_hasta().addActionListener(getActionListener());
		_frame.get_btn_calendario().setActionCommand(_Interface._btn_calendario);
		//_frame.get_btn_calendario().addActionListener(getActionListener());
		_frame.get_btn_copiar().setActionCommand(_Interface._btn_copiar);
		_frame.get_btn_pegar().setActionCommand(_Interface._btn_pegar);
		_frame.get_btn_copiar().addActionListener(getActionListener());
		_frame.get_btn_pegar().addActionListener(getActionListener());
		_frame.get_btn_nuevo().setActionCommand(_Interface._btn_nuevo);
		_frame.get_btn_eliminar().setActionCommand(_Interface._btn_eliminar);
		_frame.get_btn_eliminar_celda().setActionCommand(_Interface._btn_eliminar_celda);
		_frame.get_btn_eliminar_celda().addActionListener(getActionListener());
		_frame.get_btn_exportar().setActionCommand(_Interface._btn_exportar);
		_frame.get_btn_importar().setActionCommand(_Interface._btn_importar);
		_frame.get_lst_anio().setName(_Interface._list_anio);
		
		_frame.get_lst_mes().addItem("Enero");
		_frame.get_lst_mes().addItem("Febrero");
		_frame.get_lst_mes().addItem("Marzo");
		_frame.get_lst_mes().addItem("Abril");
		_frame.get_lst_mes().addItem("Mayo");
		_frame.get_lst_mes().addItem("Junio");
		_frame.get_lst_mes().addItem("Julio");
		_frame.get_lst_mes().addItem("Agosto");
		_frame.get_lst_mes().addItem("Septiembre");
		_frame.get_lst_mes().addItem("Octubre");
		_frame.get_lst_mes().addItem("Noviembre");
		_frame.get_lst_mes().addItem("Diciembre");
		_frame.get_lst_mes().setName(_Interface._list_mes);
		_frame.get_lst_mes().addItemListener(this._item_listener);

	}
	
	
	
	public void init(){
		super.init();
		_Logic _logic=(_Logic) this._logic;
		_logic.centrar();
		_logic.clean();
		_logic.setInitialDate();
		_logic.goCargar();
	}

	


	
}

