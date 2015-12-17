package aplicacion.actualizacion.fotos.gui;

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

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	public JTextField _txt_archivo = null;

	public JButton _btn_buscar_archivo = null;

	private JLabel jLabel1 = null;

	public JTextField _txt_idproveedor = null;

	private JLabel jLabel2 = null;

	public JTextField _txt_politica = null;

	public JButton _btn_actualizar = null;
	
	public JTextField _txt_proveedor_descripcion = null;
	public JButton _btn_columnas = null;
	public JTextField _txt_descripcion_politica = null;

	private JLabel jLabel3 = null;

	public JTextField _txt_linea = null;

	public JButton _btn_cancelar = null;

	private JToolBar jToolBar = null;

	private JButton _btn_salir = null;

	private JButton _btn_buscar_proveedor = null;

	private JButton _btn_buscar_politica = null;

	private JButton _btn_buscar_linea = null;

	private JScrollPane jScrollPane = null;

	private JTable jTable = null;

	private JButton _btn_cargar_archivo = null;

	private JProgressBar jProgressBar = null;

	private JButton _btn_cancelar_operacion = null;

	private JButton _btn_nueva_politica = null;

	private JButton _btn_editar_politica = null;

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
		this.setSize(850, 400);
		this.setResizable(false);
		this.setContentPane(getJContentPane());
		this.setTitle("Actualizar por Archivo");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(6, 86, 160, 19));
			jLabel3.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3.setText("Linea Predeterminada");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(6, 55, 160, 20));
			jLabel2.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2.setText("Politica Predeterminada");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(6, 27, 160, 17));
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setText("Proveedor");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(6, 120, 160, 16));
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setText("Archivo de Texto (Tabulado)");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(get_txt_archivo(), null);
			jContentPane.add(get_btn_buscar_archivo(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(get_txt_idproveedor(), null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(get_txt_politica(), null);
			jContentPane.add(get_txt_proveedor_descripcion(), null);
			jContentPane.add(get_btn_columnas(), null);
			jContentPane.add(get_txt_descripcion_politica(), null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(get_txt_linea(), null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(get_btn_buscar_proveedor(), null);
			jContentPane.add(get_btn_buscar_politica(), null);
			jContentPane.add(get_btn_buscar_linea(), null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(get_btn_cargar_archivo(), null);
			jContentPane.add(getJProgressBar(), null);
			jContentPane.add(get_btn_cancelar_operacion(), null);
			jContentPane.add(get_btn_nueva_politica(), null);
			jContentPane.add(get_btn_editar_politica(), null);
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
			_txt_archivo.setBounds(new Rectangle(170, 119, 335, 19));
			_txt_archivo.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_archivo;
	}
	/**
	 * This method initializes _btn_buscar_archivo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_archivo() {
		if (_btn_buscar_archivo == null) {
			_btn_buscar_archivo = new JButton();
			_btn_buscar_archivo.setBounds(new Rectangle(507, 120, 22, 19));
			_btn_buscar_archivo.setIcon(new ImageIcon(getClass().getResource("/icons/kfind.png")));
			
		}
		return _btn_buscar_archivo;
	}

	/**
	 * This method initializes _txt_idproveedor	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idproveedor() {
		if (_txt_idproveedor == null) {
			_txt_idproveedor = new JTextField();
			_txt_idproveedor.setBounds(new Rectangle(170, 27, 115, 19));
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
			_txt_politica.setBounds(new Rectangle(170, 57, 114, 20));
			_txt_politica.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_politica;
	}

	/**
	 * This method initializes _btn_actualizar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_actualizar() {
		if (_btn_actualizar == null) {
			_btn_actualizar = new JButton();
			_btn_actualizar.setIcon(new ImageIcon(getClass().getResource("/icons/stock_media-play.png")));
			_btn_actualizar.setToolTipText("Actualizar");
		}
		return _btn_actualizar;
	}
	
	
	/**
	 * This method initializes _txt_proveedor_descripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_proveedor_descripcion() {
		if (_txt_proveedor_descripcion == null) {
			_txt_proveedor_descripcion = new JTextField();
			_txt_proveedor_descripcion.setBounds(new Rectangle(312, 28, 296, 18));
			_txt_proveedor_descripcion.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_proveedor_descripcion.setEditable(false);
		}
		return _txt_proveedor_descripcion;
	}

	/**
	 * This method initializes _btn_columnas	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_columnas() {
		if (_btn_columnas == null) {
			_btn_columnas = new JButton();
			_btn_columnas.setBounds(new Rectangle(9, 147, 22, 19));
			_btn_columnas.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-run.png")));
			_btn_columnas.setToolTipText("* Definir Columnas de Archivo");
		}
		return _btn_columnas;
	}
	/**
	 * This method initializes _txt_descripcion_politica	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_descripcion_politica() {
		if (_txt_descripcion_politica == null) {
			_txt_descripcion_politica = new JTextField();
			_txt_descripcion_politica.setBounds(new Rectangle(366, 56, 240, 20));
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
			_txt_linea.setBounds(new Rectangle(170, 86, 241, 20));
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
			jToolBar.setBounds(new Rectangle(4, 2, 837, 21));
			jToolBar.setFloatable(false);
			jToolBar.add(get_btn_cancelar());
			jToolBar.add(get_btn_actualizar());
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
			_btn_buscar_proveedor.setBounds(new Rectangle(288, 26, 20, 19));
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
			_btn_buscar_politica.setBounds(new Rectangle(287, 57, 20, 18));
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
			_btn_buscar_linea.setBounds(new Rectangle(413, 85, 26, 20));
			_btn_buscar_linea.setIcon(new ImageIcon(getClass().getResource("/icons/kfind.png")));
		}
		return _btn_buscar_linea;
	}





	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(18, 192, 789, 152));
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
		this.jScrollPane.setViewportView(this.jTable);
	}





	/**
	 * This method initializes _btn_cargar_archivo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cargar_archivo() {
		if (_btn_cargar_archivo == null) {
			_btn_cargar_archivo = new JButton();
			_btn_cargar_archivo.setBounds(new Rectangle(532, 120, 22, 19));
			_btn_cargar_archivo.setIcon(new ImageIcon(getClass().getResource("/icons/stock_refresh.png")));
		}
		return _btn_cargar_archivo;
	}





	/**
	 * This method initializes jProgressBar	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	public JProgressBar getJProgressBar() {
		if (jProgressBar == null) {
			jProgressBar = new JProgressBar();
			jProgressBar.setBounds(new Rectangle(38, 147, 545, 19));
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
			_btn_cancelar_operacion.setBounds(new Rectangle(588, 147, 22, 19));
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
			_btn_nueva_politica.setBounds(new Rectangle(312, 57, 20, 18));
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
			_btn_editar_politica.setBounds(new Rectangle(337, 57, 20, 18));
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


}  //  @jve:decl-index=0:visual-constraint="10,10"
