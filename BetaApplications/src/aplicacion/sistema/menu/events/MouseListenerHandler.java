package aplicacion.sistema.menu.events;
import aplicacion.sistema.menu.interfaces._Interface;
import aplicacion.sistema.menu.logic._Logic;
import aplicacion.modelo.events._MouseListenerHandler;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.TrayIcon;

import javax.swing.JTable;
import javax.swing.JButton;
public class MouseListenerHandler extends _MouseListenerHandler{
	
	
	public void procesarEvento(MouseEvent e){
		_Logic logic=(_Logic) this._logic;
		/*
		if (e.getSource() instanceof JButton){
			
			if (e.getID()==e.MOUSE_MOVED){
				
				Component c=(Component)e.getSource();
				logic.mouseMove(c,e.getX(),e.getY());
			}
			if (e.getID()==e.MOUSE_CLICKED){
				Component c=(Component)e.getSource();
				logic.mouseClick(c, e.getX(),e.getY());
			}
			if (e.getID()==e.MOUSE_ENTERED){
				System.out.println("Mouse Entered");
				Component c=(Component)e.getSource();
				//logic.mouseEntered(c, e.getX(),e.getY());
			}
			if (e.getID()==e.MOUSE_EXITED){
				System.out.println("Mouse Exited");
				Component c=(Component)e.getSource();
				//logic.mouseExited(c);
			}
			
						
		}
		*/
		if (e.getSource() instanceof TrayIcon){
			
			
				logic.show();
				/*
				if (e.getID()==e.MOUSE_CLICKED){
					if (e.getButton()==MouseEvent.BUTTON1){
						if (e.getClickCount()>=1){
								
						}		
					}
				
				}*/
					
				
				
			
		}
	}
	
}
