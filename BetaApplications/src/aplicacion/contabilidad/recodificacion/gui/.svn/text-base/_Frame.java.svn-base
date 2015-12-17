package aplicacion.contabilidad.recodificacion.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JTextField _txt_idoperacion = null;
	private JLabel jLabel1 = null;
	private JTextField _txt_fecha = null;
	private JButton _btn_fecha = null;
	private JButton _btn_buscar = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private JScrollPane jScrollPane1 = null;
	private JTable jTable1 = null;
	private JToolBar jToolBar = null;
	private JButton _btn_cancelar = null;
	private JButton _btn_eliminar = null;
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
		this.setSize(709, 498);
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(438, 37, 88, 16));
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setText("Fecha");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(15, 38, 105, 17));
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setText("idoperacion");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(get_txt_idoperacion(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(get_txt_fecha(), null);
			jContentPane.add(get_btn_fecha(), null);
			jContentPane.add(get_btn_buscar(), null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(getJScrollPane1(), null);
			jContentPane.add(getJToolBar(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes _txt_idoperacion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField get_txt_idoperacion() {
		if (_txt_idoperacion == null) {
			_txt_idoperacion = new JTextField();
			_txt_idoperacion.setBounds(new Rectangle(124, 37, 139, 18));
		}
		return _txt_idoperacion;
	}

	/**
	 * This method initializes _txt_fecha	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField get_txt_fecha() {
		if (_txt_fecha == null) {
			_txt_fecha = new JTextField();
			_txt_fecha.setBounds(new Rectangle(530, 37, 125, 17));
		}
		return _txt_fecha;
	}

	/**
	 * This method initializes _btn_fecha	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton get_btn_fecha() {
		if (_btn_fecha == null) {
			_btn_fecha = new JButton();
			_btn_fecha.setBounds(new Rectangle(655, 36, 22, 19));
			_btn_fecha.setIcon(new ImageIcon(getClass().getResource("/icons/calendar-16.gif")));
		}
		return _btn_fecha;
	}

	/**
	 * This method initializes _btn_buscar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton get_btn_buscar() {
		if (_btn_buscar == null) {
			_btn_buscar = new JButton();
			_btn_buscar.setBounds(new Rectangle(265, 36, 23, 18));
			_btn_buscar.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-searchtool.png")));
		}
		return _btn_buscar;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(14, 61, 672, 117));
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
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setBounds(new Rectangle(15, 224, 672, 133));
			jScrollPane1.setViewportView(getJTable1());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes jTable1	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTable1() {
		if (jTable1 == null) {
			jTable1 = new JTable();
		}
		return jTable1;
	}

	/**
	 * This method initializes jToolBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar() {
		if (jToolBar == null) {
			jToolBar = new JToolBar();
			jToolBar.setBounds(new Rectangle(2, 2, 695, 22));
			jToolBar.setFloatable(false);
			jToolBar.add(get_btn_cancelar());
			jToolBar.add(get_btn_eliminar());
			jToolBar.add(get_btn_salir());
		}
		return jToolBar;
	}

	/**
	 * This method initializes _btn_cancelar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton get_btn_cancelar() {
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
	private JButton get_btn_eliminar() {
		if (_btn_eliminar == null) {
			_btn_eliminar = new JButton();
			_btn_eliminar.setIcon(new ImageIcon(getClass().getResource("/icons/edit-delete.png")));
		}
		return _btn_eliminar;
	}

	/**
	 * This method initializes _btn_salir	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton get_btn_salir() {
		if (_btn_salir == null) {
			_btn_salir = new JButton();
			_btn_salir.setIcon(new ImageIcon(getClass().getResource("/icons/exit.png")));
		}
		return _btn_salir;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
