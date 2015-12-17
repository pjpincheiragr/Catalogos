package aplicacion.cliente.cobranza.events;
import aplicacion.cliente.cobranza.interfaces._Interface;
import aplicacion.cliente.cobranza.logic._Logic;
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
	        	
				
	        }
		}
		
		if (event.getSource() instanceof JTable){
			System.out.println("MouseEvent on JTABLE Button>"+event.getButton()+" clicks?"+event.getClickCount());
			JTable table=(JTable)  event.getSource();
			int row=table.getSelectedRow();
			int col=table.getSelectedColumn();
			
			if (table.getName()==_Interface._table_medios){
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
			
				if (event.getID()==event.MOUSE_CLICKED)
				{
					
					
						if (event.getButton()==MouseEvent.BUTTON1){
							if (event.getClickCount()>=2){
						        if (table.getName()==_Interface._table_cpte){
						        	if (col==2|col==1|col==7|col==8|col==9){
						        		logic.verComprobante(table,row,col,event.getPoint());	
						        	}
						        }
						        if (table.getName()==_Interface._table_opg){
						        	if (col==3|col==2){
						        		logic.verComprobante(table,row,col,event.getPoint());	
						        	}
						        }
						        
							}
							
						}				
						
					
					
				}
					
				
							
				
			
		}
	}
}
