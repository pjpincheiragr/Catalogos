package aplicacion.compras.carga.control.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import aplicacion.compras.carga.items.logic.DisplayCanvas;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import java.awt.Point;
import javax.swing.JToolBar;
import javax.swing.JProgressBar;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JLabel jLabel2 = null;
	public JTextField _txt_fecha_desde = null;
	public JTextField _txt_fecha_hasta = null;
	private JButton _btn_mostrar = null;
	private JPanel jPanel = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel3 = null;
	private JLabel jLabel4 = null;
	private JLabel jLabel5 = null;
	private JLabel jLabel6 = null;
	private JLabel jLabel7 = null;
	private JLabel jLabel8 = null;
	public JTextField _txt_iva_21 = null;
	public JTextField _txt_iva10 = null;
	public JTextField _txt_iva_27 = null;
	public JTextField _txt_perc_iva = null;
	public JTextField _txt_perc_iibb = null;
	public JTextField _txt_ret_gan = null;
	public JTextField _txt_total = null;
	private JPanel jPanel1 = null;
	private DisplayCanvas canvas=null;
	private JLabel jLabel9 = null;
	private JLabel jLabel10 = null;
	private JLabel jLabel11 = null;
	private JLabel jLabel12 = null;
	private JLabel jLabel13 = null;
	public JTextField _txt_c_fecha = null;
	public JTextField _txt_c_cuenta = null;
	public JTextField _txt_c_descripcion = null;
	public JTextField _txt_c_tc = null;
	public JTextField _txt_c_idcomprobante = null;
	private JLabel jLabel14 = null;
	public JTextField _txt_c_fecha_carga = null;
	private JLabel jLabel15 = null;
	public JTextField _txt_c_fecha_control = null;
	private JPanel jPanel2 = null;
	private JButton _btn_control_ok = null;
	private JButton _btn_control_error = null;
	private JButton _btn_editar_cpte = null;
	private JScrollPane jScrollPane1 = null;
	private JTable jTable1 = null;
	private JLabel jLabel16 = null;
	private JTextField _txt_c_total = null;
	private JScrollPane jScrollPane2 = null;
	private JTextField _txt_idproveedor = null;
	private JTextField _txt_proveedor_descripcion = null;
	private JLabel jLabel17 = null;
	private JButton _btn_cancelar = null;
	private JComboBox _list_mes = null;
	private JButton _btn_cerrar_iva = null;
	private JButton _btn_fecha_desde = null;
	private JButton _btn_fecha_hasta = null;
	private JLabel jLabel20 = null;
	private JTextField _txt_controlados = null;
	private JButton _btn_next = null;
	private JButton _btn_back = null;
	private JButton _btn_contraste_mas = null;
	private JButton _btn_contraste_menos = null;
	private JComboBox _list_anio = null;
	private JButton _btn_unset_all = null;
	private JButton _btn_anterior = null;
	private JButton _btn_siguiente = null;
	private JTextField _txt_hoja_actual = null;
	private JTextField _txt_hoja_total = null;
	private JLabel jLabel18 = null;
	private JLabel jLabel21 = null;
	private JTextField _txt_fecha_carga_desde = null;
	private JTextField _txt_fecha_carga_hasta = null;
	private JButton _btn_fecha_carga_desde = null;
	private JButton _btn_fecha_carga_hasta = null;
	private JScrollPane jScrollPane3 = null;
	private JTable jTable2 = null;
	private JPanel jPanel3 = null;
	private JCheckBox _chk_utiliza_calendario = null;
	private JButton _btn_imprimir = null;
	private JToolBar jToolBar = null;
	private JButton _btn_salir = null;
	private JButton _btn_buscar_proveedor = null;
	private JProgressBar jProgressBar = null;
	private JCheckBox _chk_utiliza_fecha_factura = null;
	private JCheckBox _chk_utiliza_fecha_carga = null;
	private JButton _btn_error = null;
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
		this.setSize(1024, 748);
		this.setContentPane(getJContentPane());
		this.setTitle("Control Libro Iva Compras");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel21 = new JLabel();
			jLabel21.setBounds(new Rectangle(284, 91, 52, 16));
			jLabel21.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel21.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel21.setText("hasta");
			jLabel18 = new JLabel();
			jLabel18.setBounds(new Rectangle(113, 88, 37, 16));
			jLabel18.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel18.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel18.setText("desde");
			jLabel17 = new JLabel();
			jLabel17.setBounds(new Rectangle(82, 112, 67, 14));
			jLabel17.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel17.setText("idproveedor");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(283, 70, 53, 17));
			jLabel2.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2.setText("hasta");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(114, 69, 36, 15));
			jLabel.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setText("desde");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(get_txt_fecha_desde(), null);
			jContentPane.add(get_txt_fecha_hasta(), null);
			jContentPane.add(getJPanel(), null);
			jContentPane.add(getJPanel1(), null);
			jContentPane.add(get_txt_idproveedor(), null);
			jContentPane.add(get_txt_proveedor_descripcion(), null);
			jContentPane.add(jLabel17, null);
			jContentPane.add(get_btn_fecha_desde(), null);
			jContentPane.add(get_btn_fecha_hasta(), null);
			jContentPane.add(jLabel18, null);
			jContentPane.add(jLabel21, null);
			jContentPane.add(get_txt_fecha_carga_desde(), null);
			jContentPane.add(get_txt_fecha_carga_hasta(), null);
			jContentPane.add(get_btn_fecha_carga_desde(), null);
			jContentPane.add(get_btn_fecha_carga_hasta(), null);
			jContentPane.add(getJPanel3(), null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(get_chk_utiliza_calendario(), null);
			jContentPane.add(get_btn_buscar_proveedor(), null);
			jContentPane.add(getJProgressBar(), null);
			jContentPane.add(get_chk_utiliza_fecha_factura(), null);
			jContentPane.add(get_chk_utiliza_fecha_carga(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes _txt_fecha_desde	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_fecha_desde() {
		if (_txt_fecha_desde == null) {
			_txt_fecha_desde = new JTextField();
			_txt_fecha_desde.setBounds(new Rectangle(155, 69, 101, 16));
			
		}
		return _txt_fecha_desde;
	}

	/**
	 * This method initializes _txt_fecha_hasta	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_fecha_hasta() {
		if (_txt_fecha_hasta == null) {
			_txt_fecha_hasta = new JTextField();
			_txt_fecha_hasta.setBounds(new Rectangle(339, 70, 100, 16));
			
		}
		return _txt_fecha_hasta;
	}

	/**
	 * This method initializes _btn_mostrar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_mostrar() {
		if (_btn_mostrar == null) {
			_btn_mostrar = new JButton();
			_btn_mostrar.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-refresh.png")));
		}
		return _btn_mostrar;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabel20 = new JLabel();
			jLabel20.setBounds(new Rectangle(180, 176, 76, 17));
			jLabel20.setForeground(Color.lightGray);
			jLabel20.setText("Controlado %");
			jLabel8 = new JLabel();
			jLabel8.setBounds(new Rectangle(899, 177, 46, 17));
			jLabel8.setForeground(Color.lightGray);
			jLabel8.setText("Total");
			jLabel7 = new JLabel();
			jLabel7.setBounds(new Rectangle(787, 176, 50, 19));
			jLabel7.setForeground(Color.lightGray);
			jLabel7.setText("Ret Gan");
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(716, 175, 56, 18));
			jLabel6.setForeground(Color.lightGray);
			jLabel6.setText("Perc IIBB");
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(645, 176, 53, 17));
			jLabel5.setForeground(Color.lightGray);
			jLabel5.setText("Per Iva");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(573, 175, 62, 17));
			jLabel4.setForeground(Color.lightGray);
			jLabel4.setText("iva 27%");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(501, 175, 58, 16));
			jLabel3.setForeground(Color.lightGray);
			jLabel3.setText("iva 10.5%");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(429, 177, 66, 15));
			jLabel1.setForeground(Color.lightGray);
			jLabel1.setText("Iva 21%");
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBounds(new Rectangle(9, 188, 994, 217));
			jPanel.setBackground(Color.darkGray);
			jPanel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jPanel.setForeground(Color.white);
			jPanel.add(getJScrollPane(), null);
			jPanel.add(jLabel1, null);
			jPanel.add(jLabel3, null);
			jPanel.add(jLabel4, null);
			jPanel.add(jLabel5, null);
			jPanel.add(jLabel6, null);
			jPanel.add(jLabel7, null);
			jPanel.add(jLabel8, null);
			jPanel.add(get_txt_iva_21(), null);
			jPanel.add(get_txt_iva10(), null);
			jPanel.add(get_txt_iva_27(), null);
			jPanel.add(get_txt_perc_iva(), null);
			jPanel.add(get_txt_perc_iibb(), null);
			jPanel.add(get_txt_ret_gan(), null);
			jPanel.add(get_txt_total(), null);
			jPanel.add(jLabel20, null);
			jPanel.add(get_txt_controlados(), null);
			jPanel.add(get_btn_unset_all(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(16, 10, 928, 160));
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
		this.jScrollPane.setViewportView(table);
	}

	public void setJTable1(JTable table){
		this.jTable1=table;
		this.jScrollPane1.setViewportView(table);
	}
	/**
	 * This method initializes _txt_iva_21	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_iva_21() {
		if (_txt_iva_21 == null) {
			_txt_iva_21 = new JTextField();
			_txt_iva_21.setBounds(new Rectangle(428, 194, 70, 18));
			_txt_iva_21.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_iva_21.setBackground(Color.black);
			_txt_iva_21.setForeground(Color.white);
			_txt_iva_21.setHorizontalAlignment(JTextField.RIGHT);
			_txt_iva_21.setEditable(false);
		}
		return _txt_iva_21;
	}

	/**
	 * This method initializes _txt_iva10	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_iva10() {
		if (_txt_iva10 == null) {
			_txt_iva10 = new JTextField();
			_txt_iva10.setBounds(new Rectangle(500, 194, 70, 18));
			_txt_iva10.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_iva10.setBackground(Color.black);
			_txt_iva10.setForeground(Color.white);
			_txt_iva10.setHorizontalAlignment(JTextField.RIGHT);
			_txt_iva10.setEditable(false);
		}
		return _txt_iva10;
	}

	/**
	 * This method initializes _txt_iva_27	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_iva_27() {
		if (_txt_iva_27 == null) {
			_txt_iva_27 = new JTextField();
			_txt_iva_27.setBounds(new Rectangle(572, 194, 70, 18));
			_txt_iva_27.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_iva_27.setBackground(Color.black);
			_txt_iva_27.setForeground(Color.white);
			_txt_iva_27.setHorizontalAlignment(JTextField.RIGHT);
			_txt_iva_27.setEditable(false);
		}
		return _txt_iva_27;
	}

	/**
	 * This method initializes _txt_perc_iva	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_perc_iva() {
		if (_txt_perc_iva == null) {
			_txt_perc_iva = new JTextField();
			_txt_perc_iva.setBounds(new Rectangle(643, 194, 70, 18));
			_txt_perc_iva.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_perc_iva.setBackground(Color.black);
			_txt_perc_iva.setForeground(Color.white);
			_txt_perc_iva.setHorizontalAlignment(JTextField.RIGHT);
			_txt_perc_iva.setEditable(false);
		}
		return _txt_perc_iva;
	}

	/**
	 * This method initializes _txt_perc_iibb	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_perc_iibb() {
		if (_txt_perc_iibb == null) {
			_txt_perc_iibb = new JTextField();
			_txt_perc_iibb.setBounds(new Rectangle(715, 194, 70, 18));
			_txt_perc_iibb.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_perc_iibb.setBackground(Color.black);
			_txt_perc_iibb.setForeground(Color.white);
			_txt_perc_iibb.setHorizontalAlignment(JTextField.RIGHT);
			_txt_perc_iibb.setEditable(false);
		}
		return _txt_perc_iibb;
	}

	/**
	 * This method initializes _txt_ret_gan	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_ret_gan() {
		if (_txt_ret_gan == null) {
			_txt_ret_gan = new JTextField();
			_txt_ret_gan.setBounds(new Rectangle(786, 194, 70, 18));
			_txt_ret_gan.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_ret_gan.setBackground(Color.black);
			_txt_ret_gan.setForeground(Color.white);
			_txt_ret_gan.setHorizontalAlignment(JTextField.RIGHT);
			_txt_ret_gan.setEditable(false);
		}
		return _txt_ret_gan;
	}

	/**
	 * This method initializes _txt_total	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_total() {
		if (_txt_total == null) {
			_txt_total = new JTextField();
			_txt_total.setBounds(new Rectangle(899, 195, 70, 18));
			_txt_total.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_total.setBackground(Color.black);
			_txt_total.setForeground(Color.white);
			_txt_total.setHorizontalAlignment(JTextField.RIGHT);
			_txt_total.setEditable(false);
		}
		return _txt_total;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jLabel16 = new JLabel();
			jLabel16.setBounds(new Rectangle(404, 291, 48, 15));
			jLabel16.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel16.setText("Total");
			jLabel15 = new JLabel();
			jLabel15.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel15.setLocation(new Point(808, 4));
			jLabel15.setSize(new Dimension(108, 18));
			jLabel15.setText("Fecha de Control");
			jLabel14 = new JLabel();
			jLabel14.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel14.setLocation(new Point(690, 4));
			jLabel14.setSize(new Dimension(101, 18));
			jLabel14.setText("Fecha de carga");
			jLabel13 = new JLabel();
			jLabel13.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel13.setLocation(new Point(188, 4));
			jLabel13.setSize(new Dimension(116, 18));
			jLabel13.setText("descripcion");
			jLabel12 = new JLabel();
			jLabel12.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel12.setLocation(new Point(88, 4));
			jLabel12.setSize(new Dimension(92, 18));
			jLabel12.setText("cuenta");
			jLabel11 = new JLabel();
			jLabel11.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel11.setLocation(new Point(556, 4));
			jLabel11.setSize(new Dimension(128, 18));
			jLabel11.setText("idComprobante");
			jLabel10 = new JLabel();
			jLabel10.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel10.setLocation(new Point(512, 4));
			jLabel10.setSize(new Dimension(34, 18));
			jLabel10.setText("TC");
			jLabel9 = new JLabel();
			jLabel9.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel9.setLocation(new Point(13, 4));
			jLabel9.setSize(new Dimension(66, 18));
			jLabel9.setText("Fecha");
			jPanel1 = new JPanel();
			jPanel1.setLayout(null);
			jPanel1.setBounds(new Rectangle(9, 407, 1002, 310));
			jPanel1.setBackground(Color.lightGray);
			jPanel1.add(jLabel9, null);
			jPanel1.add(jLabel10, null);
			jPanel1.add(jLabel11, null);
			jPanel1.add(jLabel12, null);
			jPanel1.add(jLabel13, null);
			jPanel1.add(get_txt_c_fecha(), null);
			jPanel1.add(get_txt_c_cuenta(), null);
			jPanel1.add(get_txt_c_descripcion(), null);
			jPanel1.add(get_txt_c_tc(), null);
			jPanel1.add(get_txt_c_idcomprobante(), null);
			jPanel1.add(jLabel14, null);
			jPanel1.add(get_txt_c_fecha_carga(), null);
			jPanel1.add(jLabel15, null);
			jPanel1.add(get_txt_c_fecha_control(), null);
			//jPanel1.add(getJPanel2(), null);
			//jPanel1.add(this.getCanvas(),null);
			jPanel1.add(get_btn_control_ok(), null);
			jPanel1.add(get_btn_control_error(), null);
			jPanel1.add(get_btn_editar_cpte(), null);
			jPanel1.add(getJScrollPane1(), null);
			jPanel1.add(jLabel16, null);
			jPanel1.add(get_txt_c_total(), null);
			jPanel1.add(getJScrollPane2(), null);
			jPanel1.add(get_btn_next(), null);
			jPanel1.add(get_btn_back(), null);
			jPanel1.add(get_btn_contraste_menos(), null);
			jPanel1.add(get_btn_anterior(), null);
			jPanel1.add(get_btn_siguiente(), null);
			jPanel1.add(get_btn_contraste_mas(), null);
			jPanel1.add(get_txt_hoja_actual(), null);
			jPanel1.add(get_txt_hoja_total(), null);
			jPanel1.add(get_btn_imprimir(), null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes _txt_c_fecha	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_c_fecha() {
		if (_txt_c_fecha == null) {
			_txt_c_fecha = new JTextField();
			_txt_c_fecha.setBackground(Color.black);
			_txt_c_fecha.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_c_fecha.setForeground(Color.white);
			_txt_c_fecha.setSize(new Dimension(66, 18));
			_txt_c_fecha.setLocation(new Point(14, 22));
			_txt_c_fecha.setEditable(false);
		}
		return _txt_c_fecha;
	}

	/**
	 * This method initializes _txt_c_cuenta	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_c_cuenta() {
		if (_txt_c_cuenta == null) {
			_txt_c_cuenta = new JTextField();
			_txt_c_cuenta.setBackground(Color.black);
			_txt_c_cuenta.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_c_cuenta.setForeground(Color.white);
			_txt_c_cuenta.setLocation(new Point(91, 22));
			_txt_c_cuenta.setSize(new Dimension(89, 18));
			_txt_c_cuenta.setEditable(false);
		}
		return _txt_c_cuenta;
	}

	/**
	 * This method initializes _txt_c_descripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_c_descripcion() {
		if (_txt_c_descripcion == null) {
			_txt_c_descripcion = new JTextField();
			_txt_c_descripcion.setBackground(Color.black);
			_txt_c_descripcion.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_c_descripcion.setForeground(Color.white);
			_txt_c_descripcion.setHorizontalAlignment(JTextField.LEFT);
			_txt_c_descripcion.setLocation(new Point(188, 22));
			_txt_c_descripcion.setSize(new Dimension(320, 18));
			_txt_c_descripcion.setEditable(false);
		}
		return _txt_c_descripcion;
	}

	/**
	 * This method initializes _txt_c_tc	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_c_tc() {
		if (_txt_c_tc == null) {
			_txt_c_tc = new JTextField();
			_txt_c_tc.setBackground(Color.black);
			_txt_c_tc.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_c_tc.setForeground(Color.white);
			_txt_c_tc.setSize(new Dimension(39, 19));
			_txt_c_tc.setLocation(new Point(513, 22));
			_txt_c_tc.setEditable(false);
		}
		return _txt_c_tc;
	}

	/**
	 * This method initializes _txt_c_idcomprobante	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_c_idcomprobante() {
		if (_txt_c_idcomprobante == null) {
			_txt_c_idcomprobante = new JTextField();
			_txt_c_idcomprobante.setBackground(Color.black);
			_txt_c_idcomprobante.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_c_idcomprobante.setForeground(Color.white);
			_txt_c_idcomprobante.setSize(new Dimension(127, 18));
			_txt_c_idcomprobante.setLocation(new Point(557, 22));
			_txt_c_idcomprobante.setEditable(false);
		}
		return _txt_c_idcomprobante;
	}

	/**
	 * This method initializes _txt_c_fecha_carga	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_c_fecha_carga() {
		if (_txt_c_fecha_carga == null) {
			_txt_c_fecha_carga = new JTextField();
			_txt_c_fecha_carga.setBackground(Color.black);
			_txt_c_fecha_carga.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_c_fecha_carga.setForeground(Color.white);
			_txt_c_fecha_carga.setLocation(new Point(689, 22));
			_txt_c_fecha_carga.setSize(new Dimension(115, 18));
			_txt_c_fecha_carga.setEditable(false);
		}
		return _txt_c_fecha_carga;
	}

	/**
	 * This method initializes _txt_c_fecha_control	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_c_fecha_control() {
		if (_txt_c_fecha_control == null) {
			_txt_c_fecha_control = new JTextField();
			_txt_c_fecha_control.setBackground(Color.black);
			_txt_c_fecha_control.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_c_fecha_control.setForeground(Color.white);
			_txt_c_fecha_control.setSize(new Dimension(107, 18));
			_txt_c_fecha_control.setLocation(new Point(810, 22));
			_txt_c_fecha_control.setEditable(false);
		}
		return _txt_c_fecha_control;
	}

	public DisplayCanvas getCanvas(){
		if (canvas==null){
			canvas = new DisplayCanvas();
			//canvas.setBounds(new Rectangle(5, 25, 886, 152));
			canvas.setBounds(new Rectangle(15, 84, 801, 127));
		}
		
		return canvas;
	
	}

	/**
	 * This method initializes jPanel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setLayout(new GridBagLayout());
			jPanel2.setBounds(new Rectangle(16, 76, 694, 160));
		}
		return jPanel2;
	}

	/**
	 * This method initializes _btn_control_ok	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_control_ok() {
		if (_btn_control_ok == null) {
			_btn_control_ok = new JButton();
			_btn_control_ok.setBounds(new Rectangle(927, 156, 23, 22));
			_btn_control_ok.setToolTipText("marcar como controlado");
			_btn_control_ok.setIcon(new ImageIcon(getClass().getResource("/icons/emblem-default-16.png")));
		}
		return _btn_control_ok;
	}

	/**
	 * This method initializes _btn_control_error	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_control_error() {
		if (_btn_control_error == null) {
			_btn_control_error = new JButton();
			_btn_control_error.setBounds(new Rectangle(956, 156, 23, 23));
			_btn_control_error.setToolTipText("anular marca de control");
			_btn_control_error.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-stop.png")));
		}
		return _btn_control_error;
	}

	/**
	 * This method initializes _btn_editar_cpte	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_editar_cpte() {
		if (_btn_editar_cpte == null) {
			_btn_editar_cpte = new JButton();
			_btn_editar_cpte.setBounds(new Rectangle(927, 126, 23, 23));
			_btn_editar_cpte.setToolTipText("editar comprobante");
			_btn_editar_cpte.setIcon(new ImageIcon(getClass().getResource("/icons/edit-find-replace.png")));
		}
		return _btn_editar_cpte;
	}

	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setBounds(new Rectangle(16, 164, 563, 123));
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
		}
		return jTable1;
	}

	/**
	 * This method initializes _txt_c_total	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_c_total() {
		if (_txt_c_total == null) {
			_txt_c_total = new JTextField();
			_txt_c_total.setBounds(new Rectangle(462, 288, 115, 19));
			_txt_c_total.setHorizontalAlignment(JTextField.RIGHT);
			_txt_c_total.setBackground(Color.black);
			_txt_c_total.setForeground(Color.white);
			_txt_c_total.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_c_total.setEditable(false);
		}
		return _txt_c_total;
	}

	/**
	 * This method initializes jScrollPane2	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	public JScrollPane getJScrollPane2() {
		if (jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setBounds(new Rectangle(15, 42, 900, 119));
		}
		return jScrollPane2;
	}

	/**
	 * This method initializes _txt_idproveedor	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idproveedor() {
		if (_txt_idproveedor == null) {
			_txt_idproveedor = new JTextField();
			_txt_idproveedor.setBounds(new Rectangle(153, 111, 101, 16));
		}
		return _txt_idproveedor;
	}

	/**
	 * This method initializes _txt_proveedor_descripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_proveedor_descripcion() {
		if (_txt_proveedor_descripcion == null) {
			_txt_proveedor_descripcion = new JTextField();
			_txt_proveedor_descripcion.setBounds(new Rectangle(281, 110, 291, 18));
			_txt_proveedor_descripcion.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_proveedor_descripcion;
	}

	/**
	 * This method initializes _btn_cancelar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cancelar() {
		if (_btn_cancelar == null) {
			_btn_cancelar = new JButton();
			_btn_cancelar.setToolTipText("cancelar control");
			_btn_cancelar.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-stop.png")));
		}
		return _btn_cancelar;
	}

	/**
	 * This method initializes _list_mes	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_list_mes() {
		if (_list_mes == null) {
			_list_mes = new JComboBox();
			_list_mes.addItem("Enero");
			_list_mes.addItem("Febrero");
			_list_mes.addItem("Marzo");
			_list_mes.addItem("Abril");
			_list_mes.addItem("Mayo");
			_list_mes.addItem("Junio");
			_list_mes.addItem("Julio");
			_list_mes.addItem("Agosto");
			_list_mes.addItem("Septiembre");
			_list_mes.addItem("Octubre");
			_list_mes.addItem("Noviembre");
			_list_mes.addItem("Diciembre");
			_list_mes.setFont(new Font("Dialog", Font.BOLD, 10));
			_list_mes.setBounds(new Rectangle(3, 5, 127, 14));
		}
		return _list_mes;
	}

	/**
	 * This method initializes _btn_cerrar_iva	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cerrar_iva() {
		if (_btn_cerrar_iva == null) {
			_btn_cerrar_iva = new JButton();
			_btn_cerrar_iva.setToolTipText("Cerrar Libro de Iva Compras del Mes Seleccionado");
			_btn_cerrar_iva.setEnabled(false);
			_btn_cerrar_iva.setIcon(new ImageIcon(getClass().getResource("/icons/lock.png")));
		}
		return _btn_cerrar_iva;
	}

	/**
	 * This method initializes _btn_fecha_desde	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_fecha_desde() {
		if (_btn_fecha_desde == null) {
			_btn_fecha_desde = new JButton();
			_btn_fecha_desde.setBounds(new Rectangle(256, 71, 23, 16));
			_btn_fecha_desde.setIcon(new ImageIcon(getClass().getResource("/icons/calendar.gif")));
		}
		return _btn_fecha_desde;
	}

	/**
	 * This method initializes _btn_fecha_hasta	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_fecha_hasta() {
		if (_btn_fecha_hasta == null) {
			_btn_fecha_hasta = new JButton();
			_btn_fecha_hasta.setBounds(new Rectangle(441, 69, 26, 17));
			_btn_fecha_hasta.setIcon(new ImageIcon(getClass().getResource("/icons/calendar.gif")));
		}
		return _btn_fecha_hasta;
	}

	/**
	 * This method initializes _txt_controlados	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_controlados() {
		if (_txt_controlados == null) {
			_txt_controlados = new JTextField();
			_txt_controlados.setBounds(new Rectangle(180, 195, 76, 15));
			_txt_controlados.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_controlados.setBackground(Color.black);
			_txt_controlados.setForeground(Color.white);
			_txt_controlados.setEditable(false);
		}
		return _txt_controlados;
	}


	/**
	 * This method initializes _btn_next	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_next() {
		if (_btn_next == null) {
			_btn_next = new JButton();
			_btn_next.setBounds(new Rectangle(956, 185, 23, 23));
			_btn_next.setToolTipText("proximo comprobante");
			_btn_next.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-go-back-rtl.png")));
		}
		return _btn_next;
	}


	/**
	 * This method initializes _btn_back	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_back() {
		if (_btn_back == null) {
			_btn_back = new JButton();
			_btn_back.setBounds(new Rectangle(927, 185, 22, 23));
			_btn_back.setToolTipText("comprobante anterior");
			_btn_back.setIcon(new ImageIcon(getClass().getResource("/icons/back.png")));
		}
		return _btn_back;
	}


	/**
	 * This method initializes _btn_contraste_mas	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_contraste_mas() {
		if (_btn_contraste_mas == null) {
			_btn_contraste_mas = new JButton();
			_btn_contraste_mas.setIcon(new ImageIcon(getClass().getResource("/icons/add.png")));
			_btn_contraste_mas.setToolTipText("subir contraste");
			_btn_contraste_mas.setBounds(new Rectangle(927, 64, 22, 24));
		}
		return _btn_contraste_mas;
	}


	/**
	 * This method initializes _btn_contraste_menos	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_contraste_menos() {
		if (_btn_contraste_menos == null) {
			_btn_contraste_menos = new JButton();
			_btn_contraste_menos.setBounds(new Rectangle(927, 94, 23, 25));
			_btn_contraste_menos.setToolTipText("bajar contraste");
			_btn_contraste_menos.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-remove.png")));
		}
		return _btn_contraste_menos;
	}


	/**
	 * This method initializes _list_anio	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_list_anio() {
		if (_list_anio == null) {
			_list_anio = new JComboBox();
			_list_anio.setFont(new Font("Dialog", Font.BOLD, 10));
			_list_anio.setBounds(new Rectangle(131, 4, 91, 15));
		}
		return _list_anio;
	}


	/**
	 * This method initializes _btn_unset_all	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_unset_all() {
		if (_btn_unset_all == null) {
			_btn_unset_all = new JButton();
			_btn_unset_all.setBounds(new Rectangle(951, 16, 20, 22));
			_btn_unset_all.setIcon(new ImageIcon(getClass().getResource("/icons/mail-mark-not-junk.png")));
			_btn_unset_all.setToolTipText("marcar todos los comprobantes como NO controlados");
		}
		return _btn_unset_all;
	}


	/**
	 * This method initializes _btn_anterior	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_anterior() {
		if (_btn_anterior == null) {
			_btn_anterior = new JButton();
			_btn_anterior.setBounds(new Rectangle(920, 38, 15, 20));
			_btn_anterior.setToolTipText("hoja anterior");
			_btn_anterior.setIcon(new ImageIcon(getClass().getResource("/icons/go-previous.png")));
		}
		return _btn_anterior;
	}


	/**
	 * This method initializes _btn_siguiente	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_siguiente() {
		if (_btn_siguiente == null) {
			_btn_siguiente = new JButton();
			_btn_siguiente.setBounds(new Rectangle(984, 37, 16, 19));
			_btn_siguiente.setToolTipText("siguiente hoja");
			_btn_siguiente.setIcon(new ImageIcon(getClass().getResource("/icons/forward.png")));
		}
		return _btn_siguiente;
	}


	/**
	 * This method initializes _txt_hoja_actual	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_hoja_actual() {
		if (_txt_hoja_actual == null) {
			_txt_hoja_actual = new JTextField();
			_txt_hoja_actual.setBounds(new Rectangle(936, 38, 19, 19));
		}
		return _txt_hoja_actual;
	}


	/**
	 * This method initializes _txt_hoja_total	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_hoja_total() {
		if (_txt_hoja_total == null) {
			_txt_hoja_total = new JTextField();
			_txt_hoja_total.setBounds(new Rectangle(958, 37, 24, 20));
		}
		return _txt_hoja_total;
	}


	/**
	 * This method initializes _txt_fecha_carga_desde	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_fecha_carga_desde() {
		if (_txt_fecha_carga_desde == null) {
			_txt_fecha_carga_desde = new JTextField();
			_txt_fecha_carga_desde.setBounds(new Rectangle(155, 89, 100, 16));
		}
		return _txt_fecha_carga_desde;
	}


	/**
	 * This method initializes _txt_fecha_carga_hasta	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_fecha_carga_hasta() {
		if (_txt_fecha_carga_hasta == null) {
			_txt_fecha_carga_hasta = new JTextField();
			_txt_fecha_carga_hasta.setBounds(new Rectangle(340, 92, 100, 16));
		}
		return _txt_fecha_carga_hasta;
	}


	/**
	 * This method initializes _btn_fecha_carga_desde	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_fecha_carga_desde() {
		if (_btn_fecha_carga_desde == null) {
			_btn_fecha_carga_desde = new JButton();
			_btn_fecha_carga_desde.setBounds(new Rectangle(257, 91, 22, 14));
			_btn_fecha_carga_desde.setIcon(new ImageIcon(getClass().getResource("/icons/calendar.gif")));
		}
		return _btn_fecha_carga_desde;
	}


	/**
	 * This method initializes _btn_fecha_carga_hasta	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_fecha_carga_hasta() {
		if (_btn_fecha_carga_hasta == null) {
			_btn_fecha_carga_hasta = new JButton();
			_btn_fecha_carga_hasta.setBounds(new Rectangle(442, 92, 25, 14));
			_btn_fecha_carga_hasta.setIcon(new ImageIcon(getClass().getResource("/icons/calendar.gif")));
		}
		return _btn_fecha_carga_hasta;
	}


	/**
	 * This method initializes jScrollPane3	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane3() {
		if (jScrollPane3 == null) {
			jScrollPane3 = new JScrollPane();
			jScrollPane3.setBounds(new Rectangle(4, 23, 218, 135));
			jScrollPane3.setViewportView(getJTable2());
		}
		return jScrollPane3;
	}


	/**
	 * This method initializes jTable2	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getJTable2() {
		if (jTable2 == null) {
			jTable2 = new JTable();
		}
		return jTable2;
	}
	
	public void setJTable2(JTable table){
		this.jTable2=table;
		this.jScrollPane3.setViewportView(jTable2);
	}


	/**
	 * This method initializes jPanel3	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jPanel3 = new JPanel();
			jPanel3.setLayout(null);
			jPanel3.setBounds(new Rectangle(774, 27, 228, 160));
			jPanel3.setBackground(Color.gray);
			jPanel3.add(getJScrollPane3(), null);
			jPanel3.add(get_list_anio(), null);
			jPanel3.add(get_list_mes(), null);
		}
		return jPanel3;
	}


	/**
	 * This method initializes _chk_utiliza_calendario	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_utiliza_calendario() {
		if (_chk_utiliza_calendario == null) {
			_chk_utiliza_calendario = new JCheckBox();
			_chk_utiliza_calendario.setFont(new Font("Dialog", Font.BOLD, 10));
			_chk_utiliza_calendario.setSelected(true);
			_chk_utiliza_calendario.setBounds(new Rectangle(645, 56, 126, 13));
			_chk_utiliza_calendario.setText("utiliza calendario");
		}
		return _chk_utiliza_calendario;
	}


	/**
	 * This method initializes _btn_imprimir	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_imprimir() {
		if (_btn_imprimir == null) {
			_btn_imprimir = new JButton();
			_btn_imprimir.setBounds(new Rectangle(955, 128, 21, 22));
			_btn_imprimir.setIcon(new ImageIcon(getClass().getResource("/icons/document-print.png")));
		}
		return _btn_imprimir;
	}


	/**
	 * This method initializes jToolBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar() {
		if (jToolBar == null) {
			jToolBar = new JToolBar();
			jToolBar.setBounds(new Rectangle(1, 3, 1006, 22));
			jToolBar.setFloatable(false);
			jToolBar.add(get_btn_mostrar());
			jToolBar.add(get_btn_cancelar());
			jToolBar.add(get_btn_cerrar_iva());
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
			_btn_salir.setIcon(new ImageIcon(getClass().getResource("/icons/exit.png")));
		}
		return _btn_salir;
	}


	/**
	 * This method initializes _btn_buscar_proveedor	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_proveedor() {
		if (_btn_buscar_proveedor == null) {
			_btn_buscar_proveedor = new JButton();
			_btn_buscar_proveedor.setBounds(new Rectangle(256, 110, 21, 16));
			_btn_buscar_proveedor.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-searchtool.png")));
		}
		return _btn_buscar_proveedor;
	}


	/**
	 * This method initializes jProgressBar	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	public JProgressBar getJProgressBar() {
		if (jProgressBar == null) {
			jProgressBar = new JProgressBar();
			jProgressBar.setBounds(new Rectangle(1, 30, 770, 18));
		}
		return jProgressBar;
	}


	/**
	 * This method initializes _chk_utiliza_fecha_factura	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_utiliza_fecha_factura() {
		if (_chk_utiliza_fecha_factura == null) {
			_chk_utiliza_fecha_factura = new JCheckBox();
			_chk_utiliza_fecha_factura.setBounds(new Rectangle(5, 69, 103, 14));
			_chk_utiliza_fecha_factura.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_utiliza_fecha_factura.setSelected(true);
			_chk_utiliza_fecha_factura.setText("Fecha Factura");
		}
		return _chk_utiliza_fecha_factura;
	}


	/**
	 * This method initializes _chk_utiliza_fecha_carga	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_utiliza_fecha_carga() {
		if (_chk_utiliza_fecha_carga == null) {
			_chk_utiliza_fecha_carga = new JCheckBox();
			_chk_utiliza_fecha_carga.setBounds(new Rectangle(5, 89, 99, 15));
			_chk_utiliza_fecha_carga.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_utiliza_fecha_carga.setSelected(true);
			_chk_utiliza_fecha_carga.setText("Fecha Carga");
		}
		return _chk_utiliza_fecha_carga;
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
	
}  //  @jve:decl-index=0:visual-constraint="10,10"
