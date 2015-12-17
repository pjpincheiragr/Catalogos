package aplicacion.herramientas.conexion.conectores;
import java.sql.DriverManager;
import java.sql.SQLException;

import aplicacion.modelo.constructor.*;
public class ODBC extends Generic {
	private String connection_stream="jdbc:odbc:"; 
	public ODBC(Constructor C,String clase,String connection_stream){
		super(C);
		System.out.println("Create ODBC Driver "+clase+" streambase:"+connection_stream);
		this.connection_stream=connection_stream;
		try {
			 Class.forName(clase);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public ODBC(Constructor C){
		super(C);
		try {
			 Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected String getConnectionStream() {
		String connection_stream = "";
		connection_stream = this.connection_stream + database;
		return connection_stream;
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

}
