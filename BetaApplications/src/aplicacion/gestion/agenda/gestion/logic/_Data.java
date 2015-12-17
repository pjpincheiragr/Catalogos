package aplicacion.gestion.agenda.gestion.logic;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
	
	public String getUpdate(String idaviso,String iduser,String agenda){
		String q="";
		q+="update  b_agenda_user set agenda='"+agenda+"' ";
		q+="where idaviso ="+idaviso+" and iduser like '"+iduser+"' ";
		q+="";
		return q;
	}
	
	
	public String getUpdateLeido(String idaviso,String iduser,boolean _leido){
		String q="";
		String leido="0";
		if (_leido){
		leido="1";	
		}
		q+="update  b_agenda_user set leido="+leido+" ";
		q+="where idaviso ="+idaviso+" and iduser like '"+iduser+"' ";
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
	
	
	public String getAvisoQuery(String idaviso){
		String q="select idaviso,titulo,mensaje,";
		q+="convert(varchar,agenda,105)+' '+convert(varchar,agenda,8),";
		q+="idcreador,privado from b_agenda ";
		q+="where idaviso like '"+idaviso+"'";
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
	
	private String getAvisoUserListQuery(String idaviso){
		String q = " ";
		q+=" select iduser from b_agenda_user where idaviso like '"+idaviso+"'";
		System.out.println(q);
		return q;
	}
	
	public Object[][] getAvisosColorOld(String desde,String hasta,String iduser){
		String q = " ";
		q+=" select day(agenda),count(idaviso) ";
		q+=" from b_agenda_user ";
		q+=" where iduser like '"+iduser+"' ";
		q+=" group by day(agenda) ";
		return this.getResults(q);
	}
	
	public String getAgendaColorQuery(String iduser,String idhost,String desde,String hasta,List<String> idcategoria){
		String q=" ";
		q+=" select day(u.agenda),count(u.idaviso) ";
		q+=" from b_agenda_user u  left outer join b_agenda a  on u.idaviso=a.idaviso ";
		q+=" left outer join b_agenda_show s  on u.idaviso=s.idaviso and u.iduser=s.iduser ";
		q+=" left outer join b_agenda_categorias c   on u.idaviso=c.idaviso and u.iduser=c.iduser ";
		q+=" left outer join b_categorias_avisos ca   on isnull(c.idcategoria,'')=ca.id and c.iduser=ca.iduser ";
		q+=" where u.iduser like '"+iduser+"'  ";
		q+=" and u.agenda between '"+desde+"' and '"+hasta+"' and a.anulado= 0 ";
		String categorias="";
		int i=0;
		while (i<idcategoria.size()){
			if (categorias.length()>0){
				categorias+=" or ";
			}
			if (idcategoria.get(i).compareTo("")!=0){
				categorias+=" isnull(c.idcategoria,'') like '"+idcategoria.get(i)+"%' ";	
			}else{
				categorias+=" isnull(c.idcategoria,'') like '"+idcategoria.get(i)+"' ";
			}
			i++;
		}
		if (categorias.length()>0){
			q+=" and ("+categorias+") ";	
		}
		
		q+=" group by u.agenda ";
		q+=" order by u.agenda ";
		System.out.println(q);
		return q;
	}
	
	public Object[][] getMessages(String iduser,String idhost,String fecha_limite){
		return this.getResults(this.getMessagesQuery(iduser, idhost, fecha_limite));
	}
	public String getMessagesQuery(String iduser,String idhost,String fecha_limite){
		String q=" ";
		q+=" select u.idaviso,u.iduser,a.titulo,a.mensaje,a.privado,u.agenda,GETDATE(),max(s.show),u.pantalla ";
		q+=" from b_agenda_user u ";
		q+=" left outer join b_agenda a ";
		q+=" on u.idaviso=a.idaviso ";
		q+=" left outer join b_agenda_show s ";
		q+=" on u.idaviso=s.idaviso and u.iduser=s.iduser and s.idhost like '"+idhost+"' ";
		q+=" where u.iduser like '"+iduser+"'  ";
		q+=" and u.agenda < GETDATE() and a.anulado= 0 and u.leido=0 ";
		q+=" group by u.idaviso,u.iduser,a.titulo,a.mensaje,a.privado,u.agenda,u.pantalla ";
		q+=" having isnull(max(show),'01-01-1900 00:00:00')<'"+fecha_limite+"' ";
		q+=" order by max(s.show) ";
		System.out.println(q);
		return q;
	}
	public Object[][] getAgenda(String iduser,String idhost,String desde,String hasta,List<String> idcategoria){
		return this.getResults(this.getAgendaQuery(iduser, idhost, desde,hasta,idcategoria));
	}
	/*
	 * 
	 *
  select u.idaviso,a.titulo,convert(varchar,u.agenda,105)+' '+convert(varchar,u.agenda,8),u.leido,max(s.show),
  isnull(ca.clasificacion,''),isnull(c.idcategoria,''),day(u.agenda)  
  from b_agenda_user u  left outer join b_agenda a  on u.idaviso=a.idaviso  
  left outer join b_agenda_show s  on u.idaviso=s.idaviso and u.iduser=s.iduser  
  left outer join b_agenda_categorias c   on u.idaviso=c.idaviso and u.iduser=c.iduser  
  left outer join b_categorias_avisos ca   on isnull(c.idcategoria,'')=ca.id and c.iduser=ca.iduser  
  where u.iduser like '   9'   and u.agenda between '22-08-2010' and '29-08-2010' and a.anulado= 0  
  and isnull(c.idcategoria,'') like '%'  
  group by u.idaviso,u.iduser,a.titulo,u.leido,u.agenda,u.pantalla,isnull(ca.clasificacion,''),isnull(c.idcategoria,'')
  order by u.agenda,max(s.show) 
	 */
	public String getAgendaQuery(String iduser,String idhost,String desde,String hasta,List<String> idcategoria){
		String q=" ";
		q+=" select u.idaviso,a.titulo,convert(varchar,u.agenda,105)+' '+convert(varchar,u.agenda,8),u.leido,max(s.show), ";
		q+=" isnull(ca.clasificacion,''),isnull(c.idcategoria,''),day(u.agenda) ";
		q+=" from b_agenda_user u  left outer join b_agenda a  on u.idaviso=a.idaviso ";
		q+=" left outer join b_agenda_show s  on u.idaviso=s.idaviso and u.iduser=s.iduser ";
		q+=" left outer join b_agenda_categorias c   on u.idaviso=c.idaviso and u.iduser=c.iduser ";
		q+=" left outer join b_categorias_avisos ca   on isnull(c.idcategoria,'')=ca.id and c.iduser=ca.iduser ";
		q+=" where u.iduser like '"+iduser+"'  ";
		q+=" and u.agenda between '"+desde+"' and '"+hasta+"' and a.anulado= 0 ";
		String categorias="";
		int i=0;
		while (i<idcategoria.size()){
			if (categorias.length()>0){
				categorias+=" or ";
			}
			if (idcategoria.get(i).compareTo("")!=0){
				categorias+=" isnull(c.idcategoria,'') like '"+idcategoria.get(i)+"%' ";	
			}else{
				categorias+=" isnull(c.idcategoria,'') like '"+idcategoria.get(i)+"' ";
			}
			i++;
		}
		if (categorias.length()>0){
			q+=" and ("+categorias+") ";	
		}
		
		q+=" group by u.idaviso,u.iduser,a.titulo,u.leido,u.agenda,u.pantalla,isnull(ca.clasificacion,''),isnull(c.idcategoria,'') ";
		q+=" order by u.agenda,max(s.show) ";
		System.out.println(q);
		return q;
	}
	/*
	 * 
	 * 
	 */
	/**
	 * 
	 * @param iduser
	 * @param idhost
	 * @param desde
	 * @param hasta
	 * @param idcategoria
	 * @return
	 */
	public String getAgendaQueryOLD(String iduser,String idhost,String desde,String hasta,String idcategoria){
		String q=" ";
		q+=" select u.idaviso,a.titulo,convert(varchar,u.agenda,105)+' '+convert(varchar,u.agenda,8),u.leido,max(s.show),ca.clasificacion,c.idcategoria,day(u.agenda) ";
		q+=" from b_agenda_user u ";
		q+=" left outer join b_agenda_categorias c on u.idaviso=c.idaviso and u.iduser=c.iduser ";
		q+=" left outer join b_categorias_avisos ca on c.idcategoria=ca.id and c.iduser=ca.iduser ";
		q+=" left outer join b_agenda a ";
		q+=" on u.idaviso=a.idaviso ";
		q+=" left outer join b_agenda_show s ";
		q+=" on u.idaviso=s.idaviso and u.iduser=s.iduser ";
		q+=" where u.iduser like '"+iduser+"'  ";
		q+=" and u.agenda between '"+desde+"' and '"+hasta+"' and a.anulado= 0 ";
		if (idcategoria.compareTo("0")!=0){
			q+=" and c.idcategoria like '"+idcategoria+"%' ";	
		}
		q+=" group by u.idaviso,u.iduser,a.titulo,u.leido,u.agenda,u.pantalla,ca.clasificacion,c.idcategoria ";
		q+=" order by u.agenda,max(s.show) ";
		System.out.println(q);
		return q;
	}
	
	public String getUpdate(String newcode,String oldcode,String iduser,String color){
		String q="update b_categorias_avisos set clasificacion='"+newcode+"'";
		if (color.compareTo("")!=0){
			q+=",background='"+color+"' ";
		}
		q+=" where id like '"+oldcode+"' and iduser like '"+iduser+"'";
		return q;
	}
	
	public String getUpdatePadre(String newcode,String oldcode,String iduser){
		String q="update b_categorias_avisos set padre='"+newcode+"' where padre like '"+oldcode+"' and iduser like '"+iduser+"' ";
		return q;
	}
	
	public String getId(String clasificacion,String padre,String iduser){
		String q="select id from b_categorias_avisos where clasificacion like '"+clasificacion+"' and padre like '"+padre+"' and iduser like '"+iduser+"' ";
		return q;
	}
	
	public String getClasificacionDescripcion(String id){
		String q="select clasificacion from b_categorias_avisos where id like '"+id+"' ";
		String descripcion="";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				descripcion=(String) results[0][0];
			}
		}
		return descripcion;
	}
	
	public String getClasificacion(String clasificacion,String padre,String iduser){
		String q="select clasificacion,id from b_categorias_avisos where id like '"+clasificacion+"' and padre like '"+padre+"' and iduser like '"+iduser+"' order by clasificacion";
		return q;
	}
	
	public String getInsert(String id,String clasificacion,String padre,String iduser,String color){
		String q="insert into b_categorias_avisos (id,clasificacion,padre,iduser,background) values ('"+id+"','"+clasificacion+"','"+padre+"','"+iduser+"','"+color+"')";
		return q;
	}
	
	public String getDelete(String clasificacion,String padre,String iduser){
		String q="delete from  b_categorias_avisos where  clasificacion like '"+clasificacion+"' and padre like '"+padre+"' and iduser like '"+iduser+"' ";
		System.out.println(q);
		return q;
	}
	
	public boolean puedeEliminar(String clasificacion,String padre,String iduser){
		boolean exist=false;
		String q="select * from  b_categorias_avisos where  clasificacion like '"+clasificacion+"' and padre like '"+padre+"' and iduser like '"+iduser+"' ";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				exist=true;
			}
		}
		return exist;
	}
	
	public String getAvisoHora(String idaviso,String iduser){
		String exist="";
		String q="select convert(varchar,agenda,8) from  b_agenda_user where  iduser like '"+iduser+"' and idaviso ="+idaviso+" ";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				exist=(String) results[0][0];
			}
		}
		return exist;
	}
	public boolean exist(String id,String iduser){
		boolean exist=false;
		
		String q="select * from b_categorias_avisos where id like '"+id+"' and iduser like '"+iduser+"' ";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				exist=true;
			}
		}
		return exist;
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
		String q="insert into b_agenda_categorias (idcategoria,iduser,idaviso) values ('"+idcategoria+"','"+iduser+"','"+idpedido+"')";
		
		return q;
	}
	
	public String updateCategorias(String idcategoria,String iduser){
		String q="delete from b_agenda_categorias where idcategoria like '"+idcategoria+"%' and iduser like '"+iduser+"' ";
		return q;
	}
	
	public String updateCategoria(String idpedido,String iduser,String idcategoria){
		String q="update b_agenda_categorias set idcategoria='"+idcategoria+"' where iduser like '"+iduser+"' and idaviso like '"+idpedido+"' ";
		return q;
	}
	public boolean existeAviso(String idaviso,String iduser){
		boolean ok=false;
		String q="";
		q+="select * from b_agenda_user ";
		q+="where idaviso ="+idaviso+" and iduser like '"+iduser+"' ";
		Object[][] results=this.getResults(q);
    	if (results!=null){
    		if (results.length>0){
    			ok=true;
    		}
    	}
		return ok;
	}
	
	public boolean getAvisoLeido(String idaviso,String iduser){
		boolean ok=false;
		String q="";
		q+="select * from b_agenda_user ";
		q+="where idaviso ="+idaviso+" and iduser like '"+iduser+"' and leido=1 ";
		Object[][] results=this.getResults(q);
    	if (results!=null){
    		if (results.length>0){
    			ok=true;
    		}
    	}
		return ok;
	}
	public boolean esCreador(String idaviso,String iduser){
		boolean ok=false;
		String q="";
		q+="select * from b_agenda ";
		q+="where idaviso ="+idaviso+" and idcreador like '"+iduser+"' ";
		Object[][] results=this.getResults(q);
    	if (results!=null){
    		if (results.length>0){
    			ok=true;
    		}
    	}
		return ok;
	}

}
