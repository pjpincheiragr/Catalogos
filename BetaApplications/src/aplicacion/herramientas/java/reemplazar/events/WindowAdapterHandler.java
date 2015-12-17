package aplicacion.herramientas.java.reemplazar.events;
import java.awt.event.WindowEvent;
import aplicacion.modelo.events._WindowAdapterHandler;
import aplicacion.herramientas.java.reemplazar.interfaces.*;
import aplicacion.herramientas.java.reemplazar.logic.*;

public class WindowAdapterHandler extends _WindowAdapterHandler{
	
	public void procesarEvento(WindowEvent we){
		if (we.getID()==WindowEvent.WINDOW_CLOSING){
			_Logic _logic=(_Logic) this._logic;
			_logic.exit();	
		}
	}
}
