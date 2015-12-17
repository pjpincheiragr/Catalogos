package aplicacion.actualizacion.importar.events;

import java.awt.event.ActionEvent;


import aplicacion.modelo.events._ActionListenerHandler;
import aplicacion.actualizacion.importar.interfaces._Interface;
import aplicacion.actualizacion.importar.logic._Logic;

public class ActionListenerHandler extends _ActionListenerHandler{
	
	public void procesarEvento(ActionEvent e){
		_Logic logic=(_Logic)this._logic;
		if (e.getActionCommand()==_Interface._btn_buscar_version){
			logic.goCheck();
		}
		if (e.getActionCommand()==_Interface._btn_actualizar){
			logic.goUpdate();
		}
		if (e.getActionCommand()==_Interface._btn_salir){
			logic.exit();
		}
		if (e.getActionCommand()==_Interface._btn_error){
			logic.sendInfo();
		}
		if (e.getActionCommand()==_Interface._btn_cancelar){
			logic.doCancel();
		}
				
	}

}
