package aplicacion.gestion.agenda.gestion.logic;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

import aplicacion.herramientas.java.table.Column;
import aplicacion.herramientas.java.table.CustomTable;

public class JTableCellEditor extends AbstractCellEditor implements TableCellEditor {
    // This is the component that will handle the editing of the cell value
    JTableCellRenderer renderer=new JTableCellRenderer();

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
    	
    	JTable table=renderer.getTableRenderer();
    	System.out.println("Return "+table.getRowCount());
    	return table;
    	
    }
}