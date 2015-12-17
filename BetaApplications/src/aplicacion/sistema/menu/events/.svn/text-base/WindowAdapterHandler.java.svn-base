package aplicacion.sistema.menu.events;
import java.awt.event.WindowEvent;
import aplicacion.sistema.menu.logic._Logic;
import aplicacion.modelo.events._WindowAdapterHandler;

public class WindowAdapterHandler extends _WindowAdapterHandler{
	
	public void procesarEvento(WindowEvent we){
		if (we.getID()==WindowEvent.WINDOW_CLOSING){
			_Logic _logic=(_Logic) this._logic;
			_logic.minimizar();	
		}
	}
}
