package aplicacion.modelo.events;

import java.awt.dnd.DragSourceDragEvent;//import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DragSourceDropEvent;//import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DragSourceEvent;//import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DragSourceListener; //import java.awt.dnd.DropTargetListener;

public class _DragSourceListener implements DragSourceListener {
	public _DragSourceListenerHandler _handler=null;
	
	public void setEventHandler(_DragSourceListenerHandler handler){
		this._handler=handler;
	}
	
	public void drop (DragSourceDropEvent dtde) {
		this._handler.procesar(dtde);
	}
	public void drag (DragSourceDragEvent dtde) {
		this._handler.procesar(dtde);
	}
	public void dragEnter (DragSourceDragEvent dtde) {}

	public void dragExit (DragSourceEvent dte) {}

	public void dragOver (DragSourceDragEvent dtde) {}

	// drop( ) method listed below

	
	
	public void dragDropEnd (DragSourceDropEvent dtde) {}

	@Override
	public void dropActionChanged(DragSourceDragEvent dsde) {
		// TODO Auto-generated method stub
		
	}
}
