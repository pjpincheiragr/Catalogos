package aplicacion.flota.chofer.gui;

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
	
	private JTextField _txt_idchofer = null;
	private JLabel jLabel = null;
	private JButton _btn_cancelar = null;
	private JButton _btn_buscar = null;
	private JButton _btn_guardar = null;
	private JButton _btn_eliminar = null;
	private JTable jTable = null;
	private LockableUI lockableUI=null;
	private JLabel jLabel1 = null;
	private JTextField _txt_nombre = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JTextField _txt_DNI = null;
	private JTextField _txt_apellido = null;
	private JLabel jLabel31 = null;
	private JTextField _txt_licencia = null;
	private JLabel jLabel311 = null;
	private JTextField _txt_vencimiento = null;
	private JButton _btn_nuevo = null;
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
		this.setSize(378, 336);
		this.setContentPane(getJContentPane());
		this.setTitle("Chofer");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel311 = new JLabel();
			jLabel311.setBounds(new Rectangle(15, 245, 93, 14));
			jLabel311.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel311.setText("fecha vencimiento");
			jLabel311.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel31 = new JLabel();
			jLabel31.setBounds(new Rectangle(15, 205, 93, 14));
			jLabel31.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel31.setText("licencia");
			jLabel31.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(15, 165, 93, 13));
			jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel3.setText("DNI");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(15, 125, 93, 13));
			jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel2.setText("apellido");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(14, 85, 93, 13));
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setText("nombre");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(14, 45, 93, 13));
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setText("idchofer");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(get_txt_idchofer(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(get_txt_nombre(), null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(get_txt_DNI(), null);
			jContentPane.add(get_txt_apellido(), null);
			jContentPane.add(jLabel31, null);
			jContentPane.add(get_txt_licencia(), null);
			jContentPane.add(jLabel311, null);
			jContentPane.add(get_txt_vencimiento(), null);
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
			jToolBar.add(get_btn_nuevo());
			jToolBar.add(get_btn_buscar());
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
	 * This method initializes _txt_idchofer	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idchofer() {
		if (_txt_idchofer == null) {
			_txt_idchofer = new JTextField();
			_txt_idchofer.setToolTipText("Filtrar Parametros");
			_txt_idchofer.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_idchofer.setBounds(new Rectangle(120, 45, 160, 16));
		}
		return _txt_idchofer;
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
	 * This method initializes _txt_nombre	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_nombre() {
		if (_txt_nombre == null) {
			_txt_nombre = new JTextField();
			_txt_nombre.setBounds(new Rectangle(120, 85, 160, 16));
			_txt_nombre.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_nombre;
	}

	/**
	 * This method initializes _txt_DNI	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_DNI() {
		if (_txt_DNI == null) {
			_txt_DNI = new JTextField();
			_txt_DNI.setBounds(new Rectangle(120, 165, 160, 16));
			_txt_DNI.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_DNI;
	}

	/**
	 * This method initializes _txt_apellido	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_apellido() {
		if (_txt_apellido == null) {
			_txt_apellido = new JTextField();
			_txt_apellido.setBounds(new Rectangle(120, 125, 160, 16));
			_txt_apellido.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_apellido;
	}

	/**
	 * This method initializes _txt_licencia	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_licencia() {
		if (_txt_licencia == null) {
			_txt_licencia = new JTextField();
			_txt_licencia.setBounds(new Rectangle(120, 205, 160, 16));
			_txt_licencia.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_licencia;
	}

	/**
	 * This method initializes _txt_vencimiento	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_vencimiento() {
		if (_txt_vencimiento == null) {
			_txt_vencimiento = new JTextField();
			_txt_vencimiento.setBounds(new Rectangle(120, 245, 160, 16));
			_txt_vencimiento.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_vencimiento;
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




}  //  @jve:decl-index=0:visual-constraint="10,10"
