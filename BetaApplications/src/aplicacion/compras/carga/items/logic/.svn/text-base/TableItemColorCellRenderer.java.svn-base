package aplicacion.compras.carga.items.logic;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableItemColorCellRenderer extends DefaultTableCellRenderer {

	private _Logic logic=null;
	
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int rowIndex, int vColIndex) {

		Component c = super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, rowIndex, vColIndex);
		String idarticulo="";
		try {
			idarticulo=table.getValueAt(rowIndex, 1).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		if (idarticulo.compareTo("")!=0){
			double qty=logic.getArticuloCantidad(idarticulo);
			if (qty<=0){
				c.setBackground(Color.LIGHT_GRAY);
			}else{
				c.setBackground(Color.white);
			}
		}
		return c;

	}

	public _Logic getLogic() {
		return logic;
	}

	public void setLogic(_Logic logic) {
		this.logic = logic;
	}

}