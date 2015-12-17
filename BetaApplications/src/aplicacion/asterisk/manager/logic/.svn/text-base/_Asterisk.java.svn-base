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

import aplicacion.asterisk.manager.gui.PanelAsterisk;
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

public class _Asterisk extends Logic {

	private ManagerConnection managerConnection;
	private String callerid = "";
	private String host = "192.168.4.114";
	private String user = "admin";
	private String password = "amp111";
	private String myuser = "freepbx";
	private String mypassword = "fpbx";
	private String mydatabase = "asteriskcdrdb";
	private _ManagerEventListener managerEventListener = null;
	private _Data data = null;
	private PanelAsterisk asterisk = null;
	private String extension = "";
	private Timer Timer;  //  @jve:decl-index=0:
	private Crono crono;
	private String estado="";
	private int current;
	private int lenght;
	private boolean done,canceled;
	private int errors=0;
	
	public _Asterisk() {

	}
	public MySQL getMySQL(){
		MySQL mysql=new MySQL(null);
		mysql.setHost(this.host);
		int _port=new Integer(3306);
		mysql.setId("Asterisk");
		mysql.setPort(_port);
		mysql.setUser(myuser);
		mysql.setPassword(mypassword);
		mysql.setDatabase(mydatabase);
		mysql.connect();
		return mysql;
	}
	public void init(){
		try {
			
			managerEventListener = new _ManagerEventListener();
			managerEventListener.setLogic(this);

			ManagerConnectionFactory factory = new ManagerConnectionFactory(
					host, user, password);
			managerConnection = factory.createManagerConnection();
			managerConnection.addEventListener(managerEventListener);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void connect_cdr(){
		MySQL mysql=this.getMySQL();
		data.getConnectionHandler().addConector(mysql);
	}
	
	public void load_user(){
		host=(String) data.getParametro("ask_host")[0][1];
		user=(String) data.getParametro("ask_user")[0][1];
		password=(String) data.getParametro("ask_pass")[0][1];
	}
	
	public void setFrame(PanelAsterisk panel) {
		this.asterisk = (PanelAsterisk) panel;
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

	public void select(int row) {
		if (row >= 0) {
			asterisk.getJTabbedPane().setSelectedIndex(0);
			String callerid = asterisk.getJTable1().getValueAt(row, 1)
					.toString();
			asterisk.get_txt_callerid().setText(callerid);
			Object[][] results = data.getCuenta(callerid);
			this.show(results);
			this.cargar_historial(extension);
		}

	}

	public void login() {
		// register for events

		boolean login = true;
		if (login) {
			// connect to Asterisk and log in
			try {
				managerConnection.login();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			} catch (AuthenticationFailedException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			} catch (TimeoutException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
		}
		extension = data.getExtension();
		asterisk.get_txt_extension().setText(extension);
		this.cargar_historial(extension);

	}

	public void disconnect() {
		try {
			managerConnection.logoff();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void evalCall(NewExtenEvent custom) {
		if (extension.compareTo("") == 0) {
			extension = data.getExtension();
		}
		 System.out.println("Extension:"+custom.getExtension()+" Channel:"+custom.getChannel()+" Context:"+custom.getContext()+" AppData:"+custom.getAppData()+" Application:"+custom.getApplication()+" Source:"+custom.getSource());

		if (custom.getChannel().contains("DAHDI")) {
			if (custom.getExtension().compareTo(extension) == 0) {

				String appdata = custom.getAppData();
				String channel = custom.getChannel();

				this.load_callerid(callerid, extension, channel);

			}
			if (custom.getContext().contains("macro-user-callerid")) {
				String data = custom.getAppData();
				if (data.contains("AMPUSER")) {
					int index = data.indexOf("AMPUSER=") + "AMPUSER=".length();
					data = data.substring(index);
					// int end=data.indexOf(" ");
					System.out.println("Calling number:" + callerid);
					// frame.get_txt_callerid().setText(callerid);
					// this.evaluate_callerid(callerid);
				}

			}
			if (custom.getContext().contains("from-pstn")) {
				// Context:from-pstn AppData:1 |Set|CALLERID(name)=5358237
				String data = custom.getAppData();
				if (data.contains("1 |Set|CALLERID(name)=")) {
					int index = data.indexOf("1 |Set|CALLERID(name)=")
							+ "1 |Set|CALLERID(name)=".length();
					data = data.substring(index, data.length());
					System.out.println("Calling number:" + data);
					callerid = data;
					// frame.get_txt_callerid().setText(callerid);
					// this.evaluate_callerid(callerid);
				}
			}
		}
		if (custom.getChannel().contains("SIP")) {
			if (custom.getContext().contains("macro-user-callerid")) {
				String data = custom.getAppData();
				if (data.contains("AMPUSER")) {
					String channel = custom.getChannel();
					int index = data.indexOf("AMPUSER=") + "AMPUSER=".length();
					data = data.substring(index);
					// int end=data.indexOf(" ");
					if (data.contains("CID=")) {
						callerid = data.replaceAll("CID=", "");
						this.load_callerid(callerid, extension, channel);
					}

				}

			}
		}
		if (custom.getChannel().contains("LOCAL")) {
			if (custom.getContext().contains("macro-user-callerid")) {
				String data = custom.getAppData();
				if (data.contains("AMPUSER")) {
					String channel = custom.getChannel();
					int index = data.indexOf("AMPUSER=") + "AMPUSER=".length();
					data = data.substring(index);
					// int end=data.indexOf(" ");

					if (data.contains("CID=")) {

						callerid = data.replaceAll("CID=", "");
						this.load_callerid(callerid, extension, channel);
					}

				}

			}
		}
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
			JTabbedPane tabbed = (JTabbedPane) asterisk.getParent();
			tabbed.setSelectedComponent(asterisk);
			asterisk.getJTabbedPane().setSelectedIndex(0);
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

	public void load_callerid(String id, String extension, String channel) {
		boolean ok = data.isCallRegistered(id, extension, channel);
		if (!ok) {
			data.register_call(callerid, extension, channel);
			System.out.println("You Are Recieving a Call from " + callerid
					+ " channel " + channel);
		}
		asterisk.get_txt_callerid().setText(id);

		Object[][] results = data.getCuenta(id);
		this.show(results);
		this.goHistory();
		

	}

	public void cargar_historial() {
		System.out.println("Cargar Historial de " + extension);
		this.cargar_historial(extension);
		done=true;
	}

	public void cargar_historial(String extension) {
		
		final String _extension = extension;
		Runnable _execute = new Runnable() {
			public void run() {
				_cargar_historial(_extension);
			}
		};
		this.invokeLater(_execute);
	}

	public void _cargar_historial(String extension) {
		Object[][] results = data.getExtensionHistory(extension);
		boolean ok = false;
		if (results != null) {
			if (results.length > 0) {
				this.create_table_history(results);
				ok = true;
			}
		}
		if (!ok) {
			asterisk.setJTable1(null);
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
		asterisk.setJTable(table.getTable());
		this.focus(0);

	}
	
	public boolean _guardar(String callerid){
		boolean ok=false;
		data.beginTransaction();
		String q=data.getDelete(callerid);
		data.clearBatch();
		data.addBatch(q);
		for (int i=0;i<asterisk.getJTable().getRowCount();i++){
			String idcuenta="";
			try {
				idcuenta = asterisk.getJTable().getValueAt(i, 0).toString();
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
		col.setName("fecha");
		col.setWidth(120);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("callerid");
		col.setWidth(80);
		col.setEditable(false);
		table.addColumn(col);

		col = new Column();
		col.setName("duracion");
		col.setWidth(100);
		col.setEditable(false);
		table.addColumn(col);

		col = new Column();
		col.setName("estado");
		col.setWidth(180);
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
		table.setName(_Interface._table_history);
		table.addKeyListener(this._constructor.getKeyListener());
		table.addMouseListener(this._constructor.getMouseListener());
		Font fuente = new Font("Arial", Font.PLAIN, 10);
		table.setHeaderFont(fuente);
		table.build();
		table.fillData();
		asterisk.setJTable1(table.getTable());

	}

	public void focus(int row) {
		try {
			asterisk.getJTable().getCellEditor().stopCellEditing();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		asterisk.getJTable().changeSelection(row, 0, false, false);

	}

	public void simulate(String callerid) {
		this.callerid = callerid;
		this.asterisk.get_txt_callerid().setText(callerid);
		this.load_callerid(this.callerid, extension, "simulation");
	}

	public void editCell(int row, int col) {
		JTable table = asterisk.getJTable();
		table.changeSelection(row, col, false, false);
		table.editCellAt(row, col);
		table.transferFocus();
	}

	public void borrarRenglon(int row) {
		if (preguntar("Confirmar", "Elimina Renglon " + row + " de la tabla?")) {
			try {
				asterisk.getJTable().getCellEditor().stopCellEditing();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
			DefaultTableModel model = (DefaultTableModel) asterisk.getJTable()
					.getModel();
			model.removeRow(row);
			if (model.getRowCount() <= 0) {
				model.setRowCount(1);
				model.setValueAt(true, 0, 0);
				this.editCell(0, 1);
			}

		}
	}

	private aplicacion.herramientas.java.evaluadores.Mixto Mixto = null;

	public void initialize_Mixto() {
		Mixto = new aplicacion.herramientas.java.evaluadores.Mixto() {
			public void cargar(String codigo) {
				Object[][] results = this.getInfo(codigo);
				String descripcion = (String) results[0][1];
				// asterisk.get_txt_descripcion_Mixto().setText(descripcion);
			}
		};
		Mixto.setConstructor(this.getConstructor());
	}

	public void BuscarMixto(JTextField tx) {
		Mixto.Buscar(tx);
	}

	public void evaluate_cuenta(JTextField tx, int row) {
		String idcuenta = tx.getText();
		if (idcuenta.compareTo("") == 0) {
			Mixto.buscar(tx);
		} else {
			if (Mixto.existe(idcuenta)) {
				Object[][] info = Mixto.getInfo(idcuenta);
				if (info != null) {
					if (info.length > 0) {
						String descripcion = (String) info[0][1];
						asterisk.getJTable().setValueAt(descripcion, row, 1);
						DefaultTableModel model = (DefaultTableModel) asterisk
								.getJTable().getModel();
						if (row == model.getRowCount() - 1) {
							model.setRowCount(model.getRowCount() + 1);

						}
						asterisk.getJTable().changeSelection(row + 1, 0, false,
								false);
						asterisk.getJTable().editCellAt(row + 1, 0);
						asterisk.getJTable().transferFocus();
					}
				}
			} else {
				Mixto.evaluate(tx);
			}

		}

	}

	public void buscarMixto(JTextField tx) {
		Mixto.buscar(tx);
	}

	public void evaluarMixto(JTextField tx) {
		Mixto.evaluate(tx);
	}

	public void goHistory() {
		
		this.createTimer();
		SwingWorker worker = null;
		if (worker == null) {
			worker = new SwingWorker() {
				public Object construct() {
					return new _taskEnviar();
				}
			};
		}
		if (Timer!=null) {
			Timer.start();
		}
		worker.start();
	}
	class _taskEnviar {
		_taskEnviar() {
			_taskworkEnviar();
		}
	}
	
	public void _taskworkEnviar(){
		this.cargar_historial();
	}
	public void createTimer(){
		crono=new Crono();
		crono.start();
		lenght=0;
		current=0;
		errors=0;
		done = false;
		canceled=false;
		Timer=new Timer(3000, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (done |canceled){
					
					Timer.stop();
					}else {
						
					
				}
			}
		}); 
		
	}

}
