package aplicacion.gestion.calendario.logic;

import java.awt.Color;
import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.awt.event.FocusEvent;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import java.awt.event.ItemListener;

public class CheckBoxCellEditor {
	private Class tipo = null;
	private ItemListener itemListener=null;
	private String name="";
	
	public void setName(String name){
		this.name=name;
	}
	public DefaultCellEditor getCellCheck() {
		CustomCheckBoxCellEditor dce5;

		JCheckBox currentCell = new JCheckBox() {
			public void processFocusEvent(FocusEvent e) {
				Component focusOwner = KeyboardFocusManager
						.getCurrentKeyboardFocusManager().getFocusOwner();
				if (isDisplayable() && e.getID() == FocusEvent.FOCUS_GAINED) {
					if (focusOwner == this) {
						this.setBackground(Color.gray);
					}
				} else {
					if (e.getID() == FocusEvent.FOCUS_LOST) {
						this.setBackground(Color.white);
					}
				}
				super.processFocusEvent(e);
			}
		};
		currentCell.setName(name);
		currentCell.addItemListener(itemListener);

		dce5 = new CustomCheckBoxCellEditor(currentCell);
		dce5.setType(tipo);
		return dce5;
	}

	public void setTipo(Class tipo) {
		this.tipo = tipo;
	}
	
	public void setItemListener(ItemListener _itemListener){
		this.itemListener=_itemListener;
	}
}
