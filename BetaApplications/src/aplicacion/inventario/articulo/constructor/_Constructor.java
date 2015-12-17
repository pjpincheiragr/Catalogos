package aplicacion.inventario.articulo.constructor;

import java.awt.dnd.DropTarget;

import aplicacion.modelo.constructor.Constructor;
import aplicacion.inventario.articulo.events.ActionListenerHandler;
import aplicacion.inventario.articulo.events.DropTargetListenerHandler;
import aplicacion.inventario.articulo.events.WindowAdapterHandler;
import aplicacion.inventario.articulo.events.KeyListenerHandler;
import aplicacion.inventario.articulo.events.MouseListenerHandler;
import aplicacion.inventario.articulo.events.ItemListenerHandler;
import aplicacion.inventario.articulo.gui._Frame;
import aplicacion.inventario.articulo.interfaces.*;
import aplicacion.inventario.articulo.logic._Data;
import aplicacion.inventario.articulo.logic._Logic;

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
	protected void initialize_droptargetlistener_handler(){
		_droptargetlistener_handler=new DropTargetListenerHandler();
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
		frame.get_btn_recodificar().setActionCommand(_Interface._btn_recodificar);
		frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		frame.get_btn_buscar_articulo().setActionCommand(_Interface._btn_buscar_articulo);
		frame.get_btn_buscar_linea().setActionCommand(_Interface._btn_buscar_linea);
		frame.get_btn_buscar_proveedor().setActionCommand(_Interface._btn_buscar_proveedor);
		frame.get_btn_buscar_politica().setActionCommand(_Interface._btn_buscar_politica);
		frame.get_btn_anterior().setActionCommand(_Interface._btn_anterior_foto);
		frame.get_btn_siguiente().setActionCommand(_Interface._btn_siguiente_foto);
		frame.get_btn_eliminar().setActionCommand(_Interface._btn_eliminar);
		frame.get_btn_guardar().setActionCommand(_Interface._btn_guardar);
		frame.get_btn_sincronizar().setActionCommand(_Interface._btn_sincronizar);
		frame.get_btn_editar_politica().setActionCommand(_Interface._btn_editar_politica);
		frame.get_btn_cargar_alias().setActionCommand(_Interface._btn_cargar_alias);
		frame.get_btn_editar_alias().setActionCommand(_Interface._btn_editar_alias);
		frame.get_btn_buscar_proveedor_actualizacion().setActionCommand(_Interface._btn_buscar_proveedor_actualizacion);
		
		frame.get_btn_imprimir_etiqueta().setActionCommand(_Interface._btn_imprimir_etiquetas);
		frame.get_txt_idarticulo().setName(_Interface._txt_idarticulo);
		frame.get_txt_descripcion().setName(_Interface._txt_descripcion);
		frame.get_txt_idproveedor().setName(_Interface._txt_idproveedor);
		frame.get_txt_linea().setName(_Interface._txt_linea);
		frame.get_txt_idpolitica().setName(_Interface._txt_idpolitica);
		frame.get_txt_precio_lista().setName(_Interface._txt_precio_lista);
		frame.get_txt_precio_costo().setName(_Interface._txt_precio_costo);
		frame.get_txt_precio_publico().setName(_Interface._txt_precio_publico);
		frame.get_txt_minimo().setName(_Interface._txt_minimo);
		frame.get_txt_cuenta_actualizacion().setName(_Interface._txt_cuenta_actualizacion);
		frame.get_btn_zoom_in().setActionCommand(_Interface._btn_zoom_in);
		frame.get_btn_zoom_out().setActionCommand(_Interface._btn_zoom_out);
		frame.get_btn_modificar_stock().setActionCommand(_Interface._btn_modificar_stock);
		frame.get_btn_transferir().setActionCommand(_Interface._btn_transferir_stock);
	}
	
	public void init(){
		super.init();
		_Logic _logic=(_Logic) this._logic;
		
		
		_logic.init();
		
		_logic.centrar();
		_logic.initialize_dnd();
		String idarticulo="";
		try {
			idarticulo=(String)this.getParameter(aplicacion.inventario.articulo.interfaces._parametros.idarticulo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (idarticulo!=null){
			if (idarticulo.compareTo("")!=0){
				_logic.cargar(idarticulo);
			}else{
				_logic.focus();	
			}	
		}else{
			_logic.focus();
		}
		
		
	}
}
