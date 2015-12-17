package aplicacion.gestion.transferencia.gui;


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

	private JToolBar jToolBar = null;

	private JLabel jLabel6 = null;

	private JLabel jLabel7 = null;

	private JTextField _txt_concepto = null;

	private JComboBox _list_caja_origen = null;

	private JLabel jLabel = null;

	private JLabel jLabel2 = null;

	private JComboBox _list_caja_destino = null;

	private JLabel jLabel4 = null;

	private JTextField _txt_fecha_desde = null;

	private JLabel jLabel5 = null;

	private JTextField _txt_fecha_hasta = null;

	private JButton _btn_fecha_desde = null;

	private JButton _btn_fecha_hasta = null;

	private JTextField _txt_caja_origen_detalle = null;

	private JTextField _txt_caja_destino_detalle = null;

	private JButton _btn_salir = null;

	private JButton _btn_autocompletar = null;

	private JButton _btn_error = null;

	private JButton _btn_confirmar = null;

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
		this.setSize(783, 461);
		this.setContentPane(getJContentPane());
		this.setTitle("Transferencias Entre Cajas");
	}
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("Total ");
			jLabel3.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3.setBounds(new Rectangle(550, 5, 75, 19));
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
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(254, 30, 56, 18));
			jLabel5.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel5.setText("hasta");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(472, 5, 122, 18));
			jLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel4.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel4.setText("Fecha Operacion");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(372, 64, 80, 16));
			jLabel2.setHorizontalTextPosition(SwingConstants.RIGHT);
			jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel2.setText("Destino");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(10, 61, 87, 17));
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setText("Caja Origen");
			jLabel7 = new JLabel();
			jLabel7.setBounds(new Rectangle(8, 83, 91, 19));
			jLabel7.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel7.setText("Concepto");
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(10, 32, 90, 18));
			jLabel6.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel6.setText("Fecha Desde");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(9, 4, 89, 17));
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setText("Transferencia");
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBounds(new Rectangle(4, 33, 756, 112));
			jPanel.setBackground(Color.LIGHT_GRAY);
			jPanel.add(jLabel1, null);
			jPanel.add(get_txt_idtransferencia(), null);
			jPanel.add(get_txt_fecha(), null);
			jPanel.add(get_btn_fecha(), null);
			jPanel.add(get_txt_estado(), null);
			jPanel.add(get_btn_buscar_pago(), null);
			jPanel.add(jLabel6, null);
			jPanel.add(jLabel7, null);
			jPanel.add(get_txt_concepto(), null);
			jPanel.add(get_list_caja_origen(), null);
			jPanel.add(jLabel, null);
			jPanel.add(jLabel2, null);
			jPanel.add(get_list_caja_destino(), null);
			jPanel.add(jLabel4, null);
			jPanel.add(get_txt_fecha_desde(), null);
			jPanel.add(jLabel5, null);
			jPanel.add(get_txt_fecha_hasta(), null);
			jPanel.add(get_btn_fecha_desde(), null);
			jPanel.add(get_btn_fecha_hasta(), null);
			jPanel.add(get_txt_caja_origen_detalle(), null);
			jPanel.add(get_txt_caja_destino_detalle(), null);
			
		}
		return jPanel;
	}

	/**
	 * This method initializes _txt_idPago	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idtransferencia() {
		if (_txt_idPago == null) {
			_txt_idPago = new JTextField();
			_txt_idPago.setBounds(new Rectangle(104, 4, 103, 18));
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
			jLabel8.setBounds(new Rectangle(551, 3, 188, 18));
			jLabel8.setText("Medios Transferidos");
			jLabel8.setBackground(Color.black);
			jLabel8.setForeground(Color.blue);
			Font fuente=new Font("Arial", Font.ITALIC, 13);
			jLabel8.setFont(fuente);
			jLabel8.setHorizontalAlignment(JTextField.RIGHT);
			jPanel2 = new JPanel();
			jPanel2.setLayout(null);
			jPanel2.setBackground(Color.LIGHT_GRAY);
			jPanel2.setBounds(new Rectangle(5, 151, 753, 205));
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
			jScrollPaneMedios.setBounds(new Rectangle(13, 27, 728, 170));
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
			_txt_total_pago.setBounds(new Rectangle(633, 4, 107, 18));
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
			jPanel3.setBounds(new Rectangle(6, 365, 753, 55));
			jPanel3.add(jLabel3, null);
			jPanel3.add(get_txt_total_pago(), null);
			jPanel3.add(get_txt_leyenda(), null);
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
		this._txt_idPago.requestFocusInWindow();
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
			_txt_leyenda.setBounds(new Rectangle(6, 25, 745, 19));
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
			_txt_estado.setBounds(new Rectangle(238, 5, 175, 16));
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
	 * This method initializes jToolBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar() {
		if (jToolBar == null) {
			jToolBar = new JToolBar();
			jToolBar.setFloatable(false);
			jToolBar.setBounds(new Rectangle(6, 4, 754, 21));
			jToolBar.add(get_btn_cancelar());
			jToolBar.add(get_btn_autocompletar());
			jToolBar.add(get_btn_grabar());
			jToolBar.add(get_btn_confirmar());
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
			_txt_concepto.setBounds(new Rectangle(104, 83, 648, 19));
			_txt_concepto.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_concepto;
	}

	/**
	 * This method initializes _list_caja_origen	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_list_caja_origen() {
		if (_list_caja_origen == null) {
			_list_caja_origen = new JComboBox();
			_list_caja_origen.setBounds(new Rectangle(104, 60, 105, 18));
			_list_caja_origen.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _list_caja_origen;
	}

	/**
	 * This method initializes _list_caja_destino	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_list_caja_destino() {
		if (_list_caja_destino == null) {
			_list_caja_destino = new JComboBox();
			_list_caja_destino.setBounds(new Rectangle(458, 63, 105, 16));
			_list_caja_destino.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _list_caja_destino;
	}

	/**
	 * This method initializes _btn_cargar_medio_disponibles	
	 * 	
	 * @return javax.swing.JButton	
	 */

	/**
	 * This method initializes _txt_fecha_desde	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_fecha_desde() {
		if (_txt_fecha_desde == null) {
			_txt_fecha_desde = new JTextField();
			_txt_fecha_desde.setBounds(new Rectangle(104, 30, 103, 18));
			_txt_fecha_desde.setFont(new Font("Dialog", Font.PLAIN, 10));
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
			_txt_fecha_hasta.setBounds(new Rectangle(314, 30, 103, 16));
			_txt_fecha_hasta.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_fecha_hasta;
	}

	/**
	 * This method initializes _btn_fecha_desde	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_fecha_desde() {
		if (_btn_fecha_desde == null) {
			_btn_fecha_desde = new JButton();
			URL resourceURL = getClass().getClassLoader().getResource(
			"icons/calendar.gif");
			_btn_fecha_desde.setIcon(new ImageIcon(resourceURL));
			_btn_fecha_desde.setBounds(new Rectangle(213, 30, 22, 18));
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
			URL resourceURL = getClass().getClassLoader().getResource(
			"icons/calendar.gif");
			_btn_fecha_hasta.setIcon(new ImageIcon(resourceURL));
			_btn_fecha_hasta.setBounds(new Rectangle(422, 31, 21, 20));
		}
		return _btn_fecha_hasta;
	}

	/**
	 * This method initializes _txt_caja_origen_detalle	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_caja_origen_detalle() {
		if (_txt_caja_origen_detalle == null) {
			_txt_caja_origen_detalle = new JTextField();
			_txt_caja_origen_detalle.setBounds(new Rectangle(216, 60, 147, 17));
			_txt_caja_origen_detalle.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_caja_origen_detalle.setEditable(false);
		}
		return _txt_caja_origen_detalle;
	}

	/**
	 * This method initializes _txt_caja_destino_detalle	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_caja_destino_detalle() {
		if (_txt_caja_destino_detalle == null) {
			_txt_caja_destino_detalle = new JTextField();
			_txt_caja_destino_detalle.setBounds(new Rectangle(568, 63, 159, 15));
			_txt_caja_destino_detalle.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_caja_destino_detalle.setEditable(false);
		}
		return _txt_caja_destino_detalle;
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
	 * This method initializes _btn_autocompletar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_autocompletar() {
		if (_btn_autocompletar == null) {
			_btn_autocompletar = new JButton();
			_btn_autocompletar.setIcon(new ImageIcon(getClass().getResource("/icons/applications-system.png")));
			_btn_autocompletar.setToolTipText("Cargar Medios Disponibles para Transferir");
		}
		return _btn_autocompletar;
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
	 * This method initializes _btn_confirmar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_confirmar() {
		if (_btn_confirmar == null) {
			_btn_confirmar = new JButton();
			_btn_confirmar.setIcon(new ImageIcon(getClass().getResource("/icons/emblem-default-16.png")));
			_btn_confirmar.setEnabled(false);
			_btn_confirmar.setToolTipText("Confirmar Recepcion");
		}
		return _btn_confirmar;
	}
	
		
}  //  @jve:decl-index=0:visual-constraint="10,10"
