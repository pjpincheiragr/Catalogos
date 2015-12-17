package aplicacion.actualizacion.recodificador.gui;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.GridBagLayout;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.Color;
import java.net.*;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.JProgressBar;
import java.awt.Font;
import javax.swing.SwingConstants;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JToolBar jToolBar = null;
	private JButton _btn_guardar = null;
	private JButton _btn_cancelar = null;
	private JButton _btn_salir = null;
	private JPanel jPanel = null;
	private JLabel jLabel = null;
	private JTextField _txt_idarticulo_desde = null;
	private JLabel jLabel1 = null;
	private JTextField _txt_idarticulo_hasta = null;
	private JButton _btn_buscar_desde = null;
	private JButton _btn_buscar_hasta = null;
	private JButton _btn_cargar = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private JCheckBox _chk_marcar = null;
	private JProgressBar PBar = null;
	private JToolBar jToolBar1 = null;
	private JButton _btn_completar = null;
	private JButton _btn_cancelar_operacion = null;
	private JButton _btn_error = null;
	private JButton _btn_buscar = null;
	private JButton _btn_importar = null;
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
		this.setSize(990, 533);
		this.setResizable(false);
		this.setContentPane(getJContentPane());
		this.setTitle("Recodificador de Articulos");
	}
	
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(getJPanel(), null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(get_chk_marcar(), null);
			jContentPane.add(getPBar(), null);
			jContentPane.add(getJToolBar1(), null);
			jContentPane.add(get_btn_cancelar_operacion(), null);
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
			jToolBar.setBounds(new Rectangle(4, 5, 964, 22));
			jToolBar.setFloatable(false);
			jToolBar.add(get_btn_guardar());
			jToolBar.add(get_btn_importar());
			jToolBar.add(get_btn_cancelar());
			jToolBar.add(get_btn_salir());
			jToolBar.addSeparator();
			jToolBar.add(get_btn_error());
		}
		return jToolBar;
	}

	/**
	 * This method initializes _btn_guardar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_guardar() {
		if (_btn_guardar == null) {
			_btn_guardar = new JButton();
			_btn_guardar.setToolTipText("aplicar recodificacion");
			URL resourceURL = getClass().getClassLoader().getResource("icons/filesave.png");
			_btn_guardar.setIcon(new ImageIcon(resourceURL));
			
		}
		return _btn_guardar;
	}

	/**
	 * This method initializes _btn_cancelar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cancelar() {
		if (_btn_cancelar == null) {
			_btn_cancelar = new JButton();
			_btn_cancelar.setToolTipText("Limpiar Aplicacion");
			_btn_cancelar.setBounds(new Rectangle(647, 190, 28, 18));
			URL resourceURL = getClass().getClassLoader().getResource("icons/stock_calc-cancel.png");
			_btn_cancelar.setIcon(new ImageIcon(resourceURL));
			
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
			URL resourceURL = getClass().getClassLoader().getResource("icons/exit.png");
			_btn_salir.setIcon(new ImageIcon(resourceURL));
			_btn_salir.setToolTipText("salir de aplicacion");
		}
		return _btn_salir;
	}
	
	
	
	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(290, 10, 66, 18));
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setText("Hasta:");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(16, 10, 106, 15));
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setText("idArticulo desde:");
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBounds(new Rectangle(6, 40, 957, 66));
			jPanel.setBackground(new Color(204, 204, 204));
			jPanel.add(jLabel, null);
			jPanel.add(get_txt_idarticulo_desde(), null);
			jPanel.add(jLabel1, null);
			jPanel.add(get_txt_idarticulo_hasta(), null);
			jPanel.add(get_btn_buscar_desde(), null);
			jPanel.add(get_btn_buscar_hasta(), null);
			jPanel.add(get_btn_cargar(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes _txt_idarticulo_desde	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idarticulo_desde() {
		if (_txt_idarticulo_desde == null) {
			_txt_idarticulo_desde = new JTextField();
			_txt_idarticulo_desde.setBounds(new Rectangle(124, 10, 125, 18));
			_txt_idarticulo_desde.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_idarticulo_desde;
	}

	/**
	 * This method initializes _txt_idarticulo_hasta	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idarticulo_hasta() {
		if (_txt_idarticulo_hasta == null) {
			_txt_idarticulo_hasta = new JTextField();
			_txt_idarticulo_hasta.setBounds(new Rectangle(359, 11, 145, 17));
			_txt_idarticulo_hasta.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_idarticulo_hasta;
	}

	/**
	 * This method initializes _btn_buscar_desde	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_desde() {
		if (_btn_buscar_desde == null) {
			_btn_buscar_desde = new JButton();
			_btn_buscar_desde.setBounds(new Rectangle(257, 9, 21, 20));
			_btn_buscar_desde.setToolTipText("buscar articulo");
			URL resourceURL = getClass().getClassLoader().getResource("icons/search.png");
			_btn_buscar_desde.setIcon(new ImageIcon(resourceURL));
			
		}
		return _btn_buscar_desde;
	}

	/**
	 * This method initializes _btn_buscar_hasta	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_hasta() {
		if (_btn_buscar_hasta == null) {
			_btn_buscar_hasta = new JButton();
			_btn_buscar_hasta.setBounds(new Rectangle(511, 11, 24, 20));
			_btn_buscar_hasta.setToolTipText("buscar articulo");
			URL resourceURL = getClass().getClassLoader().getResource("icons/search.png");
			_btn_buscar_hasta.setIcon(new ImageIcon(resourceURL));
		}
		return _btn_buscar_hasta;
	}

	/**
	 * This method initializes _btn_cargar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cargar() {
		if (_btn_cargar == null) {
			_btn_cargar = new JButton();
			_btn_cargar.setBounds(new Rectangle(543, 11, 22, 21));
			_btn_cargar.setToolTipText("cargar planilla de datos de articulo");
			URL resourceURL = getClass().getClassLoader().getResource("icons/stock_refresh.png");
			_btn_cargar.setIcon(new ImageIcon(resourceURL));
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
			jScrollPane.setBounds(new Rectangle(19, 188, 938, 279));
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
	 * This method initializes _chk_marcar	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_marcar() {
		if (_chk_marcar == null) {
			_chk_marcar = new JCheckBox();
			_chk_marcar.setBounds(new Rectangle(16, 163, 140, 15));
			_chk_marcar.setText("marcar/desmarcar");
			_chk_marcar.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_marcar.setToolTipText("marcar/desmarcar  los articulos para aplicar recodificacion");
		}
		return _chk_marcar;
	}

	/**
	 * This method initializes PBar	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	public JProgressBar getPBar() {
		if (PBar == null) {
			PBar = new JProgressBar();
			PBar.setBounds(new Rectangle(7, 109, 634, 21));
		}
		return PBar;
	}

	/**
	 * This method initializes jToolBar1	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar1() {
		if (jToolBar1 == null) {
			jToolBar1 = new JToolBar();
			jToolBar1.setBounds(new Rectangle(7, 134, 957, 23));
			jToolBar1.setFloatable(false);
			jToolBar1.add(get_btn_completar());
			jToolBar1.add(get_btn_buscar());
		}
		return jToolBar1;
	}

	/**
	 * This method initializes _btn_completar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_completar() {
		if (_btn_completar == null) {
			_btn_completar = new JButton();
			_btn_completar.setIcon(new ImageIcon(getClass().getResource("/icons/applications-system.png")));
			_btn_completar.setToolTipText("Completar Campo Recodificacion");
			
		}
		return _btn_completar;
	}

	/**
	 * This method initializes _btn_cancelar_operacion	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cancelar_operacion() {
		if (_btn_cancelar_operacion == null) {
			_btn_cancelar_operacion = new JButton();
			_btn_cancelar_operacion.setBounds(new Rectangle(644, 110, 25, 20));
			URL resourceURL = getClass().getClassLoader().getResource("icons/stock_calc-cancel.png");
			_btn_cancelar_operacion.setIcon(new ImageIcon(resourceURL));
			_btn_cancelar_operacion.setToolTipText("Cancelar Operacion en progreso");
		}
		return _btn_cancelar_operacion;
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
	 * This method initializes _btn_buscar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar() {
		if (_btn_buscar == null) {
			_btn_buscar = new JButton();
			_btn_buscar.setIcon(new ImageIcon(getClass().getResource("/icons/stock_hyperlink-internet-search.png")));
			_btn_buscar.setToolTipText("Buscar/Reemplazar");
		}
		return _btn_buscar;
	}

	/**
	 * This method initializes _btn_importar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_importar() {
		if (_btn_importar == null) {
			_btn_importar = new JButton();
			_btn_importar.setIcon(new ImageIcon(getClass().getResource("/icons/bottom.png")));
			_btn_importar.setToolTipText("Importar");
		}
		return _btn_importar;
	}

	

}  //  @jve:decl-index=0:visual-constraint="10,10"
