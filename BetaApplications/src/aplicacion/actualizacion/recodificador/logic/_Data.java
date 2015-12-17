package aplicacion.actualizacion.recodificador.logic;

import aplicacion.modelo.logic.Data;

public class _Data extends Data {

	public Object[][] getQuery(String desde,String hasta) {
		
		String q = "";
		q += "select idarticulo,descripcion ";
		q += "from v_ma_articulos ";
		q += "where idarticulo between ";
		q += "'" + desde + "' and ";
		q += "'" + hasta + "' ";
		q += "order by idarticulo ";
		q += "";
		q += "";
		q += "";
		return this.getResults(q);
	}
	
	public boolean exist_article(String idarticulo) {
		boolean exist = false;
		String q = "select ";
		q += "idarticulo ";
		q += "from v_ma_articulos ";
		q += "where idarticulo like '" + idarticulo + "'";
		Object[][] results = getResults(q);
		if (results != null) {
			if (results.length > 0) {
				exist = true;
			}
		}
		return exist;
	}

	public void recodeArticle(String article, String new_article) {
		article = article.replace("%", "");
		new_article = new_article.replace("%", "");
		String q = "";
		q += "update v_ma_articulos set idarticulo='" + new_article + "'";
		q += "where idarticulo like '" + article + "' ";
		q += "";
		q += "";
		q += "";
	}
	
	public String getUpdateQuery(String tabla, String idarticulo, String nuevo) {
		String q = "update " + tabla + " set idarticulo='" + nuevo
				+ "' where idarticulo like '" + idarticulo + "' ";
		return q;
	}

	public String getDeleteQuery(String tabla, String nuevo) {
		String q = "delete from  " + tabla + " where idarticulo like '" + nuevo+"' ";
				
		return q;
	}
	public boolean existeNuevo(String tabla, String nuevo) {
		boolean ok=false;
		String q = "select  * from " + tabla + " where idarticulo like '" + nuevo
				+ "'  ";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				ok=true;
			}
		}
		return ok;
	}
	
	public String constraints_deactivate(String table) {
		String q = "alter table " + table + " nocheck constraint all";
		return q;
	}

	public String delete_unidad(String idarticulo) {
		String q = "delete from s_ta_equiv where idarticulo like '"
				+ idarticulo + "' ";
		return q;
	}

	public String constraints_activate(String table) {
		String q = "alter table " + table + " check constraint all";
		return q;
	}

	public void elimininar_articulo(String idarticulo) {
		String q = "";
		q += "delete from v_ma_articulos where idarticulo like '" + idarticulo
				+ "' ";
		q += "";
		clearBatch();
		addBatch(this.constraints_deactivate("v_ma_articulos"));
		addBatch(q);
		addBatch(this.constraints_activate("v_ma_articulos"));
		executeBatch();

	}

	public String trigger_disable(String table) {
		String q = "alter table " + table + " disable trigger all";
		return q;
	}

	public String trigger_enable(String table) {
		String q = "alter table " + table + " enable trigger all";
		return q;
	}

	}
