package aplicacion.gestion.tablero.events;

import aplicacion.gestion.tablero.logic.*;

import java.awt.Container;
import java.awt.event.KeyEvent;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTable;

import aplicacion.gestion.tablero.interfaces.*;

import aplicacion.modelo.events._KeyListenerHandler;
public class KeyListenerHandler extends _KeyListenerHandler{

	public void procesarEvento(KeyEvent event){
		_Logic _logic=(_Logic) this._logic;
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
			 if (tx.getName()==_Interface._txt_cell){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 //_logic.evaluateCell(tx,row,col);
				 }
				 if (event.getKeyCode()==KeyEvent.VK_F5){
					 
				 }
			 }
			 
					
					if (tx.getName()== _Interface._txt_desde){
						if (event.getKeyCode()==KeyEvent.VK_F5){
							
							_logic.BuscarFecha(tx);
						}
						if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
							//cancelar
						}
						if (event.getKeyCode()==KeyEvent.VK_ENTER){
							
						}
					}
					

					if (tx.getName()== _Interface._txt_hasta){
						if (event.getKeyCode()==KeyEvent.VK_F5){
							
							_logic.BuscarFecha(tx);
						}
						if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
							//cancelar
						}
						if (event.getKeyCode()==KeyEvent.VK_ENTER){
						
						}
					}
				

		}
		
					
	}
}
