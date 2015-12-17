package aplicacion.cliente.archivo.events;

import aplicacion.cliente.archivo.logic.*;
import java.awt.Container;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.JTable;

import aplicacion.cliente.archivo.interfaces.*;
import aplicacion.modelo.events._KeyListenerHandler;

public class KeyListenerHandler extends _KeyListenerHandler {

	public void procesarEvento(KeyEvent event) {
		_Logic _logic = (_Logic) this._logic;

		if (event.getSource() instanceof JTextField) {

			JTextField tx = (JTextField) event.getSource();

			if (tx.getName() == _Interface._txt_idcliente) {

				if (event.getKeyCode() == KeyEvent.VK_F5) {
					_logic.BuscarCliente(tx);
				}
				if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
					// cancelar
				}
				if (event.getKeyCode() == KeyEvent.VK_ENTER) {
					// check numero. para saber si edita o es nuevo
					_logic.evaluarCliente(tx);
				}

			}

			if (tx.getName() == _Interface._txt_descuento) {

				if (event.getKeyCode() == KeyEvent.VK_ENTER) {
					// check numero. para saber si edita o es nuevo
					_logic.evaluarDescuento(tx);
				}

			}

			if (tx.getName() == _Interface._txt_limite) {

				if (event.getKeyCode() == KeyEvent.VK_ENTER) {
					// check numero. para saber si edita o es nuevo
					_logic.evaluarLimite(tx);
				}

			}
			if (tx.getName() == _Interface._txt_clientedescripcion) {
				if (event.getKeyCode() == KeyEvent.VK_ENTER) {
					_logic.evaluar_descripcion(tx);
				}
			}
			if (tx.getName() == _Interface._txt_domicilio) {
				if (event.getKeyCode() == KeyEvent.VK_ENTER) {
					_logic.evaluar_calle(tx);
				}
			}
			if (tx.getName() == _Interface._txt_numero) {
				if (event.getKeyCode() == KeyEvent.VK_ENTER) {
					_logic.evaluar_numero(tx);
				}
			}
			if (tx.getName() == _Interface._txt_piso) {
				if (event.getKeyCode() == KeyEvent.VK_ENTER) {
					_logic.evaluar_piso(tx);
				}
			}
			if (tx.getName() == _Interface._txt_postal) {
				if (event.getKeyCode() == KeyEvent.VK_ENTER) {
					_logic.evaluar_postal(tx);
				}
			}
			if (tx.getName() == _Interface._txt_provincia) {
				if (event.getKeyCode() == KeyEvent.VK_ENTER) {
					_logic.evaluar_provincia(tx);
				}
			}
			if (tx.getName() == _Interface._txt_localidad) {
				if (event.getKeyCode() == KeyEvent.VK_ENTER) {
					_logic.evaluar_localidad(tx);
				}
			}
			if (tx.getName() == _Interface._txt_provincia) {
				if (event.getKeyCode() == KeyEvent.VK_ENTER) {
					_logic.evaluar_provincia(tx);
				}
			}
			
			if (tx.getName() == _Interface._txt_telefono) {
				if (event.getKeyCode() == KeyEvent.VK_ENTER) {
					_logic.evaluar_provincia(tx);
				}
			}
			if (tx.getName() == _Interface._txt_fax) {
				if (event.getKeyCode() == KeyEvent.VK_ENTER) {
					_logic.evaluar_fax(tx);
				}
			}
			if (tx.getName() == _Interface._txt_email) {
				if (event.getKeyCode() == KeyEvent.VK_ENTER) {
					_logic.evaluar_email(tx);
				}
			}
			if (tx.getName() == _Interface._txt_contacto) {
				if (event.getKeyCode() == KeyEvent.VK_ENTER) {
					_logic.evaluar_contacto(tx);
				}
			}
			if (tx.getName() == _Interface._txt_cond_iva) {
				if (event.getKeyCode() == KeyEvent.VK_ENTER) {
					
				}
			}
			if (tx.getName() == _Interface._txt_provincia) {
				if (event.getKeyCode() == KeyEvent.VK_ENTER) {
					_logic.evaluar_provincia(tx);
				}
			}
		}
		
	}
}
