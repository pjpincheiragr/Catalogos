package aplicacion.herramientas.java.hora.gui;

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
import javax.swing.JLabel;
import javax.swing.JToolBar;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	
	private JComboBox _list_mes = null;
	private JComboBox _list_anio = null;
	private JTextField _txt_fecha = null;
	private JButton _btn_ok = null;
	private JTable jTable = null;
	private JScrollPane jScrollPane = null;
	private JTextField _txt_hora = null;
	private JTextField _txt_minutos = null;
	private JButton _btn_hora_up = null;
	private JButton _btn_hora_down = null;
	private JLabel jLabel = null;
	private JButton _btn_minuto_up = null;
	private JButton _btn_minuto_down = null;
	private JLabel jLabel1 = null;
	private JToolBar jToolBar = null;
	private JButton _btn_cancelar = null;
	private JButton _btn_salir = null;
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
		this.setSize(251, 279);
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
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(59, 201, 39, 16));
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setText("Minuto");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(7, 201, 35, 16));
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setText("Hora");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(get_list_mes(), null);
			jContentPane.add(get_list_anio(), null);
			jContentPane.add(get_txt_fecha(), null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(get_txt_hora(), null);
			jContentPane.add(get_txt_minutos(), null);
			jContentPane.add(get_btn_hora_up(), null);
			jContentPane.add(get_btn_hora_down(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(get_btn_minuto_up(), null);
			jContentPane.add(get_btn_minuto_down(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(getJToolBar(), null);
	
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
			_list_mes.setBounds(new Rectangle(4, 29, 132, 17));
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
			_list_anio.setSize(new Dimension(96, 17));
			_list_anio.setLocation(new Point(140, 29));
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
			_txt_fecha.setBounds(new Rectangle(114, 219, 123, 21));
			_txt_fecha.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_fecha.setHorizontalAlignment(JTextField.LEFT);
			
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
			jScrollPane.setBounds(new Rectangle(4, 49, 235, 143));
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

	/**
	 * This method initializes _txt_hora	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_hora() {
		if (_txt_hora == null) {
			_txt_hora = new JTextField();
			_txt_hora.setBounds(new Rectangle(6, 220, 30, 18));
			_txt_hora.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_hora.setHorizontalAlignment(JTextField.RIGHT);
		}
		return _txt_hora;
	}

	/**
	 * This method initializes _txt_minutos	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_minutos() {
		if (_txt_minutos == null) {
			_txt_minutos = new JTextField();
			_txt_minutos.setBounds(new Rectangle(60, 220, 30, 18));
			_txt_minutos.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_minutos.setHorizontalAlignment(JTextField.RIGHT);
		}
		return _txt_minutos;
	}

	/**
	 * This method initializes _btn_hora_up	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_hora_up() {
		if (_btn_hora_up == null) {
			_btn_hora_up = new JButton();
			_btn_hora_up.setBounds(new Rectangle(37, 219, 20, 9));
			_btn_hora_up.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-media-play-up.png")));
		}
		return _btn_hora_up;
	}

	/**
	 * This method initializes _btn_hora_down	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_hora_down() {
		if (_btn_hora_down == null) {
			_btn_hora_down = new JButton();
			_btn_hora_down.setBounds(new Rectangle(37, 230, 20, 9));
			_btn_hora_down.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-media-play-down.png")));
		}
		return _btn_hora_down;
	}

	/**
	 * This method initializes _btn_minuto_up	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_minuto_up() {
		if (_btn_minuto_up == null) {
			_btn_minuto_up = new JButton();
			_btn_minuto_up.setBounds(new Rectangle(91, 220, 20, 9));
			_btn_minuto_up.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-media-play-up.png")));
		}
		return _btn_minuto_up;
	}

	/**
	 * This method initializes _btn_minuto_down	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_minuto_down() {
		if (_btn_minuto_down == null) {
			_btn_minuto_down = new JButton();
			_btn_minuto_down.setBounds(new Rectangle(91, 230, 20, 9));
			_btn_minuto_down.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-media-play-down.png")));
		}
		return _btn_minuto_down;
	}

	/**
	 * This method initializes jToolBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar() {
		if (jToolBar == null) {
			jToolBar = new JToolBar();
			jToolBar.setBounds(new Rectangle(3, 2, 245, 22));
			jToolBar.setFloatable(false);
			jToolBar.add(get_btn_ok());
			jToolBar.add(get_btn_cancelar());
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
			_btn_cancelar.setIcon(new ImageIcon(getClass().getResource("/icons/stock_calendar-24.png")));
			_btn_cancelar.setToolTipText("Fecha Actual");
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
}  //  @jve:decl-index=0:visual-constraint="10,10"
