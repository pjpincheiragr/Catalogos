package aplicacion.compras.pedidoe.logic;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
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
import java.util.Properties;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;

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
import javax.swing.table.*;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import aplicacion.compras.pedidoe.logic.TableColorCellRenderer;
import aplicacion.compras.pedidoe.constructor._Constructor;
import aplicacion.compras.pedidoe.gui._Etiquetas;
import aplicacion.herramientas.java.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import aplicacion.compras.pedidoe.gui._Frame;
import aplicacion.compras.pedidoe.gui._Email;
import aplicacion.compras.pedidoe.interfaces._Interface;
import java.awt.Rectangle;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Logic;
import aplicacion.modelo.logic.Data;

import aplicacion.herramientas.java.calendario.constructor.*;
import aplicacion.herramientas.java.comprobantes.fvn;
import aplicacion.herramientas.java.launcher.logic.SwingWorker;
import aplicacion.herramientas.java.table.*;
import aplicacion.herramientas.java.xml.Element;
import aplicacion.herramientas.java.*;

public class _Logic extends Logic {
	private _Frame frame = null;
	private _Data data = null;
	private Timer Timer; // @jve:decl-index=0:
	private Crono crono;
	private String estado = "";
	private int current;
	private _Etiquetas etiquetas = null;
	
	private int lenght;
	private boolean done, canceled;
	private int errors = 0;
	private boolean nuevo = true;
	private String _cliente = "";
	private _Email email = null;
	private String emailFrom = "";
	private String password = "";
	private String email_asunto = "";
	public boolean no_a_todo = false;
	public boolean si_a_todo = false;
	public boolean cambios = false;
	String tc = "PEP";
	String equivalencia = "";

	public void setFrame(JFrame _frame) {
		this.frame = (_Frame) _frame;
		super.setFrame(_frame);
	}

	public void setData(Data _data) {
		this.data = (_Data) _data;
		super.setData(_data);
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
	public void evaluate_critico(JComboBox cb) {
		this.cargar_critico();
	}
	public void evaluate_faltantes(JComboBox cb) {
		this.cargar_faltantes();
	}
	public void evaluate_estado(JComboBox cb) {
		if (cb.getSelectedItem().toString().compareTo("Nuevo") == 0) {
			frame.get_chk_seguimiento().setSelected(true);
		}
		if (cb.getSelectedItem().toString().compareTo("Enviado") == 0) {
			frame.get_chk_seguimiento().setSelected(true);
		}
		if (cb.getSelectedItem().toString().compareTo("Recibido") == 0) {
			frame.get_chk_seguimiento().setSelected(false);
		}
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

	public void cargar_equivalencias(JTable table, int row) {
		String idarticulo = table.getValueAt(row, 1).toString();
		this.cargar_equivalencias(idarticulo);
	}

	public void cargar_equivalencias_faltantes(JTable table, int row) {
		String idarticulo = table.getValueAt(row, 1).toString();
		this.cargar_equivalencias_faltantes(idarticulo);
	}

	public void cargar_equivalencias_critico(JTable table, int row) {
		String idarticulo = table.getValueAt(row, 1).toString();
		this.cargar_equivalencias_critico(idarticulo);
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
				this.cargar_equivalencias(idarticulo);
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

	public void setEstado(String estado) {
		for (int i = 0; i < frame.get_lst_estado().getItemCount(); i++) {
			if (frame.get_lst_estado().getItemAt(i).toString()
					.compareTo(estado) == 0) {
				frame.get_lst_estado().setSelectedIndex(i);
			}
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

	public void _envio() {
		if (reporte != null) {
			reporte.dispose();
		}
		String idpedido = frame.get_txt_idpedido().getText();
		String empresa = data.getNombreEmpresa();
		String telefono = data.getTelefonoEmpresa();
		String email = data.getEmail();
		String fecha = frame.get_txt_fecha().getText();
		reporte = new aplicacion.herramientas.java.ireport.constructor._Constructor();

		HashMap param = new HashMap();
		param.put("idpedido", idpedido);
		param.put("fecha", fecha);
		param.put("empresa", empresa);
		param.put("telefono", telefono);
		param.put("email", email);
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
						"rotulo_proveedores.jasper");
		reporte.build(this.getConstructor());
		reporte.init();
	}

	public void envio() {
		String idtransporte = frame.get_txt_idtransporte().getText();
		String idprovincia = frame.get_txt_idprovincia().getText();
		String domicilio = frame.get_txt_domicilio().getText();
		String ciudad = frame.get_txt_idciudad().getText();
		String fecha = frame.get_txt_fecha_envio().getText();
		if (idtransporte.compareTo("") != 0) {
			if (domicilio.compareTo("") != 0) {
				if (ciudad.compareTo("") != 0) {
					if (fecha.compareTo("") != 0) {
						if (!this.esFechaVieja(fecha)) {
								boolean error = this._guardar();
								if (!error) {
									this._envio();
								} else {
									error("Error al Guardar Pedido");
								}
							
						} else {
							error("La fecha de envio no puede ser vieja");
						}

					} else {
						error("Por Favor Ingrese Una Fecha de envio aeste pedido");
						frame.get_txt_fecha_envio().requestFocusInWindow();
					}

				} else {
					error("Por Favor Ingrese La Localidad a la cual enviara este pedido");
					frame.get_txt_idciudad().requestFocusInWindow();
				}
			} else {
				error("Por Favor Ingrese El Domicilio al cual enviara este pedido");
				frame.get_txt_domicilio().requestFocusInWindow();
			}
		} else {
			error("Por Favor Ingrese El Transporte Por El cual enviara este pedido");
			frame.get_txt_idtransporte().requestFocusInWindow();
		}
	}

	public void setNuevo(boolean nuevo) {
		this.nuevo = nuevo;
	}
	
	public void clean() {
		nuevo = false;
		cambios = false;
		no_a_todo = false;
		si_a_todo = false;
		frame.get_lst_categoria().setSelectedIndex(-1);
		frame.get_lst_categoria_faltantes().setSelectedIndex(-1);
		frame.get_lst_stock().setSelectedIndex(-1);
		frame.setJTable(null);
		frame.setJTableCritico(null);
		frame.setJTable_faltantes(null);
		frame.get_txt_fecha().setText("");
		frame.get_txt_idcliente().setText("");
		frame.get_txt_idvendedor().setText("");
		frame.get_txt_cliente_descripcion().setText("");
		frame.get_txt_informacion().setText("");
		frame.get_txt_email().setText("");
		frame.get_txt_tel().setText("");
		frame.get_txt_fax().setText("");
		frame.get_txt_idprovincia().setText("");
		frame.get_txt_cpostal().setText("");
		frame.get_txt_idciudad().setText("");
		frame.get_txt_domicilio().setText("");
		frame.get_txt_provincia_descripcion().setText("");
		frame.get_txt_pedido_descripcion().setText("");
		frame.get_txt_vendedor_descripcion().setText("");
		frame.get_txt_articulo().setText("");
		frame.get_txt_articulo_descripcion().setText("");
		frame.get_txt_articulo_stock().setText("");
		frame.get_txt_idpedido().setText("");
		frame.get_txt_items().setText("");
		frame.get_txt_idcreador().setText("");
		frame.get_txt_creador().setText("");
		frame.get_txt_informacion().setText("");
		frame.get_txt_iva().setText("");
		frame.get_txt_total().setText("");
		frame.get_txt_modificado().setText("");
		frame.get_txt_subtotal().setText("");
		frame.get_txt_idpedido().setEditable(true);
		frame.get_txt_fecha_envio().setText("");
		frame.get_txt_guia().setText("");
		frame.get_txt_idtransporte().setText("");
		frame.get_txt_transporte_descripcion().setText("");
		frame.get_txt_fecha_creacion().setText("");
		frame.get_btn_guardar().setEnabled(false);

		frame.get_btn_preparar().setEnabled(false);
		frame.get_btn_presupuesto().setEnabled(false);
		frame.get_btn_remito().setEnabled(false);
		frame.get_btn_envio().setEnabled(false);
		frame.get_btn_eliminar().setEnabled(false);
		frame.get_btn_identificador().setEnabled(false);
		frame.get_txt_observacion().setText("");
		frame.setJTable_equivalencias(null);
		frame.setJTable_equivalencias_critico(null);
		frame.setJTable_equivalencias_faltantes(null);
		frame.setJTableCritico(null);

		this.getToday();

		// this.obtener_proximo_cpte();
		frame.get_txt_idpedido().setText("");
		this.focus();
		frame.getLockableUI().setLocked(true);
		this.clean_pdc();
		frame.setJTable11(null);
		frame.setJTable12(null);
		frame.get_lst_estado().setSelectedIndex(0);
	}

	public void nuevo() {
		if (this.cambios) {
			if (preguntar("Guardar", "Desea Guardar los cambios realizados?")) {
				this.guardar();
			}
		}
		this.clean();
		this.nuevo = true;
		this.obtener_proximo_cpte();

		this.evaluar_numero(frame.get_txt_idpedido());

	}

	public void focus_cuenta() {
		try {
			frame.getJTable().getCellEditor().stopCellEditing();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame.get_txt_idcliente().requestFocusInWindow();
	}

	public void nuevo(String idvendedor) {
		nuevo();

		frame.get_txt_idvendedor().setText(idvendedor);
		this.evaluarVendedor(frame.get_txt_idvendedor());
		frame.get_txt_idcliente().requestFocusInWindow();
	}

	/*
	 * private aplicacion.herramientas.java.evaluadores.Condicion
	 * Condicion=null; public void initialize_Condicion(){ Condicion=new
	 * aplicacion.herramientas.java.evaluadores.Condicion(){ public void
	 * cargar(String codigo){ Object[][] results=this.getInfo(codigo); String
	 * descripcion=(String) results[0][1]; String cod=(String) results[0][0];
	 * frame.get_txt_idcondicion().setText(cod);
	 * frame.get_txt_condicion_detalle().setText(descripcion); guardar(); }
	 * 
	 * public void completar(String codigo){ Object[][]
	 * results=this.getInfo(codigo); String descripcion=(String) results[0][1];
	 * String cod=(String) results[0][0];
	 * frame.get_txt_idcondicion().setText(cod);
	 * frame.get_txt_condicion_detalle().setText(descripcion);
	 * 
	 * } }; Condicion.setConstructor(this.getConstructor()); } public void
	 * BuscarCondicion(JTextField tx){ Condicion.Buscar(tx); } public void
	 * BuscarCondicion(){ Condicion.Buscar(frame.get_txt_idcondicion()); }
	 * public void buscarCondicion(JTextField tx){ Condicion.buscar(tx); }
	 * 
	 * public void evaluarCondicion(JTextField tx){ Condicion.evaluate(tx); }
	 */

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
		cambios = true;
		tx.setText(tx.getText().replaceAll(" ", ""));
		Vendedor.evaluate(tx);
	}

	public void BuscarFechaEnvio() {
		this.BuscarFecha(frame.get_txt_fecha_envio());
	}

	public void getToday() {
		_Frame _frame = (_Frame) this._frame;
		_frame.get_txt_fecha().setText(
				new Convertidor().getDateWithFormat("dd-MM-yyyy"));
		String hasta = new Convertidor().getDateWithFormat("dd-MM-yyyy");
		String desde = this.getDaysRoll(hasta, -7);
		frame.get_txt_fecha_desde().setText(desde);
		frame.get_txt_fecha_hasta().setText(hasta);
		String desde_faltante = this.getDaysRoll(hasta, -14);
		frame.get_txt_fecha_desde_faltante().setText(desde_faltante);
		frame.get_txt_fecha_hasta_faltante().setText(hasta);

	}

	public void focus() {
		frame.get_txt_idpedido().requestFocusInWindow();
	}

	public _Data getData() {
		return this.data;
	}

	public void obtener_proximo_cpte() {

		String cb = data.getProximoPGCorrecto(tc);
		frame.get_txt_idpedido().setText(cb);

	}

	public void editCell(int row, int col) {
		JTable table = frame.getJTable();
		table.changeSelection(row, col, false, false);
		table.editCellAt(row, col);
		table.transferFocus();
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
			try {
				model.removeRow(row);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (model.getRowCount() <= 0) {
				model.setRowCount(1);
				model.setValueAt(true, 0, 0);
				this.editCell(0, 1);
			}
			this._calculate();
			cambios = true;
		}
	}

	public void clean_pdc() {
		frame.get_txt_pdc_descripcion().setText("");
		frame.get_txt_pdc_idpedido().setText("");
		frame.get_txt_pdc_informacion().setText("");
		frame.get_txt_pdc_vendedor().setText("");
	}

	public void clean_pdc_items(String idpedido) {

		int n = this.preguntar("Confirmar",
				"Elimina los articulos del pedido de cliente borrado?",
				new String[] { "Si", "No. Conservar los articulos" },
				"No. Conservar los articulos");

		for (int i = frame.getJTable().getRowCount() - 1; i >= 0; i--) {
			String _idpedido = "";
			try {
				_idpedido = frame.getJTable().getValueAt(i, 8).toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (_idpedido.compareTo(idpedido) == 0) {
				if (n == 1) {
					frame.getJTable().setValueAt("", i, 8);
				} else {
					DefaultTableModel model = (DefaultTableModel) frame
							.getJTable().getModel();
					model.removeRow(i);
					if (model.getRowCount() <= 0) {
						model.setRowCount(1);
						model.setValueAt(true, 0, 0);
						this.editCell(0, 1);
					}
					this._calculate();
				}
			}
		}
	}

	private List<String> getInstruccionesGuardarPDCs() {
		String idpedido = frame.get_txt_idpedido().getText();
		List<String> instrucciones = new ArrayList<String>();
		String instruccion = this.getInstruccionEncabezado();
		instrucciones.add(data.getDeletePEP_PDC(idpedido));
		for (int i = 0; i < frame.getJTable11().getRowCount(); i++) {
			String idpedido_pdc = "";
			try {
				idpedido_pdc = frame.getJTable11().getValueAt(i, 0).toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (idpedido_pdc.compareTo("") != 0) {
				instrucciones
						.add(data.getInsertPEP_PDC(idpedido, idpedido_pdc));
			}
		}
		return instrucciones;
	}

	public void borrarRenglon_pdc(int row) {
		String idpedido = frame.getJTable11().getValueAt(row, 0).toString();
		if (confirmar("Confirme Elimina el renglon " + idpedido
				+ " de la tabla?", 3)) {
			try {
				frame.getJTable11().getCellEditor().stopCellEditing();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
			DefaultTableModel model = (DefaultTableModel) frame.getJTable11()
					.getModel();
			model.removeRow(row);
			if (model.getRowCount() <= 0) {
				model.setRowCount(1);
				model.setValueAt(true, 0, 0);
				this.editCell(model.getRowCount() - 1, 0);
			}
			this.clean_pdc();
			this.clean_pdc_items(idpedido);
			cambios = true;
		}

	}

	public boolean puede_eliminar_remito(String remito) {
		System.out.println("Puede Eliminar Remito " + remito + "?");
		boolean free = true;
		Object[][] results = data.tieneComprobanteBetaAsociado(remito);
		if (results != null) {
			if (results.length > 0) {
				free = false;
				String tc = (String) results[0][0];
				String idcomprobante = (String) results[0][1];
				error("No puede eliminar este remito porque esta asociado al comprobante "
						+ tc + "-" + idcomprobante);
			}
		}
		if (free) {
			results = data.tieneComprobanteAlfaAsociado(remito);
			if (results != null) {
				if (results.length > 0) {
					free = false;
					String tc = (String) results[0][0];
					String idcomprobante = (String) results[0][1];
					error("No puede eliminar este remito porque esta asociado al comprobante "
							+ tc + "-" + idcomprobante);

				}
			}
		}
		return free;
	}

	public void eliminar_pedido() {

		boolean ok = true;
		String idpedido = frame.get_txt_idpedido().getText();

		if (ok) {
			error("ATENCION: ESTA POR ELIMINAR ESTE PEDIDO");
			String NEW_LINE = System.getProperty("line.separator");
			String msj = "Confirme la eliminacion del pedido. Tambien Eliminara los remitos generados!!!";
			msj += NEW_LINE + "Codigo:";
			ok = confirmar(msj, 2);
			if (ok) {
				this._eliminar_pedido(idpedido);
			}
		}
	}

	public void _eliminar_pedido(String idpedido) {
		boolean ok = true;

		data.beginTransaction();
		data.clearBatch();

		if (ok) {
			data.addBatch(data.getEliminar(idpedido));
			boolean error = data.executeBatch();
			if (!error) {
				ok = true;
				data.commitTransaction();
				aviso("Pedido eliminado Correctamente");

				frame.get_btn_guardar().setEnabled(!ok);
				frame.get_btn_preparar().setEnabled(!ok);
				frame.get_btn_presupuesto().setEnabled(!ok);
				frame.get_btn_remito().setEnabled(!ok);
				frame.get_btn_envio().setEnabled(!ok);
				frame.get_btn_eliminar().setEnabled(!ok);
			} else {
				error("no se pudo eliminar el pedido");
				data.rollbackTransaction();
			}

			data.setAutoCommit();

		}

	}

	private aplicacion.herramientas.java.evaluadores.PedidoEspecial PEP = null;

	public void initialize_PEP() {
		PEP = new aplicacion.herramientas.java.evaluadores.PedidoEspecial() {
			public void cargar(String codigo) {
				cargar_pedido(codigo);
			}
		};
		PEP.setConstructor(this.getConstructor());
	}

	public void reconnect_PEP() {
		if (PEP != null) {
			PEP.setConstructor(this.getConstructor());
		}

	}

	public void BuscarPEP(JTextField tx) {
		PEP.Buscar(tx);
	}

	public void BuscarPEP() {
		PEP.Buscar(frame.get_txt_idpedido());
	}

	public void buscarPEP(JTextField tx) {
		PEP.buscar(tx);
	}

	public void evaluarPEP(JTextField tx) {
		PEP.evaluate(tx);
	}

	private aplicacion.herramientas.java.evaluadores.PedidoCliente PDC = null;

	public void initialize_PDC() {
		PDC = new aplicacion.herramientas.java.evaluadores.PedidoCliente() {
			public void cargar(String codigo) {
				// cargar_pedido(codigo);
			}
		};
		PDC.setConstructor(this.getConstructor());
	}

	public void reconnect_PDC() {
		if (PDC != null) {
			PDC.setConstructor(this.getConstructor());
		}

	}

	public void BuscarPDC(JTextField tx) {
		PDC.Buscar(tx);
	}

	public void BuscarPDC() {
		PDC.Buscar(frame.get_txt_idpedido());
	}

	public void buscarPDC(JTextField tx) {
		PDC.buscar(tx);
	}

	public void evaluarPDC(JTextField tx) {
		PDC.evaluate(tx);
	}

	public void evaluar_numero(JTextField tx) {

		String numero = tx.getText();

		if (data.existe(numero)) {
			if (!nuevo) {
				this.evaluarPEP(tx);
			} else {
				String proximo = data.getProximoPGCorrecto(tc);
				frame.get_txt_idpedido().setText(proximo);
				this.evaluar_numero(frame.get_txt_idpedido());

			}

		} else {

			String correcto = data.getProximoPGCorrecto(tc);
			if (correcto.compareTo(numero) == 0) {
				System.out.println("Nuevo " + tc + " " + numero);

				frame.get_btn_guardar().setEnabled(true);
				frame.get_btn_preparar().setEnabled(true);
				frame.get_btn_presupuesto().setEnabled(true);
				frame.get_btn_remito().setEnabled(true);
				frame.get_btn_envio().setEnabled(true);
				frame.getLockableUI().setLocked(false);
				frame.get_btn_identificador().setEnabled(true);
				nuevo = true;
				tx.setEditable(false);
				frame.get_txt_idcliente().setEnabled(true);
				frame.get_txt_idvendedor().setEnabled(true);
				frame.get_btn_buscar_cliente().setEnabled(true);
				frame.get_btn_cargar_cliente().setEnabled(true);

				this.limpiar_proveedor();
				this.cargarVendedor();
				this.fillCreado();
				this.fillModificado();
				this.crear_tabla_pdc();
				frame.get_txt_pedido_descripcion().setText("PEDIDO ESPECIAL");
				frame.get_txt_pedido_descripcion().setSelectionStart(0);
				frame.get_txt_pedido_descripcion().setSelectionEnd(
						frame.get_txt_pedido_descripcion().getText().length());
				frame.get_txt_pedido_descripcion().requestFocusInWindow();
			} else {
				this.evaluarPEP(tx);
			}
		}
	}

	public void addItem(boolean seleccionado, String idarticulo,
			String descripcion, String cantidad, String costo, String precio,
			String descuento, String pedido) {
		JTable table = frame.getJTable();
		if (table == null) {
			this.crear_tabla_items();
			table = frame.getJTable();
		} else {
			if (table.getRowCount() <= 0) {
				this.crear_tabla_items();
				table = frame.getJTable();
			}
		}

		int row = table.getModel().getRowCount() - 1;
		if (table.getValueAt(row, 1).toString().compareTo("") != 0) {
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setRowCount(model.getRowCount() + 1);
			row = model.getRowCount() - 1;
		}
		table.setValueAt(seleccionado, row, 0);
		table.setValueAt(idarticulo, row, 1);
		table.setValueAt(descripcion, row, 2);
		table.setValueAt(cantidad, row, 3);
		table.setValueAt(costo, row, 4);
		table.setValueAt(precio, row, 5);
		table.setValueAt(descuento, row, 6);
		table.setValueAt(pedido, row, 8);
		this.eval_variables(row, cantidad, precio, descuento);
	}

	public void cargar_items(String idpedido) {
		Object[][] results = data.getPedidoItems(idpedido);
		if (results != null) {
			if (results.length > 0) {
				for (int i = 0; i < results.length; i++) {
					String idarticulo = (String) results[i][0];
					String descripcion = (String) results[i][1];
					String cantidad = (String) results[i][2];
					String costo = (String) results[i][3];
					String precio = (String) results[i][4];
					String descuento = "0.0";
					String seleccionado = (String) results[i][5];
					String pedido = (String) results[i][6];
					boolean selected = false;
					selected = seleccionado.compareTo("1") == 0;
					this.addItem(selected, idarticulo, descripcion, cantidad,
							costo, precio, descuento, pedido);
				}
			}
		}
	}

	private void crear_tabla_items() {
		System.out.println("Create Table of Items");
		Object[][] results = new Object[][] { { true, "", "", "", "", "", "",
				"", "" } };
		this.create_table(results);
	}

	private void crear_tabla_pdc() {
		System.out.println("Create Table of PDC");
		Object[][] results = new Object[][] { { true, "", "", "", "", "", "" } };
		this.create_table_pdc(results);
	}

	private void crear_tabla_pdc_articulos() {
		System.out.println("Create Table of PDC Items");
		Object[][] results = new Object[][] { { true, "", "", "" } };
		this.create_table_pdc_articulos(results);
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
		col.setWidth(280);
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

		col = new Column();
		col.setName("Pedido");
		col.setWidth(80);
		col.setEditable(true);
		col.setAligment(JTextField.RIGHT);
		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_items_pedido);
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

	private void create_table_pdc(Object[][] results) {

		CustomTable table = new CustomTable();
		table.setSortable(false);
		Column col = null;

		col = new Column();
		col.setName("idpedido");
		col.setWidth(100);
		col.setEditable(true);
		CellEditor pce = new CellEditor();
		pce.setAligment(JTextField.RIGHT);
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_remitos_idpedido);
		pce.setTipo(String.class);

		col.setCellEditor(pce.getCellEditor());
		table.addColumn(col);

		col = new Column();
		col.setName("descripcion");
		col.setWidth(240);
		col.setEditable(false);
		table.addColumn(col);

		col = new Column();
		col.setName("cliente");
		col.setWidth(80);
		col.setEditable(false);
		table.addColumn(col);

		col = new Column();
		col.setName("nombre");
		col.setWidth(140);
		col.setEditable(false);
		table.addColumn(col);

		col = new Column();
		col.setName("vendedor");
		col.setWidth(140);
		col.setEditable(false);
		table.addColumn(col);

		col = new Column();
		col.setName("fecha");
		col.setWidth(120);
		col.setEditable(false);
		table.addColumn(col);

		table.setData(results);
		table.addKeyListener(this.getConstructor().getKeyListener());
		table.addMouseListener(this._constructor.getMouseListener());
		Font fuente = new Font("Dialog", Font.PLAIN, 10);
		table.setHeaderFont(fuente);
		table.setFont(fuente);
		table.setName(_Interface._table_remitos);
		table.build();
		table.fillData();

		JTable _table = table.getTable();
		frame.setJTable11(_table);
	}

	private void create_table_pdc_articulos(Object[][] results) {
		CustomTable table = new CustomTable();
		table.setSortable(false);
		Column col = null;

		col = new Column();
		col.setName("");
		col.setWidth(40);
		col.setClass(Boolean.class);
		col.setEditable(true);
		table.addColumn(col);

		col = new Column();
		col.setName("idarticulo");
		col.setWidth(120);
		col.setEditable(false);
		table.addColumn(col);

		col = new Column();
		col.setName("descripcion");
		col.setWidth(240);
		col.setEditable(false);
		table.addColumn(col);

		col = new Column();
		col.setName("cantidad");
		col.setWidth(80);
		col.setEditable(false);
		table.addColumn(col);

		table.setData(results);
		table.addKeyListener(this.getConstructor().getKeyListener());
		table.addMouseListener(this._constructor.getMouseListener());
		Font fuente = new Font("Dialog", Font.PLAIN, 10);
		table.setHeaderFont(fuente);
		table.setFont(fuente);
		table.setName(_Interface._table_pdc_articulos);
		table.build();
		table.fillData();

		JTable _table = table.getTable();
		frame.setJTable12(_table);
	}

	private void cargarProveedor(String idcliente) {
		Object[][] results = this.data.getCliente(idcliente);
		if (results != null) {
			if (results.length > 0) {
				frame.get_txt_cliente_descripcion().setEditable(true);
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
				frame.get_txt_cliente_descripcion().setText(descripcion);
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

				frame.get_txt_cliente_descripcion().requestFocusInWindow();
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
							if (this.data.utilizaPrecioPublico(_cliente) != this.data
									.utilizaPrecioPublico(idcliente)) {
								if (confirmar(
										"Para Recalcular Precios Ingrese el Codigo:",
										2)) {
									this._recalculate_precios();
								}
							}

						}

					}
				}
				_cliente = idcliente;
			}
		}
	}

	private aplicacion.herramientas.java.evaluadores.Proveedor proveedor = null;

	public void initialize_proveedor() {
		proveedor = new aplicacion.herramientas.java.evaluadores.Proveedor() {
			public void cargar(String codigo) {
				cargarProveedor(codigo);
			}
		};
		proveedor.setConstructor(this.getConstructor());
	}

	public void reconnect_cliente() {
		if (proveedor != null) {
			proveedor.setConstructor(this.getConstructor());
		}
	}

	public void BuscarProveedor(JTextField tx) {
		proveedor.Buscar(tx);
	}

	public void buscarCliente(JTextField tx) {
		proveedor.buscar(tx);
	}

	public void BuscarCliente() {
		proveedor.Buscar(frame.get_txt_idcliente());
	}

	public void evaluarCliente(JTextField tx) {
		cambios = true;
		proveedor.evaluate(tx);
	}

	public void evaluate_descripcion(JTextField tx) {
		cambios = true;
		String valor = tx.getText();
		if (valor.compareTo("") != 0) {
			frame.get_txt_idcliente().requestFocusInWindow();

		} else {
			error("Ingrese una descripcion breve");
		}
	}

	public void evaluate_cliente_descripcion(JTextField tx) {
		cambios = true;
		String valor = tx.getText();
		if (valor.compareTo("") != 0) {

			String idcliente = frame.get_txt_idcliente().getText();
			Object[][] results = data.getCliente(idcliente);
			String _descripcion = "";
			if (results != null) {
				if (results.length > 0) {
					_descripcion = (String) results[0][1];
				}
			}
			if (valor.compareTo(_descripcion) == 0) {

			} else {

				// frame.get_txt_idprovincia().requestFocusInWindow();
			}
			frame.get_txt_idvendedor().requestFocusInWindow();
			// frame.get_txt_idprovincia().requestFocusInWindow();
		} else {

			tx.setText("Consumidor Final");
			tx.setSelectionStart(0);
			tx.setSelectionEnd(tx.getText().length());
			tx.requestFocusInWindow();
		}

	}

	public void evaluate_informacion(JTextArea txa) {
		cambios = true;
		String value = "";
		value = txa.getText();
		frame.get_txt_idvendedor().requestFocusInWindow();
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

	public boolean _tiene_idpedido(String idpedido) {
		boolean exist = false;
		int i = 0;
		if (frame.getJTable11() != null) {
			while (i < frame.getJTable11().getRowCount() & !exist) {
				exist = ((String) frame.getJTable11().getValueAt(i, 1))
						.compareTo(idpedido) == 0;
				i++;
			}
		}
		return exist;
	}

	public void mostrar_pedido(String idpedido) {
		Object[][] results = data.getPedidoPDC(idpedido);
		if (results != null) {
			if (results.length > 0) {
				String descripcion = (String) results[0][0];
				String cliente = (String) results[0][3];
				String cliente_descripcion = (String) results[0][4];
				String info = (String) results[0][5];
				String vendedor = (String) results[0][7];
				String fecha = (String) results[0][1];
				frame.get_txt_pdc_descripcion().setText(descripcion);
				frame.get_txt_pdc_idpedido().setText(idpedido);
				frame.get_txt_pdc_vendedor().setText(vendedor);
				frame.get_txt_pdc_informacion().setText(info);
				this.cargar_pdc_articulos(idpedido);
			}
		}
	}

	public void mostrar_pedido(int row, JTable table) {
		String idpedido = "";
		try {
			idpedido = (String) table.getValueAt(row, 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (idpedido.compareTo("") != 0) {
			this.mostrar_pedido(idpedido);
		}

	}

	public void _evaluate_idpedido(JTextField tx, int row) {
		cambios = true;
		String idpedido = tx.getText();
		Object[][] results = data.getPedidoPDC(idpedido);
		JTable table = frame.getJTable11();
		boolean exist = false;

		if (results != null) {
			if (results.length > 0) {
				exist = true;
				String descripcion = (String) results[0][0];
				String cliente = (String) results[0][3];
				String cliente_descripcion = (String) results[0][4];
				String vendedor = (String) results[0][7];
				String fecha = (String) results[0][1];
				table.setValueAt(descripcion, row, 1);
				table.setValueAt(cliente, row, 2);
				table.setValueAt(cliente_descripcion, row, 3);
				table.setValueAt(vendedor, row, 4);
				table.setValueAt(fecha, row, 5);
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				this.mostrar_pedido(idpedido);
				if (row == model.getRowCount() - 1) {
					model.setRowCount(model.getRowCount() + 1);

				}
				table.changeSelection(row + 1, 0, false, false);
				table.editCellAt(row + 1, 0);
				table.transferFocus();
			}
		}
		if (!exist) {
			this.evaluarPDC(tx);
		}
	}

	public void setDefaultTab() {
		frame.getJTabbedPane().setSelectedIndex(1);
	}

	public void _evaluate_idarticulo(JTextField tx, int row) {
		cambios = true;
		String idpedido = frame.get_txt_idpedido().getText();
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
				if (_stock <= 0) {

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
					double qty = data.getPedidoCantidad(idarticulo, idpedido);
					if (qty > 0) {
						error("Ya se Pidio este item en otro pedido que aun no se ha recibido. Verifique");
					}
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
						//e.printStackTrace();
					}
					double _precio = 0.0;
					double _costo = 0.0;
					double _cantidad = 0.0;
					double _total = 0.0;

					try {
						_cantidad = new Double(cantidad.replaceAll(",", ""));
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
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

	public boolean check_articulos() {
		boolean ok = true;
		if (frame.getJTable() != null) {
			int i = 0;
			while (i < frame.getJTable().getRowCount() & ok) {
				if (!this.eval_row_empty(i)) {
					ok = this.eval_row(i);
				} else {

					ok = this.eval_incomplete_row(i);
					if (!ok) {
						if (!nuevo) {
							error("La fila "
									+ i
									+ " de Articulos Esta Incompleta. Verifique que los datos (idarticulo+descripcion+cantidad+precio) sean correctos");
						}
					} else {

					}

				}
				i++;
			}
		}
		return ok;
	}

	public boolean eval_incomplete_row(int row) {
		boolean incomplete = false;
		String descripcion = null;
		String cantidad = null;
		String precio = null;
		try {
			descripcion = (String) frame.getJTable().getValueAt(row, 2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			cantidad = (String) frame.getJTable().getValueAt(row, 3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		try {
			precio = (String) frame.getJTable().getValueAt(row, 5);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		incomplete = (precio == null | cantidad == null | descripcion == null);
		if (!incomplete) {
			incomplete = (precio.compareTo("") == 0
					& cantidad.compareTo("") == 0 & descripcion.compareTo("") == 0);
		}

		return incomplete;

	}

	public boolean eval_row(int row) {
		boolean ok = true;
		String idarticulo = null;
		String descripcion = null;
		String cantidad = null;
		String precio = null;
		boolean seleccionado = false;
		try {
			seleccionado = (Boolean) frame.getJTable().getValueAt(row, 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ok = false;

		}
		if (ok) {
			try {
				idarticulo = (String) frame.getJTable().getValueAt(row, 1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ok = false;
			}
			if (idarticulo.compareTo("") != 0) {
				if (idarticulo.compareTo("*") == 0) {
					ok = true;
				} else {
					ok = data.existeArticulo(idarticulo);
					if (!ok) {
						error("Error en fila " + row + ". El articulo ("
								+ idarticulo + ") no existe ");
					}
				}
			} else {
				ok = false;
			}

		}

		if (ok) {
			try {
				descripcion = (String) frame.getJTable().getValueAt(row, 2);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ok = false;
			}
		}
		if (ok) {
			try {
				cantidad = (String) frame.getJTable().getValueAt(row, 3);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ok = false;
			}
		}

		if (ok) {
			try {
				precio = (String) frame.getJTable().getValueAt(row, 5);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ok = false;
			}
		}
		return ok;
	}

	public boolean eval_row_empty(int row) {
		boolean empty = false;
		String idarticulo = null;

		try {
			idarticulo = (String) frame.getJTable().getValueAt(row, 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			empty = true;
		}
		if (idarticulo != null) {
			if (idarticulo.compareTo("") != 0) {
				empty = false;

			} else {
				empty = true;
			}
		} else {
			empty = true;
		}

		return empty;
	}

	public void _delete_item_articulo(JTextField tx, int row) {
		String value = tx.getText();
		if (value.compareTo("") == 0) {
			this.borrarRenglon(row);
		}
	}

	public void _delete_remito_idpedido(JTextField tx, int row) {
		String value = tx.getText();
		if (value.compareTo("") == 0) {
			this.borrarRenglon_pdc(row);
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

	public void _eval_critico_cantidad(JTextField tx, int row) {
		String aux = "";
		cambios = true;

		try {
			aux = tx.getText();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		boolean error = false;
		double cantidad = 0;
		try {
			cantidad = new Double(aux);
		} catch (NumberFormatException e) {
			error = true;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!error) {
			frame.getJTable_critico().setValueAt(cantidad > 0, row, 0);
			if (row < frame.getJTable_critico().getRowCount() - 1) {
				frame.getJTable_critico().changeSelection(row + 1, 7, false,
						false);
				frame.getJTable_critico().editCellAt(row + 1, 7);
				frame.getJTable_critico().transferFocus();
			}

		} else {
			error("Error en cantidad");
			tx.requestFocusInWindow();
		}

	}

	public void _eval_faltante_cantidad(JTextField tx, int row) {
		String aux = "";
		cambios = true;

		try {
			aux = tx.getText();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		boolean error = false;
		double cantidad = 0;
		try {
			cantidad = new Double(aux);
		} catch (NumberFormatException e) {
			error = true;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!error) {
			frame.getJTable_faltantes().setValueAt(cantidad > 0, row, 0);
			if (row < frame.getJTable_faltantes().getRowCount() - 1) {
				frame.getJTable_faltantes().changeSelection(row + 1, 8, false,
						false);
				frame.getJTable_faltantes().editCellAt(row + 1, 8);
				frame.getJTable_faltantes().transferFocus();
			}

		} else {
			error("Error en cantidad");
			tx.requestFocusInWindow();
		}

	}

	public void _eval_remitos_idpedido(JTextField tx, int row) {
		String aux = "";
		cambios = true;

		try {
			aux = tx.getText();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		tx.requestFocusInWindow();
		if (aux.compareTo("") != 0) {

			_evaluate_idpedido(tx, row);
		} else {
			aviso("Ingrese numero de pedido de cliente. Busque con F5");
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

	public aplicacion.herramientas.java.visualizadores.Articulo vArticulo = null;
	private aplicacion.herramientas.java.importadores.Articulo bArticulo = null;

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

	private aplicacion.herramientas.java.importadores.PedidoCliente bPDC = null;

	public void BuscarPedidoCliente(JTextField ext) {
		if (bPDC != null) {
			bPDC.close();
		}

		bPDC = new aplicacion.herramientas.java.importadores.PedidoCliente(this
				.getConstructor()) {
			public void cargar(Object[] seleccion) {
				agregar_pedido(seleccion);
			}
		};
		bPDC.Buscar(ext);
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
		/*
		 * if (logic!=null){
		 * logic.setPedido((aplicacion.compras.pedidoe.constructor
		 * ._Constructor)this.getConstructor()); }
		 */

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

		aplicacion.ventas.catalogo.logic._Logic logic = (aplicacion.ventas.catalogo.logic._Logic) Catalogo
				.getLogic();
		if (logic != null) {
			/*
			 * logic.setPedido((aplicacion.compras.pedidoe.constructor._Constructor
			 * )this.getConstructor());
			 */
		}

	}

	public void agregar(Object[] seleccion) {
		cambios = true;
		String idpedido_pdc = frame.get_txt_pdc_idpedido().getText();
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
		Object[][] results = data.getArticulo(idarticulo);
		if (results != null) {
			if (results.length > 0) {

				int exist = this.existArticulo(idarticulo);
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
					table.setValueAt(idpedido_pdc, row, 8);
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

	public void agregar2(Object[] seleccion) {
		String idpedido_pdc = frame.get_txt_pdc_idpedido().getText();
		this.agregar2(seleccion, idpedido_pdc);
	}

	public void agregar2(Object[] seleccion, String idpedido_pdc) {

		cambios = true;
		String idpedido = frame.get_txt_idpedido().getText();

		String idarticulo = (String) seleccion[0];
		String descripcion = (String) seleccion[1];
		String cantidad = (String) seleccion[2];
		// String cantidad="1.0";
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
				int exist = -1;
				if (idarticulo.compareTo("*") == 0) {

				} else {
					exist = this.existArticulo(idarticulo);
				}

				String costo = results[0][2].toString();
				String precio = results[0][1].toString();
				if (exist >= 0) {
					error("El articulo " + idarticulo
							+ " ya se encuentra en el pedido.");
					if (confirmar("Confirme para agregarlo de todas formas:", 2)) {
						exist = -1;
					}
				}
				if (exist < 0) {
					int row = 0;
					if (table != null) {

						int rows = table.getRowCount();
						if (rows > 0) {
							row = this.existEmpty();
							if (row < 0) {
								DefaultTableModel model = (DefaultTableModel) table
										.getModel();
								model.setRowCount(model.getRowCount() + 1);
								row = model.getRowCount() - 1;
							}
						} else {
							this.crear_tabla_items();
							table = frame.getJTable();
						}

					} else {
						this.crear_tabla_items();
						table = frame.getJTable();
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
					table.setValueAt(idpedido_pdc, row, 8);
					this.eval_variables(row, cantidad, precio, descuento);
					table.changeSelection(row, 1, false, false);
					table.editCellAt(row, 1);
					table.transferFocus();
					double qty = data.getPedidoCantidad(idarticulo, idpedido);
					if (qty > 0) {
						error("Ya se Pidio este item en otro pedido que aun no se ha recibido. Verifique");
					}
				}

				System.out.println(idarticulo + " " + descripcion + " "
						+ cantidad + " " + costo + " " + precio);

			}
		}
		this._calculate();
	}

	public void agregar_pdc(Object[] seleccion) {
		cambios = true;
		String idpedido = frame.get_txt_idpedido().getText();

		String idarticulo = (String) seleccion[0];
		String descripcion = (String) seleccion[1];
		String cantidad = (String) seleccion[2];
		String idpedido_pdc = (String) seleccion[3];
		// String cantidad="1.0";
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
					table.setValueAt(idpedido_pdc, row, 8);
					this.eval_variables(row, cantidad, precio, descuento);
					table.changeSelection(row, 1, false, false);
					table.editCellAt(row, 1);
					table.transferFocus();
					double qty = data.getPedidoCantidad(idarticulo, idpedido);
					if (qty > 0) {
						error("Ya se Pidio este item en otro pedido que aun no se ha recibido. Verifique");
					}
				}

				System.out.println(idarticulo + " " + descripcion + " "
						+ cantidad + " " + costo + " " + precio);

			}
		}
		this._calculate();
	}

	public void agregar_pedido(String idpedido) {
		JTable table = frame.getJTable11();
		Object[][] results = data.getPedidoPDC(idpedido);
		if (results != null) {
			if (results.length > 0) {
				String descripcion = (String) results[0][0];
				String fecha = (String) results[0][1];
				String cliente = (String) results[0][3];
				String cliente_descripcion = (String) results[0][4];
				String vendedor = (String) results[0][7];
				int exist = this.existPDC(idpedido);

				if (exist < 0) {
					int row = 0;
					if (table != null) {

						row = this.existEmptyPDC();
						if (row < 0) {
							DefaultTableModel model = (DefaultTableModel) table
									.getModel();
							model.setRowCount(model.getRowCount() + 1);
							row = model.getRowCount() - 1;
						}

					} else {
						this.crear_tabla_pdc();
					}

					table.setValueAt(idpedido, row, 0);
					table.setValueAt(descripcion, row, 1);
					table.setValueAt(cliente, row, 2);
					table.setValueAt(cliente_descripcion, row, 3);
					table.setValueAt(vendedor, row, 4);
					table.setValueAt(fecha, row, 5);

					table.changeSelection(row, 0, false, false);
					table.editCellAt(row, 0);
					table.transferFocus();
				}

				System.out.println(idpedido + " " + descripcion);

			}
		}

	}

	/**
	 * []seleccion idpedido|descripcion|
	 * 
	 * @param seleccion
	 */
	public void agregar_pedido(Object[] seleccion) {
		cambios = true;
		String idpedido = (String) seleccion[0];
		String descripcion = (String) seleccion[1];
		String fecha = (String) seleccion[2];
		String cliente = (String) seleccion[3];
		String cliente_descripcion = (String) seleccion[4];
		String vendedor = (String) seleccion[5];

		JTable table = frame.getJTable11();
		try {
			table.getCellEditor().stopCellEditing();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		Object[][] results = data.getPedidoPDC(idpedido);
		if (results != null) {
			if (results.length > 0) {
				descripcion = (String) results[0][0];
				cliente = (String) results[0][3];
				cliente_descripcion = (String) results[0][4];
				vendedor = (String) results[0][7];
				int exist = this.existPDC(idpedido);

				if (exist < 0) {
					int row = 0;
					if (table != null) {

						row = this.existEmptyPDC();
						if (row < 0) {
							DefaultTableModel model = (DefaultTableModel) table
									.getModel();
							model.setRowCount(model.getRowCount() + 1);
							row = model.getRowCount() - 1;
						}

					} else {
						this.crear_tabla_pdc();
					}

					table.setValueAt(idpedido, row, 0);
					table.setValueAt(descripcion, row, 1);
					table.setValueAt(cliente, row, 2);
					table.setValueAt(cliente_descripcion, row, 3);
					table.setValueAt(vendedor, row, 4);
					table.setValueAt(fecha, row, 5);

					table.changeSelection(row, 0, false, false);
					table.editCellAt(row, 0);
					table.transferFocus();
				}

				System.out.println(idpedido + " " + descripcion);

			}
		}

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

	public int existEmptyPDC() {
		int exist = -1;
		int i = 0;
		JTable table = frame.getJTable11();
		if (table != null) {
			while (i < table.getRowCount() & exist < 0) {
				String _idpedido = "";
				try {
					_idpedido = table.getValueAt(i, 0).toString();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
				if (_idpedido.compareTo("") == 0) {
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

	public int existPDC(String idpedido) {
		int exist = -1;
		if (idpedido!=null){
			int i = 0;
			JTable table = frame.getJTable11();
			if (table != null) {
				while (i < table.getRowCount() & exist < 0) {
					String _idpedido = "";
					try {
						_idpedido = table.getValueAt(i, 0).toString();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (idpedido.compareTo(_idpedido) == 0) {
						exist = i;
					}
					i++;
				}
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

	public void focus(JTable table, int row, int col) {
		table.changeSelection(row, col, false, false);
		table.editCellAt(row, col);
		table.transferFocus();
	}

	public Object[][] process_data(Object[][] results) {
		Object[][] tmp = new Object[results.length][11];
		for (int i = 0; i < results.length; i++) {
			boolean b = false;
			tmp[i][0] = false;

			tmp[i][1] = results[i][0];// id
			tmp[i][2] = results[i][1];// desc
			tmp[i][3] = results[i][2];// costo
			tmp[i][4] = results[i][3];// abc
			tmp[i][5] = new Double((String) results[i][4]);// abc u
			tmp[i][6] = results[i][5];// stock
			//tmp[i][7] = results[i][6];// ventas
			double ventas = 0.0;
			ventas = new Double((String) results[i][6]);
			if (ventas < 0) {
				ventas = 0;
			}
			tmp[i][7] = ventas;
			tmp[i][8] = 0.0;
			tmp[i][9] = 0.0;
			tmp[i][10] = results[i][7];// linea
		}
		return tmp;
	}
	/**
	 * Devuelve chk|idarticulo|descripcion|stock|ventas|pedir|linea|fecha|vendedor|tc|idcomprobante|id
	 * @param results
	 * @return
	 */
	public Object[][] process_data_faltantes(Object[][] results) {
		Object[][] tmp = new Object[results.length][15];
		for (int i = 0; i < results.length; i++) {
			boolean b = false;
			tmp[i][0] = false;
			tmp[i][1] = results[i][0];// idarticulo
			tmp[i][2] = results[i][1];// desc
			tmp[i][3] = results[i][2];// costo
			tmp[i][4] = results[i][3];// abc
			tmp[i][5] = new Double((String)results[i][4]);// abc u
			tmp[i][6] = new Double((String) results[i][5]);//stock
			tmp[i][7] = new Double((String) results[i][6]);//ventas
			
			tmp[i][8] = 0.0; //pedir
			//tmp[i][8] = 0.0;
			tmp[i][9] = results[i][7];// linea
			tmp[i][10] = results[i][8];// fecha
			tmp[i][11] = results[i][9];// vendedor
			tmp[i][12] = results[i][10];// tc
			tmp[i][13] = results[i][11];// idcomprobante
			tmp[i][14] = results[i][12];// id
			//tmp[i][16] = results[i][13];// id
		}
		return tmp;
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

	public void showCost(JCheckBox chk) {
		frame.getJTable().repaint();
	}
	public Color getShowCost(JTable table,int row,boolean isSelected) {
		Color color = Color.white;
		boolean b = table.getValueAt(row, 13).toString().compareTo("")!=0;
		if (b){
			color=Color.RED;
			if (isSelected){
				color=Color.orange;
			}
		}else{
			if (isSelected){
				color=Color.lightGray;
			}
		}
		return color;
	}
	
	
	public boolean getShowCost() {
		boolean b = frame.get_chk_costo().isSelected();

		return b;
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
				if (frame.get_chk_costo().isSelected()) {
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
	public void editarArticulo(JTable table,int row,int col) {
		String idarticulo="";
		try {
			idarticulo = table.getValueAt(row, col).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public boolean _calculate() {
		return this._calculate(-1, false);

	}
	
	public void evaluate_chk_importar(JTable table, int row, boolean selected) {

		if (selected) {
			try {
				table.getCellEditor().stopCellEditing();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Double cantidad = 0.0;
			
			try {
				String _cant="";
				if (table.getName()==_Interface._table_faltantes){
					_cant=table.getValueAt(row, 5).toString();
					 
				}else{
					_cant=table.getValueAt(row, 7).toString();
						
				}
				cantidad = new Double(_cant.replaceAll(",", ""));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (cantidad <= 0) {
				table.setValueAt(1.0, row, 7);
			}
			table.changeSelection(row, 7, false, false);
			table.editCellAt(row, 7);
			table.transferFocus();
		}

	}

	public boolean _calculate(int row, boolean selected) {
		boolean ok = true;
		double items = 0.0;
		double subtotal = 0.0;
		double suma = 0.0;

		double unidades = 0.0;
		double rows = 0;
		double siva = 0.0;
		double iva = 0.0;
		String idcliente = frame.get_txt_idcliente().getText();
		boolean ri = data.esResponsableInscripto(idcliente);
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

			siva = suma / 1.21;
			iva = suma - siva;
			/*
			 * if (ri){ siva=suma/1.21; iva=suma-siva; }else{ siva=suma; iva=0;
			 * }
			 */
		}
		Convertidor c = new Convertidor();
		String _unidades = c.getMoney(unidades, 2);
		frame.get_txt_items().setText(_unidades);
		String _subtotal = c.getMoney(siva, 2);
		frame.get_txt_subtotal().setText(_subtotal);
		String _iva = c.getMoney(iva, 2);
		frame.get_txt_iva().setText(_iva);
		String total = c.getMoney(suma, 2);
		frame.get_txt_total().setText(total);

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

	public void seleccionar(boolean b) {
		if (frame.getJTable() != null) {
			for (int i = 0; i < frame.getJTable().getRowCount(); i++) {
				frame.getJTable().setValueAt(b, i, 0);
			}
		}
		this._calculate();
	}

	public void seleccionarCriticos(boolean b) {
		if (frame.getJTable_critico() != null) {
			for (int i = 0; i < frame.getJTable_critico().getRowCount(); i++) {
				frame.getJTable_critico().setValueAt(b, i, 0);
			}
		}
		
	}
	
	public void seleccionarFaltantes(boolean b) {
		if (frame.getJTable_faltantes() != null) {
			for (int i = 0; i < frame.getJTable_faltantes().getRowCount(); i++) {
				frame.getJTable_faltantes().setValueAt(b, i, 0);
			}
		}
		
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

	public void _eval_item_pedido(JTextField tx, int row) {
		cambios = true;

		String valor = tx.getText();
		boolean ok = true;
		if (valor.compareTo("") != 0) {
			ok = data.existePDC(valor);
			if (!ok) {
				error("El pedido de cliente " + valor + " es inexistente");
			}
		}
		if (ok) {
			DefaultTableModel model = (DefaultTableModel) this.frame
					.getJTable().getModel();
			if (row == model.getRowCount() - 1) {
				model.setRowCount(model.getRowCount() + 1);
				model.setValueAt(true, model.getRowCount() - 1, 0);
			}

			frame.getJTable().changeSelection(row + 1, 1, false, false);
			frame.getJTable().editCellAt(row + 1, 1);
			frame.getJTable().transferFocus();
		} else {
			tx.requestFocusInWindow();
			tx.setSelectionStart(0);
			tx.setSelectionEnd(tx.getText().length());
		}

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

				frame.getJTable().changeSelection(row, 8, false, false);
				frame.getJTable().editCellAt(row, 8);
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

	public double _calculate_descuento(JTextField tx) {
		String _descuento = tx.getText();
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

	public void _eval_item_precio(JTextField tx, int row) {
		cambios = true;
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
				Convertidor Cv = new Convertidor();
				tx.setText(Cv.getMoney(prc, 2));
				frame.getJTable().changeSelection(row, 6, false, false);
				frame.getJTable().editCellAt(row, 6);
				frame.getJTable().transferFocus();
				this.eval_variables_from_precio(row, precio);

			} else {
				this.eval_variables_from_precio(row, "0.0");
				error("el precio debe ser mayor a cero");

			}
		} else {
			this.eval_variables_from_precio(row, "0.0");
			error("error en precio");

		}

	}

	public void cargarCliente() {
		String idcliente = frame.get_txt_idcliente().getText();
		this.evaluarCliente(frame.get_txt_idcliente());
	}

	public void limpiar_proveedor() {
		frame.get_txt_idcliente().setText("");
		frame.get_txt_cliente_descripcion().setText("");

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

	public void verMaestro() {
		String idcliente = frame.get_txt_idcliente().getText();
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

	public void cargar_pedido(String idpedido) {
		this.clean();
		frame.get_txt_idpedido().setText(idpedido);
		frame.get_txt_idpedido().setEditable(false);

		Object[][] results = data.getPedido(idpedido);
		if (results != null) {
			if (results.length > 0) {
				nuevo = false;
				boolean _eliminado = data.eliminado(idpedido);
				frame.getLockableUI().setLocked(false);
				frame.get_btn_guardar().setEnabled(!_eliminado);
				frame.get_btn_preparar().setEnabled(!_eliminado);
				frame.get_btn_presupuesto().setEnabled(!_eliminado);
				frame.get_btn_remito().setEnabled(!_eliminado);
				frame.get_btn_envio().setEnabled(!_eliminado);
				frame.get_btn_eliminar().setEnabled(!_eliminado);
				frame.get_btn_eliminar().setEnabled(true);
				frame.get_btn_remito().setEnabled(true);
				frame.get_btn_identificador().setEnabled(true);

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
				frame.get_txt_pedido_descripcion().setText(descripcion);
				frame.get_txt_fecha().setText(agenda);
				frame.get_txt_fecha_creacion().setText(fecha_creacion);
				frame.get_txt_modificado().setText(fecha_modificacion);
				frame.get_txt_idcliente().setText(cliente);
				frame.get_txt_cliente_descripcion()
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
				this.cargar_items(idpedido);
				this._calculate();
				this.cargar_pdcs(idpedido);
				frame.get_txt_idvendedor().requestFocusInWindow();
				cambios = false;
			}
		}
	}

	public void cargar_pdcs(String idpedido) {
		Object[][] results = data.getPedidosPDC(idpedido);
		boolean empty = true;
		if (results != null) {
			if (results.length > 0) {
				this.create_table_pdc(results);
				empty = false;
			}
		}
		if (empty) {
			this.crear_tabla_pdc();
		}
	}

	public void importar() {
		JTable table = frame.getJTable12();
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

					if (frame.getJTable() == null) {
						this.crear_tabla_items();
					}
					this.agregar2(new Object[] { idarticulo, descripcion,
							cantidad });
				}

			}
		}
	}

	public void importar_critico() {
		JTable table = frame.getJTable_critico();
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
					Double cantidad = (Double) table.getValueAt(i, 7);
					if (frame.getJTable() == null) {
						this.crear_tabla_items();
					}
					this.agregar2(new Object[] { idarticulo, descripcion,
							cantidad.toString() });
				}

			}
		}
	}

	public void importar_faltante() {
		JTable table = frame.getJTable_faltantes();

		int selections = 0;
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
					selections++;
				}
			}
		}
		if (selections > 0) {
			int nx = this
					.preguntar(
							"Confirmar",
							"Quita automaticamente de la lista de faltantes los articulos seleccionados? ",
							new String[] { "Si", "Checkear Individualmente",
									"No Quitar Ninguno" },
							"Checkear Individualmente");

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
						//this.create_tabla_faltantes(null);
						String cantidad = table.getValueAt(i, 8).toString();
						double cant=1.0;
						try {
							cant=new Double(cantidad.replaceAll(",", ""));
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						if (cant<=0){
							cant=1;
						}
						String idpedido_pdc = "";
						try {
							idpedido_pdc = (String) table.getValueAt(i, 13);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						String id = (String) table.getValueAt(i, 14);
						this.agregar_pedido(idpedido_pdc);
						if (id.compareTo("") != 0) {
							if (nx == 0) {
								this.eliminar_faltante(id);
							} else {
								if (nx == 1) {
									int n = this
											.preguntar(
													"Confirmar",
													"Quita "
															+ idarticulo
															+ " de la lista de faltantes? ",
													new String[] { "Si",
															"No. Conservar, lo hago manualmente" },
													"Si");
									if (n == 0) {
										this.eliminar_faltante(id);
									}
								}

							}

						}

						this.agregar2(new Object[] { idarticulo, descripcion,
								""+cant }, idpedido_pdc);
					}

				}
			}
		}

	}

	public void eliminar_faltantes_seleccionados() {
		JTable table = frame.getJTable_faltantes();
		int selections = 0;
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
					selections++;
				}
			}
		}
		if (selections > 0) {
			if (confirmar(
					"Confirme para eliminar los faltantes seleccionados: ", 2)) {
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
							String id = "";
							try {
								id = (String) table.getValueAt(i, 14);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							this.eliminar_faltante(id);
						}

					}
					aviso("Faltantes Eliminados");
					this.cargar_faltantes();
				}

			} else {
				error("OPERACION CANCELADA");
			}
		} else {
			error("Seleccione los faltantes que quiere eliminar de la lista");
		}

	}

	public void cargar_pdc_articulos(String idpedido) {
		Object[][] results = data.getPDCItems(idpedido);
		boolean exist = false;
		if (results != null) {
			if (results.length > 0) {
				exist = true;
				Object[][] tmp = new Object[results.length][results[0].length + 1];
				for (int i = 0; i < results.length; i++) {
					tmp[i][0] = false;
					for (int j = 0; j < results[0].length; j++) {
						tmp[i][j + 1] = results[i][j];
					}
				}
				this.create_table_pdc_articulos(tmp);
			}
		}
		if (!exist) {
			this.crear_tabla_pdc_articulos();
		}
	}

	private String getInstruccionItemDeleteLast() {
		String idpedido = frame.get_txt_idpedido().getText();
		int last = frame.getJTable().getRowCount() - 1;
		boolean empty = this.eval_row_empty(last);
		while (empty & last >= 0) {
			last--;
			empty = this.eval_row_empty(last);
		}
		String instruccion = this.data.getDeleteItemQuery(idpedido, last);
		return instruccion;
	}

	private String getInstruccionItem(int i) {
		String q = "";
		String idpedido = frame.get_txt_idpedido().getText();
		String item = "" + i;
		String idarticulo = (String) frame.getJTable().getValueAt(i, 1);
		String descripcion = (String) frame.getJTable().getValueAt(i, 2);
		String cantidad = (String) frame.getJTable().getValueAt(i, 3);

		String costo = "0.0";
		String cotiza = "0.0";
		String total = "0.0";
		String idpedido_pdc = "";
		try {
			costo = (String) frame.getJTable().getValueAt(i, 4);
			cotiza = (String) frame.getJTable().getValueAt(i, 5);
			total = (String) frame.getJTable().getValueAt(i, 7);
			idpedido_pdc = (String) frame.getJTable().getValueAt(i, 8);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (total.compareTo("") == 0) {
			total = "0.0";
		}
		String seleccionado = "0";
		descripcion = descripcion.replaceAll("'", "");
		total = total.replaceAll(",", "");
		cotiza = cotiza.replaceAll(",", "");
		costo = costo.replaceAll(",", "");
		cantidad = cantidad.replaceAll(",", "");
		Boolean selected = (Boolean) frame.getJTable().getValueAt(i, 0);
		if (selected) {
			seleccionado = "1";
		}
		if (!nuevo) {
			if (data.existItemPedido(idpedido, i)) {
				q = data.getUpdateItems(idpedido, item, idarticulo,
						descripcion, cantidad, costo, cotiza, total,
						seleccionado, idpedido_pdc);
			} else {
				q = data.getGuardarItemQuery(idpedido, item, idarticulo,
						descripcion, cantidad, costo, cotiza, total,
						seleccionado, idpedido_pdc);
			}

		} else {
			q = data.getGuardarItemQuery(idpedido, item, idarticulo,
					descripcion, cantidad, costo, cotiza, total, seleccionado,
					idpedido_pdc);
		}

		return q;
	}

	private String getInstruccionRemitoItem(int i) {
		double aliciva = 1.21;
		String q = "";
		String idpedido = frame.get_txt_idpedido().getText();
		String item = "" + i;
		String idcliente = frame.get_txt_idcliente().getText();
		String idarticulo = (String) frame.getJTable().getValueAt(i, 1);
		String descripcion = (String) frame.getJTable().getValueAt(i, 2);
		String cantidad = (String) frame.getJTable().getValueAt(i, 3);
		String costo = (String) frame.getJTable().getValueAt(i, 4);
		String importe = (String) frame.getJTable().getValueAt(i, 5);
		String total = (String) frame.getJTable().getValueAt(i, 7);
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

	private String getInstruccionEncabezado() {
		String q = "";
		String idpedido = frame.get_txt_idpedido().getText();
		String descripcion = frame.get_txt_pedido_descripcion().getText();
		descripcion = descripcion.replaceAll("'", "");
		String cliente = frame.get_txt_idcliente().getText();
		String cliente_descripcion = frame.get_txt_cliente_descripcion()
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

	private List<String> getInstruccionesActualizarPunteros() {
		List<String> instrucciones = new ArrayList<String>();
		String instruccion = data.getUpdateTc(tc);
		instrucciones.add(instruccion);
		return instrucciones;
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

	private List<String> getInstruccionesGuardarItems() {
		List<String> instrucciones = new ArrayList<String>();
		instrucciones.add(this.getInstruccionItemDeleteLast());

		for (int i = 0; i < frame.getJTable().getRowCount(); i++) {

			if (!this.eval_row_empty(i)) {

				if (this.eval_row(i)) {
					String instruccion = this.getInstruccionItem(i);
					instrucciones.add(instruccion);
				}

			}

		}

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

	public void fillCreador() {
		String idcreador = frame.get_txt_idvendedor().getText();
		frame.get_txt_idcreador().setText(idcreador);
		this.fillCreador(idcreador);
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

	public boolean _generar_remito() {

		List<String> instrucciones = this
				.getInstruccionesGuardarEncabezadoRemito();
		List<String> instrucciones_puntero = this
				.getInstruccionesActualizarPunterosRemito();
		List<String> instrucciones_items = this
				.getInstruccionesGuardarRemitoItems();
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
		boolean error = data.executeBatch();
		if (!error) {
			data.commitTransaction();
		} else {
			data.rollbackTransaction();
		}
		return error;

	}

	public boolean _guardar() {
		boolean error = true;
		boolean ok = true;
		String idcliente = frame.get_txt_idcliente().getText();
		String idpedido = frame.get_txt_idpedido().getText();
		if (idcliente.compareTo("") != 0) {
			if (proveedor.existe(idcliente)) {
				ok = true;
			} else {
				ok = false;
				error("Cuenta de Proveedor Incorrecta. Verifique");
				frame.get_txt_idcliente().requestFocusInWindow();
			}
		} else {
			ok = false;
			error("Cuenta de Proveedor Incorrecta. Verifique");
			frame.get_txt_idcliente().requestFocusInWindow();
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
			ok = this.check_articulos();
			if (!ok) {
				if (this.nuevo) {
					if (frame.getJTable() != null) {
						ok = frame.getJTable().getRowCount() <= 1;
					} else {
						ok = true;
					}
				} else {
					error("Error en Articulos. Verifique");
				}

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
			String descripcion = frame.get_txt_pedido_descripcion().getText();
			if (descripcion.compareTo("") != 0) {
				ok = true;
			} else {
				error("Ingrese una descripcion para este pedido");
				frame.get_txt_pedido_descripcion().requestFocusInWindow();
				ok = false;
			}

		}
		if (ok) {
			String descripcion = frame.get_txt_cliente_descripcion().getText();
			if (descripcion.compareTo("") != 0) {
				ok = true;
			} else {
				error("Ingrese el nombre de cliente para este pedido");
				frame.get_txt_pedido_descripcion().requestFocusInWindow();
				ok = false;
			}
		}
		if (ok) {
			error = this._guardar_post();
		}
		
		return error;
	}

	public boolean _guardar_post() {
		if (nuevo) {
			String numero = frame.get_txt_idpedido().getText();
			int times = 0;
			boolean existe = true;
			while (existe) {

				this.obtener_proximo_cpte();
				numero = frame.get_txt_idpedido().getText();
				existe = data.existe(numero);
				if (existe & times > 5) {
					aviso("El sistema ajusto el numero de pedido para poder grabar sin riesgos");
					data.updateTC(tc);
					times = 0;
				}
				times++;
			}

			boolean seguimiento = frame.get_chk_seguimiento().isSelected();
			String info = frame.get_txt_informacion().getText();
			if (!seguimiento & info.compareTo("") != 0) {
				seguimiento = preguntar("Confirmar",
						"Se detecto informacion adicional. Quiere tener un seguimiento de este Pedido?");
				if (seguimiento) {
					frame.get_chk_seguimiento().setSelected(true);
				}
			}
		}
		List<String> instrucciones = this.getInstruccionesGuardarEncabezado();
		List<String> instrucciones_items = this.getInstruccionesGuardarItems();
		List<String> instrucciones_pdc = this.getInstruccionesGuardarPDCs();
		data.beginTransaction();
		data.clearBatch();
		for (int i = 0; i < instrucciones.size(); i++) {
			System.out.println(instrucciones.get(i));
			data.addBatch(instrucciones.get(i));
		}

		for (int i = 0; i < instrucciones_items.size(); i++) {
			data.addBatch(instrucciones_items.get(i));
			System.out.println(instrucciones_items.get(i));
		}
		for (int i = 0; i < instrucciones_pdc.size(); i++) {
			System.out.println(instrucciones_pdc.get(i));
			data.addBatch(instrucciones_pdc.get(i));
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

	public void preparar() {
		
		if (this.check_seleccion_del_pedido()) {
			boolean error = this._guardar();
				if (!error) {
					this._preparar();
			}
			
		}
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

	private aplicacion.herramientas.java.ireport.constructor._Constructor reporte = null;

	public void _preparar() {
		if (reporte != null) {
			reporte.dispose();
		}
		String idpedido = frame.get_txt_idpedido().getText();

		reporte = new aplicacion.herramientas.java.ireport.constructor._Constructor();
		HashMap param = new HashMap();
		param.put("idpedido", idpedido);
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
						"pep_preparar.jasper");
		reporte.build(this.getConstructor());
		reporte.init();
	}

	public void reporte() {

		boolean error = this._guardar();
		if (!error) {
			String idtransporte = frame.get_txt_idtransporte().getText();
			if (this.Transporte.existe(idtransporte)) {
				if (reporte != null) {
					reporte.dispose();
				}
				String idpedido = frame.get_txt_idpedido().getText();
				String empresa = data.getNombreEmpresa();
				String telefono = data.getTelefonoEmpresa();
				String email = data.getEmail();
				String info = frame.get_txt_observacion().getText();
				reporte = new aplicacion.herramientas.java.ireport.constructor._Constructor();
				HashMap param = new HashMap();
				param.put("idpedido", idpedido);
				param.put("empresa", empresa);
				param.put("telefono", telefono);
				param.put("email", email);
				param.put("observaciones", info);
				reporte.setParameter(_parametros.LookAndFeel, this
						.getConstructor().getLookAndFeelTheme());
				reporte.setParameter(_parametros.connector, this
						.getConstructor().getConnectionHandler());
				reporte
						.setParameter(
								aplicacion.herramientas.java.ireport.interfaces._parametros.parametros,
								param);
				reporte
						.setParameter(
								aplicacion.herramientas.java.ireport.interfaces._parametros.reporte,
								"pep_pedido.jasper");

				reporte.build(this.getConstructor());
				reporte.init();
			} else {
				error("Por Favor Defina el Transporte para el pedido");
			}

		}

	}

	private aplicacion.ventas.presupuesto.constructor._Constructor presupuesto = null;

	public void _presupuesto() {
		String idcliente = frame.get_txt_idcliente().getText();
		String idpedido = frame.get_txt_idpedido().getText();
		String cliente_descripcion = frame.get_txt_cliente_descripcion()
				.getText();
		String subtotal = frame.get_txt_subtotal().getText();
		String iva = frame.get_txt_iva().getText();
		String total = frame.get_txt_total().getText();

		boolean ok = this.check_seleccion_del_pedido();
		if (ok) {
			if (presupuesto != null) {
				presupuesto.dispose();
			}

			boolean ri = data.esResponsableInscripto(idcliente);

			presupuesto = new aplicacion.ventas.presupuesto.constructor._Constructor();
			presupuesto.setParameter(_parametros.LookAndFeel, this
					.getConstructor().getLookAndFeelTheme());
			presupuesto.setParameter(_parametros.connector, this
					.getConstructor().getConnectionHandler());
			presupuesto
					.setParameter(
							aplicacion.ventas.presupuesto.interfaces._Parametros.idcliente,
							idcliente);
			presupuesto
					.setParameter(
							aplicacion.ventas.presupuesto.interfaces._Parametros.descripcion_cliente,
							cliente_descripcion);
			presupuesto
					.setParameter(
							aplicacion.ventas.presupuesto.interfaces._Parametros.idpedido,
							idpedido);
			presupuesto
					.setParameter(
							aplicacion.ventas.presupuesto.interfaces._Parametros.subtotal,
							subtotal);
			presupuesto.setParameter(
					aplicacion.ventas.presupuesto.interfaces._Parametros.iva,
					iva);
			presupuesto.setParameter(
					aplicacion.ventas.presupuesto.interfaces._Parametros.total,
					total);
			presupuesto
					.setParameter(
							aplicacion.ventas.presupuesto.interfaces._Parametros.discriminar,
							ri);
			presupuesto.build(this.getConstructor());
			presupuesto.init();
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
		String idpedido = frame.get_txt_idpedido().getText();
		String descripcion = frame.get_txt_pedido_descripcion().getText();
		String cliente = frame.get_txt_idcliente().getText();
		String cliente_descripcion = frame.get_txt_cliente_descripcion()
				.getText();
		String idvendedor = frame.get_txt_idvendedor().getText();
		String info = frame.get_txt_informacion().getText();
		String iva = frame.get_txt_iva().getText().replace(",", "");
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

	public void transfer_fecha_focus(JTextField tx) {
		if (tx.getName() == _Interface._txt_fecha) {
			frame.get_txt_idcliente().requestFocusInWindow();
		}
		if (tx.getName() == _Interface._txt_fecha_envio) {
			frame.get_txt_domicilio().requestFocusInWindow();
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

	public void BuscarFecha() {
		Fecha.Buscar(frame.get_txt_fecha());
	}

	public void evaluarFecha(JTextField tx) {
		cambios = true;
		Fecha.evaluate(tx);
	}

	public void evaluate_sobrescribir(JCheckBox chk) {
		cambios = true;
		si_a_todo = false;
		no_a_todo = !chk.isSelected();

	}

	public void editar_pedido() {
		String idpedido = frame.get_txt_pdc_idpedido().getText();
		this.editar_pedido(idpedido);
	}

	public void editar_pedido(int row) {

		String idpedido = frame.getJTable11().getValueAt(row, 0).toString();
		this.editar_pedido(idpedido);
	}

	public void editar_pedido(String idpedido) {
		aplicacion.ventas.pedido.constructor._Constructor pedido = new aplicacion.ventas.pedido.constructor._Constructor();
		pedido.setParameter(_parametros.connector, this.getConstructor()
				.getConnectionHandler());
		pedido.setParameter(_parametros.iduser, this.getConstructor()
				.getIduser());
		pedido.setParameter(_parametros.LookAndFeel, this.getConstructor()
				.getLookAndFeelTheme());
		pedido.setParameter(
				aplicacion.ventas.pedido.interfaces._parametros._idpedido,
				idpedido);
		pedido.build(this.getConstructor());
		pedido.init();
	}

	public void exit_command() {

		boolean error = false;
		String idpedido = frame.get_txt_idpedido().getText();
		if (nuevo) {
			if (cambios) {
				cambios = preguntar("Confirmar",
						"Desde guardar Los Cambios Realizados?");
			}
		} else {
			if (cambios) {
				boolean eliminado = data.eliminado(idpedido);
				if (!eliminado) {
					cambios = preguntar("Confirmar",
							"Desde guardar Los Cambios Realizados?");
				}
			}
		}
		if (cambios) {
			error = this._guardar();
		}
		if (!error) {

			if (vArticulo != null) {
				this.vArticulo.dispose();
				this.vArticulo = null;
			}
			if (this.Vendedor != null) {
				this.Vendedor.dispose();
				this.Vendedor = null;
			}
			if (this.vEquivalencia != null) {
				this.vEquivalencia.dispose();
				this.vEquivalencia = null;
			}
			if (this.bArticulo != null) {
				this.bArticulo.dispose();
				this.bArticulo = null;
			}
			if (this.bPDC != null) {
				this.bPDC.dispose();
				this.bPDC = null;
			}
			if (this.PDC != null) {
				this.PDC.dispose();
				this.PDC = null;
			}
			if (this.PEP != null) {
				this.PEP.dispose();
				this.PEP = null;
			}
			if (this.proveedor != null) {
				this.proveedor.dispose();
				this.proveedor = null;
			}
			if (this.articulo != null) {
				this.articulo.dispose();
				this.articulo = null;
			}
			if (this.Provincia != null) {
				this.Provincia.dispose();
				this.Provincia = null;
			}
			if (this.Catalogo != null) {
				this.Catalogo.dispose();
				this.Catalogo = null;
			}
			if (this.Transporte != null) {
				this.Transporte.dispose();
				this.Transporte = null;
			}
			super.exit_command();
		}
	}

	private aplicacion.herramientas.java.evaluadores.Transporte Transporte = null;

	public void initialize_Transporte() {
		Transporte = new aplicacion.herramientas.java.evaluadores.Transporte() {
			public void cargar(String codigo) {
				Object[][] results = this.getInfo(codigo);
				String descripcion = (String) results[0][1];
				frame.get_txt_transporte_descripcion().setText(descripcion);
				String fecha_envio = frame.get_txt_fecha_envio().getText();

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
		cambios = true;
		Transporte.evaluate(tx);
	}

	public void loadFromXML() {
		frame.getLockableUI().setLocked(false);
		super.loadFromXML();
	}

	protected JTable create_table_xml(String nombre) {
		JTable table = null;
		if (nombre.compareTo(_Interface._table_items) == 0) {
			this.crear_tabla_items();
			table = frame.getJTable();
		}
		/*
		 * if (nombre.compareTo(_Interface._table_remitos)==0){
		 * this.crear_tabla_remitos(); table= frame.getJTable1(); }
		 */
		return table;
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

	public String getDaysRoll(String fecha_actual, int days) {
		Date today = new Convertidor().getDate(fecha_actual);
		java.util.GregorianCalendar _today = new java.util.GregorianCalendar();
		_today.setTime(today);
		// _today.roll(Calendar.DAY_OF_YEAR, days);
		_today.add(Calendar.DAY_OF_YEAR, days);
		Date before = _today.getTime();
		String s2 = new Convertidor().getDateWithFormat("dd-MM-yyyy", before);
		return s2;
	}

	private aplicacion.herramientas.java.visualizadores.Linea vLinea = null;

	public void buscarLinea(JTextField tx) {
		if (vLinea != null) {
			vLinea.close();
		}
		vLinea = new aplicacion.herramientas.java.visualizadores.Linea(this
				.getConstructor());
		// vLinea.setIdproveedor(frame.get_txt_idproveedor().getText());
		int n = vLinea.buscarLinea(tx);
		if (n == 0) {
			aviso("no hay Lineas con ese codigo");
		}
	}

	public void evaluar_linea_critico(JTextField tx) {
		String linea = tx.getText();
		if (linea.compareTo("") != 0) {
			if (data.check_linea(linea)) {
				this.cargar_critico(linea);
			} else {
				this.buscarLinea(tx);
			}
		} else {
			this.buscarLinea(tx);
		}
	}

	public void evaluar_linea_faltantes(JTextField tx) {
		String linea = tx.getText();
		if (linea.compareTo("") != 0) {
			if (data.check_linea(linea)) {
				this.cargar_faltantes(linea);
			} else {
				this.buscarLinea(tx);
			}
		} else {
			this.buscarLinea(tx);
		}
	}

	public void cargar_critico() {
		String linea = frame.get_txt_linea().getText();
		
			this.cargar_critico(linea);	
		
		
	}

	public void cargar_faltantes() {
		String linea = frame.get_txt_linea_faltantes().getText();
		this.cargar_faltantes(linea);
	}

	public void cargar_critico(String linea) {
		String idproveedor = frame.get_txt_idcliente().getText();
		String hasta = new Convertidor().getDateWithFormat("dd-MM-yyyy");
		String desde = this.getDaysRoll(hasta, -7);
		hasta = frame.get_txt_fecha_hasta().getText();
		desde = frame.get_txt_fecha_desde().getText();
		int quiebres=-1;
		try {
			quiebres=new Integer(frame.get_lst_stock().getSelectedItem().toString());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String idcategoria="";
		try {
			idcategoria = frame.get_lst_categoria().getSelectedItem().toString();
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
		boolean full=frame.get_chk_full_critico().isSelected();
		String q = data.getLineaItems(idproveedor, desde, hasta, linea,quiebres,idcategoria,tipo,full);
		System.out.println(q);
		Object[][] results = data.getResults(q);
		boolean ok = false;
		if (results != null) {
			if (results.length > 0) {
				results = this.process_data(results);
				ok = true;
				this.create_tabla_critico(results);

			}
		}
		if (!ok) {
			frame.setJTable_faltantes(null);
			error("No existen criticos segun los parametros de busqueda");
		}
	}

	public void cargar_faltantes(String linea) {

		String idproveedor = frame.get_txt_idcliente().getText();
		String hasta_ventas = new Convertidor().getDateWithFormat("dd-MM-yyyy");
		String desde_ventas = this.getDaysRoll(hasta_ventas, -7);
		hasta_ventas = this.getDaysRoll(hasta_ventas, +1);
		String hasta = frame.get_txt_fecha_hasta_faltante().getText();
		hasta = this.getDaysRoll(hasta, +1);
		String desde = frame.get_txt_fecha_desde_faltante().getText();
		if (!frame.get_chk_mostrar_faltantes_proveedor().isSelected()) {
			idproveedor = "%";
		}
		boolean mostrar_eliminados = frame.get_chk_mostrar_eliminados()
				.isSelected();
		String categoria="";
		try {
			categoria=frame.get_lst_categoria_faltantes().getSelectedItem().toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		int tipo=0;
		try {
			tipo=frame.get_lst_modo_faltantes().getSelectedIndex();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int pdc=frame.get_lst_faltantes().getSelectedIndex();
		String q = data.getFaltantes(idproveedor, desde, hasta, desde_ventas,
				hasta_ventas, mostrar_eliminados, linea,categoria,pdc,tipo);
		System.out.println(q);
		Object[][] results = data.getResults(q);
		boolean ok = false;
		if (results != null) {
			if (results.length > 0) {
				results = this.process_data_faltantes(results);
				ok = true;
				this.create_tabla_faltantes(results);
			}
		}
		if (!ok) {
			frame.setJTable_faltantes(null);
			error("No existen faltantes acusados segun los parametros de busqueda");
		}
	}

	public void automatico() {
		System.out.println("pedido automatico>");
		frame.get_btn_guardar().setEnabled(true);
		frame.get_btn_preparar().setEnabled(true);
		frame.get_btn_presupuesto().setEnabled(true);
		frame.get_btn_remito().setEnabled(true);
		frame.get_btn_envio().setEnabled(true);
		frame.getLockableUI().setLocked(false);
		nuevo = true;
		frame.get_txt_idpedido().setEditable(false);
		frame.get_txt_idcliente().setEnabled(true);
		frame.get_txt_idvendedor().setEnabled(true);
		frame.get_btn_buscar_cliente().setEnabled(true);
		frame.get_btn_cargar_cliente().setEnabled(true);
		frame.get_txt_pedido_descripcion().setText("PEDIDO ESPECIAL");
		frame.get_txt_pedido_descripcion().setSelectionStart(0);
		frame.get_txt_pedido_descripcion().setSelectionEnd(5);
		frame.get_txt_pedido_descripcion().requestFocusInWindow();

		this.limpiar_proveedor();
		this.fillCreado();
		this.fillModificado();
		this.crear_tabla_items();
		this.cargarVendedor();
		frame.get_txt_pedido_descripcion().requestFocusInWindow();
		frame.get_txt_pedido_descripcion().setSelectionStart(0);
		frame.get_txt_pedido_descripcion().setSelectionEnd(5);

	}

	private void crear_empty_etiquetas() {
		Object[][] results = new Object[][] { { false, "", "", "" } };
		this.create_table_etiquetas(results);
	}

	private void create_tabla_critico(Object[][] results) {
		// "","idArticulo","Descripcion","Costo","Minimo","Stock","Ventas","Pedir"
		// ,"Total"
		CustomTable Table = new CustomTable();
		// ColorTableCellRenderer colour=new ColorTableCellRenderer();
		Column col = new Column();
		col = new Column();
		col.setName("");
		col.setWidth(30);
		col.setEditable(true);
		CheckBoxCellEditor chkce = new CheckBoxCellEditor();
		chkce.setItemListener(this._constructor.getItemListener());
		chkce.setTipo(Boolean.class);
		chkce.setName(_Interface._chk_seleccionar_critico_item);
		col.setCellEditor(chkce.getCellCheck());
		col.setClass(Boolean.class);
		Table.addColumn(col);

		col = new Column();
		col.setName("idArticulo");
		col.setWidth(120);
		col.setEditable(false);
		// col.setCellRenderer(new TableItemColorCellRenderer());
		CellEditor pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.blue);
		pce.setName(_Interface._table_idarticulo);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());

		Table.addColumn(col);

		col = new Column();
		col.setName("Descripcion");
		col.setWidth(240);
		col.setEditable(false);
		// col.setCellRenderer(new TableItemColorCellRenderer());
		Table.addColumn(col);

		col = new Column();
		col.setName("Costo");
		col.setWidth(60);
		col.setAligment(JTextField.RIGHT);
		col.setEditable(false);
		// col.setCellRenderer(new TableItemColorCellRenderer());
		col.setClass(Double.class);
		Table.addColumn(col);

		col = new Column();
		col.setName("ABC");
		col.setWidth(50);
		col.setAligment(JTextField.RIGHT);
		col.setEditable(false);
		// col.setCellRenderer(new TableItemColorCellRenderer());
		col.setClass(String.class);
		Table.addColumn(col);
		
		col = new Column();
		col.setName("ABC U");
		col.setWidth(60);
		col.setAligment(JTextField.RIGHT);
		col.setEditable(false);
		// col.setCellRenderer(new TableItemColorCellRenderer());
		col.setClass(Double.class);
		Table.addColumn(col);

		col = new Column();
		col.setName("Stock");
		col.setWidth(60);
		col.setAligment(JTextField.RIGHT);
		col.setEditable(false);
		// col.setCellRenderer(new TableItemColorCellRenderer());
		col.setClass(Double.class);
		Table.addColumn(col);

		col = new Column();
		col.setName("Ventas");
		col.setWidth(60);
		col.setAligment(JTextField.RIGHT);
		col.setEditable(false);
		// col.setCellRenderer(new TableItemColorCellRenderer());
		col.setClass(Double.class);
		Table.addColumn(col);

		col = new Column();
		col.setName("Pedir");
		col.setAligment(JTextField.RIGHT);
		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.blue);
		pce.setName(_Interface._table_critico_pedir);
		pce.setTipo(Double.class);
		col.setCellEditor(pce.getCellEditor());
		col.setWidth(60);
		col.setEditable(true);
		// col.setCellRenderer(new TableItemColorCellRenderer());
		col.setClass(Double.class);
		Table.addColumn(col);

		col = new Column();
		col.setName("Total");
		col.setAligment(JTextField.RIGHT);
		col.setWidth(70);
		col.setEditable(false);
		// col.setCellRenderer(new TableItemColorCellRenderer());
		col.setClass(Double.class);
		Table.addColumn(col);

		col = new Column();
		col.setName("Linea");
		col.setWidth(180);
		col.setEditable(false);
		// col.setCellRenderer(new TableItemColorCellRenderer());
		Table.addColumn(col);

		Table.setName(_Interface._table_critico);
		Table.setFont(new Font("Dialog", Font.PLAIN, 9));
		Table.addMouseListener(this.getConstructor().getMouseListener());
		Table.addKeyListener(this.getConstructor().getKeyListener());
		Table.setData(results);
		Table.build();
		Table.fillData();
		JTable table = Table.getTable();
		frame.setJTableCritico(table);
	}

	public void eliminar_faltante(String id) {
		String q = data.getEliminar_faltante_utilizado(id);
		System.out.println("Eliminar faltante>" + q);
		data.clearBatch();
		data.addBatch(q);
		data.executeBatch();
	}
	
	
	
	/**
	 *  Crea Tabla con chk|idarticulo|descripcion|stock|ventas|pedir|linea|fecha|vendedor|tc|idcomprobante|id	
	 * 	@param results
	 */
	private void create_tabla_faltantes(Object[][] results) {
		// "","idArticulo","Descripcion","Costo","Minimo","Stock","Ventas","Pedir"
		// ,"Total"
		CustomTable Table = new CustomTable();
		TableItemColorCellRenderer cellrenderer=new TableItemColorCellRenderer();
		cellrenderer.setLogic(this);
		
		// ColorTableCellRenderer colour=new ColorTableCellRenderer();
		Column col = new Column();
		col = new Column();
		col.setName("");
		col.setWidth(30);
		col.setEditable(true);
		CheckBoxCellEditor chkce = new CheckBoxCellEditor();
		chkce.setItemListener(this._constructor.getItemListener());
		chkce.setTipo(Boolean.class);
		chkce.setName(_Interface._chk_seleccionar_faltante_item);
		col.setCellEditor(chkce.getCellCheck());
		col.setClass(Boolean.class);
		Table.addColumn(col);

		col = new Column();
		col.setName("idArticulo");
		col.setWidth(110);
		col.setEditable(false);
		col.setCellRenderer(cellrenderer);
		CellEditor pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.blue);
		pce.setName(_Interface._table_idarticulo);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());
		Table.addColumn(col);

		col = new Column();
		col.setName("Descripcion");
		col.setWidth(200);
		col.setEditable(false);
		col.setCellRenderer(cellrenderer);
		Table.addColumn(col);

		col = new Column();
		col.setName("Costo");
		col.setWidth(50);
		col.setAligment(JTextField.RIGHT);
		col.setEditable(false);
		col.setCellRenderer(cellrenderer);
		col.setClass(Double.class);
		Table.addColumn(col);

		col = new Column();
		col.setName("ABC");
		col.setWidth(44);
		col.setAligment(JTextField.RIGHT);
		col.setEditable(false);
		col.setCellRenderer(cellrenderer);
		col.setClass(String.class);
		Table.addColumn(col);

		col = new Column();
		col.setName("ABC u");
		col.setWidth(50);
		col.setAligment(JTextField.RIGHT);
		col.setEditable(false);
		col.setCellRenderer(cellrenderer);
		col.setClass(Double.class);
		Table.addColumn(col);

		col = new Column();
		col.setName("Stock");
		col.setWidth(56);
		col.setAligment(JTextField.RIGHT);
		col.setEditable(false);
		col.setCellRenderer(cellrenderer);
		col.setClass(Double.class);
		Table.addColumn(col);

		col = new Column();
		col.setName("Ventas");
		col.setWidth(56);
		col.setAligment(JTextField.RIGHT);
		col.setEditable(false);
		col.setCellRenderer(cellrenderer);
		col.setClass(Double.class);
		Table.addColumn(col);

		col = new Column();
		col.setName("Pedir");
		col.setAligment(JTextField.RIGHT);
		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.blue);
		pce.setName(_Interface._table_faltante_pedir);
		pce.setTipo(Double.class);
		col.setCellEditor(pce.getCellEditor());
		col.setWidth(56);
		col.setEditable(true);
		col.setCellRenderer(cellrenderer);
		col.setClass(Double.class);
		Table.addColumn(col);

		col = new Column();
		col.setName("Linea");
		col.setWidth(100);
		col.setEditable(false);
		col.setCellRenderer(cellrenderer);
		Table.addColumn(col);
		
		col = new Column();
		col.setName("Fecha");
		col.setWidth(100);
		col.setEditable(false);
		col.setCellRenderer(cellrenderer);
		Table.addColumn(col);

		col = new Column();
		col.setName("Vendedor");
		col.setWidth(100);
		col.setEditable(false);
		col.setCellRenderer(cellrenderer);
		Table.addColumn(col);

		col = new Column();
		col.setName("tc");
		col.setWidth(36);
		col.setEditable(false);
		col.setCellRenderer(cellrenderer);
		Table.addColumn(col);

		col = new Column();
		col.setName("idcomprobante");
		col.setWidth(100);
		col.setEditable(false);
		col.setCellRenderer(cellrenderer);
		Table.addColumn(col);


		col = new Column();
		col.setName("id");
		col.setWidth(40);
		col.setEditable(false);
		col.setCellRenderer(cellrenderer);
		Table.addColumn(col);

		Table.setName(_Interface._table_faltantes);
		Table.setFont(new Font("Dialog", Font.PLAIN, 9));
		Table.addMouseListener(this.getConstructor().getMouseListener());
		Table.addKeyListener(this.getConstructor().getKeyListener());
		Table.setData(results);
		
		Table.build();
		Table.fillData();
		JTable table = Table.getTable();
		table.setColumnSelectionAllowed(false);
		frame.setJTable_faltantes(table);
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

	public Object[][] getEtiqueteas() {
		LinkedList etiquetas = new LinkedList();
		for (int i = 0; i < frame.getJTable().getRowCount(); i++) {
			String idarticulo = "";
			try {
				idarticulo = frame.getJTable().getValueAt(i, 1).toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String descripcion = "";
			try {
				descripcion = frame.getJTable().getValueAt(i, 2).toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String cantidad = "";
			try {
				cantidad = frame.getJTable().getValueAt(i, 3).toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String pedido = "";
			try {
				pedido = frame.getJTable().getValueAt(i, 8).toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cantidad = cantidad.replaceAll(",", "");
			Object[][] results = data.getPedidoPDC(pedido);
			if (results != null) {
				if (results.length > 0) {
					String pedido_descripcion = (String) results[0][0];
					String fecha = (String) results[0][1];
					String pedido_cliente = (String) results[0][3];
					String pedido_cliente_descripcion = (String) results[0][4];
					String _descripcion = "ENTREGA " + idarticulo + " "
							+ pedido_cliente + " " + pedido_cliente_descripcion
							+ " " + pedido_descripcion + " " + fecha;
					etiquetas
							.add(new String[] { pedido, _descripcion, cantidad });
				}
			}
			etiquetas.add(new String[] { idarticulo, descripcion, cantidad });
		}

		Object[][] results = null;

		if (etiquetas.size() > 0) {
			results = new Object[etiquetas.size()][3];
			for (int i = 0; i < etiquetas.size(); i++) {
				String[] etq = (String[]) etiquetas.get(i);
				for (int j = 0; j < 3; j++) {
					results[i][j] = (String) etq[j];
				}
			}
		}

		return results;
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
								String cuenta = frame.get_txt_idcliente().getText();
								String cliente_descripcion = frame
										.get_txt_cliente_descripcion().getText();
								String descripcion = frame
										.get_txt_pedido_descripcion().getText();
								String fecha = frame.get_txt_fecha().getText();
								String emailto = frame.get_txt_email().getText();

								String empresa = data.getNombreEmpresa();
								String telefono = data.getTelefonoEmpresa();
								String _email = data.getEmail();
								String transporte = frame
										.get_txt_transporte_descripcion().getText();
								String info = frame.get_txt_observacion().getText();
								String nombre = frame.get_txt_cliente_descripcion()
										.getText();
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
						frame.get_txt_idtransporte().requestFocusInWindow();
					}
				}else{
					error("Debe Configurar El email en el Pedido en Envio/Direccion");
					frame.get_txt_email().requestFocusInWindow();
				}
			}

		}else{
			error("Deben Configurarse Los Parametros de Sistema de E-mail");
		}
	}

	public void Etiquetar() {
		String idpedido = frame.get_txt_idpedido().getText();
		if (data.existe(idpedido)) {
			frame.getJTable().setEnabled(false);
			String cuenta = frame.get_txt_idcliente().getText();
			String cliente_descripcion = frame.get_txt_cliente_descripcion()
					.getText();
			String idcomprobante = frame.get_txt_idpedido().getText();
			String descripcion = frame.get_txt_pedido_descripcion().getText();
			String fecha = frame.get_txt_fecha().getText();
			String _descripcion = cuenta + " " + cliente_descripcion + " "
					+ descripcion + " " + fecha;
			Object[][] results = this.getEtiqueteas();

			if (results != null) {
				if (results.length > 0) {
					_Constructor constructor = (_Constructor) this
							.getConstructor();
					if (this.etiquetas != null) {
						etiquetas.setVisible(false);
						etiquetas.dispose();
					}
					this.etiquetas = new _Etiquetas();
					this.etiquetas.get_chk_seleccionar().setName(
							_Interface._chk_seleccionar_etiquetas);
					this.etiquetas.get_chk_seleccionar().addItemListener(
							constructor.getItemListener());
					this.etiquetas.get_btn_imprimir_etiquetas()
							.setActionCommand(
									_Interface._btn_imprimir_etiquetas);
					this.etiquetas.get_btn_imprimir_etiquetas()
							.addActionListener(constructor.getActionListener());
					this.etiquetas.setVisible(true);
					this.etiquetas.requestFocus();
					this.etiquetas.requestFocusInWindow();
					this.crear_empty_etiquetas();
					int units = 0;
					for (int i = 0; i < results.length; i++) {
						String idarticulo = "";

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
							etiquetas.getJTable()
									.setValueAt(idarticulo, row, 1);
							etiquetas.getJTable().setValueAt(descripcion, row,
									2);
							etiquetas.getJTable().setValueAt(cantidad, row, 3);

							model.setRowCount(model.getRowCount() + 1);
							units += _cantidad;
							etiquetas.get_txt_unidades().setText("" + units);
						}
					}
				}
			}

			frame.getJTable().setEnabled(true);
		} else {
			error("Grabe El pedido para poder imprimir identificadores");
		}

	}

	private JTable crearTablaItems(Object[][] auzx) {
		final CustomTable Table = new CustomTable();
		System.out.println("Creando Tabla Articulos");

		Column col = new Column();
		col.setName("");
		col.setWidth(20);
		col.setClass(Boolean.class);
		col.setEditable(false);
		col.setEditable(true);
		CheckBoxCellEditor chkce = new CheckBoxCellEditor();
		chkce.setItemListener(this.getConstructor().getItemListener());
		chkce.setTipo(Boolean.class);
		// chkce.setName(_Interface._table_chk_articulos);
		col.setCellEditor(chkce.getCellCheck());
		Table.addColumn(col);

		col = new Column();
		col.setName("idarticulo");
		col.setWidth(120);
		col.setClass(String.class);
		col.setEditable(false);
		TableColorCellRenderer cellrender = new TableColorCellRenderer();
		cellrender.setLogic(this);
		col.setCellRenderer(cellrender);
		Table.addColumn(col);

		col = new Column();
		col.setName("descripcion");
		col.setWidth(280);
		col.setClass(String.class);
		col.setEditable(false);
		cellrender = new TableColorCellRenderer();
		cellrender.setLogic(this);
		col.setCellRenderer(cellrender);
		Table.addColumn(col);

		col = new Column();
		col.setName("linea");
		col.setWidth(180);
		col.setClass(String.class);
		col.setEditable(false);
		cellrender = new TableColorCellRenderer();
		cellrender.setLogic(this);
		col.setCellRenderer(cellrender);
		Table.addColumn(col);

		col = new Column();
		col.setName("publico");
		col.setWidth(80);
		col.setClass(String.class);
		col.setEditable(false);
		cellrender = new TableColorCellRenderer();
		cellrender.setLogic(this);
		col.setCellRenderer(cellrender);
		Table.addColumn(col);

		col = new Column();
		col.setName("stock");
		col.setWidth(70);
		col.setClass(String.class);
		col.setEditable(false);
		cellrender = new TableColorCellRenderer();
		cellrender.setLogic(this);
		col.setCellRenderer(cellrender);
		Table.addColumn(col);
		Table.setData(auzx);
		Table.setFont(new Font("Dialog", Font.BOLD, 10));
		// Table.setName(_Interface._table_articulo);
		Table.build();
		Table.fillData();

		return Table.getTable();

	}

	private boolean cargado(String idarticulo, JTable table) {
		boolean cargado = false;
		int i = 0;
		while (i < table.getRowCount() & !cargado) {
			String _idarticulo = "";
			try {
				_idarticulo = table.getValueAt(i, 1).toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
			cargado = (_idarticulo.compareTo(idarticulo) == 0);
			i++;
		}
		return cargado;
	}

	public JTable getDetalle(String _idarticulo) {
		JTable table = null;
		Object[][] _results = data.getData(_idarticulo);
		if (_results != null) {
			if (_results.length > 0) {
				String _articulo = (String) _results[0][0];
				String _descripcion = (String) _results[0][1];
				String _linea = (String) _results[0][2];
				String _stock = (String) _results[0][3];
				String _suspendidov = (String) _results[0][4];
				String _actualizacion = (String) _results[0][5];
				String _publico = (String) _results[0][8];
				Object[][] tmp = new Object[][] { { false, _idarticulo,
						_descripcion, _linea, _publico, _stock } };
				table = crearTablaItems(tmp);
			}
		}
		return table;
	}

	public void cargar_equivalencias(String _idarticulo) {

		JTable table = this.getDetalle(_idarticulo);
		frame.setJTable_equivalencias(table);
		this.cargar_equivalencias(_idarticulo, frame.getJTable_equivalencias());
	}

	public void cargar_equivalencias_critico(String _idarticulo) {
		JTable table = this.getDetalle(_idarticulo);
		frame.setJTable_equivalencias_critico(table);
		this.cargar_equivalencias(_idarticulo, frame
				.getJTable_equivalencias_critico());
	}

	public void cargar_equivalencias_faltantes(String _idarticulo) {
		JTable table = this.getDetalle(_idarticulo);
		frame.setJTable_equivalencias_faltantes(table);
		this.cargar_equivalencias(_idarticulo, frame
				.getJTable_equivalencias_faltantes());
	}

	public Color getColor(int row, int col, JTable table) {
		Color color = Color.white;
		double stock = 0.0;
		String _stock = "0.0";
		if (table != null) {
			try {
				_stock = table.getValueAt(row, 5).toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (_stock != null) {
				if (_stock.compareTo("") != 0) {
					stock = new Double(_stock.replaceAll(",", ""));
				}
			}
		}
		// System.out.println("Stock>"+_stock);
		if (stock > 0) {
			//color = Color.GREEN;
		}
		return color;
	}

	public void cargar_equivalencias(String _idarticulo, JTable table) {
		Object[][] _results = data.getData(_idarticulo);
		if (_results != null) {
			if (_results.length > 0) {
				String _articulo = (String) _results[0][0];
				String _descripcion = (String) _results[0][1];
				String _linea = (String) _results[0][2];
				String _stock = (String) _results[0][3];
				String _suspendidov = (String) _results[0][4];
				String _actualizacion = (String) _results[0][5];
				String _publico = (String) _results[0][8];

				Object[][] results = data.getEquivalencias(_idarticulo);
				for (int i = 0; i < results.length; i++) {
					String idarticulo = (String) results[i][0];
					if (!this.cargado(idarticulo, table)) {
						boolean empty = true;
						DefaultTableModel model = (DefaultTableModel) table
								.getModel();
						int row = model.getRowCount() - 1;
						try {
							empty = table.getValueAt(row, 1).toString()
									.compareTo("") == 0;
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (!empty) {
							model.setRowCount(model.getRowCount() + 1);
							row = model.getRowCount() - 1;
						}
						boolean selected = false;// this.isSelected((String)results[i][0]);
						table.setValueAt(selected, row, 0);
						for (int j = 0; j < results[0].length; j++) {
							table.setValueAt(results[i][j].toString(), row,
									j + 1);
						}

					}
				}

			}

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

		this.createTimer();
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

	public void createTimer() {
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

	public void updateBar() {
		email.getJProgressBar().setMaximum(lenght);
		email.getJProgressBar().setValue(current);
		email.getJProgressBar().setString(
				estado + " " + current + "/" + lenght + " " + crono.elapsed());
		email.getJProgressBar().setStringPainted(true);

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

	public void endbar() {
		estado = "";
		email.getJProgressBar().setString("");
		email.getJProgressBar().setIndeterminate(false);
		email.getJProgressBar().setValue(0);
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
						param.put("observaciones", info);
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
							logic.excelReport("pep_pedido.jasper", param, output);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							ok=false;
						}
						return ok;
	}
	
}
