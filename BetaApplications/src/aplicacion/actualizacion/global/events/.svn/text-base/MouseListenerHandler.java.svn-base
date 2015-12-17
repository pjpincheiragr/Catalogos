package aplicacion.actualizacion.global.events;
import aplicacion.actualizacion.global.interfaces._Interface;
import aplicacion.actualizacion.global.logic._Logic;
import aplicacion.modelo.events._MouseListenerHandler;
import java.awt.event.MouseEvent;
import java.awt.Component;


import javax.swing.JCheckBox;


public class MouseListenerHandler extends _MouseListenerHandler{
	
	
	public void procesarEvento(MouseEvent e){
		_Logic logic=(_Logic) this._logic;
		
		if (e.getSource() instanceof JCheckBox){
			JCheckBox chk=(JCheckBox)e.getSource();
			
			if(chk.getName()==_Interface._chk_seleccionar){
//				if (e.getID() == e.MOUSE_CLICKED){
//					System.out.println("evento mouse");
//					logic.seleccionar(chk);
//				}
				
			}
						
		}
	}
	
}
