package aplicacion.actualizacion.importar.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Point;
import javax.swing.JToolBar;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JCheckBox;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JTextField _txt_revision_actual = null;
	private JLabel jLabel1 = null;
	private JTextField _txt_revision_disponible = null;
	private JProgressBar jProgressBar = null;
	private JButton _btn_descargar = null;
	private JButton _btn_cancelar = null;
	private JButton _btn_salir = null;
	private JToolBar jToolBar = null;
	private JButton _btn_buscar_version = null;
	private JButton _btn_error = null;
	private JTextField _txt_fecha = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private JCheckBox _chk_mostrar_aplicadas = null;
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
		this.setSize(632, 347);
		this.setResizable(false);
		this.setContentPane(getJContentPane());
		this.setTitle("Actualizacion Web de Precios");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel1 = new JLabel();
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setLocation(new Point(2, 70));
			jLabel1.setSize(new Dimension(117, 16));
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setText("Ultima Disponible");
			jLabel = new JLabel();
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setLocation(new Point(2, 51));
			jLabel.setSize(new Dimension(116, 16));
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setText("Ultima Aplicada");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(get_txt_revision_actual(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(get_txt_revision_disponible(), null);
			jContentPane.add(getJProgressBar(), null);
			jContentPane.add(get_btn_cancelar(), null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(get_txt_fecha(), null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(get_chk_mostrar_aplicadas(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes _txt_revision_actual	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_revision_actual() {
		if (_txt_revision_actual == null) {
			_txt_revision_actual = new JTextField();
			_txt_revision_actual.setEditable(false);
			_txt_revision_actual.setSize(new Dimension(115, 16));
			_txt_revision_actual.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_revision_actual.setHorizontalAlignment(JTextField.RIGHT);
			_txt_revision_actual.setLocation(new Point(127, 51));
		}
		return _txt_revision_actual;
	}

	/**
	 * This method initializes _txt_revision_disponible	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_revision_disponible() {
		if (_txt_revision_disponible == null) {
			_txt_revision_disponible = new JTextField();
			_txt_revision_disponible.setEditable(false);
			_txt_revision_disponible.setSize(new Dimension(115, 16));
			_txt_revision_disponible.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_revision_disponible.setHorizontalAlignment(JTextField.RIGHT);
			_txt_revision_disponible.setLocation(new Point(127, 71));
		}
		return _txt_revision_disponible;
	}

	/**
	 * This method initializes jProgressBar	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	public JProgressBar getJProgressBar() {
		if (jProgressBar == null) {
			jProgressBar = new JProgressBar();
			jProgressBar.setBounds(new Rectangle(2, 27, 596, 16));
		}
		return jProgressBar;
	}

	/**
	 * This method initializes _btn_descargar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_descargar() {
		if (_btn_descargar == null) {
			_btn_descargar = new JButton();
			_btn_descargar.setIcon(new ImageIcon(getClass().getResource("/icons/stock_save-as.png")));
			_btn_descargar.setToolTipText("Descargar Actualizacion");
			_btn_descargar.setEnabled(false);
		}
		return _btn_descargar;
	}

	/**
	 * This method initializes _btn_cancelar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cancelar() {
		if (_btn_cancelar == null) {
			_btn_cancelar = new JButton();
			_btn_cancelar.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-stop.png")));
			_btn_cancelar.setSize(new Dimension(19, 18));
			_btn_cancelar.setLocation(new Point(601, 27));
		}
		return _btn_cancelar;
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
			_btn_salir.setPreferredSize(new Dimension(22, 22));
		}
		return _btn_salir;
	}

	/**
	 * This method initializes jToolBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar() {
		if (jToolBar == null) {
			jToolBar = new JToolBar();
			jToolBar.setBounds(new Rectangle(0, 0, 620, 25));
			jToolBar.setFloatable(false);
			jToolBar.add(get_btn_buscar_version());
			jToolBar.add(get_btn_descargar());
			jToolBar.add(get_btn_salir());
			jToolBar.addSeparator();
			jToolBar.add(get_btn_error());
		}
		return jToolBar;
	}

	/**
	 * This method initializes _btn_buscar_version	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_version() {
		if (_btn_buscar_version == null) {
			_btn_buscar_version = new JButton();
			_btn_buscar_version.setIcon(new ImageIcon(getClass().getResource("/icons/stock_hyperlink-internet-search.png")));
			_btn_buscar_version.setToolTipText("Buscar Version Disponible");
		}
		return _btn_buscar_version;
	}

	/**
	 * This method initializes _btn_error	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_error() {
		if (_btn_error == null) {
			_btn_error = new JButton();
			_btn_error.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-media-record.png")));
			_btn_error.setToolTipText("Envio de Informacion/Error a Sistemas");
		}
		return _btn_error;
	}

	/**
	 * This method initializes _txt_fecha	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_fecha() {
		if (_txt_fecha == null) {
			_txt_fecha = new JTextField();
			_txt_fecha.setBounds(new Rectangle(244, 71, 125, 16));
			_txt_fecha.setEditable(false);
			_txt_fecha.setEnabled(true);
		}
		return _txt_fecha;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(15, 100, 594, 191));
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
	 * This method initializes _chk_mostrar_aplicadas	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_mostrar_aplicadas() {
		if (_chk_mostrar_aplicadas == null) {
			_chk_mostrar_aplicadas = new JCheckBox();
			_chk_mostrar_aplicadas.setBounds(new Rectangle(421, 73, 118, 13));
			_chk_mostrar_aplicadas.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_mostrar_aplicadas.setText("Mostrar Aplicadas");
		}
		return _chk_mostrar_aplicadas;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
