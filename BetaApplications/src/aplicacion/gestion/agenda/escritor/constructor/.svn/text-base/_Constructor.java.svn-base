/**
 * Aplicacion de Agenda
 * Autor: Agustin Wisky
 * ultima modificacion: 05-07-2010
 * version: 1
 * 
 */
package aplicacion.gestion.agenda.escritor.constructor;

import aplicacion.modelo.constructor.Constructor;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.gestion.agenda.escritor.events.*;
import aplicacion.gestion.agenda.escritor.gui.*;
import aplicacion.gestion.agenda.escritor.interfaces.*;
import aplicacion.gestion.agenda.escritor.logic.*;


public class _Constructor extends Constructor{
		
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
	
	
	public void initialize_components(){
		_Frame _frame=(_Frame) this._frame;
		_frame.setResizable(false);
		_frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		_frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		_frame.get_btn_error().setActionCommand(_Interface._btn_error);
		_frame.get_btn_guardar().setActionCommand(_Interface._btn_guardar);
		_frame.get_txt_agenda().setName(_Interface._txt_agenda);
		_frame.get_txt_idcreador().setName(_Interface._txt_idcreador);
		_frame.get_txt_idevento().setName(_Interface._txt_idaviso);
		_frame.get_txt_mensaje().setName(_Interface._txt_mensaje);
		_frame.get_txt_titulo().setName(_Interface._txt_titulo);
		_frame.get_btn_fecha().setActionCommand(_Interface._btn_fecha);
		_frame.get_btn_nuevo().setActionCommand(_Interface._btn_nuevo);
		_frame.get_btn_eliminar().setActionCommand(_Interface._btn_eliminar);
		_frame.get_lst_posponer().setName(_Interface._lst_posponer);
	}
	
	
	
	public void init(){
		
		super.init();
		_Frame _frame=(_Frame) this._frame;
		_Logic _logic=(_Logic) this._logic;
		_logic.centrar();
		String fecha="";
		try {
			fecha=(String) this.getValueParameter(_Parametros.fecha);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String idvendedor="";
		try {
			idvendedor=(String) this.getValueParameter(_Parametros.idvendedor);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		aplicacion.gestion.agenda.gestion.constructor._Constructor C=null;
		try {
			C=(aplicacion.gestion.agenda.gestion.constructor._Constructor) this.getValueParameter(_Parametros.gestion);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (C!=null){
			_logic.setGestion(C);
		}
		_logic.initialize_Hora();
		_logic.initialize_Vendedor();
		_logic.cargar_posponer();
		
			if (fecha.compareTo("")==0){
				_logic.init(idvendedor);	
			}else{
				_logic.nuevo(fecha,idvendedor);
			}	
		
		
	}

	


	
}

