package aplicacion.ventas.pedido.logic;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import stock1.Convertidor;

import aplicacion.modelo.logic.Data;
public class _Data extends Data{
	private String TC="PDC";
	
	private int getProximoPG(String tc,String negocio){
		int c=0;
		c=this.getProximoTC(tc,negocio);
		return c;
	}
	
	public Object[][] getEquivalencias(String idarticulo){
		String q="";
		q+="select m.idarticulo,m.descripcion,m.descripabrev,m.precio2,isnull(s.stock,0) ";
		q+="from b_articulos_equivalencias e left outer join v_ma_articulos m ";
		q+=" on (e.idarticulo1=m.idarticulo or e.idarticulo2=m.idarticulo) ";
		q+=" left outer join view_stock s on m.idarticulo=s.idarticulo ";
		q+=" where (e.idarticulo1 like '"+idarticulo+"' or e.idarticulo2 like '"+idarticulo+"')";
		q+=" and m.idarticulo not like '"+idarticulo+"' ";
		//q+=" group by m.idarticulo,m.descripcion,m.descripabrev,m.precio2 ";
		return this.getResults(q);
	}
	
	public String getInsertFaltante(String idarticulo, String descripcion,String ip,String idvendedor, String cantidad,String stock,String tc,String idcomprobante){
		String q="insert into b_articulos_faltantes (idarticulo,descripcion,fecha,idusuario,ip,cantidad,stock,tc,idcomprobante) ";
		q+=" values ('"+idarticulo+"','"+descripcion+"',getdate(),'"+idvendedor+"','"+ip+"',"+cantidad+","+stock+",'"+tc+"','"+idcomprobante+"') ";
		return q;
	}
	
	private int getProximoTC(String tc, String negocio){
		int c=0;
		String q="";
		q+="select x_ultimo_nro from b_ta_cpte ";
		q+="where codigo = '"+tc+"' and unegocio like '"+negocio+"'";
		Object[][] aux=connection_handler.getDefaultConnector().getResults(q);
		try{
			c=new Integer(aux[0][0].toString());
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return c;
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
	public String getUpdateTc(String tc){
		String q="";
		q+="update b_ta_cpte set x_ultimo_nro = x_ultimo_nro + 1 ";
		q+="where codigo = '"+tc+"' ";
		return q;
	}
	
	public int updateTC(String tc,String negocio){
		int c=0;
		String q="";
		q+="update b_ta_cpte set x_ultimo_nro = x_ultimo_nro + 1 where codigo like '"+tc+"' and unegocio like '"+negocio+"'";
		
		Object[][] aux=connection_handler.getDefaultConnector().getResults(q);
		try{
			c=new Integer(aux[0][0].toString());
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return c;
	}
	
	
	
	
	public String getQueryRemitoGenerado(String idpedido){
		String q="";
		q+="select remito ";
		q+="from b_pdc_rms ";
		q+="where idpedido like '"+idpedido+"'  ";
		q+="and eliminado=0 ";
		return q;
	}

	public boolean esCtaCte(String idcliente){
		boolean ok=false;
		String q="select rtrim(ltrim(idcond_cpra_vta))  from ma_cuentasadic where codigo like '"+idcliente+"' ";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				ok=results[0][0].toString().compareTo("10")==0;
			}
		}
		return ok;
	}
	
	public boolean copia(){
		boolean copia=false;
		String valor=this.getParametroServer("copia_remito");
		if (valor!=null){
			if (valor.compareTo("1")==0){
				copia=true;		
			}
		}
		return copia;
	}
	public boolean existeRemitoGenerado(String idremito){
		boolean ok=false;
		String q="select tc from v_mv_cpte where tc like 'rm' and idcomprobante like '"+idremito+"' ";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				ok=true;
			}
		}
		return ok;
	}
	public String getRemitoGenerado(String idpedido){
		Object[][] results=this.getResults(this.getQueryRemitoGenerado(idpedido));
		String remito="";
		if (results!=null){
			if (results.length>0){
				remito=(String) results[0][0];
			}
		}
		return remito;
	}
	
	public Object[][] getPedidoItemsShow(String idpedido){
		String q="";
		q+="";
		q+="select isnull(items.seleccionado,0),items.item,items.idarticulo,rtrim(ltrim(items.descripcion)),items.cantidad,items.cotiza,items.total,isnull(articulo.descripabrev,'') ";
		q+="from b_pdc_item items ";
		q+="left outer join v_ma_articulos articulo ";
		q+="on items.idarticulo=articulo.idarticulo ";
		q+="where items.idpedido like '"+idpedido+"' ";
		q+="order by items.item ";
		System.out.println(q);
		Object[][] results=getResults(q);
		return results;
	}
	
	public Object[][] getPedidoItemsShow(String idpedido,String item){
		String q="";
		q+="";
		q+="select isnull(items.seleccionado,0),items.item,items.idarticulo,rtrim(ltrim(items.descripcion)),items.cantidad,items.cotiza,items.total,isnull(articulo.descripabrev,'') ";
		q+="from b_pdc_item items ";
		q+="left outer join v_ma_articulos articulo ";
		q+="on items.idarticulo=articulo.idarticulo ";
		q+="where items.idpedido like '"+idpedido+"' and items.item like '"+item+"' ";
		q+="order by items.item ";
		System.out.println(q);
		Object[][] results=getResults(q);
		return results;
	}
	public Object[][] getPedidoItems(String idpedido){
		String q="";
		q+="";
		q+="select idarticulo,ltrim(rtrim(descripcion)),cantidad,costo,cotiza,isnull(seleccionado,0),isnull(descuento,0) ";
		q+="from b_pdc_item ";
		q+="where idpedido like '"+idpedido+"' ";
		q+="order by item ";
		System.out.println(q);
		Object[][] results=getResults(q);
		return results;
	}
	
	public String getInstruccionAsociacionRemitoPedido(String idpedido,String iduser){
		String q="insert into b_pdc_rms (idpedido,remito,creacion,idusuario) values ";
		q+="(";
		q+="'"+idpedido+"',";
		q+="@idremito,";
		q+="getdate(),";
		q+="'"+iduser+"'";
		q+=")";
		return q;
	}
	
	

	public Object[][] getPedido(String idpedido){
		String q="";
		q+="select pedido.descripcion,pedido.fecha_creacion,";
		q+="pedido.ultima_modificacion,(case when isnull(pedido.cliente,'*') like '*' then '112010001' else pedido.cliente end),pedido.cliente_descripcion,";
		q+="pedido.datos_extra,pedido.idvendedor,vendedores.nombre, ";
		q+="pedido.idtransporte,isnull(transporte.nombre,''),";
		q+="convert(varchar(10),pedido.fecha_envio,105),";
		q+="pedido.guia, ";
		q+="convert(varchar(10),pedido.fecha_agenda,105),";
		q+="pedido.seguimiento, ";
		q+="isnull(pedido.domicilio,''),";
		q+="isnull(pedido.cliente_localidad,''),";
		q+="isnull(pedido.cliente_idprovincia,''),";
		q+="isnull(pedido.cliente_cpostal,''),";
		q+="isnull(pedido.cliente_telefono,''),";
		q+="isnull(pedido.idcreador,''),";
		q+="isnull(pedido.iddeposito,'   1'),";
		q+="isnull(pedido.negocio,'   1'),";
		q+="pedido.numero ";
		q+="from b_pdc pedido left outer join v_ta_vendedores vendedores ";
		q+="on ltrim(pedido.idvendedor)=ltrim(vendedores.idvendedor) ";
		q+=" left outer join transportes transporte ";
		q+="on ltrim(pedido.idtransporte)=ltrim(transporte.idtransporte) ";
		q+="where pedido.idpedido like '"+idpedido+"' ";
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
	}
	
	public Object[][] getStock(String idarticulo){
		String q="";
		q+="select a.idarticulo,a.descripcion,isnull(s.stock,0) ";
		q+="from v_ma_articulos a ";
		q+="left outer join view_stock s on a.idarticulo=s.idarticulo ";
		q+="where a.idarticulo like '"+idarticulo+"' ";
		//q+="group by  a.idarticulo,a.descripcion ";
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
	}
	
	public Object[][] getData(String idarticulo){
		String q="";
		q+="select m.idarticulo,m.descripcion,m.descripabrev,isnull(s.stock,0),isnull(m.suspendidov,0),convert(varchar(10),m.actualizacion,105),m.cuentaproveedor,c.descripcion,m.precio2 ";
		q+="from v_ma_articulos m ";
		q+="left outer join view_stock s on m.idarticulo=s.idarticulo "; 
		q+="left outer join ma_cuentas c on m.cuentaproveedor=c.codigo ";
		q+="where m.idarticulo like '"+idarticulo+"' ";
		//q+="group by m.idarticulo,m.descripcion,m.descripabrev,isnull(m.suspendidov,0),convert(varchar(10),m.actualizacion,105),m.cuentaproveedor,c.descripcion,m.precio2 ";
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
	}
	
	public Object[][] getUltimoPedido(String idproveedor){
		String q="";
		q+="select top 1 idpedido,fecha_creacion ";
		q+="from b_pep ";
		q+="where cliente like '"+idproveedor+"' ";
		q+="and estado like 'nuevo' ";
		q+="order by idpedido desc ";
		return this.getResults(q);
	}
	
	

	/*
	public Object[][] getData(String idarticulo){
		String q="";
		q+="select m.idarticulo,m.descripcion,m.descripabrev,isnull(s.cantidadud,0),isnull(m.suspendidov,0),convert(varchar(10),c.ultimo_upd,105) ";
		q+="from v_ma_articulos m ";
		q+="left outer join Stk_Stock_UDKG s on m.idarticulo=s.idarticulo "; 
		q+="left outer join b_alias a ";
		q+="on m.idarticulo=a.idarticulo and m.cuenta_actualizacion=a.idproveedor ";
		q+="left outer join b_codigos c ";
		q+="on a.idcodigo=c.idcodigo and a.lineaproveedor=c.lineaproveedor and a.idproveedor=c.idproveedor ";
		q+="where m.idarticulo like '"+idarticulo+"' ";
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
	}
	*/
	public double getPrecioPublico(String idarticulo){
		double prc=0.0;
		String q="select precio2 from v_ma_articulos where idarticulo like '"+idarticulo+"'";
		Object[][] results=getResults(q);
		if (results!=null){
			if (results.length>0){
				String precio=(String) results[0][0];
				try {
					prc=new Double(precio);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return prc;
	}
	
	public double getPrecioCosto(String idarticulo){
		double prc=0.0;
		String q="select costo from v_ma_articulos where idarticulo like '"+idarticulo+"'";
		Object[][] results=getResults(q);
		if (results!=null){
			if (results.length>0){
				String precio=(String) results[0][0];
				prc=new Double(precio);
			}
		}
		return prc;
	}
	public boolean existeArticulo(String idarticulo){
		boolean existe=false;
		Object[][] results=this.getArticulo(idarticulo);
		if (results!=null){
			if (results.length>0){
				existe=true;
			}
		}
		return existe;
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
		q+="select b2.idarticulo,a.descripcion,a.descripabrev,round(a.precio2,2),s.stock,count(*) as referencias ";
		q+="from v_mv_cpteinsumos b1 left outer join v_mv_cpte cb1  on b1.tc=cb1.tc and b1.idcomprobante=cb1.idcomprobante ";
		q+="left outer join v_mv_cpteinsumos b2 ";
		q+="on b1.tc=b2.tc and b1.idcomprobante=b2.idcomprobante ";
		q+="and b1.idarticulo <> b2.idarticulo ";
		q+="left outer join v_ma_Articulos a ";
		q+="on b2.idarticulo=a.idarticulo ";
		q+=" left outer join view_stock s on a.idarticulo=s.idarticulo ";
		q+="where b1.idarticulo like '"+idarticulo+"' ";
		q+="and b2.idarticulo not like '*' ";
		q+="and (b1.tc like 'tkfc' or b1.tc like 'tk' or b1.tc like 'fp' or b1.tc like 'fc') ";
		q+="and cb1.cuenta not in(select c.codigo from ";
		q+="ma_cuentasadic c ";
		q+="left outer join v_ta_categoria cat ";
		q+="on c.idcategoria=cat.idcategoria ";
		q+="where cat.descripcion like '%sucursal%') ";
		q+="group by b2.idarticulo,a.descripcion,a.descripabrev,round(a.precio2,2),s.stock ";
		q+="having count(*)>=2 ";
		q+="order by referencias desc ";
		results=this.getResults(q);
		return results;
	}
	
	public Object[][] getArticulosRelacionadosPDC(String idarticulo){
		Object[][] results=null;
		String q="";
		q+=" select b2.idarticulo,a.descripcion,a.descripabrev,round(a.precio2,2),s.stock,count(*) ";
		q+=" from b_pdc_item b1 left outer join b_pdc cb1 on b1.idpedido=cb1.idpedido ";
		q+=" left outer join b_pdc_item b2 ";
		q+=" on b1.idpedido=b2.idpedido ";
		q+=" and b1.idarticulo <> b2.idarticulo ";
		q+=" left outer join v_ma_Articulos a ";
		q+=" on b2.idarticulo=a.idarticulo ";
		q+=" left outer join view_stock s on a.idarticulo=s.idarticulo ";
		q+=" where b1.idarticulo like '"+idarticulo+"' ";
		q+=" and b2.idarticulo not like '*' ";
		q+=" and cb1.cliente not in(select c.codigo from ";
		q+=" ma_cuentasadic c ";
		q+=" left outer join v_ta_categoria cat ";
		q+=" on c.idcategoria=cat.idcategoria ";
		q+=" where cat.descripcion like '%sucursal%') ";
		q+=" group by b2.idarticulo,a.descripcion,a.descripabrev,round(a.precio2,2),s.stock ";
		q+=" having count(*)>=2 ";
		q+=" order by count(*) desc ";
		q+="";
		results=this.getResults(q);
		return results;
	}
	
	public boolean bloqueado(String articulo){
		boolean suspendidov=false;
		Object[][] results=getArticulo(articulo);
		if (results!=null){
			if (results.length>0){
				System.out.println("Valido?");
				suspendidov=results[0][4].toString().compareTo("1")==0;		
			}
		}
		return suspendidov;
	}
	public Object[][] getArticulo(String idarticulo){
		String q="";
		q+="select m.descripcion,m.precio2,m.costo,m.descripabrev,m.suspendidov,m.suspendidoc,isnull(s.stock,0) ";
		q+="from v_ma_articulos m left outer join view_stock s on m.idarticulo=s.idarticulo "; 
		q+="where m.idarticulo like '"+idarticulo+"' ";
		//q+="group by m.descripcion,m.precio2,m.costo,m.descripabrev,m.suspendidov,m.suspendidoc ";
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
	}
	
	public Object[][] getVendedor(String iduser){
		String q="";
		q+="select b.idvendedor,v.nombre ";
		q+="from b_users b left outer join v_ta_vendedores v on ltrim(b.idvendedor)=ltrim(v.idvendedor) ";
		q+="where b.iduser like '"+iduser+"'";
		return this.getResults(q);
	}

	public Object[][] getDeposito(String iduser){
		String q="";
		q+="select b.deposito ";
		q+="from b_users b ";
		q+="where b.iduser like '"+iduser+"'";
		return this.getResults(q);
	}
	
	public Object[][] getNegocio(String iduser){
		String q="";
		q+="select b.negocio ";
		q+="from b_users b ";
		q+="where b.iduser like '"+iduser+"'";
		return this.getResults(q);
	}
	
	public String getIdDeposito(String iduser){
		String iddeposito="   1";
		Object[][] results=this.getDeposito(iduser);
		if (results!=null){
			if (results.length>0){
				iddeposito=(String) results[0][0];
			}
		}
		return iddeposito;
	}
	
	public String getNegocioId(String iduser){
		String negocio="   1";
		Object[][] results=this.getNegocio(iduser);
		if (results!=null){
			if (results.length>0){
				negocio=(String) results[0][0];
			}
		}
		return negocio;
	}
	
	public Object[][] getVendedorPorNombre(String nombre){
		String q="select idvendedor from v_ta_vendedores ";
		q+="where nombre like '"+nombre+"' ";
		return this.getResults(q);
	}
	
	public boolean eliminado(String numero){
		boolean existe=false;
		String q="select * from b_pdc where idpedido like '"+numero+"' and eliminar=1 ";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				existe=true;
			}
		}
		return existe;
	}
	
	public boolean existe(String numero){
		boolean existe=false;
		String q="select * from b_pdc where idpedido like '"+numero+"' ";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				existe=true;
			}
		}
		return existe;
	}

	public boolean existe(String tc,String numero){
		boolean existe=false;
		String q="select * from b_fvn where tc like '"+tc+"' and idcomprobante like '"+numero+"' ";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				existe=true;
			}
		}
		return existe;
	}
	
	public String getUpdateTC(String tc){
		String q="";
		q+="update b_ta_cpte set x_ultimo_nro = x_ultimo_nro + 1 ";
		q+="where codigo = '"+tc+"' ";
		return q;
	}
	
	public String getGuardarQuery(String tc,String idcomprobante,String idvendedor,String fecha,String cuenta,String cuenta_descripcion,String articulos,String subtotal,String total,String remito){
		Convertidor Cv=new Convertidor();
		Double desc_porc=0.0;
		Double desc_imp=0.0;
		
		
		//values[2]
		String q="insert into B_FVN ";
		q=q+"(tc,idcomprobante,Fecha_Carga,idusuario,ultima_modificacion,fecha_comprobante,fecha_vencimiento,cuenta,cuenta_descripcion,";
		q=q+"observacion,articulos,subtotal,desc_porc,desc_imp,total_cpte,tc_origen,idcomprobante_origen,pagada,anulada)";
		q=q+" values ('"+tc+"',";
		q=q+" '"+idcomprobante+"',";
		/*q=q+" '"+this.idComprobanteAssoc.getText()+"',";*/
		q=q+" getDate(),";
		q=q+" '"+idvendedor+"',";
		q=q+" getDate(),";
		q=q+" '"+fecha+"',";
		q=q+" '"+fecha+"',";
		q=q+" '"+cuenta+"',";
		q=q+" '"+cuenta_descripcion+"',";
		q=q+" '',";
		q=q+" "+articulos+",";	
		q=q+""+subtotal+",";
		q=q+""+desc_porc+",";
		q=q+""+desc_imp+",";
		q=q+""+total+",";
		/*desc % por pago, descuento en importe por pago, total a pagar*/
		q=q+"'RM',";
		q=q+"'"+remito+"',";
		q=q+"0,";
		q=q+"0";
		q=q+")";
		System.out.println(q);
		return q;
	}

	public String getGuardarItems(String tc,String idcomprobante,int i,String art,String desc,String qty,String precio,String sdesc_porc,String sdesc_imp,String stotal){
		Double Sub=0.0;
		Double desc_porc=0.0;
		Double desc_imp=0.0;
		Double total=0.0;
		try {
			Sub=new Double(precio.replace(",", ""));
		}catch(Exception e){
			
		}
		try {
			desc_porc=new Double(sdesc_porc.replace(",", ""));
		}catch(Exception e){
			
		}
		try {
			desc_imp=new Double(sdesc_imp.replace(",", ""));
		}catch(Exception e){
			
		}
		try {
			total=new Double(stotal.replace(",", ""));
		}catch(Exception e){
			
		}
		String q="";
		q=q+"insert into B_FVN_Item ";
		q=q+" (tc,idcomprobante,sec,idarticulo,descripcion,cantidad,precio,desc_porc,desc_imp,total)";
		q=q+" values (";
		q=q+" '"+tc+"','"+idcomprobante+"','"+i+"','"+art+"','"+desc+"',"+qty+","+precio+","+desc_porc+","+desc_imp+","+total+")";
		return q;
	}
	
	public String getMes_Operativo(){
		Calendar C=Calendar.getInstance();
		Date date=null;
		String s1="";
		DateFormat formatter;
		Locale locale = Locale.getDefault();
		    try {
		    	date = C.getTime();
		    	formatter = new SimpleDateFormat("MM", locale);
		        s1 = formatter.format(date);
		    }
		        catch(Exception e){
		}
		  return s1;      
	}
	
	public int getNumeroAsientoProximo(){
		int num=0;
		String mes_operativo=this.getMes_Operativo();
		String q=" select top 1 isnull(numero_asiento,0) from b_mv_asientos ";
		q=q+" where mes_operativo ="+mes_operativo+" ";
		q=q+" order by numero_asiento desc ";
		System.out.println(q);
		Object[][] results=connection_handler.getDefaultConnector().getResults(q);
		if (results!=null){
			if (results.length>0){
				String numero=results[0][0].toString();
				try{
					num=new Integer(numero);	
					num++;
				}catch(Exception e){
					System.out.println(e.getMessage());
				}
				
			}
		}
		return num;
	}
	
	public String getInsertQueryVMVStock(String tc,String idcomprobante,String idarticulo,String descripcion,String cantidad,int sec,String fecha,String precio,String cuenta){
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
		q=q+"(tc,idcomprobante,secuencia,fecha,idarticulo,descripcion,idunidad,cantidadud,cantidad,costo,precioventa,stock,iddeposito,UNEGOCIO,cuentaproveedor)";
		q=q+"values ";
		q=q+"('"+tc+"','"+idcomprobante+"','"+sec+"','"+fecha+"','"+idarticulo+"','"+descripcion+"','   1',"+qty+","+qty+","+price+",0.0,0,'   1','   1','"+cuenta+"')";
		return q;
		}
	
	public String getInsertACuenta(String mes,String asiento,String sec,String detalle,String fecha,String cuenta,String descripcion,String obs,String tc,String idcomp,String debe_haber,String importe,String caja){
		String q="";
		
		double price=0.0;
		try {
			importe=importe.replace(",", "");
			price=new Double(importe);
			
		}catch(Exception e){
			
		}
		q=q+"insert into b_mv_asientos ";
		q=q+" (mes_operativo,numero_asiento,secuencia,detalle,fecha,cuenta,cuenta_descripcion,observacion,tc,idcomprobante,debe_haber,importe,anulado,idcajas)";
		q=q+" values (";
		q=q+" '"+mes+"','"+asiento+"','"+sec+"','"+detalle+"','"+fecha+"','"+cuenta+"','"+descripcion+"','"+obs+"','"+tc+"','"+idcomp+"','"+debe_haber+"',"+price+",";
		q=q+"0,'"+caja+"')";
		return q;
	}
	public List<String> getInstruccionesAsientoDeComprobante(String cuenta,
			String descripcion,
			String tc,
			String caja,
			String idcomprobante,
			String importe,
			String fecha,
			boolean credito
			){
		List<String> _instrucciones=new ArrayList<String>();
		String obs="";
		
		String debe_haber="";
		if (credito){
			debe_haber="D";
		}else{
			debe_haber="H";
		}
		int sec=0;
		String detalle="Ventas Varias";
		String mes=this.getMes_Operativo();
		String asiento=""+this.getNumeroAsientoProximo();
		String _instruccion=this.getInsertACuenta(mes, asiento, ""+sec, detalle,fecha, cuenta, descripcion, obs, tc, idcomprobante, debe_haber, importe,caja);
		_instrucciones.add(_instruccion);
		cuenta="43101";
		descripcion="Ventas Varias";
		sec++;
		if (credito){
			debe_haber="H";	
		}else{
			debe_haber="D";	
		}
		_instruccion=this.getInsertACuenta(mes, asiento, ""+sec, detalle,fecha, cuenta, descripcion, obs, tc, idcomprobante, debe_haber, importe,caja);
		_instrucciones.add(_instruccion);
		return _instrucciones;	
	}
	public boolean esResponsableInscripto(String idcliente){
		boolean existe=false;
		String q="select * from ma_cuentasadic where codigo like '"+idcliente+"' and ltrim(iva) like '1' ";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				existe=true;
			}
		}
		return existe;
	}

	public boolean utilizaPrecioPublico(String idcliente){
		boolean existe=false;
		String q="select isnull(clase,2) from ma_cuentasadic where codigo like '"+idcliente+"' and isnull(clase,2)=2 ";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				existe=true;
			}
		}
		return existe;
	}

	

	
	public Object[][] getClientInformation(String idcliente){
		
		String q="";
		q=q+"select M.codigo,M.descripcion ";
		q=q+"FROM MA_CUENTAS M ";
		q=q+" where M.codigo like '"+idcliente+"' and M.titulo = 0";
		Object[][] results=connection_handler.getDefaultConnector().getResults(q);
		return results;
	}
	
	
	
	public String getProximoPGCorrecto(String tc,String negocio){
		String prox="";
		prox=this.getProximoPG_Ceros(tc,negocio);
		return prox;
	}
	
	
	
	
	
	
	private String getProximoPG_Ceros(String tc,String negocio){
		String c="";
		int i=this.getProximoPG(tc,negocio);
		String aux=""+i;
		while (aux.length()<8){
			aux="0"+aux;
		}
		c=aux;
		return c;
	}
	
	
	
	
	
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
		q=q+" isnull(a.calle,''),";
		q+="  isnull(ltrim(a.numero),'')";
		q+="  ,isnull(a.piso,''), ltrim(a.cpostal), ";
		q=q+" a.provincia,";
		q+="  st.descripcion,tip.descripcion,a.documento_tipo,isnull(td.descripcion,''), ";
		q+="  isnull(a.limite_credito,0),isnull(a.descuento,0) ";
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
	
	public Object[][] getCompras(String idpedido){
		String q="";
		q+="select convert(varchar(10),pep.fecha_creacion,105),pdc.idpedido,pep.descripcion,pep.cliente,c.descripcion,pep.estado ";
		q+="from b_pep_pdc pdc left outer join b_pep pep ";
		q+="on pdc.idpedido=pep.idpedido ";
		q+="left outer join ma_cuentas c ";
		q+="on pep.cliente=c.codigo ";
		q+="where pdc.idpedido_pdc like '"+idpedido+"' ";
		return this.getResults(q);
	}
	
	
	public Object[][] getRemitosViejos(String idpedido){
		String q="";
		q+="select  remitos.creacion,remitos.remito,isnull(remitos.idusuario,''),(case when count(remitos.remito)=count(alfa.tc) and count(remitos.remito)>0 then 'Alfa' else "; 
		q+="( (case when count(remitos.remito)=count(fvn.tc) and count(remitos.remito)>0  and fvn.anulada=0 then 'Beta' else  ";
		q+="( (case when count(remitos.remito)>0 and isnull(rmx.anulada,0)=0 then 'RM' else '' end) )  end )) end) as estado ";
		q+="from b_pdc pedido  ";
		q+="left outer join b_pdc_rms remitos on pedido.idpedido=remitos.idpedido and remitos.eliminado=0 "; 
		q+="left outer join b_fvn fvn on remitos.remito=fvn.idcomprobante_origen  ";
		q+="left outer join v_mv_cpte alfa on remitos.remito=alfa.comprobanteorigen  ";
		q+="left outer join v_mv_cpte rmx on remitos.remito=rmx.idcomprobante and rmx.tc like 'RM' "; 
		q+="where pedido.idpedido like '"+idpedido+"' ";
		q+="group by pedido.idpedido,remitos.creacion,remitos.remito,remitos.idusuario,rmx.anulada,fvn.anulada ";
		System.out.println("Remitos>"+q);
		return this.getResults(q);
	}
	public Object[][] getTransferencias(String idpedido){
		String q="";
		q+="select t.idtransferencia,t.fecha,o.nombre,d.nombre ";
		q+="from b_pdc_transferencia t  ";
		q+="left outer join v_ta_Vendedores o ";
		q+="on t.iduser_origen=o.idvendedor ";
		q+="left outer join v_ta_Vendedores d ";
		q+="on t.iduser_destino=d.idvendedor ";
		q+="where idpedido like '"+idpedido+"' ";
		q+=" order by t.idtransferencia ";
		return this.getResults(q);
	}
	public Object[][] getRemitos(String idpedido){
		String q="";
		q+=" select  remitos.creacion,remitos.remito,isnull(remitos.idusuario,''), ";
		q+=" (case when count(remitos.remito)=count(alfa.tc) and count(remitos.remito)>0  ";
		q+=" then 'Alfa' else ( (case when count(remitos.remito)=count(fvn.tc) and count(remitos.remito)>0  and fvn.anulada=0 then 'Beta' else  ( (case when count(remitos.remito)>0 and isnull(rmx.anulada,0)=0 "; 
		q+=" then 'RM' else '' end) )  end )) end) as estado, ";
		q+=" (case when count(remitos.remito)=count(alfa.tc) and count(remitos.remito)>0  ";
		q+=" then alfa.importe else ( (case when count(remitos.remito)=count(fvn.tc) and count(remitos.remito)>0  and fvn.anulada=0 then fvn.total_cpte else  ( (case when count(remitos.remito)>0 and isnull(rmx.anulada,0)=0 "; 
		q+=" then rmx.importe else 0 end) )  end )) end) as importe ";
		q+=" from b_pdc pedido   ";
		q+=" left outer join b_pdc_rms remitos on pedido.idpedido=remitos.idpedido "; 
		q+=" and remitos.eliminado=0  ";
		q+=" left outer join b_fvn fvn on remitos.remito=fvn.idcomprobante_origen  ";
		q+=" left outer join v_mv_cpte alfa on remitos.remito=alfa.comprobanteorigen   ";
		q+=" left outer join v_mv_cpte rmx on remitos.remito=rmx.idcomprobante and rmx.tc like 'RM' "; 
		q+=" where pedido.idpedido like '"+idpedido+"'  ";
		q+=" group by pedido.idpedido,remitos.creacion,remitos.remito,remitos.idusuario,rmx.anulada,fvn.anulada,alfa.importe,fvn.total_cpte,rmx.importe ";
		System.out.println("Remitos>"+q);
		return this.getResults(q);
	}
	public String getInsertControl(String idpedido,String idvendedor,String idarticulo,
			double precio_venta,double precio_sistema,double cantidad,double stock,String actualizacion,
			String iduser,String validacion,String ip){
		String q="";
		q+="insert into b_PDC_control (idpedido,tc,idcomprobante,idvendedor,fecha,idarticulo,precio_venta,precio_sistema,cantidad,stock,actualizacion,iduser,validacion,ip) ";
		q+=" values (";
		q+="'"+idpedido+"',";
		q+="'RM',";
		q+="@idremito,";
		q+="'"+idvendedor+"',";
		q+="getdate(),";
		q+="'"+idarticulo+"',";
		q+=""+precio_venta+",";
		q+=""+precio_sistema+",";
		q+=""+cantidad+",";
		q+=""+stock+",";
		q+="'"+actualizacion+"',";
		q+="'"+iduser+"',";
		q+="'"+validacion+"',";
		q+="'"+ip+"')";
		return q;
	}
	
	public String getIdpedido(String idremito){
		String q="";
		q+="select idpedido from b_pdc_rms where remito like '"+idremito+"' and eliminado=0 ";
		q+="";
		String idpedido="";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				idpedido=(String) results[0][0];
			}
		}
		return idpedido;
	}
	
	public int getRemitosValidos(String idpedido){
		String q="";
		q+="select count(*) ";
		q+="from b_pdc_rms ";
		q+="where idpedido like '"+idpedido+"' and eliminado=0 ";
		q+="group by idpedido ";
		int validos=0;
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				String _validos=(String) results[0][0];
				try {
					validos=new Integer(_validos);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return validos;
	}
	
	public String getEliminar(String idpedido){
		String q="";
		q+="update b_pdc set eliminar=1 where idpedido like '"+idpedido+"'";
		return q;
	}
	
	public String getUpdateQuery(String idpedido,
			String descripcion,String cliente,
			String cliente_descripcion,String vendedor,String informacion,
			String idtransporte,String fecha_envio,String guia,String fecha_agenda,
			String total,String seguimiento,
			String domicilio,String ciudad,String idprovincia,String cpostal,String telefono,String iddeposito){
		String q="update b_pdc set ";
		q=q+" Descripcion='"+descripcion+"',";
		q=q+" Enviar = '',";
		q=q+" Cliente='"+cliente+"',";
		q=q+" Cliente_Descripcion='"+cliente_descripcion+"',";
		q=q+" datos_extra='"+informacion+"',";
		q=q+" Plazo='',";	
		q=q+" estado='',";
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
		q+=" iddeposito='"+iddeposito+"' ";
		q=q+" where idpedido = '"+idpedido+"'";
		return q;
	}

	public String getUltimaModificacion(String idpedido){
		String q="";
		q+=" select ultima_modificacion "; 
		q+=" from ";
		q+=" b_pdc ";
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
	
	public String getDeleteItemQuery(String idpedido,int item){
		String q="";
		q+="delete from b_pdc_item ";
		q+="where item>"+item+" ";
		q+="and idpedido like '"+idpedido+"' ";
		return q;
	}
	
	public String getDeleteItemNumberQuery(String idpedido,int item){
		String q="";
		q+="delete from b_pdc_item ";
		q+="where item like '"+item+"' ";
		q+="and idpedido like '"+idpedido+"' ";
		return q;
	}
	
	public String getGuardarItemQuery(String idpedido,
			String item,String idarticulo,String descripcion,
			String cantidad,String costo,
			String cotiza,String total,String seleccionado,String descuento){
		String q="";
		q+="insert into b_pdc_item ";
		q+="(idpedido,item,idarticulo,descripcion,cantidad,costo,cotiza,total,";
		q+="observaciones,ave,ped,listo,seleccionado,descuento) ";
		q+="values (";
		q+="'"+idpedido+"',";
		q+=""+item+",";
		q+="'"+idarticulo+"',";
		q+="'"+descripcion+"',";
		q+=""+cantidad+",";
		q+=""+costo+",";
		q+=""+cotiza+",";
		q+=""+total+",";
		q+="'',";//obs
		q+="'',";//ave
		q+="'',";//ped
		q+="'',";//listo
		q+=seleccionado;
		q+=","+descuento;//descuento
		q+=")";
		return q;
	}
	
	public String getGuardarItemQueryHistory(String idoperacion,String idpedido,
			String item,String idarticulo,String descripcion,
			String cantidad,String costo,
			String cotiza,String total,String seleccionado,String descuento){
		String q="";
		q+="insert into b_auditor_pdc_item ";
		q+="(idoperacion,idpedido,item,idarticulo,descripcion,cantidad,costo,cotiza,total,";
		q+="observaciones,ave,ped,listo,seleccionado,descuento) ";
		q+="values (";
		q+="'"+idoperacion+"',";
		q+="'"+idpedido+"',";
		q+=""+item+",";
		q+="'"+idarticulo+"',";
		q+="'"+descripcion+"',";
		q+=""+cantidad+",";
		q+=""+costo+",";
		q+=""+cotiza+",";
		q+=""+total+",";
		q+="'',";//obs
		q+="'',";//ave
		q+="'',";//ped
		q+="'',";//listo
		q+=seleccionado;
		q+=","+descuento;//descuento
		q+=")";
		return q;
	}
	
	public String getUpdateItems(String idpedido,String item,String art,String desc,String qty,String cost,String price,String total,String seleccionado,String descuento){
		String q="";
		Convertidor Cv=new Convertidor();
		q+=" update b_pdc_item ";
		desc=desc.replaceAll("'", "");
		q+=" set idarticulo='"+art+"',descripcion='"+desc+"',cantidad="+qty+",cotiza="+price+",costo="+cost+",total="+total+",seleccionado="+seleccionado+",descuento="+descuento;
		q+=" where idpedido = '"+idpedido+"' and item = '"+item+"'";
		return q;
	}
	
	public String getGuardarQuery(String idpedido,
			String descripcion,String cliente,String cliente_descripcion,
			String idvendedor,String info,
			String idtransporte,String fecha_envio,String guia,String fecha_agenda,
			String total,String seguimiento,
			String domicilio,String ciudad,String idprovincia,String cpostal,String telefono,String iddeposito)
			{
		String q="insert into b_pdc ";
		q+="(idPedido,Descripcion,";
		q+="Fecha_Creacion,Cliente,";
		q+="Cliente_Descripcion,";
		q+="Plazo,estado,cond_iva,idvendedor,cuit,";
		q+="vence,ordinario,datos_extra,enviar,";
		q+="tipo_doc,clase,ultima_modificacion,idtransporte,fecha_envio,guia,fecha_agenda,";
		q+="total,seguimiento,";
		q+="domicilio,cliente_localidad,cliente_idprovincia,cliente_cpostal,cliente_telefono,idcreador,iddeposito";
		q+=")";
		q=q+" values (";
		q+="'"+idpedido+"',";
		q+="'"+descripcion+"',";
		q+="getdate(),";
		q+="'"+cliente+"',";
		q+="'"+cliente_descripcion+"',";
		q+="'',";//plazo
		q+="'',";//estado
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
		q+="'"+idvendedor+"',";
		q+="'"+iddeposito+"'";
		q=q+")";
		
		return q;
	}
	
	public String getGuardarQueryHistory(
			String idoperacion,
			String operacion,
			String iduser,
			String ip,
			String idpedido,
			String descripcion,String cliente,String cliente_descripcion,
			String idvendedor,String info,
			String idtransporte,String fecha_envio,String guia,String fecha_agenda,
			String total,String seguimiento,
			String domicilio,String ciudad,String idprovincia,String cpostal,String telefono)
			{
		String q="insert into b_auditor_pdc (";
		q+="idoperacion,operacion,iduser,ip,modificacion,";
		q+="idPedido,Descripcion,";
		q+="Fecha_Creacion,Cliente,";
		q+="Cliente_Descripcion,";
		q+="Plazo,estado,cond_iva,idvendedor,cuit,";
		q+="vence,ordinario,datos_extra,enviar,";
		q+="tipo_doc,clase,ultima_modificacion,idtransporte,fecha_envio,guia,fecha_agenda,";
		q+="total,seguimiento,";
		q+="domicilio,cliente_localidad,cliente_idprovincia,cliente_cpostal,cliente_telefono";
		q+=")";
		q=q+" values (";
		q+="'"+idoperacion+"',";
		q+="'"+operacion+"',";
		q+="'"+iduser+"',";
		q+="'"+ip+"',";
		q+="getdate(),";
		q+="'"+idpedido+"',";
		q+="'"+descripcion+"',";
		q+="getdate(),";
		q+="'"+cliente+"',";
		q+="'"+cliente_descripcion+"',";
		q+="'',";//plazo
		q+="'',";//estado
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
		q+="'"+telefono+"'";
		q=q+")";
		
		return q;
	}
	
	public boolean existItemPedido(String idpedido,int item){
		boolean existe=false;
		String q="select item from b_pdc_item ";
		q+="where idpedido like '"+idpedido+"' and item ="+item+"";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				existe=true;
			}
		}
		return existe;
	}
	
	public String getRemitoVariables(){
		String q="";
		q+="declare @sucursal varchar(4) ";
		q+="declare @numero varchar(8) ";
		q+="declare @letra varchar(1) ";
		q+="declare @idremito varchar(13) ";
		q+="set @sucursal='0000' ";
		q+="set @numero='0000000' ";
		q+="select @sucursal=substring(@sucursal,1,4-len(x_suc_default))+convert(varchar(1),x_suc_default), ";
		q+="@numero=substring(@numero,1,8-len(x_ultimo_nro))+convert(varchar,x_ultimo_nro), ";
		q+="@letra='X' ";
		q+="from v_ta_cpte ";
		q+="where codigo like 'RM' ";
		q+="set @idremito=@sucursal+@numero+@letra ";
		//q+="select @numero,@sucursal,@letra,@idremito ";
		return q;
	}
	public String getInsertRemito(
			String idcliente,
			String fecha,
			String idvendedor,
			String importe,String importe_s_iva,String iva,
			String condicion
			){
		
		String iddeposito="   1";
		String aliciva="21";
		String q="";
		q+=this.getRemitoVariables();
		q+="declare @codigo varchar(9) ";
		q+="declare @nombre varchar(50) ";
		q+="declare @domicilio varchar(100) ";
		q+="declare @telefono varchar(100) ";
		q+="declare @localidad varchar(50) ";
		q+="declare @provincia varchar(4) ";
		q+="declare @cpostal varchar(10) ";
		q+="declare @tipo_documento varchar(4) ";
		q+="declare @numero_documento varchar(13) ";
		q+="declare @iva varchar(4) ";
		q+="declare @condicion varchar(4) ";
		q+="declare @clase varchar(4) ";
		q+="select @codigo=m.codigo, ";
		q+="@nombre=m.descripcion, ";
		q+="@domicilio=RTRIM(LTRIM(CASE WHEN ISNULL(calle,'.') like '' then '.' else ISNULL(calle,'.') end))+' '+RTRIM(LTRIM(isnull(numero,''))), ";
		// 
		q+="@telefono=ma.Telefono, ";
		q+="@localidad=ma.Localidad, ";
		q+="@provincia=ma.provincia, ";
		q+="@cpostal=ma.cpostal, ";
		q+="@tipo_documento=ma.documento_tipo, ";
		q+="@numero_documento=RTRIM(LTRIM(ma.numero_documento)), ";
		q+="@iva=ma.iva, ";
		q+="@condicion=ma.idcond_cpra_vta, ";
		q+="@clase=ma.clase ";
		q+="from ma_cuentas m left outer join ma_cuentasadic ma ";
		q+="on m.codigo=ma.codigo ";
		q+="where m.codigo like '"+idcliente+"' ";

		q+="";
		q+="insert into v_mv_cpte ( ";
		q+="tc,idcomprobante,cuenta,nombre,Domicilio,fecha,fechaestinicio,fechaestfin, ";
		q+="Telefono,Localidad,idprovincia,codigopostal,documentotipo,documentonumero, ";
		q+="condicioniva,idcond_cpra_vta,claseprecio,observaciones, ";
		q+="Importe,Importe_S_iva, ";
		q+="finalizada,anulada,aprobado, ";
		q+="idmotivocpravta,porcDescuento1,idvendedor, ";
		q+="ImporteInsumos,ImporteServicios,ImporteOtrosConceptos,ImporteIva,ImporteIvaRec, ";
		q+="netoGravado,netonoGravado, ";
		q+="impreso,iddeposito,aliciva, ";
		q+="moneda,Cotizacion,sucursal,numero,letra, ";
		q+="unegocio,usuario,unegocio_destino)  ";
		q+="values ('RM',@idremito,@codigo,@nombre,@domicilio,getdate(),getdate(),getdate(),";
		q+="@telefono,@localidad,@provincia,@cpostal,@tipo_documento,@numero_documento,@iva,@condicion,@clase, ";
		q+="'',"+importe+","+importe_s_iva+",0,0,0, ";
		q+="'   1', ";
		q+="0, ";
		q+="'"+idvendedor+"', ";
		q+=""+importe+",0,0,";
		q+=""+iva+",0,"+importe_s_iva+",0,0,'"+iddeposito+"',"+aliciva+",'   1',1,@sucursal,@numero,@letra,'   1','VENTAS1','   1' ";
		q+=")";	
		return q;
	}
	
	public String getInsertRemitoItem(String idarticulo,String descripcion,double cantidad,
			double costo,double importe,double importe_s_iva,double total,double totalf){
		String idunidad="   1";
		String aliciva="21.0";
		String idcaja="   1";
		double equiv_udbase=1.0;
		double porcdto=0.0;
		double importedto=0.0;
		int exento=0;
		int transmision=1;
		int recepcion=1;
		String tipolista="V";
		String iddeposito="   1";
		String unegocio="   1";
		String q="";
		q=q+"insert into v_mv_cpteinsumos (";
		q=q+"tc,idcomprobante,idarticulo,";
		q=q+"descripcion,idunidad,cantidadud,cantidad,";
		q=q+"costo,importe,importe_s_iva,";
		q=q+"total,exento,claseprecio,";
		q=q+"tipolista,porcdto,importedto,";
		q=q+"aliciva,totalfinal,idunidadbase,idcaja,equiv_udbase,";
		q=q+"transmision,recepcion,iddeposito) ";
		q=q+"values (";
		q=q+"'RM',";
		q=q+"@idremito,";
		q=q+"'"+idarticulo+"',"; //idarticulo
		q=q+"'"+descripcion+"',"; //descripcion
		q=q+"'"+idunidad+"',"; //idunidad
		q=q+""+cantidad+","; //cantidad
		q=q+""+cantidad+","; //cantidadud=cantidad
		q=q+""+costo+","; //costo
		q=q+""+importe+","; //importe
		q=q+""+importe_s_iva+","; //importe_s_iva
		q=q+""+total+","; //total
		q=q+""+exento+",";//exento
		q=q+"@clase,"; //claseprecio
		q=q+"'"+tipolista+"',"; //tipolista
		q=q+""+porcdto+","; //porcdto
		q=q+""+importedto+","; //importedto
		q=q+""+aliciva+","; //aliciva
		q=q+""+totalf+","; //totalfinal!=total
		q=q+"'"+idunidad+"',";
		q=q+"'"+idcaja+"',";
		q=q+""+equiv_udbase+",";
		q=q+""+transmision+","; //transmision
		q=q+""+recepcion+","; //recepcion
		q=q+"'"+iddeposito+"'"; //iddeposito
		q=q+")";
		
		return q;
	}
	
	public String getComprobantesAlfaConRemito(String idremito){
		String q="";
		q+="select tc,idcomprobante from v_mv_cpte ";
		q+="where  ";
		q+="tcorigen like 'RM' and comprobanteorigen like '"+idremito+"' ";
		return q;
	}
	public Object[][] tieneComprobanteAlfaAsociado(String idremito){
		String q=this.getComprobantesAlfaConRemito(idremito);
		
		Object[][] results=this.getResults(q);
		
		return results;
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
	
	
	public String getMarcarAnuladoRemito(String remito){
		String q="";
		q=q+"update v_mv_cpte set anulada=1,finalizada=1 where tc like 'rm' and idcomprobante like '"+remito+"'";
		q=q+"";
		q=q+"";
		return q;
	}
	
	public String getDeleteRemitoPedidoQuery(String rmx){
		String q="update B_PDC_rms set eliminado=1";
		q=q+" where remito = '"+rmx+"' ";
		return q;
	}
	
	public String getUpdateRM(){
		String q="";
		q+="update v_ta_cpte set x_ultimo_nro = x_ultimo_nro + 1 where codigo like 'RM'";
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

	private String getSaldoAlfaQuery(String cuenta){
		String q="";
		q+="";
		q+="select ";
		q+="isnull(sum(case when a.[debe-haber] like 'D' then a.importe else -a.importe end),0) as CAlfa ";
		q+="from mv_asientos a ";
		q+="where a.cuenta like '"+cuenta+"' ";
		
		return q;
	}
	private String getSaldoBetaQuery(String cuenta){
		String q="";
		q+="";
		q+="select ";
		q+="isnull(sum(case when (b.debe_haber like 'D' ) then b.importe else -b.importe end),0) as CBeta ";
		q+="from  b_mv_asientos b ";
		q+="where b.cuenta like '"+cuenta+"' ";
		q+="and isnull(b.anulado,1)=0 ";
		
		return q;
	}
	public double getSaldoBeta(String cuenta){
		String q=this.getSaldoBetaQuery(cuenta);
		System.out.println(q);
		double saldo=0.0;
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				saldo=new Double((String)results[0][0]);
			}
		}
		return saldo;
		
	}
	public double getSaldoAlfa(String cuenta){
		String q=this.getSaldoAlfaQuery(cuenta);
		System.out.println(q);
		double saldo=0.0;
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				saldo=new Double((String)results[0][0]);
			}
		}
		return saldo;
	}
	
	public double getLimite(String cuenta){
		double limite=0.0;
		String q="";
		q+="select limite_credito from ma_cuentasadic where codigo like '"+cuenta+"' ";
		q+="";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				limite=new Double(results[0][0].toString());
			}
		}
		return limite;
	}
	
	
	public String getPedidosEstadoQuery(
			int top,String cuenta,String filters,String having){
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
		if (cuenta.compareTo("")!=0){
			q+="pedido.cliente like '"+cuenta+"' ";
		}
		if (filters.length()>0){
			q+=" and "+filters+"  ";
		}
		q+=" group by ";
		q+="pedido.idpedido,pedido.seguimiento,pedido.descripcion, ";
		q+="convert(varchar(10),isnull(pedido.fecha_agenda,pedido.ultima_modificacion),105) , ";
		q+="pedido.cliente, pedido.cliente_descripcion,isnull(pedido.total,0),isnull(vendedor.nombre,''),isnull(creador.nombre,''), ";
		q+="convert(varchar(10),pedido.fecha_creacion,105),";
		q+="rmx.anulada,fvn.anulada ";
		if (having.length()>0){
			q+=" having "+having;	
		}
		return q;
	}
}
