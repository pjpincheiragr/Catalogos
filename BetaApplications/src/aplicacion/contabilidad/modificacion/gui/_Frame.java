package aplicacion.contabilidad.modificacion.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JToolBar;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Font;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JToolBar jJToolBarBar = null;
	private JButton _btn_cancelar = null;
	private JButton _btn_guardar = null;
	private JButton _btn_salir = null;
	private JLabel jLabel = null;
	private JComboBox _lst_tc = null;
	private JTextField _txt_sucursal = null;
	private JTextField _txt_numero = null;
	private JComboBox _list_letra = null;
	private JTextField _txt_fecha = null;
	private JTextField _txt_cuenta = null;
	private JTextField _txt_cuenta_descripcion = null;
	private JTextField _txt_importe = null;
	private JButton _btn_fecha = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JLabel jLabel4 = null;
	private JTextField _txt_idcomprobante = null;
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
		this.setSize(642, 228);
		this.setContentPane(getJContentPane());
		this.setTitle("Modificacion de Comprobante de Venta");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(503, 44, 84, 11));
			jLabel4.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel4.setText("Fecha");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(251, 42, 47, 15));
			jLabel3.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel3.setText("letra");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(152, 43, 90, 16));
			jLabel2.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel2.setText("numero");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(96, 44, 51, 14));
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setText("sucursal");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(10, 42, 40, 17));
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setText("tc");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJJToolBarBar(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(get_lst_tc(), null);
			jContentPane.add(get_txt_sucursal(), null);
			jContentPane.add(get_txt_numero(), null);
			jContentPane.add(get_list_letra(), null);
			jContentPane.add(get_txt_fecha(), null);
			jContentPane.add(get_txt_cuenta(), null);
			jContentPane.add(get_txt_cuenta_descripcion(), null);
			jContentPane.add(get_txt_importe(), null);
			jContentPane.add(get_btn_fecha(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(jLabel4, null);
			jContentPane.add(get_txt_idcomprobante(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jJToolBarBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJJToolBarBar() {
		if (jJToolBarBar == null) {
			jJToolBarBar = new JToolBar();
			jJToolBarBar.setBounds(new Rectangle(3, 2, 629, 22));
			jJToolBarBar.setFloatable(false);
			jJToolBarBar.add(get_btn_cancelar());
			jJToolBarBar.add(get_btn_guardar());
			jJToolBarBar.add(get_btn_salir());
		}
		return jJToolBarBar;
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
			_btn_guardar.setIcon(new ImageIcon(getClass().getResource("/icons/filesave.png")));
			_btn_guardar.setEnabled(false);
			_btn_guardar.setToolTipText("Guardar");
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
			_btn_salir.setToolTipText("Salir");
		}
		return _btn_salir;
	}

	/**
	 * This method initializes _lst_tc	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_tc() {
		if (_lst_tc == null) {
			_lst_tc = new JComboBox();
			_lst_tc.setBounds(new Rectangle(9, 60, 82, 16));
			_lst_tc.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _lst_tc;
	}

	/**
	 * This method initializes _txt_sucursal	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_sucursal() {
		if (_txt_sucursal == null) {
			_txt_sucursal = new JTextField();
			_txt_sucursal.setBounds(new Rectangle(94, 61, 54, 16));
			_txt_sucursal.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_sucursal;
	}

	/**
	 * This method initializes _txt_numero	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_numero() {
		if (_txt_numero == null) {
			_txt_numero = new JTextField();
			_txt_numero.setBounds(new Rectangle(150, 61, 97, 16));
			_txt_numero.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_numero;
	}

	/**
	 * This method initializes _list_letra	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_list_letra() {
		if (_list_letra == null) {
			_list_letra = new JComboBox();
			_list_letra.setBounds(new Rectangle(250, 61, 49, 16));
			_list_letra.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _list_letra;
	}

	/**
	 * This method initializes _txt_fecha	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_fecha() {
		if (_txt_fecha == null) {
			_txt_fecha = new JTextField();
			_txt_fecha.setBounds(new Rectangle(496, 59, 107, 16));
			_txt_fecha.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_fecha;
	}

	/**
	 * This method initializes _txt_cuenta	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_cuenta() {
		if (_txt_cuenta == null) {
			_txt_cuenta = new JTextField();
			_txt_cuenta.setBounds(new Rectangle(10, 88, 100, 16));
			_txt_cuenta.setEditable(false);
			_txt_cuenta.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_cuenta;
	}

	/**
	 * This method initializes _txt_cuenta_descripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_cuenta_descripcion() {
		if (_txt_cuenta_descripcion == null) {
			_txt_cuenta_descripcion = new JTextField();
			_txt_cuenta_descripcion.setBounds(new Rectangle(112, 88, 254, 16));
			_txt_cuenta_descripcion.setEditable(false);
			_txt_cuenta_descripcion.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_cuenta_descripcion;
	}

	/**
	 * This method initializes _txt_importe	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_importe() {
		if (_txt_importe == null) {
			_txt_importe = new JTextField();
			_txt_importe.setBounds(new Rectangle(371, 87, 77, 18));
			_txt_importe.setEditable(false);
			_txt_importe.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_importe;
	}

	/**
	 * This method initializes _btn_fecha	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_fecha() {
		if (_btn_fecha == null) {
			_btn_fecha = new JButton();
			_btn_fecha.setBounds(new Rectangle(606, 55, 21, 20));
			_btn_fecha.setIcon(new ImageIcon(getClass().getResource("/icons/calendar-16.gif")));
		}
		return _btn_fecha;
	}

	/**
	 * This method initializes _txt_idcomprobante	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idcomprobante() {
		if (_txt_idcomprobante == null) {
			_txt_idcomprobante = new JTextField();
			_txt_idcomprobante.setBounds(new Rectangle(307, 61, 144, 16));
		}
		return _txt_idcomprobante;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
