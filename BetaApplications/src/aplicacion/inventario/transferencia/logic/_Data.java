package aplicacion.inventario.transferencia.logic;

import aplicacion.herramientas.conexion.creator.interfaces._Interface;
import aplicacion.modelo.logic.Data;

public class _Data extends Data {
	private String tc="TRSK";
	
	public Object[][] getVendedor(String iduser){
		String q="";
		q+="select b.idvendedor,v.nombre ";
		q+="from b_users b left outer join v_ta_vendedores v on ltrim(b.idvendedor)=ltrim(v.idvendedor) ";
		q+="where b.iduser like '"+iduser+"'";
		return this.getResults(q);
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
	
	/*
	 * descripcion|precio2|costo|linea|suspendido|suspendidoc|cantidadud
	 */
	public double getStockArticulo(String idarticulo,String iddeposito){
		String q="";
		q+="select isnull(sum(s.cantidadud),0) ";
		q+="from v_ma_articulos m left outer join v_mv_stock s on m.idarticulo=s.idarticulo and s.anulado=0 "; 
		q+="where m.idarticulo like '"+idarticulo+"' and s.iddeposito like '"+iddeposito+"' ";
		System.out.println(">"+q);
		double stock=0.0;
		
		Object[][] results=getResults(q);
		if (results!=null){
			if (results.length>0){
				String _stock="";
				try {
					_stock=(String) results[0][0];
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					stock=new Double(_stock);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
			}
		}
		
		return stock;
	}
	
	/*
	 * descripcion|precio2|costo|linea|suspendido|suspendidoc|cantidadud
	 */
	public Object[][] getArticulo(String idarticulo){
		String q="";
		q+="select m.descripcion,m.precio2,m.costo,m.descripabrev,m.suspendidov,m.suspendidoc,isnull(sum(s.cantidadud),0) ";
		q+="from v_ma_articulos m left outer join v_mv_stock s on m.idarticulo=s.idarticulo and s.anulado=0 "; 
		q+="where m.idarticulo like '"+idarticulo+"' ";
		q+="group by m.descripcion,m.precio2,m.costo,m.descripabrev,m.suspendidov,m.suspendidoc ";
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
	}
	
	/*
	 * descripcion|precio2|costo|linea|suspendido|suspendidoc|cantidadud
	 */
	public Object[][] getArticulo(String idarticulo,String iddeposito){
		String q="";
		q+="select m.descripcion,m.precio2,m.costo,m.descripabrev,m.suspendidov,m.suspendidoc,isnull(sum(s.cantidadud),0) ";
		q+="from v_ma_articulos m left outer join v_mv_stock s on m.idarticulo=s.idarticulo and s.anulado=0 "; 
		q+="where m.idarticulo like '"+idarticulo+"' and s.iddeposito like '"+iddeposito+"' ";
		q+="group by m.descripcion,m.precio2,m.costo,m.descripabrev,m.suspendidov,m.suspendidoc ";
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
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
	 public boolean updateTC(String tc){
			String q="";
			q+="update b_ta_cpte set x_ultimo_nro = x_ultimo_nro + 1 where codigo like '"+tc+"'";
			this.clearBatch();
			this.addBatch(q);
			return this.executeBatch();
			
		}
	
	 	public String getProximoPGCorrecto(String tc){
			String prox="";
			prox=this.getProximoPG_Ceros(tc);
			return prox;
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

	public Object[][] getTransferencia(String idtransferencia){
		Object[][] results=getResults(this.getTransferenciaQuery(idtransferencia));
		return results;
	}
	
	private String getTransferenciaQuery(String idtransferencia) {
		String q = "";
		q+="select idtransferencia,";
		q+="fecha,";
		q+="iduser, ";
		q+="iddeposito_origen, ";
		q+="iddeposito_destino ";
		q+="FROM b_transferencia_stock ";
		q+="where idtransferencia like '"+idtransferencia+"' ";
		q+="order by idtransferencia desc";
		return q;
	}

	
	public String getUpdateQuery(String idtransferencia,String fecha,String iddeposito,String iddeposito_destino,String iduser,String idvendedor){
		String q="";
		q+="update b_transferencia_stock set ultima_modificacion=getdate(),fecha='"+fecha+"',iddeposito_origen='"+iddeposito+"',iddeposito_destino='"+iddeposito_destino+"',iduser='"+iduser+"',idvendedor='"+idvendedor+"' ";
		q+="where idtransferencia like '"+idtransferencia+"' ";
		return q;
	}
	
	public String getInsertQuery(String idtransferencia,String fecha,String iddeposito,String iddeposito_destino,String iduser,String idvendedor){
		String q="";
		q+="insert into b_transferencia_stock (idtransferencia,fecha,ultima_modificacion,iddeposito_origen,iddeposito_destino,iduser,ip,host,idvendedor) ";
		q+="values  ('"+idtransferencia+"','"+fecha+"',getdate(),'"+iddeposito+"','"+iddeposito_destino+"','"+iduser+"','"+this.getIp()+"','"+this.getHost()+"','"+idvendedor+"') ";
		return q;
	}
	
	public String getDeleteStock(String idtransferencia){
		String q="";
		q+="delete from v_mv_stock  ";
		q+="where tc like '"+tc+"' and idcomprobante like '"+idtransferencia+"' ";
		return q;
	}
	public String getDeleteTransferencia(String idtransferencia){
		String q="";
		q+="delete from b_transferencia_stock where idtransferencia like '"+idtransferencia+"'";
		return q;
	}
	
	public String getDeleteTransferenciaItems(String idtransferencia){
		String q="";
		q+="delete from b_transferencia_stock_item where idtransferencia like '"+idtransferencia+"'";
		return q;
	}
	
	public boolean exist(String idtransferencia){
		boolean exist=false;
		Object[][] results = getResults(this.getTransferenciaQuery(idtransferencia));
		if (results != null) {
			if (results.length > 0) {
			exist=true;
			}
		}
		return exist;
	}
	
		
	
	public String getQueryTransferenciaItems(String idtransferencia){
		String q="";
		q+="select items.utiliza,items.idarticulo,a.descripcion, ";
		q+="items.stock, ";
		q+="items.cantidad,a.descripabrev ";
		q+="FROM b_transferencia_stock tr ";
		q+="left outer join  ";
		q+=" b_transferencia_stock_item items ";
		q+=" on tr.idtransferencia=items.idtransferencia ";
		q+=" left outer join v_ma_articulos a on items.idarticulo=a.idarticulo ";
		q+="where tr.idtransferencia like '"+idtransferencia+"'  ";
		q+="order by items.IDARTICULO  ";
		System.out.println(""+q);
		return q;
	}
	
	public boolean aplicado(String idtransferencia){
		boolean aplicado=false;
		String q="select idcomprobante from v_mv_stock where tc like '"+tc+"' and idcomprobante like '"+idtransferencia+"' and anulado=0 ";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				aplicado=true;	
			}
		}
		return aplicado;
		
	}
	public String updateTransferenciaItem(String idtransferencia,int secuencia,String idarticulo,double cantidad,double stock,boolean seleccionado){
		String selected="0";
		if (seleccionado)selected="1";
		String q="";
		q+="update b_transferencia_stock_item set cantidad="+cantidad+",stock="+stock+",utiliza="+selected+",secuencia="+secuencia+" ";
		q+="where idtransferencia like '"+idtransferencia+"' and idarticulo like '"+idarticulo+"'";
		q+="";
		return q;
	}
	
	public String deleteTransferenciaItem(String idtransferencia,String idarticulo){
		String q="";
		q+="delete from  b_transferencia_stock_item ";
		q+="where idtransferencia like '"+idtransferencia+"' and idarticulo like '"+idarticulo+"'";
		q+="";
		return q;
	}
	public String insertTransferenciaItem(String idtransferencia,int secuencia,String idarticulo,double cantidad,double stock,boolean seleccionado){
		String selected="0";
		if (seleccionado)selected="1";
		String q="";
		q+="insert into b_transferencia_stock_item (idtransferencia,secuencia,idarticulo,cantidad,stock,utiliza) ";
		q+="values ('"+idtransferencia+"',"+secuencia+",'"+idarticulo+"',"+cantidad+","+stock+","+selected+") ";
		q+="";
		return q;
	}
	
	public Object[][] getTransferenciaItems(String idtransferencia){
		return this.getResults(this.getQueryTransferenciaItems(idtransferencia));
	}
	
	public boolean existeItem(String idtransferencia,String idarticulo){
		boolean exist=false;
		String q="";
		q+="select idarticulo from b_transferencia_stock_item ";
		q+="where idtransferencia like '"+idtransferencia+"' and idarticulo like '"+idarticulo+"' ";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				exist=true;	
			}
		}
		return exist;
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

	public Object[][] getStock(String idproveedor,String linea,String idarticulo_desde,String idarticulo_hasta,String iddeposito,String iddeposito_destino,boolean restriction,String stock,String categorias){
		return this.getQueryResult(this.getStockQuery(idproveedor, linea, idarticulo_desde, idarticulo_hasta,iddeposito,iddeposito_destino,restriction,stock,categorias));
	}
	
	public String getStockQuery(String idproveedor,String linea,String idarticulo_desde,String idarticulo_hasta,String iddeposito,String iddeposito_destino,boolean restriction,String stock,String categorias){
		String q="";
		q+="select ";
		q+="a.idarticulo,a.descripcion,";
		q+="sum(case when s.iddeposito like '"+iddeposito+"' then s.cantidadud else 0 end) as 'origen',";
		q+="sum(case when s.iddeposito like '"+iddeposito_destino+"' then s.cantidadud else 0 end) as 'destino',";
		q+="isnull(e.categoria_rotacion,'D'),isnull(e.rotacion,0),";
		q+="a.descripabrev ";
		q+="from v_ma_articulos a left outer join b_articulos_abc_evolution e on a.idarticulo=e.idarticulo ";
		q+="left outer join v_mv_stock s on a.idarticulo=s.idarticulo and s.anulado=0  ";
		
		String desde="001-000";
		String hasta="999-zzzz";
		if (idarticulo_desde.compareTo("")!=0){
			desde=idarticulo_desde;
		}
		if (idarticulo_hasta.compareTo("")!=0){
			hasta=idarticulo_hasta;
		}
		
		q+=" where a.idarticulo between '"+desde+"' and  '"+hasta+"' ";
		if (idproveedor.compareTo("")!=0){
			q+=" and a.cuentaproveedor like '"+idproveedor +"'";
		}
		if (linea.compareTo("")!=0){
			q+=" and a.descripabrev like '"+linea +"'";
		}
		if (categorias.compareTo("")!=0){
			
			q+=" and ("+categorias+") ";
		}
		q+="group by a.idarticulo,a.descripcion,a.descripabrev,isnull(e.categoria_rotacion,'D'),isnull(e.rotacion,0) ";
		q+="having sum(case when s.iddeposito like '"+iddeposito+"' then s.cantidadud else 0 end)>0 ";
		if (restriction){
			q+="and ";	
			q+="sum(case when s.iddeposito like '"+iddeposito_destino+"' then s.cantidadud else 0 end)<= "+stock+" ";
		}
		q+=" order by a.idarticulo,a.descripcion,a.descripabrev ";
		System.out.println(q);
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
}
