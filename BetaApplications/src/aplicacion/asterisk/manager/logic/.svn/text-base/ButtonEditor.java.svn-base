package aplicacion.asterisk.manager.logic;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 * @version 1.0 11/09/98
 */

public class ButtonEditor extends DefaultCellEditor {
  protected JButton button;
  private _Logic logic=null;
  private String actionCommand;
  private String label;
  
  private boolean isPushed;
  
  
  public ButtonEditor(JCheckBox checkBox) {
    super(checkBox);
    button = new JButton();
    button.setOpaque(true);
    button.setText("");
    button.setSize(22, 22);
    button.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-media-record.png")));
    
  }

  public Component getTableCellEditorComponent(JTable table, Object value,
      boolean isSelected, int row, int column) {
    if (isSelected) {
      button.setForeground(table.getSelectionForeground());
      button.setBackground(table.getSelectionBackground());
    } else {
      button.setForeground(table.getForeground());
      button.setBackground(table.getBackground());
    }
    label = (value == null) ? "" : value.toString();
    button.setText(label);
    isPushed = true;
    return button;
  }

  public Object getCellEditorValue() {
    if (isPushed) {
      // 
      // 
      JOptionPane.showMessageDialog(button, label + ": Ouch!");
      // System.out.println(label + ": Ouch!");
    }
    isPushed = false;
    return new String(label);
  }

  public boolean stopCellEditing() {
    isPushed = false;
    return super.stopCellEditing();
  }

  protected void fireEditingStopped() {
    super.fireEditingStopped();
  }

public _Logic getLogic() {
	return logic;
}

public String getActionCommand() {
	return actionCommand;
}

public void setLogic(_Logic logic) {
	this.logic = logic;
}

public void setActionCommand(String actionCommand) {
	this.actionCommand = actionCommand;
}
}

