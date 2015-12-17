package aplicacion.herramientas.java.image.events;
import aplicacion.modelo.events._MouseListenerHandler;
import java.awt.event.MouseEvent;
import java.awt.Component;
import aplicacion.herramientas.java.image.logic.*;

public class MouseListenerHandler extends _MouseListenerHandler{
	public void procesarEvento(MouseEvent e){
		_Logic logic=(_Logic) this._logic;
		
		
		if (e.getID()==e.MOUSE_CLICKED){
			Component c=(Component)e.getSource();
			logic.mouseClick(c, e.getX(),e.getY(),e.getLocationOnScreen());
		}
		if (e.getID()==e.MOUSE_EXITED){
			Component c=(Component)e.getSource();
			
			logic.mouseExited(c);
		}
		if (e.getID()==e.MOUSE_MOVED){
			
			Component c=(Component)e.getSource();
			logic.mouseMove(c,e.getX(),e.getY());
		}
		
	}
	
}
