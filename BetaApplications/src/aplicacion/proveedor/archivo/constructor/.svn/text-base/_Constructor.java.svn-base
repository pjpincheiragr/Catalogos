package aplicacion.proveedor.archivo.constructor;
import java.awt.Font;

import aplicacion.herramientas.java.visualfx.Transformer;
import aplicacion.modelo.constructor.Constructor;
import aplicacion.proveedor.archivo.gui._Frame;
import aplicacion.proveedor.archivo.interfaces._Interface;
import aplicacion.proveedor.archivo.interfaces._Parametros;
import aplicacion.proveedor.archivo.logic._Data;
import aplicacion.proveedor.archivo.logic._Logic;
import aplicacion.proveedor.archivo.events.WindowAdapterHandler;
import aplicacion.proveedor.archivo.events.ActionListenerHandler;
import aplicacion.proveedor.archivo.events.KeyListenerHandler;
import javax.swing.JComboBox;
public class _Constructor extends Constructor{

	protected void initialize_frame(){
		_frame=new _Frame();
	}
	
	public void initialize_components(){
		_Frame _frame=(_Frame) this._frame;
		_frame.setResizable(false);
		_frame.get_txt_idprovincia().setName(_Interface._txt_cod_provincia);
		_frame.get_txt_cond_iva().setName(_Interface._txt_cond_iva);
		_frame.get_txt_cond_venta().setName(_Interface._txt_cond_venta);
		_frame.get_txt_condicion_detalle().setName(_Interface._txt_condicion_detalle);
		_frame.get_txt_condicion_venta_detalle().setName(_Interface._txt_condicion_venta_detalle);
		_frame.get_txt_contacto().setName(_Interface._txt_contacto);
		_frame.get_txt_cuit().setName(_Interface._txt_cuit);
		_frame.get_txt_debe().setName(_Interface._txt_debe);
		_frame.get_txt_domicilio().setName(_Interface._txt_domicilio);
		_frame.get_txt_fax().setName(_Interface._txt_fax);
		_frame.get_txt_haber().setName(_Interface._txt_haber);
		_frame.get_txt_localidad().setName(_Interface._txt_localidad);
		_frame.get_txt_numero().setName(_Interface._txt_numero);
		_frame.get_txt_observaciones().setName(_Interface._txt_observaciones);
		
		_frame.get_txt_idproveedor().setName(_Interface._txt_idproveedor);
		
		
		
		
		_frame.get_btn_buscar_proveedor().setActionCommand(_Interface._btn_buscar_proveedor);
		
		_frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		
		_frame.get_btn_pago().setActionCommand(_Interface._btn_pago);
		
		_frame.get_btn_grabar().setActionCommand(_Interface._btn_grabar);
		_frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		_frame.get_btn_error().setActionCommand(_Interface._btn_error);
		_frame.get_btn_rotulo().setActionCommand(_Interface._btn_rotulo);
		_frame.get_btn_actualizar_saldo().setActionCommand(_Interface._btn_actualizar_saldo);
		_frame.get_btn_analitico().setActionCommand(_Interface._btn_analitico);
		_frame.get_btn_reporte().setActionCommand(_Interface._btn_reporte);
		//esto es un caso especial. no le agrega el action listener automaticamente.
		_frame.get_btn_actualizar_saldo().addActionListener(this.getActionListener());
		_frame.get_txt_idtransporte().setName(_Interface._txt_idtransporte);
		load_combobox_options(_frame.get_imprime_etiquetas());
		load_combobox_options(_frame.get_permite_articulos());
		load_combobox_options(_frame.get_requiere_ingresos_brutos());
		load_combobox_options(_frame.get_requiere_iva10());
		load_combobox_options(_frame.get_requiere_iva21());

		load_combobox_options(_frame.get_requiere_iva27());
		load_combobox_options(_frame.get_requiere_neto_no_gravado());
		load_combobox_options(_frame.get_requiere_percepcion_ganancias());
		load_combobox_options(_frame.get_requiere_percepcion_iva());
		load_combobox_options(_frame.get_requiere_rg3337());
		load_combobox_options(_frame.get_actualiza_precios());
		load_combobox_options(_frame.get_requiere_remitos());
		load_combobox_options(_frame.get_requiere_impuestos_internos());
		

	}
	
	private void load_combobox_options(JComboBox cb){
		cb.removeAllItems();
		cb.addItem("NUNCA");
		cb.addItem("SIEMPRE");
		cb.addItem("PREGUNTAR");
		cb.setFont(new Font("Dialog", Font.BOLD, 10));

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
		
		
		String idproveedor=(String)this.getValueParameter(_Parametros.idproveedor);
		_Frame _frame=(_Frame) this._frame;
		
		
		

		_frame.setVisible(true);
		_Logic _logic=(_Logic) this._logic;
		_logic.centrar();
		_logic.initialize_proveedor();
		_logic.initialize_Transporte();
		_logic.focus();
		
		_frame.get_txt_idproveedor().setText(idproveedor);
		if (idproveedor.compareTo("")!=0){
		  _logic.evaluarProveedor(_frame.get_txt_idproveedor());	
		}
		
		
		
		
		
	}
}
