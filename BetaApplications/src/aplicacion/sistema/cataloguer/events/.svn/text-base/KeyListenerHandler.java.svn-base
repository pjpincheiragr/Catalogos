package aplicacion.sistema.cataloguer.events;

import java.awt.event.KeyEvent;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import aplicacion.sistema.cataloguer.interfaces._Interface;
import aplicacion.sistema.cataloguer.logic._Logic;
import aplicacion.modelo.events._KeyListenerHandler;

public class KeyListenerHandler extends _KeyListenerHandler {
	
	public void procesar(KeyEvent event){
		
		_Logic logic=(_Logic) this._logic;
		
		JTable table=null;
		int row=-1;
		int col=-1;
		
		if (event.getSource() instanceof JTable){
			table=(JTable) event.getSource();
			row=table.getSelectedRow();
			col=table.getSelectedColumn();
			if (event.getKeyCode()==KeyEvent.VK_DELETE){
				logic.borrarRenglon(row);
			}
		}
		
		if (event.getSource() instanceof JTextField){
			JTextField tx=(JTextField) event.getSource();
			 if (tx.getParent() instanceof JTable){
					table=(JTable) tx.getParent();
					row=table.getSelectedRow();
					col=table.getSelectedColumn();
				}
			 
			 
			 if (tx.getName()==_Interface._txt_idquery){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluarQuery(tx);
				 }
				 if (event.getKeyCode()==KeyEvent.VK_F5){
					 logic.BuscarQuery(tx);
				 }
				 
			 } 
			 
			 if (tx.getName()==_Interface._table_filtros_clave){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluar_tabla_clave(tx, row);
				 }
			 }
			 
			 if (tx.getName()==_Interface._table_filtros_reemplazo){
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluar_tabla_reemplazo(tx, row);
				 }
			 }
		}
		
	}
	
}
