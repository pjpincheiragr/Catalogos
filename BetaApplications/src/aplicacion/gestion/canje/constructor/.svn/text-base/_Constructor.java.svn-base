/**
 * Constructor de la aplicacion de Canje de Efectivo/Cheques
 * Autor: Agustin Wisky 
 * ultima modificacion: 02/09/2009
 * version: 1
 * la version 1 cumple con el modelo de 7 capas.
 * No tiene incluido hibernate ni xml para el almacenamiento de las consultas.
 * pero esta 100% comentada
 * Esta Aplicacion sirve para cambiar cheques por efectivo y viceversa.
 * Hace el asiento correspondiente en la caja seleccionada y da entrada o salida a cada medio
 * situado en ingreso o egreso dentro de la interfaz.
 */

package aplicacion.gestion.canje.constructor;
import aplicacion.modelo.constructor.Constructor;
import aplicacion.gestion.canje.events.*;
import aplicacion.gestion.canje.gui.*;
import aplicacion.gestion.canje.logic.*;
import aplicacion.gestion.canje.interfaces.*;
import aplicacion.gestion.canje.interfaces._Parametros;


public class _Constructor extends Constructor{
	
	
		
	public _Constructor(){
	
	}
	/**
	 * Metodo para inicializar el frame de la aplicacion y hacer un override de la clase Constructor
	 */
	protected void initialize_frame(){
		_frame=new _Frame();
	}
	/**
	 * Metodo para inicializar la logica de la aplicacion y hacer un override de la clase Constructor
	 */
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
	
	/**
	 * Metodo(override) para inicializar los componentes de la interfaz de usuario
	 * Aca se indica los nombres de los componentes para que los manejadores de eventos
	 * dentro del paquete events sepan de que objeto provienen los eventos.
	 * Para poder seleccionar que metodo de la logica ejecutar para resolver ese evento
	 */
	public void initialize_components(){
		
		_Frame _frame=(_Frame) this._frame;
		_frame.setResizable(false);
		
		
		_frame.get_txt_idPago().setName(_Interface._txt_idcanje);
		_frame.get_list_cajas().setName(_Interface._list_cajas);
		
		_frame.get_txt_concepto().setName(_Interface._txt_concepto);
		
		_frame.get_txt_fecha().setName(_Interface._txt_fecha);
		
		_frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		
		_frame.get_btn_anular().setActionCommand(_Interface._btn_anular);
		_frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		_frame.get_btn_error().setActionCommand(_Interface._btn_error);
		
		_frame.get_btn_grabar().setActionCommand(_Interface._btn_grabar);
		
		_frame.get_btn_buscar_pago().setActionCommand(_Interface._btn_buscar_canje);
		
		
		
		_frame.get_btn_fecha().setActionCommand(_Interface._btn_fecha);
		
		
	}
	
	
	
	public void init(){
		super.init();
		
		_Logic _logic=(_Logic) this._logic;
		_logic.centrar();
		_logic.cargar_cajas();
		_logic.obtener_proximo_cpte();
		
		String idcaja=(String)this.getValueParameter(_Parametros.caja);
		String idcomprobante=(String)this.getValueParameter(_Parametros.idcomprobante);
		
		if (idcaja.compareTo("")!=0){
			_logic.evaluarCanjeId();
			_logic.setCaja(idcaja);
		}
		if (idcomprobante.compareTo("")!=0){
			_logic.setCaja(idcaja);
			_logic.load_Canje(idcomprobante);
		}
		
	}

	


	
}

