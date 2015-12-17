package aplicacion.herramientas.java.visualizadores;

import java.awt.Rectangle;

import javax.swing.JTextField;
import aplicacion.modelo.constructor.*;
import aplicacion.herramientas.conexion.ConnectionHandler;
/**
 * Aplicacion para evitar tener q crear a cada rato unas 100 lineas de codigo al pedo. :-)
 * @author Agustin
 *
 */
/* Como utilizarlo? pegar este codigo en la aplicacion
 
private aplicacion.herramientas.java.visualizadores.Query vQuery=null;
public void buscarQuery(JTextField tx) {
	if (vQuery!=null){
		vQuery.close();
	}
	vQuery=new aplicacion.herramientas.java.visualizadores.Query(this.getConstructor().getConnectionHandler());
	int n = vQuery.Buscar(tx);
	if (n == 0) {
			aviso("no hay Querys con ese codigo");
	}
}
*/
public class Query extends Generico{

	private String idQuery="";	
	
	public String getIdQuery() {
		return idQuery;
	}

	public void setIdQuery(String idQuery) {
		this.idQuery = idQuery;
	}

	public Query(Constructor C){
		super(C);
	}
	
	public void initializeConstructor(){
		C = new aplicacion.herramientas.java.visualselector.constructor._Constructor();
	}
	public int initializeLogic(JTextField tx) {
	
		aplicacion.herramientas.java.visualselector.logic._Logic logic = (aplicacion.herramientas.java.visualselector.logic._Logic) C
				.getLogic();
		logic.setCaller(tx);
		aplicacion.herramientas.java.visualselector.logic.Columna c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		aplicacion.herramientas.java.visualselector.logic.Filtro f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
		c = new aplicacion.herramientas.java.visualselector.logic.Columna();

		c.setNombre("idquery");
		c.setAlias("idquery");
		c.setColumnField(tx);
		c.setWidth(120);
		logic.addColumn(c);
		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("idproveedor");
		c.setAlias("idproveedor");
		c.setWidth(120);
		logic.addColumn(c);
		logic.setFromTable("b_query ");
		

		f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
		f.setNombre("idquery");
		f.setValor(tx.getText());
		logic.addFilter(f);

		logic.setTop(100);
		
		logic.setOrderby("idquery");

		logic.setTitle("Buscador de Query");
		
		int n= logic._loadoptions();
		
		return n;

	}
	
}
