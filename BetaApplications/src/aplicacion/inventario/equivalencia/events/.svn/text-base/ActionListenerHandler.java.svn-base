package aplicacion.inventario.equivalencia.events;
import java.awt.event.ActionEvent;
import aplicacion.inventario.equivalencia.interfaces.*;
import aplicacion.inventario.equivalencia.logic.*;
import aplicacion.modelo.events._ActionListenerHandler;

public class ActionListenerHandler extends _ActionListenerHandler{
	public void procesarEvento(ActionEvent e){
		_Logic _logic=(_Logic) this._logic;
		if (e.getActionCommand()==_Interface._btn_cancelar){
			_logic.cancelar();
		}
		
		if (e.getActionCommand()==_Interface._btn_cancelar_tarea){
			_logic.cancelar_tarea();
		}
		
		if (e.getActionCommand()==_Interface._btn_cargar){
			_logic.goCargar();
		}
		if (e.getActionCommand()==_Interface._btn_guardar){
			_logic.goGuardar();
		}
		
		if (e.getActionCommand()==_Interface._btn_borrar){
			_logic.goBorrar();
		}
		if (e.getActionCommand()==_Interface._btn_salir){
			_logic.exit();
		}
		if (e.getActionCommand()==_Interface._btn_error){
			_logic.sendInfo();
		}
		if (e.getActionCommand()==_Interface._btn_copiar){
			_logic.copiar();
		}
		if (e.getActionCommand()==_Interface._btn_pegar){
			_logic.pegar();
		}
		if (e.getActionCommand()==_Interface._btn_completar){
			_logic.completar();
		}
		
		if (e.getActionCommand()==_Interface._btn_autoalias){
			_logic.goAutocompletar();
		}
		
		if (e.getActionCommand()==_Interface._btn_buscar_articulo_desde){
			_logic.buscar_articulo_desde();
		}
		if (e.getActionCommand()==_Interface._btn_buscar_articulo_hasta){
			_logic.buscar_articulo_hasta();
		}
		if (e.getActionCommand()==_Interface._btn_buscar_linea){
			_logic.BuscarLinea();
		}
		if (e.getActionCommand()==_Interface._btn_buscar_proveedor){
			_logic.BuscarProveedor();
		}
	}
}
