package aplicacion.herramientas.java.ftp.logic;

import aplicacion.herramientas.conexion.creator.interfaces._Interface;
import aplicacion.modelo.logic.Data;

public class _Data extends Data {

	public Object[][] getParametroSqlite(String id){
		Object[][] results=null;
		String q="";
		q+="select  (case when u.iduser is null then 0 else 1 end), ";
		q+="a.menu_nombre,a.area,a.idaplicacion ";
		q+="from b_aplicaciones a  ";
		q+="left outer join b_users_aplicaciones u ";
		q+="on a.idaplicacion=u.idaplicacion and isnull(u.iduser,'')='"+id+"'";
		results=getResults(q);
		return results;
	}
	
		
}
