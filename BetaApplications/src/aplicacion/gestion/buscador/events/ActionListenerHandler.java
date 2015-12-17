package aplicacion.gestion.buscador.events;

import java.awt.event.ActionEvent;
import aplicacion.gestion.buscador.interfaces.*;
import aplicacion.gestion.buscador.logic.*;
import aplicacion.modelo.events._ActionListenerHandler;

public class ActionListenerHandler extends _ActionListenerHandler{
	public void procesarEvento(ActionEvent e){
		_Logic _logic=(_Logic) this._logic;
		if (e.getActionCommand()==_Interface._btn_cancelar){
			_logic.cancelar();
		}
		
		
		if (e.getActionCommand()==_Interface._btn_salir){
			_logic.exit();
		}
		if (e.getActionCommand()==_Interface._btn_error){
			_logic.sendInfo();
		}
		if (e.getActionCommand()==_Interface._btn_buscar_fecha_hasta){
			_logic.BuscarFechaHasta();
		}
		if (e.getActionCommand()==_Interface._btn_buscar_fecha_desde){
			_logic.BuscarFechaDesde();
		}
		if (e.getActionCommand()==_Interface._btn_salir){
			_logic.exit();
		}
	}
}
