package aplicacion.sistema.query.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import java.awt.Rectangle;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JToolBar jToolBar = null;
	private JButton _btn_cargar = null;
	private JButton _btn_salir = null;
	
	private JTextField _txt_idquery = null;
	private JLabel jLabel = null;
	private JButton _btn_cancelar = null;
	private JTextField _txt_idproveedor = null;
	private JLabel jLabel1 = null;
	private JButton _btn_aplicaciones = null;
	private JButton _btn_buscar_usuario = null;
	private JButton _btn_guardar = null;
	private JButton _btn_eliminar = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private JButton _btn_error = null;
	private JButton _btn_play = null;
	private JLabel jLabel2 = null;
	private JTextField _txt_tabla = null;
	private JLabel jLabel3 = null;
	private JLabel jLabel4 = null;
	private JTextField _txt_odbc = null;
	private JCheckBox _chk_limit = null;
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
		this.setSize(671, 438);
		this.setContentPane(getJContentPane());
		this.setTitle("Consultas");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(24, 98, 127, 18));
			jLabel4.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel4.setText("ODBC");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(475, 155, 179, 20));
			jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3.setText("Definicion de Columnas");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(29, 129, 122, 18));
			jLabel2.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2.setText("Tabla");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(23, 75, 130, 14));
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setText("idProveedor");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(21, 45, 130, 13));
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setText("idquery");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(get_txt_idquery(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(get_txt_idproveedor(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(get_btn_buscar_usuario(), null);
			jContentPane.add(get_btn_cargar(), null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(get_txt_tabla(), null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(jLabel4, null);
			jContentPane.add(get_txt_odbc(), null);
			jContentPane.add(get_chk_limit(), null);
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
			jToolBar.setBounds(new Rectangle(0, 0, 452, 24));
			jToolBar.setFloatable(false);
			jToolBar.add(get_btn_cancelar());
			jToolBar.add(get_btn_guardar());
			jToolBar.add(get_btn_aplicaciones());
			jToolBar.add(get_btn_eliminar());
			jToolBar.add(get_btn_salir());
			jToolBar.add(get_btn_error());
			jToolBar.add(get_btn_play());
		}
		return jToolBar;
	}

	/**
	 * This method initializes _btn_cargar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cargar() {
		if (_btn_cargar == null) {
			_btn_cargar = new JButton();
			_btn_cargar.setIcon(new ImageIcon(getClass().getResource("/icons/reload3.png")));
			_btn_cargar.setToolTipText("Cargar");
			_btn_cargar.setBounds(new Rectangle(358, 42, 23, 18));
		}
		return _btn_cargar;
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
	 * This method initializes _txt_idquery	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idquery() {
		if (_txt_idquery == null) {
			_txt_idquery = new JTextField();
			_txt_idquery.setToolTipText("Filtrar Parametros");
			_txt_idquery.setBounds(new Rectangle(163, 44, 160, 16));
		}
		return _txt_idquery;
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
			_btn_cancelar.setToolTipText("Cancelar Edicion");
		}
		return _btn_cancelar;
	}

	/**
	 * This method initializes _txt_idproveedor	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idproveedor() {
		if (_txt_idproveedor == null) {
			_txt_idproveedor = new JTextField();
			_txt_idproveedor.setBounds(new Rectangle(164, 74, 157, 16));
		}
		return _txt_idproveedor;
	}

	/**
	 * This method initializes _btn_aplicaciones	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_aplicaciones() {
		if (_btn_aplicaciones == null) {
			_btn_aplicaciones = new JButton();
			_btn_aplicaciones.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-run.png")));
			_btn_aplicaciones.setToolTipText("Configurar Aplicaciones");
		}
		return _btn_aplicaciones;
	}

	/**
	 * This method initializes _btn_buscar_usuario	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_usuario() {
		if (_btn_buscar_usuario == null) {
			_btn_buscar_usuario = new JButton();
			_btn_buscar_usuario.setBounds(new Rectangle(330, 42, 23, 18));
			_btn_buscar_usuario.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-searchtool.png")));
			_btn_buscar_usuario.setToolTipText("Buscar Usuario");
		}
		return _btn_buscar_usuario;
	}

	/**
	 * This method initializes _btn_guardar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_guardar() {
		if (_btn_guardar == null) {
			_btn_guardar = new JButton();
			_btn_guardar.setIcon(new ImageIcon(getClass().getResource("/icons/filesave.png")));
			_btn_guardar.setToolTipText("Guardar");
		}
		return _btn_guardar;
	}

	/**
	 * This method initializes _btn_eliminar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_eliminar() {
		if (_btn_eliminar == null) {
			_btn_eliminar = new JButton();
			_btn_eliminar.setIcon(new ImageIcon(getClass().getResource("/icons/edit-delete.png")));
			_btn_eliminar.setToolTipText("Eliminar Usuario");
		}
		return _btn_eliminar;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(18, 179, 639, 208));
			jScrollPane.setViewportView(getJTable());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTable1	
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
	 * This method initializes _btn_error	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_error() {
		if (_btn_error == null) {
			_btn_error = new JButton();
			_btn_error.setIcon(new ImageIcon(getClass().getResource("/icons/media-record.png")));
		}
		return _btn_error;
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
			_btn_play.setToolTipText("Restaurar Session Desde XML");
		}
		return _btn_play;
	}

	/**
	 * This method initializes _txt_tabla	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_tabla() {
		if (_txt_tabla == null) {
			_txt_tabla = new JTextField();
			_txt_tabla.setBounds(new Rectangle(165, 129, 154, 15));
		}
		return _txt_tabla;
	}

	/**
	 * This method initializes _txt_odbc	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_odbc() {
		if (_txt_odbc == null) {
			_txt_odbc = new JTextField();
			_txt_odbc.setBounds(new Rectangle(163, 99, 157, 15));
		}
		return _txt_odbc;
	}

	/**
	 * This method initializes _chk_limit	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_limit() {
		if (_chk_limit == null) {
			_chk_limit = new JCheckBox();
			_chk_limit.setBounds(new Rectangle(164, 150, 153, 18));
			_chk_limit.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_limit.setText("utiliza limit en vez de top");
		}
		return _chk_limit;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
