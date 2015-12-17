package aplicacion.actualizacion.global.events;
import aplicacion.actualizacion.global.logic._Logic;
import aplicacion.modelo.events.*;
import aplicacion.actualizacion.global.interfaces.*;
import java.awt.event.ActionEvent;
public class ActionListenerHandler extends _ActionListenerHandler{
	
	
	public void procesar(ActionEvent e){
		_Logic logic=(_Logic) this._logic;
		if (e.getActionCommand()==_Interface._btn_cancelar){
			logic.clean();
		}
		if (e.getActionCommand()==_Interface._btn_nuevo){
			logic.nuevo();
		}
		if (e.getActionCommand()==_Interface._btn_cancelar_global){
			logic.doCancel();
		}
		if (e.getActionCommand()==_Interface._btn_cancelar_tarea){
			logic.doCancel();
		}
		if (e.getActionCommand()==_Interface._btn_play){
			logic.play();
		}
		if (e.getActionCommand()==_Interface._btn_salir){
			logic.exit();
		}
		if (e.getActionCommand()==_Interface._btn_modificarCamino){
			logic.modificarRuta();
		}
	
	}
}
