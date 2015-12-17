package aplicacion.herramientas.java.ejemplo.events;

import java.awt.event.ActionEvent;

import aplicacion.herramientas.java.ejemplo.interfaces._Interface;
import aplicacion.herramientas.java.ejemplo.logic._Logic;
import aplicacion.modelo.events._ActionListenerHandler;

public class ActionListenerHandler extends _ActionListenerHandler {

	public void procesarEvento(ActionEvent e){
		_Logic _logic=(_Logic) this._logic;
		
		if (e.getActionCommand()==_Interface._btn_salir){
			_logic.exit();
		}
		if (e.getActionCommand()==_Interface._btn_empezar){
			_logic.EjecutarTareaParalelaConMonitoreo();
		}	
		if (e.getActionCommand()==_Interface._btn_cancelar){
			_logic.cancelar();
		}	
	}
	
}
