package aplicacion.contabilidad.modificacion.logic;

import java.util.ArrayList;
import java.util.List;

import aplicacion.modelo.logic.Data;

public class _Data extends Data {

	public String getCpteQuery(String tc,String sucursal,String numero,String letra){
		String q="";
		q+="select m.cuenta,c.descripcion,m.importe,convert(varchar(10),m.fecha,105) ";
		q+="from mv_asientos m left outer join ma_cuentas c ";
		q+="on m.cuenta=c.codigo ";
		q+="where m.tc like '"+tc+"' and m.numero like '"+numero+"' ";
		q+="and m.sucursal like '"+sucursal+"' and m.letra like '"+letra+"' ";
		q+="and m.cuenta like '11201%' ";
		System.out.println(q);
		return q;
	}
	public List<String> getInstruccionesCambioFecha(String tc,String sucursal,String numero,String letra,String fecha){
		List<String> instrucciones=new ArrayList<String>();
		String q="";
		q+="declare @tc varchar(4) ";
		q+="declare @sucursal varchar(4) ";
		q+="declare @numero varchar(8) ";
		q+="declare @letra varchar(1) ";
		q+="declare @fecha varchar(10) ";
		q+="declare @asiento_nuevo int ";
		q+="declare @mes_operativo int "; 
		q+="declare @numero_asiento int ";
		q+="set @fecha='"+fecha+"' ";
		q+="set @tc='"+tc+"' ";
		q+="set @numero='"+numero+"' ";
		q+="set @sucursal='"+sucursal+"' ";
		q+="set @letra='"+letra+"' ";
		instrucciones.add(q);
		q="select top 1 @asiento_nuevo=([numero asiento])+1 ";
		q+="from mv_asientos where mes_operativo = month(@fecha) "; 
		q+="order by periodo desc,mes_operativo desc,[numero asiento] desc ";
		q+="";
		instrucciones.add(q);
		q="update mv_asientos set ";
		q+="mes_operativo=month(@fecha),";
		q+="[numero asiento]=@asiento_nuevo, ";
		q+="fecha=@fecha, ";
		q+="fechasubdiario=@fecha ";
		q+="where tc = @tc and numero =@numero and letra= @letra and sucursal=@sucursal ";
		instrucciones.add(q);
		q="update v_mv_cpte set fecha=@fecha ";
		q+="where tc = @tc and idcomprobante like @sucursal+@numero+@letra ";
		instrucciones.add(q);

		return instrucciones;
	}
	public Object[][] getCpte(String tc,String sucursal,String numero,String letra){
		String q=this.getCpteQuery(tc, sucursal, numero, letra);
		Object[][] results=this.getResults(q);
		return results;
	}
	
	public boolean existe(String tc,String sucursal,String numero,String letra){
		boolean existe=false;
		Object[][] results=this.getCpte(tc, sucursal, numero, letra);
		if (results!=null){
			if (results.length>0){
				existe=true;
			}
		}
		return existe;
	}
}
