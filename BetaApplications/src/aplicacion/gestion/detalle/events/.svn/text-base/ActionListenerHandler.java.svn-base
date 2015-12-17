package aplicacion.gestion.detalle.events;
import java.awt.event.ActionEvent;
import aplicacion.gestion.detalle.interfaces.*;
import aplicacion.gestion.detalle.logic.*;
import aplicacion.modelo.events._ActionListenerHandler;

public class ActionListenerHandler extends _ActionListenerHandler{
	public void procesarEvento(ActionEvent e){
		_Logic _logic=(_Logic) this._logic;
		if (e.getActionCommand()==_Interface._btn_cancelar){
			_logic.cancelar();
		}
		if (e.getActionCommand()==_Interface._btn_reporte){
		//	_logic.reporte();
		}
		if (e.getActionCommand()==_Interface._btn_mostrar){
			_logic.cargar();
		}
		
		if (e.getActionCommand()==_Interface._btn_salir){
			_logic.exit();
		}
		
		if (e.getActionCommand()==_Interface._btn_error){
			_logic.sendInfo();
		}
	}
}
