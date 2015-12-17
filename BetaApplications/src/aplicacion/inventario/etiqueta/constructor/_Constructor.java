package aplicacion.inventario.etiqueta.constructor;

import aplicacion.modelo.constructor.Constructor;
import aplicacion.inventario.etiqueta.events.ActionListenerHandler;
import aplicacion.inventario.etiqueta.events.WindowAdapterHandler;
import aplicacion.inventario.etiqueta.events.KeyListenerHandler;
import aplicacion.inventario.etiqueta.events.MouseListenerHandler;
import aplicacion.inventario.etiqueta.events.ItemListenerHandler;
import aplicacion.inventario.etiqueta.gui._Frame;
import aplicacion.inventario.etiqueta.interfaces.*;
import aplicacion.inventario.etiqueta.logic._Data;
import aplicacion.inventario.etiqueta.logic._Logic;

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
		frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		frame.get_btn_error().setActionCommand(_Interface._btn_error);
		frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		
		frame.get_btn_eliminar().setActionCommand(_Interface._btn_eliminar);
		frame.get_btn_guardar().setActionCommand(_Interface._btn_guardar);
		frame.get_btn_guardar_historial().setActionCommand(_Interface._btn_guardar_historial);
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
		frame.get_txt_iddeposito().setName(_Interface._txt_iddeposito);
		frame.get_btn_cargar_historial().setActionCommand(_Interface._btn_cargar_historial);
		frame.get_txt_desde().setName(_Interface._txt_fecha_desde);
		frame.get_txt_hasta().setName(_Interface._txt_fecha_hasta);
		frame.get_btn_buscar_fecha_desde().setActionCommand(_Interface._btn_buscar_fecha_desde);
		frame.get_btn_buscar_fecha_hasta().setActionCommand(_Interface._btn_buscar_fecha_hasta);
		frame.get_chk_seleccionar_historial().setName(_Interface._chk_seleccionar_historial);
		frame.get_btn_marcar().setActionCommand(_Interface._btn_marcar_impresas);
		frame.get_btn_desmarcar().setActionCommand(_Interface._btn_desmarcar_impresas);
		frame.get_btn_imprimir_etiquetas().setActionCommand(_Interface._btn_imprimir_etiquetas);
	}
	
	public void init(){
		super.init();
		_Logic _logic=(_Logic) this._logic;
		_logic.initialize_Fecha();
		_logic.initialize_Deposito();
		
		_logic.centrar();
		
		
		_logic.crear_tabla();
		
		String idarticulo="";
		try {
			idarticulo=(String)this.getParameter(aplicacion.inventario.etiqueta.interfaces._parametros.idarticulo);
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
