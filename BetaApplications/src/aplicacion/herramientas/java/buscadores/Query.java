package aplicacion.herramientas.java.buscadores;

import javax.swing.JTable;
import javax.swing.JTextField;

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
private aplicacion.herramientas.java.buscadores.Query bQuery=null;
public void BuscarQuery(JTextField ext) {
	 if (bQuery!=null){
		 bQuery.close();
	 }
 bQuery=new aplicacion.herramientas.java.buscadores.Query(this.getConstructor());
 bQuery.Buscar(ext);
} 
 */
public class Query extends Generico{

	
	
	public Query(Constructor C){
		super(C);
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
	
	public void initializeLogic(JTextField tx){
		aplicacion.herramientas.java.sortableselector.logic._Logic logic = (aplicacion.herramientas.java.sortableselector.logic._Logic) C
		.getLogic();
		logic.setCaller(tx);

		columna c = new columna();
		Filtro f = new Filtro();
		
		c.setNombre("idquery");
		c.setAlias("Idquery");
		c.setWidth(120);
		c.setMaster(true);
		c.setColumnField(tx);
		logic.addColumn(c);

		c = new columna();
		c.setNombre("idproveedor");
		c.setAlias("idproveedor");
		c.setWidth(120);
		logic.addColumn(c);

		logic.addFromTable("b_query");
		f = new Filtro();
		f.setNombre("idquery");
		f.setAlias("Idquery");
		f.setWidth(200);
		f.setFocus(true);
		logic.addFilter(f);

		f = new Filtro();
		f.setNombre("idproveedor");
		f.setAlias("idproveedor");
		f.setWidth(200);
		f.setFocus(true);
		logic.addFilter(f);
		
		logic.addOrder("idquery");
		
		logic.init();
	}
}
