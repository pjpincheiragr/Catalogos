package aplicacion.compras.carga.encabezado.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Point;

public class _Confirmacion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JTextField _txt_idproveedor = null;
	private JTextField _txt_descripcion_proveedor = null;
	private JTextField _txt_total = null;
	private JButton _btn_grabar = null;
	private JLabel jLabel2 = null;
	private JTextField _txt_confirmacion = null;
	private JTextField _txt_usuario = null;
	private JTextField _txt_idcomprobante = null;
	private JTextField _txt_detalle = null;
	private JTextField _txt_fecha = null;
	private JTextField _txt_fecha_contable = null;
	/**
	 * This is the default constructor
	 */
	public _Confirmacion() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(328, 200);
		this.setContentPane(getJContentPane());
		this.setTitle("FACTURA DE COMPRA");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(51, 127, 141, 15));
			jLabel2.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel2.setText("codigo de confirmacion");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(16, 8, 67, 14));
			jLabel.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel.setText("proveedor");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(get_txt_idproveedor(), null);
			jContentPane.add(get_txt_descripcion_proveedor(), null);
			jContentPane.add(get_txt_total(), null);
			jContentPane.add(get_btn_grabar(), null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(get_txt_confirmacion(), null);
			jContentPane.add(get_txt_usuario(), null);
			jContentPane.add(get_txt_idcomprobante(), null);
			jContentPane.add(get_txt_detalle(), null);
			jContentPane.add(get_txt_fecha(), null);
			jContentPane.add(get_txt_fecha_contable(), null);
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
			_txt_idproveedor.setForeground(Color.white);
			_txt_idproveedor.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_idproveedor.setLocation(new Point(86, 7));
			_txt_idproveedor.setSize(new Dimension(107, 16));
			_txt_idproveedor.setHorizontalAlignment(JTextField.RIGHT);
			_txt_idproveedor.setEditable(false);
			_txt_idproveedor.setBackground(Color.black);
		}
		return _txt_idproveedor;
	}

	/**
	 * This method initializes _txt_descripcion_proveedor	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_descripcion_proveedor() {
		if (_txt_descripcion_proveedor == null) {
			_txt_descripcion_proveedor = new JTextField();
			_txt_descripcion_proveedor.setForeground(Color.white);
			_txt_descripcion_proveedor.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_descripcion_proveedor.setLocation(new Point(8, 24));
			_txt_descripcion_proveedor.setSize(new Dimension(305, 16));
			_txt_descripcion_proveedor.setHorizontalAlignment(JTextField.LEFT);
			_txt_descripcion_proveedor.setEditable(false);
			_txt_descripcion_proveedor.setBackground(Color.black);
		}
		return _txt_descripcion_proveedor;
	}

	/**
	 * This method initializes _txt_total	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_total() {
		if (_txt_total == null) {
			_txt_total = new JTextField();
			_txt_total.setForeground(Color.white);
			_txt_total.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_total.setLocation(new Point(230, 60));
			_txt_total.setSize(new Dimension(81, 16));
			_txt_total.setHorizontalAlignment(JTextField.RIGHT);
			_txt_total.setEditable(false);
			_txt_total.setToolTipText("Importe");
			_txt_total.setBackground(Color.black);
		}
		return _txt_total;
	}

	/**
	 * This method initializes _btn_grabar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_grabar() {
		if (_btn_grabar == null) {
			_btn_grabar = new JButton();
			_btn_grabar.setBounds(new Rectangle(277, 142, 24, 16));
			_btn_grabar.setEnabled(false);
			_btn_grabar.setIcon(new ImageIcon(getClass().getResource("/icons/emblem-default-16.png")));
		}
		return _btn_grabar;
	}

	/**
	 * This method initializes _txt_confirmacion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_confirmacion() {
		if (_txt_confirmacion == null) {
			_txt_confirmacion = new JTextField();
			_txt_confirmacion.setBounds(new Rectangle(194, 127, 79, 16));
			_txt_confirmacion.setBackground(Color.black);
			_txt_confirmacion.setForeground(Color.white);
			_txt_confirmacion.setFont(new Font("Dialog", Font.PLAIN, 14));
			_txt_confirmacion.setEditable(false);
		}
		return _txt_confirmacion;
	}

	/**
	 * This method initializes _txt_usuario	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_usuario() {
		if (_txt_usuario == null) {
			_txt_usuario = new JTextField();
			_txt_usuario.setBounds(new Rectangle(196, 143, 78, 15));
			_txt_usuario.setFont(new Font("Dialog", Font.PLAIN, 14));
		}
		return _txt_usuario;
	}

	/**
	 * This method initializes _txt_idcomprobante	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idcomprobante() {
		if (_txt_idcomprobante == null) {
			_txt_idcomprobante = new JTextField();
			_txt_idcomprobante.setBackground(Color.black);
			_txt_idcomprobante.setForeground(Color.white);
			_txt_idcomprobante.setLocation(new Point(102, 60));
			_txt_idcomprobante.setSize(new Dimension(125, 16));
			_txt_idcomprobante.setEditable(false);
			_txt_idcomprobante.setToolTipText("Idcomprobante");
			_txt_idcomprobante.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_idcomprobante;
	}

	/**
	 * This method initializes _txt_detalle	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_detalle() {
		if (_txt_detalle == null) {
			_txt_detalle = new JTextField();
			_txt_detalle.setBounds(new Rectangle(13, 108, 296, 16));
			_txt_detalle.setBackground(Color.black);
			_txt_detalle.setFont(new Font("Dialog", Font.PLAIN, 14));
			_txt_detalle.setForeground(Color.orange);
			_txt_detalle.setEditable(false);
		}
		return _txt_detalle;
	}

	/**
	 * This method initializes _txt_fecha	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_fecha() {
		if (_txt_fecha == null) {
			_txt_fecha = new JTextField();
			_txt_fecha.setBounds(new Rectangle(16, 60, 82, 15));
			_txt_fecha.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_fecha.setForeground(Color.white);
			_txt_fecha.setHorizontalAlignment(JTextField.RIGHT);
			_txt_fecha.setToolTipText("Fecha Factura");
			_txt_fecha.setBackground(Color.black);
		}
		return _txt_fecha;
	}

	/**
	 * This method initializes _txt_fecha_contable	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_fecha_contable() {
		if (_txt_fecha_contable == null) {
			_txt_fecha_contable = new JTextField();
			_txt_fecha_contable.setBounds(new Rectangle(17, 77, 80, 12));
			_txt_fecha_contable.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_fecha_contable.setEditable(false);
			_txt_fecha_contable.setToolTipText("Fecha Contable");
		}
		return _txt_fecha_contable;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
