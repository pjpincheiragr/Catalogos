package aplicacion.compras.carga.scanner.constructor;
import aplicacion.modelo.constructor.*;

import aplicacion.compras.carga.scanner.gui._Frame;
import aplicacion.compras.carga.scanner.interfaces.*;
import aplicacion.compras.carga.scanner.events.*;
import aplicacion.compras.carga.scanner.logic.*;
public class _Constructor extends Constructor{

	public void initialize_frame(){
		this._frame=new _Frame();
	}
	
	public void initialize_logic(){
		this._logic=new _Logic();
	}
	
	public void initialize_data(){
		this._data=new _Data();
	}
	public void initialize_windowadapter_handler(){
		this._windowadapter_handler=new WindowAdapterHandler();
	}
	public void initialize_actionlistener_handler(){
		this._actionlistener_handler=new ActionListenerHandler();
	}
	public void initialize_components(){
		_Frame frame=(_Frame) this._frame;
		frame.get_btn_guardar().setActionCommand(_Interface._btn_guardar);
		
		frame.get_btn_scan().setActionCommand(_Interface._btn_scan);
		
	}
	
	public void init(){
		_Logic logic=(_Logic) this._logic;
		
		String cuenta=(String)this.getValueParameter(_Parametros._idproveedor);
		String descripcion=(String)this.getValueParameter(_Parametros._descripcion_proveedor);
		String tc=(String)this.getValueParameter(_Parametros._tc);
		String idcomprobante=(String)this.getValueParameter(_Parametros._idcomprobante);
		String secuencia=(String)this.getValueParameter(_Parametros._secuencia);
		//aplicacion.compras.carga.encabezado.gui._Frame _Framex=
			//(aplicacion.compras.carga.encabezado.gui._Frame)this.getValueParameter(_Parametros._frame);
		logic.setFileName(cuenta, descripcion, tc, idcomprobante, secuencia);
		//logic.setFrameX(_Framex);
		super.init();
	}
}

