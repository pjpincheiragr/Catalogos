package aplicacion.asterisk.manager.logic;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;

import org.asteriskjava.manager.ManagerConnection;
import org.asteriskjava.manager.ManagerConnectionFactory;

import aplicacion.asterisk.manager.events._ManagerEventListener;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException; /*
 import aplicacion.asterisk.manager.interfaces.*;
 import javax.swing.JFrame;
 import javax.swing.JCheckBox;
 import org.asteriskjava.manager.ManagerConnection;
 import org.asteriskjava.manager.ManagerConnectionFactory;
 import org.asteriskjava.manager.ManagerEventListener;
 import org.asteriskjava.manager.action.StatusAction;
 import org.asteriskjava.manager.event.ManagerEvent;
 import org.asteriskjava.manager.event.StatusCompleteEvent;
 import aplicacion.asterisk.manager.events.*;
 import aplicacion.herramientas.java.table.CheckBoxCellEditor;
 import javax.swing.ImageIcon;
 import aplicacion.asterisk.manager.logic.ButtonEditor;
 */
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;

import org.asteriskjava.manager.AuthenticationFailedException;

import org.asteriskjava.manager.TimeoutException;
import aplicacion.herramientas.conexion.conectores.MySQL;
import org.asteriskjava.manager.event.NewExtenEvent;

import aplicacion.asterisk.manager.gui.PanelAgenda;
import aplicacion.asterisk.manager.interfaces._Interface;
import aplicacion.asterisk.manager.logic._Data;
import aplicacion.asterisk.manager.logic._Error._taskEnviar;

import aplicacion.herramientas.java.Crono;
import aplicacion.herramientas.java.launcher.logic.SwingWorker;
import aplicacion.herramientas.java.table.CellEditor;
import aplicacion.herramientas.java.table.Column;
import aplicacion.herramientas.java.table.CustomTable;
import aplicacion.modelo.logic.Data;
import aplicacion.modelo.logic.Logic;

public class _Agenda extends Logic {

	private _Data data = null;
	private PanelAgenda agenda = null;
	
	public _Agenda() {

	}
	
	public void init(){
		try {
			
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	
	
	public void setFrame(PanelAgenda panel) {
		this.agenda = (PanelAgenda) panel;
	}

	public void setData(Data data) {
		this.data = (_Data) data;
		super.setData(this.data);
	}

	protected void centrar_frame(JFrame frame) {
		Dimension d = frame.getSize();
		Dimension s = Toolkit.getDefaultToolkit().getScreenSize();
		double w = s.getWidth();
		double h = s.getHeight() - 30;
		double wf = d.getWidth();
		double hf = d.getHeight();
		int x = new Double(w - wf).intValue();
		int y = new Double(h - hf).intValue();
		frame.setLocation(x, y);
	}

	
	public void show(Object[][] results) {
		try {
			this.getConstructor().getFrame().setAlwaysOnTop(true);
			this.getConstructor().getFrame().setVisible(true);
			this.getConstructor().getFrame().toFront();
			this.getConstructor().getFrame().requestFocus();
			this.getConstructor().getFrame().setAlwaysOnTop(false);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			JTabbedPane tabbed = (JTabbedPane) agenda.getParent();
			tabbed.setSelectedComponent(agenda);
			
			_Logic logic = (_Logic) this.getConstructor().getLogic();
			logic.show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		boolean ok = false;
		if (results != null) {
			if (results.length > 0) {
				ok = true;
			}
		}
		if (!ok) {
			results = new Object[][] { { "", "" } };
		}
		final Object[][] _results = results;
		Runnable _execute = new Runnable() {
			public void run() {
				boolean ok = false;
				create_table(_results);

			}
		};
		this.invokeLater(_execute);
	}



	

	public void cargar_historial() {
		
		
		Runnable _execute = new Runnable() {
			public void run() {
				_cargar_historial();
			}
		};
		this.invokeLater(_execute);
	}

	public void _cargar_historial() {
		String idhost=data.getHost();
		Object[][] results = data.getHistorial(idhost);
		boolean ok = false;
		if (results != null) {
			if (results.length > 0) {
				this.create_table_history(results);
				ok = true;
			}
		}
		if (!ok) {
			agenda.setJTable(null);
		}

	}

	private void create_table(Object[][] results) {
		CustomTable table = new CustomTable();
		Column col = new Column();

		col = new Column();
		col.setName("cuenta");
		col.setWidth(80);
		col.setEditable(true);
		col.setClass(String.class);
		CellEditor pce = new CellEditor();
		pce.setAligment(JTextField.RIGHT);
		pce.addKeyListener(this._constructor.getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_idcuenta);
		pce.setTipo(String.class);

		col.setCellEditor(pce.getCellEditor());
		table.addColumn(col);

		col = new Column();
		col.setName("Descripcion");
		col.setWidth(120);
		col.setEditable(false);
		table.addColumn(col);

		/*
		 * col = new Column(); col.setName(""); col.setClass(Boolean.class);
		 * col.setWidth(26); col.setEditable(true); ButtonEditor chkce = new
		 * ButtonEditor(new JCheckBox()); col.setCellEditor(chkce);
		 * col.setCellRenderer(new ButtonRenderer()); table.addColumn(col);
		 */

		// results=this.processData(results);
		table.setData(results);
		table.setName(_Interface._table_cuentas);
		table.addKeyListener(this._constructor.getKeyListener());
		table.addMouseListener(this._constructor.getMouseListener());
		Font fuente = new Font("Arial", Font.PLAIN, 10);
		table.setHeaderFont(fuente);
		table.build();
		table.fillData();
		agenda.setJTable(table.getTable());
		this.focus(0);

	}
	
	public boolean _guardar(String callerid){
		boolean ok=false;
		data.beginTransaction();
		String q=data.getDelete(callerid);
		data.clearBatch();
		data.addBatch(q);
		for (int i=0;i<agenda.getJTable().getRowCount();i++){
			String idcuenta="";
			try {
				idcuenta = agenda.getJTable().getValueAt(i, 0).toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (idcuenta!=null){
				if (idcuenta.compareTo("")!=0){
					q=data.getInsertCallerId(callerid, idcuenta);
					data.addBatch(q);	
				}	
			}
			
			
		}
		ok=!data.executeBatch();
		if (ok){
			data.commitTransaction();
			aviso("Se grabo Correctamente");
		}else{
			data.rollbackTransaction();
			error("Error Grabando");
		}
		return ok;
	}
	
	

	private void create_table_history(Object[][] results) {
		CustomTable table = new CustomTable();
		Column col = new Column();

		col = new Column();
		col.setName("aviso");
		col.setWidth(50);
		col.setAligment(JTextField.RIGHT);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("iduser");
		col.setWidth(70);
		col.setEditable(false);
		table.addColumn(col);

		col = new Column();
		col.setName("titulo");
		col.setWidth(240);
		col.setEditable(false);
		table.addColumn(col);

		col = new Column();
		col.setName("Agenda");
		col.setWidth(120);
		col.setEditable(false);
		table.addColumn(col);

		

		table.setData(results);
		table.setName(_Interface._table_messages);
		table.addKeyListener(this._constructor.getKeyListener());
		table.addMouseListener(this._constructor.getMouseListener());
		Font fuente = new Font("Arial", Font.PLAIN, 10);
		table.setHeaderFont(fuente);
		table.build();
		table.fillData();
		agenda.setJTable(table.getTable());

	}

	public void focus(int row) {
		try {
			agenda.getJTable().getCellEditor().stopCellEditing();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		agenda.getJTable().changeSelection(row, 0, false, false);

	}


	public void editCell(int row, int col) {
		JTable table = agenda.getJTable();
		table.changeSelection(row, col, false, false);
		table.editCellAt(row, col);
		table.transferFocus();
	}



	
}
