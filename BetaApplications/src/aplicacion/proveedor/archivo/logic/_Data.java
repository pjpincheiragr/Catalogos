package aplicacion.proveedor.archivo.logic;
import aplicacion.modelo.logic.Logic;
import aplicacion.modelo.logic.Data;

public class _Data extends Data{

	public Object[][] getSaldos(String idproveedor){
		Object[][] results=null;
		String q="";
		String[] parameters=new String[] {
			idproveedor	
		};
		q=this.getQuery("saldos", parameters);
		System.out.println("SALDOS> "+q);
		results=this.getResults(q);
		return results;
	}
	
	/**
	 * Devuelve el proveedor web equivalente segun el proveedor local  
	 * @param idproveedor
	 * @return
	 */
	public String getEquivalente(String idproveedor){
			String equivalencia_local="";
			String q="select equivalencia from b_proveedor_equivalencia where idproveedor like '"+idproveedor+"' ";
			Object[][] results=this.getResults(q);
			if (results!=null){
				if (results.length>0){
					equivalencia_local=(String)results[0][0];
				}
			}
			return equivalencia_local;
	}
	public String getInsertEquivalente(String idproveedor,String equivalencia){
		String q="";
		q+="insert into b_proveedor_equivalencia (idproveedor,equivalencia) values ('"+idproveedor+"','"+equivalencia+"') ";
		q+="";
		return q;
	}
	
	public String getUpdateEquivalente(String idproveedor,String equivalencia){
		String q="";
		q+="update b_proveedor_equivalencia set equivalencia='"+equivalencia+"' where idproveedor like '"+idproveedor+"' ";
		q+="";
		return q;
	}
	
	public String getUpdateMaestro(String codigo,String telefono,String cpostal,String fax,String email,String localidad,String observaciones,String contacto,String calle,String numero,String piso){
		String q="";
		q+="update ma_cuentasadic set ";
		q+="mail='"+email+"',fax='"+fax+"',telefono='"+telefono+"',";
		q+="cpostal='"+cpostal+"',localidad='"+localidad+"',observaciones='"+observaciones+"',contacto='"+contacto+"', ";
		q+="calle='"+calle+"',numero='"+numero+"',piso='"+piso+"' ";
		q+="where codigo like '"+codigo+"' ";
		return q;
	}
	private String getProveedorQuery(String idproveedor){
		String q="";
		String[] parameters=new String[] {
				idproveedor	
			};
		q=this.getQuery("proveedor", parameters);
		return q;
	}
	public Object[][] getProveedor(String idproveedor){
		String q=this.getProveedorQuery(idproveedor);
		System.out.println(q);
		Object[][] results=this.getResults(q);
		return results;
	}
	public boolean existeProveedor(String idproveedor){
		boolean existe=false;
		String q=this.getProveedorQuery(idproveedor);
		System.out.println(q);
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				existe=true;
			}
		}
		return existe;
	}
	
	
	
	public boolean updateRestrictions(
	    String requiere_iibb,
		String requiere_perc_iva,
		String requiere_perc_gan,
		String requiere_neto_ngrav,
		String actualiza_precios,
		String imprime_etiq,
		String iva10,
		String iva27,
		String iva21,
		String articulos,
		String rg3337,
		String remitos,
		String impuestos_internos,
		String alicuota_impuestos_internos,
		String alicuota_ingresos_brutos,
		String alicuota_percepcion_iva,
		String tc,
		String descuento,
		String idtransporte,
		String idcodigo
		){
		boolean ok=false;
		String q="";
		String[] parameters=new String[] {
				requiere_iibb,
				requiere_perc_iva,
				requiere_perc_gan,
				requiere_neto_ngrav,
				actualiza_precios,
				imprime_etiq,
				iva10,
				iva27,
				iva21,
				articulos,
				rg3337,
				remitos,
				impuestos_internos,
				alicuota_impuestos_internos,
				alicuota_ingresos_brutos,
				alicuota_percepcion_iva,
				tc,
				descuento,
				idtransporte,
				idcodigo
		};
		q=this.getQuery("update", parameters);
		connection_handler.getDefaultConnector().clearBatch();
		System.out.println(q);
		connection_handler.getDefaultConnector().addBatch(q);
		ok=!connection_handler.getDefaultConnector().executeBatch();
		return ok;
	}

	public Object[][] cargarCuenta(String codigo){
		String[] parameters=new String[]{
			codigo	
		};
		String q=this.getQuery("account_info", parameters);
		System.out.println(q);
		Object[][] results=this.getResults(q);
		return results;
	}
	
	public Object[][] getImputaciones(String codigo){
		String[] parameters=new String[]{
			codigo	
		};
		String q=this.getQuery("imputaciones", parameters);
		System.out.println(q);
		Object[][] results=this.getResults(q);
		return results;
	}
	
	
	
	public Object[][] cargar(String codigo){
		String[] parameters=new String[]{
			codigo	
		};
		String q=this.getQuery("account_info", parameters);
		System.out.println(q);
		Object[][] results=this.getResults(q);
		return results;
	}
}

