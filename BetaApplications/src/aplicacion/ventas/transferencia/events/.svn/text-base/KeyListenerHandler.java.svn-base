package aplicacion.ventas.transferencia.events;

import aplicacion.ventas.transferencia.logic.*;
import aplicacion.ventas.transferencia.interfaces._Interface;

import java.awt.event.KeyEvent;

import javax.swing.JTextField;

import aplicacion.modelo.events._KeyListenerHandler;

public class KeyListenerHandler extends _KeyListenerHandler{

	public void procesarEvento(KeyEvent event){
		_Logic _logic=(_Logic) this._logic;
		if (event.getSource() instanceof JTextField){
			JTextField tx=(JTextField) event.getSource();
			
		
			
			if (tx.getName()== _Interface._txt_fecha){
				
				if (event.getKeyCode()==KeyEvent.VK_F5){
					
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					//cancelar
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
				
				
				}
			}
			
			if (tx.getName()== _Interface._txt_idvendedor_origen){
				
				if (event.getKeyCode()==KeyEvent.VK_F5){
					_logic.BuscarVendedor(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					//cancelar
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic.evaluarVendedor(tx);
				
				}
				
			}
			if (tx.getName()== _Interface._txt_idvendedor_destino){
				
				if (event.getKeyCode()==KeyEvent.VK_F5){
					_logic.BuscarVendedor(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					//cancelar
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic.evaluarVendedor(tx);
				
				}
				
			}
			
if (tx.getName()==_Interface._txt_agenda_destinatario){
				
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic.evaluarHora(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_F5){
					_logic.BuscarHoraDestinatario();
				}
			}
			if (tx.getName()==_Interface._txt_agenda_remitente){
				
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic.evaluarHora(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_F5){
					_logic.BuscarHoraRemitente();
				}
			}
	}
	}
}
