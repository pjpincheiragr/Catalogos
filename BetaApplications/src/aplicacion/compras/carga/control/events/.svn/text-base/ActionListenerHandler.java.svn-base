package aplicacion.compras.carga.control.events;
import aplicacion.compras.carga.control.interfaces._Interface;
import aplicacion.compras.carga.control.logic.*;
import aplicacion.modelo.events._ActionListenerHandler;
import java.awt.event.ActionEvent;
public class ActionListenerHandler extends _ActionListenerHandler{
 
	public void procesarEvento(ActionEvent event){
		_Logic logic=(_Logic) this._logic;
		if (event.getActionCommand()==_Interface._btn_cargar){
			logic.cargarLibro();
		}
		
		if (event.getActionCommand()==_Interface._btn_control_ok){
			logic.setControl();
		}
		if (event.getActionCommand()==_Interface._btn_unsetall){
			logic.unsetControlAll();
		}
		if (event.getActionCommand()==_Interface._btn_cancelar){
			logic.cancelar();
		}
		if (event.getActionCommand()==_Interface._btn_salir){
			logic.exit();
		}
		if (event.getActionCommand()==_Interface._btn_error){
			logic.sendInfo();
		}
		if (event.getActionCommand()==_Interface._btn_imprimir){
			logic.imprimir();
		}
		if (event.getActionCommand()==_Interface._btn_contraste_mas){
			logic.contraste_mas();
		}
		if (event.getActionCommand()==_Interface._btn_contraste_menos){
			logic.contraste_menos();
		}
		if (event.getActionCommand()==_Interface._btn_control_error){
			logic.unsetControl();
		}
		if (event.getActionCommand()==_Interface._btn_editar_cpte){
			logic.editarCpte();
		}
		if (event.getActionCommand()==_Interface._btn_fecha_carga){
			logic.load_carga();
		}
		if (event.getActionCommand()==_Interface._btn_fecha_desde){
			logic.load_desde();
		}
		if (event.getActionCommand()==_Interface._btn_fecha_hasta){
			logic.load_hasta();
		}

		if (event.getActionCommand()==_Interface._btn_next){
			logic.next();
		}

		if (event.getActionCommand()==_Interface._btn_back){
			logic.back();
		}
		if (event.getActionCommand()==_Interface._btn_anterior){
			logic._anterior();
		}
		if (event.getActionCommand()==_Interface._btn_siguiente){
			logic._siguiente();
		}
		

	}
}
