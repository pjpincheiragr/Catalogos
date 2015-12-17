package aplicacion.compras.carga.pedido.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.net.URL;
import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.awt.GridBagLayout;
import java.awt.Color;
import javax.swing.DefaultCellEditor;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import java.util.*;
import javax.swing.ImageIcon;
import javax.swing.JToolBar;

import org.jdesktop.jxlayer.JXLayer;
import org.jdesktop.jxlayer.plaf.effect.BufferedImageOpEffect;
import org.jdesktop.jxlayer.plaf.ext.LockableUI;

import com.jhlabs.image.BoxBlurFilter;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import javax.swing.JSplitPane;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JButton _btn_cancelar_pedido = null;
	private JButton _btn_cargar_proveedor = null;
	private JButton _btn_buscar_fecha = null;
	private JButton _btn_guardar = null;
	private JButton _btn_imprimir_pedido = null;
	private JButton _btn_nuevo_pedido = null;
	private JCheckBox _chk_seleccionar_items = null;
	private JCheckBox _chk_seleccionar_lineas = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JLabel jLabel4 = null;
	private JLabel jLabel6 = null;
	private JLabel jLabel7 = null;
	private JPanel jContentPane = null;
	private JPanel jPanel = null;
	private JPanel jPanel1 = null;
	private JPanel jPanel_lineas = null;
	private JScrollPane jScrollPane = null;
	private JScrollPane jScrollPane1 = null;
	private JTable jTable=null;  //  @jve:decl-index=0:
	private JTable jTable1=null;
	private JTextField _txt_total_unidades = null;
	private JTextField _txt_total_compra = null;
	private JTextField _txt_items = null;
	private JTextField _txt_total = null;
	private JTextField _txt_idproveedor = null;
	private JTextField _txt_descripcion = null;
	private JTextField _txt_idpedido = null;
	private JTextField _txt_fecha = null;
	private JButton _btn_buscar_pedido = null;
	private JToolBar jToolBar = null;
	private JButton _btn_cargar_pedido = null;
	private JButton _btn_agregar_linea = null;
	private JButton _btn_quitar_linea = null;
	private JButton _btn_agregar_asteriscos = null;
	private JTextField _txt_descripcion_pedido = null;
	private JButton _btn_error = null;
	private JButton _btn_salir = null;
	private LockableUI lockableUI=null;
	private JButton _btn_fecha_desde = null;
	private JTextField _txt_hasta = null;
	private JButton _btn_fecha_hasta = null;
	private JTextField _txt_desde = null;
	private JComboBox _lst_estado = null;
	private JLabel jLabel9 = null;
	private JTextField _txt_proyectado = null;
	private JButton _btn_proyectado = null;
	private JTextField _txt_critico = null;
	private JLabel jLabel10 = null;
	private JButton _btn_critico = null;
	private JLabel jLabel11 = null;
	private JButton _btn_eliminar = null;
	private JCheckBox _chk_seguimiento = null;
	private JTextField _txt_fecha_creacion = null;
	private JScrollPane jScrollPane2 = null;
	private JTable jTable_articulos = null;
	private JTextField _txt_articulo_actualizacion = null;
	private JTextField _txt_articulo = null;
	private JTextField _txt_articulo_pedido = null;
	private JTextField _txt_articulo_linea = null;
	private JTextField _txt_articulo_bloqueado = null;
	private JTextField _txt_articulo_descripcion = null;
	private JTextField _txt_articulo_stock = null;
	private JButton _btn_email = null;
	private JTabbedPane jTabbedPane = null;
	private JPanel jPanel3 = null;
	private JButton _btn_editar_proveedor = null;
	private JLabel jLabel5 = null;
	private JPanel jPanel4 = null;
	private JLabel jLabel12 = null;
	/**
	 * This is the default constructor
	 */
	public _Frame() {
		super();
		initialize();
	}
	
	public JTable getJTable(){
		return this.jTable;
	}
	
	public void setJTable(JTable jtable){
		this.jTable=jtable;
		this.jScrollPane.setViewportView(jTable);
	}
	
	public JTable getJTable1(){
		return this.jTable1;
	}
	
	public void setJTable1(JTable jtable){
		this.jTable1=jtable;
		this.jScrollPane1.setViewportView(jTable1);
	}
	
	private void initialize() {
		this.setSize(1024, 713);
		this.setContentPane(getJContentPane());
		this.setTitle("Pedido a Proveedor");
	}

	/*
	public JXLayer<JComponent> getJXLayerUI(){
		JPanel panel = this.getJPanel_lineas();
		
		JXLayer<JComponent> l = new JXLayer<JComponent>(panel, this.getLockableUI());
		l.setBounds(new Rectangle(4, 155, 994, 524));
		
		return l;
		
	}
	*/
	public LockableUI getLockableUI(){
		if (lockableUI==null){
			lockableUI= new LockableUI();        
			BoxBlurFilter blur=new BoxBlurFilter(2,2,2);
			BufferedImageOpEffect effect = new BufferedImageOpEffect(blur);
			lockableUI.setLockedEffects(effect);
		}
		return lockableUI; 

	}
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel22 = new JLabel();
			jLabel22.setBounds(new Rectangle(470, 30, 65, 18));
			jLabel22.setToolTipText("Pide Automaticamente desde la Categoria Indicada Al generar o Finalizar el Pedido");
			jLabel22.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel22.setText("Auto Desde");
			jLabel13 = new JLabel();
			jLabel13.setBounds(new Rectangle(23, 392, 119, 15));
			jLabel13.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel13.setText("Comprador");
			jLabel3 = new JLabel();
			jLabel3.setText("Total");
			jLabel3.setBounds(new Rectangle(838, 225, 34, 22));
			jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel2 = new JLabel();
			jLabel2.setText("items");
			jLabel2.setBounds(new Rectangle(674, 228, 57, 18));
			jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1 = new JLabel();
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setBounds(new Rectangle(843, 260, 36, 14));
			jLabel1.setText("Total");
			jLabel = new JLabel();
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setBounds(new Rectangle(655, 260, 85, 16));
			jLabel.setText("unidades");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJXLayerUI(), null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(get_txt_idpedido(), null);
			jContentPane.add(jLabel6, null);
			jContentPane.add(get_btn_buscar_pedido(), null);
			jContentPane.add(get_btn_cargar_pedido(), null);
			jContentPane.add(get_txt_descripcion_pedido(), null);
			jContentPane.add(get_lst_estado(), null);
			jContentPane.add(jLabel11, null);
			jContentPane.add(get_txt_fecha(), null);
			jContentPane.add(jLabel7, null);
			jContentPane.add(get_btn_buscar_fecha(), null);
			jContentPane.add(get_chk_seguimiento(), null);
			jContentPane.add(get_txt_idproveedor(), null);
			jContentPane.add(get_btn_cargar_proveedor(), null);
			jContentPane.add(get_btn_editar_proveedor(), null);
			jContentPane.add(get_txt_descripcion(), null);
			jContentPane.add(jLabel4, null);
			jContentPane.add(getJPanel1(), null);
			jContentPane.add(get_txt_idvendedor(), null);
			jContentPane.add(get_btn_buscar_vendedor(), null);
			jContentPane.add(get_txt_vendedor_descripcion(), null);
			jContentPane.add(jLabel13, null);
			jContentPane.add(get_lst_categoria_generacion(), null);
			jContentPane.add(jLabel22, null);
			jContentPane.add(getJProgressBar(), null);
			jContentPane.add(get_btn_cancelar_tarea(), null);
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
			jLabel23 = new JLabel();
			jLabel23.setBounds(new Rectangle(271, 73, 70, 17));
			jLabel23.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel23.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel23.setText("Categoria");
			jLabel5 = new JLabel();
			jLabel5.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel5.setBounds(new Rectangle(7, 11, 95, 22));
			jLabel5.setText("Historico");
			jLabel11 = new JLabel();
			jLabel11.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel11.setBounds(new Rectangle(698, 55, 90, 14));
			jLabel11.setText("Estado");
			jLabel7 = new JLabel();
			jLabel7.setText("Fecha");
			jLabel7.setBounds(new Rectangle(698, 29, 91, 18));
			jLabel7.setFont(new Font("Tahoma", Font.PLAIN, 10));
			jLabel6 = new JLabel();
			jLabel6.setText("idPedido");
			jLabel6.setBounds(new Rectangle(7, 32, 68, 17));
			jLabel6.setFont(new Font("Tahoma", Font.PLAIN, 10));
			
			jLabel4 = new JLabel();
			jLabel4.setFont(new Font("Tahoma", Font.PLAIN, 10));
			jLabel4.setBounds(new Rectangle(6, 55, 70, 18));
			jLabel4.setText("idProveedor");
			jLabel10 = new JLabel();
			jLabel10.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel10.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel10.setBounds(new Rectangle(435, 17, 51, 14));
			jLabel10.setText("Critico");
			jLabel9 = new JLabel();
			jLabel9.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel9.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel9.setBounds(new Rectangle(566, 17, 54, 14));
			jLabel9.setText("Proyectado");
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBackground(new Color(153, 153, 153));
			jPanel.add(get_chk_seleccionar_lineas(), null);
			jPanel.add(getJToolBar2(), null);
			jPanel.add(getJScrollPane(), null);
			jPanel.add(jLabel, null);
			jPanel.add(get_txt_total_unidades(), null);
			jPanel.add(jLabel1, null);
			jPanel.add(get_txt_total_compra(), null);
			jPanel.add(getJPanel5(), null);
			jPanel.add(get_lst_modo_lineas(), null);
			jPanel.add(get_lst_categoria_lineas(), null);
			jPanel.add(jLabel23, null);
			
		}
		return jPanel;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	public JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(3, 95, 987, 161));
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			
			jLabel21 = new JLabel();
			jLabel21.setBounds(new Rectangle(529, 5, 30, 17));
			jLabel21.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel21.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel21.setText("Stock");
			jLabel19 = new JLabel();
			jLabel19.setBounds(new Rectangle(416, 4, 49, 16));
			jLabel19.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel19.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel19.setText("Categoria");
			jPanel1 = new JPanel();
			jPanel1.setLayout(null);
			jPanel1.setBackground(Color.gray);
			jPanel1.setBounds(new Rectangle(5, 411, 998, 250));
			jPanel1.add(jLabel2, null);
			jPanel1.add(jLabel3, null);
			jPanel1.add(get_txt_items(), null);
			jPanel1.add(get_txt_total(), null);
			jPanel1.add(get_txt_linea(), null);
			jPanel1.add(getJSplitPane(), null);
			jPanel1.add(get_chk_seleccionar_items(), null);
			jPanel1.add(get_lst_categoria(), null);
			jPanel1.add(jLabel19, null);
			jPanel1.add(jLabel21, null);
			jPanel1.add(get_lst_stock(), null);
			jPanel1.add(get_lst_modo(), null);
			jPanel1.add(get_txt_descripcion_linea(), null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	public JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getJTable1());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes total_unidades	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_total_unidades() {
		if (_txt_total_unidades == null) {
			_txt_total_unidades = new JTextField();
			_txt_total_unidades.setEditable(false);
			_txt_total_unidades.setBackground(Color.black);
			_txt_total_unidades.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_total_unidades.setForeground(Color.white);
			_txt_total_unidades.setToolTipText("Total unidades pedidas");
			_txt_total_unidades.setLocation(new Point(743, 261));
			_txt_total_unidades.setSize(new Dimension(89, 18));
			_txt_total_unidades.setHorizontalAlignment(JTextField.RIGHT);
		}
		return _txt_total_unidades;
	}

	/**
	 * This method initializes total_compra	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_total_compra() {
		if (_txt_total_compra == null) {
			_txt_total_compra = new JTextField();
			_txt_total_compra.setEditable(false);
			_txt_total_compra.setBackground(Color.black);
			_txt_total_compra.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_total_compra.setForeground(Color.white);
			_txt_total_compra.setToolTipText("");
			_txt_total_compra.setLocation(new Point(883, 261));
			_txt_total_compra.setSize(new Dimension(112, 18));
			_txt_total_compra.setHorizontalAlignment(JTextField.RIGHT);
		}
		return _txt_total_compra;
	}

	/**
	 * This method initializes Seleccionar0	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_seleccionar_items() {
		if (_chk_seleccionar_items == null) {
			_chk_seleccionar_items = new JCheckBox();
			_chk_seleccionar_items.setText("Seleccionar");
			_chk_seleccionar_items.setBounds(new Rectangle(8, 6, 115, 13));
			_chk_seleccionar_items.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _chk_seleccionar_items;
	}

	/**
	 * This method initializes Seleccionar	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_seleccionar_lineas() {
		if (_chk_seleccionar_lineas == null) {
			_chk_seleccionar_lineas = new JCheckBox();
			_chk_seleccionar_lineas.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_seleccionar_lineas.setSize(new Dimension(120, 15));
			_chk_seleccionar_lineas.setLocation(new Point(0, 73));
			_chk_seleccionar_lineas.setText("Seleccionar");
		}
		return _chk_seleccionar_lineas;
	}

	/**
	 * This method initializes Items	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_items() {
		if (_txt_items == null) {
			_txt_items = new JTextField();
			_txt_items.setHorizontalAlignment(JTextField.RIGHT);
			_txt_items.setForeground(Color.white);
			_txt_items.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_items.setEditable(false);
			_txt_items.setBounds(new Rectangle(732, 228, 99, 18));
			_txt_items.setBackground(Color.black);
		}
		return _txt_items;
	}
	
	/**
	 * This method initializes Total	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_total() {
		if (_txt_total == null) {
			_txt_total = new JTextField();
			_txt_total.setHorizontalAlignment(JTextField.RIGHT);
			_txt_total.setForeground(Color.white);
			_txt_total.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_total.setEditable(false);
			_txt_total.setBounds(new Rectangle(875, 227, 113, 19));
			_txt_total.setBackground(Color.black);
		}
		return _txt_total;
	}

	/**
	 * This method initializes idProveedor	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idproveedor() {
		if (_txt_idproveedor == null) {
			_txt_idproveedor = new JTextField();
			_txt_idproveedor.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_idproveedor.setBounds(new Rectangle(79, 55, 113, 19));
			_txt_idproveedor.setEditable(false);
		}
		return _txt_idproveedor;
	}

	/**
	 * This method initializes Descripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_descripcion() {
		if (_txt_descripcion == null) {
			_txt_descripcion = new JTextField();
			_txt_descripcion.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_descripcion.setBounds(new Rectangle(250, 56, 287, 18));
			_txt_descripcion.setEditable(false);
		}
		return _txt_descripcion;
	}

	/**
	 * This method initializes idPedido	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idpedido() {
		if (_txt_idpedido == null) {
			_txt_idpedido = new JTextField();
			_txt_idpedido.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_idpedido.setBounds(new Rectangle(79, 31, 113, 18));
			
		}
		return _txt_idpedido;
	}

	/**
	 * This method initializes Fecha	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_fecha() {
		if (_txt_fecha == null) {
			_txt_fecha = new JTextField();
			_txt_fecha.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_fecha.setBounds(new Rectangle(795, 29, 183, 18));
		}
		return _txt_fecha;
	}

	/**
	 * This method initializes CargarFecha	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_fecha() {
		if (_btn_buscar_fecha == null) {
			_btn_buscar_fecha = new JButton();
			URL resourceURL = getClass().getClassLoader().getResource("icons/calendar-16.gif");
			_btn_buscar_fecha.setIcon(new ImageIcon(resourceURL));
			_btn_buscar_fecha.setBounds(new Rectangle(980, 28, 23, 20));
		}
		return _btn_buscar_fecha;
	}
	
	
	
	
	/**
	 * This method initializes Cargar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cargar_proveedor() {
		if (_btn_cargar_proveedor == null) {
			_btn_cargar_proveedor = new JButton();
			_btn_cargar_proveedor.setToolTipText("Cargar Datos Proveedor");
			_btn_cargar_proveedor.setBounds(new Rectangle(196, 55, 23, 19));
			_btn_cargar_proveedor.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-refresh.png")));
		}
		return _btn_cargar_proveedor;
	}
	
	
	/**
	 * This method initializes Cancelar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cancelar() {
		if (_btn_cancelar_pedido == null) {
			_btn_cancelar_pedido = new JButton();
			_btn_cancelar_pedido.setToolTipText("Cancelar");
			URL resourceURL = getClass().getClassLoader().getResource("icons/stock_calc-cancel.png");
			_btn_cancelar_pedido.setIcon(new ImageIcon(getClass().getResource("/icons/stock_calc-cancel.png")));
		}
		return _btn_cancelar_pedido;
	}

	
	/**
	 * This method initializes jPanel_lineas	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel_lineas() {
		if (jPanel_lineas == null) {
			jPanel_lineas = new JPanel();
			jPanel_lineas.setLayout(null);
			jPanel_lineas.setBounds(new Rectangle(5, 173, 994, 546));
		}
		return jPanel_lineas;
	}

	/**
	 * This method initializes Imprimir	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_imprimir() {
		if (_btn_imprimir_pedido == null) {
			_btn_imprimir_pedido = new JButton();
			_btn_imprimir_pedido.setToolTipText("Imprimir");
			URL resourceURL = getClass().getClassLoader().getResource("icons/document-print.png");
			_btn_imprimir_pedido.setIcon(new ImageIcon(getClass().getResource("/icons/document-print.png")));
		}
		return _btn_imprimir_pedido;
	}


	/**
	 * This method initializes Guardar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_guardar() {
		if (_btn_guardar == null) {
			_btn_guardar = new JButton();
			_btn_guardar.setToolTipText("Guardar");
			URL resourceURL = getClass().getClassLoader().getResource("icons/filesave.png");
			_btn_guardar.setIcon(new ImageIcon(getClass().getResource("/icons/document-save.png")));
		}
		return _btn_guardar;
	}

	/**
	 * This method initializes _btn_nuevo_pedido	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_nuevo_pedido() {
		if (_btn_nuevo_pedido == null) {
			_btn_nuevo_pedido = new JButton();
			_btn_nuevo_pedido.setText("");
			
			_btn_nuevo_pedido.setIcon(new ImageIcon(getClass().getResource("/icons/document-new.png")));
			_btn_nuevo_pedido.setToolTipText("Nuevo Pedido");
		}
		return _btn_nuevo_pedido;
	}
	
	
	/**
	 * This method initializes Buscar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_pedido() {
		if (_btn_buscar_pedido == null) {
			_btn_buscar_pedido = new JButton();
			_btn_buscar_pedido.setToolTipText("Buscar Pedidos existentes");
			_btn_buscar_pedido.setBounds(new Rectangle(196, 30, 23, 19));
			URL resourceURL = getClass().getClassLoader().getResource("icons/search.png");
			_btn_buscar_pedido.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-searchtool.png")));
		}
		return _btn_buscar_pedido;
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
			jToolBar.setBounds(new Rectangle(1, 2, 1001, 24));
			jToolBar.add(get_btn_nuevo_pedido());
			jToolBar.add(get_btn_guardar());
			jToolBar.add(get_btn_cancelar());
			jToolBar.add(get_btn_imprimir());
			jToolBar.add(get_btn_email());
			jToolBar.add(get_btn_control());
			jToolBar.add(get_btn_eliminar());
			jToolBar.add(get_btn_salir());
			jToolBar.add(get_btn_error());
		}
		return jToolBar;
	}

	/**
	 * This method initializes cargar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cargar_pedido() {
		if (_btn_cargar_pedido == null) {
			_btn_cargar_pedido = new JButton();
			_btn_cargar_pedido.setToolTipText("Cargar Pedido");
			_btn_cargar_pedido.setBounds(new Rectangle(223, 30, 23, 19));
			URL resourceURL = getClass().getClassLoader().getResource("icons/stock_refresh.png");
			_btn_cargar_pedido.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-refresh.png")));
		}
		return _btn_cargar_pedido;
	}

	/**
	 * This method initializes agregar_linea	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_agregar_linea() {
		if (_btn_agregar_linea == null) {
			_btn_agregar_linea = new JButton();
			URL resourceURL = getClass().getClassLoader().getResource("icons/add.png");
			_btn_agregar_linea.setIcon(new ImageIcon(getClass().getResource("/icons/add.png")));
			_btn_agregar_linea.setToolTipText("Agregar Linea al pedido");
		}
		return _btn_agregar_linea;
	}

	/**
	 * This method initializes quitar_linea	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_quitar_linea() {
		if (_btn_quitar_linea == null) {
			_btn_quitar_linea = new JButton();
			URL resourceURL = getClass().getClassLoader().getResource("icons/gtk-remove.png");
			_btn_quitar_linea.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-remove.png")));
			_btn_quitar_linea.setToolTipText("Quita la Linea seleccionada del pedido");
		}
		return _btn_quitar_linea;
	}

	/**
	 * This method initializes agregar_asteriscos	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_agregar_asteriscos() {
		if (_btn_agregar_asteriscos == null) {
			_btn_agregar_asteriscos = new JButton();
			_btn_agregar_asteriscos.setToolTipText("Agregar Articulos Varios");
			
			_btn_agregar_asteriscos.setIcon(new ImageIcon(getClass().getResource("/icons/applications-system.png")));
		}
		return _btn_agregar_asteriscos;
	}

	/**
	 * This method initializes _txt_descripcion_pedido	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_descripcion_pedido() {
		if (_txt_descripcion_pedido == null) {
			_txt_descripcion_pedido = new JTextField();
			_txt_descripcion_pedido.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_descripcion_pedido.setBounds(new Rectangle(250, 31, 213, 18));
		}
		return _txt_descripcion_pedido;
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
	 * This method initializes _btn_salir	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_salir() {
		if (_btn_salir == null) {
			_btn_salir = new JButton();
			_btn_salir.setIcon(new ImageIcon(getClass().getResource("/icons/exit.png")));
			_btn_salir.setToolTipText("Cierra Aplicacion");
		}
		return _btn_salir;
	}

	/**
	 * This method initializes _btn_fecha_desde	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_fecha_desde() {
		if (_btn_fecha_desde == null) {
			_btn_fecha_desde = new JButton();
			_btn_fecha_desde.setIcon(new ImageIcon(getClass().getResource("/icons/calendar-16.gif")));
			_btn_fecha_desde.setBounds(new Rectangle(233, 12, 23, 20));
		}
		return _btn_fecha_desde;
	}

	/**
	 * This method initializes _txt_hasta	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_hasta() {
		if (_txt_hasta == null) {
			_txt_hasta = new JTextField();
			_txt_hasta.setToolTipText("Fecha Hasta Rotacion");
			_txt_hasta.setBounds(new Rectangle(267, 13, 120, 18));
		}
		return _txt_hasta;
	}

	/**
	 * This method initializes _btn_fecha_hasta	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_fecha_hasta() {
		if (_btn_fecha_hasta == null) {
			_btn_fecha_hasta = new JButton();
			_btn_fecha_hasta.setIcon(new ImageIcon(getClass().getResource("/icons/calendar-16.gif")));
			_btn_fecha_hasta.setBounds(new Rectangle(388, 13, 23, 20));
		}
		return _btn_fecha_hasta;
	}

	/**
	 * This method initializes _txt_desde	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_desde() {
		if (_txt_desde == null) {
			_txt_desde = new JTextField();
			_txt_desde.setBounds(new Rectangle(110, 13, 120, 18));
		}
		return _txt_desde;
	}

	/**
	 * This method initializes _lst_estado	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_estado() {
		if (_lst_estado == null) {
			_lst_estado = new JComboBox();
			_lst_estado.setBounds(new Rectangle(796, 54, 206, 18));
		}
		return _lst_estado;
	}

	/**
	 * This method initializes _txt_proyectado	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_proyectado() {
		if (_txt_proyectado == null) {
			_txt_proyectado = new JTextField();
			_txt_proyectado.setText("45");
			_txt_proyectado.setBounds(new Rectangle(621, 14, 37, 20));
			_txt_proyectado.setToolTipText("Proyeccion de Stock Para X Dias");
		}
		return _txt_proyectado;
	}

	/**
	 * This method initializes _btn_proyectado	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_proyectado() {
		if (_btn_proyectado == null) {
			_btn_proyectado = new JButton();
			_btn_proyectado.setToolTipText("Recalcular Stock Necesario");
			_btn_proyectado.setBounds(new Rectangle(659, 13, 23, 21));
			_btn_proyectado.setIcon(new ImageIcon(getClass().getResource("/icons/applications-system.png")));
		}
		return _btn_proyectado;
	}

	/**
	 * This method initializes _txt_critico	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_critico() {
		if (_txt_critico == null) {
			_txt_critico = new JTextField();
			_txt_critico.setText("7");
			_txt_critico.setBounds(new Rectangle(488, 13, 37, 20));
		}
		return _txt_critico;
	}

	/**
	 * This method initializes _btn_critico	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_critico() {
		if (_btn_critico == null) {
			_btn_critico = new JButton();
			_btn_critico.setIcon(new ImageIcon(getClass().getResource("/icons/applications-system.png")));
			_btn_critico.setBounds(new Rectangle(525, 13, 23, 21));
		}
		return _btn_critico;
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
			_btn_eliminar.setToolTipText("Eliminar Pedido");
		}
		return _btn_eliminar;
	}

	/**
	 * This method initializes _chk_seguimiento	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_seguimiento() {
		if (_chk_seguimiento == null) {
			_chk_seguimiento = new JCheckBox();
			_chk_seguimiento.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_seguimiento.setBounds(new Rectangle(545, 58, 97, 14));
			_chk_seguimiento.setText("Seguimiento");
		}
		return _chk_seguimiento;
	}

	/**
	 * This method initializes _txt_fecha_creacion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_fecha_creacion() {
		if (_txt_fecha_creacion == null) {
			_txt_fecha_creacion = new JTextField();
			_txt_fecha_creacion.setToolTipText("Fecha de Creacion");
			_txt_fecha_creacion.setBounds(new Rectangle(101, 21, 140, 15));
			_txt_fecha_creacion.setEditable(false);
		}
		return _txt_fecha_creacion;
	}

	/**
	 * This method initializes jScrollPane2	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane2() {
		if (jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setBounds(new Rectangle(4, 25, 980, 95));
			jScrollPane2.setViewportView(getJTable_articulos());
		}
		return jScrollPane2;
	}

	/**
	 * This method initializes jTable_articulos	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getJTable_articulos() {
		if (jTable_articulos == null) {
			jTable_articulos = new JTable();
		}
		return jTable_articulos;
	}

	public void setJTable_articulos(JTable table){
		this.jTable_articulos=table;
		this.jScrollPane2.setViewportView(table);
	}
	
	/**
	 * This method initializes _txt_articulo_actualizacion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_articulo_actualizacion() {
		if (_txt_articulo_actualizacion == null) {
			_txt_articulo_actualizacion = new JTextField();
			_txt_articulo_actualizacion.setEditable(false);
			_txt_articulo_actualizacion.setHorizontalAlignment(JTextField.RIGHT);
			_txt_articulo_actualizacion.setBounds(new Rectangle(406, 3, 101, 18));
			_txt_articulo_actualizacion.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_articulo_actualizacion;
	}
	
	/**
	 * This method initializes _txt_articulo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_articulo() {
		if (_txt_articulo == null) {
			_txt_articulo = new JTextField();
			_txt_articulo.setEditable(false);
			_txt_articulo.setBounds(new Rectangle(2, 3, 120, 18));
			_txt_articulo.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_articulo;
	}
	
	/**
	 * This method initializes _txt_articulo_bloqueado	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_articulo_bloqueado() {
		if (_txt_articulo_bloqueado == null) {
			_txt_articulo_bloqueado = new JTextField();
			_txt_articulo_bloqueado.setEnabled(true);
			_txt_articulo_bloqueado.setFont(new Font("Dialog", Font.PLAIN, 8));
			_txt_articulo_bloqueado.setEditable(false);
			_txt_articulo_bloqueado.setBounds(new Rectangle(686, 3, 105, 18));
			_txt_articulo_bloqueado.setToolTipText("Indicador si esta bloqueado para la venta");
		}
		return _txt_articulo_bloqueado;
	}

	/**
	 * This method initializes _txt_articulo_linea	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_articulo_linea() {
		if (_txt_articulo_linea == null) {
			_txt_articulo_linea = new JTextField();
			_txt_articulo_linea.setEditable(false);
			_txt_articulo_linea.setBounds(new Rectangle(508, 3, 176, 18));
			_txt_articulo_linea.setFont(new Font("Dialog", Font.PLAIN, 8));
		}
		return _txt_articulo_linea;
	}

	/**
	 * This method initializes _txt_articulo_descripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_articulo_descripcion() {
		if (_txt_articulo_descripcion == null) {
			_txt_articulo_descripcion = new JTextField();
			_txt_articulo_descripcion.setEditable(false);
			_txt_articulo_descripcion.setBounds(new Rectangle(126, 3, 234, 18));
			_txt_articulo_descripcion.setFont(new Font("Dialog", Font.PLAIN, 8));
		}
		return _txt_articulo_descripcion;
	}

	/**
	 * This method initializes _txt_articulo_stock	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_articulo_stock() {
		if (_txt_articulo_stock == null) {
			_txt_articulo_stock = new JTextField();
			_txt_articulo_stock.setEditable(false);
			_txt_articulo_stock.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_articulo_stock.setBounds(new Rectangle(361, 3, 43, 18));
			_txt_articulo_stock.setToolTipText("Stock");
		}
		return _txt_articulo_stock;
	}

	/**
	 * This method initializes _txt_articulo_pedido	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_articulo_pedido() {
		if (_txt_articulo_pedido == null) {
			_txt_articulo_pedido = new JTextField();
			_txt_articulo_pedido.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_articulo_pedido.setEditable(false);
			_txt_articulo_pedido.setEnabled(true);
			_txt_articulo_pedido.setToolTipText("Mercaderia que esta pedida");
			_txt_articulo_pedido.setBounds(new Rectangle(795, 3, 49, 18));
			_txt_articulo_pedido.setHorizontalAlignment(JTextField.RIGHT);
		}
		return _txt_articulo_pedido;
	}

	/**
	 * This method initializes _btn_email	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_email() {
		if (_btn_email == null) {
			_btn_email = new JButton();
			_btn_email.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-stock-mail-fwd.png")));
			_btn_email.setToolTipText("Enviar Pedido Por Email");
		}
		return _btn_email;
	}

	/**
	 * This method initializes jTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	public JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.setLocation(0,0);
			jTabbedPane.setSize(new Dimension(1004, 279));
			jTabbedPane.setFont(new Font("Dialog", Font.PLAIN, 10));
			jTabbedPane.addTab("Envio/Direccion", null, getJPanel2(), null);
			jTabbedPane.addTab("Proyeccion", null, getJPanel(), null);
			jTabbedPane.addTab("Propiedades", null, getJPanel4(), null);
			
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
		}
		return jPanel3;
	}

	/**
	 * This method initializes _btn_editar_proveedor	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_editar_proveedor() {
		if (_btn_editar_proveedor == null) {
			_btn_editar_proveedor = new JButton();
			_btn_editar_proveedor.setIcon(new ImageIcon(getClass().getResource("/icons/edit-find-replace.png")));
			_btn_editar_proveedor.setBounds(new Rectangle(223, 55, 23, 20));
			_btn_editar_proveedor.setToolTipText("Editar Proveedor");
		}
		return _btn_editar_proveedor;
	}

	/**
	 * This method initializes jPanel4	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel4() {
		if (jPanel4 == null) {
			jLabel14 = new JLabel();
			jLabel14.setBounds(new Rectangle(18, 43, 78, 18));
			jLabel14.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel14.setText("Modificado");
			jLabel12 = new JLabel();
			jLabel12.setBounds(new Rectangle(20, 17, 74, 19));
			jLabel12.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel12.setText("Creado");
			jPanel4 = new JPanel();
			jPanel4.setLayout(null);
			jPanel4.add(get_txt_fecha_creacion(), null);
			jPanel4.add(jLabel12, null);
			jPanel4.add(get_txt_modificado(), null);
			jPanel4.add(jLabel14, null);
		}
		return jPanel4;
	}
	
	/**
	 * This method initializes _txt_items	
	

/**
	 * This method initializes _txt_idtransporte	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField _txt_idtransporte = null;
	public JTextField get_txt_idtransporte() {
		if (_txt_idtransporte == null) {
			_txt_idtransporte = new JTextField();
			_txt_idtransporte.setHorizontalAlignment(JTextField.RIGHT);
			_txt_idtransporte.setBounds(new Rectangle(84, 4, 59, 17));
			_txt_idtransporte.setToolTipText("Codigo de Transporte");
		}
		return _txt_idtransporte;
	}

	/**
	 * This method initializes _txt_transporte_descripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField _txt_transporte_descripcion = null;
	public JTextField get_txt_transporte_descripcion() {
		if (_txt_transporte_descripcion == null) {
			_txt_transporte_descripcion = new JTextField();
			_txt_transporte_descripcion.setHorizontalAlignment(JTextField.RIGHT);
			_txt_transporte_descripcion.setEditable(false);
			_txt_transporte_descripcion.setToolTipText("Nombre del Transporte");
			_txt_transporte_descripcion.setBounds(new Rectangle(195, 3, 197, 18));
			_txt_transporte_descripcion.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_transporte_descripcion;
	}

	/**
	 * This method initializes _btn_buscar_transporte	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton _btn_buscar_transporte = null;
	public JButton get_btn_buscar_transporte() {
		if (_btn_buscar_transporte == null) {
			_btn_buscar_transporte = new JButton();
			_btn_buscar_transporte.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-searchtool.png")));
			_btn_buscar_transporte.setBounds(new Rectangle(146, 4, 22, 17));
			_btn_buscar_transporte.setToolTipText("Buscar Transporte");
		}
		return _btn_buscar_transporte;
	}


	/**
	 * This method initializes _txt_fecha_envio	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField _txt_fecha_envio=null;
	public JTextField get_txt_fecha_envio() {
		if (_txt_fecha_envio == null) {
			_txt_fecha_envio = new JTextField();
			_txt_fecha_envio.setHorizontalAlignment(JTextField.RIGHT);
			_txt_fecha_envio.setBounds(new Rectangle(480, 2, 140, 19));
			_txt_fecha_envio.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_fecha_envio;
	}

	/**
	 * This method initializes _btn_buscar_fecha_envio	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton _btn_buscar_fecha_envio=null;
	public JButton get_btn_buscar_fecha_envio() {
		if (_btn_buscar_fecha_envio == null) {
			_btn_buscar_fecha_envio = new JButton();
			_btn_buscar_fecha_envio.setIcon(new ImageIcon(getClass().getResource("/icons/calendar-16.gif")));
			_btn_buscar_fecha_envio.setBounds(new Rectangle(624, 2, 21, 19));
			_btn_buscar_fecha_envio.setToolTipText("Mostrar Calendario");
		}
		return _btn_buscar_fecha_envio;
	}
	/**
	 * This method initializes _txt_guia	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField _txt_guia=null;
	public JTextField get_txt_guia() {
		if (_txt_guia == null) {
			_txt_guia = new JTextField();
			_txt_guia.setHorizontalAlignment(JTextField.RIGHT);
			_txt_guia.setToolTipText("Numero de Guia del Envio");
			_txt_guia.setBounds(new Rectangle(479, 101, 169, 19));
			_txt_guia.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_guia;
	}

	/**
	 * This method initializes _txt_domicilio	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField _txt_domicilio=null;
	public JTextField get_txt_domicilio() {
		if (_txt_domicilio == null) {
			_txt_domicilio = new JTextField();
			_txt_domicilio.setBounds(new Rectangle(85, 50, 305, 19));
		}
		return _txt_domicilio;
	}

	/**
	 * This method initializes _txt_idprovincia	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField _txt_idprovincia=null;
	public JTextField get_txt_idprovincia() {
		if (_txt_idprovincia == null) {
			_txt_idprovincia = new JTextField();
			_txt_idprovincia.setBounds(new Rectangle(84, 27, 108, 18));
		}
		return _txt_idprovincia;
	}

	/**
	 * This method initializes _txt_cpostal	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField _txt_cpostal=null;
	public JTextField get_txt_cpostal() {
		if (_txt_cpostal == null) {
			_txt_cpostal = new JTextField();
			_txt_cpostal.setBounds(new Rectangle(480, 47, 167, 19));
		}
		return _txt_cpostal;
	}

	/**
	 * This method initializes _txt_tel	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField _txt_tel=null;
	public JTextField get_txt_tel() {
		if (_txt_tel == null) {
			_txt_tel = new JTextField();
			_txt_tel.setBounds(new Rectangle(86, 74, 260, 19));
		}
		return _txt_tel;
	}

	/**
	 * This method initializes _txt_provincia_descripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField _txt_provincia_descripcion=null;
	public JTextField get_txt_provincia_descripcion() {
		if (_txt_provincia_descripcion == null) {
			_txt_provincia_descripcion = new JTextField();
			_txt_provincia_descripcion.setBounds(new Rectangle(195, 26, 196, 18));
			_txt_provincia_descripcion.setEditable(false);
		}
		return _txt_provincia_descripcion;
	}

	
	 	private JScrollPane jScrollPaneInformacion=null;
		private JScrollPane getJScrollPaneInformacion() {
			if (jScrollPaneInformacion == null) {
				jScrollPaneInformacion = new JScrollPane();
				jScrollPaneInformacion.setBounds(new Rectangle(86, 128, 563, 77));
				jScrollPaneInformacion.setViewportView(get_txt_informacion());
			}
			return jScrollPaneInformacion;
		}

		/**
		 * This method initializes _txt_informacion	
		 * 	
		 * @return javax.swing.JTextArea	
		 */
		private JTextArea _txt_informacion = null;
		public JTextArea get_txt_informacion() {
			if (_txt_informacion == null) {
				_txt_informacion = new JTextArea();
				_txt_informacion.setFont(new Font("Dialog", Font.PLAIN, 10));
				_txt_informacion.setToolTipText("Ingrese aqui la informacion relacionada con el pedido de compra");
			}
			return _txt_informacion;
		}
	/**
	 * This method initializes _txt_idciudad	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField _txt_idciudad=null;
	public JTextField get_txt_idciudad() {
		if (_txt_idciudad == null) {
			_txt_idciudad = new JTextField();
			_txt_idciudad.setBounds(new Rectangle(480, 25, 167, 19));
		}
		return _txt_idciudad;
	}

	/**
	 * This method initializes _txt_email	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField _txt_email=null;
	public JTextField get_txt_email() {
		if (_txt_email == null) {
			_txt_email = new JTextField();
			_txt_email.setBounds(new Rectangle(86, 101, 304, 20));
			_txt_email.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_email;
	}

	/**
	 * This method initializes _txt_fax	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField _txt_fax=null;
	public JTextField get_txt_fax() {
		if (_txt_fax == null) {
			_txt_fax = new JTextField();
			_txt_fax.setBounds(new Rectangle(407, 74, 241, 21));
		}
		return _txt_fax;
	}
	
	/**
	 * This method initializes jPanel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel jPanel2=null;
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			JLabel jLabel31 = new JLabel();
			jLabel31.setBounds(new Rectangle(365, 75, 39, 18));
			jLabel31.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel31.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel31.setText("Fax:");
			JLabel jLabel20 = new JLabel();
			jLabel20.setBounds(new Rectangle(21, 102, 57, 16));
			jLabel20.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel20.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel20.setText("email");
			JLabel jLabel27 = new JLabel();
			jLabel27.setBounds(new Rectangle(3, 231, 79, 15));
			jLabel27.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel27.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel27.setText("Para Proveedor");
			JLabel jLabel26 = new JLabel();
			jLabel26.setBounds(new Rectangle(3, 145, 82, 17));
			jLabel26.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel26.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel26.setText("del comprador");
			JLabel jLabel25 = new JLabel();
			jLabel25.setBounds(new Rectangle(15, 213, 65, 16));
			jLabel25.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel25.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel25.setText("Observacion");
			JLabel jLabel24 = new JLabel();
			jLabel24.setBounds(new Rectangle(3, 128, 80, 17));
			jLabel24.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel24.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel24.setText("Informacion");
			JLabel jLabel18 = new JLabel();
			jLabel18.setBounds(new Rectangle(407, 26, 64, 19));
			jLabel18.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel18.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel18.setText("ciudad");
			JLabel jLabel9 = new JLabel();
			jLabel9.setBounds(new Rectangle(1, 75, 72, 17));
			jLabel9.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel9.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel9.setText("Tel");
			JLabel jLabel8 = new JLabel();
			jLabel8.setBounds(new Rectangle(398, 48, 71, 18));
			jLabel8.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel8.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel8.setText("Codigo Postal");
			JLabel jLabel7 = new JLabel();
			jLabel7.setBounds(new Rectangle(6, 26, 65, 19));
			jLabel7.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel7.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel7.setText("Provincia");
			JLabel jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(7, 48, 66, 17));
			jLabel4.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel4.setText("Domicilio");
			JLabel jLabel17 = new JLabel();
			jLabel17.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel17.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel17.setBounds(new Rectangle(399, 101, 71, 20));
			jLabel17.setText("Guia");
			JLabel jLabel16 = new JLabel();
			jLabel16.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel16.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel16.setBounds(new Rectangle(397, 3, 69, 17));
			jLabel16.setText("Fecha Envio");
			JLabel jLabel15 = new JLabel();
			jLabel15.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel15.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel15.setBounds(new Rectangle(4, 5, 68, 16));
			jLabel15.setText("Transporte");
			JLabel jLabel6 = new JLabel();
			jLabel6.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel6.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel6.setBounds(new Rectangle(126, 344, 66, 17));
			jLabel6.setText("idVendedor");
			JLabel jLabel5 = new JLabel();
			jLabel5.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel5.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel5.setBounds(new Rectangle(11, 40, 55, 22));
			jLabel5.setText("Modificado");
			JLabel jLabel3 = new JLabel();
			jLabel3.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3.setBounds(new Rectangle(11, 15, 54, 19));
			jLabel3.setText("Creado");
			JLabel jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(668, 31, 49, 19));
			jLabel2.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2.setText("Fecha");
			JLabel jLabel1 = new JLabel();
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setBounds(new Rectangle(3, 9, 66, 19));
			jLabel1.setText("Proveedor");
			JLabel jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(3, 29, 88, 18));
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setText("idpedido");
			
			jPanel2 = new JPanel();
			jPanel2.setLayout(null);
			jPanel2.setBackground(Color.gray);
			jPanel2.add(jLabel15, null);
			jPanel2.add(get_txt_idtransporte(), null);
			jPanel2.add(get_btn_buscar_transporte(), null);
			jPanel2.add(get_txt_transporte_descripcion(), null);
			jPanel2.add(jLabel16, null);
			jPanel2.add(get_txt_fecha_envio(), null);
			jPanel2.add(get_btn_buscar_fecha_envio(), null);
			jPanel2.add(jLabel17, null);
			jPanel2.add(get_txt_guia(), null);
			jPanel2.add(jLabel4, null);
			jPanel2.add(get_txt_domicilio(), null);
			jPanel2.add(jLabel7, null);
			jPanel2.add(get_txt_idprovincia(), null);
			jPanel2.add(jLabel8, null);
			jPanel2.add(get_txt_cpostal(), null);
			jPanel2.add(jLabel9, null);
			jPanel2.add(get_txt_tel(), null);
			jPanel2.add(get_txt_provincia_descripcion(), null);
			jPanel2.add(jLabel18, null);
			jPanel2.add(get_txt_idciudad(), null);
			jPanel2.add(getJScrollPane(), null);
			jPanel2.add(jLabel24, null);
			jPanel2.add(getJScrollPane6(), null);
			jPanel2.add(getJScrollPaneInformacion(), null);
			jPanel2.add(jLabel25, null);
			jPanel2.add(jLabel26, null);
			jPanel2.add(jLabel27, null);
			jPanel2.add(jLabel20, null);
			jPanel2.add(get_txt_email(), null);
			jPanel2.add(get_txt_fax(), null);
			jPanel2.add(jLabel31, null);
			jPanel2.add(get_btn_editar_transporte(), null);
		}
		return jPanel2;
	}

	/**
	 * This method initializes jScrollPane6	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane jScrollPane6=null;
	private JScrollPane getJScrollPane6() {
		if (jScrollPane6 == null) {
			jScrollPane6 = new JScrollPane();
			jScrollPane6.setBounds(new Rectangle(85, 213, 564, 60));
			jScrollPane6.setViewportView(get_txt_observacion());
		}
		return jScrollPane6;
	}

	/**
	 * This method initializes _txt_observacion	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea _txt_observacion =null;
	public JTextArea get_txt_observacion() {
		if (_txt_observacion == null) {
			_txt_observacion = new JTextArea();
			_txt_observacion.setToolTipText("Ingrese Aqui la Informacion para el proveedor");
		}
		return _txt_observacion;
	}

	/**
	 * This method initializes _txt_idcreador	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField _txt_idcreador=null;
	public JTextField get_txt_idcreador() {
		if (_txt_idcreador == null) {
			_txt_idcreador = new JTextField();
			_txt_idcreador.setEditable(false);
			_txt_idcreador.setBounds(new Rectangle(204, 16, 42, 18));
			_txt_idcreador.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_idcreador;
	}

	/**
	 * This method initializes _txt_creador	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField _txt_creador=null;
	public JTextField get_txt_creador() {
		if (_txt_creador == null) {
			_txt_creador = new JTextField();
			_txt_creador.setEditable(false);
			_txt_creador.setBounds(new Rectangle(251, 16, 98, 19));
			_txt_creador.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_creador;
	}
	
	/**
	 * This method initializes _btn_editar_transporte	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton _btn_editar_transporte =null;
	private JButton _btn_crear_info = null;
	private JToolBar jToolBar2 = null;
	private JPanel jPanel5 = null;
	private JTextField _txt_linea = null;
	private JTextField _txt_idvendedor = null;
	private JTextField _txt_vendedor_descripcion = null;
	private JButton _btn_buscar_vendedor = null;
	private JSplitPane jSplitPane = null;
	private JPanel jPanel6 = null;
	private JTextField _txt_equivalencias = null;
	private JCheckBox _chk_autofoco = null;
	private JLabel jLabel13 = null;
	private JTextField _txt_modificado = null;
	private JLabel jLabel14 = null;
	private JComboBox _lst_categoria = null;
	private JLabel jLabel19 = null;
	private JLabel jLabel21 = null;
	private JComboBox _lst_stock = null;
	private JComboBox _lst_categoria_generacion = null;
	private JLabel jLabel22 = null;
	private JComboBox _lst_modo = null;
	private JComboBox _lst_modo_lineas = null;
	private JComboBox _lst_categoria_lineas = null;
	private JLabel jLabel23 = null;
	private JLabel jLabel28 = null;
	private JTextField _txt_margen = null;
	private JButton _btn_control = null;
	private JProgressBar JProgressBar = null;
	private JButton _btn_cancelar_tarea = null;
	private JTextField _txt_descripcion_linea = null;
	public JButton get_btn_editar_transporte() {
		if (_btn_editar_transporte == null) {
			_btn_editar_transporte = new JButton();
			_btn_editar_transporte.setBounds(new Rectangle(172, 3, 21, 19));
			_btn_editar_transporte.setToolTipText("Editar Transporte");
			_btn_editar_transporte.setIcon(new ImageIcon(getClass().getResource("/icons/edit-find-replace.png")));
		}
		return _btn_editar_transporte;
	}
	public JXLayer<JComponent> getJXLayerUI(){
		JTabbedPane pane = this.getJTabbedPane();
		JXLayer<JComponent> layer = new JXLayer<JComponent>(pane, this.getLockableUI());
		layer.setBounds(new Rectangle(3, 110, 897, 275));
		this.getLockableUI().setLocked(false);
		return layer;
		
	}

	/**
	 * This method initializes _btn_crear_info	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_crear_info() {
		if (_btn_crear_info == null) {
			_btn_crear_info = new JButton();
			_btn_crear_info.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-run.png")));
			_btn_crear_info.setPreferredSize(new Dimension(24, 24));
			_btn_crear_info.setToolTipText("Cargar Lineas Predeterminadas");
		}
		return _btn_crear_info;
	}

	/**
	 * This method initializes jToolBar2	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar2() {
		if (jToolBar2 == null) {
			jToolBar2 = new JToolBar();
			jToolBar2.setFloatable(false);
			jToolBar2.setSize(new Dimension(998, 24));
			jToolBar2.setLocation(new Point(2, 2));
			jToolBar2.add(get_btn_crear_info());
			jToolBar2.add(get_btn_agregar_linea());
			jToolBar2.add(get_btn_quitar_linea());
			jToolBar2.add(get_btn_agregar_asteriscos());
		}
		return jToolBar2;
	}

	/**
	 * This method initializes jPanel5	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel5() {
		if (jPanel5 == null) {
			jLabel28 = new JLabel();
			jLabel28.setBounds(new Rectangle(689, 17, 53, 14));
			jLabel28.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel28.setText("Margen");
			jPanel5 = new JPanel();
			jPanel5.setLayout(null);
			jPanel5.setBackground(new Color(255, 204, 153));
			jPanel5.setSize(new Dimension(997, 45));
			jPanel5.setLocation(new Point(3, 26));
			jPanel5.add(jLabel5, null);
			jPanel5.add(get_txt_desde(), null);
			jPanel5.add(get_btn_fecha_desde(), null);
			jPanel5.add(get_txt_hasta(), null);
			jPanel5.add(get_btn_fecha_hasta(), null);
			jPanel5.add(jLabel10, null);
			jPanel5.add(get_txt_critico(), null);
			jPanel5.add(get_btn_critico(), null);
			jPanel5.add(jLabel9, null);
			jPanel5.add(get_txt_proyectado(), null);
			jPanel5.add(get_btn_proyectado(), null);
			jPanel5.add(jLabel28, null);
			jPanel5.add(get_txt_margen(), null);
		}
		return jPanel5;
	}

	/**
	 * This method initializes _txt_linea	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_linea() {
		if (_txt_linea == null) {
			_txt_linea = new JTextField();
			_txt_linea.setBounds(new Rectangle(747, 5, 240, 17));
			_txt_linea.setForeground(Color.white);
			_txt_linea.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_linea.setBackground(Color.black);
			_txt_linea.setHorizontalAlignment(JTextField.RIGHT);
		}
		return _txt_linea;
	}

	/**
	 * This method initializes _txt_idvendedor	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idvendedor() {
		if (_txt_idvendedor == null) {
			_txt_idvendedor = new JTextField();
			_txt_idvendedor.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_idvendedor.setBounds(new Rectangle(147, 392, 47, 16));
		}
		return _txt_idvendedor;
	}

	/**
	 * This method initializes _txt_vendedor_descripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_vendedor_descripcion() {
		if (_txt_vendedor_descripcion == null) {
			_txt_vendedor_descripcion = new JTextField();
			_txt_vendedor_descripcion.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_vendedor_descripcion.setBounds(new Rectangle(214, 391, 197, 17));
			_txt_vendedor_descripcion.setEditable(false);
		}
		return _txt_vendedor_descripcion;
	}

	/**
	 * This method initializes _btn_buscar_vendedor	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_vendedor() {
		if (_btn_buscar_vendedor == null) {
			_btn_buscar_vendedor = new JButton();
			_btn_buscar_vendedor.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-searchtool.png")));
			_btn_buscar_vendedor.setBounds(new Rectangle(194, 391, 19, 17));
		}
		return _btn_buscar_vendedor;
	}

	/**
	 * This method initializes jSplitPane	
	 * 	
	 * @return javax.swing.JSplitPane	
	 */
	public JSplitPane getJSplitPane() {
		if (jSplitPane == null) {
			jSplitPane = new JSplitPane();
			jSplitPane.setBounds(new Rectangle(3, 25, 990, 194));
			jSplitPane.setDividerLocation(160);
			jSplitPane.setOneTouchExpandable(true);
			jSplitPane.setTopComponent(getJScrollPane1());
			
			jSplitPane.setBottomComponent(getJPanel6());
			jSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		}
		return jSplitPane;
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
			jPanel6.setBackground(new Color(102, 102, 102));
			jPanel6.add(get_txt_articulo(), null);
			jPanel6.add(get_txt_articulo_descripcion(), null);
			jPanel6.add(get_txt_articulo_stock(), null);
			jPanel6.add(get_txt_articulo_actualizacion(), null);
			jPanel6.add(get_txt_articulo_linea(), null);
			jPanel6.add(get_txt_articulo_bloqueado(), null);
			jPanel6.add(get_txt_articulo_pedido(), null);
			jPanel6.add(getJScrollPane2(),null);
			jPanel6.add(get_txt_equivalencias(), null);
			jPanel6.add(get_chk_autofoco(), null);
		}
		return jPanel6;
	}

	/**
	 * This method initializes _txt_equivalencias	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_equivalencias() {
		if (_txt_equivalencias == null) {
			_txt_equivalencias = new JTextField();
			_txt_equivalencias.setBounds(new Rectangle(849, 3, 36, 18));
			_txt_equivalencias.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_equivalencias.setEditable(false);
			_txt_equivalencias.setHorizontalAlignment(JTextField.RIGHT);
		}
		return _txt_equivalencias;
	}

	/**
	 * This method initializes _chk_autofoco	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_autofoco() {
		if (_chk_autofoco == null) {
			_chk_autofoco = new JCheckBox();
			_chk_autofoco.setBounds(new Rectangle(893, 3, 92, 16));
			_chk_autofoco.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_autofoco.setText("automatico");
		}
		return _chk_autofoco;
	}

	/**
	 * This method initializes _txt_modificado	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_modificado() {
		if (_txt_modificado == null) {
			_txt_modificado = new JTextField();
			_txt_modificado.setBounds(new Rectangle(102, 44, 138, 15));
			_txt_modificado.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_modificado.setEditable(false);
		}
		return _txt_modificado;
	}

	/**
	 * This method initializes _lst_categoria	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_categoria() {
		if (_lst_categoria == null) {
			_lst_categoria = new JComboBox();
			_lst_categoria.setBounds(new Rectangle(471, 3, 62, 18));
			_lst_categoria.addItem("");
			_lst_categoria.addItem("A");
			_lst_categoria.addItem("B");
			_lst_categoria.addItem("C");
			_lst_categoria.addItem("D");
		}
		return _lst_categoria;
	}

	/**
	 * This method initializes _lst_stock	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_stock() {
		if (_lst_stock == null) {
			_lst_stock = new JComboBox();
			_lst_stock.setToolTipText("Stock Menor a");
			_lst_stock.setSize(new Dimension(60, 18));
			_lst_stock.setLocation(new Point(564, 3));
			_lst_stock.addItem("");
			_lst_stock.addItem("0");
			_lst_stock.addItem("1");
			_lst_stock.addItem("2");
			_lst_stock.addItem("3");
			_lst_stock.addItem("4");
			_lst_stock.addItem("5");
		}
		return _lst_stock;
	}

	/**
	 * This method initializes _lst_categoria_generacion	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_categoria_generacion() {
		if (_lst_categoria_generacion == null) {
			_lst_categoria_generacion = new JComboBox();
			_lst_categoria_generacion.setBounds(new Rectangle(542, 31, 55, 19));
			_lst_categoria_generacion.addItem("A");
			_lst_categoria_generacion.addItem("B");
			_lst_categoria_generacion.addItem("C");
			_lst_categoria_generacion.addItem("D");
			_lst_categoria_generacion.setSelectedIndex(1);
		}
		return _lst_categoria_generacion;
	}

	/**
	 * This method initializes _lst_modo	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_modo() {
		if (_lst_modo == null) {
			_lst_modo = new JComboBox();
			_lst_modo.setBounds(new Rectangle(139, 5, 132, 18));
			_lst_modo.addItem("Rotacion");
			_lst_modo.addItem("Volumen");
			_lst_modo.addItem("Pickups");
		}
		return _lst_modo;
	}

	/**
	 * This method initializes _lst_modo_lineas	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_modo_lineas() {
		if (_lst_modo_lineas == null) {
			_lst_modo_lineas = new JComboBox();
			_lst_modo_lineas.setLocation(new Point(126, 73));
			_lst_modo_lineas.setSize(new Dimension(133, 18));
			_lst_modo_lineas.addItem("Rotacion");
			_lst_modo_lineas.addItem("Volumen");
			_lst_modo_lineas.addItem("Pickups");
		}
		return _lst_modo_lineas;
	}

	/**
	 * This method initializes _lst_categoria_lineas	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_categoria_lineas() {
		if (_lst_categoria_lineas == null) {
			_lst_categoria_lineas = new JComboBox();
			_lst_categoria_lineas.setLocation(new Point(349, 73));
			_lst_categoria_lineas.setSize(new Dimension(58, 18));
			_lst_categoria_lineas.addItem("");
			_lst_categoria_lineas.addItem("A");
			_lst_categoria_lineas.addItem("B");
			_lst_categoria_lineas.addItem("C");
			_lst_categoria_lineas.addItem("D");
		}
		return _lst_categoria_lineas;
	}

	/**
	 * This method initializes _txt_margen	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_margen() {
		if (_txt_margen == null) {
			_txt_margen = new JTextField();
			_txt_margen.setBounds(new Rectangle(748, 14, 34, 19));
			_txt_margen.setText("20");
			_txt_margen.setToolTipText("Margen por demoras en llegada del pedido o la realizacion del mismo o por previcion de faltantes");
		}
		return _txt_margen;
	}

	/**
	 * This method initializes _btn_control	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_control() {
		if (_btn_control == null) {
			_btn_control = new JButton();
			_btn_control.setIcon(new ImageIcon(getClass().getResource("/icons/editclear.png")));
			_btn_control.setToolTipText("Control de Stock de Productos A-B");
		}
		return _btn_control;
	}

	/**
	 * This method initializes JProgressBar	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	public JProgressBar getJProgressBar() {
		if (JProgressBar == null) {
			JProgressBar = new JProgressBar();
			JProgressBar.setBounds(new Rectangle(8, 82, 965, 23));
		}
		return JProgressBar;
	}

	/**
	 * This method initializes _btn_cancelar_tarea	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cancelar_tarea() {
		if (_btn_cancelar_tarea == null) {
			_btn_cancelar_tarea = new JButton();
			_btn_cancelar_tarea.setBounds(new Rectangle(977, 83, 22, 22));
			_btn_cancelar_tarea.setIcon(new ImageIcon(getClass().getResource("/icons/process-stop.png")));
		}
		return _btn_cancelar_tarea;
	}

	/**
	 * This method initializes _txt_descripcion_linea	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_descripcion_linea() {
		if (_txt_descripcion_linea == null) {
			_txt_descripcion_linea = new JTextField();
			_txt_descripcion_linea.setBounds(new Rectangle(274, 5, 136, 16));
		}
		return _txt_descripcion_linea;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
