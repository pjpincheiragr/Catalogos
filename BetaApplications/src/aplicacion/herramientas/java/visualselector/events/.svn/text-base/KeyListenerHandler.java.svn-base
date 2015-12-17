package aplicacion.herramientas.java.visualselector.events;
import java.awt.event.KeyEvent;

import aplicacion.herramientas.java.visualselector.gui.*;
import aplicacion.herramientas.java.visualselector.logic.*;
import aplicacion.modelo.events._KeyListenerHandler;

import javax.swing.JTable;

public class KeyListenerHandler extends _KeyListenerHandler{
	public void procesarEvento(KeyEvent event){
		//System.out.println("Evento de teclado en visualselector "+event.getID());
		_Logic _logic=(_Logic) this._logic;
		if (event.getSource() instanceof JTable){
			if (event.getKeyCode()==KeyEvent.VK_ENTER){
				JTable table=(JTable) event.getSource();
				int row=table.getSelectedRow();
				_logic.close(row);	
			}
			if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
				JTable table=(JTable) event.getSource();
				int row=table.getSelectedRow();
				_logic.exit();	
			}
		}
	}
}
