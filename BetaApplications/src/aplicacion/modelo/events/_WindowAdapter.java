package aplicacion.modelo.events;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;




public class _WindowAdapter extends WindowAdapter {
	public _WindowAdapterHandler _handler=null;
	public void setEventHandler(_WindowAdapterHandler handler){
		this._handler=handler;
	}

	public void windowClosing(WindowEvent we){
		this._handler.procesar(we);
	}
}
