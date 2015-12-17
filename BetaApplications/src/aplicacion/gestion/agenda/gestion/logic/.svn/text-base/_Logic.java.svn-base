package aplicacion.gestion.agenda.gestion.logic;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuComponent;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DragSource;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.StringTokenizer;

import javax.swing.CellRendererPane;
import javax.swing.DropMode;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.JViewport;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import aplicacion.herramientas.java.*;
import aplicacion.herramientas.java.buscadores.Host;
import aplicacion.herramientas.java.launcher.logic.SwingWorker;
import aplicacion.herramientas.java.sortableselector.logic.Filtro;
import aplicacion.herramientas.java.sortableselector.logic.columna;
import aplicacion.herramientas.java.table.CheckBoxCellEditor;
import aplicacion.herramientas.java.table.Column;
import aplicacion.herramientas.java.table.CustomTable;
import aplicacion.gestion.agenda.gestion.gui._Frame;
import aplicacion.gestion.agenda.gestion.interfaces._Interface;
import aplicacion.modelo.logic.Logic;
import aplicacion.modelo.logic.Data;
import aplicacion.modelo.interfaces.*;
import aplicacion.sistema.version.logic.Crono;
import aplicacion.ventas.gestion2.logic.Nodo;
import aplicacion.gestion.agenda.gestion.logic.TableCheckCellRenderer;

import aplicacion.gestion.agenda.gestion.logic.TablePedidosColorCellRenderer;

import aplicacion.gestion.agenda.gestion.gui._Propiedades;
import aplicacion.gestion.agenda.gestion.logic.CheckBoxNode;

public class _Logic extends Logic {
	private _Frame frame = null;
	private _Data data = null;
	private boolean debug, done, canceled;
	private Timer Timer; // @jve:decl-index=0:
	private Crono crono;
	private String[] lastWeekLoaded = null;
	private boolean nuevo = false;
	DataFlavor urlFlavor, uriListFlavor, macPictStreamFlavor;
	private List<String> avisosDragged = null;
	private Object[][] memory = null;
	private String formato = "dd-MM-yyyy HH:mm:ss";
	private Object[][] colors;
	private Object[][] colors_avisos;
	private String estado = "";
	private int current = 0;
	private int lenght = 0;
	private Object[][] agendas = null;
	private Object[][] agendasM = null;
	private int scrollheight;
	private int scrollwidth;
	DropTarget dropTarget;
	DragSource dragSource;

	private int scrollheight_D;
	private int scrollwidth_D;
	DropTarget dropTarget_D;
	DragSource dragSource_D;

	private int scrollheight_M;
	private int scrollwidth_M;
	DropTarget dropTarget_M;
	DragSource dragSource_M;

	
	public _Logic() {
	}

	public void setFrame(JFrame _frame) {
		this.frame = (_Frame) _frame;
		super.setFrame(_frame);
	}

	public void setData(Data _data) {
		this.data = (_Data) _data;
		super.setData(_data);
	}

	public void clean() {

		frame.get_txt_agenda().setText("");
		frame.setJTree(null);
		frame.setJTable(null);
		frame.setJTable2(null);
		frame.setJTable_semanal(null);
		frame.get_txt_idvendedor().setText("");
		frame.get_txt_vendedor_descripcion();
		nuevo = false;
		this.elegir_vendedor();
	}

	public void init() {
		

	}

	public Boolean isNewWeek(String fecha) {
		boolean newweek = false;
		String before = this.getWeekStartDate(fecha);
		String after = this.getWeekEndDate(fecha);
		if (this.lastWeekLoaded != null) {
			newweek = lastWeekLoaded[0].compareTo(before) != 0
					| lastWeekLoaded[1].compareTo(after) != 0;
			if (newweek) {
				lastWeekLoaded[0] = before;
				lastWeekLoaded[1] = after;
			}
		} else {
			newweek = true;
			lastWeekLoaded = new String[2];
			lastWeekLoaded[0] = before;
			lastWeekLoaded[1] = after;
		}
		return newweek;
	}

	public void getToday() {
		_Frame _frame = (_Frame) this._frame;
		_frame.get_txt_agenda().setText(
				new Convertidor().getDateWithFormat("dd-MM-yyyy HH:mm:ss"));
	}

	public _Data getData() {
		return this.data;
	}

	private aplicacion.herramientas.java.evaluadores.Hora Hora = null;

	public void initialize_Hora() {
		Hora = new aplicacion.herramientas.java.evaluadores.Hora() {
			public void cargar(JTextField tx) {

			}
		};
		Hora.setConstructor(this.getConstructor());
	}

	public void BuscarHora(JTextField tx) {
		Hora.Buscar(tx);
	}

	public void BuscarHora() {
		Hora.Buscar(frame.get_txt_agenda());
	}

	public void evaluarHora(JTextField tx) {
		Hora.evaluate(tx);
	}

	private aplicacion.herramientas.java.evaluadores.Vendedor Vendedor = null;

	public void initialize_Vendedor() {
		Vendedor = new aplicacion.herramientas.java.evaluadores.Vendedor() {
			public void cargar(JTextField tx) {
				String codigo = tx.getText();
				Object[][] results = this.getInfo(codigo);
				String descripcion = (String) results[0][1];
				String cod = (String) results[0][0];
				frame.get_txt_idvendedor().setText(cod);
				frame.get_txt_vendedor_descripcion().setText(descripcion);
			}
		};
		Vendedor.setConstructor(this.getConstructor());
	}

	public void BuscarVendedor(JTextField tx) {
		Vendedor.Buscar(tx);
	}

	public void BuscarVendedor() {
		Vendedor.Buscar(frame.get_txt_idvendedor());
	}

	public void buscarVendedor(JTextField tx) {
		Vendedor.buscar(tx);
	}

	public void evaluarVendedor(JTextField tx) {
		tx.setText(tx.getText().replaceAll(" ", ""));
		Vendedor.evaluate(tx);
	}

	public String validar_vendedor() {
		String idvendedor = "";
		while (idvendedor.compareTo("") == 0) {
			String password = this.requestPassword("Ingrese Su Clave:");
			idvendedor = data.getVendedorValidacion(password);
			if (idvendedor.compareTo("") == 0) {
				error("Error de Validacion de Usuario");
			}
		}
		return idvendedor;
	}

	public void today(){
		
		String fecha=data.getSystemDate();
		int row=this.getRowOnCalendar(fecha);
		int col=this.getColumnOnCalendar(fecha);
		frame.getJTable2().clearSelection();
		frame.getJTable2().changeSelection(row, col, false, false);
		this.goCargar();
	}
	public boolean elegir_vendedor() {
		boolean ok = false;
		String idvendedor = this.validar_vendedor();
		frame.get_txt_idvendedor().setText(idvendedor);
		if (idvendedor.compareTo("") != 0) {
			ok = true;
			this.evaluarVendedor(frame.get_txt_idvendedor());
			frame.get_txt_idvendedor().setEditable(false);
			buildTreeCheckBoxNode();
			
			
		} else {
			ok = false;
		}
		return ok;
	}

	public void cargarAviso(String idaviso) {
		Object[][] results = data.getAviso(idaviso);
		if (results != null) {
			if (results.length > 0) {
				String _idaviso = (String) results[0][0];
				String titulo = (String) results[0][1];
				String mensaje = (String) results[0][2];
				String agenda = (String) results[0][3];
				String idcreador = (String) results[0][4];
				String privado = (String) results[0][5];
				String validacion = this.validar_vendedor();
				//System.out.println("Validacion:" + validacion + " vs "+ idcreador);
				if (validacion.compareTo(idcreador) == 0) {

				} else {
					error("No puede ver este mensaje");
				}

			}
		}
	}

	public void cargarAviso(String idaviso, String validacion) {
		Object[][] results = data.getAviso(idaviso);
		if (results != null) {
			if (results.length > 0) {
				String _idaviso = (String) results[0][0];
				String titulo = (String) results[0][1];
				String mensaje = (String) results[0][2];
				String agenda = (String) results[0][3];
				String idcreador = (String) results[0][4];
				String privado = (String) results[0][5];
				if (validacion.compareTo(idcreador) == 0) {

				} else {
					error("No puede ver este mensaje");
				}

			}
		}
	}

	private int seleccion_destinatarios() {
		return this.getDestinatarios().size();
	}

	private List<String> getDestinatarios() {
		List<String> lista = new ArrayList<String>();

		DefaultTreeModel treeModel=null;
		try {
			treeModel = (DefaultTreeModel) frame.getJTree()
					.getModel();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (treeModel!=null){
			CheckBoxNode raiz = null;
			try {
				raiz = (CheckBoxNode) treeModel.getRoot();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}

			if (raiz != null) {
				lista = this.guardar(raiz, lista);

				int cantH;

				cantH = raiz.getChildCount();

				for (int i = 0; i < cantH; i++) {

					lista = guardar((CheckBoxNode) raiz.getChildAt(i), lista);

				}
			}
	
		}
		
		return lista;

	}

	private List<String> guardar(CheckBoxNode nodo, List<String> lista) {
		int cantH;
		if (nodo.isSelected()) {
			//System.out.println("idclasificacion: " + nodo.getIdclasificacion());
			lista.add(nodo.getIdclasificacion());
		}
		cantH = nodo.getChildCount();

		for (int i = 0; i < cantH; i++) {

			lista = guardar((CheckBoxNode) nodo.getChildAt(i), lista);

		}
		return lista;
	}

	public void nuevo() {
		int row = frame.getJTable_semanal().getSelectedRow();
		int column = frame.getJTable_semanal().getSelectedColumn();
		String fecha = this.getFecha(row, column);
		this.nuevoAviso(fecha);
	}

	public String getRoll(String fecha, int months, int days, int hours,
			int minutes) {
		Date today = new Convertidor().getDateWithFormat(formato, formato,
				fecha);
		java.util.GregorianCalendar _date = new java.util.GregorianCalendar();
		_date.setTime(today);
		if (months != 0) {
			_date.add(Calendar.MONTH, months);
		}
		if (days != 0) {
			_date.add(Calendar.DAY_OF_MONTH, days);
		}
		if (hours != 0) {
			_date.add(Calendar.HOUR_OF_DAY, hours);
		}
		if (minutes != 0) {
			_date.add(Calendar.MINUTE, minutes);
		}
		// _today.add(Calendar.DAY_OF_YEAR, days);
		Date before = _date.getTime();
		String s2 = new Convertidor().getDateWithFormat(formato, before);
		return s2;
	}

	public String getRollDate(String fecha, int months, int days) {
		String formato = "dd-MM-yyyy";
		Date today = new Convertidor().getDateWithFormat(formato, formato,
				fecha);
		java.util.GregorianCalendar _date = new java.util.GregorianCalendar();
		_date.setTime(today);
		if (months != 0) {
			_date.add(Calendar.MONTH, months);
		}
		if (days != 0) {
			_date.add(Calendar.DAY_OF_MONTH, days);
		}

		// _today.add(Calendar.DAY_OF_YEAR, days);
		Date before = _date.getTime();
		String s2 = new Convertidor().getDateWithFormat(formato, before);
		return s2;
	}

	public int getDateDiference(String fecha, String fecha2) {
		String formato = "dd-MM-yyyy";
		Date today = new Convertidor().getDateWithFormat(formato, formato,
				fecha);
		Date movil = new Convertidor().getDateWithFormat(formato, formato,
				fecha2);
		java.util.GregorianCalendar _date = new java.util.GregorianCalendar();
		_date.setTime(today);
		java.util.GregorianCalendar _movil = new java.util.GregorianCalendar();
		_movil.setTime(movil);

		int _ifecha = _date.get(Calendar.DAY_OF_YEAR);
		int _imovil = _movil.get(Calendar.DAY_OF_YEAR);
		int dif = _imovil - _ifecha;
		//System.out.println("DATE diference:" + fecha + "<>" + fecha2 + " ="				+ dif);
		return dif;
	}

	public int getDayHeader(String fecha, int col) {
		String formato = "dd-MM-yyyy";
		Date today = new Convertidor().getDateWithFormat(formato, formato,
				fecha);

		java.util.GregorianCalendar _date = new java.util.GregorianCalendar();
		_date.setTime(today);
		_date.add(Calendar.DAY_OF_YEAR, col + 1);

		int dif = _date.get(Calendar.DAY_OF_MONTH);

		return dif;
	}

	public String getMonthStartDate(String fecha) {
		String formato = "dd-MM-yyyy";
		Date today = new Convertidor().getDateWithFormat(formato, formato,
				fecha);
		java.util.GregorianCalendar _date = new java.util.GregorianCalendar();
		_date.setTime(today);
		_date.set(Calendar.DAY_OF_MONTH,1);
		String s1 = new Convertidor().getDateWithFormat(formato, _date.getTime());
		//System.out.println("Inicio del mes "+s1);
		return s1;
	}
	
	public String getMonthEndDate(String fecha) {
		String formato = "dd-MM-yyyy";
		Date today = new Convertidor().getDateWithFormat(formato, formato,
				fecha);
		java.util.GregorianCalendar _date = new java.util.GregorianCalendar();
		_date.setTime(today);
		int max=_date.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		_date.set(Calendar.DAY_OF_MONTH,max);
		String s1 = new Convertidor().getDateWithFormat(formato, _date.getTime());
		//System.out.println("Fin del mes "+s1);
		return s1;
	}
	public String getWeekStartDate(String fecha) {
		String formato = "dd-MM-yyyy";
		Date today = new Convertidor().getDateWithFormat(formato, formato,
				fecha);
		java.util.GregorianCalendar _date = new java.util.GregorianCalendar();
		java.util.GregorianCalendar _before = new java.util.GregorianCalendar();
		_date.setTime(today);
		_before.setTime(today);
		_before.add(Calendar.DAY_OF_YEAR, -_date.get(Calendar.DAY_OF_WEEK));
		// _today.add(Calendar.DAY_OF_YEAR, days);

		Date before = _before.getTime();
		String s1 = new Convertidor().getDateWithFormat(formato, before);
		// aviso("Start para "+fecha+"="+s1);
		return s1;
	}

	public String getWeekEndDate(String fecha) {
		String formato = "dd-MM-yyyy";
		Date today = new Convertidor().getDateWithFormat(formato, formato,
				fecha);
		java.util.GregorianCalendar _date = new java.util.GregorianCalendar();
		java.util.GregorianCalendar _after = new java.util.GregorianCalendar();
		_date.setTime(today);
		_after.setTime(today);
		_after.add(Calendar.DAY_OF_YEAR, 8 - _date.get(Calendar.DAY_OF_WEEK));

		// _today.add(Calendar.DAY_OF_YEAR, days);

		Date after = _after.getTime();
		String s2 = new Convertidor().getDateWithFormat(formato, after);
		// aviso("End para "+fecha+"="+s2);
		return s2;
	}

	public void evaluar_posponer(JComboBox cb) {
		int option = cb.getSelectedIndex();
		String fecha = data.getSystemDateTime();
		if (Hora.esCorrecta(fecha)) {
			if (option == 1) {
				fecha = this.getRoll(fecha, 0, 0, 0, 5);
			}
			if (option == 2) {
				fecha = this.getRoll(fecha, 0, 0, 0, 10);
			}
			if (option == 3) {
				fecha = this.getRoll(fecha, 0, 0, 0, 15);
			}
			if (option == 4) {
				fecha = this.getRoll(fecha, 0, 0, 0, 30);
			}
			if (option == 5) {
				fecha = this.getRoll(fecha, 0, 0, 1, 0);
			}
			if (option == 6) {
				fecha = this.getRoll(fecha, 0, 0, 2, 0);
			}
			if (option == 7) {
				fecha = this.getRoll(fecha, 0, 1, 0, 0);
			}
			frame.get_txt_agenda().setText(fecha);
		}

	}

	public boolean autorizado(String idaviso, String idupdater) {
		boolean ok = true;
		if (!nuevo) {
			if (data.exist(idaviso)) {
				ok = false;
				Object[][] results = data.getAviso(idaviso);
				if (results != null) {
					if (results.length > 0) {
						String _idaviso = (String) results[0][0];
						String titulo = (String) results[0][1];
						String mensaje = (String) results[0][2];
						String agenda = (String) results[0][3];
						String idcreador = (String) results[0][4];
						//System.out.println("Validacion:" + idupdater + " vs "		+ idcreador);
						if (idupdater.compareTo(idcreador) == 0) {
							ok = true;
						}
					}
				}
			} else {
				ok = true;
			}
		}
		return ok;
	}

	public void buildTreeCheckBoxNode() {
		Runnable _execute = new Runnable() {
			public void run() {
				buildTree("");
			}
		};
		this.invokeLater(_execute);
	}

	private void loadToMemory() {
		String iduser = frame.get_txt_idvendedor().getText();
		String q = "select id,clasificacion,padre,0,background from b_categorias_avisos where iduser like '"
				+ iduser + "' order by padre ,clasificacion ";
		Object[][] results = data.getResults(q);
		boolean loaded=false;
		if (results != null) {
			if (results.length > 0) {
				//System.out.println("Clasificaciones encontradas? "+ results.length);
				loaded=true;
				memory = new Object[results.length + 1][5];
				for (int i = 0; i < results.length; i++) {
					for (int j = 0; j < 5; j++) {
						memory[i][j] = (String) results[i][j];
					}
					memory[i][3] = true;

				}
				memory[results.length][0] = "";
				memory[results.length][1] = "Sin Clasificar";
				memory[results.length][2] = "0";
				memory[results.length][3] = true;
				memory[results.length][4] = "";

			}
		}
		if (!loaded){
			memory = new Object[1][5];
			memory[results.length][0] = "";
			memory[results.length][1] = "Sin Clasificar";
			memory[results.length][2] = "0";
			memory[results.length][3] = true;
			memory[results.length][4] = "";
		}
	}

	public void load_clases_from_memory(CheckBoxNode abuelo) {
		int i = 0;
		int childs = 0;
		if (memory != null) {
			while (i < memory.length) {
				if (memory[i][2].toString().compareTo(
						abuelo.getIdclasificacion()) == 0) {
					CheckBoxNode padre = new CheckBoxNode(memory[i][1]
							.toString(), memory[i][0].toString(),
							(Boolean) memory[i][3]);
					
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

	public void cargar_seleccion_idaviso(String idaviso) {
		String[] simulacion_base = data.getAvisoUserList(idaviso);
		for (int i = 0; i < simulacion_base.length; i++) {
			this.modificar_memoria(simulacion_base[i]);
		}
	}

	private void modificar_memoria(String idclasificacion) {
		//System.out.println("modificacion memoria:" + idclasificacion + " "+ true);

		int i = 0;
		if (memory != null) {
			while (i < memory.length) {
				memory[i][3] = true;

				i++;
			}
		}

	}

	private void buildTree(String idaviso) {
		this.buildTree(idaviso, null);
	}

	private void buildTree(String idaviso, String default_iduser) {
		CheckBoxNode raiz = new CheckBoxNode("Categorias", "0", true);
		this.loadToMemory();
		// this.cargar_seleccion_idaviso(idaviso);
		if (default_iduser != null) {
			this.modificar_memoria(default_iduser);
		}
		this.load_clases_from_memory(raiz);
		DefaultTreeModel treeModel = new DefaultTreeModel(raiz);
		treeModel.addTreeModelListener(this.getConstructor()
				.getTreeModelListener());
		JTree tree = new JTree(treeModel);
		dropTarget = new DropTarget(tree, this.getConstructor()
				.getDropTargetListener());
		tree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);

		tree.setEditable(true);
		tree.setShowsRootHandles(true);

		tree.setName(_Interface._tree);
		CheckBoxNodeRenderer renderer = new CheckBoxNodeRenderer();
		renderer.setLogic(this);
		tree.setCellRenderer(renderer);
		CheckBoxNodeEditor checkBoxNodeEditor = new CheckBoxNodeEditor(tree);
		
		checkBoxNodeEditor.setLogic(this);
		tree.setCellEditor(checkBoxNodeEditor);
		tree.addMouseListener(this.getConstructor().getMouseListener());
		final JTree _tree = tree;
		Runnable _execute = new Runnable() {
			public void run() {
				frame.setJTree(_tree);
				fechas();
				fillTable();
				mark();
				goCargar();
			}
		};
		this.invokeLater(_execute);

	}

	private void deseleccionarRaiz() {
		//System.out.println("Deseleccionar Raiz");
		DefaultTreeModel treeModel = (DefaultTreeModel) frame.getJTree()
				.getModel();
		CheckBoxNode raiz = null;
		try {
			raiz = (CheckBoxNode) treeModel.getRoot();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		raiz.setSelected(false);
	}

	public void seleccionarDesde(CheckBoxNode chk, boolean b) {
		int cantH;
		if (!b){
			if (!chk.isRoot()){
				this.deseleccionarRaiz();
			}
		}
		chk.setSelected(b);
		
		cantH = chk.getChildCount();
		for (int i = 0; i < cantH; i++) {
			CheckBoxNode hijo = (CheckBoxNode) chk.getChildAt(i);
			if (hijo != null) {
				//System.out.println("======hijo:" + (i + 1) + "/" + cantH + "");
				this.seleccionarDesde(hijo, b);
			}

		}
		DefaultTreeModel treeModel = (DefaultTreeModel) frame.getJTree()
				.getModel();
		treeModel.reload(chk);
	}

	public void evaluate_aviso_checkbox(JCheckBox chk) {

	}

	public void evaluate_checkbox(MyCheckBox chk) {
		JTree tree = null;
		if (chk.getParent() instanceof JTree) {
			tree = (JTree) chk.getParent();
			try {
				tree.stopEditing();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
			
		}

		if (tree != null) {
			try {

				CheckBoxNode nodo = (CheckBoxNode) tree.getSelectionPath()
						.getLastPathComponent();
				nodo.setSelected(chk.isSelected());
				if (!nodo.isRoot()){
					if (!chk.isSelected()){
						this.deseleccionarRaiz();
					}
				}
				//System.out.println("Nodo >" + nodo + " " + nodo.isSelected());
				for (int i = 0; i < nodo.getChildCount(); i++) {
					CheckBoxNode hijo = (CheckBoxNode) nodo.getChildAt(i);
					this.seleccionarDesde(hijo, chk.isSelected());
				}
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				// e1.printStackTrace();
			}
			//System.out.println("Check box tree selection "	+ tree.getSelectionPath() + " " + chk.isSelected() + " "+ chk.getIdclasificacion());
		}
	}

	
	/**
	 * fecha|idtransferencia|origen|destino
	 * 
	 * @param results
	 */

	private JScrollPane create_table_cell_item(Object[][] results, int row,
			int column, String chk_name, String cell_item_name, int width,
			int fontsize,boolean header,String name) {

		CustomTable table = new CustomTable();
		table.setSortable(false);
		Column col = null;
		TableSemanaColorCellRenderer renderer = new TableSemanaColorCellRenderer();
		renderer.setLogic(this);
		renderer.setX(column);
		renderer.setY(row);
		renderer.setTable(name);
		col = new Column();
		col.setName("");
		col.setWidth(20);
		col.setEditable(true);
		CheckBoxCellEditor chk = new CheckBoxCellEditor();
		chk.setName(chk_name);
		chk.setItemListener(this.getConstructor().getItemListener());
		col.setClass(Boolean.class);
		col.setCellEditor(chk.getCellCheck());
		
		table.addColumn(col);
		col = new Column();
		String _header="aviso";
		if (header){
				_header=""+this.getDayOnCalendar(row, column);
		}
		col.setName(_header);
		col.setWidth(width);
		col.setEditable(false);
		col.setCellRenderer(renderer);
		table.addColumn(col);
		table.setData(results);
		Font fuente = new Font("Dialog", Font.PLAIN, 10);
		table.setHeaderFont(fuente);

		table.setFont(fuente);
		table.setName(cell_item_name);
		table.build();
		table.fillData();
		JTable tx = table.getTable();
		tx.setRowSelectionAllowed(true);
		tx.setColumnSelectionAllowed(false);
		tx.addMouseListener(this.getConstructor().getMouseListener());
		if (!header){
			tx.setTableHeader(null);
			
		}else{
			JTableHeader theader = tx.getTableHeader();
		    theader.addMouseListener(this.getConstructor().getMouseListener());
		}
		
		tx.setDragEnabled(true);
		if (results==null){
			DefaultTableModel model=(DefaultTableModel)tx.getModel();
			model.setRowCount(0);
		}
		
		final JTable _tx = tx;
		dragSource = new _DragSource();
		dragSource.addDragSourceListener(this.getConstructor()
				.getDragSourceListener());
		_DragGestureListener dragGestureListener = new _DragGestureListener();
		dragGestureListener.setX(column);
		dragGestureListener.setY(row);
		dragGestureListener.setTable(tx);
		
		dragGestureListener.setTableName(name);
		dragGestureListener.setLogic(this);
		dragSource.createDefaultDragGestureRecognizer(_tx,
				DnDConstants.ACTION_COPY, dragGestureListener);
		JScrollPane scroll = new JScrollPane();
		scroll.addMouseListener(this.getConstructor().getMouseListener());
		scroll.setViewportView(_tx);
		scroll.setName(name);
		
		return scroll;
	}

	
	public void editarAvisoItem(Point P, int irow) {
		Point location = frame.getJTable_semanal().getLocationOnScreen();
		P.setLocation(P.x - location.x, P.y - location.y);
		int column = frame.getJTable_semanal().columnAtPoint(P);
		int row = frame.getJTable_semanal().rowAtPoint(P);

		List<String[]> avisos = (List<String[]>) agendas[row][column];
		String idaviso = "";
		if (avisos.size() > 0) {
			Object[] _aux = avisos.get(irow);
			idaviso = _aux[0].toString();

		}
		if (idaviso.compareTo("") != 0) {
			this.editarAviso(idaviso);
		} else {
			String fecha = this.getFecha(row, column);
			nuevoAviso(fecha);
		}

	}

	public void editarAvisoItemDia(Point P, int irow) {
		Point location = frame.getJTable().getLocationOnScreen();
		P.setLocation(P.x - location.x, P.y - location.y);
		String desde=frame.get_txt_agenda().getText();
		String inicio = this.getWeekStartDate(desde);
		int column = this.getDateDiference(inicio, desde);
		
		int row = frame.getJTable().rowAtPoint(P);

		List<String[]> avisos = (List<String[]>) agendas[row][column];
		String idaviso = "";
		if (avisos.size() > 0) {
			Object[] _aux = avisos.get(irow);
			idaviso = _aux[0].toString();

		}
		if (idaviso.compareTo("") != 0) {
			this.editarAviso(idaviso);
		} else {
			String fecha = this.getFecha(row, column);
			nuevoAviso(fecha);
		}
	}

	public void nuevoAvisoItemWeek(Point P) {
		Point location = frame.getJTable_semanal().getLocationOnScreen();
		P.setLocation(P.x - location.x, P.y - location.y);
		int column = frame.getJTable_semanal().columnAtPoint(P);
		int row = frame.getJTable_semanal().rowAtPoint(P);

		String fecha = this.getFecha(row, column);
		nuevoAviso(fecha);
		
	}
	public void nuevoAvisoItem(Point P) {
		if (frame.getJTabbedPane().getSelectedIndex() == 0) {
			this.nuevoAvisoItemDia(P);
		}
		if (frame.getJTabbedPane().getSelectedIndex() == 1) {
			this.nuevoAvisoItemWeek(P);
		}
		if (frame.getJTabbedPane().getSelectedIndex() == 2) {
			this.nuevoAvisoItemMonth(P);
		}
		
	}
	public void nuevoAvisoItemMonth(Point P) {
		Point location = frame.getJTable_mensual().getLocationOnScreen();
		P.setLocation(P.x - location.x, P.y - location.y);
		int column = frame.getJTable_mensual().columnAtPoint(P);
		int row = frame.getJTable_mensual().rowAtPoint(P);

		String fecha = this.getDateOnCalendar(row, column);
		String time=data.getSystemTime();
		fecha+=" "+time;
		nuevoAviso(fecha);
		
	}
	
	public void editarAvisoItemMonth(Point P, int irow) {
		Point location = frame.getJTable_mensual().getLocationOnScreen();
		P.setLocation(P.x - location.x, P.y - location.y);
		int column = frame.getJTable_mensual().columnAtPoint(P);
		int row = frame.getJTable_mensual().rowAtPoint(P);

		List<String[]> avisos = (List<String[]>) agendasM[row][column];
		String idaviso = "";
		if (avisos.size() > 0) {
			Object[] _aux = avisos.get(irow);
			idaviso = _aux[0].toString();

		}
		if (idaviso.compareTo("") != 0) {
			this.editarAviso(idaviso);
		} else {
			String fecha = this.getFecha(row, column);
			nuevoAviso(fecha);
		}
	}
	
	public void menuItemMonth(Point P, int irow) {
		Point location = frame.getJTable_mensual().getLocationOnScreen();
		P.setLocation(P.x - location.x, P.y - location.y);
		int column = frame.getJTable_mensual().columnAtPoint(P);
		int row = frame.getJTable_mensual().rowAtPoint(P);

		List<String[]> avisos = (List<String[]>) agendasM[row][column];
		String idaviso = "";
		if (avisos.size() > 0) {
			Object[] _aux = avisos.get(irow);
			idaviso = _aux[0].toString();

		}
		CustomPopup pop=this.create_interface(idaviso);
		
		frame.getJTable_mensual().add(pop);
		pop.show(frame.getJTable_mensual(), P.x, P.y);
	}

	public void menuItemWeek(Point P, int irow) {
		Point location = frame.getJTable_semanal().getLocationOnScreen();
		P.setLocation(P.x - location.x, P.y - location.y);
		int column = frame.getJTable_semanal().columnAtPoint(P);
		int row = frame.getJTable_semanal().rowAtPoint(P);

		List<String[]> avisos = (List<String[]>) agendas[row][column];
		String idaviso = "";
		if (avisos.size() > 0) {
			Object[] _aux = avisos.get(irow);
			idaviso = _aux[0].toString();

		}
		CustomPopup pop=this.create_interface(idaviso);
		pop.setIdaviso(idaviso);
		frame.getJTable_semanal().add(pop);
		pop.show(frame.getJTable_semanal(), P.x, P.y);
	}
	
	public void menuItemDia(Point P, int irow) {
		Point location = frame.getJTable().getLocationOnScreen();
		P.setLocation(P.x - location.x, P.y - location.y);
		String desde=frame.get_txt_agenda().getText();
		String inicio = this.getWeekStartDate(desde);
		int column = this.getDateDiference(inicio, desde);
		
		int row = frame.getJTable().rowAtPoint(P);

		List<String[]> avisos = (List<String[]>) agendas[row][column];
		String idaviso = "";
		if (avisos.size() > 0) {
			Object[] _aux = avisos.get(irow);
			idaviso = _aux[0].toString();

		}
		CustomPopup pop=this.create_interface(idaviso);
		
		frame.getJTable().add(pop);
		pop.show(frame.getJTable(), P.x, P.y);
	}
	
	
public void updateAvisoItemLeido(Point P, int irow, boolean leido) {
		String iduser = frame.get_txt_idvendedor().getText();
		Point location = frame.getJTable_semanal().getLocationOnScreen();
		P.setLocation(P.x - location.x, P.y - location.y);
		int column = frame.getJTable_semanal().columnAtPoint(P);
		int row = frame.getJTable_semanal().rowAtPoint(P);

		List<String[]> avisos = (List<String[]>) agendas[row][column];
		String idaviso = "";
		if (avisos.size() > 0) {
			Object[] _aux = avisos.get(irow);
			idaviso = _aux[0].toString();

		}

		String q = data.getUpdateLeido(idaviso, iduser, leido);

		if (idaviso.compareTo("") != 0) {
			data.clearBatch();
			//System.out.println(q);
			data.addBatch(q);
			data.executeBatch();
			
			this.goCargar();
		}

	}

public void updateAvisoItemLeidoMensual(Point P, int irow, boolean leido) {
		String iduser = frame.get_txt_idvendedor().getText();
		Point location = frame.getJTable_mensual().getLocationOnScreen();
		P.setLocation(P.x - location.x, P.y - location.y);
		int column = frame.getJTable_mensual().columnAtPoint(P);
		int row = frame.getJTable_mensual().rowAtPoint(P);

		List<String[]> avisos = (List<String[]>) agendasM[row][column];
		String idaviso = "";
		if (avisos.size() > 0) {
			Object[] _aux = avisos.get(irow);
			idaviso = _aux[0].toString();

		}

		String q = data.getUpdateLeido(idaviso, iduser, leido);

		if (idaviso.compareTo("") != 0) {
			data.clearBatch();
			//System.out.println(q);
			data.addBatch(q);
			data.executeBatch();
			
			this.goCargar();
		}

	}

	
public void updateAvisoItemLeidoDay(Point P, int irow, boolean leido) {
		String iduser = frame.get_txt_idvendedor().getText();
		Point location = frame.getJTable().getLocationOnScreen();
		P.setLocation(P.x - location.x, P.y - location.y);
		
		String desde=frame.get_txt_agenda().getText();
		String inicio = this.getWeekStartDate(desde);
		int column = this.getDateDiference(inicio, desde);
		int row = frame.getJTable().rowAtPoint(P);

		List<String[]> avisos = (List<String[]>) agendas[row][column];
		String idaviso = "";
		if (avisos.size() > 0) {
			Object[] _aux = avisos.get(irow);
			idaviso = _aux[0].toString();

		}

		String q = data.getUpdateLeido(idaviso, iduser, leido);

		if (idaviso.compareTo("") != 0) {
			data.clearBatch();
			//System.out.println(q);
			data.addBatch(q);
			data.executeBatch();
			this.goCargar();
		}

	}


public void drop(Point P) {
		if (frame.getJTabbedPane().getSelectedIndex() == 0) {
			Point location = frame.getJTable().getLocationOnScreen();
			P.setLocation(P.x - location.x, P.y - location.y);
			this.dropDia(P);
		}
		if (frame.getJTabbedPane().getSelectedIndex() == 1) {
			Point location = frame.getJTable_semanal().getLocationOnScreen();
			P.setLocation(P.x - location.x, P.y - location.y);
			this.dropSemana(P);
		}
		if (frame.getJTabbedPane().getSelectedIndex() == 2) {
			Point location = frame.getJTable_mensual().getLocationOnScreen();
			P.setLocation(P.x - location.x, P.y - location.y);
			this.dropMonth(P);
		}
	}

public void dropSemana(Point P) {
		JTable table = frame.getJTable_semanal();
		JViewport view = (JViewport) table.getParent();
		JScrollPane scroll = (JScrollPane) view.getParent();
		JScrollBar verticalScrollBar = scroll.getVerticalScrollBar();
		JScrollBar horizontalScrollBar = scroll.getHorizontalScrollBar();
		scrollheight = verticalScrollBar.getValue();
		scrollwidth = horizontalScrollBar.getValue();
		Point location = table.getLocationOnScreen();
		int x, y;

		P.setLocation(P.x + location.x, P.y + location.y);
		int column = table.columnAtPoint(P);
		int row = table.rowAtPoint(P);
		String nueva_fecha = this.getFecha(row, column);
		String iduser = frame.get_txt_idvendedor().getText();
		// aviso("Drop "+P+" "+row+":"+column+" Transferir:");
		if(avisosDragged!=null){
			if (avisosDragged.size() > 0) {
				data.beginTransaction();
				data.clearBatch();
				for (int i = 0; i < avisosDragged.size(); i++) {
					String idaviso = avisosDragged.get(i);
					String q = data.getUpdate(idaviso, iduser, nueva_fecha);
					data.addBatch(q);
					// aviso("Drop Aviso: "+idaviso+" a nueva fecha "+nueva_fecha);
				}
				avisosDragged = new ArrayList<String>();
				boolean error = data.executeBatch();
				if (!error) {
					data.commitTransaction();
					this.goCargar();
				} else {
					data.rollbackTransaction();
				}
			}
	
		}
		
	}

public String _getTime2(int row, int col) {
		
		String formato = "dd-MM-yyyy";
		
		int mes = frame.get_lst_mes().getSelectedIndex();
		int anio = new Integer(frame.get_lst_anio().getSelectedItem().toString());
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, mes);
		cal.set(Calendar.YEAR, anio);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		int ini=cal.get(Calendar.DAY_OF_WEEK);
		int max=7;
		int value=1+max*row+(col+1)-ini;
		int maximun=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		int minimun=cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		
		if (value>maximun){
			value=value-maximun;
			cal.add(Calendar.MONTH, 1);
			cal.set(Calendar.DAY_OF_MONTH,value);
		}else{
			if (value<minimun){
				cal.add(Calendar.MONTH, -1);
				maximun=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
				value=maximun+value;
				cal.set(Calendar.DAY_OF_MONTH,value);
			}else{
				cal.set(Calendar.DAY_OF_MONTH,value);
			}
		}
		Locale locale = Locale.getDefault();
		String s1 = "";
		Date date = null;
		DateFormat formatter;
		try {
			date = cal.getTime();

			formatter = new SimpleDateFormat(formato, locale);
			s1 = formatter.format(date);
		} catch (Exception e) {

		}
		//System.out.println("Valor del Calendario en "+row+":"+col+"= "+s1+" maxi="+maximun+" min="+minimun+" mes="+mes);
		return s1;
	}

	public boolean isToday(int row, int col) {
		boolean ok=false;
		String fecha=this._getTime2(row, col);
		String fechasistema=data.getSystemDate();
		
		ok=fecha.compareTo(fechasistema)==0;
		return ok;
	}
	
	public boolean isFinde(int row, int col) {
		boolean ok=false;
		ok=(col==0|col==6);
		return ok;
	}
	
	public boolean isSelected(int row, int col) {
		boolean ok=false;
		ok=frame.getJTable2().getSelectedRow()==row && frame.getJTable2().getSelectedColumn()==col; 
		return ok;
	}
	
	public boolean isSelectedWeek(int row, int col) {
		boolean ok=false;
		String fecha = this.getFechaWeek(row, col);
		String fechaagenda=frame.get_txt_agenda().getText();
		ok=fecha.compareTo(fechaagenda)==0;
		return ok;
	}
	

public boolean isOtherMonthWeek(int row, int col) {
	String fecha = this.getFechaWeek(row, col);
	Date time=new Convertidor().getDateWithFormat2("dd-MM-yyyy", fecha);
		
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		
		
		int value=cal.get(Calendar.DAY_OF_MONTH);
		int maximun=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		int minimun=cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		boolean other=false;
		if (value>maximun){
			other=true;
		}else{
			if (value<minimun){
				other=true;
			}else{
				cal.set(Calendar.DAY_OF_MONTH,value);
			}
		}

		return other;
	}


public boolean isTodayWeek(int row, int col) {
	String fecha = this.getFechaWeek(row, col);
	String fechasistema=data.getSystemDate();
	boolean ok=fecha.compareTo(fechasistema)==0;
	return ok;
	}

public boolean isOtherMonth(int row, int col) {
	boolean other=false;
	String formato = "dd-MM-yyyy";
	//
	int mes = frame.get_lst_mes().getSelectedIndex();
	int anio = new Integer(frame.get_lst_anio().getSelectedItem().toString());
	
	Calendar cal = Calendar.getInstance();
	cal.set(Calendar.MONTH, mes);
	cal.set(Calendar.YEAR, anio);
	cal.set(Calendar.DAY_OF_MONTH, 1);
	int ini=cal.get(Calendar.DAY_OF_WEEK);
	int max=7;
	int value=1+max*row+(col+1)-ini;
	int maximun=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	int minimun=cal.getActualMinimum(Calendar.DAY_OF_MONTH);
	
	if (value>maximun){
		other=true;
	}else{
		if (value<minimun){
			other=true;
		}else{
			cal.set(Calendar.DAY_OF_MONTH,value);
		}
	}

	return other;
}


	public void dropMonth(Point P) {
		JTable table = frame.getJTable_mensual();
		JViewport view = (JViewport) table.getParent();
		JScrollPane scroll = (JScrollPane) view.getParent();
		JScrollBar verticalScrollBar = scroll.getVerticalScrollBar();
		JScrollBar horizontalScrollBar = scroll.getHorizontalScrollBar();
		scrollheight_M = verticalScrollBar.getValue();
		scrollwidth_M = horizontalScrollBar.getValue();
		Point location = table.getLocationOnScreen();
		int x, y;

		P.setLocation(P.x + location.x, P.y + location.y);
		int column = table.columnAtPoint(P);
		int row = table.rowAtPoint(P);
		
		
		String iduser = frame.get_txt_idvendedor().getText();
		// aviso("Drop "+P+" "+row+":"+column+" Transferir:");
		if (avisosDragged.size() > 0) {
			data.beginTransaction();
			data.clearBatch();
			for (int i = 0; i < avisosDragged.size(); i++) {
				String nueva_fecha = this.getDateOnCalendar(row, column);
				String idaviso = avisosDragged.get(i);
				String hora=data.getAvisoHora(idaviso, iduser);
				nueva_fecha=nueva_fecha+" "+hora;
				String q = data.getUpdate(idaviso, iduser, nueva_fecha);
				data.addBatch(q);
				// aviso("Drop Aviso: "+idaviso+" a nueva fecha "+nueva_fecha);
			}
			avisosDragged = new ArrayList<String>();
			boolean error = data.executeBatch();
			if (!error) {
				data.commitTransaction();
				this.goCargar();
			} else {
				data.rollbackTransaction();
			}
		}

	}

	public void dropDia(Point P) {

		JTable table = frame.getJTable();
		JViewport view = (JViewport) table.getParent();
		JScrollPane scroll = (JScrollPane) view.getParent();
		JScrollBar verticalScrollBar = scroll.getVerticalScrollBar();
		JScrollBar horizontalScrollBar = scroll.getHorizontalScrollBar();
		scrollheight_D = verticalScrollBar.getValue();
		scrollwidth_D = horizontalScrollBar.getValue();
		Point location = table.getLocationOnScreen();
		int x, y;

		P.setLocation(P.x + location.x, P.y + location.y);

		int row = table.rowAtPoint(P);
		String nueva_fecha = this.getFecha(row);
		String iduser = frame.get_txt_idvendedor().getText();
		// aviso("Drop "+P+" "+row+":"+column+" Transferir:");
		if (avisosDragged.size() > 0) {
			data.beginTransaction();
			data.clearBatch();
			for (int i = 0; i < avisosDragged.size(); i++) {
				String idaviso = avisosDragged.get(i);
				String q = data.getUpdate(idaviso, iduser, nueva_fecha);
				data.addBatch(q);
				// aviso("Drop Aviso: "+idaviso+" a nueva fecha "+nueva_fecha);
			}
			avisosDragged = new ArrayList<String>();
			boolean error = data.executeBatch();
			if (!error) {
				data.commitTransaction();
				this.goCargar();
			} else {
				data.rollbackTransaction();
			}
		}

	}

	public void showCell(JTable table, int row, int column) {
		Rectangle rect = table.getCellRect(row, column, true);
		table.scrollRectToVisible(rect);
		table.clearSelection();
		table.setRowSelectionInterval(row, row);
		try {
			((DefaultTableModel) table.getModel()).fireTableDataChanged();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addAvisos(int row, int col, int[] rows,String table) {
		if (this.avisosDragged == null) {
			avisosDragged = new ArrayList<String>();
		}
		List<String[]> avisos =null;
		if (table==_Interface._table_mensual_item){
			avisos=(List<String[]>) agendasM[row][col];
		}else{
			avisos=(List<String[]>) agendas[row][col];	
		}
		 
		for (int i = 0; i < rows.length; i++) {
			//System.out.println("");
			//System.out.println("");
			//System.out.println("");
			
			Object[] _aux = avisos.get(rows[i]);
			
			String idaviso = _aux[0].toString();
			
			this.avisosDragged.add(idaviso);
		}
	}

	public String getFecha(Point P, JTable table) {
		Point location = table.getLocationOnScreen();
		P.setLocation(P.x - location.x, P.y - location.y);
		int column = table.columnAtPoint(P);
		int row = table.rowAtPoint(P);
		String fecha = this.getFecha(row, column);
		return fecha;
	}

	public String getFechaDia(Point P, JTable table) {
		Point location = table.getLocationOnScreen();
		P.setLocation(P.x - location.x, P.y - location.y);

		int row = table.rowAtPoint(P);
		String fecha = this.getFecha(row);
		return fecha;
	}

	public void editarAvisoItem(Point P) {

		String fecha = this.getFecha(P, frame.getJTable_semanal());
		nuevoAviso(fecha);
	}
	
	public void editarAvisoItemMensual(Point P) {

		String fecha = this.getFecha(P, frame.getJTable_mensual());
		nuevoAviso(fecha);
	}

	public void nuevoAvisoItemDia(Point P) {

		String fecha = this.getFechaDia(P, frame.getJTable());
		nuevoAviso(fecha);
	}

	public String getFecha(int row, int column) {
		String fecha = "";
		fecha = frame.get_txt_agenda().getText();
		String hora = "" + row;
		if (hora.length() < 2) {
			hora = "0" + hora;
		}
		hora += ":00:00";

		fecha = this.getWeekStartDate(fecha);
		fecha += " " + hora;
		fecha = this.getRoll(fecha, 0, column, 0, 0);

		return fecha;
	}

	public String getFechaWeek(int row, int column) {
		String fecha = "";
		fecha = frame.get_txt_agenda().getText();
		fecha = this.getWeekStartDate(fecha);
		
		fecha = this.getRollDate(fecha, 0, column);

		return fecha;
	}	
	public String getFecha(int row) {
		String fecha = "";
		fecha = frame.get_txt_agenda().getText();
		String hora = "" + row;
		if (hora.length() < 2) {
			hora = "0" + hora;
		}
		hora += ":00:00";
		fecha += " " + hora;
		return fecha;
	}

	aplicacion.gestion.agenda.escritor.constructor._Constructor nuevoc = null;

	public void nuevoAviso(String fecha) {
		if (nuevoc != null) {
			nuevoc.dispose();
		}
		String idvendedor = frame.get_txt_idvendedor().getText();
		nuevoc = new aplicacion.gestion.agenda.escritor.constructor._Constructor();
		nuevoc.setParameter(_parametros.connector, this._data
				.getConnectionHandler().Clone());
		nuevoc.setParameter(_parametros.LookAndFeel, this.getConstructor()
				.getLookAndFeelTheme());
		if (fecha.compareTo("") != 0) {
			nuevoc
					.setParameter(
							aplicacion.gestion.agenda.escritor.interfaces._Parametros.fecha,
							fecha);
		}

		nuevoc
				.setParameter(
						aplicacion.gestion.agenda.escritor.interfaces._Parametros.idvendedor,
						
						idvendedor);
		nuevoc
		.setParameter(
				aplicacion.gestion.agenda.escritor.interfaces._Parametros.gestion,
				this.getConstructor());
		nuevoc.build(this.getConstructor());
		
		nuevoc.init();

	}

	public void initialize_dnd() {
		try {
			urlFlavor = new DataFlavor(
					"application/x-java-url; class=java.net.URL");
			uriListFlavor = new DataFlavor(
					"text/uri-list; class=java.lang.String");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}

	}

	public boolean getLeidoAvisoSemana(JTable table, int row, int master_row,
			int master_column) {
		boolean leido = false;
		try {

			List<Object[]> avisos = (List<Object[]>) agendas[master_row][master_column];
			if (avisos.size() > 0) {

				Object[] _aux = avisos.get(row);
				leido = (Boolean) _aux[3];
				if (leido) {
					//System.out.println("LEido " + _aux[0].toString() + " "+ _aux[1].toString());
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return leido;
	}

	public String getColorAvisoSemana(JTable table, int row, int master_row,
			int master_column) {
		String color = "";
		try {

			List<Object[]> avisos = (List<Object[]>) agendas[master_row][master_column];
			if (avisos.size() > 0) {

				Object[] _aux = avisos.get(row);
				color = this.getcolor(_aux[2].toString());

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return color;
	}

	public String getColorAvisoSemanaMonth(JTable table, int row, int master_row,
			int master_column) {
		String color = "";
		try {

			List<Object[]> avisos = (List<Object[]>) agendasM[master_row][master_column];
			if (avisos.size() > 0) {
				Object[] _aux = avisos.get(row);
				color = this.getcolor(_aux[2].toString());
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return color;
	}
	
	public String getToolTipAvisoSemanaMonth(JTable table, int row, int master_row,
			int master_column) {
		String titulo = "";
		
		try {

			List<Object[]> avisos = (List<Object[]>) agendasM[master_row][master_column];
			if (avisos.size() > 0) {

				Object[] _aux = avisos.get(row);
				titulo = _aux[1].toString();

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		}
		return titulo;
	}
	
	public String getToolTipAvisoSemana(JTable table, int row, int master_row,
			int master_column) {
		String titulo = "";
		try {

			List<Object[]> avisos = (List<Object[]>) agendas[master_row][master_column];
			if (avisos.size() > 0) {

				Object[] _aux = avisos.get(row);
				titulo = _aux[1].toString();

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return titulo;
	}

	/**
	 * fecha|idtransferencia|origen|destino
	 * 
	 * @param results
	 */
	private void create_table_agenda_semanal(Object[][] results, String desde) {
		// JTableCellRenderer table_renderer = new JTableCellRenderer();
		// table_renderer.setLogic(this);
		// JTableCellEditor editor = new JTableCellEditor();

		JScrollPaneCellRenderer table_renderer = new JScrollPaneCellRenderer();
		table_renderer.setLogic(this);
		JScrollPaneCellditor editor = new JScrollPaneCellditor();

		CustomTable table = new CustomTable();
		table.setSortable(false);
		Column col = null;
		String[] dias = new String[] { "Hora", "Domingo", "Lunes", "Martes",
				"Miercoles", "Jueves", "Viernes", "Sabado" };
		for (int i = 1; i < dias.length; i++) {
			dias[i] += " " + this.getDayHeader(desde, i - 1);
		}
		;

		col = new Column();
		col.setName("");
		col.setWidth(40);
		col.setClass(String.class);
		col.setEditable(false);
		col.setAligment(JTextField.CENTER);
		table.addColumn(col);
		int w = frame.getJTabbedPane().getWidth();
		w = w - 80;
		int width = new Double(new Double(w) / new Double(dias.length - 1))
				.intValue();
			
		for (int i = 1; i < dias.length; i++) {
			col = new Column();
			col.setName(dias[i]);
			col.setWidth(width);
			col.setCellRenderer(table_renderer);
			col.setEditable(true);
			table.addColumn(col);
		}

		table.setData(results);
		table.addKeyListener(this.getConstructor().getKeyListener());
		table.addMouseListener(this.getConstructor().getMouseListener());
		Font fuente = new Font("Dialog", Font.BOLD, 10);
		table.setHeaderFont(fuente);
		fuente = new Font("Dialog", Font.PLAIN, 10);
		table.setFont(fuente);
		table.setName(_Interface._table_semanal);
		table.build();
		table.fillData();

		JTable _table = table.getTable();
		_table.setDragEnabled(true);
		_table.setColumnSelectionAllowed(true);
		_table.setRowSelectionAllowed(true);

		for (int i = 0; i < dias.length; i++) {
			TableColumn colx = _table.getColumnModel().getColumn(i);
			colx.setCellEditor(editor);
		}

		for (int i = 0; i < results.length; i++) {
			int j = 1;
			int max = 21;
			while (j < agendas[0].length) {
				List<String[]> avisos = (List<String[]>) agendas[i][j];
				int maxv = 10 + avisos.size() * 20;
				if (maxv > max) {
					max = maxv;
				}

				j++;
			}

			_table.setRowHeight(i, max);
		}

		_table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		for (int i = 0; i < results.length; i++) {
			int j = 1;
			int max = 21;
			while (j < agendas[0].length) {
				List<String[]> avisos = (List<String[]>) agendas[i][j];
				if (avisos.size() <= 0) {
					_table.setValueAt(null, i, j);
				}
				j++;
			}

		}
		frame.setJTable_semanal(_table);
		dropTarget = new DropTarget(this.frame.getJTable_semanal(), this
				.getConstructor().getDropTargetListener());
		JViewport view = (JViewport) frame.getJTable_semanal().getParent();
		JScrollPane scroll = (JScrollPane) view.getParent();
		JScrollBar verticalScrollBar = scroll.getVerticalScrollBar();
		JScrollBar horizontalScrollBar = scroll.getHorizontalScrollBar();
		verticalScrollBar.setValue(scrollheight);
		horizontalScrollBar.setValue(scrollwidth);
	}

	/**
	 * fecha|idtransferencia|origen|destino
	 * 
	 * @param results
	 */
	private void create_table_agenda_mensual(Object[][] results, String desde) {
		// JTableCellRenderer table_renderer = new JTableCellRenderer();
		// table_renderer.setLogic(this);
		// JTableCellEditor editor = new JTableCellEditor();
		
		JScrollPaneCellRenderer table_renderer = new JScrollPaneCellRenderer();
		table_renderer.setLogic(this);
		

		CustomTable table = new CustomTable();
		table.setSortable(false);
		Column col = null;
		String[] dias = new String[] { "Domingo", "Lunes", "Martes",
				"Miercoles", "Jueves", "Viernes", "Sabado" };
		
		col = new Column();
		
		int w = frame.getJTabbedPane().getWidth();
		int width = new Double(new Double(w) / new Double(dias.length)).intValue();
		
		
		for (int i = 0; i < dias.length; i++) {
			col = new Column();
			col.setName(dias[i]);
			col.setWidth(width);
			
			col.setCellRenderer(table_renderer);
			col.setEditable(true);
			table.addColumn(col);
		}

		table.setData(results);
		table.addKeyListener(this.getConstructor().getKeyListener());
		table.addMouseListener(this.getConstructor().getMouseListener());
		Font fuente = new Font("Dialog", Font.BOLD, 12);
		table.setHeaderFont(fuente);
		fuente = new Font("Dialog", Font.BOLD, 12);
		table.setFont(fuente);
		table.setName(_Interface._table_mensual);
		table.build();
		table.fillData();

		JTable _table = table.getTable();
		_table.setDragEnabled(true);
		_table.setColumnSelectionAllowed(true);
		_table.setRowSelectionAllowed(true);
		JScrollPaneCellditor editor=new JScrollPaneCellditor();
		editor.setLogic(this);
		for (int i = 0; i < dias.length; i++) {
			TableColumn colx = _table.getColumnModel().getColumn(i);
			colx.setCellEditor(editor);
		}
		
		int h = frame.getJTabbedPane().getHeight()-40;
		int max = new Double(new Double(h) / new Double(results.length)).intValue();
		max=max-4;
		for (int i = 0; i < results.length; i++) {
			/*int j = 0;
			int max = frame.getJTabbedPane().getHeight()-20;
			
			while (j < agendasM[0].length) {
				List<String[]> avisos = (List<String[]>) agendasM[i][j];
				int maxv = 10 + avisos.size() * 20;
				if (maxv > max) {
					max = maxv;
				}

				j++;
			}*/

			_table.setRowHeight(i, max);
		}

		_table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		frame.setJTable_mensual(_table);
		dropTarget_M = new DropTarget(this.frame.getJTable_mensual(), this
				.getConstructor().getDropTargetListener());
		JViewport view = (JViewport) frame.getJTable_mensual().getParent();
		JScrollPane scroll = (JScrollPane) view.getParent();
		JScrollBar verticalScrollBar = scroll.getVerticalScrollBar();
		JScrollBar horizontalScrollBar = scroll.getHorizontalScrollBar();
		verticalScrollBar.setValue(scrollheight_M);
		horizontalScrollBar.setValue(scrollwidth_M);
	}

	private void create_table_agenda_diario(Object[][] results, String desde) {
		// JTableCellRenderer table_renderer = new JTableCellRenderer();
		// table_renderer.setLogic(this);
		// JTableCellEditor editor = new JTableCellEditor();
		String inicio = this.getWeekStartDate(desde);
		int index = this.getDateDiference(inicio, desde);
		JScrollPaneCellRenderer table_renderer = new JScrollPaneCellRenderer();
		table_renderer.setLogic(this);
		
		JScrollPaneCellditor editor = new JScrollPaneCellditor();

		CustomTable table = new CustomTable();
		table.setSortable(false);
		Column col = null;
		String[] dia= new String[] { "Domingo", "Lunes", "Martes",
				"Miercoles", "Jueves", "Viernes", "Sabado" };
		String[] dias = new String[] { "Hora", dia[index-1] };
		for (int i = 1; i < dias.length; i++) {
			dias[i] += " " + this.getDayHeader(inicio, index-1);
		}
		;

		col = new Column();
		col.setName("");
		col.setWidth(20);
		col.setClass(String.class);
		col.setEditable(false);
		col.setAligment(JTextField.CENTER);
		table.addColumn(col);

		int w = frame.getJTabbedPane().getWidth();
		w = w - 70;
		for (int i = 1; i < dias.length; i++) {
			col = new Column();
			col.setName(dias[i]);
			col.setWidth(w);
			col.setCellRenderer(table_renderer);
			col.setEditable(true);
			table.addColumn(col);
		}

		table.setData(results);
		table.addKeyListener(this.getConstructor().getKeyListener());
		table.addMouseListener(this.getConstructor().getMouseListener());
		Font fuente = new Font("Dialog", Font.BOLD, 10);
		table.setHeaderFont(fuente);
		fuente = new Font("Dialog", Font.PLAIN, 10);
		table.setFont(fuente);
		table.setName(_Interface._table_diario);
		table.build();
		table.fillData();

		JTable _table = table.getTable();
		_table.setDragEnabled(true);
		_table.setColumnSelectionAllowed(true);
		_table.setRowSelectionAllowed(true);

		for (int i = 0; i < dias.length; i++) {
			TableColumn colx = _table.getColumnModel().getColumn(i);
			colx.setCellEditor(new JScrollPaneCellditor());
		}
		
		for (int i = 0; i < results.length; i++) {
			int j = index;
			int max = 21;
			List<String[]> avisos = (List<String[]>) agendas[i][j];
			int maxv = 10 + avisos.size() * 20;
			if (maxv > max) {
				max = maxv;
			}
			_table.setRowHeight(i, max);
		}

		_table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		for (int i = 0; i < results.length; i++) {
			int j = 1;
			int max = 21;
			List<String[]> avisos = (List<String[]>) agendas[i][index];
			if (avisos.size() <= 0) {
				_table.setValueAt(null, i, j);
			}
		}
		frame.setJTable(_table);
		dropTarget_D = new DropTarget(this.frame.getJTable(), this
				.getConstructor().getDropTargetListener());
		JViewport view = (JViewport) frame.getJTable().getParent();
		JScrollPane scroll = (JScrollPane) view.getParent();
		JScrollBar verticalScrollBar = scroll.getVerticalScrollBar();
		JScrollBar horizontalScrollBar = scroll.getHorizontalScrollBar();
		verticalScrollBar.setValue(scrollheight_D);
		horizontalScrollBar.setValue(scrollwidth_D);
	}

	public void _leerAviso(String idaviso, String iduser) {
		aplicacion.gestion.agenda.lector.constructor._Constructor CC = new aplicacion.gestion.agenda.lector.constructor._Constructor();
		CC.setParameter(_parametros.connector, this._data
				.getConnectionHandler().Clone());
		CC.setParameter(_parametros.LookAndFeel, this.getConstructor()
				.getLookAndFeelTheme());
		CC.build(this.getConstructor());
		CC.init();
		aplicacion.gestion.agenda.lector.logic._Logic logic = (aplicacion.gestion.agenda.lector.logic._Logic) CC
				.getLogic();
		logic.setGestion((aplicacion.gestion.agenda.gestion.constructor._Constructor)this.getConstructor());
		logic.cargarAviso(idaviso, iduser);
	}

	public double getControlCalendar(int row, int col) {
		double perc = 0.0;

		if (frame.getJTable2() != null) {
			
			String day = "";
			try {
				day = frame.getJTable2().getValueAt(row, col).toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}

			if (day.compareTo("") != 0) {
				if (colors != null) {
					boolean found=false;
					int i = 0;
					while (!found& i < colors.length){
						String tmp = colors[i][0].toString();
						found=(tmp.compareTo(day) == 0);
						if (found){perc = (Double) colors[i][1];}
						else{
							i++;
						}
					}
				

				}

			}
		}else{
			//System.out.println("JTable2 NULL for color render dia!");
		}
		

		return perc;
	}

	public void load_colors() {
		int mes = frame.get_lst_mes().getSelectedIndex() + 1;
		int anio = new Integer(frame.get_lst_anio().getSelectedItem()
				.toString());
		String desde = "";
		String hasta = "";
		int days = this.get_days();
		String _mes=""+mes;
		if (_mes.length()<2){
			_mes="0"+mes;
		}
		desde = "01-" + _mes + "-" + anio;
		hasta = days + "-" + _mes + "-" + anio;
		String iduser = frame.get_txt_idvendedor().getText();
		//System.out.println("pidiendo fechas " + desde + " <> " + hasta);
		List<String> clasificacion = this.getDestinatarios();
		if (clasificacion.size() > 0) {
			colors = this.getControl(desde, hasta, iduser);
		} else {
			colors = null;
			
		}
		

		frame.getJTable2().repaint();
	}

	private Object[][] getControl(String desde, String hasta, String iduser) {
		int valor = 84;
		Object[][] cargas = null;
		String idhost = data.getHost();
		List<String> idcategoria = this.getDestinatarios();
		String q = data.getAgendaColorQuery(iduser, idhost, desde, hasta,
				idcategoria);
		Object[][] results = data.getResults(q);
		
		if (results != null) {
			if (results.length > 0) {
				cargas = new Object[results.length][2];
				for (int i = 0; i < results.length; i++) {
					String _carga = (String) results[i][1];
					String dia = (String) results[i][0];
					double carga = 0.0;
					try {
						carga = new Double(_carga);
					} catch (Exception e) {

					}

					if (carga > 255) {
						carga = 255;

					} else {
						if (carga < valor) {
							if (carga > 0) {
								carga = valor;
							}
						}
					}
					cargas[i][1] = carga;
					cargas[i][0] = dia;
				}

			}
		}

		return cargas;
	}

	private void create_table(Object[][] results) {

		CustomTable table = new CustomTable();

		Column col = new Column();

		String[] dias = new String[] { "Dom", "Lun", "Mar", "Mie", "Jue",
				"Vie", "Sab",
		};
		TableDayColorCellRenderer renderer = new TableDayColorCellRenderer();
		renderer.setLogic(this);
		for (int i = 0; i < dias.length; i++) {
			col = new Column();
			col.setName(dias[i]);
			col.setWidth(25);
			col.setAligment(JTextField.CENTER);
			col.setEditable(false);

			col.setCellRenderer(renderer);
			table.addColumn(col);

		}

		table.setSortable(false);
		
		table.setData(results);
		table.setName(_Interface._table_dias);
		table.addKeyListener(this._constructor.getKeyListener());
		table.addMouseListener(this._constructor.getMouseListener());
		Font fuente = new Font("Dialog", Font.PLAIN, 8);
		table.setHeaderFont(fuente);

		table.setFont(fuente);
		table.build();
		table.fillData();

		JTable _table = table.getTable();
		frame.setJTable2(_table);

	}

	
	
	public void fillTable() {
		int rows = 6;
		int days = 7;
		Object[][] tmp = new Object[rows][days];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < days ; j++) {
				tmp[i][j] = this.getDayOnCalendar(i, j);
			}
		}
		this.create_table(tmp);


		
		mark(Calendar.getInstance());
	}
	
	public int getDayOnCalendar(int row,int col){
		
		String formato = "dd-MM-yyyy";
		int mes = frame.get_lst_mes().getSelectedIndex();
		int anio = new Integer(frame.get_lst_anio().getSelectedItem().toString());
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, mes);
		cal.set(Calendar.YEAR, anio);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		int ini=cal.get(Calendar.DAY_OF_WEEK);
		int max=7;
		int value=1+max*row+(col+1)-ini;
		int maximun=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		int minimun=cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		
		if (value>maximun){
			value=value-maximun;
			cal.add(Calendar.MONTH, 1);
			cal.set(Calendar.DAY_OF_MONTH,value);
		}else{
			if (value<minimun){
				cal.add(Calendar.MONTH, -1);
				maximun=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
				value=maximun+value;
				cal.set(Calendar.DAY_OF_MONTH,value);
			}else{
				cal.set(Calendar.DAY_OF_MONTH,value);
			}
		}
		return value;
	}

	public String getDateOnCalendar(int row,int col){
		
		String desde = this._getTime2(row, col);
		
		
		return desde;
	}
	/** 
	 * Metodo para determinar la fila de un dia en el calendario
	 * @param fecha
	 * @return
	 */
	public int getRowOnCalendar(String fecha){
		
		String inicio=this.getDateOnCalendar(0, 0);
		int finish=this.getDateDiference(inicio, fecha);
		
		
		int ini = 0;
		System.out.println("rown on calendar:"+fecha+" inicio:"+inicio+" finish:"+finish+" ini:"+ini);
		int max = 6;
		int cellx = ini;
		int celly = 0;
		int day=0;
		while (day<finish){
			if (day<finish){
				if (cellx >= max) {
					cellx = 0;
					celly++;
				} else {
					cellx++;
				}
				day++;	
			}
		}
		//System.out.println("Get Row of "+fecha+" =dia:"+finish+" =Row "+celly+" ini="+ini);
		return celly;
	}

	/** 
	 * Metodo para determinar la columna de un dia en el calendario
	 * @param fecha
	 * @return
	 */
	public int getColumnOnCalendar(String fecha){
		
		
		String inicio=this.getDateOnCalendar(0, 0);
		int finish=this.getDateDiference(inicio, fecha);
		int ini = get_day_week(inicio);// domingo

		int max = 6;
		int cellx = 0;
		int celly = 0;
		int day=0;
		while (day<finish){
				if (cellx >= max) {
					cellx = 0;
					celly++;
				} else {
					cellx++;
				}
				day++;	
			
		}
		//System.out.println("Get Column of "+fecha+" =dia:"+finish+" =Columna "+cellx+" ini="+ini);
		return cellx;
	}
	
	
		public int get_days() {
		String s_year = frame.get_lst_anio().getSelectedItem().toString();
		int year = new Integer(s_year);
		int month = 0;
		switch (frame.get_lst_mes().getSelectedIndex()) {
		case 0:
			month = Calendar.JANUARY;
			break;
		case 1:
			month = Calendar.FEBRUARY;
			break;
		case 2:
			month = Calendar.MARCH;
			break;
		case 3:
			month = Calendar.APRIL;
			break;
		case 4:
			month = Calendar.MAY;
			break;
		case 5:
			month = Calendar.JUNE;
			break;
		case 6:
			month = Calendar.JULY;
			break;
		case 7:
			month = Calendar.AUGUST;
			break;
		case 8:
			month = Calendar.SEPTEMBER;
			break;
		case 9:
			month = Calendar.OCTOBER;
			break;
		case 10:
			month = Calendar.NOVEMBER;
			break;
		case 11:
			month = Calendar.DECEMBER;
			break;
		}

		Calendar cal = new GregorianCalendar(year, month, 1);
		int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		// days++;
		// //System.out.println("dias del mes "+days);
		return days;
	}
	public int get_day_week(String fecha) {
		int col=0;
		String formato = "dd-MM-yyyy";
		Date today = new Convertidor().getDateWithFormat(formato, formato,
				fecha);
		java.util.GregorianCalendar _date = new java.util.GregorianCalendar();
		_date.setTime(today);
		_date.set(Calendar.DAY_OF_MONTH, 1);
		
		col= _date.get(Calendar.DAY_OF_WEEK) - 1;
		System.out.println("Get day week para "+fecha+" >"+col);
		return col;
	}
	public int get_day_week() {
		String s_year = frame.get_lst_anio().getSelectedItem().toString();
		int year = new Integer(s_year);
		int month = 0;
		switch (frame.get_lst_mes().getSelectedIndex()) {
		case 0:
			month = Calendar.JANUARY;
			break;
		case 1:
			month = Calendar.FEBRUARY;
			break;
		case 2:
			month = Calendar.MARCH;
			break;
		case 3:
			month = Calendar.APRIL;
			break;
		case 4:
			month = Calendar.MAY;
			break;
		case 5:
			month = Calendar.JUNE;
			break;
		case 6:
			month = Calendar.JULY;
			break;
		case 7:
			month = Calendar.AUGUST;
			break;
		case 8:
			month = Calendar.SEPTEMBER;
			break;
		case 9:
			month = Calendar.OCTOBER;
			break;
		case 10:
			month = Calendar.NOVEMBER;
			break;
		case 11:
			month = Calendar.DECEMBER;
			break;
		}

		Calendar cal = new GregorianCalendar(year, month, 1);
		int day = cal.get(Calendar.DAY_OF_WEEK) - 1;
		return day;
	}
	public void mark() {
		Calendar cal=Calendar.getInstance();
		this.mark(cal);
	}
	public void mark(Calendar cal) {

		int t_m = cal.get(Calendar.MONTH);
		int t_y = cal.get(Calendar.YEAR);
		int t_d = cal.get(Calendar.DAY_OF_MONTH);
		int s_m = frame.get_lst_mes().getSelectedIndex();

		int s_y = new Integer(frame.get_lst_anio().getSelectedItem().toString());
		int ini = get_day_week();// domingo

		if (t_m == s_m && t_y == s_y) {
			int max = 6;
			int cellx = ini;
			int celly = 0;
			for (int i = 0; i < this.get_days(); i++) {
				if (cellx > max) {
					cellx = 0;
					celly++;
				} else {
					cellx++;
				}

			}
			DefaultTableModel defaultTableModel = (DefaultTableModel) frame
					.getJTable2().getModel();
			for (int j = 0; j < celly + 1; j++) {
				if (j == (defaultTableModel.getRowCount() - 1)) {
					defaultTableModel.setRowCount(defaultTableModel
							.getRowCount() + 1);
				}
			}
			cellx = ini;
			celly = 0;
			for (int i = 1; i < this.get_days(); i++) {

				if (i == t_d) {

					frame.getJTable2().requestFocusInWindow();
					frame.getJTable2().clearSelection();
					frame.getJTable2().changeSelection(celly, cellx, false,
							false);
					// frame.getJTable().editCellAt(celly, cellx);

				}
				// //System.out.println("cell"+celly+":"+cellx);
				if (cellx == max) {
					cellx = 0;
					celly++;
				} else {
					cellx++;
				}

			}
		}
		show_time();
	}

	public void show_time() {
		

	}

	public boolean checkPostition(int row, int col) {
		boolean ok = false;
		int max = this.get_days();
		if (row < 0)
			row = 0;
		int number = row * 7 - this.get_day_week() + col + 1;// this.get_day_week()

		if (number <= max & number > 0) {
			if (row <= frame.getJTable2().getRowCount() - 1 & row >= 0) {
				if (col <= frame.getJTable2().getColumnCount() - 1 & col >= 0) {
					ok = true;
				}

			}
		}

		return ok;

	}

	public void fechas() {

		frame.get_lst_anio().removeItemListener(
				this.getConstructor().getItemListener());
		frame.get_lst_mes().removeItemListener(
				this.getConstructor().getItemListener());
		Calendar Cal = Calendar.getInstance();
		int year = Cal.get(Calendar.YEAR);
		int m = Cal.get(Calendar.MONTH);
		for (int i = year - 5; i < year + 5; i++) {
			frame.get_lst_anio().addItem("" + (i));
		}

		frame.get_lst_anio().setSelectedIndex(5);
		frame.get_lst_mes().setSelectedIndex(m);
		frame.get_lst_anio().addItemListener(
				this.getConstructor().getItemListener());
		frame.get_lst_mes().addItemListener(
				this.getConstructor().getItemListener());
		dias(frame.get_lst_mes(), frame.get_lst_anio());
	}

	public void dias(JComboBox _mes, JComboBox _anio) {
		int mes = _mes.getSelectedIndex();

		int anio = new Integer(_anio.getSelectedItem().toString());
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, mes);
		cal.set(Calendar.YEAR, anio);

		String default_Format = "dd-MM-yyyy";
		int day = 1;
		cal.set(Calendar.DAY_OF_MONTH, day);
		Locale locale = Locale.getDefault();
		String s1 = "";
		Date date = null;
		DateFormat formatter;
		try {
			date = cal.getTime();
			formatter = new SimpleDateFormat(default_Format, locale);
			s1 = formatter.format(date);
		} catch (Exception e) {

		}

		day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, day);
		try {
			date = cal.getTime();
			formatter = new SimpleDateFormat(default_Format, locale);
			s1 = formatter.format(date);
		} catch (Exception e) {

		}

	}

	public void update_mes(JComboBox _mes) {

		this.dias(_mes, frame.get_lst_anio());
		show_time2();
		this.goCargar();
	}

	public void update_anio(JComboBox _anio) {

		this.dias(frame.get_lst_mes(), _anio);
		show_time2();
		this.goCargar();
	}

	public void show_time2() {
		this.fillTable();
		int col = this.get_day_week();
		this.frame.getJTable2().changeSelection(0, col, false, false);
	}

	public int getHourPostition(String fecha, int rows) {
		Date today = new Convertidor().getDateWithFormat(formato, formato,
				fecha);
		java.util.GregorianCalendar _date = new java.util.GregorianCalendar();
		_date.setTime(today);
		int hh = _date.get(Calendar.HOUR_OF_DAY);

		return hh;

	}
	

	public void loadAgenda(Object[][] results, String desde) {
		int rows = 24;
		int days = 8;
		agendas = new Object[rows][days];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < days; j++) {
				List<String[]> aviso = new ArrayList<String[]>();
				agendas[i][j] = aviso;
			}
		}

		for (int i = 0; i < results.length; i++) {
			String idaviso = results[i][0].toString();
			String titulo = results[i][1].toString();
			String agenda = results[i][2].toString();
			//System.out.println(idaviso + " " + titulo + " " + agenda);
			boolean leido = results[i][3].toString().compareTo("1") == 0;
			String clasificacion = results[i][5].toString();
			String idclasificacion = "";
			try {
				idclasificacion = results[i][6].toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			int row = this.getHourPostition(agenda, rows);
			if (row < 0) {
				row = 0;
			}
			
			int col = this.getDateDiference(desde, agenda);

			List<Object[]> avisos = (List<Object[]>) agendas[row][col];
			avisos.add(new Object[] { idaviso, titulo, idclasificacion, leido });
			//System.out.println("Asignando valores a " + row + ":" + col + "+="+ idaviso + ":" + titulo);
			agendas[row][col] = avisos;

		}
	}

	
	public void loadAgendaMonth(Object[][] results, String desde) {
		int rows = 6;
		int days = 7;
		agendasM = new Object[rows][days];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < days; j++) {
				List<String[]> aviso = new ArrayList<String[]>();
				agendasM[i][j] = aviso;
			}
		}

		for (int i = 0; i < results.length; i++) {
			String idaviso = results[i][0].toString();
			String titulo = results[i][1].toString();
			String agenda = results[i][2].toString();
			
			boolean leido = results[i][3].toString().compareTo("1") == 0;
			String clasificacion = results[i][5].toString();
			String idclasificacion = "";
			try {
				idclasificacion = results[i][6].toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			int col = this.getColumnOnCalendar(agenda);
			int row = this.getRowOnCalendar(agenda);
			if (col>=0 & row>=0 & col<days & row<rows){
				List<Object[]> avisos = (List<Object[]>) agendasM[row][col];
				avisos
						.add(new Object[] { idaviso, titulo, idclasificacion, leido });
				//System.out.println("Asignando valores MONTH a " + row + ":" + col + "+="						+ idaviso + ":" + titulo+" fecha:"+agenda);
				agendasM[row][col] = avisos;	
			}
			

		}
	}

	public Object[][] processWeek(Object[][] results, String desde) {
		int rows = 24;
		int days = 8;
		String chk_name = _Interface._chk_aviso_semanal;
		String cell_item_name = _Interface._table_semanal_item;
		Object[][] tmp = new Object[rows][days];
		for (int i = 0; i < rows; i++) {
			String hora = "" + i;
			if (hora.length() < 2) {
				hora = "0" + hora;
			}
			hora += ":00";
			tmp[i][0] = hora;
			for (int j = 1; j < days - 1; j++) {

				tmp[i][j] = null;
			}
		}
		int w = frame.getJTabbedPane().getWidth();
		w = w - days*20-50;
		int width = new Double(new Double(w) / new Double(days ))
				.intValue();
		for (int i = 0; i < rows; i++) {
			String hora = "" + i;
			if (hora.length() < 2) {
				hora = "0" + hora;
			}
			hora += ":00";
			tmp[i][0] = hora;
			
			for (int j = 1; j < days; j++) {
				List<String[]> avisos = (List<String[]>) agendas[i][j];
				if (avisos.size() > 0) {

					Object[][] aux = new Object[avisos.size()][2];
					for (int u = 0; u < avisos.size(); u++) {
						Object[] _aux = avisos.get(u);
						String titulo = _aux[1].toString();
						if (titulo.length() > 30) {
							titulo = titulo.substring(0, 30);
						}
						aux[u][1] = titulo;
						aux[u][0] = (Boolean) _aux[3];
					}
					tmp[i][j] = this.create_table_cell_item(aux, i, j,
							chk_name, cell_item_name, width, 8,false,_Interface._table_semanal_item);
				} else {
					tmp[i][j] = null;
				}

			}
		}
		return tmp;
	}

	public Object[][] processMonth(Object[][] results, String desde) {
		int rows = 6;
		int days = 7;
		String chk_name = _Interface._chk_aviso_mensual;
		String cell_item_name = _Interface._table_mensual_item;
		Object[][] tmp = new Object[rows][days];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < days ; j++) {

				tmp[i][j] = null;
			}
		}
		int w = frame.getJTabbedPane().getWidth();
		w = w - days*40-10;
		int width = new Double(new Double(w) / new Double(days))
				.intValue();
			
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < days; j++) {
				List<String[]> avisos = (List<String[]>) agendasM[i][j];
				if (avisos.size() > 0) {

					Object[][] aux = new Object[avisos.size()][2];
					for (int u = 0; u < avisos.size(); u++) {
						Object[] _aux = avisos.get(u);
						String titulo = _aux[1].toString();
						if (titulo.length() > 30) {
							titulo = titulo.substring(0, 30);
						}
						aux[u][1] = titulo;
						aux[u][0] = (Boolean) _aux[3];
					}
					tmp[i][j] = this.create_table_cell_item(aux, i, j,
							chk_name, cell_item_name, width, 8,true,_Interface._table_mensual_item);
				} else {
					tmp[i][j] = this.create_table_cell_item(null, i, j,
							chk_name, cell_item_name, width, 8,true,_Interface._table_mensual_item);
				}

			}
		}
		return tmp;
	}

	
	public Object[][] processDay(Object[][] results, String desde) {
		int rows = 24;
		int days = 2;
		String chk_name = _Interface._chk_aviso_day;
		String cell_item_name = _Interface._table_day_item;
		Object[][] tmp = new Object[rows][days];
		for (int i = 0; i < rows; i++) {
			String hora = "" + i;
			if (hora.length() < 2) {
				hora = "0" + hora;
			}
			hora += ":00";
			tmp[i][0] = hora;
			for (int j = 1; j < days - 1; j++) {
				tmp[i][j] = null;
			}
		}

		String inicio = this.getWeekStartDate(desde);
		int index = this.getDateDiference(inicio, desde);
		int w = frame.getJTabbedPane().getWidth();
		w = w - 100;
		for (int i = 0; i < rows; i++) {
			String hora = "" + i;
			if (hora.length() < 2) {
				hora = "0" + hora;
			}
			hora += ":00";
			tmp[i][0] = hora;
			int j = 1;
			List<String[]> avisos = (List<String[]>) agendas[i][index];
			if (avisos.size() > 0) {
				Object[][] aux = new Object[avisos.size()][2];
				for (int u = 0; u < avisos.size(); u++) {
					Object[] _aux = avisos.get(u);
					String titulo = _aux[1].toString();

					aux[u][1] = titulo;
					aux[u][0] = (Boolean) _aux[3];
				}
				tmp[i][j] = this.create_table_cell_item(aux, i, index,
						chk_name, cell_item_name, w, 11,false,_Interface._table_day_item);
			} else {
				tmp[i][j] = null;
			}

		}
		return tmp;
	}

	public Object[][] process(Object[][] results) {
		Object[][] tmp = new Object[results.length][6];
		this.colors_avisos = null;
		colors_avisos = new Object[results.length][2];
		for (int i = 0; i < results.length; i++) {
			tmp[i][0] = false;
			tmp[i][1] = results[i][0].toString();
			tmp[i][2] = results[i][1].toString();
			tmp[i][3] = results[i][2].toString();
			boolean leido = results[i][3].toString().compareTo("1") == 0;
			tmp[i][4] = leido;
			tmp[i][5] = results[i][5].toString();
			colors_avisos[i][0] = (String) results[i][0];
			colors_avisos[i][1] = (String) results[i][6];
			//System.out.println("Colors>" + colors_avisos[i][0] + " "					+ colors_avisos[i][1]);
		}
		return tmp;
	}


	public void cargarAgendas(String fecha) {
		String iduser = frame.get_txt_idvendedor().getText();
		String idhost = data.getHost();
		frame.get_txt_agenda().setText(fecha);
		String desde = this.getWeekStartDate(fecha);
		String hasta = this.getWeekEndDate(fecha);
		String inicio= this._getTime2(0, 0);
		String fin=this._getTime2(5, 6);
		fin=this.getRollDate(fin, 0, 1);
		List<String> idcategoria = this.getDestinatarios();
		if (idcategoria.size() > 0) {
			Object[][] results = data.getAgenda(iduser, idhost, desde, hasta,
					idcategoria);
			Object[][] resultsM = data.getAgenda(iduser, idhost, inicio, fin,
					idcategoria);
			loadAgenda(results, desde);
			loadAgendaMonth(resultsM, inicio);
			results = this.processWeek(results, desde);
			resultsM = this.processMonth(resultsM, inicio);
			Object[][] results_day = this.processDay(results, fecha);
			this.create_table_agenda_diario(results_day, fecha);
			this.create_table_agenda_semanal(results, desde);
			this.create_table_agenda_mensual(resultsM, inicio);
			
		}

	}

	public void select(TreePath Selection) {
		String iduser = frame.get_txt_idvendedor().getText();

		if (Selection != null) {
			CheckBoxNode Node = (CheckBoxNode) (Selection
					.getLastPathComponent());

			this.goCargar();

		}
	}

	private class _taskCargar {
		_taskCargar() {
			_cargarAgenda();
		}
	}

	public void goCargar() {
		//System.out.println("Cargar");
		this.createTimer();
		frame.getJProgressBar().setIndeterminate(true);
		SwingWorker worker = null;
		if (worker == null) {
			worker = new SwingWorker() {
				public Object construct() {
					return new _taskCargar();
				}
			};
		}
		if (Timer != null) {
			Timer.start();
		}
		worker.start();

	}

	public void createTimer() {
		crono = new Crono();
		crono.start();
		current = 0;
		lenght = 0;
		done = false;
		canceled = false;

		Timer = new Timer(300, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (done & !canceled) {
					endbar();
					Timer.stop();
				} else {
					updateBar();
				}
			}
		});

	}

	public void endbar() {
		done = true;
		frame.getJProgressBar().setIndeterminate(false);
		frame.getJProgressBar().setValue(lenght);
		frame.getJProgressBar().setString("");
	}

	public void updateBar() {
		frame.getJProgressBar().setMaximum(lenght);
		frame.getJProgressBar().setValue(current);
		frame.getJProgressBar().setString(
				estado + " " + current + "/" + lenght + " " + crono.elapsed());
		frame.getJProgressBar().setStringPainted(true);
	}

	public String _getTime(int row, int col, JComboBox _mes, JComboBox _anio,
			int days) {
		String formato = "dd-MM-yyyy";
		int mes = _mes.getSelectedIndex();
		int anio = new Integer(_anio.getSelectedItem().toString());
		int day = -1;
		try {
			day = new Integer((String) frame.getJTable2().getValueAt(row, col));
		} catch (Exception e) {
			// //System.out.println("Error Dia:"+e.getMessage());
		}

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, mes);
		cal.set(Calendar.YEAR, anio);

		if (day > 0) {
			cal.set(Calendar.DAY_OF_MONTH, day);
		}

		// //System.out.println(cal.getTime());
		Locale locale = Locale.getDefault();
		String s1 = "";
		Date date = null;
		DateFormat formatter;
		try {
			if (days > 0) {
				cal.add(Calendar.DATE, days);
			}
			date = cal.getTime();

			formatter = new SimpleDateFormat(formato, locale);
			s1 = formatter.format(date);
		} catch (Exception e) {

		}
		if (s1.compareTo("") == 0) {
			s1 = data.getSystemDate();
		}
		return s1;
	}

	public String getTime() {
		int row = frame.getJTable2().getSelectedRow();
		int col = frame.getJTable2().getSelectedColumn();
		String s1 = this._getTime(row, col, frame.get_lst_mes(), frame
				.get_lst_anio(), 0);
		return s1;
	}

	public void _cargarAgenda() {
		estado = "Cargando Agenda";
		this.load_colors();

		int row = frame.getJTable2().getSelectedRow();
		int col = frame.getJTable2().getSelectedColumn();
		
		String desde = this._getTime2(row, col);
		//System.out.println("Desde > "+desde);
		if (javax.swing.SwingUtilities.isEventDispatchThread()) {
			frame.setJTable(null);
			frame.setJTable_semanal(null);
			cargarAgendas(desde);
		} else {
			final String _desde = desde;
			Runnable _execute = new Runnable() {
				public void run() {
					frame.setJTable(null);
					frame.setJTable_semanal(null);
					frame.setJTable_mensual(null);
					cargarAgendas(_desde);
				}
			};
			invokeLater(_execute);
		}
		done = true;
	}

	public int get_Clasificacion(String clasificacion, String padre) {
		int _clas = 0;
		String iduser = frame.get_txt_idvendedor().getText();
		String q = data.getId(clasificacion, padre, iduser);
		Object[][] results = data.getResults(q);
		if (results != null) {
			if (results.length > 0) {
				_clas = new Integer((String) results[0][0]);
			}
		}
		return _clas;
	}

	public boolean recodificar(String oldcode, String newcode, String color) {
		String iduser = frame.get_txt_idvendedor().getText();
		String q1 = data.getUpdate(newcode, oldcode, iduser, color);
		String q2 = data.getUpdatePadre(newcode, oldcode, iduser);
		data.clearBatch();
		//System.out.println(q1);
		//System.out.println(q2);
		data.addBatch(q1);
		data.addBatch(q2);

		boolean error = data.executeBatch();
		if (!error) {
			aviso("Se Recodifico Correctamente");
		} else {
			error("Error Recodificando ");
		}
		return error;
	}

	private boolean existe(String id) {
		String iduser = frame.get_txt_idvendedor().getText();
		boolean existe = data.exist(id, iduser);
		return existe;
	}

	public CheckBoxNode nueva_clasificacion(CheckBoxNode padre,
			CheckBoxNode clas, String color) {

		if (clas != null) {
			int ch = padre.getChildCount();
			boolean ex = true;
			while (ex) {
				ex = this.existe(padre.getIdclasificacion() + "-" + ch);
				if (ex) {
					ch++;
				}

			}
			clas.setIdclasificacion(padre.getIdclasificacion() + "-" + ch);
			String iduser = frame.get_txt_idvendedor().getText();
			String q = data.getInsert(clas.getIdclasificacion(), clas
					.getUserObject().toString(), padre.getIdclasificacion(),
					iduser, color);
			//System.out.println(q);
			data.beginTransaction();
			data.clearBatch();
			data.addBatch(q);
			boolean error = data.executeBatch();
			if (!error) {
				data.commitTransaction();

			} else {
				data.rollbackTransaction();
				error("Error Creando Clasificacion");
				clas = null;
			}

		}
		return clas;
	}

	public boolean eliminar_clasificacion(CheckBoxNode node, CheckBoxNode parent) {
		int i = node.getChildCount();
		boolean ok = true;
		String idvendedor = this.validar_vendedor();
		String old = frame.get_txt_idvendedor().getText();
		if (idvendedor.compareTo(old) == 0) {
			String clas = (String) node.getIdclasificacion();
			if (node.isLeaf()) {

				if (preguntar("confirmar", "Desea eliminar la clasificacion? ")) {

					String q = data.getDelete(clas,
							parent.getIdclasificacion(), idvendedor);
					//System.out.println(q);
					data.beginTransaction();
					data.clearBatch();
					data.addBatch(q);
					q = data.updateCategorias(clas, idvendedor);
					data.addBatch(q);
					boolean error = data.executeBatch();
					if (!error) {
						data.commitTransaction();
					} else {
						data.rollbackTransaction();
						ok = false;
					}
				}

			} else {
				aviso("Debe eliminar primero las sub-clasificaciones!");
			}
		} else {
			error("Error de Usuario");
			ok = false;
		}

		return ok;
	}

	private _Propiedades propiedades = null;

	public void initPropiedades() {
		if (this.propiedades != null) {
			propiedades.dispose();
		}
		propiedades = new _Propiedades();
		centrar_frame(propiedades);
		propiedades.setVisible(true);
		propiedades.get_btn_agregar().setActionCommand(
				_Interface._btn_propiedades_guardar);
		propiedades.get_btn_agregar().addActionListener(
				this.getConstructor().getActionListener());
		propiedades.get_btn_color().setActionCommand(
				_Interface._btn_propiedades_color);
		propiedades.get_btn_color().addActionListener(
				this.getConstructor().getActionListener());
		propiedades.get_txt_nombre().requestFocusInWindow();
	}

	public void renameCurrentNode() {
		// rootNode.removeAllChildren();
		// treeModel.reload();
		TreePath currentSelection = frame.getJTree().getSelectionPath();
		if (currentSelection != null) {
			CheckBoxNode currentNode = (CheckBoxNode) (currentSelection
					.getLastPathComponent());

			CheckBoxNode parent = (CheckBoxNode) (currentNode.getParent());
			this.initPropiedades();
			propiedades.get_txt_idclasificacion().setText(
					currentNode.getIdclasificacion());
			propiedades.get_txt_nombre().setText(
					currentNode.getUserObject().toString());
			String color = this.getcolor(currentNode.getIdclasificacion());
			Color _color = this.getColor(color);
			if (_color != null) {
				propiedades.get_btn_color().setBackground(_color);
			}

			propiedades.get_txt_color().setText(color);
		} else {
			error("Seleccione la clasificacion a modificar ");
		}
	}

	public void removeCurrentNode() {

		if (frame.getJTree() != null) {

			TreePath currentSelection = frame.getJTree().getSelectionPath();
			if (currentSelection != null) {
				CheckBoxNode currentNode = (CheckBoxNode) (currentSelection
						.getLastPathComponent());

				CheckBoxNode parent = (CheckBoxNode) (currentNode.getParent());
				if (parent != null) {
					if (currentNode.isLeaf()) {
						if (!currentNode.isRoot()) {
							DefaultTreeModel treeModel = (DefaultTreeModel) frame
									.getJTree().getModel();
							if (treeModel != null) {

								boolean ok = eliminar_clasificacion(
										currentNode, parent);
								if (ok)
									treeModel.removeNodeFromParent(currentNode);

							}

						} else {
							// JOptionPane.showMessageDialog(this,
							// "No se puede Eliminar la raiz!");
							error("No se puede Eliminar la raiz!");
						}
					} else {
						error("Debe Eliminar las Sub-Clasificaciones ");
					}
				}
			}
		}

		// Either there was no selection, or the root was selected.
		// toolkit.beep();
	}

	public CheckBoxNode renameCurrentNode(CheckBoxNode clas, String clasx,
			String color) {
		if (clas != null) {

			if (clasx.compareTo("") != 0) {
				String iduser = frame.get_txt_idvendedor().getText();

				String q = data.getUpdate(clasx, clas.getIdclasificacion(),
						iduser, color);
				//System.out.println(q);
				data.clearBatch();
				data.addBatch(q);
				boolean error = data.executeBatch();
				if (!error) {
					DefaultTreeModel treeModel = (DefaultTreeModel) frame
							.getJTree().getModel();
					clas.setUserObject(clasx);
					treeModel.reload(clas);
				} else {
					clas = null;
				}
			}

		}
		return clas;
	}

	public void addCategoria() {
		this.initPropiedades();
		propiedades.get_txt_nombre().requestFocusInWindow();
	}

	public void guardarCategoria() {
		TreePath currentSelection = frame.getJTree().getSelectionPath();
		if (currentSelection != null) {
			CheckBoxNode currentNode = (CheckBoxNode) (currentSelection
					.getLastPathComponent());

			CheckBoxNode parent = (CheckBoxNode) (currentNode.getParent());
			String idclassificacion = propiedades.get_txt_idclasificacion()
					.getText();
			String nombre = propiedades.get_txt_nombre().getText();
			String iduser = frame.get_txt_idvendedor().getText();
			String color = propiedades.get_txt_color().getText();

			boolean exist = data.exist(idclassificacion, iduser);
			if (exist) {
				String viejo = currentNode.getUserObject().toString();
				if (viejo.compareTo(nombre) != 0) {
					this.renameCurrentNode(currentNode, nombre, color);
				} else {
					data.beginTransaction();
					String q = data.getUpdate(nombre, idclassificacion, iduser,
							color);
					//System.out.println(q);
					data.clearBatch();
					data.addBatch(q);
					boolean error = data.executeBatch();
					if (!error) {
						data.commitTransaction();
					} else {
						data.rollbackTransaction();
					}
				}

			} else {
				this.addObject(nombre, color);
			}
			propiedades.setVisible(false);
			this.loadToMemory();
		}
	}

	public void editarCategoria() {
		this.initPropiedades();
		propiedades.get_txt_nombre().requestFocusInWindow();

	}

	public DefaultMutableTreeNode addObject(String idcategoria, String color) {

		CheckBoxNode parentNode = null;
		TreePath parentPath = frame.getJTree().getSelectionPath();
		DefaultTreeModel treeModel = (DefaultTreeModel) frame.getJTree()
				.getModel();
		if (parentPath == null) {
			parentNode = (CheckBoxNode) treeModel.getRoot();
		} else {
			parentNode = (CheckBoxNode) (parentPath.getLastPathComponent());
		}

		return addObject(parentNode, null, true, idcategoria, color);
	}

	public String getSelectedClass(JTree tree) {
		CheckBoxNode parentNode = null;
		try {
			TreePath parentPath = tree.getSelectionPath();
			if (parentPath != null) {
				DefaultTreeModel treeModel = (DefaultTreeModel) frame
						.getJTree().getModel();
				if (parentPath == null) {
					parentNode = (CheckBoxNode) treeModel.getRoot();
				} else {
					parentNode = (CheckBoxNode) (parentPath
							.getLastPathComponent());
				}
			}

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String clas_p = "";
		if (parentNode != null) {
			try {
				clas_p = (String) parentNode.getIdclasificacion();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return clas_p;
	}

	public String getSelectedClass() {
		String clas_p = "";

		clas_p = this.getSelectedClass(frame.getJTree());
		return clas_p;
	}

	public String getSelectedClassName() {
		String clas_p = "Pedidos";
		clas_p = this.getSelectedClassName(frame.getJTree());
		return clas_p;
	}

	public String getSelectedClassName(JTree tree) {
		CheckBoxNode parentNode = null;
		try {
			TreePath parentPath = tree.getSelectionPath();
			if (parentPath != null) {
				DefaultTreeModel treeModel = (DefaultTreeModel) frame
						.getJTree().getModel();
				if (parentPath == null) {
					parentNode = (CheckBoxNode) treeModel.getRoot();
				} else {
					parentNode = (CheckBoxNode) (parentPath
							.getLastPathComponent());
				}
			}

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String clas_p = "Pedidos";
		if (parentNode != null) {
			try {
				clas_p = (String) parentNode.getUserObject();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return clas_p;
	}

	public DefaultMutableTreeNode addObject(CheckBoxNode parent, Object child,
			boolean shouldBeVisible, String clas, String color) {
		String clas_p = (String) parent.getIdclasificacion();

		CheckBoxNode childNode = new CheckBoxNode(clas, parent
				.getIdclasificacion(), false);
		DefaultTreeModel treeModel = (DefaultTreeModel) frame.getJTree()
				.getModel();
		if (parent == null) {

			parent = (CheckBoxNode) treeModel.getRoot();
		}

		if (clas.compareTo("") != 0) {
			childNode = nueva_clasificacion(parent, childNode, color);
			if (childNode != null) {
				treeModel.insertNodeInto(childNode, parent, parent
						.getChildCount());
				if (shouldBeVisible) {
					frame.getJTree().scrollPathToVisible(
							new TreePath(childNode.getPath()));
				}
			}
		}
		return childNode;
	}

	public int getPosition(String idclas) {
		int i = 0;
		boolean found = false;
		if (memory != null) {
			while (i < memory.length & !found) {
				found = (memory[i][0].toString().compareTo(idclas) == 0);
				if (!found)
					i++;
			}
		}
		if (!found) {
			i = -1;
		}
		return i;
	}

	public String getcolorfromMemory(String idaviso) {
		String color = "";
		boolean found = false;
		int i = 0;
		if (this.colors_avisos != null) {
			while (!found & i < this.colors_avisos.length) {
				found = colors_avisos[i][0].toString().compareTo(idaviso) == 0;
				if (!found)
					i++;
			}
		}
		if (found) {
			String idclas = colors_avisos[i][1].toString();
			color = this.getcolor(idclas);
		}
		return color;
	}

	public String getcolor(String idclas) {
		String color = "";
		int i = this.getPosition(idclas);
		if (i >= 0) {
			color = memory[i][4].toString();

		}
		return color;
	}

	public Color getColor(String color) {

		int r = 255;
		int g = 255;
		int b = 255;

		if (color.compareTo("") != 0) {
			// //System.out.println("foreground"+foreground);
			String _r = color.substring(0, color.indexOf(","));
			try {
				r = new Integer(_r);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
			color = color.substring(color.indexOf(",") + 1);
			// //System.out.println("foreground"+foreground);
			String _g = color.substring(0, color.indexOf(","));
			try {
				g = new Integer(_g);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
			color = color.substring(color.indexOf(",") + 1);
			// //System.out.println("foreground"+foreground);
			String _b = color;
			try {
				b = new Integer(_b);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}

		}
		// //System.out.println("foreground rgb("+r+","+g+","+b+")");
		Color _foreground = new Color(r, g, b);

		return _foreground;
	}

	public Color getSelectedColor(String color) {

		int r = 0;
		int g = 0;
		int b = 0;

		if (color.compareTo("") != 0) {
			String _r = color.substring(0, color.indexOf(","));
			try {
				r = new Integer(_r);
			} catch (NumberFormatException e) {
			}
			color = color.substring(color.indexOf(",") + 1);
			String _g = color.substring(0, color.indexOf(","));
			try {
				g = new Integer(_g);
			} catch (NumberFormatException e) {
			}
			color = color.substring(color.indexOf(",") + 1);

			String _b = color;
			try {
				b = new Integer(_b);
			} catch (NumberFormatException e) {
			}

		}

		r += 40;
		g += 40;
		b += 40;
		if (r > 255)
			r = 255;
		if (g > 255)
			g = 255;
		if (b > 255)
			b = 255;
		Color _foreground = new Color(r, g, b);
		return _foreground;
	}

	public void seleccionarColor() {
		Color c = Color.white;
		Color newColor = JColorChooser.showDialog(_frame,
				"Seleccione un Color", c);
		if (newColor != null) {
			propiedades.get_btn_color().setBackground(newColor);
			propiedades.get_txt_color().setText(
					"" + newColor.getRed() + "," + newColor.getGreen() + ","
							+ newColor.getBlue());
		}
	}

	public String getClassName(Point p) {

		CheckBoxNode parentNode = null;
		try {
			TreePath parentPath = frame.getJTree().getPathForLocation(p.x, p.y);
			if (parentPath != null) {
				DefaultTreeModel treeModel = (DefaultTreeModel) frame
						.getJTree().getModel();
				if (parentPath == null) {
					parentNode = (CheckBoxNode) treeModel.getRoot();
				} else {
					parentNode = (CheckBoxNode) (parentPath
							.getLastPathComponent());
				}
			}

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String clas_p = "";
		if (parentNode != null) {
			try {
				clas_p = (String) parentNode.getIdclasificacion();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return clas_p;
	}

	public void drop(String flavor, Point p) {
		int n = 1;
		//System.out.println("data=" + flavor);
		String tmp = "";
		List<String> pedidos = new ArrayList<String>();
		String idclasificacion = this.getClassName(p);
		int columns = frame.getJTable().getColumnCount();
		//System.out.println("Row Count of Table> "	+ frame.getJTable().getColumnCount());
		while (flavor.indexOf("	") >= 0) {
			tmp = flavor.substring(0, flavor.indexOf("	"));
			flavor = flavor.substring(flavor.indexOf("	") + 1, flavor.length());
			if (n == 2) {
				if (idclasificacion.compareTo("") != 0) {
					//System.out.println("si tiene permiso. " + tmp + " -> "+ idclasificacion);
					pedidos.add(tmp);
				} else {
					if (idclasificacion.compareTo("0") == 0) {
						//System.out.println("si tiene permiso. Desclasifica? "	+ tmp + " -> " + idclasificacion);
						pedidos.add(tmp);
					}
				}
			}
			if (n >= columns - 1) {
				n = 1;
			} else {
				n++;
			}
		}
		if (pedidos.size() > 0) {
			this.change(pedidos, idclasificacion);
		}
	}

	public void processDND(DropTargetDropEvent dtde) {
		//System.out.println("Drop Event");
		dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);

		//System.out.println("Source>" + dtde.getSource());
		Point P = dtde.getLocation();

		this.drop(P);

		/*
		 * Transferable trans = dtde.getTransferable();
		 * //System.out.println("Flavors:"); boolean gotData = false; try { // try
		 * to get an image if
		 * (trans.isDataFlavorSupported(DataFlavor.stringFlavor)) {
		 * //System.out.println("image flavor is supported"); String flavor =
		 * (String) trans .getTransferData(DataFlavor.stringFlavor); //
		 * showImageInNewFrame (img); drop(flavor, dtde.getLocation()); gotData
		 * = true; } else if (trans
		 * .isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
		 * //System.out.println("javaFileList is supported"); java.util.List list
		 * = (java.util.List) trans
		 * .getTransferData(DataFlavor.javaFileListFlavor); ListIterator it =
		 * list.listIterator(); while (it.hasNext()) { File f = (File)
		 * it.next(); //System.out.println("Adding Foto DND >" +
		 * f.getAbsolutePath());
		 * 
		 * } gotData = true; } else if
		 * (trans.isDataFlavorSupported(uriListFlavor)) {
		 * //System.out.println("uri-list flavor is supported"); String uris =
		 * (String) trans.getTransferData(uriListFlavor);
		 * 
		 * // url-lists are defined by rfc 2483 as crlf-delimited
		 * StringTokenizer izer = new StringTokenizer(uris, "\r\n"); while
		 * (izer.hasMoreTokens()) { String uri = izer.nextToken();
		 * //System.out.println(uri);
		 * 
		 * } gotData = true; } else if (trans.isDataFlavorSupported(urlFlavor))
		 * { //System.out.println("url flavor is supported"); URL url = (URL)
		 * trans.getTransferData(urlFlavor); //System.out.println(url.toString());
		 * 
		 * //System.out.println(url);
		 * 
		 * gotData = true; } } catch (Exception e) { e.printStackTrace(); }
		 * finally { //System.out.println("gotData is " + gotData);
		 * dtde.dropComplete(gotData); }
		 */
	}

	public boolean change(List<String> avisos, String idclasificacion) {
		boolean ok = true;
		if (avisos.size() > 0) {
			String idvendedor = this.validar_vendedor();
			String old = frame.get_txt_idvendedor().getText();
			if (idvendedor.compareTo(old) == 0) {
				data.beginTransaction();
				data.clearBatch();
				for (int i = 0; i < avisos.size(); i++) {
					String q = "";
					if (data.existeCategoria(avisos.get(i), idvendedor)) {
						q = data.updateCategoria(avisos.get(i), idvendedor,
								idclasificacion);
					} else {
						q = data.insertCategoria(avisos.get(i), idvendedor,
								idclasificacion);
					}
					//System.out.println(q);
					data.addBatch(q);
				}
				boolean error = data.executeBatch();
				if (!error) {
					data.commitTransaction();

					aviso("El cambio se Realizo Correctamente");
					this.goCargar();
				} else {
					ok = false;
					data.rollbackTransaction();
					error("Error Grabando");
				}

			} else {
				error("Usuario Invalido");
			}
		}
		return ok;
	}

	public void editarAviso(JTable table, int row) {
		if (row >= 0) {
			String idaviso = table.getValueAt(row, 1).toString();
			this.editarAviso(idaviso);
		}
	}

	public void editarAviso(String idaviso) {
		//System.out.println("Tratando de cargar idaviso");
		String idvendedor = frame.get_txt_idvendedor().getText();
		String iduser = idvendedor;
		if (data.existeAviso(idaviso, iduser)) {
			if (data.esCreador(idaviso, iduser)) {
				this._editarAviso(idaviso, iduser);
			} else {
				this._leerAviso(idaviso, iduser);
			}
		} else {
			error("No puede ver este aviso");
		}
	}

	public void _editarAviso(String idaviso, String iduser) {
		aplicacion.gestion.agenda.escritor.constructor._Constructor CC = new aplicacion.gestion.agenda.escritor.constructor._Constructor();
		CC.setParameter(_parametros.connector, this._data
				.getConnectionHandler().Clone());
		CC.setParameter(_parametros.LookAndFeel, this.getConstructor()
				.getLookAndFeelTheme());
		CC.build(this.getConstructor());
		CC.init();
		aplicacion.gestion.agenda.escritor.logic._Logic logic = (aplicacion.gestion.agenda.escritor.logic._Logic) CC
				.getLogic();
		logic.setGestion((aplicacion.gestion.agenda.gestion.constructor._Constructor)this.getConstructor());
		logic.cargarAviso(idaviso, iduser);
	}
	
	public CheckBoxNode getRaiz(){
		CheckBoxNode raiz = null;
	
		List<String> lista = new ArrayList<String>();

		DefaultTreeModel treeModel=null;
		try {
			treeModel = (DefaultTreeModel) frame.getJTree()
					.getModel();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (treeModel!=null){
			
			try {
				raiz = (CheckBoxNode) treeModel.getRoot();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}

			if (raiz != null) {
				
			}
		
	}
		return raiz;
	}

	public CustomPopup create_interface(String idaviso){
		  CustomPopup popup = new CustomPopup();
		  MenuItem menuItem;
		  menuItem = new MenuItem("Nuevo");
		  menuItem.setActionCommand(_Interface._btn_nuevo_aviso);
		  menuItem.addActionListener(this.getConstructor().getActionListener());
		  menuItem.setFont(new Font("Dialog", Font.PLAIN, 10));
		  //popup.add(menuItem);
		  menuItem = new MenuItem("Editar");
		  menuItem.setActionCommand(_Interface._btn_editar_aviso);
		  menuItem.addActionListener(this.getConstructor().getActionListener());
		  menuItem.setFont(new Font("Dialog", Font.PLAIN, 10));
		  //popup.add(menuItem);
		  menuItem = new MenuItem("Categoria");
		  //menuItem.setActionCommand(_Interface._btn_minimizar);
		  menuItem.addActionListener(this.getConstructor().getActionListener());
		  menuItem.setFont(new Font("Dialog", Font.PLAIN, 10));
		  
		  Menu categorias=this.getCategoriasMenu();
		  popup.setIdaviso(idaviso);
		  
		  popup.add(categorias);
		  return popup;
	}
	
	private Menu getCategoriasMenu(){
		
		
		MenuComponent mc= this.load_clases_from_tree(this.getRaiz());
		Menu categorias=(Menu) mc;
		return categorias;
	}
	
	public void recategotizar(String idaviso,String idcategoria){
		data.beginTransaction();
		data.clearBatch();
		String iduser=frame.get_txt_idvendedor().getText();
		
		String q = "";
		if (data.existeCategoria(idaviso, iduser)){
			q=data.updateCategoria(idaviso, iduser, idcategoria);	
		}else{
			q=data.insertCategoria(idaviso, iduser, idcategoria);
		}
		
		data.addBatch(q);
		avisosDragged = new ArrayList<String>();
		boolean error = data.executeBatch();
		if (!error) {
			data.commitTransaction();
			this.goCargar();
		} else {
			data.rollbackTransaction();
		}
	}
	public MenuComponent load_clases_from_tree(CheckBoxNode abuelo) {
		if (abuelo.getChildCount()>0){
			Menu mpadre=new Menu(abuelo.getText());
			mpadre.addActionListener(this.getConstructor().getActionListener());
			for (int i=0;i<abuelo.getChildCount();i++){
				CheckBoxNode nodo=(CheckBoxNode)abuelo.getChildAt(i);
				MenuComponent mc=this.load_clases_from_tree(nodo);
				if (mc instanceof Menu){
					mpadre.add((Menu)mc);	
				}else{
					mpadre.add((MenuItem)mc);
				}
			}
			return mpadre;
		}else{
			CustomMenuItem mpadre=new CustomMenuItem(abuelo.getText());
			mpadre.setIdcategoria(abuelo.getIdclasificacion());
			mpadre.addActionListener(this.getConstructor().getActionListener());
			mpadre.setActionCommand(_Interface._btn_recategorizar_aviso);
			return mpadre;
		}
		
	}
}
