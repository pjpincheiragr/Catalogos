package aplicacion.sistema.indexer.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import java.awt.Rectangle;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JProgressBar;
import javax.swing.JCheckBox;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JToolBar jToolBar = null;
	private JButton _btn_salir = null;
	
	private JLabel jLabel = null;
	private JButton _btn_cancelar = null;
	private JTextField _txt_archivo = null;
	private JButton _btn_guardar = null;
	private JButton _btn_indexar = null;
	private JButton _btn_eliminar = null;
	private JButton _btn_quitar_indice = null;
	private JProgressBar jProgressBar = null;
	private JButton _btn_buscar_archivo = null;
	private JButton _btn_cargar = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private JButton _btn_quitar_todos = null;
	private JCheckBox _chk_seleccionar = null;
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
		this.setSize(753, 383);
		this.setContentPane(getJContentPane());
		this.setTitle("Indexador de Documentos");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(9, 47, 76, 15));
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setText("Directorio");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(get_txt_archivo(), null);
			jContentPane.add(getJProgressBar(), null);
			jContentPane.add(get_btn_buscar_archivo(), null);
			jContentPane.add(get_btn_cargar(), null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(get_chk_seleccionar(), null);
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
			jToolBar.setBounds(new Rectangle(0, 0, 743, 24));
			jToolBar.setFloatable(false);
			jToolBar.add(get_btn_cancelar());
			jToolBar.add(get_btn_guardar());
			jToolBar.add(get_btn_eliminar());
			jToolBar.add(get_btn_indexar());
			jToolBar.add(get_btn_quitar_indice());
			jToolBar.add(get_btn_quitar_todos());
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
	 * This method initializes _txt_archivo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_archivo() {
		if (_txt_archivo == null) {
			_txt_archivo = new JTextField();
			_txt_archivo.setBounds(new Rectangle(97, 45, 158, 16));
		}
		return _txt_archivo;
	}

	/**
	 * This method initializes _btn_guardar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton get_btn_guardar() {
		if (_btn_guardar == null) {
			_btn_guardar = new JButton();
			_btn_guardar.setIcon(new ImageIcon(getClass().getResource("/icons/document-save.png")));
			_btn_guardar.setToolTipText("Guardar Configuracion");
		}
		return _btn_guardar;
	}

	/**
	 * This method initializes _btn_indexar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_indexar() {
		if (_btn_indexar == null) {
			_btn_indexar = new JButton();
			_btn_indexar.setIcon(new ImageIcon(getClass().getResource("/icons/edit-redo.png")));
			_btn_indexar.setToolTipText("Indexar Archivo en Base de Datos");
		}
		return _btn_indexar;
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
			_btn_eliminar.setToolTipText("Eliminar Configuracion");
		}
		return _btn_eliminar;
	}

	/**
	 * This method initializes _btn_quitar_indice	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_quitar_indice() {
		if (_btn_quitar_indice == null) {
			_btn_quitar_indice = new JButton();
			_btn_quitar_indice.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-undo-ltr.png")));
			_btn_quitar_indice.setToolTipText("Quitar Indice de la Base de Datos");
		}
		return _btn_quitar_indice;
	}

	/**
	 * This method initializes jProgressBar	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	public JProgressBar getJProgressBar() {
		if (jProgressBar == null) {
			jProgressBar = new JProgressBar();
			jProgressBar.setBounds(new Rectangle(3, 25, 738, 13));
		}
		return jProgressBar;
	}

	/**
	 * This method initializes _btn_buscar_archivo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_archivo() {
		if (_btn_buscar_archivo == null) {
			_btn_buscar_archivo = new JButton();
			_btn_buscar_archivo.setBounds(new Rectangle(258, 44, 21, 20));
			_btn_buscar_archivo.setIcon(new ImageIcon(getClass().getResource("/icons/document-open.png")));
		}
		return _btn_buscar_archivo;
	}

	/**
	 * This method initializes _btn_cargar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cargar() {
		if (_btn_cargar == null) {
			_btn_cargar = new JButton();
			_btn_cargar.setBounds(new Rectangle(280, 44, 21, 20));
			_btn_cargar.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-refresh.png")));
		}
		return _btn_cargar;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(9, 88, 724, 251));
			jScrollPane.setViewportView(getJTable1());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTable1	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getJTable1() {
		if (jTable == null) {
			jTable = new JTable();
		}
		return jTable;
	}

	public void setJTable1(JTable table){
		this.jTable=table;
		this.jScrollPane.setViewportView(jTable);
	}

	/**
	 * This method initializes _btn_quitar_todos	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_quitar_todos() {
		if (_btn_quitar_todos == null) {
			_btn_quitar_todos = new JButton();
			_btn_quitar_todos.setIcon(new ImageIcon(getClass().getResource("/icons/user-trash.png")));
			_btn_quitar_todos.setToolTipText("Eliminar Todos los Catalogos PDF");
		}
		return _btn_quitar_todos;
	}

	/**
	 * This method initializes _chk_seleccionar	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_seleccionar() {
		if (_chk_seleccionar == null) {
			_chk_seleccionar = new JCheckBox();
			_chk_seleccionar.setBounds(new Rectangle(9, 67, 122, 13));
			_chk_seleccionar.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_seleccionar.setText("Seleccionar");
		}
		return _chk_seleccionar;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
