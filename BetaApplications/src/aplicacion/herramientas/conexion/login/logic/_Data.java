package aplicacion.herramientas.conexion.login.logic;

import aplicacion.herramientas.conexion.conectores.MsSQL;
import aplicacion.herramientas.conexion.creator.interfaces._Interface;
import aplicacion.modelo.logic.Data;

public class _Data extends Data {
	
	public Object[][] get_conexion(String idconexion){
		String q="";
		q+="SELECT host,port,database,user,password,tipo,ssh_host,ssh_port,ssh_user,ssh_password,mysql,instance ";
		q+="FROM Connection ";
		q+="where id like '"+idconexion+"'";
		Object[][] results=connection_handler.getConnector(_Interface._beta_database).getResults(q);
		return results;
	}
	
	public String get_conexion_mysql(String idconexion){
		String q="";
		q+="SELECT mysql ";
		q+="FROM Connection ";
		q+="where id like '"+idconexion+"'";
		String mysql="";
		Object[][] results=connection_handler.getConnector(_Interface._beta_database).getResults(q);
		if (results!=null){
			if (results.length>0){
				mysql=(String) results[0][0];
			}
		}
		return mysql;
	}
	
	public Object[][] get_conexiones(String tipo){
		String q="";
		q+="SELECT id,host,port,database,user,password,tipo ";
		q+="FROM Connection where tipo like '"+tipo+"' ";
		Object[][] results=connection_handler.getConnector(_Interface._beta_database).getResults(q);
		return results;
	}
	
	public Object[][] getUserCheck(String user,String pass){
		
		String q="";
		
		q+="select iduser from b_users where iduser like '"+user+"' and pass like '"+pass+"' ";
		System.out.println(q);
		Object[][] results=null;
		
		try {
			results=getResults(q);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}
	
	public boolean hasMemory(){
		
		String _valor=this.getParametroSqlite("login_remember").toString();
		int n=0;
		try {
			n=new Integer(_valor);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (n>0);
	}

	public String getVersion(){
		Object[][] results=this.getParametroSqlite("version");
		String _valor=results[0][1].toString();
		return _valor;
	}

	public Object[][] getParametroSqlite(String id){
		Object[][] results=null;
		String q="";
		q+="select  id,value from parameters ";
		q+="where id like '%"+id+"%' ";
		q+="order by id";
		results=getConnector(_Interface._beta_database).getResults(q);
		return results;
	}
	
	public void insert(String id, String value){
		String q="";
		q+="insert into parameters (id,value)";
		q+="values ('"+id+"','"+value+"')";
		getConnector(_Interface._beta_database).clearBatch();
		getConnector(_Interface._beta_database).addBatch(q);
		getConnector(_Interface._beta_database).executeBatch();
	}
	public void update(String id, String value){
		//System.out.println("Guardando "+id+"="+value);
		String q="";
		q+="update parameters set value='"+value+"' where id like '"+id+"' ";
		q+="";
		getConnector(_Interface._beta_database).clearBatch();
		getConnector(_Interface._beta_database).addBatch(q);
		getConnector(_Interface._beta_database).executeBatch();
	}
	
	public boolean existe(String id){
		boolean existe=false;
		Object[][] results=this.getParametroSqlite(id);
		if (results!=null){
			existe=results.length>0;
		}
		return existe;
	}
	
	public String getMySQLFromConnection(String idconexion){
		Object[][] results=get_conexion(idconexion);
		String mysql="";
		if (results!=null){
			if (results.length>0){
				mysql=(String) results[0][10];
			}
		}
		return mysql;
	}
}
