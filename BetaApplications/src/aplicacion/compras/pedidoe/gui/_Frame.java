package aplicacion.compras.pedidoe.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Font;

import javax.swing.JComponent;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import java.awt.GridBagLayout;
import java.awt.Color;
import javax.swing.JComboBox;

import org.jdesktop.jxlayer.JXLayer;
import org.jdesktop.jxlayer.plaf.effect.BufferedImageOpEffect;
import org.jdesktop.jxlayer.plaf.ext.LockableUI;

import com.jhlabs.image.BoxBlurFilter;
import javax.swing.JTabbedPane;
import java.awt.Point;
import javax.swing.JSplitPane;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JToolBar jToolBar = null;
	private JButton _btn_cancelar = null;
	private JButton _btn_guardar = null;
	private JButton _btn_salir = null;
	private JLabel jLabel = null;
	private JTextField _txt_idpedido = null;
	private JButton _btn_buscar_pedido = null;
	private JButton _btn_cargar_pedido = null;
	private JTextField _txt_pedido_descripcion = null;
	private JLabel jLabel1 = null;
	private JTextField _txt_idcliente = null;
	private JButton _btn_buscar_cliente = null;  //  @jve:decl-index=0:
	private JButton _btn_cargar_cliente = null;
	private JTextField _txt_cliente_descripcion = null;
	private JLabel jLabel2 = null;
	private JTextField _txt_fecha = null;
	private JButton _btn_buscar_fecha = null;
	private JLabel jLabel3 = null;
	private JTextField _txt_fecha_creacion = null;
	private JButton _btn_preparar = null;
	private JButton _btn_presupuesto = null;
	private JButton _btn_remito = null;
	private JButton _btn_editar_cliente = null;
	private JScrollPane jScrollPane = null;
	private JTextArea _txt_informacion = null;
	private JLabel jLabel5 = null;
	private JTextField _txt_modificado = null;
	private JLabel jLabel6 = null;
	private JTextField _txt_idvendedor = null;
	private JTextField _txt_vendedor_descripcion = null;
	private JScrollPane jScrollPane1 = null;
	private JTable jTable = null;
	private JButton _btn_buscar_vendedor = null;
	private JTextField _txt_articulo = null;
	private JTextField _txt_articulo_descripcion = null;
	private JTextField _txt_articulo_stock = null;
	private JCheckBox _chk_seleccionar = null;
	private JPanel jPanel = null;
	private JLabel jLabel11 = null;
	private JTextField _txt_items = null;
	private JLabel jLabel12 = null;
	private JTextField _txt_subtotal = null;
	private JLabel jLabel13 = null;
	private JTextField _txt_iva = null;
	private JLabel jLabel14 = null;
	private JTextField _txt_total = null;
	private JCheckBox _chk_costo = null;
	private JCheckBox _chk_sobrescribir = null;
	private JTable jTable1 = null;
	private JButton _btn_error = null;
	private JLabel jLabel15 = null;
	private JLabel jLabel16 = null;
	private JLabel jLabel17 = null;
	private JTextField _txt_fecha_envio = null;
	private JButton _btn_buscar_fecha_envio = null;
	private JTextField _txt_guia = null;
	private JTextField _txt_idtransporte = null;
	private JTextField _txt_transporte_descripcion = null;
	private JButton _btn_buscar_transporte = null;
	private JPanel General = null;  //  @jve:decl-index=0:visual-constraint="11,674"
	private LockableUI lockableUI=null;  //  @jve:decl-index=0:
	private JButton _btn_play = null;
	private JCheckBox _chk_seguimiento = null;
	private JTabbedPane jTabbedPane = null;
	private JPanel jPanel2 = null;
	private JLabel jLabel4 = null;
	private JTextField _txt_domicilio = null;
	private JLabel jLabel7 = null;
	private JTextField _txt_idprovincia = null;
	private JLabel jLabel8 = null;
	private JTextField _txt_cpostal = null;
	private JLabel jLabel9 = null;
	private JTextField _txt_tel = null;
	private JTextField _txt_provincia_descripcion = null;
	private JLabel jLabel18 = null;
	private JTextField _txt_idciudad = null;
	private JButton _btn_envio = null;
	private JButton _btn_eliminar = null;
	private JTextField _txt_articulo_actualizacion = null;
	private JButton _btn_identificador = null;
	private JButton _btn_nuevo = null;
	private JPanel jPanel4 = null;
	private JScrollPane jScrollPane2 = null;
	private JTable jTable11 = null;
	private JLabel jLabel21 = null;
	private JTextField _txt_pdc_idpedido = null;
	private JTextField _txt_pdc_descripcion = null;
	private JTextField _txt_pdc_vendedor = null;
	private JScrollPane jScrollPane3 = null;
	private JTextArea _txt_pdc_informacion = null;
	private JButton _btn_pdc_editar = null;
	private JTabbedPane jTabbedPane1 = null;
	private JPanel jPanel3 = null;
	private JPanel jPanel5 = null;
	private JScrollPane jScrollPane4 = null;
	private JTable jTable12 = null;
	private JButton _btn_importar = null;
	private JToolBar jToolBar1 = null;
	private JPanel jPanel6 = null;
	private JToolBar jToolBar2 = null;
	private JScrollPane jScrollPane5 = null;
	private JTable jTable_critico = null;
	private JButton _btn_importar_critico = null;
	private JButton _btn_critico_cargar = null;
	private JLabel jLabel10 = null;
	private JTextField _txt_fecha_desde = null;
	private JButton _btn_fecha_desde = null;
	private JLabel jLabel22 = null;
	private JTextField _txt_fecha_hasta = null;
	private JButton _btn_fecha_hasta = null;
	private JComboBox _lst_estado = null;
	private JLabel jLabel23 = null;
	private JTextField _txt_articulo_linea = null;
	private JTextField _txt_articulo_bloqueado = null;
	private JLabel jLabel24 = null;
	private JScrollPane jScrollPane6 = null;
	private JTextArea _txt_observacion = null;
	private JLabel jLabel25 = null;
	private JLabel jLabel26 = null;
	private JLabel jLabel27 = null;
	private JTextField _txt_idcreador = null;
	private JTextField _txt_creador = null;
	private JPanel jPanel_faltantes = null;
	private JToolBar jToolBar3 = null;
	private JButton _btn_importar_faltante = null;
	private JButton _btn_cargar_faltantes = null;
	private JTextField _txt_fecha_desde_faltante = null;
	private JTextField _txt_fecha_hasta_faltante = null;
	private JCheckBox _chk_mostrar_faltantes_proveedor = null;
	private JButton _btn_eliminar_faltantes = null;
	private JButton _btn_buscar_fecha_desde_faltante = null;
	private JButton _btn_buscar_fecha_hasta_faltante = null;
	private JLabel jLabel28 = null;
	private JScrollPane jScrollPane7 = null;
	private JTable jTable_faltantes = null;
	private JPanel jPanel_info = null;
	private JCheckBox _chk_mostrar_eliminados = null;
	private JLabel jLabel30 = null;
	private JTextField _txt_linea = null;
	private JCheckBox _chk_full_critico = null;
	private JLabel jLabel19 = null;
	private JTextField _txt_linea_faltantes = null;
	private JScrollPane jScrollPane8 = null;
	private JTable jTable_equivalencias = null;
	private JScrollPane jScrollPane9 = null;
	private JTable jTable_equivalencias_faltantes = null;
	private JScrollPane jScrollPane10 = null;
	private JTable jTable_equivalencias_critico = null;
	private JLabel jLabel20 = null;
	private JTextField _txt_email = null;
	private JTextField _txt_fax = null;
	private JLabel jLabel31 = null;
	private JButton _btn_editar_transporte = null;
	private JTextField _txt_idarticuo_faltantes = null;
	private JTextField _txt_descripcion_faltantes = null;
	private JTextField txt_vendedor_faltantes = null;
	private JLabel jLabel32 = null;
	private JLabel jLabel33 = null;
	private JLabel jLabel34 = null;
	private JComboBox _lst_faltantes = null;
	private JLabel jLabel29 = null;
	private JSplitPane jSplitPane = null;
	private JLabel jLabel35 = null;
	private JComboBox _lst_categoria = null;
	private JCheckBox _chk_seleccion_criticos = null;
	private JComboBox _lst_categoria_faltantes = null;
	private JLabel jLabel36 = null;
	private JCheckBox _chk_seleccionar_faltantes = null;
	private JComboBox _lst_stock = null;
	private JLabel jLabel37 = null;
	private JComboBox _lst_modo = null;
	private JComboBox _lst_modo_faltantes = null;
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
		this.setSize(911, 730);
		this.setContentPane(getJContentPane());
		this.setTitle("Pedido Especial a Proveedor");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			
			jLabel17 = new JLabel();
			jLabel17.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel17.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel17.setBounds(new Rectangle(399, 101, 71, 20));
			jLabel17.setText("Guia");
			jLabel16 = new JLabel();
			jLabel16.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel16.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel16.setBounds(new Rectangle(397, 3, 69, 17));
			jLabel16.setText("Fecha Envio");
			jLabel15 = new JLabel();
			jLabel15.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel15.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel15.setBounds(new Rectangle(4, 5, 68, 16));
			jLabel15.setText("Transporte");
			jLabel6 = new JLabel();
			jLabel6.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel6.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel6.setBounds(new Rectangle(126, 344, 66, 17));
			jLabel6.setText("idVendedor");
			jLabel5 = new JLabel();
			jLabel5.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel5.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel5.setBounds(new Rectangle(11, 40, 55, 22));
			jLabel5.setText("Modificado");
			jLabel3 = new JLabel();
			jLabel3.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3.setBounds(new Rectangle(11, 15, 54, 19));
			jLabel3.setText("Creado");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(668, 31, 49, 19));
			jLabel2.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2.setText("Fecha");
			jLabel1 = new JLabel();
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setBounds(new Rectangle(3, 9, 66, 19));
			jLabel1.setText("Proveedor");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(3, 29, 88, 18));
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setText("idpedido");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(get_txt_idpedido(), null);
			jContentPane.add(get_btn_buscar_pedido(), null);
			jContentPane.add(get_btn_cargar_pedido(), null);
			jContentPane.add(get_txt_pedido_descripcion(), null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(get_txt_fecha(), null);
			jContentPane.add(get_btn_buscar_fecha(), null);
			//jContentPane.add(this.getJXLayerUI(), null);
			jContentPane.add(getGeneral());
			//jContentPane.add(getJPanel4(), null);
			
			
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
			jToolBar.setBounds(new Rectangle(1, 2, 900, 24));
			jToolBar.setFloatable(false);
			jToolBar.setBackground(new Color(51, 51, 255));
			jToolBar.add(get_btn_nuevo());
			jToolBar.add(get_btn_cancelar());
			jToolBar.add(get_btn_guardar());
			jToolBar.add(get_btn_preparar());
			jToolBar.add(get_btn_presupuesto());
			jToolBar.add(get_btn_remito());
			jToolBar.add(get_btn_envio());
			jToolBar.add(get_btn_identificador());
			jToolBar.add(get_btn_eliminar());
			jToolBar.add(get_btn_salir());
			jToolBar.addSeparator();
			jToolBar.add(get_btn_error());
			jToolBar.add(get_btn_play());
			jToolBar.addSeparator();
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
			_btn_cancelar.setToolTipText("Cancelar");
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
			_btn_guardar.setToolTipText("Guardar Cambios");
			_btn_guardar.setEnabled(false);
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
			_btn_salir.setToolTipText("Cierra Aplicacion");
		}
		return _btn_salir;
	}

	/**
	 * This method initializes _txt_idpedido	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idpedido() {
		if (_txt_idpedido == null) {
			_txt_idpedido = new JTextField();
			_txt_idpedido.setBounds(new Rectangle(100, 30, 92, 18));
			_txt_idpedido.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_idpedido;
	}

	/**
	 * This method initializes _btn_buscar_pedido	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_pedido() {
		if (_btn_buscar_pedido == null) {
			_btn_buscar_pedido = new JButton();
			_btn_buscar_pedido.setBounds(new Rectangle(195, 30, 22, 19));
			_btn_buscar_pedido.setToolTipText("Buscar Pedido");
			_btn_buscar_pedido.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-searchtool.png")));
		}
		return _btn_buscar_pedido;
	}

	/**
	 * This method initializes _btn_cargar_pedido	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cargar_pedido() {
		if (_btn_cargar_pedido == null) {
			_btn_cargar_pedido = new JButton();
			_btn_cargar_pedido.setBounds(new Rectangle(219, 31, 21, 19));
			_btn_cargar_pedido.setToolTipText("Cargar Pedido");
			_btn_cargar_pedido.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-refresh.png")));
		}
		return _btn_cargar_pedido;
	}

	/**
	 * This method initializes _txt_pedido_descripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_pedido_descripcion() {
		if (_txt_pedido_descripcion == null) {
			_txt_pedido_descripcion = new JTextField();
			_txt_pedido_descripcion.setBounds(new Rectangle(243, 31, 385, 18));
			_txt_pedido_descripcion.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_pedido_descripcion.setToolTipText("Descripcion del pedido");
		}
		return _txt_pedido_descripcion;
	}

	/**
	 * This method initializes _txt_idcliente	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idcliente() {
		if (_txt_idcliente == null) {
			_txt_idcliente = new JTextField();
			_txt_idcliente.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_idcliente.setToolTipText("Codigo Proveedor");
			_txt_idcliente.setBounds(new Rectangle(71, 9, 93, 18));
		}
		return _txt_idcliente;
	}

	/**
	 * This method initializes _btn_buscar_cliente	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_cliente() {
		if (_btn_buscar_cliente == null) {
			_btn_buscar_cliente = new JButton();
			_btn_buscar_cliente.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-searchtool.png")));
			_btn_buscar_cliente.setToolTipText("Buscar Proveedor");
			_btn_buscar_cliente.setBounds(new Rectangle(166, 9, 21, 19));
		}
		return _btn_buscar_cliente;
	}

	/**
	 * This method initializes _btn_cargar_cliente	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cargar_cliente() {
		if (_btn_cargar_cliente == null) {
			_btn_cargar_cliente = new JButton();
			_btn_cargar_cliente.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-refresh.png")));
			_btn_cargar_cliente.setToolTipText("Cargar Proveedor");
			_btn_cargar_cliente.setBounds(new Rectangle(190, 9, 21, 19));
		}
		return _btn_cargar_cliente;
	}

	/**
	 * This method initializes _txt_cliente_descripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_cliente_descripcion() {
		if (_txt_cliente_descripcion == null) {
			_txt_cliente_descripcion = new JTextField();
			_txt_cliente_descripcion.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_cliente_descripcion.setBounds(new Rectangle(239, 9, 333, 19));
			_txt_cliente_descripcion.setToolTipText("Descripcion del Cliente");
		}
		return _txt_cliente_descripcion;
	}

	/**
	 * This method initializes _txt_fecha	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_fecha() {
		if (_txt_fecha == null) {
			_txt_fecha = new JTextField();
			_txt_fecha.setBounds(new Rectangle(725, 30, 135, 18));
			_txt_fecha.setHorizontalAlignment(JTextField.RIGHT);
			_txt_fecha.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_fecha.setToolTipText("Fecha De Agenda");
		}
		return _txt_fecha;
	}

	/**
	 * This method initializes _btn_buscar_fecha	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_fecha() {
		if (_btn_buscar_fecha == null) {
			_btn_buscar_fecha = new JButton();
			_btn_buscar_fecha.setBounds(new Rectangle(864, 29, 22, 21));
			_btn_buscar_fecha.setToolTipText("Mostrar Calendario");
			_btn_buscar_fecha.setIcon(new ImageIcon(getClass().getResource("/icons/calendar-16.gif")));
		}
		return _btn_buscar_fecha;
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
			_txt_fecha_creacion.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_fecha_creacion.setToolTipText("Fecha de Creacion");
			_txt_fecha_creacion.setBounds(new Rectangle(70, 17, 130, 17));
			_txt_fecha_creacion.setHorizontalAlignment(JTextField.RIGHT);
		}
		return _txt_fecha_creacion;
	}

	/**
	 * This method initializes _btn_preparar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_preparar() {
		if (_btn_preparar == null) {
			_btn_preparar = new JButton();
			_btn_preparar.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-other-24.png")));
			_btn_preparar.setEnabled(false);
			_btn_preparar.setToolTipText("Preparar Pedido");
		}
		return _btn_preparar;
	}

	/**
	 * This method initializes _btn_presupuesto	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_presupuesto() {
		if (_btn_presupuesto == null) {
			_btn_presupuesto = new JButton();
			_btn_presupuesto.setIcon(new ImageIcon(getClass().getResource("/icons/document-print.png")));
			_btn_presupuesto.setEnabled(false);
			_btn_presupuesto.setToolTipText("Imprimir Pedido a Proveedor");
		}
		return _btn_presupuesto;
	}

	/**
	 * This method initializes _btn_remito	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_remito() {
		if (_btn_remito == null) {
			_btn_remito = new JButton();
			_btn_remito.setToolTipText("Enviar Pedido por Email");
			_btn_remito.setEnabled(false);
			_btn_remito.setIcon(new ImageIcon(getClass().getResource("/icons/mail-forward.png")));
		}
		return _btn_remito;
	}

	/**
	 * This method initializes _btn_editar_cliente	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_editar_cliente() {
		if (_btn_editar_cliente == null) {
			_btn_editar_cliente = new JButton();
			_btn_editar_cliente.setToolTipText("Editar Proveedor");
			_btn_editar_cliente.setBounds(new Rectangle(214, 9, 21, 19));
			_btn_editar_cliente.setIcon(new ImageIcon(getClass().getResource("/icons/edit-find-replace.png")));
		}
		return _btn_editar_cliente;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(86, 128, 563, 77));
			jScrollPane.setViewportView(get_txt_informacion());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes _txt_informacion	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea get_txt_informacion() {
		if (_txt_informacion == null) {
			_txt_informacion = new JTextArea();
			_txt_informacion.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_informacion.setToolTipText("Ingrese aqui la informacion relacionada con el pedido de compra");
		}
		return _txt_informacion;
	}

	/**
	 * This method initializes _txt_modificado	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_modificado() {
		if (_txt_modificado == null) {
			_txt_modificado = new JTextField();
			_txt_modificado.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_modificado.setHorizontalAlignment(JTextField.RIGHT);
			_txt_modificado.setBounds(new Rectangle(70, 43, 130, 19));
			_txt_modificado.setEditable(false);
		}
		return _txt_modificado;
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
			_txt_idvendedor.setBounds(new Rectangle(196, 344, 96, 18));
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
			_txt_vendedor_descripcion.setBounds(new Rectangle(321, 343, 153, 18));
			_txt_vendedor_descripcion.setEditable(false);
		}
		return _txt_vendedor_descripcion;
	}

	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setBounds(new Rectangle(6, 365, 880, 126));
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
	 * This method initializes _btn_buscar_vendedor	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_vendedor() {
		if (_btn_buscar_vendedor == null) {
			_btn_buscar_vendedor = new JButton();
			_btn_buscar_vendedor.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-searchtool.png")));
			_btn_buscar_vendedor.setToolTipText("Buscar Vendedor");
			_btn_buscar_vendedor.setBounds(new Rectangle(295, 342, 21, 19));
		}
		return _btn_buscar_vendedor;
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
			_txt_articulo.setBounds(new Rectangle(5, 495, 130, 18));
			_txt_articulo.setFont(new Font("Dialog", Font.PLAIN, 10));
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
			_txt_articulo_descripcion.setEditable(false);
			_txt_articulo_descripcion.setBounds(new Rectangle(139, 494, 266, 18));
			_txt_articulo_descripcion.setFont(new Font("Dialog", Font.PLAIN, 10));
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
			_txt_articulo_stock.setBounds(new Rectangle(411, 494, 76, 18));
			_txt_articulo_stock.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_articulo_stock.setToolTipText("Stock");
		}
		return _txt_articulo_stock;
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
			_chk_seleccionar.setBounds(new Rectangle(7, 348, 90, 15));
			_chk_seleccionar.setText("Seleccionar");
		}
		return _chk_seleccionar;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabel14 = new JLabel();
			jLabel14.setBounds(new Rectangle(728, 8, 36, 21));
			jLabel14.setText("Total");
			jLabel14.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel14.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel13 = new JLabel();
			jLabel13.setBounds(new Rectangle(591, 8, 34, 20));
			jLabel13.setText("IVA");
			jLabel13.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel13.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel12 = new JLabel();
			jLabel12.setBounds(new Rectangle(393, 7, 98, 20));
			jLabel12.setText("Subtotal");
			jLabel12.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel12.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel11 = new JLabel();
			jLabel11.setBounds(new Rectangle(39, 7, 51, 19));
			jLabel11.setText("Items");
			jLabel11.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel11.setHorizontalAlignment(SwingConstants.RIGHT);
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBackground(Color.gray);
			jPanel.setBounds(new Rectangle(3, 589, 884, 31));
			jPanel.add(jLabel11, null);
			jPanel.add(get_txt_items(), null);
			jPanel.add(jLabel12, null);
			jPanel.add(get_txt_subtotal(), null);
			jPanel.add(jLabel13, null);
			jPanel.add(get_txt_iva(), null);
			jPanel.add(jLabel14, null);
			jPanel.add(get_txt_total(), null);
			jPanel.add(get_chk_costo(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes _txt_items	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_items() {
		if (_txt_items == null) {
			_txt_items = new JTextField();
			_txt_items.setBounds(new Rectangle(96, 8, 73, 18));
			_txt_items.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_items.setEditable(false);
			_txt_items.setHorizontalAlignment(JTextField.RIGHT);
		}
		return _txt_items;
	}

	/**
	 * This method initializes _txt_subtotal	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_subtotal() {
		if (_txt_subtotal == null) {
			_txt_subtotal = new JTextField();
			_txt_subtotal.setBounds(new Rectangle(497, 8, 92, 18));
			_txt_subtotal.setEditable(false);
			_txt_subtotal.setHorizontalAlignment(JTextField.RIGHT);
			_txt_subtotal.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_subtotal;
	}

	/**
	 * This method initializes _txt_iva	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_iva() {
		if (_txt_iva == null) {
			_txt_iva = new JTextField();
			_txt_iva.setBounds(new Rectangle(633, 8, 94, 18));
			_txt_iva.setEditable(false);
			_txt_iva.setHorizontalAlignment(JTextField.RIGHT);
			_txt_iva.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_iva;
	}

	/**
	 * This method initializes _txt_total	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_total() {
		if (_txt_total == null) {
			_txt_total = new JTextField();
			_txt_total.setBounds(new Rectangle(771, 8, 100, 18));
			_txt_total.setEditable(false);
			_txt_total.setHorizontalAlignment(JTextField.RIGHT);
			_txt_total.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_total;
	}

	/**
	 * This method initializes _chk_costo	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_costo() {
		if (_chk_costo == null) {
			_chk_costo = new JCheckBox();
			_chk_costo.setBounds(new Rectangle(4, 7, 21, 19));
		}
		return _chk_costo;
	}

	/**
	 * This method initializes _chk_sobrescribir	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_sobrescribir() {
		if (_chk_sobrescribir == null) {
			_chk_sobrescribir = new JCheckBox();
			_chk_sobrescribir.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_sobrescribir.setBounds(new Rectangle(488, 345, 113, 15));
			_chk_sobrescribir.setText("Sobrescribir");
		}
		return _chk_sobrescribir;
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
	 * This method initializes _txt_fecha_envio	
	 * 	
	 * @return javax.swing.JTextField	
	 */
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
	 * This method initializes _txt_idtransporte	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idtransporte() {
		if (_txt_idtransporte == null) {
			_txt_idtransporte = new JTextField();
			_txt_idtransporte.setHorizontalAlignment(JTextField.RIGHT);
			_txt_idtransporte.setBounds(new Rectangle(81, 4, 62, 17));
			_txt_idtransporte.setToolTipText("Codigo de Transporte");
		}
		return _txt_idtransporte;
	}

	/**
	 * This method initializes _txt_transporte_descripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
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
	 * This method initializes General	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getGeneral() {
		if (General == null) {
			jLabel23 = new JLabel();
			jLabel23.setBounds(new Rectangle(676, 10, 40, 16));
			jLabel23.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel23.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel23.setText("Estado");
			General = new JPanel();
			General.setLayout(null);
			General.setBounds(new Rectangle(3, 61, 894, 625));
			//General.setBounds(new Rectangle(3, 80, 894, 520));
			General.add(jLabel1, null);
			General.add(get_txt_idcliente(), null);
			General.add(get_btn_buscar_cliente(), null);
			General.add(get_btn_cargar_cliente(), null);
			General.add(get_btn_editar_cliente(), null);
			General.add(get_txt_cliente_descripcion(), null);
			General.add(jLabel6, null);
			General.add(get_txt_idvendedor(), null);
			General.add(get_btn_buscar_vendedor(), null);
			General.add(get_txt_vendedor_descripcion(), null);
			General.add(get_chk_sobrescribir(), null);
			General.add(get_chk_seleccionar(), null);
			General.add(get_txt_articulo(), null);
			General.add(get_txt_articulo_descripcion(), null);
			General.add(get_txt_articulo_stock(), null);
			General.add(getJPanel(), null);
			General.add(getJScrollPane1(), null);
			General.add(getJTabbedPane(), null);
			General.add(get_txt_articulo_actualizacion(), null);
			General.add(get_lst_estado(), null);
			General.add(get_chk_seguimiento(),null);
			General.add(jLabel23, null);
			General.add(get_txt_articulo_linea(), null);
			General.add(get_txt_articulo_bloqueado(), null);
			General.add(getJScrollPane8(), null);
		}
		return General;
	}
	
	public JXLayer<JComponent> getJXLayerUI(){
		JPanel panel = this.getGeneral();
		JXLayer<JComponent> layer = new JXLayer<JComponent>(panel, this.getLockableUI());
		layer.setBounds(new Rectangle(4, 50, 897, 550));
		this.getLockableUI().setLocked(true);
		return layer;
		
	}
	
	public LockableUI getLockableUI(){
		if (lockableUI==null){
			lockableUI= new LockableUI();        
			BoxBlurFilter blur=new BoxBlurFilter(1,1,3);
			BufferedImageOpEffect effect = new BufferedImageOpEffect(blur);
			lockableUI.setLockedEffects(effect);
			
			
			lockableUI.setLocked(true);
		}
		return lockableUI; 

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
			_btn_play.setToolTipText("Restaurar Informacion desde XML");
		}
		return _btn_play;
	}

	/**
	 * This method initializes _chk_seguimiento	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_seguimiento() {
		if (_chk_seguimiento == null) {
			_chk_seguimiento = new JCheckBox();
			_chk_seguimiento.setToolTipText("Permite que la fecha de agenda del pedido se vaya actualizando con los dias");
			_chk_seguimiento.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_seguimiento.setBounds(new Rectangle(576, 11, 95, 16));
			_chk_seguimiento.setText("seguimiento");
		}
		return _chk_seguimiento;
	}

	/**
	 * This method initializes jTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	public JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.setBounds(new Rectangle(4, 31, 879, 308));
			jTabbedPane.setFont(new Font("Dialog", Font.BOLD, 10));
			jTabbedPane.addTab("Envio/Direccion", null, getJPanel2(), null);
			jTabbedPane.addTab("Pedidos de Cliente", null, getJPanel4(), null);
			jTabbedPane.addTab("Pedido Critico", null, getJPanel6(), null);
			jTabbedPane.addTab("Faltantes", null, getJPanel_faltantes(), null);
			jTabbedPane.addTab("Propiedades", null, getJPanel_info(), null);
		}
		return jTabbedPane;
	}

	/**
	 * This method initializes jPanel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jLabel31 = new JLabel();
			jLabel31.setBounds(new Rectangle(365, 75, 39, 18));
			jLabel31.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel31.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel31.setText("Fax:");
			jLabel20 = new JLabel();
			jLabel20.setBounds(new Rectangle(21, 102, 57, 16));
			jLabel20.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel20.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel20.setText("email");
			jLabel27 = new JLabel();
			jLabel27.setBounds(new Rectangle(3, 231, 79, 15));
			jLabel27.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel27.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel27.setText("Para Proveedor");
			jLabel26 = new JLabel();
			jLabel26.setBounds(new Rectangle(3, 145, 82, 17));
			jLabel26.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel26.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel26.setText("del comprador");
			jLabel25 = new JLabel();
			jLabel25.setBounds(new Rectangle(15, 213, 65, 16));
			jLabel25.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel25.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel25.setText("Observacion");
			jLabel24 = new JLabel();
			jLabel24.setBounds(new Rectangle(3, 128, 80, 17));
			jLabel24.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel24.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel24.setText("Informacion");
			jLabel18 = new JLabel();
			jLabel18.setBounds(new Rectangle(407, 26, 64, 19));
			jLabel18.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel18.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel18.setText("ciudad");
			jLabel9 = new JLabel();
			jLabel9.setBounds(new Rectangle(1, 75, 72, 17));
			jLabel9.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel9.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel9.setText("Tel");
			jLabel8 = new JLabel();
			jLabel8.setBounds(new Rectangle(398, 48, 71, 18));
			jLabel8.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel8.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel8.setText("Codigo Postal");
			jLabel7 = new JLabel();
			jLabel7.setBounds(new Rectangle(6, 26, 65, 19));
			jLabel7.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel7.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel7.setText("Provincia");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(7, 48, 66, 17));
			jLabel4.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel4.setText("Domicilio");
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
	 * This method initializes _txt_domicilio	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_domicilio() {
		if (_txt_domicilio == null) {
			_txt_domicilio = new JTextField();
			_txt_domicilio.setBounds(new Rectangle(82, 47, 309, 19));
		}
		return _txt_domicilio;
	}

	/**
	 * This method initializes _txt_idprovincia	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idprovincia() {
		if (_txt_idprovincia == null) {
			_txt_idprovincia = new JTextField();
			_txt_idprovincia.setBounds(new Rectangle(81, 27, 111, 18));
		}
		return _txt_idprovincia;
	}

	/**
	 * This method initializes _txt_cpostal	
	 * 	
	 * @return javax.swing.JTextField	
	 */
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
	public JTextField get_txt_tel() {
		if (_txt_tel == null) {
			_txt_tel = new JTextField();
			_txt_tel.setBounds(new Rectangle(82, 74, 264, 19));
		}
		return _txt_tel;
	}

	/**
	 * This method initializes _txt_provincia_descripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_provincia_descripcion() {
		if (_txt_provincia_descripcion == null) {
			_txt_provincia_descripcion = new JTextField();
			_txt_provincia_descripcion.setBounds(new Rectangle(195, 26, 196, 18));
			_txt_provincia_descripcion.setEditable(false);
		}
		return _txt_provincia_descripcion;
	}

	/**
	 * This method initializes _txt_idciudad	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idciudad() {
		if (_txt_idciudad == null) {
			_txt_idciudad = new JTextField();
			_txt_idciudad.setBounds(new Rectangle(480, 25, 167, 19));
		}
		return _txt_idciudad;
	}

	/**
	 * This method initializes _btn_envio	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_envio() {
		if (_btn_envio == null) {
			_btn_envio = new JButton();
			_btn_envio.setIcon(new ImageIcon(getClass().getResource("/icons/emblem-package-24.png")));
			_btn_envio.setEnabled(false);
			_btn_envio.setToolTipText("Enviar Pedido (Rotulo)");
		}
		return _btn_envio;
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
			_btn_eliminar.setToolTipText("Eliminar Pedido");
		}
		return _btn_eliminar;
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
			_txt_articulo_actualizacion.setLocation(new Point(491, 494));
			_txt_articulo_actualizacion.setSize(new Dimension(90, 18));
			_txt_articulo_actualizacion.setToolTipText("Indica La ultima Fecha de Actualizacion");
			_txt_articulo_actualizacion.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_articulo_actualizacion;
	}

	/**
	 * This method initializes _btn_identificador	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_identificador() {
		if (_btn_identificador == null) {
			_btn_identificador = new JButton();
			_btn_identificador.setIcon(new ImageIcon(getClass().getResource("/icons/emblem-sales-24.png")));
			_btn_identificador.setEnabled(false);
			_btn_identificador.setToolTipText("Imprimir Identificador");
		}
		return _btn_identificador;
	}

	/**
	 * This method initializes _btn_nuevo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_nuevo() {
		if (_btn_nuevo == null) {
			_btn_nuevo = new JButton();
			_btn_nuevo.setIcon(new ImageIcon(getClass().getResource("/icons/document-new.png")));
			_btn_nuevo.setToolTipText("Nuevo Pedido");
		}
		return _btn_nuevo;
	}

	/**
	 * This method initializes jPanel4	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel4() {
		if (jPanel4 == null) {
			jLabel21 = new JLabel();
			jLabel21.setBounds(new Rectangle(9, 104, 49, 16));
			jLabel21.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel21.setText("idPedido");
			jPanel4 = new JPanel();
			jPanel4.setLayout(null);
			jPanel4.setBounds(new Rectangle(275, 89, 797, 232));
			jPanel4.setBackground(new Color(102, 153, 255));
			jPanel4.add(jLabel21, null);
			jPanel4.add(get_txt_pdc_idpedido(), null);
			jPanel4.add(get_txt_pdc_descripcion(), null);
			jPanel4.add(get_txt_pdc_vendedor(), null);
			jPanel4.add(get_btn_pdc_editar(), null);
			jPanel4.add(getJScrollPane2(), null);
			jPanel4.add(getJTabbedPane1(), null);
		}
		return jPanel4;
	}

	/**
	 * This method initializes jScrollPane2	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane2() {
		if (jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setBounds(new Rectangle(6, 6, 855, 94));
			jScrollPane2.setViewportView(getJTable11());
		}
		return jScrollPane2;
	}

	/**
	 * This method initializes jTable11	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getJTable11() {
		if (jTable11 == null) {
			jTable11 = new JTable();
		}
		return jTable11;
	}
	public void setJTable11(JTable table){
		this.jTable11=table;
		this.jScrollPane2.setViewportView(jTable11);
	}
	/**
	 * This method initializes _txt_pdc_idpedido	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_pdc_idpedido() {
		if (_txt_pdc_idpedido == null) {
			_txt_pdc_idpedido = new JTextField();
			_txt_pdc_idpedido.setLocation(new Point(61, 103));
			_txt_pdc_idpedido.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_pdc_idpedido.setEditable(false);
			_txt_pdc_idpedido.setSize(new Dimension(92, 16));
		}
		return _txt_pdc_idpedido;
	}

	/**
	 * This method initializes _txt_pdc_descripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_pdc_descripcion() {
		if (_txt_pdc_descripcion == null) {
			_txt_pdc_descripcion = new JTextField();
			_txt_pdc_descripcion.setLocation(new Point(179, 104));
			_txt_pdc_descripcion.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_pdc_descripcion.setEditable(false);
			_txt_pdc_descripcion.setSize(new Dimension(343, 16));
		}
		return _txt_pdc_descripcion;
	}

	/**
	 * This method initializes _txt_pdc_vendedor	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_pdc_vendedor() {
		if (_txt_pdc_vendedor == null) {
			_txt_pdc_vendedor = new JTextField();
			_txt_pdc_vendedor.setBounds(new Rectangle(525, 104, 176, 15));
			_txt_pdc_vendedor.setEditable(false);
			_txt_pdc_vendedor.setHorizontalAlignment(JTextField.LEFT);
			_txt_pdc_vendedor.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_pdc_vendedor;
	}

	/**
	 * This method initializes jScrollPane3	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane3() {
		if (jScrollPane3 == null) {
			jScrollPane3 = new JScrollPane();
			jScrollPane3.setBounds(new Rectangle(4, 4, 697, 105));
			jScrollPane3.setViewportView(get_txt_pdc_informacion());
		}
		return jScrollPane3;
	}

	/**
	 * This method initializes _txt_pdc_informacion	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea get_txt_pdc_informacion() {
		if (_txt_pdc_informacion == null) {
			_txt_pdc_informacion = new JTextArea();
			_txt_pdc_informacion.setLineWrap(true);
			_txt_pdc_informacion.setEditable(false);
			_txt_pdc_informacion.setWrapStyleWord(true);
		}
		return _txt_pdc_informacion;
	}

	/**
	 * This method initializes _btn_pdc_editar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_pdc_editar() {
		if (_btn_pdc_editar == null) {
			_btn_pdc_editar = new JButton();
			_btn_pdc_editar.setBounds(new Rectangle(156, 103, 20, 17));
			_btn_pdc_editar.setIcon(new ImageIcon(getClass().getResource("/icons/edit-find-replace.png")));
		}
		return _btn_pdc_editar;
	}

	/**
	 * This method initializes jTabbedPane1	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJTabbedPane1() {
		if (jTabbedPane1 == null) {
			jTabbedPane1 = new JTabbedPane();
			jTabbedPane1.setBounds(new Rectangle(2, 121, 855, 142));
			jTabbedPane1.setFont(new Font("Dialog", Font.BOLD, 10));
			jTabbedPane1.addTab("Informacion", null, getJPanel3(), null);
			jTabbedPane1.addTab("Articulos", null, getJPanel5(), null);
		}
		return jTabbedPane1;
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
			jPanel3.add(getJScrollPane3(), null);
		}
		return jPanel3;
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
			jPanel5.add(getJToolBar1(), null);
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
			jScrollPane4.setBounds(new Rectangle(4, 22, 836, 90));
			jScrollPane4.setViewportView(getJTable12());
		}
		return jScrollPane4;
	}

	/**
	 * This method initializes jTable12	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getJTable12() {
		if (jTable12 == null) {
			jTable12 = new JTable();
		}
		return jTable12;
	}
	
	public void setJTable12(JTable table){
		this.jTable12=table;
		this.jScrollPane4.setViewportView(jTable12);
	}

	/**
	 * This method initializes _btn_importar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_importar() {
		if (_btn_importar == null) {
			_btn_importar = new JButton();
			_btn_importar.setIcon(new ImageIcon(getClass().getResource("/icons/bottom.png")));
			_btn_importar.setToolTipText("Utilizar Los Articulos Seleccionados");
		}
		return _btn_importar;
	}

	/**
	 * This method initializes jToolBar1	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar1() {
		if (jToolBar1 == null) {
			jToolBar1 = new JToolBar();
			jToolBar1.setBounds(new Rectangle(5, 2, 833, 21));
			jToolBar1.setFloatable(false);
			jToolBar1.add(get_btn_importar());
		}
		return jToolBar1;
	}

	/**
	 * This method initializes jPanel6	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel6() {
		if (jPanel6 == null) {
			jLabel37 = new JLabel();
			jLabel37.setBounds(new Rectangle(519, 60, 39, 15));
			jLabel37.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel37.setText("Stock");
			jLabel35 = new JLabel();
			jLabel35.setBounds(new Rectangle(386, 59, 53, 15));
			jLabel35.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel35.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel35.setText("Categoria");
			jLabel30 = new JLabel();
			jLabel30.setBounds(new Rectangle(29, 39, 48, 15));
			jLabel30.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel30.setText("Linea");
			jLabel22 = new JLabel();
			jLabel22.setBounds(new Rectangle(666, 28, 52, 15));
			jLabel22.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel22.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel22.setText("Hasta");
			jLabel10 = new JLabel();
			jLabel10.setBounds(new Rectangle(455, 29, 66, 13));
			jLabel10.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel10.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel10.setText("Desde");
			jPanel6 = new JPanel();
			jPanel6.setLayout(null);
			jPanel6.setBackground(new Color(153, 255, 153));
			jPanel6.add(getJToolBar2(), null);
			jPanel6.add(getJScrollPane5(), null);
			jPanel6.add(jLabel10, null);
			jPanel6.add(get_txt_fecha_desde(), null);
			jPanel6.add(get_btn_fecha_desde(), null);
			jPanel6.add(jLabel22, null);
			jPanel6.add(get_txt_fecha_hasta(), null);
			jPanel6.add(get_btn_fecha_hasta(), null);
			jPanel6.add(jLabel30, null);
			jPanel6.add(get_txt_linea(), null);
			jPanel6.add(get_chk_full_critico(), null);
			jPanel6.add(getJScrollPane10(), null);
			jPanel6.add(jLabel35, null);
			jPanel6.add(get_lst_categoria(), null);
			jPanel6.add(get_chk_seleccion_criticos(), null);
			jPanel6.add(get_lst_stock(), null);
			jPanel6.add(jLabel37, null);
			jPanel6.add(get_lst_modo(), null);
		}
		return jPanel6;
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
			jToolBar2.setSize(new Dimension(868, 21));
			jToolBar2.setLocation(new Point(0, 0));
			jToolBar2.add(get_btn_importar_critico());
			jToolBar2.add(get_btn_critico_cargar());
		}
		return jToolBar2;
	}

	/**
	 * This method initializes jScrollPane5	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane5() {
		if (jScrollPane5 == null) {
			jScrollPane5 = new JScrollPane();
			jScrollPane5.setBounds(new Rectangle(5, 78, 856, 118));
			jScrollPane5.setViewportView(getJTable_critico());
		}
		return jScrollPane5;
	}

	/**
	 * This method initializes jTable_critico	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getJTable_critico() {
		if (jTable_critico == null) {
			jTable_critico = new JTable();
		}
		return jTable_critico;
	}

	public void setJTableCritico(JTable table){
		this.jTable_critico=table;
		this.jScrollPane5.setViewportView(table);
	}
	/**
	 * This method initializes _btn_importar_critico	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_importar_critico() {
		if (_btn_importar_critico == null) {
			_btn_importar_critico = new JButton();
			_btn_importar_critico.setIcon(new ImageIcon(getClass().getResource("/icons/bottom.png")));
			_btn_importar_critico.setToolTipText("Importar Items al Pedido");
		}
		return _btn_importar_critico;
	}

	/**
	 * This method initializes _btn_critico_cargar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_critico_cargar() {
		if (_btn_critico_cargar == null) {
			_btn_critico_cargar = new JButton();
			_btn_critico_cargar.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-refresh.png")));
			_btn_critico_cargar.setToolTipText("Cargar Ultimas Ventas");
		}
		return _btn_critico_cargar;
	}

	/**
	 * This method initializes _txt_fecha_desde	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_fecha_desde() {
		if (_txt_fecha_desde == null) {
			_txt_fecha_desde = new JTextField();
			_txt_fecha_desde.setBounds(new Rectangle(526, 27, 104, 16));
			_txt_fecha_desde.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_fecha_desde;
	}

	/**
	 * This method initializes _btn_fecha_desde	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton get_btn_fecha_desde() {
		if (_btn_fecha_desde == null) {
			_btn_fecha_desde = new JButton();
			_btn_fecha_desde.setBounds(new Rectangle(633, 26, 22, 17));
			_btn_fecha_desde.setIcon(new ImageIcon(getClass().getResource("/icons/calendar-16.gif")));
		}
		return _btn_fecha_desde;
	}

	/**
	 * This method initializes _txt_fecha_hasta	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_fecha_hasta() {
		if (_txt_fecha_hasta == null) {
			_txt_fecha_hasta = new JTextField();
			_txt_fecha_hasta.setBounds(new Rectangle(721, 28, 110, 15));
		}
		return _txt_fecha_hasta;
	}

	/**
	 * This method initializes _btn_fecha_hasta	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton get_btn_fecha_hasta() {
		if (_btn_fecha_hasta == null) {
			_btn_fecha_hasta = new JButton();
			_btn_fecha_hasta.setBounds(new Rectangle(835, 27, 21, 15));
			_btn_fecha_hasta.setIcon(new ImageIcon(getClass().getResource("/icons/calendar-16.gif")));
		}
		return _btn_fecha_hasta;
	}

	/**
	 * This method initializes _lst_estado	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_estado() {
		if (_lst_estado == null) {
			_lst_estado = new JComboBox();
			_lst_estado.setLocation(new Point(722, 11));
			_lst_estado.setFont(new Font("Dialog", Font.PLAIN, 10));
			_lst_estado.setSize(new Dimension(160, 18));
		}
		return _lst_estado;
	}

	/**
	 * This method initializes _txt_articulo_linea	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_articulo_linea() {
		if (_txt_articulo_linea == null) {
			_txt_articulo_linea = new JTextField();
			_txt_articulo_linea.setBounds(new Rectangle(588, 494, 162, 18));
			_txt_articulo_linea.setFont(new Font("Dialog", Font.PLAIN, 10));
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
			_txt_articulo_bloqueado.setBounds(new Rectangle(753, 494, 128, 18));
			_txt_articulo_bloqueado.setToolTipText("Indicada si el articulo seleccionado esta habilitado para la compra");
			_txt_articulo_bloqueado.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_articulo_bloqueado.setEditable(false);
		}
		return _txt_articulo_bloqueado;
	}

	/**
	 * This method initializes jScrollPane6	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane6() {
		if (jScrollPane6 == null) {
			jScrollPane6 = new JScrollPane();
			jScrollPane6.setBounds(new Rectangle(85, 213, 560, 60));
			jScrollPane6.setViewportView(get_txt_observacion());
		}
		return jScrollPane6;
	}

	/**
	 * This method initializes _txt_observacion	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
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
	 * This method initializes jPanel_faltantes	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel_faltantes() {
		if (jPanel_faltantes == null) {
			jLabel36 = new JLabel();
			jLabel36.setBounds(new Rectangle(668, 55, 56, 16));
			jLabel36.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel36.setText("Categoria");
			jLabel29 = new JLabel();
			jLabel29.setBounds(new Rectangle(733, 55, 71, 15));
			jLabel29.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel29.setText("Listar");
			jLabel34 = new JLabel();
			jLabel34.setBounds(new Rectangle(33, 58, 97, 15));
			jLabel34.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel34.setText("idArticulo");
			jLabel33 = new JLabel();
			jLabel33.setBounds(new Rectangle(151, 57, 120, 15));
			jLabel33.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel33.setText("Descripcion");
			jLabel32 = new JLabel();
			jLabel32.setBounds(new Rectangle(407, 57, 104, 14));
			jLabel32.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel32.setText("nombre vendedor");
			jLabel19 = new JLabel();
			jLabel19.setBounds(new Rectangle(275, 55, 61, 16));
			jLabel19.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel19.setText("Linea");
			jLabel28 = new JLabel();
			jLabel28.setBounds(new Rectangle(475, 27, 53, 17));
			jLabel28.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel28.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel28.setText("Desde");
			jPanel_faltantes = new JPanel();
			jPanel_faltantes.setLayout(null);
			jPanel_faltantes.setBackground(new Color(255, 204, 153));
			jPanel_faltantes.add(getJToolBar3(), null);
			jPanel_faltantes.add(get_txt_fecha_desde_faltante(), null);
			jPanel_faltantes.add(get_txt_fecha_hasta_faltante(), null);
			jPanel_faltantes.add(get_chk_mostrar_faltantes_proveedor(), null);
			jPanel_faltantes.add(get_btn_buscar_fecha_desde_faltante(), null);
			jPanel_faltantes.add(get_btn_buscar_fecha_hasta_faltante(), null);
			jPanel_faltantes.add(jLabel28, null);
			jPanel_faltantes.add(get_chk_mostrar_eliminados(), null);
			jPanel_faltantes.add(jLabel19, null);
			jPanel_faltantes.add(get_txt_linea_faltantes(), null);
			jPanel_faltantes.add(get_txt_idarticuo_faltantes(), null);
			jPanel_faltantes.add(get_txt_descripcion_faltantes(), null);
			jPanel_faltantes.add(getTxt_vendedor_faltantes(), null);
			jPanel_faltantes.add(jLabel32, null);
			jPanel_faltantes.add(jLabel33, null);
			jPanel_faltantes.add(jLabel34, null);
			jPanel_faltantes.add(get_lst_faltantes(), null);
			jPanel_faltantes.add(jLabel29, null);
			jPanel_faltantes.add(getJSplitPane(), null);
			jPanel_faltantes.add(get_lst_categoria_faltantes(), null);
			jPanel_faltantes.add(jLabel36, null);
			jPanel_faltantes.add(get_chk_seleccionar_faltantes(), null);
			jPanel_faltantes.add(get_lst_modo_faltantes(), null);
		}
		return jPanel_faltantes;
	}

	/**
	 * This method initializes jToolBar3	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar3() {
		if (jToolBar3 == null) {
			jToolBar3 = new JToolBar();
			jToolBar3.setFloatable(false);
			jToolBar3.setSize(new Dimension(876, 22));
			jToolBar3.setLocation(new Point(0, 0));
			jToolBar3.add(get_btn_importar_faltante());
			jToolBar3.add(get_btn_cargar_faltantes());
			jToolBar3.add(get_btn_eliminar_faltantes());
		}
		return jToolBar3;
	}

	/**
	 * This method initializes _btn_importar_faltante	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_importar_faltante() {
		if (_btn_importar_faltante == null) {
			_btn_importar_faltante = new JButton();
			_btn_importar_faltante.setIcon(new ImageIcon(getClass().getResource("/icons/go-bottom.png")));
			_btn_importar_faltante.setToolTipText("Importar Faltantes Seleccionados al Pedido");
		}
		return _btn_importar_faltante;
	}

	/**
	 * This method initializes _btn_cargar_faltantes	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cargar_faltantes() {
		if (_btn_cargar_faltantes == null) {
			_btn_cargar_faltantes = new JButton();
			_btn_cargar_faltantes.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-refresh.png")));
			_btn_cargar_faltantes.setToolTipText("Cargar Faltantes");
		}
		return _btn_cargar_faltantes;
	}

	/**
	 * This method initializes _txt_fecha_desde_faltante	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_fecha_desde_faltante() {
		if (_txt_fecha_desde_faltante == null) {
			_txt_fecha_desde_faltante = new JTextField();
			_txt_fecha_desde_faltante.setBounds(new Rectangle(533, 26, 115, 18));
			_txt_fecha_desde_faltante.setToolTipText("Fecha Desde donde se marco el faltante");
			_txt_fecha_desde_faltante.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_fecha_desde_faltante;
	}

	/**
	 * This method initializes _txt_fecha_hasta_faltante	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_fecha_hasta_faltante() {
		if (_txt_fecha_hasta_faltante == null) {
			_txt_fecha_hasta_faltante = new JTextField();
			_txt_fecha_hasta_faltante.setBounds(new Rectangle(677, 28, 122, 17));
			_txt_fecha_hasta_faltante.setToolTipText("Fecha Hasta donde se marco el faltante");
			_txt_fecha_hasta_faltante.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_fecha_hasta_faltante;
	}

	/**
	 * This method initializes _chk_mostrar_faltantes_proveedor	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_mostrar_faltantes_proveedor() {
		if (_chk_mostrar_faltantes_proveedor == null) {
			_chk_mostrar_faltantes_proveedor = new JCheckBox();
			_chk_mostrar_faltantes_proveedor.setBounds(new Rectangle(148, 27, 167, 15));
			_chk_mostrar_faltantes_proveedor.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_mostrar_faltantes_proveedor.setText("solo faltantes del proveedor");
		}
		return _chk_mostrar_faltantes_proveedor;
	}

	/**
	 * This method initializes _btn_eliminar_faltantes	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_eliminar_faltantes() {
		if (_btn_eliminar_faltantes == null) {
			_btn_eliminar_faltantes = new JButton();
			_btn_eliminar_faltantes.setIcon(new ImageIcon(getClass().getResource("/icons/edit-delete.png")));
			_btn_eliminar_faltantes.setToolTipText("Eliminar Faltantes Seleccionados de la Lista de Faltantes");
		}
		return _btn_eliminar_faltantes;
	}

	/**
	 * This method initializes _btn_buscar_fecha_desde_faltante	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton get_btn_buscar_fecha_desde_faltante() {
		if (_btn_buscar_fecha_desde_faltante == null) {
			_btn_buscar_fecha_desde_faltante = new JButton();
			_btn_buscar_fecha_desde_faltante.setBounds(new Rectangle(650, 27, 18, 17));
			_btn_buscar_fecha_desde_faltante.setIcon(new ImageIcon(getClass().getResource("/icons/calendar-16.gif")));
		}
		return _btn_buscar_fecha_desde_faltante;
	}

	/**
	 * This method initializes _btn_buscar_fecha_hasta_faltante	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton get_btn_buscar_fecha_hasta_faltante() {
		if (_btn_buscar_fecha_hasta_faltante == null) {
			_btn_buscar_fecha_hasta_faltante = new JButton();
			_btn_buscar_fecha_hasta_faltante.setBounds(new Rectangle(802, 28, 19, 18));
			_btn_buscar_fecha_hasta_faltante.setIcon(new ImageIcon(getClass().getResource("/icons/calendar-16.gif")));
		}
		return _btn_buscar_fecha_hasta_faltante;
	}

	/**
	 * This method initializes jScrollPane7	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane7() {
		if (jScrollPane7 == null) {
			jScrollPane7 = new JScrollPane();
			jScrollPane7.setViewportView(getJTable_faltantes());
		}
		return jScrollPane7;
	}

	/**
	 * This method initializes jTable_faltantes	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getJTable_faltantes() {
		if (jTable_faltantes == null) {
			jTable_faltantes = new JTable();
		}
		return jTable_faltantes;
	}
	
	public void setJTable_faltantes(JTable table){
		this.jTable_faltantes=table;
		this.jScrollPane7.setViewportView(this.jTable_faltantes);
	}

	/**
	 * This method initializes jPanel_info	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel_info() {
		if (jPanel_info == null) {
			jPanel_info = new JPanel();
			jPanel_info.setLayout(null);
			jPanel_info.add(jLabel3, null);
			jPanel_info.add(get_txt_fecha_creacion(), null);
			jPanel_info.add(get_txt_idcreador(), null);
			jPanel_info.add(get_txt_creador(), null);
			jPanel_info.add(jLabel5, null);
			jPanel_info.add(get_txt_modificado(), null);
		}
		return jPanel_info;
	}

	/**
	 * This method initializes _chk_mostrar_eliminados	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_mostrar_eliminados() {
		if (_chk_mostrar_eliminados == null) {
			_chk_mostrar_eliminados = new JCheckBox();
			_chk_mostrar_eliminados.setBounds(new Rectangle(7, 27, 133, 15));
			_chk_mostrar_eliminados.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_mostrar_eliminados.setText("mostrar eliminados");
			_chk_mostrar_eliminados.setToolTipText("Si selecciona, muestra incluso los faltantes utilizados o eliminados");
		}
		return _chk_mostrar_eliminados;
	}

	/**
	 * This method initializes _txt_linea	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_linea() {
		if (_txt_linea == null) {
			_txt_linea = new JTextField();
			_txt_linea.setBounds(new Rectangle(27, 57, 154, 17));
			_txt_linea.setText("");
			_txt_linea.setToolTipText("Filtro Por Linea");
		}
		return _txt_linea;
	}

	/**
	 * This method initializes _chk_full_critico	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_full_critico() {
		if (_chk_full_critico == null) {
			_chk_full_critico = new JCheckBox();
			_chk_full_critico.setBounds(new Rectangle(659, 58, 50, 16));
			_chk_full_critico.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_full_critico.setToolTipText("Incluir Articulos de Otros Proveedores");
			_chk_full_critico.setText("Full");
		}
		return _chk_full_critico;
	}

	/**
	 * This method initializes _txt_linea_faltantes	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_linea_faltantes() {
		if (_txt_linea_faltantes == null) {
			_txt_linea_faltantes = new JTextField();
			_txt_linea_faltantes.setBounds(new Rectangle(274, 74, 130, 18));
			_txt_linea_faltantes.setToolTipText("Filtro Por Linea");
		}
		return _txt_linea_faltantes;
	}

	/**
	 * This method initializes jScrollPane8	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane8() {
		if (jScrollPane8 == null) {
			jScrollPane8 = new JScrollPane();
			jScrollPane8.setBounds(new Rectangle(5, 516, 881, 70));
			jScrollPane8.setViewportView(getJTable_equivalencias());
		}
		return jScrollPane8;
	}

	/**
	 * This method initializes jTable_equivalencias	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getJTable_equivalencias() {
		if (jTable_equivalencias == null) {
			jTable_equivalencias = new JTable();
		}
		return jTable_equivalencias;
	}

	/**
	 * This method initializes jScrollPane9	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane9() {
		if (jScrollPane9 == null) {
			jScrollPane9 = new JScrollPane();
			jScrollPane9.setViewportView(getJTable_equivalencias_faltantes());
		}
		return jScrollPane9;
	}

	/**
	 * This method initializes jTable_equivalencias_faltantes	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getJTable_equivalencias_faltantes() {
		if (jTable_equivalencias_faltantes == null) {
			jTable_equivalencias_faltantes = new JTable();
		}
		return jTable_equivalencias_faltantes;
	}

	/**
	 * This method initializes jScrollPane10	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane10() {
		if (jScrollPane10 == null) {
			jScrollPane10 = new JScrollPane();
			jScrollPane10.setBounds(new Rectangle(6, 198, 856, 76));
			jScrollPane10.setViewportView(getJTable_equivalencias_critico());
		}
		return jScrollPane10;
	}

	/**
	 * This method initializes jTable_equivalencias_critico	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getJTable_equivalencias_critico() {
		if (jTable_equivalencias_critico == null) {
			jTable_equivalencias_critico = new JTable();
		}
		return jTable_equivalencias_critico;
	}
	
	public void setJTable_equivalencias_critico(JTable table) {
		this.jTable_equivalencias_critico=table;
		this.jScrollPane10.setViewportView(this.jTable_equivalencias_critico);
	}
	
	public void setJTable_equivalencias_faltantes(JTable table) {
		this.jTable_equivalencias_faltantes=table;
		this.jScrollPane9.setViewportView(this.jTable_equivalencias_faltantes);
	}
	
	public void setJTable_equivalencias(JTable table) {
		this.jTable_equivalencias=table;
		this.jScrollPane8.setViewportView(this.jTable_equivalencias);
	}

	/**
	 * This method initializes _txt_email	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_email() {
		if (_txt_email == null) {
			_txt_email = new JTextField();
			_txt_email.setBounds(new Rectangle(84, 101, 306, 20));
			_txt_email.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_email;
	}

	/**
	 * This method initializes _txt_fax	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_fax() {
		if (_txt_fax == null) {
			_txt_fax = new JTextField();
			_txt_fax.setBounds(new Rectangle(407, 74, 241, 21));
		}
		return _txt_fax;
	}

	/**
	 * This method initializes _btn_editar_transporte	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_editar_transporte() {
		if (_btn_editar_transporte == null) {
			_btn_editar_transporte = new JButton();
			_btn_editar_transporte.setBounds(new Rectangle(172, 3, 21, 19));
			_btn_editar_transporte.setToolTipText("Editar Transporte");
			_btn_editar_transporte.setIcon(new ImageIcon(getClass().getResource("/icons/edit-find-replace.png")));
		}
		return _btn_editar_transporte;
	}

	/**
	 * This method initializes _txt_idarticuo_faltantes	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idarticuo_faltantes() {
		if (_txt_idarticuo_faltantes == null) {
			_txt_idarticuo_faltantes = new JTextField();
			_txt_idarticuo_faltantes.setPreferredSize(new Dimension(4, 18));
			_txt_idarticuo_faltantes.setSize(new Dimension(115, 18));
			_txt_idarticuo_faltantes.setToolTipText("Filtro por Articulo");
			_txt_idarticuo_faltantes.setLocation(new Point(31, 74));
		}
		return _txt_idarticuo_faltantes;
	}

	/**
	 * This method initializes _txt_descripcion_faltantes	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_descripcion_faltantes() {
		if (_txt_descripcion_faltantes == null) {
			_txt_descripcion_faltantes = new JTextField();
			_txt_descripcion_faltantes.setLocation(new Point(149, 74));
			_txt_descripcion_faltantes.setToolTipText("Filtro Por Descripcion");
			_txt_descripcion_faltantes.setSize(new Dimension(122, 18));
		}
		return _txt_descripcion_faltantes;
	}

	/**
	 * This method initializes txt_vendedor_faltantes	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTxt_vendedor_faltantes() {
		if (txt_vendedor_faltantes == null) {
			txt_vendedor_faltantes = new JTextField();
			txt_vendedor_faltantes.setLocation(new Point(406, 74));
			txt_vendedor_faltantes.setToolTipText("Filtro Por Nombre de Vendedor");
			txt_vendedor_faltantes.setSize(new Dimension(136, 18));
		}
		return txt_vendedor_faltantes;
	}

	/**
	 * This method initializes _lst_faltantes	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_faltantes() {
		if (_lst_faltantes == null) {
			_lst_faltantes = new JComboBox();
			_lst_faltantes.setSize(new Dimension(114, 17));
			_lst_faltantes.setToolTipText("Listar Todos o Para Pedir o Para Stock");
			_lst_faltantes.setLocation(new Point(732, 74));
			_lst_faltantes.addItem("");
			_lst_faltantes.addItem("Pedidos");
			_lst_faltantes.addItem("Stock");
		}
		return _lst_faltantes;
	}

	/**
	 * This method initializes jSplitPane	
	 * 	
	 * @return javax.swing.JSplitPane	
	 */
	private JSplitPane getJSplitPane() {
		if (jSplitPane == null) {
			jSplitPane = new JSplitPane();
			jSplitPane.setBounds(new Rectangle(3, 97, 869, 181));
			jSplitPane.setDividerLocation(170);
			jSplitPane.setOneTouchExpandable(true);
			jSplitPane.setTopComponent(getJScrollPane7());
			jSplitPane.setBottomComponent(getJScrollPane9());
			jSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		}
		return jSplitPane;
	}

	/**
	 * This method initializes _lst_categoria	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_categoria() {
		if (_lst_categoria == null) {
			_lst_categoria = new JComboBox();
			_lst_categoria.setBounds(new Rectangle(446, 58, 63, 18));
			_lst_categoria.addItem("");
			_lst_categoria.addItem("A");
			_lst_categoria.addItem("B");
			_lst_categoria.addItem("C");
			_lst_categoria.addItem("D");
		}
		return _lst_categoria;
	}

	/**
	 * This method initializes _chk_seleccion_criticos	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_seleccion_criticos() {
		if (_chk_seleccion_criticos == null) {
			_chk_seleccion_criticos = new JCheckBox();
			_chk_seleccion_criticos.setBounds(new Rectangle(5, 57, 19, 17));
		}
		return _chk_seleccion_criticos;
	}

	/**
	 * This method initializes _lst_categoria_faltantes	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_categoria_faltantes() {
		if (_lst_categoria_faltantes == null) {
			_lst_categoria_faltantes = new JComboBox();
			_lst_categoria_faltantes.setSize(new Dimension(58, 17));
			_lst_categoria_faltantes.setLocation(new Point(669, 74));
			_lst_categoria_faltantes.addItem("");
			_lst_categoria_faltantes.addItem("A");
			_lst_categoria_faltantes.addItem("B");
			_lst_categoria_faltantes.addItem("C");
			_lst_categoria_faltantes.addItem("D");
		}
		return _lst_categoria_faltantes;
	}

	/**
	 * This method initializes _chk_seleccionar_faltantes	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_seleccionar_faltantes() {
		if (_chk_seleccionar_faltantes == null) {
			_chk_seleccionar_faltantes = new JCheckBox();
			_chk_seleccionar_faltantes.setBounds(new Rectangle(4, 74, 20, 18));
		}
		return _chk_seleccionar_faltantes;
	}

	/**
	 * This method initializes _lst_stock	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_stock() {
		if (_lst_stock == null) {
			_lst_stock = new JComboBox();
			_lst_stock.setBounds(new Rectangle(567, 58, 54, 18));
			_lst_stock.setToolTipText("Stock Menor Igual a");
			_lst_stock.addItem("");
			_lst_stock.addItem("0");
			_lst_stock.addItem("1");
			_lst_stock.addItem("2");
			_lst_stock.addItem("3");
			_lst_stock.addItem("4");
			_lst_stock.addItem("5");
			_lst_stock.addItem("10");
		}
		return _lst_stock;
	}

	/**
	 * This method initializes _lst_modo	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_modo() {
		if (_lst_modo == null) {
			_lst_modo = new JComboBox();
			_lst_modo.setLocation(new Point(255, 57));
			_lst_modo.setSize(new Dimension(124, 18));
			_lst_modo.addItem("Rotacion");
			_lst_modo.addItem("Volumen");
			_lst_modo.addItem("Pickups");
		}
		return _lst_modo;
	}

	/**
	 * This method initializes _lst_modo_faltantes	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_modo_faltantes() {
		if (_lst_modo_faltantes == null) {
			_lst_modo_faltantes = new JComboBox();
			_lst_modo_faltantes.setBounds(new Rectangle(548, 74, 114, 17));
			_lst_modo_faltantes.setFont(new Font("Dialog", Font.PLAIN, 10));
			_lst_modo_faltantes.addItem("Rotacion");
			_lst_modo_faltantes.addItem("Volumen");
			_lst_modo_faltantes.addItem("Pickups");
		}
		return _lst_modo_faltantes;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
