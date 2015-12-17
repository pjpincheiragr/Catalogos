package aplicacion.gestion.rechazados.events;
import java.awt.event.ActionEvent;

import aplicacion.gestion.rechazados.interfaces.*;
import aplicacion.gestion.rechazados.logic.*;
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
		if (e.getActionCommand()==_Interface._btn_anular){
			_logic.anular();
		}
		
		
		if (e.getActionCommand()==_Interface._btn_grabar){
			_logic.GrabarPago();
		}
		
		if (e.getActionCommand()==_Interface._btn_buscar_pago){
			_logic.BuscarPago();
		}
		
		if (e.getActionCommand()==_Interface._btn_buscar_proveedor){
			_logic.BuscarCuenta();
		}
		
		if (e.getActionCommand()==_Interface._btn_fecha){
			_logic.load_calendar();
		}
	}
}
