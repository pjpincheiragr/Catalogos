package aplicacion.sistema.host.logic;


import aplicacion.modelo.logic.Data;

public class _Data extends Data {

	/**
	 * devuelve idhost,ip,server,printer,monitor,isnull(extension,''),dhcp
	 * @param iduser
	 * @return
	 */
	public Object[][] getHost(String iduser){
		Object[][] results=null;
		String q="";
		q+="select  idhost,ip,server,printer,monitor,extension,dhcp,email,os,mac from b_host ";
		
		q+="where idhost like '"+iduser+"' ";
		results=getResults(q);
		return results;
	}
	
	/**
	 * guarda un host nuevo en memoria
	 * 
	 * @param idhost
	 * @param ip
	 * @param server
	 * @param printer
	 * @param monitor
	 * @param extension
	 * @param dhcp
	 * @param email
	 * @param os
	 * @return
	 */
	public boolean insert(String idhost, String ip,String server,String printer,String monitor,String extension,boolean dhcp,String email,String os,String mac){
		String q="";
		q+="insert into b_host (idhost,ip,server,printer,monitor,dhcp,email,os,mac";
		if (extension.compareTo("")!=0){
			q+=",extension";	
		}
		q+=")";
		String _dhcp="0";
		if (dhcp)_dhcp="1";
		q+="values ('"+idhost+"','"+ip+"',"+server+","+printer+","+monitor+","+_dhcp+",'"+email+"','"+os+"','"+mac+"'";
		if (extension.compareTo("")!=0){
			q+=","+extension;	
		}
		q+=")";
		clearBatch();
		addBatch(q);
		return executeBatch();
	}
	
	/**
	 * actualiza un host ya guardado
	 * 
	 * @param idhost
	 * @param ip
	 * @param server
	 * @param monitor
	 * @param printer
	 * @param extension
	 * @param dhcp
	 * @param email
	 * @param os
	 * @return
	 */
	public boolean update(String idhost, String ip,String server,String monitor,String printer,String extension,boolean dhcp,String email,String os,String mac){
		String q="";
		String _dhcp="0";
		if (dhcp)_dhcp="1";
		if (extension.compareTo("")==0){
			extension="null";
		}
			
		q+="update b_host set ip='"+ip+"',server="+server+",monitor="+monitor+",printer="+printer+",dhcp="+_dhcp+",email='"+email+"',os='"+os+"',mac='"+mac+"'";
		q+=",extension="+extension;	
		q+=" where idhost like '"+idhost+"' ";
		q+="";
		
		clearBatch();
		addBatch(q);
		return executeBatch();
	}
	
	/**
	 * renombra un host ya guardado
	 * 
	 * @param idhost
	 * @param nuevo_nombre
	 * @return
	 */
	public boolean recodificar(String idhost, String nuevo_nombre){
		String q="";	
		q+="update b_host set idhost='"+nuevo_nombre+"' ";
		q+=" where idhost like '"+idhost+"' ";
		q+="";
		
		clearBatch();
		addBatch(q);
		return executeBatch();
	}
	
	/**
	 * chequea la existencia del host seleccionado en la memoria
	 * @param id
	 * @return
	 */
	public boolean check_host(String id){
		boolean existe=false;
		Object[][] results=this.getHost(id);
	
		if (results!=null){
			existe=results.length>0;
			}
		
		return existe;
	}
	
	/**
	 * elimina el host seleccionado
	 * @param id
	 */
	public void delete(String id){
		String q="";
		q+="delete from b_host where idhost like '"+id+"' ";
		q+="";
		clearBatch();
		addBatch(q);
		executeBatch();
	}
	
}
