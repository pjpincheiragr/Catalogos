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
 * Como utilizarlo? pegar este codigo en la aplicacion private
 * aplicacion.herramientas.java.buscadores.Politica bPolitica=null; public void
 * BuscarPolitica(JTextField ext) { if (bPolitica!=null){ bPolitica.close(); }
 * bPolitica=new
 * aplicacion.herramientas.java.buscadores.Politica(this.getConstructor
 * ().getConnectionHandler()); bPolitica.Buscar(ext); }
 */
public class Politica extends Generico {
	private JTextField descripcion;

	public JTextField getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(JTextField descripcion) {
		this.descripcion = descripcion;
	}

	public Politica(Constructor C) {
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
		c.setColumnField(tx);
		c.setWidth(120);
		c.setMaster(true);
		logic.addColumn(c);

		c = new columna();
		c.setNombre("descripcion");
		c.setAlias("descripcion");
		c.setWidth(250);
		c.setMaster(false);
		c.setColumnField(descripcion);
		logic.addColumn(c);

		logic.addFromTable("v_ta_politicaprecios ");
		f = new Filtro();
		f.setNombre("codigo");
		f.setAlias("codigo");
		f.setWidth(120);
		logic.addFilter(f);
		f = new Filtro();
		f.setNombre("descripcion");
		f.setAlias("descripcion");

		f.setWidth(250);
		logic.addFilter(f);
		logic.addOrder("codigo");
		logic.setTop(200);
		logic.setTitle("Buscador de Politica");
		logic.init();
	}
}
