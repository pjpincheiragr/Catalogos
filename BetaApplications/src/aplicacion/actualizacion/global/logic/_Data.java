package aplicacion.actualizacion.global.logic;

import aplicacion.modelo.logic.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import aplicacion.herramientas.conexion.conectores.*;

public class _Data extends Data{
	String tc="odbc";
	 Statement stmt;
	 ResultSet rs;
	 Connection con;
	 String estado="";
	 int current=0;
	 int length=0;
	 boolean canceled=false;

	 public String getUpdateTC(String tc){
			String q="";
			q+="update b_ta_cpte set x_ultimo_nro = x_ultimo_nro + 1 where codigo like '"+tc+"'";
			return q;
			
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
		
	public String[] getODBC(String idproveedor){
    	 String[] consulta=new String[2];
    	 String q="";
    	 q+="select isnull(clase,'sun.jdbc.odbc.JdbcOdbcDriver'),odbc_name ";
    	 q+="from b_configuracion_catalogos "; 
    	 q+="where idproveedor like '"+idproveedor+"'";
    	 Object[][] results=this.getResults(q);
    	 if (results!=null){
    		 for (int i=0;i<results.length;i++){
    			 consulta[0]=(String) results[0][0];
    			 consulta[1]=(String) results[0][1];
    		 }
    	 }
    	 return consulta;
     }
	 
     public String getConnectionStream(String clase){
    	 String stream="jdbc:odbc:";
    	 if (clase.compareTo("jstels.jdbc.dbf.DBFDriver")==0){
    		 stream="jdbc:jstels:dbf:";
    	 }
    	 return stream;
     }
	public boolean createODBCConnection(String idproveedor) {
		String[] odbc=this.getODBC(idproveedor);
		
     	boolean b=true;
         try {
			ODBC c=new ODBC(null,odbc[0],this.getConnectionStream(odbc[0]));
			 c.setDebugMode(true);
			 c.setDatabase(odbc[1]);
			 c.setId(idproveedor);
			 c.connect();
			 connection_handler.addConector(c);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			b=false;
		}
         return b;
     }
	
	public String getInsertCodigosVariacion(String codigo,String linea,String idproveedor,double precio5,double old,String idcomprobante){
		String q="insert into b_codigos_variacion (idcodigo,lineaproveedor,idproveedor,";
		q+="precio5,old,fecha,tc,idcomprobante) values (";
		q+="'"+codigo+"','"+linea+"','"+idproveedor+"',";
		q+=precio5+","+old+",getdate(),'"+tc+"','"+idcomprobante+"')";
		return q;
	}
    
	public String getInsertArticulosVariacion(String idarticulo,String politica,String idproveedor,double precio5,
			double costo,double precio2,double mcosto,double mpublico){
		String q="insert into b_articulos_variacion (idarticulo,politica,idproveedor,precio5,";
		q+="costo,precio2,mcosto,mclase2,fecha,origen)";
		q+=" values (";
		q+="'"+idarticulo+"','"+politica+"','"+idproveedor+"',";
		q+=precio5+","+costo+","+precio2+","+mcosto+","+mpublico+",getdate(),'"+tc+"')";
		return q;
	}
	
     public Object[][] getODBCResult(String idproveedor,String query){
    	 Object[][] aux = null;
    	// System.out.println(query);
    	 aux=this.connection_handler.getConnector(idproveedor).getResults(query);
    	return aux;
     }
    
     
     public String getUpdateQuery(String idcomprobante,String pol,String iduser){
     	String q="";
     	q+=" update v set "; 
     	q+=" v.precio5=c.precio5, ";
     	q+=" v.costo=p.mcosto*c.precio5,v.precio2=p.mclase2*p.mcosto*c.precio5, ";
     	q+=" v.iduser_actualizador='"+iduser+"',";
     	q+=" v.actualizacion = getdate() ";
     	q+=" from ";
     	q+=" b_codigos c join b_alias a ";
     	q+=" on c.idcodigo=a.idcodigo and c.lineaproveedor=a.lineaproveedor and c.idproveedor=a.idproveedor ";
     	q+=" join v_ma_articulos v on a.idarticulo=v.idarticulo and a.idproveedor = v.cuenta_actualizacion ";
     	q+=" left outer join v_ta_politicaprecios p on (case when isnull(v.politicaprecios,'') like '' then '"+pol+"' else v.politicaprecios end)=p.codigo ";
     	q+=" where c.idcomprobante like '"+idcomprobante+"'  and c.tc like 'ODBC' ";
     	
     	System.out.println(q);
     	return q;
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
 	public String update_politica(String idcomprobante,String pol,String iduser){
 		String q="";
 		q=this.getUpdateQuery(idcomprobante,  pol,iduser);
 		//System.out.println(q);
 		return q;
 	}
 	public Object[][] getCatalogos(String tipo){
 		return this.getResults(this.getQueryCatalogos(tipo));
 	}
 	
 	public String getQueryCatalogos(String tipo){
 		String q="";
 		q+="select c.idproveedor,";
 		q+="a.descripcion, ";
 		q+="(case when rtrim(isnull(c.odbc_name,'')) like '' then 'archivo' else 'odbc' end) as 'tipo', ";
 		q+="c.odbc_name as origen, ";
 		q+="c.linea,c.politica_default ";
 		q+="from b_configuracion_catalogos c ";
 		q+="left outer join ma_cuentas a ";
 		q+="on c.idproveedor=a.codigo ";
 		if (tipo.compareTo("")!=0){
 			q+="where (case when rtrim(isnull(c.odbc_name,'')) like '' then 'archivo' else 'odbc' end) like '"+tipo+"' ";
 		}
 		q+="order by c.idproveedor ";
 		return q;
 	}
 	public String getInsert(String idproveedor,String codigo,String linea,double precio,double old,String descripcion,String idcomprobante,String politicaprecio){
 		String q="";
 		descripcion=descripcion.replaceAll("'", "");
 		linea=linea.replaceAll("'", "");
 		codigo=codigo.replaceAll("'", "");
 		
 		q=this.getInsertQuery(idproveedor, codigo, linea, precio, descripcion,politicaprecio,idcomprobante);
 		return q;
 	}
 	
 	public boolean insert(String idproveedor,String codigo,String linea,double precio,double old,String descripcion,String idcomprobante,String politicaprecio){
 		String q="";
 		descripcion=descripcion.replaceAll("'", "");
 		linea=linea.replaceAll("'", "");
 		codigo=codigo.replaceAll("'", "");
 		
 		q=this.getInsertQuery(idproveedor, codigo, linea, precio, descripcion,politicaprecio,idcomprobante);
 		
 		clearBatch();
 		String q2=this.getInsertCodigosVariacion(codigo, linea, idproveedor, precio,old,idcomprobante);
 		//System.out.println(q);
 		addBatch(q);
 		//System.out.println(q2);
 		addBatch(q2);
 		boolean error=false;
 		try {
			error=executeBatch();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		return error;
 		
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
 		System.out.println(q);
 		return q;
 		
 	}
 	public boolean update(double precio,double old,String codigo,String linea,String idproveedor,String idcomprobante){
 		linea=linea.replaceAll("'", "");
 		codigo=codigo.replaceAll("'", "");
 		String q="";
 		q+="update b_codigos set ";
 		q+="precio5="+precio+",";
 		q+="ultimo_upd=getDate(),tc='ODBC',idcomprobante='"+idcomprobante+"' ";
 		q+="where idcodigo like '"+codigo+"' and ";
 		q+="Lineaproveedor like '"+linea+"' and ";
 		q+="idproveedor = '"+idproveedor+"' ";
 		clearBatch();
 		addBatch(q);
 		String q2=this.getInsertCodigosVariacion(codigo, linea, idproveedor, precio,old,idcomprobante);
 		addBatch(q2);
 		System.out.println(q);
 		return executeBatch();
 	}
 	
 	
 	public boolean updateFecha(String codigo,String linea,String idproveedor,String idcomprobante){
 		linea=linea.replaceAll("'", "");
 		codigo=codigo.replaceAll("'", "");
 		String q="";
 		q+="update b_codigos set ";
 		q+="ultimo_upd=getDate(),tc='ODBC',idcomprobante='"+idcomprobante+"' ";
 		q+="where idcodigo like '"+codigo+"' and ";
 		q+="Lineaproveedor like '"+linea+"' and ";
 		q+="idproveedor = '"+idproveedor+"' ";
 		//System.out.println(q);
 		clearBatch();
 		addBatch(q);
 		return executeBatch();
 	}
 	
 	public String getUpdateFecha(String codigo,String linea,String idproveedor,String idcomprobante){
 		linea=linea.replaceAll("'", "");
 		codigo=codigo.replaceAll("'", "");
 		String q="";
 		q+="update b_codigos set ";
 		q+="ultimo_upd=getDate(),tc='ODBC',idcomprobante='"+idcomprobante+"' ";
 		q+="where idcodigo like '"+codigo+"' and ";
 		q+="Lineaproveedor like '"+linea+"' and ";
 		q+="idproveedor = '"+idproveedor+"' ";
 		//System.out.println(q);
 		return q;
 	}
    
     
     public String getQuery(String idprov){
    	 String consulta="";
    	 String[] parameters=new String[]{
    			 idprov
    	 };
    	 String q=this.getQuery("query", parameters);
    	 Object[][] results=getResults(q);
    	 if (results!=null){
    		 for (int i=0;i<results.length;i++){
    			 consulta=(String) results[0][0];
    		 }
    	 }
    	 return consulta;
     }
     
     
     public Object[][] getProveedor(String idproveedor){
 		String[] parameters=new String[]{
 				idproveedor
 				};
 		String q="";
 		q+="select M.codigo, ";
 		q+=" M.descripcion, ";
 		q+=" c.consulta, ";
 		q+="c.linea, ";
 		q+="c.odbc_name, ";
 		q+="c.politica_default, ";
 		q+="c.automatico, ";
 		q+="isnull(c.clase,'sun.jdbc.odbc.JdbcOdbcDriver') "; 
 		q+="FROM MA_CUENTAS M LEFT OUTER JOIN "; 
 		q+="b_configuracion_catalogos c   ";
 		q+="ON M.CODIGO = c.idproveedor "; 
 		q+="where M.codigo like '"+idproveedor+"' ";
 		//System.out.println(">"+q);
 		Object[][] results=getResults(q);
 		return results;
 		
 	}
    public String getQueryCatalogo(String idproveedor){
    	String q="";
    	Object[][] results=this.getProveedor(idproveedor);
    	if (results!=null){
    		if (results.length>0){
    			q=(String) results[0][2];
    		}
    	}
    	return q;
    	
    }
     
     public String getPolitica(String idprov){
    	 String consulta="";
    	 String[] parameters=new String[]{
    			 idprov
    	 };
    	 String q=this.getQuery("politica", parameters);
    	 Object[][] results=getResults(q);
    	 if (results!=null){
    		 for (int i=0;i<results.length;i++){
    			 consulta=(String) results[0][0];
    		 }
    	 }
    	 return consulta;
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
      	q+="where c.idproveedor like '"+idproveedor+"' and v.cuenta_actualizacion like '"+idproveedor+"' and c.tc like '"+tc+"' and c.idcomprobante like '"+idcomprobante+"' ";
      	return q;
      }
     
     public String updatefechas(String idproveedor,String pol){
     	String q="";
     	q+="update b_codigos set ultimo_upd=getdate()  ";
     	q+="from  ";
     	q+="b_codigos c join b_alias a "; 
     	q+="on c.idcodigo=a.idcodigo and c.lineaproveedor=a.lineaproveedor and c.idproveedor=a.idproveedor "; 
     	q+="join v_ma_articulos v on a.idarticulo=v.idarticulo  ";
     	q+="left outer join v_ta_politicaprecios p on (case when isnull(c.politicaprecio,'') like '' then isnull(v.politicaprecios,'"+pol+"') else v.politicaprecios end)=p.codigo "; 
     	q+="where c.idproveedor like '"+idproveedor+"' and v.cuenta_actualizacion like '"+idproveedor+"'  ";
     	q+="and abs(c.precio5-v.precio5)<=0.01 ";
     	return q;
     }
     
     
     public String _updateArticle(String idarticulo,String idproveedor,String pol,String precio,String iduser){
     	String q="";
     	q+="update v set v.precio5="+precio+", v.costo=p.mcosto*"+precio+",v.precio2=p.mclase2*p.mcosto*"+precio+", ";
     	q+=" v.iduser_actualizador='"+iduser+"',actualizacion=getdate() ";
     	q+="from v_ma_articulos v  ";
     	q+="left outer join v_ta_politicaprecios p on (case when isnull(v.politicaprecios,'') like '' then '"+pol+"' else v.politicaprecios end)=p.codigo "; 
     	q+="where v.cuentaproveedor like '"+idproveedor+"'  ";
     	q+="and v.idarticulo like '"+idarticulo+"' ";
     	return q;
     }
     
    
     
     public boolean InsertCatalog(String idproveedor,String politica,String consulta,String linea,String odbc,String clase){
    	 String q=this.getInsertCatalogQuery(idproveedor, politica, consulta,linea,odbc,clase);
    	 this.clearBatch();
    	 this.addBatch(q);
    	 return this.executeBatch();
     }
     
     public boolean UpdateCatalog(String idproveedor,String politica,String consulta,String linea,String odbc,String clase){
    	 String q=this.getUpdate(consulta, linea, odbc, politica, idproveedor,clase);
    	 this.clearBatch();
    	 this.addBatch(q);
    	 return this.executeBatch();
     }
     
 	private String getUpdate(String consulta,String linea,String odbc,String politica,String idproveedor,String clase){
		String q="";
		q+="update b_configuracion_catalogos ";
		q+="set ";
		q=q+"consulta='"+consulta+"',";
		q=q+"linea='"+linea+"',";
		q=q+"odbc_name='"+odbc+"',";
		q=q+"politica_default='"+politica+"', ";
		q=q+"clase='"+clase+"' ";
		q=q+" where idproveedor = '"+idproveedor+"' ";
		return q;
	}
 	
     public String getInsertCatalogQuery(String idproveedor,String politica,String consulta,String linea,String odbc,String clase){
    	 String q="";
    	 q+="insert into b_configuracion_catalogos (idproveedor,politica_default,consulta,linea,odbc_name,clase) ";
    	 q+="values ('"+idproveedor+"','"+politica+"','"+consulta+"','"+linea+"','"+odbc+"','"+clase+"')";
    	 q+="";
    	 return q;
     }
     
     public boolean check_catalog(String idproveedor){
    	 boolean exist=false;
    	 String q="select idproveedor from b_configuracion_catalogos where idproveedor like '"+idproveedor+"'";
    	 Object[][] results=this.getResults(q);
    	 if (results!=null){
    		 if (results.length>0){
    			exist=true; 
    		 }
    	 }
    	 return exist;
     }
     
     public String existeIdcomprobanteQuery(String idcomprobante){
    	String q="";
    	q+=" select top 1 * from b_global_proveedor ";
    	q+=" where idcomprobante like '"+idcomprobante+"' ";
    	
    	return q;
     } 
     
     public boolean existeIdcomprobante(String idcomprobante){
    	 boolean exist=false;
    	 Object[][] results=this.getResults(this.existeIdcomprobanteQuery(idcomprobante));
    	 if (results!=null){
    		 if (results.length>0){
    			 exist=true;		 
    		 }
    	 }
    	 
    	 return exist;
     }
     
 	public Object[][] getCodigos(String idproveedor,String linea){
		return this.getResults(this.getCodigosQuery(idproveedor,linea));
		
	}
 	
	public boolean updateTC(String tc){
		String q="";
		q+="update b_ta_cpte set x_ultimo_nro = x_ultimo_nro + 1 where codigo like '"+tc+"'";
		this.clearBatch();
		this.addBatch(q);
		return this.executeBatch();
	}	
	
	public boolean InsertFTP(String idactualizacion,String idproveedor){
		boolean ok=false;
		String q=this.getInsertFTP(idactualizacion, idproveedor);
		this.clearBatch();
		this.addBatch(q);
		ok=!this.executeBatch();
		return ok;
	}
	
	public String getInsertFTP(String idactualizacion,String idproveedor){
		String q="insert into b_actualizacion_ftp (idactualizacion,idproveedor,fecha) ";
		q+=" values ('"+idactualizacion+"','"+idproveedor+"',getdate()) ";
		return q;
	}
	
	public Object[][] getUpdates(){
		String q="select top 100 max(ftp.idactualizacion),max(ftp.fecha),ftp.idproveedor,max(m.descripcion) ";
		q+=" from b_actualizacion_ftp ftp left outer join ma_cuentas m ";
		q+=" on ftp.idproveedor=m.codigo ";
		q+=" group by ftp.idproveedor  ";
		q+=" order by max(id) desc ";
		return this.getResults(q);
	}
 	
	private String getCodigosQuery(String idproveedor,String linea){
		String q="";
		q+="select idcodigo,descripcion,lineaproveedor,precio5 ";
		q+="from b_codigos where idproveedor like '"+idproveedor +"' ";
		if (linea.compareTo("")!=0){
			q+="and lineaproveedor like '"+linea+"' ";	
		}
		q+="and ultimo_upd is not null ";
		q+="order by lineaproveedor,idcodigo ";
		System.out.println("//"+q);
		return q;
	}
	
 	
     public String updateFecha(String idcomprobante){
    	 
    	 String q="";
    	 q+="update b_global set Fecha_inicio=getdate() ";
    	 q+="where idcomprobante like '"+idcomprobante+"'";
    	 
    	 return q;
     }
     public String insertIdcomprobante(String idcomprobante, String iduser, String fecha_inicio){
    	 String q="";
    	 q+="insert into b_global (idcomprobante, iduser, fecha_inicio) ";
    	 q+="values ('"+idcomprobante+"','"+iduser+"',getdate())";
    	 return q;
     }
     
     public String updateIdcomprobante(String idcomprobante){
    	 String q="";
    	 q+="update b_global set Finalizado=1";
    	 q+="where idcomprobante like '"+idcomprobante+"'";
    	 return q;
     }
     
     public String insertIdcomprobante(String idcomprobante,String idproveedor){
    	 String q="";
    	 q+="insert into b_global_proveedor (idcomprobante, idproveedor) ";
    	 q+="values ('"+idcomprobante+"','"+idproveedor+"')";
    	 return q;
     }
     
     public String updateIdcomprobante(String idcomprobante,String idproveedor){
    	 String q="";
    	 q+="update b_global_proveedor set fecha_inicio= getdate()";
    	 q+="where idcomprobante like '"+idcomprobante+"' and idproveedor like '"+idproveedor+"'";
    	 return q;
     }
     
     public String finalizado(String idcomprobante,String idproveedor){
    	 String q="";
    	 q+="update b_global_proveedor set fecha_fin=getdate(), ";
    	 q+="finalizado=1 ";
    	 q+="where idcomprobante like '"+idcomprobante+"' and idproveedor like '"+idproveedor+"'";
    	 return q;
    }
     
     public String getIdomprobante(){
    	 String q="";
    	 q+="select top 100 idcomprobante, fecha, finalizado ";
    	 q+="order by idcomprobante desc.";
    	 return q;
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


}
