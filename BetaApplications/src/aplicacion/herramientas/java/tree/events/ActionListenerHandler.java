package aplicacion.herramientas.java.tree.events;

import java.awt.event.ActionEvent;

import aplicacion.herramientas.java.tree.interfaces._Interface;
import aplicacion.herramientas.java.tree.logic._Logic;
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
	}
	
	


}