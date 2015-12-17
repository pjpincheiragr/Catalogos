package aplicacion.ventas.gestion2.events;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.JTree;

import aplicacion.ventas.gestion2.interfaces._Interface;
import aplicacion.ventas.gestion2.logic._Logic;
import aplicacion.modelo.events._MouseListenerHandler;
public class MouseListenerHandler extends _MouseListenerHandler {

	public void procesarEvento(MouseEvent event){
		_Logic logic=(_Logic) this._logic;
		if (event.getSource() instanceof JTable){
			
			JTable table=(JTable)  event.getSource();
			int row=table.getSelectedRow();
			int col=table.getSelectedColumn();
			
			if (table.getName()==_Interface._table_dias){
				if (event.getID()==event.MOUSE_CLICKED){
					if (event.getButton()==MouseEvent.BUTTON1){
						if (event.getClickCount()>=1){
							logic.show_with_filter(true);
							logic.completeDiaCarga();
						}
					}
				}
			}
			
			if (event.getSource() instanceof JTree){
				if (event.getID()==event.MOUSE_CLICKED){
				System.out.println("Mouse Event JTree");
				JTree tree=(JTree) event.getSource();
				logic.select(tree.getSelectionPath());
				}
			}
			
			if (table.getName()==_Interface._table_pedidos_nuevos){
				if (event.getID()==event.MOUSE_CLICKED){
					if (event.getButton()==MouseEvent.BUTTON1){
						if (event.getClickCount()<2){
							logic.show_pedido_nuevos(row);
						}else{
							if (event.getClickCount()>=2){
								logic.editar_pedido_nuevo(row);
							}	
						}
					}
				}
			}
		}
	}
}
