package aplicacion.flota.proveedor.gui;

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
	
	private JTextField _txt_idproveedor = null;
	private JLabel jLabel = null;
	private JButton _btn_cancelar = null;
	private JButton _btn_buscar = null;
	private JButton _btn_guardar = null;
	private JButton _btn_eliminar = null;
	private JTable jTable = null;
	private LockableUI lockableUI=null;
	private JButton _btn_nuevo = null;
	private JLabel jLabel1 = null;
	private JTextField _txt_cuit = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JTextField _txt_telefono = null;
	private JButton _btn_rename = null;
	private JTextField _txt_direccion = null;
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
		this.setSize(328, 263);
		this.setContentPane(getJContentPane());
		this.setTitle("proveedor");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(15, 165, 81, 13));
			jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel3.setText("telefono");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(15, 125, 81, 13));
			jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel2.setText("direccion");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(14, 85, 81, 13));
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setText("cuit");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(14, 45, 81, 13));
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setText("idproveedor");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(get_txt_idproveedor(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(get_txt_cuit(), null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(get_txt_telefono(), null);
			jContentPane.add(get_txt_direccion(), null);
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
			jToolBar.add(get_btn_buscar());
			jToolBar.add(get_btn_nuevo());
			jToolBar.add(get_btn_rename());
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
	 * This method initializes _txt_idproveedor	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idproveedor() {
		if (_txt_idproveedor == null) {
			_txt_idproveedor = new JTextField();
			_txt_idproveedor.setToolTipText("Filtrar Parametros");
			_txt_idproveedor.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_idproveedor.setBounds(new Rectangle(104, 44, 160, 16));
		}
		return _txt_idproveedor;
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
	 * This method initializes _btn_nuevo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_nuevo() {
		if (_btn_nuevo == null) {
			_btn_nuevo = new JButton();
			_btn_nuevo.setIcon(new ImageIcon(getClass().getResource("/icons/stock_new-text.png")));
			_btn_nuevo.setToolTipText("nueva aplicacion");
		}
		return _btn_nuevo;
	}

	/**
	 * This method initializes _txt_cuit	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_cuit() {
		if (_txt_cuit == null) {
			_txt_cuit = new JTextField();
			_txt_cuit.setBounds(new Rectangle(104, 85, 160, 16));
			_txt_cuit.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_cuit;
	}

	/**
	 * This method initializes _txt_telefono	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_telefono() {
		if (_txt_telefono == null) {
			_txt_telefono = new JTextField();
			_txt_telefono.setBounds(new Rectangle(104, 165, 160, 16));
			_txt_telefono.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_telefono;
	}

	/**
	 * This method initializes _btn_rename	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_rename() {
		if (_btn_rename == null) {
			_btn_rename = new JButton();
			_btn_rename.setIcon(new ImageIcon(getClass().getResource("/icons/applications-system.png")));
			_btn_rename.setToolTipText("renombrar aplicacion");
		}
		return _btn_rename;
	}

	/**
	 * This method initializes _txt_direccion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField get_txt_direccion() {
		if (_txt_direccion == null) {
			_txt_direccion = new JTextField();
			_txt_direccion.setBounds(new Rectangle(104, 125, 160, 16));
			_txt_direccion.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_direccion;
	}




}  //  @jve:decl-index=0:visual-constraint="10,10"
