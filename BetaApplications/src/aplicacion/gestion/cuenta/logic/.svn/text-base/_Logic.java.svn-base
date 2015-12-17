package aplicacion.gestion.cuenta.logic;

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
import aplicacion.gestion.cuenta.gui._Frame;
import aplicacion.gestion.cuenta.interfaces._Interface;
import aplicacion.gestion.cuenta.logic.extensions.Cuenta_Medios_de_Pago;
import aplicacion.gestion.cuenta.logic.extensions.Cuenta_Asiento;
import aplicacion.modelo.logic.Logic;
import aplicacion.modelo.logic.Data;
import aplicacion.modelo.interfaces.*;

public class _Logic extends Logic{
	private _Frame frame=null;
	private _Data data=null;
	private aplicacion.herramientas.java.visualselector.constructor._Constructor vSelector=null;
	
	public _Logic(){
		Cuenta_Medios_de_Pago control=new Cuenta_Medios_de_Pago();
		Cuenta_Asiento asiento=new Cuenta_Asiento();
		this.addExtension(control);
		this.addExtension(asiento);
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
	
	/**
	 * Metodo para seleccionar la caja con que debe trabajar la aplicacion.
	 * Este metodo se utiliza cuando se llama a esta aplicacion desde gestion de caja 
	 * @param idcaja
	 */
	public void setCaja(String idcaja){
		for (int i=0;i<frame.get_list_cajas().getItemCount();i++){
			if (idcaja.compareTo(frame.get_list_cajas().getItemAt(i).toString())==0){
				frame.get_list_cajas().setSelectedIndex(i);
			}
		}
		frame.get_txt_cuenta().requestFocusInWindow();
	}
	
	public void clean(){
		frame.get_txt_saldo().setText("0.00");
		frame.get_txt_fecha().setText("");
		frame.get_txt_cuenta().setText("");
		frame.get_txt_cuenta_detalle().setText("");
		frame.setJTable(null);
		cargar_cajas();
		this.setInitialDate();
	}
	
	public void cancelar(){
		if (preguntar("Confirmar","Cancela?")) {
			clean();
		}
	}
	
	
public void getToday(){
_Frame _frame=(_Frame) this._frame;
_frame.get_txt_fecha().setText(
			new Convertidor().getDateWithFormat("dd-MM-yyyy")
			);	
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

	table.build();

	table.fillData();
	JTable _table = table.getTable();
	aplicacion.gestion.cuenta.constructor._Constructor C=
		(aplicacion.gestion.cuenta.constructor._Constructor) this.getConstructor();
	_table.addMouseListener(C.getMouseListener());
	frame.setJTable(table.getTable());
}

public void cargar(){
		_Data data=(_Data)this.getData();
		String caja=frame.get_list_cajas().getSelectedItem().toString();
		String cuenta=frame.get_txt_cuenta().getText();
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
		Object[][] results=null;
		if (cuenta.compareTo("")!=0){
			
			results=data.get_movimientos(caja, cuenta,desde,hasta);
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
							debe = new Double(results[i][5].toString());
							haber = new Double(results[i][6].toString());
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						results[i][5] = debe;
						results[i][6] = haber;
					}
					frame.setJTable(null);	
				this.create_tabla(results);
				this.calculate();
				
				}else {
					
					caja=caja.replaceAll(" ", "");
					this.error("No hay movimientos disponibles para la caja "+caja+" cuenta "+cuenta);
				}
			}else {
				
				caja=caja.replaceAll(" ", "");
				this.error("No hay movimientos disponibles para la caja "+caja+" cuenta "+cuenta);
		}
		}else{
			error("Debe Ingresar la cuenta para ver movimientos");
		}
		
		
	
}
	
public void init(){
		this.cargar_cajas();
		this.setInitialDate();
}
	
		
public _Data getData(){
		return this.data;
	}
	
public void evaluate_caja(JComboBox cb){
		String desc=cb.getSelectedItem().toString();
		desc=data.getDetalleCaja(desc);
		if (desc.compareTo("")!=0){
			frame.get_txt_caja_detalle().setText(desc);
		}
		
	}

public void cargar_cajas(){
	frame.get_list_cajas().removeAllItems();
	frame.get_list_cajas().removeActionListener(null);
	_Data data=(_Data) _data;
	String iduser=this.getConstructor().getIduser();
	Object[][] results=data.get_cajas_origen(iduser);
	for (int i=0;i<results.length;i++){
		
		try {
			frame.get_list_cajas().addItem(results[i][0]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


private aplicacion.herramientas.java.visualizadores.Cuenta vCuenta=null;
public void buscarCuenta(JTextField tx) {
	if (vCuenta!=null){
		vCuenta.close();
	}
	vCuenta=new aplicacion.herramientas.java.visualizadores.Cuenta(this.getConstructor());
	int n = vCuenta.Buscar(tx);
	if (n == 0) {
			aviso("no hay Cuentas con ese codigo");
	}
}
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

public void _evaluar_codigo_cuenta(JTextField tx){
	String aux=tx.getText();
	if (aux.compareTo("")!=0){
			int n=0;
			try {
				n=new Integer(aux);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			if (n>0){
				this.load_All(tx);	
			}else {
				this.buscarCuenta(tx);
			}
				
	}else {
		aviso("Ingrese Codigo de Cuenta. utilice F5 para buscar");
	}
}

public void load_All(JTextField tx){
	String aux=tx.getText();
	if (aux.compareTo("")!=0){
		if (Character.isLetter(aux.charAt(0))){
			//goVisualGuessCliente(tx);
		}else {
			loadAll(aux);	
		}
		
	}
	
}

public void loadAll(String idcliente){
	
	Object[][] results=data.getClientInformation(idcliente);
	if (results!=null){
		if (results.length>0){
			
			String desc=results[0][1].toString();
			frame.get_txt_cuenta_detalle().setText(desc);
			this.cargar();
		}else {
			aviso("El Codigo de Cuenta es Inexistente");
			frame.get_txt_cuenta().requestFocusInWindow();
		}
	}
}
public void BuscarCuenta(){
	this.BuscarCuenta(frame.get_txt_cuenta());
}


private aplicacion.herramientas.java.buscadores.Cuenta bCuenta=null;
public void BuscarCuenta(JTextField ext) {
	 if (bCuenta==null){
		 bCuenta=new aplicacion.herramientas.java.buscadores.Cuenta(this.getConstructor());
	 }

 bCuenta.Buscar(ext);
} 
public void setInitialDate(){
	String aux=data.getSystemDate();
	frame.get_txt_fecha().setText(aux);
}

public void transfer_fecha_focus(JTextField tx){
	if (tx.getName()==_Interface._txt_fecha){
		frame.get_txt_fecha_hasta().requestFocusInWindow();
	}
	if (tx.getName()==_Interface._txt_fecha_hasta){
		cargar();
	}
}

	private aplicacion.herramientas.java.evaluadores.Fecha Fecha=null;
	public void initialize_Fecha(){
		Fecha=new aplicacion.herramientas.java.evaluadores.Fecha(){
			public void cargar(JTextField tx){
				transfer_fecha_focus(tx);
			}
		};
		Fecha.setConstructor(this.getConstructor());
	}
	public void BuscarFecha(JTextField tx){
		Fecha.Buscar(tx);
	}
	public void BuscarFecha(){
		Fecha.Buscar(frame.get_txt_fecha());
	}
	
	public void evaluarFecha(JTextField tx){
		Fecha.evaluate(tx);
	}
	
	public void calculate(){
		double saldo=0.0;
		double _debe=0.0;
		double _haber=0.0;
		for (int i=0;i<frame.getJTableMedios().getRowCount();i++){
			try {
				double debe = (Double)frame.getJTableMedios().getValueAt(i, 5);
				double haber = (Double)frame.getJTableMedios().getValueAt(i, 6);
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
}