package aplicacion.cliente.reporte.logic;

import aplicacion.modelo.logic.Data;

public class _Data extends Data {
	public Object[][] getClientInformation(String idcliente){
		String q="select descripcion from ma_cuentas where codigo like '"+idcliente+"'"; 
		Object[][] results=getResults(q);
		return results;
	}
}
