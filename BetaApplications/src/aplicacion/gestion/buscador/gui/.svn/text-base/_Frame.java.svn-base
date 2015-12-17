package aplicacion.gestion.buscador.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.Point;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumnModel;


import java.net.URL;
import java.util.*;

import javax.swing.JComboBox;
import java.awt.GridBagLayout;
import java.awt.Color;
import javax.swing.JCheckBox;

import javax.swing.JToolBar;
import javax.swing.SwingConstants;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;

	private JTextField _txt_idcuenta = null;

	private JTextField _txt_cuenta_descripcion = null;

	private JTextField _txt_fecha_desde = null;

	private JTextField _txt_idcomprobante = null;

	private JScrollPane jScrollPane = null;

	private JTable jTable = null;
	private JTextField _txt_idarticulo = null;

	private JLabel jLabel5 = null;

	private JPanel jPanel = null;

	private JComboBox _lst_modo = null;

	private JLabel jLabel6 = null;

	private JComboBox _lst_tc = null;

	private JButton _btn_buscar_fecha_desde = null;

	private JTextField _txt_articulo_descripcion = null;

	private JCheckBox _chk_seleccionar = null;
	private JButton _btn_imprimir = null;
	private JToolBar jToolBar = null;
	private JTextField _txt_fecha_hasta = null;
	private JButton _btn_cancelar = null;
	private JButton _btn_cargar = null;
	private JButton _btn_salir = null;
	private JLabel jLabel1 = null;
	private JButton _btn_buscar_fecha_hasta = null;
	private JButton _btn_buscar_articulo = null;
	private JButton _btn_buscar_cuenta = null;
	private JButton _btn_error = null;

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
		this.setSize(1000, 700);
		this.setContentPane(getJContentPane());
		this.setTitle("Buscador de Comprobantes");
		this.setResizable(false);
		
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel6 = new JLabel();
			jLabel6.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel6.setBounds(new Rectangle(6, 5, 84, 19));
			jLabel6.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel6.setText("Modo");
			jLabel5 = new JLabel();
			jLabel5.setText("IDArticulo");
			jLabel5.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel5.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel5.setBounds(new Rectangle(3, 120, 86, 20));
			jLabel3 = new JLabel();
			jLabel3.setText("Fecha");
			jLabel3.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3.setBounds(new Rectangle(4, 61, 85, 21));
			jLabel2 = new JLabel();
			jLabel2.setText("idComprobante");
			jLabel2.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2.setBounds(new Rectangle(3, 91, 86, 22));
			jLabel = new JLabel();
			jLabel.setText("Cuenta");
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setBounds(new Rectangle(4, 32, 86, 22));
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(getJPanel(), null);
			jContentPane.add(getJToolBar(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes _txt_idcuenta	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idcuenta() {
		if (_txt_idcuenta == null) {
			_txt_idcuenta = new JTextField();
			_txt_idcuenta.setBounds(new Rectangle(96, 33, 106, 18));
			_txt_idcuenta.setText("");
			_txt_idcuenta.setToolTipText("Codigo de Cuenta");
			_txt_idcuenta.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_idcuenta;
	}
	
	/**
	 * This method initializes _txt_cuenta_descripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_cuenta_descripcion() {
		if (_txt_cuenta_descripcion == null) {
			_txt_cuenta_descripcion = new JTextField();
			_txt_cuenta_descripcion.setBounds(new Rectangle(229, 33, 271, 19));
			_txt_cuenta_descripcion.setText("");
			_txt_cuenta_descripcion.setToolTipText("Descripcion de cuenta");
			_txt_cuenta_descripcion.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_cuenta_descripcion;
	}

	public JTextField get_txt_fecha_desde() {
		
			if (_txt_fecha_desde == null) {
				_txt_fecha_desde = new JTextField();

				_txt_fecha_desde.setToolTipText("Fecha Desde");
				_txt_fecha_desde.setFont(new Font("Dialog", Font.PLAIN, 10));
				_txt_fecha_desde.setBounds(new Rectangle(95, 62, 107, 18));
			}
			return _txt_fecha_desde;
		
	}

	/**
	 * This method initializes _txt_idcomprobante	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idcomprobante() {
		if (_txt_idcomprobante == null) {
			_txt_idcomprobante = new JTextField();
			_txt_idcomprobante.setBounds(new Rectangle(206, 93, 142, 18));
			_txt_idcomprobante.setToolTipText("idcomprobante");
			_txt_idcomprobante.setFont(new Font("Dialog", Font.PLAIN, 10));
			
		}
		return _txt_idcomprobante;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(7, 207, 980, 184));
			jScrollPane.setViewportView(getJTable());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTable() {
		if (jTable == null) {
			jTable = new JTable();
		}
		return jTable;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(234, 64, 58, 16));
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setText("Hasta");
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBounds(new Rectangle(3, 26, 982, 174));
			jPanel.setBackground(Color.lightGray);
			jPanel.add(get_txt_idcuenta(), null);
			jPanel.add(get_txt_cuenta_descripcion(), null);
			jPanel.add(get_txt_fecha_desde(), null);
			jPanel.add(get_txt_idcomprobante(), null);
			jPanel.add(get_txt_idarticulo(), null);
			jPanel.add(jLabel5, null);
			jPanel.add(jLabel2, null);
			jPanel.add(jLabel3, null);
			jPanel.add(jLabel, null);
			jPanel.add(get_lst_tc(), null);
			jPanel.add(get_btn_buscar_fecha_desde(), null);
			jPanel.add(get_txt_articulo_descripcion(), null);
			jPanel.add(get_chk_seleccionar(), null);
			jPanel.add(jLabel6, null);
			jPanel.add(get_lst_modo(), null);
			jPanel.add(get_txt_fecha_hasta(), null);
			jPanel.add(jLabel1, null);
			jPanel.add(get_btn_buscar_fecha_hasta(), null);
			jPanel.add(get_btn_buscar_articulo(), null);
			jPanel.add(get_btn_buscar_cuenta(), null);
		}
		return jPanel;
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
			_lst_modo.setToolTipText("Modo de Busqueda");
			_lst_modo.setBounds(new Rectangle(95, 4, 107, 19));
			
		}
		return _lst_modo;
	}
	public JComboBox get_lst_tc() {
		if (_lst_tc == null) {
			_lst_tc = new JComboBox();
			_lst_tc.setBounds(new Rectangle(94, 92, 107, 19));
			_lst_tc.setToolTipText("Tipo de Comprobante");
			_lst_tc.setFont(new Font("Dialog", Font.PLAIN, 10));
			
		}
		return _lst_tc;
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
				_btn_buscar_fecha_desde.setLocation(new Point(205, 63));
				_btn_buscar_fecha_desde.setSize(new Dimension(23, 19));
				_btn_buscar_fecha_desde.setPreferredSize(new Dimension(24, 24));
				
				
			}
			return _btn_buscar_fecha_desde;
		}
			
		
	
	/**
	 * This method initializes _txt_articulo_descripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_articulo_descripcion() {
		if (_txt_articulo_descripcion == null) {
			_txt_articulo_descripcion = new JTextField();
			_txt_articulo_descripcion.setBounds(new Rectangle(230, 121, 262, 18));
			_txt_articulo_descripcion.setToolTipText("Descripcion");
			_txt_articulo_descripcion.setFont(new Font("Dialog", Font.PLAIN, 10));
			
		}
		return _txt_articulo_descripcion;
	}
	/**
	 * This method initializes _chk_seleccionar	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_seleccionar() {
		if (_chk_seleccionar == null) {
			_chk_seleccionar = new JCheckBox();
			_chk_seleccionar.setBounds(new Rectangle(3, 154, 103, 15));
			_chk_seleccionar.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_seleccionar.setText("Seleccionar");
			
		}
		return _chk_seleccionar;
	}
	
	
	/**
	 * This method initializes _btn_imprimir	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_imprimir() {
		if (_btn_imprimir == null) {
			_btn_imprimir = new JButton();
			
			_btn_imprimir.setIcon(new ImageIcon(getClass().getResource("/icons/fileprint.png")));
			_btn_imprimir.setToolTipText("Imprmir Listado de los Comprobantes Seleccionados");
			
		}
		return _btn_imprimir;
	}
	/**
	 * This method initializes jToolBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar() {
		if (jToolBar == null) {
			jToolBar = new JToolBar();
			jToolBar.setBounds(new Rectangle(0, 0, 989, 24));
			jToolBar.setFloatable(false);
			jToolBar.add(get_btn_cancelar());
			jToolBar.add(get_btn_cargar());
			jToolBar.add(get_btn_imprimir());
			jToolBar.add(get_btn_salir());
			jToolBar.addSeparator();
			jToolBar.add(get_btn_error());
		}
		return jToolBar;
	}
	
	/**
	 * This method initializes _txt_idarticulo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idarticulo() {
		if (_txt_idarticulo == null) {
			_txt_idarticulo = new JTextField();
			_txt_idarticulo.setToolTipText("codigo de articulo");
			_txt_idarticulo.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_idarticulo.setBounds(new Rectangle(94, 121, 109, 18));
			
		}
		return _txt_idarticulo;
	}

	/**
	 * This method initializes _txt_fecha_hasta	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_fecha_hasta() {
		if (_txt_fecha_hasta == null) {
			_txt_fecha_hasta = new JTextField();
			_txt_fecha_hasta.setBounds(new Rectangle(302, 62, 109, 19));
			_txt_fecha_hasta.setToolTipText("Fecha Hasta");
		}
		return _txt_fecha_hasta;
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
	 * This method initializes _btn_cargar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cargar() {
		if (_btn_cargar == null) {
			_btn_cargar = new JButton();
			_btn_cargar.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-refresh.png")));
		}
		return _btn_cargar;
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
		}
		return _btn_salir;
	}

	/**
	 * This method initializes _btn_buscar_fecha_hasta	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_fecha_hasta() {
		if (_btn_buscar_fecha_hasta == null) {
			_btn_buscar_fecha_hasta = new JButton();
			_btn_buscar_fecha_hasta.setBounds(new Rectangle(415, 62, 22, 19));
			_btn_buscar_fecha_hasta.setIcon(new ImageIcon(getClass().getResource("/icons/calendar-16.gif")));
		}
		return _btn_buscar_fecha_hasta;
	}

	/**
	 * This method initializes _btn_buscar_articulo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_articulo() {
		if (_btn_buscar_articulo == null) {
			_btn_buscar_articulo = new JButton();
			_btn_buscar_articulo.setBounds(new Rectangle(205, 119, 23, 21));
			_btn_buscar_articulo.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-searchtool.png")));
		}
		return _btn_buscar_articulo;
	}

	/**
	 * This method initializes _btn_buscar_cuenta	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_cuenta() {
		if (_btn_buscar_cuenta == null) {
			_btn_buscar_cuenta = new JButton();
			_btn_buscar_cuenta.setBounds(new Rectangle(205, 32, 22, 20));
			_btn_buscar_cuenta.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-searchtool.png")));
		}
		return _btn_buscar_cuenta;
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
	
}  //  @jve:decl-index=0:visual-constraint="10,10"
