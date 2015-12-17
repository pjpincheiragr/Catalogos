package aplicacion.ventas.facturador.events;
import java.awt.event.ActionEvent;

import aplicacion.ventas.facturador.interfaces.*;
import aplicacion.ventas.facturador.logic.*;
import aplicacion.modelo.events._ActionListenerHandler;

public class ActionListenerHandler extends _ActionListenerHandler{
	public void procesarEvento(ActionEvent e){
		
		_Logic _logic=(_Logic) this._logic;
		
		if (e.getActionCommand()==_Interface._btn_salir){
			_logic.exit();
		}
		if (e.getActionCommand()==_Interface._btn_error){
			_logic.sendInfo();
		}
		if (e.getActionCommand()==_Interface._btn_editar_pdc){
			_logic.editar_pedido();
		}
		if (e.getActionCommand()==_Interface._btn_imprimir){
			_logic.imprimir();
		}
		if (e.getActionCommand()==_Interface._btn_guardar){
			_logic.guardar();
		}
		if (e.getActionCommand()==_Interface._btn_cargar_remito){
			
		}
		if (e.getActionCommand()==_Interface._btn_buscar_remito){
			_logic.BuscarRemito();
		}
		if (e.getActionCommand()==_Interface._btn_eliminar_remito){
			_logic.eliminar_remito();
		}
		if (e.getActionCommand()==_Interface._btn_cancelar){
			_logic.cancelar();
		}
		
		if (e.getActionCommand()==_Interface._btn_eliminar){
			_logic.eliminar_fvn();
		}
		
		if (e.getActionCommand()==_Interface._btn_buscar_fecha){
			_logic.BuscarFecha();
		}
	}
}
