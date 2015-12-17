package aplicacion.gestion.canje.gui;


import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.KeyStroke;
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumnModel;


import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import javax.swing.JToolBar;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel jContentPane = null;

	private JPanel jPanel = null;

	private JLabel jLabel1 = null;

	private JTextField _txt_idPago = null;

	private JTextField _txt_fecha = null;

	private JPanel jPanel2 = null;
	private JScrollPane jScrollPaneMedios = null;
	
	private JTable jTableMedios = null;
	private JTextField _txt_total_egreso = null;
	private JLabel jLabel3 = null;
	private JTable jTableCpte = null;
	private JPanel jPanel3 = null;
	private JButton _btn_grabar = null;
	private JTable jTableOPG = null;
	private JTextField _txt_leyenda = null;
	private JLabel jLabel8 = null;

	private JButton _btn_cancelar = null;

	private JButton _btn_anular = null;

	private JButton _btn_fecha = null;
	private JButton _btn_salir = null;

	private JTextField _txt_estado = null;

	private JButton _btn_buscar_pago = null;

	private JToolBar jToolBar = null;

	private JLabel jLabel6 = null;

	private JLabel jLabel7 = null;

	private JTextField _txt_concepto = null;

	private JComboBox _list_cajas = null;

	private JTextField _txt_caja_detalle = null;

	private JLabel jLabel = null;

	private JTextField _txt_total_ingreso = null;

	private JTextField _txt_total_diferencia = null;

	private JLabel jLabel2 = null;

	private JPanel jPanel1 = null;

	private JScrollPane jScrollPane = null;

	private JTable jTableMedios2 = null;

	private JLabel jLabel4 = null;

	private JLabel jLabel5 = null;

	private JTextField _txt_cotizacion_dolar = null;

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
		this.setSize(783, 597);
		this.setContentPane(getJContentPane());
		this.setTitle("Canje de Valores/Efectivo");
	}
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("Total Egreso");
			jLabel3.setSize(new Dimension(98, 19));
			jLabel3.setLocation(new Point(523, 13));
			jLabel3.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJPanel(), null);
			jContentPane.add(getJPanel3(), null);
			jContentPane.add(getJPanel2(), null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(getJPanel1(), null);
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
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(5, 55, 92, 18));
			jLabel5.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel5.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel5.setText("Dolar");
			jLabel7 = new JLabel();
			jLabel7.setBounds(new Rectangle(15, 83, 83, 19));
			jLabel7.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel7.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel7.setText("Concepto");
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(22, 29, 74, 18));
			jLabel6.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel6.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel6.setText("Caja");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(9, 4, 89, 17));
			jLabel1.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel1.setText("Comprobante");
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBounds(new Rectangle(4, 33, 756, 112));
			jPanel.setBackground(Color.LIGHT_GRAY);
			jPanel.add(jLabel1, null);
			jPanel.add(get_txt_idPago(), null);
			jPanel.add(get_txt_fecha(), null);
			jPanel.add(get_btn_fecha(), null);
			jPanel.add(get_txt_estado(), null);
			jPanel.add(get_btn_buscar_pago(), null);
			jPanel.add(jLabel6, null);
			jPanel.add(jLabel7, null);
			jPanel.add(get_txt_concepto(), null);
			jPanel.add(get_list_cajas(), null);
			jPanel.add(get_txt_caja_detalle(), null);
			jPanel.add(jLabel5, null);
			jPanel.add(get_txt_cotizacion_dolar(), null);
			
		}
		return jPanel;
	}

	/**
	 * This method initializes _txt_idPago	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idPago() {
		if (_txt_idPago == null) {
			_txt_idPago = new JTextField();
			_txt_idPago.setBounds(new Rectangle(100, 4, 109, 18));
		}
		return _txt_idPago;
	}

	/**
	 * This method initializes _txt_fecha	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_fecha() {
		if (_txt_fecha == null) {
			_txt_fecha = new JTextField();
			_txt_fecha.setHorizontalAlignment(JTextField.RIGHT);
			_txt_fecha.setBounds(new Rectangle(601, 5, 121, 18));
		}
		return _txt_fecha;
	}

	
	/**
	 * This method initializes jPanel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jLabel8 = new JLabel();
			jLabel8.setBounds(new Rectangle(550, 3, 188, 18));
			jLabel8.setText("Egreso");
			jLabel8.setBackground(Color.black);
			jLabel8.setForeground(Color.blue);
			jLabel8.setFont(new Font("Arial", Font.ITALIC, 14));
			jLabel8.setHorizontalAlignment(JTextField.RIGHT);
			jPanel2 = new JPanel();
			jPanel2.setLayout(null);
			jPanel2.setBackground(Color.LIGHT_GRAY);
			jPanel2.setBounds(new Rectangle(5, 151, 753, 129));
			jPanel2.add(getJScrollPaneMedios(), null);
			jPanel2.add(jLabel8, null);
		}
		return jPanel2;
	}

	/**
	 * This method initializes jScrollPaneMedios	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPaneMedios() {
		if (jScrollPaneMedios == null) {
			jScrollPaneMedios = new JScrollPane();
			jScrollPaneMedios.setBounds(new Rectangle(12, 22, 728, 99));
			jScrollPaneMedios.setViewportView(getJTableMedios());
		}
		return jScrollPaneMedios;
	}

	/**
	 * This method initializes jTableMedios	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getJTableMedios() {
		if (jTableMedios == null) {
			jTableMedios = new JTable();
		}
		return jTableMedios;
	}
	
	public void setJTableMedios(JTable table){
		this.jTableMedios=table;
		this.jScrollPaneMedios.setViewportView(this.jTableMedios);
	}
	/**
	 * This method initializes _txt_total_egreso	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_total_egreso() {
		if (_txt_total_egreso == null) {
			_txt_total_egreso = new JTextField();
			_txt_total_egreso.setEditable(false);
			_txt_total_egreso.setHorizontalAlignment(JTextField.RIGHT);
			_txt_total_egreso.setLocation(new Point(630, 13));
			_txt_total_egreso.setSize(new Dimension(120, 18));
			_txt_total_egreso.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_total_egreso.setText("0.00");
		}
		return _txt_total_egreso;
	}
	

	/**
	 * This method initializes jPanel3	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jLabel2 = new JLabel();
			jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2.setSize(new Dimension(93, 16));
			jLabel2.setLocation(new Point(525, 63));
			jLabel2.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel2.setText("diferencia");
			jLabel = new JLabel();
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setSize(new Dimension(98, 18));
			jLabel.setLocation(new Point(522, 38));
			jLabel.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel.setText("Total Ingreso");
			jPanel3 = new JPanel();
			jPanel3.setLayout(null);
			jPanel3.setBackground(Color.LIGHT_GRAY);
			jPanel3.setBounds(new Rectangle(6, 433, 753, 108));
			jPanel3.add(jLabel3, null);
			jPanel3.add(get_txt_total_egreso(), null);
			jPanel3.add(get_txt_leyenda(), null);
			jPanel3.add(jLabel, null);
			jPanel3.add(get_txt_total_ingreso(), null);
			jPanel3.add(get_txt_total_diferencia(), null);
			jPanel3.add(jLabel2, null);
		}
		return jPanel3;
	}

	/**
	 * This method initializes _btn_grabar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_grabar() {
		if (_btn_grabar == null) {
			_btn_grabar = new JButton();
			_btn_grabar.setToolTipText("Grabar");
			URL resourceURL = getClass().getClassLoader().getResource("icons/filesave.png");
			_btn_grabar.setIcon(new ImageIcon(resourceURL));
		}
		return _btn_grabar;
	}
	
	
	public void block(){
		this._txt_idPago.setEditable(false);
	}
	public void setMedioFocus(){
		this.jTableMedios.requestFocusInWindow();
		this.jTableMedios.changeSelection(0,0,false,false);
		this.jTableMedios.editCellAt(0,0);
		this.jTableMedios.transferFocus();
	}
	

	/**
	 * This method initializes _txt_leyenda	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_leyenda() {
		if (_txt_leyenda == null) {
			_txt_leyenda = new JTextField();
			
			_txt_leyenda.setBackground(Color.black);
			_txt_leyenda.setBounds(new Rectangle(2, 87, 748, 19));
			_txt_leyenda.setHorizontalAlignment(JTextField.RIGHT);
			Font fuente=new Font("Arial", Font.BOLD, 10);
			_txt_leyenda.setFont(fuente);
		}
		return _txt_leyenda;
	}

	/**
	 * This method initializes _btn_cancelar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cancelar() {
		if (_btn_cancelar == null) {
			_btn_cancelar = new JButton();
			_btn_cancelar.setToolTipText("Cancelar");
			URL resourceURL = getClass().getClassLoader().getResource("beta/tools/icons/stock_calc-cancel.png");
			_btn_cancelar.setIcon(new ImageIcon(resourceURL));
		}
		return _btn_cancelar;
	}
	/**
	 * This method initializes _btn_anular	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_anular() {
		if (_btn_anular == null) {
			_btn_anular = new JButton();
			_btn_anular.setToolTipText("Anular");
			_btn_anular.setEnabled(false);
			URL resourceURL = getClass().getClassLoader().getResource("beta/tools/icons/user-trash.png");
			_btn_anular.setIcon(new ImageIcon(getClass().getResource("/icons/edit-delete.png")));
		}
		return _btn_anular;
	}
	
	
	/**
	 * This method initializes _btn_fecha	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_fecha() {
		if (_btn_fecha == null) {
			_btn_fecha = new JButton();
			_btn_fecha.setBounds(new Rectangle(726, 5, 29, 20));
			URL resourceURL = getClass().getClassLoader().getResource(
			"icons/calendar.gif");
			_btn_fecha.setIcon(new ImageIcon(resourceURL));
		}
		return _btn_fecha;
	}

	/**
	 * This method initializes _txt_estado	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_estado() {
		if (_txt_estado == null) {
			_txt_estado = new JTextField();
			_txt_estado.setBounds(new Rectangle(238, 5, 152, 16));
			_txt_estado.setForeground(Color.white);
			_txt_estado.setEditable(false);
			_txt_estado.setBackground(Color.black);
		}
		return _txt_estado;
	}

	/**
	 * This method initializes _btn_buscar_pago	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_pago() {
		if (_btn_buscar_pago == null) {
			_btn_buscar_pago = new JButton();
			_btn_buscar_pago.setBounds(new Rectangle(213, 5, 23, 20));
			URL resourceURL = getClass().getClassLoader().getResource("beta/tools/icons/search.png");
			_btn_buscar_pago.setIcon(new ImageIcon(resourceURL));
		}
		return _btn_buscar_pago;
	}

	/**
	 * This method initializes _btn_salir	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_salir() {
		if (_btn_salir == null) {
			_btn_salir = new JButton();
			_btn_salir.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-logout.png")));
		}
		return _btn_salir;
	}
	
	/**
	 * This method initializes jToolBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar() {
		if (jToolBar == null) {
			jToolBar = new JToolBar();
			jToolBar.setFloatable(false);
			jToolBar.setBounds(new Rectangle(6, 4, 755, 21));
			jToolBar.add(get_btn_cancelar());
			jToolBar.add(get_btn_grabar());
			jToolBar.add(get_btn_anular());
			jToolBar.add(get_btn_salir());
			jToolBar.addSeparator();
			jToolBar.add(get_btn_error());
		}
		return jToolBar;
	}

	/**
	 * This method initializes _btn_buscar_caja	
	 * 	
	 * @return javax.swing.JButton	
	 */
	

	/**
	 * This method initializes _txt_concepto	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_concepto() {
		if (_txt_concepto == null) {
			_txt_concepto = new JTextField();
			_txt_concepto.setBounds(new Rectangle(99, 83, 652, 19));
		}
		return _txt_concepto;
	}

	/**
	 * This method initializes _list_cajas	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_list_cajas() {
		if (_list_cajas == null) {
			_list_cajas = new JComboBox();
			_list_cajas.setBounds(new Rectangle(99, 29, 111, 18));
		}
		return _list_cajas;
	}

	/**
	 * This method initializes _txt_caja_detalle	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_caja_detalle() {
		if (_txt_caja_detalle == null) {
			_txt_caja_detalle = new JTextField();
			_txt_caja_detalle.setBounds(new Rectangle(213, 30, 167, 17));
			_txt_caja_detalle.setEditable(false);
		}
		return _txt_caja_detalle;
	}

	/**
	 * This method initializes _txt_total_ingreso	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_total_ingreso() {
		if (_txt_total_ingreso == null) {
			_txt_total_ingreso = new JTextField();
			_txt_total_ingreso.setEditable(false);
			_txt_total_ingreso.setLocation(new Point(630, 38));
			_txt_total_ingreso.setSize(new Dimension(120, 18));
			_txt_total_ingreso.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_total_ingreso.setHorizontalAlignment(JTextField.RIGHT);
		}
		return _txt_total_ingreso;
	}

	/**
	 * This method initializes _txt_total_diferencia	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_total_diferencia() {
		if (_txt_total_diferencia == null) {
			_txt_total_diferencia = new JTextField();
			_txt_total_diferencia.setEditable(false);
			_txt_total_diferencia.setLocation(new Point(630, 63));
			_txt_total_diferencia.setSize(new Dimension(120, 18));
			_txt_total_diferencia.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_total_diferencia.setHorizontalAlignment(JTextField.RIGHT);
		}
		return _txt_total_diferencia;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(565, 3, 170, 17));
			jLabel4.setFont(new Font("Arial", Font.ITALIC, 14));
			jLabel4.setForeground(Color.blue);
			jLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel4.setText("Ingreso");
			jPanel1 = new JPanel();
			jPanel1.setLayout(null);
			jPanel1.setLocation(new Point(7, 288));
			jPanel1.setBackground(Color.gray);
			jPanel1.setSize(new Dimension(753, 137));
			jPanel1.add(getJScrollPane(), null);
			jPanel1.add(jLabel4, null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(11, 23, 725, 101));
			jScrollPane.setViewportView(getJTableMedios2());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTableMedios2	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getJTableMedios2() {
		if (jTableMedios2 == null) {
			jTableMedios2 = new JTable();
		}
		return jTableMedios2;
	}
	
	public void setJTableMedios2(JTable table){
		this.jTableMedios2=table;
		this.jScrollPane.setViewportView(this.jTableMedios2);
	}

	/**
	 * This method initializes _txt_cotizacion_dolar	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_cotizacion_dolar() {
		if (_txt_cotizacion_dolar == null) {
			_txt_cotizacion_dolar = new JTextField();
			_txt_cotizacion_dolar.setBounds(new Rectangle(99, 55, 110, 17));
		}
		return _txt_cotizacion_dolar;
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
