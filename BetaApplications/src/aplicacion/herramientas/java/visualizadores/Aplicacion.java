package aplicacion.herramientas.java.visualizadores;

import javax.swing.JTextField;

import aplicacion.modelo.constructor.Constructor;
import java.awt.Rectangle;
import aplicacion.modelo.constructor.*;
import aplicacion.herramientas.conexion.ConnectionHandler;

public class Aplicacion extends Generico{
	
	private aplicacion.herramientas.java.visualizadores.Aplicacion vaplicacion=null;
/*	public void buscaraplicacion(JTextField tx) {
		if (vaplicacion!=null){
			vaplicacion.close();
		}
		vaplicacion=new aplicacion.herramientas.java.visualizadores.Aplicacion(this.getConstructor().getConnectionHandler());
		int n = vaplicacion.Buscar(tx);
		if (n == 0) {
				aviso("no hay aplicacions con ese codigo");
		}
	}
	*/


		private String idaplicacion="";	
		
		public String getIdaplicacion() {
			return idaplicacion;
		}

		public void setIdaplicacion(String idaplicacion) {
			this.idaplicacion = idaplicacion;
		}

		public Aplicacion(Constructor C){
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
			c.setNombre("idaplicacion");
			c.setAlias("idaplicacion");
			c.setColumnField(tx);
			c.setWidth(150);
			logic.addColumn(c);
			c = new aplicacion.herramientas.java.visualselector.logic.Columna();
			c.setNombre("menu_nombre");
			c.setAlias("nombre");
			c.setWidth(150);
			logic.addColumn(c);
//			c = new aplicacion.herramientas.java.visualselector.logic.Columna();
//			c.setNombre("os");
//			c.setAlias("sistema op");
//			c.setColumnField(tx);
//			c.setWidth(120);
//			logic.addColumn(c);
			logic.setFromTable("b_aplicaciones ");
			

			f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
			f.setNombre("idaplicacion");
			f.setValor(tx.getText());
			logic.addFilter(f);

			logic.setTop(100);
			
			logic.setOrderby("idaplicacion");

			logic.setTitle("Buscador de aplicacion");
			
			int n= logic._loadoptions();
			
			return n;

		}
		
	}


