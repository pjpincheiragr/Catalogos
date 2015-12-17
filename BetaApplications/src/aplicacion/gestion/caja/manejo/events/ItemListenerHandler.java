package aplicacion.gestion.caja.manejo.events;


import aplicacion.gestion.caja.manejo.logic.*;
import aplicacion.gestion.caja.manejo.interfaces.*;
import aplicacion.gestion.caja.manejo.interfaces._Interface;

import aplicacion.modelo.events._ItemListenerHandler;

import java.awt.Container;
import java.awt.event.ItemEvent;

import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JTable;


public class ItemListenerHandler extends  _ItemListenerHandler{
	public void procesarEvento(ItemEvent e) {
		_Logic _logic=(_Logic) this._logic;
		if (e.getSource() instanceof JComboBox){
			JComboBox lst = (JComboBox) e.getSource();
			
			
			if (lst.getName() == _Interface._list_cajas){
				if (e.getID()==ItemEvent.ITEM_STATE_CHANGED){
					_logic.evaluate_caja(lst);	
				}
				
			}
			if (lst.getName() == _Interface._list_moneda){
				if (e.getID()==ItemEvent.ITEM_STATE_CHANGED){
					
				}
			}	
		}
		if (e.getSource() instanceof JCheckBox){
			JCheckBox chk=(JCheckBox) e.getSource();
			if (chk.getName()==_Interface._chk_alfa){
				_logic.calculate_difence();
			}
			if (chk.getName()==_Interface._chk_beta){
				_logic.calculate_difence();
			}
		}
	}

}
