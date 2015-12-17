package aplicacion.inventario.planilla.logic;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.*;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

import aplicacion.herramientas.java.table.*;
import aplicacion.herramientas.java.Crono;
import aplicacion.herramientas.java.Convertidor;
import aplicacion.herramientas.java.launcher.logic.SwingWorker;
import aplicacion.herramientas.java.sortableselector.logic.Filtro;
import aplicacion.herramientas.java.sortableselector.logic.columna;
import aplicacion.herramientas.java.table.CellEditor;
import aplicacion.herramientas.java.table.Column;
import aplicacion.herramientas.java.table.CustomCellEditor;
import aplicacion.herramientas.java.table.CustomTable;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Data;
import aplicacion.modelo.logic.Logic;
import aplicacion.inventario.planilla.gui._Frame;
import aplicacion.inventario.planilla.interfaces.*;
import aplicacion.inventario.planilla.logic._Data;

public class _Logic extends Logic {
	private _Frame frame;
	private _Data data;
	private boolean actualiza = false;
	private Object[][] memoria = null;
	private aplicacion.inventario.politica.constructor._Constructor editor_politica = null;
	private aplicacion.herramientas.java.visualselector.constructor._Constructor vSelector = null;
	private aplicacion.herramientas.java.importar.constructor._Constructor importar = null;
	private aplicacion.herramientas.java.exportar.constructor._Constructor exportar = null;
	private aplicacion.inventario.articulo.constructor._Constructor articulo = null;
	private aplicacion.herramientas.java.buscadores.Articulo bArticulo = null;
	private aplicacion.herramientas.java.visualizadores.Articulo vArticulo = null;
	// variables de tareas swingwork
	private String estado = "";
	private int current;

	private int lenght;
	private boolean debug, done, canceled, override;
	private Timer Timer; // @jve:decl-index=0:
	private Crono crono;
	private int errors = 0;
	private boolean[] marquer;

	public void setFrame(JFrame _frame) {
		this.frame = (_Frame) _frame;
		super.setFrame(_frame);
	}

	public void setData(Data _data) {
		this.data = (_Data) _data;
		super.setData(_data);
	}

	public void BuscarProveedor() {
		this.BuscarProveedor(frame.get_txt_idproveedor());
	}

	public void _desmarcar() {
		for (int row = 0; row < marquer.length; row++) {
			marquer[row] = false;
			current = row;
		}
	}

	public void _marcar_alias() {
		int row = 0;
		int count = 0;
		while (row < marquer.length & !canceled) {
			String idarticulo = frame.getJTable().getValueAt(row, 1).toString();
			boolean selected = (Boolean) frame.getJTable().getValueAt(row, 0);
			if (selected) {
				estado = "Obteniendo Valores para " + idarticulo;
				current = count;
				marquer[row] = this.exist_alias_row(row);
				count++;
			}

			row++;
		}
		done = true;
	}

	private aplicacion.herramientas.java.buscadores.Proveedor bProveedor = null;

	public void BuscarProveedor(JTextField ext) {
		if (bProveedor == null) {
			bProveedor = new aplicacion.herramientas.java.buscadores.Proveedor(
					this.getConstructor());
		}

		bProveedor.Buscar(ext);
	}

	public void deseleccionar() {
		frame.getJTable().clearSelection();
		frame.getJTable().transferFocus();
	}

	public void seleccionar(int col) {

		if (frame.getJTable().getSelectedColumn() == col) {
			System.out.println("click en header+" + col);
			frame.getJTable().clearSelection();
			frame.getJTable().setColumnSelectionInterval(col, col);
			int row = 0;
			boolean toggle = false;
			boolean extend = false;
			frame.getJTable().changeSelection(row, col, toggle, extend);
			row = frame.getJTable().getRowCount();
			toggle = false;
			extend = true;
			frame.getJTable().changeSelection(row, col, toggle, extend);
		}
	}

	public void editar_articulo(JTable table, int row) {
		String idarticulo = table.getValueAt(row, 1).toString();
		if (idarticulo.compareTo("") != 0) {
			editar_articulo(idarticulo);
		}
	}

	public void editar_articulo() {
		int row = -1;
		row = frame.getJTable().getSelectedRow();
		if (row >= 0) {
			this.editar_articulo(frame.getJTable(), row);
		}
	}

	public void editar_articulo(String idarticulo) {
		if (articulo != null) {
			articulo.dispose();
		}
		articulo = new aplicacion.inventario.articulo.constructor._Constructor();
		articulo.setParameter(_parametros.LookAndFeel, this.getConstructor()
				.getLookAndFeelTheme());
		articulo.setParameter(_parametros.connector, this.getConstructor()
				.getConnectionHandler());
		articulo.setParameter(_parametros.iduser, this.getConstructor()
				.getIduser());
		articulo
				.setParameter(
						aplicacion.inventario.articulo.interfaces._parametros.idarticulo,
						idarticulo);
		articulo.build(this.getConstructor());
		articulo.init();
	}

	/**
	 * chk|idarticulo|descripcion|linea|proveedor|lista|politica|costo|precio2|
	 * actualizacion| compras|ventas|stock|actual|clasificacion
	 * 
	 * @param results
	 */
	private void create_table(Object[][] results) {
		CustomTable table = new CustomTable();
		CellEditor pce = null;
		Column col = null;
		// TableItemColorCellRenderer color=new TableItemColorCellRenderer();
		// color.setLogic(this);

		col = new Column();
		col.setName("");
		col.setWidth(40);
		col.setEditable(true);
		col.setClass(Boolean.class);

		table.addColumn(col);

		col = new Column();
		col.setName("idArticulo");
		col.setWidth(140);
		col.setEditable(true);
		col.setClass(String.class);
		table.addColumn(col);
		col = new Column();
		col.setName("Descripcion");
		col.setWidth(300);
		col.setEditable(true);
		col.setClass(String.class);
		col.setAligment(JTextField.LEFT);
		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_descripcion);
		pce.setTipo(String.class);
		pce.setAligment(JTextField.LEFT);
		col.setCellEditor(pce.getCellEditor());
		// col.setCellRenderer(color);
		table.addColumn(col);

		col = new Column();
		col.setName("Linea");
		col.setWidth(140);
		col.setEditable(true);
		col.setClass(String.class);
		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_linea);
		pce.setTipo(String.class);
		col.setAligment(JTextField.RIGHT);
		col.setCellEditor(pce.getCellEditor());
		// col.setCellRenderer(color);
		table.addColumn(col);

		col = new Column();
		col.setName("Proveedor");
		col.setWidth(120);
		col.setEditable(true);
		col.setClass(String.class);
		col.setAligment(JTextField.RIGHT);
		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_proveedor);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());
		// col.setCellRenderer(color);
		table.addColumn(col);

		col = new Column();
		col.setName("Lista");
		col.setWidth(100);
		col.setEditable(true);
		col.setClass(Double.class);
		col.setAligment(JTextField.RIGHT);
		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_lista);
		pce.setTipo(Double.class);
		col.setCellEditor(pce.getCellEditor());
		// col.setCellRenderer(color);
		table.addColumn(col);

		col = new Column();
		col.setName("politica");
		col.setWidth(100);
		col.setEditable(true);
		col.setClass(String.class);
		col.setAligment(JTextField.RIGHT);
		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_politica);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());
		// col.setCellRenderer(color);
		table.addColumn(col);

		col = new Column();
		col.setName("Costo");
		col.setWidth(100);
		col.setEditable(true);
		col.setClass(Double.class);
		col.setAligment(JTextField.RIGHT);
		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_costo);
		pce.setTipo(Double.class);
		col.setCellEditor(pce.getCellEditor());
		// col.setCellRenderer(color);
		table.addColumn(col);

		col = new Column();
		col.setName("Publico");
		col.setWidth(100);
		col.setEditable(true);
		col.setClass(Double.class);
		col.setAligment(JTextField.RIGHT);
		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_publico);
		pce.setTipo(Double.class);
		col.setCellEditor(pce.getCellEditor());
		// col.setCellRenderer(color);
		table.addColumn(col);

		col = new Column();
		col.setName("Actualizacion");
		col.setWidth(120);
		col.setClass(Date.class);
		col.setCellRenderer(new DateRenderer());
		col.setEditable(false);
		col.setClass(Date.class);
		table.addColumn(col);

		col = new Column();
		col.setName("compras");
		col.setWidth(50);
		col.setClass(Boolean.class);
		col.setEditable(true);
		table.addColumn(col);

		col = new Column();
		col.setName("ventas");
		col.setWidth(50);
		col.setClass(Boolean.class);
		col.setEditable(true);
		table.addColumn(col);

		col = new Column();
		col.setName("Stock");
		col.setWidth(80);
		col.setEditable(false);
		col.setClass(Double.class);
		col.setAligment(JTextField.RIGHT);
		// col.setCellRenderer(color);
		table.addColumn(col);

		col = new Column();
		col.setName("Prov. Act");
		col.setWidth(120);
		col.setEditable(true);
		col.setClass(String.class);
		col.setAligment(JTextField.RIGHT);
		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_proveedor_actualizacion);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());
		// col.setCellRenderer(color);
		table.addColumn(col);

		table.addColumn(col);
		col = new Column();
		col.setName("Clasificacion");
		col.setWidth(120);
		col.setEditable(true);
		col.setClass(String.class);
		col.setAligment(JTextField.LEFT);
		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_clasificacion);
		pce.setTipo(String.class);
		pce.setAligment(JTextField.LEFT);
		col.setCellEditor(pce.getCellEditor());
		// col.setCellRenderer(color);
		table.addColumn(col);
		results = this.procesarDatos(results);
		table.setData(results);
		Font fuente = new Font("Arial", Font.PLAIN, 9);
		table.setHeaderFont(fuente);
		table.setFont(fuente);
		table.build();
		table.fillData();
		JTable _table = table.getTable();
		_table.setName(_Interface._table);

		_table.addMouseListener(this.getConstructor().getMouseListener());
		_table.addKeyListener(this.getConstructor().getKeyListener());
		_table.getTableHeader().addMouseListener(
				this.getConstructor().getMouseListener());
		_table.setColumnSelectionAllowed(false);
		frame.setJTable(table.getTable());
	}

	public void clean() {
		frame.setJTable(null);
		frame.get_txt_descripcion_proveedor().setText("");
		frame.get_txt_idarticulo_desde().setText("");
		frame.get_txt_idarticulo_hasta().setText("");
		frame.get_txt_idproveedor().setText("");
		frame.get_txt_limite().setText("10000");
		frame.get_txt_linea().setText("");
		frame.getJProgressBar().setIndeterminate(false);
		frame.getJProgressBar().setString("");
		frame.get_txt_cuenta_actualizacion().setText("");
		frame.get_txt_descripcion_no().setText("");

	}

	public boolean exist_alias_row(int row) {
		boolean exist = false;
		String idarticulo = frame.getJTable().getValueAt(row, 1).toString();
		String idproveedor = frame.getJTable().getValueAt(row, 4).toString();
		exist = data.check_alias(idarticulo, idproveedor);
		return exist;
	}

	public Color getForegroundColor(int row, int col) {
		Color _foreground = new Color(0, 0, 0);
		if (frame.get_list_marcar().getSelectedIndex() == 1) {
			if (marquer[row]) {
				_foreground = new Color(255, 155, 155);
			}
		}
		return _foreground;
	}

	public Color getBackgroundColor(int row, int col) {
		Color _background = new Color(255, 255, 255);
		return _background;
	}

	private aplicacion.herramientas.java.visualizadores.Proveedor vProveedor = null;

	public void buscarProveedor(JTextField tx) {
		if (vProveedor != null) {
			vProveedor.close();
		}
		vProveedor = new aplicacion.herramientas.java.visualizadores.Proveedor(
				this.getConstructor());
		int n = vProveedor.Buscar(tx);
		if (n == 0) {
			aviso("no hay Proveedors con ese codigo");
		}
	}

	private aplicacion.herramientas.java.buscadores.Politica bPolitica = null;

	public void BuscarPolitica(JTextField ext) {
		if (bPolitica == null) {
			bPolitica = new aplicacion.herramientas.java.buscadores.Politica(
					this.getConstructor());
		}

		bPolitica.Buscar(ext);
	}

	private aplicacion.herramientas.java.visualizadores.Politica vPolitica = null;

	public void buscarPolitica(JTextField tx) {
		if (vPolitica != null) {
			vPolitica.close();
		}
		vPolitica = new aplicacion.herramientas.java.visualizadores.Politica(
				this.getConstructor());
		int n = vPolitica.Buscar(tx);
		if (n == 0) {
			aviso("no hay Politicas con ese codigo");
		}
	}

	public void BuscarLinea(JTextField tx) {
		if (bLinea == null) {
			bLinea = new aplicacion.herramientas.java.buscadores.Linea(this
					.getConstructor());
		}

		bLinea.setIdproveedor(frame.get_txt_idproveedor());
		bLinea.BuscarLinea(tx);
	}

	public void BuscarLinea() {
		this.BuscarLinea(frame.get_txt_linea());
	}

	private aplicacion.herramientas.java.buscadores.Linea bLinea;

	public void BuscarLinea(JTextField tx, int row) {
		CustomCellEditor cell = (CustomCellEditor) frame.getJTable()
				.getCellEditor(row, 8);
		JTextField idproveedor = (JTextField) cell.getComponent();
		if (bLinea == null) {
			bLinea = new aplicacion.herramientas.java.buscadores.Linea(this
					.getConstructor());
		}

		bLinea.setIdproveedor(idproveedor);

		boolean ok = data.check_proveedor(idproveedor.getText())
				& idproveedor.getText().compareTo("") != 0;
		if (ok) {
			bLinea.BuscarLineaProveedor(tx);
		} else {
			aviso("Para buscar una linea en esta fila, debe ingresar un proveedor valido primero!");
		}
	}

	public void BuscarLineaTabla(JTextField txt, int row, int col) {
		aplicacion.herramientas.java.sortableselector.constructor._Constructor CC = new aplicacion.herramientas.java.sortableselector.constructor._Constructor();
		CC.setParameter(_parametros.connector, this.getConstructor()
				.getConnectionHandler());
		CC.build(this.getConstructor());

		CC.init();
		aplicacion.herramientas.java.sortableselector.logic._Logic logic = (aplicacion.herramientas.java.sortableselector.logic._Logic) CC
				.getLogic();

		String idproveedor = "";
		try {
			idproveedor = (String) frame.getJTable().getValueAt(row, 4);
		} catch (Exception e) {

		}
		boolean ok = false;

		ok = idproveedor.compareTo("") != 0;
		if (ok) {
			ok = data.check_proveedor(idproveedor);
		}
		if (ok) {
			columna c = new columna();
			c.setNombre("c.lineaproveedor");
			c.setAlias("Linea");
			c.setWidth(200);
			c.setMaster(true);
			c.setColumnField(txt);
			logic.addColumn(c);

			c = new columna();
			c.setNombre("c.idproveedor");
			c.setAlias("Cod.Proveedor");
			c.setWidth(120);
			logic.addColumn(c);
			c = new columna();
			c.setNombre("m.descripcion");
			c.setAlias("Des.Proveedor");
			c.setWidth(180);

			logic
					.addFromTable("ma_cuentas m left outer join b_codigos c on m.codigo=c.idproveedor ");
			Filtro f = new Filtro();
			f.setNombre("c.lineaproveedor");
			f.setAlias("Linea Proveedor");
			f.setWidth(200);
			f.setFocus(true);
			logic.addFilter(f);

			logic
					.addRestriction("c.lineaproveedor not like '' and c.idproveedor like '"
							+ idproveedor + "'");
			logic.addGroup("c.lineaproveedor,c.idproveedor,m.descripcion");
			logic.addOrder("c.lineaproveedor");
			logic.init();
		} else {
			aviso("Para buscar una linea en esta fila, debe ingresar un proveedor valido primero!");
		}

	}

	public double convert_formula(String formula) {
		double tmp = 0.0;
		try {
			tmp = new Convertidor().formula(formula);
		} catch (Exception e) {

		}
		return tmp;
	}

	public void BuscarArticuloDesde() {
		this.BuscarCatalogo(frame.get_txt_idarticulo_desde());
	}

	public void BuscarArticuloHasta() {
		this.BuscarCatalogo(frame.get_txt_idarticulo_hasta());
	}

	private aplicacion.ventas.catalogo.constructor._Constructor Catalogo = null;

	public void BuscarCatalogo(JTextField ext) {
		if (Catalogo != null) {
			Catalogo.dispose();
			Catalogo = null;
		}
		if (Catalogo == null) {
			Catalogo = new aplicacion.ventas.catalogo.constructor._Constructor();

			if (!(ext.getParent() instanceof JTable)) {
				Catalogo
						.setParameter(
								aplicacion.ventas.catalogo.interfaces._parametros._txt_caller,
								ext);
			}

			Catalogo.setParameter(_parametros.connector, data
					.getConnectionHandler());
			Catalogo.setParameter(_parametros.LookAndFeel, this
					.getConstructor().getLookAndFeelTheme());
			Catalogo.build(this.getConstructor());
			Catalogo.init();

			this.getConstructor().addChild(Catalogo);
		}

	}

	public void buscarArticulo(JTextField tx) {
		if (vArticulo != null) {
			vArticulo.close();
		}
		vArticulo = new aplicacion.herramientas.java.visualizadores.Articulo(
				this.getConstructor());
		vArticulo.setIdproveedor(frame.get_txt_idproveedor().getText());
		int n = vArticulo.Buscar(tx);
		if (n == 0) {
			vArticulo.close();
			aviso("no hay articulos con ese codigo");

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
					if (frame.get_chk_verificar_articulos().isSelected()) {
						if (data.check_idarticulo(idarticulo)) {
							frame.get_txt_idarticulo_hasta()
									.requestFocusInWindow();
						} else {
							error("Codigo de Articulo " + idarticulo
									+ " Inexistente ");
						}
					} else {
						frame.get_txt_idarticulo_hasta().requestFocusInWindow();
					}

				} else {
					this.buscarArticulo(tx);
				}
			} else {
				this.buscarArticulo(tx);
			}
		}
	}

	public void evaluar_descripcion(JTextField tx) {
		this.goCargar();
	}

	public void evaluar_idarticulo_hasta(JTextField tx) {
		String idarticulo = "";
		idarticulo = tx.getText();
		if (idarticulo.compareTo("") == 0) {
			aviso("Ingrese un Articulo. Busqueda F5");
		} else {
			if (idarticulo.length() > 4) {
				if (idarticulo.substring(3, 4).compareTo("-") == 0) {
					if (frame.get_chk_verificar_articulos().isSelected()) {
						if (data.check_idarticulo(idarticulo)) {
							this.goCargar();
						} else {
							error("Codigo de Articulo " + idarticulo
									+ " Inexistente ");
						}
					} else {
						this.goCargar();
					}

				} else {
					this.buscarArticulo(tx);
				}
			} else {
				this.buscarArticulo(tx);
			}
		}
	}

	public void focus() {
		frame.get_txt_idproveedor().requestFocusInWindow();
	}

	public Object[][] procesarDatos(Object[][] datos) {
		Object[][] tmp = new Object[datos.length][datos[0].length];
		for (int i = 0; i < datos.length; i++) {
			tmp[i][0] = false;
			tmp[i][1] = datos[i][1];// id
			tmp[i][2] = datos[i][2];// desc
			tmp[i][3] = datos[i][3];// linea
			tmp[i][4] = datos[i][4];// prov

			String lista = (String) datos[i][5];
			String costo = (String) datos[i][7];
			String publico = (String) datos[i][8];
			String stock = (String) datos[i][12];
			double _costo = 0.0;
			double _lista = 0.0;
			double _publico = 0.0;
			try {
				_costo = new Double(costo);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				_lista = new Double(lista);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				_publico = new Double(publico);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			tmp[i][5] = new Convertidor().roundDouble(_lista, 2);
			tmp[i][6] = datos[i][6];// polit
			tmp[i][7] = new Convertidor().roundDouble(_costo, 2);
			tmp[i][8] = new Convertidor().roundDouble(_publico, 2);

			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

			Date date = null;
			try {
				date = (Date) formatter.parse(datos[i][9].toString());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				// e1.printStackTrace();
			}
			System.out.println(i + "> asignando fecha>" + date);
			if (date == null) {
				date = new Convertidor().getDate("01-01-2000");
			}
			tmp[i][9] = date;
			tmp[i][10] = datos[i][10].toString().compareTo("1") == 0;
			tmp[i][11] = datos[i][11].toString().compareTo("1") == 0;
			double _stock = 0.0;
			try {
				_stock = new Double(stock);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tmp[i][12] = new Convertidor().roundDouble(_stock, 2);
			tmp[i][13] = datos[i][13];// cuenta

		}
		return tmp;
	}

	private int getTop() {
		int top = 0;
		try {
			top = new Integer(frame.get_txt_limite().getText());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return top;
	}

	public void eliminar() {
		aviso("Operacion no Implementada");
	}

	public void _taskworkCargar() {

		frame.getJProgressBar().setIndeterminate(true);
		int top = this.getTop();
		String desde = frame.get_txt_idarticulo_desde().getText();
		String hasta = frame.get_txt_idarticulo_hasta().getText();
		if (desde.compareTo("") == 0) {
			desde = "001-000";
		}
		if (hasta.compareTo("") == 0) {
			hasta = "999-000";
		}
		String idproveedor = frame.get_txt_idproveedor().getText();
		String linea = frame.get_txt_linea().getText();
		String descripcion = frame.get_txt_descripcion().getText();
		String no_descripcion = frame.get_txt_descripcion_no().getText();
		String idproveedor_actualizacion = frame.get_txt_cuenta_actualizacion().getText();
		boolean stock = frame.get_chk_stock().isSelected();

		final Object[][] results = data.getArticulos(top, idproveedor, linea,
				desde, hasta, descripcion, no_descripcion, stock,
				idproveedor_actualizacion);
		if (results != null) {
			if (results.length > 0) {
				Runnable _execute = new Runnable() {
					public void run() {
						create_table(results);
					}
				};
				this.invokeLater(_execute);
			} else {
				Runnable _execute = new Runnable() {
					public void run() {
						frame.setJTable(null);
					}
				};
				this.invokeLater(_execute);
			}

		} else {
			Runnable _execute = new Runnable() {
				public void run() {
					frame.setJTable(null);
				}
			};
			this.invokeLater(_execute);
		}

		done = true;
	}

	public void _taskworkMarcar() {
		if (frame.getJTable() != null) {

			if (frame.get_list_marcar().getSelectedIndex() == 0) {
				this._desmarcar();
			} else {
				this.lenght = this.getSelected();
				if (lenght <= 0) {
					aviso("debe seleccionar los articulos que quiere que sean marcados");
				} else {
					this.marquer = new boolean[frame.getJTable().getRowCount()];
					if (frame.get_list_marcar().getSelectedIndex() == 1) {
						this._marcar_alias();
					}
				}
			}
			done = true;
			frame.getJTable().repaint();

		}
	}

	public void evaluar_table_check(JCheckBox chk, int row, int col) {

	}

	private void transfer_focus(int row, int col) {
		if (row < frame.getJTable().getRowCount() - 1) {
			frame.getJTable().changeSelection(row, col, false, false);
			frame.getJTable().editCellAt(row, col);
			frame.getJTable().transferFocus();
		}

	}

	public void evaluar_table_descripcion(JTextField tx, int row, int col) {
		String valor = "";
		valor = tx.getText();
		if (valor.compareTo("") != 0) {

			if (frame.get_lst_desplazamiento().getSelectedIndex() == 1) {
				this.transfer_focus(row, col + 1);
			} else {
				this.transfer_focus(row + 1, col);
			}
			/*
			 * boolean error=this.guardar_row_monitor(row); if (error){
			 * this.transfer_focus(row, col); }
			 */
		} else {
			tx.requestFocusInWindow();
		}
	}

	public void evaluar_table_proveedor(JTextField tx, int row, int col) {
		String valor = "";
		valor = tx.getText();
		if (valor.compareTo("") != 0) {
			if (data.check_proveedor(valor)) {
				if (frame.get_lst_desplazamiento().getSelectedIndex() == 1) {
					this.transfer_focus(row, col + 1);
				} else {
					this.transfer_focus(row + 1, col);
				}
				/*
				 * boolean error=this.guardar_row_monitor(row); if (error){
				 * this.transfer_focus(row, col); }
				 */
			} else {
				error("codigo de proveedor incorrecto");
				tx.requestFocusInWindow();
			}

		} else {
			tx.requestFocusInWindow();
		}
	}

	public void evaluar_table_proveedor_actualizacion(JTextField tx, int row,
			int col) {
		String valor = "";
		valor = tx.getText();
		if (valor.compareTo("") != 0) {
			if (data.check_proveedor(valor)) {
				if (frame.get_lst_desplazamiento().getSelectedIndex() == 1) {
					this.transfer_focus(row, col + 1);
				} else {
					this.transfer_focus(row + 1, col);
				}
				/*
				 * boolean error=this.guardar_row_monitor(row); if (error){
				 * this.transfer_focus(row, col); }
				 */
			} else {
				error("codigo de proveedor incorrecto");
				tx.requestFocusInWindow();
			}

		} else {
			tx.requestFocusInWindow();
		}
	}

	public void evaluar_table_clasificacion(JTextField tx, int row, int col) {
		String valor = "";
		valor = tx.getText();
		if (valor.compareTo("") != 0) {
			if (frame.get_lst_desplazamiento().getSelectedIndex() == 1) {

				this.transfer_focus(row + 1, 1);
			} else {
				this.transfer_focus(row + 1, col);
			}

		} else {
			tx.requestFocusInWindow();
		}
	}

	public void evaluar_table_linea(JTextField tx, int row, int col) {
		String valor = "";
		valor = tx.getText();
		if (valor.compareTo("") != 0) {
			if (frame.get_lst_desplazamiento().getSelectedIndex() == 1) {
				this.transfer_focus(row, col + 1);
			} else {
				this.transfer_focus(row + 1, col);
			}
			/*
			 * boolean error=this.guardar_row_monitor(row); if (error){
			 * this.transfer_focus(row, col); }
			 */
		} else {
			tx.requestFocusInWindow();
		}
	}

	public void showRow(int row) {
		String idarticulo = "";
		try {
			idarticulo = (String) frame.getJTable().getValueAt(row, 1);
		} catch (Exception e) {

		}
		if (idarticulo.compareTo("") != 0) {
			fillExtra(idarticulo);
		} else {
			frame.get_txt_articulo_descripcion().setText("");
			frame.get_txt_articulo_stock().setText("");
		}
	}

	public void _move_down(int row) {
		/*
		 * frame.getJTable().changeSelection(row+1, 1, false,false);
		 * frame.getJTable().editCellAt(row+1, 1);
		 * frame.getJTable().transferFocus();
		 */
		if (row < frame.getJTable().getRowCount() - 1) {
			row++;

			this.showRow(row);
		}

	}

	public void _move_up(int row) {
		/*
		 * frame.getJTable().changeSelection(row-1, 1, false,false);
		 * frame.getJTable().editCellAt(row-1, 1);
		 * frame.getJTable().transferFocus();
		 */

		if (row > 0) {
			row--;
			this.showRow(row);
		}

	}

	public void completar_politica(String pol, int row, int col) {

		String valor = frame.getJTable().getValueAt(row, col).toString();

		if (valor.compareTo("") != 0) {
			if (data.check_politica(valor)) {
				Object[][] results = data.getPolitica(valor);
				if (results != null) {
					if (results.length > 0) {
						String fcosto = (String) results[0][2];
						String fpublico = (String) results[0][3];
						double mcosto = this.convert_formula(fcosto);
						double mpublico = this.convert_formula(fpublico);
						double lista = (Double) frame.getJTable().getValueAt(
								row, 5);
						double costo = lista * mcosto;
						double publico = costo * mpublico;
						frame.getJTable().setValueAt(
								new Convertidor().getMoney(lista, 2), row, 5);
						frame.getJTable().setValueAt(
								new Convertidor().getMoney(costo, 2), row, 7);
						frame.getJTable().setValueAt(
								new Convertidor().getMoney(publico, 2), row, 8);
					}
				}
			} else {
				// error
			}
		} else {
			// error;
		}
	}

	public boolean evaluar_table_politica(String valor, int row, int col) {
		boolean error = false;
		if (valor.compareTo("") != 0) {
			if (data.check_politica(valor)) {
				Object[][] results = data.getPolitica(valor);
				if (results != null) {
					if (results.length > 0) {
						String fcosto = (String) results[0][2];
						String fpublico = (String) results[0][3];
						double mcosto = this.convert_formula(fcosto);
						double mpublico = this.convert_formula(fpublico);
						double lista = 0.0;
						try {
							lista = new Double(frame.getJTable().getValueAt(
									row, 5).toString());
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						double costo = lista * mcosto;
						double publico = costo * mpublico;
						frame.getJTable().setValueAt(
								new Convertidor().getMoney(lista, 2), row, 5);
						frame.getJTable().setValueAt(
								new Convertidor().getMoney(costo, 2), row, 7);
						frame.getJTable().setValueAt(
								new Convertidor().getMoney(publico, 2), row, 8);

					}
				}
			} else {
				error = true;
			}
		}
		return error;
	}

	public boolean evaluar_table_politica_actualizacion(String valor, int row,
			int col) {
		boolean error = false;
		if (valor.compareTo("") != 0) {
			if (data.check_politica(valor)) {
				Object[][] results = data.getPolitica(valor);
				if (results != null) {
					if (results.length > 0) {
						String fcosto = (String) results[0][2];
						String fpublico = (String) results[0][3];
						double mcosto = this.convert_formula(fcosto);
						double mpublico = this.convert_formula(fpublico);
						double lista = (Double) frame.getJTable().getValueAt(
								row, 5);
						double costo = lista * mcosto;
						double publico = costo * mpublico;
						frame.getJTable().setValueAt(
								new Convertidor().getMoney(costo, 2), row, 7);
						frame.getJTable().setValueAt(
								new Convertidor().getMoney(publico, 2), row, 8);

					}
				}
			} else {
				error = true;
			}
		}
		return error;
	}

	public void evaluar_table_politica(JTextField tx, int row, int col) {
		String valor = "";
		valor = tx.getText();
		if (!this.evaluar_table_politica(valor, row, col)) {
			if (frame.get_lst_desplazamiento().getSelectedIndex() == 1) {
				this.transfer_focus(row, col + 1);
			} else {
				this.transfer_focus(row + 1, col);
			}
			/*
			 * boolean error=this.guardar_row_monitor(row); if (error){
			 * this.transfer_focus(row, col); }
			 */
		} else {
			error("Error en politica");
			tx.requestFocusInWindow();
		}

	}

	public void evaluar_table_politica_actualizacion(JTextField tx, int row,
			int col) {
		String valor = "";
		valor = tx.getText();
		if (!this.evaluar_table_politica_actualizacion(valor, row, col)) {
			if (frame.get_lst_desplazamiento().getSelectedIndex() == 1) {
				this.transfer_focus(row, col + 1);
			} else {
				this.transfer_focus(row + 1, col);
			}
			/*
			 * boolean error=this.guardar_row_monitor(row); if (error){
			 * this.transfer_focus(row, col); }
			 */
		} else {
			error("Error en politica");
			tx.requestFocusInWindow();
		}

	}

	public void evaluar_table_lista(JTextField tx, int row, int col) {
		String valor = "";
		valor = tx.getText();
		valor = valor.replaceAll(",", "");
		double val = 0.0;
		try {
			val = new Double(valor);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (val > 0) {
			tx.setText("" + new Convertidor().roundDouble(val, 2));
			if (frame.get_lst_desplazamiento().getSelectedIndex() == 1) {
				this.transfer_focus(row, col + 1);
			} else {
				this.transfer_focus(row + 1, col);
			}
			/*
			 * boolean error=this.guardar_row_monitor(row); if (error){
			 * this.transfer_focus(row, col); }
			 */
		} else {
			error("El Precio de Lista Debe ser Mayor a Cero");
			tx.requestFocusInWindow();
		}
	}

	public void evaluar_table_costo(JTextField tx, int row, int col) {
		String valor = "";
		valor = tx.getText();
		double val = 0.0;
		try {
			val = new Double(valor);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (val > 0) {
			if (frame.get_lst_desplazamiento().getSelectedIndex() == 1) {
				this.transfer_focus(row, col + 1);
			} else {
				this.transfer_focus(row + 1, col);
			}
			/*
			 * boolean error=this.guardar_row_monitor(row); if (error){
			 * this.transfer_focus(row, col); }
			 */
		} else {
			error("El Precio de CostoDebe ser Mayor a Cero");
			tx.requestFocusInWindow();
		}
	}

	public void evaluar_table_publico(JTextField tx, int row, int col) {
		String valor = "";
		valor = tx.getText();
		valor = valor.replaceAll(",", "");
		double val = 0.0;
		try {
			val = new Double(valor);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (val > 0) {
			if (frame.get_lst_desplazamiento().getSelectedIndex() == 1) {
				this.transfer_focus(row + 1, 2);
			} else {
				this.transfer_focus(row + 1, col);
			}
			/*
			 * boolean error=this.guardar_row_monitor(row); if (error){
			 * this.transfer_focus(row, col); }
			 */
		} else {
			error("El Precio de Publico Debe ser Mayor a Cero");
			tx.requestFocusInWindow();
		}
	}

	public void editar_politica() {
		if (this.editor_politica != null) {
			this.editor_politica.dispose();
		}
		int row = frame.getJTable().getSelectedRow();

		this.editor_politica = new aplicacion.inventario.politica.constructor._Constructor();
		this.editor_politica.setParameter(_parametros.LookAndFeel, this
				.getConstructor().getLookAndFeelTheme());
		this.editor_politica.setParameter(_parametros.connector,
				getConstructor().getConnectionHandler());
		if (row >= 0) {
			String idpolitica = frame.getJTable().getValueAt(row, 6).toString();

			if (data.check_politica(idpolitica)) {
				this.editor_politica
						.setParameter(
								aplicacion.inventario.politica.interfaces._parametros.idpolitica,
								idpolitica);
			}
		}
		this.editor_politica.build(this.getConstructor());
		this.editor_politica.init();
	}

	public void copiar() {
		System.out.println("cOPIAR A MEMORIA");
		if (frame.getJTable() != null) {
			int[] rows = frame.getJTable().getSelectedRows();
			int[] columns = frame.getJTable().getSelectedColumns();
			memoria = new Object[rows.length][columns.length];
			for (int i = 0; i < rows.length; i++) {
				for (int j = 0; j < columns.length; j++) {
					memoria[i][j] = frame.getJTable().getValueAt(rows[i],
							columns[j]);
				}

			}
			try {

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void pegar() {
		if (frame.getJTable() != null) {
			int[] rows = frame.getJTable().getSelectedRows();
			int[] columns = frame.getJTable().getSelectedColumns();

			if (memoria != null) {
				if (memoria.length == rows.length
						& memoria[0].length == columns.length) {
					try {

						for (int i = 0; i < memoria.length; i++) {
							for (int j = 0; j < memoria[0].length; j++) {
								frame.getJTable().setValueAt(memoria[i][j],
										rows[i], columns[j]);
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		}
	}

	public boolean _completar_columna(int col) {
		boolean ok = false;
		ok = (col == 2) | (col == 3) | (col == 4) | (col == 6) | (col == 10)
				| (col == 11) | (col == 13) | (col == 14);
		return ok;
	}

	public void fillExtra(String idarticulo) {
		Object[][] results = data.getData(idarticulo);
		frame.get_txt_articulo().setText("");
		frame.get_txt_articulo_descripcion().setText("");
		frame.get_txt_articulo_stock().setText("");
		if (results != null) {
			if (results.length > 0) {
				String _articulo = (String) results[0][0];
				String _descripcion = (String) results[0][1];
				String _linea = (String) results[0][2];
				String _stock = (String) results[0][3];
				String _suspendidov = (String) results[0][4];
				String _actualizacion = (String) results[0][5];
				frame.get_txt_articulo().setText(_articulo);
				frame.get_txt_articulo_descripcion().setText(_descripcion);
				frame.get_txt_articulo_stock().setText(_stock);
				frame.get_txt_articulo_linea().setText(_linea);

				if (_suspendidov.compareTo("1") == 0) {
					frame.get_txt_articulo_bloqueado().setText("BLOQUEADO");
					frame.get_txt_articulo_bloqueado().setBackground(Color.red);
				} else {
					frame.get_txt_articulo_bloqueado().setText("HABILITADO");
					frame.get_txt_articulo_bloqueado().setBackground(
							Color.green);
				}

				if (_actualizacion.compareTo("") == 0) {
					frame.get_txt_articulo_actualizacion().setBackground(
							Color.red);
					_actualizacion = "SIN FECHA";
				} else {
					if (this.eval_venc(_actualizacion)) {
						frame.get_txt_articulo_actualizacion().setBackground(
								Color.green);
					} else {
						frame.get_txt_articulo_actualizacion().setBackground(
								Color.red);
					}
				}
				frame.get_txt_articulo_actualizacion().setText(_actualizacion);

			}
		}
	}

	private boolean eval_venc(String fecha) {
		boolean ok = false;
		if (fecha.compareTo("") != 0) {
			Calendar _fecha_actualizacion = Calendar.getInstance();
			Date date = null;
			DateFormat formatter;
			Locale locale = Locale.getDefault();
			try {
				formatter = new SimpleDateFormat("dd-MM-yyyy", locale);
				date = (Date) formatter.parse(fecha);

			} catch (Exception e) {

			}
			long diferencia = 1;
			if (date != null) {
				_fecha_actualizacion.setTime(date);
				Date today = new Date();
				java.util.GregorianCalendar _fecha_limite = new java.util.GregorianCalendar();
				_fecha_limite.setTime(today);
				// _today.roll(Calendar.DAY_OF_YEAR, days);
				_fecha_limite.add(Calendar.DAY_OF_YEAR, -30);

				Calendar now = Calendar.getInstance();
				_fecha_actualizacion.add(Calendar.DATE, 27);
				ok = _fecha_limite.before(_fecha_actualizacion);
				// System.out.println(now.getTime()+" "+cal.getTime());
			}
			// System.out.println("dif "+c+" "+diferencia);
		} else {
			// "Fecha Nula"
		}
		return ok;
	}

	public void completar(Object valor, int row, int col) {
		String idarticulo = frame.getJTable().getValueAt(row, 1).toString();
		if (col == 6 | col == 14) {
			String _valor = valor.toString();
			frame.getJTable().setValueAt(_valor, row, col);
			estado = "Completando Politica de " + idarticulo;
			this.completar_politica(_valor, row, col);
		} else {
			estado = "Completando Valores de " + idarticulo;
			if (col == 10 | col == 11) {
				frame.getJTable().setValueAt(valor, row, col);
			} else {
				String _valor = valor.toString();
				frame.getJTable().setValueAt(_valor, row, col);
			}
		}
	}

	public void _taskworkCompletar() {
		int[] columns = frame.getJTable().getSelectedColumns();
		int cx = 0;

		while (cx < columns.length & !canceled) {
			int col = columns[cx];
			if (this._completar_columna(col)) {
				if (frame.getJTable() != null) {
					Object val = frame.getJTable().getValueAt(
							frame.getJTable().getSelectedRows()[0], col);
					int[] indexes = frame.getJTable().getSelectedRows();
					this.lenght = indexes.length;
					int i = 0;
					while (i < indexes.length & !canceled) {
						this.current = i;
						try {
							this.completar(val, indexes[i], col);
						} catch (Exception e) {

						}
						i++;
					}

				}
			}
			cx++;
		}

		done = true;
	}

	// metodos basicos de tareas swing
	public void createTimer() {
		crono = new Crono();
		crono.start();
		lenght = 0;
		current = 0;
		errors = 0;
		done = false;
		canceled = false;
		Timer = new Timer(300, new ActionListener() {
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
		frame.get_btn_completar().setEnabled(true);
		frame.get_btn_cargar().setEnabled(true);
		frame.get_btn_guardar().setEnabled(true);
	}

	public void doCancel() {
		if (preguntar("confirmar", "Cancela Tarea?")) {
			this.canceled = true;
			frame.get_btn_cancelar().setEnabled(true);
			frame.get_btn_cargar().setEnabled(true);
			frame.get_btn_completar().setEnabled(true);
		}
	}

	class _taskCompletar {
		_taskCompletar() {
			_taskworkCompletar();
		}
	}

	class _taskCargar {
		_taskCargar() {
			_taskworkCargar();
		}
	}

	class _taskGuardar {
		_taskGuardar(String iduser) {
			_taskworkGuardar(iduser);
		}
	}

	class _taskSincronizar {
		_taskSincronizar() {
			_taskworkSincronizar();
		}
	}

	class _taskAutoconfigurar {
		_taskAutoconfigurar() {
			_taskworkAutoConfigurar();
		}
	}

	class _taskMarcar {
		_taskMarcar() {
			_taskworkMarcar();
		}
	}

	class _taskIncrementar {
		_taskIncrementar() {
			_taskworkIncrementar();
		}
	}

	public void goCompletar() {
		this.createTimer();
		SwingWorker worker = null;
		worker = new SwingWorker() {
			@Override
			public Object construct() {
				return new _taskCompletar();
			}
		};
		if (Timer != null) {
			Timer.start();
		}
		worker.start();
	}

	public void goIncrementar() {
		this.createTimer();
		SwingWorker worker = null;
		worker = new SwingWorker() {
			@Override
			public Object construct() {
				return new _taskIncrementar();
			}
		};
		if (Timer != null) {
			Timer.start();
		}
		worker.start();
	}

	public void goCargar() {
		this.createTimer();
		SwingWorker worker = null;
		worker = new SwingWorker() {
			@Override
			public Object construct() {
				return new _taskCargar();
			}
		};
		if (Timer != null) {
			Timer.start();
		}
		worker.start();
	}

	public void goGuardar() {
		String iduser = this.validar_usuario();
		if (iduser.compareTo("") != 0) {
			this.createTimer();
			final String _iduser = iduser;
			SwingWorker worker = null;
			worker = new SwingWorker() {
				@Override
				public Object construct() {
					return new _taskGuardar(_iduser);
				}
			};
			if (Timer != null) {
				Timer.start();
			}
			worker.start();

		} else {
			error("OPERACION CANCELADA");
		}
	}

	public void goSincronizar() {
		this.createTimer();
		SwingWorker worker = null;
		worker = new SwingWorker() {
			@Override
			public Object construct() {
				return new _taskSincronizar();
			}
		};
		if (Timer != null) {
			Timer.start();
		}
		worker.start();
	}

	public void goAutoConfigurar() {
		this.createTimer();
		SwingWorker worker = null;
		worker = new SwingWorker() {
			@Override
			public Object construct() {
				return new _taskAutoconfigurar();
			}
		};
		if (Timer != null) {
			Timer.start();
		}
		worker.start();
	}

	public void goMarcar() {
		this.createTimer();
		SwingWorker worker = null;
		worker = new SwingWorker() {
			@Override
			public Object construct() {
				return new _taskMarcar();
			}
		};
		if (Timer != null) {
			Timer.start();
		}
		worker.start();
	}

	public int getSelected() {
		int count = 0;
		int row = 0;
		while (row < frame.getJTable().getRowCount() & !canceled) {
			boolean selected = false;
			try {
				selected = (Boolean) frame.getJTable().getValueAt(row, 0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
			if (selected)
				count++;
			row++;
		}
		return count;
	}

	public void _taskworkGuardar(String iduser) {
		this.lenght = this.getSelected();
		String idoperacion = "" + data.getProximoOperacion();

		actualiza = frame.get_chk_fecha_actualizacion().isSelected();
		data.beginTransaction();
		data.clearBatch();

		String q = data.getOperacion(idoperacion, iduser,
				"MODIFICACION ARTICULOS PLANILLA");
		data.addBatch(q);
		q = data.getUpdateOperacion();
		data.addBatch(q);
		boolean _error = data.executeBatch();
		if (!_error) {
			data.commitTransaction();
		} else {
			data.rollbackTransaction();
		}
		if (this.lenght > 0) {

			int i = 0;
			while (i < frame.getJTable().getRowCount() & !canceled) {
				boolean selected = false;
				try {
					selected = (Boolean) frame.getJTable().getValueAt(i, 0);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					// /e.printStackTrace();
				}
				if (selected) {

					current++;
					boolean error = guardar_row(i, iduser, idoperacion);
					if (error)
						errors++;
				}
				i++;
			}
		} else {
			error("Seleccione los articulos que desea guardar");
		}
		if (canceled) {
			aviso("Se Cancelo la Operacion Guardar con " + current
					+ " operaciones y " + errors + " errores");
		} else {
			aviso("Se Completo la Operacion Guardar con " + current
					+ " operaciones y " + errors + " errores");
		}
		done = true;
	}

	public void seleccionar(boolean b) {
		JTable table = frame.getJTable();
		int[] rows = table.getSelectedRows();
		if (rows.length > 0) {
			for (int row = 0; row < rows.length; row++) {
				table.setValueAt(b, rows[row], 0);
			}
		} else {
			for (int row = 0; row < table.getRowCount(); row++) {
				table.setValueAt(b, row, 0);
			}
		}
	}

	public void cargar_prefijos(String linea) {
		Object[][] results = data.getLinePrefix(linea);
		if (results != null) {
			if (results.length > 0) {
				String prefix = (String) results[0][0];
				frame.get_txt_idarticulo_desde().setText(prefix + "-000");
				frame.get_txt_idarticulo_hasta().setText(prefix + "-zzz");
				frame.get_txt_idarticulo_hasta().requestFocusInWindow();
			}
		}
	}

	private aplicacion.herramientas.java.visualizadores.Linea vLinea = null;

	public void buscarLinea(JTextField tx) {
		if (vLinea != null) {
			vLinea.close();
		}
		vLinea = new aplicacion.herramientas.java.visualizadores.Linea(this
				.getConstructor());
		vLinea.setIdproveedor(frame.get_txt_idproveedor().getText());
		int n = vLinea.buscarLinea(tx);
		if (n == 0) {
			aviso("no hay Lineas con ese codigo");
		}
	}

	public void evaluar_linea(JTextField tx) {
		String linea = tx.getText();
		if (linea.compareTo("") != 0) {
			if (data.check_linea(linea)) {
				cargar_prefijos(linea);
			} else {
				this.buscarLinea(tx);
			}
		} else {
			this.buscarLinea(tx);
		}
	}

	public String getInstruccionRegistro(String iduser, String ip,
			String validacion, int row, String idoperacion) {
		String idarticulo = frame.getJTable().getValueAt(row, 1).toString();
		String descripcion = frame.getJTable().getValueAt(row, 2).toString();
		String precio = frame.getJTable().getValueAt(row, 1).toString();
		precio = precio.replaceAll(",", "");
		String descripcion_old = "";
		String precio_old = "";
		String costo_old = "";
		String precio5_old = "";
		String politicaprecios_old = "";
		String suspendidov_old = "";
		String suspendidoc_old = "";
		String minimo_old = "";
		String minimo = "";
		String costo = "";
		String precio5 = "";
		String politicaprecios = "";
		String suspendidov = "0";
		String suspendidoc = "0";
		String clasificacion = "";
		String clasificacion_old = "";
		Object[][] results = data.getCurrentValues(idarticulo);
		String instruccion = "";
		if (results != null) {
			if (results.length > 0) {

				try {
					descripcion_old = (String) results[0][0];
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					precio_old = (String) results[0][1];
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					costo_old = (String) results[0][2];
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					precio5_old = (String) results[0][3];
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					politicaprecios_old = (String) results[0][4];
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					suspendidov_old = (String) results[0][5];
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					suspendidoc_old = (String) results[0][6];
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					minimo_old = (String) results[0][7];
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					clasificacion_old = (String) results[0][8];
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					minimo = (String) results[0][7];
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					costo = frame.getJTable().getValueAt(row, 7).toString();
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					precio5 = frame.getJTable().getValueAt(row, 5).toString();
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					precio = frame.getJTable().getValueAt(row, 8).toString();
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					politicaprecios = frame.getJTable().getValueAt(row, 6)
							.toString();
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					clasificacion_old = frame.getJTable().getValueAt(row, 14)
							.toString();
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					precio_old = precio_old.replaceAll(",", "");
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				suspendidov = "0";
				try {
					if ((Boolean) frame.getJTable().getValueAt(row, 10)) {
						suspendidov = "1";
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				try {
					if ((Boolean) frame.getJTable().getValueAt(row, 11)) {
						suspendidoc = "1";
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				minimo = minimo.replaceAll(",", "");
				if (minimo.compareTo("") == 0) {
					minimo = "0.0";
				}
				precio5 = precio5.replaceAll(",", "");
				precio = precio.replaceAll(",", "");
				costo = costo.replaceAll(",", "");
				if (costo.compareTo("") == 0) {
					costo = "0.0";
				}
				if (precio5.compareTo("") == 0) {
					precio5 = "0.0";
				}
				if (precio.compareTo("") == 0) {
					precio = "0.0";
				}
				if (costo_old.compareTo("") == 0) {
					costo_old = "0.0";
				}
				if (precio5_old.compareTo("") == 0) {
					precio5_old = "0.0";
				}
				if (precio_old.compareTo("") == 0) {
					precio_old = "0.0";
				}
				if (suspendidoc.compareTo("") == 0) {
					suspendidoc = "0";
				}
				if (suspendidov.compareTo("") == 0) {
					suspendidov = "0";
				}
				if (suspendidoc_old.compareTo("") == 0) {
					suspendidoc_old = "0.0";
				}
				if (suspendidov_old.compareTo("") == 0) {
					suspendidov_old = "0.0";
				}
				instruccion = data.getRegistrarCambio(iduser, ip, idarticulo,
						descripcion, descripcion_old, precio, precio_old,
						costo, costo_old, precio5, precio5_old,
						politicaprecios, politicaprecios_old, minimo,
						minimo_old, suspendidov, suspendidov_old, suspendidoc,
						suspendidoc_old, validacion, clasificacion,
						clasificacion_old, idoperacion);
			}
		}
		return instruccion;
	}

	public void evaluar_proveedor(JTextField tx) {
		String idproveedor = tx.getText();
		if (idproveedor.startsWith("21101")
				& idproveedor.compareTo("21101") != 0) {
			Object[][] results = data.getProveedor(idproveedor);
			if (results != null) {
				if (results.length > 0) {
					String desc = results[0][1].toString();
					String pol = results[0][2].toString();
					String poldesc = results[0][3].toString();
					String linea = results[0][6].toString();
					frame.get_txt_descripcion_proveedor().setText(desc);
					frame.get_txt_linea().requestFocusInWindow();
				} else {
					aviso("No se encontro Proveedor " + idproveedor);
				}
			} else {
				aviso("No se encontro Proveedor " + idproveedor);
			}
		} else {
			if (idproveedor.compareTo("") == 0) {
				idproveedor = "21101";
				tx.setText(idproveedor);
			} else {
				this.buscarProveedor(tx);
			}

		}
	}

	public boolean guardar_row_monitor(int row, String iduser,
			String idoperacion) {
		boolean error = this.guardar_row(row, iduser, idoperacion);
		if (error) {
			error("Error Guardando Fila " + row);
		} else {
			System.out.println("se grabo ok fila " + row);
		}
		return error;
	}

	public boolean guardar_row(int row, String validacion, String idoperacion) {
		JTable table = frame.getJTable();
		String idarticulo = "";
		String descripcion = "";
		String linea = "";
		String idproveedor = "";
		String politica = "";
		String lista = "0.0";
		String costo = "0.0";
		String publico = "0.0";
		String fecha_actualizacion = "";
		boolean suspendidoc = false;
		boolean suspendidov = false;
		try {
			idarticulo = table.getValueAt(row, 1).toString();
		} catch (Exception e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
		descripcion = table.getValueAt(row, 2).toString();
		try {
			linea = table.getValueAt(row, 3).toString();
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		try {
			idproveedor = table.getValueAt(row, 4).toString();
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		try {
			lista = table.getValueAt(row, 5).toString();
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		try {
			politica = table.getValueAt(row, 6).toString();
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		try {
			costo = table.getValueAt(row, 7).toString();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			publico = table.getValueAt(row, 8).toString();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			fecha_actualizacion = table.getValueAt(row, 9).toString();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		suspendidoc = false;
		try {
			suspendidoc = (Boolean) table.getValueAt(row, 10);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		suspendidov = false;
		try {
			suspendidov = (Boolean) table.getValueAt(row, 11);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String cuenta_actualizacion = "";
		try {
			cuenta_actualizacion = table.getValueAt(row, 13).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String clasificacion = "";
		try {
			clasificacion = table.getValueAt(row, 14).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String _suspendidoc = "0";
		String _suspendidov = "0";
		if (suspendidoc) {
			_suspendidoc = "1";
		}
		if (suspendidov) {
			_suspendidov = "1";
		}
		lista = new Convertidor().getMoney(lista, 2);
		double _costo = 0.0;
		double _precio5 = 0.0;
		double _publico = 0.0;
		_costo = new Double(costo.replaceAll(",", ""));
		_precio5 = new Double(lista.replaceAll(",", ""));
		_publico = new Double(publico.replaceAll(",", ""));
		estado = "Guardando " + idarticulo;
		data.beginTransaction();
		data.clearBatch();

		String ip = data.getIp();
		Object[][] old = data.getArticulo(idarticulo);

		String old_precio5 = "";
		String old_costo = "";
		String old_publico = "";
		double _oprecio5 = 0.0;
		double _ocosto = 0.0;
		double _opublico = 0.0;
		if (old != null) {
			if (old.length > 0) {
				old_precio5 = (String) old[0][5];
				old_costo = (String) old[0][6];
				old_publico = (String) old[0][7];
			}

		}

		try {
			_oprecio5 = new Double(old_precio5);
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			_ocosto = new Double(old_costo);
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
		try {
			_opublico = new Double(old_publico);
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}

		String q = "";
		boolean existe = data.existe(idarticulo);

		if (!existe) {
			String minimo = "0.0";
			q = data.getInsert(idarticulo, descripcion, linea, idproveedor,
					politica, lista, costo, publico, minimo, suspendidov,
					suspendidoc, cuenta_actualizacion, clasificacion, q);
		} else {
			q = data.getUpdate(idarticulo, descripcion, linea, idproveedor,
					politica, "" + _precio5, "" + _costo, "" + _publico,
					_suspendidoc, _suspendidov, cuenta_actualizacion,clasificacion, iidaplicacions);
			
		}
		System.out.println(q);
		data.addBatch(q);
		if (actualiza){
			q=data.getUpdate(idarticulo, cuenta_actualizacion);
			System.out.println(q);
			data.addBatch(q);	
		}
		q = this.getInstruccionRegistro(iidaplicacions, ip, validacion, row,
				idoperacion);
		data.addBatch(q);
		boolean error = data.executeBatch();
		if (!error) {
			data.commitTransaction();
		} else {
			data.rollbackTransaction();
		}
		return error;
	}

	private aplicacion.herramientas.java.reemplazar.constructor._Constructor reemplazar = null;
	public void BuscarReemplazar() {
		JTable table = frame.getJTable();
		int col = table.getSelectedColumn();
		int row = table.getSelectedRow();
		if (reemplazar == null) {
			if (col == 2 | col == 3) {

				reemplazar = new aplicacion.herramientas.java.reemplazar.constructor._Constructor();
				reemplazar.setParameter(_parametros.LookAndFeel, this
						.getConstructor().getLookAndFeelTheme());
				reemplazar.setParameter(_parametros.connector, this
						.getConstructor().getConnectionHandler());
				reemplazar.build(this.getConstructor());
				reemplazar.init();
				aplicacion.herramientas.java.reemplazar.logic._Logic logic = (aplicacion.herramientas.java.reemplazar.logic._Logic) reemplazar
						.getLogic();
				logic.centrar();
			}

		}
		if (col == 2 | col == 3) {
			aplicacion.herramientas.java.reemplazar.logic._Logic logic = (aplicacion.herramientas.java.reemplazar.logic._Logic) reemplazar
					.getLogic();

			logic.setColumn(col);
			logic.setProximo(row);
			logic.setTable(table);
			logic.show();
		}

	}

	public void _taskworkSincronizar() {
		lenght = this.getSelected();

		if (lenght > 0) {
			int row = 0;

			while (row < frame.getJTable().getRowCount() & !canceled) {
				boolean selected = (Boolean) frame.getJTable().getValueAt(row,
						0);
				if (selected) {
					current++;
					boolean error = sincronizar_row(row);
					if (error)
						errors++;

				}
				row++;
			}

		} else {
			aviso("Debe seleccionar los articulos a los cuales desea sincronizar el precio");
		}
		if (canceled) {
			error("Se Cancelo la Operacion Sincronizar Precios con " + current
					+ " operaciones y " + errors + " errores");
		} else {
			aviso("Se Completo la Operacion Sincronizar Precios con " + current
					+ " operaciones y " + errors + " errores");
		}

		done = true;
	}

	public void _taskworkAutoConfigurar() {
		lenght = this.getSelected();

		if (lenght > 0) {
			int row = 0;

			while (row < frame.getJTable().getRowCount() & !canceled) {
				boolean selected = (Boolean) frame.getJTable().getValueAt(row,
						0);
				if (selected) {
					current++;
					boolean error = autoconfigurar_row(row);
					if (error)
						errors++;

				}
				row++;
			}

		} else {
			aviso("Debe seleccionar los articulos a los cuales desea autoconfigurar el proveedor de actualizacion");
		}
		if (canceled) {
			error("Se Cancelo la Operacion AutoConfigurar Proveedor de Actualizacion con "
					+ current + " operaciones y " + errors + " errores");
		} else {
			aviso("Se Completo la Operacion AutoConfigurar Proveedor de Actualizacion con "
					+ current + " operaciones y " + errors + " errores");
		}

		done = true;
	}

	public void paint() {
		frame.getJTable().getModel();
	}

	public boolean sincronizar_row(int row) {
		boolean error = false;
		JTable table = frame.getJTable();
		String idarticulo = "";
		String descripcion = "";
		String linea = "";
		String idproveedor = "";
		String politica = "";
		String lista = "";
		String costo = "";
		String publico = "";
		idarticulo = table.getValueAt(row, 1).toString();
		descripcion = table.getValueAt(row, 2).toString();
		linea = table.getValueAt(row, 3).toString();
		idproveedor = table.getValueAt(row, 13).toString();
		lista = table.getValueAt(row, 5).toString();
		politica = table.getValueAt(row, 6).toString();
		costo = table.getValueAt(row, 7).toString();
		publico = table.getValueAt(row, 8).toString();
		lista = new Convertidor().getMoney(lista, 2);
		costo = new Convertidor().getMoney(costo, 2);
		publico = new Convertidor().getMoney(publico, 2);
		estado = "Sincronizando " + idarticulo;
		if (data.check_proveedor(idproveedor)) {
			Object[][] results = data.getPrecioProveedor(idarticulo,
					idproveedor);
			if (results != null) {
				if (results.length > 0) {
					lista = (String) results[0][0];
					politica = (String) results[0][1];
					double list = 0.0;
					try {
						list = new Double(lista);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					frame.getJTable().setValueAt(
							new Convertidor().roundDouble(list, 2), row, 5);

					if (list > 0) {
						if (frame.get_chk_sincronizar_con_politica()
								.isSelected()) {
							frame.getJTable().setValueAt(politica, row, 6);
							error = this.evaluar_table_politica(politica, row,
									6);
						} else {

						}
					}

				} else {
					if (frame.get_chk_mostrar_errores().isSelected()) {
						error("No se Encontro Precio para el articulo "
								+ idarticulo + " en los alias en la fila "
								+ row + ".Revise Alias");
					}
					error = true;
				}
			} else {
				if (frame.get_chk_mostrar_errores().isSelected()) {
					error("No se Encontro Precio para el articulo "
							+ idarticulo + " en los alias en la fila " + row
							+ ".Revise Alias");
				}
				error = true;
			}
		} else {
			if (frame.get_chk_mostrar_errores().isSelected()) {
				error("Codigo de Proveedor Incorrecto. Revise el Codigo de Proveedor "
						+ idproveedor + "en la fila " + row);
			}
			error = true;
		}
		return error;
	}

	public boolean autoconfigurar_row(int row) {
		boolean error = false;
		JTable table = frame.getJTable();
		String idarticulo = "";
		String descripcion = "";
		String linea = "";
		String idproveedor = "";
		String politica = "";
		String lista = "";
		String costo = "";
		String publico = "";
		idarticulo = table.getValueAt(row, 1).toString();
		descripcion = table.getValueAt(row, 2).toString();
		linea = table.getValueAt(row, 3).toString();
		idproveedor = table.getValueAt(row, 4).toString();
		lista = table.getValueAt(row, 5).toString();
		politica = table.getValueAt(row, 6).toString();
		costo = table.getValueAt(row, 7).toString();
		publico = table.getValueAt(row, 8).toString();
		lista = new Convertidor().getMoney(lista, 2);
		costo = new Convertidor().getMoney(costo, 2);
		publico = new Convertidor().getMoney(publico, 2);
		estado = "AutoConfigurar " + idarticulo;
		/*
		 * if (data.check_proveedor(idproveedor)){
		 * 
		 * }else { if (frame.get_chk_mostrar_errores().isSelected()){
		 * error("Codigo de Proveedor Incorrecto. Revise el Codigo de Proveedor "
		 * +idproveedor+"en la fila "+row); } error=true; }
		 */
		System.out.println("predeterminado " + idproveedor);
		Object[][] results = data.getPrecioProveedor(idarticulo);
		if (results != null) {
			if (results.length > 0) {
				lista = (String) results[0][0];
				politica = (String) results[0][1];

				boolean found = false;
				int u = 0;
				while (u < results.length & !found) {
					String _proveedor = (String) results[u][2];
					found = idproveedor.compareTo(_proveedor) == 0;
					if (!found) {
						u++;
					}
				}
				if (found) {
					lista = (String) results[u][0];
					politica = (String) results[u][1];
					idproveedor = (String) results[u][2];
				} else {
					idproveedor = (String) results[0][2];
				}

				double list = 0.0;
				try {
					list = new Double(lista);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frame.getJTable().setValueAt(
						new Convertidor().roundDouble(list, 2), row, 5);
				frame.getJTable().setValueAt(idproveedor, row, 13);
				if (list > 0) {
					if (frame.get_chk_sincronizar_con_politica().isSelected()) {
						frame.getJTable().setValueAt(politica, row, 6);
						error = this.evaluar_table_politica(politica, row, 6);
					} else {

					}
				}

			} else {
				if (frame.get_chk_mostrar_errores().isSelected()) {
					error("No se Encontro Precio para el articulo "
							+ idarticulo + " en los alias en la fila " + row
							+ ".Revise Alias");
				}
				error = true;
			}
		} else {
			frame.getJTable().setValueAt("", row, 13);
			if (frame.get_chk_mostrar_errores().isSelected()) {
				error("No se Encontro Precio para el articulo " + idarticulo
						+ " en los alias en la fila " + row + ".Revise Alias");
			}
			error = true;
		}

		return error;
	}

	public void importar() {
		if (importar != null) {
			importar.dispose();
		}
		if (frame.getJTable() == null) {

			Object[][] results = new Object[][] { { false, "", "", "", "", "",
					"", "", "", "", "", "", "", "", "" } };
			this.create_table(results);
		} else {
			if (frame.getJTable().getRowCount() <= 0) {
				Object[][] results = new Object[][] { { false, "", "", "", "",
						"", "", "", "", "", "", "", "", "", "" } };
				this.create_table(results);
			}
		}
		importar = new aplicacion.herramientas.java.importar.constructor._Constructor();
		importar.setParameter(_parametros.connector, this.getConstructor()
				.getConnectionHandler());
		importar
				.setParameter(
						aplicacion.herramientas.java.importar.interfaces._parametros._tabla,
						frame.getJTable());
		
		importar.build(this.getConstructor());
		importar.init();
		aplicacion.herramientas.java.importar.logic._Logic _logic = (aplicacion.herramientas.java.importar.logic._Logic) importar
				.getLogic();
		_logic.setCustom(true);
		columna col = null;

		col = new columna();
		col.setNombre("idarticulo");
		col.setColumn(1);
		col.setMaster(true);
		_logic.addColumn(col);

		col = new columna();
		col.setNombre("descripcion");
		col.setColumn(2);
		col.setMaster(false);
		_logic.addColumn(col);

		col = new columna();
		col.setNombre("linea");
		col.setColumn(3);
		col.setMaster(false);
		_logic.addColumn(col);

		col = new columna();
		col.setNombre("proveedor");
		col.setColumn(4);
		col.setMaster(false);
		_logic.addColumn(col);

		col = new columna();
		col.setNombre("lista");
		col.setColumn(5);
		col.setMaster(false);
		_logic.addColumn(col);

		col = new columna();
		col.setNombre("politica");
		col.setColumn(6);
		col.setMaster(false);
		_logic.addColumn(col);

		col = new columna();
		col.setNombre("clasificacion");
		col.setColumn(14);
		col.setMaster(false);
		_logic.addColumn(col);

	}

	public void exportar() {
		if (this.getSelected() > 0) {
			_exportar();
		} else {
			error("Debe Seleccionar que Filas Desea Exportar");
		}
	}

	public void _exportar() {
		if (exportar != null) {
			exportar.dispose();
		}
		exportar = new aplicacion.herramientas.java.exportar.constructor._Constructor();
		exportar.setParameter(_parametros.connector, this.getConstructor()
				.getConnectionHandler());
		exportar.setParameter(_parametros.LookAndFeel, this.getConstructor()
				.getLookAndFeelTheme());
		exportar
				.setParameter(
						aplicacion.herramientas.java.exportar.interfaces._parametros._tabla,
						frame.getJTable());
		exportar.setShowOnStartup(true);
		exportar.build(this.getConstructor());

		exportar.init();

		aplicacion.herramientas.java.exportar.logic._Logic _logic = (aplicacion.herramientas.java.exportar.logic._Logic) exportar
				.getLogic();

		columna col = null;

		col = new columna();
		col.setNombre("idarticulo");
		col.setColumn(1);
		col.setMaster(true);
		_logic.addColumn(col);

		col = new columna();
		col.setNombre("descripcion");
		col.setColumn(2);
		col.setMaster(false);
		_logic.addColumn(col);

		col = new columna();
		col.setNombre("linea");
		col.setColumn(3);
		col.setMaster(false);
		_logic.addColumn(col);

		col = new columna();
		col.setNombre("proveedor");
		col.setColumn(4);
		col.setMaster(false);
		_logic.addColumn(col);

		col = new columna();
		col.setNombre("lista");
		col.setColumn(5);
		col.setMaster(false);
		_logic.addColumn(col);

		col = new columna();
		col.setNombre("politica");
		col.setColumn(6);
		col.setMaster(false);
		_logic.addColumn(col);

		col = new columna();
		col.setNombre("clasificacion");
		col.setColumn(14);
		col.setMaster(false);
		_logic.addColumn(col);

		_logic.crear_tabla();

	}

	public void _taskworkIncrementar() {
		int selected = this.getSelected();
		this.lenght = selected;
		current = 0;
		if (selected > 0) {
			String incremento = "";
			double inc = -1;
			boolean canceled = false;
			while (inc < 0 & !canceled) {

				incremento = this
						.ingresarSwing("Ingrese el Incremento(%) que desea realizar sobre el precio de lista");
				inc = new Double(incremento);
				if (inc < 0) {
					if (preguntar("Confirmar", "Cancela Incremento de Precio?")) {
						canceled = true;
					}
				}
			}
			if (selected > 0 & inc > 0) {

				for (int row = 0; row < frame.getJTable().getRowCount(); row++) {
					boolean b = (Boolean) frame.getJTable().getValueAt(row, 0);
					if (b) {
						current++;

						String lista = frame.getJTable().getValueAt(row, 5)
								.toString();
						String idarticulo = frame.getJTable()
								.getValueAt(row, 1).toString();
						estado = "Incrementando " + idarticulo;
						double lst = new Double(lista);
						lst = lst * (100 + inc) / 100;
						frame.getJTable().setValueAt(
								new Convertidor().roundDouble(lst, 2), row, 5);
						String politica = frame.getJTable().getValueAt(row, 6)
								.toString();
						if (data.check_politica(politica)) {
							this.completar_politica(politica, row, 6);
						}
					}
				}
			}
			aviso("Incremento de Precios Finalizado");
		} else {
			aviso("Debe seleccionar los articulos que desea incrementar el precio de lista");
		}

		done = true;
	}

}
