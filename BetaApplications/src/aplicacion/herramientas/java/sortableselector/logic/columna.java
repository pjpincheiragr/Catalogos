package aplicacion.herramientas.java.sortableselector.logic;
import javax.swing.JTextField;
import javax.swing.JTable;
public class columna {
	private String nombre="";
	private String alias="";
	private int width=120;
	private boolean master=false;
	private JTextField columnField=null;
	private JTable table=null;
	private int row=0;
	private int col=0;
	private Class clase =String.class; 
	
	
	public void setNombre(String nombre){
		this.nombre=nombre;
	}
	
	public Class getClase(){
		return this.clase;
	}
	
	public void setClass(Class clase){
		this.clase=clase;
	}
	public void setColumnField(JTextField column){
		this.columnField=column;
	}
	
	public void setJTable(JTable jtable){
		this.table=jtable;
		
	}
	public void setRow(int row){
		this.row=row;
	}
	
	public int getRow(){
		return this.row;
	}
	
	public void setColumn(int col){
		this.col=col;
	}
	public int getColumn(){
		return this.col;
	}
	public JTable getJtable(){
		return this.table;
	}
	public void fillData(String data){
		if (table!=null){
			this.table.setValueAt(data, row, col);	
		}
		
	}
	
	public JTextField getColumnField(){
		return this.columnField;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public void setAlias(String alias){
		this.alias=alias;
	}
	
	public String getAlias(){
		return this.alias;
	}
	
	public void setWidth(int w){
		this.width=w;
	}
	
	public int getWidth(){
		return width;
	}
	
	public void setMaster(boolean master){
		this.master=master;
	}
	
	public boolean getMaster(){
		return this.master;
	}
}
