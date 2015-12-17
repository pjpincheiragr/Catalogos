package aplicacion.inventario.politica.gui;

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
	private JTextField _txt_idpolitica = null;
	private JTextField _txt_politica_descripcion = null;
	private JButton _btn_buscar_politica = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JTextField _txt_formula_costo = null;
	private JTextField _txt_formula_publico = null;
	private JToolBar jToolBar = null;
	private JButton _btn_guardar = null;
	private JButton _btn_cancelar = null;
	private JButton _btn_salir = null;
	private JButton _btn_aplicar = null;
	private JProgressBar PBar = null;
	private JButton _btn_cancelar_tarea = null;
	private JButton _btn_cargar = null;
	private JLabel jLabel3 = null;
	private JTextField _txt_mcosto = null;
	private JTextField _txt_mpublico = null;

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
		this.setSize(621, 304);
		this.setContentPane(getJContentPane());
		this.setTitle("Politica");
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
			jLabel3.setBounds(new Rectangle(290, 68, 99, 15));
			jLabel3.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel3.setText("Multipplicador");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(12, 112, 100, 20));
			jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel2.setText("Formula Publico");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(12, 86, 100, 19));
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setText("Formula Costo");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(12, 15, 100, 17));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setText("Politica");
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBounds(new Rectangle(10, 35, 592, 206));
			jPanel.setBackground(Color.lightGray);
			jPanel.add(jLabel, null);
			jPanel.add(get_txt_idpolitica(), null);
			jPanel.add(get_txt_politica_descripcion(), null);
			jPanel.add(get_btn_buscar_politica(), null);
			jPanel.add(jLabel1, null);
			jPanel.add(jLabel2, null);
			jPanel.add(get_txt_formula_costo(), null);
			jPanel.add(get_txt_formula_publico(), null);
			jPanel.add(get_btn_aplicar(), null);
			jPanel.add(getJProgressBar(), null);
			jPanel.add(get_btn_cancelar_tarea(), null);
			jPanel.add(get_btn_cargar(), null);
			jPanel.add(jLabel3, null);
			jPanel.add(get_txt_mcosto(), null);
			jPanel.add(get_txt_mpublico(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes _txt_idpolitica
	 * 
	 * @return javax.swing.JTextField
	 */
	public JTextField get_txt_idpolitica() {
		if (_txt_idpolitica == null) {
			_txt_idpolitica = new JTextField();
			_txt_idpolitica.setBounds(new Rectangle(118, 16, 96, 17));
			_txt_idpolitica.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_idpolitica;
	}

	/**
	 * This method initializes _txt_politica_descripcion
	 * 
	 * @return javax.swing.JTextField
	 */
	public JTextField get_txt_politica_descripcion() {
		if (_txt_politica_descripcion == null) {
			_txt_politica_descripcion = new JTextField();
			_txt_politica_descripcion
					.setBounds(new Rectangle(269, 16, 223, 17));
			_txt_politica_descripcion.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_politica_descripcion;
	}

	/**
	 * This method initializes _btn_buscar_politica
	 * 
	 * @return javax.swing.JButton
	 */
	public JButton get_btn_buscar_politica() {
		if (_btn_buscar_politica == null) {
			_btn_buscar_politica = new JButton();
			_btn_buscar_politica.setBounds(new Rectangle(218, 14, 21, 21));
			_btn_buscar_politica.setToolTipText("Buscar Politica");
			_btn_buscar_politica.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-searchtool.png")));
		}
		return _btn_buscar_politica;
	}

	/**
	 * This method initializes _txt_formula_costo
	 * 
	 * @return javax.swing.JTextField
	 */
	public JTextField get_txt_formula_costo() {
		if (_txt_formula_costo == null) {
			_txt_formula_costo = new JTextField();
			_txt_formula_costo.setBounds(new Rectangle(118, 87, 157, 18));
			_txt_formula_costo.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_formula_costo.setToolTipText("formula siguiendo modelo: <precio5>%-desc1%-desc2...");
		}
		return _txt_formula_costo;
	}

	/**
	 * This method initializes _txt_formula_publico
	 * 
	 * @return javax.swing.JTextField
	 */
	public JTextField get_txt_formula_publico() {
		if (_txt_formula_publico == null) {
			_txt_formula_publico = new JTextField();
			_txt_formula_publico.setBounds(new Rectangle(118, 110, 157, 18));
			_txt_formula_publico.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_formula_publico;
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
			jToolBar.add(get_btn_nuevo());
			jToolBar.add(get_btn_guardar());
			jToolBar.add(get_btn_cancelar());
			jToolBar.add(get_btn_salir());
			jToolBar.addSeparator();
			jToolBar.add(get_btn_error());
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

			_btn_cancelar.setToolTipText("Limpiar Aplicacion");
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
	 * This method initializes _btn_aplicar
	 * 
	 * @return javax.swing.JButton
	 */
	public JButton get_btn_aplicar() {
		if (_btn_aplicar == null) {
			_btn_aplicar = new JButton();
			_btn_aplicar.setBounds(new Rectangle(444, 174, 21, 21));
			_btn_aplicar.setToolTipText("Aplicar Cambios a Articulos que utilicen esta politica");
			_btn_aplicar.setIcon(new ImageIcon(getClass().getResource("/icons/player_play.png")));
			
			
		}
		return _btn_aplicar;
	}

	

	/**
	 * This method initializes PBar
	 * 
	 * @return javax.swing.JProgressBar
	 */
	public JProgressBar getJProgressBar() {
		if (PBar == null) {
			PBar = new JProgressBar();
			PBar.setBounds(new Rectangle(21, 174, 420, 21));
			PBar.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return PBar;
	}

	/**
	 * This method initializes _btn_cancelar_tarea
	 * 
	 * @return javax.swing.JButton
	 */
	public JButton get_btn_cancelar_tarea() {
		if (_btn_cancelar_tarea == null) {
			_btn_cancelar_tarea = new JButton();
			_btn_cancelar_tarea.setBounds(new Rectangle(470, 172, 24, 25));
			_btn_cancelar_tarea
					.setToolTipText("Cancelar Operacion en Progreso");
			URL resourceURL = getClass().getResource(
			"/icons/stock_calc-cancel.png");
			_btn_cancelar_tarea.setIcon(new ImageIcon(resourceURL));
		}
		return _btn_cancelar_tarea;
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
			_btn_cargar.setToolTipText("Cargar Politica");
			
			_btn_cargar.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-refresh.png")));
			_btn_cargar.setToolTipText("Cargar");
		}
		return _btn_cargar;
	}


	/**
	 * This method initializes _txt_mcosto	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_mcosto() {
		if (_txt_mcosto == null) {
			_txt_mcosto = new JTextField();
			_txt_mcosto.setBounds(new Rectangle(301, 88, 60, 18));
			_txt_mcosto.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_mcosto.setEditable(false);
		}
		return _txt_mcosto;
	}

	/**
	 * This method initializes _txt_mpublico	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_mpublico() {
		if (_txt_mpublico == null) {
			_txt_mpublico = new JTextField();
			_txt_mpublico.setBounds(new Rectangle(302, 111,60, 18));
			_txt_mpublico.setFont(new Font("Dialog", Font.PLAIN, 10));
			_txt_mpublico.setEditable(false);
		}
		return _txt_mpublico;
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
			_btn_nuevo.setToolTipText("Nueva Politica");
		}
		return _btn_nuevo;
	}

	
} // @jve:decl-index=0:visual-constraint="10,10"
