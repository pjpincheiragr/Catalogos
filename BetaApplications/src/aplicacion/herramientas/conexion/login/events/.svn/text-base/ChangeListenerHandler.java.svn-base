package aplicacion.herramientas.conexion.login.events;


import aplicacion.herramientas.conexion.login.logic.*;
import aplicacion.herramientas.conexion.login.interfaces.*;

import aplicacion.modelo.events._ChangeListenerHandler;

import java.awt.Container;
import javax.swing.event.ChangeEvent;

import javax.swing.JSlider;
import javax.swing.JTable;


public class ChangeListenerHandler extends  _ChangeListenerHandler{
	public void procesarEvento(ChangeEvent e) {
		
		_Logic _logic=(_Logic) this._logic;
		
		if (e.getSource() instanceof JSlider){
			JSlider js = (JSlider) e.getSource();
			  if (js.getName() == _Interface._bar_trnslucent){
				_logic.setOpacity(js);
			 }
		}
		
		
	}

}
