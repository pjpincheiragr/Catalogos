package aplicacion.ventas.gestion2.events;
import java.awt.event.WindowEvent;
import aplicacion.ventas.gestion2.logic._Logic;
import aplicacion.ventas.gestion2.interfaces._Interface;
import aplicacion.modelo.events._WindowAdapterHandler;

public class WindowAdapterHandler extends _WindowAdapterHandler{
	
	public void procesarEvento(WindowEvent we){
		if (we.getID()==WindowEvent.WINDOW_CLOSING){
			_Logic _logic=(_Logic) this._logic;
			
			if (we.getWindow().getName()==_Interface._frame){
				_logic.exit();	
			}
			if (we.getWindow().getName()==_Interface._transferencia){
				_logic.dispose_transferencia();
			}
			if (we.getWindow().getName()==_Interface._categoria){
				_logic.dispose_categoria();
			}	
		}
	}
}
