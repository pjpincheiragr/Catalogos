package aplicacion.inventario.control.constructor;

import aplicacion.modelo.constructor.Constructor;
import aplicacion.inventario.control.events.ActionListenerHandler;
import aplicacion.inventario.control.events.WindowAdapterHandler;
import aplicacion.inventario.control.events.KeyListenerHandler;
import aplicacion.inventario.control.events.MouseListenerHandler;
import aplicacion.inventario.control.events.ItemListenerHandler;
import aplicacion.inventario.control.gui._Frame;
import aplicacion.inventario.control.interfaces.*;
import aplicacion.inventario.control.logic._Data;
import aplicacion.inventario.control.logic._Logic;

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
		frame.get_btn_nuevo().setActionCommand(_Interface._btn_nuevo);
		frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		frame.get_btn_etiquetas().setActionCommand(_Interface._btn_etiquetar);
		frame.get_btn_eliminar_stock().setActionCommand(_Interface._btn_eliminar_stock);
		frame.get_btn_aplicar_stock().setActionCommand(_Interface._btn_aplicar_stock);
		frame.get_btn_eliminar().setActionCommand(_Interface._btn_eliminar);
		frame.get_btn_guardar().setActionCommand(_Interface._btn_guardar);
		frame.get_btn_imprimir().setActionCommand(_Interface._btn_imprimir);
		frame.get_btn_check().setActionCommand(_Interface._btn_check);
		frame.get_btn_importar().setActionCommand(_Interface._btn_importar);
		frame.get_txt_idcontrol().setName(_Interface._txt_idcontrol);
		frame.get_txt_linea().setName(_Interface._txt_linea);
		frame.get_txt_iddeposito().setName(_Interface._txt_iddeposito);
		frame.get_txt_fecha().setName(_Interface._txt_fecha);
		frame.get_txt_fecha_stock().setName(_Interface._txt_fecha_limite);
		frame.get_btn_buscar_fecha_stock().setActionCommand(_Interface._btn_buscar_fecha_limite);
		frame.get_txt_idvendedor().setName(_Interface._txt_idvendedor);
		frame.get_txt_articulo_desde().setName(_Interface._txt_idarticulo_desde);
		frame.get_txt_articulo_hasta().setName(_Interface._txt_idarticulo_hasta);
		frame.get_btn_completar().setActionCommand(_Interface._btn_autocompletar);
		frame.get_chk_free().setName(_Interface._chk_free);
		
	}
	
	public void init(){
		super.init();
		_Logic _logic=(_Logic) this._logic;
		
		_logic.initialize_Control();
		_logic.initialize_Deposito();
		_logic.initialize_Fecha();
		_logic.initialize_Vendedor();
		_logic.centrar();
		_logic.focus();
		
		String idarticulo="";
		try {
			idarticulo=(String)this.getParameter(aplicacion.inventario.control.interfaces._parametros.idarticulo);
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
