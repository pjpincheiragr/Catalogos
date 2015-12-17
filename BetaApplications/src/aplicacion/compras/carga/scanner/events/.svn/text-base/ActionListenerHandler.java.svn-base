package aplicacion.compras.carga.scanner.events;
import java.awt.event.ActionEvent;
import aplicacion.compras.carga.scanner.logic.*;
import aplicacion.modelo.events._ActionListenerHandler;
import aplicacion.compras.carga.scanner.interfaces.*;
public class ActionListenerHandler extends _ActionListenerHandler{

	public void procesarEvento(ActionEvent e){
		_Logic logic=(_Logic) this._logic;
		
		if (e.getActionCommand()==_Interface._btn_cancelar){
			
		}
		if (e.getActionCommand()==_Interface._btn_guardar){
			logic.write();
		}
		if (e.getActionCommand()==_Interface._btn_scan){
			
			//logic.acquire();
			logic.OpenImage();
		}
	}
}
