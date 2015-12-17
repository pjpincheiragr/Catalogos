package aplicacion.ventas.pedido.gui;

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

import com.jhlabs.image.DiffuseFilter;
import javax.swing.JTabbedPane;
import java.awt.Point;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JToolBar jToolBar = null;
	private JButton _btn_cancelar = null;
	private JButton _btn_guardar = null;
	private JButton _btn_salir = null;
	private JLabel jLabel = null;
	private JTextField _txt_numero = null;
	private JTextField _txt_idpedido = null;
	private JButton _btn_buscar_pedido = null;
	private JButton _btn_cargar_pedido = null;
	private JTextField _txt_pedido_descripcion = null;
	private JLabel jLabel1 = null;
	private JTextField _txt_idcliente = null;
	private JButton _btn_buscar_cliente = null;
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
	private JScrollPane jScrollPane2 = null;
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
	private JButton _btn_eliminar_remito = null;
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
	private JTextField _txt_articulo_bloqueado = null;
	private JTextField _txt_articulo_linea = null;
	private JPanel jPanel3 = null;
	private JTextField _txt_articulo_pedido = null;
	private JLabel jLabel19 = null;
	private JLabel jLabel20 = null;
	private JLabel jLabel21 = null;
	private JLabel jLabel22 = null;
	private JLabel jLabel23 = null;
	private JLabel jLabel24 = null;
	private JLabel jLabel25 = null;
	private JButton _btn_remitos_buscar = null;
	private JButton _btn_copia = null;
	private JButton _btn_editar_transporte = null;
	private JLabel jLabel26 = null;
	private JTextField _txt_iddeposito = null;
	private JTextField _txt_negocio = null;
	private JTextField _txt_deposito_descripcion = null;
	private JButton _btn_buscar_deposito = null;
	private JTabbedPane jTabbedPane1 = null;
	private JPanel jPanel1 = null;
	private JTable jTable_stock = null;
	private JTextField _txt_idcreador = null;
	private JTextField _txt_creador = null;
	private JButton _btn_pedidoe = null;
	private JPanel jPanel4 = null;
	private JScrollPane jScrollPane3 = null;
	private JTable jTable_compras = null;
	private JToolBar jToolBar1 = null;
	private JToolBar jToolBar2 = null;
	private JButton _btn_cargar_compras = null;
	private JPanel jPanel5 = null;
	private JButton _btn_faltantes = null;
	private JTextField _txt_fax = null;
	private JTextField _txt_email = null;
	private JLabel jLabel27 = null;
	private JLabel jLabel28 = null;
	private JScrollPane jScrollPane4 = null;
	private JTable jTable_equivalencias = null;
	private JButton _btn_importar_equivalencia = null;
	private JPanel jPanel6 = null;
	private JButton _btn_transferir = null;
	private JButton _btn_aviso = null;
	private JPanel jPanel7 = null;
	private JScrollPane jScrollPane5 = null;
	private JTable jTable_transferencia = null;
	private JTextField _txt_estado = null;
	private JButton _btn_importar = null;
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
		this.setSize(911, 725);
		this.setContentPane(getJContentPane());
		this.setTitle("Pedido de Cliente");
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
			jLabel17.setBounds(new Rectangle(459, 4, 30, 20));
			jLabel17.setText("Guia");
			jLabel16 = new JLabel();
			jLabel16.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel16.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel16.setBounds(new Rectangle(632, 4, 69, 17));
			jLabel16.setText("Fecha Envio");
			jLabel15 = new JLabel();
			jLabel15.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel15.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel15.setBounds(new Rectangle(4, 5, 68, 16));
			jLabel15.setText("Transporte");
			jLabel6 = new JLabel();
			jLabel6.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel6.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel6.setBounds(new Rectangle(9, 271, 66, 17));
			jLabel6.setText("idVendedor");
			jLabel5 = new JLabel();
			jLabel5.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel5.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel5.setBounds(new Rectangle(12, 49, 55, 22));
			jLabel5.setText("Modificado");
			jLabel3 = new JLabel();
			jLabel3.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3.setBounds(new Rectangle(13, 21, 54, 19));
			jLabel3.setText("Creado");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(755, 35, 35, 19));
			jLabel2.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2.setText("Fecha");
			jLabel1 = new JLabel();
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setBounds(new Rectangle(9, 2, 73, 19));
			jLabel1.setText("Cliente");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(10, 34, 67, 18));
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setText("pedido");
			
			jContentPane = new JPanel();
			
			jContentPane.setLayout(null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(get_txt_numero(), null);
			jContentPane.add(get_txt_idpedido(), null);
			jContentPane.add(get_btn_buscar_pedido(), null);
			jContentPane.add(get_txt_negocio(), null);
			jContentPane.add(get_btn_cargar_pedido(), null);
			jContentPane.add(get_txt_pedido_descripcion(), null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(get_txt_fecha(), null);
			jContentPane.add(get_btn_buscar_fecha(), null);
			//jContentPane.add(this.getJXLayerUI(), null);
			jContentPane.add(getGeneral());
			
			jContentPane.add(getJPanel(), null);
			jContentPane.add(get_txt_estado(), null);
			
			
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
			jToolBar.add(get_btn_nuevo());
			jToolBar.add(get_btn_cancelar());
			jToolBar.add(get_btn_guardar());
			jToolBar.add(get_btn_preparar());
			jToolBar.add(get_btn_presupuesto());
			jToolBar.add(get_btn_remito());
			jToolBar.add(get_btn_copia());
			jToolBar.add(get_btn_faltantes());
			jToolBar.add(get_btn_envio());
			jToolBar.add(get_btn_identificador());
			jToolBar.add(get_btn_transferir());
			jToolBar.add(get_btn_importar());
			jToolBar.add(get_btn_aviso());
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
			_txt_idpedido.setBounds(new Rectangle(237, 34, 122, 18));
			_txt_idpedido.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_idpedido;
	}
	
	public JTextField get_txt_numero() {
		if (_txt_numero == null) {
			_txt_numero = new JTextField();
			_txt_numero.setBounds(new Rectangle(150, 34, 85, 18));
			_txt_numero.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_numero;
	}

	/**
	 * This method initializes _btn_buscar_pedido	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_pedido() {
		if (_btn_buscar_pedido == null) {
			_btn_buscar_pedido = new JButton();
			_btn_buscar_pedido.setBounds(new Rectangle(362, 34, 22, 19));
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
			_btn_cargar_pedido.setBounds(new Rectangle(385, 34, 21, 19));
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
			_txt_pedido_descripcion.setBounds(new Rectangle(410, 34, 250, 18));
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
			_txt_idcliente.setBounds(new Rectangle(87, 3, 93, 18));
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
			_btn_buscar_cliente.setToolTipText("Buscar Cliente");
			_btn_buscar_cliente.setBounds(new Rectangle(182, 3, 21, 19));
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
			_btn_cargar_cliente.setToolTipText("Cargar Cliente");
			_btn_cargar_cliente.setBounds(new Rectangle(206, 3, 21, 19));
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
			_txt_cliente_descripcion.setBounds(new Rectangle(255, 3, 372, 19));
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
			_txt_fecha.setBounds(new Rectangle(791, 35, 80, 18));
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
			_btn_buscar_fecha.setBounds(new Rectangle(875, 33, 22, 21));
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
			_txt_fecha_creacion.setBounds(new Rectangle(70, 23, 132, 17));
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
			_btn_presupuesto.setIcon(new ImageIcon(getClass().getResource("/icons/cash-icon.png")));
			_btn_presupuesto.setEnabled(false);
			_btn_presupuesto.setToolTipText("Crear Presupuesto");
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
			_btn_remito.setToolTipText("Generar Remito");
			_btn_remito.setEnabled(false);
			_btn_remito.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-run.png")));
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
			_btn_editar_cliente.setToolTipText("Editar Cliente");
			_btn_editar_cliente.setBounds(new Rectangle(230, 3, 21, 19));
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
			jScrollPane.setBounds(new Rectangle(80, 95, 775, 116));
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
			_txt_modificado.setBounds(new Rectangle(71, 51, 130, 19));
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
			_txt_idvendedor.setBounds(new Rectangle(79, 271, 60, 18));
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
			_txt_vendedor_descripcion.setBounds(new Rectangle(165, 271, 153, 18));
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
			jScrollPane1.setBounds(new Rectangle(3, 20, 877, 114));
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
	
	public void setJTable1(JTable table){
		this.jTable1=table;
		this.jScrollPane2.setViewportView(jTable1);
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
			_btn_buscar_vendedor.setBounds(new Rectangle(142, 271, 21, 19));
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
			_txt_articulo.setBounds(new Rectangle(26, 20, 120, 18));
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
			_txt_articulo_descripcion.setBounds(new Rectangle(150, 20, 234, 18));
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
			_txt_articulo_stock.setBounds(new Rectangle(385, 20, 43, 18));
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
			_chk_seleccionar.setBounds(new Rectangle(4, 4, 90, 15));
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
			jLabel14.setBounds(new Rectangle(728, 2, 36, 21));
			jLabel14.setText("Total");
			jLabel14.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel14.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel13 = new JLabel();
			jLabel13.setBounds(new Rectangle(591, 2, 34, 20));
			jLabel13.setText("IVA");
			jLabel13.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel13.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel12 = new JLabel();
			jLabel12.setBounds(new Rectangle(393, 1, 98, 20));
			jLabel12.setText("Subtotal");
			jLabel12.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel12.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel11 = new JLabel();
			jLabel11.setBounds(new Rectangle(37, 1, 51, 19));
			jLabel11.setText("Items");
			jLabel11.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel11.setHorizontalAlignment(SwingConstants.RIGHT);
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBackground(Color.gray);
			jPanel.setBounds(new Rectangle(2, 645, 897, 23));
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
			_txt_items.setBounds(new Rectangle(96, 2, 73, 18));
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
			_txt_subtotal.setBounds(new Rectangle(497, 2, 92, 18));
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
			_txt_iva.setBounds(new Rectangle(633, 2, 94, 18));
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
			_txt_total.setBounds(new Rectangle(771, 2, 100, 18));
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
			_chk_costo.setBounds(new Rectangle(4, 1, 21, 19));
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
			_chk_sobrescribir.setBounds(new Rectangle(106, 3, 113, 15));
			_chk_sobrescribir.setText("Sobrescribir");
		}
		return _chk_sobrescribir;
	}

	/**
	 * This method initializes jScrollPane2	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane2() {
		if (jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setBounds(new Rectangle(7, 39, 696, 90));
			jScrollPane2.setViewportView(getJTable1());
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
			jTable1 = new JTable();
		}
		return jTable1;
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
			_txt_fecha_envio.setBounds(new Rectangle(706, 4, 132, 19));
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
			_btn_buscar_fecha_envio.setBounds(new Rectangle(841, 4, 21, 19));
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
			_txt_guia.setBounds(new Rectangle(492, 3, 111, 19));
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
			_txt_idtransporte.setBounds(new Rectangle(81, 4, 105, 18));
			_txt_idtransporte.setFont(new Font("Dialog", Font.PLAIN, 10));
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
			_txt_transporte_descripcion.setBounds(new Rectangle(235, 4, 206, 18));
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
			_btn_buscar_transporte.setLocation(new Point(188, 4));
			_btn_buscar_transporte.setSize(new Dimension(21, 20));
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
			jLabel26 = new JLabel();
			jLabel26.setBounds(new Rectangle(340, 273, 78, 17));
			jLabel26.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel26.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel25 = new JLabel();
			jLabel25.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel25.setBounds(new Rectangle(710, 3, 89, 16));
			jLabel25.setText("Estado");
			jLabel24 = new JLabel();
			jLabel24.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel24.setBounds(new Rectangle(533, 3, 153, 14));
			jLabel24.setText("Linea");
			jLabel23 = new JLabel();
			jLabel23.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel23.setBounds(new Rectangle(431, 3, 94, 14));
			jLabel23.setText("Actualizacion");
			jLabel22 = new JLabel();
			jLabel22.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel22.setBounds(new Rectangle(385, 3, 38, 14));
			jLabel22.setText("Stock");
			jLabel21 = new JLabel();
			jLabel21.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel21.setBounds(new Rectangle(151, 3, 149, 14));
			jLabel21.setText("Descripcion");
			jLabel20 = new JLabel();
			jLabel20.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel20.setBounds(new Rectangle(28, 3, 116, 14));
			jLabel20.setText("Articulo");
			jLabel19 = new JLabel();
			jLabel19.setHorizontalAlignment(SwingConstants.LEFT);
			jLabel19.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel19.setBounds(new Rectangle(819, 3, 46, 15));
			jLabel19.setText("Pedidos");
			General = new JPanel();
			General.setLayout(null);
			General.setBounds(new Rectangle(3, 61, 894, 582));
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
			General.add(getJTabbedPane(), null);
			General.add(get_chk_seguimiento(), null);
			General.add(jLabel26, null);
			General.add(get_txt_iddeposito(), null);
			
			General.add(get_txt_deposito_descripcion(), null);
			General.add(get_btn_buscar_deposito(), null);
			General.add(getJTabbedPane1(), null);
		}
		return General;
	}
	
	public JXLayer<JComponent> getJXLayerUI(){
		JPanel panel = this.getGeneral();
		panel.setSize(new Dimension(897, 557));
		JXLayer<JComponent> layer = new JXLayer<JComponent>(panel, this.getLockableUI());
		layer.setBounds(new Rectangle(0, 67, 897, 550));
		this.getLockableUI().setLocked(true);
		return layer;
		
	}
	
	public LockableUI getLockableUI(){
		if (lockableUI==null){
			lockableUI= new LockableUI();        
			//BoxBlurFilter blur=new BoxBlurFilter(1,1,3);
			DiffuseFilter blur=new DiffuseFilter();
			blur.setInterpolation(1);
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
	 * This method initializes _btn_eliminar_remito	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_eliminar_remito() {
		if (_btn_eliminar_remito == null) {
			_btn_eliminar_remito = new JButton();
			_btn_eliminar_remito.setToolTipText("Eliminar Remito Seleccionado");
			_btn_eliminar_remito.setIcon(new ImageIcon(getClass().getResource("/icons/edit-delete.png")));
		}
		return _btn_eliminar_remito;
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
			_chk_seguimiento.setBounds(new Rectangle(739, 2, 117, 14));
			_chk_seguimiento.setHorizontalAlignment(SwingConstants.LEFT);
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
			jTabbedPane.setBounds(new Rectangle(9, 23, 873, 244));
			jTabbedPane.setFont(new Font("Dialog", Font.BOLD, 10));
			jTabbedPane.addTab("Envio/Direccion", null, getJPanel2(), null);
			jTabbedPane.addTab("Remitos", null, getJPanel3(), null);
			jTabbedPane.addTab("Compras", null, getJPanel4(), null);
			jTabbedPane.addTab("Acerca de", null, getJPanel5(), null);
			jTabbedPane.addTab("Transferencias", null, getJPanel7(), null);
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
			jLabel28 = new JLabel();
			jLabel28.setBounds(new Rectangle(11, 97, 66, 17));
			jLabel28.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel28.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel28.setText("Informacion");
			jLabel27 = new JLabel();
			jLabel27.setBounds(new Rectangle(447, 72, 42, 16));
			jLabel27.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel27.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel27.setText("email");
			jLabel18 = new JLabel();
			jLabel18.setBounds(new Rectangle(444, 25, 47, 19));
			jLabel18.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel18.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel18.setText("Ciudad");
			jLabel9 = new JLabel();
			jLabel9.setBounds(new Rectangle(12, 72, 61, 17));
			jLabel9.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel9.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel9.setText("Tel/Fax");
			jLabel8 = new JLabel();
			jLabel8.setBounds(new Rectangle(447, 48, 44, 18));
			jLabel8.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel8.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel8.setText("CPostal");
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
			jPanel2.add(get_btn_editar_transporte(), null);
			jPanel2.add(get_txt_fax(), null);
			jPanel2.add(get_txt_email(), null);
			jPanel2.add(jLabel27, null);
			jPanel2.add(jLabel28, null);
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
			_txt_domicilio.setBounds(new Rectangle(81, 48, 359, 18));
			_txt_domicilio.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_domicilio.setHorizontalAlignment(JTextField.LEFT);
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
			_txt_idprovincia.setBounds(new Rectangle(81, 26, 106, 18));
			_txt_idprovincia.setFont(new Font("Dialog", Font.PLAIN, 10));
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
			_txt_cpostal.setBounds(new Rectangle(492, 47, 178, 19));
			_txt_cpostal.setFont(new Font("Dialog", Font.PLAIN, 10));
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
			_txt_tel.setBounds(new Rectangle(81, 70, 158, 18));
			_txt_tel.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_tel.setHorizontalAlignment(JTextField.LEFT);
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
			_txt_provincia_descripcion.setBounds(new Rectangle(190, 26, 251, 18));
			_txt_provincia_descripcion.setFont(new Font("Dialog", Font.PLAIN, 10));
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
			_txt_idciudad.setBounds(new Rectangle(492, 25, 179, 19));
			_txt_idciudad.setFont(new Font("Dialog", Font.PLAIN, 10));
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
			_txt_articulo_actualizacion.setBounds(new Rectangle(430, 20, 101, 18));
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
			_txt_articulo_bloqueado.setBounds(new Rectangle(710, 20, 105, 18));
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
			_txt_articulo_linea.setBounds(new Rectangle(532, 20, 176, 18));
			_txt_articulo_linea.setFont(new Font("Dialog", Font.PLAIN, 8));
		}
		return _txt_articulo_linea;
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
			jPanel3.add(getJToolBar1(), null);
		}
		return jPanel3;
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
			_txt_articulo_pedido.setBounds(new Rectangle(819, 20, 49, 18));
			_txt_articulo_pedido.setHorizontalAlignment(JTextField.RIGHT);
		}
		return _txt_articulo_pedido;
	}

	/**
	 * This method initializes _btn_remitos_buscar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_remitos_buscar() {
		if (_btn_remitos_buscar == null) {
			_btn_remitos_buscar = new JButton();
			_btn_remitos_buscar.setToolTipText("Mostrar Remitos Generados");
			_btn_remitos_buscar.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-refresh.png")));
		}
		return _btn_remitos_buscar;
	}

	/**
	 * This method initializes _btn_copia	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_copia() {
		if (_btn_copia == null) {
			_btn_copia = new JButton();
			_btn_copia.setIcon(new ImageIcon(getClass().getResource("/icons/document-print.png")));
			_btn_copia.setToolTipText("Imprmir Copia de Remito Generado");
			_btn_copia.setEnabled(false);
		}
		return _btn_copia;
	}

	/**
	 * This method initializes _btn_editar_transporte	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_editar_transporte() {
		if (_btn_editar_transporte == null) {
			_btn_editar_transporte = new JButton();
			_btn_editar_transporte.setBounds(new Rectangle(211, 3, 21, 20));
			_btn_editar_transporte.setToolTipText("Editar Transporte");
			_btn_editar_transporte.setIcon(new ImageIcon(getClass().getResource("/icons/edit-find-replace.png")));
		}
		return _btn_editar_transporte;
	}

	/**
	 * This method initializes _txt_iddeposito	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_iddeposito() {
		if (_txt_iddeposito == null) {
			_txt_iddeposito = new JTextField();
			_txt_iddeposito.setEditable(false);
			_txt_iddeposito.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_iddeposito.setBounds(new Rectangle(422, 272, 60, 17));
		}
		return _txt_iddeposito;
	}

	public JTextField get_txt_negocio() {
		if (_txt_negocio == null) {
			_txt_negocio = new JTextField();
			_txt_negocio.setEditable(false);
			_txt_negocio.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_negocio.setBounds(new Rectangle(96, 33, 50, 18));
			_txt_negocio.setToolTipText("Unidad de Negocio");
		}
		return _txt_negocio;
	}
	/**
	 * This method initializes _txt_deposito_descripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_deposito_descripcion() {
		if (_txt_deposito_descripcion == null) {
			_txt_deposito_descripcion = new JTextField();
			_txt_deposito_descripcion.setBounds(new Rectangle(502, 272, 139, 17));
			_txt_deposito_descripcion.setEditable(false);
			_txt_deposito_descripcion.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_deposito_descripcion;
	}
	/**
	 * This method initializes _btn_buscar_deposito	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_deposito() {
		if (_btn_buscar_deposito == null) {
			_btn_buscar_deposito = new JButton();
			_btn_buscar_deposito.setBounds(new Rectangle(482, 272, 20, 17));
			_btn_buscar_deposito.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-searchtool.png")));
		}
		return _btn_buscar_deposito;
	}

	/**
	 * This method initializes jTabbedPane1	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJTabbedPane1() {
		if (jTabbedPane1 == null) {
			jTabbedPane1 = new JTabbedPane();
			jTabbedPane1.setBounds(new Rectangle(2, 291, 885, 288));
			jTabbedPane1.setFont(new Font("Dialog", Font.BOLD, 10));
			jTabbedPane1.addTab("Items", null, getJPanel1(), null);
		}
		return jTabbedPane1;
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
			jPanel1.add(getJScrollPane1(), null);
			jPanel1.add(get_chk_seleccionar(), null);
			jPanel1.add(get_chk_sobrescribir(), null);
			jPanel1.add(getJPanel6(), null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes _txt_idcreador	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idcreador() {
		if (_txt_idcreador == null) {
			_txt_idcreador = new JTextField();
			_txt_idcreador.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_idcreador.setBounds(new Rectangle(205, 22, 35, 18));
			_txt_idcreador.setEditable(false);
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
			_txt_creador.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_creador.setHorizontalAlignment(JTextField.LEFT);
			_txt_creador.setBounds(new Rectangle(243, 22, 107, 18));
			_txt_creador.setEditable(false);
		}
		return _txt_creador;
	}

	/**
	 * This method initializes _btn_pedidoe	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_pedidoe() {
		if (_btn_pedidoe == null) {
			_btn_pedidoe = new JButton();
			_btn_pedidoe.setIcon(new ImageIcon(getClass().getResource("/icons/phone-16.gif")));
			_btn_pedidoe.setEnabled(false);
			_btn_pedidoe.setToolTipText("Nuevo Pedido a Proveedor");
		}
		return _btn_pedidoe;
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
			jPanel4.add(getJToolBar2(), null);
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
			jScrollPane3.setBounds(new Rectangle(13, 35, 675, 163));
			jScrollPane3.setViewportView(getJTable_compras());
		}
		return jScrollPane3;
	}

	/**
	 * This method initializes jTable_compras	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTable_compras() {
		if (jTable_compras == null) {
			jTable_compras = new JTable();
		}
		return jTable_compras;
	}

	public void setJTable_compras(JTable table){
		jTable_compras=table;
		this.jScrollPane3.setViewportView(jTable_compras);
	}
	/**
	 * This method initializes jToolBar1	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar1() {
		if (jToolBar1 == null) {
			jToolBar1 = new JToolBar();
			jToolBar1.setBounds(new Rectangle(3, 3, 863, 23));
			jToolBar1.setFloatable(false);
			jToolBar1.add(get_btn_remitos_buscar());
			jToolBar1.add(get_btn_eliminar_remito());
		}
		return jToolBar1;
	}

	/**
	 * This method initializes jToolBar2	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar2() {
		if (jToolBar2 == null) {
			jToolBar2 = new JToolBar();
			jToolBar2.setBounds(new Rectangle(2, 2, 861, 23));
			jToolBar2.setFloatable(false);
			jToolBar2.add(get_btn_cargar_compras());
			jToolBar2.add(get_btn_pedidoe());
		}
		return jToolBar2;
	}

	/**
	 * This method initializes _btn_cargar_compras	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cargar_compras() {
		if (_btn_cargar_compras == null) {
			_btn_cargar_compras = new JButton();
			_btn_cargar_compras.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-refresh.png")));
		}
		return _btn_cargar_compras;
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
			jPanel5.add(jLabel3, null);
			jPanel5.add(get_txt_fecha_creacion(), null);
			jPanel5.add(get_txt_idcreador(), null);
			jPanel5.add(get_txt_creador(), null);
			jPanel5.add(jLabel5, null);
			jPanel5.add(get_txt_modificado(), null);
		}
		return jPanel5;
	}

	/**
	 * This method initializes _btn_faltantes	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_faltantes() {
		if (_btn_faltantes == null) {
			_btn_faltantes = new JButton();
			_btn_faltantes.setIcon(new ImageIcon(getClass().getResource("/icons/emblem-certified-16.png")));
			_btn_faltantes.setEnabled(false);
			_btn_faltantes.setToolTipText("Indicar los Faltantes Para Pedir a Proveedor");
		}
		return _btn_faltantes;
	}

	/**
	 * This method initializes _txt_fax	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_fax() {
		if (_txt_fax == null) {
			_txt_fax = new JTextField();
			_txt_fax.setBounds(new Rectangle(241, 70, 178, 18));
		}
		return _txt_fax;
	}

	/**
	 * This method initializes _txt_email	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_email() {
		if (_txt_email == null) {
			_txt_email = new JTextField();
			_txt_email.setBounds(new Rectangle(491, 71, 363, 19));
		}
		return _txt_email;
	}

	/**
	 * This method initializes jScrollPane4	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane4() {
		if (jScrollPane4 == null) {
			jScrollPane4 = new JScrollPane();
			jScrollPane4.setBounds(new Rectangle(4, 42, 869, 75));
			jScrollPane4.setViewportView(getJTable_equivalencias());
		}
		return jScrollPane4;
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

	public void setJTable_equivalencias(JTable table){
		this.jTable_equivalencias=table;
		this.jScrollPane4.setViewportView(jTable_equivalencias);
	}

	/**
	 * This method initializes _btn_importar_equivalencia	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_importar_equivalencia() {
		if (_btn_importar_equivalencia == null) {
			_btn_importar_equivalencia = new JButton();
			_btn_importar_equivalencia.setToolTipText("Importar Equivalencias");
			_btn_importar_equivalencia.setBounds(new Rectangle(2, 20, 20, 18));
			_btn_importar_equivalencia.setIcon(new ImageIcon(getClass().getResource("/icons/go-up.png")));
		}
		return _btn_importar_equivalencia;
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
			jPanel6.setBounds(new Rectangle(2, 136, 875, 122));
			jPanel6.setBackground(new Color(102, 102, 102));
			jPanel6.add(getJScrollPane4(), null);
			jPanel6.add(get_btn_importar_equivalencia(), null);
			jPanel6.add(get_txt_articulo(), null);
			jPanel6.add(jLabel20, null);
			jPanel6.add(get_txt_articulo_descripcion(), null);
			jPanel6.add(jLabel21, null);
			jPanel6.add(get_txt_articulo_stock(), null);
			jPanel6.add(jLabel22, null);
			jPanel6.add(get_txt_articulo_actualizacion(), null);
			jPanel6.add(jLabel23, null);
			jPanel6.add(get_txt_articulo_linea(), null);
			jPanel6.add(jLabel24, null);
			jPanel6.add(get_txt_articulo_bloqueado(), null);
			jPanel6.add(jLabel25, null);
			jPanel6.add(get_txt_articulo_pedido(), null);
			jPanel6.add(jLabel19, null);
		}
		return jPanel6;
	}

	/**
	 * This method initializes _btn_transferir	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_transferir() {
		if (_btn_transferir == null) {
			_btn_transferir = new JButton();
			_btn_transferir.setIcon(new ImageIcon(getClass().getResource("/icons/stock_mail-send-receive.png")));
			_btn_transferir.setEnabled(false);
			_btn_transferir.setToolTipText("Transferir Pedido");
		}
		return _btn_transferir;
	}

	/**
	 * This method initializes _btn_aviso	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_aviso() {
		if (_btn_aviso == null) {
			_btn_aviso = new JButton();
			_btn_aviso.setIcon(new ImageIcon(getClass().getResource("/icons/calendar-16.gif")));
			_btn_aviso.setEnabled(false);
			_btn_aviso.setToolTipText("Agendar Un Evento Para este Pedido");
		}
		return _btn_aviso;
	}

	/**
	 * This method initializes jPanel7	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel7() {
		if (jPanel7 == null) {
			jPanel7 = new JPanel();
			jPanel7.setLayout(null);
			jPanel7.add(getJScrollPane5(), null);
		}
		return jPanel7;
	}

	/**
	 * This method initializes jScrollPane5	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane5() {
		if (jScrollPane5 == null) {
			jScrollPane5 = new JScrollPane();
			jScrollPane5.setBounds(new Rectangle(11, 17, 573, 175));
			jScrollPane5.setViewportView(getJTable_transferencia());
		}
		return jScrollPane5;
	}

	/**
	 * This method initializes jTable_transferencia	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getJTable_transferencia() {
		if (jTable_transferencia == null) {
			jTable_transferencia = new JTable();
		}
		return jTable_transferencia;
	}
	
	public void setJTable_transferencia(JTable table) {
			jTable_transferencia = table;
			this.jScrollPane5.setViewportView(jTable_transferencia);
	}

	/**
	 * This method initializes _txt_estado	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_estado() {
		if (_txt_estado == null) {
			_txt_estado = new JTextField();
			_txt_estado.setHorizontalAlignment(JTextField.LEFT);
			_txt_estado.setSize(new Dimension(70, 17));
			_txt_estado.setLocation(new Point(680, 35));
			_txt_estado.setEditable(false);
		}
		return _txt_estado;
	}

	/**
	 * This method initializes _btn_importar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_importar() {
		if (_btn_importar == null) {
			_btn_importar = new JButton();
			_btn_importar.setEnabled(false);
			_btn_importar.setIcon(new ImageIcon(getClass().getResource("/icons/bottom.png")));
			_btn_importar.setToolTipText("Importar Desde Otros Pedidos");
		}
		return _btn_importar;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
