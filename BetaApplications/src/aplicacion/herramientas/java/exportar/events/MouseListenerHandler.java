package aplicacion.herramientas.java.exportar.events;
import aplicacion.herramientas.java.exportar.interfaces._Interface;
import aplicacion.herramientas.java.exportar.logic._Logic;
import aplicacion.modelo.events._MouseListenerHandler;
import java.awt.event.MouseEvent;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.JTable;
public class MouseListenerHandler extends _MouseListenerHandler{
	
	
	public void procesarEvento(MouseEvent event){
		_Logic logic=(_Logic) this._logic;
		
		
		
		if (event.getSource() instanceof JTable){
			
			JTable table=(JTable)  event.getSource();
			int row=table.getSelectedRow();
			int col=table.getSelectedColumn();
			if (table.getName()==_Interface._table_archivo){
				
				if (event.getID()==event.MOUSE_CLICKED)
				{
					
					if (event.getButton()==MouseEvent.BUTTON3){
						
								
					}
					
				}
					
				
							
				
			}
		}
	}
}
