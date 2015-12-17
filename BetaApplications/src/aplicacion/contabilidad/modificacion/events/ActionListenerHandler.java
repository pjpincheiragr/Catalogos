package aplicacion.contabilidad.modificacion.events;
import aplicacion.contabilidad.modificacion.interfaces._Interface;
import aplicacion.contabilidad.modificacion.logic.*;
import aplicacion.modelo.events._ActionListenerHandler;
import java.awt.event.ActionEvent;
public class ActionListenerHandler extends _ActionListenerHandler{
 
	public void procesarEvento(ActionEvent event){
		_Logic logic=(_Logic) this._logic;
		if (event.getActionCommand()==_Interface._btn_salir){
			logic.exit();
		}
		
		
		if (event.getActionCommand()==_Interface._btn_cancelar){
			logic.cancelar();
		}
		if (event.getActionCommand()==_Interface._btn_guardar){
			logic.guardar();
		}
		

	}
}
