package aplicacion.herramientas.java.msortableselector.events;
import java.awt.event.WindowEvent;
import aplicacion.herramientas.java.msortableselector.interfaces.*;
import aplicacion.herramientas.java.msortableselector.logic.*;
import aplicacion.modelo.events._WindowAdapterHandler;

public class WindowAdapterHandler extends _WindowAdapterHandler{
	
	public void procesarEvento(WindowEvent we){
		if (we.getID()==WindowEvent.WINDOW_CLOSING){
			_Logic _logic=(_Logic) this._logic;
			_logic.exit();	
		}
	}
}
