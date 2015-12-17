package aplicacion.sistema.usuario.logic;

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
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;
import aplicacion.modelo.logic.*;
class CheckBoxNodeEditor extends AbstractCellEditor implements TreeCellEditor {

	  CheckBoxNodeRenderer renderer = new CheckBoxNodeRenderer();
	  private Logic logic=null;
	  private String name="";
	  ChangeEvent changeEvent = null;

	  JTree tree;

	  public CheckBoxNodeEditor(JTree tree) {
	    this.tree = tree;
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
	    	chk.setName(name);
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	}