package aplicacion.flota.modelo.events;

import java.awt.event.ActionEvent;

import aplicacion.flota.modelo.interfaces._Interface;
import aplicacion.flota.modelo.logic._Logic;
import aplicacion.modelo.events._ActionListenerHandler;

public class ActionListenerHandler extends _ActionListenerHandler {
	
	public void procesarEvento(ActionEvent event){
		_Logic logic=(_Logic) this._logic;
		
		if(event.getActionCommand() == _Interface._btn_anterior){
			logic.anteriorFoto();
		}
		if(event.getActionCommand() == _Interface._btn_buscar){
			//logic.buscar();
		}
		if(event.getActionCommand() == _Interface._btn_buscarImagen){
			logic.buscar_imagen();
		}
		if(event.getActionCommand() == _Interface._btn_cancelar){
			logic.clean();
		}
		if(event.getActionCommand() == _Interface._btn_eliminar){
			//logic.buscar();
		}
		if(event.getActionCommand() == _Interface._btn_eliminarChequeo){
			//logic.buscar();
		}
		if(event.getActionCommand() == _Interface._btn_eliminarEquipamiento){
			//logic.buscar();
		}
		if(event.getActionCommand() == _Interface._btn_eliminarFoto){
			logic.eliminarFoto();
		}
		if(event.getActionCommand() == _Interface._btn_eliminarMantenimiento){
			//logic.buscar();
		}
		if(event.getActionCommand() == _Interface._btn_goFirst){
			logic.goFirst();
		}
		if(event.getActionCommand() == _Interface._btn_goLast){
			logic.goLast();
		}
		if(event.getActionCommand() == _Interface._btn_guardar){
			//logic.buscar();
		}
		if(event.getActionCommand() == _Interface._btn_nuevoChequeo){
			//logic.buscar();
		}
		if(event.getActionCommand() == _Interface._btn_nuevoEquipamiento){
			//logic.buscar();
		}
		if(event.getActionCommand() == _Interface._btn_nuevoMantenimiento){
			//logic.buscar();
		}
		if(event.getActionCommand() == _Interface._btn_salir){
			//logic.buscar();
		}
		if(event.getActionCommand() == _Interface._btn_siguiente){
			logic.siguienteFoto();
		}

	}
	
	


}