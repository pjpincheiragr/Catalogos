package aplicacion.cliente.gestion.events;
import java.awt.event.ActionEvent;
import aplicacion.cliente.gestion.interfaces.*;
import aplicacion.cliente.gestion.logic.*;
import aplicacion.modelo.events._ActionListenerHandler;

public class ActionListenerHandler extends _ActionListenerHandler{
	public void procesarEvento(ActionEvent e){
		_Logic _logic=(_Logic) this._logic;
		if (e.getActionCommand()==_Interface._btn_cancelar){
			_logic.cancelar();
		}
		
		if (e.getActionCommand()==_Interface._btn_cancelar_tarea){
			_logic.cancelar_tarea();
		}
		
		if (e.getActionCommand()==_Interface._btn_cargar){
			_logic.goCargar();
		}
		
		if (e.getActionCommand()==_Interface._btn_reporte){
			_logic.reporte();
		}
		
		if (e.getActionCommand()==_Interface._btn_maestro){
			_logic.verMaestro();
		}
		
		if (e.getActionCommand()==_Interface._btn_salir){
			_logic.exit();
		}
		if (e.getActionCommand()==_Interface._btn_error){
			_logic.sendInfo();
		}
	}
}
