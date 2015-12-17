package aplicacion.herramientas.java.msortableselector.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JToolBar;
import java.awt.Rectangle;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JProgressBar;
import java.awt.GridBagLayout;
import javax.swing.JSplitPane;
import javax.swing.JCheckBox;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JToolBar _toolbar_header = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private JButton _btn_cancelar = null;
	private JButton _btn_buscar = null;
	private JButton _btn_salir = null;
	private JScrollPane jScrollPane1 = null;
	private JTable jTable1 = null;
	private JProgressBar jProgressBar = null;
	private JPanel jPanel = null;
	private JSplitPane jSplitPane = null;
	private JPanel jPanel1 = null;
	private JScrollPane jScrollPane2 = null;
	private JTable jTable2 = null;
	private JCheckBox _chk_seleccionar = null;
	private JButton _btn_guardar = null;
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
		this.setSize(612, 366);
		this.setContentPane(getJContentPane());
		this.setTitle("Buscador");
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
			jContentPane.add(getJProgressBar(), null);
			jContentPane.add(get_toolbar_header(), null);
			jContentPane.add(getJSplitPane(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes _toolbar_header	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	public JToolBar get_toolbar_header() {
		if (_toolbar_header == null) {
			_toolbar_header = new JToolBar();
			_toolbar_header.setFloatable(false);
			_toolbar_header.setBounds(new Rectangle(1, 1, 596, 24));
			_toolbar_header.add(get_btn_cancelar());
			_toolbar_header.add(get_btn_buscar());
			_toolbar_header.add(get_btn_guardar());
			_toolbar_header.add(get_btn_salir());
			_toolbar_header.addSeparator();
			_toolbar_header.add(get_btn_error());
		}
		return _toolbar_header;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	public JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(4, 71, 570, 200));
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
			jTable.setFont(new Font("Dialog", Font.PLAIN, 12));
		}
		return jTable;
	}

	public void setJTable(JTable table){
		final JTable tablex=table;
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				jTable=tablex;
				jScrollPane.setViewportView(jTable);		
			}
		});
	}
	/**
	 * This method initializes _btn_cancelar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cancelar() {
		if (_btn_cancelar == null) {
			_btn_cancelar = new JButton();
			_btn_cancelar.setIcon(new ImageIcon(getClass().getResource("/icons/stock_calc-cancel.png")));
		}
		return _btn_cancelar;
	}

	/**
	 * This method initializes _btn_buscar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar() {
		if (_btn_buscar == null) {
			_btn_buscar = new JButton();
			_btn_buscar.setIcon(new ImageIcon(getClass().getResource("/icons/stock_hyperlink-internet-search.png")));
		}
		return _btn_buscar;
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
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	public JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setBounds(new Rectangle(4, 6, 574, 60));
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

	public void setJTable1(JTable table){
		this.jTable1=table;
		this.jScrollPane1.setViewportView(jTable1);
	}
	public void setJTable2(JTable table){
		this.jTable2=table;
		this.jScrollPane2.setViewportView(jTable2);
	}
	/**
	 * This method initializes jProgressBar	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	public JProgressBar getJProgressBar() {
		if (jProgressBar == null) {
			jProgressBar = new JProgressBar();
			jProgressBar.setBounds(new Rectangle(0, 25, 597, 14));
		}
		return jProgressBar;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.add(getJScrollPane(), null);
			jPanel.add(getJScrollPane1(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jSplitPane	
	 * 	
	 * @return javax.swing.JSplitPane	
	 */
	public JSplitPane getJSplitPane() {
		if (jSplitPane == null) {
			jSplitPane = new JSplitPane();
			jSplitPane.setBounds(new Rectangle(3, 42, 595, 290));
			jSplitPane.setDividerLocation(290);
			jSplitPane.setOneTouchExpandable(true);
			jSplitPane.setTopComponent(getJPanel());
			jSplitPane.setBottomComponent(getJPanel1());
			jSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		}
		return jSplitPane;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setLayout(null);
			jPanel1.add(getJScrollPane2(), null);
			jPanel1.add(get_chk_seleccionar(), null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes jScrollPane2	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	public JScrollPane getJScrollPane2() {
		if (jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setBounds(new Rectangle(3, 25, 578, 118));
			jScrollPane2.setViewportView(getJTable2());
		}
		return jScrollPane2;
	}

	/**
	 * This method initializes jTable2	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getJTable2() {
		if (jTable2 == null) {
			jTable2 = new JTable();
		}
		return jTable2;
	}

	/**
	 * This method initializes _chk_seleccionar	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_seleccionar() {
		if (_chk_seleccionar == null) {
			_chk_seleccionar = new JCheckBox();
			_chk_seleccionar.setBounds(new Rectangle(4, 5, 104, 13));
			_chk_seleccionar.setFont(new Font("Dialog", Font.BOLD, 10));
			_chk_seleccionar.setText("Seleccionar");
		}
		return _chk_seleccionar;
	}

	/**
	 * This method initializes _btn_guardar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_guardar() {
		if (_btn_guardar == null) {
			_btn_guardar = new JButton();
			_btn_guardar.setIcon(new ImageIcon(getClass().getResource("/icons/emblem-default.png")));
		}
		return _btn_guardar;
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
