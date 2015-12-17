package aplicacion.proveedor.archivo.events;
import java.awt.event.ActionEvent;

import aplicacion.proveedor.archivo.interfaces.*;
import aplicacion.proveedor.archivo.logic.*;

import aplicacion.modelo.events._ActionListenerHandler;

public class ActionListenerHandler extends _ActionListenerHandler{
	public void procesarEvento(ActionEvent e){
		_Logic _logic=(_Logic) this._logic;
		
		if (e.getActionCommand()==_Interface._btn_cancelar){
			_logic.cancelar();
		}
		
		
		if (e.getActionCommand()==_Interface._btn_buscar_proveedor){
			_logic.BuscarProveedor();
			
		}
		if (e.getActionCommand()==_Interface._btn_analitico){
			_logic.verAnalitico();
		}
		if (e.getActionCommand()==_Interface._btn_reporte){
			_logic.reporte();
		}
		if (e.getActionCommand()==_Interface._btn_pago){
			_logic.hacerPago();
			
		}
		if (e.getActionCommand()==_Interface._btn_grabar){
			_logic.guardar();
			
		}
		if (e.getActionCommand()==_Interface._btn_rotulo){
			_logic.aviso("Operacion aun no implementada");
			
		}
		if (e.getActionCommand()==_Interface._btn_salir){
			_logic.exit();
		}
		if (e.getActionCommand()==_Interface._btn_error){
			_logic.sendInfo();
		}
		if (e.getActionCommand()==_Interface._btn_actualizar_saldo){
			_logic.actualizar_saldo();
			
		}
		
		
		
	}
}
