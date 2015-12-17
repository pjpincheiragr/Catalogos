package aplicacion.inventario.articulo.logic;

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
	
	public int  getProximoAJM(){
		int c=0;
		String q="";
		q+="select x_ultimo_nro from b_ta_cpte ";
		q+="where codigo = 'AJM' ";
		Object[][] aux=connection_handler.getDefaultConnector().getResults(q);
		try{
			c=new Integer(aux[0][0].toString());
		}catch(Exception e){
			//System.out.println(e.getMessage());
		}
		return c;
	}
	
	public String getUpdateAJM(){
		String q="";
		q+="update b_ta_cpte set x_ultimo_nro = x_ultimo_nro + 1 where codigo like 'AJM'";
		return q;
	}
	
	public String disable_triggers(){
		String q="";
		q+="alter table v_ma_articulos disable trigger all ";
		return q;
	}
	public Object[][] getDeposito(String idarticulo){
		String q="";
		q+="select v.idarticulo,v.descripcion,v.descripabrev,d.iddeposito,d.descripcion, ";
		q+="isnull(sum(s.cantidadud),0) ";
		q+="from v_ta_deposito d join ";
		q+="v_ma_articulos v on d.iddeposito is not null ";
		q+="left outer join v_mv_stock s ";
		q+="on v.idarticulo=s.idarticulo and s.anulado=0 ";
		q+="and s.iddeposito=d.iddeposito ";
		q+="where v.idarticulo like '"+idarticulo+"' ";
		q+="group by v.idarticulo,v.descripcion,v.descripabrev,d.iddeposito,d.descripcion ";
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
	}
	
	public Object[][] getEquivalencias(String idarticulo){
		String q="";
		q+="select m.idarticulo,m.descripcion,m.descripabrev ";
		q+="from b_articulos_equivalencias e left outer join v_ma_articulos m";
		q+=" on (e.idarticulo1=m.idarticulo or e.idarticulo2=m.idarticulo) ";
		q+=" where (e.idarticulo1 like '"+idarticulo+"' or e.idarticulo2 like '"+idarticulo+"')";
		q+=" and m.idarticulo not like '"+idarticulo+"' ";
		return this.getResults(q);
	}
	
	public Object[][] getFotos(String idarticulo){
		String q="";
		q+="select foto  ";
		q+="from v_ma_articulos_foto ";
		q+="where idarticulo like '"+idarticulo+"' ";
		q+="order by foto ";
		return this.getResults(q);
	}
	
	public String get_delete_fotos(String idarticulo){
		String q="";
		q+="delete from v_ma_articulos_foto ";
		q+="where idarticulo like '"+idarticulo+"'";
		q+="";
		return q;
	}
	
	public String get_insert_foto(String idarticulo,String foto){
		String q="";
		q+="insert into v_ma_articulos_foto (idarticulo,foto) ";
		q+="values ('"+idarticulo+"','"+foto+"')";
		q+="";
		return q;
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
			String minimo,
			boolean suspendidov,
			boolean suspendidoc,
			String cuenta_actualizacion,
			boolean actualiza,
			String clasificacion,
			String iduser) {
		clearBatch();
		String q=this.getUpdate(idarticulo, descripcion, linea, idproveedor, politica, lista, costo, publico,minimo,suspendidov,suspendidoc,cuenta_actualizacion,actualiza,clasificacion,iduser);
		System.out.println(q);
		addBatch(q);
		return executeBatch();
	}
	
	
	
	public String getInsert(String idarticulo1,String idarticulo2){
		String q="";
		q+="insert into b_articulos_equivalencias (idarticulo1,idarticulo2) values ('"+idarticulo1+"','"+idarticulo2+"')";
		return q;
	}
	
	public boolean Insert(
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
		clearBatch();
		addBatch(this.getInsert(idarticulo, descripcion, linea, idproveedor, politica, lista, costo, publico,minimo,suspendidov,suspendidoc,cuenta_actualizacion,iduser,clasificacion));
		return executeBatch();
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
		q+="insert into v_ma_articulos (idarticulo,descripcion,idrubro,cuentaproveedor,politicaPRECIOS,precio5,costo,precio2,PUNTOPEDIDO,idunidad,suspendidov,suspendidoc,cuenta_actualizacion,actualizacion,iduser_modificador,creacion,iduser_creador,iduser_actualizador,modificacion,clasificacion) values (";
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
	
	public String getUpdate(
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
			boolean fecha_actualizacion,
			String clasificacion,
			String iduser) {
		 String q="update v_ma_articulos ";
		 q+=" set descripcion='"+descripcion+"', ";
		 q+=" idrubro='"+linea+"', ";
		 q+=" cuentaproveedor='"+idproveedor+"', ";
		 q+=" politicaprecios='"+politica+"', ";
		 q+=" precio2="+publico+", ";
		 q+=" precio5="+lista+", ";
		 q+=" costo="+costo+", ";
		 q+=" cuenta_actualizacion='"+cuenta_actualizacion+"',";
		 q+=" puntopedido="+minimo+", ";
		 if (suspendidov){
			 q+=" suspendidov=1,";
		 }else{
			 q+=" suspendidov=0,";
		 }
		 if (suspendidoc){
			 q+=" suspendidoc=1, ";
		 }else{
			 q+=" suspendidoc=0, ";
		 }
		 q+=" iduser_modificador='"+iduser+"', ";
		 q+=" modificacion=getdate(), ";
		 q+=" clasificacion='"+clasificacion+"' ";
		 if (fecha_actualizacion){
			 q+=",actualizacion=getdate(),iduser_actualizador='"+iduser+"' ";
		 }
		 q+=" where idarticulo like '"+idarticulo+"' ";
		 return q;
	}
	

	public Object[][] getCurrentValues(String idarticulo){
		String q="";
		q+="select descripcion,precio2,costo,precio5,politicaprecios,";
		q+="suspendidov,suspendidoc,puntopedido from v_ma_articulos where idarticulo like '"+idarticulo+"' ";
		q+="";
		Object[][] results=this.getResults(q);
		return results;
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
		q+="clasifiacion,clasificacion_old,idoperacion) ";
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
		q+="isnull(r.descripcion,''),";
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
		q+="m.clasificacion, ";
		q+="isnull(e.categoria_rotacion,'D'), ";
		q+="isnull(e.rotacion,0), ";
		q+="isnull(r.idrubro,'') ";
		q+="FROM v_ma_articulos m left outer join v_ta_rubros r on m.idrubro = r.idrubro ";
		q+="left outer join v_mv_stock s on m.idarticulo=s.idarticulo and s.anulado=0 ";
		q+="left outer join b_articulos_abc_evolution e ";
		q+=" on m.idarticulo=e.idarticulo ";
		q+="where m.idarticulo like '"+idarticulo+"' ";
		q+="group by ";
		q+="m.idarticulo,";
		q+="m.descripcion,";
		q+="m.cuentaproveedor,";
		q+="m.politicaprecios,";
		q+="r.descripcion,";
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
		q+="m.clasificacion, ";
		q+="isnull(e.categoria_rotacion,'D'), ";
		q+="isnull(e.rotacion,0), ";
		q+="isnull(r.idrubro,'') ";
		return q;
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
	public boolean exist_equivalencia(String idarticulo1){
		boolean exist=false;
		String q="";
		q+="select * from b_articulos_equivalencias ";
		q+="where ((idarticulo1 like '"+idarticulo1+"') ";
		q+="or (idarticulo2 like '"+idarticulo1+"')) ";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				exist=true;
			}
		}
		return exist;
	}
	
	public String getDeleteEquivalencia(String idarticulo1){
		String q="";
		q+="delete from b_articulos_equivalencias ";
		q+="where ((idarticulo1 like '"+idarticulo1+"') ";
		q+="or (idarticulo2 like '"+idarticulo1+"')) ";
		return q;
	}	
	
	public String getQueryArticulos(int top,String idproveedor,String linea,String desde,String hasta){
	String q="";
	q+="select top "+top+" 0,idarticulo,descripcion,";
	q+="descripabrev,cuentaproveedor,precio5,";
	q+="politicaprecios,costo,precio2 ";
	q+="from v_ma_articulos   ";
	String where="";
	if (idproveedor.compareTo("")!=0){
		where+=" cuentaproveedor like '"+idproveedor+"' ";
	}
	if (linea.compareTo("")!=0){
		if (where.length()>0) where+=" and ";
		where+=" descripabrev like '"+linea+"' ";
	}
	if (where.length()>0) where+=" and ";
	where+="idarticulo between '"+desde+"' and '"+hasta+"'";
	q+="where "+where+" ";
	q+="order by idarticulo";
	q+="";
	return q;
	}
	
	public Object[][] getArticulos(int top,String idproveedor,String linea,String desde,String hasta){
		Object[][] results=this.getResults(this.getQueryArticulos(top, idproveedor, linea, desde, hasta));
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
		q+="select top 1 substring(v.idarticulo,0,4) ";
		q+="FROM v_ma_articulos v left outer join v_ta_rubros r on v.idrubro=r.idrubro ";
		q+="where  r.descripcion like '"+linea+"' ";
		q+="group by substring(v.idarticulo,0,4) ";
		q+="order by count(*) desc";
		return q;
	}
	
	private String getLineQuery(String linea) {
		String q = "";
		q = q + "select top 1 descripcion ";
		q = q + "FROM v_ta_rubros ";
		q = q + "where idrubro like '"+linea+"'";
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
	
	public Object[][] getRubro(String codigo){
		String q="";
		q=q+" select idrubro, descripcion ";
		q=q+" from v_ta_rubros ";
		q=q+" where idrubro like '"+codigo+"' ";
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
	
	public Object[][] getPrecioProveedor(String idarticulo,String idproveedor){
		String q="";
		q=q+" select c.precio5 from b_alias b ";
		q=q+" left outer join b_codigos c ";
		q=q+" on b.idcodigo=c.idcodigo ";
		q=q+" and b.lineaproveedor=c.lineaproveedor ";
		q=q+" and b.idproveedor =c.idproveedor ";
		q=q+" where b.idarticulo like '"+idarticulo+"' "; 
		q=q+" and b.idproveedor like '"+idproveedor+"' ";
		return getResults(q);
	}
	
	public String getQueryAlias(String idarticulo){
		String q="select a.idcodigo,a.lineaproveedor,a.idproveedor,m.descripcion,c.precio5,c.politicaprecio,p.mcosto*c.precio5,p.mcosto*p.mclase2*c.precio5,convert(varchar(10),c.ultimo_upd,105) ";
		q=q+" from b_alias a left outer join ma_cuentas m on a.idproveedor = m.codigo ";
		q=q+" left outer join b_codigos c on a.idproveedor=c.idproveedor and a.idcodigo = c.idcodigo and a.lineaproveedor=c.lineaproveedor ";
		q=q+" left outer join v_ta_politicaprecios p on c.politicaprecio=p.codigo ";
		q=q+" where a.idarticulo like '"+idarticulo+"'  ";
		System.out.println(q);
		return q;
	}
	
	public Object[][] getAlias(String idarticulo){
		return getResults(this.getQueryAlias(idarticulo));
	}
	
	
	public Object[][] getStockPuntoControl(String idarticulo,String iddeposito,String idmovimiento){
		return this.getResults(this.getQueryStock(idarticulo,iddeposito,idmovimiento));
	}
	
	public Object[][] getDepositos(){
		String q="select iddeposito,descripcion from v_ta_deposito";
		Object[][] results=this.getResults(q);
		
		return results;
	}
	public Object[][] getPuntoControl(String idarticulo,String iddeposito){
		String q="";
		q+="select top 1 c.idcontrol,convert(varchar(10),c.fecha,105),c.movimiento,ci.cantidad ";
		q+="from b_control c ";
		q+="left outer join b_control_items ci on c.idcontrol=ci.idcontrol ";
		q+="where  ";
		q+="isnull(c.punto,0)=1 and isnull(c.eliminar,0)=0 and "; 
		q+="ci.idarticulo like '"+idarticulo+"' ";
		if (iddeposito.compareTo("")!=0){
			q+=" and c.iddeposito like '"+iddeposito+"' ";
		}
		q+="order by c.idcontrol desc ";
		System.out.println("punto control "+q);
		Object[][] tmp=this.getResults(q);
		
		return tmp;
	}
	
	public String getQueryStock(String idarticulo,String iddeposito,String idmovimiento){
		String q="";
		q+="Select convert(varchar(10),s.fecha,105),s.cuentaproveedor,m.descripcion,s.tc,s.idcomprobante,";
		q+="(case when cantidadud >=0 then s.cantidadud else 0 end) as entrada,";
		q+="(case when cantidadud <0 then -s.cantidadud else 0 end) as salida,";
		q+=" 0, " ;
		q+=" s.iddeposito,d.descripcion " ;
		q+=" from v_mv_stock s left outer join ma_cuentas m";
		q+=" on s.cuentaproveedor=m.codigo ";
		q+=" left outer join v_ta_deposito d  ";
		q+=" on s.iddeposito=d.iddeposito  ";
		q+=" where s.idarticulo like '"+idarticulo+"' and s.anulado=0 ";
		if (iddeposito.compareTo("")!=0){
			q+=" and s.iddeposito like '"+iddeposito+"' ";
		}
		q+=" and s.id >= "+idmovimiento;
		q+=" order by s.fecha";
		System.out.println("stock punto control "+q);
		return q;
	}
	
	public String getQueryStock(String idarticulo,String iddeposito){
		String q="";
		q+="Select convert(varchar(10),s.fecha,105),s.cuentaproveedor,m.descripcion,s.tc,s.idcomprobante,";
		q+="(case when cantidadud >=0 then s.cantidadud else 0 end) as entrada,";
		q+="(case when cantidadud <0 then -s.cantidadud else 0 end) as salida,";
		q+=" 0, " ;
		q+=" s.iddeposito,d.descripcion " ;
		q+=" from v_mv_stock s left outer join ma_cuentas m";
		q+=" on s.cuentaproveedor=m.codigo ";
		q+=" left outer join v_ta_deposito d  ";
		q+=" on s.iddeposito=d.iddeposito  ";
		q+=" where s.idarticulo like '"+idarticulo+"' and s.anulado=0 ";
		if (iddeposito.compareTo("")!=0){
			q+=" and s.iddeposito like '"+iddeposito+"' ";
		}
		q+=" order by s.fecha desc";
		return q;
	}
	
	public String getQuerySaldoStock(String idarticulo,String iddeposito){
		String q="";
		q+="Select isnull(sum(s.cantidadud),0) ";
		q+=" from v_mv_stock s ";
		q+=" where s.idarticulo like '"+idarticulo+"' and s.anulado=0 ";
		if (iddeposito.compareTo("")!=0){
			q+=" and s.iddeposito like '"+iddeposito+"' ";
		}
		return q;
	}
	public double getSaldoStock(String idarticulo,String iddeposito){
		Object[][] results= this.getResults(this.getQuerySaldoStock(idarticulo,iddeposito));
		double stock=0.0;
		if (results!=null){
			if (results.length>0){
				try {
					stock=new Double((String) results[0][0]);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return stock;
	}
	
	public Object[][] getStock(String idarticulo,String iddeposito){
		String q=this.getQueryStock(idarticulo,iddeposito);
		System.out.println(q);
		return this.getResults(q);
	}
	
	public String getQueryCompras(String idarticulo){
		String q="";
		q+="SELECT 'PEP' as 'tc',P.IDPEDIDO,P.CLIENTE,P.CLIENTE_DESCRIPCION,convert(varchar(10),P.FECHA_CREACION,105) as fecha,P.ESTADO,I.Cantidad,  I.idpedido_pdc "; 
		q+="FROM B_PEP P LEFT OUTER JOIN B_PEP_ITEM I ON P.IDPEDIDO=I.IDPEDIDO  WHERE I.IDARTICULO LIKE '"+idarticulo+"' ";
		q+="AND P.ELIMINAR =0   ";
		q+="union ";
		q+="SELECT 'PDP' as 'tc',P.IDPEDIDO,P.cuenta,P.DESCRIPCION,convert(varchar(10),P.FECHA,105) as fecha,P.ESTADO,I.Cantidad,'' ";
		q+="FROM B_PDP P LEFT OUTER JOIN B_PDP_ITEMs I ON P.IDPEDIDO=I.IDPEDIDO  WHERE I.IDARTICULO LIKE '"+idarticulo+"' ";
		q+="AND P.ELIMINAR =0  order by fecha desc,tc,P.IDPEDIDO desc  ";
		System.out.println(q);
		return q;
	}
	
	public String getUserValidacion(String password){
		String q="select iduser from b_users where pass like '"+password+"' ";
		String idvendedor="";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				idvendedor=(String) results[0][0];
			}
		}
		return idvendedor;
	}
	
	public Object[][] getCompras(String idarticulo){
		return this.getResults(this.getQueryCompras(idarticulo));
	}
	
	public String getProximoAJM_Ceros(){
		String c="";
		int i=this.getProximoAJM();
		String aux=""+i;
		while (aux.length()<8){
			aux="0"+aux;
		}
		c=aux;
		return c;
	}
	
	public String getInsertQueryVMVStock(String tc,String idcomprobante,String idarticulo,String descripcion,String cantidad,int sec,String fecha,String precio,String iddeposito){
		String q="";
		
		double price=0.0;
		try {
			precio=precio.replace(",", "");
			price=new Double(precio);
		}catch(Exception e){
			
		}
		double qty=0.0;
		try {
			cantidad=cantidad.replace(",", "");
			qty=new Double(cantidad);
		}catch(Exception e){
			
		}
		q="insert into v_mv_stock ";
		q=q+"(tc,idcomprobante,secuencia,fecha,idarticulo,descripcion,idunidad,cantidadud,cantidad,costo,precioventa,stock,iddeposito,UNEGOCIO)";
		q=q+"values ";
		q=q+"('"+tc+"','"+idcomprobante+"','"+sec+"',getdate(),'"+idarticulo+"','"+descripcion+"','   1',"+qty+","+qty+","+price+",0.0,0,'"+iddeposito+"','   1')";
		return q;
		}
}
