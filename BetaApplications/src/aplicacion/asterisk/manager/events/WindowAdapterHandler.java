package aplicacion.asterisk.manager.events;

import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import aplicacion.asterisk.manager.interfaces._Interface;
import aplicacion.asterisk.manager.logic._Logic;
import aplicacion.asterisk.manager.logic.DynamicFrame;
import aplicacion.modelo.events._WindowAdapterHandler;

public class WindowAdapterHandler extends _WindowAdapterHandler {

	public void procesarEvento(WindowEvent we) {
		_Logic _logic = (_Logic) this._logic;
		if (we.getSource() instanceof DynamicFrame) {
			DynamicFrame fx = (DynamicFrame) we.getSource();
			if (we.getID() == WindowEvent.WINDOW_CLOSING) {
				System.out.println("Cerrando Dynamic " + fx.getIdAviso() + " "
						+ fx.getIdUser());
				_logic.close(fx);
			}

		} else {
			_logic.exit();
		}
	}

}
