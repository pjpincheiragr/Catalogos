package aplicacion.herramientas.java.tree.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.Rectangle;
import javax.swing.JTree;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JScrollPane jScrollPane = null;
	private JTree jTree = null;
	private JButton _btn_agregar = null;
	private JButton _btn_borrar = null;
	private JButton _btn_edit = null;

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
		this.setSize(337, 271);
		this.setContentPane(getJContentPane());
		this.setTitle("Tree");
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
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(get_btn_agregar(), null);
			jContentPane.add(get_btn_borrar(), null);
			jContentPane.add(get_btn_edit(), null);
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
			jScrollPane.setBounds(new Rectangle(12, 9, 261, 152));
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
			_btn_agregar.setBounds(new Rectangle(14, 165, 29, 19));
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
			_btn_borrar.setBounds(new Rectangle(46, 164, 28, 21));
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
			_btn_edit.setBounds(new Rectangle(78, 164, 29, 20));
			_btn_edit.setToolTipText("Renombrar");
			_btn_edit.setIcon(new ImageIcon(getClass().getResource("/icons/edit-find-replace.png")));
		}
		return _btn_edit;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"