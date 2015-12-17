/**
 * Aplicacion Para verificar los cheques en cuenta en la caja seleccionada
 * Autor: Agustin Wisky
 * ultima modificacion: 02-09-2009
 * version: 1
 * 
 */
package aplicacion.ventas.transferencia.constructor;

import aplicacion.modelo.constructor.Constructor;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.ventas.transferencia.events.*;
import aplicacion.ventas.transferencia.gui.*;
import aplicacion.ventas.transferencia.logic.*;
import aplicacion.ventas.transferencia.interfaces._Interface;
import aplicacion.ventas.transferencia.interfaces._Parametros;

public class _Constructor extends Constructor{
		
	public _Constructor(){
	
	}
	
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
	
	public void initialize_components(){
		_Frame _frame=(_Frame) this._frame;
		_frame.setResizable(false);
		_frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		
		_frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		_frame.get_btn_eliminar().setActionCommand(_Interface._btn_eliminar);
		_frame.get_btn_guardar().setActionCommand(_Interface._btn_transferir);
		_frame.get_txt_fecha().setName(_Interface._txt_fecha);
		_frame.get_txt_idcreador().setName(_Interface._txt_idvendedor_origen);
		_frame.get_txt_idvendedor().setName(_Interface._txt_idvendedor_destino);
		_frame.get_lst_posponer().setName(_Interface._lst_posponer_remitente);
		
	}
	
	
	
	public void init(){
		
		super.init();
		_Frame _frame=(_Frame) this._frame;
		_Logic _logic=(_Logic) this._logic;
		
		
		aplicacion.ventas.pedido.constructor._Constructor pedido=null;
		try {
			pedido=(aplicacion.ventas.pedido.constructor._Constructor)this.getValueParameter(_Parametros.pedido);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		if (pedido!=null){
			_logic.setPedido(pedido);
		}
		aplicacion.ventas.gestion2.constructor._Constructor gestion=null;
		try {
			gestion=(aplicacion.ventas.gestion2.constructor._Constructor)this.getValueParameter(_Parametros.gestion2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		if (gestion!=null){
			_logic.setGestion2(gestion);
		}
		_logic.initialize_Vendedor();
		_logic.initialize_Hora();
		_logic.cargar_posponer();
		_logic.centrar();
		_logic.init();
		
		
		
		
		
		
	}

	


	
}

