package aplicacion.herramientas.java.tmp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import aplicacion.herramientas.conexion.conectores.MySQL;
import aplicacion.herramientas.conexion.ConnectionHandler;
import aplicacion.herramientas.conexion.conectores.MsSQL;
import aplicacion.modelo.logic.*;
public class CopiaImagen {
	
	public Data data;
	 public MySQL getMySQL(){
			MySQL s=new MySQL(null);
			s.setId("MySQL");
			s.setDatabase("test");
			s.setUser("root");
			s.setPassword("ipsilon");
			s.setHost("192.168.4.150");
			s.connect();
			return s;
		}
	 public MsSQL getMsSQL(){
			MsSQL s=new MsSQL(null);
			s.setId("MsSQL");
			s.setDatabase("Neuquen2006");
			s.setUser("sa");
			s.setPassword("");
			s.setHost("192.168.4.1");
			s.connect();
			
			return s;
		}
	 
	    public void initConnector(){
	    	data=new Data();
	    	ConnectionHandler CH=new ConnectionHandler();
	    	data.setSql(CH);
			data.getConnectionHandler().addConector(getMySQL());
			data.getConnectionHandler().addConector(getMsSQL());
			data.getConnectionHandler().setDefault("MsSQL");
			data.getConnectionHandler().startConnections();
		}
	    public static void main(String[] arg){
	    	CopiaImagen Ci=new CopiaImagen();
	    	Ci.initConnector();
	    	Ci.storeoldimages();
	    }
	    public void storeoldimages(){
	    	String q="select cuenta,tc,idcomprobante,fotos from c_mv_cpte_digital ";
	    	Object[][] results=data.getResults(q);
	    	for (int i=0;i<results.length;i++){
	    		String cuenta=(String) results[i][0];
	    		String tc=(String) results[i][1];
	    		String idcomprobante=(String) results[i][2];
	    		String fotos=(String) results[i][3];
	    		int _fotos=new Integer(fotos);
	    		for (int j=0;j<_fotos;j++){
	    			
		    		String filename="//192.168.4.150/windows storage/fotos/"+cuenta+"-"+idcomprobante+"-"+(j+1)+".JPG";
		    		System.out.println(cuenta+" "+tc+" "+idcomprobante+" "+filename);
		    		this.storeImage(cuenta,tc,idcomprobante, j, filename);
	    		}
	    		
	    	}
	    }
	public void storeImage(String idproveedor,String tc,String idcomprobante,int secuencia,String filename){
    	FileInputStream fis = null;
        PreparedStatement ps = null;
        if (idcomprobante.compareTo("")!=0 & idproveedor.compareTo("")!=0 & idproveedor.compareTo("")!=0){
        	String INSERT_PICTURE = "insert into facturas(idproveedor, tc, idcomprobante,secuencia,imagen) values (?, ?, ?, ?, ?)";
            File file = new File(filename);
              if (file.exists()){
            	  if (file.isFile()){
            	  try {
        	          
        	          
        	          try {
        				fis = new FileInputStream(file);
        	          } catch (FileNotFoundException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        	          }
        	          
        	          try {
        	        	  
        				ps = data.getConnector("MySQL").prepareStatement(INSERT_PICTURE);
        				ps.setString(1, idproveedor);
        				ps.setString(2, tc);
        				ps.setString(3, idcomprobante);
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
        }
          
    }
}
