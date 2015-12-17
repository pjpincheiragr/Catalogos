package aplicacion.sistema.perfil.events;

import java.awt.event.KeyEvent;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JButton;
import aplicacion.sistema.perfil.interfaces._Interface;
import aplicacion.sistema.perfil.logic._Logic;
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
			 if (tx.getName()== _Interface._txt_nombre){
					
					if (event.getKeyCode()==KeyEvent.VK_F5){
						//logic.BuscarVendedor(tx);
					}
					if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
						//cancelar
					}
					if (event.getKeyCode()==KeyEvent.VK_ENTER){
						logic.evaluarNombre(tx.getText());
					}
				}
			 if (tx.getName()==_Interface._txt_idusuario){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluarIdusuario(tx);
					 
				 }
				 if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					// logic.clean();
				 }
				 if (event.getKeyCode()==KeyEvent.VK_F5){
					// logic.buscarusuario(tx);
				 }
			 }
			
			 if (tx.getName()==_Interface._txt_apellido){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluarApellido(tx.getText());
				 }
				 if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					 //logic.clean();
				 }
				 
			 }
		
			 if (tx.getName()==_Interface._txt_email){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluarEmail(tx.getText());
				 }
				 if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					// logic.clean();
				 }
				 
			 	}
			
		
		 if (tx.getName()==_Interface._txt_DNI){
			 
			 if (event.getKeyCode()==KeyEvent.VK_ENTER){
				 logic.evaluarDNI(tx.getText());
			 }
			 if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
				// logic.clean();
			 }
			 
		 	}
		 if (tx.getName()==_Interface._txt_liscencia){
			 
			 if (event.getKeyCode()==KeyEvent.VK_ENTER){
				 logic.evaluarVencimiento(tx);
				 
			 }
			 if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
				// logic.clean();
			 }
			 
		 	}
		 
		 if (tx.getName()==_Interface._txt_nacimiento){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluarNacimiento(tx);
					 
				 }
				 if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					// logic.clean();
				 }
				 
			 	}
			 
		 if (tx.getName()==_Interface._txt_telefono){
		 
			 if (event.getKeyCode()==KeyEvent.VK_ENTER){
				 logic.evaluarTelefono(tx.getText());
			 }
			 if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
				 //logic.clean();
			 }
			 
		 	}
		 if (tx.getName()==_Interface._txt_domicilio){
			 
			 if (event.getKeyCode()==KeyEvent.VK_ENTER){
				 logic.evaluarDomicilio(tx.getText());
			 }
			 if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
				// logic.clean();
			 }
			 
		 	}

		}
		
	}
	
}
