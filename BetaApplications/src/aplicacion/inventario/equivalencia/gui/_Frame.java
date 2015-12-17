package aplicacion.inventario.equivalencia.gui;

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
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel4 = null;
	private JButton _btn_guardar = null;
	private JButton _btn_cancelar_tarea = null;
	private JTextField _txt_idarticulo_desde = null;
	private JTextField _txt_idproveedor = null;
	private JTable jTable;
	private JButton _btn_cargar = null;
	private JToolBar jToolBar1 = null;
	private JButton _btn_completar = null;
	private JButton CargarPrecios = null;
	private JTextField _txt_idarticulo_hasta = null;
	private JButton _btn_autoalias = null;
	private JTextField _txt_art_desde = null;
	private JLabel jLabel8 = null;
	private JTextField _txt_art_qty = null;
	private JCheckBox _chk_art_substring = null;
	private JTextField _txt_prefijo_codigo = null;
	private JLabel jLabel7 = null;
	private JCheckBox _chk_carcateres = null;
	private JLabel jLabel9 = null;
	private JTextField _txt_precode = null;
	private JCheckBox _chk_lineas = null;
	private JButton _btn_borrar = null;
	private JScrollPane jScrollPane = null;
	private JTextField _txt_proveedor_descripcion = null;
	private JPanel jPanel_opciones = null;
	private JButton _btn_buscar_articulo_desde = null;
	private JButton _btn_buscar_articulo_hasta = null;
	private JButton _btn_buscar_proveedor = null;
	private JCheckBox _chk_seleccionar = null;
	private JButton _btn_copiar = null;
	private JButton _btn_pegar = null;
	private JLabel jLabel2 = null;
	private JButton _btn_buscar_linea = null;
	private JTextField _txt_linea = null;
	private JProgressBar jProgressBar = null;
	private JButton _btn_cancelar = null;
	private JButton _btn_salir = null;
	private JCheckBox _chk_precargar_codigos = null;
	private JComboBox _list_modo = null;
	private JButton _btn_error = null;
	private JCheckBox _chk_stock = null;
	private JTextField _txt_linea_equivalente = null;
	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(17, 35, 99, 20));
			jLabel2.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2.setText("Linea");
			jLabel9 = new JLabel();
			jLabel9.setText("Prefijo Codigo:");
			jLabel9.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel9.setBounds(new Rectangle(12, 76, 101, 12));
			jLabel7 = new JLabel();
			jLabel7.setText("Prefijo Articulo");
			jLabel7.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel7.setBounds(new Rectangle(10, 57, 98, 14));
			jLabel8 = new JLabel();
			jLabel8.setText("caracteres");
			jLabel8.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel8.setBounds(new Rectangle(292, 30, 70, 18));
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(20, 12, 96, 18));
			jLabel4.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel4.setText("idProveedor");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(276, 62, 54, 18));
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setText("Hasta");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(21, 60, 97, 18));
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setText("idarticulo");
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBounds(new Rectangle(-1, 26, 1024, 720));
			jPanel.add(jLabel, null);
			jPanel.add(jLabel1, null);
			jPanel.add(jLabel4, null);
			jPanel.add(get_txt_idarticulo_desde(), null);
			jPanel.add(get_txt_idproveedor(), null);
			jPanel.add(getJToolBar1(), null);
			jPanel.add(get_txt_idarticulo_hasta(), null);
			jPanel.add(get_btn_cargar(), null);
			jPanel.add(getJScrollPane(), null);
			jPanel.add(get_txt_proveedor_descripcion(), null);
			jPanel.add(getJPanel_opciones(), null);
			jPanel.add(get_btn_buscar_articulo_desde(), null);
			jPanel.add(get_btn_buscar_articulo_hasta(), null);
			jPanel.add(get_btn_buscar_proveedor(), null);
			jPanel.add(get_chk_seleccionar(), null);
			jPanel.add(jLabel2, null);
			jPanel.add(get_btn_buscar_linea(), null);
			jPanel.add(get_txt_linea(), null);
			jPanel.add(getJProgressBar(), null);
			jPanel.add(get_btn_cancelar_tarea(), null);
			jPanel.add(get_lst_modo(), null);
			jPanel.add(get_chk_stock(), null);
			jPanel.add(get_txt_linea_equivalente(), null);
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
			_btn_cancelar_tarea.setBounds(new Rectangle(647, 190, 28, 18));
			URL resourceURL = getClass().getClassLoader().getResource("beta/tools/icons/stock_calc-cancel.png");
			_btn_cancelar_tarea.setIcon(new ImageIcon(resourceURL));
		}
		return _btn_cancelar_tarea;
	}

	/**
	 * This method initializes _txt_idarticulo_desde	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idarticulo_desde() {
		if (_txt_idarticulo_desde == null) {
			_txt_idarticulo_desde = new JTextField();
			_txt_idarticulo_desde.setBounds(new Rectangle(120, 61, 110, 17));
			
		}
		return _txt_idarticulo_desde;
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
	 * This method initializes _btn_cargar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cargar() {
		if (_btn_cargar == null) {
			_btn_cargar = new JButton();
			URL resourceURL = getClass().getClassLoader().getResource("beta/tools/icons/stock_refresh.png");
			_btn_cargar.setIcon(new ImageIcon(resourceURL));
			_btn_cargar.setBounds(new Rectangle(473, 63, 28, 18));
			_btn_cargar.setToolTipText("Cargar");
			
		}
		return _btn_cargar;
	}

	/**
	 * This method initializes jToolBar1	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar1() {
		if (jToolBar1 == null) {
			jToolBar1 = new JToolBar();
			jToolBar1.setBounds(new Rectangle(18, 212, 972, 22));
			jToolBar1.setFloatable(false);
			
			jToolBar1.add(get_btn_autoalias());
			jToolBar1.addSeparator();
			jToolBar1.addSeparator();
			jToolBar1.add(get_btn_borrar());
			jToolBar1.addSeparator();
			jToolBar1.addSeparator();
			jToolBar1.add(get_btn_completar());
			jToolBar1.addSeparator();
			jToolBar1.addSeparator();
			jToolBar1.add(get_btn_copiar());
			jToolBar1.add(get_btn_pegar());
			
		}
		return jToolBar1;
	}

	/**
	 * This method initializes _btn_completar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_completar() {
		if (_btn_completar == null) {
			_btn_completar = new JButton();
			_btn_completar.setToolTipText("Completar");
			URL resourceURL = getClass().getClassLoader().getResource("beta/tools/icons/bottom.png");
			_btn_completar.setIcon(new ImageIcon(resourceURL));
		}
		return _btn_completar;
	}

	/**
	 * This method initializes _txt_idarticulo_hasta	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idarticulo_hasta() {
		if (_txt_idarticulo_hasta == null) {
			_txt_idarticulo_hasta = new JTextField();
			_txt_idarticulo_hasta.setBounds(new Rectangle(335, 62, 108, 18));
			
			
		}
		return _txt_idarticulo_hasta;
	}

	/**
	 * This method initializes _btn_autoalias	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_autoalias() {
		if (_btn_autoalias == null) {
			_btn_autoalias = new JButton();
			_btn_autoalias.setToolTipText("Auto Alias");
			URL resourceURL = getClass().getClassLoader().getResource("beta/tools/icons/applications-system.png");
			_btn_autoalias.setIcon(new ImageIcon(resourceURL));
		}
		return _btn_autoalias;
	}

	/**
	 * This method initializes _txt_art_desde	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_art_desde() {
		if (_txt_art_desde == null) {
			_txt_art_desde = new JTextField();
			_txt_art_desde.setText("3");
			_txt_art_desde.setBounds(new Rectangle(192, 32, 48, 15));
		}
		return _txt_art_desde;
	}

	/**
	 * This method initializes _txt_art_qty	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_art_qty() {
		if (_txt_art_qty == null) {
			_txt_art_qty = new JTextField();
			_txt_art_qty.setBounds(new Rectangle(245, 32, 42, 14));
		}
		return _txt_art_qty;
	}

	/**
	 * This method initializes _chk_art_substring	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_art_substring() {
		if (_chk_art_substring == null) {
			_chk_art_substring = new JCheckBox();
			_chk_art_substring.setText("tomar articulos desde");
			_chk_art_substring.setBounds(new Rectangle(11, 34, 163, 12));
			_chk_art_substring.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_art_substring.setSelected(true);
		}
		return _chk_art_substring;
	}

	/**
	 * This method initializes _txt_prefijo_codigo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_prefijo_codigo() {
		if (_txt_prefijo_codigo == null) {
			_txt_prefijo_codigo = new JTextField();
			_txt_prefijo_codigo.setBounds(new Rectangle(116, 55, 70, 14));
		}
		return _txt_prefijo_codigo;
	}

	/**
	 * This method initializes _chk_carcateres	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_carcateres() {
		if (_chk_carcateres == null) {
			_chk_carcateres = new JCheckBox();
			_chk_carcateres.setText("obviar caracteres");
			_chk_carcateres.setBounds(new Rectangle(9, 8, 138, 14));
			_chk_carcateres.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_carcateres.setSelected(true);
		}
		return _chk_carcateres;
	}

	/**
	 * This method initializes _txt_precode	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_precode() {
		if (_txt_precode == null) {
			_txt_precode = new JTextField();
			_txt_precode.setBounds(new Rectangle(116, 75, 70, 16));
		}
		return _txt_precode;
	}

	/**
	 * This method initializes _chk_lineas	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_lineas() {
		if (_chk_lineas == null) {
			_chk_lineas = new JCheckBox();
			_chk_lineas.setText("Utiliza Linea");
			_chk_lineas.setSelected(true);
			_chk_lineas.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_lineas.setBounds(new Rectangle(191, 9, 125, 13));
		}
		return _chk_lineas;
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
			URL resourceURL = getClass().getClassLoader().getResource("beta/tools/icons/user-trash.png");
			_btn_borrar.setIcon(new ImageIcon(resourceURL));
			
		}
		return _btn_borrar;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(18, 258, 975, 369));
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
	 * This method initializes jPanel_opciones	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel_opciones() {
		if (jPanel_opciones == null) {
			jPanel_opciones = new JPanel();
			jPanel_opciones.setLayout(null);
			jPanel_opciones.setBounds(new Rectangle(21, 83, 970, 106));
			jPanel_opciones.setBackground(new Color(153, 153, 153));
			jPanel_opciones.add(get_txt_precode(), null);
			jPanel_opciones.add(jLabel9, null);
			jPanel_opciones.add(get_txt_prefijo_codigo(), null);
			jPanel_opciones.add(jLabel7, null);
			jPanel_opciones.add(jLabel8, null);
			jPanel_opciones.add(get_txt_art_qty(), null);
			jPanel_opciones.add(get_txt_art_desde(), null);
			jPanel_opciones.add(get_chk_art_substring(), null);
			jPanel_opciones.add(get_chk_lineas(), null);
			jPanel_opciones.add(get_chk_carcateres(), null);
			jPanel_opciones.add(get_chk_precargar_codigos(), null);
		}
		return jPanel_opciones;
	}

	/**
	 * This method initializes _btn_buscar_articulo_desde	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_articulo_desde() {
		if (_btn_buscar_articulo_desde == null) {
			_btn_buscar_articulo_desde = new JButton();
			_btn_buscar_articulo_desde.setBounds(new Rectangle(232, 61, 19, 16));
			URL resourceURL = getClass().getClassLoader().getResource("beta/tools/icons/search.png");
			_btn_buscar_articulo_desde.setIcon(new ImageIcon(resourceURL));
			
		}
		return _btn_buscar_articulo_desde;
	}

	/**
	 * This method initializes _btn_buscar_articulo_hasta	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_articulo_hasta() {
		if (_btn_buscar_articulo_hasta == null) {
			_btn_buscar_articulo_hasta = new JButton();
			_btn_buscar_articulo_hasta.setBounds(new Rectangle(447, 63, 21, 17));
			URL resourceURL = getClass().getClassLoader().getResource("beta/tools/icons/search.png");
			_btn_buscar_articulo_hasta.setIcon(new ImageIcon(resourceURL));
			
		}
		return _btn_buscar_articulo_hasta;
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
	 * This method initializes _chk_marcar	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_seleccionar() {
		if (_chk_seleccionar == null) {
			_chk_seleccionar = new JCheckBox();
			_chk_seleccionar.setBounds(new Rectangle(20, 238, 138, 18));
			_chk_seleccionar.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_seleccionar.setText("marcar/desmarcar");
			
			
		}
		return _chk_seleccionar;
	}

	/**
	 * This method initializes _btn_copiar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_copiar() {
		if (_btn_copiar == null) {
			_btn_copiar = new JButton();
			_btn_copiar.setToolTipText("Copiar a Memoria");
			URL resourceURL = getClass().getClassLoader().getResource("beta/tools/icons/edit-copy.png");
			_btn_copiar.setIcon(new ImageIcon(resourceURL));
			
		}
		return _btn_copiar;
	}

	/**
	 * This method initializes _btn_pegar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_pegar() {
		if (_btn_pegar == null) {
			_btn_pegar = new JButton();
			_btn_pegar.setToolTipText("Pegar");
			URL resourceURL = getClass().getClassLoader().getResource("beta/tools/icons/edit-paste.png");
			_btn_pegar.setIcon(new ImageIcon(resourceURL));
			
		}
		return _btn_pegar;
	}

	/**
	 * This method initializes _btn_buscar_linea	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_linea() {
		if (_btn_buscar_linea == null) {
			_btn_buscar_linea = new JButton();
			_btn_buscar_linea.setBounds(new Rectangle(322, 38, 20, 17));
			URL resourceURL = getClass().getClassLoader().getResource("beta/tools/icons/search.png");
			_btn_buscar_linea.setIcon(new ImageIcon(resourceURL));
		
		}
		return _btn_buscar_linea;
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
			jProgressBar.setBounds(new Rectangle(17, 190, 629, 18));
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
	 * This method initializes _chk_precargar_codigos	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_precargar_codigos() {
		if (_chk_precargar_codigos == null) {
			_chk_precargar_codigos = new JCheckBox();
			_chk_precargar_codigos.setSelected(true);
			_chk_precargar_codigos.setBounds(new Rectangle(262, 78, 290, 16));
			_chk_precargar_codigos.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_precargar_codigos.setText("Precargar consulta cuando busque codigos");
		}
		return _chk_precargar_codigos;
	}

	/**
	 * This method initializes modo	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_modo() {
		if (_list_modo == null) {
			_list_modo = new JComboBox();
			_list_modo.addItem("Articulos");
			_list_modo.addItem("Alias");
			_list_modo.setBounds(new Rectangle(505, 63, 136, 18));
		}
		return _list_modo;
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
		
		this.setTitle("Planilla Importacion de Equivalencias");
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
	 * This method initializes _chk_stock	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_stock() {
		if (_chk_stock == null) {
			_chk_stock = new JCheckBox();
			_chk_stock.setBounds(new Rectangle(659, 65, 132, 14));
			_chk_stock.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_stock.setText("stock > 0");
		}
		return _chk_stock;
	}

	/**
	 * This method initializes _txt_linea_equivalente	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_linea_equivalente() {
		if (_txt_linea_equivalente == null) {
			_txt_linea_equivalente = new JTextField();
			_txt_linea_equivalente.setBounds(new Rectangle(346, 38, 205, 17));
		}
		return _txt_linea_equivalente;
	}
	
	

}  //  @jve:decl-index=0:visual-constraint="12,17"
