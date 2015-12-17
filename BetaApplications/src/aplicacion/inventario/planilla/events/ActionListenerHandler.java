package aplicacion.inventario.planilla.events;

import java.awt.event.ActionEvent;

import aplicacion.inventario.planilla.interfaces._Interface;
import aplicacion.inventario.planilla.logic._Logic;
import aplicacion.modelo.events._ActionListenerHandler;

public class ActionListenerHandler extends _ActionListenerHandler {
	
	public void procesarEvento(ActionEvent event){
		_Logic logic=(_Logic) this._logic;
		if (event.getActionCommand()==_Interface._btn_cancelar){
			logic.cancelar();
		}
		if (event.getActionCommand()==_Interface._btn_autoconfigurar){
			logic.goAutoConfigurar();
		}
		if (event.getActionCommand()==_Interface._btn_cancelar_tarea){
			logic.doCancel();
		}
		if (event.getActionCommand()==_Interface._btn_editar_articulo){
			logic.editar_articulo();
		}
		if (event.getActionCommand()==_Interface._btn_cargar){
			logic.goCargar();
		}
		if (event.getActionCommand()==_Interface._btn_sincronizar){
			logic.goSincronizar();
		}
		if (event.getActionCommand()==_Interface._btn_guardar){
			logic.goGuardar();
		}
		if (event.getActionCommand()==_Interface._btn_eliminar){
			logic.eliminar();
		}
		if (event.getActionCommand()==_Interface._btn_completar){
			logic.goCompletar();
		}
		if (event.getActionCommand()==_Interface._btn_salir){
			logic.exit();
		}
		if (event.getActionCommand()==_Interface._btn_error){
			logic.sendInfo();
		}
		if (event.getActionCommand()==_Interface._btn_copiar_memoria){
			logic.copiar();
		}
		if (event.getActionCommand()==_Interface._btn_pegar_memoria){
			logic.pegar();
		}
		if (event.getActionCommand()==_Interface._btn_politicas_de_precios){
			logic.editar_politica();
		}
		if (event.getActionCommand()==_Interface._btn_buscar_articulo_desde){
			logic.BuscarArticuloDesde();
		}
		if (event.getActionCommand()==_Interface._btn_buscar_articulo_hasta){
			logic.BuscarArticuloHasta();
		}
		if (event.getActionCommand()==_Interface._btn_buscar_proveedor){
			logic.BuscarProveedor();
		}
		if (event.getActionCommand()==_Interface._btn_buscar_linea){
			logic.BuscarLinea();
		}
		if (event.getActionCommand()==_Interface._btn_importar){
			logic.importar();
		}
		if (event.getActionCommand()==_Interface._btn_exportar){
			logic.exportar();
		}
		if (event.getActionCommand()==_Interface._btn_buscar_reemplazar){
			logic.BuscarReemplazar();
		}
		if (event.getActionCommand()==_Interface._btn_incrementar){
			logic.goIncrementar();
		}
	}
	
	


}