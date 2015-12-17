package aplicacion.herramientas.java.hora.events;
import aplicacion.herramientas.java.hora.interfaces._Interface;
import aplicacion.herramientas.java.hora.logic._Logic;
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
			JTextField tx=(JTextField)event.getSource();
			if (tx.getName()==_Interface._txt_hora){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					logic.evaluate_hora(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_DOWN){
					logic.hora_down(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_UP){
					logic.hora_up(tx);
				}
			}
			if (tx.getName()==_Interface._txt_minuto){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					logic.evaluate_minuto(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_DOWN){
					logic.minuto_down(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_UP){
					logic.minuto_up(tx);
				}
			}
		}
	}
}
