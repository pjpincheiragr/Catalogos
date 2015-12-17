package aplicacion.sistema.autorizacion.events;

import java.awt.event.KeyEvent;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import aplicacion.sistema.autorizacion.interfaces._Interface;
import aplicacion.sistema.autorizacion.logic._Logic;
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
			
			if (tx.getName()==_Interface._txt_idusuario){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					logic.cargar_parametros();
				}
			}
			 
		}
		
	}
	
}
