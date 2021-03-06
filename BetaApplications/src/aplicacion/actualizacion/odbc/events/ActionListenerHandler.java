package aplicacion.actualizacion.odbc.events;
import aplicacion.actualizacion.odbc.logic._Logic;
import aplicacion.modelo.events.*;
import aplicacion.actualizacion.odbc.interfaces.*;
import java.awt.event.ActionEvent;
public class ActionListenerHandler extends _ActionListenerHandler{
	
	
	public void procesar(ActionEvent e){
		_Logic logic=(_Logic) this._logic;
		if (e.getActionCommand()==_Interface._btn_guardar){
			logic.guardar();
		}
		if (e.getActionCommand()==_Interface._btn_nuevo){
			logic.nuevo();
		}
		if (e.getActionCommand()==_Interface._btn_exportar){
			logic._exportar();
		}
		if (e.getActionCommand()==_Interface._btn_reload){
			logic.goExport();
		}
		if (e.getActionCommand()==_Interface._btn_ejecutar){
			logic.Update();
		}
		if (e.getActionCommand()==_Interface._btn_cancelar_tarea){
			logic.doCancel();
		}
		if (e.getActionCommand()==_Interface._btn_cancelar){
			logic.cancelar();
		}
		if (e.getActionCommand()==_Interface._btn_buscar_politica){
			logic.BuscarPolitica();
		}
		if (e.getActionCommand()==_Interface._btn_buscar_proveedor){
			logic.BuscarProveedor();
		}
		if (e.getActionCommand()==_Interface._btn_salir){
			logic.exit();
		}
		if (e.getActionCommand()==_Interface._btn_error){
			logic.sendInfo();
		}
		if (e.getActionCommand()==_Interface._btn_test){
			logic.Info();
		}
	}
}
