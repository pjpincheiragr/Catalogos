package aplicacion.gestion.detalle.logic;

import java.awt.Font;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import aplicacion.herramientas.java.*;
import aplicacion.herramientas.java.sortableselector.logic.Filtro;
import aplicacion.herramientas.java.sortableselector.logic.columna;
import aplicacion.herramientas.java.table.Column;
import aplicacion.herramientas.java.table.CustomTable;
import aplicacion.gestion.caja.manejo.logic.DateRenderer;
import aplicacion.gestion.caja.manejo.logic.MoneyRenderer;
import aplicacion.gestion.detalle.gui._Frame;
import aplicacion.gestion.detalle.interfaces._Interface;

import aplicacion.modelo.logic.Logic;
import aplicacion.modelo.logic.Data;
import aplicacion.modelo.interfaces.*;

public class _Logic extends Logic{
	private _Frame frame=null;
	private _Data data=null;
	private aplicacion.herramientas.java.visualselector.constructor._Constructor vSelector=null;
	private String query="";
	
	public _Logic(){
		System.out.println("Extension=?"+this.getExtension("Pago_Control"));
		System.out.println("Extension=?"+this.getExtension("Pago_Asiento"));
	}
	public void setFrame(JFrame _frame){
		this.frame=(_Frame)_frame;
		super.setFrame(_frame);
	}
	
	public void setData(Data _data){
		this.data=(_Data)_data;
		super.setData(_data);
	}
	
	
	public void clean(){
		frame.get_txt_saldo().setText("0.00");
		frame.setJTable(null);
	}
	
	public void cancelar(){
		if (preguntar("Confirmar","Cancela?")) {
			clean();
		}
	}
	
	
	
public Double getTotal(Object[][] aux){
		Double tmp=0.0;
		for (int i=0;i<aux.length;i++){
			double _tmp=0.0;
			try {
				_tmp= new Double((String) aux[i][4]);
				
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tmp+=_tmp;
		}
		return tmp;
}
	
private void create_tabla(Object[][] results) {
	Object[][] empty_medios = new Object[][] { { "", "", "", "", "", "",
			"", "", "" } };
	// public void create_table_medios(Object[][] results) {
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

	table.addColumn(col);

	table.setData(results);

	Font fuente = new Font("Arial", Font.PLAIN, 9);
	table.setHeaderFont(fuente);
	table.setSortable(false);
	table.build();

	table.fillData();
	JTable _table = table.getTable();
	aplicacion.gestion.detalle.constructor._Constructor C=
		(aplicacion.gestion.detalle.constructor._Constructor) this.getConstructor();
	_table.addMouseListener(C.getMouseListener());
	frame.setJTable(table.getTable());
}

public void cargar(){
		_Data data=(_Data)this.getData();
		Object[][] results=null;
			
			results=data.get_movimientos(query);
			if (results!=null){
				if (results.length>0){
					for (int i = 0; i < results.length; i++) {
						DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
						try {
							results[i][0] = (Date) formatter
									.parse(results[i][0].toString());
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						double debe = 0.0;
						double haber = 0.0;
						
						try {
							debe = new Double(results[i][4].toString());
							haber = new Double(results[i][5].toString());
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						results[i][4] = debe;
						results[i][5] = haber;
					}
					frame.setJTable(null);	
					this.create_tabla(results);
					this.calculate();	
				
				}
			}
		
		
	
}
	
public void init(){
}
	
		
public _Data getData(){
		return this.data;
	}
/*
private aplicacion.herramientas.java.ireport.constructor._Constructor reporte=null;
public void reporte(){
	if (reporte!=null){
		 reporte.dispose();
	}
	String cuenta=frame.get_txt_cuenta().getText();
	String cuenta_detalle=frame.get_txt_cuenta_detalle().getText();
	String caja=frame.get_list_cajas().getSelectedItem().toString();
	String desde="01-01-1900";
	String hasta="01-01-2900";
	String _desde=frame.get_txt_fecha().getText();
	String _hasta=frame.get_txt_fecha_hasta().getText();
	if (_desde.compareTo("")!=0){
		desde=_desde;
	}
	if (_hasta.compareTo("")!=0){
		hasta=_hasta;
	}
	reporte=new aplicacion.herramientas.java.ireport.constructor._Constructor();
	HashMap param=new HashMap();
	param.put("cuenta",cuenta);
	param.put("cuenta_descripcion",cuenta_detalle);
	param.put("caja", caja);
	param.put("desde", desde);
	param.put("hasta", hasta);
	reporte.setParameter(_parametros.LookAndFeel,this.getConstructor().getLookAndFeelTheme());
	reporte.setParameter(_parametros.connector,this.getConstructor().getConnectionHandler());
	reporte.setParameter(aplicacion.herramientas.java.ireport.interfaces._parametros.parametros, param);
	reporte.setParameter(aplicacion.herramientas.java.ireport.interfaces._parametros.reporte, "reporte_movimientos.jasper");
	reporte.build(this.getConstructor());
	reporte.init();	
}
*/

	
	public void calculate(){
		double saldo=0.0;
		double _debe=0.0;
		double _haber=0.0;
		for (int i=0;i<frame.getJTableMedios().getRowCount();i++){
			try {
				double debe = (Double)frame.getJTableMedios().getValueAt(i, 4);
				double haber = (Double)frame.getJTableMedios().getValueAt(i, 5);
				_debe+=debe;
				_haber+=haber;
				saldo+=(debe-haber);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		frame.get_txt_debe().setText(""+new Convertidor().roundDouble(_debe, 2));
		frame.get_txt_haber().setText(""+new Convertidor().roundDouble(_haber, 2));
		frame.get_txt_saldo().setText(""+new Convertidor().roundDouble(saldo, 2));
	}
	
	public void setQuery(String query){
		this.query=query;
	}
}