package aplicacion.gestion.agenda.gestion.events;

import aplicacion.gestion.agenda.gestion.interfaces._Interface;
import aplicacion.gestion.agenda.gestion.logic.*;

import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import aplicacion.modelo.events._KeyListenerHandler;

public class KeyListenerHandler extends _KeyListenerHandler {

	public void procesarEvento(KeyEvent event) {
		_Logic _logic = (_Logic) this._logic;
		if (event.getSource() instanceof JTextField) {
			JTextField tx = (JTextField) event.getSource();

			if (tx.getName() == _Interface._txt_agenda) {

				if (event.getKeyCode() == KeyEvent.VK_F5) {
					_logic.BuscarHora();
				}
				if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
					// cancelar
				}
				if (event.getKeyCode() == KeyEvent.VK_ENTER) {
					// check numero. para saber si edita o es nuevo
					_logic.evaluarHora(tx);
				}

			}

			
			if (tx.getName() == _Interface._txt_idvendedor) {
				if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
					// cancelar
				}
				if (event.getKeyCode() == KeyEvent.VK_ENTER) {
					// check numero. para saber si edita o es nuevo
					_logic.evaluarVendedor(tx);
				}
			}
			if (tx.getName() == _Interface._txt_idcreador) {
				if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
					// cancelar
				}
				if (event.getKeyCode() == KeyEvent.VK_ENTER) {
					// check numero. para saber si edita o es nuevo
					_logic.evaluarVendedor(tx);
				}
			}
					}
		
	}
	
}
