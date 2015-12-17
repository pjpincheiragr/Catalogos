package aplicacion.gestion.egreso.constructor;
import aplicacion.modelo.constructor.Constructor;
import aplicacion.proveedor.*;

import aplicacion.gestion.egreso.events.*;
import aplicacion.gestion.egreso.gui.*;
import aplicacion.gestion.egreso.logic.*;
import aplicacion.gestion.egreso.events.*;
import aplicacion.gestion.egreso.interfaces.*;
import aplicacion.gestion.egreso.interfaces._Parametros;


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
		_frame.get_txt_idcuenta().setName(_Interface._txt_idcliente);
		
		_frame.get_txt_idPago().setName(_Interface._txt_idcobranza);
		_frame.get_list_cajas().setName(_Interface._list_cajas);
		_frame.get_txt_concepto().setName(_Interface._txt_concepto);
		
		_frame.get_txt_fecha().setName(_Interface._txt_fecha);
		
		_frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		
		_frame.get_btn_anular().setActionCommand(_Interface._btn_anular);
		_frame.get_btn_salir().setActionCommand(_Interface._btn_salir);

		_frame.get_btn_error().setActionCommand(_Interface._btn_error);
		_frame.get_btn_grabar().setActionCommand(_Interface._btn_grabar);
		
		_frame.get_btn_buscar_pago().setActionCommand(_Interface._btn_buscar_pago);
		
		_frame.get_btn_buscar_proveedor().setActionCommand(_Interface._btn_buscar_proveedor);
		
		_frame.get_btn_fecha().setActionCommand(_Interface._btn_fecha);
		
		
	}
	
	
	
	public void init(){
		super.init();
		
		_Logic _logic=(_Logic) this._logic;
		_logic.centrar();
		_logic.cargar_cajas();
		_logic.obtener_proximo_cpte();
		
		String idcaja=(String)this.getValueParameter(_Parametros.caja);
		String idcomprobante=(String)this.getValueParameter(_Parametros.idcomprobante);
		
		if (idcaja.compareTo("")!=0){
			_logic._evaluar_idegreso();
			_logic.setCaja(idcaja);
			
		}
		if (idcomprobante.compareTo("")!=0){
			_logic.cargarPago(idcomprobante);
		}
		
	}

	


	
}

