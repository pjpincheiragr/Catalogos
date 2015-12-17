package aplicacion.modelo.events;
import aplicacion.sistema.menu.interfaces._Interface;
import aplicacion.sistema.menu.logic._Logic;
import aplicacion.modelo.events._MouseListenerHandler;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.Component;
import java.awt.TrayIcon;

import javax.swing.JTable;
import javax.swing.JButton;
public class MouseListenerHandler extends _MouseListenerHandler{
	
	public void procesarEvento(MouseWheelEvent e){
		Component c=(Component)e.getSource();
		String message="";
		 int notches = e.getWheelRotation();
	       if (notches < 0) {
	           message = "Mouse wheel moved UP "
	                        + -notches + " notch(es)";
	       } else {
	           message = "Mouse wheel moved DOWN "
	                        + notches + " notch(es)";
	       }
	       System.out.println(message+" Alt is Down? "+e.isAltDown()+" ");

	}
	public void procesarEvento(MouseEvent e){
		_Logic logic=(_Logic) this._logic;
		
		
			
			
			if (e.getID()==e.MOUSE_CLICKED){
				Component c=(Component)e.getSource();
				logic.mouseClick(c, e.getX(),e.getY());
			}
			if (e.getID()==e.MOUSE_ENTERED){
				System.out.println("Mouse Entered");
				Component c=(Component)e.getSource();
				logic.mouseEntered(c, e.getX(),e.getY());
			}
			if (e.getID()==e.MOUSE_EXITED){
				System.out.println("Mouse Exited");
				Component c=(Component)e.getSource();
				logic.mouseExited(c);
			}
			
						
		}
		
		
	
}
