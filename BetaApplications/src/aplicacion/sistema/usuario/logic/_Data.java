package aplicacion.sistema.usuario.logic;


import aplicacion.modelo.logic.Data;

public class _Data extends Data {

	public Object[][] getParametroSqlite(String iduser){
		Object[][] results=null;
		String q="";
		q+="select  iduser,nombre,pass,background,superusuario,monitor,internet,idvendedor,isnull(asterisk,0),deposito,negocio from b_users ";
		q+="where iduser like '"+iduser+"' ";
		results=getResults(q);
		return results;
	}
	public Object[][] getCajas(String iduser){
		String q="";
		q+="select caja.idcajas,caja.descripcion,isnull(users.origen,0),isnull(users.destino,0) ";
		q+="from   b_ta_cajas caja left outer join b_users_caja users ";
		q+="on caja.idcajas =users.idcaja and users.iduser like '"+iduser+"' ";
		return getResults(q);
	}
	
	public boolean isCajaConfigured(String iduser,String idcaja){
		boolean caja=false;
		String q="";
		q+="select * ";
		q+="from  b_users_caja users ";
		q+="where users.idcaja like '"+idcaja+"' and users.iduser like '"+iduser+"'";
		Object[][] results=getResults(q);
		if (results!=null){
			if(results.length>0){
				caja=true;
			}
		}
		return caja;
	}
	public Object[][] getDeposito(String iduser){
		String q="";
		q+="select b.deposito,v.nombre ";
		q+="from b_users b left outer join v_ta_deposito v on ltrim(b.iddeposito)=ltrim(v.iddeposito) ";
		q+="where b.iduser like '"+iduser+"'";
		return this.getResults(q);
	}
	public boolean update(String iduser,String idcaja,boolean origen,boolean destino){
		boolean error=false;
		int _origen=0;
		int _destino=0;
		if (origen) _origen=1;
		if (destino) _destino=1;
		String q="";
		q+="update b_users_caja set origen="+_origen+",destino="+_destino+" ";
		q+="where iduser like '"+iduser+"' and idcaja like '"+idcaja+"' ";
		this.clearBatch();
		this.addBatch(q);
		error=this.executeBatch();
		return error;
	}
	
	public String getUpdatePassword(String iduser,String password){
		String q="";
		q+="update b_users set pass='"+password+"' ";
		q+="where iduser like '"+iduser+"' ";
		return q;
	}
	
	public boolean insert(String iduser,String idcaja,boolean origen,boolean destino){
		boolean error=false;
		int _origen=0;
		int _destino=0;
		if (origen) _origen=1;
		if (destino) _destino=1;
		String q="";
		q+="insert into b_users_caja (iduser,idcaja,origen,destino) ";
		q+="values ('"+iduser+"','"+idcaja+"',"+_origen+","+_destino+") ";
		this.clearBatch();
		this.addBatch(q);
		error=this.executeBatch();
		return error;
	}
	
	public String insert(String iduser, String nombre,String background,String superusuario,String monitor,String internet,String idvendedor,String asterisk,String deposito,String negocio){
		String q="";
		q+="insert into b_users (iduser,nombre,pass,background,superusuario,monitor,internet,idvendedor,asterisk,deposito,negocio)";
		q+="values ('"+iduser+"','"+nombre+"','"+iduser+"','"+background+"','"+superusuario+"','"+monitor+"',"+internet+",'"+idvendedor+"',"+asterisk+",'"+deposito+"','"+negocio+"')";
		
		return q;
	}
	
	public String update(String iduser, String nombre,String background,String superusuario,String monitor,String internet,String idvendedor,String asterisk,String deposito,String negocio){
		String q="";
		q+="update b_users set nombre='"+nombre+"',background='"+background+"',superusuario="+superusuario+",monitor="+monitor+",internet="+internet+",idvendedor='"+idvendedor+"',deposito='"+deposito+"',negocio='"+negocio+"',asterisk="+asterisk+" where iduser like '"+iduser+"' ";
		q+="";
		return q;
	}
	
	public boolean check_user(String id){
		boolean existe=false;
		Object[][] results=this.getParametroSqlite(id);
		if (results!=null){
			existe=results.length>0;
		}
		return existe;
	}
	
	public Object[][] getUserCheck(String user,String pass){
		
		String q="";
		q+="select iduser from b_users where iduser like '"+user+"' and pass like '"+pass+"' ";
		Object[][] results=null;
		
		try {
			results=getResults(q);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}
	
	public Object[][] getUserCheck(String pass){
		
		String q="";
		q+="select iduser from b_users where pass like '"+pass+"' ";
		Object[][] results=null;
		
		try {
			results=getResults(q);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}
	
	public void delete(String id){
		String q="";
		q+="delete from b_users where iduser like '"+id+"' ";
		q+="";
		clearBatch();
		addBatch(q);
		executeBatch();
	}
	
	public void delete_aplicaciones(String id){
		String q="";
		q+="delete from b_users_aplicaciones where iduser like '"+id+"' ";
		q+="";
		clearBatch();
		addBatch(q);
		executeBatch();
	}
	
	
	public String getDeleteAvisoUsers(String iduser){
		String q="";
		q+="delete from b_user_host where iduser='"+iduser+"'";
		return q;
	}
	
	public String getInsertAvisoUser(String iduser,String idhost){
		String q="";
		q+="insert into b_user_host (iduser,idhost) values ('"+iduser+"','"+idhost+"')";
		return q;
	}
	
	public Object[][] getHostList(){
		return this.getResults(this.getHostListQuery());
	}
	private String getHostListQuery(){
		String q = " ";
		q+=" select idhost,idhost,0,0 ";
		q+=" from b_host  ";
		System.out.println(q);
		return q;
	}
	
	public String[] getUserHostList(String iduser){
		String[] list=null;
		Object[][] results=this.getResults(this.getUserHostListQuery(iduser));
		list=new String[results.length];
		for (int i=0;i<results.length;i++){
			list[i]=(String)results[i][0];
		}
		return list;
	}
	
	private String getUserHostListQuery(String iduser){
		String q = " ";
		q+=" select idhost from b_user_host where iduser like '"+iduser+"'";
		System.out.println(q);
		return q;
	}

	public String getInsertHost(String iduser,String idhost){
		String q="insert b_user_host (iduser,idHost) ";
		q+="values ('"+iduser+"','"+idhost+"') ";
		return q;
	}

	public String getDeleteHost(String iduser){
		String q="delete from b_user_host ";
		q+="where iduser like '"+iduser+"' ";
		return q;
	}
	
	public Object[][] getVendedorPorNombre(String nombre){
		String q="select idvendedor from v_ta_vendedores ";
		q+="where nombre like '"+nombre+"' ";
		return this.getResults(q);
	}
	
}
