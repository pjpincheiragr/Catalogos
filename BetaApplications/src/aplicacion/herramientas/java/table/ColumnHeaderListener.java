package aplicacion.herramientas.java.table;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JTable;
import javax.swing.table.*;

public class ColumnHeaderListener extends MouseAdapter {
    public void mouseClicked(MouseEvent evt) {
        JTable table = ((JTableHeader)evt.getSource()).getTable();
        TableColumnModel colModel = table.getColumnModel();

        // The index of the column whose header was clicked
        int vColIndex = colModel.getColumnIndexAtX(evt.getX());
        int mColIndex = table.convertColumnIndexToModel(vColIndex);

        // Return if not clicked on any column header
        if (vColIndex == -1) {
            return;
        }
        	
        table.setColumnSelectionAllowed(true);
        table.setRowSelectionAllowed(true);

        if (table.getSelectedColumn()==vColIndex){
            System.out.println("click en header+"+vColIndex);
            table.clearSelection();
        	table.setColumnSelectionInterval(vColIndex, vColIndex);
        	int row = 0;
            int col = vColIndex;
            boolean toggle = false;
            boolean extend = false;
            table.changeSelection(row, col, toggle, extend);
            
            // Extend the selection to include all cells between (2,1) to (5,3)
            row = table.getRowCount();
            col = vColIndex;
            toggle = false;
            extend = true;
            table.changeSelection(row, col, toggle, extend);

           
        }



        // Determine if mouse was clicked between column heads
        Rectangle headerRect = table.getTableHeader().getHeaderRect(vColIndex);
        if (vColIndex == 0) {
            headerRect.width -= 3;    // Hard-coded constant
        } else {
            headerRect.grow(-3, 0);   // Hard-coded constant
        }
        if (!headerRect.contains(evt.getX(), evt.getY())) {
            // Mouse was clicked between column heads
            // vColIndex is the column head closest to the click

            // vLeftColIndex is the column head to the left of the click
            int vLeftColIndex = vColIndex;
            if (evt.getX() < headerRect.x) {
                vLeftColIndex--;
            }
            //System.out.println("click en header+"+vLeftColIndex);
        }
    } 
    public void mouseExited(MouseEvent evt){
    	JTable table = ((JTableHeader)evt.getSource()).getTable();
        TableColumnModel colModel = table.getColumnModel();
    	table.setColumnSelectionAllowed(true);
        table.setRowSelectionAllowed(true);
    	//System.out.println("exit en header+");
    }
}
