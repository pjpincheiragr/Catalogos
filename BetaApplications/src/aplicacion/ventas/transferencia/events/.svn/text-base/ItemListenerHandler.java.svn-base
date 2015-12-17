package aplicacion.ventas.transferencia.events;


import aplicacion.ventas.transferencia.logic.*;
import aplicacion.ventas.transferencia.interfaces.*;
import aplicacion.modelo.events._ItemListenerHandler;
import java.awt.event.ItemEvent;
import javax.swing.JComboBox;


public class ItemListenerHandler extends  _ItemListenerHandler{
	public void procesarEvento(ItemEvent e) {
		
		
		
		_Logic _logic=(_Logic) this._logic;
		if (e.getSource() instanceof JComboBox){
			JComboBox lst = (JComboBox) e.getSource();
			if (e.getID()==ItemEvent.ITEM_STATE_CHANGED){
				if (lst.getName() == _Interface._lst_posponer_remitente){
					_logic.evaluar_posponer(lst);
				}	
			}
			
		}
		
		
	}

}
