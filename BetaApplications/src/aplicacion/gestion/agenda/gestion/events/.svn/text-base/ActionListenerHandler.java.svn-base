package aplicacion.gestion.agenda.gestion.events;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import aplicacion.gestion.agenda.gestion.interfaces.*;
import aplicacion.gestion.agenda.gestion.logic.*;
import aplicacion.modelo.events._ActionListenerHandler;


public class ActionListenerHandler extends _ActionListenerHandler{
	public void procesarEvento(ActionEvent e){
		_Logic _logic=(_Logic) this._logic;
		if (e.getSource() instanceof MenuItem) {
			if (e.getActionCommand()==_Interface._btn_recategorizar_aviso){
				System.out.println("ActionEvent "+e.getSource());
				CustomMenuItem mi=(CustomMenuItem) e.getSource();
				Menu m=(Menu)mi.getParent();
				System.out.println("ActionEvent "+m.getParent());
				
				CustomPopup cp=(CustomPopup) m.getParent();
				_logic.recategotizar(cp.getIdaviso(), mi.getIdcategoria());	
			}
			
		}
		
		if (e.getActionCommand()==_Interface._btn_cancelar){
			_logic.cancelar();
		}
		
		if (e.getActionCommand()==_Interface._btn_fecha){
			_logic.BuscarHora();
		}
		if (e.getActionCommand()==_Interface._btn_mostrar){
		
		}
		if (e.getActionCommand()==_Interface._btn_nuevo){
			_logic.nuevo();
		}
		if (e.getActionCommand()==_Interface._btn_hoy){
			_logic.today();
		}
		if (e.getActionCommand()==_Interface._btn_cargar){
			_logic.goCargar();
		}
		if (e.getActionCommand()==_Interface._btn_salir){
			_logic.exit();
		}
		
		if (e.getActionCommand()==_Interface._btn_error){
			_logic.sendInfo();
		}
		
		if (e.getActionCommand()==_Interface._btn_renombrar_categoria){
			_logic.renameCurrentNode();
		}
		if (e.getActionCommand()==_Interface._btn_nueva_categoria){
			_logic.addCategoria();
		}
		if (e.getActionCommand()==_Interface._btn_eliminar_categoria){
			_logic.removeCurrentNode();
		}
		if (e.getActionCommand()==_Interface._btn_propiedades_color){
			_logic.seleccionarColor();
		}
		if (e.getActionCommand()==_Interface._btn_propiedades_guardar){
			_logic.guardarCategoria();
		}
		
	}
}
