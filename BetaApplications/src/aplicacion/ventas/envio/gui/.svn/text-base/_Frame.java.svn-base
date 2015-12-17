package aplicacion.ventas.envio.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JToolBar;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.Point;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JToolBar jToolBar = null;
	private JButton _btn_cancelar = null;
	private JButton _btn_salir = null;
	private JLabel jLabel1 = null;
	private JTextField _txt_fecha_desde = null;
	private JButton _btn_fecha_desde = null;
	private JButton _btn_cargar = null;
	private JLabel jLabel = null;
	private JTextField _txt_idpedido = null;
	private JTextField _txt_idcliente = null;
	private JTextField _txt_cliente_descripcion = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JLabel jLabel4 = null;
	private JComboBox _lst_condicion_de_venta = null;
	private JComboBox _lst_plazo_de_entrega = null;
	private JLabel jLabel5 = null;
	private JLabel jLabel6 = null;
	private JLabel jLabel7 = null;
	private JTextField _txt_domicilio = null;
	private JTextField _txt_idciudad = null;
	private JTextField _txt_total = null;
	private JButton _btn_error = null;
	private JLabel jLabel8 = null;
	private JComboBox _lst_mantenimiento = null;
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
		this.setSize(470, 365);
		this.setContentPane(getJContentPane());
		this.setTitle("Envio");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel8 = new JLabel();
			jLabel8.setBounds(new Rectangle(8, 256, 142, 20));
			jLabel8.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel8.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel8.setText("Mantenimiento de Oferta");
			jLabel7 = new JLabel();
			jLabel7.setBounds(new Rectangle(14, 190, 97, 23));
			jLabel7.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel7.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel7.setText("total");
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(15, 152, 96, 22));
			jLabel6.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel6.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel6.setText("Localidad");
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(14, 119, 97, 20));
			jLabel5.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel5.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel5.setText("Domicilio");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(15, 88, 95, 14));
			jLabel4.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel4.setText("Cliente");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(39, 285, 111, 19));
			jLabel3.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3.setText("Plazo de entrega");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(37, 226, 112, 21));
			jLabel2.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2.setText("Condicion de Venta");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(15, 59, 96, 19));
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setText("idpedido");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(232, 32, 79, 18));
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setHorizontalTextPosition(SwingConstants.RIGHT);
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setText("Fecha");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(get_txt_fecha_desde(), null);
			jContentPane.add(get_btn_fecha_desde(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(get_txt_idpedido(), null);
			jContentPane.add(get_txt_idcliente(), null);
			jContentPane.add(get_txt_cliente_descripcion(), null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(jLabel4, null);
			jContentPane.add(get_lst_condicion_de_venta(), null);
			jContentPane.add(get_lst_plazo_de_entrega(), null);
			jContentPane.add(jLabel5, null);
			jContentPane.add(jLabel6, null);
			jContentPane.add(jLabel7, null);
			jContentPane.add(get_txt_domicilio(), null);
			jContentPane.add(get_txt_idciudad(), null);
			jContentPane.add(get_txt_total(), null);
			jContentPane.add(jLabel8, null);
			jContentPane.add(get_lst_mantenimiento(), null);
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
			jToolBar.setBounds(new Rectangle(2, 2, 460, 24));
			jToolBar.setFloatable(false);
			jToolBar.add(get_btn_cancelar());
			jToolBar.add(get_btn_cargar());
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
			_btn_cancelar.setToolTipText("Cancelar");
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
			_btn_salir.setIcon(new ImageIcon(getClass().getResource("/icons/exit.png")));
			_btn_salir.setToolTipText("Salir");
		}
		return _btn_salir;
	}

	/**
	 * This method initializes _txt_fecha_desde	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_fecha_desde() {
		if (_txt_fecha_desde == null) {
			_txt_fecha_desde = new JTextField();
			_txt_fecha_desde.setBounds(new Rectangle(315, 33, 112, 17));
			_txt_fecha_desde.setHorizontalAlignment(JTextField.RIGHT);
			_txt_fecha_desde.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_fecha_desde;
	}

	/**
	 * This method initializes _btn_fecha_desde	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_fecha_desde() {
		if (_btn_fecha_desde == null) {
			_btn_fecha_desde = new JButton();
			_btn_fecha_desde.setBounds(new Rectangle(428, 32, 22, 19));
			_btn_fecha_desde.setIcon(new ImageIcon(getClass().getResource("/icons/calendar-16.gif")));
		}
		return _btn_fecha_desde;
	}

	/**
	 * This method initializes _btn_cargar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cargar() {
		if (_btn_cargar == null) {
			_btn_cargar = new JButton();
			_btn_cargar.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-print-preview.png")));
			_btn_cargar.setToolTipText("Mostrar Reporte");
		}
		return _btn_cargar;
	}

	/**
	 * This method initializes _txt_idpedido	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idpedido() {
		if (_txt_idpedido == null) {
			_txt_idpedido = new JTextField();
			_txt_idpedido.setBounds(new Rectangle(115, 60, 112, 18));
			_txt_idpedido.setHorizontalAlignment(JTextField.RIGHT);
			_txt_idpedido.setEditable(false);
			_txt_idpedido.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_idpedido;
	}

	/**
	 * This method initializes _txt_idcliente	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idcliente() {
		if (_txt_idcliente == null) {
			_txt_idcliente = new JTextField();
			_txt_idcliente.setBounds(new Rectangle(116, 83, 110, 20));
			_txt_idcliente.setHorizontalAlignment(JTextField.RIGHT);
			_txt_idcliente.setEditable(false);
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
			_txt_cliente_descripcion.setBounds(new Rectangle(230, 82, 221, 21));
			_txt_cliente_descripcion.setEditable(false);
			_txt_cliente_descripcion.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_cliente_descripcion;
	}

	/**
	 * This method initializes _lst_condicion_de_venta	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_condicion_de_venta() {
		if (_lst_condicion_de_venta == null) {
			_lst_condicion_de_venta = new JComboBox();
			_lst_condicion_de_venta.setFont(new Font("Dialog", Font.BOLD, 10));
			_lst_condicion_de_venta.setSize(new Dimension(280, 18));
			_lst_condicion_de_venta.setLocation(new Point(158, 227));
		}
		return _lst_condicion_de_venta;
	}

	/**
	 * This method initializes _lst_plazo_de_entrega	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_plazo_de_entrega() {
		if (_lst_plazo_de_entrega == null) {
			_lst_plazo_de_entrega = new JComboBox();
			_lst_plazo_de_entrega.setLocation(new Point(158, 285));
			_lst_plazo_de_entrega.setSize(new Dimension(280, 18));
		}
		return _lst_plazo_de_entrega;
	}

	/**
	 * This method initializes _txt_domicilio	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_domicilio() {
		if (_txt_domicilio == null) {
			_txt_domicilio = new JTextField();
			_txt_domicilio.setBounds(new Rectangle(118, 119, 272, 20));
			_txt_domicilio.setHorizontalAlignment(JTextField.RIGHT);
			_txt_domicilio.setEditable(false);
			_txt_domicilio.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_domicilio;
	}

	/**
	 * This method initializes _txt_idciudad	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idciudad() {
		if (_txt_idciudad == null) {
			_txt_idciudad = new JTextField();
			_txt_idciudad.setBounds(new Rectangle(119, 151, 107, 19));
			_txt_idciudad.setHorizontalAlignment(JTextField.RIGHT);
			_txt_idciudad.setEditable(false);
			_txt_idciudad.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_idciudad;
	}

	/**
	 * This method initializes _txt_total	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_total() {
		if (_txt_total == null) {
			_txt_total = new JTextField();
			_txt_total.setBounds(new Rectangle(118, 191, 108, 20));
			_txt_total.setHorizontalAlignment(JTextField.RIGHT);
			_txt_total.setEditable(false);
			_txt_total.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_total;
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
	 * This method initializes _lst_mantenimiento	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_mantenimiento() {
		if (_lst_mantenimiento == null) {
			_lst_mantenimiento = new JComboBox();
			_lst_mantenimiento.setLocation(new Point(158, 256));
			_lst_mantenimiento.setSize(new Dimension(280, 18));
		}
		return _lst_mantenimiento;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
