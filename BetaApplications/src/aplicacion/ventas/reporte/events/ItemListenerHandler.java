package aplicacion.ventas.reporte.events;


import aplicacion.ventas.reporte.logic.*;
import aplicacion.ventas.reporte.interfaces.*;
import aplicacion.modelo.events._ItemListenerHandler;
import java.awt.event.ItemEvent;
import javax.swing.JComboBox;


public class ItemListenerHandler extends  _ItemListenerHandler{
	public void procesarEvento(ItemEvent e) {
		
		JComboBox lst = (JComboBox) e.getSource();
		
		_Logic _logic=(_Logic) this._logic;
		if (lst.getName() == _Interface._list_reportes){
			
		}
		
	}

}
