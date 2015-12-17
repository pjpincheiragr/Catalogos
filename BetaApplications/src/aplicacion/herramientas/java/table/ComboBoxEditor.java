package aplicacion.herramientas.java.table;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.InputMap;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import javax.swing.JComboBox;
import javax.swing.text.Document;
import javax.swing.undo.UndoManager;



public class ComboBoxEditor {
	private Color backgroundColor=Color.white;
	private Color selection_backgroundColor=Color.white;
	private Color foregroundColor=Color.black;
	private Color selection_foregroundColor=Color.black;
	private Font font=new Font("Arial", Font.PLAIN, 9);
	private KeyAdapter keyListener=null;
	private MouseAdapter mouseadapter=null;
	private ItemListener itemlistener=null;
	private String name="";
	private String[] values=null;
	private Class tipo=String.class;
	
	
	public void setTipo(Class tipo){
		this.tipo=tipo;
	}
	
	public DefaultCellEditor getCellEditor(){
		JComboBox combo = new JComboBox(){
			public void processFocusEvent(FocusEvent e) {
				Component focusOwner = KeyboardFocusManager
						.getCurrentKeyboardFocusManager().getFocusOwner();
				if (isDisplayable() && e.getID() == FocusEvent.FOCUS_GAINED) {
					if (focusOwner == this) {
						this.setBackground(Color.orange);
					}
				} else {
					if (e.getID() == FocusEvent.FOCUS_LOST) {
						this.setBackground(Color.white);
					}
				}
				super.processFocusEvent(e);
			}
		};

		if (name!=""){
			combo.setName(name);
		}
		
		combo.setFont(font);
		if (keyListener!=null){
			combo.addKeyListener(keyListener);
		}
		if (mouseadapter!=null){
			combo.addMouseListener(mouseadapter);
		}
		if (itemlistener!=null){
			combo.addItemListener(itemlistener);
		}
		
		DefaultComboBoxModel model=new DefaultComboBoxModel(values);
    	combo.setModel(model);
	    DefaultCellEditor editor = new DefaultCellEditor(combo);
	    return editor;
	}
	
	
	public void seFont(Font font){
		this.font=font;
	}

	public void setBackgroundColor(Color c){
		this.backgroundColor=c;
	}
	
	public void setSelectedBackgroundColor(Color c){
		this.selection_backgroundColor=c;
	}
	
	public void setForegroundColor(Color c){
		this.foregroundColor=c;
	}
	
	public void setSelectedForegroundColor(Color c){
		this.selection_foregroundColor=c;
	}
	
	public void addKeyListener(KeyAdapter key){
		this.keyListener=key;
	}
	public void addItemListener(ItemListener item){
		this.itemlistener=item;
	}
	public void setName(String name){
		this.name=name;
	}

	public String[] getValues() {
		return values;
	}

	public void setValues(String[] values) {
		this.values = values;
	}
}
