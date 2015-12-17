package aplicacion.ventas.facturador.logic;


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
	private String TC="FVN";
	
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
	
	
	public Object[][] getArticulo(String idarticulo){
		String q="";
		q+="select m.descripcion,m.precio2,m.costo,m.descripabrev,m.suspendidov,m.suspendidoc,sum(isnull(s.cantidadud,0)) ";
		q+="from v_ma_articulos m left outer join v_mv_stock s on m.idarticulo=s.idarticulo and s.anulado=0 "; 
		q+="where m.idarticulo like '"+idarticulo+"' ";
		q+="group by m.descripcion,m.precio2,m.costo,m.descripabrev,m.suspendidov,m.suspendidoc ";
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
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

	
	
	public String getComprobantesAlfaConRemito(String idremito){
		String q="";
		q+="select tc,idcomprobante from v_mv_cpte ";
		q+="where  ";
		q+="tcorigen like 'RM' and comprobanteorigen like '"+idremito+"' and anulada=0 ";
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
	
	public void UpdateTC(String tc){
		this.clearBatch();
		this.addBatch(this.getUpdateTC(tc));
		this.executeBatch();
	}
	
	public String getUpdateTC(String tc){
		String q="";
		q+="update b_ta_cpte set x_ultimo_nro = x_ultimo_nro + 1 ";
		q+="where codigo = '"+tc+"' ";
		return q;
	}
	
	
	public Object[][] getFVN(String tc,String idcomprobante){
		String q="";
		q+="select fecha_comprobante,cuenta,cuenta_descripcion,total_cpte,subtotal,idusuario,anulada,idcomprobante_origen ";
		q+="from b_fvn where tc like '"+tc+"' and idcomprobante like '"+idcomprobante+"' ";
		return this.getResults(q);
		
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

	public String getRemitoEncabezado(String idcomprobante){
		String q="";
		q=q+"select v.fecha,v.cuenta,v.nombre,v.domicilio,";
		q=q+"v.telefono,v.localidad,v.idprovincia,v.codigopostal,v.documentotipo,";
		q=q+"v.documentonumero,v.condicioniva,v.idcond_cpra_vta,v.claseprecio,";
		q=q+"v.importe,v.importe_s_iva,v.idvendedor, ";
		q=q+" t.descripcion,tip.descripcion,vv.nombre ";
		q=q+" from v_mv_cpte v left outer join ";
		q=q+" v_ta_cpra_vta t ";
		q=q+" on v.idcond_cpra_vta = t.idcond_cpra_vta ";
		q=q+" left outer join ta_tipodocumento tip ";
		q=q+" on v.condicioniva=tip.codigo left outer join ";
		q=q+" v_ta_vendedores vv on v.idvendedor=vv.idvendedor ";
		q=q+" where v.tc like 'rm' and v.idcomprobante like '"+idcomprobante+"' ";
		q=q+"";
		
		return q; 
	}
	public Object[][] getRemito(String id){
		return this.getResults(this.getRemitoEncabezado(id));
	}
	public Object[][] getRemitoItems(String id){
		return this.getResults(this.getRemitoItemsQuery(id));
	}
	public String getRemitoItemsQuery(String id){
	
		String q="select idarticulo,descripcion,cantidad,0.0,importe,0.0,total  ";
		q=q+" from v_mv_cpteinsumos i ";
		q=q+" where tc like 'RM' and idcomprobante like '"+id+"'";
		q=q+" order by id ";
		return q;
	}
	
	public String getFvnFecha(String tc,String idcomprobante){
		String q="select convert(varchar(10),fecha_carga,105) from b_fvn where tc like '"+tc+"' and idcomprobante like '"+idcomprobante+"' ";
		Object[][] results=this.getResults(q);
		String fecha="";
		if (results!=null){
			if (results.length>0){
				fecha=results[0][0].toString();
			}
		}
		return fecha;
	}
	
	public String getRemitoFecha(String tc,String idcomprobante){
		String q="select convert(varchar(10),fecha,105) from v_mv_cpte where tc like '"+tc+"' and idcomprobante like '"+idcomprobante+"' ";
		Object[][] results=this.getResults(q);
		String fecha="";
		if (results!=null){
			if (results.length>0){
				fecha=results[0][0].toString();
			}
		}
		return fecha;
	}

	
	public boolean fvn_anulada(String tc,String idcomprobante){
		boolean anulada=false;
		String q="select anulada from b_fvn where tc like '"+tc+"' and idcomprobante like '"+idcomprobante+"' ";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				anulada=results[0][0].toString().compareTo("1")==0;
			}else{
				anulada=false;
			}
		}
		return anulada;
	}
	
	public boolean fvn_conciliada(String tc,String idcomprobante){
		boolean conciliada=true;
		String q="select * from b_aplicacion where origen_tc like '"+tc+"' and origen_idcomprobante like '"+idcomprobante+"' and anulado=0";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				conciliada=true;
			}else{
				conciliada=false;
			}
		}
		return conciliada;
	}
	public String getAnularFVN(String tc,String idcomprobante){
		String q="";
		q+="update b_fvn set anulada=1 where tc like '"+tc+"' and idcomprobante like '"+idcomprobante+"'";
		return q;
	}
	
	/*public String getAnularFVNItems(String tc,String idcomprobante){
		String q="";
		q+="update b_fvn_item set anulada=1 where tc like '"+tc+"' and idcomprobante like '"+idcomprobante+"'";
		return q;
	}*/
	
	public String getAnularFVNStock(String tc,String idcomprobante){
		String q="";
		q+="delete from v_mv_stock where tc like '"+tc+"' and idcomprobante like '"+idcomprobante+"'";
		return q;
	}
	
	public String getAnularFVNAsiento(String tc,String idcomprobante){
		String q="";
		q+="update b_mv_asientos set anulado=1 where tc like '"+tc+"' and idcomprobante like '"+idcomprobante+"' ";
		return q;
	}
	
	public Object[][] getFVNItems(String tc,String id){
		return this.getResults(this.getFVNItemsQuery(tc,id));
	}
	
	public String getFVNItemsQuery(String tc,String id){
		String q="select idarticulo,descripcion,cantidad,0.0,precio,0.0,total  ";
		q=q+" from b_fvn_item i ";
		q=q+" where tc like '"+tc+"' and idcomprobante like '"+id+"'";
		q=q+" order by sec ";
		return q;
	}

	
	public Object[][] getClientInformation(String idcliente){
		
		String q="";
		q=q+"select M.codigo,M.descripcion ";
		q=q+"FROM MA_CUENTAS M ";
		q=q+" where M.codigo like '"+idcliente+"' and M.titulo = 0";
		Object[][] results=connection_handler.getDefaultConnector().getResults(q);
		return results;
	}
	
	public Object[][] getAnticiposDisponibles(String idcliente){
		String q="";
		q=q+" select b.fecha,b.tc,b.idcomprobante,b.anticipo,'' ";
		q=q+" from b_cbs b left outer join b_aplicacion a ";
		q=q+" on b.tc=a.origen_tc ";
		q=q+" and b.idcomprobante=a.origen_idcomprobante and a.anulado=0 ";
		q=q+" where (a.origen_tc is null or a.anulado =1)";
		q=q+" and b.anticipo > 0 and b.anulada=0 ";
		q=q+" and b.cuenta like '"+idcliente+"'";
		q=q+" group by b.fecha,b.tc,b.idcomprobante,b.anticipo ";
		System.out.println("anticipo disponible> "+q);
		Object[][] results=connection_handler.getDefaultConnector().getResults(q);
		return results;
	}
	
	public String getPrimeraRelacion(String idcomprobante){
		String aux="";
		String q="";
		q+="select b.tc,b.idcomprobante ";
		q+="from b_aplicacion b left outer join b_cbs c ";
		q+="on b.origen_tc=c.tc and b.origen_idcomprobante=c.idcomprobante ";
		q+="where b.origen_tc like '"+this.TC+"' ";
		q+="and b.origen_idcomprobante like '"+idcomprobante+"' ";
		q+="and c.anulada=0 ";
		Object[][] results=connection_handler.getDefaultConnector().getResults(q);
		System.out.println(q);
		if (results!=null){
			if (results.length>0){
				aux=results[0][0].toString()+"-"+results[0][1].toString();
			}
		}
		return aux;
	}
	
	public boolean PagoRelacionado(String idcomprobante){
		boolean ok=false;
		String q="";
		q+="select b.tc,b.idcomprobante ";
		q+="from b_aplicacion b left outer join b_cbs c ";
		q+="on b.origen_tc=c.tc and b.origen_idcomprobante=c.idcomprobante ";
		q+="where b.origen_tc like '"+this.TC+"' ";
		q+="and b.origen_idcomprobante like '"+idcomprobante+"' ";
		q+="and b.anulado=0 ";
		System.out.println(q);
		Object[][] results=connection_handler.getDefaultConnector().getResults(q);
		if (results!=null){
			if (results.length>0){
				ok=true;
			}
		}
		return ok;
	}
	
	public Object[][] getAnticiposUtilizados(String idcomprobante){
		String q="";
		q+="select c.fecha,c.tc,c.idcomprobante,c.anticipo,'' "; 
		q+="from b_aplicacion b ";
		q+="left outer join b_cbs c ";
		q+="on b.origen_tc=c.tc ";
		q+="and b.origen_idcomprobante=c.idcomprobante ";
		q+="where b.tc like '"+TC+"' ";
		q+="and b.idcomprobante like '"+idcomprobante+"' ";
		q+="and c.tc like '"+TC+"' ";

		System.out.println(q);
		Object[][] results=connection_handler.getDefaultConnector().getResults(q);
		return results;
	}
	
	public Object[][] getCreditos(String idcliente){
		String q="";
		q=q+" select b.fecha_comprobante,b.tc,b.idcomprobante, ";
		q=q+" (case when b.tc like 'NCN' then b.total_cpte else 0 end) as Importe,";
		q=q+" (case when b.tc like 'NCN' then 'Sin Aplicacion' else 'Pendiente de Pago' end) as Estado";
		q=q+" from b_fcn b ";
		q=q+" left outer join ";
		q=q+" b_aplicacion a ";
		q=q+" on b.tc=a.origen_tc and b.idcomprobante=a.origen_idcomprobante and a.anulado=0 ";
		q=q+" where a.tc is null and b.tc like 'NCN'";
		q=q+" and b.cuenta like '"+idcliente+"' ";
		q=q+" and b.anulada = 0 ";
		System.out.println(q);
		Object[][] results=connection_handler.getDefaultConnector().getResults(q);
		return results;
	}
	
	public Object[][] getSaldos(String idcliente){
		String q="select b.fecha_comprobante,b.tc,b.idcomprobante,b.total_cpte,'Pendiente'   ";
		q=q+"from b_fcn b ";
		q=q+"left outer join ";
		q=q+"b_aplicacion a on ";
		q=q+"b.tc=a.origen_tc ";
		q=q+" and b.idcomprobante=a.origen_idcomprobante and a.anulado=0 ";
		q=q+" where (b.tc like 'fcn'  ) ";
		q=q+" and b.anulada=0 ";
		q=q+" and b.cuenta like '"+idcliente+"'";
		q=q+" and a.tc is null ";	
		q=q+" group by b.fecha_comprobante,b.tc,b.idcomprobante,b.total_cpte,a.tc ";
		System.out.println(q);
		Object[][] results=connection_handler.getDefaultConnector().getResults(q);
		return results;
	}
	public boolean anulada(String idx){
		boolean ok=false;
		idx=idx.replace(" ", "");
		String q="select anulado from b_mv_asientos where tc like '"+TC+"' and idcomprobante like '"+idx+"'";
		Object[][] results=connection_handler.getDefaultConnector().getResults(q);
		if (results!=null){
			if (results.length>0){
				String result=(String)results[0][0];
				
				ok=result.compareTo("q")==0;
			}
		}
		return ok;
	}
	
	public boolean exist_pago(String idx){
		boolean ok=false;
		idx=idx.replace(" ", "");
		String q="select * from b_mv_asientos where tc like '"+TC+"' and idcomprobante like '"+idx+"'";
		System.out.println(q);
		Object[][] results=connection_handler.getDefaultConnector().getResults(q);
		if (results!=null){
			if (results.length>0){
				ok=true;
			}
		}
		return ok;
	}
	
	public boolean pago_anulada(String idx){
		boolean ok=false;
		idx=idx.replace(" ", "");
		String q="select * from b_mv_asientos where tc like '"+TC+"' and idcomprobante like '"+idx+"' and anulado=1 ";
		System.out.println(q);
		Object[][] results=connection_handler.getDefaultConnector().getResults(q);
		if (results!=null){
			if (results.length>0){
				ok=true;
			}
		}
		return ok;
	}
	
	public String checkcodeMedios(String code){
		String aux="";
		String q="";
		q="select codigoopcional,descripcion,codigo  from ma_cuentas ";
		//q="and  (codigo like '%0001' or codigo like '%0002')";
		q=q+"where mediodepago is not null ";
		q=q+" and  codigoopcional like '"+code+"'";
		q=q+" and  codigoopcional not like 'DO'";
		System.out.println(q);
		Object[][] results=connection_handler.getDefaultConnector().getResults(q);
		if (results!=null){
			if (results.length>0){
				if (results[0][0].toString().toUpperCase().compareTo(code.toUpperCase())==0){
					aux=results[0][1].toString();
				}
			}
		}
		return aux;
	}
	
	public String getBanco(String idbanco){
		String q="select descripcion from v_ta_bancos where ltrim(idbanco) like '"+idbanco+"' ";
		System.out.println(q);
		Object[][] results=connection_handler.getDefaultConnector().getResults(q);
		String val="";
		if (results!=null){
			if (results.length>0){
				val=results[0][0].toString();	
			}
		}
		return val;
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
	public boolean anular_PG(String tc,String idcomp){
		connection_handler.getDefaultConnector().clearBatch();
		String q="";//"update b_cbs set anulada=1 ";
		//q=q+"where tc like '"+tc+"' and idcomprobante like '"+idcomp+"'";
		connection_handler.getDefaultConnector().addBatch(q);
		q="update b_mv_asientos set anulado=1 ";
		q=q+"where tc like '"+tc+"' and idcomprobante like '"+idcomp+"'";
		connection_handler.getDefaultConnector().addBatch(q);
		//q="update b_aplicacion set anulado=1 ";
		//q=q+"where tc like '"+tc+"' and idcomprobante like '"+idcomp+"'";
		//sql.addBatchSQL(q);
		boolean error=this.connection_handler.getDefaultConnector().executeBatch();
		return error;
	}
	
	
	public Object[][] load_encabezado(String id){
		String q="";
		q+="select c.importe,c.cuenta,m.descripcion,c.detalle,c.fecha,c.idcajas ";
		q+="from b_mv_asientos c  ";
		q+="left outer join ma_cuentas m ";
		q+="on c.cuenta=m.codigo ";
		q+="where c.tc like '"+TC+"' and idcomprobante like '"+id+"' and c.debe_haber like 'D'";
		System.out.println(q);
		Object[][] results=connection_handler.getDefaultConnector().getResults(q);
		
		
		return results;
	}
	
	
	
	public Object[][] load_medios_de_pago(String id){
		
		String q=" ";
		q=q+"select a.cuenta,a.cuenta_descripcion,a.importe,a.cht_idbanco,a.cht_serie,a.cht_numero,a.cht_vencimiento,a.cht_importe,b.descripcion "; 
		q=q+"from b_mv_asientos a left outer join v_ta_bancos b on ltrim(rtrim(a.cht_idbanco)) = rtrim(ltrim(b.idbanco)) ";
		q=q+"where a.tc like '"+TC+"' and a.idcomprobante like '"+id+"' ";
		q=q+"and (a.cuenta like '111010001' or a.cuenta like '111010002' or a.cuenta like '43104' ) ";
		q=q+"order by a.secuencia ";
		Object[][] results=connection_handler.getDefaultConnector().getResults(q);
		return results;
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
	
	public Object[][] load_comprobantes_pagados(String id){
		
		String q="";
		q+="select f.fecha_carga,a.origen_tc,a.origen_idcomprobante,f.total_cpte "; 
		q+="from b_aplicacion a ";
		q+="left outer join  ";
		q+="b_fcn f on a.origen_tc=f.tc and a.origen_idcomprobante=f.idcomprobante ";
		q+="where a.tc like '"+TC+"' ";
		q+="and a.idcomprobante like '"+id+"' ";
		q+=" and a.origen_tc not like '"+TC+"' ";
		Object[][] results=connection_handler.getDefaultConnector().getResults(q);
		return results;
		         
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
	
	public String getInsertIntoCbs(String tc,String idcomp,String cuenta,String fecha,String importe,String anticipo){
		String q="insert into b_cbs (tc,idcomprobante,cuenta,fecha,importe,anticipo) values (";
		q=q+"'"+tc+"',";
		q=q+"'"+idcomp+"',";
		q=q+"'"+cuenta+"',";
		q=q+"'"+fecha+"',";
		q=q+""+importe+",";
		q=q+""+anticipo+")";
		return q;
	}
	
	
	public String getGenerarAplicacionPagoComprobante(String cuenta,String tc,String idcomp,String cb_tc,String cb_idcomp,String fecha){
		String q="";
		q=q+"insert b_aplicacion (cuenta,tc,idcomprobante,origen_tc,origen_idcomprobante,fecha) ";
		q=q+" values ('"+cuenta+"',";
		q=q+"'"+cb_tc+"',";
		q=q+"'"+cb_idcomp+"',";
		q=q+"'"+tc+"',";
		q=q+"'"+idcomp+"',";
		q=q+"'"+fecha+"')";
		return q;
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
	
	public String getUpdateQuery(String idcomprobante,String cuenta,String cuenta_descripcion,String subtotal,String total,String vendedor){
	
		String q="update B_FVN set ";
		q=q+" Cuenta='"+cuenta+"',";
		q=q+" Cuenta_Descripcion='"+cuenta_descripcion+"',";
		q=q+" ultima_modificacion=getDate(),";
		q=q+" subtotal="+subtotal+",";
		q=q+" total_cpte="+total+",";
		q=q+" idvendedor='"+vendedor+"', ";
		boolean arts=true;
		if (arts){
			q=q+" articulos=1 ";	
		}else {
			q=q+" articulos=0 ";
		}
		q=q+" where idcomprobante = '"+idcomprobante+"'";
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

	public String getDeleteRemitoQuery(String rmx,String cuenta){
		String q="update B_PDC set remito=''";
		q=q+" where remito = '"+rmx+"' and ( cliente like '"+cuenta+"' ";
		if (cuenta.compareTo("112010001")==0){
			q+=" or cliente like '*' ";
		}
		q+=") ";
		return q;
	}
	
	public String getDeleteRemitoPedidoQuery(String rmx){
		String q="update B_PDC_rms set eliminado=1";
		q=q+" where remito = '"+rmx+"' ";
		return q;
	}
	
	public String getMarcarAnuladoRemito(String remito){
		String q="";
		q=q+"update v_mv_cpte set anulada=1,finalizada=1 where tc like 'rm' and idcomprobante like '"+remito+"'";
		q=q+"";
		q=q+"";
		return q;
	}
	public String getMarcarDesanuladoRemito(String remito){
		String q="";
		q=q+"update v_mv_cpte set anulada=0,finalizada=0 where tc like 'rm' and idcomprobante like '"+remito+"'";
		q=q+"";
		q=q+"";
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
	
	public String getUpdateRM(){
		String q="";
		q+="update v_ta_cpte set x_ultimo_nro = x_ultimo_nro + 1 where codigo like 'RM'";
		return q;
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
	
	public Object[][] getData(String idarticulo){
		String q="";
		q+="select m.idarticulo,m.descripcion,m.descripabrev,isnull(sum(s.cantidadud),0),isnull(m.suspendidov,0),convert(varchar(10),m.actualizacion,105),m.cuentaproveedor,c.descripcion ";
		q+="from v_ma_articulos m ";
		q+="left outer join v_mv_stock s on m.idarticulo=s.idarticulo and s.anulado =0 "; 
		q+="left outer join ma_cuentas c on m.cuentaproveedor=c.codigo ";
		q+="where m.idarticulo like '"+idarticulo+"' ";
		q+="group by m.idarticulo,m.descripcion,m.descripabrev,isnull(m.suspendidov,0),convert(varchar(10),m.actualizacion,105),m.cuentaproveedor,c.descripcion ";
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
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
	public String getIdRemito(String idpedido){
		String q="";
		q+="select remito from b_pdc_rms where idpedido like '"+idpedido+"' and eliminado=0 ";
		q+="";
		String idremito="";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				idremito=(String) results[0][0];
			}
		}
		return idremito;
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

	public Object[][] getVendedor(String iduser){
		String q="";
		q+="select b.idvendedor,v.nombre ";
		q+="from b_users b left outer join v_ta_vendedores v on ltrim(b.idvendedor)=ltrim(v.idvendedor) ";
		q+="where b.iduser like '"+iduser+"'";
		return this.getResults(q);
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

}
