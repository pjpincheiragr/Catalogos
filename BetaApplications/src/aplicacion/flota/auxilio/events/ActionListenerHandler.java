package aplicacion.flota.auxilio.events;

import java.awt.event.ActionEvent;

import aplicacion.flota.auxilio.interfaces._Interface;
import aplicacion.flota.auxilio.logic._Logic;
import aplicacion.modelo.events._ActionListenerHandler;

public class ActionListenerHandler extends _ActionListenerHandler {
	
	public void procesarEvento(ActionEvent event){
		_Logic logic=(_Logic) this._logic;
		
		
		if(event.getActionCommand() == _Interface._btn_buscar){
			logic.buscar();
		}

		if(event.getActionCommand() == _Interface._btn_buscarAuxilio){
			logic.buscarAuxilio();
		}

		if(event.getActionCommand() == _Interface._btn_buscarChofer){
			logic.buscarChofer();
		}

		if(event.getActionCommand() == _Interface._btn_buscarChoferAuxilio){
			logic.buscarChoferAuxilio();
		}
		if(event.getActionCommand() == _Interface._btn_buscarReemplazo){
			logic.buscarReemplazo();
		}
		if(event.getActionCommand() == _Interface._btn_buscarUnidad){
			logic.buscarUnidad();
		}
		if(event.getActionCommand() == _Interface._btn_calendario){
			logic.BuscarFecha();
		}
		if(event.getActionCommand() == _Interface._btn_cancelar){
			logic.clean();
		}
		if(event.getActionCommand() == _Interface._btn_eliminar){
			logic.eliminar();	
		}
		if(event.getActionCommand() == _Interface._btn_guardar){
			logic.guardar();
		}
		if(event.getActionCommand() == _Interface._btn_salir){
		logic.exit();	
		}
		if(event.getActionCommand() == _Interface._btn_editarUnidad){
			logic.editarUnidad();	
			}
		if(event.getActionCommand() == _Interface._btn_editarUnidad1){
			logic.editarUnidad();	
			}

		if(event.getActionCommand() == _Interface._btn_editarUnidad2){
			logic.editarUnidad();	
			}




		
	}
	
	


}