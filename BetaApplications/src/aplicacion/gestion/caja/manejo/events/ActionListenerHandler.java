package aplicacion.gestion.caja.manejo.events;
import java.awt.event.ActionEvent;

import aplicacion.gestion.caja.manejo.interfaces.*;
import aplicacion.gestion.caja.manejo.logic.*;
import aplicacion.modelo.events._ActionListenerHandler;

public class ActionListenerHandler extends _ActionListenerHandler{
	public void procesarEvento(ActionEvent e){
		
		_Logic _logic=(_Logic) this._logic;
		if (e.getActionCommand()==_Interface._btn_cheques){
			
			_logic.cheques_en_cartera();
		}
		if (e.getActionCommand()==_Interface._btn_canje){
			_logic.canje();
		}
		if (e.getActionCommand()==_Interface._btn_venta){
			_logic.venta();
		}
		if (e.getActionCommand()==_Interface._btn_cobranza){
			_logic.efectuar_cobranza();
		}
		if (e.getActionCommand()==_Interface._btn_pago){
			_logic.efectuar_pago();
		}
		if (e.getActionCommand()==_Interface._btn_cheque_rechazado){
			_logic.rechazado();
		}
		if (e.getActionCommand()==_Interface._btn_reporte){
			
		}
		if (e.getActionCommand()==_Interface._btn_salir){
			_logic.exit();
		}
		if (e.getActionCommand()==_Interface._btn_error){
			_logic.sendInfo();
		}
		if (e.getActionCommand()==_Interface._btn_ajuste){
			_logic.ajustar();
		}
		if (e.getActionCommand()==_Interface._btn_egreso){
			_logic.egreso();
		}
		
		if (e.getActionCommand()==_Interface._btn_ingreso){
			_logic.ingreso();
		}
		
		if (e.getActionCommand()==_Interface._btn_transferencias){
			_logic.transferencia();
		}
		
		if (e.getActionCommand()==_Interface._btn_cargar){
			_logic.goCargar();
		}
		if (e.getActionCommand()==_Interface._btn_movimientos){
			_logic.ver_movimientos("");
		}
		
		if (e.getActionCommand()==_Interface._btn_reporte){
			_logic.reporte();
		}
		if (e.getActionCommand()==_Interface._btn_fecha_desde){
			_logic.buscar_fecha_desde();
		}
		if (e.getActionCommand()==_Interface._btn_fecha_hasta){
			_logic.buscar_fecha_hasta();
		}
	}
}
