package aplicacion.gestion.agenda.escritor.events;

import aplicacion.gestion.agenda.escritor.interfaces._Interface;
import aplicacion.gestion.agenda.escritor.logic.*;

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

			if (tx.getName() == _Interface._txt_idaviso) {
				if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
					// cancelar
				}
				if (event.getKeyCode() == KeyEvent.VK_ENTER) {
					// check numero. para saber si edita o es nuevo
					_logic.evaluate_idaviso(tx);
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
			if (tx.getName() == _Interface._txt_titulo) {
				if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
					// cancelar
				}
				if (event.getKeyCode() == KeyEvent.VK_ENTER) {
					// check numero. para saber si edita o es nuevo
					_logic.evaluar_titulo(tx);
				}
			}
			
		}
		if (event.getSource() instanceof JTextArea) {
			JTextArea tx = (JTextArea) event.getSource();
			if (tx.getName() == _Interface._txt_mensaje) {
				if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
					// cancelar
				}
				if (event.getKeyCode() == KeyEvent.VK_ENTER) {
					// check numero. para saber si edita o es nuevo
					_logic.evaluar_mensaje(tx);
				}
			}
		}
	}
	
}
