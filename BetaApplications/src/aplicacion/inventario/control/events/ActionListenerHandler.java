package aplicacion.inventario.control.events;

import java.awt.event.ActionEvent;

import aplicacion.inventario.control.interfaces._Interface;
import aplicacion.inventario.control.logic._Logic;
import aplicacion.modelo.events._ActionListenerHandler;

public class ActionListenerHandler extends _ActionListenerHandler {
	
	public void procesarEvento(ActionEvent event){
		_Logic logic=(_Logic) this._logic;
		if (event.getActionCommand()==_Interface._btn_cancelar){
			logic.cancelar();
		}
		if (event.getActionCommand()==_Interface._btn_buscar_deposito){
			logic.BuscarDeposito();
		}
		if (event.getActionCommand()==_Interface._btn_autocompletar){
			logic.goAutocompletarStock();
		}
		if (event.getActionCommand()==_Interface._btn_cargar){
			logic.cargar();
		}
		if (event.getActionCommand()==_Interface._btn_check){
			logic.check();
		}
		if (event.getActionCommand()==_Interface._btn_nuevo){
			logic.nuevo();
		}
		if (event.getActionCommand()==_Interface._btn_etiquetar){
			logic.Etiquetar();
		}
		if (event.getActionCommand()==_Interface._btn_imprimir_etiquetas){
			logic.imprimir_etiquetas();
		}
		if (event.getActionCommand()==_Interface._btn_guardar){
			logic.Guardar();
		}
		if (event.getActionCommand()==_Interface._btn_eliminar){
			logic.eliminar();
		}
		if (event.getActionCommand()==_Interface._btn_eliminar_stock){
			logic.eliminar_aplicacion();
		}
		if (event.getActionCommand()==_Interface._btn_aplicar_stock){
			logic.aplicarStock();
		}
		if (event.getActionCommand()==_Interface._btn_buscar_fecha_limite){
			logic.BuscarFechaLimite();
		}
		if (event.getActionCommand()==_Interface._btn_salir){
			logic.exit();
		}
		if (event.getActionCommand()==_Interface._btn_error){
			logic.sendInfo();
		}
		if (event.getActionCommand()==_Interface._btn_imprimir){
			logic.reporte();
		}
		if (event.getActionCommand()==_Interface._btn_importar){
			logic.importar();
		}
		
	}
	
	


}