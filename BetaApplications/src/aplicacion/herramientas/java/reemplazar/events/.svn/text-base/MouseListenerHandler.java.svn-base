package aplicacion.herramientas.java.reemplazar.events;

import aplicacion.herramientas.java.reemplazar.interfaces._Interface;
import aplicacion.herramientas.java.reemplazar.logic._Logic;
import aplicacion.modelo.events._MouseListenerHandler;
import java.awt.event.MouseEvent;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.JTable;

public class MouseListenerHandler extends _MouseListenerHandler {

	public void procesarEvento(MouseEvent event) {
		_Logic logic = (_Logic) this._logic;

		if (event.getSource() instanceof JTable) {

			JTable table = (JTable) event.getSource();
			int row = table.getSelectedRow();
			int col = table.getSelectedColumn();
			
		}
	}
}
