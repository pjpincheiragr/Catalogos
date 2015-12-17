package aplicacion.compras.pedidoe.events;
import java.awt.event.WindowEvent;

import aplicacion.compras.pedidoe.interfaces._Interface;
import aplicacion.compras.pedidoe.logic._Logic;
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
				
		}
	}
}
