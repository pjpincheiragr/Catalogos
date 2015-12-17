package aplicacion.sistema.configuracion.events;

import java.awt.event.ActionEvent;

import aplicacion.sistema.configuracion.interfaces._Interface;
import aplicacion.sistema.configuracion.logic._Logic;
import aplicacion.modelo.events._ActionListenerHandler;

public class ActionListenerHandler extends _ActionListenerHandler {
	
	public void procesarEvento(ActionEvent event){
		_Logic logic=(_Logic) this._logic;
		if (event.getActionCommand()==_Interface._btn_cancelar){
			logic.cancelar();
		}
		if (event.getActionCommand()==_Interface._btn_cargar){
			logic.cargar_parametros();
		}
		if (event.getActionCommand()==_Interface._btn_salir){
			logic.exit();
			
		}
	}
	
	


}