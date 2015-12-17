package aplicacion.proveedor.guia.logic;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Vector;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import java.util.zip.ZipInputStream;
import java.util.zip.ZipEntry;

import javax.swing.tree.*;

import aplicacion.catalogo.repuestos.constructor._Constructor;
import aplicacion.catalogo.repuestos.interfaces._Interface;
import aplicacion.catalogo.repuestos.logic.Nodo;
import aplicacion.herramientas.java.launcher.logic.SwingWorker;
import aplicacion.herramientas.java.table.Column;
import aplicacion.herramientas.java.table.CustomTable;
import aplicacion.herramientas.java.xml.Atributo;
import aplicacion.herramientas.java.xml.Element;
import aplicacion.herramientas.java.xml.XML;

import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPFile;

import aplicacion.proveedor.guia.gui._Frame;
import aplicacion.proveedor.guia.logic._Data;
import aplicacion.proveedor.guia.constructor.*;
import aplicacion.proveedor.guia.logic.*;
import aplicacion.proveedor.guia.interfaces.*;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Data;
import aplicacion.modelo.logic.Logic;
import aplicacion.sistema.version.logic.JakartaFtpWrapper;
import aplicacion.herramientas.java.*;

public class _Logic extends Logic {
	private _Data data;
	private _Frame frame;

	private JakartaFtpWrapper ftp = null;
	// variables de tareas swingwork
	private String estado = "";
	private String version = "1.";
	private int current;
	private int errors;
	private int lenght, max;
	private Object[][] memory = null;
	private boolean debug = true, done, canceled, override;
	private Timer Timer; // @jve:decl-index=0:
	private Crono crono;
	private String tc = "bupd";
	private File file;
	private BufferedWriter out = null;
	private Object[] test;
	private String ftpserver;
	private String username;
	private String password;
	private String download;
	private String directory = "";
	private aplicacion.herramientas.java.visualselector.constructor._Constructor vSelector = null;
	private aplicacion.herramientas.java.buscadores.Proveedor bProveedor = null;
	private aplicacion.herramientas.java.visualizadores.Proveedor vProveedor = null;
	private aplicacion.herramientas.java.evaluadores.Proveedor proveedor=null;
	
	
	public void cargar_seleccion_proveedor(String idproveedor){
		String[] simulacion_base=data.cargar_simulacion(idproveedor);
		
		if(simulacion_base!=null){
			for (int i=0;i<simulacion_base.length;i++){
				this.modificar_memoria(simulacion_base[i]);
			}
		}
		
	}
	private void modificar_memoria(String idclasificacion){
		boolean found=false;
		int i=0;
		while (i<memory.length & !found){
			found=(memory[i][0].toString().compareTo(idclasificacion)==0);
			if (found){
				memory[i][3]=true;
			}
			i++;
		}
	}
	
	public void guardar(){
		
		String idproveedor=frame.get_txt_idproveedor().getText();
		DefaultTreeModel treeModel =(DefaultTreeModel) frame.getJTree().getModel();
		CheckBoxNode raiz = (CheckBoxNode)treeModel.getRoot();
		System.out.println("Eliminar todas las clasificaciones de "+idproveedor);
		List<String> lista=new ArrayList<String>();

		lista=this.guardar(raiz,idproveedor,lista);
		
		int lon=lista.size();
		
		data.beginTransaction();
		data.clearBatch();
		String q=data.getDeleteClasificacion(idproveedor);
		data.addBatch(q);

		for(int i=0;i<lon;i++){
			q=data.getInsertClasificacion(idproveedor,lista.get(i));
			data.addBatch(q);
		}

		boolean error=data.executeBatch();
		if (error) {
			data.rollbackTransaction();
			error("Error Guardando");
		}else{
			data.commitTransaction();
			aviso("Se Guardo Correctamente");
		}
		
	}
	
	private List<String> guardar(CheckBoxNode nodo,String idproveedor,List<String> lista){
		int cantH;
		cantH=nodo.getChildCount();
		
		if (nodo.isSelected()){
			System.out.println("idclasificacion: "+nodo.getIdclasificacion());
			lista.add(nodo.getIdclasificacion());	
	 	}
		
		for(int i=0;i<cantH;i++){
			lista=guardar((CheckBoxNode)nodo.getChildAt(i),idproveedor,lista);	
			}
		
		return lista;
	}
	
	public void load_variables() {
		this.ftpserver = (String) data.getParametroSqlite("ftp")[0][1];
		this.username = (String) data.getParametroSqlite("ftp_user")[0][1];
		this.password = (String) data.getParametroSqlite("ftp_password")[0][1];
		this.directory = "beta/versions/";
	}

	public void setFrame(JFrame frame) {
		this.frame = (_Frame) frame;
		super.setFrame(frame);
	}

	public void setData(Data _data) {
		this.data = (_Data) _data;
		super.setData(_data);
	}

	public void clean() {
		frame.get_txt_idproveedor().setText("");
		frame.get_txt_idproveedor().setEditable(true);
		frame.setJTree(null);
	}

	public boolean addLine(String line) {
		boolean ok = true;

		try {
			out.write(line);
			out.newLine();
		} catch (IOException e) {
			ok = false;
		}
		return ok;
	}

	public boolean close_write_file() {
		boolean ok = true;

		try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			ok = false;
		}
		return ok;
	}

	private List<CheckBoxNode> addOptions(String directory) {
		List<CheckBoxNode> Options = new ArrayList<CheckBoxNode>();
		File dir = new File(directory);

		if (dir.exists()) {
			String[] children = dir.list();
		
			if (children == null) {
			}
			else {
				for (int i = 0; i < children.length; i++) {
					String filename = children[i];
					CheckBoxNode node = new CheckBoxNode(filename,"idclass@", false);
					Options.add(node);
				}
			}

		}
		return Options;
	}

	public void focus() {
		frame.requestFocusInWindow();
		frame.get_txt_idproveedor().requestFocusInWindow();
		frame.setJTree(null);
	}

	private void loadToMemory() {

		String q = " select id,clasificacion,padre,0 ";
		q += " from b_clasificacion ";
		q += " order by padre ,clasificacion";
		System.out.println(q);
		Object[][] results = data.getResults(q);
		
		if (results != null) {
			if (results.length > 0) {
				System.out.println("Clasificaciones encontradas? "+ results.length);
				memory = new Object[results.length][4];
				for (int i = 0; i < results.length; i++) {
					for (int j = 0; j < 3; j++) {
						memory[i][j] = (String) results[i][j];
					}
					memory[i][3] = results[i][3].toString().compareTo("1")==0;
				}
			}
		}

	}

	public void load_clases_from_memory(CheckBoxNode abuelo) {
		int i = 0;
		int childs = 0;
		
		if (memory != null) {
		
			while (i < memory.length) {
			
				if (memory[i][2].toString().compareTo(abuelo.getIdclasificacion()) == 0) {
					CheckBoxNode padre = new CheckBoxNode(memory[i][1].toString(), memory[i][0].toString(), (Boolean)memory[i][3]);
					
					this.load_clases_from_memory(padre);
					DefaultTreeModel treeModel = (DefaultTreeModel) frame
							.getJTree().getModel();
					treeModel.insertNodeInto(padre, abuelo, childs);
					childs++;
				}
				i++;
			}
		}

	}

	public void load_clases(CheckBoxNode abuelo) {

		String q = "";
		q ="select clasificacion,id,0 "; 
		q+="from b_clasificacion ";  
		q+="where padre like '"+abuelo.getIdclasificacion()+"' ";  
		q+="order by clasificacion  ";
		System.out.println(q);
		Object[][] results = data.getResults(q);
		
		if (results != null) {
			if (results.length > 0) {
		
				for (int i = 0; i < results.length; i++) {
					CheckBoxNode padre = new CheckBoxNode("" + (String) results[i][0] + "",(String) results[i][1],false);
					this.load_clases(padre);
					DefaultTreeModel treeModel = (DefaultTreeModel) frame.getJTree().getModel();
					treeModel.insertNodeInto(padre, abuelo, i);
				}
			}
		}
	}

	
	public void buildTreeCheckBoxNode() {
		Runnable _execute=new Runnable(){
			public void run(){
				buildTree("");
			}
		};
		this.invokeLater(_execute);
	}
	
	private void buildTree(String idproveedor) {
		CheckBoxNode raiz = new CheckBoxNode("Repuestos", "0",false);
		this.loadToMemory();
		this.cargar_seleccion_proveedor(idproveedor);
		this.load_clases_from_memory(raiz);
		DefaultTreeModel treeModel = new DefaultTreeModel(raiz);
		treeModel.addTreeModelListener(this.getConstructor().getTreeModelListener());
		JTree tree = new JTree(treeModel);
		
		tree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.addTreeSelectionListener(this.getConstructor()
				.getTreeSelectionListener());
		tree.setShowsRootHandles(true);
		tree.setName(_Interface._tree);
		CheckBoxNodeRenderer renderer = new CheckBoxNodeRenderer();
		tree.setCellRenderer(renderer);
		CheckBoxNodeEditor checkBoxNodeEditor=new CheckBoxNodeEditor(tree);
		checkBoxNodeEditor.setLogic(this);
		tree.setCellEditor(checkBoxNodeEditor);
		tree.setEditable(true);
		// this.load_clases(raiz);
		//tree.addMouseListener(this.getConstructor().getMouseListener());
		//tree.addKeyListener(this.getConstructor().getKeyListener());
		
		frame.setJTree(tree);	
	}
	
	
	public void evaluate_checkbox(MyCheckBox chk){
		JTree tree=null;
	
		if (chk.getParent() instanceof JTree){
			tree=(JTree) chk.getParent();
			try {
				tree.stopEditing();
			} catch (Exception e) {
			//e.printStackTrace();
			}
		}
	
		if (tree!=null){
			try {
				
					CheckBoxNode nodo=(CheckBoxNode)tree.getSelectionPath().getLastPathComponent();
					System.out.println("Nodo >"+nodo+" "+nodo.isSelected());
					this.seleccionarDesde(nodo,chk.isSelected());
			}catch (Exception e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			}
			System.out.println("Check box tree selection "+tree.getSelectionPath()+" "+chk.isSelected()+" "+chk.getIdclasificacion());
		}
	}
	
	
	public void seleccionarDesde(CheckBoxNode chk,boolean b){
		int cantH;
		chk.setSelected(b);
		cantH=chk.getChildCount();
		

		for (int i=0;i<cantH;i++){
				CheckBoxNode hijo=(CheckBoxNode)chk.getChildAt(i);	
			    	
				if(hijo!=null){
					System.out.println("======hijo:"+(i+1)+"/"+cantH+"");
					this.seleccionarDesde(hijo,b);
				}
			
		}
		DefaultTreeModel treeModel=(DefaultTreeModel) frame.getJTree().getModel();
		treeModel.reload(chk);
	}
		

	

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
	public void BuscarProveedor(){
		proveedor.Buscar(frame.get_txt_idproveedor());
	}

	
	private void cargarProveedor(String idproveedor){
		Object[][] results=data.getProveedorInformation(idproveedor);

		if (results!=null){
			if (results.length>0){
				String descripcion=(String) results[0][0];
				frame.get_txt_idproveedor().setEditable(false);
				//frame.get_txt_proveedor_descripcion().setText(descripcion);
				this.buildTree(idproveedor);
			}
		}
	}
	
	public void evaluarProveedor(JTextField tx){
		proveedor.evaluate(tx);
	}

	public void renombrarCategoria(){
		
	}
	
	public void crearCategoria(){
		
	}
	
}
	

