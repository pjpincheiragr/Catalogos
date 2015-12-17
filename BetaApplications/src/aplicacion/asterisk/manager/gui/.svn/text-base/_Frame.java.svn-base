package aplicacion.asterisk.manager.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Image;

import javax.swing.ImageIcon;
import java.awt.Dimension;
import javax.swing.JTextField;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Point;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JTabbedPane;
import java.awt.ComponentOrientation;
import javax.swing.JMenu;
import javax.swing.JProgressBar;

import aplicacion.compras.carga.items.logic.DisplayCanvas;
import javax.swing.JMenuBar;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton _btn_minimizar = null;
	private JTabbedPane jTabbedPane = null;
	private JTable jTable = null;
	private JPanel jPanel_principal = null;
	private JPanel jPanel_acerca = null;
	private JPanel jPanel_agenda = null;
	private JPanel jPanel_messenger = null;
	public JLabel _version = null;
	private JProgressBar jProgressBar = null;
	private JProgressBar _bar_memory = null;
	/**
	 * This is the default constructor
	 */
	public _Frame() {
		super();
		this.setVisible(false);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(603, 305);
		this.setTitle("Beta");
		this.setUndecorated(true);
		this.setContentPane(getJContentPane());
		
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	public JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.setBackground(new Color(204, 204, 204));
			jContentPane.add(getJTabbedPane(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes _btn_minimizar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_minimizar() {
		if (_btn_minimizar == null) {
			_btn_minimizar = new JButton();
			
			_btn_minimizar.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-media-play-down.png")));
			_btn_minimizar.setToolTipText("Minimizar");
		}
		return _btn_minimizar;
	}

	/**
	 * This method initializes jTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	public JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.setVisible(false);
			jTabbedPane.setSize(new Dimension(524, 271));
			jTabbedPane.setLocation(new Point(0, 0));
			jTabbedPane.setFont(new Font("Dialog", Font.PLAIN, 10));
			jTabbedPane.setTabPlacement(JTabbedPane.BOTTOM);
			jTabbedPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			jTabbedPane.addTab("Principal", new ImageIcon(getClass().getResource("/icons/xterm.png")), getJPanel_principal(), null);
			//jTabbedPane.addTab("Llamadas", new ImageIcon(getClass().getResource("/icons/phone-16.gif")), getJPanel_Asterisk(), null);
			//jTabbedPane.addTab("Agenda", new ImageIcon(getClass().getResource("/icons/appointment-soon.png")), getJPanel_agenda(), null);
			//jTabbedPane.addTab("Messenger", new ImageIcon(getClass().getResource("/icons/im-msn-24.png")), getJPanel_messenger(), null);
			
			
			
		}
		return jTabbedPane;
	}



	/**
	 * This method initializes jPanel_principal	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public JPanel getJPanel_principal() {
		if (jPanel_principal == null) {
			jPanel_principal = new JPanel();
			jPanel_principal.setLayout(null);
			jPanel_principal.add(getJProgressBar(), null);
			jPanel_principal.add(getJToolBar(), null);
			
		}
		return jPanel_principal;
	}

	/**
	 * This method initializes jPanel_acerca	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public JPanel getJPanel_acerca() {
		if (jPanel_acerca == null) {
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(5, 3, 55, 45));
			jLabel.setFont(new Font("Dialog", Font.BOLD, 24));
			jLabel.setForeground(Color.white);
			jLabel.setIcon(new ImageIcon(getClass().getResource("/icons/chrome.png")));
			jLabel.setText("");
			jPanel_acerca = new JPanel();
			jPanel_acerca.setLayout(null);
			_version = new JLabel();
			_version.setBounds(new Rectangle(369, 4, 71, 17));
			_version.setFont(new Font("Dialog", Font.PLAIN, 10));
			_version.setHorizontalAlignment(SwingConstants.RIGHT);
			_version.setForeground(Color.white);
			_version.setText("");
			jPanel_acerca.add(_version, null);
			jPanel_acerca.add(get_bar_memory(), null);
			jPanel_acerca.add(jLabel, null);
			//jPanel_acerca.add(getJPanel(), null);
			
		}
		return jPanel_acerca;
	}

	/**
	 * This method initializes jPanel_agenda	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel_agenda() {
		if (jPanel_agenda == null) {
			jPanel_agenda = new JPanel();
			jPanel_agenda.setLayout(null);
		}
		return jPanel_agenda;
	}

	/**
	 * This method initializes jPanel_messenger	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel_messenger() {
		if (jPanel_messenger == null) {
			jPanel_messenger = new JPanel();
			jPanel_messenger.setLayout(null);
		}
		return jPanel_messenger;
	}

	/**
	 * This method initializes jProgressBar	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	public JProgressBar getJProgressBar() {
		if (jProgressBar == null) {
			jProgressBar = new JProgressBar();
			jProgressBar.setBounds(new Rectangle(1, 217, 516, 14));
			jProgressBar.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return jProgressBar;
	}

	/**
	 * This method initializes _bar_memory	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	public JProgressBar get_bar_memory() {
		if (_bar_memory == null) {
			_bar_memory = new JProgressBar();
			_bar_memory.setBounds(new Rectangle(1, 217, 443, 14));
			_bar_memory.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _bar_memory;
	}
	private DisplayCanvas canvas=null;
	private JButton _btn_salir = null;
	private JToolBar jToolBar = null;
	private JButton _btn_maximizar = null;
	private JLabel jLabel = null;
	private JButton _btn_reconnectar = null;
	private JPanel jPanel = null;
	private JButton _btn_ayuda = null;
	public DisplayCanvas getCanvas(){
		if (canvas==null){
			canvas = new DisplayCanvas();
			canvas.setBounds(new Rectangle(5, 26, 1032, 187));	
		}
		
		return canvas;
	
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

	/**
	 * This method initializes jToolBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	public JToolBar getJToolBar() {
		if (jToolBar == null) {
			jToolBar = new JToolBar();
			jToolBar.setBounds(new Rectangle(4, 3, 512, 20));
			jToolBar.setFloatable(false);
			jToolBar.add(get_btn_maximizar());
			jToolBar.add(get_btn_minimizar());
			jToolBar.add(get_btn_reconnectar());
			jToolBar.add(get_btn_ayuda());
			jToolBar.add(get_btn_salir());
		}
		return jToolBar;
	}

	/**
	 * This method initializes _btn_maximizar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_maximizar() {
		if (_btn_maximizar == null) {
			_btn_maximizar = new JButton();
			_btn_maximizar.setToolTipText("Maximizar");
			_btn_maximizar.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-media-play-up.png")));
		}
		return _btn_maximizar;
	}

	/**
	 * This method initializes _btn_reconnectar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_reconnectar() {
		if (_btn_reconnectar == null) {
			_btn_reconnectar = new JButton();
			_btn_reconnectar.setIcon(new ImageIcon(getClass().getResource("/icons/stock_update-data.png")));
			_btn_reconnectar.setToolTipText("Reconectar al Servidor");
		}
		return _btn_reconnectar;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
			jPanel.setBounds(new Rectangle(16, 60, 476, 144));
		}
		return jPanel;
	}

	/**
	 * This method initializes _btn_ayuda	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_ayuda() {
		if (_btn_ayuda == null) {
			_btn_ayuda = new JButton();
			_btn_ayuda.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-help-16.png")));
			_btn_ayuda.setToolTipText("Ayuda Soporte Tecnico Beta");
		}
		return _btn_ayuda;
	}
	
	
}  //  @jve:decl-index=0:visual-constraint="10,15"
