package aplicacion.inventario.etiqueta.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Point;
import java.awt.Dimension;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import java.awt.GridBagLayout;
import javax.swing.JCheckBox;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JToolBar jToolBar = null;
	private JButton _btn_cancelar = null;
	private JButton _btn_guardar = null;
	private JButton _btn_salir = null;
	private JButton _btn_error = null;
	private JButton _btn_eliminar = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private JButton _btn_imprimir_etiquetas = null;
	private JButton _btn_abrir = null;
	private JTabbedPane jTabbedPane = null;
	private JPanel jPanel = null;
	private JToolBar jToolBar1 = null;
	private JButton _btn_importar = null;
	private JButton _btn_cargar_stock = null;
	private JCheckBox _chk_seleccionar = null;
	private JLabel jLabel2 = null;
	private JTextField _txt_articulo_desde = null;
	private JTextField _txt_articulo_hasta = null;
	private JTextField _txt_idproveedor = null;
	private JTextField _txt_linea = null;
	private JLabel jLabel5 = null;
	private JTextField _txt_proveedor_descripcion = null;
	private JLabel jLabel6 = null;
	private JLabel jLabel7 = null;
	private JScrollPane jScrollPane1 = null;
	private JTable jTable1 = null;
	private JCheckBox _chk_mostrar_faltante = null;
	private JButton _btn_clean = null;
	private JPanel jPanel1 = null;
	private JCheckBox _chk_seleccionar_items = null;
	private JToolBar jToolBar2 = null;
	private JButton _btn_quitar = null;
	private JPanel jPanel2 = null;
	private JCheckBox _chk_existentes = null;
	private JCheckBox _chk_permite_repeticiones = null;
	private JCheckBox _chk_solo_una_etiqueta = null;
	private JCheckBox _chk_etiquetas_especiales = null;
	private JLabel jLabel = null;
	private JTextField _txt_especial = null;
	private JCheckBox _chk_imprime_codigo_de_barras = null;
	private JPanel jPanel3 = null;
	private JPanel jPanel4 = null;
	private JButton _btn_email = null;
	private JLabel jLabel1 = null;
	private JTextField _txt_iddeposito = null;
	private JTextField _txt_deposito_descripcion = null;
	private JToolBar jToolBar3 = null;
	private JButton _btn_desmarcar = null;
	private JButton _btn_cargar_historial = null;
	private JCheckBox _chk_mostrar_impresas = null;
	private JCheckBox _chk_seleccionar_historial = null;
	private JScrollPane jScrollPane2 = null;
	private JTable jTable2 = null;
	private JButton _btn_marcar = null;
	private JLabel jLabel3 = null;
	private JTextField _txt_desde = null;
	private JLabel jLabel4 = null;
	private JTextField _txt_hasta = null;
	private JButton _btn_buscar_fecha_desde = null;
	private JButton _btn_buscar_fecha_hasta = null;
	private JButton _btn_guardar_historial = null;
	private JScrollPane jScrollPane3 = null;
	private JTable jTable_fields = null;
	private JScrollPane jScrollPane4 = null;
	private JTable jTable_cptes = null;
	private JScrollPane jScrollPane5 = null;
	private JTable jTable4 = null;
	private JToolBar jToolBar4 = null;
	private JButton _btn_importar_items = null;
	private JCheckBox _chk_seleccionar_items_cptes = null;
	private JCheckBox _chk_seleccionar_cptes = null;
	private JToolBar jToolBar5 = null;
	private JButton _btn_importar_cptes = null;
	private JButton _btn_buscar = null;
	private JButton _btn_cancelar_busqueda = null;
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
		this.setSize(1000, 689);
		this.setContentPane(getJContentPane());
		this.setTitle("Impresion de Etiquetas");
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
			jContentPane.add(getJTabbedPane(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jToolBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar() {
		if (jToolBar == null) {
			jToolBar = new JToolBar();
			jToolBar.setBounds(new Rectangle(0, 0, 986, 24));
			jToolBar.setFloatable(false);
			jToolBar.add(get_btn_cancelar());
			jToolBar.add(get_btn_abrir());
			jToolBar.add(get_btn_guardar());
			jToolBar.add(get_btn_eliminar());
			jToolBar.addSeparator(new Dimension(60,20));
			jToolBar.add(get_btn_imprimir_etiquetas());
			jToolBar.add(get_btn_email());
			jToolBar.addSeparator();
			
			jToolBar.addSeparator(new Dimension(60,20));
			jToolBar.add(get_btn_salir());
			jToolBar.add(get_btn_error());
		}
		return jToolBar;
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
			_btn_cancelar.setToolTipText("Cancelar Operacion");
		}
		return _btn_cancelar;
	}

	/**
	 * This method initializes _btn_guardar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_guardar() {
		if (_btn_guardar == null) {
			_btn_guardar = new JButton();
			_btn_guardar.setIcon(new ImageIcon(getClass().getResource("/icons/document-save.png")));
			_btn_guardar.setToolTipText("Guardar Transferencia");
		}
		return _btn_guardar;
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
			_btn_salir.setToolTipText("Salir de Aplicacion");
		}
		return _btn_salir;
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
	 * This method initializes _btn_eliminar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_eliminar() {
		if (_btn_eliminar == null) {
			_btn_eliminar = new JButton();
			_btn_eliminar.setIcon(new ImageIcon(getClass().getResource("/icons/edit-delete.png")));
			_btn_eliminar.setToolTipText("Elimimnar Transferencia");
		}
		return _btn_eliminar;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(5, 52, 939, 372));
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
	 * This method initializes _btn_imprimir_etiquetas	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_imprimir_etiquetas() {
		if (_btn_imprimir_etiquetas == null) {
			_btn_imprimir_etiquetas = new JButton();
			_btn_imprimir_etiquetas.setIcon(new ImageIcon(getClass().getResource("/icons/emblem-sales-24.png")));
		}
		return _btn_imprimir_etiquetas;
	}

	/**
	 * This method initializes _btn_abrir	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_abrir() {
		if (_btn_abrir == null) {
			_btn_abrir = new JButton();
			_btn_abrir.setIcon(new ImageIcon(getClass().getResource("/icons/document-open.png")));
			_btn_abrir.setToolTipText("Nueva Transferencia");
		}
		return _btn_abrir;
	}

	/**
	 * This method initializes jTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	public JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.setBounds(new Rectangle(3, 31, 961, 484));
			jTabbedPane.setFont(new Font("Dialog", Font.PLAIN, 10));
			jTabbedPane.addTab("Etiquetas", null, getJPanel1(), null);
			jTabbedPane.addTab("Stock", null, getJPanel(), null);
			jTabbedPane.addTab("Preferencias", null, getJPanel2(), null);
			jTabbedPane.addTab("Historial", null, getJPanel3(), null);
			jTabbedPane.addTab("Comprobantes", null, getJPanel4(), null);
			
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
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(10, 28, 85, 15));
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setText("IdDeposito");
			jLabel7 = new JLabel();
			jLabel7.setBounds(new Rectangle(231, 91, 78, 16));
			jLabel7.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel7.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel7.setText("Hasta");
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(10, 71, 86, 14));
			jLabel6.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel6.setText("Linea");
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(11, 49, 83, 15));
			jLabel5.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel5.setText("Proveedor");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(10, 92, 86, 15));
			jLabel2.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel2.setText("IdArticulo Desde");
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.add(getJToolBar1(), null);
			jPanel.add(get_chk_seleccionar(), null);
			jPanel.add(jLabel2, null);
			jPanel.add(get_txt_articulo_desde(), null);
			jPanel.add(get_txt_articulo_hasta(), null);
			jPanel.add(get_txt_idproveedor(), null);
			jPanel.add(get_txt_linea(), null);
			jPanel.add(jLabel5, null);
			jPanel.add(get_txt_proveedor_descripcion(), null);
			jPanel.add(jLabel6, null);
			jPanel.add(jLabel7, null);
			jPanel.add(getJScrollPane1(), null);
			jPanel.add(get_chk_mostrar_faltante(), null);
			jPanel.add(get_chk_solo_una_etiqueta(), null);
			jPanel.add(jLabel1, null);
			jPanel.add(get_txt_iddeposito(), null);
			jPanel.add(get_txt_deposito_descripcion(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jToolBar1	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar1() {
		if (jToolBar1 == null) {
			jToolBar1 = new JToolBar();
			jToolBar1.setBounds(new Rectangle(3, 3, 949, 22));
			jToolBar1.setFloatable(false);
			jToolBar1.add(get_btn_importar());
			jToolBar1.add(get_btn_cargar_stock());
			jToolBar1.add(get_btn_clean());
		}
		return jToolBar1;
	}

	/**
	 * This method initializes _btn_importar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_importar() {
		if (_btn_importar == null) {
			_btn_importar = new JButton();
			_btn_importar.setIcon(new ImageIcon(getClass().getResource("/icons/back.png")));
		}
		return _btn_importar;
	}

	/**
	 * This method initializes _btn_cargar_stock	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cargar_stock() {
		if (_btn_cargar_stock == null) {
			_btn_cargar_stock = new JButton();
			_btn_cargar_stock.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-refresh.png")));
		}
		return _btn_cargar_stock;
	}

	/**
	 * This method initializes _chk_seleccionar	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_seleccionar() {
		if (_chk_seleccionar == null) {
			_chk_seleccionar = new JCheckBox();
			_chk_seleccionar.setBounds(new Rectangle(11, 118, 114, 16));
			_chk_seleccionar.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_seleccionar.setText("Seleccionar");
		}
		return _chk_seleccionar;
	}

	/**
	 * This method initializes _txt_articulo_desde	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_articulo_desde() {
		if (_txt_articulo_desde == null) {
			_txt_articulo_desde = new JTextField();
			_txt_articulo_desde.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_articulo_desde.setSize(new Dimension(105, 18));
			_txt_articulo_desde.setLocation(new Point(101, 91));
		}
		return _txt_articulo_desde;
	}

	/**
	 * This method initializes _txt_articulo_hasta	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_articulo_hasta() {
		if (_txt_articulo_hasta == null) {
			_txt_articulo_hasta = new JTextField();
			_txt_articulo_hasta.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_articulo_hasta.setSize(new Dimension(105, 18));
			_txt_articulo_hasta.setLocation(new Point(312, 91));
		}
		return _txt_articulo_hasta;
	}

	/**
	 * This method initializes _txt_idproveedor	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idproveedor() {
		if (_txt_idproveedor == null) {
			_txt_idproveedor = new JTextField();
			_txt_idproveedor.setBounds(new Rectangle(99, 48, 108, 17));
			_txt_idproveedor.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_idproveedor;
	}

	/**
	 * This method initializes _txt_linea	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_linea() {
		if (_txt_linea == null) {
			_txt_linea = new JTextField();
			_txt_linea.setBounds(new Rectangle(100, 70, 322, 16));
			_txt_linea.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_linea;
	}

	/**
	 * This method initializes _txt_proveedor_descripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_proveedor_descripcion() {
		if (_txt_proveedor_descripcion == null) {
			_txt_proveedor_descripcion = new JTextField();
			_txt_proveedor_descripcion.setBounds(new Rectangle(209, 48, 208, 16));
			_txt_proveedor_descripcion.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_proveedor_descripcion.setEditable(false);
		}
		return _txt_proveedor_descripcion;
	}

	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setBounds(new Rectangle(11, 139, 911, 297));
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

	
	public void setJTable1(JTable table){
		this.jTable1=table;
		this.jScrollPane1.setViewportView(jTable1);
	}
	/**
	 * This method initializes _chk_mostrar_faltante	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_mostrar_faltante() {
		if (_chk_mostrar_faltante == null) {
			_chk_mostrar_faltante = new JCheckBox();
			_chk_mostrar_faltante.setBounds(new Rectangle(446, 30, 179, 17));
			_chk_mostrar_faltante.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_mostrar_faltante.setSelected(true);
			_chk_mostrar_faltante.setToolTipText("Muestra los items en stock del deposito origen que no se encuentren en el deposito Destino");
			_chk_mostrar_faltante.setText("Mostrar Stock > 0");
		}
		return _chk_mostrar_faltante;
	}

	/**
	 * This method initializes _btn_clean	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_clean() {
		if (_btn_clean == null) {
			_btn_clean = new JButton();
			_btn_clean.setIcon(new ImageIcon(getClass().getResource("/icons/stock_calc-cancel.png")));
			_btn_clean.setToolTipText("Limpia Esta Seccion de Importacion");
		}
		return _btn_clean;
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
			jPanel1.add(get_chk_seleccionar_items(), null);
			jPanel1.add(getJToolBar2(), null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes _chk_seleccionar_items	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_seleccionar_items() {
		if (_chk_seleccionar_items == null) {
			_chk_seleccionar_items = new JCheckBox();
			_chk_seleccionar_items.setBounds(new Rectangle(6, 28, 137, 20));
			_chk_seleccionar_items.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_seleccionar_items.setText("seleccionar");
		}
		return _chk_seleccionar_items;
	}

	/**
	 * This method initializes jToolBar2	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar2() {
		if (jToolBar2 == null) {
			jToolBar2 = new JToolBar();
			jToolBar2.setSize(new Dimension(954, 25));
			jToolBar2.setFloatable(false);
			jToolBar2.setLocation(new Point(0, 0));
			jToolBar2.add(get_btn_quitar());
		}
		return jToolBar2;
	}

	/**
	 * This method initializes _btn_quitar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_quitar() {
		if (_btn_quitar == null) {
			_btn_quitar = new JButton();
			_btn_quitar.setIcon(new ImageIcon(getClass().getResource("/icons/edit-delete.png")));
			_btn_quitar.setToolTipText("Quitar Articulos Seleccionados");
		}
		return _btn_quitar;
	}

	/**
	 * This method initializes jPanel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(269, 68, 184, 17));
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setText("Tamaño de Etiquetas Especiales");
			jPanel2 = new JPanel();
			jPanel2.setLayout(null);
			jPanel2.add(get_chk_existentes(), null);
			jPanel2.add(get_chk_permite_repeticiones(), null);
			jPanel2.add(get_chk_etiquetas_especiales(), null);
			jPanel2.add(jLabel, null);
			jPanel2.add(get_txt_especial(), null);
			jPanel2.add(get_chk_imprime_codigo_de_barras(), null);
		}
		return jPanel2;
	}

	/**
	 * This method initializes _chk_existentes	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_existentes() {
		if (_chk_existentes == null) {
			_chk_existentes = new JCheckBox();
			_chk_existentes.setBounds(new Rectangle(16, 16, 208, 14));
			_chk_existentes.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_existentes.setText("Permite codigos no existentes");
		}
		return _chk_existentes;
	}

	/**
	 * This method initializes _chk_permite_repeticiones	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_permite_repeticiones() {
		if (_chk_permite_repeticiones == null) {
			_chk_permite_repeticiones = new JCheckBox();
			_chk_permite_repeticiones.setBounds(new Rectangle(16, 40, 170, 18));
			_chk_permite_repeticiones.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_permite_repeticiones.setText("Permite Repeticiones");
		}
		return _chk_permite_repeticiones;
	}

	/**
	 * This method initializes _chk_solo_una_etiqueta	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_solo_una_etiqueta() {
		if (_chk_solo_una_etiqueta == null) {
			_chk_solo_una_etiqueta = new JCheckBox();
			_chk_solo_una_etiqueta.setBounds(new Rectangle(446, 54, 172, 17));
			_chk_solo_una_etiqueta.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_solo_una_etiqueta.setText("Solo una Etiqueta");
		}
		return _chk_solo_una_etiqueta;
	}

	/**
	 * This method initializes _chk_etiquetas_especiales	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_etiquetas_especiales() {
		if (_chk_etiquetas_especiales == null) {
			_chk_etiquetas_especiales = new JCheckBox();
			_chk_etiquetas_especiales.setBounds(new Rectangle(17, 65, 192, 19));
			_chk_etiquetas_especiales.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_etiquetas_especiales.setText("Marcar Como Etiquetas Especiales");
		}
		return _chk_etiquetas_especiales;
	}

	/**
	 * This method initializes _txt_especial	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_especial() {
		if (_txt_especial == null) {
			_txt_especial = new JTextField();
			_txt_especial.setBounds(new Rectangle(457, 69, 59, 16));
		}
		return _txt_especial;
	}

	/**
	 * This method initializes _chk_imprime_codigo_de_barras	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_imprime_codigo_de_barras() {
		if (_chk_imprime_codigo_de_barras == null) {
			_chk_imprime_codigo_de_barras = new JCheckBox();
			_chk_imprime_codigo_de_barras.setBounds(new Rectangle(16, 92, 193, 21));
			_chk_imprime_codigo_de_barras.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_imprime_codigo_de_barras.setSelected(true);
			_chk_imprime_codigo_de_barras.setText("Imprime Codigo de Barras");
		}
		return _chk_imprime_codigo_de_barras;
	}

	/**
	 * This method initializes jPanel3	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(566, 39, 55, 18));
			jLabel4.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel4.setText("Hasta");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(338, 41, 57, 16));
			jLabel3.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel3.setText("Desde");
			jPanel3 = new JPanel();
			jPanel3.setLayout(null);
			jPanel3.add(getJToolBar3(), null);
			jPanel3.add(get_chk_mostrar_impresas(), null);
			jPanel3.add(get_chk_seleccionar_historial(), null);
			jPanel3.add(getJScrollPane2(), null);
			jPanel3.add(jLabel3, null);
			jPanel3.add(get_txt_desde(), null);
			jPanel3.add(jLabel4, null);
			jPanel3.add(get_txt_hasta(), null);
			jPanel3.add(get_btn_buscar_fecha_desde(), null);
			jPanel3.add(get_btn_buscar_fecha_hasta(), null);
		}
		return jPanel3;
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
			jPanel4.add(getJScrollPane4(), null);
			jPanel4.add(getJScrollPane5(), null);
			jPanel4.add(getJToolBar4(), null);
			jPanel4.add(get_chk_seleccionar_items_cptes(), null);
			jPanel4.add(get_chk_seleccionar_cptes(), null);
			jPanel4.add(getJToolBar5(), null);
		}
		return jPanel4;
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
			_btn_email.setToolTipText("Enviar Por Email");
		}
		return _btn_email;
	}

	/**
	 * This method initializes _txt_iddeposito	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_iddeposito() {
		if (_txt_iddeposito == null) {
			_txt_iddeposito = new JTextField();
			_txt_iddeposito.setBounds(new Rectangle(100, 27, 42, 18));
		}
		return _txt_iddeposito;
	}

	/**
	 * This method initializes _txt_deposito_descripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_deposito_descripcion() {
		if (_txt_deposito_descripcion == null) {
			_txt_deposito_descripcion = new JTextField();
			_txt_deposito_descripcion.setBounds(new Rectangle(147, 28, 120, 17));
			_txt_deposito_descripcion.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_deposito_descripcion.setEditable(false);
		}
		return _txt_deposito_descripcion;
	}

	/**
	 * This method initializes jToolBar3	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar3() {
		if (jToolBar3 == null) {
			jToolBar3 = new JToolBar();
			jToolBar3.setBounds(new Rectangle(2, 3, 951, 28));
			jToolBar3.setFloatable(false);
			jToolBar3.add(get_btn_cargar_historial());
			jToolBar3.add(get_btn_marcar());
			jToolBar3.add(get_btn_desmarcar());
			jToolBar3.add(get_btn_guardar_historial());
		}
		return jToolBar3;
	}

	/**
	 * This method initializes _btn_desmarcar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_desmarcar() {
		if (_btn_desmarcar == null) {
			_btn_desmarcar = new JButton();
			_btn_desmarcar.setIcon(new ImageIcon(getClass().getResource("/icons/stock_calc-cancel.png")));
			_btn_desmarcar.setToolTipText("Marca Como no Impresas las etiquetas seleccionadas del historial");
		}
		return _btn_desmarcar;
	}

	/**
	 * This method initializes _btn_cargar_historial	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cargar_historial() {
		if (_btn_cargar_historial == null) {
			_btn_cargar_historial = new JButton();
			_btn_cargar_historial.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-refresh.png")));
		}
		return _btn_cargar_historial;
	}

	/**
	 * This method initializes _chk_mostrar_impresas	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_mostrar_impresas() {
		if (_chk_mostrar_impresas == null) {
			_chk_mostrar_impresas = new JCheckBox();
			_chk_mostrar_impresas.setText("Incluir Impresas");
			_chk_mostrar_impresas.setBounds(new Rectangle(139, 39, 107, 24));
			_chk_mostrar_impresas.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _chk_mostrar_impresas;
	}

	/**
	 * This method initializes _chk_seleccionar_historial	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_seleccionar_historial() {
		if (_chk_seleccionar_historial == null) {
			_chk_seleccionar_historial = new JCheckBox();
			_chk_seleccionar_historial.setBounds(new Rectangle(15, 40, 112, 22));
			_chk_seleccionar_historial.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_seleccionar_historial.setText("Seleccionar");
		}
		return _chk_seleccionar_historial;
	}

	/**
	 * This method initializes jScrollPane2	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane2() {
		if (jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setBounds(new Rectangle(11, 123, 917, 303));
			jScrollPane2.setViewportView(getJTable2());
		}
		return jScrollPane2;
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

	public void setJTable2(JTable table){
		this.jTable2=table;
		this.jScrollPane2.setViewportView(table);
	}
	/**
	 * This method initializes _btn_marcar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_marcar() {
		if (_btn_marcar == null) {
			_btn_marcar = new JButton();
			_btn_marcar.setIcon(new ImageIcon(getClass().getResource("/icons/emblem-default-16.png")));
			_btn_marcar.setToolTipText("Marcar como Impresas las etiquetas seleccionadas del Historial");
		}
		return _btn_marcar;
	}

	/**
	 * This method initializes _txt_desde	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_desde() {
		if (_txt_desde == null) {
			_txt_desde = new JTextField();
			_txt_desde.setBounds(new Rectangle(406, 40, 100, 16));
			_txt_desde.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_desde;
	}

	/**
	 * This method initializes _txt_hasta	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_hasta() {
		if (_txt_hasta == null) {
			_txt_hasta = new JTextField();
			_txt_hasta.setBounds(new Rectangle(627, 37, 111, 18));
			_txt_hasta.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_hasta;
	}

	/**
	 * This method initializes _btn_buscar_fecha_desde	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_fecha_desde() {
		if (_btn_buscar_fecha_desde == null) {
			_btn_buscar_fecha_desde = new JButton();
			_btn_buscar_fecha_desde.setIcon(new ImageIcon(getClass().getResource("/icons/calendar-16.gif")));
			_btn_buscar_fecha_desde.setSize(new Dimension(22, 20));
			_btn_buscar_fecha_desde.setLocation(new Point(509, 38));
		}
		return _btn_buscar_fecha_desde;
	}

	/**
	 * This method initializes _btn_buscar_fecha_hasta	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_fecha_hasta() {
		if (_btn_buscar_fecha_hasta == null) {
			_btn_buscar_fecha_hasta = new JButton();
			_btn_buscar_fecha_hasta.setIcon(new ImageIcon(getClass().getResource("/icons/calendar-16.gif")));
			_btn_buscar_fecha_hasta.setSize(new Dimension(22, 20));
			_btn_buscar_fecha_hasta.setLocation(new Point(740, 36));
		}
		return _btn_buscar_fecha_hasta;
	}

	/**
	 * This method initializes _btn_guardar_historial	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_guardar_historial() {
		if (_btn_guardar_historial == null) {
			_btn_guardar_historial = new JButton();
			_btn_guardar_historial.setIcon(new ImageIcon(getClass().getResource("/icons/document-save.png")));
			_btn_guardar_historial.setToolTipText("Guardar Modificaciones");
		}
		return _btn_guardar_historial;
	}

	/**
	 * This method initializes jScrollPane3	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane3() {
		if (jScrollPane3 == null) {
			jScrollPane3 = new JScrollPane();
			jScrollPane3.setBounds(new Rectangle(8, 27, 862, 55));
			jScrollPane3.setViewportView(getJTable_fields());
		}
		return jScrollPane3;
	}

	/**
	 * This method initializes jTable_fields	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getJTable_fields() {
		if (jTable_fields == null) {
			jTable_fields = new JTable();
		}
		return jTable_fields;
	}

	public void setJtable_fields(){
		
	}
	/**
	 * This method initializes jScrollPane4	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane4() {
		if (jScrollPane4 == null) {
			jScrollPane4 = new JScrollPane();
			jScrollPane4.setBounds(new Rectangle(7, 108, 865, 108));
			jScrollPane4.setViewportView(getJTable_cptes());
		}
		return jScrollPane4;
	}

	/**
	 * This method initializes jTable_cptes	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTable_cptes() {
		if (jTable_cptes == null) {
			jTable_cptes = new JTable();
		}
		return jTable_cptes;
	}

	/**
	 * This method initializes jScrollPane5	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane5() {
		if (jScrollPane5 == null) {
			jScrollPane5 = new JScrollPane();
			jScrollPane5.setBounds(new Rectangle(5, 280, 784, 165));
			jScrollPane5.setViewportView(getJTable4());
		}
		return jScrollPane5;
	}

	/**
	 * This method initializes jTable4	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTable4() {
		if (jTable4 == null) {
			jTable4 = new JTable();
		}
		return jTable4;
	}

	/**
	 * This method initializes jToolBar4	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar4() {
		if (jToolBar4 == null) {
			jToolBar4 = new JToolBar();
			jToolBar4.setBounds(new Rectangle(3, 225, 946, 26));
			jToolBar4.setFloatable(false);
			jToolBar4.add(get_btn_importar_items());
		}
		return jToolBar4;
	}

	/**
	 * This method initializes _btn_importar_items	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_importar_items() {
		if (_btn_importar_items == null) {
			_btn_importar_items = new JButton();
			_btn_importar_items.setIcon(new ImageIcon(getClass().getResource("/icons/back.png")));
		}
		return _btn_importar_items;
	}

	/**
	 * This method initializes _chk_seleccionar_items_cptes	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_seleccionar_items_cptes() {
		if (_chk_seleccionar_items_cptes == null) {
			_chk_seleccionar_items_cptes = new JCheckBox();
			_chk_seleccionar_items_cptes.setBounds(new Rectangle(6, 259, 136, 17));
			_chk_seleccionar_items_cptes.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_seleccionar_items_cptes.setText("Seleccionar");
		}
		return _chk_seleccionar_items_cptes;
	}

	/**
	 * This method initializes _chk_seleccionar_cptes	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox get_chk_seleccionar_cptes() {
		if (_chk_seleccionar_cptes == null) {
			_chk_seleccionar_cptes = new JCheckBox();
			_chk_seleccionar_cptes.setBounds(new Rectangle(6, 89, 110, 16));
			_chk_seleccionar_cptes.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_seleccionar_cptes.setText("Seleccionar");
		}
		return _chk_seleccionar_cptes;
	}

	/**
	 * This method initializes jToolBar5	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar5() {
		if (jToolBar5 == null) {
			jToolBar5 = new JToolBar();
			jToolBar5.setBounds(new Rectangle(3, 2, 948, 24));
			jToolBar5.setFloatable(false);
			jToolBar5.add(get_btn_importar_cptes());
			jToolBar5.add(get_btn_buscar());
			jToolBar5.add(get_btn_cancelar_busqueda());
		}
		return jToolBar5;
	}

	/**
	 * This method initializes _btn_importar_cptes	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_importar_cptes() {
		if (_btn_importar_cptes == null) {
			_btn_importar_cptes = new JButton();
			_btn_importar_cptes.setIcon(new ImageIcon(getClass().getResource("/icons/back.png")));
		}
		return _btn_importar_cptes;
	}

	/**
	 * This method initializes _btn_buscar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar() {
		if (_btn_buscar == null) {
			_btn_buscar = new JButton();
			_btn_buscar.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-searchtool.png")));
		}
		return _btn_buscar;
	}

	/**
	 * This method initializes _btn_cancelar_busqueda	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cancelar_busqueda() {
		if (_btn_cancelar_busqueda == null) {
			_btn_cancelar_busqueda = new JButton();
			_btn_cancelar_busqueda.setIcon(new ImageIcon(getClass().getResource("/icons/stock_calc-cancel.png")));
		}
		return _btn_cancelar_busqueda;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
