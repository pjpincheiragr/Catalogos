package aplicacion.herramientas.java.visualselector.test;


import aplicacion.herramientas.conexion.ConnectionHandler;
import aplicacion.herramientas.conexion.conectores.MsSQL;
import aplicacion.herramientas.conexion.conectores.SQLite;
import aplicacion.herramientas.java.visualselector.constructor.*;
import aplicacion.herramientas.java.visualselector.logic.*;
import aplicacion.modelo.interfaces._parametros;
public class _Test {
	
	public static void main(String[] args){
		_Constructor CC=new _Constructor();
		CC.build(null);
		CC.init();
		_Logic logic=(_Logic)CC.getLogic();
		Columna c = new Columna();
		Filtro f = new Filtro();
		c = new Columna();
		c.setNombre("idarticulo");
		c.setAlias("idarticulo");
		//c.setColumnField(ext);
		c.setWidth(120);
		c.setMaster(true);
		logic.addColumn(c);

		c = new Columna();
		c.setNombre("descripcion");
		c.setAlias("descripcion");
		c.setWidth(420);
		c.setMaster(false);
		logic.addColumn(c);


		logic.setFromTable("v_ma_articulos ");
		f = new Filtro();
		f.setNombre("idarticulo");
		f.setAlias("idarticulo");
		f.setValor("033");
		logic.addFilter(f);
		f = new Filtro();
		f.setNombre("descripcion");
		f.setAlias("descripcion");
		
		logic.addFilter(f);
		f = new Filtro();
		f.setNombre("descripabrev");
		f.setAlias("linea");
		
		logic.addFilter(f);
		logic.setTop(10);
		logic.setOrderby("idarticulo");
		
		int n=logic._loadoptions();
		if (n==0){
			
		}
		

		
	}
	
	
}
