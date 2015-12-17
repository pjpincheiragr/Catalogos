package aplicacion.catalogo.repuestos.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.Rectangle;
import javax.swing.JTree;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JToolBar;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JScrollPane jScrollPane = null;
	private JTree jTree = null;
	private JButton _btn_agregar = null;
	private JButton _btn_borrar = null;
	private JButton _btn_edit = null;
	private JScrollPane jScrollPane1 = null;
	private JTable jTable = null;
	private JLabel jLabel = null;
	private JTextField _txt_idclasificacion = null;
	private JTextField _txt_clasificacion_descripcion = null;
	private JToolBar jToolBar = null;
	private JButton _btn_salir = null;

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
		this.setSize(790, 364);
		this.setResizable(false);
		this.setContentPane(getJContentPane());
		this.setTitle("Repuestos");
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
			jContentPane.add(getJScrollPane(), null);
			jLabel = new JLabel();
			jLabel.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel.setBounds(new Rectangle(305, 38, 76, 18));
			jLabel.setText("idclasificacion");
			jContentPane.add(jLabel, null);
			jContentPane.add(get_txt_idclasificacion(), null);
			jContentPane.add(get_txt_clasificacion_descripcion(), null);
			jContentPane.add(getJScrollPane1(), null);
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
			jScrollPane.setBounds(new Rectangle(12, 37, 277, 278));
			jScrollPane.setViewportView(getJTree());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTree	
	 * 	
	 * @return javax.swing.JTree	
	 */
	public JTree getJTree() {
		if (jTree == null) {
			jTree = new JTree();
		}
		return jTree;
	}
	
	public void setJTree(JTree tree){
		this.jTree=tree;
		this.jScrollPane.setViewportView(tree);
	}

	/**
	 * This method initializes _btn_agregar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_agregar() {
		if (_btn_agregar == null) {
			_btn_agregar = new JButton();
			_btn_agregar.setToolTipText("Agregar");
			_btn_agregar.setIcon(new ImageIcon(getClass().getResource("/icons/add.png")));
		}
		return _btn_agregar;
	}

	/**
	 * This method initializes _btn_borrar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_borrar() {
		if (_btn_borrar == null) {
			_btn_borrar = new JButton();
			_btn_borrar.setToolTipText("Borrar");
			_btn_borrar.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-remove.png")));
		}
		return _btn_borrar;
	}

	/**
	 * This method initializes _btn_edit	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_edit() {
		if (_btn_edit == null) {
			_btn_edit = new JButton();
			_btn_edit.setToolTipText("Renombrar");
			_btn_edit.setIcon(new ImageIcon(getClass().getResource("/icons/edit-find-replace.png")));
		}
		return _btn_edit;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	

	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setBounds(new Rectangle(306, 68, 460, 246));
			jScrollPane1.setViewportView(getJTable());
		}
		return jScrollPane1;
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
		this.jScrollPane1.setViewportView(jTable);
	}
	/**
	 * This method initializes _txt_idclasificacion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idclasificacion() {
		if (_txt_idclasificacion == null) {
			_txt_idclasificacion = new JTextField();
			_txt_idclasificacion.setEditable(false);
			_txt_idclasificacion.setHorizontalAlignment(JTextField.RIGHT);
			_txt_idclasificacion.setBackground(Color.black);
			_txt_idclasificacion.setForeground(Color.white);
			_txt_idclasificacion.setBounds(new Rectangle(396, 38, 106, 17));
			_txt_idclasificacion.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_idclasificacion;
	}

	/**
	 * This method initializes _txt_clasificacion_descripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_clasificacion_descripcion() {
		if (_txt_clasificacion_descripcion == null) {
			_txt_clasificacion_descripcion = new JTextField();
			_txt_clasificacion_descripcion.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_clasificacion_descripcion.setHorizontalAlignment(JTextField.LEFT);
			_txt_clasificacion_descripcion.setBackground(Color.black);
			_txt_clasificacion_descripcion.setForeground(Color.white);
			_txt_clasificacion_descripcion.setBounds(new Rectangle(510, 38, 254, 17));
			_txt_clasificacion_descripcion.setEditable(false);
		}
		return _txt_clasificacion_descripcion;
	}

	/**
	 * This method initializes jToolBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar() {
		if (jToolBar == null) {
			jToolBar = new JToolBar();
			jToolBar.setBounds(new Rectangle(6, 3, 774, 21));
			jToolBar.setFloatable(false);
			jToolBar.add(get_btn_agregar());
			jToolBar.add(get_btn_borrar());
			jToolBar.add(get_btn_edit());
			jToolBar.add(get_btn_salir());
		}
		return jToolBar;
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
}  //  @jve:decl-index=0:visual-constraint="10,10"
