package aplicacion.modelo.events;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class _ItemListener implements ItemListener{

public _ItemListenerHandler _handler=null;
	
	public void setEventHandler(_ItemListenerHandler handler){
		this._handler=handler;
	}
	public void itemStateChanged(ItemEvent e) {
		this._handler.procesar(e);
	}
}
