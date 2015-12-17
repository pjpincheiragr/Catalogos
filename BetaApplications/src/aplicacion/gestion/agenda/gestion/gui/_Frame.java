package aplicacion.gestion.agenda.gestion.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTree;
import javax.swing.JProgressBar;
import java.awt.Point;
import java.awt.Insets;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JTextField _txt_idvendedor = null;
	private JTextField _txt_vendedor_descripcion = null;
	private JTabbedPane jTabbedPane = null;
	private JPanel jPanel = null;
	private JComboBox _lst_mes = null;
	private JComboBox _lst_anio = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable2 = null;
	private JToolBar jToolBar = null;
	private JButton _btn_cancelar = null;
	private JButton _btn_nuevo = null;
	private JScrollPane jScrollPane1 = null;
	private JTable jTable = null;
	private JScrollPane jScrollPane2 = null;  //  @jve:decl-index=0:visual-constraint="28,288"
	private JTree jTree = null;
	private JTextField _txt_agenda = null;
	private JProgressBar jProgressBar = null;
	private JPanel jPanel1 = null;
	private JPanel jPanel2 = null;
	private JPanel jPanel3 = null;
	private JScrollPane jScrollPane21 = null;
	private JPanel jPanel4 = null;
	private JToolBar jToolBar1 = null;
	private JButton _btn_agregar = null;
	private JButton _btn_quitar = null;
	private JButton _btn_editar = null;
	private JButton _btn_salir = null;
	private JScrollPane jScrollPane22 = null;
	private JTable jTable_semanal = null;
	private JScrollPane jScrollPane23 = null;
	private JTable jTable_mensual = null;
	private JButton _btn_cargar = null;
	private JButton _btn_hoy = null;
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
		this.setSize(1024, 549);
		//this.setResizable(false);
		this.setContentPane(getJContentPane());
		this.setTitle("Agenda");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
			gridBagConstraints16.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints16.gridy = 1;
			gridBagConstraints16.ipadx = 0;
			gridBagConstraints16.ipady = 0;
			gridBagConstraints16.fill = GridBagConstraints.BOTH;
			gridBagConstraints16.gridx = 0;
			GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
			gridBagConstraints15.fill = GridBagConstraints.BOTH;
			gridBagConstraints15.gridwidth = 2;
			gridBagConstraints15.gridx = 0;
			gridBagConstraints15.gridy = 0;
			gridBagConstraints15.ipadx = 926;
			gridBagConstraints15.ipady = -6;
			gridBagConstraints15.weightx = 1.0;
			gridBagConstraints15.insets = new Insets(2, 2, 4, 2);
			GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
			gridBagConstraints14.fill = GridBagConstraints.BOTH;
			gridBagConstraints14.gridx = 1;
			gridBagConstraints14.gridy = 1;
			gridBagConstraints14.ipadx = 0;
			gridBagConstraints14.ipady = 0;
			gridBagConstraints14.weightx = 1.0;
			gridBagConstraints14.weighty = 1.0;
			gridBagConstraints14.insets = new Insets(0, 0, 0, 0);
			jLabel = new JLabel();
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setText("usuario");
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(getJTabbedPane(), gridBagConstraints14);
			jContentPane.add(getJToolBar(), gridBagConstraints15);
			jContentPane.add(getJPanel3(), gridBagConstraints16);
		}
		return jContentPane;
	}

	/**
	 * This method initializes _txt_idvendedor	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idvendedor() {
		if (_txt_idvendedor == null) {
			_txt_idvendedor = new JTextField();
			_txt_idvendedor.setHorizontalAlignment(JTextField.RIGHT);
			_txt_idvendedor.setFont(new Font("Dialog", Font.PLAIN, 10));
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
			_txt_vendedor_descripcion.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_vendedor_descripcion.setHorizontalAlignment(JTextField.LEFT);
			_txt_vendedor_descripcion.setEditable(false);
		}
		return _txt_vendedor_descripcion;
	}

	/**
	 * This method initializes jTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	public JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.setFont(new Font("Dialog", Font.PLAIN, 10));
			jTabbedPane.addTab("Diario", null, getJPanel(), null);
			jTabbedPane.addTab("Semana", null, getJScrollPane22(), null);
			jTabbedPane.addTab("Mensual", null, getJScrollPane23(), null);
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
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.weighty = 1.0;
			gridBagConstraints.gridx = 0;
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
			jPanel.add(getJScrollPane1(), gridBagConstraints);
		}
		return jPanel;
	}

	/**
	 * This method initializes _lst_mes	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_mes() {
		if (_lst_mes == null) {
			_lst_mes = new JComboBox();
			_lst_mes.addItem("Enero");
			_lst_mes.addItem("Febrero");
			_lst_mes.addItem("Marzo");
			_lst_mes.addItem("Abril");
			_lst_mes.addItem("Mayo");
			_lst_mes.addItem("Junio");
			_lst_mes.addItem("Julio");
			_lst_mes.addItem("Agosto");
			_lst_mes.addItem("Septiembre");
			_lst_mes.addItem("Octubre");
			_lst_mes.addItem("Noviembre");
			_lst_mes.addItem("Diciembre");
			_lst_mes.setFont(new Font("Dialog", Font.PLAIN, 9));
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
			
			_lst_anio.setFont(new Font("Dialog", Font.PLAIN, 9));
		}
		return _lst_anio;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			
			jScrollPane.setViewportView(jTable2);
		}
		return jScrollPane;
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
		this.jScrollPane.setViewportView(jTable2);
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
			jToolBar.add(get_btn_cancelar());
			jToolBar.add(get_btn_cargar());
			jToolBar.add(get_btn_hoy());
			jToolBar.add(get_btn_nuevo());
			jToolBar.add(get_btn_salir());
		}
		return jToolBar;
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
	 * This method initializes _btn_nuevo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_nuevo() {
		if (_btn_nuevo == null) {
			_btn_nuevo = new JButton();
			_btn_nuevo.setIcon(new ImageIcon(getClass().getResource("/icons/document-new.png")));
			_btn_nuevo.setToolTipText("Nuevo Aviso");
		}
		return _btn_nuevo;
	}

	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getJTable());
		}
		return jScrollPane1;
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
		this.jScrollPane1.setViewportView(jTable);
	}
	/**
	 * This method initializes jScrollPane2	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	/*
	private JScrollPane getJScrollPane2() {
		if (jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setSize(new Dimension(194, 253));
			jScrollPane2.setViewportView(getJTree());
		}
		return jScrollPane2;
	}*/

	/**
	 * This method initializes jTree	
	 * 	
	 * @return javax.swing.JTree	
	 */
	public JTree getJTree() {
		if (jTree == null) {
			jTree = new JTree();
			jTree.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return jTree;
	}

	public void setJTree(JTree tree){
		this.jTree=tree;
		this.jScrollPane21.setViewportView(jTree);
	}

	/**
	 * This method initializes _txt_agenda	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_agenda() {
		if (_txt_agenda == null) {
			_txt_agenda = new JTextField();
			_txt_agenda.setHorizontalAlignment(JTextField.LEFT);
			_txt_agenda.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_agenda.setEditable(false);
		}
		return _txt_agenda;
	}

	/**
	 * This method initializes jProgressBar	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	public JProgressBar getJProgressBar() {
		if (jProgressBar == null) {
			jProgressBar = new JProgressBar();
		}
		return jProgressBar;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
			gridBagConstraints13.fill = GridBagConstraints.BOTH;
			gridBagConstraints13.gridy = 5;
			gridBagConstraints13.weightx = 1.0;
			gridBagConstraints13.weighty = 0.0D;
			gridBagConstraints13.ipadx = 0;
			gridBagConstraints13.ipady = -16;
			gridBagConstraints13.insets = new Insets(10, 0, 0, 0);
			gridBagConstraints13.gridx = 0;
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.gridx = 0;
			gridBagConstraints8.ipady = 0;
			gridBagConstraints8.weighty = 0.0D;
			gridBagConstraints8.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints8.ipadx = 0;
			gridBagConstraints8.weightx = 1.0D;
			gridBagConstraints8.insets = new Insets(2, 0, 8, 0);
			gridBagConstraints8.gridy = 0;
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.fill = GridBagConstraints.BOTH;
			gridBagConstraints9.gridy = 6;
			gridBagConstraints9.weightx = 1.0;
			gridBagConstraints9.weighty = 1.0;
			gridBagConstraints9.gridx = 0;
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.gridx = 0;
			gridBagConstraints7.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints7.weighty = 0.0D;
			gridBagConstraints7.ipady = 0;
			gridBagConstraints7.insets = new Insets(14, 0, 4, 0);
			gridBagConstraints7.gridy = 4;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints6.gridx = 0;
			gridBagConstraints6.gridy = 3;
			gridBagConstraints6.weighty = 0.0D;
			gridBagConstraints6.weightx = 1.0;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.fill = GridBagConstraints.BOTH;
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.gridy = 1;
			gridBagConstraints2.ipady = -4;
			gridBagConstraints2.weighty = 0.0D;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.BOTH;
			gridBagConstraints1.gridy = 2;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.weighty = 0.0D;
			gridBagConstraints1.ipady = 120;
			gridBagConstraints1.gridx = 0;
			jPanel1 = new JPanel();
			jPanel1.setLayout(new GridBagLayout());
			jPanel1.add(getJPanel4(), gridBagConstraints8);
			jPanel1.add(getJPanel2(), gridBagConstraints2);
			jPanel1.add(get_txt_agenda(), gridBagConstraints6);
			jPanel1.add(getJScrollPane(), gridBagConstraints1);
			jPanel1.add(getJProgressBar(), gridBagConstraints7);
			jPanel1.add(getJScrollPane21(), gridBagConstraints9);
			jPanel1.add(getJToolBar1(), gridBagConstraints13);
		}
		return jPanel1;
	}

	/**
	 * This method initializes jPanel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints4.gridy = 1;
			gridBagConstraints4.weightx = 1.0;
			gridBagConstraints4.weighty = 1.0D;
			gridBagConstraints4.gridx = 1;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints3.gridy = 1;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.ipadx = 40;
			gridBagConstraints3.gridx = 0;
			jPanel2 = new JPanel();
			jPanel2.setLayout(new GridBagLayout());
			jPanel2.add(get_lst_mes(), gridBagConstraints3);
			jPanel2.add(get_lst_anio(), gridBagConstraints4);
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
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.gridx = 0;
			gridBagConstraints5.weightx = 1.0D;
			gridBagConstraints5.weighty = 1.0D;
			gridBagConstraints5.fill = GridBagConstraints.BOTH;
			gridBagConstraints5.gridy = 0;
			jPanel3 = new JPanel();
			jPanel3.setLayout(new GridBagLayout());
			jPanel3.add(getJPanel1(), gridBagConstraints5);
		}
		return jPanel3;
	}

	/**
	 * This method initializes jScrollPane21	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane21() {
		if (jScrollPane21 == null) {
			jScrollPane21 = new JScrollPane();
			jScrollPane21.setViewportView(this.getJTree());
		}
		return jScrollPane21;
	}

	/**
	 * This method initializes jPanel4	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel4() {
		if (jPanel4 == null) {
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			gridBagConstraints12.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints12.gridy = 0;
			gridBagConstraints12.weightx = 1.0;
			gridBagConstraints12.ipadx = 110;
			gridBagConstraints12.gridx = 2;
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints11.gridy = 0;
			gridBagConstraints11.weightx = 1.0;
			gridBagConstraints11.ipadx = 30;
			gridBagConstraints11.gridx = 1;
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.gridx = 0;
			gridBagConstraints10.ipadx = 4;
			gridBagConstraints10.gridy = 0;
			jPanel4 = new JPanel();
			jPanel4.setLayout(new GridBagLayout());
			jPanel4.add(jLabel, gridBagConstraints10);
			jPanel4.add(get_txt_idvendedor(), gridBagConstraints11);
			jPanel4.add(get_txt_vendedor_descripcion(), gridBagConstraints12);
		}
		return jPanel4;
	}

	/**
	 * This method initializes jToolBar1	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar1() {
		if (jToolBar1 == null) {
			jToolBar1 = new JToolBar();
			jToolBar1.setEnabled(true);
			jToolBar1.setFloatable(false);
			jToolBar1.add(get_btn_agregar());
			jToolBar1.add(get_btn_quitar());
			jToolBar1.add(get_btn_editar());
		}
		return jToolBar1;
	}

	/**
	 * This method initializes _btn_agregar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_agregar() {
		if (_btn_agregar == null) {
			_btn_agregar = new JButton();
			_btn_agregar.setIcon(new ImageIcon(getClass().getResource("/icons/add.png")));
		}
		return _btn_agregar;
	}

	/**
	 * This method initializes _btn_quitar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_quitar() {
		if (_btn_quitar == null) {
			_btn_quitar = new JButton();
			_btn_quitar.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-remove.png")));
		}
		return _btn_quitar;
	}

	/**
	 * This method initializes _btn_editar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_editar() {
		if (_btn_editar == null) {
			_btn_editar = new JButton();
			_btn_editar.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-run.png")));
		}
		return _btn_editar;
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
	 * This method initializes jScrollPane22	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane22() {
		if (jScrollPane22 == null) {
			jScrollPane22 = new JScrollPane();
			jScrollPane22.setViewportView(getJTable_semanal());
		}
		return jScrollPane22;
	}

	/**
	 * This method initializes jTable_semanal	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getJTable_semanal() {
		if (jTable_semanal == null) {
			jTable_semanal = new JTable();
		}
		return jTable_semanal;
	}

	public void setJTable_semanal(JTable table) {
			jTable_semanal = table;
			this.jScrollPane22.setViewportView(jTable_semanal);
	}
	public void setJTable_mensual(JTable table) {
		jTable_mensual = table;
		this.jScrollPane23.setViewportView(jTable_mensual);
}
	/**
	 * 
	 * This method initializes jScrollPane23	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane23() {
		if (jScrollPane23 == null) {
			jScrollPane23 = new JScrollPane();
			jScrollPane23.setViewportView(getJTable_mensual());
		}
		return jScrollPane23;
	}

	/**
	 * This method initializes jTable_mensual	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getJTable_mensual() {
		if (jTable_mensual == null) {
			jTable_mensual = new JTable();
		}
		return jTable_mensual;
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
			_btn_cargar.setToolTipText("Actualizar Agenda");
		}
		return _btn_cargar;
	}

	/**
	 * This method initializes _btn_hoy	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_hoy() {
		if (_btn_hoy == null) {
			_btn_hoy = new JButton();
			_btn_hoy.setIcon(new ImageIcon(getClass().getResource("/icons/stock_calendar-24.png")));
			_btn_hoy.setToolTipText("Ir al Dia Actual");
		}
		return _btn_hoy;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
