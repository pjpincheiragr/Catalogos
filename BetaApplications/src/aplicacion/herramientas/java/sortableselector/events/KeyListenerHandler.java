package aplicacion.herramientas.java.sortableselector.events;



import java.awt.Container;
import java.awt.event.KeyEvent;

import javax.swing.DefaultFocusManager;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTable;

import aplicacion.herramientas.java.sortableselector.interfaces.*;
import aplicacion.herramientas.java.sortableselector.logic.*;
import aplicacion.modelo.events._KeyListenerHandler;

public class KeyListenerHandler extends _KeyListenerHandler{

	public void procesarEvento(KeyEvent event){
		_Logic _logic=(_Logic) this._logic;
		
		
		if (event.getSource() instanceof JTable){
			JTable table=(JTable) event.getSource();
			int row=table.getSelectedRow();
			int col=table.getSelectedColumn();
			if (table.getName()==_Interface._table){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic.Close(table, row);
				}
				
			}
			
		}
		
		if (event.getSource() instanceof JTextField){
			
			JTextField tx=(JTextField) event.getSource();
			int row=-1;
			int col=-1;
			if (tx.getParent() instanceof JTable){
				JTable table=(JTable) tx.getParent();
				row=table.getSelectedRow();
				col=table.getSelectedColumn();
			}
			if (tx.getName()== _Interface._column){
				if (event.getKeyCode()==KeyEvent.VK_F5){
					_logic.search(tx,col);
					
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					_logic.evaluat_escape(tx, col);
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					System.out.println("Close");
					_logic.enter(tx,col);
				}
				if (event.getKeyCode()==KeyEvent.VK_UP){
					
					_logic.Up(tx,col);
					
				}
				if (event.getKeyCode()==KeyEvent.VK_TAB){
					if (event.isControlDown()){
						_logic.transferBackFocus(tx, col);
					}else{
						_logic.transferNextFocus(tx, col);	
					}
					
					
				}
				if (event.getKeyCode()==KeyEvent.VK_DOWN){
					
					_logic.Down(tx,col);
					
					
				}
			}
			

			
		
			
						
			
			
		}
		
					
	}
}
