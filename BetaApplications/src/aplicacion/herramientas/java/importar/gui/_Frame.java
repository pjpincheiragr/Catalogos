package aplicacion.herramientas.java.importar.gui;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JToolBar;
import java.awt.Dimension;
import javax.swing.JProgressBar;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	public JTextField _txt_archivo = null;

	public JButton _btn_buscar_archivo = null;

	public JButton _btn_actualizar = null;
	
	public JButton _btn_cancelar = null;

	private JToolBar jToolBar = null;

	private JButton _btn_salir = null;

	private JScrollPane jScrollPane = null;

	private JTable jTable = null;

	private JButton _btn_cargar_archivo = null;

	private JProgressBar jProgressBar = null;

	private JButton _btn_cancelar_operacion = null;

	private JScrollPane jScrollPane1 = null;

	private JTable jTable1 = null;

	private JCheckBox _chk_seleccionar = null;

	private JLabel jLabel1 = null;

	private JLabel jLabel2 = null;

	private JCheckBox _chk_crear = null;

	private JButton _btn_error = null;

	private JComboBox _lst_modo = null;

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
		this.setSize(850, 418);
		this.setResizable(false);
		this.setContentPane(getJContentPane());
		this.setTitle("Importar Desde Archivo de Texto");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(528, 64, 291, 15));
			jLabel2.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2.setText("Preview de Archivo");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(227, 202, 377, 14));
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setText("Configuracion de Columnas Para Importar");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(6, 29, 160, 16));
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setText("Archivo de Texto (Tabulado)");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(get_txt_archivo(), null);
			jContentPane.add(get_btn_buscar_archivo(), null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(get_btn_cargar_archivo(), null);
			jContentPane.add(getJProgressBar(), null);
			jContentPane.add(get_btn_cancelar_operacion(), null);
			jContentPane.add(getJScrollPane1(), null);
			jContentPane.add(get_chk_seleccionar(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(get_chk_crear(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes _txt_archivo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_archivo() {
		if (_txt_archivo == null) {
			_txt_archivo = new JTextField();
			_txt_archivo.setBounds(new Rectangle(170, 28, 335, 19));
			_txt_archivo.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_archivo;
	}
	/**
	 * This method initializes _btn_buscar_archivo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_archivo() {
		if (_btn_buscar_archivo == null) {
			_btn_buscar_archivo = new JButton();
			_btn_buscar_archivo.setBounds(new Rectangle(507, 29, 22, 19));
			_btn_buscar_archivo.setIcon(new ImageIcon(getClass().getResource("/icons/folder-new.png")));
			
		}
		return _btn_buscar_archivo;
	}

	/**
	 * This method initializes _btn_actualizar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_actualizar() {
		if (_btn_actualizar == null) {
			_btn_actualizar = new JButton();
			_btn_actualizar.setIcon(new ImageIcon(getClass().getResource("/icons/filesave.png")));
			_btn_actualizar.setToolTipText("Actualizar");
		}
		return _btn_actualizar;
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
			_btn_cancelar.setToolTipText("Cancelar");
		}
		return _btn_cancelar;
	}





	/**
	 * This method initializes jToolBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar() {
		if (jToolBar == null) {
			jToolBar = new JToolBar();
			jToolBar.setBounds(new Rectangle(4, 2, 837, 21));
			jToolBar.setFloatable(false);
			jToolBar.add(get_btn_cancelar());
			jToolBar.addSeparator();
			jToolBar.add(get_lst_modo());
			jToolBar.add(get_btn_actualizar());
			jToolBar.addSeparator();
			jToolBar.add(get_btn_salir());
			jToolBar.add(get_btn_error());
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
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(10, 84, 813, 106));
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
		this.jScrollPane.setViewportView(this.jTable);
	}





	/**
	 * This method initializes _btn_cargar_archivo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cargar_archivo() {
		if (_btn_cargar_archivo == null) {
			_btn_cargar_archivo = new JButton();
			_btn_cargar_archivo.setBounds(new Rectangle(532, 29, 22, 19));
			_btn_cargar_archivo.setIcon(new ImageIcon(getClass().getResource("/icons/stock_refresh.png")));
		}
		return _btn_cargar_archivo;
	}





	/**
	 * This method initializes jProgressBar	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	public JProgressBar getJProgressBar() {
		if (jProgressBar == null) {
			jProgressBar = new JProgressBar();
			jProgressBar.setBounds(new Rectangle(584, 30, 237, 18));
			jProgressBar.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return jProgressBar;
	}





	/**
	 * This method initializes _btn_cancelar_operacion	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cancelar_operacion() {
		if (_btn_cancelar_operacion == null) {
			_btn_cancelar_operacion = new JButton();
			_btn_cancelar_operacion.setBounds(new Rectangle(557, 29, 22, 19));
			_btn_cancelar_operacion.setIcon(new ImageIcon(getClass().getResource("/icons/stock_calc-cancel.png")));
		}
		return _btn_cancelar_operacion;
	}





	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setBounds(new Rectangle(12, 223, 598, 155));
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

	public void setJTable1(JTable table){
		this.jTable1=table;
		this.jScrollPane1.setViewportView(jTable1);
	}





	/**
	 * This method initializes _chk_seleccionar	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_seleccionar() {
		if (_chk_seleccionar == null) {
			_chk_seleccionar = new JCheckBox();
			_chk_seleccionar.setBounds(new Rectangle(10, 62, 143, 17));
			_chk_seleccionar.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_seleccionar.setText("seleccionar");
		}
		return _chk_seleccionar;
	}





	/**
	 * This method initializes _chk_crear	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_crear() {
		if (_chk_crear == null) {
			_chk_crear = new JCheckBox();
			_chk_crear.setBounds(new Rectangle(625, 224, 147, 19));
			_chk_crear.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_crear.setEnabled(false);
			_chk_crear.setText("crear items nuevos");
		}
		return _chk_crear;
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
	 * This method initializes _lst_modo	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_modo() {
		if (_lst_modo == null) {
			_lst_modo = new JComboBox();
			_lst_modo.setMaximumSize(new Dimension(100, 18));
			_lst_modo.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _lst_modo;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
