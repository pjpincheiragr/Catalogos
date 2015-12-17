package aplicacion.modelo.events;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;

public class _TreeModelListener implements TreeModelListener {
	public _TreeModelListenerHandler _handler=null;
	
	public void setEventHandler(_TreeModelListenerHandler handler){
		//System.out.println("treemodellistener> seting handler "+handler.getClass());
		this._handler=handler;
	}
    public void treeNodesChanged(TreeModelEvent e) {
    	_handler.procesar(e);
    }
    public void treeNodesInserted(TreeModelEvent e) {
    	_handler.procesar(e);
    }
    public void treeNodesRemoved(TreeModelEvent e) {
    	_handler.procesar(e);
    }
    public void treeStructureChanged(TreeModelEvent e) {
    	_handler.procesar(e);
    }
}
