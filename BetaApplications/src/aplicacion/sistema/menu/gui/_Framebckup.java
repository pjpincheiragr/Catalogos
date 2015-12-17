package aplicacion.sistema.menu.gui;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Rectangle;
import java.net.URL;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JProgressBar;
import java.awt.Font;

public class _Framebckup extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton _btn_salir = null;
	private JLabel jLabel = null;
	private JButton _btn_error = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private JProgressBar jProgressBar = null;
	/**
	 * This is the default constructor
	 */
	public _Framebckup() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(1400, 1000);
		this.setContentPane(getJContentPane());
		this.setTitle("Beta RC 2009");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	public JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(get_btn_salir(), null);
			jContentPane.add(get_btn_error(), null);
			jContentPane.add(getJProgressBar(), null);
			
		}
		return jContentPane;
	}

	/**
	 * This method initializes _btn_salir	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_salir() {
		if (_btn_salir == null) {
			_btn_salir = new JButton();
			_btn_salir.setToolTipText("Salir de Beta");
			_btn_salir.setBounds(new Rectangle(39, 20, 26, 26));
			_btn_salir.setVisible(false);
			_btn_salir.setIcon(new ImageIcon(getClass().getResource("/icons/exit.png")));
		}
		return _btn_salir;
	}

	/**
	 * This method initializes jLabel	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	public JLabel getJLabel() {
		if (jLabel == null) {
			jLabel = new JLabel();
			jLabel.setText("");
			jLabel.setTransferHandler(null);
			jLabel.setBounds(new Rectangle(5, 5, 10, 10));
			}
		return jLabel;
	}

	/**
	 * This method initializes _btn_error	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_error() {
		if (_btn_error == null) {
			_btn_error = new JButton();
			_btn_error.setToolTipText("Envio de Error/Bug/Requerimiento a Sistemas");
			_btn_error.setBounds(new Rectangle(10, 20, 26, 26));
			_btn_error.setVisible(false);
			_btn_error.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-media-record.png")));
		}
		return _btn_error;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	public JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(500, 40, 400, 260));
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
	 * This method initializes _btn_recargar	
	 * 	
	 * @return javax.swing.JButton	
	 */

	/**
	 * This method initializes jProgressBar	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	public JProgressBar getJProgressBar() {
		if (jProgressBar == null) {
			jProgressBar = new JProgressBar();
			jProgressBar.setBounds(new Rectangle(9, 740, 609, 18));
			jProgressBar.setFont(new Font("Dialog", Font.BOLD, 10));
			jProgressBar.setToolTipText("Barra de Progreso de Carga de Aplicaciones");
			jProgressBar.setVisible(false);
		}
		return jProgressBar;
	}
	

}  //  @jve:decl-index=0:visual-constraint="10,10"
