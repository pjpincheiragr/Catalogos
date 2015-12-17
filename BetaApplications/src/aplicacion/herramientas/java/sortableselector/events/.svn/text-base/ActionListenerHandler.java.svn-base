package aplicacion.herramientas.java.sortableselector.events;
import java.awt.event.ActionEvent;

import aplicacion.herramientas.java.sortableselector.interfaces.*;
import aplicacion.herramientas.java.sortableselector.logic.*;
import aplicacion.modelo.events._ActionListenerHandler;

public class ActionListenerHandler extends _ActionListenerHandler{
	public void procesarEvento(ActionEvent e){
		_Logic _logic=(_Logic) this._logic;
		
		if (e.getActionCommand()==_Interface._btn_buscar){
			
			_logic.goCargar();
			
		}
		if (e.getActionCommand()==_Interface._btn_cancelar){
			
			_logic.clean();
			
		}
		if (e.getActionCommand()==_Interface._btn_salir){
			
			_logic.exit();
		}
		if (e.getActionCommand()==_Interface._btn_error){
			
			_logic.sendInfo();
		}
		
	}
}
