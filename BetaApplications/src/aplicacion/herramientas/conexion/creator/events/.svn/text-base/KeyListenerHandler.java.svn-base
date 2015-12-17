package aplicacion.herramientas.conexion.creator.events;

import aplicacion.herramientas.conexion.creator.logic.*;

import java.awt.Container;
import java.awt.event.KeyEvent;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTable;

import aplicacion.herramientas.conexion.creator.interfaces.*;
import aplicacion.modelo.events._KeyListenerHandler;
public class KeyListenerHandler extends _KeyListenerHandler{

	public void procesarEvento(KeyEvent event){
		_Logic _logic=(_Logic) this._logic;
		
		
		if (event.getSource() instanceof JTable){
			JTable table=(JTable) event.getSource();
			int row=table.getSelectedRow();
			int col=table.getSelectedColumn();
			
			
		}
		if (event.getSource() instanceof JComboBox){
			JComboBox lst = (JComboBox) event.getSource();
			if (lst.getName() == _Interface._list_tipo){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					//logic.evaluate_caja(lst);	
				}
				
			}
		}
		if (event.getSource() instanceof JTextField){
			
			JTextField tx=(JTextField) event.getSource();
			
			if (tx.getName()== _Interface._txt_idconexion){
				if (event.getKeyCode()==KeyEvent.VK_F5){
					_logic.buscar(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					_logic.cancelar();
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic._evaluar_codigo_conexion(tx);
				}
			}
			
			
			}
		
		}
}
