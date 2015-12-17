package aplicacion.herramientas.java.ejemplo.gui;

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
import javax.swing.JComboBox;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JTable jTable = null;
	private JToolBar jToolBar = null;
	private JButton _btn_empezar = null;
	private JProgressBar jProgressBar = null;
	private JButton _btn_salir = null;
	private JButton _btn_cancelar = null;
	private JProgressBar jProgressBar1 = null;
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
		this.setSize(683, 110);
		this.setContentPane(getJContentPane());
		this.setTitle("Ejemplo");
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
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(getJProgressBar(), null);
			jContentPane.add(getJProgressBar1(), null);
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
			jToolBar.setLocation(new Point(3, 4));
			jToolBar.setFloatable(false);
			jToolBar.setSize(new Dimension(666, 23));
			jToolBar.add(get_btn_empezar());
			jToolBar.add(get_btn_cancelar());
			jToolBar.add(get_btn_salir());
		}
		return jToolBar;
	}

	/**
	 * This method initializes _btn_empezar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_empezar() {
		if (_btn_empezar == null) {
			_btn_empezar = new JButton();
			_btn_empezar.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-media-play-ltr.png")));
		}
		return _btn_empezar;
	}

	/**
	 * This method initializes jProgressBar	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	public JProgressBar getJProgressBar() {
		if (jProgressBar == null) {
			jProgressBar = new JProgressBar();
			jProgressBar.setLocation(new Point(4, 33));
			jProgressBar.setSize(new Dimension(663, 17));
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

	/**
	 * This method initializes _btn_cancelar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cancelar() {
		if (_btn_cancelar == null) {
			_btn_cancelar = new JButton();
			_btn_cancelar.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-stop.png")));
		}
		return _btn_cancelar;
	}

	/**
	 * This method initializes jProgressBar1	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	public JProgressBar getJProgressBar1() {
		if (jProgressBar1 == null) {
			jProgressBar1 = new JProgressBar();
			jProgressBar1.setSize(new Dimension(663, 17));
			jProgressBar1.setLocation(new Point(4, 54));
		}
		return jProgressBar1;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
