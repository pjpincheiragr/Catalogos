package aplicacion.gestion.agenda.logic;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.JOptionPane;

import aplicacion.modelo.logic.Data;

public class _Data extends Data{
	private String TC="MSJ";
	
	private int getProximoTC(String tc){
		int c=0;
		String q="select x_ultimo_nro from b_ta_cpte where codigo = '"+tc+"'";
		Object[][] aux=connection_handler.getDefaultConnector().getResults(q);
		try{
			c=new Integer(aux[0][0].toString());
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return c;
	}
	
		
	public String getUpdatePG(){
		String aux="";
		int num=this.getProximoPG()+1;
		aux="update b_ta_cpte set x_ultimo_nro="+num;
		aux=aux+" where codigo = '"+TC+"'";
		return aux;
	}
	
		
	public boolean exist(String idx){
		boolean ok=false;
		idx=idx.replace(" ", "");
		String q="select * from b_agenda where idaviso like '"+idx+"'";
		System.out.println(q);
		Object[][] results=connection_handler.getDefaultConnector().getResults(q);
		if (results!=null){
			if (results.length>0){
				ok=true;
			}
		}
		return ok;
	}
	
	public String getInsert(String idaviso,String titulo,String mensaje,String iduser,String agenda){
		String q="";
		q+="insert into b_agenda (idaviso,titulo,mensaje,iduser,agenda)";
		q+="values ('"+idaviso+"','"+titulo+"','"+mensaje+"','"+iduser+"','"+agenda+"') ";
		q+="";
		return q;
	}
	
	public String getUpdate(String idaviso,String titulo,String mensaje,String iduser,String agenda){
		String q="";
		q+="update  b_agenda set titulo='"+titulo+"',mensaje='"+mensaje+"',agenda='"+agenda+"' ";
		q+="where idaviso ='"+idaviso+"' ";
		q+="";
		return q;
	}
	public String getAvisoQuery(String idaviso){
		String q="select idaviso,titulo,descripcion,iduser,agenda from b_avisos where idaviso like '"+idaviso+"'";
		return q;
	}
	
	public Object[][] getAviso(String idaviso){
		String q=this.getAvisoQuery(idaviso);
		Object[][] results=this.getResults(q);
		return results;
	}
	
	public String getProximoPGCorrecto(){
		String prox="";
		prox=this.getProximoPG_Ceros();
		return prox;
	}
	
	private int getProximoPG(){
		int c=0;
		c=this.getProximoTC(""+TC+"");
		return c;
	}
	
	
	private String getProximoPG_Ceros(){
		String c="";
		int i=this.getProximoPG();
		String aux=""+i;
		while (aux.length()<8){
			aux="0"+aux;
		}
		c=aux;
		return c;
	}
	
	public String getVendedorValidacion(String password){
		String q="select idvendedor from b_users where pass like '"+password+"' ";
		String idvendedor="";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				idvendedor=(String) results[0][0];
			}
		}
		return idvendedor;
	}
}
