package aplicacion.inventario.reporteMovimiento.events;
import java.awt.event.ActionEvent;
import aplicacion.inventario.reporteLinea.interfaces.*;
import aplicacion.inventario.reporteLinea.logic.*;
import aplicacion.modelo.events._ActionListenerHandler;

public class ActionListenerHandler extends _ActionListenerHandler{
	public void procesarEvento(ActionEvent e){
		_Logic _logic=(_Logic) this._logic;
		if (e.getActionCommand()==_Interface._btn_cancelar){
			_logic.cancelar();
		}
		if (e.getActionCommand()==_Interface._btn_reporte){
			_logic.reporte();
		}
		if (e.getActionCommand()==_Interface._btn_salir){
			_logic.exit();
		}
		
		if (e.getActionCommand()==_Interface._btn_error){
			_logic.sendInfo();
		}
		if (e.getActionCommand()==_Interface._btn_buscar_idproveedor){
			_logic.BuscarProveedor();
		}
		
	}
}
