package aplicacion.gestion.agenda.gestion.logic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import java.util.Vector;

import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.table.*;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;
import aplicacion.modelo.logic.*;
class NewTableCellEditor extends AbstractCellEditor implements TableCellEditor {

	  _TableCellRenderer renderer = new _TableCellRenderer();
	  private Logic logic=null;
	  ChangeEvent changeEvent = null;

	  JTable tree;

	  public NewTableCellEditor() {
	    
	  }

	  public Object getCellEditorValue() {
	    JTable table = renderer.getTableRenderer();
	    return table;
	  }
	  /*
	  public boolean isCellEditable(EventObject event) {
	    boolean returnValue = true;
	    if (event instanceof MouseEvent) {
	      MouseEvent mouseEvent = (MouseEvent) event;
	      TreePath path = tree.getPathForLocation(mouseEvent.getX(),
	          mouseEvent.getY());
	      if (path != null) {
	        Object node = path.getLastPathComponent();
	        if ((node != null) && (node instanceof DefaultMutableTreeNode)) {
	          DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) node;
	          Object userObject = treeNode.getUserObject();
	          returnValue = ((treeNode.isLeaf()) && (userObject instanceof CheckBoxNode));
	        }
	      }
	    }
	    
	    return returnValue;
	  }*/



	public Logic getLogic() {
		return logic;
	}

	public void setLogic(_Logic logic) {
		this.logic = logic;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		// TODO Auto-generated method stub
		  
		Component editor = renderer.getTableCellRendererComponent(table, value, isSelected, false,row, column);

	    
	    if (editor instanceof JTable) {
	    	JTable chk=((JTable) editor);
	      chk.addMouseListener(logic.getConstructor().getMouseListener());
	    }

	    return editor;
		
	}
	}