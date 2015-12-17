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
 
private aplicacion.herramientas.java.visualizadores.Host vHost=null;
public void buscarHost(JTextField tx) {
	if (vHost!=null){
		vHost.close();
	}
	vHost=new aplicacion.herramientas.java.visualizadores.Host(this.getConstructor().getConnectionHandler());
	int n = vHost.Buscar(tx);
	if (n == 0) {
			aviso("no hay Hosts con ese codigo");
	}
}
*/
public class Host extends Generico{

	private String idHost="";	
	
	public String getIdHost() {
		return idHost;
	}

	public void setIdHost(String idHost) {
		this.idHost = idHost;
	}

	public Host(Constructor C){
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
		c.setNombre("idhost");
		c.setAlias("idhost");
		c.setColumnField(tx);
		c.setWidth(120);
		logic.addColumn(c);
		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("mac");
		c.setAlias("mac");
		c.setWidth(120);
		logic.addColumn(c);
		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("os");
		c.setAlias("sistema op");
		c.setColumnField(tx);
		c.setWidth(120);
		logic.addColumn(c);
		logic.setFromTable("b_host ");
		

		f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
		f.setNombre("idhost");
		f.setValor(tx.getText());
		logic.addFilter(f);

		logic.setTop(100);
		
		logic.setOrderby("idhost");

		logic.setTitle("Buscador de Host");
		
		int n= logic._loadoptions();
		
		return n;

	}
	
}
