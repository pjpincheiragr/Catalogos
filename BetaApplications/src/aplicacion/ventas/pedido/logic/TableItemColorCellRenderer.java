package aplicacion.ventas.pedido.logic;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


import aplicacion.ventas.pedido.gui._Frame;
import aplicacion.modelo.logic.Logic;

public class TableItemColorCellRenderer extends DefaultTableCellRenderer {
	private Logic logic; 
	
	public Logic getLogic() {
		return logic;
	}

	public void setLogic(Logic logic) {
		this.logic = logic;
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int rowIndex, int vColIndex) {
		
		Component c = super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, rowIndex, vColIndex);
		c.setBackground(c.getBackground());
		c.setForeground(Color.DARK_GRAY);
			if (logic instanceof aplicacion.ventas.pedido.logic._Logic){
				_Logic _logic=(_Logic) logic;
				if (!_logic.getShowCost()){
					c.setBackground(c.getBackground());
					c.setForeground(c.getBackground());
					
				}	else{
					
				}	
			}else{
				if (logic instanceof aplicacion.ventas.facturador.logic._Logic){
					aplicacion.ventas.facturador.logic._Logic _logic2=(aplicacion.ventas.facturador.logic._Logic) logic;
					if (!_logic2.getShowCost()){
						c.setBackground(c.getBackground());
						c.setForeground(c.getBackground());
						
					}
				}
			}
			
		
		
		return c;

	}
			
			

}