package aplicacion.ventas.pedido.events;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;

import aplicacion.ventas.pedido.interfaces.*;
import aplicacion.ventas.pedido.logic.*;


import aplicacion.ventas.pedido.logic.CustomPopup;
import aplicacion.modelo.events._ActionListenerHandler;

public class ActionListenerHandler extends _ActionListenerHandler{
	public void procesarEvento(ActionEvent e){
		
		_Logic _logic=(_Logic) this._logic;
		if (e.getSource() instanceof MenuItem) {
			if (e.getActionCommand()==_Interface._btn_insertar_fila){
				System.out.println("ActionEvent "+e.getSource());
				MenuItem mi=(MenuItem) e.getSource();
				Menu m=(Menu)mi.getParent();
				System.out.println("ActionEvent "+m.getParent());
				
				CustomPopup cp=(CustomPopup) m.getParent();
				_logic.insertarFila(cp.getRow());	
			}
			
		}
		if (e.getActionCommand()==_Interface._btn_salir){
			_logic.exit();
		}
		if (e.getActionCommand()==_Interface._btn_transferir){
			_logic.transferir();
		}
		if (e.getActionCommand()==_Interface._btn_ejecutar_importacion){
			_logic.ejecutar_importacion();
		}
		if (e.getActionCommand()==_Interface._btn_cargar_compras){
			_logic.cargar_compras();
		}
		if (e.getActionCommand()==_Interface._btn_pedidoe){
			_logic.pedir();
		}
		if (e.getActionCommand()==_Interface._btn_nuevo){
			_logic.nuevo();
		}
		
		if (e.getActionCommand()==_Interface._btn_importar_equivalencias){
			_logic.importar_equivalencias();
		}
		if (e.getActionCommand()==_Interface._btn_importar){
			_logic.Importar();
		}
		if (e.getActionCommand()==_Interface._btn_salir_importar){
			_logic.cancelar_importar();
		}
		if (e.getActionCommand()==_Interface._btn_catalogo){
			_logic.BuscarCatalogo();
		}
		if (e.getActionCommand()==_Interface._btn_error){
			_logic.sendInfo();
		}
		if (e.getActionCommand()==_Interface._btn_eliminar){
			_logic.eliminar_pedido();
		}
		if (e.getActionCommand()==_Interface._btn_envio){
			_logic.envio();
		}
		if (e.getActionCommand()==_Interface._btn_copia){
			_logic.copia();
		}
		if (e.getActionCommand()==_Interface._btn_salir_faltantes){
			_logic.dispose_faltantes();
		}
		if (e.getActionCommand()==_Interface._btn_play){
			_logic.loadFromXML();
		}
		if (e.getActionCommand()==_Interface._btn_buscar_fecha_envio){
			_logic.BuscarFechaEnvio();
		}
		if (e.getActionCommand()==_Interface._btn_guardar){
			_logic.guardar();
		}
		if (e.getActionCommand()==_Interface._btn_buscar_transporte){
			_logic.BuscarTransporte();
		}
		if (e.getActionCommand()==_Interface._btn_remito){
			_logic.Remito();
		}
		if (e.getActionCommand()==_Interface._btn_generar_remito){
			_logic.generar_remito_retiro();
		}
		if (e.getActionCommand()==_Interface._btn_generar_retiro){
			_logic.generar_retiro();
		}
		if (e.getActionCommand()==_Interface._btn_preparar_pedido){
			_logic.preparar();
		}
		if (e.getActionCommand()==_Interface._btn_presupuesto){
			_logic.presupuesto();
		}
		if (e.getActionCommand()==_Interface._btn_cancelar){
			_logic.cancelar();
		}
		
		if (e.getActionCommand()==_Interface._btn_buscar_fecha){
			_logic.BuscarFecha();
		}
		if (e.getActionCommand()==_Interface._btn_buscar_cliente){
			_logic.BuscarCliente();
		}
		if (e.getActionCommand()==_Interface._btn_cargar_cliente){
			_logic.cargarCliente();
		}
		if (e.getActionCommand()==_Interface._btn_editar_cliente){
			_logic.editarCliente();
		}
		if (e.getActionCommand()==_Interface._btn_editar_transporte){
			_logic.editarTransporte();
		}
		if (e.getActionCommand()==_Interface._btn_eliminar_remito){
			_logic.eliminar_remito();
		}
		if (e.getActionCommand()==_Interface._btn_buscar_vendedor){
			_logic.BuscarVendedor();
		}
		if (e.getActionCommand()==_Interface._btn_buscar_deposito){
			_logic.BuscarDeposito();
		}
		if (e.getActionCommand()==_Interface._btn_buscar_pedido){
			_logic.BuscarPDC();
		}
		if (e.getActionCommand()==_Interface._btn_cargar_pedido){
			_logic.cargar_pedido();
		}
		if (e.getActionCommand()==_Interface._btn_cargar_cliente){
			
		}
		if (e.getActionCommand()==_Interface._btn_imprimir_identificador){
			_logic.Etiquetar();
		}
		 if (e.getActionCommand()==_Interface._btn_imprimir_etiquetas){
			_logic.imprimir_etiquetas();
		}
		 if (e.getActionCommand()==_Interface._btn_importar_relaciones){
				_logic.importar_relaciones();
			}
		 if (e.getActionCommand()==_Interface._btn_marcar_faltantes){
				_logic.faltantes_manual();
			}
		 if (e.getActionCommand()==_Interface._btn_marcar_faltantes_pedido){
				_logic.faltantes_automaticos();
			}
		 if (e.getActionCommand()==_Interface._btn_faltantes){
			 _logic.FaltantesPedir();
		 }
		 if (e.getActionCommand()==_Interface._btn_aviso){
			 _logic.nuevoAviso();
		 }
		 if (e.getActionCommand()==_Interface._btn_buscar_remitos_generados){
				_logic.cargar_remitos();
		}
		if (e.getActionCommand()==_Interface._btn_cancelar_remito){
				_logic.cancelar_remito();
		}
		if (e.getActionCommand()==_Interface._btn_salir_remito){
			_logic.cancelar_remito();
		}
	}
}
