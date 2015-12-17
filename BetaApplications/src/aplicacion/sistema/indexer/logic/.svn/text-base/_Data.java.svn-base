package aplicacion.sistema.indexer.logic;


import aplicacion.herramientas.conexion.conectores.MySQL;
import aplicacion.modelo.logic.Data;

public class _Data extends Data {

	public Object[][] getArchivo(String file){
		Object[][] results=null;
		String q="";
		q+="select top 1 filename,index,linea,idproveedor from pdf ";
		q+="where titulo like '"+file+"' ";
		results=getResults(q);
		return results;
	}
	public String getDelete(String pdf){
		String q="delete from pdf where filename like '"+pdf+"' ";
		return q;
	}
	
	public String getDeleteSplit(String pdf){
		String q="delete from pdf_split where filename like '"+pdf+"' ";
		return q;
	}
	
	public String getDeleteFile(String pdf){
		String q="delete from pdf_archivo where filename like '"+pdf+"' ";
		return q;
	}
	
	private String getProveedorQuery(String idproveedor){
		String q="";
		String[] parameters=new String[] {
				idproveedor	
			};
		q=this.getQuery("proveedor", parameters);
		return q;
	}
	public Object[][] getProveedor(String idproveedor){
		String q=this.getProveedorQuery(idproveedor);
		System.out.println(q);
		Object[][] results=this.getResults(q);
		return results;
	}


     /** devuelve la cadena de insercion para la carga de los indices en la base
	 * file: nombre del archivo 
	 * title: titulo
	 * content: el contenido que obtengo del pdf en cada pagina
	 * Index es el contenido extra que define lo que contiene en general el Archivo
	 * 
	 */
	public String getInsert(String file,int page,String content,String index,String idproveedor,String linea){
		String q="";
		q+="insert into pdf (filename,page,content,resumen,idproveedor,linea) ";
		q+="values ('"+file+"',"+page+",'"+content+"','"+index+"','"+idproveedor+"','"+linea+"')";
		System.out.println(q);
		return q;
	}
	
}
