/*
 * Created on Apr 4, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package aplicacion.herramientas.java.sortableselector.logic;

import javax.swing.JTable;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.util.*;
/**
 * @version 1.0 02/25/99
 */
public class SortableTableModel extends DefaultTableModel {
	TableSorter sorter;
	JTable table=null;
    
    
	public SortableTableModel() {
		
	}
	
	public void setTable(JTable table){
		this.table=table;
	}
    
	
	
	public void sortByColumn(int column, boolean isAscent) {
		
			sorter = new TableSorter(column, isAscent,this);
		
		fireTableDataChanged();
		DefaultTableModel model=(DefaultTableModel)table.getModel();
		 Vector data = model.getDataVector();
	     Collections.sort(data, new TableSorter(column, isAscent,this));
	     model.fireTableStructureChanged();
		
	}
	
/*
	public Object getValueAt(int row, int col) {
		
		return super.getValueAt(row, col);
	}

	@Override
	public void setValueAt(Object value, int row, int col) {
	 super.setValueAt(value, row, col);
	 fireTableDataChanged();
	}*/
	
	
}
