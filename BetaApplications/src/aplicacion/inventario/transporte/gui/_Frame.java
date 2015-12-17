package aplicacion.inventario.transporte.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Rectangle;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JToolBar;


import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import java.awt.Font;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel jContentPane = null;
	private JPanel jPanel = null;
	private JLabel jLabel = null;
	private JTextField _txt_idtransporte = null;
	private JTextField _txt_transporte_descripcion = null;
	private JButton _btn_buscar_transporte = null;
	private JToolBar jToolBar = null;
	private JButton _btn_guardar = null;
	private JButton _btn_cancelar = null;
	private JButton _btn_salir = null;
	private JButton _btn_cargar = null;
	private JButton _btn_error = null;

	private JButton _btn_nuevo = null;

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
		this.setSize(616, 179);
		this.setContentPane(getJContentPane());
		this.setTitle("Transporte");
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
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(12, 15, 100, 17));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setText("Transporte");
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBounds(new Rectangle(10, 35, 593, 100));
			jPanel.setBackground(Color.lightGray);
			jPanel.add(jLabel, null);
			jPanel.add(get_txt_idtransporte(), null);
			jPanel.add(get_txt_transporte_descripcion(), null);
			jPanel.add(get_btn_buscar_transporte(), null);
			jPanel.add(get_btn_cargar(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes _txt_idtransporte
	 * 
	 * @return javax.swing.JTextField
	 */
	public JTextField get_txt_idtransporte() {
		if (_txt_idtransporte == null) {
			_txt_idtransporte = new JTextField();
			_txt_idtransporte.setBounds(new Rectangle(118, 16, 96, 17));
			_txt_idtransporte.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_idtransporte;
	}

	/**
	 * This method initializes _txt_transporte_descripcion
	 * 
	 * @return javax.swing.JTextField
	 */
	public JTextField get_txt_transporte_descripcion() {
		if (_txt_transporte_descripcion == null) {
			_txt_transporte_descripcion = new JTextField();
			_txt_transporte_descripcion
					.setBounds(new Rectangle(269, 16, 223, 17));
			_txt_transporte_descripcion.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_transporte_descripcion;
	}

	/**
	 * This method initializes _btn_buscar_transporte
	 * 
	 * @return javax.swing.JButton
	 */
	public JButton get_btn_buscar_transporte() {
		if (_btn_buscar_transporte == null) {
			_btn_buscar_transporte = new JButton();
			_btn_buscar_transporte.setBounds(new Rectangle(218, 14, 21, 21));
			_btn_buscar_transporte.setToolTipText("Buscar Transporte");
			_btn_buscar_transporte.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-searchtool.png")));
		}
		return _btn_buscar_transporte;
	}

	/**
	 * This method initializes jToolBar
	 * 
	 * @return javax.swing.JToolBar
	 */
	private JToolBar getJToolBar() {
		if (jToolBar == null) {
			jToolBar = new JToolBar();
			jToolBar.setBounds(new Rectangle(7, 6, 598, 23));
			jToolBar.setFloatable(false);
			jToolBar.add(get_btn_cancelar());
			jToolBar.add(get_btn_nuevo());
			jToolBar.add(get_btn_guardar());
			jToolBar.addSeparator();
			jToolBar.add(get_btn_error());
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
			_btn_guardar.setToolTipText("Guardar Cambios");
			URL resourceURL = getClass().getResource("/icons/filesave.png");
			_btn_guardar.setIcon(new ImageIcon(resourceURL));
		}
		return _btn_guardar;
	}

	/**
	 * This method initializes _btn_cancelar
	 * 
	 * @return javax.swing.JButton
	 */
	public JButton get_btn_cancelar() {
		if (_btn_cancelar == null) {
			_btn_cancelar = new JButton();

			_btn_cancelar.setToolTipText("Cancelar");
			_btn_cancelar.setBounds(new Rectangle(647, 190, 28, 18));
			URL resourceURL = getClass().getResource("/icons/stock_calc-cancel.png");
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
			URL resourceURL = getClass().getResource("/icons/exit.png");
			_btn_salir.setIcon(new ImageIcon(resourceURL));
			_btn_salir.setToolTipText("Salir de aplicacion");
		}
		return _btn_salir;
	}

	/**
	 * This method initializes _btn_cargar
	 * 
	 * @return javax.swing.JButton
	 */
	public JButton get_btn_cargar() {
		if (_btn_cargar == null) {
			_btn_cargar = new JButton();
			_btn_cargar.setBounds(new Rectangle(242, 14, 21, 21));
			_btn_cargar.setToolTipText("Cargar Transporte");
			
			_btn_cargar.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-refresh.png")));
			_btn_cargar.setToolTipText("Cargar");
		}
		return _btn_cargar;
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


	/**
	 * This method initializes _btn_nuevo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_nuevo() {
		if (_btn_nuevo == null) {
			_btn_nuevo = new JButton();
			_btn_nuevo.setIcon(new ImageIcon(getClass().getResource("/icons/document-new.png")));
			_btn_nuevo.setToolTipText("Crear Nuevo Transporte");
		}
		return _btn_nuevo;
	}

	
} // @jve:decl-index=0:visual-constraint="10,10"
