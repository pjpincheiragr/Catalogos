package aplicacion.gestion.tablero.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.text.*;
import java.text.*;


import javax.swing.JFormattedTextField;
import java.awt.Rectangle;
import java.awt.GridBagLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.Point;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.JProgressBar;
import java.awt.event.KeyEvent;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import java.awt.TextField;
import java.awt.Button;
import javax.swing.JSplitPane;
import javax.swing.JSlider;
import java.awt.GridBagConstraints;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton _btn_cargar = null;
	private JButton _btn_calendario = null;
	private JButton _btn_salir = null;
	private JToolBar jToolBar = null;
	private JProgressBar jProgressBar = null;
	private JButton _btn_error = null;
	private JTabbedPane jTabbedPane = null;
	private JTable jTable = null;
	private JScrollPane jScrollPane1 = null;
	private JTextArea _txt_formula = null;
	private JToolBar jToolBar1 = null;
	private JButton _btn_guardar = null;
	private JTextField _txt_foreground = null;
	private JButton _btn_elegir_foreground = null;
	private JButton _btn_elegir_background = null;
	private JTextField _txt_background = null;
	private JTextField _txt_fecha_desde = null;
	private JTextField _txt_fecha_hasta = null;
	private JTextField _txt_dias_restantes = null;
	private JTextField _txt_dias_trabajados = null;
	private JButton _btn_buscar_fecha_desde = null;
	private JButton _btn_buscar_fecha_hasta = null;
	private JPanel jPanel2 = null;
	private JSplitPane jSplitPane = null;
	private JScrollPane jScrollPane = null;
	private JTextArea _txt_detalle = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JLabel jLabel4 = null;
	private JLabel jLabel5 = null;
	private JTabbedPane jTabbedPane1 = null;
	private JSlider jSlider = null;
	private JLabel jLabel6 = null;
	private JTextField _txt_nombre = null;
	private JScrollPane jScrollPane2 = null;
	private JTextArea _txt_grafica = null;
	private JLabel jLabel8 = null;
	private JLabel jLabel9 = null;
	private JTextField _txt_fila = null;
	private JTextField _txt_columna = null;
	private JButton _btn_copiar = null;
	private JButton _btn_pegar = null;
	private JButton _btn_nuevo = null;
	private JButton _btn_eliminar = null;
	private JButton _btn_importar = null;
	private JButton _btn_exportar = null;
	private JButton _btn_eliminar_celda = null;
	private JComboBox _lst_mes = null;
	private JComboBox _lst_anio = null;
	private JTabbedPane jTabbedPane2 = null;
	private JPanel jPanel = null;
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
		this.setContentPane(getJContentPane());
		this.setTitle("Tablero de Control");
		this.setBounds(new Rectangle(0, 0, 1020, 710));
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(431, 51, 71, 18));
			jLabel5.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel5.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel5.setText("Desde");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(639, 52, 70, 16));
			jLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel4.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel4.setText("Hasta");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(getJProgressBar(), null);
			jContentPane.add(get_txt_fecha_desde(), null);
			jContentPane.add(get_btn_buscar_fecha_desde(), null);
			jContentPane.add(get_txt_fecha_hasta(), null);
			jContentPane.add(get_btn_buscar_fecha_hasta(), null);
			jContentPane.add(getJSplitPane(), null);
			jContentPane.add(get_txt_dias_restantes(), null);
			jContentPane.add(get_txt_dias_trabajados(), null);
			jContentPane.add(get_btn_calendario(), null);
			jContentPane.add(get_lst_mes(), null);
			jContentPane.add(get_lst_anio(), null);
		}
		return jContentPane;
	}



	/**
	 * This method initializes _btn_cargar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cargar() {
		if (_btn_cargar == null) {
			_btn_cargar = new JButton();
			_btn_cargar.setIcon(new ImageIcon(getClass().getResource("/icons/stock_refresh.png")));
			_btn_cargar.setToolTipText("Cargar");
		}
		return _btn_cargar;
	}

	
	/**
	 * This method initializes _btn_calendario	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_calendario() {
		if (_btn_calendario == null) {
			_btn_calendario = new JButton();
			_btn_calendario.setIcon(new ImageIcon(getClass().getResource("/icons/calendar-16.gif")));
			_btn_calendario.setToolTipText("Modificar Calendario");
			_btn_calendario.setBounds(new Rectangle(624, 55, 18, 18));
		}
		return _btn_calendario;
	}
	/**
	 * This method initializes _btn_salir	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_salir() {
		if (_btn_salir == null) {
			_btn_salir = new JButton();
			_btn_salir.setToolTipText("Salir");
			_btn_salir.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-logout.png")));
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
			jToolBar.setFloatable(false);
			jToolBar.setBounds(new Rectangle(1, 1, 1001, 24));
			jToolBar.add(get_btn_cargar());
			jToolBar.add(get_btn_nuevo());
			jToolBar.add(get_btn_eliminar());
			jToolBar.add(get_btn_importar());
			jToolBar.add(get_btn_exportar());
			jToolBar.add(get_btn_salir());
			jToolBar.add(get_btn_error());
		}
		return jToolBar;
	}

	/**
	 * This method initializes jProgressBar	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	public JProgressBar getJProgressBar() {
		if (jProgressBar == null) {
			jProgressBar = new JProgressBar();
			jProgressBar.setBounds(new Rectangle(5, 33, 993, 14));
			jProgressBar.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return jProgressBar;
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
	 * This method initializes jTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	public JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
		}
		return jTabbedPane;
	}

	

	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(get_txt_formula());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes jTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea get_txt_formula() {
		if (_txt_formula == null) {
			_txt_formula = new JTextArea();
			_txt_formula.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_formula.setWrapStyleWord(true);
			_txt_formula.setLineWrap(true);
		}
		return _txt_formula;
	}

	/**
	 * This method initializes jToolBar1	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar1() {
		if (jToolBar1 == null) {
			jToolBar1 = new JToolBar();
			jToolBar1.setFloatable(false);
			jToolBar1.setBounds(new Rectangle(1, 1, 968, 24));
			jToolBar1.add(get_btn_guardar());
			jToolBar1.add(get_btn_copiar());
			jToolBar1.add(get_btn_pegar());
			jToolBar1.add(get_btn_eliminar_celda());
		}
		return jToolBar1;
	}

	/**
	 * This method initializes _btn_guardar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_guardar() {
		if (_btn_guardar == null) {
			_btn_guardar = new JButton();
			_btn_guardar.setIcon(new ImageIcon(getClass().getResource("/icons/document-save.png")));
		}
		return _btn_guardar;
	}

	/**
	 * This method initializes _txt_foreground	
	 * 	
	 * @return java.awt.TextField	
	 */
	public JTextField get_txt_foreground() {
		if (_txt_foreground == null) {
			_txt_foreground = new JTextField();
			_txt_foreground.setHorizontalAlignment(JTextField.RIGHT);
			_txt_foreground.setBounds(new Rectangle(140, 12, 104, 18));
			_txt_foreground.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_foreground;
	}

	/**
	 * This method initializes _btn_elegir_foreground	
	 * 	
	 * @return java.awt.Button	
	 */
	public JButton get_btn_elegir_foreground() {
		if (_btn_elegir_foreground == null) {
			_btn_elegir_foreground = new JButton();
			
			_btn_elegir_foreground.setIcon(new ImageIcon(getClass().getResource("/icons/text_bold.png")));
			_btn_elegir_foreground.setBounds(new Rectangle(248, 10, 18, 18));
		}
		return _btn_elegir_foreground;
	}

	/**
	 * This method initializes _btn_elegir_background	
	 * 	
	 * @return java.awt.Button	
	 */
	public JButton get_btn_elegir_background() {
		if (_btn_elegir_background == null) {
			_btn_elegir_background = new JButton();
			_btn_elegir_background.setIcon(new ImageIcon(getClass().getResource("/icons/text_under.png")));
			_btn_elegir_background.setBounds(new Rectangle(248, 34, 18, 18));
		}
		return _btn_elegir_background;
	}

	/**
	 * This method initializes _txt_background	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_background() {
		if (_txt_background == null) {
			_txt_background = new JTextField();
			_txt_background.setHorizontalAlignment(JTextField.RIGHT);
			_txt_background.setBounds(new Rectangle(141, 34, 102, 18));
			_txt_background.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_background;
	}

	/**
	 * This method initializes _txt_fecha_desde	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_fecha_desde() {
		if (_txt_fecha_desde == null) {
			_txt_fecha_desde = new JTextField();
			_txt_fecha_desde.setBounds(new Rectangle(340, 57, 100, 18));
		}
		return _txt_fecha_desde;
	}

	/**
	 * This method initializes _txt_fecha_hasta	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_fecha_hasta() {
		if (_txt_fecha_hasta == null) {
			_txt_fecha_hasta = new JTextField();
			_txt_fecha_hasta.setBounds(new Rectangle(465, 56, 100, 18));
		}
		return _txt_fecha_hasta;
	}
	
	/**
	 * This method initializes _txt_fecha_hasta	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_dias_restantes() {
		if (_txt_dias_restantes == null) {
			_txt_dias_restantes = new JTextField();
			_txt_dias_restantes.setBounds(new Rectangle(645, 56, 30, 18));
			_txt_dias_restantes.setToolTipText("Dias Restantes");
		}
		return _txt_dias_restantes;
	}

	/**
	 * This method initializes _txt_fecha_hasta	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_dias_trabajados() {
		if (_txt_dias_trabajados == null) {
			_txt_dias_trabajados = new JTextField();
			_txt_dias_trabajados.setBounds(new Rectangle(592, 56, 30, 18));
			_txt_dias_trabajados.setToolTipText("Dias Trabajados");
		}
		return _txt_dias_trabajados;
	}
	
	/**
	 * This method initializes _btn_buscar_fecha_desde	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_fecha_desde() {
		if (_btn_buscar_fecha_desde == null) {
			_btn_buscar_fecha_desde = new JButton();
			_btn_buscar_fecha_desde.setIcon(new ImageIcon(getClass().getResource("/icons/calendar-16.gif")));
			_btn_buscar_fecha_desde.setBounds(new Rectangle(442, 55, 18, 18));
		}
		return _btn_buscar_fecha_desde;
	}

	/**
	 * This method initializes _btn_buscar_fecha_hasta	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_fecha_hasta() {
		if (_btn_buscar_fecha_hasta == null) {
			_btn_buscar_fecha_hasta = new JButton();
			_btn_buscar_fecha_hasta.setIcon(new ImageIcon(getClass().getResource("/icons/calendar-16.gif")));
			_btn_buscar_fecha_hasta.setBounds(new Rectangle(569, 55, 18, 18));
		}
		return _btn_buscar_fecha_hasta;
	}

	/**
	 * This method initializes jPanel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jLabel9 = new JLabel();
			jLabel9.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel9.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel9.setBounds(new Rectangle(208, 78, 56, 20));
			jLabel9.setText("Columna");
			jLabel8 = new JLabel();
			jLabel8.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel8.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel8.setBounds(new Rectangle(140, 79, 28, 18));
			jLabel8.setText("Fila");
			jLabel6 = new JLabel();
			jLabel6.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel6.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel6.setBounds(new Rectangle(26, 57, 103, 16));
			jLabel6.setText("Nombre");
			jLabel3 = new JLabel();
			jLabel3.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3.setBounds(new Rectangle(24, 37, 105, 17));
			jLabel3.setText("Color Fondo");
			jLabel2 = new JLabel();
			jLabel2.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2.setBounds(new Rectangle(24, 13, 104, 17));
			jLabel2.setText("Color Fuente");
			jPanel2 = new JPanel();
			jPanel2.setLayout(null);
			jPanel2.setBackground(new Color(204, 204, 204));
			jPanel2.add(getJToolBar1(), null);
			jPanel2.add(getJTabbedPane2(), null);
		}
		return jPanel2;
	}

	/**
	 * This method initializes jSplitPane	
	 * 	
	 * @return javax.swing.JSplitPane	
	 */
	private JSplitPane getJSplitPane() {
		if (jSplitPane == null) {
			jSplitPane = new JSplitPane();
			jSplitPane.setBounds(new Rectangle(8, 83, 981, 584));
			jSplitPane.setDividerLocation(700);
			jSplitPane.setOneTouchExpandable(true);
			jSplitPane.setTopComponent(getJTabbedPane());
			jSplitPane.setBottomComponent(getJTabbedPane1());
			jSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		}
		return jSplitPane;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(get_txt_detalle());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes _txt_detalle	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea get_txt_detalle() {
		if (_txt_detalle == null) {
			_txt_detalle = new JTextArea();
			_txt_detalle.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_detalle.setWrapStyleWord(true);
			_txt_detalle.setLineWrap(true);
		}
		return _txt_detalle;
	}

	/**
	 * This method initializes jTabbedPane1	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	public JTabbedPane getJTabbedPane1() {
		if (jTabbedPane1 == null) {
			jTabbedPane1 = new JTabbedPane();
			
			jTabbedPane1.addTab("Configuracion", null, getJPanel2(), null);
		}
		return jTabbedPane1;
	}

	/**
	 * This method initializes jSlider	
	 * 	
	 * @return javax.swing.JSlider	
	 */
	private JSlider getJSlider() {
		if (jSlider == null) {
			jSlider = new JSlider();
		}
		return jSlider;
	}

	/**
	 * This method initializes _txt_nombre	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_nombre() {
		if (_txt_nombre == null) {
			_txt_nombre = new JTextField();
			_txt_nombre.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_nombre.setBounds(new Rectangle(140, 56, 103, 17));
		}
		return _txt_nombre;
	}

	/**
	 * This method initializes jScrollPane2	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane2() {
		if (jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setViewportView(get_txt_grafica());
		}
		return jScrollPane2;
	}

	/**
	 * This method initializes _txt_grafica	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea get_txt_grafica() {
		if (_txt_grafica == null) {
			_txt_grafica = new JTextArea();
			_txt_grafica.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_grafica.setLineWrap(true);
			_txt_grafica.setWrapStyleWord(true);
		}
		return _txt_grafica;
	}

	/**
	 * This method initializes _txt_fila	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_fila() {
		if (_txt_fila == null) {
			_txt_fila = new JTextField();
			_txt_fila.setHorizontalAlignment(JTextField.RIGHT);
			_txt_fila.setEditable(false);
			_txt_fila.setBounds(new Rectangle(170, 80, 31, 18));
			_txt_fila.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_fila;
	}

	/**
	 * This method initializes _txt_columna	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_columna() {
		if (_txt_columna == null) {
			_txt_columna = new JTextField();
			_txt_columna.setHorizontalAlignment(JTextField.RIGHT);
			_txt_columna.setEditable(false);
			_txt_columna.setBounds(new Rectangle(268, 77, 31, 21));
			_txt_columna.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_columna;
	}

	/**
	 * This method initializes _btn_copiar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_copiar() {
		if (_btn_copiar == null) {
			_btn_copiar = new JButton();
			_btn_copiar.setIcon(new ImageIcon(getClass().getResource("/icons/edit-copy.png")));
			_btn_copiar.setToolTipText("Copiar");
		}
		return _btn_copiar;
	}

	/**
	 * This method initializes _btn_pegar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_pegar() {
		if (_btn_pegar == null) {
			_btn_pegar = new JButton();
			_btn_pegar.setIcon(new ImageIcon(getClass().getResource("/icons/editpaste.png")));
		}
		return _btn_pegar;
	}

	/**
	 * This method initializes _btn_nuevo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_nuevo() {
		if (_btn_nuevo == null) {
			_btn_nuevo = new JButton();
			_btn_nuevo.setIcon(new ImageIcon(getClass().getResource("/icons/filenew.png")));
			_btn_nuevo.setToolTipText("Crear Nuevo Tablero");
		}
		return _btn_nuevo;
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
			_btn_eliminar.setToolTipText("Eliminar Tablero");
		}
		return _btn_eliminar;
	}

	/**
	 * This method initializes _btn_importar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_importar() {
		if (_btn_importar == null) {
			_btn_importar = new JButton();
			_btn_importar.setIcon(new ImageIcon(getClass().getResource("/icons/down.png")));
			_btn_importar.setToolTipText("Importar Configuracion");
		}
		return _btn_importar;
	}

	/**
	 * This method initializes _btn_exportar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_exportar() {
		if (_btn_exportar == null) {
			_btn_exportar = new JButton();
			_btn_exportar.setIcon(new ImageIcon(getClass().getResource("/icons/go-up.png")));
			_btn_exportar.setToolTipText("Exportar Configuracion");
		}
		return _btn_exportar;
	}

	/**
	 * This method initializes _btn_eliminar_celda	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_eliminar_celda() {
		if (_btn_eliminar_celda == null) {
			_btn_eliminar_celda = new JButton();
			_btn_eliminar_celda.setIcon(new ImageIcon(getClass().getResource("/icons/edit-delete.png")));
			_btn_eliminar_celda.setToolTipText("Eliminar Configuracion de Celda Seleccionada");
		}
		return _btn_eliminar_celda;
	}

	/**
	 * This method initializes _lst_mes	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_mes() {
		if (_lst_mes == null) {
			_lst_mes = new JComboBox();
			_lst_mes.setBounds(new Rectangle(697, 55, 129, 18));
			_lst_mes.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _lst_mes;
	}

	/**
	 * This method initializes _lst_anio	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_anio() {
		if (_lst_anio == null) {
			_lst_anio = new JComboBox();
			_lst_anio.setBounds(new Rectangle(839, 55, 112, 18));
			_lst_anio.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _lst_anio;
	}

	/**
	 * This method initializes jTabbedPane2	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJTabbedPane2() {
		if (jTabbedPane2 == null) {
			jTabbedPane2 = new JTabbedPane();
			jTabbedPane2.setBounds(new Rectangle(3, 28, 947, 359));
			jTabbedPane2.addTab("Formula", null, getJScrollPane1(), null);
			jTabbedPane2.addTab("Detalle", null, getJScrollPane(), null);
			jTabbedPane2.addTab("Grafico", null, getJScrollPane2(), null);
			jTabbedPane2.addTab("Propiedades", null, getJPanel(), null);
		}
		return jTabbedPane2;
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
			jPanel.add(jLabel2, null);
			jPanel.add(get_txt_foreground(), null);
			jPanel.add(get_btn_elegir_foreground(), null);
			jPanel.add(jLabel3, null);
			jPanel.add(get_txt_background(), null);
			jPanel.add(get_btn_elegir_background(), null);
			jPanel.add(jLabel6, null);
			jPanel.add(get_txt_nombre(), null);
			jPanel.add(jLabel8, null);
			jPanel.add(get_txt_fila(), null);
			jPanel.add(jLabel9, null);
			jPanel.add(get_txt_columna(), null);
		}
		return jPanel;
	}
	

	
}  //  @jve:decl-index=0:visual-constraint="10,10"
