package aplicacion.flota.trabajo.events;

import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;

import aplicacion.sistema.aplicacion2.interfaces._Interface;
import aplicacion.sistema.aplicacion2.logic._Logic;
import aplicacion.modelo.events._DropTargetListenerHandler;

public class DropTargetListenerHandler extends _DropTargetListenerHandler {
	/*
	 * CAMBIAR EL IMPORT Y COPIAR EL METODO PROCESS DND EN LA LOGICA
	 * (non-Javadoc)
	 * @see aplicacion.modelo.events.ExceptionHandler#procesar(java.awt.dnd.DropTargetDropEvent)
	 */
	
	public void procesar(DropTargetDropEvent dtde){
		System.out.println("Drag And Drop Event "+dtde.getDropAction());
		_Logic logic=(_Logic) this._logic;
		logic.processDND(dtde);
	}
			


}