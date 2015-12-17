package aplicacion.asterisk.manager.logic;

import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;

public class DynamicFrame extends JFrame {
	private String idaviso="";  //  @jve:decl-index=0:
	private String iduser="";
	
	
	public String getIdAviso(){
		return this.idaviso;
	}
	public String getIdUser(){
		return this.iduser;
	}
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	public JLabel _txt_mensaje = null;
	public JButton _btn_aceptar = null;
	private JLabel jLabel = null;
	private JButton _btn_ver = null;
	private JPanel jPanel = null;

	/**
	 * This is the default constructor
	 */
	public DynamicFrame() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setResizable(false);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/chrome.png")));
		this.setContentPane(getJContentPane());
		this.setTitle("Aviso");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 1;
			gridBagConstraints4.gridy = 4;
			gridBagConstraints4.ipadx = 30;
			gridBagConstraints4.ipady = 30;
			gridBagConstraints4.fill = GridBagConstraints.BOTH;
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 0;
			gridBagConstraints11.ipady = 10;
			gridBagConstraints11.gridy = 4;
			jLabel = new JLabel();
			jLabel.setText("");
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.ipady = 30;
			gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints.gridwidth = 2;
			gridBagConstraints.gridy = 0;
			_txt_mensaje = new JLabel();
			_txt_mensaje.setText("JLabel");
			_txt_mensaje.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
			_txt_mensaje.setHorizontalAlignment(SwingConstants.CENTER);
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(_txt_mensaje, gridBagConstraints);
			jContentPane.add(jLabel, gridBagConstraints11);
			jContentPane.add(getJPanel(), gridBagConstraints4);
		}
		return jContentPane;
	}

	/**
	 * This method initializes _btn_aceptar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_aceptar() {
		if (_btn_aceptar == null) {
			_btn_aceptar = new JButton("Aceptar");
			_btn_aceptar.setFont(new Font("Dialog", Font.BOLD, 10));
		}
		return _btn_aceptar;
	}
	public String getIdaviso() {
		return idaviso;
	}
	public String getIduser() {
		return iduser;
	}
	public void setIdaviso(String idaviso) {
		this.idaviso = idaviso;
	}
	public void setIduser(String iduser) {
		this.iduser = iduser;
	}
	/**
	 * This method initializes _btn_ver	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_ver() {
		if (_btn_ver == null) {
			_btn_ver = new JButton("Ver");
			_btn_ver.setFont(new Font("Dialog", Font.BOLD, 10));
		}
		return _btn_ver;
	}
	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 1;
			gridBagConstraints2.gridy = 0;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.ipady = 0;
			gridBagConstraints1.gridy = 0;
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
			jPanel.add(get_btn_aceptar(), gridBagConstraints1);
			jPanel.add(get_btn_ver(), gridBagConstraints2);
		}
		return jPanel;
	}

}
