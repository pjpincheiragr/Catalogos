package aplicacion.compras.carga.pedido.logic;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableLineaColorCellRenderer extends DefaultTableCellRenderer {

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int rowIndex, int vColIndex) {

		Component c = super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, rowIndex, vColIndex);
		Color default_foreground = new Color(255, 255, 255);
		Color default_background = new Color(255, 255, 255);
		Color pedido = new Color(255, 120, 120);
		Color excluido = new Color(140, 140, 140);
		
		Color _default_foreground = new Color(235, 235, 235);
		Color _default_background = new Color(235, 235, 235);
		Color _pedido = new Color(235, 100, 100);
		Color _excluido = new Color(120, 120, 120);
		Color C = default_background;
		c.setForeground(Color.black);
		String linea = "";

		double items = 0;
		
		try {
			linea = ((String) table.getValueAt(rowIndex, 1));
		} catch (Exception e) {
		}
		try {
			items = ((Double) table.getValueAt(rowIndex, 4));
		} catch (Exception e) {
			//e.printStackTrace();
		}

		

		
		if (items < 0) {
			items = 0;
		}
		boolean b = false;
		try {
			b = ((Boolean) table.getValueAt(rowIndex, 0));
		} catch (Exception e) {
			//e.printStackTrace();
		}
		//System.out.println("cellrenderer> "+art+" pedir?"+pedir+" stock?"+stock
		// +" minimo?"+minimo+" selected?"+b);
		if (items > 0) {
			if (b) {
				
					c.setBackground(pedido);
					if (isSelected){
						c.setBackground(_pedido);
					}
				} else {
					c.setBackground(excluido);
					if (isSelected){
						c.setBackground(_excluido);
					}
				}
			} else {
				c.setBackground(default_foreground);
				if (isSelected){
					c.setBackground(_default_foreground);
				}
			}
		return c;
	}

}