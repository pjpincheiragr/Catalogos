package aplicacion.actualizacion.archivo.logic;

import aplicacion.modelo.logic.Data;
/**
 * @author Agustin Wisky
 * @company Wismi S.A.
 * @since 10-10-2009
 */
public class _Data extends Data {
	String tc="updf";
	
	public String getProximoPGCorrecto(String tc){
		String prox="";
		prox=this.getProximoPG_Ceros(tc);
		return prox;
	}
	
	public boolean updateFecha(String codigo,String linea,String idproveedor,String idcomprobante){
 		linea=linea.replaceAll("'", "");
 		codigo=codigo.replaceAll("'", "");
 		String q="";
 		q+="update b_codigos set ";
 		q+="ultimo_upd=getDate(),tc='UPDF',idcomprobante='"+idcomprobante+"' ";
 		q+="where idcodigo like '"+codigo+"' and ";
 		q+="Lineaproveedor like '"+linea+"' and ";
 		q+="idproveedor = '"+idproveedor+"' ";
 		//System.out.println(q);
 		clearBatch();
 		addBatch(q);
 		return executeBatch();
 	}
 	
	
	public String getInsertCodigosVariacion(String codigo,String linea,String idproveedor,double precio5,double old,String idcomprobante){
		String q="insert into b_codigos_variacion (idcodigo,lineaproveedor,idproveedor,";
		q+="precio5,old,fecha,tc,idcomprobante) values (";
		q+="'"+codigo+"','"+linea+"','"+idproveedor+"',";
		q+=precio5+","+old+",getdate(),'"+tc+"','"+idcomprobante+"')";
		return q;
	}
	private int getProximoPG(String tc){
		int c=0;
		c=this.getProximoTC(""+tc+"");
		return c;
	}
	
	private int getProximoTC(String tc){
		int c=0;
		String q="";
		q+="select x_ultimo_nro from b_ta_cpte ";
		q+="where codigo = '"+tc+"' ";
		Object[][] aux=connection_handler.getDefaultConnector().getResults(q);
		try{
			c=new Integer(aux[0][0].toString());
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return c;
	}
	
	private String getProximoPG_Ceros(String tc){
		String c="";
		int i=this.getProximoPG(tc);
		String aux=""+i;
		while (aux.length()<8){
			aux="0"+aux;
		}
		c=aux;
		return c;
	}
	public boolean updateTC(String tc){
		String q="";
		q+="update b_ta_cpte set x_ultimo_nro = x_ultimo_nro + 1 where codigo like '"+tc+"'";
		this.clearBatch();
		this.addBatch(q);
		return this.executeBatch();
		
	}
	
	public String getUpdateQuery(String idproveedor,String code,String linea,String pol,String iduser){
    	String q="";
    	q=q+" update v set "; 
    	q=q+" v.precio5=c.precio5, ";
    	q=q+" v.costo=p.mcosto*c.precio5,v.precio2=p.mclase2*p.mcosto*c.precio5, ";
    	q+=" v.iduser_actualizador='"+iduser+"', ";
    	q+=" v.actualizacion=getdate() ";
    	q=q+" from ";
    	q=q+" b_codigos c join b_alias a ";
    	q=q+" on c.idcodigo=a.idcodigo and c.lineaproveedor=a.lineaproveedor and c.idproveedor=a.idproveedor ";
    	q=q+" join v_ma_articulos v on a.idarticulo=v.idarticulo ";
    	q=q+" left outer join v_ta_politicaprecios p on (case when isnull(v.politicaprecios,'') like '' then '"+pol+"' else v.politicaprecios end)=p.codigo ";
    	q=q+" where c.idproveedor like '"+idproveedor+"'and v.cuentaproveedor like '"+idproveedor+"'  ";
    	q=q+" and c.idcodigo like '"+code+"' ";
    	q=q+" and c.lineaproveedor like '"+linea+"' ";
    	return q;
    }
	
	public String getInsertQuery(String idproveedor,String codigo,String linea,String precio,String descripcion){
		
    	String q="";
    	
    	q=q+" insert into b_codigos (idcodigo,idproveedor,lineaproveedor,precio5,descripcion,ULTIMO_UPD)";
    	q=q+" values (";
    	q=q+" '"+codigo+"',";
    	q=q+" '"+idproveedor+"',";
    	q=q+" '"+linea+"',";
    	q=q+" "+precio+",";
    	q=q+" '"+descripcion+"',";
    	q=q+" GETDATE())";
    	
    	return q;
    }
	
	public double getPrecioViejo(String idproveedor,String code,String linea){
    	double precio=0.0;
    	String q="SELECT PRECIO5 ";
    	q=q+" FROM B_CODIGOS ";
    	q=q+" WHERE IDPROVEEDOR LIKE '"+idproveedor+"' ";
    	q=q+" and idcodigo like '"+code+"' ";
    	q=q+" and lineaproveedor like '"+linea+"' ";
    	Object[][] results=getResults(q);
    	if (results!=null){
    		if (results.length>0){
    			try {
					precio=new Double((String)results[0][0]);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
    	return precio;
    }
	public boolean check_politics_existence(String codigo){
		boolean exist=false;
		String q="";
		q+="select codigo from v_ta_politicaprecios ";
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
	private String getLineQuery(String idproveedor,String linea) {
		String q = "";
		q = q + "select top 1 idcodigo ";
		q = q + "FROM b_codigos ";
		q = q + "where idproveedor like '"+idproveedor+"' ";
		q = q + "and lineaproveedor like '"+linea+"'";
		
		return q;
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

	public boolean exist(String codigo,String lineaproveedor,String idproveedor){
		
 		lineaproveedor=lineaproveedor.replaceAll("'", "");
 		codigo=codigo.replaceAll("'", "");
		String q="select idcodigo from b_codigos ";
		q=q+" where idcodigo like '"+codigo+"' ";
		q=q+" and lineaproveedor like '"+lineaproveedor+"' ";
		q=q+" and idproveedor like '"+idproveedor+"' ";
		Object[][] results=getResults(q);
		boolean b=false;
		if (results!=null){
			if (results.length>0){
				b=true;
			}
		}
		return b;
	}
	public boolean update_pol(String idproveedor,String code,String linea,String pol,String iduser){
		String q="";
		q=this.getUpdateQuery(idproveedor, code, linea, pol,iduser);
		clearBatch();
		addBatch(q);
		return executeBatch();
	}
	
	  public String getUpdatePrices(String idproveedor,String pol,String tc,String idcomprobante,String iduser){
	      	String q="";
	    	q+="update v set v.precio5=c.precio5, v.costo=p.mcosto*c.precio5,v.precio2=p.mclase2*p.mcosto*c.precio5, ";
	     	q+=" v.iduser_actualizador='"+iduser+"',actualizacion=getdate() ";
	      	q+="from  ";
	      	q+="b_codigos c join b_alias a "; 
	      	q+="on c.idcodigo=a.idcodigo and c.lineaproveedor=a.lineaproveedor and c.idproveedor=a.idproveedor "; 
	      	q+="join v_ma_articulos v on a.idarticulo=v.idarticulo  ";
	      	q+="left outer join v_ta_politicaprecios p on (case when isnull(c.politicaprecio,'') like '' then isnull(v.politicaprecios,'"+pol+"') else v.politicaprecios end)=p.codigo "; 
	      	q+="where c.idproveedor like '"+idproveedor+"' and v.cuenta_actualizacion like '"+idproveedor+"' and c.tc like '"+tc+"' and c.idcomprobante like '"+idcomprobante+"' ";
	      	return q;
	      }
	  public String getInsertQuery(String idproveedor,String codigo,String linea,double precio,String descripcion,String politicaprecio,String idcomprobante){
	     	String q="";
	     	
	     	q+=" insert into b_codigos (idcodigo,idproveedor,lineaproveedor,precio5,descripcion,politicaprecio,tc,idcomprobante,ultimo_upd)";
	     	q+=" values (";
	     	q+=" '"+codigo+"',";
	     	q+=" '"+idproveedor+"',";
	     	q+=" '"+linea+"',";
	     	q+=" "+precio+",";
	     	q+=" '"+descripcion+"',";
	     	q+="'"+politicaprecio+"','"+tc+"','"+idcomprobante+"',getdate())";
	     	
	     	return q;
	     }
 	
	
 	public String getInsert(String idproveedor,String codigo,String linea,double precio,double old,String descripcion,String idcomprobante,String politicaprecio){
 		String q="";
 		descripcion=descripcion.replaceAll("'", "");
 		linea=linea.replaceAll("'", "");
 		codigo=codigo.replaceAll("'", "");
 		
 		q=this.getInsertQuery(idproveedor, codigo, linea, precio, descripcion,politicaprecio,idcomprobante);
 		return q;
 	}
	
 	public String getUpdate(double precio,double old,String codigo,String linea,String idproveedor,String idcomprobante){
 		linea=linea.replaceAll("'", "");
 		codigo=codigo.replaceAll("'", "");
 		String q="";
 		q+="update b_codigos set ";
 		q+="precio5="+precio+",";
 		q+="ultimo_upd=getDate(),tc='"+tc+"',idcomprobante='"+idcomprobante+"' ";
 		q+="where idcodigo like '"+codigo+"' and ";
 		q+="Lineaproveedor like '"+linea+"' and ";
 		q+="idproveedor = '"+idproveedor+"' ";
 		return q;
 		
 	}
 	
 	public String getUpdateFecha(String codigo,String linea,String idproveedor,String idcomprobante){
 		linea=linea.replaceAll("'", "");
 		codigo=codigo.replaceAll("'", "");
 		String q="";
 		q+="update b_codigos set ";
 		q+="ultimo_upd=getDate(),tc='"+tc+"',idcomprobante='"+idcomprobante+"' ";
 		q+="where idcodigo like '"+codigo+"' and ";
 		q+="Lineaproveedor like '"+linea+"' and ";
 		q+="idproveedor = '"+idproveedor+"' ";
 		//System.out.println(q);
 		return q;
 	}
	
	
	public Object[][] getProveedor(String codigo){
		String q="";
		q=q+" select m.codigo,m.descripcion,isnull(c.politica_default,''),isnull(p.descripcion,''),isnull(c.odbc_name,''),isnull(c.consulta,''),isnull(c.linea,'') ";
		q=q+" from ma_cuentas m left outer join b_configuracion_catalogos c ";
		q=q+" left outer join v_ta_politicaprecios p ";
		q=q+" on c.politica_default=p.codigo ";
		q=q+" on m.codigo=c.idproveedor ";
		q=q+" where m.codigo like '"+codigo+"' ";
		Object[][] results=getResults(q);
		return results;
	}
	
	 public String updatediferences(String idproveedor,String pol){
	     	String q="";
	     	q=q+"select v.idarticulo,c.precio5,v.precio5 ";
	     	q=q+"from  ";
	     	q=q+"b_codigos c join b_alias a "; 
	     	q=q+"on c.idcodigo=a.idcodigo and c.lineaproveedor=a.lineaproveedor and c.idproveedor=a.idproveedor "; 
	     	q=q+"join v_ma_articulos v on a.idarticulo=v.idarticulo  ";
	     	q=q+"left outer join v_ta_politicaprecios p on (case when isnull(c.politicaprecio,'') like '' then isnull(v.politicaprecios,'"+pol+"') else v.politicaprecios end)=p.codigo "; 
	     	q=q+"where c.idproveedor like '"+idproveedor+"'  and v.cuentaproveedor like '"+idproveedor+"'  ";
	     	q=q+"and abs(c.precio5-v.precio5)>0.01 ";
	     	return q;
	     }
	     
	     public String updatefechas(String idproveedor,String linea,String pol){
	     	String q="";
	     	q=q+"update b_codigos set ultimo_upd=getdate()  ";
	     	q=q+"from  ";
	     	q=q+"b_codigos c join b_alias a "; 
	     	q=q+"on c.idcodigo=a.idcodigo and c.lineaproveedor=a.lineaproveedor and c.idproveedor=a.idproveedor "; 
	     	q=q+"join v_ma_articulos v on a.idarticulo=v.idarticulo  ";
	     	q=q+"left outer join v_ta_politicaprecios p on (case when isnull(c.politicaprecio,'') like '' then isnull(v.politicaprecios,'"+pol+"') else v.politicaprecios end)=p.codigo "; 
	     	q=q+"where c.idproveedor like '"+idproveedor+"'  and c.lineaproveedor like '"+linea+"'   and v.cuentaproveedor like '"+idproveedor+"'  ";
	     	q=q+"and abs(c.precio5-v.precio5)<=0.01 ";
	     	return q;
	     }
	     
	     public String _updateArticle(String idarticulo,String idproveedor,String pol,String precio){
	     	String q="";
	     	q=q+"update v set v.precio5="+precio+", v.costo=p.mcosto*"+precio+",v.precio2=p.mclase2*p.mcosto*"+precio+" "; 
	     	q=q+"from v_ma_articulos v  ";
	     	q=q+"left outer join v_ta_politicaprecios p on (case when isnull(v.politicaprecios,'') like '' then '"+pol+"' else v.politicaprecios end)=p.codigo "; 
	     	q=q+"where v.cuentaproveedor like '"+idproveedor+"' ";
	     	q=q+"and v.idarticulo like '"+idarticulo+"' ";
	     	return q;
	     }
}
