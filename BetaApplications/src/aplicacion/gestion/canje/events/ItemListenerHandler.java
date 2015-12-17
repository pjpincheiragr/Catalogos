package aplicacion.gestion.canje.events;


import aplicacion.gestion.canje.logic.*;
import aplicacion.gestion.canje.interfaces.*;

import aplicacion.modelo.events._ItemListenerHandler;

import java.awt.Container;
import java.awt.event.ItemEvent;

import javax.swing.JComboBox;
import javax.swing.JTable;


public class ItemListenerHandler extends  _ItemListenerHandler{
	public void procesarEvento(ItemEvent e) {
		
		JComboBox lst = (JComboBox) e.getSource();
		
		_Logic _logic=(_Logic) this._logic;
		if (lst.getName() == _Interface._list_cajas){
			_logic.evaluate_caja(lst);
		}
		
		
	}

}
