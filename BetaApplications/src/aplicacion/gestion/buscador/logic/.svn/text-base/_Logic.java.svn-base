package aplicacion.gestion.buscador.logic;

import java.awt.Color;
import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import aplicacion.herramientas.java.*;

import aplicacion.herramientas.java.sortableselector.*;
import aplicacion.gestion.buscador.gui._Frame;
import aplicacion.gestion.buscador.interfaces._Interface;


import aplicacion.herramientas.java.table.Column;
import aplicacion.herramientas.java.table.CustomTable;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Logic;
import aplicacion.modelo.logic.Data;

public class _Logic extends Logic {
	private _Frame frame = null;
	private _Data data = null;

	private aplicacion.herramientas.java.visualselector.constructor._Constructor vSelector = null;
	

	/**
	 * Incializa la logica de esta aplicacion y carga las extensiones que
	 * utiliza
	 */
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


	public void loadModos(){
		frame.get_lst_modo().removeItemListener(this.getConstructor().getItemListener());
		frame.get_lst_modo().removeAllItems();
		frame.get_lst_modo().addItem(_Interface._option_stock);
		frame.get_lst_modo().addItem(_Interface._option_ventas);
		frame.get_lst_modo().addItem(_Interface._option_compras);
		frame.get_lst_modo().addItemListener(this.getConstructor().getItemListener());
	}
	
	public void evaluate_modos(JComboBox lst){
		if (lst.getSelectedItem().toString().compareTo(_Interface._option_compras)==0){
			fillCompras();
		}
		if (lst.getSelectedItem().toString().compareTo(_Interface._option_ventas)==0){
			fillVentas();
		}
		if (lst.getSelectedItem().toString().compareTo(_Interface._option_stock)==0){
			fillStock();
		}
		frame.get_txt_idcuenta().requestFocusInWindow();
	}
	
	public void evaluate_tc(JComboBox lst){
		frame.get_txt_idcomprobante().requestFocusInWindow();
	}
	
	public void fillCompras(){
		frame.get_lst_tc().removeItemListener(this.getConstructor().getItemListener());
		frame.get_lst_tc().removeAllItems();
		frame.get_lst_tc().addItem("");
		frame.get_lst_tc().addItem("FCC");
		frame.get_lst_tc().addItem("NCC");
		frame.get_lst_tc().addItem("FCN");
		frame.get_lst_tc().addItem("RMC");
	}
	
	public void fillVentas(){
		frame.get_lst_tc().removeItemListener(this.getConstructor().getItemListener());
		frame.get_lst_tc().removeAllItems();
		frame.get_lst_tc().addItem("");
		frame.get_lst_tc().addItem("FC");
		frame.get_lst_tc().addItem("NC");
		frame.get_lst_tc().addItem("FVN");
		frame.get_lst_tc().addItem("NCN");
		frame.get_lst_tc().addItem("TK");
		frame.get_lst_tc().addItem("TKFC");
		frame.get_lst_tc().addItem("RM");
	}
	
	public void fillStock(){
		frame.get_lst_tc().removeItemListener(this.getConstructor().getItemListener());
		frame.get_lst_tc().removeAllItems();
		frame.get_lst_tc().addItem("");
		frame.get_lst_tc().addItem("FC");
		frame.get_lst_tc().addItem("NC");
		frame.get_lst_tc().addItem("FVN");
		frame.get_lst_tc().addItem("NCN");
		frame.get_lst_tc().addItem("TK");
		frame.get_lst_tc().addItem("TKFC");
		frame.get_lst_tc().addItem("RM");
		frame.get_lst_tc().addItem("FCC");
		frame.get_lst_tc().addItem("NCC");
		frame.get_lst_tc().addItem("FCN");
		frame.get_lst_tc().addItem("RMC");
		frame.get_lst_tc().addItem("AJP");
		frame.get_lst_tc().addItem("AJN");
		frame.get_lst_tc().addItem("SI");
		frame.get_lst_tc().addItem("CTRL");
	}
	
	public void focus(){
		frame.get_lst_modo().requestFocusInWindow();
	}
	
	public void cargarCuenta(String idcuenta){
		Object[][] results=data.getClientInformation(idcuenta);
		if (results!=null){
			if (results.length>0){
				frame.get_txt_idcuenta().setText(idcuenta);
				
				String desc=results[0][1].toString();
				frame.get_txt_cuenta_descripcion().setText(desc);
				
				frame.get_txt_fecha_desde().requestFocusInWindow();
			}
		}
	}
	private aplicacion.herramientas.java.evaluadores.Cliente cliente=null;
	public void initialize_cliente(){
		cliente=new aplicacion.herramientas.java.evaluadores.Cliente(){
			public void cargar(String codigo){
				cargarCuenta(codigo);
			}
		};
		cliente.setConstructor(this.getConstructor());
	}
	public void BuscarCliente(JTextField tx){
		cliente.Buscar(tx);
	}
	public void BuscarCliente(){
		cliente.Buscar(frame.get_txt_idcuenta());
	}
	public void buscarCliente(JTextField tx){
		cliente.buscar(tx);
	}
	public void evaluarCliente(JTextField tx){
		cliente.evaluate(tx);
	}

	public void evaluar_cuenta(JTextField tx){
		if (frame.get_lst_modo().getSelectedItem().toString().compareTo(_Interface._option_ventas)==0){
			this.evaluarCliente(tx);
		}
		if (frame.get_lst_modo().getSelectedItem().toString().compareTo(_Interface._option_compras)==0){
			this.evaluarProveedor(tx);
		}
	}
	
	public void BuscarCuenta(JTextField tx){
		if (frame.get_lst_modo().getSelectedItem().toString().compareTo(_Interface._option_ventas)==0){
			this.BuscarCliente(tx);
		}
		if (frame.get_lst_modo().getSelectedItem().toString().compareTo(_Interface._option_compras)==0){
			this.BuscarProveedor(tx);
		}
	}
	
	private aplicacion.herramientas.java.evaluadores.Proveedor proveedor=null;
	public void initialize_proveedor(){
		proveedor=new aplicacion.herramientas.java.evaluadores.Proveedor(){
			public void cargar(String codigo){
				cargarCuenta(codigo);
			}
		};
		proveedor.setConstructor(this.getConstructor());
	}
	public void BuscarProveedor(JTextField tx){
		proveedor.Buscar(tx);
	}
	public void BuscarProveedor(){
		proveedor.Buscar(frame.get_txt_idcuenta());
	}
	public void buscarProveedor(JTextField tx){
		proveedor.buscar(tx);
	}
	public void evaluarProveedor(JTextField tx){
		proveedor.evaluate(tx);
	}
	
	private aplicacion.herramientas.java.buscadores.Fecha bFecha=null;
	public void BuscarFecha(JTextField tx){
		if (bFecha==null){
			bFecha=new aplicacion.herramientas.java.buscadores.Fecha(this.getConstructor());
		}
		bFecha.Buscar(tx);
	}
	
	public void BuscarFechaDesde(){
		this.BuscarFecha(frame.get_txt_fecha_desde());
	}
	public void BuscarFechaHasta(){
		this.BuscarFecha(frame.get_txt_fecha_hasta());
	}
	
	public void evaluate_fecha_desde(JTextField tx){
		frame.get_txt_fecha_hasta().requestFocusInWindow();
	}
	public void evaluate_fecha_hasta(JTextField tx){
		frame.get_lst_tc().requestFocusInWindow();
	}
	
	public void cargar(){
		
	}
	private CustomTable crearTablaDeItems(boolean editable) {
		CustomTable Table = new CustomTable();
		Column col = new Column();
	
			
		col = new Column();
		col.setName("fecha");
		col.setClass(Date.class);
		col.setCellRenderer(new DateRenderer());
		col.setWidth(120);
		col.setEditable(false);
		Table.addColumn(col);
		
		col = new Column();
		col.setName("cuenta");
		col.setWidth(80);
		Table.addColumn(col);
		
		col = new Column();
		col.setName("descripcion");
		col.setWidth(120);
		Table.addColumn(col);
		
		col = new Column();
		col.setName("tc");
		col.setWidth(80);
		Table.addColumn(col);
		
		col = new Column();
		col.setName("idcomprobante");
		col.setWidth(100);
		Table.addColumn(col);
		
		col = new Column();
		col.setName("importe");
		col.setWidth(120);
		col.setEditable(false);
		col.setClass(Double.class);
		col.setCellRenderer(new MoneyRenderer());
		col.setAligment(JTextField.RIGHT);
		Table.addColumn(col);
		
		return Table;
	}

}