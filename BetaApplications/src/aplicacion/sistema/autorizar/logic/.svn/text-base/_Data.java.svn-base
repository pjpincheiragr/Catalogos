package aplicacion.sistema.autorizar.logic;


import aplicacion.modelo.logic.Data;

public class _Data extends Data {

	public Object[][] getParametroSqlite(String iduser){
		Object[][] results=null;
		String q="";
		q+="select  iduser,nombre,pass,background from b_users ";
		q+="where iduser like '"+iduser+"' ";
		results=getResults(q);
		return results;
	}
	public Object[][] getCajas(String iduser){
		String q="";
		q+="select caja.idcajas,caja.descripcion,isnull(users.origen,0),isnull(users.destino,0) ";
		q+="from   b_ta_cajas caja left outer join b_users_caja users ";
		q+="on caja.idcajas =users.idcaja and users.iduser like '"+iduser+"' ";
		return getResults(q);
	}
	
	public boolean isCajaConfigured(String iduser,String idcaja){
		boolean caja=false;
		String q="";
		q+="select * ";
		q+="from  b_users_caja users ";
		q+="where users.idcaja like '"+idcaja+"' and users.iduser like '"+iduser+"'";
		Object[][] results=getResults(q);
		if (results!=null){
			if(results.length>0){
				caja=true;
			}
		}
		return caja;
	}
	
	public boolean update(String iduser,String idcaja,boolean origen,boolean destino){
		boolean error=false;
		int _origen=0;
		int _destino=0;
		if (origen) _origen=1;
		if (destino) _destino=1;
		String q="";
		q+="update b_users_caja set origen="+_origen+",destino="+_destino+" ";
		q+="where iduser like '"+iduser+"' and idcaja like '"+idcaja+"' ";
		this.clearBatch();
		this.addBatch(q);
		error=this.executeBatch();
		return error;
	}
	
	public boolean insert(String iduser,String idcaja,boolean origen,boolean destino){
		boolean error=false;
		int _origen=0;
		int _destino=0;
		if (origen) _origen=1;
		if (destino) _destino=1;
		String q="";
		q+="insert into b_users_caja (iduser,idcaja,origen,destino) ";
		q+="values ('"+iduser+"','"+idcaja+"',"+_origen+","+_destino+") ";
		this.clearBatch();
		this.addBatch(q);
		error=this.executeBatch();
		return error;
	}
	
	public boolean insert(String iduser, String nombre,String pass,String background){
		String q="";
		q+="insert into b_users (iduser,nombre,pass,background)";
		q+="values ('"+iduser+"','"+nombre+"','"+pass+"','"+background+"')";
		clearBatch();
		addBatch(q);
		return executeBatch();
	}
	
	public boolean update(String iduser, String nombre,String pass,String background){
		String q="";
		q+="update b_users set nombre='"+nombre+"',pass='"+pass+"',background='"+background+"' where iduser like '"+iduser+"' ";
		q+="";
		clearBatch();
		addBatch(q);
		return executeBatch();
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
