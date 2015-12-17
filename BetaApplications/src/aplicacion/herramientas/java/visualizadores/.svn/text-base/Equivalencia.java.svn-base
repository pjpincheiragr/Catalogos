package aplicacion.herramientas.java.visualizadores;

import javax.swing.JTextField;
import java.awt.Rectangle;
import aplicacion.modelo.constructor.*;
import aplicacion.herramientas.conexion.ConnectionHandler;
/**
 * Aplicacion para evitar tener q crear a cada rato unas 100 lineas de codigo al pedo. :-)
 * @author Agustin
 *
 */
/* Como utilizarlo? pegar este codigo en la aplicacion
 
private aplicacion.herramientas.java.visualizadores.Equivalencia vEquivalencia=null;
public void buscarEquivalencia(JTextField tx) {
	if (vEquivalencia!=null){
		vEquivalencia.close();
	}
	vEquivalencia=new aplicacion.herramientas.java.visualizadores.Equivalencia(this.getConstructor().getConnectionHandler());
	vEquivalencia.setIdproveedor(frame.get_txt_idproveedor().getText());
	int n = vEquivalencia.buscarEquivalencia(tx);
	if (n == 0) {
			aviso("no hay Equivalencias con ese codigo");
	}
}
*/
public class Equivalencia extends Generico{
	private boolean suspendidov=false;
	private boolean suspendidoc=false;
	public boolean isSuspendidov() {
		return suspendidov;
	}

	public void setSuspendidov(boolean suspendidov) {
		this.suspendidov = suspendidov;
	}

	public boolean isSuspendidoc() {
		return suspendidoc;
	}

	public void setSuspendidoc(boolean suspendidoc) {
		this.suspendidoc = suspendidoc;
	}
private boolean publico=false;
public boolean isPublico() {
	return publico;
}

public void setPublico(boolean publico) {
	this.publico = publico;
}

private String idproveedor="";	

	public String getIdproveedor() {
		return idproveedor;
	}

	public void setIdproveedor(String idproveedor) {
		this.idproveedor = idproveedor;
	}

	public Equivalencia(Constructor C){
		super(C);
	}
	
	
	public void initializeConstructor(){
		C = new aplicacion.herramientas.java.visualselector.constructor._Constructor();
	}
	
	public int initializeLogic(JTextField tx) {
		String valor=tx.getText();
		aplicacion.herramientas.java.visualselector.logic._Logic logic = (aplicacion.herramientas.java.visualselector.logic._Logic) C
				.getLogic();
		logic.setCaller(tx);
		aplicacion.herramientas.java.visualselector.logic.Columna c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		aplicacion.herramientas.java.visualselector.logic.Filtro f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("m.idarticulo");
		c.setAlias("articulo");
		c.setColumnField(tx);
		c.setWidth(100);
		logic.addColumn(c);

		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("m.descripcion");
		c.setAlias("descripcion");
		c.setWidth(140);
		logic.addColumn(c);

		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("m.descripabrev");
		c.setAlias("linea");
		c.setWidth(140);
		logic.addColumn(c);

		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("s.cantidadud");
		c.setAlias("stock");
		c.setWidth(240);
		logic.addColumn(c);
		
	
		

		
		logic.setFromTable(" b_articulos_equivalencias e left outer join v_ma_articulos m on (e.idarticulo1=m.idarticulo or e.idarticulo2=m.idarticulo) LEFT OUTER JOIN Stk_Stock_UDKG s on m.idarticulo=s.idarticulo ");

		logic.setTop(100);
		logic.setOrderby("(case when m.idarticulo like '"+valor+"' then 0 else 1 end),m.idarticulo ");
		String restriction="(e.idarticulo1 like '"+valor+"' or e.idarticulo2 like '"+valor+"')";
		if (restriction.length()>0){
			logic.setRestriction(restriction);	
		}
		
		logic.setTitle("Buscador de Equivalencia");
		
		int n= logic._loadoptions();
		
		return n;

		
	}
	
	
	
}
