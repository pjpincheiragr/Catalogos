package aplicacion.herramientas.conexion.login.logic;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.TrayIcon;
import java.awt.SystemTray;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.net.InetAddress;
import java.net.URL;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Logic;
import aplicacion.herramientas.conexion.Tunnel;
import aplicacion.herramientas.conexion.conectores.MsSQL;
import aplicacion.herramientas.conexion.conectores.MySQL;
import aplicacion.herramientas.conexion.conectores.SQLite;
import aplicacion.herramientas.conexion.login.gui.*;
import aplicacion.herramientas.conexion.login.logic.*;
import aplicacion.herramientas.conexion.ConnectionHandler;
import aplicacion.herramientas.conexion.login.interfaces.*;
import aplicacion.herramientas.java.Crono;
import aplicacion.herramientas.java.launcher.logic.SwingWorker;
//import aplicacion.herramientas.java.launcher.interfaces._Interface;
import aplicacion.modelo.logic.*;
import aplicacion.modelo.interfaces.*;
//com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel

import org.pushingpixels.substance.api.skin.SubstanceAutumnLookAndFeel;

import javax.swing.*;
public class _Logic extends Logic {
	private _Frame frame=null;
	private _Data data=null;
	private Tunnel tunnel=null;
	private boolean ingreso=true;
	private int mysql_port=4445;
	private int mssql_port=4444;
	private String estado="Iniciando";
	private int current;
	private boolean connected=false;
	private int lenght;
	private _splash splash=null;
	private boolean debug,done,canceled,override;
	private float opacity=1.0f;
	private Timer Timer;  //  @jve:decl-index=0:
	private Crono crono;
	private String[][] Skins=null;
	private String iddeposito="   1";
	
	public void initSkins(){
		Skins=new String[14][2];
		
		Skins[0]=new String[]{"org.pushingpixels.substance.api.skin.SubstanceBusinessLookAndFeel","Business"};
		Skins[1]=new String[]{"org.pushingpixels.substance.api.skin.SubstanceBusinessBlueSteelLookAndFeel","Business Blue"};
		Skins[2]=new String[]{"org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel","Business Black"};
		Skins[3]=new String[]{"org.pushingpixels.substance.api.skin.SubstanceCremeLookAndFeel","Creme"};
		Skins[4]=new String[]{"org.pushingpixels.substance.api.skin.SubstanceCremeCoffeeLookAndFeel","Creme Cofee"};
		Skins[5]=new String[]{"org.pushingpixels.substance.api.skin.SubstanceModerateLookAndFeel","Moderate"};
		Skins[6]=new String[]{"org.pushingpixels.substance.api.skin.SubstanceOfficeSilver2007LookAndFeel","Office"};
		Skins[7]=new String[]{"org.pushingpixels.substance.api.skin.SubstanceNebulaLookAndFeel","Nebula"};
		Skins[8]=new String[]{"org.pushingpixels.substance.api.skin.SubstanceNebulaBrickWallLookAndFeel","NebulaBrick"};
		Skins[9]=new String[]{"org.pushingpixels.substance.api.skin.SubstanceMistSilverLookAndFeel","MisSilver"};
		Skins[10]=new String[]{"org.pushingpixels.substance.api.skin.SubstanceMistAquaLookAndFeel","MistAqua"};
		Skins[11]=new String[]{"org.pushingpixels.substance.api.skin.SubstanceGeminiLookAndFeel","Gemini"};
		Skins[12]=new String[]{"org.pushingpixels.substance.api.skin.SubstanceRavenLookAndFeel","Raven"};
		Skins[13]=new String[]{"org.pushingpixels.substance.api.skin.SubstanceAutumnLookAndFeel","Autumn"};
		
		frame.get_lst_skin().removeAllItems();
		for (int i=0;i<Skins.length;i++){
			frame.get_lst_skin().addItem(Skins[i][1]);
		}
		
		
		
	}
	
	public boolean isIngreso() {
		return ingreso;
	}

	public void setIngreso(boolean ingreso) {
		this.ingreso = ingreso;
	}

	public void setFrame(JFrame frame){
		this.frame=(_Frame) frame;
		super.setFrame(frame);
	}
	
	public void setData(Data data){
		this.data=(_Data) data;
	}
	
	public void cargarSkins(){
		frame.get_lst_skin().removeAllItems();
		frame.get_lst_skin().removeItemListener(this.getConstructor().getItemListener());
		this.initSkins();		
		frame.get_lst_skin().setName(_Interface._lst_skins);
		
		frame.get_lst_skin().addItemListener(this.getConstructor().getItemListener());
	}
	
	public void setSkin(JComboBox lst){
		int n=lst.getSelectedIndex();
		
		String	lookAndFeelTheme=Skins[n][0];
		try {
            
	           UIManager.setLookAndFeel(lookAndFeelTheme);
	           getConstructor().setLookAndFeelTheme(lookAndFeelTheme);
	          
	          } catch (Exception e) {
	        	  e.printStackTrace();
	        	  
	          }
	          javax.swing.SwingUtilities.invokeLater(new Runnable(){
	        	  public void run() {
	        		  SwingUtilities.updateComponentTreeUI(_frame);
	                  
	              }

	          });
	          
	}
	
	public void setOpacity(JSlider lst){
		int value=lst.getValue();
		
		
		try {
			
			
			float opacity=new Float(value/100.0);
			//System.out.println(""+opacity+" bar value?"+value);
			if (opacity<0.5f){
				opacity=0.5f;
			}
			
			com.sun.awt.AWTUtilities.setWindowOpacity(_frame, opacity);  
	           getConstructor().setOpacity(opacity);
	          
	          } catch (Exception e) {
	        	  e.printStackTrace();
	        	  
	          }
	          javax.swing.SwingUtilities.invokeLater(new Runnable(){
	        	  public void run() {
	        		  SwingUtilities.updateComponentTreeUI(_frame);
	                  
	              }

	          });
	          
	}
	
	public void evaluate_idarticulo(JTextField tx){
	String value="";
	value=tx.getText();
	if (value.compareTo("")!=0){
		frame.get_txt_password().requestFocusInWindow();
	}else{
		aviso("ingrese usuario");
		tx.requestFocusInWindow();
	}
	}
	
	public void cargar_background(){
		if (this.getConstructor().isLookAndFeel()){
			
		}
	}
	public void focus(){
		frame.get_txt_usuario().requestFocusInWindow();
	}

	public void cargar_conexiones(){
		Object[][] results=data.get_conexiones("MsSQL");
		frame.get_list_conexion().removeAllItems();
		if (results!=null){
			if (results.length>0){
				for (int i=0;i<results.length;i++){
					String id=(String) results[i][0];
					frame.get_list_conexion().addItem(id);	
				}
				
			}
		}
	}
	
	
	/**
	 * Cargar Conexion al Manajador de Conexiones
	 * @param idconexion
	 */
	public MsSQL cargarLocal(){
		String idconexion=frame.get_list_conexion().getSelectedItem().toString();
		Object[][] results=data.get_conexion(idconexion);
		MsSQL mssql=null;
		if (results!=null){
			if (results.length>0){
				String host=(String) results[0][0];
				String port=(String) results[0][1];
				String database=(String) results[0][2];
				String user=(String) results[0][3];
				String password=(String) results[0][4];
				String instance=(String) results[0][11];
				mssql=this.getMsSQL(idconexion, host, port, database, instance, user, password);
				
			}
		}
		return mssql;
	}
	
	public MySQL cargarLocalMySQL(){
		String idmysql=data.getMySQLFromConnection(frame.get_list_conexion().getSelectedItem().toString());
		System.out.println("MysqL Connection?"+idmysql);
		Object[][] results=data.get_conexion(idmysql);
		MySQL mysql=null;
		if (results!=null){
			if (results.length>0){
				String host=(String) results[0][0];
				String port=(String) results[0][1];
				String database=(String) results[0][2];
				String user=(String) results[0][3];
				String password=(String) results[0][4];
				mysql=this.getMySQL(idmysql, host, port, database, user, password);
				
			}
		}
		return mysql;
	}
	public MsSQL cargarTunel(){
		String idconexion=frame.get_list_conexion().getSelectedItem().toString();
		Object[][] results=data.get_conexion(idconexion);
		MsSQL mssql=null;
		if (results!=null){
			if (results.length>0){
				String database=(String) results[0][2];
				String user=(String) results[0][3];
				String password=(String) results[0][4];
				String instance=(String) results[0][11];
				mssql=this.getMsSQL(idconexion, "127.0.0.1", ""+this.mssql_port, database, instance, user, password);
			}
		}
		return mssql;
	}
	
	public MySQL cargarTunelMySQl(String idconexion){
		Object[][] results=data.get_conexion(idconexion);
		MySQL mysql=null;
		if (results!=null){
			if (results.length>0){
				String database=(String) results[0][2];
				String user=(String) results[0][3];
				String password=(String) results[0][4];
				mysql=this.getMySQL(idconexion, "127.0.0.1", ""+this.mysql_port, database, user, password);
			}
		}
		return mysql;
	}
	
	public void crearTunnel(){
		String idconexion=frame.get_list_conexion().getSelectedItem().toString();
		estado="Cargando Conectores";
		Object[][] results=data.get_conexion(idconexion);
		MsSQL mssql=null;
		String host="";
		String port="";
		String database="";
		String user="";
		String password="";
		String ssh_host="";
		String ssh_port="";
		String ssh_user="";
		String ssh_password="";
		String mysql="";
		String mysql_host="";
		String mysql_port="";
		if (results!=null){
			if (results.length>0){
				host=(String) results[0][0];
				port=(String) results[0][1];
				database=(String) results[0][2];
				user=(String) results[0][3];
				password=(String) results[0][4];
				ssh_host=(String) results[0][6];
				ssh_port=(String) results[0][7];
				ssh_user=(String) results[0][8];
				ssh_password=(String) results[0][9];
				mysql=(String) results[0][10];
				Object[][] results_mysql=data.get_conexion(mysql);
				if (results_mysql!=null){
					if (results_mysql.length>0){
						mysql_host=(String)results_mysql[0][0];
						mysql_port=(String) results_mysql[0][1];
					}
				}
			}
		}
		if (tunnel!=null){
			tunnel.close();
		}
		tunnel=new Tunnel(){
			public void error(String message){
				super.error(message);
			}
			public void connected(){
				connectionTunnel();
			}
		};
		estado="Creando Tunnel SSH";		
		tunnel.setTunnelHost(ssh_host);
		tunnel.setSSHPort(new Integer(ssh_port));
		tunnel.addRedirection(this.mssql_port, host,new Integer(port));
		tunnel.addRedirection(this.mysql_port, mysql_host,new Integer(mysql_port));
		tunnel.setUser(ssh_user);
    	tunnel.setPassword(ssh_password);
    	/*
    	final Tunnel _tunnel=tunnel;
    	Runnable execute=new Runnable(){
    		public void run(){
    			boolean error=_tunnel.go();
    	    	if (error){
    	    		error("Error Creando Tunnel");
    	    	}
    		}
    	};
    	if (javax.swing.SwingUtilities.isEventDispatchThread()){
    		
    		boolean error=tunnel.go();
        	if (error){
        		error("Error Creando Tunnel");
        	}
    	}else{
    		this.invokeLater(execute);
    	}
    */
    	estado="Conectando Con Servidor SSH";
    	boolean error=tunnel.go();
    	if (error){
    		splash.setVisible(false);
    		frame.setVisible(true);
    		done=true;
    		error("Error Creando Tunnel");
    	}
	}
	
	
	public void connectionTunnel(){
		estado="SSH OK";
		String idconexion=frame.get_list_conexion().getSelectedItem().toString();
		estado="Conectando a Servidor 1";
		MsSQL mssql=cargarTunel();
		String idmysql=data.get_conexion_mysql(idconexion);
		estado="Conectando a Servidor 2";
		MySQL mysql=this.cargarTunelMySQl(idmysql);
		data.getConnectionHandler().addConector(mssql);
		data.getConnectionHandler().addConector(mysql);
		data.getConnectionHandler().setDefault(idconexion);
		
		this._login();
	}
	
	public void connectionLocal(){
		String idconexion=frame.get_list_conexion().getSelectedItem().toString();
		MsSQL mssql=cargarLocal();
		this.data.getConnectionHandler().addConector(mssql);
		this.data.getConnectionHandler().setDefault(idconexion);
		MySQL mysql=this.cargarLocalMySQL();
		try {
			this.data.getConnectionHandler().addConector(mysql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getConstructor().setConnectionHandler(data.getConnectionHandler());
		if (connected){
			this._login();	
		}else{
			splash.setVisible(false);
			frame.setVisible(true);
			error("Error de Conexion");
			frame.get_txt_password().setText("");
			done=true;	
		}
	}
	
	public void nuevaConexion(){
		aplicacion.herramientas.conexion.creator.constructor._Constructor 
		CC=new aplicacion.herramientas.conexion.creator.constructor._Constructor();
		CC.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler());
		CC.build(this.getConstructor());
		
		CC.init();
		aplicacion.herramientas.conexion.creator.logic._Logic 
		_logic=(aplicacion.herramientas.conexion.creator.logic._Logic)CC.getLogic();
		_logic.centrar();
	}
	
	public void editarConexion(){
		String auth="ipsilon";
		
		String authorization=this.requestPassword("Ingrese autorizacion");
		if (authorization.compareTo(auth)==0){
			aplicacion.herramientas.conexion.creator.constructor._Constructor 
			CC=new aplicacion.herramientas.conexion.creator.constructor._Constructor();
			CC.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler());
			CC.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
			CC.build(this.getConstructor());
			
			CC.init();
			
			aplicacion.herramientas.conexion.creator.logic._Logic 
			_logic=(aplicacion.herramientas.conexion.creator.logic._Logic)CC.getLogic();
			String idconexion=frame.get_list_conexion().getSelectedItem().toString();
			_logic.centrar();
			_logic.cargar(idconexion);	
		}else{
			error("El Codigo no es correcto. No puede gestionar las conexiones");
		}
	}
	
	public MySQL getMySQL(
			String idconexion,
			String host,
			String port,
			String database,
			String user,
			String password){
			//MsSQL
			MySQL mysql=new MySQL(this.getConstructor());;
			mysql.setHost(host);
			int _port=new Integer(port);
			mysql.setId("MySQL");
			mysql.setPort(_port);
			mysql.setUser(user);
			mysql.setPassword(password);
			mysql.setDatabase(database);
			
			mysql.connect();
		return mysql;
	}
	
	public MsSQL getMsSQL(
			String idconexion,
			String host,
			String port,
			String database,
			String instance,
			String user,
			String password){
			//MsSQL
			MsSQL mssql=new MsSQL(this.getConstructor());;
			mssql.setHost(host);
			int _port=new Integer(port);
			mssql.setPort(_port);
			mssql.setUser(user);
			mssql.setPassword(password);
			mssql.setDatabase(database);
			mssql.setInstance(instance);
			mssql.setTdsVersion("8.0");
			mssql.setId(idconexion);
			connected=mssql.connect();
			
			if (!connected){
				System.out.println("login connected?"+connected);
				splash.setVisible(false);
				frame.setVisible(true);
				error("Error de Conexion");
				frame.get_txt_password().setText("");
				done=true;
			}
		return mssql;
	}
	/**
	 * Devuelve -1 si no hay conexion
	 * Devuelve 0 si el login es incorrecto
	 * Devuelve 1 si el login es correcto
	 * @param user
	 * @param pass
	 * @return
	 */
	public int check_user_pass(String user,String pass){
		boolean ok=false;
		
		int n=-1; 
			Object[][] results=null;
			try {
				results = data.getUserCheck(user, pass);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (results!=null){
				if (results.length>0){
					String id=(String)results[0][0];
					ok=(user.toUpperCase().compareTo(id.toUpperCase())==0);
					if (ok){
						n=1;
					}else{
						n=0;
					}
					
				}
			}	
			
		
		return n;
	}
	public void exit_command(){
		
		try {
			if (this.getConstructor().getCaller()!=null){
				this.getConstructor().getCaller().dispose();
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			this.getConstructor().dispose();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Ingreso(){
		
		String iduser=frame.get_txt_usuario().getText();
		String conexion=frame.get_list_conexion().getSelectedItem().toString();
		estado="Cargando Connectores en la aplicacion";
		
		boolean error=false;
		try {
			if (this.getConstructor().getCaller()!=null){
				ConnectionHandler handler=data.getConnectionHandler().Clone();
				handler.startConnectionPools();
				this.getConstructor().getCaller().setParameter(_parametros.connector, handler);
				this.getConstructor().getCaller().setParameter(_parametros.iduser, iduser);
				this.getConstructor().getCaller().setParameter(_parametros.iddeposito, iddeposito);
				this.getConstructor().getCaller().setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
				this.getConstructor().getCaller().setLookAndFeelTheme(this.getConstructor().getLookAndFeelTheme());
				this.getConstructor().getCaller().setOpacity(this.getConstructor().getOpacity());
				this.getConstructor().getCaller().init();
				this.getConstructor().getCaller().reloadLookAndFeel();
				this.getConstructor().setCaller(null);
				System.gc();

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error=true;

		}
		done=true;
		if (!error){
			this.exit_command();	
		}
		
		
	}
	
	public void cargar_modos(){
			frame.get_lst_modo().removeAllItems();
			frame.get_lst_modo().addItem("Local");
			frame.get_lst_modo().addItem("Internet");	
		
		
		
	}
	
	public void loadVersion(){
		String version=data.getVersion();
		frame._txt_version_label.setText(version);
	}
	
	class loginTask {
		loginTask() {
			_loginTask();
			}
	}
	
	public void createTimer(){
		
		crono=new Crono();
		crono.start();
		this.showSPlash();
		splash.getJProgressBar().setIndeterminate(true);
		frame.setVisible(false);
		
		Timer=new Timer(100, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (done |canceled){
					endbar();
					crono.pause();
					Timer.stop();
					}else {
					//System.out.println("Timer Running "+crono.elapsed());
					updateBar();
				}
			}
		}); 
	}
	
	
	public void updateBar(){
		splash.getJProgressBar().setMaximum(lenght);
		splash.getJProgressBar().setValue(current);
		splash.getJProgressBar().setString(estado+" "+crono.elapsed());
		splash.getJProgressBar().setStringPainted(true);
		/*javax.swing.SwingUtilities.invokeLater(new Runnable() {
  			@Override
  			public void run() {
  			  SwingUtilities.updateComponentTreeUI(splash);
  			  opacity=opacity-0.005f;
  				com.sun.awt.AWTUtilities.setWindowOpacity(splash, opacity);
  				
  			}
  			});*/
	}
	
	public void endbar(){
		estado="";
		splash.getJProgressBar().setString("");
		splash.getJProgressBar().setIndeterminate(false);
		splash.getJProgressBar().setValue(0);
		
		splash.setVisible(false);
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

	public void showSPlash(){
		splash=new _splash();
		splash.setIconImage(new ImageIcon(getClass().getResource("/icons/chrome.png")).getImage());
		
		Dimension d=this.splash.getSize();
		 Dimension s=Toolkit.getDefaultToolkit().getScreenSize();
		 double w=s.getWidth()*0.5;
		 double h=s.getHeight()*0.5;
		 double wf=d.getWidth()*0.5;
		 double hf=d.getHeight()*0.5;
		 int x=new Double(w-wf).intValue();
		 int y=new Double(h-hf).intValue();
		 this.splash.setLocation(x, y);
		 this.splash.setVisible(true);
		 ImageIcon img=null;
			img=new ImageIcon(getClass().getResource("/icons/lambo.jpg"));
			Image _img=this.scale(img.getImage(), splash.getWidth(), splash.getHeight());
			if (img!=null){
				splash.getJLabel().setIcon(new ImageIcon(_img));	
			}
		 javax.swing.SwingUtilities.invokeLater(new Runnable() {
	  			@Override
	  			public void run() {
	  			  SwingUtilities.updateComponentTreeUI(splash);
	  				com.sun.awt.AWTUtilities.setWindowOpacity(splash, opacity);
	  				splash.setVisible(true);
	  				
	  			}
	  			});
       
	}
	public void goLogIn() {
		done = false;
		canceled=false;
		this.createTimer();
		
		SwingWorker worker=null;
			worker = new SwingWorker() {
				@Override
				public Object construct() {
					current = 0;
					done = false;
					return new loginTask();
				}
			};
			
			if (Timer!=null) {
				Timer.start();
			}
			worker.start();
			
		}
	
	public void _loginTask(){
		String idconexion=frame.get_list_conexion().getSelectedItem().toString();
		
		if (frame.get_lst_modo().getSelectedIndex()==0){
			this.connectionLocal();
		}else {
			this.crearTunnel();
		}
		
		
		
		
	}
	public void Reingreso(){
		aviso("Se Conecto Correctamente. El sistema reconectara a todas las aplicaciones.");
		if (this.getConstructor().getCaller()!=null){
			try {
				//this.getConstructor().getCaller().getFrame().setVisible(true);
				//System.out.println("Devolviendo Conectividad a:"+this.getConstructor().getCaller().getClass());
				
				
				
				
				Object[][] results=data.getConnectionHandler().getDefaultConnector().getResults("Select * from b_users");
				if (results!=null){
					if (results.length>0){
						//aviso("La conexion esta estable!");
						ConnectionHandler CH=data.getConnectionHandler().Clone();
						CH.startConnections();
						results=CH.getDefaultConnector().getResults("Select * from b_users");
						if (results!=null){
							if (results.length>0){
								//aviso("La conexion clonada esta estable!");
								this.getConstructor().getCaller().resetConnectionHandler(CH);
							}
						}
							
					}
				}
				//
//				this.getConstructor().getCaller().getLogic().getData().setSql(this.getConstructor().getConnectionHandler());
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			//System.out.println("NO hay caller definido! q raro! Devolviendo Conectividad a:"+this.getConstructor());	
		}
		done=true;
	}
	public void _login(){
		
		String user=frame.get_txt_usuario().getText();
		String pass=frame.get_txt_password().getText();
		estado="Checkeando Usuario";
		if (user.compareTo("")!=0 & pass.compareTo("")!=0){
			int n=this.check_user_pass(user, pass);
			if (n!=1){
				pass=this.getEncrypted(pass);
				pass=pass.replaceAll("'", "''");
				
				n=this.check_user_pass(user, pass);	
			}
			if (n==1){
				this.guardar_memoria();
				//System.out.println("User Pass [OK]");
				this.getConstructor().setIduser(user);
				this.getConstructor().setIdDeposito(iddeposito);
				this.getConstructor().getErrorHandler().setIduser(user);
				this.getConstructor().getConnectionHandler().getDefaultConnector().getConstructor().setIdDeposito(iddeposito);
				this.getConstructor().getConnectionHandler().getDefaultConnector().getConstructor().setIduser(user);
				
				if (ingreso){
					//System.out.println("> Ingreso");
					Ingreso();	
				}else{
					//System.out.println("> Reingreso");
					Reingreso();
				}
				
			}else {
				if (n==0){
					splash.setVisible(false);
					frame.setVisible(true);
					error("Error de usuario o password!");
					if (!frame.get_chk_recordar().isSelected()){
						frame.get_txt_password().setText("");	
					}
					done=true;	
				}else{
						splash.setVisible(false);
						frame.setVisible(true);
						error("Error de Conexion");
						frame.get_txt_password().setText("");
						done=true;
					
					
				}
				
			}	
		}
	}
	public void set(JComboBox lst,String value){
		boolean found=false;
		int i=0;
		while (i<lst.getItemCount() &!found){
			found=lst.getItemAt(i).toString().compareTo(value)==0;
			if (!found){
				i++;
			}else{
				lst.setSelectedIndex(i);
			}
		}
		
	}
	
	public void guardar_memoria(){
		String login_sucursal=frame.get_list_conexion().getSelectedItem().toString();
		String login_modo=frame.get_lst_modo().getSelectedItem().toString();
		String theme=frame.get_lst_skin().getSelectedItem().toString();
		
		/*String ingresos="";
		Object[][] results=data.getParameters("login_ingresos");
		if (results!=null){
			ingresos=results[0][1].toString();
		}
		int _ingresos=new Integer(ingresos);
		_ingresos++;
		ingresos=""+_ingresos;
		*/
		
		if (frame.get_chk_recordar().isSelected()){
		//System.out.println("Guardando Variables de inicio");
		String login_user=frame.get_txt_usuario().getText();
		String login_password=frame.get_txt_password().getText();
		
		
		data.update("login_user", login_user);
		data.update("login_password", login_password);
		data.update("login_remember", "1");
		
		}else{
		data.update("login_remember", "0");
		data.update("login_user", "");
		data.update("login_password", "");
		}
		data.update("login_sucursal", login_sucursal);
		data.update("login_modo", login_modo);
		data.update("lookandfeeltheme", theme);
		
		//data.update("login_ingresos", ingresos);
		
	}
	
	public void cargar_memoria(){
	
		boolean load=false;
		Object[][] results=null;
		results=data.getParametroSqlite("login_remember");
		String login_remember=(String)results[0][1];
		
		int level=0;
		
		this.set(frame.get_list_conexion(), (String)data.getParametroSqlite("login_sucursal")[0][1]);
		this.set(frame.get_lst_skin(), (String)data.getParametroSqlite("lookandfeeltheme")[0][1]);
		results=data.getParametroSqlite("login_modo");
		this.set(frame.get_lst_modo(), (String) results[0][1]);
		if (login_remember.compareTo("1")==0){
			results=data.getParametroSqlite("login_user");
			String login_user=(String) results[0][1];
			results=data.getParametroSqlite("login_password");
			String login_password=(String) results[0][1];
			
			frame.get_txt_usuario().setText(login_user);
			frame.get_txt_password().setText(login_password);
			frame.get_chk_recordar().setSelected(true);
		}else{
			frame.get_chk_recordar().setSelected(false);
		}
		
			
		
	}
	
	public void loadLookAndFeel(){
		String lookandfeel="";
		String lookandfeeltheme="";
		try {
			lookandfeel=(String)data.getParametroSqlite("lookandfeel")[0][1];
			lookandfeeltheme=(String)data.getParametroSqlite("lookandfeeltheme")[0][1];
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getConstructor().setLookAndFeel(false);
		if (lookandfeel!=null){
			if (lookandfeel.compareTo("")!=0){
				this.getConstructor().setLookAndFeel(true);
				if (lookandfeeltheme.compareTo("")!=0){
					this.getConstructor().setLookAndFeelTheme(lookandfeeltheme);	
				}
			}	
		}
		
	}
}
