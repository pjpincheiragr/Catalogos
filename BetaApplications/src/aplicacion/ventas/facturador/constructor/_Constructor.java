package aplicacion.ventas.facturador.constructor;
import aplicacion.modelo.constructor.Constructor;
import aplicacion.proveedor.*;

import aplicacion.ventas.facturador.events.*;
import aplicacion.ventas.facturador.gui.*;
import aplicacion.ventas.facturador.logic.*;
import aplicacion.ventas.facturador.events.*;
import aplicacion.ventas.facturador.interfaces.*;
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
		
		_Frame frame=(_Frame) this._frame;
		frame.setResizable(false);
		frame.get_txt_idcliente().setName(_Interface._txt_idcliente);
		frame.get_txt_remito().setName(_Interface._txt_remito);
		frame.get_txt_numero().setName(_Interface._txt_numero);
		frame.get_txt_idcondicion().setName(_Interface._txt_idcondicion);
		frame.get_txt_idvendedor().setName(_Interface._txt_idvendedor);
		frame.get_btn_buscar_remito().setName(_Interface._btn_buscar_remito);
		frame.get_btn_buscar_cliente().setName(_Interface._btn_buscar_cliente);
		frame.get_btn_cargar_cliente().setName(_Interface._btn_cargar_cliente);
		frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		frame.get_btn_pdc().setActionCommand(_Interface._btn_editar_pdc);
		frame.get_btn_error().setActionCommand(_Interface._btn_error);
		frame.get_btn_grabar().setActionCommand(_Interface._btn_guardar);
		frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		frame.get_btn_buscar_fecha().setActionCommand(_Interface._btn_buscar_fecha);
		frame.get_btn_buscar_remito().setActionCommand(_Interface._btn_buscar_remito);
		frame.get_btn_eliminar_remito().setActionCommand(_Interface._btn_eliminar_remito);
		frame.get_btn_eliminar().setActionCommand(_Interface._btn_eliminar);
		frame.get_lst_tipo_comprobante().setName(_Interface._list_comprobante);
		frame.get_btn_imprimir().setActionCommand(_Interface._btn_imprimir);
	}
	
	
	
	public void init(){
		super.init();
		
		_Logic _logic=(_Logic) this._logic;
		_logic.centrar();
		_logic.getToday();
		_logic.initialize_cliente();
		_logic.initialize_Remito();
		_logic.initialize_Condicion();
		_logic.initialize_Vendedor();
		
		_logic.initialize_FVN();
		_logic.clean();
		_logic.init();
		_logic.focus();
		
		
	}

	


	
}

