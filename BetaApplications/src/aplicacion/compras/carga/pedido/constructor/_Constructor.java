package aplicacion.compras.carga.pedido.constructor;

import aplicacion.compras.carga.pedido.events.ActionListenerHandler;
import aplicacion.compras.carga.pedido.events.ItemListenerHandler;
import aplicacion.compras.carga.pedido.events.KeyListenerHandler;
import aplicacion.compras.carga.pedido.events.MouseListenerHandler;
import aplicacion.compras.carga.pedido.events.WindowAdapterHandler;
import aplicacion.compras.carga.pedido.gui._Frame;
import aplicacion.compras.carga.pedido.interfaces._Interface;
import aplicacion.compras.carga.pedido.logic._Data;
import aplicacion.compras.carga.pedido.logic._Logic;
import aplicacion.compras.carga.pedido.interfaces._parametros;
import aplicacion.modelo.constructor.Constructor;

public class _Constructor extends Constructor {
	public void initialize_frame(){
		_frame=new _Frame();
	}
	
	public void initialize_data(){
		_data=new _Data();
	}
	
	public void initialize_logic(){
		_logic=new _Logic();
	}
	public void initialize_actionlistener_handler(){
		_actionlistener_handler=new ActionListenerHandler();
	}
	public void initialize_windowadapter_handler(){
		  _windowadapter_handler=new WindowAdapterHandler();
	}
	
	public void initialize_keylistener_handler(){
		_keylistener_handler=new KeyListenerHandler();
	}
	
	public void initialize_mouselistener_handler(){
		_mouselistener_handler=new MouseListenerHandler();
	}
	public void initialize_itemlistener_handler(){
		_itemlistener_handler=new ItemListenerHandler();
	}
	
	public void initialize_components(){
		_Frame frame=(_Frame) this._frame;
		frame.get_btn_cargar_pedido().setActionCommand(_Interface._btn_cargar_pedido);
		frame.get_txt_idproveedor().setName(_Interface._txt_idproveedor);
		frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		frame.get_btn_imprimir().setActionCommand(_Interface._btn_imprimir);
		frame.get_btn_error().setActionCommand(_Interface._btn_error);
		frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		frame.get_txt_idpedido().setName(_Interface._txt_idpedido);
		frame.get_txt_descripcion_pedido().setName(_Interface._txt_descripcion_pedido);
		frame.get_btn_nuevo_pedido().setActionCommand(_Interface._btn_nuevo_pedido);
		frame.get_btn_agregar_asteriscos().setActionCommand(_Interface._btn_agregar_asteriscos);
		frame.get_btn_agregar_linea().setActionCommand(_Interface._btn_agregar_linea);
		frame.get_btn_buscar_pedido().setActionCommand(_Interface._btn_buscar_pedido);
		frame.get_btn_buscar_fecha().setActionCommand(_Interface._btn_buscar_fecha);
		frame.get_btn_cargar_proveedor().setActionCommand(_Interface._btn_cargar_proveedor);
		frame.get_btn_guardar().setActionCommand(_Interface._btn_guardar);
		frame.get_btn_fecha_desde().setActionCommand(_Interface._btn_fecha_desde);
		frame.get_btn_fecha_hasta().setActionCommand(_Interface._btn_fecha_hasta);
		frame.get_btn_proyectado().setActionCommand(_Interface._btn_proyectado);
		frame.get_btn_critico().setActionCommand(_Interface._btn_critico);
		frame.get_txt_desde().setName(_Interface._txt_desde);
		frame.get_txt_hasta().setName(_Interface._txt_hasta);
		frame.get_chk_seleccionar_items().setName(_Interface._chk_items);
		frame.get_chk_seleccionar_lineas().setName(_Interface._chk_linea);
		frame.get_txt_fecha().setName(_Interface._txt_fecha);
		frame.get_btn_eliminar().setActionCommand(_Interface._btn_eliminar);
		frame.get_lst_categoria().setName(_Interface._lst_categoria);
		frame.get_lst_stock().setName(_Interface._lst_categoria);
		frame.get_txt_idvendedor().setName(_Interface._txt_idvendedor);
		frame.get_txt_fecha().setName(_Interface._txt_fecha);
		frame.get_txt_descripcion_pedido().setName(_Interface._txt_descripcion_pedido);
		frame.get_txt_descripcion().setName(_Interface._txt_descripcion);
		frame.get_txt_informacion().setName(_Interface._txt_informacion);
		frame.get_txt_fecha_envio().setName(_Interface._txt_fecha_envio);
		frame.get_txt_guia().setName(_Interface._txt_guia);
		frame.get_txt_informacion().addKeyListener(this.getKeyListener());
		frame.get_txt_idtransporte().setName(_Interface._txt_idtransporte);
		frame.get_txt_idprovincia().setName(_Interface._txt_idprovincia);
		frame.get_txt_domicilio().setName(_Interface._txt_domicilio);
		frame.get_txt_cpostal().setName(_Interface._txt_cpostal);
		frame.get_txt_idciudad().setName(_Interface._txt_idciudad);
		frame.get_txt_tel().setName(_Interface._txt_telefono);
		
		frame.get_btn_cancelar_tarea().setActionCommand(_Interface._btn_cancelar_tarea);
		//frame.get_btn_buscar_cliente().setActionCommand(_Interface._btn_buscar_cliente);
		//frame.get_btn_cargar_cliente().setActionCommand(_Interface._btn_cargar_cliente);
		//frame.get_btn_editar_cliente().setActionCommand(_Interface._btn_editar_cliente);
		frame.get_btn_editar_proveedor().setActionCommand(_Interface._btn_editar_proveedor);
		frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		frame.get_btn_error().setActionCommand(_Interface._btn_error);
		//frame.get_btn_play().setActionCommand(_Interface._btn_play);
		frame.get_btn_buscar_transporte().setActionCommand(_Interface._btn_buscar_transporte);
		frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		frame.get_btn_guardar().setActionCommand(_Interface._btn_guardar);
		
		
		
		frame.get_btn_buscar_vendedor().setActionCommand(_Interface._btn_buscar_vendedor);
		//frame.get_btn_preparar().setActionCommand(_Interface._btn_preparar_pedido);
		frame.get_btn_editar_transporte().setActionCommand(_Interface._btn_editar_transporte);
		frame.get_btn_email().setActionCommand(_Interface._btn_mostrar_email);
		frame.get_btn_crear_info().setActionCommand(_Interface._btn_importar);
		frame.setName(_Interface._frame);
		frame.get_lst_modo_lineas().setName(_Interface._lst_lineas);
		frame.get_lst_modo().setName(_Interface._lst_modo);
		frame.get_lst_categoria_lineas().setName(_Interface._lst_lineas_frame_categoria);
		
		frame.get_btn_control().setActionCommand(_Interface._btn_control);
	}
	
	public void init(){
		super.init();
		_Frame frame=(_Frame) this._frame;
		_Logic logic=(_Logic)this._logic;
		logic.centrar();
		logic.initialize_layer();
		logic.initialize_proveedor();
		logic.initialize_Linea();
		logic.initialize_PedidoProveedor();
		logic.initialize_Fecha();
		logic.initialize_Provincia();
		logic.initialize_Transporte();
		logic.initialize_Vendedor();
		logic.getToday();
		logic.cargar_estados();
		logic.clean();

		//logic.setEstados();

		String idpedido=(String)this.getParameter(_parametros._idpedido);
		if (idpedido!=null){
			if (idpedido.compareTo("")!=0){
				logic.cargar_pedido(idpedido);
			}	
		}
		
		logic.focus();		
	}

}
