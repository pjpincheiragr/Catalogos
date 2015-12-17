package aplicacion.ventas.gestion.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import aplicacion.compras.carga.items.logic.DisplayCanvas;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import java.awt.Point;
import javax.swing.JToolBar;
import javax.swing.JProgressBar;

import org.jdesktop.jxlayer.JXLayer;
import org.jdesktop.jxlayer.plaf.effect.BufferedImageOpEffect;
import org.jdesktop.jxlayer.plaf.ext.LockableUI;

import com.jhlabs.image.BoxBlurFilter;
import javax.swing.JTabbedPane;

public class _Frame extends JFrame {
	private LockableUI lockableUI=null;
	private LockableUI lockableUI2=null;
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton _btn_cargar = null;
	private JPanel jPanel = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private JPanel jPanel1 = null;
	private JLabel jLabel9 = null;
	private JLabel jLabel11 = null;
	private JLabel jLabel12 = null;
	private JLabel jLabel13 = null;
	public JTextField _txt_c_fecha = null;
	public JTextField _txt_c_cuenta = null;
	public JTextField _txt_c_nombre = null;
	public JTextField _txt_c_idcomprobante = null;
	private JLabel jLabel14 = null;
	public JTextField _txt_c_fecha_carga = null;
	private JLabel jLabel15 = null;
	public JTextField _txt_c_fecha_control = null;
	private JButton _btn_editar_cpte = null;
	private JTable jTable1 = null;
	private JScrollPane jScrollPane2 = null;
	private JTextField _txt_idcliente = null;
	private JTextField _txt_cliente_descripcion = null;
	private JLabel jLabel17 = null;
	private JButton _btn_cancelar = null;
	private JComboBox _list_mes = null;
	private JComboBox _list_anio = null;
	private JScrollPane jScrollPane3 = null;
	private JTable jTable2 = null;
	private JPanel jPanel3 = null;
	private JToolBar jToolBar = null;
	private JButton _btn_salir = null;
	private JProgressBar jProgressBar = null;
	private JButton _btn_error = null;
	private JLabel jLabel19 = null;
	private JTextField _txt_pedido_descripcion = null;
	private JTextField _txt_c_descripcion = null;
	private JTextField _txt_c_vendedor = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel6 = null;
	private JTextField _txt_c_total = null;
	private JPanel jPanel4 = null;
	private JTextField _txt_idpedido = null;
	private JLabel jLabel7 = null;
	private JTextField _txt_idvendedor = null;
	private JTextField _txt_vendedor_descripcion = null;
	private JLabel jLabel8 = null;
	private JLabel jLabel10 = null;
	private JLabel jLabel20 = null;
	private JTextField _txt_idarticulo = null;
	private JTextField _txt_idarticulo_descripcion = null;
	private JLabel jLabel23 = null;
	private JTextField _txt_idarticulo_linea = null;
	private JTable jTable11 = null;
	private JCheckBox _chk_utiliza_calendario = null;
	private JButton _btn_nuevo = null;
	private JButton _btn_consultar = null;
	
	private JLabel jLabel = null;
	private JComboBox _lst_estado = null;
	private JCheckBox _chk_eliminados = null;
	private JPanel jPanel2 = null;
	private JTabbedPane jTabbedPane = null;
	private JTabbedPane jTabbedPane1 = null;
	private JPanel jPanel5 = null;
	private JScrollPane jScrollPane1 = null;
	private JTextArea _txt_informacion = null;
	private JTextField _txt_estado = null;
	private JScrollPane jScrollPane4 = null;
	private JTable jTable3 = null;
	private JCheckBox _chk_estado = null;
	private JLabel jLabel2 = null;
	private JTextField _txt_pedido_informacion = null;
	private JLabel jLabel3 = null;
	private JTextField _txt_pedido_remito = null;
	private JTextField _txt_idcreador = null;
	private JTextField _txt_creador = null;
	private JLabel jLabel4 = null;
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
		this.setSize(1029, 748);
		this.setResizable(false);
		this.setContentPane(getJContentPane());
		this.setTitle("Visor de Pedidos");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel = new JLabel();
			jLabel.setText("Estado");
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setBounds(new Rectangle(7, 70, 63, 15));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			
			jLabel23 = new JLabel();
			jLabel23.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel23.setHorizontalAlignment(SwingConstants.LEFT);
			jLabel23.setBounds(new Rectangle(597, 5, 42, 14));
			jLabel23.setText("Linea");
			jLabel20 = new JLabel();
			jLabel20.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel20.setHorizontalAlignment(SwingConstants.LEFT);
			jLabel20.setBounds(new Rectangle(495, 4, 67, 15));
			jLabel20.setText("idarticulo");
			jLabel10 = new JLabel();
			jLabel10.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel10.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel10.setBounds(new Rectangle(3, 45, 67, 16));
			jLabel10.setText("Codigo");
			jLabel8 = new JLabel();
			jLabel8.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel8.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel8.setBounds(new Rectangle(5, 24, 65, 15));
			jLabel8.setText("Descripcion");
			jLabel7 = new JLabel();
			jLabel7.setHorizontalAlignment(SwingConstants.LEFT);
			jLabel7.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel7.setBounds(new Rectangle(273, 4, 66, 16));
			jLabel7.setText("idvendedor");
			jLabel19 = new JLabel();
			jLabel19.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel19.setHorizontalAlignment(SwingConstants.LEFT);
			jLabel19.setBounds(new Rectangle(178, 4, 68, 17));
			jLabel19.setText("idpedido");
			jLabel17 = new JLabel();
			jLabel17.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel17.setHorizontalAlignment(SwingConstants.LEFT);
			jLabel17.setBounds(new Rectangle(75, 4, 67, 14));
			jLabel17.setText("cliente");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(getJProgressBar(), null);
			//jContentPane.add(getJPanel4(), null);
			//jContentPane.add(getJPanel2(), null);
			jContentPane.add(this.getJXLayerUI(), null);
			jContentPane.add(this.getJXLayerUI2(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes _btn_cargar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cargar() {
		if (_btn_cargar == null) {
			_btn_cargar = new JButton();
			_btn_cargar.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-refresh.png")));
		}
		return _btn_cargar;
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
			jPanel.setBackground(Color.darkGray);
			jPanel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jPanel.setForeground(Color.white);
			jPanel.setBounds(new Rectangle(3, 4, 1002, 182));
			jPanel.add(getJTabbedPane(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(1, 1, 998, 170));
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
			jTable.setToolTipText("Tabla de Pedidos");
		}
		return jTable;
	}
	
	public void setJTable(JTable table){
		this.jTable=table;
		this.jScrollPane.setViewportView(table);
	}

	
	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(786, 233, 99, 19));
			jLabel6.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel6.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel6.setText("Total");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(637, 42, 133, 17));
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setText("Vendedor");
			jLabel15 = new JLabel();
			jLabel15.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel15.setLocation(new Point(818, 3));
			jLabel15.setSize(new Dimension(108, 18));
			jLabel15.setText("Modificado");
			jLabel14 = new JLabel();
			jLabel14.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel14.setLocation(new Point(637, 5));
			jLabel14.setSize(new Dimension(101, 18));
			jLabel14.setText("Creado");
			jLabel13 = new JLabel();
			jLabel13.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel13.setLocation(new Point(107, 44));
			jLabel13.setSize(new Dimension(116, 18));
			jLabel13.setText("descripcion");
			jLabel12 = new JLabel();
			jLabel12.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel12.setLocation(new Point(14, 44));
			jLabel12.setSize(new Dimension(73, 18));
			jLabel12.setText("cuenta");
			jLabel11 = new JLabel();
			jLabel11.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel11.setLocation(new Point(85, 3));
			jLabel11.setSize(new Dimension(98, 18));
			jLabel11.setText("idpedido");
			jLabel9 = new JLabel();
			jLabel9.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel9.setLocation(new Point(13, 4));
			jLabel9.setSize(new Dimension(66, 18));
			jLabel9.setText("Fecha");
			jPanel1 = new JPanel();
			jPanel1.setLayout(null);
			jPanel1.setBackground(Color.lightGray);
			jPanel1.setBounds(new Rectangle(4, 191, 1002, 258));
			jPanel1.add(jLabel9, null);
			jPanel1.add(jLabel11, null);
			jPanel1.add(jLabel12, null);
			jPanel1.add(jLabel13, null);
			jPanel1.add(get_txt_c_fecha(), null);
			jPanel1.add(get_txt_c_cuenta(), null);
			jPanel1.add(get_txt_c_nombre(), null);
			jPanel1.add(get_txt_c_idcomprobante(), null);
			jPanel1.add(jLabel14, null);
			jPanel1.add(get_txt_c_fecha_carga(), null);
			jPanel1.add(jLabel15, null);
			jPanel1.add(get_txt_c_fecha_control(), null);
			//jPanel1.add(getJPanel2(), null);
			//jPanel1.add(this.getCanvas(),null);
			jPanel1.add(get_btn_editar_cpte(), null);
			jPanel1.add(get_txt_c_descripcion(), null);
			jPanel1.add(get_txt_c_vendedor(), null);
			jPanel1.add(jLabel1, null);
			jPanel1.add(jLabel6, null);
			jPanel1.add(get_txt_c_total(), null);
			jPanel1.add(getJTabbedPane1(), null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes _txt_c_fecha	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_c_fecha() {
		if (_txt_c_fecha == null) {
			_txt_c_fecha = new JTextField();
			_txt_c_fecha.setBackground(Color.black);
			_txt_c_fecha.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_c_fecha.setForeground(Color.white);
			_txt_c_fecha.setSize(new Dimension(66, 18));
			_txt_c_fecha.setLocation(new Point(14, 22));
			_txt_c_fecha.setEditable(false);
		}
		return _txt_c_fecha;
	}

	/**
	 * This method initializes _txt_c_cuenta	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_c_cuenta() {
		if (_txt_c_cuenta == null) {
			_txt_c_cuenta = new JTextField();
			_txt_c_cuenta.setBackground(Color.black);
			_txt_c_cuenta.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_c_cuenta.setForeground(Color.white);
			_txt_c_cuenta.setLocation(new Point(14, 62));
			_txt_c_cuenta.setSize(new Dimension(89, 18));
			_txt_c_cuenta.setEditable(false);
		}
		return _txt_c_cuenta;
	}

	/**
	 * This method initializes _txt_c_nombre	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_c_nombre() {
		if (_txt_c_nombre == null) {
			_txt_c_nombre = new JTextField();
			_txt_c_nombre.setBackground(Color.black);
			_txt_c_nombre.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_c_nombre.setForeground(Color.white);
			_txt_c_nombre.setHorizontalAlignment(JTextField.LEFT);
			_txt_c_nombre.setLocation(new Point(106, 62));
			_txt_c_nombre.setSize(new Dimension(320, 18));
			_txt_c_nombre.setEditable(false);
		}
		return _txt_c_nombre;
	}

	/**
	 * This method initializes _txt_c_idcomprobante	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_c_idcomprobante() {
		if (_txt_c_idcomprobante == null) {
			_txt_c_idcomprobante = new JTextField();
			_txt_c_idcomprobante.setBackground(Color.black);
			_txt_c_idcomprobante.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_c_idcomprobante.setForeground(Color.white);
			_txt_c_idcomprobante.setSize(new Dimension(99, 18));
			_txt_c_idcomprobante.setLocation(new Point(84, 22));
			_txt_c_idcomprobante.setEditable(false);
		}
		return _txt_c_idcomprobante;
	}

	/**
	 * This method initializes _txt_c_fecha_carga	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_c_fecha_carga() {
		if (_txt_c_fecha_carga == null) {
			_txt_c_fecha_carga = new JTextField();
			_txt_c_fecha_carga.setBackground(Color.black);
			_txt_c_fecha_carga.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_c_fecha_carga.setForeground(Color.white);
			_txt_c_fecha_carga.setLocation(new Point(637, 23));
			_txt_c_fecha_carga.setSize(new Dimension(179, 18));
			_txt_c_fecha_carga.setEditable(false);
		}
		return _txt_c_fecha_carga;
	}

	/**
	 * This method initializes _txt_c_fecha_control	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_c_fecha_control() {
		if (_txt_c_fecha_control == null) {
			_txt_c_fecha_control = new JTextField();
			_txt_c_fecha_control.setBackground(Color.black);
			_txt_c_fecha_control.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_c_fecha_control.setForeground(Color.white);
			_txt_c_fecha_control.setSize(new Dimension(169, 18));
			_txt_c_fecha_control.setLocation(new Point(818, 23));
			_txt_c_fecha_control.setEditable(false);
		}
		return _txt_c_fecha_control;
	}

	/**
	 * This method initializes _btn_editar_cpte	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_editar_cpte() {
		if (_btn_editar_cpte == null) {
			_btn_editar_cpte = new JButton();
			_btn_editar_cpte.setBounds(new Rectangle(185, 21, 19, 19));
			_btn_editar_cpte.setToolTipText("editar comprobante");
			_btn_editar_cpte.setIcon(new ImageIcon(getClass().getResource("/icons/edit-find-replace.png")));
		}
		return _btn_editar_cpte;
	}

	/**
	 * This method initializes jScrollPane2	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	public JScrollPane getJScrollPane2() {
		if (jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setViewportView(getJTable11());
		}
		return jScrollPane2;
	}

	/**
	 * This method initializes _txt_idcliente	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idcliente() {
		if (_txt_idcliente == null) {
			_txt_idcliente = new JTextField();
			_txt_idcliente.setToolTipText("Ingrese Codigo de Cliente y Presione F5");
			_txt_idcliente.setBounds(new Rectangle(74, 44, 101, 18));
			_txt_idcliente.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_idcliente;
	}

	/**
	 * This method initializes _txt_cliente_descripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_cliente_descripcion() {
		if (_txt_cliente_descripcion == null) {
			_txt_cliente_descripcion = new JTextField();
			_txt_cliente_descripcion.setToolTipText("Ingrese Descripcion de Cliente y Presione F5");
			_txt_cliente_descripcion.setBounds(new Rectangle(74, 23, 100, 18));
			_txt_cliente_descripcion.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_cliente_descripcion;
	}

	/**
	 * This method initializes _btn_cancelar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cancelar() {
		if (_btn_cancelar == null) {
			_btn_cancelar = new JButton();
			_btn_cancelar.setToolTipText("limpiar busqueda");
			_btn_cancelar.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-stop.png")));
		}
		return _btn_cancelar;
	}

	/**
	 * This method initializes _list_mes	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_list_mes() {
		if (_list_mes == null) {
			_list_mes = new JComboBox();
			_list_mes.addItem("Enero");
			_list_mes.addItem("Febrero");
			_list_mes.addItem("Marzo");
			_list_mes.addItem("Abril");
			_list_mes.addItem("Mayo");
			_list_mes.addItem("Junio");
			_list_mes.addItem("Julio");
			_list_mes.addItem("Agosto");
			_list_mes.addItem("Septiembre");
			_list_mes.addItem("Octubre");
			_list_mes.addItem("Noviembre");
			_list_mes.addItem("Diciembre");
			_list_mes.setFont(new Font("Dialog", Font.BOLD, 10));
			_list_mes.setBounds(new Rectangle(3, 5, 127, 14));
		}
		return _list_mes;
	}

	/**
	 * This method initializes _list_anio	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_list_anio() {
		if (_list_anio == null) {
			_list_anio = new JComboBox();
			_list_anio.setFont(new Font("Dialog", Font.BOLD, 10));
			_list_anio.setBounds(new Rectangle(131, 4, 91, 15));
		}
		return _list_anio;
	}


	/**
	 * This method initializes jScrollPane3	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane3() {
		if (jScrollPane3 == null) {
			jScrollPane3 = new JScrollPane();
			jScrollPane3.setBounds(new Rectangle(4, 23, 218, 135));
			jScrollPane3.setViewportView(getJTable2());
		}
		return jScrollPane3;
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
		this.jScrollPane3.setViewportView(jTable2);
	}

	public void setJTable1(JTable table){
		this.jTable1=table;
		this.jScrollPane2.setViewportView(jTable1);
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
			jPanel3.setBackground(Color.gray);
			jPanel3.setBounds(new Rectangle(765, 18, 226, 160));
			jPanel3.add(getJScrollPane3(), null);
			jPanel3.add(get_list_anio(), null);
			jPanel3.add(get_list_mes(), null);
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
			jToolBar.setBounds(new Rectangle(1, 3, 1021, 22));
			jToolBar.setFloatable(false);
			jToolBar.add(get_btn_cancelar());
			jToolBar.add(get_btn_cargar());
			jToolBar.add(get_btn_nuevo());
			jToolBar.add(get_btn_consultar());
			jToolBar.add(get_btn_salir());
			jToolBar.addSeparator();
			jToolBar.add(get_btn_error());
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
			_btn_salir.setToolTipText("Salir de Aplicacion");
			_btn_salir.setIcon(new ImageIcon(getClass().getResource("/icons/exit.png")));
		}
		return _btn_salir;
	}


	/**
	 * This method initializes jProgressBar	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	public JProgressBar getJProgressBar() {
		if (jProgressBar == null) {
			jProgressBar = new JProgressBar();
			jProgressBar.setBounds(new Rectangle(6, 212, 1006, 18));
		}
		return jProgressBar;
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
	 * This method initializes _txt_pedido_descripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_pedido_descripcion() {
		if (_txt_pedido_descripcion == null) {
			_txt_pedido_descripcion = new JTextField();
			_txt_pedido_descripcion.setToolTipText("Ingrese Descripcion de Pedido y Presione F5");
			_txt_pedido_descripcion.setBounds(new Rectangle(178, 23, 86, 18));
			_txt_pedido_descripcion.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_pedido_descripcion;
	}


	/**
	 * This method initializes _txt_c_descripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_c_descripcion() {
		if (_txt_c_descripcion == null) {
			_txt_c_descripcion = new JTextField();
			_txt_c_descripcion.setBounds(new Rectangle(206, 21, 309, 18));
			_txt_c_descripcion.setForeground(Color.white);
			_txt_c_descripcion.setBackground(Color.black);
			_txt_c_descripcion.setHorizontalAlignment(JTextField.RIGHT);
			_txt_c_descripcion.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_c_descripcion;
	}


	/**
	 * This method initializes _txt_c_vendedor	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_c_vendedor() {
		if (_txt_c_vendedor == null) {
			_txt_c_vendedor = new JTextField();
			_txt_c_vendedor.setBounds(new Rectangle(637, 61, 179, 18));
			_txt_c_vendedor.setBackground(Color.black);
			_txt_c_vendedor.setForeground(Color.white);
			_txt_c_vendedor.setHorizontalAlignment(JTextField.RIGHT);
			_txt_c_vendedor.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_c_vendedor;
	}


	/**
	 * This method initializes _txt_c_total	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_c_total() {
		if (_txt_c_total == null) {
			_txt_c_total = new JTextField();
			_txt_c_total.setBounds(new Rectangle(887, 233, 102, 18));
			_txt_c_total.setBackground(Color.black);
			_txt_c_total.setHorizontalAlignment(JTextField.RIGHT);
			_txt_c_total.setForeground(Color.white);
			_txt_c_total.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_c_total;
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
			//jPanel4.setBounds(new Rectangle(4, 239, 1009, 456));
			jPanel4.setBounds(new Rectangle(0, 0, 1009, 456));
			jPanel4.add(getJPanel(), null);
			jPanel4.add(getJPanel1(), null);
		}
		return jPanel4;
	}


	/**
	 * This method initializes _txt_idpedido	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idpedido() {
		if (_txt_idpedido == null) {
			_txt_idpedido = new JTextField();
			_txt_idpedido.setToolTipText("Ingrese Codigo de Pedido y Presione F5");
			_txt_idpedido.setBounds(new Rectangle(178, 44, 86, 18));
			_txt_idpedido.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_idpedido;
	}


	/**
	 * This method initializes _txt_idvendedor	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idvendedor() {
		if (_txt_idvendedor == null) {
			_txt_idvendedor = new JTextField();
			_txt_idvendedor.setToolTipText("Ingrese Codigo de Vendedor y Presione F5");
			_txt_idvendedor.setBounds(new Rectangle(275, 44, 97, 18));
			_txt_idvendedor.setFont(new Font("Dialog", Font.PLAIN, 10));
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
			_txt_vendedor_descripcion.setToolTipText("Ingrese Nombre de Vendedor y Presione F5");
			_txt_vendedor_descripcion.setBounds(new Rectangle(274, 23, 99, 18));
			_txt_vendedor_descripcion.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_vendedor_descripcion;
	}


	/**
	 * This method initializes _txt_idarticulo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idarticulo() {
		if (_txt_idarticulo == null) {
			_txt_idarticulo = new JTextField();
			_txt_idarticulo.setToolTipText("Ingrese Codigo de Articulo y Presione F5");
			_txt_idarticulo.setBounds(new Rectangle(489, 44, 101, 18));
			_txt_idarticulo.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_idarticulo;
	}


	/**
	 * This method initializes _txt_idarticulo_descripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idarticulo_descripcion() {
		if (_txt_idarticulo_descripcion == null) {
			_txt_idarticulo_descripcion = new JTextField();
			_txt_idarticulo_descripcion.setToolTipText("Ingrese Descripcion de articulo y presione F5");
			_txt_idarticulo_descripcion.setBounds(new Rectangle(488, 23, 103, 18));
			_txt_idarticulo_descripcion.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_idarticulo_descripcion;
	}


	/**
	 * This method initializes _txt_idarticulo_linea	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idarticulo_linea() {
		if (_txt_idarticulo_linea == null) {
			_txt_idarticulo_linea = new JTextField();
			_txt_idarticulo_linea.setToolTipText("Ingrese Nombre de Linea y Presione F5");
			_txt_idarticulo_linea.setBounds(new Rectangle(597, 23, 151, 18));
			_txt_idarticulo_linea.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_idarticulo_linea;
	}


	/**
	 * This method initializes jTable11	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getJTable11() {
		if (jTable1 == null) {
			jTable1 = new JTable();
		}
		return jTable1;
	}


	/**
	 * This method initializes _chk_utiliza_calendario	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_utiliza_calendario() {
		if (_chk_utiliza_calendario == null) {
			_chk_utiliza_calendario = new JCheckBox();
			_chk_utiliza_calendario.setSelected(true);
			_chk_utiliza_calendario.setFont(new Font("Dialog", Font.BOLD, 10));
			_chk_utiliza_calendario.setBounds(new Rectangle(761, 2, 194, 14));
			_chk_utiliza_calendario.setText("Utiliza Calendario");
		}
		return _chk_utiliza_calendario;
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
	 * This method initializes _btn_consultar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_consultar() {
		if (_btn_consultar == null) {
			_btn_consultar = new JButton();
			_btn_consultar.setIcon(new ImageIcon(getClass().getResource("/icons/stock_hyperlink-internet-search.png")));
			_btn_consultar.setToolTipText("Consultar Articulo");
		}
		return _btn_consultar;
	}


	/**
	 * This method initializes _lst_estado	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_estado() {
		if (_lst_estado == null) {
			_lst_estado = new JComboBox();
			_lst_estado.setFont(new Font("Dialog", Font.BOLD, 8));
			_lst_estado.setBounds(new Rectangle(73, 69, 100, 16));
		}
		return _lst_estado;
	}


	/**
	 * This method initializes _chk_eliminados	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_eliminados() {
		if (_chk_eliminados == null) {
			_chk_eliminados = new JCheckBox();
			_chk_eliminados.setEnabled(false);
			_chk_eliminados.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_eliminados.setBounds(new Rectangle(176, 70, 146, 14));
			_chk_eliminados.setText("mostrar eliminados");
		}
		return _chk_eliminados;
	}


	/**
	 * This method initializes jPanel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(376, 4, 89, 17));
			jLabel4.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel4.setText("idcreador");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(333, 104, 93, 14));
			jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel3.setText("Remito");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(5, 103, 72, 16));
			jLabel2.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel2.setText("informacion");
			jPanel2 = new JPanel();
			jPanel2.setLayout(null);
			jPanel2.setBounds(new Rectangle(0, 0, 1004, 180));
			jPanel2.add(jLabel8, null);
			jPanel2.add(jLabel10, null);
			jPanel2.add(jLabel, null);
			jPanel2.add(get_txt_cliente_descripcion(), null);
			jPanel2.add(get_txt_idcliente(), null);
			jPanel2.add(get_lst_estado(), null);
			jPanel2.add(jLabel17, null);
			jPanel2.add(get_chk_eliminados(), null);
			jPanel2.add(get_txt_idpedido(), null);
			jPanel2.add(get_txt_pedido_descripcion(), null);
			jPanel2.add(jLabel19, null);
			jPanel2.add(jLabel7, null);
			jPanel2.add(get_txt_vendedor_descripcion(), null);
			jPanel2.add(get_txt_idvendedor(), null);
			jPanel2.add(jLabel20, null);
			jPanel2.add(get_txt_idarticulo_descripcion(), null);
			jPanel2.add(get_txt_idarticulo(), null);
			jPanel2.add(jLabel23, null);
			jPanel2.add(get_txt_idarticulo_linea(), null);
			jPanel2.add(get_chk_estado(), null);
			jPanel2.add(get_chk_utiliza_calendario(), null);
			jPanel2.add(getJPanel3(), null);
			jPanel2.add(jLabel2, null);
			jPanel2.add(get_txt_pedido_informacion(), null);
			jPanel2.add(jLabel3, null);
			jPanel2.add(get_txt_pedido_remito(), null);
			jPanel2.add(get_txt_idcreador(), null);
			jPanel2.add(get_txt_creador(), null);
			jPanel2.add(jLabel4, null);
		}
		return jPanel2;
	}

	public JXLayer<JComponent> getJXLayerUI(){
		JPanel panel = this.getJPanel4();
		
		JXLayer<JComponent> l = new JXLayer<JComponent>(panel, this.getLockableUI());
		l.setBounds(new Rectangle(4, 239, 1004, 546));
		
		
		return l;
		
	}
	
	public LockableUI getLockableUI(){
		if (lockableUI==null){
			lockableUI= new LockableUI();        
			BoxBlurFilter blur=new BoxBlurFilter(2,2,2);
			BufferedImageOpEffect effect = new BufferedImageOpEffect(blur);
			lockableUI.setLockedEffects(effect);
			
			
			lockableUI.setLocked(true);
		}
		return lockableUI; 

	}

	public JXLayer<JComponent> getJXLayerUI2(){
		JPanel panel = this.getJPanel2();
		
		JXLayer<JComponent> l2 = new JXLayer<JComponent>(panel, this.getLockableUI2());
		l2.setBounds(new Rectangle(0, 32, 1004, 180));
		
		
		
		return l2;
		
	}
	
	public LockableUI getLockableUI2(){
		if (lockableUI2==null){
			lockableUI2= new LockableUI();        
			BoxBlurFilter blur=new BoxBlurFilter(2,2,2);
			BufferedImageOpEffect effect = new BufferedImageOpEffect(blur);
			lockableUI2.setLockedEffects(effect);
			
			
			lockableUI2.setLocked(true);
		}
		return lockableUI2; 

	}


	/**
	 * This method initializes jTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	public JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.setBounds(new Rectangle(1, 1, 1000, 180));
			jTabbedPane.addTab("Nuevos", null, getJScrollPane(), null);
			jTabbedPane.addTab("Seguimiento", null, getJScrollPane4(), null);
		}
		return jTabbedPane;
	}


	/**
	 * This method initializes jTabbedPane1	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJTabbedPane1() {
		if (jTabbedPane1 == null) {
			jTabbedPane1 = new JTabbedPane();
			jTabbedPane1.setBounds(new Rectangle(15, 85, 975, 148));
			jTabbedPane1.addTab("Articulos", null, getJScrollPane2(), null);
			jTabbedPane1.addTab("Informacion", null, getJPanel5(), null);
		}
		return jTabbedPane1;
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
			jPanel5.add(getJScrollPane1(), null);
			
		}
		return jPanel5;
	}


	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setBounds(new Rectangle(14, 12, 569, 97));
			jScrollPane1.setViewportView(get_txt_informacion());
		}
		return jScrollPane1;
	}


	/**
	 * This method initializes _txt_informacion	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea get_txt_informacion() {
		if (_txt_informacion == null) {
			_txt_informacion = new JTextArea();
			_txt_informacion.setLineWrap(true);
			_txt_informacion.setEditable(false);
			_txt_informacion.setWrapStyleWord(true);
		}
		return _txt_informacion;
	}




	/**
	 * This method initializes jScrollPane4	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane4() {
		if (jScrollPane4 == null) {
			jScrollPane4 = new JScrollPane();
			jScrollPane4.setViewportView(getJTable3());
		}
		return jScrollPane4;
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
		this.jScrollPane4.setViewportView(jTable3);
	}


	/**
	 * This method initializes _chk_estado	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_estado() {
		if (_chk_estado == null) {
			_chk_estado = new JCheckBox();
			_chk_estado.setText("Mostrar Estado");
			_chk_estado.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_estado.setBounds(new Rectangle(328, 68, 135, 18));
		}
		return _chk_estado;
	}


	/**
	 * This method initializes _txt_pedido_informacion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_pedido_informacion() {
		if (_txt_pedido_informacion == null) {
			_txt_pedido_informacion = new JTextField();
			_txt_pedido_informacion.setBounds(new Rectangle(80, 102, 247, 19));
			_txt_pedido_informacion.setToolTipText("Informacion Adicional Contenida en un Pedido");
		}
		return _txt_pedido_informacion;
	}


	/**
	 * This method initializes _txt_pedido_remito	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_pedido_remito() {
		if (_txt_pedido_remito == null) {
			_txt_pedido_remito = new JTextField();
			_txt_pedido_remito.setBounds(new Rectangle(434, 102, 156, 17));
			_txt_pedido_remito.setToolTipText("Numero de Remito que Genero un Pedido");
		}
		return _txt_pedido_remito;
	}


	/**
	 * This method initializes _txt_idcreador	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idcreador() {
		if (_txt_idcreador == null) {
			_txt_idcreador = new JTextField();
			_txt_idcreador.setBounds(new Rectangle(379, 44, 106, 18));
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
			_txt_creador.setBounds(new Rectangle(379, 23, 106, 18));
			_txt_creador.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_creador;
	}
}  //  @jve:decl-index=0:visual-constraint="9,6"
