package aplicacion.compras.gestion.events;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

import aplicacion.compras.gestion.interfaces._Interface;
import aplicacion.compras.gestion.logic._Logic;
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
			if (table.getName()==_Interface._table_pedidos){
				if (event.getID()==event.MOUSE_CLICKED){
					if (event.getButton()==MouseEvent.BUTTON1){
						if (event.getClickCount()<2){
							logic.show_pedido(row);
						}else{
							if (event.getClickCount()>=2){
								logic.editar_pedido(row);
							}	
						}
					}
				}
			}
		}
	}
}
