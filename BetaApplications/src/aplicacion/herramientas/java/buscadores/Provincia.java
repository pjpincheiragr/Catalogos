package aplicacion.herramientas.java.buscadores;

import javax.swing.JTable;
import javax.swing.JTextField;

import aplicacion.herramientas.java.sortableselector.logic.Filtro;
import aplicacion.herramientas.java.sortableselector.logic.columna;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.constructor.*;
import aplicacion.herramientas.conexion.*;

/**
 * Aplicacion para evitar tener q crear a cada rato unas 100 lineas de codigo al
 * pedo. :-)
 * 
 * @author Agustin
 * 
 */
/*
 * Como utilizarlo? pegar este codigo en la aplicacion 
 private aplicacion.herramientas.java.buscadores.Provincia bProvincia=null; 
  public  void BuscarProvincia(JTextField ext) { 
  if (bProvincia!=null){  bProvincia.close(); } 
  bProvincia=new aplicacion.herramientas.java.buscadores.Provincia(this.getConstructor()); 
  bProvincia.Buscar(ext); 
  }
 */
public class Provincia extends Generico {

	public Provincia(Constructor C) {
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

	public void initializeLogic(JTextField tx) {
		aplicacion.herramientas.java.sortableselector.logic._Logic logic = (aplicacion.herramientas.java.sortableselector.logic._Logic) C
				.getLogic();
		logic.setCaller(tx);
		columna c = new columna();
		Filtro f = new Filtro();
		c = new columna();
		c.setNombre("codigo");
		c.setAlias("codigo");
		c.setWidth(70);
		c.setColumnField(tx);
		c.setMaster(true);
		
		logic.addColumn(c);

		c = new columna();
		c.setNombre("descripcion");
		c.setAlias("descripcion");
		c.setWidth(180);
		c.setMaster(false);
		logic.addColumn(c);


		logic.addFromTable("ta_estados ");
		
		f = new Filtro();
		f.setNombre("descripcion");
		f.setAlias("descripcion");
		f.setWidth(120);
		logic.addFilter(f);
		
		logic.addOrder("desripcion");
		
		logic.init();
	}
}
