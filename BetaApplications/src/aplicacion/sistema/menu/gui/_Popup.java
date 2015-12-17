package aplicacion.sistema.menu.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JTextField;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Point;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JTabbedPane;
import java.awt.ComponentOrientation;
import javax.swing.JMenu;

public class _Popup extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JTextField _txt_callerid = null;
	private JLabel jLabel = null;
	private JToolBar jJToolBarBar = null;
	private JButton _btn_guardar = null;
	private JButton _btn_minimizar = null;
	private JPanel jPanel_Asterisk = null;
	private JTabbedPane jTabbedPane = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private JButton _btn_cargar = null;
	private JPanel jPanel_principal = null;
	private JPanel jPanel_acerca = null;
	private JPanel jPanel_agenda = null;
	private JPanel jPanel_messenger = null;
	private JLabel jLabel1 = null;
	/**
	 * This is the default constructor
	 */
	public _Popup() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(536, 298);
		this.setUndecorated(true);
		this.setContentPane(getJContentPane());
		this.setTitle("Beta");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(2, 0, 525, 28));
			jLabel1.setFont(new Font("Dialog", Font.BOLD, 18));
			jLabel1.setForeground(Color.white);
			jLabel1.setIcon(new ImageIcon(getClass().getResource("/icons/chrome.png")));
			jLabel1.setText("Beta RC 2010");
			jLabel = new JLabel();
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setBounds(new Rectangle(4, 35, 46, 17));
			jLabel.setText("Caller ID");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.setBackground(new Color(204, 204, 204));
			jContentPane.add(getJTabbedPane(), null);
			jContentPane.add(get_btn_minimizar(), null);
			jContentPane.add(jLabel1, null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes _txt_callerid	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_callerid() {
		if (_txt_callerid == null) {
			_txt_callerid = new JTextField();
			_txt_callerid.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_callerid.setEditable(true);
			_txt_callerid.setBounds(new Rectangle(53, 35, 111, 18));
			_txt_callerid.setToolTipText("Numero de Telefono");
			_txt_callerid.setHorizontalAlignment(JTextField.RIGHT);
		}
		return _txt_callerid;
	}

	/**
	 * This method initializes jJToolBarBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJJToolBarBar() {
		if (jJToolBarBar == null) {
			jJToolBarBar = new JToolBar();
			jJToolBarBar.setFloatable(false);
			
			jJToolBarBar.setOrientation(JToolBar.HORIZONTAL);
			jJToolBarBar.setSize(new Dimension(517, 26));
			jJToolBarBar.setLocation(new Point(0, 0));
			
		}
		return jJToolBarBar;
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
			_btn_guardar.setBounds(new Rectangle(192, 33, 28, 22));
			_btn_guardar.setToolTipText("Guardar Relacion Numero Cuentas");
		}
		return _btn_guardar;
	}

	/**
	 * This method initializes _btn_minimizar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_minimizar() {
		if (_btn_minimizar == null) {
			_btn_minimizar = new JButton();
			_btn_minimizar.setIcon(new ImageIcon(getClass().getResource("/icons/remove.png")));
			_btn_minimizar.setBounds(new Rectangle(496, 6, 28, 20));
			_btn_minimizar.setToolTipText("Salir del Beta");
		}
		return _btn_minimizar;
	}

	/**
	 * This method initializes jPanel_Asterisk	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel_Asterisk() {
		if (jPanel_Asterisk == null) {
			jPanel_Asterisk = new JPanel();
			jPanel_Asterisk.setLayout(null);
			jPanel_Asterisk.add(jLabel, null);
			jPanel_Asterisk.add(get_txt_callerid(), null);
			jPanel_Asterisk.add(getJJToolBarBar(), null);
			jPanel_Asterisk.add(getJScrollPane(), null);
			jPanel_Asterisk.add(get_btn_cargar(), null);
			jPanel_Asterisk.add(get_btn_guardar(), null);
		}
		return jPanel_Asterisk;
	}

	/**
	 * This method initializes jTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.setSize(new Dimension(524, 271));
			jTabbedPane.setLocation(new Point(1, 28));
			jTabbedPane.setFont(new Font("Dialog", Font.PLAIN, 10));
			jTabbedPane.setTabPlacement(JTabbedPane.BOTTOM);
			jTabbedPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			jTabbedPane.addTab("Principal", new ImageIcon(getClass().getResource("/icons/xterm.png")), getJPanel_principal(), null);
			jTabbedPane.addTab("Llamadas", new ImageIcon(getClass().getResource("/icons/phone-16.gif")), getJPanel_Asterisk(), null);
			jTabbedPane.addTab("Agenda", new ImageIcon(getClass().getResource("/icons/appointment-soon.png")), getJPanel_agenda(), null);
			jTabbedPane.addTab("Messenger", new ImageIcon(getClass().getResource("/icons/im-msn-24.png")), getJPanel_messenger(), null);
			jTabbedPane.addTab("Acerca de", new ImageIcon(getClass().getResource("/icons/gnome-help-24.png")), getJPanel_acerca(), null);
			
			
		}
		return jTabbedPane;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(4, 140, 504, 86));
			jScrollPane.setViewportView(getJTable());
		}
		return jScrollPane;
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

	public void setJTable(JTable table){
		this.jTable=table;
		this.jScrollPane.setViewportView(jTable);
	}

	/**
	 * This method initializes _btn_cargar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cargar() {
		if (_btn_cargar == null) {
			_btn_cargar = new JButton();
			_btn_cargar.setToolTipText("Cargar Cuentas Relacionadas");
			_btn_cargar.setLocation(new Point(169, 34));
			_btn_cargar.setSize(new Dimension(20, 20));
			_btn_cargar.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-refresh.png")));
		}
		return _btn_cargar;
	}

	/**
	 * This method initializes jPanel_principal	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel_principal() {
		if (jPanel_principal == null) {
			jPanel_principal = new JPanel();
			jPanel_principal.setLayout(null);
		}
		return jPanel_principal;
	}

	/**
	 * This method initializes jPanel_acerca	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel_acerca() {
		if (jPanel_acerca == null) {
			jPanel_acerca = new JPanel();
			jPanel_acerca.setLayout(null);
			
		}
		return jPanel_acerca;
	}

	/**
	 * This method initializes jPanel_agenda	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel_agenda() {
		if (jPanel_agenda == null) {
			jPanel_agenda = new JPanel();
			jPanel_agenda.setLayout(null);
		}
		return jPanel_agenda;
	}

	/**
	 * This method initializes jPanel_messenger	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel_messenger() {
		if (jPanel_messenger == null) {
			jPanel_messenger = new JPanel();
			jPanel_messenger.setLayout(null);
		}
		return jPanel_messenger;
	}
}  //  @jve:decl-index=0:visual-constraint="10,31"
