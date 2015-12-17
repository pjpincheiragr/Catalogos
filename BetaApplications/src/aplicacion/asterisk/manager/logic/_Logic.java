package aplicacion.asterisk.manager.logic;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.IOException;
import java.security.Principal;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JCheckBox;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import org.asteriskjava.manager.AuthenticationFailedException;
import org.asteriskjava.manager.ManagerConnection;
import org.asteriskjava.manager.ManagerConnectionFactory;
import org.asteriskjava.manager.ManagerEventListener;
import org.asteriskjava.manager.TimeoutException;
import org.asteriskjava.manager.action.StatusAction;
import org.asteriskjava.manager.event.ManagerEvent;
import org.asteriskjava.manager.event.NewExtenEvent;
import org.asteriskjava.manager.event.StatusCompleteEvent;
import aplicacion.asterisk.manager.events.*;
import aplicacion.asterisk.manager.gui._Frame;
import aplicacion.asterisk.manager.gui.PanelAsterisk;
import aplicacion.asterisk.manager.gui.PanelAgenda;
import aplicacion.asterisk.manager.logic._Data;
import aplicacion.herramientas.java.table.CheckBoxCellEditor;
import aplicacion.herramientas.java.table.Column;
import aplicacion.herramientas.java.table.CustomTable;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Data;
import aplicacion.modelo.logic.Logic;
import javax.swing.ImageIcon;
import aplicacion.asterisk.manager.interfaces._Interface;
import aplicacion.asterisk.manager.logic.ButtonEditor;

public class _Logic extends Logic {

	private ManagerConnection managerConnection;
	private String callerid = "";
	private String host = "192.168.4.114";
	private String user = "admin";
	private String password = "amp111";
	private _ManagerEventListener managerEventListener = null;
	private _Data data = null;
	private _Frame frame = null;
	private String extension = "";
	private boolean load_asterisk = false;
	private boolean load_agenda = false;
	public _Logic() {

	}

	public void setFrame(JFrame frame) {
		this.frame = (_Frame) frame;
		super.setFrame(frame);
	}

	public void setData(Data data) {
		this.data = (_Data) data;
		super.setData(this.data);
	}

	public void openWeb(String url) {
		boolean ok = true;
		try {
			java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
		} catch (IOException e) {
			ok = true;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!ok) {
			error("Soporte no disponible");
		}
	}

	public void ayuda() {
		String url = "http://www.gisbertrepuestos.com.ar/wordpress/?cat=3";
		this.openWeb(url);
	}

	public void exit() {
		if (preguntar(null, "Confirmar", "Cierra Aplicacion?")) {
			exit_command();
		}
	}

	public void exit_command() {
		System.exit(0);
	}

	public void shift() {
		this._principal.minimizado();
		frame.getJTabbedPane().setSelectedIndex(0);
		frame.setVisible(true);
		frame.toFront();
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

	public void maximizar() {

		_principal.modify();
	}

	public void select(int row) {
		_asterisk.select(row);
	}

	public void loadAcercade() {
		frame.getJTabbedPane().addTab(
				"Acerca de",
				new ImageIcon(getClass()
						.getResource("/icons/gnome-help-24.png")),
				frame.getJPanel_acerca(), null);
	}

	public void loadModules() {
		/*
		 * cleanAll(); loadAsterisk(); loadPrincipal();
		 */
		load_asterisk = data.getAsterisk(this.getConstructor().getIduser());
		
		Runnable _execute2 = new Runnable() {
			public void run() {
				cleanAll();
				loadPrincipal();
				if (load_asterisk) {
					try {
						loadAsterisk();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				loadAcercade();
			}
		};
		this.invokeAndWait(_execute2);

	}

	public void cleanAll() {
		// frame.getJTabbedPane().removeAll();
	}

	PanelAsterisk asterisk = null;
	_Asterisk _asterisk = null;

	private void loadAsterisk() {
		_asterisk = new _Asterisk();
		_asterisk.setConstructor(this.getConstructor());
		_asterisk.initialize_Mixto();
		_asterisk.setData(data);
		asterisk = new PanelAsterisk();
		_asterisk.setFrame(asterisk);
		_asterisk.load_user();
		_asterisk.init();
		_asterisk.login();
		try {
			_asterisk.connect_cdr();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		asterisk.get_btn_maestro().setActionCommand(
				_Interface._btn_ask_maestro_cliente);
		asterisk.get_btn_maestro().addActionListener(
				this.getConstructor().getActionListener());
		asterisk.get_btn_pdc().setActionCommand(_Interface._btn_ask_pdc);
		asterisk.get_btn_pdc().addActionListener(
				this.getConstructor().getActionListener());
		asterisk.get_btn_visor_pdc().setActionCommand(
				_Interface._btn_ask_visor_pdc);
		asterisk.get_btn_visor_pdc().addActionListener(
				this.getConstructor().getActionListener());
		asterisk.get_btn_guardar()
				.setActionCommand(_Interface._btn_ask_guardar);
		asterisk.get_btn_guardar().addActionListener(
				this.getConstructor().getActionListener());
		frame.getJTabbedPane().addTab("Telefono",
				new ImageIcon(getClass().getResource("/icons/phone-16.gif")),
				asterisk, null);

		_asterisk.cargar_historial();
		// _asterisk.simulate("5358241"); nico
		// _asterisk.simulate("5358237");
	}

	_Principal _principal = null;

	private void loadPrincipal() {
		_principal = new _Principal();
		_principal.setUser(this.getConstructor().getIduser());
		_principal.setConstructor(this.getConstructor());
		_principal.setData(data);
		_principal.setFrame(frame);
		
		_principal.init();

		// _asterisk.simulate("5358237");
	}

	public void verMaestro() {
		int row = asterisk.getJTable().getSelectedRow();
		if (row >= 0) {
			this.verMaestro(row);
		}

	}

	public void nuevo_pdc() {
		int row = asterisk.getJTable().getSelectedRow();
		if (row >= 0) {
			String idcliente = asterisk.getJTable().getValueAt(row, 0)
					.toString();
			this.nuevo_pedido(idcliente);
		}

	}

	public void nuevo_visor() {
		int row = asterisk.getJTable().getSelectedRow();
		if (row >= 0) {
			String idcliente = asterisk.getJTable().getValueAt(row, 0)
					.toString();
			this.nuevo_visor(idcliente);
		}

	}

	public void guardar() {
		String callerid = asterisk.get_txt_callerid().getText();
		if (this.confirmar("Confirme Para Guardar", 2)) {
			_asterisk._guardar(callerid);
		}
	}

	public void evaluate_idcuenta(JTextField tx, int row) {
		_asterisk.evaluate_cuenta(tx, row);
	}

	public void borrar_renglon(JTextField tx, int row) {
		if (tx.getText().compareTo("") == 0) {
			_asterisk.borrarRenglon(row);
		}

	}

	public void focus(int row) {
		frame.getJTabbedPane().requestFocusInWindow();
		frame.getJTabbedPane().setSelectedComponent(asterisk);
		asterisk.getJTable().changeSelection(row, 0, false, false);
		asterisk.getJTable().editCellAt(row, 0);
		asterisk.getJTable().transferFocus();
	}

	public void Buscar(JTextField tx) {
		_asterisk.BuscarMixto(tx);
	}

	public void nuevo_pedido(String idcliente) {
		aplicacion.ventas.pedido.constructor._Constructor pedido = new aplicacion.ventas.pedido.constructor._Constructor();
		pedido.setParameter(_parametros.connector, this.getConstructor()
				.getConnectionHandler().Clone());
		pedido.setParameter(_parametros.iduser, this.getConstructor()
				.getIduser());
		pedido.setParameter(_parametros.LookAndFeel, this.getConstructor()
				.getLookAndFeelTheme());
		pedido.setParameter(
				aplicacion.ventas.pedido.interfaces._parametros._idcliente,
				idcliente);
		pedido.build(this.getConstructor());
		pedido.init();
	}

	public void reconnectar() {
		this.getConstructor().reconnect(true);
	}

	public void nuevo_visor(String idcliente) {
		aplicacion.ventas.gestion.constructor._Constructor visor = new aplicacion.ventas.gestion.constructor._Constructor();
		visor.setParameter(_parametros.connector, this.getConstructor()
				.getConnectionHandler().Clone());
		visor.setParameter(_parametros.iduser, this.getConstructor()
				.getIduser());
		visor.setParameter(_parametros.LookAndFeel, this.getConstructor()
				.getLookAndFeelTheme());
		visor.setParameter(
				aplicacion.ventas.gestion.interfaces._parametros.idcuenta,
				idcliente);
		visor.build(this.getConstructor());
		visor.init();
	}

	public void ejecutar(String lanzador) {
		lanzador = lanzador.replaceAll("lanzador:", "");
		_principal.goCargar(lanzador, "");
	}

	public void hide() {
		if (_principal.getMaximizado()) {
			_principal.minimizado();
		} else {
			frame.setVisible(false);
		}

	}

	public void show() {
		this._principal.show();
	}

	public void showMax() {
		this._principal.maximizado();
	}

	public void verMaestro(int row) {

		String idcliente = asterisk.getJTable().getValueAt(row, 0).toString();
		if (idcliente.compareTo("") != 0) {
			aplicacion.cliente.archivo.constructor._Constructor CC = new aplicacion.cliente.archivo.constructor._Constructor();
			CC.setParameter(_parametros.connector, this._data
					.getConnectionHandler().Clone());
			CC.setParameter(_parametros.LookAndFeel, this.getConstructor()
					.getLookAndFeelTheme());
			CC
					.setParameter(
							aplicacion.cliente.archivo.interfaces._Parametros.idcliente,
							idcliente);
			CC.build(this.getConstructor());
			CC.init();
		}
	}
	public String validar_vendedor() {
		String idvendedor = "";
		while (idvendedor.compareTo("") == 0) {
			String password = this.requestPassword("Ingrese Su Clave:");
			idvendedor = data.getVendedorValidacion(password);
			if (idvendedor.compareTo("") == 0) {
				error("Error de Validacion de Usuario");
			}
		}
		return idvendedor;
	}
	
	public void editarAviso(JTable table,int row) {
		if (row>=0){
			String idaviso=table.getValueAt(row, 0).toString();
			this.editarAviso(idaviso);	
		}
	}
	
	public void _editarAviso(String idaviso,String iduser) {
		aplicacion.gestion.agenda.escritor.constructor._Constructor CC = new aplicacion.gestion.agenda.escritor.constructor._Constructor();
		CC.setParameter(_parametros.connector, this._data
				.getConnectionHandler().Clone());
		CC.setParameter(_parametros.LookAndFeel, this.getConstructor()
				.getLookAndFeelTheme());
		CC.build(this.getConstructor());
		CC.init();
		aplicacion.gestion.agenda.escritor.logic._Logic logic=
		(aplicacion.gestion.agenda.escritor.logic._Logic) CC.getLogic();
		logic.cargarAviso(idaviso,iduser);
	}
	
	public void _leerAviso(String idaviso,String iduser) {
		aplicacion.gestion.agenda.lector.constructor._Constructor CC = new aplicacion.gestion.agenda.lector.constructor._Constructor();
		CC.setParameter(_parametros.connector, this._data
				.getConnectionHandler().Clone());
		CC.setParameter(_parametros.LookAndFeel, this.getConstructor()
				.getLookAndFeelTheme());
		CC.build(this.getConstructor());
		CC.init();
		aplicacion.gestion.agenda.lector.logic._Logic logic=
		(aplicacion.gestion.agenda.lector.logic._Logic) CC.getLogic();
		logic.cargarAviso(idaviso,iduser);
	}
	public void editarAviso(String idaviso) {
		String iduser = validar_vendedor();
		if (data.existeAviso(idaviso, iduser)){
			if (data.esCreador(idaviso, iduser)){
				this._editarAviso(idaviso, iduser);
			}else{
				this._leerAviso(idaviso, iduser);
			}
		}else{
			error("No puede ver este aviso");
		}
	}
	public void nuevoAviso() {

		aplicacion.gestion.agenda.escritor.constructor._Constructor CC = new aplicacion.gestion.agenda.escritor.constructor._Constructor();
		CC.setParameter(_parametros.connector, this._data
				.getConnectionHandler().Clone());
		CC.setParameter(_parametros.LookAndFeel, this.getConstructor()
				.getLookAndFeelTheme());
		CC.build(this.getConstructor());
		CC.init();
		aplicacion.gestion.agenda.escritor.logic._Logic logic=
		(aplicacion.gestion.agenda.escritor.logic._Logic) CC.getLogic();
		logic.nuevo();

	}

	public void Agenda() {

		aplicacion.gestion.agenda.gestion.constructor._Constructor CC = new aplicacion.gestion.agenda.gestion.constructor._Constructor();
		CC.setParameter(_parametros.connector, this._data
				.getConnectionHandler().Clone());
		CC.setParameter(_parametros.LookAndFeel, this.getConstructor()
				.getLookAndFeelTheme());
		CC.build(this.getConstructor());
		CC.init();
		

	}
	public void goCargar(String lanzador, String menu) {

		_principal.goCargar(lanzador, menu);
	}

	public void check_running() {
		aplicacion.sistema.menu.logic.TaskManager TM = new aplicacion.sistema.menu.logic.TaskManager();
		int running = TM.isRunnningAnotherBeta();
		boolean ok = true;
		if (running > 1) {
			ok = false;
			String[] options = new String[] { "Continuar", "Salir" };
			int n = this.preguntar("Confirme para Continuar",
					"Hay Otras instancias de Beta Ejecutandose:", options,
					options[0]);
			if (n == 0) {
				ok = true;
			}
			if (n == 1) {
				System.exit(0);
			}

		} else {

		}
		if (ok) {
			loadModules();
		}
	}
	public void close(JButton b){
		try {
			JPanel panel=(JPanel) b.getParent();
			JPanel panel2=(JPanel) panel.getParent();
			JLayeredPane layered=(JLayeredPane) panel2.getParent();
			JRootPane root=(JRootPane)layered.getParent();
			DynamicFrame fx=(DynamicFrame) root.getParent();
			close(fx);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void edit(JButton b){
		try {
			JPanel panel=(JPanel) b.getParent();
			JPanel panel2=(JPanel) panel.getParent();
			JLayeredPane layered=(JLayeredPane) panel2.getParent();
			JRootPane root=(JRootPane)layered.getParent();
			DynamicFrame fx=(DynamicFrame) root.getParent();
			String idaviso=fx.getIdaviso();
			close(fx);
			this.editarAviso(idaviso);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void close(DynamicFrame fx){
		this._principal.close(fx);
	}
}
