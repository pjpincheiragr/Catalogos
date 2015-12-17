package aplicacion.sistema.autorizar.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import java.awt.Rectangle;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JToolBar jToolBar = null;
	private JButton _btn_salir = null;
	
	private JTextField _txt_codigo_requerido = null;
	private JLabel jLabel = null;
	private JButton _btn_cancelar = null;
	private JTextField _txt_codigo_de_autorizacion = null;
	private JLabel jLabel1 = null;
	private JButton _btn_autorizar = null;
	private JTable jTable = null;
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
		this.setSize(460, 156);
		this.setContentPane(getJContentPane());
		this.setTitle("Usuario");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(23, 75, 130, 14));
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setText("Codigo de Autorizacion");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(21, 45, 130, 13));
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setText("Codigo Requerido");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(get_txt_codigo_requerido(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(get_txt_codigo_de_autorizacion(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(get_btn_autorizar(), null);
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
			jToolBar.setBounds(new Rectangle(0, 0, 452, 24));
			jToolBar.setFloatable(false);
			jToolBar.add(get_btn_cancelar());
			jToolBar.add(get_btn_salir());
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
			_btn_salir.setIcon(new ImageIcon(getClass().getResource("/icons/exit.png")));
			_btn_salir.setToolTipText("Salir");
		}
		return _btn_salir;
	}


	/**
	 * This method initializes _txt_codigo_requerido	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_codigo_requerido() {
		if (_txt_codigo_requerido == null) {
			_txt_codigo_requerido = new JTextField();
			_txt_codigo_requerido.setToolTipText("Ingrese la clave para obtener la autorizacion");
			_txt_codigo_requerido.setBounds(new Rectangle(163, 44, 160, 16));
		}
		return _txt_codigo_requerido;
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
			_btn_cancelar.setToolTipText("Cancelar Edicion");
		}
		return _btn_cancelar;
	}

	/**
	 * This method initializes _txt_codigo_de_autorizacion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_codigo_de_autorizacion() {
		if (_txt_codigo_de_autorizacion == null) {
			_txt_codigo_de_autorizacion = new JTextField();
			_txt_codigo_de_autorizacion.setBounds(new Rectangle(164, 74, 157, 16));
		}
		return _txt_codigo_de_autorizacion;
	}

	/**
	 * This method initializes _btn_autorizar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_autorizar() {
		if (_btn_autorizar == null) {
			_btn_autorizar = new JButton();
			_btn_autorizar.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-run.png")));
			_btn_autorizar.setBounds(new Rectangle(330, 42, 34, 20));
			_btn_autorizar.setToolTipText("Configurar Aplicaciones");
		}
		return _btn_autorizar;
	}

	
}  //  @jve:decl-index=0:visual-constraint="10,10"
