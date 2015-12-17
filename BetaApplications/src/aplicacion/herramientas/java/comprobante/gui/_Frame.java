package aplicacion.herramientas.java.comprobante.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JToolBar;
import java.awt.Rectangle;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JProgressBar;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JToolBar _toolbar_header = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private JButton _btn_buscar = null;
	private JButton _btn_salir = null;
	private JScrollPane jScrollPane1 = null;
	private JTable jTable1 = null;
	private JProgressBar jProgressBar = null;
	private JButton _btn_editar = null;
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
		this.setSize(587, 351);
		this.setContentPane(getJContentPane());
		this.setTitle("Buscador");
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
			jContentPane.add(get_toolbar_header(), null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(getJScrollPane1(), null);
			jContentPane.add(getJProgressBar(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes _toolbar_header	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	public JToolBar get_toolbar_header() {
		if (_toolbar_header == null) {
			_toolbar_header = new JToolBar();
			_toolbar_header.setBounds(new Rectangle(0, 0, 578, 24));
			_toolbar_header.setFloatable(false);
			_toolbar_header.add(get_btn_buscar());
			_toolbar_header.add(get_btn_editar());
			_toolbar_header.add(get_btn_salir());
		}
		return _toolbar_header;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	public JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(4, 111, 570, 200));
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
			jTable.setFont(new Font("Dialog", Font.PLAIN, 12));
		}
		return jTable;
	}

	public void setJTable(JTable table){
		final JTable tablex=table;
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				jTable=tablex;
				jScrollPane.setViewportView(jTable);		
			}
		});
	}
	/**
	 * This method initializes _btn_buscar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar() {
		if (_btn_buscar == null) {
			_btn_buscar = new JButton();
			_btn_buscar.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-refresh.png")));
			_btn_buscar.setToolTipText("Actualizar");
		}
		return _btn_buscar;
	}

	/**
	 * This method initializes _btn_salir	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_salir() {
		if (_btn_salir == null) {
			_btn_salir = new JButton();
			_btn_salir.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-logout.png")));
		}
		return _btn_salir;
	}

	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	public JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setBounds(new Rectangle(2, 41, 574, 60));
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
	 * This method initializes jProgressBar	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	public JProgressBar getJProgressBar() {
		if (jProgressBar == null) {
			jProgressBar = new JProgressBar();
			jProgressBar.setBounds(new Rectangle(0, 25, 574, 14));
		}
		return jProgressBar;
	}

	/**
	 * This method initializes _btn_editar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_editar() {
		if (_btn_editar == null) {
			_btn_editar = new JButton();
			_btn_editar.setIcon(new ImageIcon(getClass().getResource("/icons/edit-find-replace.png")));
			_btn_editar.setToolTipText("Editar en su Interfaz original");
		}
		return _btn_editar;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
