package aplicacion.asterisk.manager.logic;


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
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

import javax.swing.table.*;
import javax.swing.text.BadLocationException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
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

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleInsets;

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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import aplicacion.asterisk.manager.gui.PanelAgenda;
import aplicacion.asterisk.manager.gui._Frame;
import aplicacion.asterisk.manager.logic._Data;
import aplicacion.asterisk.manager.interfaces._Interface;


import aplicacion.compras.carga.items.logic.StrEtiqueta;
import aplicacion.compras.carga.items.logic.printing;
import aplicacion.herramientas.java.launcher.logic.SwingWorker;
import aplicacion.herramientas.java.launcher.logic.Task_Model;
import aplicacion.herramientas.java.table.Column;
import aplicacion.herramientas.java.table.CustomTable;
import aplicacion.herramientas.java.xml.Element;
import aplicacion.herramientas.java.xml.XML;
import aplicacion.herramientas.java.Convertidor;

import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Data;
import aplicacion.modelo.logic.Logic;
import aplicacion.modelo.constructor.*;
import aplicacion.asterisk.manager.logic._Update;
import aplicacion.asterisk.manager.logic._Error;
import aplicacion.herramientas.conexion.ConnectionHandler;
import aplicacion.herramientas.conexion.conectores.Generic;
public class _Principal extends Logic{
	private _Frame frame=null;
	private _Data data=null;
	
	private boolean work_email=false;
	private boolean work_printer=false;
	private boolean work_prices=false;
	//private boolean work_email=false;
	
	private JPanel memorypanel=null;
	
	private int hx=30; //distancia desde la barra de inicio
	private int bh=18; //altura menu frame
	private String iduser="";
	private TrayIcon trayIcon;
	private  PopupMenu popup;
	private boolean tunel=false;
	private List<BufferedImage> images = null;
	private boolean monitor=false;
	private boolean server=false;
	private boolean aleatorio=true;
	private boolean maximizado=false;
	private int timeScreen=0;
	private String last_file="";
	private boolean local=false;
	private boolean load_agenda=false;
	private Timer Timer;  //  @jve:decl-index=0:
	private int image=-1;
	private Timer MultiTimer;
	private Crono crono;
	private String estado="";
	private double memory_record=0;
	private int current;
	private _Update updater=null;
	private _Error statics=null;
	private boolean printer=false;
	private boolean pause=true;
	private TimeSeries memory = new TimeSeries("Memoria");
	private int lenght;
	private boolean done=false,canceled;
	private int errors=0;
	private int max_lines=20;
	private int current_line=0;
	private boolean console=false;
	private String temporal_directory="C:\\Windows\\Temp\\screenshots\\";
	private String destination="C:\\windows\\temp\\_beta";
	private int _clock;
	private int _clock_multimedia;
	private int _clock_multimedia_reset;
	private int _clock_messages;
	private int _clock_messages_reset;
	private int _clock_show_messages;
	private int _clock_show_messages_reset;
	private int _clock_monitor;
	private int _clock_monitor_reset;
	private int _clock_printer;
	private int _clock_printer_reset;
	private int _clock_printer_error;
	private int _clock_printer_error_reset; 
	private int _printer_error;
	private int _clock_memory;
	private int _clock_memory_reset;
	private int _clock_garbage;
	private int _clock_garbage_reset;
	private int _clock_system_check;
	private int _clock_system_check_reset;
	private int _clock_update;
	private int _clock_update_reset;
	private int _clock_statics;
	private int _clock_statics_reset;
	private int _clock_update_price;
	private int _clock_update_price_reset;
	
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
	
	class _taskSystemCheck {
		_taskSystemCheck() {
			_taskworkSystemCheck();
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
	
public void _taskworkSystemCheck(){
		this.system_check();
		
	}
	
	public void _taskworkCargar(String lanzador,String menu){
		
		boolean ok=true;//this.data.getConnectionHandler().getDefaultConnector().testConection();
		if (ok){
			current=1;
			estado="Cargando Aplicacion "+menu;
			this.launchApplicacion(lanzador);
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

	
	
	public void goCargar(String lanzador,String menu) {
		if (Timer!=null) {
			if(Timer.isRunning()){
				this.endbar();
			}
		}
		
		//System.out.println("CARGAR TRABAJO");
		frame.getJProgressBar().setString("Cargando Aplicacion "+menu);
		frame.getJProgressBar().setStringPainted(true);
		frame.getJProgressBar().setIndeterminate(true);
		frame.getJProgressBar().setVisible(true);
		crono=new Crono();
		crono.start();
		current=0;
		errors=0;
		done = false;
		canceled=false;
		
		
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
		}else{
			this.createTimer();	
		}
		worker.start();
		
	}	
	
	public void goSystemCheck() {
		
		SwingWorker worker = null;
		if (worker == null) {
			worker = new SwingWorker() {
				public Object construct() {
					return new _taskSystemCheck();
				}
			};
		}
		worker.start();
		
	}	
	
	public _Principal(){
		super();
		//this._debug=1;
		images=new ArrayList<BufferedImage>();
		//System.out.println("OS:"+this.getOS());
		if (this.getOS().compareTo("Windows 7")==0){
			this.hx=40;
		}
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
		//frame.get_btn_eliminar().setVisible(su);
		//frame.get_btn_agregar().setVisible(su);
	}
	
	public void create_interface(){
		  popup = new PopupMenu();
		
		  MenuItem menuItem;
		  menuItem = new MenuItem("Salir");
		  menuItem.setActionCommand(_Interface._btn_salir);
		  menuItem.addActionListener(this.getConstructor().getActionListener());
		  menuItem.setFont(new Font("Dialog", Font.PLAIN, 10));
		  popup.add(menuItem);
		  menuItem = new MenuItem("Minimizar");
		  menuItem.setActionCommand(_Interface._btn_minimizar);
		  menuItem.addActionListener(this.getConstructor().getActionListener());
		  menuItem.setFont(new Font("Dialog", Font.PLAIN, 10));
		  popup.add(menuItem);
	}
	
	public void show(){
		this.minimizado();
		_frame.setVisible(true);
		//_frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		_frame.requestFocus();
		_frame.toFront();
		
	}
	
	public void createTimer(){
		Timer=new Timer(500, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//System.out.println("TIMER ");
				if (done){
					endbar();
					
					//System.out.println("TIMER STOPPED. RUNNINNG="+Timer.isRunning());
					}
			}
		}); 
		
		
	}
	
	private void initialize_clocks(){
		 _clock=200;
		 int _clock_segundo=5;//ciclos por segundo
		 int _clock_minuto=_clock_segundo*60;
		 int _clock_hora=_clock_minuto*60;
		 _clock_multimedia=0;
		 _clock_multimedia_reset=_clock_minuto*5;
		 _clock_messages=30;
		 _clock_messages_reset=_clock_minuto;
		 _clock_show_messages=-30;
		 _clock_show_messages_reset=_clock_segundo*15;
		 _clock_monitor=-20;
		 _clock_monitor_reset=_clock_minuto*2;
		 _clock_printer=0;
		 _clock_printer_reset=_clock_segundo*2;
		 _clock_printer_error=0;
		 _clock_printer_error_reset=_clock_minuto*1+_clock_segundo*20;
		 _printer_error=0;
		 _clock_memory=1;
		 _clock_memory_reset=_clock_segundo*5;
		 _clock_garbage=30;
		 _clock_garbage_reset=_clock_minuto*2;
		 _clock_system_check=0;
		 _clock_system_check_reset=_clock_minuto*10;
		 _clock_update=0;
		 _clock_update_reset=_clock_hora*2;
		 _clock_statics=0;
		 _clock_statics_reset=_clock_hora*5;
		 _clock_update_price=0;
		 _clock_update_price_reset=_clock_hora*1;
		
	}
	
	

	public void refresh(){
		frame.repaint();
	}
	
	private void do_multimedia(){
		if (this._clock_multimedia>this._clock_multimedia_reset){
			this._clock_multimedia=0;
			this.go_work_Executer(_Interface._work_multimedia);
		}
		this._clock_multimedia++;
	}
	private void do_monitor(){
		if (this._clock_monitor>this._clock_monitor_reset){
			this._clock_monitor=0;
			this.go_work_Executer(_Interface._work_monitor);
		}
		this._clock_monitor++;
	}
	
	private void do_printer(){
		if (this._clock_printer>this._clock_printer_reset){
			this._clock_printer=0;
			
		}
		this._clock_printer++;
	}
	
	private void do_printer_error(){
		if (this._clock_printer_error>this._clock_printer_error_reset){
			this._clock_printer_error=0;
			this.go_work_Executer(_Interface._work_printer_error);
		}
		this._clock_printer_error++;
	}
	
	
	
	private void do_messages(){
		if (this._clock_messages>this._clock_messages_reset){
			this._clock_messages=0;
			this.go_work_Executer(_Interface._work_messages);
		}
		this._clock_messages++;
	}
	
	private void do_show_messages(){
		if (this._clock_show_messages>this._clock_show_messages_reset){
			this._clock_show_messages=0;
			this.go_work_Executer(_Interface._work_show_messages);
		}
		this._clock_show_messages++;
	}
	
	private void do_garbage(){
		if (this._clock_garbage>this._clock_garbage_reset){
			this._clock_garbage=0;
			this.go_work_Executer(_Interface._work_garbage);
		}
		this._clock_garbage++;
	}
	
	
	private void do_memory(){
		if (this._clock_memory>this._clock_memory_reset){
			this._clock_memory=0;
			this.go_work_Executer(_Interface._work_memory);
		}
		this._clock_memory++;
	}
	
	
	private void do_update(){
		if (this._clock_update>this._clock_update_reset){
			this._clock_update=0;
			this.go_work_Executer(_Interface._work_update);
		}
		this._clock_update++;
	}
	
	private void do_statics(){
		if (this._clock_statics>this._clock_statics_reset){
			this._clock_statics=0;
			this.go_work_Executer(_Interface._work_statics);
		}
		this._clock_statics++;
		
	}

	private void do_update_prices(){
		if (this._clock_update_price>this._clock_update_price_reset){
			this.go_work_Executer(_Interface._work_update_prices);
			this._clock_update_price=0;
		}
		this._clock_update_price++;
	}

	
	private void go_work_Executer(String work){
		if (this._debug==1){
			System.out.println("Execute: "+work);
		}
		SwingWorker worker = null;
		final String _work=work;
		if (worker == null) {
			worker = new SwingWorker() {
				public Object construct() {
					return new _task_work_EXECUTER(_work);
				}
			};
		}
		worker.start();
	}
	class _task_work_EXECUTER {
		_task_work_EXECUTER(String work) {
			if (work==_Interface._work_multimedia){
				work_multimedia();
			}
			if (work==_Interface._work_monitor){
				work_monitor();		
			}
			if (work==_Interface._work_printer){
				work_printer();	
			}
			if (work==_Interface._work_messages){
				work_messages();	
			}
			if (work==_Interface._work_show_messages){
				work_show_messages();	
			}
			if (work==_Interface._work_garbage){
				work_garbage();	
			}
			if (work==_Interface._work_memory){
				work_memory();	
			}
			if (work==_Interface._work_update){
				work_update();	
			}
			if (work==_Interface._work_statics){
				work_statics();	
			}
			if (work==_Interface._work_printer_error){
			work_printer_error();
			}
			if (work==_Interface._work_update_prices){
			work_update_prices();
			}
		}
	}

	
	private void work_statics(){
		statics();
	}
	private void work_update(){
		update();
	}
	private void work_memory(){
		memory();
	}
	private void work_update_prices(){
		price();
	}
	private void work_multimedia(){
		multimedia();
	}
	private void work_monitor(){
		monitor();
	}
	private void work_printer(){
		printer();
	}
	private void work_printer_error(){
		printer_error();	
	}
	private void work_messages(){
		//read_messages();
	}
	private void work_show_messages(){
		show_messages();
	}
	private void work_garbage(){
		refresh();
		garbage();
	}
	
	public void work(){
		do_multimedia();
		do_monitor();
		do_printer();
//		if (this.load_agenda){
		do_messages();
		do_show_messages();
//		}
		do_garbage();
		do_memory();
		do_update();
		do_statics();
		do_printer_error();
		do_update_prices();
	}
	
	public void check_process(){
		if (_vplogic!=null){
			String estado=_vplogic.getEstado();
			//System.out.println(estado);
			
		}
	}
	
	public void system_check(){
		this.getInfo();
		this.isRunnningAnotherBeta();
	}
	
	public void printer_error(){
		if (printer){
		if (this._printer_error>0){
			System.out.println("Error de Impresion de Etiquetas." +
					" Revise Conexion y Estado de la Impresora." +
					" Las Etiquetas seran impresas automaticamente. ");
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

		if (javax.swing.SwingUtilities.isEventDispatchThread()){
			getMemoryState();
		}else{
			
			Runnable _execute=new Runnable(){
				public void run(){
					getMemoryState();
				}
			};
			this.invokeLater(_execute);	
		}
	}
	public void update(){
		if (_debug>0) System.out.println("WORK:UPDATE");
		goCheckVersion();
	}
	public void statics(){
		if (_debug>0) System.out.println("WORK:UPDATE");
		goSendStatics();
	}
	public void printer(){
		if (_debug>0)System.out.println("WORK:PRINTER "+printer);
		if (printer){
			
			if (_debug>0)System.out.println("WORK:PRINTER");
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
		if (_debug>1) System.out.println("WORK:MONITOR "+monitor);
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
	
	public List listRunningProcesses() {
		List<String> processes = new ArrayList<String>();
		try {
		String line;
		Process p = Runtime.getRuntime().exec("tasklist /nh /v");
		BufferedReader input = new BufferedReader (new InputStreamReader(p.getInputStream()));
		while ((line = input.readLine()) != null) {
		if (!line.trim().equals("")) {
		// keep only the process name
			System.out.println(line);
			String process=line.substring(0, line.indexOf(" "));
			if (line.contains("Activo")){
				process+=" ACTIVO";
			}
			processes.add(process);
		}

		}
		input.close();
		}
		catch (Exception err) {
		err.printStackTrace();
		}
		return processes;
		}

	public void getInfo(){
		this.listTools("mem");
		this.listTools("ipconfig /all");
		this.listTools("netstat -an");
		
	}
	
	public List listTools(String command) {
		List<String> processes = new ArrayList<String>();
		try {
		String line;
		Runtime runtime=Runtime.getRuntime();
		Process p = runtime.exec("cmd.exe /C "+command);
		BufferedReader input = new BufferedReader (new InputStreamReader(p.getInputStream()));
		while ((line = input.readLine()) != null) {
		if (!line.trim().equals("")) {
		// keep only the process name
			System.out.println(line);
			String process=line.substring(0, line.indexOf(" "));
			if (line.contains("Activo")){
				process+=" ACTIVO";
			}
			processes.add(process);
		}

		}
		input.close();
		}
		
		catch (Exception err) {
		err.printStackTrace();
		}
		
	return processes;
	}
	
	public int isRunnningAnotherBeta(){
			
			List<String> processes = listRunningProcesses();
			String result = "";
			String NEW_LINE = System.getProperty("line.separator");
			// display the result
			Iterator<String> it = processes.iterator();
			int i = 0;
			System.out.println("Listing Running processes :");
			int running=0;
			while (it.hasNext() ) {
				
				String process=it.next();
				//System.out.println(process);
				if (process.contains("Beta.exe") & process.contains("ACTIVO")){
					
					running++;
				}
			i++;
			if (i==10) {
			result += "\n ";
			i = 0;
			}
			}	
			System.out.println("Procesos Beta.exe Corriendo?"+running);
			return running;
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
	    	
		 if (updater==null){
			 updater=new _Update();
			 	
			 	updater.setData(_data);
			 	updater.setConstructor(this.getConstructor());
			 	updater.setFrame(frame);
			 	updater.setTrayIcon(trayIcon);
		 }
		 updater.load_variables();
		 updater.goCheck();
		 	
	    }
	 
	 public void goSendStatics() {
	    	
		 if (statics==null){
			 statics=new _Error();
			 	
			 	statics.setData(_data);
			 	statics.setConstructor(this.getConstructor());
			 	statics.setFrame(frame);
			 	
		 }
		 
		 statics.goEnviar();
		 	
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
				
				/*frame.setVisible(true);
				frame.requestFocusInWindow();
				frame.toFront();*/
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
		Double freeMB=this.getFreeMemoryMB();
		int value=free.intValue();
		int valuetotal=total.intValue();
		int valuefree=freeMB.intValue();
		if (free<10){
			this.trayIcon.displayMessage("Beta", "Nivel de Memoria Bajo "+free+"% ", TrayIcon.MessageType.WARNING);
		}
		
		try {
			//this.memory.add(sec, value);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		frame.get_bar_memory().setValue(value);
		frame.get_bar_memory().setString("Memoria Disponible "+new Convertidor().getMoney(free,2)+"%  "+valuefree+"MB/"+valuetotal+"MB ");
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
		
		try {
			Timer.stop();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	public void minimizado(){
		maximizado=false;
		
		Dimension d=this._frame.getSize();
		Dimension s=Toolkit.getDefaultToolkit().getScreenSize();
		int width=new Double(s.getWidth()).intValue();
		int height=new Double(s.getHeight()).intValue();
		
		load_initial_image(width,height);
		
		int cw=frame.getJTabbedPane().getWidth();
		int ch=frame.getJTabbedPane().getHeight();
		
		frame.setBounds(width-cw, height-ch-hx-bh, cw+10, ch+bh);
		frame.setMinimumSize(new Dimension(cw+10, ch+bh));
		frame.getJTabbedPane().setVisible(true);
		frame.getJTabbedPane().setBounds(0, 0,cw,ch);
		
		frame.getJTabbedPane().repaint();
		frame.setVisible(true);
	}
	public void _modify(){
		maximizado=true;
		frame.getJTabbedPane().setVisible(false);
		
		Dimension d=this._frame.getSize();
		Dimension s=Toolkit.getDefaultToolkit().getScreenSize();
		int width=new Double(s.getWidth()).intValue();
		int height=new Double(s.getHeight()).intValue();
		load_initial_image(width,height);
		String iduser=this.getConstructor().getIduser();
		if (iduser.compareTo("agustinw")!=0){
			//frame.get_btn_kill().setVisible(false);
		}
		int bar_width=new Double(width*0.7).intValue();
		int bar_width_half=new Double(width*0.3*0.5).intValue();
		frame.getJTabbedPane().setBounds(width-frame.getJTabbedPane().getWidth(), height-frame.getJTabbedPane().getHeight()-hx-bh,frame.getJTabbedPane().getWidth(),frame.getJTabbedPane().getHeight());
		frame.getJTabbedPane().repaint();
		frame.setPreferredSize(new Dimension(width+10, height));
		frame.setSize(width+10,height-hx);
	    frame.setMinimumSize(new Dimension(width+10,height-hx));
	    //_frame.setExtendedState(_frame.getExtendedState() | _frame.MAXIMIZED_BOTH);
		//frame.getJProgressBar().setLocation(bar_width_half, height-110);
		//frame.getJProgressBar().setSize(bar_width, 16);
		frame.setLocation(0,0);
		//frame.get_bar_memory().setLocation(bar_width_half, height-92);
		//frame.get_bar_memory().setSize(bar_width, 16);
		
		//frame.getJToolBar().setLocation(frame.getWidth()-frame.getJToolBar().getWidth()-20, frame.getJToolBar().getLocation().y);
		//frame._version.setLocation(width-110, height-98);
		//frame.getJProgressBar().setVisible(false);
		
		String id="";
		if (this.getConstructor().getConnectionHandler().getDefaultConnector()!=null){
			id=this.getConstructor().getConnectionHandler().getDefaultConnector().getId();
		}
		frame.setTitle(_frame.getTitle()+" ("+id+")");
		frame.setResizable(false);
		frame.getJTabbedPane().setVisible(true);
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
	
	}
	public void maximizado(){
		maximizado=true;
    	modify();
    }
	public boolean getMaximizado(){
		return this.maximizado;
	}
	
	public void init(){
		
		
		this.initialize_clocks();
		try {
			tunel=this.getData().getConnectionHandler().getDefaultConnector().hasTunnel();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		Runnable showModalDialog = new Runnable() {
		    public void run() {
		    	//createGraphics();
		    	loadUserAreas();
		    	loadVersion();
		    	monitor=getUserMonitor();
		    	printer=getUserPrinter();
		    	server=getUserServer();
		    	freeMemory();
				minimizado();
				
				cargarIconos();
				createTrayIcon();
				createInitialTable();
				focus();
				//load_agenda = data.getAgenda(getConstructor().getIduser());
				
					//loadAgenda();
		    }
    		};
    	createMultiTimer();
    	this.invokeLater(showModalDialog);
//    	this.goCheckVersion();
    	
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
		    
		    /*trayIcon.displayMessage("Beta",
	                mini, TrayIcon.MessageType.NONE);*/
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
		
				_loadUserAreas();		
		
		
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
				
				final JMenuBar _menuBar=menuBar;
				Runnable _execute=new Runnable(){
					public void run(){
						System.out.println("Load User Areas set menu bar. Invoking Later");
						_frame.setJMenuBar(_menuBar);
					}
				};
				this.invokeLater(_execute);
				
			}
		}
	}
	
	public void runAplication(String clase){
		//aviso("Running Aplicacion "+clase);
		estado="Corriendo Aplicacion "+clase;
		System.out.println("Corriendo Aplicacion "+clase);
		try {
			
			
			Class c=Class.forName(clase);
			Class v0=Class.forName(_Interface._task_model_v0);
			Class v1=Class.forName(_Interface._task_model_v1);
			Object	oc=null;
			if (c!=null){
				boolean error=false;
				try {
					
					oc=c.newInstance();
					
				} catch (java.lang.Error e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					error=true;
					if (error){
						displayError("Error Creando Instancia de Aplicacion", e.getMessage(), e.getLocalizedMessage(), e);
					}
				}
				if (oc!=null & !error){
					if (oc.getClass().getSuperclass()==v1){
						this.run_v1(clase);
					}
					if (oc.getClass().getSuperclass()==v0){
						this.run_v0(clase);
					}		
				}
				
			}
			
			
			
		} catch (Exception e) {
				
				e.printStackTrace();
				displayError("Error En Carga de Aplicacion", e.getMessage(), e.getLocalizedMessage(), e);
		}
		Runnable _execute=new Runnable(){
			public void run(){
				endbar();		
			}
		};
		this.invokeLater(_execute);
	}

	
	
	/**
	 * Este metodo es para mantener la compatibilidad con aplicaciones viejas
	 * Hasta que pueda pasarlas a la version por capas. Q es mas facil de mantener
	 * Entender y Mejorar!!
	 */
	public void run_v0(String clase){
		//aviso("Ejecutando "+clase+" Tipo V0");
		
		Object	oc=null;
		
			
		
		try {
			Class c=Class.forName(clase);
			oc=c.newInstance();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (oc!=null){
			visual.Task_Model T=(visual.Task_Model) oc;
			int i=0;
			beta.tools.connector.GTransfer GX=null;
			
			while (i<this.getConstructor().getParametros().size() & GX==null){
				Object[] p=(Object[]) this.getConstructor().getParametros().get(i);
				String id=(String)p[0];
				Object o=p[1];
				if (id.compareTo("GX")==0){
					GX=(beta.tools.connector.GTransfer)o;
				}
				i++;
			}
			if (GX==null){
				GX=new beta.tools.connector.GTransfer();
				Generic _default=this.getConstructor().getConnectionHandler().getDefaultConnector();
				GX.setHost(_default.getHost());
				GX.setPort(_default.getPort());
				GX.setDatabase(_default.getDatabase());
				GX.setUser(_default.getUser());
				GX.setPassword(_default.getPassword());
				GX.setSucursal(_default.getId());
				GX.ConnectSQL(_default.getId());
			}
			boolean b=true;//esta_autorizado(iduser,clase);
			if (b){
				String NEW_LINE = System.getProperty("line.separator");
				/*String msg="Esta es una aplicacion vieja."+NEW_LINE;
				msg+="Hasta que no se reprograme. Esta aplicacion quedara inhabilitada por problemas de incompatibilida";
				error(msg);*/
				
				T.setParameter("GX", GX);
				T.setParameter("iduser", this.getConstructor().getIduser());
				for (i=0;i<this.getConstructor().getParametros().size();i++){
					try {
					Object[] p=(Object[]) this.getConstructor().getParametros().get(i);
					String id=(String)p[0];
					Object o=p[1];
					T.setParameter(id, o);
					} catch (Exception e) {
						System.out.println("No se pudo Cargar el parametro "+i+" >"+clase+" "+e.getMessage());
					}
				}
				
				
				
				if (javax.swing.SwingUtilities.isEventDispatchThread()){
					T.run();
				}else{
					final visual.Task_Model _T=T;
					Runnable _execute=new Runnable(){
						public void run(){
							_T.run();
						}
					};
					javax.swing.SwingUtilities.invokeLater(_execute);	
				}
				
			}else {
				error("("+this.getConstructor().getIduser()+") No esta Autorizado");
				T=null;
			}	
		}
	}

	public void run_v1(String clase){
		//aviso("Ejecutando "+clase+" Tipo V1 Params:"+this.getConstructor().getParametros().size());
		Object	oc=null;
		try {
			Class c=Class.forName(clase);
			oc=c.newInstance();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (oc!=null){
			Task_Model T=(Task_Model) oc;
			boolean b=true;//esta_autorizado(iduser,clase);
			if (b){
				
				for (int i=0;i<this.getConstructor().getParametros().size();i++){
					try {
					Object[] p=(Object[]) this.getConstructor().getParametros().get(i);
					String id=(String)p[0];
					Object o=p[1];
					T.setParameter(id, o);
					} catch (Exception e) {
						System.out.println("No se pudo Cargar el parametro "+i+" >"+clase+" "+e.getMessage());
						e.printStackTrace();
					}
				}
				
				if (javax.swing.SwingUtilities.isEventDispatchThread()){
					T.run(getConstructor());
				}else{
					final Task_Model _T=T;
					Runnable _execute=new Runnable(){
						public void run(){
							_T.run(getConstructor());
						}
					};
					javax.swing.SwingUtilities.invokeLater(_execute);	
				}	
			}else {
				error("("+this.getConstructor().getIduser()+") No esta Autorizado");
				T=null;
			}	
		}
		
	}
	
	public void launchApplicacion(String lanzador){
		//String menu="";
		
		this.runAplication(lanzador);
		
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
		String q="select id,isnull(idarticulo,''),isnull(descripcion,''),isnull(cantidad,0),isnull(especial,0),isnull(especial_width,0),isnull(especial_height,0),isnull(quitarprefijo,0) from b_etiquetas where isnull(impresa,0)=0 order by id ";
		List<String> ids=new ArrayList<String>();	
		Object[][] results=data.getResults(q);
		if (results!=null){
			if (results.length>0){
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
						try {
							_width=new Integer(width);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							_height=new Integer(height);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						_cant = new Double(cant);
					} catch (Exception e) {
						e.printStackTrace();
					}
					ids.add(id);
					if (_cant >= 1) {
						if (idarticulo.compareTo("")!=0){
							if (descripcion.compareTo("")!=0){
								descripcion.replaceAll("'", "");
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
								System.out.println("Se Enviaron las Etiquetas Pendientes a la Impresora");
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
			
		}
		
	}
	
	
	private aplicacion.actualizacion.importar.constructor._Constructor precios=null;
	public void price(){
		if (_debug>0)System.out.println("WORK:PRICE "+server);
		if (this.server){
			this.check_precio();
		}
	}
	
	private aplicacion.actualizacion.importar.logic._Data _vpdata= null;
	private aplicacion.actualizacion.importar.logic._Logic _vplogic=null;
	public void check_precio(){
		System.out.println("VERIFICANDO ACTUALIZACIONES DISPONIBLES");
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
			System.out.println("Actualmente no Se esta Actualizando. asi que se procede a ver que hay disponible para descargar");
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
	
	/*
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
	}*/
	public void focus(){
		frame.toFront();
		frame.getJTabbedPane().setSelectedIndex(0);
		frame.requestFocus();
		
	}
	public ImageIcon resize(String resource,int width,int height){
		Image img = new ImageIcon(getClass().getResource(resource)).getImage();  
		Image newimg = img.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH);  
		ImageIcon imgx = new ImageIcon(newimg);  
		return imgx;
	}
	public void cargarIconos(){
		Rectangle bounds=frame.getJPanel_principal().getBounds();
		Object[][] icons=data.getIconos(iduser);
		int w=50;
		int h=50;
		int x_ini=10;
		int x=x_ini;
		int y=30;
		int inc=8;
		if (icons!=null){
			if (icons.length>0){
				for (int i=0;i<icons.length;i++){
					String area=icons[i][0].toString();
					String nombre=icons[i][1].toString();
					String icono=icons[i][2].toString();
					String lanzador="lanzador:"+icons[i][3].toString();
					JButton btn=new JButton();
					btn.setBounds(x, y, w, h);
					btn.setToolTipText(nombre);
					btn.setIcon(this.resize(icono, w,h));
					btn.setActionCommand(lanzador);
					btn.addActionListener(this.getConstructor().getActionListener());
					frame.getJPanel_principal().add(btn);
					x+=w+inc;
					if (x+w>bounds.width){
						x=x_ini;
						y+=h+inc;
					}
				}
				
			}
		}
	}
	/*
	public void buscar(JTextField tx){
		aplicacion.sistema.scantool.logic._Logic
		logic=(aplicacion.sistema.scantool.logic._Logic) scantool.getLogic();
		logic.setTx(tx);
		logic.evaluate_codigo(tx);
	}*/
	
	public void ccreateGraphics(){
		
		this.memory.setMaximumItemCount(300);
		new Second();
		if (memorypanel!=null){
			frame.getJPanel_acerca().remove(memorypanel);	
		}
		memorypanel=this.createDemoPanel(this.memory, "Uso de Memoria");
		memorypanel.setBounds(new Rectangle(16, 60, 476, 144));
		frame.getJPanel_acerca().add(memorypanel,null);
	}
	public JFreeChart createChart(XYDataset dataset, String nombre) {

		JFreeChart chart = ChartFactory.createTimeSeriesChart(nombre, // title
				"Hora", // x-axis label
				"", // y-axis label
				dataset, // data
				false, // create legend?
				false, // generate tooltips?
				false // generate URLs?
				);

		Color c=frame.getJPanel_acerca().getBackground();
		chart.setBackgroundPaint(c);

		XYPlot plot = (XYPlot) chart.getPlot();
		plot.setBackgroundPaint(Color.lightGray);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);
		plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
		plot.setDomainCrosshairVisible(true);
		plot.setRangeCrosshairVisible(true);

		XYItemRenderer r = plot.getRenderer();
		if (r instanceof XYLineAndShapeRenderer) {
			XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
			renderer.setBaseShapesVisible(false);
			renderer.setBaseShapesFilled(false);
			renderer.setDrawSeriesLineAsPath(true);
		}

		DateAxis axis = (DateAxis) plot.getDomainAxis();
		axis.setDateFormatOverride(new SimpleDateFormat("hh:ss"));

		return chart;

	}

	public static XYDataset createDataset(TimeSeries s1) {

		TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(s1);
		// dataset.addSeries(s2);
		return dataset;

	}

	public JPanel createDemoPanel(TimeSeries s1, String nombre) {
		JFreeChart chart = createChart(createDataset(s1), nombre);
		ChartPanel panel = new ChartPanel(chart);
		panel.setFillZoomRectangle(true);
		panel.setMouseWheelEnabled(true);
		return panel;
	}
	
	public String getRoll(String fecha,int months,int days,int hours,int minutes) {
		String formato="dd-MM-yyyy HH:mm:ss";
		Date today = new Convertidor().getDateWithFormat(formato, formato, fecha);
		java.util.GregorianCalendar _date = new java.util.GregorianCalendar();
		_date.setTime(today);
		if (months!=0){
			_date.add(Calendar.MONTH, months);	
		}
		if (days!=0){
			_date.add(Calendar.DAY_OF_MONTH, days);	
		}
		if (hours!=0){
			_date.add(Calendar.HOUR_OF_DAY, hours);	
		}
		if (minutes!=0){
			_date.add(Calendar.MINUTE, minutes);	
		}
		// _today.add(Calendar.DAY_OF_YEAR, days);
		Date before = _date.getTime();
		String s2 = new Convertidor().getDateWithFormat(formato, before);
		return s2;
	}
	
	DynamicFrame current_fx=null;
	int max=5;
	int ini=0;
	float opacity=1f;
	Timer timer=null;
	
	public void messages_hide(){
		if (current_fx!=null){
			max=10;
			ini=0;
			opacity=1f;
			timer=new Timer(200, new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					
					if (ini>=max){
						current_fx.dispose();
						max=0;
						ini=0;
						timer.stop();
						messages_show();
					}else{
						opacity=opacity-0.1f;
						if (opacity<0f){
							opacity=0f;
						}
						com.sun.awt.AWTUtilities.setWindowOpacity(current_fx, opacity);
						ini++;
					}
				}
			}); 
			timer.start();
		}
	}
	
	
	int fxy=0;
	int fxinc=0;
	public void messages_show(){
		if (avisos!=null){
			if (avisos.size()>0){
				opacity=0.0f;
				
				current_fx=this.avisos.get(0);
				com.sun.awt.AWTUtilities.setWindowOpacity(current_fx, opacity);
				current_fx.setAlwaysOnTop(true);
				
				current_fx.setVisible(true);
				Dimension d=current_fx.getSize();
				 Dimension s=Toolkit.getDefaultToolkit().getScreenSize();
				 double w=s.getWidth()*0.5;
				 double h=s.getHeight()*0.5;
				 double wf=d.getWidth()*0.5;
				 double hf=d.getHeight()*0.5;
				 int x=new Double(w-wf).intValue();
				 fxy=-new Double(d.getHeight()).intValue();//new Double(h-hf).intValue();
				 
				max=20;
				//fxinc=new Double(new Double(fxy)/new Double(max)).intValue();
				fxinc=8;
				
				ini=0;
				timer=new Timer(100, new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						
						if (ini>=max){
							opacity=1f;
							
							com.sun.awt.AWTUtilities.setWindowOpacity(current_fx, opacity);
							fxy=0;
							current_fx.setLocation(current_fx.getLocation().x,fxy);
							max=0;
							ini=0;
							timer.stop();
						}else{
							opacity=opacity+0.05f;
							if (opacity>1f){
								opacity=1f;
							}
							com.sun.awt.AWTUtilities.setWindowOpacity(current_fx, opacity);
							
							
							if (fxy>=0){
								fxy=0;
							}else{
								fxy+=fxinc;	
							}
							current_fx.setLocation(current_fx.getLocation().x,fxy);
							ini++;
						}
					}
				}); 
				timer.start();
				avisos.remove(0);
			}
			
		}
	}
	public void show_messages(){
		
		if (current_fx!=null){
			this.messages_hide();
		}else{
			this.messages_show();
		}
		
		
		
	}
	
	public void read_messages(){
		String fecha=data.getSystemDateTime();
		int minutos=2;
		String fecha_limit=this.getRoll(fecha, 0, 0, 0, -minutos);
		String iduser="   9";
		String idhost=data.getHost();
		Object[][] results=data.getMessages(iduser, idhost, fecha_limit);
		if (results!=null){
			if (results.length>0){
				for (int i=0;i<results.length;i++){
					String idaviso=(String) results[i][0];
					String user=(String) results[i][1];
					String titulo=(String) results[i][2];
					String mensaje=(String) results[i][3];
					String privado=(String) results[i][4];
					String pantalla=(String) results[i][8];
					boolean priv=privado.compareTo("1")==0;
					boolean screen=pantalla.compareTo("1")==0;
					//process_message(idaviso, iduser, titulo, mensaje, idhost,priv,screen);
				}
			}
		}
	}
	
	_Agenda _agenda=null;
    PanelAgenda agenda=null;
    private void loadAgenda(){
    	    agenda=new PanelAgenda();
    		_agenda=new _Agenda();
    		_agenda.setConstructor(this.getConstructor());
    		agenda.get_btn_nuevo().setActionCommand(_Interface._btn_nuevo_aviso);
    		agenda.get_btn_nuevo().addActionListener(this.getConstructor().getActionListener());
    		agenda.get_btn_agenda().setActionCommand(_Interface._btn_agenda);
    		agenda.get_btn_agenda().addActionListener(this.getConstructor().getActionListener());
    		_agenda.setData(data);
    		_agenda.setFrame(agenda);	
    		_agenda.init();
    		
    	//_asterisk.simulate("5358237");
    		frame.getJTabbedPane().addTab("Agenda", new ImageIcon(getClass().getResource("/icons/calendar-16.gif")), agenda, null);
    		
    		_agenda.cargar_historial();
    }
	
	private void process_message(String idaviso,String iduser,String titulo,String message,String idhost,boolean privado,boolean screen){
		
		data.clearBatch();
		String q=data.getInsertShow(idaviso, iduser, idhost);
		data.addBatch(q);
		
		boolean error=data.executeBatch();
		if (!error){
			frame.setVisible(false);
			//_agenda.cargar_historial();
			/*frame.getJTabbedPane().setSelectedComponent(agenda);
			frame.setVisible(true);
			frame.toFront();*/
			
			if (screen){
				String nombre=data.getVendedor(iduser);
				String mensaje="  "+nombre+": "+titulo+"  ";
				this.aviso_sin_repeticion(idaviso, iduser, titulo, mensaje,nombre);
			}
		}
		
	}
	
	public void close(DynamicFrame fx){
		String idaviso=fx.getIdAviso();
		String iduser=fx.getIdUser();
		boolean active=false;
		if (avisos!=null){
			int i=0;
			while (!active & i<avisos.size()){
				DynamicFrame tmp=avisos.get(i);
				active=tmp.getIdAviso().compareTo(idaviso)==0&tmp.getIdUser().compareTo(iduser)==0;
				if (active){
					System.out.println("quitando activo de los activos "+idaviso+" "+iduser);
					tmp.dispose();
					avisos.remove(i);
				}
				i++;
			}
		}
		fx.setVisible(false);
		
	}
	
	public DynamicFrame dynamic(String idaviso,String iduser,String titulo,String mensaje,String nombre){
		DynamicFrame fx=new DynamicFrame();
		fx.setIdaviso(idaviso);
		fx.setIduser(iduser);
		fx.setTitle("Recordatorio para "+nombre);
		if (mensaje.length()>100){
			mensaje=mensaje.substring(0,100);
		}
		
		fx._txt_mensaje.setText(mensaje);
		fx.setDefaultCloseOperation(DynamicFrame.DO_NOTHING_ON_CLOSE);
		fx.setName(_Interface._dynamic_frame);
		fx.addWindowListener(this.getConstructor().getWindowListener());
		fx.get_btn_aceptar().setActionCommand(_Interface._btn_dynamic);
		fx.get_btn_aceptar().addActionListener(this.getConstructor().getActionListener());
		fx.get_btn_ver().setActionCommand(_Interface._btn_dynamic_edit);
		fx.get_btn_ver().addActionListener(this.getConstructor().getActionListener());
		fx.pack();
		top_frame(fx);
		
		
		return fx;
	}
	
	protected void top_frame(JFrame frame){
		 Dimension d=frame.getSize();
		 Dimension s=Toolkit.getDefaultToolkit().getScreenSize();
		 double w=s.getWidth()*0.5;
		 double h=s.getHeight()*0.5;
		 double wf=d.getWidth()*0.5;
		 double hf=d.getHeight()*0.5;
		 int x=new Double(w-wf).intValue();
		 int y=0;//new Double(h-hf).intValue();
		 frame.setLocation(x, y);
	}
	private LinkedList<DynamicFrame> avisos=null;
	
	private void _avisoSwing_sin_repeticion(String idaviso,String iduser,String titulo,String mensaje,String nombre) {
		final String _idaviso=idaviso;
		final String _iduser=iduser;
		final String _titulo=titulo;
		final String _mensaje=mensaje;
		final String _nombre=nombre;
		Runnable _execute=new Runnable() {
			   public void run() {
				   _aviso_sin_repeticion(_idaviso, _iduser, _titulo, _mensaje,_nombre);		   
			   }
		};
		this.invokeAndWait(_execute);
	}
	private void aviso_sin_repeticion(String idaviso,String iduser,String titulo,String mensaje,String nombre){
		if (javax.swing.SwingUtilities.isEventDispatchThread()){
			this._aviso_sin_repeticion(idaviso, iduser, titulo, mensaje,nombre);
			}
		else{
			this._avisoSwing_sin_repeticion(idaviso, iduser, titulo, mensaje,nombre);
		}
	}
	private void _aviso_sin_repeticion(String idaviso,String iduser,String titulo,String mensaje,String nombre){
		boolean active=false;
		DynamicFrame tmp=null;
		if (avisos!=null){
			int i=0;
			while (!active & i<avisos.size()){
				tmp=avisos.get(i);
				active=tmp.getIdAviso().toString().compareTo(idaviso)==0&tmp.getIduser().toString().compareTo(iduser)==0;
				i++;
			}
		}else{
			avisos=new LinkedList<DynamicFrame>();
		}
		if (!active){
			System.out.println("Agregando aviso a los activos hasta que cierre "+idaviso+" "+iduser);
			DynamicFrame fx=this.dynamic(idaviso, iduser, titulo, mensaje,nombre);
			avisos.addLast(fx);
		}
	}
	
}