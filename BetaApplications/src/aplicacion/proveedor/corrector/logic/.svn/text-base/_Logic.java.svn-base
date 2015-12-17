package aplicacion.proveedor.corrector.logic;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Point;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import aplicacion.modelo.interfaces.*;

import aplicacion.herramientas.java.comprobantes.fvn;
import aplicacion.herramientas.java.launcher.logic.SwingWorker;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Logic;
import aplicacion.modelo.logic.Data;
import aplicacion.herramientas.java.*;



import aplicacion.herramientas.java.sortableselector.logic.Filtro;
import aplicacion.herramientas.java.sortableselector.logic.columna;
import aplicacion.herramientas.java.table.*;
import aplicacion.herramientas.java.table.CellEditor;
import aplicacion.herramientas.java.table.DateRenderer;

import aplicacion.proveedor.corrector.gui.*;
import aplicacion.proveedor.corrector.interfaces.*;

import java.util.Date;
import java.awt.Graphics;
public class _Logic extends Logic {
	
    
    
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
	private java.util.List<String[]> cptes=null;
	
	
	public _Logic(){
		super();
		
	}
	
	
	public void setFrame(JFrame frame){
		this.frame=(_Frame) frame;
		super.setFrame(frame);
	}
	
	public void setData(Data _data){
		this.data=(_Data)_data;
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
		String codigo=frame.get_txt_idproveedor().getText();
			results=data.getSaldos(codigo);
		
			
		
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
		col.setName("fecha");
		col.setWidth(100);
		col.setClass(Date.class);
		col.setCellRenderer(new DateRenderer());
		col.setEditable(false);
		Table.addColumn(col);
		
		col = new Column();
		col.setName("TC");
		col.setWidth(80);
		col.setEditable(false);
		Table.addColumn(col);
		
		col = new Column();
		col.setName("IdCpte");
		col.setWidth(120);
		col.setEditable(false);
		Table.addColumn(col);
		
		col = new Column();
		col.setName("Debe");
		col.setWidth(120);
		col.setEditable(false);
		col.setClass(Double.class);
		Table.addColumn(col);
		
		col = new Column();
		col.setName("Haber");
		col.setWidth(120);
		col.setClass(Double.class);
		col.setEditable(false);
		Table.addColumn(col);
		
		col = new Column();
		col.setName("Anulado");
		col.setWidth(80);
		col.setEditable(false);
		Table.addColumn(col);
		
		col = new Column();
		col.setName("Cpte.Anulado");
		col.setWidth(130);
		col.setEditable(false);
		Table.addColumn(col);
		
		col = new Column();
		col.setName("Aplic.TC");
		col.setWidth(100);
		col.setEditable(false);
		Table.addColumn(col);
		
		col = new Column();
		col.setName("Aplic.IDCpte");
		col.setWidth(120);
		col.setEditable(false);
		Table.addColumn(col);
		col = new Column();
		
		col.setName("Aplic.Anulada");
		col.setWidth(100);
		col.setEditable(false);
		Table.addColumn(col);
		
		//fecha,tc,idcomprobante,debe,haber,anulado,cpte anulado,aplicacion.tc,aplicacion.cpte,aplicacion.anulada
		
		return Table;
	}
	
	public Object[][] processData(Object[][] results){
		
		Convertidor Cv=new Convertidor();
		for (int i=0;i<results.length;i++){
			int[] columns=new int[]{3,4};
			int datecolumn=0;
			for (int j=0;j<columns.length;j++){
				String value=(String) results[i][columns[j]];
				Double tmp=0.0;
				try {
					tmp=new Double(value);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				tmp=Cv.roundDouble(tmp, 2);
				results[i][columns[j]]=tmp;	
			}
			String 	value=(String) results[i][datecolumn];
			java.util.Date date=null;
			date=new Convertidor().getDate(value);
			results[i][datecolumn]=date;
				
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
			_table.setName(_Interface._table_asientos);
			_table.addMouseListener(this.getConstructor().getMouseListener());
			_table.addKeyListener(this.getConstructor().getKeyListener());
			_table.getTableHeader().addMouseListener(this.getConstructor().getMouseListener());
			frame.setJTable(_table);	
			this.recalculateSaldos();
			this.recalculate();
		}else {
			frame.setJTable(null);
		}
	}
	
	public Double getSaldo(String tc,String idcomprobante){
		double saldo=0.0;
		int i=0;
		
		while (i<frame.getJTable().getRowCount()){
			String i_tc=frame.getJTable().getValueAt(i, 1).toString();
			String i_idcomprobante=frame.getJTable().getValueAt(i, 2).toString();
			if (i_tc.compareTo(tc)==0 & i_idcomprobante.compareTo(idcomprobante)==0){
				String debe=frame.getJTable().getValueAt(i, 3).toString();
				String haber=frame.getJTable().getValueAt(i, 4).toString();
				
				double _debe=0.0;
				double _haber=0.0;
				_debe=new Double(debe);
				_haber=new Double(haber);
				saldo=_debe-_haber;
			}
			i++;
		}
		return saldo;
	}
	
	public void calculateAnticipos(){
		String cuenta=frame.get_txt_idproveedor().getText();
		Object[][] results=data.getAnticipos(cuenta);
		if (results!=null){
			if (results.length>0){
				for (int i=0;i<results.length;i++){
				String importe=(String) results[i][3];
				String value=(String) results[i][0];
				java.util.Date date=null;
				date=new Convertidor().getDate(value);
				String estado="";
				String tc=results[i][1].toString();
				String idcomprobante=results[i][2].toString();
				if (this.estaConciliado(tc, idcomprobante)){
					estado="conciliado";
				}
				this.addCpteConciliacion(frame.getJTable3(), false, date, tc, idcomprobante, importe, estado);
				}
			}
		}
	}
	public boolean estaConciliado(String tc,String idcomprobante){
		boolean conciliado=false;
		int i=0;
		while (!conciliado & i<frame.getJTable().getRowCount()){
			String i_tc=frame.getJTable().getValueAt(i, 1).toString();
			String i_idcomprobante=frame.getJTable().getValueAt(i, 2).toString();
			if (i_tc.compareTo(tc)==0 & i_idcomprobante.compareTo(idcomprobante)==0){
				String anulado=frame.getJTable().getValueAt(i, 9).toString();
				if (anulado.compareTo("0")==0){
					conciliado=true;
				}
			}
			i++;
		}
		return conciliado;
	}
	
	public void _taskworkCargarSwing(Object[][] results) {
		create_table(results);
	}
	
	
	
	public void clean(){
		frame.setJTable(null);
	}
	
	
	
	public void focus(){
		frame.get_txt_idproveedor().requestFocusInWindow();
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
	public boolean hasbeenused(String tc,String idcomprobante){
		boolean used=false;
		int i=0;
		while (!used & i<cptes.size()){
			String[] tmp=cptes.get(i);
			
			used=tmp[0].compareTo(tc)==0&tmp[1].compareTo(idcomprobante)==0;
			
			i++;
		}
		
		return used;
	}
	
	public void recalculateSaldos(){
		cptes=new java.util.ArrayList<String[]>();
		
		double saldo_final=0.0;
		for (int i=0;i<frame.getJTable().getRowCount();i++){
			double debe=0;
			double haber=0;
			String anulado="";
			String aplicacion="";
			debe=(Double)frame.getJTable().getValueAt(i, 3);
			haber=(Double)frame.getJTable().getValueAt(i, 4);
			anulado=(String)frame.getJTable().getValueAt(i, 5);
			aplicacion=(String)frame.getJTable().getValueAt(i, 9);
			String tc=(String)frame.getJTable().getValueAt(i, 1);
			String idcomprobante=(String)frame.getJTable().getValueAt(i, 2);
			if (anulado.compareTo("0")==0){
				    if (!this.hasbeenused(tc, idcomprobante)){
				    	cptes.add(new String[]{tc,idcomprobante});
				    	saldo_final+=debe-haber;	
				    }
						
			}
			
		}
		frame.get_txt_saldo().setText(new Convertidor().getMoney(saldo_final, 2));
	}
	
	public void calculateDeudas(){
		cptes=new java.util.ArrayList<String[]>();
		double saldo_final=0.0;
		for (int i=0;i<frame.getJTable().getRowCount();i++){
			double debe=0;
			double haber=0;
			String anulado="";
			String aplicacion="";
			debe=(Double)frame.getJTable().getValueAt(i, 3);
			haber=(Double)frame.getJTable().getValueAt(i, 4);
			anulado=(String)frame.getJTable().getValueAt(i, 5);
			aplicacion=(String)frame.getJTable().getValueAt(i, 9);
			Date fecha=(Date)frame.getJTable().getValueAt(i, 0);
			String tc=(String)frame.getJTable().getValueAt(i, 1);
			String idcomprobante=(String)frame.getJTable().getValueAt(i, 2);
			if (tc.compareTo("FCN")==0|tc.compareTo("IGR")==0){
				if (anulado.compareTo("1")!=0){
					if (!this.hasbeenused(tc, idcomprobante)){
						this.cptes.add(new String[]{tc,idcomprobante});
						String importe=new Convertidor().getMoney(haber,2);
						this.estaConciliado(tc, idcomprobante);
						String estado="";
						if (this.estaConciliado(tc, idcomprobante)){
							estado="Conciliado";
						}
						addCpte(frame.getJTable1(), false, fecha, tc, idcomprobante, importe,estado);
						//System.out.println("Agregando "+tc+" "+idcomprobante+" a Lista de FVNs ");
					}else{
						//System.out.println(" "+tc+" "+idcomprobante+" ya se encuentra en la Lista de FVNs ");
						
					}
				}
					
			}
		}
		
	}

	public double getAnticipo(String tc,String idcomprobante){
		double importe=0.0;
		Object[][] results=data.getAnticipo(tc, idcomprobante);
		if (results!=null){
			if (results.length>0){
				try {
					importe=new Double((String)results[0][0]);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return importe;
	}
	
	public void evaluatecheckdeudas(JCheckBox jx,int row, int col,boolean transfer){
		double debe=this.recalculate_deudas(row,jx.isSelected());
		double haber=this.recalculate_creditos(-1,false);
		double anticipos=this.recalculate_anticipos(-1,false);
		this.calculate(debe,haber,anticipos);
	}
	public void evaluatecheckcreditos(JCheckBox jx,int row, int col,boolean transfer){
		double haber=this.recalculate_creditos(row,jx.isSelected());
		double debe=this.recalculate_deudas(-1,false);
		double anticipos=this.recalculate_anticipos(-1,false);
		this.calculate(debe,haber,anticipos);
	}
	public void evaluatecheckanticipos(JCheckBox jx,int row, int col,boolean transfer){
		double debe=this.recalculate_deudas(-1,false);
		double haber=this.recalculate_creditos(-1,false);
		double anticipos=this.recalculate_anticipos(row,jx.isSelected());
		this.calculate(debe,haber,anticipos);
	}
	
	public void SeleccionarCreditos(boolean b){
		JTable table=frame.getJTable2();
		int[] rows=table.getSelectedRows();
		if (rows.length>0){
			for (int row=0;row<rows.length;row++){
				table.setValueAt(b, rows[row], 0);
			}	
		}else{
			for (int row=0;row<table.getRowCount();row++){
				table.setValueAt(b, row, 0);
			}
		}
		double haber=this.recalculate_creditos(-1,false);
		double debe=this.recalculate_deudas(-1,false);
		double anticipos=this.recalculate_anticipos(-1,false);
		this.calculate(debe,haber,anticipos);
	}
	
	public void SeleccionarDeudas(boolean b){
		JTable table=frame.getJTable1();
		int[] rows=table.getSelectedRows();
		if (rows.length>0){
			for (int row=0;row<rows.length;row++){
				table.setValueAt(b, rows[row], 0);
			}	
		}else{
			for (int row=0;row<table.getRowCount();row++){
				table.setValueAt(b, row, 0);
			}
		}
		double debe=this.recalculate_deudas(-1,false);
		double haber=this.recalculate_creditos(-1,false);
		double anticipos=this.recalculate_anticipos(-1,false);
		this.calculate(debe,haber,anticipos);
	}
	
	public void SeleccionarAnticipos(boolean b){
		JTable table=frame.getJTable3();
		int[] rows=table.getSelectedRows();
		if (rows.length>0){
			for (int row=0;row<rows.length;row++){
				table.setValueAt(b, rows[row], 0);
			}	
		}else{
			for (int row=0;row<table.getRowCount();row++){
				table.setValueAt(b, row, 0);
			}
		}
		double debe=this.recalculate_deudas(-1,false);
		double haber=this.recalculate_creditos(-1,false);
		double anticipos=this.recalculate_anticipos(-1,false);
		this.calculate(debe,haber,anticipos);
	}
	
	public void seleccionar_pendientes(){
		for (int i=0;i<frame.getJTable1().getRowCount();i++){
			boolean b=false;
			String estado=frame.getJTable1().getValueAt(i, 5).toString();
			b=estado.compareTo("")==0;
			frame.getJTable1().setValueAt(b, i, 0);
		}
		for (int i=0;i<frame.getJTable2().getRowCount();i++){
			boolean b=false;
			String estado=frame.getJTable2().getValueAt(i, 5).toString();
			b=estado.compareTo("")==0;
			frame.getJTable2().setValueAt(b, i, 0);
		}
		for (int i=0;i<frame.getJTable3().getRowCount();i++){
			boolean b=false;
			String estado=frame.getJTable3().getValueAt(i, 5).toString();
			b=estado.compareTo("")==0;
			frame.getJTable3().setValueAt(b, i, 0);
		}
		double haber=this.recalculate_creditos(-1, false);
		double debe=this.recalculate_deudas(-1, false);
		double anticipos=this.recalculate_anticipos(-1,false);
		this.calculate(debe,haber,anticipos);
	}
	
	public void calculateCreditos(){
		cptes=new java.util.ArrayList<String[]>();
		
		for (int i=0;i<frame.getJTable().getRowCount();i++){
			double debe=0;
			double haber=0;
			String anulado="";
			String aplicacion="";
			debe=(Double)frame.getJTable().getValueAt(i, 3);
			haber=(Double)frame.getJTable().getValueAt(i, 4);
			anulado=(String)frame.getJTable().getValueAt(i, 5);
			aplicacion=(String)frame.getJTable().getValueAt(i, 9);
			Date fecha=(Date)frame.getJTable().getValueAt(i, 0);
			String tc=(String)frame.getJTable().getValueAt(i, 1);
			String idcomprobante=(String)frame.getJTable().getValueAt(i, 2);
			if (tc.compareTo("PG")==0|tc.compareTo("EGR")==0){
				if (anulado.compareTo("1")!=0){
					if (!this.hasbeenused(tc, idcomprobante)){
						this.cptes.add(new String[]{tc,idcomprobante});
						String importe=new Convertidor().getMoney(debe,2);
						String estado="";
						if (this.estaConciliado(tc, idcomprobante)){
							estado="Conciliado";
						}else{
							if (tc.compareTo("PG")==0){
								double anticipo=this.getAnticipo(tc, idcomprobante);
								if (anticipo<=0){
									estado="Conciliado";		
								}else{
									importe=new Convertidor().getMoney(anticipo,2);	
								}
							}	
						}
						
						this.addCpteCredito(frame.getJTable2(), false, fecha, tc, idcomprobante, importe,estado);
						//System.out.println("Agregando "+tc+" "+idcomprobante+" a Lista de FVNs ");
					}else{
						//System.out.println(" "+tc+" "+idcomprobante+" ya se encuentra en la Lista de FVNs ");
						
					}
				}
					
			}
		}
		
		
	}
	
	public double recalculate_creditos(int r,boolean ux){
		
		double saldo_final=0.0;
		if (frame.getJTable2()!=null){
		for (int i=0;i<frame.getJTable2().getRowCount();i++){
			boolean selected=false;
			selected=(Boolean) frame.getJTable2().getValueAt(i, 0);
			if (i==r){
				selected=ux;
			}
			if (selected){
				double importe=0.0;
				try {
					importe=(Double)frame.getJTable2().getValueAt(i, 4);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (importe>0){
					saldo_final+=importe;
				}
			}
		}
		}
		return saldo_final;
	}
	
	public double recalculate_anticipos(int r,boolean ux){
		
		double saldo_final=0.0;
		if (frame.getJTable3()!=null){
			for (int i=0;i<frame.getJTable3().getRowCount();i++){
				boolean selected=false;
				selected=(Boolean) frame.getJTable3().getValueAt(i, 0);
				if (i==r){
					selected=ux;
				}
				if (selected){
					double importe=0.0;
					try {
						importe=(Double)frame.getJTable3().getValueAt(i, 4);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (importe>0){
						saldo_final+=importe;
					}
				}
			}	
		}
		
		
		return saldo_final;
	}
	
	public double recalculate_deudas(int r,boolean ux){
		double saldo_final=0.0;
		if (frame.getJTable1()!=null){
		for (int i=0;i<frame.getJTable1().getRowCount();i++){
			boolean selected=false;
			selected=(Boolean) frame.getJTable1().getValueAt(i, 0);
			if (i==r){
				selected=ux;
			}
			if (selected){
				double importe=0.0;
				try {
					importe=(Double)frame.getJTable1().getValueAt(i, 4);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (importe>0){
					saldo_final+=importe;
				}
			}
		}	
		}
		return saldo_final;
	}
	
	public void recalculate(){
		frame.setJTable1(null);
		frame.setJTable2(null);
		frame.setJTable3(null);
		this.calculateCreditos();
		this.calculateDeudas();
		this.calculateAnticipos();
		this.seleccionar_pendientes();
		double debe=this.recalculate_deudas(-1,false);
		double haber=this.recalculate_creditos(-1,false);
		double anticipos=this.recalculate_anticipos(-1,false);
		this.calculate(debe,haber,anticipos);
		drawLines();
		
	}
	
	public void verComprobante(JTable table,int row,int col,Point p){
		int _tc=1;
		
		
		if (table.getName()!=_Interface._table_asientos){
			
				_tc=2;	
			
		}else{
			if (col>=7)
				_tc=7;
		}
		String tc=table.getValueAt(row, _tc).toString();
		String idcomprobante=table.getValueAt(row, _tc+1).toString();
		
		if (tc.compareTo("PG")==0){
			this.verPago(idcomprobante);
		}
		
	}
	
	
	
	private aplicacion.proveedor.pago.constructor._Constructor vPago=null;
	public void verPago(String idpago){
		int row=frame.getJTable().getSelectedRow();
		
		if (idpago.compareTo("")!=0){
				if (vPago!=null){
					vPago.dispose();
				}
				vPago=new aplicacion.proveedor.pago.constructor._Constructor();
				vPago.setParameter(_parametros.connector, this._data.getConnectionHandler());
				vPago.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
				vPago.setParameter(aplicacion.proveedor.pago.interfaces._Parametros.idpago, idpago);
				vPago.build(this.getConstructor());
				vPago.init();	
			
				
		}
	}
	


	public void calculate(double debe,double haber,double anticipos){
		frame.get_txt_anticipos().setText(new Convertidor().getMoney(anticipos,2));
		frame.get_txt_debe().setText(new Convertidor().getMoney(debe,2));
		frame.get_txt_haber().setText(new Convertidor().getMoney(haber,2));
		frame.get_txt_saldo_utilizable().setText(new Convertidor().getMoney(debe-haber,2));
		double saldo=new Double(frame.get_txt_saldo().getText().replace(",", ""));
		double saldo_utilizable=debe-((haber+anticipos));
		double dif=Math.abs(saldo)-Math.abs(saldo_utilizable);
		if (dif==0){
			frame.get_txt_diferencia().setForeground(Color.green);	
		}else{
			frame.get_txt_diferencia().setForeground(Color.red);
		}
		frame.get_txt_diferencia().setText(new Convertidor().getMoney(dif, 2));
		frame.get_txt_saldo_utilizable().setText(new Convertidor().getMoney(saldo_utilizable,2));
	}
	
	private aplicacion.herramientas.java.evaluadores.Proveedor proveedor=null;
	public void initialize_proveedor(){
		proveedor=new aplicacion.herramientas.java.evaluadores.Proveedor(){
			public void cargar(String codigo){
				Object[][] results=this.getInfo(codigo);
				String descripcion=(String) results[0][1];
				frame.get_txt_descripcion_proveedor().setText(descripcion);
				goCargar();
			}
		};
		proveedor.setConstructor(this.getConstructor());
	}
	public void drawLines(){
		Graphics g=frame.getJContentPane().getGraphics();
		g.setColor(Color.LIGHT_GRAY);
		g.drawLine(940,236,940,310);
		g.drawLine(940,332,940,415);
		g.drawLine(740,352,820,408);
		g.drawLine(740,504,820,440);
		
	}
	
	public void BuscarProveedor(JTextField tx){
		proveedor.Buscar(tx);
	}
	public void BuscarProveedor(){
		proveedor.Buscar(frame.get_txt_idproveedor());
	}
	public void buscarProveedor(JTextField tx){
		proveedor.buscar(tx);
	}
	public void evaluarProveedor(JTextField tx){
		proveedor.evaluate(tx);
	}
	
	private JTable create_table_(Object[][] results,String chk_name,String table_name) {
		CustomTable table = new CustomTable();
		
		Column col = new Column();
		col = new Column();
		col.setName("");
		col.setWidth(30);
		col.setEditable(true);
		
		CheckBoxCellEditor chkce = new CheckBoxCellEditor();
		chkce.setItemListener(_constructor.getItemListener());
		chkce.setTipo(Boolean.class);
		chkce.setName(chk_name);
		col.setCellEditor(chkce.getCellCheck());
		col.setClass(Boolean.class);
	    table.addColumn(col);

		
		col = new Column();
		col.setName("Fecha");
		col.setWidth(70);
		col.setEditable(false);
		col.setClass(Date.class);
	
		col.setCellRenderer(new DateRenderer());
		table.addColumn(col);
		
		
		col = new Column();
		col.setName("TC");
		col.setWidth(60);
		col.setEditable(false);
		table.addColumn(col);
		
		col = new Column();
		col.setName("idComprobante");
		col.setWidth(120);
		col.setEditable(false);
		table.addColumn(col);
		
		col = new Column();
		col.setName("importe");
		col.setWidth(70);
		col.setClass(Double.class);
		col.setEditable(false);
		table.addColumn(col);
		
		col = new Column();
		col.setName("estado");
		col.setWidth(70);
		col.setEditable(false);
		table.addColumn(col);
		
		table.setData(results);
		table.addKeyListener(this._constructor.getKeyListener());
		Font fuente=new Font("Arial", Font.PLAIN, 8);
		table.setHeaderFont(fuente);
		table.build();
		table.fillData();
		JTable _table=table.getTable();
		_table.setName(table_name);
		_table.addMouseListener(this.getConstructor().getMouseListener());
		return _table;
	}
	
	private void create_table_deudas(Object[][] results) {
			frame.setJTable1(this.create_table_(results,_Interface._table_deudas_chk,_Interface._table_deudas));
	}
	
	private void create_table_creditos(Object[][] results) {
		frame.setJTable2(this.create_table_(results,_Interface._table_creditos_chk,_Interface._table_creditos));
	}
	
	private void create_table_conciliacion(Object[][] results) {
		frame.setJTable3(this.create_table_(results,_Interface._table_anticipos_chk,_Interface._table_anticipos));
	}
	
	private boolean tableEmpty(JTable table){
		boolean empty=true;
		if (table!=null){
			empty=!(table.getRowCount()>0);
		}
		return empty;
	}
	
	public void addCpte(JTable table,boolean selected,Date fecha,String tc,String idcomprobante,String importe,String estado){
		double imp=0.0;
		try {
			imp=new Double(importe.replace(",", ""));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (this.tableEmpty(table)){
			Object[][] aux=new Object[][]{{selected,fecha,tc,idcomprobante,imp,estado}};
			this.create_table_deudas(aux);
		}else {
			
			
			DefaultTableModel model=(DefaultTableModel) table.getModel();
			int r=model.getRowCount();
			model.setRowCount(r+1);
			
			table.setValueAt(selected, r,0);
			table.setValueAt(fecha,r,1);
			table.setValueAt(tc,r,2);
			table.setValueAt(idcomprobante,r,3);
			table.setValueAt(imp,r,4);
			table.setValueAt(estado,r,5);
		}
	}
	
	public void addCpteCredito(JTable table,boolean selected,Date fecha,String tc,String idcomprobante,String importe,String estado){
		double imp=0.0;
		try {
			imp=new Double(importe.replace(",", ""));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (this.tableEmpty(table)){
			Object[][] aux=new Object[][]{{selected,fecha,tc,idcomprobante,imp,estado}};
			this.create_table_creditos(aux);
		}else {
			
			
			DefaultTableModel model=(DefaultTableModel) table.getModel();
			int r=model.getRowCount();
			model.setRowCount(r+1);
			
			table.setValueAt(selected, r,0);
			table.setValueAt(fecha,r,1);
			table.setValueAt(tc,r,2);
			table.setValueAt(idcomprobante,r,3);
			table.setValueAt(imp,r,4);
			table.setValueAt(estado,r,5);
		}
	}
	public void addCpteConciliacion(JTable table,boolean selected,Date fecha,String tc,String idcomprobante,String importe,String estado){
		double imp=0.0;
		try {
			imp=new Double(importe.replace(",", ""));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (this.tableEmpty(table)){
			Object[][] aux=new Object[][]{{selected,fecha,tc,idcomprobante,imp,estado}};
			this.create_table_conciliacion(aux);
		}else {
			
			
			DefaultTableModel model=(DefaultTableModel) table.getModel();
			int r=model.getRowCount();
			model.setRowCount(r+1);
			
			table.setValueAt(selected, r,0);
			table.setValueAt(fecha,r,1);
			table.setValueAt(tc,r,2);
			table.setValueAt(idcomprobante,r,3);
			table.setValueAt(imp,r,4);
			table.setValueAt(estado,r,5);
		}
	}
	}
	



