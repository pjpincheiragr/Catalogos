package aplicacion.herramientas.java.ftp.events;

import java.awt.event.ActionEvent;

import aplicacion.herramientas.java.ftp.interfaces._Interface;
import aplicacion.herramientas.java.ftp.logic._Logic;
import aplicacion.modelo.events._ActionListenerHandler;

public class ActionListenerHandler extends _ActionListenerHandler {
	
	public void procesarEvento(ActionEvent event){
		_Logic logic=(_Logic) this._logic;
		if (event.getActionCommand()==_Interface._btn_cancelar){
			logic.cancelar();
		}
		if (event.getActionCommand()==_Interface._btn_cargar){
		
		}
		if (event.getActionCommand()==_Interface._btn_salir){
			logic.exit();
			
		}
	}
	
	


}