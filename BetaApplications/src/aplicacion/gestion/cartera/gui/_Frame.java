package aplicacion.gestion.cartera.gui;


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

	private JPanel jPanel2 = null;
	private JScrollPane jScrollPaneMedios = null;
	
	private JTable jTableMedios = null;
	private JTextField _txt_total_cheques = null;
	private JLabel jLabel3 = null;
	private JTable jTableCpte = null;
	private JPanel jPanel3 = null;
	private JTable jTableOPG = null;
	private JLabel jLabel8 = null;

	private JButton _btn_cancelar = null;

	private JToolBar jToolBar = null;

	private JLabel jLabel6 = null;

	private JComboBox _list_cajas = null;

	private JButton _btn_mostrar = null;
	private JButton _btn_salir = null;

	private JTextField _txt_fecha = null;

	private JTextField _txt_caja_detalle = null;

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
		this.setSize(866, 487);
		this.setContentPane(getJContentPane());
		this.setTitle("Cheques en Cartera");
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
			jLabel3.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3.setBounds(new Rectangle(569, 5, 140, 19));
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
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(11, 4, 65, 18));
			jLabel6.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel6.setText("Caja");
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBounds(new Rectangle(6, 31, 834, 34));
			jPanel.setBackground(Color.LIGHT_GRAY);
			jPanel.add(jLabel6, null);
			jPanel.add(get_list_cajas(), null);
			jPanel.add(get_btn_mostrar(), null);
			jPanel.add(get_txt_fecha(), null);
			jPanel.add(get_txt_caja_detalle(), null);
			
		}
		return jPanel;
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
			jLabel8.setText("Listado de Cheques");
			jLabel8.setBackground(Color.black);
			jLabel8.setForeground(Color.blue);
			Font fuente=new Font("Arial", Font.ITALIC, 13);
			jLabel8.setFont(fuente);
			jLabel8.setHorizontalAlignment(JTextField.RIGHT);
			jPanel2 = new JPanel();
			jPanel2.setLayout(null);
			jPanel2.setBackground(Color.LIGHT_GRAY);
			jPanel2.setBounds(new Rectangle(8, 77, 834, 316));
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
	
	public void setJTableMedios(JTable table){
		this.jTableMedios=table;
		this.jScrollPaneMedios.setViewportView(this.jTableMedios);
	}
	/**
	 * This method initializes _txt_total_cheques	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_total_cheques() {
		if (_txt_total_cheques == null) {
			_txt_total_cheques = new JTextField();
			_txt_total_cheques.setEditable(false);
			_txt_total_cheques.setHorizontalAlignment(JTextField.RIGHT);
			_txt_total_cheques.setText("0.00");
			_txt_total_cheques.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_total_cheques.setBounds(new Rectangle(717, 4, 107, 18));
		}
		return _txt_total_cheques;
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
			jPanel3.setBounds(new Rectangle(8, 400, 833, 30));
			jPanel3.add(jLabel3, null);
			jPanel3.add(get_txt_total_cheques(), null);
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
			_btn_cancelar.setIcon(new ImageIcon(resourceURL));
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
	
	public JComboBox get_list_cajas(){
	/**
	 * This method initializes _list_cajas	
	 * 	
	 * @return javax.swing.JComboBox	
	 * 
	 */			
		if (_list_cajas==null) {
			_list_cajas = new JComboBox();
			_list_cajas.setBounds(new Rectangle(79, 5, 105, 18));
			_list_cajas.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _list_cajas;
	}

	/**
	 * This method initializes _btn_mostrar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_mostrar() {
		if (_btn_mostrar == null) {
			_btn_mostrar = new JButton();
			_btn_mostrar.setBounds(new Rectangle(368, 5, 31, 21));
			_btn_mostrar.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-refresh.png")));_btn_mostrar.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-refresh.png")));_btn_mostrar.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-refresh.png")));
		}
		return _btn_mostrar;
	}

	/**
	 * This method initializes _txt_fecha	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_fecha() {
		if (_txt_fecha == null) {
			_txt_fecha = new JTextField();
			_txt_fecha.setBounds(new Rectangle(679, 7, 147, 18));
			_txt_fecha.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_fecha;
	}

	/**
	 * This method initializes _txt_caja_detalle	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_caja_detalle() {
		if (_txt_caja_detalle == null) {
			_txt_caja_detalle = new JTextField();
			_txt_caja_detalle.setBounds(new Rectangle(190, 6, 172, 19));
			_txt_caja_detalle.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_caja_detalle.setEditable(false);
		}
		return _txt_caja_detalle;
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
