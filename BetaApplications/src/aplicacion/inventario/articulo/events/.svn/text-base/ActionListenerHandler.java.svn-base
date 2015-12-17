package aplicacion.inventario.articulo.events;

import java.awt.event.ActionEvent;

import aplicacion.inventario.articulo.interfaces._Interface;
import aplicacion.inventario.articulo.logic._Logic;
import aplicacion.modelo.events._ActionListenerHandler;

public class ActionListenerHandler extends _ActionListenerHandler {
	
	public void procesarEvento(ActionEvent event){
		_Logic logic=(_Logic) this._logic;
		if (event.getActionCommand()==_Interface._btn_cancelar){
			logic.cancelar();
		}
		if (event.getActionCommand()==_Interface._btn_recodificar){
			logic.recodificar();
		}
		if (event.getActionCommand()==_Interface._btn_modificar_stock){
			logic.ajustar();
		}
		if (event.getActionCommand()==_Interface._btn_transferir_stock){
			logic.aviso("OPERACION AUN NO IMPLEMENTADA");
		}
		if (event.getActionCommand()==_Interface._btn_cargar){
			logic.cargar();
		}
		if (event.getActionCommand()==_Interface._btn_siguiente_foto){
			logic._siguiente();
		}
		if (event.getActionCommand()==_Interface._btn_anterior_foto){
			logic._anterior();
		}
		
		if (event.getActionCommand()==_Interface._btn_editar_alias){
			logic.editar_alias();
		}
		if (event.getActionCommand()==_Interface._btn_cargar_alias){
			logic.cargar_alias();
		}
		if (event.getActionCommand()==_Interface._btn_sincronizar){
		  logic.sincronizar();
		}
		if (event.getActionCommand()==_Interface._btn_guardar){
			logic.guardar();
		}
		if (event.getActionCommand()==_Interface._btn_eliminar){
			logic.eliminar();
		}
		if (event.getActionCommand()==_Interface._btn_buscar_politica){
			logic.BuscarPolitica();
		}
		
		if (event.getActionCommand()==_Interface._btn_salir){
			logic.exit();
		}
		if (event.getActionCommand()==_Interface._btn_error){
			logic.sendInfo();
		}
		
		if (event.getActionCommand()==_Interface._btn_editar_politica){
			logic.editar_politica();
		}
		if (event.getActionCommand()==_Interface._btn_buscar_articulo){
			logic.BuscarArticulo();
		}
		
		if (event.getActionCommand()==_Interface._btn_buscar_proveedor){
			logic.BuscarProveedor();
		}
		if (event.getActionCommand()==_Interface._btn_buscar_proveedor_actualizacion){
			logic.BuscarProveedorActualizacion();
		}
		if (event.getActionCommand()==_Interface._btn_buscar_linea){
			logic.BuscarLinea();
		}
		if (event.getActionCommand()==_Interface._btn_imprimir_etiquetas){
			logic.Etiquetar();
		}
		if (event.getActionCommand()==_Interface._btn_imprimir_etiqueta){
			logic.imprimir_etiquetas();
		}
		if (event.getActionCommand()==_Interface._btn_zoom_in){
			logic._mover_zoomin();
		}
		if (event.getActionCommand()==_Interface._btn_zoom_out){
			logic._mover_zoomout();
		}
	}
	
	


}