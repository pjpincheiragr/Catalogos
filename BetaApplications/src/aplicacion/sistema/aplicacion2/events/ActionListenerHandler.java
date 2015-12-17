package aplicacion.sistema.aplicacion2.events;

import java.awt.event.ActionEvent;

import aplicacion.sistema.aplicacion2.interfaces._Interface;
import aplicacion.sistema.aplicacion2.logic._Logic;
import aplicacion.modelo.events._ActionListenerHandler;

public class ActionListenerHandler extends _ActionListenerHandler {
	
	public void procesarEvento(ActionEvent event){
		_Logic logic=(_Logic) this._logic;
		
		if (event.getActionCommand()==_Interface._btn_cancelar){
			logic.clean();
		}
		if (event.getActionCommand()==_Interface._btn_salir){
			logic.exit();
		}
		if (event.getActionCommand()==_Interface._btn_error){
			logic.sendInfo();
		}
		if (event.getActionCommand()==_Interface._btn_eliminar){
			logic.delete();
		}
		if (event.getActionCommand()==_Interface._btn_guardar){
			logic.guardar();
		}
		if (event.getActionCommand()==_Interface._btn_nueva){
			logic.nuevaAplicacion();
		}
		if (event.getActionCommand()==_Interface._btn_buscar_aplicacion){
			logic.Buscaraplicacion();
		}
		if (event.getActionCommand()==_Interface._btn_buscarImagen){
			logic.buscarIcono();
		}
		if (event.getActionCommand()==_Interface._btn_goFirst){
			logic.goFirst();
		}
		if (event.getActionCommand()==_Interface._btn_goLast){
			logic.goLast();
		}
		if (event.getActionCommand()==_Interface._btn_siguiente){
			logic.siguienteFoto();
		}
		if (event.getActionCommand()==_Interface._btn_anterior){
			logic.anteriorFoto();
		}
		if (event.getActionCommand()==_Interface._btn_eliminarFoto){
			logic.eliminarFoto();
		}
		if (event.getActionCommand()==_Interface._btn_rename){
			logic.recodificar();
		}
		if (event.getActionCommand()==_Interface._btn_probar_aplicaciones){
			logic.correrAplicacion();
		}
	}
	
	


}