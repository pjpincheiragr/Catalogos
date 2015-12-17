package aplicacion.ventas.gestion2.logic;
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
	
	public String getOwner(String idpedido){
		String q="select idvendedor from b_pdc where idpedido like '"+idpedido+"' ";
		String idvendedor="";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				idvendedor=(String) results[0][0];
			}
		}
		return idvendedor; 
	}
	public String getPedidosColor(String desde,String hasta){
		
		String q="";
		q+="";
		q+="select day(case when pedido.fecha_agenda is null then pedido.fecha_creacion else pedido.fecha_agenda end)";
		q+=",count(*) ";
		q+="from b_pdc pedido ";
		q+="left outer join b_pdc_categorias categorias  ";
		q+="on pedido.idpedido=categorias.idpedido and pedido.idvendedor=categorias.iduser ";
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
	 
public String getPedidosTransferenciaColor(String desde,String hasta){
		
		String q="";
		q+="";
		q+="select day(case when pedido.fecha_agenda is null then pedido.fecha_creacion else pedido.fecha_agenda end)";
		q+=",count(*) ";
		q+="from b_pdc_transferencia transferencia ";
		q+="left outer join  b_pdc pedido on transferencia.idpedido=pedido.idpedido ";
		q+="left outer join b_pdc_categorias categorias  ";
		q+="on pedido.idpedido=categorias.idpedido and pedido.idvendedor=categorias.iduser ";
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

	/**
	 * devuelve idpedido,descripcion,fecha_Agenda,cliente,descripcion,total,creacion,seguimiento,clasificacion
	 * @param desde
	 * @param hasta
	 * @param top
	 * @param calendario
	 * @return
	 */
	public String getPedidosQuery(String desde,String hasta,
			int top,boolean calendario){
		String q="";
		String _top="";
		if (top>0){
			_top=" top "+top;
		}
		//q+="convert(varchar(10),isnull(pedido.fecha_agenda,pedido.ultima_modificacion),105) as agenda, ";
		//q+="isnull(pedido.total,0) as importe,";
		//q+="convert(varchar(10),pedido.fecha_creacion,105),";
		q+="select "+_top+" pedido.idpedido,";
		q+="pedido.descripcion, ";
		q+="pedido.cliente, pedido.cliente_descripcion,";
		q+="isnull(categorias.nivel,0) as nivel,";
		q+="isnull(vendedor.nombre,''), ";
		q+="pedido.seguimiento, ";
		q+="isnull(ucategoria.clasificacion,''),";
		q+="convert(varchar(10),isnull(pedido.fecha_agenda,pedido.ultima_modificacion),105) as agenda, ";
		q+="isnull(categorias.idcategoria,'') ";
		q+="from b_pdc pedido ";
		q+="left outer join b_pdc_transferencia transferencia on pedido.idpedido=transferencia.idpedido and pedido.idvendedor=transferencia.iduser_destino ";
		q+="left outer join b_pdc_categorias categorias ";
		q+="on pedido.idpedido=categorias.idpedido and pedido.idvendedor=categorias.iduser ";
		q+="left outer join b_users_categorias ucategoria on categorias.idcategoria= ucategoria.id and categorias.iduser=ucategoria.iduser ";
		q+="left outer join b_pdc_rms remitos ";
		q+="on pedido.idpedido=remitos.idpedido and remitos.eliminado=0 ";
		q+="left outer join ma_cuentas cuenta ";
		q+="on pedido.cliente=cuenta.codigo ";
		q+="left outer join v_ta_vendedores vendedor ";
		q+="on isnull(transferencia.iduser_origen,pedido.idvendedor)=vendedor.idvendedor ";
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
	
	/**
	 * devuelve idpedido,descripcion,fecha_Agenda,cliente,descripcion,total,creacion,seguimiento,clasificacion
	 * @param desde
	 * @param hasta
	 * @param top
	 * @param calendario
	 * @return
	 */
	public String getPedidosTransferidosQuery(String desde,String hasta,
			int top,boolean calendario){
		String q="";
		String _top="";
		if (top>0){
			_top=" top "+top;
		}
		q+="select "+_top+" pedido.idpedido,";
		q+="pedido.descripcion, ";
		q+="pedido.cliente, pedido.cliente_descripcion,";
		q+="isnull(categorias.nivel,0) as nivel,";
		q+="isnull(vendedor.nombre,''), ";
		q+="pedido.seguimiento, ";
		q+="isnull(ucategoria.clasificacion,''),";
		q+="convert(varchar(10),transferencia.fecha,105) as agenda, ";
		q+="isnull(categorias.idcategoria,'') ";
		q+="from b_pdc_transferencia transferencia ";
		q+="left outer join  b_pdc pedido on transferencia.idpedido=pedido.idpedido ";
		q+="left outer join b_pdc_categorias categorias ";
		q+="on pedido.idpedido=categorias.idpedido and transferencia.iduser_origen=categorias.iduser ";
		q+="left outer join b_users_categorias ucategoria on categorias.idcategoria= ucategoria.id and categorias.iduser=ucategoria.iduser ";
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
	/**
	 * devuelve idpedido,descripcion,fecha_Agenda,cliente,descripcion,total,creacion,seguimiento,clasificacion
	 * @param desde
	 * @param hasta
	 * @param top
	 * @param calendario
	 * @return
	 */
	public String getPedidosTransferidosQueryOLD(String desde,String hasta,
			int top,boolean calendario){
		String q="";
		String _top="";
		if (top>0){
			_top=" top "+top;
		}
		q+="select "+_top+" pedido.idpedido,pedido.descripcion, ";
		q+="convert(varchar(10),isnull(pedido.fecha_agenda,pedido.ultima_modificacion),105) as agenda, ";
		q+="pedido.cliente, pedido.cliente_descripcion,isnull(pedido.total,0) as importe,";
		//q+="isnull(vendedor.nombre,'') as vendedor,";
		q+="isnull(creador.nombre,'') as creador, ";
		q+="convert(varchar(10),pedido.fecha_creacion,105),pedido.seguimiento, ";
		q+="isnull(ucategoria.clasificacion,'') ";
		q+="from b_pdc_transferencia transferencia ";
		q+="left outer join  b_pdc pedido on transferencia.idpedido=pedido.idpedido ";
		q+="left outer join b_pdc_categorias categorias ";
		q+="on pedido.idpedido=categorias.idpedido and pedido.idvendedor=categorias.iduser ";
		q+="left outer join b_users_categorias ucategoria on categorias.idcategoria= ucategoria.id and categorias.iduser=ucategoria.iduser ";
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
	public String getUpdateLevel(String idpedido,String iduser,String level){
		String q="";
		q+="update b_pdc_categorias set nivel="+level+" where idpedido like '"+idpedido+"' and iduser like '"+iduser+"' ";
		return q;
	}
	public String getInsert(String idtransferencia,String idpedido,String iduser_origen,String iduser_destino,String nota,String objetivo){
		String q="insert into b_pdc_transferencia(idtransferencia,fecha,idpedido,iduser_origen,iduser_destino,nota,objetivo) ";
		q+=" values (";
		q+="'"+idtransferencia+"',";
		q+="getdate(),";
		q+="'"+idpedido+"',";
		q+="'"+iduser_origen+"',";
		q+="'"+iduser_destino+"',";
		q+="'"+nota+"',";
		q+="'"+objetivo+"'";
		q+=")";
		return q;
	}
	
	public String getUpdate(String idpedido,String iduser_destino){
		String q="update b_pdc set idvendedor ='"+iduser_destino+"' where idpedido like '"+idpedido+"' ";
		return q;
	}
	
	
	public String getUpdateTC(){
		String tc="TRPC";
		String q="";
		q+="update b_ta_cpte set x_ultimo_nro = x_ultimo_nro + 1 ";
		q+="where codigo = '"+tc+"' ";
		return q;
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
	
	public Object[][] getVendedor(String iduser){
		String q="";
		q+="select b.idvendedor,v.nombre ";
		q+="from b_users b left outer join v_ta_vendedores v on ltrim(b.idvendedor)=ltrim(v.idvendedor) ";
		q+="where b.iduser like '"+iduser+"'";
		return this.getResults(q);
	}
	

	public String getVendedorNombre(String iduser){
		String nombre="";
		String q="";
		q+="select v.nombre ";
		q+="from  v_ta_vendedores v  ";
		q+="where idvendedor like '"+iduser+"'";
		Object[][]results= this.getResults(q);
		if (results!=null){
    		if (results.length>0){
    			nombre=(String)results[0][0];
    		}
    	}
		return nombre;
	}
	public String getVendedorValidacion(String password){
		String q="select idvendedor from b_users where pass like '"+password+"' ";
		String idvendedor="";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				idvendedor=(String) results[0][0];
			}
		}
		return idvendedor;
	}
	
	
	public Object[][] tieneComprobanteAlfaAsociado(String idremito){
		String q=this.getComprobantesAlfaConRemito(idremito);
		
		Object[][] results=this.getResults(q);
		
		return results;
	}
	
	
	public String getUpdate(String newcode,String oldcode,String iduser,String color){
		String q="update b_users_categorias set clasificacion='"+newcode+"'";
		if (color.compareTo("")!=0){
			q+=",background='"+color+"' ";
		}
		q+=" where id like '"+oldcode+"' and iduser like '"+iduser+"'";
		return q;
	}
	
	public String getUpdatePadre(String newcode,String oldcode,String iduser){
		String q="update b_users_categorias set padre='"+newcode+"' where padre like '"+oldcode+"' and iduser like '"+iduser+"' ";
		return q;
	}
	
	public String getId(String clasificacion,String padre,String iduser){
		String q="select id from b_users_categorias where clasificacion like '"+clasificacion+"' and padre like '"+padre+"' and iduser like '"+iduser+"' ";
		return q;
	}
	
	public String getClasificacionDescripcion(String id){
		String q="select clasificacion from b_users_categorias where id like '"+id+"' ";
		String descripcion="";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				descripcion=(String) results[0][0];
			}
		}
		return descripcion;
	}
	
	public String getClasificacion(String clasificacion,String padre,String iduser){
		String q="select clasificacion,id from b_users_categorias where id like '"+clasificacion+"' and padre like '"+padre+"' and iduser like '"+iduser+"' order by clasificacion";
		return q;
	}
	
	public String getInsert(String id,String clasificacion,String padre,String iduser,String color){
		String q="insert into b_users_categorias (id,clasificacion,padre,iduser,background) values ('"+id+"','"+clasificacion+"','"+padre+"','"+iduser+"','"+color+"')";
		return q;
	}
	
	public String getDelete(String clasificacion,String padre,String iduser){
		String q="delete from  b_users_categorias where  clasificacion like '"+clasificacion+"' and padre like '"+padre+"' and iduser like '"+iduser+"' ";
		q+=" and  sistema = 0";
		return q;
	}
	
	public boolean puedeEliminar(String clasificacion,String padre,String iduser){
		boolean exist=false;
		String q="select * from  b_users_categorias where  clasificacion like '"+clasificacion+"' and padre like '"+padre+"' and iduser like '"+iduser+"' ";
		q+=" and  sistema = 0";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				exist=true;
			}
		}
		return exist;
	}
	
	public boolean exist(String id,String iduser){
		boolean exist=false;
		
		String q="select * from b_users_categorias where id like '"+id+"' and iduser like '"+iduser+"' ";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				exist=true;
			}
		}
		return exist;
	}
	
	public boolean existeCategoria(String idpedido,String iduser){
		boolean existe=false;
		String q="select idcategoria from b_pdc_categorias where idpedido like '"+idpedido+"' and iduser like '"+iduser+"' ";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				existe=true;
			}
		}
		return existe;
	}
	
	public String insertCategoria(String idpedido,String iduser,String idcategoria,String level){
		String q="insert into b_pdc_categorias (idcategoria,iduser,idpedido,nivel) values ('"+idcategoria+"','"+iduser+"','"+idpedido+"',"+level+")";
		
		return q;
	}
	
	public String updateCategorias(String idcategoria,String iduser){
		String q="delete from b_pdc_categorias where idcategoria like '"+idcategoria+"%' and iduser like '"+iduser+"' ";
		return q;
	}
	
	public String updateCategoria(String idpedido,String iduser,String idcategoria){
		String q="update b_pdc_categorias set idcategoria='"+idcategoria+"' where iduser like '"+iduser+"' and idpedido like '"+idpedido+"' ";
		return q;
	}
	
	public String getProximoCorrecto(){
		String prox="";
		prox=this.getProximoCeros();
		return prox;
	}
	
	public String getIdVendedors(String nombre){
		String id="";
		return id;
	}
	public String[] getVendedores(){
		String q="select nombre from v_ta_vendedores order by nombre ";
		Object[][] results=this.getResults(q);
		String[] lista=null;
		if (results!=null){
			if (results.length>0){
				lista=new String[results.length];
				for (int i=0;i<results.length;i++){
					lista[i]=results[i][0].toString();
				}
			}
			
		}
		return lista;
	}
	public boolean existe(String idtransferencia){
		boolean existe=false;
		String q="select * from b_pdc_transferencia where idtransferencia like '"+idtransferencia+"' ";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				existe=true;
			}
		}
		return existe;
	}
	
	private String getProximoCeros(){
		String c="";
		int i=this.getProximo();
		String aux=""+i;
		while (aux.length()<8){
			aux="0"+aux;
		}
		c=aux;
		return c;
	}
	
	
	public int getProximo(){
		int c=0;
		String tc="TRPC";
		String q="select x_ultimo_nro from b_ta_cpte where codigo = '"+tc+"'";
		Object[][] aux=connection_handler.getDefaultConnector().getResults(q);
		try{
			c=new Integer(aux[0][0].toString());
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return c;
	}
	
	//avisos
	private int getProximoTC(String tc){
		int c=0;
		String q="select x_ultimo_nro from b_ta_cpte where codigo = '"+tc+"'";
		Object[][] aux=connection_handler.getDefaultConnector().getResults(q);
		try{
			c=new Integer(aux[0][0].toString());
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return c;
	}
	
	public String getProximoAvisoCorrecto(){
		String prox="";
		prox=""+this.getProximoAviso();
		return prox;
	}
	
	private int getProximoAviso(){
		int c=0;
		c=this.getProximoTC("MSJ");
		return c;
	}
		
	public String getUpdateAvisos(){
		String aux="";
		aux="update b_ta_cpte set x_ultimo_nro=x_ultimo_nro+1";
		aux=aux+" where codigo = 'MSJ'";
		return aux;
	}
	
	public String getInsertAvisoUser(String idaviso,String iduser,String agenda,String pantalla){
		String q="";
		q+="insert into b_agenda_user (idaviso,iduser,agenda,pantalla) values ("+idaviso+",'"+iduser+"','"+agenda+"',"+pantalla+")";
		return q;
	}
		
	public boolean exist(String idx){
		boolean ok=false;
		idx=idx.replace(" ", "");
		String q="select * from b_agenda where idaviso like '"+idx+"'";
		System.out.println(q);
		Object[][] results=connection_handler.getDefaultConnector().getResults(q);
		if (results!=null){
			if (results.length>0){
				ok=true;
			}
		}
		return ok;
	}
	
	public String getEvent(String idaviso,String iduser,String event){
		String host=getHost();
		String q="insert into b_agenda_event (idaviso,iduser,host,event) ";
		q+=" values ("+idaviso+",'"+iduser+"','"+host+"','"+event+"') ";
		return q;
	}
	
	public String getInsert(String idaviso,String titulo,String mensaje,String idcreador,String agenda,String privado,String pantalla){
		String q="";
		q+="insert into b_agenda (idaviso,titulo,mensaje,idcreador,agenda,privado,pantalla)";
		q+="values ("+idaviso+",'"+titulo+"','"+mensaje+"','"+idcreador+"','"+agenda+"',"+privado+","+pantalla+") ";
		q+="";
		return q;
	}
}
