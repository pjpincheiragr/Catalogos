package aplicacion.proveedor.pago.logic;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.JOptionPane;

import aplicacion.modelo.logic.Data;
public class _Data extends Data{
	private String TC="PG";
	
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
	
	public boolean autoriza_caja(String iduser,String idcaja){
	boolean ok=false;
	String q="";
	q+="select caja.idcajas,caja.descripcion ";
	q+="from   b_ta_cajas caja left outer join b_users_caja users ";
	q+="on caja.idcajas =users.idcaja and users.iduser like '"+iduser+"' ";
	q+="where isnull(users.origen,0)=1 and users.idcaja like '"+idcaja+"' ";
	System.out.println(q);
	Object[][] results=connection_handler.getDefaultConnector().getResults(q);
	if (results!=null){
		if (results.length>0){
			ok=true;
		}
	}
	return ok;
	}
	
	public Object[][] get_cajas(String iduser){
		String q="";
		q+="select caja.idcajas,caja.descripcion ";
		q+="from   b_ta_cajas caja left outer join b_users_caja users ";
		q+="on caja.idcajas =users.idcaja and users.iduser like '"+iduser+"' ";
		q+="where isnull(users.origen,0)=1 ";
		System.out.println(q);
		Object[][] results=connection_handler.getDefaultConnector().getResults(q);
		return results;
	}
	
	public String getDetalleCaja(String idcajas){
		String detalle="";
		String q="";
		q+="select descripcion ";
		q+="from b_ta_cajas ";
		q+="where idcajas like '"+idcajas+"'";
		System.out.println(q);
		Object[][] results=connection_handler.getDefaultConnector().getResults(q);
		if (results!=null){
			try {
				detalle = (String) results[0][0];
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return detalle;
	}
	
	public String getUpdatePG(){
		String aux="";
		int num=this.getProximoPG()+1;
		aux="update b_ta_cpte set x_ultimo_nro="+num;
		aux=aux+" where codigo = '"+TC+"'";
		return aux;
	}
	
	public Object[][] getProveedorInformation(String idcliente){
		
		String q="";
		q+="select M.codigo,M.descripcion,";
		q+="isnull(a.telefono,''),";
		q+="isnull(a.fax,''),";
		q+="isnull(a.mail,''),";
		q+="isnull(a.contacto,''),";
		q+="isnull(a.localidad,''),";
		q+="isnull(a.observaciones,''),";
		q+="a.iva, ";
		q+="a.numero_documento, ";
		q+="a.idcond_cpra_vta, ";
		q+="t.descripcion, ";
		q+="isnull(a.calle,''),isnull(ltrim(a.numero),''),isnull(a.piso,''),ltrim(a.cpostal), ";
		q+="a.provincia,st.descripcion,tip.descripcion ";
		q+="FROM MA_CUENTAS M LEFT OUTER JOIN ";
		q+="MA_CUENTASADIC  a  ";
		q+="ON M.CODIGO = a.CODIGO left outer join v_ta_cpra_vta t ";
		q+=" on a.idcond_cpra_vta = t.idcond_cpra_vta ";
		q+=" left outer join ta_tipodocumento tip ";
		q+=" on a.iva=tip.codigo ";
		q+=" left outer join ta_estados st ";
		q+=" on a.provincia=st.codigo ";
		q+=" where M.codigo like '"+idcliente+"'";
		Object[][] results=connection_handler.getDefaultConnector().getResults(q);
		return results;
	}
	public Object[][] getAnticiposContablesDisponibles(String idcliente){
		String q="";
		q+=" select b.fecha,b.tc,b.idcomprobante,b.importe,'' ";
		q+=" from b_mv_asientos b left outer join b_aplicacion a ";
		q+=" on b.tc=a.origen_tc ";
		q+=" and b.idcomprobante=a.origen_idcomprobante and a.anulado=0  ";
		q+=" where (a.origen_tc is null or a.anulado =1)";
		q+=" and b.anulado=0 ";
		q+=" and b.cuenta like '"+idcliente+"' ";
		q+="and b.debe_haber like 'D' and b.tc like 'EGR' ";
		q+=" group by b.fecha,b.tc,b.idcomprobante,b.importe ";
		System.out.println("anticipo contable disponible> "+q);
		Object[][] results=connection_handler.getDefaultConnector().getResults(q);
		return results;
	}
	public Object[][] getAnticiposDisponibles(String idcliente){
		String q="";
		q+=" select b.fecha,b.tc,b.idcomprobante,b.anticipo,'' ";
		q+=" from b_cbs b left outer join b_aplicacion a ";
		q+=" on b.tc=a.origen_tc ";
		q+=" and b.idcomprobante=a.origen_idcomprobante and a.anulado=0 ";
		q+=" where (a.origen_tc is null or a.anulado =1)";
		q+=" and b.anticipo > 0 and b.anulada=0 ";
		q+=" and b.cuenta like '"+idcliente+"'";
		q+=" group by b.fecha,b.tc,b.idcomprobante,b.anticipo ";
		System.out.println("anticipo disponiblesss> "+q);
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
		q+="select ";
		q+="(case when b.origen_tc like 'PG' then c.fecha else f.fecha_comprobante end) as fecha,";
		q+="b.origen_tc,b.origen_idcomprobante,";
		q+="(case when b.origen_tc like 'PG' then c.anticipo else f.subtotal end) as importe,'' "; 
		q+="from b_aplicacion b ";
		q+="left outer join b_cbs c ";
		q+="on b.origen_tc=c.tc ";
		q+="and b.origen_idcomprobante=c.idcomprobante ";
		q+="left outer join b_fcn f ";
		q+="on b.origen_tc=f.tc and b.origen_idcomprobante=f.idcomprobante ";
		q+="where b.tc like 'PG' ";
		q+="and b.idcomprobante like '"+idcomprobante+"' ";
		q+="and (b.origen_tc like 'PG' )";
		System.out.println("Anticipos utilizados> "+q);
		Object[][] results=connection_handler.getDefaultConnector().getResults(q);
		return results;
	}
	
	public Object[][] getEgresosUtilizados(String idcomprobante){
		String q="";
		q+="select convert(varchar(10),asientos.fecha,105),asientos.tc,asientos.idcomprobante,asientos.importe "; 
		q+="from b_aplicacion a ";
		q+="left outer join b_mv_asientos asientos ";
		q+="on a.origen_tc=asientos.tc and a.origen_idcomprobante=asientos.idcomprobante  and asientos.debe_haber like 'D' ";
		q+="where a.tc like 'PG' ";
		q+="and a.idcomprobante like '"+idcomprobante+"' ";
		q+="and a.origen_tc like 'EGR' ";
		System.out.println("Egresos Utilizados> "+q);
		Object[][] results=this.getResults(q);
		return results;
	}
	
	public Object[][] getDeudas(String idcuenta,String desde,String hasta){
		String q="";
		q+="select convert(varchar(10),b.fecha,105),b.tc,b.idcomprobante,b.importe ";
		q+="from b_mv_asientos b "; 
		q+="where b.cuenta like '"+idcuenta+"' ";
		q+="and b.fecha between '"+desde+"' and '"+hasta+"' ";
		q+="and b.debe_haber like 'H' and b.anulado=0 and tc like 'IGR' ";
		System.out.println("deuda> "+q);
		Object[][] results=this.getResults(q);
		return results;
	}
	
	public Object[][] getAFavor(String idcuenta){
		String q="";
		q+="select convert(varchar(10),b.fecha,105),b.tc,b.idcomprobante,b.importe ";
		q+="from b_mv_asientos b "; 
		q+="where b.cuenta like '"+idcuenta+"' ";
		q+="and b.debe_haber like 'D' and b.anulado=0";
		System.out.println("a favor> "+q);
		Object[][] results=this.getResults(q);
		return results;
	}
	
	public boolean getConciliado(String tc,String idcomprobante){
		boolean b=false;
		String q="";
		q+="select * from b_aplicacion ";
		q+="where (origen_tc like '"+tc+"' and origen_idcomprobante like '"+idcomprobante+"') and anulado =0";
		
		System.out.println("conciliado> "+q);
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				b=true;
			}
		}
		return b;
	}
	
	public Object[][] getAnticiposContablesUtilizados(String idcomprobante){
		String q="";
		q+="select a.fecha,a.tc,a.idcomprobante,a.importe,'pagado' ";
		q+="from b_aplicacion b ";
		q+="left outer join b_mv_asientos a ";
		q+="on b.origen_tc=a.tc and b.origen_idcomprobante=a.idcomprobante ";
		q+="where b.tc like '"+TC+"' ";
		q+="and b.idcomprobante like '"+idcomprobante+"' ";
		q+="and a.tc like 'EGR' ";
		q+="and a.debe_haber like 'D' ";
		System.out.println("Anticipos utilizados> "+q);
		Object[][] results=connection_handler.getDefaultConnector().getResults(q);
		return results;
	}
	
	public Object[][] getCreditos(String idcliente){
		String q="";
		q+=" select b.fecha_comprobante,b.tc,b.idcomprobante, ";
		q+=" (case when b.tc like 'NCN' then b.total_cpte else 0 end) as Importe,";
		q+=" (case when b.tc like 'NCN' then 'Sin Aplicacion' else 'Pendiente de Pago' end) as Estado";
		q+=" from b_fcn b ";
		q+=" left outer join ";
		q+=" b_aplicacion a ";
		q+=" on b.tc=a.origen_tc and b.idcomprobante=a.origen_idcomprobante and a.anulado=0 ";
		q+=" where a.tc is null and b.tc like 'NCN'";
		q+=" and b.cuenta like '"+idcliente+"' ";
		q+=" and b.anulada = 0 ";
		System.out.println("getCreditos> "+q);
		Object[][] results=connection_handler.getDefaultConnector().getResults(q);
		return results;
	}
	
	public Object[][] getSaldos(String idcliente){
		String q="select b.fecha_comprobante,b.tc,b.idcomprobante,b.total_cpte,'Pendiente'   ";
		q+="from b_fcn b ";
		q+="left outer join ";
		q+="b_aplicacion a on ";
		q+="b.tc=a.origen_tc ";
		q+=" and b.idcomprobante=a.origen_idcomprobante and a.anulado=0 ";
		q+=" where (b.tc like 'fcn'  ) ";
		q+=" and b.anulada=0 ";
		q+=" and b.cuenta like '"+idcliente+"'";
		q+=" and a.tc is null ";	
		q+=" group by b.fecha_comprobante,b.tc,b.idcomprobante,b.total_cpte,a.tc ";
		System.out.println("Saldos>"+q);
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
		String q="select * from b_cbs where tc like '"+TC+"' and idcomprobante like '"+idx+"'";
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
		q+="where mediodepago is not null ";
		q+=" and  codigoopcional like '"+code+"'";
		q+=" and  codigoopcional not like 'DO'";
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
		q+=" where mes_operativo ="+mes_operativo+" ";
		q+=" order by numero_asiento desc ";
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
		clearBatch();
		String q="update b_cbs set anulada=1 ";
		q+="where tc like '"+tc+"' and idcomprobante like '"+idcomp+"'";
		addBatch(q);
		q="update b_mv_asientos set anulado=1 ";
		q+="where tc like '"+tc+"' and idcomprobante like '"+idcomp+"'";
		addBatch(q);
		q="update b_aplicacion set anulado=1 ";
		q+="where tc like '"+tc+"' and idcomprobante like '"+idcomp+"'";
		addBatch(q);
		boolean error=executeBatch();
		return error;
	}
	
	
	public Object[][] load_encabezado(String id){
		String q="";
		q+="select c.importe,c.anticipo,c.cuenta,m.descripcion,c.fecha ";
		q+="from b_cbs c  ";
		q+="left outer join ma_cuentas m ";
		q+="on c.cuenta=m.codigo ";
		q+="where c.idcomprobante like '"+id+"' and c.tc like '"+TC+"'";
		Object[][] results=connection_handler.getDefaultConnector().getResults(q);
		
		return results;
	}
	
	
	
	public Object[][] load_medios_de_pago(String id){
		
		String q=" ";
		q+="select a.cuenta,a.cuenta_descripcion,a.importe,a.cht_idbanco,a.cht_serie,a.cht_numero,a.cht_vencimiento,a.cht_importe,b.descripcion "; 
		q+="from b_mv_asientos a left outer join v_ta_bancos b on ltrim(rtrim(a.cht_idbanco)) = rtrim(ltrim(b.idbanco)) ";
		q+="where a.tc like '"+TC+"' and a.idcomprobante like '"+id+"' ";
		q+="and (a.cuenta like '111010001' or a.cuenta like '111010002' or a.cuenta like '43104' ) ";
		q+="order by a.secuencia ";
		Object[][] results=connection_handler.getDefaultConnector().getResults(q);
		return results;
	}
	
	public Object[][] getPago(String idpago){
		String q=" ";
		q+="select idcajas,detalle from b_mv_asientos where tc like '"+TC+"' and idcomprobante like '"+idpago+"'";
		q+="group by idcajas,detalle ";

		Object[][] results=this.getResults(q);
		
		return results;
	}
	
	public String getProximoPGCorrecto(){
		String prox="";
		prox=this.getProximoPG_Ceros();
		return prox;
	}
	
	private int getProximoPG(){
		int c=0;
		c=this.getProximoTC(""+TC+"");
		return c;
	}
	
	public Object[][] load_comprobantes_pagados(String id){
		
		String q="";
		q+="select convert(varchar(10),b.fecha,105),b.tc,b.idcomprobante,b.importe ";
		q+="from b_aplicacion a left outer join "; 
		q+="b_mv_asientos b ";
		q+="on a.origen_tc=b.tc ";
		q+="and a.origen_idcomprobante=b.idcomprobante  and b.cuenta = a.cuenta  ";
		q+="where a.tc like '"+TC+"' ";
		q+="and a.idcomprobante like '"+id+"' ";
		q+="and b.debe_haber like 'h' ";
		Object[][] results=connection_handler.getDefaultConnector().getResults(q);
		return results;
		         
	}
	
	public Object[][] load_creditos_utilizados(String id){
		
		String q="";
		q+="select convert(varchar(10),b.fecha,105),b.tc,b.idcomprobante,b.importe ";
		q+="from b_aplicacion a left outer join "; 
		q+="b_mv_asientos b ";
		q+="on a.origen_tc=b.tc ";
		q+="and a.origen_idcomprobante=b.idcomprobante  and b.cuenta = a.cuenta  ";
		q+="where a.tc like '"+TC+"' ";
		q+="and a.idcomprobante like '"+id+"' ";
		q+="and b.debe_haber like 'd' ";
		Object[][] results=connection_handler.getDefaultConnector().getResults(q);
		return results;
		         
	}
	
	private String getProximoPG_Ceros(){
		String c="";
		int i=this.getProximoPG();
		String aux=""+i;
		while (aux.length()<8){
			aux="0"+aux;
		}
		c=aux;
		return c;
	}
	
	public String getInsertIntoCbs(String tc,String idcomp,String cuenta,String fecha,String importe,String anticipo){
		String q="insert into b_cbs (tc,idcomprobante,cuenta,fecha,importe,anticipo) values (";
		q+="'"+tc+"',";
		q+="'"+idcomp+"',";
		q+="'"+cuenta+"',";
		q+="'"+fecha+"',";
		q+=""+importe+",";
		q+=""+anticipo+")";
		return q;
	}
	
	
	public String getGenerarAplicacionPagoComprobante(String cuenta,String tc,String idcomp,String cb_tc,String cb_idcomp,String fecha){
		String q="";
		q+="insert b_aplicacion (cuenta,tc,idcomprobante,origen_tc,origen_idcomprobante,fecha) ";
		q+=" values ('"+cuenta+"',";
		q+="'"+cb_tc+"',";
		q+="'"+cb_idcomp+"',";
		q+="'"+tc+"',";
		q+="'"+idcomp+"',";
		q+="'"+fecha+"')";
		return q;
	}
	
	public Object[][] getFCNPendientes(String idcuenta,String desde,String hasta){
		String q="";
		q+=" SELECT CONVERT(VARCHAR(10),A.FECHA,105), ";
		q+=" A.TC,A.IDCOMPROBANTE,A.IMPORTE ";
		q+=" FROM  ";
		q+=" B_MV_ASIENTOS A ";
		q+=" LEFT OUTER JOIN B_FCN FCN ";
		q+=" ON A.TC=FCN.TC AND A.IDCOMPROBANTE=FCN.IDCOMPROBANTE ";
		q+=" WHERE A.CUENTA LIKE '"+idcuenta+"' ";
		q+=" AND (A.TC LIKE 'FCN') ";
		q+=" AND A.ANULADO=0 ";
		q+=" AND A.FECHA between '"+desde+"' AND '"+hasta+"' ";
		q+=" AND FCN.ANULADA=0 ";
		q+=" and A.IDCOMPROBANTE NOT IN (";
		q+="select origen_idcomprobante from b_aplicacion ";
		q+="where cuenta like '"+idcuenta+"' and origen_tc like 'FCN' and anulado=0 ";
		q+=" )";
		q+=" order by A.FECHA,A.TC,A.IDCOMPROBANTE,A.IMPORTE ";
		System.out.println("FCN PENDIENTES> "+q);
		Object[][] results=this.getResults(q);
		return results;
	}
	
	
	public Object[][] getIGRPendientes(String idcuenta,String desde,String hasta){
		String q="";
		q+=" SELECT CONVERT(VARCHAR(10),A.FECHA,105), ";
		q+=" A.TC,A.IDCOMPROBANTE,A.IMPORTE ";
		q+=" FROM  ";
		q+=" B_MV_ASIENTOS A ";
		q+=" WHERE A.CUENTA LIKE '"+idcuenta+"' ";
		q+=" AND (A.TC LIKE 'IGR') ";
		q+=" AND A.ANULADO=0 ";
		q+=" AND A.FECHA between '"+desde+"' AND '"+hasta+"' ";
		q+=" and A.IDCOMPROBANTE NOT IN (";
		q+="select origen_idcomprobante from b_aplicacion ";
		q+="where cuenta like '"+idcuenta+"' and origen_tc like 'IGR' and anulado=0 ";
		q+=" )";
		q+=" order by A.FECHA,A.TC,A.IDCOMPROBANTE,A.IMPORTE ";
		System.out.println("IGR PENDIENTES> "+q);
		Object[][] results=this.getResults(q);
		return results;
	}
	public Object[][] getAnticiposPendientes(String idcuenta,String desde,String hasta){
		String q="";
		q+="SELECT CONVERT(VARCHAR(10),cbs.FECHA,105),  cbs.TC,cbs.IDCOMPROBANTE,cbs.anticipo ";
		q+="FROM   B_CBS CBS   LEFT OUTER JOIN B_MV_ASIENTOS A ";
		q+="ON CBS.TC=A.TC AND A.IDCOMPROBANTE=CBS.IDCOMPROBANTE ";  
		q+="WHERE CBS.CUENTA LIKE '"+idcuenta+"'  AND CBS.TC LIKE 'PG' "; 
		q+="AND CBS.ANULADA=0   ";
		q+="AND CBS.FECHA between '"+desde+"' and '"+hasta+"'  ";
		q+="and cbs.IDCOMPROBANTE NOT IN ( ";
		q+="select origen_idcomprobante from b_aplicacion ";
		q+="where cuenta like '"+idcuenta+"' and origen_tc like 'PG' and anulado=0) ";
		q+="group  by CONVERT(VARCHAR(10),cbs.FECHA,105),  cbs.TC,cbs.IDCOMPROBANTE,cbs.anticipo ";
		System.out.println("ANTICIPOS PENDIENTES> "+q);
		Object[][] results=this.getResults(q); 
		return results;
	}
	public Object[][] getAFavor(String idcuenta,String desde,String hasta){
		String q="";
		q+="select convert(varchar(10),b.fecha,105),b.tc,b.idcomprobante,b.importe ";
		q+="from b_mv_asientos b "; 
		q+="where b.cuenta like '"+idcuenta+"' ";
		q+="and b.fecha between '"+desde+"' and '"+hasta+"' ";
		q+="and b.debe_haber like 'D' and b.anulado=0 and tc like 'EGR' ";
		System.out.println("a favor> "+q);
		Object[][] results=this.getResults(q);
		return results;
	}
	
}
