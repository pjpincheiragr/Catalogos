package aplicacion.cliente.cobranza.logic;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.JOptionPane;

import aplicacion.cliente.cobranza.interfaces._Interface;
import aplicacion.modelo.logic.Data;
public class _Data extends Data{
	private String tc="CBCT";
	public Object[][] getDeudas(String idcuenta,String desde,String hasta){
		String q="";
		q+="select convert(varchar(10),b.fecha,105),b.tc,b.idcomprobante,b.importe ";
		q+="from b_mv_asientos b "; 
		q+="where b.cuenta like '"+idcuenta+"' ";
		q+="and b.fecha between '"+desde+"' and '"+hasta+"' ";
		q+="and b.debe_haber like 'D' and b.anulado=0 and tc like 'EGR' ";
		System.out.println("deuda> "+q);
		Object[][] results=this.getResults(q);
		return results;
	}
	
	public boolean getConciliado(String tc,String idcomprobante){
		boolean b=false;
		String q="";
		q+="select * from b_aplicacion ";
		q+="where (origen_tc like '"+tc+"' and origen_idcomprobante like '"+idcomprobante+"') and anulado =0";
		//q+="or (tc like '"+tc+"' and idcomprobante like '"+idcomprobante+"'))  ";
		System.out.println("conciliado> "+q);
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				b=true;
			}
		}
		return b;
	}
	
	public Object[][] getAFavor(String idcuenta,String desde,String hasta){
		String q="";
		q+="select convert(varchar(10),b.fecha,105),b.tc,b.idcomprobante,b.importe ";
		q+="from b_mv_asientos b "; 
		q+="where b.cuenta like '"+idcuenta+"' ";
		q+="and b.fecha between '"+desde+"' and '"+hasta+"' ";
		q+="and b.debe_haber like 'H' and b.anulado=0 and tc like 'IGR' ";
		System.out.println("a favor> "+q);
		Object[][] results=this.getResults(q);
		return results;
	}
	
	public Object[][] getFVNPendientes(String idcuenta,String desde,String hasta){
		String q="";
		q+=" SELECT CONVERT(VARCHAR(10),A.FECHA,105), ";
		q+=" A.TC,A.IDCOMPROBANTE,(case when A.debe_haber like 'D' then A.IMPORTE else -A.importe end) ";
		q+=" FROM  ";
		q+=" B_MV_ASIENTOS A ";
		q+=" LEFT OUTER JOIN B_FVN FVN ";
		q+=" ON A.TC=FVN.TC AND A.IDCOMPROBANTE=FVN.IDCOMPROBANTE ";
		q+=" WHERE A.CUENTA LIKE '"+idcuenta+"' ";
		q+=" AND (A.TC LIKE 'FVN') ";
		q+=" AND A.ANULADO=0 ";
		q+=" AND A.FECHA between '"+desde+"' AND '"+hasta+"' ";
		q+=" AND FVN.ANULADA=0 ";
		q+=" and A.IDCOMPROBANTE NOT IN (";
		q+="select origen_idcomprobante from b_aplicacion ";
		q+="where cuenta like '"+idcuenta+"' and origen_tc like 'FVN' and anulado=0 ";
		q+=" )";
		q+=" order by A.FECHA,A.TC,A.IDCOMPROBANTE,A.IMPORTE ";
		System.out.println("FVN PENDIENTES> "+q);
		Object[][] results=this.getResults(q);
		return results;
	}
	
	
	public Object[][] getEGRPendientes(String idcuenta,String desde,String hasta){
		String q="";
		q+=" SELECT CONVERT(VARCHAR(10),A.FECHA,105), ";
		q+=" A.TC,A.IDCOMPROBANTE,A.IMPORTE ";
		q+=" FROM  ";
		q+=" B_MV_ASIENTOS A ";
		q+=" WHERE A.CUENTA LIKE '"+idcuenta+"' ";
		q+=" AND (A.TC LIKE 'EGR') ";
		q+=" AND A.ANULADO=0 ";
		q+=" AND A.FECHA between '"+desde+"' AND '"+hasta+"' ";
		q+=" and A.IDCOMPROBANTE NOT IN (";
		q+="select origen_idcomprobante from b_aplicacion ";
		q+="where cuenta like '"+idcuenta+"' and origen_tc like 'EGR' and anulado=0 ";
		q+=" )";
		q+=" order by A.FECHA,A.TC,A.IDCOMPROBANTE,A.IMPORTE ";
		System.out.println("EGR PENDIENTES> "+q);
		Object[][] results=this.getResults(q);
		return results;
	}
	
	public Object[][] getNCNPendientes(String idcuenta,String desde,String hasta){
		String q="";
		q+=" SELECT CONVERT(VARCHAR(10),A.FECHA,105), ";
		q+=" A.TC,A.IDCOMPROBANTE,A.IMPORTE ";
		q+=" FROM  ";
		q+=" B_MV_ASIENTOS A ";
		q+=" LEFT OUTER JOIN B_FVN FVN ";
		q+=" ON A.TC=FVN.TC AND A.IDCOMPROBANTE=FVN.IDCOMPROBANTE ";
		q+=" WHERE A.CUENTA LIKE '"+idcuenta+"' ";
		q+=" AND A.FECHA between '"+desde+"' AND '"+hasta+"' ";
		q+=" AND (A.TC LIKE 'NCN') ";
		q+=" AND A.ANULADO=0 ";
		q+=" AND FVN.ANULADA=0 ";
		q+=" and A.IDCOMPROBANTE NOT IN (";
		q+="select origen_idcomprobante from b_aplicacion ";
		q+="where cuenta like '"+idcuenta+"' and origen_tc like 'NCN' and anulado=0 ";
		q+=" )";
		q+=" order by A.FECHA,A.TC,A.IDCOMPROBANTE,A.IMPORTE ";
		System.out.println("NCN PENDIENTES> "+q);
		Object[][] results=this.getResults(q);
		return results;
	}
	
	public Object[][] getAnticiposPendientes(String idcuenta,String desde,String hasta){
		String q="";
		q+="SELECT CONVERT(VARCHAR(10),cbs.FECHA,105),  cbs.TC,cbs.IDCOMPROBANTE,cbs.anticipo ";
		q+="FROM   B_CBS CBS   LEFT OUTER JOIN B_MV_ASIENTOS A ";
		q+="ON CBS.TC=A.TC AND A.IDCOMPROBANTE=CBS.IDCOMPROBANTE ";  
		q+="WHERE CBS.CUENTA LIKE '"+idcuenta+"'  AND CBS.TC LIKE 'CBcT' "; 
		q+="AND CBS.ANULADA=0   ";
		q+="AND CBS.FECHA between '"+desde+"' and '"+hasta+"'  ";
		q+="and cbs.IDCOMPROBANTE NOT IN ( ";
		q+="select origen_idcomprobante from b_aplicacion ";
		q+="where cuenta like '"+idcuenta+"' and origen_tc like 'CBCT' and anulado=0) ";
		q+="group  by CONVERT(VARCHAR(10),cbs.FECHA,105),  cbs.TC,cbs.IDCOMPROBANTE,cbs.anticipo ";
		System.out.println("ANTICIPOS PENDIENTES> "+q);
		Object[][] results=this.getResults(q); 
		return results;
	}
	
	public Object[][] getCBCTPendientes(String idcuenta,String desde,String hasta){
		String q="";
		q+="select convert(varchar(10),a.fecha,105),a.tc,a.idcomprobante,";
		q+="max(case when a.tc like 'CBCT' then cob.anticipo else 0 end) as Anticipo ";
		q+="from b_mv_asientos a ";
		q+="left outer join b_cbs cob ";
		q+="on a.tc=cob.tc and a.idcomprobante=cob.idcomprobante ";
		q+="left outer join b_aplicacion ap ";
		q+="on a.tc=ap.origen_tc and a.idcomprobante=ap.origen_idcomprobante ";
		q+="where a.cuenta like '"+idcuenta+"'  ";
		q+="and a.anulado=0  ";
		q+=" AND a.FECHA between '"+desde+"' AND '"+hasta+"' ";
		q+="and a.tc like 'CBCT' ";
		q+="group by a.fecha,a.tc,a.idcomprobante,a.debe_haber,a.importe ";
		q+="having max(case when ap.tc is null then 1 else (ap.anulado) end)=1  ";
		q+="order by a.fecha,a.tc,a.idcomprobante,a.debe_haber,a.importe ";
		System.out.println("ANTICIPOS PENDIENTES> "+q);
		Object[][] results=this.getResults(q); 
		return results;
	}
	
	private int getProximoTC(String tc){
		int c=0;
		String[] parameters=new String[]{tc};
		String q=this.getQuery("_proximo_cpte", parameters);
		Object[][] aux=getResults(q);
		try{
			c=new Integer(aux[0][0].toString());
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return c;
	}
	
	public String getUpdateCBCT(){
		String aux="";
		int num=this.getProximoCBCT()+1;
		String[] parameters=new String[]{""+num};
		aux=this.getQuery("update_cpte", parameters);
		return aux;
	}
	
	public Object[][] getClientInformation(String idcliente){
		String[] parameters=new String[]{idcliente};
		String q=this.getQuery("account_info", parameters);
		Object[][] results=getResults(q);
		return results;
	}
	
	public Object[][] getAnticiposDisponibles(String idcliente){
		String q="";
		String[] parameters=new String[]{idcliente};
		q=this.getQuery("anticipos_disponibles", parameters);
		Object[][] results=getResults(q);
		return results;
	}
	
	public Object[][] getNotasDeCreditosDisponibles(String idcliente){
		String q="";
		String[] parameters=new String[]{idcliente};
		q=this.getQuery("notas_de_credito_disponibles", parameters);
		Object[][] results=getResults(q);
		return results;
	}
	public Object[][] getNotasDeCreditoUtilizadas(String idcomprobante){
		String q="";
		String[] parameters=new String[]{idcomprobante};
		q=this.getQuery("notas_de_credito_utilizadas", parameters);
		Object[][] results=getResults(q);
		return results;
	}
	
	public Object[][] getAnticiposUtilizados(String idcomprobante){
		String q="";
		q+="select ";
		q+="(case when b.origen_tc like 'CBCT' then c.fecha else f.fecha_comprobante end) as fecha,";
		q+="b.origen_tc,b.origen_idcomprobante,";
		q+="(case when b.origen_tc like 'CBCT' then c.anticipo else f.subtotal end) as importe,'' "; 
		q+="from b_aplicacion b ";
		q+="left outer join b_cbs c ";
		q+="on b.origen_tc=c.tc ";
		q+="and b.origen_idcomprobante=c.idcomprobante ";
		q+="left outer join b_fvn f ";
		q+="on b.origen_tc=f.tc and b.origen_idcomprobante=f.idcomprobante ";
		q+="where b.tc like 'CBCT' ";
		q+="and b.idcomprobante like '"+idcomprobante+"' ";
		q+="and (b.origen_tc like 'CBCT' or b.origen_tc like 'NCN')";
		System.out.println("Anticipos utilizados> "+q);
		Object[][] results=connection_handler.getDefaultConnector().getResults(q);
		return results;
	}
	
	
	
	public Object[][] getSaldos(String idcliente){
		String q="";
		String[] parameters=new String[]{idcliente};
		q=this.getQuery("saldos", parameters);
		Object[][] results=getResults(q);
		return results;
	}
	
	public String[][] getFVNDetalles(String cuenta,String tc,String idcomprobante){
		
			String q="";
			String[] parameters=new String[]{
				cuenta,
				tc,
				idcomprobante
			};
			q=this.getQuery(_Interface._data_detalle_fvn, parameters);
			Object[][] results=this.getResults(q);
			String[][] aux=null;
			if (results!=null){
				if (results.length>0){
					aux=new String[results.length][results[0].length];
					for (int i=0;i<results.length;i++){
						for (int j=0;j<results[0].length;j++){
							aux[i][j]=(String) results[i][j];
							
						}
					}	
				}
				
			}
			
			return aux;
		
	}
	
	public boolean anulada(String idx){
		boolean ok=false;
		idx=idx.replace(" ", "");
		String q="";
		String[] parameters=new String[]{idx};
		q=this.getQuery("anulada", parameters);
		
		Object[][] results=getResults(q);
		if (results!=null){
			if (results.length>0){
				String result=(String)results[0][0];
				
				ok=result.compareTo("q")==0;
			}
		}
		return ok;
	}
	
	public boolean exist_cobranza(String idx){
		boolean ok=false;
		idx=idx.replace(" ", "");
		String q="";
		String[] parameters=new String[]{idx};
		q=this.getQuery("existe_cobranza", parameters);
		Object[][] results=getResults(q);
		if (results!=null){
			if (results.length>0){
				ok=true;
			}
		}
		return ok;
	}
	
	public boolean cobranza_anulada(String idx){
		boolean ok=false;
		idx=idx.replace(" ", "");
		String q="";
		String[] parameters=new String[]{idx};
		q=this.getQuery("anulada", parameters);
		Object[][] results=getResults(q);
		if (results!=null){
			if (results.length>0){
				String anulada=(String) results[0][0];
				int n=0;
				try {
					n=new Integer(anulada);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ok=n>0;
			}
		}
		return ok;
	}
	
	public String checkcodeMedios(String code){
		String aux="";
		String q="";
		String[] parameters=new String[]{code};
		q=this.getQuery("check_medios", parameters);
		
		Object[][] results=getResults(q);
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
		String q="";
		String[] parameters=new String[]{idbanco};
		q=this.getQuery("banco", parameters);
		
		Object[][] results=getResults(q);
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
		String q="";
		String[] parameters=new String[]{mes_operativo};
		q=this.getQuery("proximo_numero_asiento", parameters);
		
		Object[][] results=getResults(q);
		
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
	public boolean anular_CBCT(String tc,String idcomp){
		clearBatch();
		String q="";
		String[] parameters=new String[]{tc,idcomp};
		q=this.getQuery("anular_cbs", parameters);
		addBatch(q);
		parameters=new String[]{tc,idcomp};
		q=this.getQuery("anular_asiento", parameters);
		addBatch(q);
		parameters=new String[]{tc,idcomp};
		q=this.getQuery("anular_aplicacion", parameters);
		addBatch(q);
		boolean error=executeBatch();
		return error;
	}
	
	
	public Object[][] load_encabezado(String id){
		String q="";
		String[] parameters=new String[]{id};
		q=this.getQuery("load_encabezado", parameters);
		Object[][] results=getResults(q);
		
		return results;
	}
	
	
	
	public Object[][] load_medios_de_pago(String id){
		String q="";
		String[] parameters=new String[]{id};
		q=this.getQuery("load_medios_de_pago", parameters);
		Object[][] results=getResults(q);
		return results;
	}
	
	public String getProximoCBCTCorrecto(){
		String prox="";
		prox=this.getProximoCBCT_Ceros();
		return prox;
	}
	
	private int getProximoCBCT(){
		int c=0;
		c=this.getProximoTC("CBCT");
		return c;
	}
	
	
	
public Object[][] load_comprobantes_pagados(String id){
		
		String q="";
		q+="select convert(varchar(10),b.fecha,105),b.tc,b.idcomprobante,b.importe ";
		q+="from b_aplicacion a left outer join "; 
		q+="b_mv_asientos b ";
		q+="on a.origen_tc=b.tc ";
		q+="and a.origen_idcomprobante=b.idcomprobante  and b.cuenta = a.cuenta  ";
		q+="where a.tc like '"+tc+"' ";
		q+="and a.idcomprobante like '"+id+"' ";
		q+="and b.debe_haber like 'D' ";
		System.out.println(q);
		Object[][] results=connection_handler.getDefaultConnector().getResults(q);
		return results;
		         
	}
	private String getProximoCBCT_Ceros(){
		String c="";
		int i=this.getProximoCBCT();
		String aux=""+i;
		while (aux.length()<8){
			aux="0"+aux;
		}
		c=aux;
		return c;
	}
	
	public String getInsertIntoCbs(String tc,String idcomp,String cuenta,String fecha,String importe,String anticipo){
		String q="";
		String[] parameters=new String[]{tc,idcomp,cuenta,fecha,importe,anticipo};
		q=this.getQuery("insert_cbs", parameters);
		return q;
	}
	
	
	public String getGenerarAplicacionCobranzaComprobante(String cuenta,String tc,String idcomp,String cb_tc,String cb_idcomp,String fecha){
		String q="";
		String[] parameters=new String[]{cuenta, tc, idcomp, cb_tc, cb_idcomp, fecha};
		q=this.getQuery("insert_aplicacion", parameters);
		return q;
	}
	
	public boolean esCredito(String tc,String idcomprobante){
		String q="select debe_haber from b_mv_asientos where tc like '"+tc+"' and idcomprobante like '"+idcomprobante+"' and cuenta like '11201%' ";
		String debe="";
		boolean credito=false;
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				debe=(String)results[0][0];
				if (debe.compareTo("H")==0){
					credito=true;
				}
			}
		}
		return credito;
	}
}
