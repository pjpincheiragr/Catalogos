package aplicacion.actualizacion.alias.logic;

import aplicacion.herramientas.java.*;
import aplicacion.modelo.logic.Data;

/**
 * @author Agustin Wisky
 * @company Wismi S.A.
 * @since 10-10-2009
 */
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
		
		public Object[][] getArticulos(String idproveedor,String desde,String hasta,boolean stock){
			String q=this.getQueryArticulos(idproveedor, desde, hasta,stock);
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
		
		public String getQueryArticulos(String idproveedor,String desde,String hasta,boolean stock){
			String linea="";
			String descripcion="";
			String q="select top 10000 G.idarticulo,isnull(G.descripcion,''),isnull(P.idcodigo,'') as codigo,";
			q+=" isnull(P.precio5,0)as Lista,isnull(A.factor,0) as Factor,isnull(G.precio5,0.0),isnull(G.descripabrev,'')as Linea,";
			q+=" isnull(P.idproveedor,'')as idproveedor,A.lineaproveedor,isnull(P.ultimo_upd,'') ";
			q+=" from   v_ma_articulos G ";
			q+=" left outer join Stk_Stock_UDKG s on G.idarticulo=s.idarticulo ";
			q+=" left outer join b_alias A  ";
			q+=" on G.idarticulo=A.idarticulo  ";
			if (idproveedor.compareTo("")!=0){
				q+=" and isnull(A.idproveedor,'') like '%"+idproveedor+"%'";	
			}
			q+=" left outer join b_codigos P  ";
			q+=" on A.idcodigo=P.idcodigo and A.idproveedor=P.idproveedor and A.lineaproveedor=P.lineaproveedor ";
			q+=" where  ";
			q+=" G.idArticulo between '"+desde+"' and  '"+hasta+"' ";
			q+=" and isnull(G.descripabrev,'') like '%"+linea+"%'";
			q+=" and isnull(G.Descripcion,'') like '%"+descripcion+"%'";
			if (stock){
				q+=" and isnull(s.cantidadud,0) >0 ";	
			}
			q+=" order by G.idArticulo ";
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
			System.out.println(q);
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
}
