package aplicacion.flota.provincia.events;

import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;

import aplicacion.sistema.aplicacion2.interfaces._Interface;
import aplicacion.sistema.aplicacion2.logic._Logic;
import aplicacion.modelo.events._DropTargetListenerHandler;

public class DropTargetListenerHandler extends _DropTargetListenerHandler {
	
	
	public void procesar(DropTargetDropEvent dtde){
		System.out.println("Drag And Drop Event "+dtde.getDropAction());
		_Logic logic=(_Logic) this._logic;
		logic.processDND(dtde);
	}
			


}