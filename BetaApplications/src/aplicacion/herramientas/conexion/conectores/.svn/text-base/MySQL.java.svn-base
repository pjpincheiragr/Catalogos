package aplicacion.herramientas.conexion.conectores;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.ConnectionPoolDataSource;

import aplicacion.modelo.constructor.*;
import aplicacion.herramientas.conexion.*;

/**
 * Conector que cumple con la especificacion de la interfaz Conector
 * @author Agustin Wisky
 * @since 03-09-2009
 *
 */
public class MySQL extends Generic {
	private String tdsVersion="";
	
	
	public String getTdsVersion() {
		return tdsVersion;
	}

	public void setTdsVersion(String tdsVersion) {
		this.tdsVersion = tdsVersion;
	}

	
	public MySQL(Constructor C){
		super(C);
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo para formar la cadena de conexion tipica de MsSQL
	 * @return
	 */
	protected String getConnectionStream() {
		
		String connection_stream = "";
		connection_stream += "jdbc:mysql://";
		connection_stream += host;
		if (this.port > 0) {
			connection_stream += ":" + port;
		}
		connection_stream += "/" + database+"?user="+user+"&password="+password;
		
		return connection_stream;
	}
	
	public MySQL Clone(){
		
		MySQL clone=new MySQL(C);
		clone.setTdsVersion(getTdsVersion());
		clone.setId(getId());
		clone.setHost(getHost());
		clone.setDatabase(getDatabase());
		clone.setPassword(getPassword());
		clone.setPort(getPort());
		clone.setUser(getUser());
		clone.setConnectorType(getConnectorType());
		clone.setConnectionPool(getConnectionPool());
		return clone;
	}
	
public boolean connect(){
		
		String connection_stream = getConnectionStream();
		error=false;
		try {
			DriverManager.setLoginTimeout(10);
			
			//connection = DriverManager.getConnection(connection_stream,user,password);
			connection = DriverManager.getConnection(connection_stream);
		} catch (SQLException e) {
				System.out.println(connection_stream);
				//displaySQLErrors(e);
				String NEW_LINE = System.getProperty("line.separator");
				String msj="Error de Conexion con el Servidor ("+id+")";
				msj+=NEW_LINE;
				msj+="Posibles Causas del Problema de Conexion:";
				msj+=NEW_LINE;
				msj+="1) Problema de Red. ";
				msj+=NEW_LINE;
				msj+="	Verifique El Cable de Red en su Puesto o su conexion Wireless ";
				msj+=NEW_LINE;
				msj+="	Verifique Que la infrastructura de red este en funcionamiento (Switchs y Routers) ";
				msj+=NEW_LINE;
				msj+="2) Firewall (Cortafuego)";
				msj+=NEW_LINE;
				msj+="	Verifique que el servidor "+id+" no se encuentre detras de un Firewall que este activado";
				msj+=NEW_LINE;
				msj+="3) Servidor "+id;
				msj+=NEW_LINE;
				msj+="	Verifique que el Servidor "+id+" Este encendido";
				msj+=NEW_LINE;
				msj+="4) Nunca reinicie los servidores! Consulte a Sistemas";
				
				error(msj);
				
				
			error=true;
			
		}
		if (error) {
			System.out.println("Error en conexion");
			
		}
		connected = !error;
		if (connected){
			connected();
		}
		return connected;
	}

public ConnectionPoolDataSource getConnectionPoolDataSource(){
	com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource
	dataSource = new com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource();
      dataSource.setDatabaseName (this.database);
      dataSource.setServerName (this.host);
      dataSource.setUser (this.user);
      try {
		dataSource.setLoginTimeout(10);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      dataSource.setPassword (this.password);
   return dataSource;
}
}
