package aplicacion.compras.carga.scanner.gui;

import aplicacion.compras.carga.items.logic.DisplayCanvas;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.Component;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private Component map=null;
	private JButton _btn_guardar = null;
	private JButton _btn_scan = null;
	public JTextField _txt_idproveedor = null;
	public JTextField _txt_tc = null;
	public JTextField _txt_idcomprobante = null;
	public JTextField _txt_secuencia = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JTextField _txt_proveedor_descripcion = null;
	private JTextField _txt_ruta = null;
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
		this.setSize(361, 408);
		this.setResizable(false);
		this.setContentPane(getJContentPane());
		this.setTitle("Preview");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	public JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(148, 34, 18, 22));
			jLabel3.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel3.setText("num");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(41, 37, 82, 17));
			jLabel2.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel2.setText("idcomprobante");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(8, 37, 27, 17));
			jLabel1.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel1.setText("tc");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(7, 1, 75, 17));
			jLabel.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel.setText("Cuenta");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(get_btn_guardar(), null);
			jContentPane.add(get_btn_scan(), null);
			
			jContentPane.add(get_txt_idproveedor(), null);
			jContentPane.add(get_txt_tc(), null);
			jContentPane.add(get_txt_idcomprobante(), null);
			jContentPane.add(get_txt_secuencia(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(get_txt_proveedor_descripcion(), null);
			jContentPane.add(get_txt_ruta(), null);
		}
		return jContentPane;
	}

	
	/**
	 * This method initializes _btn_guardar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_guardar() {
		if (_btn_guardar == null) {
			_btn_guardar = new JButton();
			_btn_guardar.setBounds(new Rectangle(206, 357, 95, 19));
			_btn_guardar.setFont(new Font("Dialog", Font.BOLD, 10));
			_btn_guardar.setIcon(new ImageIcon(getClass().getResource("/icons/emblem-default-16.png")));
			_btn_guardar.setText("guardar");
		}
		return _btn_guardar;
	}

	/**
	 * This method initializes _btn_scan	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_scan() {
		if (_btn_scan == null) {
			_btn_scan = new JButton();
			_btn_scan.setBounds(new Rectangle(315, 11, 30, 28));
			_btn_scan.setToolTipText("Escanear");
			_btn_scan.setIcon(new ImageIcon(getClass().getResource("/icons/file-manager-24.png")));
		}
		return _btn_scan;
	}
	
	public void setCanvas(Component c){
		map=c;
	}
	
	

	/**
	 * This method initializes _txt_idproveedor	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idproveedor() {
		if (_txt_idproveedor == null) {
			_txt_idproveedor = new JTextField();
			_txt_idproveedor.setBounds(new Rectangle(7, 19, 90, 16));
			_txt_idproveedor.setHorizontalAlignment(JTextField.LEFT);
			_txt_idproveedor.setEditable(false);
			_txt_idproveedor.setBackground(Color.black);
			_txt_idproveedor.setForeground(Color.white);
			_txt_idproveedor.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_idproveedor;
	}

	/**
	 * This method initializes _txt_tc	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_tc() {
		if (_txt_tc == null) {
			_txt_tc = new JTextField();
			_txt_tc.setBounds(new Rectangle(8, 56, 33, 16));
			_txt_tc.setHorizontalAlignment(JTextField.LEFT);
			_txt_tc.setEditable(false);
			_txt_tc.setBackground(Color.black);
			_txt_tc.setForeground(Color.white);
			_txt_tc.setFont(new Font("Dialog", Font.PLAIN, 10));
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
			_txt_idcomprobante.setBounds(new Rectangle(41, 56, 106, 16));
			_txt_idcomprobante.setHorizontalAlignment(JTextField.RIGHT);
			_txt_idcomprobante.setEditable(false);
			_txt_idcomprobante.setBackground(Color.black);
			_txt_idcomprobante.setForeground(Color.white);
			_txt_idcomprobante.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_idcomprobante;
	}

	/**
	 * This method initializes _txt_secuencia	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_secuencia() {
		if (_txt_secuencia == null) {
			_txt_secuencia = new JTextField();
			_txt_secuencia.setBounds(new Rectangle(148, 56, 22, 16));
			_txt_secuencia.setHorizontalAlignment(JTextField.RIGHT);
			_txt_secuencia.setEditable(false);
			_txt_secuencia.setBackground(Color.black);
			_txt_secuencia.setForeground(Color.white);
			_txt_secuencia.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_secuencia;
	}

	/**
	 * This method initializes _txt_proveedor_descripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_proveedor_descripcion() {
		if (_txt_proveedor_descripcion == null) {
			_txt_proveedor_descripcion = new JTextField();
			_txt_proveedor_descripcion.setBounds(new Rectangle(99, 19, 211, 16));
			_txt_proveedor_descripcion.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_proveedor_descripcion.setEditable(false);
			_txt_proveedor_descripcion.setBackground(Color.black);
			_txt_proveedor_descripcion.setForeground(Color.white);
			_txt_proveedor_descripcion.setHorizontalAlignment(JTextField.LEFT);
		}
		return _txt_proveedor_descripcion;
	}

	/**
	 * This method initializes _txt_ruta	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_ruta() {
		if (_txt_ruta == null) {
			_txt_ruta = new JTextField();
			_txt_ruta.setBounds(new Rectangle(3, 330, 349, 18));
			_txt_ruta.setBackground(Color.darkGray);
			_txt_ruta.setForeground(Color.white);
			_txt_ruta.setHorizontalAlignment(JTextField.LEFT);
			_txt_ruta.setEditable(false);
			_txt_ruta.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_ruta;
	}
}  //  @jve:decl-index=0:visual-constraint="15,30"
