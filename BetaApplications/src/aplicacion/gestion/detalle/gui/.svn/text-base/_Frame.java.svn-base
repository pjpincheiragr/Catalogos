package aplicacion.gestion.detalle.gui;


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

	private JPanel jPanel2 = null;
	private JScrollPane jScrollPaneMedios = null;
	
	private JTable jTableMedios = null;
	private JTextField _txt_saldo = null;
	private JLabel jLabel3 = null;
	private JTable jTableCpte = null;
	private JPanel jPanel3 = null;
	private JTable jTableOPG = null;
	private JLabel jLabel8 = null;

	private JButton _btn_cancelar = null;

	private JToolBar jToolBar = null;

	private JButton _btn_salir = null;

	private JButton _btn_error = null;

	private JButton _btn_reporte = null;

	private JTextField _txt_haber = null;

	private JTextField _txt_debe = null;

	private JLabel jLabel4 = null;

	private JLabel jLabel5 = null;

	private JButton _btn_transferir = null;

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
		this.setSize(858, 447);
		this.setContentPane(getJContentPane());
		this.setTitle("Examinar Movimientos en Cuenta");
	}
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("Saldo");
			jLabel3.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3.setBounds(new Rectangle(711, 2, 105, 19));
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJPanel3(), null);
			jContentPane.add(getJPanel2(), null);
			jContentPane.add(getJToolBar(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jPanel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jLabel8 = new JLabel();
			jLabel8.setBounds(new Rectangle(631, 2, 188, 18));
			jLabel8.setText("Listado de Movimientos");
			jLabel8.setBackground(Color.black);
			jLabel8.setForeground(Color.blue);
			Font fuente=new Font("Arial", Font.ITALIC, 13);
			jLabel8.setFont(fuente);
			jLabel8.setHorizontalAlignment(JTextField.RIGHT);
			jPanel2 = new JPanel();
			jPanel2.setLayout(null);
			jPanel2.setBackground(Color.LIGHT_GRAY);
			jPanel2.setBounds(new Rectangle(7, 30, 834, 316));
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
			jScrollPaneMedios.setBounds(new Rectangle(12, 23, 810, 281));
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
	
	public void setJTable(JTable table){
		this.jTableMedios=table;
		this.jScrollPaneMedios.setViewportView(this.jTableMedios);
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
			_txt_saldo.setHorizontalAlignment(JTextField.RIGHT);
			_txt_saldo.setText("0.00");
			_txt_saldo.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_saldo.setBounds(new Rectangle(711, 25, 110, 18));
		}
		return _txt_saldo;
	}
	

	/**
	 * This method initializes jPanel3	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(613, 4, 91, 17));
			jLabel5.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel5.setText("haber");
			jLabel5.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(499, 5, 96, 16));
			jLabel4.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel4.setText("debe");
			jPanel3 = new JPanel();
			jPanel3.setLayout(null);
			jPanel3.setBackground(Color.LIGHT_GRAY);
			jPanel3.setBounds(new Rectangle(7, 351, 833, 48));
			jPanel3.add(jLabel3, null);
			jPanel3.add(get_txt_saldo(), null);
			jPanel3.add(get_txt_haber(), null);
			jPanel3.add(get_txt_debe(), null);
			jPanel3.add(jLabel4, null);
			jPanel3.add(jLabel5, null);
		}
		return jPanel3;
	}

	public void setMedioFocus(){
		this.jTableMedios.requestFocusInWindow();
		this.jTableMedios.changeSelection(0,0,false,false);
		this.jTableMedios.editCellAt(0,0);
		this.jTableMedios.transferFocus();
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
			_btn_cancelar.setIcon(new ImageIcon(getClass().getResource("/icons/stock_calc-cancel.png")));
		}
		return _btn_cancelar;
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
			jToolBar.setBounds(new Rectangle(6, 4, 836, 21));
			jToolBar.add(get_btn_cancelar());
			jToolBar.add(get_btn_reporte());
			jToolBar.add(get_btn_transferir());
			jToolBar.add(get_btn_salir());
			jToolBar.add(get_btn_error());
		}
		return jToolBar;
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
	 * This method initializes _btn_reporte	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_reporte() {
		if (_btn_reporte == null) {
			_btn_reporte = new JButton();
			_btn_reporte.setIcon(new ImageIcon(getClass().getResource("/icons/document-print.png")));
			_btn_reporte.setToolTipText("Reporte");
		}
		return _btn_reporte;
	}

	/**
	 * This method initializes _txt_haber	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_haber() {
		if (_txt_haber == null) {
			_txt_haber = new JTextField();
			_txt_haber.setBounds(new Rectangle(597, 25, 110, 18));
			_txt_haber.setHorizontalAlignment(JTextField.RIGHT);
			_txt_haber.setEditable(false);
		}
		return _txt_haber;
	}

	/**
	 * This method initializes _txt_debe	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_debe() {
		if (_txt_debe == null) {
			_txt_debe = new JTextField();
			_txt_debe.setBounds(new Rectangle(484, 25, 110, 18));
			_txt_debe.setHorizontalAlignment(JTextField.RIGHT);
			_txt_debe.setEditable(false);
		}
		return _txt_debe;
	}

	/**
	 * This method initializes _btn_transferir	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_transferir() {
		if (_btn_transferir == null) {
			_btn_transferir = new JButton();
			_btn_transferir.setIcon(new ImageIcon(getClass().getResource("/icons/finish.png")));
			_btn_transferir.setEnabled(false);
			_btn_transferir.setToolTipText("Transferir Movimientos a Otra Cuenta");
		}
		return _btn_transferir;
	}
	
		
}  //  @jve:decl-index=0:visual-constraint="10,10"
