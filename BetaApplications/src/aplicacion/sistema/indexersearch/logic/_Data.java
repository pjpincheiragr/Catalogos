package aplicacion.sistema.indexersearch.logic;


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
	public String getDelete(String titulo){
		String q="delete from pdf where title like '"+titulo+"' ";
		return q;
	}
	
	private String getWhereString(String descript) {
		String description = "";

		
		String aux = descript;
		while (aux.contains(" ")) {
			String sub = aux.substring(0, aux.indexOf(" "));
			if (sub.compareTo("") != 0) {
				if (description.length() > 0) {
					description = description + " ";
				}
				description = description + "  +" + sub + "* ";
			}
			aux = aux.substring(aux.indexOf(" ") + 1);
		}
		if (aux.compareTo("") != 0) {
			if (description.length() > 0) {
				description = description + " ";
			}
			description = description + " +" + aux + "* ";
		}
		String column = "MATCH (content,title,resumen,linea) AGAINST ('"+description+"' in boolean mode); ";
		return column;
	}
	public String getSearch(String busqueda){
		String q="";
		q+="select idproveedor,linea,filename,page from pdf ";
		q+="where "+this.getWhereString(busqueda);
		return q;
	}
	
	public Object[][] buscar(String busqueda){
		String q=this.getSearch(busqueda);
		System.out.println(q);
		return this.getConnector("MySQL").getResults(q);
	}
	

     /** devuelve la cadena de insercion para la carga de los indices en la base
	 * file: nombre del archivo 
	 * title: titulo
	 * content: el contenido que obtengo del pdf en cada pagina
	 * Index es el contenido extra que define lo que contiene en general el Archivo
	 * 
	 */
	public String getInsert(String file,String title,int page,String content,String index,String idproveedor,String linea){
		String q="";
		q+="insert into pdf (filename,title,page,content,resumen,idproveedor,linea) ";
		q+="values ('"+file+"','"+title+"',"+page+",'"+content+"','"+index+"','"+idproveedor+"','"+linea+"')";
		System.out.println(q);
		return q;
	}
	
}
