package aplicacion.sistema.usuario.events;

import java.awt.event.KeyEvent;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JButton;
import aplicacion.sistema.usuario.interfaces._Interface;
import aplicacion.sistema.usuario.logic._Logic;
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
			 if (tx.getName()== _Interface._txt_idvendedor){
					
					if (event.getKeyCode()==KeyEvent.VK_F5){
						logic.BuscarVendedor(tx);
					}
					if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
						//cancelar
					}
					if (event.getKeyCode()==KeyEvent.VK_ENTER){
						logic.evaluarVendedor(tx);
					}
				}
			 if (tx.getName()== _Interface._txt_iddeposito){
					
					if (event.getKeyCode()==KeyEvent.VK_F5){
						//logic.BuscarDeposito(tx);
					}
					if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
						//cancelar
					}
					if (event.getKeyCode()==KeyEvent.VK_ENTER){
						logic.evaluarDeposito(tx);
					}
				}
			 if (tx.getName()== _Interface._txt_negocio){
					
					if (event.getKeyCode()==KeyEvent.VK_F5){
//						logic.BuscarNegociotx);
					}
					if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
						//cancelar
					}
					if (event.getKeyCode()==KeyEvent.VK_ENTER){
						logic.evaluarNegocio(tx);
					}
				}
			 if (tx.getName()==_Interface._txt_idusuario){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 String valor=tx.getText();
					 logic.evaluarusuario(tx);
				 }
				 if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					 logic.clean();
				 }
				 if (event.getKeyCode()==KeyEvent.VK_F5){
					 logic.buscarusuario(tx);
				 }
			 }
			
			 if (tx.getName()==_Interface._txt_pass){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 String valor=tx.getText();
					 logic.evaluarPass(valor);
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
			}
	}
	
}
