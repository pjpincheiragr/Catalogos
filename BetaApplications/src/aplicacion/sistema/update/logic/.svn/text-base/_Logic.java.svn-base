package aplicacion.sistema.update.logic;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Vector;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import java.util.zip.ZipInputStream;
import java.util.zip.ZipEntry;

import javax.swing.tree.*;
import aplicacion.herramientas.java.launcher.logic.SwingWorker;
import aplicacion.herramientas.java.table.Column;
import aplicacion.herramientas.java.table.CustomTable;
import aplicacion.herramientas.java.xml.Atributo;
import aplicacion.herramientas.java.xml.Element;
import aplicacion.herramientas.java.xml.XML;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPFile;


import aplicacion.sistema.update.gui._Frame;
import aplicacion.sistema.update.logic._Data;
import aplicacion.sistema.update.constructor.*;
import aplicacion.sistema.update.logic.*;
import aplicacion.sistema.update.interfaces.*;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Data;
import aplicacion.modelo.logic.Logic;
import aplicacion.sistema.version.logic.JakartaFtpWrapper;
import aplicacion.herramientas.java.*;



public class _Logic extends Logic {
	private _Data data;
	private _Frame frame;
	private String destination="C:\\Beta\\";
    private JakartaFtpWrapper ftp=null;
	//variables de tareas swingwork
	private String estado="";
	private String version="1.";
	private int current;
	private int errors;
	private int lenght,max;
	private boolean debug=true,done,canceled,override;
	private Timer Timer;  //  @jve:decl-index=0:
	private Crono crono;
	private String tc="bupd";
	private File file;
	private BufferedWriter out =null;
	private Object[] test;
	private String ftpserver;
	private String username;
    private String password;
    private String download;
    private String directory="";
	private aplicacion.herramientas.java.visualselector.constructor._Constructor vSelector=null;
	private aplicacion.herramientas.java.buscadores.Proveedor bProveedor=null;
	private aplicacion.herramientas.java.visualizadores.Proveedor vProveedor=null;
	
	
	
	
	
	public void load_variables(){
		this.ftpserver=(String)data.getParametroSqlite("ftp")[0][1];
		this.username=(String)data.getParametroSqlite("ftp_user")[0][1];
		this.password=(String)data.getParametroSqlite("ftp_password")[0][1];
		this.directory="beta/versions/";
	}
	
	
	public boolean generar_xml(){
		boolean ok=false;
		frame.getJProgressBar().setIndeterminate(true);
		estado="GENERANDO XML";
	    XML xml = new XML();
        Atributo atributo=null;
        Element root=new Element("versiones");
        root.setRoot(true);
        
        Object[][] results=data.getUpdates();
        if (results!=null){
        	if (results.length>0){
        		for (int i=results.length-1;i>=0;i--){
                	String idversion=(String) results[i][0];
                	String fecha=(String) results[i][1];
                	String comentario=(String) results[i][2];
                	
                	Element version=new Element("version");
                    
                    atributo=new Atributo("id");
                    atributo.setValor(idversion);
                    version.addAtribute(atributo);
                    
                    atributo=new Atributo("fecha");
                    atributo.setValor(fecha);
                    version.addAtribute(atributo);
                    
                    atributo=new Atributo("comentario");
                    atributo.setValor(comentario);
                    version.addAtribute(atributo);
                    
                    Object[][] files=data.getUpdatesFiles(idversion);
                    if (files!=null){
                    	if (files.length>0){
                    		for (int j=0;j<files.length;j++){
                    			String folder=(String) files[j][0];
                            	String file=(String) files[j][1];
                        		Element update=new Element("update");
                                atributo=new Atributo("directory");
                                atributo.setValor("/beta/versions/"+idversion);
                                update.addAtribute(atributo);
                                
                                atributo=new Atributo("source");
                                atributo.setValor(file);
                                update.addAtribute(atributo);
                                
                                atributo=new Atributo("destination");
                                atributo.setValor(folder+"/");
                                update.addAtribute(atributo);
                                version.addElement(update);
                             	
                    		}
                    		   		
                    	}
                    }
                    root.addElement(version);
                    	
                }
                    	
            }	
        }
        
            
        xml.setRoot(root);
        ok=xml._guardar(destination+"/version.xml");
        ok=this.uploadFile("/beta/", destination+"version.xml", true);
        return ok;
	}
	
	public void setFrame(JFrame frame){
		this.frame=(_Frame) frame;
		super.setFrame(frame);
	}
	
	
	public void setData(Data _data){
		this.data=(_Data)_data;
		super.setData(_data);
	}
	
	

	
	 
  
   
   
   
   	//metodos basicos de tareas swing
	public void createTimer(){
		current=0;
		errors=0;
		canceled = false;
		done = false;
		crono=new Crono();
		crono.start();
		Timer=new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (done |canceled){
					endbar();
					Timer.stop();
					canceled=false;
					done=false;
					}else {
					updateBar();
				}
			}
		}); 
		
	}
	
	
	public void updateBar(){
		frame.getJProgressBar().setMaximum(lenght);
		frame.getJProgressBar().setValue(current);
		frame.getJProgressBar().setString(estado+" "+current+"/"+lenght+" "+crono.elapsed());
		frame.getJProgressBar().setStringPainted(true);
	}
	
	public void endbar(){
		estado="";
		frame.getJProgressBar().setString("");
		frame.getJProgressBar().setIndeterminate(false);
		frame.getJProgressBar().setValue(0);
	}
	
	public void clean(){
		frame.get_txt_comentario().setText("");
		frame.getJProgressBar().setString("");
		frame.get_txt_idcomprobante().setText("");
		frame.get_txt_idcomprobante().setEditable(true);
    	frame.get_txt_idcomprobante().requestFocusInWindow();
	}
	
	public void doCancel(){
		canceled=true;
		endbar();
		aviso("Proceso de Actualizacion Cancelado");
		frame.get_btn_cancelar().setEnabled(false);
	}
	
	
		
	
	 
	public void guardar(){
		String file_path="";//frame.get_txt_archivo().getText();
		if (file_path.compareTo("")!=0){
			if (!file_path.endsWith(".txt")){
				file_path+=".txt";
			}
			boolean ok=this.create_write_file(file_path);
			if (ok){
				crono=new Crono();
		        crono.start();
			
			}else{
				error("Error al crear Archivo");
			}
			
		}else{
			error("Debe Seleccionar el Destino ");
		}
	}
	

	   
	    
		
		
		
		 public boolean addLine(String line){
			 boolean ok=true;
			 try {
				out.write(line);
				 out.newLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				ok=false;
			}
			return ok;
		 }
		public boolean close_write_file(){
			 boolean ok=true;
			 try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				ok=false;
			}
			return ok;
		}
		
		
		private List<CheckBoxNode> addOptions(String directory){
			List<CheckBoxNode> Options=new ArrayList<CheckBoxNode>();
			File dir = new File(directory);
	    	if (dir.exists()){
	    			String[] children = dir.list();
	            	 if (children == null) { 
	            	} else { 
	            	for (int i=0; i<children.length; i++) { 
	            	String filename = children[i];
	            	CheckBoxNode node=new CheckBoxNode(filename, false);
	            	Options.add(node);
	            	}	
	            	}
	    			
	    	}
	    	return Options;
		}
		
		
		public void buildTree(){
			String directory=this.destination;
				List<CheckBoxNode> libOptions=this.addOptions(directory+"lib");
				List<CheckBoxNode> reportOptions=this.addOptions(directory+"reports");
				
			 CheckBoxNode _libOptions[] = new CheckBoxNode[libOptions.size()];
			 	for (int i=0;i<libOptions.size();i++){
			 		_libOptions[i]=libOptions.get(i);
			 	}
			 CheckBoxNode _reportOptions[] = new CheckBoxNode[reportOptions.size()];
			 	for (int i=0;i<reportOptions.size();i++){
			 		_reportOptions[i]=reportOptions.get(i);
			 	}
				    Vector libVector = new NamedVector("lib",
				        _libOptions);
				    Vector reportVector = new NamedVector("reports", _reportOptions);
				    Object rootNodes[] = { libVector, reportVector };
				    Vector rootVector = new NamedVector("Root", rootNodes);
				    JTree tree = new JTree(rootVector);

				    CheckBoxNodeRenderer renderer = new CheckBoxNodeRenderer();
				    tree.setCellRenderer(renderer);

				    tree.setCellEditor(new CheckBoxNodeEditor(tree));
				    tree.setEditable(true);

	        frame.setJTree(tree);
		}
		
	 public boolean create_write_file(String file_path){
		 boolean ok=true;
		 try {
 			///aca va el lector de archivo log de la tarea
 			
 			file=new File(file_path);
 			file = file.getAbsoluteFile();
 			if (file.exists())file.delete();
 			file.createNewFile();
 			out = new BufferedWriter(new FileWriter(file));
 			
 		}catch (Exception e){
 			ok=false;
 		}
 		return ok;
	 }
	
	
	
	
	
	

	
	
	
	
	
	
	public void focus(){
		frame.requestFocusInWindow();
		frame.get_txt_idcomprobante().requestFocusInWindow();
		
	}
	
	   public void nuevo(){
	    	this.clean();
	    	String idcomprobante=data.getProximoPGCorrecto(tc);
	    	
	    	frame.get_txt_idcomprobante().setText(version+idcomprobante);
	    	this.evaluar_idcomprobante(frame.get_txt_idcomprobante());
	    	Runnable _execute=new Runnable(){
	    		public void run(){
	    			buildTree();		
	    		}
	    	};
	    	this.invokeLater(_execute);
	    }
	public void evaluar_idcomprobante(JTextField tx){
		String _nuevo=data.getProximoPGCorrecto(tc);
		String idcomprobante=tx.getText();
		if (_nuevo.compareTo(idcomprobante)==0){
			frame.get_txt_idcomprobante().setEditable(false);
			
			
			
			
		}else{
			//aviso("debe cargarse uno existente o error");
			tx.requestFocusInWindow();
		}
		
	}
	
	
	
	public void Exportar(){
		if (this.confirmar("Confirme para exportar", 3)){
			this.goExportar();
		}
	}
	public void goExportar() {
    	this.createTimer();
    	SwingWorker worker=null;
    		worker = new SwingWorker() {
    			@Override
    			public Object construct() {
    				return new _taskInfo();
    			}
    		};
   		if (Timer!=null) {
    			Timer.start();
   		}
   		worker.start();
    }
    
	 class _taskInfo {
			_taskInfo() {
				_taskworkInfo();
				}
			}
	 
	public boolean  _simulacion(String folder,DefaultMutableTreeNode nodo){
			boolean ok=true;
			Object userObject = nodo.getUserObject();
			 String destination_folder=this.directory+frame.get_txt_idcomprobante().getText();
			 
			 if (nodo.isLeaf() && userObject instanceof CheckBoxNode){
	        	 CheckBoxNode cknode=(CheckBoxNode) userObject;
	        	 String localfile=this.destination+folder+"\\"+cknode.getText();
	        	 if (cknode.isSelected()){
	        		 estado="Eliminado Versiones Anteriores "+localfile;
	        		 String[] olds=data.getOldFilesPath(destination_folder, cknode.getText());
	        		 if (olds!=null){
	        			 for (int i=0;i<olds.length;i++){
	        				 System.out.println("Eliminar "+this.directory+olds[i]);
	        				 this.deleteFile(this.directory+"/"+olds[i], cknode.getText(), true);
	        			 }
	        		 }
	        		 estado="Subiendo "+localfile;
	        		 System.out.println("UPLOAD FILE: "+localfile+" "+">"+destination_folder+"/");
	        		ok= this.uploadFile(destination_folder, localfile,true);
	        	 }
	         }	
			 return ok;
	 }
	 
	 
	 public boolean _registrar_version(String version){
		 boolean ok=false;
		 
		 DefaultMutableTreeNode nodo=(DefaultMutableTreeNode) frame.getJTree().getModel().getRoot();
		 
         int folders=nodo.getChildCount();
         
         String comentario=frame.get_txt_comentario().getText();
         List<String> instrucciones=new ArrayList<String>();
         
		 String instruccion=data.insert_update(version, comentario);
		 instrucciones.add(instruccion);
         for (int i=0;i<folders;i++){
        	 DefaultMutableTreeNode folder=(DefaultMutableTreeNode)nodo.getChildAt(i);
        	 Object userObject = folder.getUserObject();
        	 
        	 if (userObject instanceof NamedVector){
        		 NamedVector cknode=(NamedVector) userObject;
        		 
        		 for (int j=0;j<folder.getChildCount();j++){
        			 DefaultMutableTreeNode file=(DefaultMutableTreeNode)folder.getChildAt(j);
        			 instruccion=this._registrar_version_file(version,cknode.getTitle(),file);
        			 if (instruccion.length()>0){
        				 instrucciones.add(instruccion);	 
        			 }
        			 
        		 }
        		 
        	 }
        	 
        	 	 
         }
         
         data.beginTransaction();
         data.clearBatch();
       	 for (int i=0;i<instrucciones.size();i++){
       		 data.addBatch(instrucciones.get(i));
       	 }
       	 ok=!data.executeBatch();
       	 if (ok){
       		 ok=!data.updateTC(tc);
     	 }
       	 if (ok){
       		 data.commitTransaction();
       	 }else{
       		 data.rollbackTransaction();
       	 }
		 return ok;
	 }
	 public void deleteOldRevisions(String id){
		 
	 }
	 public String _registrar_version_file(String version,String folder,DefaultMutableTreeNode nodo){
		 Object userObject = nodo.getUserObject();
		 String q="";
		 
		 if (nodo.isLeaf() && userObject instanceof CheckBoxNode){
        	 CheckBoxNode cknode=(CheckBoxNode) userObject;
        	 if (cknode.isSelected()){
        		 q=data.insert_update_file(version, folder, cknode.getText());
        	 }
         }
		 return q;
	 }
	 
	 
	 public void simulacion(){
		 String version=frame.get_txt_idcomprobante().getText();
		 boolean ok=false;
		 ok=frame.get_txt_comentario().getText().compareTo("")!=0;
		 if (ok){
			 ok=this._registrar_version(version);
			 if (ok){
				 System.out.println("simulacion()");
				 DefaultMutableTreeNode nodo=(DefaultMutableTreeNode) frame.getJTree().getModel().getRoot();
				 
		         int folders=nodo.getChildCount();
		         System.out.println("folders?"+folders);
		         boolean upload=true;
        		 for (int i=0;i<folders;i++){
		        	 DefaultMutableTreeNode folder=(DefaultMutableTreeNode)nodo.getChildAt(i);
		        	 Object userObject = folder.getUserObject();
		        	 System.out.println("folders "+i+"> "+userObject.getClass()+" >"+userObject+" ");
		        	 if (userObject instanceof NamedVector){
		        		 NamedVector cknode=(NamedVector) userObject;
		        		 System.out.println("FOLDER:"+cknode.getTitle());
		        		 int j=0;
		        		 while(j<folder.getChildCount() & upload){
		        			 DefaultMutableTreeNode file=(DefaultMutableTreeNode)folder.getChildAt(j);
		        			 upload=this._simulacion(cknode.getTitle(),file);
		        			 j++;
		        		 }
		        		 
		        	 }
		         }
        		 if (upload){
        			 data.marcar_operacion_ftp_exitosa(version);
        			 
        			 ok=this.generar_xml();
        			 
        		 }else{
        			 
        		 }
			}else{
					
			}
		 }
		 
		 
        	 
			done=true;
		if (ok){
			aviso("Export Ok");
       	 }else{
       		error("");
       	 }
	 }
	 
	 public void _taskworkInfo(){
		 
		 		simulacion();
		 	
		
	    	
	    }
	 
	    
    public void Info(){
    	aviso("Este proceso demorara unos minutos. Si se extiende por mas de 2 minutos, probablemente tenga un  error de configuracion");
		done=false;
		canceled=false;
		this.createTimer();
		goExportar();
	
    }
    
    public boolean uploadFile(String directory,String localfile,boolean binary){
		boolean ok=true;
		Date rev=null;
		try {
			//JakartaFtpWrapper
			ftp = new JakartaFtpWrapper();
			frame.getJProgressBar().setIndeterminate(false);
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
					
					if (!dir){
						ftp.mkd(directory);
					}
					dir=ftp.changeWorkingDirectory(directory);
					
					System.out.println("ftp>"+directory+"? "+dir);
					
					File _localfile=new File(localfile);
					if (_localfile.exists()){
						System.out.println("?>"+_localfile.getName()+" size="+_localfile.length());
						estado="Subiendo "+localfile+" ";
						
						ok=uploadToFTP(_localfile.getName(), localfile,_localfile.length());
							
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
		
		return ok;
	}
    
    public boolean deleteFile(String directory,String localfile,boolean binary){
		boolean ok=true;
		Date rev=null;
		try {
			//JakartaFtpWrapper
			ftp = new JakartaFtpWrapper();
			frame.getJProgressBar().setIndeterminate(false);
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
					if (dir){
						
						ftp.deleteFile(localfile);
					}
					
					//
					
						
				} catch (Exception ftpe) {
					ftpe.printStackTrace();
					ok=false;
				}
				finally {
					ftp.logout();
					ftp.disconnect();
				}
			} else {
				System.out.println("Unable to connect to" + ftpserver);
			}
			System.out.println("Finished");
		} catch(Exception e) {
			e.printStackTrace();
			ok=false;
		}
		
		return ok;
	}
    public boolean uploadToFTP(String serverfile,String localfile,long filesize){
    	System.out.println(serverfile+" size:"+filesize+" "+localfile);
    	
    	boolean ok=true;
    	this.lenght=100;
        try {
            //InputStream stO =  new BufferedInputStream(
            //        ftp.retrieveFileStream(serverfile),
            //        ftp.getBufferSize());
            
            
            //OutputStream stD = new FileOutputStream(localfile);
            
        	InputStream stO =new FileInputStream(localfile);
        	ftp.remoteStoreUnique(serverfile);
        	ftp.storeFile(serverfile, stO);
        	OutputStream stD = new BufferedOutputStream(
        	        ftp.appendFileStream(serverfile),ftp.getBufferSize());
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
                        		System.out.println("bytetransfered: "+bytesTransferred+"|"+totalBytesTransferred+" "+streamSize);
                               current = (int)Math.ceil(((double)totalBytesTransferred/(double)streamSize) * 100);
                               System.out.println("current: "+current);
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
            System.out.println(serverfile+" uploaded:"+test);    
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
        
        
        return ok;
    }

    
    
    
    public void decompress(String zipname){
    	
        try {
            FileInputStream fis = new FileInputStream(zipname);
            ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
            ZipEntry entry;

            //
            // Read each entry from the ZipInputStream until no more entry found
            // indicated by a null return value of the getNextEntry() method.
            //
            while ((entry = zis.getNextEntry()) != null) {
                System.out.println("Unzipping: " + entry.getName());

                int size;
                byte[] buffer = new byte[2048];

                FileOutputStream fos = new FileOutputStream(entry.getName());
                BufferedOutputStream bos = new BufferedOutputStream(fos, buffer.length);

                while ((size = zis.read(buffer, 0, buffer.length)) != -1) {
                    bos.write(buffer, 0, size);
                }
                bos.flush();
                bos.close();
            }

            zis.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String rmPath(String fName) {
        int pos = fName.lastIndexOf(File.separatorChar);
        if (pos > -1)
          fName = fName.substring(pos + 1);
        return fName;
      }
    
    public boolean compressFile(String source,String target) {
    	boolean ok=true;
    	try {
    		File _target=new File(target);
			ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(target));
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
			ok=false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ok=false;
		}
		return ok;
    }
}

