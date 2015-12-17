package aplicacion.ventas.gestion2.logic;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.LookupOp;
import java.awt.image.LookupTable;
import java.awt.image.ShortLookupTable;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.Vector;
import aplicacion.herramientas.java.launcher.logic.SwingWorker;
import aplicacion.herramientas.java.sortableselector.logic.Filtro;
import aplicacion.herramientas.java.table.CellEditor;
import aplicacion.herramientas.java.table.CheckBoxCellEditor;
import aplicacion.herramientas.java.table.Column;
import aplicacion.herramientas.java.table.ComboBoxEditor;
import aplicacion.herramientas.java.table.CustomTable;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Logic;
import aplicacion.modelo.logic.Data;
import aplicacion.sistema.version.logic.Crono;
import aplicacion.sistema.version.logic.JakartaFtpWrapper;
import aplicacion.ventas.gestion2.gui._Frame;
import aplicacion.ventas.gestion2.logic._Data;
import aplicacion.ventas.gestion2.constructor._Constructor;
import aplicacion.ventas.gestion2.interfaces._Interface;
import aplicacion.ventas.pedido.gui._Faltantes;
import aplicacion.ventas.pedido.logic.TableItemColorCellRenderer;
import aplicacion.herramientas.java.image.logic.*;
import aplicacion.herramientas.java.table.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JTree;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import org.apache.commons.net.ftp.FTPFile;
import aplicacion.herramientas.java.*;
import aplicacion.ventas.gestion2.gui._Propiedades;

public class _Logic extends Logic {
	private _Frame frame = null;
	private _Propiedades propiedades=null;
	private _Data data = null;
	private aplicacion.herramientas.java.visualizadores.Proveedor vProveedor = null;
	private String[][] memory=null;
	private String default_Format = "dd-MM-yyyy";
	private String estado = "";
	private int current;
	private int lenght;
	DropTarget dropTarget;
	JLabel dropHereLabel;
	DataFlavor urlFlavor, uriListFlavor, macPictStreamFlavor;
	private boolean debug, done, canceled;
	private Timer Timer; // @jve:decl-index=0:
	private Crono crono;
	private Object[][] colors;
	private Object[][] colors_pedidos;
	private String formato="dd-MM-yyyy HH:mm:ss";
	
	private class _taskCargar {
		_taskCargar(String idcategoria) {
			_cargarPedidos(idcategoria);
		}
	}
	public void goCargar() {
		String idcategoria=this.getSelectedClass();
		this.goCargar(idcategoria);
	}
	
public void goCargar(String idcategoria) {
	final String _idcategoria=idcategoria;
		System.out.println("Cargar");
		this.createTimer();
		frame.getJProgressBar().setIndeterminate(true);
		SwingWorker worker = null;
		if (worker == null) {
			worker = new SwingWorker() {
				public Object construct() {
					return new _taskCargar(_idcategoria);
				}
			};
		}
		if (Timer!=null) {
			Timer.start();
		}
		worker.start();
		
	}
	public _Logic(){
		this.acumulators=new ArrayList<Object[]>();
	}
	
	public void setFrame(JFrame frame) {
		this.frame = (_Frame) frame;
		super.setFrame(frame);
	}

	public void mark(int row, int col) {
		frame.getJTable2().changeSelection(row, col, false, false);
	}

	
	private aplicacion.herramientas.java.buscadores.Fecha bFecha = null;

	public void BuscarFecha(JTextField tx) {
		if (bFecha == null) {
			bFecha = new aplicacion.herramientas.java.buscadores.Fecha(this
					.getConstructor());

		}
		
		bFecha.Buscar(tx);
	}


	


	public void createTimer() {
		crono = new Crono();
		crono.start();
		current = 0;
		lenght=0;
		done=false;
		canceled=false;
		showLayer(false);
		Timer = new Timer(300, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (done &!canceled) {
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
		showLayer(true);
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




	public void cancelar() {
		
			doclean();
	}

	public void imprimir() {
		if (frame.getJScrollPane2().getViewport().getView() instanceof ScrollableImage) {
			ScrollableImage scroll = (ScrollableImage) frame.getJScrollPane2()
					.getViewport().getView();
			scroll.print();
		}

	}

	

	public void calculate_column(int column, JTextField tx) {
		int total = frame.getJTable().getRowCount();
		double suma = 0.0;
		for (int i = 0; i < total; i++) {
			double sum = 0.0;
			try {
				String control = frame.getJTable().getValueAt(i, column)
						.toString();

				sum = new Double(control.replaceAll(",", ""));
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (sum > 0) {
				suma += sum;
			}
		}
		Convertidor C = new Convertidor();
		tx.setText(C.getMoney(suma, 2));
	}



	
	



	public void delete_fecha_desde(JTextField tx) {
		String fecha = tx.getText();

	}

	public void setData(Data data) {
		this.data = (_Data) data;
		super.setData(data);
	}

		
	public void update_level(JComboBox cb,int row){
		System.out.println("Update Nivel "+row);
		String valor=cb.getSelectedItem().toString();
		String idpedido=frame.getJTable().getValueAt(row, 1).toString();
		String iduser=frame.get_txt_idvendedor().getText();
		String q=data.getUpdateLevel(idpedido, iduser,valor);
		System.out.println(q);
		data.beginTransaction();
		data.clearBatch();
		data.addBatch(q);
		boolean error=data.executeBatch();
		if (!error){
			data.commitTransaction();
		}else{
			data.rollbackTransaction();
		}
	}
	
	private void create_table_nuevos(Object[][] results) {
		//idpedido|descripcion|agenda|cuenta|nombre|importe|vendedor|estado|creado|seguimiento
		_Constructor constructor = (_Constructor) this.getConstructor();
		TablePedidosColorCellRenderer renderer = new TablePedidosColorCellRenderer();
		renderer.setLogic(this);
		TableCheckCellRenderer chk_renderer = new TableCheckCellRenderer();
		chk_renderer.setLogic(this);
		DateRenderer daterenderer=new DateRenderer();
		daterenderer.setLogic(this);
		CustomTable table = new CustomTable();

		Column col = new Column();

		col = new Column();
		col.setName("");
		col.setWidth(20);
		col.setEditable(true);
		col.setClass(Boolean.class);
		col.setCellRenderer(chk_renderer);
		table.addColumn(col);

		
		col = new Column();
		col.setName("idpedido");
		col.setWidth(64);
		col.setEditable(false);
		col.setClass(String.class);
		col.setCellRenderer(renderer);
		table.addColumn(col);

		col = new Column();
		col.setName("Descripcion");
		col.setWidth(210);
		col.setEditable(false);
		col.setCellRenderer(renderer);
		table.addColumn(col);

		/*col = new Column();
		col.setName("Agenda");
		col.setWidth(68);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		col.setClass(Date.class);
		col.setCellRenderer(daterenderer);
		table.addColumn(col);
		*/
		col = new Column();
		col.setName("Cuenta");
		col.setWidth(64);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		col.setCellRenderer(renderer);
		table.addColumn(col);

		col = new Column();
		col.setName("Nombre");
		col.setWidth(110);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		col.setCellRenderer(renderer);
		table.addColumn(col);
		
		/*col = new Column();
		col.setName("Importe");
		col.setWidth(60);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		col.setCellRenderer(renderer);
		table.addColumn(col);
		*/
		
		col = new Column();
		col.setName("Nivel");
		col.setWidth(44);
		col.setEditable(true);
		col.setAligment(JTextField.RIGHT);
		col.setCellRenderer(renderer);
		ComboBoxEditor cb=new ComboBoxEditor();
		String[] values=new String[10];
		for (int i=0;i<values.length;i++){
			values[i]=""+i;
		}
		cb.setValues(values);
		cb.setName(_Interface._lst_nivel);
		cb.addItemListener(this.getConstructor().getItemListener());
		
		col.setCellEditor(cb.getCellEditor());
		table.addColumn(col);
		
		
		col = new Column();
		col.setName("Usuario");
		col.setWidth(70);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		col.setCellRenderer(renderer);
		table.addColumn(col);
		
		/*
		col = new Column();
		col.setName("Creado");
		col.setWidth(80);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		col.setCellRenderer(daterenderer);
		col.setClass(Date.class);
		table.addColumn(col);
		 */
		col = new Column();
		col.setName("");
		col.setWidth(20);
		col.setEditable(true);
		col.setClass(Boolean.class);
		CheckBoxCellEditor chkce = new CheckBoxCellEditor();
		chkce.setItemListener(this._constructor.getItemListener());
		chkce.setTipo(Boolean.class);
		chkce.setName(_Interface._chk_seguimiento);
		col.setCellEditor(chkce.getCellCheck());
		
		table.addColumn(col);
		
		col = new Column();
		col.setName("Clasificacion");
		col.setClass(String.class);
		col.setWidth(90);
		col.setEditable(false);
		col.setCellRenderer(renderer);
		table.addColumn(col);

		col = new Column();
		col.setName("Agenda");
		col.setWidth(66);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		col.setClass(Date.class);
		col.setCellRenderer(daterenderer);
		table.addColumn(col);
		
		
		if (frame.get_chk_estado().isSelected()){
			col = new Column();
			col.setName("estado");
			col.setWidth(80);
			col.setEditable(false);
			col.setAligment(JTextField.RIGHT);
			col.setCellRenderer(renderer);
			col.setClass(String.class);
			table.addColumn(col);	
		}
		
		
		table.setName(_Interface._table_pedidos_nuevos);
		table.setData(results);
		table.addKeyListener(this.getConstructor().getKeyListener());
		table.addMouseListener(this.getConstructor().getMouseListener());
		Font fuente = new Font("Dialog", Font.PLAIN, 10);
		table.setHeaderFont(fuente);
		table.setFont(fuente);
		table.build();
		table.fillData();

		JTable _table = table.getTable();
		_table.setDragEnabled(true);
		_table.setColumnSelectionAllowed(false);
		_table.setRowSelectionAllowed(true);
		frame.setJTable(_table);
		System.out.println("what happend?");
	}
	
	
		

		public void update_modo(JComboBox _modo) {
			this.goCargar();
		}
		
	public void update_mes(JComboBox _mes) {

		this.dias(_mes, frame.get_list_anio());
		show_time2();
	}

	public void update_anio(JComboBox _anio) {

		this.dias(frame.get_list_mes(), _anio);
		show_time2();
	}

	
	public void show_time2() {

		this.create_table();
		this.fillTable();
		int col = this.get_day_week();
		this.frame.getJTable2().changeSelection(0, col, false, false);

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

	public int get_day_week() {
		String s_year = frame.get_list_anio().getSelectedItem().toString();
		int year = new Integer(s_year);
		int month = 0;
		switch (frame.get_list_mes().getSelectedIndex()) {
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
	

	public boolean elegir_vendedor() {
		boolean ok = false;
		System.out.println("elegir vendedor");
		String idvendedor = this.validar_vendedor();
		frame.get_txt_idvendedor().setText(idvendedor);

		if (idvendedor.compareTo("") != 0) {
			ok = true;
			this.evaluarVendedor(frame.get_txt_idvendedor());
			frame.get_txt_idvendedor().setEditable(false);
			buildTree();
			fechas();
			fillTable();

			String	idcategoria=this.getSelectedClass();
			this.goCargar(idcategoria);
			
		} else {
			ok = false;
		}

		return ok;
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
	
	public void initPropiedades(){
		if (this.propiedades!=null){
			propiedades.dispose();
		}
		propiedades=new _Propiedades();
		centrar_frame(propiedades);
		propiedades.setVisible(true);
		propiedades.get_btn_agregar().setActionCommand(_Interface._btn_propiedades_guardar);
		propiedades.get_btn_agregar().addActionListener(this.getConstructor().getActionListener());
		propiedades.get_btn_color().setActionCommand(_Interface._btn_propiedades_color);
		propiedades.get_btn_color().addActionListener(this.getConstructor().getActionListener());
		propiedades.get_txt_nombre().requestFocusInWindow();
	}
	
	public void fillTable() {
		this.create_table();
		this.load_colors();
		int ini = get_day_week();// domingo

		int max = 6;
		int cellx = ini;
		int celly = 0;
		for (int i = 0; i <= this.get_days(); i++) {
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
				defaultTableModel
						.setRowCount(defaultTableModel.getRowCount() + 1);
			}
		}
		cellx = ini;
		celly = 0;
		for (int i = 1; i <= this.get_days(); i++) {
			frame.getJTable2().setValueAt("" + i, celly, cellx);
			// System.out.println("cell"+celly+":"+cellx);
			if (cellx == max) {
				cellx = 0;
				celly++;
			} else {
				cellx++;
			}

		}
		
		
		
		
		frame.getJTable2().repaint();
		mark(Calendar.getInstance());
	}

	public int get_days() {
		String s_year = frame.get_list_anio().getSelectedItem().toString();
		int year = new Integer(s_year);
		int month = 0;
		switch (frame.get_list_mes().getSelectedIndex()) {
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
		// System.out.println("dias del mes "+days);
		return days;
	}

	public void mark(Calendar cal) {

		int t_m = cal.get(Calendar.MONTH);
		int t_y = cal.get(Calendar.YEAR);
		int t_d = cal.get(Calendar.DAY_OF_MONTH);
		int s_m = frame.get_list_mes().getSelectedIndex();
		int s_y = new Integer(frame.get_list_anio().getSelectedItem()
				.toString());
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
				// System.out.println("cell"+celly+":"+cellx);
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
		System.out.println("showtime");
		// frame.get_txt_fecha().setText(""+this.getTime());

	}

	public void cargar_dia_actual(){
		String fecha=data.getSystemDate();
		String	idcategoria=this.getSelectedClass();
		this.goCargar(idcategoria);
	}
	public void showLayer(boolean show){
		frame.getLockableUI().setLocked(!show);
		frame.getLockableUI2().setLocked(!show);
	}
	private void create_table() {
		_Constructor constructor = (_Constructor) this.getConstructor();
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
		Object[][] results = new Object[][] { { "", "", "", "", "", "", "" } };
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

	public void fechas() {
		
		frame.get_list_anio().removeItemListener(this.getConstructor().getItemListener());
		frame.get_list_mes().removeItemListener(this.getConstructor().getItemListener());
		Calendar Cal = Calendar.getInstance();
		int year = Cal.get(Calendar.YEAR);
		int m = Cal.get(Calendar.MONTH);
		for (int i = year - 5; i < year + 5; i++) {
			frame.get_list_anio().addItem("" + (i));
		}

		frame.get_list_anio().setSelectedIndex(5);
		frame.get_list_mes().setSelectedIndex(m);
		frame.get_list_anio().addItemListener(this.getConstructor().getItemListener());
		frame.get_list_mes().addItemListener(this.getConstructor().getItemListener());
		dias(frame.get_list_mes(), frame.get_list_anio());
	}

	public String _getTime(int row, int col, JComboBox _mes, JComboBox _anio,
			int days) {

		int mes = _mes.getSelectedIndex();
		int anio = new Integer(_anio.getSelectedItem().toString());
		int day = -1;
		try {
			day = new Integer((String) frame.getJTable2().getValueAt(row, col));
		} catch (Exception e) {
			// System.out.println("Error Dia:"+e.getMessage());
		}

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, mes);
		cal.set(Calendar.YEAR, anio);

		if (day > 0) {
			cal.set(Calendar.DAY_OF_MONTH, day);
		}

		// System.out.println(cal.getTime());
		Locale locale = Locale.getDefault();
		String s1 = "";
		Date date = null;
		DateFormat formatter;
		try {
			if (days > 0) {
				cal.add(Calendar.DATE, days);
			}
			date = cal.getTime();

			formatter = new SimpleDateFormat(default_Format, locale);
			s1 = formatter.format(date);
		} catch (Exception e) {

		}
		if (s1.compareTo("")==0){
			s1=data.getSystemDate();
		}
		return s1;
	}

	public String getTime() {
		int row = frame.getJTable2().getSelectedRow();
		int col = frame.getJTable2().getSelectedColumn();
		String s1 = this._getTime(row, col, frame.get_list_mes(), frame
				.get_list_anio(), 0);
		return s1;
	}

	public void completeDiaCarga(int row, int col) {
		System.out.println("complete dia de carga");

		if (this.checkPostition(row, col)) {
			String fecha = this._getTime(row, col, frame.get_list_mes(), frame
					.get_list_anio(), 0);
			System.out.println("FECHA>" + fecha);
	
			fecha = this._getTime(row, col, frame.get_list_mes(), frame
					.get_list_anio(), 1);
			System.out.println("FECHA>" + fecha);
	
			String	idcategoria=this.getSelectedClass();
			this.goCargar(idcategoria);
		} else {

		}

	}
	
	public void completeDiaCarga() {
		System.out.println("complete dia de carga");
		int row = frame.getJTable2().getSelectedRow();
		int col = frame.getJTable2().getSelectedColumn();
		this.completeDiaCarga(row, col);

	}

	

	public void utiliza_calendario() {
		frame.getJTable2().setEnabled(true);
		this.completeDiaCarga();
		String	idcategoria=this.getSelectedClass();
		this.goCargar(idcategoria);
	}


	public String getEstado(String idpedido){
		String estado="";
		return estado;
	}
	
	public void evaluar_estado(JComboBox cb){
		cb.transferFocus();
		String	idcategoria=this.getSelectedClass();
		this.goCargar(idcategoria);
	}
	
	public Object[][] process_data(Object[][] results,boolean seguimiento){
		Object[][] tmp=null;
		this.colors_pedidos=null;
		if (results!=null){
			Convertidor cv=new Convertidor();
			if (results.length>0){
				colors_pedidos=new Object[results.length][2];	
				
					tmp=new Object[results.length][results[0].length];
					
					for (int i=0;i<results.length;i++){
						tmp[i][0]=false;
						colors_pedidos[i][0]=(String)results[i][0];
						colors_pedidos[i][1]=(String)results[i][9];
						String _seguimiento=(String)results[i][6];
							for (int j=0;j<results[0].length-1;j++){
								tmp[i][j+1]=results[i][j];	
							}
						//tmp[i][3]=cv.getDateWithFormat2("dd-MM-yyyy", results[i][2].toString());
						tmp[i][9]=cv.getDateWithFormat2("dd-MM-yyyy", results[i][8].toString());
						tmp[i][7]=_seguimiento.compareTo("1")==0;
							
							//tmp[matches][results[0].length-1]=(results[i][results[0].length-1].toString().compareTo("1")==0);
							
						
						
					}
					
				
						
			}
			
		}
		return tmp;
	}
	
	
	public void exit_command(){
		
		
		this.getConstructor().dispose(false);
		
	}
	
	public void _cargarPedidos(String idcategoria) {
		estado="actualizando pedidos";
		data.updatePedidosConSeguimiento();
		this.acumulate();
		this.load_colors();
		int row=frame.getJTable2().getSelectedRow();
		int col=frame.getJTable2().getSelectedColumn();
		String desde = this._getTime(row, col, frame.get_list_mes(),
				frame.get_list_anio(), 0);
		String hasta = this._getTime(row, col, frame.get_list_mes(),
				frame.get_list_anio(), 1);//sino 1
		if (!frame.get_chk_utiliza_calendario().isSelected()){
			desde="01-01-1900";
			hasta="01-01-2300";
		}
		int top=1000;
		String q="";
		if(frame.get_chk_estado().isSelected()){
			q=data.getPedidosEstadoQuery(desde, hasta, top,frame.get_chk_utiliza_calendario().isSelected());
		}else{
			int modo=frame.get_lst_modo().getSelectedIndex();
			if (modo==0){
				q=data.getPedidosQuery(desde, hasta,top,frame.get_chk_utiliza_calendario().isSelected());	
			}
			if (modo==1){
				q=data.getPedidosTransferidosQuery(desde, hasta,top,frame.get_chk_utiliza_calendario().isSelected());
			}
					
			
			/*
			if (idcategoria.compareTo("0-4")==0){
				
			}else{
					
			}*/
			
		}
		
		
		String acumulated=this.getAcumulatedQuery(idcategoria);
		System.out.println("getAcumulated for pedidos>"+acumulated);
		if (frame.get_chk_utiliza_calendario().isSelected()){
			q+=" and ";
		}
		if (!frame.get_chk_eliminados().isSelected()){
			q+=" pedido.eliminar=0 ";
		}
		
		if (acumulated.length()>0){
			q+=" and ";
			q+=" "+acumulated+" ";
			
		}
		if (frame.get_lst_modo().getSelectedIndex()==1){
			String iduser=frame.get_txt_idvendedor().getText();
			q+=" and pedido.idvendedor not like '"+iduser+"' ";
		}
		if (frame.get_chk_estado().isSelected()){
			q+=" group by ";
			q+="pedido.idpedido,pedido.seguimiento,pedido.descripcion, ";
			q+="convert(varchar(10),isnull(pedido.fecha_agenda,pedido.ultima_modificacion),105) , ";
			q+="pedido.cliente, pedido.cliente_descripcion,isnull(pedido.total,0),isnull(vendedor.nombre,''),isnull(creador.nombre,''), ";
			q+="convert(varchar(10),pedido.fecha_creacion,105),";
			q+="rmx.anulada,fvn.anulada ";
			q+=",isnull(ucategoria.clasificacion,''),isnull(categorias.idcategoria,'') ";
			q+=",isnull(categorias.nivel,0) ";
			if (frame.get_lst_estado().getSelectedIndex()>=1){
				q+="having ";
				q+="(case when count(remitos.remito)=count(alfa.tc) and count(remitos.remito)>0 then 'Alfa' else ( ";
				q+="(case when count(remitos.remito)=count(fvn.tc) and count(remitos.remito)>0 and fvn.anulada=0 then 'Beta' else ( ";
				q+="(case when count(remitos.remito)>0 and isnull(rmx.anulada,0)=0 then 'RM' else '' end) )  end  ";
				q+=")) end) like '"+frame.get_lst_estado().getSelectedItem().toString()+"' ";
			}
			
			System.out.println("pedidos>"+q);
		}else{
			q+=" group by ";
			q+="pedido.idpedido,pedido.seguimiento,pedido.descripcion, ";
			q+="convert(varchar(10),isnull(pedido.fecha_agenda,pedido.ultima_modificacion),105) , ";
			q+="pedido.cliente, pedido.cliente_descripcion,isnull(pedido.total,0),isnull(vendedor.nombre,''),isnull(creador.nombre,''), ";
			q+="convert(varchar(10),pedido.fecha_creacion,105)";
			q+=",isnull(ucategoria.clasificacion,''),isnull(categorias.idcategoria,'') ";
			q+=",isnull(categorias.nivel,0) ";
			if (frame.get_lst_modo().getSelectedIndex()==1){
				q+=",	transferencia.fecha ";
			}
		}
		
		/*
		if (frame.get_lst_estado().getSelectedIndex()>=1){
			q+="having ";
			q+="(case when count(remitos.remito)=count(alfa.tc) and count(remitos.remito)>0 then 'Alfa' else ( ";
			q+="(case when count(remitos.remito)=count(fvn.tc) and count(remitos.remito)>0 and fvn.anulada=0 then 'Beta' else ( ";
			q+="(case when count(remitos.remito)>0 and isnull(rmx.anulada,0)=0 then 'RM' else '' end) )  end  ";
			q+=")) end) like '"+frame.get_lst_estado().getSelectedItem().toString()+"' ";
		}*/
		q+="order by isnull(categorias.nivel,0) desc ,pedido.seguimiento desc,pedido.idpedido desc ";
//		q+=",isnull(categorias.nivel,0) ";
		System.out.println("pedidos>"+q);
		estado="Esperando Respuesta del Servidor de Base de Datos";
		Object[][] results=data.getResults(q);
		boolean empty=true;
		if (results!=null){
			if (results.length>0){
				empty=false;
				Object[][] results_nuevos=process_data(results,false);
				this.create_results_nuevos(results_nuevos);
					
			}else{
				
			}
			
				
		}
		if (empty){
			if (javax.swing.SwingUtilities.isEventDispatchThread()){
				
	        	frame.setJTable(null);
	        	cleanpedidos();
			}else{
				Runnable _execute = new Runnable() {
			        public void run() {
			        	frame.setJTable(null);
						
			        	cleanpedidos();
			        }
				};
				invokeLater(_execute);	
			}
		}
		
		if (javax.swing.SwingUtilities.isEventDispatchThread()){
			frame.getJTable2().repaint();
		}else{
			Runnable _execute = new Runnable() {
		        public void run() {
		        	frame.getJTable2().repaint();
		        }
			};
			invokeLater(_execute);	
		}
		done=true;
	}
	
	
	
	/**
	 * @deprecated Use {@link #create_results_nuevos(Object[][])} instead
	 */
	private void create_results(Object[][] results_nuevos){
		create_results_nuevos(results_nuevos);
	}
	private void create_results_nuevos(Object[][] results_nuevos){
		boolean empty=true;
		System.out.println("CREATE RESULTS "+results_nuevos.length);
		
		if (results_nuevos!=null){
			String nombre=this.getSelectedClassName();
			if (results_nuevos.length>0){
				empty=false;
				if (javax.swing.SwingUtilities.isEventDispatchThread()){
					create_table_nuevos(results_nuevos);
				}else{
					final Object[][] _results=results_nuevos; 
					Runnable _execute = new Runnable() {
				        public void run() {
				        	create_table_nuevos(_results);
				        }
					};
					invokeLater(_execute);	
				}
				
			}
		}
		if (empty){
			String nombre=this.getSelectedClassName();
			
			if (javax.swing.SwingUtilities.isEventDispatchThread()){
				
				frame.setJTable(null);
				cleanpedidos();
			}else{
				Runnable _execute = new Runnable() {
			        public void run() {
			        	frame.setJTable(null);
						cleanpedidos();
			        }
				};
				invokeLater(_execute);	
			}
		}
	}
	
	public void check_user(){
		this.elegir_vendedor();
	}
	public void cerrar_trasnferencia(){
		transferencia.setVisible(false);
		transferencia.dispose();
	}
	private aplicacion.ventas.transferencia.constructor._Constructor transferencia;
	
	public void Transferencia(){
		if (frame.get_lst_modo().getSelectedIndex()==0){
			if (frame.getJTable()!=null){
				JTable table=frame.getJTable();
				int rows=0;
				for (int i=0;i<table.getRowCount();i++){
					boolean selected=false;
					try {
						selected=(Boolean) table.getValueAt(i, 0);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (selected){
						rows++;
					}
				}
				if (rows>0){
					this.dispose_transferencia();
					transferencia=new aplicacion.ventas.transferencia.constructor._Constructor();
					transferencia.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler().Clone());
					transferencia.setParameter(_parametros.iduser, this.getConstructor().getIduser());
					transferencia.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
					transferencia.setParameter(aplicacion.ventas.transferencia.interfaces._Parametros.gestion2, this.getConstructor());
					transferencia.build(this.getConstructor());
					transferencia.init();
					
				}else{
					error("Seleccione los pedidos para transferir");
					this.dispose_transferencia();		
				}
				
			}	
		}else{
			error("Esta Opcion Esta Disponible Para sus pedidos unicamente");
			this.dispose_transferencia();
		}
		
			
	}
	
	
	
	
	private aplicacion.ventas.gestion2.gui._Categoria categoria;
	public void Categorizar(){
		if (frame.get_lst_modo().getSelectedIndex()==0){
			if (frame.getJTable()!=null){
				JTable table=frame.getJTable();
				int rows=0;
				for (int i=0;i<table.getRowCount();i++){
					boolean selected=false;
					try {
						selected=(Boolean) table.getValueAt(i, 0);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (selected){
						rows++;
					}
				}
				if (rows>0){
					if (this.categoria != null) {
						categoria.setVisible(false);
						categoria.dispose();
					}
					categoria = new aplicacion.ventas.gestion2.gui._Categoria();
					categoria.setName(_Interface._categoria);
					centrar_frame(categoria);
					categoria.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					categoria.addWindowListener(this.getConstructor().getWindowListener());
					
					categoria.get_chk_seleccionar().setName(
							_Interface._chk_seleccionar_pedidos_categoria);
					categoria.get_chk_seleccionar().addItemListener(
							this.getConstructor().getItemListener());
					
					categoria.get_btn_transferir().setActionCommand(_Interface._btn_confirmar_categoria);
					categoria.get_btn_transferir().addActionListener(this.getConstructor().getActionListener());
					categoria.get_btn_salir().setActionCommand(_Interface._btn_salir_categoria);
					categoria.get_btn_salir().addActionListener(this.getConstructor().getActionListener());
					this.buildTreeCategorizar();
					categoria.setVisible(true);
					categoria.requestFocus();
					categoria.requestFocusInWindow();
					categoria.setJTable(crear_table_pedidos());
				}else{
					error("Seleccione los pedidos para categorizar");
				}
				
			}	
		}else{
			error("Esta Opcion Esta Disponible Para sus pedidos unicamente");
			this.dispose_categoria();
		}
		
			
	}
	
	public String getRoll(String fecha,int months,int days,int hours,int minutes) {
		Date today = new Convertidor().getDateWithFormat(formato, formato, fecha);
		java.util.GregorianCalendar _date = new java.util.GregorianCalendar();
		_date.setTime(today);
		if (months!=0){
			_date.add(Calendar.MONTH, months);	
		}
		if (days!=0){
			_date.add(Calendar.DAY_OF_MONTH, days);	
		}
		if (hours!=0){
			_date.add(Calendar.HOUR_OF_DAY, hours);	
		}
		if (minutes!=0){
			_date.add(Calendar.MINUTE, minutes);	
		}
		// _today.add(Calendar.DAY_OF_YEAR, days);
		Date before = _date.getTime();
		String s2 = new Convertidor().getDateWithFormat(formato, before);
		return s2;
	}
	
	
	
	
	private JTable crear_table_pedidos() {
		JTable table=frame.getJTable();
		int rows=0;
		for (int i=0;i<table.getRowCount();i++){
			boolean selected=false;
			try {
				selected=(Boolean) table.getValueAt(i, 0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (selected){
				rows++;
			}
		}
		Object[][] results = new Object[rows][6];
		rows=0;
		for (int i=0;i<table.getRowCount();i++){
			boolean selected=false;
			try {
				selected=(Boolean) table.getValueAt(i, 0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (selected){
				results[rows][0]=true;
				results[rows][1]=table.getValueAt(i, 1).toString();
				results[rows][2]=table.getValueAt(i, 2).toString();
				results[rows][3]=table.getValueAt(i, 3).toString();
				results[rows][4]=table.getValueAt(i, 4).toString();
				results[rows][5]=table.getValueAt(i, 5).toString();
				rows++;
			}
		}
		return this.create_table_pedidos(results);
	}
	
	/**
	 * chk|idarticulo|descripcion|cant
	 * 
	 * @param results
	 */
	private JTable create_table_pedidos(Object[][] results) {
		_Constructor constructor = (_Constructor) this.getConstructor();
		CustomTable table = new CustomTable();
		CellEditor pce = null;
		CheckBoxCellEditor cbce = null;

		table.setSortable(false);
		Column col = null;

		col = new Column();
		col.setName("");
		col.setWidth(30);
		col.setEditable(true);
		col.setClass(Boolean.class);
		cbce = new CheckBoxCellEditor();
		cbce.setItemListener(constructor.getItemListener());
		col.setCellEditor(cbce.getCellCheck());
		table.addColumn(col);

		col = new Column();
		col.setName("idpedido");
		col.setWidth(80);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);

		col = new Column();
		col.setName("descripcion");
		col.setWidth(210);
		col.setEditable(false);
		col.setAligment(JTextField.LEFT);
		table.addColumn(col);

		col = new Column();
		col.setName("cuenta");
		col.setWidth(90);
		col.setEditable(false);
		col.setClass(String.class);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);

		col = new Column();
		col.setName("Nombre");
		col.setWidth(160);
		col.setEditable(false);
		col.setClass(String.class);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);

		col = new Column();
		col.setName("Nivel");
		col.setWidth(44);
		col.setEditable(true);
		col.setAligment(JTextField.RIGHT);
		ComboBoxEditor cb=new ComboBoxEditor();
		String[] values=new String[10];
		for (int i=0;i<values.length;i++){
			values[i]=""+i;
		}
		cb.setValues(values);
		
		col.setCellEditor(cb.getCellEditor());
		table.addColumn(col);
		
		table.setData(results);
		table.addKeyListener(this._constructor.getKeyListener());
		Font fuente = new Font("Dialog", Font.PLAIN, 10);
		table.setHeaderFont(fuente);
		table.setFont(fuente);
		table.build();
		table.fillData();

		JTable _table = table.getTable();

		return _table;
	}

	public void dispose_transferencia(){
		if (transferencia!=null){
			this.transferencia.dispose();	
		}
		
	}
	public void dispose_categoria(){
		if (categoria!=null){
			this.categoria.dispose();	
		}
		
	}
	
	
	public void drop(){
		JTable table=categoria.getJTable();
		String idcategoria=this.getSelectedClass(categoria.getJTree());
		List<String> pedidos=new ArrayList<String>();
		for (int i=0;i<table.getRowCount();i++){
			boolean selected=false;
			try {
				selected=(Boolean) table.getValueAt(i, 0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (selected){
				String idpedido=table.getValueAt(i, 1).toString();
				pedidos.add(idpedido);
			}
		}
		if (pedidos.size()>0){
			this.change(pedidos, idcategoria);	
		}
	}
	
	public void drop(String flavor,Point p){
		int n=1;
		System.out.println ("data="+flavor);
		String tmp="";
		List<String> pedidos=new ArrayList<String>();
		String idclasificacion=this.getClassName(p);
		int columns=frame.getJTable().getColumnCount();
		System.out.println("Row Count of Table> "+frame.getJTable().getColumnCount());
		while (flavor.indexOf("	")>=0){
			tmp=flavor.substring(0,flavor.indexOf("	"));
			flavor=flavor.substring(flavor.indexOf("	")+1,flavor.length());
			if (n==2){
				if (idclasificacion.compareTo("")!=0){
					System.out.println("si tiene permiso. "+tmp+" -> "+idclasificacion);
					pedidos.add(tmp);
				}else{
					if (idclasificacion.compareTo("0")==0){
						System.out.println("si tiene permiso. Desclasifica? "+tmp+" -> "+idclasificacion);
						pedidos.add(tmp);
					}
				}		
			}
			if (n>=columns-1){
				n=1;
			}else{
				n++;	
			}
		}
		if (pedidos.size()>0){
			this.change(pedidos, idclasificacion);	
		}
	}
	
	public boolean change(List<String>pedidos,String idclasificacion){
		boolean ok=true;
		if (pedidos.size()>0){
			String idvendedor = this.validar_vendedor();
			String old=frame.get_txt_idvendedor().getText();
			if (idvendedor.compareTo(old)==0){
				data.beginTransaction();
				data.clearBatch();
				for (int i=0;i<pedidos.size();i++){
					String q="";
					if (data.existeCategoria(pedidos.get(i), idvendedor)){
						q=data.updateCategoria(pedidos.get(i), idvendedor, idclasificacion);
					}else{
						q=data.insertCategoria(pedidos.get(i), idvendedor, idclasificacion,"0");
					}
					System.out.println(q);
					data.addBatch(q);
				}
				boolean error=data.executeBatch();
				if(!error){
					data.commitTransaction();
					if (this.categoria!=null){
						this.dispose_categoria();
					}
					aviso("El cambio se Realizo Correctamente");
					this.goCargar();
				}else{
					ok=false;
					data.rollbackTransaction();
					error("Error Grabando");	
				}
				
			}else{
				error("Usuario Invalido");
			}
		}
		return ok;
	}
	public void deleteFromTable(String idpedido){
		JTable table=frame.getJTable();
		if (table!=null){
			int i=0;
			boolean found=false;
			while (i<table.getRowCount() & !found){
				found=table.getValueAt(i, 1).toString().compareTo(idpedido)==0;
				if (!found)i++;
			}
			if (found){
				DefaultTableModel model=(DefaultTableModel) table.getModel();
				model.removeRow(i++);
			}
		}
	}
	
	public void cargarModos(){
		frame.get_lst_modo().removeItemListener(this.getConstructor().getItemListener());
		frame.get_lst_modo().removeAllItems();
		frame.get_lst_modo().addItem("Mis Pedidos");
		frame.get_lst_modo().addItem("Transferencias");
		frame.get_lst_modo().addItemListener(this.getConstructor().getItemListener());
	}
	
	public void processDND(DropTargetDropEvent dtde){
		System.out.println ("drop");
		dtde.acceptDrop (DnDConstants.ACTION_COPY_OR_MOVE);
		
		Transferable trans = dtde.getTransferable( );
		System.out.println ("Flavors:"); 
		boolean gotData = false;
		try {
			// try to get an image
			if (trans.isDataFlavorSupported (DataFlavor.stringFlavor)) { 
				System.out.println ("image flavor is supported"); 
				String flavor = (String) trans.getTransferData (DataFlavor.stringFlavor);
				//showImageInNewFrame (img);
				drop(flavor,dtde.getLocation());
				gotData = true;
			} else if (trans.isDataFlavorSupported (
				DataFlavor.javaFileListFlavor)) {
				System.out.println ("javaFileList is supported");
				java.util.List list = (java.util.List)
				trans.getTransferData (DataFlavor.javaFileListFlavor);
				ListIterator it = list.listIterator( );    
				while (it.hasNext( )) {
				File f = (File) it.next( );
				System.out.println("Adding Foto DND >"+f.getAbsolutePath());
				
				
				}
				gotData = true;
			} else if (trans.isDataFlavorSupported (uriListFlavor)) {
				System.out.println ("uri-list flavor is supported"); 
				String uris = (String)
				trans.getTransferData (uriListFlavor);

				// url-lists are defined by rfc 2483 as crlf-delimited 
				StringTokenizer izer = new StringTokenizer (uris, "\r\n");   
				while (izer.hasMoreTokens ( )) {
				String uri = izer.nextToken( );
				System.out.println (uri);
				
				
				}
				gotData = true;
			} else if (trans.isDataFlavorSupported (urlFlavor)) {
				System.out.println ("url flavor is supported");
				URL url = (URL) trans.getTransferData (urlFlavor);
				System.out.println (url.toString( ));
				
				System.out.println (url);
				
				gotData = true;
			}
		} catch (Exception e) {
			e.printStackTrace( );
		} finally {
			System.out.println ("gotData is " + gotData);
			dtde.dropComplete (gotData);
		}
	}

	public void cleanpedidos(){
		frame.setJTable1(null);
		
		String nombre=this.getSelectedClassName();
		
		frame.get_txt_idcliente().setText("");
		frame.get_txt_cliente_descripcion().setText("");
		
		frame.get_txt_idarticulo().setText("");
		frame.get_txt_idpedido().setText("");
		
		frame.get_txt_idarticulo_descripcion().setText("");
		frame.get_txt_idarticulo_linea().setText("");
		frame.get_txt_pedido_descripcion().setText("");
		frame.get_txt_pedido_informacion().setText("");
		frame.get_txt_pedido_remito().setText("");
		frame.get_txt_c_cuenta().setText("");
		frame.get_txt_c_descripcion().setText("");
		frame.get_txt_c_fecha().setText("");
		frame.get_txt_c_idcomprobante().setText("");
		frame.get_txt_c_nombre().setText("");
		
		frame.get_txt_c_total().setText("");
		frame.get_txt_informacion().setText("");
		done=true;
	}
	
	
	public double getControlDias(String agenda,String creado) {
		double dias=0;
		if (agenda.compareTo(creado)!=0){
			dias=2;
		}
		return dias;
	}
	public double getControlCalendar(int row, int col) {
		double perc = 0.0;
		if (frame.getJTable2()!=null){
			String day="";
			try {
				day=frame.getJTable2().getValueAt(row, col).toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			if (day.compareTo("")!=0){
				if (colors!=null){
					for (int i=0;i<colors.length;i++){
						String tmp=colors[i][0].toString();
						if (tmp.compareTo(day)==0){
							perc=(Double)colors[i][1];
						}
					}	
				}
				
			}	
		}
		
		
		return perc;
	}

	public void Editar(int row,int col,JTable table){
		
	}
	
	public void load_colors(){
		int mes = frame.get_list_mes().getSelectedIndex()+1;
		int anio = new Integer(frame.get_list_anio().getSelectedItem().toString());
		String desde = "";
		String hasta = "";
		int days = this.get_days();
		desde="01-"+mes+"-"+anio;
		hasta=days+"-"+mes+"-"+anio;
		String idcategoria=this.getSelectedClass();
		
		System.out.println("pidiendo fechas "+desde+" <> "+hasta);
		colors=this.getControl(desde, hasta,idcategoria);
	}
	
	
	
	private Object[][] getControl(String desde, String hasta,String idcategoria){
		Object[][] cargas=null;
		
		String q=data.getPedidosColor(desde, hasta);
		if (frame.get_lst_modo().getSelectedIndex()==1){
			q=data.getPedidosTransferenciaColor(desde, hasta);	
		}
		if (idcategoria.compareTo("")==0){
			idcategoria=this.getSelectedClass();
		}
		String acumulated=this.getAcumulatedQuery(idcategoria);
		if (acumulated.length()>0){
			q+=" and "+acumulated+" ";
		}
		q+="group by day(case when pedido.fecha_agenda is null then pedido.fecha_creacion else pedido.fecha_agenda end) ";
		q+="order by day(case when pedido.fecha_agenda is null then pedido.fecha_creacion else pedido.fecha_agenda end)";
		System.out.println("color>"+q);
	
		Object[][] results = data.getResults(q);
		
		if (results != null) {
			if (results.length > 0) {
				cargas=new Object[results.length][2];
				for(int i=0;i<results.length;i++){
					String _carga = (String) results[i][1];
					String dia=(String) results[i][0];
					System.out.println("dia "+dia+" carga="+_carga);
					double carga = 0.0;
					try {
						carga = new Double(_carga);
					} catch (Exception e) {

					}
					
					if(carga>255){
						carga=255;
						
					}else{
						if (carga<50){
							if (carga>0){
								carga=50;
							}
						}
					}
					cargas[i][1]=carga;
					cargas[i][0]=dia;
				}

				
			}
		}
		
		return cargas;
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

	public void buscarProveedor(JTextField tx) {
		if (vProveedor != null) {
			vProveedor.close();
		}
		vProveedor = new aplicacion.herramientas.java.visualizadores.Proveedor(
				this.getConstructor());
		int n = vProveedor.Buscar(tx);
		if (n == 0) {
			aviso("no hay Proveedors con ese codigo");
		}
	}
	
	public void cargar_pedido(String idpedido){
		//this.clean();
		frame.get_txt_idpedido().setText(idpedido);
		//frame.get_txt_idpedido().setEditable(false);
		
		Object[][] results=data.getPedido(idpedido);
		if (results!=null){
			if (results.length>0){
				String descripcion=(String) results[0][0];
				frame.get_txt_pedido_descripcion().setText(descripcion);
			}
		}
	}
	
	
	
	public void show_pedido_nuevos(int row){
		if (frame.getJTable()!=null){
			if (row>=0 & row<frame.getJTable().getRowCount()){
				String pedido=frame.getJTable().getValueAt(row, 1).toString();
				show_pedido(pedido);
				this.cargar_items(pedido);
			}	
		}
	}
	public void show_pedido(String idpedido){
		
		frame.get_txt_c_idcomprobante().setText(idpedido);
		
		
		Object[][] results=data.getPedido(idpedido);
		if (results!=null){
			if (results.length>0){
				String descripcion=(String) results[0][0];
				String fecha_agenda=(String) results[0][1];
				String fecha_creacion=(String) results[0][2];
				String fecha_modificacion=(String) results[0][3];
				String cliente=(String)results[0][4];
				String cliente_descripcion=(String)results[0][5];
				String info=(String)results[0][6];
				String idvendedor=(String)results[0][7];
				String vendedor=(String)results[0][8];
				String total=(String)results[0][13];
				frame.get_txt_c_fecha().setText(fecha_agenda);
				frame.get_txt_informacion().setText(info);
				frame.get_txt_c_descripcion().setText(descripcion);
				frame.get_txt_c_cuenta().setText(cliente);
				frame.get_txt_c_nombre().setText(cliente_descripcion);
				frame.get_txt_c_total().setText(total);
			}
		}
	}
	private aplicacion.herramientas.java.evaluadores.PedidoCliente PDC=null;
	public void initialize_PDC(){
		PDC=new aplicacion.herramientas.java.evaluadores.PedidoCliente(){
			public void cargar(String codigo){
				cargar_pedido(codigo);
			}
		};
		PDC.setConstructor(this.getConstructor());
	}
	public void BuscarPDC(JTextField tx){
		PDC.Buscar(tx);
	}
	public void BuscarPDC(){
		PDC.Buscar(frame.get_txt_idpedido());
	}
	public void buscarPDC(JTextField tx){
		PDC.buscar(tx);
	}
	
	public void evaluarPDC(JTextField tx){
		PDC.evaluate(tx);
	}
	
	private void cargarCliente(String idcliente){
		Object[][] results=this.data.getCliente(idcliente);
		if (results!=null){
			if (results.length>0){
				//frame.get_txt_idcliente().setEditable(false);
				String descripcion=results[0][1].toString();
				frame.get_txt_cliente_descripcion().setText(descripcion);
				
				if (idcliente.compareTo("112010001")==0){
					frame.get_txt_cliente_descripcion().setEditable(true);
					frame.get_txt_cliente_descripcion().requestFocusInWindow();
					frame.get_txt_cliente_descripcion().setSelectionStart(0);
					frame.get_txt_cliente_descripcion().setSelectionEnd(descripcion.length());
				}else{
					

					
				}
				
				this.show_with_filter(false);
				
			}
		}
	}
	private aplicacion.herramientas.java.evaluadores.Cliente cliente=null;
	public void initialize_cliente(){
		cliente=new aplicacion.herramientas.java.evaluadores.Cliente(){
			public void cargar(String codigo){
				cargarCliente(codigo);
			}
		};
		cliente.setConstructor(this.getConstructor());
	}
	public void BuscarCliente(JTextField tx){
		cliente.Buscar(tx);
	}
	public void buscarCliente(JTextField tx){
		cliente.buscar(tx);
	}
	
	public void BuscarCliente(){
		cliente.Buscar(frame.get_txt_idcliente());
	}
	public void evaluarCliente(JTextField tx){
		cliente.evaluate(tx);
	}


	private aplicacion.herramientas.java.evaluadores.Vendedor Vendedor=null;
	public void initialize_Vendedor(){
		
		Vendedor=new aplicacion.herramientas.java.evaluadores.Vendedor(){
			public void cargar(JTextField tx){
				String codigo=tx.getText();
				Object[][] results=this.getInfo(codigo);
				String descripcion=(String) results[0][1];
				String cod=(String) results[0][0];
				frame.get_txt_idvendedor().setText(cod);
				frame.get_txt_vendedor_descripcion().setText(descripcion);	
					
						
				
				
				
				
				
			}
		};
		Vendedor.setConstructor(this.getConstructor());
	}
	public void BuscarVendedor(JTextField tx){
		Vendedor.Buscar(tx);
	}
	public void BuscarVendedor(){
		Vendedor.Buscar(frame.get_txt_idvendedor());
	}
	public void buscarVendedor(JTextField tx){
		Vendedor.buscar(tx);
	}
	
	public void evaluarVendedor(JTextField tx){
		tx.setText(tx.getText().replaceAll(" ", ""));
		Vendedor.evaluate(tx);
	}
	
	
	
 	private aplicacion.herramientas.java.evaluadores.Articulo Articulo=null;
	public void initialize_Articulo(){
		Articulo=new aplicacion.herramientas.java.evaluadores.Articulo(){
			public void cargar(String codigo){
				Object[][] results=this.getInfo(codigo);
				String descripcion=(String) results[0][1];
				frame.get_txt_idarticulo_descripcion().setText(descripcion);
			}
		};
		Articulo.setConstructor(this.getConstructor());
	}
	public void BuscarArticulo(JTextField tx){
		Articulo.Buscar(tx);
	}
	public void BuscarArticulo(){
		Articulo.Buscar(frame.get_txt_idarticulo());
	}
	public void buscarArticulo(JTextField tx){
		Articulo.buscar(tx);
	}
	
	public void evaluarArticulo(JTextField tx){
		Articulo.evaluate(tx);
	}
	
	private aplicacion.herramientas.java.evaluadores.Linea Linea=null;
	public void initialize_Linea(){
		Linea=new aplicacion.herramientas.java.evaluadores.Linea(){
			public void cargar(String codigo){
				Object[][] results=this.getInfo(codigo);
				String descripcion=(String) results[0][1];
				frame.get_txt_idarticulo_linea().setText(descripcion);
			}
		};
		Linea.setConstructor(this.getConstructor());
	}
	public void BuscarLinea(JTextField tx){
		Linea.Buscar(tx);
	}
	public void BuscarLinea(){
		Linea.Buscar(frame.get_txt_idarticulo_linea());
	}
	public void buscarLinea(JTextField tx){
		Linea.buscar(tx);
	}
	
	public void evaluarLinea(JTextField tx){
		Linea.evaluate(tx);
	}
	public void cargar_items(String idpedido){
		Object[][] results=data.getPedidoItems(idpedido);
		
		if (results!=null){
			if (results.length>0){
				for (int i=0;i<results.length;i++){
					results[i][0]=((String) results[i][0]).compareTo("1")==0;
					
					results[i][1]=(String) results[i][1];
					results[i][2]=(String) results[i][2];
					results[i][3]=(String) results[i][3];
					results[i][4]=(String) results[i][4];
					results[i][5]=(String) results[i][5];
					
				}
				this.create_table_items(results);
			}else{
				frame.setJTable1(null);
			}
		}else{
			frame.setJTable1(null);	
		}
		
	}
	
private void create_table_items(Object[][] results) {
		
		CustomTable table = new CustomTable();
		table.setSortable(false);
		Column col = null;
		
		col = new Column();
		col.setName("");
		col.setWidth(30);
		col.setEditable(false);
		CheckBoxCellEditor chkce = new CheckBoxCellEditor();
		chkce.setItemListener(this._constructor.getItemListener());
		chkce.setTipo(Boolean.class);
		col.setCellEditor(chkce.getCellCheck());
		
		col.setClass(Boolean.class);
		table.addColumn(col);

		col = new Column();
		col.setName("idarticulo");
		col.setWidth(100);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);

		col = new Column();
		col.setName("descripcion");
		col.setWidth(380);
		col.setEditable(false);
		col.setAligment(JTextField.LEFT);
		table.addColumn(col);

		col = new Column();
		col.setName("cant");
		col.setWidth(46);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);


		col = new Column();
		col.setName("precio");
		col.setWidth(70);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);
		
		

		col = new Column();
		col.setName("Total");
		col.setWidth(80);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);

		
		col = new Column();
		col.setName("Linea");
		col.setWidth(160);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);
		
		table.setData(results);
		table.addKeyListener(this.getConstructor().getKeyListener());
		table.addMouseListener(this._constructor.getMouseListener());
		Font fuente = new Font("Dialog", Font.PLAIN, 10);
		table.setHeaderFont(fuente);
		table.setFont(fuente);
		
		table.build();
		
		table.fillData();

		JTable _table = table.getTable();
		frame.setJTable1(_table);
	}

	public void show_with_filter(boolean b){
		frame.get_chk_utiliza_calendario().setSelected(b);
		String	idcategoria=this.getSelectedClass();
		this.goCargar(idcategoria);
	}
	
	public String getAcumulatorQuery(int i){
		
		Object[] tmp=this.acumulators.get(i);
		List<String> values=(List<String>)tmp[1];
		JTextField tx=(JTextField)tmp[0];
		String column=(String) tmp[2];
		String description="";
		if (values.size() > 0) {
			int j = 0;
			while (j < values.size()) {
				String flt = values.get(j);
				String aux = flt;
				while (aux.contains(" ")) {
					String sub = aux.substring(0, aux.indexOf(" "));
					if (sub.compareTo("") != 0) {
						if (description.length() > 0) {
							description = description + " and ";
						}
						description = description + " " + column + " like '%"
								+ sub + "%'";
					}
					aux = aux.substring(aux.indexOf(" ") + 1);
				}
				if (aux.compareTo("") != 0) {
					if (description.length() > 0) {
						description = description + " and ";
					}
					description = description + "  " + column + " like '%"
							+ aux + "%'";
				}

				j++;
			}
		}

		return description;
	}
	public String getAcumulatedQuery(String filter){
		int modo=frame.get_lst_modo().getSelectedIndex();
		String q="";
		if (acumulators!=null){
			for (int i=0;i<this.acumulators.size();i++){
				String acumulator=this.getAcumulatorQuery(i);
				if (acumulator.length()>0){
					if (q.length()>0){
						q+="and ";
					}
					q+=acumulator+" ";
				}
			}	
		}
		String iduser=frame.get_txt_idvendedor().getText();
		if (iduser.compareTo("")!=0){
			if (q.length()>0){
				q+=" and ";
			}
			if (modo==0){
				q+=" isnull(pedido.idvendedor,pedido.idcreador) like '"+iduser+"' ";	
			}
			if (modo==1){
				q+=" transferencia.iduser_origen = '"+iduser+"' ";	
			}
			if (modo==2){
				q+=" transferencia.iduser_destino = '"+iduser+"' ";	
			}
		}
		
		if (filter.compareTo("")!=0){
			
			if (filter.compareTo("0")!=0){
				if (filter.compareTo("0-0")==0){
					if (q.length()>0){
						q+="and ";
					}
					q+=" isnull(categorias.idcategoria,'0-0') like '"+filter+"' ";
				}else{
					if (q.length()>0){
						q+="and ";
					}
					q+=" isnull(categorias.idcategoria,'') like '"+filter+"%' ";	
				}
					
			}
			
		}
		return q;
	}
	
	public void focus(){
		frame.get_txt_idcliente().requestFocusInWindow();
	}
	public void doclean(){
		acumulators=new ArrayList<Object[]>();
		this.load_estados();
		frame.setJTable2(null);
		frame.setJTable1(null);
		frame.setJTable(null);
		frame.get_chk_utiliza_calendario().setSelected(true);
		frame.get_txt_idcliente().setText("");
		frame.get_txt_cliente_descripcion().setText("");
		
		frame.get_txt_idarticulo().setText("");
		frame.get_txt_idpedido().setText("");
		
		frame.get_txt_idarticulo_descripcion().setText("");
		frame.get_txt_idarticulo_linea().setText("");
		frame.get_txt_pedido_descripcion().setText("");
		frame.get_txt_pedido_informacion().setText("");
		frame.get_txt_pedido_remito().setText("");
		frame.get_txt_c_cuenta().setText("");
		frame.get_txt_c_descripcion().setText("");
		frame.get_txt_c_fecha().setText("");
		
		frame.get_txt_c_idcomprobante().setText("");
		frame.get_txt_c_nombre().setText("");
		
		frame.get_txt_c_total().setText("");
		
		this.elegir_vendedor();
	
	}
	
	public void editar_pedido(){
		String idpedido=frame.get_txt_c_idcomprobante().getText();
		if (idpedido.compareTo("")!=0){
			this.editar_pedido(idpedido);
		}
	}
	public void editar_pedido_nuevo(int row){
		if (frame.getJTable()!=null){
			if (row>=0 & row<frame.getJTable().getRowCount()){
				String idpedido=frame.getJTable().getValueAt(row, 1).toString();
				show_pedido(idpedido);
				editar_pedido(idpedido);
			}
			
		}
	}
	
	public void editar_pedido(String idpedido){
		aplicacion.ventas.pedido.constructor._Constructor
		pedido=new aplicacion.ventas.pedido.constructor._Constructor();
		pedido.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler().Clone());
		pedido.setParameter(_parametros.iduser, this.getConstructor().getIduser());
		pedido.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
		pedido.setParameter(aplicacion.ventas.pedido.interfaces._parametros._idpedido, idpedido);
		pedido.build(this.getConstructor());
		pedido.init();
		this.getConstructor().addChild(pedido);
	}
	
	public void nuevo_pedido(){
		aplicacion.ventas.pedido.constructor._Constructor
		pedido=new aplicacion.ventas.pedido.constructor._Constructor();
		pedido.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler().Clone());
		pedido.setParameter(_parametros.iduser, this.getConstructor().getIduser());
		pedido.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
		pedido.build(this.getConstructor());
		pedido.init();
		this.getConstructor().addChild(pedido);
		
		
		
	}
	
	public void consultar(){
		aplicacion.ventas.catalogo.constructor._Constructor
		consulta=new aplicacion.ventas.catalogo.constructor._Constructor();
		consulta.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler().Clone());
		consulta.setParameter(_parametros.iduser, this.getConstructor().getIduser());
		consulta.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
		consulta.build(this.getConstructor());
		consulta.init();
	}
	
	
	private List<Object[]> acumulators=null;
	public int getAcumulator(JTextField tx){
		int i=0;
		boolean found=false;
		while(i<acumulators.size() & !found){
			Object[] tmp=acumulators.get(i);
			JTextField atx=(JTextField)tmp[0];
			found=(atx.getName()==tx.getName());
			if(!found)i++;
		}
		if (!found)i=-1;
		return i;
	}
	
	public void acumulate(){
		this.addAcumulator(frame.get_txt_pedido_descripcion(),"pedido.descripcion");
		this.addAcumulator(frame.get_txt_idpedido(),"pedido.idpedido");
		this.addAcumulator(frame.get_txt_idcliente(), "pedido.cliente");
		this.addAcumulator(frame.get_txt_cliente_descripcion(), "pedido.cliente_descripcion");
		//this.addAcumulator(frame.get_txt_idvendedor(), "pedido.idvendedor");
		//this.addAcumulator(frame.get_txt_vendedor_descripcion(), "vendedor.nombre");
		this.addAcumulator(frame.get_txt_idarticulo(),"items.idarticulo");
		this.addAcumulator(frame.get_txt_idarticulo_descripcion(),"items.descripcion");
		this.addAcumulator(frame.get_txt_idarticulo_linea(),"articulo.descripabrev");
		this.addAcumulator(frame.get_txt_pedido_informacion(),"pedido.datos_extra");
		this.addAcumulator(frame.get_txt_pedido_remito(),"remitos.remito");
		//this.addAcumulator(frame.get_txt_idcreador(), "pedido.idcreador");
		this.addAcumulator(frame.get_txt_creador(), "creador.nombre");
	}
	public void load_estados(){
		frame.get_lst_estado().removeItemListener(this.getConstructor().getItemListener());
		frame.get_lst_estado().removeAllItems();
		frame.get_lst_estado().addItem("Todos");
		frame.get_lst_estado().addItem("");
		frame.get_lst_estado().addItem("Alfa");
		frame.get_lst_estado().addItem("Beta");
		frame.get_lst_estado().addItem("RM");
		
		frame.get_lst_estado().addItemListener(this.getConstructor().getItemListener());
		
	}
	public void addAcumulator(JTextField tx,String column){
		
		int n=this.getAcumulator(tx);
		List<String> values=new ArrayList<String>();
		Object[] tmp=null;
		
		if (n>=0){
			tmp=this.acumulators.get(n);
			values=(List<String>)tmp[1];
			
		}else{
			tmp=new Object[]{tx,values,column};
		}
		values.add(tx.getText());
		System.out.println("column:"+column+" search>"+tx.getName()+" ("+values.toString()+")");
		tx.setText("");
		tmp[0]=tx;
		if (n>=0){
			acumulators.set(n, tmp);	
		}else{
			acumulators.add(tmp);
		}
		
	}
	
	public void initial(){
		if (data!=null){
			if (data.getIsSuperUser(this.getConstructor().getIduser())){
				frame.get_chk_eliminados().setEnabled(true);
			}else{
				frame.get_chk_eliminados().setEnabled(false);
			}	
		}
		
	}
	
	
	
	public void removeCurrentNode() {
		
		if (frame.getJTree()!=null){
			
			TreePath currentSelection = frame.getJTree().getSelectionPath();
		    if (currentSelection != null) {
		    	Nodo currentNode = (Nodo)(currentSelection.getLastPathComponent());
		    	
		        Nodo parent = (Nodo)(currentNode.getParent());
		        if (parent != null) {
		        	if (currentNode.isLeaf()){
		        		if (!currentNode.isRoot()){
		        			DefaultTreeModel treeModel =(DefaultTreeModel) frame.getJTree().getModel();
		        			if (treeModel!=null){
		        				
		        				boolean ok=eliminar_clasificacion(currentNode,parent);
		        				if (ok)treeModel.removeNodeFromParent(currentNode);
		        				
		        			}
		        			
		                    
		        			
		                    
		        		}else {
		        			//JOptionPane.showMessageDialog(this, "No se puede Eliminar la raiz!");
		        			error("No se puede Eliminar la raiz!");
		        		}
		        	}else {
		        		error("Debe Eliminar las Sub-Clasificaciones ");
		        	}
		        }
		    }	
		}
	     

	    // Either there was no selection, or the root was selected.
	    //toolkit.beep();
	}
	

	public DefaultMutableTreeNode addObject(Nodo parent,
	                                        Object child,String idcategoria,String color) {
	    return addObject(parent, child, false,idcategoria,color);
	}
	
	public void select(TreePath Selection){
		String iduser=frame.get_txt_idvendedor().getText();
		
		if (Selection != null) {
	    	Nodo Node = (Nodo)(Selection.getLastPathComponent());
	    	String clasificacion=Node.getValue();
	    	
	    	this.goCargar(clasificacion);
	    	
	    		
		}
	}
	
	
	
	
	private void loadToMemory(){
		String iduser=frame.get_txt_idvendedor().getText();
		String q="select id,clasificacion,padre,background from b_users_categorias where iduser like '"+iduser+"' order by padre ,clasificacion ";
		Object[][] results=data.getResults(q);
		memory=null;
		if (results!=null){
			if (results.length>0){
				System.out.println("Clasificaciones encontradas? "+results.length);
				memory=new String[results.length][results[0].length];
				for (int i=0;i<results.length;i++){
					for (int j=0;j<results[0].length;j++){
						memory[i][j]=(String) results[i][j];	
					}
					
				}
			}
		}
		
	}
	
	public String getClassName(Point p){
		
		Nodo parentNode = null;
	    try {
			TreePath parentPath = frame.getJTree().getPathForLocation(p.x, p.y);
			if (parentPath!=null){
				DefaultTreeModel treeModel = (DefaultTreeModel) frame.getJTree().getModel();
				if (parentPath == null) {
				    parentNode = (Nodo)treeModel.getRoot();
				} else {
				    parentNode = (Nodo)(parentPath.getLastPathComponent());
				}	
			}
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String clas_p="";
		if (parentNode!=null){
			try {
				clas_p=(String)parentNode.getValue();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		return clas_p;
	}
	public void buildTree(){
		Nodo raiz=new Nodo("Pedidos","0");
		
		
		DefaultTreeModel treeModel = new DefaultTreeModel(raiz);
		treeModel.addTreeModelListener(this.getConstructor().getTreeModelListener());
		
		JTree tree = new JTree(treeModel);
		
        tree.setEditable(false);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.addTreeSelectionListener(this.getConstructor().getTreeSelectionListener());
        tree.setShowsRootHandles(true);
        tree.setName(_Interface._tree);
        tree.addMouseListener(this.getConstructor().getMouseListener());
        tree.addKeyListener(this.getConstructor().getKeyListener());
        this.loadToMemory();
        
        final Nodo _raiz=raiz;
        
        dropTarget = new DropTarget (tree,this.getConstructor().getDropTargetListener());
        final JTree _tree=tree;
        
        Runnable _execute=new Runnable(){
        	public void run(){
        		load_clases_from_memory(_raiz);
        		frame.setJTree(_tree);
        	}
        };
        this.invokeLater(_execute);
        
        
        //this.load_clases(raiz);
        
	}
	
	public void buildTreeCategorizar(){
		Nodo raiz=new Nodo("Pedidos","0");
		
		
		DefaultTreeModel treeModel = new DefaultTreeModel(raiz);
		treeModel.addTreeModelListener(this.getConstructor().getTreeModelListener());
		JTree tree = new JTree(treeModel);
		
        tree.setEditable(false);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.addTreeSelectionListener(this.getConstructor().getTreeSelectionListener());
        tree.setShowsRootHandles(true);
        
        tree.addMouseListener(this.getConstructor().getMouseListener());
        tree.addKeyListener(this.getConstructor().getKeyListener());
        this.loadToMemory();
        
        final Nodo _raiz=raiz;
        
        
        final JTree _tree=tree;
        
        Runnable _execute=new Runnable(){
        	public void run(){
        		load_clases_from_memory(_raiz);
        		categoria.setJTree(_tree);
        	}
        };
        this.invokeLater(_execute);
        
        
        //this.load_clases(raiz);
        
	}
	public void Recodificar(){
	   	 TreePath currentSelection = frame.getJTree().getSelectionPath();
	   	 String color=propiedades.get_txt_color().getText();
	        if (currentSelection != null) {
	        	Nodo currentNode = (Nodo)(currentSelection.getLastPathComponent());
	        	Nodo parent=(Nodo) currentNode.getParent();
	        	String aux="";
	        	if (parent!=null){
	        		aux=parent.getValue();
	        		System.out.println(aux);
	        		String valx=aux.replace("-", "");
	        		String act=currentNode.getValue();
	        		act=act.replace(valx, "");
	        		aux=aux+"-"+act;
	        	}
	        	String clasx=JOptionPane.showInputDialog("Ingrese Nuevo Codigo para ("+currentNode.getValue()+") "+currentNode.getUserObject().toString(),aux);
	        	
	        	if (clasx!=null){
	        		if (preguntar("Confirmar","Confirma Recodificacion de "+currentNode.getValue()+" a "+clasx)){
	        			System.out.println("Recodificar "+currentNode.getValue()+" a "+clasx);
	            		boolean error=recodificar(currentNode.getValue(),clasx,color);	
	            		if (!error){
	             			currentNode.setValue(clasx);
	             			DefaultTreeModel treeModel =(DefaultTreeModel) frame.getJTree().getModel(); 
	             			treeModel.reload(currentNode);
	             		}	
	        		}
	        		
	        		
	        	}
	        }
	   }
	
	private boolean existe(String id){
		String iduser=frame.get_txt_idvendedor().getText();
		boolean existe=data.exist(id,iduser);
		return existe;
	}
	
	public int get_Clasificacion(String clasificacion,String padre){
		int _clas=0;
		String iduser=frame.get_txt_idvendedor().getText();
		String q=data.getId(clasificacion, padre,iduser);
		Object[][] results=data.getResults(q);
		if (results!=null){
			if (results.length>0){
				_clas=new Integer((String)results[0][0]);
			}
		}
		return _clas;
	}
	
	public boolean recodificar(String oldcode,String newcode,String color){
		String iduser=frame.get_txt_idvendedor().getText();
    	String q1=data.getUpdate(newcode, oldcode,iduser,color);
    	String q2=data.getUpdatePadre(newcode, oldcode,iduser);
    	data.clearBatch();
    	System.out.println(q1);
    	System.out.println(q2);
    	data.addBatch(q1);
    	data.addBatch(q2);

    	boolean error=data.executeBatch();
    	if (!error){
    		aviso("Se Recodifico Correctamente");
    	}else {
    		error("Error Recodificando ");
    	}
    	return error;	
    }

public Nodo nueva_clasificacion(Nodo padre,Nodo clas,String color){
		
		if (clas!=null){
			int ch=padre.getChildCount();
			boolean ex=true;
			while (ex){
				ex=this.existe(padre.getValue()+"-"+ch);
				if (ex){
					ch++;	
				}
				
			}
			clas.setValue(padre.getValue()+"-"+ch);
			String iduser=frame.get_txt_idvendedor().getText();
			String q=data.getInsert(clas.getValue(),clas.getUserObject().toString(),padre.getValue(),iduser,color);
			System.out.println(q);
			data.beginTransaction();
			data.clearBatch();
			data.addBatch(q);
			boolean error=data.executeBatch();
			if (!error){
				data.commitTransaction();
				
			}else {
				data.rollbackTransaction();
				error("Error Creando Clasificacion");
				clas=null;
			}
			
		}
		return clas;
	}

public boolean eliminar_clasificacion(Nodo node,Nodo parent){
	int i=node.getChildCount();
	boolean ok=true;
	String idvendedor = this.validar_vendedor();
	String old=frame.get_txt_idvendedor().getText();
	if (idvendedor.compareTo(old)==0){
		String clas=(String)node.getUserObject();
		if (node.isLeaf()){
			
				if (preguntar("confirmar","Desea eliminar la clasificacion? ")){
					
					String q=data.getDelete(clas, parent.getValue(),idvendedor);
					System.out.println(q);
					data.beginTransaction();
					data.clearBatch();
					data.addBatch(q);
					q=data.updateCategorias(clas, idvendedor);
					data.addBatch(q);
					boolean error=data.executeBatch();
					if (!error){
						data.commitTransaction();
					}else{
						data.rollbackTransaction();
						ok=false;
					}
				}	
			
			
			
		}else {
			aviso("Debe eliminar primero las sub-clasificaciones!");
		}	
	}else{
		error("Error de Usuario");
		ok=false;
	}
	
	return ok;
}

public void renameCurrentNode() {
    //rootNode.removeAllChildren();
    //treeModel.reload();
	TreePath currentSelection = frame.getJTree().getSelectionPath();
    if (currentSelection != null) {
    	Nodo currentNode = (Nodo)(currentSelection.getLastPathComponent());
    	
        Nodo parent = (Nodo)(currentNode.getParent());
        this.initPropiedades();
        propiedades.get_txt_idclasificacion().setText(currentNode.getValue());
        propiedades.get_txt_nombre().setText(currentNode.getUserObject().toString());
        String color=this.getcolor(currentNode.getValue());
        Color _color=this.getColor(color);
        if (_color!=null){
        	propiedades.get_btn_color().setBackground(_color);
        }
        
        propiedades.get_txt_color().setText(color);
    }else{
    	error("Seleccione la clasificacion a modificar ");
    }
}

public Nodo renameCurrentNode(Nodo clas,String clasx,String color){
	if (clas!=null){
		
		if (clasx.compareTo("")!=0){
			String iduser=frame.get_txt_idvendedor().getText();
			
			String q=data.getUpdate(clasx, clas.getValue(),iduser,color);
			System.out.println(q);
			data.clearBatch();
			data.addBatch(q);
			boolean error=data.executeBatch();
			if (!error){
				DefaultTreeModel treeModel =(DefaultTreeModel) frame.getJTree().getModel();
				clas.setUserObject(clasx);
				treeModel.reload(clas);
			}else {
				clas=null;
			}	
		}
		
	}
	return clas;
}

public void addCategoria(){
	this.initPropiedades();
	propiedades.get_txt_nombre().requestFocusInWindow();
}

 
public void guardarCategoria(){
	TreePath currentSelection = frame.getJTree().getSelectionPath();
    if (currentSelection != null) {
    	Nodo currentNode = (Nodo)(currentSelection.getLastPathComponent());
    	
        Nodo parent = (Nodo)(currentNode.getParent());
        String idclassificacion=propiedades.get_txt_idclasificacion().getText();
        String nombre=propiedades.get_txt_nombre().getText();
        String iduser=frame.get_txt_idvendedor().getText();
        String color=propiedades.get_txt_color().getText();
        
        boolean exist=data.exist(idclassificacion, iduser);
        if (exist){
        	String viejo=currentNode.getUserObject().toString();
        	if (viejo.compareTo(nombre)!=0){
        		this.renameCurrentNode(currentNode, nombre, color);	
        	}else{
        		data.beginTransaction();
        		String q=data.getUpdate(nombre,idclassificacion,iduser,color);
    			System.out.println(q);
    			data.clearBatch();
    			data.addBatch(q);
    			boolean error=data.executeBatch();
    			if (!error){
    				data.commitTransaction();
    			}else{
    				data.rollbackTransaction();
    			}
        	}
        		
        	
        	
        }else{
        	this.addObject(nombre,color);
        }
        propiedades.setVisible(false);
        this.loadToMemory();
    }else{
    	error("No hay nodo seleccionado ");
    }
}
public void editarCategoria(){
	this.initPropiedades();
	propiedades.get_txt_nombre().requestFocusInWindow();
	
}
public DefaultMutableTreeNode addObject(String idcategoria,String color) {
	
    Nodo parentNode = null;
    TreePath parentPath = frame.getJTree().getSelectionPath();
    DefaultTreeModel treeModel = (DefaultTreeModel) frame.getJTree().getModel();
    if (parentPath == null) {
        parentNode = (Nodo)treeModel.getRoot();
    } else {
        parentNode = (Nodo)(parentPath.getLastPathComponent());
    }

    return addObject(parentNode, null, true,idcategoria,color);
}

public String getSelectedClass(JTree tree){
	Nodo parentNode = null;
    try {
		TreePath parentPath = tree.getSelectionPath();
		if (parentPath!=null){
			DefaultTreeModel treeModel = (DefaultTreeModel) frame.getJTree().getModel();
			if (parentPath == null) {
			    parentNode = (Nodo)treeModel.getRoot();
			} else {
			    parentNode = (Nodo)(parentPath.getLastPathComponent());
			}	
		}
		
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	String clas_p="";
	if (parentNode!=null){
		try {
			clas_p=(String)parentNode.getValue();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	return clas_p;
}
public String getSelectedClass(){
	String clas_p="";
	clas_p=this.getSelectedClass(frame.getJTree());
	return clas_p;
}

public String getSelectedClassName(){
	String clas_p="Pedidos";
	clas_p=this.getSelectedClassName(frame.getJTree());
	return clas_p;
}

public String getSelectedClassNameCategorizar(){
	String clas_p="Pedidos";
	clas_p=this.getSelectedClassName(categoria.getJTree());
	return clas_p;
}

public String getSelectedClassName(JTree tree){
	Nodo parentNode = null;
    try {
		TreePath parentPath = tree.getSelectionPath();
		if (parentPath!=null){
			DefaultTreeModel treeModel = (DefaultTreeModel) frame.getJTree().getModel();
			if (parentPath == null) {
			    parentNode = (Nodo)treeModel.getRoot();
			} else {
			    parentNode = (Nodo)(parentPath.getLastPathComponent());
			}	
		}
		
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	String clas_p="Pedidos";
	if (parentNode!=null){
		try {
			clas_p=(String)parentNode.getUserObject();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	return clas_p;
}

public DefaultMutableTreeNode addObject(Nodo parent,
                                        Object child, 
                                        boolean shouldBeVisible,String clas,String color) {
	String clas_p=(String)parent.getUserObject();
	
	Nodo childNode = new Nodo(clas,parent.getValue());
	DefaultTreeModel treeModel = (DefaultTreeModel) frame.getJTree().getModel();
    if (parent == null) {
    	
        parent = (Nodo)treeModel.getRoot();
    }

    if (clas.compareTo("")!=0){
    	childNode=nueva_clasificacion(parent,childNode,color);
        if (childNode!=null){
            treeModel.insertNodeInto(childNode, parent, 
                    parent.getChildCount());
            if (shouldBeVisible) {
                frame.getJTree().scrollPathToVisible(new TreePath(childNode.getPath()));
            }
        }	
    }
    return childNode;
}

public int getPosition(String idclas){
	int i=0;
	boolean found=false;
	if (memory!=null){
		while (i<memory.length &!found){
			found=(memory[i][0].toString().compareTo(idclas)==0);
			if (!found)	i++;
		}
	}
	if (!found){
		i=-1;
	}
	return i;
}

public String getcolorfromMemory(String idpedido){
	String color="";
	boolean found=false;
	int i=0;
	if (this.colors_pedidos!=null){
		while (!found & i<this.colors_pedidos.length){
			found=colors_pedidos[i][0].toString().compareTo(idpedido)==0;
			if (!found)i++;
		}
	}
	if (found){
		String idclas=colors_pedidos[i][1].toString();
		color=this.getcolor(idclas);
	}
	return color;
}

public String getcolor(String idclas){
	String color="";
	int i=this.getPosition(idclas);
	if (i>=0){
		color=memory[i][3];
		
	}
	return color;
}

public void load_clases_from_memory(Nodo abuelo){
	int i=0;
	int childs=0;
	if (memory!=null){
		while (i<memory.length){
			if (memory[i][2].compareTo(abuelo.getValue())==0){
				Nodo padre = new Nodo(memory[i][1],memory[i][0]);
				this.load_clases_from_memory(padre);
				DefaultTreeModel treeModel =(DefaultTreeModel) frame.getJTree().getModel();
				treeModel.insertNodeInto(padre, abuelo, childs);
				childs++;
			}
			i++;
		}	
	}
}

public void seleccionarColor(){
	Color c = Color.white;
	Color newColor = JColorChooser.showDialog(_frame,
			"Seleccione un Color", c);
	if (newColor != null) {
		propiedades.get_btn_color().setBackground(newColor);
		propiedades.get_txt_color().setText(
				"" + newColor.getRed() + "," + newColor.getGreen()
				+ "," + newColor.getBlue());
	}
}

public Color getColor(String color) {

	int r = 255;
	int g = 255;
	int b = 255;

	if (color.compareTo("") != 0) {
		// System.out.println("foreground"+foreground);
		String _r = color.substring(0, color.indexOf(","));
		try {
			r = new Integer(_r);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		color = color.substring(color.indexOf(",") + 1);
		// System.out.println("foreground"+foreground);
		String _g = color.substring(0, color.indexOf(","));
		try {
			g = new Integer(_g);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		color = color.substring(color.indexOf(",") + 1);
		// System.out.println("foreground"+foreground);
		String _b = color;
		try {
			b = new Integer(_b);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}

	}
	// System.out.println("foreground rgb("+r+","+g+","+b+")");
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
		} catch (NumberFormatException e) {	}
		color = color.substring(color.indexOf(",") + 1);
		String _g = color.substring(0, color.indexOf(","));
		try {
			g = new Integer(_g);
		} catch (NumberFormatException e) {		}
		color = color.substring(color.indexOf(",") + 1);

		String _b = color;
		try {
			b = new Integer(_b);
		} catch (NumberFormatException e) {		}

	}
	
	r+=40;
	g+=40;
	b+=40;
	if (r>255)r=255;
	if (g>255)g=255;
	if (b>255)b=255;
	Color _foreground = new Color(r, g, b);
	return _foreground;
}

public void seleccionar(boolean selected){
	this.seleccionar(frame.getJTable(), selected);
}

public void seleccionar_categoria(boolean selected){
	this.seleccionar(categoria.getJTable(), selected);
}
public void seleccionar(JTable table,boolean selected){
	for (int i=0;i<table.getRowCount();i++){
		table.setValueAt(selected, i, 0);
	}
}

public void update_seguimiento(JCheckBox chk,int row,int col){
	String idpedido=frame.getJTable().getValueAt(row, 1).toString();
	String idvendedor=frame.get_txt_idvendedor().getText();
	if (idpedido.compareTo("")!=0){
		String owner=data.getOwner(idpedido);
		if (owner.compareTo(idvendedor)==0){
			data.update_seguimiento(idpedido, chk.isSelected());	
		}else{
			error("Este Pedido le pertenece a otro usuario");
			frame.getJTable().setValueAt(!chk.isSelected(), row, col);
		}
	}
}


}
