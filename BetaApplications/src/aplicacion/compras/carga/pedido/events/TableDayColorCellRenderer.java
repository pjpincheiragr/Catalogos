package aplicacion.compras.carga.pedido.events;

import java.awt.Color;
import java.awt.Component;
import aplicacion.compras.carga.control.logic.*;
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
		Color verde = new Color(100, 255, 100);
		Color azul = new Color(100, 100, 255);
		
		
		double[] perc=logic.getControlCalendar(rowIndex, vColIndex);
		System.out.println("percentage color? "+perc);
		Double color=perc[0]*255;
		
		Color default_background = new Color(0, color.intValue(), 0);
		
		if (perc[0]<=0){
			if (perc[1]<=0){
				default_background =Color.white;	
			}
			
		}else {
			if (!logic.checkPostition(rowIndex, vColIndex)){
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