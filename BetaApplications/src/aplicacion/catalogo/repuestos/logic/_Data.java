package aplicacion.catalogo.repuestos.logic;

import aplicacion.modelo.logic.Data;

public class _Data extends Data {

	public String getUpdate(String newcode,String oldcode){
		String[] parameters=new String[]{
				newcode,oldcode
		};
		String q=this.getQuery("update", parameters);
		return q;
	}
	public String getInsertClasificacion(String id,String dato,String nota){
		String[] parameters=new String[]{
				id,dato,nota
		};
		String q=this.getQuery("insert_clasificacion", parameters);
		return q;
	}
	public String getDeleteClasificacion(String id){
		String[] parameters=new String[]{
				id
		};
		String q=this.getQuery("delete_clasificacion", parameters);
		return q;
	}
	public String getUpdatePadre(String newcode,String oldcode){
		String[] parameters=new String[]{
				newcode,oldcode
		};
		String q=this.getQuery("update_padre", parameters);
		return q;
	}
	
	public String getId(String clasificacion,String padre){
		String[] parameters=new String[]{
				clasificacion,padre
		};
		String q=this.getQuery("id", parameters);
		return q;
	}
	private String _getTecnica(String clasificacion){
		String[] parameters=new String[]{
				clasificacion
		};
		String q=this.getQuery("tecnica", parameters);
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
	public Object[][] getTecnica(String id){
		String q=this._getTecnica(id);
		Object[][] results=this.getResults(q);
		return results;
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
