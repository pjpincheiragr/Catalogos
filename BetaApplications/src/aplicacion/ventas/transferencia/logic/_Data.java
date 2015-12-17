package aplicacion.ventas.transferencia.logic;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.JOptionPane;

import aplicacion.modelo.logic.Data;

public class _Data extends Data{
	private String tc="TRPC";
	
	
	public String getProximoCorrecto(){
		String prox="";
		prox=this.getProximoCeros();
		return prox;
	}
	
	public boolean existe(String idtransferencia){
		boolean existe=false;
		String q="select * from b_pdc_transferencia where idtransferencia like '"+idtransferencia+"' ";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				existe=true;
			}
		}
		return existe;
	}
	
	private String getProximoCeros(){
		String c="";
		int i=this.getProximo();
		String aux=""+i;
		while (aux.length()<8){
			aux="0"+aux;
		}
		c=aux;
		return c;
	}
	
	
	public int getProximo(){
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
	
	
		
	
	public Object[][] getVendedor(String iduser){
		String q="";
		q+="select b.idvendedor,v.nombre ";
		q+="from b_users b left outer join v_ta_vendedores v on ltrim(b.idvendedor)=ltrim(v.idvendedor) ";
		q+="where b.iduser like '"+iduser+"'";
		return this.getResults(q);
	}
	public String getUpdate(String idpedido,String iduser_destino){
		String q="update b_pdc set idvendedor ='"+iduser_destino+"' where idpedido like '"+idpedido+"' ";
		return q;
	}
	
	
	
	public String getInsert(String idtransferencia,String idpedido,String iduser_origen,String iduser_destino,String nota,String objetivo){
		String q="insert into b_pdc_transferencia(idtransferencia,fecha,idpedido,iduser_origen,iduser_destino,nota,objetivo) ";
		q+=" values (";
		q+="'"+idtransferencia+"',";
		q+="getdate(),";
		q+="'"+idpedido+"',";
		q+="'"+iduser_origen+"',";
		q+="'"+iduser_destino+"',";
		q+="'"+nota+"',";
		q+="'"+objetivo+"'";
		q+=")";
		return q;
	}
	
	public Object[][] get_objetivos(){
		String q="";
		q+="select descripcion ";
		q+="from   b_pdc_objetivos ";
		q+="order by descripcion desc ";
		System.out.println(q);
		Object[][] results=connection_handler.getDefaultConnector().getResults(q);
		return results;
	}
	
	public String getUpdateTC(){
		String q="";
		q+="update b_ta_cpte set x_ultimo_nro = x_ultimo_nro + 1 ";
		q+="where codigo = '"+tc+"' ";
		return q;
	}
	
	public String getVendedorNombre(String iduser){
		String nombre="";
		String q="";
		q+="select v.nombre ";
		q+="from  v_ta_vendedores v  ";
		q+="where idvendedor like '"+iduser+"'";
		Object[][]results= this.getResults(q);
		if (results!=null){
    		if (results.length>0){
    			nombre=(String)results[0][0];
    		}
    	}
		return nombre;
	}
	
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
	public String getProximoAvisoCorrecto(){
		String prox="";
		prox=""+this.getProximoAviso();
		return prox;
	}
	
	private int getProximoAviso(){
		int c=0;
		c=this.getProximoTC("MSJ");
		return c;
	}
		
	public String getUpdateAvisos(){
		String aux="";
		aux="update b_ta_cpte set x_ultimo_nro=x_ultimo_nro+1";
		aux=aux+" where codigo = 'MSJ'";
		return aux;
	}
	
	public String getInsertAvisoUser(String idaviso,String iduser,String agenda,String pantalla){
		String q="";
		q+="insert into b_agenda_user (idaviso,iduser,agenda,pantalla) values ("+idaviso+",'"+iduser+"','"+agenda+"',"+pantalla+")";
		return q;
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
	
	public String getEvent(String idaviso,String iduser,String event){
		String host=getHost();
		String q="insert into b_agenda_event (idaviso,iduser,host,event) ";
		q+=" values ("+idaviso+",'"+iduser+"','"+host+"','"+event+"') ";
		return q;
	}
	
	public String getInsert(String idaviso,String titulo,String mensaje,String idcreador,String agenda,String privado,String pantalla){
		String q="";
		q+="insert into b_agenda (idaviso,titulo,mensaje,idcreador,agenda,privado,pantalla)";
		q+="values ("+idaviso+",'"+titulo+"','"+mensaje+"','"+idcreador+"','"+agenda+"',"+privado+","+pantalla+") ";
		q+="";
		return q;
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
	
	public boolean exist(String id,String iduser){
		boolean exist=false;
		
		String q="select * from b_users_categorias where id like '"+id+"' and iduser like '"+iduser+"' ";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				exist=true;
			}
		}
		return exist;
	}
	
	public boolean existeCategoria(String idpedido,String iduser){
		boolean existe=false;
		String q="select idcategoria from b_pdc_categorias where idpedido like '"+idpedido+"' and iduser like '"+iduser+"' ";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				existe=true;
			}
		}
		return existe;
	}
	
	public String insertCategoria(String idpedido,String iduser,String idcategoria,String level){
		String q="insert into b_pdc_categorias (idcategoria,iduser,idpedido,nivel) values ('"+idcategoria+"','"+iduser+"','"+idpedido+"',"+level+")";
		
		return q;
	}
	
	public String updateCategorias(String idcategoria,String iduser){
		String q="delete from b_pdc_categorias where idcategoria like '"+idcategoria+"%' and iduser like '"+iduser+"' ";
		return q;
	}
	
	public String updateCategoria(String idpedido,String iduser,String idcategoria){
		String q="update b_pdc_categorias set idcategoria='"+idcategoria+"' where iduser like '"+iduser+"' and idpedido like '"+idpedido+"' ";
		return q;
	}
	
	public String getUpdateLevel(String idpedido,String iduser,String level){
		String q="";
		q+="update b_pdc_categorias set nivel="+level+" where idpedido like '"+idpedido+"' and iduser like '"+iduser+"' ";
		return q;
	}
	
	public String getDeleteAsociado(String idaviso){
		String q="";
		q+=" delete from  b_agenda_asociado where idaviso ="+idaviso+" ";
		q+="";
		return q;
	}
	
	public String getInsertAsociado(String idaviso,String tc,String idcomprobante){
		String q="";
		q+="insert into b_agenda_asociado (idaviso,tc,idcomprobante) ";
		q+=" values ("+idaviso+",'"+tc+"','"+idcomprobante+"')";
		q+="";
		return q;
	}
	}



