package aplicacion.ventas.reporte.gui;


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

	private JTable jTableMedios = null;
	private JTable jTableCpte = null;
	private JTable jTableOPG = null;
	private JButton _btn_cancelar = null;

	private JToolBar jToolBar = null;

	private JLabel jLabel6 = null;

	private JComboBox _list_reportes = null;

	private JButton _btn_salir = null;

	private JTextField _txt_fecha = null;

	private JButton _btn_error = null;

	private JButton _btn_reporte = null;

	private JLabel jLabel1 = null;

	private JButton _btn_fecha_desde = null;

	private JLabel jLabel2 = null;

	private JTextField _txt_fecha_hasta = null;

	private JButton _btn_fecha_hasta = null;

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
		this.setSize(532, 149);
		this.setResizable(false);
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
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJPanel(), null);
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
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(235, 32, 78, 19));
			jLabel2.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel2.setText("Hasta");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(8, 34, 72, 17));
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setText("Fecha Desde");
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(16, 6, 65, 18));
			jLabel6.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel6.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel6.setText("Reporte");
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBounds(new Rectangle(6, 31, 515, 73));
			jPanel.setBackground(Color.LIGHT_GRAY);
			jPanel.add(jLabel6, null);
			jPanel.add(get_list_reportes(), null);
			jPanel.add(get_txt_fecha(), null);
			jPanel.add(jLabel1, null);
			jPanel.add(get_btn_fecha_desde(), null);
			jPanel.add(jLabel2, null);
			jPanel.add(get_txt_fecha_hasta(), null);
			jPanel.add(get_btn_fecha_hasta(), null);
			
		}
		return jPanel;
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
			jToolBar.setBounds(new Rectangle(6, 4, 516, 21));
			jToolBar.add(get_btn_cancelar());
			jToolBar.add(get_btn_reporte());
			jToolBar.add(get_btn_salir());
			jToolBar.add(get_btn_error());
		}
		return jToolBar;
	}

	/**
	 * This method initializes _btn_buscar_caja	
	 * 	
	 * @return javax.swing.JButton	
	 */
	
	public JComboBox get_list_reportes(){
	/**
	 * This method initializes _list_cajas	
	 * 	
	 * @return javax.swing.JComboBox	
	 * 
	 */			
		if (_list_reportes==null) {
			_list_reportes = new JComboBox();
			_list_reportes.setBounds(new Rectangle(85, 7, 368, 18));
			_list_reportes.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _list_reportes;
	}

	/**
	 * This method initializes _txt_fecha	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_fecha() {
		if (_txt_fecha == null) {
			_txt_fecha = new JTextField();
			_txt_fecha.setBounds(new Rectangle(86, 33, 115, 18));
			_txt_fecha.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_fecha;
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
	 * This method initializes _btn_fecha_desde	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_fecha_desde() {
		if (_btn_fecha_desde == null) {
			_btn_fecha_desde = new JButton();
			_btn_fecha_desde.setBounds(new Rectangle(203, 33, 20, 19));
			_btn_fecha_desde.setIcon(new ImageIcon(getClass().getResource("/icons/calendar-16.gif")));
		}
		return _btn_fecha_desde;
	}

	/**
	 * This method initializes _txt_fecha_hasta	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_fecha_hasta() {
		if (_txt_fecha_hasta == null) {
			_txt_fecha_hasta = new JTextField();
			_txt_fecha_hasta.setBounds(new Rectangle(316, 32, 115, 18));
		}
		return _txt_fecha_hasta;
	}

	/**
	 * This method initializes _btn_fecha_hasta	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_fecha_hasta() {
		if (_btn_fecha_hasta == null) {
			_btn_fecha_hasta = new JButton();
			_btn_fecha_hasta.setBounds(new Rectangle(435, 31, 20, 20));
			_btn_fecha_hasta.setIcon(new ImageIcon(getClass().getResource("/icons/calendar-16.gif")));
		}
		return _btn_fecha_hasta;
	}
	
		
}  //  @jve:decl-index=0:visual-constraint="10,10"
