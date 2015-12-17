package aplicacion.flota.marca.events;

import java.awt.event.ActionEvent;

import aplicacion.flota.marca.interfaces._Interface;
import aplicacion.flota.marca.logic._Logic;
import aplicacion.modelo.events._ActionListenerHandler;

public class ActionListenerHandler extends _ActionListenerHandler {
	
	public void procesarEvento(ActionEvent event){
		_Logic logic=(_Logic) this._logic;
		

		if(event.getActionCommand() == _Interface._btn_cancelar){
			//logic.buscar();
		}
		if(event.getActionCommand() == _Interface._btn_buscar){
			//logic.buscar();
		}
		if(event.getActionCommand() == _Interface._btn_eliminarModelo){
			//logic.buscar();
		}
		if(event.getActionCommand() == _Interface._btn_eliminar){
			//logic.buscar();
		}
		if(event.getActionCommand() == _Interface._btn_guardar){
			//logic.buscar();
		}
		if(event.getActionCommand() == _Interface._btn_nuevoModelo){
			//logic.buscar();
		}
		if(event.getActionCommand() == _Interface._btn_salir){
			//logic.buscar();
		}
		
	}
	
	


}