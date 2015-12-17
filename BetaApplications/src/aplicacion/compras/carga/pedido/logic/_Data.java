package aplicacion.compras.carga.pedido.logic;

import aplicacion.modelo.logic.Data;

public class _Data extends Data {
	String tc="PDP";
	public String getProximoPEDIDO_Ceros() {
		String c = "";
		int i = this.getProximoPEDIDO();
		String aux = "" + i;
		while (aux.length() < 8) {
			aux = "0" + aux;
		}
		c = aux;
		return c;
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
	public boolean update_seguimiento(String idpedido,boolean seguimiento,String estado){
		String q="";
		String seg="0";
		if (seguimiento) seg="1";
		q+="update b_pdp set seguimiento="+seg+",estado='"+estado+"' where idpedido like '"+idpedido+"' ";
		this.clearBatch();
		System.out.println(q);
		this.addBatch(q);
		return !this.executeBatch();
	}
	
	private String getProveedorQuery(String idcliente){
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
		q=q+" isnull(a.calle,''),";
		q+="  isnull(ltrim(a.numero),'')";
		q+="  ,isnull(a.piso,''), ltrim(a.cpostal), ";
		q=q+" a.provincia,";
		q+="  st.descripcion,tip.descripcion,a.documento_tipo,isnull(td.descripcion,''), ";
		q+="  isnull(a.idtransporte,''),isnull(tt.nombre,'')";
		q=q+" FROM MA_CUENTAS M LEFT OUTER JOIN ";
		q=q+" MA_CUENTASADIC  a  ";
		q=q+" ON M.CODIGO = a.CODIGO left outer join v_ta_cpra_vta t ";
		q=q+" on a.idcond_cpra_vta = t.idcond_cpra_vta ";
		q=q+" left outer join ta_condiva tip ";
		q=q+" on a.iva=tip.codigo ";
		q=q+" left outer join ta_estados st ";
		q=q+" on a.provincia=st.codigo ";
		q=q+" left outer join ta_tipodocumento td on a.documento_tipo=td.codigo ";
		q=q+" left outer join transportes tt on a.idtransporte=tt.idtransporte ";
		q=q+" where M.codigo like '"+idcliente+"'";
		return q;
	}
	
	public Object[][] getProveedor(String idcliente){
		String q=this.getProveedorQuery(idcliente);
		System.out.println(q);
		Object[][] results=this.getResults(q);
		return results;
	}
	
	public Object[][] getData(String idarticulo){
		String q="";
		q+="select m.idarticulo,m.descripcion,r.descripcion,isnull(sum(s.cantidadud),0),isnull(m.suspendidov,0),convert(varchar(10),m.actualizacion,105),m.cuentaproveedor,c.descripcion,m.precio2,m.costo ";
		q+="from v_ma_articulos m left outer join v_ta_rubros r on m.idrubro=r.idrubro ";
		q+="left outer join v_mv_stock s on m.idarticulo=s.idarticulo and s.anulado =0 "; 
		q+="left outer join ma_cuentas c on m.cuentaproveedor=c.codigo ";
		q+="where m.idarticulo like '"+idarticulo+"' ";
		q+="group by m.idarticulo,m.descripcion,r.descripcion,isnull(m.suspendidov,0),convert(varchar(10),m.actualizacion,105),m.cuentaproveedor,c.descripcion,m.precio2,m.costo ";
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
	}
	
	public double getPedidoCantidad(String idarticulo){
		double cant=0;
		String q="";
		q+="SELECT I.Cantidad ";
		q+="FROM B_PEP P LEFT OUTER JOIN B_PEP_ITEM I ON P.IDPEDIDO=I.IDPEDIDO  WHERE I.IDARTICULO LIKE '"+idarticulo+"' ";
		q+="AND P.ELIMINAR =0  and P.estado like 'Enviado' ";
		q+="union ";
		q+="SELECT I.Cantidad ";
		q+="FROM B_PDP P LEFT OUTER JOIN B_PDP_ITEMs I ON P.IDPEDIDO=I.IDPEDIDO  WHERE I.IDARTICULO LIKE '"+idarticulo+"' ";
		q+="AND P.ELIMINAR =0  and P.estado like 'Enviado' ";
		Object[][] results=this.getResults(q);
		
		if (results!=null){
			if (results.length>0){
				double qi=0;
				for (int i=0;i<results.length;i++){
					String qty=(String) results[i][0];
					try {
						qi=new Double(qty);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					cant+=qi;
				}
				
				
			}
		}
		return cant;
	}
	public Object[][] getEquivalencias(String idarticulo){
		String q="";
		q+="select m.idarticulo,m.descripcion,r.descripcion,m.costo,sum(isnull(s.cantidadud,0)) ";
		q+="from b_articulos_equivalencias e left outer join v_ma_articulos m ";
		q+=" on (e.idarticulo1=m.idarticulo or e.idarticulo2=m.idarticulo) left outer join v_ta_rubros r on m.idrubro=r.idrubro ";
		q+=" left outer join v_mv_stock s on m.idarticulo=s.idarticulo and s.anulado=0 ";
		q+=" where (e.idarticulo1 like '"+idarticulo+"' or e.idarticulo2 like '"+idarticulo+"')";
		q+=" and m.idarticulo not like '"+idarticulo+"' ";
		q+=" group by m.idarticulo,m.descripcion,r.descripcion,m.costo ";
		return this.getResults(q);
	}
	
	public String getDeleteArticulo(String idpedido,String linea,String idarticulo) {
		
		String q = "delete i from b_pdp_items i left outer join v_ma_articulos v ";
		q += "on i.idarticulo = v.idarticulo ";
		q += " where i.idpedido like '" + idpedido + "' ";
		q += " and i.linea like '" + linea + "' ";
		q += " and i.idarticulo like '" + idarticulo + "' ";
		return q;
	}
	public String getDeleteLinea(String idpedido,String linea) {
		String q = "delete i from b_pdp_items i left outer join v_ma_articulos v ";
		q += "on i.idarticulo = v.idarticulo ";
		q += " where i.idpedido like '" + idpedido + "' ";
		q += " and v.idrubro like '" + linea + "' ";
		return q;
	}

	public String getInsertItem(String idpedido,String idarticulo, String descripcion,
			double qty, double costo, boolean usar,String linea) {
		
		int seleccion = 0;
		if (usar)
			seleccion = 1;
		String q = "";
		q = q + "insert into b_pdp_items ";
		q = q + "(idpedido,idarticulo,descripcion,cantidad,costo,usar,linea)";
		q = q + " values (";
		q = q + "'" + idpedido + "',";
		q = q + "'" + idarticulo + "',";
		q = q + "'" + descripcion + "',";
		q = q + "" + qty + ",";
		q = q + "" + costo + "," + seleccion + ",'"+linea+"')";
		return q;
	}



	public String getPedQuery(String cli) {
		String q = "";
		q = q + "select p.idpedido,p.cuenta,m.descripcion,p.desde,p.hasta,p.fecha ";
		q = q + "FROM b_pdp p left outer join ma_cuentas m ";
		q = q + "on p.cuenta=m.codigo ";
		q = q + "where p.idpedido like '" + cli + "'";
		return q;
	}
	public int getProximoPEDIDO() {
		int c = 0;
		String q = "select x_ultimo_nro from b_ta_cpte where codigo = 'PDP'";
		Object[][] aux = getResults(q);
		try {
			c = new Integer(aux[0][0].toString());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return c;
	}
	public void genera_nuevo_pedido() {
		int next = getProximoPEDIDO() + 1;
		String q = "update b_ta_cpte set x_ultimo_nro=" + next
				+ " where codigo = 'PDP'";
		clearBatch();
		addBatch(q);
		boolean b = executeBatch();
	}
	
	public String getProvQuery(String code) {
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
	
	public String getLineaItemsCreation(String idpedido,String idproveedor,String linea,String desde,String hasta,String categoria) {
		// String linea=frame.get_txt_linea().getText();
		
		String q = "";
		q = q + " select m.idarticulo,m.descripcion, ";
		q = q + " CAST(m.costo AS decimal(10,2)),m.puntopedido, ";
		q = q + " ISNULL(s.cantidadud, 0) AS stk, ";
		q = q + " sum(case when (v.tc like 'FC' or v.tc like 'TK' or v.tc like 'TKFC' or v.tc like 'FP' or v.tc like 'FVN' ) then -v.cantidadud else 0 end)as Ventas, ";
		q = q + " isnull(i.cantidad,0),";
		q = q + " e.rotacion,";
		q = q + " e.pickups";
		q = q + " from v_ma_articulos m left outer join Stk_Stock_UDKG s ON ";
		q = q + " m.IDARTICULO = s.IdArticulo ";
		q = q + " left outer join b_articulos_abc_evolution e ";
		q = q + " on m.idarticulo=e.idarticulo ";
		q = q + " left outer join v_mv_stock v ON ";
		q = q + " m.IDARTICULO = v.IdArticulo and v.anulado=0 ";
		q = q + " and v.fecha between '" + desde + "' and '"+hasta+"' ";
		q = q + " left outer join b_pdp_items i ON ";
		q = q + " m.IDARTICULO = i.IdArticulo  ";
		q = q + " and i.idpedido like '"+ idpedido + "' ";
		q = q + " where m.idrubro like '" + linea + "'  ";
		if (categoria.compareTo("")!=0){
			q += " and e.categoria_rotacion between 'A' and '"+categoria+"' ";	
		}
		q = q + " group by m.idarticulo,m.descripcion,m.costo,s.cantidadud,m.puntopedido,i.cantidad,e.rotacion,e.pickups ";
		return q;
	}
	
	public String getLineaItems(String idpedido,String idproveedor,String linea,String desde,String hasta,String categoria,String stock,int tipo,String descripcion) {
		// String linea=frame.get_txt_linea().getText();
		String info="";
		if (tipo<=0){
			info="isnull(e.categoria_rotacion,'D'),isnull(e.rotacion,0)";
		}
		if (tipo==1){
			info="isnull(e.categoria_volumen,'D'),isnull(e.volumen,0)";
		}
		if (tipo==2){
			info="isnull(e.categoria_pickups,'D'),isnull(e.pickups,0)";
		}
		String q = "";
		q+=" select i.usar,m.idarticulo,m.descripcion,"+info+",  CAST(m.costo AS decimal(10,2)),m.puntopedido, ";
		q+=" m.puntomaximo,  sum(ISNULL(s.cantidadud, 0)) AS stk, ";
		q+="  sum(case when s.fecha between '" + desde + "' and '"+hasta+"'  and (s.tc like 'FC' or s.tc like 'TK' or s.tc like 'TKFC' or s.tc like 'FP' or s.tc like 'FVN' ) then -s.cantidadud else 0 end)as Ventas, ";
		q+=" isnull(i.cantidad,0),  ";
		q+=" isnull(datediff(day,max(case when (s.tc like 'TKFC' or s.tc like 'TK' or s.tc like 'FVN' or s.tc like 'FC'  or s.tc like 'FP' ) then s.fecha  else null end),getdate()),9999) as 'inmovilizado', ";
		q+=" isnull(datediff(day,max(case when (s.tc like 'FCN' or s.tc like 'FCC'  or s.tc like 'FPC' ) then s.fecha  else null end) ,getdate()),9999) as 'dias U.COMPRA' ";
		q+=" from v_ma_articulos m ";
		q+=" left outer join b_articulos_abc_evolution e ";
		q+=" on m.idarticulo=e.idarticulo ";
		q+=" left outer join v_mv_stock s ";
		q+=" ON  m.IDARTICULO = s.IdArticulo and isnull(s.anulado,0)=0 ";
		q+=" left outer join b_pdp_items i ON ";
		q+=" m.IDARTICULO = i.IdArticulo  and  i.idpedido like '"+ idpedido + "' ";
		
		if (linea.compareTo("ARTICULOS VARIOS")==0){
			q += " where i.linea likE '" + linea + "' ";	
		}else {
			q += " where m.idrubro like '" + linea + "' ";
		}
		if (descripcion.compareTo("")!=0){
			q += " and i.descripcion like '%"+descripcion+"%' ";
		}
		if (categoria.compareTo("")!=0){
			if (tipo<=0){
				q += " and e.categoria_rotacion between 'A' and '"+categoria+"' ";	
			}
			if (tipo==1){
				q += " and e.categoria_volumen between 'A' and '"+categoria+"' ";	
			}
			if (tipo==2){
				q += " and e.categoria_pickups between 'A' and '"+categoria+"' ";	
			}
		}
		
		q += " and m.suspendido=0 ";
		q+=" group by m.idarticulo,m.descripcion,m.costo,"+info+",m.puntomaximo,m.puntopedido,isnull(i.cantidad,0),i.usar ";
		if (stock.compareTo("")!=0){
			q += " having sum(ISNULL(s.cantidadud, 0)) <= "+stock+" ";
		}
		q+=" order by m.idarticulo ";
		
		
			return q;
	}
	
	public String getArticleUpdate(String art, double minimo) {
		String q = "update v_ma_articulos set puntopedido=" + minimo + " ";
		q += " where idarticulo like '" + art + "' ";
		return q;
	}
	
	public String getArticleUpdateCritico(String art, double minimo) {
		String q = "update v_ma_articulos set puntomaximo=" + minimo + " ";
		q += " where idarticulo like '" + art + "' ";
		return q;
	}
	
	public String getLineaSelectedItems(String idpedido,String idproveedor,String linea) {
		// String linea=frame.get_txt_linea().getText();
		
		String q = "";
		q+=" select m.idarticulo,m.descripcion, ";
		q+=" isnull(i.cantidad,0),i.linea";
		q+=" from v_ma_articulos m  ";
		q+=" left join b_pdp_items i ON ";
		q+=" m.IDARTICULO = i.IdArticulo  ";
		q+=" and i.idpedido like '"+ idpedido + "' ";
		if (linea.compareTo("ARTICULOS VARIOS")==0){
			q+=" where i.linea like '" + linea + "' ";	
		}else {
			q+=" where m.idrubro like '" + linea + "' ";
		}
		
		
		//q+=" and i.usar=1 and isnull(i.cantidad,0)>0 ";
		q+=" and isnull(i.cantidad,0)>0 ";
		
		return q;
	}
	
	public String getLineas(String idproveedor) {
		String q = "";
		q = q + " select r.idrubro, r.descripcion ";
		q = q + " from v_ma_articulos v left outer join v_ta_rubros r on v.idrubro = r.idrubro ";
		q = q + " where v.cuentaproveedor like '"
				+ idproveedor + "' ";
		q = q + " and r.descripcion not like '' ";
		q = q + " group by r.descripcion  ";
		q = q + " order by r.descripcion ";
		q = q + "";
		return q;
	}

	public String getLineasImportarQuery(String idproveedor,String idpedido,String desde,String hasta,String categoria,int tipo){
		String q="";
		q+="select (case when l.seleccionada is not null then 1 else 0 end),r.idrubro,r.descripcion,";
		if (tipo<=0){
			q+="max(e.categoria_rotacion),max(e.rotacion) ";	
		}
		if (tipo==1){
			q+="max(e.categoria_volumen),max(e.volumen) ";	
		}
		if (tipo==2){
			q+="max(e.categoria_picking),max(e.picking) ";	
		}
		
		//q+=",(convert(money,sum(case when v.cuentaproveedor like '"+idproveedor+"' then 1 else 0 end))/convert(money,count(*)))*100,";
		//q+="sum(case when s.tc like 'FVN' or s.tc like 'TKFC' or s.tc like 'TK' or s.tc like 'NC' or s.tc like 'FC' then -s.cantidadud*v.precio2 else 0 end) ";
		q+=" from v_ma_articulos v left outer join v_ta_rubros r on v.idrubro = r.idrubro ";
		q+="left outer join b_pdp_lineas l ";
		q+="on v.idrubro=l.linea and l.idpedido='"+idpedido+"' ";
		q+="left outer join b_lineas_abc_evolution e on v.idrubro=e.linea  ";
		//q+="left outer join v_mv_stock s on v.idarticulo =s.idarticulo and s.anulado=0 and s.fecha between '"+desde+"' and '"+hasta+"' ";
		q+="where  r.descripcion not like ''  ";
		q+="and v.cuentaproveedor like '"+idproveedor+"'  ";
		
		q+="group by r.idrubro,r.descripcion,l.seleccionada,e.linea ";
		//q+="having (convert(money,sum(case when v.cuentaproveedor like '"+idproveedor+"' then 1 else 0 end))/convert(money,count(*)))>0.5 ";
		
		if (categoria.compareTo("")!=0){
			if (tipo<=0){
				q+="having max(e.categoria_rotacion) between 'A' and '"+categoria+"' ";	
			}
			if (tipo==1){
				q+="having max(e.categoria_volumen) between 'A' and '"+categoria+"' ";	
			}
			if (tipo==2){
				q+="having max(e.categoria_picking) between 'A' and '"+categoria+"' ";	
			}
		}
		q+="order by r.descripcion ";
		System.out.println(q);
		return q;
	}
	
	
	public String getUpdate(String idpedido,String linea,boolean seleccionada,double items,double valor){
		String q="";
		String seleccion="0";
		if (seleccionada) seleccion="1";
		q+="update b_pdp_lineas set seleccionada="+seleccion+",";
		q+="items="+items+",";
		q+="valor="+valor+" ";
		q+="where idpedido like '"+idpedido+"' ";
		q+="and linea like '"+linea+"' ";
		q+="";
		return q;
	}
	
	public Object[][] getLineStatics(String linea){
		String q="";
		q+="select r.descripcion,l.categoria_rotacion,l.rotacion,l.categoria_volumen,l.volumen,l.categoria_picking,l.picking";
		q+=" from b_lineas_abc_evolution l left outer join v_ta_rubros r on l.linea = r.idrubro ";
		q+="where r.descripcion like '"+linea+"' ";
		return this.getResults(q);
	}
	
	public Object[][] getLineasImportar(String idproveedor,String idpedido,String desde,String hasta,String categoria,int tipo){
		Object[][] results=null;
		results=this.getResults(this.getLineasImportarQuery(idproveedor, idpedido,desde,hasta,categoria,tipo));
		if (results!=null){
			if (results.length>0){
				for (int i=0;i<results.length;i++){
					results[i][0]=(results[i][0].toString().compareTo("1")==0);
				}
			}
		}
		return results;
	}
	
	public String getLineasAgregadas(String idpedido,int tipo,String categoria) {
		String q = "";
		q = q + " select l.seleccionada,l.linea,r.descripcion,";
		if (tipo<=0){
			q+="isnull(e.categoria_rotacion,'D'),isnull(e.rotacion,0) ";	
		}
		if (tipo==1){
			q+="isnull(e.categoria_volumen,'D'),isnull(e.volumen,0) ";	
		}
		if (tipo==2){
			q+="isnull(e.categoria_picking,'D'),isnull(e.picking,0) ";	
		}
		q+=",isnull(l.items,0),isnull(l.valor,0) ";
		q = q + " from b_pdp_lineas l left outer join v_ta_rubros r on l.linea = r.idrubro ";
		q = q + " left outer join b_lineas_abc_evolution e  on l.linea=e.linea ";
		q = q + " where l.idpedido like '"
				+ idpedido + "' ";
		q = q + " and l.linea not like '' ";
		if (categoria.compareTo("")!=0){
			if (tipo<=0){
				q+="and isnull(e.categoria_rotacion,'D') between 'A' and '"+categoria+"' ";	
			}
			if (tipo==1){
				q+="and isnull(e.categoria_volumen,'D') between 'A' and '"+categoria+"' ";	
			}
			if (tipo==2){
				q+="and isnull(e.categoria_pickups,'D') between 'A' and '"+categoria+"' ";	
			}
		}
		q = q + " order by l.linea ";
		q = q + "";
		System.out.println(q);
		return q;
	}
	
	public boolean update_article(String art, double minimo) {
		System.out
				.println("update minimo article " + art + " minimo=" + minimo);
		clearBatch();
		addBatch(
				getArticleUpdate(art, minimo));
		boolean b = executeBatch();
		return b;
	}
	
	public boolean update_article_critico(String art, double minimo) {
		System.out.println("update minimo article " + art + " minimo=" + minimo);
		clearBatch();
		addBatch(getArticleUpdateCritico(art, minimo));
		boolean b = executeBatch();
		return b;
	}
	
	public String get_linea_descripcion(String idrubro) {
		String q = "select descripcion from v_ta_rubros where idrubro like '" + idrubro + "' ";
		Object[][] results = getResults(q);
		String descripcion = "";
		if (results != null) {
			if (results.length > 0) {
				descripcion = (String) results[0][0];
			}
		}
		return descripcion;
	}
	
	public boolean exist(String idpedido,String art) {
		String q = "select idarticulo from b_pdp_items where idpedido like '"
				+ idpedido
				+ "' and idarticulo like '" + art + "' ";
		Object[][] results = getResults(q);
		boolean exist = false;
		if (results != null) {
			if (results.length > 0) {
				exist = true;
			}
		}
		return exist;
	}
	
	public boolean guardar_pedido(String idpedido,String descripcion,String fecha,String estado,boolean seguimiento){
		String _seguimiento="0";
		if (seguimiento){
			_seguimiento="1";
		}
    	String q="update b_pdp set descripcion='"+descripcion+"',fecha_agenda='"+fecha+"',estado='"+estado+"',seguimiento="+_seguimiento+" where idpedido like '"+idpedido+"'";
    	clearBatch();
    	addBatch(q);
    	return executeBatch();
    }
	
	public String getEliminarPedido(String idpedido){
    	String q="update b_pdp set eliminar=1 where idpedido like '"+idpedido+"'";
    	return q;
    }
	
	public String getArticuloQuery(String idpedido,String codigo,String desde,String hasta){
		String q="";
		q = q + " select m.idarticulo,m.descripcion, ";
		q = q + " CAST(m.costo AS decimal(10,2)),m.puntopedido, ";
		q = q + " ISNULL(s.cantidadud, 0) AS stk, ";
		q = q + " sum(case when (v.tc like 'FC' or v.tc like 'TK' or v.tc like 'TKFC' or v.tc like 'FP' or v.tc like 'FVN' ) then -v.cantidadud else 0 end)as Ventas, ";
		q = q + " isnull(i.cantidad,0)";
		q = q + " from v_ma_articulos m left outer join Stk_Stock_UDKG s ON ";
		q = q + " m.IDARTICULO = s.IdArticulo ";
		q = q + " left outer join v_mv_stock v ON ";
		q = q + " m.IDARTICULO = v.IdArticulo ";
		q = q + " and v.fecha between '" + desde + "' and '"+hasta+"' ";
		q = q + " left outer join b_pdp_items i ON ";
		q = q + " m.IDARTICULO = i.IdArticulo  ";
		q = q + " and i.idpedido like '"+ idpedido + "' ";
		q = q + " where m.idarticulo like '" + codigo + "' ";
		//q = q + " and i.idpedido like '"+ idpedido + "' ";
		//q = q + " and m.cuentaproveedor like '" + idproveedor + "' ";
		
		q = q + " group by m.idarticulo,m.descripcion,m.costo,s.cantidadud,m.puntopedido,i.cantidad ";
		return q;
	}
	
	public boolean existe(String idpedido) {
		String q = "select idpedido from b_pdp where idpedido like '"
				+ idpedido + "'  ";
		Object[][] results = getResults(q);
		boolean exist = false;
		if (results != null) {
			if (results.length > 0) {
				exist = true;
			}
		}
		return exist;
	}

	public String getUpdateItem(String idpedido,String art, double pedir,boolean usar) {
		String q = "";
		String _usar="0";
		if (usar){
			_usar="1";
		}
		q += "update b_pdp_items set cantidad=" + pedir
				+ ",usar="+_usar+" where idpedido like '"
				+ idpedido
				+ "' and idarticulo like '" + art + "'";
		return q;
	}

	public String getInsertar_linea(String idpedido,String linea){
		String q="insert into b_pdp_lineas (idpedido,linea) ";
		q+=" values ('"+idpedido+"','"+linea+"')";
		return q;
	}
	
	public String getQuitar_linea(String idpedido,String linea){
		String q=" delete from b_pdp_lineas ";
		q+=" where idpedido like '"+idpedido+"' and linea like '"+linea+"' ";
		return q;
	}
	
	public String getArticulo(String idarticulo){
		String q="";
		q=q+"select G.idarticulo,G.descripcion,r.descripcion,G.cuentaproveedor,G.politicaprecios,G.precio5,G.costo,G.precio2 ";
		q=q+"from v_ma_articulos G  left ouyter join v_ta_rubros r on G.idrubro=r.idrubro "; 
		q=q+"where  G.idarticulo like '"+idarticulo+"' ";
		return q;
	}
	
	
	public boolean existe_linea(String idpedido,String linea){
		boolean b=false;
		String q="select linea from b_pdp_lineas ";
		q+="where linea like '"+linea+"' and idpedido like '"+idpedido+"'";
		System.out.println("ExisteLinea?>"+q);
		Object[][] results = this.getResults(q);
		if (results!=null){
			if (results.length>0){
				String line=(String) results[0][0];
				b=line.compareTo(linea)==0;
				System.out.println(line+"vs"+linea+"<>"+b);
			}	
		}
		
		return b;
	}
	
	public boolean getSeleccionada(String idpedido,String linea){
		boolean b=false;
		Object[][] results=this.getResults(this.getQuerySeleccionada(idpedido, linea));
		if (results!=null){
			if (results.length>0){
				b=results[0][0].toString().compareTo("1")==0;
			}
		}
		return b;
	}
	public boolean setSeleccionada(String idpedido,String linea,boolean seleccionada){
		String q="";
		q+="update b_pdp_lineas set seleccionada=";
		if (seleccionada){
			q+="1 ";
		}else{
			q+="0 ";
		}
		q+="where idpedido like '"+idpedido+"' ";
		q+="and linea like '"+linea+"' ";
		clearBatch();
		addBatch(q);
		System.out.println("UPDATE LINEA>"+q);
		return executeBatch();
	}
	private String getQuerySeleccionada(String idpedido,String linea){
		String q="select isnull(seleccionada,0) from b_pdp_lineas ";
		q+="where idpedido like '"+idpedido+"' ";
		q+="and linea like '"+linea+"' ";
		return q;
	}
	
	public String getPedidoEncabezado(String idpedido){
		String q = "select p.cuenta,convert(varchar(10),p.fecha,105),p.descripcion,c.descripcion,p.estado,p.seguimiento,convert(varchar(10),p.fecha_agenda,105) ";
		q+="from b_pdp p left outer join ma_cuentas c on p.cuenta=c.codigo ";
		q+="where p.idpedido like '"+idpedido+ "'";
		return q;
	}
	
	public String getNombreEmpresa(){
		String nombre="";
		String q="select valor ";
		q+="from ta_configuracion ";
		q+="where grupo like 'datos' ";
		q+="and clave like 'Nombre' ";
		Object[][] results=this.getResults(q);
		if(results!=null){
			if(results.length>0){
				nombre=(String) results[0][0];
			}
		}
		return nombre;
	}
	
	public String getTelefonoEmpresa(){
		String telefono="";
		String q="select valor ";
		q+="from ta_configuracion ";
		q+="where grupo like 'datos' ";
		q+="and clave like 'telefono' ";
		Object[][] results=this.getResults(q);
		if(results!=null){
			if(results.length>0){
				telefono=(String) results[0][0];
			}
		}
		return telefono;
	}
	
	public String getEmail(){
		String nombre="";
		String q="select valor ";
		q+="from ta_configuracion ";
		q+="where grupo like 'datos' ";
		q+="and clave like 'web_email' ";
		Object[][] results=this.getResults(q);
		if(results!=null){
			if(results.length>0){
				nombre=(String) results[0][0];
			}
		}
		return nombre;
	}
	
	public String getInsertNuevo(String idpedido,String cuenta,String descripcion,String estado){
		String q="";
		descripcion=descripcion.replaceAll("'", "");
		q+="insert into b_pdp (idpedido,descripcion,fecha,cuenta,estado) values ('"+idpedido+"','"+descripcion+"',getdate(),'"+cuenta+"','"+estado+"')";
		return q;
	}
	
	public String getUpdateQuery(String idpedido,
			String descripcion,String cliente,
			String cliente_descripcion,String vendedor,String informacion,
			String idtransporte,String fecha_envio,String guia,String fecha_agenda,
			String total,String seguimiento,
			String domicilio,String ciudad,String idprovincia,String cpostal,String telefono,String estado,String observaciones,String email){
		String q="update b_pdp set ";
		q=q+" Descripcion='"+descripcion+"',";
		q=q+" Enviar = '',";
		q=q+" cuenta='"+cliente+"',";
		q=q+" Cliente_Descripcion='"+cliente_descripcion+"',";
		q=q+" datos_extra='"+informacion+"',";
		q=q+" Plazo='',";	
		q=q+" estado='"+estado+"',";
		q=q+" cond_iva='',";
		q=q+" cuit='',";
		q=q+" clase='',";
		q=q+" tipo_doc='',";
		q=q+" vence=null,";
		q=q+" idvendedor='"+vendedor+"', ";
		q=q+" ordinario=0, ";
		q=q+" ultima_modificacion=getdate(), ";
		q=q+" idtransporte='"+idtransporte+"',";
		q=q+" fecha_envio='"+fecha_envio+"',";
		q=q+" guia='"+guia+"', ";
		q=q+" fecha_agenda='"+fecha_agenda+"',";
		q=q+" total="+total+", ";
		q=q+" seguimiento="+seguimiento+", ";
		q+=" domicilio='"+domicilio+"',";
		q+=" cliente_localidad='"+ciudad+"',";
		q+=" cliente_idprovincia='"+idprovincia+"',";
		q+=" cliente_cpostal='"+cpostal+"', ";
		q+=" cliente_telefono='"+telefono+"', ";
		q+=" observaciones='"+observaciones+"', ";
		q+=" email='"+email+"' ";
		q=q+" where idpedido = '"+idpedido+"'";
		
		return q;
	}

	public String getGuardarQuery(String idpedido,
			String descripcion,String cliente,String cliente_descripcion,
			String idvendedor,String info,
			String idtransporte,String fecha_envio,String guia,String fecha_agenda,
			String total,String seguimiento,
			String domicilio,String ciudad,String idprovincia,String cpostal,String telefono,String estado,String observaciones,String email)
			{
		String q="insert into b_pdp ";
		q+="(idPedido,Descripcion,";
		q+="fecha,Cuenta,";
		q+="Cliente_Descripcion,";
		q+="Plazo,estado,cond_iva,idvendedor,cuit,";
		q+="vence,ordinario,datos_extra,enviar,";
		q+="tipo_doc,clase,ultima_modificacion,idtransporte,fecha_envio,guia,fecha_agenda,";
		q+="total,seguimiento,";
		q+="domicilio,cliente_localidad,cliente_idprovincia,cliente_cpostal,cliente_telefono,observaciones,";
		q+="idcreador,email)";
		q=q+" values (";
		q+="'"+idpedido+"',";
		q+="'"+descripcion+"',";
		q+="getdate(),";
		q+="'"+cliente+"',";
		q+="'"+cliente_descripcion+"',";
		q+="'',";//plazo
		q+="'"+estado+"',";//estado
		q+="'',";//iva
		q+="'"+idvendedor+"',";
		q+="'',";//cuit
		q+="'',";//vence
		q+="'',";//ordinario
		q+="'"+info+"',";
		q+="'',";//enviar
		q+="'',";//tipo_doc
		q+="'',";//clase
		q+="getdate(),";
		q+="'"+idtransporte+"',";
		q+="'"+fecha_envio+"',";
		q+="'"+guia+"',";
		q+="'"+fecha_agenda+"',";
		q+=""+total+",";
		q+=""+seguimiento+",";
		q+="'"+domicilio+"',";
		q+="'"+ciudad+"',";
		q+="'"+idprovincia+"',";
		q+="'"+cpostal+"',";
		q+="'"+telefono+"',";
		q+="'"+observaciones+"',";
		q+="'"+idvendedor+"',";
		q+="'"+email+"'";
		q=q+")";
		
		return q;
	}
	
	public String getUpdateTc(String tc){
		String q="";
		q+="update b_ta_cpte set x_ultimo_nro = x_ultimo_nro + 1 ";
		q+="where codigo = '"+tc+"' ";
		return q;
	}
	
	public boolean updateTC(){
		boolean ok=true;
		this.clearBatch();
		this.addBatch(this.getUpdateTc(tc));
		ok=!this.executeBatch();
		return ok;
	}
	public String getUltimaModificacion(String idpedido){
		String q="";
		q+=" select ultima_modificacion "; 
		q+=" from ";
		q+=" b_pdp ";
		q+=" where idpedido like '"+idpedido+"' ";
		Object[][] results=this.getResults(q);
		String modificado="";
		if (results!=null){
			if (results.length>0){
				modificado=(String) results[0][0];
			}
		}
		return modificado;
	}
	
	public Object[][] getVendedor(String iduser){
		String q="";
		q+="select b.idvendedor,v.nombre ";
		q+="from b_users b left outer join v_ta_vendedores v on ltrim(b.idvendedor)=ltrim(v.idvendedor) ";
		q+="where b.iduser like '"+iduser+"'";
		return this.getResults(q);
	}
	
	public Object[][] getVendedorPorNombre(String nombre){
		String q="select idvendedor from v_ta_vendedores ";
		q+="where nombre like '"+nombre+"' ";
		return this.getResults(q);
	}
	
	public Object[][] getPedido(String idpedido){
		String q="";
		q+="select pedido.descripcion,";
		q+="pedido.fecha,";
		q+="pedido.ultima_modificacion,";
		q+="pedido.cuenta,";
		q+="pedido.cliente_descripcion,";
		q+="pedido.datos_extra,";
		q+="pedido.idvendedor,";
		q+="vendedores.nombre, ";
		q+="pedido.idtransporte,";
		q+="isnull(transporte.nombre,''),";
		q+="convert(varchar(10),pedido.fecha_envio,105),";
		q+="pedido.guia, ";
		q+="convert(varchar(10),pedido.fecha_agenda,105),";
		q+="pedido.seguimiento, ";
		q+="isnull(pedido.domicilio,''),";
		q+="isnull(pedido.cliente_localidad,''),";
		q+="isnull(pedido.cliente_idprovincia,''),";
		q+="isnull(pedido.cliente_cpostal,''),";
		q+="isnull(pedido.cliente_telefono,''),";
		q+="isnull(pedido.estado,''),";
		q+="isnull(pedido.observaciones,''),";
		q+="isnull(pedido.idcreador,''),";
		q+="isnull(pedido.email,'')";
		q+="from b_pdp pedido left outer join v_ta_vendedores vendedores ";
		q+="on ltrim(pedido.idvendedor)=ltrim(vendedores.idvendedor) ";
		q+=" left outer join transportes transporte ";
		q+="on ltrim(pedido.idtransporte)=ltrim(transporte.idtransporte) ";
		q+="where pedido.idpedido like '"+idpedido+"' ";
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
	}
	
	public boolean eliminado(String numero){
		boolean existe=false;
		String q="select * from b_pdp where idpedido like '"+numero+"' and eliminar=1 ";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				existe=true;
			}
		}
		return existe;
	}
}
