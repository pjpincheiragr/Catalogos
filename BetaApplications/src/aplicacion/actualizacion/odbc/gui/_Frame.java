package aplicacion.actualizacion.odbc.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JToolBar;
import java.awt.Point;
import javax.swing.JTable;
import javax.swing.JProgressBar;
import javax.swing.JCheckBox;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JTextField _txt_idproveedor = null;
	private JTextField _txt_proveedor_descripcion = null;
	private JLabel jLabel1 = null;
	private JTextField _txt_odbc = null;
	private JLabel jLabel2 = null;
	private JTextField _txt_idpolitica = null;
	private JTextField _txt_politica_detalle = null;
	private JLabel jLabel3 = null;
	private JScrollPane jScrollPane = null;
	private JTextArea _txt_consulta = null;
	private JButton _btn_guardar = null;
	private JButton _btn_cancelar = null;
	private JButton _btn_test = null;
	private JToolBar jToolBar = null;
	private JButton _btn_salir = null;
	private JButton _btn_buscar_proveedor = null;
	private JButton _btn_buscar_politica = null;
	private JTable jTable = null;
	private JProgressBar jProgressBar = null;
	private JButton _btn_cancelar_tarea = null;
	private JCheckBox _chk_forzar = null;
	private JButton _btn_error = null;
	private JLabel jLabel5 = null;
	private JTextField _txt_linea = null;
	private JTextField _txt_idcomprobante = null;
	private JLabel jLabel6 = null;
	private JButton _btn_nuevo = null;
	private JLabel jLabel4 = null;
	private JTextField _txt_class = null;
	private JButton _btn_exportar = null;
	private JButton _btn_load = null;
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
		this.setSize(759, 316);
		this.setResizable(false);
		this.setContentPane(getJContentPane());
		this.setTitle("Catalogo ODBC");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(35, 68, 78, 17));
			jLabel4.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel4.setText("Class");
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(28, 27, 84, 15));
			jLabel6.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel6.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel6.setText("idcomprobante");
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(41, 132, 73, 16));
			jLabel5.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel5.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel5.setText("Linea");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(28, 154, 89, 18));
			jLabel3.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3.setText("Consulta");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(14, 110, 104, 17));
			jLabel2.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2.setText("politica de precio");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(36, 89, 80, 18));
			jLabel1.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setText("Nombre ODBC");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(34, 48, 79, 16));
			jLabel.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setText("idproveedor");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(get_txt_idproveedor(), null);
			jContentPane.add(get_txt_proveedor_descripcion(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(get_txt_odbc(), null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(get_txt_idpolitica(), null);
			jContentPane.add(get_txt_politica_detalle(), null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(get_btn_buscar_proveedor(), null);
			jContentPane.add(get_btn_buscar_politica(), null);
			jContentPane.add(getJProgressBar(), null);
			jContentPane.add(get_btn_cancelar_tarea(), null);
			jContentPane.add(get_chk_forzar(), null);
			jContentPane.add(jLabel5, null);
			jContentPane.add(get_txt_linea(), null);
			jContentPane.add(get_txt_idcomprobante(), null);
			jContentPane.add(jLabel6, null);
			jContentPane.add(jLabel4, null);
			jContentPane.add(get_txt_class(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes _txt_idproveedor	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idproveedor() {
		if (_txt_idproveedor == null) {
			_txt_idproveedor = new JTextField();
			_txt_idproveedor.setBounds(new Rectangle(115, 48, 107, 17));
		}
		return _txt_idproveedor;
	}

	/**
	 * This method initializes _txt_proveedor_descripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_proveedor_descripcion() {
		if (_txt_proveedor_descripcion == null) {
			_txt_proveedor_descripcion = new JTextField();
			_txt_proveedor_descripcion.setBounds(new Rectangle(254, 49, 254, 16));
			_txt_proveedor_descripcion.setEditable(false);
			_txt_proveedor_descripcion.setEnabled(true);
		}
		return _txt_proveedor_descripcion;
	}

	/**
	 * This method initializes _txt_odbc	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_odbc() {
		if (_txt_odbc == null) {
			_txt_odbc = new JTextField();
			_txt_odbc.setBounds(new Rectangle(117, 89, 250, 17));
		}
		return _txt_odbc;
	}

	/**
	 * This method initializes _txt_idpolitica	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idpolitica() {
		if (_txt_idpolitica == null) {
			_txt_idpolitica = new JTextField();
			_txt_idpolitica.setBounds(new Rectangle(118, 110, 64, 16));
		}
		return _txt_idpolitica;
	}

	/**
	 * This method initializes _txt_politica_detalle	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_politica_detalle() {
		if (_txt_politica_detalle == null) {
			_txt_politica_detalle = new JTextField();
			_txt_politica_detalle.setBounds(new Rectangle(216, 110, 295, 15));
			_txt_politica_detalle.setEditable(false);
			_txt_politica_detalle.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_politica_detalle;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	public JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(118, 154, 619, 84));
			jScrollPane.setViewportView(get_txt_consulta());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes _txt_consulta	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea get_txt_consulta() {
		if (_txt_consulta == null) {
			_txt_consulta = new JTextArea();
			_txt_consulta.setLineWrap(true);
			_txt_consulta.setToolTipText("La consulta debe formar un resultado del tipo codigo,descripcion,linea,precio de lo contrario fallara la actualizacion");
			
			_txt_consulta.setWrapStyleWord(true);
		}
		return _txt_consulta;
	}

	/**
	 * This method initializes _btn_guardar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_guardar() {
		if (_btn_guardar == null) {
			_btn_guardar = new JButton();
			_btn_guardar.setIcon(new ImageIcon(getClass().getResource("/icons/stock_save.png")));
			_btn_guardar.setToolTipText("Guardar Cambios de Configuracion");
		}
		return _btn_guardar;
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
	 * This method initializes _btn_test	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_test() {
		if (_btn_test == null) {
			_btn_test = new JButton();
			_btn_test.setToolTipText("Comenzar Actualizacion");
			_btn_test.setEnabled(false);
			_btn_test.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-run.png")));
		}
		return _btn_test;
	}

	/**
	 * This method initializes jToolBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar() {
		if (jToolBar == null) {
			jToolBar = new JToolBar();
			jToolBar.setSize(new Dimension(751, 23));
			jToolBar.setLocation(new Point(0, 0));
			jToolBar.setFloatable(false);
			jToolBar.add(get_btn_cancelar());
			jToolBar.add(get_btn_nuevo());
			jToolBar.add(get_btn_guardar());
			jToolBar.add(get_btn_test());
			jToolBar.add(get_btn_load());
			jToolBar.add(get_btn_exportar());
			jToolBar.add(get_btn_salir());
			jToolBar.addSeparator();
			jToolBar.add(get_btn_error());
			
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
			_btn_buscar_proveedor.setBounds(new Rectangle(225, 48, 24, 16));
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
			_btn_buscar_politica.setBounds(new Rectangle(184, 109, 25, 17));
			_btn_buscar_politica.setIcon(new ImageIcon(getClass().getResource("/icons/kfind.png")));
		}
		return _btn_buscar_politica;
	}

	

	/**
	 * This method initializes jProgressBar	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	public JProgressBar getJProgressBar() {
		if (jProgressBar == null) {
			jProgressBar = new JProgressBar();
			jProgressBar.setBounds(new Rectangle(119, 246, 590, 19));
		}
		return jProgressBar;
	}

	/**
	 * This method initializes _btn_cancelar_tarea	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cancelar_tarea() {
		if (_btn_cancelar_tarea == null) {
			_btn_cancelar_tarea = new JButton();
			_btn_cancelar_tarea.setBounds(new Rectangle(714, 246, 22, 20));
			_btn_cancelar_tarea.setIcon(new ImageIcon(getClass().getResource("/icons/stock_calc-cancel.png")));
		}
		return _btn_cancelar_tarea;
	}

	/**
	 * This method initializes _chk_forzar	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_forzar() {
		if (_chk_forzar == null) {
			_chk_forzar = new JCheckBox();
			_chk_forzar.setBounds(new Rectangle(514, 48, 219, 17));
			_chk_forzar.setFont(new Font("Dialog", Font.BOLD, 10));
			_chk_forzar.setText("forzar actualizacion completa");
		}
		return _chk_forzar;
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
	 * This method initializes _txt_linea	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_linea() {
		if (_txt_linea == null) {
			_txt_linea = new JTextField();
			_txt_linea.setBounds(new Rectangle(118, 132, 245, 16));
			_txt_linea.setToolTipText("Filtre la linea que desa actualizar. Limpie este campo para actualizar Todo");
		}
		return _txt_linea;
	}

	/**
	 * This method initializes _txt_idcomprobante	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idcomprobante() {
		if (_txt_idcomprobante == null) {
			_txt_idcomprobante = new JTextField();
			_txt_idcomprobante.setSize(new Dimension(105, 16));
			_txt_idcomprobante.setLocation(new Point(115, 26));
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
			_btn_nuevo.setToolTipText("Nueva Operacion de Actualizacion");
		}
		return _btn_nuevo;
	}

	/**
	 * This method initializes _txt_class	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_class() {
		if (_txt_class == null) {
			_txt_class = new JTextField();
			_txt_class.setSize(new Dimension(392, 18));
			_txt_class.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_class.setLocation(new Point(116, 68));
		}
		return _txt_class;
	}

	/**
	 * This method initializes _btn_exportar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_exportar() {
		if (_btn_exportar == null) {
			_btn_exportar = new JButton();
			_btn_exportar.setIcon(new ImageIcon(getClass().getResource("/icons/top.png")));
		}
		return _btn_exportar;
	}

	/**
	 * This method initializes _btn_load	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_load() {
		if (_btn_load == null) {
			_btn_load = new JButton();
			_btn_load.setIcon(new ImageIcon(getClass().getResource("/icons/reload.png")));
		}
		return _btn_load;
	}
}  //  @jve:decl-index=0:visual-constraint="9,17"
