package aplicacion.sistema.host.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import java.awt.Rectangle;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JTabbedPane;
import java.awt.GridBagLayout;
import java.awt.Point;
import javax.swing.JRadioButton;

import org.jdesktop.jxlayer.JXLayer;
import org.jdesktop.jxlayer.plaf.effect.BufferedImageOpEffect;
import org.jdesktop.jxlayer.plaf.ext.LockableUI;

import com.jhlabs.image.DiffuseFilter;
import com.jhlabs.image.BoxBlurFilter;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JToolBar jToolBar = null;
	private JButton _btn_cargar = null;
	private JButton _btn_salir = null;
	
	private JTextField _txt_idhost = null;
	private JLabel jLabel = null;
	private JButton _btn_cancelar = null;
	private JTextField _txt_ip = null;
	private JLabel jLabel1 = null;
	private JButton _btn_buscar_host = null;
	private JButton _btn_guardar = null;
	private JButton _btn_eliminar = null;
	private JTable jTable = null;
	private JButton _btn_error = null;
	private JButton _btn_play = null;
	private JTabbedPane jTabbedPane = null;
	private LockableUI lockableUI=null;
	private JPanel jPanel1 = null;
	private JCheckBox _chk_printer = null;
	private JCheckBox _chk_server = null;
	private JCheckBox _chk_monitoreo = null;
	private JTextField _txt_extension = null;
	private JLabel jLabel2 = null;
	private JRadioButton _rad_dhcp = null;
	private JRadioButton _rad_manual = null;
	private JPanel jPanel = null;
	private JButton _btn_rename = null;
	private JLabel jLabel3 = null;
	private JTextField _txt_email = null;
	private JLabel jLabel4 = null;
	private JTextField _txt_os = null;
	private JLabel jLabel5 = null;
	private JTextField _txt_macAddress = null;
	
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
		this.setSize(545, 448);
		this.setContentPane(getJContentPane());
		this.setTitle("Host");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel2 = new JLabel();
			jLabel2.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2.setBounds(new Rectangle(8, 68, 79, 15));
			jLabel2.setText("extension");
			jLabel1 = new JLabel();
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setBounds(new Rectangle(8, 48, 79, 14));
			jLabel1.setText("ip");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(14, 45, 81, 13));
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setText("idhost");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(get_txt_idhost(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(get_btn_buscar_host(), null);
			jContentPane.add(get_btn_cargar(), null);
			//jContentPane.add(getJXLayerUI(), null);
			jContentPane.add(getJPanel(), null);
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
			jToolBar.setBounds(new Rectangle(0, 0, 535, 24));
			jToolBar.setFloatable(false);
			jToolBar.add(get_btn_cancelar());
			jToolBar.add(get_btn_guardar());
			jToolBar.add(get_btn_rename());
			jToolBar.add(get_btn_eliminar());
			jToolBar.add(get_btn_salir());
			jToolBar.add(get_btn_error());
			jToolBar.add(get_btn_play());
		}
		return jToolBar;
	}

	/**
	 * This method initializes _btn_cargar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cargar() {
		if (_btn_cargar == null) {
			_btn_cargar = new JButton();
			_btn_cargar.setIcon(new ImageIcon(getClass().getResource("/icons/reload3.png")));
			_btn_cargar.setToolTipText("Cargar");
			_btn_cargar.setBounds(new Rectangle(297, 42, 23, 18));
		}
		return _btn_cargar;
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
	 * This method initializes _txt_idhost	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idhost() {
		if (_txt_idhost == null) {
			_txt_idhost = new JTextField();
			_txt_idhost.setToolTipText("Filtrar Parametros");
			_txt_idhost.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_idhost.setBounds(new Rectangle(104, 44, 160, 16));
		}
		return _txt_idhost;
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
	 * This method initializes _txt_ip	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_ip() {
		if (_txt_ip == null) {
			_txt_ip = new JTextField();
			_txt_ip.setBounds(new Rectangle(97, 48, 160, 16));
			_txt_ip.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_ip;
	}

	/**
	 * This method initializes _btn_buscar_host	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_host() {
		if (_btn_buscar_host == null) {
			_btn_buscar_host = new JButton();
			_btn_buscar_host.setBounds(new Rectangle(269, 42, 23, 18));
			_btn_buscar_host.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-searchtool.png")));
			_btn_buscar_host.setToolTipText("Buscar Usuario");
		}
		return _btn_buscar_host;
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
			_btn_guardar.setToolTipText("Guardar");
		}
		return _btn_guardar;
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
			_btn_eliminar.setToolTipText("Eliminar Usuario");
		}
		return _btn_eliminar;
	}


	/**
	 * This method initializes _btn_error	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_error() {
		if (_btn_error == null) {
			_btn_error = new JButton();
			_btn_error.setIcon(new ImageIcon(getClass().getResource("/icons/media-record.png")));
		}
		return _btn_error;
	}

	/**
	 * This method initializes _btn_play	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_play() {
		if (_btn_play == null) {
			_btn_play = new JButton();
			_btn_play.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-media-play-ltr.png")));
			_btn_play.setToolTipText("Restaurar Session Desde XML");
		}
		return _btn_play;
	}

	/**
	 * This method initializes jTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.setBounds(new Rectangle(1, 175, 507, 166));
			jTabbedPane.addTab("Propiedades", null, getJPanel1(), null);
			jTabbedPane.setBounds(new Rectangle(7, 175, 501, 166));
		
		}
		return jTabbedPane;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setLayout(null);
			jPanel1.add(get_chk_printer(), null);
			jPanel1.add(get_chk_server(), null);
			jPanel1.add(get_chk_monitoreo(), null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes _chk_printer	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_printer() {
		if (_chk_printer == null) {
			_chk_printer = new JCheckBox();
			_chk_printer.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_printer.setLocation(new Point(12, 16));
			_chk_printer.setSize(new Dimension(192, 18));
			_chk_printer.setText("Servidor de Etiquetas");
		}
		return _chk_printer;
	}

	/**
	 * This method initializes _chk_server	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_server() {
		if (_chk_server == null) {
			_chk_server = new JCheckBox();
			_chk_server.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_server.setLocation(new Point(12, 36));
			_chk_server.setSize(new Dimension(200, 18));
			_chk_server.setText("Servidor de Actualizaciones");
		}
		return _chk_server;
	}

	/**
	 * This method initializes _chk_monitoreo	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_monitoreo() {
		if (_chk_monitoreo == null) {
			_chk_monitoreo = new JCheckBox();
			_chk_monitoreo.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_monitoreo.setLocation(new Point(12, 56));
			_chk_monitoreo.setSize(new Dimension(200, 18));
			_chk_monitoreo.setText("Monitoreo");
		}
		return _chk_monitoreo;
	}

	/**
	 * This method initializes _txt_extension	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_extension() {
		if (_txt_extension == null) {
			_txt_extension = new JTextField();
			_txt_extension.setBounds(new Rectangle(97, 68, 160, 16));
			_txt_extension.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_extension;
	}

	/**
	 * This method initializes _rad_dhcp	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	public JRadioButton get_rad_dhcp() {
		if (_rad_dhcp == null) {
			_rad_dhcp = new JRadioButton();
			_rad_dhcp.setFont(new Font("Dialog", Font.PLAIN, 10));
			_rad_dhcp.setBounds(new Rectangle(97, 5, 71, 37));
			_rad_dhcp.setText("idhcp");
		}
		return _rad_dhcp;
	}

	/**
	 * This method initializes _rad_manual	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	public JRadioButton get_rad_manual() {
		if (_rad_manual == null) {
			_rad_manual = new JRadioButton();
			_rad_manual.setFont(new Font("Dialog", Font.PLAIN, 10));
			_rad_manual.setBounds(new Rectangle(171, 5, 83, 37));
			_rad_manual.setText("manual");
		}
		return _rad_manual;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(9, 135, 80, 15));
			jLabel5.setText("MAC Address");
			jLabel5.setFont(new Font("Dialog", Font.PLAIN, 10));

			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(9, 113, 80, 15));
			jLabel4.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel4.setText("sistema");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(7, 90, 81, 15));
			jLabel3.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3.setText("e-mail");
			jPanel = new JPanel();
			jPanel.setLayout(null);
			//jPanel.setBounds(new Rectangle(0, 0, 523, 303));
			jPanel.setBounds(new Rectangle(7, 65, 523, 346));
			jPanel.add(get_rad_dhcp(), null);
			jPanel.add(get_rad_manual(), null);
			jPanel.add(jLabel1, null);
			jPanel.add(get_txt_ip(), null);
			jPanel.add(jLabel2, null);
			jPanel.add(get_txt_extension(), null);
			jPanel.add(getJTabbedPane(), null);
			jPanel.add(jLabel3, null);
			jPanel.add(get_txt_email(), null);
			jPanel.add(jLabel4, null);
			jPanel.add(get_txt_os(), null);			jPanel.add(jLabel5, null);

			jPanel.add(get_txt_macAddress(), null);
			
		}
		return jPanel;
	}

	/**
	 * This method initializes _btn_rename	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_rename() {
		if (_btn_rename == null) {
			_btn_rename = new JButton();
			_btn_rename.setIcon(new ImageIcon(getClass().getResource("/icons/applications-system.png")));
		}
		return _btn_rename;
	}
	
	/*public JXLayer<JComponent> getJXLayerUI(){
		JPanel panel = this.getJPanel();
		panel.setSize(new Dimension(523, 303));
		JXLayer<JComponent> layer = new JXLayer<JComponent>(panel, this.getLockableUI());
		layer.setBounds(new Rectangle(10, 65, 523, 303));
		this.getLockableUI().setLocked(true);
		return layer;
		
	}*/
	
	public LockableUI getLockableUI(){
		if (lockableUI==null){
			lockableUI= new LockableUI();        
			//BoxBlurFilter blur=new BoxBlurFilter(1,1,3);
			DiffuseFilter blur=new DiffuseFilter();
			blur.setInterpolation(1);
			BufferedImageOpEffect effect = new BufferedImageOpEffect(blur);
			lockableUI.setLockedEffects(effect);
			
			
			lockableUI.setLocked(true);
		}
		return lockableUI; 

	}

	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_email() {
		if (_txt_email == null) {
			_txt_email = new JTextField();
			_txt_email.setBounds(new Rectangle(98, 90, 160, 16));
			_txt_email.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_email;
	}

	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_os() {
		if (_txt_os == null) {
			_txt_os = new JTextField();
			_txt_os.setBounds(new Rectangle(98, 113, 160, 16));
			_txt_os.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_os;
	}

	/**
	 * This method initializes _txt_macAddress	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_macAddress() {
		if (_txt_macAddress == null) {
			_txt_macAddress = new JTextField();
			_txt_macAddress.setBounds(new Rectangle(98, 135, 160, 17));
			_txt_macAddress.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_macAddress;
	}




}  //  @jve:decl-index=0:visual-constraint="10,10"
