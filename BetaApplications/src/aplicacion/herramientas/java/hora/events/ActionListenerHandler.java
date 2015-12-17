package aplicacion.herramientas.java.hora.events;
import aplicacion.herramientas.java.hora.interfaces._Interface;
import aplicacion.herramientas.java.hora.logic.*;
import aplicacion.modelo.events.*;
import java.awt.event.ActionEvent;

public class ActionListenerHandler extends _ActionListenerHandler {
	

	public void procesar(ActionEvent e){
		_Logic logic=(_Logic) this._logic;
		
		if (e.getActionCommand()==_Interface._btn_ok){
			logic.make_fecha();
		}
		if (e.getActionCommand()==_Interface._btn_salir){
			logic.exit_command();
		}
		if (e.getActionCommand()==_Interface._btn_cancel){
			logic.cancelar();
		}
		if (e.getActionCommand()==_Interface._btn_minuto_up){
			logic.minuto_up();
		}
		if (e.getActionCommand()==_Interface._btn_minuto_down){
			logic.minuto_down();
		}
		if (e.getActionCommand()==_Interface._btn_hora_up){
			logic.hora_up();
		}
		if (e.getActionCommand()==_Interface._btn_hora_down){
			logic.hora_down();
		}
			
		
	}

}
