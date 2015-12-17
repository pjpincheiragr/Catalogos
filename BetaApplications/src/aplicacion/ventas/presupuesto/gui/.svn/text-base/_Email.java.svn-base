package aplicacion.ventas.presupuesto.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Rectangle;
import java.awt.GridBagLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Toolkit;
import javax.swing.JToolBar;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JProgressBar;

public class _Email extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel jPanel = null;
	private JTable jTable = null;
	private JLabel jLabel1 = null;
	private JTextField _txt_emailfrom = null;
	private JToolBar jToolBar = null;
	private JButton _btn_enviar = null;
	private JLabel jLabel2 = null;
	private JTextField _txt_emailTo = null;
	private JLabel jLabel3 = null;
	private JTextField _txt_asunto = null;
	private JScrollPane jScrollPane = null;
	private JTextArea _txt_mensaje = null;
	private JProgressBar jProgressBar = null;
	private JLabel jLabel = null;
	private JButton _btn_salir = null;

	/**
	 * This is the default constructor
	 */
	public _Email() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(683, 320);
		this.setContentPane(getJContentPane());
		this.setTitle("Email Presupuesto Cliente");
		this.setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/chrome.png")));
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJPanel(), null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(getJProgressBar(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(9, 103, 53, 19));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setText("Mensaje");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(6, 64, 56, 18));
			jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel3.setText("Asunto");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(26, 35, 36, 19));
			jLabel2.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2.setText("Para:");
			jLabel1 = new JLabel();
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setBounds(new Rectangle(11, 13, 51, 14));
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setText("De:");
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBounds(new Rectangle(7, 53, 663, 237));
			jPanel.setBackground(Color.gray);
			jPanel.add(jLabel2, null);
			jPanel.add(get_txt_emailTo(), null);
			jPanel.add(get_txt_emailfrom(), null);
			jPanel.add(jLabel1, null);
			jPanel.add(jLabel3, null);
			jPanel.add(get_txt_asunto(), null);
			jPanel.add(getJScrollPane(), null);
			jPanel.add(jLabel, null);
		}
		return jPanel;
	}

	/**
	 * This method initializes _txt_emailfrom	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_emailfrom() {
		if (_txt_emailfrom == null) {
			_txt_emailfrom = new JTextField();
			_txt_emailfrom.setHorizontalAlignment(JTextField.LEFT);
			_txt_emailfrom.setLocation(new Point(64, 11));
			_txt_emailfrom.setSize(new Dimension(530, 19));
			_txt_emailfrom.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_emailfrom.setEditable(false);
		}
		return _txt_emailfrom;
	}

	/**
	 * This method initializes jToolBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar() {
		if (jToolBar == null) {
			jToolBar = new JToolBar();
			jToolBar.setLocation(new Point(3, 4));
			jToolBar.setFloatable(false);
			jToolBar.setSize(new Dimension(666, 23));
			jToolBar.add(get_btn_enviar());
			jToolBar.add(get_btn_salir());
		}
		return jToolBar;
	}

	/**
	 * This method initializes _btn_enviar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_enviar() {
		if (_btn_enviar == null) {
			_btn_enviar = new JButton();
			_btn_enviar.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-stock-mail-fwd.png")));
		}
		return _btn_enviar;
	}

	/**
	 * This method initializes _txt_emailTo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_emailTo() {
		if (_txt_emailTo == null) {
			_txt_emailTo = new JTextField();
			_txt_emailTo.setLocation(new Point(64, 35));
			_txt_emailTo.setHorizontalAlignment(JTextField.LEFT);
			_txt_emailTo.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_emailTo.setBackground(new Color(255, 255, 204));
			
			_txt_emailTo.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_emailTo.setSize(new Dimension(530, 18));
		}
		return _txt_emailTo;
	}

	/**
	 * This method initializes _txt_asunto	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_asunto() {
		if (_txt_asunto == null) {
			_txt_asunto = new JTextField();
			_txt_asunto.setLocation(new Point(64, 61));
			_txt_asunto.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_asunto.setSize(new Dimension(530, 24));
		}
		return _txt_asunto;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setLocation(new Point(64, 100));
			jScrollPane.setSize(new Dimension(530, 126));
			jScrollPane.setViewportView(get_txt_mensaje());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes _txt_mensaje	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea get_txt_mensaje() {
		if (_txt_mensaje == null) {
			_txt_mensaje = new JTextArea();
			_txt_mensaje.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_mensaje;
	}

	/**
	 * This method initializes jProgressBar	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	public JProgressBar getJProgressBar() {
		if (jProgressBar == null) {
			jProgressBar = new JProgressBar();
			jProgressBar.setBounds(new Rectangle(5, 30, 664, 17));
		}
		return jProgressBar;
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

}  //  @jve:decl-index=0:visual-constraint="10,10"
