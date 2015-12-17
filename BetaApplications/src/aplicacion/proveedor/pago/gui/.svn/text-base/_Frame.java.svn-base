package aplicacion.proveedor.pago.gui;


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
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JComboBox;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel jContentPane = null;

	private JPanel jPanel = null;

	private JLabel jLabel = null;

	private JTextField _txt_idproveedor = null;
	
	private JTextField _txt_proveedor_descripcion = null;
	
	private JLabel jLabel1 = null;

	private JTextField _txt_idpago = null;

	private JTextField _txt_fecha = null;

	private JPanel jPanel1 = null;

	private JPanel jPanel2 = null;
	private JScrollPane jScrollPaneMedios = null;
	
	private JTable jTableMedios = null;
	private JTextField _txt_total_cpte = null;
	private JTextField _txt_anticipo = null;
	private JTextField _txt_total_pago = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JLabel jLabel4 = null;
	private JScrollPane jScrollPaneCpte = null;
	private JTable jTableCpte = null;
	private JCheckBox _chk_seleccionar_cpte = null;
	private JPanel jPanel3 = null;
	private JButton _btn_grabar = null;
	private JPanel jPanel4 = null;
	private JScrollPane jScrollPaneOPG = null;
	private JTable jTableOPG = null;
	private JLabel jLabel5 = null;
	private JTextField _txt_total_creditos = null;
	private JTextField _txt_leyenda = null;
	private JLabel jLabel6 = null;

	private JLabel jLabel7 = null;

	private JLabel jLabel8 = null;

	private JButton _btn_cancelar = null;

	private JButton _btn_anular = null;

	private JButton _btn_fecha = null;

	private JTextField _txt_estado = null;

	private JButton _btn_buscar_pago = null;

	private JButton _btn_buscar_proveedor = null;

	private JToolBar jToolBar = null;

	private JButton _btn_salir = null;

	private JCheckBox _chk_seleccionar_creditos = null;

	private JLabel jLabel9 = null;

	private JTextField _txt_saldo = null;

	private JTextField _txt_deuda_desde = null;

	private JTextField _txt_deuda_hasta = null;

	private JTextField _txt_credito_desde = null;

	private JTextField _txt_credito_hasta = null;

	private JButton _btn_buscar_deuda_desde = null;

	private JButton _btn_buscar_deuda_hasta = null;

	private JButton _btn_buscar_credito_desde = null;

	private JButton _btn_buscar_credito_hasta = null;

	private JButton _btn_cargar_proveedor = null;

	private JButton _btn_error = null;

	private JButton _btn_imprimir = null;

	private JLabel jLabel10 = null;

	private JTextField _txt_detalle = null;

	private JLabel labelt_caja = null;

	private JComboBox _lst_caja = null;

	private JTextField _txt_caja_detalle = null;

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
		this.setSize(783, 689);
		this.setContentPane(getJContentPane());
		this.setTitle("Pago Proveedor");
	}
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel4 = new JLabel();
			jLabel4.setText("Anticipo");
			jLabel4.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel4.setBounds(new Rectangle(482, 90, 140, 16));
			jLabel3 = new JLabel();
			jLabel3.setText("Total Pago");
			jLabel3.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3.setBounds(new Rectangle(482, 66, 140, 19));
			jLabel2 = new JLabel();
			jLabel2.setText("Total Comprobantes");
			jLabel2.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2.setBounds(new Rectangle(481, 5, 140, 17));
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJPanel(), null);
			jContentPane.add(getJPanel1(), null);
			jContentPane.add(getJPanel3(), null);
			jContentPane.add(getJPanel4(), null);
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
			labelt_caja = new JLabel();
			labelt_caja.setBounds(new Rectangle(4, 28, 78, 16));
			labelt_caja.setHorizontalAlignment(SwingConstants.RIGHT);
			labelt_caja.setFont(new Font("Dialog", Font.PLAIN, 10));
			labelt_caja.setText("Caja");
			jLabel10 = new JLabel();
			jLabel10.setBounds(new Rectangle(4, 74, 79, 18));
			jLabel10.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel10.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel10.setText("Detalle");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(9, 4, 73, 17));
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setText("Pago");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(8, 50, 74, 19));
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setText("Proveedor");
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBounds(new Rectangle(5, 33, 755, 103));
			jPanel.add(jLabel, null);
			jPanel.setBackground(Color.LIGHT_GRAY);
			jPanel.add(get_txt_idproveedor(), null);
			jPanel.add(get_txt_proveedor_descripcion(), null);
			jPanel.add(jLabel1, null);
			jPanel.add(get_txt_idpago(), null);
			jPanel.add(get_txt_fecha(), null);
			jPanel.add(get_btn_fecha(), null);
			jPanel.add(get_txt_estado(), null);
			jPanel.add(get_btn_buscar_pago(), null);
			jPanel.add(get_btn_buscar_proveedor(), null);
			jPanel.add(get_btn_cargar_proveedor(), null);
			jPanel.add(jLabel10, null);
			jPanel.add(get_txt_detalle(), null);
			jPanel.add(labelt_caja, null);
			jPanel.add(get_lst_caja(), null);
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
			_txt_idproveedor.setBounds(new Rectangle(87, 50, 109, 18));
		
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
			_txt_proveedor_descripcion.setEditable(false);
			_txt_proveedor_descripcion.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_proveedor_descripcion.setBounds(new Rectangle(246, 50, 383, 18));
		}
		return _txt_proveedor_descripcion;
	}

	/**
	 * This method initializes _txt_idpago	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idpago() {
		if (_txt_idpago == null) {
			_txt_idpago = new JTextField();
			_txt_idpago.setBounds(new Rectangle(86, 4, 109, 18));
			_txt_idpago.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_idpago.setText("");
		}
		return _txt_idpago;
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

	
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(607, 5, 127, 16));
			jLabel6.setText("Comprobantes");
			jLabel6.setBackground(Color.black);
			jLabel6.setForeground(Color.blue);
			Font fuente=new Font("Arial", Font.ITALIC, 13);
			jLabel6.setFont(fuente);
			jLabel6.setHorizontalAlignment(JTextField.RIGHT);
			jPanel1 = new JPanel();
			jPanel1.setLayout(null);
			jPanel1.setBackground(Color.LIGHT_GRAY);
			jPanel1.setBounds(new Rectangle(4, 140, 754, 137));
			jPanel1.add(getJScrollPaneCpte(), null);
			jPanel1.add(get_chk_seleccionar_cpte(), null);
			jPanel1.add(jLabel6, null);
			jPanel1.add(get_txt_deuda_desde(), null);
			jPanel1.add(get_txt_deuda_hasta(), null);
			jPanel1.add(get_btn_buscar_deuda_desde(), null);
			jPanel1.add(get_btn_buscar_deuda_hasta(), null);
		}
		return jPanel1;
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
			jPanel2.setBounds(new Rectangle(4, 398, 753, 129));
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
	 * This method initializes _txt_total_cpte	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_total_cpte() {
		if (_txt_total_cpte == null) {
			_txt_total_cpte = new JTextField();
			_txt_total_cpte.setEditable(false);
			_txt_total_cpte.setHorizontalAlignment(JTextField.RIGHT);
			_txt_total_cpte.setText("0.00");
			_txt_total_cpte.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_total_cpte.setBounds(new Rectangle(629, 4, 107, 18));
		}
		return _txt_total_cpte;
	}

	/**
	 * This method initializes _txt_anticipo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_anticipo() {
		if (_txt_anticipo == null) {
			_txt_anticipo = new JTextField();
			_txt_anticipo.setEditable(false);
			_txt_anticipo.setHorizontalAlignment(JTextField.RIGHT);
			_txt_anticipo.setText("0.00");
			_txt_anticipo.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_anticipo.setBounds(new Rectangle(630, 85, 107, 18));
		}
		return _txt_anticipo;
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
			_txt_total_pago.setBounds(new Rectangle(630, 65, 107, 18));
		}
		return _txt_total_pago;
	}
	/**
	 * This method initializes jScrollPaneCpte	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPaneCpte() {
		if (jScrollPaneCpte == null) {
			jScrollPaneCpte = new JScrollPane();
			jScrollPaneCpte.setBounds(new Rectangle(12, 22, 726, 109));
			jScrollPaneCpte.setViewportView(getJTableCpte());
		}
		return jScrollPaneCpte;
	}

	/**
	 * This method initializes jTableCpte	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getJTableCpte() {
		if (jTableCpte == null) {
			jTableCpte = new JTable();
		}
		return jTableCpte;
	}
	
	public void setJTableCpte(JTable table){
		this.jTableCpte=table;
		this.jScrollPaneCpte.setViewportView(this.jTableCpte);
	}

	/**
	 * This method initializes _chk_seleccionar_cpte	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	
	public JCheckBox get_chk_seleccionar_cpte() {
		if (_chk_seleccionar_cpte == null) {
			_chk_seleccionar_cpte = new JCheckBox();
			_chk_seleccionar_cpte.setBounds(new Rectangle(14, 4, 141, 14));
			_chk_seleccionar_cpte.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_seleccionar_cpte.setText("Seleccionar");
			
		}
		return _chk_seleccionar_cpte;
	}
	/**
	 * This method initializes jPanel3	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jLabel9 = new JLabel();
			jLabel9.setBounds(new Rectangle(483, 46, 139, 18));
			jLabel9.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel9.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel9.setText("Saldo");
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(481, 24, 140, 17));
			jLabel5.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel5.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel5.setText("NCreditos/Anticipos");
			jPanel3 = new JPanel();
			jPanel3.setLayout(null);
			jPanel3.setBackground(Color.LIGHT_GRAY);
			jPanel3.setBounds(new Rectangle(4, 529, 753, 118));
			jPanel3.add(jLabel2, null);
			jPanel3.add(get_txt_total_cpte(), null);
			jPanel3.add(jLabel3, null);
			jPanel3.add(get_txt_total_pago(), null);
			jPanel3.add(jLabel4, null);
			jPanel3.add(get_txt_anticipo(), null);
			jPanel3.add(jLabel5, null);
			jPanel3.add(get_txt_total_creditos(), null);
			jPanel3.add(get_txt_leyenda(), null);
			jPanel3.add(jLabel9, null);
			jPanel3.add(get_txt_saldo(), null);
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
		}
		return _btn_grabar;
	}
	
	public void setInitialFocus(){
		this._txt_idproveedor.requestFocusInWindow();
	}
	public void block(){
		this._txt_idpago.setEditable(false);
	}
	public void setMedioFocus(){
		this.jTableMedios.requestFocusInWindow();
		this.jTableMedios.changeSelection(0,0,false,false);
		this.jTableMedios.editCellAt(0,0);
		this.jTableMedios.transferFocus();
	}
	/**
	 * This method initializes jPanel4	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel4() {
		if (jPanel4 == null) {
			jLabel7 = new JLabel();
			jLabel7.setBounds(new Rectangle(581, 2, 157, 16));
			jLabel7.setText("Creditos Disponibles");
			jLabel7.setBackground(Color.black);
			jLabel7.setForeground(Color.blue);
			Font fuente=new Font("Arial", Font.ITALIC, 13);
			jLabel7.setFont(fuente);
			jLabel7.setHorizontalAlignment(JTextField.RIGHT);
			
			jPanel4 = new JPanel();
			
			jPanel4.setLayout(null);
			jPanel4.setBackground(Color.LIGHT_GRAY);
			jPanel4.setBounds(new Rectangle(4, 281, 753, 114));
			jPanel4.add(getJScrollPaneOPG(), null);
			jPanel4.add(jLabel7, null);
			jPanel4.add(get_chk_seleccionar_creditos(), null);
			jPanel4.add(get_txt_credito_desde(), null);
			jPanel4.add(get_txt_credito_hasta(), null);
			jPanel4.add(get_btn_buscar_credito_desde(), null);
			jPanel4.add(get_btn_buscar_credito_hasta(), null);
		}
		return jPanel4;
	}

	/**
	 * This method initializes jScrollPaneOPG	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPaneOPG() {
		if (jScrollPaneOPG == null) {
			jScrollPaneOPG = new JScrollPane();
			jScrollPaneOPG.setBounds(new Rectangle(14, 25, 724, 84));
			jScrollPaneOPG.setViewportView(getJTableOPG());
		}
		return jScrollPaneOPG;
	}

	/**
	 * This method initializes jTableOPG	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getJTableOPG() {
		if (jTableOPG == null) {
			jTableOPG = new JTable();
		}
		return jTableOPG;
	}
	
	public void setJTableOPG(JTable table){
		this.jTableOPG=table;
		this.jScrollPaneOPG.setViewportView(this.jTableOPG);
	}

	/**
	 * This method initializes _txt_total_creditos	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_total_creditos() {
		if (_txt_total_creditos == null) {
			_txt_total_creditos = new JTextField();
			_txt_total_creditos.setEditable(false);
			_txt_total_creditos.setHorizontalAlignment(JTextField.RIGHT);
			_txt_total_creditos.setText("0.00");
			_txt_total_creditos.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_total_creditos.setBounds(new Rectangle(629, 24, 107, 18));
		}
		return _txt_total_creditos;
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
			_txt_leyenda.setBounds(new Rectangle(2, 87, 477, 19));
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
			URL resourceURL = getClass().getClassLoader().getResource("icons/stock_calc-cancel.png");
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
			_btn_fecha.setBounds(new Rectangle(724, 5, 23, 20));
			_btn_fecha.setIcon(new ImageIcon(getClass().getResource("/icons/calendar-16.gif")));
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
			_txt_estado.setBounds(new Rectangle(224, 5, 152, 16));
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
			_btn_buscar_pago.setBounds(new Rectangle(199, 3, 21, 19));
			URL resourceURL = getClass().getClassLoader().getResource("icons/search.png");
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
			_btn_buscar_proveedor.setBounds(new Rectangle(199, 49, 21, 19));
			_btn_buscar_proveedor.setEnabled(false);
			URL resourceURL = getClass().getClassLoader().getResource("icons/search.png");
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
			jToolBar.setBounds(new Rectangle(6, 4, 758, 21));
			jToolBar.add(get_btn_cancelar());
			jToolBar.add(get_btn_nuevo());
			jToolBar.add(get_btn_grabar());
			jToolBar.add(get_btn_imprimir());
			jToolBar.add(get_btn_anular());
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
			_btn_salir.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-logout.png")));
			_btn_salir.setToolTipText("Salir");
		}
		return _btn_salir;
	}

	/**
	 * This method initializes _chk_seleccionar_creditos	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_seleccionar_creditos() {
		if (_chk_seleccionar_creditos == null) {
			_chk_seleccionar_creditos = new JCheckBox();
			_chk_seleccionar_creditos.setBounds(new Rectangle(16, 4, 130, 13));
			_chk_seleccionar_creditos.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_seleccionar_creditos.setText("Seleccionar");
		}
		return _chk_seleccionar_creditos;
	}

	/**
	 * This method initializes _txt_saldo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_saldo() {
		if (_txt_saldo == null) {
			_txt_saldo = new JTextField();
			_txt_saldo.setBounds(new Rectangle(629, 45, 107, 18));
			_txt_saldo.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_saldo.setHorizontalAlignment(JTextField.RIGHT);
			_txt_saldo.setText("0.00");
			_txt_saldo.setEditable(false);
		}
		return _txt_saldo;
	}

	/**
	 * This method initializes _txt_deuda_desde	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_deuda_desde() {
		if (_txt_deuda_desde == null) {
			_txt_deuda_desde = new JTextField();
			_txt_deuda_desde.setBounds(new Rectangle(229, 2, 100, 17));
		}
		return _txt_deuda_desde;
	}

	/**
	 * This method initializes _txt_deuda_hasta	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_deuda_hasta() {
		if (_txt_deuda_hasta == null) {
			_txt_deuda_hasta = new JTextField();
			_txt_deuda_hasta.setBounds(new Rectangle(418, 2, 100, 17));
		}
		return _txt_deuda_hasta;
	}

	/**
	 * This method initializes _txt_credito_desde	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_credito_desde() {
		if (_txt_credito_desde == null) {
			_txt_credito_desde = new JTextField();
			_txt_credito_desde.setBounds(new Rectangle(229, 2, 100,17));
		}
		return _txt_credito_desde;
	}

	/**
	 * This method initializes _txt_credito_hasta	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_credito_hasta() {
		if (_txt_credito_hasta == null) {
			_txt_credito_hasta = new JTextField();
			_txt_credito_hasta.setBounds(new Rectangle(418, 3, 100,17));
		}
		return _txt_credito_hasta;
	}

	/**
	 * This method initializes _btn_buscar_deuda_desde	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_deuda_desde() {
		if (_btn_buscar_deuda_desde == null) {
			_btn_buscar_deuda_desde = new JButton();
			_btn_buscar_deuda_desde.setBounds(new Rectangle(331, 2, 18, 16));
			_btn_buscar_deuda_desde.setIcon(new ImageIcon(getClass().getResource("/icons/calendar-16.gif")));
		}
		return _btn_buscar_deuda_desde;
	}

	/**
	 * This method initializes _btn_buscar_deuda_hasta	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_deuda_hasta() {
		if (_btn_buscar_deuda_hasta == null) {
			_btn_buscar_deuda_hasta = new JButton();
			_btn_buscar_deuda_hasta.setBounds(new Rectangle(519, 2, 20, 17));
			_btn_buscar_deuda_hasta.setIcon(new ImageIcon(getClass().getResource("/icons/calendar-16.gif")));
		}
		return _btn_buscar_deuda_hasta;
	}

	/**
	 * This method initializes _btn_buscar_credito_desde	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_credito_desde() {
		if (_btn_buscar_credito_desde == null) {
			_btn_buscar_credito_desde = new JButton();
			_btn_buscar_credito_desde.setBounds(new Rectangle(330, 2, 17, 16));
			_btn_buscar_credito_desde.setIcon(new ImageIcon(getClass().getResource("/icons/calendar-16.gif")));
		}
		return _btn_buscar_credito_desde;
	}

	/**
	 * This method initializes _btn_buscar_credito_hasta	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_credito_hasta() {
		if (_btn_buscar_credito_hasta == null) {
			_btn_buscar_credito_hasta = new JButton();
			_btn_buscar_credito_hasta.setBounds(new Rectangle(519, 4, 19, 17));
			_btn_buscar_credito_hasta.setIcon(new ImageIcon(getClass().getResource("/icons/calendar-16.gif")));
		}
		return _btn_buscar_credito_hasta;
	}

	/**
	 * This method initializes _btn_cargar_proveedor	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cargar_proveedor() {
		if (_btn_cargar_proveedor == null) {
			_btn_cargar_proveedor = new JButton();
			_btn_cargar_proveedor.setBounds(new Rectangle(224, 49, 19, 19));
			_btn_cargar_proveedor.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-refresh.png")));
		}
		return _btn_cargar_proveedor;
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
	 * This method initializes _btn_imprimir	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_imprimir() {
		if (_btn_imprimir == null) {
			_btn_imprimir = new JButton();
			_btn_imprimir.setIcon(new ImageIcon(getClass().getResource("/icons/document-print.png")));
			_btn_imprimir.setToolTipText("Imprimir");
		}
		return _btn_imprimir;
	}

	/**
	 * This method initializes _txt_detalle	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_detalle() {
		if (_txt_detalle == null) {
			_txt_detalle = new JTextField();
			_txt_detalle.setLocation(new Point(87, 73));
			_txt_detalle.setPreferredSize(new Dimension(4, 18));
			_txt_detalle.setSize(new Dimension(542, 18));
		}
		return _txt_detalle;
	}

	/**
	 * This method initializes _lst_caja	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_caja() {
		if (_lst_caja == null) {
			_lst_caja = new JComboBox();
			_lst_caja.setBounds(new Rectangle(87, 27, 107, 17));
		}
		return _lst_caja;
	}

	/**
	 * This method initializes _txt_caja_detalle	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_caja_detalle() {
		if (_txt_caja_detalle == null) {
			_txt_caja_detalle = new JTextField();
			_txt_caja_detalle.setBounds(new Rectangle(199, 28, 179, 17));
		}
		return _txt_caja_detalle;
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
			_btn_nuevo.setToolTipText("Nuevo Pago");
		}
		return _btn_nuevo;
	}
	
		
}  //  @jve:decl-index=0:visual-constraint="10,10"
