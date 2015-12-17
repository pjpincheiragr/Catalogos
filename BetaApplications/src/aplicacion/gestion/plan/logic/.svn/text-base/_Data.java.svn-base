package aplicacion.gestion.plan.logic;

import aplicacion.modelo.logic.Data;

public class _Data extends Data {

	public String getUpdate(String newcode,String oldcode){
		String[] parameters=new String[]{
				newcode,oldcode
		};
		String q=this.getQuery("update", parameters);
		return q;
	}
	
	public String getUpdatePadre(String newcode,String oldcode){
		String[] parameters=new String[]{
				newcode,oldcode
		};
		String q=this.getQuery("update_padre", parameters);
		return q;
	}
	
	public String getClasificacion(String clasificacion,String padre){
		String[] parameters=new String[]{
				clasificacion,padre
		};
		String q=this.getQuery("clasificacion", parameters);
		return q;
	}
	
	public String getInsert(String id,String clasificacion,String padre){
		String[] parameters=new String[]{
				id,clasificacion,padre
		};
		String q=this.getQuery("insert", parameters);
		return q;
	}
	
	public String getDelete(String clasificacion,String padre){
		String[] parameters=new String[]{
				clasificacion,padre
		};
		String q=this.getQuery("delete", parameters);
		return q;
	}
	
	public boolean exist(String id){
		boolean exist=false;
		String[] parameters=new String[]{
				id
		};
		
		String q=this.getQuery("exist", parameters);
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				exist=true;
			}
		}
		return exist;
	}
}
