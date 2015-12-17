package aplicacion.ventas.catalogo.logic;
import java.util.ArrayList;
import java.util.List;

import aplicacion.herramientas.conexion.conectores.MySQL;
import aplicacion.modelo.logic.*;

public class _Data extends Data{
	
	public Object[][] getIdenticos(String codigo,String linea,String idproveedor){
		String q="select c.codigo,c.descripcion,c.linea,c.idproveedor,m.descripcion ";
		q+="from b_catalogo c left outer join ma_cuentas m on c.idproveedor=m.codigo ";
		q+="where  ";
		q+=" c.codigo like '"+codigo+"' and c.linea like '"+linea+"' and c.idproveedor not like '"+idproveedor+"'";
		q+=" group by c.codigo,c.descripcion,c.linea,c.idproveedor,m.descripcion";
		System.out.println(q);
		return this.getResults(q);
	}
	
	public Object[][] getVendedor(String iduser){
		String q="";
		q+="select b.idvendedor,v.nombre ";
		q+="from b_users b left outer join v_ta_vendedores v on ltrim(b.idvendedor)=ltrim(v.idvendedor) ";
		q+="where b.iduser like '"+iduser+"'";
		return this.getResults(q);
	}
	
	public String getInsertFaltante(String idarticulo, String descripcion,String ip,String idvendedor, String cantidad,String stock){
		String q="insert into b_articulos_faltantes (idarticulo,descripcion,fecha,idusuario,ip,cantidad,stock) ";
		q+=" values ('"+idarticulo+"','"+descripcion+"',getdate(),'"+idvendedor+"','"+ip+"',"+cantidad+","+stock+") ";
		return q;
	}
	
	public Object[][] getDeposito(String idarticulo){
		String q="";
		q+="select v.idarticulo,v.descripcion,r.descripcion,d.descripcion, ";
		q+="sum(s.cantidadud) ";
		q+=" from v_ma_articulos v ";
		q+="left outer join v_mv_stock s ";
		q+="on v.idarticulo=s.idarticulo and s.anulado = 0 ";
		q+="left outer join v_ta_deposito d ";
		q+="on s.iddeposito=d.iddeposito ";
		q+="left outer join v_ta_rubros r on v.idrubro = r.idrubro ";
		q+="where v.idarticulo like '"+idarticulo+"' ";
		q+="group by v.idarticulo,v.descripcion,r.descripcion,d.descripcion ";
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
	}
	
	public Object[][] getArticulosRelacionados(String _idarticulo){
		Object[][] results=null;
		Object[][] results1=this.getArticulosRelacionadosPDC(_idarticulo);
		Object[][] results2=this.getArticulosRelacionadosVMVCPTE(_idarticulo);
		List<Object[]> relaciones=new ArrayList<Object[]>();
		if (results1!=null){
			for (int i=0;i<results1.length;i++){
				String idarticulo=(String) results1[i][0];
				int pos=this.getPosition(relaciones, idarticulo);
				if (pos<0){
					Object[] tmp=new Object[results1[0].length];
					for (int u=0;u<results1[0].length;u++){
						tmp[u]=results1[i][u];
					}
					relaciones.add(tmp);
				}else{
					Object[] tmp=(Object[]) relaciones.get(pos);
					double qty=new Double(tmp[3].toString());
					double qty2=new Double(results1[i][3].toString());
					qty*=qty2;
					tmp[3]=qty;
					relaciones.set(pos, tmp);
				}
			}
		}
		if (results2!=null){
			for (int i=0;i<results2.length;i++){
				String idarticulo=(String) results2[i][0];
				int pos=this.getPosition(relaciones, idarticulo);
				if (pos<0){
					Object[] tmp=new Object[results2[0].length];
					for (int u=0;u<results2[0].length;u++){
						tmp[u]=results2[i][u];
					}
					relaciones.add(tmp);
				}else{
					Object[] tmp=(Object[]) relaciones.get(pos);
					double qty=new Double(tmp[3].toString());
					double qty2=new Double(results2[i][3].toString());
					qty*=qty2;
					tmp[3]=qty;
					relaciones.set(pos, tmp);
				}
			}
		}
		if (relaciones.size()>0){
			Object[] tmp=(Object[]) relaciones.get(0);
			int cols=tmp.length;
			results=new Object[relaciones.size()][cols];
			for (int i=0;i<relaciones.size();i++){
				tmp=(Object[]) relaciones.get(i);
				for (int j=0;j<cols;j++){
					results[i][j]=tmp[j];	
				}
			}
		}
		return results;
	}
	
	public int getPosition(List<Object[]> relaciones,String idarticulo){
		int pos=0;
		boolean found=false;
		while (pos<relaciones.size() & !found){
			Object[] tmp=(Object[]) relaciones.get(pos);
			found=tmp[0].toString().compareTo(idarticulo)==0;
			if (!found)pos++;
		}
		if (!found)pos=-1;
		return pos;
	}
	
	public Object[][] getArticulosRelacionadosVMVCPTE(String idarticulo){
		Object[][] results=null;
		String q="";
		q+="select b2.idarticulo,a.descripcion,r.descripcion,round(a.precio2,2),s.stock,count(*) as referencias ";
		q+="from v_mv_cpteinsumos b1 left outer join v_mv_cpte cb1  on b1.tc=cb1.tc and b1.idcomprobante=cb1.idcomprobante ";
		q+="left outer join v_mv_cpteinsumos b2 ";
		q+="on b1.tc=b2.tc and b1.idcomprobante=b2.idcomprobante ";
		q+="and b1.idarticulo <> b2.idarticulo ";
		q+="left outer join v_ma_Articulos a ";
		q+="on b2.idarticulo=a.idarticulo ";
		q+="left outer join v_ta_rubros r ";
		q+="on b2.idrubro=r.idrubro ";
		q+=" left outer join view_stock s on a.idarticulo=s.idarticulo ";
		q+="where b1.idarticulo like '"+idarticulo+"' ";
		q+="and b2.idarticulo not like '*' ";
		q+="and (b1.tc like 'tkfc' or b1.tc like 'tk' or b1.tc like 'fp' or b1.tc like 'fc') ";
		q+="and cb1.cuenta not in(select c.codigo from ";
		q+="ma_cuentasadic c ";
		q+="left outer join v_ta_categoria cat ";
		q+="on c.idcategoria=cat.idcategoria ";
		q+="where cat.descripcion like '%sucursal%') ";
		q+="group by b2.idarticulo,a.descripcion,r.descripcion,round(a.precio2,2),s.stock ";
		q+="having count(*)>=2 ";
		q+="order by referencias desc ";

		results=this.getResults(q);
		return results;
	}
	
	public Object[][] getArticulosRelacionadosPDC(String idarticulo){
		Object[][] results=null;
		String q="";
		q+=" select b2.idarticulo,a.descripcion,r.descripcion,round(a.precio2,2),s.stock,count(*) ";
		q+=" from b_pdc_item b1 left outer join b_pdc cb1 on b1.idpedido=cb1.idpedido ";
		q+=" left outer join b_pdc_item b2 ";
		q+=" on b1.idpedido=b2.idpedido ";
		q+=" and b1.idarticulo <> b2.idarticulo ";
		q+=" left outer join v_ma_Articulos a ";
		q+=" on b2.idarticulo=a.idarticulo ";
		q+=" left outer join v_ta_rubros r ";
		q+=" on b2.idrubro=r.idrubro ";
		q+=" left outer join view_stock s on a.idarticulo=s.idarticulo ";
		q+=" where b1.idarticulo like '"+idarticulo+"' ";
		q+=" and b2.idarticulo not like '*' ";
		q+=" and cb1.cliente not in(select c.codigo from ";
		q+=" ma_cuentasadic c ";
		q+=" left outer join v_ta_categoria cat ";
		q+=" on c.idcategoria=cat.idcategoria ";
		q+=" where cat.descripcion like '%sucursal%') ";
		q+=" group by b2.idarticulo,a.descripcion,r.descripcion,round(a.precio2,2),s.stock ";
		q+=" having count(*)>=2 ";
		q+=" order by count(*) desc ";
		q+="";

		results=this.getResults(q);
		return results;
	}
	
	public Object[][] getEquivalencias(String idarticulo){
		String q="";
		q+="select m.idarticulo,m.descripcion,r.descripcion,m.precio2,sum(isnull(s.cantidadud,0)) ";
		q+="from b_articulos_equivalencias e left outer join v_ma_articulos m ";
		q+=" on (e.idarticulo1=m.idarticulo or e.idarticulo2=m.idarticulo) ";
		q+=" left outer join v_mv_stock s on m.idarticulo=s.idarticulo and s.anulado=0 ";
		q+=" left outer join v_ta_rubros r ";
		q+=" on m.idrubro=r.idrubro ";
		q+=" where (e.idarticulo1 like '"+idarticulo+"' or e.idarticulo2 like '"+idarticulo+"')";
		q+=" and m.idarticulo not like '"+idarticulo+"' ";
		q+=" group by m.idarticulo,m.descripcion,r.descripcion,m.precio2 ";
		return this.getResults(q);
	}
	
	
	public Object[][] getData(String idarticulo){
		String q="";
		q+="select m.idarticulo,m.descripcion,r.descripcion,isnull(sum(s.cantidadud),0),isnull(m.suspendidov,0),convert(varchar(10),m.actualizacion,105), ";
		q+="m.precio2 ";
		q+="from v_ma_articulos m ";
		q+="left outer join v_mv_stock s on m.idarticulo=s.idarticulo and s.anulado=0 ";
		q+="left outer join v_TA_RUBROS r on m.idrubro=r.idrubro  ";
		q+="where m.idarticulo like '"+idarticulo+"' ";
		q+="group by m.idarticulo,m.descripcion,r.descripcion,isnull(m.suspendidov,0),m.actualizacion,m.precio2";
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
	}
	public Object[][] getCatalogos(String descripcion){
		String q="select filename from pdf_archivo ";
		if (descripcion.length()>0){
			q+=" where "+descripcion;	
		}
		
		q+=" order by filename";
		System.out.println("Catalogos?>"+q);
		return this.getConnector("MySQL").getResults(q);
	}
	
	public String getQueryStock(String idarticulo){
		String q="";
		q+="Select convert(varchar(10),s.fecha,105),s.cuentaproveedor,m.descripcion,s.tc,s.idcomprobante,";
		q+="(case when cantidadud >=0 then s.cantidadud else 0 end) as entrada,";
		q+="(case when cantidadud <0 then -s.cantidadud else 0 end) as salida,";
		q+=" 0,";
		q+="(case when (tc like 'tk' or tc like 'tkfc' or tc like 'fc' or tc like 'nc') then s.precioventa else s.costo end) as precio,";
		q+="isnull(v.nombre,'')";
		q+=" from v_mv_stock s left outer join ma_cuentas m";
		q+=" on s.cuentaproveedor=m.codigo ";
		q+=" left outer join v_ta_vendedores v on s.idvendedor=v.idvendedor ";
		q+=" where s.idarticulo like '"+idarticulo+"' and s.anulado=0 ";
		q+=" order by s.fecha";
		System.out.println(q);
		return q;
	}
	
	public Object[][] getStock(String idarticulo){
		return this.getResults(this.getQueryStock(idarticulo));
	}
	
	
	public Object[][] getCodigo(String codigo,String linea,String idproveedor){
		String q="select c.codigo,c.descripcion,c.marca_vehiculo,c.vehiculo,c.linea,c.categoria1,c.categoria2,c.idproveedor ";
		q+="from b_catalogo c ";
		q+="where  ";
		q+=" replace(replace(c.codigo,' ',''),'-','') like replace(replace('"+codigo+"',' ',''),'-','') and c.linea like '"+linea+"' and c.idproveedor like '"+idproveedor+"'";
		System.out.println(q);
		return this.getResults(q);
	}
	public Object[][] getCodigo(String codigo,String linea,String idproveedor,String descripcion){
		String q="select c.codigo,c.descripcion,c.marca_vehiculo,c.vehiculo,c.linea,c.categoria1,c.categoria2,c.idproveedor ";
		q+="from b_catalogo c ";
		q+="where  ";
		q+=" c.codigo like '"+codigo+"' and c.linea like '"+linea+"' and c.idproveedor not like '"+idproveedor+"' and c.descripcion like '"+descripcion+"' ";
		System.out.println(q);
		return this.getResults(q);
	}
	public Object[][] getEquivalencias(String codigo,String linea){
		String q="";
		q+="select (case when (replace(e.codigo1,' ','') like replace('"+codigo+"',' ','') and e.linea1 like '"+linea+"') then e.codigo2 else e.codigo1 end),isnull(c.descripcion,''),";
		q+="(case when (replace(e.codigo1,' ','') like replace('"+codigo+"',' ','') and e.linea1 like '"+linea+"') then e.linea2 else e.linea1 end),";
		q+="isnull(c.idproveedor,''),isnull(m.descripcion,'') ";
		q+="from b_catalogo_equivalencia e left outer join b_catalogo c  ";
		q+="on (case when (e.codigo1 like '"+codigo+"' and e.linea1 like '"+linea+"') then e.codigo2 else e.codigo1 end)=c.codigo and ";
		q+=" (case when (e.codigo1 like '"+codigo+"' and e.linea1 like '"+linea+"') then e.linea2 else e.linea1 end) = c.linea ";
		q+=" left outer join ma_cuentas m on c.idproveedor=m.codigo ";
		q+="where  ";
		q+="(( replace(replace(e.codigo1,' ',''),'-','') like replace(replace('"+codigo+"',' ',''),'-','') and e.linea1 like '"+linea+"') ";
		q+="or (replace(replace(e.codigo2,' ',''),'-','') like replace(replace('"+codigo+"',' ',''),'-','') and e.linea2 like '"+linea+"')) ";
		q+="group by (case when (replace(e.codigo1,' ','') like replace('"+codigo+"',' ','') and e.linea1 like '"+linea+"') then e.codigo2 else e.codigo1 end),";
		q+="(case when (replace(e.codigo1,' ','') like replace('"+codigo+"',' ','') and e.linea1 like '"+linea+"') then e.linea2 else e.linea1 end),c.idproveedor,c.descripcion,m.descripcion ";
		
		System.out.println("get equivalencia query>"+q);
		return this.getResults(q);
	}
	
	public boolean esArticulo(String idarticulo){
		boolean es=false;
		String q="select idarticulo from v_ma_articulos where idarticulo like '"+idarticulo+"'";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				es=true;
			}
		}
		return es;
	}
	public Object[][] getArticulos(String codigo,String linea){
		String q="";
		q+="select m.idarticulo,m.descripcion,r.descripcion,m.precio2,sum(isnull(s.cantidadud,0)) as stock ";
		q+="from v_ma_Articulos m left outer join b_alias a on m.idarticulo=a.idarticulo ";
		q+="left outer join v_mv_stock s on m.idarticulo=s.idarticulo AND s.ANULADO=0  ";
		q+="left outer join v_TA_RUBROS r on m.idrubro=r.idrubro  ";
		q+="where  ";
		q+="replace(a.idcodigo,' ','') like replace('"+codigo+"',' ','') and a.lineaproveedor like '"+linea+"' ";
		q+="group by m.idarticulo,m.descripcion,r.descripcion,m.precio2  ";
		System.out.println(q);
		return this.getResults(q);
	}
	public Object[][] getCodigos(String idarticulo){
		String q="";
		q+="select c.idcodigo,c.descripcion,c.lineaproveedor,c.idproveedor,m.descripcion ";
		q+="from  b_alias a left outer join b_codigos c on a.idcodigo=c.idcodigo and a.lineaproveedor=c.lineaproveedor ";
		q+="left outer join ma_cuentas m on c.idproveedor=m.codigo ";
		q+="where  ";
		q+="a.idarticulo like '"+idarticulo+"' ";
		System.out.println(q);
		return this.getResults(q);
	}
	
	private String getQueryColumn(String aux,String column){
		String description="";
		while (aux.contains(" ")) {
			String sub = aux.substring(0, aux.indexOf(" "));
			if (sub.compareTo("") != 0) {
				if (description.length() > 0) {
					description = description + " and ";
				}
				description = description + " " + column + " like '%"
						+ sub + "%'";
			}
			aux = aux.substring(aux.indexOf(" ") + 1);
		}
		if (aux.compareTo("") != 0) {
			if (description.length() > 0) {
				description = description + " and ";
			}
			description = description + "  " + column + " like '%"
					+ aux + "%'";
		}
		return description;
	}
	public Object[][] getArticuloDinamico(String codigo,String linea){
		String q="";
		q+="select m.idarticulo,m.descripcion,r.descripcion,m.precio2,sum(s.cantidadud) as stock ";
		q+="from v_ma_Articulos m ";
		q+="left outer join v_mv_stock s on m.idarticulo=s.idarticulo AND s.ANUALDO=0";
		q+="left outer join v_TA_RUBROS r on m.idrubro=r.idrubro  ";
		q+="where  ";
		codigo=codigo.replace("-", " ");
		codigo=codigo.replace("/", " ");
		codigo=codigo.replace(".", " ");
		String where_codigo=this.getQueryColumn(codigo, "m.idarticulo");
		q+=where_codigo+" and r.descripcion like '"+linea+"' ";
		q+="group by m.idarticulo,m.descripcion,r.descripcion,m.precio2  ";
		System.out.println(q);
		return this.getResults(q);
	}
	
	public Object[][] getProducto(String codigo,String linea,String idproveedor){
		String q="";
		q+="select c.codigo,c.descripcion,c.linea,c.idproveedor,m.descripcion,m.precio ";
		q+="from b_catalogo_equivalencia e ";
		q+="left outer join b_catalogo c on ";
		q+="(replace(e.codigo1,'-','')=replace(c.codigo,'-','') and e.linea1=c.linea) ";
		q+="or (replace(e.codigo2,'-','')=replace(c.codigo,'-','') and e.linea2=c.linea) ";
		q+="left outer join ma_cuentas m ";
		q+=" on c.idproveedor=m.codigo ";
		q+="where  ";
		q+="c.codigo like '"+codigo+"' and c.linea like '"+linea+"') ";
		q+="group by c.codigo,c.descripcion,c.linea,c.idproveedor,m.descripcion";
		return this.getResults(q);
	}
	
	public String[] getImagenes(String codigo,String linea,String idproveedor){
		String[] imagenes=null;
		String q="";
		q+=" Select imagen from  b_catalogo_imagen where codigo like '"+codigo+"' and linea like '"+linea+"' and idproveedor like '"+idproveedor+"' ";
		Object[][] results=this.getResults(q);
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
		String column = "MATCH (content,title,resumen,linea,filename) AGAINST ('"+description+"' in boolean mode); ";
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
	
	
	
}
