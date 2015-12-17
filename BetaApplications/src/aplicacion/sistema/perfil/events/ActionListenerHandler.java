package aplicacion.sistema.perfil.events;

import java.awt.event.ActionEvent;

import aplicacion.sistema.perfil.interfaces._Interface;
import aplicacion.sistema.perfil.logic._Logic;
import aplicacion.modelo.events._ActionListenerHandler;

public class ActionListenerHandler extends _ActionListenerHandler {
	
	public void procesarEvento(ActionEvent event){
		_Logic logic=(_Logic) this._logic;


		if (event.getActionCommand()==_Interface._btn_salir){
			logic.exit();
		}
		if (event.getActionCommand()==_Interface._btn_error){
			logic.sendInfo();
		}
		if (event.getActionCommand()==_Interface._btn_guardar){
			logic.goTareaPesada();
		}
		if (event.getActionCommand()==_Interface._btn_cambiarContrasenia){
			logic.cambiarPass();
			
		}
		if (event.getActionCommand()==_Interface._btn_cancelar){
			logic.clean();
		}
		if(event.getActionCommand()==_Interface._btn_buscarImagen){
			logic.buscar_imagen();
		}
		if(event.getActionCommand()==_Interface._btn_eliminarFoto){
			logic.eliminarFoto();
		}
		if(event.getActionCommand()==_Interface._btn_goFirst){
			logic.goFirst();
		}
		if(event.getActionCommand()==_Interface._btn_anterior){
			logic.anteriorFoto();
		}
		if(event.getActionCommand()==_Interface._btn_siguente){
			logic.siguienteFoto();
		}
		if(event.getActionCommand()==_Interface._btn_goLast){
			logic.goLast();
		}
		if(event.getActionCommand()==_Interface._btn_zoomIn){
			logic.zoomIn();
		}
		if(event.getActionCommand()==_Interface._btn_zoomOut){
			logic.zoomOut();
		}
		if(event.getActionCommand()==_Interface._btn_resetFoto){
			logic.resetFoto();
		}
		if(event.getActionCommand()==_Interface._btn_view){
			logic.view();
		}
	}
	
	


}