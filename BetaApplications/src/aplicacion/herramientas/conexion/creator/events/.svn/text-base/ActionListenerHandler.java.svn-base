package aplicacion.herramientas.conexion.creator.events;
import java.awt.event.ActionEvent;

import aplicacion.herramientas.conexion.creator.interfaces.*;
import aplicacion.herramientas.conexion.creator.logic._Logic;
import aplicacion.modelo.events._ActionListenerHandler;

public class ActionListenerHandler extends _ActionListenerHandler{
	public void procesarEvento(ActionEvent e){
		_Logic logic=(_Logic) this._logic;
		
		if (e.getActionCommand()==_Interface._btn_salir){
			logic.exit();
		}
		if (e.getActionCommand()==_Interface._btn_login){
			logic.login();
		}
		
		if (e.getActionCommand()==_Interface._btn_buscar){
			logic.buscar();
		}
		if (e.getActionCommand()==_Interface._btn_guardar){
			logic.guardar();
		}	
		if (e.getActionCommand()==_Interface._btn_cancelar){
			logic.cancelar();
		}
		if (e.getActionCommand()==_Interface._btn_eliminar){
			logic.eliminar();
		}
	}
}
