package aplicacion.compras.carga.pedido.events;
import aplicacion.compras.carga.pedido.interfaces._Interface;
import aplicacion.compras.carga.pedido.logic.*;
import aplicacion.modelo.events._ActionListenerHandler;


import java.awt.event.ActionEvent;

import javax.swing.JCheckBox;
public class ActionListenerHandler extends _ActionListenerHandler{
 
	public void procesarEvento(ActionEvent event){
		_Logic logic=(_Logic) this._logic;
		if (event.getActionCommand()==_Interface._btn_cancelar_operacion){
			logic._cancelar();
			} 
		if (event.getActionCommand()==_Interface._btn_proyectado){
			logic.goCalculateProyectado();
			}
		if (event.getActionCommand()==_Interface._btn_eliminar){
			logic.eliminar();
			}
		if (event.getActionCommand()==_Interface._btn_critico){
			logic.goCalculateCritico();
			} 
		if (event.getActionCommand()==_Interface._btn_salir){
			logic.exit();
		}
		
			if (event.getActionCommand()==_Interface._btn_cancelar){
				logic.cancelar();
			}
			
			if (event.getActionCommand()==_Interface._btn_error){
				logic.sendInfo();
			}

			if (event.getActionCommand()==_Interface._btn_quitar_linea){
				logic.removeLinea();
			}

			if (event.getActionCommand()==_Interface._btn_agregar_linea){
				logic.importar_lineas();
			}
			if (event.getActionCommand()==_Interface._btn_importar){
				logic.importar();
			}
			
			if (event.getActionCommand()==_Interface._btn_importar_lineas){
				logic._importar_lineas_seleccionadas();
			}
			if (event.getActionCommand()==_Interface._btn_cerrar_lineas){
				logic.cerrar_lineas();
			}
			if (event.getActionCommand()==_Interface._btn_buscar_fecha){
				logic.BuscarFecha();
			}

			if(event.getActionCommand()==_Interface._btn_cancelar_tarea){
				logic.cancelar_tarea();
			}
			if (event.getActionCommand()==_Interface._btn_agregar_asteriscos){
				logic.agregar_linea("ARTICULOS VARIOS");
			}

			if (event.getActionCommand()==_Interface._btn_nuevo_pedido){
				logic._nuevo();
			}
			if (event.getActionCommand()==_Interface._btn_cargar_proveedor){
				logic.cargar_proveedor();
			} 
			if (event.getActionCommand()==_Interface._btn_editar_proveedor){
				logic.verMaestro();
			}

			if (event.getActionCommand()==_Interface._btn_cargar_lineas){
				//logic.goCargar();
			} 
			
			if (event.getActionCommand()==_Interface._btn_enviar_email){
				logic.enviar_email();
			} 
			if (event.getActionCommand()==_Interface._btn_mostrar_email){
				logic.Enviar();
			}
			if (event.getActionCommand()==_Interface._btn_close_email){
				logic.dispose_email();
			}
			if (event.getActionCommand()==_Interface._btn_buscar_pedido){
				logic.BuscarPedidoProveedor();
			}

			if (event.getActionCommand()==_Interface._btn_control){
				logic.control_stock();
			}
			if (event.getActionCommand()==_Interface._btn_cargar_pedido){
				logic.evaluarPedidoProveedor();
			} 
			if (event.getActionCommand()==_Interface._btn_guardar){
				logic.guardar();
			} 

			if (event.getActionCommand()==_Interface._btn_imprimir){
				logic.imprimir();
			}
			if (event.getActionCommand()==_Interface._btn_fecha_desde){
				logic.buscar_fecha_desde();
			}
			if (event.getActionCommand()==_Interface._btn_fecha_hasta){
				logic.buscar_fecha_hasta();
			}
		
			if (event.getActionCommand()==_Interface._btn_buscar_transporte){
				logic.BuscarTransporte();
			}
			if (event.getActionCommand()==_Interface._btn_editar_transporte){
				logic.editarTransporte();
			}
			if (event.getActionCommand()==_Interface._seleccionar_lineas){
				JCheckBox chk=(JCheckBox)event.getSource();
				logic.seleccionar_lineas(!chk.isSelected());
			}

	}
}
