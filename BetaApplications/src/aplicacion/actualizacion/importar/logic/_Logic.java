package aplicacion.actualizacion.importar.logic;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import aplicacion.herramientas.java.launcher.logic.SwingWorker;
import java.io.*;
import java.util.zip.*;

import java.util.zip.ZipInputStream;
import java.util.zip.ZipEntry;

import aplicacion.herramientas.java.table.Column;
import aplicacion.herramientas.java.table.CustomTable;
import aplicacion.herramientas.java.xml.Element;
import aplicacion.herramientas.java.xml.XML;
import aplicacion.modelo.logic.Logic;
import aplicacion.modelo.logic.Data;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.*;
import java.io.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.Vector;
import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.io.IOException;
import java.net.URLClassLoader;
import java.net.MalformedURLException;
import aplicacion.herramientas.java.*;

import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTP;
import org.tmatesoft.svn.core.SVNErrorCode;
import org.tmatesoft.svn.core.SVNErrorMessage;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNProperties;
import org.tmatesoft.svn.core.SVNProperty;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.io.ISVNEditor;
import org.tmatesoft.svn.core.io.ISVNReporterBaton;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import aplicacion.actualizacion.importar.gui.*;
import aplicacion.actualizacion.importar.interfaces.*;
import aplicacion.actualizacion.importar.logic.*;
import aplicacion.actualizacion.importar.test.*;

import java.util.List;
import aplicacion.sistema.version.logic.ftp;
import aplicacion.sistema.version.logic.FtpWrapperTest;
import aplicacion.sistema.version.logic.JakartaFtpWrapper;
import aplicacion.sistema.version.logic.JarFileLoader;

public class _Logic extends Logic {
	private _Frame frame = null;
	private _Data data = null;
	private String ftpserver;
	private String username;
	private String password;
	private String download;
	private String file;
	private String version;
	//private String destination = "";
	private JakartaFtpWrapper ftp = null;
	private String directory = "";
	private int exitos = 0;
	private String estado = "";
	private Object[] test;
	private boolean update = false;
	private List<String> resumen = null;
	private int current;
	private int lenght, max;
	private String emailFrom = "betacoresystems@gmail.com";
	private String emailTo = "agustinwisky@gmail.com";
	private String emailpassword = "ceci@1985";
	private boolean debug, done, canceled, override;
	private Timer Timer; // @jve:decl-index=0:
	private Crono crono;
	private XML xml;
	private String tc = "UPDW";
	private List<Element> sources;

	class updateTask {
		updateTask() {
			download_updates();
		}
	}

	class checkTask {
		checkTask() {
			check();
		}
	}

	public void goUpdate() {
		_goUpdate();
	}

	public String getEstado() {
		return estado;
	}

	public void _goUpdateNoFrame() {
		System.out.println("Actualizando Modo Automatico");
		SwingWorker worker = null;
		worker = new SwingWorker() {
			@Override
			public Object construct() {
				return new updateTask();
			}
		};

		update = true;
		worker.start();
	}

	public void _goUpdate() {
		System.out.println("Actualizando Modo Grafico GUI");
		this.createTimer();
		estado = "Descargando Actualizacion";
		frame.get_btn_descargar().setEnabled(false);
		SwingWorker worker = null;
		worker = new SwingWorker() {
			@Override
			public Object construct() {
				return new updateTask();
			}
		};

		if (Timer != null) {
			Timer.start();
		}
		worker.start();
	}

	public String getDestination(){
		String destination="";
		if (this.isLinux()){
			destination="_actualizacion/";
		}else{
			destination="C:\\windows\\temp\\_actualizacion";
		}
		return destination;
	}
	
	public void goCheck() {
		this.createTimer();
		frame.get_txt_revision_disponible().setText("");
		frame.get_btn_descargar().setEnabled(false);
		frame.getJProgressBar().setIndeterminate(true);
		estado = "Buscando Version Disponible";
		SwingWorker worker = null;
		worker = new SwingWorker() {
			@Override
			public Object construct() {

				return new checkTask();
			}
		};
		if (Timer != null) {
			Timer.start();
		}
		worker.start();
	}

	public void setFrame(JFrame _frame) {
		this.frame = (_Frame) _frame;
		super.setFrame(_frame);
	}

	public void setData(Data _data) {
		this.data = (_Data) _data;
		super.setData(_data);
	}

	public boolean necesita_actualizar() {
		boolean nec = false;
		String rev_actual = frame.get_txt_revision_actual().getText();
		String rev_disponible = frame.get_txt_revision_disponible().getText();
		nec = rev_actual.compareTo(rev_disponible) != 0;
		return nec;
	}

	public void cargar() {
		this.load_variables();
		this.goCheck();
		frame.get_btn_descargar().setEnabled(false);

	}

	public void actualizar() {
		try {
			this.update();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void downloadFromFTP(FTPFile file, String localfile) {
		try {
			OutputStream stD = new FileOutputStream(localfile);
			ftp.retrieveFile(file.getName(), stD);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean deleteDirectory(File path) {
		if (path.exists()) {
			File[] files = path.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					deleteDirectory(files[i]);
				} else {
					files[i].delete();
				}
			}
		}
		return (path.delete());
	}

	public void prepareDestination() {
		String destination=this.getDestination();
		File file = new File(destination);
		if (file.exists()) {
			deleteDirectory(file);
		}
		boolean ok = file.mkdir();
		System.out.println("directory creation > " + destination + ">?" + ok);
	}

	public boolean downloadFromFTP(String serverfile, String localfile,
			long filesize) {
		System.out.println(serverfile + " size:" + filesize + " " + localfile);

		boolean ok = true;
		this.lenght = 100;
		current = 0;
		try {
			InputStream stO = new BufferedInputStream(ftp
					.retrieveFileStream(serverfile), ftp.getBufferSize());

			OutputStream stD = new FileOutputStream(localfile);
			// ftp.enterRemotePassiveMode();

			long test = org.apache.commons.net.io.Util.copyStream(stO, stD, ftp
					.getBufferSize(), filesize,
			// org.apache.commons.net.io.CopyStreamEvent.UNKNOWN_STREAM_SIZE,
					new org.apache.commons.net.io.CopyStreamAdapter() {
						public void bytesTransferred(
								long totalBytesTransferred,
								int bytesTransferred, long streamSize) {
							// System.out.println("bytetransfered: "+bytesTransferred+"|"+totalBytesTransferred+" "+streamSize);
							current = (int) Math
									.ceil(((double) totalBytesTransferred / (double) streamSize) * 100);
							// System.out.println("current: "+current);
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
			System.out.println(serverfile + " downloaded:" + test);
			// ftp.completePendingCommand();
			/*
			 * //stD.close(); boolean ok= if (ok){ if (!canceled){
			 * 
			 * //this.updateRevision();
			 * 
			 * //actualiza(); }
			 * 
			 * }else{ error("No se completo la operacion de descarga"); }
			 */
			System.out.println("current done?: " + current);
		} catch (Exception e) {
			e.printStackTrace();
			ok = false;
		}

		return ok;
	}

	public void _actualizar() {
		/*
		 * Runnable _execute=new Runnable(){ public void run(){ Sync sync=new
		 * Sync(); sync.initConsole(); sync.setSource(new File(destination));
		 * sync.setTarget(new
		 * File("C:\\Archivos de Programa\\Beta Systems\\Beta"));
		 * sync.prepareParameters(); int n=sync.syncDirectory(); } };
		 * this.invokeLater(_execute);
		 */
		_actualizar_old();

	}

	public void _actualizar_old() {

		String newline = System.getProperty("line.separator");
		String download = "C:\\Archivos de Programa\\Beta Systems\\Beta\\Beta.exe";

		try {
			String command = "cmd /c \"" + download + "\"";
			File file = new File(download);

			if (file.exists()) {
				Process child = Runtime.getRuntime().exec(command);

			} else {
				error("No se Encuentra el archivo " + command);
			}

		} catch (IOException e) {
			e.printStackTrace();
			this.displayError("Error En Actualizacion", e.getMessage(), e
					.getLocalizedMessage(), e);
		}

		System.exit(0);
	}

	public void actualiza() {

		Runnable showModalDialog = new Runnable() {
			public void run() {
				/*
				 * if(preguntar("Confirmar",
				 * "Descarga Completa. Desea Actualizar Ahora?")){
				 * 
				 * }
				 */
				_actualizar();
			}
		};
		javax.swing.SwingUtilities.invokeLater(showModalDialog);

	}

	public void update() {
		long rev = 0;
		ftp = new JakartaFtpWrapper();
		try {
			if (ftp.connectAndLogin(ftpserver, username, password)) {
				System.out.println("Connected to " + ftpserver);
				try {
					ftp.setPassiveMode(true);

					ftp.changeWorkingDirectory(directory);

					Vector v = ftp.getFiles();
					for (int i = 0; i < v.size(); i++) {
						FTPFile file = (FTPFile) v.get(i);
						if (file.getName().compareTo(this.file) == 0) {
							this.lenght = 100;

							downloadFromFTP(file.getName(), download, file
									.getSize());
						}

					}
				} catch (Exception ftpe) {
					ftpe.printStackTrace();
				} finally {
					try {
						ftp.logout();
						ftp.disconnect();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						// e.printStackTrace();
					}
				}
			} else {
				System.out.println("Unable to connect to" + ftpserver);
			}
			System.out.println("Finished");
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.updateRevision();
		done = true;
	}

	public void load_variables() {
		sources = new ArrayList<Element>();
		version = data.getUltmimoAplicado();
		try {
			frame.get_txt_revision_actual().setText(version);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		this.ftpserver = (String) data.getParametroSqlite("ftp")[0][1];
		this.username = (String) data.getParametroSqlite("ftp_user")[0][1];
		this.password = (String) data.getParametroSqlite("ftp_password")[0][1];
		this.directory = (String) data.getParametroSqlite("ftp_directory")[0][1];
		this.file = (String) data.getParametroSqlite("ftp_file")[0][1];
		this.download = (String) data.getParametroSqlite("ftp_download")[0][1];
	}

	public boolean downloadFile(String directory, String filex,
			String destination, boolean binary) {
		boolean ok = true;
		Date rev = null;
		try {
			// JakartaFtpWrapper
			ftp = new JakartaFtpWrapper();

			if (ftp.connectAndLogin(ftpserver, username, password)) {
				System.out.println("Connected to " + ftpserver);
				try {
					binary = true;
					if (binary) {
						System.out.println("Binary Mode");
						ftp.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
						ftp.setFileType(FTP.BINARY_FILE_TYPE);
						ftp.setPassiveMode(true);
					} else {
						System.out.println("ASCII Mode");
						ftp.setFileTransferMode(FTP.COMPRESSED_TRANSFER_MODE);
						ftp.setFileType(FTP.ASCII_FILE_TYPE);
						ftp.setPassiveMode(false);
					}

					boolean dir = ftp.changeWorkingDirectory(directory);

					System.out.println("ftp>" + directory + "? " + dir);

					Vector v = ftp.getFiles();
					for (int i = 0; i < v.size(); i++) {
						FTPFile file = (FTPFile) v.get(i);
						System.out.println("?>" + file.getName());
						if (file.getName().compareTo(filex) == 0) {
							estado = "Descargando " + filex;
							if (this.isLinux()){
								ok = downloadFromFTP(filex, destination + "/"
										+ filex, file.getSize());
							}else{
								ok = downloadFromFTP(filex, destination + "\\"
										+ filex, file.getSize());	
							}
							

						}

					}
				} catch (Exception ftpe) {
					ftpe.printStackTrace();
					ok = false;
				}
				/*
				 * finally { ftp.logout(); ftp.disconnect(); }
				 */
			} else {
				ok = false;
				System.out.println("Unable to connect to" + ftpserver);
			}
			System.out.println("Finished");
		} catch (Exception e) {
			e.printStackTrace();
			ok = false;
		}

		return ok;
	}

	public String getLastVersion() {
		String destination=this.getDestination();
		String revision = "";
		this.prepareDestination();
		boolean ok = this.downloadFile("/beta", "actualizacion.xml",
				destination, false);
		if (ok) {
			this.init_xml();
			this.process_changes();
		}
		return revision;
	}

	public boolean isDone() {
		return done;
	}

	public boolean check() {
		boolean actualizaciones = false;
		System.out.println("Proceso check: verificando que hay disponible para actualizar de precios");
		sources = new ArrayList<Element>();
		String destination=this.getDestination();
		this.prepareDestination();
		
		boolean ok = this.downloadFile("/beta", "actualizacion.xml",
				destination, false);
		if (ok) {
			this.init_xml();
			actualizaciones = this.process_changes();
		}
		done = true;
		return actualizaciones;
	}

	private void create_table(Object[][] results) {
		CustomTable table = new CustomTable();
		Column col = new Column();

		col = new Column();
		col.setName("");
		col.setWidth(40);
		col.setEditable(true);
		col.setClass(Boolean.class);
		table.addColumn(col);

		col = new Column();
		col.setName("id");
		col.setWidth(80);
		col.setEditable(false);
		table.addColumn(col);

		col = new Column();
		col.setName("fecha");
		col.setWidth(120);
		col.setEditable(false);
		table.addColumn(col);

		col = new Column();
		col.setName("idproveedor");
		col.setWidth(120);
		col.setEditable(false);
		table.addColumn(col);

		col = new Column();
		col.setName("nombre");
		col.setWidth(180);
		col.setEditable(false);
		table.addColumn(col);
		table.setData(results);
		table.addKeyListener(this._constructor.getKeyListener());
		Font fuente = new Font("Arial", Font.PLAIN, 8);
		table.setHeaderFont(fuente);
		table.build();
		table.fillData();

		frame.setJTable(table.getTable());
	}

	public void createTable(List<Element> versiones) {
		Object[][] results = new Object[versiones.size()][5];
		for (int i = 0; i < versiones.size(); i++) {
			Element e = versiones.get(versiones.size() - 1 - i);
			String id = e.getAtributo("id").getValor().toString();
			String fecha = e.getAtributo("fecha").getValor().toString();
			String idproveedor = e.getAtributo("idproveedor").getValor()
					.toString();
			String nombre = e.getAtributo("nombre").getValor().toString();
			results[i][0] = false;
			results[i][1] = id;
			results[i][2] = fecha;
			results[i][3] = idproveedor;
			results[i][4] = nombre;
		}
		final Object[][] _results = results;
		Runnable execute = new Runnable() {
			public void run() {
				create_table(_results);
			}
		};
		this.invokeLater(execute);
	}

	/*
	 * public void check(){ Object[][] results=data.getLastUpdate(); if
	 * (results!=null){ if (results.length>0){ String version=(String)
	 * results[0][0]; String fecha=(String) results[0][1]; String
	 * modificacion=(String) results[0][2];
	 * frame.get_txt_revision_disponible().setText(version);
	 * frame.getJTextArea().setText(modificacion);
	 * frame.get_txt_fecha().setText(fecha); done=true; } } }
	 */

	public void updateRevision() {
		String revision = frame.get_txt_revision_disponible().getText();
		data.updateParametro("version", revision);
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void createTimer() {
		crono = new Crono();
		crono.start();
		current = 0;
		done = false;
		canceled = false;
		Timer = new Timer(300, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (done) {
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
		frame.get_btn_descargar().setEnabled(true);
		frame.getJProgressBar().setString("");
	}

	public void updateBar() {
		frame.getJProgressBar().setMaximum(lenght);
		frame.getJProgressBar().setValue(current);
		frame.getJProgressBar().setString(
				estado + " " + current + "/" + lenght + " " + crono.elapsed());
		frame.getJProgressBar().setStringPainted(true);
	}

	public void doCancel() {
		if (preguntar("Confirma", "Cancela Tarea en Ejecucion?")) {
			canceled = true;
			endbar();
			try {
				ftp.abort();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.resumen.add("Proceso Cancelado");
			frame.get_btn_cancelar().setEnabled(false);
		}
	}

	public void init_xml() {
		xml = new XML();
		String destination=this.getDestination();
		if (this.isLinux()){
			xml.setConfigFile(destination + "/actualizacion.xml");
		}else{
			xml.setConfigFile(destination + "\\actualizacion.xml");	
		}
			
		
		xml.readAll();
	}

	public void download_updates() {
		String destination=this.getDestination();
		System.out.println("Descargando Actualizaciones");
		xml = new XML();
		if (this.isLinux()){
			xml.setConfigFile(destination + "/actualizacion.xml");
		}else{
			xml.setConfigFile(destination + "\\actualizacion.xml");	
		}
		xml.readAll();
		this.download_sources();
	}

	public boolean report(String idcomprobante, String _idproveedor,
			String linea, Object[][] results) {
		boolean ok = true;
		String destination=this.getDestination();
		try {
			// /aca va el lector de archivo log de la tarea
			String _folder="";
			if (this.isLinux()){
				_folder=destination + "/" + idcomprobante;
			}else{
				_folder=destination + "\\" + idcomprobante;
			}
			File dir = new File(_folder);
			if (!dir.exists()) {
				dir.mkdir();
			}
			String file_path = "";
			if (this.isLinux()){
				file_path = _folder + "/"+ _idproveedor + ".txt";
			}else{
				file_path = _folder + "\\"+ _idproveedor + ".txt";
			}
			
			File file = new File(file_path);
			file = file.getAbsoluteFile();
			if (file.exists())
				file.delete();
			file.createNewFile();
			BufferedWriter out = new BufferedWriter(new FileWriter(file));
			for (int i = 0; i < results.length; i++) {
				String line = "";
				for (int j = 0; j < results[0].length; j++) {
					if (line.length() > 0) {
						line += "	";
					}
					line += results[i][j].toString();
				}

				try {
					out.write(line);
					out.newLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					ok = false;
				}
			}

			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				ok = false;
			}
		} catch (Exception e) {
			ok = false;
		}
		return ok;
	}

	public String[] getVersion() {
		String[] tmp = null;
		this.prepareDestination();
		String destination=this.getDestination();
		boolean ok = this.downloadFile("/actualizacion", "actualizacion.xml",
				destination, false);
		if (ok) {
			xml = new XML();
			if (this.isLinux()){
				xml.setConfigFile(destination + "/actualizacion.xml");
			}else{
				xml.setConfigFile(destination + "\\actualizacion.xml");	
			}
			
			xml.readAll();
			tmp = this.get_process_changes(false);
		}
		return tmp;
	}

	public boolean download_update(Element e) {
		String source = e.getAtributo("source").getValor().toString();
		String dir = e.getAtributo("directory").getValor().toString();
		String destination = e.getAtributo("destination").getValor().toString();
		String _destination=this.getDestination();
		String _folder="";
		if (this.isLinux()){
			_folder=_destination + "/" + destination;
		}else{
			_folder=_destination + "\\" + destination;
		}
		File filex = new File(_folder);
		if (!filex.exists()) {
			filex.mkdir();
		}
		System.out.println("update source: " + source + " destination:"
				+ destination);
		boolean ok =false;
		ok=this.downloadFile(dir, source, _folder, true);
		return ok;
	}

	public void process_update(Element e) {
		String source = e.getAtributo("source").getValor().toString();
		System.out.println("update source: " + source);
	}

	public void process_comentario(Element e) {
		String comentario = e.getAtributo("text").getValor().toString();

	}

	public void process_version(Element e) {
		String id = e.getAtributo("id").getValor().toString();
		String fecha = e.getAtributo("fecha").getValor().toString();
		frame.get_txt_revision_disponible().setText(id);
		frame.get_txt_fecha().setText(fecha);
		System.out.println("version " + id + " " + fecha);
		int i = 0;
		while (i < e.getElements().size()) {
			Element ex = e.getElements().get(i);
			if (ex.getId().compareTo("comentario") == 0) {
				process_comentario(ex);
			}
			if (ex.getId().compareTo("update") == 0) {
				process_update(ex);
			}
			i++;
		}
	}

	public boolean download_version(Element e) {

		String id = e.getAtributo("id").getValor().toString();
		String fecha = e.getAtributo("fecha").getValor().toString();
		System.out.println("version " + id + " " + fecha);
		int i = 0;
		boolean ok = false;
		int errors = 0;
		while (i < e.getElements().size()) {
			Element ex = e.getElements().get(i);

			if (ex.getId().compareTo("update") == 0) {
				this.createTimer();
				ok = download_update(ex);
				if (!ok) {
					errors++;
				}
			}
			i++;
		}
		ok = (errors <= 0);

		return ok;
	}

	public void process() {
		Element root = xml.getRoot();

		int i = 0;
		while (i < root.getElements().size()) {
			Element e = root.getElements().get(i);
			if (e.getId().compareTo("version") == 0) {
				process_version(e);
			}
			i++;
		}

	}

	public boolean selected(String id) {
		boolean selected = false;
		int i = 0;
		while (i < frame.getJTable().getRowCount() & !selected) {

			boolean b = (Boolean) frame.getJTable().getValueAt(i, 0);
			String _id = (String) frame.getJTable().getValueAt(i, 1);
			if (_id.compareTo(id) == 0) {
				selected = b;
			}
			if (!selected) {
				i++;
			}
		}
		return selected;
	}

	/**
	 * Prepara las actualizaciones detectadas que pueden ser aplicadas
	 * 
	 * @return
	 */
	public boolean process_changes() {
		boolean actualizaciones = false;
		Element root = xml.getRoot();
		List<Element> versiones = new ArrayList<Element>();
		String installed_version = "";
		try {
			installed_version = frame.get_txt_revision_actual().getText();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String newer_version = installed_version;
		String newer_fecha = "";
		int i = 0;
		while (i < root.getElements().size()) {
			Element e = root.getElements().get(i);
			if (e.getId().compareTo("version") == 0) {
				String id = e.getAtributo("id").getValor().toString();
				String idproveedor = e.getAtributo("proveedor").getValor()
						.toString();
				String fecha = e.getAtributo("fecha").getValor().toString();
				if (frame.get_chk_mostrar_aplicadas().isSelected()){
					versiones.add(e);
					newer_version = id;
					newer_fecha = fecha;
				}else{
					if (!data.aplicado(id)) {
						if (!data.viejo(id, idproveedor)) {
							versiones.add(e);
							newer_version = id;
							newer_fecha = fecha;
						} else {
							System.out.println("la version " + id
									+ " del proveedor " + idproveedor
									+ " es vieja. no se aplica");
						}

					}					
				}

			}
			i++;
		}
		if (versiones.size() > 0) {
			actualizaciones = true;
		}
		if (frame != null) {
			if (versiones.size() > 0) {

				this.createTable(versiones);
			} else {

				Runnable execute = new Runnable() {
					public void run() {
						frame.setJTable(null);
					}
				};
				this.invokeLater(execute);
			}
			frame.get_txt_revision_disponible().setText(newer_version);
			frame.get_txt_fecha().setText(newer_fecha);
		}

		return actualizaciones;
	}

	public String[] get_process_changes(boolean download) {
		Element root = xml.getRoot();
		String NEW_LINE = System.getProperty("line.separator");
		String comentarios = "";

		int _installed_version = new Integer(version.replace(".", ""));
		String newer_version = version;
		String newer_fecha = "";
		int i = 0;
		if (root != null) {
			while (i < root.getElements().size()) {
				Element e = root.getElements().get(i);
				if (e.getId().compareTo("version") == 0) {
					String id = e.getAtributo("id").getValor().toString();
					String fecha = e.getAtributo("fecha").getValor().toString();
					String coment = e.getAtributo("comentario").getValor()
							.toString();
					int _updated_version = new Integer(id.replace(".", ""));
					if (_updated_version > _installed_version) {
						newer_version = id;
						comentarios += "<version:" + id + " " + fecha + ">";
						comentarios += NEW_LINE;
						comentarios += coment;
						comentarios += NEW_LINE;
						newer_fecha = fecha;
						Element version = e;
						int vi = 0;
						while (vi < version.getElements().size()) {
							Element ve = version.getElements().get(vi);

							if (ve.getId().compareTo("update") == 0) {
								this.addSource(ve);

							}
							vi++;
						}
					}

				}
				i++;
			}

		}
		String[] tmp = null;
		if (comentarios.compareTo("") == 0) {
			comentarios = "Usted tiene instalada la ultima version disponible";

		} else {
			tmp = new String[] { newer_version, comentarios };
		}

		return tmp;
	}

	public boolean decompress(String filename, String folder) {
		boolean ok = true;
		int BUFFER = 2048;
		System.out.println("DECOMPRESS> " + filename);
		try {
			BufferedOutputStream dest = null;
			FileInputStream fis = new FileInputStream(filename);
			ZipInputStream zis = new ZipInputStream(
					new BufferedInputStream(fis));
			ZipEntry entry;
			while ((entry = zis.getNextEntry()) != null) {
				System.out.println("Extracting: " + entry);
				int count;
				byte data[] = new byte[BUFFER];
				// write the files to the disk
				String destination=this.getDestination();
				FileOutputStream fos = null;
				if (this.isLinux()){
					fos = new FileOutputStream(destination
							+ "/" + folder + "/" + entry.getName());
				}else{
					fos = new FileOutputStream(destination
							+ "//" + folder + "//" + entry.getName());
				}
				
				dest = new BufferedOutputStream(fos, BUFFER);
				while ((count = zis.read(data, 0, BUFFER)) != -1) {
					dest.write(data, 0, count);
				}
				dest.flush();
				dest.close();
			}
			zis.close();
		} catch (Exception e) {
			e.printStackTrace();
			ok = false;
		}
		return ok;
	}

	public void download_sources() {
		exitos = 0;
		resumen = new ArrayList<String>();

		String idcomprobante = data.getProximoPGCorrecto(tc);
		System.err.println(">>>>>  idcomprobante: "+idcomprobante);
		Element root = xml.getRoot();
		List<Element> versiones = new ArrayList<Element>();
		int i = 0;

		while (i < root.getElements().size() & !canceled) {
			Element e = root.getElements().get(i);
			if (e.getId().compareTo("version") == 0 & !canceled) {
				String id = e.getAtributo("id").getValor().toString();

				String fecha = e.getAtributo("fecha").getValor().toString();
				String idproveedor = e.getAtributo("idproveedor").getValor()
						.toString();
				String linea = "";
				if (!data.aplicado(id) & !canceled) {
					boolean selected = true;
					try {
						selected = this.selected(id);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (selected & !canceled) {
						String _descripcion=data.getProveedor(idproveedor);
						resumen.add("Actualizacion WEB:" + id + " Proveedor:"
								+ idproveedor +" "+_descripcion+ " UPDW:" + idcomprobante);
						// versiones.add(e);

						Element version = e;
						int vi = 0;
						while (vi < version.getElements().size() & !canceled) {
							Element ve = version.getElements().get(vi);

							if (ve.getId().compareTo("update") == 0) {
								// actualizacion
								boolean ok = this.download_update(ve);
								if (ok & !canceled) {
									resumen
											.add("Descarga de Archivo Con Actualizacion [OK]");
									String destination = ve.getAtributo(
											"destination").getValor()
											.toString();
									String source = ve.getAtributo("source")
											.getValor().toString();
									String _destination=this.getDestination();
									File filex = null;
									if (this.isLinux()){
										filex=new File(_destination
												+ "/" + destination + "/"
												+ source);
									}else{
										filex=new File(_destination
												+ "\\" + destination + "\\"
												+ source);	
									}
									
									
									System.out.println("TRYING DECOMPRESS>"
											+ filex.getAbsolutePath());
									if (filex.exists()) {
										estado = "Descomprimiendo";
										ok = this
												.decompress(filex
														.getAbsolutePath(),
														destination);
										if (ok & !canceled) {
											resumen
													.add("Descompresion de Archivo Con Actualizacion [OK]");
											String textfile = source
													.replaceAll(".zip", ".txt");
											estado = "Cargando Informacion de Actualizacion";
											Object[][] results=null;
											try {
												String path=_destination
												+ "\\"
												+ destination
												+ "\\" + textfile;
												results = this
												
														.read_tabulated_file(path);
												System.out.println("buscando archivo "+path);
											} catch (Exception e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}
											if (results != null) {
												ok = this._taskworkUpdate(
														idcomprobante,
														idproveedor, linea,
														results);

												if (ok & !canceled) {
													resumen
															.add("Actualizacion [OK]");

													if (ok & !canceled) {
														ok = data
																.setAplicado(
																		id,
																		idproveedor);

													}

													if (ok & !canceled) {
														ok = data.updateTC(tc);
														if (ok & !canceled) {
															exitos++;
														}
													}
													if (ok) {
														String pol="";
														
														Object[][] report=data.getReportPrices(idproveedor, pol, textfile, idcomprobante, id);
														ok = this.report(
																idcomprobante,
																idproveedor,
																linea, report);
														if (ok) {
															resumen
																	.add("Reporte [OK]");
														} else {
															resumen
																	.add("Reporte [ERROR]");
														}

													}

												} else {
													resumen
															.add("Actualizacion [ERROR]");
												}
											}
										} else {
											resumen
													.add("Descompresion de Archivo Con Actualizacion [ERROR]");
										}
									} else {
										resumen
												.add("No se Encontro el archivo para descomprimir [ERROR]");
									}
								} else {
									resumen
											.add("Descarga de Archivo Con Actualizacion [ERROR]");
									System.out
											.println("No se pudo bajar correctamente "
													+ id);
								}
							}
							vi++;
						}
					}

				}

			}
			i++;
		}
		boolean compress=this.compressReport(idcomprobante);
		resumen.add("");
		if (compress){
			resumen.add("COMPRESION: [OK]");	
		}else{
			resumen.add("COMPRESION: [ERROR]");
		}
		
		if (canceled) {
			resumen.add("");
			resumen.add("******ESTA ACTUALIZACION FUE CANCELADA******");
		}

		done = true;
		if (exitos == 1) {
			this.resumen.add("Se concreto " + exitos + " operacion exitosa ");
		} else {
			if (exitos > 1) {
				this.resumen.add("Se concretaron " + exitos
						+ " operaciones exitosas ");
			} else {
				this.resumen.add("Se concretaron " + exitos
						+ " operaciones exitosas ");
			}
		}
		
		if (resumen.size() > 0) {
			System.out.println("Enviando Reporte de Actualizacion de precios por EMAIL. Se enviara lo siguiente:");
			for (int u=0;u<resumen.size();u++){
				System.out.println(resumen.get(u));	
			}
			System.out.println();
		}
		if (resumen.size() > 0) {
			this._taskworkEnviar(idcomprobante);
		}
		update = false;
	}

	public boolean isUpdating() {
		return update;
	}

	public boolean _taskworkUpdate(String idcomprobante, String _idproveedor,
			String linea, Object[][] results) {
		if (frame != null) {
			frame.getJProgressBar().setIndeterminate(false);
		}

		current = 0;
		System.out.println("Actualizando UPDW " + idcomprobante);
		boolean ok = true;
		this.lenght = results.length;
		String pol = "";

		String idproveedor = data.getEquivalente(_idproveedor);
		if (idproveedor.compareTo("") == 0) {
			idproveedor = _idproveedor;
		}
		pol = data.getPolitica(idproveedor);
		int u = 0;
		Convertidor Cv = new Convertidor();
		// Object[] test=PF.getSettings();
		Object[] test = new Integer[] { 0, 1, 3, 2 };
		int i_art = -1;
		int i_pre = -1;
		int i_des = -1;
		int i_lin = -1;
		try {
			i_art = (Integer) test[0];
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			i_des = (Integer) test[1];
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		if (test[2].getClass() == Integer.class) {
			i_pre = (Integer) test[2];
		}
		if (test[3].getClass() == Integer.class) {
			i_lin = (Integer) test[3];

		}
		String filter = linea;
		if (filter.compareTo("") != 0) {
			int qtys = 0;
			while (u < results.length & !canceled) {
				current = u;
				linea = (String) results[u][i_lin];
				if (linea.compareTo(filter) == 0) {
					qtys++;
				}
				u++;
			}
			this.lenght = qtys;
		}
		u = 0;
		current = 0;
		while (u < results.length & !canceled) {

			String codigo = (String) results[u][i_art];

			codigo = Cv.LimpiarString(codigo, " ");
			String descripcion = "";
			try {
				descripcion = (String) results[u][i_des];
			} catch (Exception e) {

			}
			// System.out.println("Do Update "+u);
			try {
				linea = (String) results[u][i_lin];
			} catch (Exception e) {

			}

			linea = Cv.remove_last_spaces(linea);
			estado = codigo + " " + linea + " ";
			String pre = (String) results[u][i_pre];
			
			// pre=Cv.normalize(pre);
			if (!pre.contains(".")) {
				pre+=".0";
			}
			/*if (pre.contains(".")) {
				pre = pre.replace(",", "");
			} else {
				pre = pre.replace(",", ".");
			}*/
			
			double precio = 0;
			try {
				precio = new Double(pre);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			boolean doit = true;
			if (filter.compareTo("") != 0) {
				if (linea.compareTo(filter) != 0) {
					doit = false;
				}
			}
			System.out.print("actualiza? "+results[u][i_art]+" "+results[u][i_des]+" "+results[u][i_pre]+" doit="+doit+" ? ");
			if (precio > 0 & doit) {
				System.out.println("Si! actualiza ");
				data.beginTransaction();
				double old = data.getPrecioViejo(idproveedor, codigo, linea);
				data.clearBatch();
				String q = "";
				if ((Math.abs(precio - old) > 0.01) | override) {
					
					if (!data.exist(codigo, linea, idproveedor)) {
						q=data.getInsert(idproveedor, codigo, linea, precio, old,
								descripcion, idcomprobante, pol);
					}else{
						q=data.getUpdate(precio, old, codigo, linea, idproveedor,
								idcomprobante);
							
					}
					data.addBatch(q);
					q=data.getInsertCodigosVariacion(codigo, linea, idproveedor, precio,old,idcomprobante);			    			
					data.addBatch(q);
				} else {
					q=data.getUpdateFecha(codigo, linea, idproveedor, idcomprobante);
					data.addBatch(q);
				}
				boolean error=data.executeBatch();
	    		if (error){
	    			data.rollbackTransaction();
	    		}else{
	    			data.commitTransaction();
	    		}
				current++;
			}else{
				System.out.println("No ");
			}

			u++;
		}

		ok = this.Actualizar(idcomprobante, idproveedor, pol);
		return ok;

	}

	public boolean Actualizar(String idcomprobante, String idproveedor,
			String pol) {
		boolean error = false;
		System.out
				.println("Actualizacion de fechas y aplicacion de precios nuevos UPDF:"
						+ idcomprobante);
		if (!canceled) {
			estado = "Aplicando Precios Nuevos";
			if (frame != null) {
				frame.getJProgressBar().setIndeterminate(true);
			}

			String iduser = this.getConstructor().getIduser();

			String q = "alter table v_ma_articulos disable trigger all ";
			System.out.println(q);
			data.beginTransaction();
			data.clearBatch();
			data.addBatch(q);
			q = data.getUpdatePrices(idproveedor, pol, tc, idcomprobante,
					iduser);
			System.out.println(q);
			data.addBatch(q);
			q = "alter table v_ma_articulos enable trigger all ";
			System.out.println(q);
			data.addBatch(q);
			q += "update b_ta_cpte set x_ultimo_nro = x_ultimo_nro + 1 where codigo like '"
					+ tc + "'";
			System.out.println(q);
			data.addBatch(q);
			error = data.executeBatch();
			if (!error) {
				data.commitTransaction();
			} else {
				data.rollbackTransaction();
			}
		}

		return !error;
	}

	public Object[][] read_tabulated_file(String path) {
		Object[][] results = null;
		int chrx = 9;// este nueve es un caracter de tabulacion
		String record = null;
		List<List<String>> dynamic = new ArrayList<List<String>>();
		if (path.compareTo("") != 0) {
			File file = new File(path);
			int recCount = 0;

			try {

				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				record = new String();
				int cols = 0;
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				record = new String();

				List<String> line = new ArrayList<String>();
				while ((record = br.readLine()) != null) {
					// cada record es una linea
					this.current = recCount;
					String auxs = "";
					int col = 0;

					// recorro la linea para encontrar las tabulaciones
					System.err.println(record);
					for (int i = 0; i < record.length(); i++) {
						if (record.charAt(i) == chrx) {
							try {
								line.add(auxs);

							} catch (Exception e) {
								if (debug) {
									System.out.println(e.getMessage());
								}
							}
							auxs = "";
							col++;
						} else {
							auxs = auxs + record.charAt(i);
						}
					}
					try {
						line.add(auxs);
					} catch (Exception e) {
						if (debug) {
							System.out.println(e.getMessage());
						}
					}

					if (debug) {
						System.out.println(record);
					}

					dynamic.add(line);
					line = new ArrayList<String>();
					recCount++;

				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.lenght = dynamic.size();
			System.out.println("========>>>TABULATED FILE<<<========");
			results = new Object[dynamic.size()][dynamic.get(0).size()];
			for (int i = 0; i < dynamic.size(); i++) {
				for (int j = 0; j < dynamic.get(0).size(); j++) {
					results[i][j] ="";
					try {
						results[i][j] = dynamic.get(i).get(j);
						System.out.print(results[i][j]+"	");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//results[i][j] = "";
				}
				System.out.println();
			}
			System.out.println("END========>>>TABULATED FILE<<<========END");
		} else {
			this.resumen.add("Debe seleccionar un archivo");
		}
		return results;
	}

	public void addSource(Element e) {
		boolean found = false;
		int i = 0;
		String source = e.getAtributo("source").getValor();
		System.out.println("Agregando " + source);
		while (i < sources.size() & !found) {
			Element x = sources.get(i);
			String xsource = x.getAtributo("source").getValor();
			found = source.compareTo(xsource) == 0;
			if (!found) {
				i++;
			}

		}
		if (!found) {
			this.sources.add(e);
			System.out.println("Agregando source:"
					+ e.getAtributo("source").getValor() + " "
					+ e.getAtributo("directory").getValor());
		} else {
			System.out.println("Quitar source:"
					+ this.sources.get(i).getAtributo("source").getValor()
					+ " "
					+ this.sources.get(i).getAtributo("directory").getValor());
			this.sources.remove(i);
			System.out.println("Utilizar source:"
					+ e.getAtributo("source").getValor() + " "
					+ e.getAtributo("directory").getValor());
			this.sources.add(e);
		}
	}

	/*
	 * public void download(){ Element root=xml.getRoot();
	 * 
	 * int i=0; while (i<root.getElements().size()){ Element
	 * e=root.getElements().get(i); if (e.getId().compareTo("version")==0){
	 * boolean ok=download_version(e); if (ok){ this.actualiza(); }else{
	 * error("Error En la Descarga de la actualizacion. Intentelo nuevamente");
	 * } } i++; }
	 * 
	 * }
	 */

	private Properties getProperties() {
		Properties props = new Properties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.starttls.enable", "true");
		props.setProperty("mail.smtp.port", "587");
		props.setProperty("mail.smtp.user", emailFrom);
		props.setProperty("mail.smtp.auth", "true");
		return props;
	}

	public List<String> getInstrucciones() {
		List<String> instrucciones = new ArrayList<String>();
		instrucciones
				.add("===================IMPORTANTE ACTUALIZACION===============");
		instrucciones
				.add("SI UN ARTICULO NO SE ENCUENTRA ACTUALIZADO VERIFIQUE LOS ");
		instrucciones.add("ALIAS DEL MISMO EN EL MAESTRO DE ARTICULOS.  ");
		instrucciones
				.add("DESDE EL MAESTRO SE PUEDE CORREGIR Y DEJAR LISTO PARA LA PROXIMA!!");
		instrucciones
				.add("==========================================================");
		return instrucciones;
	}

	public Message getMessage(Session session, String idcomprobante) {

		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(emailFrom));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					emailTo));
			String subject = "Actualizacion Automatica";
			
			message.setSubject(subject);
			String newline = System.getProperty("line.separator");
			String _text = "";
			resumen.add("");
			resumen.add("IDUSER:" + this.getConstructor().getIduser()
					+ " HOST:" + data.getHost() + " IP:" + data.getIp());
			resumen.add("");

			for (int i = 0; i < resumen.size(); i++) {
				_text += resumen.get(i);
				_text += newline;
			}
			for (int i = 0; i < this.getInstrucciones().size(); i++) {
				_text += getInstrucciones().get(i);
				_text += newline;
			}
			MimeBodyPart mbp1 = new MimeBodyPart();
			mbp1.setText(_text);
			
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(mbp1);
			   
			MimeBodyPart mbp2 = new MimeBodyPart();

			String destination=this.getDestination();
			// attach the file to the message
			File f = null;
			if (this.isLinux()){
				f=new File(destination + "\\" + idcomprobante + ".zip");
			}else{
				f=new File(destination + "/" + idcomprobante + ".zip");
			}
			if (f.exists()){
				FileDataSource fds = new FileDataSource(f.getAbsoluteFile());
				mbp2.setDataHandler(new DataHandler(fds));
				mbp2.setFileName(fds.getName());
				mp.addBodyPart(mbp2);
					
			}else{
				System.out.println("No existe "+f.getAbsolutePath()+" no se agrega al informe de email!!");
			}
			message.setContent(mp);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}

	public boolean compressReport(String idcomprobante) {
		boolean ok = false;
		String _destination=this.getDestination();
		
		String folder = "";
		if (this.isLinux()){
			folder=_destination + "/" + idcomprobante;
		}else{
			folder=_destination + "\\" + idcomprobante;
		}
		ok=this.compressFolder(folder, folder+ ".zip");
		return ok;
	}

	public void _taskworkEnviar(String idcomprobante) {
		estado = "Enviando EMail";
		boolean error = false;
		if (frame != null) {
			frame.getJProgressBar().setIndeterminate(true);
		}

		Properties props = this.getProperties();
		Session session = Session.getDefaultInstance(props);
		Message message = null;
		try {
			message = this.getMessage(session,idcomprobante);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

		}
		try {
			if (message != null) {
				Transport t = session.getTransport("smtp");
				t.connect(emailFrom, emailpassword);
				t.sendMessage(message, message.getAllRecipients());
				t.close();
			}
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error = true;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error = true;
		}

	}

	private String rmPath(String fName) {
		int pos = fName.lastIndexOf(File.separatorChar);
		if (pos > -1)
			fName = fName.substring(pos + 1);
		return fName;
	}
	
	public boolean compressFolder(String source,String target){
		boolean ok=true;
		 try
	     {
	     File inFolder=new File(source);
	     File outFolder=new File(target);
	     System.out.println("comprimiendo: "+source+" -> "+target);
	     ZipOutputStream out = new ZipOutputStream(new 
	BufferedOutputStream(new FileOutputStream(outFolder)));
	     BufferedInputStream in = null;
	     byte[] data    = new byte[1000];
	     String files[] = inFolder.list();
	     for (int i=0; i<files.length; i++)
	      {
	      in = new BufferedInputStream(new FileInputStream(inFolder.getPath() + "/" + files[i]), 1000);                  
	out.putNextEntry(new ZipEntry(files[i])); 
	      int count;
	      while((count = in.read(data,0,1000)) != -1)
	      {
	           out.write(data, 0, count);
	          }
	      out.closeEntry();
	      }
	      out.flush();
	      out.close();
	      }    catch(Exception e)       {
	    	  ok=false;
	           e.printStackTrace();
	          } 
	      return ok;
	}
	
	public boolean ccompressFile(String source, String target) {
		boolean ok = true;
		try {
			File _target = new File(target);
			ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(
					target));
			FileInputStream fis = new FileInputStream(source);
			// put a new ZipEntry in the ZipOutputStream
			zos.putNextEntry(new ZipEntry(rmPath(source)));
			int size = 0;
			byte[] buffer = new byte[1024];

			// read data to the end of the source file and write it to the zip
			// output stream.
			while ((size = fis.read(buffer, 0, buffer.length)) > 0) {
				zos.write(buffer, 0, size);
			}

			zos.closeEntry();
			fis.close();

			// Finish zip process
			zos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ok = false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ok = false;
		}
		return ok;
	}
}
