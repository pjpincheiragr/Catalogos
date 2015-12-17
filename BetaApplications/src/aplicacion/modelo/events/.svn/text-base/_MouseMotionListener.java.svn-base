package aplicacion.modelo.events;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
public class _MouseMotionListener implements MouseMotionListener{
	private _MouseListenerHandler _handler=null;
	
	public void mouseMoved(MouseEvent e){
		this._handler.procesar(e);
	}
	public void mouseDragged(MouseEvent e){
		this._handler.procesar(e);
	}
	public void setEventHandler(_MouseListenerHandler handler){
		this._handler=handler;
	}

}
