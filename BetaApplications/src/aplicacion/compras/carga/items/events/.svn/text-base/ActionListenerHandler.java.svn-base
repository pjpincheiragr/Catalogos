package aplicacion.compras.carga.items.events;
import java.awt.event.ActionEvent;
import aplicacion.compras.carga.items.interfaces._Interface;
import aplicacion.compras.carga.items.logic._Logic;
import aplicacion.modelo.events._ActionListenerHandler;



public class ActionListenerHandler extends _ActionListenerHandler{
	
	public void procesarEvento(ActionEvent e){
		System.out.println("Click!!> "+e.getActionCommand());
		_Logic logic=(_Logic) this._logic;
		
		if (e.getActionCommand()==_Interface._btn_arriba){
			logic._mover_arriba();
		}
		if (e.getActionCommand()==_Interface._btn_imprimir){
			logic.imprimir();
		}
		if (e.getActionCommand()==_Interface._btn_recargar_configuracion){
			logic.recargar();
		}
		if (e.getActionCommand()==_Interface._btn_abajo){
			logic._mover_abajo();
		}
		if (e.getActionCommand()==_Interface._btn_izquierda){
			logic._mover_izquierda();
		}
		if (e.getActionCommand()==_Interface._btn_derecha){
			logic._mover_derecha();
		}
		if (e.getActionCommand()==_Interface._btn_zoomin){
			logic._mover_zoomin();
		}
		if (e.getActionCommand()==_Interface._btn_zoomout){
			logic._mover_zoomout();
		}
		if (e.getActionCommand()==_Interface._btn_editar_proveedor){
			logic.editar_proveedor();
		}
		if (e.getActionCommand()==_Interface._btn_rotate_left){
			logic._rotar_izquierda();
		}
		if (e.getActionCommand()==_Interface._btn_rotate_right){
			logic._rotar_derecha();
		}
		if (e.getActionCommand()==_Interface._btn_salir){
			logic.exit();
		}
		if (e.getActionCommand()==_Interface._btn_error){
			logic.sendInfo();
		}
		if (e.getActionCommand()==_Interface._btn_cancelar){
			logic.cancel();
		}
		if (e.getActionCommand()==_Interface._btn_guardar){
			logic.guardar();
			//logic.test_actualizar();
		}
		if (e.getActionCommand()==_Interface._btn_eliminar){
			//
		}
		if (e.getActionCommand()==_Interface._btn_brighten){
			logic._brighten();
		}
		
		if (e.getActionCommand()==_Interface._btn_darken){
			logic._darken();
		}
		if (e.getActionCommand()==_Interface._btn_maximizar){
			logic.centrar_imagen();
		}
		if (e.getActionCommand()==_Interface._btn_hoja_anterior){
			logic._anterior();
		}
		if (e.getActionCommand()==_Interface._btn_hoja_siguiente){
			logic._siguiente();
		}
		if (e.getActionCommand()==_Interface._btn_eliminar_imagen){
			logic.eliminar_foto();
		}
		if (e.getActionCommand()==_Interface._btn_primero){
			logic._primero();
		}
		if (e.getActionCommand()==_Interface._btn_ultimo){
			logic._ultimo();
		}
		if (e.getActionCommand()==_Interface._btn_cargar_proveedor_articulos){
			logic.cargar_proveedor_articulos();
		}
		if (e.getActionCommand()==_Interface._btn_imprimir_etiquetas){
			logic.imprimir_etiquetas();
		}
		if (e.getActionCommand()==_Interface._btn_aplicar_descuento_articulos){
			logic.aplicar_descuento_articulos();
		}
		if (e.getActionCommand()==_Interface._btn_actualizar){
			logic._actualizar();
		}
		if (e.getActionCommand()==_Interface._btn_eliminar_cpte){
			logic.eliminar();
		}
		if (e.getActionCommand()==_Interface._btn_buscar_proveedor){
			logic.buscarProveedor();
		}
		if (e.getActionCommand()==_Interface._btn_buscar_cpte){
			logic.buscarComprobantes();
		}
		
		if (e.getActionCommand()==_Interface._btn_play){
			logic.loadFromXML();
		}
		if (e.getActionCommand()==_Interface._btn_buscar_fecha){
			logic.BuscarFecha();
		}
		if (e.getActionCommand()==_Interface._btn_importar_pep_items){
			logic.importar_pep_items();
		}
		if (e.getActionCommand()==_Interface._btn_abrir){
			logic.addFoto();
		}
		if (e.getActionCommand()==_Interface._btn_scan){
			logic.scan();
		}
	}

}
