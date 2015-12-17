package aplicacion.ventas.pedido.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Rectangle;
import java.awt.GridBagLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Toolkit;
import javax.swing.JToolBar;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.SwingConstants;

public class _Remito extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel jPanel = null;
	private JButton _btn_importar_relaciones = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private JToolBar jToolBar = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JTextField _txt_idcliente = null;
	private JTextField _txt_cliente_descripcion = null;
	private JTextField _txt_idpedido = null;
	private JTextField _txt_pedido_descripcion = null;
	private JLabel jLabel2 = null;
	private JTextField _txt_fecha = null;
	private JLabel jLabel3 = null;
	private JTextField _txt_total = null;
	private JButton _btn_cancelar = null;
	private JButton _btn_salir = null;
	private JCheckBox _chk_enviar_ctacte = null;
	private JCheckBox _chk_imprimir_copia = null;

	/**
	 * This is the default constructor
	 */
	public _Remito() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(767, 394);
		this.setContentPane(getJContentPane());
		this.setTitle("Remito");
		this.setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/chrome.png")));
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
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(518, 302, 79, 20));
			jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3.setText("Total:");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(579, 12, 70, 21));
			jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel2.setText("Fecha");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(8, 34, 53, 20));
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setText("cliente");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(10, 11, 53, 18));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setText("idpedido");
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBounds(new Rectangle(3, 28, 760, 338));
			jPanel.setBackground(Color.gray);
			jPanel.add(getJScrollPane(), null);
			jPanel.add(jLabel, null);
			jPanel.add(jLabel1, null);
			jPanel.add(get_txt_idcliente(), null);
			jPanel.add(get_txt_cliente_descripcion(), null);
			jPanel.add(get_txt_idpedido(), null);
			jPanel.add(get_txt_pedido_descripcion(), null);
			jPanel.add(jLabel2, null);
			jPanel.add(get_txt_fecha(), null);
			jPanel.add(jLabel3, null);
			jPanel.add(get_txt_total(), null);
			jPanel.add(get_chk_enviar_ctacte(), null);
			jPanel.add(get_chk_imprimir_copia(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes _btn_importar_relaciones	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_importar_relaciones() {
		if (_btn_importar_relaciones == null) {
			_btn_importar_relaciones = new JButton();
			_btn_importar_relaciones.setToolTipText("Generar Remito");
			_btn_importar_relaciones.setIcon(new ImageIcon(getClass().getResource("/icons/emblem-default-16.png")));
			_btn_importar_relaciones.setText("");
		}
		return _btn_importar_relaciones;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(11, 93, 730, 191));
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
	 * This method initializes jToolBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar() {
		if (jToolBar == null) {
			jToolBar = new JToolBar();
			jToolBar.setFloatable(false);
			jToolBar.setSize(new Dimension(824, 24));
			jToolBar.setLocation(new Point(0, 0));
			jToolBar.add(get_btn_importar_relaciones());
			jToolBar.add(get_btn_cancelar());
			jToolBar.add(get_btn_salir());
		}
		return jToolBar;
	}

	/**
	 * This method initializes _txt_idcliente	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idcliente() {
		if (_txt_idcliente == null) {
			_txt_idcliente = new JTextField();
			_txt_idcliente.setBounds(new Rectangle(66, 33, 100, 18));
			_txt_idcliente.setEditable(false);
			_txt_idcliente.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_idcliente;
	}

	/**
	 * This method initializes _txt_cliente_descripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_cliente_descripcion() {
		if (_txt_cliente_descripcion == null) {
			_txt_cliente_descripcion = new JTextField();
			_txt_cliente_descripcion.setBounds(new Rectangle(170, 33, 340, 18));
			_txt_cliente_descripcion.setEditable(false);
			_txt_cliente_descripcion.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_cliente_descripcion;
	}

	/**
	 * This method initializes _txt_idpedido	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idpedido() {
		if (_txt_idpedido == null) {
			_txt_idpedido = new JTextField();
			_txt_idpedido.setBounds(new Rectangle(67, 10, 100, 18));
			_txt_idpedido.setEditable(false);
			_txt_idpedido.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_idpedido;
	}

	/**
	 * This method initializes _txt_pedido_descripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_pedido_descripcion() {
		if (_txt_pedido_descripcion == null) {
			_txt_pedido_descripcion = new JTextField();
			_txt_pedido_descripcion.setBounds(new Rectangle(170, 10, 340, 18));
			_txt_pedido_descripcion.setEditable(false);
			_txt_pedido_descripcion.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_pedido_descripcion;
	}

	/**
	 * This method initializes _txt_fecha	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_fecha() {
		if (_txt_fecha == null) {
			_txt_fecha = new JTextField();
			_txt_fecha.setBounds(new Rectangle(653, 11, 99, 21));
			_txt_fecha.setEditable(false);
			_txt_fecha.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_fecha;
	}

	/**
	 * This method initializes _txt_total	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_total() {
		if (_txt_total == null) {
			_txt_total = new JTextField();
			_txt_total.setBounds(new Rectangle(604, 298, 136, 26));
			_txt_total.setForeground(Color.white);
			_txt_total.setHorizontalAlignment(JTextField.RIGHT);
			_txt_total.setFont(new Font("Dialog", Font.BOLD, 14));
			_txt_total.setEditable(false);
			_txt_total.setBackground(Color.black);
		}
		return _txt_total;
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
	 * This method initializes _chk_enviar_ctacte	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_enviar_ctacte() {
		if (_chk_enviar_ctacte == null) {
			_chk_enviar_ctacte = new JCheckBox();
			_chk_enviar_ctacte.setBounds(new Rectangle(67, 58, 163, 18));
			_chk_enviar_ctacte.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_enviar_ctacte.setText("Retiro Directo");
		}
		return _chk_enviar_ctacte;
	}

	/**
	 * This method initializes _chk_imprimir_copia	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_imprimir_copia() {
		if (_chk_imprimir_copia == null) {
			_chk_imprimir_copia = new JCheckBox();
			_chk_imprimir_copia.setBounds(new Rectangle(240, 58, 160, 18));
			_chk_imprimir_copia.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_imprimir_copia.setText("Imprimir Copia");
		}
		return _chk_imprimir_copia;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
