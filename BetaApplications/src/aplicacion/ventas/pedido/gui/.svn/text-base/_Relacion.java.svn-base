package aplicacion.ventas.pedido.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Rectangle;
import java.awt.GridBagLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Toolkit;
import javax.swing.JToolBar;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Point;

public class _Relacion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel jPanel = null;
	private JButton _btn_importar_relaciones = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private JCheckBox _chk_seleccionar = null;
	private JToolBar jToolBar = null;

	/**
	 * This is the default constructor
	 */
	public _Relacion() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(833, 379);
		this.setContentPane(getJContentPane());
		this.setTitle("Relaciones");
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
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBounds(new Rectangle(3, 28, 820, 317));
			jPanel.setBackground(Color.gray);
			jPanel.add(getJScrollPane(), null);
			jPanel.add(get_chk_seleccionar(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes _btn_importar_relaciones	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_importar_relaciones() {
		if (_btn_importar_relaciones == null) {
			_btn_importar_relaciones = new JButton();
			_btn_importar_relaciones.setToolTipText("Importar Relaciones");
			_btn_importar_relaciones.setIcon(new ImageIcon(getClass().getResource("/icons/emblem-default-16.png")));
			_btn_importar_relaciones.setText("");
		}
		return _btn_importar_relaciones;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(15, 33, 800, 267));
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
			_chk_seleccionar.setSelected(false);
			_chk_seleccionar.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_seleccionar.setText("seleccionar");
		}
		return _chk_seleccionar;
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
			jToolBar.setSize(new Dimension(824, 24));
			jToolBar.setLocation(new Point(0, 0));
			jToolBar.add(get_btn_importar_relaciones());
		}
		return jToolBar;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
