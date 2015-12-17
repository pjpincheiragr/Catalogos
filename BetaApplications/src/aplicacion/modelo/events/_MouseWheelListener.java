package aplicacion.modelo.events;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
public class _MouseWheelListener implements MouseWheelListener{
	private _MouseListenerHandler _handler=null;
	
	
	public void mouseWheelMoved(MouseWheelEvent e) {
		this._handler.procesar(e);
	}
	public void setEventHandler(_MouseListenerHandler handler){
		this._handler=handler;
	}

}
