package aplicacion.asterisk.manager.events;
import aplicacion.asterisk.manager.interfaces._Interface;
import aplicacion.asterisk.manager.logic._Logic;
import aplicacion.modelo.events._MouseListenerHandler;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.TrayIcon;

import javax.swing.JTable;
import javax.swing.JButton;
public class MouseListenerHandler extends _MouseListenerHandler{
	
	
	public void procesarEvento(MouseEvent e){
		_Logic logic=(_Logic) this._logic;
		if (e.getSource() instanceof JTable){
			
			JTable table=(JTable)  e.getSource();
			int row=table.getSelectedRow();
			int col=table.getSelectedColumn();
			if (table.getName()==_Interface._table_cuentas){
				if (e.getID()==e.MOUSE_CLICKED){
					if (e.getButton()==MouseEvent.BUTTON1){
						if (e.getClickCount()>=1){
							if (col==0){
								logic.focus(row);	
							}
							
						}
					}
				}
			}
			if (table.getName()==_Interface._table_history){
				if (e.getID()==e.MOUSE_CLICKED){
					if (e.getButton()==MouseEvent.BUTTON1){
						if (e.getClickCount()>=2){
							logic.select(row);
						}
					}
				}
			}
			if (table.getName()==_Interface._table_messages){
				if (e.getID()==e.MOUSE_CLICKED){
					if (e.getButton()==MouseEvent.BUTTON1){
						if (e.getClickCount()>=2){
							logic.editarAviso(table, row);
						}
					}
				}
			}
		}
		/*
		if (e.getSource() instanceof JButton){
			
			if (e.getID()==e.MOUSE_MOVED){
				
				Component c=(Component)e.getSource();
				logic.mouseMove(c,e.getX(),e.getY());
			}
			if (e.getID()==e.MOUSE_CLICKED){
				Component c=(Component)e.getSource();
				logic.mouseClick(c, e.getX(),e.getY());
			}
			if (e.getID()==e.MOUSE_ENTERED){
				System.out.println("Mouse Entered");
				Component c=(Component)e.getSource();
				//logic.mouseEntered(c, e.getX(),e.getY());
			}
			if (e.getID()==e.MOUSE_EXITED){
				System.out.println("Mouse Exited");
				Component c=(Component)e.getSource();
				//logic.mouseExited(c);
			}
			
						
		}
		*/
		if (e.getSource() instanceof TrayIcon){
			
			
				
				if (e.getID()==e.MOUSE_CLICKED){
					if (e.getButton()==MouseEvent.BUTTON1){
						
							logic.shift();
						
					}
				
				}
					
				
				
			
		}
	}
	
}
