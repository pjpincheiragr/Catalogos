package aplicacion.sistema.autorizar.events;


import aplicacion.sistema.autorizar.logic.*;
import aplicacion.sistema.autorizar.interfaces._Interface;

import aplicacion.modelo.events._ItemListenerHandler;

import java.awt.Container;
import java.awt.event.ItemEvent;

import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JTable;


public class ItemListenerHandler extends  _ItemListenerHandler{
	public void procesarEvento(ItemEvent e) {
		_Logic _logic=(_Logic) this._logic;
		
		if (e.getSource() instanceof JCheckBox){
			JCheckBox chk=(JCheckBox) e.getSource();
			if (chk.getParent() instanceof JTable){
				JTable table=(JTable) chk.getParent();
				int row=table.getSelectedRow();
				if (chk.getName() == _Interface._chk_origen){
					
				}
				if (chk.getName() == _Interface._chk_destino){
					
				}
			}
			
			
		}
		
		if (e.getSource() instanceof JComboBox){
			JComboBox lst = (JComboBox) e.getSource();
			if (lst.getName() == _Interface._lst_skins){
				if (e.getID()==ItemEvent.ITEM_STATE_CHANGED){
					
				}
			}	
		}
		
		
	}

}
