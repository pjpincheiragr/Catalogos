package aplicacion.inventario.politica.events;

import java.awt.event.KeyEvent;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import aplicacion.inventario.politica.interfaces._Interface;
import aplicacion.inventario.politica.logic._Logic;
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
			
			 
			 if (tx.getName()==_Interface._txt_idpolitica){
					if (event.getKeyCode()==KeyEvent.VK_ENTER){
						logic.evaluar_politica(tx);
					}
					if (event.getKeyCode()==KeyEvent.VK_F5){
						logic.BuscarPolitica(tx);
					}
				
			 }
			 if (tx.getName()==_Interface._txt_formula_costo){
					if (event.getKeyCode()==KeyEvent.VK_ENTER){
						logic.recalcular_costo(tx);
					}
					
				}
			 if (tx.getName()==_Interface._txt_formula_publico){
					if (event.getKeyCode()==KeyEvent.VK_ENTER){
						logic.recalcular_publico(tx);
					}
					
				}
			 
			 if (tx.getName()==_Interface._txt_politica_descripcion){
					if (event.getKeyCode()==KeyEvent.VK_ENTER){
						logic.check_description();
					}
					
				}
			 
			 
		}
		
	}
	
}
