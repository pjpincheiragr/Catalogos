package aplicacion.proveedor.guia.events;

import aplicacion.proveedor.guia.logic._Logic;

import java.awt.Container;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JTree;
import aplicacion.proveedor.guia.interfaces._Interface;
import aplicacion.modelo.events._KeyListenerHandler;

public class KeyListenerHandler extends _KeyListenerHandler{

	public void procesarEvento(KeyEvent event){
		_Logic _logic=(_Logic) this._logic;
		


		
		if (event.getSource() instanceof JTextField){
			JTextField tx=(JTextField)event.getSource();
			if (tx.getName()== _Interface._txt_idproveedor){
				
				if (event.getKeyCode()==KeyEvent.VK_F5){
					_logic.BuscarProveedor(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					_logic.cancelar();
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					//check numero. para saber si edita o es nuevo
					_logic.evaluarProveedor(tx);
				}
					
			}
		}
		
		
					
	}
}
