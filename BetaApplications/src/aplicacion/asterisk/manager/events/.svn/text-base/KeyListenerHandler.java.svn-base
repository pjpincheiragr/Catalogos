package aplicacion.asterisk.manager.events;

import aplicacion.asterisk.manager.logic.*;
import java.awt.Container;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.JTable;

import aplicacion.asterisk.manager.interfaces.*;
import aplicacion.modelo.events._KeyListenerHandler;
public class KeyListenerHandler extends _KeyListenerHandler{

	public void procesarEvento(KeyEvent event){
		_Logic _logic=(_Logic) this._logic;
		
		System.out.println("KeyEvent>"+event.getSource());
		
		
		if (event.getSource() instanceof JTextField){
			
			JTextField tx=(JTextField) event.getSource();
			
			JTable table=null;
			int row=-1;
			int col=-1;
			if (tx.getParent() instanceof JTable){
				table=(JTable) tx.getParent();
				row=table.getSelectedRow();
				col=table.getSelectedColumn();
				
			}
			

			if (tx.getName()== _Interface._table_idcuenta){
				
				if (event.getKeyCode()==KeyEvent.VK_F5){
					_logic.Buscar(tx);
					
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					//cancelar
				}
				if (event.getKeyCode()==KeyEvent.VK_DELETE){
					_logic.borrar_renglon(tx, row);
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic.evaluate_idcuenta(tx, row);
					//check numero. para saber si edita o es nuevo
					//_logic.evaluarCliente(tx);
				}
					
			}
			
			
		}
		}
}
