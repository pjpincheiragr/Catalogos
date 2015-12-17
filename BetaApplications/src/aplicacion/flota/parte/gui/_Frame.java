package aplicacion.flota.parte.gui;

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
	private JButton _btn_cargar = null;
	private JButton _btn_salir = null;
	
	private JTextField _txt_idaplicacion = null;
	private JLabel jLabel = null;
	private JButton _btn_cancelar = null;
	private JButton _btn_buscar_aplicacion = null;
	private JButton _btn_guardar = null;
	private JButton _btn_eliminar = null;
	private JTable jTable = null;
	private JButton _btn_error = null;
	private LockableUI lockableUI=null;
	private JButton _btn_nueva = null;
	private JLabel jLabel1 = null;
	private JTextField _txt_nombre = null;
	private JLabel jLabel2 = null;
	private JComboBox _lst_area = null;
	private JLabel jLabel3 = null;
	private JTextField _txt_lanzador = null;
	private DisplayCanvas canvas = null;
	private JLabel jLabel4 = null;
	private JToolBar jToolBar1 = null;
	private JButton _btn_buscarImagen = null;
	private JButton _btn_goFirst = null;
	private JButton _btn_anterior = null;
	private JButton _btn_siguiente = null;
	private JButton _btn_goLast = null;
	private JButton _btn_eliminarFoto = null;
	private JButton _btn_rename = null;
	private JButton _btn_probar_aplicacion = null;
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
		this.setSize(545, 263);
		this.setContentPane(getJContentPane());
		this.setTitle("Aplicaciones");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(375,44, 43, 15));
			jLabel4.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
			jLabel4.setText("icono");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(15, 165, 81, 13));
			jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel3.setText("lanzador");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(15, 125, 81, 13));
			jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel2.setText("area");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(14, 85, 81, 13));
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setText("nombre");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(14, 45, 81, 13));
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setText("idaplicacion");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(get_txt_idaplicacion(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(get_btn_buscar_aplicacion(), null);
			jContentPane.add(get_btn_cargar(), null);
			//jContentPane.add(getJXLayerUI(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(get_txt_nombre(), null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(get_lst_area(), null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(get_txt_lanzador(), null);
			jContentPane.add(getCanvas(), null);
			jContentPane.add(jLabel4, null);
			jContentPane.add(getJToolBar1(), null);
			jContentPane.add(get_btn_probar_aplicacion(), null);
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
			jToolBar.add(get_btn_rename());
			jToolBar.add(get_btn_eliminar());
			jToolBar.add(get_btn_salir());
			jToolBar.add(get_btn_error());
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
			_btn_cargar.setBounds(new Rectangle(291, 42, 23, 18));
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
	 * This method initializes _txt_idaplicacion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idaplicacion() {
		if (_txt_idaplicacion == null) {
			_txt_idaplicacion = new JTextField();
			_txt_idaplicacion.setToolTipText("Filtrar Parametros");
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
	 * This method initializes _btn_buscar_aplicacion	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_aplicacion() {
		if (_btn_buscar_aplicacion == null) {
			_btn_buscar_aplicacion = new JButton();
			_btn_buscar_aplicacion.setBounds(new Rectangle(265, 42, 23, 18));
			_btn_buscar_aplicacion.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-searchtool.png")));
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
	 * This method initializes _txt_nombre	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_nombre() {
		if (_txt_nombre == null) {
			_txt_nombre = new JTextField();
			_txt_nombre.setBounds(new Rectangle(104, 85, 160, 16));
			_txt_nombre.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_nombre;
	}

	/**
	 * This method initializes _lst_area	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_area() {
		if (_lst_area == null) {
			_lst_area = new JComboBox();
			_lst_area.setBounds(new Rectangle(104, 125,160, 16));
			_lst_area.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _lst_area;
	}

	/**
	 * This method initializes _txt_lanzador	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_lanzador() {
		if (_txt_lanzador == null) {
			_txt_lanzador = new JTextField();
			_txt_lanzador.setBounds(new Rectangle(104, 165, 160, 16));
			_txt_lanzador.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_lanzador;
	}

	/**
	 * This method initializes canvas	
	 * 	
	 * @return aplicacion.compras.carga.items.logic.DisplayCanvas	
	 */
	public DisplayCanvas getCanvas() {
		if (canvas == null) {
			canvas = new DisplayCanvas();
			canvas.setLayout(new GridBagLayout());
			canvas.setBounds(new Rectangle(353, 65, 90, 90));
			canvas.setMove(true);
		}
		return canvas;
	}

	/**
	 * This method initializes jToolBar1	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar1() {
		if (jToolBar1 == null) {
			jToolBar1 = new JToolBar();
			jToolBar1.setBounds(new Rectangle(312, 159, 172, 25));
			jToolBar1.setFloatable(false);
			jToolBar1.add(get_btn_buscarImagen());
			jToolBar1.add(get_btn_goFirst());
			jToolBar1.add(get_btn_anterior());
			jToolBar1.add(get_btn_siguiente());
			jToolBar1.add(get_btn_goLast());
			jToolBar1.add(get_btn_eliminarFoto());
		}
		return jToolBar1;
	}

	/**
	 * This method initializes _btn_buscarImagen	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscarImagen() {
		if (_btn_buscarImagen == null) {
			_btn_buscarImagen = new JButton();
			_btn_buscarImagen.setIcon(new ImageIcon(getClass().getResource("/icons/pedidos.png")));
		}
		return _btn_buscarImagen;
	}

	/**
	 * This method initializes _btn_goFirst	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_goFirst() {
		if (_btn_goFirst == null) {
			_btn_goFirst = new JButton();
			_btn_goFirst.setIcon(new ImageIcon(getClass().getResource("/icons/go-first.png")));
		}
		return _btn_goFirst;
	}

	/**
	 * This method initializes _btn_anterior	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_anterior() {
		if (_btn_anterior == null) {
			_btn_anterior = new JButton();
			_btn_anterior.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-go-back-ltr.png")));
		}
		return _btn_anterior;
	}

	/**
	 * This method initializes _btn_siguiente	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_siguiente() {
		if (_btn_siguiente == null) {
			_btn_siguiente = new JButton();
			_btn_siguiente.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-go-back-rtl.png")));
		}
		return _btn_siguiente;
	}

	/**
	 * This method initializes _btn_goLast	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_goLast() {
		if (_btn_goLast == null) {
			_btn_goLast = new JButton();
			_btn_goLast.setIcon(new ImageIcon(getClass().getResource("/icons/stock_last.png")));
		}
		return _btn_goLast;
	}

	/**
	 * This method initializes _btn_eliminarFoto	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_eliminarFoto() {
		if (_btn_eliminarFoto == null) {
			_btn_eliminarFoto = new JButton();
			_btn_eliminarFoto.setIcon(new ImageIcon(getClass().getResource("/icons/edit-delete.png")));
		}
		return _btn_eliminarFoto;
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
	 * This method initializes _btn_probar_aplciacion	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_probar_aplicacion() {
		if (_btn_probar_aplicacion == null) {
			_btn_probar_aplicacion = new JButton();
			_btn_probar_aplicacion.setBounds(new Rectangle(265, 163, 23, 18));
			_btn_probar_aplicacion.setIcon(new ImageIcon(getClass().getResource("/icons/stock_media-play.png")));
			_btn_probar_aplicacion.setToolTipText("probar aplicacion");
		}
		return _btn_probar_aplicacion;
	}




}  //  @jve:decl-index=0:visual-constraint="10,10"
