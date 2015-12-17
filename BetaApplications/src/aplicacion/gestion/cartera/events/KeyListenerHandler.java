package aplicacion.gestion.cartera.events;

import aplicacion.gestion.cartera.logic.*;
import java.awt.event.KeyEvent;
import aplicacion.modelo.events._KeyListenerHandler;

public class KeyListenerHandler extends _KeyListenerHandler{

	public void procesarEvento(KeyEvent event){
		_Logic _logic=(_Logic) this._logic;
	}
}
