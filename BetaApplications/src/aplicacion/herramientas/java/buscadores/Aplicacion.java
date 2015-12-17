package aplicacion.herramientas.java.buscadores;


import javax.swing.JTable;
import javax.swing.JTextField;

import aplicacion.herramientas.java.sortableselector.logic.Filtro;
import aplicacion.herramientas.java.sortableselector.logic.columna;
import aplicacion.modelo.constructor.Constructor;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.herramientas.conexion.*;

public class Aplicacion extends Generico{
/*	private aplicacion.herramientas.java.buscadores.Aplicacion baplicacion=null;
	public void BuscarAplicacions(JTextField ext) {
		 if (baplicacion!=null){
			 baplicacion.close();
		 }
	 baplicacion=new aplicacion.herramientas.java.buscadores.Aplicacion(this.getConstructor());
	 baplicacion.Buscar(ext);
	}*/ 
	 


		
		
		public Aplicacion(Constructor C){
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
			
			c.setNombre("idaplicacion");
			c.setAlias("Idaplicacion");
			c.setWidth(150);
			c.setMaster(true);
			c.setColumnField(tx);
			logic.addColumn(c);

			c = new columna();
			c.setNombre("menu_nombre");
			c.setAlias("nombre");
			c.setWidth(150);
			logic.addColumn(c);

			logic.addFromTable("b_aplicaciones");
			f = new Filtro();
			f.setNombre("idaplicacion");
			f.setAlias("Idaplicacion");
			f.setWidth(200);
			f.setFocus(true);
			logic.addFilter(f);

			f = new Filtro();
			f.setNombre("menu_nombre");
			f.setAlias("nombre");
			f.setWidth(200);
			f.setFocus(true);
			logic.addFilter(f);
			
			logic.addOrder("idaplicacion");
			
			logic.init();
		}
	 
}
