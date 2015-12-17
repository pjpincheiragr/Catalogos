package aplicacion.proveedor.reporte.logic;

import aplicacion.modelo.logic.Data;

public class _Data extends Data {
	public Object[][] getProveedorInformation(String proveedor){
		String q="select descripcion from ma_cuentas where codigo like '"+proveedor+"'"; 
		Object[][] results=getResults(q);
		return results;
	}
}
