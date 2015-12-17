package aplicacion.compras.carga.items.logic;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import aplicacion.herramientas.java.table.*;
import aplicacion.herramientas.java.image.logic.ScrollableImage;
import aplicacion.herramientas.java.sortableselector.*;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Logic;
import aplicacion.modelo.logic.Data;
import aplicacion.proveedor.archivo.interfaces._Parametros;
import aplicacion.herramientas.java.*;

import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

import uk.co.mmscomputing.device.scanner.Scanner;
import uk.co.mmscomputing.device.scanner.ScannerIOException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import java.awt.*;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.*;
import java.util.*;

import aplicacion.alfa.comprobantes.mv_asientos.objetos.Asiento;
import aplicacion.alfa.comprobantes.mv_asientos.objetos.Renglon;
import aplicacion.compras.carga.items.logic._ScannerListener;
import aplicacion.compras.carga.items.constructor._Constructor;
import aplicacion.compras.carga.items.interfaces._Interface;
import aplicacion.compras.carga.items.gui._Frame;
import aplicacion.compras.carga.items.gui._Etiquetas;
import aplicacion.compras.carga.items.gui._Actualizar;
import aplicacion.compras.carga.items.logic._Data;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author Agustin Wisky
 * @company Wismi S.A.
 * @since 10-10-2009
 */
public class _Logic extends Logic {
	private _Frame frame = null;
	private _Etiquetas etiquetas = null;
	private _Actualizar actualizar = null;
	private _Data data = null;
	public String equivalencia = "";
	private aplicacion.herramientas.java.buscadores.Articulo bArticulo = null;
	private aplicacion.herramientas.java.visualizadores.Articulo vArticulo = null;
	private aplicacion.herramientas.java.buscadores.Proveedor bProveedor = null;
	private aplicacion.herramientas.java.visualizadores.Proveedor vProveedor = null;
	private int permite_articulos = 0;
	private int hoja_actual = 1;
	private int hojas = 1;

	String server = "";
	private int requiere_iva10 = 0;
	private int requiere_iva21 = 0;
	private int requiere_iva27 = 0;
	private int requiere_percepcion_ingresos_brutos = 0;
	private int requiere_percepcion_iva = 0;

	private List<Object[]> cantidadArticulos=new ArrayList<Object[]>();
	private int requiere_percepcion_ganancias = 0;
	private int requiere_neto_no_grabado = 0;
	private int requiere_rg3337 = 0;
	private int requiere_impuestos_internos = 0;

	private Scanner scanner;
	private _ScannerListener scannerlistener;
	private double alic_iva1 = 0.21;
	private double alic_iva2 = 0.105;
	private double alic_iva3 = 0.27;
	private double alic_iibb = 0.02;
	private double alic_perc_iva = 0.0;
	private double alic_impuestos_internos = 0.0;
	private double alic_rg3337 = 0.03;
	private double base_iibb = 50.0;
	private List<BufferedImage> images = null;
	private aplicacion.inventario.articulo.constructor._Constructor articulo = null;
	// private String server="E:/fotos/";
	private aplicacion.herramientas.java.visualselector.constructor._Constructor vSelector = null;

	public void buscarProveedor() {
		JTextField proveedor = frame.get_txt_idproveedor();
		this.BuscarProveedor(proveedor);
	}

	public void setFrame(JFrame frame) {
		this.frame = (_Frame) frame;
		super.setFrame(frame);
	}

	public void setData(Data data) {
		this.data = (_Data) data;
		this.server = data.getPrimitiveMessage("fotostorage");
		super.setData(this.data);
	}

	private int getZoomInc() {
		int x = 0;
		try {
			x = new Integer(frame.get_txt_zoom().getText());
		} catch (Exception ex) {

		}
		return x;
	}

	public boolean check_fecha(String fecha){
		String q="select month('"+fecha+"')";
		Object[][] results=data.getResults(q);
		boolean ok=false;
		if (results!=null){
			if (results.length>0){
				ok=true;
			}
		}
			return ok;
	}
	private int getInc() {
		int x = 0;
		try {
			x = new Integer(frame.get_txt_zoom().getText());
		} catch (Exception ex) {

		}
		return x;
	}

	public void _anterior() {
		System.out.println("_anterior");
		if (this.hoja_actual > 1) {
			this.hoja_actual--;
			frame.get_txt_hoja_actual().setText("" + hoja_actual);

			this.mostrar_foto(this.hoja_actual);
		}
	}

	public void _primero() {

		if (this.images.size() > 0) {
			this.mostrar_foto(1);
			this.hoja_actual = 1;
			frame.get_txt_hoja_actual().setText("" + hoja_actual);
		}

	}

	public void _ultimo() {

		if (this.images.size() > 0) {
			this.mostrar_foto(this.images.size());
			this.hoja_actual = images.size();
			frame.get_txt_hoja_actual().setText("" + hoja_actual);

		}

	}

	public void mostrar_foto(int i) {
		if (i > 0 & i <= images.size()) {
			BufferedImage bi = images.get(i - 1);
			if (bi != null) {
				frame.getCanvas().setImage(bi);
				frame.getCanvas().zoom(0);
				frame.getCanvas().setVisible(true);
			}

		}

	}

	public void eliminar_foto() {
		if (hoja_actual > 0) {
			this.eliminar_foto(this.hoja_actual);
		}

	}

	public void eliminar_foto(int i) {
		if (i > 0 & i <= images.size()) {
			if (confirmar("Confirme la eliminacion de la imagen :", 2)) {
				images.remove(i - 1);
				if (images.size() > 0) {
					this.mostrar_foto(1);
					this.hoja_actual = 1;
					this.hojas = images.size();
				} else {
					this.hoja_actual = 0;
					this.hojas = 0;
					frame.getCanvas().setImage(null);
					frame.getCanvas().zoom(0);
					frame.getCanvas().setVisible(false);
				}
			}
			frame.get_txt_hoja_actual().setText("" + hoja_actual);
			frame.get_txt_hoja_total().setText("" + hojas);
		} else {
			error("no hay imagenes para eliminar");
		}
	}

	public void _siguiente() {
		if (this.hoja_actual < this.hojas) {
			this.hoja_actual++;
			frame.get_txt_hoja_actual().setText("" + hoja_actual);
			this.mostrar_foto(this.hoja_actual);
		}

	}

	private void create_table(Object[][] results) {
		_Constructor constructor = (_Constructor) this.getConstructor();
		CustomTable table = new CustomTable();
		table.setSortable(false);
		Column col = null;
		CellEditor pce = null;
		TableItemColorCellRenderer renderer=new TableItemColorCellRenderer();
		renderer.setLogic(this);
		col = new Column();
		col.setName("");
		col.setWidth(30);
		col.setEditable(true);
		col.setClass(Boolean.class);
		table.addColumn(col);

		col = new Column();
		col.setName("idarticulo");
		col.setWidth(100);
		col.setEditable(true);
		col.setAligment(JTextField.RIGHT);
		pce = new CellEditor();
		pce.addKeyListener(constructor.getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_items_idarticulo);
		pce.setTipo(String.class);

		col.setCellEditor(pce.getCellEditor());
		col.setCellRenderer(renderer);
		table.addColumn(col);

		col = new Column();
		col.setName("descripcion");
		col.setWidth(280);
		col.setEditable(true);
		col.setAligment(JTextField.LEFT);
		pce = new CellEditor();
		pce.addKeyListener(constructor.getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_items_descripcion);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());
		col.setCellRenderer(renderer);
		table.addColumn(col);

		col = new Column();
		col.setName("cant");
		col.setWidth(46);
		col.setEditable(true);
		col.setAligment(JTextField.RIGHT);
		pce = new CellEditor();
		pce.addKeyListener(constructor.getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_items_cantidad);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());
		col.setCellRenderer(renderer);
		table.addColumn(col);

		col = new Column();
		col.setName("precio");
		col.setWidth(70);
		col.setEditable(true);
		col.setAligment(JTextField.RIGHT);
		pce = new CellEditor();
		pce.addKeyListener(constructor.getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_items_precio);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());
		col.setCellRenderer(renderer);
		table.addColumn(col);

		col = new Column();
		col.setName("Desc");
		col.setWidth(50);
		col.setEditable(true);
		col.setAligment(JTextField.RIGHT);
		pce = new CellEditor();
		pce.addKeyListener(constructor.getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_items_descuento);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());
		col.setCellRenderer(renderer);
		table.addColumn(col);

		col = new Column();
		col.setName("Total");
		col.setWidth(80);
		col.setEditable(true);
		col.setAligment(JTextField.RIGHT);
		pce = new CellEditor();
		pce.addKeyListener(constructor.getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_items_total);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());
		col.setCellRenderer(renderer);
		table.addColumn(col);

		col = new Column();
		col.setName("tc");
		col.setWidth(60);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		col.setCellRenderer(renderer);
		table.addColumn(col);

		col = new Column();
		col.setName("idcomprobante");
		col.setWidth(120);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		col.setCellRenderer(renderer);
		table.addColumn(col);

		table.setData(results);
		table.setName(_Interface._table_items);
		table.addKeyListener(this._constructor.getKeyListener());
		table.addMouseListener(this._constructor.getMouseListener());
		Font fuente = new Font("Dialog", Font.BOLD, 10);
		table.setHeaderFont(fuente);
		table.setFont(fuente);
		table.build();
		table.fillData();

		JTable _table = table.getTable();
		_table.setColumnSelectionAllowed(false);
		frame.setJTable(_table);
	}

	public double getCantidadPedida(String idarticulo){
		double q=data.getPedido(idarticulo);
		q+=data.getPedidoEspecial(idarticulo);
		return q;
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

	private void create_table_actualizar(Object[][] results) {
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
		// cbce.setName(_Interface._table_etiquetas_seleccion);
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
		col.setName("precio");
		col.setWidth(80);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);

		table.setData(results);
		table.setName(_Interface._table_actualizar);
		table.addKeyListener(this._constructor.getKeyListener());
		Font fuente = new Font("Dialog", Font.BOLD, 10);
		table.setHeaderFont(fuente);
		table.setFont(fuente);
		table.build();
		table.fillData();

		JTable _table = table.getTable();

		actualizar.setJTable(_table);
	}

	public void seleccionar_etiquetas(boolean b) {
		double units = 0.0;
		for (int i = 0; i < etiquetas.getJTable().getRowCount(); i++) {
			String cantidad = "";
			double _cantidad = 0.0;
			try {
				etiquetas.getJTable().setValueAt(b, i, 0);
				cantidad = etiquetas.getJTable().getValueAt(i, 3).toString();
				cantidad = cantidad.replace(",", "");
				_cantidad = new Double(cantidad);

			} catch (Exception e) {

			}
			if (b & _cantidad > 0) {
				units += _cantidad;
			}
		}
		etiquetas.get_txt_unidades().setText("" + units);
	}

	private void crear_empty_etiquetas() {
		Object[][] results = new Object[][] { { false, "", "", "" } };
		this.create_table_etiquetas(results);
	}

	private void crear_empty_actualizar() {
		Object[][] results = new Object[][] { { false, "", "", "" } };
		this.create_table_actualizar(results);
	}

	public void imprimir_etiquetas() {

		LinkedList l = new LinkedList();
		try {
			etiquetas.getJTable().clearSelection();
			etiquetas.getJTable().getCellEditor().stopCellEditing();
		} catch (Exception e) {

		}
		etiquetas.getJTable().setEnabled(false);
		int etqs = 0;
		for (int i = 0; i < etiquetas.getJTable().getRowCount(); i++) {
			boolean b = false;
			try {
				b = (Boolean) etiquetas.getJTable().getValueAt(i, 0);
			} catch (Exception e) {

			}
			if (b)
				etqs++;
		}
		if (etqs > 0) {
			if (confirmar("Confirme Para Imprimir Etiquetas", 2)) {
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
						idarticulo = etiquetas.getJTable().getValueAt(i, 1)
								.toString();
						descripcion = etiquetas.getJTable().getValueAt(i, 2)
								.toString();
						cant = etiquetas.getJTable().getValueAt(i, 3)
								.toString();
						_cant = new Double(cant);
					} catch (Exception e) {

					}
					if (b & _cant >= 1) {
						String q = "insert into b_etiquetas (fecha,idarticulo,descripcion,cantidad) ";
						q += "values (getdate(),'" + idarticulo + "','"
								+ descripcion + "'," + _cant + ")";
						data.addBatch(q);
					}

				}
				data.executeBatch();
				data.commitTransaction();
				etiquetas.setVisible(false);
				etiquetas.dispose();
			}

		} else {
			aviso("No hay etiquetas para imprimir");
			if (preguntar("Etiquetas", "Cierra Ventana de Etiquetas?")) {
				etiquetas.setVisible(false);
				etiquetas.dispose();
			}
		}

	}

	public void Actualizar(String cuenta, String tc, String idcomprobante) {
		frame.getJTable().setEnabled(false);

		Object[][] results = null;
		if (this.getTipoTC().compareTo("FCN") != 0) {
			results = data.getArticulosPrecios(cuenta, tc, idcomprobante);
		} else {
			results = data.getArticulosPreciosBeta(cuenta, tc, idcomprobante);
		}

		if (results != null) {
			if (results.length > 0) {
				_Constructor constructor = (_Constructor) this.getConstructor();
				if (this.actualizar != null) {
					actualizar.setVisible(false);
					actualizar.dispose();
				}
				this.actualizar = new _Actualizar();
				this.actualizar.get_btn_actualizar_articulos()
						.setActionCommand(_Interface._btn_actualizar);
				this.actualizar.get_btn_actualizar_articulos()
						.addActionListener(constructor.getActionListener());
				this.crear_empty_actualizar();
				this.centrar_frame(this.actualizar);
				this.actualizar.setVisible(true);
				this.actualizar.requestFocus();
				this.actualizar.requestFocusInWindow();
				Convertidor C = new Convertidor();
				for (int i = 0; i < results.length; i++) {
					String idarticulo = "";
					String descripcion = "";
					String precio = "";
					double _precio = 0.0;
					try {
						idarticulo = results[i][0].toString();
						descripcion = results[i][1].toString();
						precio = results[i][2].toString();
						_precio = new Double(precio.replace(",", ""));

					} catch (Exception e) {

					}
					if (_precio > 0) {
						DefaultTableModel model = (DefaultTableModel) actualizar
								.getJTable().getModel();
						int row = model.getRowCount() - 1;
						boolean ok = this.check_proveedor(idarticulo, cuenta);
						actualizar.getJTable().setValueAt(ok, row, 0);
						actualizar.getJTable().setValueAt(idarticulo, row, 1);
						actualizar.getJTable().setValueAt(descripcion, row, 2);
						actualizar.getJTable().setValueAt(
								C.getMoney(_precio, 2), row, 3);

						model.setRowCount(model.getRowCount() + 1);

					}
				}
			}
		}
	}

	public String getInstruccionRegistro(String iduser, String idarticulo,
			String precio5, String ip, String validacion, String idoperacion) {
		String instruccion = data.getRegistrarCambio(iduser, ip, idarticulo,
				precio5, validacion, idoperacion);
		return instruccion;
	}

	public boolean check_proveedor(String idarticulo, String idproveedor) {
		boolean ok = false;
		String q = "select idarticulo from v_ma_articulos ";
		q += "where idarticulo like '" + idarticulo
				+ "' and isnull(cuenta_actualizacion,cuentaproveedor) like '"
				+ idproveedor + "'";
		Object[][] results = data.getResults(q);
		if (results != null) {
			if (results.length > 0) {
				ok = true;
			}
		}
		return ok;
	}

	public void _actualizar() {
		int qty = 0;

		String validacion = this.validar_usuario();
		List<String> instrucciones = new ArrayList<String>();
		String iduser = this.getConstructor().getIduser();
		String ip = data.getIp();
		int idoperacion = data.getProximoOperacion();
		if (validacion.compareTo("") != 0) {

			if (actualizar != null) {
				if (actualizar.getJTable() != null) {
					for (int i = 0; i < actualizar.getJTable().getRowCount(); i++) {
						boolean selected = false;
						try {
							selected = (Boolean) actualizar.getJTable()
									.getValueAt(i, 0);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							// e1.printStackTrace();
						}
						if (selected) {
							String idarticulo = "";
							String precio = "";
							String q = "";
							double _precio = 0.0;
							try {
								idarticulo = (String) actualizar.getJTable()
										.getValueAt(i, 1);
								precio = (String) actualizar.getJTable()
										.getValueAt(i, 3);
								_precio = new Double(precio.replaceAll(",", ""));

							} catch (Exception e) {
								e.printStackTrace();
							}
							if (_precio > 0) {
								qty++;
								q = data.getUpdateArticulo(idarticulo, ""
										+ _precio, iduser);
								System.out.println("UPDATE>" + q);
								instrucciones.add(q);
								q = this.getInstruccionRegistro(iduser,
										idarticulo, precio, ip, validacion, ""
												+ idoperacion);
							}
						}

					}
				}
			}
			if (instrucciones!=null){
				if (instrucciones.size() > 0) {
					data.beginTransaction();
					for (int i = 0; i < instrucciones.size(); i++) {
						data.addBatch(instrucciones.get(i));
					}
					data.addBatch(data.getUpdateOperacion());
					boolean error = data.executeBatch();
					if (!error) {
						data.commitTransaction();
						aviso("Se actualizo correctamente");
						actualizar.setVisible(false);
						actualizar.dispose();
						if (this.etiquetas != null) {
							this.etiquetas.toFront();
						}
					} else {
						data.rollbackTransaction();
						error("Hubo errores al actualizar");
					}

				} else {
					actualizar.setVisible(false);
					actualizar.dispose();
				}
			}
			

		} else {
			error("OPERACION CANCELADA");
		}
	}

	public void cargar_articulos(String cuenta, String tc, String idcomprobante) {
		Object[][] results = null;
		if (this.getTipoTC().compareTo("FCN") != 0) {
			results = data.getArticulos(cuenta, tc, idcomprobante);
		} else {
			results = data.getArticulosBeta(cuenta, tc, idcomprobante);
		}

		if (results != null) {
			if (results.length > 0) {
				this.crear_tabla_items();
				for (int i = 0; i < results.length; i++) {
					String idarticulo = (String) results[i][0];
					String descripcion = (String) results[i][1];
					String cantidad = (String) results[i][2];
					String precio = (String) results[i][3];
					String total = (String) results[i][4];
					String desc = (String) results[i][5];
					String _tc = (String) results[i][6];
					String _idcomprobante = (String) results[i][7];

					DefaultTableModel model = (DefaultTableModel) frame
							.getJTable().getModel();
					int row = model.getRowCount() - 1;
					frame.getJTable().setValueAt(true, row, 0);
					frame.getJTable().setValueAt(idarticulo, row, 1);
					frame.getJTable().setValueAt(descripcion, row, 2);
					frame.getJTable().setValueAt(cantidad, row, 3);
					frame.getJTable().setValueAt(precio, row, 4);
					frame.getJTable().setValueAt(desc, row, 5);
					frame.getJTable().setValueAt(total, row, 6);
					frame.getJTable().setValueAt(_tc, row, 7);
					frame.getJTable().setValueAt(_idcomprobante, row, 8);
					model.setRowCount(model.getRowCount() + 1);

				}

			}
		}
	}

	public void scan() {

		aviso("RECUERDE ESCANEAR LA IMAGEN CON RESOLUCION DE 150 DPI");
		boolean error = false;
		try {
			String[] lists = null;
			try {
				lists = scanner.getDeviceNames();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				error = true;
			}
			if (!error) {
				boolean twain = false;
				int i = 0;
				while (i < lists.length) {
					System.out.println("SCANNER>" + lists[i]);
					if (lists[i].toLowerCase().contains("wia")) {
						twain = true;
						try {
							scanner.select(lists[i]);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							error = true;
						}

					}
					i++;
				}
				if (!error) {
					scanner.acquire();
				}

			}

		} catch (ScannerIOException se) {
			error = true;

			// displayError("Escaner no disponible", se.getLocalizedMessage(),
			// se.getMessage(), se);
			se.printStackTrace();
		}
		if (error) {
			error("Escaner no disponible");
		}
	}

	public void Etiquetar(String cuenta, String tc, String idcomprobante) {
		frame.getJTable().setEnabled(false);
		Object[][] results = null;
		if (tc.compareTo("FCN") != 0) {
			results = data.getArticulos(cuenta, tc, idcomprobante);
		} else {
			results = data.getArticulosBeta(cuenta, tc, idcomprobante);
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
				this.centrar_frame(this.etiquetas);
				this.etiquetas.setVisible(true);
				this.etiquetas.requestFocus();
				this.etiquetas.requestFocusInWindow();

				this.crear_empty_etiquetas();
				int units = 0;
				for (int i = 0; i < results.length; i++) {
					String idarticulo = "";
					String descripcion = "";
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
				if (actualizar == null) {
					etiquetas.toFront();
				}
			}
		}

		frame.getJTable().setEnabled(true);
	}

	public void calcular_etiquetas() {
		double units = 0.0;
		for (int i = 0; i < etiquetas.getJTable().getRowCount(); i++) {
			boolean b = false;
			try {
				b = (Boolean) etiquetas.getJTable().getValueAt(i, 0);
			} catch (Exception e) {

			}

			String cant = "";
			double _cant = 0.0;
			try {
				cant = etiquetas.getJTable().getValueAt(i, 3).toString();
				_cant = new Double(cant);
			} catch (Exception e) {

			}
			if (b & _cant > 0) {
				units += _cant;
			}
		}
		etiquetas.get_txt_unidades().setText("" + units);
	}

	public void seleccion_etiqueta(JCheckBox chk, int row) {
		double units = 0.0;
		for (int i = 0; i < etiquetas.getJTable().getRowCount(); i++) {
			boolean b = false;
			try {
				b = (Boolean) etiquetas.getJTable().getValueAt(i, 0);
			} catch (Exception e) {

			}
			String cant = "";
			double _cant = 0.0;
			try {
				cant = etiquetas.getJTable().getValueAt(i, 3).toString();
				_cant = new Double(cant);
			} catch (Exception e) {

			}
			if (i == row) {
				if (chk.isSelected() & _cant > 0) {
					units += _cant;
				}
			} else {
				if (b & _cant > 0) {
					units += _cant;
				}
			}

		}
		Convertidor C = new Convertidor();
		etiquetas.get_txt_unidades().setText(C.getMoney(units, 2));
	}

	public void update_prefijo(String linea) {
		Object[][] results = data.getPrefijo(frame
				.get_txt_idproveedor_articulos().getText(), linea);
		if (results != null) {
			if (results.length > 0) {
				String prefijo = (String) results[0][0];
				frame.get_txt_prefijo().setText(prefijo);
			}
		}

	}

	public void _darken() {
		frame.getCanvas().lumix_rem(1);

	}

	public void _brighten() {
		frame.getCanvas().lumix_add(1);

	}

	private boolean _evaluar_codigo_proveedor(String idproveedor) {
		boolean ok = false;
		if (idproveedor.startsWith("21101")) {
			Object[][] results = data.getProveedor(idproveedor);
			if (results != null) {
				if (results.length > 0) {
					ok = true;
				}
			}
		}
		return ok;
	}

	public boolean check_cpte(String idcomprobante) {
		boolean ok = false;
		String tc = this.getTipoTC();
		String cuenta = frame.get_txt_idproveedor().getText();

		Object[][] results = null;
		if (this.getTipoTC().compareTo("FCN") != 0) {
			results = data.getCpte(cuenta, tc, idcomprobante);
		} else {
			results = data.getCpteBeta(cuenta, tc, idcomprobante);
		}

		if (results != null) {
			if (results.length > 0) {
				ok = true;
			}
		}
		return ok;
	}

	public void BuscarProveedor(JTextField ext) {
		if (bProveedor == null) {
			bProveedor = new aplicacion.herramientas.java.buscadores.Proveedor(
					this.getConstructor());
		}

		bProveedor.Buscar(ext);
	}

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
	
	public boolean existenFotos(String idproveedor, String tc,
			String idcomprobante){
		boolean existe=false;
		String q = "SELECT secuencia FROM facturas  where idproveedor like '"
			+ idproveedor + "' and tc like '" + tc
			+ "' and idcomprobante like '" + idcomprobante+"' ";
		System.out.println(q);
		existe=data.getConnector("MySQL").hasResults(q);
		return existe;
	}

	public BufferedImage getImages(String idproveedor, String tc,
			String idcomprobante, int secuencia) {
		BufferedImage _image = null;
		boolean ok = false;
		try {
			Statement stmt = data.getConnector("MySQL").createStatement();
			String q = "SELECT imagen FROM facturas  where idproveedor like '"
					+ idproveedor + "' and tc like '" + tc
					+ "' and idcomprobante like '" + idcomprobante
					+ "' and secuencia = " + secuencia;
			System.out.println(q);
			ResultSet resultSet = stmt.executeQuery(q);

			if (resultSet.next()) {
				ok = true;
				Blob image = resultSet.getBlob(1);
				InputStream input = image.getBinaryStream();
				_image = javax.imageio.ImageIO.read(input);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return _image;
	}

	public BufferedImage loadImage(String filename) {
		BufferedImage img = null;

		try {
			img = ImageIO.read(new File(filename));
		} catch (IOException e) {
		}
		return img;
	}

	public boolean cargar_fotos() {
		String idproveedor = frame.get_txt_idproveedor().getText();
		String sucursal = frame.get_txt_sucursal().getText();
		String tc = this.getTipoTC();
		String numero = frame.get_txt_numero().getText();
		String letra = frame.get_list_letra().getSelectedItem().toString();
		String idcomprobante = frame.get_txt_idcomprobante().getText();
		boolean ok = this.getImages(idproveedor, tc, idcomprobante);
		return ok;
	}

	public boolean getImages(String idproveedor, String tc, String idcomprobante) {
		BufferedImage _image = null;
		boolean ok = false;
		try {
			Statement stmt = data.getConnector("MySQL").createStatement();
			String q = "SELECT imagen FROM facturas  where idproveedor like '"
					+ idproveedor + "' and tc like '" + tc
					+ "' and idcomprobante like '" + idcomprobante + "'";
			System.out.println(q);
			ResultSet resultSet = stmt.executeQuery(q);

			while (resultSet.next()) {
				ok = true;
				Blob image = resultSet.getBlob(1);
				InputStream input = image.getBinaryStream();
				_image = javax.imageio.ImageIO.read(input);
				if (_image != null) {
					this.addFoto(_image);
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ok;
	}

	public void addFoto(BufferedImage bi) {
		images.add(bi);
	}

	public void addFotoAndShow(BufferedImage bi) {
		images.add(bi);
		this.hoja_actual = images.size();
		this.hojas = images.size();
		frame.get_txt_hoja_actual().setText("" + hoja_actual);
		frame.get_txt_hoja_total().setText("" + hojas);
		this.mostrar_foto(images.size());
	}

	public void recargar(){
		String idproveedor=frame.get_txt_idproveedor().getText();
		boolean ok=_evaluar_codigo_proveedor(idproveedor);
		if (ok){
			this.cargarproveedor(idproveedor);
		}else{
			this._evaluar_proveedor(frame.get_txt_idproveedor(), false);
		}
	}
	
	public void addFoto(String filex) {
		
		File fx = new File(filex);
		if (fx.exists()) {
			BufferedImage bi = this.loadImage(fx.getAbsolutePath());
			if (bi != null) {

				images.add(bi);
				this.hoja_actual = images.size();
				this.hojas = images.size();
				frame.get_txt_hoja_actual().setText("" + hoja_actual);
				frame.get_txt_hoja_total().setText("" + hojas);
				this.mostrar_foto(this.hoja_actual);
			}

		}

	}

	public void exit_command() {
		System.out.println("Custom dispose carga item");
		if (vArticulo != null) {
			this.vArticulo.dispose();
			this.vArticulo = null;
		}
		if (this.vComprobantes != null) {
			this.vComprobantes.dispose();
			this.vComprobantes = null;
		}
		if (this.vEquivalencia != null) {
			this.vEquivalencia.dispose();
			this.vEquivalencia = null;
		}
		if (this.vProveedor != null) {
			this.vProveedor.dispose();
			this.vProveedor = null;
		}
		if (this.vSelector != null) {
			this.vSelector.dispose();
			this.vSelector = null;
		}
		if (this.articulo != null) {
			articulo.dispose();
			articulo = null;
		}
		if (this.actualizar != null) {
			this.actualizar.dispose();
			this.actualizar = null;
		}
		if (this.etiquetas != null) {
			this.etiquetas.dispose();
			this.etiquetas = null;
		}
		if (this.bFecha != null) {
			this.bFecha.dispose();
			this.bFecha = null;
		}
		if (this.bArticulo != null) {
			this.bArticulo.dispose();
			this.bArticulo = null;
		}
		if (this.Catalogo != null) {
			this.Catalogo.dispose();
			this.Catalogo = null;
		}

		frame.getCanvas().setVisible(false);
		frame.getCanvas().resetVars();
		frame.getCanvas().dispose();
		System.gc();
		System.gc();
		System.gc();
		this.bProveedor = null;
		this.bFecha = null;

		super.exit_command();
	}

	public void cargarFoto(String idcomprobante, int secuencia) {
		String tc = this.getTipoTC();
		String idproveedor = frame.get_txt_idproveedor().getText();
		BufferedImage img = this.getImages(idproveedor, tc, idcomprobante,
				(secuencia - 1));

		frame.getCanvas().setVisible(true);
		if (img != null) {

			System.out.println("Hay foto almacenada");
			frame.getCanvas().setImage(img);
			frame.getCanvas().zoom(0);
			frame.getCanvas().setVisible(true);

		} else {
			System.out.println("No Hay foto almacenada");
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
			}
			this._calculate();
		}
	}

	public void cargarTipoTc() {
		frame.get_list_tc().removeAllItems();
		frame.get_list_tc().addItem("Factura");
		frame.get_list_tc().addItem("Nota de Credito");
		frame.get_list_tc().addItem("Remito");
		if (data.getIsSuperUser(this.getConstructor().getIduser())) {
			frame.get_list_tc().addItem("FCN");
		}

	}

	public void getToday() {
		String fecha = data.getSystemDate();
		frame.get_txt_fecha_carga().setText(fecha);
	}

	public void cargarLetras() {
		frame.get_list_letra().removeItemListener(this.getConstructor().getItemListener());
		frame.get_list_letra().removeAllItems();

		frame.get_list_letra().addItem("A");
		frame.get_list_letra().addItem("B");
		frame.get_list_letra().addItem("C");
		frame.get_list_letra().addItem("M");
		frame.get_list_letra().addItem("X");
		frame.get_list_letra().addItemListener(this.getConstructor().getItemListener());
	}

	public String getTipoTC() {

		String tc = "";
		if (frame.get_list_tc().getSelectedIndex() == 0) {
			tc = "FCC";

		}

		if (frame.get_list_tc().getSelectedIndex() == 1) {
			tc = "NCC";

		}

		if (frame.get_list_tc().getSelectedIndex() == 2) {
			tc = "RMC";

		}
		if (frame.get_list_tc().getSelectedIndex() == 3) {
			tc = "FCN";

		}
		return tc;
	}

	public boolean get_impuestos_automaticos() {
		String subtotal = frame.get_txt_subtotal_calculado().getText();

		boolean ok = this._evaluate_subtotal_auto(subtotal, 0);
		return ok;
	}

	public void guardar() {
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
			String fecha=frame.get_txt_fecha().getText();
			if (this.evaluarFecha(fecha)){
				if (this.check_fecha(fecha)){
					this.guardar("GUARDAR", iduser);	
				}else{
					error("Verifique la fecha de factura ");	
				}
					
			}else{
				error("Verifique la fecha de factura");
			}
			
		} else {
			error("OPERACION CANCELADA");
		}
	}

	public void guardar(String operacion, String iduser) {
		boolean nuevo=true;
		if (Vendedor==null){
			this.initialize_Vendedor();
		}
		
		String cuenta = frame.get_txt_idproveedor().getText();
		String sucursal = frame.get_txt_sucursal().getText();
		String numero = frame.get_txt_numero().getText();
		String letra = frame.get_list_letra().getSelectedItem().toString();
		String idcomprobante = sucursal + numero + letra;
		String tc = this.getTipoTC();
		String idvendedor = frame.get_txt_idvendedor().getText();
		boolean ok = true;
		if (idvendedor.compareTo("") != 0) {
			if (this.Vendedor.existe(idvendedor)) {
				ok = true;
			} else {
				ok = this.elegir_vendedor();
				if (!ok) {
					frame.get_txt_idvendedor().requestFocusInWindow();
					error("Codigo de Vendedor Incorrecto. Verifique");
				}
			}
		} else {
			ok = this.elegir_vendedor();
			if (!ok) {
				frame.get_txt_idvendedor().requestFocusInWindow();
				error("Codigo de Vendedor Incorrecto. Verifique");
			}
		}
		if (ok) {
			Object[][] _cpte = null;
			if (tc.compareTo("FCN") != 0) {
				_cpte = data.getCpte(cuenta, tc, idcomprobante);
			} else {
				_cpte = data.getCpteBeta(cuenta, tc, idcomprobante);
			}

			ok = false;
			if (_cpte != null) {
				if (_cpte.length > 0) {
					ok = true;
					nuevo=false;
				}
			}
			if (!ok) {
				ok = confirmar(
						"Este Comprobante no existe. Confirme para grabar ", 2);
			}
			if (ok) {
				int selections = check_selectionss();

				if (selections > 0) {
					int rows = frame.getJTable().getRowCount();
					ok = false;
					if (rows != selections) {
						ok = false;
						if (confirmar("Confirme Seleccion de Items: ", 2)) {
							ok = true;
						}

					} else {
						ok = true;
					}
				} else {
					ok = false;
					if (frame.get_chk_carga_articulos().isSelected()) {

						if (confirmar("Confirme para evitar la carga de articulos: ",
								2)) {
							ok = true;
						}
					} else {
						ok = true;
					}

				}
				if (ok) {
					
					if (images.size() <= 0) {
						ok = false;
						error("Debe Incorporar Las Imagenes del Comprobante");
						if (confirmar(
								"Confirme si quiere evitar la carga de imagenes: ",
								2)) {
							ok = true;
						}
					}
						if (ok) {

							try {
								ok = frame.get_list_imputacion()
										.getSelectedIndex() >= 0;
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if (ok){
								if (this.getTipoTC().compareTo("RMC") != 0) {
									if (this.get_subtotal() <= 0) {
										ok = false;
										error("Debe Cargar Los Impuestos");
										ok=confirmar("Confirme para una carga automatica de impuestos: ",2);
										if (ok) {
											
											ok = this.get_impuestos_automaticos();
											if (!ok){
												error("ERROR CALCULANDO IMPUESTOS AUTOMATICOS");
											}
										}
									}
								}
								if (ok){
									ok = this.diferencia() <= 0.01;
									if (!ok) {

										ok = confirmar("Existe una diferencia de "
												+ this.diferencia()
												+ " confirme para continuar: ", 4);
									}
									if (ok) {

										
											ok = this.check_articulos_validos();
											if (ok) {
												if (nuevo){
													ok=false;
													double total=0.0;
													double pretotal=this.get_total();
													double diferencia=Math.abs(pretotal-total);
													String _total=this.ingresar("Por Favor Ingrese EL importe Total del Comprobante:");
													_total=_total.replaceAll(",", "");
														
													if (_total.compareTo("")!=0){
															try {
																total=new Double(_total);
															} catch (NumberFormatException e) {
																// TODO Auto-generated catch block
																e.printStackTrace();
															}	
													}
													diferencia=total-pretotal;	
													diferencia=new Convertidor().roundDouble(diferencia, 2);
													String NEW_LINE = System.getProperty("line.separator");
													if (diferencia>0.01){
															String msj="DIFERENCIA EN IMPORTE TOTAL: "+diferencia+"";
															msj+=NEW_LINE;
															msj+=" carga  :"+new Convertidor().roundDouble(pretotal, 2);
															msj+=NEW_LINE;
															msj+=" checkeo:"+new Convertidor().roundDouble(total, 2);
															error(msj);
															ok=false;
															aviso("POR FAVOR VERIFIQUE LA CARGA");
													}else{
														ok=true;
													}
													
													
													
														
												}
												if (ok){
													this._guardar(iduser, operacion);	
												}
												
											} else {
												error("Verifique que todos los articulos esten habilitados");
											}
										
									}
								}
								
							} else {
								error("Verifique la imputacion de esta operacion. Tal vez deba configurar el proveedor");
							}
							

						}
					}
				
			}

		}

		if (!ok) {
			error("OPERACION CANCELADA");
		}

	}

	private List<String> _grabar_asiento() {
		List<String> instrucciones = new ArrayList<String>();
		int i = frame.get_list_tc().getSelectedIndex();
		if (i == 0) {

			instrucciones = this._get_instrucciones_asiento_fcc();
		}
		if (i == 1) {
			instrucciones = this._get_instrucciones_asiento_ncc();
		}
		if (i == 3) {
			instrucciones = this._get_instrucciones_asiento_fcn();
		}
		return instrucciones;
	}

	public boolean enlaza_remito() {
		boolean ok = false;
		// 7 tc 8 id
		int i = 0;
		while (i < frame.getJTable().getRowCount() & !ok) {
			boolean selected = false;
			try {
				selected = (Boolean) frame.getJTable().getValueAt(i, 0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
			if (selected) {
				String tc = (String) frame.getJTable().getValueAt(i, 7);
				String idcomprobante = (String) frame.getJTable().getValueAt(i,
						8);
				if (tc != null) {
					if ((tc.compareTo("RMC") == 0)
							& idcomprobante.compareTo("") != 0) {
						ok = true;
					}
				}

			}
			i++;
		}

		return ok;
	}

	public boolean enlaza_pedido() {
		boolean ok = false;
		// 7 tc 8 id
		int i = 0;
		while (i < frame.getJTable().getRowCount() & !ok) {
			boolean selected = false;
			try {
				selected = (Boolean) frame.getJTable().getValueAt(i, 0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
			if (selected) {
				String tc = (String) frame.getJTable().getValueAt(i, 7);
				String idcomprobante = (String) frame.getJTable().getValueAt(i,
						8);
				if (tc != null) {
					if ((tc.compareTo("PDP") == 0 | tc.compareTo("PEP") == 0)
							& idcomprobante.compareTo("") != 0) {
						ok = true;
					}
				}

			}
			i++;
		}

		return ok;
	}

	public String getEncabezadoDigital(String cuenta, String tc,
			String idcomprobante) {
		int selections = check_selectionss();
		String fecha_carga_articulos = "";
		String iduser = this.getConstructor().getIduser();
		String ip = data.getIp();
		int carga_articulos = 0;
		System.out.println("encabezado digital > selections?" + selections
				+ " cargar articulos?"
				+ frame.get_chk_carga_articulos().isSelected());
		if (selections <= 0) {
			if (frame.get_chk_carga_articulos().isSelected()) {
				carga_articulos = 1;
			}
		} else {
			carga_articulos = 0;
			fecha_carga_articulos = data.getSystemDate();
		}
		String q = "";

		if (!data.existDigital(cuenta, tc, idcomprobante)) {
			q = data.getInsertDigital(cuenta, tc, idcomprobante, images.size(),
					carga_articulos, fecha_carga_articulos, iduser, ip);
		} else {

			q = data.getUpdateDigital(cuenta, tc, idcomprobante, ""
					+ carga_articulos, "" + images.size(),
					fecha_carga_articulos, iduser, ip);
		}
		return q;
	}

	public void _guardar(String operacion, String iduser) {
		boolean edit = false;
		String cuenta = frame.get_txt_idproveedor().getText();
		String sucursal = frame.get_txt_sucursal().getText();
		String numero = frame.get_txt_numero().getText();
		String letra = frame.get_list_letra().getSelectedItem().toString();
		String idcomprobante = sucursal + numero + letra;
		String tc = this.getTipoTC();

		boolean ok = true;
		if (ok) {

			data.beginTransaction();

			try {
				ok = this.eliminar_fotos();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			
			ok = this.guardar_imagenes();
			if (!ok) {
				
				data.rollbackTransaction();
				error("Error guardando Imagenes");
			} else {
				if (data.tiene_remitos_aplicados(cuenta, tc, idcomprobante)) {
					System.out.println("tiene remitos aplicados");
					List<String> remitos_regenerar = this
							.getRemitosViejos();

					// primero elimino lo viejo
					int i = 0;
					while (i < remitos_regenerar.size() & ok) {
						String _tc = "RMC";
						String _idcomprobante = remitos_regenerar.get(i);
						String fecha = "";
						data.clearBatch();
						List<String> _instrucciones = this
								.getInstruccionesRegenerarRemitos(_tc,
										_idcomprobante, fecha);
						for (int j = 0; j < _instrucciones.size(); j++) {
							data.addBatch(_instrucciones.get(j));
						}
						ok = !data.executeBatch();

						i++;
					}
					String q = data.getDelete_remitos_aplicados(cuenta, tc,
							idcomprobante);
					data.clearBatch();
					data.addBatch(q);
					ok = !data.executeBatch();
					if (!ok) {
						error("Error Regenerando Remitos a su estado original");
					}
				}else{
					System.out.println("no tiene remitos aplicados");
				}
				data.clearBatch();
				List<String> instrucciones_eliminar = this
						.getInstruccionesEliminar(cuenta, tc, sucursal, numero,
								letra);

				for (int i = 0; i < instrucciones_eliminar.size(); i++) {
					data.addBatch(instrucciones_eliminar.get(i));
				}

				ok = !data.executeBatch();
				if (!ok) {
					error("Error Limpiando Datos Anteriores");
					data.rollbackTransaction();
				} else {
					// para regenerar remitos
					

				}
			}
			if (!ok) {
				data.rollbackTransaction();
				error("Error Sobreescribiendo Comprobante");
				
			}

			if (ok) {

				List<String> instrucciones = null;
				instrucciones = new ArrayList<String>();
				String idoperacion = "" + data.getProximoOperacion();

				String registra_operacion = ""
						+ data
								.getOperacion(idoperacion, iduser, operacion
										+ " " + cuenta + ":" + tc + "-"
										+ idcomprobante);
				instrucciones.add(registra_operacion);
				if (tc.compareTo("FCN") != 0) {
					ok = data.existCpte(cuenta, tc, idcomprobante);
				} else {
					ok = data.existCpteBeta(cuenta, tc, idcomprobante);
					if (ok){
						edit=true;
					}
				}
				System.out.println("ExisteCPTE? "+ok);
				if (!ok) {
					System.out.println("No Existe CPTE");
					// este es un comprobante nuevo
					data.clearBatch();
					List<String> instrucciones_encabezado = new ArrayList<String>();
					List<String> instrucciones_asiento = this._grabar_asiento();

					if (tc.compareTo("RMC") == 0) {
						instrucciones_encabezado = this
								.getInstruccionesEncabezado();
					} else {
						ok = false;
						if (instrucciones_asiento != null) {
							if (instrucciones_asiento.size() > 0) {
								ok = true;
								if (tc.compareTo("FCN") != 0) {
									instrucciones_encabezado = this
											.getInstruccionesEncabezado();
								} else {
									instrucciones_encabezado = this
											.getInstruccionesEncabezadoBeta();
									
									if (!edit){
										instrucciones_encabezado.add(data
												.getUpdateTc("FCN"));
									}
								}
								for (int i = 0; i < instrucciones_asiento
										.size(); i++) {
									instrucciones.add(instrucciones_asiento
											.get(i));
								}

							}
						}

					}
					for (int i = 0; i < instrucciones_encabezado.size(); i++) {
						instrucciones.add(instrucciones_encabezado.get(i));
					}
					// encabezado digital
					String q = this.getEncabezadoDigital(cuenta, tc,
							idcomprobante);
					instrucciones.add(q);
					for (int i = 0; i < instrucciones.size(); i++) {
						System.out.println("Instruccion" + i + "> "
								+ instrucciones.get(i));
						data.addBatch(instrucciones.get(i));
					}
					ok = !data.executeBatch();
					if (!ok) {
						error("Error Confirmando Operacion");
					}

				}
				if (ok) {
					// existe el comprobante

					if (this.check_selectionss() > 0) {

						instrucciones = new ArrayList<String>();
						data.clearBatch();
						List<String> instrucciones_stock = null;
						List<String> instrucciones_remito = this
								.getInstruccionesConciliarRemito();

						if (tc.compareTo("FCN") != 0) {
							instrucciones_stock = this.getInstruccionesStock();

						} else {
							instrucciones_stock = this
									.getInstruccionesStockBeta();

						}

						for (int i = 0; i < instrucciones_stock.size(); i++) {
							instrucciones.add(instrucciones_stock.get(i));
						}

						if (this.enlaza_remito()) {
							if (this.check_remitos_vs_articulos()) {
								for (int i = 0; i < instrucciones_remito.size(); i++) {
									instrucciones.add(instrucciones_remito
											.get(i));
								}
							} else {
								error("Se detecto una diferencia entre los remitos utilizados y la carga de articulos actual. Verifique");
								ok = false;
							}
						}

						if (this.enlaza_pedido()) {
							List<String> instrucciones_enlace = this
									.getInstruccionesEnlace();
							for (int i = 0; i < instrucciones_enlace.size(); i++) {
								instrucciones.add(instrucciones_enlace.get(i));
							}
						}

						// //seccion para que marque
						String q = this.getEncabezadoDigital(cuenta, tc,
								idcomprobante);
						instrucciones.add(q);
						// //
						if (ok) {
							for (int i = 0; i < instrucciones.size(); i++) {
								System.out.println("Instruccion" + i + "> "
										+ instrucciones.get(i));
								data.addBatch(instrucciones.get(i));
							}
							ok = !data.executeBatch();
							if (!ok) {
								error("grabando instrucciones de stock");
							}
						}

					} else {
						ok = true;
					}

				}else{
					System.out.println("Existe CPTE");
				}

				if (ok) {
					data.commitTransaction();
					aviso("Se guardo Correctamente el comprobante");

					if (this.check_selectionss() > 0) {
						this.guardar_stock(cuenta, tc, idcomprobante);
					} else {
						this.clean();
					}

				} else {
					data.rollbackTransaction();
					error("Error grabando comprobante");

				}

			} else {
				data.rollbackTransaction();
				error("Error grabando comprobante");
				
			}
			System.out.println("Fin de metodo.");
		}
				
	}

	public int check_selectionss() {
		boolean ok = false;
		int selecteds = 0;
		for (int i = 0; i < frame.getJTable().getRowCount(); i++) {
			boolean selected = false;
			try {
				selected = (Boolean) frame.getJTable().getValueAt(i, 0);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				// e1.printStackTrace();
			}
			if (selected) {
				selecteds++;
			}
		}
		return selecteds;
	}

	public List<String> getInstruccionesStock() {
		String fecha = data.getSystemDate();
		String cuenta = frame.get_txt_idproveedor().getText();
		String tc = this.getTipoTC();
		String idcomprobante = frame.get_txt_idcomprobante().getText();
		// data.beginTransaction();
		List<String> instrucciones = new ArrayList<String>();
		String instruccion = "";
		if (this._check_articulos_cargados()) {
			System.out.println("tratando de sobreescribir items");
			instruccion = data.getunBlock();
			instrucciones.add(instruccion);
			instruccion = data
					.getEliminarCpteInsumos(cuenta, tc, idcomprobante);
			instrucciones.add(instruccion);
			instruccion = data.getEliminarStock(cuenta, tc, idcomprobante);
			instrucciones.add(instruccion);
			instruccion = data.getDeleteEnlace(tc, idcomprobante, cuenta);
			instrucciones.add(instruccion);
			instruccion = data.getBlock();
			instrucciones.add(instruccion);
		}
		instruccion = data
				.getInsertEncabezado(cuenta, tc, idcomprobante, fecha);
		instrucciones.add(instruccion);
		int i = 0;
		while (i < frame.getJTable().getRowCount()) {
			boolean selected = false;
			try {
				selected = (Boolean) frame.getJTable().getValueAt(i, 0);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				// e1.printStackTrace();
			}
			if (selected) {
				String idarticulo = "";
				try {
					idarticulo = frame.getJTable().getValueAt(i, 1).toString();
				} catch (Exception e) {

				}
				// aca va un eval row antes de grabar
				if (idarticulo.compareTo("") != 0) {
					String q1 = this.insert_sets(fecha, cuenta, tc,
							idcomprobante, i);

					instrucciones.add(q1);
					String q2 = this.data.getInsertStock();
					instrucciones.add(q2);
					String q3 = this.data.getInsertCpte();
					instrucciones.add(q3);
				}
			}
			i++;
		}

		return instrucciones;
	}

	public List<String> getInstruccionesStockBeta() {
		String fecha = data.getSystemDate();
		String cuenta = frame.get_txt_idproveedor().getText();
		String tc = this.getTipoTC();
		String idcomprobante = frame.get_txt_idcomprobante().getText();
		// data.beginTransaction();
		List<String> instrucciones = new ArrayList<String>();
		String instruccion = "";
		if (this._check_articulos_cargados()) {
			System.out.println("tratando de sobreescribir items");
			instruccion = data.getunBlock();
			instrucciones.add(instruccion);
			instruccion = data.getEliminarCpteInsumosBeta(cuenta, tc,
					idcomprobante);
			instrucciones.add(instruccion);
			instruccion = data.getEliminarStock(cuenta, tc, idcomprobante);
			instrucciones.add(instruccion);
			instruccion = data.getDeleteEnlace(tc, idcomprobante, cuenta);
			instrucciones.add(instruccion);
			instruccion = data.getBlock();
			instrucciones.add(instruccion);
		}
		instruccion = data
				.getInsertEncabezado(cuenta, tc, idcomprobante, fecha);
		instrucciones.add(instruccion);
		for (int i = 0; i < frame.getJTable().getRowCount(); i++) {
			boolean selected = false;
			try {
				selected = (Boolean) frame.getJTable().getValueAt(i, 0);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				// e1.printStackTrace();
			}
			if (selected) {
				String idarticulo = "";
				try {
					idarticulo = frame.getJTable().getValueAt(i, 1).toString();
				} catch (Exception e) {

				}
				// aca va un eval row antes de grabar
				if (idarticulo.compareTo("") != 0) {
					String q1 = this.insert_sets(fecha, cuenta, tc,
							idcomprobante, i);

					instrucciones.add(q1);
					String q2 = this.data.getInsertStock();
					instrucciones.add(q2);
					String q3 = this.data.getInsertCpteBeta();
					instrucciones.add(q3);
				}
			}

		}

		return instrucciones;
	}

	public List<String> getInstruccionesEnlace() {
		String fecha = data.getSystemDate();
		String cuenta = frame.get_txt_idproveedor().getText();
		String tc = this.getTipoTC();
		String idcomprobante = frame.get_txt_idcomprobante().getText();
		// data.beginTransaction();
		List<String> instrucciones = new ArrayList<String>();
		String instruccion = "";

		for (int i = 0; i < frame.getJTable().getRowCount(); i++) {
			boolean selected = false;
			try {
				selected = (Boolean) frame.getJTable().getValueAt(i, 0);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				// e1.printStackTrace();
			}
			if (selected) {
				String idarticulo = "";
				String pp_tc = "";
				String pp_idcomprobante = "";
				try {
					idarticulo = frame.getJTable().getValueAt(i, 1).toString();
				} catch (Exception e) {

				}
				try {
					pp_tc = frame.getJTable().getValueAt(i, 7).toString();
				} catch (Exception e) {

				}
				try {
					pp_idcomprobante = frame.getJTable().getValueAt(i, 8)
							.toString();
				} catch (Exception e) {

				}
				if (pp_tc.compareTo("") != 0
						& pp_idcomprobante.compareTo("") != 0) {
					instruccion = data.getInsert_enlace_compras_pedido(tc,
							idcomprobante, cuenta, pp_tc, pp_idcomprobante,
							idarticulo);
					System.out.println(instruccion);
					instrucciones.add(instruccion);
				}

				// aca va un eval row antes de grabar
			}

		}
		return instrucciones;
	}

	public void guardar_stock(String cuenta, String tc, String idcomprobante) {
		// data.commitTransaction();
		boolean actualizar = this.check_updates(cuenta, tc, idcomprobante);

		boolean etiquetar = false;
		if (frame.get_list_etiquetas().getSelectedIndex() == 1) {
			etiquetar = true;
		} else {
			if (frame.get_list_etiquetas().getSelectedIndex() == 2) {
				etiquetar = this.preguntar("confirmar", "Imprime Etiquetas?");
			}
		}
		this.clean();

		if (etiquetar) {
			this.Etiquetar(cuenta, tc, idcomprobante);
		}

		if (actualizar) {
			this.Actualizar(cuenta, tc, idcomprobante);
		}

	}

	public boolean check_updates(String cuenta, String tc, String idcomprobante) {
		boolean update = false;
		Object[][] results = null;
		if (this.getTipoTC().compareTo("FCN") != 0) {
			results = data.getArticulosPrecios(cuenta, tc, idcomprobante);
		} else {
			results = data.getArticulosPreciosBeta(cuenta, tc, idcomprobante);
		}
		if (results != null) {
			int i = 0;
			while (i < results.length & !update) {
				String idarticulo = results[i][0].toString();
				String precio = results[i][2].toString();

				double _precio = 0.0;
				double _precio_old = 0.0;
				Object[][] old = data.getData(idarticulo);
				if (old != null) {
					if (old.length > 0) {
						try {
							_precio_old = new Double((String) old[0][6]);
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				try {
					_precio = new Double(precio.replace(",", ""));

				} catch (Exception e) {

				}
				System.out.println("debe actualizar " + idarticulo + " "
						+ _precio + " " + _precio_old);
				update = (Math.abs(_precio - _precio_old) > 0.1);
				i++;
			}
		}
		return update;
	}

	public String insert_sets(String fecha, String cuenta, String tc,
			String idcomprobante, int row) {
		String q = "";
		try {
			String idarticulo = frame.getJTable().getValueAt(row, 1).toString();
			String descripcion = frame.getJTable().getValueAt(row, 2)
					.toString();
			descripcion = descripcion.replaceAll("\n", " ");
			descripcion = descripcion.replaceAll("'", "''");
			String cantidad = frame.getJTable().getValueAt(row, 3).toString();
			String costo = frame.getJTable().getValueAt(row, 4).toString();
			costo = costo.replaceAll(",", "");
			String descuento = frame.getJTable().getValueAt(row, 5).toString();
			descuento = descuento.replaceAll(",", "");
			String total = frame.getJTable().getValueAt(row, 6).toString();
			total = total.replaceAll(",", "");
			String secuencia = "" + row;
			String precioventa = "0.0";
			String tc_origen = "";
			try {
				tc_origen = frame.getJTable().getValueAt(row, 7).toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			String idcomprobante_origen = "";
			try {
				idcomprobante_origen = frame.getJTable().getValueAt(row, 8)
						.toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			if (this.getTipoTC().compareTo("NCC")==0){
				try {
					double qty=new Double(cantidad.replaceAll(",", ""));
					cantidad="-"+qty;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			q = data.getInsertSets(secuencia, idarticulo, descripcion,
					cantidad, costo, precioventa, descuento, total, tc_origen,
					idcomprobante_origen);
			System.out.println(q);
		} catch (Exception e) {

		}
		return q;
	}

	public void buscarComprobantes() {
		JTextField proveedor = frame.get_txt_idproveedor();
		JTextField comprobante = frame.get_txt_idcomprobante();
		this.buscarComprobantes(proveedor, comprobante, false);
	}

	private aplicacion.herramientas.java.visualizadores.Comprobantes vComprobantes = null;

	public void buscarComprobantes(JTextField tx, JTextField proveedor,
			boolean shift) {
		if (vComprobantes != null) {
			vComprobantes.close();
		}
		vComprobantes = new aplicacion.herramientas.java.visualizadores.Comprobantes(
				this.getConstructor());

		vComprobantes.setProveedor(proveedor);
		vComprobantes.setTc(frame.get_list_tc());
		String []options=new String[]{"FCC","NCC","RMC","FCN"};
		vComprobantes.setShift(shift);
		System.out.println("Shift Pressed Down");
		vComprobantes.setSu(data.getIsSuperUser(this.getConstructor()
				.getIduser()));
		int n = vComprobantes.buscarComprobantes(tx,options);
		if (n == 0) {
			aviso("no hay Comprobantess con ese codigo");
		}
	}

	protected JTable create_table_xml(String nombre) {
		JTable table = null;
		if (nombre.compareTo(_Interface._table_items) == 0) {
			if (frame.getJTable() != null) {
				crear_tabla_items();
			}

			table = frame.getJTable();
		}
		if (nombre.compareTo(_Interface._table_remitos) == 0) {

		}
		return table;
	}

	public void buscarProveedoresComprobantes(JTextField tx, boolean shift) {
		if (vComprobantes != null) {
			vComprobantes.close();
		}

		vComprobantes = new aplicacion.herramientas.java.visualizadores.Comprobantes(
				this.getConstructor());
		vComprobantes.setShift(shift);
		vComprobantes.setSu(data.getIsSuperUser(this.getConstructor()
				.getIduser()));
		int n = vComprobantes.buscarProveedoresComprobantes(tx);

		if (n == 0) {
			aviso("No se Encontraros comprobantes pendientes");
		}
	}

	public void init() {
		this.clean();
		this.cargarTipoTc();
		this.cargarLetras();
		this.init_scanner();
	}

	private void init_scanner() {
		try {
			scanner = Scanner.getDevice();
			scannerlistener = new _ScannerListener();
			scannerlistener.setLogic(this);
			scanner.addListener(scannerlistener);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void _rotar_derecha() {
		System.out.println("rotar_Derecha");
		frame.getCanvas().setAngle(0.2);
	}

	public void _rotar_izquierda() {
		System.out.println("rotar_Izquierda");
		frame.getCanvas().setAngle(-0.2);
	}

	public void test_actualizar() {
		String cuenta = frame.get_txt_idproveedor().getText();
		String tc = this.getTipoTC();
		String idcomprobante = frame.get_txt_idcomprobante().getText();
		this.Actualizar(cuenta, tc, idcomprobante);
	}

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

	private void create_table_impuestos(Object[][] results) {
		_Constructor constructor = (_Constructor) this.getConstructor();
		CustomTable table = new CustomTable();

		Column col = new Column();

		col = new Column();
		col.setName("Cuenta");
		col.setWidth(120);
		col.setEditable(true);
		table.addColumn(col);

		col = new Column();
		col.setName("Descripcion");
		col.setWidth(300);
		col.setEditable(false);
		table.addColumn(col);

		col = new Column();
		col.setName("Importe");
		col.setWidth(120);
		col.setEditable(true);
		col.setAligment(JTextField.RIGHT);

		CellEditor pce = new CellEditor();
		pce.addKeyListener(constructor.getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_importe);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());

		table.addColumn(col);

		table.setSortable(false);
		table.setData(results);
		table.addKeyListener(this.getConstructor().getKeyListener());
		table.addMouseListener(this.getConstructor().getMouseListener());
		Font fuente = new Font("Dialog", Font.BOLD, 10);
		table.setHeaderFont(fuente);
		table.setFont(fuente);
		table.setName(_Interface._table_impuestos);
		table.build();
		table.fillData();
		
		frame.setJTable4(table.getTable());
	}

	public void visual_stock(String idart) {
		/*
		 * CargadorDeAplicacion CA=new CargadorDeAplicacion();
		 * CA.setParameter("iduser", ""); CA.setSucursal("Neuquen");
		 * CA.setParameter("frame",this); CA.setParameter("codigo",idart);
		 * CA.setParameter("GX",data.getSql());
		 * CA.setAuthorizationRequierement(false);
		 * CA.setClase("visual.Task_VisualGuessMovimientoStock"); CA.launch();
		 */
	}

	public boolean _evaluate_importe(JTextField tx, int row) {
		String impx = tx.getText();
		impx = impx.replaceAll(",", "");
		boolean error = false;
		double imp = 0.0;
		try {
			imp = new Double(impx);
		} catch (Exception e) {
			error = true;
			e.printStackTrace();
		}

		if (imp < 0) {
			error = true;
		}

		return !error;
	}

	private double _get_importe(String impx) {
		impx.replaceAll(",", "");

		double imp = 0.0;
		try {
			imp = new Double(impx);
		} catch (Exception e) {

		}

		return imp;
	}

	public double _get_importe(JTextField tx) {
		String impx = tx.getText();
		impx = impx.replaceAll(",", "");
		double imp = this._get_importe(impx);
		return imp;
	}

	private double getSubtotalPrecargado() {
		recalculate_total(-1, null);
		double tmp = 0.0;
		try {
			String sub = frame.get_txt_subtotal_impuestos().getText();
			sub = sub.replace(",", "");
			tmp = new Double(sub);
		} catch (Exception e) {

		}
		return tmp;
	}

	private void _do_automatic_calcs(double imp) {
		System.out.println("Automatic Calcs");
		if (this.requiere_percepcion_ingresos_brutos == 1) {
			this._do_automatic_calc_percepcion_iibb(imp, null);
		}
		if (this.requiere_iva27 == 1) {
			this._do_automatic_calc_iva27(imp, null);
		}
		if (this.requiere_percepcion_ganancias == 1) {
			this._do_automatic_calc_ganacias(imp, null);
		}
		if (this.requiere_impuestos_internos == 1) {
			this._do_automatic_calc_impuestos_internos(imp, null);
		}
		if (this.requiere_percepcion_iva == 1) {
			this._do_automatic_calc_percepcion_iva(imp, null);
		}

		if (this.requiere_iva21 == 1) {
			this._do_automatic_calc_iva(imp, null);
		}
		if (this.requiere_iva10 == 1) {
			this._do_automatic_calc_iva10(imp, null);
		}
		if (this.requiere_rg3337 == 1) {
			this._do_automatic_calc_rg3337(imp, null);
		}
		this.recalculate_total(-1, null);
	}

	public boolean _evaluate_subtotal(JTextField tx, int row) {
		boolean transfer = true;
		
		if (_get_importe(tx) > 0) {

			frame.get_txt_subtotal_impuestos().setText(
					new Convertidor().getMoney(this._get_importe(tx), 2));
			if (this.getTipoTC().compareTo("FCN") != 0) {
				this._do_automatic_calcs(_get_importe(tx));
			}

			frame.getJTable4().changeSelection(row + 1, 2, false, false);
			frame.getJTable4().editCellAt(row + 1, 2);
			frame.getJTable4().transferFocus();

		} else {
			transfer = false;
			error("El subtotal no puede ser nulo");
			tx.setSelectionStart(0);
			tx.setSelectionEnd(tx.getText().length());
		}

		return transfer;
	}

	public boolean _evaluate_subtotal_auto(String importe, int row) {
		boolean ok=false;
		importe = importe.replaceAll(",", "");
		double imp = this._get_importe(importe);
		if (imp > 0) {
			ok=true;
			
			try {
				frame.getJTable4().getCellEditor().stopCellEditing();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			frame.getJTable4().setValueAt(importe,
					_Interface.row_subtotal, 2);
			//aviso("IMPORTE SUBTOTAL TABLA IMPUESTOS? "+frame.getJTable4().getValueAt(row, 2));
			frame.get_txt_subtotal_impuestos().setText(
					new Convertidor().getMoney(imp, 2));

			if (this.getTipoTC().compareTo("FCN") != 0) {
				this._do_automatic_calcs(imp);
			}else{
				this.recalculate_total(-1, null);
			}
			
			

		} 

		return ok;
	}

	private double get_subtotal() {
		double imp = 0.0;
		try {
			String importe = frame.getJTable4().getValueAt(0, 2).toString();
			importe = importe.replace(",", "");
			imp = new Double(importe);
		} catch (Exception e) {

		}
		return imp;
	}

	private void _automatic_calc(JTextField tx, int row) {
		double imp = this.get_subtotal();
		if (imp > 0) {
			if (this.requiere_percepcion_ingresos_brutos == 1
					& row == _Interface.row_percepcion_iibb) {
				this._do_automatic_calc_percepcion_iibb(imp, tx);
			}
			if (this.requiere_iva27 == 1 & row == _Interface.row_iva27) {
				this._do_automatic_calc_iva27(imp, tx);
			}
			if (this.requiere_percepcion_ganancias == 1
					& row == _Interface.row_retencion_ganancias) {
				this._do_automatic_calc_ganacias(imp, tx);
			}
			if (this.requiere_impuestos_internos == 1
					& row == _Interface.row_impuestos_internos) {
				this._do_automatic_calc_impuestos_internos(imp, tx);
			}
			if (this.requiere_percepcion_iva == 1
					& row == _Interface.row_retencion_iva) {
				this._do_automatic_calc_percepcion_iva(imp, tx);
			}

			if (this.requiere_iva21 == 1 & row == _Interface.row_iva) {
				this._do_automatic_calc_iva(imp, tx);
			}
			if (this.requiere_iva10 == 1 & row == _Interface.row_iva10) {
				this._do_automatic_calc_iva10(imp, tx);
			}
			if (this.requiere_rg3337 == 1 & row == _Interface.row_rg3337) {
				this._do_automatic_calc_rg3337(imp, tx);
			}
		}

	}

	private void _do_automatic_calc_percepcion_iibb(double impx, JTextField tx) {
		double _importe=0.0;
		if (impx > this.base_iibb) {
			_importe = impx * this.alic_iibb;
		}
		System.out.println("Calculo de IIBB "+impx+" base iibb:"+base_iibb+" alic_iibb:"+this.alic_iibb+" iibb:"+_importe);
		Convertidor C = new Convertidor();
		String importe = C.getMoney(_importe, 2);
		if (tx != null) {
			tx.setText(importe);
		} else {
			System.out.println("JTABLE ("+_Interface.row_percepcion_iibb+",2)="+importe);
			frame.getJTable4().setValueAt(importe,
					_Interface.row_percepcion_iibb, 2);
		}
	}

	private void _do_automatic_calc_iva27(double impx, JTextField tx) {
		double _importe=0.0;
		_importe =  impx * this.alic_iva3;
		Convertidor C = new Convertidor();
		String importe = C.getMoney(_importe, 2);
		if (tx != null) {
			tx.setText(importe);
		} else {
			frame.getJTable4().setValueAt(importe, _Interface.row_iva27, 2);
		}
	}

	private void _do_automatic_calc_ganacias(double impx, JTextField tx) {
		double _importe=0.0;
		_importe =  impx * 1;// alicuota percepcion ganancias
		Convertidor C = new Convertidor();
		String importe = C.getMoney(_importe, 2);
		if (tx != null) {
			tx.setText(importe);
		} else {
			frame.getJTable4().setValueAt(importe,
					_Interface.row_retencion_ganancias, 2);
		}
	}

	private void _do_automatic_calc_impuestos_internos(double impx,
			JTextField tx) {
		double _importe=0.0;
		_importe =  impx * this.alic_impuestos_internos;
		Convertidor C = new Convertidor();
		String importe = C.getMoney(_importe, 2);
		if (tx != null) {
			tx.setText(importe);
		} else {
			frame.getJTable4().setValueAt(importe,
					_Interface.row_impuestos_internos, 2);
		}
	}

	private void _do_automatic_calc_percepcion_iva(double impx, JTextField tx) {
		double _importe=0.0;
		_importe =  impx * this.alic_perc_iva;
		Convertidor C = new Convertidor();
		String importe = C.getMoney(_importe, 2);
		if (tx != null) {
			tx.setText(importe);
		} else {
			frame.getJTable4().setValueAt(importe,
					_Interface.row_retencion_iva, 2);
		}

	}

	private void _do_automatic_calc_iva(double impx, JTextField tx) {
		double _importe=0.0;
		_importe = impx * this.alic_iva1;
		System.out.println("Calculo de IVA21 "+impx+"  alic_iva:"+this.alic_iva1+" iva:"+_importe);
		Convertidor C = new Convertidor();
		String importe = C.getMoney(_importe, 2);
		if (tx != null) {
			tx.setText(importe);
		} else {
			System.out.println("JTABLE ("+_Interface.row_iva+",2)="+importe);
			frame.getJTable4().setValueAt(importe, _Interface.row_iva, 2);
		}
	}

	private void _do_automatic_calc_iva10(double impx, JTextField tx) {
		double _importe=0.0;
		_importe = impx * this.alic_iva2;
		System.out.println("Calculo de IVA21 "+impx+"  alic_iva:"+this.alic_iva1+" iva:"+_importe);
		Convertidor C = new Convertidor();
		String importe = C.getMoney(_importe, 2);
		if (tx != null) {
			tx.setText(importe);
		} else {
			frame.getJTable4().setValueAt(importe, _Interface.row_iva10, 2);
		}
	}

	private void _do_automatic_calc_rg3337(double impx, JTextField tx) {
		double _importe=0.0;
		_importe =  impx * this.alic_rg3337;
		Convertidor C = new Convertidor();
		String importe = C.getMoney(_importe, 2);
		if (tx != null) {
			tx.setText(importe);
		} else {
			frame.getJTable4().setValueAt(importe, _Interface.row_rg3337, 2);
		}
	}

	public boolean _evaluate_impuesto(JTextField tx, int row, int activo,
			String impuesto) {
		boolean transfer_focus = false;
		boolean automatic_calc = false;
		double imp = this._get_importe(tx);
		if (activo == 1) {
			if (imp <= 0) {
				aviso("Este proveedor requiere el impuesto " + impuesto
						+ " en la carga");
				if (this.preguntar("Confirmar",
						"Evita la carga de este impuesto?")) {
					transfer_focus = true;
				} else {
					automatic_calc = true;
				}
			} else {
				transfer_focus = true;
			}
		} else {
			if (imp > 0) {

				if (activo == 0) {
					aviso("Este proveedor no requiere el impuesto " + impuesto
							+ " en la carga");
					if (this.preguntar("Confirmar",
							"Confirma la carga de este impuesto?")) {
						transfer_focus = true;
					}
				}
				if (activo == 2) {
					transfer_focus = true;
				}

			} else {
				if (activo == 2) {
					aviso("Revise el comprobante por la existencia de este impuesto");
					if (this.preguntar("Confirmar",
							"Evita la carga de este impuesto?")) {
						transfer_focus = true;
					} else {
						automatic_calc = true;
						transfer_focus = false;
					}
				} else {
					transfer_focus = true;
				}

			}
		}

		if (automatic_calc) {
			_automatic_calc(tx, row);
		}

		return transfer_focus;

	}

	public void _evaluate_importe_columns(JTextField tx, int row) {
		boolean transfer = true;

		if (this._evaluate_importe(tx, row)) {

			if (row == _Interface.row_subtotal) {
				// esto es subtotal. no pasa nada. pasa a la siguiente
				transfer = this._evaluate_subtotal(tx, row);
			}

			if (row == _Interface.row_iva) {
				if (this.getTipoTC().compareTo("FCN") != 0) {
					// esto es iva 21%
					transfer = this._evaluate_impuesto(tx, row,
							this.requiere_iva21, "IVA C.F. 21%");
				}

			}
			if (row == _Interface.row_iva10) {
				// esto es iva 10.5%
				if (this.getTipoTC().compareTo("FCN") != 0) {
					transfer = this._evaluate_impuesto(tx, row,
							this.requiere_iva10, "IVA C.F. 10.5%");
				}
			}
			if (row == _Interface.row_iva27) {
				// esto es iva 27%
				if (this.getTipoTC().compareTo("FCN") != 0) {
					transfer = this._evaluate_impuesto(tx, row,
							this.requiere_iva27, "IVA C.F. 27%");
				}
			}
			if (row == _Interface.row_retencion_iva) {
				// esto es retencionn iva
				if (this.getTipoTC().compareTo("FCN") != 0) {
					transfer = this._evaluate_impuesto(tx, row,
							this.requiere_percepcion_iva, "IVA Retencion");
				}
			}
			if (row == _Interface.row_percepcion_iibb) {
				if (this.getTipoTC().compareTo("FCN") != 0) {
					// esto es percepcion iva
					transfer = this._evaluate_impuesto(tx, row,
							this.requiere_percepcion_ingresos_brutos,
							"Percepcion Ingresos Brutos");
				}
			}
			if (row == _Interface.row_retencion_ganancias) {
				// esto es retencion ganancias
				if (this.getTipoTC().compareTo("FCN") != 0) {
					transfer = this._evaluate_impuesto(tx, row,
							this.requiere_percepcion_ganancias,
							"Retencion de Ganancias");
				}
			}
			if (row == _Interface.row_importe_neto_no_gravado) {
				// esto es importe neto no gravado
				if (this.getTipoTC().compareTo("FCN") != 0) {
					transfer = this._evaluate_impuesto(tx, row,
							this.requiere_neto_no_grabado,
							"Importe neto no gravado");
				}
			}
			if (row == _Interface.row_ajuste_por_redondeo) {
				// esto es ajuste por redondeo
				transfer = this._evaluate_ajuste(tx, row);
			}
			if (row == _Interface.row_impuestos_internos) {
				if (this.getTipoTC().compareTo("FCN") != 0) {
					// esto es ajuste por redondeo
					transfer = this._evaluate_impuesto(tx, row,
							this.requiere_impuestos_internos,
							"Impuestos Internos");
				}
			}

			if (row == _Interface.row_rg3337) {
				// esto es rg 3337
				if (this.getTipoTC().compareTo("FCN") != 0) {
					transfer = this._evaluate_impuesto(tx, row,
							this.requiere_rg3337, "RG 3337");
				}
			}

		} else {
			error("Error en importe");
			transfer = false;
		}
		this.recalculate_total(row, tx);

		if (transfer) {
			if (row == _Interface.row_ajuste_por_redondeo) {
				frame.getJTabbedPane().requestFocusInWindow();
				frame.getJTabbedPane().setSelectedIndex(1);
				frame.getJTable().requestFocusInWindow();
				frame.getJTable().changeSelection(0, 1, false, false);
				frame.getJTable().editCellAt(0, 1);
				frame.getJTable().transferFocus();

			} else {
				frame.getJTable4().changeSelection(row + 1, 2, false, false);
				frame.getJTable4().editCellAt(row + 1, 2);
				frame.getJTable4().transferFocus();
			}

		} else {
			tx.requestFocusInWindow();
			tx.setSelectionStart(0);
			tx.setSelectionEnd(tx.getText().length());

		}

	}

	public boolean _evaluate_ajuste(JTextField tx, int row) {
		boolean transfer = this._evaluate_importe(tx, row);
		if (transfer) {
			frame.getJTabbedPane().requestFocusInWindow();
			frame.getJTabbedPane().setSelectedIndex(1);
			frame.getJTable().requestFocusInWindow();
			frame.getJTable().changeSelection(0, 1, false, false);
			frame.getJTable().editCellAt(0, 1);
			frame.getJTable().transferFocus();
		} else {
			error("Error en importe");
			tx.requestFocusInWindow();
			tx.setSelectionStart(0);
			tx.setSelectionEnd(tx.getText().length());
		}
		return transfer;
	}

	public void recalculate_total(int row, JTextField tx) {
		double total = 0.0;
		// System.out.println("Recalcular Total!");
		for (int i = 0; i < frame.getJTable4().getRowCount(); i++) {
			String importe = "";
			if (i != row) {
				importe = frame.getJTable4().getValueAt(i, 2).toString();

			} else {
				importe = tx.getText();
			}
			importe = importe.replaceAll(",", "");
			// System.out.println("Recalcular Total! "+i+">$"+importe);
			double imp = 0.0;
			try {
				imp = new Double(importe);
			} catch (Exception e) {

			}
			total += imp;
		}
		// System.out.println("Recalcular Total TEXT! >$"+total);
		frame.get_txt_total().setText(new Convertidor().getMoney(total, 2));
		frame.get_txt_total_impuestos().setText(
				new Convertidor().getMoney(total, 2));
	}

	public void update_imputacion(JComboBox cb) {
		String imputacion="";
		try {
			imputacion = cb.getSelectedItem().toString();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
		if (imputacion.compareTo("")!=0){
			Object[][] results = data.getAcountInfo(imputacion);
			String nombre = "";
			if (results != null) {
				if (results.length > 0) {
					nombre = (String) results[0][1];
					frame.get_txt_imputacion_detalle().setText(nombre);
				}
			}	
		}
		
		

		try {
			frame.getJTable4().setValueAt(imputacion, 0, 0);
			frame.getJTable4().setValueAt(imputacion, 8, 0);
			frame.getJTable4().setValueAt(imputacion, 9, 0);
			frame.getJTable4().setValueAt(imputacion, 10, 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void _fill_Imputaciones() {
		String idproveedor = frame.get_txt_idproveedor().getText();
		Object[][] results = data.getImputacionCodigos(idproveedor);
		frame.get_list_imputacion().removeAllItems();
		boolean ok=false;
		if (results != null) {
			if (results.length > 0)	{
				ok=true;
				for (int i = 0; i < results.length; i++) {
					String imputacion = (String) results[i][0];
					if (imputacion != null & frame.get_list_imputacion() != null) {
						frame.get_list_imputacion().addItem(imputacion);
					}

				}		
				frame.get_list_imputacion().setSelectedIndex(0);
			}

		}
		if (!ok){
			error("Debe Configurar la Imputacion de Este Proveedor.");
		}
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
		}

		aplicacion.ventas.catalogo.logic._Logic logic = (aplicacion.ventas.catalogo.logic._Logic) Catalogo
				.getLogic();
		if (logic != null) {
			logic
					.setCargaItems((aplicacion.compras.carga.items.constructor._Constructor) this
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
		}
	}

	public void BuscarArticulo(JTextField ext) {
		if (bArticulo == null) {
			bArticulo = new aplicacion.herramientas.java.buscadores.Articulo(
					this.getConstructor());
		}
		bArticulo.setCaller(this.getConstructor());

		bArticulo.Buscar(ext);
	}

	public void buscarArticulo(JTextField tx) {
		if (vArticulo != null) {
			vArticulo.close();
			vArticulo=null;
		}
		vArticulo = new aplicacion.herramientas.java.visualizadores.Articulo(
				this.getConstructor());
		boolean full=frame.get_chk_articulos_proveedor().isSelected();
		if (!full){
			vArticulo.setIdproveedor(frame.get_txt_idproveedor().getText());
		}
		
		int n = vArticulo.Buscar(tx);
		if (n == 0) {
			vArticulo.close();
			aviso("no hay articulos con ese codigo");
		}
	}

	private double _get_importe_row(int row) {
		double imp = 0.0;
		String _imp = "";
		try {
			_imp = frame.getJTable4().getValueAt(row, 2).toString();
			_imp = _imp.replaceAll(",", "");
			imp = new Double(_imp);
		} catch (Exception e) {

		}
		return imp;
	}

	private String _get_cuenta_row(int row) {

		String cuenta = "";
		try {
			cuenta = frame.getJTable4().getValueAt(row, 0).toString();

		} catch (Exception e) {

		}
		return cuenta;
	}
	

	private String _get_cuenta_row_detalle(int row) {
		String cuenta = "";
		String descripcion = "";
		try {
			cuenta = frame.getJTable4().getValueAt(row, 0).toString();
			Object[][] results=data.getAcountInfo(cuenta);
			if (results!=null){
				if (results.length>0){
					descripcion=(String) results[0][2];
				}
			}
		} catch (Exception e) {

		}
		return descripcion;
	}

	private double _get_importe_subtotal() {
		String impx = frame.getJTable4().getValueAt(0, 2).toString();
		double tmp = this._get_importe(impx);
		return tmp;
	}

	private double _get_importe_iva() {
		String impx = frame.getJTable4().getValueAt(1, 2).toString();
		double tmp = this._get_importe(impx);
		return tmp;
	}

	public void cargar_cpte(String idcomprobante) {
		String tc = this.getTipoTC();
		String cuenta = frame.get_txt_idproveedor().getText();
		String sucursal = idcomprobante.substring(0, 4);
		String numero = idcomprobante.substring(4, 12);
		String letra = idcomprobante.substring(12, 13);
		frame.get_txt_sucursal().setText(sucursal);
		frame.get_txt_sucursal().setEditable(false);
		frame.get_txt_numero().setText(numero);
		frame.get_txt_numero().setEditable(false);
		frame.get_btn_guardar().setEnabled(true);
		frame.get_btn_eliminar().setEnabled(true);
		if (tc.compareTo("FCC") == 0 | tc.compareTo("NCC") == 0) {
			if (data.tiene_remitos_aplicados(cuenta, tc, idcomprobante)) {
				error("Este Comprobante Tiene remitos aplicados");
				cargar_remitos(cuenta);
			}
		}
		if (tc.compareTo("RMC") == 0) {
			if (data.remito_incluido_en_factura(cuenta, tc, idcomprobante)) {
				frame.get_btn_guardar().setEnabled(false);
				frame.get_btn_eliminar().setEnabled(false);

				error("Este Comprobante esta aplicado en una factura. No puede modificarlo ni eliminarlo");

			}
		}
		
		this.select_letra(letra);
		frame.get_list_letra().setEnabled(false);
		Object[][] results = null;
		if (this.getTipoTC().compareTo("FCN") != 0) {
			results = data.getCpte(cuenta, tc, idcomprobante);
		} else {
			results = data.getCpteBeta(cuenta, tc, idcomprobante);
		}

		if (results != null) {
			if (results.length > 0) {
				String fecha_factura = (String) results[0][0];
				String fecha_carga = (String) results[0][1];
				String subtotal = (String) results[0][2];
				String importe = (String) results[0][3];
				String idvendedor = (String) results[0][6];
				frame.get_txt_idcomprobante_asociado().setText("");
				if (this.getTipoTC().compareTo("FCN") == 0) {
					frame.get_txt_idcomprobante_asociado().setEditable(true);
					String comprobante_asociado = (String) results[0][7];
					frame.get_txt_idcomprobante_asociado().setText(comprobante_asociado);
				}
				
				if (idvendedor.compareTo("") != 0) {
					frame.get_txt_idvendedor().setText(idvendedor);
					this.evaluarVendedor(frame.get_txt_idvendedor());
				}

				this.cargar_fotos();
				if (images.size() > 0) {
					this.mostrar_foto(1);
					this.hojas = images.size();
					this.hoja_actual = 1;

				} else {
					hojas = 0;
					hoja_actual = 0;
				}
				frame.get_txt_hoja_total().setText("" + hojas);
				frame.get_txt_hoja_actual().setText("" + hoja_actual);

				Convertidor C = new Convertidor();
				importe = C.getMoney(importe, 2);
				subtotal = C.getMoney(subtotal, 2);
				frame.get_txt_fecha().setText(fecha_factura);
				frame.get_txt_fecha_carga().setText(fecha_carga);
				// frame.get_txt_subtotal().setText(subtotal);
				frame.get_txt_total().setText(importe);

				// crear_tabla_items();
				this.frame.getJTable().requestFocusInWindow();
				this.frame.getJTable().changeSelection(0, 1, false, false);
				this.frame.getJTable().editCellAt(0, 1);
				this.frame.getJTable().transferFocus();
				if (this.getTipoTC().compareTo("FCN") != 0) {
					this.load_impuestos(cuenta, tc, idcomprobante);
				} else {
					this.load_impuestos_beta(cuenta, tc, idcomprobante);
				}

			}

		}
	}

	public boolean _check_articulos_cargados() {
		boolean cargados = false;
		String tc = this.getTipoTC();
		String cuenta = frame.get_txt_idproveedor().getText();
		String idcomprobante = frame.get_txt_idcomprobante().getText();
		Object[][] results = null;
		if (this.getTipoTC().compareTo("FCN") != 0) {
			results = data.getCpteArticulos(cuenta, tc, idcomprobante);
		} else {
			results = data.getCpteArticulosBeta(cuenta, tc, idcomprobante);

		}
		if (results != null) {
			cargados = results.length > 0;

		}
		return cargados;
	}

	
	public boolean check_id_cpte(String idcomprobante) {
		boolean ok = false;
		String letra = frame.get_list_letra().getSelectedItem().toString();
		String sucursal = frame.get_txt_sucursal().getText();
		String numero = frame.get_txt_numero().getText();
		String _idcomprobante = sucursal + numero + letra;
		if (idcomprobante.length() == 13) {
			if (idcomprobante.compareTo(_idcomprobante) == 0) {
				ok = true;
			}
		}
		return ok;
	}

	public void _evaluar_idcomprobante(JTextField tx, boolean shift) {

		String idcomprobante = tx.getText();

		if (idcomprobante.compareTo("") == 0) {

			this.buscarComprobantes(tx, frame.get_txt_idproveedor(), shift);
		} else {
			String idproveedor=frame.get_txt_idproveedor().getText();
			boolean ok = this.check_cpte(idcomprobante);
			boolean carga=ok;
			if (!ok) {
				ok = this.check_id_cpte(idcomprobante);
				if (!ok){
					String tc=this.getTipoTC();
					if (this.existenFotos(idproveedor, tc, idcomprobante)){
						error("El asiento de este comprobante no existe en el sistema.");
						if (preguntar("Confirmar","Quiere recuperar la imagen para cargarlo")){
							
							ok=false;
							carga=ok;
						}	
					}else{
						error("Este Comprobante fue grabado incorrectamente. Avise a sistemas");
					}
					
				}else{
					if (ok) {
						if (confirmar(
								"idcomprobante "
										+ idcomprobante
										+ " inexistente. Confirme para habilitar la carga ",
								3)) {
							// String cuenta =
							// frame.get_txt_idproveedor().getText();
							this.getToday();
							frame.get_btn_guardar().setEnabled(true);
							frame.get_txt_sucursal().setEditable(false);
							frame.get_txt_numero().setEditable(false);
							frame.get_list_letra().setEnabled(false);
							frame.get_txt_fecha().setEnabled(true);
							frame.get_txt_fecha().setEditable(true);
							frame.get_txt_fecha().requestFocusInWindow();
							frame.get_list_tc().setEnabled(false);
							frame.get_txt_idcomprobante().setEditable(false);
							frame.get_btn_guardar().setEnabled(true);
							this.cargarVendedor();
							crear_asiento();
							if (this.getTipoTC().compareTo("FCN")==0){
								frame.get_txt_idcomprobante_asociado().setEditable(true);
								frame.get_txt_idcomprobante_asociado().requestFocusInWindow();	
							}else{
								frame.get_txt_idcomprobante_asociado().setEditable(false);
							}
							

						}
					} else {
						error("Error en idcomprobante " + idcomprobante
								+ " formacion incorrecta ");
						
					}					
				}
				

			} 
			if (carga) {

				if (this._check_articulos_cargados()) {
					ok = false;
					aviso("Ya se cargaron los articulos de este comprobante");
					ok = preguntar("confirmar", "edita articulos cargados?");
					if (!ok) {
						this.clean();
					}
				}
				if (ok) {
					this.cargar_cpte(idcomprobante);
					String cuenta = frame.get_txt_idproveedor().getText();
					if (this._check_articulos_cargados()) {
						this.cargar_articulos(cuenta, this.getTipoTC(),
								idcomprobante);
						this._calculate();

					} else {
						crear_tabla_items();
						this.cargar_pedidos_especiales(cuenta);
						this.cargar_pedidos_proyeccion(cuenta);
						this.cargar_remitos(cuenta);
					}
					frame.get_txt_sucursal().setEditable(false);
					frame.get_txt_numero().setEditable(false);
					frame.get_list_letra().setEnabled(false);
					frame.get_list_tc().setEnabled(false);
					frame.get_txt_idcomprobante().setEditable(false);
					frame.get_txt_idproveedor().setEditable(false);
					frame.get_btn_buscar_proveedor().setEnabled(false);
					frame.get_btn_buscar_cpte().setEnabled(false);
					frame.getJTable().changeSelection(0, 1, false, false);
					frame.getJTable().editCellAt(0, 1);
					frame.getJTable().transferFocus();

				}
			}

		}

	}

	public boolean check_remitos_vs_articulos() {
		String cuenta = frame.get_txt_idproveedor().getText();
		boolean ok = true;
		List<String> remitos = new ArrayList<String>();
		for (int i = 0; i < frame.getJTable().getRowCount(); i++) {
			boolean selected = false;
			try {
				selected = (Boolean) frame.getJTable().getValueAt(i, 0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (selected) {
				String tc = "";
				try {
					tc = (String) frame.getJTable().getValueAt(i, 7);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String remito = "";
				try {
					remito = (String) frame.getJTable().getValueAt(i, 8);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (tc.compareTo("RMC") == 0) {
					if (remito.compareTo("") != 0) {
						if (!remitos.contains(remito)) {
							remitos.add(remito);
						}
					}
				}
			}

		}
		int i = 0;
		while (ok & i < remitos.size()) {
			Object[][] items = data.getRemitosItems(cuenta, remitos.get(i));
			int u = 0;
			while (u < items.length & ok) {
				String idarticulo = (String) items[u][0];
				String cantidad = (String) items[u][2];
				ok = this.check_table_article(idarticulo, remitos.get(i),
						cantidad);
				if (!ok) {
					error("El articulo "
							+ idarticulo
							+ " no se encuentra o no coincide con el remito original RMC-"
							+ remitos.get(i) + ".");
					ok = confirmar(
							"Para Continuar A Pesar de Este Error de Conciliacion Ingrese la secuencia: ",
							3);
				}
				u++;
			}
			i++;
		}

		return ok;
	}

	public boolean check_table_article(String idarticulo, String idcomprobante,
			String cantidad) {

		boolean found = false;
		int i = 0;
		while (i < frame.getJTable().getRowCount() & !found) {
			boolean selected = false;
			try {
				selected = (Boolean) frame.getJTable().getValueAt(i, 0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String _idarticulo = frame.getJTable().getValueAt(i, 1).toString();
			String _cantidad = frame.getJTable().getValueAt(i, 3).toString();
			String _idcomprobante = frame.getJTable().getValueAt(i, 8)
					.toString();
			double _cantidad1 = new Double(_cantidad.replaceAll(",", ""));
			double _cantidado = new Double(cantidad.replaceAll(",", ""));
			found = ((_cantidad1 == _cantidado)
					& (_idarticulo.compareTo(idarticulo) == 0)
					& (_idcomprobante.compareTo(idcomprobante) == 0) & selected);
			i++;
		}
		return found;
	}

	public List<String> getInstruccionesEncabezado() {
		List<String> instrucciones = new ArrayList<String>();
		String importe_s_iva = "0.0";
		String importe_insumos = "0.0";
		String neto_gravado = "0.0";
		String importe_iva = "0.0";
		String cuenta = frame.get_txt_idproveedor().getText();
		String idcomprobante = frame.get_txt_idcomprobante().getText();
		String fecha = frame.get_txt_fecha().getText();
		String sucursal = idcomprobante.substring(0, 4);
		String numero = idcomprobante.substring(4, 12);
		String letra = idcomprobante.substring(12, 13);
		System.out.println("suc:" + sucursal + "-" + numero + "-" + letra);
		String vencimiento = fecha;
		double subtotal = 0.0;
		subtotal = this.get_subtotal();
		String importe = "" + subtotal;
		importe = importe.replace(",", "");
		importe_s_iva = importe_s_iva.replaceAll(",", "");
		importe_iva = importe_iva.replaceAll(",", "");
		importe_insumos = importe_insumos.replaceAll(",", "");
		neto_gravado = neto_gravado.replaceAll(",", "");
		String tc = this.getTipoTC();
		String idvendedor = frame.get_txt_idvendedor().getText();
		System.out.println("////////GRABANDO COMPROBANTE///////////");
		System.out.println("importe total   =" + importe);
		System.out.println("importe neto    =" + importe_s_iva);
		System.out.println("iva             =" + importe_iva);
		System.out.println("insumos         =" + importe_insumos);
		System.out.println("neto gravado    =" + neto_gravado);

		String instruccion = data.getInsert(cuenta, tc, idcomprobante, fecha,
				fecha, vencimiento, importe, importe_s_iva, importe_insumos,
				importe_iva, neto_gravado, sucursal, numero, letra, idvendedor);
		instrucciones.add(instruccion);
		return instrucciones;
	}

	public List<String> getInstruccionesEncabezadoBeta() {
		List<String> instrucciones = new ArrayList<String>();
		String importe_s_iva = "0.0";
		String importe_insumos = "0.0";
		String neto_gravado = "0.0";
		String importe_iva = "0.0";
		String cuenta = frame.get_txt_idproveedor().getText();
		String idcomprobante = frame.get_txt_idcomprobante().getText();
		String fecha = frame.get_txt_fecha().getText();
		String sucursal = idcomprobante.substring(0, 4);
		String numero = idcomprobante.substring(4, 12);
		String letra = idcomprobante.substring(12, 13);
		System.out.println("suc:" + sucursal + "-" + numero + "-" + letra);
		String vencimiento = fecha;
		String importe = frame.get_txt_subtotal_impuestos().getText();
		String idcomprobante_asociado = frame.get_txt_idcomprobante_asociado().getText();
		double total = 0.0;
		try {
			total = new Double(frame.get_txt_total().getText().replaceAll(",",
					""));
		} catch (Exception e) {

		}
		importe = "" + total;
		importe_s_iva = importe_s_iva.replaceAll(",", "");
		importe_iva = importe_iva.replaceAll(",", "");
		importe_insumos = importe_insumos.replaceAll(",", "");
		neto_gravado = neto_gravado.replaceAll(",", "");
		String tc = this.getTipoTC();
		String idvendedor = frame.get_txt_idvendedor().getText();
		System.out.println("////////GRABANDO COMPROBANTE///////////");
		System.out.println("importe total   =" + importe);
		System.out.println("importe neto    =" + importe_s_iva);
		System.out.println("iva             =" + importe_iva);
		System.out.println("insumos         =" + importe_insumos);
		System.out.println("neto gravado    =" + neto_gravado);

		String instruccion = data.getInsertBeta(cuenta, tc, idcomprobante,
				fecha, fecha, vencimiento, importe, importe_s_iva,
				importe_insumos, importe_iva, neto_gravado, sucursal, numero,
				letra, idvendedor,idcomprobante_asociado);
		instrucciones.add(instruccion);

		return instrucciones;
	}

	public void _evaluar_proveedor_articulos(JTextField tx) {
		String idproveedor = tx.getText();
		if (this._evaluar_codigo_proveedor(idproveedor)) {
			this.cargarproveedor_articulos(idproveedor);

			frame.get_txt_idcomprobante().requestFocusInWindow();
		} else {
			error("Error en codigo de proveedor");
			tx.setSelectionStart(0);
			tx.setSelectionEnd(tx.getText().length());
		}
	}
	public String getProveedor(String cuit){
		String proveedor="";
		Object[][] results=data.getProveedorCuit(cuit);
		if (results!=null){
			if (results.length>0){
				proveedor=(String) results[0][0];
			}
		}
		return proveedor;
	}
	
	public boolean evaluate_cai(String cai){
		boolean ok=false;
		if (cai.length()==40){
			String cuit=cai.substring(0,11);
			String tc=cai.substring(11,13);
			String sucursal=cai.substring(13,17);
			String nro=cai.substring(17,31);
			boolean es=false;
			int year=0;
			try{
				year=new Integer(cai.substring(35,39));
			}catch(Exception e){
				
			}
			es=(year>2000);
			String fecha="";
				if (es){
					fecha=cai.substring(31,33)+"-";
					fecha+=cai.substring(33,35)+"-";
					fecha+=cai.substring(35,39);
							
				}else {
					fecha=cai.substring(31,35)+"-";
					fecha+=cai.substring(35,37)+"-";
					fecha+=cai.substring(37,39);
					fecha=new Convertidor().ConvertDate("dd-MM-yyyy", "yyyy-MM-dd", fecha);
				}
			
				if (this.evaluarFecha(fecha)){
					String verif=cai.substring(39,40);
					String idproveedor=this.getProveedor(cuit);
					if (idproveedor.compareTo("")!=0){
						ok=true;
						//frame.get_txt_cai().setText(nro);
						//frame.get_txt_cai_vencimiento().setText(fecha);
						frame.get_txt_idproveedor().setText(idproveedor);
						frame.get_txt_sucursal().setText(sucursal);
						//frame.get_txt_fecha_factura().setEditable(true);
						//frame.get_txt_fecha_contable().setEditable(true);
						this._evaluar_proveedor(frame.get_txt_idproveedor(), false);
						
					}else{
						frame.get_txt_idproveedor().setText("");
					}
				}else{
					frame.get_txt_idproveedor().setText("");
				}
			
		}
		return ok;
	}
	
	public void _evaluar_proveedor(JTextField tx, boolean shift) {
		String idproveedor = tx.getText();
		boolean ok=false;
		if (idproveedor.length()==40){
			
			ok=this.evaluate_cai(idproveedor);
			if (!ok){
				error("VERIFIQUE CAI O CUIT CORRECTAMENTE CARGADO EN SISTEMA DE PROVEEDORES");
			}
		}else{
			if (idproveedor.length()==13){
				String _idproveedor=this.getProveedor(idproveedor.replaceAll("-", ""));
				if (_idproveedor.compareTo("")!=0){
					idproveedor=_idproveedor;
					tx.setText(idproveedor);
				}
				
			}
			int n = -1;
			try {
				n = new Integer(idproveedor);
			} catch (Exception e) {

			}
			if (n > 1) {
				if (this._evaluar_codigo_proveedor(idproveedor)) {
					this.cargarproveedor(idproveedor);

					frame.get_txt_sucursal().requestFocusInWindow();
				} else {
					error("Error en codigo de proveedor");
					tx.setSelectionStart(0);
					tx.setSelectionEnd(tx.getText().length());
				}
			} else {
				if (tx.getText().compareTo("") != 0) {
					this.buscarProveedor(tx);
				} else {
					this.buscarProveedoresComprobantes(tx, shift);
				}

			}			
		}
		


	}

	public double diferencia() {
		double items = 0.0;
		double subtotal = 0.0;
		double suma = 0.0;
		double diferencia = 0.0;
		double unidades = 0.0;
		double rows = 0;
		for (int i = 0; i < frame.getJTable().getRowCount(); i++) {
			boolean selected = false;
			try {
				selected = (Boolean) frame.getJTable().getValueAt(i, 0);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				// e1.printStackTrace();
			}
			if (selected) {
				String _cantidad = "";
				String _precio = "";
				String _descuento = "";
				double cantidad = 0.0;
				double precio = 0.0;
				double descuento = 0.0;
				double importe = 0.0;

				try {
					_cantidad = frame.getJTable().getValueAt(i, 3).toString();
					_precio = frame.getJTable().getValueAt(i, 4).toString();
					_descuento = frame.getJTable().getValueAt(i, 5).toString();
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

				} catch (Exception e) {

				}
				if (importe > 0) {
					rows++;
				}
				suma += importe;
				unidades += cantidad;

			}
		}

		double desc = this._calculate_descuento(frame
				.get_txt_descuento_porcentaje());
		desc = (100 - desc) / 100;
		suma = suma * desc;
		diferencia = Math.abs(suma - this.get_subtotal());
		diferencia = new Convertidor().roundDouble(diferencia, 2);
		return diferencia;
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

	public void stockArticulo(JTextField tx) {
		String idarticulo = tx.getText();
		Object[][] results = data.getArticulo(idarticulo);
		boolean exist = false;
		if (results != null) {
			if (results.length > 0) {
				exist = true;

			}
		}
		if (exist) {
			this.visual_stock(idarticulo);
		} else {
			error("El articulo " + idarticulo + " es inexistente");
		}
	}

	public void aplicar_descuento_articulos() {
		double desc = this._calculate_descuento(frame.get_txt_descuento());
		Convertidor c = new Convertidor();

		if (desc >= 0) {
			int[] indexes = frame.getJTable().getSelectedRows();
			for (int i = 0; i < indexes.length; i++) {
				String _cantidad = "";
				String _precio = "";
				String _descuento = "";
				double cantidad = 0.0;
				double precio = 0.0;

				double importe = 0.0;

				try {
					_cantidad = frame.getJTable().getValueAt(indexes[i], 3)
							.toString();
					_precio = frame.getJTable().getValueAt(indexes[i], 4)
							.toString();

					_cantidad = _cantidad.replace(",", "");
					_precio = _precio.replace(",", "");
					cantidad = new Double(_cantidad);
					precio = new Double(_precio);
					frame.getJTable().setValueAt(c.getMoney(desc, 2),
							indexes[i], 5);
					if (desc >= 0 & desc <= 99.99) {
						precio = precio * (100 - desc) / 100;
						System.out.println(i + " precio=" + precio);
						importe = cantidad * precio;

					} else {
						importe = cantidad * precio;
					}

					frame.getJTable().setValueAt(c.getMoney(importe, 2),
							indexes[i], 6);

				} catch (Exception e) {

				}

			}
		}
	}

	public boolean _calculate() {
		boolean ok = true;
		double items = 0.0;
		double subtotal = 0.0;
		double suma = 0.0;
		double diferencia = 0.0;
		double unidades = 0.0;
		double rows = 0;
		for (int i = 0; i < frame.getJTable().getRowCount(); i++) {
			String _cantidad = "";
			String _idarticulo = "";
			String _precio = "";
			String _descuento = "";
			double cantidad = 0.0;
			double precio = 0.0;
			double descuento = 0.0;
			double importe = 0.0;

			try {
				_idarticulo = frame.getJTable().getValueAt(i, 1).toString();
			} catch (Exception e) {

			}
			if (_idarticulo.compareTo("") != 0) {
				try {
					_cantidad = frame.getJTable().getValueAt(i, 3).toString();
					_precio = frame.getJTable().getValueAt(i, 4).toString();
					_descuento = frame.getJTable().getValueAt(i, 5).toString();
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

				} catch (Exception e) {
					ok = true;
				}
				if (importe > 0) {
					rows++;
				}
				suma += importe;
				unidades += cantidad;
			}

		}

		double desc = (100 - this._calculate_descuento(frame
				.get_txt_descuento_porcentaje())) / 100;
		double suma_con_desc = 0.0;

		double desc_imp = 0.0;
		try {
			desc_imp = new Double(frame.get_txt_descuento_detalle().getText());
		} catch (Exception e) {
			// TODO: handle exception
		}

		desc_imp = suma - suma * desc;

		suma_con_desc = suma - desc_imp;
		diferencia = suma_con_desc - this.getSubtotalPrecargado();

		Convertidor c = new Convertidor();

		String _suma = c.getMoney(suma, 2);
		String _desc_impt = c.getMoney(desc_imp, 2);
		String _suma_con_descuento = c.getMoney(suma_con_desc, 2);
		String _diferencia = c.getMoney(diferencia, 2);
		String _unidades = c.getMoney(unidades, 2);

		frame.get_txt_descuento_detalle().setText(_desc_impt);
		frame.get_txt_subtotal_con_descuento().setText(_suma_con_descuento);
		frame.get_txt_subtotal_diferencia().setText(_diferencia);
		double dif = rows * 0.01;

		if (Math.abs(diferencia) >= dif) {
			frame.get_txt_subtotal_diferencia().setBackground(Color.black);
			frame.get_txt_subtotal_diferencia().setForeground(Color.red);
			// frame.get_btn_guardar().setEnabled(false);
		} else {
			frame.get_txt_subtotal_diferencia().setBackground(Color.black);
			frame.get_txt_subtotal_diferencia().setForeground(Color.green);
			// frame.get_btn_guardar().setEnabled(true);
		}

		frame.get_txt_subtotal_calculado().setText(_suma);
		frame.get_txt_unidades().setText(_unidades);
		return ok;
	}

	public void _eval_item_precio(JTextField tx, int row) {
		String _precio = tx.getText();
		_precio = _precio.replaceAll(",", "");

		double prc = 0.0;
		boolean error = false;
		try {
			prc = new Double(_precio);
		} catch (Exception e) {
			error = true;
		}
		if (!error) {
			if (prc > 0) {
				frame.getJTable().setValueAt(true, row, 0);
				Convertidor Cv = new Convertidor();
				tx.setText(Cv.getMoney(prc, 2));
				frame.getJTable().changeSelection(row, 5, false, false);
				frame.getJTable().editCellAt(row, 5);
				frame.getJTable().transferFocus();
				this._calculate();
			} else {
				error("el precio debe ser mayor a cero");
			}
		} else {
			error("error en precio");
		}

	}

	public void _eval_item_descripcion(JTextField tx, int row) {
		String desc = tx.getText();
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
		multi = (1.0 - multi) * 100;
		BigDecimal bd = new BigDecimal(Double.toString(multi));
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		return bd;
	}

	public void _evaluate_descuento_subtotal(JTextField tx) {
		this._evaluate_descuento(tx);
		frame.get_txt_descuento_detalle().requestFocusInWindow();
		_calculate();
	}

	public void _evaluate_descuento(JTextField tx) {
		double desc = this._calculate_descuento(tx);
		Convertidor Cv = new Convertidor();
		tx.setText(Cv.getMoney(desc, 2));

	}

	public void _evaluate_descuento_detalle(JTextField tx) {
		double desc = 0;
		try {
			desc = new Double(tx.getText());
		} catch (Exception e) {
			// TODO: handle exception
		}
		Convertidor Cv = new Convertidor();
		tx.setText(Cv.getMoney(desc, 2));
		_calculate();
	}

	public void _eval_item_descuento(JTextField tx, int row) {
		String _descuento = tx.getText();
		_descuento = _descuento.replace(",", "");
		double dsc = 0.0;
		double prc = 0.0;
		double qty = 0.0;
		boolean error = false;
		try {
			String _cantidad = frame.getJTable().getValueAt(row, 3).toString();
			String _precio = frame.getJTable().getValueAt(row, 4).toString();
			_cantidad.replace(",", "");
			_precio = _precio.replace(",", "");
			prc = new Double(_precio);
			qty = new Double(_cantidad);
		} catch (Exception e) {

		}
		if (!_descuento.contains("+")) {
			try {
				dsc = new Double(_descuento);
			} catch (Exception e) {

			}
		} else {
			dsc = this.formula(_descuento).doubleValue();
		}

		if (!error) {
			if (dsc >= 0 & dsc <= 99.99) {
				Convertidor Cv = new Convertidor();
				tx.setText(Cv.getMoney(dsc, 2));
				double importe = prc * qty * (100 - dsc) / 100;
				String _total = Cv.getMoney(importe, 2);
				frame.getJTable().setValueAt(_total, row, 6);

				DefaultTableModel model = (DefaultTableModel) this.frame
						.getJTable().getModel();
				if (row == model.getRowCount() - 1) {
					model.setRowCount(model.getRowCount() + 1);

				} else {

				}

				frame.getJTable().changeSelection(row + 1, 1, false, false);
				frame.getJTable().editCellAt(row + 1, 1);
				frame.getJTable().transferFocus();
				this._mover_arriba();
				this._calculate();
			} else {
				tx.setText("0.0");
				tx.requestFocusInWindow();

			}
		} else {
			tx.setText("0.0");
			tx.requestFocusInWindow();
		}
		if (error) {
			tx.requestFocusInWindow();
			tx.setSelectionStart(0);
			tx.setSelectionEnd(tx.getText().length());
		}
	}

	public double _calculate_descuento(JTextField tx) {
		String _descuento = tx.getText();
		return this._calculate_descuento(_descuento);

	}

	public double _calculate_descuento(String _descuento) {
		_descuento = _descuento.replace(",", "");
		double dsc = 0.0;

		if (!_descuento.contains("+")) {
			try {
				dsc = new Double(_descuento);
			} catch (Exception e) {

			}
		} else {
			dsc = this.formula(_descuento).doubleValue();
		}
		return dsc;

	}

	public void _eval_item_cantidad(JTextField tx, int row) {
		String _cantidad = tx.getText();
		_cantidad = _cantidad.replace(",", "");
		double qty = 0.0;
		boolean error = false;
		try {
			qty = new Double(_cantidad);
		} catch (Exception e) {
			error = true;
		}
		if (!error) {
			if (qty > 0) {
				frame.getJTable().setValueAt(true, row, 0);
				Convertidor Cv = new Convertidor();
				tx.setText(Cv.getMoney(qty, 2));
				frame.getJTable().changeSelection(row, 4, false, false);
				frame.getJTable().editCellAt(row, 4);
				frame.getJTable().transferFocus();
				this._calculate();
			} else {
				tx.setText("1.0");
				tx.requestFocusInWindow();
				// error("la cantidad debe ser mayor a cero");
			}
		} else {
			tx.setText("1.0");
			tx.requestFocusInWindow();
			// error("error en cantidad");
		}

	}

	public void _eval_etiquetas_cantidad(JTextField tx, int row) {
		String _cantidad = tx.getText();
		_cantidad = _cantidad.replace(",", "");
		double qty = 0.0;
		boolean error = false;
		try {
			qty = new Double(_cantidad);
		} catch (Exception e) {
			error = true;
		}
		if (!error) {
			if (qty > 0) {
				Convertidor Cv = new Convertidor();
				tx.setText(Cv.getMoney(qty, 2));
				if (row < etiquetas.getJTable().getRowCount() - 1) {
					etiquetas.getJTable().changeSelection(row + 1, 3, false,
							false);
					etiquetas.getJTable().editCellAt(row + 1, 3);
					etiquetas.getJTable().transferFocus();
				}

				this.calcular_etiquetas();
			} else {
				tx.setText("1.0");
				tx.requestFocusInWindow();
				// error("la cantidad debe ser mayor a cero");
			}
		} else {
			tx.setText("1.0");
			tx.requestFocusInWindow();
			// error("error en cantidad");
		}

	}

	public void cargar_proveedor_articulos() {
		String idproveedor = frame.get_txt_idproveedor().getText();
		String descripcion = frame.get_txt_proveedor_descripcion().getText();
		frame.get_txt_proveedor_descripcion().setText(descripcion);
		frame.get_txt_idproveedor_articulos().setText(idproveedor);
		this.cargarproveedor_articulos(idproveedor);
	}

	public void evaluate_pedidos_especiales_selection(JCheckBox chk, int row) {
		if (chk.isSelected()) {
			this.mostrar_pedidos_especiales_items(row);
		} else {
			String idpedido = frame.getJTable1().getValueAt(row, 2).toString();
			error("AVISO: SI desvincula este pedido, automaticamente se quitaran los articulos coincidentes de la carga");
			if (confirmar("Confirme la desvinculacion del pedido " + idpedido
					+ " de la carga: ", 3)) {
				frame.setJTable2(null);
				// falta hacer el desvincular..
			}

		}
	}

	private void create_table_pedidos_especiales(Object[][] results) {
		CustomTable table = new CustomTable();
		CellEditor pce = null;
		Column col = null;

		col = new Column();
		col.setName("");
		col.setWidth(40);
		col.setEditable(false);
		col.setClass(Boolean.class);

		table.addColumn(col);

		col = new Column();
		col.setName("fecha");
		col.setWidth(100);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("TC");
		col.setWidth(40);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("idpedido");
		col.setWidth(100);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("descripcion");
		col.setWidth(120);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("estado");
		col.setWidth(60);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("comprador");
		col.setWidth(130);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("transporte");
		col.setWidth(90);
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
		_table.setName(_Interface._table_pedidos_especiales);
		_table.addMouseListener(this.getConstructor().getMouseListener());
		_table.addKeyListener(this.getConstructor().getKeyListener());
		_table.getTableHeader().addMouseListener(
				this.getConstructor().getMouseListener());
		frame.setJTable1(table.getTable());
	}

	private void create_table_faltantes(Object[][] results) {
		CustomTable table = new CustomTable();
		CellEditor pce = null;
		Column col = null;

		col = new Column();
		col.setName("fecha");
		col.setWidth(100);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("tc");
		col.setWidth(40);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("idpedido");
		col.setWidth(100);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("vendedor");
		col.setWidth(120);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);
		
		col = new Column();
		col.setName("cantidad");
		col.setWidth(80);
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
		_table.setName(_Interface._table_faltantes);
		_table.addMouseListener(this.getConstructor().getMouseListener());
		_table.addKeyListener(this.getConstructor().getKeyListener());
		_table.getTableHeader().addMouseListener(
				this.getConstructor().getMouseListener());
		frame.setJTable7(table.getTable());
	}
	
	private void create_table_remitos(Object[][] results) {
		CustomTable table = new CustomTable();
		CellEditor pce = null;
		Column col = null;

		col = new Column();
		col.setName("");
		col.setWidth(40);
		col.setEditable(true);
		col.setClass(Boolean.class);
		CheckBoxCellEditor cbce = new CheckBoxCellEditor();
		cbce.setName(_Interface._table_remitos_chk);
		cbce.setItemListener(this.getConstructor().getItemListener());
		col.setCellEditor(cbce.getCellCheck());
		table.addColumn(col);

		col = new Column();
		col.setName("fecha");
		col.setWidth(100);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("TC");
		col.setWidth(40);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("idcomprobante");
		col.setWidth(100);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("importe");
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
		_table.setName(_Interface._table_remitos);
		_table.addMouseListener(this.getConstructor().getMouseListener());
		_table.addKeyListener(this.getConstructor().getKeyListener());
		_table.getTableHeader().addMouseListener(
				this.getConstructor().getMouseListener());
		frame.setJTable5(table.getTable());
	}

	private void create_table_pedidos_proyeccion(Object[][] results) {
		CustomTable table = new CustomTable();
		CellEditor pce = null;
		Column col = null;

		col = new Column();
		col.setName("");
		col.setWidth(40);
		col.setEditable(false);
		col.setClass(Boolean.class);

		table.addColumn(col);

		col = new Column();
		col.setName("fecha");
		col.setWidth(100);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("TC");
		col.setWidth(40);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("idpedido");
		col.setWidth(100);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("descripcion");
		col.setWidth(120);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("estado");
		col.setWidth(60);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("comprador");
		col.setWidth(130);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("transporte");
		col.setWidth(90);
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
		_table.setName(_Interface._table_pedidos_proyeccion);
		_table.addMouseListener(this.getConstructor().getMouseListener());
		_table.addKeyListener(this.getConstructor().getKeyListener());
		_table.getTableHeader().addMouseListener(
				this.getConstructor().getMouseListener());
		frame.setJTable3(table.getTable());
	}

	private void create_table_pedidos_especiales_items(Object[][] results) {
		CustomTable table = new CustomTable();
		CellEditor pce = null;
		Column col = null;

		col = new Column();
		col.setName("");
		col.setWidth(40);
		col.setEditable(true);
		col.setClass(Boolean.class);
		col.setClass(Boolean.class);
		CheckBoxCellEditor cbce = new CheckBoxCellEditor();
		cbce.setName(_Interface._table_pedidos_especiales_item_chk);
		cbce.setItemListener(this.getConstructor().getItemListener());
		col.setCellEditor(cbce.getCellCheck());
		table.addColumn(col);

		col = new Column();
		col.setName("idarticulo");
		col.setWidth(120);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("descripcion");
		col.setWidth(240);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("cantidad");
		col.setWidth(60);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("precio");
		col.setWidth(70);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("linea");
		col.setWidth(160);
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

		_table.addMouseListener(this.getConstructor().getMouseListener());
		_table.addKeyListener(this.getConstructor().getKeyListener());
		_table.getTableHeader().addMouseListener(
				this.getConstructor().getMouseListener());
		frame.setJTable2(table.getTable());
	}
	

	private void create_table_remitos_items(Object[][] results) {
		CustomTable table = new CustomTable();

		Column col = null;

		col = new Column();
		col.setName("");
		col.setWidth(40);
		col.setEditable(true);
		col.setClass(Boolean.class);
		CheckBoxCellEditor cbce = new CheckBoxCellEditor();
		cbce.setName(_Interface._table_remitos_item_chk);
		cbce.setItemListener(this.getConstructor().getItemListener());
		col.setCellEditor(cbce.getCellCheck());
		table.addColumn(col);

		col = new Column();
		col.setName("idarticulo");
		col.setWidth(120);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("descripcion");
		col.setWidth(240);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("cantidad");
		col.setWidth(60);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("precio");
		col.setWidth(70);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("linea");
		col.setWidth(160);
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

		_table.addMouseListener(this.getConstructor().getMouseListener());
		_table.addKeyListener(this.getConstructor().getKeyListener());
		_table.getTableHeader().addMouseListener(
				this.getConstructor().getMouseListener());
		frame.setJTable6(table.getTable());
	}
	
	public void cargar_faltantes(String idarticulo) {
		Object[][] results = data.getComposicionPedido(idarticulo);
		boolean ok=false;
		if (results != null) {
			if (results.length > 0) {
				this.create_table_faltantes(results);
				ok=true;
			}
		}
		if (!ok){
			frame.setJTable7(null);
		}
	}
	
	public void cargar_pedidos_especiales(String idcuenta) {
		frame.getJTabbedPane1().setTitleAt(0, "Pedidos PEP");
		Object[][] results = data.getPedidosEspeciales(idcuenta);
		if (results != null) {
			if (results.length > 0) {
				frame.getJTabbedPane1().setTitleAt(0,
						"Pedidos PEP(" + results.length + ")");
				results = this.procesar_pedidos_especiales(results);
				this.create_table_pedidos_especiales(results);
			}
		}
	}

	public List<String> getInstruccionesConciliarRemito() {
		List<String> instrucciones = new ArrayList<String>();
		List<String> remitos_conciliar = this.getRemitos();
		/*
		 * List<String> remitos_regenerar=this.getRemitosViejos(); //primero
		 * elimino lo viejo for (int i=0;i<remitos_regenerar.size();i++){ String
		 * _tc="RMC"; String _idcomprobante=remitos_regenerar.get(i); String
		 * fecha="";
		 * List<String>_instrucciones=this.getInstruccionesRegenerarRemitos(_tc,
		 * _idcomprobante, fecha); for (int j=0;j<_instrucciones.size();j++){
		 * instrucciones.add(_instrucciones.get(j)); } }
		 */
		// grabo nuevamente las conciliaciones
		for (int i = 0; i < remitos_conciliar.size(); i++) {
			String _otc = "RMC";
			String _oidcomprobante = remitos_conciliar.get(i);
			String _osucursal = remitos_conciliar.get(i).substring(0, 4);
			String _onumero = remitos_conciliar.get(i).substring(4, 12);
			String _oletra = remitos_conciliar.get(i).substring(12, 13);
			String _oimporte = "0.0";
			String tc = this.getTipoTC();
			String idcomprobante = frame.get_txt_idcomprobante().getText();
			String sucursal = frame.get_txt_sucursal().getText();
			String numero = frame.get_txt_numero().getText();
			String letra = frame.get_list_letra().getSelectedItem().toString();
			String cuenta = frame.get_txt_idproveedor().getText();
			String q = data.getRemitoAplicacion(cuenta, tc, sucursal, numero,
					letra, _otc, _osucursal, _onumero, _oletra, _oimporte,
					idcomprobante, _oidcomprobante);
			instrucciones.add(q);
			q = data.getRemoveRemitoCompraStock(_otc, _oidcomprobante);
			instrucciones.add(q);
		}
		return instrucciones;
	}

	/*
	 * public List<String> getInstruccionesRegenerarRemitos(){ List<String>
	 * instrucciones=new ArrayList<String>();
	 * 
	 * List<String> remitos_regenerar=this.getRemitosViejos();
	 * 
	 * 
	 * //primero elimino lo viejo for (int i=0;i<remitos_regenerar.size();i++){
	 * String _tc="RMC"; String _idcomprobante=remitos_regenerar.get(i); String
	 * fecha="";
	 * List<String>_instrucciones=this.getInstruccionesRegenerarRemitos(_tc,
	 * _idcomprobante, fecha); for (int j=0;j<_instrucciones.size();j++){
	 * instrucciones.add(_instrucciones.get(j)); } }
	 * 
	 * return instrucciones; }
	 */
	public List<String> getRemitos() {
		List<String> remitos = new ArrayList<String>();
		for (int i = 0; i < frame.getJTable().getRowCount(); i++) {
			boolean selected = false;
			try {
				selected = (Boolean) frame.getJTable().getValueAt(i, 0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
			if (selected) {
				String tc = "";
				try {
					tc = (String) frame.getJTable().getValueAt(i, 7);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String remito = "";
				try {
					remito = (String) frame.getJTable().getValueAt(i, 8);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (tc != null) {
					if (tc.compareTo("RMC") == 0) {
						if (remito.compareTo("") != 0) {
							if (!this.estaRemito(remitos, remito)) {
								remitos.add(remito);
							}

						}
					}
				}

			}

		}
		return remitos;
	}

	public boolean estaRemito(List<String> remitos, String remito) {
		boolean found = false;
		int i = 0;
		while (i < remitos.size() & !found) {
			found = remitos.get(i).compareTo(remito) == 0;
			i++;
		}
		return found;
	}

	public List<String> getRemitosViejos() {
		List<String> remitos = new ArrayList<String>();
		String tc = this.getTipoTC();
		String idcomprobante = frame.get_txt_idcomprobante().getText();
		String sucursal = frame.get_txt_sucursal().getText();
		String numero = frame.get_txt_numero().getText();
		String letra = frame.get_list_letra().getSelectedItem().toString();
		String cuenta = frame.get_txt_idproveedor().getText();
		Object[][] results = data.getRemitos_aplicados(cuenta, tc,
				idcomprobante);
		if (results != null) {
			if (results.length > 0) {
				for (int i = 0; i < results.length; i++) {
					String remito = (String) results[i][1];
					if (!this.estaRemito(remitos, remito)) {
						remitos.add(remito);
					}
				}

			}
		}
		return remitos;
	}

	public void cargar_remitos(String idcuenta) {
		frame.getJTabbedPane().setTitleAt(3, "Remitos");
		Object[][] results = data.getRemitos(idcuenta);
		if (results != null) {
			if (results.length > 0) {
				frame.getJTabbedPane().setTitleAt(3,
						"Remitos(" + results.length + ")");
				results = this.procesar_remitos(results);
				this.create_table_remitos(results);
			}
		}
	}

	public void cargar_pedidos_proyeccion(String idcuenta) {
		frame.getJTabbedPane1().setTitleAt(1, "Pedidos PDP");
		Object[][] results = data.getPedidosProyeccion(idcuenta);
		if (results != null) {
			if (results.length > 0) {
				frame.getJTabbedPane1().setTitleAt(1,
						"Pedidos PDP(" + results.length + ")");
				results = this.procesar_pedidos_proyeccion(results);
				this.create_table_pedidos_proyeccion(results);
			}
		}
	}

	public void addFoto() {
		System.out.println("addfoto");
		final JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(frame);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			this.addFoto(fc.getSelectedFile().getAbsolutePath().toString());

		}
	}

	public void importar_pep_items() {

		JTable table = frame.getJTable2();
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
					String idarticulo = (String) table.getValueAt(i, 1);
					String descripcion = (String) table.getValueAt(i, 2);
					String cantidad = (String) table.getValueAt(i, 3);
					String precio = (String) table.getValueAt(i, 4);
					if (frame.getJTable() == null) {
						this.crear_tabla_items();
					}
					this.agregar2(new Object[] { idarticulo, descripcion,
							cantidad });
				}

			}
		}
	}

	public int existEmpty() {
		int exist = -1;
		int i = 0;
		JTable table = frame.getJTable();
		if (table != null) {
			while (i < table.getRowCount() & exist < 0) {
				String _idarticulo = "";
				try {
					_idarticulo = table.getValueAt(i, 1).toString();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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

	public int existArticulo(String idarticulo, String idcomprobante) {
		int exist = -1;
		int i = 0;
		JTable table = frame.getJTable();
		if (table != null) {
			while (i < table.getRowCount() & exist < 0) {
				String _idarticulo = "";
				try {
					_idarticulo = table.getValueAt(i, 1).toString();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String _idcomprobante = "";
				try {
					_idcomprobante = table.getValueAt(i, 8).toString();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (idarticulo.compareTo(_idarticulo) == 0
						& idcomprobante.compareTo(_idcomprobante) == 0) {
					exist = i;
				}
				i++;
			}
		}
		return exist;
	}

	public void agregar2(Object[] seleccion) {

		String idarticulo = (String) seleccion[0];
		String descripcion = (String) seleccion[1];
		String cantidad = (String) seleccion[2];
		String tc = frame.get_txt_tc().getText();
		String idpedido = frame.get_txt_idpedido().getText();
		// String cantidad="1.0";
		String descuento = frame.get_txt_descuento().getText();
		if (descuento.compareTo("") == 0) {
			descuento = "0.0";
		}

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
				int exist = -1;
				if (idarticulo.compareTo("*") == 0) {

				} else {
					exist = this.existArticulo(idarticulo);
				}

				String costo = results[0][2].toString();
				String precio = results[0][1].toString();
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
					double _descuento = 0.0;
					double _costo = 0.0;
					double _total = 0.0;
					double _cantidad = 0.0;
					try {
						_costo = new Double(costo.replaceAll(",", ""));
						_descuento = this._calculate_descuento(descuento);
						_cantidad = new Double(cantidad.replaceAll(",", ""));
						_total = _costo * _cantidad;
						_total = _total * (100 - _descuento) / 100;
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					table.setValueAt(""
							+ new Convertidor().roundDouble(_costo, 2), row, 4);
					table.setValueAt(descuento, row, 5);
					table.setValueAt(""
							+ new Convertidor().roundDouble(_total, 2), row, 6);
					table.setValueAt(tc, row, 7);
					table.setValueAt(idpedido, row, 8);

					// this.eval_variables(row, cantidad, precio, descuento);
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

	public void agregar4(Object[] seleccion) {

		String idarticulo = (String) seleccion[0];
		String descripcion = (String) seleccion[1];
		String cantidad = "1";
		String tc = frame.get_txt_tc().getText();
		String idpedido = frame.get_txt_idpedido().getText();
		// String cantidad="1.0";
		String descuento = frame.get_txt_descuento().getText();
		if (descuento.compareTo("") == 0) {
			descuento = "0.0";
		}

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
				int exist = -1;
				if (idarticulo.compareTo("*") == 0) {

				} else {
					exist = this.existArticulo(idarticulo);
				}

				String costo = results[0][2].toString();
				String precio = results[0][1].toString();
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
					double _descuento = 0.0;
					double _costo = 0.0;
					double _total = 0.0;
					double _cantidad = 0.0;
					try {
						_costo = new Double(costo.replaceAll(",", ""));
						_descuento = this._calculate_descuento(descuento);
						_cantidad = new Double(cantidad.replaceAll(",", ""));
						_total = _costo * _cantidad;
						_total = _total * (100 - _descuento) / 100;
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					table.setValueAt(""
							+ new Convertidor().roundDouble(_costo, 2), row, 4);
					table.setValueAt(descuento, row, 5);
					table.setValueAt(""
							+ new Convertidor().roundDouble(_total, 2), row, 6);
					table.setValueAt(tc, row, 7);
					table.setValueAt(idpedido, row, 8);

					// this.eval_variables(row, cantidad, precio, descuento);
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

	public void agregar3(Object[] seleccion) {

		String idarticulo = (String) seleccion[0];
		String descripcion = (String) seleccion[1];
		String cantidad = (String) seleccion[2];
		String tc = (String) seleccion[3];
		String idcomprobante = (String) seleccion[4];
		System.out.println("Agregar3 " + idarticulo + " " + cantidad + " " + tc
				+ " " + idcomprobante);
		// String cantidad="1.0";
		String descuento = frame.get_txt_descuento().getText();
		if (descuento.compareTo("") == 0) {
			descuento = "0.0";
		}

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
				int exist = -1;
				if (idarticulo.compareTo("*") == 0) {

				} else {
					exist = this.existArticulo(idarticulo, idcomprobante);
				}

				String costo = results[0][2].toString();
				String precio = results[0][1].toString();
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
					double _descuento = 0.0;
					double _costo = 0.0;
					double _total = 0.0;
					double _cantidad = 0.0;
					try {
						_costo = new Double(costo.replaceAll(",", ""));
						_descuento = this._calculate_descuento(descuento);
						_cantidad = new Double(cantidad.replaceAll(",", ""));
						_total = _costo * _cantidad;
						_total = _total * (100 - _descuento) / 100;
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					table.setValueAt(""
							+ new Convertidor().roundDouble(_costo, 2), row, 4);
					table.setValueAt(descuento, row, 5);
					table.setValueAt(""
							+ new Convertidor().roundDouble(_total, 2), row, 6);
					table.setValueAt(tc, row, 7);
					table.setValueAt(idcomprobante, row, 8);

					// this.eval_variables(row, cantidad, precio, descuento);
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

	public Object[][] procesar_pedidos_especiales(Object[][] results) {
		Object[][] tmp = new Object[results.length][results[0].length + 1];
		for (int i = 0; i < results.length; i++) {
			tmp[i][0] = false;
			for (int j = 0; j < results[0].length; j++) {
				tmp[i][j + 1] = results[i][j];
			}
		}
		return tmp;
	}

	public Object[][] procesar_remitos(Object[][] results) {
		Object[][] tmp = new Object[results.length][results[0].length + 1];
		for (int i = 0; i < results.length; i++) {
			tmp[i][0] = false;
			for (int j = 0; j < results[0].length; j++) {
				tmp[i][j + 1] = results[i][j];
			}
		}
		return tmp;
	}

	public Object[][] procesar_pedidos_proyeccion(Object[][] results) {
		Object[][] tmp = new Object[results.length][results[0].length + 1];
		for (int i = 0; i < results.length; i++) {
			tmp[i][0] = false;
			for (int j = 0; j < results[0].length; j++) {
				tmp[i][j + 1] = results[i][j];
			}
		}
		return tmp;
	}

	public Object[][] procesar_pedidos_especiales_items(Object[][] results) {
		Object[][] tmp = new Object[results.length][results[0].length + 1];
		for (int i = 0; i < results.length; i++) {
			tmp[i][0] = false;
			for (int j = 0; j < results[0].length; j++) {
				tmp[i][j + 1] = results[i][j];
			}
		}
		return tmp;
	}

	public Object[][] procesar_remitos_items(Object[][] results) {
		Object[][] tmp = new Object[results.length][results[0].length + 1];
		for (int i = 0; i < results.length; i++) {
			tmp[i][0] = false;
			for (int j = 0; j < results[0].length; j++) {
				tmp[i][j + 1] = results[i][j];
			}
		}
		return tmp;
	}

	public void mostrar_pedidos_especiales_items(int row) {
		String tc = frame.getJTable1().getValueAt(row, 2).toString();
		String idpedido = frame.getJTable1().getValueAt(row, 3).toString();
		frame.get_txt_tc().setText(tc);
		frame.get_txt_idpedido().setText(idpedido);
		Object[][] results = data.getPedidosEspecialesItems(idpedido);
		results = this.procesar_pedidos_especiales_items(results);
		if (results != null) {
			if (results.length > 0) {
				this.create_table_pedidos_especiales_items(results);
			}
		}
	}

	public void mostrar_remitos_items(int row) {
		String tc = frame.getJTable5().getValueAt(row, 2).toString();
		String cuenta = frame.get_txt_idproveedor().getText();
		String idcomprobante = frame.getJTable5().getValueAt(row, 3).toString();
		frame.get_txt_rmc_idcomprobante().setText(idcomprobante);
		frame.get_txt_rmc_tc().setText("RMC");
		Object[][] results = data.getRemitosItems(cuenta, idcomprobante);
		results = this.procesar_remitos_items(results);
		if (results != null) {
			if (results.length > 0) {
				this.create_table_remitos_items(results);
			}
		}
	}

	public void mostrar_pedidos_proyectados_item(int row) {
		String tc = frame.getJTable3().getValueAt(row, 2).toString();
		String idpedido = frame.getJTable3().getValueAt(row, 3).toString();
		frame.get_txt_tc().setText(tc);
		frame.get_txt_idpedido().setText(idpedido);
		Object[][] results = data.getPedidosProyectadosItems(idpedido);
		results = this.procesar_pedidos_especiales_items(results);
		if (results != null) {
			if (results.length > 0) {
				this.create_table_pedidos_especiales_items(results);
			}
		}
	}

	public boolean check_articulos_validos() {
		boolean ok = true;
		int selections = 0;
		int items = 0;
		double suma_selections = 0.0;
		double suma_items = 0.0;
		boolean suspendidoc = false;
		int i = 0;
		while (i < frame.getJTable().getRowCount() & !suspendidoc) {
			boolean selected = false;
			try {
				selected = (Boolean) frame.getJTable().getValueAt(i, 0);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				// e1.printStackTrace();
			}
			if (selected) {
				String articulo = (String) frame.getJTable().getValueAt(i, 1);
				if (articulo.compareTo("*") != 0) {
					suspendidoc = data.bloqueado(articulo);
					if (suspendidoc) {
						error("El Articulo " + articulo
								+ " esta bloqueado para la venta.");
					}

				}

			}
			i++;
		}
		ok = !suspendidoc;
		return ok;
	}

	public void seleccionar(boolean b) {
		if (frame.getJTable() != null) {
			for (int i = 0; i < frame.getJTable().getRowCount(); i++) {
				frame.getJTable().setValueAt(b, i, 0);
			}
		}
		this._calculate();
	}

	public void seleccionar_especiales(boolean b) {
		if (frame.getJTable2() != null) {
			for (int i = 0; i < frame.getJTable2().getRowCount(); i++) {
				frame.getJTable2().setValueAt(b, i, 0);
			}
		}

	}

	public void seleccionar_pep_item(JCheckBox chk, int row) {
		JTable table = frame.getJTable2();
		if (frame.get_chk_incorporar_en_orden().isSelected()) {
			String idarticulo = (String) table.getValueAt(row, 1);
			String descripcion = (String) table.getValueAt(row, 2);
			String cantidad = (String) table.getValueAt(row, 3);
			String precio = (String) table.getValueAt(row, 4);
			if (frame.getJTable() == null) {
				this.crear_tabla_items();
			}
			this.agregar2(new Object[] { idarticulo, descripcion, cantidad });

		}
	}

	public void seleccionar_remito_item(JCheckBox chk, int row) {
		JTable table = frame.getJTable6();
		if (frame.get_chk_incorporar_en_orden().isSelected()) {
			String idarticulo = (String) table.getValueAt(row, 1);
			String descripcion = (String) table.getValueAt(row, 2);
			String cantidad = (String) table.getValueAt(row, 3);
			String precio = (String) table.getValueAt(row, 4);
			String tc = frame.get_txt_rmc_tc().getText();
			String idcomprobante = frame.get_txt_rmc_idcomprobante().getText();
			if (frame.getJTable() == null) {
				this.crear_tabla_items();
			}
			this.agregar3(new Object[] { idarticulo, descripcion, cantidad, tc,
					idcomprobante });

		}
	}

	public void seleccionar_remito(JCheckBox chk, int row) {
		// JTable table = frame.getJTable6();
		String tc = frame.getJTable5().getValueAt(row, 2).toString();
		String cuenta = frame.get_txt_idproveedor().getText();
		String idcomprobante = frame.getJTable5().getValueAt(row, 3).toString();
		frame.get_txt_rmc_idcomprobante().setText(idcomprobante);
		frame.get_txt_rmc_tc().setText("RMC");
		Object[][] results = data.getRemitosItems(cuenta, idcomprobante);
		results = this.procesar_remitos_items(results);
		if (results != null) {
			if (results.length > 0) {
				this.create_table_remitos_items(results);
			}
		}

		if (chk.isSelected()) {
			for (int i = 0; i < frame.getJTable6().getRowCount(); i++) {
				String idarticulo = (String) frame.getJTable6()
						.getValueAt(i, 1);
				String descripcion = (String) frame.getJTable6().getValueAt(i,
						2);
				String cantidad = (String) frame.getJTable6().getValueAt(i, 3);
				String precio = (String) frame.getJTable6().getValueAt(i, 4);
				if (frame.getJTable() == null) {
					this.crear_tabla_items();
				}
				this.agregar3(new Object[] { idarticulo, descripcion, cantidad,
						tc, idcomprobante });
			}
		} else {
			for (int i = frame.getJTable().getRowCount() - 1; i >= 0; i--) {
				String _tc = frame.getJTable().getValueAt(i, 10).toString();
				String _idcomprobante = frame.getJTable().getValueAt(i, 11)
						.toString();
				DefaultTableModel model = (DefaultTableModel) frame.getJTable()
						.getModel();
				if (tc.compareTo(_tc) == 0
						& idcomprobante.compareTo(_idcomprobante) == 0) {
					model.removeRow(i);
				}
			}
		}

	}

	public void _evaluate_idarticulo(JTextField tx, int row) {
		if (row < 0) {
			row = 0;
		}
		String idarticulo = tx.getText();
		Object[][] results = data.getArticulo(idarticulo);
		boolean exist = false;
		double descuento = 0.0;
		boolean bloqueado = false;
		int equiv = 0;
		try {
			descuento = new Double(frame.get_txt_descuento().getText());
		} catch (Exception e) {

		}
		frame.get_txt_descuento().setText("" + descuento);
		if (results != null) {
			if (results.length > 0) {
				exist = true;
				String stock = (String) results[0][6];
				String suspendido = (String) results[0][5];

				double _stock = 0.0;
				try {
					_stock = new Double(stock);
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				boolean ok = true;
				if (suspendido.compareTo("1") == 0) {

					if (idarticulo.compareTo(equivalencia) != 0) {
						equivalencia = idarticulo;
						System.out.println("Buscar Equivalencia para "
								+ idarticulo);
						ok = false;
						equiv = this.buscarEquivalencia(tx);

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
					double _precio = 0.0;
					try {
						_precio = new Double(precio);
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String costo = (String) results[0][2];
					double _costo = 0.0;
					try {
						_costo = new Double(precio);
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					boolean empty = true;
					try {
						empty = frame.getJTable().getValueAt(row, 2).toString()
								.compareTo("") == 0;
					} catch (Exception e) {

					}
					boolean doit = true;
					if (!empty) {
						if (preguntar("Confirmar", "Sobrescribe este renglon?")) {
							doit = true;
						} else {
							doit = false;
						}
					} else {
						doit = true;
					}
					System.out.println(_precio);

					if (doit) {
						frame.getJTable().setValueAt(descripcion, row, 2);
						frame.getJTable().setValueAt("1.0", row, 3);
						frame.getJTable().setValueAt(costo, row, 4);
						frame.getJTable().setValueAt("" + descuento, row, 5);
						frame.getJTable().setValueAt(costo, row, 6);
					} else {
						frame.getJTable().changeSelection(row, 2, false, false);
						frame.getJTable().editCellAt(row, 2);
						frame.getJTable().transferFocus();
					}
					
					
					
					fillExtra(idarticulo);
					_calculate();
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
					+ " no existe. Desdea Crearlo?")) {
				this.goMa_Articulos(tx.getText());
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

	public void _eval_item_articulo(JTextField tx, int row) {
		String aux = "";
		String prefijo = frame.get_txt_prefijo().getText();
		tx.setBackground(Color.GREEN);
		try {
			aux = tx.getText();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		tx.requestFocusInWindow();
		if (aux.compareTo("") != 0) {
			if (aux.compareTo("*") == 0) {
				frame.getJTable().changeSelection(row, 2, false, false);
				frame.getJTable().editCellAt(row, 2);
				frame.getJTable().transferFocus();
			} else {
				if (aux.compareTo(prefijo) == 0) {
					tx.setText("*");
					frame.getJTable().setValueAt("", row, 2);
					frame.getJTable().changeSelection(row, 2, false, false);
					frame.getJTable().editCellAt(row, 2);
					frame.getJTable().transferFocus();
				} else {
					if (aux.length() > 4) {
						if (aux.substring(3, 4).compareTo("-") == 0) {
							_evaluate_idarticulo(tx, row);
						} else {
							if (frame.get_chk_autocompletar().isSelected()) {
								this.buscarArticulo(tx);
							}
						}
					} else {
						if (frame.get_chk_autocompletar().isSelected()) {
							this.buscarArticulo(tx);
						}

					}
				}

			}
		} else {

			if (prefijo.compareTo("") != 0) {
				tx.setText(prefijo);
				tx.setCaretPosition(prefijo.length());
				tx.requestFocusInWindow();
			} else {
				tx.setText("*");
				frame.getJTable().setValueAt(aux, row, 2);
				frame.getJTable().changeSelection(row, 2, false, false);
				frame.getJTable().editCellAt(row, 2);
				frame.getJTable().transferFocus();
			}

		}
	}

	private void _fillLineas(String idproveedor) {
		Object[][] results = data.getLineas(idproveedor);
		if (results != null) {
			if (results.length > 0) {
				frame.get_list_lineas().removeAllItems();
				frame.get_list_lineas().addItem("");
				for (int i = 0; i < results.length; i++) {
					String linea = (String) results[i][0];
					frame.get_list_lineas().addItem(linea);
				}
			}
		}
	}

	public void cargarproveedor(String idproveedor) {
		Object[][] results = data.getProveedor(idproveedor);
		if (results != null) {
			if (results.length > 0) {

				String codigo = (String) results[0][0];
				String descripcion = (String) results[0][1];
				String domicilio = (String) results[0][2];
				String condicion_iva = (String) results[0][3];
				String condicion_iva_detalle = (String) results[0][4];
				String cuit = (String) results[0][5];
				String condicion_compra = (String) results[0][6];
				String condicion_compra_detalle = (String) results[0][7];

				String iibb = (String) results[0][8];
				String iva = (String) results[0][9];
				String gan = (String) results[0][10];
				String neto = (String) results[0][11];
				String iva10 = (String) results[0][12];
				String iva27 = (String) results[0][13];
				String iva21 = (String) results[0][17];
				String articulos = (String) results[0][18];
				String rg3337 = (String) results[0][19];
				String impuestos_internos = (String) results[0][20];
				String alicuota_impuestos_internos = (String) results[0][21];
				String alicuota_ingresos_brutos = (String) results[0][22];
				System.out.println("alicuota ingresos brutos del proveedor "+alicuota_ingresos_brutos);
				if (alicuota_ingresos_brutos.compareTo("")!=0){
					try {
						this.alic_iibb=new Double(alicuota_ingresos_brutos);
					} catch (NumberFormatException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}	
				}
				String alicuota_percepcion_iva = (String) results[0][23];
				String tc = (String) results[0][24];
				String _etiquetas = (String) results[0][25];
				String _actualiza = (String) results[0][26];
				String descuento = (String) results[0][27];
				int upd = -1;
				try {
					upd = new Integer(_actualiza);
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					// e1.printStackTrace();
				}
				if (upd == -1) {
					error("Configure este proveedor para definir la carga de impuestos correctamente");
				}
				int etq = 0;
				try {
					etq = new Integer(_etiquetas);
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					// e1.printStackTrace();
				}
				frame.get_list_actualiza().setSelectedIndex(upd);
				frame.get_list_etiquetas().setSelectedIndex(etq);
				frame.get_txt_proveedor_descripcion().setText(descripcion);
				frame.get_btn_buscar_cpte().setEnabled(true);
				frame.get_txt_numero().setEditable(true);
				frame.get_txt_sucursal().setEditable(true);
				frame.get_list_letra().setEnabled(true);
				frame.get_txt_idcomprobante().setEditable(true);
				frame.get_txt_descuento().setText(descuento);
				try {
					this.requiere_percepcion_ingresos_brutos = new Integer(iibb);
					this.requiere_percepcion_iva = new Integer(iva);
					this.requiere_percepcion_ganancias = new Integer(gan);
					this.requiere_neto_no_grabado = new Integer(neto);
					this.requiere_iva10 = new Integer(iva10);
					this.requiere_iva27 = new Integer(iva27);
					this.requiere_iva21 = new Integer(iva21);
					this.permite_articulos = new Integer(articulos);
					this.requiere_impuestos_internos = new Integer(
							impuestos_internos);
					this.requiere_rg3337 = new Integer(rg3337);

				} catch (Exception e) {

				}
				frame.get_chk_carga_articulos().setSelected(
						permite_articulos == 1);
				_fill_Imputaciones();
			}
		}
	}

	private void select_letra(String letra) {
		for (int i = 0; i < frame.get_list_letra().getItemCount(); i++) {
			if (frame.get_list_letra().getItemAt(i).toString().compareTo(letra) == 0) {
				frame.get_list_letra().setSelectedIndex(i);

			}
		}

	}

	public void select_tc(String tc) {
		if (frame.get_list_tc().getItemCount() > 0) {
			if (tc.compareTo("FCC") == 0) {
				frame.get_list_tc().setSelectedIndex(0);
			}
			if (tc.compareTo("NCC") == 0) {
				frame.get_list_tc().setSelectedIndex(1);
			}
			if (tc.compareTo("RMC") == 0) {
				frame.get_list_tc().setSelectedIndex(2);
			}
			if (tc.compareTo("FCN") == 0) {
				frame.get_list_tc().setSelectedIndex(3);
			}
		}

	}

	private void selectImputacion(String imputacion) {
		for (int i = 0; i < frame.get_list_imputacion().getItemCount(); i++) {
			if (frame.get_list_imputacion().getItemAt(i).toString().compareTo(
					imputacion) == 0) {
				frame.get_list_imputacion().setSelectedIndex(i);

			}
		}

	}

	private void crear_asiento() {

		String idproveedor = frame.get_txt_idproveedor().getText();
		String proveedor_descripcion = frame.get_txt_proveedor_descripcion()
				.getText();
		Object[][] tmp = null;
		Object[][] results = null;

		String imputacion = "11301";
		boolean error = false;
		if (frame.get_list_imputacion().getItemCount() > 0) {
			try {
				imputacion = frame.get_list_imputacion().getSelectedItem()
						.toString();
			} catch (Exception e) {
				error = true;
			}
		}
		if (error) {
			aviso("NO SE CONFIGURA UNA CUENTA DE IMPUTACION PARA EL PROVEEDOR. SE UTLIZA 13101 POR DEFECTO");
		}
		results = new Object[][] { { imputacion, "Subtotal" },
				{ "11203", "Iva   21%" },
				{ "11261", "Iva 10.5%" },
				{ "11262", "Iva   27%" },
				{ "11263", "Retencion Iva " },
				{ "11208", "RG 3337" },
				{ "11209", "Percepcion Ingresos Brutos " },
				{ "11210", "Retencion Ganancias " },
				{ imputacion, "Importe Neto No Gravado " },
				{ imputacion, "Impuestos Internos " },
				{ imputacion, "Ajuste por redondeo " },

		};
		String impuesto="";
		impuesto=data.getCuentaImpuesto("iva21");
		if (impuesto.compareTo("")!=0){
			results[1][0]=impuesto;
		}
		impuesto=data.getCuentaImpuesto("iva10.5");
		if (impuesto.compareTo("")!=0){
			results[2][0]=impuesto;
		}
		impuesto=data.getCuentaImpuesto("iva27");
		if (impuesto.compareTo("")!=0){
			results[3][0]=impuesto;
		}
		impuesto=data.getCuentaImpuesto("retencion_iva");
		if (impuesto.compareTo("")!=0){
			results[4][0]=impuesto;
		}
		impuesto=data.getCuentaImpuesto("rg3337");
		if (impuesto.compareTo("")!=0){
			results[5][0]=impuesto;
		}
		impuesto=data.getCuentaImpuesto("iibb");
		if (impuesto.compareTo("")!=0){
			results[6][0]=impuesto;
		}
		impuesto=data.getCuentaImpuesto("ganancias");
		if (impuesto.compareTo("")!=0){
			results[7][0]=impuesto;
		}
		if (results != null) {
			if (results.length > 0) {
				tmp = new Object[results.length][3];
				for (int i = 0; i < results.length; i++) {
					String _imputacion = (String) results[i][0];
					String imputacion_alias = (String) results[i][1];
					Object[] tmpx = new Object[] { _imputacion,
							imputacion_alias, "0.0" };
					tmp[i] = tmpx;
				}

			}
		}
		this.create_table_impuestos(tmp);

	}

	private void load_impuestos(String idproveedor, String tc,
			String idcomprobante) {
		Object[][] results = data.getImpuestos(idproveedor, tc, idcomprobante);
		boolean ok = false;
		if (results != null) {
			if (results.length > 0) {
				ok = true;
				this.crear_asiento();

				for (int i = 0; i < results.length; i++) {
					String _secuencia = (String) results[i][0];
					String _cuenta = (String) results[i][1];
					String _importe = (String) results[i][2];
					int sec = -1;
					double importe = 0.0;
					try {
						sec = new Integer(_secuencia);
						importe = new Double(_importe.replaceAll(",", ""));
					} catch (Exception e) {

					}
					if (i > 0) {
						if (sec >= 0) {
							sec--;
							Convertidor C = new Convertidor();
							frame.getJTable4().setValueAt(
									C.getMoney(importe, 2), sec, 2);
							if (sec == 1 | sec == 8 | sec == 9 | sec == 10) {
								frame.getJTable4().setValueAt(_cuenta, sec, 0);
							}
						}

					}

				}
				String _imputacion = (String) results[1][1];
				System.out.println("select imputacion " + _imputacion);
				this.selectImputacion(_imputacion);
				double subtotal = this.get_subtotal();
				frame.get_txt_subtotal_impuestos().setText(
						new Convertidor().getMoney(subtotal, 2));
				this.recalculate_total(-1, null);
			}
		}
		if (!ok) {
			this.crear_asiento();
		}
	}

	private void load_impuestos_beta(String idproveedor, String tc,
			String idcomprobante) {
		Object[][] results = data.getImpuestosBeta(idproveedor, tc,
				idcomprobante);
		boolean ok=false;
		if (results != null) {
			if (results.length > 0) {
				ok=true;
				this.crear_asiento();

				for (int i = 0; i < results.length; i++) {
					String _secuencia = (String) results[i][0];
					String _cuenta = (String) results[i][1];
					String _importe = (String) results[i][2];
					int sec = -1;
					double importe = 0.0;
					try {
						sec = new Integer(_secuencia);
						importe = new Double(_importe.replaceAll(",", ""));
					} catch (Exception e) {

					}
					if (i > 0) {
						if (sec >= 0) {
							sec--;
							Convertidor C = new Convertidor();
							frame.getJTable4().setValueAt(
									C.getMoney(importe, 2), sec, 2);
							if (sec == 1 | sec == 8 | sec == 9 | sec == 10) {
								frame.getJTable4().setValueAt(_cuenta, sec, 0);
							}
						}

					}

				}
				String _imputacion = (String) results[1][1];
				System.out.println("select imputacion " + _imputacion);
				this.selectImputacion(_imputacion);
				double subtotal = this.get_subtotal();
				frame.get_txt_subtotal_impuestos().setText(
						new Convertidor().getMoney(subtotal, 2));
				this.recalculate_total(-1, null);
			}
		}
		if (!ok){
			this.crear_asiento();
		}
	}

	public void cargarproveedor_articulos(String idproveedor) {
		Object[][] results = data.getProveedor(idproveedor);
		if (results != null) {
			if (results.length > 0) {

				String codigo = (String) results[0][0];
				String descripcion = (String) results[0][1];
				String domicilio = (String) results[0][2];
				String condicion_iva = (String) results[0][3];
				String condicion_iva_detalle = (String) results[0][4];
				String cuit = (String) results[0][5];
				String condicion_compra = (String) results[0][6];
				String condicion_compra_detalle = (String) results[0][7];

				frame.get_txt_proveedor_articulos_descripcion().setText(
						descripcion);
				this._fillLineas(idproveedor);

			}
		}
	}

	private void crear_tabla_items() {
		Object[][] results = new Object[][] { { false, "", "", "", "", "", "",
				"", "" } };
		this.create_table(results);
	}

	public void _mover_arriba() {
		int x = this.getInc();
		frame.getCanvas().move_Y(-x);
	}

	public void _mover_abajo() {
		int x = this.getInc();
		frame.getCanvas().move_Y(x);
	}

	public void _mover_izquierda() {
		int x = this.getInc();
		frame.getCanvas().move_X(-x);
	}

	public void _mover_derecha() {
		int x = this.getInc();
		frame.getCanvas().move_X(x);
	}

	public void _mover_zoomin() {
		int x = this.getZoomInc();
		frame.getCanvas().zoom_add(x);
	}

	public void _mover_zoomout() {
		int x = this.getZoomInc();
		frame.getCanvas().zoom_rem(x);

	}

	public void _move_down(int row) {
		/*
		 * frame.getJTable().changeSelection(row+1, 1, false,false);
		 * frame.getJTable().editCellAt(row+1, 1);
		 * frame.getJTable().transferFocus();
		 */
		if (row < frame.getJTable().getRowCount() - 1) {
			row++;
			this._mover_arriba();
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
			this._mover_abajo();
			this.showRow(row);
		}

	}

	public void _eval_item_total(JTextField tx, int row) {
		DefaultTableModel model = (DefaultTableModel) this.frame.getJTable()
				.getModel();
		if (row == model.getRowCount() - 1) {
			model.setRowCount(model.getRowCount() + 1);

		} else {

		}
		try {
			frame.getJTable().changeSelection(row + 1, 1, false, false);
			frame.getJTable().editCellAt(row + 1, 1);
			frame.getJTable().transferFocus();
			this._mover_arriba();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void cancel() {
		if (preguntar("confirmar", "Desea Cancelar la carga del comprobante?")) {
			clean();

		}
	}

	public void centrar_imagen() {
		if (frame.getCanvas() != null) {
			frame.getCanvas().maximizar();
		}
	}

	public void clean() {
		images = new ArrayList<BufferedImage>();
		frame.get_txt_fecha().setText("");
		frame.get_txt_fecha_carga().setText("");
		frame.get_txt_idcomprobante().setText("");
		frame.get_txt_idproveedor().setText("");
		if (frame.getCanvas() != null) {
			frame.getCanvas().resetVars();
		}
		if (images!=null){
			for (int i=0;i<images.size();i++){
				images.remove(i);
			}
			this.images=null;	
		}
		cantidadArticulos=new ArrayList<Object[]>();
		
		this.images=new ArrayList<BufferedImage>();
		frame.get_txt_idcomprobante_asociado().setText("");
		frame.get_txt_proveedor_descripcion().setText("");
		frame.get_txt_subtotal_impuestos().setText("");
		frame.get_txt_subtotal_calculado().setText("");
		frame.get_txt_subtotal_diferencia().setText("");
		frame.get_txt_total().setText("");
		frame.get_txt_unidades().setText("");
		frame.get_txt_zoom().setText("10");
		frame.get_txt_rmc_idcomprobante().setText("");
		frame.get_txt_rmc_tc().setText("");
		frame.get_txt_idvendedor().setText("");
		frame.get_txt_idcreador().setText("");
		frame.get_txt_vendedor_descripcion().setText("");
		frame.get_txt_creador().setText("");
		
		frame.get_chk_carga_articulos().setSelected(false);
		frame.get_txt_total_impuestos().setText("");
		frame.get_txt_total().setText("");
		frame.get_list_imputacion().removeAllItems();
		frame.get_txt_tc().setText("");
		frame.get_txt_idcomprobante().setText("");
		frame.get_list_tc().setEnabled(true);
		frame.getJTabbedPane().setTitleAt(2, "Pedidos");
		frame.getJTabbedPane().setTitleAt(3, "Remitos");
		frame.getCanvas().setVisible(false);
		frame.get_txt_idcomprobante().setEditable(true);
		frame.get_txt_idproveedor().setEditable(true);
		frame.get_btn_buscar_proveedor().setEnabled(true);
		frame.get_btn_buscar_cpte().setEnabled(true);
		frame.get_list_actualiza().setSelectedIndex(-1);
		frame.get_list_etiquetas().setSelectedIndex(-1);
		frame.get_chk_autocompletar().setSelected(true);
		frame.get_txt_idproveedor_articulos().setText("");
		frame.get_txt_prefijo().setText("");
		frame.get_txt_descuento_porcentaje().setText("0.0");
		frame.get_txt_descuento_detalle().setText("0.0");
		frame.get_txt_subtotal_con_descuento().setText("");
		frame.get_txt_articulo_().setText("");
		frame.get_txt_articulo_descripcion().setText("");
		frame.get_txt_articulo_stock().setText("");
		frame.get_txt_articulo_actualizacion().setText("");
		frame.get_txt_articulo_bloqueado().setText("");
		frame.get_txt_articulo_bloqueado().setBackground(Color.black);
		frame.get_txt_articulo_pedido().setText("");
		frame.get_txt_articulo_pedido().setBackground(Color.black);
		frame.get_txt_articulo_linea().setText("");

		frame.get_txt_descuento().setText("");
		frame.get_txt_numero().setText("");
		frame.get_txt_sucursal().setText("");
		frame.get_txt_fecha().setEnabled(false);
		frame.get_btn_buscar_cpte().setEnabled(false);
		frame.get_txt_numero().setEditable(false);
		frame.get_txt_sucursal().setEditable(false);
		frame.get_list_letra().setEnabled(false);
		frame.get_txt_idcomprobante().setEditable(false);
		frame.getJTabbedPane1().setTitleAt(0, "Pedidos PEP");
		frame.getJTabbedPane1().setTitleAt(1, "Pedidos PDP");
		this.select_tc("FCC");
		frame.get_txt_idproveedor().requestFocusInWindow();
		frame.get_txt_hoja_actual().setText("");
		frame.get_txt_hoja_total().setText("");
		Runnable _execute=new Runnable(){
			public void run(){
				frame.setJTable(null);
				frame.setJTable1(null);
				frame.setJTable2(null);
				frame.setJTable3(null);
				frame.setJTable4(null);
				frame.setJTable5(null);
				frame.setJTable6(null);
						
			}
		};
		this.invokeLater(_execute);
		System.gc();
		System.gc();
		System.gc();
		System.gc();
	}

	aplicacion.proveedor.archivo.constructor._Constructor maestro_proveedor = null;

	public void editar_proveedor() {
		String idproveedor = frame.get_txt_idproveedor().getText();
		if (maestro_proveedor != null) {
			maestro_proveedor.dispose();
		}
		maestro_proveedor = new aplicacion.proveedor.archivo.constructor._Constructor();
		maestro_proveedor.setParameter(_parametros.connector, data
				.getConnectionHandler());
		maestro_proveedor.setParameter(_parametros.iduser, this
				.getConstructor().getIduser());
		maestro_proveedor.setParameter(_parametros.LookAndFeel, this
				.getConstructor().getLookAndFeelTheme());
		maestro_proveedor.setParameter(_Parametros.idproveedor, idproveedor);
		maestro_proveedor.build(this.getConstructor());
		maestro_proveedor.init();

	}

	public int getPositionArticuulo(String idarticulo){
		int i=0;
		boolean found=false;
		
		while (i<cantidadArticulos.size() &!found){
			Object[] tmp=cantidadArticulos.get(i);
			found=tmp[0].toString().compareTo(idarticulo)==0;
		if (!found)i++;	
		}
		if (!found)i=-1;
		return i;
	}
	
	public double getArticuloCantidad(String idarticulo){
		double qty=0;
		int i=this.getPositionArticuulo(idarticulo);
		if (i>=0){
			Object[] tmp=cantidadArticulos.get(i);
			qty=(Double)tmp[1];
			
		}
		return qty;
	}
	
	public void storeArticuloCantidad(String idarticulo,double cantidad){
		int i=this.getPositionArticuulo(idarticulo);
		if (i>=0){
			Object[] tmp=cantidadArticulos.get(i);
			tmp[1]=cantidad;
			cantidadArticulos.set(i, tmp);
		}else{
			Object[] tmp=new Object[]{idarticulo,cantidad};
			cantidadArticulos.add(tmp);
		}
	}
	
	
	public List<String> getInstruccionesRegenerarRemitos(String _tc,
			String _idcomprobante, String fecha) {
		List<String> instrucciones = new ArrayList<String>();

		String cuenta = frame.get_txt_idproveedor().getText();
		String instruccion = data.getInsertEncabezado(cuenta, _tc,
				_idcomprobante, fecha);
		instrucciones.add(instruccion);
		Object[][] items = data.getRemitosItems(cuenta, _idcomprobante);

		for (int u = 0; u < items.length; u++) {
			String idarticulo = (String) items[u][0];
			String descripcion = (String) items[u][1];
			String cantidad = (String) items[u][2];
			String precio = (String) items[u][3];
			String q1 = data.getInsertSets("" + u, idarticulo, descripcion,
					cantidad, precio, "0.0", "0.0", "0.0", "", "");
			instrucciones.add(q1);
			String q2 = this.data.getInsertStock();
			instrucciones.add(q2);

		}
		return instrucciones;
	}

	private List<String> _get_instrucciones_asiento_fcc() {
		System.out.println("grabar asiento fcc");
		List<String> instrucciones = new ArrayList<String>();
		Asiento A = null;
		Renglon r = null;
		String idcajas = "   2";
		Object[][] info = data.getAcountInfo(frame.get_txt_idproveedor()
				.getText());
		String iva = "";
		String cuit = "";
		String cai = "";
		String cai_vencimiento = "";
		String fecha_contable = frame.get_txt_fecha().getText();
		if (info != null) {
			if (info.length > 0) {
				iva = (String) info[0][8];
				cuit = (String) info[0][9];
			}
		}

		A = new Asiento(this.getConstructor().getConnectionHandler());
		A.setDetalle(frame.get_txt_proveedor_descripcion().getText());
		A.setTc(this.getTipoTC());
		A.setSucursal(frame.get_txt_sucursal().getText());
		A.setNumero(frame.get_txt_numero().getText());
		A.setLetra(frame.get_list_letra().getSelectedItem().toString());
		A.setFecha(fecha_contable);
		A.setSubtotal(this.get_subtotal());
		if (cai.compareTo("") != 0) {
			A.setNrocai(cai);
			A.setFhvtocai(cai_vencimiento);
		} else {
			A.setNrocai("");
			A.setFhvtocai("");
		}
		A.setNrocai("");
		A.setFhvtocai("");
		A.setId_cotiz("0");
		A.setCotizacion("1.0");
		A.setFechasubdiario(fecha_contable);
		A.setUnegocio("   1");
		A.setIdmotivo("   1");
		r = new Renglon();
		r.setCuenta(frame.get_txt_idproveedor().getText());
		r.setCabcuenta(frame.get_txt_idproveedor().getText());
		System.out.println(">>FCC> condicion de iva? " + iva);
		r.setCabcondiva(iva);
		r.setLiva_aliciva("0.0");
		r.setLiva_aliciva2("0.0");
		r.setLiva_aliciva3("0.0");
		r.setLiva_exento("0.0");
		if (this._get_importe_row(_Interface.row_iva) != 0) {
			r.setLiva_aliciva("21.0");
		}
		if (this._get_importe_row(_Interface.row_iva10) != 0) {
			r.setLiva_aliciva2("10.0");
		}
		if (this._get_importe_row(_Interface.row_iva27) != 0) {
			r.setLiva_aliciva3("27.0");
		}

		r.setCabcuit(cuit);
		r.setCabnombre(frame.get_txt_proveedor_descripcion().getText());

		r
				.setLiva_impnetongrav(""
						+ this
								._get_importe_row(_Interface.row_importe_neto_no_gravado));
		;
		r.setLiva_impnetograv(""
				+ this._get_importe_row(_Interface.row_subtotal));
		;

		r.setLiva_impiva("" + this._get_importe_row(_Interface.row_iva));
		if (this._get_importe_row(_Interface.row_iva) > 0) {
			r.setLiva_aliciva("21");
		} else {
			r.setLiva_aliciva("0.0");
		}
		r.setLiva_impiva2("" + this._get_importe_row(_Interface.row_iva10));
		if (this._get_importe_row(_Interface.row_iva10) > 0) {
			r.setLiva_aliciva2("10.5");
		} else {
			r.setLiva_aliciva2("0.0");
		}

		r.setLiva_impiva3("" + this._get_importe_row(_Interface.row_iva27));
		if (this._get_importe_row(_Interface.row_iva27) > 0) {
			r.setLiva_aliciva3("27");
		} else {
			r.setLiva_aliciva3("0.0");
		}
		r.setLiva_exento(""
				+ _get_importe_row(_Interface.row_importe_neto_no_gravado));
		r.setLiva_ret_ganancias(""
				+ this._get_importe_row(_Interface.row_retencion_ganancias));
		r.setLiva_ret_ibtos(""
				+ this._get_importe_row(_Interface.row_percepcion_iibb));
		double sum_perc=this._get_importe_row(_Interface.row_retencion_iva);
		sum_perc+=this._get_importe_row(_Interface.row_rg3337);
		r.setLiva_ret_perc(""+sum_perc);
		r.setLiva_total("" + this.get_total());
		r.setLiva_tipo("COMPRAS");

		double total = 0.0;
		try {
			total = new Double(frame.get_txt_total().getText().replaceAll(",",
					""));
		} catch (Exception e) {

		}

		r.setImporte("" + total);
		r.setDebe_haber("H");
		r.setIdcajas(idcajas);
		A.addRenglon(r);

		r = new Renglon();

		r.setCuenta(this._get_cuenta_row(_Interface.row_subtotal));
		r.setImporte("" + this._get_importe_row(_Interface.row_subtotal));
		r.setIdcajas(idcajas);
		r.setDebe_haber("D");
		A.addRenglon(r);

		r = new Renglon();
		r.setCuenta(this._get_cuenta_row(_Interface.row_iva));
		r.setImporte("" + this._get_importe_row(_Interface.row_iva));
		r.setIdcajas(idcajas);
		r.setDebe_haber("D");
		A.addRenglon(r);

		r = new Renglon();
		r.setCuenta(this._get_cuenta_row(_Interface.row_iva10));
		r.setImporte("" + this._get_importe_row(_Interface.row_iva10));
		r.setIdcajas(idcajas);
		r.setDebe_haber("D");
		A.addRenglon(r);

		r = new Renglon();
		r.setCuenta(this._get_cuenta_row(_Interface.row_iva27));
		r.setImporte("" + this._get_importe_row(_Interface.row_iva27));
		r.setIdcajas(idcajas);
		r.setDebe_haber("D");
		A.addRenglon(r);

		r = new Renglon();
		r.setCuenta(this._get_cuenta_row(_Interface.row_retencion_iva));
		r.setImporte("" + this._get_importe_row(_Interface.row_retencion_iva));
		r.setIdcajas(idcajas);
		r.setDebe_haber("D");
		A.addRenglon(r);

		r = new Renglon();
		r.setCuenta(this._get_cuenta_row(_Interface.row_rg3337));
		r.setImporte("" + this._get_importe_row(_Interface.row_rg3337));
		r.setIdcajas(idcajas);
		r.setDebe_haber("D");
		A.addRenglon(r);

		r = new Renglon();
		r.setCuenta(this._get_cuenta_row(_Interface.row_percepcion_iibb));
		r
				.setImporte(""
						+ this._get_importe_row(_Interface.row_percepcion_iibb));
		r.setIdcajas(idcajas);
		r.setDebe_haber("D");
		A.addRenglon(r);

		r = new Renglon();
		r.setCuenta(this._get_cuenta_row(_Interface.row_retencion_ganancias));
		r.setImporte(""
				+ this._get_importe_row(_Interface.row_retencion_ganancias));
		r.setIdcajas(idcajas);
		r.setDebe_haber("D");
		A.addRenglon(r);

		r = new Renglon();
		r.setCuenta(this
				._get_cuenta_row(_Interface.row_importe_neto_no_gravado));
		r
				.setImporte(""
						+ this
								._get_importe_row(_Interface.row_importe_neto_no_gravado));
		r.setIdcajas(idcajas);
		r.setDebe_haber("D");
		A.addRenglon(r);

		r = new Renglon();
		r.setCuenta(this._get_cuenta_row(_Interface.row_impuestos_internos));
		r.setImporte(""
				+ this._get_importe_row(_Interface.row_impuestos_internos));
		r.setIdcajas(idcajas);
		r.setDebe_haber("D");
		A.addRenglon(r);

		r = new Renglon();
		r.setCuenta(this._get_cuenta_row(_Interface.row_ajuste_por_redondeo));
		r.setImporte(""
				+ this._get_importe_row(_Interface.row_ajuste_por_redondeo));
		r.setIdcajas(idcajas);
		r.setDebe_haber("D");
		A.addRenglon(r);

		instrucciones = A.getInstrucciones();
		return instrucciones;
	}

	private List<String> _get_instrucciones_asiento_fcn() {
		System.out.println("grabar asiento fcn");
		List<String> instrucciones = new ArrayList<String>();
		aplicacion.beta.comprobantes.mv_asientos.objetos.Asiento A = null;
		aplicacion.beta.comprobantes.mv_asientos.objetos.Renglon r = null;
		String idcajas = "   2";
		String idcomprobante = frame.get_txt_idcomprobante().getText();
		Object[][] info = data.getAcountInfo(frame.get_txt_idproveedor()
				.getText());
		String iva = "";
		String imputacion = frame.get_list_imputacion().getSelectedItem()
				.toString();
		Object[][] results = data.getAcountInfo(imputacion);
		String nombre = "";
		if (results != null) {
			if (results.length > 0) {
				nombre = (String) results[0][1];
				frame.get_txt_imputacion_detalle().setText(nombre);
			}
		}

		String fecha_contable = frame.get_txt_fecha().getText();
		if (info != null) {
			if (info.length > 0) {
				iva = (String) info[0][8];
			}
		}

		A = new aplicacion.beta.comprobantes.mv_asientos.objetos.Asiento(this
				.getConstructor().getConnectionHandler());
		A.setDetalle(frame.get_txt_proveedor_descripcion().getText());
		A.setTc(this.getTipoTC());
		A.setIdcomprobante(idcomprobante);
		A.setFecha(fecha_contable);
		A.setCotizacion("1.0");

		r = new aplicacion.beta.comprobantes.mv_asientos.objetos.Renglon();
		r.setCuenta(frame.get_txt_idproveedor().getText());
		r.setCuenta_nombre(frame.get_txt_proveedor_descripcion().getText());
		System.out.println(">>FCN> condicion de iva? " + iva);

		double total = 0.0;
		try {
			total = new Double(frame.get_txt_total().getText().replaceAll(",",
					""));
		} catch (Exception e) {

		}

		r.setImporte("" + total);
		r.setDebe_haber("H");
		r.setIdcajas(idcajas);
		A.addRenglon(r);

		r = new aplicacion.beta.comprobantes.mv_asientos.objetos.Renglon();

		r.setCuenta(this._get_cuenta_row(_Interface.row_subtotal));
		r.setCuenta_nombre(this._get_cuenta_row_detalle(_Interface.row_subtotal));
		r.setImporte("" + this._get_importe_row(_Interface.row_subtotal));
		r.setIdcajas(idcajas);
		r.setDebe_haber("D");
		A.addRenglon(r);

		r = new aplicacion.beta.comprobantes.mv_asientos.objetos.Renglon();
		r.setCuenta(this._get_cuenta_row(_Interface.row_iva));
		r.setCuenta_nombre(this._get_cuenta_row_detalle(_Interface.row_iva));
		r.setImporte("" + this._get_importe_row(_Interface.row_iva));
		r.setIdcajas(idcajas);
		r.setDebe_haber("D");
		A.addRenglon(r);

		r = new aplicacion.beta.comprobantes.mv_asientos.objetos.Renglon();
		r.setCuenta(this._get_cuenta_row(_Interface.row_iva10));
		r.setCuenta_nombre(this._get_cuenta_row_detalle(_Interface.row_iva10));
		r.setImporte("" + this._get_importe_row(_Interface.row_iva10));
		r.setIdcajas(idcajas);
		r.setDebe_haber("D");
		A.addRenglon(r);

		r = new aplicacion.beta.comprobantes.mv_asientos.objetos.Renglon();
		r.setCuenta(this._get_cuenta_row(_Interface.row_iva27));
		r.setCuenta_nombre(this._get_cuenta_row_detalle(_Interface.row_iva27));
		r.setImporte("" + this._get_importe_row(_Interface.row_iva27));
		r.setIdcajas(idcajas);
		r.setDebe_haber("D");
		A.addRenglon(r);

		r = new aplicacion.beta.comprobantes.mv_asientos.objetos.Renglon();
		r.setCuenta(this._get_cuenta_row(_Interface.row_retencion_iva));
		r.setCuenta_nombre(this._get_cuenta_row_detalle(_Interface.row_retencion_iva));
		r.setImporte("" + this._get_importe_row(_Interface.row_retencion_iva));
		r.setIdcajas(idcajas);
		r.setDebe_haber("D");
		A.addRenglon(r);

		r = new aplicacion.beta.comprobantes.mv_asientos.objetos.Renglon();
		r.setCuenta(this._get_cuenta_row(_Interface.row_rg3337));
		r.setCuenta_nombre(this._get_cuenta_row_detalle(_Interface.row_rg3337));
		r.setImporte("" + this._get_importe_row(_Interface.row_rg3337));
		r.setIdcajas(idcajas);
		r.setDebe_haber("D");
		A.addRenglon(r);

		r = new aplicacion.beta.comprobantes.mv_asientos.objetos.Renglon();
		r.setCuenta(this._get_cuenta_row(_Interface.row_percepcion_iibb));
		r.setCuenta_nombre(this._get_cuenta_row_detalle(_Interface.row_percepcion_iibb));
		r
				.setImporte(""
						+ this._get_importe_row(_Interface.row_percepcion_iibb));
		r.setIdcajas(idcajas);
		r.setDebe_haber("D");
		A.addRenglon(r);

		r = new aplicacion.beta.comprobantes.mv_asientos.objetos.Renglon();
		r.setCuenta(this._get_cuenta_row(_Interface.row_retencion_ganancias));
		r.setCuenta_nombre(this._get_cuenta_row_detalle(_Interface.row_retencion_ganancias));
		r.setImporte(""
				+ this._get_importe_row(_Interface.row_retencion_ganancias));
		r.setIdcajas(idcajas);
		r.setDebe_haber("D");
		A.addRenglon(r);

		r = new aplicacion.beta.comprobantes.mv_asientos.objetos.Renglon();
		r.setCuenta(this
				._get_cuenta_row(_Interface.row_importe_neto_no_gravado));
		r.setCuenta_nombre(this._get_cuenta_row_detalle(_Interface.row_importe_neto_no_gravado));
		r
				.setImporte(""
						+ this
								._get_importe_row(_Interface.row_importe_neto_no_gravado));
		r.setIdcajas(idcajas);
		r.setDebe_haber("D");
		A.addRenglon(r);

		r = new aplicacion.beta.comprobantes.mv_asientos.objetos.Renglon();
		r.setCuenta(this._get_cuenta_row(_Interface.row_impuestos_internos));
		r.setCuenta_nombre(this._get_cuenta_row_detalle(_Interface.row_impuestos_internos));
		r.setImporte(""
				+ this._get_importe_row(_Interface.row_impuestos_internos));
		r.setIdcajas(idcajas);
		r.setDebe_haber("D");
		A.addRenglon(r);

		r = new aplicacion.beta.comprobantes.mv_asientos.objetos.Renglon();
		r.setCuenta(this._get_cuenta_row(_Interface.row_ajuste_por_redondeo));
		r.setCuenta_nombre(this._get_cuenta_row_detalle(_Interface.row_ajuste_por_redondeo));
		r.setImporte(""
				+ this._get_importe_row(_Interface.row_ajuste_por_redondeo));
		r.setIdcajas(idcajas);
		r.setDebe_haber("D");
		A.addRenglon(r);

		instrucciones = A.getInstrucciones();
		return instrucciones;
	}

	public boolean _eliminar_fotos(String cuenta, String tc,
			String idcomprobante) {
		boolean error = data.deleteFotos(cuenta, tc, idcomprobante);
		return !error;
	}

	public boolean eliminar_fotos() {

		String cuenta = frame.get_txt_idproveedor().getText();
		String sucursal = frame.get_txt_sucursal().getText();
		String numero = frame.get_txt_numero().getText();
		String letra = frame.get_list_letra().getSelectedItem().toString();
		String idcomprobante = sucursal + numero + letra;
		boolean ok = this._eliminar_fotos(cuenta, this.getTipoTC(),
				idcomprobante);
		return ok;
	}

	private List<String> getInstruccionesEliminarAlfa(String cuenta, String tc,
			String sucursal, String numero, String letra) {
		List<String> instrucciones = new ArrayList<String>();
		String idcomprobante = sucursal + numero + letra;
		String q = data.getEliminarAsiento(cuenta, tc, sucursal, numero, letra);
		instrucciones.add(q);
		q = data.getEliminarEnlace(tc, idcomprobante, cuenta);
		instrucciones.add(q);
		q = data.getEliminarCpte(cuenta, tc, idcomprobante);
		instrucciones.add(q);
		q = data.getEliminarCpteInsumos(cuenta, tc, idcomprobante);
		instrucciones.add(q);
		q = data.getEliminarDigital(cuenta, tc, idcomprobante);
		instrucciones.add(q);
		q = data.getEliminarStock(cuenta, tc, idcomprobante);
		instrucciones.add(q);
		return instrucciones;
	}

	public boolean guardar_imagenes() {
		boolean ok = true;
		if (images.size() > 0) {
			System.out.println("imagenes?");
			int i = 0;
			while (i < images.size() & ok) {
				ok = this.write(images.get(i), i + 1);
				i++;
			}
		}
		return ok;
	}

	private boolean write(BufferedImage img, int sec) {

		String idproveedor = frame.get_txt_idproveedor().getText();
		String idcomprobante = frame.get_txt_sucursal().getText()
				+ frame.get_txt_numero().getText()
				+ frame.get_list_letra().getSelectedItem().toString();
		String tc = this.getTipoTC();

		boolean ok = this.storeImage(idproveedor, tc, idcomprobante, (sec - 1),
				img);

		return ok;
	}

	public boolean storeImage(String idproveedor, String tc,
			String idcomprobante, int secuencia, BufferedImage img) {
		FileInputStream fis = null;
		PreparedStatement ps = null;
		System.out.println("Store Image " + tc + " " + idproveedor);
		boolean ok = true;
		if (idcomprobante.compareTo("") != 0 & idproveedor.compareTo("") != 0
				& idproveedor.compareTo("") != 0) {
			String INSERT_PICTURE = "insert into facturas(idproveedor, tc, idcomprobante,secuencia,imagen) values (?, ?, ?, ?, ?)";
			ByteArrayOutputStream buffer_img = new ByteArrayOutputStream();

			try {
				ImageIO.write(img, "jpg", buffer_img);
				InputStream in = new ByteArrayInputStream(buffer_img
						.toByteArray());

				ps = data.getConnector("MySQL")
						.prepareStatement(INSERT_PICTURE);
				ps.setString(1, idproveedor);
				ps.setString(2, tc);
				ps.setString(3, idcomprobante);
				ps.setInt(4, secuencia);
				ps.setBinaryStream(5, in, (int) buffer_img.size());

				int n = ps.executeUpdate();
				ok = n > 0;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return ok;
	}

	private List<String> getInstruccionesEliminarBeta(String cuenta, String tc,
			String idcomprobante) {
		List<String> instrucciones = new ArrayList<String>();

		String q = data.getEliminarAsientoBeta(cuenta, tc, idcomprobante);
		instrucciones.add(q);
		q = data.getEliminarEnlace(tc, idcomprobante, cuenta);
		instrucciones.add(q);
		q = data.getEliminarCpteBeta(cuenta, tc, idcomprobante);
		instrucciones.add(q);
		q = data.getEliminarCpteInsumosBeta(cuenta, tc, idcomprobante);
		instrucciones.add(q);
		q = data.getEliminarDigitalBeta(cuenta, tc, idcomprobante);
		instrucciones.add(q);
		q = data.getEliminarStock(cuenta, tc, idcomprobante);
		instrucciones.add(q);
		return instrucciones;
	}

	public void eliminar() {
		// si no tiene opg asociadas
		// si tiene articulos asociados advertir
		if (confirmar("Confirme para Eliminar este Comprobante:", 3)) {
			_eliminar();
		}

	}

	private List<String> getInstruccionesEliminar(String cuenta, String tc,
			String sucursal, String numero, String letra) {
		String idcomprobante = sucursal + numero + letra;
		List<String> instrucciones = new ArrayList<String>();
		List<String> instrucciones_cpte = null;

		if (tc.compareTo("FCN") != 0) {
			instrucciones_cpte = this.getInstruccionesEliminarAlfa(cuenta, tc,
					sucursal, numero, letra);
		} else {
			instrucciones_cpte = this.getInstruccionesEliminarBeta(cuenta, tc,
					idcomprobante);
		}
		String q = data.getunBlock();
		instrucciones.add(q);
		q=data.getDeleteDigital(tc, idcomprobante, cuenta);
		instrucciones.add(q);
		for (int i = 0; i < instrucciones_cpte.size(); i++) {
			instrucciones.add(instrucciones_cpte.get(i));
		}
		q = data.getBlock();
		instrucciones.add(q);

		return instrucciones;
	}

	private void _eliminar() {

		String cuenta = frame.get_txt_idproveedor().getText();
		String sucursal = frame.get_txt_sucursal().getText();
		String numero = frame.get_txt_numero().getText();
		String letra = frame.get_list_letra().getSelectedItem().toString();
		String idcomprobante = sucursal + numero + letra;
		String tc = this.getTipoTC();

		List<String> instrucciones = this.getInstruccionesEliminar(cuenta, tc,
				sucursal, numero, letra);
		data.beginTransaction();
		data.clearBatch();
		for (int i = 0; i < instrucciones.size(); i++) {
			System.out.println(i + ">" + instrucciones.get(i));
			data.addBatch(instrucciones.get(i));
		}

		boolean error = data.executeBatch();
		if (!error) {
			if (data.tiene_remitos_aplicados(cuenta, tc, idcomprobante)) {

				Object[][] results = data.getRemitos_aplicados(cuenta, tc,
						idcomprobante);
				if (results != null) {
					if (results.length > 0) {
						int i = 0;
						while (i < results.length & !error) {
							instrucciones = new ArrayList<String>();
							String _tc = (String) results[i][0];
							String _idcomprobante = (String) results[i][1];
							String _fecha = (String) results[i][2];
							List<String> _stock = this
									.getInstruccionesRegenerarRemitos(_tc,
											_idcomprobante, _fecha);
							for (int u = 0; u < _stock.size(); u++) {
								instrucciones.add(_stock.get(u));
							}
							data.clearBatch();
							for (int u = 0; u < instrucciones.size(); u++) {
								System.out.println(i + ">"
										+ instrucciones.get(u));
								data.addBatch(instrucciones.get(u));
							}
							error = data.executeBatch();
							i++;
						}
						if (!error) {
							String q = data.getDelete_remitos_aplicados(cuenta,
									tc, idcomprobante);
							data.clearBatch();
							data.addBatch(q);
							error = data.executeBatch();
						}
					}
				}

			}

		}
		if (!error) {
			boolean delete_error = data.deleteFotos(cuenta, tc, idcomprobante);
			if (!delete_error) {
				data.commitTransaction();
				aviso("se elimino correctamente el comprobante");
				this.clean();
			} else {
				data.rollbackTransaction();
				error("error al intentar eliminar el comprobante");
			}

		} else {
			data.rollbackTransaction();
			error("error al intentar eliminar el comprobante");
		}

	}

	private List<String> _get_instrucciones_asiento_ncc() {
		List<String> instrucciones = new ArrayList<String>();
		Asiento A = null;
		Renglon r = null;
		String idcajas = "   2";
		A = new Asiento(this.getConstructor().getConnectionHandler());
		String iva = "";
		Object[][] info = data.getAcountInfo(frame.get_txt_idproveedor()
				.getText());
		String cuit = "";
		String cai = "";
		String cai_vencimiento = "";
		String fecha_contable = frame.get_txt_fecha().getText();
		if (info != null) {
			if (info.length > 0) {
				iva = (String) info[0][8];
				cuit = (String) info[0][9];
			}
		}
		A.setDetalle(frame.get_txt_proveedor_descripcion().getText());
		A.setTc(this.getTipoTC());
		A.setSucursal(frame.get_txt_sucursal().getText());
		A.setNumero(frame.get_txt_numero().getText());
		A.setLetra(frame.get_list_letra().getSelectedItem().toString());
		A.setFecha(fecha_contable);
		A.setSubtotal(this.get_subtotal());

		if (cai.compareTo("") != 0) {
			A.setNrocai(cai);
			A.setFhvtocai(cai_vencimiento);
		} else {
			A.setNrocai("");
			A.setFhvtocai("");
		}
		A.setNrocai("");
		A.setFhvtocai("");
		r = new Renglon();
		r.setCuenta(frame.get_txt_idproveedor().getText());
		r.setCabcuenta(frame.get_txt_idproveedor().getText());
		r.setCabcuit(cuit);
		System.out.println(">>NCC> condicion de iva? " + iva);
		r.setCabcondiva(iva);
		r.setLiva_aliciva("0.0");
		r.setLiva_aliciva2("0.0");
		r.setLiva_aliciva3("0.0");
		r.setLiva_exento("0.0");
		r.setCabnombre(frame.get_txt_proveedor_descripcion().getText());

		r
				.setLiva_impnetongrav(""
						+ -this
								._get_importe_row(_Interface.row_importe_neto_no_gravado));
		;
		r.setLiva_impnetograv(""
				+ -this._get_importe_row(_Interface.row_subtotal));
		;
		r.setLiva_impiva("" + -this._get_importe_row(_Interface.row_iva));

		r.setLiva_impiva2("" + -this._get_importe_row(_Interface.row_iva10));

		r.setLiva_impiva3("" + -this._get_importe_row(_Interface.row_iva27));

		r.setLiva_ret_ganancias(""
				+ -this._get_importe_row(_Interface.row_retencion_ganancias));
		r.setLiva_ret_ibtos(""
				+ -this._get_importe_row(_Interface.row_percepcion_iibb));
		
		double sum_perc=this._get_importe_row(_Interface.row_retencion_iva);
		sum_perc+=this._get_importe_row(_Interface.row_rg3337);
		r.setLiva_ret_perc(""+sum_perc);
		
		r.setLiva_total("" + -this.get_total());
		r.setLiva_tipo("COMPRAS");
		if (this._get_importe_row(_Interface.row_iva) != 0) {
			r.setLiva_aliciva("21.0");
		}
		if (this._get_importe_row(_Interface.row_iva10) != 0) {
			r.setLiva_aliciva2("10.0");
		}
		if (this._get_importe_row(_Interface.row_iva27) != 0) {
			r.setLiva_aliciva3("27.0");
		}
		double total = 0.0;
		try {
			total = new Double(frame.get_txt_total().getText().replaceAll(",",
					""));
		} catch (Exception e) {

		}
		r.setImporte("" + total);
		r.setDebe_haber("D");
		r.setIdcajas(idcajas);
		A.addRenglon(r);

		r = new Renglon();
		r.setCuenta(this._get_cuenta_row(_Interface.row_subtotal));
		r.setImporte("" + this._get_importe_row(_Interface.row_subtotal));
		r.setIdcajas(idcajas);
		r.setDebe_haber("H");
		A.addRenglon(r);

		r = new Renglon();
		r.setCuenta(this._get_cuenta_row(_Interface.row_iva));
		r.setImporte("" + this._get_importe_row(_Interface.row_iva));
		r.setIdcajas(idcajas);
		r.setDebe_haber("H");
		A.addRenglon(r);

		r = new Renglon();
		r.setCuenta(this._get_cuenta_row(_Interface.row_iva10));
		r.setImporte("" + this._get_importe_row(_Interface.row_iva10));
		r.setIdcajas(idcajas);
		r.setDebe_haber("H");
		A.addRenglon(r);

		r = new Renglon();
		r.setCuenta(this._get_cuenta_row(_Interface.row_iva27));
		r.setImporte("" + this._get_importe_row(_Interface.row_iva27));
		r.setIdcajas(idcajas);
		r.setDebe_haber("H");
		A.addRenglon(r);

		r = new Renglon();
		r.setCuenta(this._get_cuenta_row(_Interface.row_retencion_iva));
		r.setImporte("" + this._get_importe_row(_Interface.row_retencion_iva));
		r.setIdcajas(idcajas);
		r.setDebe_haber("H");
		A.addRenglon(r);

		r = new Renglon();
		r.setCuenta(this._get_cuenta_row(_Interface.row_rg3337));
		r.setImporte("" + this._get_importe_row(_Interface.row_rg3337));
		r.setIdcajas(idcajas);
		r.setDebe_haber("H");
		A.addRenglon(r);

		r = new Renglon();
		r.setCuenta(this._get_cuenta_row(_Interface.row_percepcion_iibb));
		r
				.setImporte(""
						+ this._get_importe_row(_Interface.row_percepcion_iibb));
		r.setIdcajas(idcajas);
		r.setDebe_haber("H");
		A.addRenglon(r);

		r = new Renglon();
		r.setCuenta(this._get_cuenta_row(_Interface.row_retencion_ganancias));
		r.setImporte(""
				+ this._get_importe_row(_Interface.row_retencion_ganancias));
		r.setIdcajas(idcajas);
		r.setDebe_haber("H");
		A.addRenglon(r);

		r = new Renglon();
		r.setCuenta(this
				._get_cuenta_row(_Interface.row_importe_neto_no_gravado));
		r
				.setImporte(""
						+ this
								._get_importe_row(_Interface.row_importe_neto_no_gravado));
		r.setIdcajas(idcajas);
		r.setDebe_haber("H");
		A.addRenglon(r);

		r = new Renglon();
		r.setCuenta(this._get_cuenta_row(_Interface.row_impuestos_internos));
		r.setImporte(""
				+ this._get_importe_row(_Interface.row_impuestos_internos));
		r.setIdcajas(idcajas);
		r.setDebe_haber("H");
		A.addRenglon(r);

		r = new Renglon();
		r.setCuenta(this._get_cuenta_row(_Interface.row_ajuste_por_redondeo));
		r.setImporte(""
				+ this._get_importe_row(_Interface.row_ajuste_por_redondeo));
		r.setIdcajas(idcajas);
		r.setDebe_haber("H");
		A.addRenglon(r);

		instrucciones = A.getInstrucciones();
		return instrucciones;
	}

	private double get_total() {

		double imp = 0.0;
		try {
			String importe = frame.get_txt_total().getText();
			importe = importe.replace(",", "");
			imp = new Double(importe);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("get total para asiento? " + imp);
		return imp;
	}

	public void change_tc(JComboBox cb) {
		frame.get_txt_sucursal().setText("");

		frame.get_txt_numero().setText("");
		frame.get_list_letra().removeItemListener(
				this.getConstructor().getItemListener());
		frame.get_list_letra().removeAllItems();
		if (cb.getSelectedIndex() == 3) {
			frame.get_list_letra().addItem("X");
		} else {
			if (cb.getSelectedIndex() == 0 | cb.getSelectedIndex() == 1) {
				frame.get_list_letra().addItem("A");
				frame.get_list_letra().addItem("B");
			} else {
				frame.get_list_letra().addItem("X");
			}

		}
		frame.get_list_letra().addItemListener(
				this.getConstructor().getItemListener());
		frame.get_txt_sucursal().requestFocusInWindow();
	}

	public void initial_focus() {
		frame.get_txt_idproveedor().requestFocusInWindow();
	}

	private aplicacion.herramientas.java.buscadores.Fecha bFecha = null;
	/**
	 * Muestra un Frame para seleccionar Fecha
	 * @param tx
	 */
	public void BuscarFecha(JTextField tx) {
		if (bFecha == null) {
			bFecha = new aplicacion.herramientas.java.buscadores.Fecha(this
					.getConstructor());
		}

		bFecha.Buscar(tx);

	}

	public void BuscarFecha() {
		JTextField tx = frame.get_txt_fecha();
		this.BuscarFecha(tx);
	}

	/**
	 * Evaluador de Fecha de Comprobantes
	 * @param tx
	 */
	public void _evaluar_fecha(JTextField tx) {
		String fecha = tx.getText();
		String idcomprobante = frame.get_txt_idcomprobante().getText();
		String cuenta = frame.get_txt_idproveedor().getText();
		if (fecha.compareTo("") == 0) {
			this.BuscarFecha(tx);
		} else {
			if (this.evaluarFecha(fecha)) {

				if (this._check_articulos_cargados()) {
					this.cargar_articulos(cuenta, this.getTipoTC(),
							idcomprobante);
					this._calculate();
				} else {
					
						crear_tabla_items();	
					
					
					

				}
				this.cargar_pedidos_especiales(cuenta);
				this.cargar_pedidos_proyeccion(cuenta);
				if (this.getTipoTC().compareTo("FCC") == 0) {
					this.cargar_remitos(cuenta);
				}
				frame.get_txt_idcomprobante().setEditable(false);
				frame.get_txt_idproveedor().setEditable(false);
				frame.get_btn_buscar_proveedor().setEnabled(false);
				frame.get_btn_buscar_cpte().setEnabled(false);
				frame.get_list_tc().setEnabled(false);
				frame.getJTabbedPane().requestFocusInWindow();
				frame.getJTabbedPane().setSelectedIndex(0);
				frame.getJTable4().requestFocusInWindow();
				frame.getJTable4().changeSelection(0, 2, false, false);
				frame.getJTable4().editCellAt(0, 2);
				frame.getJTable4().transferFocus();
			} else {
				error("Fecha " + fecha + " incorrecta");
				tx.setSelectionStart(0);
				tx.setSelectionEnd(tx.getText().length());
			}
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
		if (n == 0) {
			aviso("no hay Equivalencias con ese codigo");
		}
		return n;
	}

	public void _evaluar_sucursal(JTextField tx) {
		String sucursal = tx.getText();
		boolean ok = this._evaluar_sucursal(sucursal);
		if (ok) {
			if (this.getTipoTC().compareTo("FCN") == 0) {
				String number = "" + data.getProximoPEDIDO();
				frame.get_txt_numero().setText(number);
			}
			frame.get_txt_numero().requestFocusInWindow();
		} else {
			error("Error en numero de sucursal");
			tx.setSelectionStart(0);
			tx.setSelectionEnd(tx.getText().length());
		}
	}

	public void _evaluar_numero(JTextField tx) {
		String numero = tx.getText();
		boolean ok = this._evaluar_numero(numero);
		if (this.getTipoTC().compareTo("FCN") == 0) {
			/*
			 * int n=new Integer(numero); ok=(data.getProximoPEDIDO()==n); if
			 * (!ok){
			 * error("Utilice el proximo numero otorgado por el sistema");
			 * tx.setText(""+data.getProximoPEDIDO()); }
			 */
		}
		if (ok) {
			frame.get_list_letra().requestFocusInWindow();
		} else {
			error("Error en numero de comprobante");
			tx.setSelectionStart(0);
			tx.setSelectionEnd(tx.getText().length());
		}
	}

	private boolean existe_cpte() {
		String idproveedor = frame.get_txt_idproveedor().getText();
		String sucursal = frame.get_txt_sucursal().getText();
		String numero = frame.get_txt_numero().getText();
		String letra = frame.get_list_letra().getSelectedItem().toString();
		String idcomprobante = sucursal + numero + letra;
		boolean exist = false;
		Object[][] results = data.existe_cpte(idproveedor, this.getTipoTC(),
				idcomprobante);
		if (results != null) {
			if (results.length > 0) {
				exist = true;
			}
		}
		return exist;
	}

	public void _evaluar_letra(JComboBox combo) {

		String letra = combo.getSelectedItem().toString();
		String sucursal = frame.get_txt_sucursal().getText();
		String numero = frame.get_txt_numero().getText();
		String idcomprobante = sucursal + numero + letra;
		if (idcomprobante.length() == 13) {
			frame.get_txt_idcomprobante().setText(idcomprobante);
			frame.get_txt_idcomprobante().requestFocusInWindow();

		} else {
			error("Revise El id de comprobante ingresado");
		}

	}

	private boolean _evaluar_numero(String s) {
		boolean ok = true;
		int num = -1;
		String aux = "";

		if (s.compareTo("") == 0) {
			s = "1";
		}
		try {
			num = new Integer(s);

			aux = "" + num;
			while (aux.length() < 8) {
				aux = "0" + aux;
			}

		} catch (Exception e) {
			ok = false;

		}
		if (ok) {
			this.frame.get_txt_numero().setText(aux);
		}
		return ok;
	}

	private boolean _evaluar_sucursal(String s) {
		boolean ok = true;
		int suc = -1;
		String aux = "";

		if (s.compareTo("") == 0) {
			s = "1";
		}
		try {
			suc = new Integer(s);

			aux = "" + suc;
			while (aux.length() < 4) {
				aux = "0" + aux;
			}

		} catch (Exception e) {
			ok = false;

		}
		if (ok) {
			this.frame.get_txt_sucursal().setText(aux);
		}
		return ok;
	}

	public void fillExtra(String idarticulo) {
		Object[][] results = data.getData(idarticulo);
		frame.get_txt_articulo_().setText("");
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
				frame.get_txt_articulo_().setText(_articulo);
				frame.get_txt_articulo_descripcion().setText(_descripcion);
				frame.get_txt_articulo_stock().setText(_stock);
				frame.get_txt_articulo_linea().setText(_linea);

				if (_suspendidov.compareTo("1") == 0) {
					frame.get_txt_articulo_bloqueado().setText("BLOQUEADO");
					frame.get_txt_articulo_bloqueado().setBackground(Color.DARK_GRAY);
				} else {
					frame.get_txt_articulo_bloqueado().setText("HABILITADO");
					frame.get_txt_articulo_bloqueado().setBackground(
							Color.black);
				}
				double q=data.getPedido(idarticulo);
				q+=data.getPedidoEspecial(idarticulo);
				this.storeArticuloCantidad(idarticulo, q);
				
				if (q<=0){
					frame.get_txt_articulo_pedido().setText("no fue pedido");
					frame.get_txt_articulo_pedido().setBackground(Color.darkGray);
					frame.setJTable7(null);
				}else{
					this.cargar_faltantes(idarticulo);
					frame.get_txt_articulo_pedido().setText(""+q);
					frame.get_txt_articulo_pedido().setBackground(Color.black);
					
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

	private aplicacion.herramientas.java.evaluadores.Vendedor Vendedor = null;

	public void initialize_Vendedor() {
		Vendedor = new aplicacion.herramientas.java.evaluadores.Vendedor() {
			public void cargar(String codigo) {
				Object[][] results = this.getInfo(codigo);
				String descripcion = (String) results[0][1];
				String cod = (String) results[0][0];
				frame.get_txt_idvendedor().setText(cod);
				frame.get_txt_vendedor_descripcion().setText(descripcion);
				transfer_focus_articulos();

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
		String text="";
		try {
			text=tx.getText();
			text=text.replaceAll(" ", "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tx.setText(text);
		if (Vendedor!=null){
			Vendedor.evaluate(tx);	
		}
		
	}

	public void transfer_focus_articulos() {
		System.out.println("TRansfer Focus to Table");
		if (frame.getJTable() == null) {
			this.crear_tabla_items();

		} else {
			if (frame.getJTable().getRowCount() <= 0) {
				this.crear_tabla_items();
			}
		}
		frame.getJTable().requestFocusInWindow();
		frame.getJTable().changeSelection(0, 1, false, false);
		frame.getJTable().editCellAt(0, 1);
		frame.getJTable().transferFocus();
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

		} else {
			ok = false;
		}

		return ok;
	}

	public void cargarVendedor() {
		String iduser = this.getConstructor().getIduser();
		Object[][] results = data.getVendedor(iduser);
		boolean loaded = false;
		if (results != null) {
			if (results.length > 0) {
				String idvendedor = (String) results[0][0];
				String nombre = (String) results[0][1];
				frame.get_txt_idvendedor().setText(idvendedor);
				frame.get_txt_vendedor_descripcion().setText(nombre);
				idvendedor = idvendedor.replaceAll(" ", "");
				loaded = idvendedor.compareTo("") != 0;
				frame.get_txt_idvendedor().setEditable(false);
				frame.get_txt_idcreador().setText(idvendedor);
				frame.get_txt_creador().setText(nombre);
			}
		}
		System.out.println("cargado de vendedor por defecto? " + loaded);
		if (!loaded) {
			if (javax.swing.SwingUtilities.isEventDispatchThread()) {
				Runnable runnable = new Runnable() {
					public void run() {
						elegir_vendedor();
					}
				};
				this.invokeLater(runnable);
			} else {
				elegir_vendedor();
			}

		}
	}

	public void imprimir() {
			frame.getCanvas().reduce();
			frame.getCanvas().lumix_add(2);
			PrinterJob printJob = PrinterJob.getPrinterJob();
	        printJob.setPrintable(frame.getCanvas());
	        if (printJob.printDialog())
	          try { 
	            printJob.print();
	          } catch(PrinterException pe) {
	            System.out.println("Error printing: " + pe);
	       }
	    
	}
}
