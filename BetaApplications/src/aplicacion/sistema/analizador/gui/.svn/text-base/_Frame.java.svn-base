package aplicacion.sistema.analizador.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JToolBar;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import java.awt.Point;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JProgressBar;
import java.awt.GridBagLayout;
import javax.swing.JCheckBox;
import java.awt.Font;
import javax.swing.JComboBox;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JToolBar jToolBar = null;
	private JButton _btn_cancelar = null;
	private JButton _btn_guardar = null;
	private JButton _btn_exportar = null;
	private JButton _btn_abrir = null;
	private JButton _btn_ejecutar = null;
	private JButton _btn_salir = null;
	private JSplitPane jSplitPane = null;
	private JScrollPane jScrollPane1 = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private JTextArea jTextArea = null;
	private JProgressBar jProgressBar = null;
	private JPanel jPanel = null;
	private JCheckBox _chk_seleccionar = null;
	private JButton _btn_error = null;
	private JComboBox _lst_connections = null;
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
		this.setSize(1024, 740);
		this.setContentPane(getJContentPane());
		this.setTitle("Analizador de Consultas");
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
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(getJSplitPane(), null);
			jContentPane.add(getJProgressBar(), null);
			jContentPane.add(get_lst_connections(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jToolBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	public JToolBar getJToolBar() {
		if (jToolBar == null) {
			jToolBar = new JToolBar();
			jToolBar.setFloatable(false);
			jToolBar.setBounds(new Rectangle(0, 0, 1020, 22));
			jToolBar.add(get_btn_cancelar());
			jToolBar.add(get_btn_ejecutar());
			jToolBar.addSeparator();
			jToolBar.addSeparator();
			jToolBar.add(get_btn_abrir());
			jToolBar.add(get_btn_guardar());
			jToolBar.add(get_btn_exportar());
			jToolBar.addSeparator();
			jToolBar.addSeparator();
			jToolBar.add(get_btn_salir());
			jToolBar.add(get_btn_error());
		}
		return jToolBar;
	}

	/**
	 * This method initializes _btn_cancelar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cancelar() {
		if (_btn_cancelar == null) {
			_btn_cancelar = new JButton();
			_btn_cancelar.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-stop.png")));
		}
		return _btn_cancelar;
	}

	/**
	 * This method initializes _btn_guardar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_guardar() {
		if (_btn_guardar == null) {
			_btn_guardar = new JButton();
			_btn_guardar.setIcon(new ImageIcon(getClass().getResource("/icons/filesave.png")));
		}
		return _btn_guardar;
	}

	/**
	 * This method initializes _btn_exportar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_exportar() {
		if (_btn_exportar == null) {
			_btn_exportar = new JButton();
			_btn_exportar.setIcon(new ImageIcon(getClass().getResource("/icons/go-top.png")));
		}
		return _btn_exportar;
	}

	/**
	 * This method initializes _btn_abrir	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_abrir() {
		if (_btn_abrir == null) {
			_btn_abrir = new JButton();
			_btn_abrir.setIcon(new ImageIcon(getClass().getResource("/icons/document-open.png")));
		}
		return _btn_abrir;
	}

	/**
	 * This method initializes _btn_ejecutar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_ejecutar() {
		if (_btn_ejecutar == null) {
			_btn_ejecutar = new JButton();
			_btn_ejecutar.setIcon(new ImageIcon(getClass().getResource("/icons/stock_media-play.png")));
		}
		return _btn_ejecutar;
	}

	/**
	 * This method initializes _btn_salir	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_salir() {
		if (_btn_salir == null) {
			_btn_salir = new JButton();
			_btn_salir.setIcon(new ImageIcon(getClass().getResource("/icons/exit.png")));
		}
		return _btn_salir;
	}

	/**
	 * This method initializes jSplitPane	
	 * 	
	 * @return javax.swing.JSplitPane	
	 */
	private JSplitPane getJSplitPane() {
		if (jSplitPane == null) {
			jSplitPane = new JSplitPane();
			jSplitPane.setDividerLocation(130);
			jSplitPane.setLocation(new Point(1, 52));
			jSplitPane.setSize(new Dimension(1010, 600));
			jSplitPane.setTopComponent(getJScrollPane1());
			//jSplitPane.setBottomComponent(getJScrollPane());
			jSplitPane.setBottomComponent(getJPanel());
			jSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		}
		return jSplitPane;
	}

	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getJTextArea());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(2, 27, 1000, 421));
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

	public void setJTable(JTable table){
		this.jTable=table;
		this.jScrollPane.setViewportView(jTable);
	}

	/**
	 * This method initializes jTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea getJTextArea() {
		if (jTextArea == null) {
			jTextArea = new JTextArea();
			jTextArea.setLineWrap(true);
			jTextArea.setWrapStyleWord(true);
		}
		return jTextArea;
	}

	/**
	 * This method initializes jProgressBar	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	public JProgressBar getJProgressBar() {
		if (jProgressBar == null) {
			jProgressBar = new JProgressBar();
			jProgressBar.setBounds(new Rectangle(162, 25, 849, 18));
		}
		return jProgressBar;
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
			jPanel.add(getJScrollPane(), null);
			jPanel.add(get_chk_seleccionar(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes _chk_seleccionar	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_seleccionar() {
		if (_chk_seleccionar == null) {
			_chk_seleccionar = new JCheckBox();
			_chk_seleccionar.setBounds(new Rectangle(3, 4, 139, 16));
			_chk_seleccionar.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_seleccionar.setText("Seleccionar");
		}
		return _chk_seleccionar;
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
	 * This method initializes _lst_connections	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_connections() {
		if (_lst_connections == null) {
			_lst_connections = new JComboBox();
			_lst_connections.setBounds(new Rectangle(5, 26, 153, 16));
		}
		return _lst_connections;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
