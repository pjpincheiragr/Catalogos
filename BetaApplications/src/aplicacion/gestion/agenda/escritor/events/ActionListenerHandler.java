package aplicacion.gestion.agenda.escritor.events;
import java.awt.event.ActionEvent;

import aplicacion.gestion.agenda.escritor.interfaces.*;
import aplicacion.gestion.agenda.escritor.logic.*;
import aplicacion.modelo.events._ActionListenerHandler;

public class ActionListenerHandler extends _ActionListenerHandler{
	public void procesarEvento(ActionEvent e){
		_Logic _logic=(_Logic) this._logic;
		if (e.getActionCommand()==_Interface._btn_cancelar){
			_logic.cancelar();
		}
		if (e.getActionCommand()==_Interface._btn_fecha){
			_logic.BuscarHora();
		}
		if (e.getActionCommand()==_Interface._btn_mostrar){
		
		}
		if (e.getActionCommand()==_Interface._btn_nuevo){
			_logic.nuevo();
		}
		if (e.getActionCommand()==_Interface._btn_guardar){
			_logic.guardar();
		}
		if (e.getActionCommand()==_Interface._btn_eliminar){
			_logic.eliminar();
		}
		if (e.getActionCommand()==_Interface._btn_salir){
			_logic.exit();
		}
		
		if (e.getActionCommand()==_Interface._btn_error){
			_logic.sendInfo();
		}
	}
}
