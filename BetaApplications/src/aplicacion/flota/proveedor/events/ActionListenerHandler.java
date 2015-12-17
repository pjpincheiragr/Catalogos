package aplicacion.flota.proveedor.events;

import java.awt.event.ActionEvent;

import aplicacion.flota.proveedor.interfaces._Interface;
import aplicacion.flota.proveedor.logic._Logic;
import aplicacion.modelo.events._ActionListenerHandler;

public class ActionListenerHandler extends _ActionListenerHandler {
	
	public void procesarEvento(ActionEvent event){
		_Logic logic=(_Logic) this._logic;
		
		if(event.getActionCommand() == _Interface._btn_buscar){
			//logic.buscar();
		}
		if(event.getActionCommand() == _Interface._btn_cancelar){
			//logic.buscar();
		}
		if(event.getActionCommand() == _Interface._btn_eliminar){
			//logic.buscar();
		}
		if(event.getActionCommand() == _Interface._btn_guardar){
			//logic.buscar();
		}
		if(event.getActionCommand() == _Interface._btn_nuevo){
			//logic.buscar();
		}
		if(event.getActionCommand() == _Interface._btn_rename){
			//logic.buscar();
		}
		if(event.getActionCommand() == _Interface._btn_salir){
			//logic.buscar();
		}

	}
	
	


}