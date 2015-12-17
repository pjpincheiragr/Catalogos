package aplicacion.flota.modelo.gui;

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

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JToolBar jToolBar = null;
	private JButton _btn_salir = null;
	
	private JTextField _txt_marca = null;
	private JLabel jLabel = null;
	private JButton _btn_cancelar = null;
	private JButton _btn_buscar = null;
	private JButton _btn_guardar = null;
	private JButton _btn_eliminar = null;
	private JTable jTable = null;
	private LockableUI lockableUI=null;
	private JLabel jLabel1 = null;
	private JTextField _txt_modelo = null;
	private JLabel jLabel2 = null;
	private JTextField _txt_version = null;
	private DisplayCanvas canvas = null;
	private JToolBar jToolBar1 = null;
	private JButton _btn_buscarImagen = null;
	private JButton _btn_goFirst = null;
	private JButton _btn_anterior = null;
	private JButton _btn_siguiente = null;
	private JButton _btn_goLast = null;
	private JButton _btn_eliminarFoto = null;
	private JTabbedPane jTabbedPane = null;
	private JPanel jPanel = null;
	private JPanel jPanel1 = null;
	private JToolBar jJToolBarBar = null;
	private JScrollPane jScrollPane = null;
	private JTable _table_equipamiento = null;
	private JButton _btn_nuevoEquipamiento = null;
	private JButton _btn_eliminarEquipamiento = null;
	private JToolBar jJToolBarBar1 = null;
	private JButton _btn_nuevoChequeo = null;
	private JButton _btn_eliminarChequeo = null;
	private JScrollPane jScrollPane1 = null;
	private JTable _table_chequeo = null;
	private JPanel jPanel2 = null;
	private JToolBar jJToolBarBar11 = null;
	private JButton _btn_nuevoMantenimiento = null;
	private JButton _btn_eliminarMantenimiento = null;
	private JScrollPane jScrollPane2 = null;
	private JTable _table_mantenimiento = null;
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
		this.setSize(545, 400);
		this.setContentPane(getJContentPane());
		this.setTitle("Modelo");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(15, 125, 81, 13));
			jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel2.setText("version");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(14, 85, 81, 13));
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setText("modelo");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(14, 45, 81, 13));
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setText("marca");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(get_txt_marca(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(get_txt_modelo(), null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(get_txt_version(), null);
			jContentPane.add(getCanvas(), null);
			jContentPane.add(getJToolBar1(), null);
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
	 * This method initializes _txt_marca	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_marca() {
		if (_txt_marca == null) {
			_txt_marca = new JTextField();
			_txt_marca.setToolTipText("Filtrar Parametros");
			_txt_marca.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_marca.setBounds(new Rectangle(104, 44, 160, 16));
		}
		return _txt_marca;
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
	 * This method initializes _txt_modelo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_modelo() {
		if (_txt_modelo == null) {
			_txt_modelo = new JTextField();
			_txt_modelo.setBounds(new Rectangle(104, 85, 160, 16));
			_txt_modelo.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_modelo;
	}

	/**
	 * This method initializes _txt_version	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_version() {
		if (_txt_version == null) {
			_txt_version = new JTextField();
			_txt_version.setBounds(new Rectangle(104, 125, 160, 16));
			_txt_version.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_version;
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
			canvas.setBounds(new Rectangle(378, 44, 90, 90));
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
			jToolBar1.setBounds(new Rectangle(339, 139, 172, 25));
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
	 * This method initializes jTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.setBounds(new Rectangle(13, 180, 513, 181));
			jTabbedPane.addTab("equipamiento", null, getJPanel(), null);
			jTabbedPane.addTab("chequeo", null, getJPanel1(), null);
			jTabbedPane.addTab("Mantenimiento", null, getJPanel2(), null);
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
			jPanel.add(getJJToolBarBar(), gridBagConstraints);
			jPanel.add(getJScrollPane(), gridBagConstraints1);
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
			jPanel1.add(getJJToolBarBar1(), gridBagConstraints2);
			jPanel1.add(getJScrollPane1(), gridBagConstraints3);
		}
		return jPanel1;
	}

	/**
	 * This method initializes jJToolBarBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJJToolBarBar() {
		if (jJToolBarBar == null) {
			jJToolBarBar = new JToolBar();
			jJToolBarBar.setPreferredSize(new Dimension(46, 24));
			jJToolBarBar.setFloatable(false);
			jJToolBarBar.add(get_btn_nuevoEquipamiento());
			jJToolBarBar.add(get_btn_eliminarEquipamiento());
		}
		return jJToolBarBar;
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
	 * This method initializes jJToolBarBar1	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJJToolBarBar1() {
		if (jJToolBarBar1 == null) {
			jJToolBarBar1 = new JToolBar();
			jJToolBarBar1.setPreferredSize(new Dimension(46, 24));
			jJToolBarBar1.setOrientation(JToolBar.HORIZONTAL);
			jJToolBarBar1.setFloatable(false);
			jJToolBarBar1.add(get_btn_nuevoChequeo());
			jJToolBarBar1.add(get_btn_eliminarChequeo());
		}
		return jJToolBarBar1;
	}

	/**
	 * This method initializes _btn_nuevoChequeo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_nuevoChequeo() {
		if (_btn_nuevoChequeo == null) {
			_btn_nuevoChequeo = new JButton();
			_btn_nuevoChequeo.setIcon(new ImageIcon(getClass().getResource("/icons/stock_new-text.png")));
		}
		return _btn_nuevoChequeo;
	}

	/**
	 * This method initializes _btn_eliminarChequeo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_eliminarChequeo() {
		if (_btn_eliminarChequeo == null) {
			_btn_eliminarChequeo = new JButton();
			_btn_eliminarChequeo.setIcon(new ImageIcon(getClass().getResource("/icons/edit-delete.png")));
		}
		return _btn_eliminarChequeo;
	}

	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(get_table_chequeo());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes _table_chequeo	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable get_table_chequeo() {
		if (_table_chequeo == null) {
			_table_chequeo = new JTable();
		}
		return _table_chequeo;
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
			jPanel2.add(getJJToolBarBar11(), gridBagConstraints4);
			jPanel2.add(getJScrollPane2(), gridBagConstraints5);
		}
		return jPanel2;
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
			jJToolBarBar11.add(get_btn_nuevoMantenimiento());
			jJToolBarBar11.add(get_btn_eliminarMantenimiento());
		}
		return jJToolBarBar11;
	}

	/**
	 * This method initializes _btn_nuevoMantenimiento	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_nuevoMantenimiento() {
		if (_btn_nuevoMantenimiento == null) {
			_btn_nuevoMantenimiento = new JButton();
			_btn_nuevoMantenimiento.setIcon(new ImageIcon(getClass().getResource("/icons/stock_new-text.png")));
		}
		return _btn_nuevoMantenimiento;
	}

	/**
	 * This method initializes _btn_eliminarMantenimiento	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_eliminarMantenimiento() {
		if (_btn_eliminarMantenimiento == null) {
			_btn_eliminarMantenimiento = new JButton();
			_btn_eliminarMantenimiento.setIcon(new ImageIcon(getClass().getResource("/icons/edit-delete.png")));
		}
		return _btn_eliminarMantenimiento;
	}

	/**
	 * This method initializes jScrollPane2	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane2() {
		if (jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setViewportView(get_table_mantenimiento());
		}
		return jScrollPane2;
	}

	/**
	 * This method initializes _table_mantenimiento	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable get_table_mantenimiento() {
		if (_table_mantenimiento == null) {
			_table_mantenimiento = new JTable();
		}
		return _table_mantenimiento;
	}




}  //  @jve:decl-index=0:visual-constraint="13,75"
