package aplicacion.sistema.autorizar.events;

import java.awt.event.ActionEvent;

import aplicacion.sistema.autorizar.interfaces._Interface;
import aplicacion.sistema.autorizar.logic._Logic;
import aplicacion.modelo.events._ActionListenerHandler;

public class ActionListenerHandler extends _ActionListenerHandler {
	
	public void procesarEvento(ActionEvent event){
		_Logic logic=(_Logic) this._logic;
		if (event.getActionCommand()==_Interface._btn_cancelar){
			logic.cancelar();
		}
		if (event.getActionCommand()==_Interface._btn_cargar){
		
		}
		if (event.getActionCommand()==_Interface._btn_salir){
			logic.exit();
		}
		if (event.getActionCommand()==_Interface._btn_buscar_usuario){
		
		}
		if (event.getActionCommand()==_Interface._btn_buscar_background){
		
		}
		if (event.getActionCommand()==_Interface._btn_eliminar){
		
		}
		if (event.getActionCommand()==_Interface._btn_guardar){

		}
		if (event.getActionCommand()==_Interface._btn_aplicaciones){

		}
	}
	
	


}