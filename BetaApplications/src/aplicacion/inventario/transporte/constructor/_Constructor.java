package aplicacion.inventario.transporte.constructor;

import aplicacion.modelo.constructor.Constructor;
import aplicacion.inventario.transporte.events.ActionListenerHandler;
import aplicacion.inventario.transporte.events.WindowAdapterHandler;
import aplicacion.inventario.transporte.events.KeyListenerHandler;
import aplicacion.inventario.transporte.gui._Frame;
import aplicacion.inventario.transporte.interfaces.*;
import aplicacion.inventario.transporte.logic._Data;
import aplicacion.inventario.transporte.logic._Logic;

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
	
	public void initialize_components(){
		_Frame frame=(_Frame) _frame;
		frame.get_btn_cargar().setActionCommand(_Interface._btn_cargar);
		frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		frame.get_btn_error().setActionCommand(_Interface._btn_error);
		frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		frame.get_btn_guardar().setActionCommand(_Interface._btn_guardar);
		frame.get_btn_buscar_transporte().setActionCommand(_Interface._btn_buscar_transporte);
		frame.get_btn_nuevo().setActionCommand(_Interface._btn_nuevo);
		frame.get_txt_idtransporte().setName(_Interface._txt_idtransporte);
		
		
	}
	public void init(){
		super.init();
		_Logic _logic=(_Logic) this._logic;
		_logic.centrar();
		String idtransporte="";
		try {
			idtransporte=(String) this.getParameter(_parametros.idtransporte);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		if (idtransporte!=null){
			if (idtransporte.compareTo("")!=0){
				_logic.cargarTransporte(idtransporte);
			}else{
				_logic.nuevo();
				_logic.focus();	
			}	
		}else{
			_logic.focus();
		}
		
		
		
	}
}
