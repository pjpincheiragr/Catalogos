package aplicacion.ventas.envio.logic;

import aplicacion.modelo.logic.Data;

public class _Data extends Data {
	public Object[][] getClientInformation(String idcliente){
		String q="select descripcion from ma_cuentas where codigo like '"+idcliente+"'"; 
		Object[][] results=getResults(q);
		return results;
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
}
