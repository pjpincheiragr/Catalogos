package aplicacion.sistema.indexer.events;

import java.awt.event.ActionEvent;

import aplicacion.sistema.indexer.interfaces._Interface;
import aplicacion.sistema.indexer.logic._Logic;
import aplicacion.modelo.events._ActionListenerHandler;

public class ActionListenerHandler extends _ActionListenerHandler {
	
	public void procesarEvento(ActionEvent event){
		_Logic logic=(_Logic) this._logic;
		if (event.getActionCommand()==_Interface._btn_cancelar){
			logic.cancelar();
		}
		if (event.getActionCommand()==_Interface._btn_cargar){
			logic.cargar_directorio();
		}
		if (event.getActionCommand()==_Interface._btn_salir){
			logic.exit();
		}
		if (event.getActionCommand()==_Interface._btn_abrir_archivo){
			logic.buscar_archivo();
		}
		
		if (event.getActionCommand()==_Interface._btn_eliminar){
			logic.eliminar();
		}
		if (event.getActionCommand()==_Interface._btn_guardar){

		}
		if (event.getActionCommand()==_Interface._btn_indexar){
			logic.indexar();
		}
		if (event.getActionCommand()==_Interface._btn_quitar_todos){
			logic.quitar_todos();
		}
		if (event.getActionCommand()==_Interface._btn_quitar_indice){

		}
	}
	
	


}