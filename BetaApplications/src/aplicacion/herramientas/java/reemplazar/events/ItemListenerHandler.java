package aplicacion.herramientas.java.reemplazar.events;

import aplicacion.herramientas.java.reemplazar.interfaces.*;
import aplicacion.herramientas.java.reemplazar.logic.*;

import aplicacion.modelo.events._ItemListenerHandler;
import java.awt.event.ItemEvent;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.*;
public class ItemListenerHandler extends  _ItemListenerHandler{
	public void procesarEvento(ItemEvent e) {
		_Logic _logic=(_Logic) this._logic;
		System.out.println(e.getSource());
		if (e.getSource() instanceof JCheckBox){
			JCheckBox chk=(JCheckBox) e.getSource();
			
		}
		if (e.getSource() instanceof JRadioButton){
			JRadioButton chk=(JRadioButton) e.getSource();
			if (chk.getName()==_Interface._chk_adelante){
				_logic.evaluate_adelante(chk);
			}
			if (chk.getName()==_Interface._chk_atras){
				_logic.evaluate_atras(chk);
			}
		}
	}
}
