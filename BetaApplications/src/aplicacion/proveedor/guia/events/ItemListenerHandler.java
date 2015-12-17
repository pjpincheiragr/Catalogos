package aplicacion.proveedor.guia.events;


import aplicacion.proveedor.guia.logic.*;
import aplicacion.proveedor.guia.interfaces._Interface;

import aplicacion.modelo.events._ItemListenerHandler;

import java.awt.Container;
import java.awt.event.ItemEvent;

import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JTree;

public class ItemListenerHandler extends  _ItemListenerHandler{
	public void procesarEvento(ItemEvent e) {
		_Logic _logic=(_Logic) this._logic;
		
		if (e.getSource() instanceof MyCheckBox){
			MyCheckBox chk=(MyCheckBox) e.getSource();
			if (chk.getParent() instanceof JTable){
				JTable table=(JTable) chk.getParent();
				int row=table.getSelectedRow();
				
			}
			
			if (e.getID()==ItemEvent.ITEM_STATE_CHANGED){
				_logic.evaluate_checkbox(chk);
				
			}
		
			
		}
		
		if (e.getSource() instanceof JComboBox){
			JComboBox lst = (JComboBox) e.getSource();
			
				if (e.getID()==ItemEvent.ITEM_STATE_CHANGED){
					
				}
				
		}
		
		
	}

}
