package aplicacion.compras.carga.control.events;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

import aplicacion.compras.carga.control.interfaces._Interface;
import aplicacion.compras.carga.control.logic._Logic;
import aplicacion.modelo.events._MouseListenerHandler;
public class MouseListenerHandler extends _MouseListenerHandler {

	public void procesarEvento(MouseEvent event){
		_Logic logic=(_Logic) this._logic;
		if (event.getSource() instanceof JTable){
			
			JTable table=(JTable)  event.getSource();
			int row=table.getSelectedRow();
			int col=table.getSelectedColumn();
			if (table.getName()==_Interface._table_libro){
				if (event.getID()==event.MOUSE_CLICKED){
					if (event.getButton()==MouseEvent.BUTTON1){
						if (row>=0 & event.getClickCount()>=1){
							logic.goCargarCpte(row);			
						}
					}
				}
				
				
			}
			if (table.getName()==_Interface._table_dias){
				if (event.getID()==event.MOUSE_CLICKED){
					if (event.getButton()==MouseEvent.BUTTON1){
						if (event.getClickCount()>=1){
							logic.completeDiaCarga();
						}
										
						
					}
				}
				
			}
			
		}
	}
}
