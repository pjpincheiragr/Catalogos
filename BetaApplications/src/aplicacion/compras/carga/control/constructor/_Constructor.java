package aplicacion.compras.carga.control.constructor;
import java.util.Calendar;

import aplicacion.modelo.constructor.Constructor;

import aplicacion.compras.carga.control.gui._Frame;
import aplicacion.compras.carga.control.interfaces._Interface;
import aplicacion.compras.carga.control.logic.*;
import aplicacion.compras.carga.control.events.*;
public class _Constructor extends Constructor{

	public void initialize_frame(){
		_frame=new _Frame();
	}
	
	public void initialize_data(){
		_data=new _Data();
	}
	
	public void initialize_logic(){
		_logic=new _Logic();
	}
	public void initialize_actionlistener_handler(){
		_actionlistener_handler=new ActionListenerHandler();
	}
	
	public void initialize_keylistener_handler(){
		_keylistener_handler=new KeyListenerHandler();
	}
	
	public void initialize_mouselistener_handler(){
		_mouselistener_handler=new MouseListenerHandler();
	}
	public void initialize_itemlistener_handler(){
		_itemlistener_handler=new ItemListenerHandler();
	}
	public void initialize_components(){
		_Frame frame=(_Frame) this._frame;
		frame.get_txt_fecha_desde().setName(_Interface._txt_fecha_desde);
		frame.get_txt_fecha_hasta().setName(_Interface._txt_fecha_hasta);
		frame.get_txt_fecha_carga_desde().setName(_Interface._txt_fecha_carga_desde);
		frame.get_txt_fecha_carga_hasta().setName(_Interface._txt_fecha_carga_hasta);
		
		frame.get_txt_idproveedor().setName(_Interface._txt_idproveedor);
		frame.get_btn_mostrar().setActionCommand(_Interface._btn_cargar);
		frame.get_btn_editar_cpte().setActionCommand(_Interface._btn_editar_cpte);
		frame.get_btn_control_ok().setActionCommand(_Interface._btn_control_ok);
		frame.get_btn_control_error().setActionCommand(_Interface._btn_control_error);
		frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		frame.get_btn_fecha_desde().setActionCommand(_Interface._btn_fecha_desde);
		frame.get_btn_fecha_hasta().setActionCommand(_Interface._btn_fecha_hasta);
		frame.get_btn_contraste_mas().setActionCommand(_Interface._btn_contraste_mas);
		frame.get_btn_contraste_menos().setActionCommand(_Interface._btn_contraste_menos);
		frame.get_btn_back().setActionCommand(_Interface._btn_back);
		frame.get_btn_imprimir().setActionCommand(_Interface._btn_imprimir);
		frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		frame.get_btn_error().setActionCommand(_Interface._btn_error);
		frame.get_btn_next().setActionCommand(_Interface._btn_next);
		frame.get_btn_anterior().setActionCommand(_Interface._btn_anterior);
		frame.get_btn_siguiente().setActionCommand(_Interface._btn_siguiente);
		frame.get_btn_next().setActionCommand(_Interface._btn_next);
		frame.get_btn_unset_all().setActionCommand(_Interface._btn_unsetall);
		frame.get_list_anio().setName(_Interface._list_anio);
		frame.get_list_mes().setName(_Interface._list_mes);
		frame.get_chk_utiliza_calendario().setName(_Interface._chk_utiliza_calendario);
		
	}
	
	public void init(){
		super.init();
		
		_Frame frame=(_Frame) this._frame;
		_Logic logic=(_Logic)this._logic;
		logic.initialize_proveedor();
		logic.fechas();
		logic.fillTable();
		logic.load_variables();
		//Ca.set(Calendar.HOUR,1);
		//load(Ca.getTime());
		
	}
}

