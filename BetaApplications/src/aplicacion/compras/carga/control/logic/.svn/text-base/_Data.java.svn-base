package aplicacion.compras.carga.control.logic;
import aplicacion.modelo.logic.Data;

public class _Data extends Data{
	public Object[][] getLibro(String desde,String hasta,String carga_desde,String carga_hasta,String idproveedor){
		String q="";
		q+="select convert(varchar(10),a.fecha,105), ";
		q+="c.tc, ";
		q+="c.idcomprobante, ";
		q+="mc.descripcion, ";
		q+="c.cuenta, ";
		q+="a.cabcuit, ";
		q+="a.liva_impnetograv, ";
		q+="a.liva_impnetongrav, ";
		q+="a.liva_impiva, ";
		q+="a.liva_impiva2, ";
		q+="a.liva_impiva3, ";
		q+="a.liva_ret_perc, ";
		q+="a.liva_ret_ibtos, ";
		q+="a.liva_ret_ganancias, ";
		q+="a.liva_total, ";
		q+="convert(varchar(10),d.fecha_carga,105), ";
		q+="convert(varchar(10),d.fecha_control,105) ";
		q+=" from  ";
		q+="c_mv_cpte c left outer join ";
		q+="ma_cuentas mc on c.cuenta=mc.codigo ";
		q+="left outer join ";
		q+="ma_cuentasadic ma on c.cuenta=ma.codigo ";
		q+="left outer join ";
		q+="b_cpte_digital d ";
		q+="on c.tc=d.tc and c.cuenta=d.cuenta and c.idcomprobante=d.idcomprobante ";
		q+="left outer join mv_asientos a ";
		q+="on c.tc=a.tc and c.sucursal=a.sucursal ";
		q+="and c.numero=a.numero and c.letra=a.letra ";
		q+="and c.cuenta=a.cuenta ";
		q+="where a.fecha>= '"+desde+"' and a.fecha <= '"+hasta+"' and d.fecha_carga >= '"+carga_desde+"' and d.fecha_carga<= '"+carga_hasta+"' and a.liva_tipo like '%COMPRAS%' ";
		q+="and a.cuenta like '%"+idproveedor+"%' ";
		q+="order by c.fecha,a.cuenta,c.idcomprobante ";
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
		
	}
	
public Object[][] getCpte(String cuenta,String tc,String idcomprobante){
		
		String[] parameters=new String[]{
				cuenta,
				tc,
				idcomprobante
				};
		String q=this.getQuery("cpte", parameters);
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
		
		
	}
	
	
public Object[][] getControles(String carga_desde,String carga_hasta,String cuenta,String desde,String hasta){
	
	String[] parameters=new String[]{
			carga_desde,
			carga_hasta,
			cuenta,
			desde,
			hasta
			};
	String q=this.getQuery("controles", parameters);
	System.out.println(">"+q);
	Object[][] results=getResults(q);
	return results;
	
	
}
	
	
	public Object[][] getProveedor(String idproveedor){
		String[] parameters=new String[]{
				idproveedor
				};
		String q=this.getQuery("proveedor", parameters);
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
		
		
	}
	public Object[][] getImpuestos(String idproveedor,String tc,String idcomprobante){
		String sucursal=idcomprobante.substring(0,4);
		String numero=idcomprobante.substring(4,12);
		String letra=idcomprobante.substring(12, 13);
		String[] parameters=new String[]{
				idproveedor,
				tc,
				sucursal,
				numero,
				letra
				};
		String q=this.getQuery("asiento_numero", parameters);
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		int mes_operativo=-1;
		int numero_asiento=-1;
		
		if (results!=null){
			if (results.length>0){
				try {
					mes_operativo=new Integer((String)results[0][0]);
					numero_asiento=new Integer((String)results[0][1]);
				}catch(Exception e){
					
				}
			}
		}
		if (mes_operativo >0 & numero_asiento>0){
			results=this.getAsiento(""+mes_operativo, ""+numero_asiento);
		}else{
			results=null;
		}
		return results;
	}
	
	private Object[][] getAsiento(String mes_operativo,String numero_asiento){
		String[] parameters=new String[]{
				mes_operativo,
				numero_asiento
				};
		String q=this.getQuery("asiento", parameters);
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
	}
	
	public Object[][] getImputacion(String idproveedor,String tc,String idcomprobante){
		String sucursal=idcomprobante.substring(0,4);
		String numero=idcomprobante.substring(4,12);
		String letra=idcomprobante.substring(12, 13);
		String[] parameters=new String[]{
				idproveedor,
				tc,
				sucursal,
				numero,
				letra
				};
		String q=this.getQuery("asiento_numero", parameters);
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		int mes_operativo=-1;
		int numero_asiento=-1;
		
		if (results!=null){
			if (results.length>0){
				try {
					mes_operativo=new Integer((String)results[0][0]);
					numero_asiento=new Integer((String)results[0][1]);
				}catch(Exception e){
					
				}
			}
		}
		if (mes_operativo >0 & numero_asiento>0){
			results=this.getImputacion(""+mes_operativo, ""+numero_asiento);
		}else{
			results=null;
		}
		return results;
	}
	private Object[][] getImputacion(String mes_operativo,String numero_asiento){
		
		String[] parameters=new String[]{
				mes_operativo,
				numero_asiento
				};
		String q=this.getQuery("imputacion", parameters);
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
	}
	
	public String getUpdate(String cuenta,String tc,String idcomprobante,String control){
		String q="";
		String[] parameters=new String[]{
				control,
				cuenta,
				tc,
				idcomprobante
				};
		q=this.getQuery("control", parameters);
		return q;
		
	}
public Object[][] getFotos(String cuenta,String tc,String idcomprobante){
		
		String[] parameters=new String[]{
				cuenta,
				tc,
				idcomprobante
				};
		String q=this.getQuery("fotos", parameters);
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
		
		
	}

}
