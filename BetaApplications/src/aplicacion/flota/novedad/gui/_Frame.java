package aplicacion.flota.novedad.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import java.awt.Rectangle;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JTabbedPane;
import java.awt.GridBagLayout;
import java.awt.Point;
import javax.swing.JRadioButton;

import org.jdesktop.jxlayer.JXLayer;
import org.jdesktop.jxlayer.plaf.effect.BufferedImageOpEffect;
import org.jdesktop.jxlayer.plaf.ext.LockableUI;

import com.jhlabs.image.DiffuseFilter;
import com.jhlabs.image.BoxBlurFilter;
import aplicacion.compras.carga.items.logic.DisplayCanvas;
import javax.swing.JTextArea;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JToolBar jToolBar = null;
	private JButton _btn_salir = null;
	
	private JTextField _txt_idnovedad = null;
	private JLabel jLabel = null;
	private JButton _btn_cancelar = null;
	private JButton _btn_buscar_aplicacion = null;
	private JButton _btn_guardar = null;
	private JButton _btn_eliminar = null;
	private JTable jTable = null;
	private LockableUI lockableUI=null;
	private JLabel jLabel1 = null;
	private JTextField _txt_sucursal = null;
	private JLabel jLabel3 = null;
	private JTextField _txt_idunidad = null;
	private JButton _btn_nueva = null;
	private JTextField _txt_numero = null;
	private JComboBox _lst_letra = null;
	private JButton _btn_buscar_unidad = null;
	private JTextField _txt_dominio = null;
	private JTextField _txt_detalleUnidad = null;
	private JLabel jLabel31 = null;
	private JTextField _txt_chofer = null;
	private JButton _btn_buscar_chofer = null;
	private JTextField _txt_nombreChofer = null;
	private JLabel jLabel311 = null;
	private JTextField _txt_fechaInicio = null;
	private JButton _btn_calendario = null;
	private JLabel jLabel3111 = null;
	private JTextField _txt_fechaFinalizacion = null;
	private JButton _btn_calendario1 = null;
	private JLabel jLabel31111 = null;
	private JComboBox _lst_combustibleGarage = null;
	private JLabel jLabel311111 = null;
	private JTextField _txt_combustibleEstacion = null;
	private JComboBox _lst_combustibleEstacion = null;
	private JLabel jLabel3111111 = null;
	private JComboBox _lst_tarjeta = null;
	private JTextField _txt_numeroTarjeta = null;
	private JTabbedPane jTabbedPane = null;
	private JScrollPane jScrollPane = null;
	private JTable _table_equipamiento = null;
	private JScrollPane jScrollPane1 = null;
	private JTable _table_prechequeo = null;
	private JScrollPane jScrollPane2 = null;
	private JTextArea _txt_observasiones = null;
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
		this.setSize(545, 537);
		this.setContentPane(getJContentPane());
		this.setTitle("Novedad ");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel3111111 = new JLabel();
			jLabel3111111.setBounds(new Rectangle(16, 285, 81, 13));
			jLabel3111111.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3111111.setText("tarjeta");
			jLabel3111111.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel311111 = new JLabel();
			jLabel311111.setBounds(new Rectangle(16, 255, 105, 13));
			jLabel311111.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel311111.setText("combustible estacion");
			jLabel311111.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel31111 = new JLabel();
			jLabel31111.setBounds(new Rectangle(16, 225, 105, 13));
			jLabel31111.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel31111.setText("combustible garage");
			jLabel31111.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel3111 = new JLabel();
			jLabel3111.setBounds(new Rectangle(15, 195, 81, 13));
			jLabel3111.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3111.setText("fecha inicio");
			jLabel3111.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel311 = new JLabel();
			jLabel311.setBounds(new Rectangle(14, 165, 81, 13));
			jLabel311.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel311.setText("fecha inicio");
			jLabel311.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel31 = new JLabel();
			jLabel31.setBounds(new Rectangle(14, 135, 81, 13));
			jLabel31.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel31.setText("chofer");
			jLabel31.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(15, 105, 81, 13));
			jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel3.setText("idunidad");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(14, 75, 81, 13));
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setText("parte");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(14, 45, 81, 13));
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setText("idNovedad");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(get_txt_idnovedad(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(get_txt_sucursal(), null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(get_txt_idunidad(), null);
			jContentPane.add(get_btn_buscar_aplicacion(), null);
			jContentPane.add(get_txt_numero(), null);
			jContentPane.add(get_lst_letra(), null);
			jContentPane.add(get_btn_buscar_unidad(), null);
			jContentPane.add(get_txt_dominio(), null);
			jContentPane.add(get_txt_detalleUnidad(), null);
			jContentPane.add(jLabel31, null);
			jContentPane.add(get_txt_chofer(), null);
			jContentPane.add(get_btn_buscar_chofer(), null);
			jContentPane.add(get_txt_nombreChofer(), null);
			jContentPane.add(jLabel311, null);
			jContentPane.add(get_txt_fechaInicio(), null);
			jContentPane.add(get_btn_calendario(), null);
			jContentPane.add(jLabel3111, null);
			jContentPane.add(get_txt_fechaFinalizacion(), null);
			jContentPane.add(get_btn_calendario1(), null);
			jContentPane.add(jLabel31111, null);
			jContentPane.add(get_lst_combustibleGarage(), null);
			jContentPane.add(jLabel311111, null);
			jContentPane.add(get_txt_combustibleEstacion(), null);
			jContentPane.add(get_lst_combustibleEstacion(), null);
			jContentPane.add(jLabel3111111, null);
			jContentPane.add(get_lst_tarjeta(), null);
			jContentPane.add(get_txt_numeroTarjeta(), null);
			jContentPane.add(getJTabbedPane(), null);
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
			jToolBar.setBounds(new Rectangle(0, 0, 535, 24));
			jToolBar.setFloatable(false);
			jToolBar.add(get_btn_nueva());
			jToolBar.add(get_btn_cancelar());
			jToolBar.add(get_btn_guardar());
			jToolBar.add(get_btn_eliminar());
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
			_btn_salir.setIcon(new ImageIcon(getClass().getResource("/icons/exit.png")));
			_btn_salir.setToolTipText("Salir");
		}
		return _btn_salir;
	}


	/**
	 * This method initializes _txt_idnovedad	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idnovedad() {
		if (_txt_idnovedad == null) {
			_txt_idnovedad = new JTextField();
			_txt_idnovedad.setToolTipText("Filtrar Parametros");
			_txt_idnovedad.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_idnovedad.setBounds(new Rectangle(104, 44, 160, 16));
		}
		return _txt_idnovedad;
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
	 * This method initializes _btn_buscar_aplicacion	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_aplicacion() {
		if (_btn_buscar_aplicacion == null) {
			_btn_buscar_aplicacion = new JButton();
			_btn_buscar_aplicacion.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-searchtool.png")));
			_btn_buscar_aplicacion.setBounds(new Rectangle(267, 41, 24, 24));
			_btn_buscar_aplicacion.setToolTipText("Buscar Usuario");
		}
		return _btn_buscar_aplicacion;
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
			_btn_eliminar.setToolTipText("Eliminar Aplicacion");
		}
		return _btn_eliminar;
	}


	public LockableUI getLockableUI(){
		if (lockableUI==null){
			lockableUI= new LockableUI();        
			//BoxBlurFilter blur=new BoxBlurFilter(1,1,3);
			DiffuseFilter blur=new DiffuseFilter();
			blur.setInterpolation(1);
			BufferedImageOpEffect effect = new BufferedImageOpEffect(blur);
			lockableUI.setLockedEffects(effect);
			
			
			lockableUI.setLocked(true);
		}
		return lockableUI; 

	}

	/**
	 * This method initializes _txt_sucursal	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_sucursal() {
		if (_txt_sucursal == null) {
			_txt_sucursal = new JTextField();
			_txt_sucursal.setBounds(new Rectangle(104, 75, 70, 16));
			_txt_sucursal.setToolTipText("sucursal");
			_txt_sucursal.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_sucursal;
	}

	/**
	 * This method initializes _txt_idunidad	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idunidad() {
		if (_txt_idunidad == null) {
			_txt_idunidad = new JTextField();
			_txt_idunidad.setBounds(new Rectangle(104, 105,90, 16));
			_txt_idunidad.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_idunidad;
	}

	/**
	 * This method initializes _btn_nueva	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_nueva() {
		if (_btn_nueva == null) {
			_btn_nueva = new JButton();
			_btn_nueva.setIcon(new ImageIcon(getClass().getResource("/icons/document-new.png")));
		}
		return _btn_nueva;
	}

	/**
	 * This method initializes _txt_numero	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_numero() {
		if (_txt_numero == null) {
			_txt_numero = new JTextField();
			_txt_numero.setBounds(new Rectangle(180, 75, 100, 16));
			_txt_numero.setToolTipText("numero");
			_txt_numero.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_numero;
	}

	/**
	 * This method initializes _lst_letra	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_letra() {
		if (_lst_letra == null) {
			_lst_letra = new JComboBox();
			_lst_letra.setBounds(new Rectangle(287, 75, 50, 15));
			_lst_letra.setToolTipText("letra");
		}
		return _lst_letra;
	}

	/**
	 * This method initializes _btn_buscar_unidad	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_unidad() {
		if (_btn_buscar_unidad == null) {
			_btn_buscar_unidad = new JButton();
			_btn_buscar_unidad.setBounds(new Rectangle(198, 101, 24, 24));
			_btn_buscar_unidad.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-searchtool.png")));
			_btn_buscar_unidad.setToolTipText("Buscar Usuario");
		}
		return _btn_buscar_unidad;
	}

	/**
	 * This method initializes _txt_dominio	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_dominio() {
		if (_txt_dominio == null) {
			_txt_dominio = new JTextField();
			_txt_dominio.setBounds(new Rectangle(229, 105, 90, 16));
			_txt_dominio.setToolTipText("dominio");
			_txt_dominio.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_dominio;
	}

	/**
	 * This method initializes _txt_detalleUnidad	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_detalleUnidad() {
		if (_txt_detalleUnidad == null) {
			_txt_detalleUnidad = new JTextField();
			_txt_detalleUnidad.setBounds(new Rectangle(325, 105, 160, 16));
			_txt_detalleUnidad.setToolTipText("detalle unidad");
			_txt_detalleUnidad.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_detalleUnidad;
	}

	/**
	 * This method initializes _txt_chofer	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_chofer() {
		if (_txt_chofer == null) {
			_txt_chofer = new JTextField();
			_txt_chofer.setBounds(new Rectangle(105, 135, 70, 16));
			_txt_chofer.setToolTipText("sucursal");
			_txt_chofer.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_chofer;
	}

	/**
	 * This method initializes _btn_buscar_chofer	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_chofer() {
		if (_btn_buscar_chofer == null) {
			_btn_buscar_chofer = new JButton();
			_btn_buscar_chofer.setBounds(new Rectangle(178, 131, 24, 24));
			_btn_buscar_chofer.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-searchtool.png")));
			_btn_buscar_chofer.setToolTipText("Buscar Usuario");
		}
		return _btn_buscar_chofer;
	}

	/**
	 * This method initializes _txt_nombreChofer	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_nombreChofer() {
		if (_txt_nombreChofer == null) {
			_txt_nombreChofer = new JTextField();
			_txt_nombreChofer.setBounds(new Rectangle(211, 135, 160, 16));
			_txt_nombreChofer.setToolTipText("nombre y apellido del chofer");
			_txt_nombreChofer.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_nombreChofer;
	}

	/**
	 * This method initializes _txt_fechaInicio	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_fechaInicio() {
		if (_txt_fechaInicio == null) {
			_txt_fechaInicio = new JTextField();
			_txt_fechaInicio.setBounds(new Rectangle(105, 165, 90, 16));
			_txt_fechaInicio.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_fechaInicio;
	}

	/**
	 * This method initializes _btn_calendario	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_calendario() {
		if (_btn_calendario == null) {
			_btn_calendario = new JButton();
			_btn_calendario.setBounds(new Rectangle(200, 160, 24, 24));
			_btn_calendario.setIcon(new ImageIcon(getClass().getResource("/icons/calendar-16.gif")));
			_btn_calendario.setToolTipText("Buscar Usuario");
		}
		return _btn_calendario;
	}

	/**
	 * This method initializes _txt_fechaFinalizacion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_fechaFinalizacion() {
		if (_txt_fechaFinalizacion == null) {
			_txt_fechaFinalizacion = new JTextField();
			_txt_fechaFinalizacion.setBounds(new Rectangle(105, 195, 90, 16));
			_txt_fechaFinalizacion.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_fechaFinalizacion;
	}

	/**
	 * This method initializes _btn_calendario1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_calendario1() {
		if (_btn_calendario1 == null) {
			_btn_calendario1 = new JButton();
			_btn_calendario1.setBounds(new Rectangle(202, 192, 24, 24));
			_btn_calendario1.setIcon(new ImageIcon(getClass().getResource("/icons/calendar-16.gif")));
			_btn_calendario1.setToolTipText("Buscar Usuario");
		}
		return _btn_calendario1;
	}

	/**
	 * This method initializes _lst_combustibleGarage	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_combustibleGarage() {
		if (_lst_combustibleGarage == null) {
			_lst_combustibleGarage = new JComboBox();
			_lst_combustibleGarage.setBounds(new Rectangle(130, 225, 90, 16));
		}
		return _lst_combustibleGarage;
	}

	/**
	 * This method initializes _txt_combustibleEstacion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_combustibleEstacion() {
		if (_txt_combustibleEstacion == null) {
			_txt_combustibleEstacion = new JTextField();
			_txt_combustibleEstacion.setBounds(new Rectangle(130, 255, 90, 16));
			_txt_combustibleEstacion.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_combustibleEstacion;
	}

	/**
	 * This method initializes _lst_combustibleEstacion	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_combustibleEstacion() {
		if (_lst_combustibleEstacion == null) {
			_lst_combustibleEstacion = new JComboBox();
			_lst_combustibleEstacion.setBounds(new Rectangle(227, 255, 90, 16));
		}
		return _lst_combustibleEstacion;
	}

	/**
	 * This method initializes _lst_tarjeta	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_tarjeta() {
		if (_lst_tarjeta == null) {
			_lst_tarjeta = new JComboBox();
			_lst_tarjeta.setBounds(new Rectangle(107, 285, 90, 16));
			_lst_tarjeta.setToolTipText("tipo de tarjeta");
		}
		return _lst_tarjeta;
	}

	/**
	 * This method initializes _txt_numeroTarjeta	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_numeroTarjeta() {
		if (_txt_numeroTarjeta == null) {
			_txt_numeroTarjeta = new JTextField();
			_txt_numeroTarjeta.setBounds(new Rectangle(208, 285, 100, 16));
			_txt_numeroTarjeta.setToolTipText("numero tarjeta");
			_txt_numeroTarjeta.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_numeroTarjeta;
	}

	/**
	 * This method initializes jTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.setBounds(new Rectangle(16, 315, 496, 181));
			jTabbedPane.addTab("equipamiento", null, getJScrollPane(), null);
			jTabbedPane.addTab("prechequeo", null, getJScrollPane1(), null);
			jTabbedPane.addTab("observaciones", null, getJScrollPane2(), null);
		}
		return jTabbedPane;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(get_table_equipamiento());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes _table_equipamiento	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable get_table_equipamiento() {
		if (_table_equipamiento == null) {
			_table_equipamiento = new JTable();
		}
		return _table_equipamiento;
	}

	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(get_table_prechequeo());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes _table_prechequeo	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable get_table_prechequeo() {
		if (_table_prechequeo == null) {
			_table_prechequeo = new JTable();
		}
		return _table_prechequeo;
	}

	/**
	 * This method initializes jScrollPane2	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane2() {
		if (jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setViewportView(get_txt_observasiones());
		}
		return jScrollPane2;
	}

	/**
	 * This method initializes _txt_observasiones	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea get_txt_observasiones() {
		if (_txt_observasiones == null) {
			_txt_observasiones = new JTextArea();
		}
		return _txt_observasiones;
	}




}  //  @jve:decl-index=0:visual-constraint="10,10"
