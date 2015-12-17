package aplicacion.actualizacion.recodificador.events;
import java.awt.event.ActionEvent;
import aplicacion.actualizacion.recodificador.interfaces.*;
import aplicacion.actualizacion.recodificador.logic.*;
import aplicacion.modelo.events._ActionListenerHandler;

public class ActionListenerHandler extends _ActionListenerHandler{
	public void procesarEvento(ActionEvent e){
		_Logic _logic=(_Logic) this._logic;


		if (e.getActionCommand()==_Interface._btn_salir){
			 _logic.exit();
			}
		if (e.getActionCommand()==_Interface._btn_importar){
			 _logic.importar();
			}
		if (e.getActionCommand()==_Interface._btn_error){
			 _logic.sendInfo();
			}
		if (e.getActionCommand()==_Interface._btn_buscar_desde){
			_logic.BuscarArticuloDesde();
		}

		if (e.getActionCommand()==_Interface._btn_buscar_hasta){
			_logic.BuscarArticuloHasta();
		}
		if (e.getActionCommand()==_Interface._btn_cargar){
			 _logic.Cargar();
			}

		if (e.getActionCommand()==_Interface._btn_recodificar){
			 _logic.recodificar();
			}

		if (e.getActionCommand()==_Interface._btn_cancelar){
			 _logic.cancelar();
			}

		if (e.getActionCommand()==_Interface._btn_guardar){
			 _logic._recodificar();
			}

		if (e.getActionCommand()==_Interface._btn_cancelar_operacion){
			 _logic.cancelar_tarea();
			}

		if (e.getActionCommand()==_Interface._btn_precodificar){
			 _logic.precodificar();
			}
		if (e.getActionCommand()==_Interface._btn_buscar_reemplazar){
			 _logic.BuscarReemplazar();
			}
		if (e.getActionCommand()==_Interface._btn_limpiar){
			 _logic.completar();
			}
		}
	
	
}
