package aplicacion.compras.carga.pedido.events;
import java.awt.event.WindowEvent;

import aplicacion.compras.carga.pedido.interfaces._Interface;
import aplicacion.compras.carga.pedido.logic._Logic;
import aplicacion.modelo.events._WindowAdapterHandler;

public class WindowAdapterHandler extends _WindowAdapterHandler{
	
	public void procesarEvento(WindowEvent we){
		if (we.getID()==WindowEvent.WINDOW_CLOSING){
			_Logic _logic=(_Logic) this._logic;
			if (we.getWindow().getName()==_Interface._frame){
				
				_logic.exit();	
			}
			if (we.getWindow().getName()==_Interface._email){
				_logic.dispose_email();
			}
			
			if (we.getWindow().getName()==_Interface._linea){
				_logic.cerrar_lineas();
			}
				
		}
	}
}
