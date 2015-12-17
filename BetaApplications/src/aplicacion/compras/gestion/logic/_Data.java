package aplicacion.compras.gestion.logic;
import aplicacion.modelo.logic.Data;

public class _Data extends Data{
	
	public void update_seguimiento(String idpedido,boolean seguimiento,String estado){
		String q="";
		String seg="0";
		if (seguimiento) seg="1";
		q+="update b_pep set seguimiento="+seg+",estado='"+estado+"' where idpedido like '"+idpedido+"' ";
		this.clearBatch();
		System.out.println(q);
		this.addBatch(q);
		this.executeBatch();
	}
	public void update_seguimiento_pdp(String idpedido,boolean seguimiento,String estado){
		String q="";
		String seg="0";
		if (seguimiento) seg="1";
		q+="update b_pdp set seguimiento="+seg+",estado='"+estado+"' where idpedido like '"+idpedido+"' ";
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
		q+="from b_pep pedido ";
		q+="left outer join ma_cuentas cuenta ";
		q+="on pedido.cliente=cuenta.codigo ";
		q+="left outer join v_ta_vendedores vendedor ";
		q+="on pedido.idvendedor=vendedor.idvendedor ";
		q+="left outer join b_pep_item items ";
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
		q+="select 'PEP' as TC,pedido1.idpedido,convert(varchar(10),pedido1.fecha_agenda,105) as agenda,";
		q+="pedido1.descripcion,isnull(tt.nombre,''),c.descripcion,pedido1.estado,	convert(varchar(10),pedido1.fecha_creacion,105),pedido1.seguimiento ";
		q+="from b_pep pedido1 ";
		q+="left outer join ma_cuentas c on pedido1.cliente=c.codigo "; 
		q+="left outer join transportes tt on pedido1.idtransporte=tt.idtransporte ";
		q+="where pedido1.eliminar=0 ";
		if (calendario){
			q+=" and convert(varchar(10),isnull(pedido1.fecha_agenda,pedido1.fecha_creacion),105) like '"+desde+"' ";	
		}
		q+="union ";
		q+="select 'PDP' as TC,pedido2.idpedido,convert(varchar(10),pedido2.fecha_agenda,105) as agenda,";
		q+="pedido2.descripcion,isnull(tt.nombre,''),c.descripcion,pedido2.estado,convert(varchar(10),pedido2.fecha,105),pedido2.seguimiento ";
		q+="from b_pdp pedido2 ";
		q+="left outer join ma_cuentas c on pedido2.cuenta=c.codigo ";
		q+="left outer join transportes tt on pedido2.idtransporte=tt.idtransporte ";
		q+="where pedido2.eliminar=0 ";
		if (calendario){
			q+=" and convert(varchar(10),isnull(pedido2.fecha_agenda,pedido2.fecha_creacion),105) like '"+desde+"' ";	
		}
		//q+="isnull(fvn.anulada,0)=0 and ";
		//q+="convert(varchar(10),isnull(pedido.fecha_agenda,pedido.ultima_modificacion),105) like '"+desde+"' ";
		//q+="isnull(pedido.fecha_agenda,pedido.fecha_creacion) between '"+desde+"' and '"+hasta+"' and isnull(pedido.eliminar,0)=0 ";
		
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
		this.clearBatch();
		String q="update b_pep set fecha_agenda=getdate() ";
		q+="where seguimiento=1 ";
		this.addBatch(q);
		q="update b_pdp set fecha_agenda=getdate() ";
		q+="where seguimiento=1 ";
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
		q+="from b_pep pedido left outer join v_ta_vendedores vendedores ";
		q+="on ltrim(pedido.idvendedor)=ltrim(vendedores.idvendedor) ";
		q+=" left outer join transportes transporte ";
		q+="on ltrim(pedido.idtransporte)=ltrim(transporte.idtransporte) ";
		q+="where pedido.idpedido like '"+idpedido+"' ";
		//System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
	}
	
	public String getPedidoEncabezado(String idpedido){
		String q = "select p.cuenta,convert(varchar(10),p.fecha,105),p.descripcion,c.descripcion,p.estado,p.seguimiento,convert(varchar(10),p.fecha_agenda,105) ";
		q+="from b_pdp p left outer join ma_cuentas c on p.cuenta=c.codigo ";
		q+="where p.idpedido like '"+idpedido+"'";
		return q;
	}
	
	public Object[][] getPedidoPDP(String idpedido){
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
		q+="from b_pdp pedido left outer join v_ta_vendedores vendedores ";
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
		q+="from b_pep_item items ";
		q+="left outer join v_ma_articulos articulo ";
		q+="on items.idarticulo=articulo.idarticulo ";
		q+="where items.idpedido like '"+idpedido+"' ";
		q+="order by items.item ";
		System.out.println(q);
		Object[][] results=getResults(q);
		return results;
	}
	
	public Object[][] getPedidoItemsPDP(String idpedido){
		String q="";
		q+="";
		q+="select isnull(items.usar,0),items.idarticulo,items.descripcion,items.cantidad,items.costo,items.cantidad*items.costo,isnull(articulo.descripabrev,'') ";
		q+="from b_pdp_lineas lineas left outer join v_ma_articulos articulo ";
		q+="on lineas.linea=articulo.descripabrev ";
		q+="left outer join b_pdp_items items ";
		q+="on items.idpedido=lineas.idpedido and items.idarticulo=articulo.idarticulo and items.usar=1 ";
		q+="where lineas.idpedido like '"+idpedido+"' and lineas.seleccionada=1	and items.cantidad>0 ";
		q+="order by items.idarticulo ";
		System.out.println(q);
		Object[][] results=getResults(q);
		return results;
	}
}
