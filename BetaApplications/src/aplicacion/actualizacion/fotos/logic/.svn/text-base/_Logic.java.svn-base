package aplicacion.actualizacion.fotos.logic;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import aplicacion.herramientas.java.launcher.logic.SwingWorker;
import aplicacion.herramientas.java.sortableselector.logic.Filtro;
import aplicacion.herramientas.java.sortableselector.logic.columna;
import aplicacion.herramientas.java.table.CheckBoxCellEditor;
import aplicacion.herramientas.java.table.Column;
import aplicacion.herramientas.java.table.CustomTable;

import javax.imageio.ImageIO;
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


import aplicacion.actualizacion.fotos.gui._Frame;
import aplicacion.actualizacion.fotos.logic._Data;
import aplicacion.actualizacion.fotos.interfaces.*;
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
	private boolean debug=false,done,canceled,override;
	private Timer Timer;  //  @jve:decl-index=0:
	private Crono crono;
	
	private Column_Selector PF;
	private Object[] test;
	private aplicacion.herramientas.java.visualselector.constructor._Constructor vSelector=null;
	private aplicacion.herramientas.java.buscadores.Proveedor bProveedor=null;
	private aplicacion.herramientas.java.visualizadores.Proveedor vProveedor=null;
	private String server="E:/catalogo/";
	
	public void nueva_politica(){
		aplicacion.inventario.politica.constructor._Constructor
		CC=new aplicacion.inventario.politica.constructor._Constructor();
		CC.setParameter(_parametros.connector, getConstructor().getConnectionHandler());
		CC.build(this.getConstructor());
		CC.init();
	}
	
	public void editar_politica(){
	
		aplicacion.inventario.politica.constructor._Constructor
		CC=new aplicacion.inventario.politica.constructor._Constructor();
		CC.setParameter(_parametros.connector, getConstructor().getConnectionHandler());
		String idpolitica=frame.get_txt_politica().getText();
		CC.setParameter(aplicacion.inventario.politica.interfaces._parametros.idpolitica, idpolitica);
		CC.build(this.getConstructor());
		CC.init();
	}
	
	public void setFrame(JFrame frame){
		this.frame=(_Frame) frame;
		super.setFrame(frame);
	}
	
	public void setData(Data _data){
		this.data=(_Data)_data;
		super.setData(_data);
	}
	
	

	
	public void BuscarLinea(){
		this.BuscarLinea(frame.get_txt_linea());
	}
	private aplicacion.herramientas.java.buscadores.Linea bLinea;
	public void BuscarLinea(JTextField tx){
		
		if (bLinea==null){
			bLinea=new aplicacion.herramientas.java.buscadores.Linea(this.getConstructor());
		}
		
		bLinea.setIdproveedor(frame.get_txt_idproveedor());
		bLinea.BuscarLineaProveedor(tx);	
	
	}
	private aplicacion.herramientas.java.visualizadores.Linea vLinea=null;
	public void buscarLinea(JTextField tx) {
		if (vLinea!=null){
			vLinea.close();
		}
		vLinea=new aplicacion.herramientas.java.visualizadores.Linea(this.getConstructor());
		vLinea.setIdproveedor(frame.get_txt_idproveedor().getText());
		int n = vLinea.buscarLineaProveedor(tx);
		if (n == 0) {
				aviso("no hay Lineas con ese codigo");
		}
	}
	public void evaluar_linea(JTextField tx){
		String linea=tx.getText();
		String idproveedor=frame.get_txt_idproveedor().getText();
		if (linea.compareTo("")!=0){
			if (data.check_linea(idproveedor, linea)){
				frame.get_txt_archivo().requestFocusInWindow();
			}else{
				this.buscarLinea(tx);
			}
		}else{
			this.buscarLinea(tx);
		}
	}
	public void Actualizar(String idproveedor,String pol){
    	estado="Actualizando Diferencias";
    	String q=data.updatediferences(idproveedor, pol);
    	Object[][] results=data.getResults(q);
    	//System.out.println(q);
    	
    	
    	if (results!=null){
    		if (results.length>0){
    			this.lenght=results.length;
    			int u=0;
    			while (u<results.length & !canceled){
    				current =u;
    				String art=(String)results[u][0];
    				String precio=(String)results[u][1];
    				String upd=data._updateArticle(art, idproveedor, pol,precio);
    				estado="Diferencias "+art;
    				data.clearBatch();
    				data.addBatch(upd);
    				data.executeBatch();
    				u++;
    			}
    		}
    	}
    	if (!canceled){
    		
    		ActualizarFechas(idproveedor,pol);
    	}
    	
    }
	
   public List<String> getLineas(){
	   List<String> lineas=new ArrayList<String>();
	   Object[] test=PF.getSettings();
		int i_lin=-1;
		if (test[3].getClass()==Integer.class){
			i_lin=(Integer)test[3];
		} 
	   for (int i=0;i<frame.getJTable().getRowCount();i++){
		 String _linea=frame.getJTable().getValueAt(i, i_lin).toString();
		 if (!lineas.contains(_linea)){
			 lineas.add(_linea);
		 }
	   }
	   System.out.println("Listas para actualizar >"+lineas.size());
	   return lineas;
   }
   
   public void ActualizarFechas(String idproveedor,String pol){
	   frame.getJProgressBar().setIndeterminate(true);
    	estado="Actualizando Fechas de Articulos";
    	current=0;
    	List<String> lineas=this.getLineas();
    	lenght=lineas.size();
    	int i=0;
    	while(i<lineas.size() &!canceled){
    		String q=data.updatefechas(idproveedor,lineas.get(i), pol);
    		data.clearBatch();
        	data.addBatch(q);
        	boolean error=data.executeBatch();
        	if (error){
        		errors++;
        	}
    		current=i;
    		i++;
    	}
    	if (canceled){
    		aviso("Operacion Actualizacion Cancelada con "+current+" operaciones y "+errors+" errores");
    	}else{
    		
    		aviso("Operacion Actualizacion Completa con "+current+" operaciones y "+errors+" errores");
    	}
    	
    	done=true;
    }
   
   
   
   public void evaluar_politica(JTextField tx){
		String politica=tx.getText();
		
		if (politica.compareTo("")!=0){
			if (data.check_politics_existence(politica)){
				this.cargar_politica(politica);
				frame.get_txt_linea().requestFocusInWindow();
			}else{
				this.buscarPolitica(tx);
			}
		}else{
			this.buscarPolitica(tx);
		}
	}
	public void cargarProveedor(String idproveedor){
		
		
			Object[][] results=data.getProveedor(idproveedor);
				if (results!=null){
					if (results.length>0){
						String desc=results[0][1].toString();
						String pol=results[0][2].toString();
						String poldesc=results[0][3].toString();
						String linea=results[0][6].toString();
						frame.get_txt_proveedor_descripcion().setText(desc);
						frame.get_txt_politica().setText(pol);
						frame.get_txt_descripcion_politica().setText(poldesc);
						frame.get_txt_linea().setText(linea);
						frame.get_txt_politica().requestFocusInWindow();
					}
				}
		
	}
	
	private aplicacion.herramientas.java.evaluadores.Proveedor proveedor=null;
	public void initialize_proveedor(){
		proveedor=new aplicacion.herramientas.java.evaluadores.Proveedor(){
			public void cargar(String codigo){
				cargarProveedor(codigo);
			}
		};
		proveedor.setConstructor(this.getConstructor());
	}
	public void BuscarProveedor(JTextField tx){
		proveedor.Buscar(tx);
	}
	public void buscarProveedor(JTextField tx){
		proveedor.buscar(tx);
	}
	public void BuscarProveedor(){
		proveedor.Buscar(frame.get_txt_idproveedor());
	}
	public void evaluarProveedor(JTextField tx){
		proveedor.evaluate(tx);
	}

	public void _goRead() {
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
		if (Timer!=null) {
			Timer.start();
		}
		worker.start();
	}
	
	public void goUpdate(){
		
		String idproveedor=frame.get_txt_idproveedor().getText();
		if (idproveedor.compareTo("")!=0){
			if (data.check_proveedor(idproveedor)){
				this._goUpdate();
			}else{
				error("Debe especificar un Proveedor Valido");
			}
		}else{
			error("Debe Seleccionar El Proveedor que va a actualizar");
		}
		
		
	}
	
public void _goUpdate() {
	this.createTimer();
	frame.get_btn_actualizar().setEnabled(false);
	SwingWorker worker=null;
		worker = new SwingWorker() {
			@Override
			public Object construct() {
				return new _taskUpdate();
			}
		};
		if (Timer!=null) {
			Timer.start();
		}
		worker.start();
	}
	
	class _taskRead {
		_taskRead() {
			_taskworkRead();
				}
		}
	
	class _taskUpdate {
		_taskUpdate() {
			_taskworkUpdate();
			}
		}
	
	//metodos basicos de tareas swing
	public void createTimer(){
		current=0;
		errors=0;
		canceled = false;
		done = false;
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
		estado="";
		frame.getJProgressBar().setString("");
		frame.getJProgressBar().setIndeterminate(false);
		frame.getJProgressBar().setValue(0);
	}
	
	public void clean(){
		frame.get_txt_archivo().setText("");
		frame.get_txt_descripcion_politica().setText("");
		frame.get_txt_idproveedor().setText("");
		frame.get_txt_linea().setText("");
		frame.get_txt_politica().setText("");
		frame.get_txt_proveedor_descripcion().setText("");
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
	
	
	public void BuscarPolitica(){
		this.BuscarPolitica(frame.get_txt_politica());
	}

	
	private aplicacion.herramientas.java.buscadores.Politica bPolitica=null;
	public void BuscarPolitica(JTextField ext) {
		 if (bPolitica==null){
			 bPolitica=new aplicacion.herramientas.java.buscadores.Politica(this.getConstructor());
		 }
	 
	 bPolitica.setDescripcion(frame.get_txt_descripcion_politica());
	 bPolitica.Buscar(ext);
	}
	
	private aplicacion.herramientas.java.visualizadores.Politica vPolitica=null;
	public void buscarPolitica(JTextField tx) {
		if (vPolitica!=null){
			vPolitica.close();
		}
		vPolitica=new aplicacion.herramientas.java.visualizadores.Politica(this.getConstructor());
		int n = vPolitica.Buscar(tx);
		if (n == 0) {
				aviso("no hay Politicas con ese codigo");
		}
	}

	public void Update(){
			done=false;
			canceled=false;
			this.createTimer();
			crono=new Crono();
	        crono.start();
	        current=0;
	        goUpdate();	
	}
	
	public void _taskworkRead(){
		 estado="Cargando Archivo";
		 String path=frame.get_txt_archivo().getText();
		 read_tabulated_file(path);
	}
	 
	public void goRead(){
		String filename=frame.get_txt_archivo().getText();
		if (filename.compareTo("")!=0){
			File file=new File(filename);
			if (file.exists()){
				_goRead();	
			}else{
				error("El archivo "+filename+" es inexistente");
			}	
		}else{
			error("Debe Seleccionar un archivo que contenga la actualizacion");
		}
		
	}
	
	public void buscar_archivo(){
		JFileChooser JF = new JFileChooser();
		int rx=JF.showOpenDialog(frame);
		if (rx == JFileChooser.APPROVE_OPTION) {
            File file = JF.getSelectedFile();
            frame.get_txt_archivo().setText(file.getAbsolutePath());
    	}
	}
	
	
	
	
	public void config(){
		String[] slabels=new String[]{"Codigo","Foto","Linea"};
		boolean[] required=new boolean[]{true,false,true,false};
		int cols=-1;
		if (frame.getJTable()!=null){
			cols=frame.getJTable().getColumnCount();
		}
		if (cols>0){
			if (PF!=null){
				test=PF.getSettings();
				PF=new Column_Selector(cols,slabels,required,test);
				PF.show();
				test=PF.getSettings();
				if (test!=null){
					for (int i=0;i<test.length;i++){
						System.out.println(test[i].toString());
					}	
				}
			}else {
				test=new Object[]{0,1,2,3};
				PF=new Column_Selector(cols,slabels,required,test);
				
			}
			
			PF.show();
		}else{
			error("Debe Cargar El Archivo de actualizacion antes de configurar sus columnas");
		}
	}
	
	
	
	public void actualizar(){
		
		if (frame.getJTable()!=null){
			if (PF!=null){
				if (PF.getSettings()!=null){
					this.goUpdate();
				}else{
					error("Debe configurar las columnas");
				}		
			}else{
				error("Debe configurar las columnas");
			}
			
		}else{
			error("Debe cargar un archivo tabulado primero");
		}
		
	}
	 public void _taskworkUpdate(){
		 
		 String pol=frame.get_txt_politica().getText();
		 String idproveedor=frame.get_txt_idproveedor().getText();
	    	int u=0;
	    	Convertidor Cv=new Convertidor();
	    	Object[] test=PF.getSettings();
    		int i_art=-1;
    		int i_foto=-1;
    		int i_lin=-1;String linea="";
    		try {
    			i_art=(Integer)test[0];	
    		}catch(Exception e){
    			System.out.println(e.getMessage());
    		}
    		try {
    			i_foto=(Integer)test[1];	
    		}catch(Exception e){
    			System.out.println(e.getMessage());
    		} 
    		if (test[2].getClass()==Integer.class){
    			i_lin=(Integer)test[2];
    		}
    		
        	while (u<frame.getJTable().getRowCount() & !canceled){
	    		current=u;
	    		String codigo=(String)frame.getJTable().getValueAt(u, i_art);
	        	codigo=Cv.LimpiarString(codigo, " ");
	        	String foto="";
	        	try {
    				foto=(String)frame.getJTable().getValueAt(u, i_foto);		
    			}catch(Exception e){
    				
    			}
	        	System.out.println("Do Update "+u);
	    		
	    		if (i_lin<0){
	    		linea=frame.get_txt_linea().getText();
	    		}else {
	    			try {
	    				linea=(String)frame.getJTable().getValueAt(u, i_lin);		
	    			}catch(Exception e){
	    				
	    			}
	    		
	    		}
	    		linea=Cv.remove_last_spaces(linea);
	    		
	    		
	    		
				System.out.println(u+"> update "+codigo+" "+foto+" "+linea);
				this.loadFoto(foto, codigo, linea, idproveedor);
	    		u++;
	    	}
	    	
	    	
	    	this.Actualizar(idproveedor, pol);
	    	
	    }
	 
	 public void loadFoto(String path,String codigo,String linea,String idproveedor){
			//System.out.println("addfoto");
		 	File fx=new File(path);
		 	if (fx.exists()){
		 		aplicacion.herramientas.java.image.constructor._Constructor C=
					new aplicacion.herramientas.java.image.constructor._Constructor();
					C.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
					C.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler());
					
					C.setParameter(aplicacion.herramientas.java.image.interfaces._Parametros._eliminar, false);
					C.setShowOnStartup(false);
					C.build(this.getConstructor());
					C.init();
					aplicacion.herramientas.java.image.logic._Logic logic=
						(aplicacion.herramientas.java.image.logic._Logic)C.getLogic();
					logic.setFileName(path);
					logic.loadImage();
					String idarticulo=data.getArticulo(codigo, linea, idproveedor);
					if (idarticulo!=null){
						if (idarticulo.compareTo("")!=0){
							int i=0;
							boolean ok=this.write(logic.getImagePanel().getImage(), i,idarticulo);
							 if (ok){
								 String filename=server;
								 filename+=idarticulo+"";
								 filename+="-"+i+".jpg";
								 data.clearBatch();
								 String qi=data.get_insert_foto(idarticulo, filename);
								 System.out.println(qi);
								 data.addBatch(qi);
								 ok=!data.executeBatch();
							 }
						}
					}
				
		 	}else{
		 		System.out.println("File not exist "+path);
		 	}
							 
		}
	 
		
		private boolean write(BufferedImage img,int sec,String idarticulo){
			String filename=server;
			filename+=idarticulo+"";
			filename+="-"+sec+".jpg";
			
			boolean ok=this.write(img, filename);
			return ok;
			}
		
			private boolean write(BufferedImage img,String filename){
		    	boolean error=false;
		    	String msj="";
		    	String rename=filename.replace(".jpg", ".back");
		    	File file=new File(filename);
		    	File filebak=new File(rename);
		    	if (file.exists()){
		    		file.renameTo(filebak);
		    	}
		    	try {
		    	  ImageIO.write(img, "jpg", file);
		    	}catch(Exception e){
		    		msj=e.getMessage();
		    		e.printStackTrace();
		    		error=true;
		    	}
		    	return !error;
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
				        
				        
				        
				        List<String> line=new ArrayList<String>();
				        while ((record = br.readLine()) != null) {
				        	//cara record es una linea
				           this.current=recCount;	
				           String auxs="";
				           int col=0;
				           
				           //recorro la linea para encontrar las tabulaciones
				           for (int i=0;i<record.length();i++){
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
				     
				     Object[][] results=new Object[dynamic.size()][dynamic.get(0).size()];
				     for (int i=0;i<dynamic.size();i++){
				    	 for (int j=0;j<dynamic.get(0).size();j++){
				    		 results[i][j]=dynamic.get(i).get(j);
				    	 }
				     }
				     this._taskworkReadSwing(results);	 
				     
				      
		  }else {
			  this.error("Debe seleccionar un archivo");
		  }
		  this.done=true;
	  }
	
	
	public void _taskworkReadSwing(Object[][] _results){
		final Object[][] results=_results;
		Runnable _execute = new Runnable() {
	        public void run() {
	        	createTable(results);    	
	        }
		};
		this.invokeLater(_execute);
		done=true;
	}
	
	public void cargar_politica(String idpolitica){
		Object[][] results=data.getPolitica(idpolitica);
		if (results!=null){
			if (results.length>0){
				String descripcion="";
				descripcion=(String) results[0][1];
				frame.get_txt_descripcion_politica().setText(descripcion);
			}
		}
	}
	
	public void createTable(Object[][] results){
		
		CustomTable Table = new CustomTable();
		Column col = null;
		for (int i=0;i<results[0].length;i++){
			col = new Column();
			col.setName("Col "+i);
			col.setWidth(120);
			col.setClass(String.class);
	        col.setEditable(false);
			Table.addColumn(col);
		}
		Table.setData(results);
		Table.build();
		Table.fillData();
		frame.setJTable(Table.getTable());
		
	}
	public void focus(){
		frame.get_txt_idproveedor().requestFocusInWindow();
	}
}
