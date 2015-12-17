package aplicacion.sistema.update.logic;

import aplicacion.modelo.logic.Data;

public class _Data extends Data {
	String tc="bupd";
	
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
			//System.out.println(e.getMessage());
		}
		return c;
	}
	
	private String getProximoPG_Ceros(String tc){
		String c="";
		int i=this.getProximoPG(tc);
		String aux=""+i;
		while (aux.length()<4){
			aux="0"+aux;
		}
		c=aux;
		return c;
	}
	
	public boolean updateTC(String tc){
		String q="";
		q+="update b_ta_cpte set x_ultimo_nro = x_ultimo_nro + 1 where codigo like '"+tc+"'";
		this.clearBatch();
		System.out.println(q);
		this.addBatch(q);
		return this.executeBatch();
		
	}
	
	public boolean marcar_operacion_ftp_exitosa(String idversion){
		String q="";
		q+="update b_version set ftp=1 where idversion like '"+idversion+"'";
		this.clearBatch();
		System.out.println(q);
		this.addBatch(q);
		return this.executeBatch();
	}
	
	public String insert_update(String id,String comentario){
		String q="insert into b_version (idversion,comentario,fecha) values (";
		q+="'"+id+"',";
		q+="'"+comentario+"',";
		q+="getdate())";
		return q;
	}
	
	public String insert_update_file(String id,String folder,String file){
		String q="insert into b_version_file (idversion,folder,[file]) values (";
		q+="'"+id+"',";
		q+="'"+folder+"',";
		q+="'"+file+"')";
		return q;
	}
	
	public String[] getOldFilesPath(String version,String file){
		String q="";
		q+="";
		q+="select top 20 idversion,folder from b_version_file "; 
		q+="where [file] like '"+file+"' and idversion < '"+version+"' ";
		q+="order by id desc ";
		String[] paths=null;
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>2){
				paths=new String[results.length-2];
				
				for (int i=2;i<results.length;i++){
					String idversion=results[i][0].toString();
					String folder=results[i][1].toString();
					paths[i-2]=idversion+"/";
				}
			}
		}
		return paths;
	}
	public boolean exist(String codigo,String lineaproveedor,String idproveedor){
		String q="";
		Object[][] results=getResults(q);
		boolean b=false;
		if (results!=null){
			if (results.length>0){
				b=true;
			}
		}
		return b;
	}
	
	public Object[][] getUpdates(){
		String q="";
		q+="select top 30 idversion,fecha,comentario from b_version order by idversion desc ";
		return this.getResults(q);
	}
	
	public Object[][] getUpdatesFiles(String idversion){
		String q="";
		q+="select folder,[file] ";  
		q+="from b_version_file ";
		q+="where idversion like '"+idversion+"' ";
		q+=" ";
		return this.getResults(q);
	}
	
}
