package aplicacion.inventario.linea.constructor;

import aplicacion.modelo.constructor.Constructor;
import aplicacion.inventario.linea.events.ActionListenerHandler;
import aplicacion.inventario.linea.events.WindowAdapterHandler;
import aplicacion.inventario.linea.events.KeyListenerHandler;
import aplicacion.inventario.linea.events.MouseListenerHandler;
import aplicacion.inventario.linea.events.ItemListenerHandler;
import aplicacion.inventario.linea.gui._Frame;
import aplicacion.inventario.linea.interfaces.*;
import aplicacion.inventario.linea.logic._Data;
import aplicacion.inventario.linea.logic._Logic;

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
		frame.get_btn_cargar().setActionCommand(_Interface._btn_cargar);
		frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		frame.get_btn_error().setActionCommand(_Interface._btn_error);
		frame.get_btn_imprimir().setActionCommand(_Interface._btn_imprimir);
		frame.get_btn_editar().setActionCommand(_Interface._btn_editar);
		frame.get_btn_editar_alias().setActionCommand(_Interface._btn_editar_alias);
		frame.get_btn_editar_actualizacion().setActionCommand(_Interface._btn_editar_actualizacion);
		frame.get_btn_actualizacion_archivo().setActionCommand(_Interface._btn_arctualizar_archivo);
		frame.get_btn_actualizacion_catalogo().setActionCommand(_Interface._btn_arctualizar_odbc);
		frame.get_btn_pedido_cargar().setActionCommand(_Interface._btn_pedido_cargar);
		frame.get_btn_pedido_especial().setActionCommand(_Interface._btn_nuevo_pedido);
		frame.get_chk_seleccionar().setName(_Interface._chk_seleccionar_items);
		frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		frame.get_btn_codigos().setActionCommand(_Interface._btn_planilla_codigos);
		frame.get_btn_cargar_linea().setActionCommand(_Interface._btn_cargar_linea);
	}
	
	public void init(){
		super.init();
		_Logic _logic=(_Logic) this._logic;
		_logic.centrar();
		String idarticulo="";
		try {
			idarticulo=(String)this.getParameter(aplicacion.inventario.linea.interfaces._parametros.idarticulo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		_logic.getToday();
		if (idarticulo!=null){
			if (idarticulo.compareTo("")!=0){
				//_logic.cargar_control(idarticulo);
			}else{
				_logic.focus();	
			}	
		}
	}
}
