package aplicacion.gestion.agenda.gestion.logic;
import javax.swing.*;
import java.awt.*;

import javax.swing.table.*;

public class TableCheckCellRenderer extends JCheckBox implements TableCellRenderer {
	
	private  _Logic logic=null;
    
    public void setLogic(_Logic logic){
    	this.logic=logic;
    }
    
    public TableCheckCellRenderer() {
        super();
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
        boolean isSelected, boolean hasFocus, int rowIndex, int column) {
    	Color default_foreground = new Color(255, 255, 255);
		Color verde = new Color(255, 100, 100);
		Color azul = new Color(100, 100, 255);
		setBackground(default_foreground);
		String idaviso="";
		String creado="";
		try {
			idaviso = table.getValueAt(rowIndex, 1).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (idaviso.compareTo("")!=0){
			String color=logic.getcolorfromMemory(idaviso);
			if (color.compareTo("")!=0){
				Color _color=logic.getColor(color);
				if (isSelected){
					_color=logic.getSelectedColor(color);
				}
				setBackground(_color);
			}
		}
    	return this;
    }
}