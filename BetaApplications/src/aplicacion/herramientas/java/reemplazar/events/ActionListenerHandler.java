package aplicacion.herramientas.java.reemplazar.events;
import java.awt.event.ActionEvent;
import aplicacion.herramientas.java.reemplazar.interfaces.*;
import aplicacion.herramientas.java.reemplazar.logic.*;
import aplicacion.modelo.events._ActionListenerHandler;

public class ActionListenerHandler extends _ActionListenerHandler{
	public void procesarEvento(ActionEvent e){
		_Logic _logic=(_Logic) this._logic;
		
		
		if (e.getActionCommand()==_Interface._btn_salir){
			_logic.exit();
		}
		if (e.getActionCommand()==_Interface._btn_error){
			_logic.sendInfo();
		}
		if (e.getActionCommand()==_Interface._btn_buscar){
			_logic.search_event();
		}
		if (e.getActionCommand()==_Interface._btn_buscar_reemplazar){
			_logic.search_and_replace_event();
		}
		if (e.getActionCommand()==_Interface._btn_buscar_reemplazar_todo){
			_logic.search_and_replaceall_event();
		}
		if (e.getActionCommand()==_Interface._btn_reemplazar){
			_logic.replace_event();
		}
		if (e.getActionCommand()==_Interface._btn_sacar_repeticiones){
			_logic.search_and_cut_off_event();
		}
		if (e.getActionCommand()==_Interface._btn_sacar_repeticiones_todo){
			_logic.search_and_cut_offall_event();
			
		}
	}
}
