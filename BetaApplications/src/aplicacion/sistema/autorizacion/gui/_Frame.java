package aplicacion.sistema.autorizacion.gui;

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
import java.awt.Font;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JToolBar jToolBar = null;
	private JButton _btn_cargar = null;
	private JButton _btn_salir = null;
	private JScrollPane jScrollPane = null;  //  @jve:decl-index=0:visual-constraint="19,69"
	private JTable jTable = null;
	private JButton _btn_cancelar = null;
	private JLabel jLabel = null;
	private JTextField _txt_usuario = null;
	private JButton _btn_buscar_usuario = null;
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
		this.setSize(701, 331);
		this.setContentPane(getJContentPane());
		this.setTitle("Autorizacion de Tareas");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(5, 31, 143, 14));
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setText("usuario");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(getJScrollPane(),null);
			jContentPane.add(jLabel, null);
			jContentPane.add(get_txt_usuario(), null);
			jContentPane.add(get_btn_buscar_usuario(), null);
			jContentPane.add(get_btn_cargar(), null);
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
			_btn_cargar.setBounds(new Rectangle(179, 49, 20, 18));
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
			jScrollPane.setBounds(new Rectangle(3, 75, 678, 221));
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
	 * This method initializes _txt_usuario	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_usuario() {
		if (_txt_usuario == null) {
			_txt_usuario = new JTextField();
			_txt_usuario.setBounds(new Rectangle(4, 50, 150, 17));
			_txt_usuario.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_usuario;
	}

	/**
	 * This method initializes _btn_buscar_usuario	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_usuario() {
		if (_btn_buscar_usuario == null) {
			_btn_buscar_usuario = new JButton();
			_btn_buscar_usuario.setBounds(new Rectangle(156, 49, 20, 18));
			_btn_buscar_usuario.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-searchtool.png")));
			_btn_buscar_usuario.setToolTipText("Buscar Usuario");
		}
		return _btn_buscar_usuario;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
