package aplicacion.cliente.gestion.logic;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; //import java.sql.Date;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Date;
import javax.swing.*;

import aplicacion.herramientas.java.Convertidor;
import aplicacion.modelo.interfaces.*;

import aplicacion.herramientas.java.launcher.logic.SwingWorker;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Logic;
import aplicacion.modelo.logic.Data;
import aplicacion.herramientas.java.*;

import aplicacion.herramientas.java.sortableselector.logic.Filtro;
import aplicacion.herramientas.java.sortableselector.logic.columna;
import aplicacion.herramientas.java.table.*;
import aplicacion.herramientas.java.table.CellEditor;

import aplicacion.cliente.gestion.gui.*;
import aplicacion.cliente.gestion.interfaces.*;

public class _Logic extends Logic {

	private Timer Timer; // @jve:decl-index=0:
	private Crono crono;
	private String estado = "";
	private int current;
	private int lenght;
	private boolean done, canceled;
	private int errors = 0;

	private Object[][] memoria = null;
	private _Data data;
	private _Frame frame;

	public void setFrame(JFrame frame) {
		this.frame = (_Frame) frame;
		super.setFrame(frame);
	}

	public void setData(Data _data) {
		this.data = (_Data) _data;
		super.setData(_data);
	}

	public void goCargar() {
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

	}

	class _taskCargar {
		_taskCargar() {
			_taskworkCargar();
		}
	}

	/**
	 * codigo|descripcion|categoria|calfa|cbeta|alfa|beta
	 * 
	 * @param results
	 * @return
	 */
	public Object[][] process_data(Object[][] results) {
		Object[][] tmp = new Object[results.length][9];
		String desde = frame.get_txt_desde().getText();
		String hasta = frame.get_txt_hasta().getText();
		boolean saldo_distinto_de_cero = frame
				.get_chk_saldos_distintos_de_cero().isSelected();
		Object[][] ralfa = data.getConsumoAlfa(saldo_distinto_de_cero, desde,
				hasta);
		Object[][] rbeta = data.getConsumoBeta(saldo_distinto_de_cero, desde,
				hasta);
		Object[][] salfa = data.getSaldoAlfa(saldo_distinto_de_cero);
		Object[][] sbeta = data.getSaldoBeta(saldo_distinto_de_cero);
		for (int i = 0; i < results.length; i++) {
			double calfa = 0.0;
			double cbeta = 0.0;
			double alfa = 0.0;
			double beta = 0.0;
			String _calfa = this.getValor(results[i][0].toString(), ralfa);
			String _cbeta = this.getValor(results[i][0].toString(), rbeta);
			String _alfa = this.getValor(results[i][0].toString(), salfa);
			String _beta = this.getValor(results[i][0].toString(), sbeta);
			calfa = new Double(_calfa);
			cbeta = new Double(_cbeta);
			alfa = new Double(_alfa);
			beta = new Double(_beta);
			tmp[i][0] = results[i][0];
			tmp[i][1] = results[i][1];
			tmp[i][2] = results[i][2];
			tmp[i][3] = new Double(results[i][3].toString());
			tmp[i][4] = new Double(results[i][4].toString());
			tmp[i][5] = calfa + cbeta;
			tmp[i][6] = alfa;
			tmp[i][7] = beta;
			tmp[i][8] = alfa + beta;
		}
		return tmp;
	}

	public String getDaysRoll(String fecha_actual, int days) {
		Date today = new Convertidor().getDate(fecha_actual);
		java.util.GregorianCalendar _today = new java.util.GregorianCalendar();
		_today.setTime(today);
		// _today.roll(Calendar.DAY_OF_YEAR, days);
		_today.add(Calendar.DAY_OF_YEAR, days);
		Date before = _today.getTime();
		String s2 = new Convertidor().getDateWithFormat("dd-MM-yyyy", before);
		return s2;
	}

	public int getCuenta(String id, Object[][] results) {
		int row = 0;
		boolean found = false;
		while (row < results.length & !found) {
			found = id.compareTo(results[row][0].toString()) == 0;
			if (!found)
				row++;
		}
		if (!found) {
			row = -1;
		}
		return row;
	}

	public String getValor(String id, Object[][] results) {
		String valor = "0.0";
		int row = this.getCuenta(id, results);
		if (row >= 0) {
			valor = results[row][1].toString();
		}
		return valor;
	}

	public void _taskworkCargar() {
		estado = "Cargando Datos";
		frame.getJProgressBar().setIndeterminate(true);
		Object[][] results = null;
		boolean saldo_distinto_de_cero = frame
				.get_chk_saldos_distintos_de_cero().isSelected();
		results = data.getSaldos(saldo_distinto_de_cero);

		results = this.process_data(results);
		final Object[][] _results = results;

		Runnable _execute = new Runnable() {
			public void run() {
				_taskworkCargarSwing(_results);
			}
		};
		this.invokeLater(_execute);

		done = true;
	}

	private CustomTable crearTablaDeItems(boolean editable) {
		CustomTable Table = new CustomTable();
		Column col = new Column();
		CellEditor pce = null;

		col = new Column();
		col.setName("idcliente");
		col.setWidth(80);
		col.setEditable(false);
		Table.addColumn(col);

		col = new Column();
		col.setName("Nombre");
		col.setWidth(200);
		Table.addColumn(col);

		col = new Column();
		col.setName("Categoria");
		col.setWidth(120);
		col.setEditable(true);
		col.setClass(String.class);
		ComboBoxEditor ce = new ComboBoxEditor();
		ce.setName(_Interface._cb_categoria);
		ce.addItemListener(this.getConstructor().getItemListener());
		ce.setValues(data.getCategorias());
		col.setCellEditor(ce.getCellEditor());
		// col.setCellRenderer(new MoneyRenderer());
		col.setAligment(JTextField.RIGHT);
		Table.addColumn(col);

		col = new Column();
		col.setName("Limite");
		col.setWidth(80);
		col.setEditable(true);
		col.setClass(Double.class);
		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_limite);
		pce.setAligment(JTextField.RIGHT);
		col.setCellEditor(pce.getCellEditor());
		// col.setCellRenderer(new MoneyRenderer());
		col.setAligment(JTextField.RIGHT);
		Table.addColumn(col);

		col = new Column();
		col.setName("Descuento");
		col.setWidth(80);
		col.setEditable(true);
		col.setClass(Double.class);
		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_descuento);
		pce.setAligment(JTextField.RIGHT);
		col.setCellEditor(pce.getCellEditor());
		// col.setCellRenderer(new MoneyRenderer());
		col.setAligment(JTextField.RIGHT);
		Table.addColumn(col);

		col = new Column();
		col.setName("Consumo");
		col.setWidth(100);
		col.setEditable(false);
		col.setClass(Double.class);
		// col.setCellRenderer(new MoneyRenderer());
		col.setAligment(JTextField.RIGHT);
		Table.addColumn(col);

		col = new Column();
		col.setName("Alfa");
		col.setWidth(100);
		col.setEditable(false);
		col.setClass(Double.class);
		// col.setCellRenderer(new MoneyRenderer());
		col.setAligment(JTextField.RIGHT);
		Table.addColumn(col);

		col = new Column();
		col.setName("Beta");
		col.setWidth(100);
		col.setEditable(false);
		col.setClass(Double.class);
		// col.setCellRenderer(new MoneyRenderer());
		col.setAligment(JTextField.RIGHT);
		Table.addColumn(col);

		col = new Column();
		col.setName("Total");
		col.setWidth(110);
		col.setEditable(false);
		col.setClass(Double.class);
		// col.setCellRenderer(new MoneyRenderer());
		col.setAligment(JTextField.RIGHT);
		Table.addColumn(col);
		
		return Table;
	}

	public Object[][] processData(Object[][] results) {

		Convertidor Cv = new Convertidor();
		for (int i = 0; i < results.length; i++) {
			for (int j = 3; j < results[0].length; j++) {
				String value = results[i][j].toString();
				Double tmp = 0.0;
				try {
					tmp = new Double(value);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				tmp = Cv.roundDouble(tmp, 2);
				results[i][j] = tmp;
			}

		}
		return results;
	}

	public void evaluate_selection(JComboBox cb, int row, JTable table) {
		if (cb.getSelectedIndex() >= 0) {

			String cuenta = table.getValueAt(row, 0).toString();
			String id = data.getCategoriaId(cb.getSelectedItem().toString());
			String q = data.getUpdate(cuenta, id);
			data.clearBatch();
			data.addBatch(q);
			data.executeBatch();
		}

	}

	public void create_table(Object[][] results) {
		if (results != null) {

			CustomTable Table = this.crearTablaDeItems(false);
			results = this.processData(results);
			Table.setData(results);
			Table.build();
			Table.fillData();
			JTable _table = Table.getTable();
			_table.setName(_Interface._table);
			_table.addMouseListener(this.getConstructor().getMouseListener());
			_table.addKeyListener(this.getConstructor().getKeyListener());
			_table.getTableHeader().addMouseListener(
					this.getConstructor().getMouseListener());
			_table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP,0),"none" );
			_table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0),"none" );
			frame.setJTable(_table);
			this.recalculateSaldos();
		} else {
			frame.setJTable(null);
		}
	}

	public void _taskworkCargarSwing(Object[][] results) {
		create_table(results);

	}

	public void clean() {
		frame.setJTable(null);

	}

	public void focus() {
		// frame.get_txt_idproveedor().requestFocusInWindow();
	}

	public void cancelar_tarea() {
		if (preguntar("confirmar", "Cancela Tarea?")) {
			this.canceled = true;
		}
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

	public void recalculateSaldos() {
		double saldo_acreedor = 0.0;
		double saldo_deudor = 0.0;
		double saldo_final = 0.0;
		for (int i = 0; i < frame.getJTable().getRowCount(); i++) {
			double saldo = 0;
			saldo = (Double) frame.getJTable().getValueAt(i, 6);
			if (saldo > 0) {
				saldo_deudor += saldo;
			}
			if (saldo < 0) {
				saldo_acreedor += -saldo;
			}
			saldo_final += -saldo;
		}
		frame.get_txt_saldo().setText(
				new Convertidor().getMoney(saldo_final, 2));
		if (saldo_deudor > 0) {
			frame.get_txt_saldo_deudor().setForeground(Color.red);
		}
		if (saldo_acreedor > 0) {
			frame.get_txt_saldo_acreedor().setForeground(Color.green);
		}
		frame.get_txt_saldo_deudor().setText(
				new Convertidor().getMoney(saldo_deudor, 2));
		frame.get_txt_saldo_acreedor().setText(
				new Convertidor().getMoney(saldo_acreedor, 2));
	}

	public void cargar() {

		this.goCargar();
	}

	public void verMaestro() {
		int row = frame.getJTable().getSelectedRow();
		this.verMaestro(row);
	}

	public void verMaestro(int row) {

		String idcliente = frame.getJTable().getValueAt(row, 0).toString();
		if (idcliente.compareTo("") != 0) {

			aplicacion.cliente.archivo.constructor._Constructor CC = new aplicacion.cliente.archivo.constructor._Constructor();
			CC.setParameter(_parametros.connector, this._data
					.getConnectionHandler());
			CC.setParameter(_parametros.LookAndFeel, this.getConstructor()
					.getLookAndFeelTheme());
			CC
					.setParameter(
							aplicacion.cliente.archivo.interfaces._Parametros.idcliente,
							idcliente);
			CC.build(this.getConstructor());
			CC.init();

		}
	}

	public void getToday() {
		String hasta = new Convertidor().getDateWithFormat("dd-MM-yyyy");
		frame.get_txt_hasta().setText(hasta);
		String desde = this.getDaysRoll(hasta, -90);
		frame.get_txt_desde().setText(desde);
	}

	private aplicacion.herramientas.java.ireport.constructor._Constructor reporte = null;

	public void reporte() {
		if (reporte != null) {
			reporte.dispose();
		}
		reporte = new aplicacion.herramientas.java.ireport.constructor._Constructor();
		HashMap param = new HashMap();
		param.put("desde", "112010001");
		param.put("hasta", "112019999");
		reporte.setParameter(_parametros.LookAndFeel, this.getConstructor()
				.getLookAndFeelTheme());
		reporte.setParameter(_parametros.connector, this.getConstructor()
				.getConnectionHandler());
		reporte
				.setParameter(
						aplicacion.herramientas.java.ireport.interfaces._parametros.parametros,
						param);
		reporte
				.setParameter(
						aplicacion.herramientas.java.ireport.interfaces._parametros.reporte,
						"reporte_alfabeta.jasper");
		reporte.build(this.getConstructor());
		reporte.init();
	}

	public void eval_limite(JTextField tx, int row, int col) {
		String limite = tx.getText();
		limite = limite.replaceAll(",", "");
		double _limite = 0.0;
		try {
			_limite = new Double(limite);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (_limite < 0) {
			error("Valor de Limite de Credito Incorrecto ");
			tx.requestFocusInWindow();
		} else {
			String cuenta = frame.getJTable().getValueAt(row, 0).toString();
			String q = data.getUpdateLimite(cuenta, _limite);
			data.clearBatch();
			data.addBatch(q);
			data.executeBatch();
			if (row < frame.getJTable().getRowCount() - 1) {
				frame.getJTable().changeSelection(row + 1, col, false, false);
				frame.getJTable().editCellAt(row + 1, col);
				frame.getJTable().transferFocus();
			}
		}
	}

	public void eval_descuento(JTextField tx, int row, int col) {
		String descuento = tx.getText();
		descuento = descuento.replaceAll(",", "");
		double _descuento = 0.0;
		try {
			_descuento = new Double(descuento);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (_descuento < 0 | _descuento > 100) {
			error("Valor de Descuento incorrecto ");
			tx.requestFocusInWindow();
		} else {
			String cuenta = frame.getJTable().getValueAt(row, 0).toString();
			String q = data.getUpdateDescuento(cuenta, _descuento);
			data.clearBatch();
			data.addBatch(q);
			data.executeBatch();
			if (row < frame.getJTable().getRowCount() - 1) {
				frame.getJTable().changeSelection(row + 1, col, false, false);
				frame.getJTable().editCellAt(row + 1, col);
				frame.getJTable().transferFocus();
			}
		}
	}
	
	
	public void up(int row,int col){
		try {
			frame.getJTable().getCellEditor().stopCellEditing();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (row>0){
			frame.getJTable().requestFocusInWindow();
			frame.getJTable().changeSelection(row-1, col, false, false);
			frame.getJTable().editCellAt(row-1, col);
			frame.getJTable().transferFocus();	
		}
		
	}
	
	public void down(int row,int col){
		try {
			frame.getJTable().getCellEditor().stopCellEditing();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (row<frame.getJTable().getRowCount()-1){
			frame.getJTable().requestFocusInWindow();
			frame.getJTable().changeSelection(row+1, col, false, false);
			frame.getJTable().editCellAt(row+1, col);
			frame.getJTable().transferFocus();	
		}
		
	}
}
