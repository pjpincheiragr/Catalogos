package aplicacion.sistema.indexersearch.events;


import aplicacion.sistema.indexersearch.logic.*;
import aplicacion.sistema.indexersearch.interfaces._Interface;

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
				
			}
		}
		
		if (e.getSource() instanceof JComboBox){
			JComboBox lst = (JComboBox) e.getSource();
				
		}
		
		
	}

}
