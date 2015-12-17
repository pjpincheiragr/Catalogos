package aplicacion.ventas.presupuesto.events;
import java.awt.event.ActionEvent;
import aplicacion.ventas.presupuesto.interfaces._Interface;
import aplicacion.ventas.presupuesto.logic.*;
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
		if (e.getActionCommand()==_Interface._btn_cargar){
			_logic.cargar();
		}
		
		if (e.getActionCommand()==_Interface._btn_email){
			_logic.Enviar();
		}
		if (e.getActionCommand()==_Interface._btn_enviar_email){
			_logic.enviar_email();
		}
		if (e.getActionCommand()==_Interface._btn_close_email){
			_logic.dispose_email();
		}
		
		if (e.getActionCommand()==_Interface._btn_buscar_fecha_desde){
			_logic.buscar_fecha_desde();
		}
		
		
	}
}
