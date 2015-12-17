package aplicacion.herramientas.java.table;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableColorCellRenderer extends DefaultTableCellRenderer {
    
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int rowIndex, int vColIndex) {

		Component c = super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, rowIndex, vColIndex);
		
		Color default_foreground = new Color(255, 255, 255);
		Color verde = new Color(100, 255, 100);
		Color default_background = new Color(255, 255, 255);
		c.setForeground(Color.black);
		c.setBackground(default_foreground);
		 
			
		return c;
	}
	
}