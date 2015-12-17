package aplicacion.actualizacion.codigo.gui;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JFrame;



import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.GridBagLayout;
import java.net.URL;

import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.JProgressBar;
import java.awt.Font;
/**
 * @author Agustin Wisky
 * @company Wismi S.A.
 * @since 10-10-2009
 */
public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JToolBar jToolBar = null;
	private JButton _btn_guardar = null;
	private JButton _btn_cancelar = null;
	private JButton _btn_salir = null;
	private JPanel jPanel = null;
	private JLabel jLabel = null;
	private JTextField _txt_codigo_desde = null;
	private JLabel jLabel1 = null;
	private JTextField _txt_codigo_hasta = null;
	private JButton _btn_buscar_desde = null;
	private JButton _btn_buscar_hasta = null;
	private JButton _btn_cargar = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private JCheckBox _chk_marcar = null;
	private JLabel jLabel2 = null;
	private JTextField _txt_idproveedor = null;
	private JTextField _txt_proveedor_descripcion = null;
	private JLabel jLabel3 = null;
	private JTextField _txt_linea = null;
	private JButton _btn_buscar_linea = null;
	private JButton _btn_buscar_proveedor = null;
	private JButton _btn_completar = null;
	private JToolBar jToolBar1 = null;
	private JButton _btn_borrar = null;
	private JProgressBar JProgressBar = null;
	private JButton _btn_cancelar_operacion = null;
	private JButton _btn_exportar = null;
	private JButton _btn_importar = null;
	private JButton _btn_error = null;
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
		this.setSize(1024, 740);
		this.setContentPane(getJContentPane());
		this.setTitle("Planilla Edicion de Codigos de Proveedor");
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
			jContentPane.add(getJPanel(), null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(get_chk_marcar(), null);
			jContentPane.add(getJToolBar1(), null);
			jContentPane.add(getJProgressBar(), null);
			jContentPane.add(get_btn_cancelar_operacion(), null);
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
			jToolBar.setBounds(new Rectangle(4, 5, 980, 22));
			jToolBar.setFloatable(false);
			jToolBar.add(get_btn_guardar());
			jToolBar.add(get_btn_exportar());
			jToolBar.add(get_btn_importar());
			jToolBar.add(get_btn_cancelar());
			jToolBar.add(get_btn_salir());
			jToolBar.addSeparator();
			jToolBar.add(get_btn_error());
		}
		return jToolBar;
	}

	/**
	 * This method initializes _btn_guardar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_guardar() {
		if (_btn_guardar == null) {
			_btn_guardar = new JButton();
			_btn_guardar.setToolTipText("Guardar");
			URL resourceURL = getClass().getClassLoader().getResource("icons/filesave.png");
			_btn_guardar.setIcon(new ImageIcon(resourceURL));
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
			_btn_cancelar.setToolTipText("Cancelar Tarea en progreso");
			_btn_cancelar.setBounds(new Rectangle(647, 190, 28, 18));
			URL resourceURL = getClass().getClassLoader().getResource("icons/stock_calc-cancel.png");
			_btn_cancelar.setIcon(new ImageIcon(resourceURL));
		}
		return _btn_cancelar;
	}

	/**
	 * This method initializes _btn_salir	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_salir() {
		if (_btn_salir == null) {
			_btn_salir = new JButton();
			URL resourceURL = getClass().getClassLoader().getResource("icons/exit.png");
			_btn_salir.setIcon(new ImageIcon(resourceURL));
		}
		return _btn_salir;
	}
	
	
	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(16, 40, 102, 19));
			jLabel3.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel3.setText("Linea");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(14, 16, 102, 14));
			jLabel2.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel2.setText("idproveedor:");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(287, 69, 66, 18));
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setText("Hasta:");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(12, 70, 106, 15));
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setText("Codigo Desde:");
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBounds(new Rectangle(16, 40, 987, 112));
			jPanel.setBackground(new Color(204, 204, 204));
			jPanel.add(jLabel, null);
			jPanel.add(get_txt_codigo_desde(), null);
			jPanel.add(jLabel1, null);
			jPanel.add(get_txt_codigo_hasta(), null);
			jPanel.add(get_btn_buscar_desde(), null);
			jPanel.add(get_btn_buscar_hasta(), null);
			jPanel.add(get_btn_cargar(), null);
			jPanel.add(jLabel2, null);
			jPanel.add(get_txt_idproveedor(), null);
			jPanel.add(get_txt_proveedor_descripcion(), null);
			jPanel.add(jLabel3, null);
			jPanel.add(get_txt_linea(), null);
			jPanel.add(get_btn_buscar_linea(), null);
			jPanel.add(get_btn_buscar_proveedor(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes _txt_codigo_desde	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_codigo_desde() {
		if (_txt_codigo_desde == null) {
			_txt_codigo_desde = new JTextField();
			_txt_codigo_desde.setBounds(new Rectangle(126, 67, 125, 18));
			_txt_codigo_desde.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_codigo_desde;
	}

	/**
	 * This method initializes _txt_codigo_hasta	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_codigo_hasta() {
		if (_txt_codigo_hasta == null) {
			_txt_codigo_hasta = new JTextField();
			_txt_codigo_hasta.setBounds(new Rectangle(361, 68, 145, 18));
			_txt_codigo_hasta.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_codigo_hasta;
	}

	/**
	 * This method initializes _btn_buscar_desde	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_desde() {
		if (_btn_buscar_desde == null) {
			_btn_buscar_desde = new JButton();
			_btn_buscar_desde.setBounds(new Rectangle(259, 66, 21, 20));
			URL resourceURL = getClass().getClassLoader().getResource("icons/search.png");
			_btn_buscar_desde.setIcon(new ImageIcon(resourceURL));
			
		}
		return _btn_buscar_desde;
	}

	/**
	 * This method initializes _btn_buscar_hasta	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_hasta() {
		if (_btn_buscar_hasta == null) {
			_btn_buscar_hasta = new JButton();
			_btn_buscar_hasta.setBounds(new Rectangle(513, 68, 24, 20));
			URL resourceURL = getClass().getClassLoader().getResource("icons/search.png");
			_btn_buscar_hasta.setIcon(new ImageIcon(resourceURL));
		}
		return _btn_buscar_hasta;
	}

	/**
	 * This method initializes _btn_cargar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cargar() {
		if (_btn_cargar == null) {
			_btn_cargar = new JButton();
			_btn_cargar.setBounds(new Rectangle(545, 68, 22, 21));
			URL resourceURL = getClass().getClassLoader().getResource("icons/stock_refresh.png");
			_btn_cargar.setIcon(new ImageIcon(resourceURL));
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
			jScrollPane.setBounds(new Rectangle(20, 248, 981, 430));
			jScrollPane.setViewportView(getJTable());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getJTable() {
		if (jTable == null) {
			jTable = new JTable();
		}
		return jTable;
	}

	public void setJTable(JTable table){
		 this.jTable=table;
		 this.jScrollPane.setViewportView(jTable);
	}
	
	/**
	 * This method initializes _chk_marcar	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_marcar() {
		if (_chk_marcar == null) {
			_chk_marcar = new JCheckBox();
			_chk_marcar.setBounds(new Rectangle(12, 227, 140, 15));
			_chk_marcar.setFont(new Font("Dialog", Font.BOLD, 10));
			_chk_marcar.setText("seleccionar");
		}
		return _chk_marcar;
	}

	/**
	 * This method initializes _txt_idproveedor	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idproveedor() {
		if (_txt_idproveedor == null) {
			_txt_idproveedor = new JTextField();
			_txt_idproveedor.setBounds(new Rectangle(125, 15, 121, 18));
			_txt_idproveedor.setFont(new Font("Dialog", Font.PLAIN, 10));
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
			_txt_proveedor_descripcion.setBounds(new Rectangle(287, 14, 303, 18));
			_txt_proveedor_descripcion.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_proveedor_descripcion.setEditable(false);
		}
		return _txt_proveedor_descripcion;
	}

	/**
	 * This method initializes _txt_linea	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_linea() {
		if (_txt_linea == null) {
			_txt_linea = new JTextField();
			_txt_linea.setBounds(new Rectangle(127, 40, 264, 18));
			_txt_linea.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_linea;
	}

	/**
	 * This method initializes _btn_buscar_linea	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_linea() {
		if (_btn_buscar_linea == null) {
			_btn_buscar_linea = new JButton();
			_btn_buscar_linea.setBounds(new Rectangle(401, 39, 19, 17));
			URL resourceURL = getClass().getClassLoader().getResource("icons/search.png");
			_btn_buscar_linea.setIcon(new ImageIcon(resourceURL));
		}
		return _btn_buscar_linea;
	}

	/**
	 * This method initializes _btn_buscar_proveedor	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_proveedor() {
		if (_btn_buscar_proveedor == null) {
			_btn_buscar_proveedor = new JButton();
			_btn_buscar_proveedor.setBounds(new Rectangle(258, 15, 22, 17));
			URL resourceURL = getClass().getClassLoader().getResource("icons/search.png");
			_btn_buscar_proveedor.setIcon(new ImageIcon(resourceURL));
		}
		return _btn_buscar_proveedor;
	}

	/**
	 * This method initializes jToolBar1	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar1() {
		if (jToolBar1 == null) {
			jToolBar1 = new JToolBar();
			jToolBar1.setBounds(new Rectangle(18, 196, 974, 23));
			jToolBar1.setFloatable(false);
			jToolBar1.add(get_btn_borrar());
			jToolBar1.addSeparator();
			jToolBar1.add(get_btn_completar());
		}
		return jToolBar1;
	}

	/**
	 * This method initializes Borrar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_borrar() {
		if (_btn_borrar == null) {
			_btn_borrar = new JButton();
			URL resourceURL = getClass().getClassLoader().getResource("icons/user-trash.png");
			_btn_borrar.setIcon(new ImageIcon(resourceURL));
		}
		return _btn_borrar;
	}

	public JButton get_btn_completar() {
		if (_btn_completar == null) {
			_btn_completar = new JButton();
			_btn_completar.setToolTipText("Completar");
			URL resourceURL = getClass().getClassLoader().getResource("icons/bottom.png");
			_btn_completar.setIcon(new ImageIcon(resourceURL));
		}
		return _btn_completar;
	}

	/**
	 * This method initializes JProgressBar	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	public JProgressBar getJProgressBar() {
		if (JProgressBar == null) {
			JProgressBar = new JProgressBar();
			JProgressBar.setBounds(new Rectangle(17, 169, 623, 20));
		}
		return JProgressBar;
	}

	/**
	 * This method initializes _btn_cancelar_operacion	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cancelar_operacion() {
		if (_btn_cancelar_operacion == null) {
			_btn_cancelar_operacion = new JButton();
			_btn_cancelar_operacion.setBounds(new Rectangle(646, 169, 25, 19));
			URL resourceURL = getClass().getClassLoader().getResource("icons/stock_calc-cancel.png");
			_btn_cancelar_operacion.setIcon(new ImageIcon(resourceURL));
		}
		return _btn_cancelar_operacion;
	}

	/**
	 * This method initializes _btn_exportar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_exportar() {
		if (_btn_exportar == null) {
			_btn_exportar = new JButton();
			_btn_exportar.setIcon(new ImageIcon(getClass().getResource("/icons/go-top.png")));
			_btn_exportar.setToolTipText("Exportar Codigos");
		}
		return _btn_exportar;
	}

	/**
	 * This method initializes _btn_importar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_importar() {
		if (_btn_importar == null) {
			_btn_importar = new JButton();
			_btn_importar.setIcon(new ImageIcon(getClass().getResource("/icons/bottom.png")));
			_btn_importar.setToolTipText("Importart Codigos");
		}
		return _btn_importar;
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


}  //  @jve:decl-index=0:visual-constraint="10,10"
