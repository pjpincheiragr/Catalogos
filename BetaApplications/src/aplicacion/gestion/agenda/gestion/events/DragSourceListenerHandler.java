package aplicacion.gestion.agenda.gestion.events;

import java.awt.dnd.DragSourceDragEvent;
import java.awt.event.ActionEvent;

import aplicacion.gestion.agenda.gestion.interfaces._Interface;
import aplicacion.gestion.agenda.gestion.logic._Logic;
import aplicacion.modelo.events._DragSourceListenerHandler;

public class DragSourceListenerHandler extends _DragSourceListenerHandler {
	
	
	public void procesar(DragSourceDragEvent dtde){
		_Logic logic=(_Logic) this._logic;
		dtde.getLocation().getClass();
		
	}
			


}