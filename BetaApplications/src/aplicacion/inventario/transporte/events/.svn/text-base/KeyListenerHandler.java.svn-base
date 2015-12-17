package aplicacion.inventario.transporte.events;

import java.awt.event.KeyEvent;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import aplicacion.inventario.transporte.interfaces._Interface;
import aplicacion.inventario.transporte.logic._Logic;
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
			
			 
			 if (tx.getName()==_Interface._txt_idtransporte){
					if (event.getKeyCode()==KeyEvent.VK_ENTER){
						logic.evaluar_transporte(tx);
					}
					if (event.getKeyCode()==KeyEvent.VK_F5){
						logic.BuscarTransporte(tx);
					}
				
			 }
			  if (tx.getName()==_Interface._txt_transporte_descripcion){
					if (event.getKeyCode()==KeyEvent.VK_ENTER){
						logic.check_description();
					}
					
				}
			 
			 
		}
		
	}
	
}
