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
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.event.CellEditorListener;
import javax.swing.table.*;
import aplicacion.modelo.logic.*;
class _TableCellEditor  implements TableCellEditor {

	  CheckBoxNodeRenderer renderer = new CheckBoxNodeRenderer();
	  private Logic logic=null;
	  

	  JTree tree;

	  
	  public boolean stopCellEditing(){
		  return true;
	  }
	  
	  public Object getCellEditorValue() {
	    MyCheckBox checkbox = renderer.getLeafRenderer();
	    
	    CheckBoxNode checkBoxNode = new CheckBoxNode(checkbox.getText(),checkbox.getIdclasificacion(),checkbox.isSelected());
	    return checkBoxNode;
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

	  public Component getTreeCellEditorComponent(JTree tree, Object value,
	      boolean selected, boolean expanded, boolean leaf, int row) {
		Component editor = renderer.getTreeCellRendererComponent(tree, value,
	        true, expanded, leaf, row, true);

	    
	    if (editor instanceof JCheckBox) {
	    	JCheckBox chk=((JCheckBox) editor);
	      chk.addItemListener(logic.getConstructor().getItemListener());
	    }

	    return editor;
	  }

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
		return null;
	}
	@Override
	public void addCellEditorListener(CellEditorListener l) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void cancelCellEditing() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean isCellEditable(EventObject anEvent) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void removeCellEditorListener(CellEditorListener l) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean shouldSelectCell(EventObject anEvent) {
		// TODO Auto-generated method stub
		return false;
	}
	}