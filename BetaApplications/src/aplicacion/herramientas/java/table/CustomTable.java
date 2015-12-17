package aplicacion.herramientas.java.table;

import aplicacion.herramientas.java.sortableselector.*;
import aplicacion.herramientas.java.sortableselector.logic.SortButtonRenderer;
import aplicacion.herramientas.java.sortableselector.logic.SortableTableModel;

import java.awt.AlphaComposite;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.DefaultCellEditor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;
import javax.swing.table.*;


public class CustomTable extends JComponent{
	private LinkedList columns=null;
	private int rows=1;
	private String name="";
	private MouseAdapter mouseadapter=null;
	private MouseListener mouselistener=null;
	private MouseAdapter columnmouseadapter=null;
	private KeyAdapter keyadapter=null;
	private Font headerFont=null;
	private Font Font=null;
	private boolean sortable=true;
	private int width=400;
	private int height=200;
	private int x=0;
	private int y=0;
	private JTable table=null;
	private JScrollPane scrollPane=null;
	private Object[][] data=null;
	private float alfa=1.0f;
	
	public void setAlfa(float f){
		this.alfa=f;
	}
	public CustomTable(){
		columns=new LinkedList();
	}
	
	public void setRowCount(int rowcount){
		this.rows=rowcount;
	}
	
	public void addColumn(Column c){
		int i=0;
		boolean found=false;
		while (i<columns.size()){
			Column col=(Column) columns.get(i);
			found=col.getName()==c.getName();
			i++;
		}
		if (!found){
			columns.addLast(c);
		}
	}
	public void setSortable(boolean sortable){
		this.sortable=sortable;
	}
	public void setHeaderFont(Font _headerFont){
		this.headerFont=_headerFont;
	}
	public void setFont(Font _Font){
		this.Font=_Font;
	}
	
	public Column getCustomColumn(int index){
		Column col=null;
		if (index>=0){
			if (index<columns.size()){
				col=(Column) columns.get(index);
			}	
		}
		return col;
	}
	
	private JTable setAligment(JTable jTable){
		TableColumnModel model = jTable.getColumnModel();
		int[] values=this.getColumnAligments();
		DefaultTableCellRenderer tcr =null;
		for (int u=0;u<values.length;u++) {
			Column c=getCustomColumn(u);
			if (c.getColumnClass()!=Boolean.class){
				tcr = new DefaultTableCellRenderer();
				tcr.setHorizontalAlignment(values[u]);
				model.getColumn(u).setCellRenderer(tcr);
			}
		}
		return jTable;
	}
	
	private JTable setSizes(JTable jTable){
		TableColumnModel model = jTable.getColumnModel();
		int[] values=this.getColumnSizes();
		for (int u=0;u<values.length;u++) {
			model.getColumn(u).setWidth(values[u]);
			model.getColumn(u).setPreferredWidth(values[u]);
		}
		return jTable;
	}
	
	private JTable setDefaultOptions(JTable jTable){
		jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jTable.getTableHeader().setReorderingAllowed(false);
		jTable.setColumnSelectionAllowed(true);
		jTable.setRowSelectionAllowed(true);
		return jTable;
	}
	
	private SortableTableModel constructTableModel(){
		SortableTableModel defaultTableModel = new SortableTableModel(){
			public boolean isCellEditable(int row, int col) {
				boolean editable=true;
				Column column=getCustomColumn(col);
				if (column!=null){
					editable=column.isEditable();
				}
				return editable;
			}
			public Class getColumnClass(int col) {
				Class column_class=super.getColumnClass(col);
				Column column=getCustomColumn(col);
				if (column!=null){
					if (column.getColumnClass()!=null){
						column_class=column.getColumnClass();	
					}
				}
				return column_class;
	        }
		};
		
		defaultTableModel.setRowCount(rows);
		defaultTableModel.setColumnCount(this.getColumnIdentifiers().length);
		defaultTableModel.setColumnIdentifiers(this.getColumnIdentifiers());
		return defaultTableModel;
	}
	
	private String[] getColumnIdentifiers(){
		String[] identifiers=null;
		if (columns!=null){
			if (columns.size()>0){
				identifiers=new String[columns.size()];
				for (int i=0;i<columns.size();i++){
					identifiers[i]=getCustomColumn(i).getName();
				}
			}
		}
		return identifiers;
	}
	
	private int[] getColumnSizes(){
		int[] sizes=null;
		if (columns!=null){
			if (columns.size()>0){
				sizes=new int[columns.size()];
				for (int i=0;i<columns.size();i++){
					sizes[i]=getCustomColumn(i).getWidth();
				}
			}
		}
		return sizes;
	}	
	
	private int[] getColumnAligments(){
		int[] sizes=null;
		if (columns!=null){
			if (columns.size()>0){
				sizes=new int[columns.size()];
				for (int i=0;i<columns.size();i++){
					sizes[i]=getCustomColumn(i).getAligment();
				}
			}
		}
		return sizes;
	}
	
	private JTable constructTable(SortableTableModel defaultTableModel){
		JTable jTable =null;
		
		jTable = new JTable(defaultTableModel){
			
			public void paint(Graphics g) {
	        	Graphics2D g2d = (Graphics2D)g;
	       		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alfa));
	            super.paint(g);
	            
	        }
			
			public Component prepareRenderer(TableCellRenderer renderer,
                    int rowIndex, int vColIndex) {
		        	Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
		        	Column column=getCustomColumn(vColIndex);
					if (column!=null){
						if (column.getTableCellRenderer()!=null){
							c = super.prepareRenderer(column.getTableCellRenderer(), rowIndex, vColIndex);
							
						}
					}
		        	return c;
			}    
			
			public TableCellEditor getCellEditor(int row, int col){
				TableCellEditor cellEditor=super.getCellEditor(row, col);
				Column column=getCustomColumn(col);
				if (column!=null){
					if (column.getCellEditor()!=null){
						cellEditor=column.getCellEditor();
						
					}
				}
				return cellEditor;
			}
			
		};
		jTable.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),"none" );

		jTable=this.setDefaultOptions(jTable);
		jTable=this.setSizes(jTable);
		jTable=this.setAligment(jTable);
		if (mouselistener!=null){
			jTable.addMouseListener(mouselistener);
		}
		
		if (keyadapter!=null){
			jTable.addKeyListener(keyadapter);
		}
		 
		if (columnmouseadapter!=null){
			JTableHeader header = jTable.getTableHeader();
		    header.addMouseListener(columnmouseadapter);	
		}
		if (name.compareTo("")!=0){
			jTable.setName(name);	
		}
		jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jTable.setAutoCreateColumnsFromModel(false);

		TableColumnModel model = jTable.getColumnModel();
		if (this.sortable){
			SortButtonRenderer renderer = new SortButtonRenderer();
			if (this.headerFont!=null){
				renderer.setHeaderFont(headerFont);
			}
			for (int u=0;u<model.getColumnCount();u++) {
				model.getColumn(u).setHeaderRenderer(renderer);
			}
	
		}else{
			
		}
				
		
		//jTable =this.setAligment(jTable);
		//sacar el comportamiento default de enter!
		
		jTable.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),"none" );
		defaultTableModel.setTable(jTable);
		

		//TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(defaultTableModel);
		if (this.headerFont!=null){
			
			JTableHeader header = jTable.getTableHeader();
			
			final TableCellRenderer headerRenderer = header.getDefaultRenderer();
			header.setDefaultRenderer( new TableCellRenderer() {
				public Component getTableCellRendererComponent( JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column ) {
					Component comp = headerRenderer.getTableCellRendererComponent( table, value, isSelected, hasFocus, row, column );
					comp.setFont( headerFont );
					return comp;
				}
			});
		}
		if (this.Font!=null){
			jTable.setFont(this.Font);
			
		}
		if (sortable){
			TableRowSorter sorter = new TableRowSorter(defaultTableModel);
			jTable.setRowSorter(sorter);	
		}
		
		jTable=setAligment(jTable);
		return jTable;
	}
	
	public void setWidth(int width){
		this.width=width;
	}
	
	public void setHeight(int height){
		this.height=height;
	}
	
	public JScrollPane getSrollPane(){
		
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(new Rectangle(x, y, width, height));
			if (table!=null){
				scrollPane.setViewportView(table);	
			}
		}
		return scrollPane;
	}
	public void setData(Object[][] data){
		this.data=data;
		if (data!=null){
			rows=data.length;
		}
	}
	
	public void fillData(){
		
		if (data!=null){
			int[] lengths=new int[data[0].length];
			for (int j=0;j<data[0].length;j++){
				lengths[j]=0;
			}
			if (data!=null){
				if (data.length>0){
					if (data[0].length>0){
						if (data[0].length==this.columns.size()){
							for (int i=0;i<data.length;i++){
								for (int j=0;j<data[0].length;j++){
									if (getCustomColumn(j).getColumnClass()==Boolean.class){
										boolean tmp=false;
										try {
											tmp=new Boolean(data[i][j].toString()=="1");
										}catch(Exception e){
											
										}
										try {
											tmp=(Boolean)(data[i][j]);
										}catch(Exception e){
											
										}
										table.setValueAt(tmp, i, j);
									}else {
										if (data[i][j]==null){
											if (getCustomColumn(j).getColumnClass()==Double.class){
												table.setValueAt(0.0, i, j);
											}else {
												if (getCustomColumn(j).getColumnClass()==Boolean.class){
													table.setValueAt(false, i, j);
												}else {
														table.setValueAt("", i, j);	
													
													
												}
											}
											
										}else {
											table.setValueAt(data[i][j], i, j);	
											if (data[i][j].toString().length()>lengths[j]){
												lengths[j]=data[i][j].toString().length();
											}
										}
											
									}
									
								}
							}
						}else {
							System.out.println("column sizes!=data[0].length???"+data[0].length+"!="+this.columns.size());
						}
					}else {
						System.out.println("Data[0] length<=0?");	
					}
				}else {
					System.out.println("Data length<=0?");
				}
			}else {
				DefaultTableModel model=(DefaultTableModel)this.table.getModel();
				model.setRowCount(0);
				System.out.println("Data null");
			}	
		}
		
		
	}
	
	
	public void build(){
		

		
		
		
			SortableTableModel defaultTableModel = constructTableModel();
				table =constructTable(defaultTableModel);		
				
			
	}
	
	public JTable getTable(){
		return this.table;
	}
	
	public void addMouseAdapter(MouseAdapter mouseadapter){
		this.mouseadapter=mouseadapter;
	}
	
	public void addMouseListener(MouseListener mouselistener){
		this.mouselistener=mouselistener;
	}
	
	public void addKeyListener(KeyAdapter keyadapter){
		this.keyadapter=keyadapter;
	}
	
	public void setName(String name){
		this.name=name;
	}
}
