package aplicacion.inventario.articulo.events;

import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;

import aplicacion.inventario.articulo.interfaces._Interface;
import aplicacion.inventario.articulo.logic._Logic;
import aplicacion.modelo.events._DropTargetListenerHandler;

public class DropTargetListenerHandler extends _DropTargetListenerHandler {
	
	
	public void procesar(DropTargetDropEvent dtde){
		System.out.println("Drag And Drop Event "+dtde.getDropAction());
		_Logic logic=(_Logic) this._logic;
		logic.processDND(dtde);
	}
			


}