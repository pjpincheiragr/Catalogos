package aplicacion.proveedor.pago.constructor;


import aplicacion.modelo.constructor.Constructor;
import aplicacion.proveedor.*;

import aplicacion.proveedor.pago.events.*;
import aplicacion.proveedor.pago.gui.*;
import aplicacion.proveedor.pago.logic.*;
import aplicacion.proveedor.pago.events.*;
import aplicacion.proveedor.pago.interfaces.*;


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
		_frame.get_txt_idproveedor().setName(_Interface._txt_idcliente);
		_frame.get_txt_idpago().setName(_Interface._txt_idcobranza);
		_frame.get_txt_fecha().setName(_Interface._txt_fecha);
		_frame.get_txt_credito_desde().setName(_Interface._txt_credito_desde);
		_frame.get_txt_credito_desde().setName(_Interface._txt_credito_hasta);
		_frame.get_txt_deuda_desde().setName(_Interface._txt_deuda_desde);
		_frame.get_txt_deuda_hasta().setName(_Interface._txt_deuda_hasta);
		_frame.get_btn_imprimir().setActionCommand(_Interface._btn_imprimir);
		
		_frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		_frame.get_btn_nuevo().setActionCommand(_Interface._btn_nuevo);
		_frame.get_btn_anular().setActionCommand(_Interface._btn_anular);
		_frame.get_btn_grabar().setActionCommand(_Interface._btn_grabar);
		_frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		_frame.get_btn_error().setActionCommand(_Interface._btn_error);
		_frame.get_btn_buscar_pago().setActionCommand(_Interface._btn_buscar_pago);
		_frame.get_btn_buscar_proveedor().setActionCommand(_Interface._btn_buscar_proveedor);
		_frame.get_btn_cargar_proveedor().setActionCommand(_Interface._btn_cargar_proveedor);
		_frame.get_btn_buscar_credito_desde().setActionCommand(_Interface._btn_credito_desde);
		_frame.get_btn_buscar_credito_hasta().setActionCommand(_Interface._btn_credito_hasta);
		_frame.get_btn_buscar_deuda_desde().setActionCommand(_Interface._btn_deuda_desde);
		_frame.get_btn_buscar_deuda_hasta().setActionCommand(_Interface._btn_deuda_hasta);
		
		_frame.get_btn_fecha().setActionCommand(_Interface._btn_fecha);
		_frame.get_chk_seleccionar_cpte().setName(_Interface._chk_seleccionar_cpte);
		_frame.get_chk_seleccionar_creditos().setName(_Interface._chk_seleccionar_creditos);
		
		
	}
	
	
	
	public void init(){
		super.init();
		
		_Logic _logic=(_Logic) this._logic;
		_logic.centrar();
		_logic.initialize_proveedor();
		_logic.obtener_proximo_cpte();
		_logic.initialize_Pago();
		_logic.cargar_cajas();
		_logic.focus();
		
		String idproveedor=(String)this.getValueParameter(_Parametros.idproveedor);
		if (idproveedor.compareTo("")!=0){
			_logic.pagoAProveedor(idproveedor);
		}
		
		
		String idpago=(String)this.getValueParameter(_Parametros.idpago);
		
		System.out.println("Error en cobranza logic! FVN NULLLLL!!!");
		if (idpago.compareTo("")!=0){
			if (_logic.existe(idpago)){
				_logic.load_Pago(idpago);
				_logic.focus();
			}else{
				_logic.error("El Pago "+idpago+" no se encuentra disponible");
				_logic.exit_command();
			}
			
		}else{
			if (idproveedor.compareTo("")!=0){
					_logic.cargarProveedor(idproveedor);	
				
				
			}else{
				_logic.focus();
			}
		}
		
	}

	


	
}

