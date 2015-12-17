package aplicacion.actualizacion.fotos.events;
import java.awt.event.ActionEvent;
import aplicacion.actualizacion.fotos.interfaces.*;
import aplicacion.actualizacion.fotos.logic.*;
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
		
		
		if (e.getActionCommand()==_Interface._btn_buscar_politica){
			_logic.BuscarPolitica();
		}
		if (e.getActionCommand()==_Interface._btn_actualizar){
			_logic.goUpdate();
		}
		if (e.getActionCommand()==_Interface._btn_buscar_proveedor){
			_logic.BuscarProveedor();
		}
		if (e.getActionCommand()==_Interface._btn_buscar_archivo){
			_logic.buscar_archivo();
		}
		if (e.getActionCommand()==_Interface._btn_cargar_archivo){
			_logic.goRead();
		}
		if (e.getActionCommand()==_Interface._btn_columnas){
			_logic.config();
		}
		if (e.getActionCommand()==_Interface._btn_editar_politica){
			_logic.editar_politica();
		}
		if (e.getActionCommand()==_Interface._btn_nueva_politica){
			_logic.nueva_politica();
		}
	}
}
