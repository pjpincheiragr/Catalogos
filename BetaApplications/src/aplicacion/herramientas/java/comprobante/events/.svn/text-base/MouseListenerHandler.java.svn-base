package aplicacion.herramientas.java.comprobante.events;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

import aplicacion.herramientas.java.comprobante.interfaces.*;
import aplicacion.herramientas.java.comprobante.logic.*;
import aplicacion.modelo.events._MouseListenerHandler;
public class MouseListenerHandler extends _MouseListenerHandler {

	public void procesarEvento(MouseEvent event){
		_Logic logic=(_Logic) this._logic;
		if (event.getSource() instanceof JTable){
			
			JTable table=(JTable)  event.getSource();
			int row=table.getSelectedRow();
			int col=table.getSelectedColumn();
			if (table.getName()==_Interface._table){
				if (event.getClickCount()==2){
					logic.Close(table, row);
				}
				
			}
			
			
		}
	}
}
