package aplicacion.herramientas.java.sortableselector.test;


import aplicacion.herramientas.conexion.conectores.*;
import aplicacion.herramientas.java.sortableselector.logic.Filtro;
import aplicacion.herramientas.java.sortableselector.logic.columna;


public class _Test {

	public void run(){
		aplicacion.herramientas.java.sortableselector.constructor._Constructor 
		CC=new aplicacion.herramientas.java.sortableselector.constructor._Constructor();
		
		MsSQL mssql=new MsSQL(CC);
		mssql.setHost("192.168.4.1");
		mssql.setPort(1433);
		mssql.setUser("Neuquen2006");
		mssql.setPassword("Neuquen2006");
		mssql.setDatabase("Neuquen2006");
		mssql.setTdsVersion("8.0");
		mssql.setId("Neuquen");
		
		
		CC.build(null);
		CC.addConnector(mssql);
		CC.init();
		aplicacion.herramientas.java.sortableselector.logic._Logic 
		logic=(aplicacion.herramientas.java.sortableselector.logic._Logic)CC.getLogic();
		
		
		columna c=new columna();
		c.setNombre("codigo");
		c.setAlias("codigo");
		c.setWidth(100);
		logic.addColumn(c);
		
		c=new columna();
		c.setNombre("descripcion");
		c.setAlias("descripcion");
		c.setWidth(180);
		logic.addColumn(c);
		
		Filtro f=new Filtro();
		f.setNombre("codigo");
		f.setAlias("codigo");
		f.setWidth(120);
		logic.addFilter(f);
		
		f=new Filtro();
		f.setNombre("descripcion");
		f.setAlias("descripcion");
		f.setWidth(180);
		logic.addFilter(f);
		
		logic.addFromTable("ma_cuentas");
		logic.init();
	}
	public static void main(String[] args){
		_Test test=new _Test();
		test.run();
		}
}
