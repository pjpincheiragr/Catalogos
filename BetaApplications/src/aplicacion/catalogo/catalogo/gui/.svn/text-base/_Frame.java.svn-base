package aplicacion.catalogo.catalogo.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.JTabbedPane;
import java.awt.GridBagLayout;
import javax.swing.JToolBar;
import java.awt.Dimension;


public class _Frame extends JFrame {

    private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JScrollPane jScrollPane = null;
	
	private JTree jTree = null;
	private JScrollPane jScrollPane1 = null;

	private JTable jTable = null;
	
	private JPanel Aplicacion = null;
	private JPanel jPanelBuscador = null;
	private JScrollPane jScrollPane2 = null;

	private JTable jTable1 = null;

	private JButton _btn_guardar = null;

	private JCheckBox _chk_seleccionar = null;

	private JCheckBox _chk_seleccionar2 = null;

	private JButton _btn_limpiar = null;

	private JButton _btn_salir = null;

	private JCheckBox _chk_raiz = null;

	private JButton _btn_nuevo_pedido = null;

	private JTextField _txt_modelo = null;

	private JTextField _txt_url = null;

	private JTabbedPane jTabbedPane = null;

	private JPanel jPanel3 = null;

	private JToolBar jToolBar = null;

	private JTabbedPane jTabbedPane1 = null;

	private JTabbedPane jTabbedPane2 = null;

	private JPanel jPanel1 = null;

	private JLabel jLabel = null;

	private JButton _btn_eliminar = null;

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		
		this.setSize(1024, 750);
		this.setContentPane(getJContentPane());
		this.setTitle("Catalogo");
	}
	
	public _Frame() {
		super();
		initialize();
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
			jContentPane.add(get_txt_url(), null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(getJTabbedPane1(), null);
			jContentPane.add(getJTabbedPane2(), null);
			jContentPane.add(getJTabbedPane(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	public JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(7, 233, 230, 321));
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
			//jTree = new JTree();
		}
		return jTree;
	}
	
	public void setJTree(JTree tree){
		this.jTree=tree;
		this.getJScrollPane().setViewportView(jTree);
	}
	
	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setBounds(new Rectangle(11, 31, 710, 166));
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
			jTable.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return jTable;
	}
	
	public void setJTable(JTable table){
		this.jTable=table;
		this.jScrollPane1.setViewportView(table);
	}
	
	public void setJTable1(JTable table){
		this.jTable=table;
		this.jScrollPane2.setViewportView(table);
	}
	
	
	/**
	 * This method initializes Aplicacion	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public JPanel getAplicacion() {
		if (Aplicacion == null) {
			Aplicacion = new JPanel();
			Aplicacion.setLayout(null);
			Aplicacion.setBackground(Color.LIGHT_GRAY);
			Aplicacion.setBounds(new Rectangle(8, 42, 230, 182));
		}
		return Aplicacion;
	}
	/**
	 * This method initializes jPanelBuscador	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelBuscador() {
		if (jPanelBuscador == null) {
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(12, 15, 62, 19));
			jLabel.setText("modelo");
			jPanelBuscador = new JPanel();
			jPanelBuscador.setLayout(null);
			jPanelBuscador.add(getJScrollPane(), null);
			jPanelBuscador.add(getAplicacion(), null);
			jPanelBuscador.add(get_chk_raiz(), null);
			jPanelBuscador.add(get_txt_modelo(), null);
			jPanelBuscador.add(jLabel, null);
		}
		return jPanelBuscador;
	}
	
	/**
	 * This method initializes jScrollPane2	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane2() {
		if (jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setViewportView(getJTable1());
			jScrollPane2.setBounds(new Rectangle(3, 32, 704, 89));
		}
		return jScrollPane2;
	}
	/**
	 * This method initializes jTable1	
	 * 	
	 * @return javax.swing.JTable	
	 */
	
	public JTable getJTable1() {
		if (jTable1 == null) {
			
		}
		return jTable1;
	}
	
		
	/**
	 * This method initializes _btn_guardar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_guardar() {
		if (_btn_guardar == null) {
			_btn_guardar = new JButton();
			_btn_guardar.setIcon(new ImageIcon(getClass().getResource("/icons/emblem-default-16.png")));
			_btn_guardar.setToolTipText("Listo");
			
		}
		return _btn_guardar;
	}
	/**
	 * This method initializes _chk_seleccionar	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_seleccionar() {
		if (_chk_seleccionar == null) {
			_chk_seleccionar = new JCheckBox();
			_chk_seleccionar.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_seleccionar.setBounds(new Rectangle(4, 6, 100, 14));
			_chk_seleccionar.setText("Seleccionar");
			
			
		}
		return _chk_seleccionar;
	}
	/**
	 * This method initializes _chk_seleccionar2	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_seleccionar2() {
		if (_chk_seleccionar2 == null) {
			_chk_seleccionar2 = new JCheckBox();
			_chk_seleccionar2.setBounds(new Rectangle(7, 8, 166, 16));
			_chk_seleccionar2.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_seleccionar2.setText("seleccionar");
		}
		return _chk_seleccionar2;
	}
	/**
	 * This method initializes _btn_limpiar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_limpiar() {
		if (_btn_limpiar == null) {
			_btn_limpiar = new JButton();
			_btn_limpiar.setToolTipText("Limpiar");
			_btn_limpiar.setLocation(new Point(681, 4));
			_btn_limpiar.setSize(new Dimension(22, 20));
			_btn_limpiar.setIcon(new ImageIcon(getClass().getResource("/icons/editclear.png")));
		}
		return _btn_limpiar;
	}
	/**
	 * This method initializes _btn_salir	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_salir() {
		if (_btn_salir == null) {
			_btn_salir = new JButton();
			_btn_salir.setIcon(new ImageIcon(getClass().getResource("/icons/system-log-out.png")));
			_btn_salir.setToolTipText("Salir");
		}
		return _btn_salir;
	}
	
	/**
	 * This method initializes _chk_raiz	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_raiz() {
		if (_chk_raiz == null) {
			_chk_raiz = new JCheckBox();
			_chk_raiz.setBounds(new Rectangle(7, 574, 237, 13));
			_chk_raiz.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_raiz.setSelected(true);
			_chk_raiz.setText("Muestra Contenido de Subclases");
		}
		return _chk_raiz;
	}
	
	
	/**
	 * This method initializes _btn_nuevo_pedido	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_nuevo_pedido() {
		if (_btn_nuevo_pedido == null) {
			_btn_nuevo_pedido = new JButton();
			_btn_nuevo_pedido.setToolTipText("Crear Pedido");
			_btn_nuevo_pedido.setIcon(new ImageIcon(getClass().getResource("/icons/centrejust.png")));
			
		}
		return _btn_nuevo_pedido;
	}
	
	
	
	/**
	 * This method initializes _txt_modelo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_modelo() {
		if (_txt_modelo == null) {
			_txt_modelo = new JTextField();
			_txt_modelo.setBounds(new Rectangle(77, 14, 85, 21));
			
		}
		return _txt_modelo;
	}
	/**
	 * This method initializes _txt_url	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_url() {
		if (_txt_url == null) {
			_txt_url = new JTextField();
			_txt_url.setBounds(new Rectangle(9, 687, 438, 22));
			_txt_url.setEnabled(false);
			_txt_url.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_url;
	}

	/**
	 * This method initializes jTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.setBounds(new Rectangle(273, 278, 734, 188));
			jTabbedPane.addTab("Seleccion", null, getJPanel3(), null);
		}
		return jTabbedPane;
	}

	/**
	 * This method initializes jPanel3	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jPanel3 = new JPanel();
			jPanel3.setLayout(null);
			jPanel3.setToolTipText("Seleccion");
			jPanel3.add(getJScrollPane2(), null);
			jPanel3.add(get_btn_limpiar(), null);
			jPanel3.add(get_chk_seleccionar2(), null);
		}
		return jPanel3;
	}

	/**
	 * This method initializes jToolBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar() {
		if (jToolBar == null) {
			jToolBar = new JToolBar();
			jToolBar.setBounds(new Rectangle(4, 3, 1004, 22));
			jToolBar.setFloatable(false);
			
			jToolBar.add(get_btn_nuevo_pedido());
			jToolBar.add(get_btn_guardar());
			jToolBar.add(get_btn_salir());
		}
		return jToolBar;
	}

	/**
	 * This method initializes jTabbedPane1	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJTabbedPane1() {
		if (jTabbedPane1 == null) {
			jTabbedPane1 = new JTabbedPane();
			jTabbedPane1.setBounds(new Rectangle(7, 27, 256, 642));
			jTabbedPane1.addTab("Filtros de Aplicacion", null, getJPanelBuscador(), null);
		}
		return jTabbedPane1;
	}

	/**
	 * This method initializes jTabbedPane2	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJTabbedPane2() {
		if (jTabbedPane2 == null) {
			jTabbedPane2 = new JTabbedPane();
			jTabbedPane2.setBounds(new Rectangle(271, 30, 736, 233));
			jTabbedPane2.addTab("Resultado de Busqueda", null, getJPanel1(), null);
		}
		return jTabbedPane2;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setLayout(null);
			jPanel1.add(get_chk_seleccionar(), null);
			jPanel1.add(getJScrollPane1(), null);
			jPanel1.add(get_btn_eliminar(), null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes _btn_eliminar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_eliminar() {
		if (_btn_eliminar == null) {
			_btn_eliminar = new JButton();
			_btn_eliminar.setBounds(new Rectangle(693, 6, 21, 20));
			_btn_eliminar.setToolTipText("Eliminar Seleccion");
			_btn_eliminar.setIcon(new ImageIcon(getClass().getResource("/icons/edit-delete.png")));
		}
		return _btn_eliminar;
	}
	
	
}  //  @jve:decl-index=0:visual-constraint="10,10"


