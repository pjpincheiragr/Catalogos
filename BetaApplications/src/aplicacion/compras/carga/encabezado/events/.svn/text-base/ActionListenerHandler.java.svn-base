package aplicacion.compras.carga.encabezado.events;

import java.awt.event.ActionEvent;
import aplicacion.compras.carga.encabezado.interfaces._Interface;
import aplicacion.compras.carga.encabezado.logic._Logic;
import aplicacion.modelo.events._ActionListenerHandler;

public class ActionListenerHandler extends _ActionListenerHandler{
	public void procesarEvento(ActionEvent e){
		_Logic logic=(_Logic) this._logic;
		System.out.println("Click!!> "+e.getActionCommand());
		if (e.getActionCommand()==_Interface._btn_editar_proveedor){
			
			logic.editar_proveedor();
		}
		if (e.getActionCommand()==_Interface._btn_buscar_proveedor){
			
			logic.BuscarProveedor();
		}
		if (e.getActionCommand()==_Interface._btn_grabar){
			
			logic._facturador();
		}
		if (e.getActionCommand()==_Interface._btn_grabar_facturador){
			
			logic.guardar();
		}
		if (e.getActionCommand()==_Interface._btn_abrir){
			logic.addFoto();
		}
		if (e.getActionCommand()==_Interface._btn_scan){
			logic.scan();
		}
		if (e.getActionCommand()==_Interface._btn_salir){
			logic.exit();
		}
		if (e.getActionCommand()==_Interface._btn_error){
			logic.sendInfo();
		}
		if (e.getActionCommand()==_Interface._btn_cancelar){
			logic.cancelar();
		}
		if (e.getActionCommand()==_Interface._btn_eliminar){
			logic.eliminar();
		}
		if (e.getActionCommand()==_Interface._btn_buscar_fecha_contable){
			logic._load_fecha_contable();
		}
		if (e.getActionCommand()==_Interface._btn_buscar_fecha_factura){
			logic._load_fecha_factura();
		}
		if (e.getActionCommand()==_Interface._btn_buscar_fecha_vencimiento){
			logic._load_fecha_vencimiento();
		}
		if (e.getActionCommand()==_Interface._btn_recargar_datos_proveedor){
			logic._recargar_proveedor();
		}
	}

}
