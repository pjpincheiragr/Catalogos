package aplicacion.catalogo.repuestos.events;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.JTree;
import aplicacion.catalogo.repuestos.interfaces._Interface;
import aplicacion.catalogo.repuestos.logic._Logic;
import aplicacion.modelo.events._MouseListenerHandler;
public class MouseListenerHandler extends _MouseListenerHandler {

	public void procesarEvento(MouseEvent event){
		_Logic logic=(_Logic) this._logic;
		System.out.println("mouse event");
		if (event.getSource() instanceof JTable){
			
			JTable table=(JTable)  event.getSource();
			int row=table.getSelectedRow();
			int col=table.getSelectedColumn();
			if (table.getName()==_Interface._table_tecnica){
				if (event.getClickCount()==1){
					
				}
				
			}
			
			
		}
		if (event.getSource() instanceof JTree){
			JTree tree=(JTree) event.getSource();
			logic.select(tree.getSelectionPath());
		}
	}
}
