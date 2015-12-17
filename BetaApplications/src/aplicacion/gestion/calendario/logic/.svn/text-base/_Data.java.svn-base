package aplicacion.gestion.calendario.logic;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import aplicacion.modelo.logic.Data;

public class _Data extends Data{

	public String getVariablesQuery(String idtablero,String fecha){
		String q="";
		q+="select * from b_tablero_calendario where idtablero like '"+idtablero+"' and fecha between '"+fecha+"' and '"+fecha+"'";
		System.out.println(q);
		return q;
	}
	

	public String getBackground(int row,String col,String idtablero){
		String q="";
		q+="select background from b_tablero_variables where fila like '"+row+"' and columna like '"+col+"' and idtablero like '"+idtablero+"'";
		System.out.println(q);
		String background="255,255,255";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				background=(String) results[0][0];
			}
		}
		return background;
	}
	
	public String[] getTableros(){
		String q="select idtablero from b_tablero";
		Object[][] results=this.getResults(q);
		String[] tableros=null;
		if (results!=null){
			if (results.length>0){
				tableros=new String[results.length];
				for (int i=0;i<results.length;i++){
					tableros[i]=(String)results[i][0];
				}
			}
		}
		return tableros;
	}
	
	public boolean guardar(String value,String row,String column,String foreground,String background,String idtablero,String detalle,String nombre,String grafica){
		boolean ok=false;
		if (this.exist(row, column,idtablero)){
			ok=this.update(value, row, column,foreground,background,idtablero,detalle,nombre,grafica);
		}else{
			ok=this.insert(value, row, column,foreground,background,idtablero,detalle,nombre,grafica);
		}
		return ok;
	}
	public String getNombre(String row,String column,String idtablero){
		String nombre="";
		String q="";
		q+="select nombre from b_tablero_variables where fila like '"+row+"' and columna like '"+column+"' and idtablero like '"+idtablero+"'";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				nombre=(String) results[0][0];
			}
		}
		return nombre;
	}
	public String getFormula(String row,String column,String idtablero){
		String formula="";
		String q="";
		q+="select formula from b_tablero_variables where fila like '"+row+"' and columna like '"+column+"' and idtablero like '"+idtablero+"'";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				formula=(String) results[0][0];
			}
		}
		return formula;
	}
	public String getFormulaDetalle(String row,String column,String idtablero){
		String formula="";
		String q="";
		q+="select formula_detalle from b_tablero_variables where fila like '"+row+"' and columna like '"+column+"' and idtablero like '"+idtablero+"'";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				formula=(String) results[0][0];
			}
		}
		return formula;
	}
	
	public String getFormulaGrafica(String row,String column,String idtablero){
		String formula="";
		String q="";
		q+="select formula_grafica from b_tablero_variables where fila like '"+row+"' and columna like '"+column+"' and idtablero like '"+idtablero+"'";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				formula=(String) results[0][0];
			}
		}
		return formula;
	}
	public boolean insert(String value,String row,String column,String foreground,String background,String idtablero,String detalle,String nombre,String grafica){
		String q="";
		q+="insert into  b_tablero_variables (formula,fila,columna,foreground,background,idtablero,formula_detalle,nombre,formula_grafica) values ('"+value+"','"+row+"','"+column+"','"+foreground+"','"+background+"','"+idtablero+"','"+detalle+"','"+nombre+"','"+grafica+"') ";
		this.clearBatch();
		this.addBatch(q);
		return !this.executeBatch();
	}
	
	public boolean update(String value,String row,String column,String foreground,String background,String idtablero,String detalle,String nombre,String grafica){
		String q="";
		q+="update b_tablero_variables set formula='"+value+"',foreground='"+foreground+"',background='"+background+"',formula_detalle='"+detalle+"',formula_grafica='"+grafica+"',nombre='"+nombre+"' where fila like '"+row+"' and columna like '"+column+"' and idtablero like '"+idtablero+"' ";
		this.clearBatch();
		this.addBatch(q);
		return !this.executeBatch();
	}
	
	public boolean exist(String row,String column,String idtablero){
		boolean ok=false;
		String q="";
		q+="select * from b_tablero_variables where fila like '"+row+"' and columna like '"+column+"' and idtablero like '"+idtablero+"'";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				ok=true;
			}
		}
		return ok;
	}
	
	public String getForeground(int row,String col,String idtablero){
		String q="";
		q+="select foreground from b_tablero_variables where fila like '"+row+"' and columna like '"+col+"' and idtablero like '"+idtablero+"'";
		System.out.println(q);
		String foreground="0,0,0";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				foreground=(String) results[0][0];
			}
		}
		return foreground;
	}
	
	public boolean getLaborable(String idtablero,String fecha){
		boolean laborable=true;
		Object[][] results=this.getResults(getVariablesQuery(idtablero,fecha));
		if (results!=null){
			if (results.length>0){
				laborable=false;
			}
		}
		return laborable;
	}
	
	public void setLaborable(String idtablero,String fecha,boolean laborable){
		String q="delete from b_tablero_calendario where idtablero like '"+idtablero+"' and fecha between '"+fecha+"' and '"+fecha+"'";
		if (laborable){
			q="insert into b_tablero_calendario (idtablero,fecha) values ('"+idtablero+"','"+fecha+"') ";
		}
		this.clearBatch();
		System.out.println(q);
		this.addBatch(q);
		this.executeBatch();
	}
}
