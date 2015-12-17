package aplicacion.gestion.agenda.events;

import aplicacion.gestion.agenda.logic.*;
import aplicacion.gestion.agenda.interfaces._Interface;

import java.awt.event.KeyEvent;

import javax.swing.JTextField;

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
					_logic.evaluar_fecha(tx);
				}

			}

			if (tx.getName() == _Interface._txt_idaviso) {

				
				if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
					// cancelar
				}
				if (event.getKeyCode() == KeyEvent.VK_ENTER) {
					// check numero. para saber si edita o es nuevo
					_logic.evaluate_idaviso(tx);
				}

			}
		}
	}
}
