package aplicacion.sistema.cptes.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import java.awt.Rectangle;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JToolBar jToolBar = null;
	private JButton _btn_cargar = null;
	private JButton _btn_salir = null;
	private JScrollPane jScrollPane = null;  //  @jve:decl-index=0:visual-constraint="19,69"
	private JTable jTable = null;
	private JTextField _txt_id = null;
	private JLabel jLabel = null;
	private JButton _btn_cancelar = null;
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
		this.setSize(701, 280);
		this.setContentPane(getJContentPane());
		this.setTitle("Parametros de Sistema");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(6, 25, 130, 13));
			jLabel.setText("Filtrar Parametro");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(getJScrollPane(),null);
			jContentPane.add(get_txt_id(), null);
			jContentPane.add(jLabel, null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jToolBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar() {
		if (jToolBar == null) {
			jToolBar = new JToolBar();
			jToolBar.setBounds(new Rectangle(0, 0, 690, 24));
			jToolBar.setFloatable(false);
			jToolBar.add(get_btn_cargar());
			jToolBar.add(get_btn_cancelar());
			jToolBar.add(get_btn_salir());
		}
		return jToolBar;
	}

	/**
	 * This method initializes _btn_cargar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cargar() {
		if (_btn_cargar == null) {
			_btn_cargar = new JButton();
			_btn_cargar.setIcon(new ImageIcon(getClass().getResource("/icons/reload3.png")));
		}
		return _btn_cargar;
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
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(6, 62, 678, 160));
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
		this.jScrollPane.setViewportView(table);
	}

	/**
	 * This method initializes _txt_id	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_id() {
		if (_txt_id == null) {
			_txt_id = new JTextField();
			_txt_id.setToolTipText("Filtrar Parametros");
			_txt_id.setBounds(new Rectangle(6, 42, 160, 16));
		}
		return _txt_id;
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
}  //  @jve:decl-index=0:visual-constraint="10,10"
