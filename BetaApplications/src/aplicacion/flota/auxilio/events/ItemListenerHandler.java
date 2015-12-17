package aplicacion.flota.auxilio.events;

import aplicacion.flota.auxilio.logic.*;
import aplicacion.flota.auxilio.interfaces._Interface;

import aplicacion.modelo.events._ItemListenerHandler;

import java.awt.Container;
import java.awt.event.ItemEvent;

import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JRadioButton; 

public class ItemListenerHandler extends  _ItemListenerHandler{
	
	public void procesarEvento(ItemEvent e) {
		_Logic logic=(_Logic)this._logic;
		
//		if (e.getSource() instanceof JRadioButton){
//			JRadioButton rad=(JRadioButton) e.getSource();
//			
//			
//			if (e.getID()==ItemEvent.ITEM_STATE_CHANGED){
//				if (rad.getName() == _Interface._rad_idhcp){
//					logic.evaluarIdhcp(rad);
//				}
//				if (rad.getName() == _Interface._rad_manual){
//					logic.evaluarManual(rad);
//				}
//				
//			}
//			
//			
//		}
	}

}
