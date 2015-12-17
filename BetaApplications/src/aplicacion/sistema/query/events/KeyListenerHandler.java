package aplicacion.sistema.query.events;

import java.awt.event.KeyEvent;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import aplicacion.sistema.query.interfaces._Interface;
import aplicacion.sistema.query.logic._Logic;
import aplicacion.modelo.events._KeyListenerHandler;

public class KeyListenerHandler extends _KeyListenerHandler {
	
	public void procesar(KeyEvent event){
		
		_Logic logic=(_Logic) this._logic;
		
		JTable table=null;
		int row=-1;
		int col=-1;
		

		
		if (event.getSource() instanceof JTextField){
			JTextField tx=(JTextField) event.getSource();
			 if (tx.getParent() instanceof JTable){
					table=(JTable) tx.getParent();
					row=table.getSelectedRow();
					col=table.getSelectedColumn();
				}
			 if (tx.getName()==_Interface._txt_idquery){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 String valor=tx.getText();
					 logic.evaluarQuery(tx);
				 }
				 if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					 logic.clean();
				 }
				 if (event.getKeyCode()==KeyEvent.VK_F5){
					 logic.BuscarQuery(tx);
				 }
			 }
			
			 if (tx.getName()==_Interface._txt_nombre){
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluate_nombre(tx);
				 }
				 
			 }
			 if (tx.getName()==_Interface._txt_tabla_nombre){
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluate_tabla_nombre(tx, col, row);
				 }
				 
			 }
			 if (tx.getName()==_Interface._txt_tabla_alias){
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluate_tabla_alias(tx, col, row);
				 }
			 }

			 if (tx.getName()==_Interface._txt_odbc){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluate_odbc(tx);
				 }
				 
			 }
			 
			 if (tx.getName()==_Interface._txt_tabla){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluate_tabla(tx);
				 }
				 
			 } 
		}
		
	}
	
}
