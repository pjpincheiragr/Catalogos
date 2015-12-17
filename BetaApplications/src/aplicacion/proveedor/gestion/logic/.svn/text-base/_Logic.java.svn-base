package aplicacion.proveedor.gestion.logic;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.HashMap;

import javax.swing.*;

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

import aplicacion.proveedor.gestion.gui.*;
import aplicacion.proveedor.gestion.interfaces.*;


public class _Logic extends Logic {
	private aplicacion.herramientas.java.visualselector.constructor._Constructor vSelector=null;
	private aplicacion.herramientas.java.buscadores.Articulo bArticulo=null;
	private aplicacion.herramientas.java.visualizadores.Articulo vArticulo=null;
	private aplicacion.herramientas.java.visualizadores.Proveedor vProveedor=null;
    private aplicacion.herramientas.java.buscadores.Proveedor bProveedor=null;
    private aplicacion.herramientas.java.buscadores.Linea bLinea;		
    
    
	private Timer Timer;  //  @jve:decl-index=0:
	private Crono crono;
	private String estado="";
	private int current;
	private int lenght;
	private boolean done,canceled;
	private int errors=0;
	
	private Object[][] memoria=null;
	private _Data data;
	private _Frame frame;
	
	public void setFrame(JFrame frame){
		this.frame=(_Frame) frame;
		super.setFrame(frame);
	}
	
	public void setData(Data _data){
		this.data=(_Data)_data;
		super.setData(_data);
	}
	
	public void exit_command(){
		this.getConstructor().dispose(false);
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
		if (Timer!=null) {
			Timer.start();
		}
		worker.start();
		
	}	
	
	
	class _taskCargar {
		_taskCargar() {
			_taskworkCargar();
		}
	}
	
	
	public void _taskworkCargar(){
		estado="Cargando Datos";
		frame.getJProgressBar().setIndeterminate(true);
		Object[][] results =null;
		boolean saldo_distinto_de_cero=frame.get_chk_saldos_distintos_de_cero().isSelected();
			results=data.getSaldos(saldo_distinto_de_cero);
		
			
		
		final Object[][] _results=results;
		Runnable _execute = new Runnable() {
	        public void run() {
	        	_taskworkCargarSwing(_results);
	        }
		};
		this.invokeLater(_execute);
		
		done=true;
	}


	
	private CustomTable crearTablaDeItems(boolean editable) {
		CustomTable Table = new CustomTable();
		Column col = new Column();
	
			
		col = new Column();
		col.setName("idproveedor");
		col.setWidth(120);
		col.setEditable(false);
		Table.addColumn(col);
		
		col = new Column();
		col.setName("Nombre");
		col.setWidth(280);
		Table.addColumn(col);
		
		col = new Column();
		col.setName("Debe");
		col.setWidth(160);
		col.setEditable(false);
		col.setClass(Double.class);
		//col.setCellRenderer(new MoneyRenderer());
		col.setAligment(JTextField.RIGHT);
		Table.addColumn(col);
		
		col = new Column();
		col.setName("Haber");
		col.setWidth(160);
		col.setEditable(false);
		col.setClass(Double.class);
		//col.setCellRenderer(new MoneyRenderer());
		col.setAligment(JTextField.RIGHT);
		Table.addColumn(col);
		
		col = new Column();
		col.setName("Saldo");
		col.setWidth(160);
		col.setEditable(false);
		col.setClass(Double.class);
		//col.setCellRenderer(new MoneyRenderer());
		col.setAligment(JTextField.RIGHT);
		Table.addColumn(col);
		return Table;
	}
	
	public Object[][] processData(Object[][] results){
		
		Convertidor Cv=new Convertidor();
		for (int i=0;i<results.length;i++){
			for (int j=2;j<5;j++){
				String value=(String) results[i][j];
				Double tmp=0.0;
				try {
					tmp=new Double(value);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				tmp=Cv.roundDouble(tmp, 2);
				results[i][j]=tmp;	
			}
				
				
				
		}
		return results;
	}
	
	public void create_table(Object[][] results){
		if (results!=null){
			
			CustomTable Table = this.crearTablaDeItems(false);
			results=this.processData(results);
			Table.setData(results);
			Table.build();
			Table.fillData();
			JTable _table=Table.getTable();
			_table.setName(_Interface._table);
			_table.addMouseListener(this.getConstructor().getMouseListener());
			_table.addKeyListener(this.getConstructor().getKeyListener());
			_table.getTableHeader().addMouseListener(this.getConstructor().getMouseListener());
			frame.setJTable(_table);	
			this.recalculateSaldos();
		}else {
			frame.setJTable(null);
		}
	}
	public void _taskworkCargarSwing(Object[][] results) {
		create_table(results);
       	
	}
	
	
	
	public void clean(){
		frame.setJTable(null);
		
	}
	
	
	
	public void focus(){
		//frame.get_txt_idproveedor().requestFocusInWindow();
	}
	
	public void cancelar_tarea(){
		if (preguntar("confirmar","Cancela Tarea?")){
			this.canceled=true;	
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
	public void recalculateSaldos(){
		double saldo_acreedor=0.0;
		double saldo_deudor=0.0;
		double saldo_final=0.0;
		for (int i=0;i<frame.getJTable().getRowCount();i++){
			double saldo=0;
			saldo=(Double)frame.getJTable().getValueAt(i, 4);
			if (saldo>0){
				saldo_deudor+=saldo;
			}
			if (saldo<0){
				saldo_acreedor+=-saldo;
			}
			saldo_final+=-saldo;
		}
		frame.get_txt_saldo().setText(new Convertidor().getMoney(saldo_final, 2));
		if (saldo_deudor>0){
			frame.get_txt_saldo_deudor().setForeground(Color.red);	
		}
		if (saldo_acreedor>0){
			frame.get_txt_saldo_acreedor().setForeground(Color.green);	
		}
		frame.get_txt_saldo_deudor().setText(new Convertidor().getMoney(saldo_deudor, 2));
		frame.get_txt_saldo_acreedor().setText(new Convertidor().getMoney(saldo_acreedor, 2));
	}
	
	public void cargar(){
		
		this.goCargar();
	}
	
	public void hacerPago(){
		int row=frame.getJTable().getSelectedRow();
		String idproveedor=frame.getJTable().getValueAt(row, 0).toString();
		if (idproveedor.compareTo("")!=0){
				aplicacion.proveedor.pago.constructor._Constructor 
				CC=new aplicacion.proveedor.pago.constructor._Constructor();
				CC.setParameter(_parametros.connector, this._data.getConnectionHandler());
				CC.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
				CC.setParameter(aplicacion.proveedor.pago.interfaces._Parametros.idproveedor, idproveedor);
				CC.build(this.getConstructor());
				CC.init();	
			
				
		}
	}
	public void verMaestro(int row){
		String idproveedor=frame.getJTable().getValueAt(row, 0).toString();
		if (idproveedor.compareTo("")!=0){
				aplicacion.proveedor.archivo.constructor._Constructor 
				CC=new aplicacion.proveedor.archivo.constructor._Constructor();
				CC.setParameter(_parametros.connector, this._data.getConnectionHandler());
				CC.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
				CC.setParameter(aplicacion.proveedor.pago.interfaces._Parametros.idproveedor, idproveedor);
				CC.build(this.getConstructor());
				CC.init();	
			
				
		}
	}
	public void verMaestro(){
		int row=frame.getJTable().getSelectedRow();
		this.verMaestro(row);
	}
	
	private aplicacion.herramientas.java.ireport.constructor._Constructor reporte=null;
	public void reporte(){
		if (reporte!=null){
			 reporte.dispose();
		}
		reporte=new aplicacion.herramientas.java.ireport.constructor._Constructor();
		HashMap param=new HashMap();
		param.put("desde","211010001");
		param.put("hasta","211019999");
		reporte.setParameter(_parametros.LookAndFeel,this.getConstructor().getLookAndFeelTheme());
		reporte.setParameter(_parametros.connector,this.getConstructor().getConnectionHandler());
		reporte.setParameter(aplicacion.herramientas.java.ireport.interfaces._parametros.parametros, param);
		reporte.setParameter(aplicacion.herramientas.java.ireport.interfaces._parametros.reporte, "reporte_alfabeta.jasper");
		reporte.build(this.getConstructor());
		reporte.init();	
	}
}
