package aplicacion.sistema.aplicacion.logic;

import aplicacion.herramientas.conexion.creator.interfaces._Interface;
import aplicacion.modelo.logic.Data;

public class _Data extends Data {

	public Object[][] getParametroSqlite(String id){
		Object[][] results=null;
		String q="";
		q+="select  idaplicacion,menu_nombre,area,icono,lanzador from b_Aplicaciones ";
		q+="where idaplicacion like '%"+id+"%' ";
		q+="order by idaplicacion";
		//System.out.println(">"+q);
		results=getResults(q);
		return results;
	}
	
	public void insert(String id, String value,String area,String lanzador,String icono){
		String q="";
		q+="insert into b_Aplicaciones (idaplicacion,menu_nombre,area,lanzador,icono)";
		q+="values ('"+id+"','"+value+"','"+area+"','"+lanzador+"','"+icono+"')";
		clearBatch();
		addBatch(q);
		executeBatch();
	}
	public void update(String id, String label,String area,String lanzador,String icono){
		String q="";
		q+="update b_Aplicaciones set menu_nombre='"+label+"',area='"+area+"',lanzador='"+lanzador+"',icono='"+icono+"' where idaplicacion like '"+id+"' ";
		q+="";
		clearBatch();
		addBatch(q);
		executeBatch();
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
		q+="delete from b_Aplicaciones where idaplicacion like '"+id+"' ";
		q+="";
		clearBatch();
		addBatch(q);
		executeBatch();
	}
	
}
