package aplicacion.herramientas.java.importar.logic;
import aplicacion.herramientas.java.Crono;
import aplicacion.herramientas.java.importar.interfaces.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import aplicacion.herramientas.java.launcher.logic.SwingWorker;
import aplicacion.herramientas.java.sortableselector.logic.Filtro;
import aplicacion.herramientas.java.sortableselector.logic.columna;
import aplicacion.herramientas.java.table.CellEditor;
import aplicacion.herramientas.java.table.CheckBoxCellEditor;
import aplicacion.herramientas.java.table.Column;
import aplicacion.herramientas.java.table.CustomTable;
import aplicacion.herramientas.java.table.ComboBoxEditor;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;


import aplicacion.herramientas.java.importar.gui._Frame;
import aplicacion.herramientas.java.importar.logic._Data;
import aplicacion.herramientas.java.importar.interfaces.*;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Data;
import aplicacion.modelo.logic.Logic;

import aplicacion.herramientas.java.*;



public class _Logic extends Logic {
	private _Data data;
	private _Frame frame;
	//variables de tareas swingwork
	private String estado="";
	private int current;
	private int errors;
	private int lenght,max;
	private boolean debug,done,canceled,override;
	private Timer Timer;  //  @jve:decl-index=0:
	private Crono crono;
	private List<columna> Columns;
	private JTable _table;
	private boolean custom=false;
	

	private aplicacion.herramientas.java.visualselector.constructor._Constructor vSelector=null;
	
	public _Logic(){
		Columns=new ArrayList<columna>();
	}
	public void addColumn(columna c){
		System.out.println("Adding Columns!"+c.getNombre());
		this.Columns.add(c);
	}
	
	
	public void setFrame(JFrame frame){
		this.frame=(_Frame) frame;
		super.setFrame(frame);
	}
	
	public void setData(Data _data){
		this.data=(_Data)_data;
		super.setData(_data);
	}
	
	public JTable get_table() {
		return _table;
	}
	public void set_table(JTable _table) {
		this._table = _table;
	}
	
	public void cargar_modos(){
		frame.get_lst_modo().removeAllItems();
		frame.get_lst_modo().addItem("Sobreescribir");
		frame.get_lst_modo().addItem("Agregar");
		frame.get_lst_modo().addItem("Insertar");
	}
	
   
   public void goRead() {
	   this.createTimer();
		SwingWorker worker=null;
		if (worker==null){
		worker = new SwingWorker() {
			@Override
			public Object construct() {
				
				return new _taskRead();
			}
		};
		}
		if (Timer!=null){
			Timer.start();
		}
		worker.start();
	}

   public void goImportar() {
	   this.createTimer();
		SwingWorker worker=null;
		if (worker==null){
		worker = new SwingWorker() {
			@Override
			public Object construct() {
				return new _taskImportar();
			}
		};
		}
		if (Timer!=null){
			Timer.start();
		}
		worker.start();
	}
   
    class _taskRead {
		_taskRead() {
			_taskworkRead();
			}
		}
    
	class _taskImportar {
		_taskImportar() {
			_taskworkImportar();
				}
		}
	
	
	//metodos basicos de tareas swing
	public void createTimer(){
		crono=new Crono();
		crono.start();
		lenght=0;
		current=0;
		errors=0;
		done = false;
		canceled=false;
		Timer=new Timer(300, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (done |canceled){
					endbar();
					Timer.stop();
					}else {
						
					updateBar();
				}
			}
		}); 
		
	}
	
	
	public void updateBar(){
		frame.getJProgressBar().setMaximum(lenght);
		frame.getJProgressBar().setValue(current);
		frame.getJProgressBar().setString(estado+" "+current+"/"+lenght+" "+crono.elapsed());
		frame.getJProgressBar().setStringPainted(true);
	}
	
	public void endbar(){
		estado="";
		frame.getJProgressBar().setString("");
		frame.getJProgressBar().setIndeterminate(false);
		frame.getJProgressBar().setValue(0);
	
	}
	
	public void clean(){
		frame.get_txt_archivo().setText("");
		frame.getJProgressBar().setString("");
		frame.setJTable(null);
	}
	
	public void doCancel(){
		canceled=true;
		endbar();
		aviso("Proceso de Actualizacion Cancelado");
		frame.get_btn_actualizar().setEnabled(true);
		frame.get_btn_cancelar().setEnabled(false);
		
	}
	
	private void _taskworkRead(){
		 this.createTimer();
		 String path=frame.get_txt_archivo().getText();
		 read_tabulated_file(path);
	}
	 
	public void cargar_archivo(){
		crono=new Crono();
        crono.start();
        goRead();
	}
	
	public void buscar_archivo(){
		JFileChooser JF = new JFileChooser();
		int rx=JF.showOpenDialog(frame);
		if (rx == JFileChooser.APPROVE_OPTION) {
            File file = JF.getSelectedFile();
            frame.get_txt_archivo().setText(file.getAbsolutePath());
    	}
	}
	
	
	
	
	
	 
	 
	public void read_tabulated_file(String path){
		
		  int chrx=9;//este nueve es un caracter de tabulacion
		  String record = null;
		  List<List<String>> dynamic=new ArrayList<List<String>>();
		  if (path.compareTo("")!=0){
			  File file=new File(path);
				 int recCount = 0; 

				     try { 
				    	 
				    	FileReader fr     = new FileReader(file);
				        BufferedReader br = new BufferedReader(fr);
				        record = new String();
				        int cols=0;
				        fr= new FileReader(file);
				        br=new BufferedReader(fr);
				        record = new String();
				        
				        if (Timer!=null) {
							Timer.start();
						}
				        
				        List<String> line=new ArrayList<String>();
				        while ((record = br.readLine()) != null) {
				        	//cara record es una linea
				           this.current=recCount;	
				           String auxs="";
				           int col=0,lon=record.length();
				           
				           //recorro la linea para encontrar las tabulaciones
				           for (int i=0;i<lon;i++){
				        	   if (record.charAt(i)==chrx){
				        		   try {
				        			   line.add(auxs);
				        			   
				        		   } catch (Exception e){
				        			   if (debug) {
										System.out.println(e.getMessage());
				        			   }
				        		   }
				        		   auxs="";
				        		   col++;
				        	   }
				        	   else {
				        		   auxs=auxs+record.charAt(i);
				        	   }
				           }
				           try {
				        	   line.add(auxs);
				           }catch (Exception e){
							   if (debug) {
								System.out.println(e.getMessage());
							}
						   }
				           
				           if (debug) {
							System.out.println(record);
						}
				           
				           
				           dynamic.add(line);
				           line=new ArrayList<String>();
				           recCount++;

				        } 
				     } catch (IOException e) { 
				        e.printStackTrace();
				     }
				     this.lenght=dynamic.size();
				     Object[][] results=new Object[dynamic.size()][dynamic.get(0).size()+1];
				     int lon=dynamic.size();

				     for (int i=0;i<lon;i++){
				    	 results[i][0]=false;
				    	 for (int j=0;j<dynamic.get(0).size();j++){
				    		 results[i][j+1]=dynamic.get(i).get(j);
				    	 }
				     }
				     if (results!=null){
				    	 this.create_table_swing(results);
				    	 crear_tabla_swing();
				     }else {
				    	 frame.setJTable(null);
				     }
				     this.done=true;
				     
				      
		  }else {
			aviso("Seleccione un Archivo primero. Utilice la tecla F5 ");  
		  }
		  
		  }
	public void crear_tabla_swing(){
		Runnable _execute = new Runnable() {
	        public void run() {
	        	crear_tabla();
	        }
		};
		javax.swing.SwingUtilities.invokeLater(_execute);
	}
	public void create_table_swing(Object[][] _results){
		final Object[][] results=_results;
		Runnable _execute = new Runnable() {
	        public void run() {
	        	createTable(results);
	        }
		};
		javax.swing.SwingUtilities.invokeLater(_execute);
	}
	public void createTable(Object[][] results){
		CustomTable Table = new CustomTable();
		Column col = null;
	
		col = new Column();
		col.setName("");
		col.setWidth(40);
		col.setClass(Boolean.class);
        col.setEditable(true);
		Table.addColumn(col);
		
		for (int i=1;i<results[0].length;i++){
			col = new Column();
			col.setName("Col "+i);
			col.setWidth(120);
			col.setClass(String.class);
	        col.setEditable(false);
			Table.addColumn(col);
		}
		
		Font font=new Font("Dialog", Font.PLAIN, 10);
		Table.setHeaderFont(font);
		Table.setData(results);
		Table.build();
		Table.fillData();
		
		JTable _table=Table.getTable();
		_table.setFont(font);
		_table.setName(_Interface._table_archivo);
		_table.addMouseListener(this.getConstructor().getMouseListener());
		frame.setJTable(Table.getTable());
		
	}
	
	public void crear_tabla(){
		if (Columns.size()>0){
			Object[][] results=null;
			results=new Object[Columns.size()][3];
			int lon=this.Columns.size();
			
			for (int i=0;i<lon;i++){
				columna colx=Columns.get(i);
				results[i][0]=colx.getMaster();
				results[i][1]=colx.getNombre();
				results[i][2]="";
			}
			this.create_table(results);	
		}else{
			aviso("Nada Configurado");
		}
		
	}
	
	private String[] getColumns(){
		String[] tmp=null;
		tmp=new String[frame.getJTable().getColumnCount()];
		int lon=tmp.length;

		for (int col=1;col<lon;col++){
			tmp[col]="Col "+col;
		}
		return tmp;
	}
	
	private columna getColumna(String colname){
		columna col=null;
		boolean found=false;
		int i=0;
		while (i<Columns.size() & !found){
			found=Columns.get(i).getNombre().compareTo(colname)==0;
			if (!found)i++;
		}
		if (found){
			col=Columns.get(i);
		}
		return col;
	}
	
	private List<Object[]> get_Columns(){
		System.out.println("Get Columns");
		List<Object[]> tmp=new ArrayList<Object[]>();
		int rows=frame.getJTable1().getRowCount();

		for (int row=0;row<rows;row++){
			String colname=frame.getJTable1().getValueAt(row, 1).toString();
			columna col=this.getColumna(colname);
			String val="";
			try {
				val=frame.getJTable1().getValueAt(row, 2).toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (val.compareTo("")!=0){
				int n=-1;
				val=val.replaceAll("Col ","");
				n=new Integer(val);
				if (n>=0){
					tmp.add(new Object[]{col,n});
					System.out.println("Get Columns add> "+col.getNombre()+" = "+n);
				}
			}
		}
		return tmp;
	}
	public boolean masterHasSetted(){
		boolean set=false;
		int rows=frame.getJTable1().getRowCount();

		for (int row=0;row<rows;row++){
			String colname=frame.getJTable1().getValueAt(row, 1).toString();
			columna col=this.getColumna(colname);
			String val="";
			try {
				val=frame.getJTable1().getValueAt(row, 2).toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (val.compareTo("")!=0){
				if (col.getMaster()){
					set=true;
				}
			}
		}
		return set;
	}
	
	public columna getMaster(){
		columna col=null;
		boolean found=false;
		int i=0;
		while (i<Columns.size() & !found){
			found=Columns.get(i).getMaster();
			if (!found)i++;
		}
		if (found){
			col=Columns.get(i);
		}
		return col;
	}
	
	public int getSettedMaster(){
		
		int index=-1;
		boolean found=false;
		List<Object[]> tmp=this.get_Columns();
		int lon=tmp.size();

		for (int i=0;i<lon;i++){
				Object[] iterator=tmp.get(i);
				columna colum=(columna)iterator[0];
				int colx=(Integer)iterator[1];
				if (colum.getMaster()){
					index=colx;
				}
				
		}
		System.out.println("Setted Master?"+index);
		return index;
	}
	
	public void guardar(){
		if (this.masterHasSetted()){
			crono=new Crono();
	        crono.start();
			this.goImportar();
		}else{
			error("Debe Configurar la Columna Requerida "+this.getMaster().getNombre());
		}
	}
	/** metodo clave para la importacion
	 * este metodo debe hacerse un override en la aplicacion que llame al importar.
	 * porque al grabar la importacion. esta aplicacion intentara grabar los valores a traves de 
	 * la clave requerida. por ejemplo idarticulo... en la planilla de articulos.
	 * @param clave
	 * @param col
	 * @param value
	 */
	public void assign(String clave,int col,String value){
		System.out.println("tratando de insertar en huesped key("+clave+") en columna("+col+") el valor="+value);
	}
	
	public int getSelected(){
		int selected=0,rows=frame.getJTable().getRowCount();
		
		for (int row=0;row<rows;row++){
			boolean b=(Boolean) frame.getJTable().getValueAt(row, 0);
			if (b) selected++;
		}
		return selected;
	}
public void deseleccionar(){
		
		frame.getJTable().clearSelection();
		frame.getJTable().transferFocus();
	}
public void seleccionar(boolean b){
		
		JTable table=frame.getJTable();
		int[] rows=table.getSelectedRows();
		if (rows.length>0){
			int lon=rows.length;
			for (int row=0;row<lon;row++){
				table.setValueAt(b, rows[row], 0);
			}	
		}else{
			int lon=table.getRowCount();
			for (int row=0;row<lon;row++){
				table.setValueAt(b, row, 0);
			}
		}
	}

	
	public void _taskworkImportar(){
		List<Object[]> tmp=this.get_Columns();
		this.lenght=this.getSelected();
		int notfound=0;
		if (lenght>0){
			int lon=tmp.size();
			for (int i=0;i<lon;i++){
				Object[] iterator=tmp.get(i);
				columna colum=(columna)iterator[0];
				int col=(Integer)iterator[1];
			}
		int master=this.getSettedMaster();
		current=0;
		int rows=frame.getJTable().getRowCount();
		for (int row=0;row<rows;row++){
			boolean b=(Boolean) frame.getJTable().getValueAt(row, 0);
			if (b){
				
				String key=frame.getJTable().getValueAt(row, master).toString();
				int rowk=this.search_key(key);
				if (rowk<0){
					if (frame.get_lst_modo().getSelectedIndex()==2){
						DefaultTableModel model=(DefaultTableModel)this._table.getModel();
						int column=this.getMaster().getColumn();
						rowk=model.getRowCount()-1;
						String keyx="";
						try {
							keyx=_table.getValueAt(rowk, column).toString();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							//e.printStackTrace();
						}
						if (keyx.compareTo("")==0){
							rowk=model.getRowCount()-1;
							model.setRowCount(model.getRowCount()+1);	
						}else{
							model.setRowCount(model.getRowCount()+1);
							rowk=model.getRowCount()-1;
							
						}
						
						System.out.println("Setting Row from Model "+rowk);
					}
					
				}
				if (rowk>=0){
					lon=tmp.size();
					for (int col=0;col<lon;col++){
						Object[] iterator=tmp.get(col);
						columna colx=(columna)iterator[0];
						int index=(Integer)iterator[1];
						String 	value=frame.getJTable().getValueAt(row, index).toString();
						
						
						estado="Importanto "+key;
						if (!colx.getMaster()|frame.get_lst_modo().getSelectedIndex()==2){
							if (_table!=null){
								if (frame.get_lst_modo().getSelectedIndex()==1){
									try {
										String old=_table.getValueAt(rowk, colx.getColumn()).toString();
										if (!old.contains(value)){
											value=old+" "+value;;	
										}
										
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								_table.setValueAt(value, rowk, colx.getColumn());	
							}
						}
					}
				}else{
					notfound++;
				}
				
				current++;
			}
		}
		
		}else{
			error("Seleccione las filas que desea importar");
		}
		done=true;
		aviso("Aplicacion Importar Completa con "+current+" Operaciones y "+notfound+" claves no encontradas");
		_exitSwing();
	}
	
	private int search_key(String key){
		int column=this.getMaster().getColumn();
		//System.out.println("Searchkey "+key+" en columna "+column);
		int row=0;
		boolean found=false;
		while (!found & row<_table.getRowCount()){
			if (_table.getValueAt(row, column)!=null){
				if (custom){
					String var=_table.getValueAt(row, column).toString();
					var=var.replaceAll("\"", "");
					
				
					key=key.replaceAll("\"", "");
					
					if (var.length()>2){
						found=var.substring(2).toUpperCase().endsWith(new String(key.toUpperCase()));	
					}
					
				}else{
					found=_table.getValueAt(row, column).toString().compareTo(key)==0;	
				}
			}
			if (!found) row++;
		}
		if (!found){
			if (custom){
				System.out.println("No se encontro el codigo "+key);
			}
			row=-1;
		}
		
		return row;	
	}
	
	private void create_table(Object[][] results) {
		CustomTable table = new CustomTable();
		Column col = null;
		CellEditor pce = null;
		ComboBoxEditor Box=null;
		
		col = new Column();
		col.setName("Requerida");
		col.setWidth(100);
		col.setEditable(false);
		col.setClass(Boolean.class);
		table.addColumn(col);
		
		col = new Column();
		col.setName("Tabla");
		col.setWidth(200);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);
				
		col = new Column();
		col.setName("Archivo");
		col.setWidth(200);
		col.setEditable(true);
		col.setClass(String.class);
		Box=new ComboBoxEditor();
		Box.addItemListener(this.getConstructor().getItemListener());
		Box.setName(_Interface._lst_column_selector);
		Box.setValues(getColumns());
		col.setCellEditor(Box.getCellEditor());
		table.addColumn(col);
		
				
		table.setData(results);

		Font fuente = new Font("Arial", Font.PLAIN, 9);
		table.setHeaderFont(fuente);
		table.build();
		table.fillData();
		JTable _table = table.getTable();
		
		aplicacion.herramientas.java.importar.constructor._Constructor C=
			(aplicacion.herramientas.java.importar.constructor._Constructor) this.getConstructor();
		_table.addMouseListener(C.getMouseListener());
		_table.addKeyListener(C.getKeyListener());
		Font font=new Font("Dialog", Font.PLAIN, 10);
		_table.setFont(font);
		
		frame.setJTable1(table.getTable());
	}
	public boolean isCustom() {
		return custom;
	}
	public void setCustom(boolean custom) {
		this.custom = custom;
	}
	public void focus(){
		
		frame.get_txt_archivo().requestFocusInWindow();
	}
}
