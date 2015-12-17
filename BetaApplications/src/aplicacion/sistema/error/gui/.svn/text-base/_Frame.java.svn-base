package aplicacion.sistema.error.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JProgressBar;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JToolBar jToolBar = null;
	private JLabel jLabel = null;
	private JTextField _txt_asunto = null;
	private JLabel jLabel1 = null;
	private JScrollPane jScrollPane = null;
	private JTextArea jTextArea = null;
	private JButton _btn_enviar = null;
	private JButton _btn_guardar = null;
	private JButton _btn_salir = null;
	private JLabel jLabel2 = null;
	private JScrollPane jScrollPane1 = null;
	private JTextArea jTextArea1 = null;
	private JButton _btn_capturar = null;
	private JProgressBar jProgressBar = null;
	private JButton _btn_cancelar = null;
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
		this.setSize(691, 306);
		this.setResizable(false);
		this.setContentPane(getJContentPane());
		this.setTitle("Envio de Bug/Error/Requerimiento a Sistemas");
	}

	public JPanel getJContentPane() {
		if (jContentPane == null) {
			
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(12, 74, 70, 19));
			jLabel2.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel2.setText("Error");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(9, 178, 72, 17));
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setText("Comentario");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(12, 51, 70, 17));
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setText("Asunto");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(get_txt_asunto(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(getJScrollPane1(), null);
			jContentPane.add(getJProgressBar(), null);
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
			jToolBar.setBounds(new Rectangle(3, 3, 678, 24));
			jToolBar.setFloatable(false);
			jToolBar.add(get_btn_enviar());
			jToolBar.add(get_btn_cancelar());
			jToolBar.add(get_btn_guardar());
			jToolBar.add(get_btn_capturar());
			jToolBar.add(get_btn_salir());
		}
		return jToolBar;
	}

	/**
	 * This method initializes _txt_asunto	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_asunto() {
		if (_txt_asunto == null) {
			_txt_asunto = new JTextField();
			_txt_asunto.setBounds(new Rectangle(86, 49, 576, 20));
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
			jScrollPane.setBounds(new Rectangle(87, 178, 577, 73));
			jScrollPane.setViewportView(getJTextArea());
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
			jTextArea.setFont(new Font("Dialog", Font.PLAIN, 10));
			jTextArea.setWrapStyleWord(true);
			jTextArea.setLineWrap(true);
		}
		return jTextArea;
	}

	/**
	 * This method initializes _btn_enviar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_enviar() {
		if (_btn_enviar == null) {
			_btn_enviar = new JButton();
			_btn_enviar.setIcon(new ImageIcon(getClass().getResource("/icons/mail-forward.png")));
			_btn_enviar.setToolTipText("Enviar Email");
		}
		return _btn_enviar;
	}

	/**
	 * This method initializes _btn_guardar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_guardar() {
		if (_btn_guardar == null) {
			_btn_guardar = new JButton();
			_btn_guardar.setIcon(new ImageIcon(getClass().getResource("/icons/document-save.png")));
			_btn_guardar.setToolTipText("Guardar Informacion en XML");
			
		}
		return _btn_guardar;
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
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setBounds(new Rectangle(87, 74, 576, 81));
			jScrollPane1.setViewportView(getJTextArea1());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes jTextArea1	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea getJTextArea1() {
		if (jTextArea1 == null) {
			jTextArea1 = new JTextArea();
			jTextArea1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jTextArea1.setWrapStyleWord(true);
			jTextArea1.setLineWrap(true);
		}
		return jTextArea1;
	}

	/**
	 * This method initializes _btn_capturar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_capturar() {
		if (_btn_capturar == null) {
			_btn_capturar = new JButton();
			_btn_capturar.setIcon(new ImageIcon(getClass().getResource("/icons/lock.png")));
			_btn_capturar.setEnabled(false);
			_btn_capturar.setToolTipText("Crear una Imagen del Escritorio");
		}
		return _btn_capturar;
	}

	/**
	 * This method initializes jProgressBar	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	public JProgressBar getJProgressBar() {
		if (jProgressBar == null) {
			jProgressBar = new JProgressBar();
			jProgressBar.setBounds(new Rectangle(4, 28, 657, 16));
		}
		return jProgressBar;
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
			_btn_cancelar.setToolTipText("Cancelar Envio");
		}
		return _btn_cancelar;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
