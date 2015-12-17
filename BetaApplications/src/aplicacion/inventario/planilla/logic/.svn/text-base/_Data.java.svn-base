package aplicacion.inventario.planilla.logic;

import aplicacion.herramientas.conexion.creator.interfaces._Interface;
import aplicacion.modelo.logic.Data;

public class _Data extends Data {

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
	
	public String getInsert(
			String idarticulo,
			String descripcion,
			String linea,
			String idproveedor,
			String politica,
			String lista,
			String costo,
			String publico,
			String minimo,
			boolean suspendidov,
			boolean suspendidoc,
			String cuenta_actualizacion,
			String clasificacion,
			String iduser) {
		String q="";
		q+="insert into v_ma_articulos (idarticulo,descripcion,DESCRIPABREV,cuentaproveedor,politicaPRECIOS,precio5,costo,precio2,PUNTOPEDIDO,idunidad,suspendidov,suspendidoc,cuenta_actualizacion,actualizacion,iduser_modificador,creacion,iduser_creador,iduser_actualizador,modificacion,clasificacion) values (";
		q+="'"+idarticulo+"',";
		q+="'"+descripcion+"',";
		q+="'"+linea+"',";
		q+="'"+idproveedor+"',";
		q+="'"+politica+"',";
		q+=""+lista+",";
		q+=""+costo+",";
		q+=""+publico+",";
		q+=""+minimo+",";
		q+="'   1',";
		if (suspendidov){
			q+="1,";	
		}else{
			q+="0,";
		}
		
		if (suspendidoc){
			q+="1";	
		}else{
			q+="0";
		}
		q+=",'"+cuenta_actualizacion+"',";
		q+="getdate(),";
		q+="'"+iduser+"',";
		q+="getdate(),";
		q+="'"+iduser+"',";
		q+="'"+iduser+"',getdate(),";
		q+="'"+clasificacion+"'";
		q+=")";
		return q;
	}
	
	public boolean existe(String idarticulo1){
		boolean exist=false;
		String q="";
		q+="select * from v_ma_articulos ";
		q+="where idarticulo like '"+idarticulo1+"' ";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				exist=true;
			}
		}
		return exist;
	}
	
	public Object[][] getData(String idarticulo){
		String q="";
		q+="select m.idarticulo,m.descripcion,m.descripabrev,sum(isnull(s.cantidadud,0)),isnull(m.suspendidoc,0),convert(varchar(10),m.actualizacion,105) ";
		q+="from v_ma_articulos m ";
		q+="left outer join v_mv_stock s on m.idarticulo=s.idarticulo and s.anulado=0 "; 
		q+="where m.idarticulo like '"+idarticulo+"' ";
		q+=" group by m.idarticulo,m.descripcion,m.descripabrev,isnull(m.suspendidoc,0),convert(varchar(10),m.actualizacion,105) ";
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
	}
	
	public Object[][] getArticulo(String idarticulo){
		Object[][] results=getResults(this.getArticuleQuery(idarticulo));
		return results;
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
		q+="m.actualizacion ";
		q+="FROM v_ma_articulos m left outer join v_mv_stock s on m.idarticulo=s.idarticulo and s.anulado=0 ";
		q+="where m.idarticulo like '"+idarticulo+"' ";
		q+="group by m.idarticulo,";
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
		q+="m.actualizacion ";
		return q;
	}


	public String getRegistrarCambio(String iduser,
			String ip,
			String idarticulo,
			String descripcion,
			String descripcion_old,
			String precio,
			String precio_old,
			String costo,
			String costo_old,
			String precio5,
			String precio5_old,
			String politicaprecios,
			String politicaprecios_old,
			String minimo,
			String minimo_old,
			String suspendidov,
			String suspendidov_old,
			String suspendidoc,
			String suspendidoc_old,
			String validacion,
			String clasificacion,
			String clasificacion_old,
			String idoperacion
		){
		String q="";
		q+="insert into b_auditor_articulos ";
		q+="(iduser,ip,fecha,idarticulo,descripcion,descripcion_old,precio,precio_old,";
		q+="costo,costo_old,precio5,precio5_old,politicaprecios,politicaprecios_old,minimo,minimo_old,";
		q+="suspendidov,suspendidov_old,suspendidoc,suspendidoc_old,validacion,";
		q+="clasificacion,clasificacion_old,idoperacion) ";
		q+="values (";
		q+="'"+iduser+"',";
		q+="'"+ip+"',";
		q+="getdate(),";
		q+="'"+idarticulo+"',";
		q+="'"+descripcion+"',";
		q+="'"+descripcion_old+"',";
		q+=""+precio+",";
		q+=""+precio_old+",";
		q+=""+costo+",";
		q+=""+costo_old+",";
		q+=""+precio5+",";
		q+=""+precio5_old+",";
		q+="'"+politicaprecios+"',";
		q+="'"+politicaprecios_old+"',";
		q+=""+minimo+",";
		q+=""+minimo_old+",";
		q+=""+suspendidov+",";
		q+=""+suspendidov_old+",";
		q+=""+suspendidoc+",";
		q+=""+suspendidoc_old+",";
		q+="'"+validacion+"',";
		q+="'"+clasificacion+"',";
		q+="'"+clasificacion_old+"',";
		q+="'"+idoperacion+"')";
		return q;
	}

	
	public Object[][] getCurrentValues(String idarticulo){
		String q="";
		q+="select descripcion,precio2,costo,precio5,politicaprecios,";
		q+="suspendidov,suspendidoc,puntopedido,clasificacion from v_ma_articulos where idarticulo like '"+idarticulo+"' ";
		q+="";
		Object[][] results=this.getResults(q);
		return results;
	}
	
	public boolean Update(
			String idarticulo,
			String descripcion,
			String linea,
			String idproveedor,
			String politica,
			String lista,
			String costo,
			String publico,
			String suspendidoc,
			String suspendidov,
			String idproveedor_actualizacion,
			String clasificacion,
			String iduser
			
			) {
		clearBatch();
		addBatch(this.getUpdate(idarticulo, descripcion, linea, idproveedor, politica, lista, costo, publico,suspendidoc,suspendidov,idproveedor_actualizacion,clasificacion,iduser));
		return executeBatch();
	}
	
	public String getUpdate(String idarticulo,String idproveedor){
		String q="update v_ma_articulos set actualizacion=c.ultimo_upd ";
		q+=" from v_ma_articulos a left outer join ";
		q+=" b_alias h on a.idarticulo = h.idarticulo and isnull(a.cuenta_actualizacion,a.cuentaproveedor)=h.idproveedor ";
		q+=" left outer join b_codigos c on ";
		q+=" h.idcodigo=c.idcodigo and h.lineaproveedor=c.lineaproveedor and h.idproveedor=c.idproveedor ";
		q+=" where a.idarticulo like '"+idarticulo+"' and h.idproveedor like '"+idproveedor+"' ";
		q+="";
		return q;
	}
	
	public String getUpdate(
			String idarticulo,
			String descripcion,
			String linea,
			String idproveedor,
			String politica,
			String lista,
			String costo,
			String publico,
			String suspendidoc,
			String suspendidov,
			String idproveedor_actualizacion,
			String clasificacion,
			String iduser
			
			
			) {
		 String q="update v_ma_articulos ";
		 q+=" set descripcion='"+descripcion+"', ";
		 q+=" descripabrev='"+linea+"', ";
		 q+=" cuentaproveedor='"+idproveedor+"', ";
		 q+=" politicaprecios='"+politica+"', ";
		 q+=" precio2="+publico+", ";
		 q+=" precio5="+lista+", ";
		 q+=" costo="+costo+", ";
		 q+=" suspendidoc="+suspendidoc+", ";
		 q+=" suspendidov="+suspendidov+", ";
		 q+=" cuenta_actualizacion='"+idproveedor_actualizacion+"', ";
		 q+=" iduser_modificador='"+iduser+"', ";
		 q+=" modificacion=getdate(), ";
		 q+=" clasificacion='"+clasificacion+"' ";
		 q+=" where idarticulo like '"+idarticulo+"' ";
		 //posibles valores a agregar//
		 //q+=" puntopedido="+minimo+", ";
		 //q+=" Clasificacion='"+Clase+"' ";
		 return q;
	}
	
	public String getInsert(
			String idarticulo,
			String descripcion,
			String linea,
			String idproveedor,
			String politica,
			String lista,
			String costo,
			String publico,
			String suspendidoc,
			String suspendidov) {
		String q="";
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

	


	public boolean check_idarticulo(String idarticulo){
		boolean exist=false;
		Object[][] results = getResults(this.getArticuleQuery(idarticulo));
		if (results != null) {
			if (results.length > 0) {
			exist=true;
			}
		}
		return exist;
	}
	
	
	public String getQueryArticulos(int top,String idproveedor,String linea,String desde,String hasta,String descripcion,String no_descripcion,boolean stock,String idproveedor_actualizacion){
	String q="";
	q+="select top "+top+" 0,a.idarticulo,a.descripcion,";
	q+="a.descripabrev,a.cuentaproveedor,a.precio5,";
	q+="a.politicaprecios,a.costo,a.precio2,convert(varchar(10),c.ultimo_upd,105),isnull(a.suspendidoc,0),isnull(a.suspendidov,0),sum(isnull(s.cantidadud,0)),a.cuenta_actualizacion,a.clasificacion  ";
	q+="from v_ma_articulos a left outer join ";
	q+=" v_mv_stock s on a.idarticulo=s.idarticulo and s.anulado=0 left outer join ";
	q+="b_alias h on a.idarticulo = h.idarticulo and isnull(a.cuenta_actualizacion,a.cuentaproveedor)=h.idproveedor ";
	q+="left outer join b_codigos c on ";
	q+="h.idcodigo=c.idcodigo and h.lineaproveedor=c.lineaproveedor and h.idproveedor=c.idproveedor ";
	q+="";
	q+="";
	q+="";
	q+="";
	String where="";
	if (idproveedor.compareTo("")!=0){
		if (where.length()>0) where+=" and ";
		where+=" a.cuentaproveedor like '"+idproveedor+"' ";
	}
	if (idproveedor_actualizacion.compareTo("")!=0){
		if (where.length()>0) where+=" and ";
		where+=" a.cuenta_actualizacion like '"+idproveedor_actualizacion+"' ";
	}
	if (descripcion.compareTo("")!=0){
		if (where.length()>0) where+=" and ";
		where+=" a.descripcion like '%"+descripcion+"%' ";
	}
	if (no_descripcion.compareTo("")!=0){
		if (where.length()>0) where+=" and ";
		where+=" a.descripcion not like '%"+no_descripcion+"%' ";
	}
	if (linea.compareTo("")!=0){
		if (where.length()>0) where+=" and ";
		where+=" a.descripabrev like '"+linea+"' ";
	}
	if (stock){
		if (where.length()>0) where+=" and ";
		where+=" isnull(s.cantidadud,0) >0 ";
	}
	
	if (where.length()>0) where+=" and ";
	where+="a.idarticulo between '"+desde+"' and '"+hasta+"'";
	q+="where "+where+" ";
	
	q+="group by a.idarticulo,a.descripcion,";
	q+="a.descripabrev,a.cuentaproveedor,a.precio5,";
	q+="a.politicaprecios,a.costo,a.precio2,convert(varchar(10),c.ultimo_upd,105),isnull(a.suspendidoc,0),isnull(a.suspendidov,0),a.cuenta_actualizacion,a.clasificacion  ";
	if (stock){
		q+="having sum(isnull(s.cantidadud,0))>0 ";
	}
	q+="order by a.idarticulo";
	//
	System.out.println(q);
	return q;
	}
	
	public Object[][] getArticulos(int top,String idproveedor,String linea,String desde,String hasta,String descripcion,String no_descripcion,boolean stock,String idproveedor_actualizacion){
		Object[][] results=this.getResults(this.getQueryArticulos(top, idproveedor, linea, desde, hasta,descripcion,no_descripcion,stock,idproveedor_actualizacion));
		return results;
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
	
	public boolean check_alias(String idarticulo,String idproveedor){
		Object[][] results=getResults(this.getAliasQuery(idarticulo, idproveedor));
		boolean exist=false;
		if (results!=null){
			if (results.length>0){
				exist=true;
			}
		}
		return exist;
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
	public String getAliasQuery(String idarticulo,String idproveedor){
		String q="";
		q+=" select id from b_alias ";
		q+=" where idarticulo like '"+idarticulo+"' ";
		q+=" and idproveedor like '"+idproveedor+"' ";
		return q;
	}
	public Object[][] getPrecioProveedor(String idarticulo){
		String q="";
		q=q+" select c.precio5,c.politicaprecio,c.idproveedor,c.ultimo_upd from b_alias b ";
		q=q+" left outer join b_codigos c ";
		q=q+" on b.idcodigo=c.idcodigo ";
		q=q+" and b.lineaproveedor=c.lineaproveedor ";
		q=q+" and b.idproveedor =c.idproveedor ";
		q=q+" where b.idarticulo like '"+idarticulo+"' "; 
		q=q+" order by c.ultimo_upd desc ";
		System.out.println(q);
		return getResults(q);
	}
	
	public Object[][] getPrecioProveedor(String idarticulo,String idproveedor){
		String q="";
		q=q+" select c.precio5,c.politicaprecio from b_alias b ";
		q=q+" left outer join b_codigos c ";
		q=q+" on b.idcodigo=c.idcodigo ";
		q=q+" and b.lineaproveedor=c.lineaproveedor ";
		q=q+" and b.idproveedor =c.idproveedor ";
		q=q+" where b.idarticulo like '"+idarticulo+"' "; 
		q=q+" and b.idproveedor like '"+idproveedor+"' ";
		return getResults(q);
	}
}
