package aplicacion.compras.carga.pedido.logic;
import javax.swing.*;
import java.awt.*;

import javax.swing.table.*;

public class TableCheckCellRenderer extends JCheckBox implements TableCellRenderer {
    public TableCheckCellRenderer() {
        super();
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int rowIndex, int column) {
    	Color default_foreground = new Color(255, 255, 255);
		Color default_background = new Color(255, 255, 255);
		Color pedido = new Color(255, 100, 100);
		Color excluido = new Color(140, 140, 140);
		Color menor = new Color(185, 100, 100);
		Color agregado = new Color(100, 190, 100);
		Color C = default_background;
		setForeground(Color.black);
		String art = "";

		double stock = 0;
		double minimo = 0;
		double pedir = 0;
		try {
			art = ((String) table.getValueAt(rowIndex, 1));
		} catch (Exception e) {
		}
		try {
			stock = ((Double) table.getValueAt(rowIndex, 7));
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			minimo = ((Double) table.getValueAt(rowIndex, 6));
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			pedir = ((Double) table.getValueAt(rowIndex, 9));
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (stock < 0) {
			stock = 0;
		}
		boolean b = false;
		try {
			b = ((Boolean) table.getValueAt(rowIndex, 0));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("cellrenderer> "+art+" pedir?"+pedir+" stock?"+stock
		// +" minimo?"+minimo+" selected?"+b);
		if (pedir > 0) {

			if (b) {
				if ((minimo - stock) == pedir) {
					setBackground(pedido);
				} else {
					if ((minimo - stock) < pedir) {
						setBackground(agregado);
					} else {
						setBackground(menor);
						// setBackground(menor);
					}

				}

			} else {
				setBackground(excluido);
			}

		} else {
			if ((minimo - stock > 0) & (minimo > 0)) {
				setBackground(excluido);
			} else {
				setBackground(default_foreground);
			}
		}
		boolean selected=false;
		try {
			selected=(Boolean) value;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	setSelected(selected);
        return this;
    }
}