package aplicacion.herramientas.java.hora.constructor;
import aplicacion.herramientas.java.hora.events.*;
import aplicacion.herramientas.java.hora.gui.*;
import aplicacion.herramientas.java.hora.interfaces._Interface;
import aplicacion.herramientas.java.hora.logic.*;
import aplicacion.modelo.constructor.*;
import javax.swing.*;
public class _Constructor extends Constructor{
	
	public void initialize_frame(){
		this._frame=new _Frame();
	}

	public void initialize_logic(){
		this._logic=new _Logic();
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
		
		frame.get_btn_hora_down().setActionCommand(_Interface._btn_hora_down);
		frame.get_btn_hora_up().setActionCommand(_Interface._btn_hora_up);
		frame.get_btn_minuto_down().setActionCommand(_Interface._btn_minuto_down);
		frame.get_btn_minuto_up().setActionCommand(_Interface._btn_minuto_up);
		frame.get_txt_hora().setName(_Interface._txt_hora);
		frame.get_txt_minutos().setName(_Interface._txt_minuto);
		frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancel);
	}
	
	
	public void init(){

		super.init();
		_Logic logic=(_Logic)getLogic();
		JTextField _textfield=null;
		try {
			_textfield =(JTextField)this.getValueParameter(aplicacion.herramientas.java.hora.interfaces._parametros._textfield);
		}catch(Exception e){
			
		}
		if (_textfield!=null){
			logic.setCampo(_textfield);
			logic.calculate_coordinates();
			
		}
		logic.init();
		
		
	}
}
