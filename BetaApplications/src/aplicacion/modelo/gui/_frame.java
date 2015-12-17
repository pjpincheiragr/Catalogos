package aplicacion.modelo.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JFrame;



public class _frame extends JFrame {
	private java.awt.Image background;

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;

	/**
	 * This is the default constructor
	 */
	public _frame() {
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
			jContentPane.setLayout(new BorderLayout());
		}
		return jContentPane;
	}

	public void updateBackground( ) {
		try {
		java.awt.Robot rbt = new java.awt.Robot( );
		Toolkit tk = Toolkit.getDefaultToolkit( );
		Dimension dim = tk.getScreenSize( );
		background = rbt.createScreenCapture(
		new java.awt.Rectangle(0,0,(int)dim.getWidth( ),
		(int)dim.getHeight( )));
		} catch (Exception ex) {
		ex.printStackTrace( );
		}
		}
	
	public void paintComponent(Graphics g) {
		Point pos = this.getLocationOnScreen( );
		Point offset = new Point(-pos.x,-pos.y);
		g.drawImage(background,offset.x,offset.y,null);
	}
	
	public static void main(String[] args){
		_frame f=new _frame();
		f.updateBackground();
		f.setVisible(true);
		f.repaint();
	}
}
