package aplicacion.inventario.planilla.gui;



import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.JTabbedPane;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;


import java.awt.Font;
import java.awt.event.*;

import javax.swing.JCheckBox;
import javax.swing.JProgressBar;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;


public class _Frame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel jPanel = null;
	private JToolBar jToolBar = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel3 = null;
	private JLabel jLabel4 = null;
	private JButton _btn_guardar = null;
	private JButton _btn_cancelar = null;
	private JTextField _txt_idarticulo_desde = null;
	private JTextField _txt_linea = null;
	private JTextField _txt_idproveedor = null;
	private JTable jTable;
	private JButton _btn_cargar = null;
	private JToolBar jToolBar1 = null;
	private JButton _btn_completar = null;
	private JTextField _txt_idarticulo_hasta = null;
	private JButton _btn_buscar_reemplazar = null;
	private JButton _btn_sincronizar = null;
	private JButton _btn_eliminar = null;
	private JProgressBar JProgressBar = null;
	private JButton _btn_cancelar_tarea = null;
	private JButton _btn_salir = null;
	private JTextField _txt_descripcion_proveedor = null;
	private JButton _btn_buscar_proveedor = null;
	private JButton _btn_buscar_articulo_desde = null;
	private JButton _btn_buscar_articulo_hasta = null;
	private JButton _btn_buscar_linea = null;
	private JButton _btn_importar = null;
	private JButton _btn_exportar = null;
	private JButton _btn_aumentar_precios = null;
	private JCheckBox _chk_selecionar = null;
	private JScrollPane jScrollPane = null;
	private JButton _btn_politicas_de_precio = null;
	private JButton _btn_copiar_memoria = null;
	private JButton _btn_pegar_memoria = null;
	private JTextField _txt_limite = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel5 = null;
	private JComboBox _lst_desplazamiento = null;
	private JCheckBox _chk_verificar_articulos = null;
	private JCheckBox _chk_sincronizar_con_politica = null;
	private JCheckBox _chk_mostrar_errores = null;
	private JLabel jLabel6 = null;
	private JComboBox _list_marcar = null;
	private JButton _btn_editar_articulo = null;
	private JButton _btn_error = null;
	private JLabel jLabel7 = null;
	private JTextField _txt_descripcion = null;
	private JButton _btn_play = null;
	private JCheckBox _chk_stock = null;
	private JButton _btn_autoconfigurar = null;
	private JTextField _txt_cuenta_actualizacion = null;
	private JLabel jLabel8 = null;
	private JTextField _txt_articulo = null;
	private JTextField _txt_articulo_descripcion = null;
	private JTextField _txt_articulo_stock = null;
	private JTextField _txt_articulo_actualizacion = null;
	private JTextField _txt_articulo_linea = null;
	private JTextField _txt_articulo_bloqueado = null;
	private JTextField _txt_descripcion_no = null;
	private JCheckBox _chk_fecha_actualizacion = null;
	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabel8 = new JLabel();
			jLabel8.setBounds(new Rectangle(14, 101, 95, 15));
			jLabel8.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel8.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel8.setText("Actualizacion");
			jLabel7 = new JLabel();
			jLabel7.setBounds(new Rectangle(12, 74, 97, 18));
			jLabel7.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel7.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel7.setText("Descripcion");
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(647, 24, 91, 16));
			jLabel6.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel6.setText("Marcar");
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(645, 5, 90, 15));
			jLabel5.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel5.setHorizontalTextPosition(SwingConstants.LEADING);
			jLabel5.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel5.setText("Desplazamiento");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(645, 44, 90, 17));
			jLabel2.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2.setText("Limite de carga");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(11, 9, 96, 17));
			jLabel4.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel4.setText("Proveedor");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(12, 30, 96, 17));
			jLabel3.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3.setText("Linea");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(312, 51, 54, 17));
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setText("Hasta");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(11, 51, 97, 17));
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setText("Articulo");
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBounds(new Rectangle(6, 28, 1008, 666));
			jPanel.add(jLabel, null);
			jPanel.add(jLabel1, null);
			jPanel.add(jLabel3, null);
			jPanel.add(jLabel4, null);
			jPanel.add(get_txt_idarticulo_desde(), null);
			jPanel.add(get_txt_linea(), null);
			jPanel.add(get_txt_idproveedor(), null);
			jPanel.add(getJToolBar1(), null);
			jPanel.add(get_txt_idarticulo_hasta(), null);
			jPanel.add(getJProgressBar(), null);
			jPanel.add(get_btn_cancelar_tarea(), null);
			jPanel.add(get_txt_descripcion_proveedor(), null);
			jPanel.add(get_btn_buscar_proveedor(), null);
			jPanel.add(get_btn_buscar_articulo_desde(), null);
			jPanel.add(get_btn_buscar_articulo_hasta(), null);
			jPanel.add(get_btn_buscar_linea(), null);
			jPanel.add(get_chk_selecionar(), null);
			jPanel.add(getJScrollPane(), null);
			jPanel.add(get_txt_limite(), null);
			jPanel.add(jLabel2, null);
			jPanel.add(jLabel5, null);
			jPanel.add(get_lst_desplazamiento(), null);
			jPanel.add(get_chk_verificar_articulos(), null);
			jPanel.add(get_chk_sincronizar_con_politica(), null);
			jPanel.add(get_chk_mostrar_errores(), null);
			jPanel.add(jLabel6, null);
			jPanel.add(get_list_marcar(), null);
			jPanel.add(jLabel7, null);
			jPanel.add(get_txt_descripcion(), null);
			jPanel.add(get_chk_stock(), null);
			jPanel.add(get_txt_cuenta_actualizacion(), null);
			jPanel.add(jLabel8, null);
			jPanel.add(get_txt_articulo(), null);
			jPanel.add(get_txt_articulo_descripcion(), null);
			jPanel.add(get_txt_articulo_stock(), null);
			jPanel.add(get_txt_articulo_actualizacion(), null);
			jPanel.add(get_txt_articulo_linea(), null);
			jPanel.add(get_txt_articulo_bloqueado(), null);
			jPanel.add(get_txt_descripcion_no(), null);
			jPanel.add(get_chk_fecha_actualizacion(), null);
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
			jToolBar.setBounds(new Rectangle(7, 3, 1007, 22));
			jToolBar.setFloatable(false);
			jToolBar.add(get_btn_cancelar());
			jToolBar.add(get_btn_cargar());
			jToolBar.addSeparator();
			jToolBar.addSeparator();
			jToolBar.add(get_btn_guardar());
			jToolBar.add(get_btn_sincronizar());
			jToolBar.add(get_btn_autoconfigurar());
			jToolBar.add(get_btn_importar());
			jToolBar.add(get_btn_exportar());
			jToolBar.add(get_btn_aumentar_precios());
			jToolBar.add(get_btn_politicas_de_precio());
			jToolBar.add(get_btn_salir());
			jToolBar.addSeparator();
			jToolBar.add(get_btn_error());
			jToolBar.add(get_btn_play());
			
			
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
			_btn_guardar.setText("");
			_btn_guardar.setIcon(new ImageIcon(getClass().getResource("/icons/document-save-as.png")));
			_btn_guardar.setToolTipText("Guardar Cambios");
			
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
			_btn_cancelar.setText("");
			_btn_cancelar.setToolTipText("Cancelar");
			_btn_cancelar.setIcon(new ImageIcon(getClass().getResource("/icons/stock_calc-cancel.png")));
			
		}
		return _btn_cancelar;
	}

	/**
	 * This method initializes _txt_idarticulo_desde	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idarticulo_desde() {
		if (_txt_idarticulo_desde == null) {
			_txt_idarticulo_desde = new JTextField();
			_txt_idarticulo_desde.setBounds(new Rectangle(118, 51, 160, 17));
			_txt_idarticulo_desde.setToolTipText("Articulo desde el cual debe cargarse");
			_txt_idarticulo_desde.setFont(new Font("Dialog", Font.PLAIN, 10));
			
		}
		return _txt_idarticulo_desde;
	}
	
	/**
	 * This method initializes _txt_linea	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_linea() {
		if (_txt_linea == null) {
			_txt_linea = new JTextField();
			_txt_linea.setBounds(new Rectangle(118, 30, 412, 17));
			_txt_linea.setToolTipText("Linea de los Articulos que deben Cargarse");
			_txt_linea.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_linea;
	}

	/**
	 * This method initializes _txt_idproveedor	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idproveedor() {
		if (_txt_idproveedor == null) {
			_txt_idproveedor = new JTextField();
			_txt_idproveedor.setBounds(new Rectangle(118, 9, 137, 17));
			_txt_idproveedor.setToolTipText("Codigo de Proveedor de los Articulos que deben cargarse");
			_txt_idproveedor.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_idproveedor;
	}

	/**
	 * This method initializes _btn_cargar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cargar() {
		if (_btn_cargar == null) {
			_btn_cargar = new JButton();
			_btn_cargar.setText("");
			_btn_cargar.setToolTipText("Cargar Datos");
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
			jToolBar1.setBounds(new Rectangle(2, 150, 1000, 22));
			jToolBar1.setFloatable(false);
			jToolBar1.add(get_btn_completar());
			
			jToolBar1.add(get_btn_buscar_reemplazar());
			jToolBar1.add(get_btn_eliminar());
			jToolBar1.add(get_btn_copiar_memoria());
			jToolBar1.add(get_btn_pegar_memoria());
			jToolBar1.add(get_btn_editar_articulo());
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
			_btn_completar.setText("");
			_btn_completar.setToolTipText("Completar Valores");
			_btn_completar.setIcon(new ImageIcon(getClass().getResource("/icons/bottom.png")));
			
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
			_txt_idarticulo_hasta.setBounds(new Rectangle(372, 51, 160, 17));
			_txt_idarticulo_hasta.setToolTipText("Articulo Hasta el Cual debe cargarse");
			_txt_idarticulo_hasta.setFont(new Font("Dialog", Font.PLAIN, 10));
			
		}
		return _txt_idarticulo_hasta;
	}

	/**
	 * This method initializes _btn_buscar_reemplazar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_reemplazar() {
		if (_btn_buscar_reemplazar == null) {
			_btn_buscar_reemplazar = new JButton();
			_btn_buscar_reemplazar.setText("");
			_btn_buscar_reemplazar.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-searchtool.png")));
			//_btn_buscar_reemplazar.setEnabled(false);
			_btn_buscar_reemplazar.setToolTipText("Buscar/Reemplazar Dentro de la Tabla");
			
		}
		return _btn_buscar_reemplazar;
	}

	/**
	 * This method initializes _btn_sincronizar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_sincronizar() {
		if (_btn_sincronizar == null) {
			_btn_sincronizar = new JButton();
			_btn_sincronizar.setText("");
			_btn_sincronizar.setIcon(new ImageIcon(getClass().getResource("/icons/object-rotate-right.png")));
			_btn_sincronizar.setToolTipText("Sincronizar Precios Desde Base de Proveedores");
			
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
			_btn_eliminar.setText("");
			_btn_eliminar.setIcon(new ImageIcon(getClass().getResource("/icons/edit-delete.png")));
			_btn_eliminar.setEnabled(false);
			_btn_eliminar.setToolTipText("Eliminar Articulos Seleccionados");
		}
		return _btn_eliminar;
	}

	/**
	 * This method initializes JProgressBar	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	public JProgressBar getJProgressBar() {
		if (JProgressBar == null) {
			JProgressBar = new JProgressBar();
			JProgressBar.setBounds(new Rectangle(4, 129, 562, 17));
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
			_btn_cancelar_tarea.setBounds(new Rectangle(568, 129, 22, 17));
			_btn_cancelar_tarea.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-stop.png")));
			_btn_cancelar_tarea.setToolTipText("Cancelar Tarea En Ejecucion");
			_btn_cancelar_tarea.setText("");
			
		}
		return _btn_cancelar_tarea;
	}
	/**
	 * This method initializes _btn_salir	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_salir() {
		if (_btn_salir == null) {
			_btn_salir = new JButton();
			_btn_salir.setText("");
			_btn_salir.setToolTipText("Salir");
			_btn_salir.setIcon(new ImageIcon(getClass().getResource("/icons/exit.png")));
			
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
		this.setSize(new Dimension(1027, 736));
		this.setResizable(false);
		this.setContentPane(getJContentPane());
		this.setTitle("Planilla Edicion de Articulos");
		
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
	 * This method initializes _txt_descripcion_proveedor	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_descripcion_proveedor() {
		if (_txt_descripcion_proveedor == null) {
			_txt_descripcion_proveedor = new JTextField();
			_txt_descripcion_proveedor.setBounds(new Rectangle(283, 10, 281, 17));
			_txt_descripcion_proveedor.setEditable(false);
			_txt_descripcion_proveedor.setToolTipText("Nombre de Proveedor");
			_txt_descripcion_proveedor.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_descripcion_proveedor;
	}
	/**
	 * This method initializes _btn_buscar_proveedor	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_proveedor() {
		if (_btn_buscar_proveedor == null) {
			_btn_buscar_proveedor = new JButton();
			_btn_buscar_proveedor.setBounds(new Rectangle(260, 9, 19, 17));
			_btn_buscar_proveedor.setToolTipText("Buscar Proveedor");
			_btn_buscar_proveedor.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-searchtool.png")));
		}
		return _btn_buscar_proveedor;
	}
	/**
	 * This method initializes _btn_buscar_articulo_desde	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_articulo_desde() {
		if (_btn_buscar_articulo_desde == null) {
			_btn_buscar_articulo_desde = new JButton();
			_btn_buscar_articulo_desde.setBounds(new Rectangle(281, 50, 23, 18));
			_btn_buscar_articulo_desde.setToolTipText("Buscar Articulo");
			_btn_buscar_articulo_desde.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-searchtool.png")));
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
			_btn_buscar_articulo_hasta.setBounds(new Rectangle(536, 51, 23, 18));
			_btn_buscar_articulo_hasta.setToolTipText("Buscar Articulo");
			_btn_buscar_articulo_hasta.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-searchtool.png")));
		}
		return _btn_buscar_articulo_hasta;
	}
	/**
	 * This method initializes _btn_buscar_linea	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_linea() {
		if (_btn_buscar_linea == null) {
			_btn_buscar_linea = new JButton();
			_btn_buscar_linea.setBounds(new Rectangle(535, 29, 23, 18));
			_btn_buscar_linea.setToolTipText("Buscar Linea");
			_btn_buscar_linea.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-searchtool.png")));
		}
		return _btn_buscar_linea;
	}
	/**
	 * This method initializes _btn_importar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_importar() {
		if (_btn_importar == null) {
			_btn_importar = new JButton();
			_btn_importar.setIcon(new ImageIcon(getClass().getResource("/icons/go-bottom.png")));
			_btn_importar.setToolTipText("Importar Desde Archivo de Texto Tabulado");
			
		}
		return _btn_importar;
	}
	/**
	 * This method initializes _btn_exportar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_exportar() {
		if (_btn_exportar == null) {
			_btn_exportar = new JButton();
			_btn_exportar.setIcon(new ImageIcon(getClass().getResource("/icons/go-top.png")));
			_btn_exportar.setToolTipText("Exportar a Archivo de Texto Tabulado");
			
		}
		return _btn_exportar;
	}
	/**
	 * This method initializes _btn_aumentar_precios	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_aumentar_precios() {
		if (_btn_aumentar_precios == null) {
			_btn_aumentar_precios = new JButton();
			_btn_aumentar_precios.setIcon(new ImageIcon(getClass().getResource("/icons/cash-icon.png")));
			_btn_aumentar_precios.setToolTipText("Incrementar un % el Precio de Lista");
			
		}
		return _btn_aumentar_precios;
	}
	/**
	 * This method initializes _chk_selecionar	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_selecionar() {
		if (_chk_selecionar == null) {
			_chk_selecionar = new JCheckBox();
			_chk_selecionar.setBounds(new Rectangle(4, 173, 135, 14));
			_chk_selecionar.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_selecionar.setText("seleccionar");
		}
		return _chk_selecionar;
	}
	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(6, 192, 985, 425));
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
	
	public void setJTable(JTable table){
		this.jTable=table;
		this.jScrollPane.setViewportView(jTable);
	}
	/**
	 * This method initializes _btn_politicas_de_precio	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_politicas_de_precio() {
		if (_btn_politicas_de_precio == null) {
			_btn_politicas_de_precio = new JButton();
			_btn_politicas_de_precio.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-run.png")));
			_btn_politicas_de_precio.setToolTipText("Editar Politicas de Precio");
		}
		return _btn_politicas_de_precio;
	}
	/**
	 * This method initializes _btn_copiar_memoria	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_copiar_memoria() {
		if (_btn_copiar_memoria == null) {
			_btn_copiar_memoria = new JButton();
			_btn_copiar_memoria.setIcon(new ImageIcon(getClass().getResource("/icons/editcopy.png")));
			_btn_copiar_memoria.setToolTipText("Copiar al Porta Papeles");
		}
		return _btn_copiar_memoria;
	}
	/**
	 * This method initializes _btn_pegar_memoria	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_pegar_memoria() {
		if (_btn_pegar_memoria == null) {
			_btn_pegar_memoria = new JButton();
			_btn_pegar_memoria.setIcon(new ImageIcon(getClass().getResource("/icons/edit-paste.png")));
			_btn_pegar_memoria.setToolTipText("Pegar desde el Portapapeles");
		}
		return _btn_pegar_memoria;
	}
	/**
	 * This method initializes _txt_limite	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_limite() {
		if (_txt_limite == null) {
			_txt_limite = new JTextField();
			_txt_limite.setBounds(new Rectangle(744, 44, 60, 17));
			_txt_limite.setText("10000");
			_txt_limite.setHorizontalAlignment(JTextField.RIGHT);
			_txt_limite.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_limite.setToolTipText("Cantidad de Articulos que Cargara la Aplicacion. No se Recomiendan mas de 20.000");
		}
		return _txt_limite;
	}
	/**
	 * This method initializes _lst_desplazamiento	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_desplazamiento() {
		if (_lst_desplazamiento == null) {
			_lst_desplazamiento = new JComboBox();
			_lst_desplazamiento.setBounds(new Rectangle(743, 4, 123, 17));
			_lst_desplazamiento.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _lst_desplazamiento;
	}
	/**
	 * This method initializes _chk_verificar_articulos	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_verificar_articulos() {
		if (_chk_verificar_articulos == null) {
			_chk_verificar_articulos = new JCheckBox();
			_chk_verificar_articulos.setBounds(new Rectangle(644, 65, 291, 14));
			_chk_verificar_articulos.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_verificar_articulos.setText("Verficar Codigos de Articulos Para la Carga");
		}
		return _chk_verificar_articulos;
	}
	/**
	 * This method initializes _chk_sincronizar_con_politica	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_sincronizar_con_politica() {
		if (_chk_sincronizar_con_politica == null) {
			_chk_sincronizar_con_politica = new JCheckBox();
			_chk_sincronizar_con_politica.setBounds(new Rectangle(644, 85, 297, 14));
			_chk_sincronizar_con_politica.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_sincronizar_con_politica.setSelected(true);
			_chk_sincronizar_con_politica.setText("Al Sincronizar Precios aplicar politica Automaticamente");
		}
		return _chk_sincronizar_con_politica;
	}
	/**
	 * This method initializes _chk_mostrar_errores	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_mostrar_errores() {
		if (_chk_mostrar_errores == null) {
			_chk_mostrar_errores = new JCheckBox();
			_chk_mostrar_errores.setBounds(new Rectangle(644, 105, 338, 14));
			_chk_mostrar_errores.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_mostrar_errores.setText("Mostrar los Errores que se producen al realizar las Operaciones");
		}
		return _chk_mostrar_errores;
	}
	/**
	 * This method initializes _list_marcar	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_list_marcar() {
		if (_list_marcar == null) {
			_list_marcar = new JComboBox();
			_list_marcar.setBounds(new Rectangle(744, 24, 123, 17));
			_list_marcar.setEnabled(false);
		}
		return _list_marcar;
	}
	/**
	 * This method initializes _btn_editar_articulo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_editar_articulo() {
		if (_btn_editar_articulo == null) {
			_btn_editar_articulo = new JButton();
			_btn_editar_articulo.setIcon(new ImageIcon(getClass().getResource("/icons/stock_hyperlink-internet-search.png")));
			_btn_editar_articulo.setToolTipText("Ver Maestro del Articulo Seleccionado");
		}
		return _btn_editar_articulo;
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
	 * This method initializes _txt_descripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_descripcion() {
		if (_txt_descripcion == null) {
			_txt_descripcion = new JTextField();
			_txt_descripcion.setLocation(new Point(118, 73));
			_txt_descripcion.setToolTipText("Ingrese la descripcion que contiene");
			_txt_descripcion.setSize(new Dimension(215, 18));
		}
		return _txt_descripcion;
	}
	
	/**
	 * This method initializes _btn_play	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_play() {
		if (_btn_play == null) {
			_btn_play = new JButton();
			_btn_play.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-media-play-ltr.png")));
			_btn_play.setToolTipText("Restaurar");
		}
		return _btn_play;
	}
	/**
	 * This method initializes _chk_stock	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_stock() {
		if (_chk_stock == null) {
			_chk_stock = new JCheckBox();
			_chk_stock.setBounds(new Rectangle(644, 127, 93, 13));
			_chk_stock.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_stock.setText("stock >0");
		}
		return _chk_stock;
	}
	/**
	 * This method initializes _btn_autoconfigurar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_autoconfigurar() {
		if (_btn_autoconfigurar == null) {
			_btn_autoconfigurar = new JButton();
			_btn_autoconfigurar.setIcon(new ImageIcon(getClass().getResource("/icons/appointment-soon.png")));
			_btn_autoconfigurar.setToolTipText("AutoConfigurar La Actualizacion");
		}
		return _btn_autoconfigurar;
	}
	/**
	 * This method initializes _txt_cuenta_actualizacion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_cuenta_actualizacion() {
		if (_txt_cuenta_actualizacion == null) {
			_txt_cuenta_actualizacion = new JTextField();
			_txt_cuenta_actualizacion.setBounds(new Rectangle(118, 99, 136, 17));
			_txt_cuenta_actualizacion.setToolTipText("Cuenta de Actualizacion");
		}
		return _txt_cuenta_actualizacion;
	}
	/**
	 * This method initializes _txt_articulo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_articulo() {
		if (_txt_articulo == null) {
			_txt_articulo = new JTextField();
			_txt_articulo.setBounds(new Rectangle(8, 629, 100, 17));
			_txt_articulo.setForeground(Color.white);
			_txt_articulo.setBackground(Color.black);
			_txt_articulo.setEditable(false);
		}
		return _txt_articulo;
	}
	/**
	 * This method initializes _txt_articulo_descripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_articulo_descripcion() {
		if (_txt_articulo_descripcion == null) {
			_txt_articulo_descripcion = new JTextField();
			_txt_articulo_descripcion.setBounds(new Rectangle(112, 629, 245, 18));
			_txt_articulo_descripcion.setBackground(Color.black);
			_txt_articulo_descripcion.setForeground(Color.white);
			_txt_articulo_descripcion.setHorizontalAlignment(JTextField.LEFT);
			_txt_articulo_descripcion.setEditable(false);
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
			_txt_articulo_stock.setBounds(new Rectangle(361, 629, 69, 18));
			_txt_articulo_stock.setBackground(Color.black);
			_txt_articulo_stock.setForeground(Color.white);
			_txt_articulo_stock.setEditable(false);
		}
		return _txt_articulo_stock;
	}
	/**
	 * This method initializes _txt_articulo_actualizacion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_articulo_actualizacion() {
		if (_txt_articulo_actualizacion == null) {
			_txt_articulo_actualizacion = new JTextField();
			_txt_articulo_actualizacion.setBounds(new Rectangle(434, 629, 107, 18));
			_txt_articulo_actualizacion.setBackground(Color.black);
			_txt_articulo_actualizacion.setForeground(Color.white);
			_txt_articulo_actualizacion.setEditable(false);
		}
		return _txt_articulo_actualizacion;
	}
	/**
	 * This method initializes _txt_articulo_linea	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_articulo_linea() {
		if (_txt_articulo_linea == null) {
			_txt_articulo_linea = new JTextField();
			_txt_articulo_linea.setBounds(new Rectangle(546, 629, 126, 18));
			_txt_articulo_linea.setBackground(Color.black);
			_txt_articulo_linea.setForeground(Color.white);
			_txt_articulo_linea.setEditable(false);
		}
		return _txt_articulo_linea;
	}
	/**
	 * This method initializes _txt_articulo_bloqueado	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_articulo_bloqueado() {
		if (_txt_articulo_bloqueado == null) {
			_txt_articulo_bloqueado = new JTextField();
			_txt_articulo_bloqueado.setBounds(new Rectangle(677, 629, 105, 18));
			_txt_articulo_bloqueado.setBackground(Color.black);
			_txt_articulo_bloqueado.setForeground(Color.white);
			_txt_articulo_bloqueado.setEditable(false);
		}
		return _txt_articulo_bloqueado;
	}
	/**
	 * This method initializes _txt_descripcion_no	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_descripcion_no() {
		if (_txt_descripcion_no == null) {
			_txt_descripcion_no = new JTextField();
			_txt_descripcion_no.setSize(new Dimension(221, 18));
			_txt_descripcion_no.setToolTipText("Ingrese La descripcion que no contiene");
			_txt_descripcion_no.setLocation(new Point(338, 73));
		}
		return _txt_descripcion_no;
	}
	/**
	 * This method initializes _chk_fecha_actualizacion	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_fecha_actualizacion() {
		if (_chk_fecha_actualizacion == null) {
			_chk_fecha_actualizacion = new JCheckBox();
			_chk_fecha_actualizacion.setBounds(new Rectangle(751, 126, 185, 19));
			_chk_fecha_actualizacion.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_fecha_actualizacion.setText("Marcar Fecha de Actualizacion");
		}
		return _chk_fecha_actualizacion;
	}
	
	
}  //  @jve:decl-index=0:visual-constraint="13,22"
