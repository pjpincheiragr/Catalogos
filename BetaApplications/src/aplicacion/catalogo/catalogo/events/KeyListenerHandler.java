package aplicacion.catalogo.catalogo.events;

import javax.swing.JTable;
import java.awt.event.KeyEvent;
import aplicacion.catalogo.catalogo.interfaces._Interface;
import aplicacion.catalogo.catalogo.logic._Logic;
import aplicacion.modelo.events._KeyListenerHandler;

public class KeyListenerHandler extends _KeyListenerHandler {
	
	public void procesar(KeyEvent event){
		_Logic logic=(_Logic) this._logic;
		if (event.getSource() instanceof JTable){
			JTable table=(JTable)  event.getSource();
			int row=table.getSelectedRow();
			int col=table.getSelectedColumn();
			
		}	
	}
	
}
