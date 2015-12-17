package aplicacion.inventario.politica.logic;

import aplicacion.herramientas.conexion.creator.interfaces._Interface;
import aplicacion.modelo.logic.Data;

public class _Data extends Data {

	private String getPoliticaQuery(String codigo){
		String q="";
		q+="select "; 
		q+="codigo,descripcion,fcosto,fclase2 "; 
		q+="from v_ta_politicaprecios ";
		q+="where codigo like '"+codigo+"'";
		return q;	
	}
	public Object[][] getPolitica(String codigo){
		String q=this.getPoliticaQuery(codigo);
		return getResults(q);
	}
	public boolean exist(String codigo){
		boolean existe=false;
		String q=this.getPoliticaQuery(codigo);
		Object[][] tmp=getResults(q);
		if (tmp!=null){
			if (tmp.length>0){
				existe=true;
			}
		}
		return existe;
	}
	
	public String getArticulosQuery(String idpolitica){
		String q="";
		q+="select idarticulo,isnull(precio5,0) ";
		q+="from v_ma_articulos ";
		q+="where politicaprecios like '"+idpolitica+"'";
		q+="";
		return q;
	}

	private String getUpdateArticulo(String idarticulo,double costo,double publico){
		String q="";
		q+="update v_ma_articulos ";
		q+="set costo="+costo+",";
		q+="precio2="+publico;
		q+="where idarticulo like '"+idarticulo+"' ";
		return q;
	}
	
	public Object[][] getArticulos(String idpolitica){
		String q=this.getArticulosQuery(idpolitica);
		return getResults(q);
	}
	
	public void actualizar_articulo(String idarticulo,double p5,double mcosto,double mpublico){
		double costo=p5*mcosto;
		double publico=costo*mpublico;
		String q=this.getUpdateArticulo(idarticulo, costo, publico);
		clearBatch();
		addBatch(q);
		executeBatch();
	}
	
	public Object[][] getListadePoliticas(){
		String q="select convert(integer,ltrim(codigo)) ";
		q+="from v_ta_politicaprecios ";
		q+="order by convert(integer,ltrim(codigo)) ";
		System.out.println(q);
		Object[][] tmp=this.getResults(q);
		return tmp;
	}
	

	public String getUpdate(String idpolitica,String descripcion,String fcosto,String fpublico,double mcosto,double mpublico){
		String q="";
		q="update v_ta_politicaprecios set ";
		q+=" descripcion='"+descripcion+"',";
		q+=" fcosto='"+fcosto+"',";
		q+=" fclase2='"+fpublico+"',";
		q+=" mcosto="+mcosto+",";
		q+=" mclase2="+mpublico+"";
		q+=" where codigo like '"+idpolitica+"' ";
		return q;
	}

	public String getInsert(String idpolitica,String descripcion,String fcosto,String fpublico,double mcosto,double mpublico){
		String q="";
		q="insert into  v_ta_politicaprecios  ";
		q+="(codigo,descripcion,fcosto,fclase2,mcosto,mclase2) ";
		q+=" values (";
		q+="'"+idpolitica+"', ";
		q+="'"+descripcion+"',";
		q+="'"+fcosto+"',";
		q+="'"+fpublico+"',";
		q+=""+mcosto+",";
		q+=""+mpublico+"";
		q+=")";
		return q;
	}
	
	

	public boolean check_politica(String codigo){
	Object[][] results=this.getPolitica(codigo);
	boolean exist=false;
	if (results!=null){
		if (results.length>0){
			exist=true;
		}
	}
	return exist;
	}
}
