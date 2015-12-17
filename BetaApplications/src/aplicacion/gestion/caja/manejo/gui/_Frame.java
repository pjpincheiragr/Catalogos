package aplicacion.gestion.caja.manejo.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.text.*;
import java.text.*;


import javax.swing.JFormattedTextField;
import java.awt.Rectangle;
import java.awt.GridBagLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.Point;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.JProgressBar;
import java.awt.event.KeyEvent;
import javax.swing.JTabbedPane;
import javax.swing.JCheckBox;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JComboBox _lst_cajas = null;
	private JTextField _txt_hasta = null;
	private JButton _btn_cargar = null;
	private JButton _btn_egreso = null;
	private JButton _btn_ingreso = null;
	private JTextField _txt_caja_detalle = null;
	private JLabel jLabel1 = null;
	private JButton _btn_transferencia = null;
	private JButton _btn_salir = null;
	private JPanel jPanel1 = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private JTextField _txt_saldo = null;
	private JButton _btn_cheques = null;
	private JButton _btn_reporte = null;
	private JPanel jPanel2 = null;
	private JScrollPane jScrollPane1 = null;
	private JTable jTable1 = null;
	private JTextField _txt_arqueo = null;
	private JTextField _txt_suma_saldo = null;
	private JTextField _txt_diferencia = null;
	private JLabel jLabel3 = null;
	private JLabel jLabel4 = null;
	private JButton _btn_ajustar = null;
	private JButton _btn_canje = null;
	private JLabel jLabel5 = null;
	
	private JButton _btn_movimientos = null;
	private JToolBar jToolBar = null;
	private JButton _btn_rechazados = null;
	private JProgressBar jProgressBar = null;
	private JLabel jLabel7 = null;
	private JButton _btn_cobranza = null;
	private JButton _btn_pago = null;
	private JButton _btn_carga_venta = null;
	private JButton _btn_error = null;
	private JTextField _txt_desde = null;
	private JButton _btn_fecha_desde = null;
	private JButton _btn_fecha_hasta = null;
	private JLabel jLabel6 = null;
	private JTabbedPane jTabbedPane = null;
	private JTabbedPane jTabbedPane1 = null;  //  @jve:decl-index=0:
	private JScrollPane jScrollPane3 = null;
	private JTable jTable3 = null;
	private JScrollPane jScrollPane4 = null;
	private JTable jTable4 = null;
	private JCheckBox _chk_caja_alfa = null;
	private JCheckBox _chk_beta = null;
	private JTextField _txt_saldo_alfa = null;
	private JLabel jLabel8 = null;
	private JTextField _txt_saldo_total = null;
	private JPanel jPanel = null;
	private JTabbedPane jTabbedPane2 = null;
	private JScrollPane jScrollPane2 = null;
	private JTable jTable_efectivo_alfa = null;
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
		this.setContentPane(getJContentPane());
		this.setTitle("Gestion de Caja");
		this.setBounds(new Rectangle(0, 0, 1024, 750));
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel8 = new JLabel();
			jLabel8.setBounds(new Rectangle(525, 450, 67, 20));
			jLabel8.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel8.setText("Saldo Total");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(getJProgressBar(), null);
			jContentPane.add(getJTabbedPane(), null);
			jContentPane.add(getJPanel2(), null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(get_txt_suma_saldo(), null);
			jContentPane.add(jLabel4, null);
			jContentPane.add(get_txt_diferencia(), null);
			//jContentPane.add(get_btn_ajustar(), null);
			jContentPane.add(get_chk_caja_alfa(), null);
			jContentPane.add(get_chk_beta(), null);
			jContentPane.add(get_txt_saldo_alfa(), null);
			jContentPane.add(jLabel8, null);
			jContentPane.add(get_txt_saldo_total(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(get_lst_cajas(), null);
			jContentPane.add(get_txt_caja_detalle(), null);
			
			jContentPane.add(jLabel6, null);
			jContentPane.add(get_txt_desde(), null);
			jContentPane.add(get_btn_fecha_desde(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(get_txt_hasta(), null);
			jContentPane.add(get_btn_fecha_hasta(), null);
			jContentPane.add(get_txt_saldo(), null);
		}
		return jContentPane;
	}



	/**
	 * This method initializes _lst_cajas	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_cajas() {
		if (_lst_cajas == null) {
			_lst_cajas = new JComboBox();
			_lst_cajas.setFont(new Font("Dialog", Font.PLAIN, 10));
			_lst_cajas.setBounds(new Rectangle(64, 61, 92, 19));
		}
		return _lst_cajas;
	}

	/**
	 * This method initializes _txt_hasta	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_hasta() {
		if (_txt_hasta == null) {
			_txt_hasta = new JTextField();
			_txt_hasta.setHorizontalAlignment(JTextField.RIGHT);
			_txt_hasta.setBounds(new Rectangle(822, 61, 120, 19));
			_txt_hasta.setFont(new Font("Dialog", Font.PLAIN, 10));
			
		}
		return _txt_hasta;
	}

	/**
	 * This method initializes _btn_cargar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cargar() {
		if (_btn_cargar == null) {
			_btn_cargar = new JButton();
			_btn_cargar.setIcon(new ImageIcon(getClass().getResource("/icons/stock_refresh.png")));
			_btn_cargar.setToolTipText("Cargar");
		}
		return _btn_cargar;
	}

	/**
	 * This method initializes _btn_egreso	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_egreso() {
		if (_btn_egreso == null) {
			_btn_egreso = new JButton();
			_btn_egreso.setIcon(new ImageIcon(getClass().getResource("/icons/remove.png")));
			_btn_egreso.setToolTipText("Nuevo Egreso");
		}
		return _btn_egreso;
	}

	/**
	 * This method initializes _btn_ingreso	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_ingreso() {
		if (_btn_ingreso == null) {
			_btn_ingreso = new JButton();
			_btn_ingreso.setIcon(new ImageIcon(getClass().getResource("/icons/add.png")));
			_btn_ingreso.setToolTipText("Nuevo Ingreso");
		}
		return _btn_ingreso;
	}

	/**
	 * This method initializes _txt_caja_detalle	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_caja_detalle() {
		if (_txt_caja_detalle == null) {
			_txt_caja_detalle = new JTextField();
			_txt_caja_detalle.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_caja_detalle.setBounds(new Rectangle(162, 61, 156, 19));
			_txt_caja_detalle.setEditable(false);
		}
		return _txt_caja_detalle;
	}

	/**
	 * This method initializes _btn_transferencia	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_transferencia() {
		if (_btn_transferencia == null) {
			_btn_transferencia = new JButton();
			_btn_transferencia.setToolTipText("Nueva Transferencia");
			_btn_transferencia.setIcon(new ImageIcon(getClass().getResource("/icons/mail-send-receive.png")));
		}
		return _btn_transferencia;
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
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {

			jLabel6 = new JLabel();
			jLabel6.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel6.setBounds(new Rectangle(521, 63, 77, 17));
			jLabel6.setText("Fecha Hasta");
			jLabel1 = new JLabel();
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setBounds(new Rectangle(775, 61, 44, 19));
			jLabel1.setText("Hasta");
			jLabel = new JLabel();
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setBounds(new Rectangle(5, 59, 55, 20));
			jLabel.setText("Caja");
			
			jLabel4 = new JLabel();
			jLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel4.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel4.setBounds(new Rectangle(501, 507, 82, 19));
			jLabel4.setText("Diferencia");
			jLabel3 = new JLabel();
			jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel3.setBounds(new Rectangle(507, 479, 76, 20));
			jLabel3.setText("Arqueo");
			jPanel1 = new JPanel();
			jPanel1.setLayout(null);
			jPanel1.setBackground(new Color(102, 102, 102));
			
			jPanel1.add(getJTabbedPane1(), null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	public JScrollPane getJScrollPane() {
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
	
	public void setJTable(JTable table) {
		this.jTable=table;
		this.jScrollPane.setViewportView(jTable);	
	}

	
	/**
	 * This method initializes _txt_saldo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_saldo() {
		if (_txt_saldo == null) {
			_txt_saldo = new JTextField();
			_txt_saldo.setHorizontalAlignment(JTextField.RIGHT);
			_txt_saldo.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_saldo.setBounds(new Rectangle(601, 421, 120, 19));
			_txt_saldo.setEditable(false);
		}
		return _txt_saldo;
	}

	/**
	 * This method initializes _btn_cheques	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cheques() {
		if (_btn_cheques == null) {
			_btn_cheques = new JButton();
			_btn_cheques.setToolTipText("Cheques en cartera");
			_btn_cheques.setIcon(new ImageIcon(getClass().getResource("/icons/fileopen.png")));
		}
		return _btn_cheques;
	}

	/**
	 * This method initializes _btn_reporte	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_reporte() {
		if (_btn_reporte == null) {
			_btn_reporte = new JButton();
			_btn_reporte.setIcon(new ImageIcon(getClass().getResource("/icons/stock_add-bookmark.png")));
			
			_btn_reporte.setToolTipText("Reporte de Caja");
		}
		return _btn_reporte;
	}

	/**
	 * This method initializes jPanel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jLabel7 = new JLabel();
			jLabel7.setBounds(new Rectangle(277, 8, 131, 15));
			jLabel7.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
			jLabel7.setText("ARQUEO DE CAJA");
			jPanel2 = new JPanel();
			jPanel2.setLayout(null);
			jPanel2.setBackground(Color.lightGray);
			jPanel2.setBounds(new Rectangle(20, 394, 426, 268));
			jPanel2.add(getJScrollPane1(), null);
			jPanel2.add(get_txt_arqueo(), null);
			jPanel2.add(jLabel7, null);
		}
		return jPanel2;
	}

	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setBounds(new Rectangle(14, 34, 393, 201));
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

	public void setJTable1(JTable jtable){
		this.jTable1=jtable;
		this.jScrollPane1.setViewportView(jTable1);
	}

	/**
	 * This method initializes _txt_arqueo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_arqueo() {
		if (_txt_arqueo == null) {
			

			_txt_arqueo = new JTextField();
			_txt_arqueo.setBounds(new Rectangle(299, 242, 107, 19));
			_txt_arqueo.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_arqueo.setHorizontalAlignment(JTextField.RIGHT);
			_txt_arqueo.setEditable(false);
		}
		return _txt_arqueo;
	}

	/**
	 * This method initializes _txt_suma_saldo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_suma_saldo() {
		if (_txt_suma_saldo == null) {
			_txt_suma_saldo = new JTextField();
			_txt_suma_saldo.setHorizontalAlignment(JTextField.RIGHT);
			_txt_suma_saldo.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_suma_saldo.setBounds(new Rectangle(601, 479, 123, 19));
			_txt_suma_saldo.setEditable(false);
		}
		return _txt_suma_saldo;
	}

	/**
	 * This method initializes _txt_diferencia	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_diferencia() {
		if (_txt_diferencia == null) {
			_txt_diferencia = new JTextField();
			_txt_diferencia.setHorizontalAlignment(JTextField.RIGHT);
			_txt_diferencia.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_diferencia.setBounds(new Rectangle(598, 508, 125, 18));
			_txt_diferencia.setEditable(false);
		}
		return _txt_diferencia;
	}

	/**
	 * This method initializes _btn_ajustar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_ajustar() {
		if (_btn_ajustar == null) {
			_btn_ajustar = new JButton();
			_btn_ajustar.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-run.png")));
			_btn_ajustar.setBounds(new Rectangle(703, 521, 22, 22));
			_btn_ajustar.setToolTipText("Ajustar Diferencia");
		}
		return _btn_ajustar;
	}

	/**
	 * This method initializes _btn_canje	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_canje() {
		if (_btn_canje == null) {
			_btn_canje = new JButton();
			_btn_canje.setToolTipText("Canje Efectivo/Cheques");
			_btn_canje.setIcon(new ImageIcon(getClass().getResource("/icons/cash-icon.png")));
		}
		return _btn_canje;
	}



	/**
	 * This method initializes _btn_movimientos	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_movimientos() {
		if (_btn_movimientos == null) {
			_btn_movimientos = new JButton();
			_btn_movimientos.setIcon(new ImageIcon(getClass().getResource("/icons/file-manager-24.png")));
			_btn_movimientos.setToolTipText("ver movimientos de cuentas");
		}
		return _btn_movimientos;
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
			jToolBar.setBounds(new Rectangle(5, 3, 1006, 26));
			jToolBar.add(get_btn_cargar());
			jToolBar.add(get_btn_egreso());
			jToolBar.add(get_btn_ingreso());
			jToolBar.add(get_btn_transferencia());
			jToolBar.add(get_btn_cheques());
			jToolBar.addSeparator();
			jToolBar.add(get_btn_cobranza());
			jToolBar.add(get_btn_pago());
			jToolBar.add(get_btn_carga_venta());
			jToolBar.addSeparator();
			jToolBar.add(get_btn_rechazados());
			jToolBar.add(get_btn_reporte());
			jToolBar.addSeparator();
			jToolBar.add(get_btn_canje());
			jToolBar.add(get_btn_movimientos());
			jToolBar.addSeparator();
			jToolBar.add(get_btn_salir());
			jToolBar.addSeparator();
			jToolBar.add(get_btn_error());
		}
		return jToolBar;
	}

	/**
	 * This method initializes _btn_rechazados	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_rechazados() {
		if (_btn_rechazados == null) {
			_btn_rechazados = new JButton();
			_btn_rechazados.setIcon(new ImageIcon(getClass().getResource("/icons/edit-redo.png")));
			_btn_rechazados.setEnabled(false);
			_btn_rechazados.setToolTipText("Ingresar Cheque Rechazado");
		}
		return _btn_rechazados;
	}

	/**
	 * This method initializes jProgressBar	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	public JProgressBar getJProgressBar() {
		if (jProgressBar == null) {
			jProgressBar = new JProgressBar();
			jProgressBar.setBounds(new Rectangle(5, 33, 1006, 19));
			jProgressBar.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return jProgressBar;
	}

	/**
	 * This method initializes _btn_cobranza	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cobranza() {
		if (_btn_cobranza == null) {
			_btn_cobranza = new JButton();
			_btn_cobranza.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-media-play-ltr.png")));
			_btn_cobranza.setToolTipText("Efecturar Cobranza");
		}
		return _btn_cobranza;
	}

	/**
	 * This method initializes _btn_pago	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_pago() {
		if (_btn_pago == null) {
			_btn_pago = new JButton();
			
			_btn_pago.setIcon(new ImageIcon(getClass().getResource("/icons/media-playback-stop.png")));
			_btn_pago.setToolTipText("Efecturar Pago a Proveedor");
			
		}
		return _btn_pago;
	}

	/**
	 * This method initializes _btn_carga_venta	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_carga_venta() {
		if (_btn_carga_venta == null) {
			_btn_carga_venta = new JButton();
			_btn_carga_venta.setMnemonic(KeyEvent.VK_UNDEFINED);
			_btn_carga_venta.setToolTipText("Carga Comprobante de Venta");
			_btn_carga_venta.setIcon(new ImageIcon(getClass().getResource("/icons/stock_undo.png")));
		}
		return _btn_carga_venta;
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
	 * This method initializes _txt_desde	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_desde() {
		if (_txt_desde == null) {
			_txt_desde = new JTextField();
			_txt_desde.setHorizontalAlignment(JTextField.RIGHT);
			_txt_desde.setBounds(new Rectangle(605, 62, 120, 19));
			_txt_desde.setFont(new Font("Dialog", Font.PLAIN, 10));
			
		}
		return _txt_desde;
	}

	/**
	 * This method initializes _btn_fecha_desde	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_fecha_desde() {
		if (_btn_fecha_desde == null) {
			_btn_fecha_desde = new JButton();
			_btn_fecha_desde.setIcon(new ImageIcon(getClass().getResource("/icons/calendar-16.gif")));
			_btn_fecha_desde.setBounds(new Rectangle(728, 61, 23, 20));
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
			_btn_fecha_hasta.setIcon(new ImageIcon(getClass().getResource("/icons/calendar-16.gif")));
			_btn_fecha_hasta.setBounds(new Rectangle(946, 61, 23, 20));
		}
		return _btn_fecha_hasta;
	}

	/**
	 * This method initializes jTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.setBounds(new Rectangle(6, 108, 1003, 271));
			jTabbedPane.addTab("Beta", null, getJPanel1(), null);
			jTabbedPane.addTab("Alfa", null, getJPanel(), null);
		}
		return jTabbedPane;
	}

	/**
	 * This method initializes jTabbedPane1	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	public JTabbedPane getJTabbedPane1() {
		if (jTabbedPane1 == null) {
			jTabbedPane1 = new JTabbedPane();
			jTabbedPane1.setBounds(new Rectangle(6, 4, 980, 230));
			jTabbedPane1.addTab("Efectivo", null, getJScrollPane(), null);
			jTabbedPane1.addTab("Dolares", null, getJScrollPane4(), null);
			jTabbedPane1.addTab("Cheques", null, getJScrollPane3(), null);
		}
		return jTabbedPane1;
	}

	/**
	 * This method initializes jScrollPane3	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane3() {
		if (jScrollPane3 == null) {
			jScrollPane3 = new JScrollPane();
			jScrollPane3.setViewportView(getJTable3());
		}
		return jScrollPane3;
	}

	/**
	 * This method initializes jTable3	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getJTable3() {
		if (jTable3 == null) {
			jTable3 = new JTable();
		}
		return jTable3;
	}

	public void setJTable3(JTable table) {
			jTable3 = table;
			this.jScrollPane3.setViewportView(jTable3);
		
	}
	
	public void setJTable4(JTable table) {
		jTable4 = table;
		this.jScrollPane4.setViewportView(jTable4);
	
	}
	/**
	 * This method initializes jScrollPane4	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane4() {
		if (jScrollPane4 == null) {
			jScrollPane4 = new JScrollPane();
			jScrollPane4.setViewportView(getJTable4());
		}
		return jScrollPane4;
	}

	/**
	 * This method initializes jTable4	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getJTable4() {
		if (jTable4 == null) {
			jTable4 = new JTable();
		}
		return jTable4;
	}

	/**
	 * This method initializes _chk_caja_alfa	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_caja_alfa() {
		if (_chk_caja_alfa == null) {
			_chk_caja_alfa = new JCheckBox();
			_chk_caja_alfa.setBounds(new Rectangle(515, 396, 83, 17));
			_chk_caja_alfa.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_caja_alfa.setSelected(true);
			_chk_caja_alfa.setEnabled(false);
			_chk_caja_alfa.setText("Caja Alfa");
		}
		return _chk_caja_alfa;
	}

	/**
	 * This method initializes _chk_beta	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_beta() {
		if (_chk_beta == null) {
			_chk_beta = new JCheckBox();
			_chk_beta.setBounds(new Rectangle(515, 421, 80, 22));
			_chk_beta.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_beta.setSelected(true);
			_chk_beta.setText("Caja Beta");
			_chk_beta.setEnabled(false);
		}
		return _chk_beta;
	}

	/**
	 * This method initializes _txt_saldo_alfa	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_saldo_alfa() {
		if (_txt_saldo_alfa == null) {
			_txt_saldo_alfa = new JTextField();
			_txt_saldo_alfa.setLocation(new Point(601, 394));
			_txt_saldo_alfa.setEditable(false);
			_txt_saldo_alfa.setHorizontalAlignment(JTextField.RIGHT);
			_txt_saldo_alfa.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_saldo_alfa.setSize(new Dimension(121, 20));
		}
		return _txt_saldo_alfa;
	}

	/**
	 * This method initializes _txt_saldo_total	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_saldo_total() {
		if (_txt_saldo_total == null) {
			_txt_saldo_total = new JTextField();
			_txt_saldo_total.setLocation(new Point(601, 450));
			_txt_saldo_total.setEditable(false);
			_txt_saldo_total.setHorizontalAlignment(JTextField.RIGHT);
			_txt_saldo_total.setFont(new Font("Dialog", Font.BOLD, 10));
			_txt_saldo_total.setSize(new Dimension(121, 20));
		}
		return _txt_saldo_total;
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
			jPanel.add(getJTabbedPane2(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jTabbedPane2	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJTabbedPane2() {
		if (jTabbedPane2 == null) {
			jTabbedPane2 = new JTabbedPane();
			jTabbedPane2.setBounds(new Rectangle(9, 8, 975, 221));
			jTabbedPane2.addTab("Efectivo", null, getJScrollPane2(), null);
		}
		return jTabbedPane2;
	}

	/**
	 * This method initializes jScrollPane2	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane2() {
		if (jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setViewportView(getJTable_efectivo_alfa());
		}
		return jScrollPane2;
	}

	/**
	 * This method initializes jTable_efectivo_alfa	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getJTable_efectivo_alfa() {
		if (jTable_efectivo_alfa == null) {
			jTable_efectivo_alfa = new JTable();
		}
		return jTable_efectivo_alfa;
	}
	
	public void setJTable_efectivo_alfa(JTable table) {
		this.jTable_efectivo_alfa=table;
		this.jScrollPane2.setViewportView(this.jTable_efectivo_alfa);
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
