package aplicacion.proveedor.archivo.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import aplicacion.herramientas.java.*;
import aplicacion.herramientas.java.table.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.GridBagLayout;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumnModel;


import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTabbedPane;

import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import javax.swing.JToolBar;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * This method initializes _requiere_remitos	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_requiere_remitos() {
		if (_requiere_remitos == null) {
			_requiere_remitos = new JComboBox();
			_requiere_remitos.setBounds(new Rectangle(181, 276, 118, 19));
		}
		return _requiere_remitos;
	}

	/**
	 * This method initializes _requiere_impuestos_internos	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_requiere_impuestos_internos() {
		if (_requiere_impuestos_internos == null) {
			_requiere_impuestos_internos = new JComboBox();
			_requiere_impuestos_internos.setBounds(new Rectangle(180, 186, 120, 17));
		}
		return _requiere_impuestos_internos;
	}

	/**
	 * This method initializes _txt_alicuota_impuesto_interno	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_alicuota_impuesto_interno() {
		if (_txt_alicuota_impuesto_interno == null) {
			_txt_alicuota_impuesto_interno = new JTextField();
			_txt_alicuota_impuesto_interno.setBounds(new Rectangle(361, 187, 52, 16));
		}
		return _txt_alicuota_impuesto_interno;
	}

	/**
	 * This method initializes _txt_alicuota_ingresos_brutos	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_alicuota_ingresos_brutos() {
		if (_txt_alicuota_ingresos_brutos == null) {
			_txt_alicuota_ingresos_brutos = new JTextField();
			_txt_alicuota_ingresos_brutos.setBounds(new Rectangle(359, 12, 51, 16));
		}
		return _txt_alicuota_ingresos_brutos;
	}

	/**
	 * This method initializes _txt_alicuota_percepcion_iva	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_alicuota_percepcion_iva() {
		if (_txt_alicuota_percepcion_iva == null) {
			_txt_alicuota_percepcion_iva = new JTextField();
			_txt_alicuota_percepcion_iva.setBounds(new Rectangle(359, 30, 50, 15));
		}
		return _txt_alicuota_percepcion_iva;
	}

	/**
	 * This method initializes jPanel9	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel9() {
		if (jPanel9 == null) {
			jLabel39 = new JLabel();
			jLabel39.setBounds(new Rectangle(20, 51, 95, 18));
			jLabel39.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel39.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel39.setText("Equivalente Web");
			jLabel38 = new JLabel();
			jLabel38.setBounds(new Rectangle(3, 15, 75, 18));
			jLabel38.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel38.setText("Transporte");
			jPanel9 = new JPanel();
			jPanel9.setLayout(null);
			jPanel9.setBounds(new Rectangle(440, 13, 307, 146));
			jPanel9.setBackground(Color.lightGray);
			jPanel9.add(jLabel38, null);
			jPanel9.add(get_txt_idtransporte(), null);
			jPanel9.add(get_txt_transporte_descripcion(), null);
			jPanel9.add(get_txt_equivalencia(), null);
			jPanel9.add(jLabel39, null);
		}
		return jPanel9;
	}

	/**
	 * This method initializes jToolBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar() {
		if (jToolBar == null) {
			jToolBar = new JToolBar();
			jToolBar.setSize(new Dimension(766, 22));
			jToolBar.setLocation(new Point(0, 0));
			jToolBar.setEnabled(true);
			jToolBar.setFloatable(false);
			jToolBar.add(get_btn_nuevo());
			jToolBar.add(get_btn_grabar());
			jToolBar.add(get_btn_cancelar());
			jToolBar.add(get_btn_reporte());
			jToolBar.add(get_btn_pago());
			jToolBar.add(get_btn_analitico());
			jToolBar.add(get_btn_rotulo());
			jToolBar.add(get_btn_salir());
			jToolBar.addSeparator();
			jToolBar.add(get_btn_error());
		}
		return jToolBar;
	}

	/**
	 * This method initializes _btn_salir	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_salir() {
		if (_btn_salir == null) {
			_btn_salir = new JButton();
			_btn_salir.setToolTipText("Salir");
			_btn_salir.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-logout.png")));
		}
		return _btn_salir;
	}

	/**
	 * This method initializes _btn_analitico	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_analitico() {
		if (_btn_analitico == null) {
			_btn_analitico = new JButton();
			_btn_analitico.setIcon(new ImageIcon(getClass().getResource("/icons/stock_hyperlink-internet-search.png")));
		}
		return _btn_analitico;
	}

	/**
	 * This method initializes _btn_reporte	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_reporte() {
		if (_btn_reporte == null) {
			_btn_reporte = new JButton();
			_btn_reporte.setIcon(new ImageIcon(getClass().getResource("/icons/format-justify-right.png")));
			_btn_reporte.setToolTipText("Ver Reporte");
		}
		return _btn_reporte;
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

	
	public JComboBox _actualiza_precios = null;
	private JButton _btn_actualizar_saldo = null;

	private JButton _btn_buscar_proveedor = null;


	private JButton _btn_cancelar = null;

	private JButton _btn_grabar = null;

	private JButton _btn_nuevo = null;
	private JButton _btn_pago = null;
	private JButton _btn_rotulo = null;
	public JComboBox _imprime_etiquetas = null;
	public JComboBox _permite_articulos = null;
	public JComboBox _requiere_ingresos_brutos = null;
	public JComboBox _requiere_iva10 = null;
	public JComboBox _requiere_iva21 = null;

	public JComboBox _requiere_iva27 = null;
	public JComboBox _requiere_neto_no_gravado = null;
	public JComboBox _requiere_percepcion_ganancias = null;

	public JComboBox _requiere_percepcion_iva = null;


	public JComboBox _requiere_rg3337 = null;

	private JTextField _txt_cond_iva = null;


	private JTextField _txt_cond_venta = null;


	private JTextField _txt_condicion_detalle = null;


	private JTextField _txt_condicion_venta_detalle = null;


	private JTextField _txt_contacto = null;


	private JTextField _txt_cuit = null;


	private JTextField _txt_debe = null;


	private JTextField _txt_domicilio = null;

	private JTextField _txt_fax = null;

	private JTextField _txt_haber = null;


	private JTextField _txt_idproveedor = null;

	private JTextField _txt_idprovincia = null;

	private JTextField _txt_localidad = null;

	private JTextField _txt_numero = null;

	private JTextArea  _txt_observaciones = null;


	private JTextField _txt_piso = null;


	private JTextField _txt_postal = null;

	private JTextField _txt_proveedordescripcion = null;
	private JTextField _txt_provincia = null;
	private JTextField _txt_saldo = null;
	private JTextField _txt_telefono = null;
	private JTextField _txt_tipo_doc = null;
	private JTextField _txt_tipo_doc_detalle = null;
	private JLabel calle = null;
	private String[] columns=new String[]{""};
	private String[] columns1=new String[]{""};
	private JPanel Comprobantes = null;
	private DefaultTableModel defaultTableModel;
	private DefaultTableModel defaultTableModel1;
	Font fuente=new Font("Arial", Font.PLAIN, 9);  //  @jve:decl-index=0:
	private JPanel General = null;
	private String iduser="";
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel10 = null;
	private JLabel jLabel11 = null;
	private JLabel jLabel12 = null;
	private JLabel jLabel13 = null;
	private JLabel jLabel131 = null;
	
	private JLabel jLabel1311 = null;



	private JLabel jLabel14 = null;

	private JLabel jLabel15 = null;

	private JLabel jLabel16 = null;

	private JLabel jLabel17 = null;
	private JLabel jLabel18 = null;
	private JLabel jLabel19 = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel20 = null;

	private JLabel jLabel21 = null;

	private JLabel jLabel22 = null;

	private JLabel jLabel23 = null;

	private JLabel jLabel24 = null;

	private JLabel jLabel25 = null;

	private JLabel jLabel26 = null;

	private JLabel jLabel27 = null;

	private JLabel jLabel28 = null;

	private JLabel jLabel29 = null;

	private JLabel jLabel3 = null;

	private JLabel jLabel30 = null;

	private JLabel jLabel31 = null;

	private JLabel jLabel32 = null;

	private JLabel jLabel4 = null;

	private JLabel jLabel5 = null;

	private JLabel jLabel6 = null;

	private JLabel jLabel7 = null;

	private JLabel jLabel8 = null;

	private JLabel jLabel9 = null;

	private JPanel jPanel = null;

	private JPanel jPanel1 = null;

	private JPanel jPanel3 = null;

	private JPanel jPanel4 = null;

	private JPanel jPanel5 = null;

	private JPanel jPanel6 = null;

	private JPanel jPanel7 = null;

	private JScrollPane jScrollPane = null;

	private JScrollPane jScrollPane1 = null;

	private JTabbedPane jTabbedPane = null;

	private JTable jTable = null;

	private JTable jTable1 = null;

	private int[] sizes=null;

	private int[] sizes1=null;


	private JTextField txt_email = null;
	private JLabel jLabel33 = null;
	private JComboBox _requiere_remitos = null;
	private JLabel jLabel34 = null;
	private JComboBox _requiere_impuestos_internos = null;
	private JTextField _txt_alicuota_impuesto_interno = null;
	private JLabel jLabel35 = null;
	private JLabel jLabel36 = null;
	private JTextField _txt_alicuota_ingresos_brutos = null;
	private JTextField _txt_alicuota_percepcion_iva = null;
	private JLabel jLabel37 = null;
	private JPanel jPanel9 = null;
	private JToolBar jToolBar = null;
	private JButton _btn_salir = null;
	private JButton _btn_analitico = null;
	private JButton _btn_reporte = null;
	private JButton _btn_error = null;
	private JLabel jLabel41 = null;
	private JTextField _txt_Descuento = null;
	private JLabel jLabel38 = null;
	private JTextField _txt_idtransporte = null;
	private JTextField _txt_transporte_descripcion = null;
	private JTextField _txt_equivalencia = null;
	private JLabel jLabel39 = null;
	/**
	 * This is the default constructor
	 */
	public _Frame() {
		super();
		initialize();
	}

	/**
	 * This method initializes _actualiza_precios	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_actualiza_precios() {
		if (_actualiza_precios == null) {
			_actualiza_precios = new JComboBox();
			_actualiza_precios.setLocation(new Point(180, 255));
			_actualiza_precios.setSize(new Dimension(120, 18));
		}
		return _actualiza_precios;
	}

	/**
	 * This method initializes _btn_actualizar_saldo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_actualizar_saldo() {
		if (_btn_actualizar_saldo == null) {
			_btn_actualizar_saldo = new JButton();
			_btn_actualizar_saldo.setBounds(new Rectangle(18, 3, 28, 18));
			URL resourceURL = getClass().getClassLoader().getResource("icons/stock_refresh.png");
			_btn_actualizar_saldo.setIcon(new ImageIcon(resourceURL));
		}
		return _btn_actualizar_saldo;
	}

	/**
	 * This method initializes _btn_buscar_proveedor	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_proveedor() {
		if (_btn_buscar_proveedor == null) {
			_btn_buscar_proveedor = new JButton();
			_btn_buscar_proveedor.setBounds(new Rectangle(176, 38, 25, 18));
			URL resourceURL = getClass().getClassLoader().getResource("icons/search.png");
			_btn_buscar_proveedor.setIcon(new ImageIcon(resourceURL));
		}
		return _btn_buscar_proveedor;
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
			_btn_cancelar.setToolTipText("Cancelar");
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
			_btn_grabar.setText("");
			_btn_grabar.setToolTipText("Guardar");
			_btn_grabar.setIcon(new ImageIcon(getClass().getResource("/icons/document-save.png")));
		}
		return _btn_grabar;
	}

	/**
	 * This method initializes _btn_nuevo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_nuevo() {
		if (_btn_nuevo == null) {
			_btn_nuevo = new JButton();
			_btn_nuevo.setIcon(new ImageIcon(getClass().getResource("/icons/filenew.png")));
			_btn_nuevo.setToolTipText("Nuevo");
		}
		return _btn_nuevo;
	}

	/**
	 * This method initializes _btn_pago	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_pago() {
		if (_btn_pago == null) {
			_btn_pago = new JButton();
			_btn_pago.setIcon(new ImageIcon(getClass().getResource("/icons/cash-icon.png")));
			_btn_pago.setToolTipText("Pago");
		}
		return _btn_pago;
	}

	/**
	 * This method initializes _btn_rotulo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	
	public JButton get_btn_rotulo() {
		if (_btn_rotulo == null) {
			_btn_rotulo = new JButton();
			_btn_rotulo.setIcon(new ImageIcon(getClass().getResource("/icons/stock_print2.png")));
			_btn_rotulo.setToolTipText("Imprimir Rotulo");
		}
		return _btn_rotulo;
	}

	
	/**
	 * This method initializes _imprime_etiquetas	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_imprime_etiquetas() {
		if (_imprime_etiquetas == null) {
			_imprime_etiquetas = new JComboBox();
			_imprime_etiquetas.setLocation(new Point(180, 233));
			_imprime_etiquetas.setSize(new Dimension(120, 18));
		}
		return _imprime_etiquetas;
	}
	
	/**
	 * This method initializes _permite_articulos	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_permite_articulos() {
		if (_permite_articulos == null) {
			_permite_articulos = new JComboBox();
			_permite_articulos.setLocation(new Point(180, 211));
			_permite_articulos.setSize(new Dimension(120, 18));
		}
		return _permite_articulos;
	}
	/**
	 * This method initializes _requiere_ingresos_brutos	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_requiere_ingresos_brutos() {
		if (_requiere_ingresos_brutos == null) {
			_requiere_ingresos_brutos = new JComboBox();
			_requiere_ingresos_brutos.setBounds(new Rectangle(180, 10, 120, 18));
			_requiere_ingresos_brutos.setFont(new Font("Dialog", Font.BOLD, 10));
		}
		return _requiere_ingresos_brutos;
	}

	/**
	 * This method initializes _requiere_iva10	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_requiere_iva10() {
		if (_requiere_iva10 == null) {
			_requiere_iva10 = new JComboBox();
			_requiere_iva10.setBounds(new Rectangle(180, 120, 120, 18));
		}
		return _requiere_iva10;
	}

	/**
	 * This method initializes _requiere_iva21	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_requiere_iva21() {
		if (_requiere_iva21 == null) {
			_requiere_iva21 = new JComboBox();
			_requiere_iva21.setBounds(new Rectangle(180, 98, 120, 18));
		}
		return _requiere_iva21;
	}

	/**
	 * This method initializes _requiere_iva27	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_requiere_iva27() {
		if (_requiere_iva27 == null) {
			_requiere_iva27 = new JComboBox();
			_requiere_iva27.setBounds(new Rectangle(180, 142, 120, 18));
		}
		return _requiere_iva27;
	}

	/**
	 * This method initializes _requiere_neto_no_gravado	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_requiere_neto_no_gravado() {
		if (_requiere_neto_no_gravado == null) {
			_requiere_neto_no_gravado = new JComboBox();
			_requiere_neto_no_gravado.setBounds(new Rectangle(180, 76,120, 18));
		}
		return _requiere_neto_no_gravado;
	}

	/**
	 * This method initializes _requiere_percepcion_ganancias	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_requiere_percepcion_ganancias() {
		if (_requiere_percepcion_ganancias == null) {
			_requiere_percepcion_ganancias = new JComboBox();
			_requiere_percepcion_ganancias.setBounds(new Rectangle(180, 54, 120, 18));
		}
		return _requiere_percepcion_ganancias;
	}

	/**
	 * This method initializes _requiere_percepcion_iva	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_requiere_percepcion_iva() {
		if (_requiere_percepcion_iva == null) {
			_requiere_percepcion_iva = new JComboBox();
			_requiere_percepcion_iva.setBounds(new Rectangle(180, 32, 120, 18));
		}
		return _requiere_percepcion_iva;
	}

	/**
	 * This method initializes _requiere_rg3337	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_requiere_rg3337() {
		if (_requiere_rg3337 == null) {
			_requiere_rg3337 = new JComboBox();
			_requiere_rg3337.setBounds(new Rectangle(180, 164, 120, 18));
		}
		return _requiere_rg3337;
	}

	/**
	 * This method initializes _txt_cond_iva	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_cond_iva() {
		if (_txt_cond_iva == null) {
			_txt_cond_iva = new JTextField();
			_txt_cond_iva.setBounds(new Rectangle(5, 20, 47, 18));
			_txt_cond_iva.setFont(new Font("Arial", Font.PLAIN, 10));
		}
		return _txt_cond_iva;
	}

	/**
	 * This method initializes _txt_cond_venta	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_cond_venta() {
		if (_txt_cond_venta == null) {
			_txt_cond_venta = new JTextField();
			_txt_cond_venta.setBounds(new Rectangle(6, 60, 45, 18));
			_txt_cond_venta.setFont(new Font("Arial", Font.PLAIN, 10));
		}
		return _txt_cond_venta;
	}

	/**
	 * This method initializes _txt_condicion_detalle	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_condicion_detalle() {
		if (_txt_condicion_detalle == null) {
			_txt_condicion_detalle = new JTextField();
			_txt_condicion_detalle.setEditable(false);
			_txt_condicion_detalle.setFont(new Font("Arial", Font.PLAIN, 10));
			_txt_condicion_detalle.setBounds(new Rectangle(55, 20, 155, 18));
		}
		return _txt_condicion_detalle;
	}

	/**
	 * This method initializes _txt_condicion_venta_detalle	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_condicion_venta_detalle() {
		if (_txt_condicion_venta_detalle == null) {
			_txt_condicion_venta_detalle = new JTextField();
			_txt_condicion_venta_detalle.setEditable(false);
			_txt_condicion_venta_detalle.setFont(new Font("Arial", Font.PLAIN, 10));
			_txt_condicion_venta_detalle.setBounds(new Rectangle(55, 61, 157, 18));
		}
		return _txt_condicion_venta_detalle;
	}

	/**
	 * This method initializes _txt_contacto	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_contacto() {
		if (_txt_contacto == null) {
			_txt_contacto = new JTextField();
			_txt_contacto.setBounds(new Rectangle(4, 58, 205, 18));
			_txt_contacto.setFont(new Font("Arial", Font.PLAIN, 10));
		}
		return _txt_contacto;
	}

	/**
	 * This method initializes _txt_cuit	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_cuit() {
		if (_txt_cuit == null) {
			_txt_cuit = new JTextField();
			_txt_cuit.setBounds(new Rectangle(378, 20, 164, 18));
			_txt_cuit.setFont(new Font("Arial", Font.PLAIN, 10));
		}
		return _txt_cuit;
	}

	/**
	 * This method initializes _txt_debe	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_debe() {
		if (_txt_debe == null) {
			_txt_debe = new JTextField();
			_txt_debe.setBackground(Color.black);
			_txt_debe.setText("0.00");
			_txt_debe.setForeground(Color.white);
			Font fuente=new Font("Arial", Font.ITALIC, 11);
			_txt_debe.setFont(fuente);
			_txt_debe.setHorizontalAlignment(JTextField.RIGHT);
			_txt_debe.setBounds(new Rectangle(454, 267, 70, 18));
		}
		return _txt_debe;
	}

	/**
	 * This method initializes _txt_domicilio	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_domicilio() {
		if (_txt_domicilio == null) {
			_txt_domicilio = new JTextField();
			_txt_domicilio.setBounds(new Rectangle(3, 19, 208, 18));
			_txt_domicilio.setFont(new Font("Arial", Font.PLAIN, 10));
		}
		return _txt_domicilio;
	}

	/**
	 * This method initializes txt_email	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_email() {
		if (txt_email == null) {
			txt_email = new JTextField();
			txt_email.setBounds(new Rectangle(420, 22, 264, 18));
			txt_email.setFont(new Font("Arial", Font.PLAIN, 10));
		}
		return txt_email;
	}

	/**
	 * This method initializes _txt_fax	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_fax() {
		if (_txt_fax == null) {
			_txt_fax = new JTextField();
			_txt_fax.setBounds(new Rectangle(212, 22, 206, 18));
			_txt_fax.setFont(new Font("Arial", Font.PLAIN, 10));
		}
		return _txt_fax;
	}

	/**
	 * This method initializes _txt_haber	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_haber() {
		if (_txt_haber == null) {
			_txt_haber = new JTextField();
			_txt_haber.setBackground(Color.black);
			_txt_haber.setText("0.00");
			_txt_haber.setForeground(Color.white);
			Font fuente=new Font("Arial", Font.ITALIC, 11);
			_txt_haber.setFont(fuente);
			_txt_haber.setHorizontalAlignment(JTextField.RIGHT);
			_txt_haber.setBounds(new Rectangle(525, 267, 70, 18));
		}
		return _txt_haber;
	}

	/**
	 * This method initializes idCliente	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idproveedor() {
		if (_txt_idproveedor == null) {
			_txt_idproveedor = new JTextField();
			_txt_idproveedor.setBounds(new Rectangle(14, 38, 159, 17));
			_txt_idproveedor.setFont(new Font("Arial", Font.PLAIN, 10));
				
		}
		return _txt_idproveedor;
	}

	/**
	 * This method initializes _txt_idprovincia	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idprovincia() {
		if (_txt_idprovincia == null) {
			_txt_idprovincia = new JTextField();
			_txt_idprovincia.setBounds(new Rectangle(214, 54, 36, 18));
			_txt_idprovincia.setFont(new Font("Arial", Font.PLAIN, 10));
		}
		return _txt_idprovincia;
	}

	/**
	 * This method initializes _txt_localidad	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_localidad() {
		if (_txt_localidad == null) {
			_txt_localidad = new JTextField();
			_txt_localidad.setBounds(new Rectangle(61, 53, 151, 18));
			_txt_localidad.setFont(new Font("Arial", Font.PLAIN, 10));
		}
		return _txt_localidad;
	}

	/**
	 * This method initializes _txt_numero	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_numero() {
		if (_txt_numero == null) {
			_txt_numero = new JTextField();
			_txt_numero.setBounds(new Rectangle(214, 19, 58, 18));
			_txt_numero.setFont(new Font("Arial", Font.PLAIN, 10));
		}
		return _txt_numero;
	}

	/**
	 * This method initializes _txt_observaciones	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea get_txt_observaciones() {
		if (_txt_observaciones == null) {
			_txt_observaciones = new JTextArea();
			_txt_observaciones.setWrapStyleWord(true);
			_txt_observaciones.setFont(new Font("Arial", Font.PLAIN, 10));
			_txt_observaciones.setBounds(new Rectangle(213, 59, 471, 56));
		}
		return _txt_observaciones;
	}

	/**
	 * This method initializes _txt_piso	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_piso() {
		if (_txt_piso == null) {
			_txt_piso = new JTextField();
			_txt_piso.setBounds(new Rectangle(275, 19, 49, 18));
			_txt_piso.setFont(new Font("Arial", Font.PLAIN, 10));
		}
		return _txt_piso;
	}

	/**
	 * This method initializes _txt_postal	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_postal() {
		if (_txt_postal == null) {
			_txt_postal = new JTextField();
			_txt_postal.setBounds(new Rectangle(3, 53, 56, 18));
			_txt_postal.setFont(new Font("Arial", Font.PLAIN, 10));
		}
		return _txt_postal;
	}

	/**
	 * This method initializes ClienteDescripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_proveedordescripcion() {
		if (_txt_proveedordescripcion == null) {
			_txt_proveedordescripcion = new JTextField();
			_txt_proveedordescripcion.setBounds(new Rectangle(203, 37, 517, 18));
			_txt_proveedordescripcion.setFont(new Font("Arial", Font.PLAIN, 10));
			
		}
		return _txt_proveedordescripcion;
	}

	/**
	 * This method initializes _txt_provincia	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_provincia() {
		if (_txt_provincia == null) {
			_txt_provincia = new JTextField();
			_txt_provincia.setBounds(new Rectangle(251, 54, 125, 18));
			_txt_provincia.setFont(new Font("Arial", Font.PLAIN, 10));
		}
		return _txt_provincia;
	}

	/**
	 * This method initializes _txt_saldo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_saldo() {
		if (_txt_saldo == null) {
			_txt_saldo = new JTextField();
			_txt_saldo.setEditable(false);
			_txt_saldo.setBackground(Color.black);
			_txt_saldo.setText("0.00");
			_txt_saldo.setForeground(Color.white);
			Font fuente=new Font("Arial", Font.ITALIC, 11);
			_txt_saldo.setFont(fuente);
			_txt_saldo.setHorizontalAlignment(JTextField.RIGHT);
			_txt_saldo.setBounds(new Rectangle(596, 267, 80, 18));
		}
		return _txt_saldo;
	}

	/**
	 * This method initializes _txt_telefono	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_telefono() {
		if (_txt_telefono == null) {
			_txt_telefono = new JTextField();
			_txt_telefono.setBounds(new Rectangle(3, 22, 206, 18));
			_txt_telefono.setFont(new Font("Arial", Font.PLAIN, 10));
		}
		return _txt_telefono;
	}

	/**
	 * This method initializes _txt_tipo_doc	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_tipo_doc() {
		if (_txt_tipo_doc == null) {
			_txt_tipo_doc = new JTextField();
			_txt_tipo_doc.setBounds(new Rectangle(213, 20, 49, 18));
			_txt_tipo_doc.setFont(new Font("Arial", Font.PLAIN, 10));
		}
		return _txt_tipo_doc;
	}

	/**
	 * This method initializes _txt_tipo_doc_detalle	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_tipo_doc_detalle() {
		if (_txt_tipo_doc_detalle == null) {
			_txt_tipo_doc_detalle = new JTextField();
			_txt_tipo_doc_detalle.setEditable(false);
			_txt_tipo_doc_detalle.setFont(new Font("Arial", Font.PLAIN, 10));
			_txt_tipo_doc_detalle.setBounds(new Rectangle(266, 20, 100, 18));
		}
		return _txt_tipo_doc_detalle;
	}
	
	/**
	 * This method initializes Comprobantes	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getComprobantes() {
		if (Comprobantes == null) {
			Comprobantes = new JPanel();
			Comprobantes.setLayout(null);
			Comprobantes.add(getJPanel1(), null);
		}
		return Comprobantes;
	}

	/**
	 * This method initializes General	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getGeneral() {
		if (General == null) {
			General = new JPanel();
			General.setLayout(null);
			
			General.add(getJPanel(), null);
			General.add(getJPanel3(), null);
			General.add(getJPanel4(), null);
			
		}
		return General;
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jLabel = new JLabel();
			jLabel.setText("Proveedor");
			jLabel.setFont(new Font("Arial", Font.PLAIN, 10));
			jLabel.setBounds(new Rectangle(14, 24, 86, 14));
			jContentPane.add(this.get_txt_proveedordescripcion(), null);
			jContentPane.add(get_txt_idproveedor(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(getJTabbedPane(), null);
			jContentPane.add(get_btn_buscar_proveedor(), null);
			jContentPane.add(getJToolBar(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabel12 = new JLabel();
			jLabel12.setBounds(new Rectangle(620, 2, 85, 14));
			jLabel12.setText("Ubicacion");
			jLabel12.setFont(new Font("Arial", Font.PLAIN, 10));
			jLabel12.setForeground(Color.DARK_GRAY);
			jLabel10 = new JLabel();
			jLabel10.setText("Observaciones");
			jLabel10.setFont(new Font("Arial", Font.PLAIN, 10));
			jLabel10.setBounds(new Rectangle(213, 43, 92, 14));
			jLabel9 = new JLabel();
			jLabel9.setBounds(new Rectangle(214, 39, 80, 14));
			jLabel9.setFont(new Font("Arial", Font.PLAIN, 10));
			jLabel9.setText("Provincia");
			jLabel8 = new JLabel();
			jLabel8.setBounds(new Rectangle(276, 4, 41, 14));
			jLabel8.setFont(new Font("Arial", Font.PLAIN, 10));
			jLabel8.setText("Piso");
			jLabel7 = new JLabel();
			jLabel7.setText("Contacto");
			jLabel7.setFont(new Font("Arial", Font.PLAIN, 10));
			jLabel7.setBounds(new Rectangle(4, 42, 82, 14));
			jLabel6 = new JLabel();
			jLabel6.setText("e-mail");
			jLabel6.setFont(new Font("Arial", Font.PLAIN, 10));
			jLabel6.setBounds(new Rectangle(420, 5, 76, 14));
			jLabel5 = new JLabel();
			jLabel5.setText("Fax");
			jLabel5.setFont(new Font("Arial", Font.PLAIN, 10));
			jLabel5.setBounds(new Rectangle(213, 6, 60, 14));
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(61, 38, 117, 14));
			jLabel4.setFont(new Font("Arial", Font.PLAIN, 10));
			jLabel4.setText("Localidad");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(4, 38, 49, 14));
			jLabel3.setFont(new Font("Arial", Font.PLAIN, 10));
			jLabel3.setText("Postal");
			jLabel2 = new JLabel();
			jLabel2.setText("Telefono/Cel");
			jLabel2.setFont(new Font("Arial", Font.PLAIN, 10));
			jLabel2.setBounds(new Rectangle(5, 6, 104, 14));
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(214, 4, 56, 14));
			jLabel1.setFont(new Font("Arial", Font.PLAIN, 10));
			jLabel1.setText("Numero");
			calle = new JLabel();
			calle.setBounds(new Rectangle(3, 4, 59, 14));
			calle.setFont(new Font("Arial", Font.PLAIN, 10));
			calle.setText("Calle");
			
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBackground(new Color(200,200,210));
			jPanel.setBounds(new Rectangle(11, 4, 709, 78));
			jPanel.add(get_txt_domicilio(), null);
			jPanel.add(calle, null);
			jPanel.add(jLabel1, null);
			jPanel.add(get_txt_numero(), null);
			jPanel.add(jLabel3, null);
			jPanel.add(get_txt_postal(), null);
			jPanel.add(jLabel4, null);
			jPanel.add(get_txt_localidad(), null);
			jPanel.add(jLabel8, null);
			jPanel.add(get_txt_piso(), null);
			jPanel.add(jLabel9, null);
			jPanel.add(get_txt_provincia(), null);
			jPanel.add(jLabel12, null);
			jPanel.add(get_txt_idprovincia(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jLabel131 = new JLabel();
			jLabel131.setBounds(new Rectangle(65, 5, 235, 15));
			jLabel131.setFont(new Font("Arial", Font.ITALIC, 13));
			jLabel131.setForeground(Color.blue);
			jLabel131.setHorizontalAlignment(SwingConstants.LEFT);
			jLabel131.setText("Ultimos Movimientos");
			jLabel131.setBackground(Color.black);
			jLabel22 = new JLabel();
			jLabel22.setBounds(new Rectangle(601, 251, 71, 14));
			jLabel22.setText("Saldo");
			jLabel22.setBackground(Color.black);
			jLabel22.setForeground(Color.DARK_GRAY);
			jLabel22.setFont(fuente);
			Font fuente=new Font("Arial", Font.ITALIC, 13);
			jLabel22.setHorizontalAlignment(JTextField.RIGHT);
			jLabel21 = new JLabel();
			jLabel21.setBounds(new Rectangle(528, 252, 65, 12));
			jLabel21.setText("Haber");
			jLabel21.setBackground(Color.black);
			jLabel21.setForeground(Color.DARK_GRAY);
			
			jLabel21.setFont(fuente);
			jLabel21.setHorizontalAlignment(JTextField.RIGHT);
			jLabel20 = new JLabel();
			jLabel20.setBounds(new Rectangle(456, 251, 65, 12));
			jLabel20.setText("Debe");
			jLabel20.setBackground(Color.black);
			jLabel20.setForeground(Color.DARK_GRAY);
			jLabel20.setHorizontalAlignment(JTextField.RIGHT);
			jLabel20.setFont(fuente);
			jLabel13 = new JLabel();
			jLabel13.setBounds(new Rectangle(562, 3, 109, 16));
			jLabel13.setText("Cuenta");
			
			jLabel13.setBackground(Color.black);
			jLabel13.setForeground(Color.blue);
			fuente=new Font("Arial", Font.ITALIC, 13);
			jLabel13.setFont(fuente);
			jLabel13.setHorizontalAlignment(JTextField.RIGHT);
			
			jPanel1 = new JPanel();
			jPanel1.setLayout(null);
			jPanel1.setBackground(Color.lightGray);
			jPanel1.setBounds(new Rectangle(13, 15, 699, 300));
			jPanel1.add(getJScrollPane(), null);
			jPanel1.add(jLabel13, null);
			jPanel1.add(get_txt_saldo(), null);
			jPanel1.add(get_txt_debe(), null);
			jPanel1.add(get_txt_haber(), null);
			jPanel1.add(jLabel20, null);
			jPanel1.add(jLabel21, null);
			jPanel1.add(jLabel22, null);
			jPanel1.add(jLabel131, null);
			jPanel1.add(get_btn_actualizar_saldo(), null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes jPanel3	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jLabel11 = new JLabel();
			jLabel11.setBounds(new Rectangle(606, 6, 92, 13));
			jLabel11.setText("Comunicacion");
			jLabel11.setFont(new Font("Arial", Font.PLAIN, 10));
			
			jLabel11.setForeground(Color.DARK_GRAY);
			jPanel3 = new JPanel();
			jPanel3.setLayout(null);
			jPanel3.setBackground(new Color(200,200,210));
			jPanel3.setBounds(new Rectangle(12, 85, 708, 126));
			jPanel3.add(get_txt_contacto(), null);
			jPanel3.add(jLabel10, null);
			jPanel3.add(jLabel7, null);
			jPanel3.add(get_txt_telefono(), null);
			jPanel3.add(jLabel2, null);
			jPanel3.add(jLabel5, null);
			jPanel3.add(get_txt_fax(), null);
			jPanel3.add(jLabel6, null);
			jPanel3.add(get_txt_email(), null);
			jPanel3.add(jLabel11, null);
			jPanel3.add(get_txt_observaciones(), null);
		}
		return jPanel3;
	}

	/**
	 * This method initializes jPanel4	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel4() {
		if (jPanel4 == null) {
			
			jLabel19 = new JLabel();
			jLabel19.setBounds(new Rectangle(4, 43, 125, 16));
			jLabel19.setFont(new Font("Arial", Font.PLAIN, 10));
			jLabel19.setText("Condicion Venta");
			jLabel18 = new JLabel();
			jLabel18.setBounds(new Rectangle(378, 0, 57, 17));
			jLabel18.setFont(new Font("Arial", Font.PLAIN, 10));
			jLabel18.setText("Doc/Cuit");
			jLabel17 = new JLabel();
			jLabel17.setBounds(new Rectangle(213, 0, 69, 18));
			jLabel17.setFont(new Font("Arial", Font.PLAIN, 10));
			jLabel17.setText("Tipo Doc");
			jLabel15 = new JLabel();
			jLabel15.setBounds(new Rectangle(5, 2, 129, 17));
			jLabel15.setFont(new Font("Arial", Font.PLAIN, 10));
			jLabel15.setText("Condicion IVA");
			jLabel14 = new JLabel();
			jLabel14.setBounds(new Rectangle(645, 4, 58, 16));
			jLabel14.setFont(new Font("Arial", Font.PLAIN, 10));
			jLabel14.setText("Legales");
			jPanel4 = new JPanel();
			jPanel4.setLayout(null);
			jPanel4.setBounds(new Rectangle(12, 213, 708, 87));
			jPanel4.add(jLabel14, null);
			jPanel4.add(jLabel15, null);
			jPanel4.add(get_txt_cond_iva(), null);
			jPanel4.add(get_txt_condicion_detalle(), null);
			jPanel4.add(jLabel17, null);
			jPanel4.add(get_txt_tipo_doc(), null);
			jPanel4.add(jLabel18, null);
			jPanel4.add(get_txt_tipo_doc_detalle(), null);
			jPanel4.add(get_txt_cuit(), null);
			jPanel4.add(jLabel19, null);
			jPanel4.add(get_txt_cond_venta(), null);
			jPanel4.add(get_txt_condicion_venta_detalle(), null);
			jPanel4.setBackground(new Color(200,200,210));
		}
		return jPanel4;
	}

	/**
	 * This method initializes jPanel5	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel5() {
		if (jPanel5 == null) {
			jLabel41 = new JLabel();
			jLabel41.setBounds(new Rectangle(22, 299, 150, 15));
			jLabel41.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel41.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel41.setText("Descuento en Factura");
			jLabel37 = new JLabel();
			jLabel37.setBounds(new Rectangle(304, 30, 53, 17));
			jLabel37.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel37.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel37.setText("alicuota");
			jLabel36 = new JLabel();
			jLabel36.setBounds(new Rectangle(304, 11, 53, 16));
			jLabel36.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel36.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel36.setText("alicuota");
			jLabel35 = new JLabel();
			jLabel35.setBounds(new Rectangle(305, 187, 54, 16));
			jLabel35.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel35.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel35.setText("alicuota");
			jLabel34 = new JLabel();
			jLabel34.setBounds(new Rectangle(7, 186, 164, 17));
			jLabel34.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel34.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel34.setText("Requiere Impuestos Internos");
			jLabel33 = new JLabel();
			jLabel33.setBounds(new Rectangle(-17, 277, 190, 17));
			jLabel33.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel33.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel33.setText("Carga Con Remitos de Compra");
			jLabel32 = new JLabel();
			jLabel32.setBounds(new Rectangle(-17, 256, 190, 16));
			jLabel32.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel32.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel32.setText("Actualiza Precios");
			jLabel31 = new JLabel();
			jLabel31.setBounds(new Rectangle(-20, 233, 192, 18));
			jLabel31.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel31.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel31.setText("Imprime Etiquetas");
			jLabel30 = new JLabel();
			jLabel30.setBounds(new Rectangle(-16, 212, 189, 14));
			jLabel30.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel30.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel30.setText("Permite Carga de Articulos");
			jLabel29 = new JLabel();
			jLabel29.setBounds(new Rectangle(33, 164, 137, 18));
			jLabel29.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel29.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel29.setText("Requiere RG 3337");
			jLabel28 = new JLabel();
			jLabel28.setBounds(new Rectangle(12, 142, 158, 18));
			jLabel28.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel28.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel28.setText("Requiere iva 27%");
			jLabel27 = new JLabel();
			jLabel27.setBounds(new Rectangle(30, 119, 141, 19));
			jLabel27.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel27.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel27.setText("Requiere iva 10.5%");
			jLabel26 = new JLabel();
			jLabel26.setBounds(new Rectangle(31, 99, 140, 17));
			jLabel26.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel26.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel26.setText("Requiere iva 21%");
			jLabel25 = new JLabel();
			jLabel25.setBounds(new Rectangle(4, 76, 164, 20));
			jLabel25.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel25.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel25.setText("Requiere Neto No Grabado");
			jLabel24 = new JLabel();
			jLabel24.setBounds(new Rectangle(3, 54, 169, 18));
			jLabel24.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel24.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel24.setText("Requiere Percepcion Ganancias");
			jLabel23 = new JLabel();
			jLabel23.setBounds(new Rectangle(4, 32, 168, 16));
			jLabel23.setText("Requiere Percepcion de IVA");
			jLabel23.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel23.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel16 = new JLabel();
			jLabel16.setBounds(new Rectangle(2, 8, 169, 18));
			jLabel16.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel16.setText("Requiere Ingresos Brutos");
			jLabel16.setFont(new Font("Dialog", Font.BOLD, 10));
			jPanel5 = new JPanel();
			jPanel5.setLayout(null);
			jPanel5.setBounds(new Rectangle(9, 10, 425, 322));
			jPanel5.setBackground(new Color(204, 204, 255));
			jPanel5.add(jLabel16, null);
			jPanel5.add(get_requiere_ingresos_brutos(), null);
			jPanel5.add(jLabel23, null);
			jPanel5.add(get_requiere_percepcion_iva(), null);
			jPanel5.add(jLabel24, null);
			jPanel5.add(jLabel25, null);
			jPanel5.add(get_requiere_percepcion_ganancias(), null);
			jPanel5.add(get_requiere_neto_no_gravado(), null);
			jPanel5.add(jLabel26, null);
			jPanel5.add(jLabel27, null);
			jPanel5.add(jLabel28, null);
			jPanel5.add(jLabel29, null);
			jPanel5.add(get_requiere_iva21(), null);
			jPanel5.add(get_requiere_iva10(), null);
			jPanel5.add(get_requiere_iva27(), null);
			jPanel5.add(get_requiere_rg3337(), null);
			jPanel5.add(jLabel30, null);
			jPanel5.add(jLabel31, null);
			jPanel5.add(jLabel32, null);
			jPanel5.add(get_permite_articulos(), null);
			jPanel5.add(get_imprime_etiquetas(), null);
			jPanel5.add(get_actualiza_precios(), null);
			jPanel5.add(jLabel33, null);
			jPanel5.add(get_requiere_remitos(), null);
			jPanel5.add(jLabel34, null);
			jPanel5.add(get_requiere_impuestos_internos(), null);
			jPanel5.add(get_txt_alicuota_impuesto_interno(), null);
			jPanel5.add(jLabel35, null);
			jPanel5.add(jLabel36, null);
			jPanel5.add(get_txt_alicuota_ingresos_brutos(), null);
			jPanel5.add(get_txt_alicuota_percepcion_iva(), null);
			jPanel5.add(jLabel37, null);
			jPanel5.add(jLabel41, null);
			jPanel5.add(get_txt_Descuento(), null);
		}
		return jPanel5;
	}

	/**
	 * This method initializes jPanel6	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel6() {
		if (jPanel6 == null) {
			jPanel6 = new JPanel();
			jPanel6.setLayout(null);
			jPanel6.add(getJPanel5(), null);
			jPanel6.add(getJPanel7(), null);
			jPanel6.add(getJPanel9(), null);
		}
		return jPanel6;
	}

	/**
	 * This method initializes jPanel7	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel7() {
		if (jPanel7 == null) {
			jLabel1311 = new JLabel();
			jLabel1311.setBounds(new Rectangle(3, 3, 227, 20));
			jLabel1311.setFont(new Font("Arial", Font.ITALIC, 13));
			jLabel1311.setForeground(Color.blue);
			jLabel1311.setHorizontalAlignment(SwingConstants.LEFT);
			jLabel1311.setText("Cuentas de Imputacion");
			jLabel1311.setBackground(Color.black);
			jPanel7 = new JPanel();
			jPanel7.setLayout(null);
			jPanel7.setBounds(new Rectangle(10, 339, 734, 125));
			jPanel7.setBackground(SystemColor.controlShadow);
			jPanel7.add(jLabel1311, null);
			jPanel7.add(getJScrollPane1(), null);
		}
		return jPanel7;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(16, 21, 659, 223));
			jScrollPane.setViewportView(getJTable());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setBounds(new Rectangle(7, 28, 559, 89));
			jScrollPane1.setViewportView(getJTable1());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes jTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.setBounds(new Rectangle(14, 59, 756, 501));
			jTabbedPane.setFont(new Font("Dialog", Font.BOLD, 12));
			jTabbedPane.addTab("General", null, getGeneral(), null);
			jTabbedPane.addTab("Control", null, getJPanel6(), null);
			jTabbedPane.addTab("Saldo", null, getComprobantes(), null);
			
		}
		return jTabbedPane;
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

	/**
	 * This method initializes jTable1	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getJTable1() {
		if (jTable1 == null) {
			jTable1 = new JTable();
		}
		return jTable1;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(781, 611);
		this.setContentPane(getJContentPane());
		this.setResizable(false);
		this.setTitle("Proveedor");
	}

	public void setJTable(JTable table){
		this.jTable=table;
		this.jScrollPane.setViewportView(jTable);
	}

	public void setJTable1(JTable table){
		this.jTable1=table;
		this.jScrollPane1.setViewportView(jTable1);
	}

	/**
	 * This method initializes _txt_Descuento	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_Descuento() {
		if (_txt_Descuento == null) {
			_txt_Descuento = new JTextField();
			_txt_Descuento.setBounds(new Rectangle(181, 299, 118, 17));
		}
		return _txt_Descuento;
	}

	/**
	 * This method initializes _txt_idtransporte	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idtransporte() {
		if (_txt_idtransporte == null) {
			_txt_idtransporte = new JTextField();
			_txt_idtransporte.setBounds(new Rectangle(81, 14, 33, 19));
			_txt_idtransporte.setHorizontalAlignment(JTextField.RIGHT);
			_txt_idtransporte.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_idtransporte;
	}

	/**
	 * This method initializes _txt_transporte_descripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_transporte_descripcion() {
		if (_txt_transporte_descripcion == null) {
			_txt_transporte_descripcion = new JTextField();
			_txt_transporte_descripcion.setBounds(new Rectangle(116, 14, 175, 19));
			_txt_transporte_descripcion.setEditable(false);
		}
		return _txt_transporte_descripcion;
	}

	/**
	 * This method initializes _txt_equivalencia	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_equivalencia() {
		if (_txt_equivalencia == null) {
			_txt_equivalencia = new JTextField();
			_txt_equivalencia.setBounds(new Rectangle(118, 50, 137, 19));
			_txt_equivalencia.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_equivalencia;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
