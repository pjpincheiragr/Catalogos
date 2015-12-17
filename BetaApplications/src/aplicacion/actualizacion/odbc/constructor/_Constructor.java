package aplicacion.actualizacion.odbc.constructor;
import aplicacion.modelo.constructor.*;
import aplicacion.actualizacion.odbc.interfaces._parametros;
import aplicacion.actualizacion.odbc.events.*;
import aplicacion.actualizacion.odbc.gui.*;
import aplicacion.actualizacion.odbc.logic.*;
import aplicacion.actualizacion.odbc.interfaces.*;

import javax.swing.JFrame;

public class _Constructor extends Constructor {
	
	public void initialize_keylistener_handler(){
		_keylistener_handler=new KeyListenerHandler();
	}

	public void initialize_actionlistener_handler(){
		_actionlistener_handler=new ActionListenerHandler();
			
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
			frame.get_btn_cancelar_tarea().setActionCommand(_Interface._btn_cancelar_tarea);
			frame.get_btn_test().setActionCommand(_Interface._btn_test);
			frame.get_txt_idproveedor().setName(_Interface._txt_idproveedor);
			frame.get_btn_buscar_politica().setActionCommand(_Interface._btn_buscar_politica);
			frame.get_btn_buscar_proveedor().setActionCommand(_Interface._btn_buscar_proveedor);
			frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
			frame.get_btn_error().setActionCommand(_Interface._btn_error);
			frame.get_btn_guardar().setActionCommand(_Interface._btn_guardar);
			frame.get_txt_idcomprobante().setName(_Interface._txt_idcomprobante);
			frame.get_btn_nuevo().setActionCommand(_Interface._btn_nuevo);
			frame.get_btn_exportar().setActionCommand(_Interface._btn_exportar);
			frame.get_btn_load().setActionCommand(_Interface._btn_reload);
			frame.get_txt_idpolitica().setName(_Interface._txt_idpolitica);
	}
	
	public void init(){
		super.init();
		
		_Logic _logic=(_Logic) this.getLogic();
		
		_logic.initialize_proveedor();
		_logic.initialize_Politica();
		String idproveedor="";
		String linea="";
		try {
			idproveedor=(String) this.getParameter(_parametros._idproveedor);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			linea=(String) this.getParameter(_parametros._linea);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		_logic.centrar();
		_logic.nuevo();
		
		if (idproveedor!=null & linea!=null){
			if (idproveedor.compareTo("")!=0 & linea.compareTo("")!=0){
				
				
				_Frame frame=(_Frame) this._frame;
				_logic.evaluar_idcomprobante(frame.get_txt_idcomprobante());
				frame.get_txt_idproveedor().setText(idproveedor);
				_logic.evaluarProveedor(frame.get_txt_idproveedor());
				frame.get_txt_linea().setText(linea);
				
			}
		}
		System.out.println("Init ODBC");
		_logic.focus();
		
	}
}
