package aplicacion.flota.novedad.logic;


import aplicacion.modelo.logic.Data;

public class _Data extends Data {

	/**
	 * devuelve idhost,ip,server,printer,monitor,isnull(extension,''),dhcp
	 * @param iduser
	 * @return
	 */
	public Object[][] getaplicacion(String idaplicacion){
		Object[][] results=null;
		String q="";
		q+="select  idaplicacion,menu_nombre,area,lanzador from b_aplicaciones ";
		
		q+="where idaplicacion like '"+idaplicacion+"' ";
		results=getResults(q);
		return results;
	}
	
	public Object[][] getIcons(String idaplicacion){
		
		Object[][] results=null;
		String q="";
		q+="SELECT icono FROM b_aplicaciones";
	//	q+="  where idaplicacion like '"+idaplicacion+"'";
		results=this.getResults(q);
		return results;
	}
	
	public int getIconsCount(String idaplicacion){
		
			String q="SELECT count(idaplicacion) FROM b_aplicaciones  where idaplicacion like '"+idaplicacion+"'";
			int cantidad=0;
			Object[][] results=this.getConnector("MySQL").getResults(q);
			if (results!=null){
			
				if (results.length>0){
				String _cantidad=results[0][0].toString();
					try {
						System.out.println("cantidad de iconos:"+results[0][0].toString());
						cantidad=new Integer(_cantidad);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}	
		return cantidad;
	}

		
	
	/**
	 * guarda un aplicacion nuevo en memoria
	 * 
	 * @param idaplicacion
	 * @param nombre
	 * @param area
	 * @param lanzador
	 * @return
	 */
	public boolean insert(String idaplicacion, String nombre,String area,String lanzador){
		String q="";
		q+="insert into b_aplicaciones (idaplicacion,menu_nombre,area,lanzador)";
		q+="values ('"+idaplicacion+"','"+nombre+"','"+area+"','"+lanzador+"')";
		clearBatch();
		addBatch(q);
		return executeBatch();
	}
	
	/**
	 * actualiza un aplicacion ya guardado
	 * 
	 * @param idaplicacion
	 * @param nombre
	 * @param area
	 * @param lanzador
	 * @return
	 */
	public boolean update(String idaplicacion, String menu_nombre, String area, String lanzador){
		String q="";
			
		q+="update b_aplicaciones set idaplicacion='"+idaplicacion+"',menu_nombre='"+menu_nombre+"',area='"+area+"',lanzador='"+lanzador+"'";
		q+=" where idaplicacion like '"+idaplicacion+"' ";
		q+="";
		
		clearBatch();
		addBatch(q);
		return executeBatch();
	}
	
	/**
	 * renombra un aplicacion ya guardado
	 * 
	 * @param idaplicacion
	 * @param nuevo_nombre
	 * @return
	 */
	public boolean recodificar(String idaplicacion, String nuevo_nombre){
		String q="";	
		q+="update b_aplicaciones set idaplicacion='"+nuevo_nombre+"' ";
		q+=" where idaplicacion like '"+idaplicacion+"' ";
		q+="";
		
		clearBatch();
		addBatch(q);
		return executeBatch();
	}
	
	/**
	 * chequea la existencia del aplicacion seleccionado en la memoria
	 * @param id
	 * @return
	 */
	public boolean check_aplicacion(String id){
		boolean existe=false;
		Object[][] results=this.getaplicacion(id);
	
		if (results!=null){
			existe=results.length>0;
			}
		
		return existe;
	}
	
	/**
	 * elimina el aplicacion seleccionado
	 * @param id
	 */
	public void delete(String id){
		String q="";
		q+="delete from b_aplicaciones where idaplicacion like '"+id+"' ";
		q+="";
		clearBatch();
		addBatch(q);
		executeBatch();
	}
	
	public String getAreas(){
		String q="";
		q+="select area from b_aplicaciones group by area order by area ";
		return q;
	}
}
