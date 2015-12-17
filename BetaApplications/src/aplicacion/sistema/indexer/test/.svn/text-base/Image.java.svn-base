package aplicacion.sistema.indexer.test;

import aplicacion.herramientas.conexion.ConnectionHandler;
import aplicacion.herramientas.conexion.conectores.MySQL;
import aplicacion.herramientas.conexion.conectores.MsSQL;
import aplicacion.herramientas.java.image.constructor._Constructor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Blob;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
public class Image {
	ConnectionHandler c;
	
	 public MySQL getMySQL(){
			MySQL s=new MySQL(null);
			s.setId("MySQL");
			s.setDatabase("test");
			s.setUser("root");
			s.setPassword("ipsilon");
			s.setHost("localhost");
			return s;
		}
	 
	 public MsSQL getMsSQL(){
			MsSQL s=new MsSQL(null);
			s.setId("Neuquen");
			s.setHost("192.168.4.1");
			s.setDatabase("Neuquen2006");
			s.setUser("sa");
			s.setPassword("");
			return s;
		}
	    public void initConnector(){
	    	c=new ConnectionHandler();
			c.addConector(getMySQL());
			c.addConector(getMsSQL());
			c.setDefault("Neuquen");
			c.startConnections();
	    }
	    public void getImage(String filename){
	    	try {
				Statement stmt = c.getConnector("MySQL").createStatement();
					String q="SELECT filename,data FROM files where url like '"+filename+"'";
					System.out.println(q);
					ResultSet resultSet = stmt.executeQuery(q);
					if (!resultSet.next()){
						System.out.println("no se encontro el archivo");
					}else{
						 Blob image = resultSet.getBlob(2);
						 String _file = resultSet.getString(1);
				          
				          String name="e:/"+_file;
				       // get blob
				          
				           // setup the streams to process blob
				          InputStream input = image.getBinaryStream();
				          ByteArrayOutputStream output = new ByteArrayOutputStream();
				          // set read buffer size
				          byte[] rb = new byte[1024];
				          int ch = 0;
				           // process blob
				          while ((ch=input.read(rb)) != -1) {
				            output.write(rb, 0, ch);
				          }
				          // transfer to byte buffer
				          byte[] b = output.toByteArray();
				          input.close();
				          output.close();
				          // load final buffer to image icon
				          ImageIcon dPhoto = new ImageIcon(b);

						
				        
						
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			
	    }
	    public void storeImage(String codigo,String linea,String idproveedor,int secuencia,String filename){
	    	FileInputStream fis = null;
	        PreparedStatement ps = null;
	        String INSERT_PICTURE = "insert into imagen_catalogo(codigo, linea, idproveedor, secuencia,imagen) values (?, ?, ?, ?,?)";
	        File file = new File(filename);
	          if (file.exists()){
	        	  try {
	    	          
	    	          
	    	          try {
	    				fis = new FileInputStream(file);
	    	          } catch (FileNotFoundException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    	          }
	    	          
	    	          try {
	    	        	  
	    				ps = c.getConnector("MySQL").prepareStatement(INSERT_PICTURE);
	    				  ps.setString(1, codigo);
	    				  ps.setString(2, linea);
	    				  ps.setString(3, idproveedor);
	    				  ps.setInt(4,secuencia);
	    				  ps.setBinaryStream(5, fis, (int) file.length());
	    				  
	    				  ps.executeUpdate();
	    				  
	    				  System.out.println("Insercion de imagen:"+file.getName()+">?");
	    			} catch (SQLException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	    	          
	    	        } finally {
	    	          try {
	    				ps.close();
	    			} catch (SQLException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	    	          try {
	    				fis.close();
	    			} catch (IOException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	    	        }
	          }else{
	        	  System.out.println(filename+" no existe");
	          }
	        
	    }
	    
	    public static void main(String[] args){
	    	Image img=new Image();
	    	img.initConnector();
	    	//img.storeImage("e:/fotos/211010001-000100447802A-1.jpg");
	    	
	    	//img.storeImage("01EFA024", "ACDELCO", "211010029", 0,"//192.168.4.150/windows storage/catalogo/ACDELCO/01EFA024-0.JPG");
	    	//img.copyImages("211010029");
	    	img.cargarImagen();
	    }
	    
	    public void copyImages(String idproveedor){
	    	String source="//192.168.4.150/windows storage/catalogo/";
	    	String q="select ltrim(rtrim(codigo)),rtrim(ltrim(linea)),imagen ";
	    	q+="from b_catalogo_imagen ";
	    	q+="where idproveedor like '"+idproveedor+"' ";
	    	q+="order by rtrim(ltrim(linea)),ltrim(rtrim(codigo)),idproveedor";
	    	
	    	Object[][] results=c.getConnector("Neuquen").getResults(q);
	    	for (int i=0;i<results.length;i++){
	    		
	    		String codigo=(String) results[i][0];
	    		String linea=(String) results[i][1];
	    		String filename=(String) results[i][2];
	    		System.out.println(">"+codigo+" "+linea+" "+filename);
	    		this.storeImage(codigo, linea, idproveedor, 0, source+filename);
	    	}
	    	
	    }
	    
	    public BufferedImage getImageFromDatabase(String codigo,String linea){
	    	BufferedImage _image=null;
	    	try {
				Statement stmt = c.getConnector("MySQL").createStatement();
					String q="SELECT imagen FROM imagen_catalogo where codigo like '"+codigo+"' and linea like '"+linea+"'";
					System.out.println(q);
					ResultSet resultSet = stmt.executeQuery(q);
					if (!resultSet.next()){
						System.out.println("no se encontro el archivo");
					}else{
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
	    
	    public void cargarImagen(){
	    	String codigo="1-3325 CP  20";
	    	String linea="FEDERAL MOGUL";
	    	BufferedImage img=this.getImageFromDatabase(codigo, linea);
	    	if (img!=null){
	    		this.mostrarFoto(img);	
	    	}
	    	
	    }
	    public void mostrarFoto(BufferedImage img){
	    	_Constructor C=new _Constructor();
			C.build(null);
			//C.init();
			aplicacion.herramientas.java.image.logic._Logic logic=
				(aplicacion.herramientas.java.image.logic._Logic)C.getLogic();
			logic.initConnector();
			logic.setFileName("e:%");
			logic.setImage(img);
			logic.setEliminar(false);
			final aplicacion.herramientas.java.image.logic._Logic lg=logic;
			Runnable imager=new Runnable(){
				public void run(){
					JFrame F=new JFrame();
					F.setUndecorated(true);
					F.add(lg.getImagePanel());
					F.setSize(200,200);
					F.setVisible(true);
				}
			};
			javax.swing.SwingUtilities.invokeLater(imager);
	    }
	   
	    
}
