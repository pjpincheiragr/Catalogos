package aplicacion.contabilidad.recodificacion.events;

import java.awt.event.KeyEvent;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import aplicacion.sistema.analizador.interfaces._Interface;
import aplicacion.sistema.analizador.logic._Logic;
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
			
			
			 
		}
		if (event.getSource() instanceof JTextArea){
			JTextArea tx=(JTextArea) event.getSource();
			if (tx.getName()==_Interface._txt_area){
				if (event.getKeyCode()==KeyEvent.VK_F5){
					logic.goCargar(tx);
				}
			}
		}
		
	}
	
}
