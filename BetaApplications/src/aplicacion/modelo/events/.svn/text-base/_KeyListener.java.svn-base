package aplicacion.modelo.events;
import java.awt.event.KeyAdapter; 
import java.awt.event.KeyEvent;

public class _KeyListener extends KeyAdapter{
	public _KeyListenerHandler _handler=null;
	
	public void setEventHandler(_KeyListenerHandler handler){
		this._handler=handler;
	}
	
	public void keyPressed(KeyEvent event){ 
		 this._handler.procesar(event);
	}

}
