package aplicacion.sistema.cataloguer.gui;

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
import java.awt.Point;
import javax.swing.JProgressBar;
import javax.swing.JList;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JToolBar jToolBar = null;
	private JButton _btn_cargar = null;
	private JButton _btn_salir = null;
	
	private JTextField _txt_idquery = null;
	private JLabel jLabel = null;
	private JButton _btn_cancelar = null;
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
	private JTextField _txt_linea = null;
	private JLabel jLabel4 = null;
	private JTextField _txt_descripcion = null;
	private JLabel jLabel5 = null;
	private JLabel jLabel6 = null;
	private JComboBox _lst_marca_vehiculo = null;
	private JComboBox _lst_vehiculo = null;
	private JComboBox _lst_codigo = null;
	private JComboBox _lst_desc_1 = null;
	private JComboBox _lst_desc_2 = null;
	private JComboBox _lst_desc_3 = null;
	private JComboBox _lst_linea = null;
	private JComboBox _lst_desc_4 = null;
	private JLabel jLabel7 = null;
	private JComboBox _lst_categoria = null;
	private JTextField _txt_categoria = null;
	private JComboBox _lst_categoria2 = null;
	private JButton _btn_importar = null;
	private JProgressBar jProgressBar = null;
	private JTextField _txt_categoria2 = null;
	private JScrollPane jScrollPane1 = null;
	private JTable jTable1 = null;
	private JLabel jLabel8 = null;
	private JComboBox _lst_desc_5 = null;
	private JComboBox _lst_desc_6 = null;
	private JComboBox _lst_precio = null;
	private JLabel jLabel9 = null;
	private JLabel jLabel3 = null;
	private JComboBox _lst_equiv_1 = null;
	private JComboBox _lst_equiv_2 = null;
	private JComboBox _lst_equiv_3 = null;
	private JComboBox _lst_linea_original = null;
	private JComboBox _lst_codigo_original = null;
	private JComboBox _lst_linea_reemplazo = null;
	private JComboBox _lst_codigo_reemplazo = null;
	private JComboBox _lst_odbc = null;
	private JLabel jLabel10 = null;
	private JLabel jLabel11 = null;
	private JLabel jLabel12 = null;
	private JLabel jLabel13 = null;
	private JLabel jLabel14 = null;
	private JTextField _txt_query_equivalencia = null;
	private JLabel jLabel15 = null;
	private JLabel jLabel16 = null;
	private JTextField _txt_odbc_imagen = null;
	private JComboBox _lst_imagen_codigo = null;
	private JComboBox _lst_imagen_linea = null;
	private JComboBox _lst_imagen = null;
	private JTextField _txt_imagen_directorio = null;
	private JLabel jLabel17 = null;
	private JLabel jLabel18 = null;
	private JLabel jLabel19 = null;
	private JLabel jLabel20 = null;
	private JCheckBox _chk_incorpora_articulos = null;
	private JCheckBox _chk_equivalencias = null;
	private JCheckBox _chk_incorpora_imagenes = null;
	private JCheckBox _chk_incorporar_enlaces = null;
	private JCheckBox _chk_limpia_linea = null;
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
		this.setSize(1024, 768);
		this.setContentPane(getJContentPane());
		this.setTitle("Cataloguer");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel20 = new JLabel();
			jLabel20.setBounds(new Rectangle(759, 253, 59, 16));
			jLabel20.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel20.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel20.setText("directorio");
			jLabel19 = new JLabel();
			jLabel19.setBounds(new Rectangle(757, 233, 58, 15));
			jLabel19.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel19.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel19.setText("imagen");
			jLabel18 = new JLabel();
			jLabel18.setBounds(new Rectangle(759, 213, 58, 13));
			jLabel18.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel18.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel18.setText("linea");
			jLabel17 = new JLabel();
			jLabel17.setBounds(new Rectangle(757, 193, 59, 14));
			jLabel17.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel17.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel17.setText("codigo");
			jLabel16 = new JLabel();
			jLabel16.setBounds(new Rectangle(757, 165, 58, 20));
			jLabel16.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel16.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel16.setText("Imagen");
			jLabel15 = new JLabel();
			jLabel15.setBounds(new Rectangle(405, 163, 113, 20));
			jLabel15.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel15.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel15.setText("idquery equivalencia");
			jLabel14 = new JLabel();
			jLabel14.setBounds(new Rectangle(416, 263, 98, 15));
			jLabel14.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel14.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel14.setText("Codigo Reemplazo");
			jLabel13 = new JLabel();
			jLabel13.setBounds(new Rectangle(425, 239, 89, 16));
			jLabel13.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel13.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel13.setText("Linea Reemplazo");
			jLabel12 = new JLabel();
			jLabel12.setBounds(new Rectangle(427, 216, 88, 16));
			jLabel12.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel12.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel12.setText("Codigo Original");
			jLabel11 = new JLabel();
			jLabel11.setBounds(new Rectangle(428, 195, 88, 16));
			jLabel11.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel11.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel11.setText("Linea Original");
			jLabel10 = new JLabel();
			jLabel10.setBounds(new Rectangle(25, 81, 127, 19));
			jLabel10.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel10.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel10.setText("ODBC");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(408, 84, 107, 15));
			jLabel3.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel3.setText("Equivalencia Directa");
			jLabel9 = new JLabel();
			jLabel9.setBounds(new Rectangle(34, 495, 119, 19));
			jLabel9.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel9.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel9.setText("precio");
			jLabel8 = new JLabel();
			jLabel8.setBounds(new Rectangle(932, 352, 56, 21));
			jLabel8.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel8.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel8.setText("FILTROS");
			jLabel7 = new JLabel();
			jLabel7.setBounds(new Rectangle(36, 345, 120, 16));
			jLabel7.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel7.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel7.setText("Categoria");
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(32, 471, 122, 15));
			jLabel6.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel6.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel6.setText("Vehiculo");
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(33, 439, 123, 20));
			jLabel5.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel5.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel5.setText("Marca de Vehiculo");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(24, 129, 127, 18));
			jLabel4.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel4.setText("Descripcion");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(36, 297, 122, 18));
			jLabel2.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2.setText("Linea de Producto");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(22, 109, 130, 14));
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setText("Codigo de Producto");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(22, 62, 130, 13));
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setText("idquery");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(get_txt_idquery(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(get_btn_buscar_usuario(), null);
			jContentPane.add(get_btn_cargar(), null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(get_txt_linea(), null);
			jContentPane.add(jLabel4, null);
			jContentPane.add(get_txt_descripcion(), null);
			jContentPane.add(jLabel5, null);
			jContentPane.add(jLabel6, null);
			jContentPane.add(get_lst_marca_vehiculo(), null);
			jContentPane.add(get_lst_vehiculo(), null);
			jContentPane.add(get_lst_codigo(), null);
			jContentPane.add(get_lst_desc_1(), null);
			jContentPane.add(get_lst_desc_2(), null);
			jContentPane.add(get_lst_desc_3(), null);
			jContentPane.add(get_lst_linea(), null);
			jContentPane.add(get_lst_desc_4(), null);
			jContentPane.add(jLabel7, null);
			jContentPane.add(get_lst_categoria(), null);
			jContentPane.add(get_txt_categoria(), null);
			jContentPane.add(get_lst_categoria2(), null);
			jContentPane.add(getJProgressBar(), null);
			jContentPane.add(get_txt_categoria2(), null);
			jContentPane.add(getJScrollPane1(), null);
			jContentPane.add(jLabel8, null);
			jContentPane.add(get_lst_desc_5(), null);
			jContentPane.add(get_lst_desc_6(), null);
			jContentPane.add(get_lst_precio(), null);
			jContentPane.add(jLabel9, null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(get_lst_equiv_1(), null);
			jContentPane.add(get_lst_equiv_2(), null);
			jContentPane.add(get_lst_equiv_3(), null);
			jContentPane.add(get_lst_linea_original(), null);
			jContentPane.add(get_lst_codigo_original(), null);
			jContentPane.add(get_lst_linea_reemplazo(), null);
			jContentPane.add(get_lst_codigo_reemplazo(), null);
			jContentPane.add(get_lst_odbc(), null);
			jContentPane.add(jLabel10, null);
			jContentPane.add(jLabel11, null);
			jContentPane.add(jLabel12, null);
			jContentPane.add(jLabel13, null);
			jContentPane.add(jLabel14, null);
			jContentPane.add(get_txt_query_equivalencia(), null);
			jContentPane.add(jLabel15, null);
			jContentPane.add(jLabel16, null);
			jContentPane.add(get_txt_odbc_imagen(), null);
			jContentPane.add(get_lst_imagen_codigo(), null);
			jContentPane.add(get_lst_imagen_linea(), null);
			jContentPane.add(get_lst_imagen(), null);
			jContentPane.add(get_txt_imagen_directorio(), null);
			jContentPane.add(jLabel17, null);
			jContentPane.add(jLabel18, null);
			jContentPane.add(jLabel19, null);
			jContentPane.add(jLabel20, null);
			jContentPane.add(get_chk_incorpora_articulos(), null);
			jContentPane.add(get_chk_equivalencias(), null);
			jContentPane.add(get_chk_incorpora_imagenes(), null);
			jContentPane.add(get_chk_incorporar_enlaces(), null);
			jContentPane.add(get_chk_limpia_linea(), null);
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
			jToolBar.setBounds(new Rectangle(0, 0, 1014, 24));
			jToolBar.setFloatable(false);
			jToolBar.add(get_btn_cancelar());
			jToolBar.add(get_btn_guardar());
			jToolBar.add(get_btn_aplicaciones());
			jToolBar.add(get_btn_importar());
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
			_btn_cargar.setBounds(new Rectangle(359, 59, 23, 18));
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
			_txt_idquery.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_idquery.setBounds(new Rectangle(164, 61, 160, 16));
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
			_btn_buscar_usuario.setBounds(new Rectangle(331, 59, 23, 18));
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
			jScrollPane.setBounds(new Rectangle(17, 536, 975, 185));
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
	
	public void setJTable1(JTable table){
		this.jTable1=table;
		this.jScrollPane1.setViewportView(jTable1);
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
	 * This method initializes _txt_linea	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_linea() {
		if (_txt_linea == null) {
			_txt_linea = new JTextField();
			_txt_linea.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_linea.setSize(new Dimension(220, 18));
			_txt_linea.setLocation(new Point(165, 296));
		}
		return _txt_linea;
	}

	/**
	 * This method initializes _txt_descripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_descripcion() {
		if (_txt_descripcion == null) {
			_txt_descripcion = new JTextField();
			_txt_descripcion.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_descripcion.setSize(new Dimension(220, 18));
			_txt_descripcion.setLocation(new Point(163, 131));
		}
		return _txt_descripcion;
	}

	/**
	 * This method initializes _lst_marca_vehiculo	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_marca_vehiculo() {
		if (_lst_marca_vehiculo == null) {
			_lst_marca_vehiculo = new JComboBox();
			_lst_marca_vehiculo.setFont(new Font("Dialog", Font.PLAIN, 10));
			_lst_marca_vehiculo.setSize(new Dimension(220, 18));
			_lst_marca_vehiculo.setLocation(new Point(164, 440));
		}
		return _lst_marca_vehiculo;
	}

	/**
	 * This method initializes _lst_vehiculo	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_vehiculo() {
		if (_lst_vehiculo == null) {
			_lst_vehiculo = new JComboBox();
			_lst_vehiculo.setFont(new Font("Dialog", Font.PLAIN, 10));
			_lst_vehiculo.setSize(new Dimension(220, 18));
			_lst_vehiculo.setLocation(new Point(164, 468));
		}
		return _lst_vehiculo;
	}

	/**
	 * This method initializes _lst_codigo	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_codigo() {
		if (_lst_codigo == null) {
			_lst_codigo = new JComboBox();
			_lst_codigo.setFont(new Font("Dialog", Font.PLAIN, 10));
			_lst_codigo.setSize(new Dimension(220, 18));
			_lst_codigo.setLocation(new Point(163, 107));
		}
		return _lst_codigo;
	}

	/**
	 * This method initializes _lst_desc_1	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_desc_1() {
		if (_lst_desc_1 == null) {
			_lst_desc_1 = new JComboBox();
			_lst_desc_1.setLocation(new Point(163, 153));
			_lst_desc_1.setSize(new Dimension(220, 18));
		}
		return _lst_desc_1;
	}

	/**
	 * This method initializes _lst_desc_2	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_desc_2() {
		if (_lst_desc_2 == null) {
			_lst_desc_2 = new JComboBox();
			_lst_desc_2.setLocation(new Point(163, 175));
			_lst_desc_2.setSize(new Dimension(220, 18));
		}
		return _lst_desc_2;
	}

	/**
	 * This method initializes _lst_desc_3	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_desc_3() {
		if (_lst_desc_3 == null) {
			_lst_desc_3 = new JComboBox();
			_lst_desc_3.setLocation(new Point(163, 197));
			_lst_desc_3.setSize(new Dimension(220, 18));
		}
		return _lst_desc_3;
	}

	/**
	 * This method initializes _lst_linea	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_linea() {
		if (_lst_linea == null) {
			_lst_linea = new JComboBox();
			_lst_linea.setLocation(new Point(165, 317));
			_lst_linea.setSize(new Dimension(220, 18));
		}
		return _lst_linea;
	}

	/**
	 * This method initializes _lst_desc_4	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_desc_4() {
		if (_lst_desc_4 == null) {
			_lst_desc_4 = new JComboBox();
			_lst_desc_4.setBounds(new Rectangle(163, 219, 220, 18));
		}
		return _lst_desc_4;
	}

	/**
	 * This method initializes _lst_categoria	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_categoria() {
		if (_lst_categoria == null) {
			_lst_categoria = new JComboBox();
			_lst_categoria.setSize(new Dimension(220, 18));
			_lst_categoria.setLocation(new Point(164, 368));
		}
		return _lst_categoria;
	}

	/**
	 * This method initializes _txt_categoria	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_categoria() {
		if (_txt_categoria == null) {
			_txt_categoria = new JTextField();
			_txt_categoria.setLocation(new Point(164, 344));
			_txt_categoria.setSize(new Dimension(220, 18));
		}
		return _txt_categoria;
	}

	/**
	 * This method initializes _lst_categoria2	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_categoria2() {
		if (_lst_categoria2 == null) {
			_lst_categoria2 = new JComboBox();
			_lst_categoria2.setBounds(new Rectangle(164, 415, 220, 18));
		}
		return _lst_categoria2;
	}

	/**
	 * This method initializes _btn_importar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_importar() {
		if (_btn_importar == null) {
			_btn_importar = new JButton();
			_btn_importar.setIcon(new ImageIcon(getClass().getResource("/icons/object-rotate-right.png")));
			_btn_importar.setToolTipText("Importar Catalogo a la Base de Datos Local");
		}
		return _btn_importar;
	}

	/**
	 * This method initializes jProgressBar	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	public JProgressBar getJProgressBar() {
		if (jProgressBar == null) {
			jProgressBar = new JProgressBar();
			jProgressBar.setBounds(new Rectangle(5, 29, 1005, 19));
		}
		return jProgressBar;
	}

	/**
	 * This method initializes _txt_categoria2	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_categoria2() {
		if (_txt_categoria2 == null) {
			_txt_categoria2 = new JTextField();
			_txt_categoria2.setBounds(new Rectangle(164, 391, 220, 18));
		}
		return _txt_categoria2;
	}

	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setBounds(new Rectangle(454, 374, 536, 138));
			jScrollPane1.setViewportView(getJTable1());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes jTable1	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getJTable1() {
		if (jTable1 == null) {
			jTable1 = new JTable();
		}
		return jTable1;
	}

	/**
	 * This method initializes _lst_desc_5	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_desc_5() {
		if (_lst_desc_5 == null) {
			_lst_desc_5 = new JComboBox();
			_lst_desc_5.setBounds(new Rectangle(163, 241, 220, 18));
			_lst_desc_5.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _lst_desc_5;
	}

	/**
	 * This method initializes _lst_desc_6	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_desc_6() {
		if (_lst_desc_6 == null) {
			_lst_desc_6 = new JComboBox();
			_lst_desc_6.setBounds(new Rectangle(163, 265, 220, 18));
			_lst_desc_6.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _lst_desc_6;
	}

	/**
	 * This method initializes _lst_precio	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_precio() {
		if (_lst_precio == null) {
			_lst_precio = new JComboBox();
			_lst_precio.setBounds(new Rectangle(165, 496, 218, 19));
		}
		return _lst_precio;
	}

	/**
	 * This method initializes _lst_equiv_1	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_equiv_1() {
		if (_lst_equiv_1 == null) {
			_lst_equiv_1 = new JComboBox();
			_lst_equiv_1.setBounds(new Rectangle(523, 82, 180, 18));
			_lst_equiv_1.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _lst_equiv_1;
	}

	/**
	 * This method initializes _lst_equiv_2	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_equiv_2() {
		if (_lst_equiv_2 == null) {
			_lst_equiv_2 = new JComboBox();
			_lst_equiv_2.setBounds(new Rectangle(523, 107, 180, 18));
		}
		return _lst_equiv_2;
	}

	/**
	 * This method initializes _lst_equiv_3	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_equiv_3() {
		if (_lst_equiv_3 == null) {
			_lst_equiv_3 = new JComboBox();
			_lst_equiv_3.setBounds(new Rectangle(523, 131,180, 18));
		}
		return _lst_equiv_3;
	}

	/**
	 * This method initializes _lst_linea_original	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_linea_original() {
		if (_lst_linea_original == null) {
			_lst_linea_original = new JComboBox();
			_lst_linea_original.setBounds(new Rectangle(523, 194, 180, 18));
		}
		return _lst_linea_original;
	}

	/**
	 * This method initializes _lst_codigo_original	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_codigo_original() {
		if (_lst_codigo_original == null) {
			_lst_codigo_original = new JComboBox();
			_lst_codigo_original.setBounds(new Rectangle(523, 216, 180, 18));
		}
		return _lst_codigo_original;
	}

	/**
	 * This method initializes _lst_linea_reemplazo	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_linea_reemplazo() {
		if (_lst_linea_reemplazo == null) {
			_lst_linea_reemplazo = new JComboBox();
			_lst_linea_reemplazo.setBounds(new Rectangle(523, 238, 180, 18));
		}
		return _lst_linea_reemplazo;
	}

	/**
	 * This method initializes _lst_codigo_reemplazo	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_codigo_reemplazo() {
		if (_lst_codigo_reemplazo == null) {
			_lst_codigo_reemplazo = new JComboBox();
			_lst_codigo_reemplazo.setBounds(new Rectangle(523, 260, 180, 18));
		}
		return _lst_codigo_reemplazo;
	}

	/**
	 * This method initializes _lst_odbc	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_odbc() {
		if (_lst_odbc == null) {
			_lst_odbc = new JComboBox();
			_lst_odbc.setBounds(new Rectangle(163, 82, 220, 18));
		}
		return _lst_odbc;
	}

	/**
	 * This method initializes _txt_query_equivalencia	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_query_equivalencia() {
		if (_txt_query_equivalencia == null) {
			_txt_query_equivalencia = new JTextField();
			_txt_query_equivalencia.setBounds(new Rectangle(523, 165, 180, 18));
		}
		return _txt_query_equivalencia;
	}

	/**
	 * This method initializes _txt_odbc_imagen	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_odbc_imagen() {
		if (_txt_odbc_imagen == null) {
			_txt_odbc_imagen = new JTextField();
			_txt_odbc_imagen.setBounds(new Rectangle(824, 166, 177, 18));
		}
		return _txt_odbc_imagen;
	}

	/**
	 * This method initializes _lst_imagen_codigo	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_imagen_codigo() {
		if (_lst_imagen_codigo == null) {
			_lst_imagen_codigo = new JComboBox();
			_lst_imagen_codigo.setBounds(new Rectangle(824, 193, 179, 16));
		}
		return _lst_imagen_codigo;
	}

	/**
	 * This method initializes _lst_imagen_linea	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_imagen_linea() {
		if (_lst_imagen_linea == null) {
			_lst_imagen_linea = new JComboBox();
			_lst_imagen_linea.setBounds(new Rectangle(824, 213, 179, 15));
		}
		return _lst_imagen_linea;
	}

	/**
	 * This method initializes _lst_imagen	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_imagen() {
		if (_lst_imagen == null) {
			_lst_imagen = new JComboBox();
			_lst_imagen.setBounds(new Rectangle(824, 232, 180, 16));
		}
		return _lst_imagen;
	}

	/**
	 * This method initializes _txt_imagen_directorio	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_imagen_directorio() {
		if (_txt_imagen_directorio == null) {
			_txt_imagen_directorio = new JTextField();
			_txt_imagen_directorio.setBounds(new Rectangle(826, 253, 177, 19));
		}
		return _txt_imagen_directorio;
	}

	/**
	 * This method initializes _chk_incorpora_articulos	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_incorpora_articulos() {
		if (_chk_incorpora_articulos == null) {
			_chk_incorpora_articulos = new JCheckBox();
			_chk_incorpora_articulos.setBounds(new Rectangle(750, 75, 256, 16));
			_chk_incorpora_articulos.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_incorpora_articulos.setText("Incorporar Articulos");
		}
		return _chk_incorpora_articulos;
	}

	/**
	 * This method initializes _chk_equivalencias	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_equivalencias() {
		if (_chk_equivalencias == null) {
			_chk_equivalencias = new JCheckBox();
			_chk_equivalencias.setBounds(new Rectangle(750, 95, 256, 17));
			_chk_equivalencias.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_equivalencias.setText("Incorporar Equivalencias");
		}
		return _chk_equivalencias;
	}

	/**
	 * This method initializes _chk_incorpora_imagenes	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_incorpora_imagenes() {
		if (_chk_incorpora_imagenes == null) {
			_chk_incorpora_imagenes = new JCheckBox();
			_chk_incorpora_imagenes.setBounds(new Rectangle(750, 113, 255, 19));
			_chk_incorpora_imagenes.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_incorpora_imagenes.setText("Incorporar Imagenes");
		}
		return _chk_incorpora_imagenes;
	}

	/**
	 * This method initializes _chk_incorporar_enlaces	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_incorporar_enlaces() {
		if (_chk_incorporar_enlaces == null) {
			_chk_incorporar_enlaces = new JCheckBox();
			_chk_incorporar_enlaces.setBounds(new Rectangle(750, 137, 252, 13));
			_chk_incorporar_enlaces.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_incorporar_enlaces.setText("Enlazar Equivalencias");
		}
		return _chk_incorporar_enlaces;
	}

	/**
	 * This method initializes _chk_limpia_linea	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_limpia_linea() {
		if (_chk_limpia_linea == null) {
			_chk_limpia_linea = new JCheckBox();
			_chk_limpia_linea.setBounds(new Rectangle(750, 55, 251, 16));
			_chk_limpia_linea.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_limpia_linea.setText("Limpia Linea");
		}
		return _chk_limpia_linea;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
