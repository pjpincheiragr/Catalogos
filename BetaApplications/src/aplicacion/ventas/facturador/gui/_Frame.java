package aplicacion.ventas.facturador.gui;

import java.awt.BorderLayout;
import java.awt.Point;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JToolBar;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import java.awt.GridBagLayout;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JToolBar jToolBar = null;
	private JButton _btn_cancelar = null;
	private JButton _btn_grabar = null;
	private JButton _btn_salir = null;
	private JLabel jLabel = null;
	private JComboBox _lst_tipo_comprobante = null;
	private JLabel jLabel1 = null;
	private JTextField _txt_idcliente = null;
	private JTextField _txt_descripcion_cliente = null;
	private JTextField _txt_sucursal = null;
	private JTextField _txt_numero = null;
	private JTextField _txt_remito = null;
	private JTextField _txt_idpedido = null;
	private JTextField _txt_articulo_actualizacion = null;
	private JTextField _txt_articulo = null;
	private JTextField _txt_articulo_pedido = null;
	private JTextField _txt_articulo_linea = null;
	private JTextField _txt_articulo_bloqueado = null;
	private JTextField _txt_articulo_descripcion = null;
	private JTextField _txt_articulo_stock = null;
	private JLabel jLabel2 = null;
	private JComboBox _lst_letra = null;
	private JTextField _txt_fecha = null;
	private JLabel jLabel3 = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private JTextField _txt_subtotal = null;
	private JTextField _txt_total = null;
	private JButton _btn_buscar_remito = null;
	private JButton _btn_buscar_cliente = null;
	private JButton _btn_cargar_cliente = null;
	private JLabel jLabel4 = null;
	private JLabel jLabel5 = null;
	private JLabel jLabel6 = null;
	private JTextField _txt_idcondicion = null;
	private JTextField _txt_condicion_detalle = null;
	private JButton _btn_buscar_fecha = null;
	private JButton _btn_eliminar = null;
	private JButton _btn_cargar_remito = null;
	private JLabel jLabel7 = null;
	private JTextField _txt_idvendedor = null;
	private JTextField _txt_vendedor_detalle = null;
	private JButton _btn_eliminar_remito = null;
	private JButton _btn_editar_cliente = null;
	private JButton _btn_imprimir = null;
	private JButton _btn_error = null;
	private JTabbedPane jTabbedPane = null;
	private JPanel jPanel = null;
	private JScrollPane jScrollPane1 = null;
	private JTable jTable1 = null;
	private JButton _btn_pdc = null;
	private JButton _btn_nuevo = null;
	/**
	 * This is the default constructor
	 */
	public _Frame() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(860, 505);
		this.setContentPane(getJContentPane());
		this.setTitle("Carga de Comprobante");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel7 = new JLabel();
			jLabel7.setBounds(new Rectangle(15, 116, 121, 17));
			jLabel7.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel7.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel7.setText("Vendedor");
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(16, 140, 120, 16));
			jLabel6.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel6.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel6.setText("Condicion de Venta");
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(736, 420, 94, 18));
			jLabel5.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel5.setText("Total");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(628, 420, 94, 14));
			jLabel4.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel4.setText("Subtotal");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(641, 32, 53, 14));
			jLabel3.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3.setText("Fecha");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(47, 58, 88, 16));
			jLabel2.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2.setText("Remito");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(50, 80, 86, 19));
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setText("Cliente");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(52, 35, 83, 17));
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setText("Comprobante");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(get_lst_tipo_comprobante(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(get_txt_idcliente(), null);
			jContentPane.add(get_txt_descripcion_cliente(), null);
			jContentPane.add(get_txt_sucursal(), null);
			jContentPane.add(get_txt_numero(), null);
			jContentPane.add(get_txt_remito(), null);
			jContentPane.add(get_txt_idpedido(), null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(get_lst_letra(), null);
			jContentPane.add(get_txt_fecha(), null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(get_txt_subtotal(), null);
			jContentPane.add(get_txt_total(), null);
			jContentPane.add(get_btn_buscar_remito(), null);
			jContentPane.add(get_btn_buscar_cliente(), null);
			jContentPane.add(get_btn_cargar_cliente(), null);
			jContentPane.add(jLabel4, null);
			jContentPane.add(jLabel5, null);
			jContentPane.add(jLabel6, null);
			jContentPane.add(get_txt_idcondicion(), null);
			jContentPane.add(get_txt_condicion_detalle(), null);
			jContentPane.add(get_btn_buscar_fecha(), null);
			jContentPane.add(get_btn_cargar_remito(), null);
			jContentPane.add(jLabel7, null);
			jContentPane.add(get_txt_idvendedor(), null);
			jContentPane.add(get_txt_vendedor_detalle(), null);
			jContentPane.add(get_btn_eliminar_remito(), null);
			jContentPane.add(get_btn_editar_cliente(), null);
			jContentPane.add(getJTabbedPane(), null);
			jContentPane.add(get_btn_pdc(), null);
			jContentPane.add(get_txt_articulo(),null);
			jContentPane.add(get_txt_articulo_linea(),null);
			jContentPane.add(get_txt_articulo_stock(),null);
			jContentPane.add(get_txt_articulo_bloqueado(),null);
			jContentPane.add(get_txt_articulo_descripcion(),null);
			jContentPane.add(get_txt_articulo_pedido(),null);
			jContentPane.add(get_txt_articulo_stock(),null);
			jContentPane.add(get_txt_articulo_actualizacion(),null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jToolBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar() {
		if (jToolBar == null) {
			jToolBar = new JToolBar();
			jToolBar.setBounds(new Rectangle(2, 2, 839, 26));
			jToolBar.setFloatable(false);
			jToolBar.add(get_btn_nuevo());
			jToolBar.add(get_btn_cancelar());
			jToolBar.add(get_btn_grabar());
			jToolBar.add(get_btn_eliminar());
			jToolBar.add(get_btn_imprimir());
			jToolBar.add(get_btn_salir());
			jToolBar.addSeparator();
			jToolBar.add(get_btn_error());
		}
		return jToolBar;
	}

	/**
	 * This method initializes _btn_cancelar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cancelar() {
		if (_btn_cancelar == null) {
			_btn_cancelar = new JButton();
			_btn_cancelar.setIcon(new ImageIcon(getClass().getResource("/icons/stock_calc-cancel.png")));
			_btn_cancelar.setToolTipText("Cancelar Operacion");
		}
		return _btn_cancelar;
	}

	/**
	 * This method initializes _btn_grabar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_grabar() {
		if (_btn_grabar == null) {
			_btn_grabar = new JButton();
			_btn_grabar.setIcon(new ImageIcon(getClass().getResource("/icons/document-save.png")));
			_btn_grabar.setEnabled(false);
			_btn_grabar.setToolTipText("Grabar Comprobante");
		}
		return _btn_grabar;
	}

	/**
	 * This method initializes _btn_salir	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_salir() {
		if (_btn_salir == null) {
			_btn_salir = new JButton();
			_btn_salir.setIcon(new ImageIcon(getClass().getResource("/icons/exit.png")));
			_btn_salir.setToolTipText("Salir de La Aplicacion");
		}
		return _btn_salir;
	}

	/**
	 * This method initializes _lst_tipo_comprobante	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_tipo_comprobante() {
		if (_lst_tipo_comprobante == null) {
			_lst_tipo_comprobante = new JComboBox();
			_lst_tipo_comprobante.setBounds(new Rectangle(141, 34, 120, 18));
			_lst_tipo_comprobante.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _lst_tipo_comprobante;
	}

	/**
	 * This method initializes _txt_idcliente	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idcliente() {
		if (_txt_idcliente == null) {
			_txt_idcliente = new JTextField();
			_txt_idcliente.setBounds(new Rectangle(141, 81, 120, 18));
			_txt_idcliente.setHorizontalAlignment(JTextField.RIGHT);
			_txt_idcliente.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_idcliente;
	}

	/**
	 * This method initializes _txt_descripcion_cliente	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_descripcion_cliente() {
		if (_txt_descripcion_cliente == null) {
			_txt_descripcion_cliente = new JTextField();
			_txt_descripcion_cliente.setBounds(new Rectangle(331, 81, 237, 18));
			_txt_descripcion_cliente.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_descripcion_cliente.setToolTipText("Descripcion del Cliente");
			_txt_descripcion_cliente.setHorizontalAlignment(JTextField.LEFT);
			_txt_descripcion_cliente.setEditable(false);
		}
		return _txt_descripcion_cliente;
	}

	/**
	 * This method initializes _txt_sucursal	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_sucursal() {
		if (_txt_sucursal == null) {
			_txt_sucursal = new JTextField();
			_txt_sucursal.setBounds(new Rectangle(265, 34, 51, 18));
			_txt_sucursal.setEditable(false);
			_txt_sucursal.setToolTipText("sucursal de comprobante");
			_txt_sucursal.setHorizontalAlignment(JTextField.RIGHT);
			_txt_sucursal.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_sucursal;
	}

	/**
	 * This method initializes _txt_numero	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_numero() {
		if (_txt_numero == null) {
			_txt_numero = new JTextField();
			_txt_numero.setBounds(new Rectangle(318, 34, 106, 18));
			_txt_numero.setToolTipText("numero de comprobante");
			_txt_numero.setHorizontalAlignment(JTextField.RIGHT);
			_txt_numero.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_numero;
	}

	/**
	 * This method initializes _txt_remito	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_remito() {
		if (_txt_remito == null) {
			_txt_remito = new JTextField();
			_txt_remito.setBounds(new Rectangle(141, 58, 120, 18));
			_txt_remito.setToolTipText("Comprobante Origen");
			_txt_remito.setHorizontalAlignment(JTextField.RIGHT);
			_txt_remito.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_remito;
	}
	
	/**
	 * This method initializes _txt_idpedido	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idpedido() {
		if (_txt_idpedido == null) {
			_txt_idpedido = new JTextField();
			_txt_idpedido.setBounds(new Rectangle(332, 58, 120, 18));
			_txt_idpedido.setToolTipText("Pedido Origen");
			_txt_idpedido.setHorizontalAlignment(JTextField.RIGHT);
			_txt_idpedido.setEditable(false);
			_txt_idpedido.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_idpedido;
	}

	/**
	 * This method initializes _lst_letra	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_letra() {
		if (_lst_letra == null) {
			_lst_letra = new JComboBox();
			_lst_letra.setBounds(new Rectangle(427, 34, 53, 17));
			_lst_letra.setToolTipText("letra comprobante");
			_lst_letra.setEditable(false);
		}
		return _lst_letra;
	}

	/**
	 * This method initializes _txt_fecha	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_fecha() {
		if (_txt_fecha == null) {
			_txt_fecha = new JTextField();
			_txt_fecha.setBounds(new Rectangle(699, 31, 116, 18));
			_txt_fecha.setHorizontalAlignment(JTextField.RIGHT);
			_txt_fecha.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_fecha;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getJTable());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getJTable() {
		if (jTable == null) {
			jTable = new JTable();
		}
		return jTable;
	}
	
	public void setJTable(JTable table){
		this.jTable=table;
		this.jScrollPane.setViewportView(jTable);
	}

	/**
	 * This method initializes _txt_subtotal	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_subtotal() {
		if (_txt_subtotal == null) {
			_txt_subtotal = new JTextField();
			_txt_subtotal.setBounds(new Rectangle(629, 440, 99, 18));
			_txt_subtotal.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_subtotal.setHorizontalAlignment(JTextField.RIGHT);
			_txt_subtotal.setEditable(false);
			_txt_subtotal.setToolTipText("Subtotal Comprobante");
		}
		return _txt_subtotal;
	}

	/**
	 * This method initializes _txt_total	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_total() {
		if (_txt_total == null) {
			_txt_total = new JTextField();
			_txt_total.setBounds(new Rectangle(735, 440, 102, 18));
			_txt_total.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_total.setHorizontalAlignment(JTextField.RIGHT);
			_txt_total.setEditable(false);
			_txt_total.setToolTipText("Total Comprobante");
		}
		return _txt_total;
	}

	/**
	 * This method initializes _btn_buscar_remito	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_remito() {
		if (_btn_buscar_remito == null) {
			_btn_buscar_remito = new JButton();
			_btn_buscar_remito.setBounds(new Rectangle(265, 57, 19, 18));
			_btn_buscar_remito.setToolTipText("Buscar Comprobante Origen");
			_btn_buscar_remito.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-searchtool.png")));
		}
		return _btn_buscar_remito;
	}

	/**
	 * This method initializes _btn_buscar_cliente	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_cliente() {
		if (_btn_buscar_cliente == null) {
			_btn_buscar_cliente = new JButton();
			_btn_buscar_cliente.setBounds(new Rectangle(265, 81, 19, 18));
			_btn_buscar_cliente.setToolTipText("Buscar Cliente");
			_btn_buscar_cliente.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-searchtool.png")));
		}
		return _btn_buscar_cliente;
	}

	/**
	 * This method initializes _btn_cargar_cliente	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cargar_cliente() {
		if (_btn_cargar_cliente == null) {
			_btn_cargar_cliente = new JButton();
			_btn_cargar_cliente.setBounds(new Rectangle(287, 81, 19, 18));
			_btn_cargar_cliente.setToolTipText("Cargar Cliente");
			_btn_cargar_cliente.setIcon(new ImageIcon(getClass().getResource("/icons/reload3.png")));
		}
		return _btn_cargar_cliente;
	}

	/**
	 * This method initializes _txt_idcondicion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idcondicion() {
		if (_txt_idcondicion == null) {
			_txt_idcondicion = new JTextField();
			_txt_idcondicion.setBounds(new Rectangle(142, 141, 32, 18));
			_txt_idcondicion.setToolTipText("Codigo de Condicion de Venta");
			_txt_idcondicion.setHorizontalAlignment(JTextField.RIGHT);
			_txt_idcondicion.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_idcondicion;
	}

	/**
	 * This method initializes _txt_condicion_detalle	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_condicion_detalle() {
		if (_txt_condicion_detalle == null) {
			_txt_condicion_detalle = new JTextField();
			_txt_condicion_detalle.setBounds(new Rectangle(177, 141, 138, 18));
			_txt_condicion_detalle.setToolTipText("Descripcion de la Condicion de Venta");
			_txt_condicion_detalle.setEditable(false);
			_txt_condicion_detalle.setHorizontalAlignment(JTextField.LEFT);
			_txt_condicion_detalle.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_condicion_detalle;
	}

	/**
	 * This method initializes _btn_buscar_fecha	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_fecha() {
		if (_btn_buscar_fecha == null) {
			_btn_buscar_fecha = new JButton();
			_btn_buscar_fecha.setBounds(new Rectangle(818, 32, 22, 17));
			_btn_buscar_fecha.setToolTipText("Buscar Fecha");
			_btn_buscar_fecha.setIcon(new ImageIcon(getClass().getResource("/icons/calendar-16.gif")));
		}
		return _btn_buscar_fecha;
	}

	/**
	 * This method initializes _btn_eliminar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_eliminar() {
		if (_btn_eliminar == null) {
			_btn_eliminar = new JButton();
			_btn_eliminar.setIcon(new ImageIcon(getClass().getResource("/icons/edit-delete.png")));
			_btn_eliminar.setToolTipText("Anular Comprobante");
			_btn_eliminar.setEnabled(false);
		}
		return _btn_eliminar;
	}

	/**
	 * This method initializes _btn_cargar_remito	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cargar_remito() {
		if (_btn_cargar_remito == null) {
			_btn_cargar_remito = new JButton();
			_btn_cargar_remito.setBounds(new Rectangle(288, 57, 18, 19));
			_btn_cargar_remito.setIcon(new ImageIcon(getClass().getResource("/icons/reload.png")));
			_btn_cargar_remito.setToolTipText("Cargar Remito");
		}
		return _btn_cargar_remito;
	}

	/**
	 * This method initializes _txt_idvendedor	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idvendedor() {
		if (_txt_idvendedor == null) {
			_txt_idvendedor = new JTextField();
			_txt_idvendedor.setBounds(new Rectangle(142, 117, 32, 18));
			_txt_idvendedor.setEditable(false);
			_txt_idvendedor.setEnabled(false);
			_txt_idvendedor.setHorizontalAlignment(JTextField.LEFT);
		}
		return _txt_idvendedor;
	}

	/**
	 * This method initializes _txt_vendedor_detalle	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_vendedor_detalle() {
		if (_txt_vendedor_detalle == null) {
			_txt_vendedor_detalle = new JTextField();
			_txt_vendedor_detalle.setBounds(new Rectangle(177, 117, 137, 18));
			_txt_vendedor_detalle.setHorizontalAlignment(JTextField.LEFT);
			_txt_vendedor_detalle.setEditable(false);
		}
		return _txt_vendedor_detalle;
	}

	/**
	 * This method initializes _btn_eliminar_remito	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_eliminar_remito() {
		if (_btn_eliminar_remito == null) {
			_btn_eliminar_remito = new JButton();
			_btn_eliminar_remito.setBounds(new Rectangle(309, 57, 20, 19));
			_btn_eliminar_remito.setToolTipText("Eliminar Remito");
			_btn_eliminar_remito.setEnabled(false);
			_btn_eliminar_remito.setIcon(new ImageIcon(getClass().getResource("/icons/edit-delete.png")));
		}
		return _btn_eliminar_remito;
	}

	/**
	 * This method initializes _btn_editar_cliente	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_editar_cliente() {
		if (_btn_editar_cliente == null) {
			_btn_editar_cliente = new JButton();
			_btn_editar_cliente.setBounds(new Rectangle(309, 81, 19, 18));
			_btn_editar_cliente.setToolTipText("Maestro de Cliente");
			_btn_editar_cliente.setEnabled(false);
			_btn_editar_cliente.setIcon(new ImageIcon(getClass().getResource("/icons/edit-find-replace.png")));
		}
		return _btn_editar_cliente;
	}

	/**
	 * This method initializes _btn_imprimir	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_imprimir() {
		if (_btn_imprimir == null) {
			_btn_imprimir = new JButton();
			_btn_imprimir.setIcon(new ImageIcon(getClass().getResource("/icons/document-print.png")));
			_btn_imprimir.setEnabled(false);
			_btn_imprimir.setToolTipText("Imprimir Comprobante");
		}
		return _btn_imprimir;
	}

	/**
	 * This method initializes _btn_error	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_error() {
		if (_btn_error == null) {
			_btn_error = new JButton();
			_btn_error.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-media-record.png")));
			_btn_error.setToolTipText("Envio de Informacion/Error a Sistemas");
		}
		return _btn_error;
	}

	/**
	 * This method initializes jTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.setBounds(new Rectangle(5, 164, 839, 227));
			jTabbedPane.addTab("Articulos", null, getJScrollPane(), null);
			jTabbedPane.addTab("Conceptos", null, getJPanel(), null);
		}
		return jTabbedPane;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setEnabled(false);
			jPanel.add(getJScrollPane1(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setBounds(new Rectangle(6, 9, 732, 148));
			jScrollPane1.setEnabled(false);
			jScrollPane1.setViewportView(getJTable1());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes jTable1	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getJTable1() {
		if (jTable1 == null) {
			jTable1 = new JTable();
			jTable1.setEnabled(false);
		}
		return jTable1;
	}

	public void setJTable1(JTable table){
		this.jTable1=table;
		this.jScrollPane1.setViewportView(table);
	}

	/**
	 * This method initializes _btn_pdc	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_pdc() {
		if (_btn_pdc == null) {
			_btn_pdc = new JButton();
			_btn_pdc.setBounds(new Rectangle(454, 57, 19, 19));
			_btn_pdc.setToolTipText("Editar Pedido");
			_btn_pdc.setEnabled(false);
			_btn_pdc.setIcon(new ImageIcon(getClass().getResource("/icons/edit-find-replace.png")));
		}
		return _btn_pdc;
	}
	
	/**
	 * This method initializes _txt_articulo_actualizacion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_articulo_actualizacion() {
		if (_txt_articulo_actualizacion == null) {
			_txt_articulo_actualizacion = new JTextField();
			_txt_articulo_actualizacion.setEditable(false);
			_txt_articulo_actualizacion.setHorizontalAlignment(JTextField.RIGHT);
			_txt_articulo_actualizacion.setLocation(new Point(413, 397));
			_txt_articulo_actualizacion.setSize(new Dimension(101, 18));
			_txt_articulo_actualizacion.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_articulo_actualizacion;
	}
	
	/**
	 * This method initializes _txt_articulo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_articulo() {
		if (_txt_articulo == null) {
			_txt_articulo = new JTextField();
			_txt_articulo.setEditable(false);
			_txt_articulo.setBounds(new Rectangle(9, 397, 120, 18));
			_txt_articulo.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_articulo;
	}
	
	/**
	 * This method initializes _txt_articulo_bloqueado	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_articulo_bloqueado() {
		if (_txt_articulo_bloqueado == null) {
			_txt_articulo_bloqueado = new JTextField();
			_txt_articulo_bloqueado.setBounds(new Rectangle(693, 397, 105, 18));
			_txt_articulo_bloqueado.setEnabled(true);
			_txt_articulo_bloqueado.setFont(new Font("Dialog", Font.PLAIN, 8));
			_txt_articulo_bloqueado.setEditable(false);
			_txt_articulo_bloqueado.setToolTipText("Indicador si esta bloqueado para la venta");
		}
		return _txt_articulo_bloqueado;
	}

	/**
	 * This method initializes _txt_articulo_linea	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_articulo_linea() {
		if (_txt_articulo_linea == null) {
			_txt_articulo_linea = new JTextField();
			_txt_articulo_linea.setBounds(new Rectangle(515, 397, 176, 18));
			_txt_articulo_linea.setEditable(false);
			_txt_articulo_linea.setFont(new Font("Dialog", Font.PLAIN, 8));
		}
		return _txt_articulo_linea;
	}

	/**
	 * This method initializes _txt_articulo_descripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_articulo_descripcion() {
		if (_txt_articulo_descripcion == null) {
			_txt_articulo_descripcion = new JTextField();
			_txt_articulo_descripcion.setEditable(false);
			_txt_articulo_descripcion.setBounds(new Rectangle(133, 397, 234, 18));
			_txt_articulo_descripcion.setFont(new Font("Dialog", Font.PLAIN, 8));
		}
		return _txt_articulo_descripcion;
	}

	/**
	 * This method initializes _txt_articulo_stock	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_articulo_stock() {
		if (_txt_articulo_stock == null) {
			_txt_articulo_stock = new JTextField();
			_txt_articulo_stock.setEditable(false);
			_txt_articulo_stock.setBounds(new Rectangle(368, 397, 43, 18));
			_txt_articulo_stock.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_articulo_stock.setToolTipText("Stock");
		}
		return _txt_articulo_stock;
	}

	/**
	 * This method initializes _txt_articulo_pedido	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_articulo_pedido() {
		if (_txt_articulo_pedido == null) {
			_txt_articulo_pedido = new JTextField();
			_txt_articulo_pedido.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_articulo_pedido.setEditable(false);
			_txt_articulo_pedido.setEnabled(true);
			_txt_articulo_pedido.setToolTipText("Mercaderia que esta pedida");
			_txt_articulo_pedido.setSize(new Dimension(49, 18));
			_txt_articulo_pedido.setLocation(new Point(802, 397));
			_txt_articulo_pedido.setHorizontalAlignment(JTextField.RIGHT);
		}
		return _txt_articulo_pedido;
	}

	/**
	 * This method initializes _btn_nuevo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_nuevo() {
		if (_btn_nuevo == null) {
			_btn_nuevo = new JButton();
			_btn_nuevo.setIcon(new ImageIcon(getClass().getResource("/icons/document-new.png")));
			_btn_nuevo.setToolTipText("Nuevo");
		}
		return _btn_nuevo;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
