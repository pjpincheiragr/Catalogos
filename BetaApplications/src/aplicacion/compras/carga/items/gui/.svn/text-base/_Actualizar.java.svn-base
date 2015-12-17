package aplicacion.compras.carga.items.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Rectangle;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class _Actualizar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel jPanel = null;
	private JButton _btn_actualizar_articulos = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private JCheckBox _chk_seleccionar = null;
	/**
	 * This is the default constructor
	 */
	public _Actualizar() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(640, 490);
		this.setContentPane(getJContentPane());
		this.setTitle("Actualizar");
		this.setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/chrome.png")));
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
			jContentPane.add(get_btn_actualizar_articulos(), null);
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
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBounds(new Rectangle(12, 14, 604, 405));
			jPanel.setBackground(Color.gray);
			jPanel.add(getJScrollPane(), null);
			jPanel.add(get_chk_seleccionar(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes _btn_actualizar_articulos	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_actualizar_articulos() {
		if (_btn_actualizar_articulos == null) {
			_btn_actualizar_articulos = new JButton();
			_btn_actualizar_articulos.setBounds(new Rectangle(572, 427, 41, 21));
			_btn_actualizar_articulos.setToolTipText("Actualizar Precios");
			_btn_actualizar_articulos.setIcon(new ImageIcon(getClass().getResource("/icons/player_record.png")));
			_btn_actualizar_articulos.setText("");
		}
		return _btn_actualizar_articulos;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(15, 33, 571, 345));
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
	 * This method initializes _chk_seleccionar	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_seleccionar() {
		if (_chk_seleccionar == null) {
			_chk_seleccionar = new JCheckBox();
			_chk_seleccionar.setBounds(new Rectangle(14, 6, 124, 17));
			_chk_seleccionar.setSelected(true);
			_chk_seleccionar.setText("seleccionar");
		}
		return _chk_seleccionar;
	}

}
