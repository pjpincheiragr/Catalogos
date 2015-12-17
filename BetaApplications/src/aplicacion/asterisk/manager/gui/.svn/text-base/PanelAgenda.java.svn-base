package aplicacion.asterisk.manager.gui;

import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JToolBar;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;

public class PanelAgenda extends JPanel {

	private static final long serialVersionUID = 1L;
	private JToolBar jToolBar = null;
	private JScrollPane jScrollPane = null;
	private JButton _btn_nuevo = null;
	private JButton _btn_editar = null;
	private JButton _btn_agenda = null;
	private JTable jTable = null;
	private JLabel jLabel = null;

	/**
	 * This is the default constructor
	 */
	public PanelAgenda() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
		gridBagConstraints2.gridx = 0;
		gridBagConstraints2.gridy = 1;
		jLabel = new JLabel();
		jLabel.setText("Avisos Recientes");
		jLabel.setFont(new Font("Dialog", Font.BOLD, 10));
		GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
		gridBagConstraints1.fill = GridBagConstraints.BOTH;
		gridBagConstraints1.gridy = 2;
		gridBagConstraints1.weightx = 1.0;
		gridBagConstraints1.weighty = 1.0;
		gridBagConstraints1.anchor = GridBagConstraints.CENTER;
		gridBagConstraints1.gridx = 0;
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.anchor = GridBagConstraints.NORTH;
		gridBagConstraints.ipady = -10;
		gridBagConstraints.gridx = 0;
		this.setSize(397, 251);
		this.setLayout(new GridBagLayout());
		this.add(getJToolBar(), gridBagConstraints);
		this.add(jLabel, gridBagConstraints2);
		this.add(getJScrollPane(), gridBagConstraints1);
	}

	/**
	 * This method initializes jToolBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar() {
		if (jToolBar == null) {
			jToolBar = new JToolBar();
			jToolBar.setFloatable(false);
			jToolBar.setPreferredSize(new Dimension(70, 20));
			jToolBar.add(get_btn_nuevo());
			jToolBar.add(get_btn_editar());
			jToolBar.add(get_btn_agenda());
		}
		return jToolBar;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getJTable());
		}
		return jScrollPane;
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
			_btn_nuevo.setPreferredSize(new Dimension(22, 20));
			_btn_nuevo.setToolTipText("Nuevo Aviso");
			_btn_nuevo.setSize(new Dimension(22, 20));
		}
		return _btn_nuevo;
	}

	/**
	 * This method initializes _btn_editar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_editar() {
		if (_btn_editar == null) {
			_btn_editar = new JButton();
			_btn_editar.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-run.png")));
			_btn_editar.setSize(new Dimension(22, 20));
			_btn_editar.setPreferredSize(new Dimension(22, 20));
			_btn_editar.setToolTipText("Editar Aviso Seleccionado");
		}
		return _btn_editar;
	}

	/**
	 * This method initializes _btn_agenda	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_agenda() {
		if (_btn_agenda == null) {
			_btn_agenda = new JButton();
			_btn_agenda.setIcon(new ImageIcon(getClass().getResource("/icons/calendar-16.gif")));
			_btn_agenda.setSize(new Dimension(22, 20));
			_btn_agenda.setPreferredSize(new Dimension(22, 20));
			_btn_agenda.setToolTipText("Ver mi Agenda");
		}
		return _btn_agenda;
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
	
	public void setJTable(JTable table) {
		this.jTable=table;
		this.jScrollPane.setViewportView(this.jTable);
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
