package aplicacion.actualizacion.creacion.gui;

import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumnModel;
import javax.swing.JTabbedPane;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

import java.awt.Font;
import java.awt.event.*;
import javax.swing.JCheckBox;

import javax.swing.JProgressBar;
import javax.swing.SwingConstants;


public class _Frame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel jPanel = null;  //  @jve:decl-index=0:visual-constraint="22,93"
	private JToolBar jToolBar = null;
	private JLabel jLabel4 = null;
	private JButton _btn_guardar = null;
	private JButton _btn_cancelar_tarea = null;
	private JTextField _txt_idproveedor = null;
	private JTable jTable;
	private JButton CargarPrecios = null;
	private JScrollPane jScrollPane = null;
	private JTextField _txt_proveedor_descripcion = null;
	private JButton _btn_buscar_proveedor = null;
	private JLabel jLabel2 = null;
	private JTextField _txt_linea = null;
	private JProgressBar jProgressBar = null;
	private JButton _btn_cancelar = null;
	private JButton _btn_salir = null;
	private JButton _btn_error = null;
	private JTextField _txt_prefijo = null;
	private JComboBox _lst_idcodigo = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JTextField _txt_descripcion = null;
	private JComboBox _lst_descripcion1 = null;
	private JComboBox _lst_descripcion2 = null;
	private JLabel jLabel3 = null;
	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(19, 241, 75, 15));
			jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3.setText("Descripcion");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(18, 217, 73, 16));
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setText("Codigo");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(18, 195, 72, 15));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setText("Prefijo");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(17, 35, 99, 20));
			jLabel2.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2.setText("Linea");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(20, 12, 96, 18));
			jLabel4.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel4.setText("idProveedor");
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBounds(new Rectangle(-1, 26, 1024, 720));
			jPanel.add(jLabel4, null);
			jPanel.add(get_txt_idproveedor(), null);
			jPanel.add(getJScrollPane(), null);
			jPanel.add(get_txt_proveedor_descripcion(), null);
			jPanel.add(get_btn_buscar_proveedor(), null);
			jPanel.add(jLabel2, null);
			jPanel.add(get_txt_linea(), null);
			jPanel.add(getJProgressBar(), null);
			jPanel.add(get_btn_cancelar_tarea(), null);
			jPanel.add(get_txt_prefijo(), null);
			jPanel.add(get_lst_idcodigo(), null);
			jPanel.add(jLabel, null);
			jPanel.add(jLabel1, null);
			jPanel.add(get_txt_descripcion(), null);
			jPanel.add(get_lst_descripcion1(), null);
			jPanel.add(get_lst_descripcion2(), null);
			jPanel.add(jLabel3, null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jToolBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar() {
		if (jToolBar == null) {
			jToolBar = new JToolBar();
			jToolBar.setBounds(new Rectangle(7, 3, 992, 22));
			jToolBar.setFloatable(false);
			jToolBar.add(get_btn_guardar());
			jToolBar.add(get_btn_cancelar());
			jToolBar.add(get_btn_salir());
			jToolBar.addSeparator();
			jToolBar.add(get_btn_error());
		}
		return jToolBar;
	}
	
	
	/**
	 * This method initializes Table1	
	 * 	
	 * @return javax.swing.JTable	
	 */
	

	/**
	 * This method initializes _btn_guardar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_guardar() {
		if (_btn_guardar == null) {
			_btn_guardar = new JButton();
			_btn_guardar.setToolTipText("Guardar");
			URL resourceURL = getClass().getClassLoader().getResource("beta/tools/icons/filesave.png");
			_btn_guardar.setIcon(new ImageIcon(resourceURL));
			
		}
		return _btn_guardar;
	}

	/**
	 * This method initializes _btn_cancelar_tarea	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cancelar_tarea() {
		if (_btn_cancelar_tarea == null) {
			_btn_cancelar_tarea = new JButton();
			_btn_cancelar_tarea.setToolTipText("Cancelar Tarea en progreso");
			_btn_cancelar_tarea.setBounds(new Rectangle(647, 279, 28, 18));
			URL resourceURL = getClass().getClassLoader().getResource("beta/tools/icons/stock_calc-cancel.png");
			_btn_cancelar_tarea.setIcon(new ImageIcon(resourceURL));
		}
		return _btn_cancelar_tarea;
	}

	/**
	 * This method initializes _txt_idproveedor	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idproveedor() {
		if (_txt_idproveedor == null) {
			_txt_idproveedor = new JTextField();
			_txt_idproveedor.setBounds(new Rectangle(120, 13, 110, 17));
		}
		return _txt_idproveedor;
	}

	public void eval_linea(JTextField tx,int row,int col){
		String aux=tx.getText();
		jTable.changeSelection(row, col+1, false, false);
		jTable.editCellAt(row, col+1);
		jTable.transferFocus();	
	}
	public void eval_lineaproveedor(JTextField tx,int row,int col){
		String aux=tx.getText();
		jTable.changeSelection(row+1, 0, false, false);
		jTable.editCellAt(row+1, 0);
		jTable.transferFocus();	
	}
	
	
	
	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(16, 69, 904, 112));
			jScrollPane.setViewportView(getJTable());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTable1	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getJTable() {
		if (jTable == null) {
			jTable = new JTable();
		}
		return jTable;
	}

	public void setJTable(JTable jtable){
		this.jTable=jtable;
		this.jScrollPane.setViewportView(jTable);
	}
	/**
	 * This method initializes _txt_proveedor_descripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_proveedor_descripcion() {
		if (_txt_proveedor_descripcion == null) {
			_txt_proveedor_descripcion = new JTextField();
			_txt_proveedor_descripcion.setBounds(new Rectangle(266, 13, 332, 18));
			_txt_proveedor_descripcion.setEditable(false);
		}
		return _txt_proveedor_descripcion;
	}

	/**
	 * This method initializes _btn_buscar_proveedor	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_proveedor() {
		if (_btn_buscar_proveedor == null) {
			_btn_buscar_proveedor = new JButton();
			_btn_buscar_proveedor.setBounds(new Rectangle(232, 12, 20, 17));
			URL resourceURL = getClass().getClassLoader().getResource("beta/tools/icons/search.png");
			_btn_buscar_proveedor.setIcon(new ImageIcon(resourceURL));
			
		}
		return _btn_buscar_proveedor;
	}

	/**
	 * This method initializes _txt_linea	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_linea() {
		if (_txt_linea == null) {
			_txt_linea = new JTextField();
			_txt_linea.setBounds(new Rectangle(120, 38, 200, 17));
		
		}
		return _txt_linea;
	}

	/**
	 * This method initializes jProgressBar	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	public JProgressBar getJProgressBar() {
		if (jProgressBar == null) {
			jProgressBar = new JProgressBar();
			jProgressBar.setBounds(new Rectangle(17, 279, 629, 18));
			jProgressBar.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return jProgressBar;
	}

	/**
	 * This method initializes _btn_cancelar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cancelar() {
		if (_btn_cancelar == null) {
			_btn_cancelar = new JButton();
			_btn_cancelar.setToolTipText("Limpiar Aplicacion");
			_btn_cancelar.setBounds(new Rectangle(647, 190, 28, 18));
			URL resourceURL = getClass().getClassLoader().getResource("beta/tools/icons/stock_calc-cancel.png");
			_btn_cancelar.setIcon(new ImageIcon(resourceURL));
			
		}
		return _btn_cancelar;
	}

	/**
	 * This method initializes _btn_salir	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_salir() {
		if (_btn_salir == null) {
			_btn_salir = new JButton();
			URL resourceURL = getClass().getClassLoader().getResource("beta/tools/icons/exit.png");
			_btn_salir.setIcon(new ImageIcon(resourceURL));
			
		}
		return _btn_salir;
	}

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
		
		this.setTitle("Planilla Edicion de Alias");
		this.setResizable(false);
		this.setContentPane(getJContentPane());
		
		this.setBounds(new Rectangle(0, 0, 1024, 740));
		
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
			jContentPane.add(getJPanel(),null);
		}
		return jContentPane;
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

	/**
	 * This method initializes _txt_prefijo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField get_txt_prefijo() {
		if (_txt_prefijo == null) {
			_txt_prefijo = new JTextField();
			_txt_prefijo.setBounds(new Rectangle(97, 194, 120, 16));
			_txt_prefijo.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_prefijo;
	}

	/**
	 * This method initializes _lst_idcodigo	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox get_lst_idcodigo() {
		if (_lst_idcodigo == null) {
			_lst_idcodigo = new JComboBox();
			_lst_idcodigo.setBounds(new Rectangle(98, 217, 120, 16));
			_lst_idcodigo.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _lst_idcodigo;
	}

	/**
	 * This method initializes _txt_descripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField get_txt_descripcion() {
		if (_txt_descripcion == null) {
			_txt_descripcion = new JTextField();
			_txt_descripcion.setBounds(new Rectangle(99, 241, 120, 16));
		}
		return _txt_descripcion;
	}

	/**
	 * This method initializes _lst_descripcion1	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox get_lst_descripcion1() {
		if (_lst_descripcion1 == null) {
			_lst_descripcion1 = new JComboBox();
			_lst_descripcion1.setBounds(new Rectangle(238, 241, 120, 16));
		}
		return _lst_descripcion1;
	}

	/**
	 * This method initializes _lst_descripcion2	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox get_lst_descripcion2() {
		if (_lst_descripcion2 == null) {
			_lst_descripcion2 = new JComboBox();
			_lst_descripcion2.setBounds(new Rectangle(373, 241, 120, 16));
		}
		return _lst_descripcion2;
	}
	
	

}  //  @jve:decl-index=0:visual-constraint="12,17"
