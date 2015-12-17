package aplicacion.ventas.facturador.events;
import aplicacion.ventas.facturador.interfaces._Interface;
import aplicacion.ventas.facturador.logic._Logic;
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
			if (table.getName()==_Interface._table_items){
				//System.out.println("Show Row "+row);
				if (event.getID()==event.MOUSE_CLICKED){
					if (event.getButton()==MouseEvent.BUTTON1){
						if (row>=0 & event.getClickCount()>=1){
							logic.fillStock(row);	
							logic.focus(table, row, col);
						}		
					}
				}
					
				
			}
		}
	}
}
