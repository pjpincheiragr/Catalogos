package aplicacion.sistema.version.logic;

import aplicacion.herramientas.conexion.creator.interfaces._Interface;
import aplicacion.modelo.logic.Data;

public class _Data extends Data {

	public String getValue(String parameter){
		String value="";
		String q="";
		q+="SELECT value  ";
		q+="FROM parameters ";
		q+="where id like '"+parameter+"'";
		Object[][] results=connection_handler.getConnector(_Interface._beta_database).getResults(q);
		if (results!=null){
			value=(String) results[0][0];
		}
		return value;
	}
	
	public boolean existe(String parameter){
		boolean existe=false;
		String q="";
		q+="SELECT value  ";
		q+="FROM parameters ";
		q+="where id like '"+parameter+"'";
		Object[][] results=connection_handler.getConnector(_Interface._beta_database).getResults(q);
		if (results!=null){
			existe=results.length>0;
		}
		return existe;
	}

	public void setValue(String parameter,String value){
		String q="";
		q+="update parameters set value='"+value+"' ";
		q+="where id like '"+parameter+"' ";
		connection_handler.getConnector(_Interface._beta_database).clearBatch();
		connection_handler.getConnector(_Interface._beta_database).addBatch(q);
		connection_handler.getConnector(_Interface._beta_database).executeBatch();
	}
	
	public Object[][] getLastUpdate(){
		String q="";
		q+="select top 1 convert(varchar,idversion)+'.'+convert(varchar,revision),fecha,modificacion ";
		q+="from b_version ";
		q+="order by idversion desc, revision desc";
		q+="";
		return this.getResults(q);
	}
	
	public void insert(String parameter,String value){
		String q="";
		q+="insert into parameters (id,value) ";
		q+="values ('"+parameter+"','"+value+"') ";
		connection_handler.getConnector(_Interface._beta_database).clearBatch();
		connection_handler.getConnector(_Interface._beta_database).addBatch(q);
		connection_handler.getConnector(_Interface._beta_database).executeBatch();
	}

	
	
}
