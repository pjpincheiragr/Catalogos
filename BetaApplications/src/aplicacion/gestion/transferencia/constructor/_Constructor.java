package aplicacion.gestion.transferencia.constructor;
import aplicacion.modelo.constructor.Constructor;
import aplicacion.proveedor.*;

import aplicacion.gestion.transferencia.events.*;
import aplicacion.gestion.transferencia.gui.*;
import aplicacion.gestion.transferencia.logic.*;
import aplicacion.gestion.transferencia.events.*;
import aplicacion.gestion.transferencia.interfaces.*;


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
		
		_frame.get_txt_idtransferencia().setName(_Interface._txt_idcobranza);
		_frame.get_txt_fecha_desde().setName(_Interface._txt_fecha_desde);
		_frame.get_txt_fecha_hasta().setName(_Interface._txt_fecha_hasta);
		_frame.get_btn_confirmar().setActionCommand(_Interface._btn_confirmar);
		_frame.get_list_caja_origen().setName(_Interface._list_caja_origen);
		_frame.get_list_caja_destino().setName(_Interface._list_caja_destino);
		_frame.get_txt_concepto().setName(_Interface._txt_concepto);
		
		_frame.get_txt_fecha().setName(_Interface._txt_fecha);
		
		_frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		
		_frame.get_btn_anular().setActionCommand(_Interface._btn_anular);

		_frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		_frame.get_btn_error().setActionCommand(_Interface._btn_error);
		_frame.get_btn_grabar().setActionCommand(_Interface._btn_grabar);
		
		_frame.get_btn_buscar_pago().setActionCommand(_Interface._btn_buscar_pago);
		
		
		_frame.get_btn_autocompletar().setActionCommand(_Interface._btn_autocompletar);
		_frame.get_btn_fecha().setActionCommand(_Interface._btn_fecha);
		_frame.get_btn_fecha_desde().setActionCommand(_Interface._btn_fecha_desde);
		_frame.get_btn_fecha_hasta().setActionCommand(_Interface._btn_fecha_hasta);
		
		
	}
	
	
	
	public void init(){
		super.init();
		
		_Logic _logic=(_Logic) this._logic;
		_logic.centrar();
		_logic.obtener_proximo_cpte();
		String idcaja=(String)this.getValueParameter(_Parametros.caja);
		String idcaja_destino=(String)this.getValueParameter(_Parametros.caja_destino);
		String idcomprobante=(String)this.getValueParameter(_Parametros.idcomprobante);
		String express=(String)this.getValueParameter(_Parametros.express);
		_logic.getToday();
		_logic.cargar_cajas();
		if (idcaja.compareTo("")!=0){
			
			
			_logic.cargar_cajas();
			_logic._evaluar_idtransferencia();
			_logic.cargar_fechas();
			_logic.setCaja(idcaja);
			if (idcaja_destino.compareTo("")!=0){
				_logic.setCajaDestino(idcaja_destino);
				
			}
			if (express.compareTo("")!=0){
				_logic.autocompletar();
			}
			
		}
		if (idcomprobante.compareTo("")!=0){
			
			_logic.loadTransferencia(idcomprobante);
		}
		
	}

	


	
}

