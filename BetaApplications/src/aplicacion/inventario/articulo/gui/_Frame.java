package aplicacion.inventario.articulo.gui;

import aplicacion.compras.carga.items.logic.DisplayCanvas;
import beta.tools.utils.Filtro;
import beta.tools.utils.SortableSelector;
import beta.tools.utils.columna;

import java.awt.BorderLayout;
import java.awt.event.*;

import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTable;

import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Point;

import javax.swing.JButton;

import stock1.CargadorDeAplicacion;
import stock1.ColumnHeaderListener;
import stock1.Convertidor;
import stock1.Politica;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumnModel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import beta.tools.connector.GTransfer;
import javax.swing.ImageIcon;
import javax.swing.JToolBar;
import javax.swing.JTabbedPane;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JLabel jLabel = null;
	private DisplayCanvas canvas=null;
	private JTextField _txt_idarticulo = null;

	private JTextField _txt_descripcion = null;

	private JLabel jLabel2 = null;

	private JTextField _txt_linea = null;

	private JLabel jLabel3 = null;

	private JTextField _txt_precio_lista = null;

	private JLabel jLabel4 = null;
	private JLabel jLabel5 = null;

	private JTextField _txt_precio_costo = null;

	private JTextField _txt_precio_publico = null;

	private JLabel jLabel6 = null;

	private JTextField _txt_idproveedor = null;
	private JTextField _txt_descripcion_proveedor = null;

	private JLabel jLabel7 = null;

	private JTextField _txt_idpolitica = null;

	private JTextField _txt_descripcion_politica = null;

	private JLabel jLabel9 = null;

	private JTextField _txt_minimo = null;

	private JButton _btn_guardar = null;

	private JButton _btn_cancelar = null;

	private JScrollPane jScrollPane = null;

	private JTable jTable = null;

	private JButton _btn_sincronizar = null;

	private JLabel jLabel14 = null;

	private JButton _btn_eliminar = null;

	private JButton _btn_cargar_alias = null;

	private JButton _btn_editar_alias = null;

	private JToolBar jJToolBarBar = null;

	private JButton _btn_buscar_articulo = null;

	private JButton _btn_buscar_politica = null;

	private JButton _btn_editar_politica = null;

	private JButton _btn_buscar_linea = null;

	private JButton _btn_buscar_proveedor = null;

	private JToolBar jToolBar = null;

	private JButton _btn_salir = null;

	private JButton _btn_cargar = null;

	private JToolBar jToolBar1 = null;

	private JScrollPane jScrollPane1 = null;

	private JTable jTable1 = null;

	private JButton _btn_ajustar_stock = null;

	private JButton _btn_error = null;

	private JButton _btn_zoom_in = null;

	private JButton _btn_siguiente = null;

	private JButton _btn_anterior = null;

	private JPanel FotoPanel = null;

	private JTabbedPane jTabbedPane = null;

	private JPanel jPanel = null;

	private JPanel jPanel1 = null;

	private JTabbedPane jTabbedPane1 = null;

	private JPanel jPanel2 = null;

	private JButton _btn_recodificar = null;

	private JCheckBox _chk_bloquea_venta = null;

	private JCheckBox _chk_bloqueo_compras = null;

	private JButton _btn_imprimir_etiqueta = null;

	private JPanel jPanel3 = null;

	private JScrollPane jScrollPane2 = null;

	private JTable jTable2 = null;

	private JLabel jLabel1 = null;

	private JTextField _txt_stock = null;

	private JPanel jPanel4 = null;

	private JScrollPane jScrollPane3 = null;

	private JTable jTable3 = null;

	private JLabel jLabel8 = null;

	private JTextField _txt_cuenta_actualizacion = null;

	private JButton _btn_buscar_proveedor_actualizacion = null;

	private JTextField _txt_descripcion_proveedor_actualizacion = null;

	private JToolBar jToolBar2 = null;

	private JButton _btn_start = null;

	private JTextField _txt_actual = null;

	private JButton _btn_end = null;

	private JTextField _txt_total = null;

	private JToolBar jToolBar3 = null;

	private JButton _btn_zoom_out = null;

	private JLabel jLabel10 = null;

	private JTextField _txt_fecha_creacion = null;

	private JTextField _txt_iduser_creador = null;

	private JLabel jLabel11 = null;

	private JTextField _txt_iduser_modificacion = null;

	private JTextField _txt_fecha_modificacion = null;

	private JLabel jLabel12 = null;

	private JTextField _txt_iduser_actualizador = null;

	private JTextField _txt_fecha_actualizacion = null;

	private JLabel jLabel13 = null;

	private JTextField _txt_clasificacion = null;

	private JPanel jPanel5 = null;

	private JScrollPane jScrollPane4 = null;

	private JTable jTable_deposito = null;

	private JButton _btn_modificar_stock = null;

	private JToolBar jToolBar4 = null;

	private JButton _btn_transferir = null;

	private JPanel jPanel6 = null;

	private JTable jTable_control = null;

	private JTabbedPane jTabbedControl = null;

	private JLabel jLabel15 = null;

	private JTextField _txt_categoria = null;

	private JTextField _txt_categoria_ranking = null;

	private JLabel jLabel16 = null;

	private JTextField _txt_linea_descripcion = null;

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
		this.setSize(735, 632);
		this.setResizable(false);
		this.setContentPane(getJContentPane());
		this.setTitle("Articulo");
	}
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			
			jLabel16 = new JLabel();
			jLabel16.setBounds(new Rectangle(419, 36, 51, 15));
			jLabel16.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel16.setText("Rotacion");
			jLabel15 = new JLabel();
			jLabel15.setBounds(new Rectangle(306, 36, 70, 16));
			jLabel15.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel15.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel15.setText("Categoria");
			jLabel13 = new JLabel();
			jLabel13.setBounds(new Rectangle(11, 123, 85, 16));
			jLabel13.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel13.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel13.setText("Clasificacion");
			jLabel12 = new JLabel();
			jLabel12.setBounds(new Rectangle(11, 352, 82, 16));
			jLabel12.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel12.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel12.setText("Actualizado");
			jLabel11 = new JLabel();
			jLabel11.setBounds(new Rectangle(11, 330, 82, 15));
			jLabel11.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel11.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel11.setText("Modificacion");
			jLabel10 = new JLabel();
			jLabel10.setBounds(new Rectangle(11, 307, 82, 16));
			jLabel10.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel10.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel10.setText("Creado Por");
			jLabel8 = new JLabel();
			jLabel8.setBounds(new Rectangle(8, 144, 88, 18));
			jLabel8.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel8.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel8.setText("Actualizacion");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(9, 284, 84, 17));
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel1.setText("Stock Actual");
			jLabel14 = new JLabel();
			jLabel14.setBounds(new Rectangle(3, 56, 90, 20));
			jLabel14.setText("Descripcion");
			jLabel14.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel14.setFont(new Font("Tahoma", Font.PLAIN, 10));
			jLabel9 = new JLabel();
			jLabel9.setBounds(new Rectangle(11, 259, 84, 18));
			jLabel9.setFont(new Font("Tahoma", Font.PLAIN, 10));
			jLabel9.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel9.setText("Stock Minimo");
			jLabel7 = new JLabel();
			jLabel7.setText("Politica");
			jLabel7.setHorizontalTextPosition(SwingConstants.RIGHT);
			jLabel7.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel7.setBounds(new Rectangle(24, 188, 71, 19));
			jLabel7.setFont(new Font("Tahoma", Font.PLAIN, 10));
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(16, 81, 80, 18));
			jLabel6.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel6.setFont(new Font("Tahoma", Font.PLAIN, 10));
			jLabel6.setText("Proveedor");
			jLabel5 = new JLabel();
			jLabel5.setText("Precio Publico $");
			jLabel5.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel5.setBounds(new Rectangle(12, 236, 82, 20));
			jLabel5.setFont(new Font("Tahoma", Font.PLAIN, 10));
			jLabel4 = new JLabel();
			jLabel4.setText("Precio de Costo $");
			jLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel4.setBounds(new Rectangle(10, 214, 86, 17));
			jLabel4.setFont(new Font("Tahoma", Font.PLAIN, 10));
			jLabel3 = new JLabel();
			jLabel3.setText("Precio de Lista $");
			jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3.setBounds(new Rectangle(10, 166, 85, 17));
			jLabel3.setFont(new Font("Tahoma", Font.PLAIN, 10));
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(17, 102, 79, 19));
			jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2.setFont(new Font("Tahoma", Font.PLAIN, 10));
			jLabel2.setText("Linea");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(14, 36, 80, 17));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
			jLabel.setText("idArticulo");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(get_txt_idarticulo(), null);
			jContentPane.add(get_txt_descripcion(), null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(get_txt_linea(), null);
			jContentPane.add(jLabel6, null);
			jContentPane.add(get_txt_idproveedor(), null);
			jContentPane.add(get_txt_descripcion_proveedor(), null);
			jContentPane.add(jLabel9, null);
			jContentPane.add(get_txt_minimo(), null);
			jContentPane.add(jLabel14, null);
			jContentPane.add(getJJToolBarBar(), null);
			jContentPane.add(get_btn_buscar_articulo(), null);
			jContentPane.add(jLabel7, null);
			jContentPane.add(get_txt_idpolitica(), null);
			jContentPane.add(get_txt_descripcion_politica(), null);
			jContentPane.add(get_btn_buscar_politica(), null);
			jContentPane.add(get_btn_editar_politica(), null);
			jContentPane.add(get_btn_buscar_linea(), null);
			jContentPane.add(get_btn_buscar_proveedor(), null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(get_txt_precio_lista(), null);
			jContentPane.add(jLabel4, null);
			jContentPane.add(get_txt_precio_costo(), null);
			jContentPane.add(jLabel5, null);
			jContentPane.add(get_txt_precio_publico(), null);
			jContentPane.add(get_btn_cargar(), null);
			
			jContentPane.add(getJTabbedPane(), null);
			jContentPane.add(getJTabbedPane1(), null);
			jContentPane.add(get_chk_bloquea_venta(), null);
			jContentPane.add(get_chk_bloqueo_compras(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(get_txt_stock(), null);
			jContentPane.add(jLabel8, null);
			jContentPane.add(get_txt_cuenta_actualizacion(), null);
			jContentPane.add(get_btn_buscar_proveedor_actualizacion(), null);
			jContentPane.add(get_txt_descripcion_proveedor_actualizacion(), null);
			jContentPane.add(jLabel10, null);
			jContentPane.add(get_txt_fecha_creacion(), null);
			jContentPane.add(get_txt_iduser_creador(), null);
			jContentPane.add(jLabel11, null);
			jContentPane.add(get_txt_iduser_modificacion(), null);
			jContentPane.add(get_txt_fecha_modificacion(), null);
			jContentPane.add(jLabel12, null);
			jContentPane.add(get_txt_iduser_actualizador(), null);
			jContentPane.add(get_txt_fecha_actualizacion(), null);
			jContentPane.add(jLabel13, null);
			jContentPane.add(get_txt_clasificacion(), null);
			jContentPane.add(jLabel15, null);
			jContentPane.add(get_txt_categoria(), null);
			jContentPane.add(get_txt_categoria_ranking(), null);
			jContentPane.add(jLabel16, null);
			jContentPane.add(get_txt_linea_descripcion(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes _txt_idarticulo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idarticulo() {
		if (_txt_idarticulo == null) {
			_txt_idarticulo = new JTextField();
			_txt_idarticulo.setBounds(new Rectangle(97, 36, 160, 17));
			_txt_idarticulo.setFont(new Font("Dialog", Font.PLAIN, 10));
			
			_txt_idarticulo.setHorizontalAlignment(JTextField.RIGHT);
			
		}
		return _txt_idarticulo;
	}
	
	
	
	/**
	 * This method initializes _txt_descripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_descripcion() {
		if (_txt_descripcion == null) {
			_txt_descripcion = new JTextField();
			_txt_descripcion.setBounds(new Rectangle(97, 58, 577, 17));
			_txt_descripcion.setHorizontalAlignment(JTextField.LEFT);
			_txt_descripcion.setFont(new Font("Dialog", Font.PLAIN, 10));
	
		}
		return _txt_descripcion;
	}

	/**
	 * This method initializes _txt_linea	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_linea() {
		if (_txt_linea == null) {
			_txt_linea = new JTextField();
			_txt_linea.setBounds(new Rectangle(97, 103, 90, 17));
			_txt_linea.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_linea.setHorizontalAlignment(JTextField.LEFT);
	
		}
		return _txt_linea;
	}

	/**
	 * This method initializes _txt_precio_lista	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_precio_lista() {
		if (_txt_precio_lista == null) {
			_txt_precio_lista = new JTextField();
			_txt_precio_lista.setHorizontalAlignment(JTextField.RIGHT);
			_txt_precio_lista.setLocation(new Point(97, 166));
			_txt_precio_lista.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_precio_lista.setSize(new Dimension(92, 17));
	
		}
		return _txt_precio_lista;
	}
	/**
	 * This method initializes _txt_precio_costo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_precio_costo() {
		if (_txt_precio_costo == null) {
			_txt_precio_costo = new JTextField();
			_txt_precio_costo.setHorizontalAlignment(JTextField.RIGHT);
			_txt_precio_costo.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_precio_costo.setBounds(new Rectangle(97, 212, 92, 17));
		}
		return _txt_precio_costo;
	}

	/**
	 * This method initializes _txt_precio_publico	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_precio_publico() {
		if (_txt_precio_publico == null) {
			_txt_precio_publico = new JTextField();
			_txt_precio_publico.setHorizontalAlignment(JTextField.RIGHT);
			_txt_precio_publico.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_precio_publico.setBounds(new Rectangle(97, 237, 92, 17));
	
		}
		return _txt_precio_publico;
	}

	/**
	 * This method initializes _txt_idproveedor	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idproveedor() {
		if (_txt_idproveedor == null) {
			_txt_idproveedor = new JTextField();
			_txt_idproveedor.setBounds(new Rectangle(97, 82, 92, 17));
			_txt_idproveedor.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_idproveedor.setToolTipText("Proveedor al que se le compra");
			_txt_idproveedor.setHorizontalAlignment(JTextField.RIGHT);
	
		}
		return _txt_idproveedor;
	}
	
	/**
	 * This method initializes _txt_descripcion_proveedor	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_descripcion_proveedor() {
		if (_txt_descripcion_proveedor == null) {
			_txt_descripcion_proveedor = new JTextField();
			_txt_descripcion_proveedor.setBounds(new Rectangle(213, 80, 253, 18));
			_txt_descripcion_proveedor.setEditable(false);
			_txt_descripcion_proveedor.setFont(new Font("Tahoma", Font.PLAIN, 10));
			_txt_descripcion_proveedor.setHorizontalAlignment(JTextField.LEFT);
		}
		return _txt_descripcion_proveedor;
	}

	/**
	 * This method initializes _txt_idpolitica	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idpolitica() {
		if (_txt_idpolitica == null) {
			_txt_idpolitica = new JTextField();
			_txt_idpolitica.setHorizontalAlignment(JTextField.RIGHT);
			_txt_idpolitica.setLocation(new Point(97, 188));
			_txt_idpolitica.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_idpolitica.setSize(new Dimension(92, 17));
	
			
		}
		return _txt_idpolitica;
	}
	
	/**
	 * This method initializes _txt_descripcion_politica	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_descripcion_politica() {
		if (_txt_descripcion_politica == null) {
			_txt_descripcion_politica = new JTextField();
			_txt_descripcion_politica.setHorizontalAlignment(JTextField.LEFT);
			_txt_descripcion_politica.setEditable(false);
			_txt_descripcion_politica.setBounds(new Rectangle(238, 187, 228, 17));
			_txt_descripcion_politica.setFont(new Font("Tahoma", Font.PLAIN, 10));
		}
		return _txt_descripcion_politica;
	}

	/**
	 * This method initializes _txt_minimo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_minimo() {
		if (_txt_minimo == null) {
			_txt_minimo = new JTextField();
			_txt_minimo.setBounds(new Rectangle(97, 259, 92, 17));
			_txt_minimo.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_minimo.setHorizontalAlignment(JTextField.RIGHT);
		}
		return _txt_minimo;
	}

	/**
	 * This method initializes _btn_guardar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_guardar() {
		if (_btn_guardar == null) {
			_btn_guardar = new JButton();
			_btn_guardar.setIcon(new ImageIcon(getClass().getResource("/icons/filesave.png")));
			_btn_guardar.setToolTipText("Guardar");
		
		}
		return _btn_guardar;
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
			_btn_cancelar.setToolTipText("Cancelar");
		
		}
		return _btn_cancelar;
	}
	
	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(3, 23, 712, 147));
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
	
	public void setJTable1(JTable table){
		this.jTable1=table;
		this.jScrollPane1.setViewportView(jTable1);
	}
	/**
	 * This method initializes _btn_sincronizar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_sincronizar() {
		if (_btn_sincronizar == null) {
			_btn_sincronizar = new JButton();
			_btn_sincronizar.setToolTipText("Utilizar $ Precio de Alias Seleccionado");
			_btn_sincronizar.setIcon(new ImageIcon(getClass().getResource("/icons/up.png")));
			_btn_sincronizar.setEnabled(false);
			_btn_sincronizar.setText("");
		}
		return _btn_sincronizar;
	}
	/**
	 * This method initializes _btn_eliminar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_eliminar() {
		if (_btn_eliminar == null) {
			_btn_eliminar = new JButton();
			_btn_eliminar.setIcon(new ImageIcon(getClass().getResource("/icons/edit-delete.png")));
			_btn_eliminar.setEnabled(false);
			_btn_eliminar.setToolTipText("Eliminar");
		}
		return _btn_eliminar;
	}
	/**
	 * This method initializes _btn_cargar_alias	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cargar_alias() {
		if (_btn_cargar_alias == null) {
			_btn_cargar_alias = new JButton();
			_btn_cargar_alias.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-refresh.png")));
			_btn_cargar_alias.setEnabled(false);
			_btn_cargar_alias.setToolTipText("Cargar Alias de Actualizacion");
		}
		return _btn_cargar_alias;
	}

	/**
	 * This method initializes _btn_editar_alias	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_editar_alias() {
		if (_btn_editar_alias == null) {
			_btn_editar_alias = new JButton();
			_btn_editar_alias.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-run.png")));
			_btn_editar_alias.setEnabled(false);
			_btn_editar_alias.setToolTipText("Editar Alias de Actualizacion del Articulo");
		}
		return _btn_editar_alias;
	}

	/**
	 * This method initializes jJToolBarBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJJToolBarBar() {
		if (jJToolBarBar == null) {
			jJToolBarBar = new JToolBar();
			jJToolBarBar.setBounds(new Rectangle(5, 3, 722, 25));
			jJToolBarBar.setFloatable(false);
			jJToolBarBar.add(get_btn_cancelar());
			jJToolBarBar.add(get_btn_eliminar());
			jJToolBarBar.add(get_btn_guardar());
			jJToolBarBar.add(get_btn_recodificar());
			jJToolBarBar.add(get_btn_imprimir_etiqueta());
			jJToolBarBar.add(get_btn_salir());
			jJToolBarBar.addSeparator();
			jJToolBarBar.add(get_btn_error());
		}
		return jJToolBarBar;
	}

	/**
	 * This method initializes _btn_buscar_articulo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_articulo() {
		if (_btn_buscar_articulo == null) {
			_btn_buscar_articulo = new JButton();
			_btn_buscar_articulo.setBounds(new Rectangle(258, 35, 19, 18));
			_btn_buscar_articulo.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-searchtool.png")));
		}
		return _btn_buscar_articulo;
	}

	/**
	 * This method initializes _btn_buscar_politica	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_politica() {
		if (_btn_buscar_politica == null) {
			_btn_buscar_politica = new JButton();
			_btn_buscar_politica.setBounds(new Rectangle(191, 188, 20, 16));
			_btn_buscar_politica.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-searchtool.png")));
		}
		return _btn_buscar_politica;
	}

	/**
	 * This method initializes _btn_editar_politica	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_editar_politica() {
		if (_btn_editar_politica == null) {
			_btn_editar_politica = new JButton();
			_btn_editar_politica.setBounds(new Rectangle(214, 188, 21, 16));
			_btn_editar_politica.setIcon(new ImageIcon(getClass().getResource("/icons/edit-find-replace.png")));
		}
		return _btn_editar_politica;
	}

	/**
	 * This method initializes _btn_buscar_linea	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_linea() {
		if (_btn_buscar_linea == null) {
			_btn_buscar_linea = new JButton();
			_btn_buscar_linea.setBounds(new Rectangle(445, 103, 19, 18));
			_btn_buscar_linea.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-searchtool.png")));
		}
		return _btn_buscar_linea;
	}

	/**
	 * This method initializes _btn_buscar_proveedor	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_proveedor() {
		if (_btn_buscar_proveedor == null) {
			_btn_buscar_proveedor = new JButton();
			_btn_buscar_proveedor.setBounds(new Rectangle(191, 81, 19, 17));
			_btn_buscar_proveedor.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-searchtool.png")));
		}
		return _btn_buscar_proveedor;
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
			jToolBar.setToolTipText("Barra de Herramientas Alias");
			jToolBar.setBounds(new Rectangle(3, 1, 711, 21));
			jToolBar.add(get_btn_cargar_alias());
			jToolBar.add(get_btn_sincronizar());
			jToolBar.add(get_btn_editar_alias());
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

	/**
	 * This method initializes _btn_cargar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cargar() {
		if (_btn_cargar == null) {
			_btn_cargar = new JButton();
			_btn_cargar.setBounds(new Rectangle(280, 35, 19, 18));
			_btn_cargar.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-refresh.png")));
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
			jToolBar1.setToolTipText("Barra De Herramientas Stock");
			jToolBar1.setFloatable(false);
			jToolBar1.setBounds(new Rectangle(3, 3, 703, 22));
			jToolBar1.add(get_btn_ajustar_stock());
		}
		return jToolBar1;
	}

	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setBounds(new Rectangle(3, 27, 703, 122));
			jScrollPane1.setViewportView(getJTable1());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes jTable1	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getJTable1() {
		if (jTable1 == null) {
			jTable1 = new JTable();
		}
		return jTable1;
	}

	/**
	 * This method initializes _btn_ajustar_stock	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_ajustar_stock() {
		if (_btn_ajustar_stock == null) {
			_btn_ajustar_stock = new JButton();
			_btn_ajustar_stock.setToolTipText("Ajustar Stock");
			_btn_ajustar_stock.setEnabled(false);
			_btn_ajustar_stock.setIcon(new ImageIcon(getClass().getResource("/icons/applications-system.png")));
		}
		return _btn_ajustar_stock;
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
	 * This method initializes _btn_zoom_in	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_zoom_in() {
		if (_btn_zoom_in == null) {
			_btn_zoom_in = new JButton();
			_btn_zoom_in.setIcon(new ImageIcon(getClass().getResource("/icons/add.png")));
		}
		return _btn_zoom_in;
	}

	/**
	 * This method initializes _btn_siguiente	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_siguiente() {
		if (_btn_siguiente == null) {
			_btn_siguiente = new JButton();
			_btn_siguiente.setIcon(new ImageIcon(getClass().getResource("/icons/go-next.png")));
		}
		return _btn_siguiente;
	}

	/**
	 * This method initializes _btn_anterior	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_anterior() {
		if (_btn_anterior == null) {
			_btn_anterior = new JButton();
			_btn_anterior.setIcon(new ImageIcon(getClass().getResource("/icons/back.png")));
		}
		return _btn_anterior;
	}

	/**
	 * This method initializes FotoPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	

	public DisplayCanvas getCanvas(){
		if (canvas==null){
			canvas = new DisplayCanvas();
			canvas.setBounds(new Rectangle(7, 28, 201, 185));
		}
		
		return canvas;
	
	}
	/**
	 * This method initializes jTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.setBounds(new Rectangle(3, 393, 723, 202));
			
			jTabbedPane.addTab("Alias", null, getJPanel1(), null);
			jTabbedPane.addTab("Movimientos", null, getJPanel(), null);
			jTabbedPane.addTab("Stock", null, getJPanel5(), null);
			jTabbedPane.addTab("Equivalencias", null, getJPanel3(), null);
			jTabbedPane.addTab("Compras", null, getJPanel4(), null);
			jTabbedPane.addTab("Punto de Control", null, getJPanel6(), null);
			
		}
		return jTabbedPane;
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
			jPanel.add(getJScrollPane1(), null);
			jPanel.add(getJToolBar1(), null);
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
			jPanel1 = new JPanel();
			jPanel1.setLayout(null);
			jPanel1.add(getJScrollPane(), null);
			jPanel1.add(getJToolBar(), null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes jTabbedPane1	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJTabbedPane1() {
		if (jTabbedPane1 == null) {
			jTabbedPane1 = new JTabbedPane();
			jTabbedPane1.setBounds(new Rectangle(469, 79, 257, 256));
			jTabbedPane1.addTab("Multimedia", null, getJPanel2(), null);
		}
		return jTabbedPane1;
	}

	/**
	 * This method initializes jPanel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setLayout(null);
			jPanel2.add(getCanvas(), null);
			jPanel2.add(getJToolBar2(), null);
			jPanel2.add(getJToolBar3(), null);
		}
		return jPanel2;
	}

	/**
	 * This method initializes _btn_recodificar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_recodificar() {
		if (_btn_recodificar == null) {
			_btn_recodificar = new JButton();
			_btn_recodificar.setIcon(new ImageIcon(getClass().getResource("/icons/applications-system.png")));
			_btn_recodificar.setEnabled(false);
			_btn_recodificar.setToolTipText("Recodificar");
		}
		return _btn_recodificar;
	}

	/**
	 * This method initializes _chk_bloquea_venta	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_bloquea_venta() {
		if (_chk_bloquea_venta == null) {
			_chk_bloquea_venta = new JCheckBox();
			_chk_bloquea_venta.setBounds(new Rectangle(295, 213, 170, 16));
			_chk_bloquea_venta.setFont(new Font("Dialog", Font.BOLD, 10));
			_chk_bloquea_venta.setEnabled(false);
			_chk_bloquea_venta.setText("Bloqueo Para Ventas");
		}
		return _chk_bloquea_venta;
	}

	/**
	 * This method initializes _chk_bloqueo_compras	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_bloqueo_compras() {
		if (_chk_bloqueo_compras == null) {
			_chk_bloqueo_compras = new JCheckBox();
			_chk_bloqueo_compras.setFont(new Font("Dialog", Font.BOLD, 10));
			_chk_bloqueo_compras.setEnabled(false);
			_chk_bloqueo_compras.setSize(new Dimension(172, 18));
			_chk_bloqueo_compras.setLocation(new Point(295, 237));
			_chk_bloqueo_compras.setText("Bloqueo Para Compras");
		}
		return _chk_bloqueo_compras;
	}

	/**
	 * This method initializes _btn_imprimir_etiqueta	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_imprimir_etiqueta() {
		if (_btn_imprimir_etiqueta == null) {
			_btn_imprimir_etiqueta = new JButton();
			_btn_imprimir_etiqueta.setIcon(new ImageIcon(getClass().getResource("/icons/emblem-sales-24.png")));
			_btn_imprimir_etiqueta.setToolTipText("Imprimir Etiqueta");
			_btn_imprimir_etiqueta.setEnabled(false);
		}
		return _btn_imprimir_etiqueta;
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
			jPanel3.add(getJScrollPane2(), null);
		}
		return jPanel3;
	}

	/**
	 * This method initializes jScrollPane2	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane2() {
		if (jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setBounds(new Rectangle(7, 9, 663, 163));
			jScrollPane2.setViewportView(getJTable2());
		}
		return jScrollPane2;
	}
public void setJTable2(JTable table){
	this.jTable2=table;
	this.jScrollPane2.setViewportView(table);
}
	/**
	 * This method initializes jTable2	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getJTable2() {
		if (jTable2 == null) {
			jTable2 = new JTable();
		}
		return jTable2;
	}

	/**
	 * This method initializes _txt_stock	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_stock() {
		if (_txt_stock == null) {
			_txt_stock = new JTextField();
			_txt_stock.setBackground(Color.black);
			_txt_stock.setForeground(Color.white);
			_txt_stock.setHorizontalAlignment(JTextField.RIGHT);
			_txt_stock.setSize(new Dimension(92, 17));
			_txt_stock.setLocation(new Point(97, 283));
			_txt_stock.setToolTipText("Stock Total");
			_txt_stock.setEditable(false);
		}
		return _txt_stock;
	}

	/**
	 * This method initializes jPanel4	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel4() {
		if (jPanel4 == null) {
			jPanel4 = new JPanel();
			jPanel4.setLayout(null);
			jPanel4.add(getJScrollPane3(), null);
		}
		return jPanel4;
	}

	/**
	 * This method initializes jScrollPane3	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane3() {
		if (jScrollPane3 == null) {
			jScrollPane3 = new JScrollPane();
			jScrollPane3.setBounds(new Rectangle(8, 10, 698, 161));
			jScrollPane3.setViewportView(getJTable3());
		}
		return jScrollPane3;
	}

	/**
	 * This method initializes jTable3	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getJTable3() {
		if (jTable3 == null) {
			jTable3 = new JTable();
		}
		return jTable3;
	}

	public void setJTable3(JTable table){
		this.jTable3=table;
		this.jScrollPane3.setViewportView(jTable3);
	}

	/**
	 * This method initializes _txt_cuenta_actualizacion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_cuenta_actualizacion() {
		if (_txt_cuenta_actualizacion == null) {
			_txt_cuenta_actualizacion = new JTextField();
			_txt_cuenta_actualizacion.setSize(new Dimension(90, 17));
			_txt_cuenta_actualizacion.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_cuenta_actualizacion.setToolTipText("Proveedor Utilizado Para Actualizacion");
			_txt_cuenta_actualizacion.setHorizontalAlignment(JTextField.RIGHT);
			_txt_cuenta_actualizacion.setLocation(new Point(97, 144));
		}
		return _txt_cuenta_actualizacion;
	}

	/**
	 * This method initializes _btn_buscar_proveedor_actualizacion	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_proveedor_actualizacion() {
		if (_btn_buscar_proveedor_actualizacion == null) {
			_btn_buscar_proveedor_actualizacion = new JButton();
			_btn_buscar_proveedor_actualizacion.setSize(new Dimension(19, 17));
			_btn_buscar_proveedor_actualizacion.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-searchtool.png")));
			_btn_buscar_proveedor_actualizacion.setLocation(new Point(192, 143));
		}
		return _btn_buscar_proveedor_actualizacion;
	}

	/**
	 * This method initializes _txt_descripcion_proveedor_actualizacion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_descripcion_proveedor_actualizacion() {
		if (_txt_descripcion_proveedor_actualizacion == null) {
			_txt_descripcion_proveedor_actualizacion = new JTextField();
			_txt_descripcion_proveedor_actualizacion.setLocation(new Point(214, 144));
			_txt_descripcion_proveedor_actualizacion.setHorizontalAlignment(JTextField.LEFT);
			_txt_descripcion_proveedor_actualizacion.setEditable(false);
			_txt_descripcion_proveedor_actualizacion.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_descripcion_proveedor_actualizacion.setSize(new Dimension(247, 17));
		}
		return _txt_descripcion_proveedor_actualizacion;
	}

	/**
	 * This method initializes jToolBar2	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar2() {
		if (jToolBar2 == null) {
			jToolBar2 = new JToolBar();
			jToolBar2.setBounds(new Rectangle(7, 3, 172, 24));
			jToolBar2.setFloatable(false);
			jToolBar2.add(get_btn_start());
			jToolBar2.add(get_btn_anterior());
			jToolBar2.add(get_txt_actual());
			jToolBar2.add(get_btn_siguiente());
			jToolBar2.add(get_btn_end());
			jToolBar2.add(get_txt_total());
		}
		return jToolBar2;
	}

	/**
	 * This method initializes _btn_start	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_start() {
		if (_btn_start == null) {
			_btn_start = new JButton();
			_btn_start.setIcon(new ImageIcon(getClass().getResource("/icons/go-first.png")));
		}
		return _btn_start;
	}

	/**
	 * This method initializes _txt_actual	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_actual() {
		if (_txt_actual == null) {
			_txt_actual = new JTextField();
			_txt_actual.setEditable(false);
		}
		return _txt_actual;
	}

	/**
	 * This method initializes _btn_end	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_end() {
		if (_btn_end == null) {
			_btn_end = new JButton();
			_btn_end.setIcon(new ImageIcon(getClass().getResource("/icons/finish.png")));
		}
		return _btn_end;
	}

	/**
	 * This method initializes _txt_total	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_total() {
		if (_txt_total == null) {
			_txt_total = new JTextField();
			_txt_total.setEditable(false);
		}
		return _txt_total;
	}

	/**
	 * This method initializes jToolBar3	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar3() {
		if (jToolBar3 == null) {
			jToolBar3 = new JToolBar();
			jToolBar3.setBounds(new Rectangle(210, 29, 32, 151));
			jToolBar3.setFloatable(false);
			jToolBar3.setOrientation(JToolBar.VERTICAL);
			jToolBar3.add(get_btn_zoom_in());
			jToolBar3.add(get_btn_zoom_out());
		}
		return jToolBar3;
	}

	/**
	 * This method initializes _btn_zoom_out	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_zoom_out() {
		if (_btn_zoom_out == null) {
			_btn_zoom_out = new JButton();
			_btn_zoom_out.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-remove.png")));
		}
		return _btn_zoom_out;
	}

	/**
	 * This method initializes _txt_fecha_creacion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_fecha_creacion() {
		if (_txt_fecha_creacion == null) {
			_txt_fecha_creacion = new JTextField();
			_txt_fecha_creacion.setEditable(false);
			_txt_fecha_creacion.setLocation(new Point(194, 307));
			_txt_fecha_creacion.setSize(new Dimension(139, 17));
			_txt_fecha_creacion.setToolTipText("Fecha de Creacion");
			_txt_fecha_creacion.setHorizontalAlignment(JTextField.RIGHT);
			_txt_fecha_creacion.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_fecha_creacion;
	}

	/**
	 * This method initializes _txt_iduser_creador	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_iduser_creador() {
		if (_txt_iduser_creador == null) {
			_txt_iduser_creador = new JTextField();
			_txt_iduser_creador.setBounds(new Rectangle(98, 307, 90, 16));
			_txt_iduser_creador.setHorizontalAlignment(JTextField.RIGHT);
			_txt_iduser_creador.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_iduser_creador.setEditable(false);
		}
		return _txt_iduser_creador;
	}

	/**
	 * This method initializes _txt_iduser_modificacion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_iduser_modificacion() {
		if (_txt_iduser_modificacion == null) {
			_txt_iduser_modificacion = new JTextField();
			_txt_iduser_modificacion.setLocation(new Point(98, 330));
			_txt_iduser_modificacion.setEditable(false);
			_txt_iduser_modificacion.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_iduser_modificacion.setHorizontalAlignment(JTextField.RIGHT);
			_txt_iduser_modificacion.setSize(new Dimension(90, 17));
		}
		return _txt_iduser_modificacion;
	}

	/**
	 * This method initializes _txt_fecha_modificacion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_fecha_modificacion() {
		if (_txt_fecha_modificacion == null) {
			_txt_fecha_modificacion = new JTextField();
			_txt_fecha_modificacion.setLocation(new Point(193, 330));
			_txt_fecha_modificacion.setEditable(false);
			_txt_fecha_modificacion.setHorizontalAlignment(JTextField.RIGHT);
			_txt_fecha_modificacion.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_fecha_modificacion.setToolTipText("Fecha Ultima Modificacion");
			_txt_fecha_modificacion.setSize(new Dimension(140, 17));
		}
		return _txt_fecha_modificacion;
	}

	/**
	 * This method initializes _txt_iduser_actualizador	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_iduser_actualizador() {
		if (_txt_iduser_actualizador == null) {
			_txt_iduser_actualizador = new JTextField();
			_txt_iduser_actualizador.setBounds(new Rectangle(98, 353, 89, 16));
			_txt_iduser_actualizador.setEditable(false);
			_txt_iduser_actualizador.setHorizontalAlignment(JTextField.RIGHT);
			_txt_iduser_actualizador.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_iduser_actualizador;
	}

	/**
	 * This method initializes _txt_fecha_actualizacion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_fecha_actualizacion() {
		if (_txt_fecha_actualizacion == null) {
			_txt_fecha_actualizacion = new JTextField();
			_txt_fecha_actualizacion.setBounds(new Rectangle(194, 352, 138, 17));
			_txt_fecha_actualizacion.setEditable(false);
			_txt_fecha_actualizacion.setHorizontalAlignment(JTextField.RIGHT);
			_txt_fecha_actualizacion.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_fecha_actualizacion;
	}

	/**
	 * This method initializes _txt_clasificacion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_clasificacion() {
		if (_txt_clasificacion == null) {
			_txt_clasificacion = new JTextField();
			_txt_clasificacion.setBounds(new Rectangle(97, 122, 341, 17));
			_txt_clasificacion.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_clasificacion;
	}

	/**
	 * This method initializes jPanel5	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel5() {
		if (jPanel5 == null) {
			jPanel5 = new JPanel();
			jPanel5.setLayout(null);
			jPanel5.add(getJScrollPane4(), null);
			jPanel5.add(getJToolBar4(), null);
		}
		return jPanel5;
	}

	/**
	 * This method initializes jScrollPane4	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane4() {
		if (jScrollPane4 == null) {
			jScrollPane4 = new JScrollPane();
			jScrollPane4.setBounds(new Rectangle(5, 35, 699, 115));
			jScrollPane4.setViewportView(getJTable_deposito());
		}
		return jScrollPane4;
	}

	/**
	 * This method initializes jTable_deposito	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getJTable_deposito() {
		if (jTable_deposito == null) {
			jTable_deposito = new JTable();
		}
		return jTable_deposito;
	}
	
	public void setJTable_deposito(JTable table){
		this.jTable_deposito=table;
		this.jScrollPane4.setViewportView(jTable_deposito);
	}

	/**
	 * This method initializes _btn_modificar_stock	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_modificar_stock() {
		if (_btn_modificar_stock == null) {
			_btn_modificar_stock = new JButton();
			_btn_modificar_stock.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-run.png")));
			_btn_modificar_stock.setToolTipText("Modificar Stock Manualmente");
		}
		return _btn_modificar_stock;
	}

	/**
	 * This method initializes jToolBar4	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar4() {
		if (jToolBar4 == null) {
			jToolBar4 = new JToolBar();
			jToolBar4.setBounds(new Rectangle(2, 2, 714, 29));
			jToolBar4.setFloatable(false);
			jToolBar4.add(get_btn_modificar_stock());
			jToolBar4.add(get_btn_transferir());
		}
		return jToolBar4;
	}

	/**
	 * This method initializes _btn_transferir	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_transferir() {
		if (_btn_transferir == null) {
			_btn_transferir = new JButton();
			_btn_transferir.setIcon(new ImageIcon(getClass().getResource("/icons/mail-send-receive.png")));
			_btn_transferir.setToolTipText("Transferir Stock");
		}
		return _btn_transferir;
	}

	/**
	 * This method initializes jPanel6	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel6() {
		if (jPanel6 == null) {
			jPanel6 = new JPanel();
			jPanel6.setLayout(null);
			jPanel6.add(getJTabbedControl(), null);
		}
		return jPanel6;
	}

	/**
	 * This method initializes jTabbedControl	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	public JTabbedPane getJTabbedControl() {
		if (jTabbedControl == null) {
			jTabbedControl = new JTabbedPane();
			jTabbedControl.setBounds(new Rectangle(6, 6, 706, 162));
		}
		return jTabbedControl;
	}

	/**
	 * This method initializes _txt_categoria	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_categoria() {
		if (_txt_categoria == null) {
			_txt_categoria = new JTextField();
			_txt_categoria.setBounds(new Rectangle(380, 34, 34, 20));
			_txt_categoria.setEditable(false);
			_txt_categoria.setFont(new Font("Dialog", Font.BOLD, 14));
			_txt_categoria.setText("A");
			_txt_categoria.setHorizontalAlignment(JTextField.CENTER);
		}
		return _txt_categoria;
	}

	/**
	 * This method initializes _txt_categoria_ranking	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_categoria_ranking() {
		if (_txt_categoria_ranking == null) {
			_txt_categoria_ranking = new JTextField();
			_txt_categoria_ranking.setBounds(new Rectangle(472, 36, 31, 16));
			_txt_categoria_ranking.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_categoria_ranking.setToolTipText("Ranking Dentro de la Categoria");
			_txt_categoria_ranking.setEditable(false);
		}
		return _txt_categoria_ranking;
	}

	/**
	 * This method initializes _txt_linea_descripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_linea_descripcion() {
		if (_txt_linea_descripcion == null) {
			_txt_linea_descripcion = new JTextField();
			_txt_linea_descripcion.setEditable(false);
			_txt_linea_descripcion.setBounds(new Rectangle(190, 105, 244, 14));
			_txt_linea_descripcion.setHorizontalAlignment(JTextField.LEFT);
			_txt_linea_descripcion.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_linea_descripcion;
	}

	
}  //  @jve:decl-index=0:visual-constraint="10,10"
