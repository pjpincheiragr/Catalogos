package aplicacion.cliente.archivo.events;
import java.awt.event.ActionEvent;

import aplicacion.cliente.archivo.interfaces.*;
import aplicacion.cliente.archivo.logic.*;
import aplicacion.modelo.events._ActionListenerHandler;

public class ActionListenerHandler extends _ActionListenerHandler{
	public void procesarEvento(ActionEvent e){
		_Logic _logic=(_Logic) this._logic;
		if (e.getActionCommand()==_Interface._btn_cancelar){
			_logic.cancelar();
		}
		
		if (e.getActionCommand()==_Interface._btn_salir){
			_logic.exit();
		}
		if (e.getActionCommand()==_Interface._btn_error){
			_logic.sendInfo();
		}
		if (e.getActionCommand()==_Interface._btn_analitico){
			_logic.verAnalitico();
		}
		
		
		if (e.getActionCommand()==_Interface._btn_editar){
			_logic.editar();
		}
		
		if (e.getActionCommand()==_Interface._btn_reporte){
			_logic.reporte();
		}
		
		if (e.getActionCommand()==_Interface._btn_buscar_cliente){
			_logic.BuscarCliente();
			
		}
		
		if (e.getActionCommand()==_Interface._btn_cobranza){
			_logic.hacerCobranza();
			
		}
		
		if (e.getActionCommand()==_Interface._btn_guardar){
			_logic.guardar();
			
		}
		
		if (e.getActionCommand()==_Interface._btn_actualizar_saldo){
			_logic.actualizar_saldo();
			
		}
		
	}
}
