package aplicacion.compras.pedidoe.logic;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import aplicacion.compras.pedidoe.interfaces.*;
import aplicacion.compras.pedidoe.gui._Frame;
import aplicacion.compras.pedidoe.logic._Logic;

public class TableItemColorCellRenderer extends DefaultTableCellRenderer {
	private _Logic logic; 
	
	public _Logic getLogic() {
		return logic;
	}

	public void setLogic(_Logic logic) {
		this.logic = logic;
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int rowIndex, int vColIndex) {
		Color azul = new Color(100, 100, 255);
		Component c = super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, rowIndex, vColIndex);
			if (table.getName()==_Interface._table_faltantes){
				Color color=logic.getShowCost(table, rowIndex,isSelected);
				Color default_background = color;
				c.setBackground(default_background);
			}else{
				Color default_background = c.getBackground();
				if (table.getName()==_Interface._table_items){
					if (isSelected){
						c.setBackground(azul);
					}else {
						c.setBackground(default_background);	
					}	
				}
				
			}
			
		
		
		return c;

	}

}