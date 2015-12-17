package aplicacion.inventario.linea.events;

import java.awt.event.ActionEvent;

import aplicacion.inventario.linea.interfaces._Interface;
import aplicacion.inventario.linea.logic._Logic;
import aplicacion.modelo.events._ActionListenerHandler;

public class ActionListenerHandler extends _ActionListenerHandler {
	
	public void procesarEvento(ActionEvent event){
		_Logic logic=(_Logic) this._logic;
		if (event.getActionCommand()==_Interface._btn_cancelar){
			logic.cancelar();
		}
		if (event.getActionCommand()==_Interface._btn_cargar){
			logic.cargar();
		}
		if (event.getActionCommand()==_Interface._btn_cargar_linea){
			logic.cargar_lineas();
		}
		if (event.getActionCommand()==_Interface._btn_etiquetar){
			logic.Etiquetar();
		}
		if (event.getActionCommand()==_Interface._btn_arctualizar_archivo){
			logic.actualizar_por_archivo();
		}
		if (event.getActionCommand()==_Interface._btn_arctualizar_odbc){
			logic.actualizar_por_odbc();
		}
		if (event.getActionCommand()==_Interface._btn_editar){
			logic.editar();
		}
		if (event.getActionCommand()==_Interface._btn_editar_actualizacion){
			logic.editar_actualizacion();
		}
		if (event.getActionCommand()==_Interface._btn_editar_alias){
			logic.editar_alias();
		}
		if (event.getActionCommand()==_Interface._btn_planilla_codigos){
			logic.editar_codigos();
		}
		if (event.getActionCommand()==_Interface._btn_imprimir_etiquetas){
			logic.imprimir_etiquetas();
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
		if (event.getActionCommand()==_Interface._btn_pedido_cargar){
			logic.cargar_pedido();
		}
		if (event.getActionCommand()==_Interface._btn_nuevo_pedido){
			logic.nuevo_pedido();
		}
		
	}
	
	


}