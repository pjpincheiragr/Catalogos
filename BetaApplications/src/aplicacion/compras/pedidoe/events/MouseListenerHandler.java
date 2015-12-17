package aplicacion.compras.pedidoe.events;
import aplicacion.compras.pedidoe.interfaces._Interface;
import aplicacion.compras.pedidoe.logic._Logic;
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
				System.out.println("Show Row "+row);
				if (event.getID()==event.MOUSE_CLICKED){
					if (event.getButton()==MouseEvent.BUTTON1){
						if (row>=0 & event.getClickCount()>=1){
							logic.fillStock(row);	
							logic.focus(table, row, col);
						}		
					}
				}
					
				
			}
			
			if (table.getName()==_Interface._table_critico){
				System.out.println("Show Row "+row);
				if (event.getID()==event.MOUSE_CLICKED){
					if (event.getButton()==MouseEvent.BUTTON1){
						if (row>=0 & event.getClickCount()>=1){
							logic.cargar_equivalencias_critico(table,row);
						}		
					}
				}
					
				
			}
			
			if (table.getName()==_Interface._table_equivalencia_faltantes){
				System.out.println("Show Row "+row);
				if (event.getID()==event.MOUSE_CLICKED){
					if (event.getButton()==MouseEvent.BUTTON1){
						if (row>=0 & event.getClickCount()>=1){
							logic.cargar_equivalencias_faltantes(table,row);
						}		
					}
				}
					
				
			}
			
			if (table.getName()==_Interface._table_remitos){
				System.out.println("Show Row "+row);
				if (event.getID()==event.MOUSE_CLICKED){
					if (event.getButton()==MouseEvent.BUTTON1){
						if (row>=0 & event.getClickCount()>=1){
							logic.mostrar_pedido(row, table);
						}
						if (row>=0 & event.getClickCount()>=2){
							logic.editar_pedido(row);
						}
					}
				}
					
				
			}
		}
	}
}
