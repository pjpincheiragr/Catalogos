package aplicacion.herramientas.java.tree.events;

import java.awt.event.ItemEvent;

import javax.swing.JCheckBox;
import javax.swing.JTree;

import aplicacion.herramientas.java.tree.logic._Logic;
import aplicacion.modelo.events._ItemListenerHandler;

public class ItemListenerHandler extends _ItemListenerHandler {
	public void procesar(ItemEvent e){
		_Logic logic=(_Logic) this._logic;
		if (e.getSource() instanceof JCheckBox	){
			
		}
	}
}
