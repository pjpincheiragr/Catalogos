package aplicacion.sistema.menu.gui;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Rectangle;
import java.net.URL;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JProgressBar;
import java.awt.Font;
import javax.swing.SwingConstants;

import aplicacion.compras.carga.items.logic.DisplayCanvas;

import java.awt.SystemColor;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.JTextField;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private DisplayCanvas canvas=null;
	private JProgressBar jProgressBar = null;
	public JLabel _version = null;
	private JProgressBar _bar_memory = null;
	private JButton _btn_salir = null;
	private JToolBar jToolBar = null;
	private JButton _btn_anterior = null;
	private JButton _btn_siguiente = null;
	private JButton _btn_play = null;
	private JButton _btn_stop = null;
	private JButton _btn_eliminar = null;
	private JButton _btn_agregar = null;
	private JButton _btn_error = null;
	private JButton _btn_minimizar = null;
	private JScrollPane jScrollPane = null;
	private JTextArea jTextArea = null;
	private JButton _btn_kill = null;
	private JTextField _txt_codigo = null;
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
		this.setSize(1400, 1000);
		//this.setUndecorated(true);
		this.setContentPane(getJContentPane());
		this.setTitle("Beta RC 2010");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	public JPanel getJContentPane() {
		if (jContentPane == null) {
			_version = new JLabel();
			_version.setBounds(new Rectangle(1180, 759, 99, 17));
			_version.setFont(new Font("Dialog", Font.PLAIN, 10));
			_version.setHorizontalAlignment(SwingConstants.RIGHT);
			_version.setForeground(Color.white);
			_version.setText("");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJToolBar(), null);
			
			jContentPane.add(getJProgressBar(), null);
			
			jContentPane.add(_version, null);
			
			jContentPane.add(get_bar_memory(), null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(get_txt_codigo(), null);
			//jContentPane.add(get_btn_gestion_caja(), null);
			
			
		}
		return jContentPane;
	}

	public DisplayCanvas getCanvas(){
		if (canvas==null){
			canvas = new DisplayCanvas();
			canvas.setBounds(new Rectangle(5, 26, 1032, 187));	
		}
		
		return canvas;
	
	}
	/**
	 * This method initializes jProgressBar	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	public JProgressBar getJProgressBar() {
		if (jProgressBar == null) {
			jProgressBar = new JProgressBar();
			jProgressBar.setBounds(new Rectangle(9, 740, 609, 16));
			jProgressBar.setFont(new Font("Dialog", Font.BOLD, 10));
			jProgressBar.setToolTipText("Barra de Progreso de Carga de Aplicaciones");
			jProgressBar.setVisible(false);
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
			_bar_memory.setBounds(new Rectangle(12, 766, 100, 16));
			_bar_memory.setToolTipText("Memoria Disponible");
		}
		return _bar_memory;
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
			_btn_salir.setToolTipText("Salir del sistema");
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
			jToolBar.setBounds(new Rectangle(856, 18, 379, 26));
			jToolBar.setFloatable(false);
			//jToolBar.add(get_btn_consola());
			jToolBar.add(get_btn_agregar());
			jToolBar.add(get_btn_eliminar());
			jToolBar.add(get_btn_anterior());
			jToolBar.add(get_btn_stop());
			jToolBar.add(get_btn_play());
			jToolBar.add(get_btn_siguiente());
			jToolBar.add(get_btn_minimizar());
			jToolBar.addSeparator();
			jToolBar.add(get_btn_error());
			jToolBar.addSeparator();
			jToolBar.add(get_btn_salir());
			jToolBar.add(get_btn_kill());
		}
		return jToolBar;
	}

	/**
	 * This method initializes _btn_anterior	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_anterior() {
		if (_btn_anterior == null) {
			_btn_anterior = new JButton();
			_btn_anterior.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-media-rewind-ltr.png")));
			_btn_anterior.setToolTipText("Mostrar Imagen Anterior");
		}
		return _btn_anterior;
	}

	/**
	 * This method initializes _btn_siguiente	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_siguiente() {
		if (_btn_siguiente == null) {
			_btn_siguiente = new JButton();
			_btn_siguiente.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-media-rewind-rtl.png")));
			_btn_siguiente.setToolTipText("Mostrar Imagen Siguiente");
		}
		return _btn_siguiente;
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
			_btn_play.setToolTipText("Iniciar Reproduccion");
		}
		return _btn_play;
	}

	/**
	 * This method initializes _btn_stop	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_stop() {
		if (_btn_stop == null) {
			_btn_stop = new JButton();
			_btn_stop.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-media-pause.png")));
			_btn_stop.setToolTipText("Parar Reproduccion");
		}
		return _btn_stop;
	}

	/**
	 * This method initializes _btn_eliminar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_eliminar() {
		if (_btn_eliminar == null) {
			_btn_eliminar = new JButton();
			_btn_eliminar.setIcon(new ImageIcon(getClass().getResource("/icons/player_stop.png")));
		}
		return _btn_eliminar;
	}

	/**
	 * This method initializes _btn_agregar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_agregar() {
		if (_btn_agregar == null) {
			_btn_agregar = new JButton();
			_btn_agregar.setIcon(new ImageIcon(getClass().getResource("/icons/media-eject.png")));
			_btn_agregar.setToolTipText("Agregar Imagen");
		}
		return _btn_agregar;
	}

	/**
	 * This method initializes _btn_error	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_error() {
		if (_btn_error == null) {
			_btn_error = new JButton();
			_btn_error.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-media-record.png")));
			_btn_error.setToolTipText("Envio de Informacion a sistemas");
		}
		return _btn_error;
	}

	/**
	 * This method initializes _btn_minimizar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_minimizar() {
		if (_btn_minimizar == null) {
			_btn_minimizar = new JButton();
			_btn_minimizar.setIcon(new ImageIcon(getClass().getResource("/icons/system-shutdown.png")));
			_btn_minimizar.setToolTipText("Minimizar a la barra de tareas");
		}
		return _btn_minimizar;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	public JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(20, 60, 992, 125));
			jScrollPane.setViewportView(getJTextArea());
			jScrollPane.setVisible(false);
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea getJTextArea() {
		if (jTextArea == null) {
			jTextArea = new JTextArea();
			jTextArea.setLineWrap(true);
			jTextArea.setEditable(false);
			
		}
		return jTextArea;
	}

	/**
	 * This method initializes _btn_kill	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_kill() {
		if (_btn_kill == null) {
			_btn_kill = new JButton();
			_btn_kill.setIcon(new ImageIcon(getClass().getResource("/icons/edit-clear.png")));
			_btn_kill.setToolTipText("Cerrar Aplicaciones");
		}
		return _btn_kill;
	}

	/**
	 * This method initializes _txt_codigo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_codigo() {
		if (_txt_codigo == null) {
			_txt_codigo = new JTextField();
			_txt_codigo.setBounds(new Rectangle(33, 32, 124, 18));
		}
		return _txt_codigo;
	}
	
	
}  //  @jve:decl-index=0:visual-constraint="10,10"
