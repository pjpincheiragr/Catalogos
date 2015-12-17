package aplicacion.actualizacion.codigo.logic;

import aplicacion.modelo.logic.Data;
/**
 * @author Agustin Wisky
 * @company Wismi S.A.
 * @since 10-10-2009
 */
public class _Data extends Data {

	public boolean UpdatePolitics(String codigo,String linea,String idproveedor,String politicaprecio){
		String q="";
		q+="update b_codigos set politicaprecio='"+politicaprecio+"' ";
		q+="where idcodigo like '"+codigo+"' and ";
		q+=" lineaproveedor like '"+linea+"' and ";
		q+=" idproveedor like '"+idproveedor+"' ";
		clearBatch();
		addBatch(q);
		boolean error=executeBatch();
		return error;
	}
	
	private String getPoliticaQuery(String codigo){
		String q="";
		q+="select "; 
		q+="codigo,descripcion,fcosto,fclase2 "; 
		q+="from v_ta_politicaprecios ";
		q+="where codigo like '"+codigo+"'";
		return q;	
	}
	
	public Object[][] getPolitica(String codigo){
		String q=this.getPoliticaQuery(codigo);
		return getResults(q);
	}
	public boolean check_politica(String codigo){
		Object[][] results=this.getPolitica(codigo);
		boolean exist=false;
		if (results!=null){
			if (results.length>0){
				exist=true;
			}
		}
		return exist;
		}
	public String getInsert(String idcodigo,String linea,String idproveedor,String descripcion,String precio,String politica){
		String q="";
		q+="insert into b_codigos (idcodigo,lineaproveedor,idproveedor,descripcion,precio5,politica) values (";
		q+="'"+idcodigo+"',";
		q+="'"+linea+"',";
		q+="'"+idproveedor+"',";
		q+="'"+descripcion+"',";
		q+=""+precio+",";
		q+="'"+politica+"')";
		return q;
	}
	
	public String getUpdate(String idcodigo,String linea,String idproveedor,String descripcion,String precio,String politica){
		String q="";
		q+="update b_codigos set ";
		q+="descripcion='"+descripcion+"',";
		q+="precio5="+precio+",";
		q+="politicaprecio='"+politica+"' ";
		q+="where idcodigo like '"+idcodigo+"' ";
		q+="and lineaproveedor like '"+linea+"' ";
		q+="and idproveedor like '"+idproveedor+"' ";
		return q;
	}
	
	public boolean check_politics_existence(String codigo){
		boolean exist=false;
		String q="";
		q+="select * from v_ta_politicaprecios ";
		q+="where codigo like '"+codigo+"'";
		q+="";
		Object[][] results=getResults(q);
		if (results!=null){
			if (results.length>0){
				exist=true;
			}
		}
		return exist;
	}
	
	private String getCodeQuery(String idproveedor,String linea,String idcodigo) {
		String q = "";
		q = q + "select top 1 idcodigo ";
		q = q + "FROM b_codigos ";
		q = q + "where idproveedor like '"+idproveedor+"' ";
		q = q + "and lineaproveedor like '"+linea+"'";
		q = q + "and idcodigo like '"+idcodigo+"'";
		System.out.println(q);
		return q;
	}


	public boolean check_proveedor(String idproveedor){
		boolean exist=false;
		Object[][] results = getResults(this.getProvQuery(idproveedor));
		if (results != null) {
			if (results.length > 0) {
			exist=true;
			}
		}
		return exist;
	}



	public boolean check_linea(String idproveedor,String linea){
		boolean exist=false;
		Object[][] results = getResults(this.getLineQuery(idproveedor,linea));
		if (results != null) {
			if (results.length > 0) {
			exist=true;
			}
		}
		return exist;
	}

	public Object[][] getProveedor(String code){
		Object[][] results=getResults(this.getProvQuery(code));
		return results;
	}

	private String getProvQuery(String code) {
		String q = "";
		q = q + "select M.codigo,M.descripcion,";
		q = q + "isnull(a.telefono,''),";
		q = q + "isnull(a.localidad,''),";
		q = q + "isnull(a.observaciones,'')";
		q = q + "FROM MA_CUENTAS M LEFT OUTER JOIN ";
		q = q + "MA_CUENTASADIC a ON ";
		q = q + "M.CODIGO = a.CODIGO ";
		q = q + "where M.codigo like '" + code + "'";
		return q;
	}

	private String getLineQuery(String idproveedor,String linea) {
		String q = "";
		q = q + "select top 1 idcodigo ";
		q = q + "FROM b_codigos ";
		q = q + "where idproveedor like '"+idproveedor+"' ";
		q = q + "and lineaproveedor like '"+linea+"'";
		
		return q;
	}
	public boolean check_code(String idproveedor,String linea,String codigo){
		boolean exist=false;
		Object[][] results = this.getResults(this.getCodeQuery(idproveedor,linea,codigo));
		if (results != null) {
			if (results.length > 0) {
			exist=true;
			}
		}
		return exist;
	}
	
	public boolean check_alias_existence(String idcodigo,
			String lineaproveedor,String idproveedor){
		boolean exist=false;
		String q="";
		q+="select * from b_alias ";
		q+="where idcodigo like '"+idcodigo+"'";
		q+=" and idproveedor like '"+idproveedor+"' ";
		q+=" and lineaproveedor like '"+lineaproveedor+"' ";
		q+="";
		Object[][] results=getResults(q);
		if (results!=null){
			if (results.length>0){
				exist=true;
			}
		}
		return exist;
	}

	

	public String getBorrarAlias(String idcodigo,
			String lineaproveedor,String idproveedor){
		boolean exist=false;
		String q="";
		q+="delete from b_alias ";
		q+="where idcodigo like '"+idcodigo+"'";
		q+=" and idproveedor like '"+idproveedor+"' ";
		q+=" and lineaproveedor like '"+lineaproveedor+"' ";
		q+="";
		return q;
	}

	public String getBorrarCodigo(String idcodigo,
			String lineaproveedor,String idproveedor){
		boolean exist=false;
		String q="";
		q+="delete from b_codigos ";
		q+="where idcodigo like '"+idcodigo+"'";
		q+=" and idproveedor like '"+idproveedor+"' ";
		q+=" and lineaproveedor like '"+lineaproveedor+"' ";
		q+="";
		return q;
	}
	
	public String getQuery(String desde,String hasta,String idproveedor,String linea){
		String q="";
		q+="SELECT IDCODIGO,DESCRIPCION,PRECIO5,ULTIMO_UPD,LINEAPROVEEDOR,politicaprecio ";
		q+="FROM B_CODIGOS  ";
		q+="WHERE IDPROVEEDOR LIKE '"+idproveedor+"' ";
		if (linea.compareTo("")!=0){
			q+="AND LINEAPROVEEDOR LIKE '"+linea+"' ";	
		}
		if (desde.compareTo("")!=0 & hasta.compareTo("")!=0){
			q+="AND IDCODIGO between '"+desde+"' and '"+hasta+"' ";	
		}
		q+="order by lineaproveedor,idcodigo ";
		q+="";
		q+="";
		q+="";
		
		
		
		return q;
	}
}
