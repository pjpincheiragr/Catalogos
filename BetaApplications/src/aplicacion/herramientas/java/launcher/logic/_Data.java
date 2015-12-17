package aplicacion.herramientas.java.launcher.logic;

import aplicacion.modelo.logic.Data;

public class _Data extends Data {

	public boolean esta_autorizado(String iduser,String lanzador){
		boolean au=false;
		String q="";
		q=q+"select b.idaplicacion from b_aplicaciones b ";
		q=q+"left outer join b_users_aplicaciones a ";
		q=q+"on b.idaplicacion = a.idaplicacion ";
		q=q+"where b.lanzador like '"+lanzador+"' "; 
		q=q+"and a.iduser like '"+iduser+"' ";
		Object[][] results=getResults(q);
		System.out.println(q);
		if (results!=null){
			if (results.length>0){
				au=true;
			}
		}
		return au;
	}
}
