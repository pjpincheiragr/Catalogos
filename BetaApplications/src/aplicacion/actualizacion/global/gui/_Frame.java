package aplicacion.actualizacion.global.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JToolBar;
import java.awt.Point;
import javax.swing.JTable;
import javax.swing.JProgressBar;
import javax.swing.JCheckBox;
import javax.swing.JTabbedPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JToolBar jToolBar = null;
	private JButton _btn_salir = null;
	private JTable jTable = null;
	private JProgressBar jProgressBar = null;
	private JButton _btn_cancelar_tarea = null;
	private JTextField _txt_idcomprobante = null;
	private JLabel jLabel6 = null;
	private JButton _btn_nuevo = null;
	private JButton _btn_play = null;
	private JButton _btn_cancelar = null;
	private JTabbedPane jTabbedPane = null;
	private JScrollPane jScrollPane = null;
	private JTable _table_catalogos = null;
	private JProgressBar jProgressBar1 = null;
	private JButton _btn_cancelar_global = null;
	private JPanel jPanel = null;
	private JPanel jPanel1 = null;
	private JCheckBox _chk_seleccionar = null;
	private JComboBox _lst_modo = null;
	private JLabel jLabel = null;
	private JCheckBox _chk_actualizar = null;
	private JLabel jLabel1 = null;
	private JCheckBox _chk_exportar = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JTextField _txt_path = null;
	private JButton _btn_modificarCamino = null;
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
		this.setSize(759, 371);
		this.setResizable(false);
		this.setContentPane(getJContentPane());
		this.setTitle("Actualizacion global");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	public JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(28, 63, 84, 15));
			jLabel3.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3.setText("camino");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(377, 35, 60, 15));
			jLabel2.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel2.setText("exportar");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(270, 35, 60, 15));
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setText("actualizar");
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(28, 35, 84, 15));
			jLabel6.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel6.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel6.setText("idcomprobante");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(getJProgressBar(), null);
			jContentPane.add(get_btn_cancelar_tarea(), null);
			jContentPane.add(get_txt_idcomprobante(), null);
			jContentPane.add(jLabel6, null);
			jContentPane.add(getJTabbedPane(), null);
			jContentPane.add(getJProgressBar1(), null);
			jContentPane.add(get_btn_cancelar_global(), null);
			jContentPane.add(get_chk_actualizar(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(get_chk_exportar(), null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(get_txt_path(), null);
			jContentPane.add(get_btn_modificarCamino(), null);
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
			jToolBar.setSize(new Dimension(751, 23));
			jToolBar.setLocation(new Point(0, 1));
			jToolBar.setFloatable(false);
			jToolBar.add(get_btn_cancelar());
			jToolBar.add(get_btn_nuevo());
			jToolBar.add(get_btn_salir());
			jToolBar.add(get_btn_play());
			jToolBar.addSeparator();
			
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
			_btn_salir.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-logout.png")));
		}
		return _btn_salir;
	}

	/**
	 * This method initializes jProgressBar	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	public JProgressBar getJProgressBar() {
		if (jProgressBar == null) {
			jProgressBar = new JProgressBar();
			jProgressBar.setBounds(new Rectangle(22, 305, 680, 19));//22,251,680,19
		}
		return jProgressBar;
	}

	/**
	 * This method initializes _btn_cancelar_tarea	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cancelar_tarea() {
		if (_btn_cancelar_tarea == null) {
			_btn_cancelar_tarea = new JButton();
			_btn_cancelar_tarea.setBounds(new Rectangle(710, 275, 22, 20));
			_btn_cancelar_tarea.setIcon(new ImageIcon(getClass().getResource("/icons/stock_calc-cancel.png")));
		}
		return _btn_cancelar_tarea;
	}

	/**
	 * This method initializes _txt_idcomprobante	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idcomprobante() {
		if (_txt_idcomprobante == null) {
			_txt_idcomprobante = new JTextField();
			_txt_idcomprobante.setSize(new Dimension(105, 16));
			_txt_idcomprobante.setLocation(new Point(120, 35));
		}
		return _txt_idcomprobante;
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
			_btn_nuevo.setToolTipText("Nueva Operacion de Actualizacion");
		}
		return _btn_nuevo;
	}

	/**
	 * This method initializes _btn_play	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_play() {
		if (_btn_play == null) {
			_btn_play = new JButton();
			_btn_play.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-media-play-ltr.png")));
		}
		return _btn_play;
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
		}
		return _btn_cancelar;
	}

	/**
	 * This method initializes jTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.setBounds(new Rectangle(22, 85, 714, 180));
			jTabbedPane.setFont(new Font("Dialog", Font.PLAIN, 10));
			jTabbedPane.addTab("Catalogos", null, getJPanel(), null);
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
			jScrollPane.setViewportView(get_table_catalogos());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes _table_catalogos	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable get_table_catalogos() {
		if (_table_catalogos == null) {
			_table_catalogos = new JTable();
		}
		return _table_catalogos;
	}

	public JTable getJTable() {
		return jTable;
	}

	public void setJTable(JTable table) {
		this._table_catalogos= table;
		this.jScrollPane.setViewportView(_table_catalogos);
	}

	/**
	 * This method initializes jProgressBar1	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	public JProgressBar getJProgressBar1() {
		if (jProgressBar1 == null) {
			jProgressBar1 = new JProgressBar();
			jProgressBar1.setBounds(new Rectangle(22,275,680,19));
		}
		return jProgressBar1;
	}

	/**
	 * This method initializes _btn_cancelar_global	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cancelar_global() {
		if (_btn_cancelar_global == null) {
			_btn_cancelar_global = new JButton();
			_btn_cancelar_global.setBounds(new Rectangle(710, 305, 22, 20));
			_btn_cancelar_global.setIcon(new ImageIcon(getClass().getResource("/icons/stock_calc-cancel.png")));
		}
		return _btn_cancelar_global;
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
			gridBagConstraints.gridx = 0;
			gridBagConstraints.weighty = 0.0D;
			gridBagConstraints.weightx = 1.0D;
			gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints.anchor = GridBagConstraints.EAST;
			gridBagConstraints.gridy = 0;
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
			jPanel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jPanel.add(getJPanel1(), gridBagConstraints);
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
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 1;
			gridBagConstraints4.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints4.anchor = GridBagConstraints.WEST;
			gridBagConstraints4.insets = new Insets(0, 200, 0, 0);
			gridBagConstraints4.ipadx = 0;
			gridBagConstraints4.gridy = 0;
			jLabel = new JLabel();
			jLabel.setText("Modo");
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints3.gridy = 0;
			gridBagConstraints3.weightx = 0.0D;
			gridBagConstraints3.ipadx = 100;
			gridBagConstraints3.gridwidth = 1;
			gridBagConstraints3.anchor = GridBagConstraints.EAST;
			gridBagConstraints3.insets = new Insets(0, 10, 0, 0);
			gridBagConstraints3.ipady = -4;
			gridBagConstraints3.gridx = 2;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.insets = new Insets(0, 0, 0, 0);
			gridBagConstraints2.gridy = 0;
			gridBagConstraints2.ipadx = 0;
			gridBagConstraints2.ipady = 0;
			gridBagConstraints2.anchor = GridBagConstraints.WEST;
			gridBagConstraints2.weightx = 1.0D;
			gridBagConstraints2.gridx = 0;
			jPanel1 = new JPanel();
			jPanel1.setLayout(new GridBagLayout());
			jPanel1.add(get_chk_seleccionar(), gridBagConstraints2);
			jPanel1.add(get_lst_modo(), gridBagConstraints3);
			jPanel1.add(jLabel, gridBagConstraints4);
		}
		return jPanel1;
	}

	/**
	 * This method initializes _chk_seleccionar	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_seleccionar() {
		if (_chk_seleccionar == null) {
			_chk_seleccionar = new JCheckBox();
			_chk_seleccionar.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_seleccionar.setText("Seleccionar");
		}
		return _chk_seleccionar;
	}

	/**
	 * This method initializes _lst_modo	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_modo() {
		if (_lst_modo == null) {
			_lst_modo = new JComboBox();
			_lst_modo.setFont(new Font("Dialog", Font.PLAIN, 10));
			_lst_modo.addItem("");
			_lst_modo.addItem("ODBC");
			_lst_modo.addItem("Archivo");
		}
		return _lst_modo;
	}

	/**
	 * This method initializes _chk_actualizar	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_actualizar() {
		if (_chk_actualizar == null) {
			_chk_actualizar = new JCheckBox();
			_chk_actualizar.setBounds(new Rectangle(243, 32, 21, 21));
			_chk_actualizar.setSelected(true);
		}
		return _chk_actualizar;
	}

	/**
	 * This method initializes _chk_exportar	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_exportar() {
		if (_chk_exportar == null) {
			_chk_exportar = new JCheckBox();
			_chk_exportar.setBounds(new Rectangle(350, 32, 21, 21));
			_chk_exportar.setSelected(true);
		}
		return _chk_exportar;
	}

	/**
	 * This method initializes _txt_path	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_path() {
		if (_txt_path == null) {
			_txt_path = new JTextField();
			_txt_path.setBounds(new Rectangle(120, 63, 400, 16));
		}
		return _txt_path;
	}

	/**
	 * This method initializes _btn_modificarCamino	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_modificarCamino() {
		if (_btn_modificarCamino == null) {
			_btn_modificarCamino = new JButton();
			_btn_modificarCamino.setBounds(new Rectangle(525, 61, 22, 20));
			_btn_modificarCamino.setIcon(new ImageIcon(getClass().getResource("/icons/pedidos.png")));
		}
		return _btn_modificarCamino;
	}
}  //  @jve:decl-index=0:visual-constraint="9,17"
