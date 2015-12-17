package aplicacion.gestion.agenda.escritor.logic;


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
	
	public Object[][] getAsociados(String idaviso){
		String q="";
		q+="select tc,idcomprobante ";
		q+="from b_agenda_asociado  ";
		q+="where idaviso = "+idaviso+" ";
		q+=" order by tc,idcomprobante ";
		return this.getResults(q);
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
	
	public String getEliminar(String idaviso){
		String q="";
		q+="update b_agenda set anulado=1 where idaviso = '"+idaviso+"' ";
		return q;
	}
	
	public String getUpdate(String idaviso,String iduser,String agenda,String leido,String pantalla){
		String q="";
		q+="update  b_agenda_user set leido="+leido+",pantalla="+pantalla+",agenda='"+agenda+"' ";
		q+="where idaviso ='"+idaviso+"' and iduser like '"+iduser+"' ";
		q+="";
		return q;
	}
	
	
	public String getUpdate(String idaviso,String titulo,String mensaje,String agenda,String privado,String pantalla){
		String q="";
		q+="update  b_agenda set titulo='"+titulo+"',mensaje='"+mensaje+"',agenda='"+agenda+"',privado="+privado+" ,pantalla="+pantalla+" ";
		q+="where idaviso ="+idaviso+" ";
		q+="";
		return q;
	}
	
	public String getAvisoQuery(String idaviso,String iduser){
		String q="select a.idaviso,a.titulo,a.mensaje,";
		q+="convert(varchar,u.agenda,105)+' '+convert(varchar,u.agenda,8),";
		q+="a.idcreador,a.privado,u.pantalla ";
		q+="from b_agenda a left outer join b_agenda_user u ";
		q+=" on a.idaviso = u.idaviso and u.iduser like '"+iduser+"' ";
		q+="where a.idaviso like '"+idaviso+"' ";
		return q;
	}
	
	public Object[][] getAviso(String idaviso,String iduser){
		String q=this.getAvisoQuery(idaviso,iduser);
		Object[][] results=this.getResults(q);
		return results;
	}

	
	public String getAvisoQuery(String idaviso){
		String q="select a.idaviso,a.titulo,a.mensaje,";
		q+="convert(varchar,u.agenda,105)+' '+convert(varchar,u.agenda,8),";
		q+="a.idcreador,a.privado,u.pantalla from b_agenda a left outer join b_agenda_user u ";
		q+=" on a.idcreador= u.iduser and a.idcreador=u.iduser ";
		q+="where a.idaviso like '"+idaviso+"'";
		return q;
	}
	
	public Object[][] getAviso(String idaviso){
		String q=this.getAvisoQuery(idaviso);
		Object[][] results=this.getResults(q);
		return results;
	}
	
	public String getProximoPGCorrecto(){
		String prox="";
		prox=""+this.getProximoPG();
		return prox;
	}
	
	private int getProximoPG(){
		int c=0;
		c=this.getProximoTC(""+TC+"");
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
	
	public String getDeleteAvisoUsers(String idaviso,String iduser){
		String q="";
		q+="delete from  b_agenda_user where idaviso="+idaviso+"  and iduser = '"+iduser+"' ";
		return q;
	}
	
	public String getUpdateAvisoUsers(String idaviso,String iduser,String agenda,String pantalla){
		String q="";
		q+="update  b_agenda_user set pantalla="+pantalla+",agenda='"+agenda+"' where idaviso="+idaviso+"  and iduser = '"+iduser+"' ";
		return q;
	}

	public boolean existeCategoria(String idaviso,String iduser){
		boolean existe=false;
		String q="select idcategoria from b_agenda_categorias where idaviso like '"+idaviso+"' and iduser like '"+iduser+"' ";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				existe=true;
			}
		}
		return existe;
	}
	
	
	
	public String insertCategoria(String idpedido,String iduser,String idcategoria){
		String q="insert into b_agenda_categorias (idcategoria,iduser,idaviso,nivel) values ('"+idcategoria+"','"+iduser+"','"+idpedido+"',0)";
		
		return q;
	}
	
	public String updateCategoria(String idpedido,String iduser,String idcategoria){
		String q="update b_agenda_categorias set idcategoria='"+idcategoria+"' where iduser like '"+iduser+"' and idaviso like '"+idpedido+"' ";
		return q;
	}
	
	
	
	public boolean existAvisoUsers(String idaviso,String iduser){
		String q="";
		q+="select * from  b_agenda_user where idaviso="+idaviso+" and iduser = '"+iduser+"' ";
		Object[][] results=getResults(q);
		boolean ok=false;
		if (results!=null){
			if (results.length>0){
				ok=true;
			}
		}
		return ok;
	}
	
	public String getInsertAvisoUser(String idaviso,String iduser,String agenda,String pantalla){
		String q="";
		q+="insert into b_agenda_user (idaviso,iduser,agenda,pantalla) values ("+idaviso+",'"+iduser+"','"+agenda+"',"+pantalla+")";
		return q;
	}
	
	public Object[][] getUserList(){
		return this.getResults(this.getUserListQuery());
	}
	private String getUserListQuery(){
		String q = " ";
		q+=" select v.IdVendedor,v.Nombre,0,0 ";
		q+=" from v_ta_vendedores v  ";
		q+=" left outer join b_users u ";
		q+=" on v.IdVendedor=u.idvendedor ";
		q+=" where u.IDUSER is not null ";
		q+=" group by v.IdVendedor,v.Nombre ";
		System.out.println(q);
		return q;
	}
	
	public String[] getAvisoUserList(String idaviso){
		String[] list=null;
		Object[][] results=this.getResults(this.getAvisoUserListQuery(idaviso));
		list=new String[results.length];
		for (int i=0;i<results.length;i++){
			list[i]=(String)results[i][0];
		}
		return list;
	}
	
	public String[] getAvisoCategoriaList(String idaviso,String iduser){
		String[] list=null;
		Object[][] results=this.getResults(this.getAvisoCategoriaListQuery(idaviso, iduser));
		list=new String[results.length];
		for (int i=0;i<results.length;i++){
			list[i]=(String)results[i][0];
		}
		return list;
	}
	
	private String getAvisoUserListQuery(String idaviso){
		String q = " ";
		q+=" select iduser from b_agenda_user where idaviso like '"+idaviso+"'";
		System.out.println(q);
		return q;
	}
	
	private String getAvisoCategoriaListQuery(String idaviso,String iduser){
		String q = " ";
		q+=" select c.idcategoria from b_agenda_user u ";
		q+=" left outer join b_agenda_categorias c   on u.idaviso=c.idaviso and u.iduser=c.iduser ";
		q+=" where u.idaviso like '"+idaviso+"' and u.iduser like '"+iduser+"' ";
		System.out.println(q);
		return q;
	}
	
}
