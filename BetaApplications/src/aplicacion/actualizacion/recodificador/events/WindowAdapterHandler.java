package aplicacion.actualizacion.recodificador.events;
import java.awt.event.WindowEvent;
import aplicacion.modelo.events._WindowAdapterHandler;
import aplicacion.actualizacion.recodificador.interfaces.*;
import aplicacion.actualizacion.recodificador.logic.*;

public class WindowAdapterHandler extends _WindowAdapterHandler{
	
	public void procesarEvento(WindowEvent we){
		if (we.getID()==WindowEvent.WINDOW_CLOSING){
			_Logic _logic=(_Logic) this._logic;
			_logic.exit();	
		}
	}
}
