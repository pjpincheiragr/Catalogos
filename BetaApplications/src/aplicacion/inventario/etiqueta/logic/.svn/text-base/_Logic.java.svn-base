package aplicacion.inventario.etiqueta.logic;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.*;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

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
import aplicacion.ventas.catalogo.logic.TableColorCellRenderer;
import aplicacion.inventario.etiqueta.constructor.*;

import aplicacion.inventario.etiqueta.gui._Frame;
import aplicacion.inventario.etiqueta.interfaces.*;
import aplicacion.inventario.etiqueta.logic._Data;
import aplicacion.inventario.etiqueta.interfaces._Interface;

public class _Logic extends Logic {
	private _Frame frame;
	private _Data data;
	private Object[][] memoria = null;
	private aplicacion.inventario.politica.constructor._Constructor editor_politica = null;
	private aplicacion.herramientas.java.visualselector.constructor._Constructor vSelector = null;
	private aplicacion.herramientas.java.importar.constructor._Constructor importar = null;
	private aplicacion.herramientas.java.exportar.constructor._Constructor exportar = null;
	private aplicacion.actualizacion.alias.constructor._Constructor alias = null;
	private aplicacion.herramientas.java.visualizadores.Articulo vArticulo = null;
	private aplicacion.herramientas.java.buscadores.Articulo bArticulo = null;
	// variables de tareas swingwork

	private String estado = "";
	private int current;
	private int lenght;
	private boolean debug, done, canceled, override;
	private Timer Timer; // @jve:decl-index=0:
	private Crono crono;
	int errors = 0;
	private boolean nuevo=false;
	private boolean cambios = false;
	private boolean[] marquer;
	private String tc = "TRSK";
	
	private List<Filtro> Filters;
	private List<columna> SColumns;
	private List<Filtro> SFilters;
	private String SFromTable;
	private LinkedList SOrders;
	private LinkedList Sexcluders;
	private LinkedList SiniValues;
	private LinkedList SLabels;
	private LinkedList SacumFilters;
	private String SRestriction;
	private String Sidconector;
	private String SGroup;
	private int Stop;
	
	public void setFrame(JFrame _frame) {
		this.frame = (_Frame) _frame;
		super.setFrame(_frame);
	}

	public void setData(Data _data) {
		this.data = (_Data) _data;
		super.setData(_data);
	}

	/**
	 * idarticulo|descripcion|stock|cantidad
	 * 
	 * @param results
	 */
	private void create_table(Object[][] results) {
		CustomTable table = new CustomTable();
		CellEditor pce = null;
		Column col = null;

		col = new Column();
		col.setName("");
		col.setWidth(40);
		col.setEditable(true);
		col.setClass(Boolean.class);
		table.addColumn(col);
		
		col = new Column();
		col.setName("idarticulo");
		col.setWidth(100);
		col.setEditable(true);
		col.setClass(String.class);
		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_control_idarticulo);
		pce.setAligment(JTextField.RIGHT);
		col.setCellEditor(pce.getCellEditor());
		table.addColumn(col);

		col = new Column();
		col.setName("descripcion");
		col.setWidth(300);
		col.setEditable(true);
		col.setClass(String.class);
		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_control_descripcion);
		pce.setAligment(JTextField.RIGHT);
		col.setCellEditor(pce.getCellEditor());
		table.addColumn(col);

		col = new Column();
		col.setName("cantidad");
		col.setWidth(80);
		col.setEditable(true);
		col.setClass(Double.class);
		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_control_cantidad);
		pce.setAligment(JTextField.RIGHT);
		col.setCellEditor(pce.getCellEditor());
		table.addColumn(col);

		col = new Column();
		col.setName("especial");
		col.setWidth(140);
		col.setEditable(true);
		col.setClass(Boolean.class);
		table.addColumn(col);
		
		table.setData(results);
		Font fuente = new Font("Arial", Font.PLAIN, 9);
		table.setHeaderFont(fuente);
		table.setFont(fuente);
		table.build();
		table.fillData();
		JTable _table = table.getTable();
		_table.setName(_Interface._table_control);
		_table.addMouseListener(this.getConstructor().getMouseListener());
		_table.addKeyListener(this.getConstructor().getKeyListener());
		_table.getTableHeader().addMouseListener(
				this.getConstructor().getMouseListener());
		frame.setJTable(table.getTable());
	}

	/**
	 * idarticulo|descripcion|stock|cantidad
	 * 
	 * @param results
	 */
	private void create_table_importar(Object[][] results) {
		CustomTable table = new CustomTable();
		CellEditor pce = null;
		Column col = null;

		col = new Column();
		col.setName("");
		col.setWidth(40);
		col.setEditable(true);
		col.setClass(Boolean.class);
		table.addColumn(col);
		
		col = new Column();
		col.setName("idarticulo");
		col.setWidth(100);
		col.setEditable(true);
		col.setClass(String.class);
		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_control_idarticulo);
		pce.setAligment(JTextField.RIGHT);
		col.setCellEditor(pce.getCellEditor());

		table.addColumn(col);

		col = new Column();
		col.setName("descripcion");
		col.setWidth(300);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("stock");
		col.setWidth(120);
		col.setEditable(false);
		col.setClass(Double.class);
		table.addColumn(col);

		
		col = new Column();
		col.setName("linea");
		col.setWidth(140);
		col.setEditable(false);
		col.setClass(Double.class);
		table.addColumn(col);

		
		table.setData(results);
		Font fuente = new Font("Arial", Font.PLAIN, 9);
		table.setHeaderFont(fuente);
		table.setFont(fuente);
		table.build();
		table.fillData();
		JTable _table = table.getTable();
		_table.setName(_Interface._table_control);
		_table.addMouseListener(this.getConstructor().getMouseListener());
		_table.addKeyListener(this.getConstructor().getKeyListener());
		_table.getTableHeader().addMouseListener(
				this.getConstructor().getMouseListener());
		frame.setJTable1(table.getTable());
	}
	
	/**
	 * chk|idarticulo|descripcion|cantidad|especial|impresa
	 * 
	 * @param results
	 */
	private void create_table_historial(Object[][] results) {
		CustomTable table = new CustomTable();
		CellEditor pce = null;
		Column col = null;

		col = new Column();
		col.setName("");
		col.setWidth(40);
		col.setEditable(true);
		col.setClass(Boolean.class);
		table.addColumn(col);
		
		col = new Column();
		col.setName("idarticulo");
		col.setWidth(100);
		col.setEditable(false);
		col.setClass(String.class);
		

		table.addColumn(col);

		col = new Column();
		col.setName("descripcion");
		col.setWidth(360);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("cantidad");
		col.setWidth(70);
		col.setAligment(JTextField.RIGHT);
		col.setEditable(true);
		col.setClass(Double.class);
		table.addColumn(col);

		
		col = new Column();
		col.setName("especial");
		col.setWidth(80);
		col.setEditable(false);
		col.setClass(Boolean.class);
		table.addColumn(col);

		col = new Column();
		col.setName("impresa");
		col.setWidth(80);
		col.setEditable(true);
		col.setClass(Boolean.class);
		table.addColumn(col);

		col = new Column();
		col.setName("id");
		col.setWidth(100);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);
		
		table.setData(results);
		Font fuente = new Font("Arial", Font.PLAIN, 9);
		table.setHeaderFont(fuente);
		table.setFont(fuente);
		table.build();
		table.fillData();
		JTable _table = table.getTable();
		//_table.setName(_Interface._table_control);
		_table.addMouseListener(this.getConstructor().getMouseListener());
		_table.addKeyListener(this.getConstructor().getKeyListener());
		_table.getTableHeader().addMouseListener(
				this.getConstructor().getMouseListener());
		frame.setJTable2(table.getTable());
	}
	
	public void transfer_fecha_focus(JTextField tx) {
		if (tx.getName() == _Interface._txt_fecha_desde) {
			frame.get_txt_hasta().requestFocusInWindow();
		}
		if (tx.getName() == _Interface._txt_fecha_hasta) {
			
		}
	}
	
	private aplicacion.herramientas.java.evaluadores.Fecha Fecha = null;

	public void initialize_Fecha() {
		Fecha = new aplicacion.herramientas.java.evaluadores.Fecha() {
			public void cargar(JTextField tx) {
				transfer_fecha_focus(tx);
			}
		};
		Fecha.setConstructor(this.getConstructor());
	}

	public void reconnect_Fecha() {
		if (Fecha != null) {
			Fecha.setConstructor(this.getConstructor());
		}
	}

	public void BuscarFecha(JTextField tx) {
		Fecha.Buscar(tx);
	}

	public void seleccionar_historial(boolean selected){
		JTable table=frame.getJTable2();
		if (table!=null){
				for (int i=0;i<table.getRowCount();i++){
					table.setValueAt(selected,i, 0);
				}
			
		}
	}
	public void guardar_historial(){
		int errors=0;
		int operations=0;
		JTable table=frame.getJTable2();
		if (table!=null){
			boolean ok=confirmar("Confirme Para Modificar el estado de impresion:",3);
			if (ok){
				for (int i=0;i<table.getRowCount();i++){
					boolean selected=false;
					try {
						selected=(Boolean) table.getValueAt(i, 0);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (selected){
						operations++;
						String id=table.getValueAt(i, 6).toString();
						
						String cantidad=table.getValueAt(i, 3).toString();
						boolean impresa=(Boolean)table.getValueAt(i, 5);
						double qty=0;
						try {
							qty=new Double(cantidad);
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						String q=data.getUpdate(id, qty, impresa);
						data.clearBatch();
						data.addBatch(q);
						boolean error=data.executeBatch();
						if (error){
							errors++;
						}
					}
				}
				aviso("Operacion finalizada con "+operations+" operaciones y "+errors+" errores");
			}	
		}
		
	}
	
	public void marcar(boolean marcar){
		int errors=0;
		int operations=0;
		JTable table=frame.getJTable2();
		if (table!=null){
				for (int i=0;i<table.getRowCount();i++){
					boolean selected=false;
					try {
						selected=(Boolean) table.getValueAt(i, 0);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (selected){
						operations++;
						table.setValueAt(marcar,i, 5);
					}
				}
				
				
		}
		if (operations>0){
			this.guardar_historial();
		}
		
	}
	public void BuscarFechaDesde() {
		Fecha.Buscar(frame.get_txt_desde());
	}
	public void BuscarFechaHasta() {
		Fecha.Buscar(frame.get_txt_hasta());
	}

	public void evaluarFecha(JTextField tx) {
		cambios = true;
		Fecha.evaluate(tx);
	}

	
	public void nuevo() {
		this.clean();
		this.getToday();
		
		nuevo=true;
		String idcomprobante = data.getProximoPGCorrecto(tc);
		
		
		// this.evaluar_idcomprobante(frame.get_txt_idcomprobante());
	}

	public void clean_importacion(){
		frame.setJTable1(null);
		frame.get_txt_articulo_desde().setText("");
		frame.get_txt_articulo_hasta().setText("");
		frame.get_txt_idproveedor().setText("");
		frame.get_txt_proveedor_descripcion().setText("");
		frame.get_txt_linea().setText("");
	}
	public void seleccionar_items(boolean chk){
		JTable table = frame.getJTable();
		if (table != null) {

			for (int i = 0; i <table.getRowCount() ; i++) {
				table.setValueAt(chk, i,0);
			}
		}
	}
	
	public void seleccionar_items_importar(boolean chk){
		JTable table = frame.getJTable1();
		if (table != null) {

			for (int i = 0; i <table.getRowCount() ; i++) {
				table.setValueAt(chk, i,0);
			}
		}
	}
	
	public void evaluar_linea(JTextField tx){
		String linea=tx.getText();
		if (linea.compareTo("")!=0){
			if (data.check_linea(linea)){
				cargar_prefijos(linea);
			}else{
				this.buscarLinea(tx);
			}
		}else{
			this.buscarLinea(tx);
		}
	}
	
	public void evaluar_idarticulo_desde(JTextField tx) {
		String idarticulo = "";
		idarticulo = tx.getText();
		if (idarticulo.compareTo("") == 0) {
			aviso("Ingrese un Articulo. Busqueda F5");
		} else {
			if (idarticulo.length() > 4) {
				if (idarticulo.substring(3, 4).compareTo("-") == 0) {
					frame.get_txt_articulo_hasta().requestFocusInWindow();	
				} else {
					this.buscarArticulo(tx);
				}
			} else {
				this.buscarArticulo(tx);
			}
		}
	}
	
	public void evaluar_idarticulo_hasta(JTextField tx) {
		String idarticulo = "";
		idarticulo = tx.getText();
		if (idarticulo.compareTo("") == 0) {
			aviso("Ingrese un Articulo. Busqueda F5");
		} else {
			if (idarticulo.length() > 4) {
				if (idarticulo.substring(3, 4).compareTo("-") == 0) {
					this.cargar_stock();	
				} else {
					this.buscarArticulo(tx);
				}
			} else {
				this.buscarArticulo(tx);
			}
		}
	}

	private aplicacion.herramientas.java.visualizadores.Proveedor vProveedor=null;
	public void buscarProveedor(JTextField tx) {
		if (vProveedor!=null){
			vProveedor.close();
		}
		vProveedor=new aplicacion.herramientas.java.visualizadores.Proveedor(this.getConstructor());
		int n = vProveedor.Buscar(tx);
		if (n == 0) {
				aviso("no hay Proveedors con ese codigo");
		}
	}
	
	public void evaluar_proveedor(JTextField tx){
		String idproveedor=tx.getText();
		if (idproveedor.startsWith("21101") & idproveedor.compareTo("21101")!=0){
			Object[][] results=data.getProveedor(idproveedor);
				if (results!=null){
					if (results.length>0){
						String desc=results[0][1].toString();
						String pol=results[0][2].toString();
						String poldesc=results[0][3].toString();
						String linea=results[0][6].toString();
						frame.get_txt_proveedor_descripcion().setText(desc);
						frame.get_txt_linea().requestFocusInWindow();
					}else {
						aviso("No se encontro Proveedor "+idproveedor);
					}
				}else {
					aviso("No se encontro Proveedor "+idproveedor);
				}
		}else {
				this.buscarProveedor(tx);
			
				
		}
	}
	
	public void cargar_prefijos(String linea){
		Object[][] results=data.getLinePrefix(linea);
		if (results!=null){
			if (results.length>0){
				String prefix=(String) results[0][0];
				frame.get_txt_articulo_desde().setText(prefix+"-000");
				frame.get_txt_articulo_hasta().setText(prefix+"-zzz");
				frame.get_txt_articulo_hasta().requestFocusInWindow();
				}
		}
	}
	private aplicacion.herramientas.java.visualizadores.Linea vLinea=null;
	public void buscarLinea(JTextField tx) {
		if (vLinea!=null){
			vLinea.close();
		}
		vLinea=new aplicacion.herramientas.java.visualizadores.Linea(this.getConstructor());
		vLinea.setIdproveedor(frame.get_txt_idproveedor().getText());
		int n = vLinea.buscarLinea(tx);
		if (n == 0) {
				aviso("no hay Lineas con ese codigo");
		}
	}
	
	public void quitar_items(){
		if (confirmar("confirme para quitar los items seleccionados:",3)){
			JTable table = frame.getJTable();
			int imports=0;
			if (table != null) {

				for (int i = table.getRowCount()-1; i >=0 ; i--) {
					boolean selected = false;
					try {
						selected = (Boolean) table.getValueAt(i, 0);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (selected) {
						DefaultTableModel model=(DefaultTableModel) table.getModel();
						model.removeRow(i);
						imports++;
					}

				}
				DefaultTableModel model=(DefaultTableModel) table.getModel();
				if (model.getRowCount()==0){
					model.setRowCount(1);
				}
			}
			aviso("Se Quitaron "+imports+" items");
			
		}
		
	}
	public void clean() {
		nuevo=false;
		frame.setJTable(null);
		this.clean_importacion();
		this.getToday();
		
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

	
	public Object[][] procesarDatos(Object[][] datos) {
		Object[][] tmp = new Object[datos.length][datos[0].length];
		
		for (int i = 0; i < datos.length; i++) {
			tmp[i][0] = (datos[i][0].toString().compareTo("1")==0);
			tmp[i][1] = datos[i][1];// idarticulo
			tmp[i][2] = datos[i][2];// descripcion
			tmp[i][3] = new Double((String)datos[i][3]);// stock
			tmp[i][4] = new Double((String)datos[i][4]);// cantidad
		}
		return tmp;
	}

	
	private void transfer_focus(int row, int col) {
		frame.getJTable().changeSelection(row, col, false, false);
		frame.getJTable().editCellAt(row, col);
		frame.getJTable().transferFocus();
	}

	public void crear_tabla() {
		Runnable execute=new Runnable(){
			public void run(){
				Object[][] results = new Object[][] { { true,"", "", "", "" } };
				create_table(results);			
			}
		};
		this.invokeLater(execute);
	}

	public void cargar_datos(String idtransferencia) {
		
		Object[][] results = data.getTransferenciaItems(idtransferencia);
		if (results != null) {
			if (results.length > 0) {
				results = this.procesarDatos(results);
				this.create_table(results);
			}
		}
	}

		public void getToday() {
		_Frame _frame = (_Frame) this._frame;
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

	
	public void _delete_item_articulo(JTextField tx, int row) {
		String value=tx.getText();
		if (value.compareTo("")==0){
			this.borrarRenglon(row);
		}
	}
	
	public void borrarRenglon(int row){
		if (preguntar("Confirmar","Elimina Renglon "+row+" de la tabla?")){
			try {
				frame.getJTable().getCellEditor().stopCellEditing();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			DefaultTableModel model=(DefaultTableModel) frame.getJTable().getModel();
			model.removeRow(row);
			if (model.getRowCount()<=0){
				model.setRowCount(1);
				model.setValueAt(true, 0, 0);
				this.editCell(0, 1);
			}
			
			cambios=true;
		}
	}
	
	
private aplicacion.ventas.catalogo.constructor._Constructor Catalogo=null;
	
	public void BuscarCatalogo(JTextField ext) {
		if (Catalogo != null) {
			Catalogo.dispose();
			Catalogo=null;
		}
		if (Catalogo == null) {
			Catalogo = 
				new aplicacion.ventas.catalogo.constructor._Constructor();
			
			Catalogo.setParameter(_parametros.connector, data.getConnectionHandler());
			Catalogo.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
			Catalogo.build(this.getConstructor());
			Catalogo.init();	
			this.getConstructor().addChild(Catalogo);
		}
		
		
		
		
		
	}
	public void editCell(int row,int col){
		JTable table=frame.getJTable();
		table.changeSelection(row, col, false,false);
		table.editCellAt(row, col);
		table.transferFocus();
	}

	public void editarArticulo(JTextField tx) {
		String idarticulo = tx.getText();
		Object[][] results = data.getArticulo(idarticulo);
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

		frame.setJTable(_table);
	}

	private void crear_empty_etiquetas() {
		Object[][] results = new Object[][] { { false, "", "", "" } };
		this.create_table_etiquetas(results);
	}

	public void cargarDeposito() {
		String iddeposito = this.getConstructor().getIdDeposito();

		frame.get_txt_iddeposito().setText(iddeposito);
		this.evaluarDeposito(frame.get_txt_iddeposito());
	}

	private aplicacion.herramientas.java.evaluadores.Deposito Deposito = null;

	public void initialize_Deposito() {
		
		Deposito = new aplicacion.herramientas.java.evaluadores.Deposito() {
			public void cargar(JTextField tx) {
				
				String codigo=tx.getText();
				Object[][] results = this.getInfo(codigo);
				String descripcion = (String) results[0][1];
				String cod = (String) results[0][0];
				if (tx.getName()==_Interface._txt_iddeposito){
					frame.get_txt_iddeposito().setText(cod);
					frame.get_txt_deposito_descripcion().setText(descripcion);
						
				}else{
					transfer_focus_articulos();
				}
				
			}
		};
		Deposito.setConstructor(this.getConstructor());
	}

	public void BuscarDeposito(JTextField tx) {
		Deposito.Buscar(tx);
	}

	public void BuscarDeposito() {
		Deposito.Buscar(frame.get_txt_iddeposito());
	}

	public void buscarDeposito(JTextField tx) {
		Deposito.buscar(tx);
	}

	public void evaluarDeposito(JTextField tx) {
		cambios = true;
		tx.setText(tx.getText().replaceAll(" ", ""));
		Deposito.evaluate(tx);
	}

	
	public void transfer_focus_articulos() {
		System.out.println("TRansfer Focus to Table");
		if (frame.getJTable() == null) {
			this.crear_tabla();

		} else {
			if (frame.getJTable().getRowCount() <= 0) {
				this.crear_tabla();
			}
		}

		frame.getJTable().requestFocusInWindow();
		frame.getJTable().changeSelection(0, 1, false, false);
		frame.getJTable().editCellAt(0, 1);
		frame.getJTable().transferFocus();
	}

	
	private aplicacion.herramientas.java.buscadores.Proveedor bProveedor=null;
	public void BuscarProveedor(JTextField ext) {
		 if (bProveedor==null){
			 bProveedor=new aplicacion.herramientas.java.buscadores.Proveedor(this.getConstructor());
		 }

	 bProveedor.Buscar(ext);
	}
		public void _eval_item_articulo(JTextField tx, int row) {
		String aux = "";
		cambios=true;
		
		try {
			aux = tx.getText();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		tx.requestFocusInWindow();
		if (aux.compareTo("") != 0) {
			if (aux.compareTo("*") == 0) {
				_evaluate_asterisco(tx, row);
				//error("NO ESTA PERMITIDO EL * EN TRANSFERENCIAS DE STOCK ");
			} else {
				if (aux.length() > 4) {
						if (aux.substring(3, 4).compareTo("-") == 0) {
							_evaluate_idarticulo(tx, row);
						} else {
							this.buscarArticulo(tx);
						}
					} else {
							this.buscarArticulo(tx);
					
					}
				

			}
		} else {
				aviso("Ingrese un Codigo de Articulo Valido");
				
				tx.requestFocusInWindow();
			

		}
	}

	public int existEmpty(){
		int exist=-1;
		int i=0;
		JTable table=frame.getJTable();
		if (table!=null){
			while (i<table.getRowCount() & exist<0){
				String _idarticulo=table.getValueAt(i, 1).toString();
				if (_idarticulo.compareTo("")==0){
					exist=i;
				}
				i++;
			}	
		}
		return exist;
	}
	
	public int existArticulo(String idarticulo){
		int exist=-1;
		int i=0;
		JTable table=frame.getJTable();
		if (table!=null){
			while (i<table.getRowCount() & exist<0){
				String _idarticulo=table.getValueAt(i, 1).toString();
				if (idarticulo.compareTo(_idarticulo)==0){
					exist=i;
				}
				i++;
			}	
		}
		return exist;
	}
	
	
	
	public void importar_critico() {
		JTable table = frame.getJTable1();
		int imports=0;
		if (table != null) {

			for (int i = 0; i < table.getRowCount(); i++) {
				boolean selected = false;
				try {
					selected = (Boolean) table.getValueAt(i, 0);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (selected) {
					imports++;
					String idarticulo = (String) table.getValueAt(i, 1);
					String descripcion = (String) table.getValueAt(i, 2);
					String qty= (String) table.getValueAt(i, 3);
					String linea = (String) table.getValueAt(i, 4);
					if (frame.getJTable() == null) {
						this.crear_tabla();
					}
					this.agregar(new Object[] { idarticulo, descripcion,
							qty,linea });
				}

			}
		}
		aviso("Se importaron "+imports+" items");
		frame.getJTabbedPane().setSelectedIndex(0);
		table.requestFocusInWindow();
	}
	
	public void agregar(Object[] seleccion){
		cambios=true;
		String idarticulo=(String) seleccion[0];
		String descripcion=(String) seleccion[1];
		String linea="";
		try {
			linea=(String) seleccion[3];
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String iddeposito=frame.get_txt_iddeposito().getText();
		double stock=data.getStockArticulo(idarticulo, iddeposito);
		if (stock<=0){
			stock=0.0;
		}
		String cantidad="1.0";
		String descuento="0.0";
		JTable table=frame.getJTable();
		try {
			table.getCellEditor().stopCellEditing();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		Object[][] results=data.getArticulo(idarticulo);
		if (results!=null){
			if (results.length>0){
				int exist=this.existArticulo(idarticulo);
				String costo=results[0][2].toString();
				String precio=results[0][1].toString();
				if (exist<0){
					int row=0;
					if (table!=null){
						
						row=this.existEmpty();
						if (row<0){
							DefaultTableModel model=(DefaultTableModel) table.getModel();
							model.setRowCount(model.getRowCount()+1);
							row=model.getRowCount()-1;	
						}
						
					}else{
						this.crear_tabla();
					}
					if (row<0){
						row=0;
					}
					table.setValueAt(true, row, 0);
					table.setValueAt(idarticulo, row, 1);
					table.setValueAt(descripcion, row, 2);
					boolean unidad=frame.get_chk_solo_una_etiqueta().isSelected();
					if (unidad){
						stock=1;
					}
					table.setValueAt(new Convertidor().getMoney(stock,2), row, 3);
		
					boolean especial=frame.get_chk_etiquetas_especiales().isSelected();
					table.setValueAt(especial, row, 4);
					
					
					
					table.changeSelection(row, 1, false,false);
					table.editCellAt(row, 1);
					table.transferFocus();
				}
				
				
				System.out.println(idarticulo+" "+descripcion+" "+cantidad+" "+costo+" "+precio);
				
			}
		}
	
	}
	
	public void buscarArticulo(JTextField tx) {
		if (vArticulo != null) {
			vArticulo.close();
		}
		vArticulo = new aplicacion.herramientas.java.visualizadores.Articulo(
				this.getConstructor());
		
		vArticulo.setPublico(true);
		vArticulo.setSuspendidov(true);
		int n = vArticulo.Buscar(tx);
		if (n == 0) {
			vArticulo.close();
			aviso("No hay articulos con ese codigo");
		}
	}
	
	public void _eval_item_descripcion(JTextField tx, int row) {
		cambios=true;
		String descripcion = tx.getText();
		if (descripcion.compareTo("")!=0){
			
			this.editCell(row, 3);
		}else{
			this.error("Ingrese una Descripcion");
			tx.requestFocusInWindow();
		}
	}
	public void _eval_item_cantidad(JTextField tx, int row) {
		cambios=true;
		String cantidad = tx.getText();
		cantidad = cantidad.replace(",", "");
		String idarticulo="";
		double stock=0.0;
		String iddeposito=frame.get_txt_iddeposito().getText();
		try {
			idarticulo = frame.getJTable().getValueAt(row, 1).toString();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (idarticulo.compareTo("")!=0){
			stock=data.getStockArticulo(idarticulo, iddeposito);	
		}
		if (stock<=0){
			
		}
		double qty = 0.0;
		boolean error = false;
		try {
			qty = new Double(cantidad);
		} catch (Exception e) {
			error = true;
		}
		if (!error) {
			if (qty  <=0) {
				qty=1;
				tx.setText(new Convertidor().getMoney(qty,2));
				tx.requestFocusInWindow();
			} else {
				tx.setText(new Convertidor().getMoney(qty,2));
				DefaultTableModel model = (DefaultTableModel) this.frame.getJTable().getModel();
				if (row == model.getRowCount() - 1) {
					model.setRowCount(model.getRowCount() + 1);
				} 
				boolean especial=frame.get_chk_etiquetas_especiales().isSelected();
				this.frame.getJTable().setValueAt(especial, row, 4);
				this.frame.getJTable().setValueAt(true, row, 0);
				this.editCell(row+1, 1);
			}
		} else {
			tx.setText(new Convertidor().getMoney(stock,2));
			tx.requestFocusInWindow();
			// error("error en cantidad");
		}
		
	}

	public void _evaluate_asterisco(JTextField tx, int row) {
		String idarticulo = tx.getText();
		if (idarticulo.compareTo("*")==0){
			this.editCell(row, 2);
		}
	}
	
	
	public void _evaluate_idarticulo(JTextField tx, int row) {
		cambios=true;
		String idarticulo = tx.getText();
		
		
		Object[][] results = data.getArticulo(idarticulo);
		boolean exist = false;
		boolean bloqueado = false;
		
		int equiv=0;
		if (results != null) {
			if (results.length > 0) {
				exist = true;
				String descripcion =(String) results[0][0];
				String stock = (String) results[0][6];
				double _stock=0.0;
				try {
					_stock=new Double(stock);
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				boolean ok=true;
				String cantidad="";
				try {
					cantidad=frame.getJTable().getValueAt(row, 3).toString();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
				double qty=0.0;
				try {
					qty=new Double(cantidad);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
				
						
				frame.getJTable().setValueAt(descripcion, row, 2);
				if (qty<=0){
					_stock=1;
					frame.getJTable().setValueAt(new Convertidor().getMoney(_stock,2), row, 3);	
				}
				
				boolean especial=frame.get_chk_etiquetas_especiales().isSelected();
				frame.getJTable().setValueAt(especial, row, 4);
				this.editCell(row, 3);
						} else {
							error("No se encontro el articulo ");
							tx.requestFocusInWindow();
						}
						
						//fillExtra(idarticulo);
				
					
					
				}
				
	}				
	
	public void cargar_stock(){
		String idproveedor=frame.get_txt_idproveedor().getText();
		String linea=frame.get_txt_linea().getText();
		String idarticulo_desde=frame.get_txt_articulo_desde().getText();
		String idarticulo_hasta=frame.get_txt_articulo_hasta().getText();
		String iddeposito=frame.get_txt_iddeposito().getText();
		boolean restriction=frame.get_chk_mostrar_faltante().isSelected();
		boolean ok=false;
		
			Object[][] results=data.getStock(idproveedor, linea, idarticulo_desde, idarticulo_hasta,iddeposito,restriction);
			if (results!=null){
				if (results.length>0){
					ok=true;
					Object[][] tmp=new Object[results.length][results[0].length+1];
					for (int i=0;i<results.length;i++){
						tmp[i][0]=false;
						for (int j=0;j<results[0].length;j++){
							tmp[i][j+1]=results[i][j];
						}
					}
					this.create_table_importar(tmp);
				}
			}	
		
		if (!ok){
			frame.setJTable1(null);
		}
		
	}

	

	
	
	public void cargar_historial(){
		String desde="01-01-1900";
		String hasta="01-01-2100";
		if (desde.compareTo("")!=0){
			desde=frame.get_txt_desde().getText();
		}
		if (hasta.compareTo("")!=0){
			hasta=frame.get_txt_hasta().getText();
		}
		
		boolean todas=frame.get_chk_mostrar_impresas().isSelected();
		boolean ok=false;
		
			Object[][] results=data.getHistorial(desde, hasta, todas);
			if (results!=null){
				if (results.length>0){
					ok=true;
					Object[][] tmp=new Object[results.length][results[0].length+1];
					for (int i=0;i<results.length;i++){
						tmp[i][0]=false;
						for (int j=0;j<results[0].length;j++){
							if (j==2){
								double qty=0.0;
								try {
									qty=new Double(results[i][j].toString());
								} catch (NumberFormatException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								tmp[i][j+1]=qty;
							}else{
								if (j==3){
									boolean especial=results[i][j].toString().compareTo("1")==0;
									tmp[i][j+1]=especial;
								}else{
									if (j==4){
										boolean impresa=results[i][j].toString().compareTo("1")==0;
										tmp[i][j+1]=impresa;
									}else{
										tmp[i][j+1]=results[i][j];	
									}
									
								}
									
							}
							
						}
					}
					this.create_table_historial(tmp);
				}
			}	
		
		if (!ok){
			frame.setJTable2(null);
		}
		
	}
	
	
	public void imprimir(){
		
		int errors=0;
		int operations=0;
		JTable table=frame.getJTable();
		if (table!=null){
			boolean ok=confirmar("Confirme Para Imprimir Etiquetas:",3);
			if (ok){
				for (int i=0;i<table.getRowCount();i++){
					boolean selected=false;
					try {
						selected=(Boolean) table.getValueAt(i, 0);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (selected){
						operations++;
						String idarticulo=table.getValueAt(i, 1).toString();
						String descripcion=table.getValueAt(i, 2).toString();
						String cantidad=table.getValueAt(i, 3).toString();
						boolean especial=(Boolean)table.getValueAt(i, 4);
						double _cant=0.0;
						try {
							_cant=new Double(cantidad);
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						boolean error=false;
						if(_cant>0 & idarticulo.compareTo("")!=0 & descripcion.compareTo("")!=0){
							
							String q="";
							if (especial){
								String w="90";
								String quita="0";
								q="insert into b_etiquetas (fecha,idarticulo,descripcion,cantidad,especial,especial_width,especial_height,quitarprefijo) ";
								q+="values (getdate(),'"+idarticulo+"','"+descripcion+"',"+_cant+",1,"+w+","+w+","+quita+")";
							}else{
								q="insert into b_etiquetas (fecha,idarticulo,descripcion,cantidad) ";
								q+="values (getdate(),'"+idarticulo+"','"+descripcion+"',"+_cant+")";	
							}
							
							data.clearBatch();
							data.addBatch(q);
							error=data.executeBatch();
						}else{
							error=true;
						}
						if (error){
							errors++;
						}	
					}
					
				}
				aviso("Se realizaron "+operations+" operaciones con "+errors+" errores ");
			}
		}
				
		
	
	}
	
	public void focus(){
		
	}
	
	private void Systemacumulate_sum(){
		
		for (int i=0;i<this.SFilters.size();i++){
			
			LinkedList filter=(LinkedList)this.SacumFilters.get(i);
			filter.addLast(frame.getJTable_fields().getValueAt(0, i).toString());
			SacumFilters.set(i, filter);
			frame.getJTable_fields().setValueAt("",0,i);
		}
	}
	
	public void addSystemColumn(columna c){
		if (!this.hasSystemColumn(c)){
			this.SColumns.add(c);	
		}
	}
	
	private boolean hasSystemColumn(columna c){
		boolean found=false;
		int i=0;
		while(i<SColumns.size() & !found){
			found=SColumns.get(i).getNombre().compareTo(c.getNombre())==0;
			i++;
		}
		return found;
	}
	
	private boolean hasSystemFilter(Filtro f){
		boolean found=false;
		int i=0;
		while(i<SFilters.size() & !found){
			found=SFilters.get(i).getNombre().compareTo(f.getNombre())==0;
			i++;
		}
		return found;
	}
	
	private CustomTable crearSystemTabla(Object[][] auzx){
		final CustomTable Table=new CustomTable();
		for (int i=0;i<SColumns.size();i++) {
			columna c=(columna)SColumns.get(i);
			Column col=new Column();
			col.setName(c.getAlias());
			col.setWidth(c.getWidth());
			col.setClass(c.getClase());
			if (c.getNombre().compareTo("")==0){
				col.setEditable(true);
				CheckBoxCellEditor chkce = new CheckBoxCellEditor();
				chkce.setItemListener(this.getConstructor().getItemListener());
				chkce.setTipo(Boolean.class);
				//chkce.setName(_Interface._table_chk_sistema);
				col.setCellEditor(chkce.getCellCheck());
				col.setWidth(20);
			}else{
				TableColorCellRenderer cellrender=	new TableColorCellRenderer();
				//cellrender.setLogic(this);
				col.setCellRenderer(cellrender);		
				
			}
			
			System.out.println("Agregando Columna?"+col.getName());
			Table.addColumn(col);		
		}
		
		Table.setData(auzx);
		Table.build();
		Table.fillData();
		
		
		return Table;
	}
	
	private String doSystemQuerys(){
		String tmpQuery;
		if (this.SColumns.contains("*")){
		tmpQuery="select * ";
		}else {
			tmpQuery="select ";
			if (Stop>0){
				tmpQuery=tmpQuery+" top "+Stop+" ";
			}
			for (int i=0;i<SColumns.size()-1;i++){
				columna column=(columna) SColumns.get(i);
				if (column.getNombre().compareTo("")!=0){
					tmpQuery=tmpQuery+column.getNombre()+",";	
				}
				
			}
			
			columna column=(columna) SColumns.get(SColumns.size()-1);
			tmpQuery=tmpQuery+column.getNombre()+" ";
			
		}
		tmpQuery=tmpQuery+" from "+this.SFromTable;
		
		int filterx=0;
		
		for (int i=0;i<SFilters.size();i++){
			
			String x=this.getSystemWhereString(i);
					if (x.compareTo("")!=0){
						if (filterx==0){
							tmpQuery=tmpQuery+" where ";
						}
						if (filterx>0) {
							tmpQuery=tmpQuery+" and ";
						}	
						tmpQuery=tmpQuery+" "+x;
						filterx++;
					}
		}
		
		if (SRestriction.compareTo("")!=0){
			if (filterx==0){
				tmpQuery=tmpQuery+" where ";
				filterx++;
			} else {
				tmpQuery=tmpQuery+" and ";
			}
			tmpQuery=tmpQuery+SRestriction;
		}
		
		if (this.getAutoRestriction()){
			if (filterx==0){
				tmpQuery=tmpQuery+" where ";
			} else {
				tmpQuery=tmpQuery+" and ";
			}
			tmpQuery=tmpQuery+this.getSystemAutoRestrictionQuery();
		}
		
		if (SGroup.compareTo("")!=0){
			
			tmpQuery=tmpQuery+" Group by "+SGroup;
		}
		if (!SOrders.isEmpty()){
			
			tmpQuery=tmpQuery+" order by ";
			for (int i=0;i<SOrders.size()-1;i++){
				tmpQuery=tmpQuery+SOrders.get(i)+",";
			}
			tmpQuery=tmpQuery+SOrders.get(SOrders.size()-1);
			
		}
		
		System.out.println(tmpQuery);
		
	 return tmpQuery;

	}
	
	public String getSystemAutoRestrictionQuery(){
		String auto="";
		for (int i=0;i<SFilters.size();i++){
			if (SFilters.get(i).getValor().compareTo("")!=0){
				if (auto.length()>0) auto+=" and ";
				auto+=SFilters.get(i).getNombre()+"  like '"+SFilters.get(i).getValor()+"' ";
			}
		}
		return auto;
	}
	
	public boolean getAutoRestriction(){
		boolean auto=false;
		int i=0;
		while (i<Filters.size() &!auto){
			auto=Filters.get(i).getValor().compareTo("")!=0;
			i++;
		}
		return auto;
	}
	
	private String getSystemWhereString(int i){
		String description="";
		Filtro filtro=(Filtro) SFilters.get(i);
		String column=filtro.getNombre();
		String descript=this.SacumFilters.get(i).toString();
		LinkedList filter=(LinkedList)this.SacumFilters.get(i);
		if (filter.size()>0){
			int j=0;
			while (j<filter.size()){
				String flt=(String) filter.get(j);
				String aux=flt;
				while (aux.contains(" ")){
					String sub=aux.substring(0,aux.indexOf(" "));
					if (sub.compareTo("")!=0){
						if (description.length()>0){
							description=description+" and ";
						}
						description=description+" "+column+" like '%"+sub+"%'";
					}
					aux=aux.substring(aux.indexOf(" ")+1);
				}
				if (aux.compareTo("")!=0){
						if (description.length()>0){
							description=description+" and ";
						}
						description=description+"  "+column+" like '%"+aux+"%'";
				}
			
				j++;
			}
		}
		return description;
	}
	
	
	public void setSystemColumns(){
		columna c = new columna();
		Filtro f = new Filtro();
	
		c.setNombre("v.idarticulo");
		c.setAlias("idarticulo");
		c.setWidth(110);
		c.setMaster(true);
		addSystemColumn(c);

		c = new columna();
		c.setNombre("v.descripcion");
		c.setAlias("descripcion");
		c.setWidth(340);
		c.setMaster(false);
		addSystemColumn(c);

		c = new columna();
		c.setNombre("v.descripabrev");
		c.setAlias("linea");
		c.setWidth(140);
		c.setMaster(false);
		addSystemColumn(c);
		
		c = new columna();
		c.setNombre("round(isnull(v.precio2,0),2)");
		c.setAlias("Publico");
		c.setWidth(60);
		c.setMaster(false);
		addSystemColumn(c);
		
		c = new columna();
		//c.setNombre("isnull(s.cantidadud,0)");
		c.setNombre("sum(isnull(s.cantidadud,0))");
		c.setAlias("stock");
		c.setWidth(50);
		c.setMaster(false);
		addSystemColumn(c);
		
		c = new columna();
		c.setNombre("v.cuentaproveedor");
		c.setAlias("idproveedor");
		c.setWidth(100);
		c.setMaster(false);
		addSystemColumn(c);
		addSystemFromTable("V_MA_ARTICULOS v LEFT OUTER JOIN v_mv_stock s ON  v.IDARTICULO = s.IdArticulo and s.anulado=0 ");
		//addSystemFromTable("V_MA_ARTICULOS v LEFT OUTER JOIN Stk_Stock_UDKG s ON  v.IDARTICULO = s.IdArticulo ");
		f = new Filtro();
		f.setNombre("v.idarticulo");
		f.setAlias("idarticulo");
		f.setWidth(120);
		addSystemFilter(f);
		f = new Filtro();
		f.setNombre("v.descripcion");
		f.setAlias("descripcion");
		f.setWidth(350);
		f.setFocus(true);
		addSystemFilter(f);
		f = new Filtro();
		f.setNombre("v.descripabrev");
		f.setAlias("linea");
		f.setWidth(150);
		addSystemFilter(f);
		
		f = new Filtro();
		f.setNombre("v.cuentaproveedor");
		f.setAlias("idproveedor");
		f.setWidth(150);
		addSystemFilter(f);
		
		//addSystemOrder("isnull(s.cantidadud,0) desc,v.idarticulo");
		addSystemOrder("sum(isnull(s.cantidadud,0)) desc,v.idarticulo");
		//addSystemRestriction(" v.idarticulo between '001-000' and '999-zzzz' ");
		
		addSystemGroup("v.idarticulo,v.descripcion,v.descripabrev,v.precio2,v.cuentaproveedor");
		
		//setSystemTop(Stop);
		
	}
	
	public void setSystemTop(int top){
		this.Stop=top;
	}
	public void addSystemOrder(String Column){
		this.SOrders.add(Column);
	}
	public void addSystemFilter(Filtro filtro){
		if (!this.hasSystemFilter(filtro)){
			this.SFilters.add(filtro);	
		}
		
	}
	
	public void addSystemFromTable(String From){
		this.SFromTable=From;
	}
	public void addSystemRestriction(String restriction){
		this.SRestriction=restriction;
	}
	public void addSystemGroup(String rest){
		this.SGroup=rest;
	}
}
