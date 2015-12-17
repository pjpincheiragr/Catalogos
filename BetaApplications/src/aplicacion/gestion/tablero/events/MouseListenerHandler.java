package aplicacion.gestion.tablero.events;
import aplicacion.gestion.tablero.interfaces._Interface;
import aplicacion.gestion.tablero.logic._Logic;
import aplicacion.modelo.events._MouseListenerHandler;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
public class MouseListenerHandler extends _MouseListenerHandler{
	
	
	public void procesarEvento(MouseEvent event){
		_Logic logic=(_Logic) this._logic;
		System.out.println("Mouse Event ");
		if (event.getSource() instanceof JTable){
			
			JTable table=(JTable)  event.getSource();
			int row=table.getSelectedRow();
			int col=table.getSelectedColumn();
				System.out.println("Show Row "+row);
				if (event.getID()==event.MOUSE_CLICKED){
					if (event.getButton()==MouseEvent.BUTTON1){
						if (row>=0 & event.getClickCount()<2){
							logic.show(row, col,table);
						}else{
							logic.ver_opciones(row, col, table);
						}
					}
				}
			
			
			
		}
	}
}
