package aplicacion.herramientas.java.exportar.logic;

import aplicacion.herramientas.java.exportar.interfaces.*;
import aplicacion.herramientas.java.exportar.gui.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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
	private int lenght,max;
	private boolean debug,done,canceled,override;
	private Timer Timer;  //  @jve:decl-index=0:
	private Crono crono;
	private List<columna> Columns;
	private JTable _table;
	private Object[][] _data;
	public Object[][] get_data() {
		return _data;
	}
	public void set_data(Object[][] _data) {
		this._data = _data;
	}

	private File file;
	private BufferedWriter out =null;
	private aplicacion.herramientas.java.visualselector.constructor._Constructor vSelector=null;
	
	public _Logic(){
		Columns=new ArrayList<columna>();
	}
	public void addColumn(columna c){
		System.out.println("Adding Columns!");
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
	
	public boolean hasSelection(){
		boolean has=true;
		try {
			if (_table==null){
				has=false;	
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			has=false;
		}
		return has;
	}
	
   

   public void goExportar() {
		SwingWorker worker=null;
		if (worker==null){
		worker = new SwingWorker() {
			@Override
			public Object construct() {
				current = 0;
				done = false;
				
				return new _taskExportar();
			}
		};
		}
		done = false;
		
		worker.start();
	}
   
    
	class _taskExportar {
		_taskExportar() {
			_taskworkExportar();
				}
		}
	
	
	//metodos basicos de tareas swing
	public void createTimer(){
		crono=new Crono();
		crono.start();
		Timer=new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (done |canceled){
					endbar();
					Timer.stop();
					canceled=false;
					done=false;
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
		frame.getJProgressBar().setValue(lenght);
		frame.get_btn_actualizar().setEnabled(true);
	}
	
	public void clean(){
		frame.get_txt_archivo().setText("");
		frame.getJProgressBar().setString("");
	}
	
	public void doCancel(){
		canceled=true;
		endbar();
		aviso("Proceso de Actualizacion Cancelado");
		frame.get_btn_actualizar().setEnabled(true);
		frame.get_btn_cancelar().setEnabled(false);
		
	}
	
	private void writeFile(){
		 this.createTimer();
		 String path=frame.get_txt_archivo().getText();
		 this.create_write_file(path);
	}
	 
	
	
	public void buscar_archivo(){
		JFileChooser JF = new JFileChooser();
		int fx=JF.showSaveDialog(frame);
		if (fx == JFileChooser.APPROVE_OPTION) {
	        File file = JF.getSelectedFile();
	        this.frame.get_txt_archivo().setText(file.getAbsolutePath());
	        
		}
	}
	
	
	
	
	
	 
	 public boolean create_write_file(String file_path){
		 boolean ok=true;
		 try {
 			///aca va el lector de archivo log de la tarea
 			
 			file=new File(file_path);
 			file = file.getAbsoluteFile();
 			if (file.exists())file.delete();
 			file.createNewFile();
 			out = new BufferedWriter(new FileWriter(file));
 			
 		}catch (Exception e){
 			ok=false;
 		}
 		return ok;
	 }
	
	 public boolean addLine(String line){
		 boolean ok=true;
		 try {
			out.write(line);
			 out.newLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			ok=false;
		}
		return ok;
	 }
	 
	 
	public boolean close_write_file(){
		 boolean ok=true;
		 try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			ok=false;
		}
		return ok;
	}
	
	
	public void crear_tabla(){
		if (Columns.size()>0){
			Object[][] results=null;
			results=new Object[Columns.size()][2];
			for (int i=0;i<this.Columns.size();i++){
				columna colx=Columns.get(i);
				results[i][0]=colx.getMaster();
				results[i][1]=colx.getNombre();
			}
			this.create_table(results);
			frame.setVisible(true);
		}else{
			
			aviso("Nada Configurado");
		}
		
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
		
		List<Object[]> tmp=new ArrayList<Object[]>();
		for (int row=0;row<frame.getJTable1().getRowCount();row++){
			String colname=frame.getJTable1().getValueAt(row, 1).toString();
			columna col=this.getColumna(colname);
			boolean val=(Boolean)frame.getJTable1().getValueAt(row, 0);
			if (val){
					tmp.add(new Object[]{col,val});
			}
		}
		return tmp;
	}

	public boolean masterHasSetted(){
		boolean set=false;
		for (int row=0;row<frame.getJTable1().getRowCount();row++){
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
		for (int i=0;i<tmp.size();i++){
				Object[] iterator=tmp.get(i);
				columna colum=(columna)iterator[0];
				int colx=(Integer)iterator[1];
				if (colum.getMaster()){
					index=colx;
				}
		}
		return index;
	}
	
	public void guardar(){
		String file_path=frame.get_txt_archivo().getText();
		if (file_path.compareTo("")!=0){
			if (!file_path.endsWith(".txt")){
				file_path+=".txt";
			}
			boolean ok=this.create_write_file(file_path);
			if (ok){
				crono=new Crono();
		        crono.start();
				this.goExportar();	
			}else{
				error("Error al crear Archivo");
			}
			
		}else{
			error("Debe Seleccionar el Destino "+this.getMaster().getNombre());
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
		int selected=0;
		if (_table!=null){
			
			for (int row=0;row<_table.getRowCount();row++){
				boolean b=(Boolean) _table.getValueAt(row, 0);
				if (b) selected++;
			}
				
		}
		return selected;
	}
	


	public void _taskworkExportar(){
		
		List<Object[]> tmp=this.get_Columns();
		if (this.hasSelection()){
		this.lenght=this.getSelected();
		}else{
			if (_table!=null){
				this.lenght=_table.getRowCount();	
			}
			if (_data!=null){
				this.lenght=_data.length;	
			}
		}
		if (lenght>0){
			for (int i=0;i<tmp.size();i++){
				Object[] iterator=tmp.get(i);
				columna colum=(columna)iterator[0];
				boolean col=(Boolean)iterator[1];
				//System.out.println("Configuracion >"+colum.getNombre()+"("+colum.getColumn()+")->"+col);
		}
			int master=1;
			if (this.getMaster()!=null){
				master=getMaster().getColumn();	
			}
		
		current=0;
		int rows=lenght;
		for (int row=0;row<lenght;row++){
			boolean b=true;
			if (this.hasSelection()){
				if (_table!=null){
					b=(Boolean) _table.getValueAt(row, 0);	
				}
			}
			if (b){
				
				String key="";
				if (_table!=null){
					key=_table.getValueAt(row, master).toString();	
				}
				if (_data!=null){
					key=(String)_data[row][master];	
				}
				
				estado="Exportanto "+key;
				String line=key;
					for (int col=0;col<tmp.size();col++){
						Object[] iterator=tmp.get(col);
						columna colx=(columna)iterator[0];
						String value="";
						if (_table!=null){
							value=_table.getValueAt(row, colx.getColumn()).toString();	
						}
						if (_data!=null){
							value=(String)_data[row][colx.getColumn()];	
						}
						
						
						
						if (!colx.getMaster()){
							if (line.length()>0){
								line+="	";
							}
							line+=value;
						}
					}
				this.addLine(line);
				
				current++;
			}
		}		
		}else{
			error("Seleccione las filas que desea importar");
		}
		this.close_write_file();
		done=true;
		aviso("Aplicacion Exportar Completa con "+current+" Operaciones ");
		_exitSwing();
	}
	
	private int search_key(String key,int column){
		int row=0;
		boolean found=false;
		while (!found & row<_table.getRowCount()){
			found=_table.getValueAt(row, column).toString().compareTo(key)==0;
			if (!found) row++;
		}
		if (!found) row=-1;
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
		col.setEditable(true);
		col.setClass(Boolean.class);
		table.addColumn(col);
		
		col = new Column();
		col.setName("Tabla");
		col.setWidth(200);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);
		/*
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
		*/
				
		table.setData(results);

		Font fuente = new Font("Arial", Font.PLAIN, 9);
		table.setHeaderFont(fuente);
		table.build();
		table.fillData();
		JTable _table = table.getTable();
		
		aplicacion.herramientas.java.exportar.constructor._Constructor C=
			(aplicacion.herramientas.java.exportar.constructor._Constructor) this.getConstructor();
		_table.addMouseListener(C.getMouseListener());
		_table.addKeyListener(C.getKeyListener());
		Font font=new Font("Dialog", Font.PLAIN, 10);
		_table.setFont(font);
		
		frame.setJTable1(table.getTable());
	}
	
	public void focus(){
		
		frame.get_txt_archivo().requestFocusInWindow();
	}
	
	public void seleccionar(boolean b){
		if (frame.getJTable1()!=null){
			int[] indexes=null;
			try {
				indexes=frame.getJTable1().getSelectedRows();
			}catch(Exception e){
				
			}
			if (indexes!=null & frame.getJTable1().getSelectedColumn()==2){
				for (int i=0;i<indexes.length;i++){
					frame.getJTable1().setValueAt(b, indexes[i], 0);
				}
			}else {
				for (int i=0;i<frame.getJTable1().getRowCount();i++){
					frame.getJTable1().setValueAt(b, i, 0);
				}	
			}
			
		}
	}
	
	}
