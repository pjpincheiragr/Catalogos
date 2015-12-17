package aplicacion.asterisk.manager.logic;

import aplicacion.herramientas.conexion.conectores.SQLite;
import aplicacion.herramientas.conexion.creator.interfaces._Interface;
import aplicacion.modelo.logic.Data;

public class _Data extends Data {
	
	public String getVersion(){
		Object[][] results=this.getParametroSqlite("version");
		String _valor="";
		if (results!=null){
			if (results.length>0){
				try {
					_valor=results[0][1].toString();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
				
		}
		
		return _valor;
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
	private String  _system_loadAplicationsQuery(String area,String iduser){
		String q="";
		q=q+" select a.area,a.menu_nombre,a.lanzador from b_users_aplicaciones u ";
		q=q+" left outer join b_aplicaciones a ";
		q=q+" on u.idaplicacion = a.idaplicacion ";
		q=q+" where u.iduser like '"+iduser+"' ";
		q=q+" and a.area like '"+area+"' ";
		q=q+" and a.visible=1 ";
		q=q+" order by a.area,a.menu_nombre ";
		return q;
	}
	
		
	public int getImages(String iduser){
		int i=0;
		String q="select count(iduser) from wallpapers where iduser like '"+iduser+"'";
		Object[][] results=this.getConnector("MySQL").getResults(q);
		if (results!=null){
			if (results.length>0){
				try {
					i=new Integer((String)results[0][0]);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return i;
	}
	
	public void eliminar_fotos(String iduser){
		String q="delete from wallpapers where iduser like '"+iduser+"'";
		this.getConnector("MySQL").clearBatch();
		this.getConnector("MySQL").addBatch(q);
		this.getConnector("MySQL").executeBatch();
	}
	private String  _system_loadUserAreasQuery(String iduser){
		String q="";
		q=q+" select a.area from b_users_aplicaciones u ";
		q=q+" left outer join b_aplicaciones a ";
		q=q+" on u.idaplicacion = a.idaplicacion ";
		q=q+" where u.iduser like '"+iduser+"' ";
		q=q+" group by a.area ";
		q=q+" order by a.area ";
		//System.out.println(q);
		return q;
	}
	
	public Object[][] _system_loadUserAreas(String iduser){
		Object[][] results=null;
		if (connection_handler.getDefaultConnector()!=null){
			results=connection_handler.getDefaultConnector().getResults(this._system_loadUserAreasQuery(iduser));
		}
		
		return results;
	}
	
	public Object[][] _system_loadAplications(String area,String iduser){
		Object[][] results=connection_handler.getDefaultConnector().getResults(this._system_loadAplicationsQuery(area,iduser));
		return results;
	}
	
	private String  loadAplicationsQuery(String area,String iduser){
		String q="";
		q=q+" select area,label,id from Aplicaciones ";
		q=q+" where area like '"+area+"'";
		q=q+" order by area,label ";
		return q;
	}
	
	private String  loadUserAreasQuery(String iduser){
		String q="";
		q=q+" select area from Aplicaciones ";
		q=q+" group by area ";
		q=q+" order by area ";
		return q;
	}
	
	public Object[][] loadUserAreas(String iduser){
		Object[][] results=getConnector(_Interface._beta_database).getResults(this.loadUserAreasQuery(iduser));
		return results;
	}
	
	public Object[][] loadAplications(String area,String iduser){
		Object[][] results=getConnector(_Interface._beta_database).getResults(this.loadAplicationsQuery(area,iduser));
		return results;
	}
	
	public Object[][] getBackground(String iduser){
		String q="";
		q+="select isnull(background,'') from b_users where iduser like '"+iduser+"' ";
		q+="";
		Object[][] results=getResults(q);
		return results;
	}
	public Object[][] getParametroSqlite(String id){
		Object[][] results=null;
		String q="";
		q+="select  id,value from parameters ";
		q+="where id like '%"+id+"%' ";
		q+="order by id";
		try {
			SQLite Connector=(SQLite)getConnector(_Interface._beta_database);
			if (Connector!=null){
				results=Connector.getResults(q);	
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}
	
	public String getPrinted(String id){
		String q="";
		q+="update b_etiquetas set impresa=1 where id ="+id+" ";
		return q;
	}

	public String getCuentaQuery(String callerid){
		String q="";
		q+="select a.cuenta,c.descripcion ";
		q+="from b_ask_callerid a ";
		q+="left outer join ma_cuentas c on a.cuenta=c.codigo ";
		q+="where a.callerid like '"+callerid+"'";
		return q;
	}
	
	public Object[][] getCuenta(String callerid){
		return this.getResults(this.getCuentaQuery(callerid));
	}
	
	public boolean isCallRegistered(String callerid,String extension,String channel){
		boolean ok=false;
		String q="";
		q+="select fecha from b_ask_extensions_history ";
		q+="where callerid like '"+callerid+"' and channel like '"+channel+"' and datediff(second,fecha,getdate())<5 ";
		Object[][] results=this.getResults(q);
    	if (results!=null){
    		if (results.length>0){
    			ok=true;
    		}
    	}
		return ok;
	}
	public String getDelete(String callerid){
		String q="";
		q+="delete from b_ask_callerid where callerid like '"+callerid+"' ";
		
		return q;
	}
	
	public String getInsertCallerId(String callerid,String cuenta){
		String q="insert into b_ask_callerid (callerid,cuenta) values ('"+callerid+"','"+cuenta+"') ";
		return q;
	}
	
	public boolean register_call(String callerid,String extension,String channel){
		String q="insert into b_ask_extensions_history (fecha,callerid,extension,channel) ";
		q+="values (getdate(),'"+callerid+"','"+extension+"','"+channel+"')";
		this.clearBatch();
		this.addBatch(q);
		return !this.executeBatch();
	}
	
	public String getExtension(){
		String host=getHost();
		String extension="";
		String q="select extension from b_ask_extensions where host like '"+host+"' ";
		//System.out.println(q);
		Object[][] results=this.getResults(q);
    	if (results!=null){
    		if (results.length>0){
    			extension=(String)results[0][0];
    		}
    	}
		return extension;
	}
	
	public Object[][] getIconos(String iduser){
		String q="";
		q+="select a.area,a.menu_nombre,isnull(a.icono,''),a.lanzador ";
		q+="from b_users_aplicaciones u ";
		q+="left outer join b_aplicaciones a ";
		q+="on u.idaplicacion=a.idaplicacion ";
		q+="where u.iduser like '"+iduser+"' and u.icono=1 and isnull(a.icono,'') not like '' ";
		Object[][] results=this.getResults(q);
		return results;
	}
	public boolean getAgenda(String iduser){
		boolean ok=false;
		
		String q="select agenda from b_users where iduser like '"+iduser+"' and agenda=1 ";
		System.out.println(q);
		Object[][] results=this.getResults(q);
		if (results!=null){
    		if (results.length>0){
    			ok=true;
    		}
    	}
		return ok;
	}
	public boolean getAsterisk(String iduser){
		boolean ok=false;
		String q="select asterisk from b_users where iduser like '"+iduser+"' and asterisk=1 ";
		//System.out.println(q);
		Object[][] results=this.getResults(q);
		if (results!=null){
    		if (results.length>0){
    			ok=true;
    		}
    	}
		return ok;
	}
	
	public Object[][] getExtensionHistory(String extension){
		String q="select calldate,src,duration,disposition from cdr where dst like '"+extension+"' and src not like '' order by calldate desc limit 0,100 ; ";
		//System.out.println(q);
		Object[][] results=this.getConnector("Asterisk").getResults(q);
		return results;
	}
	
	public String getInsertShow(String idaviso,String iduser,String idhost){
		String q="";
		q+="insert into b_agenda_show (idaviso,iduser,idhost) ";
		q+="values ("+idaviso+",'"+iduser+"','"+idhost+"')";
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
	
	public String getVendedor(String iduser){
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
	
	public Object[][] getHistorial(String idhost){
		return this.getResults(this.getHistorialQuery(idhost));
	}
	
	public String getHistorialQuery(String idhost){
		String q="";
		q+="select top 50 s.idaviso,v.Nombre ,a.titulo,u.agenda ";
		q+="from b_agenda_show s left outer join b_agenda a ";
		q+="on s.idaviso=a.idaviso ";
		q+=" left outer join b_agenda_user u ";
		q+=" on s.idaviso=u.idaviso and s.iduser=u.iduser ";
		q+=" left outer join b_user_host h ";
		q+=" on u.iduser=h.iduser ";
		q+="left outer join V_TA_VENDEDORES v ";
		q+="on s.iduser=v.IdVendedor ";
		q+="where h.idhost like '"+idhost+"' and u.leido=0 and a.anulado=0 ";
		q+="group by s.idaviso,v.Nombre ,a.titulo,u.agenda ";
		q+="order by max(s.show) desc ";
		System.out.println(q);
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

