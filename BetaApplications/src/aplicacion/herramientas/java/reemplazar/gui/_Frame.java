package aplicacion.herramientas.java.reemplazar.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import aplicacion.herramientas.java.reemplazar.logic.*;


import java.awt.Font;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import javax.swing.JToolBar;
import java.awt.Toolkit;
import aplicacion.herramientas.java.reemplazar.logic.Java2sAutoComboBox;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class _Frame extends JFrame {

	
	private JPanel jContentPane = null;

	private JLabel jLabel = null;
	
	private JLabel jLabel1 = null;

	private Java2sAutoComboBox _lst_search = null;

	private JButton _btn_reemplazar = null;

	private JButton _btn_reemplazar_todo = null;

	private JButton _btn_buscar = null;
	
	private JButton _btn_buscar_reemplazar = null;
	private JButton _btn_salir = null;
	private boolean closed=true;

	private JCheckBox _chk_exacta = null;

	private JRadioButton _chk_adelante = null;

	private JRadioButton _chk_atras = null;

	private JButton _btn_sacar_repeticiones = null;

	private JLabel jLabel3 = null;

	private JButton _btn_sacar_todas_repeticiones = null;

	private JToolBar jToolBar = null;

	private Java2sAutoComboBox _lst_replacement = null;

	
	private JCheckBox _chk_comience = null;

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
		this.setSize(277, 317);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/chrome.png")));
		this.setResizable(false);
		this.setContentPane(getJContentPane());
		this.setTitle("Buscar/Reemplazar");
		
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(17, 85, 79, 16));
			jLabel3.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3.setText("Direccion");
			jLabel1 = new JLabel();
			jLabel1.setText("Reemplazar con");
			jLabel1.setSize(new Dimension(80, 18));
			jLabel1.setFont(new Font("Tahoma", Font.PLAIN, 10));
			jLabel1.setLocation(new Point(11, 54));
			jLabel = new JLabel();
			jLabel.setText("Buscar");
			jLabel.setSize(new Dimension(80, 18));
			jLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
			jLabel.setLocation(new Point(13, 32));
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(get_lst_search(), null);
			//jContentPane.add(getReplacement(), null);
			jContentPane.add(get_btn_reemplazar(), null);
			jContentPane.add(get_btn_reemplazar_todo(), null);
			jContentPane.add(get_btn_buscar(), null);
			jContentPane.add(get_btn_buscar_reemplazar(), null);
			jContentPane.add(get_chk_exacta(), null);
			jContentPane.add(get_chk_adelante(), null);
			jContentPane.add(get_chk_atras(), null);
			jContentPane.add(get_btn_sacar_repeticiones(), null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(get_btn_sacar_todas_repeticiones(), null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(get_lst_replacement(), null);
			jContentPane.add(get_chk_comience(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes _lst_search	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public Java2sAutoComboBox get_lst_search() {
		if (_lst_search == null) {
			_lst_search = new Java2sAutoComboBox(new LinkedList());;
			_lst_search.setStrict(false);
			_lst_search.setLocation(new Point(99, 31));
			_lst_search.setFont(new Font("Dialog", Font.PLAIN, 10));
			_lst_search.setSize(new Dimension(163, 18));
			
		}
		return _lst_search;
	}

	/**
	 * This method initializes _btn_reemplazar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_reemplazar() {
		if (_btn_reemplazar == null) {
			_btn_reemplazar = new JButton();
			_btn_reemplazar.setFont(new Font("Tahoma", Font.PLAIN, 10));
			_btn_reemplazar.setLocation(new Point(9, 207));
			_btn_reemplazar.setSize(new Dimension(120, 18));
			_btn_reemplazar.setText("Reemplazar");
			
		}
		return _btn_reemplazar;
	}

	/**
	 * This method initializes _btn_reemplazar_todo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_reemplazar_todo() {
		if (_btn_reemplazar_todo == null) {
			_btn_reemplazar_todo = new JButton();
			_btn_reemplazar_todo.setFont(new Font("Tahoma", Font.PLAIN, 10));
			_btn_reemplazar_todo.setLocation(new Point(133, 207));
			_btn_reemplazar_todo.setSize(new Dimension(120, 18));
			_btn_reemplazar_todo.setText("Reemplazar Todo");
			
		}
		return _btn_reemplazar_todo;
	}

	/**
	 * This method initializes _btn_buscar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar() {
		if (_btn_buscar == null) {
			_btn_buscar = new JButton();
			_btn_buscar.setFont(new Font("Tahoma", Font.PLAIN, 10));
			_btn_buscar.setLocation(new Point(9, 182));
			_btn_buscar.setSize(new Dimension(120, 18));
			_btn_buscar.setText("Buscar");
			
		}
		return _btn_buscar;
	}

	/**
	 * This method initializes _btn_buscar_reemplazar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_reemplazar() {
		if (_btn_buscar_reemplazar == null) {
			_btn_buscar_reemplazar = new JButton();
			_btn_buscar_reemplazar.setFont(new Font("Tahoma", Font.PLAIN, 10));
			_btn_buscar_reemplazar.setPreferredSize(new Dimension(120, 18));
			_btn_buscar_reemplazar.setLocation(new Point(133, 182));
			_btn_buscar_reemplazar.setSize(new Dimension(120, 18));
			_btn_buscar_reemplazar.setText("Buscar/Reemplazar");
			
		}
		return _btn_buscar_reemplazar;
	}

	/**
	 * This method initializes _btn_salir	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_salir() {
		if (_btn_salir == null) {
			_btn_salir = new JButton();
			_btn_salir.setFont(new Font("Tahoma", Font.PLAIN, 10));
			_btn_salir.setIcon(new ImageIcon(getClass().getResource("/icons/exit.png")));
			_btn_salir.setPreferredSize(new Dimension(24, 22));
			_btn_salir.setText("");
			
		}
		return _btn_salir;
	}
	
	public void showGui(){
		if (!closed){
			
			this.setVisible(true);
			this.toFront();
		}
	}
	
	public void setClosed(){
		this.closed=true;
	}
	
	public void setOpen(){
		this.closed=false;
	}
	
	public boolean isClosed(){
		return this.closed;
	}
	
	
	/**
	 * This method initializes _chk_exacta	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_exacta() {
		if (_chk_exacta == null) {
			_chk_exacta = new JCheckBox();
			_chk_exacta.setBounds(new Rectangle(9, 134, 109, 16));
			_chk_exacta.setFont(new Font("Tahoma", Font.PLAIN, 10));
			_chk_exacta.setText("Busqueda Exacta");
		}
		return _chk_exacta;
	}
	/**
	 * This method initializes _chk_adelante	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	public JRadioButton get_chk_adelante() {
		if (_chk_adelante == null) {
			_chk_adelante = new JRadioButton();
			_chk_adelante.setBounds(new Rectangle(101, 83, 75, 20));
			_chk_adelante.setSelected(true);
			_chk_adelante.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_adelante.setText("Adelante");
			_chk_adelante.addChangeListener(new javax.swing.event.ChangeListener() {
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					System.out.println("stateChanged()"); // TODO Auto-generated Event stub stateChanged()
					JRadioButton j=(JRadioButton)e.getSource();
					if (j.isSelected()){
						if (_chk_atras.isSelected())_chk_atras.setSelected(false);
					}else {
						if (!_chk_atras.isSelected())_chk_adelante.setSelected(true);
					}
				}
			});
		}
		return _chk_adelante;
	}
	/**
	 * This method initializes _chk_atras	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	public JRadioButton get_chk_atras() {
		if (_chk_atras == null) {
			_chk_atras = new JRadioButton();
			_chk_atras.setBounds(new Rectangle(100, 109, 72, 17));
			_chk_atras.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_atras.setText("Atras");
			
		}
		return _chk_atras;
	}
	
	/**
	 * This method initializes _btn_sacar_repeticiones	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_sacar_repeticiones() {
		if (_btn_sacar_repeticiones == null) {
			_btn_sacar_repeticiones = new JButton();
			_btn_sacar_repeticiones.setFont(new Font("Tahoma", Font.PLAIN, 10));
			_btn_sacar_repeticiones.setLocation(new Point(10, 233));
			_btn_sacar_repeticiones.setSize(new Dimension(120, 18));
			_btn_sacar_repeticiones.setText("Sacar repeticiones");
		}
		return _btn_sacar_repeticiones;
	}
	/**
	 * This method initializes _btn_sacar_todas_repeticiones	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_sacar_todas_repeticiones() {
		if (_btn_sacar_todas_repeticiones == null) {
			_btn_sacar_todas_repeticiones = new JButton();
			_btn_sacar_todas_repeticiones.setFont(new Font("Tahoma", Font.PLAIN, 10));
			_btn_sacar_todas_repeticiones.setPreferredSize(new Dimension(120, 18));
			_btn_sacar_todas_repeticiones.setSize(new Dimension(120, 18));
			_btn_sacar_todas_repeticiones.setLocation(new Point(134, 232));
			_btn_sacar_todas_repeticiones.setText("Sacar Todas Rep.");
		}
		return _btn_sacar_todas_repeticiones;
	}
	/**
	 * This method initializes jToolBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar() {
		if (jToolBar == null) {
			jToolBar = new JToolBar();
			jToolBar.setSize(new Dimension(269, 24));
			jToolBar.setLocation(new Point(0, 0));
			jToolBar.setFloatable(false);
			jToolBar.setToolTipText("Cerrar");
			jToolBar.add(get_btn_salir());
		}
		return jToolBar;
	}
	/**
	 * This method initializes _lst_replacement	
	 * 	
	 * @return aplicacion.herramientas.java.reemplazar.logic.Java2sAutoComboBox	
	 */
	public Java2sAutoComboBox get_lst_replacement() {
		if (_lst_replacement == null) {
			
			_lst_replacement = new Java2sAutoComboBox(new LinkedList());
			_lst_replacement.setBounds(new Rectangle(99, 55, 163, 18));
			_lst_replacement.setStrict(false);
			_lst_replacement.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _lst_replacement;
	}
	/**
	 * This method initializes _chk_comience	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox get_chk_comience() {
		if (_chk_comience == null) {
			_chk_comience = new JCheckBox();
			_chk_comience.setBounds(new Rectangle(9, 155, 120, 16));
			_chk_comience.setFont(new Font("Dialog", Font.PLAIN, 10));
			_chk_comience.setText("que comience");
		}
		return _chk_comience;
	}
}  //  @jve:decl-index=0:visual-constraint="8,0"
