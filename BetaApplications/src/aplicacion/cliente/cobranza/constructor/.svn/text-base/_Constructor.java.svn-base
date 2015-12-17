package aplicacion.cliente.cobranza.constructor;
import aplicacion.modelo.constructor.Constructor;
import aplicacion.cliente.cobranza.gui._Frame;
import aplicacion.cliente.cobranza.interfaces._Parametros;
import aplicacion.cliente.cobranza.gui._Frame;
import aplicacion.cliente.cobranza.interfaces._Interface;
import aplicacion.cliente.cobranza.logic._Data;
import aplicacion.cliente.cobranza.logic._Logic;
import aplicacion.cliente.cobranza.events.*;




public class _Constructor extends Constructor{
	
	
		
	
	
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
	protected void initialize_mouselistener_handler(){
		_mouselistener_handler=new MouseListenerHandler();
	}
	protected void initialize_itemlistener_handler(){
		_itemlistener_handler=new ItemListenerHandler();
		
	}
	
	public void initialize_components(){
		_Frame _frame=(_Frame) this._frame;
		_frame.get_txt_idcliente().setName(_Interface._txt_idcliente);
		_frame.get_txt_fecha().setName(_Interface._txt_fecha);
		_frame.get_txt_idcobranza().setName(_Interface._txt_idcobranza);
		_frame.get_txt_credito_desde().setName(_Interface._txt_credito_desde);
		_frame.get_txt_credito_desde().setName(_Interface._txt_credito_hasta);
		_frame.get_txt_deuda_desde().setName(_Interface._txt_deuda_desde);
		_frame.get_txt_deuda_hasta().setName(_Interface._txt_deuda_hasta);
		
		_frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		_frame.get_btn_anular().setActionCommand(_Interface._btn_anular);
		_frame.get_btn_buscar().setActionCommand(_Interface._btn_buscar);
		_frame.get_btn_imprimir_cptes().setActionCommand(_Interface._btn_imprimir_cptes);
		_frame.get_btn_grabar().setActionCommand(_Interface._btn_grabar);
		_frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		_frame.get_btn_error().setActionCommand(_Interface._btn_error);
		_frame.get_btn_fecha().setActionCommand(_Interface._btn_fecha);
		_frame.get_btn_nuevo().setActionCommand(_Interface._btn_nuevo);
		_frame.get_btn_buscar_credito_desde().setActionCommand(_Interface._btn_credito_desde);
		_frame.get_btn_buscar_credito_hasta().setActionCommand(_Interface._btn_credito_hasta);
		_frame.get_btn_buscar_deuda_desde().setActionCommand(_Interface._btn_deuda_desde);
		_frame.get_btn_buscar_deuda_hasta().setActionCommand(_Interface._btn_deuda_hasta);
		_frame.get_btn_cargar_cliente().setActionCommand(_Interface._btn_cargar_cliente);
		_frame.get_btn_buscar_cliente().setActionCommand(_Interface._btn_buscar_cliente);
		_frame.get_chk_seleccionar_cpte().setName(_Interface._chk_seleccionar_cpte);
		_frame.get_chk_seleccionar_creditos().setName(_Interface._chk_seleccionar_creditos);
		_frame.get_btn_imprimir().setActionCommand(_Interface._btn_imprimir);
	}
	
	
	
	public void init(){
		super.init();
		_Logic _logic=(_Logic) this._logic;
		_logic.centrar();
		_logic.setInitialDate();
		_logic.initialize_Cobranza();
		_logic.initialize_cliente();
		_logic.obtener_proximo_cpte();
		_logic.cargar_modos();
		aplicacion.ventas.facturador.constructor._Constructor ventas=null;
		try {
			ventas=(aplicacion.ventas.facturador.constructor._Constructor)
			this.getValueParameter(_Parametros._ventas);
		}catch(Exception e){
			
		}
		String idcliente=(String)this.getValueParameter(_Parametros.idcliente);
		String idcobranza=(String)this.getValueParameter(_Parametros.idcobranza);
		String _cpte_fecha=(String)this.getValueParameter(_Parametros._cpte_fecha);
		String _cpte_tc=(String)this.getValueParameter(_Parametros._cpte_tc);
		String _cpte_id=(String)this.getValueParameter(_Parametros._cpte_id);
		String _cpte_importe=(String)this.getValueParameter(_Parametros._cpte_importe);
		boolean credito=true;
		try {
			credito=(Boolean)this.getValueParameter(_Parametros._credito);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		System.out.println("Error en cobranza logic! FVN NULLLLL!!!");
		if (idcobranza.compareTo("")!=0){
			if (_logic.existe(idcobranza)){
				_logic.load_Cobranza(idcobranza);
				_logic.focus();
			}else{
				_logic.error("La Cobranza "+idcobranza+" no se encuentra disponible");
				_logic.exit_command();
			}
			
		}else{
			if (idcliente.compareTo("")!=0){
				if (ventas==null){
					_logic.cargarCliente(idcliente);	
				}else {
					_cpte_importe=_cpte_importe.replaceAll(",", "");
					double imp=0.0;
					try {
						imp=new Double(_cpte_importe);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					_logic.setCredito(imp>=0);
					_logic.setVentas(ventas);
					_logic.evaluate_cobranza();
					_logic.cargarCliente(idcliente);
					
					if (imp<=0){
						imp=-imp;
					}
					_logic.agregarCpte(_cpte_fecha, _cpte_tc, _cpte_id, ""+ imp);
					
					_logic.focusOnMedios();
					
				}
				
			}else{
				_logic.focus();
			}
		}
		
		
		
		

		
		
		
	}


}

