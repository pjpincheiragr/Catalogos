package aplicacion.compras.carga.control.events;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableColorCellRenderer extends DefaultTableCellRenderer {
    
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int rowIndex, int vColIndex) {

		Component c = super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, rowIndex, vColIndex);
		
		Color default_foreground = new Color(255, 255, 255);
		Color verde = new Color(90, 255, 90);
		Color azul = new Color(100, 100, 255);
		Color bordo = new Color(143, 0, 31);
		Color default_background = new Color(255, 255, 255);
		if (isSelected){
			c.setForeground(bordo);
			 c.setBackground(Color.white);;
			 c.setFont(new Font("Dialog", Font.BOLD, 12));
		}else {
			c.setForeground(Color.black);
			c.setBackground(default_foreground);	
		}
		
		String date="";
		try{
		date=table.getValueAt(rowIndex, table.getColumnCount()-1).toString();
		 if (date.compareTo("")!=0){
			 
			 if (isSelected){
				 c.setFont(new Font("Dialog", Font.BOLD, 12));
				 c.setForeground(bordo);
				 c.setBackground(verde);;
			 }else {
				 c.setForeground(Color.black);
				 c.setBackground(verde);	 
			 }
		 }
		 
		}catch(Exception e){
			
		}
		
		return c;
	}
	
}