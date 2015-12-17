package aplicacion.herramientas.conexion.login.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JProgressBar;
import java.awt.Rectangle;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

public class _splash extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JProgressBar jProgressBar = null;
	private JLabel jLabel5 = null;
	private JLabel jLabel = null;
	private JLabel jLabel6 = null;

	/**
	 * This is the default constructor
	 */
	public _splash() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		
		this.setSize(484, 270);
		this.setContentPane(getJContentPane());
		this.setTitle("Iniciando Beta RC 2010");
		this.setUndecorated(true);
	}

	
	public JLabel getJLabel(){
		if (jLabel==null){
		jLabel = new JLabel();
		jLabel.setBounds(new Rectangle(0, 0, 486, 250));
		jLabel.setText("");
		}
		return jLabel;
	}
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(4, 183, 212, 17));
			jLabel6.setForeground(Color.white);
			jLabel6.setHorizontalAlignment(SwingConstants.LEFT);
			jLabel6.setText("Iniciando Beta...");
			jLabel6.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(267, 14, 202, 27));
			jLabel5.setForeground(Color.white);
			jLabel5.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel5.setText("Beta RC 2010");
			jLabel5.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJProgressBar(), null);
			jContentPane.add(jLabel5, null);
			jContentPane.add(this.getJLabel(), null);
			jContentPane.add(jLabel6, null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jProgressBar	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	public JProgressBar getJProgressBar() {
		if (jProgressBar == null) {
			jProgressBar = new JProgressBar();
			jProgressBar.setBounds(new Rectangle(0, 252, 484, 18));
		}
		return jProgressBar;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
