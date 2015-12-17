package aplicacion.gestion.calendario.constructor;
import aplicacion.gestion.calendario.events.*;
import aplicacion.gestion.calendario.gui.*;
import aplicacion.gestion.calendario.interfaces._Interface;
import aplicacion.gestion.calendario.logic.*;
import aplicacion.modelo.constructor.*;


import javax.swing.*;
public class _Constructor extends Constructor{
	
	public void initialize_frame(){
		this._frame=new _Frame();
	}

	public void initialize_logic(){
		_logic=new _Logic();
	}
	protected void initialize_data(){
		
		_data=new _Data();
	}
	
	
	public void initialize_keylistener_handler(){
		this._keylistener_handler=new KeyListenerHandler();
	}
	public void initialize_actionlistener_handler(){
		this._actionlistener_handler=new ActionListenerHandler();
	}
	public void initialize_itemlistener_handler(){
		this._itemlistener_handler=new ItemListenerHandler();
	}
	
	public void initialize_mouselistener_handler(){
		this._mouselistener_handler=new MouseListenerHandler();
	}
	public void initialize_components(){
		_Frame frame=(_Frame) this._frame;
		frame.get_btn_ok().setActionCommand(_Interface._btn_ok);
		
		
		frame.get_list_anio().setName(_Interface._list_anio);
		frame.get_list_mes().removeItemListener(this._item_listener);
		frame.get_list_mes().addItem("Enero");
		frame.get_list_mes().addItem("Febrero");
		frame.get_list_mes().addItem("Marzo");
		frame.get_list_mes().addItem("Abril");
		frame.get_list_mes().addItem("Mayo");
		frame.get_list_mes().addItem("Junio");
		frame.get_list_mes().addItem("Julio");
		frame.get_list_mes().addItem("Agosto");
		frame.get_list_mes().addItem("Septiembre");
		frame.get_list_mes().addItem("Octubre");
		frame.get_list_mes().addItem("Noviembre");
		frame.get_list_mes().addItem("Diciembre");
		frame.get_list_mes().setName(_Interface._list_mes);
		frame.get_list_mes().addItemListener(this._item_listener);
		
	}
	
	
	public void init(){
		//
		super.init();
		_Logic logic=(_Logic)getLogic();
		JButton _button=null;
		try {
			_button =(JButton)this.getValueParameter(aplicacion.gestion.calendario.interfaces._parametros._textfield);
		}catch(Exception e){
			
		}
		if (_button!=null){
			logic.setCampo(_button);
			logic.calculate_coordinates();
			
		}
		
		logic.init();
		
		
	}
}
