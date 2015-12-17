package aplicacion.modelo.events;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

public class _TreeSelectionListener implements TreeSelectionListener {
	public _TreeModelListenerHandler _handler=null;
	
	public void setEventHandler(_TreeModelListenerHandler handler){
		//System.out.println("treemodellistener> seting handler "+handler.getClass());
		this._handler=handler;
	}
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		// TODO Auto-generated method stub
		_handler.procesar(e);
	}

}
