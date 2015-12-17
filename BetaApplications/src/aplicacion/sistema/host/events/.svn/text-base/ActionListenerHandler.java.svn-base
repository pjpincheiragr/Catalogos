package aplicacion.sistema.host.events;

import java.awt.event.ActionEvent;

import aplicacion.sistema.host.interfaces._Interface;
import aplicacion.sistema.host.logic._Logic;
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
		if (event.getActionCommand()==_Interface._btn_buscar_host){
			logic.BuscarHost();
		}
		if(event.getActionCommand()== _Interface._btn_rename){
			logic.recodificar();
		}
		
		if (event.getActionCommand()==_Interface._btn_eliminar){
			logic.delete();
		}
		if (event.getActionCommand()==_Interface._btn_guardar){
			logic.guardar();
		}
		
	}
	
	


}