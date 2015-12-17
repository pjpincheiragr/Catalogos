package aplicacion.flota.trabajo.events;

import java.awt.event.ActionEvent;
import aplicacion.flota.trabajo.interfaces._Interface;
import aplicacion.flota.trabajo.logic._Logic;
import aplicacion.modelo.events._ActionListenerHandler;

public class ActionListenerHandler extends _ActionListenerHandler {
	
	public void procesarEvento(ActionEvent event){
		_Logic logic=(_Logic) this._logic;
		
	
		if(event.getActionCommand() == _Interface._btn_buscar){
			//logic.buscar();
		}
		if(event.getActionCommand() == _Interface._btn_buscar_unidad){
			//logic.buscar();
		}
		if(event.getActionCommand() == _Interface._btn_calendario){
			//logic.buscar();
		}
		if(event.getActionCommand() == _Interface._btn_calendario1){
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
		if(event.getActionCommand() == _Interface._btn_nueva){
			//logic.buscar();
		}
		if(event.getActionCommand() == _Interface._btn_salir){
			//logic.buscar();
		}
		
	}
	
}