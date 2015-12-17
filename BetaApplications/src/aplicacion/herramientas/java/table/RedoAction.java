package aplicacion.herramientas.java.table;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
public class RedoAction extends AbstractAction{
	private UndoManager undo = null;
	
	public RedoAction(String action){
		super(action);
	}
	public void setUndoManager(UndoManager undo){
		this.undo=undo;
	}
	
	public void actionPerformed(ActionEvent evt) {
        try {
            if (undo.canRedo()) {
                undo.redo();
            }
        } catch (CannotRedoException e) {
        }
    }
}
