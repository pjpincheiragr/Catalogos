package aplicacion.inventario.equivalencia.logic;

import aplicacion.herramientas.java.*;
import aplicacion.modelo.logic.Data;

public class _Data extends Data {

	public boolean check_alias_difference(String idarticulo,String idcodigo,
			String lineaproveedor,String idproveedor,double factor){
			
			boolean exist=false;
			String q="";
			q+=" select TOP 1 * from b_alias ";
			q+=" where idarticulo like '"+idarticulo+"'";
			q+=" and idcodigo like '"+idcodigo+"'";
			q+=" and lineaproveedor like '"+lineaproveedor+"' ";
			q+=" and idproveedor like '"+idproveedor+"' ";
			Object[][] results=getResults(q);
			if (results!=null){
				if (results.length>0){
					exist=true;
				}
			}
			return exist;
		}
			
			
		public boolean check_alias_existence(String idarticulo,String idcodigo,
				String lineaproveedor,String idproveedor,double factor){
			boolean exist=false;
			String q="";
			q+="select TOP 1 * from b_alias ";
			q+="where idarticulo like '"+idarticulo+"'";
			q+=" and idproveedor like '"+idproveedor+"' ";
			q+="";
			Object[][] results=getResults(q);
			if (results!=null){
				if (results.length>0){
					exist=true;
				}
			}
			return exist;
		}
		
		public String getDefaultProveedor(String idarticulo){
			String q="";
			q+="select top 1 ltrim(rtrim(idproveedor)) ";
			q+="from b_alias ";
			q+="where idarticulo like '"+idarticulo+"' ";
			q+="group by ltrim(rtrim(idproveedor)) ";
			q+="order by count(idarticulo) desc";
			String idproveedor="";
			Object[][] results=this.getResults(q);
			if (results!=null){
				if (results.length>0){
					idproveedor=(String) results[0][0];
				}
			}
			return idproveedor;
		}
		
		public String getDefaultLinea(String idarticulo,String idproveedor){
			String q="";
			q+="select top 1 ltrim(rtrim(lineaproveedor)) ";
			q+="from b_alias ";
			q+="where idarticulo like '"+idarticulo+"' ";
			q+="and idproveedor like '"+idproveedor+"' ";
			q+="group by ltrim(rtrim(lineaproveedor)) ";
			q+="order by count(idarticulo) desc";
			String linea="";
			Object[][] results=this.getResults(q);
			if (results!=null){
				if (results.length>0){
					linea=(String) results[0][0];
				}
			}
			return linea;
		}
		
		public String getInsertarAlias(String idarticulo,String idcodigo,
				String lineaproveedor,String idproveedor,String linea,double factor){
			String q="";
			q+="insert into b_alias ";
			q+="(idarticulo,idcodigo,idproveedor,linea,lineaproveedor,factor) ";
			q+="values (";
			q+="'"+idarticulo+"',";
			q+="'"+idcodigo+"',";
			q+="'"+idproveedor+"',";
			q+="'"+linea+"',";
			q+="'"+lineaproveedor+"',";
			q+=""+factor+")";
			q+="";
			return q;
		}
		
		public String getActualizarAlias(String idarticulo,String idcodigo,
				String lineaproveedor,String idproveedor,double factor){
			String q="";
			q+="update b_alias ";
			q+="set idcodigo='"+idcodigo+"',";
			q+="lineaproveedor='"+lineaproveedor+"',";
			q+="factor="+factor+" ";
			q+="where idarticulo like '"+idarticulo+"'";
			q+="and idproveedor like '"+idproveedor+"'";
			q+="";
			return q;
		}
		
		public String getBorrarAlias(String idarticulo,String idcodigo,
				String lineaproveedor,String idproveedor,double factor){
			String q="";
			q+=" delete from b_alias ";
			q+="where idarticulo like '"+idarticulo+"' ";
			q+="and idcodigo like '"+idcodigo+"' ";
			q+="and lineaproveedor like '"+lineaproveedor+"' ";
			q+="and idproveedor like '"+idproveedor+"'";
			return q;
		}
		public Object[][] getLinea(String prefijo){
			String q="select top 1 descripabrev from ";
			q+="v_ma_articulos where substring(idarticulo,0,4) like '"+prefijo+"' ";
			Object[][] results=getResults(q);
			return results;
		}
		
		public Object[][] getAlias(String idproveedor,String desde,String hasta){
			String q=this.getQueryAlias(idproveedor, desde, hasta);
			Object[][] results=getResults(q);
			return results;
		}
		
		public Object[][] getArticulos(String desde,String hasta,String linea){
			String q=this.getQueryArticulos( desde, hasta,linea);
			Object[][] results=getResults(q);
			return results;
		}
		public boolean check_proveedor(String idproveedor){
			boolean exist=false;
			String q = "";
			q+="select M.codigo,M.descripcion,";
			q+="isnull(a.telefono,''),";
			q+="isnull(a.localidad,''),";
			q+="isnull(a.observaciones,'')";
			q+="FROM MA_CUENTAS M LEFT OUTER JOIN ";
			q+="MA_CUENTASADIC a ON ";
			q+="M.CODIGO = a.CODIGO ";
			q+="where M.codigo like '" + idproveedor + "'";
			Object[][] results = getResults(q);
			if (results != null) {
				if (results.length > 0) {
				exist=true;
				}
			}
			return exist;
		}
		
		public String getQueryAlias(String idproveedor,String desde,String hasta){
			
			String linea="";
			String descripcion="";
			String q="select top 10000 a.idarticulo,isnull(G.descripcion,''),isnull(P.idcodigo,'') as codigo,";
			q+=" isnull(P.precio5,0)as Lista,isnull(A.factor,0) as Factor,isnull(G.precio5,0.0),isnull(G.descripabrev,'')as Linea,";
			q+=" isnull(P.idproveedor,'')as idproveedor,A.lineaproveedor,isnull(P.ultimo_upd,'') ";
			q+=" from   b_alias A left outer join v_ma_articulos G  ";
			q+=" on A.idarticulo=G.idarticulo  ";
			if (idproveedor.compareTo("")!=0){
				q+=" and isnull(A.idproveedor,'') like '%"+idproveedor+"%'";	
			}
			q+=" left outer join b_codigos P  ";
			q+=" on A.idcodigo=P.idcodigo and A.idproveedor=P.idproveedor and A.lineaproveedor=P.lineaproveedor ";
			q+=" where  ";
			q+=" A.idArticulo between '"+desde+"' and  '"+hasta+"' ";
			q+=" and isnull(G.descripabrev,'') like '%"+linea+"%'";
			q+=" and isnull(G.Descripcion,'') like '%"+descripcion+"%'";
			q+=" order by A.idArticulo ";
			//System.out.println(q);
			return q;
		}
		
		/*
		 * Primer Paso Obtener los Alias Directos Establecidos de un Articulo 
		 */
		public String getAliasCodesQuery(String idarticulo){
			String q="";
			q+="select idcodigo,lineaproveedor from b_alias ";
			q+="where idarticulo like '"+idarticulo+"' ";
			return q;
		}
		
		public Object[][] getAliasCode(String idarticulo){
			return this.getResults(this.getAliasCodesQuery(idarticulo));
		}
		
		public String getCodigosEquivalentesQuery(String codigo,String linea,String linea_requerida){
			String q="";
			q+="select "; 
			q+="(case when replace(replace(replace(replace(e.codigo1,' ',''),'-',''),'.',''),',','') like replace(replace(replace(replace('"+codigo+"',' ',''),'-',''),'.',''),',','') and e.linea1 like '"+linea+"' then e.codigo2 else e.codigo1 end),  ";
			q+="(case when replace(replace(replace(replace(e.codigo1,' ',''),'-',''),'.',''),',','') like replace(replace(replace(replace('"+codigo+"',' ',''),'-',''),'.',''),',','') and e.linea1 like '"+linea+"' then e.linea2 else e.linea1 end) ";
			q+="from b_catalogo_equivalencia e ";
			q+="where ((replace(replace(replace(replace(e.codigo1,' ',''),'-',''),'.',''),',','') like replace(replace(replace(replace('"+codigo+"',' ',''),'-',''),'.',''),',','') and e.linea1 like '"+linea+"') ";
			q+="or (replace(replace(replace(replace(e.codigo1,' ',''),'-',''),'.',''),',','') like replace(replace(replace(replace('"+codigo+"',' ',''),'-',''),'.',''),',','') and e.linea2 like '"+linea+"') ) ";
			if (linea_requerida.compareTo("")!=0){
				q+=" and (case when replace(replace(replace(replace(e.codigo1,' ',''),'-',''),'.',''),',','') like replace(replace(replace(replace('"+codigo+"',' ',''),'-',''),'.',''),',','') and e.linea1 like '"+linea+"' then e.linea2 else e.linea1 end) like '"+linea_requerida+"' ";
			}
			return q;
		}
		
		public Object[][] getCodigosEquivalentes(String codigo,String linea,String linea_requerida){
			return this.getResults(this.getCodigosEquivalentesQuery(codigo, linea,linea_requerida));
		}
		
		
		public String getAliasArticleQuery(String codigo,String linea){
			String q="";
			q+="select idarticulo from b_alias ";
			q+="where replace(replace(replace(replace(idcodigo,' ',''),'-',''),'.',''),',','') like replace(replace(replace(replace('"+codigo+"',' ',''),'-',''),'.',''),',','') ";
			q+="and lineaproveedor like '"+linea+"' ";
			return q;
		}
		
		public Object[][] getAliasArticle(String codigo,String linea){
			return this.getResults(this.getAliasArticleQuery(codigo, linea));
		}
		
		public String getQueryArticulos(String desde,String hasta,String linea){
			String q="";
			q+="select a.idarticulo,a.descripcion,isnull(m.idarticulo,''),isnull(m.descripcion,'') ";
			q+="from v_ma_articulos a left outer join b_articulos_equivalencias e "; 
			q+="on (a.idarticulo=e.idarticulo1 or a.idarticulo=e.idarticulo2) "; 
			q+="left outer join v_ma_articulos m ";
			q+="on (e.idarticulo1=m.idarticulo or e.idarticulo2=m.idarticulo) and a.idarticulo<>m.idarticulo ";
			if (linea.compareTo("")!=0){
				q+="and m.descripabrev like '"+linea+"' ";	
			}
			q+="where a.idarticulo between '"+desde+"' and  '"+hasta+"' "; 
			
			//System.out.println(q);
			return q;
		}
		
		public boolean check_linea(String lineaproveedor,String idproveedor){
			boolean ok=false;
			String q="";
			q+="select TOP 1 idcodigo from b_codigos ";
			q+="where lineaproveedor like '"+lineaproveedor+"' ";
			q+="and idproveedor like '"+idproveedor+"'";
			q+="";
			System.out.println("checkLinea> "+q);
			Object[][] results=getResults(q);
			if (results!=null){
				if (results.length>0){
					ok=true;
				}
			}
			return ok;
		}

		public String getAliasCodeQuery(String codigo,String idproveedor,String linea,boolean chars,String Characters,boolean lineas){
			String q="select TOP 100 idcodigo,lineaproveedor,precio5,ultimo_upd from b_codigos where ";
			if (chars){
				String replaced="idcodigo";
				for (int u=0;u<Characters.length();u++){
					replaced="replace("+replaced+",'"+Characters.substring(u, u+1)+"','') ";
				}
				q+=replaced+" like '"+codigo+"%'";
			}else {
				q+=" idcodigo like '"+codigo+"%'";	
			}
			if (lineas){
				if (linea.compareTo("")!=0){
					q+=" and lineaproveedor like '"+linea+"'";	
				}	
			}else {
				
			}
			
			if (idproveedor.compareTo("")!=0){
				q+=" and idproveedor like '"+idproveedor+"'";	
			}
			q+="order by len(idcodigo)";
			
			return q;
		}
		public Object[][] getAliasCode(String codigo,String idproveedor,String linea,boolean chars,String Characters,boolean lineas){
			String q=this.getAliasCodeQuery(codigo, idproveedor, linea, chars, Characters, lineas);
			System.out.println(q);
			Object[][] results=getResults(q);
			return results;
		}
		
		public boolean checkcode(String code,String linea,String idproveedor){
			boolean ok=false;
			String q="";
			q+=" select TOP 1 c.idcodigo,c.precio5,isnull(b.factor,1.0) ";
			q+=" from b_codigos c left outer join b_alias b ";
			q+=" on c.idcodigo=b.idcodigo and c.lineaproveedor=b.lineaproveedor and c.idproveedor=b.idproveedor ";
			q+=" where c.idcodigo like '"+code+"' ";
			q+=" and c.lineaproveedor like '"+linea+"' ";
			q+=" and c.idproveedor like '"+idproveedor+"' ";
			//System.out.println(q);
			Object[][] results=getResults(q);
			if (results!=null){
				if (results.length>0){
					ok=true;
				}
			}
			return ok;
		}
		
		public boolean check_code(String idcodigo,String lineaproveedor,String idproveedor){
			boolean ok=false;
			String q="select TOP 1 idcodigo from b_codigos ";
			q+="where idcodigo like '"+idcodigo+"'";
			q+="and lineaproveedor like '"+lineaproveedor+"' ";
			q+="and idproveedor like '"+idproveedor+"' ";
			Object[][] results=getResults(q);
			if (results!=null){
				if (results.length>0){
					ok=true;
				}
			}
			return ok;
		}
		
		public Object[][] getCode(String code,String linea,String idproveedor){
			String q="";
			q+=" select TOP 100 c.idcodigo,c.precio5,isnull(b.factor,1.0) ";
			q+=" from b_codigos c left outer join b_alias b ";
			q+=" on c.idcodigo=b.idcodigo and c.lineaproveedor=b.lineaproveedor and c.idproveedor=b.idproveedor ";
			q+=" where c.idcodigo like '"+code+"' and ";
			q+=" c.lineaproveedor like '"+linea+"' ";
			q+=" and c.idproveedor like '"+idproveedor+"' ";
			Object[][] results=connection_handler.getDefaultConnector().getResults(q);
			return results;
		}	
		public Object[][] getClientInformation(String idcliente){
			
			String q="";
			q=q+"select M.codigo,M.descripcion ";
			q=q+"FROM MA_CUENTAS M ";
			q=q+" where M.codigo like '"+idcliente+"' and M.codigo like '21101%' and M.codigo not like '21101' ";
			Object[][] results=connection_handler.getDefaultConnector().getResults(q);
			return results;
		}
		private String getQueryLinePrefix(String linea){
			String q = "";
			q+="select top 1 substring(idarticulo,0,4) ";
			q+="FROM v_ma_articulos ";
			q+="where  descripabrev like '"+linea+"' ";
			q+="group by substring(idarticulo,0,4) ";
			q+="order by count(*) desc";
			return q;
		}
		
		private String getLineQuery(String linea) {
			String q = "";
			q = q + "select top 1 descripabrev ";
			q = q + "FROM v_ma_articulos ";
			q = q + "where  descripabrev like '"+linea+"'";
			return q;
		}
		
		public boolean check_linea(String linea){
			boolean exist=false;
			Object[][] results = getResults(this.getLineQuery(linea));
			if (results != null) {
				if (results.length > 0) {
				exist=true;
				}
			}
			return exist;
		}
		
		public Object[][] getLinePrefix(String linea){
			return getResults(this.getQueryLinePrefix(linea));
		}
		
		public Object[][] getArticulo(String idarticulo){
			Object[][] results=getResults(this.getArticuleQuery(idarticulo));
			return results;
		}

		public boolean exist_equivalencia(String idarticulo1,String idarticulo2){
			boolean exist=false;
			String q="";
			q+="select * from b_articulos_equivalencias ";
			q+="where ((idarticulo1 like '"+idarticulo1+"' and idarticulo2 like '"+idarticulo2+"') ";
			q+="or (idarticulo1 like '"+idarticulo2+"' and idarticulo2 like '"+idarticulo1+"')) ";
			Object[][] results=this.getResults(q);
			if (results!=null){
				if (results.length>0){
					exist=true;
				}
			}
			return exist;
		}
		
		public String getInsert(String idarticulo1,String idarticulo2){
			String q="";
			q+="insert into b_articulos_equivalencias (idarticulo1,idarticulo2) values ('"+idarticulo1+"','"+idarticulo2+"')";
			return q;
		}
		private String getArticuleQuery(String idarticulo) {
			String q = "";
			q+="select m.idarticulo,";
			q+="m.descripcion,";
			q+="m.cuentaproveedor,";
			q+="m.politicaprecios,";
			q+="m.descripabrev,";
			q+="m.precio5,";
			q+="m.costo,";
			q+="m.precio2, ";
			q+="m.puntopedido, ";
			q+="m.suspendidov, ";
			q+="m.suspendidoc, ";
			q+="sum(isnull(s.cantidadud,0)), ";
			q+="m.cuenta_actualizacion, ";
			q+="isnull(m.iduser_creador,''), ";
			q+="isnull(m.iduser_modificador,''), ";
			q+="isnull(m.iduser_actualizador,''), ";
			q+="m.creacion,";
			q+="m.modificacion,";
			q+="m.actualizacion, ";
			q+="m.clasificacion ";
			q+="FROM v_ma_articulos m left outer join v_mv_stock s on m.idarticulo=s.idarticulo and s.anulado=0 ";
			
			q+="where m.idarticulo like '"+idarticulo+"' ";
			q+="group by ";
			q+="m.idarticulo,";
			q+="m.descripcion,";
			q+="m.cuentaproveedor,";
			q+="m.politicaprecios,";
			q+="m.descripabrev,";
			q+="m.precio5,";
			q+="m.costo,";
			q+="m.precio2, ";
			q+="m.puntopedido, ";
			q+="m.suspendidov, ";
			q+="m.suspendidoc, ";
			q+="m.cuenta_actualizacion, ";
			q+="isnull(m.iduser_creador,''), ";
			q+="isnull(m.iduser_modificador,''), ";
			q+="isnull(m.iduser_actualizador,''), ";
			q+="m.creacion,";
			q+="m.modificacion,";
			q+="m.actualizacion, ";
			q+="m.clasificacion ";
			return q;
		}


}
