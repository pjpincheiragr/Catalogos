package aplicacion.herramientas.conexion.conectores;
import javax.sql.ConnectionPoolDataSource;

import aplicacion.modelo.constructor.*;
public class SQLite extends Generic {



	public SQLite(Constructor C) {
		super(C);
		this.pooltype=false;
		try {
			Class.forName("org.sqlite.JDBC").newInstance();
		} catch (Exception e) {
			
				//System.out.println("JDBC driver error");
			
		}
	}

	protected String getConnectionStream() {
		String connection_stream = "";
		connection_stream = "jdbc:sqlite:" + database;
		return connection_stream;
	}
	
	public SQLite Clone(){
		SQLite clone=new SQLite(C);
		clone.setId(getId());
		clone.setHost(getHost());
		clone.setDatabase(getDatabase());
		clone.setPassword(getPassword());
		clone.setPort(getPort());
		clone.setUser(getUser());
		clone.setConnectorType(getConnectorType());
		return clone;
		}
	
	
}

