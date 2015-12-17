package aplicacion.gestion.agenda.gestion.logic;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

import aplicacion.herramientas.java.table.Column;
import aplicacion.herramientas.java.table.CustomTable;

public class MyTableCellEditor extends AbstractCellEditor implements TableCellEditor {
    // This is the component that will handle the editing of the cell value
    _TableCellRenderer renderer=new _TableCellRenderer();

    // This method is called when a cell value is edited by the user.
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int rowIndex, int vColIndex) {
        // 'value' is value contained in the cell located at (rowIndex, vColIndex)
    	Component c=this.renderer.getTableCellRendererComponent(table, value, isSelected, true, rowIndex, vColIndex);
    	return c;
    }

    // This method is called when editing is completed.
    // It must return the new value to be stored in the cell.
    public Object getCellEditorValue() {
    	return new Object[][]{{"1","2"}};
    	/*JTable tmp=this.renderer.getTableRenderer();
    	Object[][] value=new Object[tmp.getRowCount()][tmp.getColumnCount()];
    	for (int i=0;i<tmp.getRowCount();i++){
    		for (int j=0;j<tmp.getColumnCount();j++){
    			value[i][j]=tmp.getValueAt(i, j);
    		}
    	}
        return value;*/
    }
}