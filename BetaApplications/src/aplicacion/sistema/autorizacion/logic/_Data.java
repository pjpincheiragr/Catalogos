package aplicacion.sistema.autorizacion.logic;

import aplicacion.herramientas.conexion.creator.interfaces._Interface;
import aplicacion.modelo.logic.Data;

public class _Data extends Data {

	public Object[][] getParametroSqlite(String id){
		Object[][] results=null;
		String q="";
		q+="select  (case when u.iduser is null then 0 else 1 end), ";
		q+="a.menu_nombre,a.area,a.idaplicacion,u.icono ";
		q+="from b_aplicaciones a  ";
		q+="left outer join b_users_aplicaciones u ";
		q+="on a.idaplicacion=u.idaplicacion and isnull(u.iduser,'')='"+id+"'";
		results=getResults(q);
		return results;
	}
	
	public void insert(String iduser, String idaplicacion,boolean icono){
		String q="";
		q+="insert into b_users_aplicaciones (iduser,idaplicacion,icono)";
		q+="values ('"+iduser+"','"+idaplicacion+"',";
		if (icono){
			q+="1";	
		}else{
			q+="0";
		}
		q+=") ";
		clearBatch();
		addBatch(q);
		executeBatch();
	}
	
	public void update(String iduser, String idaplicacion,boolean icono){
		String q="";
		q+="update b_users_aplicaciones set icono=";
		if (icono){
			q+="1";	
		}else{
			q+="0";
		}
		
		q+=" where iduser like '"+iduser+"' and idaplicacion like '"+idaplicacion+"' ";
		clearBatch();
		addBatch(q);
		executeBatch();
	}
	
	public boolean existeUsuario(String id){
		boolean existe=false;
		Object[][] results=getResults(this.getUser(id));
		if (results!=null){
			existe=results.length>0;
		}
		return existe;
	}
	
	public String getUser(String iduser){
		String q="";
		q+="select * from b_users ";
		q+="where iduser like '"+iduser+"' ";
		q+="";
		return q;
	}
	
	public void delete(String id,String idaplicacion){
		String q="";
		q+="delete from b_users_aplicaciones where iduser like '"+id+"' and idaplicacion like '"+idaplicacion+"'";
		q+="";
		clearBatch();
		addBatch(q);
		executeBatch();
	}
	
}
