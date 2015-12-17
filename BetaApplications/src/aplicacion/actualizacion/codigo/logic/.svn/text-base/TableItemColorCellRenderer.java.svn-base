package aplicacion.actualizacion.codigo.logic;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
/**
 * @author Agustin Wisky
 * @company Wismi S.A.
 * @since 10-10-2009
 */
public class TableItemColorCellRenderer extends DefaultTableCellRenderer {

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int rowIndex, int vColIndex) {

		Component c = super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, rowIndex, vColIndex);
		Color default_foreground = new Color(255, 255, 255);
		Color default_background = new Color(255, 255, 255);
		Color pedido = new Color(255, 100, 100);
		Color excluido = new Color(140, 140, 140);
		Color menor = new Color(185, 100, 100);
		Color agregado = new Color(100, 190, 100);
		Color C = default_background;
		c.setForeground(Color.black);
		String art = "";

		double stock = 0;
		double minimo = 0;
		double pedir = 0;
		try {
			art = ((String) table.getValueAt(rowIndex, 1));
		} catch (Exception e) {
		}
		try {
			stock = ((Double) table.getValueAt(rowIndex, 5));
		} catch (Exception e) {
			//e.printStackTrace();
		}

		try {
			minimo = ((Double) table.getValueAt(rowIndex, 4));
		} catch (Exception e) {
			//e.printStackTrace();
		}

		try {
			pedir = ((Double) table.getValueAt(rowIndex, 7));
			
		} catch (Exception e) {
			//e.printStackTrace();
		}

		if (stock < 0) {
			stock = 0;
		}
		boolean b = false;
		try {
			b = ((Boolean) table.getValueAt(rowIndex, 0));
		} catch (Exception e) {
			//e.printStackTrace();
		}
		//System.out.println("cellrenderer> "+art+" pedir?"+pedir+" stock?"+stock
		// +" minimo?"+minimo+" selected?"+b);
		if (pedir > 0) {

			if (b) {
				if ((minimo - stock) == pedir) {
					c.setBackground(pedido);
				} else {
					if ((minimo - stock) < pedir) {
						c.setBackground(agregado);
					} else {
						c.setBackground(menor);
						// c.setBackground(menor);
					}

				}

			} else {
				c.setBackground(excluido);
			}

		} else {
			if ((minimo - stock > 0) & (minimo > 0)) {
				c.setBackground(excluido);
			} else {
				c.setBackground(default_foreground);
			}
		}
		if (isSelected){
			c.setBackground(Color.blue);
			
		}
		return c;

	}

}