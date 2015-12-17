package aplicacion.inventario.reporteArticulo.gui;


import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.GridBagLayout;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumnModel;


import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;


import javax.swing.JToolBar;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel jContentPane = null;

	private JPanel jPanel = null;

	private JTable jTableMedios = null;
	private JTable jTableCpte = null;
	private JTable jTableOPG = null;
	private JButton _btn_cancelar = null;

	private JToolBar jToolBar = null;

	private JButton _btn_salir = null;

	private JButton _btn_error = null;

	private JButton _btn_reporte = null;

	private JLabel jLabel = null;

	private JTextField _txt_idarticulo_desde = null;

	private JLabel jLabel3 = null;

	private JTextField _txt_idarticulo_hasta = null;

	private JLabel jLabel4 = null;

	private JTextField _txt_idproveedor = null;

	private JLabel jLabel5 = null;

	private JTextField _txt_linea = null;

	private JTextField _txt_idproveedor_descripcion = null;

	private JButton _btn_buscar_articulo_desde = null;

	private JButton _btn_buscar_articulo_hasta = null;

	private JButton _btn_buscar_idproveedor = null;

	private JComboBox _lst_orden = null;

	private JLabel jLabel1 = null;

	private JLabel jLabel2 = null;

	private JCheckBox _chk_A = null;

	private JCheckBox _chk_B = null;

	private JCheckBox _chk_C = null;

	private JCheckBox _chk_D = null;

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
		this.setSize(535, 259);
		this.setContentPane(getJContentPane());
		this.setTitle("Reporte de Stock");
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
			jContentPane.add(getJPanel(), null);
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
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(7, 135, 100, 16));
			jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel2.setText("categorias incluidas");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(35, 105, 72, 16));
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setText("orden");
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(-1, 40, 111, 18));
			jLabel5.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel5.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel5.setText("Linea");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(35, 10, 75, 18));
			jLabel4.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel4.setText("idProveedor");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(273, 70, 66, 15));
			jLabel3.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3.setText("Hasta");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(3, 70, 107, 17));
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setText("idArticulo Desde");
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBounds(new Rectangle(1, 31, 522, 196));
			jPanel.setBackground(Color.LIGHT_GRAY);
			jPanel.add(jLabel, null);
			jPanel.add(get_txt_idarticulo_desde(), null);
			jPanel.add(jLabel3, null);
			jPanel.add(get_txt_idarticulo_hasta(), null);
			jPanel.add(jLabel4, null);
			jPanel.add(get_txt_idproveedor(), null);
			jPanel.add(jLabel5, null);
			jPanel.add(get_txt_linea(), null);
			jPanel.add(get_txt_idproveedor_descripcion(), null);
			jPanel.add(get_btn_buscar_articulo_desde(), null);
			jPanel.add(get_btn_buscar_articulo_hasta(), null);
			jPanel.add(get_btn_buscar_idproveedor(), null);
			jPanel.add(get_lst_orden(), null);
			jPanel.add(jLabel1, null);
			jPanel.add(jLabel2, null);
			jPanel.add(get_chk_A(), null);
			jPanel.add(get_chk_B(), null);
			jPanel.add(get_chk_C(), null);
			jPanel.add(get_chk_D(), null);
			
		}
		return jPanel;
	}

	
	public void setMedioFocus(){
		this.jTableMedios.requestFocusInWindow();
		this.jTableMedios.changeSelection(0,0,false,false);
		this.jTableMedios.editCellAt(0,0);
		this.jTableMedios.transferFocus();
	}
	

	/**
	 * This method initializes _btn_cancelar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cancelar() {
		if (_btn_cancelar == null) {
			_btn_cancelar = new JButton();
			_btn_cancelar.setToolTipText("Cancelar");
			URL resourceURL = getClass().getClassLoader().getResource("beta/tools/icons/stock_calc-cancel.png");
			_btn_cancelar.setIcon(new ImageIcon(getClass().getResource("/icons/stock_calc-cancel.png")));
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
			jToolBar.setBounds(new Rectangle(6, 4, 516, 21));
			jToolBar.add(get_btn_cancelar());
			jToolBar.add(get_btn_reporte());
			jToolBar.add(get_btn_salir());
			jToolBar.add(get_btn_error());
		}
		return jToolBar;
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
	 * This method initializes _btn_reporte	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_reporte() {
		if (_btn_reporte == null) {
			_btn_reporte = new JButton();
			_btn_reporte.setIcon(new ImageIcon(getClass().getResource("/icons/document-print.png")));
			_btn_reporte.setToolTipText("Reporte");
		}
		return _btn_reporte;
	}

	/**
	 * This method initializes _txt_idarticulo_desde	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idarticulo_desde() {
		if (_txt_idarticulo_desde == null) {
			_txt_idarticulo_desde = new JTextField();
			_txt_idarticulo_desde.setBounds(new Rectangle(115, 70, 115, 17));
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
			_txt_idarticulo_hasta.setBounds(new Rectangle(343, 70, 115, 17));
			_txt_idarticulo_hasta.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_idarticulo_hasta;
	}

	/**
	 * This method initializes _txt_idproveedor	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idproveedor() {
		if (_txt_idproveedor == null) {
			_txt_idproveedor = new JTextField();
			_txt_idproveedor.setBounds(new Rectangle(115, 10, 115, 17));
			_txt_idproveedor.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_idproveedor;
	}

	/**
	 * This method initializes _txt_linea	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_linea() {
		if (_txt_linea == null) {
			_txt_linea = new JTextField();
			_txt_linea.setBounds(new Rectangle(115, 40, 203, 17));
			_txt_linea.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_linea;
	}

	/**
	 * This method initializes _txt_idproveedor_descripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idproveedor_descripcion() {
		if (_txt_idproveedor_descripcion == null) {
			_txt_idproveedor_descripcion = new JTextField();
			_txt_idproveedor_descripcion.setBounds(new Rectangle(258, 10, 226, 18));
			_txt_idproveedor_descripcion.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_idproveedor_descripcion.setEditable(false);
		}
		return _txt_idproveedor_descripcion;
	}

	/**
	 * This method initializes _btn_buscar_articulo_desde	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_articulo_desde() {
		if (_btn_buscar_articulo_desde == null) {
			_btn_buscar_articulo_desde = new JButton();
			_btn_buscar_articulo_desde.setLocation(new Point(233, 70));
			_btn_buscar_articulo_desde.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-searchtool.png")));
			_btn_buscar_articulo_desde.setSize(new Dimension(20, 19));
		}
		return _btn_buscar_articulo_desde;
	}

	/**
	 * This method initializes _btn_buscar_articulo_hasta	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_articulo_hasta() {
		if (_btn_buscar_articulo_hasta == null) {
			_btn_buscar_articulo_hasta = new JButton();
			_btn_buscar_articulo_hasta.setLocation(new Point(460, 70));
			_btn_buscar_articulo_hasta.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-searchtool.png")));
			_btn_buscar_articulo_hasta.setSize(new Dimension(20, 19));
		}
		return _btn_buscar_articulo_hasta;
	}

	/**
	 * This method initializes _btn_buscar_idproveedor	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_idproveedor() {
		if (_btn_buscar_idproveedor == null) {
			_btn_buscar_idproveedor = new JButton();
			_btn_buscar_idproveedor.setLocation(new Point(233, 10));
			_btn_buscar_idproveedor.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-searchtool.png")));
			_btn_buscar_idproveedor.setSize(new Dimension(20, 19));
		}
		return _btn_buscar_idproveedor;
	}

	/**
	 * This method initializes _lst_orden	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_orden() {
		if (_lst_orden == null) {
			_lst_orden = new JComboBox();
			_lst_orden.setBounds(new Rectangle(115, 105, 115, 16));
		}
		return _lst_orden;
	}

	/**
	 * This method initializes _chk_A	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_A() {
		if (_chk_A == null) {
			_chk_A = new JCheckBox();
			_chk_A.setBounds(new Rectangle(115, 135, 40, 21));
			_chk_A.setText("A");
		}
		return _chk_A;
	}

	/**
	 * This method initializes _chk_B	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_B() {
		if (_chk_B == null) {
			_chk_B = new JCheckBox();
			_chk_B.setBounds(new Rectangle(165, 135, 40, 21));
			_chk_B.setText("B");
		}
		return _chk_B;
	}

	/**
	 * This method initializes _chk_C	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_C() {
		if (_chk_C == null) {
			_chk_C = new JCheckBox();
			_chk_C.setBounds(new Rectangle(215, 135, 40, 21));
			_chk_C.setText("C");
		}
		return _chk_C;
	}

	/**
	 * This method initializes _chk_D	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_D() {
		if (_chk_D == null) {
			_chk_D = new JCheckBox();
			_chk_D.setBounds(new Rectangle(265, 135, 40, 21));
			_chk_D.setText("D");
		}
		return _chk_D;
	}
	
		
}  //  @jve:decl-index=0:visual-constraint="10,10"
