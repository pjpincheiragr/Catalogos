package aplicacion.ventas.gestion2.events;

import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;

import aplicacion.ventas.gestion2.interfaces._Interface;
import aplicacion.ventas.gestion2.logic._Logic;
import aplicacion.modelo.events._DropTargetListenerHandler;

public class DropTargetListenerHandler extends _DropTargetListenerHandler {
	
	
	public void procesar(DropTargetDropEvent dtde){
		System.out.println("Drag And Drop Event "+dtde.getDropAction());
		_Logic logic=(_Logic) this._logic;
		dtde.getLocation().getClass();
		logic.processDND(dtde);
	}
			


}