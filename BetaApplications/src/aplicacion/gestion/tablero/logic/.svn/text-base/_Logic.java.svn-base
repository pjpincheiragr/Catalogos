package aplicacion.gestion.tablero.logic;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Locale;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import javax.swing.JColorChooser;
import javax.swing.table.*;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleInsets;
import org.jfree.util.Rotation;
import org.nfunk.jep.JEP;

import aplicacion.herramientas.java.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import aplicacion.gestion.tablero.gui._Frame;
import aplicacion.gestion.tablero.interfaces._Interface;
import java.util.*;

import java.awt.Rectangle;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Logic;
import aplicacion.modelo.logic.Data;
import aplicacion.herramientas.java.calendario.constructor.*;
import aplicacion.herramientas.java.comprobantes.fvn;
import aplicacion.herramientas.java.launcher.logic.SwingWorker;
import aplicacion.herramientas.java.table.*;
import aplicacion.herramientas.java.table.DateRenderer;
import aplicacion.herramientas.java.xml.Element;
import aplicacion.herramientas.java.xml.Atributo;
import aplicacion.herramientas.java.xml.XML;
import aplicacion.herramientas.java.*;

public class _Logic extends Logic {
	private _Frame frame = null;
	private _Data data = null;
	private Timer Timer; // @jve:decl-index=0:
	private Crono crono;
	private String estado = "";
	private int current;
	private int lenght;
	private JFrame grafico=null;
	private boolean done, canceled;
	private int errors = 0;
	private String[] allow_columns = new String[] { "A", "B", "C", "D", "E",
			"F", "G", "H" };
	private int allow_rows = 40;
	private List<Color[][][]> colors = null;
	private JTable[] tableros = null;
	private Object[] copiar = null;

	public void Eliminar() {
		int index = frame.getJTabbedPane().getSelectedIndex();
		String idtablero = frame.getJTabbedPane().getTitleAt(index);
		if (idtablero.compareTo("") != 0) {
			boolean ok = confirmar("Confirme Para Eliminar el tablero "
					+ idtablero + ": ", 2);
			if (ok) {
				this._eliminar(idtablero);
			}
		}
	}

	public void EliminarCelda() {
		int index = frame.getJTabbedPane().getSelectedIndex();
		String idtablero = frame.getJTabbedPane().getTitleAt(index);
		//System.out.println("Eliminar Celda");

		JScrollPane scroll = (JScrollPane) frame.getJTabbedPane()
				.getComponentAt(index);
		//System.out.println("Seleccion Scroll " + scroll);

		javax.swing.JViewport viewport = (javax.swing.JViewport) scroll
				.getComponent(0);
		JTable table = null;
		try {
			table = (JTable) viewport.getView();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (table != null) {
			copiar = new Object[3];
			int row = table.getSelectedRow();
			int col = table.getSelectedColumn();
			String columna = this.getColumnName(col);
			copiar[0] = idtablero;
			copiar[1] = row;
			copiar[2] = columna;
			//System.out.println("Copiar [" + row + "," + col + "]= " + idtablero+ " " + row + " " + columna);
			if (idtablero.compareTo("") != 0) {
				boolean ok = confirmar(
						"Confirme Para Eliminar La celda del tablero "
								+ idtablero + ": ", 2);
				if (ok) {
					ok = data.deleteCelda(row, columna, idtablero);
					if (ok) {
						this.colors.get(index)[row][col][0] = new Color(255,
								255, 255);
						;
						this.colors.get(index)[row][col][1] = new Color(0, 0, 0);
						;
						table.setValueAt("", row, col);
						table.repaint();
					}

				}
			}
		}

	}

	public void crearTablero(String _nombre) {
		final String nombre = _nombre;
		colors.add(load_colors(nombre));
		Runnable _execute = new Runnable() {
			public void run() {
				JTable table = create_table(nombre);
				table.setName(nombre);
				JScrollPane pane = new JScrollPane();
				pane.setBounds(0, 0, frame.getJTabbedPane().getWidth() - 10,
						frame.getJTabbedPane().getHeight() - 10);
				pane.setViewportView(table);

				frame.getJTabbedPane().add(pane, nombre);

			}
		};
		this.invokeLater(_execute);
	}

	public boolean insertarNuevo(String nombre) {
		data.beginTransaction();
		String q = data.getInsertTablero(nombre);
		data.clearBatch();
		data.addBatch(q);
		boolean error = data.executeBatch();
		if (!error) {
			data.commitTransaction();

		} else {
			data.rollbackTransaction();

		}
		return !error;
	}

	public void nuevo(String nombre) {
		if (data.existe(nombre)) {
			error("El tablero " + nombre + " ya existe");
		} else {
			boolean nuevo = this.insertarNuevo(nombre);
			if (nuevo) {
				crearTablero(nombre);
				aviso("Se creo Correctamente el Tablero");

			} else {
				error("Error creando Tablero");
			}
		}
	}

	public void Nuevo() {
		String nombre = this.ingresar("Ingrese Nombre:");
		if (nombre.compareTo("") != 0) {
			nuevo(nombre);
		}
	}

	public void _eliminar(String idtablero) {
		int index = -1;
		for (int i = 0; i < frame.getJTabbedPane().getTabCount(); i++) {
			if (frame.getJTabbedPane().getTitleAt(i).compareTo(idtablero) == 0) {
				index = i;
			}
		}
		if (index >= 0) {
			frame.getJTabbedPane().removeTabAt(index);
			this._eliminar_tablero(idtablero);
		}

	}

	public boolean _eliminar_tablero(String idtablero) {
		data.beginTransaction();
		String q = data.getEliminarTabero(idtablero);
		data.clearBatch();
		data.addBatch(q);
		q = data.getEliminarTaberoVariables(idtablero);
		data.addBatch(q);
		q = data.getEliminarTaberoCalendario(idtablero);
		data.addBatch(q);
		boolean error = data.executeBatch();
		if (!error) {
			data.commitTransaction();
		} else {
			data.rollbackTransaction();
		}
		return !error;
	}

	public void Copiar() {
		//System.out.println("Copiar");

		int index = frame.getJTabbedPane().getSelectedIndex();

		String idtablero = frame.getJTabbedPane().getTitleAt(index);

		JScrollPane scroll = (JScrollPane) frame.getJTabbedPane()
				.getComponentAt(index);
		System.out.println("Seleccion Scroll " + scroll);

		javax.swing.JViewport viewport = (javax.swing.JViewport) scroll
				.getComponent(0);
		JTable table = null;
		try {
			table = (JTable) viewport.getView();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (table != null) {
			copiar = new Object[3];
			int row = table.getSelectedRow();
			int col = table.getSelectedColumn();
			String columna = this.getColumnName(col);
			copiar[0] = idtablero;
			copiar[1] = row;
			copiar[2] = columna;
			System.out.println("Copiar [" + row + "," + col + "]= " + idtablero
					+ " " + row + " " + columna);
		}
	}

	public void Pegar() {
		if (copiar != null) {
			String idtablero = (String) copiar[0];
			String row = "" + copiar[1];
			String column = (String) copiar[2];

			String formula = data.getFormula(row, column, idtablero);
			String detalle = data.getFormulaDetalle(row, column, idtablero);
			String grafica = data.getFormulaGrafica(row, column, idtablero);
			String nombre = data.getNombre(row, column, idtablero);
			String foreground = data.getForeground(new Integer(row), column,
					idtablero);
			frame.get_txt_nombre().setText(nombre);
			frame.get_txt_foreground().setText(foreground);
			String background = data.getBackground(new Integer(row), column,
					idtablero);
			frame.get_txt_background().setText(background);
			frame.get_txt_formula().setText(formula);
			frame.get_txt_detalle().setText(detalle);
			frame.get_txt_grafica().setText(grafica);

		}

	}

	public int getColumn(String column) {
		int col = -1;
		for (int i = 0; i < this.allow_columns.length; i++) {
			if (this.allow_columns[i].compareTo(column.toUpperCase()) == 0) {
				col = i;
			}
		}
		return col;
	}

	public String getColumnName(int col) {
		String name = "";
		name = allow_columns[col];
		return name;
	}

	public void setFrame(JFrame _frame) {
		this.frame = (_Frame) _frame;
		super.setFrame(_frame);
	}

	public void setData(Data _data) {
		this.data = (_Data) _data;
		super.setData(_data);
	}

	private aplicacion.herramientas.java.buscadores.Calendario Calendario = null;

	public void BuscarCalendario(JButton tx) {
		if (Calendario != null) {
			Calendario.dispose();
		}
		Calendario = new aplicacion.herramientas.java.buscadores.Calendario(
				this.getConstructor());
		Calendario.Buscar(tx);
	}

	public void BuscarCalendario() {
		this.BuscarCalendario(frame.get_btn_calendario());
	}

	public void createTimer() {
		crono = new Crono();
		crono.start();
		lenght = 0;
		current = 0;
		errors = 0;
		done = false;
		canceled = false;
		Timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (done | canceled) {
					endbar();
					Timer.stop();
				} else {

					updateBar();
				}
			}
		});

	}

	public void updateBar() {
		frame.getJProgressBar().setMaximum(lenght);
		frame.getJProgressBar().setValue(current);
		frame.getJProgressBar().setString(
				estado + " " + current + "/" + lenght + " " + crono.elapsed());
		frame.getJProgressBar().setStringPainted(true);
	}

	public void endbar() {
		estado = "";
		frame.getJProgressBar().setString("");
		frame.getJProgressBar().setIndeterminate(false);
		frame.getJProgressBar().setValue(0);
		frame.get_btn_cargar().setEnabled(true);

	}

	public void addTablero(String idtablero) {
		final String _idtablero = idtablero;
		Runnable _execute = new Runnable() {
			public void run() {
				_addTablero(_idtablero);
			}
		};
		this.invokeLater(_execute);
	}
	
	public int getIndex(String idtablero){
		int index=-1;
		for (int i=0;i<frame.getJTabbedPane().getTabCount();i++){
			if (frame.getJTabbedPane().getTitleAt(i).compareTo(idtablero)==0){
				index=i;
			}
		}
		return index;
	}

	public void _addTablero(String idtablero) {

		JTable table = this.create_table(idtablero);
		table.setName(idtablero);
		JScrollPane pane = new JScrollPane();
		pane.setBounds(0, 0, frame.getJTabbedPane().getWidth() - 10, frame
				.getJTabbedPane().getHeight() - 10);
		pane.setViewportView(table);

		frame.getJTabbedPane().add(pane, idtablero);
		load_variables(idtablero, table);
	}

	/*
	 * public void simulate(){ Object[][] results=new
	 * Object[this.allow_rows][this.allow_columns.length];
	 * 
	 * for (int i=0;i<results.length;i++){ for (int
	 * j=0;j<results[0].length;j++){ results[i][j]=""; } }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * System.out.println("Filas:"+results.length+" Columnas:"+results[0].length)
	 * ; final Object[][] _results=results; Runnable _execute=new Runnable(){
	 * public void run(){ create_table(_results); } };
	 * this.invokeLater(_execute); }
	 */

	private JTable create_table(String idtablero) {
		CustomTable table = new CustomTable();
		CellEditor pce = null;
		Column col = null;
		TableItemColorCellRenderer color = new TableItemColorCellRenderer();
		color.setLogic(this);

		Object[][] results = new Object[this.allow_rows][this.allow_columns.length];
		for (int i = 0; i < results.length; i++) {
			for (int j = 0; j < results[0].length; j++) {
				results[i][j] = "";
			}
		}
		double w = (frame.getJTabbedPane().getWidth() - 14) / results[0].length;
		for (int i = 0; i < this.allow_columns.length; i++) {

			col = new Column();
			col.setName(allow_columns[i]);
			col.setWidth(new Double(w).intValue() - 1);
			// col.setEditable(true);
			col.setClass(String.class);
			col.setAligment(JTextField.LEFT);

			pce = new CellEditor();
			pce.addKeyListener(this.getConstructor().getKeyListener());
			pce.setSelectedBackgroundColor(Color.lightGray);
			pce.setTipo(String.class);
			pce.setAligment(JTextField.LEFT);
			pce.setName(_Interface._txt_cell);
			col.setCellEditor(pce.getCellEditor());
			col.setCellRenderer(color);
			table.addColumn(col);

		}

		table.setData(results);
		Font fuente = new Font("Arial", Font.PLAIN, 9);
		table.setHeaderFont(fuente);
		table.setFont(fuente);
		table.build();
		table.fillData();
		JTable _table = table.getTable();
		_table.setName(_Interface._table);

		_table.addMouseListener(this.getConstructor().getMouseListener());
		_table.addKeyListener(this.getConstructor().getKeyListener());
		_table.getTableHeader().addMouseListener(
				this.getConstructor().getMouseListener());

		return _table;
	}

	public Color getBackgroundColor(int row, int col, boolean selected) {
		int index = frame.getJTabbedPane().getSelectedIndex();
		return this.colors.get(index)[row][col][0];
	}

	public Color getBackgroundColor(int row, int col, String idtablero) {

		String background = data.getBackground(row, this.getColumnName(col),
				idtablero);
		Color _background = this.getColor(background);
		return _background;
	}

	public Color getForegroundColor(int row, int col, boolean selected) {
		int index = frame.getJTabbedPane().getSelectedIndex();
		return this.colors.get(index)[row][col][1];
	}

	public void seleccionarForeground() {

		//System.out.println("Seleccionar Foreground");
		int index = frame.getJTabbedPane().getSelectedIndex();
		//System.out.println("Seleccion Index " + index);
		String idtablero = frame.getJTabbedPane().getTitleAt(index);

		JScrollPane scroll = (JScrollPane) frame.getJTabbedPane()
				.getComponentAt(index);
		//System.out.println("Seleccion Scroll " + scroll);

		javax.swing.JViewport viewport = (javax.swing.JViewport) scroll
				.getComponent(0);
		JTable table = null;
		try {
			table = (JTable) viewport.getView();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (table != null) {
			int row = table.getSelectedRow();
			int col = table.getSelectedColumn();
			Color c = this.getForegroundColor(row, col, idtablero);

			Color newColor = JColorChooser.showDialog(_frame,
					"Choose Foreground Color", c);
			if (newColor != null) {
				this.colors.get(index)[row][col][1] = newColor;
				frame.get_txt_foreground().setText(
						"" + newColor.getRed() + "," + newColor.getGreen()
								+ "," + newColor.getBlue());
			}
			table.repaint();
		}

	}

	public void seleccionarBackground() {
		//System.out.println("Seleccionar Background");
		int index = frame.getJTabbedPane().getSelectedIndex();
		String idtablero = frame.getJTabbedPane().getTitleAt(index);
		JScrollPane scroll = (JScrollPane) frame.getJTabbedPane()
				.getComponentAt(index);

		javax.swing.JViewport viewport = (javax.swing.JViewport) scroll
				.getComponent(0);

		JTable table = null;
		try {
			table = (JTable) viewport.getView();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (table != null) {
			int row = table.getSelectedRow();
			int col = table.getSelectedColumn();
			Color c = this.getBackgroundColor(row, col, idtablero);

			Color newColor = JColorChooser.showDialog(_frame,
					"Choose Foreground Color", c);
			if (newColor != null) {

				this.colors.get(index)[row][col][0] = newColor;
				frame.get_txt_background().setText(
						"" + newColor.getRed() + "," + newColor.getGreen()
								+ "," + newColor.getBlue());
			}

			table.repaint();
		}

	}

	public Color getForegroundColor(int row, int col, String idtablero) {

		int r = 0;
		int g = 0;
		int b = 0;
		String foreground = data.getForeground(row, this.getColumnName(col),
				idtablero);
		Color _foreground = this.getColor(foreground);

		return _foreground;
	}

	public Color getColor(String color) {

		int r = 0;
		int g = 0;
		int b = 0;

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

	private Object getCleanResult(Object valor) {
		try {
			Double money = new Double(valor.toString());
			money = new Convertidor().roundDouble(money, 2);
			// valor=new Convertidor().getMoney(money, 2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return valor;
	}

	public Object evaluate_formula(String formula, int row, int col,
			JTable table, String idtablero) {
		//System.out.println("Evaluando Formula " + formula);
		Object valor = null;
		if (formula.startsWith("\"") & formula.endsWith("\"")) {
			formula = formula.substring(1, formula.length() - 1);
			// System.out.println("Set("+row+","+col+")="+formula);
			valor = formula;

		} else {
			if (formula.startsWith("=")) {
				valor = evaluate_excel(formula.substring(1), row, col, table,
						idtablero);

				valor = this.getCleanResult(valor);
				//System.out.println("Resultado Formula " + valor);
			} else {
				if (formula.toUpperCase().startsWith("SQL=")) {
					valor = evaluate_sql(formula.substring(formula
							.toUpperCase().indexOf("SQL=")
							+ "SQL=".length()), row, col);
					if (valor != null) {
						valor = this.getCleanResult(valor);

					} else {
						valor = 0.0;

					}
					//System.out.println("Resultado Formula " + valor);

				}
			}
		}
		return valor;
	}

	public void show(int row, int col, JTable table) {
		int index = frame.getJTabbedPane().getSelectedIndex();
		String idtablero = frame.getJTabbedPane().getTitleAt(index);
		String column = this.getColumnName(col);
		String formula = data.getFormula("" + row, column, idtablero);
		String detalle = data.getFormulaDetalle("" + row, column, idtablero);
		String grafica = data.getFormulaGrafica("" + row, column, idtablero);
		String nombre = data.getNombre("" + row, column, idtablero);
		String foreground = data.getForeground(row, column, idtablero);
		String background = data.getBackground(row, column, idtablero);
		frame.get_txt_nombre().setText(nombre);
		frame.get_txt_foreground().setText(foreground);
		frame.get_txt_background().setText(background);
		frame.get_txt_formula().setText(formula);
		frame.get_txt_detalle().setText(detalle);
		frame.get_txt_grafica().setText(grafica);
		frame.get_txt_columna().setText(column);
		frame.get_txt_fila().setText("" + row);
	}

	public int getKey(List<Object[]> keys, String key){
		int index=-1;
		
		for (int i=0;i<keys.size();i++){
			Object[] _key=keys.get(i);
			if (_key[0].toString().compareTo(key)==0){
				index=i;
			}
		}
		return index;
	}
	public Object[][] process_chart(String formula,String idtablero) {
		//System.out.println("process chart >"+formula);
		formula = formula.toLowerCase();
		List<Object[]> keys=new ArrayList<Object[]>();
		
		List<Object[][]> valores=new ArrayList<Object[][]>(); 
		while (formula.contains("<c>")) {
			int begins = formula.indexOf("<c>") + "<c>".length();
			int ends = formula.indexOf("</c>");
			int[] valor = this.getCelda(formula.substring(begins, ends));
			int _row = valor[0];
			int _col = valor[1];
			String columna = formula.substring(begins, ends).substring(0, 1);
			Object[][] values=this.getGraficaCelda(""+_row, columna, idtablero);
			if (values !=null){
				valores.add(values);
			}
			formula=formula.substring(ends + "</c>".length(), formula
					.length());
			//System.out.println("Formula>"+formula);
		}
		for (int i=0;i<valores.size();i++){
			//System.out.println("Valores ("+i+")>");
			Object[][] valor=valores.get(i);
			for (int j=0;j<valor.length;j++){
				String key=valor[j][0].toString();
				int index=this.getKey(keys, key);
				if (index<0){
					Object[] _key=new Object[2];
					_key[0]=valor[j][0].toString();
					_key[1]=new Double(valor[j][1].toString());
					keys.add(_key);
				}else{
					Double current=(Double) keys.get(index)[1];
					keys.get(index)[1]=current+new Double(valor[j][1].toString());
				}
				for (int k=0;k<valor[0].length;k++){
					//System.out.print(valor[j][k]+",");		
				}
				//System.out.println();
			}
		}
		
		Object[][] results = new Object[keys.size()][2];
		for (int i=0;i<keys.size();i++){
			for (int j=0;j<2;j++){
				results[i][j]=keys.get(i)[j];	
			}
			
		}
		
		return results;
	}

	public Object[][] getGraficaCelda(String row,String column,String idtablero){
		Object[][] results=null;
		String grafica = data.getFormulaGrafica("" + row, column, idtablero);
		if (grafica.contains("fecha_desde")) {
			String ini = frame.get_txt_fecha_desde().getText();
			grafica = grafica.replaceAll("fecha_desde", ini);
		}
		if (grafica.contains("fecha_hasta")) {
			grafica = grafica.replaceAll("fecha_hasta", frame
					.get_txt_fecha_hasta().getText());
		}
		if (grafica.startsWith("<Y>")) {
			grafica = grafica.replaceAll("<Y>", "");
		
		}
		if (grafica.startsWith("<M>")) {
			grafica = grafica.replaceAll("<M>", "");
			
		}
		if (grafica.startsWith("<W>")) {
			grafica = grafica.replaceAll("<w>", "");
		}
		if (grafica.startsWith("<T>")) {
			grafica = grafica.replaceAll("<T>", "");
		}
		if (grafica.compareTo("") != 0) {
			
			if (grafica.startsWith("=")) {
				results = process_chart(grafica.replace("=", ""),idtablero);
			} else {
				if (grafica.toUpperCase().startsWith("SQL=")) {
					grafica=grafica.toUpperCase().replaceAll("SQL=", "");
					//System.out.println("Grafica?"+grafica);
					results = data.getResults(grafica);
				}
			}
		}
		return results;
	}
	
	public void mostar_evolucion(int row, int col, JTable table, String nombre) {
		int index = frame.getJTabbedPane().getSelectedIndex();
		String idtablero = frame.getJTabbedPane().getTitleAt(index);
		TimeSeries s1 = null;
		DefaultPieDataset t1 = null;
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.weighty = 1.0;
		//frame.getJPanel().removeAll();
		String column = this.getColumnName(col);
		String grafica = data.getFormulaGrafica("" + row, column, idtablero);
		Object[][] results = null;
		boolean year = false;
		boolean month = false;
		boolean weak = false;
		boolean pie = false;
		if (grafica.contains("fecha_desde")) {
			String ini = frame.get_txt_fecha_desde().getText();
			grafica = grafica.replaceAll("fecha_desde", ini);
		}
		if (grafica.contains("fecha_hasta")) {
			grafica = grafica.replaceAll("fecha_hasta", frame
					.get_txt_fecha_hasta().getText());
		}
		if (grafica.startsWith("<Y>")) {
			year = true;
			grafica = grafica.replaceAll("<Y>", "");
		
		}
		if (grafica.startsWith("<M>")) {
			month = true;
			grafica = grafica.replaceAll("<M>", "");
			
		}
		if (grafica.startsWith("<W>")) {
			grafica = grafica.replaceAll("<w>", "");
			weak = true;
		}
		if (grafica.startsWith("<T>")) {
			grafica = grafica.replaceAll("<T>", "");
			pie = true;
		}
		if (grafica.compareTo("") != 0) {
			
			if (grafica.startsWith("=")) {
				results = process_chart(grafica.replace("=", ""),idtablero);
			} else {
				if (grafica.toUpperCase().startsWith("SQL=")) {
					grafica=grafica.toUpperCase().replaceAll("SQL=", "");
					//System.out.println("Grafica?"+grafica);
					results = data.getResults(grafica);
				}
			}
		}
		boolean ok=false;
		if (results != null) {
			if (results.length>0) {
				ok=true;
				if (year) {
					this.mostar_evolucion_mes(row, col, table, nombre, results, s1,
							gridBagConstraints);
				}
				if (month) {
					this.mostar_evolucion_diaria(row, col, table, nombre, results,
							s1, gridBagConstraints);
				}
				if (pie) {
					this.mostar_torta(row, col, table, nombre, results, t1,
							gridBagConstraints);
				}	
			}
			
		}
		if (!ok){
			error("No hay Informacion disponible para el grafico");
		}
	}

	public void mostar_evolucion_diaria(int row, int col, JTable table,
			String nombre, Object[][] results, TimeSeries s1,
			GridBagConstraints gridBagConstraints) {

		if (results != null) {
			if (results.length > 0) {
				s1 = new TimeSeries(nombre);
				for (int i = 0; i < results.length; i++) {
					int dia = -1;
					try {
						dia = new Integer(results[i][0].toString());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					int month = -1;
					try {
						month = new Integer(results[i][1].toString());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					int year = -1;
					try {
						year = new Integer(results[i][2].toString());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Double value = null;
					try {
						value = new Double(results[i][3].toString());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (dia > 0 & month > 0 & year > 0 & value != null) {
						//System.out.println("Add " + dia + "|" + month + "|"+ year + "|" + value);
						s1.add(new Day(dia, month, year), value);
					}

				}

				if (s1 != null) {
					
					if (grafico!=null){
						grafico.dispose();
					}
					grafico =new JFrame("Grafico "+nombre);
					grafico.setBounds(0,0, 800,600);
					this.centrar_frame(grafico);
					grafico.setContentPane(this.getJContentPane());
					grafico.getContentPane().add(
							this.createDemoPanel(s1, nombre),
							gridBagConstraints);
					grafico.getContentPane().repaint();
					grafico.setVisible(true);
				}

				//frame.getJPanel().repaint();
			}
		}
	}

	public void mostar_torta(int row, int col, JTable table, String nombre,
			Object[][] results, DefaultPieDataset t1,
			GridBagConstraints gridBagConstraints) {
		//System.out.println("Mostrar Torta "+results);
			if (results != null) {
				if (results.length > 0) {
					double total=0.0;
					for (int i = 0; i < results.length; i++) {
						double parte = 0.0;
						try {
							parte = new Double(results[i][1].toString());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						total+=parte;
					}
					
				t1 = new DefaultPieDataset();
				for (int i = 0; i < results.length; i++) {
					String label = "";
					;
					try {
						label = results[i][0].toString();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					double parte = 0.0;
					try {
						parte = new Double(results[i][1].toString());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//parte=(parte/total*100);
					if (parte>0){
						t1.setValue(label, parte);
						//System.out.println("SEt data"+label+" "+parte);
					}
					
					
				}

				if (t1 != null) {
					if (grafico!=null){
						grafico.dispose();
					}
					grafico =new JFrame("Grafico "+nombre);
					grafico.setBounds(0,0, 800,600);
					this.centrar_frame(grafico);
					grafico.setContentPane(this.getJContentPane());
					grafico.getContentPane().add(
							this.createDemoPanel(t1, nombre),
							gridBagConstraints);
					grafico.getContentPane().repaint();
					grafico.setVisible(true);
				}

			//	frame.getJPanel().repaint();
			}
		}
	}

	private JPanel getJContentPane() {
		JPanel jContentPane=null;
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
		}
		return jContentPane;
	}


	public void mostar_evolucion_mes(int row, int col, JTable table,
			String nombre, Object[][] results, TimeSeries s1,
			GridBagConstraints gridBagConstraints) {
		if (results != null) {
			if (results.length > 0) {
				s1 = new TimeSeries(nombre);
				for (int i = 0; i < results.length; i++) {
					int month = -1;
					try {
						month = new Integer(results[i][0].toString());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					int year = -1;
					try {
						year = new Integer(results[i][1].toString());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Double value = null;
					try {
						value = new Double(results[i][2].toString());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (month > 0 & year > 0 & value != null) {
						//System.out.println("Add " + month + "|" + year + "|"	+ value);
						s1.add(new Month(month, year), value);
					}

				}

			}
		}
		if (s1 != null) {
			if (grafico!=null){
				grafico.dispose();
			}
			grafico =new JFrame("Grafico "+nombre);
			grafico.setBounds(0,0, 800,600);
			this.centrar_frame(grafico);
			grafico.setContentPane(this.getJContentPane());
			grafico.getContentPane().add(
					this.createDemoPanel(s1, nombre),
					gridBagConstraints);
			grafico.getContentPane().repaint();
			grafico.setVisible(true);
		}

		//frame.getJPanel().repaint();
	}

	public void ver_opciones(int row, int col, JTable table) {
		int index = frame.getJTabbedPane().getSelectedIndex();
		String idtablero = frame.getJTabbedPane().getTitleAt(index);
		String column = this.getColumnName(col);
		String detalle = data.getFormulaDetalle("" + row, column, idtablero);
		String grafica = data.getFormulaGrafica("" + row, column, idtablero);
		List<String> opciones = new ArrayList<String>();
		if (detalle.compareTo("") != 0) {
			opciones.add("Detalle");
		}
		if (grafica.compareTo("") != 0) {
			opciones.add("Grafico");
		}
		if (opciones.size() > 0) {
			String[] options = new String[opciones.size()];
			for (int i = 0; i < opciones.size(); i++) {
				options[i] = opciones.get(i);
			}

			int option = this.preguntar("Seleccion", "Seleccione su opcion",
					options, options[0]);
			if (option >= 0) {
				if (options[option].compareTo("Detalle") == 0) {
					this.ver_detalle(row, col, table);
				}
				if (options[option].compareTo("Grafico") == 0) {
					String nombre = data.getNombre("" + row, column, idtablero);
					this.mostar_evolucion(row, col, table, nombre);
				}
			}
		} else {
			error("No hay opciones Disponibles para este indicador");
		}

	}

	public void ver_detalle(int row, int col, JTable table) {
		int index = frame.getJTabbedPane().getSelectedIndex();
		String idtablero = frame.getJTabbedPane().getTitleAt(index);

		String column = this.getColumnName(col);

		String detalle = data.getFormulaDetalle("" + row, column, idtablero);
		if (detalle.compareTo("") != 0) {
			detalle = detalle.replaceAll("SQL=", "");
			if (detalle.contains("fecha_desde")) {
				detalle = detalle.replaceAll("fecha_desde", frame
						.get_txt_fecha_desde().getText());
			}
			if (detalle.contains("fecha_hasta")) {
				detalle = detalle.replaceAll("fecha_hasta", frame
						.get_txt_fecha_hasta().getText());
			}
			aplicacion.gestion.detalle.constructor._Constructor C = new aplicacion.gestion.detalle.constructor._Constructor();
			C.setParameter(_parametros.LookAndFeel, this.getConstructor()
					.getLookAndFeelTheme());
			C.setParameter(_parametros.iduser, this.getConstructor()
					.getIduser());
			C.setParameter(_parametros.connector, data.getConnectionHandler());
			C.setParameter(
					aplicacion.gestion.detalle.interfaces._Parametros.query,
					detalle);
			if (detalle.compareTo("") != 0) {
				C
						.setParameter(
								aplicacion.gestion.detalle.interfaces._Parametros.query,
								detalle);
			}

			C.build(this.getConstructor());
			C.init();
			this.getConstructor().addChild(C);
		}

	}

	public boolean esCelda(String formula) {
		String columna = formula.substring(0, 1);
		int col = this.getColumn(columna);
		String fila = formula.substring(1);
		int row = -1;
		try {
			row = new Integer(fila);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (col >= 0 & row >= 0);
	}

	public int[] getCelda(String formula) {

		int values[] = new int[2];
		String columna = formula.substring(0, 1);
		int col = this.getColumn(columna);
		String fila = formula.substring(1);
		int row = -1;
		try {
			row = new Integer(fila);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		values[0] = row;
		values[1] = col;
		// System.out.println("Get Celda "+formula+"=("+values[0]+","+values[1]+")");
		return values;
	}

	public Object evaluate_sql(String formula, int row, int col) {
		Object valor = null;
		Object[][] results = null;
		if (formula.contains("fecha_desde")) {
			formula = formula.replaceAll("fecha_desde", frame
					.get_txt_fecha_desde().getText());
		}
		if (formula.contains("fecha_hasta")) {
			formula = formula.replaceAll("fecha_hasta", frame
					.get_txt_fecha_hasta().getText());
		}
		//System.out.println("Execute:" + formula);
		results = data.getResults(formula);
		if (results != null) {
			if (results.length > 0) {
				valor = (String) results[0][0];
			}
		}
		return valor;
	}

	public String process_cell(String formula, int row, int col, JTable table,
			String idtablero) {
		formula = formula.toLowerCase();
		while (formula.contains("<c>")) {
			int begins = formula.indexOf("<c>") + "<c>".length();
			int ends = formula.indexOf("</c>");
			int[] valor = this.getCelda(formula.substring(begins, ends));
			int _row = valor[0];
			int _col = valor[1];
			if (_row >= 0 & _col >= 0) {

				String celda = table.getValueAt(_row, _col).toString();
				if (celda == null) {
					celda = "";
				}
				if (celda.compareTo("") == 0) {
					String _column = this.getColumnName(_col);
					String _formula = data.getFormula("" + _row, "" + _column,
							idtablero);
					if (_formula.compareTo("") != 0) {
						Object _valor = this.evaluate_formula(_formula, _row,
								_col, table, idtablero);
						if (_valor == null) {
							_valor = "";
						}
						celda = _valor.toString();
						if (celda.compareTo("") == 0) {
							celda = "0";
						}
						System.out.println("RECURSIVE EVALUATION (" + _row
								+ "," + _col + ")=" + formula + " R=" + celda);
					} else {
						System.out.println("RECURSIVE EVALUATION (" + _row
								+ "," + _col + ")=" + formula
								+ " R=no es posible calcular la celda");
					}

				}
				formula = formula.substring(0, formula.indexOf("<c>"))
						+ celda
						+ formula.substring(ends + "</c>".length(), formula
								.length());
				System.out.println("AFTER RECURSIVE EVALUATION " + formula);

			}
			// formula=formula.substring(formula.indexOf("<c>")-1+"<c>".length())+celda+formula.substring(ends+"</c>".length(),
			// formula.length());

		}
		return formula;
	}

	public String getParametro(String idparametro) {
		String valor = "";
		if (idparametro.compareTo("dias_trabajados") == 0) {
			valor = frame.get_txt_dias_trabajados().getText();
		}
		if (idparametro.compareTo("dias_restantes") == 0) {
			valor = frame.get_txt_dias_restantes().getText();
		}
		return valor;
	}

	public String process_parameters(String formula, int row, int col,
			JTable table) {
		formula = formula.toLowerCase();
		while (formula.toLowerCase().contains("<p>")) {
			int begins = formula.indexOf("<p>") + "<p>".length();
			int ends = formula.indexOf("</p>");
			String valor = formula.substring(begins, ends);

			String celda = getParametro(valor);
			formula = formula.substring(0, formula.indexOf("<p>"))
					+ celda
					+ formula.substring(ends + "</p>".length(), formula
							.length());

			// formula=formula.substring(formula.indexOf("<c>")-1+"<c>".length())+celda+formula.substring(ends+"</c>".length(),
			// formula.length());

		}
		return formula;
	}

	public Object evaluate_excel(String formula, int row, int col,
			JTable table, String idtablero) {
		Object obj = null;
		formula = this.process_cell(formula, row, col, table, idtablero);
		formula = this.process_parameters(formula, row, col, table);
		JEP funcion = new JEP();

		funcion.parseExpression(formula);
		obj = funcion.getValue();
		System.out.println("Excel Formula" + formula);
		System.out.println("Resultado Formula" + formula + "=" + obj);
		return obj;
	}

	public Color[][][] load_colors(String idtablero) {
		Color[][][] colors = new Color[this.allow_rows][this.allow_columns.length][2];
		for (int i = 0; i < this.allow_rows; i++) {
			for (int j = 0; j < this.allow_columns.length; j++) {
				System.out.println("Cargando Variables de Color " + i);
				colors[i][j][0] = new Color(255, 255, 255);
				colors[i][j][1] = new Color(0, 0, 0);
			}
		}
		Object[][] results = data.getColors(idtablero);
		if (results != null) {
			if (results.length > 0) {

				for (int i = 0; i < results.length; i++) {
					System.out.println("Cargando Variables " + i);
					String fila = (String) results[i][0];

					String columna = (String) results[i][1];

					String foreground = (String) results[i][2];
					String background = (String) results[i][3];
					int row = -1;
					try {
						row = new Integer(fila);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					int col = -1;
					try {
						col = this.getColumn(columna);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (row >= 0 & col >= 0) {
						colors[row][col][0] = this.getColor(background);
						colors[row][col][1] = this.getColor(foreground);

					}

				}

			}
		}
		return colors;
	}

	public void load_variables(String idtablero, JTable table) {
		Object[][] results = data.getVariables(idtablero);
		estado = "Cargando Variables de " + idtablero;

		if (results != null) {
			if (results.length > 0) {

				for (int i = 0; i < results.length; i++) {

					String fila = (String) results[i][0];

					String columna = (String) results[i][1];

					String formula = (String) results[i][2];
					int row = -1;
					try {
						row = new Integer(fila);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					int col = -1;
					try {
						col = this.getColumn(columna);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (row >= 0 & col >= 0) {
						//System.out.println("//////========== " + idtablero+ "(" + row + "," + col + ")==========\\\\");
						Object valor = this.evaluate_formula(formula, row, col,
								table, idtablero);

						table.setValueAt(valor, row, col);
						//System.out.println("\\\\\\========== " + idtablero+ "(" + row + "," + col + ")==========/////");
					}

				}

			}
		}
	}

	public void guardar() {
		System.out.println("GUARDAR");
		int index = frame.getJTabbedPane().getSelectedIndex();
		String idtablero = frame.getJTabbedPane().getTitleAt(index);
		JScrollPane scroll = (JScrollPane) frame.getJTabbedPane()
				.getComponentAt(index);
		System.out.println(">" + scroll);
		javax.swing.JViewport viewport = (javax.swing.JViewport) scroll
				.getComponent(0);

		JTable table = (JTable) viewport.getView();
		int row = table.getSelectedRow();
		int col = table.getSelectedColumn();
		String detalle = frame.get_txt_detalle().getText();
		detalle = detalle.replaceAll("'", "''");
		String grafica = frame.get_txt_grafica().getText();
		grafica = grafica.replaceAll("'", "''");
		String nombre = frame.get_txt_nombre().getText();
		if (row >= 0 & col >= 0) {
			String _row = "" + row;
			String _column = this.getColumnName(col);
			String valor = frame.get_txt_formula().getText();
			valor = valor.replaceAll("'", "''");
			String foreground = frame.get_txt_foreground().getText();
			String background = frame.get_txt_background().getText();
			data.guardar(valor, _row, _column, foreground, background,
					idtablero, detalle, nombre, grafica);
			Object _valor = this.evaluate_formula(valor, row, col,
					table, idtablero);
			
			table.setValueAt(_valor, row, col);
			
			this.colors.get(index)[row][col][0]=this.getColor(background);
			this.colors.get(index)[row][col][1]=this.getColor(foreground);
			table.repaint(); 
		}

	}

	
	public void setInitialDate() {
		this.fechas();
		String aux = data.getSystemDate();
		frame.get_txt_fecha_hasta().setText(aux);
		System.out.println("System Date"+aux);
		String s1 = frame.get_txt_fecha_hasta().getText();
		String s2 = this.getMonthRoll(s1);
		frame.get_txt_fecha_desde().setText(s2);
		// frame.get_txt_fecha_hasta().setText("28-02-2010");
		// frame.get_txt_fecha_desde().setText("01-02-2010");
		this.recalculate_dias_trabajo();
		
	}

public void fechas(){
	
	frame.get_lst_anio().removeItemListener(this.getConstructor().getItemListener());
	frame.get_lst_mes().removeItemListener(this.getConstructor().getItemListener());
		Calendar Cal=Calendar.getInstance();
		int year=Cal.get(Calendar.YEAR);
		int m=Cal.get(Calendar.MONTH);
		for (int i=year-5;i<year+5;i++){
			frame.get_lst_anio().addItem(""+(i));	
		}
		
		frame.get_lst_anio().setSelectedIndex(5);
		
		frame.get_lst_mes().setSelectedIndex(m);
		frame.get_lst_anio().addItemListener(this.getConstructor().getItemListener());
		frame.get_lst_mes().addItemListener(this.getConstructor().getItemListener());
	}
	public void recalculate_dias_trabajo() {
		String s1 = frame.get_txt_fecha_hasta().getText();
		int days = this.getDaysLeft(s1);
		int worked = this.getDaysWorked(s1);
		frame.get_txt_dias_restantes().setText("" + days);
		frame.get_txt_dias_trabajados().setText("" + worked);

	}

	public String getDaysRoll(String fecha_actual, int days) {
		Date today = new Convertidor().getDate(fecha_actual);
		java.util.GregorianCalendar _today = new java.util.GregorianCalendar();
		_today.setTime(today);
		_today.add(Calendar.DAY_OF_YEAR, days);
		Date before = _today.getTime();
		String s2 = new Convertidor().getDateWithFormat("dd-MM-yyyy", before);
		return s2;
	}

	public String getMonthRoll(String fecha_actual) {
		Date today = new Convertidor().getDate(fecha_actual);
		java.util.GregorianCalendar _today = new java.util.GregorianCalendar();
		_today.setTime(today);
		_today.set(Calendar.DAY_OF_MONTH, 1);
		// _today.add(Calendar.DAY_OF_YEAR, days);
		Date before = _today.getTime();
		String s2 = new Convertidor().getDateWithFormat("dd-MM-yyyy", before);
		return s2;
	}

	public String getMonthRollMax(String fecha_actual) {
		Date today = new Convertidor().getDate(fecha_actual);
		java.util.GregorianCalendar _today = new java.util.GregorianCalendar();
		_today.setTime(today);
		_today.set(Calendar.DAY_OF_MONTH, _today
				.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));
		// _today.add(Calendar.DAY_OF_YEAR, days);
		Date before = _today.getTime();
		String s2 = new Convertidor().getDateWithFormat("dd-MM-yyyy", before);
		return s2;
	}

	//
	public int getDaysLeft(String fecha_actual) {
		String s1 = data.getSystemDate();
		String s2 = this.getMonthRollMax(s1);
		String idtablero = "";
		int no = data.getDiasNoLaborables(s1, s2, idtablero);
		Date today = new Convertidor().getDate(fecha_actual);
		java.util.GregorianCalendar _today = new java.util.GregorianCalendar();
		java.util.GregorianCalendar _left = new java.util.GregorianCalendar();

		_today.setTime(today);
		_left.setTime(today);

		_left.set(Calendar.DAY_OF_MONTH, _left
				.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));
		long diff = _left.getTimeInMillis() - _today.getTimeInMillis();
		Long diffDays = diff / (24 * 60 * 60 * 1000);
		int days = diffDays.intValue();
		days -= no;
		if (days < 0) {
			days = 0;
		}
		return days;
	}

	public int getDaysWorked(String fecha_actual) {
		String s1 = data.getSystemDate();
		String s2 = this.getMonthRoll(s1);
		String idtablero = "";
		int no = data.getDiasNoLaborables(s2, s1, idtablero);
		Date today = new Convertidor().getDate(fecha_actual);
		java.util.GregorianCalendar _today = new java.util.GregorianCalendar();
		java.util.GregorianCalendar _left = new java.util.GregorianCalendar();
		_today.setTime(today);
		_today.set(Calendar.DAY_OF_MONTH, 1);
		_left.setTime(today);

		long diff = _left.getTimeInMillis() - _today.getTimeInMillis();
		Long diffDays = diff / (24 * 60 * 60 * 1000);
		int dias = diffDays.intValue();
		dias++;
		dias -= no;
		return dias;
	}

	private aplicacion.herramientas.java.buscadores.Fecha bFecha = null;

	public void BuscarFecha(JTextField tx) {
		if (bFecha == null) {
			bFecha = new aplicacion.herramientas.java.buscadores.Fecha(this
					.getConstructor());
		}

		bFecha.Buscar(tx);
	}

	public void buscar_fecha_desde() {
		this.BuscarFecha(frame.get_txt_fecha_desde());
	}

	public void buscar_fecha_hasta() {
		this.BuscarFecha(frame.get_txt_fecha_hasta());
	}

	public void evaluate_fecha_desde(JTextField tx) {

	}

	public void evaluar_fecha_desde(JTextField txt) {
		String s = txt.getText();
		DateFormat formatter;
		Date date = null;
		boolean error = false;
		String s1 = "";
		try {
			formatter = new SimpleDateFormat("dd-MM-yyyy");
			date = (Date) formatter.parse(s);
			s1 = formatter.format(date);
		} catch (Exception e) {
			error = true;
			JOptionPane.showMessageDialog(frame, e.getMessage(),
					"Error en Fecha", JOptionPane.ERROR_MESSAGE);
		}
		if (!error) {
			txt.setText(s1);
			frame.get_txt_fecha_hasta().requestFocusInWindow();

		} else {
			txt.requestFocusInWindow();
		}
	}

	public boolean fechas_ok() {
		boolean ok = false;
		String s = frame.get_txt_fecha_hasta().getText();
		DateFormat formatter;
		Date date = null;
		boolean error = false;
		String s1 = "";
		try {
			formatter = new SimpleDateFormat("dd-MM-yyyy");
			date = (Date) formatter.parse(s);
			s1 = formatter.format(date);
		} catch (Exception e) {
			error = true;
			error("Error en Fecha");
		}
		if (!error) {
			int n = new Convertidor().getMayorDate(s1, frame
					.get_txt_fecha_desde().getText());
			ok = (n > 0);
		}
		return ok;
	}

	public void evaluar_fecha_hasta(JTextField txt) {
		String s = txt.getText();
		DateFormat formatter;
		Date date = null;
		boolean error = false;
		String s1 = "";
		try {
			formatter = new SimpleDateFormat("dd-MM-yyyy");
			date = (Date) formatter.parse(s);
			s1 = formatter.format(date);
		} catch (Exception e) {
			error = true;
			error("Error en Fecha");
		}
		if (!error) {
			txt.setText(s1);
			// this.simulate();
		} else {
			txt.requestFocusInWindow();
		}
	}

	private boolean checkFecha(String fecha) {
		boolean error = false;
		DateFormat formatter;
		try {
			formatter = new SimpleDateFormat("dd-MM-yyyy");
			formatter.parse(fecha);

		} catch (Exception e) {
			error = true;
			JOptionPane.showMessageDialog(frame, e.getMessage(),
					"Error en Fecha", JOptionPane.ERROR_MESSAGE);
		}
		return error;
	}

	public void loadTableros() {
		frame.getJTabbedPane().removeAll();
		String[] tableros = data.getTableros();
		this.colors = new ArrayList<Color[][][]>();
		for (int i = 0; i < tableros.length; i++) {
			colors.add(this.load_colors(tableros[i]));
			this.addTablero(tableros[i]);
		}
	}

	class _taskCargar {
		_taskCargar() {
			_taskworkCargar();
		}
	}

	public void _taskworkCargar() {

		estado = "Cargando Datos";
		this.recalculate_dias_trabajo();
		this.loadTableros();
		done = true;
	}

	public void goCargar() {

		if (this.fechas_ok()) {
			frame.getJProgressBar().setIndeterminate(true);
			this.createTimer();

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
		} else {
			error("Las fechas del periodo deben ser correctas => (Desde Menor a Hasta)");
		}

	}

	private PieDataset createSampleDataset() {

		final DefaultPieDataset result = new DefaultPieDataset();
		result.setValue("Java", new Double(43.2));
		result.setValue("Visual Basic", new Double(10.0));
		result.setValue("C/C++", new Double(17.5));
		result.setValue("PHP", new Double(32.5));
		result.setValue("Perl", new Double(1.0));
		return result;

	}

	private JFreeChart createChart(final PieDataset dataset, String nombre) {

		final JFreeChart chart = ChartFactory.createPieChart3D(nombre, // chart
				// title
				dataset, // data
				true, // include legend
				true, false);

		final PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setStartAngle(290);
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setForegroundAlpha(0.5f);
		plot.setNoDataMessage("No data to display");
		return chart;

	}

	public JFreeChart createChart(XYDataset dataset, String nombre) {

		JFreeChart chart = ChartFactory.createTimeSeriesChart(nombre, // title
				"Fecha", // x-axis label
				"Valor", // y-axis label
				dataset, // data
				true, // create legend?
				true, // generate tooltips?
				false // generate URLs?
				);

		chart.setBackgroundPaint(Color.white);

		XYPlot plot = (XYPlot) chart.getPlot();
		plot.setBackgroundPaint(Color.lightGray);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);
		plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
		plot.setDomainCrosshairVisible(true);
		plot.setRangeCrosshairVisible(true);

		XYItemRenderer r = plot.getRenderer();
		if (r instanceof XYLineAndShapeRenderer) {
			XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
			renderer.setBaseShapesVisible(true);
			renderer.setBaseShapesFilled(true);
			renderer.setDrawSeriesLineAsPath(true);
		}

		DateAxis axis = (DateAxis) plot.getDomainAxis();
		axis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));

		return chart;

	}

	public static XYDataset createDataset(TimeSeries s1) {

		TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(s1);
		// dataset.addSeries(s2);
		return dataset;

	}

	public JPanel createDemoPanel(TimeSeries s1, String nombre) {
		JFreeChart chart = createChart(createDataset(s1), nombre);
		ChartPanel panel = new ChartPanel(chart);
		panel.setFillZoomRectangle(true);
		panel.setMouseWheelEnabled(true);
		return panel;
	}

	public JPanel createDemoPanel(DefaultPieDataset s1, String nombre) {
		JFreeChart chart = this.createChart(s1, nombre);
		ChartPanel panel = new ChartPanel(chart);
		panel.setFillZoomRectangle(true);
		panel.setMouseWheelEnabled(true);
		return panel;
	}

	public void Importar() {
		JFileChooser JF = new JFileChooser();
		int fx = JF.showOpenDialog(frame);
		if (fx == JFileChooser.APPROVE_OPTION) {
			File file = JF.getSelectedFile();

			XML xml = new XML();
			xml.setFile(file);
			xml.readAll();
			Element root = xml.getRoot();
			// System.out.println(root.getId());
			if (root.getId().compareTo("Tableros") == 0) {
				List<Element> tableros = root.getElements();
				for (int i = 0; i < tableros.size(); i++) {
					Element tablero = tableros.get(i);
					String idtablero = tablero.getAtributo("idtablero")
							.getValor();
					System.out.println("Tablero> " + idtablero);
					boolean existe = data.existe(idtablero);
					boolean ok = true;
					if (existe) {

						ok = confirmar(
								"Confirme Para Sobrescribir la Configuracion del Tablero "
										+ idtablero + ": ", 2);
						if (ok) {
							this._eliminar(idtablero);
							this.insertarNuevo(idtablero);

						}
					} else {
						this.insertarNuevo(idtablero);

					}
					if (ok) {
						List<Element> celdas = tablero.getElements();
						if (celdas.size() > 0) {
							for (int k = 0; k < celdas.size(); k++) {
								Element celda = celdas.get(k);
								String fila = celda.getAtributo("fila")
										.getValor();
								String columna = celda.getAtributo("columna")
										.getValor(true);
								String formula = celda.getAtributo("formula")
										.getValor(true);
								formula = formula.replaceAll("'", "''");
								String formula_detalle = celda.getAtributo(
										"formula_detalle").getValor(true);
								formula_detalle = formula_detalle.replaceAll(
										"'", "''");
								String formula_grafica = celda.getAtributo(
										"formula_grafica").getValor(true);
								formula_grafica = formula_grafica.replaceAll(
										"'", "''");
								String foreground = celda.getAtributo(
										"foreground").getValor(true);
								String background = celda.getAtributo(
										"background").getValor(true);
								String nombre = celda.getAtributo("nombre")
										.getValor(true);
								data.guardar(formula, fila, columna,
										foreground, background, idtablero,
										formula_detalle, nombre,
										formula_grafica);
							}
						}
					}
				}
				this.goCargar();
			}

		}
	}

	public void Exportar() {
		JFileChooser JF = new JFileChooser();
		int fx = JF.showSaveDialog(frame);
		if (fx == JFileChooser.APPROVE_OPTION) {
			File file = JF.getSelectedFile();
			BufferedWriter out = null;

			file = file.getAbsoluteFile();
			if (file.exists())
				file.delete();
			try {
				file.createNewFile();
				out = new BufferedWriter(new FileWriter(file));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Element root = new Element("Tableros");
			int tableros = frame.getJTabbedPane().getTabCount();
			for (int i = 0; i < tableros; i++) {
				Element t = new Element("Tablero");
				String idtablero = frame.getJTabbedPane().getTitleAt(i);
				Atributo a = new Atributo("idtablero");
				a.setValor(idtablero);
				t.addAtribute(a);
				Object[][] variables = data.getVariablesCompletas(idtablero);
				if (variables != null) {
					if (variables.length > 0) {
						for (int j = 0; j < variables.length; j++) {
							Element v = new Element("celda");

							a = new Atributo("fila");
							a.setValor((String) variables[j][0]);
							v.addAtribute(a);
							a = new Atributo("columna");
							a.setValor((String) variables[j][1]);
							v.addAtribute(a);
							a = new Atributo("formula");

							a.setValor(((String) variables[j][2]));

							v.addAtribute(a);
							a = new Atributo("background");
							a.setValor((String) variables[j][3]);
							v.addAtribute(a);
							a = new Atributo("foreground");
							a.setValor((String) variables[j][4]);
							v.addAtribute(a);
							a = new Atributo("formula_detalle");
							a.setValor((String) variables[j][5]);
							v.addAtribute(a);
							a = new Atributo("formula_grafica");
							a.setValor((String) variables[j][6]);
							v.addAtribute(a);
							a = new Atributo("nombre");
							a.setValor((String) variables[j][7]);
							v.addAtribute(a);
							t.addElement(v);
						}
					}
				}
				root.addElement(t);
			}
			String xml = new XML().toString(root);
			System.out.println("Exportar " + xml);
			try {
				out.write(xml);
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block

			}

		}

	}
	
	public void show_time2(){
		String s_year=frame.get_lst_anio().getSelectedItem().toString();
		int year=new Integer(s_year);
		int month=0;
		switch(frame.get_lst_mes().getSelectedIndex()){
		case 0:
			month=Calendar.JANUARY;
		break;
		case 1:
			month=Calendar.FEBRUARY;
		break;
		case 2:
			month=Calendar.MARCH;
		break;
		case 3:
			month=Calendar.APRIL;
		break;
		case 4:
			month=Calendar.MAY;
		break;
		case 5:
			month=Calendar.JUNE;
		break;
		case 6:
			month=Calendar.JULY;
		break;
		case 7:
			month=Calendar.AUGUST;
		break;
		case 8:
			month=Calendar.SEPTEMBER;
		break;
		case 9:
			month=Calendar.OCTOBER;
		break;
		case 10:
			month=Calendar.NOVEMBER;
		break;
		case 11:
			month=Calendar.DECEMBER;
		break;
		}

		Calendar cal = new GregorianCalendar(year, month, 1);
		String default_Format="dd-MM-yyyy"; 
		Locale locale = Locale.getDefault();
	    String s1="";
	    Date date=null;
	    DateFormat formatter;
	    try {
	    	
	    	date = cal.getTime();
	    	formatter = new SimpleDateFormat(default_Format, locale);
	        s1 = formatter.format(date);
	    }catch(Exception e){
	        	
	    }		
	    cal.set(Calendar.DAY_OF_MONTH, cal
				.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));
	    String s2="";
try {
	    	
	    	date = cal.getTime();
	    	formatter = new SimpleDateFormat(default_Format, locale);
	        s2 = formatter.format(date);
	    }catch(Exception e){
	        	
	    }		
	    frame.get_txt_fecha_desde().setText(s1);
		frame.get_txt_fecha_hasta().setText(s2);
	}
	public int get_days(){
		String s_year=frame.get_lst_anio().getSelectedItem().toString();
		int year=new Integer(s_year);
		int month=0;
		switch(frame.get_lst_mes().getSelectedIndex()){
		case 0:
			month=Calendar.JANUARY;
		break;
		case 1:
			month=Calendar.FEBRUARY;
		break;
		case 2:
			month=Calendar.MARCH;
		break;
		case 3:
			month=Calendar.APRIL;
		break;
		case 4:
			month=Calendar.MAY;
		break;
		case 5:
			month=Calendar.JUNE;
		break;
		case 6:
			month=Calendar.JULY;
		break;
		case 7:
			month=Calendar.AUGUST;
		break;
		case 8:
			month=Calendar.SEPTEMBER;
		break;
		case 9:
			month=Calendar.OCTOBER;
		break;
		case 10:
			month=Calendar.NOVEMBER;
		break;
		case 11:
			month=Calendar.DECEMBER;
		break;
		}

		Calendar cal = new GregorianCalendar(year, month, 1);
		int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		//days++;
		//System.out.println("dias del mes "+days);
		return days;
		}

	public int get_day_week(){
		String s_year=frame.get_lst_anio().getSelectedItem().toString();
		int year=new Integer(s_year);
		int month=0;
		switch(frame.get_lst_mes().getSelectedIndex()){
		case 0:
			month=Calendar.JANUARY;
		break;
		case 1:
			month=Calendar.FEBRUARY;
		break;
		case 2:
			month=Calendar.MARCH;
		break;
		case 3:
			month=Calendar.APRIL;
		break;
		case 4:
			month=Calendar.MAY;
		break;
		case 5:
			month=Calendar.JUNE;
		break;
		case 6:
			month=Calendar.JULY;
		break;
		case 7:
			month=Calendar.AUGUST;
		break;
		case 8:
			month=Calendar.SEPTEMBER;
		break;
		case 9:
			month=Calendar.OCTOBER;
		break;
		case 10:
			month=Calendar.NOVEMBER;
		break;
		case 11:
			month=Calendar.DECEMBER;
		break;
		}

		Calendar cal = new GregorianCalendar(year, month, 1);
		int day=cal.get(Calendar.DAY_OF_WEEK)-1;
		return day;
	}

}