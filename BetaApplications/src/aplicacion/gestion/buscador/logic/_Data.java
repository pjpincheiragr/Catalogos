package aplicacion.gestion.buscador.logic;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.JOptionPane;

import aplicacion.modelo.logic.Data;
public class _Data extends Data{
	
	public Object[][] getClientInformation(String idcliente){
		String q="select codigo,descripcion from ma_cuentas where codigo like '"+idcliente+"' ";
		Object[][] results=getResults(q);
		return results;
	}
	
	
	public String getComprobantesAlfa(){
			String q="";
			q+="select ";
			q+="alfa.fecha as fecha, ";
			q+="alfa.cuenta as cuenta, ";
			q+="alfa.nombre as descripcion, ";
			q+="alfa.tc as tc, ";
			q+="alfa.idcomprobante as idcomprobante, ";
			q+="alfa.importe as importe ";
			q+="from c_mv_cpte alfa ";
			q+="left outer join c_mv_cpteinsumos alfa_insumos ";
			q+="on alfa.tc=alfa_insumos.tc and alfa.idcomprobante=alfa_insumos.idcomprobante ";
			q+="where alfa.cuenta like '211010029' ";
		return q;	
	}
	public String getComprobantesBeta(){
		String q="";
		q+="select  ";
		q+="beta.fecha_comprobante as fecha, ";
		q+="beta.cuenta as cuenta, ";
		q+="beta.cuenta_descripcion as descripcion, ";
		q+="beta.tc as tc,  ";
		q+="beta.idcomprobante as idcomprobante, ";
		q+="beta.total_cpte as importe ";
		q+="from b_fcn beta ";
		q+="left outer join b_fcn_item beta_items ";
		q+="on beta.tc=beta_items.tc and beta.idcomprobante=beta_items.idcomprobante ";
		q+="where beta.cuenta like '211010029' ";
		return q;
	}
	
	public String getComprobantesDeCompra(){
		String q="";
		q+=this.getComprobantesAlfa();
		q+="union ";
		q+=this.getComprobantesBeta();
		q+="order by fecha,cuenta,tc,idcomprobante ";
		return q;
	}
	
	}
