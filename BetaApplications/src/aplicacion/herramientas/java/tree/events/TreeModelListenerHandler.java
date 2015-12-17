package aplicacion.herramientas.java.tree.events;

import aplicacion.herramientas.java.tree.interfaces.*;
import aplicacion.herramientas.java.tree.logic._Logic;
import aplicacion.modelo.events._TreeModelListenerHandler;
import javax.swing.event.TreeModelEvent;
import javax.swing.JTree;
public class TreeModelListenerHandler extends _TreeModelListenerHandler {
	
	
	
	public void procesar(TreeModelEvent e){
		_Logic logic=(_Logic) this._logic;	
		JTree tree=(JTree)e.getSource();
		System.out.println("Evento en tree");
		if (tree.getName()==_Interface._tree){
			
		}
	}
}
