package aplicacion.herramientas.java.calendario.events;
import aplicacion.herramientas.java.calendario.interfaces._Interface;
import aplicacion.herramientas.java.calendario.logic.*;
import aplicacion.modelo.events.*;
import java.awt.event.ActionEvent;

public class ActionListenerHandler extends _ActionListenerHandler {
	

	public void procesar(ActionEvent e){
		_Logic logic=(_Logic) this._logic;
		
		if (e.getActionCommand()==_Interface._btn_ok){
			logic.make_fecha();
		}
			
		
	}

}
