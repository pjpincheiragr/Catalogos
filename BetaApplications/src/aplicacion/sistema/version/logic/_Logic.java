package aplicacion.sistema.version.logic;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import aplicacion.herramientas.java.launcher.logic.SwingWorker;

import aplicacion.herramientas.java.table.CheckBoxCellEditor;
import aplicacion.herramientas.java.table.Column;
import aplicacion.herramientas.java.table.CustomTable;
import aplicacion.herramientas.java.xml.Element;
import aplicacion.herramientas.java.xml.XML;
import aplicacion.modelo.logic.Logic;
import aplicacion.modelo.logic.Data;
import aplicacion.sistema.usuario.interfaces._Interface;
import aplicacion.sistema.version.gui.*;
import aplicacion.sistema.version.logic.*;
import aplicacion.sistema.version.interfaces.*;
import javax.swing.*;
import java.io.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.Iterator;
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
import aplicacion.sistema.version.test.*;
import java.util.List;
public class _Logic extends Logic {
	private _Frame frame=null;
	private _Data data=null;
	private   String ftpserver;
	private   String username;
    private   String password;
    private   String download;
    private   String file;
    private String version;
    //private String destination="";
    private JakartaFtpWrapper ftp=null;
    private String directory="";
    private String estado="";
	private Object[] test;
	private int current;
	private int lenght,max;
	private boolean debug,done,canceled,override;
	private Timer Timer;  //  @jve:decl-index=0:
	private Crono crono;
	private XML xml;
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
	 
	 public String getEstado(){
		 return estado;
	 }
	 
	 public void goUpdate() {
		 boolean running=isRunnningAnotherBeta()>1;
		 String NEW_LINE = System.getProperty("line.separator");
		 if (running){
			 error("Advertencia","Hay mas de una Instancia de Beta ejecutandose!!"+NEW_LINE+"Debe cerrar todas las instancias para poder realizar la actualizacion correctamente."); 
		 }else{
			 error("Advertencia","Se Recomienda Cerrar las Demas Apliaciones Beta antes de Actualizar.");
			 if (this.preguntar("Confirmar", "Continua Con la Actualizacion?")){
				 	 
				 _goUpdate();
			 }	 
		 }
		 
	 }
	 public void _goUpdate() {
		 	this.createTimer();
		 	estado="Descargando Actualizacion";
	    	frame.get_btn_descargar().setEnabled(false);
	    	SwingWorker worker=null;
	    		worker = new SwingWorker() {
	    			@Override
	    			public Object construct() {
	    				return new updateTask();
	    			}
	    		};
	    		
	    		
	    		if (Timer!=null) {
	    			Timer.start();
	    		}
	    		worker.start();
	    	}
	
	 public boolean isDone(){
		 return this.done;
	 }
	 public void goCheck() {
	    	this.createTimer();
	    	frame.get_txt_revision_disponible().setText("");
	    	frame.get_btn_descargar().setEnabled(false);
	    	frame.getJProgressBar().setIndeterminate(true);
	    	estado="Buscando Version Disponible";
	    	SwingWorker worker=null;
	    		worker = new SwingWorker() {
	    			@Override
	    			public Object construct() {
	    				
	    				return new checkTask();
	    			}
	    		};
	    		if (Timer!=null) {
	    			Timer.start();
	    		}
	    		worker.start();
	    	}
	
	public void setFrame(JFrame _frame){
		this.frame=(_Frame)_frame;
		super.setFrame(_frame);
	}
	
	public void setData(Data _data){
		this.data=(_Data)_data;
		super.setData(_data);
	}
	
		
	public boolean necesita_actualizar(){
		boolean nec=false;
		String rev_actual=frame.get_txt_revision_actual().getText();
		String rev_disponible=frame.get_txt_revision_disponible().getText();
		nec=rev_actual.compareTo(rev_disponible)!=0;
		return nec;
	}
	
	public void cargar(){
		this.load_variables();
		this.goCheck();
		frame.get_btn_descargar().setEnabled(false);
		
	}
	public void actualizar(){
		try {
			this.update();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	public void downloadFromFTP(FTPFile file,String localfile){
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

	public String getDestination(){
		String destination;
		if (this.isLinux()){
			destination="/usr/_beta";
		}else{
			destination="c:\\windows\\temp\\_beta";
		}
		return destination;
	}
	
	public void prepareDestination(){
		String destination=this.getDestination();
		File file=new File(destination);
		if (file.exists()){
			deleteDirectory(file);
		}
		boolean ok=file.mkdir();
		System.out.println("directory creation > "+destination+">?"+ok);	
	}
	
public boolean downloadFromFTP(String serverfile,String localfile,long filesize){
	System.out.println(serverfile+" size:"+filesize+" "+localfile);
	
	boolean ok=true;
	this.lenght=100;
    try {
        InputStream stO =  new BufferedInputStream(
                ftp.retrieveFileStream(serverfile),
                ftp.getBufferSize());
        
        
        OutputStream stD = new FileOutputStream(localfile);
        //ftp.enterRemotePassiveMode();
        
        
        long test=org.apache.commons.net.io.Util.copyStream(
                stO,
                stD,
                ftp.getBufferSize(),
                filesize,
                //org.apache.commons.net.io.CopyStreamEvent.UNKNOWN_STREAM_SIZE,
                new org.apache.commons.net.io.CopyStreamAdapter() {
                    public void bytesTransferred(
                    		long totalBytesTransferred,
                            int bytesTransferred,
                            long streamSize) {
                    		//System.out.println("bytetransfered: "+bytesTransferred+"|"+totalBytesTransferred+" "+streamSize);
                           current = (int)Math.ceil(((double)totalBytesTransferred/(double)streamSize) * 100);
                           //System.out.println("current: "+current);
                           if (canceled){
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
        System.out.println(serverfile+" downloaded:"+test);    
        //ftp.completePendingCommand();
        /*
        //stD.close();
        boolean ok=
        if (ok){
        	if (!canceled){
        		
        		//this.updateRevision();
            	
            	//actualiza();	
        	}
        	
        }else{
        	error("No se completo la operacion de descarga");
        }
        */
        System.out.println("current done?: "+current);
    } catch (Exception e) { 
    	e.printStackTrace();
    	ok=false;
    }
    
    done=true;
    return ok;
}

public boolean uploadFromFTP(String serverfile,String localfile,long filesize){
	System.out.println(serverfile+" size:"+filesize+" "+localfile);
	
	boolean ok=true;
	this.lenght=100;
    try {
        //InputStream stO =  new BufferedInputStream(
        //        ftp.retrieveFileStream(serverfile),
        //        ftp.getBufferSize());
        
        
        //OutputStream stD = new FileOutputStream(localfile);
        
    	InputStream stO =new FileInputStream(localfile);
    	OutputStream stD = new BufferedOutputStream(
    	        ftp.storeFileStream(serverfile));
        //ftp.enterRemotePassiveMode();
        
        
        long test=org.apache.commons.net.io.Util.copyStream(
                stO,
                stD,
                ftp.getBufferSize(),
                filesize,
                //org.apache.commons.net.io.CopyStreamEvent.UNKNOWN_STREAM_SIZE,
                new org.apache.commons.net.io.CopyStreamAdapter() {
                    public void bytesTransferred(
                    		long totalBytesTransferred,
                            int bytesTransferred,
                            long streamSize) {
                    		//System.out.println("bytetransfered: "+bytesTransferred+"|"+totalBytesTransferred+" "+streamSize);
                           current = (int)Math.ceil(((double)totalBytesTransferred/(double)streamSize) * 100);
                           //System.out.println("current: "+current);
                           if (canceled){
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
        System.out.println(serverfile+" downloaded:"+test);    
        //ftp.completePendingCommand();
        /*
        //stD.close();
        boolean ok=
        if (ok){
        	if (!canceled){
        		
        		//this.updateRevision();
            	
            	//actualiza();	
        	}
        	
        }else{
        	error("No se completo la operacion de descarga");
        }
        */
        System.out.println("current done?: "+current);
    } catch (Exception e) { 
    	e.printStackTrace();
    	ok=false;
    }
    
    done=true;
    return ok;
}

public void _actualizar(){
	this.isLinux();
	/*Runnable _execute=new Runnable(){
		public void run(){
			Sync sync=new Sync();
			sync.initConsole();
			sync.setSource(new File(destination));
			sync.setTarget(new File("C:\\Archivos de Programa\\Beta Systems\\Beta"));
			sync.prepareParameters();
			int n=sync.syncDirectory();		
		}
	};
	this.invokeLater(_execute);*/
	if (this.isLinux()){
		_actualizar_linux();
	}else{
		_actualizar_old();	
	}
	
	
}

public void _actualizar_old(){
	
	String newline = System.getProperty("line.separator");
	String download="C:\\Archivos de Programa\\Beta Systems\\Beta\\Beta.exe";
	
	try {
        String command = "cmd /c \""+download+"\"";
        File file=new File(download);
        
        if (file.exists()){
        	Process child = Runtime.getRuntime().exec(command);
        	
        	
            	
        }else{
        	error("No se Encuentra el archivo "+command);
        }
        
    } catch (IOException e) {
    	e.printStackTrace();
    	this.displayError("Error En Actualizacion",e.getMessage(), e.getLocalizedMessage(), e);
    }
    
	
	System.exit(0);
}

public void _actualizar_linux(){
	
	String newline = System.getProperty("line.separator");
	String download="/usr/beta/beta";
	
	try {
        String command = ""+download+"";
        File file=new File(download);
        
        if (file.exists()){
        	Process child = Runtime.getRuntime().exec(command);
        	
        	
            	
        }else{
        	error("No se Encuentra el archivo "+command);
        }
        
    } catch (IOException e) {
    	e.printStackTrace();
    	this.displayError("Error En Actualizacion",e.getMessage(), e.getLocalizedMessage(), e);
    }
    
	
	System.exit(0);
}

public void actualiza(){
	
	Runnable showModalDialog = new Runnable() {
        public void run() {
        	
        	if (preguntar("Confirmar","Descarga Completa. Desea Actualizar Ahora?")){
        			_actualizar();	
        	}
        }
    };
    javax.swing.SwingUtilities.invokeLater(showModalDialog);
    
	
	 

}

public void update(){
		long rev=0;
		 ftp = new JakartaFtpWrapper();
		try {
			if (ftp.connectAndLogin(ftpserver, username, password)) {
				System.out.println("Connected to " + ftpserver);
				try {
					ftp.setPassiveMode(true);
					
					ftp.changeWorkingDirectory(directory);
					
					Vector v=ftp.getFiles();
					for (int i=0;i<v.size();i++){
						FTPFile file=(FTPFile)v.get(i);
						if (file.getName().compareTo(this.file)==0){
							this.lenght=100;
							
							downloadFromFTP(file.getName(), download,file.getSize());
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
						//e.printStackTrace();
					}
				}
			} else {
				System.out.println("Unable to connect to" + ftpserver);
			}
			System.out.println("Finished");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		this.updateRevision();
		done=true;
	}
	
	
	public void load_variables(){
		sources=new ArrayList<Element>();
		
		try {
			version=(String)data.getParametroSqlite("version")[0][1];
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
		
		try {
			frame.get_txt_revision_actual().setText(version);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		try {
			this.ftpserver=(String)data.getParametroSqlite("ftp")[0][1];
			this.username=(String)data.getParametroSqlite("ftp_user")[0][1];
			this.password=(String)data.getParametroSqlite("ftp_password")[0][1];
			this.directory=(String)data.getParametroSqlite("ftp_directory")[0][1];
			this.file=(String)data.getParametroSqlite("ftp_file")[0][1];
			this.download=(String)data.getParametroSqlite("ftp_download")[0][1];
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	public boolean duploadFile(String directory,String filex,String destination,boolean binary){
		boolean ok=true;
		Date rev=null;
		try {
			//JakartaFtpWrapper
			ftp = new JakartaFtpWrapper();
			
			if (ftp.connectAndLogin(ftpserver, username, password)) {
				System.out.println("Connected to " + ftpserver);
				try {
					
					if (binary){
						System.out.println("Binary Mode");
						ftp.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
						ftp.setFileType(FTP.BINARY_FILE_TYPE);
						ftp.setPassiveMode(true);
					}else{
						System.out.println("ASCII Mode");
						ftp.setFileTransferMode(FTP.COMPRESSED_TRANSFER_MODE);
						ftp.setFileType(FTP.ASCII_FILE_TYPE);
						ftp.setPassiveMode(false);
					}
					
					boolean dir=ftp.changeWorkingDirectory(directory);
					
					
					System.out.println("ftp>"+directory+"? "+dir);
					
					Vector v=ftp.getFiles();
					for (int i=0;i<v.size();i++){
						FTPFile file=(FTPFile)v.get(i);
						System.out.println("?>"+file.getName());
						if (file.getName().compareTo(filex)==0){
							estado="Descargando "+filex;
							if (this.isLinux()){
								ok=uploadFromFTP(filex, destination+"/"+filex,file.getSize());
							}else{
								ok=uploadFromFTP(filex, destination+"\\"+filex,file.getSize());	
							}
							
							
						}
						
						
					}
				} catch (Exception ftpe) {
					ftpe.printStackTrace();
					ok=false;
				}
				/*finally {
					ftp.logout();
					ftp.disconnect();
				}*/
			} else {
				System.out.println("Unable to connect to" + ftpserver);
			}
			System.out.println("Finished");
		} catch(Exception e) {
			e.printStackTrace();
			ok=false;
		}
		done=true;
		return ok;
	}
	
	public boolean downloadFile(String directory,String filex,String destination,boolean binary){
		boolean ok=true;
		Date rev=null;
		try {
			//JakartaFtpWrapper
			ftp = new JakartaFtpWrapper();
			if (ftpserver!=null & username!=null & password!=null){
				if (ftp.connectAndLogin(ftpserver, username, password)) {
					System.out.println("Connected to " + ftpserver);
					try {
						
						binary=true;
						if (binary){
							System.out.println("Binary Mode");
							ftp.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
							ftp.setFileType(FTP.BINARY_FILE_TYPE);
							ftp.setPassiveMode(true);
						}else{
							System.out.println("ASCII Mode");
							ftp.setFileTransferMode(FTP.COMPRESSED_TRANSFER_MODE);
							ftp.setFileType(FTP.ASCII_FILE_TYPE);
							ftp.setPassiveMode(false);
						}
						
						boolean dir=ftp.changeWorkingDirectory(directory);
						
						
						System.out.println("ftp>"+directory+"? "+dir);
						
						
						System.out.println("ftp status>"+ftp.getStatus());
						
						Vector v=ftp.getFiles();
						System.out.println("ftp vector size>"+v.size());
						for (int i=0;i<v.size();i++){
							FTPFile file=(FTPFile)v.get(i);
							System.out.println("?>"+file.getName());
							if (file.getName().compareTo(filex)==0){
								estado="Descargando "+filex;
								String filename="";
								if (this.isLinux()){
									filename=destination+"/"+filex;
								}else{
									filename=destination+"\\"+filex;
								}
								ok=downloadFromFTP(filex, filename,file.getSize());	
								
							}
							
							
						}
					} catch (Exception ftpe) {
						ftpe.printStackTrace();
						ok=false;
					}
					/*finally {
						ftp.logout();
						ftp.disconnect();
					}*/
				} else {
					ok=false;
					System.out.println("Unable to connect to" + ftpserver);
				}
			}else{
				ok=false;
				System.out.println("Unable to connect to" + ftpserver);
			}
			
			System.out.println("Finished");
		} catch(Exception e) {
			e.printStackTrace();
			ok=false;
		}
		done=true;
		return ok;
	}
	
	public String getLastVersion(){
		String revision="";
		this.prepareDestination();
		String destination=this.getDestination();
		boolean ok=this.downloadFile("/beta", "version.xml", destination,false);
		if (ok){
			init();
		}
		return revision;
	}
	
	public void check(){
		Date rev=null;
		sources=new ArrayList<Element>();
		String destination=this.getDestination();
		this.prepareDestination();
		boolean ok=this.downloadFile("/beta", "version.xml", destination,false);
		if (ok){
			init();
		}
		done=true;
	}
	
	/*
	public void check(){
		Object[][] results=data.getLastUpdate();
		if (results!=null){
			if (results.length>0){
				String version=(String) results[0][0];
				String fecha=(String) results[0][1];
				String modificacion=(String) results[0][2];
				frame.get_txt_revision_disponible().setText(version);
				frame.getJTextArea().setText(modificacion);
				frame.get_txt_fecha().setText(fecha);
				done=true;
			}
		}
	}
	*/
	public void updateRevision(){
		String revision=frame.get_txt_revision_disponible().getText();
		data.setValue("version", revision);
	}
	
	public void setEstado(String estado){
		this.estado=estado;
	}
	
	
	public void createTimer(){
		crono=new Crono();
		crono.start();
		current=0;
		done=false;
		canceled=false;
		Timer=new Timer(300, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (done){
					endbar();
					Timer.stop();
				}else {
					updateBar();
				}
			}
		}); 
		
	}
    
	public void endbar(){
		done = true;
		frame.getJProgressBar().setIndeterminate(false);
		frame.getJProgressBar().setValue(lenght);
		frame.get_btn_descargar().setEnabled(true);
        frame.getJProgressBar().setString("");
	}
	
	public void updateBar(){
		frame.getJProgressBar().setMaximum(lenght);
		frame.getJProgressBar().setValue(current);
		frame.getJProgressBar().setString(estado+" "+current+"/"+lenght+" "+crono.elapsed());
		frame.getJProgressBar().setStringPainted(true);
	}
	
	

	public void doCancel(){
		if (preguntar("Confirma","Cancela Tarea en Ejecucion?")){
			canceled=true;
			endbar();
			try {
				ftp.abort();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			aviso("Proceso Cancelado");
			frame.get_btn_cancelar().setEnabled(false);	
		}
	}
	public void init_xml(){
		xml=new XML();
		String destination=this.getDestination();
		if (this.isLinux()){
			xml.setConfigFile(destination+"/version.xml");
		}else{
			xml.setConfigFile(destination+"\\version.xml");	
		}
		
		xml.readAll();
	}
	public void init(){
		this.init_xml();
		this.process_changes(false);
	}
	
	public void download_updates(){
		String destination=this.getDestination();
		xml=new XML();
		if (this.isLinux()){
			xml.setConfigFile(destination+"/version.xml");
		}else{
			xml.setConfigFile(destination+"\\version.xml");	
		}
		
		xml.readAll();
		this.process_changes(true);
	}
	
	public String[] getVersion(){
		String[] tmp=null;
		this.prepareDestination();
		String destination=this.getDestination();
		boolean ok=this.downloadFile("/beta", "version.xml", destination,false);
		if (ok){
			xml=new XML();
			if (this.isLinux()){
				xml.setConfigFile(destination+"/version.xml");
			}else{
				xml.setConfigFile(destination+"\\version.xml");	
			}
			
			xml.readAll();
			tmp=this.get_process_changes(false);
		}
		return tmp;
	}
	
	public boolean download_update(Element e){
		String source=e.getAtributo("source").getValor().toString();
		String dir=e.getAtributo("directory").getValor().toString();
		String destination=e.getAtributo("destination").getValor().toString();
		String folder=this.getDestination();
		System.out.println("source:"+source+" dir"+dir+" destination:"+destination);
		String filename=folder;
		File dire=new File(filename);
		if (!dire.exists()){
			try {
				dire.mkdir();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (this.isLinux()){
			System.out.println("LINUX");
			filename+="/"+destination;
			folder+="/"+destination;
		}else{
			System.out.println("WINDOWS OR ANYTHING ELSE!");
			filename+="\\"+destination;
			folder+="\\"+destination;
		}
		System.out.println("folder:"+folder);
		System.out.println("filename:"+filename);
		
		File filex=new File(filename);
		if (!filex.exists()){
			filex.mkdir();
		}
		System.out.println("update source: "+source+" destination:"+destination);
		return this.downloadFile(dir,source,folder,true);
	}
	
	public void process_update(Element e){
		String source=e.getAtributo("source").getValor().toString();
		System.out.println("update source: "+source);
	}
	
	
	
	public void process_version(Element e){
		String id=e.getAtributo("id").getValor().toString();
		String fecha=e.getAtributo("fecha").getValor().toString();
		frame.get_txt_revision_disponible().setText(id);
		frame.get_txt_fecha().setText(fecha);
		System.out.println("version "+id+" "+fecha);
		int i=0;
		while (i<e.getElements().size()){
			Element ex=e.getElements().get(i);
			if (ex.getId().compareTo("comentario")==0){
			
			}
			if (ex.getId().compareTo("update")==0){
				process_update(ex);
			}
			i++;
		}
	}
	
	public boolean download_version(Element e){
		
		String id=e.getAtributo("id").getValor().toString();
		String fecha=e.getAtributo("fecha").getValor().toString();
		System.out.println("version "+id+" "+fecha);
		int i=0;
		boolean ok=false;
		int errors=0;
		while (i<e.getElements().size()){
			Element ex=e.getElements().get(i);
			
			if (ex.getId().compareTo("update")==0){
				this.createTimer();
				ok=download_update(ex);
				if (!ok){
					errors++;
				}
			}
			i++;
		}
		ok=(errors<=0);
		
		return ok;
	}
	
	
	
	public void process(){
		Element root=xml.getRoot();
		
		int i=0;
		while (i<root.getElements().size()){
			Element e=root.getElements().get(i);
			if (e.getId().compareTo("version")==0){
				process_version(e);
			}
			i++;
		}
		
	}
	
	public void process_changes(boolean download){
		Element root=xml.getRoot();
		List<Element> versiones=new ArrayList<Element>();
		String NEW_LINE = System.getProperty("line.separator");
		String comentarios="";
		String installed_version=frame.get_txt_revision_actual().getText();
		int _installed_version=new Integer(installed_version.replace(".", ""));
		String newer_version=installed_version;
		String newer_fecha="";
		int i=0;
		while (i<root.getElements().size()){
			Element e=root.getElements().get(i);
			if (e.getId().compareTo("version")==0){
				
				String id=e.getAtributo("id").getValor().toString();
				String fecha=e.getAtributo("fecha").getValor().toString();
				String coment=e.getAtributo("comentario").getValor().toString();
				int _updated_version=new Integer(id.replace(".", ""));
				if (_updated_version>=_installed_version){
					versiones.add(e);
					newer_version=id;
					//comentarios+="< version:"+id+" fecha:"+fecha+" >";
					//comentarios+=NEW_LINE;
					comentarios+=coment;
					comentarios+=NEW_LINE;
					newer_fecha=fecha;
					Element version=e;
					int vi=0;
					while (vi<version.getElements().size()){
						Element ve=version.getElements().get(vi);
						
						if (ve.getId().compareTo("update")==0){
							this.addSource(ve);
							
						}
						vi++;
					}	
				}
				
			}
			i++;
		}
		if (comentarios.compareTo("")==0){
			comentarios="Usted tiene instalada la ultima version disponible";
		}
		if (versiones.size()>0){
			this.createTable(versiones);
		}else{
			this.frame.setJTable(null);
		}
		frame.get_txt_revision_disponible().setText(newer_version);
		
		frame.get_txt_fecha().setText(newer_fecha);
		if (download){
			this.download_sources();
		}
	}
	
	public List listRunningProcesses() {
		List<String> processes = new ArrayList<String>();
		try {
		String line;
		if (this.isLinux()){
			
		}else{
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
		}
		catch (Exception err) {
		err.printStackTrace();
		}
		return processes;
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
	
	private void create_table(Object[][] results) {
		CustomTable table = new CustomTable();
		Column col = new Column();
		
		
		col = new Column();
		col.setName("id");
		col.setWidth(40);
		col.setEditable(false);
		table.addColumn(col);
		
		col = new Column();
		col.setName("fecha");
		col.setWidth(120);
		col.setEditable(false);
		table.addColumn(col);
		
		col = new Column();
		col.setName("Descripcion");
		col.setWidth(360);
		col.setEditable(false);
		table.addColumn(col);
		
		table.setData(results);
		table.addKeyListener(this._constructor.getKeyListener());
		Font fuente=new Font("Arial", Font.PLAIN, 8);
		table.setHeaderFont(fuente);
		table.build();
		table.fillData();
		
		frame.setJTable(table.getTable());
	}
	public void createTable(List<Element> versiones){
		Object[][] results=new Object[versiones.size()][3];
		for (int i=0;i<versiones.size();i++){
			Element e=versiones.get(versiones.size()-1-i);
			String id=e.getAtributo("id").getValor().toString();
			String fecha=e.getAtributo("fecha").getValor().toString();
			String coment=e.getAtributo("comentario").getValor().toString();
			results[i][0]=id;
			results[i][1]=fecha;
			results[i][2]=coment;
		}
		final Object[][] _results=results;
		Runnable execute=new Runnable(){
			public void run(){
				create_table(_results);		
			}
		};
		this.invokeLater(execute);
	}
	
	public String[] get_process_changes(boolean download){
		Element root=xml.getRoot();
		String NEW_LINE = System.getProperty("line.separator");
		String comentarios="";
		
		
		int _installed_version=new Integer(version.replace(".", ""));
		String newer_version=version;
		String newer_fecha="";
		int i=0;
		if (root!=null){
			while (i<root.getElements().size()){
				Element e=root.getElements().get(i);
				if (e.getId().compareTo("version")==0){
					String id=e.getAtributo("id").getValor().toString();
					String fecha=e.getAtributo("fecha").getValor().toString();
					String coment=e.getAtributo("comentario").getValor().toString();
					int _updated_version=new Integer(id.replace(".", ""));
					if (_updated_version>_installed_version){
						newer_version=id;
						comentarios+="<version:"+id+" "+fecha+">";
						comentarios+=NEW_LINE;
						comentarios+=coment;
						comentarios+=NEW_LINE;
						newer_fecha=fecha;
						Element version=e;
						int vi=0;
						while (vi<version.getElements().size()){
							Element ve=version.getElements().get(vi);
							
							if (ve.getId().compareTo("update")==0){
								this.addSource(ve);
								
							}
							vi++;
						}	
					}
					
				}
				i++;
			}
			
		}
		String[] tmp=null;
		if (comentarios.compareTo("")==0){
			comentarios="Usted tiene instalada la ultima version disponible";
		}else{
			tmp=new String[]{newer_version,comentarios};	
		}
		
		return tmp;
	}
	public void download_sources(){
		int errors=0;
		prepareDestination();
		for (int i=0;i<sources.size();i++){
			this.createTimer();
			Element ex=sources.get(i);
			boolean ok=download_update(ex);
			if (!ok){
				errors++;
			}
		}
		if (errors<=0) {
			updateRevision();
			this.actualiza();
		}else{
			error("Error En la Descarga de la actualizacion. Intentelo nuevamente");
		}
	}
	
	public void addSource(Element e){
		boolean found=false;
		int i=0;
		String source=e.getAtributo("source").getValor();
		System.out.println("Agregando "+source);
		while (i<sources.size() & !found){
			Element x=sources.get(i);
			String xsource=x.getAtributo("source").getValor();
			found=source.compareTo(xsource)==0;
			if (!found){
				i++;	
			}
			
		}
		if (!found){
			this.sources.add(e);
			System.out.println("Agregando source:"+e.getAtributo("source").getValor()+" "+e.getAtributo("directory").getValor());
		}else{
			System.out.println("Quitar source:"+this.sources.get(i).getAtributo("source").getValor()+" "+this.sources.get(i).getAtributo("directory").getValor());
			this.sources.remove(i);
			System.out.println("Utilizar source:"+e.getAtributo("source").getValor()+" "+e.getAtributo("directory").getValor());
			this.sources.add(e);
		}
		
	}
	/*
	public void download(){
		Element root=xml.getRoot();
		
		int i=0;
		while (i<root.getElements().size()){
			Element e=root.getElements().get(i);
			if (e.getId().compareTo("version")==0){
				boolean ok=download_version(e);
				if (ok){
					this.actualiza();
				}else{
					error("Error En la Descarga de la actualizacion. Intentelo nuevamente");
				}
			}
			i++;
		}
		
	}*/
}
