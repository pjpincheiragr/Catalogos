package aplicacion.inventario.linea.logic;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.*;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

import aplicacion.compras.carga.pedido.logic.TableCheckCellRenderer;
import aplicacion.compras.carga.pedido.logic.TableItemColorCellRenderer;
import aplicacion.herramientas.java.Crono;
import aplicacion.herramientas.java.Convertidor;
import aplicacion.herramientas.java.launcher.logic.SwingWorker;
import aplicacion.herramientas.java.sortableselector.logic.Filtro;
import aplicacion.herramientas.java.sortableselector.logic.columna;
import aplicacion.herramientas.java.table.CellEditor;
import aplicacion.herramientas.java.table.CheckBoxCellEditor;
import aplicacion.herramientas.java.table.Column;
import aplicacion.herramientas.java.table.CustomTable;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Data;
import aplicacion.modelo.logic.Logic;
import aplicacion.inventario.linea.constructor.*;
import aplicacion.inventario.linea.gui._Etiquetas;
import aplicacion.inventario.linea.gui._Frame;
import aplicacion.inventario.linea.interfaces.*;
import aplicacion.inventario.linea.logic._Data;
import aplicacion.inventario.linea.logic.MoneyRenderer;
import aplicacion.inventario.linea.logic.DateRenderer;
import aplicacion.inventario.linea.interfaces._Interface;


public class _Logic extends Logic {
	private _Frame frame;
	private _Data data;
	private Object[][] memoria = null;
	private aplicacion.inventario.politica.constructor._Constructor editor_politica = null;
	private aplicacion.herramientas.java.visualselector.constructor._Constructor vSelector = null;
	private aplicacion.herramientas.java.importar.constructor._Constructor importar = null;
	private aplicacion.herramientas.java.exportar.constructor._Constructor exportar = null;
	
	private aplicacion.herramientas.java.visualizadores.Articulo vArticulo = null;
	private aplicacion.herramientas.java.buscadores.Articulo bArticulo = null;
	// variables de tareas swingwork
	
	private String estado="";
	private int current;
	private int lenght;
	private boolean debug,done,canceled,override;
	private Timer Timer;  //  @jve:decl-index=0:
	private Crono crono;
	int errors=0;
	private boolean cambios=false;
	private _Etiquetas etiquetas = null;
	private boolean[] marquer;

	public void setFrame(JFrame _frame) {
		this.frame = (_Frame) _frame;
		super.setFrame(_frame);
	}

	public void setData(Data _data) {
		this.data = (_Data) _data;
		super.setData(_data);
	}


	private void create_table(Object[][] results) {
		CustomTable table = new CustomTable();
		Column col = null;

		col = new Column();
		col.setName("Linea");
		col.setWidth(180);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("Valor");
		col.setWidth(70);
		col.setEditable(false);
		col.setCellRenderer(new MoneyRenderer());
		col.setClass(Double.class);
		table.addColumn(col);

		col = new Column();
		col.setName("Items");
		col.setWidth(70);
		col.setEditable(false);
		col.setCellRenderer(new MoneyRenderer());
		col.setClass(Double.class);
		table.addColumn(col);
		
		col = new Column();
		col.setName("idproveedor");
		col.setWidth(80);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);
		
		col = new Column();
		col.setName("nombre");
		col.setWidth(120);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("ventas");
		col.setWidth(66);
		col.setEditable(false);
		col.setClass(Double.class);
		col.setCellRenderer(new MoneyRenderer());
		table.addColumn(col);
		
		col = new Column();
		col.setName("compras");
		col.setWidth(66);
		col.setEditable(false);
		col.setClass(Double.class);
		col.setCellRenderer(new MoneyRenderer());
		table.addColumn(col);

		col = new Column();
		col.setName("movimientos");
		col.setWidth(70);
		col.setEditable(false);
		col.setClass(Double.class);
		col.setCellRenderer(new MoneyRenderer());
		table.addColumn(col);
		
		col = new Column();
		col.setName("operaciones");
		col.setWidth(70);
		col.setEditable(false);
		col.setClass(Double.class);
		col.setCellRenderer(new MoneyRenderer());
		table.addColumn(col);
		
		col = new Column();
		col.setName("actualizacion");
		col.setWidth(70);
		col.setEditable(false);
		col.setClass(Date.class);
		col.setCellRenderer(new DateRenderer());
		table.addColumn(col);
		
		col = new Column();
		col.setName("control");
		col.setWidth(70);
		col.setEditable(false);
		col.setCellRenderer(new DateRenderer());
		col.setClass(Date.class);
		table.addColumn(col);
		
		col = new Column();
		col.setName("$Ventas");
		col.setWidth(66);
		col.setEditable(false);
		col.setClass(Double.class);
		col.setCellRenderer(new MoneyRenderer());
		table.addColumn(col);
		
		col = new Column();
		col.setName("%Ventas");
		col.setWidth(66);
		col.setEditable(false);
		col.setClass(Double.class);
		col.setCellRenderer(new MoneyRenderer());
		table.addColumn(col);
		
		col = new Column();
		col.setName("$Compras");
		col.setWidth(66);
		col.setEditable(false);
		col.setClass(Double.class);
		col.setCellRenderer(new MoneyRenderer());
		table.addColumn(col);
		
		col = new Column();
		col.setName("%Compras");
		col.setWidth(66);
		col.setEditable(false);
		col.setClass(Double.class);
		col.setCellRenderer(new MoneyRenderer());
		table.addColumn(col);
		
		col = new Column();
		col.setName("%Stock");
		col.setWidth(66);
		col.setEditable(false);
		col.setClass(Double.class);
		col.setCellRenderer(new MoneyRenderer());
		table.addColumn(col);
		
		col = new Column();
		col.setName("$Renta");
		col.setWidth(66);
		col.setEditable(false);
		col.setClass(Double.class);
		col.setCellRenderer(new MoneyRenderer());
		table.addColumn(col);
		
		col = new Column();
		col.setName("%Renta");
		col.setWidth(66);
		col.setEditable(false);
		col.setClass(Double.class);
		col.setCellRenderer(new MoneyRenderer());
		table.addColumn(col);
		
		results=this.process_data(results);
		table.setData(results);
		Font fuente = new Font("Arial", Font.PLAIN, 9);
		table.setHeaderFont(fuente);
		table.setFont(fuente);
		table.build();
		table.fillData();
		JTable _table = table.getTable();
		_table.setName(_Interface._table);
		_table.setRowSelectionAllowed(true);
		_table.setColumnSelectionAllowed(false);
		_table.addMouseListener(this.getConstructor().getMouseListener());
		_table.addKeyListener(this.getConstructor().getKeyListener());
		_table.getTableHeader().addMouseListener(this.getConstructor().getMouseListener());
		frame.setJTable(table.getTable());
	}
	
	 
	public int getDays(){
		String hasta=new Convertidor().getDateWithFormat("dd-MM-yyyy");
		String desde=this.getDaysRoll(hasta, -90);
		int dif=new Convertidor().getDateDiff(desde, hasta);
		return dif;
	}
	/**
	 * chk|idarticulo|descripcion|costo|critico|proyectado|stock|ventas|pedir|total
	 * @param aux
	 * @return
	 */
	private Object[][] procesar_items_linea(Object[][] aux) {
		double dias=30;
		double dias_critico=7;
		double unidades_critico=0;
		double importe_critico=0;
		double unidades_proyeccion=0;
		double importe_proyeccion=0;
		try {
			dias_critico=new Integer(frame.get_txt_critico().getText());
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			dias=new Integer(frame.get_txt_proyeccion().getText());
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String hasta=frame.get_txt_pedido_hasta().getText();
		String desde=frame.get_txt_pedido_desde().getText();
		int dif=new Convertidor().getDateDiff(desde, hasta);
		double sum_linea = 0.0;
		double sum_linea_items = 0.0;
		// String linea=frame.get_txt_linea().getText();
		Convertidor C = new Convertidor();
		Object[][] tmp = new Object[aux.length][10];
		lenght=aux.length;
		
	
		for (int i = 0; i < aux.length; i++) {
			
			
			String art = (String) aux[i][0];
			String desc = (String) aux[i][1];
			String cst = (String) aux[i][2];
			String stk = (String) aux[i][3];
			String ven = (String) aux[i][4];
			

			current=i;
			estado="cargando item ";
			
			double stock = 0.0;
			double pedido = 0.0;
			
			double ventas = 0.0;
			
			
			boolean b = false;

			try {
				ventas = new Double(ven.replace(",", ""));
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

			

			
			System.out.println("dias?"+dif);
			
			double promedio=ventas/new Double(dif);
			double proyeccion=promedio*new Double(dias);
			proyeccion=Math.round(proyeccion);
			double critico=promedio*new Double(dias_critico);
			critico=Math.round(critico);
			
			

			if (critico<0){
				critico=0;
			}
			try {
				if (stock <= 0) {
					stock = 0.0;
				}
				if (proyeccion > stock) {
					pedido = proyeccion - stock;
				} else {
					pedido = 0.0;
				}

			} catch (Exception e) {
				System.out.println("Error. load_items_values() calculo> "
						+ e.getMessage());
			}

			System.out.println("procesando " + art + "> pedido" + pedido
					);


			
			b=false;
			
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
			if (b) {
				tmp[i][0] = "1";
			} else {
				tmp[i][0] = "0";
			}

			tmp[i][1] = art;
			tmp[i][2] = desc;
			tmp[i][3] = costo;
			tmp[i][4] = critico;
			tmp[i][5] = proyeccion;
			tmp[i][6] = stock;
			tmp[i][7] = ventas;
			tmp[i][8] = pedido;
			tmp[i][9] = tot;
			if ((critico-stock)>0){
				unidades_critico+=critico-stock;
				importe_critico+=(critico-stock)*costo;
			}
			if ((proyeccion-stock)>0){
				unidades_proyeccion+=proyeccion-stock;
				importe_proyeccion+=(proyeccion-stock)*costo;
			}
		}
	
		frame.get_txt_unidades_critico().setText(C.getMoney(unidades_critico, 2));
		frame.get_txt_unidades_proyeccion().setText(C.getMoney(unidades_proyeccion, 2));
		frame.get_txt_importe_critico().setText(C.getMoney(importe_critico, 2));
		frame.get_txt_importe_proyeccion().setText(C.getMoney(importe_proyeccion, 2));
		done = true;
		return tmp;
		
	}
	
	public void create_table_items(Object[][] results,boolean editable){
		CustomTable table = this.crearTablaDeItems(editable);
		results = this.procesar_items_linea(results);
		table.setData(results);
		table.build();
		table.fillData();
		frame.setJTable2(table.getTable());
		done=true;
	}
	
	public void cargar_pedido(){
		int row=frame.getJTable1().getSelectedRow();
		
		String linea=frame.getJTable1().getValueAt(row, 0).toString();
		this.mostrar_linea(linea);
	}
	
	private void mostrar_linea(String linea) {
		
		int row=frame.getJTable1().getSelectedRow();
		String idproveedor = frame.getJTable1().getValueAt(row, 3).toString();
		estado="cargando " + linea ;
		String desde=frame.get_txt_pedido_desde().getText();
		String hasta=frame.get_txt_pedido_hasta().getText();
		String q =data.getLineaItemsCreation(idproveedor, linea, desde, hasta);
		System.out.println(q);
		Object[][] results = data.getResults(q);
		if (results!=null){
			
				boolean editable=linea.compareTo("ARTICULOS VARIOS")==0;
				if (editable & results.length<=0){
					results=new Object[1][11];
				}
				if (javax.swing.SwingUtilities.isEventDispatchThread()){
					this.create_table_items(results, editable);
				}else{
					final boolean _editable=editable;
					final Object[][] _results=results;
					Runnable _execute = new Runnable() {
				        public void run() {
				        	create_table_items(_results, _editable);    	
				        }
					};
					invokeLater(_execute);	
				}
				
				
			
		}else{
			done=true;
		}
	}
	
	public void cargar_informacion_para_pedido(){
		String hasta=new Convertidor().getDateWithFormat("dd-MM-yyyy");
		String desde=this.getDaysRoll(hasta, -90);
		frame.get_txt_pedido_desde().setText(desde);
		frame.get_txt_pedido_hasta().setText(hasta);
		frame.get_txt_critico().setText("7");
		frame.get_txt_proyeccion().setText("30");
		frame.setJTable2(null);
		frame.get_txt_unidades_critico().setText("");
		frame.get_txt_unidades_proyeccion().setText("");
		frame.get_txt_importe_critico().setText("");
		frame.get_txt_importe_proyeccion().setText("");
		
	}
	
	
	public void nuevo_pedido(){
		int selections=0;
		int row=frame.getJTable1().getSelectedRow();
		String idproveedor=frame.getJTable1().getValueAt(row, 3).toString();
		for (int i=0;i<frame.getJTable2().getRowCount();i++){
			boolean selected=(Boolean) frame.getJTable2().getValueAt(i,0);
			if (selected)selections++;
		}
		if (selections>0){
			
			aplicacion.compras.pedidoe.constructor._Constructor
			pedido=new aplicacion.compras.pedidoe.constructor._Constructor();
			pedido.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler());
			pedido.setParameter(_parametros.iduser, this.getConstructor().getIduser());
			pedido.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
			pedido.build(this.getConstructor());
			pedido.init();
			aplicacion.compras.pedidoe.gui._Frame 
			_frame=(aplicacion.compras.pedidoe.gui._Frame) pedido.getFrame();
			aplicacion.compras.pedidoe.logic._Logic
				logic =(aplicacion.compras.pedidoe.logic._Logic) pedido.getLogic();
			logic.nuevo();
			logic.evaluar_numero(_frame.get_txt_idpedido());
			_frame.get_txt_idcliente().setText(idproveedor);
			logic.evaluarCliente(_frame.get_txt_idcliente());
			logic.transfer_focus_articulos();
			for (int i=0;i<frame.getJTable2().getRowCount();i++){
				boolean selected=(Boolean) frame.getJTable2().getValueAt(i,0);
				if (selected){
					String idarticulo=frame.getJTable2().getValueAt(i,1).toString();
					String descripcion=frame.getJTable2().getValueAt(i,2).toString();
					String cantidad=frame.getJTable2().getValueAt(i,8).toString();
					String[] selection=new String[]{idarticulo,descripcion,cantidad};
					logic.agregar2(selection);	
				}
				
			}
			
		}else{
			aviso("Seleccione Los Items Que quiera incluir en el pedido especial");
		}
	}
	
	
	
	
	private void create_table_detalle(Object[][] results) {
		CustomTable table = new CustomTable();
		Column col = null;

		col = new Column();
		col.setName("Linea");
		col.setWidth(180);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("Valor");
		col.setWidth(70);
		col.setEditable(false);
		col.setCellRenderer(new MoneyRenderer());
		col.setClass(Double.class);
		table.addColumn(col);

		col = new Column();
		col.setName("Items");
		col.setWidth(70);
		col.setEditable(false);
		col.setCellRenderer(new MoneyRenderer());
		col.setClass(Double.class);
		table.addColumn(col);
		
		col = new Column();
		col.setName("idproveedor");
		col.setWidth(80);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);
		
		col = new Column();
		col.setName("nombre");
		col.setWidth(120);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("ventas");
		col.setWidth(66);
		col.setEditable(false);
		col.setClass(Double.class);
		col.setCellRenderer(new MoneyRenderer());
		table.addColumn(col);
		
		col = new Column();
		col.setName("compras");
		col.setWidth(66);
		col.setEditable(false);
		col.setClass(Double.class);
		col.setCellRenderer(new MoneyRenderer());
		table.addColumn(col);

		col = new Column();
		col.setName("movimientos");
		col.setWidth(70);
		col.setEditable(false);
		col.setClass(Double.class);
		col.setCellRenderer(new MoneyRenderer());
		table.addColumn(col);
		
		col = new Column();
		col.setName("operaciones");
		col.setWidth(70);
		col.setEditable(false);
		col.setClass(Double.class);
		col.setCellRenderer(new MoneyRenderer());
		table.addColumn(col);
		
		col = new Column();
		col.setName("actualizacion");
		col.setWidth(70);
		col.setEditable(false);
		col.setClass(Date.class);
		col.setCellRenderer(new DateRenderer());
		table.addColumn(col);
		
		col = new Column();
		col.setName("control");
		col.setWidth(70);
		col.setEditable(false);
		col.setCellRenderer(new DateRenderer());
		col.setClass(Date.class);
		table.addColumn(col);
		
		col = new Column();
		col.setName("$Ventas");
		col.setWidth(66);
		col.setEditable(false);
		col.setClass(Double.class);
		col.setCellRenderer(new MoneyRenderer());
		table.addColumn(col);
		
		col = new Column();
		col.setName("%Ventas");
		col.setWidth(66);
		col.setEditable(false);
		col.setClass(Double.class);
		col.setCellRenderer(new MoneyRenderer());
		table.addColumn(col);
		
		col = new Column();
		col.setName("$Compras");
		col.setWidth(66);
		col.setEditable(false);
		col.setClass(Double.class);
		col.setCellRenderer(new MoneyRenderer());
		table.addColumn(col);
		
		col = new Column();
		col.setName("%Compras");
		col.setWidth(66);
		col.setEditable(false);
		col.setClass(Double.class);
		col.setCellRenderer(new MoneyRenderer());
		table.addColumn(col);
		
		col = new Column();
		col.setName("%Stock");
		col.setWidth(66);
		col.setEditable(false);
		col.setClass(Double.class);
		col.setCellRenderer(new MoneyRenderer());
		table.addColumn(col);
		
		col = new Column();
		col.setName("$Renta");
		col.setWidth(66);
		col.setEditable(false);
		col.setClass(Double.class);
		col.setCellRenderer(new MoneyRenderer());
		table.addColumn(col);
		
		col = new Column();
		col.setName("%Renta");
		col.setWidth(66);
		col.setEditable(false);
		col.setClass(Double.class);
		col.setCellRenderer(new MoneyRenderer());
		table.addColumn(col);
		
		results=this.process_data_simple(results);
		table.setData(results);
		Font fuente = new Font("Arial", Font.PLAIN, 9);
		table.setHeaderFont(fuente);
		table.setFont(fuente);
		table.build();
		table.fillData();
		JTable _table = table.getTable();
		_table.setName(_Interface._table_detalle);

		_table.addMouseListener(this.getConstructor().getMouseListener());
		_table.addKeyListener(this.getConstructor().getKeyListener());
		_table.getTableHeader().addMouseListener(this.getConstructor().getMouseListener());
		frame.setJTable1(table.getTable());
		this.select_more_relevant();
	}
	
	private void create_table_actualizacion(Object[][] results) {
		CustomTable table = new CustomTable();
		Column col = null;

		col = new Column();
		col.setName("Linea");
		col.setWidth(180);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("Valor");
		col.setWidth(70);
		col.setEditable(false);
		col.setCellRenderer(new MoneyRenderer());
		col.setClass(Double.class);
		table.addColumn(col);

		col = new Column();
		col.setName("Items");
		col.setWidth(70);
		col.setEditable(false);
		col.setCellRenderer(new MoneyRenderer());
		col.setClass(Double.class);
		table.addColumn(col);
		
		col = new Column();
		col.setName("idproveedor");
		col.setWidth(80);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);
		
		col = new Column();
		col.setName("nombre");
		col.setWidth(120);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("ventas");
		col.setWidth(66);
		col.setEditable(false);
		col.setClass(Double.class);
		col.setCellRenderer(new MoneyRenderer());
		table.addColumn(col);
		
		col = new Column();
		col.setName("compras");
		col.setWidth(66);
		col.setEditable(false);
		col.setClass(Double.class);
		col.setCellRenderer(new MoneyRenderer());
		table.addColumn(col);

		col = new Column();
		col.setName("movimientos");
		col.setWidth(70);
		col.setEditable(false);
		col.setClass(Double.class);
		col.setCellRenderer(new MoneyRenderer());
		table.addColumn(col);
		
		col = new Column();
		col.setName("operaciones");
		col.setWidth(70);
		col.setEditable(false);
		col.setClass(Double.class);
		col.setCellRenderer(new MoneyRenderer());
		table.addColumn(col);
		
		col = new Column();
		col.setName("actualizacion");
		col.setWidth(70);
		col.setEditable(false);
		col.setClass(Date.class);
		col.setCellRenderer(new DateRenderer());
		table.addColumn(col);
		
		col = new Column();
		col.setName("control");
		col.setWidth(70);
		col.setEditable(false);
		col.setCellRenderer(new DateRenderer());
		col.setClass(Date.class);
		table.addColumn(col);
		
		col = new Column();
		col.setName("$Ventas");
		col.setWidth(66);
		col.setEditable(false);
		col.setClass(Double.class);
		col.setCellRenderer(new MoneyRenderer());
		table.addColumn(col);
		
		col = new Column();
		col.setName("%Ventas");
		col.setWidth(66);
		col.setEditable(false);
		col.setClass(Double.class);
		col.setCellRenderer(new MoneyRenderer());
		table.addColumn(col);
		
		col = new Column();
		col.setName("$Compras");
		col.setWidth(66);
		col.setEditable(false);
		col.setClass(Double.class);
		col.setCellRenderer(new MoneyRenderer());
		table.addColumn(col);
		
		col = new Column();
		col.setName("%Compras");
		col.setWidth(66);
		col.setEditable(false);
		col.setClass(Double.class);
		col.setCellRenderer(new MoneyRenderer());
		table.addColumn(col);
		
		col = new Column();
		col.setName("%Stock");
		col.setWidth(66);
		col.setEditable(false);
		col.setClass(Double.class);
		col.setCellRenderer(new MoneyRenderer());
		table.addColumn(col);
		
		col = new Column();
		col.setName("$Renta");
		col.setWidth(66);
		col.setEditable(false);
		col.setClass(Double.class);
		col.setCellRenderer(new MoneyRenderer());
		table.addColumn(col);
		
		col = new Column();
		col.setName("%Renta");
		col.setWidth(66);
		col.setEditable(false);
		col.setClass(Double.class);
		col.setCellRenderer(new MoneyRenderer());
		table.addColumn(col);
		
		results=this.process_data_simple(results);
		table.setData(results);
		Font fuente = new Font("Arial", Font.PLAIN, 9);
		table.setHeaderFont(fuente);
		table.setFont(fuente);
		table.build();
		table.fillData();
		JTable _table = table.getTable();
		_table.setName(_Interface._table_detalle);

		_table.addMouseListener(this.getConstructor().getMouseListener());
		_table.addKeyListener(this.getConstructor().getKeyListener());
		_table.getTableHeader().addMouseListener(this.getConstructor().getMouseListener());
		frame.setJTable3(table.getTable());
		this.select_more_relevant_actualizacion();
	}
	
	public void seleccionar(boolean b){
		for (int i=0;i<frame.getJTable2().getRowCount();i++){
			frame.getJTable2().setValueAt(b, i, 0);
		}
	}
	public void select_more_relevant(){
		
		String idproveedor=frame.get_txt_idproveedor().getText();
		boolean found=false;
		int i=0;
		while (i<frame.getJTable1().getRowCount() & !found){
			
			String _idproveedor=frame.getJTable1().getValueAt(i,3).toString();
			found=idproveedor.compareTo(_idproveedor)==0;
			if (!found){
				i++;	
			}
			
		}
		if (found){
			frame.getJTable1().changeSelection(i, 0, false,false);
		}
	}
public void select_more_relevant_actualizacion(){
		
		String idproveedor=frame.get_txt_idproveedor().getText();
		boolean found=false;
		int i=0;
		while (i<frame.getJTable3().getRowCount() & !found){
			
			String _idproveedor=frame.getJTable3().getValueAt(i,3).toString();
			found=idproveedor.compareTo(_idproveedor)==0;
			if (!found){
				i++;	
			}
			
		}
		if (found){
			frame.getJTable3().changeSelection(i, 0, false,false);
		}
	}
	public void clean() {
		frame.setJTable(null);
		frame.get_txt_detalle_items().setText("");
		frame.setJTable1(null);
		frame.setJTable2(null);
		frame.setJTable3(null);
		frame.get_txt_detalle_linea().setText("");
		frame.get_txt_detalle_valor().setText("");
		frame.get_txt_linea().setText("");
		frame.get_txt_lineas().setText("");
		frame.get_txt_renta().setText("");
		frame.get_txt_stock().setText("");
		frame.get_txt_ventas().setText("");
		frame.get_txt_fecha_desde().setText("");
		frame.get_txt_fecha_hasta().setText("");
		frame.get_txt_compras().setText("");
		frame.get_txt_critico().setText("");
		frame.get_txt_proyeccion().setText("");
		frame.get_txt_pedido_desde().setText("");
		frame.get_txt_pedido_hasta().setText("");
		frame.get_txt_proveedor_nombre().setText("");
		frame.get_txt_idproveedor().setText("");
		frame.get_txt_detalle_actualizacion().setText("");
		frame.get_txt_detalle_actualizacion_porcentaje().setText("");
		frame.get_txt_detalle_actualizacion_stock().setText("");
		frame.get_txt_detalle_compras().setText("");
		frame.get_txt_detalle_errores().setText("");
		frame.get_txt_detalle_items().setText("");
		frame.get_txt_detalle_items_linea().setText("");
		frame.get_txt_detalle_items_stock().setText("");
		frame.get_txt_detalle_linea().setText("");
		frame.get_txt_detalle_politica().setText("");
		frame.get_txt_detalle_valor().setText("");
		frame.get_txt_detalle_ventas().setText("");
		frame.get_txt_renta().setText("");
		frame.get_txt_stock().setText("");
		frame.get_txt_detalle_control().setText("");
		frame.get_txt_unidades_critico().setText("");
		frame.get_txt_unidades_critico().setText("");
		frame.get_txt_unidades_proyeccion().setText("");
		frame.get_txt_importe_critico().setText("");
		frame.get_txt_importe_proyeccion().setText("");
	}

	
	public void focus() {
		
	}

	


	
	
	
	
	private aplicacion.herramientas.java.ireport.constructor._Constructor reporte=null;
	public void reporte(){
		if (reporte!=null){
			 reporte.dispose();
		}
		
			reporte=new aplicacion.herramientas.java.ireport.constructor._Constructor();
			HashMap param=new HashMap();
			param.put("idcontrol","");
			
			reporte.setParameter(_parametros.LookAndFeel,this.getConstructor().getLookAndFeelTheme());
			reporte.setParameter(_parametros.connector,this.getConstructor().getConnectionHandler());
			reporte.setParameter(aplicacion.herramientas.java.ireport.interfaces._parametros.parametros, param);
			String[] options=new String[]{"Reporte de Ajustes","Reporte Control Completo"};
			int n=this.preguntar("Seleccione Reporte", "Seleccione El tipo de Reporte", options, options[0]);
			if (n==0){
				reporte.setParameter(aplicacion.herramientas.java.ireport.interfaces._parametros.reporte, "reporte_control.jasper");	
			}else{
				reporte.setParameter(aplicacion.herramientas.java.ireport.interfaces._parametros.reporte, "reporte_control_completo.jasper");
			}
			
			reporte.build(this.getConstructor());
			reporte.init();	
		
			
	}
	public void cargar() {
		this.goCargarValores();
	}

	private void transfer_focus(int row, int col) {
		frame.getJTable().changeSelection(row, col, false, false);
		frame.getJTable().editCellAt(row, col);
		frame.getJTable().transferFocus();
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
	
	public void getToday() {
		_Frame _frame = (_Frame) this._frame;
		_frame.get_txt_fecha_desde().setText(
		new Convertidor().getDateWithFormat("dd-MM-yyyy"));
		String hasta=new Convertidor().getDateWithFormat("dd-MM-yyyy");
		String desde=this.getDaysRoll(hasta, -365);
		frame.get_txt_fecha_desde().setText(desde);
		frame.get_txt_fecha_hasta().setText(hasta);
	}
	
	
	private aplicacion.inventario.articulo.constructor._Constructor articulo = null;
	public void goMa_Articulos(String idarticulo) {
		if (articulo != null) {
			articulo.dispose();
		}
		articulo = new aplicacion.inventario.articulo.constructor._Constructor();
		articulo.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme()
				);
		articulo.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler());
		articulo
				.setParameter(
						aplicacion.inventario.articulo.interfaces._parametros.idarticulo,
						idarticulo);
		articulo.build(this.getConstructor());
		articulo.init();
	}
	
	
	
	public void _taskworkCargarValores(){
		
			System.out.println("Aplicar Stock");
			String desde=frame.get_txt_fecha_desde().getText();
			String hasta=frame.get_txt_fecha_hasta().getText();
			String stock=frame.get_txt_stock().getText();
			errors=0;
			current=0;
			estado="Cargando Valores";
			
			String linea=frame.get_txt_linea().getText();
			final Object[][] results=data.getLineas(desde, hasta,stock,linea);
			Runnable _execute = new Runnable() {
				public void run(){
						create_table(results);	
				}
			};
			this.invokeLater(_execute);			
			done=true;
		
	}
	
	
	//metodos basicos de tareas swing
	public void createTimer(){
		crono=new Crono();
		crono.start();
		lenght=0;
		current=0;
		errors=0;
		done = false;
		canceled=false;
		Timer=new Timer(300, new ActionListener() {
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
	
	public void doCancel(){
		if (preguntar("confirmar","Cancela Tarea?")){
			this.canceled=true;
			
			
		}
	}
	
	
	
	public void aplicarStock(){
		if (!cambios){
			boolean ok=this.confirmar("Confirme para aplicar cambios al stock", 3);
			if (ok){
				this.goCargarValores();
			}	
		}else{
			aviso("Se detectaron cambios en el control. Antes de Aplicar los cambios de Stock. Debe guardar el control");
		}
		
	}
	
	public void goCargarValores() {
		frame.getJProgressBar().setIndeterminate(true);
		this.createTimer();
		SwingWorker worker=null;
			worker = new SwingWorker() {
				@Override
				public Object construct() {
					return new _taskCargarValores();
				}
			};
			if (Timer!=null) {
				Timer.start();
			}
			worker.start();
		}
	
	
	
	class _taskCargarValores {
		_taskCargarValores() {
			_taskworkCargarValores();
			}
	}
	
	public void evaluate_control(JTextField tx,int row){
		String valor=tx.getText();
		cambios=true;
		double control=0.0;
		boolean error=false;
		try {
			control=new Double(valor.replaceAll(",", ""));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error=true;
		}
		if (!error){
			if (control>=0){
				if (row<frame.getJTable().getRowCount()-1){
					String cant=frame.getJTable().getValueAt(row, 3).toString();
					double cantidad=0.0;
					try {
						cantidad=new Double(cant);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					double ajuste=control-cantidad;
					frame.getJTable().setValueAt(ajuste, row, 5);
					frame.getJTable().changeSelection(row+1, 4, false, false);
					frame.getJTable().editCellAt(row+1, 4);
					frame.getJTable().transferFocus();
				}
				
			}else{
				error("La cantidad controlada no puede ser menor a cero");
				tx.requestFocusInWindow();
			}
		}else{
			error("Error en cantidad, verifique. No puede ser menor a cero ");
			tx.requestFocusInWindow();
		}
	}
	public void imprimir_etiquetas() {
		
		LinkedList l = new LinkedList();
		try {
			etiquetas.getJTable().clearSelection();
			etiquetas.getJTable().getCellEditor().stopCellEditing();
		} catch (Exception e) {

		}
			etiquetas.getJTable().setEnabled(false);
			int etqs=0;
			for (int i = 0; i < etiquetas.getJTable().getRowCount(); i++) {
				boolean b = false;
				try {
					b = (Boolean) etiquetas.getJTable().getValueAt(i, 0);
				} catch (Exception e) {

				}
				if (b) etqs++;
			}
			if (etqs>0){
				if (confirmar("Confirme Para Imprimir Etiquetas",2)){
				data.beginTransaction();
				data.clearBatch();
				for (int i = 0; i < etiquetas.getJTable().getRowCount(); i++) {
					boolean b = false;
					try {
						b = (Boolean) etiquetas.getJTable().getValueAt(i, 0);
					} catch (Exception e) {

					}

					String idarticulo = "";
					String descripcion = "";
					String cant = "";
					double _cant = 0.0;

					try {
						idarticulo = etiquetas.getJTable().getValueAt(i, 1).toString();
						descripcion = etiquetas.getJTable().getValueAt(i, 2).toString();
						cant = etiquetas.getJTable().getValueAt(i, 3).toString();
						_cant = new Double(cant);
					} catch (Exception e) {

					}
					if (b & _cant >= 1) {
						String q="insert into b_etiquetas (fecha,idarticulo,descripcion,cantidad) ";
						q+="values (getdate(),'"+idarticulo+"','"+descripcion+"',"+_cant+")";
						data.addBatch(q);
					}
					
				}
				data.executeBatch();
				data.commitTransaction();
				etiquetas.setVisible(false);
				etiquetas.dispose();
				}
					
			}else{
				aviso("No hay etiquetas para imprimir");
				if (preguntar("Etiquetas","Cierra Ventana de Etiquetas?")){
					etiquetas.setVisible(false);
					etiquetas.dispose();
				}
			}
}
	
	public void cargar_linea_detalles(String linea){
		String desde=frame.get_txt_fecha_desde().getText();
		String hasta=frame.get_txt_fecha_hasta().getText();
		String stock=frame.get_txt_stock().getText();
		final Object[][] results=data.getLineasSimple(desde, hasta,stock,linea);
		
		Runnable _execute = new Runnable() {
			public void run(){
					create_table_detalle(results);	
			}
		};
		this.invokeLater(_execute);
	}
	
	public void cargar_linea_actualizacion(String linea){
		String desde=frame.get_txt_fecha_desde().getText();
		String hasta=frame.get_txt_fecha_hasta().getText();
		String stock=frame.get_txt_stock().getText();
		final Object[][] results=data.getLineasActualizacion(desde, hasta,stock,linea);
		
		Runnable _execute = new Runnable() {
			public void run(){
					create_table_actualizacion(results);	
			}
		};
		this.invokeLater(_execute);
	}
	
	public void cargar_linea(String linea,String idproveedor){
		Convertidor cv=new Convertidor();
		String desde=frame.get_txt_fecha_desde().getText();
		String hasta=frame.get_txt_fecha_hasta().getText();
		String stock_relevante=frame.get_txt_stock().getText();
		String q=data.getLineasSingleQuery(desde, hasta, stock_relevante, linea);
		Object[][] results=data.getResults(q);
		//linea|valor|items|proveedor|nombre|ventas|compras|mov|stock|actualizacion|control
		if (results!=null){
			if (results.length>0){
				
				String nombre="";
				try {
					nombre=(String)results[0][4];
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Double valor=0.0;
				try {
					valor=new Double((String)results[0][1]);
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Double items=0.0;
				try {
					items=new Double((String)results[0][2]);
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Double movimientos=0.0;
				try {
					movimientos=new Double((String)results[0][7]);
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String fecha="";
				try {
					fecha = (String) results[0][9];
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				String fecha_control="";
				try {
					fecha_control = (String) results[0][10];
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Double ventas=0.0;
				try {
					ventas=new Double((String)results[0][11]);
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Double compras=0.0;
				try {
					compras=new Double((String)results[0][13]);
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				frame.get_txt_detalle_linea().setText(linea);
				frame.get_txt_detalle_valor().setText(cv.getMoney(valor,2));
				frame.get_txt_detalle_items().setText(cv.getMoney(items,2));
				
				
				frame.get_txt_detalle_actualizacion().setText(fecha);
				frame.get_txt_idproveedor().setText(idproveedor);
				frame.get_txt_proveedor_nombre().setText(nombre);
				frame.get_txt_detalle_control().setText(fecha_control);
				frame.get_txt_detalle_ventas().setText(cv.getMoney(ventas,2));
				frame.get_txt_detalle_compras().setText(cv.getMoney(compras,2));
				double items_linea=data.getItemsLinea(linea, idproveedor);
				double actualizados=data.getItemsActualizados(linea, idproveedor, fecha);
				double porcentaje=(actualizados/items_linea*100);
				System.out.println("Actualizados?"+actualizados+"/"+items_linea);
				if (fecha.compareTo("01-01-1900")==0){
					frame.get_txt_detalle_actualizacion().setText("NUNCA");	
				}
				if (fecha.compareTo("01-01-1900")!=0){
					if (this.eval_venc(fecha)){
						frame.get_txt_detalle_actualizacion().setBackground(Color.green);
					}else{
						frame.get_txt_detalle_actualizacion().setBackground(Color.red);
					}
				}else{
					frame.get_txt_detalle_actualizacion().setText("NUNCA");
					frame.get_txt_detalle_actualizacion().setBackground(Color.red);
				}
				if (fecha_control.compareTo("01-01-1900")!=0){
					frame.get_txt_detalle_control().setText(fecha_control);
					if (this.eval_venc(fecha_control)){
						frame.get_txt_detalle_control().setBackground(Color.green);
					}else{
						frame.get_txt_detalle_control().setBackground(Color.red);
					}
				}else{
					frame.get_txt_detalle_control().setText("NUNCA");
					frame.get_txt_detalle_control().setBackground(Color.red);
				}
				
				double errors=0.0;
				try {
					errors=data.getErrorsLinea(linea, idproveedor, desde, hasta);
					errors=100*errors/movimientos;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frame.get_txt_detalle_errores().setText(cv.getMoney(errors,2));
				frame.get_txt_detalle_items_linea().setText(cv.getMoney(items_linea,2));
				frame.get_txt_detalle_actualizacion_porcentaje().setText(cv.getMoney(porcentaje,2));
				
				double items_stock=data.getItemsEnStock(linea, idproveedor, fecha);
				frame.get_txt_detalle_items_stock().setText(cv.getMoney(items_stock,2));
				double sin_politica=data.getItemsSinPolitica(linea, idproveedor);
				frame.get_txt_detalle_politica().setText(cv.getMoney(sin_politica,2));
				if (fecha.compareTo("01-01-1900")!=0){
					try {
						double items_upd_stock=data.getItemsActualizadosStock(linea, idproveedor, fecha);
						double porcentaje_stock=(items_upd_stock/items_stock*100);
						frame.get_txt_detalle_actualizacion_stock().setText(cv.getMoney(porcentaje_stock,2));
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				}else{
					frame.get_txt_detalle_actualizacion_stock().setText(cv.getMoney(0.0,2));
					
				}
				this.cargar_informacion_para_pedido();
				this.cargar_linea_detalles(linea);
				this.cargar_linea_actualizacion(linea);
			
			}
		}
			
		
	}
	public void seleccion(int row){
		if (row>=0 & row<frame.getJTable().getRowCount()){
			
			String linea=frame.getJTable().getValueAt(row,0).toString();
			String idproveedor=frame.getJTable().getValueAt(row,3).toString();
			frame.get_txt_detalle_linea().setText(linea);
			frame.get_txt_idproveedor().setText(idproveedor);
			frame.get_txt_detalle_actualizacion().setText("");
			frame.get_txt_detalle_actualizacion_porcentaje().setText("");
			frame.get_txt_detalle_actualizacion_stock().setText("");
			frame.get_txt_detalle_compras().setText("");
			frame.get_txt_detalle_errores().setText("");
			frame.get_txt_detalle_items().setText("");
			frame.get_txt_detalle_items_linea().setText("");
			frame.get_txt_detalle_items_stock().setText("");
			frame.get_txt_detalle_politica().setText("");
			frame.get_txt_detalle_valor().setText("");
			frame.get_txt_detalle_ventas().setText("");
			frame.get_txt_renta().setText("");
			frame.get_txt_stock().setText("");
			frame.get_txt_detalle_control().setText("");
			frame.get_txt_unidades_critico().setText("");
			frame.get_txt_unidades_critico().setText("");
			frame.get_txt_unidades_proyeccion().setText("");
			frame.get_txt_importe_critico().setText("");
			frame.get_txt_importe_proyeccion().setText("");	
		}
		
	}
	
	public void cargar_lineas(){
		String idproveedor=frame.get_txt_idproveedor().getText();
		String linea=frame.get_txt_detalle_linea().getText();
		if (idproveedor.compareTo("")!=0 & linea.compareTo("")!=0){
			this.cargar_linea(linea, idproveedor);	
		}
		
	}
	/**
	 * chk|idarticulo|descripcion|costo|critico|proyectado|stock|ventas|pedir|total
	 * @param editable
	 * @return
	 */
	private CustomTable crearTablaDeItems(boolean editable) {
		CustomTable Table = new CustomTable();
		// ColorTableCellRenderer colour=new ColorTableCellRenderer();
		Column col = new Column();
		col = new Column();
		col.setName("");
		col.setWidth(40);
		col.setEditable(true);
		CheckBoxCellEditor chkce = new CheckBoxCellEditor();
		//chkce.setName(_Interface._chk_item);
		chkce.setItemListener(this.getConstructor().getItemListener());
		chkce.setTipo(Double.class);
		col.setCellEditor(chkce.getCellCheck());
		col.setClass(Boolean.class);
		Table.addColumn(col);

		col = new Column();
		col.setName("idArticulo");
		col.setWidth(120);
		col.setEditable(false);
		col.setCellRenderer(new TableItemColorCellRenderer());
		/*CellEditor pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.blue);
		pce.setName(_Interface._table_idarticulo);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());*/
		
		Table.addColumn(col);

		col = new Column();
		col.setName("Descripcion");
		col.setWidth(240);
		col.setEditable(editable);
		col.setCellRenderer(new TableItemColorCellRenderer());
		Table.addColumn(col);
		
		
		col = new Column();
		col.setName("Costo");
		col.setWidth(60);
		col.setAligment(JTextField.RIGHT);
		col.setEditable(false);
		col.setCellRenderer(new TableItemColorCellRenderer());
		col.setClass(Double.class);
		Table.addColumn(col);

		

		col = new Column();
		col.setName("Critico");
		col.setWidth(60);
		col.setAligment(JTextField.RIGHT);
		/*pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.blue);
		pce.setName(_Interface._table_items_critico);
		pce.setTipo(Double.class);
		col.setCellEditor(pce.getCellEditor());*/
		col.setEditable(false);
		col.setCellRenderer(new TableItemColorCellRenderer());
		col.setClass(Double.class);
		Table.addColumn(col);
		
		col = new Column();
		col.setName("Proyectado");
		col.setWidth(80);
		col.setAligment(JTextField.RIGHT);
		/*pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.blue);
		pce.setName(_Interface._table_items_minimo);
		pce.setTipo(Double.class);
		col.setCellEditor(pce.getCellEditor());*/
		col.setEditable(false);
		col.setCellRenderer(new TableItemColorCellRenderer());
		col.setClass(Double.class);
		Table.addColumn(col);
		
		
		col = new Column();
		col.setName("Stock");
		col.setWidth(60);
		col.setAligment(JTextField.RIGHT);
		col.setEditable(false);
		col.setCellRenderer(new TableItemColorCellRenderer());
		col.setClass(Double.class);
		Table.addColumn(col);

		col = new Column();
		col.setName("Ventas");
		col.setWidth(60);
		col.setAligment(JTextField.RIGHT);
		col.setEditable(false);
		col.setCellRenderer(new TableItemColorCellRenderer());
		col.setClass(Double.class);
		Table.addColumn(col);

		col = new Column();
		col.setName("Pedir");
		col.setAligment(JTextField.RIGHT);
		/*pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.blue);
		pce.setName(_Interface._table_items_pedir);
		pce.setTipo(Double.class);
		col.setCellEditor(pce.getCellEditor());*/
		col.setWidth(60);
		col.setEditable(false);
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
		Table.setName(_Interface._table_items);
		Table.setFont(new Font("Dialog", Font.PLAIN, 9));
		Table.addMouseListener(this.getConstructor().getMouseListener());
		Table.addKeyListener(this.getConstructor().getKeyListener());
		return Table;
	}

	public void editar(){
		int row=frame.getJTable1().getSelectedRow();
		if (row>=0 & row<=frame.getJTable1().getRowCount()-1){
			String idproveedor=frame.getJTable1().getValueAt(row, 3).toString();
			String linea=frame.getJTable1().getValueAt(row, 0).toString();
			
		if (linea.compareTo("")!=0 | idproveedor.compareTo("")!=0){
			this.editar_linea(linea, idproveedor,"");	
		}
		}
	}
	
	public void editar_actualizacion(){
		int row=frame.getJTable3().getSelectedRow();
		if (row>=0 & row<=frame.getJTable3().getRowCount()-1){
			String idproveedor=frame.get_txt_idproveedor().getText();
			String idproveedor_actualizacion=frame.getJTable3().getValueAt(row, 3).toString();
			String linea=frame.getJTable3().getValueAt(row, 0).toString();
		if (linea.compareTo("")!=0 | idproveedor.compareTo("")!=0){
			this.editar_linea(linea, idproveedor,idproveedor_actualizacion);	
		}
		}
	}
	aplicacion.inventario.planilla.constructor._Constructor planilla=null;
	public void editar_linea(String linea,String idproveedor,String idproveedor_actualizacion){
		if (planilla!=null){
			planilla.dispose();
		}
		planilla=new aplicacion.inventario.planilla.constructor._Constructor();
		planilla.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler());
		planilla.setParameter(_parametros.iduser, this.getConstructor().getIduser());
		planilla.setLookAndFeelTheme(this.getConstructor().getLookAndFeelTheme());
		planilla.setParameter(aplicacion.inventario.planilla.interfaces._parametros._linea, linea);
		planilla.setParameter(aplicacion.inventario.planilla.interfaces._parametros._proveedor, idproveedor);
		planilla.setParameter(aplicacion.inventario.planilla.interfaces._parametros._proveedor_actualizacion, idproveedor_actualizacion);
		planilla.build(this.getConstructor());
		planilla.init();
	}
	
	aplicacion.actualizacion.archivo.constructor._Constructor archivo=null;
	public void actualizar_por_archivo(String linea,String idproveedor){
		if (archivo!=null){
			archivo.dispose();
		}
		archivo=new aplicacion.actualizacion.archivo.constructor._Constructor();
		archivo.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler());
		archivo.setParameter(_parametros.iduser, this.getConstructor().getIduser());
		archivo.setParameter(_parametros.LookAndFeel,this.getConstructor().getLookAndFeelTheme());
		archivo.setParameter(aplicacion.actualizacion.archivo.interfaces._parametros._linea, linea);
		archivo.setParameter(aplicacion.actualizacion.archivo.interfaces._parametros._idproveedor, idproveedor);
		archivo.build(this.getConstructor());
		archivo.init();
	}
	
	aplicacion.actualizacion.odbc.constructor._Constructor odbc=null;
	public void actualizar_por_odbc(String linea,String idproveedor){
		if (odbc!=null){
			odbc.dispose();
		}
		odbc=new aplicacion.actualizacion.odbc.constructor._Constructor();
		odbc.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
		odbc.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler());
		odbc.setParameter(aplicacion.actualizacion.odbc.interfaces._parametros._linea, linea);
		odbc.setParameter(aplicacion.actualizacion.odbc.interfaces._parametros._idproveedor, idproveedor);
		odbc.build(this.getConstructor());
		odbc.init();
	}
	
	public void editar_alias(){
		int row=frame.getJTable1().getSelectedRow();
		if (row>=0 & row<=frame.getJTable3().getRowCount()-1){
			String idproveedor=frame.getJTable3().getValueAt(row, 3).toString();
			String linea=frame.getJTable3().getValueAt(row, 0).toString();
			
		if (idproveedor.compareTo("")!=0 & linea.compareTo("")!=0){
			this.editar_alias(linea, idproveedor);	
		}
		}
	}
	
	public void editar_codigos(){
		int row=frame.getJTable1().getSelectedRow();
		if (row>=0 & row<=frame.getJTable3().getRowCount()-1){
			String idproveedor=frame.getJTable3().getValueAt(row, 3).toString();
			String linea=frame.getJTable3().getValueAt(row, 0).toString();
			
		if (idproveedor.compareTo("")!=0 & linea.compareTo("")!=0){
			this.editar_codigos(linea, idproveedor);	
		}
		}
	}
	public void actualizar_por_archivo(){
		int row=frame.getJTable1().getSelectedRow();
		if (row>=0 & row<=frame.getJTable1().getRowCount()-1){
			String idproveedor=frame.getJTable1().getValueAt(row, 3).toString();
			String linea=frame.getJTable1().getValueAt(row, 0).toString();
			
		
		if (idproveedor.compareTo("")!=0 & linea.compareTo("")!=0){
			this.actualizar_por_archivo(linea, idproveedor);	
		}
		}
	}
	
	public void actualizar_por_odbc(){
		int row=frame.getJTable1().getSelectedRow();
		if (row>=0 & row<=frame.getJTable1().getRowCount()-1){
			String idproveedor=frame.getJTable1().getValueAt(row, 3).toString();
			String linea=frame.getJTable1().getValueAt(row, 0).toString();
			
			if (idproveedor.compareTo("")!=0 & linea.compareTo("")!=0){
				this.actualizar_por_odbc(linea, idproveedor);
			}	
		}
		
	}
	
	private aplicacion.actualizacion.alias.constructor._Constructor alias=null;
	public void editar_alias(String linea,String idproveedor) {
		
			if (alias != null) {
				alias.dispose();
			}
			alias = new aplicacion.actualizacion.alias.constructor._Constructor();
			alias.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
			alias.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler());
			alias.setParameter(aplicacion.actualizacion.alias.interfaces._parametros.idproveedor,
								idproveedor);
			
			alias.setParameter(
				aplicacion.actualizacion.alias.interfaces._parametros.linea,
							linea);
			alias.build(this.getConstructor());
			alias.init();
		
	}
	
	private aplicacion.actualizacion.codigo.constructor._Constructor codigos=null;
	public void editar_codigos(String linea,String idproveedor) {
		
		if (codigos != null) {
			codigos.dispose();
		}
		codigos = new aplicacion.actualizacion.codigo.constructor._Constructor();
		codigos.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
		codigos.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler());
		codigos.setParameter(aplicacion.actualizacion.codigo.interfaces._parametros.idproveedor,
							idproveedor);
		
		codigos.setParameter(
			aplicacion.actualizacion.codigo.interfaces._parametros.linea,
						linea);
		codigos.build(this.getConstructor());
		codigos.init();
	
	}
	public Object[][] process_data(Object[][] results){
		
		Convertidor cv=new Convertidor();
		double valor_stock=0.0;
		double valor_ventas=0.0;
		double valor_renta=0.0;
		double valor_compras=0.0;
		int lineas=0;
		String currentlinea="";
		for (int i=0;i<results.length;i++){
			String linea=(String) results[i][0];
			if (linea.compareTo(currentlinea)!=0){
				currentlinea=linea;
				lineas++;
			}
		}
		
		Object[][] tmp=new Object[lineas][results[0].length+4];
		System.out.println("Lineas encontradas? "+lineas);
		lineas=0;
		currentlinea="";
		
		for (int i=0;i<results.length;i++){
			double valor=0;
			double items=0;
			double ventas=0;
			double sventas=0;
			double scompras=0;
			double srenta=0;
			double compras=0;
			double movimientos=0;
			double operaciones=0;
			Date actualizacion=null;
			Date control=null;
			try {
				valor=new Double((String)results[i][1]);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				items=new Double((String)results[i][2]);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				ventas=new Double((String)results[i][5]);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				compras=new Double((String)results[i][6]);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				movimientos=new Double((String)results[i][7]);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				operaciones=new Double((String)results[i][8]);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				sventas=new Double((String)results[i][11]);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				srenta=new Double((String)results[i][12]);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				scompras=new Double((String)results[i][13]);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			valor_stock+=valor;
			valor_ventas+=sventas;
			valor_renta+=srenta;
			valor_compras+=scompras;
		}
		
		double valor=0;
		double items=0;
		double ventas=0;
		double compras=0;
		double movimientos=0;
		double operaciones=0;
		Date actualizacion=cv.getDate("01-01-1900");
		Date control=cv.getDate("01-01-1900");
		double sventas=0;
		double scompras=0;
		double pventas=0;
		double pcompras=0;
		double pstock=0;
		double srenta=0;
		double prenta=0;
		double max_items=-1;
		String idproveedor="";
		String nombre="";
		String max_idproveedor="";
		String max_nombre="";
		double _items=0.0;
		int i=0;
		while(i<results.length){
			
			String linea=(String) results[i][0];
			if (currentlinea.compareTo("")==0){
				currentlinea=linea;
			}
			while (linea.compareTo(currentlinea)==0 & i<results.length){
				_items=new Double((String)results[i][2]);
				idproveedor=(String)results[i][3];
				nombre=(String)results[i][4];
				System.out.println("<i:"+i+"> "+linea+_items+" "+idproveedor+" "+nombre);	
				if (_items>max_items){
						System.out.println(lineas+") Linea>"+linea+" "+idproveedor+" "+nombre+" max items?"+max_items);
						max_items=_items;
						max_idproveedor=idproveedor;
						max_nombre=nombre;	
				}
					
				
				
				
				try {
					valor+=new Double((String)results[i][1]);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					items+=new Double((String)results[i][2]);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					ventas+=new Double((String)results[i][5]);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					compras+=new Double((String)results[i][6]);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					movimientos+=new Double((String)results[i][7]);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					operaciones+=new Double((String)results[i][8]);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					Date _fecha_actualizacion=cv.getDate((String)results[i][9]);
					
					if (cv.getMayorDate(_fecha_actualizacion, actualizacion)>0){
						if (cv.getMayorDate(_fecha_actualizacion, new Date())<=0){
							actualizacion=_fecha_actualizacion;	
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					
					Date _fecha_control=cv.getDate((String)results[i][10]);
					if (cv.getMayorDate(_fecha_control, control)>0){
						control=_fecha_control;	
					}
				
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					sventas+=new Double((String)results[i][11]);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					srenta+=new Double((String)results[i][12]);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					scompras+=new Double((String)results[i][13]);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					prenta+=100*srenta/valor_renta;
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					pventas+=100*sventas/valor_ventas;
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					pcompras+=100*scompras/valor_compras;
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					pstock+=100*valor/valor_ventas;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				i++;
				if (i<results.length-1){
					linea=(String) results[i][0];	
				}
				
			}
			
			
			
				tmp[lineas][0]=currentlinea;
				tmp[lineas][1]=valor;
				tmp[lineas][2]=items;
				tmp[lineas][3]=max_idproveedor;//idproveedor
				tmp[lineas][4]=max_nombre;//nombre
				tmp[lineas][5]=ventas;
				tmp[lineas][6]=compras;
				tmp[lineas][7]=movimientos;
				tmp[lineas][8]=operaciones;
				tmp[lineas][9]=actualizacion;
				tmp[lineas][10]=control;
				tmp[lineas][11]=sventas;
				tmp[lineas][12]=pventas;
				tmp[lineas][13]=scompras;
				tmp[lineas][14]=pcompras;
				tmp[lineas][15]=pstock;
				tmp[lineas][16]=srenta;
				tmp[lineas][17]=prenta;
				lineas++;
				//save calcs
				
				//clean variables
				currentlinea=linea;
				
				valor=0;
				items=0;
				ventas=0;
				compras=0;
				movimientos=0;
				operaciones=0;
				sventas=0;
				scompras=0;
				pventas=0;
				pcompras=0;
				pstock=0;
				srenta=0;
				prenta=0;
				actualizacion=cv.getDate("01-01-1900");
				control=cv.getDate("01-01-1900");
				max_items=-1;
				max_idproveedor="";
				max_nombre="";
			}
			
		
		System.out.println("Valor Stock"+valor_stock);
		System.out.println("Valor Ventas"+valor_ventas);
		System.out.println("Valor Renta"+valor_renta);
		System.out.println("Valor Compras"+valor_compras);
		frame.get_txt_valor_stock().setText(cv.getMoney(valor_stock, 2));
		frame.get_txt_ventas().setText(cv.getMoney(valor_ventas, 2));
		frame.get_txt_compras().setText(cv.getMoney(valor_compras, 2));
		frame.get_txt_renta().setText(cv.getMoney(valor_renta, 2));
		double qty=0.0;
		try {
			qty = new Double(tmp.length);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame.get_txt_lineas().setText(cv.getMoney(qty, 2));
		return tmp;
	}
	
public Object[][] process_data_simple(Object[][] results){
		
		Convertidor cv=new Convertidor();
		double valor_stock=0.0;
		double valor_ventas=0.0;
		double valor_renta=0.0;
		double valor_compras=0.0;
		int lineas=0;
		String currentlinea="";
	
		Object[][] tmp=new Object[results.length][results[0].length+4];
		System.out.println("Lineas encontradas? "+lineas);
		lineas=0;
		currentlinea="";
		
		for (int i=0;i<results.length;i++){
			double valor=0;
			double items=0;
			double ventas=0;
			double sventas=0;
			double scompras=0;
			double srenta=0;
			double compras=0;
			double movimientos=0;
			double operaciones=0;
			Date actualizacion=null;
			Date control=null;
			try {
				valor=new Double((String)results[i][1]);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				items=new Double((String)results[i][2]);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				ventas=new Double((String)results[i][5]);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				compras=new Double((String)results[i][6]);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				movimientos=new Double((String)results[i][7]);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				operaciones=new Double((String)results[i][8]);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				sventas=new Double((String)results[i][11]);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				srenta=new Double((String)results[i][12]);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				scompras=new Double((String)results[i][13]);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			valor_stock+=valor;
			valor_ventas+=sventas;
			valor_renta+=srenta;
			valor_compras+=scompras;
		}
		
		double valor=0;
		double items=0;
		double ventas=0;
		double compras=0;
		double movimientos=0;
		double operaciones=0;
		Date actualizacion=cv.getDate("01-01-1900");
		Date control=cv.getDate("01-01-1900");
		double sventas=0;
		double scompras=0;
		double pventas=0;
		double pcompras=0;
		double pstock=0;
		double srenta=0;
		double prenta=0;
		
		for (int i=0;i<results.length;i++){
			
			String linea=(String) results[i][0];
			String idproveedor=(String) results[i][3];
			String nombre=(String) results[i][4];
			if (currentlinea.compareTo("")==0){
				currentlinea=linea;
			}
			
				try {
					valor=new Double((String)results[i][1]);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					items=new Double((String)results[i][2]);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					ventas=new Double((String)results[i][5]);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					compras=new Double((String)results[i][6]);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					movimientos=new Double((String)results[i][7]);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					operaciones=new Double((String)results[i][8]);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					Date _fecha_actualizacion=cv.getDate((String)results[i][9]);
					if (cv.getMayorDate(_fecha_actualizacion, actualizacion)>0){
						actualizacion=_fecha_actualizacion;	
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					
					Date _fecha_control=cv.getDate((String)results[i][10]);
					if (cv.getMayorDate(_fecha_control, control)>0){
						control=_fecha_control;	
					}
				
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					sventas=new Double((String)results[i][11]);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					srenta=new Double((String)results[i][12]);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					scompras=new Double((String)results[i][13]);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					prenta=100*srenta/valor_renta;
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					pventas=100*sventas/valor_ventas;
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					pcompras=100*scompras/valor_compras;
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					pstock=100*valor/valor_ventas;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				
				tmp[i][0]=linea;
				tmp[i][1]=valor;
				tmp[i][2]=items;
				tmp[i][3]=idproveedor;//idproveedor
				tmp[i][4]=nombre;//nombre
				tmp[i][5]=ventas;
				tmp[i][6]=compras;
				tmp[i][7]=movimientos;
				tmp[i][8]=operaciones;
				tmp[i][9]=actualizacion;
				tmp[i][10]=control;
				tmp[i][11]=sventas;
				tmp[i][12]=pventas;
				tmp[i][13]=scompras;
				tmp[i][14]=pcompras;
				tmp[i][15]=pstock;
				tmp[i][16]=srenta;
				tmp[i][17]=prenta;
				actualizacion=cv.getDate("01-01-1900");
				control=cv.getDate("01-01-1900");
				
			}
			
		
			return tmp;
	}
	private void create_table_etiquetas(Object[][] results) {
		_Constructor constructor = (_Constructor) this.getConstructor();
		CustomTable table = new CustomTable();
		CellEditor pce = null;
		CheckBoxCellEditor cbce = null;

		table.setSortable(false);
		Column col = null;

		col = new Column();
		col.setName("");
		col.setWidth(30);
		col.setEditable(true);
		col.setClass(Boolean.class);
		cbce = new CheckBoxCellEditor();
		cbce.setName(_Interface._table_etiquetas_seleccion);
		cbce.setItemListener(constructor.getItemListener());
		col.setCellEditor(cbce.getCellCheck());
		table.addColumn(col);

		col = new Column();
		col.setName("idarticulo");
		col.setWidth(100);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);

		col = new Column();
		col.setName("descripcion");
		col.setWidth(280);
		col.setEditable(false);
		col.setAligment(JTextField.LEFT);
		table.addColumn(col);

		col = new Column();
		col.setName("cant");
		col.setWidth(46);
		col.setEditable(true);
		col.setAligment(JTextField.RIGHT);
		pce = new CellEditor();
		pce.addKeyListener(constructor.getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_etiquetas_cantidad);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());
		table.addColumn(col);

		table.setData(results);
		table.setName(_Interface._table_etiquetas);
		table.addKeyListener(this._constructor.getKeyListener());
		Font fuente = new Font("Dialog", Font.BOLD, 10);
		table.setHeaderFont(fuente);
		table.setFont(fuente);
		table.build();
		table.fillData();

		JTable _table = table.getTable();

		etiquetas.setJTable(_table);
	}
	private void crear_empty_etiquetas() {
		Object[][] results = new Object[][] { { false, "", "", "" } };
		this.create_table_etiquetas(results);
	}
	public void Etiquetar() {
		
			frame.getJTable().setEnabled(false);
			int qtys=0;
			for (int i=0;i<frame.getJTable().getRowCount();i++){
				
				double qty=0.0;
				try {
					qty=(Double) frame.getJTable().getValueAt(i, 6);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (qty>0){
					qtys++;
				}
			}
			
			Object[][] results = new String[qtys][3];
			qtys=0;
			for (int i=0;i<frame.getJTable().getRowCount();i++){
				String idarticulo=(String) frame.getJTable().getValueAt(i, 0);
				String descripcion=(String) frame.getJTable().getValueAt(i, 1);
				double qty=0.0;
				try {
					qty=(Double) frame.getJTable().getValueAt(i, 6);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (qty>0){
					results[qtys][0]=idarticulo;
					results[qtys][1]=descripcion;
					results[qtys][2]=""+qty;
					qtys++;
				}
			}
			if (results != null) {
				if (results.length > 0) {
					_Constructor constructor = (_Constructor) this.getConstructor();
					if (this.etiquetas != null) {
						etiquetas.setVisible(false);
						etiquetas.dispose();
					}
					this.etiquetas = new _Etiquetas();
					this.etiquetas.get_chk_seleccionar().setName(
							_Interface._chk_seleccionar_etiquetas);
					this.etiquetas.get_chk_seleccionar().addItemListener(
							constructor.getItemListener());
					this.etiquetas.get_btn_imprimir_etiquetas().setActionCommand(
							_Interface._btn_imprimir_etiquetas);
					this.etiquetas.get_btn_imprimir_etiquetas().addActionListener(
							constructor.getActionListener());
					this.etiquetas.setVisible(true);
					this.etiquetas.requestFocus();
					this.etiquetas.requestFocusInWindow();
					this.crear_empty_etiquetas();
					int units = 0;
					for (int i = 0; i < results.length; i++) {
						String idarticulo = "";
						String descripcion="";
						String cantidad = "";
						double _cantidad = 0.0;
						try {
							idarticulo = results[i][0].toString();
							descripcion = results[i][1].toString();
							cantidad = results[i][2].toString();
							_cantidad = new Double(cantidad.replace(",", ""));

						} catch (Exception e) {

						}
						if (_cantidad > 0) {
							DefaultTableModel model = (DefaultTableModel) etiquetas
									.getJTable().getModel();
							int row = model.getRowCount() - 1;
							etiquetas.getJTable().setValueAt(true, row, 0);
							etiquetas.getJTable().setValueAt(idarticulo, row, 1);
							etiquetas.getJTable().setValueAt(descripcion, row, 2);
							etiquetas.getJTable().setValueAt(cantidad, row, 3);

							model.setRowCount(model.getRowCount() + 1);
							units += _cantidad;
							etiquetas.get_txt_unidades().setText("" + units);
						}
					}
				}
			}

			frame.getJTable().setEnabled(true);
		
		
		
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
		        	e.printStackTrace();
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
				//_fecha_actualizacion.add(Calendar.DATE, 27);
				System.out.println("fecha limite       >"+_fecha_limite);
				System.out.println("fecha actualizacion>"+_fecha_actualizacion);
				ok=_fecha_limite.toString().compareTo(_fecha_actualizacion.toString())==0;
				if (!ok){
					ok=_fecha_limite.before(_fecha_actualizacion);
					System.out.println("fecha limite < fecha_actualizacion? "+_fecha_limite.toString()+"!="+_fecha_actualizacion.toString());
				}else{
					System.out.println("Son identicas");
				}
				
				
			//	System.out.println(now.getTime()+" "+cal.getTime());
		    }
			//System.out.println("dif "+c+" "+diferencia);
		}else {
			//"Fecha Nula"
		}
		return ok;
	}
	
}
