package aplicacion.compras.carga.control.events;
import aplicacion.compras.carga.control.logic._Logic;
import aplicacion.modelo.events._ItemListenerHandler;
import aplicacion.compras.carga.control.interfaces._Interface;

import java.awt.event.ItemEvent;

import javax.swing.JComboBox;
import javax.swing.JCheckBox;
public class ItemListenerHandler extends _ItemListenerHandler {

	public void procesar(ItemEvent e){
		_Logic logic=(_Logic) this._logic;
		if (e.getSource() instanceof JCheckBox	){
			JCheckBox chk=(JCheckBox) e.getSource();
			if (chk.getName()==_Interface._chk_utiliza_calendario){
				logic.utiliza_calendario(chk.isSelected());
			}
		}
			if (e.getSource() instanceof JComboBox){
				JComboBox cb=(JComboBox) e.getSource();
				
				if (cb.getName()==_Interface._list_anio){
					logic.update_anio(cb);
				}
				
				if (cb.getName()==_Interface._list_mes){
					logic.update_mes(cb);
				}
				
				
				
		}
		
	}
}
