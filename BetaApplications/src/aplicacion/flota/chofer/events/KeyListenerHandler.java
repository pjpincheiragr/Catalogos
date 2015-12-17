
package aplicacion.flota.chofer.events;

import java.awt.event.KeyEvent;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import aplicacion.flota.chofer.interfaces._Interface;
import aplicacion.flota.chofer.logic._Logic;
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
			 
			if (tx.getName()==_Interface._txt_apellido){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					// logic.evaluarApellido(tx);
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					 logic.clean();
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_F5){
					 //logic.buscarChofer(tx);
				 	}
			 	}
			
			if (tx.getName()==_Interface._txt_DNI){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					// logic.evaluarDNI(tx);
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					 logic.clean();
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_F5){
					 //logic.buscarChofer(tx);
				 	}
			 	}
			
			if (tx.getName()==_Interface._txt_idchofer){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					// logic.evaluarIdchofer(tx);
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					 logic.clean();
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_F5){
					 //logic.buscarChofer(tx);
				 	}
			 	}
			
			if (tx.getName()==_Interface._txt_licencia){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					// logic.evaluarLicencia(tx);
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					 logic.clean();
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_F5){
					 //logic.buscarChofer(tx);
				 	}
			 	}
			
			if (tx.getName()==_Interface._txt_nombre){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					// logic.evaluarNombre(tx);
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					 logic.clean();
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_F5){
					 //logic.buscarChofer(tx);
				 	}
			 	}
			
			if (tx.getName()==_Interface._txt_vencimiento){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					// logic.evaluarVencimiento(tx);
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					 logic.clean();
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_F5){
					 //logic.buscarChofer(tx);
				 	}
			 	}
			
			
		}		
			
			
			
	}
	
}
