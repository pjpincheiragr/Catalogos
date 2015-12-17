package aplicacion.ventas.presupuesto.constructor;

import aplicacion.ventas.presupuesto.events.ActionListenerHandler;
import aplicacion.ventas.presupuesto.events.KeyListenerHandler;
import aplicacion.ventas.presupuesto.events.WindowAdapterHandler;
import aplicacion.ventas.presupuesto.gui._Frame;
import aplicacion.ventas.presupuesto.interfaces._Interface;
import aplicacion.ventas.presupuesto.interfaces._Parametros;
import aplicacion.ventas.presupuesto.logic._Data;
import aplicacion.ventas.presupuesto.logic._Logic;
import aplicacion.modelo.constructor.Constructor;

public class _Constructor extends Constructor {

	
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
	
	
	public void initialize_components(){
		_Frame _frame=(_Frame) this._frame;
		_frame.setName(_Interface._frame);
		_frame.get_txt_idcliente().setName(_Interface._txt_idcliente);
		_frame.get_txt_fecha_desde().setName(_Interface._txt_fecha_desde);
		
		
		_frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		
		_frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		_frame.get_btn_error().setActionCommand(_Interface._btn_error);
		_frame.get_btn_fecha_desde().setActionCommand(_Interface._btn_buscar_fecha_desde);
		
		_frame.get_btn_cargar().setActionCommand(_Interface._btn_cargar);
		_frame.get_btn_email().setActionCommand(_Interface._btn_email);
		
		
	}
	
	
	
	public void init(){
		super.init();
		_Logic _logic=(_Logic) this._logic;
		_Frame frame=(_Frame) this._frame;
		_logic.centrar();
		_logic.cargarCondiciondeVenta();
		_logic.cargarPlazodeEntrega();
		_logic.cargarMantenimientoOferta();
		_logic.getToday();
		
		String idcliente=(String)this.getValueParameter(_Parametros.idcliente);
		String descripcion=(String)this.getValueParameter(_Parametros.descripcion_cliente);
		String email=(String)this.getValueParameter(_Parametros.email);
		String vendedor=(String)this.getValueParameter(_Parametros.vendedor);
		String idpedido=(String)this.getValueParameter(_Parametros.idpedido);
		String subtotal=(String)this.getValueParameter(_Parametros.subtotal);
		String iva=(String)this.getValueParameter(_Parametros.iva);
		String total=(String)this.getValueParameter(_Parametros.total);
		Boolean discriminar=(Boolean)this.getValueParameter(_Parametros.discriminar);
		if (idcliente.compareTo("")!=0){
			frame.get_txt_idcliente().setText(idcliente);
			frame.get_txt_cliente_descripcion().setText(descripcion);
			frame.get_txt_idpedido().setText(idpedido);
			frame.get_txt_email().setText(email);
			frame.get_txt_vendedor().setText(vendedor);
			frame.get_txt_subtotal().setText(subtotal);
			frame.get_txt_iva().setText(iva);
			frame.get_txt_total().setText(total);
			frame.get_chk_iva().setSelected(discriminar);
		}
				
	}
}
