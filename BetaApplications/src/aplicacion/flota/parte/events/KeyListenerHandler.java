
package aplicacion.flota.parte.events;

import java.awt.event.KeyEvent;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import aplicacion.flota.parte.interfaces._Interface;
import aplicacion.flota.parte.logic._Logic;
import aplicacion.modelo.events._KeyListenerHandler;

public class KeyListenerHandler extends _KeyListenerHandler {
	
	public void procesar(KeyEvent event){
		
		_Logic logic=(_Logic) this._logic;
		
		JTable table=null;
		int row=-1;
		int col=-1;
		
		
//		if (event.getSource() instanceof JTextField){
//			JTextField tx=(JTextField) event.getSource();
//			if (tx.getParent() instanceof JTable){
//				table=(JTable) tx.getParent();
//				row=table.getSelectedRow();
//				col=table.getSelectedColumn();
//				}
//			 
//			if (tx.getName()==_Interface._txt_idaplicacion){
//				 
//				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
//					 logic.evaluarAplicacion(tx);
//				 	}
//				 if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
//					 logic.clean();
//				 	}
//				 if (event.getKeyCode()==KeyEvent.VK_F5){
//					 logic.buscaraplicacion(tx);
//				 	}
//			 	}
//			

	}
	
}
