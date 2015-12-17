package aplicacion.gestion.caja.manejo.logic;

import java.awt.Color;
import java.awt.Font;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;

import javax.swing.table.*;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import aplicacion.herramientas.java.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


import aplicacion.gestion.caja.manejo.gui._Frame;
import aplicacion.gestion.caja.manejo.interfaces._Interface;


import java.awt.Rectangle;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Logic;
import aplicacion.modelo.logic.Data;
import aplicacion.herramientas.java.calendario.constructor.*;
import aplicacion.herramientas.java.comprobantes.fvn;
import aplicacion.herramientas.java.launcher.logic.SwingWorker;
import aplicacion.herramientas.java.table.*;
import aplicacion.herramientas.java.*;

public class _Logic extends Logic {
	private _Frame frame = null;
	private _Data data = null;
	private Timer Timer;  //  @jve:decl-index=0:
	private Crono crono;
	private String estado="";
	private int current;
	private int lenght;
	private boolean done,canceled;
	private int errors=0;
	
	

	public void setFrame(JFrame _frame) {
		this.frame = (_Frame) _frame;
		super.setFrame(_frame);
	}

	public void setData(Data _data) {
		this.data = (_Data) _data;
		super.setData(_data);
	}


	class _taskCargar {
		_taskCargar() {
			_taskworkCargar();
		}
	}
	
	private void taskEfectivo(String desde,String hasta,String idcaja){
		String cuenta="111010001";
		String desdei=this.getDaysRoll(desde, -1);
		Object[][] si = this.data.get_Saldo(idcaja, cuenta, desdei);
		//Object[][] si=null;
		
		Object[][] results = this.data.get_movimientos(idcaja,cuenta,desde,hasta);
		Object[][] tmp =null;
		if (results!=null){
			if (results.length>0){
			tmp=new Object[results.length+1][results[0].length];
			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			try {
				tmp[0][0] = (Date) formatter
				.parse(desde);
			} catch (ParseException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			tmp[0][1]="";
			tmp[0][2]="";
			tmp[0][3]="";
			tmp[0][4]="Saldo Inicial al "+desde;
			tmp[0][5]="";
			double debe = 0.0;
			double haber = 0.0;
			double saldo = 0.0;
			if (si!=null){
				if (si.length>0){
					try {
						debe = new Double(si[0][0].toString());
						haber = new Double(si[0][1].toString());
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				
				}
			}
			
			tmp[0][6]=debe;
			tmp[0][7]=haber;
			saldo=debe-haber;
			tmp[0][8]=saldo;
			for (int i = 0; i < results.length; i++) {
				
				try {
					results[i][0] = (Date) formatter
							.parse(results[i][0].toString());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				debe = 0.0;
				haber = 0.0;
				
				try {
					debe = new Double(results[i][6].toString());
					haber = new Double(results[i][7].toString());
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				results[i][6] = debe;
				results[i][7] = haber;
				saldo=saldo+(debe-haber);
				results[i][8] = saldo;
			}
			
			for (int i = 0; i < results.length; i++) {
				//tmp[i][0]=false;
				    for (int j = 0; j < results[0].length; j++) {
				    	tmp[i+1][j]=results[i][j];
				    }
			}
			
		}}
		final Object[][] _results=tmp;
		final String _idcuenta=cuenta;
		Runnable _execute = new Runnable() {
	        public void run() {
	        	_taskworkCargarSwing(_results,_idcuenta);
	        }
		};
		this.invokeLater(_execute);	
	}
	
	
	private void taskEfectivoAlfa(String desde,String hasta,String idcaja){
		String cuenta="111010001";
		String desdei=this.getDaysRoll(desde, -1);
		Object[][] si = this.data.get_SaldoAlfa(idcaja, cuenta, desdei);
		//Object[][] si=null;
		
		Object[][] results = this.data.get_movimientosAlfa(idcaja,cuenta,desde,hasta);
		Object[][] tmp =null;
		if (results!=null){
			if (results.length>0){
				
			tmp=new Object[results.length+1][results[0].length];
			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			try {
				tmp[0][0] = (Date) formatter
				.parse(desde);
			} catch (ParseException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			tmp[0][1]="";
			tmp[0][2]="";
			tmp[0][3]="";
			tmp[0][4]="Saldo Inicial al "+desde;
			tmp[0][5]="";
			double debe = 0.0;
			double haber = 0.0;
			double saldo = 0.0;
			if (si!=null){
				if (si.length>0){
					try {
						debe = new Double(si[0][0].toString());
						haber = new Double(si[0][1].toString());
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				
				}
			}
			
			tmp[0][6]=debe;
			tmp[0][7]=haber;
			saldo=debe-haber;
			tmp[0][8]=saldo;
			for (int i = 0; i < results.length; i++) {
				
				try {
					results[i][0] = (Date) formatter
							.parse(results[i][0].toString());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				debe = 0.0;
				haber = 0.0;
				
				try {
					debe = new Double(results[i][6].toString());
					haber = new Double(results[i][7].toString());
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				results[i][6] = debe;
				results[i][7] = haber;
				saldo=saldo+(debe-haber);
				results[i][8] = saldo;
			}
			
			for (int i = 0; i < results.length; i++) {
				//tmp[i][0]=false;
				    for (int j = 0; j < results[0].length; j++) {
				    	tmp[i+1][j]=results[i][j];
				    }
			}
			
		}}
		final Object[][] _results=tmp;
		final String _idcuenta=cuenta;
		Runnable _execute = new Runnable() {
	        public void run() {
	        	_taskworkCargarSwingAlfa(_results,_idcuenta);
	        }
		};
		this.invokeLater(_execute);	
	}
	private void taskDolares(String desde,String hasta,String idcaja){
		String cuenta="111010099";
		String desdei=this.getDaysRoll(desde, -1);
		Object[][] si = this.data.get_Saldo(idcaja, cuenta, desdei);
		//Object[][] si=null;
		
		Object[][] results = this.data.get_movimientos(idcaja,cuenta,desde,hasta);
		Object[][] tmp =null;
		if (results!=null){
			if (results.length>0){
			tmp=new Object[results.length+1][results[0].length];
			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			try {
				tmp[0][0] = (Date) formatter
				.parse(desde);
			} catch (ParseException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			tmp[0][1]="";
			tmp[0][2]="";
			tmp[0][3]="";
			tmp[0][4]="Saldo Inicial al "+desde;
			tmp[0][5]="";
			double debe = 0.0;
			double haber = 0.0;
			double saldo = 0.0;
			if (si!=null){
				if (si.length>0){
					try {
						debe = new Double(si[0][0].toString());
						haber = new Double(si[0][1].toString());
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				
				}
			}
			
			tmp[0][6]=debe;
			tmp[0][7]=haber;
			saldo=debe-haber;
			tmp[0][8]=saldo;
			for (int i = 0; i < results.length; i++) {
				
				try {
					results[i][0] = (Date) formatter
							.parse(results[i][0].toString());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				debe = 0.0;
				haber = 0.0;
				
				try {
					debe = new Double(results[i][6].toString());
					haber = new Double(results[i][7].toString());
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				results[i][6] = debe;
				results[i][7] = haber;
				saldo=saldo+(debe-haber);
				results[i][8] = saldo;
			}
			
			for (int i = 0; i < results.length; i++) {
				//tmp[i][0]=false;
				    for (int j = 0; j < results[0].length; j++) {
				    	tmp[i+1][j]=results[i][j];
				    }
			}
			
		}}
		final Object[][] _results=tmp;
		final String _idcuenta=cuenta;
		Runnable _execute = new Runnable() {
	        public void run() {
	        	_taskworkCargarSwingUS(_results,_idcuenta);
	        }
		};
		this.invokeLater(_execute);	
	}
	
	private void taskCheques(String desde,String hasta,String idcaja){
		String cuenta="111010002";
		String desdei=this.getDaysRoll(desde, -1);
		Object[][] si = this.data.get_Saldo(idcaja, cuenta, desdei);
		//Object[][] si=null;
		
		Object[][] results = this.data.get_movimientos(idcaja,cuenta,desde,hasta);
		Object[][] tmp =null;
		if (results!=null){
			if (results.length>0){
			tmp=new Object[results.length+1][results[0].length];
			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			try {
				tmp[0][0] = (Date) formatter
				.parse(desde);
			} catch (ParseException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			tmp[0][1]="";
			tmp[0][2]="";
			tmp[0][3]="";
			tmp[0][4]="Saldo Inicial al "+desde;
			tmp[0][5]="";
			double debe = 0.0;
			double haber = 0.0;
			double saldo = 0.0;
			if (si!=null){
				if (si.length>0){
					try {
						debe = new Double(si[0][0].toString());
						haber = new Double(si[0][1].toString());
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				
				}
			}
			
			tmp[0][6]=debe;
			tmp[0][7]=haber;
			saldo=debe-haber;
			tmp[0][8]=saldo;
			for (int i = 0; i < results.length; i++) {
				
				try {
					results[i][0] = (Date) formatter
							.parse(results[i][0].toString());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				debe = 0.0;
				haber = 0.0;
				
				try {
					debe = new Double(results[i][6].toString());
					haber = new Double(results[i][7].toString());
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				results[i][6] = debe;
				results[i][7] = haber;
				saldo=saldo+(debe-haber);
				results[i][8] = saldo;
			}
			
			for (int i = 0; i < results.length; i++) {
				//tmp[i][0]=false;
				    for (int j = 0; j < results[0].length; j++) {
				    	tmp[i+1][j]=results[i][j];
				    }
			}
			
		}}
		final Object[][] _results=tmp;
		final String _idcuenta=cuenta;
		Runnable _execute = new Runnable() {
	        public void run() {
	        	_taskworkCargarSwingCHT(_results,_idcuenta);
	        }
		};
		this.invokeLater(_execute);	
	}
	
	public void _taskworkCargar(){
		String cuenta="";
		estado="Cargando Datos";
		frame.getJProgressBar().setIndeterminate(true);
		frame.get_txt_arqueo().setText("0.0");
		frame.get_txt_diferencia().setText("0.0");
		frame.get_txt_saldo().setText("0.0");
		String desde=frame.get_txt_desde().getText();
		String hasta=frame.get_txt_hasta().getText();
		String idcaja = frame.get_lst_cajas().getSelectedItem().toString();
		String desdei=this.getDaysRoll(desde, -1);
		this.taskEfectivo( desdei, hasta, idcaja);
		this.taskEfectivoAlfa( desdei, hasta, idcaja);
		this.taskDolares(desdei, hasta, idcaja);
		this.taskCheques(desdei, hasta, idcaja);
		done=true;
	}

	public void _taskworkCargarSwingCHT(Object[][] results,String idcuenta) {
		frame.setJTable4(null);	
		if (results!=null){
			if (results.length>0){
				this.create_tabla(_Interface._table_movimientos_cht,results);
				
				
				
			}
		}
		
       	
	}
	
	public void _taskworkCargarSwingUS(Object[][] results,String idcuenta) {
		frame.setJTable3(null);	
		if (results!=null){
			if (results.length>0){
				this.create_tabla(_Interface._table_movimientos_us,results);
				
				
				
			}
		}
		
       	
	}
	
	public void _taskworkCargarSwingAlfa(Object[][] results,String idcuenta) {
		frame.setJTable_efectivo_alfa(null);
		if (results!=null){
			if (results.length>0){
				
				this.create_tablaAlfa(_Interface._table_movimientosAlfa,results);
				recalculate(frame.getJTable_efectivo_alfa(),frame.get_txt_saldo_alfa());
				
			}
		}
		cargar_arqueo(idcuenta);
		recalculate_coins();
		calculate_difence();
       	
	}
	
	public void _taskworkCargarSwing(Object[][] results,String idcuenta) {
		frame.setJTable(null);	
		if (results!=null){
			if (results.length>0){
				this.create_tabla(_Interface._table_movimientos,results);
				recalculate(frame.getJTable(),frame.get_txt_saldo());
				
			}
		}
		//cargar_arqueo(idcuenta);
		//recalculate_coins();
		//calculate_difence();
    }
	
	public void goCargar() {
		if (this.fechas_ok()){
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
			if (Timer!=null) {
				Timer.start();
			}
			worker.start();	
		}else{
			error("Las fechas del periodo deben ser correctas => (Desde Menor a Hasta)");
		}
		
		
	}	
	
	public void clean() {
		this.getToday();
		cargar_cajas();
		frame.setJTable(null);
		frame.setJTable1(null);
		frame.get_txt_arqueo().setText("");
		frame.get_txt_saldo().setText("");
		frame.get_txt_suma_saldo().setText("");
		frame.get_txt_diferencia().setText("");
	}

	public void getToday() {
		_Frame _frame = (_Frame) this._frame;
		_frame.get_txt_hasta().setText(
				new Convertidor().getDateWithFormat("dd-MM-yyyy"));
		String s1=frame.get_txt_hasta().getText();
		String s2=this.getDaysRoll(s1, -60);
		frame.get_txt_desde().setText(s2);
	}

	public _Data getData() {
		return this.data;
	}
	private void create_tabla(String name,Object[][] results) {
		
		CustomTable table = new CustomTable();

		
		Column col = new Column();
		
		
		col = new Column();
		col.setName("Fecha");
		col.setWidth(96);
		col.setEditable(false);
		col.setClass(Date.class);
		col.setCellRenderer(new DateRenderer());
		table.addColumn(col);

		col = new Column();
		col.setName("asiento");
		col.setWidth(60);
		col.setEditable(false);

		table.addColumn(col);

		col = new Column();
		col.setName("tc");
		col.setWidth(36);
		col.setEditable(false);
		table.addColumn(col);

		col = new Column();
		col.setName("idcomprobante");
		col.setWidth(90);
		col.setEditable(false);
		table.addColumn(col);

		col = new Column();
		col.setName("detalle");
		col.setWidth(270);
		col.setEditable(false);
		table.addColumn(col);
		
		col = new Column();
		col.setName("cuenta");
		col.setWidth(120);
		col.setEditable(false);
		table.addColumn(col);

		col = new Column();
		col.setName("debe");
		col.setWidth(80);
		col.setEditable(false);
		col.setClass(Double.class);
		col.setCellRenderer(new MoneyRenderer());
		col.setAligment(JTextField.RIGHT);
		col.setClass(Double.class);
		table.addColumn(col);

		col = new Column();
		col.setName("haber");
		col.setWidth(80);
		col.setAligment(JTextField.RIGHT);
		col.setEditable(false);
		col.setClass(Double.class);
		col.setCellRenderer(new MoneyRenderer());
		table.addColumn(col);

		col = new Column();
		col.setName("saldo");
		col.setWidth(80);
		col.setAligment(JTextField.RIGHT);
		col.setEditable(false);
		col.setClass(Double.class);
		col.setCellRenderer(new MoneyRenderer());
		table.addColumn(col);
		

		table.setData(results);

		Font fuente = new Font("Arial", Font.PLAIN, 9);
		table.setHeaderFont(fuente);

		table.build();

		table.fillData();
		JTable _table = table.getTable();
		_table.setName(name);
		
		
		aplicacion.gestion.caja.manejo.constructor._Constructor C=
			(aplicacion.gestion.caja.manejo.constructor._Constructor) this.getConstructor();
		_table.addMouseListener(C.getMouseListener());
		if (name==_Interface._table_movimientos){
			frame.setJTable(table.getTable());	
			showCell(frame.getJTable());
		}
		if (name==_Interface._table_movimientos_us){
			frame.setJTable4(table.getTable());
			showCell(frame.getJTable4());
		}
		if (name==_Interface._table_movimientos_cht){
			frame.setJTable3(table.getTable());
			showCell(frame.getJTable3());
		}
	}
	
	
private void create_tablaAlfa(String name,Object[][] results) {
		
		CustomTable table = new CustomTable();

		
		Column col = new Column();
		
		
		col = new Column();
		col.setName("Fecha");
		col.setWidth(96);
		col.setEditable(false);
		col.setClass(Date.class);
		col.setCellRenderer(new DateRenderer());
		table.addColumn(col);

		col = new Column();
		col.setName("asiento");
		col.setWidth(60);
		col.setEditable(false);

		table.addColumn(col);

		col = new Column();
		col.setName("tc");
		col.setWidth(36);
		col.setEditable(false);
		table.addColumn(col);

		col = new Column();
		col.setName("idcomprobante");
		col.setWidth(90);
		col.setEditable(false);
		table.addColumn(col);

		col = new Column();
		col.setName("detalle");
		col.setWidth(270);
		col.setEditable(false);
		table.addColumn(col);
		
		col = new Column();
		col.setName("cuenta");
		col.setWidth(120);
		col.setEditable(false);
		table.addColumn(col);

		col = new Column();
		col.setName("debe");
		col.setWidth(80);
		col.setEditable(false);
		col.setClass(Double.class);
		col.setCellRenderer(new MoneyRenderer());
		col.setAligment(JTextField.RIGHT);
		col.setClass(Double.class);
		table.addColumn(col);

		col = new Column();
		col.setName("haber");
		col.setWidth(80);
		col.setAligment(JTextField.RIGHT);
		col.setEditable(false);
		col.setClass(Double.class);
		col.setCellRenderer(new MoneyRenderer());
		table.addColumn(col);

		col = new Column();
		col.setName("saldo");
		col.setWidth(80);
		col.setAligment(JTextField.RIGHT);
		col.setEditable(false);
		col.setClass(Double.class);
		col.setCellRenderer(new MoneyRenderer());
		table.addColumn(col);
		

		table.setData(results);

		Font fuente = new Font("Arial", Font.PLAIN, 9);
		table.setHeaderFont(fuente);

		table.build();

		table.fillData();
		JTable _table = table.getTable();
		_table.setName(name);
		
		
		aplicacion.gestion.caja.manejo.constructor._Constructor C=
			(aplicacion.gestion.caja.manejo.constructor._Constructor) this.getConstructor();
		_table.addMouseListener(C.getMouseListener());
		frame.setJTable_efectivo_alfa(table.getTable());
		showCell(frame.getJTable_efectivo_alfa());
		/*
		if (name==_Interface._table_movimientos){
			frame.setJTable(table.getTable());	
			showCell(frame.getJTable());
		}
		if (name==_Interface._table_movimientos_us){
			frame.setJTable4(table.getTable());
			showCell(frame.getJTable4());
		}
		if (name==_Interface._table_movimientos_cht){
			frame.setJTable3(table.getTable());
			showCell(frame.getJTable3());
		}*/
	}
	
	private void create_tabla_arqueo(Object[][] results) {
		Object[][] empty_medios = new Object[][] { { "", "", "", "", "", "",
				"", "", "" } };
		// public void create_table_medios(Object[][] results) {
		CustomTable table = new CustomTable();

		Column col = new Column();
		col = new Column();
		col.setName("Moneda");
		col.setWidth(96);
		col.setClass(Double.class);
		col.setCellRenderer(new MoneyRenderer());
		col.setEditable(false);
		
		table.addColumn(col);

		col = new Column();
		col.setName("Cantidad");
		col.setWidth(60);
		col.setEditable(true);
		col.setClass(Double.class);
		col.setCellRenderer(new MoneyRenderer());
		CellEditor pce = new CellEditor();
		aplicacion.gestion.caja.manejo.constructor._Constructor C=
			(aplicacion.gestion.caja.manejo.constructor._Constructor) this.getConstructor();
		pce.addKeyListener(C.getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_arqueo_cantidad);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());
		table.addColumn(col);

		col = new Column();
		col.setName("Total");
		col.setWidth(100);
		col.setEditable(false);
		col.setClass(Double.class);
		col.setCellRenderer(new MoneyRenderer());
		table.addColumn(col);

		
		table.setData(results);

		Font fuente = new Font("Arial", Font.PLAIN, 9);
		table.setHeaderFont(fuente);
		table.setSortable(false);
		table.build();
		
		table.fillData();
		JTable _table = table.getTable();
		_table.setName(_Interface._table_arqueo);
		
		//_table.addMouseListener(C.getMouseListener());
		frame.setJTable1(table.getTable());
	}
	
	public void exit_command(){
		this.getConstructor().dispose(false);
	}
	
	
	public void showCell(JTable table) {
		int row=table.getRowCount()-1;
		int column=0;
	    Rectangle rect = table.getCellRect(row, column, true);
	    table.scrollRectToVisible(rect);
	    table.clearSelection();
	    table.setRowSelectionInterval(row, row);
	    //(DefaultTableModel)(frame.getJTable().getModel())
	    }

	public void cargar_arqueo(String cuenta){
		
		String idcaja = frame.get_lst_cajas().getSelectedItem().toString();
		Object[][] results=this.data.get_monedas(idcaja,cuenta);
		boolean ok=false;
		if (results!=null){
			if (results.length>0){
				ok=true;
				for (int i = 0; i < results.length; i++) {
					double moneda=0.0;
					double cantidad=0.0;
					try {
					moneda=new Double(results[i][0].toString());
					cantidad=new Double(results[i][1].toString());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					results[i][0]=moneda;
					results[i][1]=cantidad;
					results[i][2]=moneda*cantidad;
				}
				this.create_tabla_arqueo(results);
				this.recalculate_coins();		
			}
		}
		if (!ok){
			ok=this.confirmar("No se encontraron los contadores del arqueo. Confirme para crearlos:", 4);
			if (ok){
				String q="";
				data.clearBatch();
				q=data.getInsertMoneda(idcaja, cuenta, "0.05");
				data.addBatch(q);
				q=data.getInsertMoneda(idcaja, cuenta, "0.10");
				data.addBatch(q);
				q=data.getInsertMoneda(idcaja, cuenta, "0.25");
				data.addBatch(q);
				q=data.getInsertMoneda(idcaja, cuenta, "0.50");
				data.addBatch(q);
				q=data.getInsertMoneda(idcaja, cuenta, "1");
				data.addBatch(q);
				q=data.getInsertMoneda(idcaja, cuenta, "2");
				data.addBatch(q);
				q=data.getInsertMoneda(idcaja, cuenta, "5");
				data.addBatch(q);
				q=data.getInsertMoneda(idcaja, cuenta, "10");
				data.addBatch(q);
				q=data.getInsertMoneda(idcaja, cuenta, "20");
				data.addBatch(q);
				q=data.getInsertMoneda(idcaja, cuenta, "50");
				data.addBatch(q);
				q=data.getInsertMoneda(idcaja, cuenta, "100");
				data.addBatch(q);
				data.executeBatch();	
			}
			
		}
	}
	
	public String getCuenta(){
		String idcuenta="";
		if (frame.getJTabbedPane1().getSelectedIndex()==0){
			idcuenta="111010001";
		}
		if (frame.getJTabbedPane1().getSelectedIndex()==1){
			idcuenta="111010099";
		}
		return idcuenta;
	}
	
	
	public String getCuenta(JComboBox cb){
		String idcuenta="";
		if (cb.getSelectedIndex()==0){
			idcuenta="111010001";
		}
		if (cb.getSelectedIndex()==1){
			idcuenta="111010099";
		}
		return idcuenta;
	}
	
	

	private void recalculate(){
		String total_alfa=frame.get_txt_saldo_alfa().getText();
		String total_beta=frame.get_txt_saldo().getText();
		total_alfa=total_alfa.replaceAll(",", "");
		total_beta=total_beta.replaceAll(",", "");
		if (total_alfa.compareTo("")==0){
			total_alfa="0.0";
		}
		if (total_beta.compareTo("")==0){
			total_beta="0.0";
		}
		double _alfa=0.0;
		double _beta=0.0;
		try {
			_alfa=new Double(total_alfa);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			_beta=new Double(total_beta);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double total=0.0;
		if (frame.get_chk_beta().isSelected() & frame.get_chk_caja_alfa().isSelected()){
			total=_alfa+_beta;	
		}else{
			if (frame.get_chk_beta().isSelected()){
				total=_beta;
			}
			if (frame.get_chk_caja_alfa().isSelected()){
				total=_alfa;
			}		
		}
		
		frame.get_txt_saldo_total().setText(new Convertidor().getMoney(total, 2));
	}
	
	private void recalculate(JTable table,JTextField tx){
		double debe = 0.0;
		double haber = 0.0;
		for (int i=0;i<table.getRowCount();i++){
			double _debe = 0.0;
			double _haber = 0.0;
			try {
				_debe=(Double)table.getValueAt(i, 6);
				_haber=(Double)table.getValueAt(i, 7);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			debe+=_debe;
			haber+=_haber;
		}
		
		tx.setText(new Convertidor().getMoney(debe-haber,2));
		this.recalculate();
	}
	/**
	 * procedimiento para calcular la diferencia entre el arqueo de billetes vs el saldo de la caja
	 */
	
	public void calculate_difence(JCheckBox chk){
		
		if (chk.getName()==_Interface._chk_alfa){
			frame.get_chk_caja_alfa().setSelected(chk.isSelected());
		}
		if (chk.getName()==_Interface._chk_beta){
			frame.get_chk_beta().setSelected(chk.isSelected());
		}
		this.calculate_difence();
		this.recalculate();
	}
	
	
	
	public void calculate_difence(){
		double arqueo=0.0;
		double saldo=0.0;
		arqueo=new Double(frame.get_txt_arqueo().getText().toString().replaceAll(",", ""));
		if (frame.get_chk_beta().isSelected() & frame.get_chk_caja_alfa().isSelected()) {
			try {
				saldo=new Double(frame.get_txt_saldo_total().getText().toString().replaceAll(",", ""));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}else{
			if (frame.get_chk_beta().isSelected()){
				try {
					saldo=new Double(frame.get_txt_saldo().getText().toString().replaceAll(",", ""));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}	
			}
			if (frame.get_chk_caja_alfa().isSelected()){
				try {
					saldo=new Double(frame.get_txt_saldo_alfa().getText().toString().replaceAll(",", ""));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}	
			}
		}
		
		double dif=arqueo-saldo;
		frame.get_txt_diferencia().setBackground(Color.black);
		frame.get_btn_ajustar().setEnabled(true);
		if (dif>0){
			//frame.get_txt_diferencia().setForeground(Color.green);
		}else {
			if (dif<0){
				
				//frame.get_txt_diferencia().setForeground(Color.red);	
			}else{
				frame.get_btn_ajustar().setEnabled(false);
				//frame.get_txt_diferencia().setForeground(Color.white);
			}
		}
		frame.get_txt_diferencia().setText(new Convertidor().getMoney(dif,2));
		frame.get_txt_suma_saldo().setText(new Convertidor().getMoney(arqueo,2));
	}

	public void cargar_cajas() {

		frame.get_lst_cajas().removeAllItems();
		frame.get_lst_cajas().removeItemListener(this.getConstructor().getItemListener());
		_Data data = (_Data) _data;
		String iduser=this.getConstructor().getIduser();
		Object[][] results = data.get_cajas(iduser);
		for (int i = 0; i < results.length; i++) {
			try {
				frame.get_lst_cajas().addItem(results[i][0]);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		frame.get_lst_cajas().addItemListener(this.getConstructor().getItemListener());
	}

	public void setInitialDate() {
		String aux = data.getSystemDate();
		frame.get_txt_hasta().setText(aux);
		String s1=frame.get_txt_hasta().getText();
		String s2=this.getDaysRoll(s1, -60);
		frame.get_txt_desde().setText(s2);
		
	}
	
	
	public String getDaysRoll(String fecha_actual,int days){
		Date today=new Convertidor().getDate(fecha_actual);
		java.util.GregorianCalendar _today=new java.util.GregorianCalendar();
		_today.setTime(today);
		_today.add(Calendar.DAY_OF_YEAR, days);
		Date before=_today.getTime();
		String s2=new Convertidor().getDateWithFormat("dd-MM-yyyy", before);
		return s2;
	}
	
	 private aplicacion.herramientas.java.buscadores.Fecha bFecha=null;
	 public void BuscarFecha(JTextField tx){
	 	if (bFecha==null){
	 		bFecha=new aplicacion.herramientas.java.buscadores.Fecha(this.getConstructor());
	 	}
	 	
	 	bFecha.Buscar(tx);
	 }

	 
	 public void buscar_fecha_desde(){
		 this.BuscarFecha(frame.get_txt_desde());
	 }
	
	 public void buscar_fecha_hasta(){
		 this.BuscarFecha(frame.get_txt_hasta());
	 }
	 
	 public void evaluate_fecha_desde(JTextField tx){
		 
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
				frame.get_txt_hasta().requestFocusInWindow();

			} else {
				txt.requestFocusInWindow();
			}
		}
	 public boolean fechas_ok(){
		 boolean ok=false;
			String s = frame.get_txt_hasta().getText();
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
				int n=new Convertidor().getMayorDate(s1, frame.get_txt_desde().getText());
				ok=(n>0);
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
					this.goCargar();
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

	public void cheques_en_cartera() {
		aplicacion.gestion.cartera.constructor._Constructor C = new aplicacion.gestion.cartera.constructor._Constructor();;
		C.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
		C.setParameter(_parametros.connector, data.getConnectionHandler());
		C.setParameter(_parametros.iduser, this.getConstructor().getIduser());
		String idcajas=frame.get_lst_cajas().getSelectedItem().toString();
		C.setParameter(aplicacion.gestion.cartera.interfaces._Parametros.caja, idcajas);
		C.build(this.getConstructor());
		C.init();
	}

	
	public void transferencia() {

		aplicacion.gestion.transferencia.constructor._Constructor C = new aplicacion.gestion.transferencia.constructor._Constructor();


		C.setParameter(_parametros.connector, data.getConnectionHandler());
		C.setParameter(_parametros.iduser, this.getConstructor().getIduser());
		C.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
		String idcajas=frame.get_lst_cajas().getSelectedItem().toString();
		C.setParameter(aplicacion.gestion.transferencia.interfaces._Parametros.caja, idcajas);
		C.build(this.getConstructor());
		C.init();
		this.getConstructor().addChild(C);
	}
	
	public void cargar_transferencia(String idcomprobante) {
		aplicacion.gestion.transferencia.constructor._Constructor C = new aplicacion.gestion.transferencia.constructor._Constructor();
		C.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
		C.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler());
		C.setParameter(_parametros.iduser, this.getConstructor().getIduser());
		C.setParameter(aplicacion.gestion.transferencia.interfaces._Parametros.idcomprobante, idcomprobante);
		C.build(this.getConstructor());
		C.init();
		this.getConstructor().addChild(C);
	}
	
	public void cargar_egreso(String idcomprobante) {
		aplicacion.gestion.egreso.constructor._Constructor C = new aplicacion.gestion.egreso.constructor._Constructor();
		C.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
		C.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler());
		C.setParameter(_parametros.iduser, this.getConstructor().getIduser());
		C.setParameter(aplicacion.gestion.egreso.interfaces._Parametros.idcomprobante, idcomprobante);
		C.build(this.getConstructor());
		C.init();
		this.getConstructor().addChild(C);
	}
	public void cargar_pago(String idcomprobante) {
		aplicacion.proveedor.pago.constructor._Constructor C = new aplicacion.proveedor.pago.constructor._Constructor();
		C.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
		C.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler());
		C.setParameter(_parametros.iduser, this.getConstructor().getIduser());
		C.setParameter(aplicacion.proveedor.pago.interfaces._Parametros.idpago, idcomprobante);
		C.build(this.getConstructor());
		C.init();
		this.getConstructor().addChild(C);
	}
	
	public void cargar_cobranza(String idcomprobante) {
		aplicacion.cliente.cobranza.constructor._Constructor C = new aplicacion.cliente.cobranza.constructor._Constructor();
		C.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
		C.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler());
		C.setParameter(_parametros.iduser, this.getConstructor().getIduser());
		C.setParameter(aplicacion.cliente.cobranza.interfaces._Parametros.idcobranza, idcomprobante);
		C.build(this.getConstructor());
		C.init();
		this.getConstructor().addChild(C);
	}
	
	public void efectuar_cobranza() {
		aplicacion.cliente.cobranza.constructor._Constructor C = new aplicacion.cliente.cobranza.constructor._Constructor();
		C.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
		C.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler());
		C.setParameter(_parametros.iduser, this.getConstructor().getIduser());
		C.build(this.getConstructor());
		C.init();
		this.getConstructor().addChild(C);
	}
	
	public void efectuar_pago() {
		aplicacion.proveedor.pago.constructor._Constructor C = new aplicacion.proveedor.pago.constructor._Constructor();
		C.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
		C.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler());
		C.setParameter(_parametros.iduser, this.getConstructor().getIduser());
		C.build(this.getConstructor());
		C.init();
		this.getConstructor().addChild(C);
	}
	public void cargar_canje(String idcomprobante) {
		aplicacion.gestion.canje.constructor._Constructor C = new aplicacion.gestion.canje.constructor._Constructor();
		C.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
		C.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler());
		C.setParameter(_parametros.iduser, this.getConstructor().getIduser());

		C.setParameter(aplicacion.gestion.canje.interfaces._Parametros.idcomprobante, idcomprobante);
		C.build(this.getConstructor());
		C.init();
		this.getConstructor().addChild(C);
	}
	
	public void cargar_fvn(String tc,String idcomprobante) {
		
		fvn f=new fvn(this.getConstructor());
		f.setTc(tc);
		f.setIdcomprobante(idcomprobante);
		f.setConstructor(this.getConstructor());
		f.Mostrar();
		
	}
	
	public void cargar_ingreso(String idcomprobante) {
		aplicacion.gestion.ingreso.constructor._Constructor C = new aplicacion.gestion.ingreso.constructor._Constructor();
		C.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
		C.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler());
		C.setParameter(_parametros.iduser, this.getConstructor().getIduser());
		C.setParameter(aplicacion.gestion.ingreso.interfaces._Parametros.idcomprobante, idcomprobante);
		C.build(this.getConstructor());
		C.init();
		this.getConstructor().addChild(C);
	}
	public void eval_row(JTable table,int row,int col){
		String tc="";
		String idcomprobante="";
		tc=table.getValueAt(row, 2).toString();
		idcomprobante=table.getValueAt(row, 3).toString();
		if (col==5){
			String cuenta=table.getValueAt(row, 5).toString();
			this.ver_movimientos(cuenta);
		}else{
			if (tc.compareTo("TR")==0){
				this.cargar_transferencia(idcomprobante);	
			}
			if (tc.compareTo("IGR")==0){
				this.cargar_ingreso(idcomprobante);	
			}
			if (tc.compareTo("EGR")==0){
				this.cargar_egreso(idcomprobante);	
			}
			if (tc.compareTo("CJE")==0){
				this.cargar_canje(idcomprobante);	
			}
			if (tc.compareTo("CBCT")==0){
				this.cargar_cobranza(idcomprobante);	
			}
			if (tc.compareTo("PG")==0){
				this.cargar_pago(idcomprobante);	
			}
			if (tc.compareTo("FVN")==0){
				this.cargar_fvn("FVN",idcomprobante);	
			}
			if (tc.compareTo("NCN")==0){
				this.cargar_fvn("NCN",idcomprobante);	
			}
		}
		
		
		
	}
	

	public void egreso() {
		aplicacion.gestion.egreso.constructor._Constructor C = new aplicacion.gestion.egreso.constructor._Constructor();

		C.setParameter(_parametros.iduser, this.getConstructor().getIduser());
		C.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
		C.setParameter(_parametros.connector, data.getConnectionHandler());
		String idcajas=frame.get_lst_cajas().getSelectedItem().toString();
		C.setParameter(aplicacion.gestion.egreso.interfaces._Parametros.caja, idcajas);
		
		C.build(this.getConstructor());
		C.init();
		this.getConstructor().addChild(C);
	}

	
	public void venta() {
		aplicacion.ventas.facturador.constructor._Constructor C = new aplicacion.ventas.facturador.constructor._Constructor();
		C.setParameter(_parametros.iduser, this.getConstructor().getIduser());
		C.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
		C.setParameter(_parametros.connector, data.getConnectionHandler());
		C.build(this.getConstructor());
		C.init();
		this.getConstructor().addChild(C);
	}
	public void rechazado() {
		aplicacion.gestion.rechazados.constructor._Constructor C = new aplicacion.gestion.rechazados.constructor._Constructor();

		C.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
		C.setParameter(_parametros.connector, data.getConnectionHandler());
		C.setParameter(_parametros.iduser, this.getConstructor().getIduser());
		String idcajas=frame.get_lst_cajas().getSelectedItem().toString();
		C.setParameter(aplicacion.gestion.rechazados.interfaces._Parametros.caja, idcajas);
		// C.setParameter(_parametros.iduser, iduser);

		C.build(this.getConstructor());
		C.init();
		this.getConstructor().addChild(C);
	}
	
	public void ingreso() {
		aplicacion.gestion.ingreso.constructor._Constructor C = new aplicacion.gestion.ingreso.constructor._Constructor();

		C.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
		C.setParameter(_parametros.connector, data.getConnectionHandler());
		C.setParameter(_parametros.iduser, this.getConstructor().getIduser());
		String idcajas=frame.get_lst_cajas().getSelectedItem().toString();
		C.setParameter(aplicacion.gestion.ingreso.interfaces._Parametros.caja, idcajas);
		

		C.build(this.getConstructor());
		C.init();
		this.getConstructor().addChild(C);
	}
	
	public void canje() {
		aplicacion.gestion.canje.constructor._Constructor C = new aplicacion.gestion.canje.constructor._Constructor();
		C.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
		C.setParameter(_parametros.connector, data.getConnectionHandler());
		C.setParameter(_parametros.iduser, this.getConstructor().getIduser());
		String idcajas=frame.get_lst_cajas().getSelectedItem().toString();
		C.setParameter(aplicacion.gestion.canje.interfaces._Parametros.caja, idcajas);
		// C.setParameter(_parametros.iduser, iduser);

		C.build(this.getConstructor());
		C.init();
		this.getConstructor().addChild(C);
	}
	
	public void ajuste_ingreso(double importe) {
		aplicacion.gestion.ingreso.constructor._Constructor C = new aplicacion.gestion.ingreso.constructor._Constructor();

		C.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
		C.setParameter(_parametros.connector, data.getConnectionHandler());
		C.setParameter(_parametros.iduser, this.getConstructor().getIduser());
		String idcajas=frame.get_lst_cajas().getSelectedItem().toString();
		C.setParameter(aplicacion.gestion.ingreso.interfaces._Parametros.caja, idcajas);
		// C.setParameter(_parametros.iduser, iduser);

		C.build(this.getConstructor());
		C.init();
		this.getConstructor().addChild(C);
		aplicacion.gestion.ingreso.gui._Frame frame=(aplicacion.gestion.ingreso.gui._Frame)C.getFrame();
		aplicacion.gestion.ingreso.logic._Logic logic=(aplicacion.gestion.ingreso.logic._Logic)C.getLogic();
		frame.get_txt_concepto().setText("Ajuste Diferencia de Caja");
		frame.get_txt_idproveedor().setText("512");
		logic.cargar_cajas();
		logic._evaluar_codigo_cuenta(frame.get_txt_idproveedor());
		logic.evaluate_caja(frame.get_list_cajas());
		logic.create_tabla();
		logic.AjusteEfectivo(importe);
		
		logic._medios_recalcular();
		
	}
	
	public void ajuste_egreso(double importe) {
		aplicacion.gestion.egreso.constructor._Constructor C = new aplicacion.gestion.egreso.constructor._Constructor();

		C.setParameter(_parametros.iduser, this.getConstructor().getIduser());
		C.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
		C.setParameter(_parametros.connector, data.getConnectionHandler());
		
		String idcajas=frame.get_lst_cajas().getSelectedItem().toString();
		C.setParameter(aplicacion.gestion.ingreso.interfaces._Parametros.caja, idcajas);
		// C.setParameter(_parametros.iduser, iduser);

		C.build(this.getConstructor());
		C.init();
		this.getConstructor().addChild(C);
		aplicacion.gestion.egreso.gui._Frame frame=(aplicacion.gestion.egreso.gui._Frame)C.getFrame();
		aplicacion.gestion.egreso.logic._Logic logic=(aplicacion.gestion.egreso.logic._Logic)C.getLogic();
		frame.get_txt_concepto().setText("Ajuste Diferencia de Caja");
		frame.get_txt_idcuenta().setText("512");
		logic.cargar_cajas();
		logic._evaluar_codigo_cuenta(frame.get_txt_idcuenta());
		logic.evaluate_caja(frame.get_list_cajas());
		//logic.evaluar_concepto(frame.get_txt_concepto());
		logic.create_tabla();
		logic.AjusteEfectivo(importe);
		logic._medios_recalcular();
		
	}
	
	public void evaluate_moneda(JComboBox cb) {
		String desc = cb.getSelectedItem().toString();
		

	}
	
	public void evaluate_caja(JComboBox cb) {
		String desc = cb.getSelectedItem().toString();
		desc = data.getDetalleCaja(desc);
		if (desc.compareTo("") != 0) {
			frame.get_txt_caja_detalle().setText(desc);
			this.goCargar();
		}

	}
	
	public void evaluate_cantidad(JTextField tx,int row,int col){
		String val=tx.getText();
		Double cantidad=0.0;
		double moneda=0.0;
		boolean error=false;
		try {
			cantidad=new Double(val.replaceAll(",", ""));
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error=true;
		}
		cantidad=new Double(cantidad.intValue());
		if (!error){
			String idcaja=frame.get_lst_cajas().getSelectedItem().toString();
		
		tx.setText(""+cantidad);
		moneda=(Double) frame.getJTable1().getValueAt(row, 0);
		this.data.updateMoneda(idcaja, moneda, cantidad,this.getCuenta());
		frame.getJTable1().setValueAt(moneda*cantidad, row, 2);
		frame.getJTable1().requestFocusInWindow();
		if (row<frame.getJTable1().getRowCount()-1)row++;
		frame.getJTable1().changeSelection(row, col, false,false);
		frame.getJTable1().editCellAt(row, col);
		frame.getJTable1().transferFocus();
		}else{
			error("Error en Cantidad. Verifique");
		}
		this.recalculate_coins();
	}
	
	public void editCell(int row,int col){
		try {
			frame.getJTable1().getCellEditor().stopCellEditing();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame.getJTable1().changeSelection(row,col,false,false);
		frame.getJTable1().editCellAt(row, col);
		frame.getJTable1().transferFocus();
	}
	public void recalculate_coins(){
		double total=0.0;
		for (int i=0;i<frame.getJTable1().getRowCount();i++){
			double qty=0.0;
			double coin=0.0;
			qty=new Double(frame.getJTable1().getValueAt(i, 1).toString());
			coin=new Double(frame.getJTable1().getValueAt(i, 0).toString());
			total+=qty*coin;
		}
		frame.get_txt_arqueo().setText(new Convertidor().getMoney(total,2));
		this.calculate_difence();
	}
	
	
	public void ver_movimientos(String cuenta){
		aplicacion.gestion.cuenta.constructor._Constructor 
		C=new aplicacion.gestion.cuenta.constructor._Constructor();
		C.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
		C.setParameter(_parametros.iduser, this.getConstructor().getIduser());
		C.setParameter(_parametros.connector, data.getConnectionHandler());
		String idcajas=frame.get_lst_cajas().getSelectedItem().toString();
		C.setParameter(aplicacion.gestion.cuenta.interfaces._Parametros.caja, idcajas);
		if (cuenta.compareTo("")!=0){
			C.setParameter(aplicacion.gestion.cuenta.interfaces._Parametros.cuenta, cuenta);	
		}
		
		C.build(this.getConstructor());
		C.init();
		this.getConstructor().addChild(C);
	}
	
	public void reporte(){
		aplicacion.gestion.caja.reporte.constructor._Constructor 
		C=new aplicacion.gestion.caja.reporte.constructor._Constructor();
		C.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
		C.setParameter(_parametros.iduser, this.getConstructor().getIduser());
		C.setParameter(_parametros.connector, data.getConnectionHandler());
		String idcajas=frame.get_lst_cajas().getSelectedItem().toString();
		C.setParameter(aplicacion.gestion.caja.reporte.interfaces._Parametros.caja, idcajas);
		C.build(this.getConstructor());
		C.init();
		this.getConstructor().addChild(C);
	}
	public void ajustar(){
		double dif=new Double(frame.get_txt_diferencia().getText().toString().replaceAll(",", ""));
		if (dif>0){
			this.ajuste_ingreso(dif);
		}else{
			this.ajuste_egreso(-dif);
		}
	}
	
	public void createTimer(){
		crono=new Crono();
		crono.start();
		lenght=0;
		current=0;
		errors=0;
		done = false;
		canceled=false;
		Timer=new Timer(1000, new ActionListener() {
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
		frame.get_btn_cargar().setEnabled(true);
	
	}
}