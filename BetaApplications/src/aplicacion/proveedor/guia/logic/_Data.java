package aplicacion.proveedor.guia.logic;

import aplicacion.modelo.logic.Data;

public class _Data extends Data {
	
	

	public String[] cargar_simulacion(String idproveedor){
		String[] simulacion_base=null;
		String q="select idclasificacion from b_proveedor_rubros ";
		q+="where idproveedor like '"+idproveedor+"' ";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				simulacion_base=new String[results.length];
				for (int i=0;i<results.length;i++){
					simulacion_base[i]=(String)results[i][0];	
				}
			}
		}
		return simulacion_base;
	}
	
	public Object[][] getProveedorInformation(String proveedor){
		String q="select descripcion from ma_cuentas where codigo = '"+proveedor+"'"; 
		Object[][] results=getResults(q);
		return results;
	}
	
	public String getDeleteClasificacion(String idproveedor){
		String q="delete from b_proveedor_rubros ";
		q+="where idproveedor like '"+idproveedor+"' ";
		return q;
	}
	
	public String getInsertClasificacion(String idproveedor,String idclasificacion){
		String q="insert b_proveedor_rubros (idproveedor,idclasificacion) ";
		q+="values ('"+idproveedor+"','"+idclasificacion+"') ";
		return q;
	}
}
