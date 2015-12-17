package aplicacion.gestion.tablero.events;


import aplicacion.gestion.tablero.logic.*;
import aplicacion.gestion.tablero.interfaces.*;
import aplicacion.gestion.tablero.interfaces._Interface;

import aplicacion.modelo.events._ItemListenerHandler;

import java.awt.Container;
import java.awt.event.ItemEvent;

import javax.swing.JComboBox;
import javax.swing.JTable;


public class ItemListenerHandler extends  _ItemListenerHandler{
	public void procesarEvento(ItemEvent e) {
		
		JComboBox lst = (JComboBox) e.getSource();
		
		_Logic _logic=(_Logic) this._logic;
		if (lst.getName() == _Interface._list_anio){
			if (e.getID()==ItemEvent.ITEM_STATE_CHANGED){
					_logic.show_time2();
			}
			
		}
		if (lst.getName() == _Interface._list_mes){
			if (e.getID()==ItemEvent.ITEM_STATE_CHANGED){
				_logic.show_time2();
			}
		}
	}

}
