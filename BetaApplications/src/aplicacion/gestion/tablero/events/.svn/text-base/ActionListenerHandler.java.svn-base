package aplicacion.gestion.tablero.events;
import java.awt.event.ActionEvent;

import aplicacion.gestion.tablero.interfaces.*;
import aplicacion.gestion.tablero.logic.*;
import aplicacion.modelo.events._ActionListenerHandler;

public class ActionListenerHandler extends _ActionListenerHandler{
	public void procesarEvento(ActionEvent e){
		System.out.println(e.getSource());
		_Logic _logic=(_Logic) this._logic;
		if (e.getActionCommand()==_Interface._btn_salir){
			_logic.exit();
		}
		if (e.getActionCommand()==_Interface._btn_calendario){
			_logic.BuscarCalendario();
		}
		if (e.getActionCommand()==_Interface._btn_error){
			_logic.sendInfo();
		}
		if (e.getActionCommand()==_Interface._btn_cargar){
			_logic.goCargar();
		}
		if (e.getActionCommand()==_Interface._btn_guardar){
			_logic.guardar();
		}
		if (e.getActionCommand()==_Interface._btn_elegir_foreground){
			//System.out.println("Elegir Foreground");
			_logic.seleccionarForeground();
		}
		if (e.getActionCommand()==_Interface._btn_elegir_background){
			_logic.seleccionarBackground();
		}
		if (e.getActionCommand()==_Interface._btn_fecha_desde){
			_logic.buscar_fecha_desde();
		}
		if (e.getActionCommand()==_Interface._btn_fecha_hasta){
			_logic.buscar_fecha_hasta();
		}
		if (e.getActionCommand()==_Interface._btn_copiar){
			_logic.Copiar();
		}
		if (e.getActionCommand()==_Interface._btn_pegar){
			_logic.Pegar();
		}
		if (e.getActionCommand()==_Interface._btn_eliminar){
			_logic.Eliminar();
		}
		if (e.getActionCommand()==_Interface._btn_eliminar_celda){
			_logic.EliminarCelda();
		}
		if (e.getActionCommand()==_Interface._btn_nuevo){
			_logic.Nuevo();
		}
		if (e.getActionCommand()==_Interface._btn_exportar){
			_logic.Exportar();
		}
		if (e.getActionCommand()==_Interface._btn_importar){
			_logic.Importar();
		}
		}
}
