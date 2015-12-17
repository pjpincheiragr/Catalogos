package aplicacion.cliente.corrector.events;
import java.awt.event.ActionEvent;
import aplicacion.cliente.corrector.interfaces.*;
import aplicacion.cliente.corrector.logic.*;
import aplicacion.modelo.events._ActionListenerHandler;

public class ActionListenerHandler extends _ActionListenerHandler{
	public void procesarEvento(ActionEvent e){
		_Logic _logic=(_Logic) this._logic;
		if (e.getActionCommand()==_Interface._btn_cancelar){
			_logic.cancelar();
		}
		
		if (e.getActionCommand()==_Interface._btn_cancelar_tarea){
			_logic.cancelar_tarea();
		}
		
		if (e.getActionCommand()==_Interface._btn_cargar){
			_logic.goCargar();
		}
		
		if (e.getActionCommand()==_Interface._btn_seleccionar_pendientes){
			_logic.seleccionar_pendientes();
		}
		if (e.getActionCommand()==_Interface._btn_buscar_cliente){
			_logic.BuscarCliente();
		}
		
		if (e.getActionCommand()==_Interface._btn_salir){
			_logic.exit();
		}
		
		if (e.getActionCommand()==_Interface._btn_error){
			_logic.sendInfo();
		}
		
	}
}
