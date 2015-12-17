package aplicacion.proveedor.gestion.logic;

import aplicacion.herramientas.java.*;
import aplicacion.modelo.logic.Data;

public class _Data extends Data {

	private String getSaldosQuery(boolean saldo_distinto_de_cero){
		String q="";
		q+="";
		q+="select m.codigo,m.descripcion, ";
		q+="isnull(sum(case when a.debe_haber like 'D' then a.importe else 0 end),0) as debe, ";
		q+="isnull(sum(case when a.debe_haber like 'H' then a.importe else 0 end),0) as haber, ";
		q+="isnull(sum(case when a.debe_haber like 'D' then a.importe else -a.importe end),0) as saldo ";
		q+="from ma_cuentas m ";
		q+="left outer join b_mv_asientos a ";
		q+="on m.codigo=a.cuenta ";
		q+="where m.codigo like '21101%' and m.codigo not like '21101' ";
		q+="and isnull(a.anulado,1)=0 ";
		
		q+="group by m.codigo,m.descripcion ";
		if (saldo_distinto_de_cero){
			q+="having sum(case when a.debe_haber like 'D' then a.importe else -a.importe end)<>0 ";
		}
		q+="order by m.codigo ";
		return q;
	}
	
	public Object[][] getSaldos(boolean saldo_distinto_de_cero){
		
		String q=this.getSaldosQuery(saldo_distinto_de_cero);
		System.out.println(q);
		Object[][] results=this.getResults(q);
		return results;
	}
}
