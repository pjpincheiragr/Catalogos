package aplicacion.actualizacion.equivalencia.events;
import java.awt.event.ActionEvent;
import aplicacion.actualizacion.equivalencia.interfaces.*;
import aplicacion.actualizacion.equivalencia.logic.*;
import aplicacion.modelo.events._ActionListenerHandler;
/**
 * @author Agustin Wisky
 * @company Wismi S.A.
 * @since 10-10-2009
 */
public class ActionListenerHandler extends _ActionListenerHandler{
	public void procesarEvento(ActionEvent e){
		_Logic _logic=(_Logic) this._logic;
		if (e.getActionCommand()==_Interface._btn_cancelar){
			_logic.cancelar();
		}
		
		if (e.getActionCommand()==_Interface._btn_salir){
			_logic.exit();
		}
		if (e.getActionCommand()==_Interface._btn_nuevo){
			_logic.nuevo();
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
