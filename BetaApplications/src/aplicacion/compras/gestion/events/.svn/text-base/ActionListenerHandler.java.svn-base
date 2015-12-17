package aplicacion.compras.gestion.events;
import aplicacion.compras.gestion.interfaces._Interface;
import aplicacion.compras.gestion.logic.*;
import aplicacion.modelo.events._ActionListenerHandler;
import java.awt.event.ActionEvent;
public class ActionListenerHandler extends _ActionListenerHandler{
 
	public void procesarEvento(ActionEvent event){
		_Logic logic=(_Logic) this._logic;
		if (event.getActionCommand()==_Interface._btn_salir){
			logic.exit();
		}
		if (event.getActionCommand()==_Interface._btn_cargar){
			logic.goCargar();
		}
		
		if (event.getActionCommand()==_Interface._btn_cancelar){
			logic.cancelar();
		}
		if (event.getActionCommand()==_Interface._btn_editar){
			logic.editar_pedido();
		}
		if (event.getActionCommand()==_Interface._btn_nuevo){
			logic.nuevo_pedido();
		}
		
		if (event.getActionCommand()==_Interface._btn_error){
			logic.sendInfo();
		}
		
		

	}
}
