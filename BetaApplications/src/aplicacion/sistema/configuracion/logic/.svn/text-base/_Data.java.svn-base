package aplicacion.sistema.configuracion.logic;

import aplicacion.herramientas.conexion.creator.interfaces._Interface;
import aplicacion.modelo.logic.Data;

public class _Data extends Data {

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
	
	public void delete(String id){
		String q="";
		q+="delete from parameters where id like '"+id+"' ";
		q+="";
		getConnector(_Interface._beta_database).clearBatch();
		getConnector(_Interface._beta_database).addBatch(q);
		getConnector(_Interface._beta_database).executeBatch();
	}
	
}
