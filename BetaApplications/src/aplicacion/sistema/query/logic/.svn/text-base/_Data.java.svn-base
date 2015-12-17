package aplicacion.sistema.query.logic;


import aplicacion.modelo.logic.Data;

public class _Data extends Data {

	public Object[][] getQuery(String idquery){
		Object[][] results=null;
		String q="";
		q+="select idquery,idproveedor,odbc,tabla,limit from b_query ";
		q+="where idquery like '"+idquery+"' ";
		results=getResults(q);
		return results;
	}
	
	public Object[][] getQueryColumns(String idquery){
		Object[][] results=null;
		String q="";
		q+="select nombre,alias,columna,filtro from b_query_column ";
		q+="where idquery like '"+idquery+"' ";
		results=getResults(q);
		return results;
	}
	
	
	
	
	
	public String update(String idquery,String idproveedor,String odbc,String tabla,boolean limit){
		String q="";
		
		q+="update b_query set idproveedor='"+idproveedor+"',odbc='"+odbc+"', tabla='"+tabla+"' ";
		if (limit){
			q+=",limit=1 ";	
		}else{
			q+=",limit=0 ";
		}
		
		q+="where idquery like '"+idquery+"' ";
		
		return q;
	}
	
	public String insert(String idquery,String idproveedor,String odbc,String tabla,boolean limit){
		String q="";
		q+="insert into b_query (idquery,idproveedor,odbc,tabla,limit) ";
		q+="values ('"+idquery+"','"+idproveedor+"','"+odbc+"','"+tabla+"'";
		if (limit){
			q+=",1";	
		}else{
			q+=",0";
		}
		q+=")";
		return q;
	}
	
	public String insert(String idquery,String nombre,String alias,boolean columna,boolean filtro){
		String q="";
		String _columna="0";
		String _filtro="0";
		if (columna) _columna="1";
		if (filtro) _filtro="1";
		q+="insert into b_query_column (idquery,nombre,alias,columna,filtro) ";
		q+="values ('"+idquery+"','"+nombre+"','"+alias+"',"+_columna+","+_filtro+")";
		return q;
	}
	
	public String getDelete(String idquery){
		String q="";
		q+="delete b_query_column where idquery like '"+idquery+"' ";
		q+="";
		q+="";
		return q;
	}
	
	public boolean check_user(String id){
		boolean existe=false;
		Object[][] results=this.getParametroSqlite(id);
		if (results!=null){
			existe=results.length>0;
		}
		return existe;
	}
	
	public void delete(String id){
		String q="";
		q+="delete from b_users where iduser like '"+id+"' ";
		q+="";
		clearBatch();
		addBatch(q);
		executeBatch();
	}
	
	public void delete_aplicaciones(String id){
		String q="";
		q+="delete from b_users_aplicaciones where iduser like '"+id+"' ";
		q+="";
		clearBatch();
		addBatch(q);
		executeBatch();
	}
	
}
