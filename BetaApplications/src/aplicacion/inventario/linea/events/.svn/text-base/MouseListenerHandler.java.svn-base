package aplicacion.inventario.linea.events;
import aplicacion.inventario.linea.interfaces._Interface;
import aplicacion.inventario.linea.logic._Logic;
import aplicacion.modelo.events._MouseListenerHandler;
import java.awt.event.MouseEvent;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.JTable;
public class MouseListenerHandler extends _MouseListenerHandler{
	
	
	public void procesarEvento(MouseEvent event){
		_Logic logic=(_Logic) this._logic;
		if (event.getSource() instanceof JTable){
			JTable table=(JTable)event.getSource();
			int row=table.getSelectedRow();
	        //  if (event.getID()==event.MOUSE_CLICKED){
			if (table.getName()==_Interface._table){
				if (event.getButton()==MouseEvent.BUTTON1){
					
					if (event.getClickCount()>=1){
						logic.seleccion(row);		
					}
				}	
			}
			
	        	
		}
		
		
		
	}
}
