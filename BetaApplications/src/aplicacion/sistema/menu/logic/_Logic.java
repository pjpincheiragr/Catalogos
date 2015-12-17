package aplicacion.sistema.menu.logic;

import java.awt.AlphaComposite;
import java.awt.image.*;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.MenuItem;

import java.awt.PopupMenu;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

import javax.swing.table.*;
import javax.swing.text.BadLocationException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.Random;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import aplicacion.sistema.menu.gui._Frame;
import aplicacion.sistema.menu.logic._Data;
import aplicacion.sistema.menu.interfaces._Interface;


import aplicacion.compras.carga.items.logic.StrEtiqueta;
import aplicacion.compras.carga.items.logic.printing;
import aplicacion.herramientas.java.launcher.logic.SwingWorker;
import aplicacion.herramientas.java.table.Column;
import aplicacion.herramientas.java.table.CustomTable;
import aplicacion.herramientas.java.xml.Element;
import aplicacion.herramientas.java.xml.XML;
import aplicacion.herramientas.java.Convertidor;

import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Data;
import aplicacion.modelo.logic.Logic;
import aplicacion.modelo.constructor.*;

import aplicacion.herramientas.conexion.ConnectionHandler;
public class _Logic extends Logic{
	private _Frame frame=null;
	private _Data data=null;
	private String iduser="";
	private TrayIcon trayIcon;
	private  PopupMenu popup;
	private boolean tunel=false;
	private List<BufferedImage> images = null;
	private boolean monitor=false;
	private boolean server=false;
	private boolean aleatorio=true;
	private int timeScreen=0;
	private String last_file="";
	private boolean local=false;
	private Timer Timer;  //  @jve:decl-index=0:
	private int image=-1;
	private Timer MultiTimer;
	private Crono crono;
	private String estado="";
	private double memory_record=0;
	private int current;
	
	private boolean printer=false;
	private boolean pause=true;
	
	private int lenght;
	private boolean done=false,canceled;
	private int errors=0;
	private int max_lines=20;
	private int current_line=0;
	private boolean console=false;
	private String temporal_directory="C:\\Windows\\Temp\\screenshots\\";
	
	private String destination="C:\\windows\\temp\\_beta";
	
	private int _clock_multimedia=280;
	private int _clock_multimedia_reset=300;
	private int _clock_console=2;
	private int _clock_console_reset=6;
	private int _clock_monitor=-20;
	private int _clock_monitor_reset=40;
	private int _clock_printer=0;
	private int _clock_printer_reset=10;
	private int _clock_printer_error=0;
	private int _clock_printer_error_reset=60*5*3; //c/3 minutos muestra error de impresion
	private int _printer_error=0;
	private int _clock_memory=1;
	private int _clock_memory_reset=4;
	private int _clock_garbage=30;
	private int _clock_garbage_reset=100;
	private int _clock_update=60*60*5;
	private int _clock_update_reset=60*60*5; //4 horas para checkear si hay versiones nuevas
	private int _clock_update_price=60*29;
	private int _clock_update_price_reset=60*30; //4 horas para checkear si hay versiones nuevas
	
	class TakeScreenShotTask {
		public TakeScreenShotTask(){
			screenshot();
		}
	}
	class _taskCargar {
		_taskCargar(String lanzador,String menu) {
			_taskworkCargar(lanzador,menu);
		}
	}
	
	class _taskCargarLogs {
		_taskCargarLogs() {
			_taskworkCargarLogs();
		}
	}
	
	class _taskWork {
		_taskWork() {
			work();
		}
	}
	
	class _taskCheckVersion {
		_taskCheckVersion() {
			check_version();
		}
	}
	public void _taskworkCargarLogs(){
		
		if (console){
			String filename="C:/Archivos de programa/Beta Systems/Beta/output.log";
			File file=new File(filename);
			if (file.exists()){
				this.setContents(file);
			}else{
				String text="";
				text+="ERROR> no log file";
				text+=System.getProperty("line.separator");
				frame.getJTextArea().setText(text);
			}
		}
		
		frame.getJTextArea().setCaretPosition(frame.getJTextArea().getDocument().getLength());
	}
	
	public void _taskworkCargar(String lanzador,String menu){
		
		boolean ok=this.data.getConnectionHandler().getDefaultConnector().testConection();
		if (ok){
			current=1;
			estado="Cargando Aplicacion";
			this.launchApplicacion(lanzador, menu);
		}else{
			this.current=6;
			this.aviso("Despues de que logre reconectarse intente cargar esta aplicacion nuevamente");
			done=true;
			
			this.getConstructor().reconnect();
		}
	}
	
	public void setPause(boolean pause){
		this.pause=pause;
	}
	public void loadVersion(){
		String version=data.getVersion();
		frame._version.setText(version);
	}
	
	public boolean Similar(String newfile){
		boolean match=false;
		File f1=new File(newfile);
		File f2=new File(this.last_file);
		if (f1.exists() & f2.exists()){
			ImageCompare ic = new ImageCompare(this.last_file, newfile);
			// Set the comparison parameters. 
			//   (num vertical regions, num horizontal regions, sensitivity, stabilizer)
			ic.setParameters(4, 4, 4, 4);
			// Display some indication of the differences in the image.
			ic.setDebugMode(-2);
			// Compare.
			ic.compare();
			match=ic.match();	
			ic=null;
			this.freeMemory();
		}
		
		return match;
	}
	
	public void siguiente(){
		int images=data.getImages(iduser);
		if (images>0){
			if (image<=0){
				image=1;
			}else{
				if (image<images){
					image++;
					}else{
					image=1;
				}	
			}
		}else{
			image=-1;
		}
		this.mostrar_foto();
	}
	
	public boolean guardar_imagenes() {
		boolean ok = true;
		
		if (images.size() > 0) {
			if (_debug>0) System.out.println("imagenes?");
			int i = 0;
			data.eliminar_fotos(iduser);
			while (i < images.size() & ok) {
				ok = this.write(images.get(i), i + 1);
				i++;
			}
		}
		return ok;
	}

	public boolean storeImage(int secuencia, BufferedImage img) {
		FileInputStream fis = null;
		PreparedStatement ps = null;
		
		boolean ok = true;
		
			String INSERT_PICTURE = "insert into wallpapers(iduser,secuencia,imagen) values (?, ?, ?)";
			if (_debug>0) System.out.println("Write>"+INSERT_PICTURE+" secuencia?"+secuencia);
			ByteArrayOutputStream buffer_img = new ByteArrayOutputStream();

			try {
				ImageIO.write(img, "jpg", buffer_img);
				InputStream in = new ByteArrayInputStream(buffer_img
						.toByteArray());

				
				ps = data.getConnector("MySQL")
						.prepareStatement(INSERT_PICTURE);
				ps.setString(1, iduser);
				ps.setInt(2, secuencia);
				ps.setBinaryStream(3, in, (int) buffer_img.size());
				
				int n = ps.executeUpdate();
				ok = n > 0;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		

		return ok;
	}

	public boolean deleteImage() {
		
		
		boolean ok = true;
		if (image>0){
			String q = "delete from wallpapers where iduser like '"+iduser+"'  and secuencia = '"+(image-1)+"'";
			data.getConnector("MySQL").clearBatch();
			data.getConnector("MySQL").addBatch(q);
			q = " update wallpapers set secuencia=secuencia-1 where iduser like '"+iduser+"' and secuencia > "+(image-1);
			data.getConnector("MySQL").addBatch(q);
			ok = !data.getConnector("MySQL").executeBatch();
				

		}else{
			ok=false;
		}
		return ok;
	}
	
	private boolean write(BufferedImage img, int sec) {


		boolean ok = this.storeImage((sec - 1),
				img);

		return ok;
	}

	public void goCargarLogs() {
		
		SwingWorker worker = null;
		if (worker == null) {
			worker = new SwingWorker() {
				public Object construct() {
					return new _taskCargarLogs();
				}
			};
		}
		
		worker.start();
	}
	
	public void goCargar(String lanzador,String menu) {
		System.out.println("CARGAR TRABAJO");
		frame.getJProgressBar().setString("Cargando Aplicacion");
		frame.getJProgressBar().setIndeterminate(true);
		frame.getJProgressBar().setVisible(true);
		crono=new Crono();
		crono.start();
		current=0;
		errors=0;
		done = false;
		canceled=false;
		this.createTimer();
		
		SwingWorker worker = null;
		if (worker == null) {
			final String _lanzador=lanzador;
			final String _menu=menu;
			worker = new SwingWorker() {
				public Object construct() {
					return new _taskCargar(_lanzador,_menu);
				}
			};
		}
		if (Timer!=null) {
			Timer.start();
		}
		worker.start();
		
	}	
	public _Logic(){
		super();
		images=new ArrayList<BufferedImage>();
	}
	
	public void setUser(String iduser){
		this.iduser=iduser;
	}
	
	public boolean storeImage(String host,String ip,String user,BufferedImage img){
    	
        PreparedStatement ps = null;
        boolean ok=true;
        
        	String INSERT_PICTURE = "insert into monitor(host,ip,user,image) values (?, ?, ?, ?)";
        	ByteArrayOutputStream buffer_img = new ByteArrayOutputStream();

        	try {
				ImageIO.write(img, "jpg", buffer_img);
				InputStream in=new ByteArrayInputStream(buffer_img.toByteArray());
				        	  
							ps = data.getConnector("MySQL").prepareStatement(INSERT_PICTURE);
							ps.setString(1, host);
							ps.setString(2, ip);
							ps.setString(3, user);
							ps.setBinaryStream(4, in, (int) buffer_img.size());
							  
							  int n=ps.executeUpdate();
							  ok=n>0;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	 	
        
        
         return ok; 
    }
	public void addFoto(BufferedImage bi) {
		images.add(bi);
	}
	
	
	
	public void _anterior() {
		int images=data.getImages(iduser);
		if (images>0){
			if (image>1){
				image--;
			}else{
				image=images;
				
			}
			
		}else{
			image=-1;
		}
		
		
		
		this.mostrar_foto();
		
	}

	

	
	public void mostrar_foto() {
			this.clean_images();
			if (image<=0){
				ImageIcon img=new ImageIcon(getClass().getResource("/icons/lambo.jpg"));
				BufferedImage bi=this.toBufferedImage(img.getImage());
				this.addFoto(bi);
			}else{
				BufferedImage bi=this.getImages(iduser, image-1);
				this.addFoto(bi);
			}
			if (_debug>0) System.out.println("MOSTRANDO FOTO >"+image);
			
			
				final BufferedImage bi = images.get(0);
				if (bi != null) {
				Runnable _execute=new Runnable(){
					public void run(){
						frame.getCanvas().setImage(bi);
						frame.getCanvas().setProportional(false);
						frame.getCanvas().zoom(0);
						frame.getCanvas().setVisible(true);
						frame.getCanvas().maximizar();
						frame.repaint();		
					}
				};
				this.invokeLater(_execute);
				}

		

	}

	public void eliminar_foto() {
		if (image > 0) {
			this.eliminar_foto(image);
		}else{
			if (_debug>0) System.out.println("no se puede eliminar hoja cero" +image);
		}

	}
	
	public void eliminar_foto(int i) {
		int images=data.getImages(iduser);
		if (i > 0 & i <= images) {
			if (confirmar("Confirme la eliminacion de la imagen :", 2)) {
				
				
				boolean deleted=this.deleteImage();
				if (deleted){
					images=data.getImages(iduser);
					if (images> 0) {
						image=1;
					} else{
						image=-1;
					}
					
					
				}
				this.mostrar_foto();	
	
				}
				
			}
			
	}

	public void _siguiente() {
		
		int images=data.getImages(iduser);
		if (images>0){
			if (image<=0){
				image=1;
			}else{
				if (image < images) {
					image++;
				}else{
					image=1;
				}		
			}
			
		}else{
			image=-1;
		}
		
		
		
		this.mostrar_foto();
		
		
		
	}

	private void clean_images(){
		if (images==null){
			images=new ArrayList<BufferedImage>();
		}else{
			for (int i=0;i<images.size();i++){
				images.remove(i);	
			}
		}
	}
	
	public BufferedImage getImages(String iduser,int secuencia) {
		BufferedImage _image = null;
		boolean ok = false;
		try {
			Statement stmt = data.getConnector("MySQL").createStatement();
			String q = "SELECT imagen FROM wallpapers  where iduser like '"
					+ iduser + "' and secuencia like '"+secuencia+"'";
			if (_debug>0) System.out.println(q);
			ResultSet resultSet = stmt.executeQuery(q);

			if (resultSet.next()) {
				ok = true;
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
	
	public void cargar_fotos() {
		
		int images=data.getImages(iduser);
		if (images>0){
			images=1;
		}else{
			images=-1;
		}
		
		
	}
	
	public void load_initial_image(int width,int height){
		
		image=-1;
		
		frame.getCanvas().setBounds(new Rectangle(0, 0, width+2, height));
		//frame.getCanvas().setTools(false);
		//frame.getCanvas().setImage(bi);
		frame.getJContentPane().add(frame.getCanvas(), null);
		frame.getCanvas().setMove(false);
		
		this.mostrar_foto();
		
	}
	
	
	public void nuevo_desde_archivo(){
		
	}
	public void setFrame(JFrame _frame){
		this.frame=(_Frame)_frame;
		super.setFrame(_frame);
	}
	
	public void setData(Data _data){
		this.data=(_Data)_data;
		super.setData(_data);
	}
	
	public void createTrayIcon(){
		this.setTrayIcon(iduser);
	}
	
	public void cargar_perfil(){
		
		boolean su=data.getIsSuperUser(iduser);
		frame.get_btn_eliminar().setVisible(su);
		frame.get_btn_agregar().setVisible(su);
	}
	
	public void create_interface(){
		  popup = new PopupMenu();
		
		  MenuItem menuItem;
		  menuItem = new MenuItem("Salir");
		  menuItem.setActionCommand(_Interface._btn_salir);
		  menuItem.addActionListener(this.getConstructor().getActionListener());
		  menuItem.setFont(new Font("Dialog", Font.PLAIN, 10));
		  popup.add(menuItem);
	}
	public void show(){
		
		_frame.setVisible(true);
		//_frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		_frame.requestFocus();
		_frame.toFront();
		
	}
	
	public void createTimer(){
		Timer=new Timer(500, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				System.out.println("TIMER ");
				if (done){
					endbar();
					if (Timer!=null){
						Timer.stop();
						
					}
					System.out.println("TIMER STOPPED. RUNNINNG="+Timer.isRunning());
					}
			}
		}); 
		
		
	}
	
	
	
	

	public void refresh(){
		frame.repaint();
	}
	
	public void work(){
		
		if (this._clock_multimedia>this._clock_multimedia_reset){
			this._clock_multimedia=0;
			multimedia();
		}
		if (this._clock_monitor>this._clock_monitor_reset){
			this._clock_monitor=0;
			monitor();
		}
		if (this._clock_printer>this._clock_printer_reset){
			
			this._clock_printer=0;
			printer();
		}
		if (this._clock_console>this._clock_console_reset){
			this._clock_console=0;
			this.goCargarLogs();
		}
		if (this._clock_garbage>this._clock_garbage_reset){
			this._clock_garbage=0;
			garbage();
		}
		if (this._clock_memory>this._clock_memory_reset){
			this._clock_memory=0;
			memory();
		}
		if (this._clock_update>this._clock_update_reset){
			this._clock_update=0;
			update();
		}
		if (this._clock_printer_error>this._clock_printer_error_reset){
			printer_error();
			this._clock_printer_error=0;
		}
		if (this._clock_update_price>this._clock_update_price_reset){
			price();
			this._clock_update_price=0;
		}
		this._clock_multimedia++;
		this._clock_monitor++;
		this._clock_printer++;
		this._clock_garbage++;
		this._clock_memory++;
		this._clock_update++;
		this._clock_console++;
		this._clock_printer_error++;
		this._clock_update_price++;
		check_process();
		refresh();
	}
	public void check_process(){
		if (_vplogic!=null){
			String estado=_vplogic.getEstado();
			//System.out.println(estado);
			
		}
	}
	public void printer_error(){
		if (printer){
		if (this._printer_error>0){
		this.trayIcon.displayMessage("Beta Servidor de Impresion",
				"Error de Impresion de Etiquetas." +
				" Revise Conexion y Estado de la Impresora." +
				" Las Etiquetas seran impresas automaticamente. ", TrayIcon.MessageType.ERROR);
		}else{
				
		}
		}
	}
	
	public void memory(){
		if (_debug>0) System.out.println("WORK:MEMORY");
		getMemoryState();
	}
	public void update(){
		if (_debug>0) System.out.println("WORK:UPDATE");
		goCheckVersion();
	}
	public void printer(){
		if (printer){
			
			//if (_debug>0)
				System.out.println("WORK:PRINTER");
			imprimir_etiquetas();	
		}
		
	}
	public void garbage(){
		if (_debug>0) System.out.println("WORK:GARBAGE");
		freeMemory();	
	}
	public void multimedia(){
		if (_debug>1) System.out.println("WORK:MULTIMEDIA");
		
		
		
		if (!pause){
			this.siguiente();
		}
		
		
	}
	public void monitor(){
		if (monitor){
			if (_debug>1) System.out.println("WORK:MONITOR");
			evaluate_activity();	
		}
			
	}
	
public void createMultiTimer(){
		
		MultiTimer=new Timer(200, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				work();
			}
		}); 
		MultiTimer.start();
	}
	

	
	
	
	
	
	
	private aplicacion.sistema.version.logic._Data _vdata= null;
	private aplicacion.sistema.version.logic._Logic _vlogic=null;
	public void check_version(){
		
		if (_vdata!=null){
			_vdata=new aplicacion.sistema.version.logic._Data();
			_vdata.setSql(this.getConstructor().getConnectionHandler());	
		}
		if (_vlogic==null){
			_vlogic=new aplicacion.sistema.version.logic._Logic();
			_vlogic.setData(_vdata);
			_vlogic.prepareDestination();
			_vlogic.load_variables();	
		}
		//this.trayIcon.displayMessage("Beta Actualizacion ", "Buscando Actualizacion", TrayIcon.MessageType.INFO);
		boolean ok=_vlogic.downloadFile("/beta", "version.xml", destination,false);
		String[] tmp=null;
		if (ok){
			_vlogic.init_xml();
			tmp=_vlogic.get_process_changes(false);
			if (tmp!=null){
				if (_debug>0) System.out.println("Version Disponible!>"+tmp[0]+" "+tmp[1]);
				this.trayIcon.displayMessage("Beta Actualizacion ", "Esta Disponible la revision "+tmp[1], TrayIcon.MessageType.INFO);
			}
			
		}else{
			if (_debug>0) System.out.println("No se puedo descargar ");
		}
		
	}
	
	public boolean deleteDirectory(File path) {
	    if( path.exists() ) {
	      File[] files = path.listFiles();
	      for(int i=0; i<files.length; i++) {
	         if(files[i].isDirectory()) {
	           deleteDirectory(files[i]);
	         }
	         else {
	           files[i].delete();
	         }
	      }
	    }
	    return( path.delete() );
	  }
	
public void prepareDestination(){
		
		File file=new File(destination);
		if (file.exists()){
			deleteDirectory(file);
		}
		boolean ok=file.mkdir();
		if (_debug>0) System.out.println("directory creation > "+destination+">?"+ok);	
	}

	
	public int getMiliDiference(String s1){
		DateFormat formatter;
	    Locale locale = Locale.getDefault();
	    Date date1=null;
	    
	    Calendar C=Calendar.getInstance();
		Calendar C2=Calendar.getInstance();
		try {
			formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", locale);
			date1 = (Date)formatter.parse(s1);
			if (_debug>2) System.out.println("Transformado fecha: "+s1+" "+date1);
			formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", locale);
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Date now=new Date();
    	C.setTime(date1);
    	C2.setTime(now);
    	long dif=Math.abs(C.getTimeInMillis()-C2.getTimeInMillis());
    	return new Double(dif).intValue(); 
	}
		
	
	public boolean getUserMonitor(){
		boolean monitor=false;
		
		
		Object[][] results=data.getConnectionHandler().getDefaultConnector().getResults("select monitor from b_users where iduser like '"+iduser+"'");
		if (results!=null){
			if (results.length>0){
				String value=(String) results[0][0];
				monitor=value.compareTo("1")==0;
			}
		}
		
		return monitor;
	}
	
	public boolean getUserPrinter(){
		boolean printer=false;
		
		
		Object[][] results=data.getConnectionHandler().getDefaultConnector().getResults("select printer from b_users where iduser like '"+iduser+"'");
		if (results!=null){
			if (results.length>0){
				String value=(String) results[0][0];
				printer=value.compareTo("1")==0;
			}
		}
		printer=true;
		return printer;
	}
	
	public boolean getUserServer(){
		boolean printer=false;
		
		
		Object[][] results=data.getConnectionHandler().getDefaultConnector().getResults("select servidor from b_users where iduser like '"+iduser+"'");
		if (results!=null){
			if (results.length>0){
				String value=(String) results[0][0];
				printer=value.compareTo("1")==0;
			}
		}
		
		return printer;
	}
	
	public int getTimeFromLastActivity(){
		int time=0;
		Object[][] results=data.getParametroSqlite("user_activity");
		if (results!=null){
			if (results.length>0){
				String _fecha=(String) results[0][1];
				int milis=this.getMiliDiference(_fecha);
				if (_debug>0) System.out.println("last activity?"+_fecha+" milis? "+milis+" sec:"+(milis/1000));
				time=milis/1000;
				
			}
		}
		return time;
	}
	
	
	 public void goTakeScreenShot() {
	    	
	    	SwingWorker worker=null;
	    		worker = new SwingWorker() {
	    			@Override
	    			public Object construct() {
	    				
	    				return new TakeScreenShotTask();
	    			}
	    		};
	    		worker.start();
	    	}
	 
	 public void goCheckVersion() {
	    	
	    	SwingWorker worker=null;
	    		worker = new SwingWorker() {
	    			@Override
	    			public Object construct() {
	    				
	    				return new _taskCheckVersion();
	    			}
	    		};
	    		worker.start();
	    	}
	 
	public void evaluate_activity(){
		
		if (monitor){
			if (_debug>0) System.out.println("Usuario Con Monitor");
			if (this.getTimeFromLastActivity()>60){
				if (_debug>0) System.out.println("Sistema sin actividad aparentemente");
				if (this.timeScreen>2){
					if (this.getFreeMemory()>this.memory_warning){
						this.goTakeScreenShot();	
					}
					monitor=this.getUserMonitor();
					timeScreen=0;
				}else{
					timeScreen++;
				}
					
			}else{
				if (_debug>0) System.out.println("Sistema activo. No se hace monitoreo");
			}	
			if (this.getTimeFromLastActivity()>100){
				/*
				frame.setVisible(true);
				frame.requestFocusInWindow();
				frame.toFront();
				*/
			}
		}else{
			if (this.timeScreen>120){
				monitor=this.getUserMonitor();
				timeScreen=0;
			}else{
				timeScreen++;
			}
		}
		
		
	}
	public void checkDir(){
		File dir=new File(this.temporal_directory);
		if (!dir.exists()){
			dir.mkdir();
		}
	}
	
	public void screenshot(){
		try {
			this.checkDir();
			BufferedImage screenImg;  //  @jve:decl-index=0:
 Rectangle screenRect;
 Robot robot=null;
			try {
				robot = new Robot();
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
 
 screenRect=new Rectangle(0,0,screenSize.width,screenSize.height);
 if (robot!=null){
			screenImg=robot.createScreenCapture(screenRect);
			
			  InetAddress Ip =null;
		      try {
				Ip=InetAddress.getLocalHost();
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			this.storeImage( Ip.getHostName(),Ip.getHostAddress(),iduser, screenImg);
						
 }
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	 
	}
	public void freeMemory(){
		try {
			this.getConstructor().freeResources();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getMemoryState(){
		Double total=this.getTotalMemory();
		Double free=this.getFreeMemory();
		int value=free.intValue();
		int valuetotal=total.intValue();
		if (free<20){
			//this.trayIcon.displayMessage("Beta ", "Nivel de Memoria Bajo "+free+"%. Cierre Las Aplicaciones Que no utilice", TrayIcon.MessageType.WARNING);
		}
		frame.get_bar_memory().setValue(value);
		frame.get_bar_memory().setString("Memoria Disponible "+new Convertidor().getMoney(free,2)+"% de "+valuetotal+" MB ");
		frame.get_bar_memory().setStringPainted(true);
	}
	
	public void updateBar(){
		System.out.println("update BAR");
		Runnable _execute=new Runnable(){
			public void run(){
				frame.getJProgressBar().setMaximum(lenght);
				frame.getJProgressBar().setValue(current);
				frame.getJProgressBar().setString(estado+" "+current+"/"+lenght+" "+crono.elapsed());
				frame.getJProgressBar().setStringPainted(true);
				frame.getJProgressBar().setVisible(true);		
			}
		};
		this.invokeLater(_execute);
		
	}
	
	public void endbar(){
		System.out.println("END BAR");
		estado="";
		if (crono!=null){
			crono.pause();
			crono=null;	
		}
			
		
		
		frame.getJProgressBar().setString("");
		frame.getJProgressBar().setIndeterminate(false);
		frame.getJProgressBar().setValue(0);
		frame.getJProgressBar().setVisible(false);
		
		
	}
	
	public void modify(){
		Runnable _execute=new Runnable(){
			public void run(){
				_modify();
			}
		};
		this.invokeLater(_execute);
	}
	
	public void minimizar_tray(){
		frame.setExtendedState(JFrame. ICONIFIED);
	}
	
	public void addcarpeta(){
		final JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY );
		int returnVal = fc.showOpenDialog(frame);
		String directory="";
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			directory=fc.getSelectedFile().getAbsolutePath().toString();
		}

    	File dir = new File(directory);
    	if (dir.exists()){
    		if (this.confirmar("Confirme Para Incorparar las imagenes:", 3)){
    			List<String> files=new ArrayList<String>();
            	String[] children = dir.list();
            	 if (children == null) { 
            	// Either dir does not exist or is not a directory 
            	} else { 
            	for (int i=0; i<children.length; i++) { 
            	// Get filename of file or directory 
            	String filename = children[i];
            	files.add(filename);
            	if (_debug>0) System.out.println(filename);
            	//this.proccess();
            	this.addFotoToDatabase(directory+"/"+filename);
            	} 
            	}	
    		}
    			
    	}
    		
	}
	
	public void _modify(){
		Dimension d=this._frame.getSize();
		Dimension s=Toolkit.getDefaultToolkit().getScreenSize();
		int width=new Double(s.getWidth()).intValue();
		int height=new Double(s.getHeight()).intValue();
		load_initial_image(width,height);
		String iduser=this.getConstructor().getIduser();
		if (iduser.compareTo("agustinw")!=0){
			frame.get_btn_kill().setVisible(false);
		}
		int bar_width=new Double(width*0.7).intValue();
		int bar_width_half=new Double(width*0.3*0.5).intValue();
		//frame.setPreferredSize(new Dimension(width+10, height));
		frame.setMinimumSize(new Dimension(width+10, height-30));
		frame.getJProgressBar().setLocation(bar_width_half, height-110);
		frame.getJProgressBar().setSize(bar_width, 16);
		
		frame.get_bar_memory().setLocation(bar_width_half, height-92);
		frame.get_bar_memory().setSize(bar_width, 16);
		
		frame.getJToolBar().setLocation(width-frame.getJToolBar().getWidth()-20, frame.getJToolBar().getLocation().y);
		frame._version.setLocation(width-110, height-98);
		frame.getJProgressBar().setVisible(false);
		
		String id="";
		if (this.getConstructor().getConnectionHandler().getDefaultConnector()!=null){
			id=this.getConstructor().getConnectionHandler().getDefaultConnector().getId();
		}
		frame.setTitle(_frame.getTitle()+" ("+id+")");
		
		frame.setResizable(false);
		
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.requestFocus();
		frame.toFront();
	}
	
	public boolean addFotoToDatabase(String filex) {
		File fx = new File(filex);
		boolean ok=false;
		if (fx.exists()) {
			BufferedImage bi = this.loadImage(fx.getAbsolutePath());
			if (bi != null) {
				int i=data.getImages(iduser);
				i++;
				ok=this.write(bi, i);
			}
		}
		return ok;
	}
	public void addFoto(String filex) {
		File fx = new File(filex);
		
		if (fx.exists()) {
			BufferedImage bi = this.loadImage(fx.getAbsolutePath());
			if (bi != null) {
				int i=data.getImages(iduser);
				i++;
				this.write(bi, i);
				images.add(bi);
				image=i;
				this.mostrar_foto();
				
			}

		}

	}
	public void addFoto() {
		int selection=this.preguntar("Seleccione", "Como Incorporar las imagenes?", new String[]{"Carpeta Completa","Solo una imagen"}, "Solo una imagen");
		if (selection>=0){
			if (selection==0){
				this.addcarpeta();
			}else{
				this.addFoto();
			}
		}
	}
	public void addOneFoto() {
		
		if (_debug>0) System.out.println("addfoto");
		final JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(frame);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			this.addFoto(fc.getSelectedFile().getAbsolutePath().toString());

		}
	}
	
	public BufferedImage loadImage(String filename) {
		BufferedImage img = null;

		try {
			img = ImageIO.read(new File(filename));
		} catch (IOException e) {
		}
		return img;
	}

	public void clean(){
	frame.get_txt_codigo().setText("");
	}
	
	public void init(){
		
		
		tunel=this.getData().getConnectionHandler().getDefaultConnector().hasTunnel();
		
		Runnable showModalDialog = new Runnable() {
		    public void run() {
		    	init_scan();
		    	loadVersion();
		    	monitor=getUserMonitor();
		    	printer=getUserPrinter();
		    	printer=true;
		    	server=getUserServer();
		    	
		    	modify();
		    	freeMemory();
				createMultiTimer();
				loadUserAreas();
				createTrayIcon();
				createInitialTable();
				focus();
				}
    		};
    		
    	javax.swing.SwingUtilities.invokeLater(showModalDialog);
    	this.goCheckVersion();
    	
	}


	
	public void minimizar(){
		aviso("Beta segurira funcionando. Utilice el Icono en la Bara de tareas para ver el menu");
		frame.setVisible(false);
	}
	
	public void envioASistemas(){
		this.goCargar("aplicacion.sistema.error.launcher._Launcher","Envio de Error/Bug/Requerimiento a Sistemas");
	}
	
	public void createInitialTable(){
		Object[][] results=new Object[][]{
				{"Beta","",data.getSystemDate()}
		};
		//this.create_table(results);
	}
	public void create_table(Object[][] results) {
		CustomTable table = new CustomTable();
		Column col = null;
		
		col = new Column();
		col.setName("Aplicacion");
		col.setWidth(100);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);
		
		col = new Column();
		col.setName("launcher");
		col.setWidth(200);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("Inicio");
		col.setWidth(120);
		col.setEditable(false);
		col.setClass(String.class);
		col.setAligment(JTextField.LEFT);
		table.addColumn(col);

		table.setData(results);
		Font fuente = new Font("Arial", Font.PLAIN, 9);
		table.setHeaderFont(fuente);
		table.setFont(fuente);
		table.build();
		table.fillData();
		JTable _table = table.getTable();
		
		_table.addMouseListener(this.getConstructor().getMouseListener());
		_table.addKeyListener(this.getConstructor().getKeyListener());
		_table.getTableHeader().addMouseListener(this.getConstructor().getMouseListener());
		
		
	}
	public void check(){
		if (SystemTray.isSupported()) {
			this.create_interface();		
		    SystemTray tray = SystemTray.getSystemTray();
		    
		    

		    TrayIcon[] trays=tray.getTrayIcons();
		    if (trays!=null){
		    	for (int i=0;i<trays.length;i++){
		    		if (_debug>0) System.out.println(trays[i].getClass());
		    		
		    	}
		    }
		}
	}
	
	public void setTrayIcon(String iduser){
		if (SystemTray.isSupported()) {
			check();
			this.create_interface();		
		    SystemTray tray = SystemTray.getSystemTray();
		    URL resourceURL = getClass().getClassLoader().getResource("icons/chrome.png");
		    String id="";
		    if (this.getConstructor().getConnectionHandler().getDefaultConnector()!=null){
		    	id=this.getConstructor().getConnectionHandler().getDefaultConnector().getId();	
		    }
		    boolean printer=this.getUserPrinter();
		    String version=(String) data.getParametroSqlite("version")[0][1];
		    
		    String mini="Beta "+version+" "+iduser+" ("+id+") ";
		    if (printer){
		    		mini+="Servidor De Impresion";
		    }
	        
		    trayIcon = new TrayIcon(new ImageIcon(resourceURL).getImage(), mini, popup);
		    trayIcon.setImageAutoSize(true);
		    trayIcon.addMouseListener(this.getConstructor().getMouseListener());
		    try {
		    	tray.add(trayIcon);
		    } catch (Exception e) {
		        System.err.println("TrayIcon could not be added.");
		    }
		    
		    trayIcon.displayMessage("Beta",
	                mini, TrayIcon.MessageType.NONE);
		}else {
			if (_debug>0) System.out.println("SystemTray is not Supported");
		}
	}
	
	public void exit_command(){
		removeIcon();
		try {
			if (this.getConstructor().getCaller()!=null){
				this.getConstructor().getCaller().getLogic().exit_command();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame.setVisible(false);
		System.exit(0);
	}
	
	public void removeIcon(){
		if (SystemTray.isSupported()) {
			this.create_interface();		
		    SystemTray tray = SystemTray.getSystemTray();
		    tray.remove(trayIcon);
		}
	}
	public void loadUserAreas(){
		Runnable _execute=new Runnable(){
			public void run(){
		
				_loadUserAreas();
			}
		};
		this.invokeLater(_execute);
	}
	
	
	public void kill(){
		System.out.println("////Tree////");
		this.getConstructor().print();
		System.out.println("<///Tree///>");
		List<Constructor> childs=this.getConstructor().getChilds();
		
		for (int i=0;i<childs.size();i++){
			System.out.println("Killing> "+childs.get(i).getClass());
			childs.get(i).dispose();
		}
		childs=new ArrayList<Constructor>();
		this.garbage();
	}
	
	public void _loadUserAreas(){
		Object[][] results=null;
		/*
		if (local){
		results=data.loadUserAreas(iduser);
		}else {
			
		}*/
		results=data._system_loadUserAreas(iduser);
		if (results!=null){
			if (results.length>0){
				JMenuBar menuBar=new JMenuBar();
				for (int i=0;i<results.length;i++){
					String area=(String)results[i][0];
					JMenu jMenu =new JMenu(area);
					menuBar.add(jMenu);
					loadAplications(area, jMenu);
		
					
				}
				_frame.setJMenuBar(menuBar);
			}
		}
	}
	
	
	private void launchApplicacion(String lanzador,String menu){
		
		
		aplicacion.herramientas.java.launcher.constructor._Constructor
	    C=new aplicacion.herramientas.java.launcher.constructor._Constructor();
	    C.setGraphical(false);
	    ConnectionHandler handler=this.getConstructor().getConnectionHandler();
	    if (handler!=null){
	    	C.setParameter(_parametros.connector,handler);
	    }
	    C.setParameter(_parametros.iduser, this.iduser);
	    C.setParameter(_parametros.iddeposito, this.getConstructor().getIdDeposito());
	    C.setParameter(_parametros.LookAndFeel,this.getConstructor().getLookAndFeelTheme());
	    C.build(this.getConstructor());
	    C.init();
	    
	    aplicacion.herramientas.java.launcher.logic._Logic logic=(aplicacion.herramientas.java.launcher.logic._Logic)C.getLogic();
		try {
			logic.runAplication(lanzador);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			displayError("Error Lanzando Aplicacion "+menu, e.getMessage(), e.getLocalizedMessage(), e);
		}
		
		
		
		C.setCaller(this.getConstructor());
		this.getConstructor().addChild(C);
		done=true;
	}
	
	
	public void loadAplications(String area,JMenu jMenu){
		Object[][] results=null;
		/*
		if (local){
		results=data.loadAplications(area,iduser);
		}else {
			
		}*/
		results=data._system_loadAplications(area,iduser);;
		if (results!=null){
			if (results.length>0){
				for (int i=0;i<results.length;i++){
					
					String nombre=(String)results[i][1];
					String lanzador=(String)results[i][2];
						if (nombre.compareTo("")!=0){
							JMenuItem jMenuItem = new JMenuItem(nombre);
							 jMenuItem.setName(lanzador);
							 
							 jMenuItem.addActionListener(this.getConstructor().getActionListener());
							 jMenuItem.setFont(new Font("Dialog", Font.PLAIN, 10));
							 
							 jMenu.add(jMenuItem);	
						}
				}
			}
		}
		 
	}
	
public BufferedImage toBufferedImage(Image image) {
		
        if (image instanceof BufferedImage) {
            return (BufferedImage)image;
        }
    
        // This code ensures that all the pixels in the image are loaded
        image = new ImageIcon(image).getImage();
    
        // Determine if the image has transparent pixels; for this method's
        // implementation, see e661 Determining If an Image Has Transparent Pixels
        //boolean hasAlpha = hasAlpha(image);
    
        // Create a buffered image with a format that's compatible with the screen
        BufferedImage bimage = null;
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            // Determine the type of transparency of the new buffered image
            int transparency = Transparency.TRANSLUCENT;
          /*  if (hasAlpha) {
                transparency = Transparency.BITMASK;
            }*/
    
            // Create the buffered image
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gs.getDefaultConfiguration();
            bimage = gc.createCompatibleImage(
                image.getWidth(null), image.getHeight(null), transparency);
        } catch (HeadlessException e) {
            // The system does not have a screen
        }
    
        if (bimage == null) {
            // Create a buffered image using the default color model
            int type = BufferedImage.TYPE_INT_RGB;
            /*if (hasAlpha) {
                type = BufferedImage.TYPE_INT_ARGB;
            }*/
            bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
        }
    
        // Copy image to buffered image
        Graphics g = bimage.createGraphics();
    
        // Paint the image onto the buffered image
        g.drawImage(image, 0, 0, null);
        g.dispose();
    
        return bimage;
    }

	public  Image scale(Image image, int width, int height)  {
		
		BufferedImage bsrc = toBufferedImage(image);
		BufferedImage bdest =new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bdest.createGraphics();
		AffineTransform at =AffineTransform.getScaleInstance((double)width/bsrc.getWidth(),
					(double)height/bsrc.getHeight());
		g.drawRenderedImage(bsrc,at);
		image=Toolkit.getDefaultToolkit().createImage(bdest.getSource());
		return image;
		
	}

	
	
	public void mouseExited(Component c){
		JButton button=(JButton)c;
		if (_debug>0) System.out.println("Ejecutar Zoom Out para >"+button.getActionCommand());
		this.goZoomOut(button);
    }
	
	public void mouseClick(Component c,int x, int y){
    	
    }
	
	public void mouseEntered(Component c,int x, int y){
		JButton button=(JButton)c;
		if (_debug>0) System.out.println("Ejecutar Zoom In para >"+button.getActionCommand());
		this.goZoomIn(button);
	}
	
	public void goZoomIn(JButton button){
		Rectangle backup=button.getBounds();
		button.setLocation(backup.x, backup.y-2);
	}
	
	public void goZoomOut(JButton button){
		Rectangle backup=button.getBounds();
		button.setLocation(backup.x, backup.y+2);
	}
	
	public void mouseMove(Component c,int x, int y){
		
		
		
		
    	    	
    	
    	
    }
	
	public printing getPrintingInterface(){
		printing pi = new printing();
		String port="LPT1";
		String posicion_horizontal_columna0="H0010";
		String posicion_horizontal_columna1="H0460";
		String posicion_vertical_fila0="V0005";
		String posicion_vertical_fila1="V0030";
		String posicion_vertical_fila1e="V0090";
		String codigo_de_barras="BG01050";
		int posicion_fila_descripcion=88;
		int incremento_fila_descripcion=21;
		
		String _posicion_fila_descripcion=data.getParametroServer("posicion_fila_descripcion");	
		if (_posicion_fila_descripcion.compareTo("")==0){
			data.insertParametroServer("posicion_fila_descripcion", ""+posicion_fila_descripcion);
		}else{
			posicion_fila_descripcion=new Integer(_posicion_fila_descripcion);
		}
		
		String _incremento_fila_descripcion=data.getParametroServer("incremento_fila_descripcion");	
		if (_incremento_fila_descripcion.compareTo("")==0){
			data.insertParametroServer("incremento_fila_descripcion", ""+incremento_fila_descripcion);
		}else{
			incremento_fila_descripcion=new Integer(_incremento_fila_descripcion);
		}
		String _port=data.getParametroServer("port");	
		if (_port.compareTo("")==0){
			data.insertParametroServer("port", port);
		}else{
			port=_port;
		}
		String _posicion_horizontal_columna0=data.getParametroServer("posicion_horizontal_columna0");
		if (_posicion_horizontal_columna0.compareTo("")==0){
			data.insertParametroServer("posicion_horizontal_columna0", posicion_horizontal_columna0);
		}else{
			posicion_horizontal_columna0=_posicion_horizontal_columna0;
		}
		String _posicion_horizontal_columna1=data.getParametroServer("posicion_horizontal_columna1");
		if (_posicion_horizontal_columna1.compareTo("")==0){
			data.insertParametroServer("posicion_horizontal_columna1", posicion_horizontal_columna1);
		}else{
			posicion_horizontal_columna1=_posicion_horizontal_columna1;
		}
		String _posicion_vertical_fila0=data.getParametroServer("posicion_vertical_fila0");
		if (_posicion_vertical_fila0.compareTo("")==0){
			data.insertParametroServer("posicion_vertical_fila0", posicion_vertical_fila0);
		}else{
			posicion_vertical_fila0=_posicion_vertical_fila0;
		}
		String _posicion_vertical_fila1=data.getParametroServer("posicion_vertical_fila1");
		if (_posicion_vertical_fila1.compareTo("")==0){
			data.insertParametroServer("posicion_vertical_fila1", posicion_vertical_fila1);
		}else{
			posicion_vertical_fila1=_posicion_vertical_fila1;
		}
		String _posicion_vertical_fila1e=data.getParametroServer("posicion_vertical_fila1e");
		if (_posicion_vertical_fila1e.compareTo("")==0){
			data.insertParametroServer("posicion_vertical_fila1e", posicion_vertical_fila1e);
		}else{
			posicion_vertical_fila1e=_posicion_vertical_fila1e;
		}
		String _codigo_de_barras=data.getParametroServer("codigo_de_barras");
		if (_codigo_de_barras.compareTo("")==0){
			data.insertParametroServer("codigo_de_barras", codigo_de_barras);
		}else{
			codigo_de_barras=_codigo_de_barras;
		}
		
		pi.setCodigo_de_barras(codigo_de_barras);
		pi.setPort(port);
		pi.setPosicion_horizontal_columna0(posicion_horizontal_columna0);
		pi.setPosicion_horizontal_columna1(posicion_horizontal_columna1);
		pi.setPosicion_vertical_fila0(posicion_vertical_fila0);
		pi.setPosicion_vertical_fila1(posicion_vertical_fila1);
		pi.setPosicion_vertical_fila1e(posicion_vertical_fila1e);
		pi.setIncremento_fila_descripcion(incremento_fila_descripcion);
		pi.setPosicion_fila_descripcion(posicion_fila_descripcion);
		return pi;
	}
	
	 
	  /*
	   insert into b_parametros ('etq_pos_x','30')
insert into b_parametros ('etq_pos_y','8')
insert into b_parametros ('etq_col0','H0010')
insert into b_parametros ('etq_col1','H0460')
insert into b_parametros ('etq_row0','V0005')
insert into b_parametros ('etq_row1','V0030')
insert into b_parametros ('etq_row1e','V0090')
insert into b_parametros ('etq_code128','BG01050')
insert into b_parametros ('etq_row_desc','88')
insert into b_parametros ('etq_inc_desc','21')
insert into b_parametros ('etq_port','LPT1')
	   */
	
	public void imprimir_etiquetas() {

		LinkedList l = new LinkedList();
		String q="select id,idarticulo,descripcion,cantidad,especial,especial_width,especial_height,quitarprefijo from b_etiquetas where impresa=0 order by id ";
		List<String> ids=new ArrayList<String>();	
		Object[][] results=data.getResults(q);
		if (_debug>0) System.out.println("Etiquetas para imprimir="+results.length);
		for (int i = 0; i < results.length; i++) {
			String idarticulo = "";
			String id = "";
			String descripcion = "";
			String cant = "";
			String width = "";
			String height = "";
			String prefijo = "";
			int _width=40;
			int _height=40;
			double _cant = 0.0;
			boolean especial=false;
			try {
				id = (String) results[i][0];
				idarticulo = (String) results[i][1];
				descripcion = (String) results[i][2];
				cant = (String) results[i][3];
				especial = ((String) results[i][4]).compareTo("1")==0;
				width = (String) results[i][5];
				height = (String) results[i][6];
				prefijo = (String) results[i][7];
				_width=new Integer(width);
				_height=new Integer(height);
				_cant = new Double(cant);
			} catch (Exception e) {

			}
			ids.add(id);
			if (_cant >= 1) {
				StrEtiqueta str_etiqueta = new StrEtiqueta();
				str_etiqueta.setCodigo(idarticulo);
				str_etiqueta.setDescripcion(descripcion);
				str_etiqueta.setCantidad(new Double(_cant).intValue());
				str_etiqueta.setEspecial(especial);
				str_etiqueta.setWidth(_width);
				str_etiqueta.setHeight(_height);
				str_etiqueta.setQuitar_prefijo(prefijo.compareTo("1")==0);
				l.add(str_etiqueta);
			}
		}
		
		
		
		
		if (l.size() > 0) {

			// ImpresionCodigoDeBarras PI=new ImpresionCodigoDeBarras();

				printing pi = this.getPrintingInterface();
				pi.setPrintList(l);
				pi.setDebug(false);
				boolean ok = pi.print_job();
				if (ok){
					if (_printer_error>0){
						this.trayIcon.displayMessage("Beta Servidor de Impresion", "Se Enviaron las Etiquetas Pendientes a la Impresora", TrayIcon.MessageType.INFO);	
					}else{
						this.trayIcon.displayMessage("Beta Servidor de Impresion", "Se Enviaron las Etiquetas a la Impresora", TrayIcon.MessageType.INFO);
					}
					_printer_error=0;
					data.beginTransaction();
					data.clearBatch();
						for (int i=0;i<ids.size();i++){
							q="update b_etiquetas set impresa=1 where id like '"+ids.get(i)+"'";
							data.addBatch(q);
						}
						data.executeBatch();
						data.commitTransaction();	
						
				}else{
					if (_printer_error<=0){
						this._clock_printer_error=this._clock_printer_error_reset;
						System.out.println("primer error");
					}
					_printer_error=1;
					
					
				}
				
			// PI.armar_secuencia();

		}

	}
	public void console(){
		console=!console;
		if (console){
			this.StartConsole();
		}else{
			this.StopConsole();
		}
		
	}
	public void StopConsole(){
		frame.getJScrollPane().setVisible(false);
		frame.getJTextArea().setVisible(false);
		frame.getJTextArea().setText("");
	}
	public void StartConsole(){
		Dimension s=Toolkit.getDefaultToolkit().getScreenSize();
		int width=new Double(s.getWidth()).intValue();
		int height=new Double(s.getHeight()).intValue();
		frame.getJScrollPane().setBounds(100, 100, width-200, height-280);
		frame.getJScrollPane().setVisible(true);
		frame.getJTextArea().setVisible(true);
		frame.getJTextArea().setText("");
	}
	
private void setContents(File aFile) {
		
		List<String> lineas=new ArrayList<String>();
	    //...checks on aFile are elided
	    StringBuilder contents = new StringBuilder();
	    int current=0;
	    try {
	      //use buffering, reading one line at a time
	      //FileReader always assumes default encoding is OK!
	      BufferedReader input =  new BufferedReader(new FileReader(aFile));
	      try {
	        String line = null; //not declared within while loop
	        /*
	        * readLine is a bit quirky :
	        * it returns the content of a line MINUS the newline.
	        * it returns null only for the END of the stream.
	        * it returns an empty String if two newlines appear in a row.
	        */
	        while (( line = input.readLine()) != null){
	        	if (current>this.current_line){
	        		
	        		if (frame.getJTextArea().getRows()>max_lines){
		        	    try {
							frame.getJTextArea().replaceRange("",frame.getJTextArea().getLineStartOffset(0),frame.getJTextArea().getLineStartOffset(1)-1);
						} catch (BadLocationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		        	}
	        		frame.getJTextArea().append(line);
	        		frame.getJTextArea().append(System.getProperty("line.separator"));
	        		
	        	}
	          
	          current++;
	        }
	        current_line=current;
	      }
	      finally {
	        input.close();
	      }
	    }
	    catch (IOException ex){
	      ex.printStackTrace();
	    }
	    
	    
	  }
	
	private List<String> getContents(File aFile) {
		
		List<String> lineas=new ArrayList<String>();
	    //...checks on aFile are elided
	    StringBuilder contents = new StringBuilder();
	    int current=0;
	    try {
	      //use buffering, reading one line at a time
	      //FileReader always assumes default encoding is OK!
	      BufferedReader input =  new BufferedReader(new FileReader(aFile));
	      try {
	        String line = null; //not declared within while loop
	        /*
	        * readLine is a bit quirky :
	        * it returns the content of a line MINUS the newline.
	        * it returns null only for the END of the stream.
	        * it returns an empty String if two newlines appear in a row.
	        */
	        while (( line = input.readLine()) != null){
	        	if (current>this.current_line){
	        		
	        		if (lineas.size()>max_lines){
		        	    lineas.remove(0);
		        	}
	        		lineas.add(line);
	        	}
	          
	          current++;
	        }
	      }
	      finally {
	        input.close();
	      }
	    }
	    catch (IOException ex){
	      ex.printStackTrace();
	    }
	    
	    return lineas;
	  }
	
	private aplicacion.actualizacion.importar.constructor._Constructor precios=null;
	public void price(){
		if (this.server){
			this.check_precio();
		}
	}
	
	private aplicacion.actualizacion.importar.logic._Data _vpdata= null;
	private aplicacion.actualizacion.importar.logic._Logic _vplogic=null;
	public void check_precio(){
		if (_vpdata==null){
			_vpdata=new aplicacion.actualizacion.importar.logic._Data();
			ConnectionHandler ch=this.getConstructor().getConnectionHandler().Clone();
			ch.startConnections();
			_vpdata.setSql(ch);	
		}
		if (_vplogic==null){
			_vplogic=new aplicacion.actualizacion.importar.logic._Logic();
			_vplogic.setConstructor(this.getConstructor());
			_vplogic.setData(_vpdata);
			_vplogic.prepareDestination();
			_vplogic.load_variables();	
		}
		if (!_vplogic.isUpdating()){
			boolean ok=_vplogic.check();
			if (ok){
				_vplogic._goUpdateNoFrame();
				this.trayIcon.displayMessage("Beta Actualizacion ", "Actualizacion Automatica de Precios En Curso...", TrayIcon.MessageType.WARNING);	
			}	
		}else{
			System.out.println("Se esta Actualizando");
		}
		
	}
	
	public void taskwork(){
		SwingWorker worker = null;
		if (worker == null) {
			worker = new SwingWorker() {
				public Object construct() {
					return new _taskWork();
				}
			};
		}
		
		worker.start();	
	}
	
	private aplicacion.sistema.scantool.constructor._Constructor scantool=null;
	public void init_scan() {
		if (scantool==null) {
			scantool=new aplicacion.sistema.scantool.constructor._Constructor();
			scantool.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme()
			);
			scantool.setParameter(_parametros.connector, this.getConstructor()
			.getConnectionHandler());
			scantool.setParameter(_parametros.iduser, this.getConstructor()
			.getIduser());
			
			scantool.setShowOnStartup(false);
			scantool.build(this.getConstructor());
			scantool.init();
			aplicacion.sistema.scantool.logic._Logic
			logic=(aplicacion.sistema.scantool.logic._Logic) scantool.getLogic();
			logic.setTx(frame.get_txt_codigo());
		}
	}
	public void focus(){
		frame.toFront();
		frame.requestFocus();
		frame.get_txt_codigo().requestFocusInWindow();
	}
	
	public void buscar(JTextField tx){
		aplicacion.sistema.scantool.logic._Logic
		logic=(aplicacion.sistema.scantool.logic._Logic) scantool.getLogic();
		logic.setTx(tx);
		logic.evaluate_codigo(tx);
	}
}

