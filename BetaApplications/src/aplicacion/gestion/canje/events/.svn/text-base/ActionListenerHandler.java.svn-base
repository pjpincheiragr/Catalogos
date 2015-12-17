package aplicacion.gestion.canje.events;

import java.awt.event.ActionEvent;
import aplicacion.gestion.canje.interfaces.*;
import aplicacion.gestion.canje.logic.*;
import aplicacion.modelo.events._ActionListenerHandler;

public class ActionListenerHandler extends _ActionListenerHandler{
	public void procesarEvento(ActionEvent e){
		_Logic _logic=(_Logic) this._logic;
		if (e.getActionCommand()==_Interface._btn_cancelar){
			_logic.cancelar();
		}
		
		if (e.getActionCommand()==_Interface._btn_anular){
			_logic.anular();
		}
		if (e.getActionCommand()==_Interface._btn_salir){
			_logic.exit();
		}
		if (e.getActionCommand()==_Interface._btn_error){
			_logic.sendInfo();
		}
		
		if (e.getActionCommand()==_Interface._btn_grabar){
			_logic.GrabarCanje();
		}
		
		if (e.getActionCommand()==_Interface._btn_buscar_canje){
			_logic.BuscarCanje();
		}
		
	
		
		if (e.getActionCommand()==_Interface._btn_fecha){
			_logic.load_calendar();
		}
	}
}
