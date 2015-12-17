package aplicacion.herramientas.java.table;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.InputEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultCellEditor;
import javax.swing.InputMap;
import javax.swing.JTable;
import javax.swing.KeyStroke;

import javax.swing.JTextField;
import javax.swing.text.Document;
import javax.swing.undo.UndoManager;



public class CellEditor {
	private Color backgroundColor=Color.white;
	private Color selection_backgroundColor=Color.white;
	private Color foregroundColor=Color.black;
	private Color selection_foregroundColor=Color.black;
	private Font font=new Font("Arial", Font.PLAIN, 9);
	private KeyAdapter keyadapter=null;
	private MouseAdapter mouseadapter=null;
	private UndoManager undo = null;
	private String name="";
	private UndoAction undoaction=null;
	private RedoAction redoaction=null;  //  @jve:decl-index=0:
	private CustomUndoableEditListener custom_undo=null;
	private Class tipo=String.class;
	private int aligment=JTextField.RIGHT;
	
	private void createUndoManagers(){
		this.undo =new UndoManager();
		this.undoaction=new UndoAction("Undo");
		this.undoaction.setUndoManager(undo);
		this.redoaction=new RedoAction("Redo");
		this.redoaction.setUndoManager(undo);
		this.custom_undo=new CustomUndoableEditListener();
		this.custom_undo.setUndoManager(undo);
	}
	
	public void setTipo(Class tipo){
		this.tipo=tipo;
	}
	public void setAligment(int aligment){
		this.aligment=aligment;
	}
	public CustomCellEditor getCellEditor(){
		CustomCellEditor dce;
		
		this.createUndoManagers();
		JTextField textfield = new JTextField(){
			
			public void processFocusEvent(FocusEvent e){
				JTextField tx=(JTextField)e.getSource();
				Component focusOwner = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
				if (isDisplayable() && e.getID()==FocusEvent.FOCUS_GAINED ){
					if (focusOwner==this ){
						this.setBackground(selection_backgroundColor);
						this.setForeground(selection_foregroundColor);
						this.setSelectionStart(0);
						this.setSelectionEnd(tx.getText().length());	
					}
					
				}
				else {
					if (e.getID()==FocusEvent.FOCUS_LOST){
						this.setBackground(backgroundColor);
						this.setForeground(foregroundColor);
					}
				}
				super.processFocusEvent(e);
			}
			
		};
		textfield.setHorizontalAlignment(aligment);
		String action = "LastComponentTabAction";
		InputMap map = textfield.getInputMap(JTextField.WHEN_FOCUSED);
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), action);
		//map.put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, InputEvent.SHIFT_MASK), action);
		Action lastComponentAction = new AbstractAction(action) {
			public void actionPerformed(ActionEvent e) {
				//System.out.println("e.getActionCommand1()" + e.getActionCommand());
			}
		};
		textfield.getInputMap(JTextField.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP,0),"none" );
		textfield.getInputMap(JTextField.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0),"none" );
		textfield.getKeymap().addActionForKeyStroke(KeyStroke.getKeyStroke(KeyEvent.VK_UP, InputEvent.CTRL_MASK), lastComponentAction);
        textfield.getKeymap().addActionForKeyStroke(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, InputEvent.CTRL_MASK), lastComponentAction);
        textfield.getKeymap().addActionForKeyStroke(KeyStroke.getKeyStroke(KeyEvent.VK_UP, InputEvent.CTRL_DOWN_MASK), lastComponentAction);
        textfield.getKeymap().addActionForKeyStroke(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, InputEvent.CTRL_DOWN_MASK), lastComponentAction);
        
		textfield.getActionMap().put(action,lastComponentAction);
		
		textfield.setDragEnabled(true);
		if (name!=""){
			textfield.setName(name);
		}
		Document doc = textfield.getDocument();
		doc.addUndoableEditListener(custom_undo);
		textfield.getActionMap().put("Undo",undoaction);
		textfield.getInputMap().put(KeyStroke.getKeyStroke("control Z"), "Undo");
		textfield.getActionMap().put("Redo",redoaction);
		textfield.getInputMap().put(KeyStroke.getKeyStroke("control Y"), "Redo");
		
		textfield.setFont(font);
		if (keyadapter!=null){
			textfield.addKeyListener(keyadapter);
		}
		if (mouseadapter!=null){
			textfield.addMouseListener(mouseadapter);
		}
		dce = new CustomCellEditor( textfield );
		dce.setType(tipo);
		return dce;
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
		this.keyadapter=key;
	}
	
	public void setName(String name){
		this.name=name;
	}
}
