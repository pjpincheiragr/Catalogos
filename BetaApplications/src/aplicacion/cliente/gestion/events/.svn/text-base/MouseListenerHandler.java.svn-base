package aplicacion.cliente.gestion.events;
import aplicacion.cliente.gestion.interfaces._Interface;
import aplicacion.cliente.gestion.logic._Logic;
import aplicacion.modelo.events._MouseListenerHandler;
import java.awt.event.MouseEvent;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.JTable;
public class MouseListenerHandler extends _MouseListenerHandler{
	
	
	public void procesarEvento(MouseEvent event){
		_Logic logic=(_Logic) this._logic;
		
		if (event.getSource() instanceof JTableHeader){
			JTableHeader header=(JTableHeader) event.getSource();
			
			JTable table=header.getTable();
			
	        if (event.getID()==event.MOUSE_CLICKED){
				if (event.getButton()==MouseEvent.BUTTON1){
					if (event.getClickCount()==1){
						TableColumnModel colModel = table.getColumnModel();
				        int col = colModel.getColumnIndexAtX(event.getX());
				        
					}
					
				}
	        }
		}
		
		if (event.getSource() instanceof JTable){
			
			JTable table=(JTable)  event.getSource();
			int row=table.getSelectedRow();
			int col=table.getSelectedColumn();
			if (table.getName()==_Interface._table){
				
				if (event.getID()==event.MOUSE_CLICKED)
				{
					
					if (event.getButton()==MouseEvent.BUTTON1){
						 if (table.getName()==_Interface._table){
					        	if (col==2|col==1){
					        		if (event.getClickCount()>=2){
					        			logic.verMaestro(row);	
					        		}
					        			
					        	}
					        }
						
					}
					
				}
					
				
							
				
			}
		}
	}
}
