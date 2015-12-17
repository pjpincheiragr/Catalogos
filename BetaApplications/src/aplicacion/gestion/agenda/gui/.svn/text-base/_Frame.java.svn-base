package aplicacion.gestion.agenda.gui;


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
import javax.swing.JTextArea;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel jContentPane = null;

	private JPanel jPanel = null;

	private JTable jTableMedios = null;
	private JTable jTableCpte = null;
	private JTable jTableOPG = null;
	private JButton _btn_cancelar = null;

	private JToolBar jToolBar = null;

	private JButton _btn_salir = null;

	private JButton _btn_error = null;

	private JButton _btn_guardar = null;

	private JTextField _txt_idevento = null;

	private JLabel jLabel = null;

	private JTextField _txt_titulo = null;

	private JTextField _txt_idvendedor = null;

	private JTextField _txt_vendedor_descripcion = null;

	private JButton _btn_nuevo = null;

	private JLabel jLabel2 = null;

	private JLabel jLabel1 = null;

	private JScrollPane jScrollPane = null;

	private JTextArea _txt_mensaje = null;

	private JLabel jLabel6 = null;

	private JTextField _txt_agenda = null;

	private JButton _btn_fecha = null;

	private JLabel jLabel7 = null;

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
		this.setSize(510, 324);
		this.setResizable(false);
		this.setContentPane(getJContentPane());
		this.setTitle("Agenda");
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
			jLabel7 = new JLabel();
			jLabel7.setBounds(new Rectangle(185, 14, 94, 17));
			jLabel7.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel7.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel7.setText("Agenda");
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(5, 100, 55, 19));
			jLabel6.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel6.setText("Mensaje");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(7, 75, 56, 16));
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setText("Titulo");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(7, 38, 56, 17));
			jLabel2.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel2.setText("idusuario");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(7, 14, 57, 18));
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setText("idevento");
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBounds(new Rectangle(6, 31, 488, 258));
			jPanel.setBackground(Color.LIGHT_GRAY);
			jPanel.add(get_txt_idevento(), null);
			jPanel.add(jLabel, null);
			jPanel.add(get_txt_idvendedor(), null);
			jPanel.add(get_txt_vendedor_descripcion(), null);
			jPanel.add(jLabel2, null);
			jPanel.add(get_txt_titulo(), null);
			jPanel.add(jLabel1, null);
			jPanel.add(getJScrollPane(), null);
			jPanel.add(jLabel6, null);
			jPanel.add(get_txt_agenda(), null);
			jPanel.add(get_btn_fecha(), null);
			jPanel.add(jLabel7, null);
			
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
			jToolBar.setBounds(new Rectangle(6, 4, 488, 21));
			jToolBar.add(get_btn_cancelar());
			jToolBar.add(get_btn_nuevo());
			jToolBar.add(get_btn_guardar());
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
	 * This method initializes _btn_guardar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_guardar() {
		if (_btn_guardar == null) {
			_btn_guardar = new JButton();
			_btn_guardar.setIcon(new ImageIcon(getClass().getResource("/icons/document-save.png")));
			_btn_guardar.setToolTipText("Guardar");
		}
		return _btn_guardar;
	}

	/**
	 * This method initializes _txt_idevento	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idevento() {
		if (_txt_idevento == null) {
			_txt_idevento = new JTextField();
			_txt_idevento.setBounds(new Rectangle(65, 13, 98, 20));
			_txt_idevento.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_idevento;
	}

	/**
	 * This method initializes _txt_titulo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_titulo() {
		if (_txt_titulo == null) {
			_txt_titulo = new JTextField();
			_txt_titulo.setBounds(new Rectangle(66, 72, 397, 21));
			_txt_titulo.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_titulo;
	}

	/**
	 * This method initializes _txt_idvendedor	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idvendedor() {
		if (_txt_idvendedor == null) {
			_txt_idvendedor = new JTextField();
			_txt_idvendedor.setBounds(new Rectangle(66, 38, 54, 19));
			_txt_idvendedor.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_idvendedor;
	}

	/**
	 * This method initializes _txt_vendedor_descripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_vendedor_descripcion() {
		if (_txt_vendedor_descripcion == null) {
			_txt_vendedor_descripcion = new JTextField();
			_txt_vendedor_descripcion.setBounds(new Rectangle(124, 39, 167, 17));
			_txt_vendedor_descripcion.setEditable(false);
			_txt_vendedor_descripcion.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_vendedor_descripcion;
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
		}
		return _btn_nuevo;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(66, 100, 395, 120));
			jScrollPane.setViewportView(get_txt_mensaje());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes _txt_mensaje	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea get_txt_mensaje() {
		if (_txt_mensaje == null) {
			_txt_mensaje = new JTextArea();
			_txt_mensaje.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_mensaje;
	}

	/**
	 * This method initializes _txt_agenda	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_agenda() {
		if (_txt_agenda == null) {
			_txt_agenda = new JTextField();
			_txt_agenda.setBounds(new Rectangle(283, 14, 159, 17));
			_txt_agenda.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_agenda;
	}

	/**
	 * This method initializes _btn_fecha	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_fecha() {
		if (_btn_fecha == null) {
			_btn_fecha = new JButton();
			_btn_fecha.setBounds(new Rectangle(444, 14, 20, 18));
			_btn_fecha.setIcon(new ImageIcon(getClass().getResource("/icons/calendar-16.gif")));
		}
		return _btn_fecha;
	}
	
		
}  //  @jve:decl-index=0:visual-constraint="10,10"
