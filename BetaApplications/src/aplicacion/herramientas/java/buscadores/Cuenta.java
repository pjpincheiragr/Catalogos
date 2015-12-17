package aplicacion.herramientas.java.buscadores;

import javax.swing.JTable;
import javax.swing.JTextField;

import aplicacion.herramientas.java.sortableselector.logic.Filtro;
import aplicacion.herramientas.java.sortableselector.logic.columna;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.constructor.*;
import aplicacion.herramientas.conexion.*;
/**
 * Aplicacion para evitar tener q crear a cada rato unas 100 lineas de codigo al pedo. :-)
 * @author Agustin
 *
 */
/* Como utilizarlo? pegar este codigo en la aplicacion
private aplicacion.herramientas.java.buscadores.Cuenta bCuenta=null;
public void BuscarCuenta(JTextField ext) {
	 if (bCuenta!=null){
		 bCuenta.close();
	 }
 bCuenta=new aplicacion.herramientas.java.buscadores.Cuenta(this.getConstructor());
 bCuenta.Buscar(ext);
} 
 */
public class Cuenta extends Generico{
	
	public Cuenta(Constructor C){
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
		logic.addColumn(c);


		logic.addFromTable("ma_cuentas ");
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
		logic.setTitle("Buscador de Cuenta");
		
		logic.addOrder("codigo");
		logic.addRestriction("titulo=0");
		logic.init();
	}
}