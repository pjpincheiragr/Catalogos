package aplicacion.cliente.reporte.constructor;

import aplicacion.cliente.reporte.events.ActionListenerHandler;
import aplicacion.cliente.reporte.events.KeyListenerHandler;
import aplicacion.cliente.reporte.events.WindowAdapterHandler;
import aplicacion.cliente.reporte.gui._Frame;
import aplicacion.cliente.reporte.interfaces._Interface;
import aplicacion.cliente.reporte.interfaces._Parametros;
import aplicacion.cliente.reporte.logic._Data;
import aplicacion.cliente.reporte.logic._Logic;
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
		_frame.get_txt_idcliente().setName(_Interface._txt_idcliente);
		_frame.get_txt_fecha_desde().setName(_Interface._txt_fecha_desde);
		_frame.get_txt_fecha_hasta().setName(_Interface._txt_fecha_hasta);
		
		_frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		
		_frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		_frame.get_btn_error().setActionCommand(_Interface._btn_error);
		_frame.get_btn_fecha_desde().setActionCommand(_Interface._btn_buscar_fecha_desde);
		_frame.get_btn_fecha_hasta().setActionCommand(_Interface._btn_buscar_fecha_hasta);
		_frame.get_btn_cargar().setActionCommand(_Interface._btn_cargar);
		_frame.get_btn_buscar_cliente().setActionCommand(_Interface._btn_buscar_cliente);
		
		
	}
	
	
	
	public void init(){
		super.init();
		_Logic _logic=(_Logic) this._logic;
		_logic.centrar();
		_logic.cargarTipoReportes();
		_logic.initialize_cliente();
		
		
		String idcliente=(String)this.getValueParameter(_Parametros.idcliente);
		if (idcliente.compareTo("")==0){
			_logic.focus();
		}else{
			_logic.CargarCliente(idcliente);
		}
		
		

		
		
		
	}
}
