package aplicacion.inventario.politica.constructor;

import aplicacion.modelo.constructor.Constructor;
import aplicacion.inventario.politica.events.ActionListenerHandler;
import aplicacion.inventario.politica.events.WindowAdapterHandler;
import aplicacion.inventario.politica.events.KeyListenerHandler;
import aplicacion.inventario.politica.gui._Frame;
import aplicacion.inventario.politica.interfaces.*;
import aplicacion.inventario.politica.logic._Data;
import aplicacion.inventario.politica.logic._Logic;

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
		frame.get_btn_nuevo().setActionCommand(_Interface._btn_nuevo);
		frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		frame.get_btn_error().setActionCommand(_Interface._btn_error);
		frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		frame.get_btn_guardar().setActionCommand(_Interface._btn_guardar);
		frame.get_btn_buscar_politica().setActionCommand(_Interface._btn_buscar_politica);
		frame.get_btn_aplicar().setActionCommand(_Interface._btn_aplicar);
		frame.get_btn_cancelar_tarea().setActionCommand(_Interface._btn_cancelar_tarea);
		frame.get_txt_politica_descripcion().setName(_Interface._txt_politica_descripcion);
		frame.get_txt_formula_costo().setName(_Interface._txt_formula_costo);
		frame.get_txt_formula_publico().setName(_Interface._txt_formula_publico);
		frame.get_txt_idpolitica().setName(_Interface._txt_idpolitica);
		frame.get_txt_mcosto().setName(_Interface._txt_mcosto);
		frame.get_txt_mpublico().setName(_Interface._txt_mpublico);
		
	}
	public void init(){
		super.init();
		_Logic _logic=(_Logic) this._logic;
		_logic.centrar();
		String idpolitica="";
		try {
			idpolitica=(String) this.getParameter(_parametros.idpolitica);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		if (idpolitica!=null){
			if (idpolitica.compareTo("")!=0){
				_logic.cargarPolitica(idpolitica);
			}else{
				_logic.focus();	
			}	
		}else{
			_logic.focus();
		}
		
		
		
	}
}
