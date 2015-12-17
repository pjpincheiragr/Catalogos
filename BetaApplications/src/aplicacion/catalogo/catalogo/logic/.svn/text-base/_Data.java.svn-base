package aplicacion.catalogo.catalogo.logic;

import aplicacion.modelo.logic.Data;

public class _Data extends Data {

	public String getQueryArticulos(){
		String q=this.getMessage("consulta_articulos");
		return q;
	}
	
	public boolean existeArticulo(String idarticulo){
		boolean existe=false;
		Object[][] results=this.getArticulo(idarticulo);
		if (results!=null){
			if (results.length>0){
				existe=true;
			}
		}
		return existe;
	}
	
	public Object[][] getArticulo(String idarticulo){
		String q="";
		q+="select descripcion,precio2,costo,descripabrev from v_ma_articulos "; 
		q+="where idarticulo like '"+idarticulo+"' ";
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
	}
	
	public String getInsert(String idarticulo,String aplicacion,String clasificacion,String descripcion){
		String 	q="";
		q+="insert into b_articulos_aplicacion ";
		q+="(aplicacion,idarticulo,clasificacion,descripcion,cantidad) ";
		q+="values ('"+aplicacion+"',";
		q+="'"+idarticulo+"',";
		q+="'"+clasificacion+"',";
		q+="'"+descripcion+"',1)";
		return q;
		
	}
	
	
	public String getDeleteClasificacion(String idarticulo,String clasificacion,String aplicacion){
		String q="delete from ";
		q+=" b_articulos_aplicacion ";
		q+=" where aplicacion like '"+aplicacion+"' ";
		q+=" and idarticulo like '"+idarticulo+"' ";
		q+=" and clasificacion like '"+clasificacion+"' ";
		return q;
	}
	
}
