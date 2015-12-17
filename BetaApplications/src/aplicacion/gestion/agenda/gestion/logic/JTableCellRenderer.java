package aplicacion.gestion.agenda.gestion.logic;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import aplicacion.gestion.agenda.gestion.logic.*;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class JTableCellRenderer implements TableCellRenderer {
	private _Logic logic = null;
	private int minHeight = -1;
	private int currHeight = -1;
	  Color selectionBorderColor, selectionForeground, selectionBackground,
      textForeground, textBackground;

	private JTable tableRenderer = new JTable();
	public void setLogic(_Logic logic) {
		this.logic = logic;
	}

	protected JTable getTableRenderer() {
	    return tableRenderer;
	  }
	
	public JTableCellRenderer(){
		Font fontValue;
	    fontValue = UIManager.getFont("JTable.font");
	    if (fontValue != null) {
	      tableRenderer.setFont(fontValue);
	    }
	    
	    

	    selectionBorderColor = UIManager.getColor("JTable.selectionBorderColor");
	    selectionForeground = UIManager.getColor("JTable.selectionForeground");
	    selectionBackground = UIManager.getColor("JTable.selectionBackground");
	    textForeground = UIManager.getColor("JTable.textForeground");
	    textBackground = UIManager.getColor("JTable.textBackground");

	}
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		
			Component c=null;
			JTable passed = (JTable) value;
			this.tableRenderer=passed;
			c=passed;
			
			return c;
		}
	

}