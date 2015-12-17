package aplicacion.inventario.reporteMovimiento.events;


import aplicacion.inventario.reporteLinea.logic.*;
import aplicacion.inventario.reporteLinea.interfaces.*;
import aplicacion.modelo.events._ItemListenerHandler;
import java.awt.event.ItemEvent;
import javax.swing.JComboBox;


public class ItemListenerHandler extends  _ItemListenerHandler{
	public void procesarEvento(ItemEvent e) {
		_Logic _logic=(_Logic) this._logic;
		if (e.getSource() instanceof JComboBox){
			JComboBox lst = (JComboBox) e.getSource();
			
			
			if (lst.getName() == _Interface._list_cajas){
			
			}	
		}
		
		
	}

}
