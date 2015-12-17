package aplicacion.compras.carga.encabezado.events;

import java.awt.event.ItemEvent;
import javax.swing.JComboBox;

import aplicacion.compras.carga.encabezado.interfaces._Interface;
import aplicacion.compras.carga.encabezado.logic._Logic;
import aplicacion.modelo.events._ItemListenerHandler;
public class ItemListenerHandler extends  _ItemListenerHandler{
	public void procesarEvento(ItemEvent e) {
		_Logic logic=(_Logic) this._logic;
		if (e.getSource() instanceof JComboBox){
			JComboBox combo=(JComboBox) e.getSource();
			if (combo.getName()==_Interface._list_imputacion){
				logic.update_imputacion(combo);
			}
			if (combo.getName()==_Interface._list_tc){
				logic.update_tc(combo);
			}
		}
	}

}
