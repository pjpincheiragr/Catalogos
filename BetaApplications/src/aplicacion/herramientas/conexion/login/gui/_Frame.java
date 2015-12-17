package aplicacion.herramientas.conexion.login.gui;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Transparency;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.*;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.JToolBar;
import java.awt.Font;
import javax.swing.JCheckBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.SystemColor;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JComboBox _list_conexion = null;
	private JComboBox _lst_modo = null;
	private JTextField _txt_usuario = null;
	private JPasswordField _txt_password = null;
	private JButton _btn_login = null;
	private JButton _btn_editar_conexion = null;
	private JButton _btn_salir = null;
	private JToolBar jToolBar = null;
	private JLabel jLabel3 = null;
	private JCheckBox _chk_recordar = null;
	private JComboBox _lst_skin = null;
	private JLabel jLabel5 = null;
	private JButton _btn_error = null;
	public JLabel _txt_version_label = null;
	private JLabel jLabel6 = null;
	private JLabel jLabel7 = null;
	private JSlider _bar_traslucent = null;
	private JLabel jLabel9 = null;
	private JTabbedPane jTabbedPane = null;
	private JPanel jPanel = null;
	private JPanel jPanel1 = null;
	private JComboBox _lst_deposito = null;
	private JLabel jLabel4 = null;
	private JButton _btn_login2 = null;
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
		this.setSize(486, 392);
		this.setResizable(false);
		try {
			this.setContentPane(getJContentPane());
			this.setTitle("Inicio de Sesion Beta RC 2010");
			this.setUndecorated(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel9 = new JLabel();
			jLabel9.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel9.setForeground(Color.white);
			jLabel9.setBounds(new Rectangle(120, 164, 74, 14));
			jLabel9.setText("Contraste");
			jLabel7 = new JLabel();
			jLabel7.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel7.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel7.setForeground(Color.white);
			jLabel7.setBounds(new Rectangle(119, 139, 75, 15));
			jLabel7.setText("Piel");
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(341, 347, 76, 14));
			jLabel6.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel6.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel6.setForeground(Color.white);
			jLabel6.setText("revision:");
			_txt_version_label = new JLabel();
			_txt_version_label.setBounds(new Rectangle(419, 347, 47, 14));
			_txt_version_label.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 10));
			_txt_version_label.setHorizontalAlignment(SwingConstants.RIGHT);
			_txt_version_label.setForeground(Color.white);
			
			_txt_version_label.setText("1.0182");
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(274, 25, 202, 27));
			jLabel5.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 18));
			jLabel5.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel5.setForeground(Color.white);
			jLabel5.setText("Beta RC 2010");
			jLabel3 = new JLabel();
			jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3.setText("Modo");
			jLabel3.setForeground(Color.white);
			jLabel3.setBounds(new Rectangle(113, 69, 80, 17));
			jLabel3.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel2 = new JLabel();
			jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2.setText("Conexion");
			jLabel2.setForeground(Color.white);
			jLabel2.setBounds(new Rectangle(113, 45, 80, 17));
			jLabel2.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1 = new JLabel();
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setForeground(Color.white);
			jLabel1.setBounds(new Rectangle(90, 112, 80, 17));
			jLabel1.setText("Contraseña");
			jLabel = new JLabel();
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setText("Usuario");
			jLabel.setForeground(Color.white);
			jLabel.setBounds(new Rectangle(90, 90, 80, 17));
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			
			
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(jLabel5, null);
			
			//
			jContentPane.add(_txt_version_label, null);
			//jContentPane.add(jLabel4, null);
			jContentPane.add(jLabel6, null);
			jContentPane.add(getJTabbedPane(), null);
			
			
		}
		return jContentPane;
	}

	/**
	 * This method initializes _list_conexion	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_list_conexion() {
		if (_list_conexion == null) {
			_list_conexion = new JComboBox();
			_list_conexion.setFont(new Font("Dialog", Font.PLAIN, 10));
			_list_conexion.setBounds(new Rectangle(201, 45, 132, 17));
		}
		return _list_conexion;
	}

	/**
	 * This method initializes _lst_modo	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_modo() {
		if (_lst_modo == null) {
			_lst_modo = new JComboBox();
			_lst_modo.setFont(new Font("Dialog", Font.PLAIN, 10));
			_lst_modo.setBounds(new Rectangle(201, 71, 132, 17));
		}
		return _lst_modo;
	}

	/**
	 * This method initializes _txt_usuario	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_usuario() {
		if (_txt_usuario == null) {
			_txt_usuario = new JTextField();
			_txt_usuario.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_usuario.setToolTipText("Ingrese Su Usuario");
			_txt_usuario.setBounds(new Rectangle(178, 90, 132, 18));
		}
		return _txt_usuario;
	}

	/**
	 * This method initializes _txt_password	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	public JPasswordField get_txt_password() {
		if (_txt_password == null) {
			_txt_password = new JPasswordField();
			_txt_password.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_password.setToolTipText("Ingrese Contraseña");
			_txt_password.setBounds(new Rectangle(178, 112, 132, 18));
		}
		return _txt_password;
	}

	/**
	 * This method initializes _btn_login	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_login() {
		if (_btn_login == null) {
			_btn_login = new JButton();
			_btn_login.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-media-play-ltr.png")));
			_btn_login.setBounds(new Rectangle(314, 112, 22, 18));
			_btn_login.setToolTipText("Pulse Este Boton Para Ingresar");
		}
		return _btn_login;
	}

	/**
	 * This method initializes _btn_editar_conexion	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_editar_conexion() {
		if (_btn_editar_conexion == null) {
			_btn_editar_conexion = new JButton();
			_btn_editar_conexion.setIcon(new ImageIcon(getClass().getResource("/icons/edit-find-replace.png")));
			_btn_editar_conexion.setBackground(Color.darkGray);
			_btn_editar_conexion.setBounds(new Rectangle(338, 44, 28, 20));
			_btn_editar_conexion.setToolTipText("editar conexion");
		}
		return _btn_editar_conexion;
	}

	/**
	 * This method initializes _btn_salir	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_salir() {
		if (_btn_salir == null) {
			_btn_salir = new JButton();
			_btn_salir.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-logout.png")));
			_btn_salir.setBackground(Color.darkGray);
			_btn_salir.setToolTipText("Salir");
		}
		return _btn_salir;
	}

	/**
	 * This method initializes jToolBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar() {
		if (jToolBar == null) {
			jToolBar = new JToolBar();
			jToolBar.setBounds(new Rectangle(0, 0, 494, 24));
			jToolBar.setFloatable(false);
			jToolBar.setBackground(new Color(51, 51, 51));
			jToolBar.add(get_btn_salir());
			jToolBar.add(get_btn_error());
		}
		return jToolBar;
	}

	/**
	 * This method initializes _chk_recordar	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_recordar() {
		if (_chk_recordar == null) {
			_chk_recordar = new JCheckBox();
			_chk_recordar.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_recordar.setBounds(new Rectangle(174, 140, 161, 15));
			
			_chk_recordar.setText("recordar mi contraseña");
		}
		return _chk_recordar;
	}

	/**
	 * This method initializes _lst_skin	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_skin() {
		if (_lst_skin == null) {
			_lst_skin = new JComboBox();
			_lst_skin.setFont(new Font("Dialog", Font.PLAIN, 10));
			_lst_skin.setBounds(new Rectangle(201, 140, 132, 16));
		}
		return _lst_skin;
	}

	/**
	 * This method initializes _btn_error	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_error() {
		if (_btn_error == null) {
			_btn_error = new JButton();
			_btn_error.setBackground(Color.darkGray);
			_btn_error.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-media-record.png")));
			_btn_error.setToolTipText("Envio de Informacion/Error a Sistemas");
		}
		return _btn_error;
	}

	/**
	 * This method initializes _bar_traslucent	
	 * 	
	 * @return javax.swing.JSlider	
	 */
	public JSlider get_bar_traslucent() {
		if (_bar_traslucent == null) {
			_bar_traslucent = new JSlider();
			_bar_traslucent.setValue(100);
			_bar_traslucent.setBounds(new Rectangle(198, 165, 132, 16));
			_bar_traslucent.setMinimum(90);
		}
		return _bar_traslucent;
	}

	/**
	 * This method initializes jTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.setBounds(new Rectangle(4, 55, 470, 282));
			jTabbedPane.setFont(new Font("Dialog", Font.PLAIN, 10));
			jTabbedPane.addTab("Login", null, getJPanel(), null);
			jTabbedPane.addTab("Avanzado", null, getJPanel1(), null);
		}
		return jTabbedPane;
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
			jPanel.add(jLabel, null);
			jPanel.add(get_txt_usuario(), null);
			jPanel.add(jLabel1, null);
			jPanel.add(get_txt_password(), null);
			jPanel.add(get_btn_login(), null);
			jPanel.add(get_chk_recordar(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(116, 97, 77, 16));
			jLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel4.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel4.setForeground(Color.white);
			jLabel4.setText("Deposito");
			jPanel1 = new JPanel();
			jPanel1.setLayout(null);
			jPanel1.add(jLabel2, null);
			jPanel1.add(get_list_conexion(), null);
			jPanel1.add(jLabel3, null);
			jPanel1.add(get_lst_modo(), null);
			jPanel1.add(jLabel7, null);
			jPanel1.add(get_lst_skin(), null);
			jPanel1.add(jLabel9, null);
			jPanel1.add(get_bar_traslucent(), null);
			jPanel1.add(get_lst_deposito(), null);
			jPanel1.add(jLabel4, null);
			jPanel1.add(get_btn_editar_conexion(), null);
			jPanel1.add(get_btn_login2(), null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes _lst_deposito	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_deposito() {
		if (_lst_deposito == null) {
			_lst_deposito = new JComboBox();
			_lst_deposito.setBounds(new Rectangle(201, 96, 132, 17));
			_lst_deposito.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _lst_deposito;
	}

	/**
	 * This method initializes _btn_login2	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_login2() {
		if (_btn_login2 == null) {
			_btn_login2 = new JButton();
			_btn_login2.setBounds(new Rectangle(368, 44, 22, 20));
			_btn_login2.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-media-play-ltr.png")));
		}
		return _btn_login2;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
