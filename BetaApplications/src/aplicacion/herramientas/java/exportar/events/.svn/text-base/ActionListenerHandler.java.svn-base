package aplicacion.herramientas.java.exportar.events;
import java.awt.event.ActionEvent;
import aplicacion.herramientas.java.exportar.interfaces.*;
import aplicacion.herramientas.java.exportar.logic.*;
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
		
		if (e.getActionCommand()==_Interface._btn_exportar){
			_logic.guardar();
		}
		
		
		if (e.getActionCommand()==_Interface._btn_buscar_archivo){
			_logic.buscar_archivo();
		}
		
		
	}
}
