package aplicacion.flota.vehiculo.gui;

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
import java.awt.GridBagConstraints;
import javax.swing.JTextArea;

public class _Frame extends JFrame {

	//ñ
	
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JToolBar jToolBar = null;
	private JButton _btn_salir = null;
	
	private JTextField _txt_idaplicacion = null;
	private JLabel jLabel = null;
	private JButton _btn_cancelar = null;
	private JButton _btn_buscar = null;
	private JButton _btn_guardar = null;
	private JButton _btn_eliminar = null;
	private JTable jTable = null;
	private LockableUI lockableUI=null;
	private JLabel jLabel1 = null;
	private JTextField _txt_dominio = null;
	private JLabel jLabel2 = null;
	private JComboBox _lst_marca = null;
	private JLabel jLabel3 = null;
	private JTextField _txt_cuenta = null;
	private JLabel jLabel21 = null;
	private JComboBox _lst_modelo = null;
	private JLabel jLabel211 = null;
	private JTextField _txt_anio = null;
	private JLabel jLabel212 = null;
	private JComboBox _lst_version = null;
	private JLabel jLabel2121 = null;
	private JTextField _txt_numeroMotor = null;
	private JLabel jLabel21211 = null;
	private JTextField _txt_numeroChasis = null;
	private JLabel jLabel212111 = null;
	private JLabel jLabel2121111 = null;
	private JTextField _txt_vencimiento = null;
	private JLabel jLabel2121112 = null;
	private JTextField _txt_KMinicial = null;
	private JLabel jLabel21211121 = null;
	private JTextField _txt_fecha = null;
	private JLabel jLabel212111211 = null;
	private JTextField _txt_KMactual = null;
	private JTabbedPane jTabbedPane = null;
	private JPanel jPanel = null;
	private JPanel jPanel1 = null;
	private JPanel jPanel2 = null;
	private JPanel jPanel3 = null;
	private JPanel jPanel4 = null;
	private JPanel jPanel5 = null;
	private JToolBar jJToolBarBar11 = null;
	private JButton _btn_nuevaTarjeta = null;
	private JButton _btn_eliminarTarjeta = null;
	private JTable _table_tarjetas = null;
	private JToolBar jJToolBarBar111 = null;
	private JButton _btn_nuevoEquipamiento = null;
	private JButton _btn_eliminarEquipamiento = null;
	private JTable _table_equipamiento = null;
	private JToolBar jJToolBarBar1111 = null;
	private JButton _btn_nuevaPlanificacion = null;
	private JButton _btn_eliminarPlanificacion = null;
	private JTable _table_planificacion = null;
	private JToolBar jJToolBarBar11111 = null;
	private JButton _btn_nuevaNovedad = null;
	private JButton _btn_eliminarNovedad = null;
	private JTable _table_novedad = null;
	private JToolBar jJToolBarBar111111 = null;
	private JButton _btn_nuevoTrabajo = null;
	private JButton _btn_eliminarTrabajo = null;
	private JTable _table_trabajo = null;
	private JToolBar jJToolBarBar1111111 = null;
	private JButton _btn_nuevoAuxilio = null;
	private JButton _btn_eliminarAuxilio = null;
	private JTable _table_auxilio = null;
	private JButton _btn_calendario = null;
	private JTextArea jTextArea = null;
	private JScrollPane jScrollPane = null;
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
		this.setSize(600, 622);
		this.setContentPane(getJContentPane());
		this.setTitle("Vehiculo");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel212111211 = new JLabel();
			jLabel212111211.setBounds(new Rectangle(395, 385, 69, 13));
			jLabel212111211.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel212111211.setText("KM actual");
			jLabel212111211.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel21211121 = new JLabel();
			jLabel21211121.setBounds(new Rectangle(200, 385, 67, 13));
			jLabel21211121.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel21211121.setText("Fecha");
			jLabel21211121.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel2121112 = new JLabel();
			jLabel2121112.setBounds(new Rectangle(15, 385, 81, 13));
			jLabel2121112.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2121112.setText("KM inicial");
			jLabel2121112.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel2121111 = new JLabel();
			jLabel2121111.setBounds(new Rectangle(280, 315, 69, 14));
			jLabel2121111.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2121111.setText("Vencimiento");
			jLabel2121111.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel212111 = new JLabel();
			jLabel212111.setBounds(new Rectangle(15, 315, 81, 13));
			jLabel212111.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel212111.setText("Seguro");
			jLabel212111.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel21211 = new JLabel();
			jLabel21211.setBounds(new Rectangle(15, 285, 81, 13));
			jLabel21211.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel21211.setText("Numero chasis");
			jLabel21211.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel2121 = new JLabel();
			jLabel2121.setBounds(new Rectangle(15, 255, 81, 13));
			jLabel2121.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2121.setText("Numero motor");
			jLabel2121.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel212 = new JLabel();
			jLabel212.setBounds(new Rectangle(15, 225, 81, 13));
			jLabel212.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel212.setText("Version");
			jLabel212.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel211 = new JLabel();
			jLabel211.setBounds(new Rectangle(15, 135, 81, 13));
			jLabel211.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel211.setText("Año");
			jLabel211.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel21 = new JLabel();
			jLabel21.setBounds(new Rectangle(15, 195, 81, 13));
			jLabel21.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel21.setText("Modelo");
			jLabel21.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(17, 105, 81, 13));
			jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel3.setText("Cuenta");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(15, 165, 81, 13));
			jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel2.setText("Marca");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(14, 75, 81, 13));
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setText("dominio");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(14, 45, 81, 13));
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setText("Unidad");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(get_txt_idaplicacion(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(get_txt_dominio(), null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(get_lst_marca(), null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(get_txt_cuenta(), null);
			jContentPane.add(jLabel21, null);
			jContentPane.add(get_lst_modelo(), null);
			jContentPane.add(jLabel211, null);
			jContentPane.add(get_txt_anio(), null);
			jContentPane.add(jLabel212, null);
			jContentPane.add(get_lst_version(), null);
			jContentPane.add(jLabel2121, null);
			jContentPane.add(get_txt_numeroMotor(), null);
			jContentPane.add(jLabel21211, null);
			jContentPane.add(get_txt_numeroChasis(), null);
			jContentPane.add(jLabel212111, null);
			jContentPane.add(jLabel2121111, null);
			jContentPane.add(get_txt_vencimiento(), null);
			jContentPane.add(jLabel2121112, null);
			jContentPane.add(get_txt_KMinicial(), null);
			jContentPane.add(jLabel21211121, null);
			jContentPane.add(get_txt_fecha(), null);
			jContentPane.add(jLabel212111211, null);
			jContentPane.add(get_txt_KMactual(), null);
			jContentPane.add(getJTabbedPane(), null);
			jContentPane.add(get_btn_calendario(), null);
			jContentPane.add(getJScrollPane(), null);
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
			jToolBar.add(get_btn_eliminar());
			jToolBar.add(get_btn_salir());
			jToolBar.add(get_btn_buscar());
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
	 * This method initializes _txt_idaplicacion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idaplicacion() {
		if (_txt_idaplicacion == null) {
			_txt_idaplicacion = new JTextField();
			_txt_idaplicacion.setToolTipText("numero interno de la unidad");
			_txt_idaplicacion.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_idaplicacion.setBounds(new Rectangle(104, 44, 160, 16));
		}
		return _txt_idaplicacion;
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
	 * This method initializes _txt_dominio	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_dominio() {
		if (_txt_dominio == null) {
			_txt_dominio = new JTextField();
			_txt_dominio.setBounds(new Rectangle(104, 75, 160, 16));
			_txt_dominio.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_dominio;
	}

	/**
	 * This method initializes _lst_marca	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_marca() {
		if (_lst_marca == null) {
			_lst_marca = new JComboBox();
			_lst_marca.setBounds(new Rectangle(102, 165, 160, 16));
			_lst_marca.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _lst_marca;
	}

	/**
	 * This method initializes _txt_cuenta	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_cuenta() {
		if (_txt_cuenta == null) {
			_txt_cuenta = new JTextField();
			_txt_cuenta.setBounds(new Rectangle(104, 105, 160, 16));
			_txt_cuenta.setToolTipText("cuenta contable");
			_txt_cuenta.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_cuenta;
	}

	/**
	 * This method initializes _lst_modelo	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_modelo() {
		if (_lst_modelo == null) {
			_lst_modelo = new JComboBox();
			_lst_modelo.setBounds(new Rectangle(102, 195, 160, 16));
			_lst_modelo.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _lst_modelo;
	}

	/**
	 * This method initializes _txt_anio	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_anio() {
		if (_txt_anio == null) {
			_txt_anio = new JTextField();
			_txt_anio.setBounds(new Rectangle(104, 135, 160, 16));
			_txt_anio.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_anio;
	}

	/**
	 * This method initializes _lst_version	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_version() {
		if (_lst_version == null) {
			_lst_version = new JComboBox();
			_lst_version.setBounds(new Rectangle(102, 225, 160, 16));
			_lst_version.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _lst_version;
	}

	/**
	 * This method initializes _txt_numeroMotor	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_numeroMotor() {
		if (_txt_numeroMotor == null) {
			_txt_numeroMotor = new JTextField();
			_txt_numeroMotor.setBounds(new Rectangle(104, 255, 160, 16));
			_txt_numeroMotor.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_numeroMotor;
	}

	/**
	 * This method initializes _txt_numeroChasis	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_numeroChasis() {
		if (_txt_numeroChasis == null) {
			_txt_numeroChasis = new JTextField();
			_txt_numeroChasis.setBounds(new Rectangle(104, 285, 160, 16));
			_txt_numeroChasis.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_numeroChasis;
	}

	/**
	 * This method initializes _txt_vencimiento	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_vencimiento() {
		if (_txt_vencimiento == null) {
			_txt_vencimiento = new JTextField();
			_txt_vencimiento.setBounds(new Rectangle(355,  315, 160, 16));
			_txt_vencimiento.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_vencimiento;
	}

	/**
	 * This method initializes _txt_KMinicial	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_KMinicial() {
		if (_txt_KMinicial == null) {
			_txt_KMinicial = new JTextField();
			_txt_KMinicial.setBounds(new Rectangle(105, 385, 90, 16));
			_txt_KMinicial.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_KMinicial;
	}

	/**
	 * This method initializes _txt_fecha	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_fecha() {
		if (_txt_fecha == null) {
			_txt_fecha = new JTextField();
			_txt_fecha.setBounds(new Rectangle(275, 385, 90, 16));
			_txt_fecha.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_fecha;
	}

	/**
	 * This method initializes _txt_KMactual	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_KMactual() {
		if (_txt_KMactual == null) {
			_txt_KMactual = new JTextField();
			_txt_KMactual.setBounds(new Rectangle(470, 385, 90, 16));
			_txt_KMactual.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_KMactual;
	}

	/**
	 * This method initializes jTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.setBounds(new Rectangle(15, 422, 557, 165));
			jTabbedPane.addTab("tarjetas", null, getJPanel(), null);
			jTabbedPane.addTab("equipamiento", null, getJPanel1(), null);
			jTabbedPane.addTab("planificacion", null, getJPanel2(), null);
			jTabbedPane.addTab("novedades", null, getJPanel3(), null);
			jTabbedPane.addTab("trabajos", null, getJPanel4(), null);
			jTabbedPane.addTab("auxilios", null, getJPanel5(), null);
		}
		return jTabbedPane;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.BOTH;
			gridBagConstraints1.gridy = 1;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.weighty = 1.0;
			gridBagConstraints1.gridx = 0;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.gridx = 0;
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
			jPanel.add(getJJToolBarBar11(), gridBagConstraints);
			jPanel.add(get_table_tarjetas(), gridBagConstraints1);
		}
		return jPanel;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.BOTH;
			gridBagConstraints3.gridy = 1;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.weighty = 1.0;
			gridBagConstraints3.gridx = 0;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints2.gridy = 0;
			gridBagConstraints2.weightx = 1.0;
			gridBagConstraints2.gridx = 0;
			jPanel1 = new JPanel();
			jPanel1.setLayout(new GridBagLayout());
			jPanel1.add(getJJToolBarBar111(), gridBagConstraints2);
			jPanel1.add(get_table_equipamiento(), gridBagConstraints3);
		}
		return jPanel1;
	}

	/**
	 * This method initializes jPanel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.fill = GridBagConstraints.BOTH;
			gridBagConstraints5.gridy = 1;
			gridBagConstraints5.weightx = 1.0;
			gridBagConstraints5.weighty = 1.0;
			gridBagConstraints5.gridx = 0;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints4.gridy = 0;
			gridBagConstraints4.weightx = 1.0;
			gridBagConstraints4.gridx = 0;
			jPanel2 = new JPanel();
			jPanel2.setLayout(new GridBagLayout());
			jPanel2.add(getJJToolBarBar1111(), gridBagConstraints4);
			jPanel2.add(get_table_planificacion(), gridBagConstraints5);
		}
		return jPanel2;
	}

	/**
	 * This method initializes jPanel3	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.fill = GridBagConstraints.BOTH;
			gridBagConstraints7.gridy = 1;
			gridBagConstraints7.weightx = 1.0;
			gridBagConstraints7.weighty = 1.0;
			gridBagConstraints7.gridx = 0;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints6.gridy = 0;
			gridBagConstraints6.weightx = 1.0;
			gridBagConstraints6.gridx = 0;
			jPanel3 = new JPanel();
			jPanel3.setLayout(new GridBagLayout());
			jPanel3.add(getJJToolBarBar11111(), gridBagConstraints6);
			jPanel3.add(get_table_novedad(), gridBagConstraints7);
		}
		return jPanel3;
	}

	/**
	 * This method initializes jPanel4	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel4() {
		if (jPanel4 == null) {
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.fill = GridBagConstraints.BOTH;
			gridBagConstraints9.gridy = 1;
			gridBagConstraints9.weightx = 1.0;
			gridBagConstraints9.weighty = 1.0;
			gridBagConstraints9.gridx = 0;
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints8.gridy = 0;
			gridBagConstraints8.weightx = 1.0;
			gridBagConstraints8.gridx = 0;
			jPanel4 = new JPanel();
			jPanel4.setLayout(new GridBagLayout());
			jPanel4.add(getJJToolBarBar111111(), gridBagConstraints8);
			jPanel4.add(get_table_trabajo(), gridBagConstraints9);
		}
		return jPanel4;
	}

	/**
	 * This method initializes jPanel5	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel5() {
		if (jPanel5 == null) {
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.fill = GridBagConstraints.BOTH;
			gridBagConstraints11.gridy = 1;
			gridBagConstraints11.weightx = 1.0;
			gridBagConstraints11.weighty = 1.0;
			gridBagConstraints11.gridx = 0;
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints10.gridy = 0;
			gridBagConstraints10.weightx = 1.0;
			gridBagConstraints10.gridx = 0;
			jPanel5 = new JPanel();
			jPanel5.setLayout(new GridBagLayout());
			jPanel5.add(getJJToolBarBar1111111(), gridBagConstraints10);
			jPanel5.add(get_table_auxilio(), gridBagConstraints11);
		}
		return jPanel5;
	}

	/**
	 * This method initializes jJToolBarBar11	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJJToolBarBar11() {
		if (jJToolBarBar11 == null) {
			jJToolBarBar11 = new JToolBar();
			jJToolBarBar11.setPreferredSize(new Dimension(46, 24));
			jJToolBarBar11.setOrientation(JToolBar.HORIZONTAL);
			jJToolBarBar11.setFloatable(false);
			jJToolBarBar11.add(get_btn_nuevaTarjeta());
			jJToolBarBar11.add(get_btn_eliminarTarjeta());
		}
		return jJToolBarBar11;
	}

	/**
	 * This method initializes _btn_nuevaTarjeta	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_nuevaTarjeta() {
		if (_btn_nuevaTarjeta == null) {
			_btn_nuevaTarjeta = new JButton();
			_btn_nuevaTarjeta.setIcon(new ImageIcon(getClass().getResource("/icons/stock_new-text.png")));
		}
		return _btn_nuevaTarjeta;
	}

	/**
	 * This method initializes _btn_eliminarTarjeta	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_eliminarTarjeta() {
		if (_btn_eliminarTarjeta == null) {
			_btn_eliminarTarjeta = new JButton();
			_btn_eliminarTarjeta.setIcon(new ImageIcon(getClass().getResource("/icons/edit-delete.png")));
		}
		return _btn_eliminarTarjeta;
	}

	/**
	 * This method initializes _table_tarjetas	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable get_table_tarjetas() {
		if (_table_tarjetas == null) {
			_table_tarjetas = new JTable();
		}
		return _table_tarjetas;
	}

	/**
	 * This method initializes jJToolBarBar111	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJJToolBarBar111() {
		if (jJToolBarBar111 == null) {
			jJToolBarBar111 = new JToolBar();
			jJToolBarBar111.setPreferredSize(new Dimension(46, 24));
			jJToolBarBar111.setOrientation(JToolBar.HORIZONTAL);
			jJToolBarBar111.setFloatable(false);
			jJToolBarBar111.add(get_btn_nuevoEquipamiento());
			jJToolBarBar111.add(get_btn_eliminarEquipamiento());
		}
		return jJToolBarBar111;
	}

	/**
	 * This method initializes _btn_nuevoEquipamiento	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_nuevoEquipamiento() {
		if (_btn_nuevoEquipamiento == null) {
			_btn_nuevoEquipamiento = new JButton();
			_btn_nuevoEquipamiento.setIcon(new ImageIcon(getClass().getResource("/icons/stock_new-text.png")));
		}
		return _btn_nuevoEquipamiento;
	}

	/**
	 * This method initializes _btn_eliminarEquipamiento	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_eliminarEquipamiento() {
		if (_btn_eliminarEquipamiento == null) {
			_btn_eliminarEquipamiento = new JButton();
			_btn_eliminarEquipamiento.setIcon(new ImageIcon(getClass().getResource("/icons/edit-delete.png")));
		}
		return _btn_eliminarEquipamiento;
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
	 * This method initializes jJToolBarBar1111	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJJToolBarBar1111() {
		if (jJToolBarBar1111 == null) {
			jJToolBarBar1111 = new JToolBar();
			jJToolBarBar1111.setPreferredSize(new Dimension(46, 24));
			jJToolBarBar1111.setOrientation(JToolBar.HORIZONTAL);
			jJToolBarBar1111.setFloatable(false);
			jJToolBarBar1111.add(get_btn_nuevaPlanificacion());
			jJToolBarBar1111.add(get_btn_eliminarPlanificacion());
		}
		return jJToolBarBar1111;
	}

	/**
	 * This method initializes _btn_nuevaPlanificacion	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_nuevaPlanificacion() {
		if (_btn_nuevaPlanificacion == null) {
			_btn_nuevaPlanificacion = new JButton();
			_btn_nuevaPlanificacion.setIcon(new ImageIcon(getClass().getResource("/icons/stock_new-text.png")));
		}
		return _btn_nuevaPlanificacion;
	}

	/**
	 * This method initializes _btn_eliminarPlanificacion	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_eliminarPlanificacion() {
		if (_btn_eliminarPlanificacion == null) {
			_btn_eliminarPlanificacion = new JButton();
			_btn_eliminarPlanificacion.setIcon(new ImageIcon(getClass().getResource("/icons/edit-delete.png")));
		}
		return _btn_eliminarPlanificacion;
	}

	/**
	 * This method initializes _table_planificacion	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable get_table_planificacion() {
		if (_table_planificacion == null) {
			_table_planificacion = new JTable();
		}
		return _table_planificacion;
	}

	/**
	 * This method initializes jJToolBarBar11111	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJJToolBarBar11111() {
		if (jJToolBarBar11111 == null) {
			jJToolBarBar11111 = new JToolBar();
			jJToolBarBar11111.setPreferredSize(new Dimension(46, 24));
			jJToolBarBar11111.setOrientation(JToolBar.HORIZONTAL);
			jJToolBarBar11111.setFloatable(false);
			jJToolBarBar11111.add(get_btn_nuevaNovedad());
			jJToolBarBar11111.add(get_btn_eliminarNovedad());
		}
		return jJToolBarBar11111;
	}

	/**
	 * This method initializes _btn_nuevaNovedad	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_nuevaNovedad() {
		if (_btn_nuevaNovedad == null) {
			_btn_nuevaNovedad = new JButton();
			_btn_nuevaNovedad.setIcon(new ImageIcon(getClass().getResource("/icons/stock_new-text.png")));
		}
		return _btn_nuevaNovedad;
	}

	/**
	 * This method initializes _btn_eliminarNovedad	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_eliminarNovedad() {
		if (_btn_eliminarNovedad == null) {
			_btn_eliminarNovedad = new JButton();
			_btn_eliminarNovedad.setIcon(new ImageIcon(getClass().getResource("/icons/edit-delete.png")));
		}
		return _btn_eliminarNovedad;
	}

	/**
	 * This method initializes _table_novedad	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable get_table_novedad() {
		if (_table_novedad == null) {
			_table_novedad = new JTable();
		}
		return _table_novedad;
	}

	/**
	 * This method initializes jJToolBarBar111111	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJJToolBarBar111111() {
		if (jJToolBarBar111111 == null) {
			jJToolBarBar111111 = new JToolBar();
			jJToolBarBar111111.setPreferredSize(new Dimension(46, 24));
			jJToolBarBar111111.setOrientation(JToolBar.HORIZONTAL);
			jJToolBarBar111111.setFloatable(false);
			jJToolBarBar111111.add(get_btn_nuevoTrabajo());
			jJToolBarBar111111.add(get_btn_eliminarTrabajo());
		}
		return jJToolBarBar111111;
	}

	/**
	 * This method initializes _btn_nuevoTrabajo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_nuevoTrabajo() {
		if (_btn_nuevoTrabajo == null) {
			_btn_nuevoTrabajo = new JButton();
			_btn_nuevoTrabajo.setIcon(new ImageIcon(getClass().getResource("/icons/stock_new-text.png")));
		}
		return _btn_nuevoTrabajo;
	}

	/**
	 * This method initializes _btn_eliminarTrabajo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_eliminarTrabajo() {
		if (_btn_eliminarTrabajo == null) {
			_btn_eliminarTrabajo = new JButton();
			_btn_eliminarTrabajo.setIcon(new ImageIcon(getClass().getResource("/icons/edit-delete.png")));
		}
		return _btn_eliminarTrabajo;
	}

	/**
	 * This method initializes _table_trabajo	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable get_table_trabajo() {
		if (_table_trabajo == null) {
			_table_trabajo = new JTable();
		}
		return _table_trabajo;
	}

	/**
	 * This method initializes jJToolBarBar1111111	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJJToolBarBar1111111() {
		if (jJToolBarBar1111111 == null) {
			jJToolBarBar1111111 = new JToolBar();
			jJToolBarBar1111111.setPreferredSize(new Dimension(46, 24));
			jJToolBarBar1111111.setOrientation(JToolBar.HORIZONTAL);
			jJToolBarBar1111111.setFloatable(false);
			jJToolBarBar1111111.add(get_btn_nuevoAuxilio());
			jJToolBarBar1111111.add(get_btn_eliminarAuxilio());
		}
		return jJToolBarBar1111111;
	}

	/**
	 * This method initializes _btn_nuevoAuxilio	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_nuevoAuxilio() {
		if (_btn_nuevoAuxilio == null) {
			_btn_nuevoAuxilio = new JButton();
			_btn_nuevoAuxilio.setIcon(new ImageIcon(getClass().getResource("/icons/stock_new-text.png")));
		}
		return _btn_nuevoAuxilio;
	}

	/**
	 * This method initializes _btn_eliminarAuxilio	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_eliminarAuxilio() {
		if (_btn_eliminarAuxilio == null) {
			_btn_eliminarAuxilio = new JButton();
			_btn_eliminarAuxilio.setIcon(new ImageIcon(getClass().getResource("/icons/edit-delete.png")));
		}
		return _btn_eliminarAuxilio;
	}

	/**
	 * This method initializes _table_auxilio	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable get_table_auxilio() {
		if (_table_auxilio == null) {
			_table_auxilio = new JTable();
		}
		return _table_auxilio;
	}

	/**
	 * This method initializes _btn_calendario	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_calendario() {
		if (_btn_calendario == null) {
			_btn_calendario = new JButton();
			_btn_calendario.setBounds(new Rectangle(368, 382, 20, 20));
			_btn_calendario.setIcon(new ImageIcon(getClass().getResource("/icons/calendar-16.gif")));
		}
		return _btn_calendario;
	}

	/**
	 * This method initializes jTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJTextArea() {
		if (jTextArea == null) {
			jTextArea = new JTextArea();
		}
		return jTextArea;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(106, 317, 160, 59));
			jScrollPane.setViewportView(getJTextArea());
		}
		return jScrollPane;
	}




}  //  @jve:decl-index=0:visual-constraint="10,10"
