package aplicacion.sistema.menu.events;

import java.awt.event.ActionEvent;
import javax.swing.JPopupMenu;
import javax.swing.JMenu;
import javax.swing.JMenuItem;



import aplicacion.modelo.events._ActionListenerHandler;
import aplicacion.sistema.menu.interfaces._Interface;
import aplicacion.sistema.menu.logic._Logic;

public class ActionListenerHandler extends _ActionListenerHandler{
	
	public void procesarEvento(ActionEvent e){
		_Logic logic=(_Logic)this._logic;
		if (e.getActionCommand()==_Interface._btn_abrir){
			logic.addFoto();	
		}
		if (e.getActionCommand()==_Interface._btn_kill){
			logic.kill();
			
		}
		if (e.getActionCommand()==_Interface._btn_console){
			logic.console();
		}
		if (e.getActionCommand()==_Interface._btn_minimizar){
			logic.minimizar_tray();
		}
		if (e.getActionCommand()==_Interface._btn_play){
			logic.setPause(false);
		}
		if (e.getActionCommand()==_Interface._btn_pause){
			logic.setPause(true);
		}
		if (e.getActionCommand()==_Interface._btn_anterior){
			logic._anterior();
		}
		if (e.getActionCommand()==_Interface._btn_siguiente){
			logic._siguiente();
		}
		if (e.getActionCommand()==_Interface._btn_salir){
			
			logic.exit();
		}
if (e.getActionCommand()==_Interface._btn_eliminar){
			logic.eliminar_foto();
		}
if (e.getActionCommand()==_Interface._btn_guardar){
	
	//logic.guardar_imagenes();
}
		if (e.getActionCommand()==_Interface._btn_error){
			logic.envioASistemas();
		}
		if (e.getActionCommand()==_Interface._btn_recargar){
			
			logic.loadUserAreas();
		}
		if (e.getActionCommand()==_Interface._tray_icon){
			
			logic.show();
		}
		
		if (e.getSource() instanceof JMenuItem){
			JMenuItem item = (JMenuItem)(e.getSource());
			
			logic.goCargar(item.getName(),item.getText());	
		}
		
	}

}
