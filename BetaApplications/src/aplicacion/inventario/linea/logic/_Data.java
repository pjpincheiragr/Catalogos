package aplicacion.inventario.linea.logic;

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
	public boolean Update(
			String idarticulo,
			String descripcion,
			String linea,
			String idproveedor,
			String politica,
			String lista,
			String costo,
			String publico,
			String minimo) {
		clearBatch();
		String q=this.getUpdate(idarticulo, descripcion, linea, idproveedor, politica, lista, costo, publico,minimo);
		System.out.println(q);
		addBatch(q);
		return executeBatch();
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
			String minimo) {
		clearBatch();
		addBatch(this.getInsert(idarticulo, descripcion, linea, idproveedor, politica, lista, costo, publico,minimo));
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
			String minimo) {
		String q="";
		q+="insert into v_ma_articulos (idarticulo,descripcion,DESCRIPABREV,cuentaproveedor,politicaPRECIOS,precio5,costo,precio2,PUNTOPEDIDO) values (";
		q+="'"+idarticulo+"',";
		q+="'"+descripcion+"',";
		q+="'"+linea+"',";
		q+="'"+idproveedor+"',";
		q+="'"+politica+"',";
		q+=""+lista+",";
		q+=""+costo+",";
		q+=""+publico+",";
		q+=""+minimo+")";
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
			String minimo) {
		 String q="update v_ma_articulos ";
		 q+=" set descripcion='"+descripcion+"', ";
		 q+=" descripabrev='"+linea+"', ";
		 q+=" cuentaproveedor='"+idproveedor+"', ";
		 q+=" politicaprecios='"+politica+"', ";
		 q+=" precio2="+publico+", ";
		 q+=" precio5="+lista+", ";
		 q+=" costo="+costo+", ";
		 q+=" puntopedido="+minimo+" ";
		 q+=" where idarticulo like '"+idarticulo+"' ";
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

	public Object[][] getControl(String idcontrol){
		Object[][] results=getResults(this.getControlQuery(idcontrol));
		return results;
	}
	
	private String getControlQuery(String idcontrol) {
		String q = "";
		q+="select idcontrol,";
		q+="linea,";
		q+="idusuario ";
		q+="FROM b_control ";
		q+="where idcontrol like '"+idcontrol+"' ";
		q+="order by idcontrol desc";
		return q;
	}
	public Object[][] getLineas(String desde,String hasta,String stock_relevante,String linea){
		return this.getResults(this.getLineasQuery(desde, hasta,stock_relevante,linea));
	}
	public Object[][] getLineasSimple(String desde,String hasta,String stock_relevante,String linea){
		return this.getResults(this.getLineasQuerySimple(desde, hasta,stock_relevante,linea));
	}
	public Object[][] getLineasActualizacion(String desde,String hasta,String stock_relevante,String linea){
		return this.getResults(this.getLineasQueryActualizacion(desde, hasta,stock_relevante,linea));
	}
	
	public String getLineasQuery(String desde,String hasta,String stock_relevante,String linea){
		//linea|valor|items|proveedor|nombre|ventas|compras|mov|stock|actualizacion|control
		String q=" ";
		q+="select a.descripabrev as linea, ";
		q+="round(sum(isnull(s.cantidad,0)*isnull(a.costo,0)),2) as valor, ";
		q+="sum(isnull(s.cantidad,0)) as items,isnull(m.codigo,'') as proveedor,ltrim(isnull(m.descripcion,'')) as nombre, ";
		q+="sum(case when ((s.tc like 'TKFC' or s.tc like 'FVN' or s.tc like 'TK' or s.tc like 'FP' or s.tc like 'FC'  or s.tc like 'RM') and (s.fecha between '"+desde+"' and '"+hasta+"')) then -s.cantidadud else 0 end) as ventas, ";
		q+="sum(case when ((s.tc like 'FCC' or s.tc like 'FCN' or s.tc like 'FPC'  or s.tc like 'RMC') and (s.fecha between '"+desde+"' and '"+hasta+"')) then s.cantidadud else 0 end) as compras, ";
		q+="sum(abs(case when (s.fecha between '"+desde+"' and '"+hasta+"') then isnull(s.cantidad,0) else 0 end )) as 'movimientos en cantidades', ";
		q+="sum(case when (s.fecha between '"+desde+"' and '"+hasta+"') then 1 else 0 end) as 'operaciones de stock', ";
		q+="convert(varchar(10),max(isnull(c.ultimo_upd,'')),105) as 'actualizacion', ";
		q+="convert(varchar(10),max(case when s.tc like 'ctrl' then s.fecha else '' end),105) as control, ";
		q+="sum(case when ((s.tc like 'TKFC' or s.tc like 'FVN' or s.tc like 'TK' or s.tc like 'FP' or s.tc like 'FC') and (s.fecha between '"+desde+"' and '"+hasta+"')) then -s.cantidadud*isnull(a.precio2,0) else 0 end) as '$ventas',";
		q+="sum(case when ((s.tc like 'TKFC' or s.tc like 'FVN' or s.tc like 'TK' or s.tc like 'FP' or s.tc like 'FC') and (s.fecha between '"+desde+"' and '"+hasta+"')) then -s.cantidadud*(a.precio2-a.precio2/isnull(p.mclase2,1.5)) else 0 end) as 'renta $',";
		q+="sum(case when ((s.tc like 'FCC' or s.tc like 'FCN' or s.tc like 'FPC'  or s.tc like 'RMC') and (s.fecha between '"+desde+"' and '"+hasta+"')) then s.cantidadud*isnull(a.costo*1.21,0) else 0 end) as '$compras' ";
		q+="from v_ma_articulos a ";
		q+="left outer join v_ta_politicaprecios p ";
		q+="on a.politicaprecios=p.codigo ";
		q+="left outer join b_alias b  ";
		q+="on a.idarticulo=b.idarticulo and a.cuentaproveedor=b.idproveedor ";
		q+="left outer join b_codigos c ";
		q+="on b.idcodigo=c.idcodigo and b.idproveedor=c.idproveedor and b.lineaproveedor=c.lineaproveedor ";
		q+="left outer join ma_cuentas m ";
		q+="on a.cuentaproveedor = m.codigo ";
		q+="left outer join v_mv_stock s ";
		q+="on a.idarticulo=s.idarticulo and s.anulado=0 ";
		q+="where isnull(a.descripabrev,'') not like '' and a.suspendidov=0 and a.suspendidoc=0 ";
		if (linea.compareTo("")!=0){
			q+=" and isnull(a.descripabrev,'') like '%"+linea+"%' ";
		}
		q+=" group by a.descripabrev,isnull(m.codigo,''),ltrim(isnull(m.descripcion,'')) ";
		if (stock_relevante.compareTo("")!=0){
				q+=" having sum(isnull(s.cantidad,0)) > "+stock_relevante;	
		}
		q+=" order by a.descripabrev,isnull(m.codigo,''),ltrim(isnull(m.descripcion,'')) ";
		System.out.println(q);
		return q;
	}
	
	public String getLineasSingleQuery(String desde,String hasta,String stock_relevante,String linea){
		//linea|valor|items|proveedor|nombre|ventas|compras|mov|stock|actualizacion|control
		String q=" ";
		q+="select a.descripabrev as linea, ";
		q+="round(sum(isnull(s.cantidad,0)*isnull(a.costo,0)),2) as valor, ";
		q+="sum(isnull(s.cantidad,0)) as items,isnull(m.codigo,'') as proveedor,ltrim(isnull(m.descripcion,'')) as nombre, ";
		q+="sum(case when ((s.tc like 'TKFC' or s.tc like 'FVN' or s.tc like 'TK' or s.tc like 'FP' or s.tc like 'FC'  or s.tc like 'RM') and (s.fecha between '"+desde+"' and '"+hasta+"')) then -s.cantidadud else 0 end) as ventas, ";
		q+="sum(case when ((s.tc like 'FCC' or s.tc like 'FCN' or s.tc like 'FPC'  or s.tc like 'RMC') and (s.fecha between '"+desde+"' and '"+hasta+"')) then s.cantidadud else 0 end) as compras, ";
		q+="sum(abs(case when (s.fecha between '"+desde+"' and '"+hasta+"') then isnull(s.cantidad,0) else 0 end )) as 'movimientos en cantidades', ";
		q+="sum(case when (s.fecha between '"+desde+"' and '"+hasta+"') then 1 else 0 end) as 'operaciones de stock', ";
		q+="convert(varchar(10),max(isnull(c.ultimo_upd,'')),105) as 'actualizacion', ";
		q+="convert(varchar(10),max(case when s.tc like 'ctrl' then s.fecha else '' end),105) as control, ";
		q+="sum(case when ((s.tc like 'TKFC' or s.tc like 'FVN' or s.tc like 'TK' or s.tc like 'FP' or s.tc like 'FC') and (s.fecha between '"+desde+"' and '"+hasta+"')) then -s.cantidadud*isnull(a.precio2,0) else 0 end) as '$ventas',";
		q+="sum(case when ((s.tc like 'TKFC' or s.tc like 'FVN' or s.tc like 'TK' or s.tc like 'FP' or s.tc like 'FC') and (s.fecha between '"+desde+"' and '"+hasta+"')) then -s.cantidadud*(a.precio2-a.precio2/isnull(p.mclase2,1.5)) else 0 end) as 'renta $',";
		q+="sum(case when ((s.tc like 'FCC' or s.tc like 'FCN' or s.tc like 'FPC'  or s.tc like 'RMC') and (s.fecha between '"+desde+"' and '"+hasta+"')) then s.cantidadud*isnull(a.costo*1.21,0) else 0 end) as '$compras' ";
		q+="from v_ma_articulos a ";
		q+="left outer join v_ta_politicaprecios p ";
		q+="on a.politicaprecios=p.codigo ";
		q+="left outer join b_alias b  ";
		q+="on a.idarticulo=b.idarticulo and a.cuentaproveedor=b.idproveedor ";
		q+="left outer join b_codigos c ";
		q+="on b.idcodigo=c.idcodigo and b.idproveedor=c.idproveedor and b.lineaproveedor=c.lineaproveedor ";
		q+="left outer join ma_cuentas m ";
		q+="on a.cuentaproveedor = m.codigo ";
		q+="left outer join v_mv_stock s ";
		q+="on a.idarticulo=s.idarticulo and s.anulado=0 ";
		q+="where isnull(a.descripabrev,'') not like '' and a.suspendidov=0 and a.suspendidoc=0 ";
		if (linea.compareTo("")!=0){
			q+=" and isnull(a.descripabrev,'') like '%"+linea+"%' ";
		}
		q+=" group by a.descripabrev,isnull(m.codigo,''),ltrim(isnull(m.descripcion,'')) ";
		if (stock_relevante.compareTo("")!=0){
				q+=" having sum(isnull(s.cantidad,0)) > "+stock_relevante;	
		}
		q+=" order by a.descripabrev,sum(isnull(s.cantidad,0)) desc ";
		System.out.println(q);
		return q;
	}
	
	public String getLineasQuerySimple(String desde,String hasta,String stock_relevante,String linea){
		String q=" ";
		q+="select a.descripabrev as linea, ";
		q+="round(sum(isnull(s.cantidad,0)*isnull(a.costo,0)),2) as valor, ";
		q+="sum(isnull(s.cantidad,0)) as items,isnull(m.codigo,'') as proveedor,ltrim(isnull(m.descripcion,'')) as nombre, ";
		q+="sum(case when ((s.tc like 'TKFC' or s.tc like 'FVN' or s.tc like 'TK' or s.tc like 'FP' or s.tc like 'FC'  or s.tc like 'RM') and (s.fecha between '"+desde+"' and '"+hasta+"')) then -s.cantidadud else 0 end) as ventas, ";
		q+="sum(case when ((s.tc like 'FCC' or s.tc like 'FCN' or s.tc like 'FPC'  or s.tc like 'RMC') and (s.fecha between '"+desde+"' and '"+hasta+"')) then s.cantidadud else 0 end) as compras, ";
		q+="sum(abs(case when (s.fecha between '"+desde+"' and '"+hasta+"') then isnull(s.cantidad,0) else 0 end )) as 'movimientos en cantidades', ";
		q+="sum(case when (s.fecha between '"+desde+"' and '"+hasta+"') then 1 else 0 end) as 'operaciones de stock', ";
		q+="convert(varchar(10),max(isnull(c.ultimo_upd,'')),105) as 'actualizacion', ";
		q+="convert(varchar(10),max(case when s.tc like 'ctrl' then s.fecha else '' end),105) as control, ";
		q+="sum(case when ((s.tc like 'TKFC' or s.tc like 'FVN' or s.tc like 'TK' or s.tc like 'FP' or s.tc like 'FC') and (s.fecha between '"+desde+"' and '"+hasta+"')) then -s.cantidadud*isnull(a.precio2,0) else 0 end) as '$ventas',";
		q+="sum(case when ((s.tc like 'TKFC' or s.tc like 'FVN' or s.tc like 'TK' or s.tc like 'FP' or s.tc like 'FC') and (s.fecha between '"+desde+"' and '"+hasta+"')) then -s.cantidadud*(a.precio2-a.precio2/isnull(p.mclase2,1.5)) else 0 end) as 'renta $',";
		q+="sum(case when ((s.tc like 'FCC' or s.tc like 'FCN' or s.tc like 'FPC'  or s.tc like 'RMC') and (s.fecha between '"+desde+"' and '"+hasta+"')) then s.cantidadud*isnull(a.costo*1.21,0) else 0 end) as '$compras' ";
		q+="from v_ma_articulos a ";
		q+="left outer join v_ta_politicaprecios p ";
		q+="on a.politicaprecios=p.codigo ";
		q+="left outer join b_alias b  ";
		q+="on a.idarticulo=b.idarticulo and a.cuentaproveedor=b.idproveedor ";
		q+="left outer join b_codigos c ";
		q+="on b.idcodigo=c.idcodigo and b.idproveedor=c.idproveedor and b.lineaproveedor=c.lineaproveedor ";
		q+="left outer join ma_cuentas m ";
		q+="on a.cuentaproveedor = m.codigo ";
		q+="left outer join v_mv_stock s ";
		q+="on a.idarticulo=s.idarticulo and s.anulado=0 ";
		q+="where isnull(a.descripabrev,'') not like '' and a.suspendidov=0 and a.suspendidoc=0 ";
		if (linea.compareTo("")!=0){
			q+=" and isnull(a.descripabrev,'') like '"+linea+"' ";
		}
		q+=" group by a.descripabrev,isnull(m.codigo,''),ltrim(isnull(m.descripcion,'')) ";
		if (stock_relevante.compareTo("")!=0){
				q+=" having sum(isnull(s.cantidad,0)) > "+stock_relevante;	
		}
		q+=" order by a.descripabrev,isnull(m.codigo,''),ltrim(isnull(m.descripcion,'')) ";
		System.out.println(q);
		return q;
	}
	
	public String getLineasQueryActualizacion(String desde,String hasta,String stock_relevante,String linea){
		String q=" ";
		q+="select a.descripabrev as linea, ";
		q+="round(sum(isnull(s.cantidad,0)*isnull(a.costo,0)),2) as valor, ";
		q+="sum(isnull(s.cantidad,0)) as items,isnull(m.codigo,'') as proveedor,ltrim(isnull(m.descripcion,'')) as nombre, ";
		q+="sum(case when ((s.tc like 'TKFC' or s.tc like 'FVN' or s.tc like 'TK' or s.tc like 'FP' or s.tc like 'FC'  or s.tc like 'RM') and (s.fecha between '"+desde+"' and '"+hasta+"')) then -s.cantidadud else 0 end) as ventas, ";
		q+="sum(case when ((s.tc like 'FCC' or s.tc like 'FCN' or s.tc like 'FPC'  or s.tc like 'RMC') and (s.fecha between '"+desde+"' and '"+hasta+"')) then s.cantidadud else 0 end) as compras, ";
		q+="sum(abs(case when (s.fecha between '"+desde+"' and '"+hasta+"') then isnull(s.cantidad,0) else 0 end )) as 'movimientos en cantidades', ";
		q+="sum(case when (s.fecha between '"+desde+"' and '"+hasta+"') then 1 else 0 end) as 'operaciones de stock', ";
		q+="convert(varchar(10),max(isnull(c.ultimo_upd,'')),105) as 'actualizacion', ";
		q+="convert(varchar(10),max(case when s.tc like 'ctrl' then s.fecha else '' end),105) as control, ";
		q+="sum(case when ((s.tc like 'TKFC' or s.tc like 'FVN' or s.tc like 'TK' or s.tc like 'FP' or s.tc like 'FC') and (s.fecha between '"+desde+"' and '"+hasta+"')) then -s.cantidadud*isnull(a.precio2,0) else 0 end) as '$ventas',";
		q+="sum(case when ((s.tc like 'TKFC' or s.tc like 'FVN' or s.tc like 'TK' or s.tc like 'FP' or s.tc like 'FC') and (s.fecha between '"+desde+"' and '"+hasta+"')) then -s.cantidadud*(a.precio2-a.precio2/isnull(p.mclase2,1.5)) else 0 end) as 'renta $',";
		q+="sum(case when ((s.tc like 'FCC' or s.tc like 'FCN' or s.tc like 'FPC'  or s.tc like 'RMC') and (s.fecha between '"+desde+"' and '"+hasta+"')) then s.cantidadud*isnull(a.costo*1.21,0) else 0 end) as '$compras' ";
		q+="from v_ma_articulos a ";
		q+="left outer join v_ta_politicaprecios p ";
		q+="on a.politicaprecios=p.codigo ";
		q+="left outer join b_alias b  ";
		q+="on a.idarticulo=b.idarticulo and a.cuenta_actualizacion=b.idproveedor ";
		q+="left outer join b_codigos c ";
		q+="on b.idcodigo=c.idcodigo and b.idproveedor=c.idproveedor and b.lineaproveedor=c.lineaproveedor ";
		q+="left outer join ma_cuentas m ";
		q+="on a.cuenta_actualizacion = m.codigo ";
		q+="left outer join v_mv_stock s ";
		q+="on a.idarticulo=s.idarticulo and s.anulado=0 ";
		q+="where isnull(a.descripabrev,'') not like '' and a.suspendidov=0 and a.suspendidoc=0 ";
		if (linea.compareTo("")!=0){
			q+=" and isnull(a.descripabrev,'') like '"+linea+"' ";
		}
		q+=" group by a.descripabrev,isnull(m.codigo,''),ltrim(isnull(m.descripcion,'')) ";
		if (stock_relevante.compareTo("")!=0){
				q+=" having sum(isnull(s.cantidad,0)) > "+stock_relevante;	
		}
		q+=" order by a.descripabrev,isnull(m.codigo,''),ltrim(isnull(m.descripcion,'')) ";
		System.out.println(q);
		return q;
	}
	public String getUpdateQuery(String idcontrol,String fecha){
		String q="";
		q+="update b_control set ultima_modificacion=getdate(),fecha='"+fecha+"' ";
		q+="where idcontrol like 'idcontrol' ";
		return q;
	}
	
	public String getDeleteStock(String idcontrol){
		String q="";
		q+="delete from v_mv_stock  ";
		q+="where tc like 'CTRL' and idcomprobante like '"+idcontrol+"' ";
		return q;
	}
	public String getDeleteControl(String idcontrol){
		String q="";
		q+="delete from b_control where idcontrol like '"+idcontrol+"'";
		return q;
	}
	
	public String getDeleteControlItems(String idcontrol){
		String q="";
		q+="delete from b_control_items where idcontrol like '"+idcontrol+"'";
		return q;
	}
	public boolean exist(String idcontrol){
		boolean exist=false;
		Object[][] results = getResults(this.getControlQuery(idcontrol));
		if (results != null) {
			if (results.length > 0) {
			exist=true;
			}
		}
		return exist;
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
	
	/**
	 * carga datos idarticulo|descripcion|costo|stock|ventas
	 * @param idpedido
	 * @param idproveedor
	 * @param linea
	 * @param desde
	 * @param hasta
	 * @return
	 */
	public String getLineaItemsCreation(String idproveedor,String linea,String desde,String hasta) {
		// String linea=frame.get_txt_linea().getText();
		
		String q = "";
		q = q + " select m.idarticulo,m.descripcion, ";
		q = q + " CAST(m.costo AS decimal(10,2)), ";
		q = q + " ISNULL(s.cantidadud, 0) AS stk, ";
		q = q + " sum(case when (v.tc like 'FC' or v.tc like 'TK' or v.tc like 'TKFC' or v.tc like 'FP' or v.tc like 'FVN' ) then -v.cantidadud else 0 end)as Ventas ";
		q = q + " from v_ma_articulos m left outer join Stk_Stock_UDKG s ON ";
		q = q + " m.IDARTICULO = s.IdArticulo ";
		q = q + " left outer join v_mv_stock v ON ";
		q = q + " m.IDARTICULO = v.IdArticulo and v.anulado=0 ";
		q = q + " and v.fecha between '" + desde + "' and '"+hasta+"' ";
		q = q + " where m.descripabrev like '" + linea + "' ";
		q = q + " group by m.idarticulo,m.descripcion,m.costo,s.cantidadud ";
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
		String q="select a.idcodigo,a.lineaproveedor,a.idproveedor,m.descripcion,c.precio5,convert(varchar(10),c.ultimo_upd,105) ";
		q=q+" from b_alias a left outer join ma_cuentas m on a.idproveedor = m.codigo ";
		q=q+" left outer join b_codigos c on a.idproveedor=c.idproveedor and a.idcodigo = c.idcodigo and a.lineaproveedor=c.lineaproveedor ";
		q=q+" where a.idarticulo like '"+idarticulo+"'  ";
		return q;
	}
	
	public Object[][] getAlias(String idarticulo){
		return getResults(this.getQueryAlias(idarticulo));
	}
	
	
	public String getQueryControlItems(String idcontrol){
		String q="";
		q+="select articulos.idarticulo as idarticulo, ";
		q+="articulos.descripcion as descripcion, ";
		q+="articulos.costo, ";
		q+="isnull(stock.cantidadud,0) AS stk, ";
		q+="isnull(Control.cantidad, 0) as cant, ";
		q+="isnull(Control.etiqueta, 0) as etiqueta ";
		q+="FROM b_control ctr ";
		q+="left outer join  ";
		q+="V_MA_ARTICULOS articulos on ctr.linea=articulos.descripabrev ";
		q+="LEFT OUTER  JOIN  ";
		q+="Stk_Stock_UDKG stock ON "; 
		q+="articulos.IDARTICULO = stock.IdArticulo "; 
		q+="left outer join b_control_items control ";
		q+="on articulos.idarticulo=control.idarticulo and control.idcontrol=ctr.idcontrol "; 
		q+="where ctr.idcontrol like '"+idcontrol+"' ";
		q+="order by articulos.IDARTICULO  ";
		System.out.println(""+q);
		
		return q;
	}
	public String updateControlItem(String idcontrol,String idarticulo,double cantidad){
		String q="";
		q+="update b_control_items set cantidad="+cantidad+" ";
		q+="where idcontrol like '"+idcontrol+"' and idarticulo like '"+idarticulo+"'";
		q+="";
		return q;
	}
	public String deleteControlItem(String idcontrol,String idarticulo,double cantidad){
		String q="";
		q+="delete from  b_control_items ";
		q+="where idcontrol like '"+idcontrol+"' and idarticulo like '"+idarticulo+"'";
		q+="";
		return q;
	}
	public String insertControlItem(String idcontrol,String idarticulo,double cantidad){
		String q="";
		q+="insert into b_control_items (idcontrol,idarticulo,cantidad) ";
		q+="values ('"+idcontrol+"','"+idarticulo+"',"+cantidad+") ";
		q+="";
		return q;
	}
	
	public Object[][] getControlItems(String idcontrol){
		return this.getResults(this.getQueryControlItems(idcontrol));
	}
	public boolean existeItem(String idcontrol,String idarticulo){
		boolean exist=false;
		String q="";
		q+="select idarticulo from b_control_items ";
		q+="where idcontrol like '"+idcontrol+"' and idarticulo like '"+idarticulo+"' ";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				exist=true;	
			}
		}
		return exist;
	}
	public String getInsertQueryVMVStock(String tc,String idcomprobante,String idarticulo,String descripcion,String cantidad,int sec,String fecha,String precio){
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
		q=q+"('"+tc+"','"+idcomprobante+"','"+sec+"',getdate(),'"+idarticulo+"','"+descripcion+"','   1',"+qty+","+qty+","+price+",0.0,0,'   1','   1')";
		return q;
	}
	
	public String getItemsActualizadosQuery(String linea,String idproveedor,String fecha){
		String q="";
		q+="select a.idarticulo,(CASE WHEN datediff(day,max(c.ultimo_upd),getdate())<30 THEN 1 ELSE 0 END)  ";
		q+="from v_ma_articulos a ";
		q+="left outer join v_mv_stock s on a.idarticulo=s.idarticulo ";
		q+="left outer join b_alias b  on a.idarticulo=b.idarticulo and a.cuenta_actualizacion=b.idproveedor "; 
		q+="left outer join b_codigos c on b.idcodigo=c.idcodigo and b.idproveedor=c.idproveedor and b.lineaproveedor=c.lineaproveedor "; 
		q+="where a.descripabrev like '"+linea+"' and a.cuenta_actualizacion like '"+idproveedor+"'  AND isnull(S.ANULADO,0)=0 ";
		q+="and isnull(a.suspendidov,0)=0 and isnull(a.suspendidoc,0)=0 ";
		q+="group by a.idarticulo ";
		q+="";
		System.out.println(q);
		return q;
	}
	
	public String getItemsSinPoliticaQuery(String linea,String idproveedor){
		String q="";
		q+="select count(distinct a.idarticulo) ";
		q+="from v_ma_articulos a ";
		q+="where a.descripabrev like '"+linea+"' ";
		q+="and a.cuentaproveedor like '"+idproveedor+"' ";
		q+="and rtrim(ltrim(isnull(a.politicaprecios,''))) like '' and a.suspendidov=0 and a.suspendidoc=0";
		System.out.println(q);
		return q;
	}
	
	public String getItemsActualizadosStockQuery(String linea,String idproveedor,String fecha){
		String q="";
		q+="select a.idarticulo,(CASE WHEN datediff(day,max(c.ultimo_upd),getdate())<30 THEN 1 ELSE 0 END)  ";
		q+="from v_ma_articulos a ";
		q+="left outer join v_mv_stock s on a.idarticulo=s.idarticulo ";
		q+="left outer join b_alias b  on a.idarticulo=b.idarticulo and a.cuenta_actualizacion=b.idproveedor "; 
		q+="left outer join b_codigos c on b.idcodigo=c.idcodigo and b.idproveedor=c.idproveedor and b.lineaproveedor=c.lineaproveedor "; 
		q+="where a.descripabrev like '"+linea+"' and a.cuentaproveedor like '"+idproveedor+"'  AND isnull(S.ANULADO,0)=0 ";
		q+="and a.suspendidov=0 and a.suspendidoc=0 ";
		q+="group by a.idarticulo ";
		q+="HAVING sum(ISNULL(s.cantidadud,0))>0 ";
		q+="";
		System.out.println(q);
		return q;
	}
	
	public String getErrorsLineaQuery(String linea,String idproveedor,String desde,String hasta){
		String q="";
		q+="select ";
		q+="sum(case when ((s.tc not like 'TKFC' "; 
		q+="and s.tc not like 'FVN'  ";
		q+="and s.tc not like 'TK'  ";
		q+="and s.tc not like 'FP'  ";
		q+="and s.tc not like 'FC'   ";
		q+="and s.tc not like 'RM' ";
		q+="and s.tc not like 'FCN' ";
		q+="and s.tc not like 'RMC' ";
		q+="and s.tc not like 'FCC' ";
		q+="and s.tc not like 'FPC' ";
		q+=")  ";
		q+="and (s.fecha between '"+desde+"' and '"+hasta+"')) then abs(s.cantidadud) else 0 end) as ajustes ";
		q+="from v_ma_articulos a ";
		q+="left outer join v_mv_stock s ";
		q+="on a.idarticulo=s.idarticulo ";
		q+="where  a.suspendidov=0 and a.suspendidoc=0 and a.descripabrev like '"+linea+"' ";
		q+="and a.cuentaproveedor like '"+idproveedor+"' ";
		q+="and s.tc not like 'SI' ";
		return q;
	}
	public String getItemsLineaQuery(String linea,String idproveedor){
		String q="";
		q+="";
		q+="select count(distinct a.idarticulo) ";
		q+="from v_ma_articulos a ";
		q+="where a.descripabrev like '"+linea+"' ";
		q+="and a.cuentaproveedor like '"+idproveedor+"'  and isnull(a.suspendidov,0)=0 and isnull(a.suspendidoc,0)=0";
		System.out.println(q);
		return q;
	}
	
	public double getItemsLinea(String linea,String idproveedor){
		String q=this.getItemsLineaQuery(linea, idproveedor);
		double items=0;
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				items=new Double((String)results[0][0]);
			}
		}
		return items;
	}
	
	public double getItemsActualizados(String linea,String idproveedor,String fecha){
		String q=this.getItemsActualizadosQuery(linea, idproveedor, fecha);
		double actualizados=0;
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				for (int i=0;i<results.length;i++){
					double actualizado=new Double((String)results[i][1]);
					if (actualizado==1){
						actualizados++;
					}
				}
				
			}
		}
		return actualizados;
	}
	public double getItemsSinPolitica(String linea,String idproveedor){
		String q=this.getItemsSinPoliticaQuery(linea, idproveedor);
		double sin_politica=0;
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				sin_politica=new Double((String)results[0][0]);
			}
		}
		return sin_politica;
	}
	
	public double getItemsActualizadosStock(String linea,String idproveedor,String fecha){
		String q=this.getItemsActualizadosStockQuery(linea, idproveedor, fecha);
		double actualizados=0;
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				for (int i=0;i<results.length;i++){
					double actualizado=new Double((String)results[i][1]);
					if (actualizado==1){
						actualizados++;
					}
				}
				
			}
		}
		return actualizados;
	}
	
	public double getItemsEnStock(String linea,String idproveedor,String fecha){
		String q=this.getItemsActualizadosStockQuery(linea, idproveedor, fecha);
		double stock=0;
		Object[][] results=this.getResults(q);
		if (results!=null){
			stock=new Double(results.length);
		}
		return stock;
	}
	
	public double getErrorsLinea(String linea,String idproveedor,String desde,String hasta){
		String q=this.getErrorsLineaQuery(linea, idproveedor, desde, hasta);
		double errors=0;
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				errors=new Double((String)results[0][0]);
			}
		}
		return errors;
	}
}
