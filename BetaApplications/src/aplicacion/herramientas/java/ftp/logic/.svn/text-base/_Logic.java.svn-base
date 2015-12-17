package aplicacion.herramientas.java.ftp.logic;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.*;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

import org.apache.commons.net.ftp.FTPFile;



import aplicacion.herramientas.java.sortableselector.logic.Filtro;
import aplicacion.herramientas.java.sortableselector.logic.columna;
import aplicacion.herramientas.java.table.CellEditor;
import aplicacion.herramientas.java.table.CheckBoxCellEditor;
import aplicacion.herramientas.java.table.Column;
import aplicacion.herramientas.java.table.CustomTable;
import aplicacion.inventario.planilla.logic.TableItemColorCellRenderer;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Data;
import aplicacion.modelo.logic.Logic;
import aplicacion.sistema.version.logic.Crono;
import aplicacion.sistema.version.logic.JakartaFtpWrapper;
import aplicacion.herramientas.java.ftp.gui._Frame;
import aplicacion.herramientas.java.ftp.interfaces.*;
import aplicacion.herramientas.java.ftp.logic._Data;
import aplicacion.herramientas.java.launcher.logic.SwingWorker;

public class _Logic extends Logic {
	private _Frame frame;
	private _Data data;
	
	
	private JakartaFtpWrapper ftp = null;
	private String server = "";
	private String ftpserver;
	private String username;
	private String password;
	private String file;
	private String directory = "";
	
	private String estado = "";
	private Object[] test;
	private int current;
	private int lenght, max;
	private boolean debug, done, canceled, override;
	private Timer Timer; // @jve:decl-index=0:
	private Crono crono;

	
	public void setFrame(JFrame _frame){
		this.frame=(_Frame)_frame;
		super.setFrame(_frame);
	}
	
	public void setData(Data _data){
		this.data=(_Data)_data;
		super.setData(_data);
	}
	
	class _ftpTask {
		_ftpTask(String download,String toFile) {
			_ftpDownloadFile(download,toFile);
		}
	}
	
	public void _ftpDownloadFile(String download,String local) {
		long rev = 0;
		
					if (!ftp.isConnected()){
						boolean connected=this._ftpConnect();
					}
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
								downloadFromFTP(download, local, file
										.getSize());
							}

						}	
					}
					
		
					done = true;
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

	public void evaluar_txt_archivo(JTextField tx){
		String file=tx.getText();
		if (file.compareTo("")!=0){
			this.cargarFotoFTP(file);
		}
	}
	
	public void cargarFotoFTP(String filename){
		this.goDownload(filename, filename);
		
	}
public void focus(){
	frame.get_txt_archivo().requestFocusInWindow();
}
	public void loadFoto(String path){
		ImageIcon img=null;
		if (path.compareTo("")!=0){
			File file=new File(path);
			if (file.exists()){
				Image bck=Toolkit.getDefaultToolkit().getImage(path);
				if (bck!=null){
					img=new ImageIcon(bck);
					Image imgx=this.scale(img.getImage(), frame.picture.getWidth(), frame.picture.getHeight());
					frame.picture.setIcon(new ImageIcon(imgx));
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

	public void goDownload(String Download,String toFile) {
    	estado="Descargando Foto";
    	this.createTimer();
    	final String _file=toFile;
    	final String _download=Download;
    	SwingWorker worker=null;
    		worker = new SwingWorker() {
    			@Override
    			public Object construct() {
    				return new _ftpTask(_download,_file);
    			}
    		};
    		
    		worker.start();
    		if (Timer!=null) {
    			Timer.start();
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
}
