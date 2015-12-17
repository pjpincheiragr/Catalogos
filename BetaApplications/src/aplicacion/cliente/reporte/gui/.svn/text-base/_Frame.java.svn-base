package aplicacion.cliente.reporte.gui;

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

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JToolBar jToolBar = null;
	private JLabel jLabel = null;
	private JTextField _txt_idcliente = null;
	private JTextField _txt_cliente_descripcion = null;
	private JButton _btn_cancelar = null;
	private JButton _btn_salir = null;
	private JButton _btn_buscar_cliente = null;
	private JLabel jLabel1 = null;
	private JTextField _txt_fecha_desde = null;
	private JButton _btn_fecha_desde = null;
	private JTextField _txt_fecha_hasta = null;
	private JButton _btn_fecha_hasta = null;
	private JLabel jLabel2 = null;
	private JButton _btn_cargar = null;
	private JComboBox _lst_tipo = null;
	private JLabel jLabel3 = null;
	private JButton _btn_error = null;

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
		this.setSize(470, 201);
		this.setContentPane(getJContentPane());
		this.setTitle("Reporte Cliente");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(7, 38, 75, 17));
			jLabel3.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel3.setHorizontalTextPosition(SwingConstants.RIGHT);
			jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3.setText("Tipo");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(240, 86, 58, 19));
			jLabel2.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2.setText("Hasta");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(4, 88, 79, 18));
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setHorizontalTextPosition(SwingConstants.RIGHT);
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setText("Fecha");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(5, 65, 77, 15));
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
			jLabel.setText("Cliente");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(get_txt_idcliente(), null);
			jContentPane.add(get_txt_cliente_descripcion(), null);
			jContentPane.add(get_btn_buscar_cliente(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(get_txt_fecha_desde(), null);
			jContentPane.add(get_btn_fecha_desde(), null);
			jContentPane.add(get_txt_fecha_hasta(), null);
			jContentPane.add(get_btn_fecha_hasta(), null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(get_lst_tipo(), null);
			jContentPane.add(jLabel3, null);
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
	 * This method initializes _txt_idcliente	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idcliente() {
		if (_txt_idcliente == null) {
			_txt_idcliente = new JTextField();
			_txt_idcliente.setBounds(new Rectangle(87, 66, 113, 18));
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
			_txt_cliente_descripcion.setBounds(new Rectangle(225, 65, 230, 18));
			_txt_cliente_descripcion.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_cliente_descripcion.setEditable(false);
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
	 * This method initializes _btn_buscar_cliente	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_cliente() {
		if (_btn_buscar_cliente == null) {
			_btn_buscar_cliente = new JButton();
			_btn_buscar_cliente.setBounds(new Rectangle(200, 66, 21, 18));
			_btn_buscar_cliente.setToolTipText("Buscar Cliente");
			_btn_buscar_cliente.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-searchtool.png")));
		}
		return _btn_buscar_cliente;
	}

	/**
	 * This method initializes _txt_fecha_desde	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_fecha_desde() {
		if (_txt_fecha_desde == null) {
			_txt_fecha_desde = new JTextField();
			_txt_fecha_desde.setBounds(new Rectangle(87, 89, 112, 17));
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
			_btn_fecha_desde.setBounds(new Rectangle(200, 88, 22, 19));
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
			_txt_fecha_hasta.setBounds(new Rectangle(302, 88, 117, 17));
			_txt_fecha_hasta.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_fecha_hasta;
	}

	/**
	 * This method initializes _btn_fecha_hasta	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_fecha_hasta() {
		if (_btn_fecha_hasta == null) {
			_btn_fecha_hasta = new JButton();
			_btn_fecha_hasta.setBounds(new Rectangle(422, 87, 22, 19));
			_btn_fecha_hasta.setIcon(new ImageIcon(getClass().getResource("/icons/calendar-16.gif")));
		}
		return _btn_fecha_hasta;
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
	 * This method initializes _lst_tipo	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_tipo() {
		if (_lst_tipo == null) {
			_lst_tipo = new JComboBox();
			_lst_tipo.setBounds(new Rectangle(88, 40, 133, 17));
			_lst_tipo.setFont(new Font("Dialog", Font.BOLD, 10));
		}
		return _lst_tipo;
	}

	/**
	 * This method initializes _btn_error	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_error() {
		if (_btn_error == null) {
			_btn_error = new JButton();
			_btn_error.setToolTipText("Envio de Informacion/Error a Sistemas");
			_btn_error.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-media-record.png")));
		}
		return _btn_error;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
