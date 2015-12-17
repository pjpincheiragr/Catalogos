package aplicacion.flota.vehiculo.events;

import java.awt.event.ActionEvent;

import aplicacion.flota.vehiculo.interfaces._Interface;
import aplicacion.flota.vehiculo.logic._Logic;
import aplicacion.modelo.events._ActionListenerHandler;

public class ActionListenerHandler extends _ActionListenerHandler {
	
	public void procesarEvento(ActionEvent event){
		_Logic logic=(_Logic) this._logic;
		
		
		
		
		if(event.getActionCommand() == _Interface._btn_buscar){
			//logic.buscar();
		}
		
		
		if(event.getActionCommand() == _Interface._btn_calendario){
			//logic.buscar();
		}
		
		
		if(event.getActionCommand() == _Interface._btn_cancelar){
			//logic.buscar();
		}
		
		
		if(event.getActionCommand() == _Interface._btn_eliminar){
			//logic.buscar();
		}
		
		
		if(event.getActionCommand() == _Interface._btn_eliminarAuxilio){
			//logic.buscar();
		}
		
		
		if(event.getActionCommand() == _Interface._btn_eliminarEquipamiento){
			//logic.buscar();
		}
		
		
		if(event.getActionCommand() == _Interface._btn_eliminarNovedad){
			//logic.buscar();
		}
		
		
		if(event.getActionCommand() == _Interface._btn_eliminarPlanificacion){
			//logic.buscar();
		}
		
		
		if(event.getActionCommand() == _Interface._btn_eliminarTarjeta){
			//logic.buscar();
		}
		
		
		if(event.getActionCommand() == _Interface._btn_eliminarTrabajo){
			//logic.buscar();
		}
		
		
		if(event.getActionCommand() == _Interface._btn_guardar){
			//logic.buscar();
		}
		
		
		if(event.getActionCommand() == _Interface._btn_nuevaNovedad){
			//logic.buscar();
		}
		
		
		if(event.getActionCommand() == _Interface._btn_nuevaPlanificacion){
			//logic.buscar();
		}
		
		
		if(event.getActionCommand() == _Interface._btn_nuevaTarjeta){
			//logic.buscar();
		}
		
		
		if(event.getActionCommand() == _Interface._btn_nuevoAuxilio){
			//logic.buscar();
		}
		
		
		if(event.getActionCommand() == _Interface._btn_nuevoEquipamiento){
			//logic.buscar();
		}
		
		
		if(event.getActionCommand() == _Interface._btn_nuevoTrabajo){
			//logic.buscar();
		}
		
		
		if(event.getActionCommand() == _Interface._btn_salir){
			//logic.buscar();
		}

	}
	
	


}