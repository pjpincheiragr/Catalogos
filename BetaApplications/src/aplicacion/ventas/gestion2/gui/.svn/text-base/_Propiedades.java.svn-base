package aplicacion.ventas.gestion2.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JToolBar;
import javax.swing.ImageIcon;

public class _Propiedades extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JTextField _txt_idclasificacion = null;
	private JLabel jLabel1 = null;
	private JTextField _txt_nombre = null;
	private JLabel jLabel2 = null;
	private JTextField _txt_color = null;
	private JButton _btn_color = null;
	private JToolBar jToolBar = null;
	private JButton _btn_agregar = null;
	private JButton _btn_salir = null;

	/**
	 * This is the default constructor
	 */
	public _Propiedades() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setResizable(false);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/chrome.png")));
		this.setContentPane(getJContentPane());
		this.setTitle("Clasificacion");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(8, 84, 82, 16));
			jLabel2.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel2.setText("Color");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(7, 60, 83, 15));
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setText("Nombre");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(8, 35, 82, 17));
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setText("Clasificacion");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(get_txt_idclasificacion(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(get_txt_nombre(), null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(get_txt_color(), null);
			jContentPane.add(get_btn_color(), null);
			jContentPane.add(getJToolBar(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes _txt_idclasificacion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idclasificacion() {
		if (_txt_idclasificacion == null) {
			_txt_idclasificacion = new JTextField();
			_txt_idclasificacion.setBounds(new Rectangle(96, 33, 145, 20));
			_txt_idclasificacion.setEditable(false);
			_txt_idclasificacion.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_idclasificacion;
	}

	/**
	 * This method initializes _txt_nombre	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_nombre() {
		if (_txt_nombre == null) {
			_txt_nombre = new JTextField();
			_txt_nombre.setBounds(new Rectangle(97, 58, 143, 17));
			_txt_nombre.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_nombre;
	}

	/**
	 * This method initializes _txt_color	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_color() {
		if (_txt_color == null) {
			_txt_color = new JTextField();
			_txt_color.setBounds(new Rectangle(96, 82, 143, 18));
			_txt_color.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_color;
	}

	/**
	 * This method initializes _btn_color	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_color() {
		if (_btn_color == null) {
			_btn_color = new JButton();
			_btn_color.setBounds(new Rectangle(244, 82, 19, 18));
		}
		return _btn_color;
	}

	/**
	 * This method initializes jToolBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar() {
		if (jToolBar == null) {
			jToolBar = new JToolBar();
			jToolBar.setBounds(new Rectangle(3, 2, 288, 24));
			jToolBar.setFloatable(false);
			jToolBar.add(get_btn_agregar());
			jToolBar.add(get_btn_salir());
		}
		return jToolBar;
	}

	/**
	 * This method initializes _btn_agregar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_agregar() {
		if (_btn_agregar == null) {
			_btn_agregar = new JButton();
			_btn_agregar.setIcon(new ImageIcon(getClass().getResource("/icons/emblem-default-16.png")));
		}
		return _btn_agregar;
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
		}
		return _btn_salir;
	}

}
