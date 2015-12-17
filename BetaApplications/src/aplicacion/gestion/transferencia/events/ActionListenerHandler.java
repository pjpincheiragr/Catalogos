package aplicacion.gestion.transferencia.events;
import java.awt.event.ActionEvent;

import aplicacion.gestion.transferencia.interfaces.*;
import aplicacion.gestion.transferencia.logic.*;
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
		if (e.getActionCommand()==_Interface._btn_confirmar){
			_logic.confirmar_recepcion();
		}
		if (e.getActionCommand()==_Interface._btn_error){
			_logic.sendInfo();
		}
		if (e.getActionCommand()==_Interface._btn_anular){
			_logic.anular();
		}
		
		
		if (e.getActionCommand()==_Interface._btn_grabar){
			_logic.GrabarTransferencia();
		}
		if (e.getActionCommand()==_Interface._btn_autocompletar){
			_logic.autocompletar();
		}
		
		if (e.getActionCommand()==_Interface._btn_buscar_pago){
			_logic.BuscarTransferencia();
		}
		
		if (e.getActionCommand()==_Interface._btn_buscar_proveedor){
			_logic.BuscarProveedor();
		}
		
		if (e.getActionCommand()==_Interface._btn_fecha){
			_logic.load_calendar();
		}
		
		if (e.getActionCommand()==_Interface._btn_fecha_desde){
			_logic.load_calendar_desde();
		}
		
		if (e.getActionCommand()==_Interface._btn_fecha_hasta){
			_logic.load_calendar_hasta();
		}
		if (e.getActionCommand()==_Interface._btn_buscar_cheques_en_cartea){
			
			
		}
	}
}
