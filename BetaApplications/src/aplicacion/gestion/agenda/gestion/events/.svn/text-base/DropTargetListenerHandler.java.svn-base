package aplicacion.gestion.agenda.gestion.events;

import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;

import aplicacion.gestion.agenda.gestion.interfaces._Interface;
import aplicacion.gestion.agenda.gestion.logic._Logic;
import aplicacion.modelo.events._DropTargetListenerHandler;

public class DropTargetListenerHandler extends _DropTargetListenerHandler {
	
	
	public void procesar(DropTargetDropEvent dtde){
		System.out.println("Drop Event "+dtde.getDropAction()+" source:"+dtde.getSource());
		_Logic logic=(_Logic) this._logic;
		
		logic.processDND(dtde);
	}
			


}