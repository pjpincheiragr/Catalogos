package aplicacion.herramientas.java.buscadores;

import javax.swing.JTable;
import javax.swing.JTextField;
import java.util.*;

import aplicacion.herramientas.java.sortableselector.logic.Filtro;
import aplicacion.herramientas.java.sortableselector.logic.columna;
import aplicacion.modelo.constructor.Constructor;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.herramientas.conexion.*;
/**
 * Aplicacion para evitar tener q crear a cada rato unas 100 lineas de codigo al pedo. :-)
 * @author Agustin
 *
 */
/* Como utilizarlo? pegar este codigo en la aplicacion
private aplicacion.herramientas.java.buscadores.Dynamic bDynamic=null;
public void BuscarDynamic(JTextField ext) {
	 if (bDynamic!=null){
		 bDynamic.close();
	 }
 bDynamic=new aplicacion.herramientas.java.buscadores.Dynamic(this.getConstructor());
 bDynamic.Buscar(ext);
} 
 */


public class Dynamic extends Generico{
	private List<columna> columnas=null;
	private List<Filtro> filtros=null;
	private String table="";
	private String idconector="";
	private int top=0;
	private boolean limit=false;
	
	public boolean isLimit() {
		return limit;
	}



	public void setLimit(boolean limit) {
		this.limit = limit;
	}



	public int getTop() {
		return top;
	}



	public void setTop(int top) {
		this.top = top;
	}



	public String getIdconector() {
		return idconector;
	}

		

	public void setIdconector(String idconection) {
		this.idconector = idconection;
	}


	public String getTable() {
		return table;
	}


	public void setTable(String table) {
		this.table = table;
	}


	public String getOrder() {
		return order;
	}


	public void setOrder(String order) {
		this.order = order;
	}


	public String getGroupby() {
		return groupby;
	}


	public void setGroupby(String groupby) {
		this.groupby = groupby;
	}


	public String getRestriction() {
		return restriction;
	}


	public void setRestriction(String restriction) {
		this.restriction = restriction;
	}
	private String order="";
	private String groupby="";
	private String restriction="";
	public Dynamic(Constructor C){
		super(C);
		columnas=new ArrayList<columna>();
		filtros=new ArrayList<Filtro>();
	}
	
	
	public void initializeConstructor() {
		C = new aplicacion.herramientas.java.sortableselector.constructor._Constructor() {
			@Override
			protected void initialize_logic() {
				_logic = new aplicacion.herramientas.java.sortableselector.logic._Logic() {
					@Override
					public void Close(JTable table, int row) {
						super.Close(table, row);
					}
				};
			}
		};
	}
	public void AddColumn(columna c){
		columnas.add(c);
	}
	public void AddFiltro(Filtro f){
		filtros.add(f);
	}
	public void initializeLogic(JTextField tx){
		aplicacion.herramientas.java.sortableselector.logic._Logic logic = (aplicacion.herramientas.java.sortableselector.logic._Logic) C
		.getLogic();
		logic.setCaller(tx);

		for (int i=0;i<columnas.size();i++){
			logic.addColumn(columnas.get(i));	
		}
		
		logic.addFromTable(table);
		for (int i=0;i<filtros.size();i++){
			logic.addFilter(filtros.get(i));
		}
		if (groupby.compareTo("")!=0){
			logic.addGroup(groupby);	
		}
		if (order.compareTo("")!=0){
			logic.addOrder(order);	
		}
		if (restriction.compareTo("")!=0){
			logic.addRestriction(restriction);	
		}
		
		if (limit){
			logic.setLimit(true);
		}
		if (idconector.compareTo("")!=0){
			logic.setIdconector(idconector);
			logic.getData().createODBCConnection(idconector);
		}
		if (top>0){
			logic.setTop(top);
		}
		logic.init();
	}
}
