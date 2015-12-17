package aplicacion.compras.carga.items.events;
import aplicacion.compras.carga.items.interfaces._Interface;
import aplicacion.compras.carga.items.logic._Logic;
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
				if (event.getID()==event.MOUSE_CLICKED){
					System.out.println("Show Row "+row);
					if (event.getButton()==MouseEvent.BUTTON1){
						if (event.getClickCount()>=1){
							if (row>=0){
								logic.showRow(row);
								
								table.changeSelection(row, col,false,false);
								table.editCellAt(row, col);
								table.transferFocus();			
								
							}		
						}
							
					}	
				}
			}
			if (table.getName()==_Interface._table_impuestos){
				//System.out.println("Event Source >"+row+":"+col+" >"+event.getSource());
				if (event.getID()==event.MOUSE_CLICKED){
					if (event.getButton()==MouseEvent.BUTTON1){
						if (event.getClickCount()>=1){
							table.changeSelection(row, col,false,false);
							table.editCellAt(row, col);
							table.transferFocus();			
						}
						}
				}
				
			}
			if (table.getName()==_Interface._table_pedidos_especiales){
				if (event.getID()==event.MOUSE_CLICKED){
					System.out.println("Show Row "+row);
					if (event.getButton()==MouseEvent.BUTTON1){
						if (event.getClickCount()>=1){
							if (row>=0){
								logic.mostrar_pedidos_especiales_items(row);
							}		
						}
							
					}	
				}
			}
			if (table.getName()==_Interface._table_pedidos_proyeccion){
				if (event.getID()==event.MOUSE_CLICKED){
					System.out.println("Show Row "+row);
					if (event.getButton()==MouseEvent.BUTTON1){
						if (event.getClickCount()>=1){
							if (row>=0){
								logic.mostrar_pedidos_proyectados_item(row);
							}		
						}
							
					}	
				}
			}
			if (table.getName()==_Interface._table_remitos){
				if (event.getID()==event.MOUSE_CLICKED){
					System.out.println("Show Row "+row);
					if (event.getButton()==MouseEvent.BUTTON1){
						if (event.getClickCount()>=1){
							if (row>=0){
								logic.mostrar_remitos_items(row);
							}		
						}
							
					}	
				}
			}
		}
	}
}
