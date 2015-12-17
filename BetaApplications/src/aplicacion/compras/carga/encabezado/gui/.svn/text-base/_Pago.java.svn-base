package aplicacion.compras.carga.encabezado.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class _Pago extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JTextField _txt_idproveedor = null;
	private JTextField _txt_proveedor_descripcion = null;
	private JButton _btn_pago = null;
	private JTextField _txt_confirmacion = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JTextField _txt_tc = null;
	private JTextField _txt_idcomprobante = null;
	private JLabel jLabel3 = null;
	private JTextField _txt_importe = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;

	/**
	 * This is the default constructor
	 */
	public _Pago() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(513, 291);
		this.setContentPane(getJContentPane());
		this.setTitle("Pago Contado");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(199, 73, 62, 18));
			jLabel3.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3.setText("importe");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(15, 57, 45, 16));
			jLabel2.setText("TC");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(310, 241, 68, 16));
			jLabel1.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setText("total");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(13, 16, 94, 14));
			jLabel.setText("Cuenta");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(get_txt_idproveedor(), null);
			jContentPane.add(get_txt_proveedor_descripcion(), null);
			jContentPane.add(get_btn_pago(), null);
			jContentPane.add(get_txt_confirmacion(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(get_txt_tc(), null);
			jContentPane.add(get_txt_idcomprobante(), null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(get_txt_importe(), null);
			jContentPane.add(getJScrollPane(), null);
			
		}
		return jContentPane;
	}

	/**
	 * This method initializes _txt_idproveedor	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idproveedor() {
		if (_txt_idproveedor == null) {
			_txt_idproveedor = new JTextField();
			_txt_idproveedor.setBounds(new Rectangle(12, 37, 94, 15));
			_txt_idproveedor.setBackground(Color.black);
			_txt_idproveedor.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_idproveedor.setHorizontalAlignment(JTextField.RIGHT);
			_txt_idproveedor.setEditable(false);
		}
		return _txt_idproveedor;
	}

	/**
	 * This method initializes _txt_proveedor_descripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_proveedor_descripcion() {
		if (_txt_proveedor_descripcion == null) {
			_txt_proveedor_descripcion = new JTextField();
			_txt_proveedor_descripcion.setBounds(new Rectangle(109, 38, 386, 15));
			_txt_proveedor_descripcion.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_proveedor_descripcion.setBackground(Color.black);
			_txt_proveedor_descripcion.setForeground(Color.white);
			_txt_proveedor_descripcion.setHorizontalAlignment(JTextField.LEFT);
			_txt_proveedor_descripcion.setEditable(false);
		}
		return _txt_proveedor_descripcion;
	}

	/**
	 * This method initializes _btn_pago	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton get_btn_pago() {
		if (_btn_pago == null) {
			_btn_pago = new JButton();
			_btn_pago.setBounds(new Rectangle(460, 238, 41, 21));
			_btn_pago.setIcon(new ImageIcon(getClass().getResource("/icons/emblem-default-16.png")));
		}
		return _btn_pago;
	}

	/**
	 * This method initializes _txt_confirmacion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField get_txt_confirmacion() {
		if (_txt_confirmacion == null) {
			_txt_confirmacion = new JTextField();
			_txt_confirmacion.setBounds(new Rectangle(384, 239, 72, 20));
			_txt_confirmacion.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_confirmacion.setForeground(Color.white);
			_txt_confirmacion.setBackground(Color.black);
		}
		return _txt_confirmacion;
	}

	/**
	 * This method initializes _txt_tc	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_tc() {
		if (_txt_tc == null) {
			_txt_tc = new JTextField();
			_txt_tc.setBounds(new Rectangle(15, 76, 45, 17));
			_txt_tc.setForeground(Color.white);
			_txt_tc.setBackground(Color.black);
		}
		return _txt_tc;
	}

	/**
	 * This method initializes _txt_idcomprobante	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idcomprobante() {
		if (_txt_idcomprobante == null) {
			_txt_idcomprobante = new JTextField();
			_txt_idcomprobante.setBounds(new Rectangle(66, 75, 114, 18));
			_txt_idcomprobante.setBackground(Color.black);
			_txt_idcomprobante.setForeground(Color.white);
			_txt_idcomprobante.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_idcomprobante;
	}

	/**
	 * This method initializes _txt_importe	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_importe() {
		if (_txt_importe == null) {
			_txt_importe = new JTextField();
			_txt_importe.setBounds(new Rectangle(262, 73, 82, 20));
			_txt_importe.setBackground(Color.black);
			_txt_importe.setForeground(Color.white);
			_txt_importe.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_importe;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(15, 107, 482, 121));
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

}  //  @jve:decl-index=0:visual-constraint="10,10"
