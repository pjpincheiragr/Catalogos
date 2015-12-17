package aplicacion.actualizacion.estock.gui;

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
/**
 * @author Agustin Wisky
 * @company Wismi S.A.
 * @since 10-10-2009
 */
public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	public JTextField _txt_archivo = null;

	private JLabel jLabel1 = null;

	public JTextField _txt_idproveedor = null;

	private JLabel jLabel2 = null;

	public JTextField _txt_politica = null;

	public JTextField _txt_proveedor_descripcion = null;
	public JTextField _txt_descripcion_politica = null;

	private JLabel jLabel3 = null;

	public JTextField _txt_linea = null;

	public JButton _btn_cancelar = null;

	private JToolBar jToolBar = null;

	private JButton _btn_salir = null;

	private JButton _btn_buscar_proveedor = null;

	private JButton _btn_buscar_politica = null;

	private JButton _btn_buscar_linea = null;

	private JTable jTable = null;

	private JProgressBar jProgressBar = null;

	private JButton _btn_cancelar_operacion = null;

	private JButton _btn_nueva_politica = null;

	private JButton _btn_editar_politica = null;

	private JButton _btn_error = null;

	private JTextField _txt_idcomprobante = null;

	private JLabel jLabel4 = null;

	private JButton _btn_nuevo = null;

	private JButton _btn_cargar = null;

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
		this.setSize(656, 257);
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
			jLabel4.setBounds(new Rectangle(62, 29, 104, 16));
			jLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel4.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel4.setText("idcomprobante");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(6, 115, 160, 19));
			jLabel3.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3.setText("Linea Predeterminada");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(6, 84, 160, 20));
			jLabel2.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2.setText("Politica Predeterminada");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(6, 56, 160, 17));
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setText("Proveedor");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(6, 149, 160, 16));
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setText("Archivo de Texto (Tabulado)");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(get_txt_archivo(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(get_txt_idproveedor(), null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(get_txt_politica(), null);
			jContentPane.add(get_txt_proveedor_descripcion(), null);
			jContentPane.add(get_txt_descripcion_politica(), null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(get_txt_linea(), null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(get_btn_buscar_proveedor(), null);
			jContentPane.add(get_btn_buscar_politica(), null);
			jContentPane.add(get_btn_buscar_linea(), null);
			jContentPane.add(getJProgressBar(), null);
			jContentPane.add(get_btn_cancelar_operacion(), null);
			jContentPane.add(get_btn_nueva_politica(), null);
			jContentPane.add(get_btn_editar_politica(), null);
			jContentPane.add(get_txt_idcomprobante(), null);
			jContentPane.add(jLabel4, null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes _txt_archivo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_archivo() {
		if (_txt_archivo == null) {
			_txt_archivo = new JTextField();
			_txt_archivo.setBounds(new Rectangle(170, 148, 439, 19));
			_txt_archivo.setEditable(false);
			_txt_archivo.setHorizontalAlignment(JTextField.LEFT);
			_txt_archivo.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_archivo;
	}
	/**
	 * This method initializes _txt_idproveedor	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idproveedor() {
		if (_txt_idproveedor == null) {
			_txt_idproveedor = new JTextField();
			_txt_idproveedor.setBounds(new Rectangle(170, 56, 115, 19));
			_txt_idproveedor.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_idproveedor;
	}

	
	
	
	/**
	 * This method initializes _txt_politica	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_politica() {
		if (_txt_politica == null) {
			_txt_politica = new JTextField();
			_txt_politica.setBounds(new Rectangle(170, 86, 114, 20));
			_txt_politica.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_politica;
	}

	/**
	 * This method initializes _txt_proveedor_descripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_proveedor_descripcion() {
		if (_txt_proveedor_descripcion == null) {
			_txt_proveedor_descripcion = new JTextField();
			_txt_proveedor_descripcion.setBounds(new Rectangle(312, 57, 296, 18));
			_txt_proveedor_descripcion.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_proveedor_descripcion.setEditable(false);
		}
		return _txt_proveedor_descripcion;
	}

	/**
	 * This method initializes _txt_descripcion_politica	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_descripcion_politica() {
		if (_txt_descripcion_politica == null) {
			_txt_descripcion_politica = new JTextField();
			_txt_descripcion_politica.setBounds(new Rectangle(366, 85, 240, 20));
			_txt_descripcion_politica.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_descripcion_politica.setEditable(false);
		}
		return _txt_descripcion_politica;
	}




	/**
	 * This method initializes _txt_linea	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_linea() {
		if (_txt_linea == null) {
			_txt_linea = new JTextField();
			_txt_linea.setBounds(new Rectangle(170, 115, 241, 20));
			_txt_linea.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_linea;
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
			jToolBar.setBounds(new Rectangle(4, 2, 641, 23));
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
	 * This method initializes _btn_buscar_proveedor	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_proveedor() {
		if (_btn_buscar_proveedor == null) {
			_btn_buscar_proveedor = new JButton();
			_btn_buscar_proveedor.setBounds(new Rectangle(288, 55, 20, 19));
			_btn_buscar_proveedor.setIcon(new ImageIcon(getClass().getResource("/icons/kfind.png")));
		}
		return _btn_buscar_proveedor;
	}





	/**
	 * This method initializes _btn_buscar_politica	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_politica() {
		if (_btn_buscar_politica == null) {
			_btn_buscar_politica = new JButton();
			_btn_buscar_politica.setBounds(new Rectangle(287, 86, 20, 18));
			_btn_buscar_politica.setIcon(new ImageIcon(getClass().getResource("/icons/kfind.png")));
		}
		return _btn_buscar_politica;
	}





	/**
	 * This method initializes _btn_buscar_linea	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_linea() {
		if (_btn_buscar_linea == null) {
			_btn_buscar_linea = new JButton();
			_btn_buscar_linea.setBounds(new Rectangle(413, 114, 26, 20));
			_btn_buscar_linea.setIcon(new ImageIcon(getClass().getResource("/icons/kfind.png")));
		}
		return _btn_buscar_linea;
	}










	/**
	 * This method initializes jProgressBar	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	public JProgressBar getJProgressBar() {
		if (jProgressBar == null) {
			jProgressBar = new JProgressBar();
			jProgressBar.setBounds(new Rectangle(19, 176, 564, 19));
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
			_btn_cancelar_operacion.setBounds(new Rectangle(588, 176, 22, 19));
			_btn_cancelar_operacion.setIcon(new ImageIcon(getClass().getResource("/icons/stock_calc-cancel.png")));
		}
		return _btn_cancelar_operacion;
	}





	/**
	 * This method initializes _btn_nueva_politica	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_nueva_politica() {
		if (_btn_nueva_politica == null) {
			_btn_nueva_politica = new JButton();
			_btn_nueva_politica.setToolTipText("Crear Nueva Politca");
			_btn_nueva_politica.setIcon(new ImageIcon(getClass().getResource("/icons/document-new.png")));
			_btn_nueva_politica.setBounds(new Rectangle(312, 86, 20, 18));
		}
		return _btn_nueva_politica;
	}





	/**
	 * This method initializes _btn_editar_politica	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_editar_politica() {
		if (_btn_editar_politica == null) {
			_btn_editar_politica = new JButton();
			_btn_editar_politica.setToolTipText("Editar Politica");
			_btn_editar_politica.setIcon(new ImageIcon(getClass().getResource("/icons/edit-find-replace.png")));
			_btn_editar_politica.setBounds(new Rectangle(337, 86, 20, 18));
		}
		return _btn_editar_politica;
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
			_txt_idcomprobante.setBounds(new Rectangle(171, 29, 114, 19));
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


}  //  @jve:decl-index=0:visual-constraint="10,10"
