package aplicacion.inventario.control.gui;

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
	private JLabel jLabel = null;
	private JTextField _txt_idcontrol = null;
	private JButton _btn_buscar_idcontrol = null;
	private JLabel jLabel1 = null;
	private JTextField _txt_linea = null;
	private JLabel jLabel3 = null;
	private JTextField _txt_fecha = null;
	private JButton _btn_fecha = null;
	private JLabel jLabel4 = null;
	private JTextField _txt_idvendedor = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private JTextField _txt_control_estado = null;
	private JButton _btn_cargar = null;
	private JTextField _txt_cantidad_diferencia = null;
	private JTextField _txt_items_sistema = null;
	private JTextField _txt_cantidad_sistema = null;
	private JTextField _txt_cantidad_control = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel5 = null;
	private JLabel jLabel6 = null;
	private JLabel jLabel7 = null;
	private JLabel jLabel8 = null;
	private JTextField _txt_items_control = null;
	private JTextField _txt_items_diferencia = null;
	private JLabel jLabel9 = null;
	private JTextField _txt_valor_sistema = null;
	private JTextField _txt_valor_control = null;
	private JTextField _txt_valor_diferencia = null;
	private JButton _btn_aplicar_stock = null;
	private JButton _btn_eliminar_stock = null;
	private JProgressBar jProgressBar = null;
	private JButton _btn_cancel_task = null;
	private JButton _btn_imprimir = null;
	private JButton _btn_etiquetas = null;
	private JLabel jLabel10 = null;
	private JTextField _txt_iddeposito = null;
	private JButton _btn_buscar_deposito = null;
	private JTextField _txt_deposito_descripcion = null;
	private JLabel jLabel11 = null;
	private JTextField _txt_fecha_stock = null;
	private JButton _btn_buscar_fecha_stock = null;
	private JButton _btn_nuevo = null;
	private JTextField _txt_vendedor_descripcion = null;
	private JLabel jLabel12 = null;
	private JTextField _txt_etiquetas = null;
	private JTextField _txt_idmovimiento = null;
	private JScrollPane jScrollPane1 = null;
	private JTable jTable1 = null;
	private JLabel jLabel13 = null;
	private JTextField _txt_articulo_actualizacion = null;
	private JTextField _txt_articulo = null;
	private JTextField _txt_articulo_pedido = null;
	private JTextField _txt_articulo_linea = null;
	private JTextField _txt_articulo_bloqueado = null;
	private JTextField _txt_articulo_descripcion = null;
	private JTextField _txt_articulo_stock = null;
	private JTextField _txt_articulo_desde = null;
	private JLabel jLabel15 = null;
	private JTextField _txt_articulo_hasta = null;
	private JCheckBox _chk_punto_control = null;
	private JToolBar jToolBar1 = null;
	private JButton _btn_completar = null;
	private JCheckBox _chk_free = null;
	private JButton _btn_importar = null;
	private JButton _btn_check = null;
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
		this.setTitle("Control de Stock");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel15 = new JLabel();
			jLabel15.setBounds(new Rectangle(219, 112, 76, 17));
			jLabel15.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel15.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel15.setText("Hasta");
			jLabel13 = new JLabel();
			jLabel13.setBounds(new Rectangle(14, 391, 231, 18));
			jLabel13.setText("Cambios Producidos Recientemente");
			jLabel12 = new JLabel();
			jLabel12.setBounds(new Rectangle(697, 636, 104, 14));
			jLabel12.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel12.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel12.setText("Etiquetas");
			jLabel11 = new JLabel();
			jLabel11.setBounds(new Rectangle(761, 61, 70, 16));
			jLabel11.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel11.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel11.setText("Fecha Stock");
			jLabel10 = new JLabel();
			jLabel10.setBounds(new Rectangle(11, 65, 72, 16));
			jLabel10.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel10.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel10.setText("iddeposito");
			jLabel9 = new JLabel();
			jLabel9.setText("Valor");
			jLabel9.setSize(new Dimension(110, 18));
			jLabel9.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel9.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel9.setLocation(new Point(467, 618));
			jLabel8 = new JLabel();
			jLabel8.setText("items");
			jLabel8.setSize(new Dimension(110, 18));
			jLabel8.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel8.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel8.setLocation(new Point(466, 593));
			jLabel7 = new JLabel();
			jLabel7.setText("diferencia");
			jLabel7.setSize(new Dimension(110, 18));
			jLabel7.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel7.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel7.setLocation(new Point(808, 546));
			jLabel6 = new JLabel();
			jLabel6.setText("control");
			jLabel6.setSize(new Dimension(110, 18));
			jLabel6.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel6.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel6.setLocation(new Point(693, 546));
			jLabel5 = new JLabel();
			jLabel5.setText("sistema");
			jLabel5.setSize(new Dimension(110, 18));
			jLabel5.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel5.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel5.setLocation(new Point(579, 546));
			jLabel2 = new JLabel();
			jLabel2.setText("cantidad");
			jLabel2.setSize(new Dimension(110, 18));
			jLabel2.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2.setLocation(new Point(466, 570));
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(11, 138, 71, 17));
			jLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel4.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel4.setText("idusuario");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(777, 37, 54, 15));
			jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel3.setText("Fecha");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(10, 87, 74, 17));
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setText("Linea");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(11, 44, 73, 17));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setText("idcontrol");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(get_txt_idcontrol(), null);
			jContentPane.add(get_btn_buscar_idcontrol(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(get_txt_linea(), null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(get_txt_fecha(), null);
			jContentPane.add(get_btn_fecha(), null);
			jContentPane.add(jLabel4, null);
			jContentPane.add(get_txt_idvendedor(), null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(get_txt_control_estado(), null);
			jContentPane.add(get_btn_cargar(), null);
			jContentPane.add(get_txt_cantidad_diferencia(), null);
			jContentPane.add(get_txt_items_sistema(), null);
			jContentPane.add(get_txt_cantidad_sistema(), null);
			jContentPane.add(get_txt_cantidad_control(), null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(jLabel5, null);
			jContentPane.add(jLabel6, null);
			jContentPane.add(jLabel7, null);
			jContentPane.add(jLabel8, null);
			jContentPane.add(get_txt_items_control(), null);
			jContentPane.add(get_txt_items_diferencia(), null);
			jContentPane.add(jLabel9, null);
			jContentPane.add(get_txt_valor_sistema(), null);
			jContentPane.add(get_txt_valor_control(), null);
			jContentPane.add(get_txt_valor_diferencia(), null);
			jContentPane.add(getJProgressBar(), null);
			jContentPane.add(get_btn_cancel_task(), null);
			jContentPane.add(jLabel10, null);
			jContentPane.add(get_txt_iddeposito(), null);
			jContentPane.add(get_btn_buscar_deposito(), null);
			jContentPane.add(get_txt_deposito_descripcion(), null);
			jContentPane.add(jLabel11, null);
			jContentPane.add(get_txt_fecha_stock(), null);
			jContentPane.add(get_btn_buscar_fecha_stock(), null);
			jContentPane.add(get_txt_vendedor_descripcion(), null);
			jContentPane.add(jLabel12, null);
			jContentPane.add(get_txt_etiquetas(), null);
			jContentPane.add(get_txt_idmovimiento(), null);
			jContentPane.add(getJScrollPane1(), null);
			jContentPane.add(jLabel13, null);
			jContentPane.add(get_txt_articulo(), null);
			jContentPane.add(get_txt_articulo_linea(),null);
			jContentPane.add(get_txt_articulo_stock(),null);
			jContentPane.add(get_txt_articulo_bloqueado(),null);
			jContentPane.add(get_txt_articulo_descripcion(),null);
			jContentPane.add(get_txt_articulo_pedido(),null);
			jContentPane.add(get_txt_articulo_stock(),null);
			jContentPane.add(get_txt_articulo_actualizacion(),null);
			jContentPane.add(get_txt_articulo_desde(), null);
			jContentPane.add(jLabel15, null);
			jContentPane.add(get_txt_articulo_hasta(), null);
			jContentPane.add(get_chk_punto_control(), null);
			jContentPane.add(getJToolBar1(), null);
			jContentPane.add(get_chk_free(), null);
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
			jToolBar.setFloatable(false);
			jToolBar.setLocation(new Point(2, 3));
			jToolBar.setSize(new Dimension(986, 24));
			jToolBar.add(get_btn_cancelar());
			jToolBar.add(get_btn_nuevo());
			jToolBar.add(get_btn_guardar());
			jToolBar.add(get_btn_eliminar());
			jToolBar.addSeparator(new Dimension(60,20));
			jToolBar.add(get_btn_aplicar_stock());
			jToolBar.add(get_btn_imprimir());
			jToolBar.add(get_btn_importar());
			jToolBar.add(get_btn_check());
			jToolBar.add(get_btn_etiquetas());
			jToolBar.addSeparator();
			jToolBar.add(get_btn_eliminar_stock());
			
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
			_btn_guardar.setToolTipText("Guardar Control");
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
			_btn_eliminar.setToolTipText("Elimimnar Control");
		}
		return _btn_eliminar;
	}

	/**
	 * This method initializes _txt_idcontrol	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idcontrol() {
		if (_txt_idcontrol == null) {
			_txt_idcontrol = new JTextField();
			_txt_idcontrol.setBounds(new Rectangle(87, 44, 110, 18));
			_txt_idcontrol.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_idcontrol;
	}

	/**
	 * This method initializes _btn_buscar_idcontrol	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton get_btn_buscar_idcontrol() {
		if (_btn_buscar_idcontrol == null) {
			_btn_buscar_idcontrol = new JButton();
			_btn_buscar_idcontrol.setBounds(new Rectangle(199, 43, 22, 18));
			_btn_buscar_idcontrol.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-searchtool.png")));
		}
		return _btn_buscar_idcontrol;
	}

	/**
	 * This method initializes _txt_linea	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_linea() {
		if (_txt_linea == null) {
			_txt_linea = new JTextField();
			_txt_linea.setBounds(new Rectangle(88, 87, 332, 18));
			_txt_linea.setEditable(true);
			_txt_linea.setHorizontalAlignment(JTextField.LEFT);
			_txt_linea.setEnabled(false);
			_txt_linea.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_linea;
	}

	/**
	 * This method initializes _txt_fecha	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_fecha() {
		if (_txt_fecha == null) {
			_txt_fecha = new JTextField();
			_txt_fecha.setBounds(new Rectangle(838, 36, 116, 17));
			_txt_fecha.setEditable(false);
			_txt_fecha.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_fecha;
	}

	/**
	 * This method initializes _btn_fecha	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton get_btn_fecha() {
		if (_btn_fecha == null) {
			_btn_fecha = new JButton();
			_btn_fecha.setBounds(new Rectangle(958, 34, 26, 20));
			_btn_fecha.setIcon(new ImageIcon(getClass().getResource("/icons/calendar-16.gif")));
		}
		return _btn_fecha;
	}

	/**
	 * This method initializes _txt_idvendedor	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idvendedor() {
		if (_txt_idvendedor == null) {
			_txt_idvendedor = new JTextField();
			_txt_idvendedor.setBounds(new Rectangle(88, 136, 62, 18));
			_txt_idvendedor.setEditable(true);
			_txt_idvendedor.setEnabled(false);
			_txt_idvendedor.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_idvendedor;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(13, 201, 958, 166));
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
	 * This method initializes _txt_control_estado	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_control_estado() {
		if (_txt_control_estado == null) {
			_txt_control_estado = new JTextField();
			_txt_control_estado.setBounds(new Rectangle(247, 44, 172, 18));
			_txt_control_estado.setEditable(false);
			_txt_control_estado.setHorizontalAlignment(JTextField.LEFT);
			_txt_control_estado.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_control_estado;
	}

	/**
	 * This method initializes _btn_cargar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cargar() {
		if (_btn_cargar == null) {
			_btn_cargar = new JButton();
			_btn_cargar.setBounds(new Rectangle(224, 43, 20, 18));
			_btn_cargar.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-refresh.png")));
		}
		return _btn_cargar;
	}

	/**
	 * This method initializes _txt_cantidad_diferencia	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_cantidad_diferencia() {
		if (_txt_cantidad_diferencia == null) {
			_txt_cantidad_diferencia = new JTextField();
			_txt_cantidad_diferencia.setHorizontalAlignment(JTextField.RIGHT);
			_txt_cantidad_diferencia.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_cantidad_diferencia.setLocation(new Point(808, 570));
			_txt_cantidad_diferencia.setSize(new Dimension(110, 18));
			_txt_cantidad_diferencia.setEditable(false);
		}
		return _txt_cantidad_diferencia;
	}

	/**
	 * This method initializes _txt_items_sistema	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_items_sistema() {
		if (_txt_items_sistema == null) {
			_txt_items_sistema = new JTextField();
			_txt_items_sistema.setHorizontalAlignment(JTextField.RIGHT);
			_txt_items_sistema.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_items_sistema.setLocation(new Point(580, 591));
			_txt_items_sistema.setSize(new Dimension(110, 18));
			_txt_items_sistema.setEditable(false);
		}
		return _txt_items_sistema;
	}

	/**
	 * This method initializes _txt_cantidad_sistema	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_cantidad_sistema() {
		if (_txt_cantidad_sistema == null) {
			_txt_cantidad_sistema = new JTextField();
			_txt_cantidad_sistema.setEditable(false);
			_txt_cantidad_sistema.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_cantidad_sistema.setLocation(new Point(580, 570));
			_txt_cantidad_sistema.setSize(new Dimension(110, 18));
			_txt_cantidad_sistema.setHorizontalAlignment(JTextField.RIGHT);
		}
		return _txt_cantidad_sistema;
	}

	/**
	 * This method initializes _txt_cantidad_control	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_cantidad_control() {
		if (_txt_cantidad_control == null) {
			_txt_cantidad_control = new JTextField();
			_txt_cantidad_control.setHorizontalAlignment(JTextField.RIGHT);
			_txt_cantidad_control.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_cantidad_control.setLocation(new Point(694, 570));
			_txt_cantidad_control.setSize(new Dimension(110, 18));
			_txt_cantidad_control.setEditable(false);
		}
		return _txt_cantidad_control;
	}

	/**
	 * This method initializes _txt_items_control	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_items_control() {
		if (_txt_items_control == null) {
			_txt_items_control = new JTextField();
			_txt_items_control.setHorizontalAlignment(JTextField.RIGHT);
			_txt_items_control.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_items_control.setLocation(new Point(694, 591));
			_txt_items_control.setSize(new Dimension(110, 18));
			_txt_items_control.setEditable(false);
		}
		return _txt_items_control;
	}

	/**
	 * This method initializes _txt_items_diferencia	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_items_diferencia() {
		if (_txt_items_diferencia == null) {
			_txt_items_diferencia = new JTextField();
			_txt_items_diferencia.setHorizontalAlignment(JTextField.RIGHT);
			_txt_items_diferencia.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_items_diferencia.setLocation(new Point(808, 591));
			_txt_items_diferencia.setSize(new Dimension(110, 18));
			_txt_items_diferencia.setEditable(false);
		}
		return _txt_items_diferencia;
	}

	/**
	 * This method initializes _txt_valor_sistema	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_valor_sistema() {
		if (_txt_valor_sistema == null) {
			_txt_valor_sistema = new JTextField();
			_txt_valor_sistema.setHorizontalAlignment(JTextField.RIGHT);
			_txt_valor_sistema.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_valor_sistema.setLocation(new Point(580, 612));
			_txt_valor_sistema.setSize(new Dimension(110, 18));
			_txt_valor_sistema.setEditable(false);
		}
		return _txt_valor_sistema;
	}

	/**
	 * This method initializes _txt_valor_control	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_valor_control() {
		if (_txt_valor_control == null) {
			_txt_valor_control = new JTextField();
			_txt_valor_control.setHorizontalAlignment(JTextField.RIGHT);
			_txt_valor_control.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_valor_control.setLocation(new Point(694, 613));
			_txt_valor_control.setSize(new Dimension(110, 18));
			_txt_valor_control.setEditable(false);
		}
		return _txt_valor_control;
	}

	/**
	 * This method initializes _txt_valor_diferencia	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_valor_diferencia() {
		if (_txt_valor_diferencia == null) {
			_txt_valor_diferencia = new JTextField();
			_txt_valor_diferencia.setHorizontalAlignment(JTextField.RIGHT);
			_txt_valor_diferencia.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_valor_diferencia.setLocation(new Point(808, 613));
			_txt_valor_diferencia.setSize(new Dimension(110, 18));
			_txt_valor_diferencia.setEditable(false);
		}
		return _txt_valor_diferencia;
	}

	/**
	 * This method initializes _btn_aplicar_stock	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_aplicar_stock() {
		if (_btn_aplicar_stock == null) {
			_btn_aplicar_stock = new JButton();
			_btn_aplicar_stock.setIcon(new ImageIcon(getClass().getResource("/icons/stock_mail-send-receive.png")));
			_btn_aplicar_stock.setToolTipText("Aplicar los cambios al stock");
		}
		return _btn_aplicar_stock;
	}

	/**
	 * This method initializes _btn_eliminar_stock	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_eliminar_stock() {
		if (_btn_eliminar_stock == null) {
			_btn_eliminar_stock = new JButton();
			_btn_eliminar_stock.setIcon(new ImageIcon(getClass().getResource("/icons/process-stop.png")));
			_btn_eliminar_stock.setToolTipText("Eliminar Cambios de Stock Producidos");
		}
		return _btn_eliminar_stock;
	}

	/**
	 * This method initializes jProgressBar	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	public JProgressBar getJProgressBar() {
		if (jProgressBar == null) {
			jProgressBar = new JProgressBar();
			jProgressBar.setBounds(new Rectangle(13, 159, 928, 14));
		}
		return jProgressBar;
	}

	/**
	 * This method initializes _btn_cancel_task	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cancel_task() {
		if (_btn_cancel_task == null) {
			_btn_cancel_task = new JButton();
			_btn_cancel_task.setLocation(new Point(946, 128));
			_btn_cancel_task.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-stop.png")));
			_btn_cancel_task.setToolTipText("Cancelar Operacion en Progreso");
			_btn_cancel_task.setSize(new Dimension(20, 18));
		}
		return _btn_cancel_task;
	}

	/**
	 * This method initializes _btn_imprimir	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_imprimir() {
		if (_btn_imprimir == null) {
			_btn_imprimir = new JButton();
			_btn_imprimir.setIcon(new ImageIcon(getClass().getResource("/icons/document-print.png")));
			_btn_imprimir.setToolTipText("Imprimir Ajustes del Control");
		}
		return _btn_imprimir;
	}

	/**
	 * This method initializes _btn_etiquetas	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_etiquetas() {
		if (_btn_etiquetas == null) {
			_btn_etiquetas = new JButton();
			_btn_etiquetas.setIcon(new ImageIcon(getClass().getResource("/icons/emblem-sales-24.png")));
		}
		return _btn_etiquetas;
	}

	/**
	 * This method initializes _txt_iddeposito	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_iddeposito() {
		if (_txt_iddeposito == null) {
			_txt_iddeposito = new JTextField();
			_txt_iddeposito.setBounds(new Rectangle(88, 65, 66, 18));
			_txt_iddeposito.setEnabled(false);
		}
		return _txt_iddeposito;
	}

	/**
	 * This method initializes _btn_buscar_deposito	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_deposito() {
		if (_btn_buscar_deposito == null) {
			_btn_buscar_deposito = new JButton();
			_btn_buscar_deposito.setBounds(new Rectangle(158, 65, 19, 18));
			_btn_buscar_deposito.setIcon(new ImageIcon(getClass().getResource("/icons/kfind.png")));
		}
		return _btn_buscar_deposito;
	}

	/**
	 * This method initializes _txt_deposito_descripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_deposito_descripcion() {
		if (_txt_deposito_descripcion == null) {
			_txt_deposito_descripcion = new JTextField();
			_txt_deposito_descripcion.setBounds(new Rectangle(180, 65, 239, 18));
			_txt_deposito_descripcion.setHorizontalAlignment(JTextField.LEFT);
			_txt_deposito_descripcion.setEditable(false);
		}
		return _txt_deposito_descripcion;
	}

	/**
	 * This method initializes _txt_fecha_stock	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_fecha_stock() {
		if (_txt_fecha_stock == null) {
			_txt_fecha_stock = new JTextField();
			_txt_fecha_stock.setBounds(new Rectangle(838, 60, 115, 17));
			_txt_fecha_stock.setToolTipText("Fecha Limite de Movimientos de Stock");
			_txt_fecha_stock.setText("");
		}
		return _txt_fecha_stock;
	}

	/**
	 * This method initializes _btn_buscar_fecha_stock	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_fecha_stock() {
		if (_btn_buscar_fecha_stock == null) {
			_btn_buscar_fecha_stock = new JButton();
			_btn_buscar_fecha_stock.setIcon(new ImageIcon(getClass().getResource("/icons/calendar-16.gif")));
			_btn_buscar_fecha_stock.setSize(new Dimension(26, 20));
			_btn_buscar_fecha_stock.setLocation(new Point(959, 58));
		}
		return _btn_buscar_fecha_stock;
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
			_btn_nuevo.setToolTipText("Nuevo Control");
		}
		return _btn_nuevo;
	}

	/**
	 * This method initializes _txt_vendedor_descripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_vendedor_descripcion() {
		if (_txt_vendedor_descripcion == null) {
			_txt_vendedor_descripcion = new JTextField();
			_txt_vendedor_descripcion.setEditable(false);
			_txt_vendedor_descripcion.setSize(new Dimension(172, 18));
			_txt_vendedor_descripcion.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_vendedor_descripcion.setHorizontalAlignment(JTextField.LEFT);
			_txt_vendedor_descripcion.setLocation(new Point(154, 136));
		}
		return _txt_vendedor_descripcion;
	}

	/**
	 * This method initializes _txt_etiquetas	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_etiquetas() {
		if (_txt_etiquetas == null) {
			_txt_etiquetas = new JTextField();
			_txt_etiquetas.setBounds(new Rectangle(808, 635, 110, 18));
			_txt_etiquetas.setEditable(false);
			_txt_etiquetas.setHorizontalAlignment(JTextField.RIGHT);
			_txt_etiquetas.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_etiquetas;
	}

	/**
	 * This method initializes _txt_idmovimiento	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idmovimiento() {
		if (_txt_idmovimiento == null) {
			_txt_idmovimiento = new JTextField();
			_txt_idmovimiento.setBounds(new Rectangle(536, 65, 117, 18));
			_txt_idmovimiento.setToolTipText("Movimiento de Stock");
			_txt_idmovimiento.setEditable(false);
			_txt_idmovimiento.setHorizontalAlignment(JTextField.RIGHT);
			_txt_idmovimiento.setEnabled(true);
		}
		return _txt_idmovimiento;
	}

	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setBounds(new Rectangle(13, 410, 956, 120));
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
		this.jScrollPane1.setViewportView(table);
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
			_txt_articulo_actualizacion.setLocation(new Point(419, 370));
			_txt_articulo_actualizacion.setSize(new Dimension(101, 18));
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
			_txt_articulo.setBounds(new Rectangle(15, 370, 120, 18));
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
			_txt_articulo_bloqueado.setBounds(new Rectangle(699, 370, 105, 18));
			_txt_articulo_bloqueado.setEnabled(true);
			_txt_articulo_bloqueado.setFont(new Font("Dialog", Font.PLAIN, 8));
			_txt_articulo_bloqueado.setEditable(false);
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
			_txt_articulo_linea.setBounds(new Rectangle(521, 370, 176, 18));
			_txt_articulo_linea.setEditable(false);
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
			_txt_articulo_descripcion.setBounds(new Rectangle(139, 370, 234, 18));
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
			_txt_articulo_stock.setBounds(new Rectangle(374, 370, 43, 18));
			_txt_articulo_stock.setFont(new Font("Dialog", Font.PLAIN, 10));
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
			_txt_articulo_pedido.setSize(new Dimension(49, 18));
			_txt_articulo_pedido.setLocation(new Point(808, 370));
			_txt_articulo_pedido.setHorizontalAlignment(JTextField.RIGHT);
		}
		return _txt_articulo_pedido;
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
			_txt_articulo_desde.setSize(new Dimension(120, 18));
			_txt_articulo_desde.setEnabled(false);
			_txt_articulo_desde.setLocation(new Point(88, 112));
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
			_txt_articulo_hasta.setSize(new Dimension(120, 18));
			_txt_articulo_hasta.setEnabled(false);
			_txt_articulo_hasta.setLocation(new Point(299, 112));
		}
		return _txt_articulo_hasta;
	}

	/**
	 * This method initializes _chk_punto_control	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_punto_control() {
		if (_chk_punto_control == null) {
			_chk_punto_control = new JCheckBox();
			_chk_punto_control.setBounds(new Rectangle(424, 67, 108, 15));
			_chk_punto_control.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_punto_control.setSelected(true);
			_chk_punto_control.setText("punto de control");
		}
		return _chk_punto_control;
	}

	/**
	 * This method initializes jToolBar1	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar1() {
		if (jToolBar1 == null) {
			jToolBar1 = new JToolBar();
			jToolBar1.setBounds(new Rectangle(3, 177, 976, 19));
			jToolBar1.setFloatable(false);
			jToolBar1.add(get_btn_completar());
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
			_btn_completar.setIcon(new ImageIcon(getClass().getResource("/icons/bottom.png")));
			_btn_completar.setToolTipText("Autocompletar con Valores de Stock del Sistema");
		}
		return _btn_completar;
	}

	/**
	 * This method initializes _chk_free	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_free() {
		if (_chk_free == null) {
			_chk_free = new JCheckBox();
			_chk_free.setBounds(new Rectangle(10, 113, 76, 15));
			_chk_free.setSelected(false);
			_chk_free.setToolTipText("Define si el control es estructurado o libre");
			_chk_free.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_free.setText("Articulo");
		}
		return _chk_free;
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
			_btn_importar.setToolTipText("Importar");
		}
		return _btn_importar;
	}

	/**
	 * This method initializes _btn_check	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_check() {
		if (_btn_check == null) {
			_btn_check = new JButton();
			_btn_check.setIcon(new ImageIcon(getClass().getResource("/icons/emblem-default-16.png")));
			_btn_check.setToolTipText("Verificar");
		}
		return _btn_check;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
