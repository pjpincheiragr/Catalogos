package aplicacion.ventas.catalogo.events;
import java.awt.event.ActionEvent;

import aplicacion.ventas.catalogo.interfaces.*;
import aplicacion.ventas.catalogo.logic.*;
import aplicacion.modelo.events._ActionListenerHandler;

public class ActionListenerHandler extends _ActionListenerHandler{
	public void procesarEvento(ActionEvent e){
		_Logic _logic=(_Logic) this._logic;
		
		if (e.getActionCommand()==_Interface._btn_buscar){
			_logic.goCargar();
		}
		if (e.getActionCommand()==_Interface._btn_exportar){
			_logic.exportar();
		}
		if (e.getActionCommand()==_Interface._btn_cancelar){
			_logic.clean();
		}
		
		if (e.getActionCommand()==_Interface._btn_salir){
			_logic.salir();
		}
		if (e.getActionCommand()==_Interface._btn_buscar_mas){
			_logic.goBuscarEquivalencias();
		}
		if (e.getActionCommand()==_Interface._btn_foto_anterior){
			_logic._anterior();
		}
		if (e.getActionCommand()==_Interface._btn_foto_siguiente){
			_logic._siguiente();
		}
		if (e.getActionCommand()==_Interface._btn_foto_zoom_in){
			_logic._mover_zoomin();
		}
		if (e.getActionCommand()==_Interface._btn_foto_zoom_out){
			_logic._mover_zoomout();
		}
		if (e.getActionCommand()==_Interface._btn_error){
			_logic.sendInfo();
		}
		
	}
}
