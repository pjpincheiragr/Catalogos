package aplicacion.gestion.agenda.escritor.events;


import aplicacion.gestion.agenda.escritor.interfaces.*;
import aplicacion.gestion.agenda.escritor.logic.*;
import aplicacion.gestion.agenda.escritor.interfaces._Interface;
import aplicacion.modelo.events._ItemListenerHandler;



import java.awt.event.ItemEvent;
import javax.swing.JComboBox;
import javax.swing.JTable;


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
				if (chk.getName()==_Interface._chk_user){
					_logic.evaluate_checkbox_Usuarios(chk);	
				}
				if (chk.getName()==_Interface._chk_categorias){
					_logic.evaluate_checkbox(chk);	
				}
				
			}
		
			
		}

		
		if (e.getSource() instanceof JComboBox){
			JComboBox cb=(JComboBox) e.getSource();
			if (e.getID()==ItemEvent.ITEM_STATE_CHANGED){
				if (cb.getName()==_Interface._lst_posponer){
					_logic.evaluar_posponer(cb);
				}	
			}
		}
		}
		
		
		
		
	

}
