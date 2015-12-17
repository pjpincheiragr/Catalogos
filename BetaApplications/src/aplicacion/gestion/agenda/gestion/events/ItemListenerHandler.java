package aplicacion.gestion.agenda.gestion.events;


import aplicacion.gestion.agenda.gestion.interfaces.*;
import aplicacion.gestion.agenda.gestion.logic.*;
import aplicacion.gestion.agenda.gestion.interfaces._Interface;
import aplicacion.modelo.events._ItemListenerHandler;



import java.awt.Point;
import java.awt.event.ItemEvent;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;


public class ItemListenerHandler extends  _ItemListenerHandler{
	public void procesarEvento(ItemEvent e) {
		_Logic logic=(_Logic) this._logic;
		JTable table=null;
		int row=-1;
		if (e.getSource() instanceof JCheckBox){
			JCheckBox chk=(JCheckBox) e.getSource();
			if (chk.getParent() instanceof JTable){
				table=(JTable) chk.getParent();
				row=table.getSelectedRow();
			}
			
			if (e.getID()==ItemEvent.ITEM_STATE_CHANGED){
				System.out.println("Aviso "+chk.getName());
				if (chk.getName()==_Interface._chk_aviso_mensual){
					Point P = chk.getLocationOnScreen();
					logic.updateAvisoItemLeidoMensual(P, row, chk.isSelected());
				}
				if (chk.getName()==_Interface._chk_aviso_semanal){
					Point P = chk.getLocationOnScreen();
					logic.updateAvisoItemLeido(P, row, chk.isSelected());
				}
				if (chk.getName()==_Interface._chk_aviso_day){
					Point P = chk.getLocationOnScreen();
					logic.updateAvisoItemLeidoDay(P, row, chk.isSelected());
				}
				
			}
		}
		
		if (e.getSource() instanceof MyCheckBox){
			MyCheckBox chk=(MyCheckBox) e.getSource();
			if (chk.getParent() instanceof JTable){
				table=(JTable) chk.getParent();
				row=table.getSelectedRow();
			}
			
			if (e.getID()==ItemEvent.ITEM_STATE_CHANGED){
				
					
					logic.evaluate_checkbox(chk);	
				
				
			}
		
			
		}

		
		if (e.getSource() instanceof JComboBox){
			JComboBox cb=(JComboBox) e.getSource();
			if (e.getID()==ItemEvent.ITEM_STATE_CHANGED){
				if (cb.getName()==_Interface._lst_posponer){
					logic.evaluar_posponer(cb);
				}
				if (cb.getName()==_Interface._lst_anio){
					logic.update_anio(cb);
				}
				if (cb.getName()==_Interface._lst_mes){
					logic.update_mes(cb);
				}
			}
		}
		}
		
		
		
		
	

}
