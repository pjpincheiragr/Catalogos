package aplicacion.flota.trabajo.gui;

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

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JToolBar jToolBar = null;
	private JButton _btn_salir = null;
	
	private JTextField _txt_idtrabajo = null;
	private JLabel jLabel = null;
	private JButton _btn_cancelar = null;
	private JButton _btn_buscar = null;
	private JButton _btn_guardar = null;
	private JButton _btn_eliminar = null;
	private JTable jTable = null;
	private LockableUI lockableUI=null;
	private JButton _btn_nueva = null;
	private JLabel jLabel1 = null;
	private JTextField _txt_parteSucursal = null;
	private JLabel jLabel2 = null;
	private JComboBox _lst_parteLetra = null;
	private JLabel jLabel3 = null;
	private JTextField _txt_lanzador = null;
	private JTextField _txt_parteNumero = null;
	private JTextField _txt_idunidad = null;
	private JButton _btn_buscar_unidad = null;
	private JTextField _txt_dominio = null;
	private JTextField _txt_detalleUnidad = null;
	private JLabel jLabel31 = null;
	private JTextField _txt_fechaInicio = null;
	private JButton _btn_calendario = null;
	private JLabel jLabel311 = null;
	private JTextField _txt_fechaFinal = null;
	private JButton _btn_calendario1 = null;
	private JTabbedPane jTabbedPane = null;
	private JScrollPane jScrollPane = null;
	private JTable _table_operaciones = null;
	private JScrollPane jScrollPane1 = null;
	private JTable _table_mecanicos = null;
	private JScrollPane jScrollPane2 = null;
	private JTable _table_servicio = null;
	private JScrollPane jScrollPane3 = null;
	private JTable _table_comprobantes = null;
	private JLabel jLabel4 = null;
	private JTextField _txt_fechaActual = null;
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
		this.setSize(545, 409);
		this.setContentPane(getJContentPane());
		this.setTitle("Trabajo");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(350, 45, 81, 13));
			jLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel4.setText("fecha" +
					"");
			jLabel4.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel311 = new JLabel();
			jLabel311.setBounds(new Rectangle(15, 195, 81, 13));
			jLabel311.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel311.setText("fecha inicio");
			jLabel311.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel31 = new JLabel();
			jLabel31.setBounds(new Rectangle(15, 165, 81, 13));
			jLabel31.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel31.setText("fecha inicio");
			jLabel31.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(15, 135, 81, 13));
			jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel3.setText("KM");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(15, 105, 81, 13));
			jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel2.setText("idunidad");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(14, 75, 81, 13));
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setText("parte");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(14, 45, 81, 13));
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setText("idtrabajo");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(get_txt_idtrabajo(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(get_btn_buscar(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(get_txt_parteSucursal(), null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(get_lst_parteLetra(), null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(get_txt_lanzador(), null);
			jContentPane.add(get_txt_parteNumero(), null);
			jContentPane.add(get_txt_idunidad(), null);
			jContentPane.add(get_btn_buscar_unidad(), null);
			jContentPane.add(get_txt_dominio(), null);
			jContentPane.add(get_txt_detalleUnidad(), null);
			jContentPane.add(jLabel31, null);
			jContentPane.add(get_txt_fechaInicio(), null);
			jContentPane.add(get_btn_calendario(), null);
			jContentPane.add(jLabel311, null);
			jContentPane.add(get_txt_fechaFinal(), null);
			jContentPane.add(get_btn_calendario1(), null);
			jContentPane.add(getJTabbedPane(), null);
			jContentPane.add(jLabel4, null);
			jContentPane.add(get_txt_fechaActual(), null);
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
			jToolBar.add(get_btn_cancelar());
			jToolBar.add(get_btn_guardar());
			jToolBar.add(get_btn_nueva());
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
	 * This method initializes _txt_idtrabajo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idtrabajo() {
		if (_txt_idtrabajo == null) {
			_txt_idtrabajo = new JTextField();
			_txt_idtrabajo.setToolTipText("Filtrar Parametros");
			_txt_idtrabajo.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_idtrabajo.setBounds(new Rectangle(104, 44, 160, 16));
		}
		return _txt_idtrabajo;
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
			_btn_buscar.setBounds(new Rectangle(267, 42, 23, 23));
			_btn_buscar.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-searchtool.png")));
			_btn_buscar.setToolTipText("Buscar Usuario");
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
	 * This method initializes _btn_nueva	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_nueva() {
		if (_btn_nueva == null) {
			_btn_nueva = new JButton();
			_btn_nueva.setIcon(new ImageIcon(getClass().getResource("/icons/stock_new-text.png")));
			_btn_nueva.setToolTipText("nueva aplicacion");
		}
		return _btn_nueva;
	}

	/**
	 * This method initializes _txt_parteSucursal	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_parteSucursal() {
		if (_txt_parteSucursal == null) {
			_txt_parteSucursal = new JTextField();
			_txt_parteSucursal.setBounds(new Rectangle(104, 75, 70, 16));
			_txt_parteSucursal.setToolTipText("sucursal");
			_txt_parteSucursal.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_parteSucursal;
	}

	/**
	 * This method initializes _lst_parteLetra	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_parteLetra() {
		if (_lst_parteLetra == null) {
			_lst_parteLetra = new JComboBox();
			_lst_parteLetra.setBounds(new Rectangle(285, 75, 50, 16));
			_lst_parteLetra.setToolTipText("letra");
			_lst_parteLetra.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _lst_parteLetra;
	}

	/**
	 * This method initializes _txt_lanzador	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_lanzador() {
		if (_txt_lanzador == null) {
			_txt_lanzador = new JTextField();
			_txt_lanzador.setBounds(new Rectangle(104, 135, 90, 16));
			_txt_lanzador.setToolTipText("kilometraje de la unidad");
			_txt_lanzador.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_lanzador;
	}

	/**
	 * This method initializes _txt_parteNumero	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_parteNumero() {
		if (_txt_parteNumero == null) {
			_txt_parteNumero = new JTextField();
			_txt_parteNumero.setBounds(new Rectangle(180, 75, 100, 16));
			_txt_parteNumero.setToolTipText("numero");
			_txt_parteNumero.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_parteNumero;
	}

	/**
	 * This method initializes _txt_idunidad	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idunidad() {
		if (_txt_idunidad == null) {
			_txt_idunidad = new JTextField();
			_txt_idunidad.setBounds(new Rectangle(104, 105, 80, 18));
			_txt_idunidad.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_idunidad;
	}

	/**
	 * This method initializes _btn_buscar_unidad	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_unidad() {
		if (_btn_buscar_unidad == null) {
			_btn_buscar_unidad = new JButton();
			_btn_buscar_unidad.setBounds(new Rectangle(189, 102, 23, 23));
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
			_txt_dominio.setBounds(new Rectangle(220, 105, 90, 16));
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
			_txt_detalleUnidad.setBounds(new Rectangle(316, 105, 190, 16));
			_txt_detalleUnidad.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_detalleUnidad;
	}

	/**
	 * This method initializes _txt_fechaInicio	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_fechaInicio() {
		if (_txt_fechaInicio == null) {
			_txt_fechaInicio = new JTextField();
			_txt_fechaInicio.setBounds(new Rectangle(104, 165, 80, 16));
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
			_btn_calendario.setBounds(new Rectangle(190, 162, 23, 23));
			_btn_calendario.setIcon(new ImageIcon(getClass().getResource("/icons/calendar-16.gif")));
			_btn_calendario.setToolTipText("Buscar Usuario");
		}
		return _btn_calendario;
	}

	/**
	 * This method initializes _txt_fechaFinal	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_fechaFinal() {
		if (_txt_fechaFinal == null) {
			_txt_fechaFinal = new JTextField();
			_txt_fechaFinal.setBounds(new Rectangle(104, 195, 80, 16));
			_txt_fechaFinal.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_fechaFinal;
	}

	/**
	 * This method initializes _btn_calendario1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_calendario1() {
		if (_btn_calendario1 == null) {
			_btn_calendario1 = new JButton();
			_btn_calendario1.setBounds(new Rectangle(191, 192, 23, 23));
			_btn_calendario1.setIcon(new ImageIcon(getClass().getResource("/icons/calendar-16.gif")));
			_btn_calendario1.setToolTipText("Buscar Usuario");
		}
		return _btn_calendario1;
	}

	/**
	 * This method initializes jTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.setBounds(new Rectangle(15, 227, 495, 146));
			jTabbedPane.addTab("operaciones", null, getJScrollPane(), null);
			jTabbedPane.addTab("mecanicos", null, getJScrollPane1(), null);
			jTabbedPane.addTab("servicio", null, getJScrollPane2(), null);
			jTabbedPane.addTab("comprobantes", null, getJScrollPane3(), null);
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
			jScrollPane.setViewportView(get_table_operaciones());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes _table_operaciones	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable get_table_operaciones() {
		if (_table_operaciones == null) {
			_table_operaciones = new JTable();
		}
		return _table_operaciones;
	}

	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(get_table_mecanicos());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes _table_mecanicos	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable get_table_mecanicos() {
		if (_table_mecanicos == null) {
			_table_mecanicos = new JTable();
		}
		return _table_mecanicos;
	}

	/**
	 * This method initializes jScrollPane2	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane2() {
		if (jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setViewportView(get_table_servicio());
		}
		return jScrollPane2;
	}

	/**
	 * This method initializes _table_servicio	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable get_table_servicio() {
		if (_table_servicio == null) {
			_table_servicio = new JTable();
		}
		return _table_servicio;
	}

	/**
	 * This method initializes jScrollPane3	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane3() {
		if (jScrollPane3 == null) {
			jScrollPane3 = new JScrollPane();
			jScrollPane3.setViewportView(get_table_comprobantes());
		}
		return jScrollPane3;
	}

	/**
	 * This method initializes _table_comprobantes	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable get_table_comprobantes() {
		if (_table_comprobantes == null) {
			_table_comprobantes = new JTable();
		}
		return _table_comprobantes;
	}

	/**
	 * This method initializes _txt_fechaActual	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_fechaActual() {
		if (_txt_fechaActual == null) {
			_txt_fechaActual = new JTextField();
			_txt_fechaActual.setBounds(new Rectangle(437, 45, 80, 16));
			_txt_fechaActual.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_fechaActual;
	}




}  //  @jve:decl-index=0:visual-constraint="10,10"
