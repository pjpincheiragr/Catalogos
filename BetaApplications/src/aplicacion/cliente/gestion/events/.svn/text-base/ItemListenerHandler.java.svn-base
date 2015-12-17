package aplicacion.cliente.gestion.events;

import aplicacion.cliente.gestion.interfaces.*;
import aplicacion.cliente.gestion.logic.*;

import aplicacion.modelo.events._ItemListenerHandler;
import java.awt.event.ItemEvent;
import javax.swing.JComboBox;

import javax.swing.*;
public class ItemListenerHandler extends  _ItemListenerHandler{
	public void procesarEvento(ItemEvent e) {
		
		
		_Logic _logic=(_Logic) this._logic;
		
		if (e.getSource() instanceof JCheckBox){
			JCheckBox chk=(JCheckBox) e.getSource();
			
		}
		if (e.getSource() instanceof JComboBox){
			
			JComboBox cb=(JComboBox) e.getSource();
			JTable table=null;
			int row=-1;
			if (cb.getParent() instanceof JTable){
				table=(JTable) cb.getParent();
				row=table.getSelectedRow();
				if (cb.getName()==_Interface._cb_categoria){
					_logic.evaluate_selection(cb, row, table);	
				}
			}
			
			
			
			
		}
	}

}
