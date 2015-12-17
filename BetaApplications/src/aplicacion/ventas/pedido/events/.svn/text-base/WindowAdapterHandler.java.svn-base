package aplicacion.ventas.pedido.events;
import java.awt.event.WindowEvent;
import aplicacion.ventas.pedido.logic._Logic;
import aplicacion.ventas.pedido.interfaces._Interface;
import aplicacion.modelo.events._WindowAdapterHandler;

public class WindowAdapterHandler extends _WindowAdapterHandler{
	
	public void procesarEvento(WindowEvent we){
		if (we.getID()==WindowEvent.WINDOW_CLOSING){
			_Logic _logic=(_Logic) this._logic;
			
			if (we.getWindow().getName()==_Interface._frame){
				_logic.exit();	
			}
			if (we.getWindow().getName()==_Interface._faltantes){
				
				_logic.dispose_faltantes();
			}
			if (we.getWindow().getName()==_Interface._importar){
				_logic.cancelar_importar();
			}
			if (we.getWindow().getName()==_Interface._remito){
				_logic.cancelar_remito();
			}	
		}
	}
}
