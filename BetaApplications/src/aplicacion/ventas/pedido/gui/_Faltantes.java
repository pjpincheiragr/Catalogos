package aplicacion.ventas.pedido.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Rectangle;
import java.awt.GridBagLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.JToolBar;
import java.awt.Toolkit;
import java.awt.Point;
import java.awt.Font;

public class _Faltantes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel jPanel = null;
	private JButton _btn_marcar_faltantes = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private JCheckBox _chk_seleccionar = null;
	private JLabel jLabel = null;
	private JTextField _txt_unidades = null;
	private JToolBar jToolBar = null;
	private JTextField _txt_idcomprobante = null;
	private JButton _btn_salir = null;
	private JCheckBox _chk_asociar = null;
	/**
	 * This is the default constructor
	 */
	public _Faltantes() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(640, 436);
		this.setContentPane(getJContentPane());
		this.setTitle("Faltantes");
		this.setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/chrome.png")));
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(18, 386, 64, 14));
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setText("Faltantes");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJPanel(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(get_txt_unidades(), null);
			jContentPane.add(getJToolBar(), null);
		}
		return jContentPane;
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
			jPanel.setBounds(new Rectangle(5, 26, 622, 353));
			jPanel.setBackground(Color.gray);
			jPanel.add(getJScrollPane(), null);
			jPanel.add(get_chk_seleccionar(), null);
			jPanel.add(get_txt_idcomprobante(), null);
			jPanel.add(get_chk_asociar(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes _btn_marcar_faltantes	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_marcar_faltantes() {
		if (_btn_marcar_faltantes == null) {
			_btn_marcar_faltantes = new JButton();
			_btn_marcar_faltantes.setToolTipText("Marcar Faltantes");
			_btn_marcar_faltantes.setIcon(new ImageIcon(getClass().getResource("/icons/emblem-default.png")));
			_btn_marcar_faltantes.setText("");
		}
		return _btn_marcar_faltantes;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(15, 33, 595, 305));
			jScrollPane.setViewportView(getJTable());
		}
		return jScrollPane;
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
	/**
	 * This method initializes _chk_seleccionar	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_seleccionar() {
		if (_chk_seleccionar == null) {
			_chk_seleccionar = new JCheckBox();
			_chk_seleccionar.setBounds(new Rectangle(14, 6, 124, 17));
			_chk_seleccionar.setSelected(true);
			_chk_seleccionar.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_seleccionar.setText("seleccionar");
		}
		return _chk_seleccionar;
	}

	/**
	 * This method initializes _txt_unidades	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_unidades() {
		if (_txt_unidades == null) {
			_txt_unidades = new JTextField();
			_txt_unidades.setBounds(new Rectangle(91, 385, 71, 17));
			_txt_unidades.setEditable(false);
			_txt_unidades.setHorizontalAlignment(JTextField.RIGHT);
		}
		return _txt_unidades;
	}

	/**
	 * This method initializes jToolBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar() {
		if (jToolBar == null) {
			jToolBar = new JToolBar();
			jToolBar.setFloatable(false);
			jToolBar.setSize(new Dimension(631, 22));
			jToolBar.setLocation(new Point(0, 0));
			jToolBar.add(get_btn_marcar_faltantes());
			jToolBar.add(get_btn_salir());
		}
		return jToolBar;
	}

	/**
	 * This method initializes _txt_idcomprobante	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idcomprobante() {
		if (_txt_idcomprobante == null) {
			_txt_idcomprobante = new JTextField();
			_txt_idcomprobante.setBounds(new Rectangle(498, 7, 110, 18));
			_txt_idcomprobante.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_idcomprobante.setEditable(false);
			_txt_idcomprobante.setHorizontalAlignment(JTextField.RIGHT);
		}
		return _txt_idcomprobante;
	}

	/**
	 * This method initializes _btn_salir	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_salir() {
		if (_btn_salir == null) {
			_btn_salir = new JButton();
			_btn_salir.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-logout.png")));
		}
		return _btn_salir;
	}

	/**
	 * This method initializes _chk_asociar	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_asociar() {
		if (_chk_asociar == null) {
			_chk_asociar = new JCheckBox();
			_chk_asociar.setBounds(new Rectangle(417, 6, 72, 18));
			_chk_asociar.setText("Asociar");
			_chk_asociar.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_asociar.setToolTipText("Seleccione esta opcion para asociar los faltantes con el pedido de cliente");
		}
		return _chk_asociar;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
