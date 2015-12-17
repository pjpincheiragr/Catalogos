package aplicacion.actualizacion.global.events;

import aplicacion.actualizacion.global.interfaces.*;
import aplicacion.actualizacion.global.logic.*;

import aplicacion.modelo.events._ItemListenerHandler;
import java.awt.event.ItemEvent;
import javax.swing.JComboBox;

import javax.swing.*;
/**
 * @author Agustin Wisky
 * @company Wismi S.A.
 * @since 10-10-2009
 */
public class ItemListenerHandler extends  _ItemListenerHandler{
	public void procesarEvento(ItemEvent e) {
		
		
		_Logic _logic=(_Logic) this._logic;
		
		if (e.getSource() instanceof JCheckBox){
			JCheckBox chk=(JCheckBox) e.getSource();
			if(chk.getName()==_Interface._chk_seleccionar){
				if (e.getID()==ItemEvent.ITEM_STATE_CHANGED){
					_logic.seleccionar(chk);
				}
				
			}
		}
		
		if (e.getSource() instanceof JComboBox){
			JComboBox cb=(JComboBox) e.getSource();
			if (e.getID()==ItemEvent.ITEM_STATE_CHANGED){
				if(cb.getName()==_Interface._lst_modo){
					_logic.filtro(cb);
				}	
			}
			
		}
	}

}
