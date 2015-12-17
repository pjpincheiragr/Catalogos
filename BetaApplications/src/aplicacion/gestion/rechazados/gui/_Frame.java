package aplicacion.gestion.rechazados.gui;


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

	private JLabel jLabel = null;

	private JTextField _txt_idproveedor = null;
	
	private JTextField _txt_clientedescripcion = null;
	
	private JLabel jLabel1 = null;

	private JTextField _txt_idPago = null;

	private JTextField _txt_fecha = null;

	private JPanel jPanel2 = null;
	private JScrollPane jScrollPaneMedios = null;
	
	private JTable jTableMedios = null;
	private JTextField _txt_total_pago = null;
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

	private JTextField _txt_estado = null;

	private JButton _btn_buscar_pago = null;

	private JButton _btn_buscar_proveedor = null;

	private JToolBar jToolBar = null;

	private JLabel jLabel6 = null;

	private JLabel jLabel7 = null;

	private JTextField _txt_concepto = null;

	private JComboBox _list_cajas = null;

	private JTextField _txt_caja_detalle = null;

	private JButton _btn_salir = null;

	private JScrollPane jScrollPane = null;

	private JScrollPane jScrollPane1 = null;

	private JTable jTable = null;

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
		this.setSize(783, 494);
		this.setContentPane(getJContentPane());
		this.setTitle("Registracion de Cheque Rechazado");
	}
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("Total");
			jLabel3.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3.setBounds(new Rectangle(488, 101, 140, 19));
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJPanel(), null);
			jContentPane.add(getJPanel3(), null);
			jContentPane.add(getJPanel2(), null);
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
			jLabel7 = new JLabel();
			jLabel7.setBounds(new Rectangle(12, 80, 83, 19));
			jLabel7.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel7.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel7.setText("Concepto");
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(22, 56, 74, 18));
			jLabel6.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel6.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel6.setText("Caja");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(9, 4, 89, 17));
			jLabel1.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setText("Comprobante");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(23, 28, 74, 19));
			jLabel.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setText("Cuenta");
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBounds(new Rectangle(4, 33, 756, 112));
			jPanel.add(jLabel, null);
			jPanel.setBackground(Color.LIGHT_GRAY);
			jPanel.add(get_txt_idproveedor(), null);
			jPanel.add(get_txt_clientedescripcion(), null);
			jPanel.add(jLabel1, null);
			jPanel.add(get_txt_idPago(), null);
			jPanel.add(get_txt_fecha(), null);
			jPanel.add(get_btn_fecha(), null);
			jPanel.add(get_txt_estado(), null);
			jPanel.add(get_btn_buscar_pago(), null);
			jPanel.add(get_btn_buscar_proveedor(), null);
			jPanel.add(jLabel6, null);
			jPanel.add(jLabel7, null);
			jPanel.add(get_txt_concepto(), null);
			jPanel.add(get_list_cajas(), null);
			jPanel.add(get_txt_caja_detalle(), null);
			
		}
		return jPanel;
	}

	/**
	 * This method initializes _txt_idproveedor	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	
	
	
	public JTextField get_txt_idproveedor() {
		if (_txt_idproveedor == null) {
			_txt_idproveedor = new JTextField();
			_txt_idproveedor.setEditable(false);
			_txt_idproveedor.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_idproveedor.setBounds(new Rectangle(101, 30, 107, 18));
		
		}
		return _txt_idproveedor;
	}


	/**
	 * This method initializes _txt_clientedescripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_clientedescripcion() {
		if (_txt_clientedescripcion == null) {
			_txt_clientedescripcion = new JTextField();
			_txt_clientedescripcion.setEditable(false);
			_txt_clientedescripcion.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_clientedescripcion.setBounds(new Rectangle(239, 27, 404, 18));
		}
		return _txt_clientedescripcion;
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
			_txt_idPago.setFont(new Font("Dialog", Font.PLAIN, 10));
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
			_txt_fecha.setFont(new Font("Dialog", Font.PLAIN, 10));
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
			jLabel8.setText("Forma de Pago");
			jLabel8.setBackground(Color.black);
			jLabel8.setForeground(Color.blue);
			Font fuente=new Font("Arial", Font.ITALIC, 13);
			jLabel8.setFont(fuente);
			jLabel8.setHorizontalAlignment(JTextField.RIGHT);
			jPanel2 = new JPanel();
			jPanel2.setLayout(null);
			jPanel2.setBackground(Color.LIGHT_GRAY);
			jPanel2.setBounds(new Rectangle(5, 150, 753, 128));
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
	 * This method initializes _txt_total_pago	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_total_pago() {
		if (_txt_total_pago == null) {
			_txt_total_pago = new JTextField();
			_txt_total_pago.setEditable(false);
			_txt_total_pago.setHorizontalAlignment(JTextField.RIGHT);
			_txt_total_pago.setText("0.00");
			_txt_total_pago.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_total_pago.setBounds(new Rectangle(629, 102, 107, 18));
		}
		return _txt_total_pago;
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
			jPanel3.setBackground(Color.LIGHT_GRAY);
			jPanel3.setBounds(new Rectangle(7, 286, 753, 152));
			jPanel3.add(jLabel3, null);
			jPanel3.add(get_txt_total_pago(), null);
			jPanel3.add(get_txt_leyenda(), null);
			jPanel3.add(getJScrollPane1(), null);
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
			_btn_grabar.setText("");
			_btn_grabar.setIcon(new ImageIcon(getClass().getResource("/icons/document-save.png")));
			_btn_grabar.setToolTipText("Grabar");
		}
		return _btn_grabar;
	}
	
	public void setInitialFocus(){
		this._txt_idproveedor.requestFocusInWindow();
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
			_txt_leyenda.setBounds(new Rectangle(2, 127, 748, 19));
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
			_btn_anular.setIcon(new ImageIcon(resourceURL));
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
			_txt_estado.setFont(new Font("Dialog", Font.PLAIN, 10));
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
	 * This method initializes _btn_buscar_proveedor	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_proveedor() {
		if (_btn_buscar_proveedor == null) {
			_btn_buscar_proveedor = new JButton();
			_btn_buscar_proveedor.setBounds(new Rectangle(213, 29, 25, 20));
			_btn_buscar_proveedor.setEnabled(false);
			URL resourceURL = getClass().getClassLoader().getResource("beta/tools/icons/search.png");
			_btn_buscar_proveedor.setIcon(new ImageIcon(resourceURL));
		}
		return _btn_buscar_proveedor;
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
			jToolBar.setBounds(new Rectangle(6, 4, 752, 21));
			jToolBar.add(get_btn_cancelar());
			jToolBar.add(get_btn_grabar());
			jToolBar.add(get_btn_anular());
			jToolBar.addSeparator(new Dimension(600,21));
			jToolBar.add(get_btn_salir());
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
			_txt_concepto.setBounds(new Rectangle(100, 83, 651, 19));
			_txt_concepto.setFont(new Font("Dialog", Font.PLAIN, 10));
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
			_list_cajas.setBounds(new Rectangle(100, 57, 107, 18));
			_list_cajas.setFont(new Font("Dialog", Font.PLAIN, 10));
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
			_txt_caja_detalle.setBounds(new Rectangle(212, 59, 153, 15));
			_txt_caja_detalle.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_caja_detalle.setEditable(false);
		}
		return _txt_caja_detalle;
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
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setBounds(new Rectangle(8, 6, 727, 87));
			jScrollPane1.setViewportView(getJTable());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes jTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTable() {
		if (jTable == null) {
			jTable = new JTable();
		}
		return jTable;
	}
	
		
}  //  @jve:decl-index=0:visual-constraint="10,10"
