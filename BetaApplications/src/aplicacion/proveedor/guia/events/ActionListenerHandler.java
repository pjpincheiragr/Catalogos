package aplicacion.proveedor.guia.events;

import java.awt.event.ActionEvent;

import javax.swing.JTextField;

import aplicacion.proveedor.guia.interfaces._Interface;
import aplicacion.proveedor.guia.logic.*;
import aplicacion.modelo.events._ActionListenerHandler;

public class ActionListenerHandler extends _ActionListenerHandler {
	public void procesarEvento(ActionEvent e) {
		_Logic _logic = (_Logic) this._logic;
		
		if (e.getActionCommand() == _Interface._btn_cancelar) {
			_logic.cancelar();
		}
		if (e.getActionCommand() == _Interface._btn_salir) {
			_logic.exit();
		}
		if (e.getActionCommand() == _Interface._btn_guardar) {
			_logic.guardar();
		}
		if (e.getActionCommand() == _Interface._btn_error) {
			_logic.sendInfo();
		}
		if(e.getActionCommand() == _Interface._btn_buscar){
			_logic.BuscarProveedor();
		}
		


		
		
	}
}
