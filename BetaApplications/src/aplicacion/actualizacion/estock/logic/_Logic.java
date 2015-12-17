package aplicacion.actualizacion.estock.logic;
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
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPFile;


import aplicacion.actualizacion.estock.gui._Frame;
import aplicacion.actualizacion.estock.logic._Data;
import aplicacion.actualizacion.estock.interfaces.*;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Data;
import aplicacion.modelo.logic.Logic;
import aplicacion.sistema.version.logic.JakartaFtpWrapper;
import aplicacion.herramientas.java.*;


/**
 * @author Agustin Wisky
 * @company Wismi S.A.
 * @since 10-10-2009
 */
public class _Logic extends Logic {
	private _Data data;
	private _Frame frame;
	private String destination="C:\\windows\\temp\\_actualizacion";
    private JakartaFtpWrapper ftp=null;
	//variables de tareas swingwork
	private String estado="";
	private int current;
	private int errors;
	private int lenght,max;
	private boolean debug=true,done,canceled,override;
	private Timer Timer;  //  @jve:decl-index=0:
	private Crono crono;
	private String tc="supd";
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
	
	public void nueva_politica(){
		aplicacion.inventario.politica.constructor._Constructor
		CC=new aplicacion.inventario.politica.constructor._Constructor();
		CC.setParameter(_parametros.connector, getConstructor().getConnectionHandler());
		CC.build(this.getConstructor());
		CC.init();
	}
	
	public void editar_politica(){
	
		aplicacion.inventario.politica.constructor._Constructor
		CC=new aplicacion.inventario.politica.constructor._Constructor();
		CC.setParameter(_parametros.connector, getConstructor().getConnectionHandler());
		String idpolitica=frame.get_txt_politica().getText();
		CC.setParameter(aplicacion.inventario.politica.interfaces._parametros.idpolitica, idpolitica);
		CC.build(this.getConstructor());
		CC.init();
	}
	
	public void load_variables(){
		this.ftpserver=(String)data.getParametroSqlite("ftp")[0][1];
		this.username=(String)data.getParametroSqlite("ftp_user")[0][1];
		this.password=(String)data.getParametroSqlite("ftp_password")[0][1];
		this.directory="actualizacion";
	}
	
	
	public void generar_xml(){
		frame.getJProgressBar().setIndeterminate(true);
		estado="GENERANDO XML";
	    XML xml = new XML();
        Atributo atributo=null;
        Element root=new Element("versiones");
        root.setRoot(true);
        
        Object[][] results=data.getUpdates();
        if (results!=null){
        	if (results.length>0){
        		for (int i=0;i<results.length;i++){
                	String idactualizacion=(String) results[i][0];
                	String fecha=(String) results[i][1];
                	String idproveedor=(String) results[i][2];
                	String nombre=(String) results[i][3];
                	Element version=new Element("version");
                    
                    atributo=new Atributo("id");
                    atributo.setValor(idactualizacion);
                    version.addAtribute(atributo);
                    
                    atributo=new Atributo("fecha");
                    atributo.setValor(fecha);
                    version.addAtribute(atributo);
                    
                    atributo=new Atributo("idproveedor");
                    atributo.setValor(idproveedor);
                    version.addAtribute(atributo);
                    
                    atributo=new Atributo("nombre");
                    atributo.setValor(nombre);
                    version.addAtribute(atributo);
                    
                    Element update=new Element("update");
                    atributo=new Atributo("directory");
                    atributo.setValor("/beta/actualizacion");
                    update.addAtribute(atributo);
                    
                    atributo=new Atributo("source");
                    atributo.setValor(idactualizacion+".zip");
                    update.addAtribute(atributo);
                    
                    atributo=new Atributo("destination");
                    atributo.setValor("actualizacion\\");
                    update.addAtribute(atributo);
                    version.addElement(update);
                    root.addElement(version);
                    	
                }
                    	
            }	
        }
        
            
        xml.setRoot(root);
        xml._guardar(destination+"/actualizacion.xml");
		
		
	}
	
	public void setFrame(JFrame frame){
		this.frame=(_Frame) frame;
		super.setFrame(frame);
	}
	
	
	public void setData(Data _data){
		this.data=(_Data)_data;
		super.setData(_data);
	}
	
	

	
	public void BuscarLinea(){
		this.BuscarLinea(frame.get_txt_linea());
	}
	private aplicacion.herramientas.java.buscadores.Linea bLinea;
	public void BuscarLinea(JTextField tx){
		
		if (bLinea==null){
			bLinea=new aplicacion.herramientas.java.buscadores.Linea(this.getConstructor());
		}
		
		bLinea.setIdproveedor(frame.get_txt_idproveedor());
		bLinea.BuscarLineaProveedor(tx);	
	
	}
	private aplicacion.herramientas.java.visualizadores.Linea vLinea=null;
	public void buscarLinea(JTextField tx) {
		if (vLinea!=null){
			vLinea.close();
		}
		vLinea=new aplicacion.herramientas.java.visualizadores.Linea(this.getConstructor());
		vLinea.setIdproveedor(frame.get_txt_idproveedor().getText());
		int n = vLinea.buscarLineaProveedor(tx);
		if (n == 0) {
				aviso("no hay Lineas con ese codigo");
		}
	}
	public void evaluar_linea(JTextField tx){
		String linea=tx.getText();
		String idproveedor=frame.get_txt_idproveedor().getText();
		if (linea.compareTo("")!=0){
			if (data.check_linea(idproveedor, linea)){
				frame.get_txt_archivo().requestFocusInWindow();
			}else{
				this.buscarLinea(tx);
			}
		}else{
			this.buscarLinea(tx);
		}
	}
	public void Actualizar(String idproveedor,String pol){
    	estado="Aplicando Precios Nuevos";
    	frame.getJProgressBar().setIndeterminate(true);
    	String iduser=this.getConstructor().getIduser();
    	
    	String idcomprobante=frame.get_txt_idcomprobante().getText();
    	String q="alter table v_ma_articulos disable trigger all ";
    	System.out.println(q);
    	data.beginTransaction();
    	data.clearBatch();
    	data.addBatch(q);
    	q=data.getUpdatePrices(idproveedor, pol, tc, idcomprobante, iduser);
    	System.out.println(q);
    	data.addBatch(q);
    	q="alter table v_ma_articulos enable trigger all ";
    	System.out.println(q);
    	data.addBatch(q);
    	q+="update b_ta_cpte set x_ultimo_nro = x_ultimo_nro + 1 where codigo like '"+tc+"'";
    	System.out.println(q);
    	data.addBatch(q);
    	boolean error=data.executeBatch();
    	if (!error){
    		data.commitTransaction();
    	}else{
    		data.rollbackTransaction();
    	}
    	done=true;
    	aviso("Actualizacion terminada");
    	this.clean();
    	
    }
   
  
   
   
   
   public void evaluar_politica(JTextField tx){
		String politica=tx.getText();
		
		if (politica.compareTo("")!=0){
			if (data.check_politics_existence(politica)){
				this.cargar_politica(politica);
				frame.get_txt_linea().requestFocusInWindow();
			}else{
				this.buscarPolitica(tx);
			}
		}else{
			this.buscarPolitica(tx);
		}
	}
	public void cargarProveedor(String idproveedor){
		
		
			Object[][] results=data.getProveedor(idproveedor);
				if (results!=null){
					if (results.length>0){
						String desc=results[0][1].toString();
						String pol=results[0][2].toString();
						String poldesc=results[0][3].toString();
						String linea=results[0][6].toString();
						frame.get_txt_proveedor_descripcion().setText(desc);
						frame.get_txt_politica().setText(pol);
						frame.get_txt_descripcion_politica().setText(poldesc);
						frame.get_txt_linea().setText(linea);
						frame.get_txt_politica().requestFocusInWindow();
					}
				}
		
	}
	
	
	private aplicacion.herramientas.java.evaluadores.Proveedor proveedor=null;
	public void initialize_proveedor(){
		proveedor=new aplicacion.herramientas.java.evaluadores.Proveedor(){
			public void cargar(String codigo){
				cargarProveedor(codigo);
			}
		};
		proveedor.setConstructor(this.getConstructor());
	}
	public void BuscarProveedor(JTextField tx){
		proveedor.Buscar(tx);
	}
	public void buscarProveedor(JTextField tx){
		proveedor.buscar(tx);
	}
	public void BuscarProveedor(){
		proveedor.Buscar(frame.get_txt_idproveedor());
	}
	public void evaluarProveedor(JTextField tx){
		proveedor.evaluate(tx);
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
		frame.get_txt_archivo().setText("");
		frame.get_txt_descripcion_politica().setText("");
		frame.get_txt_idproveedor().setText("");
		frame.get_txt_linea().setText("");
		frame.get_txt_politica().setText("");
		frame.get_txt_proveedor_descripcion().setText("");
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
	
	
	public void BuscarPolitica(){
		this.BuscarPolitica(frame.get_txt_politica());
	}

	
	private aplicacion.herramientas.java.buscadores.Politica bPolitica=null;
	public void BuscarPolitica(JTextField ext) {
		 if (bPolitica==null){
			 bPolitica=new aplicacion.herramientas.java.buscadores.Politica(this.getConstructor());
		 }
	 
	 bPolitica.setDescripcion(frame.get_txt_descripcion_politica());
	 bPolitica.Buscar(ext);
	}
	
	private aplicacion.herramientas.java.visualizadores.Politica vPolitica=null;
	public void buscarPolitica(JTextField tx) {
		if (vPolitica!=null){
			vPolitica.close();
		}
		vPolitica=new aplicacion.herramientas.java.visualizadores.Politica(this.getConstructor());
		int n = vPolitica.Buscar(tx);
		if (n == 0) {
				aviso("no hay Politicas con ese codigo");
		}
	}

	
	
	
	 
	public void guardar(){
		String file_path=frame.get_txt_archivo().getText();
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
	
	public void buscar_archivo(){
		JFileChooser JF = new JFileChooser();
		int rx=JF.showOpenDialog(frame);
		if (rx == JFileChooser.APPROVE_OPTION) {
            File file = JF.getSelectedFile();
            frame.get_txt_archivo().setText(file.getAbsolutePath());
    	}
	}
	
	
	
	

	
	
	
	
	public void cargar_politica(String idpolitica){
		Object[][] results=data.getPolitica(idpolitica);
		if (results!=null){
			if (results.length>0){
				String descripcion="";
				descripcion=(String) results[0][1];
				frame.get_txt_descripcion_politica().setText(descripcion);
			}
		}
	}
	
	
	public void focus(){
		frame.requestFocusInWindow();
		frame.get_txt_idproveedor().requestFocus();
		frame.get_txt_idproveedor().requestFocusInWindow();
	}
	
	   public void nuevo(){
	    	this.clean();
	    	String idcomprobante=data.getProximoPGCorrecto(tc);
	    	frame.get_txt_idcomprobante().setText(idcomprobante);
	    	this.evaluar_idcomprobante(frame.get_txt_idcomprobante());
	    }
	public void evaluar_idcomprobante(JTextField tx){
		String _nuevo=data.getProximoPGCorrecto(tc);
		String idcomprobante=tx.getText();
		if (_nuevo.compareTo(idcomprobante)==0){
			frame.get_txt_idcomprobante().setEditable(false);
			this.setFileName();
			
			frame.get_txt_idproveedor().requestFocusInWindow();
			
		}else{
			aviso("debe cargarse uno existente o error");
			tx.requestFocusInWindow();
		}
		
	}
	
	public void setFileName(){
		String id=frame.get_txt_idcomprobante().getText();
		String filename=destination+"\\"+id+".txt";
		frame.get_txt_archivo().setText(filename);
		frame.get_txt_idproveedor().requestFocusInWindow();
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
	 
	 
	 public void _taskworkInfo(){
		 String idactualizacion=frame.get_txt_idcomprobante().getText();
	    	frame.getJProgressBar().setIndeterminate(true);
	    	
	    	estado="Cargando Informacion desde Base De Datos ";
	    	String idproveedor=frame.get_txt_idproveedor().getText();
	    	String linea=frame.get_txt_linea().getText();
	    	final Object[][] results = 
				data.getStock();
	    	String file_path=frame.get_txt_archivo().getText();
	    	boolean ok=false;
			if (file_path.compareTo("")!=0){
				if (!file_path.endsWith(".txt")){
					file_path+=".txt";
				}
				ok=this.create_write_file(file_path);
			}
			if (ok){
				if (results!=null){
					if (results.length>0){
						for (int row=0;row<results.length;row++){
							boolean b=true;
							String line="";
								for (int col=0;col<results[0].length;col++){
									if (line.length()>0){
										line+="	";
									}
									line+=results[row][col].toString();
								}
								this.addLine(line);					
								current++;		
						}
						this.close_write_file();
						String target=file.getPath();
						target=target.replaceAll(".txt", ".zip");
						ok=this.compressFile(file.getPath(), target);
						if (ok){
							
							file.delete();
							frame.getJProgressBar().setIndeterminate(false);
							
							ok=this.uploadFile("/beta", target, "stock/", true);
							if (ok){
								data.updateTC(tc);
								ok=data.InsertFTP(idactualizacion, idproveedor);
								if (ok){
									this.generar_xml();
									frame.getJProgressBar().setIndeterminate(false);
									current=0;
									ok=this.uploadFile("beta/", destination+"/actualizacion.xml","", true);
									if (ok){
										aviso("Se Exporto Correctamente");	
										this.clean();
										this.nuevo();	
									}
									
									
								}
								
								
							}
							
						}
						
					}
						
				}	
			}
			
			
			
			
			done=true;
		
	    	
	    }
	 
	    
    public void Info(){
    	aviso("Este proceso demorara unos minutos. Si se extiende por mas de 2 minutos, probablemente tenga un  error de configuracion");
		done=false;
		canceled=false;
		this.createTimer();
		goExportar();
	
    }
    
    public boolean uploadFile(String directory,String localfile,String destination,boolean binary){
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
					
					File _localfile=new File(localfile);
					if (_localfile.exists()){
						System.out.println("?>"+_localfile.getName()+" size="+_localfile.length());
						estado="Subiendo "+localfile+" ";
						ok=uploadToFTP(destination+""+_localfile.getName(), localfile,_localfile.length());
							
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

