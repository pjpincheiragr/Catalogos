package aplicacion.herramientas.conexion.login.events;
import java.awt.event.ActionEvent;

import aplicacion.herramientas.conexion.login.interfaces.*;
import aplicacion.herramientas.conexion.login.logic._Logic;
import aplicacion.modelo.events._ActionListenerHandler;

public class ActionListenerHandler extends _ActionListenerHandler{
	public void procesarEvento(ActionEvent e){
		_Logic logic=(_Logic) this._logic;
		
		if (e.getActionCommand()==_Interface._btn_salir){
			logic.exit();
		}
		if (e.getActionCommand()==_Interface._btn_error){
			logic.sendInfo();
		}
		if (e.getActionCommand()==_Interface._btn_login){
			logic.goLogIn();
			//logic._loginTask();
		}
		
		if (e.getActionCommand()==_Interface._btn_editar_conexion){
			logic.editarConexion();
		}
		
		
	}
}
