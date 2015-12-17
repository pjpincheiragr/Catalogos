package aplicacion.sistema.perfil.logic;


import aplicacion.modelo.logic.Data;

public class _Data extends Data {

	public Object[][] getParametroSqlite(String iduser){
		Object[][] results=null;
		String q="";
		q+="select  iduser,nombre,pass from b_users ";
		q+="where iduser like '"+iduser+"' ";
		results=getResults(q);
		return results;
	}

	
	
	public String getUpdatePassword(String iduser,String password){
		String q="";
		q+="update b_users set pass='"+password+"' ";
		q+="where iduser like '"+iduser+"' ";
		return q;
	}
	
	
	public String insert(String iduser, String nombre,String background,String superusuario,String monitor,String internet,String idvendedor,String asterisk){
		String q="";
		q+="insert into b_users (iduser,nombre,pass,background,superusuario,monitor,internet,idvendedor,asterisk)";
		q+="values ('"+iduser+"','"+nombre+"','"+iduser+"','"+background+"','"+superusuario+"','"+monitor+"',"+internet+",'"+idvendedor+"',"+asterisk+")";
		
		return q;
	}
	
//	public String update(String iduser, String nombre,String background,String superusuario,String monitor,String internet,String idvendedor,String asterisk){
//		String q="";
//		q+="update b_users set nombre='"+nombre+"',background='"+background+"',superusuario="+superusuario+",monitor="+monitor+",internet="+internet+",idvendedor='"+idvendedor+"',asterisk="+asterisk+" where iduser like '"+iduser+"' ";
//		q+="";
//		return q;
//	}
	public String update(String iduser, String nombre, String apellido,String telefono, String email, String DNI, String vencimiento,String nacimiento,String domicilio){
		String q="";
		q+="update b_users set nombre='"+nombre+"',apellido='"+apellido+"',telefono='"+telefono+"',email='"+email+"',dni='"+DNI+"',fecha_licencia='"+vencimiento+"',fecha_nacimiento='"+nacimiento+"',domicilio='"+domicilio+"'  where iduser like '"+iduser+"' ";
		q+="";
		return q;
	}
	
	
	
	public int getWallpapersCount(String iduser){
		String q="SELECT count(id) FROM wallpapers  where iduser like '"+iduser+"'";
		int cantidad=0;
		Object[][] results=this.getConnector("MySQL").getResults(q);
		if (results!=null){
		if (results.length>0){
		String _cantidad=(String) results[0][0];
		try {
			cantidad=new Integer(_cantidad);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		}
		return cantidad;
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
public Object[][] getUser(String iduser){
		
		String q="";
		q+="select iduser,nombre,apellido,email,telefono,dni,convert(varchar,fecha_licencia,105),convert(varchar,fecha_nacimiento,105),domicilio from b_users where idvendedor like '"+iduser+"' ";
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
	
	public Object[][] getFotos(String iduser){
		String q="";
		q+="select foto  ";
		q+="from b_user_foto ";
		q+="where iduser like '"+iduser+"' ";
		q+="order by foto ";
		return this.getResults(q);
	}
	
	public String get_delete_fotos(String iduser){
		String q="";
		q+="delete from b_user_foto ";
		q+="where iduser like '"+iduser+"'";
		q+="";
		return q;
	}
	
	public String get_insert_foto(String iduser,String foto){
		String q="";
		q+="insert into b_user_foto (iduser,foto) ";
		q+="values ('"+iduser+"','"+foto+"')";
		q+="";
		return q;
	}
	
}
