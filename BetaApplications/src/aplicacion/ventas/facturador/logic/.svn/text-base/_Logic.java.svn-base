package aplicacion.ventas.facturador.logic;

import java.awt.Color;
import java.awt.Font;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.List;
import java.util.ArrayList;
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
import aplicacion.cliente.cobranza.logic.extensions._Asiento;
import aplicacion.herramientas.java.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import aplicacion.ventas.facturador.gui._Frame;
import aplicacion.ventas.facturador.interfaces._Interface;
import aplicacion.ventas.pedido.logic.TableItemColorCellRenderer;
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
	private Timer Timer; // @jve:decl-index=0:
	private Crono crono;
	public String equivalencia = "";
	private String estado = "";
	private int current;
	private int lenght;
	private String validacion = "";
	private boolean done, canceled;
	private int errors = 0;
	private boolean nuevo = false;
	private boolean cambios = false;

	public boolean getShowCost() {
		boolean b = false;// frame.get_chk_costo().isSelected();

		return b;
	}

	public void setFrame(JFrame _frame) {
		this.frame = (_Frame) _frame;
		super.setFrame(_frame);
	}

	public void setData(Data _data) {
		this.data = (_Data) _data;
		super.setData(_data);
	}

	public void init() {
		this.cargarTipoComprobante();
		this.cargarLetras();
		this.cargarSucursal();
	}

	public void cargarSucursal() {
		frame.get_txt_sucursal().setText("0001");
	}

	public void cargarLetras() {
		frame.get_lst_letra().removeItemListener(
				this.getConstructor().getItemListener());
		frame.get_lst_letra().removeAllItems();
		frame.get_lst_letra().addItem("X");
		frame.get_lst_letra().addItemListener(
				this.getConstructor().getItemListener());
	}

	public void cargarTipoComprobante() {
		frame.get_lst_tipo_comprobante().removeItemListener(
				this.getConstructor().getItemListener());
		frame.get_lst_tipo_comprobante().removeAllItems();
		frame.get_lst_tipo_comprobante().addItem("FVN");
		// frame.get_lst_tipo_comprobante().addItem("NCN");
		frame.get_lst_tipo_comprobante().addItemListener(this.getConstructor().getItemListener());
	}

	public void clean() {
		nuevo = false;
		cambios = false;
		frame.setJTable(null);
		frame.get_txt_idcliente().setEditable(true);
		frame.get_btn_buscar_cliente().setEnabled(true);
		frame.get_txt_remito().setText("");
		frame.get_txt_condicion_detalle().setText("");
		frame.get_txt_idcondicion().setText("");
		frame.get_txt_descripcion_cliente().setText("");
		frame.get_txt_idpedido().setText("");
		frame.get_txt_fecha().setText("");
		frame.get_btn_pdc().setEnabled(false);
		frame.get_txt_idcliente().setText("");
		frame.get_txt_numero().setText("");
		frame.get_txt_numero().setEditable(true);
		frame.get_btn_imprimir().setEnabled(false);
		frame.get_txt_subtotal().setText("");
		frame.get_txt_total().setText("");
		frame.get_txt_idvendedor().setText("");
		frame.get_txt_vendedor_detalle().setText("");
		frame.get_txt_idcliente().setEnabled(false);
		frame.get_txt_idvendedor().setEnabled(false);
		frame.get_txt_idcondicion().setEnabled(false);
		frame.get_txt_remito().setEnabled(false);
		frame.get_btn_buscar_fecha().setEnabled(true);
		frame.get_txt_numero().setEnabled(true);
		frame.get_txt_fecha().setEnabled(true);
		frame.get_btn_eliminar().setEnabled(false);
		frame.get_btn_buscar_cliente().setEnabled(false);
		frame.get_btn_cargar_cliente().setEnabled(false);
		frame.get_btn_buscar_remito().setEnabled(false);
		frame.get_btn_cargar_remito().setEnabled(false);
		frame.get_btn_cargar_remito().setEnabled(false);
		frame.get_btn_eliminar_remito().setEnabled(false);
		frame.get_lst_tipo_comprobante().setEnabled(true);
		frame.get_btn_grabar().setEnabled(false);
		frame.get_txt_articulo().setText("");
		frame.get_txt_articulo_actualizacion().setText("");
		frame.get_txt_articulo_bloqueado().setText("");
		frame.get_txt_articulo_linea().setText("");
		frame.get_txt_articulo_stock().setText("");
		frame.get_txt_articulo_descripcion().setText("");

		this.getToday();
		this.focus();

	}

	private aplicacion.herramientas.java.evaluadores.Condicion Condicion = null;
	public void initialize_Condicion() {
		Condicion = new aplicacion.herramientas.java.evaluadores.Condicion() {
			public void cargar(String codigo) {
				Object[][] results = this.getInfo(codigo);
				boolean ok = false;
				if (results != null) {
					if (results.length > 0) {
						String descripcion = (String) results[0][1];
						String cod = (String) results[0][0];
						frame.get_txt_idcondicion().setText(cod);
						frame.get_txt_condicion_detalle().setText(descripcion);
						ok = true;
					}
				}
				if (!ok) {
					error("Ingrese la Condicion de Venta");
				}
				guardar();
			}

			public void completar(String codigo) {
				Object[][] results = this.getInfo(codigo);
				boolean ok = false;
				if (results != null) {
					if (results.length > 0) {
						String descripcion = (String) results[0][1];
						String cod = (String) results[0][0];
						frame.get_txt_idcondicion().setText(cod);
						frame.get_txt_condicion_detalle().setText(descripcion);

						ok = true;
					}
				}
				if (!ok) {
					error("Ingrese la Condicion de Venta");
				}

			}
		};
		Condicion.setConstructor(this.getConstructor());
	}

	public void BuscarCondicion(JTextField tx) {
		Condicion.Buscar(tx);
	}

	public void BuscarCondicion() {
		Condicion.Buscar(frame.get_txt_idcondicion());
	}

	public void buscarCondicion(JTextField tx) {
		Condicion.buscar(tx);
	}

	public void evaluarCondicion(JTextField tx) {
		Condicion.evaluate(tx);
	}

	private aplicacion.herramientas.java.evaluadores.Vendedor Vendedor = null;

	public void initialize_Vendedor() {
		Vendedor = new aplicacion.herramientas.java.evaluadores.Vendedor() {
			public void cargar(String codigo) {
				Object[][] results = this.getInfo(codigo);
				String descripcion = (String) results[0][1];
				String cod = (String) results[0][0];
				frame.get_txt_idvendedor().setText(cod);
				frame.get_txt_vendedor_detalle().setText(descripcion);
				frame.get_txt_idcondicion().requestFocusInWindow();

			}
		};
		Vendedor.setConstructor(this.getConstructor());
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
		Vendedor.evaluate(tx);
	}

	private aplicacion.herramientas.java.buscadores.Fecha bFecha = null;

	public void BuscarFecha(JTextField tx) {
		if (bFecha == null) {
			bFecha = new aplicacion.herramientas.java.buscadores.Fecha(this
					.getConstructor());
		}

		bFecha.Buscar(tx);
	}

	public void BuscarFecha() {
		this.BuscarFecha(frame.get_txt_fecha());
	}

	public void getToday() {
		_Frame _frame = (_Frame) this._frame;
		_frame.get_txt_fecha().setText(
				new Convertidor().getDateWithFormat("dd-MM-yyyy"));
	}

	public void focus() {
		frame.get_lst_tipo_comprobante().requestFocusInWindow();
	}

	public _Data getData() {
		return this.data;
	}

	public void obtener_proximo_cpte(String tc) {
		String cb = data.getProximoPGCorrecto(tc);
		frame.get_txt_numero().setText(cb);

	}

	public void evaluate_comprobante(JComboBox lst) {
		String tipo = lst.getSelectedItem().toString();
		this.obtener_proximo_cpte(tipo);
		String pedido = data.getProximoPGCorrecto(tipo);
		
		 int inc=0;
		while (data.existe(tipo, pedido) & inc<3){
			data.getUpdateTC(tipo);
			pedido = data.getProximoPGCorrecto(tipo);
			inc++;
		}
		if (inc<3){
			frame.get_txt_numero().requestFocusInWindow();
		  }
		 
		
	}

	private aplicacion.herramientas.java.evaluadores.FVN FVN = null;

	public void initialize_FVN() {
		FVN = new aplicacion.herramientas.java.evaluadores.FVN() {
			public void cargar(String codigo) {
				cargar_fvn(codigo);
			}
		};
		FVN.setConstructor(this.getConstructor());
	}

	public void BuscarFVN(JTextField tx) {
		FVN.Buscar(tx);
	}

	public void BuscarFVN() {
		FVN.Buscar(frame.get_txt_numero());
	}

	public void buscarFVN(JTextField tx) {
		FVN.buscar(tx);
	}

	public void evaluarFVN(JTextField tx) {
		String tc = frame.get_lst_tipo_comprobante().getSelectedItem()
				.toString();
		FVN.setTC(tc);
		FVN.evaluate(tx);
	}

	public void evaluar_numero(JTextField tx) {

		String numero = tx.getText();
		String tc = frame.get_lst_tipo_comprobante().getSelectedItem()
				.toString();
		if (data.existe(tc, numero)) {
			this.evaluarFVN(tx);
			nuevo = false;
		} else {

			String correcto = data.getProximoPGCorrecto(tc);
			if (correcto.compareTo(numero) == 0) {
				nuevo = true;
				System.out.println("Nuevo " + tc + " " + numero);
				tx.setEditable(false);
				frame.get_txt_idcliente().setEnabled(true);
				frame.get_txt_idcondicion().setEnabled(true);
				frame.get_txt_idvendedor().setEnabled(true);
				frame.get_txt_remito().setEnabled(true);
				frame.get_btn_buscar_cliente().setEnabled(true);
				frame.get_btn_cargar_cliente().setEnabled(true);
				frame.get_btn_buscar_remito().setEnabled(true);
				frame.get_btn_cargar_remito().setEnabled(true);
				frame.get_lst_tipo_comprobante().setEnabled(false);
				frame.get_txt_remito().requestFocusInWindow();
			} else {
				this.evaluarFVN(tx);
			}
		}
	}

	// idarticulo,descripcion,cantidad,precio,total
	/*
	 * private void create_tabla(Object[][] results) {
	 * 
	 * 
	 * CustomTable table = new CustomTable();
	 * 
	 * Column col = new Column(); col = new Column(); col.setName("idarticulo");
	 * col.setWidth(120); col.setEditable(true); table.addColumn(col);
	 * 
	 * col = new Column(); col.setName("descripcion"); col.setWidth(300);
	 * col.setEditable(true); table.addColumn(col);
	 * 
	 * col = new Column(); col.setName("cantidad"); col.setWidth(70);
	 * col.setCellRenderer(new MoneyRenderer());
	 * col.setAligment(JTextField.RIGHT); col.setClass(Double.class);
	 * col.setEditable(true); table.addColumn(col);
	 * 
	 * col = new Column(); col.setName("precio"); col.setCellRenderer(new
	 * MoneyRenderer()); col.setAligment(JTextField.RIGHT);
	 * col.setClass(Double.class); col.setWidth(90); col.setEditable(true);
	 * table.addColumn(col);
	 * 
	 * col = new Column(); col.setName("total"); col.setCellRenderer(new
	 * MoneyRenderer()); col.setAligment(JTextField.RIGHT);
	 * col.setClass(Double.class); col.setWidth(90); col.setEditable(false);
	 * table.addColumn(col);
	 * 
	 * 
	 * table.setData(results);
	 * 
	 * Font fuente = new Font("Arial", Font.PLAIN, 9);
	 * table.setHeaderFont(fuente);
	 * 
	 * table.build();
	 * 
	 * table.fillData(); JTable _table = table.getTable();
	 * _table.setName(_Interface._table_items);
	 * aplicacion.ventas.facturador.constructor._Constructor C=
	 * (aplicacion.ventas.facturador.constructor._Constructor)
	 * this.getConstructor(); _table.addMouseListener(C.getMouseListener());
	 * frame.setJTable(table.getTable()); }
	 */

	private void create_table(Object[][] results) {

		CustomTable table = new CustomTable();
		table.setSortable(false);
		Column col = null;
		CellEditor pce = null;

		col = new Column();
		col.setName("");
		col.setWidth(30);
		col.setEditable(true);
		CheckBoxCellEditor chkce = new CheckBoxCellEditor();
		chkce.setItemListener(this._constructor.getItemListener());
		chkce.setTipo(Boolean.class);
		chkce.setName(_Interface._table_items_seleccionar);
		col.setCellEditor(chkce.getCellCheck());

		col.setClass(Boolean.class);
		table.addColumn(col);

		col = new Column();
		col.setName("idarticulo");
		col.setWidth(100);
		col.setEditable(true);
		col.setAligment(JTextField.RIGHT);

		pce = new CellEditor();
		pce.setAligment(JTextField.RIGHT);
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_items_idarticulo);
		pce.setTipo(String.class);

		col.setCellEditor(pce.getCellEditor());
		table.addColumn(col);

		col = new Column();
		col.setName("descripcion");
		col.setWidth(380);
		col.setEditable(true);
		col.setAligment(JTextField.LEFT);
		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_items_descripcion);
		pce.setTipo(String.class);
		pce.setAligment(JTextField.LEFT);
		col.setCellEditor(pce.getCellEditor());
		table.addColumn(col);

		col = new Column();
		col.setName("cant");
		col.setWidth(46);
		col.setEditable(true);
		col.setAligment(JTextField.RIGHT);
		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_items_cantidad);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());
		table.addColumn(col);

		col = new Column();
		col.setName("costo");
		col.setWidth(70);
		col.setEditable(true);
		col.setAligment(JTextField.RIGHT);
		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_items_costo);
		pce.setTipo(String.class);
		pce.setAligment(JTextField.RIGHT);
		col.setCellEditor(pce.getCellEditor());

		TableItemColorCellRenderer cellrenderer = new TableItemColorCellRenderer();
		cellrenderer.setLogic(this);
		cellrenderer.setLogic(this);
		col.setCellRenderer(cellrenderer);
		table.addColumn(col);

		col = new Column();
		col.setName("precio");
		col.setWidth(70);
		col.setEditable(true);
		col.setAligment(JTextField.RIGHT);
		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_items_precio);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());
		table.addColumn(col);

		col = new Column();
		col.setName("Desc");
		col.setWidth(50);
		col.setEditable(true);
		col.setAligment(JTextField.RIGHT);
		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_items_descuento);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());
		table.addColumn(col);

		col = new Column();
		col.setName("Total");
		col.setWidth(80);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_items_total);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());
		table.addColumn(col);

		table.setData(results);
		table.setName(_Interface._table_items);
		table.addKeyListener(this.getConstructor().getKeyListener());
		table.addMouseListener(this._constructor.getMouseListener());
		Font fuente = new Font("Dialog", Font.PLAIN, 10);
		table.setHeaderFont(fuente);
		table.setFont(fuente);
		table.build();
		table.fillData();

		JTable _table = table.getTable();

		System.out.println("Creation Table!");
		frame.setJTable(_table);
	}

	private void cargarCliente(String idcliente) {
		Object[][] results = this.data.getCliente(idcliente);
		if (results != null) {
			if (results.length > 0) {
				frame.get_txt_idcliente().setEditable(false);
				frame.get_txt_descripcion_cliente().setText(
						results[0][1].toString());
				frame.get_lst_tipo_comprobante().requestFocusInWindow();
				cambios = (this.getCambioCliente(idcliente));
			}
		}
	}

	private aplicacion.herramientas.java.evaluadores.Cliente cliente = null;

	public void initialize_cliente() {
		cliente = new aplicacion.herramientas.java.evaluadores.Cliente() {
			public void cargar(String codigo) {
				cargarCliente(codigo);
			}
		};
		cliente.setConstructor(this.getConstructor());
	}

	public void BuscarCliente(JTextField tx) {
		cliente.Buscar(tx);
	}

	public void buscarCliente(JTextField tx) {
		cliente.buscar(tx);
	}

	public void BuscarCliente() {
		cliente.Buscar(frame.get_txt_idcliente());
	}

	public void evaluarCliente(JTextField tx) {
		cliente.evaluate(tx);
	}

	public void inicializarDatos() {
		frame.get_txt_sucursal().setText("0001");

	}

	private aplicacion.herramientas.java.evaluadores.Remito Remito = null;

	public void initialize_Remito() {
		Remito = new aplicacion.herramientas.java.evaluadores.Remito() {
			public void cargar(String codigo) {
				cargar_remito(codigo, "");
			}
		};
		Remito.setConstructor(this.getConstructor());
	}

	public void BuscarRemito(JTextField tx) {
		Remito.Buscar(tx);
	}

	public void BuscarRemito() {
		Remito.Buscar(frame.get_txt_remito());
	}

	public void buscarRemito(JTextField tx) {
		Remito.buscar(tx);
	}

	public void evaluarRemito(JTextField tx) {
		Remito.evaluate(tx);
	}

	public void cargarRemito() {
		String idremito = frame.get_txt_remito().getText();
		this.cargar_remito(idremito, "");
	}

	public void cargar_remito(String idremito, String condicion) {
		if (Remito.existe(idremito)) {
			Object[][] results = data.getRemito(idremito);
			String fecha = results[0][0].toString();
			String cuenta = results[0][1].toString();
			String descripcion = results[0][2].toString();
			String importe = results[0][13].toString();
			String subtotal = results[0][14].toString();
			String vendedor = results[0][15].toString();
			if (condicion.compareTo("") == 0) {
				condicion = results[0][11].toString();
			}

			vendedor = vendedor.replaceAll(" ", "");
			condicion = condicion.replaceAll(" ", "");
			frame.get_txt_remito().setText(idremito);
			frame.get_txt_idcliente().setText(cuenta);
			/*
			 * frame.get_txt_idcliente().setEditable(false);
			 * frame.get_btn_buscar_cliente().setEnabled(false);
			 * frame.get_btn_cargar_cliente().setEnabled(false);
			 */
			frame.get_btn_cargar_remito().setEnabled(true);
			frame.get_btn_eliminar_remito().setEnabled(true);
			frame.get_txt_descripcion_cliente().setText(descripcion);
			frame.get_txt_subtotal().setText(
					new Convertidor().getMoney(subtotal, 2));
			frame.get_txt_total().setText(
					new Convertidor().getMoney(importe, 2));
			frame.get_txt_idcondicion().setText(condicion);
			frame.get_txt_idvendedor().setText(vendedor);
			frame.get_btn_grabar().setEnabled(true);

			this.Condicion.completar(frame.get_txt_idcondicion().getText());
			this.evaluarVendedor(frame.get_txt_idvendedor());
			System.out.println("Cargar Remito");
			this.cargarRemitoItems(idremito);
			String idpedido = data.getIdpedido(idremito);
			if (idpedido.compareTo("") != 0) {
				frame.get_btn_pdc().setEnabled(true);
			} else {
				frame.get_btn_pdc().setEnabled(false);
			}
			frame.get_txt_idpedido().setText(idpedido);
			frame.get_txt_idvendedor().requestFocusInWindow();
		} else {
			error("El Remito " + idremito + " no existe");
			frame.get_txt_remito().requestFocusInWindow();
		}
	}

	public void fillStock(int row) {
		if (row >= 0) {
			String idarticulo = "";
			try {
				idarticulo = frame.getJTable().getValueAt(row, 1).toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.fillExtra(idarticulo);
		}

	}

	public void focus(JTable table, int row, int col) {
		table.changeSelection(row, col, false, false);
		table.editCellAt(row, col);
		table.transferFocus();
	}

	public void editar_pedido() {
		String idpedido = frame.get_txt_idpedido().getText();
		if (idpedido.compareTo("") != 0) {
			this.editar_pedido(idpedido);
		}
	}

	public void editar_pedido(String idpedido) {
		aplicacion.ventas.pedido.constructor._Constructor pedido = new aplicacion.ventas.pedido.constructor._Constructor();
		pedido.setParameter(_parametros.connector, this.getConstructor()
				.getConnectionHandler().Clone());
		pedido.setParameter(_parametros.iduser, this.getConstructor()
				.getIduser());
		pedido.setParameter(_parametros.LookAndFeel, this.getConstructor()
				.getLookAndFeelTheme());
		pedido.setParameter(
				aplicacion.ventas.pedido.interfaces._parametros._idpedido,
				idpedido);
		pedido.build(this.getConstructor());
		pedido.init();
		this.getConstructor().addChild(pedido);
	}

	public boolean getCambioCliente(String idcliente) {
		String tc = frame.get_lst_tipo_comprobante().getSelectedItem()
				.toString();
		String idremito = frame.get_txt_remito().getText();
		boolean cambio = true;

		Object[][] results = data.getRemito(idremito);
		if (results != null) {
			String cuenta = results[0][1].toString();
			cambio = !(cuenta.compareTo(idcliente) == 0);
		}

		return cambio;
	}

	public void cargar_fvn(String idcomprobante) {
		String tc = frame.get_lst_tipo_comprobante().getSelectedItem()
				.toString();
		FVN.setTC(tc);
		if (FVN.existe(idcomprobante)) {
			Object[][] results = data.getFVN(tc, idcomprobante);
			String fecha = results[0][0].toString();
			String cuenta = results[0][1].toString();
			String descripcion = results[0][2].toString();
			String importe = results[0][3].toString();
			String subtotal = results[0][4].toString();
			String vendedor = results[0][5].toString().replaceAll(" ", "");
			String condicion = "";
			String anulada = results[0][6].toString();
			String remito = results[0][7].toString();

			frame.get_btn_imprimir().setEnabled(true);
			frame.get_txt_idcliente().setText(cuenta);
			frame.get_txt_idcliente().setEditable(false);
			frame.get_btn_buscar_cliente().setEnabled(false);
			frame.get_btn_cargar_cliente().setEnabled(false);
			frame.get_btn_cargar_remito().setEnabled(false);
			frame.get_btn_eliminar_remito().setEnabled(false);
			frame.get_txt_descripcion_cliente().setText(descripcion);
			frame.get_txt_subtotal().setText(
					new Convertidor().getMoney(subtotal, 2));
			frame.get_txt_total().setText(
					new Convertidor().getMoney(importe, 2));
			frame.get_txt_idcondicion().setText(condicion);
			frame.get_txt_idvendedor().setText(vendedor);
			frame.get_txt_remito().setText(remito);
			frame.get_txt_remito().setEnabled(false);
			frame.get_txt_idvendedor().setEnabled(false);
			frame.get_txt_idcondicion().setEnabled(false);
			frame.get_btn_eliminar().setEnabled(true);
			frame.get_btn_buscar_fecha().setEnabled(false);
			frame.get_txt_numero().setEnabled(false);
			frame.get_txt_fecha().setEnabled(false);
			this.evaluarVendedor(frame.get_txt_idvendedor());
			frame.get_lst_tipo_comprobante().setEnabled(false);
			frame.get_btn_grabar().setEnabled(false);
			this.cargarFVNItems(idcomprobante);

			String idpedido = data.getIdpedido(remito);
			if (idpedido.compareTo("") != 0) {
				frame.get_btn_pdc().setEnabled(true);
			} else {
				frame.get_btn_pdc().setEnabled(false);
			}
			frame.get_txt_idpedido().setText(idpedido);
		} else {
			error("La FVN " + idcomprobante + " no existe");
			frame.get_txt_numero().requestFocusInWindow();
		}
	}

	public void _eval_item_cantidad(JTextField tx, int row) {
		cambios = true;
		String cantidad = tx.getText();
		cantidad = cantidad.replace(",", "");
		double qty = 0.0;
		boolean error = false;
		try {
			qty = new Double(cantidad);
		} catch (Exception e) {
			error = true;
		}
		if (!error) {
			if (qty == 0) {
				this.eval_variables_from_cantidad(row, "1.0");
				tx.setText("1.0");
				tx.requestFocusInWindow();

			} else {

				tx.setText(new Convertidor().getMoney(qty, 2));
				int next = 5;
				this.eval_variables_from_cantidad(row, cantidad);
				// if (frame.get_chk_costo().isSelected()){
				boolean chk_costo = false;
				if (chk_costo) {
					next = 4;
				}
				frame.getJTable().changeSelection(row, next, false, false);
				frame.getJTable().editCellAt(row, next);
				frame.getJTable().transferFocus();
			}
		} else {
			this.eval_variables_from_cantidad(row, "1.0");
			tx.setText("1.0");
			tx.requestFocusInWindow();
			// error("error en cantidad");
		}
		this._calculate();
	}

	public void _eval_item_precio(JTextField tx, int row) {
		cambios = true;
		String idcliente = frame.get_txt_idcliente().getText();
		String precio = tx.getText();
		precio = precio.replaceAll(",", "");
		if (precio.compareTo("") == 0) {
			precio = "0.0";
		}
		double prc = 0.0;
		boolean error = false;
		try {
			prc = new Double(precio);
		} catch (Exception e) {
			error = true;
		}

		if (!error) {
			if (prc >= 0) {
				String idarticulo = frame.getJTable().getValueAt(row, 1)
						.toString();
				boolean publico = data.utilizaPrecioPublico(idcliente);
				double sys_prc = 0.0;
				if (publico) {
					sys_prc = data.getPrecioPublico(idarticulo);
				} else {
					sys_prc = data.getPrecioCosto(idarticulo);
				}

				if (sys_prc - prc > 0.1) {
					error("Precio " + prc + " Menor al de Sistema " + sys_prc);
				}
				Convertidor Cv = new Convertidor();
				tx.setText(Cv.getMoney(prc, 2));
				frame.getJTable().changeSelection(row, 6, false, false);
				frame.getJTable().editCellAt(row, 6);
				frame.getJTable().transferFocus();
				this.eval_variables_from_precio(row, precio);
				this._calculate();
			} else {
				this.eval_variables_from_precio(row, "0.0");
				error("el precio debe ser mayor a cero");

			}
		} else {
			this.eval_variables_from_precio(row, "0.0");
			error("error en precio");

		}

	}

	public Object[][] procesar_remito_items(Object[][] results) {
		Object[][] tmp = new Object[results.length][results[0].length + 1];
		
		if (results != null) {
			if (results.length > 0) {

				String idarticulo = "";
				String descripcion = "";
				String cantidad = "";
				String costo = "";
				String precio = "";
				String descuento = "";
				String total = "";
				for (int i = 0; i < results.length; i++) {

					idarticulo = results[i][0].toString();
					descripcion = results[i][1].toString();
					cantidad = results[i][2].toString();
					costo = results[i][3].toString();
					precio = results[i][4].toString();
					descuento = results[i][5].toString();
					total = results[i][6].toString();
					tmp[i][0] = true;
					tmp[i][1] = idarticulo;
					tmp[i][2] = descripcion;
					tmp[i][3] = new Double(cantidad);
					tmp[i][4] = new Double(costo);
					tmp[i][5] = new Double(precio);
					tmp[i][6] = new Double(descuento);
					tmp[i][7] = new Double(total);
				}

			}

		}
		return tmp;
	}

	public void editCell(int row, int col) {
		JTable table = frame.getJTable();
		table.changeSelection(row, col, false, false);
		table.editCellAt(row, col);
		table.transferFocus();
	}

	public void cargarRemitoItems(String id) {
		Object[][] results = data.getRemitoItems(id);
		if (results != null) {
			if (results.length > 0) {
				results = this.procesar_remito_items(results);
				this.create_table(results);
			}
		}
	}

	public void cargarFVNItems(String id) {
		String tc = frame.get_lst_tipo_comprobante().getSelectedItem()
				.toString();
		Object[][] results = data.getFVNItems(tc, id);
		if (results != null) {
			if (results.length > 0) {
				results = this.procesar_remito_items(results);
				this.create_table(results);
			}
		}
	}

	public boolean esFVNVieja(String tc, String idcomprobante) {
		String fecha = data.getFvnFecha(tc, idcomprobante);
		return this.esFechaVieja(fecha);
	}

	public void eliminar_fvn() {
		String tc = frame.get_lst_tipo_comprobante().getSelectedItem()
				.toString();
		String idcomprobante = frame.get_txt_numero().getText();
		String cuenta = frame.get_txt_idcliente().getText();
		String ip = data.getIp();
		if (!this.data.fvn_anulada(tc, idcomprobante)) {
			boolean su = !(this.esFVNVieja(tc, idcomprobante));
			String iduser = this.getConstructor().getIduser();
			if (!data.getIsSuperUser(iduser)) {
				String password = this
						.requestPassword("Ingrese Clave de Supervisor");
				iduser = this.getValidacion(password);
				if (iduser.compareTo("") != 0) {
					su = true;
					// validacion=iduser;

				} else {
					data.registrar_operacion(tc, idcomprobante, cuenta, iduser,
							ip, "intento de eliminacion");
					error("No Esta Autorizado");

				}
			} else {

				su = true;
			}

			if (su) {
				if (!this.data.fvn_conciliada(tc, idcomprobante)) {
					boolean confirmar = this.confirmar(
							"Confirme la Elimacion de la  " + tc + "-"
									+ idcomprobante
									+ ". Codigo de Confirmacion: ", 3);
					if (confirmar) {
						data.registrar_operacion(tc, idcomprobante, cuenta,
								iduser, ip, "eliminacion");
						this._eliminar_fvn();
					}
				} else {
					error("No puede anular un comprobante conciliado");
				}
			} else {
				error("No esta autorizado para efectuar esta operacion");
				data.registrar_operacion(tc, idcomprobante, cuenta, iduser, ip,
						"intento de eliminacion. no fue autorizado");
			}

		} else {
			error("El comprobante ya fue anulado");
		}
	}

	private List<String> getInstruccionesAsientoDeComprobante() {
		String cuenta = frame.get_txt_idcliente().getText();
		String descripcion = frame.get_txt_descripcion_cliente().getText();
		String tc = frame.get_lst_tipo_comprobante().getSelectedItem()
				.toString();
		String caja = "   1"; // que pasa si es otro punto de venta?
		String idcomprobante = frame.get_txt_numero().getText();
		String importe = frame.get_txt_total().getText().replace(",", "");
		String fecha = frame.get_txt_fecha().getText();
		boolean credito = frame.get_lst_tipo_comprobante().getSelectedItem()
				.toString().compareTo("FVN") == 0;
		List<String> instrucciones = data.getInstruccionesAsientoDeComprobante(
				cuenta, descripcion, tc, caja, idcomprobante, importe, fecha,
				credito);
		return instrucciones;

	}

	private List<String> getInstruccionesActualizacionDePunteros() {
		List<String> instrucciones = new ArrayList<String>();
		String tc = frame.get_lst_tipo_comprobante().getSelectedItem()
				.toString();
		String _instruccion = data.getUpdateTC(tc);
		instrucciones.add(_instruccion);
		return instrucciones;
	}

	private List<String> getInstruccionesDeComprobante() {
		List<String> instrucciones = new ArrayList<String>();
		String cuenta = frame.get_txt_idcliente().getText();
		String descripcion = frame.get_txt_descripcion_cliente().getText();
		String tc = frame.get_lst_tipo_comprobante().getSelectedItem()
				.toString();
		String caja = "   1"; // que pasa si es otro punto de venta?
		String idcomprobante = frame.get_txt_numero().getText();
		String subtotal = frame.get_txt_subtotal().getText().replace(",", "");
		String fecha = frame.get_txt_fecha().getText();
		String remito = frame.get_txt_remito().getText();
		String idvendedor = frame.get_txt_idvendedor().getText();
		String articulos = "1";
		String total = frame.get_txt_total().getText().replace(",", "");
		String _instruccion = data.getGuardarQuery(tc, idcomprobante,
				idvendedor, fecha, cuenta, descripcion, articulos, subtotal,
				total, remito);
		instrucciones.add(_instruccion);
		for (int i = 0; i < frame.getJTable().getRowCount(); i++) {
			String _idarticulo = frame.getJTable().getValueAt(i, 1).toString();
			String _descripcion = frame.getJTable().getValueAt(i, 2).toString();
			String _cantidad = frame.getJTable().getValueAt(i, 3).toString();
			String _precio = frame.getJTable().getValueAt(i, 5).toString();
			String _total = frame.getJTable().getValueAt(i, 7).toString();
			String desc = "0";
			String desc_imp = "0";
			String _instruccioni = data.getGuardarItems(tc, idcomprobante, i,
					_idarticulo, _descripcion, _cantidad, _precio, desc,
					desc_imp, _total);
			instrucciones.add(_instruccioni);
		}
		return instrucciones;
	}

	/**
	 * Procedimiento para marcar como anulado el remito q se transformo a FVN.
	 * Esto sirve por si se anula la FVN
	 */
	private List<String> getInstruccionesEsconderRemito() {
		List<String> instrucciones = new ArrayList<String>();
		String remito = frame.get_txt_remito().getText();
		String _instruccion = data.getMarcarAnuladoRemito(remito);
		instrucciones.add(_instruccion);

		return instrucciones;
	}

	/**
	 * Procedimiento para marcar como ok el remito q fue ocultado por una FVN.
	 */
	private List<String> getInstruccionesMostrarRemito() {
		List<String> instrucciones = new ArrayList<String>();
		String remito = frame.get_txt_remito().getText();
		String _instruccion = data.getMarcarDesanuladoRemito(remito);
		instrucciones.add(_instruccion);
		return instrucciones;
	}

	private List<String> getInstruccionesAnulacionFVN() {
		List<String> instrucciones = new ArrayList<String>();
		String tc = frame.get_lst_tipo_comprobante().getSelectedItem()
				.toString();
		String idcomprobante = frame.get_txt_numero().getText();
		String _instruccion = data.getAnularFVN(tc, idcomprobante);
		String remito = frame.get_txt_remito().getText();
		instrucciones.add(_instruccion);
		_instruccion = data.getAnularFVNAsiento(tc, idcomprobante);
		instrucciones.add(_instruccion);
		_instruccion = data.getAnularFVNStock(tc, idcomprobante);
		instrucciones.add(_instruccion);
		_instruccion = data.getMarcarDesanuladoRemito(remito);
		instrucciones.add(_instruccion);
		return instrucciones;
	}

	private List<String> getInstruccionesRegenerarRemitoItems() {
		String cuenta=frame.get_txt_idcliente().getText();
		List<String> instrucciones = new ArrayList<String>();
		String tc = "RM";
		String idcomprobante = frame.get_txt_remito().getText();
		String fecha = frame.get_txt_fecha().getText();

		for (int i = 0; i < frame.getJTable().getRowCount(); i++) {
			String idarticulo = frame.getJTable().getValueAt(i, 1).toString();
			String descripcion = frame.getJTable().getValueAt(i, 2).toString();
			String cantidad = frame.getJTable().getValueAt(i, 3).toString();

			String precio = frame.getJTable().getValueAt(i, 5).toString();
			String _instruccion = data.getInsertQueryVMVStock(tc,
					idcomprobante, idarticulo, descripcion, cantidad, i, fecha,
					precio,cuenta);
			instrucciones.add(_instruccion);
		}
		return instrucciones;
	}

	private List<String> getInstruccionesEliminarRemito() {
		// desaparecer el remito del sistema
		// cuando se paso mal el remito..
		// se lo marca como anulado. y se lo desvincula del pedido para q se
		// pueda generar otro
		List<String> instrucciones = new ArrayList<String>();
		String remito = frame.get_txt_remito().getText();
		String _instruccion = data.getMarcarAnuladoRemito(remito);
		instrucciones.add(_instruccion);
		_instruccion = data.getDeleteRemitoPedidoQuery(remito);
		instrucciones.add(_instruccion);
		return instrucciones;
	}

	private List<String> getInstruccionesDeStock() {
		List<String> instrucciones = new ArrayList<String>();
		String cuenta=frame.get_txt_idcliente().getText();
		String tc = frame.get_lst_tipo_comprobante().getSelectedItem()
				.toString();
		String idcomprobante = frame.get_txt_numero().getText();
		String fecha = frame.get_txt_fecha().getText();

		for (int i = 0; i < frame.getJTable().getRowCount(); i++) {
			String _idarticulo = frame.getJTable().getValueAt(i, 1).toString();
			String _descripcion = frame.getJTable().getValueAt(i, 2).toString();
			String _cantidad = frame.getJTable().getValueAt(i, 3).toString()
					.replaceAll(",", "");
			String _precio = frame.getJTable().getValueAt(i, 5).toString()
					.replaceAll(",", "");
			String _total = frame.getJTable().getValueAt(i, 7).toString()
					.replaceAll(",", "");
			String desc = "0";
			String desc_imp = "0";
			double cantidad = new Double(_cantidad);
			if (tc.compareTo("FVN") == 0) {
				cantidad = -cantidad;
			}
			String _instruccioni = data.getInsertQueryVMVStock(tc,
					idcomprobante, _idarticulo, _descripcion, "" + cantidad, i,
					fecha, _precio,cuenta);
			instrucciones.add(_instruccioni);
		}
		return instrucciones;
	}

	public void _eliminar_fvn_test() {
		List<String> batchlist = this.getInstruccionesAnulacionFVN();
		for (int i = 0; i < batchlist.size(); i++) {
			System.out.println(batchlist.get(i));
		}

	}

	public void _eliminar_fvn() {
		List<String> batchlist = this.getInstruccionesAnulacionFVN();
		data.beginTransaction();
		data.clearBatch();
		for (int i = 0; i < batchlist.size(); i++) {
			data.addBatch(batchlist.get(i));
		}
		boolean error = data.executeBatch();
		if (!error) {
			data.commitTransaction();
			aviso("Se Anulo La FVN Correctamente");
			this.clean();
		} else {
			data.rollbackTransaction();
			error("Error Anulando FVN");
		}
		data.setAutoCommit();
	}

	/**
	 * Elimina el remito generado
	 */
	public void eliminar_remito() {
		String tc = frame.get_lst_tipo_comprobante().getSelectedItem()
				.toString();
		String idcomprobante = frame.get_txt_numero().getText();
		String cuenta = frame.get_txt_idcliente().getText();
		String ip = data.getIp();
		String remito = frame.get_txt_remito().getText();
		if (remito.compareTo("") != 0) {
			String iduser = this.validar_usuario();
			if (iduser.compareTo("") != 0) {
				String idvendedor = "";
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

				boolean ok = this.eliminar_remito(remito);
				if (ok) {
					data.registrar_operacion(tc, idcomprobante, cuenta, iduser,
							ip, "Eliminar Remito " + remito);
					aviso("Se Elimino Correctamente");
					clean();
				} else {
					data.registrar_operacion(tc, idcomprobante, cuenta, iduser,
							ip, "Intento de Eliminacion de Remito " + remito);
					error("Error Eliminando Remito");
				}
			}
		}
	}

	public void grabarCtaCte() {
		String cuenta = frame.get_txt_idcliente().getText();
		String cuenta_descripcion = frame.get_txt_descripcion_cliente()
				.getText();
		String importe = frame.get_txt_total().getText();
		importe = new Convertidor().getMoney(importe, 2);
		String idcomprobante = frame.get_txt_sucursal().getText() + "-"
				+ frame.get_txt_numero().getText() + ""
				+ frame.get_lst_letra().getSelectedItem().toString();
		String tc = frame.get_lst_tipo_comprobante().getSelectedItem()
				.toString();
		String NEW_LINE = System.getProperty("line.separator");
		String mensaje = "Envia a Cuenta Corriente" + NEW_LINE;
		mensaje += "Cuenta:" + cuenta + " " + cuenta_descripcion + NEW_LINE;
		mensaje += "TC:" + tc + " " + idcomprobante + " $" + importe + NEW_LINE;
		mensaje += "Codigo de Confirmacion:";
		boolean confirma = this.confirmar(mensaje, 3);
		if (confirma) {
			boolean error = this.grabar(null);
			if (!error) {
				aviso("Se Grabo Correctamente el comprobante");
				imprimir();
				clean();
			} else {
				error("Error Grabando el comprobante");
			}
		}
	}

	public void guardarcambio() {
		boolean error = this._guardarcambio();
		if (!error) {
			aviso("Se Grabo Correctamente el comprobante");
			clean();
		} else {
			error("Error Grabando el comprobante");
		}

	}

	public boolean _guardarcambio() {
		boolean error = false;

		data.beginTransaction();
		data.clearBatch();
		List<String> instrucciones_cpte = this.getInstruccionesDeComprobante();
		List<String> instrucciones_stock = this.getInstruccionesDeStock();

		for (int i = 0; i < instrucciones_cpte.size(); i++) {
			data.addBatch(instrucciones_cpte.get(i));
		}

		for (int i = 0; i < instrucciones_stock.size(); i++) {
			data.addBatch(instrucciones_stock.get(i));
		}

		error = data.executeBatch();
		if (!error) {
			data.commitTransaction();
		} else {
			data.rollbackTransaction();
		}
		if (!error) {

			data.beginTransaction();
			data.clearBatch();

			List<String> instrucciones_remito = this
					.getInstruccionesEsconderRemito();
			List<String> instrucciones_punteros = this
					.getInstruccionesActualizacionDePunteros();
			for (int i = 0; i < instrucciones_remito.size(); i++) {
				data.addBatch(instrucciones_remito.get(i));
			}
			for (int i = 0; i < instrucciones_punteros.size(); i++) {
				System.out.println(instrucciones_punteros.get(i));
				data.addBatch(instrucciones_punteros.get(i));
			}
			error = data.executeBatch();
			if (!error) {
				data.commitTransaction();
			} else {
				data.rollbackTransaction();
			}
		}
		data.setAutoCommit();
		return error;
	}

	public boolean check_comprobantes_asociados(String remito) {

		boolean link = false;
		Object[][] results = data.tieneComprobanteBetaAsociado(remito);
		if (results != null) {
			if (results.length > 0) {
				link = true;
				String tc = (String) results[0][0];
				String idcomprobante = (String) results[0][1];
				error("No puede utilizar este remito porque esta asociado al comprobante "
						+ tc + "-" + idcomprobante);
			}
		}
		if (!link) {
			results = data.tieneComprobanteAlfaAsociado(remito);
			if (results != null) {
				if (results.length > 0) {
					link = true;
					String tc = (String) results[0][0];
					String idcomprobante = (String) results[0][1];
					error("No puede utilizar este remito porque esta asociado al comprobante "
							+ tc + "-" + idcomprobante);

				}
			}
		}
		return !link;
	}

	public void guardar() {
		boolean ok = true;
		this._calculate();		
		if (cambios) {
			ok = confirmar(
					"Se Detectaron Cambios.Modificara el Remito Anterior. Confirme para Continuar:",
					3);
		}
		if (ok) {
			String iduser = this.validar_usuario();
			if (iduser.compareTo("") != 0) {
				String idvendedor = "";
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
				_guardar();
			}
		} else {
			error("OPERCACION CANCELADA");
		}

	}

	public void _guardar() {
		boolean ok = true;
		String condicion = frame.get_txt_idcondicion().getText();
		if (cambios) {
			ok = this.generar_remito();
			if (ok) {
				String idpedido = frame.get_txt_idpedido().getText();
				String remito = data.getIdRemito(idpedido);
				this.cargar_remito(remito, condicion);
			}
		}
		if (ok) {
			String remito = frame.get_txt_remito().getText();
			String importe = frame.get_txt_total().getText().replace(",", "");
			String idcomprobante = frame.get_txt_sucursal().getText() + "-"
					+ frame.get_txt_numero().getText() + ""
					+ frame.get_lst_letra().getSelectedItem().toString();
			String tc = frame.get_lst_tipo_comprobante().getSelectedItem()
					.toString();
			this.obtener_proximo_cpte(tc);
			String NEW_LINE = System.getProperty("line.separator");
			double imp = 0.0;
			try {
				imp = new Double(importe);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (remito.compareTo("") != 0) {
				if (this.check_comprobantes_asociados(remito)) {
					if (imp != 0) {
						if (frame.get_txt_idcondicion().getText().compareTo(
								"10") == 0) {
							this.grabarCtaCte();
						} else {
							if (imp < 0) {
								this.goCobranza();
							} else {
								this.goCobranza();
							}

						}
					} else {
						if (imp == 0) {
							String mensaje = "Confirme El Cambio " + tc + "-"
									+ idcomprobante + "." + NEW_LINE;
							mensaje += " Codigo de Confirmacion: ";
							boolean confirmar = this.confirmar(mensaje, 3);
							if (confirmar) {
								this.guardarcambio();
							}
						}
					}
				} else {
					error("Remito " + remito + " ya fue utilizado");
				}

			} else {
				error("Seleccione un remito valido");
				frame.get_txt_remito().requestFocusInWindow();
			}
		} else {
			error("Error Grabando Comprobante");
		}

	}

	public void grabarConCobranza(List<String> instrucciones_cobranza) {
		boolean error = this.grabar(instrucciones_cobranza);

		if (!error) {
			aviso("Se Grabo Correctamente el comprobante");
			Cobranza.dispose();
			imprimir();
			clean();
		} else {
			error("Error Grabando el comprobante");
		}

	}

	public boolean grabar(List<String> instrucciones_cobranza) {
		boolean error = false;

		data.beginTransaction();
		data.clearBatch();
		List<String> instrucciones_cpte = this.getInstruccionesDeComprobante();
		List<String> instrucciones_asiento_cpte = this
				.getInstruccionesAsientoDeComprobante();
		List<String> instrucciones_stock = this.getInstruccionesDeStock();

		for (int i = 0; i < instrucciones_cpte.size(); i++) {
			data.addBatch(instrucciones_cpte.get(i));
		}
		for (int i = 0; i < instrucciones_asiento_cpte.size(); i++) {
			data.addBatch(instrucciones_asiento_cpte.get(i));
		}
		if (instrucciones_cobranza != null) {
			for (int i = 0; i < instrucciones_cobranza.size(); i++) {
				data.addBatch(instrucciones_cobranza.get(i));
			}
		}

		for (int i = 0; i < instrucciones_stock.size(); i++) {
			data.addBatch(instrucciones_stock.get(i));
		}

		error = data.executeBatch();
		if (!error) {
			data.commitTransaction();
		} else {
			data.rollbackTransaction();
		}
		if (!error) {

			data.beginTransaction();
			data.clearBatch();

			List<String> instrucciones_remito = this
					.getInstruccionesEsconderRemito();
			List<String> instrucciones_punteros = this
					.getInstruccionesActualizacionDePunteros();
			for (int i = 0; i < instrucciones_remito.size(); i++) {
				data.addBatch(instrucciones_remito.get(i));
			}
			for (int i = 0; i < instrucciones_punteros.size(); i++) {
				System.out.println(instrucciones_punteros.get(i));
				data.addBatch(instrucciones_punteros.get(i));
			}
			error = data.executeBatch();
			if (!error) {
				data.commitTransaction();
			} else {
				data.rollbackTransaction();
			}
		}
		data.setAutoCommit();
		return error;
	}

	private aplicacion.cliente.cobranza.constructor._Constructor Cobranza = null;

	public void goCobranza() {

		final boolean credito = frame.get_lst_tipo_comprobante()
				.getSelectedItem().toString().compareTo("FVN") == 0;
		if (Cobranza != null) {
			Cobranza.dispose();
		}
		Cobranza = new aplicacion.cliente.cobranza.constructor._Constructor() {
			public void initialize_logic() {
				this._logic = new aplicacion.cliente.cobranza.logic._Logic() {
					public void GrabarCobranza(boolean credito) {
						aplicacion.cliente.cobranza.logic.extensions._Asiento control = (aplicacion.cliente.cobranza.logic.extensions._Asiento) getExtension(aplicacion.cliente.cobranza.interfaces._Interface._extension_asiento);
						grabarConCobranza(control
								.getBloqueDeInstrucciones(credito));
					}
				};

			}
		};
		String tc = frame.get_lst_tipo_comprobante().getSelectedItem()
				.toString();
		String idcomprobante = frame.get_txt_numero().getText();
		String importe = frame.get_txt_total().getText();
		String cuenta = frame.get_txt_idcliente().getText();
		String fecha = frame.get_txt_fecha().getText();
		Cobranza
				.setParameter(
						aplicacion.cliente.cobranza.interfaces._Parametros._cpte_tc,
						tc);
		Cobranza.setParameter(
				aplicacion.cliente.cobranza.interfaces._Parametros._cpte_id,
				idcomprobante);
		Cobranza.setParameter(
				aplicacion.cliente.cobranza.interfaces._Parametros._cpte_fecha,
				fecha);
		Cobranza
				.setParameter(
						aplicacion.cliente.cobranza.interfaces._Parametros._cpte_importe,
						importe);
		Cobranza.setParameter(
				aplicacion.cliente.cobranza.interfaces._Parametros.idcliente,
				cuenta);
		Cobranza.setParameter(
				aplicacion.cliente.cobranza.interfaces._Parametros._ventas,
				this.getConstructor());
		Cobranza.setParameter(
				aplicacion.cliente.cobranza.interfaces._Parametros._credito,
				credito);
		Cobranza.setParameter(_parametros.LookAndFeel, this.getConstructor()
				.getLookAndFeelTheme());
		Cobranza.setParameter(_parametros.iduser, this.getConstructor()
				.getIduser());
		Cobranza.setParameter(_parametros.connector, this.getConstructor()
				.getConnectionHandler());
		Cobranza.build(this.getConstructor());
		Cobranza.init();
	}

	private aplicacion.herramientas.java.ireport.constructor._Constructor reporte = null;

	public void imprimir() {
		String tc = frame.get_lst_tipo_comprobante().getSelectedItem()
				.toString();
		String idcomprobante = frame.get_txt_numero().getText();
		if (preguntar("Confirmar", "Imprime Copia de Comprobante " + tc + "-"
				+ idcomprobante)) {
			reporte();
		}
	}

	public void reporte() {
		if (reporte != null) {
			reporte.dispose();
		}
		String tc = frame.get_lst_tipo_comprobante().getSelectedItem()
				.toString();
		String idcomprobante = frame.get_txt_numero().getText();
		reporte = new aplicacion.herramientas.java.ireport.constructor._Constructor();
		HashMap param = new HashMap();
		param.put("tc", tc);
		param.put("idcomprobante", idcomprobante);
		reporte.setParameter(_parametros.LookAndFeel, this.getConstructor()
				.getLookAndFeelTheme());
		reporte.setParameter(_parametros.connector, this.getConstructor()
				.getConnectionHandler());
		reporte
				.setParameter(
						aplicacion.herramientas.java.ireport.interfaces._parametros.parametros,
						param);
		reporte
				.setParameter(
						aplicacion.herramientas.java.ireport.interfaces._parametros.reporte,
						"fvn.jasper");
		reporte.build(this.getConstructor());
		reporte.init();
	}

	public void _recalculate_precio(String idarticulo, boolean publico, int row) {
		Object[][] results = data.getArticulo(idarticulo);
		boolean exist = false;
		String idcliente = frame.get_txt_idcliente().getText();
		try {
			TableCellEditor celleditor = frame.getJTable().getCellEditor();
			if (celleditor != null) {
				celleditor.stopCellEditing();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (results != null) {
			if (results.length > 0) {
				exist = true;
				String precio = (String) results[0][1];
				String costo = (String) results[0][2];
				if (!publico) {
					double _costo = new Double(costo);
					double _precio = _costo * 1.21;
					precio = new Convertidor().getMoney(_precio, 2);
				}
				this.eval_variables_from_precio(row, precio);
				frame.getJTable().setValueAt(
						new Convertidor().getMoney(precio, 2), row, 5);

			}
		}
	}

	public void eval_variables_from_precio(int row, String precio) {
		String descuento = "0.0";
		String cantidad = "0.0";
		try {
			descuento = frame.getJTable().getValueAt(row, 6).toString();
			cantidad = frame.getJTable().getValueAt(row, 3).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.eval_variables(row, cantidad, precio, descuento);
		this._calculate();
	}

	public void eval_variables(int row, String cantidad, String precio,
			String descuento) {

		double dsc = 0.0;
		double prc = 0.0;
		double total = 0.0;
		double qty = 0.0;

		try {
			if (descuento.contains("+")) {
				dsc = new Double(descuento);
			} else {
				dsc = this.formula(descuento).doubleValue();
			}
		} catch (Exception e) {

		}
		if (precio.compareTo("") == 0) {
			precio = "0.0";
		}
		if (cantidad.compareTo("") == 0) {
			cantidad = "0.0";
		}
		prc = new Double(precio.replace(",", ""));
		qty = new Double(cantidad.replace(",", ""));
		prc = prc * (100 - dsc) / 100;
		total = prc * qty;
		frame.getJTable().setValueAt(new Convertidor().getMoney(total, 2), row,
				7);
	}

	public void _recalculate_precios() {

		String idcliente = frame.get_txt_idcliente().getText();
		boolean publico = data.utilizaPrecioPublico(idcliente);
		if (frame.getJTable() != null) {
			for (int i = 0; i < frame.getJTable().getRowCount(); i++) {
				String idarticulo = (String) frame.getJTable().getValueAt(i, 1);
				this._recalculate_precio(idarticulo, publico, i);
			}
		}
		this._calculate();
	}

	public boolean _calculate() {
		return this._calculate(-1, false);
	}

	public boolean _calculate(int row, boolean selected) {
		
		boolean ok = true;
		double items = 0.0;
		double subtotal = 0.0;
		double suma = 0.0;
		if (row < 0) {
			try {
				frame.getJTable().getCellEditor().stopCellEditing();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
		double unidades = 0.0;
		double rows = 0;
		double siva = 0.0;
		double iva = 0.0;
		String idcliente = frame.get_txt_idcliente().getText();
		boolean ri=data.esResponsableInscripto(idcliente);
		if (frame.getJTable() != null) {
			for (int i = 0; i < frame.getJTable().getRowCount(); i++) {

				boolean seleccionado = false;

				String _cantidad = "";
				String _idarticulo = "";
				String _precio = "";
				String _descuento = "";
				double cantidad = 0.0;
				double precio = 0.0;
				double descuento = 0.0;
				double importe = 0.0;
				if (i == row) {
					seleccionado = selected;
				} else {
					try {
						seleccionado = (Boolean) frame.getJTable().getValueAt(
								i, 0);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				try {
					_idarticulo = frame.getJTable().getValueAt(i, 1).toString();
				} catch (Exception e) {

				}
				if (_idarticulo.compareTo("") != 0) {
					try {
						_cantidad = frame.getJTable().getValueAt(i, 3)
								.toString();
						_precio = frame.getJTable().getValueAt(i, 5).toString();
						_descuento = frame.getJTable().getValueAt(i, 6)
								.toString();
						_cantidad = _cantidad.replace(",", "");
						_precio = _precio.replace(",", "");
						_descuento = _descuento.replace(",", "");
						cantidad = new Double(_cantidad);
						precio = new Double(_precio);
						descuento = new Double(_descuento);
						if (descuento > 0 & descuento <= 99.99) {
							System.out.println(i + " dsc=" + descuento);
							precio = precio * (100 - descuento) / 100;
							System.out.println(i + " precio=" + precio);
							importe = cantidad * precio;

						} else {
							importe = cantidad * precio;
						}
						try {
							frame.getJTable().setValueAt(importe, i, 7);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} catch (Exception e) {
						ok = true;
					}
					if (importe > 0) {
						rows++;
					}
					if (seleccionado) {
						suma += importe;
						unidades += cantidad;
					}

				}

			}

		
			
		}

		  if (ri){ 
			  siva=suma;
			  iva=suma*0.21;
			  suma=siva+iva;
		  }
		  else {
			  siva=suma;
			  iva=0;
		  }
		Convertidor c = new Convertidor();
		String _unidades = c.getMoney(unidades, 2);
		// frame.get_txt_items().setText(_unidades);
		String _subtotal = c.getMoney(siva, 2);
		frame.get_txt_subtotal().setText(_subtotal);
		String _iva = c.getMoney(iva, 2);
		// frame.get_txt_iva().setText(_iva);
		String total = c.getMoney(suma, 2);
		
		
		frame.get_txt_total().setText(total);
		
		return ok;
	}

	public void complete_asterisco(int row) {
		String cantidad = "";
		try {
			cantidad = frame.getJTable().getValueAt(row, 3).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String costo = "";
		try {
			costo = frame.getJTable().getValueAt(row, 4).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String precio = "";
		try {
			precio = frame.getJTable().getValueAt(row, 5).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String descuento = "";
		try {
			descuento = frame.getJTable().getValueAt(row, 6).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String total = "";
		try {
			total = frame.getJTable().getValueAt(row, 7).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (cantidad.compareTo("") == 0) {
			cantidad = "0.0";
			frame.getJTable().setValueAt(cantidad, row, 3);
		}
		if (costo.compareTo("") == 0) {
			costo = "0.0";
			frame.getJTable().setValueAt(costo, row, 4);
		}
		if (precio.compareTo("") == 0) {
			precio = "0.0";
			frame.getJTable().setValueAt(precio, row, 5);
		}
		if (descuento.compareTo("") == 0) {
			descuento = "0.0";
			frame.getJTable().setValueAt(descuento, row, 6);
		}
		if (total.compareTo("") == 0) {
			total = "0.0";
			frame.getJTable().setValueAt(total, row, 7);
		}
	}

	public void _eval_item_articulo(JTextField tx, int row) {
		String aux = "";
		cambios = true;

		try {
			aux = tx.getText();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		tx.requestFocusInWindow();
		if (aux.compareTo("") != 0) {
			if (aux.compareTo("*") == 0) {
				complete_asterisco(row);
				frame.getJTable().changeSelection(row, 2, false, false);
				frame.getJTable().editCellAt(row, 2);
				frame.getJTable().transferFocus();

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

			tx.setText("*");
			tx.requestFocusInWindow();

		}
	}

	private aplicacion.herramientas.java.visualizadores.Equivalencia vEquivalencia = null;

	public int buscarEquivalencia(JTextField tx) {
		if (vEquivalencia != null) {
			vEquivalencia.close();
		}
		vEquivalencia = new aplicacion.herramientas.java.visualizadores.Equivalencia(
				this.getConstructor());

		int n = vEquivalencia.Buscar(tx);
		/*
		 * if (n == 0) { aviso("no hay Equivalencias con ese codigo"); }
		 */
		return n;
	}

	public void _evaluate_idarticulo(JTextField tx, int row) {
		cambios = true;
		String idarticulo = tx.getText();
		Object[][] results = data.getArticulo(idarticulo);
		boolean exist = false;
		boolean bloqueado = false;
		String idcliente = frame.get_txt_idcliente().getText();
		boolean publico = data.utilizaPrecioPublico(idcliente);
		int equiv = 0;
		if (results != null) {
			if (results.length > 0) {
				exist = true;
				String suspendidov = (String) results[0][4];
				String stock = (String) results[0][6];
				double _stock = 0.0;
				try {
					_stock = new Double(stock);
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				boolean ok = true;
				if (_stock <= 0 | suspendidov.compareTo("1") == 0) {

					if (idarticulo.compareTo(equivalencia) != 0) {
						equivalencia = idarticulo;
						System.out.println("Buscar Equivalencia para "
								+ idarticulo);
						ok = false;
						equiv = this.buscarEquivalencia(tx);
						if (equiv == 0) {
							ok = true;
						}
					} else {
						ok = true;
					}
				} else {
					equivalencia = "";
				}

				if (!ok) {
				} else {
					equivalencia = "";

					tx.setText(idarticulo.toUpperCase());
					String descripcion = (String) results[0][0];
					String precio = (String) results[0][1];
					String costo = (String) results[0][2];
					if (costo.compareTo("") == 0) {
						costo = "0.0";
					}
					if (precio.compareTo("") == 0) {
						precio = "0.0";
					}
					boolean empty = true;
					try {
						empty = frame.getJTable().getValueAt(row, 2).toString()
								.compareTo("") == 0;
					} catch (Exception e) {

					}
					boolean doit = true;
					String cantidad = "";
					try {
						cantidad = frame.getJTable().getValueAt(row, 3)
								.toString();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					double _precio = 0.0;
					double _costo = 0.0;
					double _cantidad = 0.0;
					double _total = 0.0;

					try {
						_cantidad = new Double(cantidad.replaceAll(",", ""));
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String total = "";
					if (!publico) {
						_costo = new Double(costo);
						_precio = _costo * 1.21;
						precio = new Convertidor().getMoney(_precio, 2);
						_total = _precio * _cantidad;
						total = new Convertidor().getMoney(_total, 2);
					} else {
						_precio = new Double(precio);
						_total = _precio * _cantidad;
						total = new Convertidor().getMoney(_total, 2);
					}

					if (_precio <= 0) {
						aviso("Precio Nulo.Verifique Precio de Articulo");
					}
					if (doit) {
						frame.getJTable().setValueAt(descripcion, row, 2);
						if (cantidad.compareTo("") == 0) {
							frame.getJTable().setValueAt("1.0", row, 3);
						}

						frame.getJTable().setValueAt(
								new Convertidor().getMoney(costo, 2), row, 4);
						frame.getJTable().setValueAt(
								new Convertidor().getMoney(precio, 2), row, 5);
						frame.getJTable().setValueAt("0.0", row, 6);
						frame.getJTable().setValueAt(
								new Convertidor().getMoney(total, 2), row, 7);
					} else {
						frame.getJTable().changeSelection(row, 2, false, false);
						frame.getJTable().editCellAt(row, 2);
						frame.getJTable().transferFocus();
					}
					this._calculate();
					fillExtra(idarticulo);

				}

			}
		}

		if (exist) {
			if (bloqueado) {

			} else {
				if (equiv <= 0) {
					frame.getJTable().changeSelection(row, 3, false, false);
					frame.getJTable().editCellAt(row, 3);
					frame.getJTable().transferFocus();
				}

			}

		} else {

			if (preguntar("confirmar", "El articulo " + idarticulo
					+ " no existe. ")) {
				tx.requestFocusInWindow();
				tx.setSelectionStart(0);
				tx.setSelectionEnd(tx.getText().length());
			} else {
				tx.requestFocusInWindow();
				tx.setSelectionStart(0);
				tx.setSelectionEnd(tx.getText().length());
			}

		}
	}

	public void fillExtra(String idarticulo) {

		Object[][] results = data.getData(idarticulo);
		frame.get_txt_articulo().setText("");
		frame.get_txt_articulo_descripcion().setText("");
		frame.get_txt_articulo_stock().setText("");
		if (results != null) {
			if (results.length > 0) {
				Convertidor cv = new Convertidor();
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

				double _pedido = data.getPedidoCantidad(idarticulo);
				double stock = new Double(_stock);
				frame.get_txt_articulo_pedido()
						.setText(cv.getMoney(_pedido, 2));
			}
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
		articulo.setParameter(_parametros.iduser, this.getConstructor()
				.getIduser());
		articulo
				.setParameter(
						aplicacion.inventario.articulo.interfaces._parametros.idarticulo,
						idarticulo);
		articulo.build(this.getConstructor());
		articulo.init();
	}

	private aplicacion.herramientas.java.importadores.Articulo bArticulo = null;
	public aplicacion.herramientas.java.visualizadores.Articulo vArticulo = null;

	public void BuscarArticulo(JTextField ext) {
		if (bArticulo != null) {
			bArticulo.close();
		}

		bArticulo = new aplicacion.herramientas.java.importadores.Articulo(this
				.getConstructor()) {
			public void cargar(Object[] seleccion) {
				agregar(seleccion);
			}
		};
		bArticulo.setSuspendidov(true);
		bArticulo.Buscar(ext);
	}

	private aplicacion.ventas.catalogo.constructor._Constructor Catalogo = null;

	public void BuscarCatalogo(JTextField ext) {
		if (Catalogo != null) {
			Catalogo.dispose();
			Catalogo = null;
		}
		if (Catalogo == null) {
			Catalogo = new aplicacion.ventas.catalogo.constructor._Constructor();

			Catalogo.setParameter(_parametros.connector, data
					.getConnectionHandler());
			Catalogo.setParameter(_parametros.LookAndFeel, this
					.getConstructor().getLookAndFeelTheme());
			Catalogo.build(this.getConstructor());
			Catalogo.init();
			this.getConstructor().addChild(Catalogo);
		}

		aplicacion.ventas.catalogo.logic._Logic logic = (aplicacion.ventas.catalogo.logic._Logic) Catalogo
				.getLogic();
		if (logic != null) {
			logic
					.setFacturador((aplicacion.ventas.facturador.constructor._Constructor) this
							.getConstructor());
		}

	}

	public void BuscarCatalogo() {
		if (Catalogo != null) {
			Catalogo.dispose();
			Catalogo = null;
		}
		if (Catalogo == null) {
			Catalogo = new aplicacion.ventas.catalogo.constructor._Constructor();

			Catalogo.setParameter(_parametros.connector, data
					.getConnectionHandler());
			Catalogo.setParameter(_parametros.LookAndFeel, this
					.getConstructor().getLookAndFeelTheme());
			Catalogo.build(this.getConstructor());
			Catalogo.init();
			this.getConstructor().addChild(Catalogo);
		}

		aplicacion.ventas.catalogo.logic._Logic logic = (aplicacion.ventas.catalogo.logic._Logic) Catalogo
				.getLogic();
		if (logic != null) {
			logic
					.setPedido((aplicacion.ventas.pedido.constructor._Constructor) this
							.getConstructor());
		}

	}

	public void agregar(String idarticulo) {
		cambios = true;

		String cantidad = "1.0";
		String descuento = "0.0";
		JTable table = frame.getJTable();
		try {
			table.getCellEditor().stopCellEditing();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		Object[][] results = data.getArticulo(idarticulo);
		if (results != null) {
			if (results.length > 0) {
				int exist = this.existArticulo(idarticulo);
				String costo = results[0][2].toString();
				String precio = results[0][1].toString();
				String descripcion = results[0][0].toString();
				if (exist < 0) {
					int row = 0;
					if (table != null) {

						row = this.existEmpty();
						if (row < 0) {
							DefaultTableModel model = (DefaultTableModel) table
									.getModel();
							model.setRowCount(model.getRowCount() + 1);
							row = model.getRowCount() - 1;
						}

					} else {
						this.crear_tabla_items();
					}
					table.setValueAt(true, row, 0);
					table.setValueAt(idarticulo, row, 1);
					table.setValueAt(descripcion, row, 2);
					table.setValueAt(cantidad, row, 3);
					double _precio = 0.0;
					double _costo = 0.0;
					try {
						_costo = new Double(costo.replaceAll(",", ""));
						_precio = new Double(precio.replaceAll(",", ""));
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					table.setValueAt(""
							+ new Convertidor().roundDouble(_costo, 2), row, 4);
					table
							.setValueAt(
									""
											+ new Convertidor().roundDouble(
													_precio, 2), row, 5);
					table.setValueAt(descuento, row, 6);
					this.eval_variables(row, cantidad, precio, descuento);
					table.changeSelection(row, 1, false, false);
					table.editCellAt(row, 1);
					table.transferFocus();
				}

				System.out.println(idarticulo + " " + descripcion + " "
						+ cantidad + " " + costo + " " + precio);

			}
		}
		this._calculate();
	}

	private void crear_tabla_items() {
		System.out.println("Create Table of Items");
		Object[][] results = new Object[][] { { true, "", "", "", "", "", "",
				"" } };
		this.create_table(results);
	}

	public void agregar(Object[] seleccion) {
		System.out.println("Agregar Seleccion ");
		cambios = true;

		String idarticulo = (String) seleccion[0];
		String descripcion = (String) seleccion[1];
		String cantidad = "1.0";
		String descuento = "0.0";
		JTable table = frame.getJTable();
		try {
			table.getCellEditor().stopCellEditing();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		String costo = "0.0";
		String precio = "0.0";

		Object[][] results = data.getArticulo(idarticulo);
		if (results != null) {
			if (results.length > 0) {
				int exist = this.existArticulo(idarticulo);

				try {
					costo = results[0][2].toString();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					precio = results[0][1].toString();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (exist >= 0) {
					int n = this.preguntar("Confirmar",
							"El articulo ya se encuentra en el pedido.",
							new String[] { "Agregar", "No Agregar" },
							"No Agregar");
					if (n == 0) {
						exist = -1;
					}

				}
				if (exist < 0) {
					int row = 0;
					if (table != null) {

						row = this.existEmpty();
						if (row < 0) {
							DefaultTableModel model = (DefaultTableModel) table
									.getModel();
							model.setRowCount(model.getRowCount() + 1);
							row = model.getRowCount() - 1;
						}

					} else {
						this.crear_tabla_items();
					}
					table.setValueAt(true, row, 0);
					table.setValueAt(idarticulo, row, 1);
					table.setValueAt(descripcion, row, 2);
					table.setValueAt(cantidad, row, 3);
					double _precio = 0.0;
					double _costo = 0.0;
					try {
						_costo = new Double(costo.replaceAll(",", ""));
						_precio = new Double(precio.replaceAll(",", ""));
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					table.setValueAt(""
							+ new Convertidor().roundDouble(_costo, 2), row, 4);
					table
							.setValueAt(
									""
											+ new Convertidor().roundDouble(
													_precio, 2), row, 5);
					table.setValueAt(descuento, row, 6);
					this.eval_variables(row, cantidad, precio, descuento);
					table.changeSelection(row, 1, false, false);
					table.editCellAt(row, 1);
					table.transferFocus();
				}

				System.out.println(idarticulo + " " + descripcion + " "
						+ cantidad + " " + costo + " " + precio);

			}
		}
		this._calculate();
	}

	public int existEmpty() {
		int exist = -1;
		int i = 0;
		JTable table = frame.getJTable();
		if (table != null) {
			while (i < table.getRowCount() & exist < 0) {
				String _idarticulo = table.getValueAt(i, 1).toString();
				if (_idarticulo.compareTo("") == 0) {
					exist = i;
				}
				i++;
			}
		}
		return exist;
	}

	public int existArticulo(String idarticulo) {
		int exist = -1;
		int i = 0;
		JTable table = frame.getJTable();
		if (table != null) {
			while (i < table.getRowCount() & exist < 0) {
				String _idarticulo = table.getValueAt(i, 1).toString();
				if (idarticulo.compareTo(_idarticulo) == 0) {
					exist = i;
				}
				i++;
			}
		}
		return exist;
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

	public BigDecimal formula(String fx) {

		boolean error = false;
		double multi = 1.0;
		int i = 0;
		int last = 0;
		int len = fx.length();
		if (fx.contains("+")) {
			System.out.println(fx + " contiene +");
			while (i < len) {

				if (fx.substring(i, i + 1).compareTo("+") == 0) {
					System.out.println("(+) encontrado en " + i);
					String number = fx.substring(last, i);
					double _number = 0.0;
					try {
						_number = new Double(number);
					} catch (Exception e) {
						e.printStackTrace();
					}
					if (_number > 0) {
						System.out.println("descuento " + _number);
						double ax = (100 - _number) / 100;
						multi = multi * ax;
					}

					last = i + 1;
				}
				i++;
			}
		} else {
			i = fx.length();
		}

		String number = fx.substring(last, i);
		System.out.println(i + "> " + number + " " + last);
		if (number.compareTo("") == 0) {
			number = "0.0";
		}
		double _number = 0.0;
		try {
			_number = new Double(number);
		} catch (Exception e) {
			// /printStackTrace();
		}
		if (_number > 0) {
			System.out.println("descuento " + _number);
			double ax = (100 - _number) / 100;
			multi = multi * ax;
		}
		multi = (1.0 - multi) * 100;
		BigDecimal bd = new BigDecimal(Double.toString(multi));
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		return bd;
	}

	public void _eval_item_total(JTextField tx, int row) {
		cambios = true;
		DefaultTableModel model = (DefaultTableModel) this.frame.getJTable()
				.getModel();
		if (row == model.getRowCount() - 1) {
			model.setRowCount(model.getRowCount() + 1);

		}
		frame.getJTable().changeSelection(row + 1, 1, false, false);
		frame.getJTable().editCellAt(row + 1, 1);
		frame.getJTable().transferFocus();

	}

	public void _eval_item_descripcion(JTextField tx, int row) {
		String desc = tx.getText();
		cambios = true;
		if (desc.compareTo("") != 0) {
			frame.getJTable().changeSelection(row, 3, false, false);
			frame.getJTable().editCellAt(row, 3);
			frame.getJTable().transferFocus();
		} else {
			error("la descripcion no puede ser nula");
			tx.requestFocusInWindow();
			tx.setSelectionStart(0);
			tx.setSelectionEnd(tx.getText().length());
		}
	}

	public void _eval_item_descuento(JTextField tx, int row) {
		cambios = true;
		String descuento = tx.getText();
		descuento = descuento.replace(",", "");
		double dsc = 0.0;
		boolean error = false;
		try {
			String _cantidad = frame.getJTable().getValueAt(row, 3).toString();
			String _precio = frame.getJTable().getValueAt(row, 5).toString();
			_cantidad.replace(",", "");
			_precio = _precio.replace(",", "");
		} catch (Exception e) {

		}
		if (!descuento.contains("+")) {
			try {
				dsc = new Double(descuento);
			} catch (Exception e) {

			}
		} else {
			dsc = this.formula(descuento).doubleValue();
		}

		if (!error) {
			if (dsc >= 0 & dsc <= 99.99) {
				descuento = "0.0";
				tx.setText(new Convertidor().getMoney(descuento, 2));
				this.eval_variables_from_descuento(row, descuento);

				DefaultTableModel model = (DefaultTableModel) this.frame
						.getJTable().getModel();
				if (row == model.getRowCount() - 1) {
					model.setRowCount(model.getRowCount() + 1);
					model.setValueAt(true, model.getRowCount() - 1, 0);
				} else {

				}

				frame.getJTable().changeSelection(row + 1, 1, false, false);
				frame.getJTable().editCellAt(row + 1, 1);
				frame.getJTable().transferFocus();
				this._calculate();
			} else {
				tx.setText("0.0");
				this.eval_variables_from_descuento(row, "0.0");
				tx.requestFocusInWindow();

			}
		} else {
			this.eval_variables_from_descuento(row, "0.0");
			tx.setText("0.0");
			tx.requestFocusInWindow();
		}
		if (error) {
			tx.requestFocusInWindow();
			tx.setSelectionStart(0);
			tx.setSelectionEnd(tx.getText().length());
		}
	}

	public void eval_variables_from_descuento(int row, String descuento) {
		String precio = "0.0";
		String cantidad = "0.0";
		try {
			precio = frame.getJTable().getValueAt(row, 5).toString();
			cantidad = frame.getJTable().getValueAt(row, 3).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.eval_variables(row, cantidad, precio, descuento);
	}

	public void eval_variables_from_cantidad(int row, String cantidad) {
		String precio = "0.0";
		String descuento = "0.0";
		try {
			precio = frame.getJTable().getValueAt(row, 5).toString();
			descuento = frame.getJTable().getValueAt(row, 6).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.eval_variables(row, cantidad, precio, descuento);
	}

	public void _eval_item_costo(JTextField tx, int row) {
		String _precio = tx.getText();

		_precio = _precio.replaceAll(",", "");
		if (_precio.compareTo("") == 0) {
			_precio = "0.0";
		}
		double prc = 0.0;
		boolean error = false;
		try {
			prc = new Double(_precio);
		} catch (Exception e) {
			error = true;
		}
		if (!error) {
			if (prc >= 0) {
				Convertidor Cv = new Convertidor();
				tx.setText(Cv.getMoney(prc, 2));
				frame.getJTable().changeSelection(row, 5, false, false);
				frame.getJTable().editCellAt(row, 5);
				frame.getJTable().transferFocus();
				this._calculate();
			} else {
				error("el precio debe ser mayor igual a cero");
			}
		} else {
			error("error en precio");
		}

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

	public void borrarRenglon(int row) {
		if (preguntar("Confirmar", "Elimina Renglon " + row + " de la tabla?")) {
			try {
				frame.getJTable().getCellEditor().stopCellEditing();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
			DefaultTableModel model = (DefaultTableModel) frame.getJTable()
					.getModel();
			model.removeRow(row);
			if (model.getRowCount() <= 0) {
				model.setRowCount(1);
				model.setValueAt(true, 0, 0);
				this.editCell(0, 1);
			}
			this._calculate();
			cambios = true;
		}
	}

	public void _delete_item_articulo(JTextField tx, int row) {
		String value = tx.getText();
		if (value.compareTo("") == 0) {
			this.borrarRenglon(row);
		}
	}

	private List<String> getInstruccionesGuardarEncabezadoRemito() {
		List<String> instrucciones = new ArrayList<String>();
		String instruccion = this.getInstruccionEncabezadoRemito();
		instrucciones.add(instruccion);
		return instrucciones;
	}

	private String getInstruccionEncabezadoRemito() {
		String q = "";
		// String idpedido=frame.get_txt_idpedido().getText();
		// String descripcion=frame.get_txt_pedido_descripcion().getText();
		String cliente = frame.get_txt_idcliente().getText();
		// String
		// cliente_descripcion=frame.get_txt_cliente_descripcion().getText();
		String idvendedor = frame.get_txt_idvendedor().getText();
		// String info=frame.get_txt_informacion().getText();
		// String iva=frame.get_txt_iva().getText().replace(",", "");
		String iva = "0.0";
		String importe = frame.get_txt_total().getText().replace(",", "");
		double _importe = 0.0;
		double _importe_siva = 0.0;
		double _iva = 0.0;
		try {
			_importe = new Double(importe);
			_iva = new Double(iva);
			_importe_siva = _importe - _iva;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String importe_s_iva = ""
				+ new Convertidor().roundDouble(_importe_siva, 2);
		String condicion = "  10";
		String fecha = frame.get_txt_fecha().getText();
		q = data.getInsertRemito(cliente, fecha, idvendedor, importe,
				importe_s_iva, iva, condicion);
		return q;
	}

	private List<String> getInstruccionesActualizarPunterosRemito() {
		List<String> instrucciones = new ArrayList<String>();
		String instruccion = data.getUpdateRM();
		instrucciones.add(instruccion);
		String idpedido = frame.get_txt_idpedido().getText();
		String iduser = this.getConstructor().getIduser();
		instruccion = data.getInstruccionAsociacionRemitoPedido(idpedido,
				iduser);
		instrucciones.add(instruccion);
		return instrucciones;
	}

	private List<String> getInstruccionesGuardarRemitoItems() {
		List<String> instrucciones = new ArrayList<String>();

		for (int i = 0; i < frame.getJTable().getRowCount(); i++) {

			String idarticulo = "";
			try {
				idarticulo = frame.getJTable().getValueAt(i, 1).toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
			if (idarticulo.compareTo("") != 0) {
				Boolean selected = (Boolean) frame.getJTable().getValueAt(i, 0);
				if (selected) {
					String instruccion = this.getInstruccionRemitoItem(i);
					instrucciones.add(instruccion);
				}

			}

		}

		return instrucciones;
	}

	private String getInstruccionRemitoItem(int i) {
		double aliciva = 1.21;
		String q = "";
		String idpedido = frame.get_txt_idpedido().getText();
		String item = "" + i;
		String idcliente = frame.get_txt_idcliente().getText();
		String idarticulo = (String) frame.getJTable().getValueAt(i, 1);
		String descripcion = (String) frame.getJTable().getValueAt(i, 2);
		String cantidad = "0.0";
		try {
			cantidad = frame.getJTable().getValueAt(i, 3).toString();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String costo = "0.0";
		try {
			costo = frame.getJTable().getValueAt(i, 4).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String importe = "0.0";
		try {
			importe = frame.getJTable().getValueAt(i, 5).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String total = "0.0";
		try {
			total = frame.getJTable().getValueAt(i, 7).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		descripcion = descripcion.replaceAll("'", "");
		total = total.replaceAll(",", "");
		importe = importe.replaceAll(",", "");
		costo = costo.replaceAll(",", "");
		cantidad = cantidad.replaceAll(",", "");
		Boolean selected = (Boolean) frame.getJTable().getValueAt(i, 0);
		double _precio = new Double(importe.replaceAll(",", ""));
		double _costo = new Double(costo.replaceAll(",", ""));
		double _cantidad = new Double(cantidad.replaceAll(",", ""));
		double _total = _precio * _cantidad;
		if (selected) {

			boolean ri = data.esResponsableInscripto(idcliente);
			if (ri) {
				_precio = _precio / aliciva;
				_total = _precio * _cantidad;
				q = this.data.getInsertRemitoItem(idarticulo, descripcion,
						_cantidad, _costo, _precio, _precio, _total, _total);
			} else {
				double _precio_c_iva = new Convertidor()
						.roundDouble(_precio, 2);
				_total = new Convertidor().roundDouble(_precio_c_iva
						* _cantidad, 2);
				q = this.data.getInsertRemitoItem(idarticulo, descripcion,
						_cantidad, _costo, _precio_c_iva, _precio, _total,
						_total);
			}

		}

		return q;
	}

	private String getInstruccionControlItem(int i) {
		String idvendedor = frame.get_txt_idvendedor().getText();
		String idarticulo = (String) frame.getJTable().getValueAt(i, 1);
		Object[][] results = data.getData(idarticulo);
		String _stock = "";
		String _actualizacion = "";
		if (results != null) {
			_stock = (String) results[0][3];
			_actualizacion = (String) results[0][5];
		}
		String q = "";
		String idpedido = frame.get_txt_idpedido().getText();
		String cantidad = "0.0";
		try {
			cantidad = frame.getJTable().getValueAt(i, 3).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String importe = "0.0";
		try {
			importe = frame.getJTable().getValueAt(i, 5).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		importe = importe.replaceAll(",", "");
		cantidad = cantidad.replaceAll(",", "");
		Boolean selected = (Boolean) frame.getJTable().getValueAt(i, 0);
		double _precio = new Double(importe.replaceAll(",", ""));
		double stock = new Double(_stock.replaceAll(",", ""));
		double _cantidad = new Double(cantidad.replaceAll(",", ""));
		double precio_sistema = data.getPrecioPublico(idarticulo);
		String idcliente = frame.get_txt_idcliente().getText();
		data.esResponsableInscripto(idcliente);
		String iduser = this.getConstructor().getIduser();
		String ip = data.getIp();
		if (selected) {
			q = this.data.getInsertControl(idpedido, idvendedor, idarticulo,
					_precio, precio_sistema, _cantidad, stock, _actualizacion,
					iduser, validacion, ip);
		}

		return q;
	}

	private List<String> getInstruccionesControl() {
		List<String> instrucciones = new ArrayList<String>();
		for (int i = 0; i < frame.getJTable().getRowCount(); i++) {
			String idarticulo = "";
			try {
				idarticulo = frame.getJTable().getValueAt(i, 1).toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
			if (idarticulo.compareTo("") != 0) {
				Boolean selected = (Boolean) frame.getJTable().getValueAt(i, 0);
				if (selected) {
					String instruccion = this.getInstruccionControlItem(i);
					instrucciones.add(instruccion);
				}

			}

		}
		return instrucciones;
	}

	public boolean generar_remito() {
		String idremito = frame.get_txt_remito().getText();
		boolean ok = this.eliminar_remito(idremito);
		if (ok) {
			ok = this._generar_remito();
		}
		return ok;
	}

	public boolean _generar_remito() {

		List<String> instrucciones = this
				.getInstruccionesGuardarEncabezadoRemito();
		List<String> instrucciones_puntero = this
				.getInstruccionesActualizarPunterosRemito();
		List<String> instrucciones_items = this
				.getInstruccionesGuardarRemitoItems();
		List<String> instrucciones_control = this.getInstruccionesControl();
		data.beginTransaction();
		data.clearBatch();
		for (int i = 0; i < instrucciones.size(); i++) {
			System.out.println(instrucciones.get(i));
			data.addBatch(instrucciones.get(i));
		}
		for (int i = 0; i < instrucciones_puntero.size(); i++) {
			System.out.println(instrucciones_puntero.get(i));
			data.addBatch(instrucciones_puntero.get(i));
		}
		for (int i = 0; i < instrucciones_items.size(); i++) {
			System.out.println(instrucciones_items.get(i));
			data.addBatch(instrucciones_items.get(i));
		}
		for (int i = 0; i < instrucciones_control.size(); i++) {
			System.out.println(instrucciones_control.get(i));
			data.addBatch(instrucciones_control.get(i));
		}
		boolean error = data.executeBatch();
		if (!error) {
			data.commitTransaction();
		} else {
			data.rollbackTransaction();
		}
		return !error;

	}

	public void uncheck_null() {
		int selections = 0;
		int items = 0;
		double suma_selections = 0.0;
		double suma_items = 0.0;
		int i = 0;
		while (i < frame.getJTable().getRowCount()) {
			boolean selected = false;
			try {
				selected = (Boolean) frame.getJTable().getValueAt(i, 0);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (selected) {
				String articulo = "";
				try {
					articulo = (String) frame.getJTable().getValueAt(i, 1);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (articulo == null) {
					try {
						frame.getJTable().setValueAt(false, i, 0);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {

					if (articulo.compareTo("") == 0) {
						try {
							frame.getJTable().setValueAt(false, i, 0);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}

			}
			i++;
		}

	}

	public boolean check_seleccion_del_pedido() {
		boolean ok = true;
		int selections = 0;
		int items = 0;
		double suma_selections = 0.0;
		double suma_items = 0.0;
		for (int i = 0; i < frame.getJTable().getRowCount(); i++) {
			boolean selected = false;
			try {
				selected = (Boolean) frame.getJTable().getValueAt(i, 0);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (selected) {
				String articulo = (String) frame.getJTable().getValueAt(i, 1);
				String total = (String) frame.getJTable().getValueAt(i, 7);
				if (total == null) {
					total = "0.0";
				}
				double _total = 0.0;
				try {
					_total = new Double(total.replace(",", ""));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (articulo != null) {
					if (articulo.compareTo("") != 0) {
						items++;
						suma_items += _total;
						if (selected) {
							selections++;
							suma_selections += _total;
						}
					}
				}
			}

		}
		if (items >= 1) {
			if (selections >= 1) {
				if (selections != items) {
					String mensaje = "Confirme Que la Eleccion de items es Correcta";
					String NEW_LINE = System.getProperty("line.separator");
					mensaje += NEW_LINE;
					mensaje += "Selecciono " + selections + " de " + items
							+ ". Diferencia $" + (suma_selections - suma_items);
					mensaje += NEW_LINE;
					mensaje += "Codigo:";
					ok = (this.confirmar(mensaje, 2));
				} else {
					ok = true;
				}
			} else {
				error("Debe Seleccionar Los items para el remito");
				ok = false;
			}

		} else {
			error("No existem items para el remito");
			ok = false;
		}

		return ok;
	}

	public boolean check_articulos_actualizados() {
		boolean ok = true;
		int selections = 0;
		int items = 0;
		double suma_selections = 0.0;
		double suma_items = 0.0;
		boolean vencido = false;
		int i = 0;
		while (i < frame.getJTable().getRowCount() & !vencido) {
			boolean selected = false;
			try {
				selected = (Boolean) frame.getJTable().getValueAt(i, 0);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (selected) {
				String articulo = "";
				try {
					articulo = (String) frame.getJTable().getValueAt(i, 1);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (articulo != null) {
					if (articulo.compareTo("") != 0) {
						if (articulo.compareTo("*") != 0) {
							vencido = this.check_vencimiento(articulo);
							if (vencido) {
								int sel = preguntar("Confirmar", "EL ARTICULO "
										+ articulo + " NO ESTA ACTUALIZADO!!",
										new String[] { "Continuar",
												"Voy a Verificar el Precio" },
										"Voy a Verificar el Precio");
								if (sel == 0) {
									vencido = false;
								}
							}
						}
					}

				}

			}
			i++;
		}
		ok = !vencido;
		return ok;
	}

	public boolean check_vencimiento(String idarticulo) {
		boolean vencido = true;
		Object[][] results = data.getData(idarticulo);
		// frame.get_txt_articulo().setText("");
		// frame.get_txt_articulo_descripcion().setText("");
		// frame.get_txt_articulo_stock().setText("");
		if (results != null) {
			if (results.length > 0) {
				Convertidor cv = new Convertidor();
				String _actualizacion = (String) results[0][5];
				if (_actualizacion.compareTo("") == 0) {

				} else {
					if (this.eval_venc(_actualizacion)) {
						vencido = false;
					} else {

					}
				}

			}
		}
		return vencido;
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

	public boolean check_articulos_asterisco() {
		boolean ok = true;
		int selections = 0;
		int items = 0;
		double suma_selections = 0.0;
		double suma_items = 0.0;
		boolean asterisco = false;
		int i = 0;
		while (i < frame.getJTable().getRowCount() & !asterisco) {
			boolean selected = false;
			try {
				selected = (Boolean) frame.getJTable().getValueAt(i, 0);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (selected) {
				String articulo = "";
				try {
					articulo = (String) frame.getJTable().getValueAt(i, 1);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (articulo != null) {
					asterisco = articulo.compareTo("*") == 0;
				}

			}
			i++;
		}
		ok = !asterisco;
		return ok;
	}

	public boolean check_precio(int row) {
		boolean selected = false;
		boolean ok = true;
		try {
			selected = (Boolean) frame.getJTable().getValueAt(row, 0);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (selected) {
			String idcliente = frame.get_txt_idcliente().getText();
			String idarticulo = "";
			try {
				idarticulo = frame.getJTable().getValueAt(row, 1).toString();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			String precio = "";
			try {
				precio = frame.getJTable().getValueAt(row, 5).toString();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			precio = precio.replaceAll(",", "");
			double prc = 0.0;

			try {
				prc = new Double(precio);
			} catch (Exception e) {
				ok = false;
				;
			}
			if (ok) {
				boolean publico = data.utilizaPrecioPublico(idcliente);
				double sys_prc = 0.0;
				if (publico) {
					sys_prc = data.getPrecioPublico(idarticulo);
				} else {
					sys_prc = data.getPrecioCosto(idarticulo);
				}

				if (sys_prc - prc > 0.5) {
					ok = false;
					error("Precio " + idarticulo + " $" + prc
							+ " Menor al sistema => $" + sys_prc + " ");
				} else {

				}
			}
		}

		return ok;
	}

	public boolean check_articulos_precios() {
		boolean ok = true;
		int selections = 0;
		int items = 0;
		double suma_selections = 0.0;
		double suma_items = 0.0;

		int i = 0;
		while (i < frame.getJTable().getRowCount() & ok) {
			boolean selected = false;
			try {
				selected = (Boolean) frame.getJTable().getValueAt(i, 0);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (selected) {
				String articulo = "";
				try {
					articulo = (String) frame.getJTable().getValueAt(i, 1);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (articulo != null) {
					if (articulo.compareTo("") != 0) {
						if (articulo.compareTo("*") != 0) {
							if (data.existeArticulo(articulo)) {
								ok = this.check_precio(i);

							}
						}
					}
				}

			}
			i++;
		}

		return ok;
	}

	public double check_global_precios() {
		double global = 0.0;
		boolean ok = true;
		int selections = 0;
		int items = 0;
		double suma_selections = 0.0;
		double suma_items = 0.0;

		int i = 0;
		while (i < frame.getJTable().getRowCount() & ok) {
			boolean selected = false;
			try {
				selected = (Boolean) frame.getJTable().getValueAt(i, 0);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String articulo = "";
			try {
				articulo = (String) frame.getJTable().getValueAt(i, 1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (articulo != null) {
				if (articulo.compareTo("") != 0) {
					if (articulo.compareTo("*") != 0) {
						global += this.check_global(i);

					}
				}
			}

			i++;
		}
		System.out.println("GLOBAL>" + global);
		return global;
	}

	public double getPrecioReal(String idarticulo, String clase) {
		double real = 0.0;
		Object[][] results = data.getArticulo(idarticulo);
		if (results != null) {
			if (results.length > 0) {
				String precio2 = (String) results[0][1];
				String costo = (String) results[0][2];
				real = new Double(precio2);
			}
		}
		return real;

	}

	public double check_global(int row) {
		double global = 0.0;
		double qty = 0.0;
		double prc = 0.0;

		String idcliente = frame.get_txt_idcliente().getText();
		String idarticulo = "";
		try {
			idarticulo = frame.getJTable().getValueAt(row, 1).toString();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		if (idarticulo.compareTo("*") != 0) {
			String precio = "";
			try {
				precio = frame.getJTable().getValueAt(row, 5).toString();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String cantidad = "";
			try {
				cantidad = frame.getJTable().getValueAt(row, 3).toString();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			precio = precio.replaceAll(",", "");
			cantidad = cantidad.replaceAll(",", "");
			boolean ok = true;
			try {
				prc = new Double(precio);
			} catch (Exception e) {
				ok = false;
				;
			}
			try {
				qty = new Double(cantidad);
			} catch (Exception e) {
				ok = false;
				;
			}
			if (ok) {
				boolean publico = data.utilizaPrecioPublico(idcliente);
				double sys_prc = 0.0;
				if (publico) {
					sys_prc = data.getPrecioPublico(idarticulo);
				} else {
					sys_prc = data.getPrecioCosto(idarticulo);
				}

				global = (prc - sys_prc) * qty;
			}
		}
		return global;
	}

	public boolean check_descuentos_del_pedido() {
		System.out.println("Check descuento de pedidos");
		boolean ok = true;
		int selections = 0;
		int items = 0;
		double suma_selections = 0.0;
		double suma_real = 0.0;
		for (int i = 0; i < frame.getJTable().getRowCount(); i++) {
			boolean selected = false;
			try {
				selected = (Boolean) frame.getJTable().getValueAt(i, 0);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			String articulo = (String) frame.getJTable().getValueAt(i, 1);
			String cantidad = (String) frame.getJTable().getValueAt(i, 3);
			String total = (String) frame.getJTable().getValueAt(i, 7);
			if (total == null) {
				total = "0.0";
			}
			double _total = 0.0;
			try {
				_total = new Double(total.replace(",", ""));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			double _cantidad = 0.0;
			try {
				if (cantidad != null) {
					_cantidad = new Double(cantidad.replace(",", ""));
				}

			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
			if (articulo != null) {
				if (articulo.compareTo("") != 0) {
					items++;

					if (selected) {
						selections++;
						if (articulo.compareTo("*") != 0) {
							double real = this.getPrecioReal(articulo, "");
							if (real >= 0) {
								suma_selections += _total;
								suma_real += real * _cantidad;
							}

						}
					}

				}
			}
		}
		if (items >= 1) {
			if (selections >= 1) {
				double dif = new Convertidor().roundDouble(suma_selections
						- suma_real, 2);
				if (dif > 0) {
					ok = true;
				} else {
					if (dif < 0) {

						// perdida
						double desc = ((suma_real - suma_selections) / suma_real) * 100;
						desc = new Convertidor().roundDouble(desc, 2);

						if (desc > 5) {
							boolean su = data.getIsSuperUser(this
									.getConstructor().getIduser());
							if (su) {
								String NEW_LINE = System
										.getProperty("line.separator");
								String msj = "Confirme la operacion de descuento del "
										+ desc + "% ";
								msj += NEW_LINE;
								msj += "Sistema   :$"
										+ new Convertidor().roundDouble(
												suma_real, 2);
								msj += NEW_LINE;
								msj += "Cotizacion:$"
										+ new Convertidor().roundDouble(
												suma_selections, 2);
								msj += NEW_LINE;

								msj += "Descuento   :$"
										+ new Convertidor().roundDouble(
												suma_real - suma_selections, 2);
								msj += NEW_LINE;
								msj += "Codigo:";
								su = this.confirmar(msj, 2);
							} else {
								String NEW_LINE = System
										.getProperty("line.separator");
								String msj = "Para hacer este descuento del "
										+ desc + "% debe ser autorizado ";
								msj += NEW_LINE;
								msj += "Sistema   :$"
										+ new Convertidor().roundDouble(
												suma_real, 2);
								msj += NEW_LINE;
								msj += "Cotizacion:$"
										+ new Convertidor().roundDouble(
												suma_selections, 2);
								msj += NEW_LINE;
								msj += "Descuento   :$"
										+ new Convertidor().roundDouble(
												suma_real - suma_selections, 2);
								msj += NEW_LINE;
								msj += "Codigo:";
								su = this.pedir_autorizacion(msj);
							}
							if (su) {
								ok = true;
							} else {
								ok = false;
							}
						} else {
							String NEW_LINE = System
									.getProperty("line.separator");
							String msj = "Confirme la operacion de descuento del "
									+ desc + "% ";
							msj += NEW_LINE;
							msj += "Sistema   :$"
									+ new Convertidor().roundDouble(suma_real,
											2);
							msj += NEW_LINE;
							msj += "Cotizacion:$"
									+ new Convertidor().roundDouble(
											suma_selections, 2);
							msj += NEW_LINE;
							msj += "Descuento   :$"
									+ new Convertidor().roundDouble(suma_real
											- suma_selections, 2);
							msj += NEW_LINE;
							msj += "Codigo:";
							ok = this.confirmar(msj, 2);
						}

					} else {
						ok = true;
					}
				}

			} else {
				error("Debe Seleccionar Los items para el remito");
				ok = false;
			}

		} else {
			error("No existem items para el remito");
			ok = false;
		}

		return ok;
	}

	private List<String> getInstruccionesEliminarRemito(String remito) {
		// desaparecer el remito del sistema
		// cuando se paso mal el remito..
		// se lo marca como anulado. y se lo desvincula del pedido para q se
		// pueda generar otro
		List<String> instrucciones = new ArrayList<String>();

		String _instruccion = data.getMarcarAnuladoRemito(remito);
		instrucciones.add(_instruccion);
		_instruccion = data.getDeleteRemitoPedidoQuery(remito);
		instrucciones.add(_instruccion);
		return instrucciones;
	}

	public boolean eliminar_remito(String remito) {
		boolean ok = true;
		if (remito.compareTo("") != 0) {
			boolean link = false;
			Object[][] results = data.tieneComprobanteBetaAsociado(remito);
			if (results != null) {
				if (results.length > 0) {
					link = true;
					String tc = (String) results[0][0];
					String idcomprobante = (String) results[0][1];
					error("No puede eliminar este remito porque esta asociado al comprobante "
							+ tc + "-" + idcomprobante);
				}
			}
			if (!link) {
				results = data.tieneComprobanteAlfaAsociado(remito);
				if (results != null) {
					if (results.length > 0) {
						link = true;
						String tc = (String) results[0][0];
						String idcomprobante = (String) results[0][1];
						error("No puede eliminar este remito porque esta asociado al comprobante "
								+ tc + "-" + idcomprobante);

					}
				}
			}
			if (!link) {
				List<String> batchlist = this
						.getInstruccionesEliminarRemito(remito);
				data.beginTransaction();
				data.clearBatch();
				for (int i = 0; i < batchlist.size(); i++) {
					data.addBatch(batchlist.get(i));
				}
				boolean error = data.executeBatch();

				if (!error) {
					data.commitTransaction();
					ok = true;

				} else {
					data.rollbackTransaction();
					ok = false;
				}

			}

		}
		return ok;
	}
}
