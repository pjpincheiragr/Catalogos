package aplicacion.compras.carga.encabezado.logic;
import aplicacion.modelo.logic.Data;

public class _Data extends Data {

	public Object[][] getProveedor(String idproveedor){
		String[] parameters=new String[]{
				idproveedor
				};
		String q=this.getQuery("proveedor", parameters);
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
		
		
	}
	
	public String getEliminarEnlace(String tc,String idcomprobante,String cuenta){
		String q="delete from b_compras_pp where ";
		q+="tc like '"+tc+"' ";
		q+="and idcomprobante like '"+tc+"' ";
		q+=" and cuenta like '"+cuenta+"' ";
		q+="";
		System.out.println(">"+q);
		return q;
	}
public Object[][] getCpte(String cuenta,String tc,String idcomprobante){
		
		String[] parameters=new String[]{
				cuenta,
				tc,
				idcomprobante
				};
		String q=this.getQuery("cpte", parameters);
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
		
		
	}
	public Object[][] getAcountInfo(String cuenta){
		String[] parameters=new String[]{
				cuenta
			};
			String q=this.getQuery("account_info", parameters);
			Object[][] results=this.getResults(q);
			return results;
	}
	
	public Object[][] getImputacionCodigos(String idproveedor){
		String[] parameters=new String[]{
				idproveedor,
				
				};
		String q=this.getQuery("imputacion", parameters);
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
	}
	
	public Object[][] getProveedorCuit(String cuit){
		String[] parameters=new String[]{
				cuit,
				
				};
		String q=this.getQuery("cuit", parameters);
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
	}
	public Object[][] getCuentaDescripcion(String cuenta){
		String[] parameters=new String[]{
				cuenta,
				};
		String q=this.getQuery("cuenta", parameters);
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
	}
	
public String getEliminarAsiento(String cuenta,String tc,String sucursal,String numero,String letra){
		
		String[] parameters=new String[]{
				cuenta,
				tc,
				sucursal,
				numero,
				letra
				};
		String q=this.getQuery("eliminar_asiento", parameters);
		System.out.println(">"+q);
		
		return q;
		
		
	}	


public boolean deleteFotos(String idproveedor,String tc,String idcomprobante){
String q="delete from facturas where idproveedor like '"+idproveedor+"' and tc like '"+tc+"' and idcomprobante like '"+idcomprobante+"'";
this.getConnector("MySQL").clearBatch();
this.getConnector("MySQL").addBatch(q);
return this.getConnector("MySQL").executeBatch();
}

public Object[][] getFotos(String cuenta,String tc,String idcomprobante){
		
		String[] parameters=new String[]{
				cuenta,
				tc,
				idcomprobante
				};
		String q=this.getQuery("fotos", parameters);
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
		
		
	}
	public Object[][] getEncabezado(String idproveedor,String tc,String idcomprobante){
		String[] parameters=new String[]{
				idproveedor,
				tc,
				idcomprobante
				};
		String q=this.getQuery("encabezado", parameters);
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
	}
	public String getEliminarCpte(String cuenta,String tc,String idcomprobante){
		String[] parameters=new String[]{
				cuenta,
				tc,
				idcomprobante
				};
		String q=this.getQuery("eliminar_cpte", parameters);
		System.out.println(">"+q);
		
		return q;
	}
	public String getEliminarCpteInsumos(String cuenta,String tc,String idcomprobante){
		String[] parameters=new String[]{
				cuenta,
				tc,
				idcomprobante
				};
		String q=this.getQuery("eliminar_cpteinsumos", parameters);
		System.out.println(">"+q);
		
		return q;
	}
	
	public String getEliminarDigital(String cuenta,String tc,String idcomprobante){
		String[] parameters=new String[]{
				cuenta,
				tc,
				idcomprobante
				};
		String q=this.getQuery("eliminar_digital", parameters);
		System.out.println(">"+q);
		
		return q;
	}
	
	
	
	public String getEliminarStock(String cuenta,String tc,String idcomprobante){
		String[] parameters=new String[]{
				cuenta,
				tc,
				idcomprobante
				};
		String q=this.getQuery("eliminar_stock", parameters);
		System.out.println(">"+q);
		
		return q;
	}
	
	public String getUpdateStock(String cuenta,String tc,String idcomprobante,String _x_cuenta,String _x_tc,String _x_idcomprobante){
		String[] parameters=new String[]{
				cuenta,
				tc,
				idcomprobante,
				_x_cuenta,
				_x_tc,
				_x_idcomprobante
				};
		String q=this.getQuery("update_stock", parameters);
		System.out.println(">"+q);
		
		return q;
	}

	public String getUpdateCpteInsumos(String cuenta,String tc,String idcomprobante,String _x_cuenta,String _x_tc,String _x_idcomprobante){
		String[] parameters=new String[]{
				cuenta,
				tc,
				idcomprobante,
				_x_cuenta,
				_x_tc,
				_x_idcomprobante
				};
		String q=this.getQuery("update_cpteinsumos", parameters);
		System.out.println(">"+q);
		
		return q;
	}
	public String getBlock(){
		String q=this.getMessage("block");
		System.out.println(">"+q);
		
		return q;
	}
	public String getunBlock(){
		String q=this.getMessage("unblock");
		System.out.println(">"+q);
		
		return q;
	}
	public Object[][] getImpuestos(String idproveedor,String tc,String idcomprobante){
		String sucursal=idcomprobante.substring(0,4);
		String numero=idcomprobante.substring(4,12);
		String letra=idcomprobante.substring(12, 13);
		String[] parameters=new String[]{
				idproveedor,
				tc,
				sucursal,
				numero,
				letra
				};
		String q=this.getQuery("asiento_numero", parameters);
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		int mes_operativo=-1;
		int numero_asiento=-1;
		
		if (results!=null){
			if (results.length>0){
				try {
					mes_operativo=new Integer((String)results[0][0]);
					numero_asiento=new Integer((String)results[0][1]);
				}catch(Exception e){
					
				}
			}
		}
		if (mes_operativo >0 & numero_asiento>0){
			results=this.getAsiento(""+mes_operativo, ""+numero_asiento);
		}else{
			results=null;
		}
		return results;
	}
	
	private Object[][] getAsiento(String mes_operativo,String numero_asiento){
		String[] parameters=new String[]{
				mes_operativo,
				numero_asiento
				};
		String q=this.getQuery("asiento", parameters);
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
	}

	public Object[][] getAsiento(String idproveedor,String tc,String sucursal,String numero,String letra){
		
		String[] parameters=new String[]{
				idproveedor,
				tc,
				sucursal,
				numero,
				letra
				};
		String q=this.getQuery("asiento_numero", parameters);
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
	}
	
	public String getFecha(String idproveedor,String tc,String sucursal,String numero,String letra){
		String fecha="";
		String q="select fecha from c_mv_cpte where cuenta like '"+idproveedor+"' and tc like '"+tc+"' and sucursal like '"+sucursal+"' and numero like '"+numero+"' and letra like '"+letra+"' ";
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		if (results!=null){
				if (results.length>0){
					fecha=(String) results[0][0];		
				}
		}
		return fecha;
	}
	
	public Object[][] existe_cpte(String idproveedor,String tc,String idcomprobante){
		String[] parameters=new String[]{
				idproveedor,
				tc,
				idcomprobante
				};
		String q="";
		if (tc.compareTo("FCN")!=0){
			q=this.getQuery("existe_cpte", parameters);	
		}else{
			q=this.getQuery("b_existe_cpte", parameters);
		}
		
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
	}
	
	public String getInsert(String cuenta,
			String tc,
			String idcomprobante,
			String fecha,
			String fecha_contable,
			String vencimiento,
			String importe,
			String importe_s_iva,
			String importe_insumos,
			String importe_iva,
			String neto_gravado,
			String sucursal,
			String numero,
			String letra
			){
		String[] parameters=new String[]{
				cuenta,
				tc,
				idcomprobante,
				fecha,
				fecha_contable,
				vencimiento,
				importe,
				importe_s_iva,
				importe_insumos,
				importe_iva,
				neto_gravado,
				sucursal,
				numero,
				letra
				};
		String q=this.getQuery("insert", parameters);
		System.out.println(">insert query"+q);
		return q;
	}
	
	public String getInsertDigital(String cuenta,String tc,String idcomprobante,int fotos,int carga_articulos){
		String[] parameters=new String[]{
			cuenta,tc,idcomprobante,""+fotos,""+carga_articulos	
		};
		String q=this.getQuery("insert_digital", parameters);
		return q;
	}
	
	public String getUpdateDigital(int fotos,int carga_articulos,String cuenta,String tc,String idcomprobante,String _x_cuenta,String _x_tc,String _x_idcomprobante){
		String[] parameters=new String[]{
				""+fotos,""+carga_articulos,cuenta,tc,idcomprobante	,_x_cuenta,_x_tc,_x_idcomprobante
		};
		String q=this.getQuery("update_digital", parameters);
		return q;
	}
	
	public Object[][] getFechaValida(String fecha){
		String[] parameters=new String[]{
				fecha	
			};
			String q=this.getQuery("fecha_valida", parameters);
			Object[][] results=this.getResults(q);
			return results;
	}
	
	public Object[][] getCerrado(String fecha){
		String[] parameters=new String[]{
				fecha	
			};
			String q=this.getQuery("cerrado", parameters);
			System.out.println(q);
			Object[][] results=this.getResults(q);
			return results;
	}
	
	public Object[][] getValida(String fecha){
		String[] parameters=new String[]{
				fecha	
			};
			String q=this.getQuery("valida", parameters);
			System.out.println(q);
			Object[][] results=this.getResults(q);
			return results;
	}
	public Object[][] getConfigurado(String idproveedor){
		String[] parameters=new String[]{
				idproveedor	
			};
			String q=this.getQuery("configurado", parameters);
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
}
