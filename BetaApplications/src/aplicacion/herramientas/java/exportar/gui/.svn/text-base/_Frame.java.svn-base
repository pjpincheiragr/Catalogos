package aplicacion.herramientas.java.exportar.gui;

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

	private JTable jTable = null;

	private JProgressBar jProgressBar = null;

	private JButton _btn_cancelar_operacion = null;

	private JScrollPane jScrollPane1 = null;

	private JTable jTable1 = null;

	private JLabel jLabel1 = null;

	private JCheckBox _chk_seleccionar = null;

	private JButton _btn_error = null;

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
		this.setSize(674, 315);
		this.setResizable(false);
		this.setContentPane(getJContentPane());
		this.setTitle("Exportar a Archivo de Texto Tabulado");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(224, 64, 377, 14));
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setText("Configuracion de Columnas Para Exportar");
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
			jContentPane.add(getJProgressBar(), null);
			jContentPane.add(get_btn_cancelar_operacion(), null);
			jContentPane.add(getJScrollPane1(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(get_chk_seleccionar(), null);
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
			_btn_buscar_archivo.setToolTipText("Buscar Carpeta Destino");
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
			_btn_actualizar.setToolTipText("Guardar Archivo");
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
			jToolBar.setBounds(new Rectangle(4, 2, 697, 21));
			jToolBar.setFloatable(false);
			jToolBar.add(get_btn_cancelar());
			jToolBar.add(get_btn_actualizar());
			jToolBar.add(get_btn_salir());
			jToolBar.addSeparator();
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
	 * This method initializes jProgressBar	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	public JProgressBar getJProgressBar() {
		if (jProgressBar == null) {
			jProgressBar = new JProgressBar();
			jProgressBar.setBounds(new Rectangle(560, 30, 91, 18));
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
			_btn_cancelar_operacion.setBounds(new Rectangle(533, 29, 22, 19));
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
			jScrollPane1.setBounds(new Rectangle(12, 85, 598, 173));
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
			_chk_seleccionar.setBounds(new Rectangle(13, 63, 157, 15));
			_chk_seleccionar.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_seleccionar.setText("Seleccionar");
		}
		return _chk_seleccionar;
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

}  //  @jve:decl-index=0:visual-constraint="-132,-7"
