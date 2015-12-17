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
 
private aplicacion.herramientas.java.visualizadores.Articulo vArticulo=null;
public void buscarArticulo(JTextField tx) {
	if (vArticulo!=null){
		vArticulo.close();
	}
	vArticulo=new aplicacion.herramientas.java.visualizadores.Articulo(this.getConstructor().getConnectionHandler());
	vArticulo.setIdproveedor(frame.get_txt_idproveedor().getText());
	int n = vArticulo.buscarArticulo(tx);
	if (n == 0) {
			aviso("no hay articulos con ese codigo");
	}
}
*/
public class Articulo extends Generico{
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

	public Articulo(Constructor C){
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
		c.setNombre("v.idarticulo");
		c.setAlias("idarticulo");
		c.setColumnField(tx);
		c.setWidth(100);
		logic.addColumn(c);

		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("v.descripabrev");
		c.setAlias("linea");
		c.setWidth(140);
		logic.addColumn(c);

		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("v.descripcion");
		c.setAlias("descripcion");
		c.setWidth(240);
		logic.addColumn(c);
		
		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		if (publico) {
			c.setNombre("v.precio2");
			c.setAlias("publico $");
		}else{
			c.setNombre("v.precio5");
			c.setAlias("lista $");
		}
		c.setWidth(120);
		logic.addColumn(c);

		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("isnull(s.cantidadud,0)");
		c.setAlias("cantidad");
		c.setWidth(70);
		logic.addColumn(c);
		String txt = tx.getText();
		String linea = "";
		if (txt.contains("+")) {
			linea = txt.substring(0, txt.indexOf("+"));
			txt = txt.substring(txt.indexOf("+") + 1, txt.length());
		}

		f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
		f.setNombre("replace(replace(replace(replace(substring(v.idarticulo,3,len(v.idarticulo)),'-',''),'/',''),'.',''),' ','')");
		System.out.println("valor"+txt);
		String valor=txt;
		System.out.println("valor"+valor);
		valor=valor.replaceAll("/", "");
		System.out.println("valor"+valor);
		valor=valor.replaceAll("-", "");
		System.out.println("valor"+valor);
		//valor=valor.replaceAll(".", "");
		System.out.println("valor"+valor);
		f.setValor(valor);
		logic.addFilter(f);

		if (linea.compareTo("") != 0) {
			f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
			f.setNombre("v.descripabrev");
			f.setAlias("linea");
			f.setValor(linea);
			logic.addFilter(f);
		}
		f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
		f.setNombre("v.cuentaproveedor");
		f.setValor(idproveedor);
		logic.addFilter(f);

		logic
				.setFromTable(" V_MA_ARTICULOS v LEFT OUTER JOIN Stk_Stock_UDKG s ON  v.IDARTICULO = s.IdArticulo ");

		logic.setTop(100);
		logic.setOrderby("v.idarticulo");
		String restriction="";
		if (this.suspendidoc){
			restriction+=" isnull(v.suspendidoc,0)=0 ";
		}
		if (this.suspendidov){
			if (restriction.length()>0){
				restriction+=" and ";
			}
			restriction+=" isnull(v.suspendidov,0)=0 ";
		}
		if (restriction.length()>0){
			logic.setRestriction(restriction);	
		}
		logic.setTitle("Buscador de Articulo");
		
		int n= logic._loadoptions();
		
		return n;

		
	}
	
	
	
}
