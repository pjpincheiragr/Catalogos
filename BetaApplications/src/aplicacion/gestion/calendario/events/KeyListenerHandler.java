package aplicacion.gestion.calendario.events;
import aplicacion.gestion.calendario.interfaces._Interface;
import aplicacion.gestion.calendario.logic._Logic;
import aplicacion.modelo.events.*;

import java.awt.event.KeyEvent;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.KeyStroke;

//import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;
public class KeyListenerHandler extends _KeyListenerHandler{

	public void procesar(KeyEvent event){
		_Logic logic=(_Logic) this._logic;
		if (event.getSource() instanceof JTable){
			JTable table=(JTable) event.getSource();
			int row=table.getSelectedRow();
			int col=table.getSelectedColumn();
			if (table.getName()==_Interface._table_dias){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					logic.make_fecha();
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					logic.exit();
				}
				if (event.getKeyCode()==KeyEvent.VK_LEFT){
					logic.move(row, col-1);
				}
				if (event.getKeyCode()==KeyEvent.VK_RIGHT){
					logic.move(row, col+1);
				}
				if (event.getKeyCode()==KeyEvent.VK_DOWN){
					logic.move(row+1, col);
				}
				if (event.getKeyCode()==KeyEvent.VK_UP){
					logic.move(row-1, col);
				}
			}
		}
		if (event.getSource() instanceof JTextField){
			
		}
	}
}
