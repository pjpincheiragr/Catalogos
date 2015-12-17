package aplicacion.proveedor.corrector.logic;

import aplicacion.herramientas.java.*;
import aplicacion.modelo.logic.Data;

public class _Data extends Data {

	private String getSaldosQuery(String codigo){
		String q="";
		q+="select ";
		q+="convert(varchar(10),asiento.fecha,105) as fecha, ";
		q+="asiento.tc,";
		q+="asiento.idcomprobante,";
		q+="(case when asiento.debe_haber like 'D' then asiento.importe else 0 end) as Debe,";
		q+="(case when asiento.debe_haber like 'H' then asiento.importe else 0 end) as Haber,";
		q+="asiento.anulado, ";
		q+="(case when asiento.tc like 'PG' then cbs.anulada else fcn.anulada end) as'FCN/PG ANULADA', ";
		q+="aplicacion.tc,";
		q+="aplicacion.idcomprobante,";
		q+="aplicacion.anulado ";
		q+="from b_mv_asientos asiento ";
		q+="left outer join b_fcn fcn ";
		q+="on asiento.tc=fcn.tc and asiento.idcomprobante=fcn.idcomprobante ";
		q+="left outer join b_cbs cbs ";
		q+="on asiento.tc=cbs.tc and asiento.idcomprobante=cbs.idcomprobante ";
		q+="left outer join b_aplicacion aplicacion ";
		q+="on asiento.tc=aplicacion.origen_tc and asiento.idcomprobante=aplicacion.origen_idcomprobante ";
		q+="where asiento.cuenta like '"+codigo+"' ";
		q+="order by asiento.fecha,asiento.tc,asiento.idcomprobante";
		return q;
	}
	
	public String getQueryAnticipo(String tc,String idcomprobante){
		String q="";
		q+="select anticipo ";
		q+="from b_cbs ";
		q+="where tc like '"+tc+"' and idcomprobante like '"+idcomprobante+"' ";
		return q;
	}
	
	public String getQueryAnticipos(String cuenta){
		String q="";
		q+=" select cbs.fecha,cbs.tc,cbs.idcomprobante,cbs.anticipo ";
		q+=" from b_cbs cbs left outer join b_mv_asientos asiento ";
		q+=" on cbs.tc = asiento.tc ";
		q+=" and cbs.idcomprobante=asiento.idcomprobante ";
		q+=" and cbs.cuenta=asiento.cuenta ";
		q+=" where cbs.cuenta like '"+cuenta+"' ";
		q+=" and asiento.tc is null and cbs.anulada=0 ";
		q+=" order by cbs.tc,cbs.idcomprobante ";
		return q;
	}
	public Object[][] getAnticipos(String cuenta){
		String q=this.getQueryAnticipos(cuenta);
		System.out.println(q);
		Object[][] results=this.getResults(q);
		return results;
	}
	public Object[][] getAnticipo(String tc,String idcomprobante){
		String q=this.getQueryAnticipo(tc, idcomprobante);
		System.out.println(q);
		Object[][] results=this.getResults(q);
		return results;
	}
	
	public Object[][] getSaldos(String codigo){
		
		String q=this.getSaldosQuery(codigo);
		System.out.println(q);
		Object[][] results=this.getResults(q);
		return results;
	}
}
