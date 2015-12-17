package aplicacion.herramientas.java.table;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class Column {
	private String name="";
	private int width=120;
	private DefaultCellEditor cellEditor=null;
	private boolean editable=false;
	
	private Class clase=null;
	private int aligment=JTextField.LEFT;
	private TableCellRenderer _tablecellrenderer =null;
	
	
	
	public void setName(String name){
		this.name=name;
	}
	
	
	public void setCellRenderer(TableCellRenderer _tablecellrenderer){
		this._tablecellrenderer=_tablecellrenderer;
	}
	public void setAligment(int aligment){
			this.aligment=aligment;
	}
	
	public int getAligment(){
		return this.aligment;
	}
	
	public void setWidth(int width){
		this.width=width;
	}
	
	public void setCellEditor(DefaultCellEditor cellEditor){
		this.cellEditor=cellEditor;
	}
	
	public void setEditable(boolean editable){
		this.editable=editable;
	}
	
	public void setClass(Class clase){
		this.clase=clase;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public DefaultCellEditor getCellEditor(){
		return this.cellEditor;
	}
	
	public TableCellRenderer getTableCellRenderer(){
		return this._tablecellrenderer;
	}
	public boolean isEditable(){
		return this.editable;
	}
	
	
	public Class getColumnClass(){
		return this.clase;
	}
}
