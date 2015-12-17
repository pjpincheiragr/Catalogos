package aplicacion.inventario.etiqueta.events;

import java.awt.event.ActionEvent;

import aplicacion.inventario.etiqueta.interfaces._Interface;
import aplicacion.inventario.etiqueta.logic._Logic;
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
		if (event.getActionCommand()==_Interface._btn_cargar){
			
		}
		if (event.getActionCommand()==_Interface._btn_cargar_stock){
			logic.cargar_stock();
		}
		
		if (event.getActionCommand()==_Interface._btn_nuevo){
			logic.nuevo();
		}
		if (event.getActionCommand()==_Interface._btn_imprimir_etiquetas){
			logic.imprimir();
		}
		if (event.getActionCommand()==_Interface._btn_guardar){
		
		}
		if (event.getActionCommand()==_Interface._btn_guardar_historial){
			logic.guardar_historial();
		}
		if (event.getActionCommand()==_Interface._btn_marcar_impresas){
			logic.marcar(true);
		}
		if (event.getActionCommand()==_Interface._btn_desmarcar_impresas){
			logic.marcar(false);
		}
		if (event.getActionCommand()==_Interface._btn_eliminar){
		
		}
		if (event.getActionCommand()==_Interface._btn_importar){
			logic.importar_critico();
		}
		if (event.getActionCommand()==_Interface._btn_clean){
			logic.clean_importacion();
		}
		if (event.getActionCommand()==_Interface._btn_quitar){
			logic.quitar_items();
		}
		if (event.getActionCommand()==_Interface._btn_eliminar_stock){
		
		}
		if (event.getActionCommand()==_Interface._btn_aplicar_stock){

		}
		if (event.getActionCommand()==_Interface._btn_buscar_fecha_desde){
			logic.BuscarFechaDesde();
		}
		if (event.getActionCommand()==_Interface._btn_buscar_fecha_hasta){
			logic.BuscarFechaHasta();
		}
		if (event.getActionCommand()==_Interface._btn_salir){
			logic.exit();
		}
		if (event.getActionCommand()==_Interface._btn_error){
			logic.sendInfo();
		}
		if (event.getActionCommand()==_Interface._btn_imprimir){

		}
		if (event.getActionCommand()==_Interface._btn_cargar_historial){
			logic.cargar_historial();
		}
		
		
	}
	
	


}