package aplicacion.sistema.error.events;

import java.awt.event.ActionEvent;

import aplicacion.sistema.error.interfaces._Interface;
import aplicacion.sistema.error.logic._Logic;
import aplicacion.modelo.events._ActionListenerHandler;

public class ActionListenerHandler extends _ActionListenerHandler {

	public void procesarEvento(ActionEvent event){
		_Logic logic=(_Logic) this._logic;
		if (event.getActionCommand()==_Interface._btn_enviar){
			logic.goEnviar();
			
		}
		if (event.getActionCommand()==_Interface._btn_salir){
			logic.exit();
			
		}
		if (event.getActionCommand()==_Interface._btn_guardar){
			logic.guardar();
			
		}
	}
}
