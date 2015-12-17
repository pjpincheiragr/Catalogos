package aplicacion.actualizacion.global.logic;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.Timer;

import org.apache.batik.dom.svg.IdContainer;
import org.apache.commons.net.ftp.FTP;
import org.asteriskjava.fastagi.command.SetExtensionCommand;

import aplicacion.herramientas.java.Convertidor;
import aplicacion.herramientas.java.launcher.logic.SwingWorker;
import aplicacion.herramientas.java.sortableselector.logic.Filtro;
import aplicacion.herramientas.java.sortableselector.logic.columna;
import aplicacion.herramientas.java.table.CheckBoxCellEditor;
import aplicacion.herramientas.java.table.Column;
import aplicacion.herramientas.java.table.CustomTable;
import aplicacion.herramientas.java.xml.Atributo;
import aplicacion.herramientas.java.xml.Element;
import aplicacion.herramientas.java.xml.XML;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Logic;
import aplicacion.modelo.logic.Data;
import aplicacion.sistema.version.logic.JakartaFtpWrapper;
import aplicacion.actualizacion.global.interfaces._Interface;
import aplicacion.herramientas.java.*;
import aplicacion.actualizacion.global.logic.*;
import aplicacion.actualizacion.global.gui.*;

public class _Logic extends Logic{
	private Object[][] archivo;
	String estado="";
	private aplicacion.herramientas.java.buscadores.Proveedor bProveedor=null;
	private aplicacion.herramientas.java.visualizadores.Proveedor vProveedor=null;
	private String tc="odbc";
	public int errors;
	public int errors2;
	private int current;
	private int current2;
	private int length;
	private boolean debug,done,canceled,override;
	private Timer Timer2;  //  @jve:decl-index=0:
	private String destination;
	private String estado2="";
	private Crono crono;
	private Crono crono2;
	private Timer Timer;  //  @jve:decl-index=0:
	private File file;
	private String username;
    private String password;
	private String ftpserver;
    private String directory="";

	
	private BufferedWriter out =null;
    private JakartaFtpWrapper ftp=null;

	int length2=0;
	private _Data data=null;
	private _Frame frame=null;
	   
	   private aplicacion.herramientas.java.visualselector.constructor._Constructor vSelector=null;
	   
	   public void setData(Data data){
		   this.data=(_Data) data;
		   super.setData(data);
	   }
	   
	   public void setFrame(JFrame frame){
		   this.frame=(_Frame) frame;
		   super.setFrame(frame);
	   }
	   class _taskInfo {
			_taskInfo() {
				_taskworkInfo();
				}
			}
	   class _taskUpdate {
			_taskUpdate() {
				update();
				}
			}
	   
		public void load_variables(){
			this.ftpserver=(String)data.getParametroSqlite("ftp")[0][1];
			this.username=(String)data.getParametroSqlite("ftp_user")[0][1];
			this.password=(String)data.getParametroSqlite("ftp_password")[0][1];
			this.directory="actualizacion";
		}
		
	   public boolean _taskworkUpdate(String idcomprobante,String idproveedor,String linea,String pol,Object[][] results,boolean archivo){
			//System.out.println("Actualizando UPDW "+idcomprobante);
		   	boolean ok=true;
		   	crono2=new Crono();
		   	crono2.start();
		   	this.length2=results.length;
			
		    	int u=0;
		    	Convertidor Cv=new Convertidor();
		    	//Object[] test=PF.getSettings();
		    	Object[] test=new Integer[]{0,1,3,2};
	    		int i_art=-1;
	    		int i_pre=-1;
	    		int i_des=-1;
	    		int i_lin=-1;
	    		try {
	    			i_art=(Integer)test[0];	
	    		}catch(Exception e){
	    			System.out.println(e.getMessage());
	    		}
	    		try {
	    			i_des=(Integer)test[1];	
	    		}catch(Exception e){
	    			System.out.println(e.getMessage());
	    		} 
	    		if (test[2].getClass()==Integer.class){
	    			i_pre=(Integer)test[2];
	    		}
	    		if (test[3].getClass()==Integer.class){
	    			i_lin=(Integer)test[3];
	    			
	    		}
				String filter=linea;
				int lon;
				int qtys=0;
				if (archivo){
					
		    		if (filter.compareTo("")!=0){
		    			//cambiar 100 por results.length 
		    			lon=results.length;
		    			while (u<lon & !canceled){
				    		current2=u;
				    		linea=(String)results[u][i_lin];		
				    		
				    		if(linea!=null){
				    			if (linea.compareTo(filter)==0){
					    			qtys++;
					    		}
				    		}else{
				    			u=lon;
				    			this.error("linea"+u+" vacia dentro de la tabla, revise");
				    		}
				    			u++;
			    		}
		    			this.length2=qtys;
		    		}else{
		    			lon=results.length;
		    			while (u<lon & !canceled & linea.compareTo("")==0){
				    		current2=u;
				    		linea=(String)results[u][i_lin];		
				    		u++;
			    		}
		    			filter=linea;
		    		}
		    		

				}
				
	    		
	    		u=0;
	    		current2=0;
// poner en el while!!! u<results.length;
	    		lon=results.length;
	        	while (u<lon & !canceled){
		    		
		    		String codigo=(String)results[u][i_art];
		    		
		        	codigo=Cv.LimpiarString(codigo, " ");
		        	String descripcion="";
		        	try {
	    				descripcion=(String)results[u][i_des];		
	    			}catch(Exception e){
	    				System.err.println(e.getMessage());
	    			}
		        	//System.out.println("Do Update "+u);
		    		try {
		    				linea=(String)results[u][i_lin];		
		    		}catch(Exception e){
		    			System.err.println(e.getMessage());
		    		}
		    				    		
		    		linea=Cv.remove_last_spaces(linea);
		    		estado2=codigo+" "+linea+" ";
		    		String pre=(String)results[u][i_pre];
		    		//pre=Cv.normalize(pre);
		    		
		    		if (pre.contains(".")){
		    			pre=pre.replace(",", "");	
		    		}else {
		    			pre=pre.replace(",", ".");
		    			if (!pre.contains(".")){
		    				pre+=".0";
		    			}
		    		}
		    		
		    		double precio=0;
		    		try {
						precio=new Double(pre);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					boolean doit=true;
					if (archivo){
						if (filter.compareTo("")==0){
//							if (linea.compareTo(filter)!=0){
//								doit=false;
//							}
							doit=false;
						}	
					}
					
					if (precio>0 & doit){
						data.beginTransaction();
						double old=data.getPrecioViejo(idproveedor, codigo, linea);
						data.clearBatch();
			    		String q="";

			    		if ((Math.abs(precio-old)>0.01)|override){
			    			if (!data.exist(codigo,linea,idproveedor)){
			    				q=data.getInsert(idproveedor, codigo, linea, precio, old,descripcion,idcomprobante,pol);
			    	
			    			}else{
			    				q=data.getUpdate(precio,old, codigo, linea, idproveedor,idcomprobante);
				    				
			    			}
			    			data.addBatch(q);
			    			String q2=data.getInsertCodigosVariacion(codigo, linea, idproveedor, precio,old,idcomprobante);			    			data.addBatch(q2);
			    			data.addBatch(q2);
			    		} else {
			    			q=data.getUpdateFecha(codigo, linea, idproveedor,idcomprobante);
			    			data.addBatch(q);
			    			
			    		}
			    		boolean error=data.executeBatch();
			    		if (error){
			    			data.rollbackTransaction();
			    		}else{
			    			data.commitTransaction();
			    		}
			    		current2++;
				    	

					}
		    		
		    		u++;
		    	}
		    	
		    	return ok;
		}

	   public String getFileName(String idcomprobante){
		   
			
			String destination=this.getDestination();
			if (this.isLinux()){
				destination+="/";
			}else{
				destination+="\\";
			}
			String filename=destination+idcomprobante+".txt";
			return filename;
		}
		
		public void evaluar_idcomprobante(JTextField tx){
			String _nuevo=data.getProximoPGCorrecto("odbc");
			String idcomprobante=tx.getText();
			if (_nuevo.compareTo(idcomprobante)==0){
				frame.get_txt_idcomprobante().setEditable(false);
					
			}else{
				aviso("debe cargarse uno existente o error");
				tx.requestFocusInWindow();
			}
			
		}
		
		 public boolean Actualizar(String idcomprobante,String idproveedor,String pol){
			 	boolean error=false;
			 	System.out.println("Actualizacion de fechas y aplicacion de precios nuevos UPDF:"+idcomprobante);
			 	if (!canceled){
			 		estado2="Aplicando Precios Nuevos";
			 		length2=0;
			 		current2=0;
			    	frame.getJProgressBar1().setIndeterminate(true);
			    	String iduser=this.getConstructor().getIduser();
			    	
			    	
			    	String q="";
			    	data.beginTransaction();
			    	data.clearBatch();
			    	q="alter table v_ma_articulos disable trigger all ";
			    	data.addBatch(q);
			    	q=data.getUpdatePrices(idproveedor, pol, tc, idcomprobante, iduser);
			    	data.addBatch(q);
			    	q="alter table v_ma_articulos enable trigger all ";
			    	data.addBatch(q);
			    	q+="update b_ta_cpte set x_ultimo_nro = x_ultimo_nro + 1 where codigo like '"+tc+"'";
			    	System.out.println(q);
			    	data.addBatch(q);
			    	error=data.executeBatch();
			    	frame.getJProgressBar1().setIndeterminate(false);
			    	current2=0;
			    	
			    	if (!error){
			    		data.commitTransaction();
			    		
			    	}else{
			    	
			    		data.rollbackTransaction();

			    	}	
			 	}
		    	
		    	
		    	return !error;
		    }
	   
	      private aplicacion.actualizacion.exportar.constructor._Constructor exportar=null;
			public boolean exportar(String idproveedor,String nombre){
			    boolean ok=false;
			    frame.getJProgressBar1().setIndeterminate(true);
				estado="Exportando Lista de "+nombre+" a Internet ";
				crono2=new Crono();
				crono2.start();
			    estado2="Obteniendo datos para exportar ";
			    length2=2;
			    current2=0;

			   ok= this.taskworkInfo();	
				current2=2;
				frame.getJProgressBar1().setIndeterminate(false);
				return ok;
				
			}
	   
			 public boolean create_write_file(String file_path){
				 boolean ok=true;
				 try {
		 			///aca va el lector de archivo log de la tarea
		 			file=new File(file_path);
		 			file = file.getAbsoluteFile();
		 			if (file.exists())//file.delete();
		 			file.createNewFile();
		 			out = new BufferedWriter(new FileWriter(file));
		 			
		 		}catch (Exception e){
		 			ok=false;
		 			e.printStackTrace();
		 		}

		 		return ok;
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
			    
			    public boolean uploadToFTP(String serverfile,String localfile,long filesize){
			    	System.err.println(serverfile+" size:"+filesize+" "+localfile);
			    	
			    	
			    	
			    	boolean ok=true;
			    	this.length=100;
			        try {
			            //InputStream stO =  new BufferedInputStream(
			            //        ftp.retrieveFileStream(serverfile),
			            //        ftp.getBufferSize());
			            
			            
			            //OutputStream stD = new FileOutputStream(localfile);
			            
			        	InputStream stO =new FileInputStream(localfile);
			        	ftp.storeFile(serverfile, stO);
			        	
			        	OutputStream stD = new BufferedOutputStream(ftp.appendFileStream(serverfile),ftp.getBufferSize());
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
			                               if (canceled){
			                            	   try {

			    								ftp.abort();
			    								ftp.disconnect();
			    							} catch (IOException e) {
			    								// TODO Auto-generated catch block
			    								e.printStackTrace();
			    								System.err
														.println(e.getMessage());
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
			        } catch (Exception e) { 
			        	e.printStackTrace();
			        	System.err.println(e.getMessage());
			        	ok=false;
			        }
			        
//			        done=true;
			        return ok;
			    }
			
			    public boolean uploadFile(String directory,String localfile,String destination,boolean binary){
			    	System.out.println("directory: "+directory+" \n localfile: "+localfile+" \n destination:"+destination);
					
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
//									System.err.println("OJO>> destination:"+destination+" localfile:"+_localfile.getName());
							    	//this.deleteFile(destination,"actualizacion.xml", true);
									try {
										ok=uploadToFTP(destination+_localfile.getName(), localfile,_localfile.length());
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
										System.err.println(e.getMessage());
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
					
					return ok;
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
			        xml._guardar(destination+"\\actualizacion.xml");
					
				}
			    
			public boolean taskworkInfo(){
				 boolean ok=false;
				 String destination=this.getDestination();
			    	frame.getJProgressBar().setIndeterminate(true);

			    	estado="Cargando Informacion desde Base De Datos ";
			       	int i=0,lon=frame.get_table_catalogos().getRowCount();

			    	while(i<lon && !this.canceled){
			    		boolean selected=(Boolean)frame.get_table_catalogos().getValueAt(i, 0);

			    		if(selected){
					    	String idproveedor=(String)frame.get_table_catalogos().getValueAt(i, 1);
					    	String linea=(String)frame.get_table_catalogos().getValueAt(i,5);

					    	final Object[][] results = data.getCodigos(idproveedor, linea);
					    	String idactualizacion=this.nuevoEUPD();
					    	String file_path=this.getFileName(idactualizacion);
					    	ok=false;

							if (file_path.compareTo("")!=0){
								if (!file_path.endsWith(".txt")){
									file_path+=".txt";
								}
								ok=this.create_write_file(file_path);

							}
							if (ok){								
								if (results!=null){

									if (results.length>0){
										System.out.println("Exportando a Archivo "+file_path+" "+idproveedor+" "+idactualizacion);
										//cambiar 100 por results.length
										for (int row=0;row<results.length;row++){
											boolean b=true;
											boolean null_code=false;
											String line="";
												for (int col=0;col<results[0].length;col++){
													if (line.length()>0){
														line+="	";
														
													}
													String value=results[row][col].toString();
													
													if(!results[row][3].toString().contains(".")){
														results[row][3]=results[row][3].toString()+".0";
													}

													if (value.compareTo("")==0 & col==0){
														null_code=true;
													}
													line+=value;
												}
												if (!null_code){
													this.addLine(line);	
												}else{
													System.out.println("codigo nulo en linea "+row+" no se agrega al archivo de exportacion ");
												}
																	
												current++;		
										}
										this.close_write_file();
										String target=file.getPath();
										target=target.replaceAll(".txt", ".zip");
										ok=this.compressFile(file.getPath(), target);
										if (ok){
											
//											file.delete();
											
											
											frame.getJProgressBar().setIndeterminate(false);
											
											// "" = uploadFile
											try {
												ok=this.uploadFile("/beta", target,"actualizacion/", true);
											} catch (Exception e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
												System.err
														.println(e.getMessage());
											}
											if (ok){
												data.updateTC(tc);
												ok=data.InsertFTP(idactualizacion, idproveedor);
												
											}
											
										}
										
									}
										
								}	
							}
							
			    		}
			    		i++;

			    	}
			    	return ok;
			}
			
			
			
	   public void ActualizarFechas(String pol,String idcomprobante,String iduser){
		   frame.getJProgressBar().setIndeterminate(true);
	    	estado="Aplicando Cambio de Precios a Articulos";
	    	current=0;
	    	length=0;
	    	data.beginTransaction();
	    	String q=data.update_politica(idcomprobante, pol,iduser);
	    	data.clearBatch();
	    	data.addBatch(q);
	    	q=data.getUpdateTC("odbc");
	    	data.addBatch(q);
	    	boolean error=data.executeBatch();
	    	if (!error){
	    		data.commitTransaction();
	    	}else{
	    		data.rollbackTransaction();
	    	}
	    	
	    	estado="Finalizado";
//	    	done=true;
	    }
	   
	   
	   public void _taskworkInfo(){
    	frame.getJProgressBar().setIndeterminate(true);
    	archivo=null;
    	int i=0,lon=frame.get_table_catalogos().getRowCount();

    	while(i<lon && !this.canceled){
    	  	boolean selected=(Boolean)frame.get_table_catalogos().getValueAt(i,0);
    	  	if(selected){
    	  		
    	  		String idproveedor =(String)frame.get_table_catalogos().getValueAt(i,1);
    	  		String pol = (String) frame.get_table_catalogos().getValueAt(i, 6);
    	  		String linea = (String) frame.get_table_catalogos().getValueAt(i,5);
            	if (this.Politica.existe(pol)){
            		estado="Cargando Informacion desde Catalogo ";
                	boolean ok=data.createODBCConnection(idproveedor);
                	if (ok){
                	//	String query=exportar.	//frame.get_txt_consulta().getText();
                        try{
//                         archivo=data.getODBCResult(idproveedor, query);
                        }catch(Exception e){
                            	
                         }
          
                        String idcomprobante=frame.get_txt_idcomprobante().getText();
                        
                        frame.getJProgressBar().setIndeterminate(false);
                        // boolean oku=this._taskworkUpdate(idcomprobante, idproveedor, linea,pol, archivo);
                        estado="Finalizando ";
                	}
                	
            	}else{
            		error("Ingrese una Politica de Precios");
            	}

    	  	}
    	  	i++;
    	}
    	
    	
//    	done=true;
    }
    
    public void clean(){
    	frame.getJProgressBar().setString("");
    	frame.getJProgressBar().setIndeterminate(false);
    	frame.getJProgressBar().setValue(0);
    	frame.get_txt_idcomprobante().setText("");
    	frame.get_txt_idcomprobante().setEditable(true);
    	frame.get_btn_cancelar_global().setEnabled(false);
    	frame.get_btn_cancelar_tarea().setEnabled(false);
    	frame.get_btn_play().setEnabled(false);
    	frame.get_btn_modificarCamino().setEnabled(true);
    	frame.setJTable(null);
    	frame.repaint();
    	frame.get_txt_idcomprobante().requestFocusInWindow();
    	
    	
    }
    public String nuevoEUPD(){
    	data.clearBatch();
		data.addBatch(data.getUpdateTC("eupd"));
		data.executeBatch();
    	String idcomprobante=data.getProximoPGCorrecto("eupd");
    	return idcomprobante;
    }
    
    public void nuevo(){
    	this.clean();
    	String idcomprobante=data.getProximoPGCorrecto("odbc");
    	boolean existe=data.existeIdcomprobante(idcomprobante);
    	int cont=0;
    	System.out.println("nuevo: "+existe);
    	while (existe && cont<3){
    		data.clearBatch();
    		System.out.println("idcomprobante: "+idcomprobante);
    		data.addBatch(data.getUpdateTC("odbc"));
    		data.executeBatch();
    		idcomprobante=data.getProximoPGCorrecto("odbc");
    		existe=data.existeIdcomprobante(idcomprobante);
    		cont++;
    	}
    	frame.get_chk_actualizar().setSelected(true);
    	frame.get_chk_exportar().setSelected(true);
    	frame.requestFocusInWindow();
    	frame.get_txt_idcomprobante().setText(idcomprobante);
    	frame.get_txt_idcomprobante().requestFocus();
    	//frame.get_txt_path().setText("C:\\Documents and Settings\\Agustin\\Escritorio\\ACTUALIZACIONES\\");
    	frame.get_txt_path().setText("C:\\Documents and Settings\\All Users\\Documentos\\ACTUALIZACIONES\\");
    	frame.get_txt_path().setEditable(false);
    	frame.get_btn_play().setEnabled(true);
    	
    	//this.evaluar_idcomprobante(frame.get_txt_idcomprobante());
    }
    
    
    
//    public void cargarProveedor(String idproveedor){
//		Object[][] results=data.getProveedor(idproveedor);
//		if (results!=null){
//			if (results.length>0){
//				frame.get_txt_idproveedor().setEnabled(false);
//				String codigo=(String) results[0][0];
//				String descripcion=(String) results[0][1];
//				String consulta=(String) results[0][2];
//				String odbc=(String) results[0][4];
//				String politica=(String) results[0][5];
//				String clase=(String) results[0][7];
//				frame.get_txt_proveedor_descripcion().setText(descripcion);
//				frame.get_txt_odbc().setText(odbc);
//				frame.get_txt_class().setText(clase);
//				frame.get_txt_consulta().setText(consulta);
//				frame.get_txt_idpolitica().setText(politica);
//				frame.get_txt_idpolitica().requestFocusInWindow();
//			}
//		}
//	}
    
 	private aplicacion.herramientas.java.evaluadores.Politica Politica=null;
//	public void initialize_Politica(){
//		Politica=new aplicacion.herramientas.java.evaluadores.Politica(){
//			public void cargar(String codigo){
//				Object[][] results=this.getInfo(codigo);
//				String descripcion=(String) results[0][1];
//				frame.get_txt_politica_detalle().setText(descripcion);
//			}
//		};
//		Politica.setConstructor(this.getConstructor());
//	}

	public void BuscarPolitica(JTextField tx){
		Politica.Buscar(tx);
	}

//	public void BuscarPolitica(){
//		Politica.Buscar(frame.get_txt_idpolitica());
//	}

	public void buscarPolitica(JTextField tx){
		Politica.buscar(tx);
	}
	
	public void evaluarPolitica(JTextField tx){
		Politica.evaluate(tx);
	}

    private aplicacion.herramientas.java.evaluadores.Proveedor proveedor=null;
//    public void initialize_proveedor(){
//    	proveedor=new aplicacion.herramientas.java.evaluadores.Proveedor(){
//    		public void cargar(String codigo){
//    			frame.get_btn_test().setEnabled(true);
//				cargarProveedor(codigo);
//    		}
//    	};
//    	proveedor.setConstructor(this.getConstructor());
//    }

    public void BuscarProveedor(JTextField tx){
    	proveedor.Buscar(tx);
    }
    public void buscarProveedor(JTextField tx){
    	proveedor.buscar(tx);
    }
    
//    public void BuscarProveedor(){
//    	proveedor.Buscar(frame.get_txt_idproveedor());
//    }

//    public void evaluarProveedor(JTextField tx){
//    	frame.get_btn_test().setEnabled(false);
//    	proveedor.evaluate(tx);
//    }

//    public void focus(){
//    	frame.get_txt_idcomprobante().requestFocusInWindow();
//    }
    
    
    public void goUpdate() {
    	this.load_variables();
    	
    	SwingWorker worker=null;
    		worker = new SwingWorker() {
    			@Override
    			public Object construct() {
    				return new _taskUpdate();
    			}
    		};
    		if (Timer!=null) {
    			Timer.start();
    		}
    		worker.start();
    	}
    
    public void goInfo() {
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
    
    public void Info(){
    	aviso("Este proceso demorara unos minutos. Si se extiende por mas de 2 minutos, probablemente tenga un  error de configuracion");
		done=false;
		canceled=false;
		this.createTimer();
		goInfo();
	}
    public void Update(){
    	override=true;
    	
//		done=false;
//		canceled=false;
		this.createTimer();
		goUpdate();
	}
    
  //metodos basicos de tareas swing
	public void createTimer(){
		crono=new Crono();
		crono.start();
		length=0;
		current=0;
		errors=0;
		done = false;
		canceled=false;
		Timer=new Timer(300, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (done |canceled){
					endbar();
					endbar2();
					Timer.stop();
					}else {
						updateBar2();
					updateBar();
				}
			}
		}); 
		
	}

	
	
	public void updateBar(){
		frame.getJProgressBar().setMaximum(length);
		frame.getJProgressBar().setValue(current);
		frame.getJProgressBar().setString(estado+" "+current+"/"+length+" "+crono.elapsed());
		frame.getJProgressBar().setStringPainted(true);
	}
	public void updateBar2(){
		frame.getJProgressBar1().setMaximum(this.length2);
		frame.getJProgressBar1().setValue(current2);
		if (crono2!=null){
			frame.getJProgressBar1().setString(estado2+" "+current2+"/"+length2+" "+crono2.elapsed());	
		}
		frame.getJProgressBar1().setStringPainted(true);
	}
	
	public void endbar(){
		estado="";
		frame.getJProgressBar().setString("");
		frame.getJProgressBar().setIndeterminate(false);
		frame.getJProgressBar().setValue(0);

	}
	public void endbar2(){
		estado2="";
		frame.getJProgressBar1().setString("");
		frame.getJProgressBar1().setIndeterminate(false);
		frame.getJProgressBar1().setValue(0);

	}
	
	public void doCancel(){
		if (preguntar("confirmar","Cancela Tarea?")){
			this.canceled=true;
			frame.get_btn_cancelar().setEnabled(true);
			frame.get_table_catalogos().setEnabled(true);
			frame.get_btn_cancelar_global().setEnabled(false);
			frame.get_btn_cancelar_tarea().setEnabled(false);
			frame.get_chk_seleccionar().setEnabled(true);
			frame.get_btn_cancelar().setEnabled(true);
			frame.get_btn_nuevo().setEnabled(true);
			frame.get_btn_play().setEnabled(true);
			frame.get_btn_salir().setEnabled(true);
			frame.get_btn_modificarCamino().setEnabled(true);
		}
	}
	
//	public void guardar(){
//		String idproveedor=frame.get_txt_idproveedor().getText();
//		String consulta=frame.get_txt_consulta().getText();
//		String politica=frame.get_txt_idpolitica().getText();
//		String odbc=frame.get_txt_odbc().getText();
//		String linea=frame.get_txt_linea().getText();
//		String clase=frame.get_txt_class().getText();
//		if (clase.compareTo("")==0){
//			
//		}
//		boolean error=false;
//		if (data.check_catalog(idproveedor)){
//			error=data.UpdateCatalog(idproveedor, politica, consulta,linea,odbc,clase);	
//		}else{
//			error=data.InsertCatalog(idproveedor, politica, consulta,linea,odbc,clase);
//		}
//		if (!error){
//			aviso("Se Grabo la configuracion Correctamente");
//		}else{
//			error("Error Grabando Configuracion");
//		}
//	}
	
	/**METODOS NUEVOS!!**/
	public Object[][] preparar_datos(Object[][] results){
		Object[][] temp=new Object[results.length][results[0].length+1];
		for (int i=0;i<results.length;i++){
			temp[i][0]=false;
			for (int j=0;j<results[0].length;j++){
				temp[i][j+1]=results[i][j];
			}
		}
		return temp;
	}
	
	private void create_table(Object[][] results) {
		CustomTable table = new CustomTable();

		Column col = new Column();
		col.setName("");
		col.setWidth(40);
		col.setEditable(true);
		CheckBoxCellEditor chkce = new CheckBoxCellEditor();
		chkce.setItemListener(this._constructor.getItemListener());
		chkce.setTipo(Boolean.class);
		chkce.setName(_Interface._table_chk);
		col.setCellEditor(chkce.getCellCheck());
		col.setClass(Boolean.class);
		table.addColumn(col);
		
		
		//table.addMouseListener(this._constructor.getMouseListener());
		col = new Column();
		col.setName("idproveedor");
		col.setWidth(80);
		col.setClass(String.class);
		/*CellEditor pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_item_id);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());*/
		col.setEditable(false);
		table.addColumn(col);
		
		col = new Column();
		col.setName("nombre");
		col.setWidth(160);
		col.setEditable(false);
		col.setClass(String.class);

/*		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_item_label);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());*/

		table.addColumn(col);

		col = new Column();
		col.setName("tipo");
		col.setWidth(80);
		col.setEditable(false);
		col.setClass(String.class);
/*		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_item_area);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());*/
		table.addColumn(col);
		
		col = new Column();
		col.setName("origen");
		col.setWidth(100);
		col.setEditable(false);
		col.setClass(String.class);
/*		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_item_icono);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());*/
		table.addColumn(col);
		
		col = new Column();
		col.setName("linea");
		col.setWidth(100);
		col.setEditable(true);
		col.setClass(String.class);
		table.addColumn(col);
		
		
		col = new Column();
		col.setName("politica");
		col.setWidth(100);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);
		
		results=this.preparar_datos(results);

		table.setData(results);

		
		
		Font fuente = new Font("Arial", Font.PLAIN, 9);
		table.setHeaderFont(fuente);
		
		table.build();
		
		table.fillData();
		JTable _table = table.getTable();
		_table.setName(_Interface._table_catalogos);
		_table.addMouseListener(this.getConstructor().getMouseListener());
		_table.addKeyListener(this.getConstructor().getKeyListener());
		
		final JTable _tablex=_table;

		if(javax.swing.SwingUtilities.isEventDispatchThread()){
			frame.setJTable(_tablex);
		}else
		{
			Runnable _execute=new Runnable(){
				   public void run() {
					   frame.setJTable(_tablex);		   
				   }
			};
			this.invokeAndWait(_execute);
		}
		
//		frame.setJTable(_table);
//		frame.setVisible(true);
	}
	
	public void cargar_parametros(){
		

		this.nuevo();
//		frame.get_txt_idcomprobante().setText("00001");
//		frame.get_txt_idcomprobante().setEnabled(false);
		
//		Object[][] results=data.getCatalogos();
//		if (results!=null){
//			if (results.length>0){
//				this.create_table(results);
//				
//			}
//		}
	}
	
	
	private aplicacion.herramientas.java.visualselector.constructor._Constructor vsellector = null;
	 
	public void evaluarIdcomprobante(JTextField tx){
		String idcomprobante=tx.getText();
		if(idcomprobante.length()>0){
			Object[][] results=data.getResults(data.existeIdcomprobanteQuery(idcomprobante));
	    	boolean b=false;

	 		if (results!=null){
	 			if (results.length>0){
	 				
	 				b=true;
	 			}
	 		}

	 		if (!b){
	 			String idx=data.getProximoPGCorrecto(tc);
	 			b=idx.compareTo(idcomprobante)==0;
	 		}
			if(b){
				/**/
				String filtro=frame.get_lst_modo().getSelectedItem().toString();
				this.crearTablaVacia(filtro);
				frame.get_txt_idcomprobante().setEditable(false);
			}else{
				error("idcomprobante invalido");
			}
		}else{
			this.buscarGlobal(tx);
			}	
		frame.get_btn_play().setEnabled(true);
						
	}
	
	  
	 	private aplicacion.herramientas.java.evaluadores.Global Global=null;
		public void initialize_Global(){
			Global=new aplicacion.herramientas.java.evaluadores.Global(){
				public void cargar(String codigo){
					Object[][] results=this.getInfo(codigo);
					String descripcion=(String) results[0][1];
					frame.get_txt_idcomprobante().setText(descripcion);
				}
			};
			Global.setConstructor(this.getConstructor());
		}
		public void BuscarGlobal(JTextField tx){
			Global.Buscar(tx);
		}
		public void BuscarGlobal(){
			Global.Buscar(frame.get_txt_idcomprobante());
		}
		public void buscarGlobal(JTextField tx){
			Global.buscar(tx);
		}
		
		public void evaluarGlobal(JTextField tx){
			Global.evaluate(tx);
		}

	public String getQuery(String idproveedor){
		Object[][] results=data.getProveedor(idproveedor);
		String consulta="";
		if (results!=null){
			if (results.length>0){
				consulta=(String) results[0][2];
			}
		}
		return consulta;
	}
		
	public void evaluarIdcomprobante(String idcomprobante){
		
		if(idcomprobante.length()>0){
			if(!data.existeIdcomprobante(idcomprobante)){
				this.play();
			}
			else{
				//cargarActualizacion();
			}
		}
		else{
			this.BuscarGlobal();
		}
		
	}
	
	
	public void play(){

	String iduser=validar_usuario();	
	String idcomprobante=frame.get_txt_idcomprobante().getText();
	String idproveedor=null;
	
	if(idcomprobante.length()>0){
		
//		frame.get_table_catalogos().setEnabled(false);
//		frame.get_btn_cancelar_global().setEnabled(true);
//		frame.get_btn_cancelar_tarea().setEnabled(true);
//		frame.get_chk_seleccionar().setEnabled(false);
//		frame.get_btn_cancelar().setEnabled(false);
//		frame.get_btn_nuevo().setEnabled(false);
//		frame.get_btn_play().setEnabled(false);
//		frame.get_btn_salir().setEnabled(false);
//		frame.get_btn_modificarCamino().setEnabled(false);
		
		//1)
		this.length=0;
		if(iduser.length()>0){
			String q=data.insertIdcomprobante(idcomprobante, iduser,"");

			data.clearBatch();
			data.addBatch(q);

				int cont=frame.get_table_catalogos().getRowCount();
				
				for(int i=0; i<cont ;i++){

					if((Boolean)frame.get_table_catalogos().getValueAt(i,0)){
						idproveedor=frame.get_table_catalogos().getValueAt(i,1).toString();
						q=data.insertIdcomprobante(idcomprobante, idproveedor);
						data.clearBatch();
						data.addBatch(q);
						this.length++;
					}
					
				}
			boolean error=data.executeBatch();

			if(!error){
				this.Update();
				
				}

			
				
		}
		
			idcomprobante=data.getProximoPGCorrecto("odbc");
			frame.get_txt_idcomprobante().setText(idcomprobante);
			frame.repaint();
//			frame.get_txt_idcomprobante().setText(idcomprobante);
//			frame.get_table_catalogos().setEnabled(true);
//			frame.get_btn_cancelar_global().setEnabled(false);
//			frame.get_btn_cancelar_tarea().setEnabled(false);
//			frame.get_chk_seleccionar().setEnabled(true);
//			frame.get_btn_cancelar().setEnabled(true);
//			frame.get_btn_nuevo().setEnabled(true);
//			frame.get_btn_play().setEnabled(true);
//			frame.get_btn_salir().setEnabled(true);
//			frame.get_btn_modificarCamino().setEnabled(true);
	 }
	}
	
	public String validar_usuario(){
		String idvendedor="";
		String password=this.requestPassword("Ingrese Su Clave:");
		idvendedor=data.getUserValidacion(password);
		if (idvendedor.compareTo("")==0){
			error("Error de Validacion de Usuario");
			
		}
		return idvendedor;
	}
		
	public void crearTablaVacia(String filtro){

		if(javax.swing.SwingUtilities.isEventDispatchThread()){
			Object[][] results=data.getResults(data.getQueryCatalogos(filtro));
			
			this.create_table(results);
			
		}
		else{
			final String _filtro=filtro;
			Runnable _execute=new Runnable(){
				public void run(){
					Object[][] results=data.getResults(data.getQueryCatalogos(_filtro));
					create_table(results);
				}
			};
			this.invokeAndWait(_execute);
		}
	}
	
//	 public void _taskworkInfo(String idproveedor,String linea){
//		 String idactualizacion=frame.get_txt_idcomprobante().getText();
//		 String destination=this.getDestination();
//	    	frame.getJProgressBar().setIndeterminate(true);
//	    	
//	    	estado="Cargando Informacion desde Base De Datos ";
//	    	
//	    	final Object[][] results = 
//				data.getCodigos(idproveedor, linea);
//	    	String file_path=frame.get_txt_archivo().getText();
//	    	boolean ok=false;
//			if (file_path.compareTo("")!=0){
//				if (!file_path.endsWith(".txt")){
//					file_path+=".txt";
//				}
//				ok=this.create_write_file(file_path);
//			}
//			if (ok){
//				if (results!=null){
//					if (results.length>0){
//						for (int row=0;row<results.length;row++){
//							boolean b=true;
//							String line="";
//								for (int col=0;col<results[0].length;col++){
//									if (line.length()>0){
//										line+="	";
//									}
//									line+=results[row][col].toString();
//								}
//								this.addLine(line);					
//								current++;		
//						}
//						this.close_write_file();
//						String target=file.getPath();
//						target=target.replaceAll(".txt", ".zip");
//						ok=this.compressFile(file.getPath(), target);
//						if (ok){
//							
//							file.delete();
//							frame.getJProgressBar().setIndeterminate(false);
//							
//							ok=this.uploadFile("/beta", target, "actualizacion/", true);
//							if (ok){
//								data.updateTC(tc);
//								ok=data.InsertFTP(idactualizacion, idproveedor);
//								if (ok){
//									this.generar_xml();
//									frame.getJProgressBar().setIndeterminate(false);
//									current=0;
//									ok=this.uploadFile("beta/", destination+"/actualizacion.xml","", true);
//									if (ok){
//										aviso("Se Exporto Correctamente");	
//										this.clean();
//										this.nuevo();	
//									}
//									
//									
//								}
//								
//								
//							}
//							
//						}
//						
//					}
//						
//				}	
//			}
//			
//			
//			
//			
//			done=true;
//		
//	    	
//	    }

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
	    
//	 class _taskInfo {
//			_taskInfo() {
//				_taskworkInfo();
//				}
//			}
		 

			
			
			
			
	public String getDestination(){
		String destination="";
		if (this.isLinux()){
			destination="_/";
		}else{
			destination="C:\\windows\\temp\\_actualizacion";
		}
		return destination;
	}

	public void seleccionar(JCheckBox chk){
		int cont=frame.get_table_catalogos().getRowCount();
		for(int i=0; i<cont ;i++){
			frame.get_table_catalogos().setValueAt(chk.isSelected(),i,0);
		}
	
	}

	public void filtro(JComboBox cb){
		this.crearTablaVacia(cb.getSelectedItem().toString());		
	}
	
	public void actualizar(){
		this.createTimer();
		
		SwingWorker worker=this.crearSwingWorker();
		
		this.Timer.start();
		
		worker.start();
		
	}
	
	
	
	public void LimpiarBarraDeProgreso2() {
		estado2 = "";
		frame.getJProgressBar1().setString("");
		frame.getJProgressBar1().setIndeterminate(false);
		frame.getJProgressBar1().setValue(0);
		
	}
	public SwingWorker crearSwingWorker() {
		SwingWorker worker = null;
		if (worker == null) {
			worker = new SwingWorker() {
				public Object construct() {
					return new _taskUpdate();
				}
			};
		}
		return worker;
	}
	
	public void update(){

		String idproveedor="";
		String nombre="";
		String tipo="";
		String origen="";
		String linea="";
		String pol="";
		String idcomprobante=frame.get_txt_idcomprobante().getText();
		int lon=frame.get_table_catalogos().getRowCount();
		
		data.clearBatch();
		frame.get_table_catalogos().setEnabled(false);
		frame.get_btn_cancelar_global().setEnabled(true);
		frame.get_btn_cancelar_tarea().setEnabled(true);
		frame.get_chk_seleccionar().setEnabled(false);
		frame.get_btn_cancelar().setEnabled(false);
		frame.get_btn_nuevo().setEnabled(false);
		frame.get_btn_play().setEnabled(false);
		frame.get_btn_salir().setEnabled(false);
		frame.get_btn_modificarCamino().setEnabled(false);

		int cont=frame.get_table_catalogos().getRowCount();
		int i;
		String q="";
		length=0;
		
		
		for(i=0; i<cont ;i++){

			if((Boolean)frame.get_table_catalogos().getValueAt(i,0)){
				idproveedor=frame.get_table_catalogos().getValueAt(i,1).toString();
				length++;
				q=data.updateIdcomprobante(idcomprobante, idproveedor);
				}	
			}


		i=0;
		boolean error=data.executeBatch();
		current=0;
		boolean actualizar=frame.get_chk_actualizar().isSelected();
		boolean exportar=frame.get_chk_exportar().isSelected();

		if (!error){
			while(i<lon && !canceled){

				boolean selected=(Boolean)frame.get_table_catalogos().getValueAt(i,0);

				if(selected){

					System.out.println("proveedor seleccionado "+idproveedor);
					
					idproveedor =(String)frame.get_table_catalogos().getValueAt(i,1);
					nombre =(String)frame.get_table_catalogos().getValueAt(i,2);
					tipo =(String)frame.get_table_catalogos().getValueAt(i,3);
					origen =(String)frame.get_table_catalogos().getValueAt(i,4);
					linea =(String)frame.get_table_catalogos().getValueAt(i,5);
					pol =(String)frame.get_table_catalogos().getValueAt(i,6);
					estado="Consultando Catalogo de "+nombre;
					frame.getJProgressBar().setIndeterminate(true);
					Object[][] results=null;
					boolean ok=false;

					
					if (actualizar){
						if(tipo.compareTo("odbc")==0){
							
							ok=data.createODBCConnection(idproveedor);
							if (ok){

								String consulta=data.getQueryCatalogo(idproveedor);
								if (consulta.compareTo("")!=0){
									
									
									results=data.getODBCResult(idproveedor, consulta);
									this.estado="Actualizando Lista de "+nombre;
									frame.getJProgressBar().setIndeterminate(false);
								}
								
		
							}
						}
						else{
							results=this.read_tabulated_file(frame.get_txt_path().getText(),idproveedor);
						}
	
					}
					else{
						ok=true;
					}
boolean archivo=tipo.toLowerCase().compareTo("archivo")==0;	
boolean oki=false;
					if (results!=null){
						if (results.length>0){
							oki=this._taskworkUpdate(idcomprobante, idproveedor, linea, pol, results,archivo);
							try {
								ok=this.Actualizar(idcomprobante,idproveedor, pol);
							} catch (Exception e) {
								// TODO Auto-generate
								e.printStackTrace();
								System.err.println(e.getMessage());
							}
							
							
						    	
						}
					}
					
					if (ok && exportar){
			    		try {
							oki=this.exportar(idproveedor,nombre);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							System.err.println(e.getMessage());
						}
						
					}

					this.current++;
				}
				i++;
		}
		
			if (exportar){
				destination=getDestination();
				this.generar_xml();
				frame.getJProgressBar().setIndeterminate(false);
				current=0;
				try {
					boolean ok=this.uploadFile("/beta", destination+"\\actualizacion.xml","", true);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.err.println(e.getMessage());
				}
				
					//aviso("Se Exporto Correctamente");	
					//this.clean();
					//this.nuevo();	
			}
		}
		
		done=true;
		aviso("Actualizacion Terminada");
		
		frame.get_table_catalogos().setEnabled(true);
		frame.get_btn_cancelar_global().setEnabled(false);
		frame.get_btn_cancelar_tarea().setEnabled(false);
		frame.get_chk_seleccionar().setEnabled(true);
		frame.get_btn_cancelar().setEnabled(true);
		frame.get_btn_nuevo().setEnabled(true);
		frame.get_btn_play().setEnabled(true);
		frame.get_btn_salir().setEnabled(true);
		frame.get_btn_modificarCamino().setEnabled(true);
	}
	
	   public boolean deleteFile(String directory,String file,boolean binary){
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
							
							ftp.deleteFile(file);
						}
						
						
						
							
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
	
		public Object[][] read_tabulated_file(String path,String idproveedor){
			
			/*
			 * esta ruta debe ser buscada segun el codigo de proveedor
			 */
			
			path+=idproveedor+"\\Lista.txt";
			
			
			  int chrx=9;//este nueve es un caracter de tabulacion
			  String record = null;
			  List<List<String>> dynamic=new ArrayList<List<String>>();
			  Object[][] results=null;
			  int col=0;
			  if (path.compareTo("")!=0){
				  File file=new File(path);
					 int recCount = 0; 

					     try { 
					    	 
					    	FileReader fr     = null;
					        BufferedReader br = null;
					        record = new String();
					        
					        fr= new FileReader(file);
					        br=new BufferedReader(fr);
					        
					        
					        
					        List<String> line=new ArrayList<String>();
					        while ((record = br.readLine()) != null) {
					        	//cara record es una linea
					           this.current=recCount;	
					           String auxs="";
					           col=0;
					           //recorro la linea para encontrar las tabulaciones
					           for (int i=0;i<record.length();i++){
					        	   if (record.charAt(i)==chrx){
					        		   try {
					        			   line.add(auxs);
					        			   
					        		   } catch (Exception e){
					        			   if (debug) {
											System.out.println(e.getMessage());
					        			   }
					        		   }
					        		   auxs="";
					        		   col++;
					        	   }
					        	   else {
					        		   auxs=auxs+record.charAt(i);
					        	   }
					           }
					           try {
					        	   line.add(auxs);
					           }catch (Exception e){
								   if (debug) {
									System.out.println(e.getMessage());
								   }
							   }
					           
					           if (debug) {
								System.out.println(record);
							}
					           
					           
					           dynamic.add(line);
					           line=new ArrayList<String>();
					           recCount++;

					        } 
					     } catch (IOException e) { 
					        e.printStackTrace();
					     }
					     this.length=dynamic.size();
					     
					     results=new Object[dynamic.size()][4];
//					     System.err.println("Archivo con:"+col+" columnas y "+recCount+" lineas");

					     for (int i=0;i<dynamic.size();i++){
					    	 for (int j=0;j<4;j++){
					    		 try {
									results[i][j]=dynamic.get(i).get(j);
//									System.out.print(results[i][j]+"\t");
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
					    	 }
					     }
					     	 
					     if(results[0].length!=4){
					    	 this.error("tabla incompatible");
					    	 results=null;
					     }
					      
			  }else {
				  this.error("Debe seleccionar un archivo");
			  }
			  return results;
		  }

		
		public void modificarRuta(){
			
		}
		
		public void evaluarPath(JTextField tx){
			
		}
		
		public void borrarRuta(){
			
		}
}
