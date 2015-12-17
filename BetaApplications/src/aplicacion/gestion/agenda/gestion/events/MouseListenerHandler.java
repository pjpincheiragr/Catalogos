package aplicacion.gestion.agenda.gestion.events;

import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.JTableHeader;

import aplicacion.gestion.agenda.gestion.interfaces._Interface;
import aplicacion.gestion.agenda.gestion.logic.CustomPopup;
import aplicacion.gestion.agenda.gestion.logic._Logic;
import aplicacion.modelo.events._MouseListenerHandler;

public class MouseListenerHandler extends _MouseListenerHandler {

	public void procesarEvento(MouseEvent event) {
		_Logic logic = (_Logic) this._logic;
		
		
		if (event.getSource() instanceof JScrollPane) {
			if (event.getID() == event.MOUSE_CLICKED) {
				
				JScrollPane scroll=(JScrollPane) event.getSource();
				System.out.println("Mouse Event"+event.getSource());
				if (event.getButton() == MouseEvent.BUTTON1) {
					if (event.getClickCount() >= 2) {
					Point P = event.getLocationOnScreen();
					
						logic.nuevoAvisoItem(P);	
					}
				}
					
			}

		}
		
		if (event.getSource() instanceof JTree) {
			if (event.getID() == event.MOUSE_CLICKED) {
				
				JTree tree = (JTree) event.getSource();
				logic.select(tree.getSelectionPath());
			}

		}

		if (event.getSource() instanceof JTable) {
			
			JTable table = (JTable) event.getSource();
			int row = table.getSelectedRow();
			int col = table.getSelectedColumn();

			if (table.getName() == _Interface._table_dias) {
				if (event.getID() == event.MOUSE_CLICKED) {
					if (event.getButton() == MouseEvent.BUTTON1) {
						if (event.getClickCount() >= 1) {
							logic.goCargar();
						}
					}
				}
			}

			

			if (table.getName() == _Interface._table_semanal) {
					
				if (event.getID() == event.MOUSE_CLICKED) {
					
					if (event.getButton() == MouseEvent.BUTTON1) {
						if (event.getClickCount() >= 2) {
						Point P = event.getLocationOnScreen();
							logic.editarAvisoItem(P);
						}
					}
				}
			}
			
			if (table.getName() == _Interface._table_mensual) {
				
				if (event.getID() == event.MOUSE_CLICKED) {
					
					if (event.getButton() == MouseEvent.BUTTON1) {
						if (event.getClickCount() >= 2) {
						Point P = event.getLocationOnScreen();
							logic.editarAvisoItemMensual(P);
						}
					}
				}
			}
			if (table.getName() == _Interface._table_diario) {
				
				if (event.getID() == event.MOUSE_CLICKED) {
					
					if (event.getButton() == MouseEvent.BUTTON1) {
						if (event.getClickCount() >= 2) {
						Point P = event.getLocationOnScreen();
							logic.nuevoAvisoItemDia(P);
						}
					}
					
				}
			}
			
			if (table.getName() == _Interface._table_day_item) {
				if (event.getID() == event.MOUSE_CLICKED) {
					if (event.getButton() == MouseEvent.BUTTON1) {
						if (event.getClickCount() >= 2) {
							Point P = event.getLocationOnScreen();
							logic.editarAvisoItemDia(P, row);
						}
					}
					if (event.getButton() == MouseEvent.BUTTON3) {
						Point P = event.getLocationOnScreen();
						logic.menuItemDia(P, row);
					}
				}
			}
			if (table.getName() == _Interface._table_semanal_item) {
				if (event.getID() == event.MOUSE_CLICKED) {
					if (event.getButton() == MouseEvent.BUTTON1) {
						if (event.getClickCount() >= 2) {
							Point P = event.getLocationOnScreen();
							logic.editarAvisoItem(P, row);
						}
					}
					if (event.getButton() == MouseEvent.BUTTON3) {
						Point P = event.getLocationOnScreen();
						logic.menuItemWeek(P, row);
					}
				}
			}
			
			
			if (table.getName() == _Interface._table_mensual_item) {
				System.out.println("Doble Click en table mensual item");
				if (event.getID() == event.MOUSE_CLICKED) {
					if (event.getButton() == MouseEvent.BUTTON1) {
						if (event.getClickCount() >= 2) {
							
							Point P = event.getLocationOnScreen();
							logic.editarAvisoItemMonth(P, row);
						}
					}
					if (event.getButton() == MouseEvent.BUTTON3) {
						Point P = event.getLocationOnScreen();
						logic.menuItemMonth(P, row);
					}
				}
			}
			/*
			 * if (table.getName()==_Interface._table_pedidos_nuevos){ if
			 * (event.getID()==event.MOUSE_CLICKED){ if
			 * (event.getButton()==MouseEvent.BUTTON1){ if
			 * (event.getClickCount()<2){ logic.show_pedido_nuevos(row); }else{
			 * if (event.getClickCount()>=2){ logic.editar_pedido_nuevo(row); }
			 * } } } }
			 */
		}
	}
}
