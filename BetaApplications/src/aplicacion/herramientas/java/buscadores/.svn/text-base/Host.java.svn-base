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
private aplicacion.herramientas.java.buscadores.Host bHost=null;
public void BuscarHost(JTextField ext) {
	 if (bHost!=null){
		 bHost.close();
	 }
 bHost=new aplicacion.herramientas.java.buscadores.Host(this.getConstructor());
 bHost.Buscar(ext);
} 
 */
public class Host extends Generico{

	
	
	public Host(Constructor C){
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
	
/*	public void initializeLogic(JTextField tx){
		aplicacion.herramientas.java.sortableselector.logic._Logic logic = (aplicacion.herramientas.java.sortableselector.logic._Logic) C
		.getLogic();
		logic.setCaller(tx);

		columna c = new columna();
		Filtro f = new Filtro();
		
		c.setNomprivate aplicacion.herramientas.java.buscadores.Host bHost=null;
		public void BuscarHost(JTextField ext) {
			 if (bHost!=null){
				 bHost.close();
			 }
		 bHost=new aplicacion.herramientas.java.buscadores.Host(this.getConstructor());
		 bHost.Buscar(ext);
		} 
		 */
	//	public class Host extends Generico{

//			
//			
//			public Host(Constructor C){
//				super(C);
//			}
			
			
//			public void initializeConstructor() {
//				C = new aplicacion.herramientas.java.sortableselector.constructor._Constructor() {
//					@Override
//					protected void initialize_logic() {
//						_logic = new aplicacion.herramientas.java.sortableselector.logic._Logic() {
//							@Override
//							public void Close(JTable table, int row) {
//								super.Close(table, row);
//							}
//						};
//					}
//				};
//			}
			
			public void initializeLogic(JTextField tx){
				aplicacion.herramientas.java.sortableselector.logic._Logic logic = (aplicacion.herramientas.java.sortableselector.logic._Logic) C
				.getLogic();
				logic.setCaller(tx);

				columna c = new columna();
				Filtro f = new Filtro();
				
				c.setNombre("idhost");
				c.setAlias("IdHost");
				c.setWidth(120);
				c.setMaster(true);
				c.setColumnField(tx);
				logic.addColumn(c);

				c = new columna();
				c.setNombre("Ip");
				c.setAlias("Ip");
				c.setWidth(120);
				logic.addColumn(c);

				logic.addFromTable("b_host");
				f = new Filtro();
				f.setNombre("idhost");
				f.setAlias("IdHost");
				f.setWidth(200);
				f.setFocus(true);
				logic.addFilter(f);

				f = new Filtro();
				f.setNombre("ip");
				f.setAlias("Ip");
				f.setWidth(200);
				f.setFocus(true);
				logic.addFilter(f);
				
				logic.addOrder("idhost");
				
				logic.init();
			}
			
//		 bre("idhost");
//		c.setAlias("IdHost");
//		c.setWidth(120);
//		c.setMaster(true);
//		c.setColumnField(tx);
//		logic.addColumn(c);
//
//		c = new columna();
//		c.setNombre("Ip");
//		c.setAlias("Ip");
//		c.setWidth(120);
//		logic.addColumn(c);
//
//		logic.addFromTable("b_host");
//		f = new Filtro();
//		f.setNombre("idhost");
//		f.setAlias("IdHost");
//		f.setWidth(200);
//		f.setFocus(true);
//		logic.addFilter(f);
//
//		f = new Filtro();
//		f.setNombre("ip");
//		f.setAlias("Ip");
//		f.setWidth(200);
//		f.setFocus(true);
//		logic.addFilter(f);
//		
//		logic.addOrder("idhost");
//		
//		logic.init();
	}

