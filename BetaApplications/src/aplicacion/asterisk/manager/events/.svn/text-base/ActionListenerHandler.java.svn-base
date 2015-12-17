package aplicacion.asterisk.manager.events;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;

import javax.swing.JMenuItem;
import javax.swing.JButton;
import aplicacion.asterisk.manager.interfaces.*;
import aplicacion.asterisk.manager.logic.*;
import aplicacion.modelo.events._ActionListenerHandler;

public class ActionListenerHandler extends _ActionListenerHandler{
	public void procesarEvento(ActionEvent e){
		_Logic _logic=(_Logic) this._logic;
		System.out.println(e.getActionCommand());
		
		if (e.getActionCommand()==_Interface._btn_minimizar){
			_logic.hide();
		}
		if (e.getActionCommand()==_Interface._btn_reconectar){
			_logic.reconnectar();
		}
		if (e.getActionCommand()==_Interface._btn_salir){
			_logic.exit();
		}
		if (e.getActionCommand()==_Interface._btn_ayuda){
			_logic.ayuda();
		}
		if (e.getActionCommand()==_Interface._btn_ask_guardar){
			_logic.guardar();
		}
		if (e.getActionCommand()==_Interface._btn_ask_pdc){
			_logic.nuevo_pdc();
		}
		if (e.getActionCommand()==_Interface._btn_ask_visor_pdc){
			_logic.nuevo_visor();
		}
		if (e.getActionCommand()==_Interface._btn_maximizar){
			_logic.maximizar();
		}
		if (e.getActionCommand().contains("lanzador:")){
			_logic.ejecutar(e.getActionCommand());
		}
		if (e.getActionCommand()==_Interface._btn_ask_maestro_cliente){
			_logic.verMaestro();
		}
		if (e.getActionCommand()==_Interface._btn_nuevo_aviso){
			_logic.nuevoAviso();
		}
		if (e.getActionCommand()==_Interface._btn_agenda){
			_logic.Agenda();
		}
		if (e.getActionCommand()==_Interface._btn_dynamic){
			JButton button=(JButton) e.getSource(); 	
			_logic.close(button);
		}
		if (e.getActionCommand()==_Interface._btn_dynamic_edit){
			JButton button=(JButton) e.getSource(); 	
			_logic.edit(button);
		}
		if (e.getSource() instanceof JMenuItem){
			JMenuItem item = (JMenuItem)(e.getSource());
			
			_logic.goCargar(item.getName(),item.getText());	
		}
	}
}
