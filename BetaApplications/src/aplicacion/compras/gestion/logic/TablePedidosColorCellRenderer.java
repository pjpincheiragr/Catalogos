package aplicacion.compras.gestion.logic;

import java.awt.Color;
import java.awt.Component;
import aplicacion.compras.gestion.logic.*;
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
		
		Color default_foreground = new Color(255, 255, 255);
		Color verde = new Color(255, 100, 100);
		Color azul = new Color(100, 100, 255);
		
		String agenda="";
		String creado="";
		String seguimiento="";
		try {
			agenda = table.getValueAt(rowIndex, 2).toString();
			creado = table.getValueAt(rowIndex, 7).toString();
			seguimiento = table.getValueAt(rowIndex, 8).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double cargas=logic.getControlDias(agenda, creado);
		
		int color=new Double(cargas).intValue();
		if (color<100){
			color=100;
		}
		Color default_background = new Color( color, color,color);
		if (seguimiento.compareTo("false")==0){
			cargas=0;
			default_background = Color.white;
		}
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
		
		
		
		return c;
	}
	
}