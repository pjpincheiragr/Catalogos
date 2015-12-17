package aplicacion.sistema.codemanager.gui;

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
import javax.swing.JTextArea;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JScrollPane jScrollPane = null;
	private JTree jTree = null;
	private JButton _btn_agregar = null;
	private JButton _btn_borrar = null;
	private JButton _btn_edit = null;
	private JPanel jPanel = null;
	private JPanel jPanel1 = null;
	private JScrollPane jScrollPane1 = null;
	private JLabel jLabel = null;
	private JTextField _txt_idclasificacion = null;
	private JTextField _txt_clasificacion_descripcion = null;
	private JTextArea _txt_info = null;

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
			jContentPane.add(getJPanel(), null);
			jContentPane.add(getJPanel1(), null);
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
			jScrollPane.setBounds(new Rectangle(4, 5, 277, 265));
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
			_btn_agregar.setBounds(new Rectangle(4, 279, 29, 19));
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
			_btn_borrar.setBounds(new Rectangle(36, 278, 28, 21));
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
			_btn_edit.setBounds(new Rectangle(68, 278, 29, 20));
			_btn_edit.setIcon(new ImageIcon(getClass().getResource("/icons/edit-find-replace.png")));
		}
		return _btn_edit;
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
			jPanel.setBounds(new Rectangle(9, 15, 287, 304));
			jPanel.setBackground(Color.lightGray);
			jPanel.add(getJScrollPane(), null);
			jPanel.add(get_btn_edit(), null);
			jPanel.add(get_btn_borrar(), null);
			jPanel.add(get_btn_agregar(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(15, 13, 76, 18));
			jLabel.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel.setText("idclasificacion");
			jPanel1 = new JPanel();
			jPanel1.setLayout(null);
			jPanel1.setBounds(new Rectangle(302, 15, 467, 304));
			jPanel1.setBackground(Color.lightGray);
			jPanel1.add(getJScrollPane1(), null);
			jPanel1.add(jLabel, null);
			jPanel1.add(get_txt_idclasificacion(), null);
			jPanel1.add(get_txt_clasificacion_descripcion(), null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setBounds(new Rectangle(15, 46, 443, 225));
			jScrollPane1.setViewportView(get_txt_info());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes _txt_idclasificacion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idclasificacion() {
		if (_txt_idclasificacion == null) {
			_txt_idclasificacion = new JTextField();
			_txt_idclasificacion.setBounds(new Rectangle(101, 14, 106, 17));
			_txt_idclasificacion.setEditable(false);
			_txt_idclasificacion.setHorizontalAlignment(JTextField.RIGHT);
			_txt_idclasificacion.setBackground(Color.black);
			_txt_idclasificacion.setForeground(Color.white);
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
			_txt_clasificacion_descripcion.setBounds(new Rectangle(214, 14, 242, 17));
			_txt_clasificacion_descripcion.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_clasificacion_descripcion.setHorizontalAlignment(JTextField.LEFT);
			_txt_clasificacion_descripcion.setBackground(Color.black);
			_txt_clasificacion_descripcion.setForeground(Color.white);
			_txt_clasificacion_descripcion.setEditable(false);
		}
		return _txt_clasificacion_descripcion;
	}

	/**
	 * This method initializes _txt_info	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea get_txt_info() {
		if (_txt_info == null) {
			_txt_info = new JTextArea();
			_txt_info.setLineWrap(true);
			_txt_info.setWrapStyleWord(true);
		}
		return _txt_info;
	}
	
	
}  //  @jve:decl-index=0:visual-constraint="10,10"
