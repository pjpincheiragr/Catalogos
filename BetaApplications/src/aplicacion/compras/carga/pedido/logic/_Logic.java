package aplicacion.compras.carga.pedido.logic;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Logic;
import aplicacion.modelo.logic.Data;



import aplicacion.compras.carga.pedido.logic.*;
import aplicacion.compras.carga.pedido.gui.*;
import aplicacion.compras.carga.pedido.interfaces.*;
import aplicacion.herramientas.java.launcher.logic.SwingWorker;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import aplicacion.herramientas.java.*;
//import aplicacion.compras.carga.pedido.logic.*;
import aplicacion.compras.carga.pedido.gui._Email;
import aplicacion.compras.carga.pedido.interfaces._Interface;


import aplicacion.herramientas.java.sortableselector.logic.Filtro;
import aplicacion.herramientas.java.sortableselector.logic.columna;
import aplicacion.herramientas.java.table.*;
import aplicacion.herramientas.java.table.CellEditor;

public class _Logic extends Logic {
	private _Data data;
	private _Frame frame;
	private aplicacion.herramientas.java.visualizadores.Proveedor vProveedor=null;
	private boolean nuevo=false;
	private _Email email = null;
	private String emailFrom = "";
	private String password = "";
	private String email_asunto = "";
	private boolean cambios=false;
	private String _cliente = "";
	String tc = "PDP";
	public void setFrame(JFrame frame){
		this.frame=(_Frame) frame;
		super.setFrame(frame);
	}
	
	public void setData(Data _data){
		this.data=(_Data)_data;
		super.setData(this.data);
	}
	
	
	private Timer Timer;  //  @jve:decl-index=0:
	private Crono crono;
	private String estado="";
	private int current;
	private int lenght;
	private boolean done,canceled;
	
	private int errors=0;
	
	String desde = "01-04-2006";

	public void _ayuda() {

	}
	 
	 public void buscar_fecha_desde(){
		 this.BuscarFecha(frame.get_txt_desde());
	 }
	
	 public void buscar_fecha_hasta(){
		 this.BuscarFecha(frame.get_txt_hasta());
	 }
	 
	 
	 public void evaluate_fecha_desde(JTextField tx){
		 
	 }
	 
	 public void eliminar(){
		 if (confirmar("Confirme para eliminar el pedido ",3)){
			 _eliminar();
		 }
	 }
	 /*
	 public void setEstados(){
		 frame.get_lst_estado().removeItemListener(this.getConstructor().getItemListener());
		 frame.get_lst_estado().removeAllItems();
		 frame.get_lst_estado().addItem("Nuevo");
		 frame.get_lst_estado().addItem("Enviado");
		 frame.get_lst_estado().addItem("Recibido");
		 frame.get_lst_estado().addItemListener(this.getConstructor().getItemListener());
	 }*/
	 
	 public void _eliminar(){
		 String idpedido=frame.get_txt_idpedido().getText();
		 String q=data.getEliminarPedido(idpedido);
		 data.beginTransaction();
		 data.clearBatch();
		 data.addBatch(q);
		 boolean error=data.executeBatch();
		 if (!error){
			 data.commitTransaction();
			 aviso("Se elimino correctamente el pedido");
			 this.clean();
		 }else{
			 data.rollbackTransaction();
			 error("Error eliminando pedido");
		 }
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
	
	public void _nuevo() {
		nuevo=true;
		frame.get_txt_descripcion_pedido().setText("PEDIDO POR PROYECCION");
		frame.get_txt_idproveedor().setEditable(true);
		frame.get_btn_nuevo_pedido().setEnabled(false);
		String pedido = data.getProximoPEDIDO_Ceros();
		int inc=0;
		while (data.existe(pedido) & inc<3){
			data.updateTC();
			pedido = data.getProximoPEDIDO_Ceros();
			inc++;
		}
		if (inc<3){
			frame.get_txt_idpedido().setText(pedido);
			frame.get_txt_idpedido().setEditable(false);
			frame.get_btn_agregar_asteriscos().setEnabled(true);
			frame.get_btn_agregar_linea().setEnabled(true);
			frame.get_btn_quitar_linea().setEnabled(true);
			frame.get_btn_cancelar().setEnabled(true);
			frame.get_txt_idproveedor().requestFocusInWindow();	
		}else{
			error("No se pudo cargar un nuevo pedido. consulte a sistemas");
		}
		
		
	}

	public void seleccionar_lineas_predeterminadas(boolean b){
		if (this.lineas!=null){
			JTable table=this.lineas.getJTable();
			for (int i=0;i<table.getRowCount();i++){
				table.setValueAt(b, i, 0);
			}
		}

	}

	public void _cargar_proveedor(String aux) {
				Object[][] results = data.getResults(data.getProvQuery(aux));
				if (results != null) {
					if (results.length > 0) {
						String desc = results[0][1].toString();
						frame.get_txt_descripcion().setText(
								desc);
						
						frame.get_txt_idproveedor()
								.setEditable(false);
						this.goCargar();
					}
		}
	}



	private void crear_pedido_minimo(String linea) {
		// pedido minimo inicial cuando un pedid
		String idpedido = frame.get_txt_idpedido().getText();
		String idproveedor = frame.get_txt_idproveedor().getText();
		String desde=frame.get_txt_desde().getText();
		String hasta=frame.get_txt_hasta().getText();
		String categoria=frame.get_lst_categoria_generacion().getSelectedItem().toString();
		String q = data.getLineaItemsCreation(idpedido,idproveedor,linea,desde,hasta,categoria);
		
		Object[][] aux = data.getResults(q);
		if (aux.length>0){
			System.out.println(linea+"> crear pedido minimo a partir de: "+q);
			int dias=0;
			try {
				dias=new Integer(frame.get_txt_proyectado().getText());
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int margen=0;
			try {
				margen=new Integer(frame.get_txt_margen().getText());
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.crear_pedido_items(aux, linea,dias,margen);	
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
	
	public String getDaysRoll(String fecha_actual,int days){
		Date today=new Convertidor().getDate(fecha_actual);
		java.util.GregorianCalendar _today=new java.util.GregorianCalendar();
		_today.setTime(today);
		//_today.roll(Calendar.DAY_OF_YEAR, days);
		_today.add(Calendar.DAY_OF_YEAR, days);
		Date before=_today.getTime();
		String s2=new Convertidor().getDateWithFormat("dd-MM-yyyy", before);
		return s2;
	}
	

	public void delete_articulo_varios(String code){
		int r=this.existcode(code);
		String linea=frame.get_txt_linea().getText();
		String idpedido = frame.get_txt_idpedido().getText();
		System.out.println("delete articulo "+code);
	 if (r>=0){
		 data.clearBatch();
		 data.addBatch(data.getDeleteArticulo(idpedido,linea, code));
		 data.executeBatch();
		 DefaultTableModel model=(DefaultTableModel) frame.getJTable1().getModel();
		 model.removeRow(r);
		 
	 }
	}
	


	private boolean pedido_existe() {

		return data.existe(frame.get_txt_idpedido().getText());
	}


	private void update_pedido(String art, String descripcion, double costo,
			double pedir, boolean usar,String linea) {
		descripcion=descripcion.replaceAll("'", "");
		String idpedido = frame.get_txt_idpedido().getText();
		String q = "";
		if (data.exist(idpedido,art)) {
			q = data.getUpdateItem(idpedido, art, pedir,usar);

		} else {
			q = data.getInsertItem(idpedido,art, descripcion, pedir, costo, usar,linea);
		}
		System.out.println("existe " + art + "? " + data.exist(idpedido,art)
				+ "> guardar en pedido "
				+ frame.get_txt_idpedido().getText()
				+ "> pedir=" + pedir);
		data.clearBatch();
		data.addBatch(q);
		boolean error = data.executeBatch();
	}

	
	public void create_table_items(Object[][] results,boolean editable){
		CustomTable table = this.crearTablaDeItems(editable);
		results = this.procesar_items_linea(results);
		table.setData(results);
		table.build();
		table.fillData();
		JTable tablex=table.getTable();
		tablex.setColumnSelectionAllowed(false);
		tablex.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "none");
		tablex.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "none");
		frame.setJTable1(tablex);
		this.recalculate_vars();
		done=true;
	}
	
	public void evaluate_categoria(JComboBox cb){
		String linea=frame.get_txt_linea().getText();
		this.goMostrarLinea(linea);
	}
	
	private void mostrar_linea(String linea) {
		frame.get_txt_linea().setText(linea);
		String idpedido = frame.get_txt_idpedido().getText();
		String idproveedor = frame.get_txt_idproveedor().getText();
		estado="cargando " + linea ;
		int tipo=0;
		try {
			tipo=frame.get_lst_modo().getSelectedIndex();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String desde=frame.get_txt_desde().getText();
		String hasta=frame.get_txt_hasta().getText();
		String categoria="";
		try {
			categoria = frame.get_lst_categoria().getSelectedItem().toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String stock="";
		try {
			stock = frame.get_lst_stock().getSelectedItem().toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String descripcion=frame.get_txt_descripcion_linea().getText();
		
		String q =data.getLineaItems(idpedido,idproveedor,linea,desde,hasta,categoria,stock,tipo,descripcion);
		System.out.println(q);
		
		frame.get_txt_articulo().setText("");
		frame.get_txt_articulo_actualizacion().setText("");
		frame.get_txt_articulo_bloqueado().setText("");
		frame.get_txt_articulo_linea().setText("");
		frame.get_txt_articulo_stock().setText("");
		frame.get_txt_articulo_descripcion().setText("");
		Object[][] results = data.getResults(q);
		if (results!=null){
			
				boolean editable=linea.compareTo("ARTICULOS VARIOS")==0;
				if (editable & results.length<=0){
					results=new Object[1][11];
				}
				if (javax.swing.SwingUtilities.isEventDispatchThread()){
					this.create_table_items(results, editable);
					frame.setJTable_articulos(null);
				}else{
					final boolean _editable=editable;
					final Object[][] _results=results;
					Runnable _execute = new Runnable() {
				        public void run() {
				        	frame.setJTable_articulos(null);
				        	create_table_items(_results, _editable);    	
				        }
					};
					invokeLater(_execute);	
				}
				
				
				block(true);		
			
		}else{
			done=true;
		}
	}
	
	
	private void _block(boolean b){
		if (frame.getJTable()!=null){
			frame.getJTable().setEnabled(b);	
		}
		if (frame.getJScrollPane()!=null){
			frame.getJScrollPane().setEnabled(b);	
		}
		if (frame.getJTable1()!=null){
			frame.getJTable1().setEnabled(b);	
		}
		if (frame.getJScrollPane1()!=null){
			frame.getJScrollPane1().setEnabled(b);	
		}
		frame.get_btn_agregar_asteriscos().setEnabled(b);
		frame.get_btn_agregar_linea().setEnabled(b);
		frame.get_btn_eliminar().setEnabled(b);
		frame.get_btn_email().setEnabled(b);
		frame.get_btn_quitar_linea().setEnabled(b);
		frame.get_btn_crear_info().setEnabled(b);
		frame.get_btn_guardar().setEnabled(b);
		frame.get_btn_imprimir().setEnabled(b);
	}
		
	public void block(boolean b){
		final boolean _b=b;
		Runnable _execute = new Runnable() {
	        public void run() {
	        	_block(_b);
	        }
		};
		
		if (javax.swing.SwingUtilities.isEventDispatchThread()){
			_block(b);
		}else{
			invokeLater(_execute);	
		}
		
	}
	/*
	public void imprimir(){
		webgenerator web=new webgenerator();
		
		columna c=new columna();
		c.setNombre("codigo");
		c.setWidth(140);
		web.addColumn(c);
		
		c=new columna();
		c.setNombre("descripcion");
		c.setWidth(2000);
		web.addColumn(c);
		
		c=new columna();
		c.setNombre("cantidad");
		c.setWidth(100);
		web.addColumn(c);
		
		c=new columna();
		c.setNombre("linea");
		c.setWidth(100);
		web.addColumn(c);
		
		web.setHojaFilas(36);
		
		Object[][] data=this.getDataToPrint();
		web.setEncabezado("Pedido "+frame.get_txt_idpedido().getText()+" - "+frame.get_txt_descripcion().getText());
		web.setFontSize(10);
		web.setData(data);
		web.setFileName("pedido_"+frame.get_txt_idpedido().getText()+".html");
		web.create();
	}
	*/
	
	public void imprimir(){
		String idpedido=frame.get_txt_idpedido().getText();
		if (PedidoProveedor.existe(idpedido)){
			reporte();	
		}
		
	}
	public void reporte(){
			boolean error = this._guardar();
				if (!error) {
					this._reporte();
			}
		
	}
	
	public void control_stock(){
		if (reporte!=null){
			 reporte.dispose();
		}
		String idpedido=frame.get_txt_idpedido().getText();
		String proveedor=frame.get_txt_descripcion().getText();
		reporte=new aplicacion.herramientas.java.ireport.constructor._Constructor();
		String categoria="A";
		categoria=frame.get_lst_categoria_generacion().getSelectedItem().toString();
		HashMap param=new HashMap();
		param.put("idpedido",idpedido);
		param.put("proveedor",proveedor);
		param.put("categoria",categoria);
		reporte.setParameter(_parametros.LookAndFeel,this.getConstructor().getLookAndFeelTheme());
		reporte.setParameter(_parametros.connector,this.getConstructor().getConnectionHandler());
		reporte.setParameter(aplicacion.herramientas.java.ireport.interfaces._parametros.parametros, param);
		reporte.setParameter(aplicacion.herramientas.java.ireport.interfaces._parametros.reporte, "pedido_control.jasper");	
		reporte.build(this.getConstructor());
		reporte.init();	
	}
	
	private aplicacion.herramientas.java.ireport.constructor._Constructor reporte=null;
	public void _reporte(){
		if (reporte!=null){
			 reporte.dispose();
		}
		String idpedido=frame.get_txt_idpedido().getText();
		String empresa=data.getNombreEmpresa();
		String telefono=data.getTelefonoEmpresa();
		String email=data.getEmail();
		reporte=new aplicacion.herramientas.java.ireport.constructor._Constructor();
		HashMap param=new HashMap();
		param.put("idpedido",idpedido);
		param.put("empresa",empresa);
		param.put("telefono",telefono);
		param.put("email",email);
		reporte.setParameter(_parametros.LookAndFeel,this.getConstructor().getLookAndFeelTheme());
		reporte.setParameter(_parametros.connector,this.getConstructor().getConnectionHandler());
		reporte.setParameter(aplicacion.herramientas.java.ireport.interfaces._parametros.parametros, param);
		reporte.setParameter(aplicacion.herramientas.java.ireport.interfaces._parametros.reporte, "pedidoproveedor.jasper");	
		
		reporte.build(this.getConstructor());
		reporte.init();	
	}
	
	
	private Object [][] getDataToPrint(){
		Object[][] tmp=null;
		int length=0;
		int width=0;
		LinkedList list=new LinkedList();
		for (int u=0;u<frame.getJTable().getRowCount();u++){
			String linea=frame.getJTable().getValueAt(u, 1).toString();
			Boolean b=(Boolean) frame.getJTable().getValueAt(u, 0);
			Object[][] results=this.get_pedido_items(linea);
			if (results!=null){
				
				if (b & results.length>0){
					list.add(results);
					length+=results.length;
					width=results[0].length;
				}
			}
		}
		if (length>0){
			int row=0;
			tmp=new Object[length][width];
			for (int u=0;u<list.size();u++){
				Object[][] results=(Object[][]) list.get(u);
				for (int i=0;i<results.length;i++){
					for (int j=0;j<results[0].length;j++){
						tmp[row][j]=results[i][j];
					}
					row++;
				}
			}
		}
		return tmp;
	}
	private Object[][] get_pedido_items(String linea){
		Object[][] results=null;
		String idpedido = frame.get_txt_idpedido().getText();
		String idproveedor = frame.get_txt_idproveedor().getText();
		String qx=data.getLineaSelectedItems(idpedido,idproveedor,linea);
		System.out.println(qx);
			results=data.getResults(qx);
			if (results!=null){
				if (results.length>0){
					for (int i = 0; i < results.length; i++) {
						String art = (String) results[i][0];
						String desc = (String) results[i][1];
						if (desc.length()>80){
							desc=desc.substring(0, 80);	
						}
						
						String guard = (String) results[i][2];
						
						boolean b = false;
						double qty = 0.0;
						
						try {
							
							try {
								qty = new Double(guard.replaceAll(",", ""));
							} catch (Exception e) {
								System.out.println("Error. load_items_values() qty> "+ e.getMessage());
							}
							
						} catch (Exception e) {
							System.out.println("Error. load_items_values() calculo> "+ e.getMessage());
						}

						results[i][2]=guard;
					}


				
				}

				}
			
		return results;
		}
		
		
	
	
	private CustomTable crearTablaDeLineas() {
		CustomTable Table = new CustomTable();
		Column col = new Column();
		col = new Column();
		col.setName("");
		col.setWidth(34);
		col.setEditable(true);
		CheckBoxCellEditor chkce = new CheckBoxCellEditor();
		chkce.setTipo(Boolean.class);
		chkce.setName(_Interface._chk_linea);
		chkce.setItemListener(this.getConstructor().getItemListener());
		col.setCellEditor(chkce.getCellCheck());
		col.setClass(Boolean.class);
		Table.addColumn(col);

		col = new Column();
		col.setName("Id");
		col.setWidth(60);
		col.setCellRenderer(new TableLineaColorCellRenderer());
		col.setEditable(false);
		Table.addColumn(col);
		
		col = new Column();
		col.setName("Linea");
		col.setWidth(200);
		col.setCellRenderer(new TableLineaColorCellRenderer());
		col.setEditable(false);
		Table.addColumn(col);


		col = new Column();
		col.setName("ABC");
		col.setAligment(JTextField.RIGHT);
		col.setWidth(120);
		col.setCellRenderer(new TableLineaColorCellRenderer());
		col.setEditable(false);
		Table.addColumn(col);

		col = new Column();
		col.setName("Valor");
		col.setAligment(JTextField.RIGHT);
		col.setWidth(120);
		col.setCellRenderer(new TableLineaColorCellRenderer());
		col.setEditable(false);
		col.setClass(Double.class);
		Table.addColumn(col);

		col = new Column();
		col.setName("Items");
		col.setAligment(JTextField.RIGHT);
		col.setWidth(120);
		col.setCellRenderer(new TableLineaColorCellRenderer());
		col.setEditable(false);
		Table.addColumn(col);

		col = new Column();
		col.setName("Total");
		col.setAligment(JTextField.RIGHT);
		col.setWidth(120);
		col.setCellRenderer(new TableLineaColorCellRenderer());
		col.setEditable(false);
		Table.addColumn(col);
		
		Table.setFont(new Font("Dialog", Font.PLAIN, 10));
		return Table;
	}

	private void crearTablaImportarLineas(Object[][] results) {
		CustomTable Table = new CustomTable();
		Column col = new Column();
		col = new Column();
		col.setName("");
		col.setWidth(34);
		col.setEditable(true);
		col.setClass(Boolean.class);
		Table.addColumn(col);
		
		col = new Column();
		col.setName("Id");
		col.setWidth(60);
		col.setCellRenderer(new TableLineaColorCellRenderer());
		col.setEditable(false);
		Table.addColumn(col);
		
		col = new Column();
		col.setName("Linea");
		col.setWidth(200);
		col.setCellRenderer(new TableLineaColorCellRenderer());
		col.setEditable(false);
		Table.addColumn(col);

		col = new Column();
		col.setName("ABC");
		col.setWidth(50);
		col.setCellRenderer(new TableLineaColorCellRenderer());
		col.setEditable(false);
		col.setClass(String.class);
		Table.addColumn(col);

		col = new Column();
		col.setName("Valor");
		col.setWidth(100);
		col.setClass(String.class);
		col.setCellRenderer(new TableLineaColorCellRenderer());
		col.setEditable(false);
		col.setClass(Integer.class);
		Table.addColumn(col);

		
		Table.setFont(new Font("Dialog", Font.PLAIN, 10));
		Table.setData(results);
		
		Table.build();
		Table.fillData();
		lineas.setJTable(Table.getTable());
		
		
	}

	public void seleccionar_lineas(boolean b){
		String idpedido=frame.get_txt_idpedido().getText();
		if (frame.getJTable()!=null){
			for (int i=0;i<frame.getJTable().getRowCount();i++){
				String linea=frame.getJTable().getValueAt(i, 1).toString();
				String items=frame.getJTable().getValueAt(i, 3).toString();
				double qty=0.0;
				try {
					qty=new Double(items.replace(",", ""));
				}catch(Exception e){
					
				}
				
				if (b){
					if (qty>0){
						frame.getJTable().setValueAt(b, i, 0);
						data.setSeleccionada(idpedido, linea, b);
					}
						
				}else {
					frame.getJTable().setValueAt(b, i, 0);
					data.setSeleccionada(idpedido, linea, b);
				}
				
			}	
		}
		
	}
	
	

	

	private CustomTable crearTablaDeItems(boolean editable) {
		//"","idArticulo","Descripcion","Costo","Minimo","Stock","Ventas","Pedir"
		// ,"Total"
		CustomTable Table = new CustomTable();
		// ColorTableCellRenderer colour=new ColorTableCellRenderer();
		Column col = new Column();
		
		col = new Column();
		col.setName("");
		col.setWidth(34);
		col.setEditable(true);
		CheckBoxCellEditor chkce = new CheckBoxCellEditor();
		chkce.setName(_Interface._chk_item);
		chkce.setItemListener(this.getConstructor().getItemListener());
		chkce.setTipo(Double.class);
		col.setCellEditor(chkce.getCellCheck());
		col.setClass(Boolean.class);
		Table.addColumn(col);

		col = new Column();
		col.setName("idArticulo");
		col.setWidth(120);
		col.setEditable(editable);
		col.setCellRenderer(new TableItemColorCellRenderer());
		CellEditor pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.blue);
		pce.setName(_Interface._table_idarticulo);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());
		Table.addColumn(col);

		col = new Column();
		col.setName("Descripcion");
		col.setWidth(220);
		col.setEditable(editable);
		col.setCellRenderer(new TableItemColorCellRenderer());
		Table.addColumn(col);
		
		col = new Column();
		col.setName("ABC");
		col.setWidth(50);
		col.setEditable(false);
		col.setClass(String.class);
		col.setCellRenderer(new TableItemColorCellRenderer());
		Table.addColumn(col);
		
		col = new Column();
		col.setName("ABC.U");
		col.setWidth(60);
		col.setEditable(false);
		col.setClass(Integer.class);
		col.setCellRenderer(new TableItemColorCellRenderer());
		Table.addColumn(col);
		
		col = new Column();
		col.setName("Costo");
		col.setWidth(56);
		col.setAligment(JTextField.RIGHT);
		col.setEditable(editable);
		col.setCellRenderer(new TableItemColorCellRenderer());
		col.setClass(Double.class);
		Table.addColumn(col);

		col = new Column();
		col.setName("Critico");
		col.setWidth(56);
		col.setAligment(JTextField.RIGHT);
		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.blue);
		pce.setName(_Interface._table_items_critico);
		pce.setTipo(Double.class);
		col.setCellEditor(pce.getCellEditor());
		col.setEditable(true);
		col.setCellRenderer(new TableItemColorCellRenderer());
		col.setClass(Double.class);
		Table.addColumn(col);
		
		col = new Column();
		col.setName("Proyectado");
		col.setWidth(76);
		col.setAligment(JTextField.RIGHT);
		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.blue);
		pce.setName(_Interface._table_items_minimo);
		pce.setTipo(Double.class);
		col.setCellEditor(pce.getCellEditor());
		col.setEditable(true);
		col.setCellRenderer(new TableItemColorCellRenderer());
		col.setClass(Double.class);
		Table.addColumn(col);
		
		
		col = new Column();
		col.setName("Stock");
		col.setWidth(58);
		col.setAligment(JTextField.RIGHT);
		col.setEditable(false);
		col.setCellRenderer(new TableItemColorCellRenderer());
		col.setClass(Double.class);
		Table.addColumn(col);

		col = new Column();
		col.setName("Ventas");
		col.setWidth(58);
		col.setAligment(JTextField.RIGHT);
		col.setEditable(false);
		col.setCellRenderer(new TableItemColorCellRenderer());
		col.setClass(Double.class);
		Table.addColumn(col);

		col = new Column();
		col.setName("Pedir");
		col.setAligment(JTextField.RIGHT);
		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.blue);
		pce.setName(_Interface._table_items_pedir);
		pce.setTipo(Double.class);
		col.setCellEditor(pce.getCellEditor());
		col.setWidth(60);
		col.setEditable(true);
		col.setCellRenderer(new TableItemColorCellRenderer());
		col.setClass(Double.class);
		Table.addColumn(col);

		col = new Column();
		col.setName("Total");
		col.setAligment(JTextField.RIGHT);
		col.setWidth(70);
		col.setEditable(false);
		col.setCellRenderer(new TableItemColorCellRenderer());
		col.setClass(Double.class);
		Table.addColumn(col);
		
		col = new Column();
		col.setName("UV");
		col.setAligment(JTextField.RIGHT);
		col.setWidth(50);
		col.setEditable(false);
		col.setCellRenderer(new TableItemColorCellRenderer());
		col.setClass(Double.class);
		Table.addColumn(col);
		
		col = new Column();
		col.setName("UC");
		col.setAligment(JTextField.RIGHT);
		col.setWidth(50);
		col.setEditable(false);
		col.setCellRenderer(new TableItemColorCellRenderer());
		col.setClass(Double.class);
		Table.addColumn(col);
		
		Table.setName(_Interface._table_items);
		Table.setFont(new Font("Dialog", Font.BOLD, 10));
		Table.addMouseListener(this.getConstructor().getMouseListener());
		Table.addKeyListener(this.getConstructor().getKeyListener());
		
		return Table;
	}

	aplicacion.inventario.transporte.constructor._Constructor transporte = null;

	public void editarTransporte() {
		String idtransporte = frame.get_txt_idtransporte().getText();
		if (transporte != null) {
			transporte.dispose();
			transporte = null;
		}
		transporte = new aplicacion.inventario.transporte.constructor._Constructor();
		transporte.setParameter(_parametros.connector, this._data
				.getConnectionHandler().Clone());
		transporte.setParameter(_parametros.LookAndFeel, this.getConstructor()
				.getLookAndFeelTheme());
		transporte
				.setParameter(
						aplicacion.inventario.transporte.interfaces._parametros.idtransporte,
						idtransporte);
		transporte.build(this.getConstructor());
		transporte.init();
		this.getConstructor().addChild(transporte);

	}
	
	private void editCell(JTable table, int row, int col) {
		table.changeSelection(row, col, false, false);
		table.editCellAt(row, col);
		table.transferFocus();
		System.out.println("transfer focus");
	}

	public void editSelectedCell(JTable table) {
		int col = table.getEditingColumn();
		int row = table.getEditingRow();
		//table.getCellEditor().cancelCellEditing();
		table.changeSelection(row, col, false, false);
		table.editCellAt(row, col);
		table.transferFocus();
	}
	
	
	
	
	public void addCode(String code,int row){
		try {
		frame.getJTable1().getCellEditor().cancelCellEditing();
			}
			catch(Exception e){
				
			}
			frame.getJTable1().setValueAt(false, row, 0);
		
		frame.getJTable1().setValueAt(code, row, 1);
		
		
		this.complete_row(row);
		//this.jTable.setValueAt(precio, row, 4);
	}
	
	
	
	
	
public void complete_row(int r){
		
		String code=frame.getJTable1().getValueAt(r, 1).toString();
		String idpedido=frame.get_txt_idpedido().getText();
		//jTable.setValueAt(code, r, 2);
		String desde=frame.get_txt_desde().getText();
		String hasta=frame.get_txt_hasta().getText();
		String q=data.getArticuloQuery(idpedido,code,desde,hasta);
		//     
		
		String desc="";
		String costo="";
		
		String stock="";
		String minimo="";
		String ventas="";
		System.out.println("Completar Fila "+r+" codigo "+code+" "+q);
		Object[][] results=data.getResults(q);
		double co=0.0;
		double stk=0.0;
		double min=0.0;
		double qty=0.0;
		
		
		if (results!=null){
			if (results.length>0){
				desc=(String)results[0][1];
				costo=(String)results[0][2];
				minimo=(String)results[0][3];
				stock=(String)results[0][4];
				ventas=(String)results[0][5];
				
				
				try {
					co=new Double(costo.replace(",", ""));
				}catch(Exception e){
					
				}
				try {
					stk=new Double(stock.replace(",", ""));
				}catch(Exception e){
					
				}
				try {
					min=new Double(minimo.replace(",", ""));
				}catch(Exception e){
					
				}
				
				try {
					qty=new Double(ventas.replace(",", ""));
				}catch(Exception e){
					
				}
				
			}
		}
		frame.getJTable1().setValueAt(false, r, 0);
		frame.getJTable1().setValueAt(desc, r, 2);
		frame.getJTable1().setValueAt(1.0, r, 7);
		frame.getJTable1().setValueAt(co, r, 3);
		frame.getJTable1().setValueAt(min, r, 4);
		frame.getJTable1().setValueAt(stk, r, 5);
		frame.getJTable1().setValueAt(qty, r, 6);
		frame.getJTable1().setValueAt(1.0, r, 8);
		
	}

	public void add(String code){
		int e=existcode(code);
		if (e<0){
			if (frame.getJTable1()!=null){
				int r=this.getEmptyRow();
				if (r<0){
						this.newSEmptyRow();
						r=frame.getJTable1().getRowCount()-1;
						this.addCode(code, r);
					
				}else {
					this.addCode(code, r);
				}
			}else {
			System.out.println("Ya existe el codigo "+code);
			}
		}
	}
	
	public void addCodes(String[] codes){
		System.out.println("Sistema De importacion Activo");
		if (codes!=null){
			System.out.println("Codigos:");
			for (int i=0;i<codes.length;i++){
				add(codes[i]);
			}
	
		}
	}
	
	public int existcode(String code){
		boolean b=false;
		int i=0;
		int s=-1;
		if (frame.getJTable1()!=null){
			while (i<frame.getJTable1().getRowCount() & !b){
				String codex="";
				try {
					codex=frame.getJTable1().getValueAt(i, 1).toString();
				}catch(Exception e){
					
				}
				b=code.compareTo(codex)==0;
				
				if (!b){
					i++;
				}else {
					s=i;
				}
			}	
		}
		return s;
	}
	
	private aplicacion.herramientas.java.evaluadores.Provincia Provincia;

	public void initialize_Provincia() {
		Provincia = new aplicacion.herramientas.java.evaluadores.Provincia() {
			public void cargar(String codigo) {
				Object[][] results = this.getInfo(codigo);
				String descripcion = (String) results[0][1];
				frame.get_txt_provincia_descripcion().setText(descripcion);
				frame.get_txt_idciudad().requestFocusInWindow();
			}
		};
		Provincia.setConstructor(this.getConstructor());
	}
	
	public void reconnect_Provincia() {
		if (Provincia != null) {
			Provincia.setConstructor(this.getConstructor());
		}

	}

	public void BuscarProvincia(JTextField tx) {
		Provincia.Buscar(tx);
	}

	public void BuscarProvincia() {
		Provincia.Buscar(frame.get_txt_idprovincia());
	}

	public void buscarProvincia(JTextField tx) {
		Provincia.buscar(tx);
	}

	public void evaluarProvincia(JTextField tx) {
		cambios = true;
		Provincia.evaluate(tx);
	}
	public void evaluate_domicilio(JTextField tx) {
		cambios = true;
		String domicilio = tx.getText();
		if (domicilio.compareTo("") != 0) {
			frame.get_txt_cpostal().requestFocusInWindow();
		} else {
			error("Ingrese una direccion");
			tx.requestFocusInWindow();
		}
	}

	public void evaluate_ciudad(JTextField tx) {
		cambios = true;
		String ciudad = tx.getText();
		if (ciudad.compareTo("") != 0) {
			frame.get_txt_domicilio().requestFocusInWindow();
		} else {
			error("Ingrese una ciudad");
			tx.requestFocusInWindow();
		}
	}

	public void evaluate_cpostal(JTextField tx) {
		cambios = true;
		String cpostal = tx.getText();
		if (cpostal.compareTo("") != 0) {
			frame.get_txt_tel().requestFocusInWindow();
		} else {
			error("Ingrese un codigo postal");
			tx.requestFocusInWindow();
		}
	}

	public void evaluate_telefono(JTextField tx) {
		cambios = true;
		String telefono = tx.getText();
		if (telefono.compareTo("") != 0) {
			frame.get_txt_guia().requestFocusInWindow();
		} else {
			error("Ingrese un numero de telefono");
			tx.requestFocusInWindow();
		}
	}

	public void evaluarGuia(JTextField tx) {
		cambios = true;
		String guia = tx.getText();
		frame.get_txt_idvendedor().requestFocusInWindow();
	}
	private void cargarProveedor(String idcliente) {
		Object[][] results = this.data.getProveedor(idcliente);
		if (results != null) {
			if (results.length > 0) {
				frame.get_txt_descripcion().setEditable(true);
				// frame.get_txt_idcliente().setEditable(false);
				String descripcion = results[0][1].toString();
				String telefono = (String) results[0][2];
				String fax = (String) results[0][3];
				String email = (String) results[0][4];
				String localidad = (String) results[0][6];
				String calle = (String) results[0][12];
				String numero = (String) results[0][13];
				String piso = (String) results[0][14];
				String cpostal = (String) results[0][15];
				String idprovincia = (String) results[0][16];
				String domicilio = calle + " " + numero + " " + piso;
				String idtransporte = (String) results[0][21];
				String transporte_nombre = (String) results[0][22];
				frame.get_txt_descripcion().setText(descripcion);
				frame.get_txt_cpostal().setText(cpostal);
				frame.get_txt_domicilio().setText(domicilio);
				frame.get_txt_tel().setText(telefono);
				frame.get_txt_fax().setText(fax);

				frame.get_txt_idciudad().setText(localidad);
				frame.get_txt_idprovincia().setText(idprovincia);
				frame.get_txt_idtransporte().setText(idtransporte);
				frame.get_txt_transporte_descripcion().setText(
						transporte_nombre);
				frame.get_txt_email().setText(email);
				if (idprovincia.compareTo("") != 0) {
					if (Provincia.existe(idprovincia)) {
						this.evaluarProvincia(frame.get_txt_idprovincia());
					}
				}

				frame.get_txt_descripcion().requestFocusInWindow();
				/*
				 * if (idcliente.compareTo("")==0){
				 * 
				 * 
				 * frame.get_txt_cliente_descripcion().setSelectionStart(0);
				 * frame
				 * .get_txt_cliente_descripcion().setSelectionEnd(descripcion
				 * .length()); }
				 */

				if (frame.getJTable() != null) {
					if (frame.getJTable().getRowCount() > 0) {
						if (this._cliente.compareTo("") != 0) {
							

						}

					}
				}
				_cliente = idcliente;
			}
		}
	}

	public void crear_valores_pedidos(){
		String codigo=frame.get_txt_idproveedor().getText();
		if (confirmar("Confirme la eleccion del proveedor "+codigo+" ",2)){
			frame.get_txt_idproveedor().setEditable(false);
			cargar_lineas_proveedor(codigo);	
		}
	}

	public void add(String code[]){
		
		int e=existcode(code[0]);
		if (e<0){
			if (frame.getJTable1()!=null){
				int r=this.getEmptyRow();
				if (r<0){
						this.newSEmptyRow();
						r=frame.getJTable1().getRowCount()-1;
						this.addCode(code[0],code[2],code[1], r);
				}else {
					this.addCode(code[0],code[2],code[1], r);
				}
		}else {
			System.out.println("Ya existe el codigo "+code);
		}
		}
	}
	
	public void newSEmptyRow(){
		DefaultTableModel model=(DefaultTableModel) frame.getJTable1().getModel();	
		model.setRowCount(model.getRowCount()+1);
	}
	
	private aplicacion.herramientas.java.evaluadores.Proveedor proveedor=null;
	public void initialize_proveedor(){
		proveedor=new aplicacion.herramientas.java.evaluadores.Proveedor(){
			public void cargar(String codigo){
				
				cargarProveedor(codigo);
				
			}
		};
		
		proveedor.setConstructor(this.getConstructor());
	}
	public void BuscarProveedor(JTextField tx){
		proveedor.Buscar(tx);
	}
	public void BuscarProveedor(){
		proveedor.Buscar(frame.get_txt_idproveedor());
	}
	public void evaluarProveedor(JTextField tx){
		proveedor.evaluate(tx);
	}
	
	public void cargar_lineas_proveedor(String cuenta){
		
		if (nuevo){
			this.goNuevo();
			
			
		}else{
			this.goCargar();
		}
	}
	
	public int getEmptyRow(){
		int e=-1;
		int i=0;
		while (e<0 & i<frame.getJTable1().getRowCount()){
			String empty="";
			try {
				empty=frame.getJTable1().getValueAt(i,2).toString();
			}catch(Exception ex){
				
			}
			if (empty.compareTo("")==0){
				e=i;
			}
			i++;
		}
		return e;
	}
	
	public void complete_row(int r,String code,String ap,String clase){
	//jTable.setValueAt(code, r, 2);
	String q=data.getArticulo(code);
	
	String desc="";
	String costo="";
	String precio="";
	String linea="";
	String prov="";
	String pol="";
	String precio5="";
	String clas="";
	String qty="";
	System.out.println("Completar Fila "+r+" codigo "+code);
	Object[][] results=data.getResults(q);
	if (results!=null){
		if (results.length>0){
			desc=(String)results[0][1];
			linea=(String)results[0][2];
			prov=(String)results[0][3];
			pol=(String)results[0][4];
			precio5=(String)results[0][5];
			costo=(String)results[0][6];
			precio=(String)results[0][7];
			qty=(String)results[0][8];
			clas=(String)results[0][9];
			double co=0.0;
			double p2=0.0;
			double min=0.0;
			double stk=0.0;
			try {
				co=new Double(costo.replace(",", ""));
			}catch(Exception e){
				
			}
			try {
				p2=new Double(precio.replace(",", ""));
			}catch(Exception e){
				
			}
			costo=new Convertidor().getMoney(co,2);
			precio=new Convertidor().getMoney(p2,2);
			
		}
	}
	frame.getJTable1().setValueAt(desc, r, 2);
	//this.jTable.setValueAt(linea, r, 2);
	//this.jTable.setValueAt(prov, r, 3);
	//this.jTable.setValueAt(pol, r, 4);
	frame.getJTable1().setValueAt(0.0, r, 6);
	frame.getJTable1().setValueAt(0.0, r,5);
	frame.getJTable1().setValueAt(0.0, r, 7);
	frame.getJTable1().setValueAt(0.0, r, 8);
	frame.getJTable1().setValueAt(0.0, r, 4);
	frame.getJTable1().setValueAt(0.0, r, 3);
	
	
	
	
}
public void addCode(String code,String ap,String clas,int row){
	try {
		frame.getJTable1().getCellEditor().cancelCellEditing();
		}
		catch(Exception e){
			
		}
	frame.getJTable1().setValueAt(false, row, 0);
	
	frame.getJTable1().setValueAt(code, row, 1);
	
	
	//this.complete_row(row);
	this.complete_row(row, code, ap, clas);
	//this.jTable.setValueAt(precio, row, 4);
}

	
	public void recalculate_vars(){
		this.recalculate_vars(-1, false);
	}
		
	public void recalculate_vars(int row,boolean selected) {
		double sum_items = 0.0;
		double sum_linea = 0.0;
		JTable table = frame.getJTable1();
		String linea = "";
		try {
			linea = frame.get_txt_linea().getText();
		} catch (Exception e) {

		}
			if (table!=null){
				for (int i = 0; i < table.getRowCount(); i++) {
					double pedir = 0.0;
					double costo = 0.0;
					boolean b = (Boolean) table.getValueAt(i, 0);
					String _pedir="0.0";
					String _costo="0.0";
					_pedir=table.getValueAt(i, 10).toString();
					_costo=table.getValueAt(i, 5).toString();
					try {
						pedir = new Double(_pedir);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					try {
						costo = new Double(_costo);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (i==row){
						b=selected;
					}
					if (b) {
						sum_items = sum_items + pedir;
						sum_linea = sum_linea + pedir * costo;
					}
				}	
			}
			
			Convertidor C = new Convertidor();
			boolean b = false;
			if (sum_linea > 0 | sum_items > 0) {
				b = true;
			}
			this.setLineValues(linea, sum_items, sum_linea, b);
			frame.get_txt_items().setText(C.getMoney(sum_items, 2));
			frame.get_txt_total().setText(C.getMoney(sum_linea, 2));
			recalculate_total_vars(-1,false);	
		
		
	}

	public void recalculate_total_vars(int row,boolean selected) {
		double sum_items = 0.0;
		double sum_linea = 0.0;
		JTable table = frame.getJTable();

		for (int i = 0; i < table.getRowCount(); i++) {
			double items = 0.0;
			double costo = 0.0;
			boolean b = (Boolean) table.getValueAt(i, 0);
			if (i==row){
				b=selected;
			}
			Object _items =table.getValueAt(i, 4);
			Object _costo =table.getValueAt(i, 5);
			if (_items instanceof Double){
				items = (Double) _items;	
			}else{
				items=new Double(_items.toString());
			}
			if (_costo instanceof Double){
				costo = (Double) _costo;	
			} else{
				costo=new Double(_costo.toString());
			}
			
			if (b) {
				sum_items = sum_items + items;
				sum_linea = sum_linea + costo;
			}
		}
		Convertidor C = new Convertidor();
		

		frame.get_txt_total_unidades().setText(
				C.getMoney(sum_items, 2));
		frame.get_txt_total_compra().setText(
				C.getMoney(sum_linea, 2));
	}

	public void process_pedir(JTextField textfield) {
		System.out.println("Process Pedirrr");
		Container component = textfield.getParent();
		textfield.requestFocusInWindow();
		if (component instanceof JTable) {
			JTable table = frame.getJTable1();
			int col = table.getEditingColumn();
			int row = table.getEditingRow();
			String ped = textfield.getText();
			String linea=frame.get_txt_linea().getText();
				
			double tot = 0.0;
			double pedir = 0.0;
			double cost = (Double) table.getValueAt(row, 5);
			double min = (Double) table.getValueAt(row, 7);
			boolean b = (Boolean) table.getValueAt(row, 0);
			
			boolean error = false;
			String art = table.getValueAt(row, 1).toString();
			
			String descripcion = (String) table.getValueAt(row, 2);
			try {
				pedir = new Double(ped.replace(",", ""));
			} catch (Exception e) {
				aviso("El valor "+ ped + " es incorrecto!");
				error = true;
				this.editCell(table, row, col);
			}
			if (!error) {
				if (pedir >= 0) {
					try {
						tot = cost * pedir;
					} catch (Exception e) {

					}

					table.setValueAt(tot, row, 11);
					textfield.setText("" + new Convertidor().getMoney(pedir, 2));
					table.setValueAt(pedir, row, 10);
					this.update_pedido(art, descripcion, cost, pedir, b,linea);
					if (linea.compareTo("ARTICULOS VARIOS")==0){
						if (table.getRowCount()-1==row){
							this.newSEmptyRow();
							table.setValueAt(false, row, 0);
							this.evaluate_seleccion_item(row, false);
							
							this.editCell(table, row + 1, 1);
						}else {
							if (table.getValueAt(row+1, 1)!=null){
								if (pedir > 0) {
									table.setValueAt(true, row, 0);
									this.evaluate_seleccion_item(row, true);
								} else {
									table.setValueAt(false, row, 0);
									this.evaluate_seleccion_item(row, false);
								}
								this.editCell(table, row + 1, col);
							}else {
								table.setValueAt(false, row+1, 0);
								this.evaluate_seleccion_item(row+1, false);
								this.editCell(table, row + 1, 1);
							}
								
						}
					}else {
						if (row<table.getRowCount()-1){
							if (table.getValueAt(row+1, 1)!=null){
								if (pedir > 0) {
									table.setValueAt(true, row, 0);
									this.evaluate_seleccion_item(row, true);
								} else {
									table.setValueAt(false, row, 0);
									this.evaluate_seleccion_item(row, false);
								}
								this.editCell(table, row + 1, col);
							}else {
								table.setValueAt(false, row+1, 0);
								this.editCell(table, row + 1, 1);
							}	
							
						}else{
							if (pedir > 0) {
								table.setValueAt(true, row, 0);
								this.evaluate_seleccion_item(row, true);
							} else {
								table.setValueAt(false, row, 0);
								this.evaluate_seleccion_item(row, false);
							}
						}
						this.editCell(table, row+1, col);
					}
				} else {
					aviso("El valor "+ ped + " es incorrecto!");
					this.editCell(table, row, col);
				}
				recalculate_vars();
			}
		}
	}

	
	public void process_codigo(JTextField textfield) {
		Container component = textfield.getParent();
		String idpedido=frame.get_txt_idpedido().getText();
		if (component instanceof JTable) {
			JTable table = (JTable) component;
			int col = table.getEditingColumn();
			int row = table.getEditingRow();
			String ped = "";
			String code=textfield.getText();
			String desde=frame.get_txt_desde().getText();
			String hasta=frame.get_txt_hasta().getText();
			String q=data.getArticuloQuery(idpedido,code,desde,hasta);
			if (code.startsWith("*")){
				error("Codigo "+code+" invalido. Opcion aun no habilitada");
				
				this.editCell(table, row , col);
			}else {
				
				Object[][] results=data.getResults(q);
				if (results.length>0){
					if (this.existcode(code)<0){
						
						table.setValueAt(code, row, col);
						complete_row(row);
						this.editCell(table, row , 7);	
					}else {
						if (this.existcode(code)!=row){
							aviso("Codigo ingresado en fila "+row+" ");
							this.editCell(table, row , col);
						}else {
							this.editCell(table, row , 7);		
						}
					}
					
				}else {
					aviso("Codigo "+code+" invalido");
					this.editCell(table, row , col);
				}
			}
			
	
		}
	}
	public int getDays(){
		String desde=frame.get_txt_desde().getText();
		String hasta=frame.get_txt_hasta().getText();
		int dif=new Convertidor().getDateDiff(desde, hasta);
		return dif;
	}
	
	public void calculate_proyectado(boolean par){
		String _dias=frame.get_txt_proyectado().getText();
		
		int dias=new Integer(_dias);
		this.calculate_proyectado(dias,par);
	}
	
	public void calculate_critico(){
		String _dias=frame.get_txt_critico().getText();
		int dias=new Integer(_dias);
		this.calculate_critico(dias);
	}
	
	public void calculate_proyectado(int dias,boolean par){
		this.lenght=frame.getJTable1().getRowCount();
		for (int i=0;i<frame.getJTable1().getRowCount();i++){
			double ventas=0.0;
			
			current=i;
			try {
				String idarticulo=(String)frame.getJTable1().getValueAt(i, 1).toString();
				estado="proyectado "+idarticulo;
//				String _ventas=(String)frame.getJTable1().getValueAt(i, 8).toString();
//				_ventas=_ventas.replaceAll(",", "");
//				ventas=new Double(_ventas);
//				int dif=this.getDays();
				
				String _ventas=(String)frame.getJTable1().getValueAt(i, 4).toString();
				_ventas=_ventas.replaceAll(",", "");
				ventas=new Double(_ventas);
				int dif=360;
				System.out.println("dias?"+dif);
				
				double promedio=ventas/new Double(dif);
				double proyeccion=promedio*new Double(dias);
				proyeccion=Math.round(proyeccion);
				if (proyeccion>0){
					Integer parx=new Double(proyeccion).intValue();
					boolean espar=new Double(parx)%2==0;
					if (!espar & par){
						if (Math.abs(proyeccion-(parx+1))<=Math.abs(proyeccion-(parx-1))){
							proyeccion=parx+1;
						}else{
							proyeccion=parx-1;
						}
						
					}	
				}
				int index=frame.get_lst_categoria_generacion().getSelectedIndex();
				String categoria=frame.getJTable1().getValueAt(i, 3).toString();
				int _index=-1;
				if (categoria.compareTo("A")==0){
					_index=1;
				}
				if (categoria.compareTo("B")==0){
					_index=2;
				}
				if (categoria.compareTo("C")==0){
					_index=3;
				}
				if (categoria.compareTo("D")==0){
					_index=4;
				}
				if (index>=0){
					if (_index<=index){
						
					}else{
						proyeccion=0;
					}	
				}
				System.out.println(categoria+" @index "+index+" @_index"+_index+" proyeccion="+proyeccion);
				frame.getJTable1().setValueAt(proyeccion, i,7);
				data.update_article(idarticulo, proyeccion);
				this._process_proyectado(frame.getJTable1(), proyeccion, i);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		this.showLayer(true);
		this.block(true);
		done=true;
	}
	
	
	public void calculate_critico(int dias){
		this.lenght=frame.getJTable1().getRowCount();
		for (int i=0;i<frame.getJTable1().getRowCount();i++){
			double ventas=0.0;
			current=i;
			try {
				String idarticulo=(String)frame.getJTable1().getValueAt(i, 1).toString();
				estado="critico "+idarticulo;
				String _ventas=(String)frame.getJTable1().getValueAt(i, 8).toString();
				_ventas=_ventas.replaceAll(",", "");
				ventas=new Double(_ventas);
				int dif=this.getDays();
				System.out.println("dias?"+dif);
				
				double promedio=ventas/new Double(dif);
				double proyeccion=promedio*new Double(dias);
				proyeccion=Math.round(proyeccion);
				frame.getJTable1().setValueAt(proyeccion, i,5);
				data.update_article_critico(idarticulo, proyeccion);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		this.showLayer(true);
		this.block(true);
		done=true;
	}
	
	/**
	 * @deprecated Use {@link #process_proyectado(JTextField)} instead
	 */
	public void process_minimo(JTextField textfield) {
		process_proyectado(textfield);
	}

	public void _process_proyectado(JTable table,double q,int row){
		String art = (String) table.getValueAt(row, 1);
		double stk = 0.0;
		double price = 0.0;
		double pedir = 0.0;
		try {
			price = (Double) table.getValueAt(row, 5);
		} catch (Exception e) {

		}
		try {
			stk = (Double) table.getValueAt(row,8);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		pedir = q - stk;
		if ((pedir) >= 0) {
			pedir = new Convertidor().roundDouble(pedir, 2);
		} else {
			pedir = new Convertidor().roundDouble(0.0, 2);
		}
		table.setValueAt(pedir, row, 10);
		String descripcion = (String) table.getValueAt(row, 2);
		String linea=frame.get_txt_linea().getText();
		Boolean b = (Boolean) table.getValueAt(row, 0);
		update_pedido(art, descripcion, price, pedir, b,linea);
		if (q >= 0) {
			if (q - stk > 0) {
				b = true;
				double cost_ = (q - stk) * price;
				cost_ = new Convertidor().roundDouble(cost_, 2);
				table.setValueAt((cost_), row, 11);

			} else {
				b = false;
				table.setValueAt(0.00, row, 11);
			}
			

		}
	}
	
	public void process_proyectado(JTextField textfield) {
		Container component = textfield.getParent();
		if (component instanceof JTable) {
			boolean error = false;
			String linea=frame.get_txt_linea().getText();
			JTable table = (JTable) component;
			int col = table.getEditingColumn();
			int row = table.getEditingRow();
			String qty = textfield.getText();
			String art = (String) table.getValueAt(row, 1);
			String descripcion = (String) table.getValueAt(row, 2);
			double q = 0.0;
			double stk = 0.0;
			double price = 0.0;
			double pedir = 0.0;
			Boolean b = (Boolean) table.getValueAt(row, 0);

			try {
				q = new Double(qty);
			} catch (Exception e) {
				aviso("El valor "+ qty + " es incorrecto!");
				this.editCell(table, row, col);
				error = true;
			}
			if (!error) {
				if (q < 0) {
					aviso("El valor "+ qty + " es incorrecto!");
					this.editCell(table, row, col);
				} else {
					try {
						price = (Double) table.getValueAt(row, 5);
					} catch (Exception e) {

					}
					try {
						stk = (Double) table.getValueAt(row,8);
					} catch (Exception e) {
						e.printStackTrace();
					}
					if (q <= 0) {
						q = 0;
						q = new Convertidor().roundDouble(q, 2);
						table.setValueAt(q, row, col);
					}
					pedir = q - stk;
					if ((pedir) >= 0) {
						pedir = new Convertidor().roundDouble(pedir, 2);
						table.setValueAt(pedir, row, 10);
					} else {
						table.setValueAt(0.00, row, 10);
					}
					textfield.setText("" + new Convertidor().getMoney(q, 2));
					table.setValueAt(b, row, 0);
					data.update_article(art, q);
					update_pedido(art, descripcion, price, q - stk, b,linea);
					if (q >= 0) {
						if (q - stk > 0) {
							b = true;
							double cost_ = (q - stk) * price;
							cost_ = new Convertidor().roundDouble(cost_, 2);
							table.setValueAt((cost_), row, 11);

						} else {
							b = false;
							table.setValueAt(0.00, row, 11);
						}
						this.editCell(table, row + 1, col);

					}
					table.setValueAt(b, row, 0);
					recalculate_vars();
				}

			}

		}
	}

	
	public void process_critico(JTextField textfield) {
		Container component = textfield.getParent();
		if (component instanceof JTable) {
			boolean error = false;
			String linea=frame.get_txt_linea().getText();
			JTable table = (JTable) component;
			int col = table.getEditingColumn();
			int row = table.getEditingRow();
			String qty = textfield.getText();
			String art = (String) table.getValueAt(row, 1);
			String descripcion = (String) table.getValueAt(row, 2);
			double q = 0.0;
			double stk = 0.0;
			double price = 0.0;
			double pedir = 0.0;
			Boolean b = (Boolean) table.getValueAt(row, 0);

			try {
				q = new Double(qty);
			} catch (Exception e) {
				aviso("El valor "+ qty + " es incorrecto!");
				this.editCell(table, row, col);
				error = true;
			}
			if (!error) {
				if (q < 0) {
					aviso("El valor "+ qty + " es incorrecto!");
					this.editCell(table, row, col);
				} else {
					try {
						price = (Double) table.getValueAt(row, 4);
					} catch (Exception e) {

					}
					try {
						stk = (Double) table.getValueAt(row, 6);
					} catch (Exception e) {
						e.printStackTrace();
					}
					if (q <= 0) {
						q = 0;
						q = new Convertidor().roundDouble(q, 2);
						table.setValueAt(q, row, col);
					}
					
					textfield.setText("" + new Convertidor().getMoney(q, 2));
					data.update_article_critico(art, q);
					this.editCell(table, row + 1, col);
					}
					
			}

		}
	}
	private Object[][] proccess_lineas(Object[][] results) {
		Object[][] tmp = null;
		if (results != null) {
			if (results.length > 0) {
				tmp = new Object[results.length][results[0].length];
				for (int i = 0; i < results.length; i++) {
					double items = 0.0;
					double monto = 0.0;
					double rotacion=0.0;
					String _seleccion = results[i][0].toString();
					String idrubro = results[i][1].toString();
					String lineas = results[i][2].toString();
					String categoria = results[i][3].toString();
					String _rotacion = results[i][4].toString();
					String _items = results[i][5].toString();
					String _monto = results[i][6].toString();
					
					try {
						items = new Double(_items);
						items = new Convertidor().roundDouble(items, 2);
					} catch (Exception e) {

					}
					try {
						monto = new Double(_monto);
						monto = new Convertidor().roundDouble(monto, 2);
					} catch (Exception e) {

					}
					try {
						rotacion = new Double(_rotacion);
						rotacion = new Convertidor().roundDouble(rotacion, 2);
					} catch (Exception e) {

					}
					tmp[i][0] = _seleccion.compareTo("1")==0;
					tmp[i][1] = idrubro;
					tmp[i][2] = lineas;
					tmp[i][3] = categoria;
					tmp[i][4] = rotacion;
					tmp[i][5] = items;
					tmp[i][6] = monto;
					
					
				}

			}
		}
		return tmp;
	}
	
	
	
	
	public void agregar_linea(String linea){
		String idpedido=frame.get_txt_idpedido().getText();
		if (!data.existe_linea(idpedido,linea)){
			this._agregar_linea(linea);
		}else {
			aviso("La linea ya existe en este pedido");
		}		
		
	}
	
	public void _agregar_linea(String linea){
		estado="agregando linea "+linea;
		System.out.println("Agregar linea "+linea);
		String idpedido=frame.get_txt_idpedido().getText();
		System.out.println("Agregar linea "+frame.getJTable());
		
		
		if (frame.getJTable()!=null){
				
			
			System.out.println("Agregar linea "+linea+" en tabla existente");
			boolean found=false;
			int i=0;
			try {
				while (i<frame.getJTable().getRowCount() & !found){
					String lineax=frame.getJTable().getValueAt( i,1).toString();
					found=lineax.compareTo(linea)==0;
					i++;
				}	
			}catch(Exception e){
				
			}
			
			
			if (!found){
				Object[][] results=data.getLineStatics(linea);
				int tipo=0;
				tipo=frame.get_lst_modo_lineas().getSelectedIndex();
				String categoria="";
				double valor=0.0;
				if (tipo<=0){
					try {
						categoria=results[0][0].toString();
						valor=new Double(results[0][1].toString());
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (tipo==1){
					try {
						categoria=results[0][2].toString();
						valor=new Double(results[0][3].toString());
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (tipo==2){
					try {
						categoria=results[0][4].toString();
						valor=new Double(results[0][5].toString());
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				DefaultTableModel model=(DefaultTableModel) frame.getJTable().getModel();
				model.setRowCount(model.getRowCount()+1);
				int rows=model.getRowCount()-1;
				System.out.println("agregando linea "+linea+" en tabla existente> rows? "+rows);
				frame.getJTable().setValueAt(true, rows,0);
				frame.getJTable().setValueAt(linea, rows,1);
				String descripcion= data.get_linea_descripcion(linea);
				frame.getJTable().setValueAt(descripcion, rows,2);
				frame.getJTable().setValueAt(categoria, rows,3);
				frame.getJTable().setValueAt(valor, rows,4);
				frame.getJTable().setValueAt("0", rows,5);
				frame.getJTable().setValueAt("0", rows,6);
				//frame.getJTable().repaint();	
			}	
		}else {
			if (!data.existe(idpedido)){
				crear_pedido_nuevo();	
			}
			System.out.println("Agregar linea "+linea+" en tabla nueva");
			
			this.crear_tabla_lineas(linea);
			
			
		}
		
		if (!data.existe_linea(idpedido,linea)){
			this.crear_pedido_minimo(linea);
			
			data.clearBatch();
			String qx=data.getInsertar_linea(idpedido,linea);
			data.addBatch(qx);
			data.executeBatch();
			if (linea.compareTo("ARTICULOS VARIOS")!=0){
				this.crear_pedido_minimo(linea);	
			}
			
			this.goMostrarLinea(linea);
		}else {
			aviso("La linea ya existe en este pedido");
		}
		
			
	}
	
	public void crear_tabla_lineas(String linea){
		final String _linea=linea;
		Runnable _execute=new Runnable(){
			public void run(){
				CustomTable table = crearTablaDeLineas();
				String categoria="";
				int tipo=0;
				double valor=0.0;
				try {
					tipo=frame.get_lst_modo_lineas().getSelectedIndex();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Object[][] results=data.getLineStatics(_linea);
				if (tipo<=0){
					try {
						categoria=results[0][0].toString();
						valor=new Double(results[0][1].toString());
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (tipo==1){
					try {
						categoria=results[0][2].toString();
						valor=new Double(results[0][3].toString());
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (tipo==2){
					try {
						categoria=results[0][4].toString();
						valor=new Double(results[0][5].toString());
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				Object[][] results2=new Object[1][6];
				results2[0][0]=false;
				results2[0][1]=_linea;
				results2[0][2]=categoria;
				results2[0][3]=valor;
				results2[0][4]="0.0";
				results2[0][5]="0.0";
				
				results2 = proccess_lineas(results2);
				table.setData(results2);
				table.setName(_Interface._table_lineas);
				table.addMouseListener(getConstructor().getMouseListener());
				table.build();
				table.fillData();
				frame.setJTable(table.getTable());		
			}
		};
		this.invokeLater(_execute);
	
	}
	public void importar_lineas(){
		this.BuscarLinea();
	}
	
	private aplicacion.herramientas.java.evaluadores.Linea Linea=null;
	public void initialize_Linea(){
		Linea=new aplicacion.herramientas.java.evaluadores.Linea(){
			public void cargar(String codigo){
				agregar_linea(codigo);
				
			}
		};
		Linea.setConstructor(this.getConstructor());
	}
	
	public void BuscarLinea(JTextField tx){
		Linea.Buscar(tx);
	}
	public void BuscarLinea(){
		
		Linea.Buscar(null);
	}
	public void buscarLinea(JTextField tx){
		Linea.buscar(tx);
	}
	
	public void evaluarLinea(JTextField tx){
		Linea.evaluate(tx);
	}
	public void removeLinea(){
		String idpedido=frame.get_txt_idpedido().getText();
		if (frame.getJTable()!=null){
			DefaultTableModel model=(DefaultTableModel) frame.getJTable().getModel();
			String linea=frame.getJTable().getValueAt(frame.getJTable().getSelectedRow(), 1).toString();
			
			if (preguntar("Confirmar","Quita Linea " + linea + " ?")) {
				model.removeRow(frame.getJTable().getSelectedRow());
				data.clearBatch();
				data.addBatch(data.getQuitar_linea(idpedido,linea));
				data.executeBatch();	
			}	
		}
		
	}
	
	public void cerrar_lineas(){
		this.lineas.setVisible(false);
		this.lineas.dispose();
		frame.getLockableUI().setLocked(false);

	}
	
	public void importar(){
		frame.getLockableUI().setLocked(true);
		String idpedido=frame.get_txt_idpedido().getText();
		boolean ok=false;
		ok=data.existe(idpedido);
		if (!ok){
			ok=!this._guardar();
		}
		if (ok){
			importar_lineas(idpedido);
		}else{
			error("Verifique el pedido.");
		}

	}
	
	public Object[][] procesar_lineas(Object[][] results){
		Object[][] tmp=new Object[results.length][results[0].length];
		for (int i=0;i<results.length;i++){
			tmp[i][0]=results[i][0];
			tmp[i][1]=results[i][1];
			tmp[i][2]=results[i][2];
			tmp[i][3]=results[i][3];
			Double valor=0.0;
			try {
				valor = new Double(results[i][4].toString());
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tmp[i][4]=valor;
			
			
		}
		return tmp;
	}
	
	
	public void importar_lineas_search(){
		String idpedido=frame.get_txt_idpedido().getText();
		String desde=frame.get_txt_desde().getText();
		String hasta=frame.get_txt_hasta().getText();
		String idproveedor=frame.get_txt_idproveedor().getText();
		String categoria="";
		try {
			categoria = lineas.get_lst_categoria().getSelectedItem().toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int tipo=0;
		try {
			tipo=lineas.get_lst_modo().getSelectedIndex();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Object[][] results=data.getLineasImportar(idproveedor, idpedido,desde,hasta,categoria,tipo);
		results=procesar_lineas(results);
		boolean ok=false;
		if (results!=null){
			if (results.length>0){
				ok=true;
				this.crearTablaImportarLineas(results);
				this.centrar_frame(lineas);
			}
		}
		
	}
	private aplicacion.compras.carga.pedido.gui._Lineas lineas=null;
	public void importar_lineas(String idpedido){
		if (lineas!=null){
			lineas.setVisible(false);
			lineas.dispose();
		}
		String desde=frame.get_txt_desde().getText();
		String hasta=frame.get_txt_hasta().getText();
		String idproveedor=frame.get_txt_idproveedor().getText();
		String categoria="";
		
		Object[][] results=data.getLineasImportar(idproveedor, idpedido,desde,hasta,categoria,0);
		results=procesar_lineas(results);
		boolean ok=false;
		if (results!=null){
			if (results.length>0){
				ok=true;
				lineas=new aplicacion.compras.carga.pedido.gui._Lineas();
				this.crearTablaImportarLineas(results);
				this.centrar_frame(lineas);
				lineas.setVisible(true);
				lineas.get_btn_importar().setActionCommand(_Interface._btn_importar_lineas);
				lineas.get_btn_importar().addActionListener(this.getConstructor().getActionListener());
				lineas.get_btn_salir().setActionCommand(_Interface._btn_cerrar_lineas);
				lineas.get_btn_salir().addActionListener(this.getConstructor().getActionListener());
				lineas.setName(_Interface._linea);
				lineas.addWindowListener(this.getConstructor().getWindowListener());
				lineas.get_chk_seleccionar().setName(_Interface._chk_selecccionar_lineas_predeterminadas);
				lineas.get_chk_seleccionar().addItemListener(this.getConstructor().getItemListener());
				lineas.get_lst_categoria().setName(_Interface._lst_lineas_categoria);
				lineas.get_lst_modo().setName(_Interface._lst_lineas_modo);
				lineas.get_lst_categoria().addItemListener(this.getConstructor().getItemListener());
				lineas.get_lst_modo().addItemListener(this.getConstructor().getItemListener());
			}
		}
		if (!ok){
			error("No hay lineas predeterminadas para este proveedor");
		}
	}
	
	private void importar_lineas_seleccionadas(){
		JTable table=this.lineas.getJTable();
		String idpedido=frame.get_txt_idpedido().getText();
		
		for (int i=0;i<table.getRowCount();i++){
			boolean selected=false;
			try {
				selected = (Boolean) table.getValueAt(i, 0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String linea=table.getValueAt(i, 1).toString();
			if (selected){
				if (!data.existe_linea(idpedido, linea)){
					this.agregar_linea(linea);	
				}else{
					
					
				}
				
				
			}
		}		
		
		this.cerrar_lineas();
	
	}
	
	public void _importar_lineas_seleccionadas(){
		
		
		if (this.lineas!=null){
			
			JTable table=this.lineas.getJTable();
			int selections=0;
			for (int i=0;i<table.getRowCount();i++){
				boolean selected=false;
				try {
					selected = (Boolean) table.getValueAt(i, 0);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (selected){
					selections++;
				}
			}
			if (selections>0){
				boolean ok=confirmar("Confirme para importar:",2);
				if (ok){
					this.goImportar();
				}else{
					error("Operacion Cancelada");
				}
				
			}else{
				error("No hay Lineas Seleccionadas para Importar");
			}
			
		}
	
	}
	
	private boolean buscar_lineas() {
		boolean ok=false;
		String idpedido=frame.get_txt_idpedido().getText();
		String idproveedor = frame.get_txt_idproveedor().getText();
		String q = data.getLineas(idproveedor);
		System.out.println("cargando lineas>" +q);
		estado="Cargando Lineas";
		if (frame.get_txt_idproveedor().getText().compareTo("")!=0){
			Object[][] results = data.getResults(q);
			LinkedList lineas=new LinkedList(); 
			
			for (int i=0;i<results.length;i++){
				String linea=results[i][0].toString();
				boolean existe=data.existe_linea(idpedido,linea);
				if (!existe){
					System.out.println("agregando linea "+linea);
					lineas.add(linea);
				}else{
					System.out.println("no se agrega linea "+linea);
				}
			}
			if (lineas.size()>0){
				ok=true;
				data.clearBatch();
				for (int i=0;i<lineas.size();i++){
					String linea=(String) lineas.get(i);
					String qx=data.getInsertar_linea(idpedido,linea);
					System.out.println(linea+">"+qx);
					data.addBatch(qx);
				}
				data.executeBatch();
			}else{
				ok=false;
			}
			estado="Creando Valores";
			if (lineas.size()>0){
				this.current=0;
				lenght=lineas.size();
				for (int i=0;i<lineas.size();i++){
					String linea=(String) lineas.get(i);
					current=i;
					estado="Creando Valores Minimos "+ linea;
					this.crear_pedido_minimo(linea);
				
				}
				
				
				
				
				
			}
		}
		return ok;
	}
	
	public void create_lineas(Object[][] results){
		System.out.println("Creando Tabla Lineas");
		CustomTable table = this.crearTablaDeLineas();
		table.setData(results);
		table.setName(_Interface._table_lineas);
		table.addMouseListener(this.getConstructor().getMouseListener());
		table.build();
		table.fillData();
		JTable tablex=table.getTable();
		tablex.setColumnSelectionAllowed(false);
		frame.setJTable(tablex);
		done=true;
	}
	
	public void ordenar_lineas(){
		String idpedido=frame.get_txt_idpedido().getText();
		int tipo=0;
		try {
			tipo=frame.get_lst_modo_lineas().getSelectedIndex();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String categoria="";
		try {
			categoria=frame.get_lst_categoria_lineas().getSelectedItem().toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String q = data.getLineasAgregadas(idpedido,tipo,categoria);
		System.out.println(q);
		Object[][] results =data.getResults(q);
		
		results = this.proccess_lineas(results);
		if (results!=null){
			if (results.length>0){
				final Object[][] _results=results;
				if (javax.swing.SwingUtilities.isEventDispatchThread()){
					create_lineas(results);
				}else{
					Runnable _execute = new Runnable() {
				        public void run() {
				        	create_lineas(_results);
				        }
					};
					invokeLater(_execute);	
				}		
			}else{
				done=true;
			}
			
		}else{
			done=true;
		}
		
	}
	
	private void cargar_lineas() {
		String idpedido=frame.get_txt_idpedido().getText();
		int tipo=0;
		try {
			tipo=frame.get_lst_modo_lineas().getSelectedIndex();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String categoria="";
		try {
			categoria=frame.get_lst_categoria_lineas().getSelectedItem().toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String q = data.getLineasAgregadas(idpedido,tipo,categoria);
		
		System.out.println(q);
		Object[][] results =data.getResults(q);
		
		results = this.proccess_lineas(results);
		if (results!=null){
			if (results.length>0){
				final Object[][] _results=results;
				if (javax.swing.SwingUtilities.isEventDispatchThread()){
					create_lineas(results);
				}else{
					Runnable _execute = new Runnable() {
				        public void run() {
				        	create_lineas(_results);
				        }
					};
					invokeLater(_execute);	
				}		
			}else{
				done=true;
			}
			
		}else{
			done=true;
		}
		
		
		
	}

	public void load_pedido() {
		String idpedido = frame.get_txt_idpedido().getText();
		this.cargar_pedido(idpedido);
		
	}

	public void cargar_pedido(String idpedido) {
		this.clean();
		frame.get_txt_idpedido().setText(idpedido);
		frame.get_txt_idpedido().setEditable(false);

		Object[][] results = data.getPedido(idpedido);
		if (results != null) {
			if (results.length > 0) {
				nuevo = false;
				boolean _eliminado = data.eliminado(idpedido);
				frame.get_btn_guardar().setEnabled(!_eliminado);
				//frame.get_btn_preparar().setEnabled(!_eliminado);
				//frame.get_btn_presupuesto().setEnabled(!_eliminado);
				//frame.get_btn_remito().setEnabled(!_eliminado);
				//frame.get_btn_envio().setEnabled(!_eliminado);
				frame.get_btn_eliminar().setEnabled(!_eliminado);
				frame.get_btn_eliminar().setEnabled(true);
				//frame.get_btn_remito().setEnabled(true);
				//frame.get_btn_identificador().setEnabled(true);

				String descripcion = (String) results[0][0];
				String fecha_creacion = (String) results[0][1];
				String fecha_modificacion = (String) results[0][2];
				String cliente = (String) results[0][3];
				String cliente_descripcion = (String) results[0][4];
				String datos_extra = (String) results[0][5];
				String idvendedor = (String) results[0][6];
				String vendedor = (String) results[0][7];
				String idtransporte = (String) results[0][8];
				String transporte = (String) results[0][9];
				String fecha_envio = (String) results[0][10];
				String guia = (String) results[0][11];
				String agenda = (String) results[0][12];
				String seguimiento = (String) results[0][13];
				String domicilio = (String) results[0][14];
				String ciudad = (String) results[0][15];
				String idprovincia = (String) results[0][16];
				String cpostal = (String) results[0][17];
				String telefono = (String) results[0][18];
				String estado = (String) results[0][19];
				String observaciones = (String) results[0][20];
				String idcreador = (String) results[0][21];
				String email = (String) results[0][22];
				System.out.println("AGENDA>" + agenda);
				frame.get_txt_descripcion_pedido().setText(descripcion);
				frame.get_txt_fecha().setText(agenda);
				frame.get_txt_fecha_creacion().setText(fecha_creacion);
				frame.get_txt_modificado().setText(fecha_modificacion);
				frame.get_txt_idproveedor().setText(cliente);
				frame.get_txt_descripcion()
						.setText(cliente_descripcion);
				frame.get_txt_informacion().setText(datos_extra);
				frame.get_txt_idvendedor().setText(idvendedor);
				frame.get_txt_vendedor_descripcion().setText(vendedor);
				frame.get_txt_idtransporte().setText(idtransporte);
				frame.get_txt_transporte_descripcion().setText(transporte);
				if (fecha_envio.compareTo("01-01-1900")==0){
					fecha_envio="";
				}
				frame.get_txt_fecha_envio().setText(fecha_envio);
				frame.get_txt_guia().setText(guia);
				frame.get_chk_seguimiento().setSelected(
						seguimiento.compareTo("1") == 0);
				frame.get_txt_domicilio().setText(domicilio);
				frame.get_txt_cpostal().setText(cpostal);
				frame.get_txt_idciudad().setText(ciudad);
				frame.get_txt_idprovincia().setText(idprovincia);
				frame.get_txt_tel().setText(telefono);
				frame.get_txt_observacion().setText(observaciones);
				frame.get_txt_idcreador().setText(idcreador);
				this.fillCreador(idcreador);
				this.setEstado(estado);
				if (idprovincia.compareTo("") != 0) {
					this.evaluarProvincia(frame.get_txt_idprovincia());
				}
				if (idtransporte.compareTo("") != 0) {
					this.evaluarTransporte(frame.get_txt_idtransporte());
				}
				frame.get_txt_tel().setText(telefono);
				frame.get_txt_email().setText(email);
				frame.get_txt_idvendedor().requestFocusInWindow();
				
				cambios = false;
				goCargar();
			}
		}
	}

	public void cargarPedido(String idpedido) {
		nuevo=false;
		frame.get_txt_idpedido().setText(idpedido);
		String q=data.getPedidoEncabezado(idpedido);
		Object[][] results = data.getResults(q);
		if (results != null) {
			if (results.length > 0) {
				
				frame.get_btn_nuevo_pedido().setEnabled(false);
				String proveedor = results[0][0].toString();
				String fecha = results[0][1].toString();
				String descripcion = results[0][2].toString();
				String nombre = results[0][3].toString();
				String estado = results[0][4].toString();
				String seguimiento = results[0][5].toString();
				String fecha_agenda = results[0][6].toString();
				
				frame.get_txt_idpedido().setEditable(false);
				frame.get_txt_idproveedor().setText(proveedor);
				frame.get_txt_descripcion().setText(nombre);
				frame.get_txt_descripcion_pedido().setText(descripcion);
				frame.get_txt_fecha().setText(fecha_agenda);
				frame.get_txt_fecha_creacion().setText(fecha);
				frame.get_chk_seguimiento().setSelected(seguimiento.compareTo("1")==0);
				this.setEstado(estado);
				//cargar proveedor
				this.goCargar();
			}
		}
	}

	public void focus(){
		frame.get_txt_idpedido().requestFocusInWindow();
		frame.getJTabbedPane().setSelectedIndex(1);
	}
	
	
	private String getInstruccionEncabezado() {
		String q = "";
		String idpedido = frame.get_txt_idpedido().getText();
		String descripcion = frame.get_txt_descripcion_pedido().getText();
		descripcion = descripcion.replaceAll("'", "");
		String cliente = frame.get_txt_idproveedor().getText();
		String cliente_descripcion = frame.get_txt_descripcion()
				.getText();
		String idvendedor = frame.get_txt_idvendedor().getText();
		String info = frame.get_txt_informacion().getText();
		String idtransporte = frame.get_txt_idtransporte().getText();
		String fecha_envio = frame.get_txt_fecha_envio().getText();
		String fecha_agenda = frame.get_txt_fecha().getText();
		String guia = frame.get_txt_guia().getText();
		String total = frame.get_txt_total().getText().replaceAll(",", "");
		String email = frame.get_txt_email().getText();
		String domicilio = frame.get_txt_domicilio().getText();
		String ciudad = frame.get_txt_idciudad().getText();
		String idprovincia = frame.get_txt_idprovincia().getText();
		String cpostal = frame.get_txt_cpostal().getText();
		domicilio = domicilio.replaceAll("'", "");
		ciudad = ciudad.replaceAll("'", "");
		String telefono = frame.get_txt_tel().getText();
		total = total.replaceAll(",", "");
		String estado = frame.get_lst_estado().getSelectedItem().toString();
		String observaciones = frame.get_txt_observacion().getText();
		if (total.compareTo("") == 0) {
			total = "0.0";
		}
		String seguimiento = "0";
		if (frame.get_chk_seguimiento().isSelected()) {
			seguimiento = "1";
		}
		if (!nuevo) {
			q = data.getUpdateQuery(idpedido, descripcion, cliente,
					cliente_descripcion, idvendedor, info, idtransporte,
					fecha_envio, guia, fecha_agenda, total, seguimiento,
					domicilio, ciudad, idprovincia, cpostal, telefono, estado,
					observaciones, email);
		} else {
			q = data.getGuardarQuery(idpedido, descripcion, cliente,
					cliente_descripcion, idvendedor, info, idtransporte,
					fecha_envio, guia, fecha_agenda, total, seguimiento,
					domicilio, ciudad, idprovincia, cpostal, telefono, estado,
					observaciones, email);
		}
		return q;
	}

	private List<String> getInstruccionesGuardarEncabezado() {
		List<String> instrucciones = new ArrayList<String>();
		String instruccion = this.getInstruccionEncabezado();
		instrucciones.add(instruccion);
		return instrucciones;
	}
	

	public boolean _guardar() {
		boolean error = true;
		boolean ok = true;
		String idcliente = frame.get_txt_idproveedor().getText();
		String idpedido = frame.get_txt_idpedido().getText();
		if (idcliente.compareTo("") != 0) {
			if (proveedor.existe(idcliente)) {
				ok = true;
			} else {
				ok = false;
				error("Cuenta de Proveedor Incorrecta. Verifique");
				frame.get_txt_idproveedor().requestFocusInWindow();
			}
		} else {
			ok = false;
			error("Cuenta de Proveedor Incorrecta. Verifique");
			frame.get_txt_idproveedor().requestFocusInWindow();
		}

		if (ok) {
			String iduser = this.validar_usuario();
			String idvendedor = "";
			if (iduser.compareTo("") != 0) {
			
				try {
					idvendedor = (String) data.getVendedor(iduser)[0][0];
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (idvendedor.compareTo("") != 0) {
					frame.get_txt_idvendedor().setText(idvendedor);
					this.evaluarVendedor(frame.get_txt_idvendedor());
				}
			}else{
				ok=false;
			}
		}

		if (ok) {
			String fecha = frame.get_txt_fecha().getText();
			if (fecha.compareTo("") != 0) {
				ok = this.Fecha.esCorrecta(fecha);
				if (ok) {
					if (nuevo) {
						String sistema = data.getSystemDate();
						if (fecha.compareTo(sistema) != 0) {
							aviso("La Fecha de Agenda del Pedido se Ajustara a "
									+ sistema);
							frame.get_txt_fecha().setText(sistema);
						}
					}
				} else {
					error("Fecha de Agenda Incorrecta. Verifique");
					frame.get_txt_fecha().requestFocusInWindow();
					ok = false;
				}
			} else {
				error("Fecha de Agenda Incorrecta. Verifique");
				frame.get_txt_fecha().requestFocusInWindow();
				ok = false;
			}

		}


		if (ok) {
			if (idpedido.compareTo("") != 0) {
				if (!this.nuevo) {
					String modificado = frame.get_txt_modificado().getText();
					String modificacion = data.getUltimaModificacion(idpedido);
					if (modificado.compareTo(modificacion) != 0) {
						String NEW_LINE = System.getProperty("line.separator");
						String msg = "Advertencia: El pedido fue modificado ("
								+ modificacion
								+ ") posiblemente desde otra ubicacion";
						msg += NEW_LINE;
						msg += "Se Recomienda Verificar La Informacion Modificada antes de Confirmar la grabacion.";
						msg += NEW_LINE;
						msg += "Confirme para modificarlo de todas formas ";
						ok = confirmar(msg, 2);
					}
				}
			}
		}


		if (ok) {
			String descripcion = frame.get_txt_descripcion().getText();
			if (descripcion.compareTo("") != 0) {
				ok = true;
			} else {
				error("Ingrese el nombre de Proveedor para este pedido");
				frame.get_txt_descripcion().requestFocusInWindow();
				ok = false;
			}
		}
		if (ok) {
			error = this._guardar_post();
		}
		return error;
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
	public boolean elegir_vendedor() {
		boolean ok = false;
		System.out.println("elegir vendedor");
		String idvendedor = this.validar_vendedor();
		frame.get_txt_idvendedor().setText(idvendedor);

		if (idvendedor.compareTo("") != 0) {
			ok = true;
			this.evaluarVendedor(frame.get_txt_idvendedor());
			frame.get_txt_idvendedor().setEditable(false);
			frame.get_btn_buscar_vendedor().setEnabled(false);
			if (nuevo) {
				frame.get_txt_idcreador().setText(idvendedor);
				this.fillCreador(idvendedor);
			}
		} else {
			ok = false;
		}

		return ok;
	}
	
	public void fillModificado() {
		String fecha = new Convertidor()
				.getDateWithFormat("dd-MM-yyyy HH:mm:ss");
		frame.get_txt_modificado().setText(fecha);
	}

	public void fillCreado() {
		String fecha = new Convertidor()
				.getDateWithFormat("dd-MM-yyyy HH:mm:ss");
		frame.get_txt_fecha_creacion().setText(fecha);
	}
	
	public void fillCreador() {
		String idcreador = frame.get_txt_idvendedor().getText();
		frame.get_txt_idcreador().setText(idcreador);
		this.fillCreador(idcreador);
	}
	
	public void fillCreador(String idcreador) {
		Object[][] results = this.Vendedor.getInfo(idcreador);
		if (results != null) {
			if (results.length > 0) {
				String descripcion = (String) results[0][1];
				frame.get_txt_creador().setText(descripcion);
			}
		}

	}
	public void guardar() {

		boolean error = this._guardar();
		if (!error) {

			nuevo = false;

			this.aviso("Se Grabo Correctamente");
		} else {
			this.error("Error Grabando Comprobante");
		}
	}
	
	private List<String> getInstruccionesActualizarPunteros() {
		List<String> instrucciones = new ArrayList<String>();
		String instruccion = data.getUpdateTc(tc);
		instrucciones.add(instruccion);
		return instrucciones;
	}
	
	public boolean _guardar_post() {

		List<String> instrucciones = this.getInstruccionesGuardarEncabezado();
		//List<String> instrucciones_items = this.getInstruccionesGuardarItems();
		//List<String> instrucciones_pdc = this.getInstruccionesGuardarPDCs();
		data.beginTransaction();
		data.clearBatch();
		for (int i = 0; i < instrucciones.size(); i++) {
			System.out.println(instrucciones.get(i));
			data.addBatch(instrucciones.get(i));
		}

		
		if (nuevo) {
			List<String> instrucciones_punteros = this
					.getInstruccionesActualizarPunteros();
			for (int i = 0; i < instrucciones_punteros.size(); i++) {
				System.out.println(instrucciones_punteros.get(i));
				data.addBatch(instrucciones_punteros.get(i));
			}
		}

		boolean error = data.executeBatch();
		if (!error) {
			data.commitTransaction();
			cambios = false;
			nuevo = false;
			this.update_modificado();
			frame.get_btn_eliminar().setEnabled(true);
		} else {
			data.rollbackTransaction();
		}
		return error;
	}

	public void update_modificado() {
		String idpedido = frame.get_txt_idpedido().getText();
		String modificado = data.getUltimaModificacion(idpedido);
		frame.get_txt_modificado().setText(modificado);
	}
	public void guardar_pedido(){
		String idpedido=frame.get_txt_idpedido().getText();
		String descripcion=frame.get_txt_descripcion().getText();
		String fecha=frame.get_txt_fecha().getText();
		String estado=frame.get_lst_estado().getSelectedItem().toString();
		boolean seguimiento=frame.get_chk_seguimiento().isSelected();
		boolean error=data.guardar_pedido(idpedido, descripcion,fecha,estado,seguimiento);
		if(error){
			error("Error Grabando Pedido");
		}else{
			aviso("El Pedido se Grabo Correctamente");
		}
	}
    
	public void clean() {
		nuevo=false;
		
		
		frame.get_txt_descripcion().setText("");
		frame.get_txt_descripcion_pedido().setText("");
		
		frame.get_txt_idproveedor().setText("");
		frame.get_txt_fecha().setText("");
		frame.get_txt_items().setText("");
		frame.get_txt_linea().setText("");
		frame.get_txt_total().setText("");
		frame.get_txt_total_compra().setText("");
		frame.get_txt_total_unidades().setText("");
		frame.getJScrollPane().setViewportView(null);
		frame.getJScrollPane1().setViewportView(null);
		frame.get_txt_articulo().setText("");
		frame.get_txt_articulo_actualizacion().setText("");
		frame.get_txt_articulo_bloqueado().setText("");
		frame.get_txt_articulo_linea().setText("");
		frame.get_txt_articulo_stock().setText("");
		frame.get_txt_articulo_descripcion().setText("");
		
		frame.setJTable(null);
		frame.setJTable1(null);
		frame.get_txt_idpedido().setEditable(true);
		frame.get_txt_idproveedor().setEditable(false);
		frame.get_btn_nuevo_pedido().setEnabled(true);
		try {
			frame.get_lst_estado().setSelectedIndex(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		frame.get_txt_idproveedor().setText("");
		frame.get_txt_idvendedor().setText("");
		frame.get_txt_descripcion().setText("");
		frame.get_txt_informacion().setText("");
		frame.get_txt_email().setText("");
		frame.get_txt_tel().setText("");
		frame.get_txt_fax().setText("");
		frame.get_txt_idprovincia().setText("");
		frame.get_txt_cpostal().setText("");
		frame.get_txt_idciudad().setText("");
		frame.get_txt_domicilio().setText("");
		frame.get_txt_provincia_descripcion().setText("");
		frame.get_txt_descripcion_pedido().setText("");
		frame.get_txt_vendedor_descripcion().setText("");
		frame.get_txt_articulo().setText("");
		frame.get_txt_articulo_descripcion().setText("");
		frame.get_txt_articulo_stock().setText("");
		frame.get_txt_idpedido().setText("");
		frame.get_txt_items().setText("");
		frame.get_txt_idcreador().setText("");
		frame.get_txt_creador().setText("");
		frame.get_txt_informacion().setText("");
		
		frame.get_txt_total().setText("");
		frame.get_txt_modificado().setText("");
		
		frame.get_txt_idpedido().setEditable(true);
		frame.get_txt_fecha_envio().setText("");
		frame.get_txt_guia().setText("");
		frame.get_txt_idtransporte().setText("");
		frame.get_txt_transporte_descripcion().setText("");
		frame.get_txt_fecha_creacion().setText("");
		frame.get_btn_guardar().setEnabled(false);

		frame.get_btn_eliminar().setEnabled(false);
		frame.get_txt_observacion().setText("");
		frame.setJTable_articulos(null);
		this.getToday();

		// this.obtener_proximo_cpte();
		frame.get_txt_idpedido().setText("");
		this.focus();
		frame.getLockableUI().setLocked(false);
		
		frame.setJTable(null);
		frame.setJTable1(null);
		

		
		this.getToday();
		this.focus();
	}

	
	public void cargar_estados() {
		frame.get_lst_estado().removeItemListener(
				this.getConstructor().getItemListener());
		frame.get_lst_estado().removeAllItems();
		frame.get_lst_estado().addItem("Nuevo");
		frame.get_lst_estado().addItem("Enviado");
		frame.get_lst_estado().addItem("Recibido");
		frame.get_lst_estado().setSelectedIndex(0);
		frame.get_lst_estado().setName(_Interface._lst_estado);
		frame.get_lst_estado().addItemListener(
				(this.getConstructor().getItemListener()));
	}
	
	private aplicacion.herramientas.java.evaluadores.PedidoProveedor PedidoProveedor=null;
	public void initialize_PedidoProveedor(){
		PedidoProveedor=new aplicacion.herramientas.java.evaluadores.PedidoProveedor(){
			public void cargar(String codigo){
				Object[][] results=this.getInfo(codigo);
				String descripcion=(String) results[0][1];
				frame.get_txt_descripcion_pedido().setText(descripcion);
					
					load_pedido();	
				
				
			}
		};
		PedidoProveedor.setConstructor(this.getConstructor());
		
	}
	public void BuscarPedidoProveedor(JTextField tx){
		PedidoProveedor.Buscar(tx);
	}
	public void BuscarPedidoProveedor(){
		PedidoProveedor.Buscar(frame.get_txt_idpedido());
	}
	public void buscarPedidoProveedor(JTextField tx){
		PedidoProveedor.buscar(tx);
	}
	
	public void evaluarPedidoProveedor(JTextField tx){
		PedidoProveedor.evaluate(tx);
	}
	public void evaluarPedidoProveedor(){
		PedidoProveedor.evaluate(frame.get_txt_idpedido());
	}
	
	public void _evaluate_idpedido(JTextField tx){
		String idpedido=tx.getText();
		
		if (data.existe(idpedido)){
			evaluarPedidoProveedor(tx);
			
		}else{
			String prox=data.getProximoPEDIDO_Ceros();
			if (idpedido.compareTo(prox)==0){
				this._nuevo();
			}else{
				evaluarPedidoProveedor(tx);
				
			}
		}
	}
	
	public void createTimerCargar(){
		System.out.println("Create TimerCargar");
		crono=new Crono();
		crono.start();
		lenght=0;
		current=0;
		errors=0;
		done = false;
		canceled=false;
//		showLayer(false);
		Timer=new Timer(200, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (done |canceled){
					System.out.println("End of TimerCargar!");
					endbar();
					Timer.stop();
					//goCompleteLineas();
					
					}else {
						
					updateBar();
				}
			}
		}); 
		
	}
	
	public void createTimerImportar(){
		System.out.println("Create Timer Importar");
		crono=new Crono();
		crono.start();
		lenght=0;
		current=0;
		errors=0;
		done = false;
		canceled=false;
//		showLayer(false);
		Timer=new Timer(200, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (done |canceled){
					System.out.println("End of TimerCargar!");
					endbar();
					}else {
					updateBar();
				}
			}
		}); 
		
	}
	
	public void createTimerCompletarLineas(){
		System.out.println("Create TimerCompletarLineas");
		crono=new Crono();
		crono.start();
		lenght=0;
		current=0;
		errors=0;
		done = false;
		canceled=false;
		
//		showLayer(true);
		Timer=new Timer(200, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (done |canceled){
					System.out.println("End of TimerCompletarLineas");
					endbar();
					String linea="";
					try {
						linea = (String) frame.getJTable().getValueAt(0, 1);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
					}
					if (linea.compareTo("")!=0){
						goMostrarLinea(linea);	
					}
					
					
					
					}else {
						
					updateBar();
				}
			}
		}); 
		
	}
	public void createTimer(){
		System.out.println("Create Timer");
		crono=new Crono();
		crono.start();
		lenght=0;
		current=0;
		errors=0;
		done = false;
		canceled=false;
//		showLayer(false);
		Timer=new Timer(200, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (done |canceled){
					System.out.println("End Timer");
					endbar();
					
					}else {
						
					updateBar();
				}
			}
		}); 
		
	}
	
	
	public void createEmailTimer() {
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
	
	public void createTimerCritico(){
		System.out.println("Create Timer");
		crono=new Crono();
		crono.start();
		lenght=0;
		current=0;
		errors=0;
		done = false;
		canceled=false;
//		showLayer(false);
		Timer=new Timer(200, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (done |canceled){
					System.out.println("End Timer");
					endbar();
					
					}else {
						
					updateBar();
				}
			}
		}); 
		
	}
	
	public void createTimerProyectado(){
		System.out.println("Create Timer");
		crono=new Crono();
		crono.start();
		lenght=0;
		current=0;
		errors=0;
		done = false;
		canceled=false;
//		showLayer(false);
		Timer=new Timer(200, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (done |canceled){
					System.out.println("End Timer");
					endbar();
					
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
//		showLayer(true);
		frame.get_btn_cargar_pedido().setEnabled(true);
		frame.get_btn_guardar().setEnabled(true);
		Timer.stop();
		this.block(true);
	}
	

	private void crear_pedido_nuevo() {
		String q = "insert into b_pdp (idpedido,fecha,cuenta,desde,hasta,descripcion) values (";
		q += "'" + frame.get_txt_idpedido().getText() + "',";
		q += "'" + frame.get_txt_fecha().getText() + "',";
		q += "'" + frame.get_txt_idproveedor().getText()
				+ "','','','"+frame.get_txt_descripcion_pedido().getText()+"')";
		System.out.println(q);
		if (frame.get_txt_idpedido().getText().compareTo("")!=0){
			data.clearBatch();
			data.addBatch(q);
			boolean b = data.executeBatch();	
		}
		
	}

	public void crear_valores() {
		estado="Creando Valores";
		
		block(false);
		this.current=0;
		lenght = frame.getJTable().getRowCount();
		for (int i = 0; i < frame.getJTable().getRowCount(); i++) {
			String linea = (String) frame.getJTable().getValueAt(i,
					1);
			current=i;
			estado="Creando Valores Minimos "+ linea;
			this.crear_pedido_minimo(linea);
		}
		done=true;
	}

	public void cargar_valores() {
		System.out.println("Cargando Valores!!!");
		block(false);
		
		this.current=0;
		if (frame.getJTable()!=null){
			
			lenght = frame.getJTable().getRowCount();
			
			int i = 0; 
			while(i < lenght & !canceled) {
				current=i;
				if (!canceled){
					
				try {
					String linea = (String) frame.getJTable().getValueAt(i,	1);
					estado="Cargando valores de " + linea;
					System.out.println("Cargando Valores de Linea "+linea);
					this.cargar_valores_items_db(linea);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				}
				
				i++;
			}
			if (!canceled){
				if (this.pedido_existe()) {
					System.out.println("este pedido ya existe");
				} else {
					System.out.println("creando pedido nuevo");
					crear_pedido_nuevo();
				}	
			}
			
		
		}
		done=true;
		
	}

	public boolean linea_seleccionada(String linea){
		boolean b=false;
		int i=0;
		boolean found=false;
		while (i<frame.getJTable().getRowCount() & !found){
			String x=(String) frame.getJTable().getValueAt(i,1);
			found=x.toUpperCase().compareTo(linea.toUpperCase())==0;
			if (!found){
				i++;	
			}else {
				b=(Boolean)frame.getJTable().getValueAt(i,0);
			}
			
		}
		
		System.out.println("Linea Seleccionada? "+linea+">"+b);
		return b;
	}
	/*
	public void imprimir(){
		int u=0;
		//PedidoHtml etiquetas=new PedidoHtml();
		etiquetas.setGTransfer(GX);
		etiquetas.setSucursal("Neuquen");
		etiquetas.setFormato("A4N");
		etiquetas.setFontSize(11);
		etiquetas.setPedido(frame.get_txt_idpedido().getText());
		etiquetas.load_formato();
		LinkedList etqs=new LinkedList();
		LinkedList Articulos=new LinkedList();
		
		
		while ( u<Articulos.size()){
			Object[] obj=(Object[])Articulos.get(u);
			String art =(String)obj[0];
			boolean b=(Boolean)obj[3];
			if (b){
				String q="select descripcion,costo,descripabrev from v_ma_articulos ";
				q=q+" where idarticulo like '"+art+"' ";
				Object[][] results=data.getResults(q);
				String descripcion="";
				String costo ="";
				String linea ="";
				double cst=0.0;
				if (results!=null){
					if (results.length>0){
						descripcion=(String) results[0][0];
						costo=(String) results[0][1];
						linea=(String) results[0][2];
						cst=new Double(costo.replace(",", ""));
					}
				}
				
				double min =(Double)obj[2];
				double ped =(Double)obj[1];
				String total ="";
				String[] aux=new String[4];
				aux[0]=art;
				aux[1]=descripcion;
				aux[2]=""+new Convertidor().getMoney(ped,2);
				aux[3]=linea;
				
				if (ped>0){
					if (linea_seleccionada(linea)){
						etqs.add(aux);
					}else {
						System.out.println("articulo para pedir pero la linea no esta seleccionada");
					}
							
				}
			}
			
			u++;
		}
		if (etqs.size()>0){
			int etq=0;
			etiquetas.Hoja(etqs);	
		}else {
			JOptionPane.showMessageDialog(_pedidoproveedor, "Noy Hay Items Para Pedir!!!");
		}
	}
	*/
	private Object[][] procesar_items_linea(Object[][] aux) {
		double sum_linea = 0.0;
		double sum_linea_items = 0.0;
		// String linea=frame.get_txt_linea().getText();
		Convertidor C = new Convertidor();
		Object[][] tmp = new Object[aux.length][14];
		lenght=aux.length;
		
		boolean existe = this.pedido_existe();
		for (int i = 0; i < aux.length; i++) {
			String usar = (String) aux[i][0];
			
			String art = (String) aux[i][1];

			String desc = (String) aux[i][2];
			String categoria = (String) aux[i][3];
			String ranking = (String) aux[i][4];
			String cst = (String) aux[i][5];
			String min = (String) aux[i][6];
			String proyec = (String) aux[i][7];
			String stk = (String) aux[i][8];
			String ven = (String) aux[i][9];
			String guard = (String) aux[i][10];

			String uventa = (String) aux[i][11];
			String ucompra = (String) aux[i][12];
			current=i;
			estado="cargando item ";
			
			double stock = 0.0;
			double minimo = 0.0;
			double pedido = 0.0;
			double qty = 0.0;
			double ventas = 0.0;
			double proyectado=0.0;
			double _ranking=0.0;
			boolean b = false;

			try {
				ventas = new Double(ven.replace(",", ""));
			} catch (Exception e) {
				System.out.println("Error. load_items_values() stock> "
						+ e.getMessage());
			}
			try {
				_ranking = new Double(ranking);
			} catch (Exception e) {
				System.out.println("Error. load_items_values() stock> "
						+ e.getMessage());
			}
			if (ventas<0){
				ventas=0;
			}
			try {
				stock = new Double(stk.replace(",", ""));
			} catch (Exception e) {
				System.out.println("Error. load_items_values() stock> "
						+ e.getMessage());
			}

			try {
				qty = new Double(guard.replace(",", ""));
			} catch (Exception e) {
				System.out.println("Error. load_items_values() qty> "
						+ e.getMessage());
			}

			try {
				minimo = new Double(min.replace(",", ""));
			} catch (Exception e) {
				System.out.println("Error. load_items_values() minimo> "
						+ e.getMessage());
			}
			
			try {
				proyectado = new Double(proyec.replace(",", ""));
			} catch (Exception e) {
				System.out.println("Error. load_items_values() proyectado> "
						+ e.getMessage());
			}

			if (minimo < 0) {
				minimo = 0;
			}

			try {
				if (stock <= 0) {
					stock = 0.0;
				}
				if (minimo > stock) {
					pedido = minimo - stock;
				} else {
					pedido = 0.0;
				}

			} catch (Exception e) {
				System.out.println("Error. load_items_values() calculo> "
						+ e.getMessage());
			}

			System.out.println("procesando " + art + "> pedido" + pedido
					+ " qty?" + qty);

			if (qty != pedido) {
				if (existe) {
					pedido = qty;
				}

			}
			
			b=usar.compareTo("1")==0;
			
			double costo = 0.0;

			try {
				costo = new Double(cst.replace(",", ""));
			} catch (Exception e) {
				System.out.println("Error. load_items_values() costo> "
						+ e.getMessage());
			}
			double _uventa = 0.0;

			try {
				_uventa = new Double(uventa.replace(",", ""));
			} catch (Exception e) {
				System.out.println("Error. load_items_values() costo> "
						+ e.getMessage());
			}
			double _ucompra = 0.0;

			try {
				_ucompra = new Double(ucompra.replace(",", ""));
			} catch (Exception e) {
				System.out.println("Error. load_items_values() costo> "
						+ e.getMessage());
			}
			double tot = 0.0;
			try {
				if (pedido > 0) {
					tot = pedido * costo;
					tot=new Convertidor().roundDouble(tot, 2);
				}
			} catch (Exception e) {
				System.out.println("Error. load_items_values() total> "
						+ e.getMessage());
			}
			if (b) {
				tmp[i][0] = "1";
			} else {
				tmp[i][0] = "0";
			}

			tmp[i][1] = art;
			tmp[i][2] = desc;
			tmp[i][3] = categoria;
			tmp[i][4] = _ranking;
			tmp[i][5] = costo;
			tmp[i][6] = proyectado;
			tmp[i][7] = minimo;
			tmp[i][8] = stock;
			tmp[i][9] = ventas;
			tmp[i][10] = qty;
			tmp[i][11] = tot;
			tmp[i][12] = _uventa;
			tmp[i][13] = _ucompra;

		}
		done = true;
		return tmp;
	}

	private Object[][] procesar_datos_items_db(Object[][] aux, String linea) {

		double sum_linea = 0.0;
		double sum_linea_items = 0.0;
		// String linea=frame.get_txt_linea().getText();
		Convertidor C = new Convertidor();

		for (int i = 0; i < aux.length; i++) {
			String usar = (String) aux[i][0];
			String art = (String) aux[i][1];
			String desc = (String) aux[i][2];
			String categoria = (String) aux[i][3];
			String ranking = (String) aux[i][4];
			String cst = (String) aux[i][5];
			String cri = (String) aux[i][6];
			String pro = (String) aux[i][7];
			String stk = (String) aux[i][8];
			String ven = (String) aux[i][9];
			String guard = (String) aux[i][10];
			double stock = 0.0;
			double minimo = 0.0;
			double pedido = 0.0;
			boolean b = false;
			double qty = 0.0;
			try {
				int _ranking=new Integer(ranking);
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				stock = new Double(stk.replace(",", ""));
			} catch (Exception e) {
				System.out.println("Error. load_items_values() stock> "
						+ e.getMessage());
			}

			try {
				minimo = new Double(pro.replace(",", ""));
			} catch (Exception e) {
				System.out.println("Error. load_items_values() minimo> "
						+ e.getMessage());
			}

			if (minimo < 0) {
				minimo = 0;
			}

			try {
				if (stock <= 0) {
					stock = 0.0;
				}
				if (minimo > stock) {
					pedido = minimo - stock;
				} else {
					pedido = 0.0;
				}
				try {
					qty = new Double(guard.replace(",", ""));
				} catch (Exception e) {
					System.out.println("Error. load_items_values() qty> "
							+ e.getMessage());
				}
				if (pedido != qty) {
					pedido = qty;
				}
				//if (!loading){
					
				//}else{
					b=usar.compareTo("1")==0;
				//}
				
			} catch (Exception e) {
				System.out.println("Error. load_items_values() calculo> "
						+ e.getMessage());
			}

			double costo = 0.0;

			try {
				costo = new Double(cst.replace(",", ""));
			} catch (Exception e) {
				System.out.println("Error. load_items_values() costo> "
						+ e.getMessage());
			}
			double tot = 0.0;
			try {
				if (pedido > 0) {
					tot = pedido * costo;
				}
			} catch (Exception e) {
				System.out.println("Error. load_items_values() total> "
						+ e.getMessage());
			}
			if (pedido > 0 & b) {
				// this.addArticle(art, pedido, minimo,true);
				sum_linea += tot;
				sum_linea_items += pedido;
			}
			// if ((tot>0|pedido>0) & b){

			// }
		}

		if (sum_linea_items > 0 | sum_linea > 0) {
			setLineValues(linea, sum_linea_items, sum_linea, true);
		} else {
			setLineValues(linea, sum_linea_items, sum_linea, false);
		}
		return aux;
	}
	
	private Object[][] procesar_datos_items(Object[][] aux, String linea) {

		double sum_linea = 0.0;
		double sum_linea_items = 0.0;
		// String linea=frame.get_txt_linea().getText();
		Convertidor C = new Convertidor();

		for (int i = 0; i < aux.length; i++) {
			String usar = (String) aux[i][0];
			String art = (String) aux[i][1];
			String desc = (String) aux[i][2];
			String categoria = (String) aux[i][3];
			String ranking = (String) aux[i][4];
			String cst = (String) aux[i][5];
			String cri = (String) aux[i][6];
			String pro = (String) aux[i][7];
			String stk = (String) aux[i][8];
			String ven = (String) aux[i][9];
			String guard = (String) aux[i][10];
			double stock = 0.0;
			double minimo = 0.0;
			double pedido = 0.0;
			boolean b = false;
			double qty = 0.0;
			try {
				stock = new Double(stk.replace(",", ""));
			} catch (Exception e) {
				System.out.println("Error. load_items_values() stock> "
						+ e.getMessage());
			}

			try {
				minimo = new Double(pro.replace(",", ""));
			} catch (Exception e) {
				System.out.println("Error. load_items_values() minimo> "
						+ e.getMessage());
			}

			if (minimo < 0) {
				minimo = 0;
			}

			try {
				if (stock <= 0) {
					stock = 0.0;
				}
				if (minimo > stock) {
					pedido = minimo - stock;
				} else {
					pedido = 0.0;
				}
				try {
					qty = new Double(guard.replace(",", ""));
				} catch (Exception e) {
					System.out.println("Error. load_items_values() qty> "
							+ e.getMessage());
				}
				if (pedido != qty) {
					pedido = qty;
				}
				//if (!loading){
					if (pedido > 0) {
						
						b = true;
					}
				//}else{
					//b=usar.compareTo("1")==0;
				//}
				
			} catch (Exception e) {
				System.out.println("Error. load_items_values() calculo> "
						+ e.getMessage());
			}

			double costo = 0.0;

			try {
				costo = new Double(cst.replace(",", ""));
			} catch (Exception e) {
				System.out.println("Error. load_items_values() costo> "
						+ e.getMessage());
			}
			double tot = 0.0;
			try {
				if (pedido > 0) {
					tot = pedido * costo;
				}
			} catch (Exception e) {
				System.out.println("Error. load_items_values() total> "
						+ e.getMessage());
			}
			if (pedido > 0 & b) {
				// this.addArticle(art, pedido, minimo,true);
				sum_linea += tot;
				sum_linea_items += pedido;
			}
			// if ((tot>0|pedido>0) & b){

			// }
		}

		if (sum_linea_items > 0 | sum_linea > 0) {
			setLineValues(linea, sum_linea_items, sum_linea, true);
		} else {
			setLineValues(linea, sum_linea_items, sum_linea, false);
		}
		return aux;
	}

	private void crear_pedido_items(Object[][] aux, String linea,int dias,int margen) {

		double sum_linea = 0.0;
		double sum_linea_items = 0.0;
		// String linea=frame.get_txt_linea().getText();
		Convertidor C = new Convertidor();
		
		int pedidos=0;
		double items=0.0;
		double costs=0.0;
		double rotacion=0.0;
		double pickups=0.0;
		double sugerido=0.0;
		for (int i = 0; i < aux.length; i++) {
		
			String art = (String) aux[i][0];
			String desc = (String) aux[i][1];
			String cst = (String) aux[i][2];
			String min = (String) aux[i][3];
			String stk = (String) aux[i][4];
			String ven = (String) aux[i][5];
			String rot = (String) aux[i][7];
			String pick = (String) aux[i][8];

			double stock = 0.0;
			double minimo = 0.0;
			double pedido = 0.0;
			boolean b = false;

			try {
				stock = new Double(stk.replace(",", ""));
			} catch (Exception e) {
				System.out.println("Error. load_items_values() stock> "
						+ e.getMessage());
			}

			try {
				minimo = new Double(min.replace(",", ""));
			} catch (Exception e) {
				System.out.println("Error. load_items_values() minimo> "
						+ e.getMessage());
			}
			try {
				rotacion = new Double(rot.replace(",", ""));
			} catch (Exception e) {
				System.out.println("Error. load_items_values() minimo> "
						+ e.getMessage());
			}
			try {
				pickups = new Double(pick.replace(",", ""));
			} catch (Exception e) {
				System.out.println("Error. load_items_values() minimo> "
						+ e.getMessage());
			}

			if (rotacion>0){
				sugerido=(margen+dias)*rotacion/360;
				sugerido=Math.round(sugerido);
				sugerido=new Double(sugerido).intValue();
			}
			if (minimo < 0) {
				minimo = 0;
			}

			try {
				if (stock <= 0) {
					stock = 0.0;
				}
				if (sugerido > stock) {
					pedido = sugerido - stock;
					
				} else {
					pedido = 0.0;
				}
				if (pedido > 0) {
					b = true;
				}
			} catch (Exception e) {
				System.out.println("Error. load_items_values() calculo> "
						+ e.getMessage());
			}

			double costo = 0.0;

			try {
				costo = new Double(cst.replace(",", ""));
			} catch (Exception e) {
				System.out.println("Error. load_items_values() costo> "
						+ e.getMessage());
			}
			double tot = 0.0;
			try {
				if (pedido > 0) {
					tot = pedido * costo;
				}
			} catch (Exception e) {
				System.out.println("Error. load_items_values() total> "
						+ e.getMessage());
			}
			if (pedido > 0) {
				// this.addArticle(art, pedido, minimo,true);
				items+=pedido;
				costs+=tot;
				this.update_pedido(art, desc, costo, pedido, b,linea);
				pedidos++;
			}
			// if ((tot>0|pedido>0) & b){

			// }
		}
		System.out.println("Evaluando Compra DE Linea "+linea+" seleccion? "+(pedidos>0)+ " =>"+pedidos);
		String idpedido = frame.get_txt_idpedido().getText();
		this.setLineValues(linea, items, costs, (items>0));
		
		
	}

	private void setLineValues(String linea, double items, double values,
			boolean b) {
		String idpedido=frame.get_txt_idpedido().getText();
		String descripcion=data.get_linea_descripcion(linea);
		Object[][] results=data.getLineStatics(descripcion);
		int tipo=0;
		tipo=frame.get_lst_modo_lineas().getSelectedIndex();
		String categoria="";
		double valor=0.0;
		if (tipo<=0){
			try {
				categoria=results[0][1].toString();
				valor=new Double(results[0][2].toString());
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (tipo==1){
			try {
				categoria=results[0][2].toString();
				valor=new Double(results[0][3].toString());
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (tipo==2){
			try {
				categoria=results[0][4].toString();
				valor=new Double(results[0][5].toString());
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		valor=new Convertidor().roundDouble(valor, 2);
		if (frame.getJTable() != null) {
			boolean found = false;
			int u = 0;
			while (!found & u < frame.getJTable().getRowCount()) {
				found = frame.getJTable().getValueAt(u, 1)
						.toString().compareTo(linea) == 0;
				if (!found) {
					u++;
				}

			}
			if (found) {
				//if (!loading){
					
				//	frame.getJTable().setValueAt(b, u, 0);
				//	data.setSeleccionada(idpedido, linea, b);	
				//}else{
					//b=data.getSeleccionada(idpedido, linea);
				frame.getJTable().setValueAt(b, u, 0);
				frame.getJTable().setValueAt(linea, u, 1);
				frame.getJTable().setValueAt(descripcion, u, 2);
				frame.getJTable().setValueAt(categoria, u, 3);
				frame.getJTable().setValueAt(valor, u, 4);
				frame.getJTable().setValueAt(items, u, 5);
				frame.getJTable().setValueAt(new Convertidor().roundDouble(values,2), u, 6);
				this.updateLinea(idpedido, linea, b, items, values);
				frame.getJTable().repaint();
				
			}
		}
		// recalculate_total_vars();
	}

	public void updateLinea(String idpedido,String linea,boolean seleccionada,Double items,Double valor){
		String q=data.getUpdate(idpedido, linea, seleccionada, items, valor);
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
	
	public void mostrar_linea_seleccionada(int row) {
		
		String linea="";
		try {
			linea = frame.getJTable().getValueAt(row, 1)
					.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		estado="cargando linea "+linea;
		this.goMostrarLinea(linea);
	}

	public void mostrar_item_down(int row,JTable table) {
		if (row<=table.getRowCount()-1){
			this.mostrar_item(row, table);
		}
	}
	public void mostrar_item_up(int row,JTable table) {
		if (row>=0){
			this.mostrar_item(row, table);
		}
	}
public void mostrar_item(int row,JTable table) {
		
		String idarticulo="";
		try {
			idarticulo = table.getValueAt(row, 1)
					.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.fillExtra(idarticulo);
	}

	private void scargar_valores_items(String linea) {
		String idpedido = frame.get_txt_idpedido().getText();
		String idproveedor = frame.get_txt_idproveedor().getText();
		String desde=frame.get_txt_desde().getText();
		String hasta=frame.get_txt_hasta().getText();
		String categoria=frame.get_lst_categoria().getSelectedItem().toString();
		String stock="";
		try {
			stock = frame.get_lst_stock().getSelectedItem().toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int tipo=0;
		try {
			tipo=frame.get_lst_modo().getSelectedIndex();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String descripcion=frame.get_txt_descripcion_linea().getText();
		
		String q = data.getLineaItems(idpedido,idproveedor,linea,desde,hasta,categoria,stock,tipo,descripcion);
		System.out.println(q);
		Object[][] aux = data.getResults(q);
		if (aux!=null){
			if (aux.length>0){
				this.procesar_datos_items(aux, linea);		
			}else{
				done=true;
			}
		}else{
			done=true;
		}
		
		
	}
	
	public void editarArticulo(int row) {
		String idarticulo = frame.getJTable1().getValueAt(row, 1).toString();
		Object[][] results = data.getResults(data.getArticulo(idarticulo));
		boolean exist = false;
		if (results != null) {
			if (results.length > 0) {
				exist = true;

			}
		}
		if (exist) {
			this.goMa_Articulos(idarticulo);
		} else {
			error("El articulo " + idarticulo + " es inexistente");
		}
	}
	
	private aplicacion.inventario.articulo.constructor._Constructor articulo = null;
	public void goMa_Articulos(String idarticulo) {
		if (articulo != null) {
			articulo.dispose();
		}
		articulo = new aplicacion.inventario.articulo.constructor._Constructor();
		articulo.setParameter(_parametros.LookAndFeel, this.getConstructor()
				.getLookAndFeelTheme());
		articulo.setParameter(_parametros.connector, this.getConstructor()
				.getConnectionHandler());
		articulo
				.setParameter(
						aplicacion.inventario.articulo.interfaces._parametros.idarticulo,
						idarticulo);
		articulo.build(this.getConstructor());
		articulo.init();
	}
	private void cargar_valores_items_db(String linea) {
		String idpedido = frame.get_txt_idpedido().getText();
		String idproveedor = frame.get_txt_idproveedor().getText();
		String desde=frame.get_txt_desde().getText();
		String hasta=frame.get_txt_hasta().getText();
		String categoria=frame.get_lst_categoria().getSelectedItem().toString();
		String stock="";
		try {
			stock = frame.get_lst_stock().getSelectedItem().toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int tipo=0;
		try {
			tipo=frame.get_lst_modo().getSelectedIndex();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String descripcion=frame.get_txt_descripcion_linea().getText();
		
		String q = data.getLineaItems(idpedido,idproveedor,linea,desde,hasta,categoria,stock,tipo,descripcion);
		System.out.println(q);
		Object[][] aux = data.getResults(q);
		if (aux!=null){
			if (aux.length>0){
				this.procesar_datos_items_db(aux, linea);		
			}else{
				done=true;
			}
		}else{
			done=true;
		}
		
		
	}

	
	private class _taskCompleteLineas{
		
		_taskCompleteLineas(){
			
			cargar_valores();
			
			
			recalculate_total_vars(-1,false);
			recalculate_vars();
			
		}
	}
	
	public void showLayer(boolean show){
		frame.getLockableUI().setLocked(!show);
	}
	
	private class _taskCargar {
		
		_taskCargar() {
			block(false);
			cargar_lineas();	
		}
	}
	
	private class _taskImportar {
		
		_taskImportar() {
			block(false);
			importar_lineas_seleccionadas();	
		}
	}

private class _taskNuevo {
		
		_taskNuevo() {
			block(false);
			String cuenta=frame.get_txt_idproveedor().getText();
			String idpedido=frame.get_txt_idpedido().getText();
			String descripcion=frame.get_txt_descripcion().getText();
			String estado=frame.get_lst_estado().getSelectedItem().toString();
			String q=data.getInsertNuevo(idpedido, cuenta,descripcion,estado);
			data.clearBatch();
			data.addBatch(q);
			boolean error=data.executeBatch();
			if(!error){
				data.genera_nuevo_pedido();
				boolean ok=buscar_lineas();
				nuevo=false;
				if (ok){
					cargar_lineas();
					
				}
				
			}
			
			
		}
	}
	
	public void setEstado(String estado){
		for (int i=0;i<frame.get_lst_estado().getItemCount();i++){
			if (frame.get_lst_estado().getItemAt(i).toString().compareTo(estado)==0){
				frame.get_lst_estado().setSelectedIndex(i);
			}
		}
	}

	private class _taskMostrarLinea {
		_taskMostrarLinea(String linea) {
			block(false);
			mostrar_linea(linea);
			
		}
	}
	
	private class _taskCalculateProyectado {
		_taskCalculateProyectado(boolean par) {
			block(false);
			calculate_proyectado(par);
		}
	}
	private class _taskCalculateCritico {
		_taskCalculateCritico() {
			block(false);
			calculate_critico();
		}
	}

	
	public void goCargar() {
		
		this.createTimerCargar();
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
	
private void goImportar() {
		
		this.createTimerImportar();
		estado="Importando Lineas Seleccionadas";
		frame.getJProgressBar().setIndeterminate(true);
		
		SwingWorker worker = null;
		if (worker == null) {
			worker = new SwingWorker() {
				public Object construct() {
					return new _taskImportar();
				}
			};
		}
		if (Timer!=null) {
			Timer.start();
		}
		worker.start();
	}
	
public void goNuevo() {
		
		this.createTimerCargar();
		frame.getJProgressBar().setIndeterminate(true);
		SwingWorker worker = null;
		if (worker == null) {
			worker = new SwingWorker() {
				public Object construct() {
					return new _taskNuevo();
				}
			};
		}
		if (Timer!=null) {
			Timer.start();
		}
		worker.start();
		
	}
	
public void goCompleteLineas() {
		System.out.println("Completar Lineas");
		this.createTimerCompletarLineas();
		frame.getJProgressBar().setIndeterminate(true);
		SwingWorker worker = null;
		if (worker == null) {
			worker = new SwingWorker() {
				public Object construct() {
					return new _taskCompleteLineas();
				}
			};
		}
		if (Timer!=null) {
			Timer.start();
		}
		worker.start();
		
	}	
	public void goMostrarLinea(String _linea) {
		System.out.println("Mostrar Linea "+_linea);
		Timer.stop();
		this.createTimer();
		frame.getJProgressBar().setIndeterminate(true);
		SwingWorker worker = null;
		final String linea = _linea;
		if (worker == null) {
			worker = new SwingWorker() {
				public Object construct() {
					return new _taskMostrarLinea(linea);
				}
			};
		}
		if (Timer!=null) {
			Timer.start();
		}
		worker.start();
	}

	public void _cancelar() {
		int n = JOptionPane.showConfirmDialog(frame,
				"Cancela Edicion del Pedido?", "Cancelar",
				JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION) {
			if (!done){
				canceled=true;
				done=true;
			}else{
				clean();	
			}
			
		}
	}
	
	public void evaluate_seleccion(int row,boolean seleccionada){
		if (row>=0){
			String linea=frame.getJTable().getValueAt(row, 1).toString();
			String idpedido=frame.get_txt_idpedido().getText();
			data.setSeleccionada(idpedido, linea, seleccionada);
			recalculate_total_vars(row, seleccionada);	
		}
		
	}
	
	public boolean updateItem(String idpedido,String idarticulo,double pedir,boolean seleccionada){
		String q=data.getUpdateItem(idpedido, idarticulo, pedir, seleccionada);
		System.out.println("UPDATE ITEM>"+q);
		data.clearBatch();
		data.addBatch(q);
		return data.executeBatch();
		
	}
	
	public void verMaestro() {
		String idcliente = frame.get_txt_idproveedor().getText();
		if (idcliente.compareTo("") != 0) {
			aplicacion.proveedor.archivo.constructor._Constructor CC = new aplicacion.proveedor.archivo.constructor._Constructor();
			CC.setParameter(_parametros.connector, this._data
					.getConnectionHandler());
			CC.setParameter(_parametros.LookAndFeel, this.getConstructor()
					.getLookAndFeelTheme());
			CC
					.setParameter(
							aplicacion.proveedor.archivo.interfaces._Parametros.idproveedor,
							idcliente);
			CC.build(this.getConstructor());
			CC.init();
		}
	}
	public void seleccionar_items(boolean seleccion){
		if (frame.getJTable1()!=null){
			for (int i=0;i<frame.getJTable1().getRowCount();i++){
				double pedir=(Double)frame.getJTable1().getValueAt(i, 7);
					
					String idpedido=frame.get_txt_idpedido().getText();
					String idarticulo=frame.getJTable1().getValueAt(i, 1).toString();
					
					boolean seleccionar=pedir>0;
					if (seleccionar){
						if (seleccion){
							frame.getJTable1().setValueAt(seleccionar, i, 0);
							this.updateItem(idpedido, idarticulo, pedir, seleccionar);	
						}
						
					}
					
					
			}	
		}
		this.recalculate_vars();
	}
	
	public void evaluate_seleccion_item(int row,boolean seleccionada){
		if (row>=0){
			String idpedido=frame.get_txt_idpedido().getText();
			String idarticulo=frame.getJTable1().getValueAt(row, 1).toString();
			double pedir=(Double)frame.getJTable1().getValueAt(row, 10);
			
			this.updateItem(idpedido, idarticulo, pedir, seleccionada);
			
			this.recalculate_vars(row, seleccionada);
			//data.setSeleccionada(idpedido, linea, seleccionada);
			//recalculate_total_vars(row, seleccionada);	
		}
		
	}
	
	public void initialize_layer(){
		frame.getLockableUI().setLocked(false);
	}
	
	private aplicacion.herramientas.java.evaluadores.Fecha Fecha=null;
	public void initialize_Fecha(){
		Fecha=new aplicacion.herramientas.java.evaluadores.Fecha(){
			public void cargar(String codigo){
				frame.get_txt_idproveedor().requestFocusInWindow();
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
	 
	 public void getToday() {
			_Frame _frame = (_Frame) this._frame;
			String hasta=new Convertidor().getDateWithFormat("dd-MM-yyyy");
			frame.get_txt_hasta().setText(hasta);
			
			String desde=this.getDaysRoll(hasta, -90);
			frame.get_txt_desde().setText(desde);
			_frame.get_txt_fecha().setText(hasta);
		}
	 
	 
	 public void goCalculateCritico() {
			
//			this.showLayer(true);
			this.createTimer();
			frame.getJProgressBar().setIndeterminate(true);
			SwingWorker worker = null;
			if (worker == null) {
				worker = new SwingWorker() {
					public Object construct() {
						return new _taskCalculateCritico();
					}
				};
			}
			if (Timer!=null) {
				Timer.start();
			}
			worker.start();
		}
	 
	 public void goCalculateProyectado() {
			
//			this.showLayer(true);
			int sel=this.preguntar("Proyeccion Tipo", "Seleccione El modo", new String[]{"PAR","IMPAR"}, "IMPAR");
			boolean par=sel==0;
			this.createTimer();
			
			frame.getJProgressBar().setIndeterminate(true);
			SwingWorker worker = null;
			final boolean _par=par;
			if (worker == null) {
				worker = new SwingWorker() {
					public Object construct() {
						
						return new _taskCalculateProyectado(_par);
						
					}
				};
			}
			if (Timer!=null) {
				Timer.start();
			}
			worker.start();
		}
	 
	 public Color getColor(int row,int col,JTable table){
			Color color=Color.white;
			double stock=0.0;
			String _stock="0.0";
			if (table!=null){
				try {
					_stock = table.getValueAt(row, 5).toString();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (_stock!=null){
					if (_stock.compareTo("")!=0){
						stock=new Double(_stock.replaceAll(",", ""));
					}
				}	
			}
			//System.out.println("Stock>"+_stock);
			if (stock>0){
				color=Color.GREEN;
			}
			return color;
		}
	 
	 private void crearTablaArticulos(Object[][] auzx){
			final CustomTable Table=new CustomTable();
			System.out.println("Creando Tabla Articulos");
			
			Column col=new Column();
			col.setName("");
			col.setWidth(20);
			col.setClass(Boolean.class);
			col.setEditable(false);
			col.setEditable(true);
			CheckBoxCellEditor chkce = new CheckBoxCellEditor();
			chkce.setItemListener(this.getConstructor().getItemListener());
			chkce.setTipo(Boolean.class);
			//chkce.setName(_Interface._table_chk_articulos);
			col.setCellEditor(chkce.getCellCheck());
			Table.addColumn(col);
			
			col=new Column();
			col.setName("idarticulo");
			col.setWidth(120);
			col.setClass(String.class);
			col.setEditable(false);
			TableColorCellRenderer cellrender=	new TableColorCellRenderer();
			cellrender.setLogic(this);
			col.setCellRenderer(cellrender);
			Table.addColumn(col);
			
			col=new Column();
			col.setName("descripcion");
			col.setWidth(280);
			col.setClass(String.class);
			col.setEditable(false);
			cellrender=	new TableColorCellRenderer();
			cellrender.setLogic(this);
			col.setCellRenderer(cellrender);
			Table.addColumn(col);
			
			col=new Column();
			col.setName("linea");
			col.setWidth(180);
			col.setClass(String.class);
			col.setEditable(false);
			cellrender=	new TableColorCellRenderer();
			cellrender.setLogic(this);
			col.setCellRenderer(cellrender);
			Table.addColumn(col);
			
			col=new Column();
			col.setName("costo");
			col.setWidth(80);
			col.setClass(String.class);
			col.setEditable(false);
			cellrender=	new TableColorCellRenderer();
			cellrender.setLogic(this);
			col.setCellRenderer(cellrender);
			Table.addColumn(col);
			
			
			col=new Column();
			col.setName("stock");
			col.setWidth(70);
			col.setClass(String.class);
			col.setEditable(false);
			cellrender=	new TableColorCellRenderer();
			cellrender.setLogic(this);
			col.setCellRenderer(cellrender);
			Table.addColumn(col);
			Table.setData(auzx);
			Table.setFont(new Font("Dialog", Font.BOLD, 10));
			//Table.setName(_Interface._table_articulo);
			Table.build();
			Table.fillData();
			
			frame.setJTable_articulos(Table.getTable());
			
		} 
	 
	 
	 private boolean cargado(String idarticulo){
			boolean cargado=false;
			int i=0;
			while (i<frame.getJTable_articulos().getRowCount() & !cargado){
				String _idarticulo="";
				try {
					_idarticulo=frame.getJTable_articulos().getValueAt(i,1).toString();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
				cargado=(_idarticulo.compareTo(idarticulo)==0);
				i++;
			}
			return cargado;
		}
	 
	 public void cargar_equivalencias(String _idarticulo){
		 
			Object[][] results=data.getEquivalencias(_idarticulo);
			
			for (int i=0;i<results.length;i++){
				String idarticulo=(String) results[i][0];
				if (!this.cargado(idarticulo)){
					boolean empty=true;
					DefaultTableModel model=(DefaultTableModel) frame.getJTable_articulos().getModel();
					int row=model.getRowCount()-1;
					try {
						empty=frame.getJTable_articulos().getValueAt(row, 1).toString().compareTo("")==0;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (!empty){
						model.setRowCount(model.getRowCount()+1);
						row=model.getRowCount()-1;
					}
					boolean selected=false;//this.isSelected((String)results[i][0]);
					frame.getJTable_articulos().setValueAt(selected, row, 0);
					
					for (int j=0;j<results[0].length;j++){
						frame.getJTable_articulos().setValueAt(results[i][j].toString(), row, j+1);	
					}
					
				}
			}
			if (results.length>=1){
				int qty=results.length;
				if (frame.get_chk_autofoco().isSelected()){
					frame.getJSplitPane().setDividerLocation(90);
					int row=frame.getJTable1().getSelectedRow();
					int max=160;
					JTable table=frame.getJTable1();
					 JViewport viewport = (JViewport)table.getParent();

					    // This rectangle is relative to the table where the
					    // northwest corner of cell (0,0) is always (0,0).
					    java.awt.Rectangle rect = table.getCellRect(row, 0, true);

					// The location of the viewport relative to the table
					 java.awt.Point pt = viewport.getViewPosition();
				    // Translate the cell location so that it is relative
					// to the view, assuming the northwest corner of the
				    // view is (0,0)
				    rect.setLocation(rect.x-pt.x, rect.y-pt.y);
				    // Scroll the area into view
					 viewport.scrollRectToVisible(rect);
					    
					
						
				}
				frame.get_txt_equivalencias().setText(""+qty);
				frame.get_txt_equivalencias().setBackground(Color.red);
			}else{
				if (frame.get_chk_autofoco().isSelected()){
					frame.getJSplitPane().setDividerLocation(160);	
				}
				
				frame.get_txt_equivalencias().setText("0");
				frame.get_txt_equivalencias().setBackground(Color.gray);
				
			}
		}
	 
	 public void cargar_proveedor(){
		this.evaluarProveedor(frame.get_txt_idproveedor()); 
	 }
	 
	 public void fillExtra(String idarticulo) {
			Object[][] results = data.getData(idarticulo);
			frame.get_txt_articulo().setText("");
			frame.get_txt_articulo_descripcion().setText("");
			frame.get_txt_articulo_stock().setText("");
			if (results != null) {
				if (results.length > 0) {
					Convertidor cv=new Convertidor();
					String _articulo = (String) results[0][0];
					String _descripcion = (String) results[0][1];
					String _linea = (String) results[0][2];
					String _stock = (String) results[0][3];
					String _suspendidov = (String) results[0][4];
					String _actualizacion = (String) results[0][5];
					String _publico= (String) results[0][8];
					String _costo= (String) results[0][9];
					frame.get_txt_articulo().setText(_articulo);
					frame.get_txt_articulo_descripcion().setText(_descripcion);
					frame.get_txt_articulo_stock().setText(_stock);
					frame.get_txt_articulo_linea().setText(_linea);
					Object[][] tmp=new Object[][]{{false,idarticulo,_descripcion,_linea,_costo,_stock}};
					crearTablaArticulos(tmp);
					this.cargar_equivalencias(idarticulo);
					if (_suspendidov.compareTo("1")==0){
						frame.get_txt_articulo_bloqueado().setText("BLOQUEADO");
						frame.get_txt_articulo_bloqueado().setBackground(Color.red);
					}else{
						frame.get_txt_articulo_bloqueado().setText("HABILITADO");
						frame.get_txt_articulo_bloqueado().setBackground(Color.green);
					}
					
					if (_actualizacion.compareTo("")==0){
						frame.get_txt_articulo_actualizacion().setBackground(Color.red);
						_actualizacion="SIN FECHA";
					}else{
						if (this.eval_venc(_actualizacion)){
							frame.get_txt_articulo_actualizacion().setBackground(Color.green);
						}else{
							frame.get_txt_articulo_actualizacion().setBackground(Color.red);
						}	
					}
					frame.get_txt_articulo_actualizacion().setText(_actualizacion);
					
					double _pedido=data.getPedidoCantidad(idarticulo);
					double stock=new Double(_stock);
					frame.get_txt_articulo_pedido().setText(cv.getMoney(_pedido, 2));
				}
			}
		}
	 
	 
	 public void evaluate_informacion(JTextArea txa) {
			cambios = true;
			String value = "";
			value = txa.getText();
			frame.get_txt_idvendedor().requestFocusInWindow();
	}
	 
	 private boolean eval_venc(String fecha){
			boolean ok=false;
			if (fecha.compareTo("")!=0){
			Calendar _fecha_actualizacion=Calendar.getInstance();
			Date date=null;
			DateFormat formatter;
			Locale locale = Locale.getDefault();
			try {
			   	formatter = new SimpleDateFormat("dd-MM-yyyy", locale);
			    date = (Date)formatter.parse(fecha);
			        
			    }
			    catch(Exception e){
			        	
			    }
			    long diferencia=1;
			    if (date!=null){
			    	_fecha_actualizacion.setTime(date);
			    	Date today=new Date();
					java.util.GregorianCalendar _fecha_limite=new java.util.GregorianCalendar();
					_fecha_limite.setTime(today);
					//_today.roll(Calendar.DAY_OF_YEAR, days);
					_fecha_limite.add(Calendar.DAY_OF_YEAR, -30);
					
					Calendar now=Calendar.getInstance();
					_fecha_actualizacion.add(Calendar.DATE, 27);
					ok=_fecha_limite.before(_fecha_actualizacion);
				//	System.out.println(now.getTime()+" "+cal.getTime());
			    }
				//System.out.println("dif "+c+" "+diferencia);
			}else {
				//"Fecha Nula"
			}
			return ok;
		}

	 
	 public void Enviar() {
			this.emailFrom=data.getParametroServer("email");
			this.password=data.getParametroServer("email_pass");
			this.email_asunto=data.getParametroServer("email_asunto");
			if (emailFrom.compareTo("")!=0 & password.compareTo("")!=0){
				String newline = System.getProperty("line.separator");
				String idpedido = frame.get_txt_idpedido().getText();
				if (data.existe(idpedido)) {
					String emailTo = frame.get_txt_email().getText();
					if (emailTo.compareTo("") != 0) {
						String idtransporte = frame.get_txt_idtransporte().getText();
						if (this.Transporte.existe(idtransporte)) {
							
								boolean error = this._guardar();
								if (!error) {
									String cuenta = frame.get_txt_idproveedor().getText();
									String cliente_descripcion = frame.get_txt_descripcion().getText();
									String descripcion = frame.get_txt_descripcion_pedido().getText();
									String fecha = frame.get_txt_fecha().getText();
									String emailto = frame.get_txt_email().getText();

									String empresa = data.getNombreEmpresa();
									String telefono = data.getTelefonoEmpresa();
									String _email = data.getEmail();
									String transporte = frame.get_txt_transporte_descripcion().getText();
									String info = frame.get_txt_observacion().getText();
									String nombre = frame.get_txt_descripcion().getText();
									if (email != null) {
										email.dispose();
									}
									String _mensaje = "Sr/es "
											+ nombre
											+ ": Adjuntamos un Pedido en Formato Excel.";
									_mensaje += newline;
									_mensaje += info;
									_mensaje += newline;
									_mensaje += "Por Favor comuniquese con nosotros para confirmar disponibilidad y envio.";
									_mensaje += newline;
									_mensaje += "Transporte Preferido para el despacho: "
											+ transporte;
									_mensaje += newline;
									_mensaje += "Muchas Gracias.";
									_mensaje += newline;
									_mensaje += "Saluda Atte. "
											+ frame.get_txt_vendedor_descripcion()
													.getText();
									_mensaje += newline;
									_mensaje += newline;
									_mensaje += empresa;
									_mensaje += newline;
									_mensaje += telefono;
									_mensaje += newline;
									_mensaje += _email;
									_mensaje += newline;

									email = new _Email();
									email.setName(_Interface._email);
									email
											.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
									email.addWindowListener(this.getConstructor()
											.getWindowListener());
									this.centrar_frame(email);
									email.get_txt_emailfrom().setText(emailFrom);
									email.get_txt_emailTo().setText(emailto);
									if (email_asunto.compareTo("")==0){
										email_asunto="Pedido de Mercaderia";
									}
									String asunto = this.email_asunto;
									asunto+=" "+frame.get_txt_idpedido().getText();
									email.get_txt_asunto().setText(asunto);
									email.get_txt_mensaje().setText(_mensaje);
									email.get_btn_enviar().setActionCommand(
											_Interface._btn_enviar_email);
									email.get_btn_enviar().addActionListener(
											this.getConstructor().getActionListener());
									email.get_btn_salir().setActionCommand(
											_Interface._btn_close_email);
									email.get_btn_salir().addActionListener(
											this.getConstructor().getActionListener());
									email.setVisible(true);

								}
							
						}else{
							
							error("Debe Configurar El Transporte en el Pedido en Envio/Direccion");
							frame.getJTabbedPane().setSelectedIndex(0);
							frame.get_txt_idtransporte().requestFocusInWindow();
						}
					}else{
						error("Debe Configurar El email en el Pedido en Envio/Direccion");
						frame.getJTabbedPane().setSelectedIndex(0);
						frame.get_txt_email().requestFocusInWindow();
					}
				}

			}else{
				error("Deben Configurarse Los Parametros de Sistema de E-mail. Consulte a Sistemas");
			}
		}

	 
	 public void enviar_email(){
			String to=email.get_txt_emailTo().getText();
			String asunto=email.get_txt_asunto().getText();
			String mensaje=email.get_txt_mensaje().getText();
			if (to.compareTo("")!=0){
				if (asunto.compareTo("")!=0){
					if (mensaje.compareTo("")!=0){
						if (confirmar(
								"Confirme para enviar el email con el pedido a ("
										+ to + "):", 3)) {
							this.goEnviar();	
						}else{
							error("OPERACION CANCELADA");
						}
						
					}else{
						error("El mensaje no puede ser nulo");
					}
				}else{
					error("El asunto no puede ser nulo");
				}
			}
			else{
				error("Complete la direccion de destino para enviar el email");
			}
		}
		public void goEnviar() {

			this.createEmailTimer();
			SwingWorker worker = null;
			if (worker == null) {
				worker = new SwingWorker() {
					public Object construct() {
						return new _taskEnviar();
					}
				};
			}
			if (Timer != null) {
				Timer.start();
			}
			worker.start();
		}

		class _taskEnviar {
			_taskEnviar() {
				_taskworkEnviar();
			}
		}

		private Properties getProperties() {

			Properties props = new Properties();
			props.setProperty("mail.smtp.host", "smtp.gmail.com");
			props.setProperty("mail.smtp.starttls.enable", "true");
			props.setProperty("mail.smtp.port", "587");
			props.setProperty("mail.smtp.user", emailFrom);
			props.setProperty("mail.smtp.auth", "true");
			return props;
		}

		public Message getMessage2(Session session, String emailTo, String file)
				throws Exception {
			MimeMessage msg = new MimeMessage(session);

			msg.setFrom(new InternetAddress(emailFrom));
			InternetAddress[] address = { new InternetAddress(emailTo) };
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject(email.get_txt_asunto().getText());

			MimeBodyPart mbp1 = new MimeBodyPart();

			String _mensaje = this.email.get_txt_mensaje().getText();
			mbp1.setText(_mensaje);

			// create the second message part
			MimeBodyPart mbp2 = new MimeBodyPart();

			// attach the file to the message

			File f = new File(file);
			FileDataSource fds = new FileDataSource(f.getAbsoluteFile());
			mbp2.setDataHandler(new DataHandler(fds));
			mbp2.setFileName(fds.getName());

			// create the Multipart and add its parts to it
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(mbp1);
			mp.addBodyPart(mbp2);

			// add the Multipart to the message
			System.out.println("Message Content null?" + mp);
			msg.setContent(mp);

			// set the Date: header
			msg.setSentDate(new Date());

			// send the message
			return msg;
		}

		public boolean preparar_reporte() {
			String emailTo = frame.get_txt_email().getText();
			estado = "Preparando Reporte";
							email.getJProgressBar().setIndeterminate(true);
							String idcomprobante = frame.get_txt_idpedido()
									.getText();

							String idpedido = frame.get_txt_idpedido().getText();
							String empresa = data.getNombreEmpresa();
							String telefono = data.getTelefonoEmpresa();
							String email = data.getEmail();
							String info = frame.get_txt_observacion().getText();
							aplicacion.herramientas.java.ireport.logic._Logic logic = new aplicacion.herramientas.java.ireport.logic._Logic();
							logic.setConstructor(this.getConstructor());
							HashMap param = new HashMap();
							param.put("idpedido", idpedido);
							param.put("empresa", empresa);
							param.put("telefono", telefono);
							param.put("email", email);
							//param.put("observaciones", info);
							
							String output = idcomprobante + ".xls";
							File fx = new File(output);
							if (fx.exists()) {
								try {
									fx.delete();
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							boolean ok=true;
							try {
								logic.excelReport("pedidoproveedor.jasper", param, output);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								ok=false;
							}
							return ok;
		}
	


		public void _taskworkEnviar() {
			boolean ok=this.preparar_reporte();
			estado = "Enviando EMail";
			boolean error = false;
			email.getJProgressBar().setIndeterminate(true);
			Properties props = this.getProperties();
			Session session = Session.getDefaultInstance(props);
			Message message = null;
			// message.setFlag(Flag, set)
			String file = frame.get_txt_idpedido().getText() + ".xls";
			String emailTo = frame.get_txt_email().getText();
			try {
				message = this.getMessage2(session, emailTo, file);
				message.addHeader("Disposition-Notification-To", emailFrom);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();

			}
			try {
				if (message != null) {
					Transport t = session.getTransport("smtp");
					t.connect(emailFrom, password);
					t.sendMessage(message, message.getAllRecipients());
					t.close();
				}
			} catch (NoSuchProviderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				error = true;

			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				error = true;
			}
			done = true;
			if (error) {
				error("Error enviando email. Guarde el Error Para poder examinarlo en su computadora");
			} else {
				aviso("El E-Mail Se Envio Correctamente. ");
				String idpedido=frame.get_txt_idpedido().getText();
				boolean oke=data.update_seguimiento(idpedido, true, "Enviado");
				if (oke){
					aviso("El Pedido se marco como enviado.");
					
				}else{
					error("Error intentando marcar el Pedido como enviado.");
				}
				 
				dispose_email();
				if (oke){
					exit();
				}
			}

		}


		public void dispose_email() {
			try {
				this.Timer.stop();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Timer = null;
			email.setVisible(false);
			email.dispose();

		}
		private aplicacion.herramientas.java.evaluadores.Transporte Transporte = null;

		public void initialize_Transporte() {
			Transporte = new aplicacion.herramientas.java.evaluadores.Transporte() {
				public void cargar(String codigo) {
					Object[][] results = this.getInfo(codigo);
					String descripcion = (String) results[0][1];
					frame.get_txt_transporte_descripcion().setText(descripcion);
					frame.get_txt_fecha_envio().requestFocusInWindow();
				}
			};
			Transporte.setConstructor(this.getConstructor());
		}

		public void reconnect_Transporte() {
			if (Transporte != null) {
				Transporte.setConstructor(this.getConstructor());
			}
		}

		public void BuscarTransporte(JTextField tx) {
			Transporte.Buscar(tx);
		}

		public void BuscarTransporte() {
			Transporte.Buscar(frame.get_txt_idtransporte());
		}

		public void buscarTransporte(JTextField tx) {
			Transporte.buscar(tx);
		}

		public void evaluarTransporte(JTextField tx) {
		Transporte.evaluate(tx);
		}
		
		private aplicacion.herramientas.java.evaluadores.Vendedor Vendedor = null;

		public void initialize_Vendedor() {
			Vendedor = new aplicacion.herramientas.java.evaluadores.Vendedor() {
				public void cargar(String codigo) {
					Object[][] results = this.getInfo(codigo);
					String descripcion = (String) results[0][1];
					String cod = (String) results[0][0];
					frame.get_txt_idvendedor().setText(cod);
					frame.get_txt_vendedor_descripcion().setText(descripcion);
					//transfer_focus_articulos();

				}
			};
			Vendedor.setConstructor(this.getConstructor());
		}

		public void reconnect_Vendedor() {
			if (Vendedor != null) {
				Vendedor.setConstructor(this.getConstructor());
			}

		}

		public void BuscarVendedor(JTextField tx) {
			Vendedor.Buscar(tx);
		}

		public void BuscarVendedor() {
			Vendedor.Buscar(frame.get_txt_idvendedor());
		}

		public void buscarVendedor(JTextField tx) {
			Vendedor.buscar(tx);
		}

		public void evaluarVendedor(JTextField tx) {
			cambios = true;
			tx.setText(tx.getText().replaceAll(" ", ""));
			Vendedor.evaluate(tx);
		}
		
		public void cancelar_tarea(){
			
		}
}
