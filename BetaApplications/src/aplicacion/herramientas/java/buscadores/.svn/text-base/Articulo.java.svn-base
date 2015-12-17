package aplicacion.herramientas.java.buscadores;

import javax.swing.JTable;
import javax.swing.JTextField;

import aplicacion.herramientas.java.sortableselector.logic.Filtro;
import aplicacion.herramientas.java.sortableselector.logic.columna;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.herramientas.conexion.*;
/**
 * Aplicacion para evitar tener q crear a cada rato unas 100 lineas de codigo al pedo. :-)
 * @author Agustin
 *
 */
/* Como utilizarlo? pegar este codigo en la aplicacion
private aplicacion.herramientas.java.buscadores.Articulo bArticulo=null;
public void BuscarArticulo(JTextField ext) {
	 if (bArticulo!=null){
		 bArticulo.close();
	 }
 bArticulo=new aplicacion.herramientas.java.buscadores.Articulo(this.getConstructor());
 bArticulo.Buscar(ext);
} 
 */
import aplicacion.modelo.constructor.*;
public class Articulo extends Generico{
	
	public Articulo(Constructor C){
		super(C);
	}

	
	public void initializeConstructor() {
		C = new aplicacion.herramientas.java.sortableselector.constructor._Constructor() {
			@Override
			protected void initialize_logic() {
				_logic = new aplicacion.herramientas.java.sortableselector.logic._Logic() {
					@Override
					public void Close(JTable table, int row) {
						System.out.println("Close!Articulo "+table+" "+row);
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
		logic.clean_construction();
		columna c = new columna();
		Filtro f = new Filtro();
		c = new columna();
		c.setNombre("v.idarticulo");
		c.setAlias("idarticulo");
		c.setColumnField(tx);
		c.setWidth(120);
		c.setMaster(true);
		logic.addColumn(c);

		c = new columna();
		c.setNombre("v.descripcion");
		c.setAlias("descripcion");
		c.setWidth(300);
		c.setMaster(false);
		logic.addColumn(c);

		c = new columna();
		c.setNombre("v.descripabrev");
		c.setAlias("linea");
		c.setWidth(140);
		c.setMaster(false);
		logic.addColumn(c);

		c = new columna();
		c.setNombre("ISNULL(s.cantidadud, 0)");
		c.setAlias("stock");
		c.setWidth(140);
		c.setMaster(false);
		logic.addColumn(c);

		c = new columna();
		c.setNombre("v.precio2");
		c.setAlias("Publico");
		c.setWidth(140);
		c.setMaster(false);
		logic.addColumn(c);

		logic
				.addFromTable("V_MA_ARTICULOS v LEFT OUTER JOIN Stk_Stock_UDKG s ON  v.IDARTICULO = s.IdArticulo ");
		f = new Filtro();
		f.setNombre("v.idarticulo");
		f.setAlias("idarticulo");
		f.setWidth(120);
		logic.addFilter(f);
		f = new Filtro();
		f.setNombre("v.descripcion");
		f.setAlias("descripcion");
		f.setWidth(250);
		f.setFocus(true);
		logic.addFilter(f);
		f = new Filtro();
		f.setNombre("v.descripabrev");
		f.setAlias("linea");
		f.setWidth(150);
		logic.addFilter(f);
		logic.setTop(200);
		logic.addOrder("v.idarticulo");
		logic.setTitle("Buscador de Articulo");
		logic.init();
		
	}
}
