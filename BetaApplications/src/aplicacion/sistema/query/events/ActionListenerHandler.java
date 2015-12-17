package aplicacion.sistema.query.events;

import java.awt.event.ActionEvent;

import aplicacion.sistema.query.interfaces._Interface;
import aplicacion.sistema.query.logic._Logic;
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
		if (event.getActionCommand()==_Interface._btn_salir){
			logic.exit();
		}
		if (event.getActionCommand()==_Interface._btn_error){
			logic.sendInfo();
		}
		if (event.getActionCommand()==_Interface._btn_play){
			logic.loadFromXML();
		}
		if (event.getActionCommand()==_Interface._btn_buscar_usuario){
			logic.BuscarQuery();
		}
		
		if (event.getActionCommand()==_Interface._btn_eliminar){
			logic.delete();
		}
		if (event.getActionCommand()==_Interface._btn_guardar){
			logic.guardar();
		}
		if (event.getActionCommand()==_Interface._btn_aplicaciones){
			logic.aplicaciones();
		}
	}
	
	


}