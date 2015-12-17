package aplicacion.gestion.agenda.lector.gui;


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
import javax.swing.JTree;
import javax.swing.JSplitPane;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTabbedPane;

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

	private JTextField _txt_idevento = null;

	private JLabel jLabel = null;

	private JTextField _txt_titulo = null;

	private JScrollPane jScrollPane = null;

	private JTextArea _txt_mensaje = null;

	private JTextField _txt_agenda = null;

	private JButton _btn_fecha = null;

	private JTextField _txt_idcreador = null;

	private JTextField _txt_creador_descripcion = null;

	private JLabel jLabel3 = null;

	

	private JPanel jPanel1 = null;

	private JPanel jPanel2 = null;

	private JButton _btn_guardar = null;

	private JLabel jLabel1 = null;

	private JLabel jLabel2 = null;

	private JComboBox _lst_posponer = null;

	private JCheckBox _chk_leido = null;

	private JCheckBox _chk_mensaje = null;

	private JTabbedPane jTabbedPane = null;

	private JPanel jPanel3 = null;

	private JScrollPane jScrollPane1 = null;

	private JTable jTable_asociado = null;

	private JSplitPane jSplitPane = null;

	private JPanel jPanel4 = null;

	private JScrollPane jScrollPane2 = null;

	private JTree jTree = null;

	private JTabbedPane jTabbedPane1 = null;

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
		this.setSize(815, 383);
		this.setResizable(false);
		this.setContentPane(getJContentPane());
		this.setTitle("Aviso");
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
			jLabel2.setBounds(new Rectangle(427, 39, 48, 17));
			jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel2.setText("Posponer");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(418, 14, 58, 17));
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setText("Agenda");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(8, 40, 56, 19));
			jLabel3.setFont(new Font("Dialog", java.awt.Font.PLAIN, 10));
			jLabel3.setText("iduser");
		
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(7, 14, 57, 18));
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setText("idaviso");
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBounds(new Rectangle(6, 31, 797, 320));
			jPanel.setBackground(Color.LIGHT_GRAY);
			jPanel.add(get_txt_idevento(), null);
			jPanel.add(jLabel, null);
			jPanel.add(get_txt_agenda(), null);
			jPanel.add(get_btn_fecha(), null);
			jPanel.add(get_txt_idcreador(), null);
			jPanel.add(get_txt_creador_descripcion(), null);
			jPanel.add(jLabel3, null);
			jPanel.add(jLabel1, null);
			jPanel.add(jLabel2, null);
			jPanel.add(get_lst_posponer(), null);
			jPanel.add(get_chk_leido(), null);
			jPanel.add(get_chk_mensaje(), null);
			jPanel.add(getJTabbedPane(), null);
			
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
			jToolBar.setBounds(new Rectangle(6, 4, 799, 25));
			jToolBar.add(get_btn_cancelar());
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
	 * This method initializes _txt_idevento	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idevento() {
		if (_txt_idevento == null) {
			_txt_idevento = new JTextField();
			_txt_idevento.setBounds(new Rectangle(65, 13, 98, 20));
			_txt_idevento.setEditable(false);
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
			_txt_titulo.setBounds(new Rectangle(67, 84, 397, 21));
			_txt_titulo.setToolTipText("Titulo");
			_txt_titulo.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_titulo;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(66, 111, 395, 109));
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
			_txt_mensaje.setToolTipText("Mensaje");
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
			_txt_agenda.setBounds(new Rectangle(481, 13, 159, 17));
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
			_btn_fecha.setBounds(new Rectangle(642, 13, 20, 18));
			_btn_fecha.setIcon(new ImageIcon(getClass().getResource("/icons/calendar-16.gif")));
		}
		return _btn_fecha;
	}

	/**
	 * This method initializes _txt_idcreador	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idcreador() {
		if (_txt_idcreador == null) {
			_txt_idcreador = new JTextField();
			_txt_idcreador.setBounds(new Rectangle(68, 39, 53, 18));
			_txt_idcreador.setEditable(false);
		}
		return _txt_idcreador;
	}

	/**
	 * This method initializes _txt_creador_descripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_creador_descripcion() {
		if (_txt_creador_descripcion == null) {
			_txt_creador_descripcion = new JTextField();
			_txt_creador_descripcion.setBounds(new Rectangle(126, 38, 130, 18));
			_txt_creador_descripcion.setEditable(false);
		}
		return _txt_creador_descripcion;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.BOTH;
			gridBagConstraints1.gridy = 1;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.weighty = 1.0;
			gridBagConstraints1.gridx = 0;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridheight = 1;
			gridBagConstraints.gridwidth = 0;
			gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints.anchor = GridBagConstraints.EAST;
			gridBagConstraints.gridy = 0;
			jPanel1 = new JPanel();
			jPanel1.setLayout(new GridBagLayout());
			jPanel1.add(getJPanel2(), gridBagConstraints);
			jPanel1.add(getJScrollPane(), gridBagConstraints1);
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
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints2.gridy = 0;
			gridBagConstraints2.weightx = 1.0D;
			gridBagConstraints2.anchor = GridBagConstraints.WEST;
			gridBagConstraints2.gridwidth = 1;
			gridBagConstraints2.insets = new Insets(0, 0, 0, 0);
			gridBagConstraints2.ipadx = 90;
			gridBagConstraints2.gridx = 1;
			jPanel2 = new JPanel();
			jPanel2.setLayout(new GridBagLayout());
			jPanel2.add(get_txt_titulo(), gridBagConstraints2);
		}
		return jPanel2;
	}
	
		
	

	/**
	 * This method initializes _btn_guardar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_guardar() {
		if (_btn_guardar == null) {
			_btn_guardar = new JButton();
			_btn_guardar.setIcon(new ImageIcon(getClass().getResource("/icons/document-save-as.png")));
		}
		return _btn_guardar;
	}

	/**
	 * This method initializes _lst_posponer	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_posponer() {
		if (_lst_posponer == null) {
			_lst_posponer = new JComboBox();
			_lst_posponer.setBounds(new Rectangle(481, 38, 159, 17));
			_lst_posponer.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _lst_posponer;
	}

	/**
	 * This method initializes _chk_leido	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_leido() {
		if (_chk_leido == null) {
			_chk_leido = new JCheckBox();
			_chk_leido.setBounds(new Rectangle(174, 14, 81, 17));
			_chk_leido.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_leido.setText("Leido");
		}
		return _chk_leido;
	}

	/**
	 * This method initializes _chk_mensaje	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_mensaje() {
		if (_chk_mensaje == null) {
			_chk_mensaje = new JCheckBox();
			_chk_mensaje.setBounds(new Rectangle(258, 40, 123, 16));
			_chk_mensaje.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_mensaje.setText("Mostrar en Pantalla");
		}
		return _chk_mensaje;
	}

	/**
	 * This method initializes jTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.setFont(new Font("Dialog", Font.BOLD, 10));
			jTabbedPane.setBounds(new Rectangle(3, 68, 790, 246));
			jTabbedPane.addTab("Aviso", null, getJSplitPane(), null);
			jTabbedPane.addTab("Asociado", null, getJPanel3(), null);
			
		}
		return jTabbedPane;
	}

	/**
	 * This method initializes jPanel3	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.BOTH;
			gridBagConstraints3.gridy = 0;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.weighty = 1.0;
			gridBagConstraints3.gridx = 0;
			jPanel3 = new JPanel();
			jPanel3.setLayout(new GridBagLayout());
			jPanel3.add(getJScrollPane1(), gridBagConstraints3);
		}
		return jPanel3;
	}

	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getJTable_asociado());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes jTable_asociado	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getJTable_asociado() {
		if (jTable_asociado == null) {
			jTable_asociado = new JTable();
		}
		return jTable_asociado;
	}
	
	public void setJTable_asociado(JTable table){
		this.jTable_asociado=table;
		this.jScrollPane1.setViewportView(jTable_asociado);
	}

	/**
	 * This method initializes jSplitPane	
	 * 	
	 * @return javax.swing.JSplitPane	
	 */
	private JSplitPane getJSplitPane() {
		if (jSplitPane == null) {
			jSplitPane = new JSplitPane();
			jSplitPane.setDividerLocation(150);
			jSplitPane.setRightComponent(getJPanel1());
			jSplitPane.setLeftComponent(getJPanel4());
		}
		return jSplitPane;
	}

	/**
	 * This method initializes jPanel4	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel4() {
		if (jPanel4 == null) {
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.fill = GridBagConstraints.BOTH;
			gridBagConstraints5.weighty = 1.0;
			gridBagConstraints5.weightx = 1.0;
			jPanel4 = new JPanel();
			jPanel4.setLayout(new GridBagLayout());
			jPanel4.add(getJTabbedPane1(), gridBagConstraints5);
		}
		return jPanel4;
	}

	/**
	 * This method initializes jScrollPane2	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane2() {
		if (jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setFont(new Font("Dialog", Font.PLAIN, 12));
			jScrollPane2.setViewportView(getJTree());
		}
		return jScrollPane2;
	}

	/**
	 * This method initializes jTree	
	 * 	
	 * @return javax.swing.JTree	
	 */
	public JTree getJTree() {
		if (jTree == null) {
			jTree = new JTree();
			jTree.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return jTree;
	}
	
	public void setJTree(JTree tree){
		this.jTree=tree;
		this.jScrollPane2.setViewportView(jTree);
	}

	/**
	 * This method initializes jTabbedPane1	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJTabbedPane1() {
		if (jTabbedPane1 == null) {
			jTabbedPane1 = new JTabbedPane();
			jTabbedPane1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jTabbedPane1.addTab("Categoria", null, getJScrollPane2(), null);
		}
		return jTabbedPane1;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
