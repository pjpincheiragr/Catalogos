package aplicacion.compras.pedidoe.events;
import java.awt.event.ActionEvent;

import aplicacion.compras.pedidoe.interfaces.*;
import aplicacion.compras.pedidoe.logic.*;

import aplicacion.modelo.events._ActionListenerHandler;


public class ActionListenerHandler extends _ActionListenerHandler{
	public void procesarEvento(ActionEvent e){
		
		_Logic _logic=(_Logic) this._logic;
		
		if (e.getActionCommand()==_Interface._btn_salir){
			_logic.exit();
		}
		if (e.getActionCommand()==_Interface._btn_nuevo){
			_logic.nuevo();
		}
		if (e.getActionCommand()==_Interface._btn_enviar_email){
			_logic.enviar_email();
		}
		if (e.getActionCommand()==_Interface._btn_mostrar_email){
			_logic.Enviar();
		}
		if (e.getActionCommand()==_Interface._btn_close_email){
			_logic.dispose_email();
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
	
		
		if (e.getActionCommand()==_Interface._btn_preparar_pedido){
			_logic.preparar();
		}
		if (e.getActionCommand()==_Interface._btn_importar){
			_logic.importar();
		}
		if (e.getActionCommand()==_Interface._btn_importar_critico){
			_logic.importar_critico();
		}
		if (e.getActionCommand()==_Interface._btn_importar_faltantes){
			_logic.importar_faltante();
		}
		if (e.getActionCommand()==_Interface._btn_eliminar_faltantes){
			_logic.eliminar_faltantes_seleccionados();
		}
		if (e.getActionCommand()==_Interface._btn_presupuesto){
			_logic.reporte();
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
			_logic.verMaestro();
		}
		if (e.getActionCommand()==_Interface._btn_editar_transporte){
			_logic.editarTransporte();
		}	
		if (e.getActionCommand()==_Interface._btn_buscar_vendedor){
			_logic.BuscarVendedor();
		}
		if (e.getActionCommand()==_Interface._btn_buscar_pedido){
			_logic.BuscarPDC();
		}
		if (e.getActionCommand()==_Interface._btn_cargar_cliente){
			
		}
		if (e.getActionCommand()==_Interface._btn_imprimir_identificador){
			_logic.Etiquetar();
		}
		 if (e.getActionCommand()==_Interface._btn_imprimir_etiquetas){
			_logic.imprimir_etiquetas();
		}
		 if (e.getActionCommand()==_Interface._btn_cargar_critico){
				_logic.cargar_critico();
		}
		 if (e.getActionCommand()==_Interface._btn_cargar_faltantes){
				_logic.cargar_faltantes();
		}
		if (e.getActionCommand()==_Interface._btn_editar_pedido_cliente){
				_logic.editar_pedido();
		}
	}
}
