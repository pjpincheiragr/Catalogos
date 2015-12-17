package aplicacion.inventario.linea.events;

import java.awt.event.KeyEvent;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import aplicacion.inventario.linea.interfaces._Interface;
import aplicacion.inventario.linea.logic._Logic;
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
			if (table.getName()==_Interface._table){
				if (event.getKeyCode()==KeyEvent.VK_E){
					if (event.isControlDown()){
						String idarticulo=table.getValueAt(row, 0).toString();
						logic.goMa_Articulos(idarticulo);
					}
				}
			}
		}	
		if (event.getSource() instanceof JTextField){
			
			JTextField tx=(JTextField) event.getSource();
			 if (tx.getParent() instanceof JTable){
					table=(JTable) tx.getParent();
					row=table.getSelectedRow();
					col=table.getSelectedColumn();
				}
			 if (tx.getName()==_Interface._txt_idcontrol){
				 
				 
			 }
			 
			 if (tx.getName()==_Interface._table_control){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluate_control(tx, row);
				 }
				 
			 }
		}
		
	}
	
}
