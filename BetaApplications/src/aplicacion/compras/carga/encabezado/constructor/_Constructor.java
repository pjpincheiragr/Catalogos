package aplicacion.compras.carga.encabezado.constructor;


import aplicacion.compras.carga.encabezado.events.ActionListenerHandler;
import aplicacion.compras.carga.encabezado.events.KeyListenerHandler;
import aplicacion.compras.carga.encabezado.events.ItemListenerHandler;
import aplicacion.compras.carga.encabezado.gui._Frame;
import aplicacion.compras.carga.encabezado.interfaces._Interface;
import aplicacion.compras.carga.encabezado.logic._Data;
import aplicacion.compras.carga.encabezado.logic._Logic;

import aplicacion.modelo.constructor.Constructor;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.compras.carga.encabezado.interfaces.*;
public class _Constructor extends Constructor {
	
	public void initialize_frame(){
		_frame=new _Frame();
	}
	
	public void initialize_logic(){
		_logic=new _Logic();
	}
	
	public void initialize_data(){
		_data=new _Data();
	}
	
	public void init(){
		super.init();
		_Logic logic=(_Logic) _logic;
		logic.initialize_proveedor();
		String cuenta=(String)this.getValueParameter(aplicacion.compras.carga.encabezado.interfaces._parametros.cuenta);
		String tc=(String)this.getValueParameter(aplicacion.compras.carga.encabezado.interfaces._parametros.tc);
		String idcomprobante=(String)this.getValueParameter(aplicacion.compras.carga.encabezado.interfaces._parametros.idcomprobante);
		logic.init();
		if (cuenta.compareTo("")!=0){
			logic.editar(cuenta, tc, idcomprobante);	
		}
	}
	
	public void initialize_keylistener_handler(){
		this._keylistener_handler=new KeyListenerHandler();
	}
	
	public void initialize_actionlistener_handler(){
		this._actionlistener_handler=new ActionListenerHandler();
	}
	public void initialize_itemlistener_handler(){
		this._itemlistener_handler=new ItemListenerHandler();
	}
	public void initialize_components(){
	
		_Frame frame=(_Frame) _frame;
		frame.get_txt_sucursal().setName(_Interface._txt_sucursal);
		
		frame.get_txt_numero().setName(_Interface._txt_numero);
		
		frame.get_list_letra().setName(_Interface._list_letra);
		
		frame.get_txt_fecha_factura().setName(_Interface._txt_fecha_factura);
		
		frame.get_txt_fecha_contable().setName(_Interface._txt_fecha_contable);
		
		frame.get_txt_idproveedor().setName(_Interface._txt_idproveedor);
		
		frame.get_btn_editar_proveedor().setActionCommand(_Interface._btn_editar_proveedor);
		
		frame.get_btn_scan().setActionCommand(_Interface._btn_scan);
		
		frame.get_btn_buscar_proveedor().setActionCommand(_Interface._btn_buscar_proveedor);
		
		frame.get_btn_grabar().setActionCommand(_Interface._btn_grabar);
		
		frame.get_btn_abrir().setActionCommand(_Interface._btn_abrir);
		
		frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		
		frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		frame.get_btn_error().setActionCommand(_Interface._btn_error);
		
		frame.get_list_imputacion().setName(_Interface._list_imputacion);
		
		frame.get_list_tc().setName(_Interface._list_tc);
		
		frame.get_btn_recargar_datos_proveedor().setActionCommand(_Interface._btn_recargar_datos_proveedor);
		
		frame.get_btn_eliminar().setActionCommand(_Interface._btn_eliminar);
		
		
		frame.get_txt_cai().setName(_Interface._txt_cai);
		
		frame.get_txt_cai_vencimiento().setName(_Interface._txt_cai_vencimiento);
		frame.setTitle("Carga de Comprobantes de Compras Digital - "+this.getValueParameter(_parametros.iduser));
		
		frame.get_btn_buscar_fecha_contable().setActionCommand(_Interface._btn_buscar_fecha_contable);
		
		frame.get_btn_buscar_fecha_factura().setActionCommand(_Interface._btn_buscar_fecha_factura);
		
		frame.get_btn_fecha_vencimiento().setActionCommand(_Interface._btn_buscar_fecha_vencimiento);
		
	}
}
