package aplicacion.catalogo.catalogo.events;

import java.awt.event.ActionEvent;

import aplicacion.catalogo.catalogo.interfaces._Interface;
import aplicacion.catalogo.catalogo.logic._Logic;
import aplicacion.modelo.events._ActionListenerHandler;

public class ActionListenerHandler extends _ActionListenerHandler {
	public void procesarEvento(ActionEvent event){
		_Logic logic=(_Logic) this._logic;
		if (event.getActionCommand()==_Interface._btn_salir){
			logic.exit();
		}
		if (event.getActionCommand()==_Interface._btn_eliminar){
			logic.eliminar();
		}
	}
}
