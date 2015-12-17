package aplicacion.sistema.aplicacion.events;

import java.awt.event.KeyEvent;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import aplicacion.sistema.aplicacion.interfaces._Interface;
import aplicacion.sistema.aplicacion.logic._Logic;
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
				if (event.getKeyCode()==KeyEvent.VK_DELETE){
					logic.delete(row);
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
			 if (tx.getName()==_Interface._txt_id){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 String valor=tx.getText();
					 logic.cargar_parametros(tx.getText());
				 }
				 if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					 logic.clean();
				 }
				 
			 }
			 
			 if (tx.getName()==_Interface._table_item_id){
					if (event.getKeyCode()==KeyEvent.VK_ENTER){
						logic.evaluar_tabla_id(tx, row);
					}
				}
			 if (tx.getName()==_Interface._table_item_label){
					if (event.getKeyCode()==KeyEvent.VK_ENTER){
						logic.evaluar_tabla_label(tx, row);
					}
				}
			 if (tx.getName()==_Interface._table_item_icono){
					if (event.getKeyCode()==KeyEvent.VK_ENTER){
						logic.evaluar_tabla_icono(tx, row);
					}
				}
			 if (tx.getName()==_Interface._table_item_area){
					if (event.getKeyCode()==KeyEvent.VK_ENTER){
						logic.evaluar_tabla_area(tx, row);
					}
				}
			 if (tx.getName()==_Interface._table_item_launcher){
					if (event.getKeyCode()==KeyEvent.VK_ENTER){
						logic.evaluar_tabla_launcher(tx, row);
					}
				}
			 
			 
		}
		
	}
	
}
