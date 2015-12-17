package aplicacion.actualizacion.global.constructor;
import aplicacion.modelo.constructor.*;
import aplicacion.actualizacion.global.events.MouseListenerHandler;
import aplicacion.actualizacion.global.interfaces._parametros;
import aplicacion.actualizacion.global.events.*;
import aplicacion.actualizacion.global.gui.*;
import aplicacion.actualizacion.global.logic.*;
import aplicacion.actualizacion.global.interfaces.*;

import javax.swing.JFrame;

public class _Constructor extends Constructor {
	
	public void initialize_keylistener_handler(){
		_keylistener_handler=new KeyListenerHandler();
	}

	protected void initialize_itemlistener_handler(){
		_itemlistener_handler=new ItemListenerHandler();
		
	}
	
	public void initialize_actionlistener_handler(){
		_actionlistener_handler=new ActionListenerHandler();
			
	}
	
	protected void initialize_mouselistener_handler(){
		_mouselistener_handler=new MouseListenerHandler();
		
	}
	
	public void initialize_frame(){
		_frame=new _Frame();
	}
	
	public void initialize_logic(){
		_logic=new _Logic();
	}
	
	public void initialize_data(){
		_data=new _Data();
	}
	
	public void initialize_components(){
			_Frame frame=(_Frame) this._frame;
			
			frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
			frame.get_btn_cancelar_global().setActionCommand(_Interface._btn_cancelar_global);
			frame.get_btn_cancelar_tarea().setActionCommand(_Interface._btn_cancelar_tarea);
			frame.get_btn_nuevo().setActionCommand(_Interface._btn_nuevo);
			frame.get_btn_play().setActionCommand(_Interface._btn_play);
			frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
			frame.get_table_catalogos().setName(_Interface._table_catalogos);
			frame.get_txt_idcomprobante().setName(_Interface._txt_idcomprobante);
			frame.get_chk_seleccionar().setName(_Interface._chk_seleccionar);
			frame.get_chk_seleccionar().addMouseListener(this.getMouseListener());
			frame.get_lst_modo().setName(_Interface._lst_modo);
			frame.get_chk_actualizar().setActionCommand(_Interface._chk_actualizar);
			frame.get_chk_exportar().setActionCommand(_Interface._chk_exportar);
			frame.get_txt_path().setName(_Interface._txt_path);
			frame.get_btn_modificarCamino().setActionCommand(_Interface._btn_modificarCamino);
			
	}
	
	public void init(){
		super.init();
		
		_Logic _logic=(_Logic) this.getLogic();
		_logic.initialize_Global();
		_logic.centrar();
		_logic.nuevo();
		_logic.load_variables();

		//_logic.focus();
		
		
	}
}
