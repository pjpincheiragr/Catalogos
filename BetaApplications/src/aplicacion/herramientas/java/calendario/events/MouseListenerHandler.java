package aplicacion.herramientas.java.calendario.events;
import aplicacion.herramientas.java.calendario.interfaces._Interface;
import aplicacion.herramientas.java.calendario.logic._Logic;
import aplicacion.modelo.events.*;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
public class MouseListenerHandler extends _MouseListenerHandler{

	public void procesar(MouseEvent e){
		_Logic logic=(_Logic) this._logic;
		if (e.getSource() instanceof JTable){
			JTable table=(JTable) e.getSource();
			int row	= table.getSelectedRow();
			int col= table.getSelectedColumn();
			
			if (table.getName() == _Interface._table_dias){
				if (e.getClickCount()<=1){
					logic.show_time(row,col);
				}else {
					logic.make_fecha();
				}
			}
		}
	}
}
