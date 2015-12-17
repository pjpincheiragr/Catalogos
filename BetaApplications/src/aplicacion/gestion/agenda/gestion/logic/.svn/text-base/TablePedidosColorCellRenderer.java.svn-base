package aplicacion.gestion.agenda.gestion.logic;

import java.awt.Color;
import java.awt.Component;

import aplicacion.gestion.agenda.gestion.interfaces._Interface;
import aplicacion.gestion.agenda.gestion.logic.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TablePedidosColorCellRenderer extends DefaultTableCellRenderer {
    private  _Logic logic=null;
    
    public void setLogic(_Logic logic){
    	this.logic=logic;
    }
    
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int rowIndex, int vColIndex) {

		Component c = super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, rowIndex, vColIndex);
		
		
		Color default_foreground = new Color(0, 0, 0);
		Color default_background = new Color(255, 255, 255);
		Color verde = new Color(255, 100, 100);
		Color azul = new Color(100, 100, 255);
		c.setBackground(default_background);
		c.setForeground(default_foreground);
		String idaviso="";
		String creado="";
		
		try {
			
			idaviso = table.getValueAt(rowIndex, 1).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (idaviso.compareTo("")!=0){
			String color=logic.getcolorfromMemory(idaviso);
			if (color.compareTo("")!=0){
				Color _color=logic.getColor(color);
				if (isSelected){
					_color=logic.getSelectedColor(color);
				}
				c.setBackground(_color);	
			}
		}
		/*
		try {
			agenda = table.getValueAt(rowIndex, 3).toString();
			creado = table.getValueAt(rowIndex, 8).toString();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double cargas=logic.getControlDias(agenda, creado);
		
		int color=new Double(cargas).intValue();
		if (color<150){
			color=150;
		}
		Color default_background = new Color( color, color,color);
		
		if (cargas<=0){
				default_background =Color.white;	
			
			
		}else {
			
		}
		c.setForeground(Color.black);
		if (isSelected){
			
			c.setBackground(azul);;
		}else {
				c.setBackground(default_background);	
			
				
		}
		
		
		*/
		
		return c;
	}
	
}