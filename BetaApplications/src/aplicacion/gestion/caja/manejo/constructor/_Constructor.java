package aplicacion.gestion.caja.manejo.constructor;
import aplicacion.modelo.constructor.Constructor;
import aplicacion.proveedor.*;

import aplicacion.gestion.caja.manejo.events.*;
import aplicacion.gestion.caja.manejo.gui.*;
import aplicacion.gestion.caja.manejo.logic.*;
import aplicacion.gestion.caja.manejo.events.*;
import aplicacion.gestion.caja.manejo.interfaces.*;
import java.text.NumberFormat;

public class _Constructor extends Constructor{
	
	
		
	
	
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
	
	protected void initialize_mouselistener_handler(){
		_mouselistener_handler=new MouseListenerHandler();
		
	}
	
	public void initialize_components(){
		
		_Frame _frame=(_Frame) this._frame;
		_frame.setResizable(false);
		_frame.get_btn_carga_venta().setActionCommand(_Interface._btn_venta);
		
		_frame.get_lst_cajas().setName(_Interface._list_cajas);
		_frame.get_btn_cargar().setActionCommand(_Interface._btn_cargar);
		_frame.get_btn_cheques().setActionCommand(_Interface._btn_cheques);
		_frame.get_btn_transferencia().setActionCommand(_Interface._btn_transferencias);
		_frame.get_btn_ingreso().setActionCommand(_Interface._btn_ingreso);
		_frame.get_btn_egreso().setActionCommand(_Interface._btn_egreso);
		_frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		_frame.get_btn_error().setActionCommand(_Interface._btn_error);
		_frame.get_btn_ajustar().setActionCommand(_Interface._btn_ajuste);
		 _frame.get_btn_canje().setActionCommand(_Interface._btn_canje);
		 _frame.get_btn_reporte().setActionCommand(_Interface._btn_reporte);
		 _frame.get_btn_movimientos().setActionCommand(_Interface._btn_movimientos);
		 _frame.get_btn_rechazados().setActionCommand(_Interface._btn_cheque_rechazado);
		 _frame.get_btn_cobranza().setActionCommand(_Interface._btn_cobranza);
		 _frame.get_btn_pago().setActionCommand(_Interface._btn_pago);
		 _frame.get_txt_desde().setName(_Interface._txt_desde);
		 _frame.get_txt_hasta().setName(_Interface._txt_hasta);
		 _frame.get_btn_fecha_desde().setActionCommand(_Interface._btn_fecha_desde);
		 _frame.get_btn_fecha_hasta().setActionCommand(_Interface._btn_fecha_hasta);
		 _frame.get_chk_beta().setName(_Interface._chk_beta);
		 _frame.get_chk_caja_alfa().setName(_Interface._chk_alfa);
	}
	
	
	
	public void init(){
		super.init();
		
		_Logic _logic=(_Logic) this._logic;
		_logic.centrar();
		_logic.clean();
		_logic.goCargar();
		
		
	}

	


	
}

