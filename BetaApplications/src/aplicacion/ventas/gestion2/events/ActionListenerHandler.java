package aplicacion.ventas.gestion2.events;
import aplicacion.ventas.gestion2.interfaces._Interface;
import aplicacion.ventas.gestion2.logic.*;
import aplicacion.modelo.events._ActionListenerHandler;
import java.awt.event.ActionEvent;
public class ActionListenerHandler extends _ActionListenerHandler{
 
	public void procesarEvento(ActionEvent event){
		_Logic logic=(_Logic) this._logic;
		if (event.getActionCommand()==_Interface._btn_salir){
			logic.exit();
		}
		if (event.getActionCommand()==_Interface._btn_cargar){
			logic.goCargar();
		}
		if (event.getActionCommand()==_Interface._btn_nueva_categoria){
			logic.addCategoria();
		}
		if (event.getActionCommand()==_Interface._btn_renombrar_categoria){
			logic.renameCurrentNode();
		}
		if (event.getActionCommand()==_Interface._btn_eliminar_categoria){
			logic.removeCurrentNode();
		}
		if (event.getActionCommand()==_Interface._btn_cancelar){
			logic.cancelar();
		}
		if (event.getActionCommand()==_Interface._btn_transferir){
			logic.Transferencia();
		}
		if (event.getActionCommand()==_Interface._btn_categoria){
			logic.Categorizar();
		}
		
		if (event.getActionCommand()==_Interface._btn_confirmar_categoria){
			logic.drop();
		}
		if (event.getActionCommand()==_Interface._btn_salir_categoria){
			logic.dispose_categoria();
		}
		
		if (event.getActionCommand()==_Interface._btn_editar){
			logic.editar_pedido();
		}
		if (event.getActionCommand()==_Interface._btn_nuevo){
			logic.nuevo_pedido();
		}
		if (event.getActionCommand()==_Interface._btn_consulta){
			logic.consultar();
		}
		if (event.getActionCommand()==_Interface._btn_error){
			logic.sendInfo();
		}
		
		if (event.getActionCommand()==_Interface._btn_propiedades_color){
			logic.seleccionarColor();
		}
		if (event.getActionCommand()==_Interface._btn_propiedades_guardar){
			logic.guardarCategoria();
		}
		

	}
}
