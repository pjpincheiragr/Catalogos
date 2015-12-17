package aplicacion.ventas.transferencia.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JToolBar;
import java.awt.Rectangle;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.JTabbedPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import java.awt.Point;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JToolBar jToolBar = null;
	private JButton _btn_guardar = null;
	private JButton _btn_salir = null;
	private JLabel jLabel = null;
	private JTextField _txt_idvendedor = null;
	private JTextField _txt_vendedor_descripcion = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private JCheckBox _chk_seleccionar = null;
	private JCheckBox _chk_avisar_destino = null;
	private JCheckBox _chk_recordarme = null;
	private JTabbedPane jTabbedPane = null;
	private JPanel jPanel = null;
	private JPanel jPanel1 = null;
	private JTabbedPane jTabbedPane1 = null;
	private JPanel jPanel2 = null;
	private JPanel jPanel3 = null;
	private JPanel jPanel4 = null;
	private JPanel jPanel5 = null;
	private JTextField _txt_agenda_destinatario = null;
	private JButton _btn_buscar_fecha_destinatario = null;
	private JSplitPane jSplitPane = null;
	private JTextField _txt_titulo_destinatario = null;
	private JScrollPane jScrollPane1 = null;
	private JTextArea _txt_mensaje_destinatario = null;
	private JPanel jPanel6 = null;
	private JPanel jPanel7 = null;
	private JTextField _txt_agenda_remitente = null;
	private JButton _btn_buscar_fecha_remitente = null;
	private JSplitPane jSplitPane1 = null;
	private JTextField _txt_titulo_remitente = null;
	private JScrollPane jScrollPane2 = null;
	private JTextArea _txt_mensaje_remitente = null;
	private JComboBox _lst_posponer = null;
	private JLabel jLabel1 = null;
	private JTextField _txt_idcreador = null;
	private JTextField _txt_creador_detalle = null;
	private JLabel jLabel2 = null;
	private JTextField _txt_idtransferencia = null;
	private JTextField _txt_fecha = null;
	private JLabel jLabel3 = null;
	private JButton _btn_buscar_fecha = null;
	private JButton _btn_cancelar = null;
	private JButton _btn_eliminar = null;
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
		this.setSize(757, 407);
		this.setResizable(false);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/chrome.png")));
		this.setContentPane(getJContentPane());
		this.setTitle("Transferencia");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(528, 31, 69, 17));
			jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel3.setText("Fecha");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(6, 33, 80, 19));
			jLabel2.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2.setText("idTransferencia");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(3, 79, 81, 18));
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setText("Transferir a:");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(get_txt_idvendedor(), null);
			jContentPane.add(get_txt_vendedor_descripcion(), null);
			jContentPane.add(getJTabbedPane(), null);
			jContentPane.add(getJLabel1(), null);
			jContentPane.add(get_txt_idcreador(), null);
			jContentPane.add(get_txt_creador_detalle(), null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(get_txt_idtransferencia(), null);
			jContentPane.add(get_txt_fecha(), null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(get_btn_buscar_fecha(), null);
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
			jToolBar.setBounds(new Rectangle(1, 3, 747, 23));
			jToolBar.setFloatable(false);
			jToolBar.add(get_btn_cancelar());
			jToolBar.add(get_btn_guardar());
			jToolBar.add(get_btn_eliminar());
			jToolBar.add(get_btn_salir());
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
			_btn_guardar.setIcon(new ImageIcon(getClass().getResource("/icons/document-save.png")));
		}
		return _btn_guardar;
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
	 * This method initializes _txt_idvendedor	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idvendedor() {
		if (_txt_idvendedor == null) {
			_txt_idvendedor = new JTextField();
			_txt_idvendedor.setBounds(new Rectangle(88, 80, 54, 19));
			_txt_idvendedor.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_idvendedor.setHorizontalAlignment(JTextField.RIGHT);
		}
		return _txt_idvendedor;
	}

	/**
	 * This method initializes _txt_vendedor_descripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_vendedor_descripcion() {
		if (_txt_vendedor_descripcion == null) {
			_txt_vendedor_descripcion = new JTextField();
			_txt_vendedor_descripcion.setBounds(new Rectangle(144, 80, 170, 19));
			_txt_vendedor_descripcion.setEditable(false);
			_txt_vendedor_descripcion.setHorizontalAlignment(JTextField.LEFT);
			_txt_vendedor_descripcion.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_vendedor_descripcion;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
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
	 * This method initializes _chk_seleccionar	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_seleccionar() {
		if (_chk_seleccionar == null) {
			_chk_seleccionar = new JCheckBox();
			_chk_seleccionar.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_seleccionar.setHorizontalAlignment(SwingConstants.LEFT);
			_chk_seleccionar.setText("seleccionar");
		}
		return _chk_seleccionar;
	}

	/**
	 * This method initializes _chk_avisar_destino	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_avisar_destino() {
		if (_chk_avisar_destino == null) {
			_chk_avisar_destino = new JCheckBox();
			_chk_avisar_destino.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_avisar_destino.setSelected(true);
			_chk_avisar_destino.setText("Avisar a Destinatario");
		}
		return _chk_avisar_destino;
	}

	/**
	 * This method initializes _chk_recordarme	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_recordarme() {
		if (_chk_recordarme == null) {
			_chk_recordarme = new JCheckBox();
			_chk_recordarme.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_recordarme.setSelected(true);
			_chk_recordarme.setText("Aviso a Remitente");
		}
		return _chk_recordarme;
	}

	/**
	 * This method initializes jTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.setBounds(new Rectangle(3, 106, 743, 266));
			jTabbedPane.setFont(new Font("Dialog", Font.PLAIN, 10));
			
			jTabbedPane.addTab("Pedidos", null, getJPanel1(), null);
			jTabbedPane.addTab("Agenda", new ImageIcon(getClass().getResource("/icons/calendar-16.gif")), getJPanel(), null);
		}
		return jTabbedPane;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.fill = GridBagConstraints.BOTH;
			gridBagConstraints2.gridy = 0;
			gridBagConstraints2.weightx = 1.0;
			gridBagConstraints2.weighty = 1.0;
			gridBagConstraints2.gridx = 0;
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
			jPanel.add(getJTabbedPane1(), gridBagConstraints2);
		}
		return jPanel;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.BOTH;
			gridBagConstraints1.gridy = 1;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.weighty = 1.0;
			gridBagConstraints1.gridx = 0;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.anchor = GridBagConstraints.WEST;
			gridBagConstraints.gridy = 0;
			jPanel1 = new JPanel();
			jPanel1.setLayout(new GridBagLayout());
			jPanel1.add(get_chk_seleccionar(), gridBagConstraints);
			jPanel1.add(getJScrollPane(), gridBagConstraints1);
		}
		return jPanel1;
	}

	/**
	 * This method initializes jTabbedPane1	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJTabbedPane1() {
		if (jTabbedPane1 == null) {
			jTabbedPane1 = new JTabbedPane();
			jTabbedPane1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jTabbedPane1.addTab("Destinatario", null, getJPanel2(), null);
			jTabbedPane1.addTab("Remitente", null, getJPanel3(), null);
		}
		return jTabbedPane1;
	}

	/**
	 * This method initializes jPanel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.fill = GridBagConstraints.BOTH;
			gridBagConstraints4.weightx = 1.0D;
			gridBagConstraints4.weighty = 1.0D;
			gridBagConstraints4.gridy = 1;
			jPanel2 = new JPanel();
			jPanel2.setLayout(new GridBagLayout());
			jPanel2.add(getJPanel4(), gridBagConstraints4);
		}
		return jPanel2;
	}

	/**
	 * This method initializes jPanel3	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.gridx = 0;
			gridBagConstraints10.fill = GridBagConstraints.BOTH;
			gridBagConstraints10.weighty = 1.0D;
			gridBagConstraints10.gridy = 1;
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.gridx = 0;
			gridBagConstraints9.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints9.weightx = 1.0D;
			gridBagConstraints9.weighty = 0.0D;
			gridBagConstraints9.anchor = GridBagConstraints.NORTH;
			gridBagConstraints9.gridy = 0;
			jPanel3 = new JPanel();
			jPanel3.setLayout(new GridBagLayout());
			jPanel3.add(getJPanel6(), gridBagConstraints9);
			jPanel3.add(getJPanel7(), gridBagConstraints10);
		}
		return jPanel3;
	}

	/**
	 * This method initializes jPanel4	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel4() {
		if (jPanel4 == null) {
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.fill = GridBagConstraints.BOTH;
			gridBagConstraints8.gridy = 1;
			gridBagConstraints8.weightx = 1.0;
			gridBagConstraints8.weighty = 1.0;
			gridBagConstraints8.ipady = 0;
			gridBagConstraints8.gridx = 0;
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.gridx = 0;
			gridBagConstraints5.anchor = GridBagConstraints.NORTH;
			gridBagConstraints5.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints5.weightx = 1.0D;
			gridBagConstraints5.weighty = 0.0D;
			gridBagConstraints5.ipady = 0;
			gridBagConstraints5.gridy = 0;
			jPanel4 = new JPanel();
			jPanel4.setLayout(new GridBagLayout());
			jPanel4.add(getJPanel5(), gridBagConstraints5);
			jPanel4.add(getJSplitPane(), gridBagConstraints8);
		}
		return jPanel4;
	}

	/**
	 * This method initializes jPanel5	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel5() {
		if (jPanel5 == null) {
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.gridx = 2;
			gridBagConstraints7.ipady = -6;
			gridBagConstraints7.ipadx = -10;
			gridBagConstraints7.gridy = 0;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.fill = GridBagConstraints.NONE;
			gridBagConstraints6.gridy = 0;
			gridBagConstraints6.weightx = 1.0;
			gridBagConstraints6.ipadx = 140;
			gridBagConstraints6.anchor = GridBagConstraints.EAST;
			gridBagConstraints6.ipady = 2;
			gridBagConstraints6.gridx = 1;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.anchor = GridBagConstraints.WEST;
			gridBagConstraints3.gridy = 0;
			gridBagConstraints3.gridx = 0;
			jPanel5 = new JPanel();
			jPanel5.setLayout(new GridBagLayout());
			jPanel5.add(get_chk_avisar_destino(), gridBagConstraints3);
			jPanel5.add(get_txt_agenda_destinatario(), gridBagConstraints6);
			jPanel5.add(get_btn_buscar_fecha_destinatario(), gridBagConstraints7);
		}
		return jPanel5;
	}

	/**
	 * This method initializes _txt_agenda_destinatario	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_agenda_destinatario() {
		if (_txt_agenda_destinatario == null) {
			_txt_agenda_destinatario = new JTextField();
			_txt_agenda_destinatario.setToolTipText("Fecha de Agenda");
			_txt_agenda_destinatario.setHorizontalAlignment(JTextField.RIGHT);
			_txt_agenda_destinatario.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_agenda_destinatario;
	}

	/**
	 * This method initializes _btn_buscar_fecha_destinatario	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_fecha_destinatario() {
		if (_btn_buscar_fecha_destinatario == null) {
			_btn_buscar_fecha_destinatario = new JButton();
			_btn_buscar_fecha_destinatario.setIcon(new ImageIcon(getClass().getResource("/icons/calendar-16.gif")));
		}
		return _btn_buscar_fecha_destinatario;
	}

	/**
	 * This method initializes jSplitPane	
	 * 	
	 * @return javax.swing.JSplitPane	
	 */
	private JSplitPane getJSplitPane() {
		if (jSplitPane == null) {
			jSplitPane = new JSplitPane();
			jSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
			jSplitPane.setBottomComponent(getJScrollPane1());
			jSplitPane.setTopComponent(get_txt_titulo_destinatario());
		}
		return jSplitPane;
	}

	/**
	 * This method initializes _txt_titulo_destinatario	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_titulo_destinatario() {
		if (_txt_titulo_destinatario == null) {
			_txt_titulo_destinatario = new JTextField();
			_txt_titulo_destinatario.setToolTipText("Titulo");
			_txt_titulo_destinatario.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_titulo_destinatario;
	}

	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(get_txt_mensaje_destinatario());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes _txt_mensaje_destinatario	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea get_txt_mensaje_destinatario() {
		if (_txt_mensaje_destinatario == null) {
			_txt_mensaje_destinatario = new JTextArea();
			_txt_mensaje_destinatario.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_mensaje_destinatario;
	}

	/**
	 * This method initializes jPanel6	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel6() {
		if (jPanel6 == null) {
			GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
			gridBagConstraints15.fill = GridBagConstraints.NONE;
			gridBagConstraints15.gridy = 0;
			gridBagConstraints15.weightx = 1.0;
			gridBagConstraints15.ipadx = 140;
			gridBagConstraints15.anchor = GridBagConstraints.EAST;
			gridBagConstraints15.ipady = -2;
			gridBagConstraints15.gridx = 1;
			GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
			gridBagConstraints13.gridx = 3;
			gridBagConstraints13.ipadx = -10;
			gridBagConstraints13.ipady = -6;
			gridBagConstraints13.gridy = 0;
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			gridBagConstraints12.fill = GridBagConstraints.NONE;
			gridBagConstraints12.gridy = 0;
			gridBagConstraints12.weightx = 0.0D;
			gridBagConstraints12.ipadx = 140;
			gridBagConstraints12.anchor = GridBagConstraints.EAST;
			gridBagConstraints12.gridx = 2;
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 0;
			gridBagConstraints11.gridy = 0;
			jPanel6 = new JPanel();
			jPanel6.setLayout(new GridBagLayout());
			jPanel6.add(get_chk_recordarme(), gridBagConstraints11);
			jPanel6.add(get_txt_agenda_remitente(), gridBagConstraints12);
			jPanel6.add(get_btn_buscar_fecha_remitente(), gridBagConstraints13);
			jPanel6.add(get_lst_posponer(), gridBagConstraints15);
		}
		return jPanel6;
	}

	/**
	 * This method initializes jPanel7	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel7() {
		if (jPanel7 == null) {
			GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
			gridBagConstraints14.fill = GridBagConstraints.BOTH;
			gridBagConstraints14.gridy = 0;
			gridBagConstraints14.weightx = 1.0;
			gridBagConstraints14.weighty = 1.0;
			gridBagConstraints14.gridx = 0;
			jPanel7 = new JPanel();
			jPanel7.setLayout(new GridBagLayout());
			jPanel7.add(getJSplitPane1(), gridBagConstraints14);
		}
		return jPanel7;
	}

	/**
	 * This method initializes _txt_agenda_remitente	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_agenda_remitente() {
		if (_txt_agenda_remitente == null) {
			_txt_agenda_remitente = new JTextField();
			_txt_agenda_remitente.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_agenda_remitente.setHorizontalAlignment(JTextField.RIGHT);
		}
		return _txt_agenda_remitente;
	}

	/**
	 * This method initializes _btn_buscar_fecha_remitente	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_fecha_remitente() {
		if (_btn_buscar_fecha_remitente == null) {
			_btn_buscar_fecha_remitente = new JButton();
			_btn_buscar_fecha_remitente.setIcon(new ImageIcon(getClass().getResource("/icons/calendar-16.gif")));
		}
		return _btn_buscar_fecha_remitente;
	}

	/**
	 * This method initializes jSplitPane1	
	 * 	
	 * @return javax.swing.JSplitPane	
	 */
	private JSplitPane getJSplitPane1() {
		if (jSplitPane1 == null) {
			jSplitPane1 = new JSplitPane();
			jSplitPane1.setOrientation(JSplitPane.VERTICAL_SPLIT);
			jSplitPane1.setBottomComponent(getJScrollPane2());
			jSplitPane1.setTopComponent(get_txt_titulo_remitente());
		}
		return jSplitPane1;
	}

	/**
	 * This method initializes _txt_titulo_remitente	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_titulo_remitente() {
		if (_txt_titulo_remitente == null) {
			_txt_titulo_remitente = new JTextField();
			_txt_titulo_remitente.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_titulo_remitente;
	}

	/**
	 * This method initializes jScrollPane2	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane2() {
		if (jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setViewportView(get_txt_mensaje_remitente());
		}
		return jScrollPane2;
	}

	/**
	 * This method initializes _txt_mensaje_remitente	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea get_txt_mensaje_remitente() {
		if (_txt_mensaje_remitente == null) {
			_txt_mensaje_remitente = new JTextArea();
			_txt_mensaje_remitente.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_mensaje_remitente;
	}

	/**
	 * This method initializes _lst_posponer	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_posponer() {
		if (_lst_posponer == null) {
			_lst_posponer = new JComboBox();
			_lst_posponer.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _lst_posponer;
	}

	/**
	 * This method initializes jLabel1	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("Remitente:");
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setBounds(new Rectangle(4, 56, 81, 20));
		}
		return jLabel1;
	}

	/**
	 * This method initializes _txt_idcreador	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idcreador() {
		if (_txt_idcreador == null) {
			_txt_idcreador = new JTextField();
			_txt_idcreador.setHorizontalAlignment(JTextField.RIGHT);
			_txt_idcreador.setLocation(new Point(89, 57));
			_txt_idcreador.setSize(new Dimension(54, 19));
			_txt_idcreador.setEditable(false);
			_txt_idcreador.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_idcreador;
	}

	/**
	 * This method initializes _txt_creador_detalle	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_creador_detalle() {
		if (_txt_creador_detalle == null) {
			_txt_creador_detalle = new JTextField();
			_txt_creador_detalle.setBounds(new Rectangle(145, 57, 169, 20));
			_txt_creador_detalle.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_creador_detalle.setHorizontalAlignment(JTextField.LEFT);
			_txt_creador_detalle.setEditable(false);
		}
		return _txt_creador_detalle;
	}

	/**
	 * This method initializes _txt_idtransferencia	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idtransferencia() {
		if (_txt_idtransferencia == null) {
			_txt_idtransferencia = new JTextField();
			_txt_idtransferencia.setBounds(new Rectangle(89, 32, 86, 19));
			_txt_idtransferencia.setEditable(false);
			_txt_idtransferencia.setEnabled(false);
		}
		return _txt_idtransferencia;
	}

	/**
	 * This method initializes _txt_fecha	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_fecha() {
		if (_txt_fecha == null) {
			_txt_fecha = new JTextField();
			_txt_fecha.setBounds(new Rectangle(599, 31, 119, 18));
			_txt_fecha.setHorizontalAlignment(JTextField.RIGHT);
			_txt_fecha.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_fecha;
	}

	/**
	 * This method initializes _btn_buscar_fecha	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_fecha() {
		if (_btn_buscar_fecha == null) {
			_btn_buscar_fecha = new JButton();
			_btn_buscar_fecha.setBounds(new Rectangle(720, 32, 18, 17));
			_btn_buscar_fecha.setIcon(new ImageIcon(getClass().getResource("/icons/calendar-16.gif")));
		}
		return _btn_buscar_fecha;
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
	 * This method initializes _btn_eliminar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_eliminar() {
		if (_btn_eliminar == null) {
			_btn_eliminar = new JButton();
			_btn_eliminar.setIcon(new ImageIcon(getClass().getResource("/icons/edit-delete.png")));
			_btn_eliminar.setEnabled(false);
			_btn_eliminar.setToolTipText("Anular Transferencia");
		}
		return _btn_eliminar;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
