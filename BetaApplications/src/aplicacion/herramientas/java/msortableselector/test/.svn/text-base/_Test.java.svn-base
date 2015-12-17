package aplicacion.herramientas.java.msortableselector.test;


import aplicacion.herramientas.conexion.conectores.*;
import aplicacion.herramientas.java.sortableselector.logic.Filtro;
import aplicacion.herramientas.java.sortableselector.logic.columna;


public class _Test {

	public void run(){
		aplicacion.herramientas.java.msortableselector.constructor._Constructor 
		CC=new aplicacion.herramientas.java.msortableselector.constructor._Constructor(){
			public void init(){
				super.init();
				aplicacion.herramientas.java.msortableselector.logic._Logic 
				logic=(aplicacion.herramientas.java.msortableselector.logic._Logic)getLogic();
				
				
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
		};
		
		CC.build(null);
		CC.init();
	}
	
	public static void main(String[] args){
		_Test test=new _Test();
		test.run();
		}
}
