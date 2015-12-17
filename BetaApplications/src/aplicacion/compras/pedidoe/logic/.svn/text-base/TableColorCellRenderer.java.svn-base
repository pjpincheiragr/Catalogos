package aplicacion.compras.pedidoe.logic;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import aplicacion.compras.pedidoe.interfaces._Interface;

public class TableColorCellRenderer extends DefaultTableCellRenderer {
    private  _Logic logic=null;
    
    public void setLogic(_Logic logic){
    	this.logic=logic;
    }
    
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int rowIndex, int vColIndex) {
		Component c = super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, rowIndex, vColIndex);
		Color azul = new Color(100, 100, 255);
		
		
		c.setForeground(Color.black);
		if (table.getName()==_Interface._table_faltantes){
			Color color=logic.getShowCost(table, rowIndex,isSelected);
			Color default_background = color;
			c.setBackground(default_background);	
			
		}else{
			Color color=logic.getColor(rowIndex, vColIndex,table);
			Color default_background = color;
			if (isSelected){
				c.setBackground(azul);
			}else {
				c.setBackground(default_background);	
			}	
		}
		
		return c;
	}
	
}