package aplicacion.herramientas.java.importar.events;

import aplicacion.herramientas.java.importar.interfaces.*;
import aplicacion.herramientas.java.importar.logic.*;

import aplicacion.modelo.events._ItemListenerHandler;
import java.awt.event.ItemEvent;
import javax.swing.JComboBox;

import javax.swing.*;
public class ItemListenerHandler extends  _ItemListenerHandler{
	public void procesarEvento(ItemEvent e) {
		_Logic _logic=(_Logic) this._logic;
		if (e.getSource() instanceof JCheckBox){
			JCheckBox chk=(JCheckBox) e.getSource();
			if (chk.getName()==_Interface._chk_seleccionar){
				_logic.seleccionar(chk.isSelected());
			}
		}
	}
}
