package aplicacion.asterisk.manager.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;

public class PanelAsterisk extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel jLabel = null;
	private JTextField _txt_callerid = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;  //  @jve:decl-index=0:visual-constraint="549,26"
	private JToolBar jToolBar = null;
	private JLabel jLabel1 = null;
	private JTextField _txt_extension = null;
	private JButton _btn_buscar_llamadas = null;
	private JButton _btn_maestro = null;
	private JButton _btn_pdc = null;
	private JButton _btn_visor_pdc = null;
	private JButton _btn_guardar = null;
	private JButton _btn_eliminar = null;
	private JTabbedPane jTabbedPane = null;
	private JPanel jPanel = null;
	private JScrollPane jScrollPane1 = null;
	private JTable jTable1 = null;
	
	/**
	 * This is the default constructor
	 */
	public PanelAsterisk() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		jLabel1 = new JLabel();
		jLabel1.setBounds(new Rectangle(404, 31, 46, 18));
		jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
		jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabel1.setText("Extension");
		this.setSize(518, 251);
		this.setLayout(null);
		jLabel = new JLabel();
		jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
		jLabel.setBounds(new Rectangle(4, 31, 43, 17));
		jLabel.setText("Caller ID");
		add(jLabel, null);
		add(get_txt_callerid(), null);
		this.add(getJToolBar(), null);
		this.add(jLabel1, null);
		//add(get_btn_guardar(), null);
		this.add(get_txt_extension(), null);
		this.add(get_btn_buscar_llamadas(), null);
		this.add(getJTabbedPane(), null);
	}
	
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(4, 9, 504, 105));
			jScrollPane.setViewportView(getJTable());
		}
		return jScrollPane;
	}
	/**
	 * This method initializes _txt_callerid	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_callerid() {
		if (_txt_callerid == null) {
			_txt_callerid = new JTextField();
			_txt_callerid.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_callerid.setEditable(true);
			_txt_callerid.setBounds(new Rectangle(49, 32, 119, 18));
			_txt_callerid.setToolTipText("Numero de Telefono");
			_txt_callerid.setHorizontalAlignment(JTextField.RIGHT);
		}
		return _txt_callerid;
	}

	/**
	 * This method initializes jTable	
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
	 * This method initializes jToolBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar() {
		if (jToolBar == null) {
			jToolBar = new JToolBar();
			jToolBar.setBounds(new Rectangle(4, 3, 510, 24));
			jToolBar.setFloatable(false);
			jToolBar.add(get_btn_guardar());
			jToolBar.add(get_btn_eliminar());
			jToolBar.add(get_btn_maestro());
			jToolBar.add(get_btn_pdc());
			jToolBar.add(get_btn_visor_pdc());
		}
		return jToolBar;
	}

	/**
	 * This method initializes _txt_extension	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_extension() {
		if (_txt_extension == null) {
			_txt_extension = new JTextField();
			_txt_extension.setBounds(new Rectangle(451, 31, 62, 18));
			_txt_extension.setHorizontalAlignment(JTextField.RIGHT);
			_txt_extension.setEditable(false);
		}
		return _txt_extension;
	}

	/**
	 * This method initializes _btn_buscar_llamadas	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_llamadas() {
		if (_btn_buscar_llamadas == null) {
			_btn_buscar_llamadas = new JButton();
			_btn_buscar_llamadas.setBounds(new Rectangle(171, 33, 20, 16));
			_btn_buscar_llamadas.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-searchtool.png")));
		}
		return _btn_buscar_llamadas;
	}

	/**
	 * This method initializes _btn_maestro	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_maestro() {
		if (_btn_maestro == null) {
			_btn_maestro = new JButton();
			_btn_maestro.setIcon(new ImageIcon(getClass().getResource("/icons/contact-new.png")));
			_btn_maestro.setToolTipText("Ver Maestro");
		}
		return _btn_maestro;
	}

	/**
	 * This method initializes _btn_pdc	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_pdc() {
		if (_btn_pdc == null) {
			_btn_pdc = new JButton();
			_btn_pdc.setIcon(new ImageIcon(getClass().getResource("/icons/document-new.png")));
			_btn_pdc.setToolTipText("Nuevo Pedido");
		}
		return _btn_pdc;
	}

	/**
	 * This method initializes _btn_visor_pdc	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_visor_pdc() {
		if (_btn_visor_pdc == null) {
			_btn_visor_pdc = new JButton();
			_btn_visor_pdc.setIcon(new ImageIcon(getClass().getResource("/icons/stock_home.png")));
			_btn_visor_pdc.setToolTipText("Visor de Pedidos");
		}
		return _btn_visor_pdc;
	}

	/**
	 * This method initializes _btn_guardar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_guardar() {
		if (_btn_guardar == null) {
			_btn_guardar = new JButton();
			_btn_guardar.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-save.png")));
			_btn_guardar.setToolTipText("Guardar Enlaces Telefono Cuenta");
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
			_btn_eliminar.setToolTipText("Eliminar Enlace Telefono Cuenta");
		}
		return _btn_eliminar;
	}

	/**
	 * This method initializes jTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	public JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.setBounds(new Rectangle(1, 55, 515, 167));
			jTabbedPane.addTab("Cuenta", null, getJPanel(), null);
			jTabbedPane.addTab("Historial", null, getJScrollPane1(), null);
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
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.add(getJScrollPane(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
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


}  //  @jve:decl-index=0:visual-constraint="10,10"
