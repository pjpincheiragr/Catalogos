
package aplicacion.sistema.host.events;

import java.awt.event.KeyEvent;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import aplicacion.sistema.host.interfaces._Interface;
import aplicacion.sistema.host.logic._Logic;
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
			 
			if (tx.getName()==_Interface._txt_idhost){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluarHost(tx);
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					 logic.clean();
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_F5){
					 logic.BuscarHost(tx);
				 	}
			 	}
			
			if (tx.getName()==_Interface._txt_ip){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluarIP(tx);
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					 logic.clean();
				 	}
				}

			if (tx.getName()==_Interface._txt_extension){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluarExtension(tx);
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					 logic.clean();
				 	}
				}
			if (tx.getName()==_Interface._txt_email){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluarEmail(tx);
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					 logic.clean();
				 	}
				}
			
			if (tx.getName()==_Interface._txt_os){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluarSistema(tx);
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					 logic.clean();
				 	}
				}
			
			if (tx.getName()==_Interface._txt_macAddress){
				 
				
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluarMacAddress(tx);
				 	}else{
				 		if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
							 logic.clean();
						 	}else{
//						 		if(Character.isLetter(event.getKeyChar()))
//						 			logic.evaluarString(tx);
//						 		else 
						 		if(Character.isLetterOrDigit(event.getKeyChar()))
						 			logic.evaluarString(tx);
						 	}
				 	}
				 
				}
			
			
		}
		
		if (event.getSource() instanceof JCheckBox){
			JCheckBox chk=(JCheckBox) event.getSource();
			
			if (chk.getName()==_Interface._chk_printer){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluarEtiqueta();
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					 logic.clean();
				 	}

			 	}
			
			if (chk.getName()==_Interface._chk_server){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluarServer();
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					 logic.clean();
				 	}
			}
			
			
		}
		
	}
	
}
