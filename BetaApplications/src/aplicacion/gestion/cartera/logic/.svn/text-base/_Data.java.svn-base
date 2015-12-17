package aplicacion.gestion.cartera.logic;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.JOptionPane;

import aplicacion.modelo.logic.Data;

public class _Data extends Data{
	private String TC="EGR";
	
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
	
	
	public Object[][] get_cajas_origen(String iduser){
		String q="";
		q+="select caja.idcajas,caja.descripcion ";
		q+="from   b_ta_cajas caja left outer join b_users_caja users ";
		q+="on caja.idcajas =users.idcaja and users.iduser like '"+iduser+"' ";
		q+="where isnull(users.origen,0)=1 ";
		System.out.println(q);
		Object[][] results=connection_handler.getDefaultConnector().getResults(q);
		return results;
	}
	public Object[][] get_efectivo(String idcaja,String fecha_desde,String fecha_hasta){
		
		
		String q="";
		q=q+"SELECT  ";
		q=q+"sum(CASE WHEN A.DEBE_HABER LIKE 'D' THEN A.IMPORTE ELSE 0 END) AS DEBE, ";
		q=q+"sum(CASE WHEN A.DEBE_HABER LIKE 'H' THEN A.IMPORTE ELSE 0 END) AS HABER ";
		q=q+"FROM B_MV_ASIENTOS A ";
		q=q+"WHERE A.CUENTA LIKE '111010001' ";
		q=q+" and A.fecha between '"+fecha_desde+"' and '"+fecha_hasta+"' ";
		q=q+"AND A.IDCAJAS LIKE '"+idcaja+"' ";
		q=q+"AND A.ANULADO=0 ";
		System.out.println(q);
		Object[][] results=connection_handler.getDefaultConnector().getResults(q);
		return results;
	
}
	
	public Object[][] getCheques(String idcajas){
		String q="";
		q+="select ltrim(a.cht_idbanco), b.descripcion, ltrim(a.cht_serie), a.cht_numero,a.cht_importe, ";
		q+="CONVERT(varchar(10), a.cht_vencimiento, 105) as vencimiento,";
		q+="max(case when a.debe_haber like 'd' then CONVERT(varchar(10), a.fecha, 105) else null end) as fecha_entrada,";
		q+="max(case when a.debe_haber like 'h' then CONVERT(varchar(10), a.fecha, 105) else null end) as fecha_salida ";
		//q+=",sum(case when a.debe_haber like 'd' then 1 else 0 end) as entradas,";
		//q+="sum(case when a.debe_haber like 'h' then 1 else 0 end) as salidas ";
		q+="from b_mv_asientos a ";
		q+="left outer join v_ta_bancos b ";
		q+="on ltrim(a.cht_idbanco)=ltrim(b.idbanco) ";
		q+="where a.idcajas like '"+idcajas+"' ";
		q+="and a.cht_importe is not null ";
		q+="and a.anulado=0 ";
		q+="group by  ltrim(a.cht_idbanco),b.descripcion,ltrim(a.cht_serie),a.cht_numero,a.cht_importe,a.cht_vencimiento ";
		q+="having sum(case when a.debe_haber like 'd' then 1 else -1 end)>0 ";
		q+="order by a.cht_vencimiento ";
		q+="";
		System.out.println(q);
		Object[][] aux=connection_handler.getDefaultConnector().getResults(q);
		return aux;
		
	}
	
	public Object[][] get_cajas(){
		String q="";
		q+="SELECT idcajas,descripcion ";
		q+="FROM [dbo].[b_ta_cajas] ";
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
	
	public int getCheque(String idcajas,String idbanco,String serie,String numero){
		int c=0;
		String q="";
		q+="select sum(case when a.debe_haber like 'd' then 1 else -1 end) as disponible ";
		q+="from b_mv_asientos a ";
		q+="left outer join v_ta_bancos b ";
		q+="on ltrim(a.cht_idbanco)=ltrim(b.idbanco) ";
		q+="where a.idcajas like '"+idcajas+"' ";
		q+="and a.cht_importe is not null ";
		q+="and a.anulado=0 ";
		q+="and ltrim(a.cht_idbanco) = ltrim('"+idbanco+"') ";
		q+="and a.cht_serie like '"+serie+"' ";
		q+="and a.cht_numero like '"+numero+"' ";
		q+="group by  a.cht_idbanco,b.descripcion,a.cht_serie,a.cht_numero,a.cht_importe,a.cht_vencimiento ";
		System.out.println(q);
		Object[][] aux=connection_handler.getDefaultConnector().getResults(q);
		try{
			c=new Integer(aux[0][0].toString());
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return c;
	}
	
	public String getUpdatePG(){
		String aux="";
		int num=this.getProximoPG()+1;
		aux="update b_ta_cpte set x_ultimo_nro="+num;
		aux=aux+" where codigo = '"+TC+"'";
		return aux;
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
		connection_handler.getDefaultConnector().addBatch(q);
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
}
