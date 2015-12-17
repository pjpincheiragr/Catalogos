package aplicacion.ventas.gestion.logic;
import aplicacion.modelo.logic.Data;

public class _Data extends Data{
	
	public void update_seguimiento(String idpedido,boolean seguimiento){
		String q="";
		String seg="0";
		if (seguimiento) seg="1";
		q+="update b_pdc set seguimiento="+seg+" where idpedido like '"+idpedido+"' ";
		this.clearBatch();
		System.out.println(q);
		this.addBatch(q);
		this.executeBatch();
	}
	
	public String getPedidosColor(String desde,String hasta){
		
		String q="";
		q+="";
		q+="select day(case when pedido.fecha_agenda is null then pedido.fecha_creacion else pedido.fecha_agenda end)";
		q+=",count(*) ";
		q+="from b_pdc pedido ";
		q+="left outer join b_pdc_rms remitos ";
		q+="on pedido.idpedido=remitos.idpedido and remitos.eliminado=0 ";
		q+="left outer join ma_cuentas cuenta ";
		q+="on pedido.cliente=cuenta.codigo ";
		q+="left outer join v_ta_vendedores vendedor ";
		q+="on pedido.idvendedor=vendedor.idvendedor ";
		q+="left outer join v_ta_vendedores creador ";
		q+="on pedido.idcreador=creador.idvendedor ";
		q+="left outer join b_pdc_item items ";
		q+="on pedido.idpedido=items.idpedido ";
		q+="left outer join v_ma_Articulos articulo ";
		q+="on items.idarticulo=articulo.idarticulo ";
		q+="where ";
		q+="(case when pedido.fecha_agenda is null then pedido.fecha_creacion else pedido.fecha_agenda end) "; 
		q+="between '"+desde+"' and '"+hasta+"' ";
		
		
		return q;
	}
	
	public String getPedidosQuery(String desde,String hasta,
			int top,boolean calendario){
		String q="";
		String _top="";
		if (top>0){
			_top=" top "+top;
		}
		q+="select "+_top+" pedido.idpedido,pedido.descripcion, ";
		q+="convert(varchar(10),isnull(pedido.fecha_agenda,pedido.ultima_modificacion),105) as agenda, ";
		q+="pedido.cliente, pedido.cliente_descripcion,isnull(pedido.total,0) as importe,isnull(vendedor.nombre,'') as vendedor,isnull(creador.nombre,'') as creador, ";
		q+="convert(varchar(10),pedido.fecha_creacion,105),pedido.seguimiento ";
		q+="from b_pdc pedido ";
		q+="left outer join b_pdc_rms remitos ";
		q+="on pedido.idpedido=remitos.idpedido and remitos.eliminado=0 ";
		q+="left outer join ma_cuentas cuenta ";
		q+="on pedido.cliente=cuenta.codigo ";
		q+="left outer join v_ta_vendedores vendedor ";
		q+="on pedido.idvendedor=vendedor.idvendedor ";
		q+="left outer join v_ta_vendedores creador ";
		q+="on pedido.idcreador=creador.idvendedor ";
		q+="left outer join b_pdc_item items ";
		q+="on pedido.idpedido=items.idpedido ";
		q+="left outer join v_ma_Articulos articulo ";
		q+="on items.idarticulo=articulo.idarticulo ";
		q+="where ";
		//q+="isnull(fvn.anulada,0)=0 and ";
		//q+="convert(varchar(10),isnull(pedido.fecha_agenda,pedido.ultima_modificacion),105) like '"+desde+"' ";
		//q+="isnull(pedido.fecha_agenda,pedido.fecha_creacion) between '"+desde+"' and '"+hasta+"' ";
		if (calendario){
			q+="convert(varchar(10),isnull(pedido.fecha_agenda,pedido.ultima_modificacion),105) like '"+desde+"' ";	
		}
		return q;
	}
	
	public String getPedidosEstadoQuery(String desde,String hasta,
			int top,boolean calendario){
		String q="";
		String _top="";
		if (top>0){
			_top=" top "+top;
		}
		q+="select "+_top+" pedido.idpedido,pedido.descripcion, ";
		q+="convert(varchar(10),isnull(pedido.fecha_agenda,pedido.ultima_modificacion),105) as agenda, ";
		q+="pedido.cliente, pedido.cliente_descripcion,isnull(pedido.total,0) as importe,isnull(vendedor.nombre,'') as vendedor,isnull(creador.nombre,'') as creador, ";
		q+="convert(varchar(10),pedido.fecha_creacion,105),";
		q+="(case when count(remitos.remito)=count(alfa.tc) and count(remitos.remito)>0 then 'Alfa' else ( ";
		q+="(case when count(remitos.remito)=count(fvn.tc) and fvn.anulada=0 and count(remitos.remito)>0  then 'Beta' else ( ";
		q+="(case when count(remitos.remito)>0 and isnull(rmx.anulada,0)=0 then 'RM' else '' end) )  end ";
		q+=")) end) as estado, ";
		q+="pedido.seguimiento ";
		q+="from b_pdc pedido ";
		q+="left outer join ma_cuentas cuenta ";
		q+="on pedido.cliente=cuenta.codigo ";
		q+="left outer join v_ta_vendedores vendedor ";
		q+="on pedido.idvendedor=vendedor.idvendedor ";
		q+="left outer join v_ta_vendedores creador ";
		q+="on pedido.idcreador=creador.idvendedor ";
		q+="left outer join b_pdc_item items ";
		q+="on pedido.idpedido=items.idpedido ";
		q+="left outer join v_ma_Articulos articulo ";
		q+="on items.idarticulo=articulo.idarticulo ";
		q+="left outer join b_pdc_rms remitos ";
		q+="on pedido.idpedido=remitos.idpedido and remitos.eliminado=0 ";
		q+="left outer join b_fvn fvn ";
		q+="on remitos.remito=fvn.idcomprobante_origen ";
		q+="left outer join v_mv_cpte alfa ";
		q+="on remitos.remito=alfa.comprobanteorigen ";
		q+="left outer join v_mv_cpte rmx ";
		q+="on remitos.remito=rmx.idcomprobante and rmx.tc like 'RM' ";
		q+="where ";
		//q+="isnull(fvn.anulada,0)=0 and ";
		//q+="convert(varchar(10),isnull(pedido.fecha_agenda,pedido.ultima_modificacion),105) like '"+desde+"' ";
		//q+="isnull(pedido.fecha_agenda,pedido.fecha_creacion) between '"+desde+"' and '"+hasta+"' ";
		if (calendario){
			q+="convert(varchar(10),isnull(pedido.fecha_agenda,pedido.ultima_modificacion),105) like '"+desde+"' ";	
		}
		return q;
	}
	public String getPedidoEstado(){
		String q="";
		q+="(case when count(remitos.remito)=count(alfa.tc) and count(remitos.remito)>0 then 'Alfa' else ( ";
		q+="(case when count(remitos.remito)=count(fvn.tc) and fvn.anulada=0 and count(remitos.remito)>0  then 'Beta' else ( ";
		q+="(case when count(remitos.remito)>0 and isnull(rmx.anulada,0)=0 then 'RM' else '' end) )  end ";
		q+=")) end)";
		q+="left outer join b_pdc_rms remitos ";
		q+="on pedido.idpedido=remitos.idpedido and remitos.eliminado=0 ";
		q+="left outer join b_fvn fvn ";
		q+="on remitos.remito=fvn.idcomprobante_origen ";
		q+="left outer join v_mv_cpte alfa ";
		q+="on remitos.remito=alfa.comprobanteorigen ";
		q+="left outer join v_mv_cpte rmx ";
		q+="on remitos.remito=rmx.idcomprobante and rmx.tc like 'RM' ";
		
		return q;
	}
	/*
	public Object[][] getPedidos(String desde,String hasta,
			String idcliente,String cliente_descripcion,
			String idvendedor,String vendedor,
			String idarticulo,String idarticulo_descripcion,String idarticulo_linea,
			String idpedido,String idpedido_descripcion){
		return this.getResults(this.getPedidosQuery(desde, hasta,idcliente,cliente_descripcion,idvendedor,vendedor,idarticulo,idarticulo_descripcion,idarticulo_linea,idpedido,idpedido_descripcion));
	}*/
	
	private String getClientQuery(String idcliente){
		String q="";
		q=q+" select M.codigo,M.descripcion,";
		q=q+" isnull(a.telefono,''),";
		q=q+" isnull(a.fax,''),";
		q=q+" isnull(a.mail,''),";
		q=q+" isnull(a.contacto,''),";
		q=q+" isnull(a.localidad,''),";
		q=q+" isnull(a.observaciones,''),";
		q=q+" a.iva, ";
		q=q+" a.numero_documento, ";
		q=q+" a.idcond_cpra_vta, ";
		q=q+" t.descripcion, ";
		q=q+" isnull(a.calle,''),isnull(ltrim(a.numero),''),isnull(a.piso,''),ltrim(a.cpostal), ";
		q=q+" a.provincia,st.descripcion,tip.descripcion,a.documento_tipo,isnull(td.descripcion,'') ";
		q=q+" FROM MA_CUENTAS M LEFT OUTER JOIN ";
		q=q+" MA_CUENTASADIC  a  ";
		q=q+" ON M.CODIGO = a.CODIGO left outer join v_ta_cpra_vta t ";
		q=q+" on a.idcond_cpra_vta = t.idcond_cpra_vta ";
		q=q+" left outer join ta_condiva tip ";
		q=q+" on a.iva=tip.codigo ";
		q=q+" left outer join ta_estados st ";
		q=q+" on a.provincia=st.codigo ";
		q=q+" left outer join ta_tipodocumento td on a.documento_tipo=td.codigo ";
		q=q+" where M.codigo like '"+idcliente+"'";
		
		return q;
	}
	
	public Object[][] getCliente(String idcliente){
		String q=this.getClientQuery(idcliente);
		System.out.println(q);
		Object[][] results=this.getResults(q);
		return results;
	}
	
	public void updatePedidosConSeguimiento(){
		
		String q="update b_pdc set fecha_agenda=getdate() ";
		q+="where seguimiento=1 ";
		this.clearBatch();
		this.addBatch(q);
		this.executeBatch();
	}
	public Object[][] getPedido(String idpedido){
		String q="";
		
		q+="select pedido.descripcion,";
		q+="convert(varchar(10),isnull(pedido.fecha_agenda,pedido.fecha_creacion),105),";
		q+="pedido.fecha_creacion,";
		q+="pedido.ultima_modificacion,pedido.cliente,pedido.cliente_descripcion,";
		q+="pedido.datos_extra,pedido.idvendedor,vendedores.nombre, ";
		q+="pedido.idtransporte,isnull(transporte.nombre,''),";
		q+="convert(varchar(10),pedido.fecha_envio,105),";
		q+="pedido.guia, ";
		q+="pedido.total ";
		q+="from b_pdc pedido left outer join v_ta_vendedores vendedores ";
		q+="on ltrim(pedido.idvendedor)=ltrim(vendedores.idvendedor) ";
		q+=" left outer join transportes transporte ";
		q+="on ltrim(pedido.idtransporte)=ltrim(transporte.idtransporte) ";
		q+="where pedido.idpedido like '"+idpedido+"' ";
		//System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
	}
	
	public Object[][] getPedidoItems(String idpedido){
		String q="";
		q+="";
		q+="select isnull(items.seleccionado,0),items.idarticulo,items.descripcion,items.cantidad,items.cotiza,items.total,isnull(articulo.descripabrev,'') ";
		q+="from b_pdc_item items ";
		q+="left outer join v_ma_articulos articulo ";
		q+="on items.idarticulo=articulo.idarticulo ";
		q+="where items.idpedido like '"+idpedido+"' ";
		q+="order by items.item ";
		System.out.println(q);
		Object[][] results=getResults(q);
		return results;
	}
	
	public Object[][] getRemitos(String idpedido){
		String q="";
		q+="select remitos.remito,isnull(rmx.anulada,0) ";
		q+="from b_pdc pedido ";
		q+="left outer join b_pdc_rms remitos ";
		q+="on pedido.idpedido=remitos.idpedido ";
		q+="left outer join v_mv_cpte rmx ";
		q+="on remitos.remito=rmx.idcomprobante and rmx.tc like 'RM' ";
		q+="where pedido.idpedido like '"+idpedido+"' ";
		Object[][] results=this.getResults(q);
		return results;
	}
	
	public String getComprobantesAlfaConRemito(String idremito){
		String q="";
		q+="select tc,idcomprobante from v_mv_cpte ";
		q+="where  ";
		q+="tcorigen like 'RM' and comprobanteorigen like '"+idremito+"' ";
		return q;
	}
	
	public String getComprobantesBetaConRemito(String idremito){
		String q="";
		q+="select tc,idcomprobante from b_fvn ";
		q+="where tc_origen like 'RM' ";
		q+="and idcomprobante_origen like '"+idremito+"' ";
		q+="and anulada=0 ";
		return q;	
	}

	public Object[][] tieneComprobanteBetaAsociado(String idremito){
		String q=this.getComprobantesBetaConRemito(idremito);
		
		Object[][] results=this.getResults(q);
		
		return results;
	}
	
	public Object[][] tieneComprobanteAlfaAsociado(String idremito){
		String q=this.getComprobantesAlfaConRemito(idremito);
		
		Object[][] results=this.getResults(q);
		
		return results;
	}
}
