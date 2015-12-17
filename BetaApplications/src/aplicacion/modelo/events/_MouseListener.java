package aplicacion.modelo.events;
import java.awt.Point;
import java.awt.event.MouseListener; 
import java.awt.event.MouseEvent; 
import aplicacion.modelo.events._MouseListenerHandler;
public class _MouseListener  implements MouseListener {
private _MouseListenerHandler _handler=null;

public void setEventHandler(_MouseListenerHandler handler){
	this._handler=handler;
}



public void mouseClicked(MouseEvent e) {
	_handler.procesar(e);
}

public void mouseEntered(MouseEvent e) {
	_handler.procesar(e);
}

public void mouseExited(MouseEvent e) {
	_handler.procesar(e);
}

public void mousePressed(MouseEvent e) {
	_handler.procesar(e);
}

public void mouseReleased(MouseEvent e) {
	_handler.procesar(e);
}

}
