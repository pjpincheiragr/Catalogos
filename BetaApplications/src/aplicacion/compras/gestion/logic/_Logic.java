package aplicacion.compras.gestion.logic;

import java.awt.Color;
import java.awt.Font;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Vector;


import aplicacion.herramientas.java.launcher.logic.SwingWorker;
import aplicacion.herramientas.java.sortableselector.logic.Filtro;
import aplicacion.herramientas.java.table.CellEditor;
import aplicacion.herramientas.java.table.CheckBoxCellEditor;
import aplicacion.herramientas.java.table.Column;
import aplicacion.herramientas.java.table.CustomTable;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Logic;
import aplicacion.modelo.logic.Data;
import aplicacion.sistema.version.logic.Crono;
import aplicacion.sistema.version.logic.JakartaFtpWrapper;

import aplicacion.compras.gestion.gui._Frame;
import aplicacion.compras.gestion.logic._Data;
import aplicacion.compras.gestion.constructor._Constructor;
import aplicacion.compras.gestion.interfaces._Interface;
import aplicacion.compras.pedidoe.logic.TableItemColorCellRenderer;
import aplicacion.herramientas.java.image.logic.*;
import aplicacion.herramientas.java.table.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.net.ftp.FTPFile;

import aplicacion.herramientas.java.*;

public class _Logic extends Logic {
	private _Frame frame = null;
	private _Data data = null;
	private aplicacion.herramientas.java.visualizadores.Proveedor vProveedor = null;

	
	private String default_Format = "dd-MM-yyyy";
	
	private String estado = "";
	private int current;
	private int lenght;
	private boolean debug, done, canceled;
	private Timer Timer; // @jve:decl-index=0:
	private Crono crono;
	private Object[][] colors;
	
	private class _taskCargar {
		_taskCargar() {
			_cargarPedidos();
		}
	}
public void goCargar() {
		
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
		Timer.stop();
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

	
	public void goback(){
		
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



	
	public void load_carga() {
		this.BuscarFecha(frame.get_txt_c_fecha_carga());
	}




	public void delete_fecha_desde(JTextField tx) {
		String fecha = tx.getText();

	}

	
	
	
	
	

	

	public void _cargar_cliente(String idproveedor) {
	}

	
	
	
	public void setData(Data data) {
		this.data = (_Data) data;
		super.setData(data);
	}

		
	
	
		
	
	
	
	/**
	 * tc|idpedido|agenda|descripcion|cuenta|nomreb|estado|creado|seguimiento
	 * @param results
	 */
	private void create_table(Object[][] results) {
		_Constructor constructor = (_Constructor) this.getConstructor();
		TablePedidosColorCellRenderer renderer = new TablePedidosColorCellRenderer();
		renderer.setLogic(this);
		CustomTable table = new CustomTable();

		Column col = new Column();

		
		
		col = new Column();
		col.setName("tc");
		col.setWidth(40);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		col.setCellRenderer(renderer);
		table.addColumn(col);
		
		col = new Column();
		col.setName("idpedido");
		col.setWidth(74);
		col.setEditable(false);
		col.setClass(String.class);
		col.setCellRenderer(renderer);
		table.addColumn(col);

		col = new Column();
		col.setName("Agenda");
		col.setWidth(74);
		col.setEditable(false);
		col.setClass(Date.class);
		col.setAligment(JTextField.RIGHT);
		col.setCellRenderer(renderer);
		table.addColumn(col);
		
		col = new Column();
		col.setName("Descripcion");
		col.setWidth(230);
		col.setEditable(false);
		col.setClass(String.class);
		col.setCellRenderer(renderer);
		table.addColumn(col);

		
		col = new Column();
		col.setName("Transporte");
		col.setWidth(80);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		col.setCellRenderer(renderer);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("Proveedor");
		col.setWidth(150);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		col.setCellRenderer(renderer);
		col.setClass(String.class);
		table.addColumn(col);
		
		col = new Column();
		col.setName("Estado");
		col.setWidth(80);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		col.setClass(String.class);
		col.setCellRenderer(renderer);
		table.addColumn(col);
		
		col = new Column();
		col.setName("creado");
		col.setWidth(80);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		col.setClass(String.class);
		col.setCellRenderer(renderer);
		col.setClass(Date.class);
		table.addColumn(col);
		
		
		col = new Column();
		col.setName("Seguimiento");
		col.setWidth(80);
		col.setEditable(true);
		CheckBoxCellEditor chkce = new CheckBoxCellEditor();
		chkce.setItemListener(this._constructor.getItemListener());
		chkce.setTipo(Boolean.class);
		chkce.setName(_Interface._chk_seguimiento);
		col.setCellEditor(chkce.getCellCheck());
		col.setAligment(JTextField.RIGHT);
		col.setClass(Boolean.class);
		table.addColumn(col);
		
		table.setName(_Interface._table_pedidos);
		table.setData(results);
		table.addKeyListener(this.getConstructor().getKeyListener());
		table.addMouseListener(this.getConstructor().getMouseListener());
		Font fuente = new Font("Dialog", Font.BOLD, 10);
		table.setHeaderFont(fuente);
		table.setFont(fuente);
		table.build();
		table.fillData();

		JTable _table = table.getTable();
		_table.setColumnSelectionAllowed(false);
		_table.setRowSelectionAllowed(true);
		frame.setJTable(_table);
		done=true;
	}

	public void update_seguimiento(boolean chk, int row){
		String tc=frame.getJTable().getValueAt(row, 0).toString();
		
		String pedido=frame.getJTable().getValueAt(row, 1).toString();
			
		if (pedido.compareTo("")!=0){
				
					int _default=0;
					String estado="";
					
					String[] options=new String[]{
							"Enviado",
							"Recibido",
							"Nuevo"};
					int n=this.preguntar("Confirmar","Marcar Como:", options, options[_default]);
					if (n>=0){
						if (n==0){
							estado="Enviado";
							chk=true;
						}
						if (n==1){
							estado="Recibido";
							chk=false;
						}
						if (n==2){
							estado="Nuevo";
							chk=true;
						}
						if (tc.compareTo("PEP")==0){
							data.update_seguimiento(pedido, chk,estado);	
						}else{
							data.update_seguimiento_pdp(pedido, chk,estado);
						}
						
						frame.getJTable().setValueAt(chk, row, 8);
						frame.getJTable().setValueAt(estado, row, 6);
						frame.getJTable().repaint();
					}else{
						frame.getJTable().setValueAt(!chk, row, 8);
					}
					
		}else{
			frame.getJTable().setValueAt(!chk, row, 8);
		}
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
			col.setWidth(30);
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
		Font fuente = new Font("Dialog", Font.BOLD, 10);
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
	
			goCargar();
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
		goCargar();
	}


	public String getEstado(String idpedido){
		String estado="";
		return estado;
	}
	
	public void evaluar_estado(JComboBox cb){
		cb.transferFocus();
		goCargar();
	}
	
	public Object[][] process_data(Object[][] results){
		Object[][] tmp=null;
		if (results!=null){
			if (results.length>0){
				tmp=new Object[results.length][results[0].length];
				for (int i=0;i<results.length;i++){
					for (int j=0;j<results[0].length-1;j++){
						tmp[i][j]=results[i][j];	
					}
					tmp[i][results[0].length-1]=(results[i][results[0].length-1].toString().compareTo("1")==0);
				}		
			}
			
		}
		return tmp;
	}
	
	private String getQueryPedidos(){
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
		String q=data.getPedidosQuery(desde, hasta,top,frame.get_chk_utiliza_calendario().isSelected());
		
		String acumulated=this.getAcumulatedQuery();
		
		
		System.out.println("pedidos>"+q);
		return q;
	}
	public void _cargarPedidos() {
		estado="actualizando pedidos";
		data.updatePedidosConSeguimiento();
		this.acumulate();
		this.load_colors();
		estado="Esperando Respuesta del Servidor de Base de Datos";
		String q=this.getQueryPedidos();
		Object[][] results=data.getResults(q);
		results=process_data(results);
		if (results!=null){
			if (results.length>0){
				if (javax.swing.SwingUtilities.isEventDispatchThread()){
					create_table(results);
				}else{
					final Object[][] _results=results; 
					Runnable _execute = new Runnable() {
				        public void run() {
				        	create_table(_results);
				        }
					};
					invokeLater(_execute);	
				}
				
			}else{
				if (javax.swing.SwingUtilities.isEventDispatchThread()){
					create_table(results);
				}else{
					Runnable _execute = new Runnable() {
				        public void run() {
				        	cleanpedidos();
				        }
					};
					invokeLater(_execute);	
				}
				
			}
		}else{
			if (javax.swing.SwingUtilities.isEventDispatchThread()){
				create_table(results);
			}else{
				Runnable _execute = new Runnable() {
			        public void run() {
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
		
	}
	
	public void cleanpedidos(){
		frame.setJTable(null);	
		frame.setJTable1(null);
		
		frame.get_txt_idcliente().setText("");
		frame.get_txt_cliente_descripcion().setText("");
		frame.get_txt_idvendedor().setText("");
		frame.get_txt_idarticulo().setText("");
		frame.get_txt_idpedido().setText("");
		frame.get_txt_vendedor_descripcion().setText("");
		frame.get_txt_idarticulo_descripcion().setText("");
		frame.get_txt_idarticulo_linea().setText("");
		frame.get_txt_pedido_descripcion().setText("");
		
		frame.get_txt_c_cuenta().setText("");
		frame.get_txt_c_descripcion().setText("");
		frame.get_txt_c_fecha().setText("");
		frame.get_txt_c_fecha_carga().setText("");
		frame.get_txt_c_fecha_control().setText("");
		frame.get_txt_c_idcomprobante().setText("");
		frame.get_txt_c_nombre().setText("");
		frame.get_txt_c_remito().setText("");
		frame.get_txt_c_total().setText("");
		frame.get_txt_c_vendedor().setText("");
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
		System.out.println("pidiendo fechas "+desde+" <> "+hasta);
		colors=this.getControl(desde, hasta);
	}
	
	
	
	private Object[][] getControl(String desde, String hasta){
		Object[][] cargas=null;
		
		String q=data.getPedidosColor(desde, hasta);
		
		String acumulated=this.getAcumulatedQuery();
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
	
	
	public void show_pedido(int row){
		if (frame.getJTable()!=null){
			if (row>=0 & row<frame.getJTable().getRowCount()){
				String pedido=frame.getJTable().getValueAt(row, 1).toString();
				String tc=frame.getJTable().getValueAt(row, 0).toString();
				show_pedido(tc,pedido);
				
			}	
		}
		
		
	}
	
	public void show_pedido(String tc,String idpedido){
		if (tc.compareTo("PDP")==0){
			frame.get_txt_tc().setText("PDP");
			this.show_pedido_pdp(idpedido);
			this.cargar_items_pdp(idpedido);
		}
		if (tc.compareTo("PEP")==0){
			frame.get_txt_tc().setText("PEP");
			this.show_pedido_pep(idpedido);
			this.cargar_items_pep(idpedido);
		}
	}
	public void show_pedido_pep(String idpedido){
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
				frame.get_txt_c_descripcion().setText(descripcion);
				frame.get_txt_c_fecha_carga().setText(fecha_creacion);
				frame.get_txt_c_fecha_control().setText(fecha_modificacion);
				frame.get_txt_c_cuenta().setText(cliente);
				frame.get_txt_c_nombre().setText(cliente_descripcion);
				frame.get_txt_c_vendedor().setText(vendedor);
				frame.get_txt_c_total().setText(total);
			}
		}
	}
	
	public void show_pedido_pdp(String idpedido){
		frame.get_txt_c_idcomprobante().setText(idpedido);
		Object[][] results=data.getResults(data.getPedidoEncabezado(idpedido));
		if (results!=null){
			if (results.length>0){
				String cliente=(String)results[0][0];
				String fecha_creacion=(String) results[0][1];
				String descripcion=(String) results[0][2];
				String cliente_descripcion=(String)results[0][3];
				String estado=(String)results[0][5];
				String fecha_agenda=(String) results[0][6];
				
				/*String fecha_modificacion=(String) results[0][5];
				
				String info=(String)results[0][6];
				String idvendedor=(String)results[0][7];
				String vendedor=(String)results[0][8];
				String total=(String)results[0][13];*/
				frame.get_txt_c_fecha().setText(fecha_agenda);
				frame.get_txt_c_descripcion().setText(descripcion);
				frame.get_txt_c_fecha_carga().setText(fecha_creacion);
				frame.get_txt_c_fecha_control().setText("");
				frame.get_txt_c_cuenta().setText(cliente);
				frame.get_txt_c_nombre().setText(cliente_descripcion);
				frame.get_txt_c_vendedor().setText("");
				frame.get_txt_c_total().setText("");
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
			public void cargar(String codigo){
				Object[][] results=this.getInfo(codigo);
				String descripcion=(String) results[0][1];
				String cod=(String) results[0][0];
				frame.get_txt_idvendedor().setText(cod);
				frame.get_txt_vendedor_descripcion().setText(descripcion);
				show_with_filter(false);
				
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
	public void cargar_items(String tc,String idpedido){
		if (tc.compareTo("PEP")==0){
			this.cargar_items_pep(idpedido);
		}
		if (tc.compareTo("PDC")==0){
			this.cargar_items_pdp(idpedido);
		}
	}
	public void cargar_items_pep(String idpedido){
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
	
	public void cargar_items_pdp(String idpedido){
		Object[][] results=data.getPedidoItemsPDP(idpedido);
		
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
	
	/**
	 * chk|idarticulo|descripcion
	 * @param results
	 */
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
		goCargar();
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
	public String getAcumulatedQuery(){
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
		frame.get_txt_tc().setText("");
		frame.get_chk_utiliza_calendario().setSelected(true);
		frame.get_txt_idcliente().setText("");
		frame.get_txt_cliente_descripcion().setText("");
		frame.get_txt_idvendedor().setText("");
		frame.get_txt_idarticulo().setText("");
		frame.get_txt_idpedido().setText("");
		frame.get_txt_vendedor_descripcion().setText("");
		frame.get_txt_idarticulo_descripcion().setText("");
		frame.get_txt_idarticulo_linea().setText("");
		frame.get_txt_pedido_descripcion().setText("");
		
		frame.get_txt_c_cuenta().setText("");
		frame.get_txt_c_descripcion().setText("");
		frame.get_txt_c_fecha().setText("");
		frame.get_txt_c_fecha_carga().setText("");
		frame.get_txt_c_fecha_control().setText("");
		frame.get_txt_c_idcomprobante().setText("");
		frame.get_txt_c_nombre().setText("");
		frame.get_txt_c_remito().setText("");
		frame.get_txt_c_total().setText("");
		frame.get_txt_c_vendedor().setText("");
		fechas();
		fillTable();
		completeDiaCarga();
	}
	
	public void editar_pedido(){
		String idpedido=frame.get_txt_c_idcomprobante().getText();
		String tc=frame.get_txt_tc().getText();
		if (idpedido.compareTo("")!=0){
			this.editar_pedido(tc,idpedido);
		}
	}
	public void editar_pedido(int row){
		if (frame.getJTable()!=null){
			if (row>=0 & row<frame.getJTable().getRowCount()){
				String tc=frame.getJTable().getValueAt(row, 0).toString();
				String idpedido=frame.getJTable().getValueAt(row, 1).toString();
				show_pedido(tc,idpedido);
				editar_pedido(tc,idpedido);
			}
			
		}
		
	}
	public void editar_pedido(String tc,String idpedido){
		if (tc.compareTo("PEP")==0){
			this.editar_pedido_pep(idpedido);
		}
		if (tc.compareTo("PDP")==0){
			this.editar_pedido_pdp(idpedido);
		}
	}
	public void editar_pedido_pep(String idpedido){
		aplicacion.compras.pedidoe.constructor._Constructor
		pedido=new aplicacion.compras.pedidoe.constructor._Constructor();
		pedido.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler());
		pedido.setParameter(_parametros.iduser, this.getConstructor().getIduser());
		pedido.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
		pedido.setParameter(aplicacion.compras.pedidoe.interfaces._parametros._idpedido, idpedido);
		pedido.build(this.getConstructor());
		pedido.init();
	}
	
	public void editar_pedido_pdp(String idpedido){
		aplicacion.compras.carga.pedido.constructor._Constructor
		pedido=new aplicacion.compras.carga.pedido.constructor._Constructor();
		pedido.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler());
		pedido.setParameter(_parametros.iduser, this.getConstructor().getIduser());
		pedido.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
		pedido.setParameter(aplicacion.compras.pedidoe.interfaces._parametros._idpedido, idpedido);
		pedido.build(this.getConstructor());
		pedido.init();
	}
	
	public void nuevo_pedido(){
		
		aplicacion.compras.pedidoe.constructor._Constructor
		pedido=new aplicacion.compras.pedidoe.constructor._Constructor();
		pedido.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler());
		pedido.setParameter(_parametros.iduser, this.getConstructor().getIduser());
		pedido.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
		pedido.build(this.getConstructor());
		pedido.init();
		aplicacion.compras.pedidoe.logic._Logic
			logic =(aplicacion.compras.pedidoe.logic._Logic) pedido.getLogic();
		logic.nuevo();
		
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
		this.addAcumulator(frame.get_txt_idvendedor(), "pedido.idvendedor");
		this.addAcumulator(frame.get_txt_vendedor_descripcion(), "vendedor.nombre");
		this.addAcumulator(frame.get_txt_idarticulo(),"items.idarticulo");
		this.addAcumulator(frame.get_txt_idarticulo_descripcion(),"items.descripcion");
		this.addAcumulator(frame.get_txt_idarticulo_linea(),"articulo.descripabrev");
		
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
		if (data.getIsSuperUser(this.getConstructor().getIduser())){
			frame.get_chk_eliminados().setEnabled(true);
		}else{
			frame.get_chk_eliminados().setEnabled(false);
		}
	}
}
