package aplicacion.inventario.transporte.events;

import java.awt.event.ActionEvent;

import aplicacion.inventario.transporte.interfaces._Interface;
import aplicacion.inventario.transporte.logic._Logic;
import aplicacion.modelo.events._ActionListenerHandler;

public class ActionListenerHandler extends _ActionListenerHandler {
	
	public void procesarEvento(ActionEvent event){
		_Logic logic=(_Logic) this._logic;
		if (event.getActionCommand()==_Interface._btn_cancelar){
			logic.cancelar();
		}
		if (event.getActionCommand()==_Interface._btn_cargar){
			logic.Cargar();
		}
		if (event.getActionCommand()==_Interface._btn_buscar_transporte){
			logic.BuscarTransporte();
		}
		if (event.getActionCommand()==_Interface._btn_salir){
			logic.exit();
		}
		if (event.getActionCommand()==_Interface._btn_error){
			logic.sendInfo();
		}
		if (event.getActionCommand()==_Interface._btn_guardar){
			logic.Guardar();
		}
		if (event.getActionCommand()==_Interface._btn_nuevo){
			logic.nuevo();
		}
		
	}
	
	
	


}