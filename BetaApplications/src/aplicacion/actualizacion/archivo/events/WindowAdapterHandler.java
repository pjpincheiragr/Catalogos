package aplicacion.actualizacion.archivo.events;
import java.awt.event.WindowEvent;
import aplicacion.modelo.events._WindowAdapterHandler;
import aplicacion.actualizacion.archivo.interfaces.*;
import aplicacion.actualizacion.archivo.logic.*;
/**
 * @author Agustin Wisky
 * @company Wismi S.A.
 * @since 10-10-2009
 */
public class WindowAdapterHandler extends _WindowAdapterHandler{
	
	public void procesarEvento(WindowEvent we){
		if (we.getID()==WindowEvent.WINDOW_CLOSING){
			_Logic _logic=(_Logic) this._logic;
			_logic.exit();	
		}
	}
}
