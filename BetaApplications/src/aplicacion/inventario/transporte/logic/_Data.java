package aplicacion.inventario.transporte.logic;

import aplicacion.herramientas.conexion.creator.interfaces._Interface;
import aplicacion.modelo.logic.Data;

public class _Data extends Data {

	private String getTransporteQuery(String codigo){
		String q="";
		q+="select "; 
		q+="idtransporte,nombre "; 
		q+="from transportes ";
		q+="where idtransporte like '"+codigo+"'";
		return q;	
	}
	public Object[][] getTransporte(String codigo){
		String q=this.getTransporteQuery(codigo);
		return getResults(q);
	}
	

	

	public String getUpdate(String idtransporte,String descripcion){
		String q="";
		q="update transportes set ";
		q+=" nombre='"+descripcion+"' ";
		q+=" where idtransporte like '"+idtransporte+"' ";
		return q;
	}


	public String getInsert(String idtransporte,String descripcion){
		String q="";
		q="insert into  transportes  ";
		q+="(idtransporte,nombre) ";
		q+=" values (";
		q+="'"+idtransporte+"', ";
		q+="'"+descripcion+"'";
		q+=")";
		return q;
	}
	
	
	
	public String getProximoTransporte(){
		String q="select top 1 rtrim(ltrim(idtransporte)) from transportes order by idtransporte desc ";
		String idtransporte="";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				idtransporte=(String) results[0][0];
			}
		}
		int _id=0;
		try {
			_id=new Integer(idtransporte);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		_id++;
		idtransporte=""+_id;
		return idtransporte;
	}

	public boolean check_transporte(String codigo){
	Object[][] results=this.getTransporte(codigo);
	boolean exist=false;
	if (results!=null){
		if (results.length>0){
			exist=true;
		}
	}
	return exist;
	}
}
