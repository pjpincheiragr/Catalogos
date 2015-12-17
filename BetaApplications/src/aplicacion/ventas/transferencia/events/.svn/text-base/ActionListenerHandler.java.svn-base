package aplicacion.ventas.transferencia.events;
import java.awt.event.ActionEvent;
import aplicacion.ventas.transferencia.interfaces.*;
import aplicacion.ventas.transferencia.logic.*;
import aplicacion.modelo.events._ActionListenerHandler;

public class ActionListenerHandler extends _ActionListenerHandler{
	public void procesarEvento(ActionEvent e){
		_Logic _logic=(_Logic) this._logic;
		if (e.getActionCommand()==_Interface._btn_cancelar){
			_logic.cancelar();
		}
		if (e.getActionCommand()==_Interface._btn_eliminar){
			_logic.eliminar();
		}
		if (e.getActionCommand()==_Interface._btn_transferir){
			_logic.transferir();
		}
		
		if (e.getActionCommand()==_Interface._btn_salir){
			_logic.exit();
		}
		
		
		
		if (e.getActionCommand()==_Interface._btn_error){
			_logic.sendInfo();
		}
	}
}
