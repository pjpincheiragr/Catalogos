package aplicacion.ventas.pedido.constructor;

import aplicacion.modelo.constructor.Constructor;
import aplicacion.ventas.pedido.events.ActionListenerHandler;
import aplicacion.ventas.pedido.events.ItemListenerHandler;
import aplicacion.ventas.pedido.events.KeyListenerHandler;
import aplicacion.ventas.pedido.events.MouseListenerHandler;
import aplicacion.ventas.pedido.events.WindowAdapterHandler;
import aplicacion.ventas.pedido.gui._Frame;
import aplicacion.ventas.pedido.interfaces._Interface;
import aplicacion.ventas.pedido.interfaces._parametros;
import aplicacion.ventas.pedido.logic._Data;
import aplicacion.ventas.pedido.logic._Logic;

import aplicacion.herramientas.conexion.*;
public class _Constructor extends Constructor {
	
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
		frame.setName(_Interface._frame);
		frame.get_txt_idcliente().setName(_Interface._txt_idcliente);
		frame.get_txt_idpedido().setName(_Interface._txt_idpedido);
		frame.get_txt_idvendedor().setName(_Interface._txt_idvendedor);
		frame.get_txt_iddeposito().setName(_Interface._txt_iddeposito);
		frame.get_txt_fecha().setName(_Interface._txt_fecha);
		frame.get_txt_pedido_descripcion().setName(_Interface._txt_pedido_descripcion);
		frame.get_txt_cliente_descripcion().setName(_Interface._txt_cliente_descripcion);
		frame.get_txt_informacion().setName(_Interface._txt_informacion);
		frame.get_txt_fecha_envio().setName(_Interface._txt_fecha_envio);
		frame.get_txt_guia().setName(_Interface._txt_guia);
		frame.get_txt_informacion().addKeyListener(this.getKeyListener());
		frame.get_txt_idtransporte().setName(_Interface._txt_idtransporte);
		frame.get_txt_idprovincia().setName(_Interface._txt_idprovincia);
		frame.get_txt_domicilio().setName(_Interface._txt_domicilio);
		frame.get_txt_cpostal().setName(_Interface._txt_cpostal);
		frame.get_txt_idciudad().setName(_Interface._txt_idciudad);
		frame.get_txt_negocio().setName(_Interface._txt_negocio);
		frame.get_txt_numero().setName(_Interface._txt_numero);
		frame.get_txt_tel().setName(_Interface._txt_telefono);
		frame.get_btn_buscar_cliente().setActionCommand(_Interface._btn_buscar_cliente);
		frame.get_btn_cargar_cliente().setActionCommand(_Interface._btn_cargar_cliente);
		frame.get_btn_editar_cliente().setActionCommand(_Interface._btn_editar_cliente);
		frame.get_btn_editar_transporte().setActionCommand(_Interface._btn_editar_transporte);
		frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		frame.get_btn_error().setActionCommand(_Interface._btn_error);
		frame.get_btn_importar_equivalencia().setActionCommand(_Interface._btn_importar_equivalencias);
		frame.get_btn_importar().setActionCommand(_Interface._btn_importar);
		frame.get_btn_play().setActionCommand(_Interface._btn_play);
		frame.get_btn_buscar_transporte().setActionCommand(_Interface._btn_buscar_transporte);
		frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		frame.get_btn_guardar().setActionCommand(_Interface._btn_guardar);
		frame.get_btn_buscar_fecha().setActionCommand(_Interface._btn_buscar_fecha);
		frame.get_btn_buscar_pedido().setActionCommand(_Interface._btn_buscar_pedido);
		frame.get_btn_cargar_pedido().setActionCommand(_Interface._btn_cargar_pedido);
		frame.get_btn_buscar_vendedor().setActionCommand(_Interface._btn_buscar_vendedor);
		frame.get_btn_buscar_deposito().setActionCommand(_Interface._btn_buscar_deposito);
		frame.get_btn_preparar().setActionCommand(_Interface._btn_preparar_pedido);
		frame.get_btn_remito().setActionCommand(_Interface._btn_remito);
		frame.get_btn_eliminar_remito().setActionCommand(_Interface._btn_eliminar_remito);
		frame.get_btn_buscar_fecha_envio().setActionCommand(_Interface._btn_buscar_fecha_envio);
		frame.get_btn_presupuesto().setActionCommand(_Interface._btn_presupuesto);
		frame.get_btn_eliminar().setActionCommand(_Interface._btn_eliminar);
		frame.get_btn_pedidoe().setActionCommand(_Interface._btn_pedidoe);
		frame.get_btn_envio().setActionCommand(_Interface._btn_envio);
		frame.get_btn_identificador().setActionCommand(_Interface._btn_imprimir_identificador);
		frame.get_chk_seleccionar().setName(_Interface._chk_seleccionar);
		frame.get_chk_costo().setName(_Interface._chk_costo);
		frame.get_chk_sobrescribir().setName(_Interface._chk_sobrescribir);
		
		frame.get_btn_identificador().setActionCommand(_Interface._btn_imprimir_identificador);
		frame.get_btn_nuevo().setActionCommand(_Interface._btn_nuevo);
		frame.get_btn_remitos_buscar().setActionCommand(_Interface._btn_buscar_remitos_generados);
		frame.get_btn_cargar_compras().setActionCommand(_Interface._btn_cargar_compras);
		frame.get_btn_copia().setActionCommand(_Interface._btn_copia);
		frame.get_btn_faltantes().setActionCommand(_Interface._btn_faltantes);
		frame.get_btn_transferir().setActionCommand(_Interface._btn_transferir);
		frame.get_btn_aviso().setActionCommand(_Interface._btn_aviso);
	}
	
	
	
	
	public void init(){
		super.init();
		
		_Logic _logic=(_Logic) this._logic;
		_Frame _frame=(_Frame) this._frame;
		_logic.centrar();
		_logic.initialize_Fecha();
		_logic.initialize_cliente();
		_logic.initialize_Vendedor();
		_logic.initialize_PDC();
		_logic.initialize_Negocio();
		_logic.initialize_Transporte();
		_logic.initialize_Provincia();
		_logic.initialize_Deposito();
		//_logic.setDefaultTab();
		
		String idpedido=(String)this.getParameter(_parametros._idpedido);
		String idcliente="";
		try {
			idcliente=(String)this.getParameter(_parametros._idcliente);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String automatico="";
		try {
			automatico=(String)this.getParameter(_parametros._automatico);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String idvendedor="";
		try {
			idvendedor=(String)this.getParameter(_parametros._idvendedor);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean nuevo=true;
		if (idpedido!=null){
			if (idpedido.compareTo("")!=0){
				_logic.cargar_pedido(idpedido);
				if (idvendedor!=null){
					if (idvendedor.compareTo("")!=0){
						String owner=_logic.getData().getOwner(idpedido);
						if (owner.compareTo(idvendedor)!=0){
							_logic.block();
						}
					}	
				}
				
				nuevo=false;
			}	
		}
		if (nuevo){
				_logic.nuevo();	
				if (idcliente!=null){
					if (idcliente.compareTo("")!=0){
						_frame.get_txt_idcliente().setText(idcliente);
						_logic.evaluarCliente(_frame.get_txt_idcliente());
					}	
				}
				
			
		}
		
		
	}

}
