package aplicacion.actualizacion.importar.logic;

import aplicacion.herramientas.conexion.creator.interfaces._Interface;
import aplicacion.modelo.logic.Data;

public class _Data extends Data {
	String tc="UPDW";
	
	public double getPrecioViejo(String idproveedor,String code,String linea){
     	double precio=0.0;
     	String q="SELECT PRECIO5 ";
     	q+=" FROM B_CODIGOS ";
     	q+=" WHERE IDPROVEEDOR LIKE '"+idproveedor+"' ";
     	q+=" and idcodigo like '"+code+"' ";
     	q+=" and lineaproveedor like '"+linea+"' ";
     	Object[][] results=getResults(q);
     	if (results!=null){
     		if (results.length>0){
     			try {
					precio=new Double((String)results[0][0]);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
     		}
     	}
     	return precio;
     }
 	
	public String getUpdate(double precio,double old,String codigo,String linea,String idproveedor,String idcomprobante){
 		linea=linea.replaceAll("'", "");
 		codigo=codigo.replaceAll("'", "");
 		String q="";
 		q+="update b_codigos set ";
 		q+="precio5="+precio+",";
 		q+="ultimo_upd=getDate(),tc='ODBC',idcomprobante='"+idcomprobante+"' ";
 		q+="where idcodigo like '"+codigo+"' and ";
 		q+="Lineaproveedor like '"+linea+"' and ";
 		q+="idproveedor = '"+idproveedor+"' ";
 		//System.out.println(q);
 		return q;
 	}
 	
	public String getInsertCodigosVariacion(String codigo,String linea,String idproveedor,double precio5,double old,String idcomprobante){
		String q="insert into b_codigos_variacion (idcodigo,lineaproveedor,idproveedor,";
		q+="precio5,old,fecha,tc,idcomprobante) values (";
		q+="'"+codigo+"','"+linea+"','"+idproveedor+"',";
		q+=precio5+","+old+",getdate(),'"+tc+"','"+idcomprobante+"')";
		return q;
	}
	
 	public String getUpdateFecha(String codigo,String linea,String idproveedor,String idcomprobante){
 		linea=linea.replaceAll("'", "");
 		codigo=codigo.replaceAll("'", "");
 		String q="";
 		q+="update b_codigos set ";
 		q+="ultimo_upd=getDate(),tc='"+tc+"',idcomprobante='"+idcomprobante+"' ";
 		q+="where idcodigo like '"+codigo+"' and ";
 		q+="Lineaproveedor like '"+linea+"' and ";
 		q+="idproveedor = '"+idproveedor+"' ";
 		//System.out.println(q);
 		return q;
 	}
 	
    
	
	public String getUltmimoAplicado(){
		String ultimo="";
		String q="";
		q+="Select top 1 idactualizacion from b_actualizacion_web where aplicada=1 ";
		q+="order by id desc ";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				ultimo=(String) results[0][0];
			}
		}
		return ultimo;
	}
	
	public boolean aplicado(String id){
		boolean ok=false;
		String q="";
		q+="Select id from b_actualizacion_web where idactualizacion like '"+id+"' and aplicada=1 ";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				ok=true;
			}
		}
		return ok;
	}
	
	public boolean viejo(String id,String idproveedor){
		boolean ok=false;
		String q="";
		q+="Select id from b_actualizacion_web where idactualizacion > '"+id+"' and aplicada=1 and idproveedor like '"+idproveedor+"' ";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				ok=true;
			}
		}
		return ok;
	}
	public boolean existe(String parameter){
		boolean existe=false;
		String q="";
		q+="SELECT value  ";
		q+="FROM parameters ";
		q+="where id like '"+parameter+"'";
		Object[][] results=connection_handler.getConnector(_Interface._beta_database).getResults(q);
		if (results!=null){
			existe=results.length>0;
		}
		return existe;
	}

	public void setValue(String parameter,String value){
		String q="";
		q+="update parameters set value='"+value+"' ";
		q+="where id like '"+parameter+"' ";
		connection_handler.getConnector(_Interface._beta_database).clearBatch();
		connection_handler.getConnector(_Interface._beta_database).addBatch(q);
		connection_handler.getConnector(_Interface._beta_database).executeBatch();
	}
	
	public Object[][] getLastUpdate(){
		String q="";
		q+="select top 1 convert(varchar,idversion)+'.'+convert(varchar,revision),fecha,modificacion ";
		q+="from b_version ";
		q+="order by idversion desc, revision desc";
		q+="";
		return this.getResults(q);
	}
	
	public void insert(String parameter,String value){
		String q="";
		q+="insert into parameters (id,value) ";
		q+="values ('"+parameter+"','"+value+"') ";
		connection_handler.getConnector(_Interface._beta_database).clearBatch();
		connection_handler.getConnector(_Interface._beta_database).addBatch(q);
		connection_handler.getConnector(_Interface._beta_database).executeBatch();
	}

	public String getInsert(String idproveedor,String codigo,String linea,double precio,double old,String descripcion,String idcomprobante,String politicaprecio){
 		String q="";
 		descripcion=descripcion.replaceAll("'", "");
 		linea=linea.replaceAll("'", "");
 		codigo=codigo.replaceAll("'", "");
 		
 		q=this.getInsertQuery(idproveedor, codigo, linea, precio, descripcion,politicaprecio,idcomprobante);
 		
 		return q;
 		
 	}
	
	
public boolean exist(String codigo,String lineaproveedor,String idproveedor){
	lineaproveedor=lineaproveedor.replaceAll("'", "");
 		codigo=codigo.replaceAll("'", "");
 		String q="select idcodigo from b_codigos ";
 		q+=" where idcodigo like '"+codigo+"' ";
 		q+=" and ltrim(rtrim(lineaproveedor)) like '"+lineaproveedor+"' ";
 		q+=" and idproveedor like '"+idproveedor+"' ";
 		//System.out.println(q);
 		Object[][] results=getResults(q);
 		boolean b=false;
 		if (results!=null){
 			if (results.length>0){
 				b=true;
 			}
 		}
 		return b;
 	}

/**
 * Devuelve el proveedor local en base al proveedor que viene en la actualizacion web  
 * @param idproveedor
 * @return
 */
public String getEquivalente(String idproveedor){
		String equivalencia_local="";
		String q="select idproveedor from b_proveedor_equivalencia where equivalencia like '"+idproveedor+"' ";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				equivalencia_local=(String)results[0][0];
			}
		}
		return equivalencia_local;
}
	
public String getPolitica(String idproveedor){
		String politica_predeterminada="";
		String q="select politica from b_proveedor_equivalencia where idproveedor like '"+idproveedor+"' ";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				politica_predeterminada=(String)results[0][0];
			}
		}
		return politica_predeterminada;
}
	
public String getInsertQuery(String idproveedor,String codigo,String linea,double precio,String descripcion,String politicaprecio,String idcomprobante){
     	String q="";
     	q+=" insert into b_codigos (idcodigo,idproveedor,lineaproveedor,precio5,descripcion,politicaprecio,tc,idcomprobante,ultimo_upd)";
     	q+=" values (";
     	q+=" '"+codigo+"',";
     	q+=" '"+idproveedor+"',";
     	q+=" '"+linea+"',";
     	q+=" "+precio+",";
     	q+=" '"+descripcion+"',";
     	q+="'"+politicaprecio+"','ODBC','"+idcomprobante+"',getdate())";
     	return q;
     }

public String getProveedor(String idproveedor){
	String q="select descripcion from ma_cuentas where codigo like '"+idproveedor+"'";
	Object[][] results=this.getResults(q);
	String descripcion="";
	if (results!=null){
		if (results.length>0){
			descripcion=(String) results[0][0];
		}
	}
	return descripcion;
}

public Object[][] getReportPrices(String idproveedor,String pol,String tc,String idcomprobante,String iduser){
      	String q="";
     	q+="select v.idarticulo,c.precio5, p.mcosto*c.precio5,p.mclase2*p.mcosto*c.precio5,v.descripabrev,getdate(),v.politicaprecios ";
      	q+="from ";
      	q+="b_codigos c join b_alias a "; 
      	q+="on c.idcodigo=a.idcodigo and c.lineaproveedor=a.lineaproveedor and c.idproveedor=a.idproveedor "; 
      	q+="join v_ma_articulos v on a.idarticulo=v.idarticulo ";
      	q+="left outer join v_ta_politicaprecios p on (case when isnull(c.politicaprecio,'') like '' then isnull(v.politicaprecios,'"+pol+"') else v.politicaprecios end)=p.codigo "; 
      	q+="where c.idproveedor like '"+idproveedor+"' and v.cuenta_actualizacion like '"+idproveedor+"' ";
      	q+=" and datediff(day,c.ultimo_upd,getdate())<30 ";
      //	System.out.println(q);
      	return this.getResults(q);
     }
	
public String getUpdatePrices(String idproveedor,String pol,String tc,String idcomprobante,String iduser){
	      	String q="";
	    	q+="update v set v.precio5=c.precio5, v.costo=p.mcosto*c.precio5,v.precio2=p.mclase2*p.mcosto*c.precio5, ";
	     	q+=" v.iduser_actualizador='"+iduser+"',actualizacion=getdate() ";
	      	q+="from  ";
	      	q+="b_codigos c join b_alias a "; 
	      	q+="on c.idcodigo=a.idcodigo and c.lineaproveedor=a.lineaproveedor and c.idproveedor=a.idproveedor "; 
	      	q+="join v_ma_articulos v on a.idarticulo=v.idarticulo  ";
	      	q+="left outer join v_ta_politicaprecios p on (case when isnull(c.politicaprecio,'') like '' then isnull(v.politicaprecios,'"+pol+"') else v.politicaprecios end)=p.codigo "; 
	      	q+="where c.idproveedor like '"+idproveedor+"' and v.cuenta_actualizacion like '"+idproveedor+"' ";
	      	q+=" and datediff(day,c.ultimo_upd,getdate())<30 ";
	      	
	      	//q+="and c.tc like '"+tc+"' and c.idcomprobante like '"+idcomprobante+"' ";
	      	System.out.println(q);
	      	return q;
	      }
	 
	 public boolean updateTC(String tc){
			String q="";
			q+="update b_ta_cpte set x_ultimo_nro = x_ultimo_nro + 1 where codigo like '"+tc+"'";
			this.clearBatch();
			this.addBatch(q);
			return !this.executeBatch();
			
		}
		public String getProximoPGCorrecto(String tc){
			String prox="";
			prox=this.getProximoPG_Ceros(tc);
			return prox;
		}
		
		private int getProximoPG(String tc){
			int c=0;
			c=this.getProximoTC(""+tc+"");
			return c;
		}
		
		
		
		private int getProximoTC(String tc){
			int c=0;
			String q="";
			q+="select x_ultimo_nro from b_ta_cpte ";
			q+="where codigo = '"+tc+"' ";
			Object[][] aux=connection_handler.getDefaultConnector().getResults(q);
			try{
				c=new Integer(aux[0][0].toString());
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
			return c;
		}
		
		private String getProximoPG_Ceros(String tc){
			String c="";
			int i=this.getProximoPG(tc);
			String aux=""+i;
			while (aux.length()<8){
				aux="0"+aux;
			}
			c=aux;
			return c;
		}
		
		public boolean setAplicado(String id,String idproveedor){
			String ultimo="";
			String q="";
			if (this.aplicado(id)){
				q="update b_actualizacion_web set aplicada=1 where idactualizacion='"+id+"' ";	
			}else{
				q="insert into b_actualizacion_web(idactualizacion,aplicada,fecha,idproveedor) values ('"+id+"',1,getdate(),'"+idproveedor+"') ";	
			}
			
			
			
			this.clearBatch();
			this.addBatch(q);
			System.out.println(q);
			return !this.executeBatch();
		}
}
