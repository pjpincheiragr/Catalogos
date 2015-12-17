package aplicacion.herramientas.java.importar.events;
import java.awt.event.ActionEvent;
import aplicacion.herramientas.java.importar.interfaces.*;
import aplicacion.herramientas.java.importar.logic.*;
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
		
		
		if (e.getActionCommand()==_Interface._btn_importar){
			_logic.guardar();
		}
		
		if (e.getActionCommand()==_Interface._btn_buscar_archivo){
			_logic.buscar_archivo();
		}
		if (e.getActionCommand()==_Interface._btn_cargar_archivo){
			_logic.cargar_archivo();
		}
		
	}
}
