package aplicacion.inventario.planilla.constructor;

import aplicacion.modelo.constructor.Constructor;
import aplicacion.inventario.planilla.events.ActionListenerHandler;
import aplicacion.inventario.planilla.events.WindowAdapterHandler;
import aplicacion.inventario.planilla.events.KeyListenerHandler;
import aplicacion.inventario.planilla.events.MouseListenerHandler;
import aplicacion.inventario.planilla.events.ItemListenerHandler;
import aplicacion.inventario.planilla.gui._Frame;
import aplicacion.inventario.planilla.interfaces.*;
import aplicacion.inventario.planilla.logic._Data;
import aplicacion.inventario.planilla.logic._Logic;

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
		frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		frame.get_btn_buscar_articulo_desde().setActionCommand(_Interface._btn_buscar_articulo_desde);
		frame.get_btn_buscar_articulo_hasta().setActionCommand(_Interface._btn_buscar_articulo_hasta);
		frame.get_btn_buscar_linea().setActionCommand(_Interface._btn_buscar_linea);
		frame.get_btn_buscar_proveedor().setActionCommand(_Interface._btn_buscar_proveedor);
		frame.get_btn_politicas_de_precio().setActionCommand(_Interface._btn_politicas_de_precios);
		frame.get_btn_copiar_memoria().setActionCommand(_Interface._btn_copiar_memoria);
		frame.get_btn_pegar_memoria().setActionCommand(_Interface._btn_pegar_memoria);
		frame.get_btn_completar().setActionCommand(_Interface._btn_completar);
		frame.get_btn_eliminar().setActionCommand(_Interface._btn_eliminar);
		frame.get_btn_cancelar_tarea().setActionCommand(_Interface._btn_cancelar_tarea);
		frame.get_btn_guardar().setActionCommand(_Interface._btn_guardar);
		frame.get_btn_buscar_reemplazar().setActionCommand(_Interface._btn_buscar_reemplazar);
		frame.get_btn_sincronizar().setActionCommand(_Interface._btn_sincronizar);
		frame.get_btn_importar().setActionCommand(_Interface._btn_importar);
		frame.get_btn_exportar().setActionCommand(_Interface._btn_exportar);
		frame.get_btn_editar_articulo().setActionCommand(_Interface._btn_editar_articulo);
		frame.get_btn_aumentar_precios().setActionCommand(_Interface._btn_incrementar);
		frame.get_btn_play().setActionCommand(_Interface._btn_play);
		frame.get_chk_selecionar().setName(_Interface._chk_seleccionar);
		frame.get_list_marcar().setName(_Interface._lst_marcar);
		frame.get_txt_idarticulo_desde().setName(_Interface._txt_idarticulo_desde);
		frame.get_txt_idarticulo_hasta().setName(_Interface._txt_idarticulo_hasta);
		frame.get_txt_idproveedor().setName(_Interface._txt_idproveedor);
		frame.get_txt_linea().setName(_Interface._txt_linea);
		frame.get_txt_descripcion().setName(_Interface._txt_descripcion);
		frame.get_lst_desplazamiento().addItem("Vertical");
		frame.get_lst_desplazamiento().addItem("Horizontal");
		frame.get_btn_autoconfigurar().setActionCommand(_Interface._btn_autoconfigurar);
		frame.get_list_marcar().addItem("");
		frame.get_list_marcar().addItem("Alias");
		frame.get_list_marcar().addItem("Stock");
	}
	
	public void init(){
		super.init();
		_Logic _logic=(_Logic) this._logic;
		_Frame _frame=(_Frame) this._frame;
		String _linea="";
		String _idproveedor="";
		String _idproveedor_actualizacion="";
		try {
			_linea = (String)this.getParameter(_parametros._linea);
			_idproveedor = (String)this.getParameter(_parametros._proveedor);
			_idproveedor_actualizacion = (String)this.getParameter(_parametros._proveedor_actualizacion);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		_logic.centrar();
		
		if (_linea.compareTo("")!=0){
			_frame.get_txt_linea().setText(_linea);
		}
		if (_idproveedor_actualizacion.compareTo("")!=0){
			_frame.get_txt_cuenta_actualizacion().setText(_idproveedor_actualizacion);
		}
		if (_idproveedor.compareTo("")!=0){
			_frame.get_txt_idproveedor().setText(_idproveedor);
			_logic.evaluar_proveedor(_frame.get_txt_idproveedor());
		}
		if (_linea.compareTo("")!=0 |_idproveedor.compareTo("")!=0){
			_logic.goCargar();
		}
		_logic.focus();
	}
}
