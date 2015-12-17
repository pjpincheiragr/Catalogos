package aplicacion.sistema.perfil.events;


import aplicacion.sistema.perfil.logic.*;
import aplicacion.sistema.perfil.interfaces._Interface;

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
		
		if (e.getSource() instanceof MyCheckBox){
			MyCheckBox chk=(MyCheckBox)e.getSource();
			/*if(chk.getName() == _Interface._chk_tree){
				_logic.evaluate_checkbox(chk);
			}*/
			
			if (e.getID()==ItemEvent.ITEM_STATE_CHANGED){
				//_logic.evaluate_checkbox(chk);
				
			}
		}
		
		if (e.getSource() instanceof JComboBox){
			JComboBox lst = (JComboBox) e.getSource();
			if (lst.getName() == ""){
				if (e.getID()==ItemEvent.ITEM_STATE_CHANGED){
					
				}
			}	
		}
		
		
	}

}
