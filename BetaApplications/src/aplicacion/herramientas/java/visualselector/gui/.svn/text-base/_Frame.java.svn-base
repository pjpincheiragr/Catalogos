package aplicacion.herramientas.java.visualselector.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import java.awt.Rectangle;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.GridBagLayout;
import aplicacion.herramientas.java.visualfx.*;
import java.awt.event.*;
public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private TranslucentPanel jContentPane = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	

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
		this.setUndecorated(true);
		this.setSize(466, 171);
		this.setContentPane(getJContentPane());
		this.setResizable(false);
		
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	public TranslucentPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new TranslucentPanel();
			jContentPane.setLayout(null);
			jContentPane.setAlfa(0.6f);
			jContentPane.add(getJScrollPane(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	public JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(0, 0, 465, 331));
			jScrollPane.setViewportView(getJTable());
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
	



}  //  @jve:decl-index=0:visual-constraint="10,10"
