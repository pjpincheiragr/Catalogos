package aplicacion.herramientas.java;

import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Rectangle;

public class _Date extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private DateField fecha = null;

	/**
	 * This method initializes fecha	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getFecha() {
		if (fecha == null) {
			fecha = new DateField("dd-MM-yyyy");
			fecha.setBounds(new Rectangle(58, 62, 123, 19));
		}
		return fecha;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				_Date thisClass = new _Date();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public _Date() {
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
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getFecha(), null);
		}
		return jContentPane;
	}

}
