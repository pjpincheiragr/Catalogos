package aplicacion.herramientas.java.calendario.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.Rectangle;
import javax.swing.JTable;
import java.awt.Dimension;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Point;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	
	private JComboBox _list_mes = null;
	private JComboBox _list_anio = null;
	private JTextField _txt_fecha = null;
	private JButton _btn_ok = null;
	private JTable jTable = null;
	private JScrollPane jScrollPane = null;
	

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
		this.setSize(250, 231);
		this.setResizable(false);
		this.setContentPane(getJContentPane());
		this.setTitle("Calendario");
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
			jContentPane.add(get_list_mes(), null);
			jContentPane.add(get_list_anio(), null);
			jContentPane.add(get_txt_fecha(), null);
			jContentPane.add(get_btn_ok(), null);
			jContentPane.add(getJScrollPane(), null);
	
		}
		return jContentPane;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	

	
	/**
	 * This method initializes _list_mes	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_list_mes() {
		if (_list_mes == null) {
			_list_mes = new JComboBox();
			_list_mes.setBounds(new Rectangle(5, 5, 127, 17));
			_list_mes.setFont(new Font("Dialog", Font.BOLD, 10));
		}
		return _list_mes;
	}

	/**
	 * This method initializes _list_anio	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_list_anio() {
		if (_list_anio == null) {
			_list_anio = new JComboBox();
			_list_anio.setFont(new Font("Dialog", Font.BOLD, 10));
			_list_anio.setSize(new Dimension(92, 17));
			_list_anio.setLocation(new Point(143, 5));
		}
		return _list_anio;
	}

	/**
	 * This method initializes _txt_fecha	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_fecha() {
		if (_txt_fecha == null) {
			_txt_fecha = new JTextField();
			_txt_fecha.setBounds(new Rectangle(7, 174, 198, 21));
			_txt_fecha.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_fecha.setHorizontalAlignment(JTextField.RIGHT);
			_txt_fecha.setForeground(Color.white);
			_txt_fecha.setBackground(Color.black);
		}
		return _txt_fecha;
	}

	/**
	 * This method initializes _btn_ok	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_ok() {
		if (_btn_ok == null) {
			_btn_ok = new JButton();
			_btn_ok.setBounds(new Rectangle(211, 173, 26, 22));
			_btn_ok.setIcon(new ImageIcon(getClass().getResource("/icons/emblem-default.png")));
		}
		return _btn_ok;
	}

	public void setJTable(JTable table){
		jTable = table;
		this.jScrollPane.setViewportView(table);
		
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(5, 27, 235, 143));
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
}  //  @jve:decl-index=0:visual-constraint="10,10"
