package aplicacion.sistema.update.gui;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JToolBar;
import java.awt.Dimension;
import javax.swing.JProgressBar;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JTree;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	public JButton _btn_cancelar = null;

	private JToolBar jToolBar = null;

	private JButton _btn_salir = null;

	private JTable jTable = null;

	private JProgressBar jProgressBar = null;

	private JButton _btn_cancelar_operacion = null;

	private JButton _btn_error = null;

	private JTextField _txt_idcomprobante = null;

	private JLabel jLabel4 = null;

	private JButton _btn_nuevo = null;

	private JButton _btn_cargar = null;

	private JScrollPane jScrollPane = null;

	private JTextArea _txt_comentario = null;

	private JScrollPane jScrollPane1 = null;

	private JTree jTree = null;

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
		this.setSize(721, 351);
		this.setResizable(false);
		this.setContentPane(getJContentPane());
		this.setTitle("Exportar Actualizacion");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(5, 31, 66, 16));
			jLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel4.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel4.setText("version");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(getJProgressBar(), null);
			jContentPane.add(get_btn_cancelar_operacion(), null);
			jContentPane.add(get_txt_idcomprobante(), null);
			jContentPane.add(jLabel4, null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(getJScrollPane1(), null);
		}
		return jContentPane;
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
			_btn_cancelar.setToolTipText("Cancelar");
		}
		return _btn_cancelar;
	}





	/**
	 * This method initializes jToolBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar() {
		if (jToolBar == null) {
			jToolBar = new JToolBar();
			jToolBar.setBounds(new Rectangle(4, 2, 707, 23));
			jToolBar.setFloatable(false);
			jToolBar.add(get_btn_cancelar());
			jToolBar.add(get_btn_nuevo());
			jToolBar.add(get_btn_cargar());
			jToolBar.addSeparator();
			jToolBar.add(get_btn_error());
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
			_btn_salir.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-logout.png")));
		}
		return _btn_salir;
	}





	/**
	 * This method initializes jProgressBar	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	public JProgressBar getJProgressBar() {
		if (jProgressBar == null) {
			jProgressBar = new JProgressBar();
			jProgressBar.setBounds(new Rectangle(74, 287, 564, 19));
			jProgressBar.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return jProgressBar;
	}





	/**
	 * This method initializes _btn_cancelar_operacion	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cancelar_operacion() {
		if (_btn_cancelar_operacion == null) {
			_btn_cancelar_operacion = new JButton();
			_btn_cancelar_operacion.setBounds(new Rectangle(643, 287, 22, 19));
			_btn_cancelar_operacion.setIcon(new ImageIcon(getClass().getResource("/icons/stock_calc-cancel.png")));
		}
		return _btn_cancelar_operacion;
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
			_btn_error.setToolTipText("Envio de Informacion/Error a Sistemas");
		}
		return _btn_error;
	}





	/**
	 * This method initializes _txt_idcomprobante	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idcomprobante() {
		if (_txt_idcomprobante == null) {
			_txt_idcomprobante = new JTextField();
			_txt_idcomprobante.setBounds(new Rectangle(77, 30, 114, 19));
		}
		return _txt_idcomprobante;
	}





	/**
	 * This method initializes _btn_nuevo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_nuevo() {
		if (_btn_nuevo == null) {
			_btn_nuevo = new JButton();
			_btn_nuevo.setIcon(new ImageIcon(getClass().getResource("/icons/document-new.png")));
		}
		return _btn_nuevo;
	}





	/**
	 * This method initializes _btn_cargar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cargar() {
		if (_btn_cargar == null) {
			_btn_cargar = new JButton();
			_btn_cargar.setIcon(new ImageIcon(getClass().getResource("/icons/stock_mail-send-receive.png")));
			_btn_cargar.setToolTipText("Exportar Actualizacion a Internet");
		}
		return _btn_cargar;
	}





	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(78, 59, 466, 44));
			jScrollPane.setViewportView(get_txt_comentario());
		}
		return jScrollPane;
	}





	/**
	 * This method initializes _txt_comentario	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea get_txt_comentario() {
		if (_txt_comentario == null) {
			_txt_comentario = new JTextArea();
		}
		return _txt_comentario;
	}





	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setBounds(new Rectangle(78, 120, 516, 157));
			jScrollPane1.setViewportView(getJTree());
		}
		return jScrollPane1;
	}





	/**
	 * This method initializes jTree	
	 * 	
	 * @return javax.swing.JTree	
	 */
	public JTree getJTree() {
		if (jTree == null) {
			jTree = new JTree();
		}
		return jTree;
	}

	public void setJTree(JTree tree){
		this.jTree=tree;
		this.jScrollPane1.setViewportView(this.jTree);
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
