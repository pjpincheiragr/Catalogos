/*
 * Created on Apr 4, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package aplicacion.herramientas.java.sortableselector.logic;

import java.awt.Component;
import java.awt.Font;
import java.awt.Insets;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * @version 1.0 02/25/99
 */
public class SortButtonRenderer extends JButton implements TableCellRenderer {
	public static final int NONE = 0;

	public static final int DOWN = 1;

	public static final int UP = 2;
	
	private Font headerFont=null;
	
	int pushedColumn;

	Hashtable state;

	JButton downButton, upButton;

	public SortButtonRenderer() {
		pushedColumn = -1;
		state = new Hashtable();
		
		setMargin(new Insets(0, 0, 0, 0));
		setHorizontalTextPosition(LEFT);
		//setIcon(new BlankIcon());

		downButton = new JButton();
		downButton.setMargin(new Insets(0, 0, 0, 0));
		downButton.setHorizontalTextPosition(LEFT);
		//downButton.setIcon(new BevelArrowIcon(BevelArrowIcon.DOWN, false, false));
		//downButton.setPressedIcon(new BevelArrowIcon(BevelArrowIcon.DOWN,
		//		false, true));

		upButton = new JButton();
		upButton.setMargin(new Insets(0, 0, 0, 0));
		upButton.setHorizontalTextPosition(LEFT);
		//upButton.setIcon(new BevelArrowIcon(BevelArrowIcon.UP, false, false));
		//upButton.setPressedIcon(new BevelArrowIcon(BevelArrowIcon.UP, false,
		//		true));

	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		JButton button = this;
		Object obj = state.get(new Integer(column));
		if (obj != null) {
			if (((Integer) obj).intValue() == DOWN) {
				button = downButton;
			} else {
				button = upButton;
			}
		}
		button.setText((value == null) ? "" : value.toString());
		boolean isPressed = (column == pushedColumn);
		button.getModel().setPressed(isPressed);
		button.getModel().setArmed(isPressed);
		return button;
	}

	public void setPressedColumn(int col) {
		pushedColumn = col;
	}

	public void setSelectedColumn(int col) {
		if (col < 0) {
			return;
		}
		Integer value = null;
		Object obj = state.get(new Integer(col));
		if (obj == null) {
			value = new Integer(DOWN);
		} else {
			if (((Integer) obj).intValue() == DOWN) {
				value = new Integer(UP);
			} else {
				value = new Integer(DOWN);
			}
		}
		state.clear();
		state.put(new Integer(col), value);
	}

	public int getState(int col) {
		int retValue;
		Object obj = state.get(new Integer(col));
		if (obj == null) {
			retValue = NONE;
		} else {
			if (((Integer) obj).intValue() == DOWN) {
				retValue = DOWN;
			} else {
				retValue = UP;
			}
		}
		return retValue;
	}
	
	public void setHeaderFont(Font font){
		this.headerFont=font;
		this.setFont(headerFont);
	}
}
