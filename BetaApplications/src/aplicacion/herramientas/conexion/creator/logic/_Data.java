package aplicacion.herramientas.conexion.creator.logic;

import aplicacion.modelo.logic.Data;
import aplicacion.herramientas.conexion.creator.interfaces.*;
public class _Data extends Data {
	
	public Object[][] get_conexion(String idconexion){
		String q="";
		q+="SELECT host,port,database,user,password,tipo,ssh_host,ssh_port,ssh_user,ssh_password,mysql,instance ";
		q+="FROM connection ";
		q+="where id like '"+idconexion+"'";
		Object[][] results=connection_handler.getConnector(_Interface._beta_database).getResults(q);
		return results;
	}
	
	public Object[][] get_conexiones(String tipo){
		String q="";
		q+="SELECT id,host,port,database,user,password,tipo ";
		q+="FROM Connection where tipo like '"+tipo+"' ";
		Object[][] results=connection_handler.getConnector(_Interface._beta_database).getResults(q);
		return results;
	}
	
	public boolean insert(String idconexion,
			String host,
			String port,
			String database,
			String user,
			String password,
			String tipo,
			String ssh_host,
			String ssh_port,
			String ssh_user,
			String ssh_password,
			String mysql){
			String q="";
			q+="insert into Connection (";
			q+="id,host,port,database,user,password,";
			q+="tipo,ssh_host,ssh_port,ssh_user,ssh_password,mysql) ";
			q+="values (";
			q+="'"+idconexion+"',";
			q+="'"+host+"',";
			q+=""+port+",";
			q+="'"+database+"',";
			q+="'"+user+"',";
			q+="'"+password+"',";
			q+="'"+tipo+"',";
			q+="'"+ssh_host+"',";
			q+="'"+ssh_port+"',";
			q+="'"+ssh_user+"',";
			q+="'"+ssh_password+"',";
			q+="'"+mysql+"')";
			connection_handler.getConnector(_Interface._beta_database).clearBatch();
			connection_handler.getConnector(_Interface._beta_database).addBatch(q);
			boolean error=connection_handler.getConnector(_Interface._beta_database).executeBatch();
			return error;
			
	}
	
	public boolean delete(String idconexion){
		String q="";
		q+="delete from Connection where id = '"+idconexion+"' ";
		connection_handler.getConnector(_Interface._beta_database).clearBatch();
		connection_handler.getConnector(_Interface._beta_database).addBatch(q);
		boolean error=connection_handler.getConnector(_Interface._beta_database).executeBatch();
		return error;
	}
	
	public boolean update(String idconexion,
			String host,
			String port,
			String database,
			String instance,
			String user,
			String password,
			String tipo,
			String ssh_host,
			String ssh_port,
			String ssh_user,
			String ssh_password,
			String mysql){
			String q="";
			q+="update connection set ";
			q+="host='"+host+"',";
			q+="port="+port+",";
			q+="database='"+database+"',";
			q+="instance='"+instance+"',";
			q+="user='"+user+"',";
			q+="password='"+password+"',";
			q+="tipo='"+tipo+"',";
			q+="ssh_host='"+ssh_host+"',";
			q+="ssh_port="+ssh_port+",";
			q+="ssh_user='"+ssh_user+"',";
			q+="ssh_password='"+ssh_password+"',";
			q+="mysql='"+mysql+"' ";
			q+="where id like '"+idconexion+"'";
			//System.out.println(q);
			connection_handler.getConnector(_Interface._beta_database).clearBatch();
			connection_handler.getConnector(_Interface._beta_database).addBatch(q);
			boolean error=connection_handler.getConnector(_Interface._beta_database).executeBatch();
			return error;
	}
	
}
