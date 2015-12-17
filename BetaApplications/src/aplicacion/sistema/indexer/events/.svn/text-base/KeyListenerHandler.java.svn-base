package aplicacion.sistema.indexer.events;

import java.awt.event.KeyEvent;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import aplicacion.sistema.indexer.interfaces._Interface;
import aplicacion.sistema.indexer.logic._Logic;
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
			 

				if (tx.getName()== _Interface._txt_idproveedor){
					
					if (event.getKeyCode()==KeyEvent.VK_F5){
						logic.BuscarProveedor(tx);
					}
					if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
						//cancelar
					}
					if (event.getKeyCode()==KeyEvent.VK_ENTER){
						//check numero. para saber si edita o es nuevo
						logic.evaluarProveedor(tx);
					}
						
				}
			 
			
 
			 			 
		}
		
	}
	
}
