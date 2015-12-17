package aplicacion.cliente.corrector.gui;

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
	private JTextField _txt_debe = null;
	private JTextField _txt_haber = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JTextField _txt_saldo = null;
	private JTextField _txt_idcliente = null;
	private JButton _btn_buscar_cliente = null;
	private JTextField _txt_descripcion_cliente = null;
	private JTextField _txt_saldo_utilizable = null;
	private JLabel jLabel3 = null;
	private JLabel jLabel4 = null;
	private JScrollPane jScrollPane1 = null;
	private JTable jTable1 = null;
	private JScrollPane jScrollPane2 = null;
	private JTable jTable2 = null;
	private JScrollPane jScrollPane3 = null;
	private JTable jTable3 = null;
	private JCheckBox _chk_seleccionar_deudas = null;
	private JCheckBox _chk_seleccionar_creditos = null;
	private JButton _btn_exportar = null;
	private JButton _btn_seleccionar_pendientes = null;
	private JLabel jLabel6 = null;
	private JTextField _txt_diferencia = null;
	private JTextField _txt_anticipos = null;
	private JLabel jLabel7 = null;
	private JCheckBox _chk_seleccionar_anticipos = null;
	private JButton _btn_error = null;
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
			jToolBar.add(get_btn_cancelar());
			jToolBar.add(get_btn_seleccionar_pendientes());
			jToolBar.add(get_btn_exportar());
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
			jScrollPane.setBounds(new Rectangle(7, 78, 994, 136));
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
		this.setTitle("Analitico de Cliente");
		this.setContentPane(getJContentPane());
		this.setBounds(new Rectangle(0, 0, 1024, 695));
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	public JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel7 = new JLabel();
			jLabel7.setBounds(new Rectangle(506, 632, 107, 15));
			jLabel7.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel7.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel7.setText("Anticipos");
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(781, 314, 98, 14));
			jLabel6.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel6.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel6.setText("Diferencia");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(888, 55, 111, 19));
			jLabel4.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
			jLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel4.setText("ASIENTOS");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(760, 418, 120, 17));
			jLabel3.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3.setText("Saldo Conciliacion");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(832, 218, 46, 16));
			jLabel2.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2.setText("Saldo");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(498, 489, 117, 15));
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setText("Creditos");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(498, 349, 116, 14));
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setText("Deudas");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(getJProgressBar(), null);
			jContentPane.add(get_txt_debe(), null);
			jContentPane.add(get_txt_haber(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(get_txt_saldo(), null);
			jContentPane.add(get_txt_idcliente(), null);
			jContentPane.add(get_btn_buscar_cliente(), null);
			jContentPane.add(get_txt_descripcion_cliente(), null);
			jContentPane.add(get_txt_saldo_utilizable(), null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(jLabel4, null);
			jContentPane.add(getJScrollPane1(), null);
			jContentPane.add(getJScrollPane2(), null);
			jContentPane.add(getJScrollPane3(), null);
			jContentPane.add(get_chk_seleccionar_deudas(), null);
			jContentPane.add(get_chk_seleccionar_creditos(), null);
			jContentPane.add(jLabel6, null);
			jContentPane.add(get_txt_diferencia(), null);
			jContentPane.add(get_txt_anticipos(), null);
			jContentPane.add(jLabel7, null);
			jContentPane.add(get_chk_seleccionar_anticipos(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes _txt_debe	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_debe() {
		if (_txt_debe == null) {
			_txt_debe = new JTextField();
			_txt_debe.setBounds(new Rectangle(620, 348, 120, 17));
			_txt_debe.setHorizontalAlignment(JTextField.RIGHT);
			_txt_debe.setEditable(false);
			_txt_debe.setBackground(Color.white);
			_txt_debe.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_debe;
	}


	/**
	 * This method initializes _txt_haber	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_haber() {
		if (_txt_haber == null) {
			_txt_haber = new JTextField();
			_txt_haber.setBounds(new Rectangle(620, 487, 120, 17));
			_txt_haber.setHorizontalAlignment(JTextField.RIGHT);
			_txt_haber.setEditable(false);
			_txt_haber.setBackground(Color.white);
			_txt_haber.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_haber;
	}


	/**
	 * This method initializes _txt_saldo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_saldo() {
		if (_txt_saldo == null) {
			_txt_saldo = new JTextField();
			_txt_saldo.setBounds(new Rectangle(880, 218, 120, 16));
			_txt_saldo.setHorizontalAlignment(JTextField.RIGHT);
			_txt_saldo.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_saldo.setBackground(Color.white);
			_txt_saldo.setEditable(false);
		}
		return _txt_saldo;
	}


	/**
	 * This method initializes _txt_idcliente	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idcliente() {
		if (_txt_idcliente == null) {
			_txt_idcliente = new JTextField();
			_txt_idcliente.setBounds(new Rectangle(7, 48, 116, 17));
		}
		return _txt_idcliente;
	}


	/**
	 * This method initializes _btn_buscar_cliente	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_cliente() {
		if (_btn_buscar_cliente == null) {
			_btn_buscar_cliente = new JButton();
			_btn_buscar_cliente.setBounds(new Rectangle(125, 47, 20, 18));
			_btn_buscar_cliente.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-searchtool.png")));
		}
		return _btn_buscar_cliente;
	}


	/**
	 * This method initializes _txt_descripcion_cliente	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_descripcion_cliente() {
		if (_txt_descripcion_cliente == null) {
			_txt_descripcion_cliente = new JTextField();
			_txt_descripcion_cliente.setBounds(new Rectangle(149, 48, 272, 17));
			_txt_descripcion_cliente.setEditable(false);
		}
		return _txt_descripcion_cliente;
	}


	/**
	 * This method initializes _txt_saldo_utilizable	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_saldo_utilizable() {
		if (_txt_saldo_utilizable == null) {
			_txt_saldo_utilizable = new JTextField();
			_txt_saldo_utilizable.setBounds(new Rectangle(880, 417, 120, 16));
			_txt_saldo_utilizable.setEditable(false);
			_txt_saldo_utilizable.setBackground(Color.white);
			_txt_saldo_utilizable.setHorizontalAlignment(JTextField.RIGHT);
			_txt_saldo_utilizable.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_saldo_utilizable;
	}


	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setBounds(new Rectangle(8, 242, 738, 101));
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
	 * This method initializes jScrollPane2	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane2() {
		if (jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setBounds(new Rectangle(10, 374, 733, 104));
			jScrollPane2.setViewportView(getJTable2());
		}
		return jScrollPane2;
	}


	/**
	 * This method initializes jTable2	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getJTable2() {
		if (jTable2 == null) {
			jTable2 = new JTable();
		}
		return jTable2;
	}
	
	public void setJTable2(JTable table){
		this.jTable2=table;
		this.jScrollPane2.setViewportView(jTable2);
	}


	/**
	 * This method initializes jScrollPane3	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane3() {
		if (jScrollPane3 == null) {
			jScrollPane3 = new JScrollPane();
			jScrollPane3.setBounds(new Rectangle(11, 516, 730, 106));
			jScrollPane3.setViewportView(getJTable3());
		}
		return jScrollPane3;
	}


	/**
	 * This method initializes jTable3	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getJTable3() {
		if (jTable3 == null) {
			jTable3 = new JTable();
		}
		return jTable3;
	}
	public void setJTable3(JTable table) {
		
		jTable3 = table;
		this.jScrollPane3.setViewportView(jTable3);
	}


	/**
	 * This method initializes _chk_seleccionar_deudas	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_seleccionar_deudas() {
		if (_chk_seleccionar_deudas == null) {
			_chk_seleccionar_deudas = new JCheckBox();
			_chk_seleccionar_deudas.setBounds(new Rectangle(11, 221, 187, 17));
			_chk_seleccionar_deudas.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
			_chk_seleccionar_deudas.setText("DEUDAS VALIDAS");
		}
		return _chk_seleccionar_deudas;
	}


	/**
	 * This method initializes _chk_seleccionar_creditos	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_seleccionar_creditos() {
		if (_chk_seleccionar_creditos == null) {
			_chk_seleccionar_creditos = new JCheckBox();
			_chk_seleccionar_creditos.setBounds(new Rectangle(12, 348, 283, 19));
			_chk_seleccionar_creditos.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
			_chk_seleccionar_creditos.setText("CREDITOS VALIDOS");
		}
		return _chk_seleccionar_creditos;
	}


	/**
	 * This method initializes _btn_exportar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_exportar() {
		if (_btn_exportar == null) {
			_btn_exportar = new JButton();
			_btn_exportar.setIcon(new ImageIcon(getClass().getResource("/icons/go-top.png")));
		}
		return _btn_exportar;
	}


	/**
	 * This method initializes _btn_seleccionar_pendientes	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_seleccionar_pendientes() {
		if (_btn_seleccionar_pendientes == null) {
			_btn_seleccionar_pendientes = new JButton();
			_btn_seleccionar_pendientes.setIcon(new ImageIcon(getClass().getResource("/icons/emblem-certified-16.png")));
			_btn_seleccionar_pendientes.setToolTipText("Seleccionar Comprobantes Pendientes de Conciliacion");
		}
		return _btn_seleccionar_pendientes;
	}


	/**
	 * This method initializes _txt_diferencia	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_diferencia() {
		if (_txt_diferencia == null) {
			_txt_diferencia = new JTextField();
			_txt_diferencia.setBounds(new Rectangle(880, 313, 120, 16));
			_txt_diferencia.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_diferencia.setHorizontalAlignment(JTextField.RIGHT);
			_txt_diferencia.setBackground(Color.white);
			_txt_diferencia.setEditable(false);
		}
		return _txt_diferencia;
	}


	/**
	 * This method initializes _txt_anticipos	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_anticipos() {
		if (_txt_anticipos == null) {
			_txt_anticipos = new JTextField();
			_txt_anticipos.setBounds(new Rectangle(620, 631, 120, 17));
			_txt_anticipos.setHorizontalAlignment(JTextField.RIGHT);
			_txt_anticipos.setEditable(false);
			_txt_anticipos.setBackground(Color.white);
			_txt_anticipos.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_anticipos;
	}


	/**
	 * This method initializes _chk_seleccionar_anticipos	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_seleccionar_anticipos() {
		if (_chk_seleccionar_anticipos == null) {
			_chk_seleccionar_anticipos = new JCheckBox();
			_chk_seleccionar_anticipos.setBounds(new Rectangle(13, 494, 354, 16));
			_chk_seleccionar_anticipos.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
			_chk_seleccionar_anticipos.setText("ANTICIPOS GENERADOS POR CONCILIACIONES");
		}
		return _chk_seleccionar_anticipos;
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
	
}  //  @jve:decl-index=0:visual-constraint="12,17"
