package aplicacion.compras.carga.pedido.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JScrollPane;
import java.awt.Rectangle;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class _Lineas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private JToolBar jToolBar = null;
	private JButton _btn_importar = null;
	private JButton _btn_salir = null;
	private JCheckBox _chk_seleccionar = null;
	private JComboBox _lst_categoria = null;
	private JLabel jLabel = null;
	private JComboBox _lst_modo = null;
	private JLabel jLabel1 = null;
	private JComboBox _lst_estrategia = null;
	/**
	 * This is the default constructor
	 */
	public _Lineas() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(752, 327);
		this.setResizable(false);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/chrome.png")));
		this.setContentPane(getJContentPane());
		this.setTitle("Lineas del Proveedor");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(430, 29, 88, 15));
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setText("Estrategia");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(257, 30, 79, 14));
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setText("Categoria");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(get_chk_seleccionar(), null);
			jContentPane.add(get_lst_categoria(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(get_lst_modo(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(get_lst_estrategia(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(5, 50, 736, 174));
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
	 * This method initializes jToolBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar() {
		if (jToolBar == null) {
			jToolBar = new JToolBar();
			jToolBar.setBounds(new Rectangle(4, 2, 740, 24));
			jToolBar.setFloatable(false);
			jToolBar.add(get_btn_importar());
			jToolBar.add(get_btn_salir());
		}
		return jToolBar;
	}

	/**
	 * This method initializes _btn_importar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_importar() {
		if (_btn_importar == null) {
			_btn_importar = new JButton();
			_btn_importar.setIcon(new ImageIcon(getClass().getResource("/icons/emblem-default.png")));
		}
		return _btn_importar;
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
	 * This method initializes _chk_seleccionar	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_seleccionar() {
		if (_chk_seleccionar == null) {
			_chk_seleccionar = new JCheckBox();
			_chk_seleccionar.setBounds(new Rectangle(5, 30, 111, 15));
			_chk_seleccionar.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_seleccionar.setText("Seleccionar");
		}
		return _chk_seleccionar;
	}

	/**
	 * This method initializes _lst_categoria	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_categoria() {
		if (_lst_categoria == null) {
			_lst_categoria = new JComboBox();
			_lst_categoria.setBounds(new Rectangle(343, 29, 77, 17));
			_lst_categoria.addItem("");
			_lst_categoria.addItem("A");
			_lst_categoria.addItem("B");
			_lst_categoria.addItem("C");
			_lst_categoria.addItem("D");
		}
		return _lst_categoria;
	}

	/**
	 * This method initializes _lst_modo	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_modo() {
		if (_lst_modo == null) {
			_lst_modo = new JComboBox();
			_lst_modo.setBounds(new Rectangle(128, 30, 124, 15));
			_lst_modo.addItem("Rotacion");
			_lst_modo.addItem("Volumen");
			_lst_modo.addItem("Pickups");
		}
		return _lst_modo;
	}

	/**
	 * This method initializes _lst_estrategia	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox get_lst_estrategia() {
		if (_lst_estrategia == null) {
			_lst_estrategia = new JComboBox();
			_lst_estrategia.setBounds(new Rectangle(524, 28, 103, 17));
			_lst_estrategia.addItem("Optimo");
			_lst_estrategia.addItem("Stockear");
			_lst_estrategia.addItem("Stockear");
		}
		return _lst_estrategia;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
