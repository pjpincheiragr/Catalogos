package aplicacion.ventas.gestion2.events;

import aplicacion.ventas.gestion2.logic._Logic;
import aplicacion.ventas.gestion2.interfaces.*;
import aplicacion.modelo.events._TreeModelListenerHandler;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.JTree;
public class TreeModelListenerHandler extends _TreeModelListenerHandler {
	
	
	
	public void procesar(TreeModelEvent e){
		_Logic logic=(_Logic) this._logic;
		if (e.getSource() instanceof JTree){
			JTree tree=(JTree)e.getSource();
			System.out.println("Evento en tree");
			if (tree.getName()==_Interface._tree){
				
			}	
		}
		
	}
	public void procesar(TreeSelectionEvent e) {
		_Logic logic=(_Logic) this._logic;	
		JTree tree=(JTree)e.getSource();
		System.out.println("Evento en tree");
		if (tree.getName()==_Interface._tree){
			logic.select(e.getPath());
		}
		
	}
}
