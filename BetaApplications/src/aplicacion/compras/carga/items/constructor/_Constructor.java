package aplicacion.compras.carga.items.constructor;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import aplicacion.modelo.constructor.*;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.compras.carga.items.gui.*;
import aplicacion.compras.carga.items.interfaces._Interface;
import aplicacion.compras.carga.items.logic._Logic;
import aplicacion.compras.carga.items.logic._Data;
import aplicacion.compras.carga.items.events.ActionListenerHandler;
import aplicacion.compras.carga.items.events.WindowAdapterHandler;
import aplicacion.compras.carga.items.events.ItemListenerHandler;
import aplicacion.compras.carga.items.events.KeyListenerHandler;
import aplicacion.compras.carga.items.events.MouseListenerHandler;
public class _Constructor extends Constructor{
	
	public void initialize_frame(){
		_frame=new _Frame();
	}
	public void initialize_logic(){
		_logic=new _Logic();
	}
	
	public void initialize_data(){
		_data=new _Data();
	}
	
	public void initialize_windowadapter_handler(){
		this._windowadapter_handler=new WindowAdapterHandler();
	}
	public void initialize_actionlistener_handler(){
		this._actionlistener_handler=new ActionListenerHandler();
	}
	public void initialize_keylistener_handler(){
		this._keylistener_handler=new KeyListenerHandler();
	}
	public void initialize_itemlistener_handler(){
		this._itemlistener_handler=new ItemListenerHandler();
	}
	
	public void initialize_mouselistener_handler(){
		this._mouselistener_handler=new MouseListenerHandler();
	}
	public void initialize_components(){
		System.out.println("Initialize Components!!!!");
		_Frame frame=(_Frame) _frame;
		frame.get_btn_imprimir().setActionCommand(_Interface._btn_imprimir);
		frame.get_btn_abajo().setActionCommand(_Interface._btn_abajo);
		frame.get_btn_editar().setActionCommand(_Interface._btn_editar_proveedor);
		frame.get_btn_hoja_anterior().setActionCommand(_Interface._btn_hoja_anterior);
		frame.get_btn_buscar_proveedor().setActionCommand(_Interface._btn_buscar_proveedor);
		frame.get_btn_fecha().setActionCommand(_Interface._btn_buscar_fecha);
		frame.get_btn_buscar_cpte().setActionCommand(_Interface._btn_buscar_cpte);
		frame.get_btn_hoja_siguiente().setActionCommand(_Interface._btn_hoja_siguiente);
		
		frame.get_btn_arriba().setActionCommand(_Interface._btn_arriba);
		
		frame.get_btn_izquierda().setActionCommand(_Interface._btn_izquierda);
		
		frame.get_btn_derecha().setActionCommand(_Interface._btn_derecha);
		
		frame.get_btn_zoomin().setActionCommand(_Interface._btn_zoomin);
		frame.get_btn_play().setActionCommand(_Interface._btn_play);
		frame.get_btn_zoomout().setActionCommand(_Interface._btn_zoomout);
		frame.get_btn_cargar_configuracion().setActionCommand(_Interface._btn_recargar_configuracion);
		frame.get_btn_rotate_left().setActionCommand(_Interface._btn_rotate_left);
		
		frame.get_btn_rotate_right().setActionCommand(_Interface._btn_rotate_right);
		frame.get_btn_darken().setActionCommand(_Interface._btn_darken);
		frame.get_btn_maximizar().setActionCommand(_Interface._btn_maximizar);
		frame.get_btn_brighten().setActionCommand(_Interface._btn_brighten);
		frame.get_txt_idproveedor().setName(_Interface._txt_idproveedor);
		
		frame.get_txt_idproveedor_articulos().setName(_Interface._txt_idproveedor_articulos);
		
		frame.get_txt_idcomprobante().setName(_Interface._txt_idcomprobante);
		
		frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		
		frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		frame.get_btn_error().setActionCommand(_Interface._btn_error);
		frame.get_btn_guardar().setActionCommand(_Interface._btn_guardar);
		
		
		
		frame.get_list_lineas().setName(_Interface._list_lineas);
		frame.get_list_letra().setName(_Interface._list_letra);
		frame.get_list_tc().setName(_Interface._list_tc);
		frame.get_list_imputacion().setName(_Interface._list_imputacion);
		/*
		frame.get_btn_brighten().addActionListener(this._action_listener);
		
		frame.get_btn_darken().addActionListener(this._action_listener);
		
		frame.get_btn_hoja_anterior().addActionListener(this._action_listener);
		
		frame.get_btn_hoja_siguiente().addActionListener(this._action_listener);
		*/
		frame.get_btn_aplicar_descuento_articulos().setActionCommand(_Interface._btn_aplicar_descuento_articulos);
		
		frame.get_txt_descuento_porcentaje().setName(_Interface._txt_descuento_porcentaje);
		
		frame.get_txt_descuento().setName(_Interface._txt_descuento);
		
		frame.get_txt_descuento_detalle().setName(_Interface._txt_descuento_porcentaje);;
		
		
		this.load_combobox_options(frame.get_list_actualiza());
		this.load_combobox_options(frame.get_list_etiquetas());
		frame.get_txt_fecha().setName(_Interface._txt_fecha);
		frame.get_txt_fecha_carga().setName(_Interface._txt_fecha_carga);
		
		//private JTextField _txt_hoja_actual = null;
		frame.get_txt_hoja_actual().setName(_Interface._txt_hoja_actual);
		
		//private JTextField _txt_hoja_total = null;
		frame.get_txt_hoja_total().setName(_Interface._txt_hoja_total);
		
		//public JTextField _txt_idcomprobante = null;
		frame.get_txt_idcomprobante().setName(_Interface._txt_idcomprobante);
		
		//public JTextField _txt_idproveedor = null;
		frame.get_txt_idproveedor().setName(_Interface._txt_idproveedor);
		
		//private JTextField _txt_idproveedor_articulos = null;
		frame.get_txt_idproveedor_articulos().setName(_Interface._txt_idproveedor_articulos);
		//public JTextField _txt_inc = null;
		
		
		//private JTextField _txt_proveedor_articulos_descripcion = null;
		frame.get_txt_proveedor_articulos_descripcion().setName(_Interface._txt_proveedor_articulos_descripcion);
		
		//public JTextField _txt_proveedor_descripcion = null;
		frame.get_txt_proveedor_descripcion().setName(_Interface._txt_proveedor_descripcion);
	
		
		
		//public JTextField _txt_subtotal_calculado = null;
		frame.get_txt_subtotal_calculado().setName(_Interface._txt_subtotal_calculado);
		
		//public JTextField _txt_subtotal_diferencia = null;
		frame.get_txt_subtotal_diferencia().setName(_Interface._txt_subtotal_diferencia);
		
	//	public JTextField _txt_total = null;
		frame.get_txt_total().setName(_Interface._txt_subtotal);
		
		//public JTextField _txt_unidades = null;
		frame.get_txt_unidades().setName(_Interface._txt_unidades);
		
		//public JTextField _txt_zoom = null;
		frame.get_txt_zoom().setName(_Interface._txt_zoom);
		
		frame.get_txt_prefijo().setName(_Interface._txt_prefijo);
		
		frame.get_txt_descuento().setName(_Interface._txt_descuento);
		frame.get_txt_descuento_detalle().setName(_Interface._txt_descuento_detalle);
		//frame.setTitle("Carga de Articulos de Comprobantes de Compras Digital - "+this.getValueParameter(_parametros.iduser));
		frame.get_txt_fecha().setName(_Interface._txt_fecha);
		frame.get_btn_fecha().setActionCommand(_Interface._btn_buscar_fecha);
		frame.get_txt_sucursal().setName(_Interface._txt_sucursal);
		frame.get_txt_numero().setName(_Interface._txt_numero);
		frame.get_chk_seleccionar().setName(_Interface._chk_seleccionar);
		frame.get_chk_seleccionar_pep().setName(_Interface._chk_seleccionar_pep);
		frame.get_btn_importar_pep_items().setActionCommand(_Interface._btn_importar_pep_items);
		frame.get_btn_cargar_imagen().setActionCommand(_Interface._btn_abrir);
		frame.get_btn_scan().setActionCommand(_Interface._btn_scan);
		frame.get_btn_eliminar_foto().setActionCommand(_Interface._btn_eliminar_imagen);
		frame.get_btn_inicio().setActionCommand(_Interface._btn_primero);
		frame.get_btn_end().setActionCommand(_Interface._btn_ultimo);
		frame.get_list_tc().setName(_Interface._list_tc);
		frame.get_btn_eliminar().setActionCommand(_Interface._btn_eliminar_cpte);
		frame.get_btn_scan().setActionCommand(_Interface._btn_scan);
		
	}
	private void load_combobox_options(JComboBox cb){
		cb.removeAllItems();
		cb.addItem("NUNCA");
		cb.addItem("SIEMPRE");
		cb.addItem("PREGUNTAR");
		cb.setFont(new Font("Dialog", Font.BOLD, 10));
		cb.setSelectedIndex(2);

	}
	
	public void init(){
		
		super.init();
		_Logic logic=(_Logic) _logic;
		_Frame frame=(_Frame) _frame;
		String cuenta=(String)this.getValueParameter(aplicacion.compras.carga.items.interfaces._parametros.cuenta);
		String tc=(String)this.getValueParameter(aplicacion.compras.carga.items.interfaces._parametros.tc);
		String idcomprobante=(String)this.getValueParameter(aplicacion.compras.carga.items.interfaces._parametros.idcomprobante);
		logic.init();
		logic.centrar();
		logic.initialize_Vendedor();
		logic.cargarTipoTc();
		logic.cargarLetras();
		logic.initial_focus();
		if (cuenta.compareTo("")!=0 & tc.compareTo("")!=0 & idcomprobante.compareTo("")!=0){
			frame.get_txt_idproveedor().setText(cuenta);
			logic._evaluar_proveedor(frame.get_txt_idproveedor(), false);
			frame.get_txt_idcomprobante().setText(idcomprobante);
			logic.select_tc(tc);
			logic._evaluar_idcomprobante(frame.get_txt_idcomprobante(), false);
		}
	}
}

