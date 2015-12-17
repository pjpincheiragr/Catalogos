package aplicacion.sistema.aplicacion.events;

import java.awt.event.ActionEvent;

import aplicacion.sistema.aplicacion.interfaces._Interface;
import aplicacion.sistema.aplicacion.logic._Logic;
import aplicacion.modelo.events._ActionListenerHandler;

public class ActionListenerHandler extends _ActionListenerHandler {
	
	public void procesarEvento(ActionEvent event){
		_Logic logic=(_Logic) this._logic;
		if (event.getActionCommand()==_Interface._btn_cancelar){
			logic.cancelar();
		}
		if (event.getActionCommand()==_Interface._btn_cargar){
			logic.cargar_parametros();
		}
		if (event.getActionCommand()==_Interface._btn_nuevo){
			logic.nuevo();
		}
		if (event.getActionCommand()==_Interface._btn_salir){
			logic.exit();
		}
		if (event.getActionCommand()==_Interface._btn_error){
			logic.sendInfo();
		}
		if (event.getActionCommand()==_Interface._btn_exportar){
			logic.exportar();
		}
		if (event.getActionCommand()==_Interface._btn_importar){
			logic.importar();
		}
	}
	
	


}