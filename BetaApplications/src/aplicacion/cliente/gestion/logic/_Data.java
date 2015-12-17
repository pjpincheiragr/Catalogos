package aplicacion.cliente.gestion.logic;

import aplicacion.herramientas.java.*;
import aplicacion.modelo.logic.Data;

public class _Data extends Data {

	private String getConsumoAlfaQuery(boolean saldo_distinto_de_cero,String desde,String hasta){
		String q="";
		q+="";
		q+="select m.codigo,";
		q+="isnull(sum(case when (a.[debe-haber] like 'D' and a.fecha between '"+desde+"' and '"+hasta+"') then a.importe else 0 end),0) as CAlfa ";
		q+="from ma_cuentas m ";
		q+="left outer join mv_asientos a ";
		q+="on m.codigo=a.cuenta ";
		q+="where m.codigo like '11201%' and m.codigo not like '11201' ";
		q+="group by m.codigo ";
		q+="order by m.codigo ";
		return q;
	}
	
	private String getConsumoBetaQuery(boolean saldo_distinto_de_cero,String desde,String hasta){
		String q="";
		q+="";
		q+="select m.codigo,";
		q+="isnull(sum(case when (b.debe_haber like 'D' and b.fecha between '"+desde+"' and '"+hasta+"') then b.importe else 0 end),0) as CBeta ";
		q+="from ma_cuentas m ";
		q+="left outer join b_mv_asientos b ";
		q+="on m.codigo=b.cuenta ";
		q+="where m.codigo like '11201%' and m.codigo not like '11201' ";
		q+="and isnull(b.anulado,1)=0 ";
		q+="group by m.codigo ";
		q+="order by m.codigo ";
		return q;
	}
	
	private String getSaldoAlfaQuery(boolean saldo_distinto_de_cero){
		String q="";
		q+="";
		q+="select m.codigo,";
		q+="isnull(sum(case when a.[debe-haber] like 'D' then a.importe else -a.importe end),0) as CAlfa ";
		q+="from ma_cuentas m ";
		q+="left outer join mv_asientos a ";
		q+="on m.codigo=a.cuenta ";
		q+="where m.codigo like '11201%' and m.codigo not like '11201' ";
		q+="group by m.codigo ";
		q+="order by m.codigo ";
		return q;
	}
	private String getSaldoBetaQuery(boolean saldo_distinto_de_cero){
		String q="";
		q+="";
		q+="select m.codigo,";
		q+="isnull(sum(case when (b.debe_haber like 'D' ) then b.importe else -b.importe end),0) as CBeta ";
		q+="from ma_cuentas m ";
		q+="left outer join b_mv_asientos b ";
		q+="on m.codigo=b.cuenta ";
		q+="where m.codigo like '11201%' and m.codigo not like '11201' ";
		q+="and isnull(b.anulado,1)=0 ";
		q+="group by m.codigo ";
		q+="order by m.codigo ";
		return q;
	}
	public String getCategoriaId(String descripcion){
		String id="";
		String q="select idcategoria from v_ta_categoria where descripcion like '"+descripcion+"'";
		Object[][] results=this.getResults(q);
		if (results!=null){
			id=(String)results[0][0];
		}
		return id;
	}
	public String getUpdate(String cuenta,String id){
		String q="update ma_cuentasadic set idcategoria='"+id+"' where codigo like '"+cuenta+"' ";
		return q;
	}
	
	public String getUpdateLimite(String cuenta,double credito){
		String q="update ma_cuentasadic set limite_credito='"+credito+"' where codigo like '"+cuenta+"' ";
		return q;
	}
	public String getUpdateDescuento(String cuenta,double descuento){
		String q="update ma_cuentasadic set descuento='"+descuento+"' where codigo like '"+cuenta+"' ";
		return q;
	}
	
	public String[] getCategorias(){
		String q="select descripcion from v_ta_categoria order by descripcion ";
		Object[][] results=this.getResults(q);
		String[] categorias=new String[results.length];
		for (int i=0;i<results.length;i++){
			categorias[i]=results[i][0].toString();
		}
		return categorias;
	}
	
	private String getSaldosQuery(boolean saldo_distinto_de_cero){
		String q="";
		q+="";
		q+="select m.codigo,";
		q+="m.descripcion,";
		q+="isnull(t.descripcion,'') as categoria, ";
		q+="c.limite_credito, ";
		q+="c.descuento ";
		q+="from ma_cuentas m ";
		q+="left outer join ma_cuentasadic c ";;
		q+="on m.codigo=c.codigo ";
		q+="left outer join v_ta_categoria t ";;
		q+="on c.idcategoria=t.idcategoria ";
		q+="where m.codigo like '11201%' and m.codigo not like '11201' ";
		
		q+="group by m.codigo,m.descripcion,t.descripcion,";
		q+="c.limite_credito, ";
		q+="c.descuento ";
		q+="order by m.codigo ";
		return q;
	}
	
	public Object[][] getConsumoAlfa(boolean saldo_distinto_de_cero,String desde,String hasta){
		String q=this.getConsumoAlfaQuery(saldo_distinto_de_cero, desde, hasta);
		System.out.println(q);
		Object[][] results=this.getResults(q);
		return results;
	}
	
	
	public Object[][] getConsumoBeta(boolean saldo_distinto_de_cero,String desde,String hasta){
		String q=this.getConsumoBetaQuery(saldo_distinto_de_cero, desde, hasta);
		System.out.println(q);
		Object[][] results=this.getResults(q);
		return results;
	}
	public Object[][] getSaldoBeta(boolean saldo_distinto_de_cero){
		String q=this.getSaldoBetaQuery(saldo_distinto_de_cero);
		System.out.println(q);
		Object[][] results=this.getResults(q);
		return results;
	}
	public Object[][] getSaldoAlfa(boolean saldo_distinto_de_cero){
		String q=this.getSaldoAlfaQuery(saldo_distinto_de_cero);
		System.out.println(q);
		Object[][] results=this.getResults(q);
		return results;
	}
	public Object[][] getSaldos(boolean saldo_distinto_de_cero){
		String q=this.getSaldosQuery(saldo_distinto_de_cero);
		System.out.println(q);
		Object[][] results=this.getResults(q);
		return results;
	}
}
