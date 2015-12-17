package aplicacion.compras.carga.control.logic;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.LookupOp;
import java.awt.image.LookupTable;
import java.awt.image.ShortLookupTable;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Vector;


import aplicacion.herramientas.java.launcher.logic.SwingWorker;
import aplicacion.herramientas.java.table.CellEditor;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Logic;
import aplicacion.modelo.logic.Data;
import aplicacion.sistema.version.logic.Crono;
import aplicacion.sistema.version.logic.JakartaFtpWrapper;

import aplicacion.compras.carga.control.events.TableColorCellRenderer;
import aplicacion.compras.carga.control.events.TableDayColorCellRenderer;
import aplicacion.compras.carga.control.gui._Frame;
import aplicacion.compras.carga.control.logic._Data;
import aplicacion.compras.carga.control.constructor._Constructor;
import aplicacion.compras.carga.control.interfaces._Interface;
import aplicacion.herramientas.java.image.logic.*;
import aplicacion.herramientas.java.table.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.net.ftp.FTPFile;

import aplicacion.herramientas.java.*;

public class _Logic extends Logic {
	private _Frame frame = null;
	private _Data data = null;
	private String server = "";
	private String ftpserver;
	private String username;
	private String password;
	private String file;
	private String directory = "";
	private aplicacion.herramientas.java.visualselector.constructor._Constructor vSelector = null;
	private aplicacion.compras.carga.items.constructor._Constructor editor = null;
	private aplicacion.herramientas.java.visualizadores.Proveedor vProveedor = null;

	private LookupTable lookupTable;
	private BufferedImage img = null;
	private int hoja_actual = 0;
	private int hojas = 0;
	private String default_Format = "dd-MM-yyyy";
	
	private JakartaFtpWrapper ftp = null;
	private String estado = "";
	private Object[] test;
	private int current;
	private int lenght, max;
	private boolean debug, done, canceled, override;
	private Timer Timer; // @jve:decl-index=0:
	private Crono crono;

	public void setFrame(JFrame frame) {
		this.frame = (_Frame) frame;
		super.setFrame(frame);
	}

	public void mark(int row, int col) {
		frame.getJTable2().changeSelection(row, col, false, false);
	}

	
	private aplicacion.herramientas.java.buscadores.Fecha bFecha = null;

	public void BuscarFecha(JTextField tx) {
		if (bFecha == null) {
			bFecha = new aplicacion.herramientas.java.buscadores.Fecha(this
					.getConstructor());

		}
		
		bFecha.Buscar(tx);
	}

	public void next() {
		int row = frame.getJTable().getSelectedRow();

		if (row >= 0 & row < frame.getJTable().getRowCount() - 1) {
			row++;
			frame.getJTable().changeSelection(row, 0, false, false);
			this.goCargarCpte(row);
		}
	}

	public void createTimer() {
		crono = new Crono();
		crono.start();
		current = 0;
		lenght=0;
		done=false;
		canceled=false;
		Timer = new Timer(300, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (done &!canceled) {
					endbar();
					Timer.stop();
				} else {
					updateBar();
				}
			}
		});
	}

	public void endbar() {
		done = true;
		frame.getJProgressBar().setIndeterminate(false);
		frame.getJProgressBar().setValue(lenght);
		frame.getJProgressBar().setString("");
	}

	public void updateBar() {
		frame.getJProgressBar().setMaximum(lenght);
		frame.getJProgressBar().setValue(current);
		frame.getJProgressBar().setString(
				estado + " " + current + "/" + lenght + " " + crono.elapsed());
		frame.getJProgressBar().setStringPainted(true);
	}


	public void back() {
		int row = frame.getJTable().getSelectedRow();
		if (row > 0 & row <= frame.getJTable().getRowCount() - 1) {
			row--;
			frame.getJTable().changeSelection(row, 0, false, false);
			this.goCargarCpte(row);
		}
	}

	public void cancelar() {
		if (preguntar("confirmar", "Cancela Control?")) {
			doclean();
		}
	}

	public void imprimir() {
		if (frame.getJScrollPane2().getViewport().getView() instanceof ScrollableImage) {
			ScrollableImage scroll = (ScrollableImage) frame.getJScrollPane2()
					.getViewport().getView();
			scroll.print();
		}
	}

	public void load_desde() {
		this.BuscarFecha(frame.get_txt_fecha_desde());
	}

	public void calculate_controlado() {
		int total = frame.getJTable().getRowCount();
		int controlados = 0;
		for (int i = 0; i < total; i++) {
			boolean controlado = false;
			try {
				String control = frame.getJTable().getValueAt(i,
						frame.getJTable().getColumnCount() - 1).toString();
				controlado = control.compareTo("") != 0;
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (controlado) {
				controlados++;
			}
		}

		frame.get_txt_controlados().setText("" + controlados + "/" + total);

	}

	public void calculate_column(int column, JTextField tx) {
		int total = frame.getJTable().getRowCount();
		double suma = 0.0;
		for (int i = 0; i < total; i++) {
			double sum = 0.0;
			try {
				String control = frame.getJTable().getValueAt(i, column)
						.toString();

				sum = new Double(control.replaceAll(",", ""));
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (sum > 0) {
				suma += sum;
			}
		}
		Convertidor C = new Convertidor();
		tx.setText(C.getMoney(suma, 2));
	}

	public void CalculateTotals() {
		this.calculate_column(8, frame.get_txt_iva_21());
		this.calculate_column(9, frame.get_txt_iva10());
		this.calculate_column(10, frame.get_txt_iva_27());
		this.calculate_column(11, frame.get_txt_perc_iva());
		this.calculate_column(12, frame.get_txt_perc_iibb());
		this.calculate_column(13, frame.get_txt_ret_gan());
		this.calculate_column(14, frame.get_txt_total());
	}

	public void load_hasta() {
		this.BuscarFecha(frame.get_txt_fecha_hasta());
	}

	public void load_carga() {
		this.BuscarFecha(frame.get_txt_c_fecha_carga());
	}

	private void cleanPreview() {
		frame.setJTable1(null);
		frame.getJScrollPane2().setViewportView(null);
		frame.get_txt_c_cuenta().setText("");
		frame.get_txt_c_descripcion().setText("");
		frame.get_txt_c_fecha().setText("");
		frame.get_txt_c_fecha_carga().setText("");
		frame.get_txt_c_fecha_control().setText("");
		frame.get_txt_c_idcomprobante().setText("");
		frame.get_txt_c_tc().setText("");
		frame.get_txt_c_total().setText("");
	}



	public void delete_fecha_desde(JTextField tx) {
		String fecha = tx.getText();

	}

	public void delete_fecha_carga_desde(JTextField tx) {
		String fecha = tx.getText();
		if (fecha.compareTo("") == 0) {
			frame.get_txt_fecha_hasta().requestFocusInWindow();
		}

	}

	public void delete_fecha_hasta(JTextField tx) {
		String fecha = tx.getText();
		if (fecha.compareTo("") == 0) {
			frame.get_txt_fecha_desde().requestFocusInWindow();
		}
	}

	public void delete_fecha_carga_hasta(JTextField tx) {
		String fecha = tx.getText();
		if (fecha.compareTo("") == 0) {
			frame.get_txt_fecha_carga_desde().requestFocusInWindow();
		}
	}

	public void evaluate_fecha_desde(JTextField tx) {
		String fecha = tx.getText();
		if (fecha.compareTo("") == 0) {
			this.BuscarFecha(tx);
		} else {
			if (this.evaluarFecha(fecha)) {
				this.frame.get_txt_fecha_hasta().requestFocusInWindow();
			} else {
				error("Fecha " + fecha + " incorrecta");
				tx.setSelectionStart(0);
				tx.setSelectionEnd(tx.getText().length());
			}
		}

	}

	public void evaluate_fecha_carga_desde(JTextField tx) {
		String fecha = tx.getText();
		if (fecha.compareTo("") == 0) {
			this.BuscarFecha(tx);
		} else {
			if (this.evaluarFecha(fecha)) {
				this.frame.get_txt_fecha_carga_hasta().requestFocusInWindow();
			} else {
				error("Fecha " + fecha + " incorrecta");
				tx.setSelectionStart(0);
				tx.setSelectionEnd(tx.getText().length());
			}
		}

	}

	public void evaluate_fecha_hasta(JTextField tx) {
		String fecha = tx.getText();
		if (fecha.compareTo("") == 0) {
			this.BuscarFecha(tx);
		} else {
			if (this.evaluarFecha(fecha)) {

				frame.get_txt_fecha_carga_desde().requestFocusInWindow();
			} else {
				error("Fecha " + fecha + " incorrecta");
				tx.setSelectionStart(0);
				tx.setSelectionEnd(tx.getText().length());
			}
		}

	}

	public void evaluate_fecha_carga_hasta(JTextField tx) {
		String fecha = tx.getText();
		if (fecha.compareTo("") == 0) {
			this.BuscarFecha(tx);
		} else {
			if (this.evaluarFecha(fecha)) {
				this.cargarLibro();

			} else {
				error("Fecha " + fecha + " incorrecta");
				tx.setSelectionStart(0);
				tx.setSelectionEnd(tx.getText().length());
			}
		}

	}

	public void doclean() {
		frame.setJTable(null);
		this.cleanPreview();
		frame.get_txt_idproveedor().setEnabled(true);
		frame.get_txt_idproveedor().setText("");
		frame.get_txt_proveedor_descripcion().setText("");
		this.fechas();
	}

	

	public void _cargar_proveedor(String idproveedor) {
		Object[][] results = data.getProveedor(idproveedor);
		if (results != null) {
			if (results.length > 0) {
				frame.get_txt_proveedor_descripcion().setEnabled(false);
				String codigo = (String) results[0][0];
				String descripcion = (String) results[0][1];
				frame.get_txt_proveedor_descripcion().setText(descripcion);

			}
		}
	}

	
	private aplicacion.herramientas.java.evaluadores.Proveedor proveedor=null;
	public void initialize_proveedor(){
		proveedor=new aplicacion.herramientas.java.evaluadores.Proveedor(){
			public void cargar(String codigo){
				_cargar_proveedor(codigo);
			}
		};
		proveedor.setConstructor(this.getConstructor());
	}
	public void BuscarProveedor(JTextField tx){
		proveedor.Buscar(tx);
	}
	public void BuscarProveedor(){
		proveedor.Buscar(frame.get_txt_idproveedor());
	}
	public void evaluarProveedor(JTextField tx){
		proveedor.evaluate(tx);
	}
	public void setData(Data data) {
		this.data = (_Data) data;
		this.server = data.getPrimitiveMessage("fotostorage");
		super.setData(data);
	}

	public void _anterior() {
		int row = frame.getJTable().getSelectedRow();
		if (this.hoja_actual > 1) {
			this.hoja_actual--;
			frame.get_txt_hoja_actual().setText("" + hoja_actual);

			String cuenta = (String) frame.getJTable().getValueAt(row, 4);
			String tc = (String) frame.getJTable().getValueAt(row, 1);
			String idcomprobante = (String) frame.getJTable()
					.getValueAt(row, 2);
			this.cargarFoto(cuenta, tc, idcomprobante, hoja_actual);
		}

	}

	public void _siguiente() {
		int row = frame.getJTable().getSelectedRow();
		if (this.hoja_actual < this.hojas) {
			this.hoja_actual++;
			frame.get_txt_hoja_actual().setText("" + hoja_actual);

			String cuenta = (String) frame.getJTable().getValueAt(row, 4);
			String tc = (String) frame.getJTable().getValueAt(row, 1);
			String idcomprobante = (String) frame.getJTable()
					.getValueAt(row, 2);
			this.cargarFoto(cuenta, tc, idcomprobante, hoja_actual);
		}

	}

	public void _cargarFoto(String file) {
		try {
			img = ImageIO.read(new File(file));
		} catch (IOException e) {
			this.displayError("Error Cargando Imagen", e.getMessage(),e.getLocalizedMessage(), e);
		}
	}
	
	public void cargarFoto(String cuenta, String tc, String idcomprobante,
			int secuencia) {
		
		img=this.getImages(cuenta, tc, idcomprobante, (secuencia-1));
		if (img!=null){
			this.applyFilter();
			ScrollableImage si = null;
			si = new ScrollableImage(new ImageIcon(img), 1);
			frame.getJScrollPane2().setViewportView(si);	
		}else{
			frame.getJScrollPane2().setViewportView(null);
		}
		
		
	}
	
	public BufferedImage getImages(String idproveedor,String tc,String idcomprobante,int secuencia){
    	BufferedImage _image=null;
    	boolean ok=false;
    	try {
			Statement stmt = data.getConnector("MySQL").createStatement();
				String q="SELECT imagen FROM facturas  where idproveedor like '"+idproveedor+"' and tc like '"+tc+"' and idcomprobante like '"+idcomprobante+"' and secuencia = "+secuencia;
				System.out.println(q);
				ResultSet resultSet = stmt.executeQuery(q);
				
					if (resultSet.next()){
						 ok=true;
						 Blob image = resultSet.getBlob(1);
						  InputStream input = image.getBinaryStream();
				          _image = javax.imageio.ImageIO.read(input);
				          
				           
					 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return _image;
    }
	
	public void cargarFotoold(String cuenta, String tc, String idcomprobante,
			int hoja) {
		String secuencia = "" + hoja;
		String filex = server + cuenta + "-" + idcomprobante + "-" + secuencia+ ".jpg";
		//String filex = cuenta + "-" + idcomprobante + "-" + secuencia+ ".jpg";		
		Object[][] results = data.getFotos(cuenta, tc, idcomprobante);
		ScrollableImage si = null;
		if (results != null) {
			if (results.length > 0) {
				//_ftpDownloadFile(filex, filex);
			   //this.goDownload(filex, filex);
				//this.loadFoto(filex);
				final String _path=filex;
				if (javax.swing.SwingUtilities.isEventDispatchThread()){
					loadFoto(_path);
				}
				Runnable _execute=new Runnable(){
					public void run(){
						loadFoto(_path);		
						
					}
				};
				javax.swing.SwingUtilities.invokeLater(_execute);
				
			}
		}
		
	}
	
	class _ftpTask {
		_ftpTask(String download,String toFile) {
			_ftpDownloadFile(download,toFile);
		}
	}
	
	class cargarCpte {
		cargarCpte(int row) {
			cargar_cpte(row);
		}
	}
	
	public void _ftpDownloadFile(String download,String local) {
		long rev = 0;
		estado="Descargando Foto";
		boolean connected=false;
		connected=ftp.isConnected();
					if (!connected){
						connected=_ftpConnect();
					}
					if (connected){
						FTPFile[] files = null;
						try {
							files=ftp.listFiles(download);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						if (files!=null){
							for (int i = 0; i < files.length; i++) {
								FTPFile file = (FTPFile) files[i];
								if (file.getName().compareTo(download) == 0) {
									this.lenght = 100;
									downloadFromFTP(download, local, file.getSize());
								}

							}	
						}					
					}
	
					
		
					
	}
	
	public boolean _ftpConnect(){
		
		boolean connected=false;
		try {
			System.out.println("Connecting to " + ftpserver);
			if (ftp.connectAndLogin(ftpserver, username, password)) {
				System.out.println("Connected to " + ftpserver);
				try {
					ftp.setPassiveMode(true);
					System.out.println("Connecting to " + directory);
					ftp.changeWorkingDirectory(directory);
					connected=true;
				}catch(Exception e){
					
				}
			}
		}catch(Exception e){
			
		}
		return connected;
	}
	
	public void downloadFromFTP(String serverfile, String localfile,
			long filesize) {
		try {
			InputStream stO = new BufferedInputStream(ftp
					.retrieveFileStream(serverfile), ftp.getBufferSize());

			OutputStream stD = new FileOutputStream(localfile);

			org.apache.commons.net.io.Util.copyStream(stO, stD, ftp
					.getBufferSize(), filesize,
					new org.apache.commons.net.io.CopyStreamAdapter() {
						public void bytesTransferred(
								long totalBytesTransferred,
								int bytesTransferred, long streamSize) {
							current = (int) Math
									.ceil(((double) totalBytesTransferred / (double) streamSize) * 100);
							if (canceled) {
								try {
									ftp.abort();
									ftp.disconnect();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
					});
			boolean ok = ftp.completePendingCommand();
			try {
				ftp.abort();
				ftp.disconnect();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (ok) {
				if (!canceled) {
					final String _path=localfile;
					if (javax.swing.SwingUtilities.isEventDispatchThread()){
						loadFoto(_path);
					}
					Runnable _execute=new Runnable(){
						public void run(){
							loadFoto(_path);		
							
						}
					};
					javax.swing.SwingUtilities.invokeLater(_execute);
					
				}
				
			}

		} catch (Exception e) {
		}

	}

	
	public void goCargarCpte(int row) {
		_cargar_cpte(row);/*
    	estado="Cargando Comprobante";
    	
    	this.createTimer();
    	final int _row=row;
    	
    	SwingWorker worker=null;
    		worker = new SwingWorker() {
    			@Override
    			public Object construct() {
    				return new cargarCpte(_row);
    			}
    		};
    		
    		
    		if (Timer!=null) {
    			Timer.start();
    		}
    		worker.start();*/
    	}
	
	
	public void load_variables(){
		/*this.ftpserver=(String)data.getParameters("ftp")[0][1];
		this.username=(String)data.getParameters("ftp_user")[0][1];
		this.password=(String)data.getParameters("ftp_password")[0][1];
		this.directory=(String)data.getParameters("ftp_fotos")[0][1];*/
		ftpserver="agustin.servequake.com";
		username="sistema";
		password="ernestino1982";
		directory="fotos";
		ftp = new JakartaFtpWrapper();
		
	}
	
	public void loadFoto(String _local){
		this._cargarFoto(_local);
		this.applyFilter();
		ScrollableImage si = null;
		si = new ScrollableImage(new ImageIcon(img), 1);
		frame.getJScrollPane2().setViewportView(si);
	}
	public void applyFilter() {
		if (lookupTable != null) {
			LookupOp lop = new LookupOp(lookupTable, null);
			lop.filter(img, img);
		}
	}

	private void create_table(Object[][] results) {
		_Constructor constructor = (_Constructor) this.getConstructor();
		CustomTable table = new CustomTable();

		Column col = new Column();

		col = new Column();
		col.setName("Cuenta");
		col.setWidth(120);
		col.setEditable(false);
		table.addColumn(col);

		col = new Column();
		col.setName("Descripcion");
		col.setWidth(300);
		col.setEditable(false);
		table.addColumn(col);

		col = new Column();
		col.setName("Importe");
		col.setWidth(120);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);

		table.addColumn(col);

		table.setSortable(false);
		table.setData(results);
		table.addKeyListener(this._constructor.getKeyListener());
		Font fuente = new Font("Dialog", Font.BOLD, 10);
		table.setHeaderFont(fuente);
		table.setFont(fuente);
		table.build();
		table.fillData();

		JTable _table = table.getTable();

		frame.setJTable1(table.getTable());
	}

	private void create_table_libro(Object[][] results) {
		_Constructor constructor = (_Constructor) this.getConstructor();
		CustomTable table = new CustomTable();

		Column col = new Column();

		col = new Column();
		col.setName("fecha");
		col.setWidth(90);
		col.setEditable(false);
		col.setCellRenderer(new TableColorCellRenderer());
		table.addColumn(col);

		col = new Column();
		col.setName("tc");
		col.setWidth(60);
		col.setCellRenderer(new TableColorCellRenderer());
		col.setEditable(false);
		table.addColumn(col);

		col = new Column();
		col.setName("idcomprobante");
		col.setWidth(120);
		col.setCellRenderer(new TableColorCellRenderer());
		col.setEditable(false);
		table.addColumn(col);

		col = new Column();
		col.setName("descripcion");
		col.setWidth(200);
		col.setCellRenderer(new TableColorCellRenderer());
		col.setEditable(false);
		table.addColumn(col);

		col = new Column();
		col.setName("Cuenta");
		col.setWidth(120);
		col.setCellRenderer(new TableColorCellRenderer());
		col.setEditable(false);
		table.addColumn(col);

		col = new Column();
		col.setName("cuit");
		col.setWidth(100);
		col.setCellRenderer(new TableColorCellRenderer());
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);

		col = new Column();
		col.setName("Imp.NetoGrav");
		col.setWidth(120);
		col.setCellRenderer(new TableColorCellRenderer());
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);

		col = new Column();
		col.setName("Imp.NetoNGrav");
		col.setWidth(120);
		col.setCellRenderer(new TableColorCellRenderer());
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);

		col = new Column();
		col.setName("Iva 21%");
		col.setWidth(100);
		col.setCellRenderer(new TableColorCellRenderer());
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);

		col = new Column();
		col.setName("Iva 10.5%");
		col.setWidth(100);
		col.setCellRenderer(new TableColorCellRenderer());
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);

		col = new Column();
		col.setName("Iva 27%");
		col.setWidth(100);
		col.setCellRenderer(new TableColorCellRenderer());
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);

		col = new Column();
		col.setName("Iva Perc");
		col.setWidth(100);
		col.setCellRenderer(new TableColorCellRenderer());
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);

		col = new Column();
		col.setName("Perc IIBB%");
		col.setWidth(100);
		col.setCellRenderer(new TableColorCellRenderer());
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);

		col = new Column();
		col.setName("Ret Gan");
		col.setWidth(100);
		col.setCellRenderer(new TableColorCellRenderer());
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);

		col = new Column();
		col.setName("Total");
		col.setWidth(100);
		col.setCellRenderer(new TableColorCellRenderer());
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);
		col = new Column();
		col.setName("Carga");
		col.setWidth(100);
		col.setCellRenderer(new TableColorCellRenderer());
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);
		col = new Column();
		col.setName("Control");
		col.setWidth(100);
		col.setCellRenderer(new TableColorCellRenderer());
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);
		/*
		 * CellEditor pce = new CellEditor();
		 * pce.addKeyListener(constructor.getKeyListener());
		 * pce.setSelectedBackgroundColor(Color.lightGray);
		 * pce.setName(_Interface._table_importe); pce.setTipo(String.class);
		 * col.setCellEditor(pce.getCellEditor());
		 * 
		 * table.addColumn(col);
		 */
		table.setSortable(true);
		table.setData(results);
		table.addKeyListener(this._constructor.getKeyListener());
		table.addMouseListener(this._constructor.getMouseListener());
		Font fuente = new Font("Dialog", Font.BOLD, 10);
		table.setHeaderFont(fuente);
		table.setFont(fuente);
		table.build();
		table.fillData();

		JTable _table = table.getTable();
		_table.setName(_Interface._table_libro);
		_table.setRowSelectionAllowed(true);
		_table.setColumnSelectionAllowed(false);
		frame.setJTable(table.getTable());
	}

	public void darkenLUT() {
		short brighten[] = new short[256];
		for (int i = 0; i < 256; i++) {
			short pixelValue = (short) (i - 10);
			if (pixelValue > 255)
				pixelValue = 255;
			else if (pixelValue < 0)
				pixelValue = 0;
			brighten[i] = pixelValue;
		}
		lookupTable = new ShortLookupTable(0, brighten);
	}

	public void brightenLUT() {
		short brighten[] = new short[256];
		for (int i = 0; i < 256; i++) {
			short pixelValue = (short) (i + 10);
			if (pixelValue > 255)
				pixelValue = 255;
			else if (pixelValue < 0)
				pixelValue = 0;
			brighten[i] = pixelValue;
		}
		lookupTable = new ShortLookupTable(0, brighten);
	}

	public void contraste_mas() {
		this.brightenLUT();
		this.applyFilter();
		frame.getJScrollPane2().getViewport().getView().repaint();
	}

	public void contraste_menos() {
		this.darkenLUT();
		this.applyFilter();
		frame.getJScrollPane2().getViewport().getView().repaint();
	}

	public void update_mes(JComboBox _mes) {

		this.dias(_mes, frame.get_list_anio());
		show_time2();
	}

	public void update_anio(JComboBox _anio) {

		this.dias(frame.get_list_mes(), _anio);
		show_time2();
	}

	public void clonarFechas() {
		frame.get_txt_fecha_carga_desde().setText(
				frame.get_txt_fecha_desde().getText());
		frame.get_txt_fecha_carga_hasta().setText(
				frame.get_txt_fecha_hasta().getText());
	}

	public void show_time2() {

		this.create_table();
		this.fillTable();
		int col = this.get_day_week();
		this.frame.getJTable2().changeSelection(0, col, false, false);

	}

	public void dias(JComboBox _mes, JComboBox _anio) {
		int mes = _mes.getSelectedIndex();

		int anio = new Integer(_anio.getSelectedItem().toString());
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, mes);
		cal.set(Calendar.YEAR, anio);

		String default_Format = "dd-MM-yyyy";
		int day = 1;
		cal.set(Calendar.DAY_OF_MONTH, day);
		Locale locale = Locale.getDefault();
		String s1 = "";
		Date date = null;
		DateFormat formatter;
		try {
			date = cal.getTime();
			formatter = new SimpleDateFormat(default_Format, locale);
			s1 = formatter.format(date);
		} catch (Exception e) {

		}

		frame.get_txt_fecha_desde().setText(s1);
		day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, day);
		try {
			date = cal.getTime();
			formatter = new SimpleDateFormat(default_Format, locale);
			s1 = formatter.format(date);
		} catch (Exception e) {

		}
		frame.get_txt_fecha_hasta().setText(s1);
		clonarFechas();
	}

	public int get_day_week() {
		String s_year = frame.get_list_anio().getSelectedItem().toString();
		int year = new Integer(s_year);
		int month = 0;
		switch (frame.get_list_mes().getSelectedIndex()) {
		case 0:
			month = Calendar.JANUARY;
			break;
		case 1:
			month = Calendar.FEBRUARY;
			break;
		case 2:
			month = Calendar.MARCH;
			break;
		case 3:
			month = Calendar.APRIL;
			break;
		case 4:
			month = Calendar.MAY;
			break;
		case 5:
			month = Calendar.JUNE;
			break;
		case 6:
			month = Calendar.JULY;
			break;
		case 7:
			month = Calendar.AUGUST;
			break;
		case 8:
			month = Calendar.SEPTEMBER;
			break;
		case 9:
			month = Calendar.OCTOBER;
			break;
		case 10:
			month = Calendar.NOVEMBER;
			break;
		case 11:
			month = Calendar.DECEMBER;
			break;
		}

		Calendar cal = new GregorianCalendar(year, month, 1);
		int day = cal.get(Calendar.DAY_OF_WEEK) - 1;
		return day;
	}

	public void fillTable() {
		this.create_table();
		int ini = get_day_week();// domingo

		int max = 6;
		int cellx = ini;
		int celly = 0;
		for (int i = 0; i <= this.get_days(); i++) {
			if (cellx > max) {
				cellx = 0;
				celly++;
			} else {
				cellx++;
			}

		}
		DefaultTableModel defaultTableModel = (DefaultTableModel) frame
				.getJTable2().getModel();
		for (int j = 0; j < celly + 1; j++) {
			if (j == (defaultTableModel.getRowCount() - 1)) {
				defaultTableModel
						.setRowCount(defaultTableModel.getRowCount() + 1);
			}
		}
		cellx = ini;
		celly = 0;
		for (int i = 1; i <= this.get_days(); i++) {
			frame.getJTable2().setValueAt("" + i, celly, cellx);
			// System.out.println("cell"+celly+":"+cellx);
			if (cellx == max) {
				cellx = 0;
				celly++;
			} else {
				cellx++;
			}

		}
		mark(Calendar.getInstance());
	}

	public int get_days() {
		String s_year = frame.get_list_anio().getSelectedItem().toString();
		int year = new Integer(s_year);
		int month = 0;
		switch (frame.get_list_mes().getSelectedIndex()) {
		case 0:
			month = Calendar.JANUARY;
			break;
		case 1:
			month = Calendar.FEBRUARY;
			break;
		case 2:
			month = Calendar.MARCH;
			break;
		case 3:
			month = Calendar.APRIL;
			break;
		case 4:
			month = Calendar.MAY;
			break;
		case 5:
			month = Calendar.JUNE;
			break;
		case 6:
			month = Calendar.JULY;
			break;
		case 7:
			month = Calendar.AUGUST;
			break;
		case 8:
			month = Calendar.SEPTEMBER;
			break;
		case 9:
			month = Calendar.OCTOBER;
			break;
		case 10:
			month = Calendar.NOVEMBER;
			break;
		case 11:
			month = Calendar.DECEMBER;
			break;
		}

		Calendar cal = new GregorianCalendar(year, month, 1);
		int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		// days++;
		// System.out.println("dias del mes "+days);
		return days;
	}

	public void mark(Calendar cal) {

		int t_m = cal.get(Calendar.MONTH);
		int t_y = cal.get(Calendar.YEAR);
		int t_d = cal.get(Calendar.DAY_OF_MONTH);
		int s_m = frame.get_list_mes().getSelectedIndex();
		int s_y = new Integer(frame.get_list_anio().getSelectedItem()
				.toString());
		int ini = get_day_week();// domingo

		if (t_m == s_m && t_y == s_y) {
			int max = 6;
			int cellx = ini;
			int celly = 0;
			for (int i = 0; i < this.get_days(); i++) {
				if (cellx > max) {
					cellx = 0;
					celly++;
				} else {
					cellx++;
				}

			}
			DefaultTableModel defaultTableModel = (DefaultTableModel) frame
					.getJTable2().getModel();
			for (int j = 0; j < celly + 1; j++) {
				if (j == (defaultTableModel.getRowCount() - 1)) {
					defaultTableModel.setRowCount(defaultTableModel
							.getRowCount() + 1);
				}
			}
			cellx = ini;
			celly = 0;
			for (int i = 1; i < this.get_days(); i++) {

				if (i == t_d) {
					frame.getJTable2().requestFocusInWindow();
					frame.getJTable2().clearSelection();
					frame.getJTable2().changeSelection(celly, cellx, false,
							false);
					// frame.getJTable().editCellAt(celly, cellx);

				}
				// System.out.println("cell"+celly+":"+cellx);
				if (cellx == max) {
					cellx = 0;
					celly++;
				} else {
					cellx++;
				}

			}
		}
		show_time();
	}

	public void show_time() {
		System.out.println("showtime");
		// frame.get_txt_fecha().setText(""+this.getTime());

	}

	private void create_table() {
		_Constructor constructor = (_Constructor) this.getConstructor();
		CustomTable table = new CustomTable();

		Column col = new Column();

		String[] dias = new String[] { "Dom", "Lun", "Mar", "Mie", "Jue",
				"Vie", "Sab",

		};
		TableDayColorCellRenderer renderer = new TableDayColorCellRenderer();
		renderer.setLogic(this);
		for (int i = 0; i < dias.length; i++) {
			col = new Column();
			col.setName(dias[i]);
			col.setWidth(30);
			col.setAligment(JTextField.CENTER);
			col.setEditable(false);

			col.setCellRenderer(renderer);
			table.addColumn(col);

		}

		table.setSortable(false);
		Object[][] results = new Object[][] { { "", "", "", "", "", "", "" } };
		table.setData(results);
		table.setName(_Interface._table_dias);
		table.addKeyListener(this._constructor.getKeyListener());
		table.addMouseListener(this._constructor.getMouseListener());
		Font fuente = new Font("Dialog", Font.BOLD, 10);
		table.setHeaderFont(fuente);
		table.setFont(fuente);
		table.build();
		table.fillData();

		JTable _table = table.getTable();

		frame.setJTable2(_table);

	}

	public void fechas() {

		Calendar Cal = Calendar.getInstance();
		int year = Cal.get(Calendar.YEAR);
		int m = Cal.get(Calendar.MONTH);
		for (int i = year - 5; i < year + 5; i++) {
			frame.get_list_anio().addItem("" + (i));
		}

		frame.get_list_anio().setSelectedIndex(5);
		frame.get_list_mes().setSelectedIndex(m);
		dias(frame.get_list_mes(), frame.get_list_anio());
	}

	public String _getTime(int row, int col, JComboBox _mes, JComboBox _anio,
			int days) {

		int mes = _mes.getSelectedIndex();
		int anio = new Integer(_anio.getSelectedItem().toString());
		int day = -1;
		try {
			day = new Integer((String) frame.getJTable2().getValueAt(row, col));
		} catch (Exception e) {
			// System.out.println("Error Dia:"+e.getMessage());
		}

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, mes);
		cal.set(Calendar.YEAR, anio);

		if (day > 0) {
			cal.set(Calendar.DAY_OF_MONTH, day);
		}

		// System.out.println(cal.getTime());
		Locale locale = Locale.getDefault();
		String s1 = "";
		Date date = null;
		DateFormat formatter;
		try {
			if (days > 0) {
				cal.add(Calendar.DATE, days);
			}
			date = cal.getTime();

			formatter = new SimpleDateFormat(default_Format, locale);
			s1 = formatter.format(date);
		} catch (Exception e) {

		}
		return s1;
	}

	public String getTime() {
		int row = frame.getJTable2().getSelectedRow();
		int col = frame.getJTable2().getSelectedColumn();
		String s1 = this._getTime(row, col, frame.get_list_mes(), frame
				.get_list_anio(), 0);
		return s1;
	}

	public void completeDiaCarga(int row, int col) {
		System.out.println("complete dia de carga");

		if (this.checkPostition(row, col)) {
			String fecha = this._getTime(row, col, frame.get_list_mes(), frame
					.get_list_anio(), 0);
			System.out.println("FECHA>" + fecha);
			frame.get_txt_fecha_carga_desde().setText(fecha);
			fecha = this._getTime(row, col, frame.get_list_mes(), frame
					.get_list_anio(), 1);
			System.out.println("FECHA>" + fecha);
			frame.get_txt_fecha_carga_hasta().setText(fecha);
			cargarLibro();
		} else {

		}

	}

	public void completeDiaCarga() {
		System.out.println("complete dia de carga");
		int row = frame.getJTable2().getSelectedRow();
		int col = frame.getJTable2().getSelectedColumn();
		this.completeDiaCarga(row, col);

	}

	public void editarCpte() {
		if (editor != null) {
			editor.dispose();
		}
		editor = new aplicacion.compras.carga.items.constructor._Constructor();
		
		String cuenta = (String) frame.get_txt_c_cuenta().getText();
		String tc = (String) frame.get_txt_c_tc().getText();
		String idcomprobante = (String) frame.get_txt_c_idcomprobante()
				.getText();
		
		String lookandfeeltheme=this.getConstructor().getLookAndFeelTheme();
		System.out.println("LoonAndFeel>"+lookandfeeltheme);
		editor.setParameter(_parametros.LookAndFeel, lookandfeeltheme);
		editor.setParameter(aplicacion.compras.carga.encabezado.interfaces._parametros.cuenta,
						cuenta);
		
		editor.setParameter(
				aplicacion.compras.carga.encabezado.interfaces._parametros.tc,
				tc);
		editor
				.setParameter(
						aplicacion.compras.carga.encabezado.interfaces._parametros.idcomprobante,
						idcomprobante);
		editor.setParameter(_parametros.connector, data.getConnectionHandler());
		editor.setParameter(_parametros.iduser, this.getConstructor().getIduser());
		editor.build(this.getConstructor());
		editor.init();

	}

	public void _cargar_cpte(int row) {
		estado="Cargando Datos";
		Convertidor C = new Convertidor();
		String cuenta = (String) frame.getJTable().getValueAt(row, 4);
		String descripcion = (String) frame.getJTable().getValueAt(row, 3);
		String tc = (String) frame.getJTable().getValueAt(row, 1);
		String idcomprobante = (String) frame.getJTable().getValueAt(row, 2);
		String fecha = (String) frame.getJTable().getValueAt(row, 0);
		String total = (String) frame.getJTable().getValueAt(row, 14);
		String fecha_carga = (String) frame.getJTable().getValueAt(row, 15);
		String fecha_control = (String) frame.getJTable().getValueAt(row, 16);
		frame.get_txt_c_cuenta().setText(cuenta);
		frame.get_txt_c_tc().setText(tc);
		frame.get_txt_c_fecha().setText(fecha);
		frame.get_txt_c_descripcion().setText(descripcion);
		frame.get_txt_c_idcomprobante().setText(idcomprobante);
		frame.get_txt_c_total().setText(C.getMoney(total, 2));
		frame.get_txt_c_fecha_carga().setText(fecha_carga);
		frame.get_txt_c_fecha_control().setText(fecha_control);

		this.cargar_cpte_detalles(row);

		this.load_impuestos(cuenta, tc, idcomprobante);
	}

	public void cargar_cpte_detalles(int row) {
		String cuenta = (String) frame.getJTable().getValueAt(row, 4);
		String tc = (String) frame.getJTable().getValueAt(row, 1);
		String idcomprobante = (String) frame.getJTable().getValueAt(row, 2);
		estado="Cargando Detalles";
		Object[][] results = data.getCpte(cuenta, tc, idcomprobante);
		if (results != null) {
			if (results.length > 0) {
				String fecha_factura = (String) results[0][0];
				String fecha_carga = (String) results[0][1];
				String subtotal = (String) results[0][2];
				String importe = (String) results[0][3];
				String fotos = (String) results[0][4];
				frame.get_txt_c_fecha_carga().setText(fecha_carga);
				frame.get_txt_c_fecha().setText(fecha_factura);
				frame.get_txt_c_total().setText(importe);
				frame.get_txt_c_cuenta().setText(cuenta);
				frame.get_txt_c_tc().setText(tc);
				frame.get_txt_c_idcomprobante().setText(idcomprobante);
				int _fotos = 0;
				try {
					_fotos = new Integer(fotos);
				} catch (Exception e) {

				}
				this.hojas = _fotos;
				if (_fotos > 0) {
					frame.get_txt_hoja_actual().setText("1");
					hoja_actual = 1;
					cargarFoto(cuenta, tc, idcomprobante, hoja_actual);
				} else {
					frame.get_txt_hoja_actual().setText("0");
				}

				frame.get_txt_hoja_total().setText("" + _fotos);
				
				

			}

		}
	}

	public void setControl() {
		int row = frame.getJTable().getSelectedRow();
		if (row >= 0 & row <= frame.getJTable().getRowCount() - 1) {
			String date = data.getSystemDate();
			frame.getJTable().setValueAt(date, row,
					frame.getJTable().getColumnCount() - 1);
			frame.get_txt_c_fecha_control().setText(date);
			boolean ok = this.updateControl("getdate()");

		}
		frame.getJTable().repaint();
		calculate_controlado();

	}

	public void utiliza_calendario(boolean ok) {
		frame.getJTable2().setEnabled(ok);

		if (ok) {
			this.completeDiaCarga();
		} else {
			this.clonarFechas();
		}
		this.cargarLibro();
	}

	public boolean updateControl(String fecha) {
		String cuenta = frame.get_txt_c_cuenta().getText();
		String tc = frame.get_txt_c_tc().getText();
		String idcomprobante = frame.get_txt_c_idcomprobante().getText();
		String q = data.getUpdate(cuenta, tc, idcomprobante, fecha);
		data.clearBatch();
		data.addBatch(q);
		boolean ok = !data.executeBatch();
		return ok;
	}

	public void unsetControlAll() {
		int total = frame.getJTable().getRowCount();
		if (preguntar("confirmar",
				"marca todos los comprobantes de esta tabla como no controlados?")) {
			data.clearBatch();
			for (int i = 0; i < total; i++) {
				frame.getJTable().setValueAt("", i,
						frame.getJTable().getColumnCount() - 1);
				frame.get_txt_c_fecha_control().setText("");
				String tc = frame.getJTable().getValueAt(i, 1).toString();
				String cuenta = frame.getJTable().getValueAt(i, 4).toString();
				String idcomprobante = frame.getJTable().getValueAt(i, 2)
						.toString();
				String q = data.getUpdate(cuenta, tc, idcomprobante, null);
				System.out.println(">" + q);
				data.addBatch(q);
			}
			boolean ok = !data.executeBatch();
			if (ok) {
				aviso("La operacion se concreto ");
			} else {
				error("hubo un error al ejecutar la operacion");
			}
			frame.getJTable().repaint();
			calculate_controlado();
		}

	}

	public void unsetControl() {
		int row = frame.getJTable().getSelectedRow();
		if (row >= 0 & row <= frame.getJTable().getRowCount() - 1) {
			frame.getJTable().setValueAt("", row,
					frame.getJTable().getColumnCount() - 1);
			frame.get_txt_c_fecha_control().setText("");
			this.updateControl("null");
		}
		frame.getJTable().repaint();
		calculate_controlado();

	}

	public void cargar_cpte(int row) {

		if (row >= 0 & row <= frame.getJTable().getRowCount() - 1) {
			Runnable execute=new Runnable(){
				public void run(){
					cleanPreview();	
				}
			};
			if (javax.swing.SwingUtilities.isEventDispatchThread()){
				cleanPreview();
			}else{
				javax.swing.SwingUtilities.invokeLater(execute);
			}
			this._cargar_cpte(row);
			
		}
		
	}

	private void crear_asiento(String imputacion) {

		Object[][] results = new Object[][] { { imputacion, "Subtotal" },
				{ "11203", "Iva   21%" }, { "11261", "Iva 10.5%" },
				{ "11262", "Iva   27%" }, { "11263", "Retencion Iva " },
				{ "11208", "RG 3337" },
				{ "11209", "Percepcion Ingresos Brutos " },
				{ "11210", "Retencion Ganancias " },
				{ imputacion, "Importe Neto No Gravado " },
				{ imputacion, "Impuestos Internos " },
				{ imputacion, "Ajuste por redondeo " },

		};
		Object[][] tmp = null;
		if (results != null) {
			if (results.length > 0) {
				tmp = new Object[results.length][3];
				for (int i = 0; i < results.length; i++) {
					String _imputacion = (String) results[i][0];
					String imputacion_alias = (String) results[i][1];
					Object[] tmpx = new Object[] { _imputacion,
							imputacion_alias, "0.0" };
					tmp[i] = tmpx;
				}

			}
		}
		final Object[][] _tmp=tmp;
		Runnable execute=new Runnable(){
			public void run(){
				create_table(_tmp);	
			}
		};
		if (javax.swing.SwingUtilities.isEventDispatchThread()){
			create_table(_tmp);
		}else{
			javax.swing.SwingUtilities.invokeLater(execute);
		}

	}

	private void load_impuestos(String idproveedor, String tc,
			String idcomprobante) {
		
		Object[][] results = data.getImpuestos(idproveedor, tc, idcomprobante);
		String imputacion = "";
		Object[][] tmp = data.getImputacion(idproveedor, tc, idcomprobante);
		if (tmp != null) {
			if (tmp.length > 0) {
				imputacion = (String) tmp[0][0];
			}
		}
		if (results != null) {
			if (results.length > 0) {
				this.crear_asiento(imputacion);
				
				final Object[][] _tmp=results;
				Runnable execute=new Runnable(){
					public void run(){
						processData(_tmp);	
					}
				};
				if (javax.swing.SwingUtilities.isEventDispatchThread()){
					processData(_tmp);
				}else{
					javax.swing.SwingUtilities.invokeLater(execute);
				}	
			
			}
		}
	}

	public void processData(Object[][] results){
		for (int i = 0; i < results.length; i++) {
			String _secuencia = (String) results[i][0];
			String _cuenta = (String) results[i][1];
			String _importe = (String) results[i][2];
			int sec = -1;
			double importe = 0.0;
			try {
				sec = new Integer(_secuencia);
				importe = new Double(_importe.replaceAll(",", ""));
			} catch (Exception e) {

			}
			if (i > 0) {
				if (sec >= 0) {
					sec--;
					Convertidor C = new Convertidor();
					frame.getJTable1().setValueAt(C.getMoney(importe, 2), sec, 2);

				}

			}

		}
	}
	
	public void cargarLibro() {
		String desde = "01-01-2000";
		String hasta = "01-01-2120";
		if (frame.get_chk_utiliza_fecha_factura().isSelected()){
			desde = frame.get_txt_fecha_desde().getText();
			hasta = frame.get_txt_fecha_hasta().getText();
		}else{
			desde = "01-01-2000";
			hasta = "01-01-2020";
		}
		String carga_desde = frame.get_txt_fecha_carga_desde().getText();
		String carga_hasta = frame.get_txt_fecha_carga_hasta().getText();
		if (frame.get_chk_utiliza_fecha_carga().isSelected()){
			carga_desde = frame.get_txt_fecha_carga_desde().getText();
			carga_hasta = frame.get_txt_fecha_carga_hasta().getText();
		}else{
			carga_desde = "01-01-2000";
			carga_hasta = "01-01-2120";
		}
		

		String idproveedor = frame.get_txt_idproveedor().getText();
		Object[][] results = null;
		results = data.getLibro(desde, hasta, carga_desde, carga_hasta,
				idproveedor);
		boolean ok = false;
		if (results != null) {
			if (results.length > 0) {
				ok = true;
				this.create_table_libro(results);
			} else {
				frame.setJTable(null);
			}
		} else {
			frame.setJTable(null);
		}
		if (ok) {
			calculate_controlado();
			CalculateTotals();
		}

		this.cleanPreview();
	}

	public double[] getControlCalendar(int row, int col) {
		double[] perc = null;
		String carga_desde = this._getTime(row, col, frame.get_list_mes(),
				frame.get_list_anio(), 0);
		String carga_hasta = this._getTime(row, col, frame.get_list_mes(),
				frame.get_list_anio(), 1);
		String desde = frame.get_txt_fecha_desde().getText();
		String hasta = frame.get_txt_fecha_hasta().getText();
		perc = this.getControl(carga_desde, carga_hasta, desde, hasta);
		return perc;
	}

	private double[] getControl(String _carga_desde, String carga_hasta,
			String desde, String hasta) {
		String cuenta = frame.get_txt_idproveedor().getText();
		Object[][] results = data.getControles(_carga_desde, carga_hasta,
				cuenta, desde, hasta);
		double perc = 0.0;
		double cargas = 0.0;
		double controles = 0.0;
		if (results != null) {
			if (results.length > 0) {

				String _cargas = (String) results[0][0];
				String _control = (String) results[0][1];
				try {
					controles = new Double(_control);
					cargas = new Double(_cargas);
				} catch (Exception e) {

				}

			}
		}
		if (cargas > 0) {
			perc = (controles / cargas);
		}
		return new double[] { perc, cargas };
	}

	public boolean checkPostition(int row, int col) {
		boolean ok = false;
		int max = this.get_days();
		if (row < 0)
			row = 0;
		int number = row * 7 - this.get_day_week() + col + 1;// this.get_day_week()
		System.out.println("initial day=" + this.get_day_week() + " dias="
				+ max + " number=" + number);
		if (number <= max & number > 0) {
			if (row <= frame.getJTable2().getRowCount() - 1 & row >= 0) {
				if (col <= frame.getJTable2().getColumnCount() - 1 & col >= 0) {
					ok = true;
				}

			}
		}

		return ok;

	}

	public void buscarProveedor(JTextField tx) {
		if (vProveedor != null) {
			vProveedor.close();
		}
		vProveedor = new aplicacion.herramientas.java.visualizadores.Proveedor(
				this.getConstructor());
		int n = vProveedor.Buscar(tx);
		if (n == 0) {
			aviso("no hay Proveedors con ese codigo");
		}
	}

	public void cargarFotoback(String cuenta, String tc, String idcomprobante,
			int hoja) {

		String secuencia = "" + hoja;
		String filex = server + cuenta + "-" + idcomprobante + "-" + secuencia
				+ ".jpg";

		Object[][] results = data.getFotos(cuenta, tc, idcomprobante);
		frame.getCanvas().setFilename(filex);

		frame.getCanvas().setVisible(true);
		System.out.println(filex);
		if (results != null) {
			if (results.length > 0) {
				String foto = filex;

				frame.getCanvas().setFilename(foto);
				frame.getCanvas().zoom(0);
				frame.getCanvas().setVisible(true);
			}
		}
	}
	
	
	
}
