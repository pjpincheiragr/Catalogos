package aplicacion.inventario.reporteMovimiento.logic;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.JOptionPane;

import aplicacion.modelo.logic.Data;

public class _Data extends Data{
	private String TC="EGR";
	
	
	public Object[][] getProveedor(String codigo){
		String q="";
		q=q+" select m.codigo,m.descripcion,isnull(c.politica_default,''),isnull(p.descripcion,''),isnull(c.odbc_name,''),isnull(c.consulta,''),isnull(c.linea,'') ";
		q=q+" from ma_cuentas m left outer join b_configuracion_catalogos c ";
		q=q+" left outer join v_ta_politicaprecios p ";
		q=q+" on c.politica_default=p.codigo ";
		q=q+" on m.codigo=c.idproveedor ";
		q=q+" where m.codigo like '"+codigo+"' ";
		Object[][] results=getResults(q);
		return results;
	}
	
		
	public Object[][] getClientInformation(String idcliente){
		
		String q="";
		q=q+"select M.codigo,M.descripcion ";
		q=q+"FROM MA_CUENTAS M ";
		q=q+" where M.codigo like '"+idcliente+"' and M.titulo = 0";
		Object[][] results=connection_handler.getDefaultConnector().getResults(q);
		return results;
	}
	
	private String getLineQuery(String linea) {
		String q = "";
		q = q + "select top 1 descripabrev ";
		q = q + "FROM v_ma_articulos ";
		q = q + "where  descripabrev like '"+linea+"'";
		return q;
	}
	
	public boolean check_linea(String linea){
		boolean exist=false;
		Object[][] results = getResults(this.getLineQuery(linea));
		if (results != null) {
			if (results.length > 0) {
			exist=true;
			}
		}
		return exist;
	}
	
	private String getQueryLinePrefix(String linea){
		String q = "";
		q+="select top 1 substring(idarticulo,0,4) ";
		q+="FROM v_ma_articulos ";
		q+="where  descripabrev like '"+linea+"' ";
		q+="group by substring(idarticulo,0,4) ";
		q+="order by count(*) desc";
		return q;
	}
	public Object[][] getLinePrefix(String linea){
		return getResults(this.getQueryLinePrefix(linea));
	}
	
	private String getProvQuery(String code) {
		String q = "";
		q = q + "select M.codigo,M.descripcion,";
		q = q + "isnull(a.telefono,''),";
		q = q + "isnull(a.localidad,''),";
		q = q + "isnull(a.observaciones,'')";
		q = q + "FROM MA_CUENTAS M LEFT OUTER JOIN ";
		q = q + "MA_CUENTASADIC a ON ";
		q = q + "M.CODIGO = a.CODIGO ";
		q = q + "where M.codigo like '" + code + "'";
		return q;
	}
	public boolean check_proveedor(String idproveedor){
		boolean exist=false;
		Object[][] results = getResults(this.getProvQuery(idproveedor));
		if (results != null) {
			if (results.length > 0) {
			exist=true;
			}
		}
		return exist;
	}
}
