package aplicacion.cliente.gestion.gui;

import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumnModel;
import javax.swing.JTabbedPane;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

import java.awt.Font;
import java.awt.event.*;
import javax.swing.JCheckBox;

import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import java.awt.Point;


public class _Frame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JToolBar jToolBar = null;
	private JTable jTable;
	private JButton _btn_cargar = null;
	private JButton CargarPrecios = null;
	private JScrollPane jScrollPane = null;
	private JProgressBar jProgressBar = null;
	private JButton _btn_cancelar = null;
	private JButton _btn_salir = null;
	private JToolBar jToolBar1 = null;
	private JButton _btn_editar = null;
	private JTextField _txt_saldo_deudor = null;
	private JTextField _txt_saldo_acreedor = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JTextField _txt_saldo = null;
	private JCheckBox _chk_saldos_distintos_de_cero = null;
	private JButton _btn_error = null;
	private JButton _btn_reporte = null;
	private JTextField _txt_desde = null;
	private JTextField _txt_hasta = null;
	private JButton _btn_fecha_desde = null;
	private JButton _btn_fecha_hasta = null;
	private JLabel jLabel3 = null;
	private JLabel jLabel4 = null;
	/**
	 * This method initializes jToolBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar() {
		if (jToolBar == null) {
			jToolBar = new JToolBar();
			jToolBar.setBounds(new Rectangle(7, 3, 1003, 22));
			jToolBar.setFloatable(false);
			jToolBar.add(get_btn_cargar());
			jToolBar.add(get_btn_reporte());
			jToolBar.add(get_btn_cancelar());
			jToolBar.add(get_btn_salir());
			jToolBar.addSeparator();
			jToolBar.add(get_btn_error());
		}
		return jToolBar;
	}
	
	
	/**
	 * This method initializes Table1	
	 * 	
	 * @return javax.swing.JTable	
	 */
	

	public void eval_linea(JTextField tx,int row,int col){
		String aux=tx.getText();
		jTable.changeSelection(row, col+1, false, false);
		jTable.editCellAt(row, col+1);
		jTable.transferFocus();	
	}
	public void eval_lineaproveedor(JTextField tx,int row,int col){
		String aux=tx.getText();
		jTable.changeSelection(row+1, 0, false, false);
		jTable.editCellAt(row+1, 0);
		jTable.transferFocus();	
	}
	
	
	
	/**
	 * This method initializes _btn_cargar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cargar() {
		if (_btn_cargar == null) {
			_btn_cargar = new JButton();
			URL resourceURL = getClass().getClassLoader().getResource("beta/tools/icons/stock_refresh.png");
			_btn_cargar.setIcon(new ImageIcon(resourceURL));
			_btn_cargar.setToolTipText("Cargar");
			
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
			jScrollPane.setBounds(new Rectangle(7, 125, 994, 426));
			jScrollPane.setViewportView(getJTable());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTable1	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getJTable() {
		if (jTable == null) {
			jTable = new JTable();
		}
		return jTable;
	}

	public void setJTable(JTable jtable){
		this.jTable=jtable;
		this.jScrollPane.setViewportView(jTable);
	}
	/**
	 * This method initializes jProgressBar	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	public JProgressBar getJProgressBar() {
		if (jProgressBar == null) {
			jProgressBar = new JProgressBar();
			jProgressBar.setFont(new Font("Dialog", Font.PLAIN, 10));
			jProgressBar.setBounds(new Rectangle(0, 28, 1016, 14));
		}
		return jProgressBar;
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
			URL resourceURL = getClass().getClassLoader().getResource("beta/tools/icons/stock_calc-cancel.png");
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
			URL resourceURL = getClass().getClassLoader().getResource("beta/tools/icons/exit.png");
			_btn_salir.setIcon(new ImageIcon(resourceURL));
			
		}
		return _btn_salir;
	}

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
		this.setTitle("Gestion de Clientes");
		this.setContentPane(getJContentPane());
		this.setBounds(new Rectangle(0, 0, 1024, 695));
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(777, 74, 67, 19));
			jLabel4.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel4.setText("Hasta");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(516, 76, 108, 16));
			jLabel3.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel3.setText("Consumo desde");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(765, 629, 109, 14));
			jLabel2.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2.setText("Saldo");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(757, 598, 117, 15));
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setText("Saldo Acreedor");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(755, 570, 116, 14));
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setText("Saldo Deudor");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(getJProgressBar(), null);
			jContentPane.add(getJToolBar1(), null);
			jContentPane.add(get_txt_saldo_deudor(), null);
			jContentPane.add(get_txt_saldo_acreedor(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(get_txt_saldo(), null);
			jContentPane.add(get_chk_saldos_distintos_de_cero(), null);
			jContentPane.add(get_txt_desde(), null);
			jContentPane.add(get_txt_hasta(), null);
			jContentPane.add(get_btn_fecha_desde(), null);
			jContentPane.add(get_btn_fecha_hasta(), null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(jLabel4, null);
		}
		return jContentPane;
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
			jToolBar1.setBounds(new Rectangle(0, 44, 1014, 22));
			jToolBar1.add(get_btn_editar());
		}
		return jToolBar1;
	}

	/**
	 * This method initializes _btn_editar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_editar() {
		if (_btn_editar == null) {
			_btn_editar = new JButton();
			_btn_editar.setIcon(new ImageIcon(getClass().getResource("/icons/stock_hyperlink-internet-search.png")));
			_btn_editar.setToolTipText("Ver Archivo Maestro del Proveedor Seleccionado");
		}
		return _btn_editar;
	}

	/**
	 * This method initializes _txt_saldo_deudor	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_saldo_deudor() {
		if (_txt_saldo_deudor == null) {
			_txt_saldo_deudor = new JTextField();
			_txt_saldo_deudor.setBounds(new Rectangle(877, 571, 115, 17));
			_txt_saldo_deudor.setHorizontalAlignment(JTextField.RIGHT);
			_txt_saldo_deudor.setEditable(false);
			_txt_saldo_deudor.setBackground(Color.white);
			_txt_saldo_deudor.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_saldo_deudor;
	}


	/**
	 * This method initializes _txt_saldo_acreedor	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_saldo_acreedor() {
		if (_txt_saldo_acreedor == null) {
			_txt_saldo_acreedor = new JTextField();
			_txt_saldo_acreedor.setBounds(new Rectangle(878, 596, 116, 17));
			_txt_saldo_acreedor.setHorizontalAlignment(JTextField.RIGHT);
			_txt_saldo_acreedor.setEditable(false);
			_txt_saldo_acreedor.setBackground(Color.white);
			_txt_saldo_acreedor.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_saldo_acreedor;
	}


	/**
	 * This method initializes _txt_saldo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_saldo() {
		if (_txt_saldo == null) {
			_txt_saldo = new JTextField();
			_txt_saldo.setBounds(new Rectangle(878, 625, 114, 16));
			_txt_saldo.setHorizontalAlignment(JTextField.RIGHT);
			_txt_saldo.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_saldo.setBackground(Color.white);
			_txt_saldo.setEditable(false);
		}
		return _txt_saldo;
	}


	/**
	 * This method initializes _chk_saldos_distintos_de_cero	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_saldos_distintos_de_cero() {
		if (_chk_saldos_distintos_de_cero == null) {
			_chk_saldos_distintos_de_cero = new JCheckBox();
			_chk_saldos_distintos_de_cero.setBounds(new Rectangle(6, 106, 267, 16));
			_chk_saldos_distintos_de_cero.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_saldos_distintos_de_cero.setSelected(true);
			_chk_saldos_distintos_de_cero.setText("Saldos Distintos de Cero");
		}
		return _chk_saldos_distintos_de_cero;
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
	 * This method initializes _txt_desde	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_desde() {
		if (_txt_desde == null) {
			_txt_desde = new JTextField();
			_txt_desde.setBounds(new Rectangle(628, 75, 120, 18));
			_txt_desde.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_desde.setToolTipText("Fecha Desde");
		}
		return _txt_desde;
	}


	/**
	 * This method initializes _txt_hasta	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_hasta() {
		if (_txt_hasta == null) {
			_txt_hasta = new JTextField();
			_txt_hasta.setBounds(new Rectangle(846, 75, 120, 18));
			_txt_hasta.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_hasta.setToolTipText("Fecha hasta");
		}
		return _txt_hasta;
	}


	/**
	 * This method initializes _btn_fecha_desde	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_fecha_desde() {
		if (_btn_fecha_desde == null) {
			_btn_fecha_desde = new JButton();
			_btn_fecha_desde.setBounds(new Rectangle(749, 75, 17, 17));
			_btn_fecha_desde.setIcon(new ImageIcon(getClass().getResource("/icons/calendar-16.gif")));
		}
		return _btn_fecha_desde;
	}


	/**
	 * This method initializes _btn_fecha_hasta	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_fecha_hasta() {
		if (_btn_fecha_hasta == null) {
			_btn_fecha_hasta = new JButton();
			_btn_fecha_hasta.setBounds(new Rectangle(967, 75, 19, 17));
			_btn_fecha_hasta.setIcon(new ImageIcon(getClass().getResource("/icons/calendar-16.gif")));
		}
		return _btn_fecha_hasta;
	}
	
	

}  //  @jve:decl-index=0:visual-constraint="12,17"
