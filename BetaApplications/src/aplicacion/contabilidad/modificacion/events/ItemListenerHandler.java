package aplicacion.contabilidad.modificacion.events;
import aplicacion.contabilidad.modificacion.logic._Logic;
import aplicacion.modelo.events._ItemListenerHandler;
import aplicacion.contabilidad.modificacion.interfaces._Interface;

import java.awt.event.ItemEvent;

import javax.swing.JComboBox;
import javax.swing.JCheckBox;
public class ItemListenerHandler extends _ItemListenerHandler {

	public void procesar(ItemEvent e){
		_Logic logic=(_Logic) this._logic;
		if (e.getSource() instanceof JCheckBox	){
			JCheckBox chk=(JCheckBox) e.getSource();
			
		}
			if (e.getSource() instanceof JComboBox){
				JComboBox cb=(JComboBox) e.getSource();
				
				
				
				
		}
		
	}
}
