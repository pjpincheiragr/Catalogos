package aplicacion.herramientas.conexion.login.events;

import aplicacion.herramientas.conexion.login.logic.*;

import java.awt.Container;
import java.awt.event.KeyEvent;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTable;

import aplicacion.herramientas.conexion.login.interfaces.*;
import aplicacion.modelo.events._KeyListenerHandler;
public class KeyListenerHandler extends _KeyListenerHandler{

	public void procesarEvento(KeyEvent event){
		_Logic _logic=(_Logic) this._logic;
		
		
		if (event.getSource() instanceof JTable){
			JTable table=(JTable) event.getSource();
			int row=table.getSelectedRow();
			int col=table.getSelectedColumn();
			
			if (event.getKeyCode()==KeyEvent.VK_ENTER){
				
			}
			
		}
		if (event.getSource() instanceof JComboBox){
			JComboBox lst = (JComboBox) event.getSource();
			
		}
		if (event.getSource() instanceof JTextField){
			
			JTextField tx=(JTextField) event.getSource();
			
			if (tx.getName()== _Interface._txt_password){
				
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					_logic.cancelar();
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic.goLogIn();
				}
			}
			
			if (tx.getName()== _Interface._txt_user){
				
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					_logic.cancelar();
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic.evaluate_idarticulo(tx);
				}
			}
			}
		
		}
}
