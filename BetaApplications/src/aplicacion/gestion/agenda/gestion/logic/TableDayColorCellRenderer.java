package aplicacion.gestion.agenda.gestion.logic;

import java.awt.Color;
import java.awt.Component;
import aplicacion.gestion.agenda.gestion.logic.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableDayColorCellRenderer extends DefaultTableCellRenderer {
    private  _Logic logic=null;
    
    public void setLogic(_Logic logic){
    	this.logic=logic;
    }
    
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int rowIndex, int vColIndex) {

		Component c = super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, rowIndex, vColIndex);
		
		Color default_foreground = new Color(255, 255, 255);
		Color verde = new Color(255, 100, 100);
		Color azul = new Color(100, 100, 255);
		Color gray = Color.LIGHT_GRAY;
		Color default_background = Color.white;
		
		if (logic.isOtherMonth(rowIndex, vColIndex)){
			default_background=gray;
		}else{
			double cargas=logic.getControlCalendar(rowIndex, vColIndex);
				
			default_background = new Color( 210, 10,10);
			
			if (cargas<=0){
					default_background =Color.white;	
				
				
			}	
		}
		
		c.setForeground(Color.black);
		if (isSelected){
			
			c.setBackground(azul);;
		}else {
				c.setBackground(default_background);	
			
				
		}
		
		
		
		return c;
	}
	
}