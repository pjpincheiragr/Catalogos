package aplicacion.cliente.corrector.events;
import java.awt.event.WindowEvent;
import aplicacion.modelo.events._WindowAdapterHandler;
import aplicacion.cliente.corrector.interfaces.*;
import aplicacion.cliente.corrector.logic.*;

public class WindowAdapterHandler extends _WindowAdapterHandler{
	
	public void procesarEvento(WindowEvent we){
		if (we.getID()==WindowEvent.WINDOW_CLOSING){
			_Logic _logic=(_Logic) this._logic;
			_logic.exit();	
		}
	}
}
