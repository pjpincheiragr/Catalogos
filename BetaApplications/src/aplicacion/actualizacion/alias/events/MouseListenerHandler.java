package aplicacion.actualizacion.alias.events;

import aplicacion.actualizacion.alias.interfaces._Interface;
import aplicacion.actualizacion.alias.logic._Logic;
import aplicacion.modelo.events._MouseListenerHandler;
import java.awt.event.MouseEvent;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.JTable;

/**
 * @author Agustin Wisky
 * @company Wismi S.A.
 * @since 10-10-2009
 */
public class MouseListenerHandler extends _MouseListenerHandler {

	public void procesarEvento(MouseEvent event) {
		_Logic logic = (_Logic) this._logic;

		if (event.getSource() instanceof JTableHeader) {
			JTableHeader header = (JTableHeader) event.getSource();

			JTable table = header.getTable();

			if (event.getID() == event.MOUSE_CLICKED) {
				if (event.getButton() == MouseEvent.BUTTON1) {
					if (event.getClickCount() == 1) {
						TableColumnModel colModel = table.getColumnModel();
						int col = colModel.getColumnIndexAtX(event.getX());
						logic.seleccionar(col);
					}

				}
			}
		}

		if (event.getSource() instanceof JTable) {

			JTable table = (JTable) event.getSource();
			int row = table.getSelectedRow();
			int col = table.getSelectedColumn();
			if (table.getName() == _Interface._table) {

				if (event.getID() == event.MOUSE_CLICKED) {
					if (event.getButton() == MouseEvent.BUTTON1) {
						if (event.getClickCount()>=1){
							logic.focus(table, row, col);
						}
					}
					if (event.getButton() == MouseEvent.BUTTON3) {

						logic.deseleccionar();
					}

				}

			}
		}
	}
}
