package aplicacion.actualizacion.alias.events;

import aplicacion.actualizacion.alias.interfaces.*;
import aplicacion.actualizacion.alias.logic.*;

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
				
				_logic.seleccionar(chk.isSelected());
			}
		}
	}

}
