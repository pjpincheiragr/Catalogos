package aplicacion.actualizacion.fotos.events;
import java.awt.event.WindowEvent;
import aplicacion.modelo.events._WindowAdapterHandler;
import aplicacion.actualizacion.fotos.interfaces.*;
import aplicacion.actualizacion.fotos.logic.*;

public class WindowAdapterHandler extends _WindowAdapterHandler{
	
	public void procesarEvento(WindowEvent we){
		if (we.getID()==WindowEvent.WINDOW_CLOSING){
			_Logic _logic=(_Logic) this._logic;
			_logic.exit();	
		}
	}
}
