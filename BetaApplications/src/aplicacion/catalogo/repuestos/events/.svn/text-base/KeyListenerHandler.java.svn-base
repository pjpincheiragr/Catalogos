package aplicacion.catalogo.repuestos.events;

import java.awt.event.KeyEvent;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import aplicacion.catalogo.repuestos.interfaces._Interface;
import aplicacion.catalogo.repuestos.logic._Logic;
import aplicacion.modelo.events._KeyListenerHandler;

public class KeyListenerHandler extends _KeyListenerHandler {
	
	public void procesar(KeyEvent event){
		
		_Logic logic=(_Logic) this._logic;
		
		JTable table=null;
		int row=-1;
		int col=-1;
		

		if (event.getSource() instanceof JTable){
			table=(JTable)  event.getSource();
			row=table.getSelectedRow();
			col=table.getSelectedColumn();
			if (table.getName()==_Interface._table_tecnica){
				
			}
		}	
		if (event.getSource() instanceof JTextField){
			 JTextField tx=( JTextField) event.getSource();
			 if (tx.getParent() instanceof JTable){
					table=(JTable) tx.getParent();
					row=table.getSelectedRow();
					col=table.getSelectedColumn();
				}
			 if (tx.getName()==_Interface._table_tecnica_dato){
					if (event.getKeyCode()==KeyEvent.VK_ENTER){
						logic._evaluate_dato(tx, row, col);
					}
				}
			 
			 if (tx.getName()==_Interface._table_tecnica_nota){
					if (event.getKeyCode()==KeyEvent.VK_ENTER){
						logic._evaluate_nota(tx, row, col);
					}
				}
		}
		
	}
	
}
