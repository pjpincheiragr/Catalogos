package aplicacion.inventario.control.events;


import aplicacion.inventario.control.logic.*;
import aplicacion.inventario.control.interfaces.*;

import aplicacion.modelo.events._ItemListenerHandler;

import java.awt.Container;
import java.awt.event.ItemEvent;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;


public class ItemListenerHandler extends  _ItemListenerHandler{
	public void procesarEvento(ItemEvent e) {
		
		
		_Logic _logic=(_Logic) this._logic;
		
		if (e.getSource() instanceof JCheckBox){
			JCheckBox chk = (JCheckBox) e.getSource();
			if (chk.getName()==_Interface._chk_free){
				_logic.evaluate_free(chk);
			}
		}
		if (e.getSource() instanceof JComboBox){
			JComboBox lst = (JComboBox) e.getSource();
			//if (lst.getName()==_Interface._lst_marcar){
				if (e.getID()==ItemEvent.ITEM_STATE_CHANGED){
					
				}
			//}	
		}
		
		
		
	}

}
