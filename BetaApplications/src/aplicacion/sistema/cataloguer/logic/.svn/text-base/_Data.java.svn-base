package aplicacion.sistema.cataloguer.logic;


import aplicacion.herramientas.conexion.conectores.MySQL;
import aplicacion.modelo.logic.Data;

public class _Data extends Data {

	public Object[][] getQuery(String idquery){
		Object[][] results=null;
		String q="";
		q+="select idquery,idproveedor,odbc,tabla from b_query ";
		q+="where idquery like '"+idquery+"' ";
		results=getResults(q);
		return results;
	}
	
	
	public String[] getImagenes(String codigo,String linea,String idproveedor){
		String[] imagenes=null;
		String q="";
		q+=" Select codigo from  imagen_catalogo where codigo like '"+codigo+"' and linea like '"+linea+"' and idproveedor like '"+idproveedor+"' ";
		Object[][] results=this.getConnector("MySQL").getResults(q);
		if (results!=null){
			if (results.length>0){
				imagenes=new String[results.length];
				for (int i=0;i<results.length;i++){
					imagenes[i]=results[i][0].toString();
				}
			}
		}
		return imagenes;
	}
	
	public boolean existe_enlace(String codigo1,String linea1,String codigo2,String linea2){
		String q="select * from b_catalogo_equivalencia where ";
		q+="( codigo1 like '"+codigo1+"' and linea1 like '"+linea1+"' and ";
		q+="codigo2 like '"+codigo2+"' and linea2 like '"+linea2+"' )";
		q+=" or ";
		q+="( codigo1 like '"+codigo2+"' and linea1 like '"+linea2+"' and ";
		q+="codigo2 like '"+codigo1+"' and linea2 like '"+linea1+"' )";
		Object[][] results=this.getResults(q);
		boolean existe=false;
		if (results!=null){
			existe=results.length>0;
		}
		return existe;
	}
	
	public String insertar_enlace(String codigo1,String linea1,String codigo2,String linea2){
		String q="";
		q+="insert into b_catalogo_equivalencia (codigo1,linea1,codigo2,linea2) ";
		q+=" values ('"+codigo1+"','"+linea1+"','"+codigo2+"','"+linea2+"')";
		return q;
	}
	
	public String insert_imagen(String codigo,String linea,String idproveedor,String imagen){
		String q="";
		q+=" insert into b_catalogo_imagen (codigo,linea,idproveedor,imagen) values ('"+codigo+"','"+linea+"','"+idproveedor+"','"+imagen+"') ";
		return q;
	}
	
	public Object[][] getFiltros(String idquery){
		String q="";
		q+="select clave,reemplazo from b_catalogo_filtro  ";
		q+="where idquery like '"+idquery+"' ";
		return getResults(q);
	}
	public Object[][] getQueryColumns(String idquery){
		Object[][] results=null;
		String q="";
		q+="select nombre,alias,columna,filtro from b_query_column ";
		q+="where idquery like '"+idquery+"' ";
		System.out.println(q);
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
	
	public String update(String idquery,String codigo,String descripcion,String descripcion1
			,String descripcion2
			,String descripcion3,
			String descripcion4,
			String descripcion5,
			String descripcion6,
			String linea,
			String linea1,
			String marca_vehiculo,
			String vehiculo,
			String categoria1,
			String categoria2,
			String categoria_1,
			String categoria_2,
			String precio,
			String equivalencia1,
			String equivalencia2,
			String equivalencia3,
			String linea_original,
			String codigo_original,
			String linea_reemplazo,
			String codigo_reemplazo,
			String query_equivalencia,
			String tipo_odbc,
			String query_imagen,
			String imagen_codigo,
			String imagen_linea,
			String imagen,
			String imagen_directorio
		){
		String q="";
		q+="update b_query_cataloguer set codigo='"+codigo+"',";
		q+="descripcion='"+descripcion+"',";
		q+="descripcion1='"+descripcion1+"', ";
		q+="descripcion2='"+descripcion2+"', ";
		q+="descripcion3='"+descripcion3+"', ";
		q+="descripcion4='"+descripcion4+"', ";
		q+="descripcion6='"+descripcion5+"', ";
		q+="descripcion5='"+descripcion6+"', ";
		q+="linea='"+linea+"', ";
		q+="linea1='"+linea1+"', ";
		q+="marca_vehiculo='"+marca_vehiculo+"', ";
		q+="vehiculo='"+vehiculo+"', ";
		q+="categoria1='"+categoria1+"', ";
		q+="categoria2='"+categoria2+"', ";
		q+="categoria_1='"+categoria_1+"', ";
		q+="categoria_2='"+categoria_2+"', ";
		q+="precio='"+precio+"', ";
		q+="equivalencia1='"+equivalencia1+"', ";
		q+="equivalencia2='"+equivalencia2+"', ";
		q+="equivalencia3='"+equivalencia3+"', ";
		q+="linea_original='"+linea_original+"', ";
		q+="codigo_original='"+codigo_original+"', ";
		q+="linea_reemplazo='"+linea_reemplazo+"', ";
		q+="codigo_reemplazo='"+codigo_reemplazo+"', ";
		q+="query_equivalencia='"+query_equivalencia+"', ";
		q+="tipo_odbc='"+tipo_odbc+"', ";
		q+="query_imagen='"+query_imagen+"', ";
		q+="imagen_codigo='"+imagen_codigo+"', ";
		q+="imagen_linea='"+imagen_linea+"', ";
		q+="imagen='"+imagen+"', ";
		q+="imagen_directorio='"+imagen_directorio+"' ";
		q+="where idquery like '"+idquery+"' ";
		
		return q;
	}
	
	public String insert(String idquery,String codigo,String descripcion,String descripcion1
			,String descripcion2
			,String descripcion3,
			String descripcion4,
			String descripcion5,
			String descripcion6,
			String linea,
			String linea1,
			String marca_vehiculo,
			String vehiculo,
			String categoria1,
			String categoria2,
			String categoria_1,
			String categoria_2,
			String precio,
			String equivalencia1,
			String equivalencia2,
			String equivalencia3,
			String linea_original,
			String codigo_original,
			String linea_reemplazo,
			String codigo_reemplazo,
			String query_equivalencia,
			String tipo_odbc,
			String query_imagen,
			String imagen_codigo,
			String imagen_linea,
			String imagen,
			String imagen_directorio
			){
		String q="";
		q+="insert into b_query_cataloguer (idquery,codigo,descripcion,descripcion1,descripcion2,descripcion3,descripcion4,descripcion5,descripcion6,linea,linea1,marca_vehiculo,vehiculo,categoria1,categoria2,categoria_1,categoria_2,precio,equivalencia1,equivalencia2,equivalencia3,linea_original,codigo_original,linea_reemplazo,codigo_reemplazo,query_equivalencia,tipo_odbc,query_imagen,imagen_codigo,imagen_linea,imagen,imagen_directorio) ";
		q+="values ('"+idquery+"','"+codigo+"','"+descripcion+"','"+descripcion1+"','"+descripcion2+"','"+descripcion3+"','"+descripcion4+"','"+descripcion5+"','"+descripcion6+"','"+linea+"','"+linea1+"','"+marca_vehiculo+"','"+vehiculo+"','"+categoria1+"','"+categoria2+"','"+categoria_1+"','"+categoria_2+"','"+precio+"','"+equivalencia1+"','"+equivalencia2+"','"+equivalencia3+"','"+linea_original+"','"+codigo_original+"','"+linea_reemplazo+"','"+codigo_reemplazo+"','"+query_equivalencia+"','"+tipo_odbc+"','"+query_imagen+"','"+imagen_codigo+"','"+imagen_linea+"','"+imagen+"','"+imagen_directorio+"') ";
		return q;
	}
	
	public String clearCatalogo(String idproveedor,
			String linea){
			String q="";
			q+="delete from b_catalogo ";
			q+="where  idproveedor ='"+idproveedor+"' and linea='"+linea+"' ";
			return q;
	}
	public String clearCatalogo(String idproveedor){
			String q="";
			q+="delete from b_catalogo ";
			q+="where  idproveedor ='"+idproveedor+"' ";
			return q;
	}	
	
	public String insertCatalogo(String idproveedor,
			String codigo,
			String linea,
			String descripcion,
			String marca_vehiculo,
			String vehiculo,
			String categoria1,
			String categoria2,
			String precio,
			String equivalencia){
		String q="";
		q+="insert into b_catalogo (idproveedor,codigo,linea,descripcion,marca_vehiculo,vehiculo,categoria1,categoria2,precio,equivalencia,fecha) ";
		q+="values ('"+idproveedor+"','"+codigo+"','"+linea+"','"+descripcion+"','"+marca_vehiculo+"','"+vehiculo+"','"+categoria1+"','"+categoria2+"',"+precio+",'"+equivalencia+"',getdate())";
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
	
	public void insert(String idquery,String clave,String reemplazo){
	String q="";
	q+="insert into b_catalogo_filtro (idquery,clave,reemplazo) ";
	q+="values ('"+idquery+"','"+clave+"','"+reemplazo+"')";
	q+="";
	System.out.println(q);
	this.clearBatch();
	this.addBatch(q);
	this.executeBatch();
	}
	
	public void delete(String idquery,String clave){
	String q="";
	q+="delete from b_catalogo_filtro ";
	q+="where idquery like '"+idquery+"' and clave like '"+clave+"' ";
	System.out.println(q);
	this.clearBatch();
	this.addBatch(q);
	this.executeBatch();
	}
	
	public boolean existe(String idquery,String clave){
		boolean existe=false;
		String q="select * from b_catalogo_filtro ";
		q+="where idquery like '"+idquery+"' and clave like '"+clave+"' ";
		Object[][] results=this.getResults(q);
		if (results!=null){
			existe=results.length>0;
		}
		return existe;
	}
	
}
