package aplicacion.sistema.acercade.events;
import aplicacion.sistema.acercade.interfaces._Interface;
import aplicacion.sistema.acercade.logic._Logic;
import aplicacion.modelo.events._MouseListenerHandler;
import java.awt.event.MouseEvent;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.JTable;
public class MouseListenerHandler extends _MouseListenerHandler{
	
	
	public void procesarEvento(MouseEvent event){
		_Logic logic=(_Logic) this._logic;
		
		
			
			
		if (event.getID()==event.MOUSE_DRAGGED){
			logic.createScreenImage();
		}
			
	        if (event.getID()==event.MOUSE_PRESSED){
				if (event.getButton()==MouseEvent.BUTTON1){
					
					logic.frozen();
				}
	        }
	        if (event.getID()==event.MOUSE_MOVED){
	        	logic.disturb(event.getX(), event.getY());
	        }
		
		
		
	}
}
