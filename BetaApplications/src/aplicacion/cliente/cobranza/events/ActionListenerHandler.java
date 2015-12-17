package aplicacion.cliente.cobranza.events;
import java.awt.event.ActionEvent;
import aplicacion.cliente.cobranza.interfaces._Interface;
import aplicacion.cliente.cobranza.logic.*;
import aplicacion.modelo.events._ActionListenerHandler;


public class ActionListenerHandler extends _ActionListenerHandler{
	public void procesarEvento(ActionEvent e){
		_Logic _logic=(_Logic) this._logic;
		if (e.getActionCommand()==_Interface._btn_cancelar){
			_logic.cancelar();
		}
		if (e.getActionCommand()==_Interface._btn_buscar_cliente){
			_logic.buscar_cliente();
		}
		if (e.getActionCommand()==_Interface._btn_cargar_cliente){
			_logic.cargar_cliente();
		}
		if (e.getActionCommand()==_Interface._btn_anular){
			_logic.anular();
		}
		if (e.getActionCommand()==_Interface._btn_nuevo){
			_logic.nuevo();
		}
		if (e.getActionCommand()==_Interface._btn_buscar){
			_logic.BuscarCobranza();
		}
		if (e.getActionCommand()==_Interface._btn_grabar){
			_logic.GrabarCobranza();
		}
		
		if (e.getActionCommand()==_Interface._btn_salir){
			_logic.exit();
		}
		if (e.getActionCommand()==_Interface._btn_error){
			_logic.sendInfo();
		}
		if (e.getActionCommand()==_Interface._btn_fecha){
			_logic.load_calendar();
		}
		if (e.getActionCommand()==_Interface._btn_credito_desde){
			_logic.buscar_credito_desde();
		}
		if (e.getActionCommand()==_Interface._btn_imprimir){
			_logic.imprimir();
		}
		if (e.getActionCommand()==_Interface._btn_credito_hasta){
			_logic.buscar_credito_hasta();
		}
		if (e.getActionCommand()==_Interface._btn_deuda_desde){
			_logic.buscar_deuda_desde();
		}
		if (e.getActionCommand()==_Interface._btn_deuda_hasta){
			_logic.buscar_deuda_hasta();
		}
		if (e.getActionCommand()==_Interface._btn_imprimir_cptes){
			_logic.imprimir_comprobantes();
		}
	}
}
