package aplicacion.inventario.transferencia.constructor;

import aplicacion.modelo.constructor.Constructor;
import aplicacion.inventario.transferencia.events.ActionListenerHandler;
import aplicacion.inventario.transferencia.events.WindowAdapterHandler;
import aplicacion.inventario.transferencia.events.KeyListenerHandler;
import aplicacion.inventario.transferencia.events.MouseListenerHandler;
import aplicacion.inventario.transferencia.events.ItemListenerHandler;
import aplicacion.inventario.transferencia.gui._Frame;
import aplicacion.inventario.transferencia.interfaces.*;
import aplicacion.inventario.transferencia.logic._Data;
import aplicacion.inventario.transferencia.logic._Logic;

public class _Constructor extends Constructor {
	protected void initialize_frame(){
		_frame=new _Frame();
		
	}
	protected void initialize_data(){
		_data=new _Data();
	}
	protected void initialize_logic(){
		_logic=new _Logic();
	}
	protected void initialize_mouselistener_handler(){
		_mouselistener_handler=new MouseListenerHandler();
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
		_Frame frame=(_Frame) _frame;
		frame.get_btn_buscar_deposito().setActionCommand(_Interface._btn_buscar_deposito);
		frame.get_btn_cargar().setActionCommand(_Interface._btn_cargar);
		frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		frame.get_btn_error().setActionCommand(_Interface._btn_error);
		frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		frame.get_btn_etiquetas().setActionCommand(_Interface._btn_etiquetar);
		frame.get_btn_eliminar_stock().setActionCommand(_Interface._btn_eliminar_stock);
		frame.get_btn_aplicar_stock().setActionCommand(_Interface._btn_aplicar_stock);
		frame.get_btn_eliminar().setActionCommand(_Interface._btn_eliminar);
		frame.get_btn_guardar().setActionCommand(_Interface._btn_guardar);
		frame.get_btn_imprimir().setActionCommand(_Interface._btn_imprimir);
		frame.get_txt_idcomprobante().setName(_Interface._txt_idtransferencia);
		frame.get_txt_iddeposito().setName(_Interface._txt_iddeposito);
		frame.get_txt_iddeposito_destino().setName(_Interface._txt_iddeposito_destino);
		frame.get_btn_nuevo().setActionCommand(_Interface._btn_nuevo);
		frame.get_txt_fecha().setName(_Interface._txt_fecha);
		frame.get_btn_fecha().setActionCommand(_Interface._btn_buscar_fecha);
		frame.get_btn_importar().setActionCommand(_Interface._btn_importar);
		frame.get_btn_cargar_stock().setActionCommand(_Interface._btn_cargar_stock);
		frame.get_btn_clean().setActionCommand(_Interface._btn_clean);
		frame.get_chk_seleccionar().setName(_Interface._chk_seleccionar);
		frame.get_chk_seleccionar_items().setName(_Interface._chk_seleccionar_items);
		frame.get_btn_quitar().setActionCommand(_Interface._btn_quitar);
		frame.get_txt_articulo_desde().setName(_Interface._txt_idarticulo_desde);
		frame.get_txt_articulo_hasta().setName(_Interface._txt_idarticulo_hasta);
		frame.get_txt_idproveedor().setName(_Interface._txt_idproveedor);
		frame.get_txt_linea().setName(_Interface._txt_linea);
		frame.get_txt_Stock().setName(_Interface._txt_stock);
		frame.get_chk_A().setActionCommand(_Interface._chk_A);
		frame.get_chk_B().setActionCommand(_Interface._chk_B);
		frame.get_chk_C().setActionCommand(_Interface._chk_C);
		frame.get_chk_D().setActionCommand(_Interface._chk_D);
	}
	
	public void init(){
		super.init();
		_Logic _logic=(_Logic) this._logic;
		_logic.initialize_Fecha();
		_logic.initialize_TransferenciaStock();
		_logic.initialize_Vendedor();
		_logic.initialize_Deposito();
		
		_logic.centrar();
		_logic.nuevo();
		
		String idarticulo="";
		try {
			idarticulo=(String)this.getParameter(aplicacion.inventario.transferencia.interfaces._parametros.idarticulo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (idarticulo!=null){
			if (idarticulo.compareTo("")!=0){
				//_logic.cargar_control(idarticulo);
			}else{
				_logic.focus();	
			}	
		}
	}
}
