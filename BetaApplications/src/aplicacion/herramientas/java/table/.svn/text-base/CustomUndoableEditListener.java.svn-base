package aplicacion.herramientas.java.table;

import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

public class CustomUndoableEditListener implements UndoableEditListener {
	private UndoManager undo = null;
	public void undoableEditHappened(UndoableEditEvent evt) {
        undo.addEdit(evt.getEdit());
    }
	public void setUndoManager(UndoManager undo){
		this.undo=undo;
	}
}
