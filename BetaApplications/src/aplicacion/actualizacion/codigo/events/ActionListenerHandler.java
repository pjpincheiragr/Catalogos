package aplicacion.actualizacion.codigo.events;
import java.awt.event.ActionEvent;



import aplicacion.actualizacion.codigo.interfaces.*;
import aplicacion.actualizacion.codigo.logic.*;
import aplicacion.modelo.events._ActionListenerHandler;
/**
 * @author Agustin Wisky
 * @company Wismi S.A.
 * @since 10-10-2009
 */
public class ActionListenerHandler extends _ActionListenerHandler{
	public void procesarEvento(ActionEvent e){
		_Logic _logic=(_Logic) this._logic;

		if (e.getActionCommand()==_Interface._btn_salir){
			 _logic.exit();
			}
		
		if (e.getActionCommand()==_Interface._btn_error){
			_logic.sendInfo();
		}
		if (e.getActionCommand()==_Interface._btn_buscar_desde){
			_logic.BuscarCodigoDesde();
		}

		if (e.getActionCommand()==_Interface._btn_buscar_hasta){
			_logic.BuscarCodigoHasta();
		}
		if (e.getActionCommand()==_Interface._btn_cargar){
			 _logic._taskworkCargar();
			}

		if (e.getActionCommand()==_Interface._btn_cancelar){
			 _logic.cancelar();
			}

		if (e.getActionCommand()==_Interface._btn_exportar){
			 _logic.exportar();
			}

		if (e.getActionCommand()==_Interface._btn_importar){
			 _logic.importar();
			}
		
		if (e.getActionCommand()==_Interface._btn_guardar){
			 _logic.goGuardar();
			}


		if (e.getActionCommand()==_Interface._btn_buscar_proveedor){
			 _logic.BuscarProveedor();
			}

		if (e.getActionCommand()==_Interface._btn_buscar_linea){
			 _logic.BuscarLinea();
			}

		if (e.getActionCommand()==_Interface._btn_borrar){
			 _logic.goBorrar();
			}




		if (e.getActionCommand()==_Interface._btn_completar){
			 _logic.completar();
			}
		
	
		if (e.getActionCommand()==_Interface._btn_cancelar_tarea){
		 _logic.cancelar_tarea();
			}
		}
	
}
