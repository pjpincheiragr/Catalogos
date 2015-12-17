package aplicacion.sistema.error.events;

import java.awt.event.KeyEvent;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import aplicacion.sistema.error.interfaces._Interface;
import aplicacion.sistema.error.logic._Logic;
import aplicacion.modelo.events._KeyListenerHandler;

public class KeyListenerHandler extends _KeyListenerHandler {
	
	public void procesar(KeyEvent event){
		
		_Logic logic=(_Logic) this._logic;
		
		JTable table=null;
		int row=-1;
		int col=-1;
		

		if (event.getSource() instanceof JTextField){
			JTextField tx=(JTextField) event.getSource();
			 
			 if (tx.getName()==_Interface._txt_asunto){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluate_asunto(tx);
				 }
				 
				 
			 }
			 			 
		}
		
	}
	
}
