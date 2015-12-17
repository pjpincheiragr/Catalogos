package aplicacion.cliente.archivo.constructor;
import aplicacion.modelo.constructor.Constructor;
import aplicacion.cliente.archivo.gui._Frame;
import aplicacion.cliente.archivo.interfaces._Interface;
import aplicacion.cliente.archivo.interfaces._Parametros;
import aplicacion.cliente.archivo.logic._Data;
import aplicacion.cliente.archivo.logic._Logic;
import aplicacion.cliente.archivo.events.WindowAdapterHandler;
import aplicacion.cliente.archivo.events.ActionListenerHandler;
import aplicacion.cliente.archivo.events.KeyListenerHandler;

public class _Constructor extends Constructor{

	protected void initialize_frame(){
		_frame=new _Frame();
	}
	
	public void initialize_components(){
		_Frame _frame=(_Frame) this._frame;
		_frame.setResizable(false);
		_frame.get_txt_proveedordescripcion().setName(_Interface._txt_clientedescripcion);
		_frame.get_txt_idprovincia().setName(_Interface._txt_cod_provincia);
		_frame.get_txt_idprovincia().addKeyListener(this.getKeyListener());
		_frame.get_txt_cond_iva().setName(_Interface._txt_cond_iva);
		_frame.get_txt_cond_iva().addKeyListener(this.getKeyListener());
		_frame.get_txt_cond_venta().setName(_Interface._txt_cond_venta);
		_frame.get_txt_cond_venta().addKeyListener(this.getKeyListener());
		_frame.get_txt_condicion_detalle().setName(_Interface._txt_condicion_detalle);
		_frame.get_txt_condicion_detalle().addKeyListener(this.getKeyListener());
		_frame.get_txt_condicion_venta_detalle().setName(_Interface._txt_condicion_venta_detalle);
		_frame.get_txt_condicion_venta_detalle().addKeyListener(this.getKeyListener());
		_frame.get_txt_contacto().setName(_Interface._txt_contacto);
		_frame.get_txt_contacto().addKeyListener(this.getKeyListener());
		_frame.get_txt_cuit().setName(_Interface._txt_cuit);
		_frame.get_txt_cuit().addKeyListener(this.getKeyListener());
		_frame.get_txt_debe().setName(_Interface._txt_debe);
		_frame.get_txt_debe().addKeyListener(this.getKeyListener());
		_frame.get_txt_domicilio().setName(_Interface._txt_domicilio);
		_frame.get_txt_domicilio().addKeyListener(this.getKeyListener());
		_frame.get_txt_fax().setName(_Interface._txt_fax);
		_frame.get_txt_fax().addKeyListener(this.getKeyListener());
		_frame.get_txt_haber().setName(_Interface._txt_haber);
		_frame.get_txt_haber().addKeyListener(this.getKeyListener());
		_frame.get_txt_localidad().setName(_Interface._txt_localidad);
		_frame.get_txt_localidad().addKeyListener(this.getKeyListener());
		_frame.get_txt_numero().setName(_Interface._txt_numero);
		_frame.get_txt_numero().addKeyListener(this.getKeyListener());
		_frame.get_txt_piso().setName(_Interface._txt_piso);
		_frame.get_txt_piso().addKeyListener(this.getKeyListener());
		_frame.get_txt_observaciones().setName(_Interface._txt_observaciones);
		_frame.get_txt_observaciones().addKeyListener(this.getKeyListener());
		_frame.get_txt_postal().setName(_Interface._txt_postal);
		_frame.get_txt_postal().addKeyListener(this.getKeyListener());
		_frame.get_txt_telefono().setName(_Interface._txt_telefono);
		_frame.get_txt_telefono().addKeyListener(this.getKeyListener());
		_frame.get_txt_fax().setName(_Interface._txt_fax);
		_frame.get_txt_fax().addKeyListener(this.getKeyListener());
		
		
		_frame.get_txt_idcliente().setName(_Interface._txt_idcliente);
		
		
		_frame.get_btn_buscar_cliente().setActionCommand(_Interface._btn_buscar_cliente);
		_frame.get_btn_reporte().setActionCommand(_Interface._btn_reporte);
		
		_frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		_frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		_frame.get_btn_error().setActionCommand(_Interface._btn_error);
		_frame.get_btn_cobranza().setActionCommand(_Interface._btn_cobranza);
		_frame.get_btn_grabar().setActionCommand(_Interface._btn_guardar);
		_frame.get_btn_actualizar_saldo().setActionCommand(_Interface._btn_actualizar_saldo);
		//_frame.get_btn_editar().setActionCommand(_Interface._btn_editar);
		_frame.get_btn_analitico().setActionCommand(_Interface._btn_analitico);
		//_frame.get_btn_editar().addActionListener(this.getActionListener());
		_frame.get_txt_limite_credito().setName(_Interface._txt_limite);
		_frame.get_txt_limite_credito().addKeyListener(this.getKeyListener());
		_frame.get_txt_descuento().setName(_Interface._txt_descuento);
		_frame.get_txt_descuento().addKeyListener(this.getKeyListener());
	}
	
	protected void initialize_logic(){
		_logic=new _Logic();
	}
	
	
	public void initialize_data(){
		_data=new _Data();
	}
	protected void initialize_windowadapter_handler(){
		_windowadapter_handler=new WindowAdapterHandler();
	}
	
	protected void initialize_actionlistener_handler(){
		_actionlistener_handler=new ActionListenerHandler();
	}
	
	protected void initialize_keylistener_handler(){
		_keylistener_handler=new KeyListenerHandler();
	}
	
	public void init(){
		super.init();
		
		String idproveedor=(String)this.getValueParameter(_Parametros.idcliente);
		_Frame _frame=(_Frame) this._frame;
		_Logic _logic=(_Logic) this._logic;
		_logic.initialize_cliente();
		_logic.block(true);
		_logic.centrar();
		_logic.focus();
		_frame.get_txt_idcliente().setText(idproveedor);
		if (idproveedor.compareTo("")!=0){
			_logic.evaluarCliente(_frame.get_txt_idcliente());	
		}
	}
}
