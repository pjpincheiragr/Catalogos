package aplicacion.ventas.facturador.events;


import aplicacion.ventas.facturador.logic.*;
import aplicacion.ventas.facturador.interfaces.*;
import aplicacion.ventas.facturador.interfaces._Interface;

import aplicacion.modelo.events._ItemListenerHandler;

import java.awt.Container;
import java.awt.event.ItemEvent;

import javax.swing.JComboBox;
import javax.swing.JTable;


public class ItemListenerHandler extends  _ItemListenerHandler{
	public void procesarEvento(ItemEvent e) {
		
		
		
		_Logic _logic=(_Logic) this._logic;
		if (e.getSource() instanceof JComboBox){
			JComboBox lst = (JComboBox) e.getSource();
			if (lst.getName() == _Interface._list_comprobante){
				if (e.getID()==ItemEvent.ITEM_STATE_CHANGED){
					//_logic.evaluate_comprobante(lst);	
				}
			}
		}
		/*
		
		if (lst.getName() == _Interface._list_moneda){
			if (e.getID()==ItemEvent.ITEM_STATE_CHANGED){
				_logic.goCargar(_logic.getCuenta(lst));
			}
		}*/
	}

}
