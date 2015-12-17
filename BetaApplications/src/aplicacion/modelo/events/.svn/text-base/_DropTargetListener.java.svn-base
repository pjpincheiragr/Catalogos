package aplicacion.modelo.events;

import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;

public class _DropTargetListener implements DropTargetListener {
	public _DropTargetListenerHandler _handler=null;
	
	public void setEventHandler(_DropTargetListenerHandler handler){
		this._handler=handler;
	}
	
	public void drop (DropTargetDropEvent dtde) {
		this._handler.procesar(dtde);
	}
	public void dragEnter (DropTargetDragEvent dtde) {}

	public void dragExit (DropTargetEvent dte) {}

	public void dragOver (DropTargetDragEvent dtde) {}

	// drop( ) method listed below

	public void dropActionChanged (DropTargetDragEvent dtde) {}
}
