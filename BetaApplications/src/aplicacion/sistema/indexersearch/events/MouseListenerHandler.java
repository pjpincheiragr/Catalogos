package aplicacion.sistema.indexersearch.events;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

import aplicacion.sistema.indexersearch.interfaces.*;
import aplicacion.sistema.indexersearch.logic.*;
import aplicacion.modelo.events._MouseListenerHandler;
public class MouseListenerHandler extends _MouseListenerHandler {

	public void procesarEvento(MouseEvent event){
		_Logic logic=(_Logic) this._logic;
		if (event.getSource() instanceof JTable){
			
			JTable table=(JTable)  event.getSource();
			
			int row=table.getSelectedRow();
			int col=table.getSelectedColumn();
			
			if (table.getName()==_Interface._table){
				
				
				if (event.getClickCount()>=2){
						
						if (event.getID()==event.MOUSE_CLICKED){
							if (event.getButton()==MouseEvent.BUTTON1){
								
								logic.cargar(row);
									
							}
						}
				}
				
			}
						
			
		}
	}
}
