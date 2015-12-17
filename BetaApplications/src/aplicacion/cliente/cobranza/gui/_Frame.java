package aplicacion.cliente.cobranza.gui;


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
import javax.swing.JComboBox;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel jContentPane = null;

	private JPanel jPanel = null;

	private JLabel jLabel = null;

	private JTextField _txt_idcliente = null;
	
	private JTextField _txt_clientedescripcion = null;
	
	private JLabel jLabel1 = null;

	private JTextField _txt_idcobranza = null;

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

	private JButton _btn_imprimir_cptes = null;

	private JToolBar jToolBar = null;

	private JButton _btn_salir = null;

	private JButton _btn_grabar = null;

	private JCheckBox _chk_seleccionar_creditos = null;

	private JButton _btn_buscar = null;

	private JTextField _txt_deuda_desde = null;

	private JButton _btn_buscar_deuda_desde = null;

	private JTextField _txt_deuda_hasta = null;

	private JButton _btn_buscar_deuda_hasta = null;

	private JTextField _txt_credito_desde = null;

	private JTextField _txt_credito_hasta = null;

	private JButton _btn_buscar_credito_desde = null;

	private JButton _btn_buscar_credito_hasta = null;

	private JButton _btn_buscar_cliente = null;

	private JButton _btn_cargar_cliente = null;

	private JLabel jLabel9 = null;

	private JTextField _txt_saldo = null;

	private JButton _btn_error = null;

	private JButton _btn_imprimir = null;

	private JButton _btn_nuevo = null;

	private JComboBox _lst_modo = null;

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
		this.setSize(783, 635);
		this.setContentPane(getJContentPane());
		this.setTitle("Cobranza Cliente");
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
			jLabel4.setBounds(new Rectangle(481, 91, 140, 16));
			jLabel3 = new JLabel();
			jLabel3.setText("Total Pago");
			jLabel3.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3.setBounds(new Rectangle(481, 67, 140, 19));
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
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(9, 4, 73, 17));
			jLabel1.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel1.setText("Cobranza ");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(8, 27, 74, 19));
			jLabel.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel.setText("Cliente");
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBounds(new Rectangle(4, 33, 756, 58));
			jPanel.add(jLabel, null);
			jPanel.setBackground(Color.LIGHT_GRAY);
			jPanel.add(get_txt_idcliente(), null);
			jPanel.add(get_txt_clientedescripcion(), null);
			jPanel.add(jLabel1, null);
			jPanel.add(get_txt_idcobranza(), null);
			jPanel.add(get_txt_fecha(), null);
			jPanel.add(get_btn_fecha(), null);
			jPanel.add(get_txt_estado(), null);
			jPanel.add(get_btn_buscar(), null);
			jPanel.add(get_btn_buscar_cliente(), null);
			jPanel.add(get_btn_cargar_cliente(), null);
			jPanel.add(get_lst_modo(), null);
			
		}
		return jPanel;
	}

	/**
	 * This method initializes _txt_idcliente	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	
	
	
	public JTextField get_txt_idcliente() {
		if (_txt_idcliente == null) {
			_txt_idcliente = new JTextField();
			_txt_idcliente.setEditable(false);
			_txt_idcliente.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_idcliente.setBounds(new Rectangle(87, 27, 109, 18));
		
		}
		return _txt_idcliente;
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
			_txt_clientedescripcion.setBounds(new Rectangle(248, 27, 381, 18));
		}
		return _txt_clientedescripcion;
	}

	/**
	 * This method initializes _txt_idcobranza	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idcobranza() {
		if (_txt_idcobranza == null) {
			_txt_idcobranza = new JTextField();
			_txt_idcobranza.setBounds(new Rectangle(86, 4, 109, 18));
			_txt_idcobranza.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_idcobranza;
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
			jLabel6.setBounds(new Rectangle(598, 2, 127, 16));
			jLabel6.setText("Comprobantes");
			jLabel6.setBackground(Color.black);
			jLabel6.setForeground(Color.blue);
			Font fuente=new Font("Arial", Font.ITALIC, 13);
			jLabel6.setFont(fuente);
			jLabel6.setHorizontalAlignment(JTextField.RIGHT);
			jPanel1 = new JPanel();
			jPanel1.setLayout(null);
			jPanel1.setBackground(Color.LIGHT_GRAY);
			jPanel1.setBounds(new Rectangle(4, 94, 755, 137));
			jPanel1.add(getJScrollPaneCpte(), null);
			jPanel1.add(get_chk_seleccionar_cpte(), null);
			jPanel1.add(jLabel6, null);
			jPanel1.add(get_btn_imprimir_cptes(), null);
			jPanel1.add(get_txt_deuda_desde(), null);
			jPanel1.add(get_btn_buscar_deuda_desde(), null);
			jPanel1.add(get_txt_deuda_hasta(), null);
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
			jPanel2.setBounds(new Rectangle(3, 344, 753, 129));
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
			_txt_anticipo.setBounds(new Rectangle(629, 86, 107, 18));
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
			_txt_total_pago.setBounds(new Rectangle(629, 66, 107, 18));
		}
		return _txt_total_pago;
	}
	/**
	 * This method initializes jScrollPaneCpte	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	public JScrollPane getJScrollPaneCpte() {
		if (jScrollPaneCpte == null) {
			jScrollPaneCpte = new JScrollPane();
			jScrollPaneCpte.setBounds(new Rectangle(12, 24, 726, 109));
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
			_chk_seleccionar_cpte.setFont(new Font("Dialog", Font.BOLD, 10));
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
			jLabel9.setBounds(new Rectangle(483, 45, 138, 17));
			jLabel9.setFont(new Font("Dialog", Font.BOLD, 10));
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
			jPanel3.setBounds(new Rectangle(3, 476, 753, 108));
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

	public void setInitialFocus(){
		this._txt_idcliente.requestFocusInWindow();
	}
	public void block(){
		this._txt_idcobranza.setEditable(false);
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
			jLabel7.setBounds(new Rectangle(608, 2, 130, 16));
			jLabel7.setText("Creditos Disponibles");
			jLabel7.setBackground(Color.black);
			jLabel7.setForeground(Color.blue);
			Font fuente=new Font("Arial", Font.ITALIC, 13);
			jLabel7.setFont(fuente);
			jLabel7.setHorizontalAlignment(JTextField.RIGHT);
			
			jPanel4 = new JPanel();
			
			jPanel4.setLayout(null);
			jPanel4.setBackground(Color.LIGHT_GRAY);
			jPanel4.setBounds(new Rectangle(4, 237, 753, 104));
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
			jScrollPaneOPG.setBounds(new Rectangle(14, 19, 724, 79));
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
			_txt_leyenda.setBounds(new Rectangle(2, 87, 465, 19));
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
			_btn_cancelar.setIcon(new ImageIcon(getClass().getResource("/icons/stock_calc-cancel.png")));
	
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
			_btn_anular.setIcon(new ImageIcon(getClass().getResource("/icons/edit-delete.png")));
			_btn_anular.setToolTipText("Anular");
			_btn_anular.setEnabled(false);
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
			_txt_estado.setBounds(new Rectangle(381, 3, 152, 19));
			_txt_estado.setForeground(Color.white);
			_txt_estado.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_estado.setBackground(Color.black);
		}
		return _txt_estado;
	}

	/**
	 * This method initializes _btn_imprimir_cptes	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_imprimir_cptes() {
		if (_btn_imprimir_cptes == null) {
			_btn_imprimir_cptes = new JButton();
			_btn_imprimir_cptes.setBounds(new Rectangle(728, 2, 24, 16));
			URL resourceURL = getClass().getClassLoader().getResource(
			"icons/stock_print2.png");
			_btn_imprimir_cptes.setIcon(new ImageIcon(resourceURL));
		}
		return _btn_imprimir_cptes;
	}

	/**
	 * This method initializes jToolBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar() {
		if (jToolBar == null) {
			jToolBar = new JToolBar();
			jToolBar.setBounds(new Rectangle(3, 2, 768, 20));
			jToolBar.setFloatable(false);
			jToolBar.add(get_btn_nuevo());
			jToolBar.add(get_btn_cancelar());
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
			_btn_salir.setToolTipText("Cerrar");
		}
		return _btn_salir;
	}

	/**
	 * This method initializes _btn_grabar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_grabar() {
		if (_btn_grabar == null) {
			_btn_grabar = new JButton();
			_btn_grabar.setIcon(new ImageIcon(getClass().getResource("/icons/stock_save-as.png")));
			_btn_grabar.setToolTipText("Guardar");
		}
		return _btn_grabar;
	}

	/**
	 * This method initializes _chk_seleccionar_creditos	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_seleccionar_creditos() {
		if (_chk_seleccionar_creditos == null) {
			_chk_seleccionar_creditos = new JCheckBox();
			_chk_seleccionar_creditos.setBounds(new Rectangle(16, 4, 146, 14));
			_chk_seleccionar_creditos.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_seleccionar_creditos.setText("Seleccionar");
		}
		return _chk_seleccionar_creditos;
	}

	/**
	 * This method initializes _btn_buscar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar() {
		if (_btn_buscar == null) {
			_btn_buscar = new JButton();
			_btn_buscar.setBounds(new Rectangle(197, 2, 21, 19));
			_btn_buscar.setToolTipText("Buscar");
			_btn_buscar.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-searchtool.png")));
		}
		return _btn_buscar;
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
	 * This method initializes _btn_buscar_deuda_desde	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_deuda_desde() {
		if (_btn_buscar_deuda_desde == null) {
			_btn_buscar_deuda_desde = new JButton();
			_btn_buscar_deuda_desde.setBounds(new Rectangle(331, 2, 22, 17));
			_btn_buscar_deuda_desde.setIcon(new ImageIcon(getClass().getResource("/icons/calendar-16.gif")));
		}
		return _btn_buscar_deuda_desde;
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
	 * This method initializes _btn_buscar_deuda_hasta	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_deuda_hasta() {
		if (_btn_buscar_deuda_hasta == null) {
			_btn_buscar_deuda_hasta = new JButton();
			_btn_buscar_deuda_hasta.setBounds(new Rectangle(521, 1, 23, 18));
			_btn_buscar_deuda_hasta.setIcon(new ImageIcon(getClass().getResource("/icons/calendar-16.gif")));
		}
		return _btn_buscar_deuda_hasta;
	}

	/**
	 * This method initializes _txt_credito_desde	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_credito_desde() {
		if (_txt_credito_desde == null) {
			_txt_credito_desde = new JTextField();
			_txt_credito_desde.setBounds(new Rectangle(231, 1, 103, 16));
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
			_txt_credito_hasta.setBounds(new Rectangle(425, 2, 102, 16));
		}
		return _txt_credito_hasta;
	}

	/**
	 * This method initializes _btn_buscar_credito_desde	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_credito_desde() {
		if (_btn_buscar_credito_desde == null) {
			_btn_buscar_credito_desde = new JButton();
			_btn_buscar_credito_desde.setBounds(new Rectangle(336, 1, 20, 17));
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
			_btn_buscar_credito_hasta.setBounds(new Rectangle(529, 0, 23, 17));
			_btn_buscar_credito_hasta.setIcon(new ImageIcon(getClass().getResource("/icons/calendar-16.gif")));
		}
		return _btn_buscar_credito_hasta;
	}

	/**
	 * This method initializes _btn_buscar_cliente	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_cliente() {
		if (_btn_buscar_cliente == null) {
			_btn_buscar_cliente = new JButton();
			_btn_buscar_cliente.setBounds(new Rectangle(199, 27, 21, 19));
			_btn_buscar_cliente.setToolTipText("Buscar");
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
			_btn_cargar_cliente.setBounds(new Rectangle(224, 27, 21, 19));
			_btn_cargar_cliente.setToolTipText("Cargar");
			_btn_cargar_cliente.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-refresh.png")));
		}
		return _btn_cargar_cliente;
	}

	/**
	 * This method initializes _txt_saldo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_saldo() {
		if (_txt_saldo == null) {
			_txt_saldo = new JTextField();
			_txt_saldo.setBounds(new Rectangle(630, 44, 105, 19));
			_txt_saldo.setEditable(false);
			_txt_saldo.setText("0.00");
			_txt_saldo.setHorizontalAlignment(JTextField.RIGHT);
			_txt_saldo.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_saldo;
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
			_btn_imprimir.setToolTipText("Imprimir Copia");
			_btn_imprimir.setEnabled(false);
			
		}
		return _btn_imprimir;
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
			_btn_nuevo.setToolTipText("Nueva Cobranza");
		}
		return _btn_nuevo;
	}

	/**
	 * This method initializes _lst_modo	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_modo() {
		if (_lst_modo == null) {
			_lst_modo = new JComboBox();
			_lst_modo.setBounds(new Rectangle(221, 4, 147, 17));
			_lst_modo.setFont(new Font("Dialog", Font.BOLD, 10));
		}
		return _lst_modo;
	}
	
		
}  //  @jve:decl-index=0:visual-constraint="10,10"
