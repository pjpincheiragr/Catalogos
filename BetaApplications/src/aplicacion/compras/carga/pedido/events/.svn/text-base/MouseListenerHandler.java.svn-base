package aplicacion.compras.carga.pedido.events;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

import aplicacion.compras.carga.pedido.interfaces._Interface;
import aplicacion.compras.carga.pedido.logic._Logic;
import aplicacion.modelo.events._MouseListenerHandler;
public class MouseListenerHandler extends _MouseListenerHandler {

	public void procesarEvento(MouseEvent event){
		_Logic logic=(_Logic) this._logic;
		if (event.getSource() instanceof JTable){
			
			JTable table=(JTable)  event.getSource();
			int row=table.getSelectedRow();
			int col=table.getSelectedColumn();
			if (table.getName()==_Interface._table_lineas){
				if (event.getID()==event.MOUSE_CLICKED){
					if (event.getButton()==MouseEvent.BUTTON1){
						if (event.getClickCount() >= 2){
							 if (col>0 ){
								
								 logic.mostrar_linea_seleccionada(row);						 
							 }
						 }	
					}	
				}
			}
			if (table.getName()==_Interface._table_items){
				if (event.getID()==event.MOUSE_CLICKED){
					if (event.getButton()==MouseEvent.BUTTON1){
						if (event.getClickCount() >= 1){
							 	
								 logic.mostrar_item(row,table);
								 
							 
						 }	
					}	
				}
			}
		}
	}
}
