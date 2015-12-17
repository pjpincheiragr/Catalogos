package aplicacion.inventario.planilla.logic;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableItemColorCellRenderer extends DefaultTableCellRenderer {
	private _Logic logic=null;
	
	public _Logic getLogic() {
		return logic;
	}

	public void setLogic(_Logic logic) {
		this.logic = logic;
	}

	
	
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int rowIndex, int vColIndex) {

		Component c = super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, rowIndex, vColIndex);
				if (!isSelected){
					c.setForeground(logic.getForegroundColor(rowIndex, vColIndex));
					c.setBackground(logic.getBackgroundColor(rowIndex, vColIndex));	
				}
				
				
		return c;

	}

}