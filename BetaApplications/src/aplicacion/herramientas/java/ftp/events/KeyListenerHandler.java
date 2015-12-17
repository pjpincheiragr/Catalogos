package aplicacion.herramientas.java.ftp.events;

import java.awt.event.KeyEvent;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import aplicacion.herramientas.java.ftp.interfaces._Interface;
import aplicacion.herramientas.java.ftp.logic._Logic;
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
			
			if (tx.getName()==_Interface._txt_archivo){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					logic.evaluar_txt_archivo(tx);
				}
			}
			 
		}
		
	}
	
}
