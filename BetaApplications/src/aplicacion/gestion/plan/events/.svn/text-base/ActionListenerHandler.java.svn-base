package aplicacion.gestion.plan.events;

import java.awt.event.ActionEvent;

import aplicacion.catalogo.aplicacion.interfaces._Interface;
import aplicacion.catalogo.aplicacion.logic._Logic;
import aplicacion.modelo.events._ActionListenerHandler;

public class ActionListenerHandler extends _ActionListenerHandler {
	
	public void procesarEvento(ActionEvent event){
		_Logic logic=(_Logic) this._logic;
		if (event.getActionCommand()==_Interface._btn_editar){
			logic.renameCurrentNode();
		}
		if (event.getActionCommand()==_Interface._btn_borrar){
			//
			
			logic.removeCurrentNode();
		}
		if (event.getActionCommand()==_Interface._btn_agregar){
			
			logic.addObject();
		}
		if (event.getActionCommand()==_Interface._btn_salir){
			logic.exit();
		}
	}
	
	


}