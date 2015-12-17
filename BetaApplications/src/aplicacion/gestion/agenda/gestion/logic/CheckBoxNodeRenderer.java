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
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;
public class CheckBoxNodeRenderer implements TreeCellRenderer {
	  private MyCheckBox leafRenderer = new MyCheckBox();
	  private _Logic logic;
	  private DefaultTreeCellRenderer nonLeafRenderer = new DefaultTreeCellRenderer();

	  Color selectionBorderColor, selectionForeground, selectionBackground,
	      textForeground, textBackground;

	  protected MyCheckBox getLeafRenderer() {
	    return leafRenderer;
	  }

	  public CheckBoxNodeRenderer() {
	    Font fontValue;
	    fontValue = UIManager.getFont("Tree.font");
	    if (fontValue != null) {
	      leafRenderer.setFont(fontValue);
	    }
	    Boolean booleanValue = (Boolean) UIManager
	        .get("Tree.drawsFocusBorderAroundIcon");
	    leafRenderer.setFocusPainted((booleanValue != null)
	        && (booleanValue.booleanValue()));

	    selectionBorderColor = UIManager.getColor("Tree.selectionBorderColor");
	    selectionForeground = UIManager.getColor("Tree.selectionForeground");
	    selectionBackground = UIManager.getColor("Tree.selectionBackground");
	    textForeground = UIManager.getColor("Tree.textForeground");
	    textBackground = UIManager.getColor("Tree.textBackground");
	  }

	  public Component getTreeCellRendererComponent(JTree tree, Object value,
	      boolean selected, boolean expanded, boolean leaf, int row,
	      boolean hasFocus) {
		  
	    Component returnValue;
	    

	      String stringValue = tree.convertValueToText(value, selected,
	          expanded, leaf, row, false);
	      //System.out.println("Value+"+value+" "+value.getClass());
	     
	      leafRenderer.setText(stringValue);
	      leafRenderer.setSelected(false);
	      
	      
	      leafRenderer.setEnabled(tree.isEnabled());
	      
	      /*String color=logic.getcolor(leafRenderer.getIdclasificacion());
	      Color cx=null;
	      if (color.compareTo("")!=0){
	    	  cx=logic.getColor(color);
	    	  if (selected) {
	    		  cx=logic.getSelectedColor(color);
	    	  }
	      }*/
	        
	        
	      if (selected) {
	       // leafRenderer.setForeground(selectionForeground);
	    	   
	        leafRenderer.setBackground(selectionBackground);
	        leafRenderer.setForeground(textForeground);
	        
	        //leafRenderer.setBackground(textBackground);
	      } else {
	    	leafRenderer.setForeground(textForeground);
	        leafRenderer.setBackground(textBackground);
	      }

	      if ((value != null) && (value instanceof DefaultMutableTreeNode)) {
	    	  
	        Object userObject = ((DefaultMutableTreeNode) value)
	            .getUserObject();
	        
	        if (userObject instanceof CheckBoxNode) {
	          CheckBoxNode node = (CheckBoxNode) userObject;
	          leafRenderer.setSelected(node.isSelected());
	          
	        }
	      }
	      
	      if (value instanceof CheckBoxNode) {
	    	  CheckBoxNode node = (CheckBoxNode) value;
	          leafRenderer.setText(node.getText());
	          leafRenderer.setIdclasificacion(node.getIdclasificacion());
	          leafRenderer.setSelected(node.isSelected());
	      }
	      returnValue = leafRenderer;
	  
	    return returnValue;
	  }

	public _Logic getLogic() {
		return logic;
	}

	public void setLogic(_Logic logic) {
		this.logic = logic;
	}
	}