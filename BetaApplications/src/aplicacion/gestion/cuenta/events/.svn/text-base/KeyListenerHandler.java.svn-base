package aplicacion.gestion.cuenta.events;

import aplicacion.gestion.cuenta.logic.*;
import aplicacion.gestion.cuenta.interfaces._Interface;

import java.awt.event.KeyEvent;

import javax.swing.JTextField;

import aplicacion.modelo.events._KeyListenerHandler;

public class KeyListenerHandler extends _KeyListenerHandler{

	public void procesarEvento(KeyEvent event){
		_Logic _logic=(_Logic) this._logic;
		if (event.getSource() instanceof JTextField){
			JTextField tx=(JTextField) event.getSource();
			
			if (tx.getName()== _Interface._txt_cuenta){
			
				if (event.getKeyCode()==KeyEvent.VK_F5){
					_logic.BuscarCuenta(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					//cancelar
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
				//check numero. para saber si edita o es nuevo
				_logic._evaluar_codigo_cuenta(tx);
				}
				
		}
			
			if (tx.getName()== _Interface._txt_fecha){
				
				if (event.getKeyCode()==KeyEvent.VK_F5){
					_logic.BuscarFecha(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					//cancelar
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic.evaluarFecha(tx);
				
				}
			}
			
			if (tx.getName()== _Interface._txt_fecha_hasta){
				
				if (event.getKeyCode()==KeyEvent.VK_F5){
					_logic.BuscarFecha(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					//cancelar
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic.evaluarFecha(tx);
				
				}
				
			}
	}
	}
}
