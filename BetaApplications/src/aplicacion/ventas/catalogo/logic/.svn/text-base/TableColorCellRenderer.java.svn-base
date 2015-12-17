package aplicacion.ventas.catalogo.logic;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableColorCellRenderer extends DefaultTableCellRenderer {
    private  _Logic logic=null;
    
    public void setLogic(_Logic logic){
    	this.logic=logic;
    }
    
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int rowIndex, int vColIndex) {
		Component c = super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, rowIndex, vColIndex);
		Color azul = new Color(140, 160, 255);
		Color color=logic.getColor(rowIndex, vColIndex,table);
		Color default_background = color;
		c.setForeground(Color.black);
		if (isSelected){
			c.setBackground(azul);
		}else {
			c.setBackground(default_background);	
		}
		return c;
	}
	
}