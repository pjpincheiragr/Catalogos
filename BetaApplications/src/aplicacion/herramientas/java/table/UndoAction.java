package aplicacion.herramientas.java.table;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
public class UndoAction extends AbstractAction{
	private UndoManager undo = null;
	
	public UndoAction(String action){
		super(action);
	}
	public void setUndoManager(UndoManager undo){
		this.undo=undo;
	}
	
	 public void actionPerformed(ActionEvent evt) {
         try {
             if (undo.canUndo()) {
                 undo.undo();
             }
         } catch (CannotUndoException e) {
         }
     }
}
