/**
 * Aplicacion Para verificar los cheques en cuenta en la caja seleccionada
 * Autor: Agustin Wisky
 * ultima modificacion: 02-09-2009
 * version: 1
 * 
 */
package aplicacion.inventario.reporteMovimiento.constructor;

import aplicacion.modelo.constructor.Constructor;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.inventario.reporteLinea.events.*;
import aplicacion.inventario.reporteLinea.gui.*;
import aplicacion.inventario.reporteLinea.logic.*;
import aplicacion.inventario.reporteLinea.interfaces.*;


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
		
		
		_frame.get_btn_reporte().setActionCommand(_Interface._btn_reporte);
		_frame.get_txt_idproveedor().setName(_Interface._txt_idproveedor);
		_frame.get_txt_linea().setName(_Interface._txt_linea);
		_frame.get_btn_buscar_idproveedor().setActionCommand(_Interface._btn_buscar_idproveedor);
	}
	
	
	
	public void init(){
		
		super.init();
		_Frame _frame=(_Frame) this._frame;
		_Logic _logic=(_Logic) this._logic;
		String idcaja=(String)this.getValueParameter(_Parametros.caja);
		String cuenta=(String)this.getValueParameter(_Parametros.cuenta);
		
		_logic.initialize_Deposito();
		_logic.centrar();
		_logic.loadOrden();
		_logic.focus();
		
		
		
	}

	


	
}

