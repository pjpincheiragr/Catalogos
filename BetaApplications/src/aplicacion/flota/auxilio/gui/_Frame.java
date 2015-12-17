package aplicacion.flota.auxilio.gui;

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
import javax.swing.JList;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JToolBar jToolBar = null;
	private JButton _btn_salir = null;
	
	private JTextField _txt_idauxilio = null;
	private JLabel jLabel = null;
	private JButton _btn_cancelar = null;
	private JButton _btn_buscar = null;
	private JButton _btn_guardar = null;
	private JButton _btn_eliminar = null;
	private JTable jTable = null;
	private LockableUI lockableUI=null;
	private JLabel jLabel1 = null;
	private JTextField _txt_sucursal = null;
	private JLabel jLabel3 = null;
	private JTextField _txt_idunidad = null;
	private JLabel jLabel2 = null;
	private JTextField _txt_fecha = null;
	private JButton _btn_calendario = null;
	private JTextField _txt_numero = null;
	private JComboBox _lst_letra = null;
	private JButton _btn_buscarUnidad = null;
	private JTextField _txt_dominio = null;
	private JTextField _txt_detalle = null;
	private JLabel jLabel31 = null;
	private JComboBox _lst_provincia = null;
	private JLabel jLabel311 = null;
	private JComboBox _lst_localidad = null;
	private JLabel jLabel3111 = null;
	private JTextField _txt_ubicacion = null;
	private JLabel jLabel31111 = null;
	private JTextField _txt_idunidadAuxilio = null;
	private JTextField _txt_dominioAuxilio = null;
	private JButton _btn_buscarAuxilio = null;
	private JTextField _txt_detalleAuxilio = null;
	private JLabel jLabel311111 = null;
	private JTextField _txt_idunidadReemplazo = null;
	private JButton _btn_buscarReemplazo = null;
	private JTextField _txt_dominioReemplazo = null;
	private JTextField _txt_detalleReemplazo = null;
	private JLabel jLabel3111111 = null;
	private JTextField _txt_chofer = null;
	private JButton _btn_buscarChofer = null;
	private JTextField _txt_nombreChofer = null;
	private JLabel jLabel31111111 = null;
	private JTextField _txt_choferAuxilio = null;
	private JButton _btn_buscarChoferAuxilio = null;
	private JTextField _txt_nombreChoferAuxilio = null;
	private JTabbedPane jTabbedPane = null;
	private JScrollPane jScrollPane = null;
	private JTable _table_mecanico = null;
	private JTextArea _txt_falloAparente = null;
	private JTextArea _txt_falloReal = null;
	private JLabel jLabel311111111 = null;
	private JComboBox _lst_tipoFalla = null;
	private JButton _btn_editarUnidad = null;
	private JButton _btn_editarUnidad1 = null;
	private JButton _btn_editarUnidad2 = null;
	private JButton _btn_editarChofer = null;
	private JButton _btn_editarChofer2 = null;
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
		this.setSize(600, 600);
		this.setContentPane(getJContentPane());
		this.setTitle("Auxilio");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel311111111 = new JLabel();
			jLabel311111111.setBounds(new Rectangle(15, 345, 81, 14));
			jLabel311111111.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel311111111.setText("tipo de falla");
			jLabel311111111.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel31111111 = new JLabel();
			jLabel31111111.setBounds(new Rectangle(15, 315, 81, 14));
			jLabel31111111.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel31111111.setText("chofer auxilio");
			jLabel31111111.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel3111111 = new JLabel();
			jLabel3111111.setBounds(new Rectangle(15, 286, 81, 14));
			jLabel3111111.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3111111.setText("chofer");
			jLabel3111111.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel311111 = new JLabel();
			jLabel311111.setBounds(new Rectangle(15, 255, 90, 14));
			jLabel311111.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel311111.setText("unidad reemplazo");
			jLabel311111.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel31111 = new JLabel();
			jLabel31111.setBounds(new Rectangle(15, 225, 90, 14));
			jLabel31111.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel31111.setText("unidad auxilio");
			jLabel31111.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel3111 = new JLabel();
			jLabel3111.setBounds(new Rectangle(15, 195, 81, 14));
			jLabel3111.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3111.setText("ubicacion");
			jLabel3111.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel311 = new JLabel();
			jLabel311.setBounds(new Rectangle(15, 165, 81, 14));
			jLabel311.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel311.setText("localidad");
			jLabel311.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel31 = new JLabel();
			jLabel31.setBounds(new Rectangle(15, 135, 81, 13));
			jLabel31.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel31.setText("provincia");
			jLabel31.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(411, 45, 41, 14));
			jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2.setText("fecha");
			jLabel2.setFont(new Font("Dialog", Font.PLAIN, 10));
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
			jLabel.setText("idauxilio");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(get_txt_idauxilio(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(get_txt_sucursal(), null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(get_txt_idunidad(), null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(get_txt_fecha(), null);
			jContentPane.add(get_btn_calendario(), null);
			jContentPane.add(get_txt_numero(), null);
			jContentPane.add(get_lst_letra(), null);
			jContentPane.add(get_btn_buscarUnidad(), null);
			jContentPane.add(get_txt_dominio(), null);
			jContentPane.add(get_txt_detalle(), null);
			jContentPane.add(jLabel31, null);
			jContentPane.add(get_lst_provincia(), null);
			jContentPane.add(jLabel311, null);
			jContentPane.add(get_lst_localidad(), null);
			jContentPane.add(jLabel3111, null);
			jContentPane.add(get_txt_ubicacion(), null);
			jContentPane.add(jLabel31111, null);
			jContentPane.add(get_txt_idunidadAuxilio(), null);
			jContentPane.add(get_txt_dominioAuxilio(), null);
			jContentPane.add(get_btn_buscarAuxilio(), null);
			jContentPane.add(get_txt_detalleAuxilio(), null);
			jContentPane.add(jLabel311111, null);
			jContentPane.add(get_txt_idunidadReemplazo(), null);
			jContentPane.add(get_btn_buscarReemplazo(), null);
			jContentPane.add(get_txt_dominioReemplazo(), null);
			jContentPane.add(get_txt_detalleReemplazo(), null);
			jContentPane.add(jLabel3111111, null);
			jContentPane.add(get_txt_chofer(), null);
			jContentPane.add(get_btn_buscarChofer(), null);
			jContentPane.add(get_txt_nombreChofer(), null);
			jContentPane.add(jLabel31111111, null);
			jContentPane.add(get_txt_choferAuxilio(), null);
			jContentPane.add(get_btn_buscarChoferAuxilio(), null);
			jContentPane.add(get_txt_nombreChoferAuxilio(), null);
			jContentPane.add(getJTabbedPane(), null);
			jContentPane.add(jLabel311111111, null);
			jContentPane.add(get_lst_tipoFalla(), null);
			jContentPane.add(get_btn_editarUnidad(), null);
			jContentPane.add(get_btn_editarUnidad1(), null);
			jContentPane.add(get_btn_editarUnidad2(), null);
			jContentPane.add(get_btn_editarChofer(), null);
			jContentPane.add(get_btn_editarChofer2(), null);
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
			jToolBar.setBounds(new Rectangle(0, 0, 591, 24));
			jToolBar.setFloatable(false);
			jToolBar.add(get_btn_cancelar());
			jToolBar.add(get_btn_buscar());
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
	 * This method initializes _txt_idauxilio	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idauxilio() {
		if (_txt_idauxilio == null) {
			_txt_idauxilio = new JTextField();
			_txt_idauxilio.setToolTipText("Filtrar Parametros");
			_txt_idauxilio.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_idauxilio.setBounds(new Rectangle(104, 45, 160, 16));
		}
		return _txt_idauxilio;
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
	 * This method initializes _btn_buscar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar() {
		if (_btn_buscar == null) {
			_btn_buscar = new JButton();
			_btn_buscar.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-searchtool.png")));
			_btn_buscar.setToolTipText("Buscar auxilio");
		}
		return _btn_buscar;
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
			_btn_eliminar.setToolTipText("Eliminar auxilio");
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
			_txt_sucursal.setBounds(new Rectangle(104, 75, 80, 16));
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
	 * This method initializes _txt_fecha	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_fecha() {
		if (_txt_fecha == null) {
			_txt_fecha = new JTextField();
			_txt_fecha.setBounds(new Rectangle(462, 45, 70, 16));
			_txt_fecha.setToolTipText("Filtrar Parametros");
			_txt_fecha.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_fecha;
	}

	/**
	 * This method initializes _btn_calendario	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_calendario() {
		if (_btn_calendario == null) {
			_btn_calendario = new JButton();
			_btn_calendario.setBounds(new Rectangle(535, 42, 23, 23));
			_btn_calendario.setIcon(new ImageIcon(getClass().getResource("/icons/calendar-16.gif")));
			_btn_calendario.setToolTipText("buscar fecha");
		}
		return _btn_calendario;
	}

	/**
	 * This method initializes _txt_numero	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_numero() {
		if (_txt_numero == null) {
			_txt_numero = new JTextField();
			_txt_numero.setBounds(new Rectangle(190, 75, 90, 16));
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
			_lst_letra.setBounds(new Rectangle(287, 75, 50, 16));
			_lst_letra.setToolTipText("letra");
			
		}
		return _lst_letra;
	}

	/**
	 * This method initializes _btn_buscarUnidad	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscarUnidad() {
		if (_btn_buscarUnidad == null) {
			_btn_buscarUnidad = new JButton();
			_btn_buscarUnidad.setBounds(new Rectangle(198, 102, 23, 23));
			_btn_buscarUnidad.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-searchtool.png")));
			_btn_buscarUnidad.setToolTipText("buscar idunidad");
		}
		return _btn_buscarUnidad;
	}

	/**
	 * This method initializes _txt_dominio	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_dominio() {
		if (_txt_dominio == null) {
			_txt_dominio = new JTextField();
			_txt_dominio.setBounds(new Rectangle(260, 105, 100, 16));
			_txt_dominio.setToolTipText("dominio");
			_txt_dominio.setEditable(false);
			_txt_dominio.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_dominio;
	}

	/**
	 * This method initializes _txt_detalle	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_detalle() {
		if (_txt_detalle == null) {
			_txt_detalle = new JTextField();
			_txt_detalle.setBounds(new Rectangle(368, 105, 160, 16));
			_txt_detalle.setToolTipText("detalle de la unidad");
			_txt_detalle.setEditable(false);
			_txt_detalle.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_detalle;
	}

	/**
	 * This method initializes _lst_provincia	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_provincia() {
		if (_lst_provincia == null) {
			_lst_provincia = new JComboBox();
			_lst_provincia.setBounds(new Rectangle(104, 135, 100, 16));
			_lst_provincia.setEditable(false);
		}
		return _lst_provincia;
	}

	/**
	 * This method initializes _lst_localidad	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_localidad() {
		if (_lst_localidad == null) {
			_lst_localidad = new JComboBox();
			_lst_localidad.setBounds(new Rectangle(104, 165, 100, 16));
			_lst_localidad.setEditable(false);
		}
		return _lst_localidad;
	}

	/**
	 * This method initializes _txt_ubicacion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_ubicacion() {
		if (_txt_ubicacion == null) {
			_txt_ubicacion = new JTextField();
			_txt_ubicacion.setBounds(new Rectangle(104, 195, 100, 16));
			_txt_ubicacion.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_ubicacion;
	}

	/**
	 * This method initializes _txt_idunidadAuxilio	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idunidadAuxilio() {
		if (_txt_idunidadAuxilio == null) {
			_txt_idunidadAuxilio = new JTextField();
			_txt_idunidadAuxilio.setBounds(new Rectangle(113, 225, 90, 16));
			_txt_idunidadAuxilio.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_idunidadAuxilio;
	}

	/**
	 * This method initializes _txt_dominioAuxilio	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_dominioAuxilio() {
		if (_txt_dominioAuxilio == null) {
			_txt_dominioAuxilio = new JTextField();
			_txt_dominioAuxilio.setBounds(new Rectangle(267, 225, 100, 16));
			_txt_dominioAuxilio.setToolTipText("dominio");
			_txt_dominioAuxilio.setEditable(false);
			_txt_dominioAuxilio.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_dominioAuxilio;
	}

	/**
	 * This method initializes _btn_buscarAuxilio	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscarAuxilio() {
		if (_btn_buscarAuxilio == null) {
			_btn_buscarAuxilio = new JButton();
			_btn_buscarAuxilio.setBounds(new Rectangle(207, 222, 23, 23));
			_btn_buscarAuxilio.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-searchtool.png")));
			_btn_buscarAuxilio.setToolTipText("buscar idunidad");
		}
		return _btn_buscarAuxilio;
	}

	/**
	 * This method initializes _txt_detalleAuxilio	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_detalleAuxilio() {
		if (_txt_detalleAuxilio == null) {
			_txt_detalleAuxilio = new JTextField();
			_txt_detalleAuxilio.setBounds(new Rectangle(373, 225, 160, 16));
			_txt_detalleAuxilio.setToolTipText("detalle de la unidad");
			_txt_detalleAuxilio.setEditable(false);
			_txt_detalleAuxilio.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_detalleAuxilio;
	}

	/**
	 * This method initializes _txt_idunidadReemplazo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idunidadReemplazo() {
		if (_txt_idunidadReemplazo == null) {
			_txt_idunidadReemplazo = new JTextField();
			_txt_idunidadReemplazo.setBounds(new Rectangle(113, 255, 90, 16));
			_txt_idunidadReemplazo.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_idunidadReemplazo;
	}

	/**
	 * This method initializes _btn_buscarReemplazo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscarReemplazo() {
		if (_btn_buscarReemplazo == null) {
			_btn_buscarReemplazo = new JButton();
			_btn_buscarReemplazo.setBounds(new Rectangle(207, 252, 23, 23));
			_btn_buscarReemplazo.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-searchtool.png")));
			_btn_buscarReemplazo.setToolTipText("buscar idunidad");
		}
		return _btn_buscarReemplazo;
	}

	/**
	 * This method initializes _txt_dominioReemplazo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_dominioReemplazo() {
		if (_txt_dominioReemplazo == null) {
			_txt_dominioReemplazo = new JTextField();
			_txt_dominioReemplazo.setBounds(new Rectangle(267, 255, 100, 16));
			_txt_dominioReemplazo.setToolTipText("dominio");
			_txt_dominioReemplazo.setEditable(false);
			_txt_dominioReemplazo.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_dominioReemplazo;
	}

	/**
	 * This method initializes _txt_detalleReemplazo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_detalleReemplazo() {
		if (_txt_detalleReemplazo == null) {
			_txt_detalleReemplazo = new JTextField();
			_txt_detalleReemplazo.setBounds(new Rectangle(373, 255, 160, 16));
			_txt_detalleReemplazo.setToolTipText("detalle de la unidad");
			_txt_detalleReemplazo.setEditable(false);
			_txt_detalleReemplazo.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_detalleReemplazo;
	}

	/**
	 * This method initializes _txt_chofer	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_chofer() {
		if (_txt_chofer == null) {
			_txt_chofer = new JTextField();
			_txt_chofer.setBounds(new Rectangle(104, 285, 100, 16));
			_txt_chofer.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_chofer;
	}

	/**
	 * This method initializes _btn_buscarChofer	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscarChofer() {
		if (_btn_buscarChofer == null) {
			_btn_buscarChofer = new JButton();
			_btn_buscarChofer.setBounds(new Rectangle(207, 282, 23, 23));
			_btn_buscarChofer.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-searchtool.png")));
			_btn_buscarChofer.setToolTipText("buscar chofer");
		}
		return _btn_buscarChofer;
	}

	/**
	 * This method initializes _txt_nombreChofer	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_nombreChofer() {
		if (_txt_nombreChofer == null) {
			_txt_nombreChofer = new JTextField();
			_txt_nombreChofer.setBounds(new Rectangle(267, 285, 160, 16));
			_txt_nombreChofer.setToolTipText("detalle de la unidad");
			_txt_nombreChofer.setEditable(false);
			_txt_nombreChofer.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_nombreChofer;
	}

	/**
	 * This method initializes _txt_choferAuxilio	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_choferAuxilio() {
		if (_txt_choferAuxilio == null) {
			_txt_choferAuxilio = new JTextField();
			_txt_choferAuxilio.setBounds(new Rectangle(104, 315, 100, 16));
			_txt_choferAuxilio.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_choferAuxilio;
	}

	/**
	 * This method initializes _btn_buscarChoferAuxilio	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscarChoferAuxilio() {
		if (_btn_buscarChoferAuxilio == null) {
			_btn_buscarChoferAuxilio = new JButton();
			_btn_buscarChoferAuxilio.setBounds(new Rectangle(208, 312, 23, 23));
			_btn_buscarChoferAuxilio.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-searchtool.png")));
			_btn_buscarChoferAuxilio.setToolTipText("buscar chofer");
		}
		return _btn_buscarChoferAuxilio;
	}

	/**
	 * This method initializes _txt_nombreChoferAuxilio	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_nombreChoferAuxilio() {
		if (_txt_nombreChoferAuxilio == null) {
			_txt_nombreChoferAuxilio = new JTextField();
			_txt_nombreChoferAuxilio.setBounds(new Rectangle(267, 315, 160, 16));
			_txt_nombreChoferAuxilio.setToolTipText("detalle de la unidad");
			_txt_nombreChoferAuxilio.setEditable(false);
			_txt_nombreChoferAuxilio.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_nombreChoferAuxilio;
	}

	/**
	 * This method initializes jTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.setBounds(new Rectangle(14, 379, 558, 187));
			jTabbedPane.addTab("mecanico", null, getJScrollPane(), null);
			jTabbedPane.addTab("fallo aparente", null, get_txt_falloAparente(), null);
			jTabbedPane.addTab("fallo real", null, get_txt_falloReal(), null);
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
			jScrollPane.setViewportView(get_table_mecanico());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes _table_mecanico	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable get_table_mecanico() {
		if (_table_mecanico == null) {
			_table_mecanico = new JTable();
		}
		return _table_mecanico;
	}

	/**
	 * This method initializes _txt_falloAparente	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea get_txt_falloAparente() {
		if (_txt_falloAparente == null) {
			_txt_falloAparente = new JTextArea();
		}
		return _txt_falloAparente;
	}

	/**
	 * This method initializes _txt_falloReal	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea get_txt_falloReal() {
		if (_txt_falloReal == null) {
			_txt_falloReal = new JTextArea();
		}
		return _txt_falloReal;
	}

	/**
	 * This method initializes _lst_tipoFalla	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_tipoFalla() {
		if (_lst_tipoFalla == null) {
			_lst_tipoFalla = new JComboBox();
			_lst_tipoFalla.setBounds(new Rectangle(104, 345, 170, 16));
		}
		return _lst_tipoFalla;
	}

	/**
	 * This method initializes _btn_editarUnidad	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_editarUnidad() {
		if (_btn_editarUnidad == null) {
			_btn_editarUnidad = new JButton();
			_btn_editarUnidad.setBounds(new Rectangle(230, 100, 23, 23));
			_btn_editarUnidad.setIcon(new ImageIcon(getClass().getResource("/icons/edit-find-replace.png")));
			_btn_editarUnidad.setToolTipText("editar unidad");
		}
		return _btn_editarUnidad;
	}

	/**
	 * This method initializes _btn_editarUnidad1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_editarUnidad1() {
		if (_btn_editarUnidad1 == null) {
			_btn_editarUnidad1 = new JButton();
			_btn_editarUnidad1.setBounds(new Rectangle(237, 221, 23, 23));
			_btn_editarUnidad1.setIcon(new ImageIcon(getClass().getResource("/icons/edit-find-replace.png")));
			_btn_editarUnidad1.setToolTipText("editar unidad");
		}
		return _btn_editarUnidad1;
	}

	/**
	 * This method initializes _btn_editarUnidad2	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_editarUnidad2() {
		if (_btn_editarUnidad2 == null) {
			_btn_editarUnidad2 = new JButton();
			_btn_editarUnidad2.setBounds(new Rectangle(237, 250, 23, 23));
			_btn_editarUnidad2.setIcon(new ImageIcon(getClass().getResource("/icons/edit-find-replace.png")));
			_btn_editarUnidad2.setToolTipText("editar unidad");
		}
		return _btn_editarUnidad2;
	}

	/**
	 * This method initializes _btn_editarChofer	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_editarChofer() {
		if (_btn_editarChofer == null) {
			_btn_editarChofer = new JButton();
			_btn_editarChofer.setBounds(new Rectangle(237, 282, 23, 23));
			_btn_editarChofer.setIcon(new ImageIcon(getClass().getResource("/icons/edit-find-replace.png")));
			_btn_editarChofer.setToolTipText("editar unidad");
		}
		return _btn_editarChofer;
	}

	/**
	 * This method initializes _btn_editarChofer2	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_editarChofer2() {
		if (_btn_editarChofer2 == null) {
			_btn_editarChofer2 = new JButton();
			_btn_editarChofer2.setBounds(new Rectangle(237, 312, 23, 23));
			_btn_editarChofer2.setIcon(new ImageIcon(getClass().getResource("/icons/edit-find-replace.png")));
			_btn_editarChofer2.setToolTipText("editar unidad");
		}
		return _btn_editarChofer2;
	}




}  //  @jve:decl-index=0:visual-constraint="10,10"
