package aplicacion.contabilidad.recodificacion.events;

import java.awt.event.ActionEvent;

import aplicacion.sistema.analizador.interfaces._Interface;
import aplicacion.sistema.analizador.logic._Logic;
import aplicacion.modelo.events._ActionListenerHandler;

public class ActionListenerHandler extends _ActionListenerHandler {
	
	public void procesarEvento(ActionEvent event){
		_Logic logic=(_Logic) this._logic;
		if (event.getActionCommand()==_Interface._btn_cancelar){
			logic.cancelar();
		}
		if (event.getActionCommand()==_Interface._btn_ejecutar){
			logic.goCargar();
		}
		if (event.getActionCommand()==_Interface._btn_salir){
			logic.exit();
			
		}
		if (event.getActionCommand()==_Interface._btn_exportar){
			logic.exportar();
			
		}
	}
	
	


}